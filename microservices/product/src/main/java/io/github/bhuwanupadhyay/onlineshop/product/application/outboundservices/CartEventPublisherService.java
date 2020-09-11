package io.github.bhuwanupadhyay.onlineshop.product.application.outboundservices;

import io.github.bhuwanupadhyay.onlineshop.product.infrastructure.brokers.stream.CartEventSource;
import org.jddd.event.types.DomainEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@EnableBinding(CartEventSource.class)
public class CartEventPublisherService {

    private final CartEventSource eventSource;

    public CartEventPublisherService(CartEventSource eventSource) {
        this.eventSource = eventSource;
    }

    @EventListener
    public void publish(DomainEvent domainEvent) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("x-source", "cart-service");
        eventSource.eventsOut().send(MessageBuilder.createMessage(domainEvent, new MessageHeaders(headers)));
    }
}
