package io.github.bhuwanupadhyay.onlineshop.cart;

record LineItem(
        String id,
        String name,
        Double price,
        Integer quantity,
        Long inventoryId
) {

}
