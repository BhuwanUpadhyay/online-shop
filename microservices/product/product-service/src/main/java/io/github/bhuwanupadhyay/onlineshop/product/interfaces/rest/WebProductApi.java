package io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest;

import io.github.bhuwanupadhyay.core.Result;
import io.github.bhuwanupadhyay.onlineshop.product.application.queryservices.ProductQueryService;
import io.github.bhuwanupadhyay.onlineshop.product.domain.commands.ProductCreateCommand;
import io.github.bhuwanupadhyay.onlineshop.product.domain.commands.ProductUpdateCommand;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.ProductId;
import io.github.bhuwanupadhyay.onlineshop.product.domain.services.CreateProductCommandService;
import io.github.bhuwanupadhyay.onlineshop.product.domain.services.UpdateProductCommandService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class WebProductApi implements ProductsApi {

    private final CreateProductCommandService createProductCommandService;
    private final UpdateProductCommandService updateProductCommandService;
    private final ProductTransformer transformer;
    private final ProductQueryService queryService;

    public WebProductApi(CreateProductCommandService createProductCommandService,
                         UpdateProductCommandService updateProductCommandService,
                         ProductTransformer transformer, ProductQueryService queryService) {
        this.createProductCommandService = createProductCommandService;
        this.updateProductCommandService = updateProductCommandService;
        this.transformer = transformer;
        this.queryService = queryService;
    }

    @Override
    public Mono<ResponseEntity<ProductResource>> createProduct(Mono<ProductCreate> productCreate, ServerWebExchange exchange) {
        return productCreate
                .map(e -> {
                    ProductId id = new ProductId(UUID.randomUUID());
                    Result<ProductId> result = createProductCommandService
                            .execute(id, new ProductCreateCommand(e.getName(), e.getDescription()));
                    if (result.isOk()) {
                        return ResponseEntity.status(HttpStatus.CREATED).body(transformer.toResource(queryService.find(id)));
                    } else {
                        return ResponseEntity.badRequest().build();
                    }
                });
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProduct(String id, ServerWebExchange exchange) {
        final var productId = pathProductId(id);
        if (queryService.existsById(productId)) {
            queryService.deleteById(productId);
            return Mono.just(ResponseEntity.noContent().build());
        }
        return Mono.just(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<ProductResource>> findProduct(String id, ServerWebExchange exchange) {
        final var productId = pathProductId(id);
        return Mono.just(ResponseEntity.ok(transformer.toResource(queryService.find(productId))));
    }


    @Override
    public Mono<ResponseEntity<Flux<ProductResource>>> findProducts(String filters, Integer offset, Integer limit, ServerWebExchange exchange) {
        offset = Optional.ofNullable(offset).filter(v -> v > 0).map(v -> v - 1).orElse(0);
        limit = Optional.ofNullable(limit).filter(v -> v > 0).orElse(20);
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
        final var productId = pathProductId(id);
        return productUpdate
                .map(e -> {
                    Result<ProductId> result = updateProductCommandService
                            .execute(productId, new ProductUpdateCommand(e.getName(), e.getDescription(), transformer.toCategories(e.getCategories())));
                    if (result.isOk()) {
                        return ResponseEntity.ok(transformer.toResource(queryService.find(productId)));
                    } else {
                        return ResponseEntity.badRequest().build();
                    }
                });
    }

    private ProductId pathProductId(String id) {
        try {
            return new ProductId(UUID.fromString(id));
        } catch (IllegalArgumentException e) {
            throw this.invalidProductId();
        }
    }

    private RuntimeException invalidProductId() {
        return new RuntimeException();
    }

}
