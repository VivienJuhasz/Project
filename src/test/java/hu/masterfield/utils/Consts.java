package hu.masterfield.utils;

/**
 * Ez az osztály konstansokat tartalmaz, amelyeket külső forrásokból, property fileból (.properties) olvasunk be **/
public class Consts {
    //Konstansok az applikációhoz
    public static final String APPLICATION_URL = "application.url";
    //Konstansok az elérési utakhoz
    public static final String GLOBAL_TEST_DATA_PROPERTIES = "/globalTestData.properties";
    public static final String SCREENSHOTS_FOLDER = System.getProperty("user.dir").replace("\\", "/") + "/target/screenshot";
    public static final String CONFIG_PROPERTIES = "/config.properties";

    //Konstansok a Loginhoz
    public static final String LOGIN_USERNAME = "login.userName";
    public static final String LOGIN_PASSWORD = "login.password";


    // Konstatnsok a Savingshez

    public static final String ACCOUNT_TYPES_SAVINGS = "Savings";
    public static final String ACCOUNT_TYPES_MONEY_MARKET = "Money Market";
    public static final String OWNERSHIP_TYPES_INDIVIDUAL = "Individual";
    public static final String OWNERSHIP_TYPES_JOINT = "Joint";
    public static final String SAVINGS_DATA_CSV = "/savingsData.csv";
    public static final String SAVE_SAVINGS_DATA_CSV = "target/dumpSavings.csv";

    //Konstansok a regisztrációhoz
    public static final String REG_TITLE = "reg.title";
    public static final String REG_FIRST_NAME = "reg.firstName";
    public static final String REG_LAST_NAME  = "reg.lastName";
    public static final String REG_GENDER = "reg.gender";
    public static final String REG_DATE_OF_BIRTH = "reg.dateOfBirth";
    public static final String REG_SOCIAL_SECURITY_NUMBER = "reg.socialSecurityNumber";
    public static final String REG_EMAIL_ADDRESS = "reg.emailAddress";
    public static final String REG_PASSWORD = "reg.password";
    public static final String REG_CONFIRM_PASSWORD = "reg.password";
    public static final String REG_ADDRESS = "reg.address";
    public static final String REG_LOCALITY = "reg.locality";
    public static final String REG_REGION = "reg.region";
    public static final String REG_POSTAL_CODE = "reg.postalCode";
    public static final String REG_COUNTRY = "reg.country";
    public static final String REG_HOME_PHONE= "reg.homePhone";
    public static final String REG_MOBILE_PHONE = "reg.mobilePhone";
    public static final String REG_WORK_PHONE = "reg.workPhone";

    //Konstansok a profil adatok módosításához
    public static final String MOD_TITLE = "mod.title";
    public static final String MOD_FIRST_NAME = "mod.firstName";
    public static final String MOD_LAST_NAME  = "mod.lastName";
    public static final String MOD_ADDRESS = "mod.address";
    public static final String MOD_LOCALITY = "mod.locality";
    public static final String MOD_REGION = "mod.region";
    public static final String MOD_POSTAL_CODE = "mod.postalCode";
    public static final String MOD_COUNTRY = "mod.country";
    public static final String MOD_HOME_PHONE= "mod.homePhone";
    public static final String MOD_MOBILE_PHONE = "mod.mobilePhone";
    public static final String MOD_WORK_PHONE = "mod.workPhone";
}
