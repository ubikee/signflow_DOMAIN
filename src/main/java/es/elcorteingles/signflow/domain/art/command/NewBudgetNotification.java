package es.elcorteingles.signflow.domain.art.command;

import es.elcorteingles.signflow.domain.Command;

public class NewBudgetNotification extends Command {
    
    public NewBudgetNotification(String ownerID) {
        super(ownerID);
    }
    
}
