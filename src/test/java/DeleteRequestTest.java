import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteRequestTest {
    @Test
    public void deleteRequest() {
        Response response = when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204)
                .extract().response();
        assertEquals(204, response.statusCode(), "Expected status code is 204 after deletion");
    }
}
