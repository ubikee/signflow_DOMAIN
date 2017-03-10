package es.elcorteingles.signflow.domain;

import java.util.Queue;

public abstract class EntityFactory<T extends Entity> {

    public T recover(Queue<Event> events) {
        T item = this.instance();
        events.forEach(item::apply);
        return item;
    }
    
    protected abstract T instance();

}
