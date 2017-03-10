package es.elcorteingles.signflow.domain.materials;

import es.elcorteingles.signflow.domain.Repository;
import java.util.Optional;
import java.util.UUID;

public class MaterialRepository implements Repository<Material> {

    @Override
    public UUID nextID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<Material> findByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
