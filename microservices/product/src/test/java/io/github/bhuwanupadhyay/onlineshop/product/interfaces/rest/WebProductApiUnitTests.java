package io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest;

import io.github.bhuwanupadhyay.onlineshop.product.application.queryservices.ProductQueryService;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product.ProductId;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.Products;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.zalando.problem.Problem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(WebProductApi.class)
class WebProductApiUnitTests {
    public static final MediaType PROBLEM_JSON = new MediaType("application", "problem+json");
    @Autowired
    private WebTestClient client;
    @MockBean
    private ProductQueryService queryService;
    @MockBean
    private Products products;
    @MockBean
    private ProductTransformer transformer;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenCreateFirstValidateIfInvalidShouldReturn400AndProblems() {
        ProductCreate createCommand = new ProductCreate();

        when(products.save(any(Product.class))).thenReturn(mock(Product.class));

        client.post()
                .uri("/products")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(createCommand)
                .exchange()
                .expectHeader().contentType(PROBLEM_JSON)
                .expectStatus().isBadRequest()
                .expectBody();
    }

    @Test
    void whenCreateShouldReturn201() {
        ProductCreate createCommand = new ProductCreate()
                .name("Ear Phone 20-Max")
                .description("Noise cancellation for easier communication.");

        when(products.save(any(Product.class))).thenReturn(mock(Product.class));

        client.post()
                .uri("/products")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(createCommand)
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().isCreated()
                .expectBody(ProductResource.class);;
    }

    @Test
    void whenGetByIdShouldReturn200() {
        ProductId productId = ProductId.of(UUID.randomUUID());
        when(queryService.findById(eq(productId))).thenReturn(Optional.of(mock(Product.class)));

        client.get()
                .uri("/products/{id}", productId.getId().toString())
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().isOk()
                .expectBody(ProductResource.class);
    }

    @Test
    void whenGetAllShouldReturn200() {
        int offset = 1;
        int limit = 20;
        List<Product> products = List.of(mock(Product.class), mock(Product.class));
        when(queryService.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(products));

        client.get()
                .uri(b -> b.path("/products").queryParam("offset", offset).queryParam("limit", limit).build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductResource.class);
    }
}