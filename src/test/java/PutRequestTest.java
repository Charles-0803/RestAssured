import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PutRequestTest {
    @Test
    public void updateUser(){
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

        assertEquals(200, response.statusCode(), "Expected status code is 200");
        assertTrue(response.asString().contains("Senior QA Engineer"), "Response should contain 'Senior QA Engineer'");
    }
}
