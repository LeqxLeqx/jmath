/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\
 *                                                                         *
 *  jmath: a library for mathematical computation for Java                 *
 *  Copyright (C) 2017  LeqxLeqx                                           *
 *                                                                         *
 *  This program is free software: you can redistribute it and/or modify   *
 *  it under the terms of the GNU General Public License as published by   *
 *  the Free Software Foundation, either version 3 of the License, or      *
 *  (at your option) any later version.                                    *
 *                                                                         *
 *  This program is distributed in the hope that it will be useful,        *
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of         *
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the          *
 *  GNU General Public License for more details.                           *
 *                                                                         *
 *  You should have received a copy of the GNU General Public License      *
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.  *
 *                                                                         *
\* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package jmath.function;

import jmath.JMath;
import jmath.types.Complex;

import java.util.LinkedList;

/**
 * Author:    LeqxLeqx
 */
public class BinaryOperation extends Operation {


  private static boolean checkTypeConstraint(OperationType operationType) {

    switch (operationType) {

      case MAX:
      case MIN:
        return true;
      default:
        return false;

    }
  }

  private final Evaluable argument1;
  private final Evaluable argument2;

  public BinaryOperation(OperationType type, Evaluable arg1, Evaluable arg2) {
    super(type);
    if (!checkTypeConstraint(type))
      throw new IllegalArgumentException("Cannot create binary operation of type: " + type);
    if (arg1 == null || arg2 == null)
      throw new IllegalArgumentException("Cannot operate on null values");

    argument1 = arg1;
    argument2 = arg2;
  }

  @Override
  public Operation clone() {
    return new BinaryOperation(type, argument1.clone(), argument2.clone());
  }

  @Override
  public Evaluable simplify() {
    Evaluable e1, e2;
    boolean e1Const, e2Const;

    if (Operation.isOrContainsVariables(argument1)) {
      if (argument1.getEvaluableType() == EvaluableType.OPERATION)
        e1 = ((Operation) argument1).simplify();
      else
        e1 = argument1;
      e1Const = false;
    }
    else {
      e1 = argument1.getValue(ArgumentList.NONE);
      e1Const = true;
    }

    if (Operation.isOrContainsVariables(argument2)) {
      if (argument2.getEvaluableType() == EvaluableType.OPERATION)
        e2 = ((Operation) argument2).simplify();
      else
        e2 = argument2;
      e2Const = false;
    }
    else {
      e2 = argument2.getValue(ArgumentList.NONE);
      e2Const = true;
    }

    if (e1Const && e2Const)
      return new BinaryOperation(type, e1, e2).getValue(ArgumentList.NONE);
    else
      return new BinaryOperation(type, e1, e2);
  }

  @Override
  public boolean containsVariables() {
    return Operation.isOrContainsVariables(argument1) || Operation.isOrContainsVariables(argument2);
  }

  @Override
  public boolean containsVariable(String name) {
    return Operation.isOrContainsVariable(argument1, name) || Operation.isOrContainsVariable(argument2, name);
  }

  @Override
  public Operation derivative(String variable) {
    switch (type) {

      case MAX:
        return new PieceWiseOperation(
                argument1.derivative(variable),
                argument2.derivative(variable),
                new Equation(
                        EquationType.GREATER_THAN_OR_EQUAL,
                        new Expression(argument1),
                        new Expression(argument2)
                )
          );

      case MIN:
        return new PieceWiseOperation(
                argument1.derivative(variable),
                argument2.derivative(variable),
                new Equation(
                        EquationType.LESS_THAN_OR_EQUAL,
                        new Expression(argument1),
                        new Expression(argument2)
                )
          );

      default:
        throw new RuntimeException();

    }
  }

  @Override
  public Evaluable replaceVariable(String varName, Evaluable evaluable) {
    Evaluable e1, e2;

    if (Operation.isOrContainsVariable(argument1, varName))
      e1 = argument1.replaceVariable(varName, evaluable);
    else
      e1 = argument1;

    if (Operation.isOrContainsVariable(argument2, varName))
      e2 = argument2.replaceVariable(varName, evaluable);
    else
      e2 = argument2;

    return new BinaryOperation(type, e1, e2);
  }

  @Override
  void generateVariableList(LinkedList<Variable> variables) {
    Operation.addToVariableList(variables, argument1);
    Operation.addToVariableList(variables, argument2);
  }

  @Override
  public Value getValue(ArgumentList argList) {
    switch (type) {

      case MAX: return max(argList);
      case MIN: return min(argList);

      default:
        throw new RuntimeException();

    }
  }


  private Value max(ArgumentList argList) {
    Value
            v1 = argument1.getValue(argList),
            v2 = argument2.getValue(argList);

    ValueType
            t1 = v1.type,
            t2 = v2.type;

    if (t1 == ValueType.COMPLEX) {
      if (((Complex) v1.getValue()).isReal()) {
        v1 = new Value<>(((Complex) v1.getValue()).real);
        t1 = ValueType.REAL;
      }
    }
    if (t2 == ValueType.COMPLEX) {
      if (((Complex) v2.getValue()).isReal()) {
        v2 = new Value<>(((Complex) v2.getValue()).real);
        t2 = ValueType.REAL;
      }
    }

    if (t1 == ValueType.REAL && t2 == ValueType.REAL)
      return new Value<>(JMath.max((Double) v1.getValue(), (Double) v2.getValue()));
    else
      throw new OperationException(type, "Cannot ascertain maximum between non-orderable values of types %s and %s", t1, t2);

  }

  private Value min(ArgumentList argList) {

    Value
            v1 = argument1.getValue(argList),
            v2 = argument2.getValue(argList);

    ValueType
            t1 = v1.type,
            t2 = v2.type;

    if (t1 == ValueType.REAL && t2 == ValueType.REAL)
      return new Value<>(JMath.min((Double) v1.getValue(), (Double) v2.getValue()));
    else
      throw new OperationException(type, "Cannot ascertain minimum between non-orderable values of types %s and %s", t1, t2);
  }

  @Override
  public String toString() {

    String initial;

    switch(type) {

      case MAX:
        initial = "max";
        break;
      case MIN:
        initial = "min";
        break;

      default:
        throw new RuntimeException();

    }

    return String.format("%s(%s, %s)", initial, Operation.getEvaluableString(argument1), Operation.getEvaluableString(argument2));
  }


}
