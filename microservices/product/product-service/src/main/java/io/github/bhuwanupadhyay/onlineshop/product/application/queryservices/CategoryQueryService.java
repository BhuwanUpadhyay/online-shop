package io.github.bhuwanupadhyay.onlineshop.product.application.queryservices;

import io.github.bhuwanupadhyay.onlineshop.product.domain.model.entities.Category;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.CategoryId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CategoryQueryService extends PagingAndSortingRepository<Category, CategoryId>, QueryByExampleExecutor<Category> {

}
