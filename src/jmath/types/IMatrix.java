package jmath.types;

/**
 * Author:    LeqxLeqx
 */
public abstract class IMatrix extends MObject{

  IMatrix() {
    super(Type.MATRIX);
  }

  public abstract double get(int row, int column);
  public abstract IMatrix set(int row, int column, double value);
  public abstract int numberOfRows();
  public abstract int numberOfColumns();

  public abstract IVector[] toColumnVectors();
  public abstract IVector[] toRowVectors();


  public abstract IVector transform(IVector vector);


  public abstract Matrix toMatrix();
  public abstract NMatrix toNMatrix();


  // Unary operations

  public abstract IMatrix transpose();
  public abstract IMatrix negative();

  public abstract double determinant();

  // Binary Operations

  public abstract IMatrix scale(double s);
  public abstract IMatrix subtract(IMatrix m);
  public abstract IMatrix subtract(IMatrix... matrices);
  public abstract IMatrix sub(IMatrix m);
  public abstract IMatrix sub(IMatrix... matrices);

  public abstract IMatrix multiply(IMatrix other);


  public abstract boolean equals(IMatrix matrix);

}
