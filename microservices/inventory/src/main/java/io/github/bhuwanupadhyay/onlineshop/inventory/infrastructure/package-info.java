/**
 * This package contains the infrastructural components required by the Bounded
 * Context’s domain model to communicate to any external repositories (e.g., Relational
 * Database(s), NoSQL Databases, Message Queues, Event Infrastructure).
 */
package io.github.bhuwanupadhyay.onlineshop.inventory.infrastructure;
/*
    infrastructure  > messaging > brokers   > kafka
                                            > rabbitmq
                    persistence > jpa
                                > jdbc
                                > nosql
                    events  >   cdi

 */
