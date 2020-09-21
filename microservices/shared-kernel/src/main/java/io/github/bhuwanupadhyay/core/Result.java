package io.github.bhuwanupadhyay.core;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Result<R> {

    private final R result;
    private final List<Problem> problems;

    public Result(R result) {
        this(result, null);
    }

    public Result(List<Problem> problems) {
        this(null, problems);
    }

    public Result(R result, List<Problem> problems) {
        this.result = result;
        this.problems = problems;
    }

    public List<Problem> getProblems() {
        List<Problem> list = Optional.ofNullable(this.problems).orElseGet(ArrayList::new);
        return List.copyOf(list);
    }

    public Optional<R> ok() {
        if (isOk()) {
            return Optional.of(result);
        }
        return Optional.empty();
    }

    public <T> Result<T> map(Function<R, Result<T>> func) {
        if (isOk()) {
            return func.apply(this.result);
        }
        return new Result<>(this.problems);
    }


    public boolean isOk() {
        return Optional.ofNullable(problems).map(List::isEmpty).orElse(true) && Objects.nonNull(result);
    }

    public boolean isBad() {
        return !isOk();
    }

    public Result<R> peek(Consumer<R> func) {
        if (isOk()) {
            func.accept(this.result);
            return new Result<>(this.result);
        }
        return new Result<>(this.problems);
    }
}
