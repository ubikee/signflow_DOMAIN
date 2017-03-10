package es.elcorteingles.signflow.domain.subcampaign;

import es.elcorteingles.signflow.domain.subcampaign.event.SubcampaignCreated;
import es.elcorteingles.signflow.domain.Entity;
import es.elcorteingles.signflow.domain.EventHandler;
import java.util.UUID;

public final class SubCampaign extends Entity {

    private UUID id;
    private String name;
    private String owner;

    SubCampaign() {
    }

    public SubCampaign(UUID id, String name, String owner) {
        emit(new SubcampaignCreated(id.toString(), name, owner));
    }

    @EventHandler(event = SubcampaignCreated.TYPE)
    public void handleSubcampaignCreatedEvent(SubcampaignCreated event) {
        this.id = UUID.fromString(event.entityID);
        this.name = event.name;
        this.owner = event.owner;
    }

    public void changeName(String name) {
        //emit(new SubcampaignNameChangedEvent(name));
    }

    //public void changedName(SubcampaignNameChangedEvent event) {
    //    this.name = event.newName;
    //}
    
    public SubCampaignDescriptor descriptor() {
        return new SubCampaignDescriptor(this.id, this.name, this.owner);
    }

    public static class SubCampaignDescriptor {

        public final UUID id;
        public final String name;
        public final String ownerID;

        private SubCampaignDescriptor(UUID id, String name, String owner) {
            this.id = id;
            this.name = name;
            this.ownerID = owner;
        }

        @Override
        public String toString() {
            return "SubCampaignDescriptor{" + "id=" + id + ", name=" + name + ", ownerID=" + ownerID + '}';
        }
    }

}
