package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import resources.base;
import utility.Log;

public class indexPagesViewPage extends base{
	
	@FindBy(xpath="//a[contains(text(),'Add New Index Page')]")
	WebElement addNewIndexPageBtn;
	
	@FindBy(id="edit-title")
	WebElement indexPageTitle;
	
	@FindBy(name="field_hide_title[und]")
	WebElement hideTitleChkBox;
	
	@FindBy(xpath="//select[@id='edit-field-workflow-und-0-workflow-workflow-sid']")
	WebElement ContentModeration;
	
	@FindBy(css="#edit-field-workflow-und-0-workflow-workflow-comment")
	WebElement indexPageWorkFlowcmt;
	
	@FindBy(xpath="//input[@id='edit-submit']")
	WebElement submitBtn;
	
	@FindBy(xpath="//div[@class='messages status']")
	WebElement grabIndexPageTitle;
	
	@FindBy(linkText="Dashboard")
	WebElement dashboard;
	
	@FindBy(xpath= "//span[contains(text(),'Index Pages')]")
	WebElement dashToIndexLink;
	
	@FindBy(xpath= "//span[contains(text(),'Index Page')]")
	WebElement indexDashboard_IndexPageLink;
	
	public indexPagesViewPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickNewIndexPageBtn(String C_indexPageTitle, String C_Node, String C_IPWF) throws InterruptedException {
		addNewIndexPageBtn.click();
		indexPageTitle.sendKeys(C_indexPageTitle);
		Select nodeState = new Select(ContentModeration);
		nodeState.selectByVisibleText(C_Node);
		indexPageWorkFlowcmt.sendKeys(C_IPWF);
		try {
			submitBtn.click();
			Log.info("clicked on SAVE button after providing details");
		}
		catch(Exception e) {
			//Log.error("Unable to find SAVE button after providing details");
		}
		System.out.println("New Index Page is created");
		Thread.sleep(2000);
		try {
			String indexPageTitle = grabIndexPageTitle.getText();
			System.out.println(indexPageTitle);
			Log.info("Status Message: "+indexPageTitle);
			Log.info("Successfully created a Page with title name: " +C_indexPageTitle);
		}
		catch(Exception e2) {
			
		}
		Thread.sleep(1000);
		dashboard.click();
		dashToIndexLink.click();
		indexDashboard_IndexPageLink.click();
		
	}
	
	public void provideIndexPageTitle() {
		
	}

}
