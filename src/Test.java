import jmath.JMath;
import jmath.types.Matrix;
import jmath.types.Vector;

import java.util.Random;

/**
 * Created by mitchell on 20/04/17.
 */
public class Test {

  public static void main(String[] args) {

    Random r = new Random();

    for(int k = 0; k < 10; k++) {
      Vector v = new Vector(r.nextDouble(), r.nextDouble(), r.nextDouble());
      Matrix matrix = Matrix.rotation(r.nextDouble(), r.nextDouble(), r.nextDouble());

      Vector ret = matrix.transform(v);

      double vmag = v.absoluteValue(), retmag = ret.absoluteValue();
      boolean same = JMath.abs(vmag - retmag) < .01;

      System.out.printf("%s %s %s   ", vmag, same ? "==" : "!=", retmag);
      if (!same) {
        System.out.printf("\033[31mFAILURE\033[38m\n");
      }
      else {
        System.out.printf("\033[34mSUCCESS\033[38m\n");
      }

    }


  }

}
