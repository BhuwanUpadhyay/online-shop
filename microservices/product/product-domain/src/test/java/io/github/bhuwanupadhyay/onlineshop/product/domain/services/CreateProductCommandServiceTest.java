package io.github.bhuwanupadhyay.onlineshop.product.domain.services;

import io.github.bhuwanupadhyay.core.Result;
import io.github.bhuwanupadhyay.onlineshop.product.domain.commands.ProductCreateCommand;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Products;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.ProductId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateProductCommandServiceTest {

    private CreateProductCommandService commandService;

    @Mock
    private Products products;

    @BeforeEach
    void setUp() {
        commandService = new CreateProductCommandService(products);
    }

    @Test
    void onExecute() {

        ProductCreateCommand command = new ProductCreateCommand("name", "desc");
        ProductId productId = new ProductId(UUID.randomUUID());

        Product product = new Product(productId, command);

        when(products.save(eq(product))).thenReturn(product);

        Result<ProductId> result = commandService.execute(productId, command);

        verify(products).save(eq(product));

        assertTrue(result.isOk());

    }
}
