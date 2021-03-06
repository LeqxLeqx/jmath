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
 * in three dimensions. An object of this
 * class represents as single vector of three
 * dimensions.
 */
public class Vector extends IVector{

  public static Vector
          ZERO = new Vector(),
          I_HAT = new Vector(1, 0, 0),
          J_HAT = new Vector(0, 1, 0),
          K_HAT = new Vector(0, 0, 1)
                  ;


  public static Vector parse(String string) {

    if (string == null)
      throw new IllegalArgumentException("Cannot parse null string as a vector");

    String[] split = string.split("\\,");
    String xString, yString, zString;
    double x, y, z;


    if (split.length != 3 || !split[0].startsWith("[") || !split[2].endsWith("]"))
      throw new IllegalArgumentException(String.format("Cannot parse '%s' as a vector", string));

    xString = split[0].substring(1);
    yString = split[1];
    zString = split[2].substring(0, split[2].length() - 1);


    try {
      x = Double.parseDouble(xString);
      y = Double.parseDouble(yString);
      z = Double.parseDouble(zString);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(String.format("Cannot parse '%s' as a vector", string));
    }

    return new Vector(x, y, z);
  }


  /* Addition operation */


  public static Vector add(Vector a, Vector b) {
    if (a == null || b == null)
      throw new IllegalArgumentException("Vector addition cannot operate on null values");
    return new Vector(
            a.x + b.x,
            a.y + b.y,
            a.z + b.z
      );
  }
  public static Vector add(Vector... vectors) {
    if (vectors == null)
      throw new IllegalArgumentException("Vectors array cannot be null");
    if (ArrayTools.containsNull(vectors))
      throw new IllegalArgumentException("Vector array cannot contain nulls");

    double x = 0, y = 0, z = 0;

    for(Vector v : vectors) {
      x += v.x;
      y += v.y;
      z += v.z;
    }

    return new Vector(x, y, z);
  }



  /* Inner product operation */

  public static double dot(Vector a, Vector b) {
    if (a == null || b == null)
        throw new IllegalArgumentException("Vectors array cannot be null");
    return
            a.x * b.x +
            a.y * b.y +
            a.z * b.z;
  }
  public static double dot(Vector... vectors) {
    if (vectors == null)
      throw new IllegalArgumentException("Vectors array cannot be null");
    if (ArrayTools.containsNull(vectors))
      throw new IllegalArgumentException("Vector array cannot contain nulls");
    if (vectors.length == 0)
      throw new IllegalArgumentException("Vector array must be of a positive length");

    double x = 1, y = 1, z = 1;

    for(Vector v : vectors) {
      x *= v.x;
      y *= v.y;
      z *= v.z;
    }

    return x + y + z;
  }





  public final double x, y, z;

  public Vector(
          double x,
          double y,
          double z
  ) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Vector(
          double x,
          double y
  ) {
    this.x = x;
    this.y = y;
    this.z = 0;
  }

  public Vector() {
    this.x = 0; /* Technically redundant */
    this.y = 0;
    this.z = 0;
  }

  public Vector(NVector nvector) {
    if (nvector == null)
      throw new IllegalArgumentException("NVector object cannot be null");
    else if (nvector.dimension() != 3)
      throw new IllegalArgumentException("Cannot create Vector object from NVector of dimension: " + nvector.dimension());

    this.x = nvector.getX();
    this.y = nvector.getY();
    this.z = nvector.getZ();
  }


  /* Unary operations */

  @Override
  public Vector negative() {
    return new Vector(-x, -y, -z);
  }

  @Override
  public Vector normalize() {
    if (x == 0 && y == 0 && z == 0)
      return Vector.ZERO;
    else {
      double absoluteValue = absoluteValue();
      return new Vector(
              x / absoluteValue,
              y / absoluteValue,
              z / absoluteValue
      );

    }
  }

  @Override
  public double magnitude() { /* Alias of absoluteValue */
    return absoluteValue();
  }

  @Override
  public double absoluteValue() {
    return JMath.sqrt(squareAbsoluteValue());
  }

  @Override
  public double squareAbsoluteValue() {
    return x * x + y * y + z * z;
  }

  public Vector round() {
    return new Vector(JMath.round(x), JMath.round(y), JMath.round(z));
  }

  public Vector round(double precision) {
    return new Vector(
            JMath.round(x / precision) * precision,
            JMath.round(y / precision) * precision,
            JMath.round(z / precision) * precision
      );
  }


  /* Scale */

  @Override
  public Vector scale(double s) {
    return new Vector(
            x * s,
            y * s,
            z * s
      );
  }

  /* Subtraction methods */

  @Override
  public IVector sub(IVector vector) {
    if (vector == null)
      throw new IllegalArgumentException("Vector subtraction cannot operate on null values");

    return sub(vector.toVector());
  }

  @Override
  public IVector sub(IVector... ivectors) {
    if (ivectors == null)
      throw new IllegalArgumentException("Vector array cannot be null");
    if (ArrayTools.containsNull(ivectors))
      throw new IllegalArgumentException("Vector array cannot contain nulls");

    Vector[] vectors = new Vector[ivectors.length];
    for(int k = 0; k < vectors.length; k++) {
      vectors[k] = ivectors[k].toVector();
    }

    return sub(vectors);
  }

