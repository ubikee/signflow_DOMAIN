package es.elcorteingles.signflow.domain;

import es.elcorteingles.signflow.domain.budget.event.BudgetCreated;

public class Projectors {
    
    static DomainEventSubscriber<BudgetCreated> budgetProjector = (event) -> {
        // write event data to mongoDB
    };
    
}
