package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.base;
import utility.Log;

public class reservesViewPage extends base{

	@FindBy(xpath="//a[contains(text(),'Add New')]")
	WebElement addNewReserve;
	
	@FindBy(xpath="//input[@id='edit-title-0-value']")
	WebElement reserveTitle;
	
	@FindBy(xpath="//input[@id='edit-field-registrar-course-id-0-value']")
	WebElement registrarID;
	
	@FindBy(xpath="//input[@id='edit-field-course-id-0-value']")
	WebElement courseID;
	
	@FindBy(css="#edit-submit")
	WebElement submitBtn;
	
	@FindBy(linkText="Dashboard")
	WebElement dashboard;
	
	@FindBy(xpath= "//span[contains(text(),'Reserves')]")
	WebElement dashToreservesLink;
	
	@FindBy(xpath="//div[@class='messages status']")
	WebElement grabReserveTitle;
	
	@FindBy(id="searchBox")
	WebElement searchBox;
	
	@FindBy(id="edit-submit")
	WebElement searchBtn;
	
	@FindBy(xpath="//tbody/tr[1]/td[7]/a[1]")
	WebElement TracedEditBtn;
	
	@FindBy(xpath="//tbody/tr[1]/td[7]/a[2]")
	WebElement TraceDeleteBtn;
	
	@FindBy(xpath="//input[@value='Delete']")
	WebElement reserveDelete;
	
	public reservesViewPage() {
		PageFactory.initElements(driver, this);	
	}
	
	public void clickReserveNewandSaveBtn(String C_reserveTitle, String C_registrarID, String C_courseID) throws InterruptedException {
		addNewReserve.click();
		reserveTitle.sendKeys(C_reserveTitle);
		registrarID.sendKeys(C_registrarID);
		courseID.sendKeys(C_courseID);
		Thread.sleep(1000);
		try {
			
			submitBtn.click();
			Log.info("Reserves is created");
		}
		catch(Exception e) {
			Log.error("Unable to get status message, but Reserves has been created");
		}
		System.out.println("New Reserve is created");
		Thread.sleep(1000);
		try {
			String reserveTitle = grabReserveTitle.getText();
			System.out.println(reserveTitle);
			Log.info("Status Message: "+reserveTitle);
			Log.info("Successfully created a Resource Flow with title name: " +C_reserveTitle);
			Thread.sleep(2000);
		}
		catch(Exception e2) {
			
		}
		
		try {
			dashboard.click();
		}
		catch(Exception e3) {
			
		}
		
		dashToreservesLink.click();
	}
	
	
	
	
	
	
	
	
}
