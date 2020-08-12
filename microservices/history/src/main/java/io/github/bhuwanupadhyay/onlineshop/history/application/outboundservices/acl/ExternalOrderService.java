package io.github.bhuwanupadhyay.onlineshop.cart.application.outboundservices.acl;

import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.valueobjects.OrderInfo;
import io.github.bhuwanupadhyay.onlineshop.cart.infrastructure.services.http.OrderServiceClient;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static io.github.bhuwanupadhyay.onlineshop.cart.infrastructure.services.http.OrderServiceClient.OrderInfoResource;

@Service
public class ExternalOrderService {

    private final OrderServiceClient orderServiceClient;

    public ExternalOrderService(OrderServiceClient orderServiceClient) {
        this.orderServiceClient = orderServiceClient;
    }

    public Optional<OrderInfo> getOrderInfo(String orderId) {
        OrderInfoResource info = orderServiceClient.getOrderInfo(orderId);
        return Optional.ofNullable(info).map(r -> new OrderInfo(r.customerId()));
    }

}
