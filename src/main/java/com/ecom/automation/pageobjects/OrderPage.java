package com.ecom.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecom.automation.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

//-------POM Class in src/main/java----------
	WebDriver driver;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement placehoderValue;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement countryname;
	
	@FindBy(css=".action__submit")
	WebElement Placeholdersubmit;
	
	By countyBy=By.cssSelector(".ta-results");

//----Action Method for Login------
	
	
	public void SelectPalceHolderValue(String countryName)
	{
		Actions action=new Actions(driver);
		action.sendKeys(placehoderValue,countryName).build().perform();
		WaitForAllElementAppear(countyBy);
		countryname.click();
		
		
	}
	public ConfirmationPage SubmitOrder()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",  Placeholdersubmit);
		return new ConfirmationPage(driver);
		
	}
	
}
