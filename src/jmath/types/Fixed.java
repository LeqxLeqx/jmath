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
import jmath.tools.ArrayTools;

/**
 * Author:    LeqxLeqx
 */
public class Fixed extends MObject {


  public static final Fixed ZERO = new Fixed(0.0);
  public static final Fixed ONE = new Fixed(1.0);

  private static final long PRECISION = 10000;


  public static Fixed add(Fixed a, Fixed b) {
    if (a == null || b == null)
      throw new IllegalArgumentException("Cannot add a null getValue");
    return new Fixed(a.value + b.value);
  }

  public static Fixed add(Fixed... fixed) {
    checkArray(fixed);

    Fixed ret = ZERO;

    for (Fixed f : fixed) {
      ret = add(ret, f);
    }

    return ret;
  }

  public static Fixed multiply(Fixed a, Fixed b) { // Alias
    return mul(a, b);
  }

  public static Fixed multiply(Fixed... fixed) { // Alias
    return mul(fixed);
  }

  public static Fixed mul(Fixed a, Fixed b) {
    if (a == null || b == null)
      throw new IllegalArgumentException("Cannot multiply a null getValue");
    return new Fixed((a.value * b.value) / PRECISION);
  }

  public static Fixed mul(Fixed... fixed) {
    checkArray(fixed);

    Fixed ret = ONE;

    for (Fixed f : fixed) {
      ret = mul(ret, f);
    }

    return ret;
  }


  private static void checkArray(Fixed[] array) {
    if (array == null)
      throw new IllegalArgumentException("Fixed getValue array cannot be null");
    if (array.length == 0)
      throw new IllegalArgumentException("Fixed getValue array must have a positive length");
    if (ArrayTools.containsNull(array))
      throw new IllegalArgumentException("Fixed getValue array may not contain nulls");
  }


  private final long value;

  private Fixed(long value) {
    super(Type.FIXED_POINT_NUMBER);

    this.value = value;
  }

  public Fixed(int value) {
    super(Type.FIXED_POINT_NUMBER);

    this.value = value * PRECISION;

  }

  public Fixed(double value) {
    super(Type.FIXED_POINT_NUMBER);

    this.value = (long) (value * PRECISION);
  }


  public boolean isNegative() {
    return value < 0;
  }

  public boolean isPositive() {
    return value > 0;
  }

  public boolean isZero() {
    return value == 0;
  }

  public boolean isInteger() {
    return value % PRECISION == 0;
  }


  public Fixed negative() {
    return new Fixed(-value);
  }

  public Fixed absoluteValue() {
    return abs();
  }

  public Fixed abs() {
    return isNegative() ? new Fixed(-value) : this;
  }

  public Fixed round() {

    long r = JMath.abs(value % PRECISION);

    if (r == 0)
      return this;
    else if (r == PRECISION / 2) {
      if (value > 0)
        return new Fixed(value + r);
      else
        return new Fixed(value + r);
    }
    else if (r > PRECISION / 2) {
      if (value > 0)
        return new Fixed(value + PRECISION - r);
      else
        return new Fixed(value + r - PRECISION);
    }
    else { // r < PRECISION / 2
      if (value > 0)
        return new Fixed(value - r);
      else
        return new Fixed(value + r);
    }
  }




  public Fixed subtract(Fixed fixed) { // alias
    return sub(fixed);
  }

  public Fixed subtract(Fixed... fixed) { // alias
    return sub(fixed);
  }

  public Fixed sub(Fixed fixed) {
    if (fixed == null)
      throw new IllegalArgumentException("Cannot subtract a null getValue");

    return new Fixed(value - fixed.value);
  }

  public Fixed sub(Fixed... fixed) {
    checkArray(fixed);

    Fixed ret = this;

    for (Fixed f : fixed) {
      ret = ret.sub(f);
    }

    return ret;
  }

  public Fixed divide(Fixed fixed) { // alias
    return div(fixed);
  }

  public Fixed divide(Fixed... fixed) { // alias
    return div(fixed);
  }

  public Fixed div(Fixed fixed) {
    if (fixed == null)
      throw new IllegalArgumentException("Cannot divide a null getValue");
    if (fixed.value == 0)
      throw new ArithmeticException("Cannot divide zero");

    return new Fixed(PRECISION * value / fixed.value);
  }

  public Fixed div(Fixed... fixed) {
    checkArray(fixed);

    Fixed ret = this;

    for (Fixed f : fixed) {
      ret = div(ret, f);
    }

    return ret;
  }

  public Fixed modulo(double d) {
    return mod(d);
  }

  public Fixed modulo(Fixed fixed) {
    return mod(fixed);
  }
  public Fixed mod(double d) {
    return mod(new Fixed(d));
  }
  public Fixed mod(Fixed fixed) {
    return new Fixed(value % fixed.value);
  }


  public Fixed pow(Fixed f) {
    return power(f);
  }
  public Fixed pow(double d) {
    return power(d);
  }
  public Fixed power(double d) {
    return power(new Fixed(d));
  }
  public Fixed power(Fixed f) {
    if (f == null)
      throw new IllegalArgumentException("Cannot operate on null getValue");
    if (f.value < 0)
      return ONE.div(power(f.negative()));
    if (f.value == 0)
      return ONE;

    long ret = value, p;

    for(p = f.value - ONE.value; p >= ONE.value;p -= ONE.value) {
      ret *= value;
      ret /= PRECISION;
    }

    if (p != 0)
      ret *= JMath.pow((double) value / PRECISION, (double) p / PRECISION);

    return new Fixed(ret);
  }


  public Fixed squared() {
    return mul(this, this);
  }

  public Fixed cubed() {
    return mul(this, this, this);
  }




  public double toDouble() {
    return (double) value / PRECISION;
  }

  public float toFloat() {
    return (float) toDouble();
  }

  public long toLong() {
    return value / PRECISION;
  }

  public float toInt() {
    return (int) toLong();
  }

  @Override
  public boolean equals(Object o) {
    if ( o instanceof Fixed)
      return equals((Fixed) o);
    else
      return false;
  }

  public boolean equals(Fixed o) {
    if (o == null)
      return false;

    return o.value == value;
  }

  public boolean isGreater(Fixed f) {
    if (f == null)
      return true;

    return value > f.value;
  }
  public boolean isLesser(Fixed f) {
    if (f == null)
      return false;

    return value < f.value;
  }

  public boolean isGreaterOrEqual(Fixed f) {
    return isGreater(f) || equals(f);
  }
  public boolean isLesserOrEqual(Fixed f) {
    return isLesser(f) || equals(f);
  }

  @Override
  public String toString() {
    if (isInteger())
      return String.format("%s", toLong());
    else
      return String.format("%s", toDouble());
  }

}
