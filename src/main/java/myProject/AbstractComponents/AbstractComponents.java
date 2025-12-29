package myProject.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import myProject.PageObjects.CartPage;
import myProject.PageObjects.OrdersPage;

public class AbstractComponents {

	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement addToCart;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement orders;
	
	public CartPage goToCartPage() {
		addToCart.click();
		return new CartPage(driver);
		
	}
	
	public OrdersPage goToOrdersPage() {
		orders.click();
		return new OrdersPage(driver);
		
	}
	
	public void waitForElementsToAppear(List<WebElement> elements) {
	    wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	public void WaitForElementToDisAppear(WebElement Toaster) {
		wait.until(ExpectedConditions.invisibilityOf(Toaster));
	}
	
	public void WaitForElementToAppear(WebElement Toaster) {
		wait.until(ExpectedConditions.visibilityOf(Toaster));
	}
	
	public void JavaScriptExecution(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}


}
