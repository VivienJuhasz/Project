package hu.masterfield.pages;

import hu.masterfield.datatypes.RegistrationData;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Feature("Regisztrációs form második oldalának kezelése")
public class RegistrationSecondPage extends BasePage {
    // Az oldalon található webelementek azonosítása, amelyekre szükségünk van.

    protected static Logger logger = LogManager.getLogger(RegistrationSecondPage.class);

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

    // feltételek elfogadása
    @FindBy(id="agree-terms")
    private WebElement agreeTermsCheckBox;

    // regisztrációs gomb
    @FindBy(xpath = "//button[@type='submit' and text()='Register']")
    //@FindBy(css = "button[type='submit']")
    private WebElement registrationButton;
    public RegistrationSecondPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Ellenőrzi, hogy megjelentek-e az oldalon a megadott elemek
     * @return true, ha az oldal betöltődött, megjelenltek az elemek és kattinthatóak.
     */
    @Step("Regisztrációs ürlap második oldalának betöltésének ellenőrzése")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(addressInput)
                && isLoaded(localityInput)
                && isLoaded(regionInput)
                && isLoaded(postalCodeInput)
                && isLoaded(countryInput)
                && isLoaded(homePhoneInput)
                && isLoaded(mobilePhoneInput)
                && isLoaded(workPhoneInput)
                && isLoaded(agreeTermsCheckBox)
                && isLoaded(registrationButton);

        logger.trace("isLaded= " +isLoaded);
        return isLoaded;
    }

    /**
     *  Példányosítjuk a RegistrationData osztályt, hogy az oldalon található input mezőket
     *  a GlobalTestData.properties fileban megadott adatokkal tudjuk kitölteni.
     *  Így a regisztráció 2. oldalának kitöltésekor nem kell felsorolni a bemenő paramétereket
     */
    RegistrationData registrationData = new RegistrationData();

    /**
     * A regisztráció második oldalának kitöltését valósítjuk meg.
     */
    @Step("A regisztrációs második oldalának kitöltése.")
    public LoginPage registrationSecondPage() {
        logger.info("registrationSecondPage() called.");

        setTextbox(addressInput, "addressInput", registrationData.getAddress());
        setTextbox(localityInput, "localityInput", registrationData.getLocality());
        setTextbox(regionInput, "regionInput", registrationData.getRegion());
        setTextbox(postalCodeInput, "postalCodeInput", registrationData.getPostalCode());
        setTextbox(countryInput, "countryInput", registrationData.getCountry());
        setTextbox(homePhoneInput, "homePhoneInput", registrationData.getHomePhone());
        setTextbox(mobilePhoneInput, "mobilePhoneInput", registrationData.getMobilePhone());
        setTextbox(workPhoneInput, "workPhoneInput", registrationData.getWorkPhone());

        logger.trace("agreeTermsCheckBox.click");
        if (agreeTermsCheckBox.isSelected()) {
            // TO DO NOTHING
        } else {
            agreeTermsCheckBox.click();
        }

        takeScreenshot();

        logger.trace("registrationButton.click");
        registrationButton.click();

        return new LoginPage(driver);
    }
}
