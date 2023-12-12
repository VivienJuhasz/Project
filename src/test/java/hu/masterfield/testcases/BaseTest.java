package hu.masterfield.testcases;

import hu.masterfield.browser.WebBrowser;
import hu.masterfield.browser.WebBrowserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * A teszt osztályok mindegyike ebből fogja örökölni az általánosan elvárt, minden osztályban
 * használt metódusokat és JUnit annotációkat**/
public class BaseTest implements TestWatcher {
    protected static WebDriver driver = null;
    protected static WebDriverWait wait = null;

    protected static Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeAll
    public static void setup() {
        logger.info("BaseTest.setup called.");

        driver = WebBrowser.createDriver(WebBrowserType.Chrome);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        logger.info("BaseTest.setup WebDriver started.");

    }
}
