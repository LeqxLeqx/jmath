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
 * Author:    LeqxLeqx
 */
public abstract class IMatrix extends MObject{

  IMatrix() {
    super(Type.MATRIX);
  }

  public abstract double get(int row, int column);
  public abstract IMatrix set(int row, int column, double value);
  public abstract int numberOfRows();
  public abstract int numberOfColumns();

  public abstract IVector[] toColumnVectors();
  public abstract IVector[] toRowVectors();


  public abstract IVector transform(IVector vector);


  public abstract Matrix toMatrix();
  public abstract NMatrix toNMatrix();


  // Unary operations

  public abstract IMatrix transpose();
  public abstract IMatrix negative();

  public abstract double determinant();

  // Binary Operations

  public abstract IMatrix scale(double s);
  public abstract IMatrix subtract(IMatrix m);
  public abstract IMatrix subtract(IMatrix... matrices);
  public abstract IMatrix sub(IMatrix m);
  public abstract IMatrix sub(IMatrix... matrices);

  public abstract IMatrix multiply(IMatrix other);


  public abstract boolean equals(IMatrix matrix);

}
