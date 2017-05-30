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

import jmath.types.Complex;
import jmath.types.NMatrix;
import jmath.types.NVector;

import java.util.LinkedList;

/**
 * Class representing an ordered
 * list of arguments to a function
 */
public class ArgumentList {

  public static ArgumentList NONE = new ArgumentList(new String[0], new Object[0]);

  public static ArgumentList create(String string, Object... params) {
    return create(String.format(string, params));
  }

  public static ArgumentList create(String string) {

    if (string == null)
      throw new IllegalArgumentException("Cannot parseEvaluable null string as argument list");

    String[] split = string.split("\\;");
    LinkedList<String> names = new LinkedList<>();
    LinkedList<Object> values = new LinkedList<>();

    for(String s : split) {

      if (s.trim().isEmpty()) continue;

      String[] subSplit = s.split("\\=", 2);

      if (subSplit.length != 2)
        throw new IllegalArgumentException(String.format("Cannot parseEvaluable string '%s' as an argument list", string));

      subSplit[0] = subSplit[0].trim();
      subSplit[1] = subSplit[1].trim();

      ValueType valueType = ValueType.getValueType(subSplit[1]);

      if (valueType == null)
        throw new IllegalArgumentException(String.format("Cannot parseEvaluable string '%s' as a getValue", subSplit[1]));

      names.add(subSplit[0]);
      switch (valueType) {

        case REAL:
          values.add(Double.parseDouble(subSplit[1]));
          break;
        case COMPLEX:
          values.add(Complex.parse(subSplit[1]));
          break;
        case VECTOR:
          values.add(NVector.parse(subSplit[1]));
          break;
        case MATRIX:
          values.add(NMatrix.parse(subSplit[1]));
          break;

        default:
          throw new RuntimeException();

      }

    }

    return new ArgumentList(
            names.toArray(new String[names.size()]),
            values.toArray(new Object[values.size()])
        );
  }


  private String[] variableNames;
  private Object[] values;

  private ArgumentList(String[] variableNames, Object[] values) {

    if (variableNames == null)
      throw new IllegalArgumentException("Variable names array cannot be null");
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");
    if (values.length != variableNames.length)
      throw new IllegalArgumentException("Array length mismatch");

    this.variableNames = variableNames.clone();
    this.values = values.clone();
  }

  public Object getValueFromArgumentName(String string) {

    for(int k = 0; k < values.length; k++) {
      if (variableNames[k].equals(string))
        return values[k];
    }

    return null;
  }

  public Object[] getValuesFromArgumentList(String[] array) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    int top = 0;
    Object[] ret = new Object[array.length];
    Object o;

    for(String string : array) {
      o = getValueFromArgumentName(string);
      if (o == null)
        throw new IllegalArgumentException(String.format("Cannot find getValue for variable '%s'", string));

      ret[top++] = o;
    }

    return ret;
  }


}
