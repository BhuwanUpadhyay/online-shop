package io.github.bhuwanupadhyay.onlineshop.delivery.infrastructure.brokers.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CartEventSource {

    String IN_CHANNEL = "osEvents";
    String OUT_CHANNEL = "osEvents";

    @Output(OUT_CHANNEL)
    MessageChannel eventsOut();

    @Input(IN_CHANNEL)
    SubscribableChannel eventsIn();
}
