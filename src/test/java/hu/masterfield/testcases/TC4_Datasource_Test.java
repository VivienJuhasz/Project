package hu.masterfield.testcases;

import hu.masterfield.datatypes.Saving;
import hu.masterfield.pages.*;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.DataSource;
import hu.masterfield.utils.GlobalTestData;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * "Savings" típusú számlák sorozatos létrehozása külső adatforrásból
 */
public class TC4_Datasource_Test extends BaseTest{

    protected static Logger logger = LogManager.getLogger(TC3_Login_Test.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();
    @Test
    @DisplayName("TC4_Datasource")
    @Description("TC4 - \"Sikeres\" típusú számlák sorozatos létrehozása külső adatforrásból")
    @Tag("TC4")
    @Tag("Sorozatos")
    @Tag("Adatbeviteli")
    @Tag("Adatforrás")
    public void TC4_Datasource(TestInfo testInfo) {

        logger.info(testInfo.getDisplayName() + "started.");

        //CSV file betöltése
        List<Saving> savings = DataSource.loadSaving();

        //Cookie törlése
        GDPRBannerPage gdprBannerPage = new GDPRBannerPage(driver);
        gdprBannerPage.acceptCookies();

        // Login megvalósítása
        String emailAddress = globalTestData.getProperty(Consts.REG_EMAIL_ADDRESS);
        String password = globalTestData.getProperty(Consts.REG_PASSWORD);

        logger.info("login");

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoaded());

        HomePage homePage = loginPage.login(emailAddress, password);
        assertTrue(homePage.isLoaded());
        homePage.validateHomePage();

        // Végigmegyünk a CSV fileból betöltött Saving tipusú elemeken
        // és mindegyikre létrehozunk az oldalon keresztül egy-egy Savinget.
        // A végén visszaellenőrizzük, hogy a View Savings oldalon helyesen jellenek-e meg a Savingek.

        for(Saving saving : savings) {
            logger.trace(saving);

            CreateSavingsPage createSavingsPage = homePage.gotoNewSavingsPage();

            logger.info("Create new Saving.");

            ViewSavingsAccountsPage viewSavingsAccountsPage = createSavingsPage.createNewSavings(saving);

            viewSavingsAccountsPage.validatePage(saving);
        }
    }
}
