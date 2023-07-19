package bedu.proyecto.ecommerce.seleniumtests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;

    // Constructor to receive the WebDriver instance from the test class
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to perform login
    public void order() {
        driver.get("http://localhost:8080/");
        driver.manage().window().setSize(new Dimension(1517, 1022));
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("abraham@bedu.com");
        driver.findElement(By.id("password")).sendKeys("1234");
        driver.findElement(By.cssSelector(".btn")).click();
        driver.findElement(By.cssSelector(".col:nth-child(1) .btn")).click();
        driver.findElement(By.cssSelector(".btn-danger")).click();
        driver.findElement(By.cssSelector("font > font")).click();
        driver.findElement(By.cssSelector(".col:nth-child(2) .btn")).click();
        driver.findElement(By.cssSelector(".btn-danger")).click();
        driver.findElement(By.cssSelector("font > font")).click();
        driver.findElement(By.cssSelector(".col:nth-child(3) .btn")).click();
        driver.findElement(By.cssSelector(".btn-danger")).click();
        driver.findElement(By.linkText("Ver Orden")).click();
        driver.findElement(By.linkText("Generar")).click();
    }
}
