package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.base;

public class indexEntriesViewPage extends base {
	
	@FindBy(xpath="//a[contains(text(),'Add New Entry')]")
	WebElement addNewEntry;
	
	@FindBy(id="edit-title")
	WebElement indexEntryTitle;
	
	@FindBy(xpath="//input[@id='edit-field-index-entry-url-und-0-value']")
	WebElement indexEntry_URL;
	
	@FindBy(xpath="//select[@id='edit-field-index-page-und']")
	WebElement indexPagedropdown;
	
	@FindBy(css="#edit-field-workflow-und-0-workflow-workflow-comment")
	WebElement indexEntryWorkFlowcmt;
	
	@FindBy(xpath="//input[@id='edit-submit']")
	WebElement submitBtn;
	
	
	public indexEntriesViewPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	
	

}
