import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("User Management API")  // High-level category
@Feature("Get Users")  // Specific feature under the epic
public class GetRequestTest {

    @Test
    @Story("Retrieve users from the API")  // Relates to a user story
    @Severity(SeverityLevel.NORMAL)  // Marks the severity of the test
    @Description("This test verifies that a GET request to fetch users returns a valid response with status code 200 and contains user email data.")
    @TmsLink("TMS-12345")  // Link to Test Management System
    @Issue("JIRA-6789")  // Links to a bug tracking system if applicable
    @Link(name = "API Docs", url = "https://reqres.in/")
    public void getUsers() {
        RestAssured.baseURI = "https://reqres.in/api";

        Response response = given()
                .when()
                .get("/users?page=2")
                .then()
                .statusCode(200)
                .body("data", not(empty()))
                .extract().response();

        attachResponse(response.asString()); // Attaching response to Allure report

        assertEquals(200, response.statusCode(), "Status code should be 200");
        assertTrue(response.asString().contains("email"), "Response should contain email");
    }

    @Attachment(value = "Response Body", type = "application/json")
    public String attachResponse(String response) {
        return response;
    }
}
