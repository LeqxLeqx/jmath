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
