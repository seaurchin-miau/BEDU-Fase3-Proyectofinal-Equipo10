package bedu.proyecto.ecommerce.libraries;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class SeleniumDriver {
    private WebDriver driver;

    public SeleniumDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                // Set the path to your ChromeDriver executable
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                // Set the path to your GeckoDriver executable
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case EDGE:
                // Set the path to your EdgeDriver executable
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            // Add more cases for other browsers if needed

            default:
                throw new IllegalArgumentException("Invalid browser type: " + browserType);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}