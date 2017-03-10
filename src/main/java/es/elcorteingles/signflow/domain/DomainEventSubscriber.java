package es.elcorteingles.signflow.domain;

@FunctionalInterface
public interface DomainEventSubscriber<T extends Event> {
    
    public void handle(T event);
    
}
