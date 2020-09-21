package io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest;


import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.CategoryId;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProductTransformer {

    public ProductResource toResource(Product product) {
        return new ProductResource()
                .id(product.getId().id().toString())
                .name(product.getName())
                .description(product.getDescription());
    }

    public Set<CategoryId> toCategories(JsonNullable<List<CategoryRef>> categoryRefs) {
        return categoryRefs.orElse(Collections.emptyList())
                .stream()
                .map(categoryRef -> new CategoryId(UUID.fromString(categoryRef.getId())))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
