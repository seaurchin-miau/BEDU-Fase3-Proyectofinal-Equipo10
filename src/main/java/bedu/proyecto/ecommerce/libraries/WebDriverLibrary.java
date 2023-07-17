package bedu.proyecto.ecommerce.libraries;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class WebDriverLibrary {

    @Bean
    //@ConditionalOnProperty(name = "browser", havingValue = "chrome")
    public WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

/*    @Bean
    //@ConditionalOnProperty(name = "browser", havingValue = "firefox")
    public WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }*/


}
