package io.github.bhuwanupadhyay.onlineshop.product.domain.services;

import io.github.bhuwanupadhyay.core.Result;
import io.github.bhuwanupadhyay.onlineshop.product.domain.commands.ProductCreateCommand;
import io.github.bhuwanupadhyay.onlineshop.product.domain.commands.ProductUpdateCommand;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Categories;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Products;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.ProductId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateProductCommandServiceTest {

    private UpdateProductCommandService commandService;

    @Mock
    private Products products;
    @Mock
    private Categories categories;

    @BeforeEach
    void setUp() {
        this.commandService = new UpdateProductCommandService(products, categories);
    }

    @Test
    void onExecute() {
        ProductId productId = new ProductId(UUID.randomUUID());
        ProductUpdateCommand command = new ProductUpdateCommand("name", "desc", Set.of());

        Product savedProduct = new Product(productId, new ProductCreateCommand(command.name(), command.description()));
        when(products.findOne(eq(productId))).thenReturn(savedProduct);
        savedProduct.on(command, categories);
        when(products.save(eq(savedProduct))).thenReturn(savedProduct);

        Result<ProductId> result = commandService.execute(productId, command);

        verify(products).findOne(eq(productId));
        verify(products).save(eq(savedProduct));

        assertTrue(result.isOk());

    }
}
