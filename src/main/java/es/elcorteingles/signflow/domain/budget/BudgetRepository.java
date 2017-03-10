package es.elcorteingles.signflow.domain.budget;

import es.elcorteingles.signflow.domain.EntityFactory;
import es.elcorteingles.signflow.domain.EventStore;
import es.elcorteingles.signflow.domain.EventStoreRepository;
import java.util.UUID;

public final class BudgetRepository extends EventStoreRepository<MaterialsBudget> {

    public BudgetRepository(final EventStore store) {
        super(store, new EntityFactory<MaterialsBudget>() {
            @Override
            protected MaterialsBudget instance() {
                return new MaterialsBudget();
            }
        });
    }

    @Override
    public UUID nextID() {
        return UUID.randomUUID();
    }

}
