import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class LeadsService extends BaseService {

    LeadsService() {
        setBasePath("leads");
    }

    File newLeadJson = new File("src/main/resources/newlead.json");
    File addressJson = new File("src/main/resources/address.json");

    public Response createNewLead() {
        RequestSpecification request = given()
                .spec(requestSpec()).log().all()
                .body(newLeadJson);

        return request.post()
                .then()
                .log()
                .ifStatusCodeMatches(anyOf(is(304), is(422), is(503)))
                .spec(responseSpec())
                .extract()
                .response();
    }

    public Response updateLeadAddress(Integer id) {
        RequestSpecification request = given()
                .spec(requestSpec()).log().all()
                .pathParam("leadId", id)
                .body(addressJson);

        return request.put("/{leadId}")
                .then()
                .log()
                .ifStatusCodeMatches(anyOf(is(304), is(422), is(503)))
                .spec(responseSpec())
                .extract()
                .response();
    }

    public Response checkLeadExists(Integer id) {
        RequestSpecification request = given()
                .spec(requestSpec()).log().all()
                .pathParam("leadId", id);

        return request.get("/{leadId}")
                .then()
                .log()
                .ifStatusCodeMatches(anyOf(is(304), is(422), is(503)))
                .spec(responseSpec())
                .extract()
                .response();
    }

    public Response checkLeadAddress(Integer id) {
        RequestSpecification request = given()
                .spec(requestSpec()).log().all()
                .pathParam("leadId", id);

        return request.get("/{leadId}")
                .then()
                .log()
                .ifStatusCodeMatches(anyOf(is(304), is(422), is(503)))
                .spec(responseSpec())
                .extract()
                .response();
    }

    public Response getAllLeads() {
        RequestSpecification request = given()
                .spec(requestSpec()).log().all();

        return request.get()
                .then()
                .log()
                .ifStatusCodeMatches(anyOf(is(304), is(422), is(503)))
                .spec(responseSpec())
                .extract()
                .response();
    }

}
