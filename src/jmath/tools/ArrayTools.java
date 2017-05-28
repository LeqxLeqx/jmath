package jmath.tools;

import java.util.Random;

/**
 * Created by mitchell on 01/05/17.
 */
public /*static*/ class ArrayTools {


  public static <T> boolean containsNull(T[] array) {

    for (T t : array) {
      if (t == null)
        return true;
    }

    return false;
  }

  public static <T> boolean contains(T[] array, T t) {

    for (T e : array) {
      if (e == t)
        return true;
    }

    return false;
  }
  public static boolean contains(long[] array, long value) {

    for(long l : array) {
      if (l == value)
        return true;
    }

    return false;
  }
  public static boolean contains(int[] array, int value) {

    for(int l : array) {
      if (l == value)
        return true;
    }

    return false;
  }
  public static boolean contains(short[] array, short value) {

    for(short l : array) {
      if (l == value)
        return true;
    }

    return false;
  }
  public static boolean contains(byte[] array, byte value) {

    for(byte l : array) {
      if (l == value)
        return true;
    }

    return false;
  }
  public static boolean contains(char[] array, char value) {

    for(char l : array) {
      if (l == value)
        return true;
    }

    return false;
  }
  public static boolean contains(float[] array, float value) {

    for(float l : array) {
      if (l == value)
        return true;
    }

    return false;
  }
  public static boolean contains(double[] array, double value) {

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



  public static <T> void shuffle(T[] array) {
    shuffle(array, new Random());
  }
  public static <T> void shuffle(T[] array, long seed) {
    shuffle(array, new Random(seed));
  }
  public static <T> void shuffle(T[] array, Random random) {

    for(int k = array.length - 1; k > 0; k--) {
      int j = random.nextInt(k);

      exchange(array, j, k);
    }

  }


  public static void shuffle(long[] array) {
    shuffle(array, new Random());
  }
  public static void shuffle(long[] array, long seed) {
    shuffle(array, new Random(seed));
  }
  public static void shuffle(long[] array, Random random) {

    for(int k = array.length - 1; k > 0; k--) {
      int j = random.nextInt(k);

      exchange(array, j, k);
    }
  }

  public static void shuffle(int[] array) {
    shuffle(array, new Random());
  }
  public static void shuffle(int[] array, long seed) {
    shuffle(array, new Random(seed));
  }
  public static void shuffle(int[] array, Random random) {

    for(int k = array.length - 1; k > 0; k--) {
      int j = random.nextInt(k);

      exchange(array, j, k);
    }
  }

  public static void shuffle(short[] array) {
    shuffle(array, new Random());
  }
  public static void shuffle(short[] array, long seed) {
    shuffle(array, new Random(seed));
  }
  public static void shuffle(short[] array, Random random) {

    for(int k = array.length - 1; k > 0; k--) {
      int j = random.nextInt(k);

      exchange(array, j, k);
    }
  }

  public static void shuffle(byte[] array) {
    shuffle(array, new Random());
  }
  public static void shuffle(byte[] array, long seed) {
    shuffle(array, new Random(seed));
  }
  public static void shuffle(byte[] array, Random random) {

    for(int k = array.length - 1; k > 0; k--) {
      int j = random.nextInt(k);

      exchange(array, j, k);
    }
  }

  public static void shuffle(double[] array) {
    shuffle(array, new Random());
  }
  public static void shuffle(double[] array, long seed) {
    shuffle(array, new Random(seed));
  }
  public static void shuffle(double[] array, Random random) {

    for(int k = array.length - 1; k > 0; k--) {
      int j = random.nextInt(k);

      exchange(array, j, k);
    }
  }

  public static void shuffle(float[] array) {
    shuffle(array, new Random());
  }
  public static void shuffle(float[] array, long seed) {
    shuffle(array, new Random(seed));
  }
  public static void shuffle(float[] array, Random random) {

    for(int k = array.length - 1; k > 0; k--) {
      int j = random.nextInt(k);

      exchange(array, j, k);
    }
  }







  public static <T> void exchange(T[] array, int i0, int i1) {

    if (i0 < 0 || i0 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i0));
    if (i1 < 0 || i1 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i1));

    T trans = array[i0];
    array[i0] = array[i1];
    array[i1] = trans;

  }


  public static void exchange(long[] array, int i0, int i1) {

    if (i0 < 0 || i0 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i0));
    if (i1 < 0 || i1 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i1));

    long trans = array[i0];
    array[i0] = array[i1];
    array[i1] = trans;

  }

  public static void exchange(int[] array, int i0, int i1) {

    if (i0 < 0 || i0 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i0));
    if (i1 < 0 || i1 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i1));

    int trans = array[i0];
    array[i0] = array[i1];
    array[i1] = trans;

  }

  public static void exchange(short[] array, int i0, int i1) {

    if (i0 < 0 || i0 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i0));
    if (i1 < 0 || i1 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i1));

    short trans = array[i0];
    array[i0] = array[i1];
    array[i1] = trans;

  }

  public static void exchange(byte[] array, int i0, int i1) {

    if (i0 < 0 || i0 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i0));
    if (i1 < 0 || i1 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i1));

    byte trans = array[i0];
    array[i0] = array[i1];
    array[i1] = trans;

  }

  public static void exchange(float[] array, int i0, int i1) {

    if (i0 < 0 || i0 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i0));
    if (i1 < 0 || i1 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i1));

    float trans = array[i0];
    array[i0] = array[i1];
    array[i1] = trans;

  }

  public static void exchange(double[] array, int i0, int i1) {

    if (i0 < 0 || i0 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i0));
    if (i1 < 0 || i1 >= array.length)
      throw new IllegalArgumentException(String.format("Index '%s' out of bounds", i1));

    double trans = array[i0];
    array[i0] = array[i1];
    array[i1] = trans;

  }



  public static <T> void reverse(T[] array) {
    for(int k = 0; k < (double) array.length / 2; k++) {
      exchange(array, k, array.length - 1 - k);
    }
  }

  public static void reverse(long[] array) {
    for(int k = 0; k < (double) array.length / 2; k++) {
      exchange(array, k, array.length - 1 - k);
    }
  }

  public static void reverse(int[] array) {
    for(int k = 0; k < (double) array.length / 2; k++) {
      exchange(array, k, array.length - 1 - k);
    }
  }

  public static void reverse(short[] array) {
    for(int k = 0; k < (double) array.length / 2; k++) {
      exchange(array, k, array.length - 1 - k);
    }
  }

  public static void reverse(byte[] array) {
    for(int k = 0; k < (double) array.length / 2; k++) {
      exchange(array, k, array.length - 1 - k);
    }
  }

  public static void reverse(double[] array) {
    for(int k = 0; k < (double) array.length / 2; k++) {
      exchange(array, k, array.length - 1 - k);
    }
  }

  public static void reverse(float[] array) {
    for(int k = 0; k < (double) array.length / 2; k++) {
      exchange(array, k, array.length - 1 - k);
    }
  }


  public static <T> void fill(T[] array, T value) {
    for(int k = 0; k < array.length; k++) {
      array[k] = value;
    }
  }

  public static void fill(long[] array, long value) {
    for(int k = 0; k < array.length; k++) {
      array[k] = value;
    }
  }
  public static void fill(int[] array, int value) {
    for(int k = 0; k < array.length; k++) {
      array[k] = value;
    }
  }
  public static void fill(short[] array, short value) {
    for(int k = 0; k < array.length; k++) {
      array[k] = value;
    }
  }
  public static void fill(byte[] array, byte value) {
    for(int k = 0; k < array.length; k++) {
      array[k] = value;
    }
  }

  public static void fill(float[] array, float value) {
    for(int k = 0; k < array.length; k++) {
      array[k] = value;
    }
  }
  public static void fill(double[] array, double value) {
    for(int k = 0; k < array.length; k++) {
      array[k] = value;
    }
  }




  public static <T> void fill(T[] array, ObjectArrayFillInterface<T> filler) {
    for(int k = 0; k < array.length; k++) {
      array[k] = filler.get(k);
    }
  }

  public static void fill(long[] array, LongArrayFillInterface filler) {
    for(int k = 0; k < array.length; k++) {
      array[k] = filler.get(k);
    }
  }
  public static void fill(int[] array, IntArrayFillInterface filler) {
    for(int k = 0; k < array.length; k++) {
      array[k] = filler.get(k);
    }
  }
  public static void fill(short[] array, ShortArrayFillInterface filler) {
    for(int k = 0; k < array.length; k++) {
      array[k] = filler.get(k);
    }
  }
  public static void fill(byte[] array, ByteArrayFillInterface filler) {
    for(int k = 0; k < array.length; k++) {
      array[k] = filler.get(k);
    }
  }

  public static void fill(float[] array, FloatArrayFillInterface filler) {
    for(int k = 0; k < array.length; k++) {
      array[k] = filler.get(k);
    }
  }
  public static void fill(double[] array, DoubleArrayFillInterface filler) {
    for(int k = 0; k < array.length; k++) {
      array[k] = filler.get(k);
    }
  }





  public static long[] toLongArray(byte[] array) {
    long[] ret = new long[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (long) array[k];
    }

    return ret;
  }
  public static long[] toLongArray(short[] array) {
    long[] ret = new long[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (long) array[k];
    }

    return ret;
  }
  public static long[] toLongArray(float[] array) {
    long[] ret = new long[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (long) array[k];
    }

    return ret;
  }
  public static long[] toLongArray(double[] array) {
    long[] ret = new long[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (long) array[k];
    }

    return ret;
  }
  public static long[] toLongArray(int[] array) {
    long[] ret = new long[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (long) array[k];
    }

    return ret;
  }




  public static short[] toShortArray(byte[] array) {
    short[] ret = new short[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (short) array[k];
    }

    return ret;
  }
  public static short[] toShortArray(long[] array) {
    short[] ret = new short[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (short) array[k];
    }

    return ret;
  }
  public static short[] toShortArray(float[] array) {
    short[] ret = new short[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (short) array[k];
    }

    return ret;
  }
  public static short[] toShortArray(double[] array) {
    short[] ret = new short[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (short) array[k];
    }

    return ret;
  }
  public static short[] toShortArray(int[] array) {
    short[] ret = new short[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (short) array[k];
    }

    return ret;
  }




  public static byte[] toByteArray(long[] array) {
    byte[] ret = new byte[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (byte) array[k];
    }

    return ret;
  }
  public static byte[] toByteArray(short[] array) {
    byte[] ret = new byte[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (byte) array[k];
    }

    return ret;
  }
  public static byte[] toByteArray(float[] array) {
    byte[] ret = new byte[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (byte) array[k];
    }

    return ret;
  }
  public static byte[] toByteArray(double[] array) {
    byte[] ret = new byte[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (byte) array[k];
    }

    return ret;
  }
  public static byte[] toByteArray(int[] array) {
    byte[] ret = new byte[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (byte) array[k];
    }

    return ret;
  }





  public static int[] toIntArray(byte[] array) {
    int[] ret = new int[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (int) array[k];
    }

    return ret;
  }
  public static int[] toIntArray(short[] array) {
    int[] ret = new int[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (int) array[k];
    }

    return ret;
  }
  public static int[] toIntArray(long[] array) {
    int[] ret = new int[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (int) array[k];
    }

    return ret;
  }
  public static int[] toIntArray(float[] array) {
    int[] ret = new int[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (int) array[k];
    }

    return ret;
  }
  public static int[] toIntArray(double[] array) {
    int[] ret = new int[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (int) array[k];
    }

    return ret;
  }




  public static double[] toDoubleArray(byte[] array) {
    double[] ret = new double[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (double) array[k];
    }

    return ret;
  }
  public static double[] toDoubleArray(short[] array) {
    double[] ret = new double[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (double) array[k];
    }

    return ret;
  }
  public static double[] toDoubleArray(long[] array) {
    double[] ret = new double[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (double) array[k];
    }

    return ret;
  }
  public static double[] toDoubleArray(float[] array) {
    double[] ret = new double[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (double) array[k];
    }

    return ret;
  }
  public static double[] toDoubleArray(int[] array) {
    double[] ret = new double[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (double) array[k];
    }

    return ret;
  }



  public static float[] toFloatArray(byte[] array) {
    float[] ret = new float[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (float) array[k];
    }

    return ret;
  }
  public static float[] toFloatArray(short[] array) {
    float[] ret = new float[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (float) array[k];
    }

    return ret;
  }
  public static float[] toFloatArray(long[] array) {
    float[] ret = new float[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (float) array[k];
    }

    return ret;
  }
  public static float[] toFloatArray(double[] array) {
    float[] ret = new float[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (float) array[k];
    }

    return ret;
  }
  public static float[] toFloatArray(int[] array) {
    float[] ret = new float[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = (float) array[k];
    }

    return ret;
  }



  public static Integer[] toWrapperArray(int[] array) {
    Integer[] ret = new Integer[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = new Integer(array[k]);
    }

    return ret;
  }
  public static Long[] toWrapperArray(long[] array) {
    Long[] ret = new Long[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = new Long(array[k]);
    }

    return ret;
  }
  public static Short[] toWrapperArray(short[] array) {
    Short[] ret = new Short[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = new Short(array[k]);
    }

    return ret;
  }
  public static Byte[] toWrapperArray(byte[] array) {
    Byte[] ret = new Byte[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = new Byte(array[k]);
    }

    return ret;
  }
  public static Float[] toWrapperArray(float[] array) {
    Float[] ret = new Float[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = new Float(array[k]);
    }

    return ret;
  }
  public static Double[] toWrapperArray(double[] array) {
    Double[] ret = new Double[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = new Double(array[k]);
    }

    return ret;
  }




  public static int[] toPrimitiveArray(Integer[] array) {
    int[] ret = new int[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = array[k].intValue();
    }

    return ret;
  }
  public static long[] toPrimitiveArray(Long[] array) {
    long[] ret = new long[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = array[k].longValue();
    }

    return ret;
  }
  public static short[] toPrimitiveArray(Short[] array) {
    short[] ret = new short[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = array[k].shortValue();
    }

    return ret;
  }
  public static byte[] toPrimitiveArray(Byte[] array) {
    byte[] ret = new byte[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = array[k].byteValue();
    }

    return ret;
  }
  public static float[] toPrimitiveArray(Float[] array) {
    float[] ret = new float[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = array[k].floatValue();
    }

    return ret;
  }
  public static double[] toPrimitiveArray(Double[] array) {
    double[] ret = new double[array.length];

    for(int k = 0; k < array.length; k++) {
      ret[k] = array[k].doubleValue();
    }

    return ret;
  }



  public static <T, U> T[] copy(T[] destination, U[] source) {
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

    double[] ret = new double[a1.length + a2.length];

    for(int k = 0; k < a1.length; k++) {
      ret[k] = a1[k];
    }
    for(int k = 0; k < a2.length; k++) {
      ret[k + a1.length] = a2[k];
    }

    return ret;
  }




}
