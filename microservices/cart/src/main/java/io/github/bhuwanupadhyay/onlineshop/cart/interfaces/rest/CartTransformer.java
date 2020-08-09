package io.github.bhuwanupadhyay.onlineshop.cart.interfaces.rest;

import io.github.bhuwanupadhyay.core.Transformer;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.entities.ShoppingCart;
import io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.dto.Cart;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;

@Component
public class CartTransformer implements Transformer<ShoppingCart, Cart> {

    @Override
    public ShoppingCart toDomain(Cart resource) {
        return new ShoppingCart(
                resource.getUserId(),
                Instant.ofEpochMilli(resource.getActiveSince()).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                resource.getCoupen(),
                new ArrayList<>()
        );
    }

    @Override
    public Cart toResource(ShoppingCart domain) {
        return new Cart()
                .activeSince(domain.getActiveSince().atZone(ZoneId.systemDefault()).toEpochSecond())
                .userId(domain.getUserId())
                .lineItems(new ArrayList<>())
                .coupen(domain.getCoupon());
    }

}
