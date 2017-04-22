package jmath.types;

import jmath.JMath;

/**
 * Author:    LeqxLeqx
 */
public class Complex extends MObject {

  public static final Complex
                  ZERO = new Complex(0, 0),
                  I = new Complex(0, 1),
                  ONE = new Complex(1, 0)
                  ;


  public static Complex real(double d) {
    return new Complex(d, 0);
  }
  public static Complex imaginary(double d) {
    return new Complex(0, d);
  }

  public static Complex parse(String string) {
    if (string.contains("e^"))
      return parsePolarRepresentation(string);
    else
      return parseStandardRepresentation(string);
  }

  public static Complex parseStandardRepresentation(String string) {
    if (string == null)
      throw new IllegalArgumentException("Cannot parseEvaluable null string as complex number");
    if (string.length() == 0)
      throw new IllegalArgumentException(String.format("Cannot parseEvaluable '%s' as a complex number", string));

    int
      firstPlus = string.indexOf("+"),
      lastMinus = string.lastIndexOf("-"),
      pluses = Tools.numberOfOccurrences(string, '+'),
      minuses = Tools.numberOfOccurrences(string, '-'),
      split
              ;

    if (
            pluses + minuses > 2 || pluses > 1
            )
      throw new IllegalArgumentException(String.format("Cannot parseEvaluable '%s' as a complex number", string));


    if (pluses == 1)
      split = firstPlus;
    else if ((minuses == 1 && lastMinus != 0) || minuses == 2)
      split = lastMinus;
    else {
      // Non split-able

      double value;
      boolean imaginary;
      if (string.endsWith("value")) {
        imaginary = true;
        string = string.substring(0, string.length() - 1);
      }
      else
        imaginary = false;

      try {
        value = Double.parseDouble(string);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException(String.format("Cannot parseEvaluable '%s' as a complex number", string));
      }

      if (imaginary)
        return new Complex(0, value);
      else
        return new Complex(value, 0);

    }

    String
            start = string.substring(0, split),
            end = string.substring(split + 1, string.length());

    if (!end.endsWith("value"))
      throw new IllegalArgumentException(String.format("Cannot parseEvaluable '%s' as a complex number", string));

    end = end.substring(0, end.length() - 1);

    double r, i;

    try {
      r = Double.parseDouble(start);
      i = Double.parseDouble(end);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(String.format("Cannot parseEvaluable '%s' as a complex number", string));
    }

    return new Complex(r, i);
  }

