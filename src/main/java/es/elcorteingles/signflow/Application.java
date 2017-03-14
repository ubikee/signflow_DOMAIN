package es.elcorteingles.signflow;

import es.elcorteingles.signflow.domain.Store;
import es.elcorteingles.signflow.domain.Bus;
import es.elcorteingles.signflow.domain.EventBusInMemory;
import es.elcorteingles.signflow.domain.EventStoreInMemory;
import es.elcorteingles.signflow.subcampaign.domain.SubcampaignRepository;
import es.elcorteingles.signflow.domain.access.AuthenticationService;
import es.elcorteingles.signflow.domain.art.ArtEventHandlers;
import es.elcorteingles.signflow.domain.art.ArtRepository;
import es.elcorteingles.signflow.domain.art.ArtService;
import es.elcorteingles.signflow.domain.budget.BudgetEventHandlers;
import es.elcorteingles.signflow.domain.budget.BudgetRepository;
import es.elcorteingles.signflow.domain.budget.MaterialsBudgetService;
import es.elcorteingles.signflow.subcampaign.domain.SubcampaignService;

/**
 * 
 * TODO: Good candidate for IOC application context
 */ 
public class Application {

    public final static Bus BUS = new EventBusInMemory();
    public final static Store STORE = new EventStoreInMemory();
    
    public final static SubcampaignRepository SUBCAMPAIGNS = new SubcampaignRepository(STORE);
    public final static BudgetRepository BUDGETS = new BudgetRepository(STORE);
    public final static ArtRepository artRepository = new ArtRepository(STORE);
            
    static {
        BUS.addAllEventsListener(STORE::save);
        BudgetEventHandlers.register(BUS); // TODO: BUS.addAllEventsListener(BudgetEventHandlers.all());
        ArtEventHandlers.register(BUS);
    }
    
    public static Bus domainEventPublisher() {
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
