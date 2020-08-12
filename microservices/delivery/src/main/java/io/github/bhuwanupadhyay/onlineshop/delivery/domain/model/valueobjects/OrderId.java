package io.github.bhuwanupadhyay.onlineshop.delivery.domain.model.valueobjects;

import io.github.bhuwanupadhyay.ddd.ValueObject;

public record OrderId(String userId) implements ValueObject {
}
