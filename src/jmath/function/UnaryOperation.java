package jmath.function;

import jmath.JMath;
import jmath.types.Complex;
import jmath.types.IMatrix;
import jmath.types.IVector;

import java.util.LinkedList;

/**
 * Author:    LeqxLeqx
 */
public class UnaryOperation extends Operation {

  private static boolean checkTypeConstraint(OperationType t) {
    return t.argumentCount == 1;
  }

  private Evaluable argument;

  public UnaryOperation(OperationType type, Evaluable argument) {
    super(type);

    if (!checkTypeConstraint(type))
      throw new IllegalArgumentException("Cannot create binary operation of type: " + type);
    if (argument == null)
      throw new IllegalArgumentException("Cannot operate on null argument");

    this.argument = argument;
  }

  @Override
  public Operation clone() {
    return new UnaryOperation(type, argument.clone());
  }

  @Override
  public Evaluable simplify() {
    if (Operation.isOrContainsVariables(argument))
      return this.clone();
    else
      return getValue(ArgumentList.NONE);
  }

  @Override
  public boolean containsVariables() {
    return Operation.isOrContainsVariables(argument);
  }

  @Override
  public boolean containsVariable(String name) {
    return Operation.isOrContainsVariable(argument, name);
  }

  @Override
  public Evaluable derivative(String variable) {

    switch(type) {

      case ABSOLUTE_VALUE:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                argument,
                new ArithmeticOperation(OperationType.DIVISION,
                  argument.derivative(variable),
                  new UnaryOperation(OperationType.ABSOLUTE_VALUE, argument)
                )
              );
      case SIGNUM:
        return Value.ZERO;
      case FLOOR:
        return Value.ZERO;
      case CEILING:
        return Value.ZERO;
      case ROUND:
        return Value.ZERO;

      case SINE:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                argument.derivative(variable),
                new UnaryOperation(OperationType.COSINE, argument)
              );
      case COSINE:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                new Value<>(-1.0),
                new ArithmeticOperation(OperationType.MULTIPLICATION,
                  argument.derivative(variable),
                  new UnaryOperation(OperationType.SINE, argument)
                )
              );
      case TANGENT:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                argument.derivative(variable),
                new ArithmeticOperation(OperationType.EXPONENT,
                  new UnaryOperation(OperationType.SECANT, argument),
                  new Value<>(2.0)
                )
              );
      case COTANGENT:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                new Value<>(-1.0),
                new ArithmeticOperation(OperationType.MULTIPLICATION,
                  argument.derivative(variable),
                  new ArithmeticOperation(OperationType.EXPONENT,
                    new UnaryOperation(OperationType.COSECANT, argument),
                    new Value<>(2.0)
                  )
                )
              );
      case SECANT:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                argument.derivative(variable),
                new ArithmeticOperation(OperationType.MULTIPLICATION,
                  new UnaryOperation(OperationType.TANGENT, argument),
                  new UnaryOperation(OperationType.SECANT, argument)
                )
              );
      case COSECANT:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                new Value<>(-1.0),
                new ArithmeticOperation(OperationType.MULTIPLICATION,
                  argument.derivative(variable),
                  new ArithmeticOperation(OperationType.MULTIPLICATION,
                    new UnaryOperation(OperationType.COTANGENT, argument),
                    new UnaryOperation(OperationType.COSECANT, argument)
                  )
                )
              );

      case ARC_SINE:
        return new ArithmeticOperation(OperationType.DIVISION,
                argument.derivative(variable),
                new ArithmeticOperation(OperationType.EXPONENT,
                  new ArithmeticOperation(OperationType.SUBTRACTION,
                    new Value<>(1.0),
                    new ArithmeticOperation(OperationType.EXPONENT,
                      argument,
                      new Value<>(2.0)
                    )
                  ),
                  new Value<>(0.5)
                )
              );
      case ARC_COSINE:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                new Value<>(-1.0),
                new ArithmeticOperation(OperationType.DIVISION,
                  argument.derivative(variable),
                  new ArithmeticOperation(OperationType.EXPONENT,
                    new ArithmeticOperation(OperationType.SUBTRACTION,
                      new Value<>(1.0),
                      new ArithmeticOperation(OperationType.EXPONENT,
                        argument,
                        new Value<>(2.0)
                      )
                    ),
                    new Value<>(0.5)
                  )
                )
              );
      case ARC_TANGENT:
        return new ArithmeticOperation(OperationType.DIVISION,
                argument.derivative(variable),
                new ArithmeticOperation(OperationType.ADDITION,
                  new ArithmeticOperation(OperationType.EXPONENT,
                    argument,
                    new Value<>(2.0)
                  ),
                  new Value<>(1.0)
                )
              );
      case ARC_COTANGENT:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                new Value<>(-1.0),
                new ArithmeticOperation(OperationType.DIVISION,
                  argument.derivative(variable),
                  new ArithmeticOperation(OperationType.ADDITION,
                    new ArithmeticOperation(OperationType.EXPONENT,
                      argument,
                      new Value<>(2.0)
                    ),
                    new Value<>(1.0)
                  )
                )
              );
      case ARC_SECANT:
        return new ArithmeticOperation(OperationType.DIVISION,
                argument.derivative(variable),
                new ArithmeticOperation(OperationType.MULTIPLICATION,
                  new ArithmeticOperation(OperationType.EXPONENT,
                    new ArithmeticOperation(OperationType.SUBTRACTION,
                      new Value<>(1.0),
                      new ArithmeticOperation(OperationType.DIVISION,
                        new Value<>(1.0),
                        new ArithmeticOperation(OperationType.EXPONENT,
                          argument,
                          new Value<>(2.0)
                        )
                      )
                    ),
                    new Value<>(0.5)
                  ),
                  new ArithmeticOperation(OperationType.EXPONENT,
                    argument,
                    new Value<>(2.0)
                  )
                )
              );
      case ARC_COSECANT:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                new Value<>(-1.0),
                new ArithmeticOperation(OperationType.DIVISION,
                  argument.derivative(variable),
                  new ArithmeticOperation(OperationType.MULTIPLICATION,
                    new ArithmeticOperation(OperationType.EXPONENT,
                      new ArithmeticOperation(OperationType.SUBTRACTION,
                        new Value<>(1.0),
                        new ArithmeticOperation(OperationType.DIVISION,
                          new Value<>(1.0),
                          new ArithmeticOperation(OperationType.EXPONENT,
                            argument,
                            new Value<>(2.0)
                          )
                        )
                      ),
                      new Value<>(0.5)
                    ),
                    new ArithmeticOperation(OperationType.EXPONENT,
                      argument,
                      new Value<>(2.0)
                    )
                  )
                )
              );


      case HYPERBOLIC_SINE:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                argument.derivative(variable),
                new UnaryOperation(OperationType.HYPERBOLIC_COSINE, argument)
        );
      case HYPERBOLIC_COSINE:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                new Value<>(-1.0),
                new ArithmeticOperation(OperationType.MULTIPLICATION,
                        argument.derivative(variable),
                        new UnaryOperation(OperationType.HYPERBOLIC_SINE, argument)
                )
        );
      case HYPERBOLIC_TANGENT:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                argument.derivative(variable),
                new ArithmeticOperation(OperationType.EXPONENT,
                        new UnaryOperation(OperationType.HYPERBOLIC_SECANT, argument),
                        new Value<>(2.0)
                )
        );
      case HYPERBOLIC_COTANGENT:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                new Value<>(-1.0),
                new ArithmeticOperation(OperationType.MULTIPLICATION,
                        argument.derivative(variable),
                        new ArithmeticOperation(OperationType.EXPONENT,
                                new UnaryOperation(OperationType.HYPERBOLIC_COSECANT, argument),
                                new Value<>(2.0)
                        )
                )
        );
      case HYPERBOLIC_SECANT:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                new Value<>(-1.0),
                  new ArithmeticOperation(OperationType.MULTIPLICATION,
                  argument.derivative(variable),
                  new ArithmeticOperation(OperationType.MULTIPLICATION,
                          new UnaryOperation(OperationType.HYPERBOLIC_TANGENT, argument),
                          new UnaryOperation(OperationType.HYPERBOLIC_SECANT, argument)
                  )
                )
              );
      case HYPERBOLIC_COSECANT:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                new Value<>(-1.0),
                new ArithmeticOperation(OperationType.MULTIPLICATION,
                        argument.derivative(variable),
                        new ArithmeticOperation(OperationType.MULTIPLICATION,
                                new UnaryOperation(OperationType.HYPERBOLIC_COTANGENT, argument),
                                new UnaryOperation(OperationType.HYPERBOLIC_COSECANT, argument)
                        )
                )
        );

      case ARC_HYPERBOLIC_SINE:
        return new ArithmeticOperation(OperationType.DIVISION,
                argument.derivative(variable),
                new ArithmeticOperation(OperationType.EXPONENT,
                  new ArithmeticOperation(OperationType.ADDITION,
                    new ArithmeticOperation(OperationType.EXPONENT,
                      argument,
                      new Value<>(2.0)
                    ),
                    new Value<>(1.0)
                  ),
                  new Value<>(0.5)
                )
              );
      case ARC_HYPERBOLIC_COSINE:
        return new ArithmeticOperation(OperationType.DIVISION,
                argument.derivative(variable),
                new ArithmeticOperation(OperationType.MULTIPLICATION,
                  new ArithmeticOperation(OperationType.EXPONENT,
                    new ArithmeticOperation(OperationType.SUBTRACTION,
                      argument,
                      new Value<>(1.0)
                    ),
                    new Value<>(0.5)
                  ),
                  new ArithmeticOperation(OperationType.EXPONENT,
                    new ArithmeticOperation(OperationType.ADDITION,
                      argument,
                      new Value<>(1.0)
                    ),
                    new Value<>(0.5)
                  )
                )
              );
      case ARC_HYPERBOLIC_TANGENT:
      case ARC_HYPERBOLIC_COTANGENT:
        return new ArithmeticOperation(OperationType.DIVISION,
                argument.derivative(variable),
                new ArithmeticOperation(OperationType.SUBTRACTION,
                  new Value<>(1.0),
                  new ArithmeticOperation(OperationType.EXPONENT,
                    argument,
                    new Value<>(2.0)
                  )
                )
              );
      case ARC_HYPERBOLIC_SECANT:
        return new ArithmeticOperation(OperationType.DIVISION,
                new ArithmeticOperation(OperationType.MULTIPLICATION,
                  new ArithmeticOperation(OperationType.EXPONENT,
                    new ArithmeticOperation(OperationType.DIVISION,
                      new ArithmeticOperation(OperationType.SUBTRACTION,
                        new Value<>(1.0),
                        argument
                      ),
                      new ArithmeticOperation(OperationType.ADDITION,
                        argument,
                        new Value<>(1.0)
                      )
                    ),
                    new Value<>(0.5)
                  ),
                  argument.derivative(variable)
                ),
                new ArithmeticOperation(OperationType.MULTIPLICATION,
                  new ArithmeticOperation(OperationType.SUBTRACTION,
                    argument,
                    new Value<>(0.5)
                  ),
                  argument
                )
              );
      case ARC_HYPERBOLIC_COSECANT:
        return new ArithmeticOperation(OperationType.MULTIPLICATION,
                new Value<>(-1.0),
                new ArithmeticOperation(OperationType.DIVISION,
                  argument.derivative(variable),
                  new ArithmeticOperation(OperationType.MULTIPLICATION,
                    new ArithmeticOperation(OperationType.EXPONENT,
                      new ArithmeticOperation(OperationType.ADDITION,
                        new ArithmeticOperation(OperationType.DIVISION,
                          new Value<>(1.0),
                          new ArithmeticOperation(OperationType.EXPONENT,
                            argument,
                            new Value<>(2.0)
                          )
                        ),
                        new Value<>(1.0)
                      ),
                      new Value<>(0.5)
                    ),
                    new ArithmeticOperation(OperationType.EXPONENT,
                      argument,
                      new Value<>(2.0)
                    )
                  )
                )
              );

      default:
        throw new RuntimeException();

    }

  }

  @Override
  public Evaluable replaceVariable(String varName, Evaluable evaluable) {
    Evaluable e;

    if (Operation.isOrContainsVariable(argument, varName))
      e = argument.replaceVariable(varName, evaluable);
    else
      e = argument;

    return new UnaryOperation(type, e);
  }

  @Override
  void generateVariableList(LinkedList<Variable> variables) {
    Operation.addToVariableList(variables, argument);
  }

  @Override
  public Value getValue(ArgumentList argList) {

    switch(type) {

      case ABSOLUTE_VALUE: return absoluteValue(argList);
      case SIGNUM: return signum(argList);
      case FLOOR: return floor(argList);
      case CEILING: return ceiling(argList);
      case ROUND: return round(argList);

      case SINE:
      case COSINE:
      case TANGENT:
      case COTANGENT:
      case SECANT:
      case COSECANT:
        return trig(argList);

      case ARC_SINE:
      case ARC_COSINE:
      case ARC_TANGENT:
      case ARC_COTANGENT:
      case ARC_SECANT:
      case ARC_COSECANT:
        return arcTrig(argList);

      case HYPERBOLIC_SINE:
      case HYPERBOLIC_COSINE:
      case HYPERBOLIC_TANGENT:
      case HYPERBOLIC_COTANGENT:
      case HYPERBOLIC_SECANT:
      case HYPERBOLIC_COSECANT:
        return hyperbolic(argList);

      case ARC_HYPERBOLIC_SINE:
      case ARC_HYPERBOLIC_COSINE:
      case ARC_HYPERBOLIC_TANGENT:
      case ARC_HYPERBOLIC_COTANGENT:
      case ARC_HYPERBOLIC_SECANT:
      case ARC_HYPERBOLIC_COSECANT:
        return arcHyperbolic(argList);

      default:
        throw new RuntimeException();

    }

  }


  private Value absoluteValue(ArgumentList argList) {
    Value v = argument.getValue(argList);

    switch (v.type) {

      case REAL:
        return new Value<>(JMath.abs((Double) v.getValue()));
      case COMPLEX:
        return new Value<>(JMath.abs((Complex) v.getValue()));
      case VECTOR:
        return new Value<>(JMath.abs((IVector) v.getValue()));
      case MATRIX:
        return new Value<>(((IMatrix) v.getValue()).determinant());

      default:
        throw new OperationException(type, "Cannot get absolute argument of type %s", v.type);
    }
  }
  private Value signum(ArgumentList argList) {
    Value v = argument.getValue(argList);

    switch (v.type) {

      case REAL:
        return new Value<>(JMath.signum((Double) v.getValue()));
      case COMPLEX:
        return new Value<>(JMath.signum((Complex) v.getValue()));
      case VECTOR:
        return new Value<>(JMath.signum((IVector) v.getValue()));
      case MATRIX:
        return new Value<>(JMath.signum((IMatrix) v.getValue()));

      default:
        throw new OperationException(type, "Cannot get signum of type %s", v.type);
    }

  }
  private Value floor(ArgumentList argList) {
    Value v = argument.getValue(argList);

    switch (v.type) {

      case REAL:
        return new Value<>(JMath.floor((Double) v.getValue()));
      case COMPLEX:
        return new Value<>(JMath.floor((Complex) v.getValue()));

      default:
        throw new OperationException(type, "Cannot get floor of type %s", v.type);

    }

  }
  private Value ceiling(ArgumentList argList){
    Value v = argument.getValue(argList);

    switch (v.type) {

      case REAL:
        return new Value<>(JMath.ceil((Double) v.getValue()));
      case COMPLEX:
        return new Value<>(JMath.ceil((Complex) v.getValue()));

      default:
        throw new OperationException(type, "Cannot get ceiling of type %s", v.type);

    }
  }
  private Value round(ArgumentList argList) {
    Value v = argument.getValue(argList);

    switch (v.type) {

      case REAL:
        return new Value<>(JMath.round((Double) v.getValue()));
      case COMPLEX:
        return new Value<>(JMath.round((Complex) v.getValue()));

      default:
        throw new OperationException(type, "Cannot get ceiling of type %s", v.type);

    }
  }

  private Value trig(ArgumentList argList) {
    Value v = argument.getValue(argList);
    Object ret;

    switch(v.type) {

      case REAL:
        switch(this.type) { //op type

          case SINE:
            ret = JMath.sin((Double) v.getValue());
            break;
          case COSINE:
            ret = JMath.cos((Double) v.getValue());
            break;
          case TANGENT:
            ret = JMath.tan((Double) v.getValue());
            break;
          case COTANGENT:
            ret = JMath.cot((Double) v.getValue());
            break;
          case SECANT:
            ret = JMath.sec((Double) v.getValue());
            break;
          case COSECANT:
            ret = JMath.csc((Double) v.getValue());
            break;

          default:
            throw new RuntimeException();
        }
        break;

      case COMPLEX:
        switch(this.type) { //op type

          case SINE:
            ret = JMath.sin((Complex) v.getValue());
            break;
          case COSINE:
            ret = JMath.cos((Complex) v.getValue());
            break;
          case TANGENT:
            ret = JMath.tan((Complex) v.getValue());
            break;
          case COTANGENT:
            ret = JMath.cot((Complex) v.getValue());
            break;
          case SECANT:
            ret = JMath.sec((Complex) v.getValue());
            break;
          case COSECANT:
            ret = JMath.csc((Complex) v.getValue());
            break;

          default:
            throw new RuntimeException();
        }
        break;

      default:
        throw new OperationException(type, "Cannot get %s of type %s", this.type, v.type);


    }

    return new Value<>(ret);
  }

  private Value arcTrig(ArgumentList argList) {
    Value v = argument.getValue(argList);
    Object ret;

    switch(v.type) {

      case REAL:
        switch(this.type) { //op type

          case ARC_SINE:
            ret = JMath.asin((Double) v.getValue());
            break;
          case ARC_COSINE:
            ret = JMath.acos((Double) v.getValue());
            break;
          case ARC_TANGENT:
            ret = JMath.atan((Double) v.getValue());
            break;
          case ARC_COTANGENT:
            ret = JMath.acot((Double) v.getValue());
            break;
          case ARC_SECANT:
            ret = JMath.asec((Double) v.getValue());
            break;
          case ARC_COSECANT:
            ret = JMath.acsc((Double) v.getValue());
            break;

          default:
            throw new RuntimeException();
        }
        break;

      case COMPLEX:
        switch(this.type) { //op type

          case ARC_SINE:
            ret = JMath.asin((Complex) v.getValue());
            break;
          case ARC_COSINE:
            ret = JMath.acos((Complex) v.getValue());
            break;
          case ARC_TANGENT:
            ret = JMath.atan((Complex) v.getValue());
            break;
          case ARC_COTANGENT:
            ret = JMath.acot((Complex) v.getValue());
            break;
          case ARC_SECANT:
            ret = JMath.asec((Complex) v.getValue());
            break;
          case ARC_COSECANT:
            ret = JMath.acsc((Complex) v.getValue());
            break;

          default:
            throw new RuntimeException();
        }
        break;

      default:
        throw new OperationException(type, "Cannot get %s of type %s", this.type, v.type);


    }

    return new Value<>(ret);
  }

  private Value hyperbolic(ArgumentList argList) {
    Value v = argument.getValue(argList);
    Object ret;

    switch(v.type) {

      case REAL:
        switch(this.type) { //op type

          case HYPERBOLIC_SINE:
            ret = JMath.sinh((Double) v.getValue());
            break;
          case HYPERBOLIC_COSINE:
            ret = JMath.cosh((Double) v.getValue());
            break;
          case HYPERBOLIC_TANGENT:
            ret = JMath.tanh((Double) v.getValue());
            break;
          case HYPERBOLIC_COTANGENT:
            ret = JMath.coth((Double) v.getValue());
            break;
          case HYPERBOLIC_SECANT:
            ret = JMath.sech((Double) v.getValue());
            break;
          case HYPERBOLIC_COSECANT:
            ret = JMath.csch((Double) v.getValue());
            break;

          default:
            throw new RuntimeException();
        }
        break;

      case COMPLEX:
        switch(this.type) { //op type

          case HYPERBOLIC_SINE:
            ret = JMath.sinh((Complex) v.getValue());
            break;
          case HYPERBOLIC_COSINE:
            ret = JMath.cosh((Complex) v.getValue());
            break;
          case HYPERBOLIC_TANGENT:
            ret = JMath.tanh((Complex) v.getValue());
            break;
          case HYPERBOLIC_COTANGENT:
            ret = JMath.coth((Complex) v.getValue());
            break;
          case HYPERBOLIC_SECANT:
            ret = JMath.sech((Complex) v.getValue());
            break;
          case HYPERBOLIC_COSECANT:
            ret = JMath.csch((Complex) v.getValue());
            break;

          default:
            throw new RuntimeException();
        }
        break;

      default:
        throw new OperationException(type, "Cannot get %s of type %s", this.type, v.type);


    }

    return new Value<>(ret);
  }

  private Value arcHyperbolic(ArgumentList argList) {
    Value v = argument.getValue(argList);
    Object ret;

    switch(v.type) {

      case REAL:
        switch(this.type) { //op type

          case ARC_HYPERBOLIC_SINE:
            ret = JMath.asinh((Double) v.getValue());
            break;
          case ARC_HYPERBOLIC_COSINE:
            ret = JMath.acosh((Double) v.getValue());
            break;
          case ARC_HYPERBOLIC_TANGENT:
            ret = JMath.atanh((Double) v.getValue());
            break;
          case ARC_HYPERBOLIC_COTANGENT:
            ret = JMath.acoth((Double) v.getValue());
            break;
          case ARC_HYPERBOLIC_SECANT:
            ret = JMath.asech((Double) v.getValue());
            break;
          case ARC_HYPERBOLIC_COSECANT:
            ret = JMath.acsch((Double) v.getValue());
            break;

          default:
            throw new RuntimeException();
        }
        break;

      case COMPLEX:
        switch(this.type) { //op type

          case ARC_HYPERBOLIC_SINE:
            ret = JMath.asinh((Complex) v.getValue());
            break;
          case ARC_HYPERBOLIC_COSINE:
            ret = JMath.acosh((Complex) v.getValue());
            break;
          case ARC_HYPERBOLIC_TANGENT:
            ret = JMath.atanh((Complex) v.getValue());
            break;
          case ARC_HYPERBOLIC_COTANGENT:
            ret = JMath.acoth((Complex) v.getValue());
            break;
          case ARC_HYPERBOLIC_SECANT:
            ret = JMath.asech((Complex) v.getValue());
            break;
          case ARC_HYPERBOLIC_COSECANT:
            ret = JMath.acsch((Complex) v.getValue());
            break;

          default:
            throw new RuntimeException();
        }
        break;

      default:
        throw new OperationException(type, "Cannot get %s of type %s", this.type, v.type);


    }

    return new Value<>(ret);
  }


  @Override
  public String toString() {
    String initial;
    String string = Operation.getEvaluableString(argument);

    switch (type) {

      case ABSOLUTE_VALUE:
        return String.format("|%s|", string);
      case FLOOR:
        return String.format("%c%s%c", 0x230A, string, 0x230B);
      case CEILING:
        return String.format("%c%s%c", 0x2308, string, 0x2309);


      case SIGNUM:
        initial = "sgn";
        break;

      case ROUND:
        initial = "round";
        break;

      case SINE:
        initial = "sin";
        break;
      case COSINE:
        initial = "cos";
        break;
      case TANGENT:
        initial = "tan";
        break;
      case COTANGENT:
        initial = "cot";
        break;
      case SECANT:
        initial = "sec";
        break;
      case COSECANT:
        initial = "csc";
        break;

      case ARC_SINE:
        initial = "asin";
        break;
      case ARC_COSINE:
        initial = "acos";
        break;
      case ARC_TANGENT:
        initial = "atan";
        break;
      case ARC_COTANGENT:
        initial = "acot";
        break;
      case ARC_SECANT:
        initial = "asec";
        break;
      case ARC_COSECANT:
        initial = "acsc";
        break;

      case HYPERBOLIC_SINE:
        initial = "sinh";
        break;
      case HYPERBOLIC_COSINE:
        initial = "cosh";
        break;
      case HYPERBOLIC_TANGENT:
        initial = "tanh";
        break;
      case HYPERBOLIC_COTANGENT:
        initial = "coth";
        break;
      case HYPERBOLIC_SECANT:
        initial = "sech";
        break;
      case HYPERBOLIC_COSECANT:
        initial = "csch";
        break;

      case ARC_HYPERBOLIC_SINE:
        initial = "asinh";
        break;
      case ARC_HYPERBOLIC_COSINE:
        initial = "acosh";
        break;
      case ARC_HYPERBOLIC_TANGENT:
        initial = "atanh";
        break;
      case ARC_HYPERBOLIC_COTANGENT:
        initial = "acoth";
        break;
      case ARC_HYPERBOLIC_SECANT:
        initial = "asech";
        break;
      case ARC_HYPERBOLIC_COSECANT:
        initial = "acsch";
        break;

      default:
        throw new RuntimeException();


    }

    return String.format("%s(%s)", initial, string);
  }


}
