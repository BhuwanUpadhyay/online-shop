package io.github.bhuwanupadhyay.onlineshop.cart.application.commandservices;

import io.github.bhuwanupadhyay.command.CommandService;
import io.github.bhuwanupadhyay.core.Result;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.aggregates.Cart;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.repositories.CartRepository;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.valueobjects.UserId;
import org.springframework.stereotype.Service;

@Service
public class UpdateCartCommandService implements CommandService<Cart, UserId> {

    private final CartRepository repository;

    public UpdateCartCommandService(CartRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<UserId> execute(Cart command) {
        repository.save(command);
        return new Result<>(command.getId());
    }
}
