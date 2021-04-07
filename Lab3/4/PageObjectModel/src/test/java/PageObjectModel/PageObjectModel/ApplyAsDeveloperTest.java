package PageObjectModel.PageObjectModel;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplyAsDeveloperTest {
   WebDriver driver;

   @SuppressWarnings("deprecation")
@Before
   public void setup(){
	   System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
       driver = new ChromeDriver();

   }

   @Test
   public void applyAsDeveloper() {
       //Create object of HomePage Class
       HomePage home = new HomePage(driver);
       home.clickOnDeveloperApplyButton();

       //Create object of DeveloperPortalPage
       DeveloperPortalPage devportal= new DeveloperPortalPage(driver);

       //Check if page is opened
       Assert.assertTrue(devportal.isPageOpened());

       //Click on Join Toptal
       devportal.clickOnJoin();


       //Check if page is opened
       Assert.assertTrue(devportal.isPageOpened());

       //Fill up data
       devportal.setDeveloper_email("cruzdinis@ua.pt");
       devportal.setDeveloper_full_name("Dinis Cruz");
       devportal.setDeveloper_password("themostcomplexpasswordintheworld");
       devportal.setDeveloper_password_confirmation("themostcomplexpasswordintheworld");
       devportal.setDeveloper_email("cruzdinis@ua.pt");

       //Click on join
       //applyPage.clickOnJoin(); 
   }

    @After
    public void close(){
          driver.close();
       }
   }
