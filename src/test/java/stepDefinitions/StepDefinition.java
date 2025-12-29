package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import myProject.PageObjects.CartPage;
import myProject.PageObjects.ConfirmPage;
import myProject.PageObjects.LandingPage;
import myProject.PageObjects.PaymentPage;
import myProject.PageObjects.ProductCatalog;
import myProjects.TestComponents.BaseTest;

public class StepDefinition extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public CartPage cartPage;
	public PaymentPage toCart;
	public ConfirmPage confirmPage;
	public String actualMessage;
	
	@Given("User is landed on ecommerce application")
	public void User_is_landed_on_ecommerce_application() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^user logged in with username (.+) and password (.+)$")
	public void user_logged_in_with_username_and_password(String username, String password) {
		productCatalog = landingPage.loginApplication(username, password);
	}
	
	@When("^user adds product (.+) to the cart$")
	public void user_adds_product_to_the_cart(String productName) {
		productCatalog.addProductToCart(productName);
		cartPage = productCatalog.goToCartPage();
		
	}
	
	@And("^user checkout the product (.+) and submit the order$")
	public void user_checkout_the_product_and_submit_the_order(String productName) {
		toCart = cartPage.addToCart(productName);
		toCart.selectCountry("india");
		confirmPage = toCart.confirmButton();
		actualMessage = confirmPage.confirmMessage();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_confirmationPage(String expectedMessage) {
		Assert.assertTrue(actualMessage.equalsIgnoreCase(expectedMessage));
		driver.quit();
	}
	
	@Then("{string} error message should display")
	public void error_message_should_display(String expectedErrorMessage) {
		String actualErrorMessage = landingPage.getErrorMessage();
		Assert.assertTrue(actualErrorMessage.equalsIgnoreCase(expectedErrorMessage));
		driver.quit();;
	}
	
	

}
