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

import jmath.function.parsing.FunctionParser;

import java.util.LinkedList;

/**
 * A class from which all operations
 * inherit.
 */
public abstract class Operation implements Evaluable {


  public static Evaluable parse(String string) {
    return FunctionParser.parseEvaluable(string);
  }



  static boolean isOrContainsVariables(Evaluable e) {

    switch(e.getEvaluableType()) {
      case LITERAL:
        return false;
      case VARIABLE:
        return true;
      case OPERATION:
        return ((Operation) e).containsVariables();
      default:
        throw new RuntimeException(); // Should not happen
    }
  }

  static boolean isOrContainsVariable(Evaluable e, String name) {

    switch(e.getEvaluableType()) {
      case LITERAL:
        return false;
      case VARIABLE:
        if (((Variable) e).name.equals(name))
          return true;
        else
          return false;
      case OPERATION:
        return ((Operation) e).containsVariable(name);
      default:
        throw new RuntimeException(); // Should not happen
    }
  }

  static void addToVariableList(LinkedList<Variable> variables, Evaluable e) {

    switch(e.getEvaluableType()) {
      case LITERAL:
        return;
      case VARIABLE:
        variables.add((Variable) e);
        return;
      case OPERATION:
        ((Operation) e).generateVariableList(variables);
        return;
      default:
        throw new RuntimeException(); // Should not happen
    }
  }

  static String getEvaluableString(Evaluable e) {
    switch(e.getEvaluableType()) {
      case LITERAL:
        return ((Value) e).getValue().toString();
      case VARIABLE:
        return ((Variable) e).name;
      case OPERATION:
        return e.toString();
      default:
        throw new RuntimeException(); // Should not happen
    }
  }




  public final OperationType type;

  public Operation (OperationType type) {
    if (type == null)
      throw new IllegalArgumentException("Operation type cannot be null");
    this.type = type;
  }

  public abstract Operation clone();
  public abstract Evaluable simplify();
  public abstract boolean containsVariables();
  public abstract boolean containsVariable(String name);
  abstract void generateVariableList(LinkedList<Variable> variables);

  public boolean isZero() {
    return false;
  }
  public boolean isOne() {
    return false;
  }

  @Override
  public EvaluableType getEvaluableType() {
    return EvaluableType.OPERATION;
  }


}
