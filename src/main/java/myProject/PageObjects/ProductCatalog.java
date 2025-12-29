package myProject.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import myProject.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents{

WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	List<WebElement> products = driver.findElements(By.className("mb-3"));
	@FindBy(className = "mb-3")
	List<WebElement> products;
	
	// WebElement LoginToast = driver.findElement(By.className("toast-title"));
	@FindBy(className = "toast-title")
	WebElement LoginToast;
	
//	driver.findElement(By.className("ngx-toastr"));
	@FindBy(xpath  = "//div[@aria-label='Product Added To Cart']")
	WebElement addToCartToast;
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	public List<WebElement> getProductList() {
		waitForElementsToAppear(products);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod = getProductList().stream().filter(product -> 
		product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod =getProductByName(productName);
		prod.findElement(addToCart).click();
		WaitForElementToDisAppear(addToCartToast);
		
	}
	
	
}
