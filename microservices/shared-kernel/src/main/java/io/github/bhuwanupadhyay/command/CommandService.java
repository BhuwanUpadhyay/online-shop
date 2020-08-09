package io.github.bhuwanupadhyay.command;

import io.github.bhuwanupadhyay.core.Result;

@FunctionalInterface
public interface CommandService<C, R> {

    Result<R> execute(C command);
}
