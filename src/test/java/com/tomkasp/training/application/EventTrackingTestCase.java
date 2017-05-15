//   Copyright 2012,2013 Vaughn Vernon
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package com.tomkasp.training.application;

import com.tomkasp.common.domain.model.DomainEvent;
import com.tomkasp.common.domain.model.DomainEventPublisher;
import com.tomkasp.common.domain.model.DomainEventSubscriber;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class EventTrackingTestCase extends TestCase {

    private List<Class<? extends DomainEvent>> handledEvents;
    private Map<String, String> handledNotifications;

    protected EventTrackingTestCase() {
        super();
    }

    protected void expectedEvent(Class<? extends DomainEvent> aDomainEventType) {
        this.expectedEvent(aDomainEventType, 1);
    }

    protected void expectedEvent(Class<? extends DomainEvent> aDomainEventType, int aTotal) {
        int count = 0;

        for (Class<? extends DomainEvent> type : this.handledEvents) {
            if (type == aDomainEventType) {
                ++count;
            }
        }

        if (count != aTotal) {
            throw new IllegalStateException("Expected " + aTotal + " " + aDomainEventType.getSimpleName() + " events, but handled "
                + this.handledEvents.size() + " events: " + this.handledEvents);
        }
    }

    protected void expectedEvents(int anEventCount) {
        if (this.handledEvents.size() != anEventCount) {
            throw new IllegalStateException("Expected " + anEventCount + " events, but handled " + this.handledEvents.size()
                + " events: " + this.handledEvents);
        }
    }

    protected void expectedNotification(Class<? extends DomainEvent> aNotificationType) {
        this.expectedNotification(aNotificationType, 1);
    }

    protected void expectedNotification(Class<? extends DomainEvent> aNotificationType, int aTotal) {
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            // ignore
        }

        int count = 0;

        String notificationTypeName = aNotificationType.getName();

        for (String type : this.handledNotifications.values()) {
            if (type.equals(notificationTypeName)) {
                // System.out.println("MATCHED: " + type);
                // System.out.println("WITH: " + notificationTypeName);
                ++count;
            }
        }

        if (count != aTotal) {
            throw new IllegalStateException("Expected " + aTotal + " " + aNotificationType.getSimpleName()
                + " notifications, but handled " + this.handledNotifications.size() + " notifications: "
                + this.handledNotifications.values());
        }
    }

    protected void expectedNotifications(int anNotificationCount) {
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            // ignore
        }

        if (this.handledNotifications.size() != anNotificationCount) {
            throw new IllegalStateException("Expected " + anNotificationCount + " notifications, but handled "
                + this.handledNotifications.size() + " notifications: " + this.handledNotifications.values());
        }
    }

    @Override
    protected void setUp() throws Exception {
        Thread.sleep(100L);


        Thread.sleep(100L);

        DomainEventPublisher.instance().reset();

        DomainEventPublisher.instance().subscribe(new DomainEventSubscriber<DomainEvent>() {
            @Override
            public void handleEvent(DomainEvent aDomainEvent) {
                handledEvents.add(aDomainEvent.getClass());
            }

            @Override
            public Class<DomainEvent> subscribedToEventType() {
                return DomainEvent.class;
            }
        });

        this.handledEvents = new ArrayList<Class<? extends DomainEvent>>();
        this.handledNotifications = new HashMap<String, String>();

        clearExchangeListeners();

        // this.agilePmSlothMQExchangeListener = new TestAgilePMSlothMQExchangeListener();
        // this.collaborationSlothMQExchangeListener = new TestCollaborationSlothMQExchangeListener();
        // this.identityAccessSlothMQExchangeListener = new TestIdentityAccessSlothMQExchangeListener();

        Thread.sleep(200L);
    }

    private void clearExchangeListeners() throws InterruptedException {
        // At beginning of the test, give MQExchangeListeners time to receive
        // messages from queues which were published by previous tests.
        // Since RabbitMQ Java Client does not allow queue listing or cleaning
        // all queues at once, we can just consume all messages and do
        // nothing with them as a work-around.
        Thread.sleep(500L);

    }

    @Override
    protected void tearDown() throws Exception {

        // this.agilePmSlothMQExchangeListener.close();
        // this.collaborationSlothMQExchangeListener.close();
        // this.identityAccessSlothMQExchangeListener.close();
        //
        // SlothClient.instance().closeAll();

        super.tearDown();
    }


}
