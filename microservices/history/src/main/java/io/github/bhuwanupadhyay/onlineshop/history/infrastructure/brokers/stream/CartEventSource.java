package io.github.bhuwanupadhyay.onlineshop.cart.infrastructure.brokers.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CartEventSource {

    String CHANNEL = "osEvents";

    @Output(CHANNEL)
    MessageChannel eventsOut();

    @Input(CHANNEL)
    SubscribableChannel eventsIn();
}
