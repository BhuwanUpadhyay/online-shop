package io.github.bhuwanupadhyay.core;

public interface Transformer<E, R> {

    E toDomain(R resource);

    R toResource(E domain);

}
