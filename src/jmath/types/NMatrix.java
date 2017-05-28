package jmath.types;

import jmath.tools.ArrayTools;

/**
 * Author:    LeqxLeqx
 */
public class NMatrix extends IMatrix{

  public static NMatrix identity(int dim) {
    if (dim <= 0)
      throw new IllegalArgumentException("Cannot create identity matrix of non-positive dimensions");

    double[] ret = new double[dim * dim];

    for(int k = 0; k < dim; k++) {
      ret[k * (dim + 1)] = 1;
    }

    return new NMatrix(dim, dim, ret);
  }

  public static NMatrix zero(int rows, int columns) {
    if (rows <= 0 || columns <= 0)
      throw new IllegalArgumentException("Cannot create zero matrix of non-positive dimensions");

    return new NMatrix(rows, columns, new double[rows * columns]);
  }





  public static NMatrix parse(String string) {
    if (string == null)
      throw new IllegalArgumentException("Cannot parseEvaluable null string as NMatrix object");

    String[] split = string.split("\\]\\[");
    if (!split[0].startsWith("[[") || !split[split.length - 1].endsWith("]]"))
      throw new IllegalArgumentException(String.format("Cannot parseEvaluable '%s' as a matrix", string));

    split[0] = split[0].substring(2);
    split[split.length - 1] = split[split.length - 1].substring(0, split[split.length - 1].length() - 2);

    String[][] multiSplit = new String[split.length][];

    for(int k = 0; k < split.length; k++) {
      multiSplit[k] = split[k].split("\\,");
    }

    for(int k = 1; k < split.length; k++) {
      if (multiSplit[0].length != multiSplit[k].length)
        throw new IllegalArgumentException(String.format("Cannot parseEvaluable '%s' as a matrix", string));
    }

    double[] ret = new double[multiSplit.length * multiSplit[0].length];

    try {

      for(int k = 0; k < multiSplit.length; k++) {
        for(int i = 0; i < multiSplit[0].length; i++) {
          ret[i + multiSplit[0].length * k] = Double.parseDouble(multiSplit[k][i]);
        }
      }

    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(String.format("Cannot parseEvaluable '%s' as a matrix", string));
    }

    return new NMatrix(multiSplit.length, multiSplit[0].length, ret);
  }


