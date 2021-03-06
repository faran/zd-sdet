import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseService {

    private String basePath = null;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getPAT() {
        return "";
    }

    public RequestSpecification requestSpec() {

        return new RequestSpecBuilder()
            .addHeader("Accept", "application/json")
            .setUrlEncodingEnabled(false)
            .setBaseUri("https://api.getbase.com/v2/")
            .setBasePath(getBasePath())
            .build()
            .given()
            .auth()
            .preemptive()
            .oauth2(getPAT())
            .header("Content-Type", "application/json");
    }

    public ResponseSpecification responseSpec() {

        return new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();
    }
}