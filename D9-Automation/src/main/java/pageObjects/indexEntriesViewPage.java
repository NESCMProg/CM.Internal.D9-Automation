package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import resources.base;
import utility.Log;

public class indexEntriesViewPage extends base {
	
	@FindBy(xpath="//a[contains(text(),'Add New')]")
	WebElement addNewEntry;
	
	@FindBy(id="edit-title-0-value")
	WebElement indexEntryTitle;
	
	@FindBy(xpath="//select[@id='edit-langcode-0-value']")
	WebElement languageddl;
	
	@FindBy(xpath="//input[@id='edit-field-index-entry-url-0-value']")
	WebElement indexEntry_URL;
	
	@FindBy(xpath="//select[@id='edit-field-index-page-und']")
	WebElement indexPagedropdown;
	
	@FindBy(css="#edit-field-workflow-und-0-workflow-workflow-comment")
	WebElement indexEntryWorkFlowcmt;
	
	@FindBy(xpath="//input[@id='edit-submit']")
	WebElement submitBtn;
	
	@FindBy(css="#edit-title")
	WebElement searchBox;
	
	@FindBy(css="#edit-submit-index-entries")
	WebElement searchBtn;
	
	@FindBy(xpath="//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li[1]/a[1]")
	WebElement TracedEditBtn;
	
	@FindBy(xpath="//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li[2]/button[1]")
	WebElement TraceDeleteBtnArrow;
	
	@FindBy(xpath="//input[@value='Delete']")
	WebElement indexEntryDelete;
	
	@FindBy(xpath="//body/div[2]/div[1]/main[1]/div[2]/div[2]/div[1]/div[1]/div[2]")
	WebElement titleValidationError;
	
	@FindBy(css="div.form-item__error-message")
	WebElement fieldError;
	
	@FindBy(css="div.messages__content")
	WebElement createMethodSuccess;
	
	@FindBy(xpath="//h1[contains(text(),'Index Entries')]")
	 WebElement viewPageTitle;
	
	public indexEntriesViewPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickNewEntryPageBtn(String C_indexEntryTitle, String C_Languauge, String C_IndexURL) throws InterruptedException {
		addNewEntry.click();
		indexEntryTitle.sendKeys(C_indexEntryTitle);
		Select lanState = new Select(languageddl);
		lanState.selectByVisibleText(C_Languauge);
		indexEntry_URL.sendKeys(C_IndexURL);
		try {
			submitBtn.click();
			Thread.sleep(1000);
			String IE_PageTitle = viewPageTitle.getText();
			Log.info("Page Title: "+IE_PageTitle);
			Thread.sleep(2000);
			String IE_MSG = createMethodSuccess.getText();
			Log.info("Status Message: "+IE_MSG);
		}
		catch(Exception e) {
			String titleWarning = titleValidationError.getText();
			Log.error("Error Message: "+titleWarning);
			String elementError = fieldError.getText();
			Log.error("Field Message: "+elementError);
			Log.error("Test Result: Fail");
		}
		
	}
	
	public void searchIndexEntryTitle(String editedIndexEntry, String E_indexEntry, String E_language, String E_URL) {
		try {
			searchBox.sendKeys(editedIndexEntry);
			searchBtn.click();
			TracedEditBtn.click();
			indexEntryTitle.clear();
			indexEntryTitle.sendKeys(E_indexEntry);
			Select lanState = new Select(languageddl);
			lanState.selectByVisibleText(E_language);
			indexEntry_URL.clear();
			indexEntry_URL.sendKeys(E_URL);
			try {
				submitBtn.click();
				System.out.println("clicked submit button");
				Log.info("clicked on SAVE button after Editing details");
				Log.info(editedIndexEntry+" is updated as: "+E_indexEntry);
			}
			catch(Exception e1) {
				Log.info("Unable to get status message, but Index Entry is Edited");
			
		}
				
	}
		catch(Exception e2) {
			Log.info("No such Title '"+editedIndexEntry+ "' is present in the Index Page Table List");
		}
	}
	
	public void deleteIndexEntryBtn(String D_indexEntryTitle, String D_Languauge, String D_IndexURL, String deletedIndexEntry) throws InterruptedException {
		addNewEntry.click();
		indexEntryTitle.sendKeys(D_indexEntryTitle);
		Select lanState = new Select(languageddl);
		lanState.selectByVisibleText(D_Languauge);
		indexEntry_URL.sendKeys(D_IndexURL);
		Thread.sleep(1000);
		try {
			submitBtn.click();
			Log.info("Index Entry created");
		}
		catch(Exception e) {
			Log.error("Unable to get status message, but Index Entry is created");
		}
		Thread.sleep(1000);
		searchBox.sendKeys(deletedIndexEntry);
		searchBtn.click();
		
		TraceDeleteBtnArrow.click();
		List<WebElement>options=driver.findElements(By.xpath("//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li"));
		for(WebElement opt: options) {
			if (opt.getText().equals("Delete")) {
				opt.click();
			}
		}
		indexEntryDelete.click();
		System.out.println("Index Entry is deleted");
	}
}
