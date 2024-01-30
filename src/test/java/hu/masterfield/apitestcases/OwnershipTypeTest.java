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

public class OwnershipTypeTest extends BaseAPITest{

    /* OwnershipType ellenőrzése */
    protected static Logger logger = LogManager.getLogger(OwnershipTypeTest.class);

    String expectedResult = "[\n" +
            "    {\n" +
            "        \"id\": 17,\n" +
            "        \"code\": \"IND\",\n" +
            "        \"name\": \"Individual\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 18,\n" +
            "        \"code\": \"JNT\",\n" +
            "        \"name\": \"Joint\"\n" +
            "    }\n" +
            "]";

    @Test
    public void testOwnerShipType() {
        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .when()
                .get("/api/v1/data/account/ownership/type");
        logger.trace(response.prettyPrint());
        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("[0].id", is(17))
                .body("[0].code", equalTo("IND"))
                .body("[0].name", equalTo("Individual"))
                .body("[1].id", is(18))
                .body("[1].code", equalTo("JNT"))
                .body("[1].name", equalTo("Joint"));

        assertEquals(expectedResult, response.prettyPrint());
    }
}
