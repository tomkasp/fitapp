package com.tomkasp.training.infrastructure.event;

import com.tomkasp.common.event.sourcing.DispatchableDomainEvent;
import com.tomkasp.common.event.sourcing.EventDispatcher;
import com.tomkasp.common.event.sourcing.EventNotifiable;
import org.springframework.stereotype.Component;

/**
 * @author Tomasz Kasprzycki
 *         <p>
 *         This is Event Dispacher which fallows event storing operation
 */
@Component
public class FollowStoreEventDispatcher implements EventDispatcher, EventNotifiable {

    @Override
    public void dispatch(DispatchableDomainEvent aDispatchableDomainEvent) {

    }

    @Override
    public void registerEventDispatcher(EventDispatcher anEventDispatcher) {

    }

    @Override
    public boolean understands(DispatchableDomainEvent aDispatchableDomainEvent) {
        return false;
    }

    @Override
    public void notifyDispatchableEvents() {

    }
}
