package jmath.function;

import jmath.function.parsing.FunctionParser;

import java.util.LinkedList;

/**
 * Author:    LeqxLeqx
 */
public abstract class Operation implements Evaluable {


  public static Evaluable parse(String string) {
    return FunctionParser.parseEvaluable(string);
  }



  static boolean isOrContainsVariables(Evaluable e) {

    switch(e.getEvaluableType()) {
      case LITERAL:
        return false;
      case VARIABLE:
        return true;
      case OPERATION:
        return ((Operation) e).containsVariables();
      default:
        throw new RuntimeException(); // Should not happen
    }
  }

  static boolean isOrContainsVariable(Evaluable e, String name) {

    switch(e.getEvaluableType()) {
      case LITERAL:
        return false;
      case VARIABLE:
        if (((Variable) e).name.equals(name))
          return true;
        else
          return false;
      case OPERATION:
        return ((Operation) e).containsVariable(name);
      default:
        throw new RuntimeException(); // Should not happen
    }
  }

  static void addToVariableList(LinkedList<Variable> variables, Evaluable e) {

    switch(e.getEvaluableType()) {
      case LITERAL:
        return;
      case VARIABLE:
        variables.add((Variable) e);
        return;
      case OPERATION:
        ((Operation) e).generateVariableList(variables);
        return;
      default:
        throw new RuntimeException(); // Should not happen
    }
  }

  static String getEvaluableString(Evaluable e) {
    switch(e.getEvaluableType()) {
      case LITERAL:
        return ((Value) e).getValue().toString();
      case VARIABLE:
        return ((Variable) e).name;
      case OPERATION:
        return e.toString();
      default:
        throw new RuntimeException(); // Should not happen
    }
  }




  public final OperationType type;

  public Operation (OperationType type) {
    if (type == null)
      throw new IllegalArgumentException("Operation type cannot be null");
    this.type = type;
  }

  public abstract Operation clone();
  public abstract Evaluable simplify();
  public abstract boolean containsVariables();
  public abstract boolean containsVariable(String name);
  abstract void generateVariableList(LinkedList<Variable> variables);

  public boolean isZero() {
    return false;
  }
  public boolean isOne() {
    return false;
  }

  @Override
  public EvaluableType getEvaluableType() {
    return EvaluableType.OPERATION;
  }


}
