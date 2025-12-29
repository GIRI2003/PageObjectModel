package myProject.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import myProject.AbstractComponents.AbstractComponents;

public class PaymentPage extends AbstractComponents{

	WebDriver driver;
	
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css = ".ta-results")
	WebElement countrydropdown;
	
	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement selectmycountry;
	
	@FindBy(css = ".btnn")
	WebElement confirmButton;

	public void selectCountry(String mycountry) {
		country.sendKeys(mycountry);
		WaitForElementToAppear(countrydropdown);
		selectmycountry.click();
		
	}
	
	public ConfirmPage confirmButton() {
		WebElement clickable = confirmButton;
		JavaScriptExecution(clickable);
		return new ConfirmPage(driver);
	}
	
}
