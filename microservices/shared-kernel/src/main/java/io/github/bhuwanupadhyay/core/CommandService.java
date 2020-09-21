package io.github.bhuwanupadhyay.core;

@FunctionalInterface
public interface CommandService<C, ID> {

    Result<ID> execute(ID id, C command);
}
