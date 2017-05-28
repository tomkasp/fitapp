package com.tomkasp.training.infrastructure.event;

import com.tomkasp.fitapp_common.common.event.EventStore;
import com.tomkasp.fitapp_common.common.event.sourcing.DispatchableDomainEvent;
import com.tomkasp.fitapp_common.common.event.sourcing.EventDispatcher;
import com.tomkasp.fitapp_common.common.event.sourcing.EventNotifiable;
import org.springframework.stereotype.Component;

/**
 * @author Tomasz Kasprzycki
 *         <p>
 *         This is Event Dispacher which fallows event storing operation
 *         GOAL is to pass events to PROJECTIONS
 */
@Component
public class FollowStoreEventDispatcher implements EventDispatcher, EventNotifiable {

    EventStore eventStore;

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

        // this could be multi-threaded from here,
        // but is not for simplicity

        // child EventDispatchers should use only
        // ConnectionProvider.connection() and
        // not commit. i will commit and close the
        // connection here

//        for (DispatchableDomainEvent dispatchableDomainEvent : eventStore.eventsSince(1L)) {
//            this.dispatch(dispatchableDomainEvent);
//        }


    }
}
