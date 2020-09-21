package io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates;

import io.github.bhuwanupadhyay.ddd.AggregateRoot;
import io.github.bhuwanupadhyay.onlineshop.product.domain.commands.ProductCreateCommand;
import io.github.bhuwanupadhyay.onlineshop.product.domain.commands.ProductUpdateCommand;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.entities.Category;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.events.ProductCreated;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.events.ProductUpdated;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Categories;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.ProductId;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Product extends AggregateRoot<ProductId> {
    private final Set<Category> categories = new LinkedHashSet<>();
    private String name;
    private String description;

    public Product(ProductId id, ProductCreateCommand command) {
        super(id);
        this.name = command.name();
        this.description = command.description();
        this.registerEvent(new ProductCreated());
    }

    public void on(ProductUpdateCommand command, Categories categories) {
        this.name = command.name();
        this.description = command.description();
        command.categoryIds()
                .stream()
                .map(categories::find)
                .forEachOrdered(this.categories::add);
        this.registerEvent(new ProductUpdated());
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(this.categories);
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
