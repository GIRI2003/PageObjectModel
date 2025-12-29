package runnerFile;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(features = {"src/test/resources/features"},glue = {"stepDefinitions"},monochrome = true, 
				plugin = {"html:target/cucumber.html"}, tags = "@ErrorValidation")

public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
	
}
