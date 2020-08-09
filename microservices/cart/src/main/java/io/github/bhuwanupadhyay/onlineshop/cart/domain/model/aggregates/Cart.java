package io.github.bhuwanupadhyay.onlineshop.cart.domain.model.aggregates;

import io.github.bhuwanupadhyay.ddd.AggregateRoot;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.valueobjects.LineItem;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.valueobjects.UserId;

import java.time.LocalDateTime;
import java.util.List;

public class Cart extends AggregateRoot<UserId> {

    private final LocalDateTime activeSince;
    private final String coupon;
    private final List<LineItem> lineItems;

    public Cart(UserId userId,
                LocalDateTime activeSince,
                String coupon,
                List<LineItem> lineItems) {
        super(userId);
        this.activeSince = activeSince;
        this.coupon = coupon;
        this.lineItems = lineItems;
    }

    public String getUserId() {
        return getId().userId();
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
