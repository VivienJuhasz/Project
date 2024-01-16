package hu.masterfield.pages;

import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Feature("Regisztrációs form második oldalának kezelése")
public class RegistrationSecondPage extends BasePage {
    // Az oldalon található webelementek azonosítása, amelyekre szükségünk van.

    // cím megadása
    @FindBy(id="address")
    private WebElement addressInput;

    // helység megadása
    @FindBy(id="locality")
    private WebElement localityInput;

    // régió megadása
    @FindBy(id="region")
    private WebElement regionInput;

    // irányítószám megadása
    @FindBy(id="postalCode")
    private WebElement postalCodeInput;

    // ország megadása
    @FindBy(id="country")
    private WebElement countryInput;

    // otthoni telefonszám megadása
    @FindBy(id="homePhone")
    private WebElement homePhoneInput;

    // mobil telefonszám megadása
    @FindBy(id="mobilePhone")
    private WebElement mobilePhoneInput;

    // munka telefonszám megadása
    @FindBy(id="workPhone")
    private WebElement workPhoneInput;
    public RegistrationSecondPage(WebDriver driver) {
        super(driver);
    }
}
