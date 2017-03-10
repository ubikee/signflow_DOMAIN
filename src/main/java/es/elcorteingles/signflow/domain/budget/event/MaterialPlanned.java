package es.elcorteingles.signflow.domain.budget.event;

import es.elcorteingles.signflow.domain.Event;

public class MaterialPlanned extends Event {

    public static final String TYPE = "MATERIAL:PLANNED";
    public final String materialID;
    public final int units;

    public MaterialPlanned(String budgetID, String materialID, int units) {
        super(budgetID);
        this.materialID = materialID;
        this.units = units;
    }

    @Override
    public String type() {
        return TYPE;
    }

}
