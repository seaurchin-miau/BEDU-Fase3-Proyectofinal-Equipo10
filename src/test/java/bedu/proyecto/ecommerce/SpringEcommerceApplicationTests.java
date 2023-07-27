package bedu.proyecto.ecommerce;

import bedu.proyecto.ecommerce.libraries.BrowserType;
import bedu.proyecto.ecommerce.libraries.SeleniumDriver;
import bedu.proyecto.ecommerce.seleniumtests.pages.LoginPage;
import bedu.proyecto.ecommerce.seleniumtests.pages.OrdenesCompraPage;
import bedu.proyecto.ecommerce.seleniumtests.pages.OrderPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringEcommerceApplicationTests {

	/*
	SeleniumDriver seleniumDriver = new SeleniumDriver(BrowserType.CHROME); // or any other BrowserType
	WebDriver driver = seleniumDriver.getDriver();
	@Test
	void OrderPageTest() {

		OrderPage orderPage = new OrderPage(driver);
		orderPage.order();

		seleniumDriver.quitDriver();
	}

	@Test
	void LoginPageTest() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("abraham@bedu.com", "1234");

		seleniumDriver.quitDriver();
	}

	@Test
	void verOdenesDeCompraTest() {

		OrdenesCompraPage ordenesCompra = new OrdenesCompraPage(driver);
		ordenesCompra.verOdenesDeCompra();

		seleniumDriver.quitDriver();
	}

 */
}
