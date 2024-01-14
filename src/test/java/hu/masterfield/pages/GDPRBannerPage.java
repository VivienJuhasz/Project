package hu.masterfield.pages;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * A Cookiek és GDPR elfogadásáért felelős POM osztály
 */
@Feature("Cookie-k elfogadása")
public class GDPRBannerPage extends BasePage{
    protected static Logger logger = LogManager.getLogger(GDPRBannerPage.class);

    //OK gomb
    @FindBy(xpath = "//button[@class='cc-nb-okagree' and text()='OK']")
    private WebElement okButton;


    //Az oldal sütiket használ szöveg

    //@FindBy (xpath = "//p[@class='cc-nb-title' and text()='Az oldal sütiket használ']")
    @FindBy (id = "cc-nb-title")
    private WebElement cookieUsageMessage;

    public GDPRBannerPage(WebDriver driver) {
        super(driver);
    }
    @Step("OK gomb megjelenésének ellenőrzése")
    public boolean isLoaded() {
        logger.info("Ok button loaded.");
        return isLoaded(okButton);
    }
    @Step("Cookie-k elfogadása")
    public void acceptCookies() {
        okButton.click();
        logger.info("Ok button clicked.");
    }
    @Step ("Cookie ablak megjelenésének ellenőrzése")
    public boolean isCookieMessageVisible() {
        logger.info("isCookieMessageVisible called...");
        boolean isCookieVisible = cookieUsageMessage.isDisplayed();

        logger.info("isCookieMessageVisible: " + isCookieVisible);
        logger.info("Title: " + cookieUsageMessage.getText());

        return isCookieVisible;
    }
}
