package io.github.bhuwanupadhyay.onlineshop.product.interfaces.events;

import io.github.bhuwanupadhyay.onlineshop.product.infrastructure.brokers.stream.CartEventSource;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartEventHandler {

    @StreamListener(target = CartEventSource.CHANNEL)
    public void receiveEvent(@Payload String payload, @Headers Map<String, Object> headers) {

    }


}
