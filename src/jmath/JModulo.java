package jmath;

/**
 * A class whose instances represent a modulo space
 */
public class JModulo {


  public final double modulo;

  public JModulo(double mod) {
    if (mod <= 0)
      throw new IllegalArgumentException(String.format("%f is not a valid modulo", mod));

    modulo = mod;
  }


  public boolean isWithin(double value) {
    return value >= 0 && value < modulo;
  }

  /**
   * Returns the modulo of the given numerator
   * by the given denominator where the value is
   * guaranteed to be positive.
   *
   * @param n the numerator value
   * @return returns the positive modulo of 'n' within the modulo
   */
  public double mod(double n) {
    double ret = n % modulo;
    if (ret < 0)
      ret = ret + modulo;
    return ret;
  }


  public double add(double a, double b) {
    double c = a + b;
    if (!isWithin(c))
      return mod(c);
    else
      return c;
  }

  public double subtract(double a, double b) {
    return sub(a, b);
  }
  public double sub(double a, double b) {
    return add(a, -b);
  }

  public double multiply(double a, double b) {
    double c = a * b;
    if (!isWithin(c))
      return mod(c);
    else
      return c;
  }



  /**
   * Returns the distance between the given values
   * within the modulo space
   *
   * @param a first given value
   * @param b second given value
   * @return returns the modulo distance between a and b
   */
  public double distance(double a, double b) {
    double ret = JMath.abs(mod(a) - mod(b));
    return JMath.min(ret, modulo - ret);
  }


}
