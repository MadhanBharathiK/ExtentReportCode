package com.ExtentReportListner;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;




public class ExtentReportNG {
	
	@Test
	public void extentTest() {
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("AutomationTest.html");
		extent.attachReporter(spark);
		
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Demo Extent Report");
		extent.attachReporter(spark);
		
		ExtentTest test = extent.createTest("Login Test").assignAuthor("Madhan").assignCategory("Smoke").assignDevice("Chrome");
		test.pass("Login test started successfully");
		test.info("URL is loaded");
		test.info("Value entered");
		test.pass("Login completed successfully");
		
		
		ExtentTest test1 = extent.createTest("Login Test").assignAuthor("Ajith").assignCategory("Sanity").assignDevice("FireFox");
		test1.pass("HomePage test started successfully");
		test1.info("URL is loaded");
		test1.info("Value entered");
		test1.pass("HomePage completed successfully");
		
		extent.flush();
	}
	
	
	
	
	
}
	
	
	

	
	

