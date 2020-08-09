package io.github.bhuwanupadhyay.onlineshop.cart.interfaces.rest;

import io.github.bhuwanupadhyay.core.Transformer;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.aggregates.Cart;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.valueobjects.LineItem;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.valueobjects.UserId;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.util.stream.Collectors;

@Component
public class CartTransformer implements Transformer<Cart, io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.dto.Cart> {

    @Override
    public Cart toDomain(io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.dto.Cart resource) {
        return new Cart(
                new UserId(resource.getUserId()),
                Instant.ofEpochMilli(resource.getActiveSince()).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                resource.getCoupen(),
                resource.getLineItems()
                        .stream()
                        .map(item -> new LineItem(
                                item.getId(),
                                item.getName(),
                                item.getPrice(),
                                item.getQuantity(),
                                item.getInventoryId()
                        )).collect(Collectors.toList())
        );
    }

    @Override
    public io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.dto.Cart toResource(Cart domain) {
        return new io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.dto.Cart()
                .activeSince(domain.getActiveSince().atZone(ZoneId.systemDefault()).toEpochSecond())
                .userId(domain.getUserId())
                .lineItems(
                        domain.getLineItems()
                                .stream()
                                .map(item -> new io.github.bhuwanupadhyay.shoppingcart.interfaces.rest.dto.LineItem().
                                        id(item.id())
                                        .name(item.name())
                                        .price(item.price())
                                        .quantity(item.quantity())
                                        .inventoryId(item.inventoryId())).collect(Collectors.toList())
                ).coupen(domain.getCoupon());
    }

}
