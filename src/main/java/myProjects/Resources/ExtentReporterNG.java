package myProjects.Resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getExtentReportObject() {
		File file = new File(System.getProperty("user.dir")+"\\Reports\\report.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
		sparkReporter.config().setReportName("Web Automation Testing");
		sparkReporter.config().setDocumentTitle("Web Automation");
		
		ExtentReports reports = new ExtentReports();
		reports.attachReporter(sparkReporter);	
		reports.setSystemInfo("Tester", "Girinath A");
		return reports;
	}

}
