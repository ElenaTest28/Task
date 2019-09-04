package pages;

import io.qameta.allure.Step;
import models.PatientModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewPatientPage extends BasePage {

    public NewPatientPage(WebDriver driver) {
        super(driver);
        waitUntilPageLoaded(firstNameField);
    }

    @FindBy(xpath = "//input[@formcontrolname='first_name']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@formcontrolname='last_name']")
    private WebElement lastNameField;

    @FindBy(xpath = "//mat-select[@formcontrolname='day']")
    private WebElement dayOfBirthField;

    @FindBy(xpath = "//mat-select[@formcontrolname='month']")
    private WebElement monthOfBirthField;

    @FindBy(xpath = "//mat-select[@formcontrolname='year']")
    private WebElement yearOfBirthField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitBtn;

    @FindBy(xpath = "//mat-select[@formcontrolname='country_id']")
    private WebElement countryField;

    @Step("Fill required fields on the form")
    public PatientModalCard fillForm(PatientModel patientModel) {
        firstNameField.sendKeys(patientModel.getFirstName());
        lastNameField.sendKeys(patientModel.getLastName());
        dayOfBirthField.sendKeys(patientModel.getDayOfBitrh());
        System.out.println(patientModel.getMonthOfBirth() + " month");
        monthOfBirthField.sendKeys(patientModel.getMonthOfBirth());
        yearOfBirthField.sendKeys(patientModel.getYearOfBirth());
        countryField.sendKeys(patientModel.getCountry());
        isElementPresent(submitBtn);
        submitBtn.click();
        return new PatientModalCard(driver);
    }
}
