package endpoints;

import apiBase.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class LoginTest extends BaseTest {

    String path = "/api/login";

    @Test(description = "Logging in successfully")
    public void testLoginSuccessful() {
        String loginPayload = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(loginPayload)
                .when()
                .post(path)
                .then()
                .statusCode(200)
                .extract().response();

        assertNotNull(response.jsonPath().getString("token"));
    }

    @Test(description = "Logging in unsuccessful")
    public void testLoginUnsuccessful() {
        String loginPayload = "{ \"email\": \"peter@klaven\" }";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(loginPayload)
                .when()
                .post(path)
                .then()
                .statusCode(400)
                .extract().response();

        assertEquals(response.jsonPath().getString("error"), "Missing password");
    }
}
