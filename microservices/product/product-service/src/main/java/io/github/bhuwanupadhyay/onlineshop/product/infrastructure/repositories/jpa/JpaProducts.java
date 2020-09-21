package io.github.bhuwanupadhyay.onlineshop.product.infrastructure.repositories.jpa;

import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Products;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.ProductId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaProducts extends Products {

    public JpaProducts(DomainEventPublisher publisher) {
        super(publisher);
    }

    @Override
    public Optional<Product> findOne(ProductId id) {
        return Optional.empty();
    }

    @Override
    protected void persist(Product entity) {

    }

    @Override
    public ProductId nextId() {
        return null;
    }

}
