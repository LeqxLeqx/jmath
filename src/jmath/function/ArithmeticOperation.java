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
import jmath.types.*;

import java.util.LinkedList;

import static jmath.function.OperationType.DIVISION;

/**
 * Class for objects representing
 * arithmetic operations
 */
public class ArithmeticOperation extends Operation {



  private static boolean checkTypeConstraint(OperationType operationType) {
    switch(operationType) {

      case ADDITION:
      case SUBTRACTION:
      case MULTIPLICATION:
      case DIVISION:
      case EXPONENT:
      case LOGARITHM:
        return true;
      default:
        return false;
    }
  }

  public final Evaluable
            argument1,
            argument2
            ;

  public ArithmeticOperation(OperationType type, Evaluable v1, Evaluable v2) {
    super(type);
    if (!checkTypeConstraint(type))
      throw new IllegalArgumentException("Cannot create arithmetic operation of type " + type);
    if (v1 == null || v2 == null)
      throw new IllegalArgumentException("Cannot operate on null values");

    argument1 = v1;
    argument2 = v2;
  }

  @Override
  public Operation clone() {
    return new ArithmeticOperation(type, argument1.clone(), argument2.clone());
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

    switch (type) {

      case SUBTRACTION:
      case ADDITION:
        if (e1.isZero())
          return e2;
        if (e2.isZero())
          return e1;
        break;

      case MULTIPLICATION:
        if (e2.isZero())
          return e2;
      case DIVISION:
        if (e1.isOne())
          return e2;
        if (e2.isOne())
          return e1;
        if (e1.isZero())
          return e1;
        break;


      case EXPONENT:
        if (e1.isOne())
          return new Value<>(1.0);
        if (e2.isOne())
          return e1;
        if (e2.isZero())
          return new Value<>(1.0);

    }


    if (e1Const && e2Const)
      return new ArithmeticOperation(type, e1, e2).getValue(ArgumentList.NONE);
    else
      return new ArithmeticOperation(type, e1, e2);
  }

  @Override
  public boolean containsVariables() {
    return Operation.isOrContainsVariables(argument1) || Operation.isOrContainsVariables(argument2);
  }

  @Override
  public boolean containsVariable(String name) {
    return isOrContainsVariable(argument1, name) || isOrContainsVariable(argument2, name);
  }

