package io.github.bhuwanupadhyay.onlineshop.cart.domain.commands;

import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.valueobjects.ShoppingCart;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CachedCartService implements CartService {

    private final List<ShoppingCart> carts = new ArrayList<>();

    @Override
    public ShoppingCart updateCart(ShoppingCart cart) {
        carts.add(cart);
        return cart;
    }

    @Override
    public ShoppingCart findByUserId(String userId) {
        return carts.stream().filter(cart -> Objects.equals(cart.userId(), userId))
                .findFirst()
                .orElseGet(() -> updateCart(new ShoppingCart(userId, LocalDateTime.now(), null, new ArrayList<>())));
    }
}
