package es.elcorteingles.signflow.domain.budget;

import java.util.Set;

public interface Budget<T> {

    public enum STATE { OPEN, CLOSED };
    
    void planItem(String itemID, int units);
    
    Set<BudgetItem<T>> allItems();

}
