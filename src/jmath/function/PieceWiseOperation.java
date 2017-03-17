package jmath.function;

import java.util.LinkedList;

/**
 * Author:    LeqxLeqx
 */
public class PieceWiseOperation extends Operation {

  private final Evaluable argument1, argument2;
  private final Equation equation;

  public PieceWiseOperation(Evaluable e1, Evaluable e2, Equation equation) {
    super(OperationType.PIECE_WISE);

    argument1 = e1;
    argument2 = e2;
    this.equation = equation;

  }

  @Override
  public Operation clone() {
    return new PieceWiseOperation(argument1.clone(), argument2.clone(), equation.clone());
  }

  @Override
  public Evaluable simplify() {

    Evaluable e1, e2;

    if (Operation.isOrContainsVariables(argument1))
      e1 = argument1;
    else
      e1 = argument1.getValue(ArgumentList.NONE);

    if (Operation.isOrContainsVariables(argument2))
      e2 = argument2;
    else
      e2 = argument2.getValue(ArgumentList.NONE);


    if (equation.containsVariables()) {
      if (equation.evaluate(ArgumentList.NONE))
        return e1;
      else
        return e2;
    }
    else
      return new PieceWiseOperation(e1, e2, equation);
  }

  @Override
  public Value getValue(ArgumentList argList) {
    if (equation.evaluate(argList))
      return argument1.getValue(argList);
    else
      return argument2.getValue(argList);
  }

  @Override
  public Evaluable derivative(String variable) {
    return new PieceWiseOperation(
            argument1.derivative(variable),
            argument2.derivative(variable),
            equation
      );
  }

  @Override
  public Evaluable replaceVariable(String varName, Evaluable evaluable) {

    Evaluable e1, e2;
    Equation eq;

    if (Operation.isOrContainsVariable(argument1, varName))
      e1 = argument1.replaceVariable(varName, evaluable);
    else
      e1 = argument1;

    if (Operation.isOrContainsVariable(argument2, varName))
      e2 = argument2.replaceVariable(varName, evaluable);
    else
      e2 = argument2;

    if (equation.containsVariable(varName))
      eq = equation.replaceVariable(varName, evaluable);
    else
      eq = equation;

    return new PieceWiseOperation(e1, e2, eq);
  }

  @Override
  public boolean containsVariables() {
    return
            Operation.isOrContainsVariables(argument1) ||
            Operation.isOrContainsVariables(argument2) ||
            equation.containsVariables()
            ;
  }

  @Override
  public boolean containsVariable(String name) {
    return
            Operation.isOrContainsVariable(argument1, name) ||
            Operation.isOrContainsVariable(argument2, name) ||
            equation.containsVariable(name)
            ;
  }

  @Override
  void generateVariableList(LinkedList<Variable> variables) {
    Operation.addToVariableList(variables, argument1);
    Operation.addToVariableList(variables, argument2);
    equation.addToVariableList(variables);
  }

  @Override
  public String toString() {
    return String.format("{%s: %s, %s}", argument1, equation, argument2);
  }

}
