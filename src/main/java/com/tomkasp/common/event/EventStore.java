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

package com.tomkasp.common.event;


import com.tomkasp.common.domain.model.DomainEvent;
import com.tomkasp.common.event.sourcing.DispatchableDomainEvent;

import java.util.List;

public interface EventStore {

    public List<StoredEvent> allStoredEventsBetween(long aLowStoredEventId, long aHighStoredEventId);

    public List<StoredEvent> allStoredEventsSince(long aStoredEventId);

    public StoredEvent append(DomainEvent aDomainEvent);

    //TODO move this to sourcing event store
    public List<DispatchableDomainEvent> eventsSince(long aLastReceivedEvent);

    public void close();

    public long countStoredEvents();
}
