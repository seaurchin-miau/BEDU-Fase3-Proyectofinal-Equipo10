package bedu.proyecto.ecommerce.pages;


import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainPage extends BasePage {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private HomePage homePage;

/*    @Autowired
    private WebDriver webDriver;*/

    public void navigate() {
        navigatePage("http://eaapp.somee.com");
    }
    public void PerformLogin() {

        //webDriver.navigate().to("http://eaapp.somee.com");
        homePage.ClickLogin();
        loginPage.Login("admin", "password");
        loginPage.ClickLogin();
    }


}
