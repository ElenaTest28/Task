package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TermsOfServicePage extends BasePage {

    public TermsOfServicePage(WebDriver driver) {
        super(driver);
        waitUntilPageLoaded(agreeCheckbox);
    }

    @FindBy(xpath = "//span[@class='url-link']")
    private List<WebElement> links;

    @FindBy(xpath = "//mat-icon[@aria-label='Back']")
    private WebElement closeButton;

    @FindBy(xpath = "//mat-checkbox")
    private WebElement agreeCheckbox;

    @FindBy(xpath = "//p/strong")
    private WebElement releaseTitle;

    @FindBy(xpath = "//div/h3")
    private WebElement termsTitle;

    @FindBy(xpath = "//button[@class='mat-button']")
    private WebElement signOutButton;

    @Step("Open {linkText}")
    public boolean isLinkCanBeOpened(String linkText) {
        links.stream().filter(l->l.getText().equals(linkText)).findFirst().get().click();
        return isElementPresent(releaseTitle);
    }

    public String getReleaseTitle() {
        return releaseTitle.getText();
    }

    public String getTermsTitle() {
        return termsTitle.getText();
    }

    public boolean isDocumentPageDisplayed() {
        return isElementPresent(closeButton);
    }

    @Step("Close document")
    public void closeDocument() {
        closeButton.click();
    }

    @Step
    public LoginPage signout() {
        signOutButton.click();
        return new LoginPage(driver);
    }

    public boolean isLoaded() {
        return isElementPresent(agreeCheckbox);
    }
}
