package myProjects.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import myProject.PageObjects.CartPage;
import myProject.PageObjects.ProductCatalog;
import myProjects.TestComponents.BaseTest;
import myProjects.TestComponents.Retry;

public class ErrorValidation extends BaseTest{

	
	
	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginError() {
		
		String email ="giriroh@gmail.com";
		String password = "Giri4rth@2003";
		landingPage.loginApplication(email, password);
		String errorMessage = landingPage.getErrorMessage();
		Assert.assertEquals(errorMessage, "Incorrect email or password.");
	}
	
	@Test(groups = {"ErrorHandling"})
	public void productError() {
		String email ="girirohi2@gmail.com";
		String password = "Girinath@2003";
		String product = "Zara coat 3";
		ProductCatalog productcatalog =landingPage.loginApplication(email, password);
		productcatalog.addProductToCart(product);
		CartPage cartPage = productcatalog.goToCartPage();
		boolean verifyProductDisplay = cartPage.verifyProductDisplay("Zara coat 33");
		Assert.assertFalse(verifyProductDisplay);
	}
	
	
}
