package io.github.bhuwanupadhyay.onlineshop.delivery.interfaces.rest;

import io.github.bhuwanupadhyay.core.Result;
import io.github.bhuwanupadhyay.delivery.interfaces.rest.DeliveryCommandApi;
import io.github.bhuwanupadhyay.delivery.interfaces.rest.dto.DeliveryResource;
import io.github.bhuwanupadhyay.onlineshop.cart.application.commandservices.UpdateCartCommandService;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.repositories.CartRepository;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.valueobjects.UserId;
import io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.CartCommandApi;
import io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.CartQueryApi;
import io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.dto.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class DeliveryController implements DeliveryCommandApi {

    private final UpdateCartCommandService service;
    private final DeliveryTransformer transformer;
    private final CartRepository repository;

    public DeliveryController(UpdateCartCommandService service, DeliveryTransformer transformer, CartRepository repository) {
        this.service = service;
        this.transformer = transformer;
        this.repository = repository;
    }

    @Override
    public Mono<ResponseEntity<Cart>> createCart(Mono<Cart> cart, ServerWebExchange exchange) {
        return cart.map(req -> {
            Result<UserId> result = service.execute(transformer.toDomain(req));
            return ResponseEntity.ok(transformer.toResource(repository.find(result.ok().get())));
        });
    }

    @Override
    public Mono<ResponseEntity<Cart>> getUserCart(String userId, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(transformer.toResource(repository.find(new UserId(userId)))));
    }

    @Override
    public Mono<ResponseEntity<Void>> deliverOrder(Mono<DeliveryResource> deliveryResource, ServerWebExchange exchange) {
        return null;
    }
}
