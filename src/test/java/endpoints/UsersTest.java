package endpoints;

import apiBase.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import model.User;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.fail;

public class UsersTest extends BaseTest {

    String path = "/api/users/";
    int userId = 2;

    @Test(description = "Getting the Users")
    public void testListUsers() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(path + "?page=2")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/usersList.json"))
                .extract().response();
    }

    @Test(description = "Getting a single user")
    public void testSingleUser() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(path + userId)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/user.json"))
                .extract().response();
    }

    @Test(description = "Creating the User")
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

    @Test(description = "Deleting the User of given id")
    public void deleteUserTest() throws Exception {


        int statusCode = given()
                .when()
                .delete(path + userId)
                .then()
                .extract().response()
                .getStatusCode();

        if (statusCode == 204) {
            System.out.println("User deleted successfully.");
        } else if (statusCode == 404) {
            System.out.println("User not found.");
        } else {
            fail("Unexpected status code: " + statusCode);
        }
    }
}



