
## EventStore

## Testing

### Event Based Fixture:

Para poner el sistema en un estado especifico solo tenemos que lanzar al bus los
eventos necesarios para recrearlo.
Cuando el repositorio reconstruya las entidades lo hara con estos eventos.

        @BeforeClass
        public void fixture() {
            BUS.emit(new BudgetCreated(BUDGET_ID, SUBCAMPAIGN_ID));
        }
