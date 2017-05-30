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
