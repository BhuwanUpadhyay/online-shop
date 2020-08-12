/**
 * This package contains all the possible inbound services a Bounded Context provides classified by protocol.
 * <p>
 * They serve two main purposes:
 * - Protocol negotiation on behalf of the domain model (e.g., REST API(s), Web API(s), WebSocket(s), FTP(s), Message Listener(s))
 * - View adapters for data (e.g., Browser View(s), Mobile View(s))
 */
package io.github.bhuwanupadhyay.onlineshop.delivery.interfaces;
/*
    Package structure for interfaces

    interfaces ----> web
               ----> rest
               ----> socket
               ----> file
               ----> events

 */
