package io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates;

import io.github.bhuwanupadhyay.onlineshop.product.domain.commands.ProductCreateCommand;
import io.github.bhuwanupadhyay.onlineshop.product.domain.commands.ProductUpdateCommand;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.entities.Category;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.events.ProductCreated;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.events.ProductUpdated;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Categories;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.ProductId;
import org.jddd.core.types.AggregateRoot;
import org.jddd.event.types.DomainEvent;

import java.util.*;

public class Product implements AggregateRoot<Product, ProductId> {
    private final ProductId id;
    private final Set<Category> categories = new LinkedHashSet<>();
    private final List<DomainEvent> domainEvents = new LinkedList<>();
    private String name;
    private String description;

    public Product(ProductId id, ProductCreateCommand command) {
        this.id = id;
        this.name = command.name();
        this.description = command.description();
        this.domainEvents.add(new ProductCreated());
    }

    public void on(ProductUpdateCommand command, Categories categories) {
        this.name = command.name();
        this.description = command.description();
        command.categoryIds()
                .stream()
                .map(categories::findOne)
                .forEachOrdered(this.categories::add);
        this.domainEvents.add(new ProductUpdated());
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(this.categories);
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(this.domainEvents);
    }

    @Override
    public ProductId getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
