package hu.masterfield.apitestcases;

import hu.masterfield.utils.ConfigData;
import hu.masterfield.utils.GlobalTestData;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class BaseAPITest {

    protected static final String AUTH_HEADER = "Authorization";

    private static ConfigData configData = new ConfigData();
    protected GlobalTestData globalTestData = new GlobalTestData();

    protected static Logger logger = LogManager.getLogger(BaseAPITest.class);

    protected static String authToken;

    @BeforeAll
    public static void setup() {
        // Beállítjuk az alap URL-t
        RestAssured.baseURI = "https://185.199.31.30:8443/bank";

        // Azt adjuk meg, hogy minden hostban bízzon meg, attól függetlenül, hogy esetleg az
        // SSL tanúsítvány érvénytelen, lejárt
        // SSL certification kell a HTTPS-hez
        RestAssured.config = RestAssuredConfig.newConfig().sslConfig(new SSLConfig()
                .relaxedHTTPSValidation());

        Response response = given().contentType(JSON)
                .queryParam("username", "admin@demo.io")
                .queryParam("password", "Demo123!")
                .when()
                .post("/api/v1/auth");

        response.prettyPrint();

        authToken = response.jsonPath().get("authToken").toString();
    }
}