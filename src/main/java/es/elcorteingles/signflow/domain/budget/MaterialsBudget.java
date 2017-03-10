package es.elcorteingles.signflow.domain.budget;

import es.elcorteingles.signflow.domain.budget.event.BudgetImplemented;
import es.elcorteingles.signflow.domain.budget.event.BudgetCreated;
import es.elcorteingles.signflow.domain.budget.event.MaterialPlanned;
import es.elcorteingles.signflow.domain.Entity;
import es.elcorteingles.signflow.domain.EventHandler;
import es.elcorteingles.signflow.domain.materials.Material;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class MaterialsBudget extends Entity implements Budget<Material> {

    private UUID id;
    private String subcampaingID;
    private final Set<BudgetItem<Material>> lines = new HashSet<>();
    private STATE state;

    protected MaterialsBudget() {}
    
    public MaterialsBudget(final UUID id, final String subcampaignID) {  
        emit(new BudgetCreated(id.toString(), subcampaignID));
    }

    @EventHandler(event = BudgetCreated.TYPE)
    public void handleMaterialBudgetCreatedEvent(BudgetCreated event) {
        this.id = UUID.fromString(event.entityID);
        this.subcampaingID = event.subcampaignID;
        this.state = STATE.OPEN; 
    }

    @Override
    public void planItem(String materialID, int units) {
        emit(new MaterialPlanned(this.id.toString(), materialID, units));
    }

    @EventHandler(event = MaterialPlanned.TYPE)
    public void plannedItem(MaterialPlanned event) {
        Material material = new Material();
        BudgetItem<Material> item = new BudgetItem<>("id", material, event.units);
        this.lines.add(item);
    }

    public void implement() {
        emit(new BudgetImplemented(this.id));
    }

    @EventHandler(event = BudgetImplemented.TYPE)
    public void handleImplementedBudgetEvent(BudgetImplemented event) {
        this.state = STATE.CLOSED;
    }
        
    @Override
    public Set<BudgetItem<Material>> allItems() {
        return this.lines;
    }

    public MaterialDescriptor descriptor() {
        return new MaterialDescriptor(this.id, this.subcampaingID, this.state);
    }

    public static class MaterialDescriptor {

        public final UUID id;
        public final String subcampaignID;
        public final STATE state;

        private MaterialDescriptor(UUID id, String subcampaignID, STATE state) {
            this.id = id;
            this.subcampaignID = subcampaignID;
            this.state = state;
        }

        @Override
        public String toString() {
            return "MaterialDescriptor{" + "id=" + id + ", subcampaignID=" + subcampaignID + ", state=" + state + '}';
        }
        
        
    }
}
