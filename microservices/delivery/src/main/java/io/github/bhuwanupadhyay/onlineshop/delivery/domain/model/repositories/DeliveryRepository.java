package io.github.bhuwanupadhyay.onlineshop.delivery.domain.model.repositories;

import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import io.github.bhuwanupadhyay.ddd.DomainRepository;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.aggregates.Cart;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.valueobjects.UserId;

public abstract class CartRepository extends DomainRepository<Cart, UserId> {
    public CartRepository(DomainEventPublisher publisher) {
        super(publisher);
    }
}
