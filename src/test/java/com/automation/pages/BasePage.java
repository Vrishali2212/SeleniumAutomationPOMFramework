package com.automation.pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.base.SalesForceBase;
import com.automation.utility.ExtentReportUtility;

public class BasePage {
	
	protected WebDriver driver; 
	protected WebDriverWait wait ; 
	protected Logger log;
	protected ExtentReportUtility exreport = ExtentReportUtility.getInstance();
	
	public BasePage(WebDriver driver) {
		this.driver = driver ; 
		System.out.println("Driver in basePage = "+driver);
		PageFactory.initElements(driver, this);
		log  =	LogManager.getLogger(SalesForceBase.class.getName())  ;
		
	}
	
	public  void enterText(WebElement element, String value) {
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(value) ; 
			log.info("Entered data in the webelement.");
			} 
			else {
				log.error("Element is not displayed.");
			}
	}
	
	public  void enterText(WebElement element, String value, String elementname) {
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(value) ; 
			log.info(elementname + " entered.");
			} 
			else {
				log.error("-- "+elementname + " is not displayed.");
			}
	}
	
	public  void clickButton(WebElement element, String elementname) 
	{
		if (element.isDisplayed()) {
			element.click(); 
			log.info(elementname + " clicked ");
			}
			else {
				log.error("-- "+elementname+"  not displayed.");
			}
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	public  void clickCheckBox(WebElement checkboxElement) {
		if (checkboxElement.isDisplayed()) {
			if (checkboxElement.isSelected()) {
				System.out.println("Checkbox is already selected. ");
				
			}
			else {
				System.out.println("Clicking the checkbox");
				checkboxElement.click();
				log.info("Checked the checkbox") ; 
				
			}
		}else {
			System.out.println("Checkbox not displayed. ");
		}
		
	}
	
	public  void selectRadioButton(WebElement radiobutton ) {
		if (radiobutton.isSelected()) {
			log.info("Radio button is already selected") ; 
		} else {
			radiobutton.click(); 
			log.info("Radio button selected.")  ; 
		}
	}
	public  void waitTillElementVisible(By locator) {
		log.info("Waiting for page element to be visible. ");
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class) ;
		fwait.until(ExpectedConditions.visibilityOfElementLocated(locator)) ; 
				}
	public  void waitTillElementIsClickable(By by) {
		log.info("Waiting for page element to be clickable.. ");
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class) ;
		fwait.until(ExpectedConditions.elementToBeClickable(by)) ; 
				}
	

	public  void waitTillPageLoads(String pageTitle) {
		log.info("waitTillPageLoads::Waiting for page with title - "+pageTitle+" to be loaded. ");
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class) ;
		fwait.until(ExpectedConditions.titleContains(pageTitle)) ; 
	}
	
	

}
