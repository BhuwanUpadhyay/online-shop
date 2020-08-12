package io.github.bhuwanupadhyay.onlineshop.product.infrastructure.repositories.cache;

import io.github.bhuwanupadhyay.onlineshop.product.domain.model.aggregates.Cart;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.repositories.CartRepository;
import io.github.bhuwanupadhyay.onlineshop.product.domain.model.valueobjects.UserId;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CachedCartRepository extends CartRepository {

    private final List<Cart> carts = new ArrayList<>();

    public CachedCartRepository() {
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
