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

package jmath;


import jmath.function.*;
import jmath.types.*;

import java.util.Arrays;

/**
 * A static class for various mathematical operations
 */
public /*static*/ class JMath { private JMath() {}

  public static final double
          PI = java.lang.Math.PI,
          PI2 = PI * 2,
          E = java.lang.Math.E
                  ;
  public static final Complex
          COMPLEX_PI = Complex.real(PI),
          COMPLEX_PI2 = Complex.real(PI2),
          COMPLEX_E = Complex.real(E)
          ;

  public static final Fixed
          FIXED_PI = new Fixed(PI),
          FIXED_PI2 = new Fixed(PI2),
          FIXED_E = new Fixed(E)
          ;



  /* Absolute getValue functions */

  /**
   * Returns the absolute value of the lone argument 'a'
   *
   * @param a the value to which the function will be applied
   * @return the absolute value of 'a'
   */
  public static double absoluteValue(double a) {
    return abs(a);
  }

  /**
   * Returns the absolute value of the lone argument 'a'
   *
   * @param a the value to which the function will be applied
   * @return the absolute value of 'a'
   */
  public static float absoluteValue(float a) {
    return abs(a);
  }

  /**
   * Returns the absolute value of the lone argument 'a'
   *
   * @param a the value to which the function will be applied
   * @return the absolute value of 'a'
   */
  public static long absoluteValue(long a) {
    return abs(a);
  }

  /**
   * Returns the absolute value of the lone argument 'a'
   *
   * @param a the value to which the function will be applied
   * @return the absolute value of 'a'
   */
  public static int absoluteValue(int a) {
    return abs(a);
  }

  /**
   * Returns the absolute value of the lone argument 'vector'
   *
   * @param vector the value to which the function will be applied
   * @return the absolute value of 'vector'
   */
  public static double absoluteValue(IVector vector) {
    return abs(vector);
  }

  /**
   * Returns the absolute value of the lone argument 'complex'
   *
   * @param complex the value to which the function will be applied
   * @return the absolute value of 'complex'
   */
  public static double absoluteValue(Complex complex) {
    return abs(complex);
  }


  /**
   * Returns the absolute value of the lone argument 'fixed'
   *
   * @param fixed the value to which the function will be applied
   * @return the absolute value of 'fixed'
   */
  public static Fixed absoluteValue(Fixed fixed) {
    return abs(fixed);
  }

  /**
   * Returns the absolute value of the lone argument 'a'
   *
   * @param a the value to which the function will be applied
   * @return the absolute value of 'a'
   */
  public static double abs(double a) {
    return java.lang.Math.abs(a);
  }

  /**
   * Returns the absolute value of the lone argument 'a'
   *
   * @param a the value to which the function will be applied
   * @return the absolute value of 'a'
   */
  public static float abs(float a) {
    return java.lang.Math.abs(a);
  }

  /**
   * Returns the absolute value of the lone argument 'a'
   *
   * @param a the value to which the function will be applied
   * @return the absolute value of 'a'
   */
  public static long abs(long a) {
    return java.lang.Math.abs(a);
  }

  /**
   * Returns the absolute value of the lone argument 'a'
   *
   * @param a the value to which the function will be applied
   * @return the absolute value of 'a'
   */
  public static int abs(int a) {
    return java.lang.Math.abs(a);
  }


  /**
   * Returns the absolute value of the lone argument 'vector'
   *
   * @param vector the value to which the function will be applied
   * @return the absolute value of 'vector'
   */
  public static double abs(IVector vector) {
    argCheck(vector);
    return vector.absoluteValue();
  }

  /**
   * Returns the absolute value of the lone argument 'complex'
   *
   * @param complex the value to which the function will be applied
   * @return the absolute value of 'complex'
   */
  public static double abs(Complex complex) {
    argCheck(complex);
    return complex.absoluteValue();
  }

  /**
   * Returns the absolute value of the lone argument 'fixed'
   *
   * @param fixed the value to which the function will be applied
   * @return the absolute value of 'fixed'
   */
  public static Fixed abs(Fixed fixed) {
    argCheck(fixed);
    return fixed.absoluteValue();
  }


  /* Min-max getValue functions */

  /**
   * Returns the maximum between the provided values
   *
   * @param a the first value to be compared
   * @param b the second value to be compared
   * @return the maximum value between values 'a' and 'b'
   */
  public static double max(double a, double b) {
    return java.lang.Math.max(a, b);
  }

  /**
   * Returns the maximum between the provided values
   *
   * @param a the first value to be compared
   * @param b the second value to be compared
   * @return the maximum value between values 'a' and 'b'
   */
  public static float max(float a, float b) {
    return java.lang.Math.max(a, b);
  }

  /**
   * Returns the maximum between the provided values
   *
   * @param a the first value to be compared
   * @param b the second value to be compared
   * @return the maximum value between values 'a' and 'b'
   */
  public static int max(int a, int b) {
    return java.lang.Math.max(a, b);
  }

  /**
   * Returns the maximum between the provided values
   *
   * @param a the first value to be compared
   * @param b the second value to be compared
   * @return the maximum value between values 'a' and 'b'
   */
  public static long max(long a, long b) {
    return java.lang.Math.max(a, b);
  }

  /**
   * Returns the maximum between the provided values
   *
   * @param a the first value to be compared
   * @param b the second value to be compared
   * @return the maximum value between values 'a' and 'b'
   */
  public static Fixed max(Fixed a, Fixed b) {
    argCheck(a);
    argCheck(b);
    if (a.isGreater(b))
      return a;
    else
      return b;
  }

  /**
   * Returns the minimum between the provided values
   *
   * @param a the first value to be compared
   * @param b the second value to be compared
   * @return the minimum value between values 'a' and 'b'
   */
  public static double min(double a, double b) {
    return java.lang.Math.min(a, b);
  }

  /**
   * Returns the minimum between the provided values
   *
   * @param a the first value to be compared
   * @param b the second value to be compared
   * @return the minimum value between values 'a' and 'b'
   */
  public static float min(float a, float b) {
    return java.lang.Math.min(a, b);
  }

  /**
   * Returns the minimum between the provided values
   *
   * @param a the first value to be compared
   * @param b the second value to be compared
   * @return the minimum value between values 'a' and 'b'
   */
  public static int min(int a, int b) {
    return java.lang.Math.min(a, b);
  }

  /**
   * Returns the minimum between the provided values
   *
   * @param a the first value to be compared
   * @param b the second value to be compared
   * @return the minimum value between values 'a' and 'b'
   */
  public static long min(long a, long b) {
    return java.lang.Math.min(a, b);
  }

  /**
   * Returns the minimum between the provided values
   *
   * @param a the first value to be compared
   * @param b the second value to be compared
   * @return the minimum value between values 'a' and 'b'
   */
  public static Fixed min(Fixed a, Fixed b) {
    argCheck(a);
    argCheck(b);
    if (a.isLesser(b))
      return a;
    else
      return b;
  }

  /**
   * Returns the maximum amongst the provided values
   *
   * @param values
   * @return the maximum amongst the provided values
   */
  public static double max(double... values) {
    if (values == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (values.length == 0)
      throw new IllegalArgumentException("Array must have a positive getValue");

    double m = values[0];

    for(double v : values) {
      m = max(v, m);
    }
    return m;
  }

  /**
   * Returns the maximum amongst the provided values
   *
   * @param values
   * @return the maximum amongst the provided values
   */
  public static float max(float... values) {
    if (values == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (values.length == 0)
      throw new IllegalArgumentException("Array must have a positive getValue");

    float m = values[0];

    for(float v : values) {
      m = max(v, m);
    }
    return m;
  }

  /**
   * Returns the maximum amongst the provided values
   *
   * @param values
   * @return the maximum amongst the provided values
   */
  public static int max(int... values) {
    if (values == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (values.length == 0)
      throw new IllegalArgumentException("Array must have a positive getValue");

    int m = values[0];

    for(int v : values) {
      m = max(v, m);
    }
    return m;
  }

  /**
   * Returns the maximum amongst the provided values
   *
   * @param values
   * @return the maximum amongst the provided values
   */
  public static long max(long... values) {
    if (values == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (values.length == 0)
      throw new IllegalArgumentException("Array must have a positive getValue");

    long m = values[0];

    for(long v : values) {
      m = max(v, m);
    }
    return m;
  }

  /**
   * Returns the maximum amongst the provided values
   *
   * @param values
   * @return the maximum amongst the provided values
   */
  public static Fixed max(Fixed... values) {
    if (values == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (values.length == 0)
      throw new IllegalArgumentException("Array must have a positive getValue");
    if (Arrays.asList(values).contains(null))
      throw new IllegalArgumentException("Array cannot contain nulls");

    Fixed m = values[0];

    for(Fixed v : values) {
      m = max(v, m);
    }

    return m;
  }



  /**
   * Returns the minimum amongst the provided values
   *
   * @param values
   * @return the minimum amongst the provided values
   */
  public static double min(double... values) {
    if (values == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (values.length == 0)
      throw new IllegalArgumentException("Array must have a positive getValue");

    double m = values[0];

    for(double v : values) {
      m = min(v, m);
    }
    return m;
  }

  /**
   * Returns the minimum amongst the provided values
   *
   * @param values
   * @return the minimum amongst the provided values
   */
  public static float min(float... values) {
    if (values == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (values.length == 0)
      throw new IllegalArgumentException("Array must have a positive getValue");

    float m = values[0];

    for(float v : values) {
      m = min(v, m);
    }
    return m;
  }

  /**
   * Returns the minimum amongst the provided values
   *
   * @param values
   * @return the minimum amongst the provided values
   */
  public static int min(int... values) {
    if (values == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (values.length == 0)
      throw new IllegalArgumentException("Array must have a positive getValue");

    int m = values[0];

    for(int v : values) {
      m = min(v, m);
    }
    return m;
  }

  /**
   * Returns the minimum amongst the provided values
   *
   * @param values
   * @return the minimum amongst the provided values
   */
  public static long min(long... values) {
    if (values == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (values.length == 0)
      throw new IllegalArgumentException("Array must have a positive getValue");

    long m = values[0];

    for(long v : values) {
      m = min(v, m);
    }
    return m;
  }

  /**
   * Returns the minimum amongst the provided values
   *
   * @param values
   * @return the minimum amongst the provided values
   */
  public static Fixed min(Fixed... values) {
    if (values == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (values.length == 0)
      throw new IllegalArgumentException("Array must have a positive getValue");
    if (Arrays.asList(values).contains(null))
      throw new IllegalArgumentException("Array cannot contain nulls");

    Fixed m = values[0];

    for(Fixed v : values) {
      m = min(v, m);
    }

    return m;
  }






  /* Inverse trigonometric functions */

  /**
   * Returns the arcosine or inverse-cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double arcosine(double a) {
    return acos(a);
  }

  /**
   * Returns the arcosine or inverse-cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex arcosine(Complex a) {
    return acos(a);
  }

  /**
   * Returns the arcosine or inverse-cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed arcosine(Fixed a) {
    return acos(a);
  }

  /**
   * Returns the arcsine or inverse-sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double arcsine(double a) {
    return asin(a);
  }

  /**
   * Returns the arcsine or inverse-sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex arcsine(Complex a) {
    return asin(a);
  }

  /**
   * Returns the arcsine or inverse-sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed arcsine(Fixed a) {
    return asin(a);
  }

  /**
   * Returns the arctangent or inverse-tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double arctangent(double a) {
    return atan(a);
  }

  /**
   * Returns the arctangent or inverse-tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex arctangent(Complex a) {
    return atan(a);
  }

  /**
   * Returns the arctangent or inverse-tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed arctangent(Fixed a) {
    return atan(a);
  }

  /**
   * Returns the arcotangent or inverse-cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double arccotangent(double a) {
    return acot(a);
  }

  /**
   * Returns the arcotangent or inverse-cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex arccotangent(Complex a) {
    return acot(a);
  }

  /**
   * Returns the arcotangent or inverse-cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed arccotangent(Fixed a) {
    return acot(a);
  }

  /**
   * Returns the arcosecant or inverse-cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double arcosecant(double a) {
    return acsc(a);
  }

  /**
   * Returns the arcosecant or inverse-cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex arcosecant(Complex a) {
    return acsc(a);
  }

  /**
   * Returns the arcosecant or inverse-cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed arcosecant(Fixed a) {
    return acsc(a);
  }

  /**
   * Returns the arcsecant or inverse-secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double arcsecant(double a) {
    return asec(a);
  }

  /**
   * Returns the arcsecant or inverse-secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex arcsecant(Complex a) {
    return asec(a);
  }

  /**
   * Returns the arcsecant or inverse-secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed arcsecant(Fixed a) {
    return asec(a);
  }



  /**
   * Returns the arcosine or inverse-cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double acos(double a) {
    return java.lang.Math.acos(a);
  }

  /**
   * Returns the arcosine or inverse-cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex acos(Complex a) {
    argCheck(a);
    return ln(
            Complex.add(
                    a,
                    Complex.multiply(
                      Complex.I,
                      Complex.polar(
                              sqrt(Complex.ONE.sub(a.squared()).absoluteValue()),
                              Complex.ONE.sub(a.squared()).rotation() / 2
                      )
                    )
            )
    ).div(Complex.I);
  }

  /**
   * Returns the arcosine or inverse-cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed acos(Fixed a) {
    argCheck(a);
    return new Fixed(acos(a.toDouble()));
  }

  /**
   * Returns the arcsine or inverse-sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double asin(double a) {
    return java.lang.Math.asin(a);
  }

  /**
   * Returns the arcsine or inverse-sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex asin(Complex a) {
    argCheck(a);
    return ln(
            Complex.add(
                    Complex.multiply(a, Complex.I),
                    Complex.polar(
                            sqrt(Complex.ONE.sub(a.squared()).absoluteValue()),
                            Complex.ONE.sub(a.squared()).rotation() / 2
                    )
            )
      ).div(Complex.I);
  }

  /**
   * Returns the arcsine or inverse-sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed asin(Fixed a) {
    argCheck(a);
    return new Fixed(asin(a.toDouble()));
  }

  /**
   * Returns the arctangent or inverse-tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double atan(double a) {
    return java.lang.Math.atan(a);
  }

  /**
   * Returns the arctangent or inverse-tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex atan(Complex a) {
    argCheck(a);
    return ln(Complex.I.sub(a).div(Complex.add(Complex.I, a))).div(Complex.imaginary(2));
  }

  /**
   * Returns the arctangent or inverse-tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed atan(Fixed a) {
    argCheck(a);
    return new Fixed(atan(a.toDouble()));
  }

  /**
   * Returns the arcotangent or inverse-cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double acot(double a) {
    return atan(1 / a);
  }

  /**
   * Returns the arcotangent or inverse-cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex acot(Complex a) {
    argCheck(a);
    return ln(Complex.add(a, Complex.I).div(a.sub(Complex.I))).div(Complex.imaginary(2));
  }

  /**
   * Returns the arcotangent or inverse-cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed acot(Fixed a) {
    argCheck(a);
    return new Fixed(acot(a.toDouble()));
  }

  /**
   * Returns the arccosecant or inverse-cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double acsc(double a) {
    return asin(1 / a);
  }

  /**
   * Returns the arccosecant or inverse-cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex acsc(Complex a) {
    argCheck(a);
    return asin(Complex.ONE.div(a));
  }

  /**
   * Returns the arccosecant or inverse-cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed acsc(Fixed a) {
    argCheck(a);
    return new Fixed(acsc(a.toDouble()));
  }

  /**
   * Returns the arcsecant or inverse-secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double asec(double a) {
    return acos(1 / a);
  }

  /**
   * Returns the arcsecant or inverse-secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex asec(Complex a) {
    argCheck(a);
    return acos(Complex.ONE.div(a));
  }

  /**
   * Returns the arcsecant or inverse-secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed asec(Fixed a) {
    argCheck(a);
    return new Fixed(asec(a.toDouble()));
  }

  /**
   * Returns the standard atan2 angle of the provided values
   *
   * @param y dependent coordinate
   * @param x independent coordinate
   * @return returns angle to (x,y) from (0,0)
   */
  public static double atan2(double y, double x) {
    return java.lang.Math.atan2(y, x);
  }

  /**
   * Returns the standard atan2 angle of the provided values
   *
   * @param y dependent coordinate
   * @param x independent coordinate
   * @return returns angle to (x,y) from (0,0)
   */
  public static Fixed atan2(Fixed y, Fixed x) {
    return new Fixed(atan2(y.toDouble(), x.toDouble()));
  }

  /* trigonometric functions */


  /**
   * Returns the cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double cosine(double a) {
    return cos(a);
  }

  /**
   * Returns the cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex cosine(Complex a) {
    return cos(a);
  }

  /**
   * Returns the cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed cosine(Fixed a) {
    return cos(a);
  }

  /**
   * Returns the sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double sine(double a) {
    return sin(a);
  }

  /**
   * Returns the sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex sine(Complex a) {
    return sin(a);
  }

  /**
   * Returns the sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed sine(Fixed a) {
    return sin(a);
  }

  /**
   * Returns the tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double tangent(double a) {
    return tan(a);
  }

  /**
   * Returns the tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex tangent(Complex a) {
    return tan(a);
  }

  /**
   * Returns the tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed tangent(Fixed a) {
    return tan(a);
  }

  /**
   * Returns the cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double cotangent(double a) {
    return cot(a);
  }

  /**
   * Returns the cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex cotangent(Complex a) {
    return cot(a);
  }

  /**
   * Returns the cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed cotangent(Fixed a) {
    return cot(a);
  }

  /**
   * Returns the cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double cosecant(double a) {
    return csc(a);
  }

  /**
   * Returns the cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex cosecant(Complex a) {
    return csc(a);
  }

  /**
   * Returns the cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed cosecant(Fixed a) {
    return csc(a);
  }

  /**
   * Returns the secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double secant(double a) {
    return sec(a);
  }

  /**
   * Returns the secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex secant(Complex a) {
    return sec(a);
  }

  /**
   * Returns the secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed secant(Fixed a) {
    return sec(a);
  }

  /**
   *
   * Returns the cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double cos(double a) {
    return java.lang.Math.cos(a);
  }

  /**
   *
   * Returns the cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex cos(Complex a) {
    argCheck(a);
    return new Complex(
            cos(a.real) * cosh(a.imaginary),
            - sin(a.real) * sinh(a.imaginary)
      );
  }

  /**
   *
   * Returns the cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed cos(Fixed a) {
    argCheck(a);
    return new Fixed(cos(a.toDouble()));
  }

  /**
   *
   * Returns the sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double sin(double a) {
    return java.lang.Math.sin(a);
  }

  /**
   *
   * Returns the sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex sin(Complex a) {
    argCheck(a);
    return new Complex(
            sin(a.real) * cosh(a.imaginary),
            cos(a.real) * sinh(a.imaginary)
      );
  }

  /**
   *
   * Returns the sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed sin(Fixed a) {
    argCheck(a);
    return new Fixed(sin(a.toDouble()));
  }

  /**
   *
   * Returns the tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double tan(double a) {
    return java.lang.Math.tan(a);
  }

  /**
   *
   * Returns the tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex tan(Complex a) {
    argCheck(a);
    return sin(a).div(cos(a));
  }

  /**
   *
   * Returns the tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed tan(Fixed a) {
    argCheck(a);
    return new Fixed(tan(a.toDouble()));
  }

  /**
   *
   * Returns the cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double cot(double a) {
    return 1 / tan(a);
  }

  /**
   *
   * Returns the cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex cot(Complex a) {
    argCheck(a);
    return cos(a).div(sin(a));
  }

  /**
   *
   * Returns the cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed cot(Fixed a) {
    argCheck(a);
    return new Fixed(cot(a.toDouble()));
  }

  /**
   *
   * Returns the cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double csc(double a) {
    return 1 / sin(a);
  }

  /**
   *
   * Returns the cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex csc(Complex a) {
    argCheck(a);
    return Complex.ONE.div(sin(a));
  }

  /**
   *
   * Returns the cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed csc(Fixed a) {
    argCheck(a);
    return new Fixed(csc(a.toDouble()));
  }

  /**
   *
   * Returns the secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static double sec(double a) {
    return 1 / cos(a);
  }

  /**
   *
   * Returns the secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Complex sec(Complex a) {
    argCheck(a);
    return Complex.ONE.div(cos(a));
  }

  /**
   *
   * Returns the secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated trigonometric transformation
   */
  public static Fixed sec(Fixed a) {
    argCheck(a);
    return new Fixed(sec(a.toDouble()));
  }




  /* inverse hyperbolic functions */

  /**
   *
   * Returns the inverse hyperbolic cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double arcHyperbolicCosine(double a) {
    return acosh(a);
  }

  /**
   *
   * Returns the inverse hyperbolic cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex arcHyperbolicCosine(Complex a) {
    return acosh(a);
  }

  /**
   *
   * Returns the inverse hyperbolic cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed arcHyperbolicCosine(Fixed a) {
    return acosh(a);
  }

  /**
   *
   * Returns the inverse hyperbolic sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double arcHyperbolicSine(double a) {
    return asinh(a);
  }

  /**
   *
   * Returns the inverse hyperbolic sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex arcHyperbolicSine(Complex a) {
    return asinh(a);
  }

  /**
   *
   * Returns the inverse hyperbolic sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed arcHyperbolicSine(Fixed a) {
    return asinh(a);
  }

  /**
   *
   * Returns the inverse hyperbolic tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double arcHyperbolicTangent(double a) {
    return atanh(a);
  }

  /**
   *
   * Returns the inverse hyperbolic tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex arcHyperbolicTangent(Complex a) {
    return atanh(a);
  }

  /**
   *
   * Returns the inverse hyperbolic tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed arcHyperbolicTangent(Fixed a) {
    return atanh(a);
  }

  /**
   *
   * Returns the inverse hyperbolic cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double arcHyperbolicCotangent(double a) {
    return acoth(a);
  }

  /**
   *
   * Returns the inverse hyperbolic cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex arcHyperbolicCotangent(Complex a) {
    return acoth(a);
  }

  /**
   *
   * Returns the inverse hyperbolic cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed arcHyperbolicCotangent(Fixed a) {
    return acoth(a);
  }

  /**
   *
   * Returns the inverse hyperbolic cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double arcHyperbolicCosecant(double a) {
    return acsch(a);
  }

  /**
   *
   * Returns the inverse hyperbolic cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex arcHyperbolicCosecant(Complex a) {
    return acsch(a);
  }

  /**
   *
   * Returns the inverse hyperbolic cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed arcHyperbolicCosecant(Fixed a) {
    return acsch(a);
  }

  /**
   *
   * Returns the inverse hyperbolic secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double arcHyperbolicSecant(double a) {
    return asech(a);
  }

  /**
   *
   * Returns the inverse hyperbolic secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex arcHyperbolicSecant(Complex a) {
    return asech(a);
  }

  /**
   *
   * Returns the inverse hyperbolic secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed arcHyperbolicSecant(Fixed a) {
    return asech(a);
  }

  /**
   *
   * Returns the inverse hyperbolic cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double acosh(double a) {
    return ln(a + sqrt(square(a) - 1));
  }

  /**
   *
   * Returns the inverse hyperbolic cosine of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex acosh(Complex c) {
    argCheck(c);
    return ln(
            Complex.add(
                    c,
                    Complex.polar(
                            sqrt(c.squared().sub(Complex.ONE).absoluteValue()),
                            c.squared().sub(Complex.ONE).rotation() / 2
                    )
            )
    );
  }

  /**
   *
   * Returns the inverse hyperbolic cosine of the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed acosh(Fixed f) {
    argCheck(f);
    return new Fixed(acosh(f.toDouble()));
  }

  /**
   *
   * Returns the inverse hyperbolic sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double asinh(double a) {
    return ln(a + sqrt(square(a) + 1));
  }

  /**
   *
   * Returns the inverse hyperbolic sine of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex asinh(Complex c) {
    argCheck(c);
    return ln(
            Complex.add(
                    c,
                    Complex.polar(
                            sqrt(Complex.add(Complex.ONE, c.squared()).absoluteValue()),
                            Complex.add(Complex.ONE, c.squared()).rotation() / 2
                    )
            )
    );
  }

  /**
   *
   * Returns the inverse hyperbolic sine of the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed asinh(Fixed f) {
    argCheck(f);
    return new Fixed(asinh(f.toDouble()));
  }

  /**
   *
   * Returns the inverse hyperbolic tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double atanh(double a) {
    return .5 * ln((1 + a) / (1 - a));
  }

  /**
   *
   * Returns the inverse hyperbolic tangent of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex atanh(Complex c) {
    argCheck(c);
    return ln(Complex.add(Complex.ONE, c).div(Complex.ONE.sub(c))).div(Complex.real(2));
  }

  /**
   *
   * Returns the inverse hyperbolic tangent of the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed atanh(Fixed f) {
    argCheck(f);
    return new Fixed(atanh(f.toDouble()));
  }

  /**
   *
   * Returns the inverse hyperbolic cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double acoth(double a) {
    return .5 * ln((a + 1) / (a - 1));
  }

  /**
   *
   * Returns the inverse hyperbolic cotangent of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex acoth(Complex c) {
    argCheck(c);
    return ln(Complex.add(c, Complex.ONE).div(c.sub(Complex.ONE))).div(Complex.real(2));
  }

  /**
   *
   * Returns the inverse hyperbolic cotangent of the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed acoth(Fixed f) {
    argCheck(f);
    return new Fixed(acoth(f.toDouble()));
  }

  /**
   *
   * Returns the inverse hyperbolic cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double acsch(double a) {
    return ln((1 + sqrt(1 + square(a))) / a);
  }

  /**
   *
   * Returns the inverse hyperbolic cosecant of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex acsch(Complex c) {
    argCheck(c);
    return asinh(Complex.ONE.div(c));
  }

  /**
   *
   * Returns the inverse hyperbolic cosecant of the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed acsch(Fixed f) {
    argCheck(f);
    return new Fixed(acsch(f.toDouble()));
  }

  /**
   *
   * Returns the inverse hyperbolic secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double asech(double a) {
    return ln((1 + sqrt(1 - square(a))) / a);
  }

  /**
   *
   * Returns the inverse hyperbolic secant of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex asech(Complex c) {
    argCheck(c);
    return acosh(Complex.ONE.div(c));
  }

  /**
   *
   * Returns the inverse hyperbolic secant of the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed asech(Fixed f) {
    argCheck(f);
    return new Fixed(asech(f.toDouble()));
  }



  /* hyperbolic functions */


  /**
   *
   * Returns the hyperbolic cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double hyperbolicCosine(double a) {
    return cosh(a);
  }

  /**
   *
   * Returns the hyperbolic cosine of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex hyperbolicCosine(Complex c) {
    return cosh(c);
  }

  /**
   *
   * Returns the hyperbolic cosine of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed hyperbolicCosine(Fixed c) {
    return cosh(c);
  }

  /**
   *
   * Returns the hyperbolic sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double hyperbolicSine(double a) {
    return sinh(a);
  }

  /**
   *
   * Returns the hyperbolic sine of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex hyperbolicSine(Complex c) {
    return sinh(c);
  }

  /**
   *
   * Returns the hyperbolic sine of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed hyperbolicSine(Fixed c) {
    return sinh(c);
  }

  /**
   *
   * Returns the hyperbolic tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double hyperbolicTangent(double a) {
    return tanh(a);
  }

  /**
   *
   * Returns the hyperbolic tangent of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex hyperbolicTangent(Complex c) {
    return tanh(c);
  }

  /**
   *
   * Returns the hyperbolic tangent of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed hyperbolicTangent(Fixed c) {
    return tanh(c);
  }

  /**
   *
   * Returns the hyperbolic cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double hyperbolicCotangent(double a) {
    return coth(a);
  }

  /**
   *
   * Returns the hyperbolic cotangent of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex hyperbolicCotangent(Complex c) {
    return coth(c);
  }

  /**
   *
   * Returns the hyperbolic cotangent of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed hyperbolicCotangent(Fixed c) {
    return coth(c);
  }

  /**
   *
   * Returns the hyperbolic cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double hyperbolicCosecant(double a) {
    return csch(a);
  }

  /**
   *
   * Returns the hyperbolic cosecant of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex hyperbolicCosecant(Complex c) {
    return csch(c);
  }

  /**
   *
   * Returns the hyperbolic cosecant of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed hyperbolicCosecant(Fixed c) {
    return csch(c);
  }

  /**
   *
   * Returns the hyperbolic secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double hyperbolicSecant(double a) {
    return sech(a);
  }

  /**
   *
   * Returns the hyperbolic secant of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex hyperbolicSecant(Complex c) {
    return sech(c);
  }

  /**
   *
   * Returns the hyperbolic secant of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed hyperbolicSecant(Fixed c) {
    return sech(c);
  }

  /**
   *
   * Returns the hyperbolic cosine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double cosh(double a) {
    return java.lang.Math.cosh(a);
  }

  /**
   *
   * Returns the hyperbolic cosine of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex cosh(Complex c) {
    argCheck(c);
    return new Complex(
            cosh(c.real) * cos(c.imaginary),
            sinh(c.real) * sin(c.imaginary)
        );
  }

  /**
   *
   * Returns the hyperbolic cosine of the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed cosh(Fixed f) {
    argCheck(f);
    return new Fixed(cosh(f.toDouble()));
  }

  /**
   *
   * Returns the hyperbolic sine of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double sinh(double a) {
    return java.lang.Math.sinh(a);
  }

  /**
   *
   * Returns the hyperbolic sine of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex sinh(Complex c) {
    argCheck(c);
    return new Complex(
            sinh(c.real) * cos(c.imaginary),
            cosh(c.real) * sin(c.imaginary)
        );
  }

  /**
   *
   * Returns the hyperbolic sine of the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed sinh(Fixed f) {
    argCheck(f);
    return new Fixed(sinh(f.toDouble()));
  }

  /**
   *
   * Returns the hyperbolic tangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double tanh(double a) {
    return java.lang.Math.tanh(a);
  }

  /**
   *
   * Returns the hyperbolic tangent of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex tanh(Complex c) {
    argCheck(c);
    return sin(c).div(cos(c));
  }

  /**
   *
   * Returns the hyperbolic tangent of the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed tanh(Fixed f) {
    argCheck(f);
    return new Fixed(tanh(f.toDouble()));
  }

  /**
   *
   * Returns the hyperbolic cotangent of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double coth(double a) {
    return 1 / tanh(a);
  }

  /**
   *
   * Returns the hyperbolic cotangent of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex coth(Complex c) {
    argCheck(c);
    return cos(c).div(sin(c));
  }

  /**
   *
   * Returns the hyperbolic cotangent of the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed coth(Fixed f) {
    argCheck(f);
    return new Fixed(coth(f.toDouble()));
  }

  /**
   *
   * Returns the hyperbolic cosecant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double csch(double a) {
    return 1 / sinh(a);
  }

  /**
   *
   * Returns the hyperbolic cosecant of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex csch(Complex c) {
    argCheck(c);
    return Complex.ONE.div(sinh(c));
  }

  /**
   *
   * Returns the hyperbolic cosecant of the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed csch(Fixed f) {
    argCheck(f);
    return new Fixed(csch(f.toDouble()));
  }

  /**
   *
   * Returns the hyperbolic secant of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static double sech(double a) {
    return 1 / cosh(a);
  }

  /**
   *
   * Returns the hyperbolic secant of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Complex sech(Complex c) {
    argCheck(c);
    return Complex.ONE.div(cosh(c));
  }

  /**
   *
   * Returns the hyperbolic secant of the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated hyperbolic transformation
   */
  public static Fixed sech(Fixed f) {
    argCheck(f);
    return new Fixed(sech(f.toDouble()));
  }



  /* Root functions */


  /**
   *
   * Returns the square root of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static double squareRoot(double a) {
    return sqrt(a);
  }

  /**
   *
   * Returns the square root of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static Complex squareRoot(Complex c) {
    return sqrt(c);
  }

  /**
   *
   * Returns the square root of the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static Fixed squareRoot(Fixed f) {
    return sqrt(f);
  }

  /**
   *
   * Returns the cube root of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static double cubeRoot(double a) {
    return cbrt(a);
  }

  /**
   *
   * Returns the cube root of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static Complex cubeRoot(Complex c) {
    return cbrt(c);
  }

  /**
   *
   * Returns the cube root of the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static Fixed cubeRoot(Fixed f) {
    return cbrt(f);
  }


  /**
   *
   * Returns the square root of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static double sqrt(double a) {
    return java.lang.Math.sqrt(a);
  }

  /**
   *
   * Returns the square root of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static Complex sqrt(Complex c) {
    return c.squared();
  }

  /**
   *
   * Returns the square root of the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static Fixed sqrt(Fixed f) {
    return f.squared();
  }

  /**
   *
   * Returns the cube root of the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static double cbrt(double a) {
    return java.lang.Math.cbrt(a);
  }

  /**
   *
   * Returns the cube root of the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static Complex cbrt(Complex c) {
    return c.power(3);
  }

  /**
   *
   * Returns the cube root of the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static Fixed cbrt(Fixed f) {
    return f.cubed();
  }



  /* Exponent functions */

  /**
   *
   * Returns the value of Euler's Constant (the natural constant)
   * exponentiated by the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static double exponent(double a) {
    return exp(a);
  }

  /**
   *
   * Returns the value of Euler's Constant (the natural constant)
   * exponentiated by the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static Complex exponent(Complex c) {
    return exp(c);
  }

  /**
   *
   * Returns the value of Euler's Constant (the natural constant)
   * exponentiated by the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static Fixed exponent(Fixed f) {
    return exp(f);
  }

  /**
   *
   * Returns the first value exponentiated by the second
   *
   * @param a the base of the exponent
   * @param b the the power of the exponent
   * @return the associated transformation
   */
  public static double power(double a, double b) {
    return pow(a, b);
  }

  /**
   *
   * Returns the first value exponentiated by the second
   *
   * @param a the base of the exponent
   * @param b the the power of the exponent
   * @return the associated transformation
   */
  public static Complex power(Complex a, Complex b) {
    return pow(a, b);
  }

  /**
   *
   * Returns the first value exponentiated by the second
   *
   * @param a the base of the exponent
   * @param b the the power of the exponent
   * @return the associated transformation
   */
  public static Fixed power(Fixed a, Fixed b) {
    return pow(a, b);
  }

  /**
   *
   * Returns the square of the provided value
   *
   * @param a the base of the square
   * @return the associated transformation
   */
  public static double square(double a) {
    return a * a;
  }

  /**
   *
   * Returns the square of the provided value
   *
   * @param c the base of the square
   * @return the associated transformation
   */
  public static Complex square(Complex c) {
    argCheck(c);
    return c.squared();
  }

  /**
   *
   * Returns the square of the provided value
   *
   * @param f the base of the square
   * @return the associated transformation
   */
  public static Fixed square(Fixed f) {
    argCheck(f);
    return f.squared();
  }

  /**
   *
   * Returns the cube of the provided value
   *
   * @param a the base of the cube
   * @return the associated transformation
   */
  public static double cube(double a) {
    return a * a * a;
  }

  /**
   *
   * Returns the cube of the provided value
   *
   * @param c the base of the cube
   * @return the associated transformation
   */
  public static Complex cube(Complex c){
    argCheck(c);
    return c.pow(3);
  }

  /**
   *
   * Returns the cube of the provided value
   *
   * @param f the base of the cube
   * @return the associated transformation
   */
  public static Fixed cube(Fixed f) {
    argCheck(f);
    return f.cubed();
  }

  /**
   *
   * Returns the value of Euler's Constant (the natural constant)
   * exponentiated by the provided value
   *
   * @param a the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static double exp(double a) {
    return java.lang.Math.exp(a);
  }

  /**
   *
   * Returns the value of Euler's Constant (the natural constant)
   * exponentiated by the provided value
   *
   * @param c the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static Complex exp(Complex c) {
    return COMPLEX_E.pow(c);
  }

  /**
   *
   * Returns the value of Euler's Constant (the natural constant)
   * exponentiated by the provided value
   *
   * @param f the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static Fixed exp(Fixed f) {
    return FIXED_E.pow(f);
  }

  /**
   *
   * Returns the value of Euler's Constant (the natural constant)
   * exponentiated by the provided value then subtracted by one
   *
   * @param a the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static double expm1(double a) {
    return java.lang.Math.expm1(a);
  }

  /**
   *
   * Returns the value of Euler's Constant (the natural constant)
   * exponentiated by the provided value then subtracted by one
   *
   * @param c the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static Complex expm1(Complex c) {
    argCheck(c);
    return exp(c).sub(Complex.ONE);
  }

  /**
   *
   * Returns the value of Euler's Constant (the natural constant)
   * exponentiated by the provided value then subtracted by one
   *
   * @param f the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static Fixed expm1(Fixed f) {
    argCheck(f);
    return exp(f).sub(Fixed.ONE);
  }

  /**
   *
   * Returns the first value exponentiated by the second
   *
   * @param a the base of the exponent
   * @param b the the power of the exponent
   * @return the associated transformation
   */
  public static double pow(double a, double b) {
    return java.lang.Math.pow(a, b);
  }

  /**
   *
   * Returns the first value exponentiated by the second
   *
   * @param a the base of the exponent
   * @param b the the power of the exponent
   * @return the associated transformation
   */
  public static Complex pow(Complex a, Complex b) {
    argCheck(a);
    return a.pow(b);
  }

  /**
   *
   * Returns the first value exponentiated by the second
   *
   * @param a the base of the exponent
   * @param b the the power of the exponent
   * @return the associated transformation
   */
  public static Fixed pow(Fixed a, Fixed b) {
    argCheck(a);
    return a.pow(b);
  }


  /* logarithm functions */


  /**
   *
   * Returns the natural logarithm of the provided value
   *
   * @param a the value of the first argument to the logarithm
   * @return the associated transformation
   */
  public static double ln(double a) {
    return log(a);
  }

  /**
   *
   * Returns the natural logarithm of the provided value
   *
   * @param c the value of the first argument to the logarithm
   * @return the associated transformation
   */
  public static Complex ln(Complex c) {
    return log(c);
  }

  /**
   *
   * Returns the natural logarithm of the provided value
   *
   * @param f the value of the first argument to the logarithm
   * @return the associated transformation
   */
  public static Fixed ln(Fixed f) {
    return log(f);
  }

  /**
   *
   * Returns the natural logarithm of the provided value
   *
   * @param a the value of the first argument to the logarithm
   * @return the associated transformation
   */
  public static double logarithm(double a) {
    return log(a);
  }

  /**
   *
   * Returns the natural logarithm of the provided value
   *
   * @param c the value of the first argument to the logarithm
   * @return the associated transformation
   */
  public static Complex logarithm(Complex c) {
    return log(c);
  }

  /**
   *
   * Returns the natural logarithm of the provided value
   *
   * @param f the value of the first argument to the logarithm
   * @return the associated transformation
   */
  public static Fixed logarithm(Fixed f) {
    return log(f);
  }

  /**
   *
   * Returns the base 10 logarithm of the provided value
   *
   * @param a the value of the first argument to the logarithm
   * @return the associated transformation
   */
  public static double logarithmOfBase10(double a) {
    return log10(a);
  }

  /**
   *
   * Returns the base 10 logarithm of the provided value
   *
   * @param c the value of the first argument to the logarithm
   * @return the associated transformation
   */
  public static Complex logarithmOfBase10(Complex c) {
    return log10(c);
  }

  /**
   *
   * Returns the base 10 logarithm of the provided value
   *
   * @param f the value of the first argument to the logarithm
   * @return the associated transformation
   */
  public static Fixed logarithmOfBase10(Fixed f) {
    return log10(f);
  }

  /**
   *
   * Returns the base X logarithm of the provided value where
   * X is the the provided base
   *
   * @param a the value of the first argument to the logarithm
   * @param base the value of the base for the logarithm
   * @return the associated transformation
   */
  public static double logarithmOfBase(double a, double base) {
    return logBase(a, base);
  }

  /**
   *
   * Returns the base X logarithm of the provided value where
   * X is the the provided base
   *
   * @param c the value of the first argument to the logarithm
   * @param base the value of the base for the logarithm
   * @return the associated transformation
   */
  public static Complex logarithmOfBase(Complex c, Complex base) {
    return logBase(c, base);
  }

  /**
   *
   * Returns the base X logarithm of the provided value where
   * X is the the provided base
   *
   * @param f the value of the first argument to the logarithm
   * @param base the value of the base for the logarithm
   * @return the associated transformation
   */
  public static Fixed logarithmOfBase(Fixed f, Fixed base) {
    return logBase(f, base);
  }

  /**
   *
   * Returns the natural logarithm of the provided value
   *
   * @param a the value of the first argument to the logarithm
   * @return the associated transformation
   */
  public static double log(double a) {
    return java.lang.Math.log(a);
  }

  /**
   *
   * Returns the natural logarithm of the provided value
   *
   * @param c the value of the first argument to the logarithm
   * @return the associated transformation
   */
  public static Complex log(Complex c) {
    argCheck(c);
    return new Complex(log(abs(c)), atan2(c.imaginary, c.real));
  }

  /**
   *
   * Returns the natural logarithm of the provided value
   *
   * @param f the value of the first argument to the logarithm
   * @return the associated transformation
   */
  public static Fixed log(Fixed f) {
    argCheck(f);
    return new Fixed(log(f.toDouble()));
  }

  /**
   *
   * Returns the base 10 logarithm of the provided value
   *
   * @param a the value of the first argument to the logarithm
   * @return the associated transformation
   */
  public static double log10(double a) {
    return java.lang.Math.log10(a);
  }

  /**
   *
   * Returns the base 10 logarithm of the provided value
   *
   * @param c the value of the first argument to the logarithm
   * @return the associated transformation
   */
  public static Complex log10(Complex c) {
    argCheck(c);
    return log(c).div(Complex.real(log(10)));
  }

  /**
   *
   * Returns the base 10 logarithm of the provided value
   *
   * @param f the value of the first argument to the logarithm
   * @return the associated transformation
   */
  public static Fixed log10(Fixed f) {
    argCheck(f);
    return log(f).div(new Fixed(log(10)));
  }

  /**
   *
   * Returns the value of Euler's Constant (the natural constant)
   * exponentiated by the sum of the provided value and unity
   *
   * @param x the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static double log1p(double x) {
    return java.lang.Math.log1p(x);
  }

  /**
   *
   * Returns the value of Euler's Constant (the natural constant)
   * exponentiated by the sum of the provided value and unity
   *
   * @param c the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static Complex log1p(Complex c) {
    argCheck(c);
    return log(Complex.add(c, Complex.ONE));
  }

  /**
   *
   * Returns the value of Euler's Constant (the natural constant)
   * exponentiated by the sum of the provided value and unity
   *
   * @param f the value with which the function will be evaluated
   * @return the associated transformation
   */
  public static Fixed log1p(Fixed f) {
    argCheck(f);
    return log(Fixed.add(f, Fixed.ONE));
  }

  /**
   *
   * Returns the base X logarithm of the provided value where
   * X is the the provided base
   *
   * @param a the value of the first argument to the logarithm
   * @param base the value of the base for the logarithm
   * @return the associated transformation
   */
  public static double logBase(double a, double base) {
    return java.lang.Math.log(a) / java.lang.Math.log(base);
  }

  /**
   *
   * Returns the base X logarithm of the provided value where
   * X is the the provided base
   *
   * @param c the value of the first argument to the logarithm
   * @param base the value of the base for the logarithm
   * @return the associated transformation
   */
  public static Complex logBase(Complex c, Complex base) {
    argCheck(c);
    if (base == null)
      throw new IllegalArgumentException("Logarithm base cannot be null");
    return log(c).div(log(base));
  }

  /**
   *
   * Returns the base X logarithm of the provided value where
   * X is the the provided base
   *
   * @param f the value of the first argument to the logarithm
   * @param base the value of the base for the logarithm
   * @return the associated transformation
   */
  public static Fixed logBase(Fixed f, Fixed base) {
    argCheck(f);
    if (base == null)
      throw new IllegalArgumentException("Logarithm base cannot be null");
    return log(f).div(log(base));
  }



  /* Misc */



  /**
   *
   * Calculates the cumulative summation of the
   * provided values
   *
   * @param values the values for the sum
   * @return the sum of the values
   */
  public static double cumSum(double... values) {
    return cumulativeSum(values);
  }

  /**
   *
   * Calculates the cumulative summation of the
   * provided values
   *
   * @param values the values for the sum
   * @return the sum of the values
   */
  public static float cumSum(float... values) {
    return cumulativeSum(values);
  }

  /**
   *
   * Calculates the cumulative summation of the
   * provided values
   *
   * @param values the values for the sum
   * @return the sum of the values
   */
  public static long cumSum(long... values) {
    return cumulativeSum(values);
  }

  /**
   *
   * Calculates the cumulative summation of the
   * provided values
   *
   * @param values the values for the sum
   * @return the sum of the values
   */
  public static int cumSum(int... values) {
    return cumulativeSum(values);
  }

  /**
   *
   * Calculates the cumulative summation of the
   * provided values
   *
   * @param values the values for the sum
   * @return the sum of the values
   */
  public static short cumSum(short... values) {
    return cumulativeSum(values);
  }

  /**
   *
   * Calculates the cumulative summation of the
   * provided values
   *
   * @param values the values for the sum
   * @return the sum of the values
   */
  public static byte cumSum(byte... values) {
    return cumulativeSum(values);
  }



  /**
   *
   * Calculates the cumulative summation of the
   * provided values
   *
   * @param values the values for the sum
   * @return the sum of the values
   */
  public static double cumulativeSum(double... values) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");

    double ret = 0;
    for(double v : values) {
      ret += v;
    }

    return ret;
  }

  /**
   *
   * Calculates the cumulative summation of the
   * provided values
   *
   * @param values the values for the sum
   * @return the sum of the values
   */
  public static float cumulativeSum(float... values) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");

    float ret = 0;
    for(float v : values) {
      ret += v;
    }

    return ret;
  }

  /**
   *
   * Calculates the cumulative summation of the
   * provided values
   *
   * @param values the values for the sum
   * @return the sum of the values
   */
  public static long cumulativeSum(long... values) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");

    long ret = 0;
    for(long v : values) {
      ret += v;
    }

    return ret;
  }

  /**
   *
   * Calculates the cumulative summation of the
   * provided values
   *
   * @param values the values for the sum
   * @return the sum of the values
   */
  public static int cumulativeSum(int... values) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");

    int ret = 0;
    for(int v : values) {
      ret += v;
    }

    return ret;
  }

  /**
   *
   * Calculates the cumulative summation of the
   * provided values
   *
   * @param values the values for the sum
   * @return the sum of the values
   */
  public static short cumulativeSum(short... values) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");

    short ret = 0;
    for(short v : values) {
      ret += v;
    }

    return ret;
  }

  /**
   *
   * Calculates the cumulative summation of the
   * provided values
   *
   * @param values the values for the sum
   * @return the sum of the values
   */
  public static byte cumulativeSum(byte... values) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");

    byte ret = 0;
    for(byte v : values) {
      ret += v;
    }

    return ret;
  }

  /**
   *
   * Calculates the 'ceiling' function on the provided
   * value
   *
   * @param a the value for which to calculate the ceiling
   * @return the ceiling of the provided value
   */
  public static double ceiling(double a) {
    return ceil(a);
  }

  /**
   *
   * Calculates the 'ceiling' function on the provided
   * value.
   *
   * @param c the value for which to calculate the ceiling
   * @return the ceiling of both the imaginary and real components as a complex value
   */
  public static Complex ceiling(Complex c) {
    return ceil(c);
  }

  /**
   *
   * Calculates the 'ceiling' function on the provided
   * value
   *
   * @param f the value for which to calculate the ceiling
   * @return the ceiling of the provided value
   */
  public static Fixed ceiling(Fixed f) {
    return ceil(f);
  }

  /**
   *
   * Calculates the 'floor' function on the provided
   * value
   *
   * @param a the value for which to calculate the floor
   * @return the floor of the provided value
   */
  public static double floor(double a) {
    return java.lang.Math.floor(a);
  }

  /**
   *
   * Calculates the 'ceiling' function on the provided
   * value
   *
   * @param c the value for which to calculate the ceiling
   * @return the ceiling of both the imaginary and real components as a complex valuee
   */
  public static Complex floor(Complex c) {
    argCheck(c);
    return new Complex(floor(c.real), floor(c.imaginary));
  }

  /**
   *
   * Calculates the 'floor' function on the provided
   * value
   *
   * @param f the value for which to calculate the floor
   * @return the floor of the provided value
   */
  public static Fixed floor(Fixed f) {
    argCheck(f);
    Fixed rem = f.mod(1);
    if (rem.equals(Fixed.ZERO))
      return f;
    if (f.isNegative())
      return f.sub(Fixed.ONE, rem);
    else
      return f.sub(rem);
  }


  /**
   *
   * Calculates the 'ceiling' function on the provided
   * value
   *
   * @param a the value for which to calculate the ceiling
   * @return the ceiling of the provided value
   */
  public static double ceil(double a) {
    return java.lang.Math.ceil(a);
  }

  /**
   *
   * Calculates the 'ceiling' function on the provided
   * value.
   *
   * @param c the value for which to calculate the ceiling
   * @return the ceiling of both the imaginary and real components as a complex value
   */
  public static Complex ceil(Complex c) {
    argCheck(c);
    return new Complex(ceil(c.real), ceil(c.imaginary));
  }

  /**
   *
   * Calculates the 'ceiling' function on the provided
   * value
   *
   * @param f the value for which to calculate the ceiling
   * @return the ceiling of the provided value
   */
  public static Fixed ceil(Fixed f) {
    argCheck(f);
    Fixed rem = f.mod(1);
    if (rem.equals(Fixed.ZERO))
      return f;
    if (f.isNegative())
      return f.sub(rem);
    else
      return Fixed.add(f, Fixed.ONE.sub(rem));
  }

  /**
   *
   * Calculates the hypotenuse of a right-triangle
   * whose other sides have the length 'x' and 'y'
   * respectively
   *
   * This method returns the square root of the sum of
   * the squares of the two provided values
   *
   * @param x a side of the right-triangle
   * @param y another side of the right-triangle
   * @return the hypotenuse length
   */
  public static double hypotenuse(double x, double y) {
    return hypot(x, y);
  }

  /**
   *
   * Calculates the hypotenuse of a right-triangle
   * whose other sides have the length 'a' and 'b'
   * respectively
   *
   * This method returns the square root of the sum of
   * the squares of the two provided values
   *
   * @param a a side of the right-triangle
   * @param b another side of the right-triangle
   * @return the hypotenuse length
   */
  public static double hypotenuse(Complex a, Complex b) {
    return hypot(a, b);
  }

  /**
   *
   * Calculates the hypotenuse of a right-triangle
   * whose other sides have the length 'a' and 'b'
   * respectively
   *
   * This method returns the square root of the sum of
   * the squares of the two provided values
   *
   * @param a a side of the right-triangle
   * @param b another side of the right-triangle
   * @return the hypotenuse length
   */
  public static Fixed hypotenuse(Fixed a, Fixed b) {
    return hypot(a, b);
  }

  /**
   *
   * Calculates the hypotenuse of a right-triangle
   * whose other sides have the length 'x' and 'y'
   * respectively
   *
   * This method returns the square root of the sum of
   * the squares of the two provided values
   *
   * @param x a side of the right-triangle
   * @param y another side of the right-triangle
   * @return the hypotenuse length
   */
  public static double hypot(double x, double y) {
    return java.lang.Math.hypot(x, y);
  }

  /**
   *
   * Calculates the hypotenuse of a right-triangle
   * whose other sides have the length 'a' and 'b'
   * respectively
   *
   * This method returns the square root of the sum of
   * the squares of the two provided values
   *
   * @param a a side of the right-triangle
   * @param b another side of the right-triangle
   * @return the hypotenuse length
   */
  public static double hypot(Complex a, Complex b) {
    argCheck(a);
    argCheck(b);

    return a.sub(b).absoluteValue();
  }

  /**
   *
   * Calculates the hypotenuse of a right-triangle
   * whose other sides have the length 'a' and 'b'
   * respectively
   *
   * This method returns the square root of the sum of
   * the squares of the two provided values
   *
   * @param a a side of the right-triangle
   * @param b another side of the right-triangle
   * @return the hypotenuse length
   */
  public static Fixed hypot(Fixed a, Fixed b) {
    argCheck(a);
    argCheck(b);

    return Fixed.add(a.squared(), b.squared()).pow(0.5);
  }

  /**
   *
   * Calculates the sign of the value. Returns 1 if
   * the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative
   *
   * @param a the value to take the sign
   * @return the sign of the value
   */
  public static double sgn(double a) {
    return signum(a);
  }

  /**
   *
   * Calculates the sign of the value. Returns 1 if
   * the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative
   *
   * @param a the value to take the sign
   * @return the sign of the value
   */
  public static float sgn(float a) {
    return signum(a);
  }

  /**
   *
   * Calculates the sign of the value. Returns 1 if
   * the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative
   *
   * @param a the value to take the sign
   * @return the sign of the value
   */
  public static int sgn(int a) {
    return signum(a);
  }

  /**
   *
   * Calculates the sign of the value. Returns 1 if
   * the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative
   *
   * @param a the value to take the sign
   * @return the sign of the value
   */
  public static long sgn(long a) {
    return signum(a);
  }

  /**
   *
   * Calculates the sign of the value. Returns 1 if
   * the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative.
   *
   * @param c the value to take the sign
   * @return the sign of the real and imaginary values represented as a complex value
   */
  public static Complex sgn(Complex c) {
    return signum(c);
  }

  /**
   *
   * Calculates the sign of each value in the vector.
   * 1 if the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative.
   *
   * @param v the value to take the sign
   * @return the sign of each value represented as a vector.
   */
  public static Vector sgn(Vector v) {
    return signum(v);
  }

  /**
   *
   * Calculates the sign of each value in the vector.
   * 1 if the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative.
   *
   * @param v the value to take the sign
   * @return the sign of each value represented as a vector.
   */
  public static NVector sgn(IVector v) {
    return signum(v);
  }

  /**
   *
   * Calculates the sign of each value in the matrix.
   * 1 if the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative.
   *
   * @param m the value to take the sign
   * @return the sign of each value represented as a matrix.
   */
  public static Matrix sgn(Matrix m) {
    return signum(m);
  }

  /**
   *
   * Calculates the sign of each value in the matrix.
   * 1 if the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative.
   *
   * @param m the value to take the sign
   * @return the sign of each value represented as a matrix.
   */
  public static NMatrix sgn(IMatrix m) {
    return signum(m);
  }

  /**
   *
   * Calculates the sign of the value. Returns 1 if
   * the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative
   *
   * @param f the value to take the sign
   * @return the sign of the value
   */
  public static Fixed sgn(Fixed f) {
    return signum(f);
  }

  /**
   *
   * Calculates the sign of the value. Returns 1 if
   * the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative
   *
   * @param a the value to take the sign
   * @return the sign of the value
   */
  public static double signum(double a) {
    return java.lang.Math.signum(a);
  }

  /**
   *
   * Calculates the sign of the value. Returns 1 if
   * the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative
   *
   * @param a the value to take the sign
   * @return the sign of the value
   */
  public static float signum(float a) {
    return java.lang.Math.signum(a);
  }

  /**
   *
   * Calculates the sign of the value. Returns 1 if
   * the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative
   *
   * @param a the value to take the sign
   * @return the sign of the value
   */
  public static int signum(int a) {
    return (int) java.lang.Math.signum(a);
  }

  /**
   *
   * Calculates the sign of the value. Returns 1 if
   * the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative
   *
   * @param a the value to take the sign
   * @return the sign of the value
   */
  public static long signum(long a) {
    return (long) java.lang.Math.signum(a);
  }


  /**
   *
   * Calculates the sign of the value. Returns 1 if
   * the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative.
   *
   * @param c the value to take the sign
   * @return the sign of the real and imaginary values represented as a complex value
   */
  public static Complex signum(Complex c) {
    argCheck(c);
    return new Complex(
            signum(c.real),
            signum(c.imaginary)
        );
  }

  /**
   *
   * Calculates the sign of each value in the vector.
   * 1 if the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative.
   *
   * @param v the value to take the sign
   * @return the sign of each value represented as a vector.
   */
  public static Vector signum(Vector v) {
    argCheck(v);
    return new Vector(v.x, v.y, v.z);
  }

  /**
   *
   * Calculates the sign of each value in the vector.
   * 1 if the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative.
   *
   * @param v the value to take the sign
   * @return the sign of each value represented as a vector.
   */
  public static NVector signum(IVector v) {
    argCheck(v);

    double[] array = v.toArray();
    for(int k = 0; k < array.length; k++) {
      array[k] = round(array[k]);
    }

    return new NVector(array);
  }

  /**
   *
   * Calculates the sign of each value in the matrix.
   * 1 if the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative.
   *
   * @param m the value to take the sign
   * @return the sign of each value represented as a matrix.
   */
  public static Matrix signum(Matrix m) {
    argCheck(m);
    return new Matrix(
            signum(m.m00), signum(m.m01), signum(m.m02),
            signum(m.m10), signum(m.m11), signum(m.m12),
            signum(m.m20), signum(m.m21), signum(m.m22)
    );
  }

  /**
   *
   * Calculates the sign of each value in the matrix.
   * 1 if the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative.
   *
   * @param m the value to take the sign
   * @return the sign of each value represented as a matrix.
   */
  public static NMatrix signum(IMatrix m) {
    argCheck(m);
    double[] ret = new double[m.numberOfRows() * m.numberOfColumns()];
    int top = 0;

    for(int x = 0; x < m.numberOfColumns(); x++) {
      for(int y = 0; y < m.numberOfRows(); y++) {
        ret[top++] = signum(m.get(y, x));
      }
    }

    return new NMatrix(m.numberOfRows(), m.numberOfColumns(), ret);
  }

  /**
   *
   * Calculates the sign of the value. Returns 1 if
   * the value is positive, -1 if the value is
   * negative, zero if the value is neither
   * positive or negative
   *
   * @param f the value to take the sign
   * @return the sign of the value
   */
  public static Fixed signum(Fixed f) {
    argCheck(f);

    if (f.isZero())
      return Fixed.ZERO;
    else if (f.isPositive())
      return Fixed.ONE;
    else
      return Fixed.ONE.negative();
  }

  /**
   *
   * Rounds the provided value to an integer, but
   * still represented as a floating point
   *
   * @param d the value to round
   * @return the rounded value
   */
  public static double round(double d) {
    return java.lang.Math.round(d);
  }

  /**
   *
   * Rounds the provided value to an integer, but
   * still represented as a floating point
   *
   * @param f the value to round
   * @return the rounded value
   */
  public static float round(float f) {
    return java.lang.Math.round(f);
  }

  /**
   *
   * Rounds the provided value to a complex value
   * which can be represented as the sum
   * of an integer and an integer multiplied by
   * the imaginary constant
   *
   * @param c the value to round
   * @return the rounded value as a complex number
   */
  public static Complex round(Complex c) {
    argCheck(c);
    return new Complex(round(c.real), round(c.imaginary));
  }

  /**
   *
   * Rounds the provided value to the
   * closest integer
   *
   * @param f the value to round
   * @return the rounded value
   */
  public static Fixed round(Fixed f) {
    argCheck(f);
    return f.round();
  }

  /**
   *
   * Rounds the provided vector to
   * a vector whose elements are
   * each rounded to the nearest
   * integer
   *
   * @param v the vector to round
   * @return a vector of rounded elements
   */
  public static Vector round(Vector v) {
    argCheck(v);
    return new Vector(round(v.x), round(v.y), round(v.z));
  }

  /**
   *
   * Rounds the provided vector to
   * a vector whose elements are
   * each rounded to the nearest
   * integer
   *
   * @param v the vector to round
   * @return a vector of rounded elements
   */
  public static NVector round(IVector v) {
    argCheck(v);

    double[] ret = v.toArray();
    for(int k = 0; k < ret.length; k++) {
      ret[k] = round(ret[k]);
    }

    return new NVector(ret);
  }

  /**
   *
   * Rounds the provided matrix to
   * a matrix whose elements are each
   * rounded to the nearest integer
   *
   * @param m the matrix to round
   * @return a matrix of rounded elements
   */
  public static Matrix round(Matrix m) {
    argCheck(m);
    return new Matrix(
            round(m.m00), round(m.m01), round(m.m02),
            round(m.m10), round(m.m11), round(m.m12),
            round(m.m20), round(m.m21), round(m.m22)
      );
  }

  /**
   *
   * Rounds the provided matrix to
   * a matrix whose elements are each
   * rounded to the nearest integer
   *
   * @param m the matrix to round
   * @return a matrix of rounded elements
   */
  public static NMatrix round(IMatrix m) {
    argCheck(m);
    double[] ret = new double[m.numberOfRows() * m.numberOfColumns()];
    int top = 0;

    for(int x = 0; x < m.numberOfColumns(); x++) {
      for(int y = 0; y < m.numberOfRows(); y++) {
        ret[top++] = round(m.get(y, x));
      }
    }

    return new NMatrix(m.numberOfRows(), m.numberOfColumns(), ret);
  }

  /**
   *
   * Rounds the provided value down to the
   * nearest integer
   *
   * @param value the value to round
   * @return the rounded-down value
   */
  public static double roundDown(double value) {
    double rounded = JMath.round(value);
    if (rounded > value)
      rounded--;

    return rounded;
  }

  /**
   *
   * Rounds the provided vector such that
   * each element is rounded down
   * to the nearest integer
   *
   * @param vector the vector to round
   * @return a vector of rounded-down elements
   */
  public static Vector roundDown(Vector vector) {
    return new Vector(
            roundDown(vector.x),
            roundDown(vector.y),
            roundDown(vector.z)
      );
  }


  /**
   *
   * Rounds the provided value up to the
   * nearest integer
   *
   * @param value the value to round
   * @return the rounded-up value
   */
  public static double roundUp(double value) {
    double rounded = JMath.round(value);
    if (rounded < value)
      rounded++;

    return rounded;
  }

  /**
   *
   * Rounds the provided vector such that
   * each element is rounded up
   * to the nearest integer
   *
   * @param vector the vector to round
   * @return a vector of rounded-up elements
   */
  public static Vector roundUp(Vector vector) {
    return new Vector(
            roundUp(vector.x),
            roundUp(vector.y),
            roundUp(vector.z)
      );
  }


  /**
   *
   * Returns a value in the domain of [min, max]
   * closest to the argument 'val'. The value of
   * val will be returned if it falls within
   * the provided domain. If it is greater, the
   * value 'max' will be returned. If it is less,
   * the value 'min' will be returned.
   *
   * @param val the value to clamp
   * @param min the minimum value of the domain
   * @param max the maximum value of the domain
   * @return the value closest to 'val' within the domain
   */
  public static double clamp(double val, double min, double max) {

    if (min > max)
      throw new IllegalArgumentException("Minimum value cannot be greater than maximum value");

    if (val < min)
      val = min;
    if (val > max)
      val = max;

    return val;
  }

  /**
   *
   * Modifies the provided value array in place
   * such that each element represents the
   * closest value within the domain [min, max]
   * to with respect to the original value
   * closest to the argument 'val'. Each element
   * will be be unchanged if it falls within
   * the provided domain. If it is greater, the
   * value 'max' will be set in it's place. If it is less,
   * the value 'min' will be set in its place.
   *
   * @param values the value array to clamp
   * @param min the minimum value of the domain
   * @param max the maximum value of the domain
   * @return the provided value array
   */
  public static double[] clampAll(double[] values, double min, double max) {
    if (values == null)
      throw new IllegalArgumentException("Values array may not be null");

    for(int k = 0; k < values.length; k++) {
      values[k] = clamp(values[k], min, max);
    }

    return values;
  }


  /* Aliases */


  /**
   *
   * is functionally equivalent
   * to java.lang.Math.copySign(double, double)
   *
   */
  public static double copySign(double magnitude, double sing) {
    return java.lang.Math.copySign(magnitude, sing);
  }

  /**
   *
   * is functionally equivalent
   * to java.lang.Math.copySign(float, float)
   *
   */
  public static float copySign(float magnitude, float sing) {
    return java.lang.Math.copySign(magnitude, sing);
  }


  /**
   *
   * is functionally equivalent
   * to java.lang.Math.getExponent(double)
   *
   */
  public static int getExponent(double d) {
    return java.lang.Math.getExponent(d);
  }

  /**
   *
   * is functionally equivalent
   * to java.lang.Math.getExponent(float)
   *
   */
  public static int getExponent(float f) {
    return java.lang.Math.getExponent(f);
  }


  /**
   *
   * is functionally equivalent
   * to java.lang.Math.IEEEremainder(double)
   *
   */
  public static double IEEEremainder(double f1, double f2) {
    return java.lang.Math.IEEEremainder(f1, f2);
  }

  /**
   *
   * is functionally equivalent
   * to java.lang.Math.nextAfter(double, double)
   *
   */
  public static double nextAfter(double start, double direction) {
    return java.lang.Math.nextAfter(start, direction);
  }

  /**
   *
   * is functionally equivalent
   * to java.lang.Math.nextAfter(float, float)
   *
   */
  public static double nextAfter(float start, float direction) {
    return java.lang.Math.nextAfter(start, direction);
  }


  /**
   *
   * is functionally equivalent
   * to java.lang.Math.nextUp(double)
   *
   */
  public static double nextUp(double d) {
    return java.lang.Math.nextUp(d);
  }
  /**
   *
   * is functionally equivalent
   * to java.lang.Math.nextUp(float)
   *
   */
  public static double nextUp(float f) {
    return java.lang.Math.nextUp(f);
  }

  /**
   *
   * is functionally equivalent
   * to java.lang.Math.random()
   *
   */
  public static double random() {
    return java.lang.Math.random();
  }

  /**
   *
   * is functionally equivalent
   * to java.lang.Math.rint(double)
   *
   */
  public static double rint(double a) {
    return java.lang.Math.rint(a);
  }

  /**
   *
   * is functionally equivalent
   * to java.lang.Math.scalb(double, int)
   *
   */
  public static double scalb(double d, int scaleFactor) {
    return java.lang.Math.scalb(d, scaleFactor);
  }
  /**
   *
   * is functionally equivalent
   * to java.lang.Math.scalb(float, int)
   *
   */
  public static float scalb(float d, int scaleFactor) {
    return java.lang.Math.scalb(d, scaleFactor);
  }

  /**
   *
   * is functionally equivalent
   * to java.lang.Math.toDegrees(double)
   *
   */
  public static double toDegrees(double angrad) {
    return java.lang.Math.toDegrees(angrad);
  }

  /**
   *
   * is functionally equivalent
   * to java.lang.Math.toRadians(double)
   *
   */
  public static double toRadians(double angdeg) {
    return java.lang.Math.toRadians(angdeg);
  }

  /**
   *
   * is functionally equivalent
   * to java.lang.Math.ulp(double)
   *
   */
  public static double ulp(double d) {
    return java.lang.Math.ulp(d);
  }

  /**
   *
   * is functionally equivalent
   * to java.lang.Math.ulp(float)
   *
   */
  public static float ulp(float f) {
    return java.lang.Math.ulp(f);
  }


  /* Calculus */

  /**
   *
   * performs rectangular approximation
   * on the given expression over the provided
   * domain with n^x many rectangles where x is
   * the dimensionality of the expression and domain
   *
   * @param expression the expression on which to integrate
   * @param domain the domain over which to integrate
   * @param n the number of rectangles
   * @return the area under the expression
   */
  public static double rectangularApproximationMethod(
          Expression expression,
          Domain domain,
          int n
          ) {

    if (expression == null)
      throw new IllegalArgumentException("Expression cannot be null");
    if (domain == null)
      throw new IllegalArgumentException("Domain cannot be null");
    if (n <= 0)
      throw new IllegalArgumentException("N must be positive");

    String[] variableArray = expression.variableList();
    for(String variable : variableArray) {
      if(!domain.hasDimension(variable))
        throw new IllegalArgumentException(String.format("Domain does not define bounds for variable '%s'", variable));
    }

    class Multi {

      int[] array;
      int n;
      boolean completed = false;

      Multi(int width, int n) {
        array = new int[width];
        this.n = n;
      }

      boolean complete() {
        return completed;
      }

      void incriment() {
        for(int k = 0; k < array.length; k++) {
          array[k]++;
          if (array[k] >= n)
            array[k] = 0;
          else
            return;
        }

        completed = true;
      }

    }

    double ret = 0;


    double hyperArea = 1;
    double[] intervals = new double[variableArray.length];
    for(int k = 0; k < intervals.length; k++) {
      intervals[k] = (domain.getUpper(variableArray[k]) - domain.getLower(variableArray[k])) / n;
      hyperArea *= intervals[k];
    }

    for(Multi multi = new Multi(variableArray.length, n); !multi.complete(); multi.incriment()) {

      double[] positions = new double[variableArray.length];

      for(int k = 0; k < variableArray.length; k++) {
        positions[k] = domain.getLower(variableArray[k]) + intervals[k] * (multi.array[k] + 0.5);
      }

      try {
        double value = expression.evaluate(positions);
        ret += hyperArea * value;
        continue;
      } catch (EvaluationException e) {}
      try {
        Complex value = expression.evaluate(positions);
        ret += hyperArea * value.real;
        continue;
      } catch (EvaluationException e) {}

      throw new ArithmeticException("Expression returned an invalid type");
    }

    return ret;
  }

  /**
   *
   * Performs rectangular approximation on
   * the given expression from 'start' to 'end'.
   * Note this expression must contain only one variable
   *
   * @param expression the expression on which to integrate
   * @param start the start of the domain
   * @param end the end of the domain
   * @param n the number of rectangles
   * @return the area under the expression
   */
  public static double rectangularApproximationMethod(
          Expression expression,
          double start, double end,
          int n
          ) {

    if (end == start)
      return 0;
    else if (end < start)
      return -rectangularApproximationMethod(expression, end, start, n);

    double
            difference = end - start,
            interval = difference / n,
            ret = 0
        ;

    for(int k = 0; k < n; k++) {

      double position = start + ((double) k + .5) * interval;
      try {
        double value = expression.evaluate(position);
        ret += interval * value;
        continue;
      } catch (EvaluationException e) {}
      try {
        Complex value = expression.evaluate(position);
        ret += interval * value.real;
        continue;
      } catch (EvaluationException e) {}


      throw new ArithmeticException("Expression returned an invalid type");
    }

    return ret;
  }


  /**
   *
   * Performs newton's approximation method on
   * the given expression with the given
   * start value, and the number of rounds
   *
   * @param expression the to be zeroed
   * @param rounds the number of rounds to operate over
   * @param start the start value of the method
   * @return the calculated zero of the expression
   */
  public static double newtonMethod(
          Expression expression,
          int rounds,
          double start
          ) {
    if (expression == null)
      throw new IllegalArgumentException("Expression cannot be null");
    String[] variableList = expression.variableList();
    if (variableList.length != 1)
      throw new IllegalArgumentException("Expression must contain exactly one variable");
    if (rounds <= 0)
      throw new IllegalArgumentException("Round count must be positive");


    Evaluable op = expression.getEvaluable();
    if (op.getEvaluableType() == EvaluableType.OPERATION)
      op = ((Operation) op).simplify();

    Evaluable derrivative = op.derivative(variableList[0]);
    if (derrivative.getEvaluableType() == EvaluableType.OPERATION)
      derrivative = ((Operation) derrivative).simplify();


    Expression newtonian = new Expression(
            OperationFactory.sub(
                    new Variable(variableList[0]),
                    OperationFactory.div(
                            op,
                            derrivative
                    )
            )
     );

    for(int k = 0; k < rounds; k++) {
      start = newtonian.evaluate(start);
    }

    return start;
  }

  /**
   *
   * Performs secant approximation method on
   * the given expression with the given
   * start values, and the number of rounds
   *
   * @param expression the expression to be zeroed
   * @param rounds the number of rounds to operate over
   * @param s0 the first start value
   * @param s1 the second start value
   * @return the calculated zero of the expression
   */
  public static double secantMethod(
          Expression expression,
          int rounds,
          double s0,
          double s1
          ) {
    if (expression == null)
      throw new IllegalArgumentException("Expression cannot be null");
    String[] variableList = expression.variableList();
    if (variableList.length != 1)
      throw new IllegalArgumentException("Expression must contain exactly one variable");
    if (rounds <= 0)
      throw new IllegalArgumentException("Round count must be positive");
    if (s0 == s1)
      throw new IllegalArgumentException("Starts cannot be equal");

    String v1 = variableList[0], v2 = v1 + "_";

    Evaluable e1 = expression.getEvaluable();
    if (e1.getEvaluableType() == EvaluableType.OPERATION)
      e1 = ((Operation) e1).simplify();

    Expression secant = new Expression(
            OperationFactory.sub(
                    new Variable(v1),
                    OperationFactory.mul(
                            e1,
                            OperationFactory.div(
                                    OperationFactory.sub(
                                            new Variable(v1),
                                            new Variable(v2)
                                    ),
                                    OperationFactory.sub(
                                            e1,
                                            e1.replaceVariable(v1, new Variable(v2))
                                    )
                            )
                    )
            )
    );

    for(int k = 0; k < rounds && s0 != s1; k++) {
      double s_ = secant.evaluate(ArgumentList.create("%s = %s; %s = %s", v1, s0, v2, s1));
      s1 = s0;
      s0 = s_;
    }

    return s0;
  }

  /**
   *
   * Find the root using the provided method
   *
   * @param zeroMethod the method to use
   * @param expression the expression to zero
   * @param rounds the number of rounds
   * @param start the start values
   * @return the calculated zero of the method
   */
  public static double findRoot(
          ZeroMethod zeroMethod,
          Expression expression,
          int rounds,
          double... start
          ) {
    if (expression == null)
      throw new IllegalArgumentException("Expression cannot be null");
    String[] variableList = expression.variableList();
    if (variableList.length != 1)
      throw new IllegalArgumentException("Expression must contain exactly one variable");
    if (rounds <= 0)
      throw new IllegalArgumentException("Round count must be positive");
    if (zeroMethod == null)
      zeroMethod = ZeroMethod.NEWTON_METHOD;
    if (start == null)
      throw new IllegalArgumentException("Starts array cannot be null");
    if (start.length != zeroMethod.requiredStarts)
      throw new IllegalArgumentException(String.format("Selected zeroing method requires %s starting values", zeroMethod.requiredStarts));

    switch (zeroMethod) {
      case NEWTON_METHOD:
        return newtonMethod(expression, rounds, start[0]);
      case SECANT_METHOD:
        return secantMethod(expression, rounds, start[0], start[1]);

      default:
        throw new RuntimeException();

    }


  }




  /* Tool(s) */

  private static void argCheck(Object o) {
    if ( o == null)
      throw new IllegalArgumentException("Argument cannot be null");
  }


  /**
   *
   * An enumeration for the zeroing methods
   * implemented in the JMath class
   *
   */
  public enum ZeroMethod {

    NEWTON_METHOD (1),
    SECANT_METHOD (2),

    ;

    private final int requiredStarts;

    ZeroMethod (int requiredStarts) {
      this.requiredStarts = requiredStarts;
    }

  }



}
