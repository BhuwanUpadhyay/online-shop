package io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories;

import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product.ProductId;
import org.springframework.data.repository.CrudRepository;

public interface Products extends CrudRepository<Product, ProductId> {
}
