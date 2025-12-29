package myProject.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import myProject.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css = "li[class='totalRow'] button")
	WebElement checkout;
	
	@FindBy(css = "div[class='cartSection'] h3")
	List<WebElement> cart;
	
	public PaymentPage addToCart(String productName) {
		
		for (WebElement cartProductName : cart) {
			String CartItem = cartProductName.getText();
			if(CartItem.equalsIgnoreCase(productName)) {
				checkout.click();
				
			}
		}
		
		
		return new PaymentPage(driver);
	}
	
	public boolean verifyProductDisplay(String product) {
		boolean match = cart.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(product));
		return match;
	}

}
