package io.github.bhuwanupadhyay.onlineshop.cart;

import java.time.LocalDateTime;
import java.util.List;

public record ShoppingCart(
        String userId,
        LocalDateTime activeSince,
        String coupon,
        List<LineItem> lineItems
) {
}
