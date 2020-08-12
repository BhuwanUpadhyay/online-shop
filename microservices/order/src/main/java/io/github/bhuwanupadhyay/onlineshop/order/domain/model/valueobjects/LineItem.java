package io.github.bhuwanupadhyay.onlineshop.order.domain.model.valueobjects;

public record LineItem(
        String id,
        String name,
        Double price,
        Integer quantity,
        String inventoryId
) {

}
