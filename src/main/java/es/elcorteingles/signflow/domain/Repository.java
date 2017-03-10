package es.elcorteingles.signflow.domain;

import java.util.Optional;
import java.util.UUID;

public interface Repository<T extends Entity> {
    
    UUID nextID();
    
    Optional<T> findByID(String id);

}
