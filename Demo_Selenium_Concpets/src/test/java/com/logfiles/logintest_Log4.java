package com.logfiles;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class logintest_Log4 {
	/*
	 What is log? Capturing info/ activities at the time of program execution 
	 //types of log:
	  * 1. info
	  * 2.warn
	  * 3.error
	  * 4.fatal
	  
	  * How to generate the logs? Use Apache log4j API (log4j.jar)
	  
	  *HOW it works? it reads log4j configuration from log4j.properties file (This is most important to generate logs)
	  
	  * Where to create? Create inside resources folder
	  * 
	 */

	WebDriver driver;
	Logger log = Logger.getLogger(logintest_Log4.class);
	@BeforeMethod
	public void setup() {
		
		 System.setProperty("webdriver.chrome.driver","C:\\Users\\USER\\Desktop\\ChromeDriver jar\\chromedriver.exe");
		driver=new ChromeDriver();
		log.info("launching chrome browser");
		driver.get("http://localhost:4200/auth/login");
		
		log.info("Entering application url");
		
		log.warn("this is just warning");
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
	
	@Test(priority=0)
	public void VerifyTitle() {
		//Check Title :
			String LoginActualTitle = driver.getTitle();
			String LoginExpectedTitle = "MTA Architects";
			log.info("loginpage title"+LoginActualTitle);
	}
	
	@Test(priority=1)
	public void verifyLogo() {
		boolean logos= driver.findElement(By.xpath("//img[@class='logo']")).isDisplayed();
		
		
	}
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
