package es.elcorteingles.signflow.domain.budget.command;

import es.elcorteingles.signflow.domain.Command;
import java.util.Arrays;
import java.util.List;

public class PlanMaterialCommand extends Command {

    public final String budgetID;
    public final String materialID;
    public final int units;
    private final String[] languages;

    /**
     * 
     * @param budgetID
     * @param materialID
     * @param units 
     * @param languages 
     */
    public PlanMaterialCommand(String ownerID, String budgetID, String materialID, int units, String[] languages) {
        super(ownerID);
        this.budgetID = budgetID;
        this.materialID = materialID;
        this.languages = languages;
        this.units = units;
    } 
    
    public List<String> languages() {
        return Arrays.asList(languages);        
    }
}
