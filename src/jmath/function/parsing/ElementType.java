package jmath.function.parsing;

/**
 * Author:    LeqxLeqx
 */
enum ElementType {

  EVALUABLE,

  // Arithmetic
  ADD,
  SUB,
  MUL,
  DIV,
  MOD,
  POW,

  // Relative
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
