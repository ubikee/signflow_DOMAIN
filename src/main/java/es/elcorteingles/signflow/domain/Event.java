package es.elcorteingles.signflow.domain;

import java.time.LocalDateTime;

public abstract class Event {

    public final LocalDateTime ocurredOn;
    public final String entityID;

    public Event(String entityID) {
        this.entityID = entityID;
        this.ocurredOn = LocalDateTime.now();
    }
    
    public Event(String entityID, LocalDateTime ocurredOn ) {
        this.entityID = entityID;
        this.ocurredOn = ocurredOn;
    }
    
    public abstract String type();

    @Override
    public String toString() {
        return "Event{ type=" + type() + ", ocurredOn=" + ocurredOn + ", entityID=" + entityID + '}';
    }
    
    
}
