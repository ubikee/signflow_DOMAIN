package es.elcorteingles.signflow.subcampaign.domain.command;

public class CreateSubcampaignCommand {

    public final String name;
    public final String owner;
    
    public CreateSubcampaignCommand(final String name, final String owner) {
        this.name = name;
        this.owner = owner;
    }
}
