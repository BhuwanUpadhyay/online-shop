package io.github.bhuwanupadhyay.onlineshop.product.domain.commands;

import io.github.bhuwanupadhyay.core.Problem;

import java.util.LinkedList;
import java.util.List;

public record ProductCreateCommand(String name,
                                   String description) {
    public ProductCreateCommand {
        // begin syntax validation
        List<Problem> problems = new LinkedList<>();

        Problem.raiseBadIfNotEmpty(problems);
        // end syntax validation
    }
}
