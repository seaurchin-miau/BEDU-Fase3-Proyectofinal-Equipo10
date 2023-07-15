package bedu.proyecto.ecommerce;

import bedu.proyecto.ecommerce.pages.HomePage;
import bedu.proyecto.ecommerce.pages.LoginPage;
import bedu.proyecto.ecommerce.pages.MainPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringEcommerceApplicationTests {

	@Autowired
	private MainPage mainPage;
	@Test
	void contextLoads() {
/*		System.out.println("holaaaa");
		HomePage homePage = new HomePage();
		//LoginPage loginPage = new LoginPage();
		LoginPage loginPage = homePage.ClickLogin();
		loginPage.ClickLogin();*/

		mainPage.navigate();

		mainPage.PerformLogin();

	}

}
