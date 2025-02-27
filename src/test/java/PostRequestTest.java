import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("User Management API")  // High-level category
@Feature("Create User")  // Specific feature under the epic
public class PostRequestTest {

    @Test
    @Story("Create a new user in the system")  // Relates to a user story
    @Severity(SeverityLevel.CRITICAL)  // Marks the severity of the test
    @Description("This test verifies that a POST request to create a new user returns status code 201 and the response contains correct user details.")
    @TmsLink("TMS-56789")  // Link to Test Management System
    @Issue("JIRA-45678")  // Links to a bug tracking system if applicable
    @Link(name = "API Docs", url = "https://reqres.in/")
    public void createUser() {
        String requestBody = "{"
                + "\"name\": \"Alice Johnson\","
                + "\"job\": \"QA Engineer\","
                + "\"email\": \"alice.johnson@example.com\","
                + "\"age\": 28,"
                + "\"city\": \"New York\""
                + "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Alice Johnson"))
                .body("job", equalTo("QA Engineer"))
                .body("email", equalTo("alice.johnson@example.com"))
                .body("age", equalTo(28))
                .body("city", equalTo("New York"))
                .extract().response();

        attachResponse(response.asString()); // Attaching response to Allure report

        assertEquals(201, response.statusCode(), "Status code should be 201");
        assertTrue(response.asString().contains("Alice Johnson"), "Response should contain 'Alice Johnson'");
    }

    @Attachment(value = "Response Body", type = "application/json")
    public String attachResponse(String response) {
        return response;
    }
}
