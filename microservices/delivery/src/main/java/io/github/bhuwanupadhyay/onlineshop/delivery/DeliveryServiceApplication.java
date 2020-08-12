package io.github.bhuwanupadhyay.onlineshop.delivery;

import io.github.bhuwanupadhyay.onlineshop.delivery.infrastructure.brokers.stream.DeliveryEventSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(DeliveryEventSource.class)
public class DeliveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryServiceApplication.class, args);
    }
}
