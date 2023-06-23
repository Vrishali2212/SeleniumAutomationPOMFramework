package com.automation.pages.homepages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.BasePage;

public class HomePage extends BasePage {
	@FindBy(id="userNavLabel") WebElement idE ; 
	@FindBy(xpath="//a[text()='Logout']") WebElement logoutE ; 
	@FindBy(xpath="//li[@id='Opportunity_Tab']/a") WebElement oppE ; 
	@FindBy(xpath="//li[@id='Lead_Tab']/a") WebElement leadsE ; 
	
	
	public HomePage(WebDriver driver) {
		super(driver) ; 
	}
	
	public String getHomePageTitle() {
		return getPageTitle();
	}
	public void clickID() {
		clickButton(idE, "Profile Name link");
	}
	public WebDriver clickLogout() {
		clickButton(logoutE, "Logout link");
		return driver;
	}
	public WebDriver clickOpportunitiesTab() {
		clickButton(oppE, "Opportunities Tab");
		return driver;
	}
	public WebDriver clickLeadsTab() {
		clickButton(leadsE, "Leads Tab");
		return driver;
	}
	
	
}
