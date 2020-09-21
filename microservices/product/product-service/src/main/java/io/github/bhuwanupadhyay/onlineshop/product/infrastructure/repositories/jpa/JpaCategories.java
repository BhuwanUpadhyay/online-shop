package io.github.bhuwanupadhyay.onlineshop.product.infrastructure.repositories.jpa;

import io.github.bhuwanupadhyay.onlineshop.product.domain.model.entities.Category;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Categories;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.CategoryId;
import io.github.bhuwanupadhyay.onlineshop.product.infrastructure.repositories.jpa.db.CategoryDb;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaCategories extends Categories {

    private final CategoryDbRepository dbRepository;

    public JpaCategories(CategoryDbRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    @Override
    public Optional<Category> findOne(CategoryId categoryId) {
        return dbRepository.findById(categoryId.id().toString()).map(CategoryDb::toDomain);
    }

    @Override
    public CategoryId nextId() {
        return new CategoryId(UUID.randomUUID());
    }
}
