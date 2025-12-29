package myProjects.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import myProject.PageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		Properties property = new Properties();
		FileInputStream input = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\myProjects\\Resources\\GlobalData.properties");
		property.load(input);
		
		String browserName =System.getProperty("browser")!=null ? System.getProperty("browser") : property.getProperty("browser");
//		String browserName = property.getProperty("browser");

		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			
			if(browserName.contains("headless")) {
			options.addArguments("headless");
			options.addArguments("--window-size=1920,1080");
			}
			driver = new ChromeDriver(options);
			
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.loadLandingPage("https://rahulshettyacademy.com/client");
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void closeApplication() {
		if(driver != null) {
			driver.quit();
		}

	}
	
	public List<HashMap<String, String>> getJSONData(String path) throws IOException {
		
		File file = new File(path);
		String fileToString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<HashMap<String, String>>> typeReference = new TypeReference<List<HashMap<String, String>>>(){};
		List<HashMap<String, String>> list = mapper.readValue(fileToString, typeReference);
		return list;
	}
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File sourceFile = screenShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")+"\\Screenshots\\"+testCaseName+".png");
		FileUtils.copyFile(sourceFile, destFile);
		return System.getProperty("user.dir")+"\\Screenshots\\"+testCaseName+".png";
	}


}
