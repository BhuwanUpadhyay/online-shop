package io.github.bhuwanupadhyay.onlineshop.product.infrastructure.config;

import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Categories;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Products;
import io.github.bhuwanupadhyay.onlineshop.product.domain.services.CreateProductCommandService;
import io.github.bhuwanupadhyay.onlineshop.product.domain.services.UpdateProductCommandService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandServiceConfiguration {

    @Bean
    public UpdateProductCommandService updateProductCommandService(Products products, Categories categories) {
        return new UpdateProductCommandService(products, categories);
    }

    @Bean
    public CreateProductCommandService createProductCommandService(Products products) {
        return new CreateProductCommandService(products);
    }
}
