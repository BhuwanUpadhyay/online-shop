package io.github.bhuwanupadhyay.onlineshop.delivery.domain.model.repositories;

import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import io.github.bhuwanupadhyay.ddd.DomainRepository;
import io.github.bhuwanupadhyay.onlineshop.delivery.domain.model.aggregates.Delivery;
import io.github.bhuwanupadhyay.onlineshop.delivery.domain.model.valueobjects.OrderId;

public abstract class DeliveryRepository extends DomainRepository<Delivery, OrderId> {
    public DeliveryRepository(DomainEventPublisher publisher) {
        super(publisher);
    }
}
