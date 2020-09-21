package io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories;

import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.ProductId;

public interface Products extends Repository<Product, ProductId> {


}
