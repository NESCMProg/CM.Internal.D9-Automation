package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import resources.base;
import utility.Log;

public class reservesViewPage extends base {

	@FindBy(xpath = "//a[contains(text(),'Add New')]")
	WebElement addNewReserve;

	@FindBy(xpath = "//input[@id='edit-title-0-value']")
	WebElement reserveTitle;

	@FindBy(xpath = "//select[@id='edit-langcode-0-value']")
	WebElement languageddl;

	@FindBy(xpath = "//input[@id='edit-field-registrar-course-id-0-value']")
	WebElement registrarID;

	@FindBy(xpath = "//input[@id='edit-field-course-id-0-value']")
	WebElement courseID;

	@FindBy(css = "#edit-field-course-date-0-value-date")
	WebElement startDate;

	@FindBy(css = "#edit-field-course-date-0-value-time")
	WebElement startTime;

	@FindBy(css = "#edit-field-course-date-0-end-value-date")
	WebElement endDate;

	@FindBy(css = "#edit-field-course-date-0-end-value-time")
	WebElement endTime;

	@FindBy(css = "#edit-field-items-0-subform-field-isbn-0-value")
	WebElement ISBN;

	@FindBy(css = "#edit-submit")
	WebElement submitBtn;

	@FindBy(linkText = "Dashboard")
	WebElement dashboard;

	@FindBy(xpath = "//span[contains(text(),'Reserves')]")
	WebElement dashToreservesLink;

	@FindBy(xpath = "//div[@class='messages status']")
	WebElement grabReserveTitle;

	@FindBy(css = "#edit-title")
	WebElement searchBox;

	@FindBy(css = "#edit-submit-reserves")
	WebElement searchBtn;

	@FindBy(xpath = "//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li[1]/a[1]")
	WebElement TracedEditBtn;

	@FindBy(xpath = "//tbody/tr[1]/td[7]/a[2]")
	WebElement TraceDeleteBtn;

	@FindBy(xpath = "//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li[2]/button[1]")
	WebElement TraceDeleteBtnArrow;

	@FindBy(xpath = "//input[@value='Delete']")
	WebElement reserveDelete;

	@FindBy(xpath = "//div[contains(text(),'No reserves have been created yet.')]")
	WebElement invalidTitle;

	@FindBy(xpath = "//input[@value='Delete']")
	WebElement newsReserveDelete;

	@FindBy(css = ".messages__content")
	WebElement createMethodSuccess;

	@FindBy(xpath = "//h1[contains(text(),'Reserves')]")
	WebElement viewPageTitle;

	// @FindBy(xpath="//div[@class='messages__content']")
	// WebElement createMethodErrors;

	@FindBy(xpath = "//body/div[2]/div[1]/main[1]/div[2]/div[2]/div[1]/div[1]/div[2]")
	WebElement createMethodErrors;

	@FindBy(css = "div.form-item__error-message")
	WebElement fieldError;

	public reservesViewPage() {
		PageFactory.initElements(driver, this);
	}

	public void clickReserveNewandSaveBtn(String C_reserveTitle, String C_Languauge, String C_registrarID,
			String C_courseID, String C_startDate, String C_startTime, String C_endDate, String C_endTime,
			String C_ISBN) throws InterruptedException {
		Thread.sleep(1000);
		addNewReserve.click();
		reserveTitle.sendKeys(C_reserveTitle);
		Select lanState = new Select(languageddl);
		lanState.selectByVisibleText(C_Languauge);
		registrarID.sendKeys(C_registrarID);
		courseID.sendKeys(C_courseID);
		startDate.sendKeys(C_startDate);
		System.out.println("startDate:" + C_startDate);
		startTime.sendKeys(C_startTime);
		System.out.println("startTime:" + C_startTime);
		endDate.sendKeys(C_endDate);
		System.out.println("endDate:" + C_endDate);
		endTime.sendKeys(C_endTime);
		System.out.println("endTime:" + C_endTime);
		ISBN.sendKeys(C_ISBN);
		Thread.sleep(1000);
		try {
			submitBtn.click();
			Thread.sleep(2000);
			String R_MSG = createMethodSuccess.getText();
			Log.info("Status Message: " + R_MSG);
			searchBox.sendKeys(C_reserveTitle);
			searchBtn.click();
			Log.info("Test Result: Pass");
		} catch (Exception e1) {
			String headerWarning = createMethodErrors.getText();
			Log.error("Error Message: " + headerWarning);

			int fieldMSG = driver.findElements(By.xpath("//div[@class='form-item__error-message']")).size();
			System.out.println("Console Print here : " + fieldMSG);
			for (int i = 1; i <= fieldMSG; i++) {
				try {
					String alerts = driver.findElement(By.xpath("//div[@class='form-item__error-message'][" + i + "]"))
							.getText();
					Log.error("Field Message: " + alerts);
				} catch (Exception e2) {
					String secondAlerts = driver
							.findElement(By.xpath("//tbody/tr[1]/td[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]"))
							.getText();
					Log.error("Field Message: " + secondAlerts);
				}

			}
			Log.info("Test Result: Fail to create a Reserve, since mandatory field not provided");
		}

	}

	public void searchReservesTitle(String existingReserveTitle, String E_reserveTitle) {
		try {
			searchBox.sendKeys(existingReserveTitle);
			searchBtn.click();
			TracedEditBtn.click();
			reserveTitle.clear();
			reserveTitle.sendKeys(E_reserveTitle);
			try {
				Thread.sleep(1000);
				submitBtn.click();
				System.out.println("clicked submit button");
				Log.info("clicked on SAVE button after Editing details");
				Log.info(existingReserveTitle + " is updated as: " + E_reserveTitle);
			} catch (Exception e) {
				Log.info("Unable to get status message, but Reserves is Edited");
			}

		} catch (Exception e1) {
			Log.info("No such Title '" + existingReserveTitle + "' is present in the Reserves Table List");
			// String noTitleResult = invalidTitle.getText();
			// Log.info(existingReserveTitle+" is "+noTitleResult);
		}
	}

	public void deleteReserveListBtn(String deletereserveTitle, String D_language, String D_registrarID,
			String D_courseID, String D_startDate, String D_startTime, String D_endDate, String D_endTime,
			String D_Reserve) throws InterruptedException {
		addNewReserve.click();
		reserveTitle.sendKeys(deletereserveTitle);
		Select lanState = new Select(languageddl);
		lanState.selectByVisibleText(D_language);
		registrarID.sendKeys(D_registrarID);
		courseID.sendKeys(D_courseID);
		startDate.sendKeys(D_startDate);
		startTime.sendKeys(D_startTime);
		endDate.sendKeys(D_endDate);
		endTime.sendKeys(D_endTime);
		Thread.sleep(1000);
		try {
			submitBtn.click();
			Log.info("Reserve List created");
		} catch (Exception e) {
			Log.error("Unable to get status message, but reserve List created");
		}
		Thread.sleep(2000);
		searchBox.sendKeys(D_Reserve);
		searchBtn.click();

		TraceDeleteBtnArrow.click();
		List<WebElement> options = driver.findElements(By.xpath("//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li"));
		for (WebElement opt : options) {
			if (opt.getText().equals("Delete")) {
				opt.click();
			}
		}
		newsReserveDelete.click();
		System.out.println("Reserve List is deleted");
	}

}
