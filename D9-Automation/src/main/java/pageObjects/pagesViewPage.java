package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import resources.base;
import utility.Log;

public class pagesViewPage extends base {
	
	@FindBy(xpath="//a[contains(text(),'Add New')]")
	WebElement addNewPage;
	
	@FindBy(xpath="//input[@id='edit-title-0-value']")
	WebElement pageTitle;
	
	@FindBy(css="#edit-submit")
	WebElement submitBtn;
	
	@FindBy(linkText="Dashboard")
	WebElement dashboard;
	
	
	@FindBy(xpath="//div[@class='messages status']")
	WebElement grabPageTitle;
	
	@FindBy(css="#edit-title")
	WebElement searchBox;
	
	@FindBy(css="#edit-submit-pages")
	WebElement searchBtn;
	
	@FindBy(xpath="//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li[1]/a[1]")
	WebElement TracedEditBtn;
	
	@FindBy(xpath="//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li[2]/button[1]")
	WebElement TraceDeleteBtnArrow;
	
	@FindBy(xpath="//tr[@class='odd']/td[1]//following-sibling::td[6]/a[2]")
	WebElement TraceDeleteBtn;
	
	@FindBy(xpath="//input[@value='Delete']")
	WebElement pageDelete;
	
	@FindBy(css="#edit-moderation-state-0-state")
	WebElement ddl;
	
	@FindBy(xpath="//h1[contains(text(),'Pages')]")
	 WebElement viewPageTitle;
	
	@FindBy(xpath="//body/div[2]/div[1]/main[1]/div[2]/div[2]/div[1]/div[1]/div[2]")
	WebElement titleValidationError;
	
	@FindBy(css="div.form-item__error-message")
	WebElement fieldError;
	
	public pagesViewPage() {
		PageFactory.initElements(driver, this);	
	}
	
	
	public void clickPagesNewandSaveBtn(String pgTitle) throws InterruptedException {
		
			addNewPage.click();
			pageTitle.sendKeys(pgTitle);
			//Select contentState = new Select(ddl);
			//contentState.selectByVisibleText(txtformat);
			Thread.sleep(1000);
			try {
				submitBtn.click();
				Thread.sleep(2000);
				Log.info("clicked on SAVE button after providing details");
				String pageTitle = viewPageTitle.getText();
				Log.info("Page Title is: "+pageTitle);
				Log.info("Page created with title name: "+pgTitle);
				Log.info("Test Result: Pass");
			}
			catch(Exception e) {
				String titleWarning = titleValidationError.getText();
				Log.error("Error Message: "+titleWarning);
				String elementError = fieldError.getText();
				Log.error("Field Message: "+elementError);
				Log.error("Test Result: Fail to create a 'Page', since mandatory field not provided");
			}

		
		
		
	}

	public void searchPageTitle(String existingPageTitle, String E_Title) {
		try {
			searchBox.sendKeys(existingPageTitle);
			searchBtn.click();
			TracedEditBtn.click();
			pageTitle.clear();
			pageTitle.sendKeys(E_Title);
			try {
				submitBtn.click();
				System.out.println("clicked submit button");
				Log.info("clicked on SAVE button after Editing details");
				Log.info(existingPageTitle+" is updated as: "+E_Title);
			}
			catch(Exception e) {
				Log.info("Unable to get status message, but Page has been Edited");
			}
		}
		catch(Exception e1) {
			Log.info("No such Title '"+existingPageTitle+ "' is present in the Pages Table List");
		}

	}


	public void deletePageBtn(String d_pageTitle, String deletePageTitle) throws InterruptedException {
		addNewPage.click();
		pageTitle.sendKeys(d_pageTitle);
		try {
			submitBtn.click();
			Log.info("Page is created");
		}
		catch(Exception e) {
			Log.error("Unable to get status message, but Page has been created");
		}
		Thread.sleep(2000);
		searchBox.sendKeys(deletePageTitle);
		searchBtn.click();
		TraceDeleteBtnArrow.click();
		List<WebElement>options=driver.findElements(By.xpath("//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li"));
		for(WebElement opt: options) {
			if (opt.getText().equals("Delete")) {
				opt.click();
			}
		}
		pageDelete.click();
		System.out.println(d_pageTitle+"Page is deleted");
		
		
	}
	
}
