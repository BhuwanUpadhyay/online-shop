/**
 * This package contains the Application services a Bounded Context’s domain model would require.
 * <p>
 * Application services classes serve multiple purposes:
 * - Act as ports for input interfaces and output repositories
 * - Commands, Queries, Events, and Saga participants
 * - Transaction initiation, control, and termination
 * - Centralized concerns (e.g., Logging, Security, Metrics) for the underlying domain model
 * - Data transfer object transformation
 * - Callouts to other Bounded Contexts
 */
package io.github.bhuwanupadhyay.onlineshop.order.application;

/*
    Package structure for Application services

    application >   transform
                    sags
                    internal >  commands
                                queries
                                events
 */
