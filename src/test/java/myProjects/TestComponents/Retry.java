package myProjects.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int initial = 0;
	int maximun = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		if(initial<maximun) {
			initial++;
			return true;
		}
		
		return false;
	}

}
