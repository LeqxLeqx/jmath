package jmath.types;

import jmath.JMath;
import jmath.tools.ArrayTools;

/**
 * Author:    LeqxLeqx
 */
public class Matrix extends IMatrix{

  public static final Matrix
          IDENTITY = new Matrix(1, 0, 0, 0, 1, 0, 0, 0, 1),
          ZERO = new Matrix(new double[9])
          ;

  public static Matrix rotation(double phi, double theta, double psi) {
    /*
    return new Matrix(
            JMath.cos(theta) * JMath.cos(psi),
            - JMath.cos(phi) * JMath.sin(psi) - JMath.sin(phi) * JMath.sin(theta) * JMath.cos(psi),
            JMath.sin(phi) * JMath.sin(psi) + JMath.cos(phi) * JMath.sin(theta) * JMath.cos(psi),

            JMath.cos(theta) * JMath.sin(psi),
            JMath.cos(phi) * JMath.cos(psi) + JMath.sin(phi) * JMath.sin(theta) * JMath.sin(psi),
            - JMath.sin(phi) * JMath.cos(psi) + JMath.cos(phi) * JMath.sin(theta) * JMath.sin(psi),

            - JMath.sin(theta),
            JMath.sin(phi) * JMath.cos(theta),
            JMath.cos(phi) * JMath.cos(theta)
    );
    */

    Matrix
            rx = xRotation(phi),
            ry = yRotation(theta),
            rz = zRotation(psi)
                    ;

    return rx.multiply(ry).multiply(rz);
  }

  public static Matrix xRotation(double theta) {
    return new Matrix(
            1, 0, 0,
            0, JMath.cos(theta), -JMath.sin(theta),
            0, JMath.sin(theta), JMath.cos(theta)
      );
  }
  public static Matrix yRotation(double theta) {
    return new Matrix(
            JMath.cos(theta), 0, JMath.sin(theta),
            0, 1, 0,
            -JMath.sin(theta), 0, JMath.cos(theta)
      );
  }
  public static Matrix zRotation(double theta) {
    return new Matrix(
            JMath.cos(theta), -JMath.sin(theta), 0,
            JMath.sin(theta), JMath.cos(theta), 0,
            0, 0, 1
    );
  }



  public static Matrix parse(String string) {

    if (string == null)
      throw new IllegalArgumentException("Cannot parseEvaluable null string as matrix");


    String[] split = string.split("\\]\\[");

    if (split.length != 3 || !split[0].startsWith("[[") || !split[2].endsWith("]]"))
      throw new IllegalArgumentException(String.format("Cannot parseEvaluable '%s' as a matrix", string));

    split[0] = split[0].substring(2);
    split[2] = split[2].substring(0, split[2].length() - 2);


    double[] r1 = new double[3], r2 = new double[3], r3 = new double[3];

    for(int k = 0; k < 3; k++) {
      String[] rowSplit = split[k].split("\\,");

      if (rowSplit.length != 3)
        throw new IllegalArgumentException(String.format("Cannot parseEvaluable '%s' as a matrix", string));

      try {
        r1[k] = Double.parseDouble(rowSplit[0]);
        r2[k] = Double.parseDouble(rowSplit[1]);
        r3[k] = Double.parseDouble(rowSplit[2]);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException(String.format("Cannot parseEvaluable '%s' as a matrix", string));
      }

    }

    return new Matrix(
          r1[0], r2[0], r3[0],
          r1[1], r2[1], r3[1],
          r1[2], r2[2], r3[2]
      );
  }

  public static Matrix fromColumns(Vector[] columns) {
    if (columns == null)
      throw new IllegalArgumentException("Columns array cannot be null");
    if (ArrayTools.containsNull(columns))
      throw new IllegalArgumentException("Columns array cannot contain nulls");
    if (columns.length != 3)
      throw new IllegalArgumentException("Columns array must be of length 3");

    return new Matrix(
          columns[0].x, columns[1].x, columns[2].x,
          columns[0].y, columns[1].y, columns[2].y,
          columns[0].z, columns[1].z, columns[2].z
      );
  }

