package io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects;

import io.github.bhuwanupadhyay.ddd.ValueObject;

public record OrderInfo(String customerId) implements ValueObject {
}
