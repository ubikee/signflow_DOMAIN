package es.elcorteingles.signflow.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DomainEventPublisher {
    
    Map<String, List<DomainEventSubscriber>> subscribers = new HashMap<>();
    Optional<DomainEventSubscriber<Event>> maybeUniversalSubscriber;
    ExecutorService executor = Executors.newFixedThreadPool(4);

    public final void emit(Event event) {
        
        if (maybeUniversalSubscriber.isPresent()) {
            maybeUniversalSubscriber.get().handle(event);
        }
        
        String eventTYPE = event.type();
        List<DomainEventSubscriber> eventHandlers = subscribers.getOrDefault(eventTYPE, new ArrayList<>());
        eventHandlers.stream().map((DomainEventSubscriber handler) -> {
            Runnable runnable = () -> handler.handle(event);
            return runnable;
        }).forEach(executor::execute);
        
    }
    
    public final void subscribe(String eventType, DomainEventSubscriber subscriber) {
        List<DomainEventSubscriber> eventHandlers = subscribers.get(eventType);
        if (eventHandlers == null)
            subscribers.put(eventType, eventHandlers = new ArrayList<>());
        eventHandlers.add(subscriber);
    }
    
    public final void subscribeToAll(DomainEventSubscriber subscriber) {
        this.maybeUniversalSubscriber = Optional.of(subscriber);
    }
}
