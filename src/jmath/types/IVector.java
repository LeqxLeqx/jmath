package jmath.types;

/**
 * Author:    LeqxLeqx
 */
public abstract class IVector extends MObject {


  static void checkDimensions(IVector[] vectors, int dim) {
    for(int k = 0; k < vectors.length; k++) {
      if (vectors[k].dimension() != dim)
        throw new IllegalArgumentException("Vector array elements must have consistent dimensions");
    }
  }

  static void checkDimensions(IVector[] vectors) {
    int dim = vectors[0].dimension();
    for(int k = 1; k < vectors.length; k++) {
      if (vectors[k].dimension() != dim)
        throw new IllegalArgumentException("Vector array elements must have consistent dimensions");
    }
  }

  IVector() {
    super(Type.VECTOR);
  }

  public abstract int dimension();

  public abstract double get(int k);

  public abstract double getX();
  public abstract double getY();
  public abstract double getZ();


  public abstract double[] toArray();


  // Unary operations

  public abstract IVector negative();
  public abstract IVector normalize();
  public abstract double magnitude();
  public abstract double absoluteValue();
  public abstract double squareAbsoluteValue();

  public abstract boolean equals(IVector vector);

  // Binary operations

  public abstract IVector scale(double s);
  public abstract IVector sub(IVector vector);
  public abstract IVector sub(IVector... vectors);
  public abstract IVector subtract(IVector vector);
  public abstract IVector subtract(IVector... vectors);

  public abstract IVector cross(IVector a);



  public abstract NVector toNVector();
  public abstract Vector toVector();

}
