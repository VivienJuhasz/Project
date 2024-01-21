package hu.masterfield.testcases;


import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.pages.HomePage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Sikeres bejelentkezés érvényes adatok megadásával
 */

public class TC3_Login_Test extends BaseTest {
    protected static Logger logger = LogManager.getLogger(TC3_Login_Test.class);

    @Test
    @DisplayName("TC3_Login")
    @Description("TC3 - Sikeres bejelentkezés regisztrált adatokkal")
    @Tag("TC3")
    @Tag("Login")
    public void TC3_LoginTest(TestInfo testInfo) throws InterruptedException {
        GlobalTestData globalTestData = new GlobalTestData();
        Thread.sleep(5000);
        logger.info(testInfo + " started.");

        GDPRBannerPage gdprPage = new GDPRBannerPage(driver);
        gdprPage.acceptCookies();

        logger.info("Login page betöltése");
        /* login */
        String emailAddress = globalTestData.getProperty(Consts.REG_EMAIL_ADDRESS);
        String password = globalTestData.getProperty(Consts.REG_PASSWORD);

        logger.info("Login");
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoaded());

        HomePage homePage = loginPage.login(emailAddress, password);
        assertTrue(homePage.isLoaded());
        homePage.validateHomePage();
    }
}
