package io.github.bhuwanupadhyay.ddd;

public interface ValueObject {
    @Override
    int hashCode();

    @Override
    boolean equals(Object o);
}
