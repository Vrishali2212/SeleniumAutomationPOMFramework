package com.automation.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.SalesForceBase;
import com.automation.pages.homepages.HomePage;
import com.automation.pages.homepages.LeadsPage;
import com.automation.pages.homepages.Opportunities;
import com.automation.pages.homepages.OpportunitiesPipeline;
import com.automation.pages.loginpages.CheckYourEmail;
import com.automation.pages.loginpages.ForgotPasswordPage;
import com.automation.pages.loginpages.LoginPage;
import com.automation.utility.PropertiesUtility;

public class SalesForceAutomationPOMScript extends SalesForceBase {
	public LoginPage loginpage;
	public HomePage homepage ;
	CheckYourEmail checkyouremail;
	ForgotPasswordPage forgotpasswordpage ;
	Opportunities opp ;  
	OpportunitiesPipeline pipelinepage;
	LeadsPage leads;
	@Test
	public void validLoginToSalesForce() throws InterruptedException {
		PropertiesUtility prop = new PropertiesUtility();
		String myusername = prop.getPropertyValue("login.valid.userid");
		String mypassword = prop.getPropertyValue("login.valid.password") ; 
		
		LoginPage loginpage = new LoginPage(driver);
		String expectedLoginPageTitle = "Login | Salesforce";
		String actualLoginPageTitle= loginpage.getLoginPageTitle();
		Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle);
		
