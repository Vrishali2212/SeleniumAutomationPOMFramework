package com.automation.pages.loginpages;

import org.openqa.selenium.WebDriver;

import com.automation.pages.BasePage;

public class CheckYourEmail extends BasePage{
public  CheckYourEmail (WebDriver driver) {
	super(driver) ; 
}

public String getThisPageTitle() {
	return getPageTitle();
}
}
