package com.tomkasp.common.infrastracture.hibernate;

import com.tomkasp.common.domain.model.DomainEvent;
import com.tomkasp.common.event.EventSerializer;
import com.tomkasp.common.event.EventStore;
import com.tomkasp.common.event.StoredEvent;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class HibernateEventStore implements EventStore {


    @PersistenceContext
    private EntityManager entityManager;

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

        return storedEvent;
    }

    @Override
    public void close() {

    }

    @Override
    public long countStoredEvents() {
        return 0;
    }
}