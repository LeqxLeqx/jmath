package jmath.function;

/**
 * Author:    LeqxLeqx
 */
public enum EquationType {

  EQUAL                   ('='),
  GREATER_THAN            ('>'),
  LESS_THAN               ('<'),
  GREATER_THAN_OR_EQUAL   ('\u2265'),
  LESS_THAN_OR_EQUAL      ('\u2264'),

  ;

  public final char representation;

  EquationType(char rep) {
    representation = rep;
  }

}
