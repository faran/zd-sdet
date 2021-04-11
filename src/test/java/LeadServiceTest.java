import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class LeadServiceTest {

    LeadsService leadsService = new LeadsService();

    private final Logger log = LogManager.getLogger(this.getClass().getName());

    private Integer leadId;

    public Integer getLeadId() {
        return leadId;
    }

    public void setLeadId(Integer leadId) {
        this.leadId = leadId;
    }

    @Test(priority = 1)
    public void testCreateNewLead() {
        Response response = leadsService.createNewLead();
        response.prettyPrint();
        Map<String, Integer> data = response.jsonPath().getMap("data");
        Integer id = data.get("id");
        log.info("Created new ID: "+ id);
        setLeadId(id);
    }

    @Test(priority = 2)
    public void testCheckleadExists(){
       Response response = leadsService.checkLeadExists(getLeadId());
       response.prettyPrint();
       Map<String, Integer> data = response.jsonPath().getMap("data");
       Integer id = data.get("id");
       log.info("Newly created lead exists with this ID: "+ getLeadId());
       Assert.assertEquals(id, getLeadId());
    }

    @Test(priority = 3)
    public void testAddressIsNull(){
        Response response = leadsService.checkLeadAddress(getLeadId());
        response.prettyPrint();
        Map<String, String> address = response.jsonPath().getMap("data.address");
        String line1 = address.get("line1");
        log.info("Getting address for this ID: "+ getLeadId() + " line1 of address is: "+ line1);
        Assert.assertNull(line1);
    }

    @Test(priority = 4)
    public void testUpdateLeadAddress(){
        Response response = leadsService.updateLeadAddress(getLeadId());
        response.prettyPrint();
        Map<String, String> address = response.jsonPath().getMap("data.address");
        String line1 = address.get("line1");
        log.info("Updated address for this ID: "+ getLeadId() + " address line1 is: "+line1);
        Assert.assertEquals(line1, "2726 Smith Street");
    }

    @Test(priority = 5)
    public void testGetAllLeads(){
        Response response = leadsService.getAllLeads();
        response.prettyPrint();
        List<Map<Object, Integer>> totalLeads = response.jsonPath().getList("items");
        Integer totalNumberOfLeads = totalLeads.size();

        Map<String, Integer> meta = response.jsonPath().getMap("meta");
        Integer count = meta.get("count");
        log.info("Total number of Leads: "+ totalNumberOfLeads);
        Assert.assertEquals(totalNumberOfLeads, count);
    }
}
