package io.github.bhuwanupadhyay.rules;

import io.github.bhuwanupadhyay.core.Result;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class SyntaxRules<T> {
  private final Validator validator;

  public SyntaxRules(Validator validator) {
    this.validator = validator;
  }

  public Result<T> apply(T source) {
    if (Objects.isNull(source)) {
      return new Result<>(List.of(new NullObjectProblem()));
    } else {
      Set<ConstraintViolation<T>> violations = validator.validate(source, SyntaxRule.class);
      if (violations.isEmpty()) {
        return new Result<>(source);
      }
      return new Result<>(violations.stream().map(BasicProblem::create).collect(Collectors.toList()));
    }
  }

}
