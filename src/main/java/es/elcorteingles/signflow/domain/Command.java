package es.elcorteingles.signflow.domain;

import java.time.LocalDateTime;

/**
 *
 * @author jeroldan
 */
public abstract class Command {
    
    public final String ownerID;
    public final LocalDateTime date;
    
    public Command(String ownerID) {
        this.date = LocalDateTime.now();
        this.ownerID = ownerID;
    }
}
