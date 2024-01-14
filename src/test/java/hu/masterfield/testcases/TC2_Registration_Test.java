package hu.masterfield.testcases;

import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.utils.Screenshot;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * TC2 - Sikeres regisztráció érvényes adatok megadásával.
 */
public class TC2_Registration_Test extends BaseTest {
    private static Logger logger = LogManager.getLogger(TC2_Registration_Test.class);

    @Test
    @DisplayName("TC2_Registration")
    @Description("TC2 - Sikeres regisztráció tesztelése érvényes adatokkal")
    @Tag("TC2")
    @Tag("Regisztráció")

    public void TC2_Registration_Test(TestInfo testInfo) {
        Thread.sleep(10000);
        logger.info(testInfo + " started");

        GDPRBannerPage gdprPage = new GDPRBannerPage(driver);

        /* a sütik elfogadására szolgáló ablak megjelenésének ellenőrzése */
        assertTrue(gdprPage.isCookieMessageVisible());
        Screenshot.takesScreenshot(driver);
        gdprPage.acceptCookies();
        Screenshot.takesScreenshot(driver);
        logger.info("Login page will be opened...");

        LoginPage loginPage = new LoginPage();
        assertTrue(loginPage.isLoaded());
        loginPage.registrationStart();
    }
}
