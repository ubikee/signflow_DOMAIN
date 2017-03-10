package es.elcorteingles.signflow.domain;

import java.util.Optional;
import java.util.Queue;
import java.util.UUID;

public class EventStoreRepository<T extends Entity> implements Repository<T> {

    private final EventStore store;
    private final EntityFactory<T> factory;
    
    public EventStoreRepository(final EventStore store, EntityFactory<T> factory) {
        this.store = store;
        this.factory = factory;
    }

    @Override
    public UUID nextID() {
        return UUID.randomUUID();
    }

    @Override
    public Optional<T> findByID(String ID) {
        Queue<Event> events = store.get(ID);
        T item = this.factory.recover(events);
        return Optional.of(item);
    }

}
