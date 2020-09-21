package io.github.bhuwanupadhyay.onlineshop.product.application.queryservices;

import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.ProductId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ProductQueryService extends PagingAndSortingRepository<Product, ProductId>, QueryByExampleExecutor<Product> {


    default Product find(ProductId id) {
        return this.findById(id).orElseThrow(RuntimeException::new);
    }
}
