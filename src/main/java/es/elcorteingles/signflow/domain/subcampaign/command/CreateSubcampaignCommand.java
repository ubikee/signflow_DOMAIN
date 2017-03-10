package es.elcorteingles.signflow.domain.subcampaign.command;

public class CreateSubcampaignCommand {

    public final String name;
    public final String owner;
    
    public CreateSubcampaignCommand(final String name, final String owner) {
        this.name = name;
        this.owner = owner;
    }
}
