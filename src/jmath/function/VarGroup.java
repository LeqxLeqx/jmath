package jmath.function;

import java.util.LinkedList;

/**
 * Author:    LeqxLeqx
 */
class VarGroup {

  private LinkedList<Variable> variables = new LinkedList<>();
  final String name;

  VarGroup(Variable var) {
    if (var == null)
      throw new IllegalArgumentException("var cannot be null");
    variables.add(var);
    name = var.name;
  }

  void add(Variable var) {
    if (var.name.equals(name))
      variables.add(var);
    else
      throw new IllegalArgumentException("var must share name of group");
  }

  void set(Value value) {
    if (value == null)
      throw new IllegalArgumentException("Value cannot be set to null");
    for(Variable v : variables) {
      v.setValue(value);
    }
  }



}
