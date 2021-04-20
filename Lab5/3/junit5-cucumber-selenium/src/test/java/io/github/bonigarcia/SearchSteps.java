/*
 * (C) Copyright 2020 Boni Garcia (http://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.bonigarcia;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchSteps {

    private WebDriver webDriver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(url);
    }

    @And("I want to go from {string} to {string}")
    public void iType(String to, String from) {
    	webDriver.manage().window().setSize(new Dimension(909, 913));
    	webDriver.findElement(By.name("fromPort")).click();
 	    {
 	      WebElement dropdown = webDriver.findElement(By.name("fromPort"));
 	      dropdown.findElement(By.xpath("//option[. = '"+to+"']")).click();
 	    }
 	    webDriver.findElement(By.name("fromPort")).click();
 	    {
 	      WebElement dropdown = webDriver.findElement(By.name("toPort"));
 	      dropdown.findElement(By.xpath("//option[. = '"+from+"']")).click();
 	    }
 	    {
 	      WebElement element = webDriver.findElement(By.name("toPort"));
 	      Actions builder = new Actions(webDriver);
 	      builder.moveToElement(element).clickAndHold().perform();
 	    }
 	    {
 	      WebElement element = webDriver.findElement(By.name("toPort"));
 	      Actions builder = new Actions(webDriver);
 	      builder.moveToElement(element).perform();
 	    }
 	    {
 	      WebElement element = webDriver.findElement(By.name("toPort"));
 	      Actions builder = new Actions(webDriver);
 	      builder.moveToElement(element).release().perform();
 	    }
 	    webDriver.findElement(By.name("toPort")).click();
    }

    @And("I enter my information")
    public void iPressEnter() {
    	webDriver.findElement(By.cssSelector(".btn-primary")).click();
    	webDriver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
    	webDriver.findElement(By.id("inputName")).click();
	    webDriver.findElement(By.id("inputName")).sendKeys("Dinis Cruz");
	    webDriver.findElement(By.id("address")).sendKeys("Rua da almada");
	    webDriver.findElement(By.id("city")).sendKeys("Aveiro");
	    webDriver.findElement(By.id("state")).sendKeys("Aveiro");
	    webDriver.findElement(By.id("zipCode")).sendKeys("3800");
	    webDriver.findElement(By.cssSelector("form")).click();
	    webDriver.findElement(By.id("creditCardNumber")).click();
	    webDriver.findElement(By.id("creditCardNumber")).sendKeys("1111222233334444");
	    webDriver.findElement(By.id("creditCardYear")).click();
	    webDriver.findElement(By.id("creditCardYear")).sendKeys("2023");
	    webDriver.findElement(By.id("nameOnCard")).click();
	    webDriver.findElement(By.id("nameOnCard")).sendKeys("Dinis Cruz");
	    webDriver.findElement(By.id("rememberMe")).click();
	    webDriver.findElement(By.cssSelector("form")).click();
	    webDriver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @Then("I should be shown results with the price {string}")
    public void iShouldBeShownResultsIncluding(String price) {
    	assertThat(webDriver.findElement(By.cssSelector("h1")).getText(), is("Thank you for your purchase today!"));
    	webDriver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(2)")).click();
	    assertThat(webDriver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(2)")).getText(), is(price+" USD"));
	    assertThat(webDriver.getTitle(), is("BlazeDemo Confirmation"));
	    webDriver.quit();
    }

}
