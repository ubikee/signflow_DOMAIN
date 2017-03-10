package es.elcorteingles.signflow.domain.budget.command;

import es.elcorteingles.signflow.domain.Command;

public class NewBudgetCommand extends Command {

    public final String subcampaignID;
    
    public NewBudgetCommand(String ownerID, String subcampaignID) {
        super(ownerID);
        this.subcampaignID = subcampaignID;
    }
    
}
