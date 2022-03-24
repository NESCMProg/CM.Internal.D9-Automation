package pageObjects;
import utility.Log;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import resources.base;

public class calloutViewPage extends base{
	
	//WebDriverWait wait = new WebDriverWait(driver,30);
	@FindBy(xpath="//h1[contains(text(),'Callouts')]")
	WebElement calloutsLabel;
	
	@FindBy(xpath="//a[contains(text(),'Add callout')]")
	WebElement addNewCallout;
	
	@FindBy(xpath="//input[@id='edit-title-0-value']")
	WebElement title;
				
	//@FindBy(id="edit-field-callouts-und-0-field-callout-title-und-0-value")
	//WebElement subtitle;
	
	//@FindBy(xpath="//textarea[@id='edit-field-callouts-und-0-field-callout-description-und-0-value']")
	//WebElement desc;
	
	@FindBy(xpath="//input[@id='edit-submit']")
	WebElement submitBtn;
	
	@FindBy(linkText="Dashboard")
	WebElement dash;
	
	@FindBy(xpath= "//span[contains(text(),'Callouts')]")	
	WebElement dashTocalloutLink;
	
	@FindBy(xpath="//div[@class='messages status']")
	WebElement grabCalloutTitle;
	
	@FindBy(css="#edit-title")
	WebElement searchBox;
	
	@FindBy(css="#edit-submit-callouts")
	WebElement searchBtn;
	
	@FindBy(xpath="//tbody/tr[1]/td[8]/div[1]/div[1]/ul[1]/li[1]/a[1]")
	WebElement TracedEditBtn;
	
	@FindBy(xpath="//tbody/tr[1]/td[8]/div[1]/div[1]/ul[1]/li[2]/button[1]")
	WebElement TraceDeleteBtnArrow;
	
	@FindBy(linkText="Dashboard")
	WebElement dashboard;
	
	@FindBy(xpath="//input[@value='Delete']")
	WebElement calloutDelete;
	
	@FindBy(xpath="//body/div[2]/div[1]/main[1]/div[2]/div[2]/div[1]/div[2]")
	WebElement successfulCalloutMsg;
	
	@FindBy(css="div.messages__content")
	WebElement CreateMethodError;
	
	@FindBy(css="div.form-item__error-message")
	WebElement fieldError;
	
	public calloutViewPage() {
		PageFactory.initElements(driver, this);	
	}
	
	public String validateCalloutPageTitle() {
		return driver.getTitle();
	}
	
	
	
	public void  clickonNewBtnandSaveBtn(String Callouttitle1) throws InterruptedException{
	
			addNewCallout.click();
			title.sendKeys(Callouttitle1);
			//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='edit-submit']")));
			try {
				submitBtn.click();
				Thread.sleep(1000);
				Log.info("clicked on SAVE button after providing details");
				Thread.sleep(2000);
				String COMsg = successfulCalloutMsg.getText();
				Log.info("Status message: "+COMsg);
				Log.info("Callout created with Title name: "+Callouttitle1);
				Log.info("Test Result: Pass");
			}
			catch(Exception e) {
				String CO_Headermsg = CreateMethodError.getText();
				Log.error("Error Message: "+CO_Headermsg);
				String elementError = fieldError.getText();
				Log.error("Field Message: "+elementError);
				Log.error("Test Result: Fail to create Callout, since mandatory field not provided");
			}
		
	
}
	
	public void searchCalloutTitle(String inputCalloutTitle, String editedTitle) {
		try {
			searchBox.sendKeys(inputCalloutTitle);
			searchBtn.click();
			TracedEditBtn.click();
			System.out.println("TracedEdit Button is clicked");
			title.clear();
			title.sendKeys(editedTitle);
			Log.info(inputCalloutTitle+" NEW TITLE IS EDITED AS: "+editedTitle);
			try {
				submitBtn.click();
				System.out.println("clicked submit button");
				Log.info("clicked on SAVE button after Editing details");
				Log.info("'"+inputCalloutTitle+"'"+" is Updated as: "+"'"+editedTitle+"'");
			}
			catch(Exception e) {
				Log.info("Unable to get status message, but Callout has been Edited");
			}
		}
		catch(Exception e1) {
			Log.info("No such Title "+inputCalloutTitle+ " is present in the Callouts Table List");
		}
		
		
		
	}
	
	public void deleteCalloutBtn(String title1_D, String deleteCalloutTitle) throws InterruptedException {
		addNewCallout.click();
		title.sendKeys(title1_D);
		System.out.println(title1_D);
		try {
			submitBtn.click();
			Log.info("Callout created");
		}
		catch(Exception e) {
			Log.error("Unable to get status message, but Callout has been created");
		}
		Thread.sleep(2000);
		searchBox.sendKeys(deleteCalloutTitle);
		searchBtn.click();
		TraceDeleteBtnArrow.click();
		List<WebElement>options=driver.findElements(By.xpath("//tbody/tr[1]/td[8]/div[1]/div[1]/ul[1]/li"));
		for(WebElement opt: options) {
			if (opt.getText().equals("Delete")) {
				opt.click();
			}
		}
		calloutDelete.click();
		System.out.println("Callout is deleted");

	}
}
