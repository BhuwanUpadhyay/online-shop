package io.github.bhuwanupadhyay.ddd;


import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Entity<ID extends ValueObject> {

    /**
     * Globally unique identifier of the Aggregate or Entity
     */
    private final ID id;

    private LocalDateTime createdAt;

    public Entity(ID id) {
        this.id = id;
    }

    public ID getId() {
        return this.id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
