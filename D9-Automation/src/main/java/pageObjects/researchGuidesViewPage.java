package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import resources.base;
import utility.Log;

public class researchGuidesViewPage extends base {

	@FindBy(xpath="//a[contains(text(),'Add Research guide')]")
	WebElement addNewResearch;
	
	@FindBy(css="#edit-title-0-value")
	WebElement researchTitle;
	
	@FindBy(xpath="//select[@id='edit-field-type']")
	WebElement researchType;
	
	@FindBy(xpath="//input[@id='edit-field-tab-0-subform-field-title-0-value']")
	WebElement tabTitle_1;
	
	@FindBy(xpath="//input[@id='edit-field-tab-0-subform-field-url-alias-0-value']")
	WebElement tabURL_1;
	
	@FindBy(css="#edit-submit")
	WebElement submitBtn;
	
	@FindBy(css="#edit-title")
	WebElement searchBox;
	
	@FindBy(xpath="//tbody/tr[1]/td[6]/div[1]/div[1]/ul[1]/li[2]/button[1]")
	WebElement TraceDeleteBtnArrow;
	
	@FindBy(xpath="//tbody/tr[1]/td[6]/div[1]/div[1]/ul[1]/li[1]/a[1]")
	WebElement TracedEditBtn;
	
	@FindBy(css="#edit-submit-research-guides")
	WebElement searchBtn;
	
	@FindBy(xpath="//div[@class='messages status']")
	WebElement grabRResearchGuideTitle;
	
	@FindBy(linkText="Dashboard")
	WebElement dashboard;
	
	@FindBy(linkText= "Research guides")
	WebElement dashToResearchGuidesLink;
	
	@FindBy(xpath="//a[contains(text(),'View')]")
	WebElement viewBtn;
	
	@FindBy(xpath="//a[contains(text(),'Dashboard')]")
	WebElement dashboardLink;
	
	 @FindBy(css="div.name-and-slogan")
	 WebElement viewSiteSlogan;
	
	@FindBy(xpath="//input[@value='Delete']")
	WebElement researchGuideDelete;
	
	@FindBy(css="div.messages.messages-status")
	WebElement createMethodSuccess;
	
	@FindBy(xpath="//body/div[2]/div[1]/main[1]/div[2]/div[2]/div[1]/div[1]/div[2]")
	WebElement titleValidationError;
	
	@FindBy(css="div.form-item__error-message")
	WebElement fieldError;
	
	public researchGuidesViewPage() {
		PageFactory.initElements(driver, this);	
	}
	
	public void clickResearchNewandSaveBtn(String C_researchTitle, String C_termRG, String C_tabTitle_1, String C_tabURL_1) throws InterruptedException {
		try {
			addNewResearch.click();
		}
		catch(Exception e1) {
			
		}
		try{
			researchTitle.sendKeys(C_researchTitle);
		}
		catch(Exception e2){
		}
		
		researchType.click();
		Select termRG = new Select(researchType);
		termRG.selectByVisibleText(C_termRG);
		researchType.click();
		tabTitle_1.sendKeys(C_tabTitle_1);
		tabURL_1.sendKeys(C_tabURL_1);
		Thread.sleep(1000);
		try {
			submitBtn.click();
			Thread.sleep(1000);
			String RGPageSlogan = viewSiteSlogan.getText();
			Log.info("Page Title: "+RGPageSlogan);
			Thread.sleep(2000);
			String RG_MSG = createMethodSuccess.getText();
			Log.info("Status Message: "+RG_MSG);
			Log.info("Test Result: Pass");
		}
		catch(Exception e) {
			String titleWarning = titleValidationError.getText();
			Log.error("Error Message: "+titleWarning);
			String elementError = fieldError.getText();
			Log.error("Field Message: "+elementError);
			Log.error("Test Result: Fail to create a Research Guide, since mandatory field not provided");
		}
		
	}
	public void searchResearchGuideTitle(String existingResearchTitle, String E_researchTitle) {
		try {
			searchBox.sendKeys(existingResearchTitle);
			searchBtn.click();
			TracedEditBtn.click();
			researchTitle.clear();
			researchTitle.sendKeys(E_researchTitle);
			try {
				Thread.sleep(1000);
				submitBtn.click();
				System.out.println("clicked submit button");
				Log.info("clicked on SAVE button after Editing details");
				Log.info(existingResearchTitle+" is updated as: "+E_researchTitle);
			}
			catch(Exception e) {
				Log.info("Unable to get status message, but Research Guide is Edited");
			}
			
		}
		catch(Exception e1) {
			Log.info("No such Title '"+existingResearchTitle+ "' is present in the Research Guides Table List");
		}
		
	}
	
	public void deleteResearchGuideBtn(String RG_Title, String D_termRG, String D_tabTitle_1, String D_tabURL_1, String D_RGTitle) throws InterruptedException {
		addNewResearch.click();
		researchTitle.sendKeys(RG_Title);
		researchType.click();
		Select termRG = new Select(researchType);
		termRG.selectByVisibleText(D_termRG);
		researchType.click();
		tabTitle_1.sendKeys(D_tabTitle_1);
		tabURL_1.sendKeys(D_tabURL_1);
		Thread.sleep(1000);
		try {
			submitBtn.click();
			Log.info("Research Guide is created");
		}
		catch(Exception e) {
			
		}
		Thread.sleep(2000);
		dashboardLink.click();
		dashToResearchGuidesLink.click();
		searchBox.sendKeys(D_RGTitle);
		searchBtn.click();
		TraceDeleteBtnArrow.click();
		List<WebElement>options=driver.findElements(By.xpath("//tbody/tr[1]/td[6]/div[1]/div[1]/ul[1]/li"));
		for(WebElement opt: options) {
			if (opt.getText().equals("Delete")) {
				opt.click();
			}
		}

		researchGuideDelete.click();
		System.out.println("Research Guide is deleted");
		
	}
	
	
	}
	

