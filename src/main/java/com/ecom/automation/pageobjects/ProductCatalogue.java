package com.ecom.automation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecom.automation.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

//-------POM Class in src/main/java----------
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement annimating;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartClick;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderClick;
	
	
	By productsBy=By.cssSelector(".mb-3");
	By productName=By.cssSelector("b");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastcontainerBy=By.cssSelector("#toast-container");

//----Action Method for Login------
	
	public List<WebElement> getProducts()
	{
		WaitForElementAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String ProductName){
		
		return getProducts().stream().filter(product->
		product.findElement(productName).getText().equalsIgnoreCase(ProductName)).findFirst().orElse(null);
		
	}
	
	public void addToCart(String ProductName)
	{
		WebElement prod=getProductByName(ProductName);		
		prod.findElement(addToCart).click();
	}
	
	public MyCartPage clickOnCart()
	{
		WaitForElementAppear(toastcontainerBy);
		WaitForInvisibilityOf(annimating);
		cartClick.click();
		MyCartPage cart= new MyCartPage(driver);
		return cart;
	}
	
	public MyCartPage ClickOnOrder()
	{
		WaitForElementAppear(toastcontainerBy);
		orderClick.click();
		MyCartPage cart= new MyCartPage(driver);
		return cart;
	}
}
