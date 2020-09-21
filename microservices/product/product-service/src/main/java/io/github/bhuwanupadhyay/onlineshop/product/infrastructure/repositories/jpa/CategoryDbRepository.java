package io.github.bhuwanupadhyay.onlineshop.product.infrastructure.repositories.jpa;

import io.github.bhuwanupadhyay.onlineshop.product.infrastructure.repositories.jpa.db.CategoryDb;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDbRepository extends CrudRepository<CategoryDb, String> {
}
