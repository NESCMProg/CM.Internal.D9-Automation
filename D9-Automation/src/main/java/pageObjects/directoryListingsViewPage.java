package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.base;
import utility.Log;

public class directoryListingsViewPage extends base {
	
	@FindBy(xpath="//a[contains(text(),'Add directory listings')]")
	WebElement addNewDirectory;
	
	@FindBy(xpath="//input[@id='edit-field-first-name-listing-0-value']")
	WebElement directoryName;
	
	@FindBy(xpath="//input[@id='edit-field-last-name-listing-0-value']")
	WebElement lastName;
	
	@FindBy(xpath="//input[@id='edit-field-job-title-listing-0-value']")
	WebElement jobTitle;
	
	@FindBy(xpath="//input[@id='edit-field-directory-location-0-value']")
	WebElement Location;
	
	@FindBy(xpath="//input[@id='edit-field-linkedin-link-0-uri']")
	WebElement linkedinURL;
	
	@FindBy(xpath="//input[@id='edit-field-linkedin-link-0-title']")
	WebElement linkedinText;
	
	@FindBy(css="#edit-submit")
	WebElement submitBtn;
	
	@FindBy(linkText="Dashboard")
	WebElement dashboard;
	
	@FindBy(xpath= "//span[contains(text(),'Directory Listings')]")	
	WebElement dashToDirectoryLink;
	
	@FindBy(xpath="//div[@class='messages status']")
	WebElement grabDirectoryTitle;
	
	@FindBy(css="#edit-title")
	WebElement searchBox;
	
	@FindBy(css="#edit-submit-directory-listings")
	WebElement searchBtn;
	
	@FindBy(xpath="//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li[1]/a[1]")
	WebElement TracedEditBtn;
	
	@FindBy(xpath="//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li[2]/button[1]")
	WebElement TraceDeleteBtnArrow;
	
	@FindBy(xpath="//input[@value='Delete']")
	WebElement directoryListingDelete;
	
	@FindBy(xpath="//div[@role='contentinfo']/div[2]")
	WebElement savedDLStatus;
	
	@FindBy(xpath="//div[@class='messages__content']")
	WebElement createMethodErrors;
	
	//@FindBy(css="div.form-item__error-message")
	//WebElement fieldValidation;
	
	//@FindBy(xpath="(//div[@class='form-item__error-message'])[2]")
	//WebElement lastNameValiadation;
	
	public directoryListingsViewPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickDirectoryNewandSaveBtn(String dirName, String secondtName, String job, String loc, String C_Linkedin, String C_LinkedinText) throws InterruptedException {
		try {
			addNewDirectory.click();
			directoryName.sendKeys(dirName);
			lastName.sendKeys(secondtName);
			jobTitle.sendKeys(job);
			Location.sendKeys(loc);
			Thread.sleep(1000);
			linkedinURL.sendKeys(C_Linkedin);
			linkedinText.sendKeys(C_LinkedinText);
			submitBtn.click();
			Thread.sleep(1000);
			Log.info("clicked on SAVE button after providing details");
			String DL_Msg = savedDLStatus.getText();
			Log.info("Status Message: "+DL_Msg);
			Log.info("Directory List saved with Title name: '"+dirName+" "+secondtName+"'");
			Log.info("Test Result: Pass");
		}
		catch(Exception e1) {
			String headerWarning = createMethodErrors.getText();
			Log.error("Error Message: "+headerWarning);
			
			int fieldMSG = driver.findElements(By.xpath("//div[@class='form-item__error-message']")).size();
			for(int i=1; i<=fieldMSG;i++) {
				try {
					String alerts = driver.findElement(By.xpath("//div[@class='form-item__error-message']["+i+"]")).getText();
					Log.error("Field Message: "+alerts);
				}
				catch(Exception e2) {
					String alertsSecond = driver.findElement(By.xpath("//div[contains(text(),'Last name/Secondary name field is required.')]")).getText();
					Log.error("Field Message: "+alertsSecond);
				}
			}
			Log.error("Directory Listing is not created: "+dirName+" "+secondtName);
			Log.info("Test Result: Fail to create a Directory List, since mandatory field not provided");
		}
		
	}

	public void searchDirectoryListTitle(String InputDirTitle1 ,String InputDirTitle2,  String E_FirstName, String E_lastName, String E_jobTitle, String E_Location, String E_LinkedinURL, String E_LinkedinText) {
		try {
			searchBox.sendKeys(InputDirTitle1+" "+InputDirTitle2);
			searchBtn.click();
			TracedEditBtn.click();
			directoryName.clear();
			directoryName.sendKeys(E_FirstName);
			lastName.clear();
			lastName.sendKeys(E_lastName);
			jobTitle.clear();
			jobTitle.sendKeys(E_jobTitle);
			Location.clear();
			Location.sendKeys(E_Location);
			linkedinURL.clear();
			linkedinURL.sendKeys(E_LinkedinURL);
			linkedinText.clear();
			linkedinText.sendKeys(E_LinkedinText);
			Thread.sleep(1000);
			try {
				submitBtn.click();
				System.out.println("clicked submit button");
				Log.info("clicked on SAVE button after Editing details");
				Log.info(InputDirTitle1+" "+InputDirTitle2+" is updated as: "+E_FirstName+" "+E_lastName);
			}
			catch(Exception e) {
				Log.info("Unable to get status message, but Directory List is Edited");
			}
		}
		catch(Exception e1) {
			Log.info("No such Title "+InputDirTitle1+" "+InputDirTitle2+ " is present in the Directory Listings Table List");
		}
	}

	public void deleteDirectoryListBtn(String D_directoryName, String D_lastName, String D_jobTitle, String D_Location, String D_Linkedin, String D_LinkedinText, String D_Directory1, String D_Directory2) throws InterruptedException {
		addNewDirectory.click();
		directoryName.sendKeys(D_directoryName);
		lastName.sendKeys(D_lastName);
		jobTitle.sendKeys(D_jobTitle);
		Location.sendKeys(D_Location);
		linkedinURL.sendKeys(D_Linkedin);
		linkedinText.sendKeys(D_LinkedinText);
		Thread.sleep(1000);
		try {
			submitBtn.click();
			Log.info("Directory List is created");
		}
		catch(Exception e) {
			Log.error("Unable to get status message, but Directory List has been created");
		}
		Thread.sleep(1000);
		searchBox.sendKeys(D_Directory1+" "+D_Directory2);
		searchBtn.click();
		Thread.sleep(1000);
		TraceDeleteBtnArrow.click();
		List<WebElement>options=driver.findElements(By.xpath("//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li"));
		for(WebElement opt: options) {
			if (opt.getText().equals("Delete")) {
				opt.click();
			}
		}
		directoryListingDelete.click();
		System.out.println("Directory Listing is deleted");
		
	}

}
