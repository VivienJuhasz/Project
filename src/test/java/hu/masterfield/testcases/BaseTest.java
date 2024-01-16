package hu.masterfield.testcases;

import hu.masterfield.browser.WebBrowser;
import hu.masterfield.browser.WebBrowserType;
import hu.masterfield.utils.ConfigData;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import hu.masterfield.utils.Screenshot;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

/**
 * A teszt osztályok mindegyike ebből fogja örökölni az általánosan elvárt, minden osztályban
 * használt metódusokat és JUnit annotációkat
 **/
public class BaseTest implements TestWatcher {
    protected static WebDriver driver = null;
    protected static WebDriverWait wait = null;
    private static ConfigData configData = new ConfigData();
    private static GlobalTestData globalTestData = new GlobalTestData();
    protected static Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeAll
    public static void setup() {
        logger.info("BaseTest.setup called.");

        driver = WebBrowser.createDriver(WebBrowserType.ChromeSM);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        logger.info("BaseTest.setup WebDriver started.");
        String baseURL = configData.getProperty(Consts.APPLICATION_URL);
        driver.get(baseURL);
        logger.info("BaseTest.setup " + baseURL + " opened.");
    }

    @AfterAll
    public static void cleanup() {
        logger.info("BaseTest.cleanup called.");

        //driver.quit();

        logger.info("BaseTest.cleanup WebDriver quit.");
    }

    public static void takeScreenshot() throws IOException {
        Screenshot.takesScreenshot(driver);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testFailed(context, cause);
        logger.error("Error occured! \n Context: " + context.getDisplayName() + "\n" + "Cause: " + cause.getCause().getMessage());
        logger.trace("Stack trace: " + cause.getStackTrace());
        try {
            takeScreenshot();
        } catch (IOException ex) {
        logger.warn("Exception when took screenshot: " + ex.getMessage());
        throw new RuntimeException();
        }
        Allure.addAttachment("Hiba történt!" , cause.getCause().getMessage());
    }
}
