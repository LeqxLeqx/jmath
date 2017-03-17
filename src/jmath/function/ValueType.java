package jmath.function;

import jmath.types.*;

/**
 * Author:    LeqxLeqx
 */
public enum ValueType{

  REAL,
  COMPLEX,
  VECTOR,
  MATRIX,

  ;

  public static ValueType getValueType(Object value) {
    if (value instanceof Double)
      return ValueType.REAL;
    else if (value instanceof Complex)
      return ValueType.COMPLEX;
    else if (value instanceof IMatrix)
      return ValueType.MATRIX;
    else if (value instanceof IVector)
      return ValueType.VECTOR;
    else
      throw new IllegalArgumentException("Cannot get value type of type: " + value.getClass().getName());
  }

  public static ValueType getValueType(String value) {

    try {
      Complex complex = Complex.parse(value);
      if (complex.isReal())
        return REAL;
      else
        return COMPLEX;
    } catch (IllegalArgumentException e) {}

    try {
      NVector.parse(value);
      return VECTOR;
    } catch (IllegalArgumentException e) {}

    try {
      NMatrix.parse(value);
      return MATRIX;
    } catch (IllegalArgumentException e) {}

    return null;
  }

}
