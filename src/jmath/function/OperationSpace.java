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

import java.util.LinkedList;

/**
 * A class whose objects represent
 * an available set of operations
 */
public class OperationSpace {

  private static void addDefaultFunctions(Namespace n) {

    /* Binary functions */
    n.registerFunction(new Function("max", OperationFactory.max(new Variable("x"), new Variable("y")), new String[]{ "x", "y" }));
    n.registerFunction(new Function("min", OperationFactory.min(new Variable("x"), new Variable("y")), new String[]{ "x", "y" }));

    /* Unary-Misc functions */
    n.registerFunction(new Function("abs", OperationFactory.abs(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("sgn", OperationFactory.sgn(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("floor", OperationFactory.floor(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("ceil", OperationFactory.ceil(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("round", OperationFactory.round(new Variable("x")), new String[]{ "x" }));

    n.registerFunction(new Function("log", OperationFactory.log(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("log10", OperationFactory.log10(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("log2", OperationFactory.log2(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("ln", OperationFactory.log(new Variable("x")), new String[]{ "x" }));

    /* Trigonometric functions */
    n.registerFunction(new Function("sin", OperationFactory.sin(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("cos", OperationFactory.cos(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("tan", OperationFactory.tan(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("cot", OperationFactory.cot(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("sec", OperationFactory.sec(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("csc", OperationFactory.csc(new Variable("x")), new String[]{ "x" }));

    /* Inverse-Trigonometric functions */
    n.registerFunction(new Function("asin", OperationFactory.asin(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("acos", OperationFactory.acos(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("atan", OperationFactory.atan(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("acot", OperationFactory.acot(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("asec", OperationFactory.asec(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("acsc", OperationFactory.acsc(new Variable("x")), new String[]{ "x" }));

    /* Hyperbolic functions */
    n.registerFunction(new Function("sinh", OperationFactory.sinh(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("cosh", OperationFactory.cosh(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("tanh", OperationFactory.tanh(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("coth", OperationFactory.coth(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("sech", OperationFactory.sech(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("csch", OperationFactory.csch(new Variable("x")), new String[]{ "x" }));

    /* Inverse-Hyperbolic functions */
    n.registerFunction(new Function("asinh", OperationFactory.sinh(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("acosh", OperationFactory.cosh(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("atanh", OperationFactory.tanh(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("acoth", OperationFactory.coth(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("asech", OperationFactory.sech(new Variable("x")), new String[]{ "x" }));
    n.registerFunction(new Function("acsch", OperationFactory.csch(new Variable("x")), new String[]{ "x" }));


  }


  private LinkedList<Namespace> namespaces = new LinkedList<>();

  public OperationSpace() {
    Namespace namespace = new Namespace("");
    namespaces.add( namespace );

    addDefaultFunctions(namespace);
  }

  private Namespace getNamespace(String name) {
    for(Namespace n : namespaces) {
      if (n.name.equals(name))
        return n;
    }

    return null;
  }

  public void add(String name, Evaluable operation, String[] argumentSpecification) {
    add("", name, operation, argumentSpecification);
  }
  public void add(String namespace, String name, Evaluable operation, String[] argumentSpecification) {
    if (namespace == null)
      namespace = "";
    if (name == null)
      throw new IllegalArgumentException("Name cannot be null");
    if (operation == null)
      throw new IllegalArgumentException("Operation cannot be null");
    add(namespace, new Function(name, operation, argumentSpecification));
  }
  public void add(Function function) {
    add("", function);
  }
  public void add(String namespace, Function function) {
    if (namespace == null)
      namespace = "";
    if (function == null)
      throw new IllegalArgumentException("Function cannot be null");

    Namespace n = getNamespace(namespace);

    if (n == null) {
      n = new Namespace(namespace);
      namespaces.add(n);
    }

    n.registerFunction(function);
  }

  public Function getFunction(String namespace, String funcName) {

    Namespace n = getNamespace(namespace);

    if (n == null)
      return null;
    else
      return n.getFunction(funcName);
  }

  public Function getFunction(String funcName) {
    Namespace n = getNamespace("");

    return n.getFunction(funcName);
  }


  public Function[] listFunctions() {

    LinkedList<Function> functions = new LinkedList<>();
    for(Namespace namespace : namespaces) {
      for(Function f : namespace.functions) {
        functions.add(f);
      }
    }

    return functions.toArray(new Function[functions.size()]);
  }

  public Function[] listFunctions(String namespace) {
    Namespace n = getNamespace(namespace);

    if (n == null)
      return new Function[0];

    else
      return n.functions.toArray(new Function[n.functions.size()]);
  }

  public String[] listNamspaces() {
    String[] ret = new String[namespaces.size()];
    for(int k =0 ; k < ret.length; k++) {
      ret[k] = namespaces.get(k).name;
    }

    return ret;
  }


}
