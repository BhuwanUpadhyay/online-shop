package io.github.bhuwanupadhyay.onlineshop.product.infrastructure.repositories.jpa.db;


import io.github.bhuwanupadhyay.onlineshop.product.domain.commands.ProductCreateCommand;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.ProductId;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity(name = "products")
public class ProductDb {

    @Id
    private String id;
    private String name;
    private String description;

    @SuppressWarnings("FieldMayBeFinal")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
    private Set<CategoryDb> categories = new HashSet<>();

    public ProductDb() {
    }

    public ProductDb(Product product) {
        this.id = product.getId().id().toString();
        this.name = product.getName();
        this.description = product.getDescription();

        Set<CategoryDb> categoryDbs = product.getCategories()
                .stream()
                .map(category -> new CategoryDb(category, this))
                .collect(Collectors.toSet());

        this.categories.addAll(categoryDbs);
    }

    public Product toDomain() {
        Product product = new Product(
                new ProductId(UUID.fromString(this.id)),
                new ProductCreateCommand(this.name, this.description)
        );
        return product;
    }

}
