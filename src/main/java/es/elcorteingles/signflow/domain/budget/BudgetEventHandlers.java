package es.elcorteingles.signflow.domain.budget;

import es.elcorteingles.signflow.domain.DomainEventSubscriber;
import es.elcorteingles.signflow.Application;
import es.elcorteingles.signflow.domain.Bus;
import es.elcorteingles.signflow.domain.budget.command.NewBudgetCommand;
import es.elcorteingles.signflow.domain.budget.event.BudgetCreated;
import es.elcorteingles.signflow.subcampaign.domain.event.SubcampaignCreated;

public final class BudgetEventHandlers {

    public static DomainEventSubscriber<SubcampaignCreated> subcampaignCreatedHandler = (event) -> {
        NewBudgetCommand command = new NewBudgetCommand(event.owner, event.entityID);
        Application.materialBudgetService().newBudget(command);
    };
    
    public static DomainEventSubscriber<BudgetCreated> budgetCreatedHandler = (event) -> {
        //DomainRegistry.notificationsService().notify(notificationCommand);
    };

    public static void register(Bus bus) {
        bus.addEventListener(SubcampaignCreated.TYPE, subcampaignCreatedHandler);
    }

}
