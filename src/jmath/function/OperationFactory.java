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

package jmath.function;

import jmath.JMath;

/**
 * Author:    LeqxLeqx
 */
public /*static*/ class OperationFactory { private OperationFactory() {}


  private static OperationSpace opSpace = new OperationSpace();

  public static Evaluable get(String opName, Evaluable... evaluables) {
    return get("", opName, evaluables);
  }
  public static Evaluable get(String namespace, String opName, Evaluable... evaluables) {
    Function function = opSpace.getFunction(namespace, opName);
    if (function == null)
      throw new IllegalArgumentException(String.format("No such function: '%s'", opName));

    return function.deparameterize(evaluables);
  }

  public static void registerFunction(String funcName, String functionString, String[] argumentSpecification) {
    registerFunction("", funcName, functionString, argumentSpecification);
  }
  public static void registerFunction(String namespace, String funcName, String functionString, String[] argumentSpecification) {
    opSpace.add(namespace, funcName, Operation.parse(functionString), argumentSpecification);
  }

  public static OperationSpace getOpSpace() {
    return opSpace;
  }
  public static void setOpSpace(OperationSpace os) {
    if (os == null)
      os = new OperationSpace();

    opSpace = os;
  }




  // Base Functions

  public static Operation add(Evaluable... evaluables) {
    check(evaluables);
    return repeatedArithOp(evaluables, OperationType.ADDITION);
  }
  public static Operation sub(Evaluable... evaluables) {
    check(evaluables);
    return repeatedArithOp(evaluables, OperationType.SUBTRACTION);
  }
  public static Operation mul(Evaluable... evaluables) {
    check(evaluables);
    return repeatedArithOp(evaluables, OperationType.MULTIPLICATION);
  }
  public static Operation div(Evaluable... evaluables) {
    check(evaluables);
    return repeatedArithOp(evaluables, OperationType.DIVISION);
  }
  public static Operation pow(Evaluable... evaluables) {
    check(evaluables);
    return repeatedArithOp(evaluables, OperationType.EXPONENT);
  }

  public static Operation log(Evaluable e, Evaluable base) {
    check(e);
    check(base);
    return new ArithmeticOperation(OperationType.LOGARITHM, e, base);
  }
  public static Operation log(Evaluable e) {
    return log(e, new Value<>(JMath.E));
  }
  public static Operation log10(Evaluable e) {
    return log(e, new Value<>(10.0));
  }
  public static Operation log2(Evaluable e) {
    return log(e, new Value<>(2.0));
  }


  public static Operation max(Evaluable... evaluables) {
    check(evaluables);
    return repeatedBinOp(evaluables, OperationType.MAX);
  }
  public static Operation min(Evaluable... evaluables) {
    check(evaluables);
    return repeatedBinOp(evaluables, OperationType.MIN);
  }



  public static Operation abs(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.ABSOLUTE_VALUE, e);
  }
  public static Operation sgn(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.SIGNUM, e);
  }
  public static Operation floor(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.FLOOR, e);
  }
  public static Operation ceil(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.CEILING, e);
  }
  public static Operation round(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.ROUND, e);
  }



  public static Operation sin(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.SINE, e);
  }
  public static Operation cos(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.COSINE, e);
  }
  public static Operation tan(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.TANGENT, e);
  }
  public static Operation cot(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.COTANGENT, e);
  }
  public static Operation sec(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.SECANT, e);
  }
  public static Operation csc(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.COSECANT, e);
  }


  public static Operation asin(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.ARC_SINE, e);
  }
  public static Operation acos(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.ARC_COSINE, e);
  }
  public static Operation atan(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.ARC_TANGENT, e);
  }
  public static Operation acot(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.ARC_COTANGENT, e);
  }
  public static Operation asec(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.ARC_SECANT, e);
  }
  public static Operation acsc(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.ARC_COSECANT, e);
  }


  public static Operation sinh(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.HYPERBOLIC_SINE, e);
  }
  public static Operation cosh(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.HYPERBOLIC_COSINE, e);
  }
  public static Operation tanh(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.HYPERBOLIC_TANGENT, e);
  }
  public static Operation coth(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.HYPERBOLIC_COTANGENT, e);
  }
  public static Operation sech(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.HYPERBOLIC_SECANT, e);
  }
  public static Operation csch(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.HYPERBOLIC_COSECANT, e);
  }


  public static Operation asinh(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.ARC_HYPERBOLIC_SINE, e);
  }
  public static Operation acosh(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.ARC_HYPERBOLIC_COSINE, e);
  }
  public static Operation atanh(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.ARC_HYPERBOLIC_TANGENT, e);
  }
  public static Operation acoth(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.ARC_HYPERBOLIC_COTANGENT, e);
  }
  public static Operation asech(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.ARC_HYPERBOLIC_SECANT, e);
  }
  public static Operation acsch(Evaluable e) {
    check(e);
    return new UnaryOperation(OperationType.ARC_HYPERBOLIC_COSECANT, e);
  }





  // Convenience methods

  public static Operation squared(Evaluable e) {
    check(e);
    return new ArithmeticOperation(OperationType.EXPONENT, e, new Value<>(2.0));
  }
  public static Operation cubed(Evaluable e) {
    check(e);
    return new ArithmeticOperation(OperationType.EXPONENT, e, new Value<>(3.0));
  }

  public static Operation squareRoot(Evaluable e) {
    check(e);
    return new ArithmeticOperation(OperationType.EXPONENT, e, new Value<>(0.5));
  }
  public static Operation cubeRoot(Evaluable e) {
    check(e);
    return new ArithmeticOperation(OperationType.EXPONENT, e, new Value<>(1.0 / 3.0));
  }





  private static Operation repeatedArithOp(Evaluable[] evaluables, OperationType opType) {
    Operation ret = new ArithmeticOperation(opType, evaluables[0], evaluables[1]);

    for(int k = 2; k < evaluables.length; k++) {
      ret = new ArithmeticOperation(opType, ret, evaluables[k]);
    }

    return ret;
  }

  private static Operation repeatedBinOp(Evaluable[] evaluables, OperationType opType) {
    Operation ret = new BinaryOperation(opType, evaluables[0], evaluables[1]);

    for(int k = 2; k < evaluables.length; k++) {
      ret = new BinaryOperation(opType, ret, evaluables[k]);
    }

    return ret;
  }



  private static void check(Evaluable e) {
    if (e == null)
      throw new IllegalArgumentException("Evaluable cannot be null");
  }
  private static void check(Evaluable[] e) {
    if (e == null)
      throw new IllegalArgumentException("Evaluable array cannot be null");
    if (e.length < 2)
      throw new IllegalArgumentException("Evaluable array must be of length two or more");

    for(Evaluable es : e) {
      check(es);
    }
  }


}
