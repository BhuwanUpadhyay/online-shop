package io.github.bhuwanupadhyay.onlineshop.cart.domain.commands;

import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.valueobjects.ShoppingCart;

public interface CartService {

    ShoppingCart updateCart(ShoppingCart cart);

    ShoppingCart findByUserId(String userId);
}
