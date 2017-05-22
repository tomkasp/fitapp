package com.tomkasp.common.infrastracture.persistance.hibernate;

import com.tomkasp.common.domain.model.DomainEvent;
import com.tomkasp.common.event.EventSerializer;
import com.tomkasp.common.event.EventStore;
import com.tomkasp.common.event.StoredEvent;
import com.tomkasp.common.event.sourcing.DispatchableDomainEvent;
import com.tomkasp.common.event.sourcing.EventNotifiable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
//TODO change event store to sourcing event store
@Component
public class HibernateEventStore implements EventStore {


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EventNotifiable eventNotifiable;


    @Override
    public List<StoredEvent> allStoredEventsBetween(long aLowStoredEventId, long aHighStoredEventId) {
        return null;
    }

    @Override
    public List<StoredEvent> allStoredEventsSince(long aStoredEventId) {
        return null;
    }

    @Override
    public StoredEvent append(DomainEvent aDomainEvent) {
        String eventSerialization =
            EventSerializer.instance().serialize(aDomainEvent);

        StoredEvent storedEvent =
            new StoredEvent(
                aDomainEvent.getClass().getName(),
                aDomainEvent.occurredOn(),
                eventSerialization);
        entityManager.persist(storedEvent);


        this.notifyDispatchableEvents();


        return storedEvent;
    }

    @Override
    public List<DispatchableDomainEvent> eventsSince(long aLastReceivedEvent) {
        return null;

    }

    private void notifyDispatchableEvents() {
        final EventNotifiable eventNotifiable = this.eventNotifiable();
        if (eventNotifiable != null) {
            eventNotifiable.notifyDispatchableEvents();
        }
    }

    private EventNotifiable eventNotifiable() {
        return this.eventNotifiable;
    }

    @Override
    public void close() {

    }

    @Override
    public long countStoredEvents() {
        return 0;
    }
}
