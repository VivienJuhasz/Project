package hu.masterfield.pages;

import hu.masterfield.datatypes.Saving;
import hu.masterfield.utils.Consts;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Megtakarítás típusú accountok létrehozásáért felelős osztály
 */

@Feature("Megtakarítás típusú accountok létrehozásáért felelős osztály")
public class CreateSavingsPage extends BasePage {

    protected static Logger logger = LogManager.getLogger(CreateSavingsPage.class);

    // Az oldalon található webelementek azonosítása.
    // Ezek szükségesek a Saving accountok létrehozásához-

    // Saving radio button
    @FindBy(id = Consts.ACCOUNT_TYPES_SAVINGS)
    private WebElement radioSavings;

    // Money Market radio button
    @FindBy(id = Consts.ACCOUNT_TYPES_MONEY_MARKET)
    private WebElement radioMoneyMarket;

    // Individual radio button
    @FindBy(id = Consts.OWNERSHIP_TYPES_INDIVIDUAL)
    private WebElement radioIndividual;

    // Joint radio button
    @FindBy(id = Consts.OWNERSHIP_TYPES_JOINT)
    private WebElement radioJoint;

    // account name
    @FindBy(id = "name")
    private WebElement textName;

    // opening balance
    @FindBy(id = "openingBalance")
    private WebElement textOpeningBalance;

    // new Saving submit
    @FindBy(id = "newSavingsSubmit")
    private WebElement buttonNewSavingsSubmit;

    public CreateSavingsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Új Saving létrehozása
     *
     * @return ViewSavingsAccountPage, ha sikerült létrehozni a SAving típusú objektumot
     */
    @Step("Új Saving létrehozása")
    public ViewSavingsAccountsPage creatNewSavings(Saving saving) {
        logger.info("creatNewSaving() called.");

        if (saving.getAccountTypes().equals(Consts.ACCOUNT_TYPES_SAVINGS)) {
            //RadioButton esetében ez az ellenőrzés nem szükséges.
            if (radioSavings.isSelected()) {
                //To Do Nothing
            } else {
                radioSavings.click();
            }
        }
        if (saving.getAccountTypes().equals(Consts.ACCOUNT_TYPES_MONEY_MARKET)) {
            radioMoneyMarket.click();
        }
        if (saving.getOwnershipTypes().equals(Consts.OWNERSHIP_TYPES_INDIVIDUAL)) {
            radioMoneyMarket.click();
        }
        if (saving.getOwnershipTypes().equals(Consts.OWNERSHIP_TYPES_JOINT)) {
            radioMoneyMarket.click();
        }

        //textName.sendKeys(saving.getAccountName());
        setTextbox(textName, "Account Name", saving.getAccountName());

        setTextbox(textOpeningBalance, "Opening Balance", saving.getOpeningBalance());

        takeScreenshot();

        buttonNewSavingsSubmit.click();

        return new ViewSavingsAccountsPage(driver);
    }
}