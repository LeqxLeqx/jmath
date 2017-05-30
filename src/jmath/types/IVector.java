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

package jmath.types;

/**
 * Vector class from which both
 * Vector and NVector inherit.
 */
public abstract class IVector extends MObject {


  static void checkDimensions(IVector[] vectors, int dim) {
    for(int k = 0; k < vectors.length; k++) {
      if (vectors[k].dimension() != dim)
        throw new IllegalArgumentException("Vector array elements must have consistent dimensions");
    }
  }

  static void checkDimensions(IVector[] vectors) {
    int dim = vectors[0].dimension();
    for(int k = 1; k < vectors.length; k++) {
      if (vectors[k].dimension() != dim)
        throw new IllegalArgumentException("Vector array elements must have consistent dimensions");
    }
  }

  IVector() {
    super(Type.VECTOR);
  }

  public abstract int dimension();

  public abstract double get(int k);

  public abstract double getX();
  public abstract double getY();
  public abstract double getZ();


  public abstract double[] toArray();


  /* Unary operations */

  public abstract IVector negative();
  public abstract IVector normalize();
  public abstract double magnitude();
  public abstract double absoluteValue();
  public abstract double squareAbsoluteValue();

  public abstract boolean equals(IVector vector);

  /* Binary operations */

  public abstract IVector scale(double s);
  public abstract IVector sub(IVector vector);
  public abstract IVector sub(IVector... vectors);
  public abstract IVector subtract(IVector vector);
  public abstract IVector subtract(IVector... vectors);

  public abstract IVector cross(IVector a);



  public abstract NVector toNVector();
  public abstract Vector toVector();

}
