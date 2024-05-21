package com.ecom.automation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecom.automation.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

//-------POM Class in src/main/java----------
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userID;
	
	@FindBy(id="userPassword")
	WebElement userPWD;
	
	@FindBy(id="login")
	WebElement submit;

//----Action Method for Login------
	
	public ProductCatalogue loginApplicatio(String email, String password)
	{
		userID.sendKeys(email);
		userPWD.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public void Goto() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
}
