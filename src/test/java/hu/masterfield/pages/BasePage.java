package hu.masterfield.pages;

import hu.masterfield.utils.GlobalTestData;
import hu.masterfield.utils.Screenshot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * A Page POM osztályaink innen fogják örökölni az osztályban megvalósított metódusokat.
 * Deklarálja a WebDriver-t, a WebDriverWait-et, inicializálja az objektumokat, így ezeket nem kell külön megtennünk minden egyes osztályban.
 */
public class BasePage {
    protected static WebDriver driver = null;
    protected static WebDriverWait wait = null;
    protected static Logger logger = LogManager.getLogger(BasePage.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();
    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        BasePage.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    public boolean isLoaded (WebElement webElement) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
        } catch (TimeoutException ex) {
            fail(ex.getMessage());
            return false;
        }
    }
    public boolean isInteractable(WebElement webElement) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(webElement)).isEnabled();
        } catch (TimeoutException ex) {
            fail(ex.getMessage());
            return false;
        }
    }
    public static void takeScreenshot() {
        try {
            Screenshot.takesScreenshot(driver);
        } catch (IOException ex) {
            logger.warn("BasePage.takeScreenshot was thrown IOException: " + ex.getMessage() + " , " + ex.getStackTrace());
        }
    }
    public void setTextbox(WebElement webElement, String webElementName, String text) {
        if (webElement.getText().isEmpty()) {
            // TO DO NOTHING
        } else {
            try {
                webElement.clear();
                logger.trace(webElementName + ".clear() called");
            } catch (Exception ex) {
                logger.warn(webElementName + " textbox cannot clear.");
            }
        }
        webElement.sendKeys(text);
        logger.trace(webElementName + ".sendKeys() called");
    }
}
