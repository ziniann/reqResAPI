package endpoints;

import apiBase.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.TmsLink;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.User;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class UsersTest extends BaseTest {

    String path = "/api/users";

    @Test(description = "Getting the Users")
    @TmsLink("01")
    public void testListUsers() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(path + "?page=2")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/usersList.json"))
                .extract().response();
    }

    @Test(description = "Getting a single user")
    @TmsLink("02")
    public void testSingleUser() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(path + "/2")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/user.json"))
                .extract().response();
    }

    @Test(description = "Creating the User")
    @TmsLink("03")
    public void testCreateUser() throws Exception {
        SoftAssert softAssert = new SoftAssert();
        User newUser = new User("Zina", "QE");
        ObjectMapper mapper = new ObjectMapper();

        JsonPath response = given()
                .contentType(ContentType.JSON)
                .body(mapper.writeValueAsString(newUser))
                .when()
                .post(path)
                .then()
                .statusCode(201)
                .extract().response()
                .jsonPath();

        softAssert.assertEquals(response.getString("name"), newUser.getName(), "Name should match the input!");
        softAssert.assertEquals(response.getString("job"), newUser.getJob(), "Job should match the input!");
        softAssert.assertNotNull(response.getString("id"), "id should not be null!");
        softAssert.assertNotNull(response.getString("createdAt"), "createdAt should not be null!");

        softAssert.assertAll();
    }

}
