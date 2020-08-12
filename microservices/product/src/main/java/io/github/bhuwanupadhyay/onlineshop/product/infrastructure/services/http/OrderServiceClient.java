package io.github.bhuwanupadhyay.onlineshop.product.infrastructure.services.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("order-service")
public interface OrderServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/orders/{orderId}")
    OrderInfoResource getOrderInfo(@PathVariable("orderId") String orderId);

    @RequestMapping(method = RequestMethod.POST, value = "/stores/{orderId}", consumes = APPLICATION_JSON_VALUE)
    OrderInfoResource updateOrder(@PathVariable("orderId") String storeId, OrderInfoResource orderInfo);

    record OrderInfoResource(String customerId) {

    }
}