  @Override
  public Evaluable derivative(String variable) {

    if (!Operation.isOrContainsVariable(argument1, variable) && !Operation.isOrContainsVariable(argument2, variable))
      return Value.ZERO;


    switch(type) {

      case ADDITION:
        return new ArithmeticOperation(
                OperationType.ADDITION,
                argument1.derivative(variable),
                argument2.derivative(variable)
          );
      case SUBTRACTION:
        return new ArithmeticOperation(OperationType.SUBTRACTION, argument1.derivative(variable), argument2.derivative(variable));
      case MULTIPLICATION:
        return new ArithmeticOperation(
                OperationType.ADDITION,
                new ArithmeticOperation(OperationType.MULTIPLICATION,
                        argument1.clone(),
                        argument2.derivative(variable)
                        ),
                new ArithmeticOperation(OperationType.MULTIPLICATION,
                        argument2.clone(),
                        argument1.derivative(variable)
                        )
                );
      case DIVISION:
        return new ArithmeticOperation(
                DIVISION,
                new ArithmeticOperation(OperationType.SUBTRACTION,
                    new ArithmeticOperation(OperationType.MULTIPLICATION,
                      argument1.derivative(variable),
                      argument2.clone()
                    ),
                    new ArithmeticOperation(OperationType.MULTIPLICATION,
                      argument1.clone(),
                      argument2.derivative(variable)
                    )
                  ),
                new ArithmeticOperation(OperationType.EXPONENT,
                    argument2.clone(),
                    new Value<>(2.0)
                  )
                );
      case EXPONENT:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                new ArithmeticOperation(OperationType.EXPONENT,
                  argument1.clone(),
                  new ArithmeticOperation(OperationType.SUBTRACTION,
                    argument2.clone(),
                    new Value<>(1.0)
                  )
                ),
                new ArithmeticOperation(OperationType.ADDITION,
                  new ArithmeticOperation(OperationType.MULTIPLICATION,
                    argument2.clone(),
                    argument1.derivative(variable)
                  ),
                  new ArithmeticOperation(OperationType.MULTIPLICATION,
                    argument1.clone(),
                    new ArithmeticOperation(OperationType.MULTIPLICATION,
                      new ArithmeticOperation(OperationType.LOGARITHM,
                        argument1.clone(),
                        new Value<>(JMath.E)
                      ),
                      argument2.derivative(variable)
                    )
                  )
                )
              );

      case LOGARITHM:
        return new ArithmeticOperation(DIVISION,
                new ArithmeticOperation(OperationType.SUBTRACTION,
                  new ArithmeticOperation(DIVISION,
                    new ArithmeticOperation(OperationType.MULTIPLICATION,
                      new ArithmeticOperation(OperationType.LOGARITHM,
                        argument1,
                        new Value<>(JMath.E)
                      ),
                      argument2.derivative(variable)
                    ),
                    argument2
                  ),
                  new ArithmeticOperation(DIVISION,
                    new ArithmeticOperation(OperationType.MULTIPLICATION,
                      argument1.derivative(variable),
                      new ArithmeticOperation(OperationType.LOGARITHM,
                        argument2,
                        new Value<>(JMath.E)
                      )
                    ),
                    argument1
                  )
                ),
                new ArithmeticOperation(OperationType.EXPONENT,
                  new ArithmeticOperation(OperationType.LOGARITHM,
                    argument1,
                    new Value<>(JMath.E)
                  ),
                  new Value<>(2.0)
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

    return new ArithmeticOperation(type, e1, e2);
  }

  @Override
  void generateVariableList(LinkedList<Variable> variables) {
    addToVariableList(variables, argument1);
    addToVariableList(variables, argument2);
  }

  @Override
  public Value getValue(ArgumentList argList) {

    switch(type) {

      case ADDITION: return addition(argList);
      case SUBTRACTION: return subtraction(argList);
      case MULTIPLICATION: return multiplication(argList);
      case DIVISION: return division(argList);
      case EXPONENT: return exponent(argList);
      case LOGARITHM: return logarithm(argList);

      default:
        throw new RuntimeException();

    }


  }

  private Value addition(ArgumentList argList) {

    Value
        v1 = argument1.getValue(argList),
        v2 = argument2.getValue(argList);

    ValueType
        t1 = v1.type,
        t2 = v2.type;

    if (t1 == ValueType.REAL && t2 == ValueType.REAL)
      return new Value<>((Double) v1.getValue() + (Double) v2.getValue());
    else if (t1 == ValueType.COMPLEX && t2 == ValueType.COMPLEX)
      return new Value<>(Complex.add((Complex) v1.getValue(), (Complex) v2.getValue()));
    else if (t1 == ValueType.VECTOR && t2 == ValueType.VECTOR)
      return new Value<>(NVector.add((IVector) v1.getValue(), (IVector) v2.getValue()));
    else if (t1 == ValueType.MATRIX && t2 == ValueType.MATRIX)
      return new Value<>(NMatrix.add((IMatrix) v1.getValue(), (IMatrix) v2.getValue()));
    else if (t1 == ValueType.REAL && t2 == ValueType.COMPLEX)
      return new Value<>(Complex.add(Complex.real((Double) v1.getValue()), (Complex) v2.getValue()));
    else if (t1 == ValueType.COMPLEX && t2 == ValueType.REAL)
      return new Value<>(Complex.add((Complex) v1.getValue(), Complex.real((Double) v2.getValue())));
    else
      throw new OperationException(type, "Cannot add values of types %s and %s", t1, t2);

  }

  private Value subtraction(ArgumentList argList) {

    Value
            v1 = argument1.getValue(argList),
            v2 = argument2.getValue(argList);

    ValueType
            t1 = v1.type,
            t2 = v2.type;

    if (t1 == ValueType.REAL && t2 == ValueType.REAL)
      return new Value<>((Double) v1.getValue() - (Double) v2.getValue());
    else if (t1 == ValueType.COMPLEX && t2 == ValueType.COMPLEX)
      return new Value<>(((Complex) v1.getValue()).sub((Complex) v2.getValue()));
    else if (t1 == ValueType.VECTOR && t2 == ValueType.VECTOR)
      return new Value<>(((IVector) v1.getValue()).sub((IVector) v2.getValue()));
    else if (t1 == ValueType.MATRIX && t2 == ValueType.MATRIX)
      return new Value<>(((IMatrix) v1.getValue()).sub((IMatrix) v2.getValue()));
    else if (t1 == ValueType.REAL && t2 == ValueType.COMPLEX)
      return new Value<>(Complex.real((Double) v1.getValue()).sub((Complex) v2.getValue()));
    else if (t1 == ValueType.COMPLEX && t2 == ValueType.REAL)
      return new Value<>(((Complex) v1.getValue()).sub(Complex.real((Double) v2.getValue())));
    else
      throw new OperationException(type, "Cannot subtract values of types %s and %s", t1, t2);
  }

  private Value multiplication(ArgumentList argList) {

    Value
            v1 = argument1.getValue(argList),
            v2 = argument2.getValue(argList);

    ValueType
            t1 = v1.type,
            t2 = v2.type;

    if (t1 == ValueType.REAL && t2 == ValueType.REAL)
      return new Value<>((Double) v1.getValue() * (Double) v2.getValue());
    else if (t1 == ValueType.COMPLEX && t2 == ValueType.COMPLEX)
      return new Value<>(Complex.multiply((Complex) v1.getValue(), (Complex) v2.getValue()));
    else if (t1 == ValueType.VECTOR && t2 == ValueType.VECTOR)
      return new Value<>(NVector.dot((IVector) v1.getValue(), (IVector) v2.getValue()));
    else if (t1 == ValueType.MATRIX && t2 == ValueType.MATRIX)
      return new Value<>(((IMatrix) v1.getValue()).multiply((IMatrix) v2.getValue()));
    else if (t1 == ValueType.REAL && t2 == ValueType.COMPLEX)
      return new Value<>(Complex.multiply(Complex.real((Double) v1.getValue()), (Complex) v2.getValue()));
    else if (t1 == ValueType.REAL && t2 == ValueType.VECTOR)
      return new Value<>(((IVector) v1.getValue()).scale((Double) v2.getValue()));
    else if (t1 == ValueType.REAL && t2 == ValueType.MATRIX)
      return new Value<>(((IMatrix) v1.getValue()).scale((Double) v2.getValue()));
    else if (t1 == ValueType.COMPLEX && t2 == ValueType.REAL)
      return new Value<>(Complex.multiply((Complex) v1.getValue(), Complex.real((Double) v2.getValue())));
    else if (t1 == ValueType.VECTOR && t2 == ValueType.REAL)
      return new Value<>(((IVector) v1.getValue()).scale((Double) v2.getValue()));
    else if (t1 == ValueType.MATRIX && t2 == ValueType.REAL)
      return new Value<>(((IMatrix) v1.getValue()).scale((Double) v2.getValue()));
    else if (t1 == ValueType.MATRIX && t2 == ValueType.VECTOR)
      return new Value<>(((IMatrix) v1.getValue()).transform((IVector) v2.getValue()));
    else
      throw new OperationException(type, "Cannot multiply values of types %s and %s", t1, t2);

  }

  private Value division(ArgumentList argList) {

    Value
            v1 = argument1.getValue(argList),
            v2 = argument2.getValue(argList);

    ValueType
            t1 = v1.type,
            t2 = v2.type;

    if (t1 == ValueType.REAL && t2 == ValueType.REAL)
      return new Value<>((Double) v1.getValue() / (Double) v2.getValue());
    else if (t1 == ValueType.COMPLEX && t2 == ValueType.COMPLEX)
      return new Value<>(((Complex) v1.getValue()).div((Complex) v2.getValue()));
    else if (t1 == ValueType.REAL && t2 == ValueType.COMPLEX)
      return new Value<>(Complex.real((Double) v1.getValue()).div((Complex) v2.getValue()));
    else if (t1 == ValueType.COMPLEX && t2 == ValueType.REAL)
      return new Value<>(((Complex) v1.getValue()).div(Complex.real((Double) v2.getValue())));
    else if (t1 == ValueType.VECTOR && t2 == ValueType.REAL)
      return new Value<>(((IVector) v1.getValue()).scale(1 / (Double) v2.getValue()));
    else if (t1 == ValueType.MATRIX && t2 == ValueType.REAL)
      return new Value<>(((IMatrix) v1.getValue()).scale(1 / (Double) v2.getValue()));
    else
      throw new OperationException(type, "Cannot divide values of types %s and %s", t1, t2);

  }

  private Value exponent(ArgumentList argList) {

    Value
            v1 = argument1.getValue(argList),
            v2 = argument2.getValue(argList);

    ValueType
            t1 = v1.type,
            t2 = v2.type;

    if (t1 == ValueType.REAL && t2 == ValueType.REAL)
      return new Value<>(JMath.pow((Double) v1.getValue(), (Double) v2.getValue()));
    else if (t1 == ValueType.COMPLEX && t2 == ValueType.COMPLEX)
      return new Value<>(((Complex) v1.getValue()).pow((Complex) v2.getValue()));
    else if (t1 == ValueType.REAL && t2 == ValueType.COMPLEX)
      return new Value<>(Complex.real((Double) v1.getValue()).pow((Complex) v2.getValue()));
    else if (t1 == ValueType.COMPLEX && t2 == ValueType.REAL)
      return new Value<>(((Complex) v1.getValue()).pow(Complex.real((Double) v2.getValue())));
    else
      throw new OperationException(type, "Cannot divide values of types %s and %s", t1, t2);
  }

  private Value logarithm(ArgumentList argList) {

    Value
            v1 = argument1.getValue(argList),
            v2 = argument2.getValue(argList);

    ValueType
            t1 = v1.type,
            t2 = v2.type;

    if (t1 == ValueType.REAL && t2 == ValueType.REAL)
      return new Value<>(JMath.logBase((Double) v1.getValue(), (Double) v2.getValue()));
    else if (t1 == ValueType.COMPLEX && t2 == ValueType.COMPLEX)
      return new Value<>(JMath.logBase(((Complex) v1.getValue()), (Complex) v2.getValue()));
    else if (t1 == ValueType.REAL && t2 == ValueType.COMPLEX)
      return new Value<>(JMath.logBase(Complex.real((Double) v1.getValue()), (Complex) v2.getValue()));
    else if (t1 == ValueType.COMPLEX && t2 == ValueType.REAL)
      return new Value<>(JMath.logBase((Complex) v1.getValue(), Complex.real((Double) v2.getValue())));
    else
      throw new OperationException(type, "Cannot divide values of types %s and %s", t1, t2);
  }

  @Override
  public String toString() {

    String s1 = getEvaluableString(argument1);
    String s2 = getEvaluableString(argument2);

    if (argument1.getEvaluableType() == EvaluableType.OPERATION && ((Operation) argument1).type.operativeOrder < type.operativeOrder && ((Operation) argument1).type.operativeOrder != 10)
      s1 = String.format("(%s)", s1);
    if (argument2.getEvaluableType() == EvaluableType.OPERATION && ((Operation) argument2).type.operativeOrder < type.operativeOrder && ((Operation) argument2).type.operativeOrder != 10)
      s2 = String.format("(%s)", s2);


    switch(type) {

      case ADDITION:
        return String.format("%s + %s", s1, s2);
      case SUBTRACTION:
        return String.format("%s - %s", s1, s2);
      case MULTIPLICATION:
        return String.format("%s * %s", s1, s2);
      case DIVISION:
        return String.format("%s / %s", s1, s2);
      case EXPONENT:
        return String.format("%s ^ %s", s1, s2);
      case LOGARITHM:
        return String.format("log-%s(%s)", s2, s1);

      default:
        throw new RuntimeException();

    }

  }

}
