package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
        waitUntilPageLoaded(newPatientButton);
    }

    @FindBy(xpath = "//button[@uisref='core.new-patient']")
    private WebElement newPatientButton;

    @FindBy(xpath = "//app-therapist-sidebar//div[9]/div[2]")
    private WebElement signOut;

    public NewPatientPage openNewPatientPage() {
        newPatientButton.click();
        return new NewPatientPage(driver);
    }

    @Step("Open dashboard page")
    public DashboardPage open() {
        //for cases we are not on this page before
        driver.get("https://beta.caspar-health.com/en/#/therapist/dashboard/my-patients");
        return this;
    }

    @Step("Sign out")
    public LoginPage signOut() {
        signOut.click();
        return new LoginPage(driver);
    }
}
