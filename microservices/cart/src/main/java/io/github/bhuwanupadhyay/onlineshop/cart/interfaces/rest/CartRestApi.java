package io.github.bhuwanupadhyay.onlineshop.cart.interfaces.rest;

import io.github.bhuwanupadhyay.onlineshop.cart.domain.commands.CartService;
import io.github.bhuwanupadhyay.onlineshop.cart.interfaces.rest.transform.CartTransformer;
import io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.CartCommandApi;
import io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.CartQueryApi;
import io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.dto.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class CartRestApi implements CartCommandApi, CartQueryApi {

    private final CartService service;
    private final CartTransformer transformer;

    public CartRestApi(CartService service, CartTransformer transformer) {
        this.service = service;
        this.transformer = transformer;
    }

    @Override
    public Mono<ResponseEntity<Cart>> createCart(Mono<Cart> cart, ServerWebExchange exchange) {
        return cart.map(req -> ResponseEntity.ok(transformer.toResource(service.updateCart(transformer.toEntity(req)))));
    }

    @Override
    public Mono<ResponseEntity<Cart>> getUserCart(String userId, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(transformer.toResource(service.findByUserId(userId))));
    }
}
