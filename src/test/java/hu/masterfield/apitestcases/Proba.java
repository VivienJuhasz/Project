package hu.masterfield.apitestcases;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class Proba extends BaseAPITest{

    @Test
    public void TC_Proba() {

        ValidatableResponse response = given()
                .header(AUTH_HEADER, "Bearer " + authToken)
                .contentType(JSON)
                .when()
                .get("/api/v1/user")
                .then()
                .statusCode(200)
                .body("username", equalTo("22admin@demo.io"));

                // response.prettyPrint();

    }
}