package com.ecom.automation.test.functinal;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {
	public static WebDriver driver;
	static String countryName = "India";
	
	@Test
	public  void standalone(){
		
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("surajsuri07@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Suraj@123");
		driver.findElement(By.id("login")).click();
		
//------------Implementation of explicit wait to handle application synchronously on loading------------
		
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("zara coat 3")).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
//------------Implementation of explicit wait to handle application synchronously on loading------------
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
//----------- Logic to verify items in the cart with Streams and Checkout----------------------		
		List<WebElement>cartProducts=driver.findElements(By.cssSelector(".cartWrap h3"));
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase("zara coat 3"));
		//Assert.assertTrue(match);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
//-----------Wrapping up end to end automation Script on Purchasing Order in Ecommerce App-----------	
		
		Actions action=new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement placeorder = driver.findElement(By.cssSelector(".action__submit"));
		js.executeScript("arguments[0].click();",  placeorder);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();

        
		
		
		
		
		
		
		
	}

}
