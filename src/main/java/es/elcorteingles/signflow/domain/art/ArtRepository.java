package es.elcorteingles.signflow.domain.art;

import es.elcorteingles.signflow.domain.EntityFactory;
import es.elcorteingles.signflow.domain.EventStore;
import es.elcorteingles.signflow.domain.EventStoreRepository;

public class ArtRepository extends EventStoreRepository<Art> {
    
    public ArtRepository(final EventStore store) {
        super(store, new EntityFactory<Art>() {
            @Override
            protected Art instance() {
                return new Art();
            }
        });
    }
    
}
