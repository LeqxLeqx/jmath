/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\
 *                                                                         *
 *  jmath: a library for mathematical computation for Java                 *
 *  Copyright (C) 2017  LeqxLeqx                                           *
 *                                                                         *
 *  This program is free software: you can redistribute it and/or modify   *
 *  it under the terms of the GNU General Public License as published by   *
 *  the Free Software Foundation, either version 3 of the License, or      *
 *  (at your option) any later version.                                    *
 *                                                                         *
 *  This program is distributed in the hope that it will be useful,        *
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of         *
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the          *
 *  GNU General Public License for more details.                           *
 *                                                                         *
 *  You should have received a copy of the GNU General Public License      *
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.  *
 *                                                                         *
\* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package jmath.types;

import java.util.Arrays;

/**
 * Class for representing a
 * continuous domain along
 * at least one dimension
 */
public class Domain {

	private static boolean nameIsValid(String string) {
		char c;

		if (string.length() == 0)
			return false;

		c = string.charAt(0);

		if (
		  c != '_' &&
			!(c >= 'a' && c <= 'z') &&
			!(c >= 'A' && c <= 'Z')
			)
			return false;
		
		for (int k = 1; k < string.length(); k++) {
			c = string.charAt(k);
			if (
				c != '_' &&
				!(c >= 'a' && c <= 'z') &&
				!(c >= 'A' && c <= 'Z') &&
				!(c >= '0' && c <= '9')
				)
				return false;
		}

		return true;
	}

