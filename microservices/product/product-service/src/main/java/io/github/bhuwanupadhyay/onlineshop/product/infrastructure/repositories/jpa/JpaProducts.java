package io.github.bhuwanupadhyay.onlineshop.product.infrastructure.repositories.jpa;

import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Products;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.ProductId;
import io.github.bhuwanupadhyay.onlineshop.product.infrastructure.repositories.jpa.db.ProductDb;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaProducts extends Products {
    private final ProductDbRepository dbRepository;

    public JpaProducts(DomainEventPublisher publisher, ProductDbRepository dbRepository) {
        super(publisher);
        this.dbRepository = dbRepository;
    }

    @Override
    public Optional<Product> findOne(ProductId id) {
        return dbRepository.findById(id.id().toString()).map(ProductDb::toDomain);
    }

    @Override
    protected void persist(Product entity) {
        dbRepository.save(new ProductDb(entity));
    }

    @Override
    public ProductId nextId() {
        return new ProductId(UUID.randomUUID());
    }

}
