package io.github.bhuwanupadhyay.onlineshop.cart.application.commandservices;

import io.github.bhuwanupadhyay.command.CommandService;
import io.github.bhuwanupadhyay.core.Result;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.entities.ShoppingCart;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CachedCartCommandService implements CommandService<ShoppingCart, ShoppingCart> {

    private final List<ShoppingCart> carts = new ArrayList<>();

    @Override
    public ShoppingCart updateCart(ShoppingCart cart) {
        carts.add(cart);
        return cart;
    }

    @Override
    public ShoppingCart findOrUpdateByUserId(String userId) {
        return carts.stream().filter(cart -> Objects.equals(cart.getUserId(), userId))
                .findFirst()
                .orElseGet(() -> updateCart(new ShoppingCart(userId, LocalDateTime.now(), null, new ArrayList<>())));
    }

    @Override
    public Result<ShoppingCart> execute(ShoppingCart command) {
        return null;
    }
}
