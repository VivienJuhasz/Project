package hu.masterfield.pages;

import hu.masterfield.datatypes.RegistrationData;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * Regisztrációs form első oldalának osztálya
 */

@Feature("Regisztrációs form első oldalának kezelése")
public class RegistrationFirstPage extends BasePage {

    // Az oldalon található webelementek azonosítása, amelyekre szükségünk van.

    // megszólítás megadása
    @FindBy(id = "title")
    private WebElement titleSelect;

    // keresztnév megadása
    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    // vezetéknév megadása
    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    // férfi nem kiválasztása
    @FindBy(xpath = "//input[@type='radio' and @name='gender' and @value='M']")
    private WebElement genderMaleRadio;

    // női nem kiválasztása
    @FindBy(css = "input[type='radio'][name='gender'][value='F']")
    private WebElement genderFemaleRadio;

    // születési dátum
    @FindBy(id = "dob")
    private WebElement dateOfBirthInput;

    // TB szám
    @FindBy(id = "ssn")
    private WebElement socialSecurityNumberInput;

    // email cím
    @FindBy(id = "emailAddress")
    private WebElement emailAddressInput;

    // jelszó megadása
    @FindBy(id = "password")
    private WebElement regPasswordInput;

    // jelszó megerősítése
    @FindBy(id = "confirmPassword")
    private WebElement regConfirmPasswordInput;

    // tovább gomb
    @FindBy(xpath = "//button[@type='submit' and text()='Next']")
    private WebElement nextPageButton;

    public RegistrationFirstPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Ellenőrizzük, hogy megjelentek-e az oldalon az adott elemek.
     *
     * @return true , ha az oldal betöltődött, megjelentek az elvárt elemek és kattinthatóak
     */
    @Step("Regisztrációs ürlap első oldalának betöltésének ellenőrzése.")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(titleSelect)
                && isLoaded(firstNameInput)
                && isLoaded(lastNameInput)
                && isLoaded(genderMaleRadio)
                && isLoaded(genderFemaleRadio)
                && isLoaded(dateOfBirthInput)
                && isLoaded(socialSecurityNumberInput)
                && isLoaded(emailAddressInput)
                && isLoaded(regPasswordInput)
                && isLoaded(regConfirmPasswordInput)
                && isLoaded(nextPageButton);
        logger.trace("RegistrationPage isLoaded= " + isLoaded);
        return isLoaded;
    }

    /**
     * Példányosítjuk a RegistrationData osztályt, hogy az oldalon található input mezőket
     * ki tudjuk tölteni a globalTestData.properties fileból felolvasott tesztadatokkal.
     */

    RegistrationData registrationData = new RegistrationData();

    /**
     * A regisztráció első oldalát valósítjuk meg.
     */
    @Step("Regisztrációs ürlap első oldalának kitöltése.")
    public RegistrationSecondPage registrationFirstPage() {
        logger.info("registrationFirstPage() called.");

        logger.trace("titleSelect.select");
        Select selectTitle = new Select(titleSelect);
        selectTitle.selectByVisibleText(registrationData.getTitle());

        setTextbox(firstNameInput, "firstNameInput", registrationData.getFirstName());

        setTextbox(lastNameInput, "lastNameInput", registrationData.getLastName());

        if (registrationData.getGender().equals("M")) {
            if (genderMaleRadio.isSelected()) {
                // TO DO NOTHING
            } else {
                logger.trace("genderMaleRadio.click() called.");
                genderMaleRadio.click();
            }
        }
        if (registrationData.getGender().equals("F")) {
            if (genderFemaleRadio.isSelected()) {
                // TO DO NOTHING
            } else {
                logger.trace("genderFemaleRadio.click() called.");
                genderFemaleRadio.click();
            }
        }

        setTextbox(dateOfBirthInput, "dateOfBirthInput", registrationData.getDateOfBirth());

        setTextbox(socialSecurityNumberInput, "socialSecurityNumberInput", registrationData.getSocialSecurityNumber());

        setTextbox(emailAddressInput, "emailAddressInput", registrationData.getEmailAddress());

        setTextbox(regPasswordInput, "regPasswordInput", registrationData.getPassword());

        setTextbox(regConfirmPasswordInput, "regConfirmPasswordInput", registrationData.getConfirmPassword());

        takeScreenshot();

        logger.trace("nextPageButton.click() called.");
        nextPageButton.click();

        return new RegistrationSecondPage(driver);
    }
}
