package com.automation.base;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.tests.SalesForceAutomationScripts_old;
import com.automation.utility.ExtentReportUtility;
import com.automation.utility.PropertiesUtility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;


	


public class SalesForceBase {
	public static  WebDriver driver =null ;
	public  Logger log;
	public  ExtentReportUtility exreport ; 

	@BeforeSuite
	public  void beforeSuit() {
		log  =	LogManager.getLogger(SalesForceBase.class.getName())  ;
		exreport  = ExtentReportUtility.getInstance();
		System.out.println("logger initialized.");
		log.info("------------------ @beforeSuit started ---------------------------------") ;
		System.out.println("------------------ @beforeSuit started ---------------------------------") ;
		
	}
	
	@BeforeTest
	public  void beforeTest() {
		System.out.println("------------------ @beforeTest started ---------------------------------") ;
	}
	
	@BeforeMethod
	@Parameters("browserName")
	public  void beforeMethod( @Optional("Firefox")   String browName) {
		System.out.println("------------------ @beforeMethod started ---------------------------------") ;
		log.info("------------------ @beforeMethod started ---------------------------------") ;
		launchBrowser(browName ) ;
		PropertiesUtility property_utility = new PropertiesUtility();
		String url = property_utility.getPropertyValue("url");
		driver.get(url) ;   // to load website
		log.info("URL: "+url +" launched.") ; 
		//exreport  = ExtentReportUtility.getInstance();
		// exreport.logExtentInfo("URL Loaded : " +url);	
	}
	
	@AfterMethod
	public  void afterMethod() throws InterruptedException {
		Thread.sleep(10000) ;
		driver.close() ; 
		System.out.println("Inside @afterMethod");
		log.info("Driver closed.");
	}
	@AfterTest
	public void afterTest() {
		System.out.println("Inside @afterTest");
	}
	
	@AfterSuite
	public void afterSuit() {
		System.out.println("Inside @afterSute");
	}
	public  void launchBrowser(String bname) {
		switch(bname) 
		{
			case "Firefox" : WebDriverManager.firefoxdriver().setup();
							driver = new FirefoxDriver();
							driver.manage().window().maximize();
							break;
			case "chrome" : WebDriverManager.chromedriver().setup();
							driver = new ChromeDriver();
							driver.manage().window().maximize();
							break;	
		} 
		log.info("Browser "+bname+" launched. ") ; 
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ; 
	}
	public  void println(Object obj) {
		log.info(obj);
	}
	public  void print(Object obj) {
		System.out.print(obj);
	}
	
	
}
