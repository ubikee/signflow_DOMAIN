package es.elcorteingles.signflow.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class EventStoreInMemory implements Store {

    private final Map<String, Queue<Event>> events = new HashMap<>();

    public final void save(Event event) {
        Queue entityQueue = events.get(event.entityID);
        if (entityQueue == null)
            events.put(event.entityID, entityQueue = new LinkedList<>());
        entityQueue.offer(event);
    }
    
    public final Queue<Event> get(String entityID) {
        return events.get(entityID);
    }

}
