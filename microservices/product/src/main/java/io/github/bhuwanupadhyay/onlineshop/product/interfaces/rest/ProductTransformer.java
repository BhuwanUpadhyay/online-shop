package io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest;


import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductTransformer {

    public ProductResource toResource(Product product) {
        return new ProductResource()
                .id(product.getId().getId().toString())
                .name(product.getName())
                .description(product.getDescription());
    }

}
