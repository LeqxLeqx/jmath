package jmath.function.parsing;

import jmath.function.Evaluable;
import jmath.function.ValueType;
import jmath.types.Complex;
import jmath.types.IMatrix;
import jmath.types.IVector;

/**
 * Author:    LeqxLeqx
 */
class Element {


  final Object value;
  final ElementType type;

  public Element(Object val, ElementType type) {
    this.value = val;
    this.type = type;
  }
  public Element(ElementType type) {
    this.value = null;
    this.type = type;
  }

  public ValueType getLiteralType() {
    if (type != ElementType.LITERAL)
      throw new RuntimeException();
    else {

      if (value instanceof Double)
        return ValueType.REAL;
      else if (value instanceof Complex)
        return ValueType.COMPLEX;
      else if (value instanceof IVector)
        return ValueType.VECTOR;
      else if (value instanceof IMatrix)
        return ValueType.MATRIX;
      else
        throw new RuntimeException();
    }
  }


  public String asString() {
    return (String) value;
  }
  public double asDouble() {
    return (Double) value;
  }
  public Complex asComplex() {
    return (Complex) value;
  }
  public IVector asVector() {
    return (IVector) value;
  }
  public IMatrix asMatrix() {
    return (IMatrix) value;
  }
  public Evaluable asEvaluable() {
    return (Evaluable) value;
  }

  @Override
  public String toString() {
    if (value == null)
      return String.format("%s", type);
    else
      return String.format("%s:%s", type, value);
  }


}
