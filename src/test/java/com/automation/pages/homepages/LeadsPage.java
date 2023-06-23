package com.automation.pages.homepages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.automation.pages.BasePage;

public class LeadsPage  extends BasePage{
	
	@FindBy(xpath="//select[@id='fcf']") WebElement viewE ; 
	@FindBy(xpath="//select[@id='fcf']//following::input[@title='Go!']") WebElement goE ;
	
	
	public LeadsPage(WebDriver driver) {
		super(driver) ; 
	}
	
	public String getThisPageTitle() {
		return getPageTitle();
	}
	public void selectViewFromDropDown(String visibleText) {
		Select viewdropdown = new Select(viewE) ; 
		viewdropdown.selectByVisibleText(visibleText);
		
	}
	public WebDriver clickGoButton() {
		clickButton(goE,"Go Button");
		return driver;
	}
}
