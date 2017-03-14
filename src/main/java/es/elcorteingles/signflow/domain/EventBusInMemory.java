package es.elcorteingles.signflow.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventBusInMemory implements Bus {
    
    Map<String, List<DomainEventSubscriber>> subscribers = new HashMap<>();
    Optional<DomainEventSubscriber<Event>> universalSubscriber;
    ExecutorService executor = Executors.newFixedThreadPool(10);

    @Override
    public final void emit(Event event) {
        
        universalSubscriber.ifPresent(subscriber -> subscriber.handle(event));
        
        String eventTYPE = event.type();
        List<DomainEventSubscriber> eventHandlers = subscribers.getOrDefault(eventTYPE, new ArrayList<>());
        eventHandlers.stream().map((DomainEventSubscriber handler) -> {
            Runnable runnable = () -> handler.handle(event);
            return runnable;
        }).forEach(executor::execute);
        
    }
    
    @Override
    public final void addEventListener(String eventType, DomainEventSubscriber subscriber) {
        List<DomainEventSubscriber> eventHandlers = subscribers.get(eventType);
        if (eventHandlers == null)
            subscribers.put(eventType, eventHandlers = new ArrayList<>());
        eventHandlers.add(subscriber);
    }
    
    @Override
    public final void addAllEventsListener(DomainEventSubscriber subscriber) {
        this.universalSubscriber = Optional.of(subscriber);
    }
}
