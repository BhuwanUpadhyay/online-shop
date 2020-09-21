package io.github.bhuwanupadhyay.onlineshop.product.infrastructure.repositories.jpa.db;

import io.github.bhuwanupadhyay.onlineshop.product.domain.model.entities.Category;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.CategoryId;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "categories")
public class CategoryDb {

    @Id
    private String id;
    private String name;
    private String description;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductDb product;

    public CategoryDb() {
    }

    public CategoryDb(Category category, ProductDb product) {
        this.id = category.getId().id().toString();
        this.name = category.getName();
        this.description = category.getDescription();
        this.product = product;
    }

    public void setProduct(ProductDb product) {
        this.product = product;
    }

    public Category toDomain() {
        return new Category(new CategoryId(UUID.fromString(this.id)), this.name, this.description);
    }
}
