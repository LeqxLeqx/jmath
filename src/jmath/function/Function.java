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
 * Author:    LeqxLeqx
 */
public class Function {

  private static boolean argumentSpecificationValid(String[] argSpec, Evaluable evaluable) {

    if (argSpec == null)
      return false;
    LinkedList<String> listCheck = new LinkedList<>();
    LinkedList<Variable> variableList = new LinkedList<>();
    Operation.addToVariableList(variableList, evaluable);

    for(String s : argSpec) {

      if (s == null)
        return false;
      if (listCheck.contains(s))
        return false;

      listCheck.add(s);
    }

    if (variableList.size() != listCheck.size())
      return false;

    for(Variable var : variableList) {
      if (!listCheck.contains(var.name))
        return false;
    }


    return true;
  }

  final String name;
  final Evaluable evaluable;
  private final String[] argumentSpecification;

  public Function(String name, Evaluable evaluable, String[] argumentSpecification) {

    if (name == null)
      throw new IllegalArgumentException("Cannot create function of null name");
    if (evaluable == null)
      throw new IllegalArgumentException("Cannot create function of null evaluable");
    if (!argumentSpecificationValid(argumentSpecification, evaluable))
      throw new IllegalArgumentException("Argument specification is not valid");

    this.name = name;
    this.evaluable = evaluable;
    this.argumentSpecification = argumentSpecification.clone();
  }

  public Evaluable deparameterize(Evaluable[] evaluables) {

    if (evaluables.length != argumentSpecification.length)
      throw new IllegalArgumentException("Invalid argument list length for de-parameterization");


    Evaluable ret = evaluable;

    for(int k = 0; k < evaluables.length; k++) {
      ret = ret.replaceVariable(argumentSpecification[k], evaluables[k]);
    }

    return ret;
  }

}
