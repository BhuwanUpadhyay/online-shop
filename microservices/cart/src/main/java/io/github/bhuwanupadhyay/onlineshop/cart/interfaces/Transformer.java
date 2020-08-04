package io.github.bhuwanupadhyay.onlineshop.cart;

public interface Transformer<E, R> {

    E toEntity(R resource);

    R toResource(E entity);

}
