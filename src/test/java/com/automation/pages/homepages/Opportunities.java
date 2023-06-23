package com.automation.pages.homepages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.automation.pages.BasePage;

public class Opportunities extends BasePage {
	@FindBy (id="fcf") WebElement viewDropDown ; 
	@FindBy (xpath="//div[@class='lbBody']//a[text()='Opportunity Pipeline']") WebElement pipelineE ; 
	@FindBy(xpath="//div[@class='lbBody']//a[text()='Stuck Opportunities']") WebElement stuckOpportunites ; 
	
	
	
	public Opportunities(WebDriver driver) {
		super(driver) ; 
	}
	
	public String getThisPageTitle() {
		return getPageTitle();
	}
	public List<String> getAllOpportunitiesNames() {
		List<String> oppnames = new ArrayList<String> (); 
		Select opp = new Select(viewDropDown) ;
		List<WebElement> opps = opp.getOptions();
		Iterator<WebElement> it = opps.iterator();
		while (it.hasNext()) {
			WebElement w = it.next();
			oppnames.add(w.getText()) ; 
		}
		
		return oppnames ;
	}
	public WebDriver clickPipelineLink() {
		clickButton(pipelineE, "Pipeline Link");
		return driver;
	}
	public WebDriver clickStuckOpportunitiesLink() {
		clickButton(stuckOpportunites, "Stuck Opportunities Link");
		return driver;
	}
	

}
