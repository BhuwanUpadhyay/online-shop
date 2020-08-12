package io.github.bhuwanupadhyay.onlineshop.delivery.application.commandservices;

import io.github.bhuwanupadhyay.command.CommandService;
import io.github.bhuwanupadhyay.core.Result;
import io.github.bhuwanupadhyay.onlineshop.delivery.domain.model.aggregates.Delivery;
import io.github.bhuwanupadhyay.onlineshop.delivery.domain.model.repositories.DeliveryRepository;
import io.github.bhuwanupadhyay.onlineshop.delivery.domain.model.valueobjects.OrderId;
import org.springframework.stereotype.Service;

@Service
public class DeliveryCommandService implements CommandService<Delivery, OrderId> {

    private final DeliveryRepository repository;

    public DeliveryCommandService(DeliveryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<OrderId> execute(Delivery command) {
        repository.save(command);
        return new Result<>(command.getId());
    }
}
