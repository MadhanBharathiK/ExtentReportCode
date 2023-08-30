package extentReport_MTA;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.TakesScreenshot;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class mtaExtentReport {
	 WebDriver driver;
	 ExtentReports extent; // Declare the ExtentReports instance as a static variable

	@BeforeClass
	public  void set() {
		
		 extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("sampleSt.html");
		extent.attachReporter(spark);
		
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Demo Extent Report");
		extent.attachReporter(spark);
		
		
		
		
		ExtentTest setuptest= extent.createTest("Launch browser").assignAuthor("Madhan").assignCategory("Smoke").assignDevice("Chrome");
		
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\USER\\Desktop\\ChromeDriver jar\\chromedriver-win32\\chromedriver.exe");

		
		driver=new ChromeDriver();
		driver.get("http://localhost:4200/auth/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	}
	
	@Test(priority=0)
	public void VerifyTitle() {
	//Check Title :
		String LoginActualTitle = driver.getTitle();
		String LoginExpectedTitle = "MTA Architects";
		Assert.assertEquals(LoginExpectedTitle, LoginActualTitle,"Title Not matched in Login page");
		ExtentTest titleTest= extent.createTest("Title test").assignAuthor("Madhan").assignCategory("Smoke");
		titleTest.pass("Title Matched");
	}
	
	//Login:
	@Test(priority=1)

	public void verifyLogin() throws InterruptedException, IOException {
		//Enter username & password:
		WebElement user = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-auth[1]/app-login[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[3]/input[1]"));
		user.sendKeys("accounts@multitechcorp.in");
		
		//password:
		WebElement enterpass = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-auth[1]/app-login[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[4]/input[1]"));
		enterpass.sendKeys("mta@1234");
		
		//Login click:
		WebElement clickLogin = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-auth[1]/app-login[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]"));
		Assert.assertTrue(clickLogin.isEnabled(), "Login button is not Enabled");
		clickLogin.click();
		ExtentTest logintest= extent.createTest("logintest ").assignAuthor("Madhan").assignCategory("Smoke");
		
		
		
		
		
		
		System.out.println("Login Successfull");
			Thread.sleep(3000);
	}
		
	//CLICK ADD TEAM BTN:
			@Test(priority=3)
			public void ClickDashboardAddTeamBtn() {
				
				//VREIFY HOME PAGE URL:
				String excpectedHomeURL = driver.getCurrentUrl();
				String actualHomeURL ="http://localhost:4200/admin/dashboard?page=1";
				Assert.assertEquals(actualHomeURL, excpectedHomeURL,"HomePage Not launched");
				
				//VERIFY DASHBOARD ADD TEAM BUTTON:
				WebElement DashboardAddTeam = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-super-admin[1]/div[1]/div[1]/div[1]/app-teams[1]/div[1]/div[1]/div[1]/div[4]/button[1]"));
				Assert.assertTrue(DashboardAddTeam.isEnabled(), "DashboardAddTeam button is not enabled");
				DashboardAddTeam.click();
			}
			
			//CREATE NEW TEAM:
			@Test(priority=4 )
			public void CreateNewTeam() throws InterruptedException {
				//Enter Team Name :
			WebElement enterTeamName = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-super-admin[1]/div[1]/div[1]/div[1]/app-teams[1]/div[1]/div[1]/div[7]/div[1]/div[1]/div[3]/form[1]/div[1]/div[1]/input[1]"));
			enterTeamName.sendKeys("Test Automation Team");
			
			ExtentTest NewTeamTest= extent.createTest("NewTeamTest ").assignAuthor("Madhan").assignCategory("Sanity");

			
			
			//Enter Descr:
			WebElement enterTeamDes = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-super-admin[1]/div[1]/div[1]/div[1]/app-teams[1]/div[1]/div[1]/div[7]/div[1]/div[1]/div[3]/form[1]/div[1]/div[2]/textarea[1]"));
			enterTeamDes.sendKeys("Automation testing team");
			
			//CLICK ADD TEAM:
			WebElement clickTeamSumbit = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-super-admin[1]/div[1]/div[1]/div[1]/app-teams[1]/div[1]/div[1]/div[7]/div[1]/div[1]/div[3]/div[1]/button[1]"));
			Assert.assertTrue(clickTeamSumbit.isEnabled(), "clickTeamSumbit is not Enabled");
			clickTeamSumbit.click();
			System.out.println("New Team Created Successfully");
			
			NewTeamTest.fail("team created successfully");
			
			driver.navigate().refresh();
			Thread.sleep(7000);
			}
			
			//ADD USER INTO TEAM:
			@Test(priority=5)
			public void ClickThreeDotForAddTeamUser() throws InterruptedException {
				WebElement createdTeamDot = driver.findElement(By.xpath("//*[@id=\"content-togg\"]/div/app-teams/div/div/div[2]/div/div[9]/div/i"));
				createdTeamDot.click();
				Assert.assertTrue(createdTeamDot.isEnabled(), "ThreeDot btn is not enbaled");
				Thread.sleep(3000);
				ExtentTest AddTeamUserDot= extent.createTest("AddTeamUserDot ").assignAuthor("Ajith").assignCategory("Sanity");
				AddTeamUserDot.info("ThreeDot Clicked Successfully");
				
				//Add User:
				WebElement ClickAddUser = driver.findElement(By.xpath("//button[text()='Add users']"));
				ClickAddUser.click();
				Assert.assertTrue(ClickAddUser.isEnabled(),"ClickAddUser button is not enabled ");
				Thread.sleep(5000);
				
				//Click/Select UserRole Dropdown:
				WebElement UserDropDown = driver.findElement(By.xpath("//select[@formcontrolname='userName']"));
				Select selectdrop = new Select(UserDropDown);
				selectdrop.selectByVisibleText("STAFF");
				
				//Click Select UserName:
				WebElement clickUserCheckBox = driver.findElement(By.xpath("//*[@id=\"adduserModal\"]/div/div/div[2]/form/div[1]/div[2]/div/table/tbody/tr[1]/td[3]/input"));
				clickUserCheckBox.click();
				Thread.sleep(3000);
				
				//Scroll To End of the page:
				JavascriptExecutor ScrollToEndUser = (JavascriptExecutor) driver;
				ScrollToEndUser.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-super-admin[1]/div[1]/div[1]/div[1]/app-teams[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[25]/td[3]/input[1]")));
				
				//Click Add Btn after Selecting User:
				WebElement AddSelectedUser = driver.findElement(By.xpath("//button[text()='Add']"));
				AddSelectedUser.click();
				ExtentTest UserAdded= extent.createTest("UserAdded ").assignAuthor("Ajith").assignCategory("ReTest");
				UserAdded.warning("Check the user in team");
				Thread.sleep(3000);
			}
				
				
				//CLICK CREATED TEAM THREEDOT TO ADD PROJECT:
			@Test(priority=6)
			public void ClickThreeDotForAddProject () throws InterruptedException {
				WebElement createdTeamDot = driver.findElement(By.xpath("//*[@id=\"content-togg\"]/div/app-teams/div/div/div[2]/div/div[9]/div/i"));
				createdTeamDot.click();
				Assert.assertTrue(createdTeamDot.isEnabled(),"createdTeamDot is not enabled ");
				Thread.sleep(3000);
				
			}
			
			
			//CLICK ADDPROJECT BUTTON after threeDOT
			
			@Test(priority=7)
			public void ClickAddProject() throws InterruptedException {
				WebElement clickAddPro = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/button[1]"));
				clickAddPro.click();
				Assert.assertTrue(clickAddPro.isEnabled(),"ClickAdd Project button is not enabled");
				Thread.sleep(5000);
				ExtentTest clickAddPr= extent.createTest("clickAddPr ").assignAuthor("Ajith").assignCategory("ReTest");

			}
			
	
	
		@AfterClass
		public void tear() {
			driver.quit();
			extent.flush();
		}
	
		

		}
		
		

		
			
		
	
	
	
	
	
	
    
    
   
  
   
    	
    	
    
    	
    
    	
    
