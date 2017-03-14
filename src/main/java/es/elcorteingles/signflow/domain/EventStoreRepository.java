package es.elcorteingles.signflow.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.UUID;

public abstract class EventStoreRepository<T extends Entity> implements Repository<T> {

    private final Store store;
    private final EntityFactory<T> factory;
    private final Map<String, T> cache = new HashMap<>();
    // TODO: cache expiration ~ EHCache? 

    public EventStoreRepository(final Store store, EntityFactory<T> factory) {
        this.store = store;
        this.factory = factory;
    }

    @Override
    public abstract UUID nextID();

    @Override
    public final Optional<T> findByID(String ID) {        
        return Optional.of( cache.containsKey(ID) ? cache.get(ID) : this.recover(ID) );
        //TODO: look for new events for cached entities and apply them
    }
    
    private final T recover(String ID) {
        Queue<Event> events = store.get(ID);
        T item = this.factory.recover(events);
        cache.put(ID, item);
        return item;
    }

}
