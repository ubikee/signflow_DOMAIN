package es.elcorteingles.signflow.domain.art;

import es.elcorteingles.signflow.Application;
import es.elcorteingles.signflow.domain.EventBus;
import es.elcorteingles.signflow.domain.DomainEventSubscriber;
import es.elcorteingles.signflow.domain.art.command.NewBudgetNotification;
import es.elcorteingles.signflow.domain.budget.event.BudgetImplemented;

public class ArtEventHandlers {

    static DomainEventSubscriber<BudgetImplemented> handleImplementedBudgetEvent = (event) -> {
        NewBudgetNotification command = new NewBudgetNotification(event.entityID);
        Application.artService().newBudgetNotification(command);
    };

    public static void register(EventBus bus) {
        bus.addEventListener(BudgetImplemented.TYPE, handleImplementedBudgetEvent);
    }

}
