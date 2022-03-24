package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import resources.base;
import utility.Log;

public class indexPagesViewPage extends base{
	
	@FindBy(xpath="//a[contains(text(),'Add New')]")
	WebElement addNewIndexPageBtn;
	
	@FindBy(css="#edit-title-0-value")
	WebElement indexPageTitle;
	
	@FindBy(name="field_hide_title[und]")
	WebElement hideTitleChkBox;
	
	@FindBy(xpath="//select[@id='edit-langcode-0-value']")
	WebElement languageddl;
	
	@FindBy(css="#edit-submit")
	WebElement submitBtn;
	
	@FindBy(xpath="//div[@class='messages status']")
	WebElement grabIndexPageTitle;
	
	@FindBy(linkText="Dashboard")
	WebElement dashboard;
	
	@FindBy(xpath= "//span[contains(text(),'Index Pages')]")
	WebElement dashToIndexLink;
	
	@FindBy(xpath= "//span[contains(text(),'Index Page')]")
	WebElement indexDashboard_IndexPageLink;
	
	@FindBy(css="#edit-title")
	WebElement searchBox;
	
	@FindBy(css="#edit-submit-index-pages")
	WebElement searchBtn;
	
	@FindBy(xpath="//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li[1]/a[1]")
	WebElement TracedEditBtn;
	
	@FindBy(xpath="//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li[2]/button[1]")
	WebElement TraceDeleteBtnArrow;
	
	@FindBy(xpath="//input[@value='Delete']")
	WebElement indexPageDelete;
	
	@FindBy(xpath="//h1[contains(text(),'Index Pages')]")
	 WebElement viewPageTitle;
	
	@FindBy(xpath="//body/div[2]/div[1]/main[1]/div[2]/div[2]/div[1]/div[2]")
	WebElement createMethodSuccess;
	
	@FindBy(xpath="//body/div[2]/div[1]/main[1]/div[2]/div[2]/div[1]/div[1]/div[2]")
	WebElement titleValidationError;
	
	@FindBy(css="div.form-item__error-message")
	WebElement fieldError;
	
	public indexPagesViewPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickNewIndexPageBtn(String C_indexPageTitle, String C_Languauge) throws InterruptedException {
		addNewIndexPageBtn.click();
		indexPageTitle.sendKeys(C_indexPageTitle);
		Select lanState = new Select(languageddl);
		lanState.selectByVisibleText(C_Languauge);
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
	
	public void searchIndexPageTitle(String inputIndexTitle, String E_IndexTitle, String E_Languauge) {
		try {
			searchBox.sendKeys(inputIndexTitle);
			searchBtn.click();
			TracedEditBtn.click();
			indexPageTitle.clear();
			indexPageTitle.sendKeys(E_IndexTitle);
			Select lanState = new Select(languageddl);
			lanState.selectByVisibleText(E_Languauge);
			try {
				submitBtn.click();
				System.out.println("clicked submit button");
				Log.info("clicked on SAVE button after Editing details");
				Log.info(inputIndexTitle+" is updated as: "+E_IndexTitle);
			}
			catch(Exception e1) {
				Log.info("Unable to get status message, but Index Page is Edited");
			
		}
				
	}
		catch(Exception e2) {
			Log.info("No such Title '"+inputIndexTitle+ "' is present in the Index Page Table List");
		}
		
	}
	
	public void deleteIndexPageBtn(String D_indexPageTitle, String D_Languauge, String deletedIndexTitle) throws InterruptedException {
		addNewIndexPageBtn.click();
		indexPageTitle.sendKeys(D_indexPageTitle);
		Select lanState = new Select(languageddl);
		lanState.selectByVisibleText(D_Languauge);
		Thread.sleep(1000);
		try {
			submitBtn.click();
			Log.info("Index Page created");
		}
		catch(Exception e) {
			Log.error("Unable to get status message, but Index Page has been created");
		}
		Thread.sleep(1000);
		searchBox.sendKeys(deletedIndexTitle);
		searchBtn.click();
		
		TraceDeleteBtnArrow.click();
		List<WebElement>options=driver.findElements(By.xpath("//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li"));
		for(WebElement opt: options) {
			if (opt.getText().equals("Delete")) {
				opt.click();
			}
		}
		indexPageDelete.click();
		System.out.println("Index Page is deleted");
	}
}
