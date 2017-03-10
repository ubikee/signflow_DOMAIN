package es.elcorteingles.signflow.domain.budget.event;

import es.elcorteingles.signflow.domain.Event;

public class BudgetCreated extends Event {

    public final static String TYPE = "BUDGET:CREATED";
    public final String subcampaignID;

    public BudgetCreated(final String entityID, final String subcampaignID) {
        super(entityID);
        this.subcampaignID = subcampaignID;
    }

    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public String toString() {
        return super.toString() + "BudgetCreated{" + "subcampaignID=" + subcampaignID + '}';
    }

}
