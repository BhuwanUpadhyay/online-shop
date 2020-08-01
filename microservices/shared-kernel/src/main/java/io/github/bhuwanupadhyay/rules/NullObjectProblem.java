package io.github.bhuwanupadhyay.rules;

public class NullObjectProblem extends BasicProblem {
  public NullObjectProblem() {
    super("$.NullObject", "$.NullObject", "$.NullObject", SyntaxRule.IsRequired);
  }
}
