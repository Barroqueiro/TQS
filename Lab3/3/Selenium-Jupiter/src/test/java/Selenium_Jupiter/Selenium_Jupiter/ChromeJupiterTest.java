package Selenium_Jupiter.Selenium_Jupiter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class ChromeJupiterTest {

	  @SuppressWarnings("deprecation")
	  @Test
	  public void blazePurchaseTest(ChromeDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, Object> vars = new HashMap<String, Object>();
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
	    driver.quit();
	}
	  
    @Test
    void testWithOneChrome(ChromeDriver driver) {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.getTitle(),
                containsString("JUnit 5 extension for Selenium"));
    }

    @Test
    void testWithTwoChromes(ChromeDriver driver1, ChromeDriver driver2) {
        driver1.get("http://www.seleniumhq.org/");
        driver2.get("http://junit.org/junit5/");
        assertThat(driver1.getTitle(), startsWith("Selenium"));
        assertThat(driver2.getTitle(), equalTo("JUnit 5"));
    }
    
    @Test
    void testWithHTMLUNITdRIVER(HtmlUnitDriver driver) {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.getTitle(),
                containsString("JUnit 5 extension for Selenium"));
    }

}
