package com.ecom.automation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecom.automation.AbstractComponents.AbstractComponent;

public class MyCartPage extends AbstractComponent {

//-------POM Class in src/main/java----------
	WebDriver driver;
	
	public MyCartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartWrap h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	By productby=By.cssSelector("tr td:nth-child(3)");
	
	public Boolean VerifyMyCart(String ProductName)
	{
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}
	
	public OrderPage clickOncheckOut()
	{
		checkout.click();
		OrderPage order= new OrderPage(driver);
		return order;
	}
	
	public Boolean VerifyOrderDisplay(String ProductName)
	{
		WaitForAllElementAppear(productby);
		Boolean match=productNames.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}
	

}
