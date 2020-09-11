package io.github.bhuwanupadhyay.onlineshop.product.domain.model.entities;

import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import lombok.*;
import org.jddd.core.types.Entity;
import org.jddd.core.types.Identifier;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@javax.persistence.Entity
@Access(AccessType.FIELD)
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category implements Entity<Product, Category.CategoryId> {
    @Id
    @EqualsAndHashCode.Include
    private final CategoryId id;
    private String name;
    private String description;
    private Boolean isRoot;
    private String parentId;

    @Value
    @Embeddable
    @RequiredArgsConstructor(staticName = "of")
    @NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
    public static class CategoryId implements Identifier, Serializable {

        private final UUID id;

        public static CategoryId create() {
            return CategoryId.of(UUID.randomUUID());
        }
    }
}
