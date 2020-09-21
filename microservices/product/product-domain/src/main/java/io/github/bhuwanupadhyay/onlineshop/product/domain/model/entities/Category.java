package io.github.bhuwanupadhyay.onlineshop.product.domain.model.entities;

import io.github.bhuwanupadhyay.ddd.Entity;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.CategoryId;

public class Category extends Entity<CategoryId> {

    private String name;
    private String description;

    public Category(CategoryId id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public void edit(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
