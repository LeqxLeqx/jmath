package jmath;

import upsilon.tools.ArrayTools;
import jmath.types.Complex;
import jmath.types.Fixed;
import jmath.types.IVector;
import jmath.types.Vector;

/**
 * A static class for various statistical operations
 */
public /*static*/ class JStats { private JStats() {  }



  /**
   *
   * Returns the arithmetic average of the range of values provided
   *
   * @param values the values from which to calculate the average
   * @return the average of the provided values
   */
  public static double average(double... values) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");
    else if (values.length == 0)
      throw new IllegalArgumentException("Values array must contain at least one value");

    double total = 0;
    for(double v : values) {
      total += v;
    }

    return total / values.length;
  }

  /**
   *
   * Returns the arithmetic average of the range of values provided
   *
   * @param values the values from which to calculate the average
   * @return the average of the provided values
   */
  public static Complex average(Complex... values) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");
    if (ArrayTools.containsNull(values))
      throw new IllegalArgumentException("Values array cannot contain nulls");
    else if (values.length == 0)
      throw new IllegalArgumentException("Values array must contain at least one value");

    return Complex.add(values).div(Complex.real(values.length));
  }

  /**
   *
   * Returns the arithmetic average of the range of values provided
   *
   * @param values the values from which to calculate the average
   * @return the average of the provided values
   */
  public static Fixed average(Fixed... values) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");
    if (ArrayTools.containsNull(values))
      throw new IllegalArgumentException("Values array cannot contain nulls");
    else if (values.length == 0)
      throw new IllegalArgumentException("Values array must contain at least one value");


    return Fixed.add(values).div(new Fixed(values.length));
  }
  /**
   *
   * Returns the vector average of the range of Vectors provided
   *
   * @param values the Vectors from which to calculate the average
   * @return the average of the provided values
   */
  public static Vector average(Vector... values) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");
    if (ArrayTools.containsNull(values))
      throw new IllegalArgumentException("Values array cannot contain nulls");
    else if (values.length == 0)
      throw new IllegalArgumentException("Values array must contain at least one value");


    return Vector.add(values).scale(1.0 / values.length);
  }

  /**
   *
   * Returns the weighted arithmetic average of the values provided,
   * weighted by the weights provided. The weights to not necessarily
   * need to sum to one.
   *
   * @param values the values for the average
   * @param weight the weights assigned to the values
   * @return the calculated weighted average
   */
  public static double weightedAverage(double[] values, double[] weight) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");
    if (weight == null)
      throw new IllegalArgumentException("Weight array cannot be null");
    if (values.length != weight.length)
      throw new IllegalArgumentException(String.format("Array length mismatch (%d != %d)", values.length, weight.length));

    double weightTotal = JMath.cumSum(weight);
    double ret = 0;
    for(int k = 0; k < values.length; k++) {
      ret += values[k] * weight[k] / weightTotal;
    }

    return ret;
  }

  /**
   *
   * Returns the weighted arithmetic average of the values provided,
   * weighted by the weights provided. The weights to not necessarily
   * need to sum to one.
   *
   * @param values the values for the average
   * @param weight the weights assigned to the values
   * @return the calculated weighted average
   */
  public static Complex weightedAverage(Complex[] values, double[] weight) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");
    if (weight == null)
      throw new IllegalArgumentException("Weight array cannot be null");
    if (ArrayTools.containsNull(values))
      throw new IllegalArgumentException("Values array cannot contain nulls");
    if (values.length != weight.length)
      throw new IllegalArgumentException(String.format("Array length mismatch (%d != %d)", values.length, weight.length));

    double weightTotal = JMath.cumSum(weight);
    Complex ret = Complex.ZERO;
    for(int k = 0; k < values.length; k++) {
      ret = Complex.add(ret, Complex.multiply(values[k], Complex.real(weight[k])).div(Complex.real(weightTotal)));
    }

    return ret;
  }

  /**
   *
   * Returns the weighted arithmetic average of the values provided,
   * weighted by the weights provided. The weights to not necessarily
   * need to sum to one.
   *
   * @param values the values for the average
   * @param weight the weights assigned to the values
   * @return the calculated weighted average
   */
  public static Fixed weightedAverage(Fixed[] values, double[] weight) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");
    if (weight == null)
      throw new IllegalArgumentException("Weight array cannot be null");
    if (ArrayTools.containsNull(values))
      throw new IllegalArgumentException("Values array cannot contain nulls");
    if (values.length != weight.length)
      throw new IllegalArgumentException(String.format("Array length mismatch (%d != %d)", values.length, weight.length));

    double weightTotal = JMath.cumSum(weight);
    Fixed ret = Fixed.ZERO;
    for(int k = 0; k < values.length; k++) {
      ret = Fixed.add(ret, Fixed.mul(values[k], new Fixed(weight[k])).div(new Fixed(weightTotal)));
    }

    return ret;
  }

  /**
   *
   * Returns the weighted vector average of the values provided,
   * weighted by the weights provided. The weights to not necessarily
   * need to sum to one.
   *
   * @param values the values for the average
   * @param weight the weights assigned to the values
   * @return the calculated weighted average
   */
  public static Vector weightedAverage(Vector[] values, double[] weight) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");
    if (weight == null)
      throw new IllegalArgumentException("Weight array cannot be null");
    if (ArrayTools.containsNull(values))
      throw new IllegalArgumentException("Values array cannot contain nulls");
    if (values.length != weight.length)
      throw new IllegalArgumentException(String.format("Array length mismatch (%d != %d)", values.length, weight.length));

    double weightTotal = JMath.cumSum(weight);
    Vector ret = Vector.ZERO;
    for(int k = 0; k < values.length; k++) {
      ret = Vector.add(ret, values[k].scale(weight[k] / weightTotal));
    }

    return ret;
  }

  /**
   *
   * Calculates the variance of the values array
   *
   * @param values the array on which to calculate
   * @return the variance of the array
   */
  public static double variance(double... values) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");
    if (values.length == 0)
      throw new IllegalArgumentException("Array dimensions cannot be zero");


    double
      average = average(values),
      density = 1d / (values.length - 1)
              ; /* sample density */

    double ret = 0;
    for (int k = 0; k < values.length; k++) {
      ret += JMath.square(values[k] - average);
    }

    return density * ret;
  }


  /**
   *
   * Calculates the standard deviation of the values array.
   * This method is equivalent to taking the square root of
   * the result from jmath.JStats.variance(double... values)
   *
   * @param values the array on which to calculate
   * @return the standard deviation of the array
   *
   */
  public static double standardDeviation(double... values) {
    return JMath.sqrt(variance(values));
  }


  /**
   *
   * Calculates the covariance between the two provided values arrays.
   *
   * @param xValues the first values array
   * @param yValues the second values array
   * @return the covariance between the arrays
   */
  public static double covariance(double[] xValues, double[] yValues) {
    if (xValues == null)
      throw new IllegalArgumentException("xValues array cannot be null");
    if (yValues == null)
      throw new IllegalArgumentException("yValues array cannot be null");
    if (xValues.length != yValues.length)
      throw new IllegalArgumentException(String.format("Dimensional mismatch for covariance (%d != %d)", xValues.length, yValues.length));
    if (xValues.length == 0)
      throw new IllegalArgumentException("Array dimensions cannot be zero");

    double
      ret = 0,
      density = 1d / (xValues.length - 1),
      xAve = average(xValues),
      yAve = average(yValues)
              ;

    for (int k = 0; k < xValues.length; k++) {
      ret += (xValues[k] - xAve) * (yValues[k] - yAve);
    }

    return density * ret;
  }


  /**
   *
   * Calculates the covariance between the 'x' and 'y' values of
   * the provided vector array
   *
	 * @param <T> type of the values within the array

   * @param values the values array
	 * @return the covariance between the vectors
   */
  public static <T extends IVector> double covariance(T... values) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");
    if (ArrayTools.containsNull(values))
      throw new IllegalArgumentException("Values array cannot contain null");

    double[] xVals = new double[values.length], yVals = new double[values.length];

    for (int k = 0; k < values.length; k++) {

      T v = values[k];

      if (v.dimension() < 2)
        throw new IllegalArgumentException("Vectors must all have at least two elements");

      xVals[k] = v.getX();
      yVals[k] = v.getY();
    }

    return covariance(xVals, yVals);
  }



  /**
   *
   * Calculates the pearson correlation between the values
   *
   * @param xValues the first values array
   * @param yValues the second values array
   * @return the pearson 'r' correlation coefficient
   */
  public static double r(double[] xValues, double[] yValues) {
    return pearsonCorrelation(xValues, yValues);
  }

  /**
   *
   * Calculates the pearson correlation between the values
   *
   * @param xValues the first values array
   * @param yValues the second values array
   * @return the pearson 'r' correlation coefficient
   */
  public static double pearsonCorrelation(double[] xValues, double[] yValues) {
    if (xValues == null)
      throw new IllegalArgumentException("xValues array cannot be null");
    if (yValues == null)
      throw new IllegalArgumentException("yValues array cannot be null");
    if (xValues.length != yValues.length)
      throw new IllegalArgumentException(String.format("Dimensional mismatch for covariance (%d != %d)", xValues.length, yValues.length));
    if (xValues.length == 0)
      throw new IllegalArgumentException("Array dimensions cannot be zero");

    double
            top = 0,
            bottom1 = 0,
            bottom2 = 0,
            xAve = average(xValues),
            yAve = average(yValues)
                    ;

    for (int k = 0; k < xValues.length; k++) {

      top +=
              (xValues[k] - xAve) *
                      (yValues[k] - yAve);

      bottom1 += JMath.square(xValues[k] - xAve);
      bottom2 += JMath.square(yValues[k] - yAve);

    }

    return top / (JMath.sqrt(bottom1) * JMath.sqrt(bottom2));
  }

  /**
   *
   * Calculates the Pearson correlation between the values
   * of the vector array
   *
	 * @param <T> the type of values within the array
   * @param values the vector array
   * @return the Pearson 'r' correlation coefficient
   */
  public static <T extends IVector> double r(T... values) {
    return pearsonCorrelation(values);
  }

  /**
   *
   * Calculates the Pearson correlation between the values
   *
	 * @param <T> the type of values within the array
   * @param values the vector array
   * @return the Pearson 'r' correlation coefficient
   */
  public static <T extends IVector> double pearsonCorrelation(T... values) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");
    if (ArrayTools.containsNull(values))
      throw new IllegalArgumentException("Values array cannot contain null");

    double[] xVals = new double[values.length], yVals = new double[values.length];

    for (int k = 0; k < values.length; k++) {

      T v = values[k];

      if (v.dimension() < 2)
        throw new IllegalArgumentException("Vectors must all have at least two elements");

      xVals[k] = v.getX();
      yVals[k] = v.getY();

    }

    return pearsonCorrelation(xVals, yVals);
  }

}
