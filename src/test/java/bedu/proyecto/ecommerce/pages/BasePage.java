package bedu.proyecto.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.annotation.PostConstruct;

public abstract class BasePage {

    @Autowired
    private WebDriver webDriver;

    @PostConstruct
    public void InitPage() {
        PageFactory.initElements(webDriver, this);
    }

    public void navigatePage(String url){ webDriver.navigate().to(url);}

}
