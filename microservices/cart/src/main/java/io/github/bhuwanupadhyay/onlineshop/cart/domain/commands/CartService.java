package io.github.bhuwanupadhyay.onlineshop.cart;

public interface CartService {

    ShoppingCart updateCart(ShoppingCart cart);

    ShoppingCart findByUserId(String userId);
}
