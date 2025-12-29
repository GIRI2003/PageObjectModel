package myProject.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import myProject.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css = "li[class='totalRow'] button")
	WebElement checkout;
	
	@FindBy(css = "tr td:nth-of-type(2)")
	List<WebElement> orderNames;
	
	
	
	public boolean verifyOrderDisplay(String product) {
		boolean match = orderNames.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(product));
		return match;
	}

}
