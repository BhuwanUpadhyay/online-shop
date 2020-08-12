package io.github.bhuwanupadhyay.onlineshop.order.application.outboundservices.acl;

import io.github.bhuwanupadhyay.onlineshop.order.domain.model.valueobjects.OrderInfo;
import io.github.bhuwanupadhyay.onlineshop.order.infrastructure.services.http.OrderServiceClient;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static io.github.bhuwanupadhyay.onlineshop.order.infrastructure.services.http.OrderServiceClient.OrderInfoResource;

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
