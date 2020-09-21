package io.github.bhuwanupadhyay.onlineshop.product.domain.services;

import io.github.bhuwanupadhyay.core.CommandService;
import io.github.bhuwanupadhyay.core.Result;
import io.github.bhuwanupadhyay.onlineshop.product.domain.commands.ProductUpdateCommand;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Categories;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Products;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.ProductId;

public class UpdateProductCommandService implements CommandService<ProductUpdateCommand, ProductId> {

    private final Products products;
    private final Categories categories;

    public UpdateProductCommandService(Products products, Categories categories) {
        this.products = products;
        this.categories = categories;
    }

    @Override
    public Result<ProductId> execute(ProductId id, ProductUpdateCommand command) {
        Product product = products.findOne(id);
        product.on(command, categories);
        Product saved = products.save(product);
        return new Result<>(saved.getId());
    }
}
