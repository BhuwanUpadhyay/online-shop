package io.github.bhuwanupadhyay.rules;

import java.util.Objects;

public interface Problem {
    String getMessage();

    String getMessageTemplate();

    String getPropertyPath();

    default boolean isEqualTo(String propertyPath, String message) {
        return Objects.equals(propertyPath, getPropertyPath()) && Objects.equals(message, getMessage());
    }
}
