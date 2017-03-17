package jmath.function;

/**
 * Author:    LeqxLeqx
 */
public enum OperationType {


  ADDITION (2, 1),
  SUBTRACTION (2, 1),

  MULTIPLICATION(2, 2), // Assumed to be inner product
  DIVISION(2, 2),

  EXPONENT (2, 3),
  LOGARITHM(2, 3),

  ABSOLUTE_VALUE (1, 10),
  SIGNUM (1, 10),
  FLOOR (1, 10),
  CEILING (1, 10),
  ROUND (1, 10),
  MAX (2, 10),
  MIN (2, 10),

  SINE (1, 10),
  COSINE (1, 10),
  TANGENT (1, 10),
  COTANGENT (1, 10),
  SECANT (1, 10),
  COSECANT (1, 10),

  HYPERBOLIC_SINE (1, 10),
  HYPERBOLIC_COSINE (1, 10),
  HYPERBOLIC_TANGENT (1, 10),
  HYPERBOLIC_COTANGENT (1, 10),
  HYPERBOLIC_SECANT (1, 10),
  HYPERBOLIC_COSECANT (1, 10),

  ARC_SINE (1, 10),
  ARC_COSINE (1, 10),
  ARC_TANGENT (1, 10),
  ARC_COTANGENT (1, 10),
  ARC_SECANT (1, 10),
  ARC_COSECANT (1, 10),

  ARC_HYPERBOLIC_SINE (1, 10),
  ARC_HYPERBOLIC_COSINE (1, 10),
  ARC_HYPERBOLIC_TANGENT (1, 10),
  ARC_HYPERBOLIC_COTANGENT (1, 10),
  ARC_HYPERBOLIC_SECANT (1, 10),
  ARC_HYPERBOLIC_COSECANT (1, 10),

  PIECE_WISE (2, 10),  // listed with argumentCount of 2, but must take an equation/inequality argument as well

  ;


  public final int argumentCount, operativeOrder;

  OperationType (int argumentCount, int operativeOrder) {
    this.argumentCount = argumentCount;
    this.operativeOrder = operativeOrder;
  }

}
