package io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates;

import io.github.bhuwanupadhyay.onlineshop.product.domain.model.entities.Category;
import lombok.*;
import org.jddd.core.types.AggregateRoot;
import org.jddd.core.types.Identifier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@Entity
@Access(AccessType.FIELD)
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements AggregateRoot<Product, Product.ProductId> {
    @Id
    @EqualsAndHashCode.Include
    private final ProductId id;
    private String name;
    private String description;
    @OneToMany
    private List<Category> categories;

    public Product(ProductId id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Value
    @RequiredArgsConstructor(staticName = "of")
    @Embeddable
    @NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
    public static class ProductId implements Identifier, Serializable {

        private final UUID id;

        public static ProductId create() {
            return ProductId.of(UUID.randomUUID());
        }
    }
}
