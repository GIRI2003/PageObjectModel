package myProjects.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class StandAloneTest {

	public static void main(String[] args) {
		
		String productName = "iphone 13 pro";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.findElement(By.id("userEmail")).sendKeys("girirohi2@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Girinath@2003");
		driver.findElement(By.id("login")).click();
		
		WebElement LoginToast = driver.findElement(By.className("toast-title"));
		wait.until(ExpectedConditions.visibilityOf(LoginToast)).getText();
		
		
		wait.until(ExpectedConditions.invisibilityOf(LoginToast));
		List<WebElement> products = driver.findElements(By.className("mb-3"));
		for (WebElement product : products) {
			String productText = product.findElement(By.tagName("b")).getText();
			if(productText.equalsIgnoreCase(productName)) {
				product.findElement(By.className("w-10")).click();
			}
		}
		
		WebElement addToCartToast = driver.findElement(By.className("ngx-toastr"));
//		wait.until(ExpectedConditions.visibilityOf(addToCartToast));
		wait.until(ExpectedConditions.invisibilityOf(addToCartToast));
		
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		String CartItem = driver.findElement(By.cssSelector("div[class='cartSection'] h3")).getText();
		if(CartItem.equalsIgnoreCase(productName)) {
			driver.findElement(By.cssSelector("li[class='totalRow'] button")).click();
		}
		
		driver.findElement(By.xpath("//*[@placeholder='Select Country']")).sendKeys("india");
		WebElement countryDropdown = driver.findElement(By.cssSelector(".ta-results"));
		wait.until(ExpectedConditions.visibilityOf(countryDropdown));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		
		WebElement clickButton = driver.findElement(By.cssSelector(".btnn"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", clickButton);
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order")); 
		
		driver.quit();
		
	}

}
