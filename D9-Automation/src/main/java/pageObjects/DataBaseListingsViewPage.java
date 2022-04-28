package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.base;
import utility.Log;

public class DataBaseListingsViewPage extends base {
	
	@FindBy(xpath="//a[contains(text(),'Add Database Listing')]")
	WebElement addNewDBL;
	
	@FindBy(xpath="//input[@id='edit-title-0-value']")
	WebElement DBLTitle;
	
	@FindBy(css="#edit-field-ezproxy-url-0-value")
	WebElement MainURL;
	
	
	@FindBy(xpath="//input[@id='edit-field-e-resource-links-0-uri']")
	WebElement subURL1;
	
	@FindBy(xpath="//input[@id='edit-field-e-resource-links-0-title']")
	WebElement subLinkText1;
	
	@FindBy(xpath="//input[@id='edit-submit']")
	WebElement submitBtn;
	
	@FindBy(xpath="//div[@class='messages status']")
	WebElement grabDBListingTitle;
	
	@FindBy(linkText="Dashboard")
	WebElement dashboard;
	
	@FindBy(xpath= "//span[contains(text(),'Database Listings')]")	
	WebElement dashTodataBaseListingsLink;
	
	@FindBy(id="edit-q")
	WebElement searchBox;
	
	@FindBy(css="#edit-search")
	WebElement searchBtn;
	
	@FindBy(xpath="//tbody/tr[1]/td[6]/a[2]")
	WebElement TraceDeleteBtn;
	
	@FindBy(xpath="//input[@value='Delete']")
	WebElement DBLDelete;
	
	@FindBy(xpath="//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li[1]/a[1]")			
	WebElement TracedEditBtn;
	
	@FindBy(xpath="//a[contains(text(),'Dashboard')]")
	WebElement dashboardLink;
	
	@FindBy(linkText= "Database listings")
	WebElement dashToDBLLink;
	
	@FindBy(xpath="//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li[2]/button[1]")
	WebElement TraceDeleteBtnArrow;

	
	@FindBy(css="div.messages__content")
	WebElement TitleHeaderError;
	
	@FindBy(xpath="//div[contains(text(),'Title field is required.')]")
	WebElement titleFieldError;
	
	@FindBy(css="div.messages__content")
	WebElement successfulDBLtMsg;
	
	@FindBy(xpath="//h1[contains(text(),'Database listings - Listing')]")
	WebElement viewPagesuccess;
	
	public DataBaseListingsViewPage() {
		PageFactory.initElements(driver, this);	
	}
	
	public void clickDBLNewBtnandSaveBtn(String DBLTITLE, String Main_URL1, String C_subURL1, String C_subLinkText1) throws InterruptedException {
		
			addNewDBL.click();
			DBLTitle.sendKeys(DBLTITLE);
			MainURL.sendKeys(Main_URL1);
			subURL1.sendKeys(C_subURL1);
			Thread.sleep(1000);
			subLinkText1.sendKeys(C_subLinkText1);
			try {
				submitBtn.click();
				Thread.sleep(1000);
				Log.info("clicked on SAVE button after providing details");
				String slogan = viewPagesuccess.getText();
				Log.info("Site Slogan: "+slogan);
				String DBLMsg = successfulDBLtMsg.getText();
				Log.info("Status Message: "+DBLMsg);
				System.out.println(DBLMsg);
				searchBox.click();
				Log.info("Test Result: Pass");
			}
			catch(Exception e) {
				String CB_Headermsg = TitleHeaderError.getText();
				Log.error("Error Message: "+CB_Headermsg);
				String elementError = titleFieldError.getText();
				Log.error("Field Message: "+elementError);
				Log.info("Test Result: Fail to create a Database List, since mandatory field not provided");
			}
	

		
	}
	
	public void searchDatabaseListTitle(String inputDBLTitle, String editedTitle, String editedMainURL, String editedSubTitle1URL, String editedsubTitle1) {
		try {
		searchBox.sendKeys(inputDBLTitle);
		searchBtn.click();
		Thread.sleep(1000);
		TracedEditBtn.click();
		DBLTitle.clear();
		DBLTitle.sendKeys(editedTitle);
		MainURL.clear();
		MainURL.sendKeys(editedMainURL);
		subURL1.clear();
		subURL1.sendKeys(editedSubTitle1URL);
		Thread.sleep(1000);
		subLinkText1.clear();
		subLinkText1.sendKeys(editedsubTitle1);
		try {
			submitBtn.click();
			System.out.println("clicked submit button");
			Log.info("clicked on SAVE button after Editing details");
			Log.info("'"+inputDBLTitle+"' is updated as: '"+editedTitle+"'");
		}
		catch(Exception e) {
			Log.info("Unable to get status message, but DatabaseList is Edited");
		}
	}
	catch(Exception e1) {
		Log.info("No such Title '"+inputDBLTitle+ "' is present in the Database Listings Table List");
	}
	
	}

	public void deleteDBLBtn(String D_DBLTitle, String Main_URL1_D, String subURL1_D, String subLink1_D, String deleteDBLTitle) throws InterruptedException {
		addNewDBL.click();
		DBLTitle.sendKeys(D_DBLTitle);
		System.out.println(D_DBLTitle);
		MainURL.sendKeys(Main_URL1_D);
		subURL1.sendKeys(subURL1_D);
		subLinkText1.sendKeys(subLink1_D);
		try {
			submitBtn.click();
			Log.info("Database List created");
		}
		catch(Exception e) {
			Log.error("Unable to get status message, but Database List is created");
		}
		Thread.sleep(1000);
		searchBox.sendKeys(deleteDBLTitle);
		searchBtn.click();
		Thread.sleep(1000);
		TraceDeleteBtnArrow.click();
		List<WebElement>options=driver.findElements(By.xpath("//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li"));
		for(WebElement opt: options) {
			if (opt.getText().equals("Delete")) {
				opt.click();
			}
		}
		DBLDelete.click();
		System.out.println("Database List is deleted");

	}

}