  public static Matrix fromRows(Vector[] rows) {
    if (rows == null)
      throw new IllegalArgumentException("Rows array cannot be null");
    if (ArrayTools.containsNull(rows))
      throw new IllegalArgumentException("Rows array cannot contain nulls");
    if (rows.length != 3)
      throw new IllegalArgumentException("Rows array must be of length 3");

    return new Matrix(
          rows[0].x, rows[0].y, rows[0].z,
          rows[1].x, rows[1].y, rows[1].z,
          rows[2].x, rows[2].y, rows[2].z
      );

  }

  // Addition operations

  public static Matrix add(Matrix a, Matrix b) {
    return new Matrix(
          a.m00 + b.m00, a.m01 + b.m01, a.m02 + b.m02,
          a.m10 + b.m10, a.m11 + b.m11, a.m12 + b.m12,
          a.m20 + b.m20, a.m21 + b.m21, a.m22 + b.m22
      );
  }
  public static Matrix add(Matrix... matrices) {
    double
            m00 = 0,
            m01 = 0,
            m02 = 0,
            m10 = 0,
            m11 = 0,
            m12 = 0,
            m20 = 0,
            m21 = 0,
            m22 = 0
            ;

    for(Matrix m : matrices) {
      m00 += m.m00;
      m01 += m.m01;
      m02 += m.m02;
      m10 += m.m10;
      m11 += m.m11;
      m12 += m.m12;
      m20 += m.m20;
      m21 += m.m21;
      m22 += m.m22;
    }

    return new Matrix(m00, m01, m02, m10, m11, m12, m20, m21, m22);
  }





  public final double
    m00, m01, m02,
    m10, m11, m12,
    m20, m21, m22
          ;

  public Matrix(
          double m00, double m01, double m02,
          double m10, double m11, double m12,
          double m20, double m21, double m22
    ) {

    this.m00 = m00;
    this.m01 = m01;
    this.m02 = m02;
    this.m10 = m10;
    this.m11 = m11;
    this.m12 = m12;
    this.m20 = m20;
    this.m21 = m21;
    this.m22 = m22;

  }
  public Matrix(
          double m00, double m01,
          double m10, double m11
    ) {

    this.m00 = m00;
    this.m01 = m01;
    this.m02 = 0;
    this.m10 = m10;
    this.m11 = m11;
    this.m12 = 0;
    this.m20 = 0;
    this.m21 = 0;
    this.m22 = 0;
  }

  public Matrix(double[] m) {
    if (m == null)
      throw new IllegalArgumentException("Value specification array cannot be null");
    if (m.length == 9) {
      this.m00 = m[0];
      this.m01 = m[1];
      this.m02 = m[2];
      this.m10 = m[3];
      this.m11 = m[4];
      this.m12 = m[5];
      this.m20 = m[6];
      this.m21 = m[7];
      this.m22 = m[8];
    }
    else if (m.length == 4) {
      this.m00 = m[0];
      this.m01 = m[1];
      this.m02 = 0;
      this.m10 = m[2];
      this.m11 = m[3];
      this.m12 = 0;
      this.m20 = 0;
      this.m21 = 0;
      this.m22 = 0;
    }
    else
      throw new IllegalArgumentException("Value specification array length must be either 4 or 9");

  }

  public Matrix(NMatrix matrix) {
    if (matrix == null)
      throw new IllegalArgumentException("Matrix cannot be null");
    if (matrix.numberOfColumns() != 3 || matrix.numberOfRows() != 3)
      throw new IllegalArgumentException("Matrix cannot be of dimensions other than (3x3)");

    m00 = matrix.get(0, 0);
    m01 = matrix.get(0, 1);
    m02 = matrix.get(0, 2);

    m10 = matrix.get(1, 0);
    m11 = matrix.get(1, 1);
    m12 = matrix.get(1, 2);

    m20 = matrix.get(2, 0);
    m21 = matrix.get(2, 1);
    m22 = matrix.get(2, 2);

  }


