package es.elcorteingles.signflow.domain.art;

import es.elcorteingles.signflow.domain.Entity;
import es.elcorteingles.signflow.domain.art.event.ArtCreated;

/**
 *
 * @author jeroldan
 */
public class Art extends Entity {

    private String materialID;
    private String motif;
    private String status;
    private String id;
    
    Art() {}
    
    public Art(final String id, final String materialID, final String motif) {
        this.id = id;
        this.materialID = materialID;
        this.motif = motif;
        this.status = "OPEN";
        
        emit(new ArtCreated(this.id, this.materialID, this.motif));
    }
}
