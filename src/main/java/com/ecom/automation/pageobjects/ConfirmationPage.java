package com.ecom.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecom.automation.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

//-------POM Class in src/main/java----------
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement message;
	


	
	By messageBy=By.cssSelector(".hero-primary");

//----Action Method for Login------
	
	public String VerifyMessage()
	{
		WaitForElementAppear(messageBy);
		return message.getText();
	}
	
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
//	String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//	driver.close();
//
}
