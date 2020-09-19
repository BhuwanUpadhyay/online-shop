package io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest;

import io.github.bhuwanupadhyay.onlineshop.product.application.queryservices.ProductQueryService;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Products;
import io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest.validators.ProductCreateValidator;
import io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest.validators.ProductUpdateValidator;
import io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest.validators.SyntaxValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.zalando.problem.Problem;
import org.zalando.problem.StatusType;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.violations.ConstraintViolationProblem;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product.ProductId;
import static org.zalando.problem.Status.BAD_REQUEST;

@RestController
public class WebProductApi implements ProductsApi {

    private final Products products;
    private final ProductTransformer transformer;
    private final ProductQueryService queryService;
    private final Map<Class<?>, Supplier<SyntaxValidator<?>>> validators = new HashMap<>();

    public WebProductApi(Products products, ProductTransformer transformer, ProductQueryService queryService) {
        this.products = products;
        this.transformer = transformer;
        this.queryService = queryService;
        this.validators.put(ProductCreate.class, ProductCreateValidator::new);
        this.validators.put(ProductUpdate.class, ProductUpdateValidator::new);
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target != null && validators.containsKey(target.getClass())) {
            dataBinder.addValidators(validators.get(target.getClass()).get());
        }
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
        var productId = pathProductId(id);
        products.deleteById(productId);
        return Mono.just(ResponseEntity.noContent().build());
    }

    @Override
    public Mono<ResponseEntity<ProductResource>> findProduct(String id, ServerWebExchange exchange) {
        final var productId = pathProductId(id);
        var entity = queryService.findById(productId).orElseThrow(EntityNotFound::new);
        return Mono.just(ResponseEntity.ok(transformer.toResource(entity)));
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
                    var entity = products.findById(productId)
                            .orElseThrow(EntityNotFound::new);
                    entity.update(e.getName().get(), e.getDescription().get());
                    return products.save(entity);
                }).map(e -> ResponseEntity.status(HttpStatus.OK).body(transformer.toResource(e)));
    }

    private ProductId pathProductId(String id) {
        Optional<UUID> uuid;
        try {
            uuid = Optional.of(UUID.fromString(id));
        } catch (IllegalArgumentException e) {
            uuid = Optional.empty();
        }
        return uuid.map(ProductId::of)
                .orElseThrow(() -> Problem.builder()
                        .withStatus(BAD_REQUEST)
                        .withTitle("invalid product id")
                        .withDetail("product id should be in UUID format.")
                        .build());
    }

}
