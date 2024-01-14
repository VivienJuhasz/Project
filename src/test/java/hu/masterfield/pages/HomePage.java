package hu.masterfield.pages;

import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Az oldalon található elemek azonosítása. Ezek szükségesek a HomePage-ről induló műveletekhez")
public class HomePage extends BasePage {

    /* Az oldalon található elemek azonosítása. Ezek szükségesek a HomePage-ről induló műveletekhez
     */

    protected static Logger logger = LogManager.getLogger(HomePage.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();

    // Checking menu
    @FindBy(id="checking-menu")
    private WebElement checkingMenu;

    // Checking menu -> View Checking
    @FindBy(id="view-checking-menu-item")
    private WebElement viewCheckingMenuItem;

    // Checking menu -> New Checking
    @FindBy(id="new-checking-menu-item")
    private WebElement newCheckingMenuItem;

    // Savings menu
    @FindBy(id="savings-menu")
    private WebElement savingsMenu;

    // Savings menu -> View Savings
    @FindBy(id="view-savings-menu-item")
    private WebElement viewSavingsMenuItem;

    // Savings menu -> New Savings
    @FindBy(id="new-savings-menu-item")
    private WebElement newSavingsMenuItem;

    // Welcome message
    @FindBy(xpath="//div[@class='page-title']//li")
    //@FindBy(css="div.page-title li")
    //@FindBy(css="div[class='page-title'] li")
    private WebElement labelTitle;

    // User avatar / picture
    @FindBy(css="img.user-avatar.rounded-circle")
    private WebElement avatarDropdownMenuButton;

    // Avatar lenyitása -> My Profile
    // @FindBy(css="a.nav-link[href="/bank/user/profile"]")
    @FindBy(xpath="//a[contains(text(),'My Profile')]")
    private WebElement avatarDropdownMyProfileLink;

    // Avatar lenyitása -> Change Password
    // @FindBy(css="a.nav-link[href="/bank/user/password"]")
    @FindBy(xpath="//a[contains(text(),'Change Password')]")
    private WebElement avatarDropdownChangePasswordLink;

    // Avatar lenyitása -> Create Data
    // @FindBy(css="a.nav-link[href="/bank/user/create-data"]")
    @FindBy(xpath="//a[contains(text(),'Create Data')]")
    private WebElement avatarDropDownCreateDataLink;

    // Avatar lenyitása -> Delete Data
    // @FindBy(css="a.nav-link[href="/bank/user/delete-data"]")
    @FindBy(xpath="//a[contains(text(),'Delete Data')]")
    private WebElement avatarDropdownDeleteDataLink;

    // Avatarr lenyitása -> Logout
    // @FindBy(css="a.nav-link[href="/bank/user/logout"]")
    @FindBy(xpath="//a[contains(text(),'Logout')]")
    private WebElement avatarDropdownLogoutLink;

    //TRANSACTION / TRANSFER -> Deposit
    @FindBy(id="deposit-menu-item")
    private WebElement depositLink;

    //TRANSACTION / TRANSFER -> Withdraw
    @FindBy(id="withdraw-menu-item")
    private WebElement withdrawLink;

    /**
     * A HomePage-ről elnavigál a Create Savings oldalra a menüben.
     * @return a megnyitott Create Savings oldal objektuma
     */
    @Step("Navigálás a Create Savings oldalra.")
    public CreateSavingsPage gotoNewSavingsPage() {
        logger.info("gotoNewSavingsPage() called.");

        logger.trace("savingsMenu.click()");
        savingsMenu.click();

        logger.trace("newSavingsMenuItem.click()");
        newSavingsMenuItem.click();

        takeScreenshot();

        return new CreateSavingsPage(driver);
    }

    /**
     *  HomePage-ről elnavigál a View Savings oldalra a menüben.
     * @return a megnyitott View Savings oldal objektuma
     */
    @Step("Navigálás a View Savings oldalra.")
    public ViewSavingsAccountsPage gotoViewSavingsPage() {
        logger.info("gotoViewSavingsPage() called.");

        logger.trace("savingMenu.click()");
        savingsMenu.click();

        logger.trace("viewSavingsMenuItem.click");
        viewSavingsMenuItem.click();

        takeScreenshot();

        return new ViewSavingsAccountsPage(driver);
    }

    /**
     * HomePage-ről elnavigál a Deposit oldalra a menüben.
     * @return a megnyitott Deposit oldal objektuma
     */
    @Step("Navigálás a Deposit oldalra.")
    public DepositPage gotoDepositPage() {
        logger.info("gotoDepositPage() called.");

        logger.trace("depositLink.click()");
        depositLink.click();

        takeScreenshot();

        return new DepositPage(driver);
    }

    /**
     * HomePage-ről elnavigál a Savings/Transactions oldalra a menüben.
     * @return Savings/Transactions oldal objektuma
     */

    @Step("Navigálás a 'View Savings' oldalon található tranzakciós táblázat oldalára.")
    public TransactionsPage gotoTransactionsPage() {
        logger.info("gotoTransactionsPage() called.");

        logger.trace("savingMenu.click()");
        savingsMenu.click();

        logger.trace("viewSavingsMenuItem.click");
        viewSavingsMenuItem.click();

        takeScreenshot();

        return new TransactionsPage(driver);
    }

    /**
     * HomePage-ről elnavigál a My Profile oldalra az Avatar Dropdown menüben.
     * @return My Profile oldal objektuma
     */
    @Step("Navigálás a Profil adatiokat megjelenítő és módosító oldalra.")
    public MyProfilePage gotoMyProfilePage () {
        logger.info("gotoMyProfilePage() called.");

        logger.trace("avatarDropdownMenuButton.click()");
        avatarDropdownMenuButton.click();

        logger.trace("avatarDropdownMyProfileLink.click()");
        avatarDropdownMyProfileLink.click();

        takeScreenshot();

        return new MyProfilePage(driver);
    }

    /**
     * Adatok törlése
     */
     @Step("Adatok törlése.")
    public void deleteData() {
        logger.info("deleteData() called");

        logger.trace("avatarDropdownMenuButton.click()");
        avatarDropdownMenuButton.click();

        logger.trace("avatarDropdownDeleteDataLink.click()");
        avatarDropdownDeleteDataLink.click();

         takeScreenshot();
    }

    /**
     * Ellenőrzi a HomePage betöltődését
     * @return true, ha betöltődik a HomePage
     */
    @Step("HomePage betöltődésének ellenőrzése.")
    public boolean isLoaded() {
        logger.info("HomePage.isLoaded() called");
         boolean isLoaded = isLoaded(savingsMenu)
                 && isLoaded(checkingMenu)
                 && isLoaded(avatarDropdownMenuButton);

         logger.trace("isLoaded= " + isLoaded);
         return isLoaded;
    }

    /**
     * Logout megvalósítása
     * @return LoginPage tipusú objektum
     */
    @Step("Logout megvalósítása.")
    public LoginPage logout() {
        logger.info("logout() called");

        logger.trace("avatarDropdownMenuButton.click()");
        avatarDropdownMenuButton.click();

        logger.trace("avatarDropdownLogoutLink.click()");
        avatarDropdownLogoutLink.click();

        takeScreenshot();

        return new LoginPage(driver);
    }

    /**
     * HomePage ellenőrzése
     */
    @Step("HomePage ellenőrzése.")
    public void ValidateHomePage() {
        logger.info("validateHomePage called");
        assertEquals("Digital Bank", driver.getTitle());
        assertTrue(driver.getCurrentUrl().endsWith("/bank/home"));
        assertEquals("Welcome " + globalTestData.getProperty(Consts.REG_FIRST_NAME), labelTitle.getText());
    }

    /**
     * HomePage ellenőrzése profil módosítása után
     */
    @Step("HomePage ellenőrzése profil módosítása után.")
    public void ValidateHomePageAfterModifyProfile() {
        logger.info("validateHomePageAfterModifyProfile called");
        assertEquals("Digital Bank", driver.getTitle());
        assertTrue(driver.getCurrentUrl().endsWith("/bank/home"));
        assertEquals("Welcome " + globalTestData.getProperty(Consts.MOD_FIRST_NAME), labelTitle.getText());
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
