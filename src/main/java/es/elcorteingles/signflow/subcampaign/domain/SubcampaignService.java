package es.elcorteingles.signflow.subcampaign.domain;

import es.elcorteingles.signflow.subcampaign.domain.command.CreateSubcampaignCommand;
import java.util.UUID;

public final class SubcampaignService {

    final SubcampaignRepository subcampaigns;
    
    public SubcampaignService(SubcampaignRepository subcampaigns) {
        this.subcampaigns = subcampaigns;
    }
    
    // Unit Of Work
    public String createSubcampaign(CreateSubcampaignCommand command) {
        return this.newSubcampaignWith(command.name, command.owner);
    }

    private String newSubcampaignWith(String name, String owner) {
        UUID id = subcampaigns.nextID();
        SubCampaign subcampaign = new SubCampaign(id, name, owner);
        return subcampaign.descriptor().id.toString();
    }
            
}