  // accessors

  @Override
  public double get(int row, int column) {
    if (row < 0 || row >= 3)
      throw new IllegalArgumentException(String.format("Row getValue out of bounds for (3x3) matrix access: %dependent", row));
    if (column < 0 || column >= 3)
      throw new IllegalArgumentException(String.format("Column getValue out of bounds for (3x3) matrix access: %dependent", column));

    switch(row * 3 + column) {

      case 0: // 0,0
        return m00;
      case 1: // 0,1
        return m01;
      case 2: // 0,2
        return m02;
      case 3: // 1,0
        return m10;
      case 4: // 1,1
        return m11;
      case 5: // 1,2
        return m12;
      case 6: // 2,0
        return m20;
      case 7: // 2,1
        return m21;
      case 8: // 2,1
        return m22;

      default:
        throw new RuntimeException();

    }

  }

  @Override
  public Matrix set(int row, int column, double value) {
    if (row < 0 || row >= 3)
      throw new IllegalArgumentException(String.format("Row getValue out of bounds for (3x3) matrix access: %dependent", row));
    if (column < 0 || column >= 3)
      throw new IllegalArgumentException(String.format("Column getValue out of bounds for (3x3) matrix access: %dependent", column));

    double
      m00 = this.m00,
      m01 = this.m01,
      m02 = this.m02,
      m10 = this.m10,
      m11 = this.m11,
      m12 = this.m12,
      m20 = this.m20,
      m21 = this.m21,
      m22 = this.m22;

    switch(row * 3 + column) {

      case 0: // 0,0
        m00 = value;
        break;
      case 1: // 0,1
        m01 = value;
        break;
      case 2: // 0,2
        m02 = value;
        break;
      case 3: // 1,0
        m10 = value;
        break;
      case 4: // 1,1
        m11 = value;
        break;
      case 5: // 1,2
        m12 = value;
        break;
      case 6: // 2,0
        m20 = value;
        break;
      case 7: // 2,1
        m21 = value;
        break;
      case 8: // 2,1
        m22 = value;
        break;

      default:
        throw new RuntimeException();

    }

    return new Matrix(m00, m01, m02, m10, m11, m12, m20, m21, m22);
  }

  @Override
  public int numberOfRows() {
    return 3;
  }

  @Override
  public int numberOfColumns() {
    return 3;
  }


  // Vector operations

  public Vector transform(Vector v) {
    if (v == null)
      throw new IllegalArgumentException("Vector cannot be null");
    return new Vector(
            v.x * m00 + v.y * m01 + v.z * m02,
            v.x * m10 + v.y * m11 + v.z * m12,
            v.x * m20 + v.y * m21 + v.z * m22
      );
  }

  @Override
  public IVector transform(IVector v) {
    if (v == null)
      throw new IllegalArgumentException("Vector cannot be null");
    if (v.dimension() != 3)
      throw new IllegalArgumentException("Invalid dimension of vector to transform");

    return transform(v.toVector());
  }

  // To vector representations

  @Override
  public Vector[] toColumnVectors() {
    return new Vector[] {
          new Vector(m00, m10, m20),
          new Vector(m01, m11, m21),
          new Vector(m02, m12, m22)
      };
  }
  @Override
  public Vector[] toRowVectors() {
    return new Vector[] {
          new Vector(m00, m01, m02),
          new Vector(m10, m11, m12),
          new Vector(m20, m21, m22)
      };
  }


  // Unary operations

  public Matrix transpose() {
    return new Matrix(
            m00, m10, m20,
            m01, m11, m21,
            m02, m12, m22
      );
  }

  public Matrix negative() {
    return scale(-1);
  }

  public double determinant() {
    return
      m00 * m11 * m22 +
      m10 * m21 * m02 +
      m20 * m01 * m12 -
      m20 * m11 * m02 -
      m10 * m01 * m22 -
      m00 * m21 * m12;
  }

  // Binary Operations

