package io.github.bhuwanupadhyay.ddd;


import java.util.Optional;

public abstract class AggregateRepository<T extends AggregateRoot<ID>, ID extends ValueObject> {

    private final DomainEventPublisher publisher;

    protected AggregateRepository(DomainEventPublisher publisher) {
        this.publisher = publisher;
    }

    public abstract Optional<T> findOne(ID id);

    public T find(ID id) {
        return this.findOne(id)
                .orElseThrow(() -> new DomainEntityNotFound(this.getClass().getName(), id));
    }

    public ID save(T entity) {
        this.persist(entity);
        entity.getDomainEvents().forEach(publisher::publish);
        entity.clearDomainEvents();
        return entity.getId();
    }

    protected abstract void persist(T entity);

    public abstract ID nextId();
}
