package es.elcorteingles.signflow.subcampaign.domain;

import es.elcorteingles.signflow.domain.Store;
import es.elcorteingles.signflow.domain.EntityFactory;
import es.elcorteingles.signflow.domain.EventStoreRepository;
import java.util.UUID;

public final class SubcampaignRepository extends EventStoreRepository<SubCampaign> {

    public SubcampaignRepository(final Store store) {
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
