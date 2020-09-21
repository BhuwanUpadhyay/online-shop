package io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects;

import org.jddd.core.types.Identifier;

import java.util.UUID;

public record CategoryId(UUID id) implements Identifier {
}