  public static NMatrix fromColumns(IVector[] columns) {
    if (columns == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (columns.length == 0)
      throw new IllegalArgumentException("Array must be of a positive length");

    IVector.checkDimensions(columns);

    double[] values = new double[columns.length * columns[0].dimension()];
    int top = 0;

    for(IVector iv : columns) {
      for(int k = 0; k < iv.dimension(); k++) {
        values[top * iv.dimension() + k] = iv.get(k);
      }
      top++;
    }

    return new NMatrix(columns[0].dimension(), columns.length, values);
  }

  public static NMatrix fromRows(IVector[] rows) {
    if (rows == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (rows.length == 0)
      throw new IllegalArgumentException("Array must be of a positive length");

    IVector.checkDimensions(rows);

    double[] values = new double[rows.length * rows[0].dimension()];
    int top = 0;

    for(IVector iv : rows) {
      for(int k = 0; k < iv.dimension(); k++) {
        values[top++] = iv.get(k);
      }
    }

    return new NMatrix(rows.length, rows[0].dimension(), values);
  }


  // Addition operations

  public static NMatrix add(IMatrix a, IMatrix b) {
    if ( a == null || b == null)
      throw new IllegalArgumentException("Matrix addition cannot be applied to nulls");
    if (
        a.numberOfColumns() != b.numberOfColumns() ||
        a.numberOfRows() != b.numberOfRows()
        )
      throw new IllegalArgumentException("Dimensional mismatch on matrix addition");

    double[] ret = new double[a.numberOfRows() * a.numberOfColumns()];

    for(int k = 0; k < a.numberOfRows(); k++) {
      for(int i = 0; i < a.numberOfColumns(); i++) {
        ret[i + k * a.numberOfColumns()] = a.get(k, i) + b.get(k, i);
      }
    }

    return new NMatrix(a.numberOfRows(), a.numberOfColumns(), ret);
  }
  public static NMatrix add(IMatrix... matrices) {
    checkArray(matrices);

    NMatrix ret = matrices[0].toNMatrix();

    for(int k = 1; k < matrices.length; k++) {
      ret = add(ret, matrices[k]);
    }

    return ret;
  }



  private static void checkArray(IMatrix[] matrices) {
    if (matrices == null)
      throw new IllegalArgumentException("Matrix array cannot be null");
    if (ArrayTools.containsNull(matrices))
      throw new IllegalArgumentException("Matrix array cannot contain nulls");
    if (matrices.length == 0)
      throw new IllegalArgumentException("Matrix array must have a positive length");

    int rows = matrices[0].numberOfRows(), columns = matrices[0].numberOfColumns();

    for(int k = 1; k < matrices.length; k++) {
      if (
          matrices[k].numberOfColumns() != columns ||
          matrices[k].numberOfRows() != rows
          )
        throw new IllegalArgumentException("Dimension mismatch on matrix operation");
    }

  }

  private static void checkArray(IMatrix[] matrices, int rows, int columns) {
    if (matrices == null)
      throw new IllegalArgumentException("Matrix array cannot be null");
    if (ArrayTools.containsNull(matrices))
      throw new IllegalArgumentException("Matrix array cannot contain nulls");

    for(IMatrix m : matrices) {
      if (
          m.numberOfColumns() != columns ||
          m.numberOfRows() != rows
          )
        throw new IllegalArgumentException("Dimensional mismatch on matrix operation");
    }
  }


  private final double[] values;
  private final int rows, columns;

  public NMatrix(int rows, int columns, double... values) {

    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");
    if (rows <= 0)
      throw new IllegalArgumentException("Row count cannot be non-positive");
    if (columns <= 0)
      throw new IllegalArgumentException("Column count cannot be non-positive");
    if (rows * columns != values.length)
      throw new IllegalArgumentException("Matrix is not filled by the number of provided values");

    this.rows = rows;
    this.columns = columns;
    this.values = values.clone();
  }

  public NMatrix(Matrix m) {
    if (m == null)
      throw new IllegalArgumentException("Matrix cannot be null");

    rows = m.numberOfRows();
    columns = m.numberOfColumns();

    values = new double[rows * columns];

    for(int row = 0; row < rows; row++) {
      for(int column = 0; column < columns; column++) {
        values[index(row, column)] = m.get(row, column);
      }
    }

  }


  private void checkRowColumn(int row, int column) {
    if (row < 0 || row >= rows)
      throw new IllegalArgumentException(String.format("Row getValue out of bounds for (%dx%dependent) matrix access: %dependent", rows, columns, row));
    if (column < 0 || column >= columns)
      throw new IllegalArgumentException(String.format("Column getValue out of bounds for (%dx%dependent) matrix access: %dependent", rows, columns, column));


  }

  private int index(int row, int column) {
    return column + row * columns;
  }


  @Override
  public double get(int row, int column) {
    checkRowColumn(row, column);

    return values[index(row, column)];
  }

  @Override
  public NMatrix set(int row, int column, double value) {
    checkRowColumn(row, column);

    double[] values = this.values.clone();
    values[index(row, column)] = value;

    return new NMatrix(rows, columns, values);
  }

  @Override
  public int numberOfRows() {
    return rows;
  }
  @Override
  public int numberOfColumns() {
    return columns;
  }

  @Override
  public NVector[] toColumnVectors() {
    NVector[] ret = new NVector[columns];
    double[] values = new double[rows];

    for(int k = 0; k < ret.length; k++) {
      for(int i = 0; i < values.length; i++) {
        values[i] = get(i, k);
      }
      ret[k] = new NVector(values);
    }

    return ret;
  }
  @Override
  public NVector[] toRowVectors() {
    NVector[] ret = new NVector[rows];
    double[] values = new double[columns];

    for(int k = 0; k < ret.length; k++) {
      for(int i = 0; i < values.length; i++) {
        values[i] = get(k, i);
      }
      ret[k] = new NVector(values);
    }

    return ret;
  }

  @Override
  public NVector transform(IVector vector) {
    if (vector == null)
      throw new IllegalArgumentException("Cannot transform null vector");
    if (vector.dimension() != columns)
      throw new IllegalArgumentException("Dimensional mismatch on transform of vector");

    double[] ret = new double[rows];

    for(int k = 0; k < rows; k++) {
      for(int i = 0; i < columns; i++) {
        ret[k] += vector.get(i) * get(k, i);
      }
    }

    return new NVector(ret);
  }

  @Override
  public Matrix toMatrix() {
    return new Matrix(this);
  }

  @Override
  public NMatrix toNMatrix() {
    return this;
  }

  @Override
  public NMatrix transpose() {
    double[] ret = new double[rows * columns];

    for(int k = 0; k < rows; k++) {
      for(int i = 0; i < columns; i++) {
        ret[k + i * rows] = get(k, i);
      }
    }

    return new NMatrix(columns, rows, ret);
  }

  @Override
  public NMatrix negative() {
    double[] ret = values.clone();
    for(int k = 0; k < ret.length; k++) {
      ret[k] *= -1;
    }

    return new NMatrix(rows, columns, ret);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof IMatrix)
      return equals((IMatrix) o);
    else
      return false;
  }

  @Override
  public boolean equals(IMatrix matrix) {
    if (matrix == null)
      return false;
    if (
            matrix.numberOfColumns() != numberOfColumns() ||
            matrix.numberOfRows() != numberOfRows()
            )
      return false;

    for(int k = 0; k < rows; k++) {
      for(int i = 0; i < columns; i++) {
        if (get(k, i) != matrix.get(k, i))
          return false;
      }
    }

    return true;
  }


  public NMatrix minor(int row, int column) {
    checkRowColumn(row, column);
    if (rows == 1)
      throw new IllegalArgumentException("Cannot get minor of uni-row matrix");
    if (columns == 1)
      throw new IllegalArgumentException("Cannot get minor of uni-column matrix");

    int rowTop = 0, columnTop;
    double[] ret = new double[ (rows - 1) * (columns -1) ];

    for(int r = 0; r < rows; r++) {
      if (r == row) continue;

      columnTop = 0;
      for(int c = 0; c < columns; c++) {

        if (c == column) continue;

        ret[ columnTop + rowTop * (columns - 1) ] = get(r, c);

        columnTop++;
      }
      rowTop++;
    }

    return new NMatrix(rows - 1, columns - 1, ret);
  }


  public double determinant() {
    if (rows != columns)
      throw new ArithmeticException(String.format("Cannot find determinant of non-square matrix (%dx%dependent)", rows, columns));

    if (rows == 1)
      return get(0, 0);
    else if (rows == 2)
      return get(0, 0) * get(1, 1) - get(0, 1) * get(1, 0);


    double ret = 0;
    for(int k = 0; k < rows; k++) {
      ret += (((k % 2) * -2) + 1) * get(0, k) * minor(0, k).determinant();
    }

    return ret;
  }

  @Override
  public NMatrix scale(double s) {
    double[] values = this.values.clone();

    for(int k = 0; k < values.length; k++) {
      values[k] *= s;
    }

    return new NMatrix(rows, columns, values);
  }

  @Override
  public NMatrix subtract(IMatrix m) {
    return sub(m);
  }

  @Override
  public NMatrix subtract(IMatrix... matrices) {
    return sub(matrices);
  }

  @Override
  public NMatrix sub(IMatrix m) {
    if (m == null)
      throw new IllegalArgumentException("Cannot subtract by a null matrix");
    if (m.numberOfColumns() != numberOfColumns() || m.numberOfRows() != numberOfRows())
      throw new IllegalArgumentException("Dimensional mismatch on matrix subtraction");

    double[] ret = this.values.clone();
    for(int k = 0; k < rows; k++) {
      for(int i = 0; i < columns; i++) {
        ret[index(k, i)] -= m.get(k, i);
      }
    }

    return new NMatrix(rows, columns, ret);
  }

  @Override
  public NMatrix sub(IMatrix... matrices) {
    checkArray(matrices, rows, columns);

    NMatrix ret = this;
    for(IMatrix m : matrices) {
      ret = ret.sub(m);
    }

    return ret;
  }

  @Override
  public NMatrix multiply(IMatrix other) {
    if (other == null)
      throw new IllegalArgumentException("Cannot multiply by null matrix");
    if (numberOfColumns() != other.numberOfRows())
      throw new IllegalArgumentException("Dimensional mismatch on matrix multiplication");

    int newRows = numberOfRows(), newColumns = other.numberOfColumns();

    double[] ret = new double[newRows * newColumns];

    IVector[]
            thisRows = toRowVectors(),
            otherColumns = other.toColumnVectors()
                    ;

    for(int column = 0; column < newColumns; column++) {
      for(int row = 0; row < newRows; row++) {
        ret[row + column * newRows] = NVector.dot(thisRows[column], otherColumns[row]);
      }
    }

    return new NMatrix(newRows, newColumns, ret);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");

    for(NVector row : toRowVectors()) {
      sb.append(row.toString().replace("<", "[").replace(">", "]"));
    }

    sb.append("]");
    return sb.toString();
  }




}
