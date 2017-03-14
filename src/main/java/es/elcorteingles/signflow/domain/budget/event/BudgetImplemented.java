package es.elcorteingles.signflow.domain.budget.event;

import es.elcorteingles.signflow.domain.Event;
import java.util.UUID;

public class BudgetImplemented extends Event {

    public static final String TYPE = "BUDGET:IMPLEMENTED";
    
    public BudgetImplemented(UUID id) {
        super(id.toString());
    }

    @Override
    public String type() {
        return TYPE;
    }

}
