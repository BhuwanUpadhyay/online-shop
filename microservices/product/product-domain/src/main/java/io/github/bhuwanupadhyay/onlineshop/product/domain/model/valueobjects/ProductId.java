package io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects;

import io.github.bhuwanupadhyay.ddd.ValueObject;

import java.util.UUID;

public record ProductId(UUID id) implements ValueObject {
}
