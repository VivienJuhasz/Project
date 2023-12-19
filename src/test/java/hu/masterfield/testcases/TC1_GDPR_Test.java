package hu.masterfield.testcases;

import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.utils.Screenshot;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TC1 - GDPR nyilatkozat sikeres elfogadása
 */
public class TC1_GDPR_Test extends BaseTest{
    protected static Logger logger = LogManager.getLogger(TC1_GDPR_Test.class);

    @Test
    @DisplayName("TC1_GDPR_Test")
    @Description("TC1 - GDPR nyilatkozat sikeres elfogadása")
    @Tag("TC1")
    @Tag("GDPR")
    public void test_TC1_GDPR(TestInfo testInfo) throws IOException, InterruptedException {
        logger.info(testInfo + " started");
        //Thread.sleep(5000);
        GDPRBannerPage gdprPage = new GDPRBannerPage(driver);

        /* A süti elfogadására szolgáló ablak megjelenítésének ellenőrzése */
        assertTrue(gdprPage.isCookieMessageVisible());
        Screenshot.takesScreenshot(driver);
        gdprPage.acceptCookie();
        Screenshot.takesScreenshot(driver);
        logger.info("Login page will be opened.");
        logger.info("Login");
        //Thread.sleep(5000);
    }
}
