package jmath.function;

/**
 * Author:    LeqxLeqx
 */
public class Polynomial extends Expression{

  public static Evaluable polynomialOperation(String variable, double[] values) {
    if (values == null)
      throw new IllegalArgumentException("Cannot create polynomial from null array");

    Evaluable[] monomials = new Evaluable[values.length];

    for (int k = 0; k < monomials.length; k++) {
      switch (k) {

        case 0:
          monomials[k] = new Value<>(values[monomials.length - k - 1]);
          break;
        case 1:
          monomials[k] = OperationFactory.mul(new Value<>(values[monomials.length - k - 1]), new Variable(variable));
          break;
        default:
          monomials[k] = OperationFactory.mul(new Value<>(values[monomials.length - k - 1]), OperationFactory.pow(new Variable(variable), new Value<>((double) k)));
          break;

      }
    }

    switch (monomials.length) {
      case 0:
        return Value.ZERO;
      case 1:
        return monomials[0];
      default:
        return OperationFactory.add(monomials);
    }

  }


  private double[] coefficients;
  public final String variable;

  public Polynomial(String variable, double... values) {
    super(polynomialOperation(variable, values));

    coefficients = values.clone(); // checked in static polynomialOperation method
    this.variable = variable;
  }
  public Polynomial(double... values) {
    this("x", values);
  }

  @Override
  public Polynomial derivative(String variable) {

    if (coefficients.length == 0 || coefficients.length == 1 || !this.variable.equals(variable))
      return new Polynomial(this.variable, new double[0]);

    double[] ret = new double[coefficients.length - 1];

    for(int k = 0; k < ret.length; k++) {
      ret[k] = coefficients[k] * (ret.length - k);
    }

    return new Polynomial(this.variable, ret);
  }

  @Override
  public String toString() {
    if (coefficients.length == 0)
      return "0";

    String ret;
    if (coefficients[0] >= 0)
      ret = "";
    else
      ret = "-";

    for(int k = 0; k < coefficients.length; k++) {

      double coeff = coefficients[k];
      String sign;
      if (k < coefficients.length - 1)
        sign = coefficients[k + 1] >= 0 ? "+" : "-";
      else
        sign = "";

      if (k == coefficients.length - 1)
          ret += String.format("%s", Math.abs(coeff));
      else if (k == coefficients.length - 2)
          ret += String.format("%s*%s %s ", Math.abs(coeff), variable, sign);
      else
          ret += String.format("%s*%s^%s %s ", Math.abs(coeff), variable, coefficients.length - k - 1, sign);

    }

    return ret;
  }


}
