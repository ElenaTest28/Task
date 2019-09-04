import models.PatientCredentials;
import models.PatientModel;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.TermsOfServicePage;


public class LoginTest extends WebDriverScenario {


    private PatientCredentials newPatientCreds;
    private LoginPage loginPge;
    private TermsOfServicePage termsOfServicePage;

    @BeforeClass
    public void setUp() {
        init();

        PatientCredentials adminCredentials = new PatientCredentials()
                .setPatientId(System.getProperty("username"))
                .setPatientPassword(System.getProperty("password"));

        PatientModel patientModel = new PatientModel().generateRandomPatientModel();

        loginPge = Preconditions.loginAndCreateNewUser(WebDriverScenario.driver, adminCredentials, patientModel);
        newPatientCreds = Preconditions.newPatientCreds;
    }

    @Test
    public void testReleaseOfInformation() {
        termsOfServicePage =
                loginPge.loginAs(newPatientCreds.getPatientId(),
                        newPatientCreds.getPatientPassword(),
                        TermsOfServicePage.class);

        Assert.assertTrue(
                termsOfServicePage.isLinkCanBeOpened("release of medical information."),
                "Link on release of info page can be opened");
        Assert.assertEquals(
                termsOfServicePage.getReleaseTitle(),
                "Kombinierte Einwilligungserklärung und Schweigepflichtsentbindung",
                "Expected title found");
    }

    @Test
    public void testTermsAndConditions() {
        termsOfServicePage =
                loginPge.loginAs(newPatientCreds.getPatientId(),
                        newPatientCreds.getPatientPassword(),
                        TermsOfServicePage.class);

        Assert.assertTrue(
                termsOfServicePage.isLinkCanBeOpened("terms and conditions"),
                "Link on terms page can be opened"
        );
        Assert.assertEquals(
                termsOfServicePage.getTermsTitle(),
                "Allgemeine Geschäftsbedingungen (Stand: 19.07.2018)",
                "Expected title found");
    }

    @AfterMethod
    public void logout() {
        if (termsOfServicePage.isDocumentPageDisplayed()) {
            termsOfServicePage.closeDocument();
            Assert.assertTrue(termsOfServicePage.isLoaded());
        }
        loginPge = termsOfServicePage.signout();
        Assert.assertTrue(loginPge.isLoaded());
    }
}
