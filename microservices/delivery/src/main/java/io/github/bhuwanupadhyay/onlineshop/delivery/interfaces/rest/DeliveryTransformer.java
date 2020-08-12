package io.github.bhuwanupadhyay.onlineshop.delivery.interfaces.rest;

import io.github.bhuwanupadhyay.core.Transformer;
import io.github.bhuwanupadhyay.delivery.interfaces.rest.dto.DeliveryResource;
import io.github.bhuwanupadhyay.onlineshop.delivery.domain.model.aggregates.Delivery;
import org.springframework.stereotype.Component;

@Component
public class DeliveryTransformer implements Transformer<Delivery, DeliveryResource> {

    @Override
    public Delivery toDomain(DeliveryResource resource) {
        return new Delivery();
    }

    @Override
    public DeliveryResource toResource(Delivery domain) {
        return new DeliveryResource();
    }

}
