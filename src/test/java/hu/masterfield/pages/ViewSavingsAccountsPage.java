package hu.masterfield.pages;


import hu.masterfield.datatypes.Saving;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



/**
 *  A Savings típusú accountok megjelenítésének osztálya.
 *  Itt történik az adatforrásból létrehozott Saving típusok visszaellenőrzése létrehozás után
 */

@Feature("Megtakarítás típusú accountok megtekintéséért/ellenőrzéséért felelős osztály")
public class ViewSavingsAccountsPage extends BasePage {

    protected static Logger logger = LogManager.getLogger(ViewSavingsAccountsPage.class);

    // Az oldalon található webelementek azonosítása

    // accountokhoz tartozó kártyák (amik tartalmaznak minden infót az accountról)

    //@FindBy(xpath="//div[@id='firstRow']/div/div/form/div[@class='card-body']") -> Abszolut (gyökérelemtől leírom az utat) / jel használata
    //@FindBy(xpath="//div[@id='firstRow']//div[@class='card-body']") -> Relatív (hosszabb távon jobb, mint a fenti xpath)
    //@FindBy(css="div#firstRow div.card-body") -> Relatív (UGYAN AZ MINT AZ ALSÓ! #->ID helyettesíti, .->Class-t helyettesíti)
    //@FindBy(css="div[id='firstRow'] div[class='card-body']") -> Relatív (hosszabb távon jobb, de lassabb)
    //@FindBy(css="div#firstRow > div > div > form > div.card-body") -> Abszolut (gyökérelemtől leírom az utat) < használata

    @FindBy(css="div#firstRow div.card-body")
    private List<WebElement> cards;

    // Page title
    @FindBy(xpath="//h1[text()='View Savings Accounts']")
    private WebElement pageTitle;

    public ViewSavingsAccountsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Megjelenő Savingek ellenőrzése
     */
    @Step("Megjelenő Savingek ellenőrzése")
    public void validatePage(Saving expectedSaving) {
        logger.info("validatePage() called");

        takeScreenshot();

        WebElement account = findAccount(expectedSaving.getAccountName());
        assertNotNull(account, "Account with accountName="
                + expectedSaving.getAccountName() + " is not found!");

        WebElement divCartBody = account.findElement(By.xpath("./.."));
        List<WebElement> cardDivs = divCartBody.findElements(By.cssSelector("div > div"));
        Saving actualSaving = getSavingFromCard(cardDivs);
        assertEquals(expectedSaving, actualSaving);
    }

    /**
     * A felületen található összes Saving kártya átalakítása Java objektummá (Saving)
     * @return Saving objektumokat tartalmazó List-el
     */
    @Step("A felületen lévő összes Saving átalakítása Java objektummá")
    public List<Saving> getAllSavings() {
        logger.info("getAllSavings() called.");

        takeScreenshot();

        List<Saving> savingList = new ArrayList<>();
        for (WebElement card : cards) {
            List<WebElement> cardDivs = card.findElements(By.cssSelector("div"));
            savingList.add(getSavingFromCard(cardDivs));
        }
        return savingList;

    }




    private WebElement findAccount(String accountName) {
        for (WebElement card : cards) {
            //String labelAccountName = card.findElement(By.xpath("//div[@class='h4 m-0' and @contenteditable='true']"))
              //      .getText();
            String labelAccountName = card.findElements(By.tagName("div")).get(0)
                  .getText();
            if (labelAccountName.equals(accountName)) {
                return card;
            }
        }
        return null;
    }

    /**
     * Saving objektum létrehozása a kártyából
     * @param cardDivs kártya div elemei
     * @return Saving típusú objektum
     */
    private Saving getSavingFromCard(List<WebElement> cardDivs) {
        logger.info("getSavingFromCart() called.");

        String openingBalanceText = cardDivs.get(6).getText();
        Saving saving = new Saving(cardDivs.get(1).getText().substring(9),
                cardDivs.get(2).getText().substring(11),
                //cardDivs.get(2).getText().replace("Ownership: ", "")
                cardDivs.get(0).getText(),
                openingBalanceText.substring(10, openingBalanceText.lastIndexOf('.')));
        return saving;
    }

    /**
     * Ellenőrzi, hogy megjelentek-e az oldalon a megadott elemek.
     *
     * @return true az oldal betöltődött, megjelentek az elemek és kattinthatóak
     */
    @Step("ViewSavingsAccounts oldal betöltésének ellenőrzése")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(pageTitle) && isCardsLoaded(cards);

        logger.info("Page title: " + pageTitle.getText());
        logger.trace("isLoaded=" + isLoaded);
        return isLoaded;
    }

    public boolean isCardsLoaded(List<WebElement> cards) {
        boolean returnValue = false;
        for (WebElement card : cards) {
            if (isLoaded(card)) {
                returnValue = true;
            } else {
                return false;
            }
        }
        return returnValue;
    }
}

