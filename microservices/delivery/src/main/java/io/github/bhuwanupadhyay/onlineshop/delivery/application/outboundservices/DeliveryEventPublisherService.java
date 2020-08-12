package io.github.bhuwanupadhyay.onlineshop.delivery.application.outboundservices;

import io.github.bhuwanupadhyay.ddd.DomainEvent;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import io.github.bhuwanupadhyay.onlineshop.cart.infrastructure.brokers.stream.CartEventSource;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DeliveryEventPublisherService implements DomainEventPublisher {

    private final CartEventSource eventSource;

    public DeliveryEventPublisherService(CartEventSource eventSource) {
        this.eventSource = eventSource;
    }

    @Override
    public void publish(DomainEvent domainEvent) {
        Map<String, Object> headers = new HashMap<>();
        eventSource.eventsOut().send(MessageBuilder.createMessage(domainEvent, new MessageHeaders(headers)));
    }
}