  @Override
  public IVector subtract(IVector vector) {
    return sub(vector);
  }

  @Override
  public IVector subtract(IVector... vectors) {
    return sub(vectors);
  }

  public Vector subtract(Vector v) { /* Alias of sub(Vector v) */
    return sub(v);
  }
  public Vector subtract(Vector... vectors) { /* Alias of sub(Vector... vectors) */
    return sub(vectors);
  }
  public Vector sub(Vector v) {
    if (v == null)
      throw new IllegalArgumentException("Vector subtraction cannot operate on null values");

    return new Vector(
            x - v.x,
            y - v.y,
            z - v.z
      );
  }
  public Vector sub(Vector... vectors) {
    if (vectors == null)
      throw new IllegalArgumentException("Vector array cannot be null");
    if (ArrayTools.containsNull(vectors))
      throw new IllegalArgumentException("Vector array cannot contain null");

    double vx = this.x, vy = this.y, vz = this.z;

    for(Vector v : vectors) {
      vx -= v.x;
      vy -= v.y;
      vz -= v.z;
    }

    return new Vector(vx, vy, vz);
  }


  /* Cross multiplication */

  @Override
  public IVector cross(IVector a) {
    if (a == null)
      throw new IllegalArgumentException("Vector outer product cannot operate on null values");

    return cross(a.toVector());
  }

  @Override
  public Vector proj(IVector vector) {
    if (vector == null)
      throw new IllegalArgumentException("Vector projection cannot operate on null values");

    return proj(vector.toVector());
  }

  @Override
  public Vector projection(IVector vector) {
    if (vector == null)
      throw new IllegalArgumentException("Vector projection cannot operate on null values");

    return proj(vector.toVector());
  }

  @Override
  public double sproj(IVector vector) {
    if (vector == null)
      throw new IllegalArgumentException("Vector projection cannot operate on null values");

    return sproj(vector.toVector());
  }

  @Override
  public double scalarProjection(IVector vector) {
    if (vector == null)
      throw new IllegalArgumentException("Vector projection cannot operate on null values");

    return sproj(vector.toVector());
  }

  public Vector proj(Vector vector) {
    if (vector == null)
      throw new IllegalArgumentException("Vector projection cannot operate on null values");

    return vector.normalize().scale(sproj(vector));
  }

  public Vector projection(Vector vector) {
    if (vector == null)
      throw new IllegalArgumentException("Vector projection cannot operate on null values");

    return proj(vector);
  }

  public double sproj(Vector vector) {
    if (vector == null)
      throw new IllegalArgumentException("Vector cannot be null");

    return dot(this, vector.normalize());
  }

  public double scalarProjection(Vector vector) {
    if (vector == null)
      throw new IllegalArgumentException("Vector projection cannot operate on null values");

    return sproj(vector);
  }


  public Vector cross(Vector v) {
    if (v == null)
      throw new IllegalArgumentException("Vector outer product cannot operate on null values");

    return new Vector(
            y * v.z - z * v.y,
            z * v.x - x * v.z,
            x * v.y - y * v.x
      );
  }



  @Override
  public String toString() {
    return String.format("[%s,%s,%s]", x, y, z);
  }

  public String toString2() {
    return String.format("[%s,%s]", x, y);
  }

  public String toStringInt() {
    return String.format("[%d,%d,%d]", (int) x, (int) y, (int) z);
  }

  public String toStringInt2() {
    return String.format("[%d,%d]", (int) x, (int) y);
  }


  @Override
  public boolean equals(Object o) {
    if (o instanceof Vector)
      return equals((Vector) o);
    else
      return false;
  }

  @Override
  public boolean equals(IVector o) {
    if (o == null)
      return false;
    else if (o instanceof Vector || o.dimension() == 3)
      return equals(o.toVector());
    else
      return false;
  }

  public boolean equals(Vector v) {
    if (v == null)
      return false;
    return v.x == x && v.y == y && v.z == z;
  }

  @Override
  public int hashCode() {
    return (int) (Double.doubleToLongBits(x) * Double.doubleToLongBits(y) + Double.doubleToLongBits(z));
  }

  @Override
  public int dimension() {
    return 3;
  }

  @Override
  public double get(int k) {
    switch(k) {
      case 0:
        return x;
      case 1:
        return y;
      case 2:
        return z;
      default:
        throw new IllegalArgumentException(String.format("Cannot access vector (dimension %dependent) index %dependent", dimension(), k));
    }
  }

  @Override
  public double getX() {
    return x;
  }

  @Override
  public double getY() {
    return y;
  }

  @Override
  public double getZ() {
    return z;
  }

  @Override
  public double[] toArray() {
    return new double[] { x, y, z };
  }

  @Override
  public Vector toVector() {
    return this;
  }

  @Override
  public NVector toNVector() {
    return new NVector(this);
  }

}
