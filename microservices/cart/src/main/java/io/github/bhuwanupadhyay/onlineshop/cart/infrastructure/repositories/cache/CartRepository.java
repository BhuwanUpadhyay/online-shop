package io.github.bhuwanupadhyay.onlineshop.cart.infrastructure.repositories.cache;

import io.github.bhuwanupadhyay.ddd.DomainRepository;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.aggregates.Cart;
import io.github.bhuwanupadhyay.onlineshop.cart.domain.model.valueobjects.UserId;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class CartRepository extends DomainRepository<Cart, UserId> {

    private final List<Cart> carts = new ArrayList<>();

    protected CartRepository() {
        super(domainEvent -> {
        });
    }

    @Override
    public Optional<Cart> findOne(UserId userId) {
        Optional<Cart> findOne = carts.stream().filter(cart -> Objects.equals(cart.getUserId(), userId.userId()))
                .findFirst();

        Cart cart = findOne.orElseGet(() -> new Cart(userId, LocalDateTime.now(), null, new ArrayList<>()));

        if (findOne.isPresent()) {
            persist(cart);
        }

        return Optional.of(cart);
    }

    @Override
    protected void persist(Cart entity) {
        carts.add(entity);
    }

    @Override
    public UserId nextId() {
        throw new UnsupportedOperationException("No nextId available for cart!");
    }
}
