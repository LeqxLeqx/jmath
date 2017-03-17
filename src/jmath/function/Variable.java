package jmath.function;

/**
 * Author:    LeqxLeqx
 */
public class Variable implements Evaluable {

  public static boolean isNameValid(String name) {

    if (name.isEmpty())
      return false;
    if (!Character.isAlphabetic(name.charAt(0)) && name.charAt(0) != '_')
      return false;

    for(char c : name.toCharArray()) {

      if (
              !Character.isAlphabetic(c) &&
              !Character.isDigit(c) &&
              c != '_'
              )
        return false;

    }

    return true;
  }

  public final String name;
  private Value value;

  public Variable(String name, Value value) {
    if (value == null)
      throw new IllegalArgumentException("Initial getValue of variable cannot be set to null");
    if (name == null)
      throw new IllegalArgumentException("Name of variable cannot be set to null");
    if (!isNameValid(name))
      throw new IllegalArgumentException(String.format("Name of variable '%s' is not valid", name));
    this.name = name;
    this.value = value;
  }
  public Variable(String name, Object value) {
    if (value == null)
      throw new IllegalArgumentException("Initial getValue of variable cannot be set to null");
    if (name == null)
      throw new IllegalArgumentException("Name of variable cannot be set to null");
    if (!isNameValid(name))
      throw new IllegalArgumentException(String.format("Name of variable '%s' is not valid", name));
    this.name = name;
    this.value = new Value<>(value);
  }
  public Variable(String name) {
    if (name == null)
      throw new IllegalArgumentException("Name of variable cannot be set to null");
    if (!isNameValid(name))
      throw new IllegalArgumentException(String.format("Name of variable '%s' is not valid", name));
    this.name = name;
    this.value = Value.ZERO;
  }

  public void setValue(Value value) {
    if (value == null)
      throw new IllegalArgumentException("Cannot set getValue of variable to null");


    this.value = value;
  }

  @Override
  public Evaluable clone() {
    return new Variable(name, value);
  }

  @Override
  public Value getValue(ArgumentList argList) {
    return value.getValue(argList);
  }

  @Override
  public EvaluableType getEvaluableType() {
    return EvaluableType.VARIABLE;
  }

  @Override
  public Evaluable derivative(String variable) {
    return new Value<>(1.0);
  }

  @Override
  public boolean isZero() {
    return false;
  }

  @Override
  public boolean isOne() {
    return false;
  }

  @Override
  public Evaluable replaceVariable(String varName, Evaluable evaluable) {
    if (name.equals(varName))
      return evaluable;
    else
      return this;
  }

  @Override
  public String toString() {
    return String.format("%s", name);
  }

}
