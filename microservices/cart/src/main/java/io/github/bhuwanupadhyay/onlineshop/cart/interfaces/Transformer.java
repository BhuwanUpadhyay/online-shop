package io.github.bhuwanupadhyay.onlineshop.cart.interfaces;

public interface Transformer<E, R> {

    E toDomain(R resource);

    R toResource(E entity);

}
