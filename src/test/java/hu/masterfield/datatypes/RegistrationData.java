package hu.masterfield.datatypes;

import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RegistrationData {
    /**
     * Hozzáférés biztosítás a GlobalTestData.properties filehoz.
     */
    protected static GlobalTestData globalTestData = new GlobalTestData();

    private String title;
    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private String socialSecurityNumber;
    private String emailAddress;
    private String password;
    private String confirmPassword;
    private String address;
    private String locality;
    private String region;
    private String postalCode;
    private String country;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;

    /** Paraméter nélküli konstruktor, amivel a globalTestData.properties fileból
     * hozzáférünk a tesztadatokhoz.
     * A példányosításnál nem kell felsorolni az összes adatot.
     */
    public RegistrationData() {
        this.title = globalTestData.getProperty(Consts.REG_TITLE);
        this.firstName = globalTestData.getProperty(Consts.REG_FIRST_NAME);
        this.lastName = globalTestData.getProperty(Consts.REG_LAST_NAME);
        this.gender = globalTestData.getProperty(Consts.REG_GENDER);
        this.dateOfBirth = globalTestData.getProperty(Consts.REG_DATE_OF_BIRTH);
        this.socialSecurityNumber = globalTestData.getProperty(Consts.REG_SOCIAL_SECURITY_NUMBER);
        this.emailAddress = globalTestData.getProperty(Consts.REG_EMAIL_ADDRESS);
        this.password = globalTestData.getProperty(Consts.REG_PASSWORD);
        this.confirmPassword = globalTestData.getProperty(Consts.REG_CONFIRM_PASSWORD);
        this.address = globalTestData.getProperty(Consts.REG_ADDRESS);
        this.locality = globalTestData.getProperty(Consts.REG_LOCALITY);
        this.region = globalTestData.getProperty(Consts.REG_REGION);
        this.postalCode = globalTestData.getProperty(Consts.REG_POSTAL_CODE);
        this.country = globalTestData.getProperty(Consts.REG_COUNTRY);
        this.homePhone = globalTestData.getProperty(Consts.REG_HOME_PHONE);
        this.mobilePhone = globalTestData.getProperty(Consts.REG_MOBILE_PHONE);
        this.workPhone = globalTestData.getProperty(Consts.REG_WORK_PHONE);
    }
}
