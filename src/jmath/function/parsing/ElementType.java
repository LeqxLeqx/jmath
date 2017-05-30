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

package jmath.function.parsing;

enum ElementType {

  EVALUABLE,

  /* Arithmetic */
  ADD,
  SUB,
  MUL,
  DIV,
  MOD,
  POW,

  /* Relative */
  EQUALS,
  GREATER_THAN,
  LESS_THAN,
  GREATER_THAN_OR_EQUAL,
  LESS_THAN_OR_EQUAL,


  FUNCTION (true, false),
  VARIABLE,
  LITERAL,

  OPEN_PARENTHETICAL (true, false),
  CLOSE_PARENTHETICAL (false, true),

  OPEN_BRACKET (true, false),
  CLOSE_BRACKET (false, true),

  OPEN_CEILING (true, false),
  CLOSE_CEILING (false, true),

  OPEN_FLOOR (true, false),
  CLOSE_FLOOR (false, true),

  BAR,
  COMMA,
  COLON,

  ;

  final boolean opening, closing;

  ElementType() {
    opening = false;
    closing = false;
  }

  ElementType (boolean o, boolean c) {
    opening = o;
    closing = c;
  }


}