  public static Domain parse(String string) {
    if (string == null)
      throw new IllegalArgumentException("Cannot parse null string as domain");
    if (!string.startsWith("{") || !string.endsWith("}"))
      throw new IllegalArgumentException(String.format("Cannot parse '%s' as a domain", string));

    String innerString = string.substring(1, string.length() - 1).replace(" ", "");

    String[] split = innerString.split("\\,");

    String[] names;
    double[] lowers, uppers;
    BoundaryCondition[] lowerbcs, upperbcs;

    if (split.length % 2 != 0)
      throw new IllegalArgumentException(String.format("Cannot parse '%s' as a domain", innerString));

    names = new String[split.length / 2];
    lowers = new double[split.length / 2];
    uppers = new double[split.length / 2];
    lowerbcs = new BoundaryCondition[split.length / 2];
    upperbcs = new BoundaryCondition[split.length / 2];


    try {
      for (int k = 0; k < split.length; k += 2) {

        String
                before = split[k],
                after = split[k + 1];

        if (before.isEmpty() || after.isEmpty())
          throw new IllegalArgumentException(String.format("Cannot parse '%s' as a domain", innerString));

        String name;
        double lower, upper;
        BoundaryCondition lowerbc, upperbc;

        if (before.contains(":")) {
          String[] beforeSplit = before.split("\\:", 2);
          if (beforeSplit[0].isEmpty() || beforeSplit[1].isEmpty())
            throw new IllegalArgumentException(String.format("Cannot parse '%s' as a domain", innerString));

          name = beforeSplit[0];
          lowerbc = BoundaryCondition.parseFromStartCharacter(beforeSplit[1].charAt(0));
          lower = Double.parseDouble(beforeSplit[1].substring(1));
        } else {
          name = "";
          lowerbc = BoundaryCondition.parseFromStartCharacter(before.charAt(0));
          lower = Double.parseDouble(before.substring(1));
        }

        upper = Double.parseDouble(after.substring(0, after.length() - 1));
        upperbc = BoundaryCondition.parseFromEndCharacter(after.charAt(after.length() - 1));


        names[k / 2] = name;
        lowers[k / 2] = lower;
        uppers[k / 2] = upper;
        lowerbcs[k / 2] = lowerbc;
        upperbcs[k / 2] = upperbc;

      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(String.format("Cannot parse '%s' as a domain", innerString));
    }

    return new Domain(names, lowers, lowerbcs, uppers, upperbcs);
  }


  private String[] names;
  private double[] lower, upper;
  private BoundaryCondition[] lowerBoundaryConditions, upperBoundaryConditions;

  public Domain(
          String[] names,
          double[] lower,
          BoundaryCondition[] lowerBoundaryConditions,
          double[] upper,
          BoundaryCondition[] upperBoundaryConditions
        ) {

    if (names == null)
      throw new IllegalArgumentException("Names array cannot be null");
    if (lower == null)
      throw new IllegalArgumentException("Lower array cannot be null");
    if (upper == null)
      throw new IllegalArgumentException("Upper array cannot be null");
    if (lowerBoundaryConditions == null)
      throw new IllegalArgumentException("Lower boundary condition array cannot be null");
    if (upperBoundaryConditions == null)
      throw new IllegalArgumentException("Upper boundary condition array cannot be null");

    if (names.length != lower.length)
      throw new IllegalArgumentException("Array length mismatch");
    if (names.length != lowerBoundaryConditions.length)
      throw new IllegalArgumentException("Array length mismatch");
    if (names.length != upper.length)
      throw new IllegalArgumentException("Array length mismatch");
    if (names.length != upperBoundaryConditions.length)
      throw new IllegalArgumentException("Array length mismatch");

    if (names.length == 0)
      throw new IllegalArgumentException("Cannot create zero-dimensional domain");

    if (Arrays.asList(names).contains(null))
      throw new IllegalArgumentException("String array cannot contain nulls");
    if (Arrays.asList(lowerBoundaryConditions).contains(null))
      throw new IllegalArgumentException("Boundary condition array cannot contain nulls");
    if (Arrays.asList(upperBoundaryConditions).contains(null))
      throw new IllegalArgumentException("Boundary condition array cannot contain nulls");

    for(String name : names) {
      if (!nameIsValid(name) && !name.isEmpty())
        throw new IllegalArgumentException(String.format("Dimension name '%s' is invalid", name));
    }

    for(int k = 0; k < names.length; k++) {
      if (lower[k] > upper[k])
        throw new IllegalArgumentException(
                  String.format("Invalid specification of dimension %s (%s > %s)",
                          names[k].isEmpty() ? k : names[k],
                          lower[k],
                          upper[k]
                  )
                );
    }

    this.names = names.clone();
    this.lower = lower.clone();
    this.upper = upper.clone();
    this.lowerBoundaryConditions = lowerBoundaryConditions.clone();
    this.upperBoundaryConditions = upperBoundaryConditions.clone();

  }


  public String[] getNames() {
    return names.clone();
  }
  public String getName(int index) {
    if (index < 0 || index >= names.length)
      throw new IllegalArgumentException("No such dimension: " + index);

    return names[index];
  }
  public boolean isNamed(int index) {
    return !getName(index).isEmpty();
  }

  public boolean hasDimension(String name) {
    for(String n : names) {
      if (n.equals(name))
        return true;
    }

    return false;
  }


  public int getDimensionality() {
    return names.length;
  }



  public boolean isWithin(IVector vector) {
    if (vector == null)
      throw new IllegalArgumentException("Values array cannot be null");

    return isWithin(vector.toArray());
  }


  public boolean isWithin(double... values) {
    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null");
    if (values.length != names.length)
      throw new IllegalArgumentException("Values array mismatch against domain");

    for(int k = 0; k < values.length; k++) {
      if (
              !lowerBoundaryConditions[k].isGreater(values[k], lower[k]) ||
              !upperBoundaryConditions[k].isLesser(values[k], upper[k])
              )
        return false;
    }

    return true;
  }


  public double getLower(int dim) {
    if (dim < 0 || dim >= names.length)
      throw new IllegalArgumentException("No such dimension: " + dim);

    return lower[dim];
  }
  public double getLower(String dimName) {
    if (dimName == null)
      throw new IllegalArgumentException("Dimension name cannot be null");

    for(int k = 0; k < names.length; k++) {
      if (names[k].equals(dimName))
        return lower[k];
    }

    throw new IllegalArgumentException("No such dimension: '" + dimName + "'");
  }

  public BoundaryCondition getLowerBoundaryCondition(int dim) {
    if (dim < 0 || dim >= names.length)
      throw new IllegalArgumentException("No such dimension: " + dim);

    return lowerBoundaryConditions[dim];
  }
  public BoundaryCondition getLowerBoundaryCondition(String dimName) {
    if (dimName == null)
      throw new IllegalArgumentException("Dimension name cannot be null");

    for(int k = 0; k < names.length; k++) {
      if (names[k].equals(dimName))
        return lowerBoundaryConditions[k];
    }

    throw new IllegalArgumentException("No such dimension: '" + dimName + "'");
  }




  public double getUpper(int dim) {
    if (dim < 0 || dim > names.length)
      throw new IllegalArgumentException("No such dimension: " + dim);

    return upper[dim];
  }
  public double getUpper(String dimName) {
    if (dimName == null)
      throw new IllegalArgumentException("Dimension name cannot be null");

    for(int k = 0; k < names.length; k++) {
      if (names[k].equals(dimName))
        return upper[k];
    }

    throw new IllegalArgumentException("No such dimension: '" + dimName + "'");
  }

  public BoundaryCondition getUpperBoundaryCondition(int dim) {
    if (dim < 0 || dim >= names.length)
      throw new IllegalArgumentException("No such dimension: " + dim);

    return upperBoundaryConditions[dim];
  }
  public BoundaryCondition getUpperBoundaryCondition(String dimName) {
    if (dimName == null)
      throw new IllegalArgumentException("Dimension name cannot be null");

    for(int k = 0; k < names.length; k++) {
      if (names[k].equals(dimName))
        return upperBoundaryConditions[k];
    }

    throw new IllegalArgumentException("No such dimension: '" + dimName + "'");
  }



  public double[] getBounds(int dim) {
    return new double[] { getLower(dim), getUpper(dim) };
  }
  public double[] getBounds(String dimName) {
    return new double[] { getLower(dimName), getUpper(dimName) };
  }
  public BoundaryCondition[] getBoundaryConditions(int dim) {
    return new BoundaryCondition[] { getLowerBoundaryCondition(dim), getUpperBoundaryCondition(dim) };
  }
  public BoundaryCondition[] getBoundaryConditions(String dimName) {
    return new BoundaryCondition[] { getLowerBoundaryCondition(dimName), getUpperBoundaryCondition(dimName) };
  }


  public String toString() {

    String[] dims = new String[getDimensionality()];

    for(int k = 0; k < dims.length; k++) {

      if (isNamed(k))
        dims[k] = String.format(
                "%s:%c%s,%s%c",
                getName(k),
                getLowerBoundaryCondition(k).startChar,
                getLower(k),
                getUpper(k),
                getUpperBoundaryCondition(k).endChar
                );
      else
        dims[k] = String.format(
                "%c%s,%s%c",
                getLowerBoundaryCondition(k).startChar,
                getLower(k),
                getUpper(k),
                getUpperBoundaryCondition(k).endChar
                );

    }

    String ret = dims[0];
    for(int k = 1; k < dims.length; k++) {
      ret = String.format("%s,%s", ret, dims[k]);
    }

    return String.format("{%s}", ret);
  }





  public enum BoundaryCondition {

    INCLUSIVE ('[', ']'),
    EXCLUSIVE ('(', ')'),

    ;

    private static BoundaryCondition parseFromStartCharacter(char c) {
      for(BoundaryCondition bc : values()) {
        if (bc.startChar == c)
          return bc;
      }

      return null;
    }
    private static BoundaryCondition parseFromEndCharacter(char c) {
      for(BoundaryCondition bc : values()) {
        if (bc.endChar == c)
          return bc;
      }

      return null;
    }



    private final char startChar, endChar;

    BoundaryCondition(char s, char e) {
      startChar = s;
      endChar = e;
    }

    public boolean isGreater(double d0, double d1) {

      switch (this) {

        case INCLUSIVE:
          return d0 >= d1;
        case EXCLUSIVE:
          return d0 > d1;

        default:
          throw new RuntimeException();
      }

    }

    public boolean isLesser(double d0, double d1) {

      switch (this) {

        case INCLUSIVE:
          return d0 <= d1;
        case EXCLUSIVE:
          return d0 < d1;

        default:
          throw new RuntimeException();
      }

    }



  }

}
