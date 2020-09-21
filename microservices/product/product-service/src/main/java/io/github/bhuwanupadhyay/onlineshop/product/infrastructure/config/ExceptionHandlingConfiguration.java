package io.github.bhuwanupadhyay.onlineshop.product.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.webflux.advice.ProblemHandling;

@Configuration
public class ExceptionHandlingConfiguration {

//    @Bean
//    @Order(-2)
//    // The handler must have precedence over WebFluxResponseStatusExceptionHandler and Spring Boot's ErrorWebExceptionHandler
//    public WebExceptionHandler problemExceptionHandler(ObjectMapper mapper, ExceptionHandling problemHandling) {
//        return new ProblemExceptionHandler(mapper, problemHandling);
//    }

    @ControllerAdvice
    public static class ExceptionHandling implements ProblemHandling {

    }
}
