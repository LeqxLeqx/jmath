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

import jmath.types.*;

/**
 * Author:    LeqxLeqx
 */
public enum ValueType{

  REAL,
  COMPLEX,
  VECTOR,
  MATRIX,

  ;

  public static ValueType getValueType(Object value) {
    if (value instanceof Double)
      return ValueType.REAL;
    else if (value instanceof Complex)
      return ValueType.COMPLEX;
    else if (value instanceof IMatrix)
      return ValueType.MATRIX;
    else if (value instanceof IVector)
      return ValueType.VECTOR;
    else
      throw new IllegalArgumentException("Cannot get value type of type: " + value.getClass().getName());
  }

  public static ValueType getValueType(String value) {

    try {
      Complex complex = Complex.parse(value);
      if (complex.isReal())
        return REAL;
      else
        return COMPLEX;
    } catch (IllegalArgumentException e) {}

    try {
      NVector.parse(value);
      return VECTOR;
    } catch (IllegalArgumentException e) {}

    try {
      NMatrix.parse(value);
      return MATRIX;
    } catch (IllegalArgumentException e) {}

    return null;
  }

}
