package io.github.bhuwanupadhyay.ddd;


import java.util.Optional;

public abstract class EntityRepository<T extends Entity<ID>, ID extends ValueObject> {

    public abstract Optional<T> findOne(ID id);

    public T find(ID id) {
        return this.findOne(id)
                .orElseThrow(() -> new DomainEntityNotFound(this.getClass().getName(), id));
    }

    public abstract ID nextId();
}
