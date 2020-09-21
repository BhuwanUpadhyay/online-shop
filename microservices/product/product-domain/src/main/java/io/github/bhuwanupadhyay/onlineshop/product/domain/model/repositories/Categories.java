package io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories;

import io.github.bhuwanupadhyay.ddd.EntityRepository;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.entities.Category;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.CategoryId;

public abstract class Categories extends EntityRepository<Category, CategoryId> {
}
