import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("User Management API")  // High-level category
@Feature("Update User")  // Specific feature under the epic
public class PutRequestTest {

    @Test
    @Story("Update an existing user's details")  // Relates to a user story
    @Severity(SeverityLevel.CRITICAL)  // Marks the severity of the test
    @Description("This test verifies that a PUT request updates an existing user's details successfully and returns status code 200.")
    @TmsLink("TMS-78901")  // Link to Test Management System
    @Issue("JIRA-7890")  // Links to a bug tracking system if applicable
    @Link(name = "API Docs", url = "https://reqres.in/")
    public void updateUser() {
        String updateBody = "{"
                + "\"name\": \"Alice Johnson\","
                + "\"job\": \"Senior QA Engineer\","
                + "\"email\": \"alice.updated@example.com\","
                + "\"age\": 29,"
                + "\"city\": \"San Francisco\""
                + "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(updateBody)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .body("name", equalTo("Alice Johnson"))
                .body("job", equalTo("Senior QA Engineer"))
                .body("email", equalTo("alice.updated@example.com"))
                .body("age", equalTo(29))
                .body("city", equalTo("San Francisco"))
                .extract().response();

        attachResponse(response.asString()); // Attaching response to Allure report

        assertEquals(200, response.statusCode(), "Expected status code is 200");
        assertTrue(response.asString().contains("Senior QA Engineer"), "Response should contain 'Senior QA Engineer'");
    }

    @Attachment(value = "Response Body", type = "application/json")
    public String attachResponse(String response) {
        return response;
    }
}
