package jmath.function;

import jmath.function.parsing.FunctionParser;

import java.util.LinkedList;

/**
 * Author:    LeqxLeqx
 */
public class Equation {

  public static Equation parse(String string) {
    return FunctionParser.parseEquation(string);
  }

  private Expression left, right;
  public final EquationType type;

  public Equation(EquationType equationType, Expression left, Expression right) {

    if (equationType == null)
      throw new IllegalArgumentException("Equation type cannot be null");
    if (left == null || right == null)
      throw new IllegalArgumentException("Cannot equate nulls");

    type = equationType;
    this.left = left;
    this.right = right;
  }

  public Equation clone() {
    return new Equation(type, left.clone(), right.clone());
  }

  public boolean evaluate(ArgumentList argList) {

    Value
      leftValue = left.evaluate(argList.getValuesFromArgumentList(left.variableList())),
      rightValue = right.evaluate(argList.getValuesFromArgumentList(right.variableList()))
      ;

    if (
        (leftValue.type != ValueType.REAL ||
        rightValue.type != ValueType.REAL) &&
        type != EquationType.EQUAL
            )
      throw new RuntimeException(String.format("Cannot compare non-ordable types %s and %s in type %s", leftValue.type, rightValue.type, type));

    else if (leftValue.type == ValueType.REAL && rightValue.type == ValueType.REAL) {
      double
        l = (Double) leftValue.getValue(),
        r = (Double) rightValue.getValue();

      switch (type) {

        case EQUAL:
          return l == r;
        case GREATER_THAN:
          return l > r;
        case LESS_THAN:
          return l < r;
        case GREATER_THAN_OR_EQUAL:
          return l >= r;
        case LESS_THAN_OR_EQUAL:
          return l <= r;

        default:
          throw new RuntimeException();

      }
    }
    else // assumed to be type == EQUAL
      return leftValue.getValue().equals(rightValue.getValue());

  }

  boolean containsVariables() {
    return left.containsVariables() || right.containsVariables();
  }

  boolean containsVariable(String name) {
    return left.containsVariable(name) || right.containsVariable(name);
  }

  Equation replaceVariable(String varName, Evaluable evaluable) {

    Expression l, r;

    if (left.containsVariable(varName))
      l = left.replaceVariable(varName, evaluable);
    else
      l = left;

    if (right.containsVariable(varName))
      r = right.replaceVariable(varName, evaluable);
    else
      r = right;

    return new Equation(type, l, r);
  }

  void addToVariableList(LinkedList<Variable> variables) {
    Operation.addToVariableList(variables, left.evaluable);
    Operation.addToVariableList(variables, right.evaluable);
  }

  @Override
  public String toString() {
    return String.format("%s %s %s", left, type.representation, right);
  }


}
