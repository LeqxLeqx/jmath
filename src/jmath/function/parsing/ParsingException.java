package jmath.function.parsing;

/**
 * Author:    LeqxLeqx
 */
public class ParsingException extends RuntimeException {

  ParsingException(FunctionParser parser, String message) {
    super(String.format("'%s': %s", parser.string, message));
  }

}
