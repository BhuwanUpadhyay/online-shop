package io.github.bhuwanupadhyay.onlineshop.product.infrastructure.config;

import io.github.bhuwanupadhyay.onlineshop.product.infrastructure.aop.LoggingAspect;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

    @Bean
    public LoggingAspect loggingAspect(Environment env) {
        return new LoggingAspect(env);
    }
}
