//package io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest;
//
//import io.github.bhuwanupadhyay.onlineshop.product.application.queryservices.ProductQueryService;
//import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Product;
//import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.ProductId;
//import io.github.bhuwanupadhyay.onlineshop.product.domain.services.CreateProductCommandService;
//import io.github.bhuwanupadhyay.onlineshop.product.domain.services.UpdateProductCommandService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.reactive.server.WebTestClient;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@TestMethodOrder(Alphanumeric.class)
//@WebFluxTest(WebProductApi.class)
//class WebProductApiUnitTests {
//    public static final MediaType PROBLEM_JSON = new MediaType("application", "problem+json");
//    @Autowired
//    private WebTestClient client;
//    @MockBean
//    private ProductQueryService queryService;
//    @MockBean
//    private CreateProductCommandService createProductCommandService;
//    @MockBean
//    private UpdateProductCommandService updateProductCommandService;
//    @MockBean
//    private ProductTransformer transformer;
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @Test
//    void whenCreateFirstValidateIfInvalidShouldReturn400AndProblems() {
//        ProductCreate createCommand = new ProductCreate();
//
//        when(updateProductCommandService.save(any(Product.class))).thenReturn(mock(Product.class));
//
//        client.post()
//                .uri("/products")
//                .accept(MediaType.APPLICATION_JSON)
//                .bodyValue(createCommand)
//                .exchange()
//                .expectHeader().contentType(PROBLEM_JSON)
//                .expectStatus().isBadRequest()
//                .expectBody();
//    }
//
//    @Test
//    void whenCreateShouldReturn201() {
//        ProductCreate createCommand = new ProductCreate()
//                .name("Ear Phone 20-Max")
//                .description("Noise cancellation for easier communication.");
//
//        when(updateProductCommandService.save(any(Product.class))).thenReturn(mock(Product.class));
//
//        client.post()
//                .uri("/products")
//                .accept(MediaType.APPLICATION_JSON)
//                .bodyValue(createCommand)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectBody(ProductResource.class);;
//    }
//
//    @Test
//    void whenGetByIdShouldReturn200() {
//        ProductId productId = new ProductId(UUID.randomUUID());
//        when(queryService.findById(eq(productId))).thenReturn(Optional.of(mock(Product.class)));
//
//        client.get()
//                .uri("/products/{id}", productId.id().toString())
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(ProductResource.class);
//    }
//
//    @Test
//    void whenGetAllShouldReturn200() {
//        int offset = 1;
//        int limit = 20;
//        Product e1 = new Product(new ProductId(UUID.randomUUID()), "Ear Phone", "Audible ear phone");
//        Product e2 = new Product(new ProductId(UUID.randomUUID()), "Laptop", "Office work laptop");
//        List<Product> products = List.of(e1, e2);
//        when(queryService.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(products));
//        when(transformer.toResource(eq(e1))).thenReturn(new ProductTransformer().toResource(e1));
//        when(transformer.toResource(eq(e2))).thenReturn(new ProductTransformer().toResource(e2));
//
//        client.get()
//                .uri(b -> b.path("/products").queryParam("offset", offset).queryParam("limit", limit).build())
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(ProductResource[].class);
//    }
//}
