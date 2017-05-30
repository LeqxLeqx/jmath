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

package jmath.function.parsing;

import jmath.function.Evaluable;
import jmath.function.ValueType;
import jmath.types.Complex;
import jmath.types.IMatrix;
import jmath.types.IVector;

class Element {


  final Object value;
  final ElementType type;

  public Element(Object val, ElementType type) {
    this.value = val;
    this.type = type;
  }
  public Element(ElementType type) {
    this.value = null;
    this.type = type;
  }

  public ValueType getLiteralType() {
    if (type != ElementType.LITERAL)
      throw new RuntimeException();
    else {

      if (value instanceof Double)
        return ValueType.REAL;
      else if (value instanceof Complex)
        return ValueType.COMPLEX;
      else if (value instanceof IVector)
        return ValueType.VECTOR;
      else if (value instanceof IMatrix)
        return ValueType.MATRIX;
      else
        throw new RuntimeException();
    }
  }


  public String asString() {
    return (String) value;
  }
  public double asDouble() {
    return (Double) value;
  }
  public Complex asComplex() {
    return (Complex) value;
  }
  public IVector asVector() {
    return (IVector) value;
  }
  public IMatrix asMatrix() {
    return (IMatrix) value;
  }
  public Evaluable asEvaluable() {
    return (Evaluable) value;
  }

  @Override
  public String toString() {
    if (value == null)
      return String.format("%s", type);
    else
      return String.format("%s:%s", type, value);
  }


}
