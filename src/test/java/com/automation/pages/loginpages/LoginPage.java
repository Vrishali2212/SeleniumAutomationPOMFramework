package com.automation.pages.loginpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.BasePage;

public class LoginPage extends BasePage {
	
	@FindBy(id="username") WebElement usernameE ; 
	@FindBy(id="password") WebElement passwordE ; 
	@FindBy(id="Login") WebElement loginButtonE ;
	@FindBy(xpath="/html/body/div[1]/div[1]/div/div/div[2]/div[3]/form/div[1]") WebElement blankPasswordErrorMsgE;
	@FindBy(linkText="Forgot Your Password?") WebElement forgotPasswordE ; 
	@FindBy(id="error") WebElement invalidLoginErrorE ; 
	@FindBy(xpath="//label[text()='Remember me']") WebElement rememberMeCheckbox ; 
	
	public LoginPage(WebDriver driver) {
		super(driver) ; 
	}
	public String getLoginPageTitle() {
		return getPageTitle();
	}
	
	public void enterUserName(String usernamedata) {
		enterText(usernameE, usernamedata) ; 
	}
	
	public void enterPassword(String passworddata) {
		enterText(passwordE , passworddata) ; 
	}
	public WebDriver clickLoginButton() {
		clickButton(loginButtonE, "Login Button");
		return driver;
	}
	
	public String returnBlankPasswordErrorMessage() {
		return blankPasswordErrorMsgE.getText();
	}
	public WebDriver clickForgotPasswordLink() {
		clickButton(forgotPasswordE, "Forgot Password link");
		return driver;
	}
	public String getInvalidLoginErrorText() {
		return invalidLoginErrorE.getText();
	}
	public void checkRememberMe() {
		clickCheckBox(rememberMeCheckbox);
	}
	public boolean isRememberMeChecked() {
		System.out.println("Checkbox selected? " + rememberMeCheckbox.isSelected());
		if (rememberMeCheckbox.isSelected()) {
			System.out.println("Remember Me Checkbox is already selected. ");
			return true;
			
		}
		else {
			System.out.println("Remember Me Checkbox is not selected ");
			return false;
			
		}
	}
	public String getUserName() {
		return usernameE.getText();
	}
	

}
