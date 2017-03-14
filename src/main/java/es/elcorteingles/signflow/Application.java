package es.elcorteingles.signflow;

import es.elcorteingles.signflow.domain.EventBus;
import es.elcorteingles.signflow.domain.EventStore;
import es.elcorteingles.signflow.subcampaign.domain.SubcampaignRepository;
import es.elcorteingles.signflow.domain.access.AuthenticationService;
import es.elcorteingles.signflow.domain.art.ArtEventHandlers;
import es.elcorteingles.signflow.domain.art.ArtRepository;
import es.elcorteingles.signflow.domain.art.ArtService;
import es.elcorteingles.signflow.domain.budget.BudgetEventHandlers;
import es.elcorteingles.signflow.domain.budget.BudgetRepository;
import es.elcorteingles.signflow.domain.budget.MaterialsBudgetService;
import es.elcorteingles.signflow.subcampaign.domain.SubCampaignEventStore;
import es.elcorteingles.signflow.subcampaign.domain.SubcampaignService;

/**
 * 
 * TODO: Good candidate for IOC application context
 */ 
public class Application {

    public final static EventBus BUS = new EventBus();
    public final static EventStore EVENTSTORE = new SubCampaignEventStore();
    public final static SubcampaignRepository SUBCAMPAIGNS = new SubcampaignRepository(EVENTSTORE);
    public final static BudgetRepository BUDGETS = new BudgetRepository(EVENTSTORE);
    public final static ArtRepository artRepository = new ArtRepository(EVENTSTORE);
            
    static {
        BUS.addAllEventsListener(EVENTSTORE::store);
        BudgetEventHandlers.register(BUS); // TODO: BUS.addAllEventsListener(BudgetEventHandlers.all());
        ArtEventHandlers.register(BUS);
    }
    
    public static EventBus domainEventPublisher() {
        return BUS;
    }
    
    public static AuthenticationService authenticationService() {
        return new AuthenticationService();
    }
    
    public static SubcampaignService subcampaingService() {
        return new SubcampaignService(SUBCAMPAIGNS);
    }
    
    public static MaterialsBudgetService materialBudgetService() {
        return new MaterialsBudgetService(BUDGETS);
    }

    public static ArtService artService() {
        return new ArtService(artRepository);
    }
}