  public static Complex parsePolarRepresentation(String string) {
    if (string == null)
      throw new IllegalArgumentException("Cannot parseEvaluable null string as complex number");

    String[] split = string.split("e\\^");
    if (split.length != 2)
      throw new IllegalArgumentException(String.format("Cannot parseEvaluable '%s' as a complex number", string));
    if (!split[1].endsWith("value"))
      throw new IllegalArgumentException(String.format("Cannot parseEvaluable '%s' as a complex number", string));

    split[1] = split[1].substring(0, split[1].length() - 1);

    double r, p;

    try {
      r = Double.parseDouble(split[0]);
      p = Double.parseDouble(split[1]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(String.format("Cannot parseEvaluable '%s' as a complex number", string));
    }

    return polar(r, p);
  }


  public static Complex polar(double r, double phi) {
    return new Complex(
            r * JMath.cos(phi),
            r * JMath.sin(phi)
      );
  }



  // Addition methods

  public static Complex add(Complex a, Complex b) {

    if (a == null || b == null)
      throw new IllegalArgumentException("Cannot add null");

    return new Complex(
            a.real + b.real,
            a.imaginary + b.imaginary
      );
  }
  public static Complex add(Complex a, double b) {

    if (a == null)
      throw new IllegalArgumentException("Cannot add null");

    return new Complex(
            a.real + b,
            a.imaginary
      );
  }
  public static Complex add(Complex... values) {

    if (values == null)
      throw new IllegalArgumentException("Cannot add null array");
    if (Tools.arrayContainsNulls(values))
      throw new IllegalArgumentException("Cannot add array containing nulls");

    double r = 0, i = 0;

    for(Complex c : values) {
      r += c.real;
      i += c.imaginary;
    }

    return new Complex(r, i);
  }


  // Multiplication methods

  public static Complex multiply(Complex a, Complex b) {

    if (a == null || b == null)
      throw new IllegalArgumentException("Cannot multiply null");

    return new Complex(
            a.real * b.real - a.imaginary * b.imaginary,
            a.imaginary * b.real + b.imaginary * a.real
      );
  }
  public static Complex multiply(Complex... values) {

    if (values == null)
      throw new IllegalArgumentException("Cannot multiply null array");
    if (Tools.arrayContainsNulls(values))
      throw new IllegalArgumentException("Cannot multiply array containing null");
    if (values.length == 0)
      return Complex.ONE;

    Complex ret = values[0];
    for(int k = 1; k < values.length; k++) {
      ret = multiply(ret, values[k]);
    }

    return ret;
  }




  public final double real, imaginary;

  public Complex(double real, double imaginary) {
    super(Type.COMPLEX_NUMBER);
    this.real = real;
    this.imaginary = imaginary;
  }

  // qualities

  public boolean isReal() {
    return imaginary == 0;
  }
  public boolean isImaginary() {
    return real == 0;
  }
  public boolean isZero() {
    return imaginary == 0 && real == 0;
  }
  public boolean isComplex() {
    return imaginary != 0 && real != 0;
  }


  // Unary operations

  public Complex negative() {
    return new Complex(-real, -imaginary);
  }

  public Complex conjugate() {
    return new Complex(real, -imaginary);
  }

  public double magnitude() { // Alias of absolute getValue
    return absoluteValue();
  }
  public double absoluteValue() {
    return JMath.sqrt(real * real + imaginary * imaginary);
  }

  public double rotation() {
    return JMath.atan2(imaginary, real);
  }


  // Subtraction methods

  public Complex subtract(Complex c) { // Alias of sub(Complex c)
    return sub(c);
  }
  public Complex subtract(Complex... values) { // Alias of sub(Complex... values)
    return sub(values);
  }
  public Complex sub(Complex c) {

    if (c == null)
      throw new IllegalArgumentException("Cannot subtract by null");

    return new Complex(
            real - c.real,
            imaginary - c.imaginary
      );
  }
  public Complex sub(Complex... values) {

    if (values == null)
      throw new IllegalArgumentException("Cannot subtract by null array");
    if (Tools.arrayContainsNulls(values))
      throw new IllegalArgumentException("Cannot subtract by array containing nulls");

    double real = this.real, imaginary = this.imaginary;

    for(Complex c : values) {
      real -= c.real;
      imaginary -= c.imaginary;
    }

    return new Complex(real, imaginary);
  }


  // Division methods

  public Complex divide(Complex v) { // Alias of div(Complex v)
    return div(v);
  }
  public Complex divide(Complex... values) { // Alias of div(Complex... values)
    return div(values);
  }
  public Complex div(Complex c) {

    if (c == null)
      throw new IllegalArgumentException("Cannot divide by null");

    double
        real = (this.real * c.real + this.imaginary * c.imaginary) / (c.real * c.real + c.imaginary * c.imaginary),
        imaginary = (this.imaginary * c.real - this.real * c.imaginary) / (c.real * c.real + c.imaginary * c.imaginary)
      ;

    return new Complex(real, imaginary);
  }
  public Complex div(Complex... values) {

    if (values == null)
      throw new IllegalArgumentException("Cannot divide by null array");
    if (Tools.arrayContainsNulls(values))
      throw new IllegalArgumentException("Cannot divide by array containing null");


    Complex ret = this;

    for (Complex value : values) {
      ret = div(ret, value);
    }

    return ret;
  }



  // Exponent methods

  public Complex squared() {
    return multiply(this, this);
  }

  public Complex pow(double i) {
    return power(i);
  }
  public Complex pow(Complex c) {
    return power(c);
  }
  public Complex power(double i) {
    if (i == 0)
      return Complex.ONE;
    else if (i == 1)
      return this;
    else if (i == 2)
      return this.squared();
    else {

      return polar(
              JMath.pow(absoluteValue(), i),
              rotation() * i
        );

    }
  }
  public Complex power(Complex complex) {
    return multiply(
          real(JMath.pow(absoluteValue(), complex.real)),
          polar(
              1,
              JMath.ln(absoluteValue()) * complex.imaginary + rotation() * complex.real
          ).div(
              real(JMath.exp(rotation() * complex.imaginary))
          )
        );
  }

  public Complex primaryRoot(int i) {
    if (i == 0)
      throw new IllegalArgumentException("Cannot take the 0th root of a complex getValue");
    else
      return power(1 / i);
  }

  public Complex[] root(int i) {
    if (i == 0)
      throw new IllegalArgumentException("Cannot take the 0th root of a complex getValue");
    else if (i < 0)
      return ONE.div(this).root(-i);
    else {
      Complex[] ret = new Complex[i];

      double
              rootMagnitude = JMath.pow(absoluteValue(), (double) 1 / i),
              baseRotation = rotation() / i
                      ;

      for(int k = 0; k < i; k++) {
        ret[k] = polar(
                rootMagnitude,
                baseRotation + 2 * k * JMath.PI / i
            );
      }

      return ret;
    }
  }




  @Override
  public String toString() {

    if (isZero())
      return String.format("%s", 0);
    if (real == 0)
      return String.format("%si", imaginary);
    else if (imaginary == 0)
      return String.format("%s", real);

    if (imaginary < 0)
      return String.format("%s%si", real, imaginary);
    else
      return String.format("%s+%si", real, imaginary);
  }

  public String toPolarString() {
    return String.format("%se^%si", absoluteValue(), rotation());
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Complex)
      return equals((Complex) o);
    else
      return false;
  }

  public boolean equals(Complex c) {
    return c.real == real && c.imaginary == imaginary;
  }

}
