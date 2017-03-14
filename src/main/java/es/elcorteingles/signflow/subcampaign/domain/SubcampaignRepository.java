package es.elcorteingles.signflow.subcampaign.domain;

import es.elcorteingles.signflow.domain.EntityFactory;
import es.elcorteingles.signflow.domain.EventStore;
import es.elcorteingles.signflow.domain.EventStoreRepository;
import java.util.UUID;

public final class SubcampaignRepository extends EventStoreRepository<SubCampaign> {

    public SubcampaignRepository(final EventStore store) {
        super(store, new EntityFactory<SubCampaign>() {
            @Override
            protected SubCampaign instance() {
                return new SubCampaign();
            }
        });
    }

    @Override
    public UUID nextID() {
        return UUID.randomUUID();
    }

}
