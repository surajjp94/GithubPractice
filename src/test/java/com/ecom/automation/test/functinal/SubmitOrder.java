package com.ecom.automation.test.functinal;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ecom.automation.TestComponents.BaseTest;
import com.ecom.automation.pageobjects.ConfirmationPage;
import com.ecom.automation.pageobjects.MyCartPage;
import com.ecom.automation.pageobjects.OrderPage;
import com.ecom.automation.pageobjects.ProductCatalogue;

public class SubmitOrder extends BaseTest {
	public static WebDriver driver;
	public static String ProductName="QWERTY";
	public static String countryname="india";
	
	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String, String>input) throws IOException {
		
		ProductCatalogue productCatalogue=page.loginApplicatio(input.get("email"), input.get("pwd"));
		
//------------Implementation of explicit wait to handle application synchronously on loading------------
		productCatalogue.addToCart(input.get("productName"));
		MyCartPage cart= productCatalogue.clickOnCart();
		
//----------- Logic to verify items in the cart with Streams and Checkout---;-------------	
				
		Boolean match=cart.VerifyMyCart(input.get("productName"));
		//Assert.assertTrue(match);
		OrderPage order=cart.clickOncheckOut();
		
//-----------Wrapping up end to end automation Script on Purchasing Order in Ecommerce App-----------	
		
		order.SelectPalceHolderValue(countryname);
		ConfirmationPage confirmPage=order.SubmitOrder();

		String ActualMessage=confirmPage.VerifyMessage();
		
		Assert.assertTrue(ActualMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
				
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalogue productCatalogue=page.loginApplicatio("surajsuri07@gmail.com", "Suraj@123");
		MyCartPage pg=productCatalogue.ClickOnOrder();
		Assert.assertTrue(pg.VerifyOrderDisplay(ProductName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
	
		List<HashMap<String,String>> data=getJsonData();
	
		return new Object[][]{{data.get(0)}};
		
		
		
	}

}
