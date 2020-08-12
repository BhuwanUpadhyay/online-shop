package io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories;

import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import io.github.bhuwanupadhyay.ddd.DomainRepository;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Cart;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.UserId;

public abstract class CartRepository extends DomainRepository<Cart, UserId> {
    public CartRepository(DomainEventPublisher publisher) {
        super(publisher);
    }
}
