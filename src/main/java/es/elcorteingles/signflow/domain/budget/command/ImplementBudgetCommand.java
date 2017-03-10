package es.elcorteingles.signflow.domain.budget.command;

import es.elcorteingles.signflow.domain.Command;

public class ImplementBudgetCommand extends Command {

    public final String budgetID;
    
    public ImplementBudgetCommand(String ownerID, String budgetID) {
        super(ownerID);
        this.budgetID = budgetID;
    }
}
