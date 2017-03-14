package es.elcorteingles.signflow.domain;

import java.util.Queue;

public interface Store {

    Queue<Event> get(String entityID);

    void save(Event event);
    

}
