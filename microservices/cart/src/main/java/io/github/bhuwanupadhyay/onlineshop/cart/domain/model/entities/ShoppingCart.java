package io.github.bhuwanupadhyay.onlineshop.cart.domain.model.entities;

import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.valueobjects.LineItem;

import java.time.LocalDateTime;
import java.util.List;

public class ShoppingCart {

    private final String userId;
    private final LocalDateTime activeSince;
    private final String coupon;
    private final List<LineItem> lineItems;

    public ShoppingCart(String userId,
                        LocalDateTime activeSince,
                        String coupon,
                        List<LineItem> lineItems) {
        this.userId = userId;
        this.activeSince = activeSince;
        this.coupon = coupon;
        this.lineItems = lineItems;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getActiveSince() {
        return activeSince;
    }

    public String getCoupon() {
        return coupon;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }
}
