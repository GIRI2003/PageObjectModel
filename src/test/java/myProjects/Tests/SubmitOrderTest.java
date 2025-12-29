package myProjects.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import myProject.PageObjects.CartPage;
import myProject.PageObjects.ConfirmPage;
import myProject.PageObjects.OrdersPage;
import myProject.PageObjects.PaymentPage;
import myProject.PageObjects.ProductCatalog;
import myProjects.TestComponents.BaseTest;


public class SubmitOrderTest extends BaseTest{
	
	
	
	@Test( dataProvider = "getData" )
	public void submitTest(HashMap<String, String> input) throws IOException {
		
		
		String selectmycountry = "india";
		String ExpectedMessage = "Thankyou for the order.";

		ProductCatalog productcatalog =landingPage.loginApplication(input.get("email"),input.get("password"));
		productcatalog.addProductToCart(input.get("product"));
		CartPage cartPage = productcatalog.goToCartPage();
		PaymentPage paymentPage =cartPage.addToCart(input.get("product"));
		paymentPage.selectCountry(selectmycountry);
		ConfirmPage confirmPage =paymentPage.confirmButton();
		String actualMessage = confirmPage.confirmMessage();
		Assert.assertTrue(actualMessage.equalsIgnoreCase("Thankyou for the order."));
		
	}
	
	@Test(dependsOnMethods = {"submitTest"},dataProvider = "getData")
	public void orderHistoryTest(HashMap<String, String> input) {
		ProductCatalog productcatalog =landingPage.loginApplication(input.get("email"),input.get("password"));
		OrdersPage orderPage = productcatalog.goToOrdersPage();
		boolean verifyOrderDisplay = orderPage.verifyOrderDisplay(input.get("product"));
		Assert.assertTrue(verifyOrderDisplay);
		
	}
	
	

	@DataProvider
	public Object[][] getData() throws IOException {
		
		
		List<HashMap<String, String>> data = getJSONData(System.getProperty("user.dir")+"\\src\\test\\java\\myProjects\\TestData\\PurshaseOrder.json");
		return new Object[][] {
			{data.get(0)},
			{data.get(1)}
		};
		
		
		/* Method 1 -> Using normal method
		 * return new Object[][] {
		 * {"girirohi2@gmail.com","Girinath@2003","zara coat 3"},
		 * {"girinath@gmail.com","Girin@2003","adidas original"} };
		 */
			 
		
		
		
		
		/* Method 2 -> Using HashMap class
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email", "girirohi2@gmail.com"); map1.put("password",
		 * "Girinath@2003"); map1.put("product", "zara coat 3");
		 * 
		 * HashMap<String, String> map2 = new HashMap<String, String>();
		 * map2.put("email", "girinath@gmail.com"); map2.put("password", "Girin@2003");
		 * map2.put("product", "adidas original");
		 * 
		 * return new Object[][] {{map1},{map2}};
		 */
		 
		
		
		
	}
	
}
