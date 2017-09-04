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

package jmath.tools;

import java.util.Random;

/**
 * Class full of methods for the purpose
 * of modifying and interacting with arrays
 */
public /*static*/ class ArrayTools { private ArrayTools() {  }


  public static <T> boolean isNullOrEmpty(T[] array) {
    if (array == null)
      return true;
    else
      return array.length == 0;
  }

  public static <T> boolean isNullOrContainsNull(T[] array) {
    if (array == null)
      return true;
    else
      return containsNull(array);
  }




  public static <T> boolean containsNull(T[] array) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for (T t : array) {
      if (t == null)
        return true;
    }

    return false;
  }

  public static <T> boolean contains(T[] array, T t) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for (T e : array) {
      if (e == t)
        return true;
    }

    return false;
  }
  public static boolean contains(long[] array, long value) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(long l : array) {
      if (l == value)
        return true;
    }

    return false;
  }
  public static boolean contains(int[] array, int value) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int l : array) {
      if (l == value)
        return true;
    }

    return false;
  }
  public static boolean contains(short[] array, short value) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(short l : array) {
      if (l == value)
        return true;
    }

    return false;
  }
  public static boolean contains(byte[] array, byte value) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(byte l : array) {
      if (l == value)
        return true;
    }

    return false;
  }
  public static boolean contains(char[] array, char value) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(char l : array) {
      if (l == value)
        return true;
    }

    return false;
  }
  public static boolean contains(float[] array, float value) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(float l : array) {
      if (l == value)
        return true;
    }

    return false;
  }
  public static boolean contains(double[] array, double value) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(double l : array) {
      if (l == value)
        return true;
    }

    return false;
  }


  public static <T> int occurrences(T[] array, T t) {
    int ret = 0;

    for(T e : array) {
      if (e == null && t == null)
        ret++;
      else if (!(e == null || t == null) && t.equals(e))
        ret++;

    }

    return ret;
  }
  public static int occurrences(long[] array, long t) {
    int ret = 0;

    for(long e : array) {
      if (e == t)
        ret++;
    }

    return ret;
  }
  public static int occurrences(int[] array, int t) {
    int ret = 0;

    for(int e : array) {
      if (e == t)
        ret++;
    }

    return ret;
  }
  public static int occurrences(short[] array, short t) {
    int ret = 0;

    for(short e : array) {
      if (e == t)
        ret++;
    }

    return ret;
  }
  public static int occurrences(byte[] array, byte t) {
    int ret = 0;

    for(byte e : array) {
      if (e == t)
        ret++;
    }

    return ret;
  }
  public static int occurrences(char[] array, char t) {
    int ret = 0;

    for(char e : array) {
      if (e == t)
        ret++;
    }

    return ret;
  }
  public static int occurrences(float[] array, float t) {
    int ret = 0;

    for(float e : array) {
      if (e == t)
        ret++;
    }

    return ret;
  }
  public static int occurrences(double[] array, double t) {
    int ret = 0;

    for(double e : array) {
      if (e == t)
        ret++;
    }

    return ret;
  }



  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with a new java.util.Random object
   *
   * @param array array to be shuffled
   */
  public static <T> void shuffle(T[] array) {
    shuffle(array, new Random());
  }

  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with a new java.util.Random object
   * created from the provided seed
   *
   * @param array array to be shuffled
   * @param seed the seed to use for the pseudo-random number generator
   */
  public static <T> void shuffle(T[] array, long seed) {
    shuffle(array, new Random(seed));
  }

  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with the provided java.util.Random
   * object
   *
   * @param array array to be shuffled
   * @param random the random object to use
   */
  public static <T> void shuffle(T[] array, Random random) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = array.length - 1; k > 0; k--) {
      int j = random.nextInt(k);

      exchange(array, j, k);
    }

  }


  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with a new java.util.Random object
   *
   * @param array array to be shuffled
   */
  public static void shuffle(long[] array) {
    shuffle(array, new Random());
  }
  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with a new java.util.Random object
   * created from the provided seed
   *
   * @param array array to be shuffled
   * @param seed the seed to use for the pseudo-random number generator
   */
  public static void shuffle(long[] array, long seed) {
    shuffle(array, new Random(seed));
  }
  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with the provided java.util.Random
   * object
   *
   * @param array array to be shuffled
   * @param random the random object to use
   */
  public static void shuffle(long[] array, Random random) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = array.length - 1; k > 0; k--) {
      int j = random.nextInt(k);

      exchange(array, j, k);
    }
  }

  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with a new java.util.Random object
   *
   * @param array array to be shuffled
   */
  public static void shuffle(int[] array) {
    shuffle(array, new Random());
  }
  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with a new java.util.Random object
   * created from the provided seed
   *
   * @param array array to be shuffled
   * @param seed the seed to use for the pseudo-random number generator
   */
  public static void shuffle(int[] array, long seed) {
    shuffle(array, new Random(seed));
  }
  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with the provided java.util.Random
   * object
   *
   * @param array array to be shuffled
   * @param random the random object to use
   */
  public static void shuffle(int[] array, Random random) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = array.length - 1; k > 0; k--) {
      int j = random.nextInt(k);

      exchange(array, j, k);
    }
  }

  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with a new java.util.Random object
   *
   * @param array array to be shuffled
   */
  public static void shuffle(short[] array) {
    shuffle(array, new Random());
  }
  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with a new java.util.Random object
   * created from the provided seed
   *
   * @param array array to be shuffled
   * @param seed the seed to use for the pseudo-random number generator
   */
  public static void shuffle(short[] array, long seed) {
    shuffle(array, new Random(seed));
  }
  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with the provided java.util.Random
   * object
   *
   * @param array array to be shuffled
   * @param random the random object to use
   */
  public static void shuffle(short[] array, Random random) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = array.length - 1; k > 0; k--) {
      int j = random.nextInt(k);

      exchange(array, j, k);
    }
  }

  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with a new java.util.Random object
   *
   * @param array array to be shuffled
   */
  public static void shuffle(byte[] array) {
    shuffle(array, new Random());
  }
  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with a new java.util.Random object
   * created from the provided seed
   *
   * @param array array to be shuffled
   * @param seed the seed to use for the pseudo-random number generator
   */
  public static void shuffle(byte[] array, long seed) {
    shuffle(array, new Random(seed));
  }
  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with the provided java.util.Random
   * object
   *
   * @param array array to be shuffled
   * @param random the random object to use
   */
  public static void shuffle(byte[] array, Random random) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = array.length - 1; k > 0; k--) {
      int j = random.nextInt(k);

      exchange(array, j, k);
    }
  }

  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with a new java.util.Random object
   *
   * @param array array to be shuffled
   */
  public static void shuffle(double[] array) {
    shuffle(array, new Random());
  }
  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with a new java.util.Random object
   * created from the provided seed
   *
   * @param array array to be shuffled
   * @param seed the seed to use for the pseudo-random number generator
   */
  public static void shuffle(double[] array, long seed) {
    shuffle(array, new Random(seed));
  }
  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with the provided java.util.Random
   * object
   *
   * @param array array to be shuffled
   * @param random the random object to use
   */
  public static void shuffle(double[] array, Random random) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = array.length - 1; k > 0; k--) {
      int j = random.nextInt(k);

      exchange(array, j, k);
    }
  }

  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with a new java.util.Random object
   *
   * @param array array to be shuffled
   */
  public static void shuffle(float[] array) {
    shuffle(array, new Random());
  }
  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with a new java.util.Random object
   * created from the provided seed
   *
   * @param array array to be shuffled
   * @param seed the seed to use for the pseudo-random number generator
   */
  public static void shuffle(float[] array, long seed) {
    shuffle(array, new Random(seed));
  }
  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with the provided java.util.Random
   * object
   *
   * @param array array to be shuffled
   * @param random the random object to use
   */
  public static void shuffle(float[] array, Random random) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = array.length - 1; k > 0; k--) {
      int j = random.nextInt(k);

      exchange(array, j, k);
    }
  }


  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with a new java.util.Random object
   *
   * @param array array to be shuffled
   */
  public static void shuffle(char[] array) {
    shuffle(array, new Random());
  }
  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with a new java.util.Random object
   * created from the provided seed
   *
   * @param array array to be shuffled
   * @param seed the seed to use for the pseudo-random number generator
   */
  public static void shuffle(char[] array, long seed) {
    shuffle(array, new Random(seed));
  }
  /**
   *
   * Performs a Fisher-Yates shuffle on the
   * given array with the provided java.util.Random
   * object
   *
   * @param array array to be shuffled
   * @param random the random object to use
   */
  public static void shuffle(char[] array, Random random) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = array.length - 1; k > 0; k--) {
      int j = random.nextInt(k);

      exchange(array, j, k);
    }
  }







  public static <T> void exchange(T[] array, int i0, int i1) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    if (i0 < 0 || i0 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i0));
    if (i1 < 0 || i1 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i1));

    T trans = array[i0];
    array[i0] = array[i1];
    array[i1] = trans;

  }


  public static void exchange(long[] array, int i0, int i1) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    if (i0 < 0 || i0 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i0));
    if (i1 < 0 || i1 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i1));

    long trans = array[i0];
    array[i0] = array[i1];
    array[i1] = trans;

  }

  public static void exchange(int[] array, int i0, int i1) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    if (i0 < 0 || i0 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i0));
    if (i1 < 0 || i1 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i1));

    int trans = array[i0];
    array[i0] = array[i1];
    array[i1] = trans;

  }

  public static void exchange(short[] array, int i0, int i1) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    if (i0 < 0 || i0 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i0));
    if (i1 < 0 || i1 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i1));

    short trans = array[i0];
    array[i0] = array[i1];
    array[i1] = trans;

  }

  public static void exchange(byte[] array, int i0, int i1) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    if (i0 < 0 || i0 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i0));
    if (i1 < 0 || i1 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i1));

    byte trans = array[i0];
    array[i0] = array[i1];
    array[i1] = trans;

  }

  public static void exchange(float[] array, int i0, int i1) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    if (i0 < 0 || i0 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i0));
    if (i1 < 0 || i1 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i1));

    float trans = array[i0];
    array[i0] = array[i1];
    array[i1] = trans;

  }

  public static void exchange(double[] array, int i0, int i1) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    if (i0 < 0 || i0 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i0));
    if (i1 < 0 || i1 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i1));

    double trans = array[i0];
    array[i0] = array[i1];
    array[i1] = trans;

  }

  public static void exchange(char[] array, int i0, int i1) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    if (i0 < 0 || i0 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i0));
    if (i1 < 0 || i1 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i1));

    char trans = array[i0];
    array[i0] = array[i1];
    array[i1] = trans;

  }



  public static <T> T[] reverse(T[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < (double) array.length / 2; k++) {
      exchange(array, k, array.length - 1 - k);
    }

    return array;
  }

  public static long[] reverse(long[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < (double) array.length / 2; k++) {
      exchange(array, k, array.length - 1 - k);
    }

    return array;
  }

  public static int[] reverse(int[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < (double) array.length / 2; k++) {
      exchange(array, k, array.length - 1 - k);
    }

    return array;
  }

  public static short[] reverse(short[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < (double) array.length / 2; k++) {
      exchange(array, k, array.length - 1 - k);
    }

    return array;
  }

  public static byte[] reverse(byte[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < (double) array.length / 2; k++) {
      exchange(array, k, array.length - 1 - k);
    }

    return array;
  }

  public static double[] reverse(double[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < (double) array.length / 2; k++) {
      exchange(array, k, array.length - 1 - k);
    }

    return array;
  }

  public static float[] reverse(float[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < (double) array.length / 2; k++) {
      exchange(array, k, array.length - 1 - k);
    }

    return array;
  }

  public static char[] reverse(char[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < (double) array.length / 2; k++) {
      exchange(array, k, array.length - 1 - k);
    }

    return array;
  }


  public static <T> T[] fill(T[] array, T value) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < array.length; k++) {
      array[k] = value;
    }

    return array;
  }

  public static long[] fill(long[] array, long value) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < array.length; k++) {
      array[k] = value;
    }

    return array;
  }
  public static int[] fill(int[] array, int value) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < array.length; k++) {
      array[k] = value;
    }

    return array;
  }
  public static short[] fill(short[] array, short value) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < array.length; k++) {
      array[k] = value;
    }

    return array;
  }
  public static byte[] fill(byte[] array, byte value) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < array.length; k++) {
      array[k] = value;
    }

    return array;
  }

  public static float[] fill(float[] array, float value) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < array.length; k++) {
      array[k] = value;
    }

    return array;
  }
  public static double[] fill(double[] array, double value) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < array.length; k++) {
      array[k] = value;
    }

    return array;
  }
  public static char[] fill(char[] array, char value) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < array.length; k++) {
      array[k] = value;
    }

    return array;
  }




  public static <T> T[] fill(T[] array, ObjectArrayFillInterface<T> filler) {
    if (filler == null)
      throw new IllegalArgumentException("Fill interface cannot be null");
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < array.length; k++) {
      array[k] = filler.get(k);
    }

    return array;
  }

  public static long[] fill(long[] array, LongArrayFillInterface filler) {
    if (filler == null)
      throw new IllegalArgumentException("Fill interface cannot be null");
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < array.length; k++) {
      array[k] = filler.get(k);
    }

    return array;
  }
  public static int[] fill(int[] array, IntArrayFillInterface filler) {
    if (filler == null)
      throw new IllegalArgumentException("Fill interface cannot be null");
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < array.length; k++) {
      array[k] = filler.get(k);
    }

    return array;
  }
  public static short[] fill(short[] array, ShortArrayFillInterface filler) {
    if (filler == null)
      throw new IllegalArgumentException("Fill interface cannot be null");
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < array.length; k++) {
      array[k] = filler.get(k);
    }

    return array;
  }
  public static byte[] fill(byte[] array, ByteArrayFillInterface filler) {
    if (filler == null)
      throw new IllegalArgumentException("Fill interface cannot be null");
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < array.length; k++) {
      array[k] = filler.get(k);
    }

    return array;
  }

  public static float[] fill(float[] array, FloatArrayFillInterface filler) {
    if (filler == null)
      throw new IllegalArgumentException("Fill interface cannot be null");
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < array.length; k++) {
      array[k] = filler.get(k);
    }

    return array;
  }
  public static double[] fill(double[] array, DoubleArrayFillInterface filler) {
    if (filler == null)
      throw new IllegalArgumentException("Fill interface cannot be null");
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < array.length; k++) {
      array[k] = filler.get(k);
    }

    return array;
  }
  public static char[] fill(char[] array, CharArrayFillInterface filler) {
    if (filler == null)
      throw new IllegalArgumentException("Fill interface cannot be null");
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for(int k = 0; k < array.length; k++) {
      array[k] = filler.get(k);
    }

    return array;
  }



  public static <T> void forEach(T[] array, ObjectArrayForEachInterface<T> forEach) {
    if (forEach == null)
      throw new IllegalArgumentException("ForEach interface cannot be null");
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for (int k = 0; k < array.length; k++) {
      forEach.run(k, array[k]);
    }
  }
  public static void forEach(long[] array, LongArrayForEachInterface forEach) {
    if (forEach == null)
      throw new IllegalArgumentException("ForEach interface cannot be null");
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for (int k = 0; k < array.length; k++) {
      forEach.run(k, array[k]);
    }
  }
  public static void forEach(int[] array, IntArrayForEachInterface forEach) {
    if (forEach == null)
      throw new IllegalArgumentException("ForEach interface cannot be null");
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for (int k = 0; k < array.length; k++) {
      forEach.run(k, array[k]);
    }
  }
  public static void forEach(short[] array, ShortArrayForEachInterface forEach) {
    if (forEach == null)
      throw new IllegalArgumentException("ForEach interface cannot be null");
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for (int k = 0; k < array.length; k++) {
      forEach.run(k, array[k]);
    }
  }
  public static void forEach(byte[] array, ByteArrayForEachInterface forEach) {
    if (forEach == null)
      throw new IllegalArgumentException("ForEach interface cannot be null");
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for (int k = 0; k < array.length; k++) {
      forEach.run(k, array[k]);
    }
  }
  public static void forEach(double[] array, DoubleArrayForEachInterface forEach) {
    if (forEach == null)
      throw new IllegalArgumentException("ForEach interface cannot be null");
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for (int k = 0; k < array.length; k++) {
      forEach.run(k, array[k]);
    }
  }
  public static void forEach(float[] array, FloatArrayForEachInterface forEach) {
    if (forEach == null)
      throw new IllegalArgumentException("ForEach interface cannot be null");
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for (int k = 0; k < array.length; k++) {
      forEach.run(k, array[k]);
    }
  }
  public static void forEach(char[] array, CharArrayForEachInterface forEach) {
    if (forEach == null)
      throw new IllegalArgumentException("ForEach interface cannot be null");
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    for (int k = 0; k < array.length; k++) {
      forEach.run(k, array[k]);
    }
  }



  public static long[] toLongArray(byte[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    long[] ret = new long[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (long) array[k];
    }

    return ret;
  }
  public static long[] toLongArray(short[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    long[] ret = new long[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (long) array[k];
    }

    return ret;
  }
  public static long[] toLongArray(float[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    long[] ret = new long[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (long) array[k];
    }

    return ret;
  }
  public static long[] toLongArray(double[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    long[] ret = new long[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (long) array[k];
    }

    return ret;
  }
  public static long[] toLongArray(int[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    long[] ret = new long[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (long) array[k];
    }

    return ret;
  }




  public static short[] toShortArray(byte[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    short[] ret = new short[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (short) array[k];
    }

    return ret;
  }
  public static short[] toShortArray(long[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    short[] ret = new short[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (short) array[k];
    }

    return ret;
  }
  public static short[] toShortArray(float[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    short[] ret = new short[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (short) array[k];
    }

    return ret;
  }
  public static short[] toShortArray(double[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    short[] ret = new short[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (short) array[k];
    }

    return ret;
  }
  public static short[] toShortArray(int[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    short[] ret = new short[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (short) array[k];
    }

    return ret;
  }




  public static byte[] toByteArray(long[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    byte[] ret = new byte[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (byte) array[k];
    }

    return ret;
  }
  public static byte[] toByteArray(short[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    byte[] ret = new byte[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (byte) array[k];
    }

    return ret;
  }
  public static byte[] toByteArray(float[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    byte[] ret = new byte[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (byte) array[k];
    }

    return ret;
  }
  public static byte[] toByteArray(double[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    byte[] ret = new byte[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (byte) array[k];
    }

    return ret;
  }
  public static byte[] toByteArray(int[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    byte[] ret = new byte[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (byte) array[k];
    }

    return ret;
  }





  public static int[] toIntArray(byte[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    int[] ret = new int[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (int) array[k];
    }

    return ret;
  }
  public static int[] toIntArray(short[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    int[] ret = new int[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (int) array[k];
    }

    return ret;
  }
  public static int[] toIntArray(long[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    int[] ret = new int[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (int) array[k];
    }

    return ret;
  }
  public static int[] toIntArray(float[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    int[] ret = new int[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (int) array[k];
    }

    return ret;
  }
  public static int[] toIntArray(double[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    int[] ret = new int[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (int) array[k];
    }

    return ret;
  }




  public static double[] toDoubleArray(byte[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    double[] ret = new double[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (double) array[k];
    }

    return ret;
  }
  public static double[] toDoubleArray(short[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    double[] ret = new double[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (double) array[k];
    }

    return ret;
  }
  public static double[] toDoubleArray(long[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    double[] ret = new double[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (double) array[k];
    }

    return ret;
  }
  public static double[] toDoubleArray(float[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    double[] ret = new double[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (double) array[k];
    }

    return ret;
  }
  public static double[] toDoubleArray(int[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    double[] ret = new double[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (double) array[k];
    }

    return ret;
  }



  public static float[] toFloatArray(byte[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    float[] ret = new float[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (float) array[k];
    }

    return ret;
  }
  public static float[] toFloatArray(short[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    float[] ret = new float[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (float) array[k];
    }

    return ret;
  }
  public static float[] toFloatArray(long[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    float[] ret = new float[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (float) array[k];
    }

    return ret;
  }
  public static float[] toFloatArray(double[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    float[] ret = new float[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (float) array[k];
    }

    return ret;
  }
  public static float[] toFloatArray(int[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    float[] ret = new float[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (float) array[k];
    }

    return ret;
  }



  public static Integer[] toWrapperArray(int[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    Integer[] ret = new Integer[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = new Integer(array[k]);
    }

    return ret;
  }
  public static Long[] toWrapperArray(long[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    Long[] ret = new Long[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = new Long(array[k]);
    }

    return ret;
  }
  public static Short[] toWrapperArray(short[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    Short[] ret = new Short[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = new Short(array[k]);
    }

    return ret;
  }
  public static Byte[] toWrapperArray(byte[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    Byte[] ret = new Byte[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = new Byte(array[k]);
    }

    return ret;
  }
  public static Float[] toWrapperArray(float[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    Float[] ret = new Float[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = new Float(array[k]);
    }

    return ret;
  }
  public static Double[] toWrapperArray(double[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    Double[] ret = new Double[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = new Double(array[k]);
    }

    return ret;
  }
  public static Character[] toWrapperArray(char[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    Character[] ret = new Character[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = new Character(array[k]);
    }

    return ret;
  }




  public static int[] toPrimitiveArray(Integer[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    int[] ret = new int[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = array[k].intValue();
    }

    return ret;
  }
  public static long[] toPrimitiveArray(Long[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    long[] ret = new long[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = array[k].longValue();
    }

    return ret;
  }
  public static short[] toPrimitiveArray(Short[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    short[] ret = new short[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = array[k].shortValue();
    }

    return ret;
  }
  public static byte[] toPrimitiveArray(Byte[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    byte[] ret = new byte[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = array[k].byteValue();
    }

    return ret;
  }
  public static float[] toPrimitiveArray(Float[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    float[] ret = new float[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = array[k].floatValue();
    }

    return ret;
  }
  public static double[] toPrimitiveArray(Double[] array) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");

    double[] ret = new double[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = array[k].doubleValue();
    }

    return ret;
  }
  public static char[] toPrimitiveArray(Character[] array) {

    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");


    char[] ret = new char[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = array[k].charValue();
    }

    return ret;
  }



  public static <T, U> T[] copy(T[] destination, U[] source) {
    if (destination == null || source == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (destination.length != source.length)
      throw new IllegalArgumentException("destination and source have incompatible lengths");

    for(int k = 0; k < destination.length; k++) {
      destination[k] = (T) source[k];
    }

    return destination;
  }


  public static <T> Object[] concat(T[] a1, T[] a2) {
    return concat(new Object[a1.length + a2.length], a1, a2);
  }
  public static <T, U> U[] concat(U[] ret, T[] a1, T[] a2) {

    if (ret == null || a1 == null || a2 == null)
      throw new IllegalArgumentException("Array cannot be null");

    if (ret.length != a1.length + a2.length)
      throw new IllegalArgumentException("Destination array size does not match sum of input arrays");

    for(int k = 0; k < a1.length; k++) {
      ret[k] = (U) a1[k];
    }
    for(int k = 0; k < a2.length; k++) {
      ret[k + a1.length] = (U) a2[k];
    }

    return ret;

  }

  public static long[] concat(long[] a1, long[] a2) {

    if (a1 == null || a2 == null)
      throw new IllegalArgumentException("Array cannot be null");

    long[] ret = new long[a1.length + a2.length];

    for(int k = 0; k < a1.length; k++) {
      ret[k] = a1[k];
    }
    for(int k = 0; k < a2.length; k++) {
      ret[k + a1.length] = a2[k];
    }

    return ret;
  }
  public static int[] concat(int[] a1, int[] a2) {

    if (a1 == null || a2 == null)
      throw new IllegalArgumentException("Array cannot be null");

    int[] ret = new int[a1.length + a2.length];

    for(int k = 0; k < a1.length; k++) {
      ret[k] = a1[k];
    }
    for(int k = 0; k < a2.length; k++) {
      ret[k + a1.length] = a2[k];
    }

    return ret;
  }
  public static short[] concat(short[] a1, short[] a2) {

    if (a1 == null || a2 == null)
      throw new IllegalArgumentException("Array cannot be null");

    short[] ret = new short[a1.length + a2.length];

    for(int k = 0; k < a1.length; k++) {
      ret[k] = a1[k];
    }
    for(int k = 0; k < a2.length; k++) {
      ret[k + a1.length] = a2[k];
    }

    return ret;
  }
  public static byte[] concat(byte[] a1, byte[] a2) {

    if (a1 == null || a2 == null)
      throw new IllegalArgumentException("Array cannot be null");

    byte[] ret = new byte[a1.length + a2.length];

    for(int k = 0; k < a1.length; k++) {
      ret[k] = a1[k];
    }
    for(int k = 0; k < a2.length; k++) {
      ret[k + a1.length] = a2[k];
    }

    return ret;
  }
  public static float[] concat(float[] a1, float[] a2) {

    if (a1 == null || a2 == null)
      throw new IllegalArgumentException("Array cannot be null");

    float[] ret = new float[a1.length + a2.length];

    for(int k = 0; k < a1.length; k++) {
      ret[k] = a1[k];
    }
    for(int k = 0; k < a2.length; k++) {
      ret[k + a1.length] = a2[k];
    }

    return ret;
  }
  public static double[] concat(double[] a1, double[] a2) {

    if (a1 == null || a2 == null)
      throw new IllegalArgumentException("Array cannot be null");

    double[] ret = new double[a1.length + a2.length];

    for(int k = 0; k < a1.length; k++) {
      ret[k] = a1[k];
    }
    for(int k = 0; k < a2.length; k++) {
      ret[k + a1.length] = a2[k];
    }

    return ret;
  }
  public static char[] concat(char[] a1, char[] a2) {

    if (a1 == null || a2 == null)
      throw new IllegalArgumentException("Array cannot be null");

    char[] ret = new char[a1.length + a2.length];

    for(int k = 0; k < a1.length; k++) {
      ret[k] = a1[k];
    }
    for(int k = 0; k < a2.length; k++) {
      ret[k + a1.length] = a2[k];
    }

    return ret;
  }



  public static <T> T[] subArray(T[] array, T[] destination, int start, int end) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (destination == null)
      throw new IllegalArgumentException("Destination Array cannot be null");

    if (start < 0)
      throw new IllegalArgumentException("Start cannot be less than zero");
    if (end > array.length)
      throw new IllegalArgumentException("End cannot be greater than the length of the array");
    if (end < start)
      throw new IllegalArgumentException("Start cannot be greater than end");
    if (destination.length != end - start)
      throw new IllegalArgumentException("Destination array is of the wrong size");

    for (int k = start; k < end; k++) {
      destination[k - start] = array[k];
    }

    return array;
  }

  public static byte[] subArray(byte[] array, int start, int end) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (start < 0)
      throw new IllegalArgumentException("Start cannot be less than zero");
    if (end > array.length)
      throw new IllegalArgumentException("End cannot be greater than the length of the array");
    if (end < start)
      throw new IllegalArgumentException("Start cannot be greater than end");

    byte[] destination = new byte[end - start];

    for (int k = start; k < end; k++) {
      destination[k - start] = array[k];
    }

    return destination;
  }

  public static short[] subArray(short[] array, int start, int end) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (start < 0)
      throw new IllegalArgumentException("Start cannot be less than zero");
    if (end > array.length)
      throw new IllegalArgumentException("End cannot be greater than the length of the array");
    if (end < start)
      throw new IllegalArgumentException("Start cannot be greater than end");

    short[] destination = new short[end - start];

    for (int k = start; k < end; k++) {
      destination[k - start] = array[k];
    }

    return destination;
  }

  public static int[] subArray(int[] array, int start, int end) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (start < 0)
      throw new IllegalArgumentException("Start cannot be less than zero");
    if (end > array.length)
      throw new IllegalArgumentException("End cannot be greater than the length of the array");
    if (end < start)
      throw new IllegalArgumentException("Start cannot be greater than end");

    int[] destination = new int[end - start];

    for (int k = start; k < end; k++) {
      destination[k - start] = array[k];
    }

    return destination;
  }

  public static long[] subArray(long[] array, int start, int end) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (start < 0)
      throw new IllegalArgumentException("Start cannot be less than zero");
    if (end > array.length)
      throw new IllegalArgumentException("End cannot be greater than the length of the array");
    if (end < start)
      throw new IllegalArgumentException("Start cannot be greater than end");

    long[] destination = new long[end - start];

    for (int k = start; k < end; k++) {
      destination[k - start] = array[k];
    }

    return destination;
  }

  public static float[] subArray(float[] array, int start, int end) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (start < 0)
      throw new IllegalArgumentException("Start cannot be less than zero");
    if (end > array.length)
      throw new IllegalArgumentException("End cannot be greater than the length of the array");
    if (end < start)
      throw new IllegalArgumentException("Start cannot be greater than end");

    float[] destination = new float[end - start];

    for (int k = start; k < end; k++) {
      destination[k - start] = array[k];
    }

    return destination;
  }

  public static double[] subArray(double[] array, int start, int end) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (start < 0)
      throw new IllegalArgumentException("Start cannot be less than zero");
    if (end > array.length)
      throw new IllegalArgumentException("End cannot be greater than the length of the array");
    if (end < start)
      throw new IllegalArgumentException("Start cannot be greater than end");

    double[] destination = new double[end - start];

    for (int k = start; k < end; k++) {
      destination[k - start] = array[k];
    }

    return destination;
  }

  public static char[] subArray(char[] array, int start, int end) {
    if (array == null)
      throw new IllegalArgumentException("Array cannot be null");
    if (start < 0)
      throw new IllegalArgumentException("Start cannot be less than zero");
    if (end > array.length)
      throw new IllegalArgumentException("End cannot be greater than the length of the array");
    if (end < start)
      throw new IllegalArgumentException("Start cannot be greater than end");

    char[] destination = new char[end - start];

    for (int k = start; k < end; k++) {
      destination[k - start] = array[k];
    }

    return destination;
  }



}
