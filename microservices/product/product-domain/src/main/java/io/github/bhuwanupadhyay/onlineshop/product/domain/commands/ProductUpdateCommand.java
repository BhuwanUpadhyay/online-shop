package io.github.bhuwanupadhyay.onlineshop.product.domain.commands;

import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.CategoryId;

import java.util.Set;

public record ProductUpdateCommand(String name,
                                   String description,
                                   Set<CategoryId> categoryIds) {

    public ProductUpdateCommand {

    }
}
