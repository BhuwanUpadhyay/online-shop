package io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest;

import io.github.bhuwanupadhyay.onlineshop.product.application.queryservices.ProductQueryService;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product.ProductId;

@RestController
@RequiredArgsConstructor
public class WebProductApi implements ProductsApi {

    private final Products products;
    private final ProductTransformer transformer;
    private final ProductQueryService queryService;

    @Override
    public ResponseEntity<ProductResource> createProduct(ProductCreate productCreate) {
        var entity = new Product(ProductId.create(), productCreate.getName(), productCreate.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(transformer.toResource(products.save(entity)));
    }

    @Override
    public ResponseEntity<Void> deleteProduct(String id) {
        products.deleteById(ProductId.of(UUID.fromString(id)));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ProductResource> findProduct(String id) {
        var entity = queryService.findById(ProductId.of(UUID.fromString(id))).orElseThrow(EntityNotFound::new);
        return ResponseEntity.ok(transformer.toResource(entity));
    }

    @Override
    public ResponseEntity<List<ProductResource>> findProducts(String filters, Integer offset, Integer limit) {
        var page = queryService.findAll(PageRequest.of(offset, limit));
        return ResponseEntity.status(HttpStatus.OK)
                .header("X-Total-Count", String.valueOf(page.getTotalElements()))
                .header("X-Result-Count", String.valueOf(page.getNumberOfElements()))
                .body(page.stream().map(transformer::toResource).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<ProductResource> patchProduct(String id, ProductUpdate productUpdate) {
        var entity = products.findById(ProductId.of(UUID.fromString(id))).orElseThrow(EntityNotFound::new);
        entity.update(productUpdate.getName().get(), productUpdate.getDescription().get());
        return ResponseEntity.ok(transformer.toResource(products.save(entity)));
    }

}
