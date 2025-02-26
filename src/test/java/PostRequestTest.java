import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostRequestTest {
    @Test
    public void createUser(){
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

        assertEquals(201, response.statusCode(), "Status code should be 201");
        assertTrue(response.asString().contains("Alice Johnson"),"Response should contain 'Alice Johnson'");
    }
}
