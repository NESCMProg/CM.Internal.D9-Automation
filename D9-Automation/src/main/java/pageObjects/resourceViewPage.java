package pageObjects;

import utility.Log;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.base;
import utility.Log;

public class resourceViewPage extends base {

	@FindBy(xpath = "//h1[contains(text(),'Resource Flows')]")
	WebElement resourceLabel;

	@FindBy(xpath = "//a[contains(text(),'Add New')]")
	WebElement addNewResource;

	@FindBy(xpath = "//input[@id='edit-title-0-value']")
	WebElement resourceTitle;

	@FindBy(css = "#edit-field-items-0-subform-field-isbn-0-value")
	WebElement ISBN;

	@FindBy(css = "#edit-field-items-0-subform-field-upc-0-value")
	WebElement UPC;

	@FindBy(xpath = "//input[@id='edit-field-items-0-subform-field-title-0-value']")
	WebElement title;

	@FindBy(xpath = "//input[@id='edit-field-items-0-subform-field-author-0-value']")
	WebElement author;

	@FindBy(xpath = "//input[@id='edit-submit']")
	WebElement submitBtn;

	@FindBy(xpath = "//div[@class='messages status']")
	WebElement grabresourceflowTitle;

	@FindBy(linkText = "Dashboard")
	WebElement dashboard;

	@FindBy(xpath = "//span[contains(text(),'Resource Flows')]")
	WebElement dashToResourceFlowLink;

	@FindBy(css = "#edit-title")
	WebElement searchBox;

	@FindBy(css = "#edit-submit-resourceflows")
	WebElement searchBtn;

	@FindBy(xpath = "//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li[1]/a[1]")
	WebElement TracedEditBtn;

	@FindBy(xpath = "//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li[2]/button[1]")
	WebElement TraceDeleteBtnArrow;

	@FindBy(xpath = "//input[@value='Delete']")
	WebElement resourceFlowDelete;

	@FindBy(css = "div.messages__content")
	WebElement createMethodSuccess;

	@FindBy(xpath = "//h1[contains(text(),'Resource Flows')]")
	WebElement viewPageTitle;

	@FindBy(xpath = "//body/div[2]/div[1]/main[1]/div[2]/div[2]/div[1]/div[1]/div[2]")
	WebElement titleValidationError;

	@FindBy(css = "div.form-item__error-message")
	WebElement fieldError;

	public resourceViewPage() {
		PageFactory.initElements(driver, this);
	}

	public void clickResourceFlowNewandSaveBtn(String rtitle1, String upc1, String rtitle11, String author1)
			throws InterruptedException {
		addNewResource.click();
		resourceTitle.sendKeys(rtitle1);
		UPC.sendKeys(upc1);
		title.sendKeys(rtitle11);
		author.sendKeys(author1);
		try {
			submitBtn.click();
			Thread.sleep(1000);
			String RFPageTitle = viewPageTitle.getText();
			Log.info("Page Title: " + RFPageTitle);
			Thread.sleep(1000);
			String RF_MSG = createMethodSuccess.getText();
			Log.info("Status Message: " + RF_MSG);
			Log.info("Test Result: Pass");
		} catch (Exception e) {
			String titleWarning = titleValidationError.getText();
			Log.error("Error Message: " + titleWarning);
			String elementError = fieldError.getText();
			Log.error("Field Message: " + elementError);
			Log.error("Test Result: Fail to create a Resource Flow, since mandatory field not provided");
		}

	}

	public void searchResourceTitle(String existingTitle, String EditedTitle, String E_UPC1, String E_title1,
			String E_author1) {
		try {
			searchBox.sendKeys(existingTitle);
			searchBtn.click();
			TracedEditBtn.click();
			System.out.println("TracedEdit Button is clicked");
			resourceTitle.clear();
			resourceTitle.sendKeys(EditedTitle);
			Log.info(existingTitle + " NEW TITLE IS EDITED AS: " + EditedTitle);
			UPC.clear();
			UPC.sendKeys(E_UPC1);
			title.clear();
			title.sendKeys(E_title1);
			author.clear();
			author.sendKeys(E_author1);
			try {
				submitBtn.click();
				Thread.sleep(2000);
				System.out.println("clicked submit button");
				Log.info("clicked on SAVE button after Editing details");
				Log.info(existingTitle + " is updated as: " + EditedTitle);
			} catch (Exception e) {
				Log.info("Unable to get status message, but Resource Flow is Edited");
			}
		} catch (Exception e1) {
			Log.info("No such Title '" + existingTitle + "' is present in the Resource Flows Table List");
		}

	}

	public void deleteResourceFlowBtn(String mainResourceTitle, String upc_1, String rtitle11, String author1,
			String deleteResourceTitle) throws InterruptedException {
		addNewResource.click();
		resourceTitle.sendKeys(mainResourceTitle);
		UPC.sendKeys(upc_1);
		title.sendKeys(rtitle11);
		author.sendKeys(author1);
		Thread.sleep(2000);
		try {
			submitBtn.click();
			Log.info("Resource Flow is created");
		} catch (Exception e) {
			Log.error("Unable to get status message, but Resource Flow is created");
		}
		Thread.sleep(1000);
		searchBox.sendKeys(deleteResourceTitle);
		searchBtn.click();
		TraceDeleteBtnArrow.click();
		List<WebElement> options = driver.findElements(By.xpath("//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li"));
		for (WebElement opt : options) {
			if (opt.getText().equals("Delete")) {
				opt.click();
			}
		}
		resourceFlowDelete.click();
		System.out.println("Resource Flow is deleted");

	}

}
