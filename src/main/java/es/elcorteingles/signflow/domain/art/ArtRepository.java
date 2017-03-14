package es.elcorteingles.signflow.domain.art;

import es.elcorteingles.signflow.domain.Store;
import es.elcorteingles.signflow.domain.EntityFactory;
import es.elcorteingles.signflow.domain.EventStoreRepository;
import java.util.UUID;

public class ArtRepository extends EventStoreRepository<Art> {

    public ArtRepository(final Store store) {
        super(store, new EntityFactory<Art>() {
            @Override
            protected Art instance() {
                return new Art();
            }
        });
    }

    @Override
    public UUID nextID() {
        return UUID.randomUUID();
    }

}
