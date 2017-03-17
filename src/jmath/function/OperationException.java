package jmath.function;

/**
 * Author:    LeqxLeqx
 */
public class OperationException extends RuntimeException {

  public final OperationType type;

  public OperationException(OperationType ot, String message, Object... params) {
    super(String.format(message, params));
    this.type = ot;
  }

}
