package io.github.bhuwanupadhyay.onlineshop.product.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Application.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
}
