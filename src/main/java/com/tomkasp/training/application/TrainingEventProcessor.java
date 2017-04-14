package com.tomkasp.training.application;

import com.tomkasp.common.domain.model.DomainEvent;
import com.tomkasp.common.domain.model.DomainEventPublisher;
import com.tomkasp.common.domain.model.DomainEventSubscriber;
import com.tomkasp.common.event.EventStore;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Tomasz Kasprzycki
 */
@Aspect
@Component
public class TrainingEventProcessor {

    private final Logger log = LoggerFactory.getLogger(TrainingEventProcessor.class);

    @Autowired
    private EventStore eventStore;

    public static void register() {
        (new TrainingEventProcessor()).listen();
    }

    public TrainingEventProcessor() {
        super();
    }

    @Before("execution(* com.tomkasp.training.application.*.*(..))")
    private void listen() {

        log.debug("Listen ......");
        DomainEventPublisher
            .instance()
            .subscribe(new DomainEventSubscriber<DomainEvent>() {
                @Override
                public void handleEvent(DomainEvent aDomainEvent) {
                    log.debug("domain handle event: {}", aDomainEvent);
                    store(aDomainEvent);
                }

                @Override
                public Class<DomainEvent> subscribedToEventType() {
                    return DomainEvent.class;
                }

            });
    }

    /**
     * Stores aDomainEvent to the event store.
     * @param aDomainEvent the DomainEvent to store
     */
    private void store(DomainEvent aDomainEvent) {
        this.eventStore().append(aDomainEvent);
    }

    private EventStore eventStore() {
        return this.eventStore;
    }


}
