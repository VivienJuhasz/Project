package hu.masterfield.pages;

import hu.masterfield.utils.Consts;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Megtakarítás típusú accountok létrehozásáért felelős osztály
 */

@Feature("Megtakarítás típusú accountok létrehozásáért felelős osztály")
public class CreateSavingsPage extends BasePage{

    // Az oldalon található webelementek azonosítása.
    // Ezek szükségesek a Savings accountok létrehozásához-

    // Savings radio button
    @FindBy(id= Consts.ACCOUNT_TYPES_SAVINGS)
    private WebElement radioSavings;

    // Money Market radio button
    @FindBy(id=Consts.ACCOUNT_TYPES_MONEY_MARKET)
    private WebElement radioMoneyMarket;

    public CreateSavingsPage(WebDriver driver) {
        super(driver);
    }
}