package es.elcorteingles.signflow.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class EventStore {

    private final Map<String, Queue<Event>> events = new HashMap<>();

    public void store(Event event) {
        Queue entityQueue = events.get(event.entityID);
        if (entityQueue == null)
            events.put(event.entityID, entityQueue = new LinkedList<>());
        entityQueue.offer(event);
    }
    
    public Queue<Event> get(String entityID) {
        return events.get(entityID);
    }

}
