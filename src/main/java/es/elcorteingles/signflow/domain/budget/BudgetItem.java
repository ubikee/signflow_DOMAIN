package es.elcorteingles.signflow.domain.budget;

import javax.money.MonetaryAmount;

public class BudgetItem<T> {
    
    public final String id;
    
    private final T concept;
    private int units;
    private MonetaryAmount amount;
    
    public BudgetItem(String id, T concept, int units) {
        this.id = id;
        this.concept = concept;
    } 
    
    public T concept() {
        return this.concept;
    }
    
    public int units() {
        return this.units;
    }
    
    public void changeUnits(int units) {
        this.units = units;
    }
    
}
