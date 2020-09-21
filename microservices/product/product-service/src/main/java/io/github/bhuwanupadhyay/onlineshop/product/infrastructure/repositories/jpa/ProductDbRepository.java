package io.github.bhuwanupadhyay.onlineshop.product.infrastructure.repositories.jpa;

import io.github.bhuwanupadhyay.onlineshop.product.infrastructure.repositories.jpa.db.ProductDb;
import org.springframework.data.repository.CrudRepository;

public interface ProductDbRepository extends CrudRepository<ProductDb, String> {
}
