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

/**
 * Objects of this class
 * represent a variable for
 * parsed expressions
 */
public class Variable implements Evaluable {

  public static boolean isNameValid(String name) {

    if (name.isEmpty())
      return false;
    if (!Character.isAlphabetic(name.charAt(0)) && name.charAt(0) != '_')
      return false;

    /* technically this double checks the first character */
    for(char c : name.toCharArray()) {

      if (
              !Character.isAlphabetic(c) &&
              !Character.isDigit(c) &&
              c != '_'
              )
        return false;

    }

    return true;
  }

  public final String name;
  private Value value;

  public Variable(String name, Value value) {
    if (value == null)
      throw new IllegalArgumentException("Initial getValue of variable cannot be set to null");
    if (name == null)
      throw new IllegalArgumentException("Name of variable cannot be set to null");
    if (!isNameValid(name))
      throw new IllegalArgumentException(String.format("Name of variable '%s' is not valid", name));
    this.name = name;
    this.value = value;
  }
  public Variable(String name, Object value) {
    if (value == null)
      throw new IllegalArgumentException("Initial getValue of variable cannot be set to null");
    if (name == null)
      throw new IllegalArgumentException("Name of variable cannot be set to null");
    if (!isNameValid(name))
      throw new IllegalArgumentException(String.format("Name of variable '%s' is not valid", name));
    this.name = name;
    this.value = new Value<>(value);
  }
  public Variable(String name) {
    if (name == null)
      throw new IllegalArgumentException("Name of variable cannot be set to null");
    if (!isNameValid(name))
      throw new IllegalArgumentException(String.format("Name of variable '%s' is not valid", name));
    this.name = name;
    this.value = Value.ZERO;
  }

  public void setValue(Value value) {
    if (value == null)
      throw new IllegalArgumentException("Cannot set getValue of variable to null");


    this.value = value;
  }

  @Override
  public Evaluable clone() {
    return new Variable(name, value);
  }

  @Override
  public Value getValue(ArgumentList argList) {
    return value.getValue(argList);
  }

  @Override
  public EvaluableType getEvaluableType() {
    return EvaluableType.VARIABLE;
  }

  @Override
  public Evaluable derivative(String variable) {
    return new Value<>(1.0);
  }

  @Override
  public boolean isZero() {
    return false;
  }

  @Override
  public boolean isOne() {
    return false;
  }

  @Override
  public Evaluable replaceVariable(String varName, Evaluable evaluable) {
    if (name.equals(varName))
      return evaluable;
    else
      return this;
  }

  @Override
  public String toString() {
    return String.format("%s", name);
  }

}
