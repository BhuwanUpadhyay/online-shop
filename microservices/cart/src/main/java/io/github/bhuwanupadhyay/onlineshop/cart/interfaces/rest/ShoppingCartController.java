package io.github.bhuwanupadhyay.onlineshop.cart.interfaces.rest;

import io.github.bhuwanupadhyay.core.Result;
import io.github.bhuwanupadhyay.onlineshop.cart.application.commandservices.UpdateCartCommandService;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.repositories.CartRepository;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.valueobjects.UserId;
import io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.CartCommandApi;
import io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.CartQueryApi;
import io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.dto.CartResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class ShoppingCartController implements CartCommandApi, CartQueryApi {

    private final UpdateCartCommandService service;
    private final CartTransformer transformer;
    private final CartRepository repository;

    public ShoppingCartController(UpdateCartCommandService service, CartTransformer transformer, CartRepository repository) {
        this.service = service;
        this.transformer = transformer;
        this.repository = repository;
    }

    @Override
    public Mono<ResponseEntity<CartResource>> createCart(Mono<CartResource> cart, ServerWebExchange exchange) {
        return cart.map(req -> {
            Result<UserId> result = service.execute(transformer.toDomain(req));
            return ResponseEntity.ok(transformer.toResource(repository.find(result.ok().get())));
        });
    }

    @Override
    public Mono<ResponseEntity<CartResource>> getUserCart(String userId, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(transformer.toResource(repository.find(new UserId(userId)))));
    }
}
