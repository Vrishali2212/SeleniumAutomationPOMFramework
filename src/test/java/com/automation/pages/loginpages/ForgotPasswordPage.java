package com.automation.pages.loginpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.BasePage;

public class ForgotPasswordPage extends BasePage {
	@FindBy (xpath="//input[@id='un']") WebElement usernameE ; 
	@FindBy (id="continue") WebElement continueButtonE ; 
	public ForgotPasswordPage(WebDriver driver) {
		super(driver) ; 
	}
	public String getForgotPasswordPageTitle() {
		return getPageTitle();
	}
	public void enterUserName(String username ) {
		enterText(usernameE, username);
	}
	public WebDriver clickContinueButton() {
		clickButton(continueButtonE, "Continue Button");
		return driver;
	}
}
