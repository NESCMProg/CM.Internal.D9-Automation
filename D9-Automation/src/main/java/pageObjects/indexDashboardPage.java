package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.base;

public class indexDashboardPage extends base {

	@FindBy(linkText= "Index Page")
	WebElement indexDashboard_IndexPageLink;
	
	@FindBy(linkText= "Index Entries")
	WebElement indexDashboard_IndexEntryLink;
	
	
	public indexDashboardPage() {
		PageFactory.initElements(driver, this);
	}
	
	public indexPagesViewPage clickOnDashToIndexPage() {
		indexDashboard_IndexPageLink.click();
		return new indexPagesViewPage();
	}
	
	public indexEntriesViewPage clickOnDashToIndexEntryPage() {
		indexDashboard_IndexEntryLink.click();
		return new indexEntriesViewPage();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
