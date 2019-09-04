import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;

public class WebDriverScenario {

    protected static WebDriver driver;

    public void init() {
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir").replace("\\", "/") + "/src/main/resources/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterSuite
    public void tearDown(){
        driver.quit();
    }
}
