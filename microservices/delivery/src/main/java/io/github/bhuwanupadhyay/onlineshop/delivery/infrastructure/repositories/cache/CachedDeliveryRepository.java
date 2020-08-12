package io.github.bhuwanupadhyay.onlineshop.delivery.infrastructure.repositories.cache;

import io.github.bhuwanupadhyay.onlineshop.delivery.domain.model.aggregates.Delivery;
import io.github.bhuwanupadhyay.onlineshop.delivery.domain.model.repositories.DeliveryRepository;
import io.github.bhuwanupadhyay.onlineshop.delivery.domain.model.valueobjects.OrderId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CachedDeliveryRepository extends DeliveryRepository {

    private final List<Delivery> carts = new ArrayList<>();

    public CachedDeliveryRepository() {
        super(domainEvent -> {
        });
    }

    @Override
    public Optional<Delivery> findOne(OrderId id) {
        return carts.stream().filter(cart -> Objects.equals(cart.getId().userId(), id.userId()))
                .findFirst();
    }

    @Override
    protected void persist(Delivery entity) {
        carts.add(entity);
    }

    @Override
    public OrderId nextId() {
        throw new UnsupportedOperationException("No nextId available for cart!");
    }
}
