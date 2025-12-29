package myProject.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import myProject.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "userEmail") 
	WebElement username;
	
	@FindBy(id = "userPassword") 
	WebElement password;
	
	@FindBy(id = "login") 
	WebElement login;
	
	@FindBy(css = "div[role='alert']")
	WebElement errorMessage;
	
	
	public void loadLandingPage(String url) {
		driver.get(url);
	}
	
	public ProductCatalog loginApplication(String email, String pass) {
		username.sendKeys(email);
		password.sendKeys(pass);
		login.click();
		return new ProductCatalog(driver);
	}
	
	public String getErrorMessage() {
		WaitForElementToAppear(errorMessage);
		String errorText = errorMessage.getText();
		return errorText;
	}
	
	

}
