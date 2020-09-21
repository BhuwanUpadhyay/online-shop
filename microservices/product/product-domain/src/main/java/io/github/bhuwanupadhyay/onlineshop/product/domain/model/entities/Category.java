package io.github.bhuwanupadhyay.onlineshop.product.domain.model.entities;

import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.CategoryId;
import org.jddd.core.types.AggregateRoot;

public class Category implements AggregateRoot<Category, CategoryId> {
    private final CategoryId id;
    private String name;
    private String description;

    public Category(CategoryId id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void edit(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public CategoryId getId() {
        return id;
    }
}
