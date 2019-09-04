package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@formcontrolname='login']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@formcontrolname='password']")
    private WebElement passwordField;

    @FindBy(xpath = "/html/body/app-root/div/ui-view/ng-component/ui-view/app-sign-in/div/div[2]/form/div[3]/div/button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("login as {username}")
    public <T extends BasePage> T loginAs(String username, String password, Class<T> tClass) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        T page = PageFactory.initElements(driver, tClass);
        return page;
    }

    @Step("Open login page")
    public LoginPage open() {
        driver.get("https://beta.caspar-health.com/en/#/core/new-patient");
        return this;
    }

    @Step("Login page is loaded")
    public boolean isLoaded() {
        waitUntilPageLoaded(usernameField);
        return isElementPresent(usernameField);
    }


}
