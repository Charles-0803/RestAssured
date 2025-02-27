import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("User Management API")  // High-level category
@Feature("Delete User")  // Specific feature under the epic
public class DeleteRequestTest {

    @Test
    @Story("Delete an existing user")  // Relates to a user story
    @Severity(SeverityLevel.CRITICAL)  // Marks the severity of the test
    @Description("This test verifies that a DELETE request successfully removes a user and returns status code 204.")
    @TmsLink("TMS-78902")  // Link to Test Management System
    @Issue("JIRA-7891")  // Links to a bug tracking system if applicable
    @Link(name = "API Docs", url = "https://reqres.in/")
    public void deleteRequest() {
        Response response = when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204)
                .extract().response();

        attachResponse("Status Code: " + response.statusCode());

        assertEquals(204, response.statusCode(), "Expected status code is 204 after deletion");
    }

    @Attachment(value = "Response Details", type = "text/plain")
    public String attachResponse(String response) {
        return response;
    }
}
