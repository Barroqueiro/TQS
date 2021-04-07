package SauceLabStack.SauceLabSatck;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SauceLabsHomePageTest {
	private WebDriver browser;
	
	@BeforeEach
	public void before() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");  
        browser = new ChromeDriver();
	}


    @Test
     public void site_header_is_on_home_page() {
    	 System.out.println(browser);
         browser.get("https://www.saucelabs.com"); 
         WebElement href = browser.findElement(By.xpath("//a[@href='https://accounts.saucelabs.com/']"));        
         assertTrue((href.isDisplayed()));      
    }
    
	@AfterEach
	public void after() {
		 browser.close(); 
	}
}
