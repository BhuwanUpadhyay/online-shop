package io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects;

import org.jddd.core.types.Identifier;

import java.util.UUID;

public record ProductId(UUID id) implements Identifier {
}
