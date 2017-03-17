package jmath.types;

/**
 * Author:    LeqxLeqx
 */
class Tools {

  static <T> boolean arrayContainsNulls(T[] array) {
    for(int k = 0; k < array.length; k++) {
      if (array[k] == null)
        return true;
    }

    return false;
  }

  static int numberOfOccurrences(String s, char i) {

    int ret = 0;

    for(char c : s.toCharArray()) {
      if (c == i)
        ret ++;
    }

    return ret;
  }


}
