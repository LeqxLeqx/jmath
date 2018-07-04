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

import jmath.JMath;
import upsilon.tools.ArrayTools;

/**
 * Class for performing vector mathematics
 * in N dimensions. An object of this
 * class represents as single vector
 */
public class NVector extends IVector {


  public static NVector zero(int dim) {
    if (dim <= 0)
      throw new IllegalArgumentException("Cannot get zero vector of non-positive dimension");

    double[] ret = new double[dim];
    return new NVector(ret);
  }


  public static NVector unitVector(int k, int dim) {
    if (k < 0)
      throw new IllegalArgumentException("Cannot get unit vector in negative dimension");
    else if (dim <= 0)
      throw new IllegalArgumentException("Cannot get unit vector of non-positive dimension");
    else if (k >= dim)
      throw new IllegalArgumentException("Unit vector out of dimension");

    double[] ret = new double[dim];
    ret[k] = 1;

    return new NVector(ret);
  }

  public static NVector[] unitVectors(int dim) {
    if (dim <= 0)
      throw new IllegalArgumentException("Cannot get unit vector array of non-positive dimension");

    NVector[] ret = new NVector[dim];
    for(int k = 0; k < dim; k++) {
      ret[k] = unitVector(k, dim);
    }
    return ret;
  }

  public static NVector parse(String string) {

    if (string == null)
      throw new IllegalArgumentException("Cannot parse null string as a n-vector");

    String[] split = string.split("\\,");
    double[] ret;

    if (!split[0].startsWith("[") || !split[split.length - 1].endsWith("]"))
      throw new IllegalArgumentException(String.format("Cannot parse '%s' as a n-vector", string));

    split[0] = split[0].substring(1);
    split[split.length - 1] = split[split.length - 1].substring(0, split[split.length - 1].length() - 1);

    ret = new double[split.length];

    try {
      for(int k = 0; k < split.length; k++) {
        ret[k] = Double.parseDouble(split[k]);
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(String.format("Cannot parse '%s' as a n-vector", string));
    }

    return new NVector(ret);
  }

  public static NVector fullOf(double r, int dim) {
    if (dim < 0)
      throw new IllegalArgumentException("Cannot get vector of negative dimension");

    double[] ret = new double[dim];
    for(int k = 0; k < ret.length; k++) {
      ret[k] = r;
    }

    return new NVector(ret);
  }




  /* Add operations */

  public static NVector add(IVector a, IVector b) {
    if (a == null || b == null)
      throw new IllegalArgumentException("Vector addition cannot operate on null values");
    if (a.dimension() != a.dimension())
      throw new IllegalArgumentException("Mismatching vector dimensions");

    double[] ret = new double[a.dimension()];

    for(int k = 0; k < a.dimension(); k++) {
      ret[k] = a.get(k) + b.get(k);
    }

    return new NVector(ret);
  }
  public static NVector add(IVector... vectors) {
    checkArray(vectors);

    double[] ret = new double[vectors[0].dimension()];

		for (IVector vector : vectors) {
			for (int i = 0; i < vector.dimension(); i++) {
				ret[i] += vector.get(i);
			}
		}

    return new NVector(ret);
  }



  /* Inner product operation */

  public static double dot(IVector a, IVector b) {
    if (a == null || b == null)
      throw new IllegalArgumentException("Vector inner product cannot operate on null values");
    if (a.dimension() != a.dimension())
      throw new IllegalArgumentException("Mismatching vector dimensions");

    double ret = 0;

    for (int k = 0; k < a.dimension(); k++) {
      ret += a.get(k) * b.get(k);
    }

    return ret;
  }

  public static double dot(IVector... vectors) {
    checkArray(vectors);

    double ret = 0;

		for (IVector vector : vectors) {
			double subRet = 1;
			for (int i = 0; i < vector.dimension(); i++) {
				subRet *= vector.get(i);
			}
			ret += subRet;
		}

    return ret;
  }

  private static void checkArray(IVector[] vectors) {
    if (vectors == null)
      throw new IllegalArgumentException("Vectors array cannot be null");
    if (ArrayTools.containsNull(vectors))
      throw new IllegalArgumentException("Vector array cannot contain nulls");
    if (vectors.length == 0)
      throw new IllegalArgumentException("Vector array must be of a positive length");
    IVector.checkDimensions(vectors);
  }

  private static void checkArray(IVector[] vectors, int dim) {
    if (vectors == null)
      throw new IllegalArgumentException("Vectors array cannot be null");
    if (ArrayTools.containsNull(vectors))
      throw new IllegalArgumentException("Vector array cannot contain nulls");
    if (vectors.length == 0)
      throw new IllegalArgumentException("Vector array must be of a positive length");
    IVector.checkDimensions(vectors, dim);
  }


  private static Object applyVectorScalarMultiplication(Object a, Object b) {
    if (a instanceof Double && b instanceof Double)
      return (Double) a * (Double) b;
    else if (a instanceof Double && b instanceof IVector)
      return ((IVector) b).scale((Double) a);
    else if (a instanceof IVector && b instanceof Double)
      return ((IVector) a).scale((Double) b);
    else
      throw new RuntimeException();
  }
  private static Object applyVectorScalarAddition(Object a, Object b) {
    if (a instanceof Double && b instanceof Double)
      return (Double) a + (Double) b;
    else if (a instanceof IVector && b instanceof IVector)
      return NVector.add((IVector) a, (IVector) b);
    else
      throw new RuntimeException();
  }
  private static Object applyVectorScalarNegation(Object a) {
    if (a instanceof Double)
      return -(Double) a;
    else if (a instanceof IVector)
      return ((IVector) a).negative();
    else
      throw new RuntimeException();
  }


  private static IVector determinantOfVectorScalarArray(int dim, int fdim,  Object[] objects) {
    if (dim == 2) {
      return (IVector) applyVectorScalarAddition(
              applyVectorScalarMultiplication(objects[0], objects[3]),
              applyVectorScalarNegation(applyVectorScalarMultiplication(objects[1], objects[2]))
        );
    }

    Object ret = null;

    for(int k = 0; k < dim; k++) {
      Object subRet = applyVectorScalarMultiplication(
              objects[k],
              determinantOfVectorScalarArray(dim - 1, fdim, minorOfVectorScalarArray(dim, 0, k, objects))
        );
      if (ret == null) {
        if (subRet instanceof Double)
          ret = new Double(0);
        else if (subRet instanceof IVector)
          ret = zero(fdim);
        else
          throw new RuntimeException();
      }

      if (k % 2 != 0)
        subRet = applyVectorScalarNegation(subRet);

      ret = applyVectorScalarAddition(ret, subRet);
    }

    return (IVector) ret;
  }
  private static Object[] minorOfVectorScalarArray(int dim, int row, int column, Object[] objects) {

    int rowTop = 0, columnTop;
    Object[] ret = new Object[ (dim - 1) * (dim -1) ];

    for(int r = 0; r < dim; r++) {
      if (r == row) continue;

      columnTop = 0;
      for(int c = 0; c < dim; c++) {

        if (c == column) continue;

        ret[ columnTop + rowTop * (dim - 1) ] = objects[c + r * dim];

        columnTop++;
      }
      rowTop++;
    }

    return ret;
  }




  private final double[] values;

  public NVector(double... array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    this.values = array.clone();
  }

  public NVector(Vector vector) {
    if (vector == null)
      throw new IllegalArgumentException("Vector object cannot be null");

    values = new double[3];
    values[0] = vector.x;
    values[1] = vector.y;
    values[2] = vector.z;
  }



  @Override
  public int dimension() {
    return values.length;
  }

  @Override
  public double get(int k) {
    if (k < 0)
      throw new IllegalArgumentException("Cannot get negative vector getValue");
    else if (k >= values.length)
      throw new IllegalArgumentException(String.format("Cannot access vector (dimension %dependent) index %dependent", dimension(), k));

    return values[k];
  }

  @Override
  public double getX() {
    return get(0);
  }

  @Override
  public double getY() {
    return get(1);
  }

  @Override
  public double getZ() {
    return get(2);
  }

  @Override
  public double[] toArray() {
    return values.clone();
  }


  /* Unary Methods */

  @Override
  public NVector negative() {
    double[] ret = values.clone();
    for(int k = 0; k < ret.length; k++) {
      ret[k] *= -1;
    }
    return new NVector(ret);
  }

  @Override
  public NVector normalize() {
    double[] ret = values.clone();
    double abs = absoluteValue();
    for(int k = 0; k < ret.length; k++) {
      ret[k] /= abs;
    }
    return new NVector(ret);
  }

  @Override
  public double magnitude() {
    return absoluteValue();
  }

  @Override
  public double absoluteValue() {
    return JMath.sqrt(squareAbsoluteValue());
  }

  @Override
  public double squareAbsoluteValue() {
    double ret = 0;
    for(double d : values) {
      ret += d * d;
    }
    return ret;
  }

  /* Scale operat ion */

	@Override
  public NVector scale(double s) {
    double[] ret = values.clone();
    for(int k = 0; k < ret.length; k++) {
      ret[k] *= s;
    }
    return new NVector(ret); 
  }



  /* Subtraction methods */

	@Override
  public NVector subtract(IVector v) { /* Alias of sub(Vector v) */
    return sub(v);
  }
	@Override
  public NVector subtract(IVector... vectors) { /* Alias of sub(Vector... vectors) */
    return sub(vectors);
  }

	@Override
  public NVector sub(IVector v) {
    if (v == null)
      throw new IllegalArgumentException("Vector addition cannot operate on null values");

    return add(this, v.negative());
  }
	@Override
  public NVector sub(IVector... vectors) {
    checkArray(vectors, dimension());

    NVector ret = this;
    for(IVector v : vectors) {
      ret = ret.sub(v);
    }

    return ret;
  }


  /* Cross multiplication */

  @Override
  public NVector cross(IVector a) {
    return cross(new IVector[] { a });
  }

  @Override
  public IVector proj(IVector vector) {
    if (vector == null)
      throw new IllegalArgumentException("Vector projection cannot operate on null values");
    if (vector.dimension() != dimension())
      throw new IllegalArgumentException("Dimensional mismatch for projection operation");

    return vector.normalize().scale(sproj(vector));
  }

  @Override
  public IVector projection(IVector vector) {
    if (vector == null)
      throw new IllegalArgumentException("Vector projection cannot operate on null values");
    if (vector.dimension() != dimension())
      throw new IllegalArgumentException("Dimensional mismatch for projection operation");

    return proj(vector);
  }

  @Override
  public double sproj(IVector vector) {
    if (vector == null)
      throw new IllegalArgumentException("Vector projection cannot operate on null values");
    if (vector.dimension() != dimension())
      throw new IllegalArgumentException("Dimensional mismatch for projection operation");

    return dot(this, vector.normalize());
  }

  @Override
  public double scalarProjection(IVector vector) {
    if (vector == null)
      throw new IllegalArgumentException("Vector projection cannot operate on null values");
    if (vector.dimension() != dimension())
      throw new IllegalArgumentException("Dimensional mismatch for projection operation");

    return sproj(vector);
  }


  public NVector cross(IVector... vectors) {
    checkArray(vectors, dimension());
    if (vectors.length != dimension() - 2)
      throw new IllegalArgumentException("Dimensional mismatch for outer product operation");

    Object[] array = new Object[dimension() * dimension()];
    for(int k = 0; k < dimension(); k++) {
      array[k] = get(k);
    }

    int top = dimension();
    for(IVector vector : vectors) {
      for(double d : vector.toArray()) {
        array[top++] = d;
      }
    }

    for(NVector unit : NVector.unitVectors(dimension())) {
      array[top++] = unit;
    }

    IVector vector = determinantOfVectorScalarArray(dimension(), dimension(), array);

    return vector.toNVector();
  }


  @Override
  public boolean equals(Object o) {
    if (o instanceof IVector)
      return equals((IVector) o);
    else
      return false;
  }

	@Override
	public int hashCode() {
		long value = 1;
		for (int k = 0; k < values.length; k++) {
			value *= Double.doubleToLongBits(values[k]);
		}
		
		return (int) value;
	}

  @Override
  public boolean equals(IVector vector) {
    if (vector == null)
      return false;
    else if (vector.dimension() != this.dimension())
      return false;
    for(int k = 0; k < dimension(); k++) {
      if (get(k) != vector.get(k))
        return false;
    }

    return true;
  }

  @Override
  public NVector toNVector() {
    return this;
  }

  @Override
  public Vector toVector() {
    return new Vector(this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("[");
    sb.append(String.format("%s", getX()));
    for(int k = 1; k < dimension(); k++) {
      sb.append(String.format(",%s", get(k)));
    }
    sb.append("]");

    return sb.toString();
  }

}
