package io.github.bhuwanupadhyay.onlineshop.cart.interfaces.rest;

import io.github.bhuwanupadhyay.onlineshop.cart.application.commandservices.CartCommandService;
import io.github.bhuwanupadhyay.onlineshop.cart.interfaces.rest.transforms.CartTransformer;
import io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.CartCommandApi;
import io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.CartQueryApi;
import io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.dto.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class ShoppingCartController implements CartCommandApi, CartQueryApi {

    private final CartCommandService service;
    private final CartTransformer transformer;

    public ShoppingCartController(CartCommandService service, CartTransformer transformer) {
        this.service = service;
        this.transformer = transformer;
    }

    @Override
    public Mono<ResponseEntity<Cart>> createCart(Mono<Cart> cart, ServerWebExchange exchange) {
        return cart.map(req -> ResponseEntity.ok(transformer.toResource(service.updateCart(transformer.toDomain(req)))));
    }

    @Override
    public Mono<ResponseEntity<Cart>> getUserCart(String userId, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(transformer.toResource(service.findOrUpdateByUserId(userId))));
    }
}
