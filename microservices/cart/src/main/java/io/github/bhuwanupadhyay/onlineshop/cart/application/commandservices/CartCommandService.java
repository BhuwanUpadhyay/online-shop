package io.github.bhuwanupadhyay.onlineshop.cart.application.commandservices;

import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.aggregates.Cart;

public interface CartCommandService {

    Cart updateCart(Cart cart);

    Cart findOrUpdateByUserId(String userId);
}
