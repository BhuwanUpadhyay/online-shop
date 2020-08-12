package io.github.bhuwanupadhyay.onlineshop.shipping.application.outboundservices;

import io.github.bhuwanupadhyay.ddd.DomainEvent;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import io.github.bhuwanupadhyay.onlineshop.shipping.infrastructure.brokers.stream.CartEventSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
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

    @Override
    public void publish(DomainEvent domainEvent) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("x-source", "cart-service");
        headers.put("x-eventId", domainEvent.getEventId());
        headers.put("x-eventClassName", domainEvent.getEventClassName());
        eventSource.eventsOut().send(MessageBuilder.createMessage(domainEvent, new MessageHeaders(headers)));
    }
}
