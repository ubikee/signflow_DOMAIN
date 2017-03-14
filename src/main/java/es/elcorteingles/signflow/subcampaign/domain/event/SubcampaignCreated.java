package es.elcorteingles.signflow.subcampaign.domain.event;

import es.elcorteingles.signflow.domain.Event;

public class SubcampaignCreated extends Event {

    public final static String TYPE = "SUBCAMPAIGN:CREATED";
    public final String name;
    public final String owner;

    public SubcampaignCreated(final String entityID, final String name, final String owner) {
        super(entityID);
        this.name = name;
        this.owner = owner;
    }

    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public String toString() {
        return super.toString()+"SubcampaignCreated{" + "name=" + name + ", owner=" + owner + '}';
    }

    
}
