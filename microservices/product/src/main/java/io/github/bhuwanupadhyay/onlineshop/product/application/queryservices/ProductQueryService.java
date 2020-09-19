package io.github.bhuwanupadhyay.onlineshop.product.application.queryservices;

import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ProductQueryService extends PagingAndSortingRepository<Product, Product.ProductId>, QueryByExampleExecutor<Product> {

}
