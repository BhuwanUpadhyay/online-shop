package io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories;

import org.jddd.core.types.AggregateRoot;
import org.jddd.core.types.Identifier;

import java.util.Optional;

public interface Repository<T extends AggregateRoot<T, ID>, ID extends Identifier> {

    Optional<T> findById(ID id);

    default T findOne(ID id) {
        return findById(id).orElseThrow(RuntimeException::new);
    }

    T save(T entity);
}
