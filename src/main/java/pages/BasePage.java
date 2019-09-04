package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@SuppressWarnings({"unchecked", "deprecation"})
public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitUntilPageLoaded(WebElement element) {
        Wait wait = new WebDriverWait(driver, 10)
                .withTimeout(Duration.ofSeconds(2))
                .pollingEvery(Duration.ofMillis(500));

        wait.until((ExpectedCondition<Boolean>) d ->
                        ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));

        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public boolean isElementPresent(WebElement element) {
        Wait wait = new WebDriverWait(driver, 10)
                .withTimeout(Duration.ofSeconds(4))
                .pollingEvery(Duration.ofMillis(500));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

}
