import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GetRequestTest {
    @Test
    public void getUsers(){
        RestAssured.baseURI = "https://reqres.in/api";

        Response response = given()
                .when()
                .get("/users?page=2")
                .then()
                .statusCode(200)
                .body("data", not(empty()))
                .extract().response();

        assertEquals(200,response.statusCode(), "Status code should be 200");
        assertTrue(response.asString().contains("email"),"Response should contain email");
    }
}
