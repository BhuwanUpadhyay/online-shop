package io.github.bhuwanupadhyay.onlineshop.delivery.interfaces.events;

import io.github.bhuwanupadhyay.onlineshop.delivery.infrastructure.brokers.stream.DeliveryEventSource;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DeliveryEventHandler {

    @StreamListener(target = DeliveryEventSource.CHANNEL)
    public void receiveEvent(@Payload String payload, @Headers Map<String, Object> headers) {

    }


}
