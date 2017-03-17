package jmath.function;

/**
 * Author:    LeqxLeqx
 */
public interface Evaluable {

  Evaluable clone();
  Value getValue(ArgumentList argList);
  EvaluableType getEvaluableType();
  Evaluable derivative(String variable);
  boolean isZero();
  boolean isOne();
  Evaluable replaceVariable(String varName, Evaluable evaluable);

}