  public Matrix scale(double s) {
    return new Matrix(
            m00 * s, m10 * s, m20 * s,
            m01 * s, m11 * s, m21 * s,
            m02 * s, m12 * s, m22 * s
      );
  }

  @Override
  public Matrix subtract(IMatrix m) {
    return sub(m);
  }
  @Override
  public Matrix subtract(IMatrix... matrices) {
    return sub(matrices);
  }
  @Override
  public Matrix sub(IMatrix m) {
    if (m == null)
      throw new IllegalArgumentException("Cannot subtract by null matrix");

    return sub(m.toMatrix());
  }
  @Override
  public Matrix sub(IMatrix... matrices) {
    if (matrices == null)
      throw new IllegalArgumentException("Cannot subtract by null matrix array");
    if (ArrayTools.containsNull(matrices))
      throw new IllegalArgumentException("Cannot subtract by matrix array containing nulls");

    Matrix[] ms = new Matrix[matrices.length];
    for(int k =0 ; k < ms.length; k++) {
      ms[k] = matrices[k].toMatrix();
    }

    return sub(ms);
  }

  public Matrix subtract(Matrix m) { // Alias of sub(Matrix m)
    return sub(m);
  }
  public Matrix subtract(Matrix... matrices) { // Alias of sub(Matrix... matrices)
    return sub(matrices);
  }
  public Matrix sub(Matrix m) {
    if (m == null)
      throw new IllegalArgumentException("Cannot subtract by null matrix");

    return Matrix.add(this, m.negative());
  }
  public Matrix sub(Matrix... matrices) {
    if (matrices == null)
      throw new IllegalArgumentException("Cannot subtract by null matrix array");
    if (ArrayTools.containsNull(matrices))
      throw new IllegalArgumentException("Cannot subtract by matrix array containing nulls");

    Matrix[] newMatrices = new Matrix[matrices.length + 1];
    for(int k = 0; k < matrices.length; k++ ){
      newMatrices[k] = matrices[k].negative();
    }
    newMatrices[newMatrices.length - 1] = this;

    return Matrix.add(newMatrices);
  }


  @Override
  public Matrix multiply(IMatrix other) {
    if (other == null)
      throw new IllegalArgumentException("Cannot multiply by null matrix");

    return multiply(other.toMatrix());
  }

  public Matrix multiply(Matrix other) {
    if (other == null)
      throw new IllegalArgumentException("Cannot multiply by null matrix");

    double[] ret = new double[9];

    Vector[]
      thisRows = toRowVectors(),
      otherColumns = other.toColumnVectors()
              ;

    for(int column = 0; column < 3; column++) {
      for(int row = 0; row < 3; row++) {
        ret[row + column * 3] = Vector.dot(thisRows[column], otherColumns[row]);
      }
    }

    return new Matrix(ret);
  }



  @Override
  public String toString() {
    return String.format("[[%s,%s,%s][%s,%s,%s][%s,%s,%s]]", m00, m01, m02, m10, m11, m12, m20, m21, m22);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Matrix)
      return equals((Matrix) o);
    else if (o instanceof IMatrix)
      return equals((IMatrix) o);
    else
      return false;
  }

  public boolean equals(Matrix m) {
    return
            m00 == m.m00 &&
            m01 == m.m01 &&
            m02 == m.m02 &&
            m10 == m.m10 &&
            m11 == m.m11 &&
            m12 == m.m12 &&
            m20 == m.m20 &&
            m21 == m.m21 &&
            m22 == m.m22
            ;
  }

  @Override
  public boolean equals(IMatrix m) {
    if (m.numberOfColumns() != 3 || m.numberOfRows() != 3)
      return false;

    for(int k = 0; k < 3; k++) {
      for(int i = 0; i < 3; i++) {
        if (get(k, i) != m.get(k, i))
          return false;
      }
    }

    return true;
  }


  @Override
  public Matrix toMatrix() {
    return this;
  }

  @Override
  public NMatrix toNMatrix() {
    return new NMatrix(this);
  }


}
