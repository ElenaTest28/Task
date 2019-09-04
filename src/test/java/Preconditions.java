import models.PatientCredentials;
import models.PatientModel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.DashboardPage;
import pages.LoginPage;
import pages.NewPatientPage;
import pages.PatientModalCard;

public class Preconditions extends WebDriverScenario {

    public static PatientCredentials newPatientCreds = new PatientCredentials();
    private WebDriver driver;
    private DashboardPage dashboardPage;
    private PatientModalCard patientModalCard;
    private LoginPage loginPage;

    private Preconditions(WebDriver driver) {
        this.driver = driver;
    }

    public static LoginPage loginAndCreateNewUser(WebDriver driver, PatientCredentials credentials, PatientModel patientModel) {
        Preconditions instance = new Preconditions(driver).loginAsAdmin(credentials)
                .createNewPatient(patientModel)
                .logout();
        return instance.loginPage;
    }

    private Preconditions loginAsAdmin(PatientCredentials credentials) {
        loginPage = new LoginPage(driver).open();
        dashboardPage = loginPage.loginAs(credentials.getPatientId(), credentials.getPatientPassword(), DashboardPage.class);
        return this;
    }

    private Preconditions logout() {
        dashboardPage = new DashboardPage(driver).open();

        loginPage = dashboardPage.signOut();
        Assert.assertTrue(loginPage.isLoaded(), "Login page is opened");

        return this;
    }

    private Preconditions createNewPatient(PatientModel patientModel) {
        dashboardPage = new DashboardPage(driver);

        NewPatientPage newPatientPage = dashboardPage.openNewPatientPage();

        patientModalCard = newPatientPage.fillForm(patientModel);

        newPatientCreds.setPatientId(patientModalCard.getCasparId())
                .setPatientPassword(patientModalCard.getCasparPassword());
        patientModalCard.close();

        return this;
    }
}
