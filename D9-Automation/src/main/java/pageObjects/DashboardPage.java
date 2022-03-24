package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.base;

public class DashboardPage extends base {
	@FindBy(xpath="//a[contains(text(),'Dashboard')]")
	WebElement dashboardLink;
	
	@FindBy(linkText="Callouts")
	WebElement calloutLink;
		
	@FindBy(linkText= "Custom blocks")
	WebElement customBlockLink;
	
	@FindBy(linkText= "Resource Flows")
	WebElement resourceFlowLink;
	
	@FindBy(linkText= "News articles")
	WebElement newsArticlesLink;
	
	@FindBy(linkText= "Database listings")
	WebElement databaseListingsLink;
	
	@FindBy(linkText= "Directory listings")
	WebElement directoryListingsLink;
	
	@FindBy(xpath="//div[@data-id='ds-50000-pages-stacks-dashboardtilesstacks-pages']/div[1]/h4/a")
	WebElement pagesLink;
	
	@FindBy(linkText= "Reserves")
	WebElement reservesLink;
	
	@FindBy(linkText= "Research guides")
	WebElement researchGuidesLink;
	
	@FindBy(linkText="Index pages")
	WebElement indexPagesLink;
	

	
	public DashboardPage() {
		PageFactory.initElements(driver, this);
	}
 
	//Action
	public String verifyDashboardPageTitle() {
		return driver.getTitle();
	}
	

	public calloutViewPage clickOnCalloutsLink() {
		System.out.println("This is dashboard page inside POM");
		calloutLink.click();
		return new calloutViewPage();
	}
	
	public resourceViewPage clickOnResourceFlowLink() {
		System.out.println("This is dashboard page inside POM");
		resourceFlowLink.click();
		return new resourceViewPage();
	}
	
	public CustomBlockViewPage clickOnCustomBlockLink() {
		customBlockLink.click();
		return new CustomBlockViewPage();
	}
	
	public  NewsArticleViewPage clickOnNewsArticlesLink() {
		System.out.println("This is dashboard page inside POM");
		newsArticlesLink.click();
		return new NewsArticleViewPage() ;
	}
	
	public DataBaseListingsViewPage clickOnDatabaseListingLink() {
		databaseListingsLink.click();
		return new DataBaseListingsViewPage();
		
	}
	
	public directoryListingsViewPage clickOnDirectoryListingsLink() {
		directoryListingsLink.click();
		return new directoryListingsViewPage();
	}
	
	public pagesViewPage clickOnPagesLink() {
		pagesLink.click();
		return new pagesViewPage();
	}
	
	public reservesViewPage clickonReservesLink() {
		try {
			reservesLink.click();
		}
		catch(Exception e2) {
			
		}
		return new reservesViewPage();
	}
	
	public researchGuidesViewPage clickOnResearchGuideLink() {
		try {
			researchGuidesLink.click();
		}
		catch(Exception e1) {
			
		}
		return new researchGuidesViewPage();
	}
	
	public indexDashboardPage clickOnIndexPagesLink() {
		indexPagesLink.click();
		return new indexDashboardPage();
		
	}
	

	
}

