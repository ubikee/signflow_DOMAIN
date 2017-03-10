package es.elcorteingles.signflow.domain.subcampaign;

import es.elcorteingles.signflow.Application;
import static es.elcorteingles.signflow.Application.*;
import es.elcorteingles.signflow.domain.Event;
import es.elcorteingles.signflow.domain.subcampaign.command.CreateSubcampaignCommand;
import es.elcorteingles.signflow.domain.subcampaign.event.SubcampaignCreated;

import java.util.Optional;
import java.util.Queue;

import static org.junit.Assert.*;
import org.junit.Test;

public class SubcampaignServiceTest {

    SubcampaignService subcampaignService = Application.subcampaingService();

    @Test
    public void testNewSubcampaign() throws Exception {

        final String campaignName = "Black Friday Lona";
        final String ownerID = "U0001";

        final CreateSubcampaignCommand newSubcampaignCommand = new CreateSubcampaignCommand(campaignName, ownerID);
        final String newSubcampaignID = subcampaignService.createSubcampaign(newSubcampaignCommand);

        // check subcampaign exist  
        final Optional<SubCampaign> subcampaign = SUBCAMPAIGNS.findByID(newSubcampaignID);
        assertTrue(subcampaign.isPresent());
        
        // check subcampaign data is correct
        final SubCampaign.SubCampaignDescriptor descriptor = subcampaign.get().descriptor();
        assertEquals(newSubcampaignID, descriptor.id.toString());
        assertEquals(campaignName, descriptor.name);
        assertEquals(ownerID, descriptor.ownerID);

        // check event is fired
        final Queue<Event> queue = EVENTSTORE.get(newSubcampaignID);
        final boolean anyMatch = queue.stream().anyMatch((Event event) -> {
            System.out.println(event);
            return SubcampaignCreated.TYPE.equals(event.type());
        });
        assertTrue(anyMatch);

    }

}
