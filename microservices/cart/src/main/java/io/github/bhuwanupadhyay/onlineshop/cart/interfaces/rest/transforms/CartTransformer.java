package io.github.bhuwanupadhyay.onlineshop.cart.interfaces.rest.transforms;

import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.valueobjects.ShoppingCart;
import io.github.bhuwanupadhyay.onlineshop.cart.interfaces.Transformer;
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
                .activeSince(domain.activeSince().atZone(ZoneId.systemDefault()).toEpochSecond())
                .userId(domain.userId())
                .lineItems(new ArrayList<>())
                .coupen(domain.coupon());
    }

}
