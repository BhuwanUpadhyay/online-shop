package io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest;

import io.github.bhuwanupadhyay.onlineshop.product.application.queryservices.ProductQueryService;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Products;
import io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest.validators.ProductCreateSyntaxValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.stream.Collectors;

import static io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product.ProductId;

@RestController
@RequiredArgsConstructor
public class WebProductApi implements ProductsApi {

    private final Products products;
    private final ProductTransformer transformer;
    private final ProductQueryService queryService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(new ProductCreateSyntaxValidator());
    }

    @Override
    public Mono<ResponseEntity<ProductResource>> createProduct(Mono<ProductCreate> productCreate, ServerWebExchange exchange) {
        return productCreate
                .map(e -> {
                    var entity = new Product(ProductId.create(), e.getName(), e.getDescription());
                    return products.save(entity);
                }).map(e -> ResponseEntity.status(HttpStatus.CREATED).body(transformer.toResource(e)));
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProduct(String id, ServerWebExchange exchange) {
        products.deleteById(ProductId.of(UUID.fromString(id)));
        return Mono.just(ResponseEntity.noContent().build());
    }

    @Override
    public Mono<ResponseEntity<ProductResource>> findProduct(String id, ServerWebExchange exchange) {
        var entity = queryService.findById(ProductId.of(UUID.fromString(id))).orElseThrow(EntityNotFound::new);
        return Mono.just(ResponseEntity.ok(transformer.toResource(entity)));
    }


    @Override
    public Mono<ResponseEntity<Flux<ProductResource>>> findProducts(String filters, Integer offset, Integer limit, ServerWebExchange exchange) {
        var page = queryService.findAll(PageRequest.of(offset, limit));
        return Mono.just(
                ResponseEntity.status(HttpStatus.OK)
                        .header("X-Total-Count", String.valueOf(page.getTotalElements()))
                        .header("X-Result-Count", String.valueOf(page.getNumberOfElements()))
                        .body(Flux.fromIterable(page.stream().map(transformer::toResource).collect(Collectors.toList())))
        );
    }

    @Override
    public Mono<ResponseEntity<ProductResource>> patchProduct(String id, Mono<ProductUpdate> productUpdate, ServerWebExchange exchange) {
        return productUpdate
                .map(e -> {
                    var entity = products.findById(ProductId.of(UUID.fromString(id))).orElseThrow(EntityNotFound::new);
                    entity.update(e.getName().get(), e.getDescription().get());
                    return products.save(entity);
                }).map(e -> ResponseEntity.status(HttpStatus.OK).body(transformer.toResource(e)));
    }
}
