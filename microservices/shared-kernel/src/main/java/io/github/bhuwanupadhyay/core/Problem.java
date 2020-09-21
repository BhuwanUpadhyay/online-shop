package io.github.bhuwanupadhyay.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface Problem {
    String getMessage();

    String getPropertyPath();

    default boolean isEqualTo(String propertyPath, String message) {
        return Objects.equals(propertyPath, getPropertyPath()) && Objects.equals(message, getMessage());
    }

    static void raiseBadIfNotEmpty(List<Problem> problems) {
        if (problems != null && !problems.isEmpty()) {
            throw new BadRequestException(new ArrayList<>(problems));
        }
    }
}
