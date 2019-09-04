package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PatientModalCard extends BasePage {

    public PatientModalCard(WebDriver driver) {
        super(driver);
        waitUntilPageLoaded(modalCard);
    }


    @FindBy(xpath = "//app-new-user-dialog")
    private WebElement modalCard;

    @FindBy(xpath = "//app-new-user-dialog/div[2]//div[3]/div[3]/div[2]")
    private WebElement casparId;

    @FindBy(xpath = "//app-new-user-dialog/div[2]//div[3]/div[4]/div[2]")
    private WebElement casparPassword;

    @FindBy(xpath = "//button[@type='button']")
    private WebElement closeButton;

    public String getCasparId() {
        return casparId.getText();
    }

    public String getCasparPassword() {
        return casparPassword.getText();
    }

    @Step("Close patient card")
    public void close() {
        closeButton.click();
    }
}
