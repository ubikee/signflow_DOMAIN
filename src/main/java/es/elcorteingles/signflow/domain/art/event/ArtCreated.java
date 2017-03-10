package es.elcorteingles.signflow.domain.art.event;

import es.elcorteingles.signflow.domain.Event;

public class ArtCreated extends Event {

    public final String TYPE="NEWARTCREATEDEVENT";
    public final String materialID;
    public final String motif;

    public ArtCreated(final String id, final String materialID, final String motif) {
        super(id);
        this.materialID = materialID;
        this.motif = motif;
    }

    @Override
    public String type() {
        return this.TYPE;
    }

}
