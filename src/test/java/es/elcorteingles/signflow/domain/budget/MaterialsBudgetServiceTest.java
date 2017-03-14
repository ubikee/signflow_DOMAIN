package es.elcorteingles.signflow.domain.budget;

import es.elcorteingles.signflow.Application;
import static es.elcorteingles.signflow.Application.*;
import es.elcorteingles.signflow.domain.budget.command.ImplementBudgetCommand;
import es.elcorteingles.signflow.domain.budget.command.NewBudgetCommand;
import es.elcorteingles.signflow.domain.budget.command.PlanMaterialCommand;
import es.elcorteingles.signflow.domain.budget.event.BudgetCreated;
import es.elcorteingles.signflow.subcampaign.domain.event.SubcampaignCreated;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class MaterialsBudgetServiceTest {

    final static MaterialsBudgetService budgetService = Application.materialBudgetService();
    final static String BUDGET_ID = UUID.randomUUID().toString();
    final static String SUBCAMPAIGN_ID = UUID.randomUUID().toString();
    final static String OWNER_ID = "O00001";
    
    @BeforeClass
    public static void fixture() {
        BUS.emit(new SubcampaignCreated(SUBCAMPAIGN_ID, "", ""));
        BUS.emit(new BudgetCreated(BUDGET_ID, SUBCAMPAIGN_ID));
    }

    @Test
    public void testNewBudget() throws Exception {

        final NewBudgetCommand command = new NewBudgetCommand(OWNER_ID, SUBCAMPAIGN_ID);
        final String newBudgetID = budgetService.newBudget(command);
        
        // assertions
        final Optional<MaterialsBudget> budget = BUDGETS.findByID(newBudgetID);
        assertTrue(budget.isPresent());
        assertEquals(SUBCAMPAIGN_ID, budget.get().descriptor().subcampaignID);
        assertEquals(Budget.STATE.OPEN, budget.get().descriptor().state);

    }

    @Test
    public void testPlanMaterial() throws Exception {

        final String materialID = "M00001";
        final String[] languages = {"ES"};
        final int units = 5;

        final PlanMaterialCommand command = new PlanMaterialCommand(OWNER_ID, BUDGET_ID, materialID, units, languages);
        budgetService.planMaterial(command);

        // assertions
        final Optional<MaterialsBudget> budget = BUDGETS.findByID(BUDGET_ID);
        assertTrue(budget.isPresent());
        assertEquals(1, budget.get().allItems().size());
        // TODO: add assertions for budget item state

    }

    @Test
    public void testImplementBudget() throws Exception {

        final ImplementBudgetCommand command = new ImplementBudgetCommand(OWNER_ID, BUDGET_ID);
        budgetService.implementBudget(command);

        // assertions
        Optional<MaterialsBudget> budget = BUDGETS.findByID(BUDGET_ID);
        assertTrue(budget.isPresent());
        assertEquals(Budget.STATE.CLOSED, budget.get().descriptor().state);

    }
}