		loginpage.enterUserName(myusername);
		loginpage.enterPassword(mypassword);
		Thread.sleep(2000);
		driver = loginpage.clickLoginButton();
		log.info("Login button clicked");
		//exreport.logExtentPass("Login Button clicked.");
		
		
		 homepage = new HomePage(driver) ; 
		Thread.sleep(2000);
		String actualHomePageTitle = homepage.getHomePageTitle();
		String expectedHomePageTitle = "Home Page ~ Salesforce - Developer Edition" ; 
		System.out.println("Actual title =  " + actualHomePageTitle);
		Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
		log.info("PASS >> TEST CASE PASSED. ");
	}
	@Test

	public  void loginWithBlankPassword() throws InterruptedException {
		
		loginpage = new LoginPage(driver);
		String expectedLoginPageTitle = "Login | Salesforce";
		String actualLoginPageTitle= loginpage.getLoginPageTitle();
		Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle);
		
		PropertiesUtility prop = new PropertiesUtility();
		String myusername = prop.getPropertyValue("login.valid.userid");
		String mypassword = ""; 
		loginpage.enterUserName(myusername);
		loginpage.enterPassword(mypassword);
		driver = loginpage.clickLoginButton();
		log.info("Login button clicked");
	//	loginpage =  new LoginPage(driver);   
		String expectedError = "Please enter your password." ;
		Thread.sleep(4000);
		System.out.println("Actual error =" + loginpage.returnBlankPasswordErrorMessage());
		Assert.assertEquals(loginpage.returnBlankPasswordErrorMessage(), expectedError) ;
		
	}
	@Test
	
	public void rememberMe() throws InterruptedException {
		PropertiesUtility prop = new PropertiesUtility();
		String myusername = prop.getPropertyValue("login.valid.userid");
		String mypassword = prop.getPropertyValue("login.valid.password") ; 
		
		LoginPage loginpage = new LoginPage(driver);
		String expectedLoginPageTitle = "Login | Salesforce";
		String actualLoginPageTitle= loginpage.getLoginPageTitle();
		Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle);
		
		loginpage.enterUserName(myusername);
		loginpage.enterPassword(mypassword);
		Thread.sleep(3000);
		loginpage.checkRememberMe();
		Thread.sleep(3000);
		driver = loginpage.clickLoginButton();
		log.info("Login button clicked");
		//exreport.logExtentPass("Login Button clicked.");
		
		Thread.sleep(5000);
		HomePage homepage = new HomePage(driver) ; 
		Thread.sleep(2000);
		String actualHomePageTitle = homepage.getHomePageTitle();
		String expectedHomePageTitle = "Home Page ~ Salesforce - Developer Edition" ; 
		System.out.println("Actual title =  " + actualHomePageTitle);
		Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
		log.info("Home page loaded.  ");
		
		homepage.clickID();
		driver = homepage.clickLogout();
		Thread.sleep(5000);
		loginpage = new LoginPage(driver);
		Thread.sleep(3000);
		// note : Even though remember me checkbox selected, its return false. 
		Assert.assertEquals(loginpage.isRememberMeChecked(), true); 
		log.info("Remember Me checbox is checked.") ; 
		String actualUsername = loginpage.getUserName() ; 
		String expectedUsername = myusername ; 
		Assert.assertEquals(actualUsername, expectedUsername);
		log.info("PASS : Test case passed") ; 
		Thread.sleep(5000);
		
		
		
	}
	@Test
	
	public void  forgotPasswordA() {
		loginpage = new LoginPage(driver);
		String expectedLoginPageTitle = "Login | Salesforce";
		PropertiesUtility prop = new PropertiesUtility();
		String myusername = prop.getPropertyValue("login.valid.userid");
		String actualLoginPageTitle= loginpage.getLoginPageTitle();
		Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle);
		
		driver = loginpage.clickForgotPasswordLink() ; 
		
		forgotpasswordpage = new ForgotPasswordPage(driver) ;
		String expectedTitle = "Forgot Your Password | Salesforce" ; 
		String actualTitle = forgotpasswordpage.getForgotPasswordPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		
		forgotpasswordpage.enterUserName(myusername);
		driver = forgotpasswordpage.clickContinueButton();
		
		 checkyouremail  = new CheckYourEmail(driver);
		String expectedTitle2 = "Check Your Email | Salesforce";
		String actualTitle2= checkyouremail.getThisPageTitle();
		Assert.assertEquals(actualTitle2, expectedTitle2);
		 

		
	}
	
	@Test
	public  void forgotPassword_B() throws InterruptedException {
		PropertiesUtility prop = new PropertiesUtility();
		String myusername = prop.getPropertyValue("login.invalid.userid");
		String mypassword = prop.getPropertyValue("login.invalid.password") ; 
		
		 loginpage = new LoginPage(driver);
		String expectedLoginPageTitle = "Login | Salesforce";
		String actualLoginPageTitle= loginpage.getLoginPageTitle();
		Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle);
		
		loginpage.enterUserName(myusername);
		loginpage.enterPassword(mypassword);
		driver = loginpage.clickLoginButton();
		log.info("Login button clicked");
		Thread.sleep(3000);
		//exreport.logExtentPass("Login Button clicked.");
		
		String expectedErrorText = "Please check your username and password. If you still can't log in, contact your Salesforce administrator." ;
		String actualErrorText = loginpage.getInvalidLoginErrorText();
		Assert.assertEquals(actualErrorText, expectedErrorText);
	}
	@Test
	public void CreateOptyTC15() throws InterruptedException  {
		PropertiesUtility prop = new PropertiesUtility();
		String myusername = prop.getPropertyValue("login.valid.userid");
		String mypassword = prop.getPropertyValue("login.valid.password") ; 
		
	loginpage = new LoginPage(driver);
		String expectedLoginPageTitle = "Login | Salesforce";
		String actualLoginPageTitle= loginpage.getLoginPageTitle();
		Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle);
		
		loginpage.enterUserName(myusername);
		Thread.sleep(2000);
		loginpage.enterPassword(mypassword);
		driver = loginpage.clickLoginButton();
		log.info("Login button clicked");
		//exreport.logExtentPass("Login Button clicked.");
		
		Thread.sleep(5000);
		 homepage = new HomePage(driver) ; 
		Thread.sleep(2000);
		String actualHomePageTitle = homepage.getHomePageTitle();
		String expectedHomePageTitle = "Home Page ~ Salesforce - Developer Edition" ; 
		System.out.println("Actual title =  " + actualHomePageTitle);
		Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
		
		homepage.clickOpportunitiesTab();
		Thread.sleep(3000);
		
		closeLighteningDialog();
		log.info("Closed lightening dialog box.");
		
		 opp = new Opportunities(driver);
		
		
		
		String expectedTitle = "Opportunities: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(opp.getThisPageTitle(), expectedTitle);
		
		
		
		List<String> exp = new ArrayList<String> () ;
		exp.add("All Opportunitie") ;
		exp.add("Closing Next Month") ;
		exp.add("Closing This Month") ;
		exp.add("My Opportunities") ;
		exp.add("New This Week") ;
		exp.add("Recently Viewed Opportunities") ;
		exp.add("Won") ;
		
		for (String s : opp.getAllOpportunitiesNames()) {
			System.out.println(s);
		}
		
		//Assert.assertEquals(opp.getAllOpportunitiesNames(), exp);
	}
	
	public  void closeLighteningDialog() throws InterruptedException {
		Thread.sleep(10000);
				
				//driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='sessionserver']")));
				WebElement nothanks = driver.findElement(By.xpath("//input[@id='lexNoThanks']"));
				nothanks.click();
				log.info("No thanks button clicked") ;
				Thread.sleep(3000);
			WebElement sendE = driver.findElement(By.xpath("//div[@id='tryLexDialog']//input[@id='lexSubmit']")) ; 
			sendE.click();
				log.info("Send button closed. Lightening Dialogue box closed. ") ; 
			}
			
	@Test
	public  void CreateOptyTC17() throws InterruptedException {
		// Login to Salesforce
		PropertiesUtility prop = new PropertiesUtility();
		String myusername = prop.getPropertyValue("login.valid.userid");
		String mypassword = prop.getPropertyValue("login.valid.password") ; 
		
		 loginpage = new LoginPage(driver);
		String expectedLoginPageTitle = "Login | Salesforce";
		String actualLoginPageTitle= loginpage.getLoginPageTitle();
		Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle);
		
		loginpage.enterUserName(myusername);
		Thread.sleep(2000);
		loginpage.enterPassword(mypassword);
		driver = loginpage.clickLoginButton();
		log.info("Login button clicked");
		//exreport.logExtentPass("Login Button clicked.");
		
		Thread.sleep(5000);
		 homepage = new HomePage(driver) ; 
		Thread.sleep(2000);
		String actualHomePageTitle = homepage.getHomePageTitle();
		String expectedHomePageTitle = "Home Page ~ Salesforce - Developer Edition" ; 
		System.out.println("Actual title =  " + actualHomePageTitle);
		Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
		// Goto Opportunities tab 
		driver = homepage.clickOpportunitiesTab();
		Thread.sleep(3000);
		
		closeLighteningDialog();
		log.info("Closed lightening dialog box.");
		 opp = new Opportunities(driver);
		
		
		
		String expectedTitle = "Opportunities: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(opp.getThisPageTitle(), expectedTitle);
		
		driver = opp.clickPipelineLink();
		Thread.sleep(3000);
		
		 pipelinepage =  new OpportunitiesPipeline(driver) ; 
		
		String actualTitle = pipelinepage.getThisPageTitle();
		String expTitle = "Opportunity Pipeline ~ Salesforce - Developer Edition";
		
		Assert.assertEquals(actualTitle, expTitle);
		log.info("PASSED : Testcase passed ") ;
		
	}
	@Test
	
	public void CreateOptyTC18() throws InterruptedException {
		// Login to Salesforce
		PropertiesUtility prop = new PropertiesUtility();
		String myusername = prop.getPropertyValue("login.valid.userid");
		String mypassword = prop.getPropertyValue("login.valid.password") ; 
		
		 loginpage = new LoginPage(driver);
		String expectedLoginPageTitle = "Login | Salesforce";
		String actualLoginPageTitle= loginpage.getLoginPageTitle();
		Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle);
		
		loginpage.enterUserName(myusername);
		Thread.sleep(2000);
		loginpage.enterPassword(mypassword);
		driver = loginpage.clickLoginButton();
		log.info("Login button clicked");
		//exreport.logExtentPass("Login Button clicked.");
		
		Thread.sleep(5000);
		 homepage = new HomePage(driver) ; 
		Thread.sleep(2000);
		String actualHomePageTitle = homepage.getHomePageTitle();
		String expectedHomePageTitle = "Home Page ~ Salesforce - Developer Edition" ; 
		System.out.println("Actual title =  " + actualHomePageTitle);
		Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
		// Goto Opportunities tab 
		driver= homepage.clickOpportunitiesTab();
		Thread.sleep(3000);
		
		closeLighteningDialog();
		log.info("Closed lightening dialog box.");
		
		 opp = new Opportunities(driver);
		
		String expectedTitle = "Opportunities: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(opp.getThisPageTitle(), expectedTitle);
		
		driver = opp.clickStuckOpportunitiesLink();
		String expTitle2 = "Stuck Opportunities ~ Salesforce - Developer Edition";
		String actualTitle2 = driver.getTitle();
		Assert.assertEquals(actualTitle2, expTitle2);
		log.info("PASSED >> Stuck Opportunities page loaded");
		
		
	}
	@Test
	
	public void LeadsTC20() throws InterruptedException {
		PropertiesUtility prop = new PropertiesUtility();
		String myusername = prop.getPropertyValue("login.valid.userid");
		String mypassword = prop.getPropertyValue("login.valid.password") ; 
		
		 loginpage = new LoginPage(driver);
		String expectedLoginPageTitle = "Login | Salesforce";
		String actualLoginPageTitle= loginpage.getLoginPageTitle();
		Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle);
		
		loginpage.enterUserName(myusername);
		Thread.sleep(2000);
		loginpage.enterPassword(mypassword);
		driver = loginpage.clickLoginButton();
		log.info("Login button clicked");
		//exreport.logExtentPass("Login Button clicked.");
		
		Thread.sleep(5000);
		 homepage = new HomePage(driver) ; 
		Thread.sleep(2000);
		String actualHomePageTitle = homepage.getHomePageTitle();
		String expectedHomePageTitle = "Home Page ~ Salesforce - Developer Edition" ; 
		System.out.println("Actual title =  " + actualHomePageTitle);
		Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
		// Goto Opportunities tab 
		driver= homepage.clickLeadsTab();
		Thread.sleep(3000);
		
		closeLighteningDialog();
		log.info("Closed lightening dialog box.");
		
		 leads = new LeadsPage(driver) ; 
		
		String expectedTitle = "Leads: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(leads.getThisPageTitle(), expectedTitle);
		log.info("<< PASSED : TEST CASE PASSED; ");
		
		
	}
	@Test
	
	public void LeadsTC23() throws InterruptedException {
		PropertiesUtility prop = new PropertiesUtility();
		String myusername = prop.getPropertyValue("login.valid.userid");
		String mypassword = prop.getPropertyValue("login.valid.password") ; 
		
		 loginpage = new LoginPage(driver);
		String expectedLoginPageTitle = "Login | Salesforce";
		String actualLoginPageTitle= loginpage.getLoginPageTitle();
		Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle);
		
		loginpage.enterUserName(myusername);
		Thread.sleep(2000);
		loginpage.enterPassword(mypassword);
		driver = loginpage.clickLoginButton();
		log.info("Login button clicked");
		//exreport.logExtentPass("Login Button clicked.");
		
		Thread.sleep(5000);
		 homepage = new HomePage(driver) ; 
		Thread.sleep(2000);
		String actualHomePageTitle = homepage.getHomePageTitle();
		String expectedHomePageTitle = "Home Page ~ Salesforce - Developer Edition" ; 
		System.out.println("Actual title =  " + actualHomePageTitle);
		Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
		// Goto Opportunities tab 
		driver= homepage.clickLeadsTab();
		Thread.sleep(3000);
		
		closeLighteningDialog();
		log.info("Closed lightening dialog box.");
		
		 leads = new LeadsPage(driver) ; 
		
		String expectedTitle = "Leads: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(leads.getThisPageTitle(), expectedTitle);
		log.info("<< leads page loaded ");
		
		leads.selectViewFromDropDown("Today's Leads") ; 
		driver = leads.clickGoButton();
		
		Assert.assertEquals(driver.getTitle(), "Leads ~ Salesforce - Developer Edition");
		
		
		
	}
	}

