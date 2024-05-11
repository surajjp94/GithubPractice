package com.ecom.automation.test.functinal;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewEcom {
	
	public static WebDriver driver;
	public static void main(String[] args) {
		System.getProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Desktop\\Testing\\SeleniumFramework\\EcomAutomation\\Driver\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_item")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".inventory_item"));
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector(".inventory_item .inventory_item_name ")).getText().equalsIgnoreCase("Sauce Labs Fleece Jacket")).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".btn")).click();
		
		driver.findElement(By.id("shopping_cart_container")).click();
		
		driver.findElement(By.id("checkout")).click();
		driver.findElement(By.id("first-name")).sendKeys("ABC");
		driver.findElement(By.id("last-name")).sendKeys("hjhh");
		driver.findElement(By.id("postal-code")).sendKeys("54463");
		driver.findElement(By.id("continue")).click();
		
		

	}

}
