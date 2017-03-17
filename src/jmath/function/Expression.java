package jmath.function;

import jmath.types.Complex;
import jmath.types.IMatrix;
import jmath.types.IVector;

import java.util.LinkedList;
import java.util.List;

/**
 * Author:    LeqxLeqx
 */
public class Expression {

  public static Expression parse(String string) {
    Evaluable op = Operation.parse(string);
    return new Expression(op);
  }


  private static LinkedList<VarGroup> generateVarGroups(Operation o) {
    LinkedList<VarGroup> ret = new LinkedList<>();
    LinkedList<Variable> variables = new LinkedList<>();

    o.generateVariableList(variables);

    for(Variable v : variables) {
      addVariableToVarGroupList(ret, v);
    }

    return ret;
  }

  private static LinkedList<VarGroup> generateVarGroups(Variable v) {
    LinkedList<VarGroup> ret = new LinkedList<>();

    ret.add(new VarGroup(v));

    return ret;
  }


  private static void addVariableToVarGroupList(List<VarGroup> vgs, Variable var) {
    for(VarGroup vg : vgs) {
      if (vg.name.equals(var.name)) {
        vg.add(var);
        return;
      }
    }

    vgs.add(new VarGroup(var));
  }



  private static void orderVarGroups(LinkedList<VarGroup> varGroups) {
    switch(varGroups.size()) {

      case 0:
      case 1:
        return;

      case 2:
        if (varGroups.get(0).name.compareTo(varGroups.get(1).name) > 0)
          varGroups.add(varGroups.remove(0)); // should reverse order
        return;

      default:

        VarGroup pivot = varGroups.get(0);
        LinkedList<VarGroup> before = new LinkedList<>(), after = new LinkedList<>();

        for(int k = 1 ; k < varGroups.size(); k++) {
          VarGroup v = varGroups.get(k);

          if (pivot.name.compareTo(v.name) < 0)
            after.add(v);
          else
            before.add(v);
        }

        orderVarGroups(before);
        orderVarGroups(after);

        varGroups.clear();

        varGroups.addAll(before);
        varGroups.add(pivot);
        varGroups.addAll(after);

    }
  }

  private static Value[] generateValueList(Object[] objects) {
    Value[] ret = new Value[objects.length];

    for(int k = 0; k < ret.length; k++) {
      Object o = objects[k];
      if (o instanceof Float)
        o = ((Float) o).doubleValue();
      else if (o instanceof Long)
        o = ((Long) o).doubleValue();
      else if (o instanceof Integer)
        o = ((Integer) o).doubleValue();
      else if (o instanceof Short)
        o = ((Short) o).doubleValue();

      ret[k] = new Value<>(o);

    }

    return ret;
  }

  private static boolean isObjectListValid(Object[] objects) {
    if (objects == null)
      return false;
    for(Object o : objects) {
      if (o == null)
        return false;
      if (
          !(o instanceof Double) &&
          !(o instanceof Float) &&
          !(o instanceof Long) &&
          !(o instanceof Integer) &&
          !(o instanceof Short) &&

          !(o instanceof Complex) &&
          !(o instanceof IVector) &&
          !(o instanceof IMatrix)
              )
        return false;
    }
    return true;
  }

  final Evaluable evaluable;
  private final LinkedList<VarGroup> varGroups;

  public Expression(Evaluable evaluable) {

    if (evaluable == null)
      throw new IllegalArgumentException("Evaluable cannot be null");

    this.evaluable = evaluable;
    switch (evaluable.getEvaluableType()) {

      case OPERATION:
        this.varGroups = generateVarGroups((Operation) evaluable);
        break;

      case VARIABLE:
        this.varGroups = generateVarGroups((Variable) evaluable);
        break;

      case LITERAL:
        this.varGroups = new LinkedList<>();
        break;

      default:
        throw new RuntimeException();
    }

    orderVarGroups(this.varGroups);
  }

  public Evaluable getEvaluable() {
    return evaluable.clone();
  }

  public Expression clone() {
    return new Expression(evaluable.clone()); // perhaps unnecessary work
  }

  public Expression derivativeWrtX() {
    return derivative("x");
  }
  public Expression derivativeWrtY() {
    return derivative("y");
  }
  public Expression derivative(char c) {
    return derivative(String.format("%s", c));
  }
  public Expression derivative(String variable) {
    return new Expression(evaluable.derivative(variable));
  }
  public Expression secondDerivative(String variable) {
    return derivative(variable).derivative(variable);
  }


  public Expression simpify() {
    if (evaluable.getEvaluableType() == EvaluableType.OPERATION)
      return new Expression(((Operation) evaluable).simplify());
    else
      return new Expression(evaluable);
  }


  public String[] variableList() {
    String[] ret = new String[varGroups.size()];

    for(int k = 0; k < ret.length; k++) {
      ret[k] = varGroups.get(k).name;
    }

    return ret;
  }

  public <T> T evaluate(double[] arguments) {
    if (arguments == null)
      throw new IllegalArgumentException("Argument list cannot be null");

    Object[] ret = new Object[arguments.length];
    for(int k = 0; k < ret.length; k++) {
      ret[k] = (Double) arguments[k];
    }

    return evaluate(ret);
  }

  public <T> T evaluate(Object... arguments) {
    if (arguments == null)
      throw new IllegalArgumentException("Argument list cannot be null");
    if (arguments.length != varGroups.size())
      throw new IllegalArgumentException("Argument list is mis-sized");

    String[] varList = variableList();

    String ret = "";
    for(int k = 0; k < arguments.length; k++) {
      if (arguments[k] == null)
        throw new IllegalArgumentException("Argument list cannot contain null");

      ret += String.format("%s = %s; ", varList[k], arguments[k]);
    }

    return evaluate(ArgumentList.create(ret));
  }
  public <T> T evaluate(ArgumentList argList) {
    if (argList == null)
      throw new IllegalArgumentException("Argument list is not valid when null");

    Object object;
    for(VarGroup vg : varGroups) {
      object = argList.getValueFromArgumentName(vg.name);
      if (object == null)
        throw new IllegalArgumentException(String.format("Cannot evaluate from given argument list as no assignment for '%s'", vg.name));

      vg.set(new Value<>(object));
    }

    Object o = evaluable.getValue(argList).getValue();
    try {
      return (T) o;
    } catch (ClassCastException e) {
      throw new EvaluationException("Return value of expression is not of the specified type.");
    }
  }

  boolean containsVariables() {
    return Operation.isOrContainsVariables(evaluable);
  }

  boolean containsVariable(String name) {
    return Operation.isOrContainsVariable(evaluable, name);
  }

  Expression replaceVariable(String varName, Evaluable rename) {
    if (Operation.isOrContainsVariable(this.evaluable, varName))
      return new Expression(this.evaluable.replaceVariable(varName, rename));
    else
      return new Expression(this.evaluable);
  }

  @Override
  public String toString() {
    return evaluable.toString();
  }

}
