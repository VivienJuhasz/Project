package hu.masterfield.apitestcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerGetUserTest extends BaseAPITest {

    /* Ellenőrzi a user-ünk adatait. */

    String expectedResult = "{\n" +
            "    \"id\": 4,\n" +
            "    \"username\": \"admin@demo.io\",\n" +
            "    \"enabled\": true,\n" +
            "    \"accountNonExpired\": true,\n" +
            "    \"accountNonLocked\": true,\n" +
            "    \"credentialsNonExpired\": true,\n" +
            "    \"userProfile\": {\n" +
            "        \"id\": 5,\n" +
            "        \"firstName\": \"Kisha\",\n" +
            "        \"lastName\": \"Abernathy\",\n" +
            "        \"title\": \"Mr.\",\n" +
            "        \"gender\": \"M\",\n" +
            "        \"ssn\": \"710-06-4454\",\n" +
            "        \"dob\": \"12/21/1969\",\n" +
            "        \"dom\": \"12/07/2023\",\n" +
            "        \"emailAddress\": \"admin@demo.io\",\n" +
            "        \"homePhone\": \"051-727-5095\",\n" +
            "        \"mobilePhone\": \"601-272-3887\",\n" +
            "        \"workPhone\": \"613-300-0377\",\n" +
            "        \"address\": \"80401 Yong Road\",\n" +
            "        \"locality\": \"Mickeystad\",\n" +
            "        \"region\": \"PA\",\n" +
            "        \"postalCode\": \"22103\",\n" +
            "        \"country\": \"US\"\n" +
            "    }\n" +
            "}";

    @Test
    public void testGetUser() {
        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .when()
                .get("/api/v1/user");
        logger.trace(response);
        response.prettyPrint();
        response.then()
                .statusCode(200)
                .body("id", is(4))
                .body("username", equalTo("admin@demo.io"))
                .body("userProfile.firstName", equalTo("Kisha"))
                .body("userProfile.lastName", equalTo("Abernathy"))
                .body("userProfile.emailAddress", equalTo("admin@demo.io"));

        assertEquals(expectedResult, response.prettyPrint());
    }
}
