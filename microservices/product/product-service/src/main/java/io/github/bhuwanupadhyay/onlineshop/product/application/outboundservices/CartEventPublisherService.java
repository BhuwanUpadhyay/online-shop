package io.github.bhuwanupadhyay.onlineshop.product.application.outboundservices;

import io.github.bhuwanupadhyay.ddd.DomainEvent;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import io.github.bhuwanupadhyay.onlineshop.product.infrastructure.brokers.stream.CartEventSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@EnableBinding(CartEventSource.class)
public class CartEventPublisherService implements DomainEventPublisher {

    private final CartEventSource eventSource;

    public CartEventPublisherService(CartEventSource eventSource) {
        this.eventSource = eventSource;
    }

    @EventListener
    public void publish(DomainEvent domainEvent) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("X-Service", "Product");
        eventSource.eventsOut().send(MessageBuilder.createMessage(domainEvent, new MessageHeaders(headers)));
    }
}
