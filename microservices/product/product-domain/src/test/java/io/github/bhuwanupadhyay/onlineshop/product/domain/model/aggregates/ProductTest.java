package io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates;

import io.github.bhuwanupadhyay.onlineshop.product.domain.commands.ProductCreateCommand;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.ProductId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProductTest {

    @Test
    void canEqualsById() {
        ProductId productId = new ProductId(UUID.randomUUID());

        ProductCreateCommand command = new ProductCreateCommand("name", "desc");

        Product first = new Product(productId, command);

        Product second = new Product(productId, new ProductCreateCommand("name1", "desc2"));

        assertEquals(first, second);

        Product third = new Product(new ProductId(UUID.randomUUID()), command);

        assertNotEquals(first, third);
    }
}
