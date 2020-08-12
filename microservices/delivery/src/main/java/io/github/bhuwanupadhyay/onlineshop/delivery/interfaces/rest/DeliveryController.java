package io.github.bhuwanupadhyay.onlineshop.delivery.interfaces.rest;

import io.github.bhuwanupadhyay.core.Result;
import io.github.bhuwanupadhyay.delivery.interfaces.rest.DeliveryCommandApi;
import io.github.bhuwanupadhyay.delivery.interfaces.rest.dto.DeliveryResource;
import io.github.bhuwanupadhyay.onlineshop.delivery.application.commandservices.DeliveryCommandService;
import io.github.bhuwanupadhyay.onlineshop.delivery.domain.model.repositories.DeliveryRepository;
import io.github.bhuwanupadhyay.onlineshop.delivery.domain.model.valueobjects.OrderId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class DeliveryController implements DeliveryCommandApi {

    private final DeliveryCommandService service;
    private final DeliveryTransformer transformer;
    private final DeliveryRepository repository;

    public DeliveryController(DeliveryCommandService service, DeliveryTransformer transformer, DeliveryRepository repository) {
        this.service = service;
        this.transformer = transformer;
        this.repository = repository;
    }

    @Override
    public Mono<ResponseEntity<Void>> deliverOrder(Mono<DeliveryResource> deliveryResource, ServerWebExchange exchange) {
        return deliveryResource.map(req -> {
            Result<OrderId> result = service.execute(transformer.toDomain(req));
            return ResponseEntity.noContent().build();
        });
    }
}
