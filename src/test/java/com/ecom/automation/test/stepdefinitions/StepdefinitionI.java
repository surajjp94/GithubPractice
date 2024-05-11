package com.ecom.automation.test.stepdefinitions;

import java.io.IOException;

import org.testng.Assert;

import com.ecom.automation.TestComponents.BaseTest;
import com.ecom.automation.pageobjects.ConfirmationPage;
import com.ecom.automation.pageobjects.LandingPage;
import com.ecom.automation.pageobjects.MyCartPage;
import com.ecom.automation.pageobjects.OrderPage;
import com.ecom.automation.pageobjects.ProductCatalogue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepdefinitionI extends BaseTest {

	
	public LandingPage landingpage; 
	public ProductCatalogue productCatalogue;
	public MyCartPage cart;
	public OrderPage order;
	public ConfirmationPage confirmPage;
	@Given("I landed o ecommerce page")
	public void i_landed_o_ecommerce_page() throws IOException {
		landingpage=launchApplication();
	}

	@Given("^login with UserName (.+) and Password (.+)$")
	public void login_with_username_and_password(String  userName, String Password) {
		productCatalogue=page.loginApplicatio(userName,Password);
	}

	@When("^I add Product (.+) to cart$")
	public void i_add_product_to_cart(String ProductName) {
		productCatalogue.addToCart(ProductName);
		cart= productCatalogue.clickOnCart();
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_qwerty_and_submit_the_order(String ProductName) {
		cart.VerifyMyCart(ProductName);
		//Assert.assertTrue(match);
		 order=cart.clickOncheckOut();
		 order.SelectPalceHolderValue("india");
		confirmPage=order.SubmitOrder();

	}

	@Then("{string} message displayed on Confirmation page")
	public void message_displayed_on_confirmation_page(String string) {
		String ActualMessage=confirmPage.VerifyMessage();
		
		Assert.assertTrue(ActualMessage.equalsIgnoreCase(string));
	}

}
