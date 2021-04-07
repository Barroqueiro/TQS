package SauceLabStack.SauceLabSatck;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class BlazePurchaseTestTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  
  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  
  @AfterEach
  public void tearDown() {
    driver.quit();
  }
  
  @SuppressWarnings("deprecation")
@Test
  public void blazePurchaseTest() {
    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(909, 913));
    driver.findElement(By.name("fromPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'Boston']")).click();
    }
    driver.findElement(By.name("fromPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'Berlin']")).click();
    }
    {
      WebElement element = driver.findElement(By.name("toPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.name("toPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.name("toPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.name("toPort")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("Dinis Cruz");
    driver.findElement(By.id("address")).sendKeys("Rua da almada");
    driver.findElement(By.id("city")).sendKeys("Aveiro");
    driver.findElement(By.id("state")).sendKeys("Aveiro");
    driver.findElement(By.id("zipCode")).sendKeys("3800");
    driver.findElement(By.cssSelector("form")).click();
    driver.findElement(By.id("creditCardNumber")).click();
    driver.findElement(By.id("creditCardNumber")).sendKeys("1111222233334444");
    driver.findElement(By.id("creditCardYear")).click();
    driver.findElement(By.id("creditCardYear")).sendKeys("2023");
    driver.findElement(By.id("nameOnCard")).click();
    driver.findElement(By.id("nameOnCard")).sendKeys("Dinis Cruz");
    driver.findElement(By.id("rememberMe")).click();
    driver.findElement(By.cssSelector("form")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
    assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Thank you for your purchase today!"));
    driver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(2)")).click();
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(2)")).getText(), is("555 USD"));
    assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
  }
}
