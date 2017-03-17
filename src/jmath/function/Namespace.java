package jmath.function;

import java.util.LinkedList;

/**
 * Author:    LeqxLeqx
 */
class Namespace {

  final LinkedList<Function> functions = new LinkedList<>();
  final String name;

  public Namespace(String name) {
    this.name = name;
  }

  public void registerFunction(Function f) {

    deregisterFunction(f.name); // may exist
    functions.add(f);

  }

  public void deregisterFunction(String name) {

    for(int k = functions.size() - 1; k >= 0; k--) {

      if (functions.get(k).name.equals(name)) {
        functions.remove(k);
        return;
      }
    }
  }

  public Function getFunction(String string) {

    for(Function f : functions) {
      if (f.name.equals(string))
        return f;
    }

    return null;
  }

}
