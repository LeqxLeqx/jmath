package jmath.types;

/**
 * Author:    LeqxLeqx
 */
public class MObject {

  public final Type type;

  MObject(Type type) {
    this.type = type;
  }

  public enum Type {

    VECTOR,
    MATRIX,
    COMPLEX_NUMBER,
    FIXED_POINT_NUMBER,

    ;

  }

}
