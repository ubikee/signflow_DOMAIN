package es.elcorteingles.signflow.domain.art;

import es.elcorteingles.signflow.domain.DomainEventPublisher;
import es.elcorteingles.signflow.domain.DomainEventSubscriber;
import es.elcorteingles.signflow.Application;
import es.elcorteingles.signflow.domain.budget.BudgetItem;
import es.elcorteingles.signflow.domain.budget.MaterialsBudget;
import es.elcorteingles.signflow.domain.budget.event.BudgetImplemented;
import es.elcorteingles.signflow.domain.budget.event.BudgetCreated;
import es.elcorteingles.signflow.domain.materials.Material;

public class ArtEventHandlers {

    static DomainEventSubscriber<BudgetCreated> handleImplementedBudgetEvent = (event) -> {
        
    };

    public static void register(DomainEventPublisher bus) {
        bus.subscribe(BudgetImplemented.class.getName(), handleImplementedBudgetEvent);
    }

}
