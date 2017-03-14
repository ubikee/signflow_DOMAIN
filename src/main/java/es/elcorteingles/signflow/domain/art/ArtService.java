package es.elcorteingles.signflow.domain.art;

import es.elcorteingles.signflow.domain.art.command.NewBudgetNotification;

public class ArtService {

    private final ArtRepository artRepository;
    
    public ArtService(final ArtRepository artRepository) {
        this.artRepository = artRepository;
    }
    
    public void newBudgetNotification(final NewBudgetNotification command) {
        
    }
}
