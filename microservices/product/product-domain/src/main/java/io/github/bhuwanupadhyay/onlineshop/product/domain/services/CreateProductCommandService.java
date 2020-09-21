package io.github.bhuwanupadhyay.onlineshop.product.domain.services;

import io.github.bhuwanupadhyay.core.CommandService;
import io.github.bhuwanupadhyay.core.Result;
import io.github.bhuwanupadhyay.onlineshop.product.domain.commands.ProductCreateCommand;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Products;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.ProductId;

public class CreateProductCommandService implements CommandService<ProductCreateCommand, ProductId> {
    private final Products products;

    public CreateProductCommandService(Products products) {
        this.products = products;
    }

    @Override
    public Result<ProductId> execute(ProductId id, ProductCreateCommand command) {
        Product product = new Product(id, command);
        Product saved = products.save(product);
        return new Result<>(saved.getId());
    }
}
