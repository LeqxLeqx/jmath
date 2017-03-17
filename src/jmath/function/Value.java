package jmath.function;

/**
 * Author:    LeqxLeqx
 */
public class Value <T> implements Evaluable {

  public static Value ZERO = new Value<>(0.0);

  public final ValueType type;
  private final Object contents;

  public Value(T value) {
    if (value == null)
      throw new IllegalArgumentException("Value argument cannot be null");
    type = ValueType.getValueType(value);
    contents = value;
  }


  public T getValue() {
    return (T) contents;
  }

  @Override
  public Value<T> clone() {
    return new Value<>( (T) contents);
  }

  @Override
  public Value getValue(ArgumentList argList) {
    return this;
  }

  @Override
  public EvaluableType getEvaluableType() {
    return EvaluableType.LITERAL;
  }

  @Override
  public Evaluable derivative(String variable) {
    return ZERO;
  }

  @Override
  public boolean isZero() {
    return (type == ValueType.REAL && ((Double) contents) == 0);
  }

  @Override
  public boolean isOne() {
    return (type == ValueType.REAL && ((Double) contents) == 1);
  }

  @Override
  public Evaluable replaceVariable(String varName, Evaluable evaluable) {
    return this;
  }

  @Override
  public String toString() {
    return contents.toString();
  }
}
