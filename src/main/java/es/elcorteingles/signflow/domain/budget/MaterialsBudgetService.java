package es.elcorteingles.signflow.domain.budget;

import es.elcorteingles.signflow.domain.budget.command.ImplementBudgetCommand;
import es.elcorteingles.signflow.domain.budget.command.NewBudgetCommand;
import es.elcorteingles.signflow.domain.budget.command.PlanMaterialCommand;
import java.util.Optional;
import java.util.UUID;

public final class MaterialsBudgetService {

    BudgetRepository budgets;

    public MaterialsBudgetService(BudgetRepository budgets) {
        this.budgets = budgets;
    }

    public String newBudget(NewBudgetCommand command) {
        return this.newBudgetWith(command.subcampaignID);
    }

    public void planMaterial(PlanMaterialCommand command) {
        Optional<MaterialsBudget> maybeBudget = budgets.findByID(command.budgetID);
        if (maybeBudget.isPresent()) {
            MaterialsBudget budget = maybeBudget.get();
            budget.planItem(command.materialID, command.units);
            //budgets.save(budget);
        }
    }

    public void implementBudget(ImplementBudgetCommand command) {
        Optional<MaterialsBudget> maybeBudget = budgets.findByID(command.budgetID);
        if (maybeBudget.isPresent()) {
            MaterialsBudget budget = maybeBudget.get();
            budget.implement();
            //budgets.save(budget);
        }
    }

    private String newBudgetWith(String subcampaignID) {
        UUID id = budgets.nextID();
        MaterialsBudget budget = new MaterialsBudget(id, subcampaignID);
        //budgets.save(budget);
        return id.toString();
    }

}
