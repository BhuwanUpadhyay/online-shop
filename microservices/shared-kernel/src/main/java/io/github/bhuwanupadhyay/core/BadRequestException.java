package io.github.bhuwanupadhyay.core;

import java.util.List;

public class BadRequestException extends RuntimeException {

    private final List<Problem> problems;

    public BadRequestException(List<Problem> problems) {
        this.problems = problems;
    }

    public List<Problem> getProblems() {
        return problems;
    }
}
