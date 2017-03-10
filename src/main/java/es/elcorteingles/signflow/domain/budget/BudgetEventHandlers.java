package es.elcorteingles.signflow.domain.budget;

import es.elcorteingles.signflow.domain.DomainEventPublisher;
import es.elcorteingles.signflow.domain.DomainEventSubscriber;
import es.elcorteingles.signflow.Application;
import es.elcorteingles.signflow.domain.budget.command.NewBudgetCommand;
import es.elcorteingles.signflow.domain.budget.event.BudgetCreated;
import es.elcorteingles.signflow.domain.subcampaign.event.SubcampaignCreated;

public final class BudgetEventHandlers {

    static DomainEventSubscriber<SubcampaignCreated> subcampaignCreatedHandler = (event) -> {
        NewBudgetCommand command = new NewBudgetCommand(event.owner, event.entityID);
        Application.materialBudgetService().newBudget(command);
    };
    
    static DomainEventSubscriber<BudgetCreated> budgetCreatedHandler = (event) -> {
        //DomainRegistry.notificationsService().notify(notificationCommand);
    };

    public static void register(DomainEventPublisher bus) {
        bus.subscribe(SubcampaignCreated.TYPE, subcampaignCreatedHandler);
    }

}
