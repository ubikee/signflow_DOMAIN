package es.elcorteingles.signflow;

import es.elcorteingles.signflow.domain.DomainEventPublisher;
import es.elcorteingles.signflow.domain.EventStore;
import es.elcorteingles.signflow.domain.subcampaign.SubcampaignRepository;
import es.elcorteingles.signflow.domain.access.AuthenticationService;
import es.elcorteingles.signflow.domain.art.ArtRepository;
import es.elcorteingles.signflow.domain.art.ArtService;
import es.elcorteingles.signflow.domain.budget.BudgetEventHandlers;
import es.elcorteingles.signflow.domain.budget.BudgetRepository;
import es.elcorteingles.signflow.domain.budget.MaterialsBudgetService;
import es.elcorteingles.signflow.domain.subcampaign.SubCampaignEventStore;
import es.elcorteingles.signflow.domain.subcampaign.SubcampaignService;

/**
 * 
 * TODO: Good candidate for IOC application context
 */ 
public class Application {

    public final static DomainEventPublisher BUS = new DomainEventPublisher();
    public final static EventStore EVENTSTORE = new SubCampaignEventStore();
    
    public final static SubcampaignRepository SUBCAMPAIGNS = new SubcampaignRepository(EVENTSTORE);
    public final static BudgetRepository BUDGETS = new BudgetRepository(EVENTSTORE);
    public final static ArtRepository artRepository = new ArtRepository(EVENTSTORE);
            
    static {
        BUS.subscribeEventStore((event) -> {
            EVENTSTORE.store(event);
        });
        BudgetEventHandlers.register(BUS);
    }
    
    public static DomainEventPublisher domainEventPublisher() {
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
