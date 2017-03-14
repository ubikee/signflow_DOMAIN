package es.elcorteingles.signflow.domain;

public interface Bus {

    void addAllEventsListener(DomainEventSubscriber subscriber);

    void addEventListener(String eventType, DomainEventSubscriber subscriber);

    void emit(Event event);
    
}
