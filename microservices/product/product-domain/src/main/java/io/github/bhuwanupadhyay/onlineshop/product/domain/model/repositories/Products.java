package io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories;

import io.github.bhuwanupadhyay.ddd.AggregateRepository;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.ProductId;

public abstract class Products extends AggregateRepository<Product, ProductId> {

    protected Products(DomainEventPublisher publisher) {
        super(publisher);
    }
}
