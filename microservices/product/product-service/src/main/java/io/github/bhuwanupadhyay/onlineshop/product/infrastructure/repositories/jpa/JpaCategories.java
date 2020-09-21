package io.github.bhuwanupadhyay.onlineshop.product.infrastructure.repositories.jpa;

import io.github.bhuwanupadhyay.onlineshop.product.domain.model.entities.Category;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Categories;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.CategoryId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaCategories extends Categories {

    @Override
    public Optional<Category> findOne(CategoryId categoryId) {
        return Optional.empty();
    }

    @Override
    protected void persist(Category entity) {

    }

    @Override
    public CategoryId nextId() {
        return null;
    }
}
