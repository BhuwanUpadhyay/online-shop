package io.github.bhuwanupadhyay.onlineshop.delivery.interfaces.rest;

import io.github.bhuwanupadhyay.core.Transformer;
import io.github.bhuwanupadhyay.onlineshop.delivery.domain.model.aggregates.Cart;
import io.github.bhuwanupadhyay.onlineshop.delivery.domain.model.valueobjects.LineItem;
import io.github.bhuwanupadhyay.onlineshop.delivery.domain.model.valueobjects.UserId;
import io.github.bhuwanupadhyay.onlineshop.delivery.interfaces.rest.dto.CartResource;
import io.github.bhuwanupadhyay.onlineshop.delivery.interfaces.rest.dto.LineItemResource;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.util.stream.Collectors;

@Component
public class CartTransformer implements Transformer<Cart, CartResource> {

    @Override
    public Cart toDomain(CartResource resource) {
        return new Cart(
                new UserId(resource.getUserId()),
                Instant.ofEpochMilli(resource.getActiveSince()).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                resource.getCoupon(),
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
    public CartResource toResource(Cart domain) {
        return new CartResource()
                .activeSince(domain.getActiveSince().atZone(ZoneId.systemDefault()).toEpochSecond())
                .userId(domain.getUserId())
                .lineItems(
                        domain.getLineItems()
                                .stream()
                                .map(item -> new LineItemResource().
                                        id(item.id())
                                        .name(item.name())
                                        .price(item.price())
                                        .quantity(item.quantity())
                                        .inventoryId(item.inventoryId())).collect(Collectors.toList())
                ).coupon(domain.getCoupon());
    }

}
