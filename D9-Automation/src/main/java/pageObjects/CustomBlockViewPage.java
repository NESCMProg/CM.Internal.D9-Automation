package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import resources.base;
import utility.Log;

public class CustomBlockViewPage extends base {

	@FindBy(xpath="//a[contains(text(),'Add custom block')]")
	WebElement addNewCustom;
	
	@FindBy(xpath="//input[@id='edit-title-0-value']")
	WebElement title;
	
	@FindBy(css="#edit-revision-log-0-value")
	WebElement Customlm;
	
	@FindBy(css="#edit-submit")
	WebElement submitBtn;
	
	@FindBy(xpath="//div[@class='messages status']")
	WebElement grabCustomTitle;
	
	@FindBy(linkText="Dashboard")
	WebElement dashboard;
	
	@FindBy(xpath= "//span[contains(text(),'Custom Blocks')]")	
	WebElement dashToCustomBlockLink;
	
	@FindBy(css="#edit-title")
	WebElement searchBox;
	
	@FindBy(css="#edit-submit-custom-blocks")
	WebElement searchBtn;
	
	@FindBy(xpath="//tbody/tr[1]/td[8]/div[1]/div[1]/ul[1]/li[2]/button[1]")
	WebElement TraceDeleteBtnArrow;
	
	@FindBy(xpath="//tbody/tr[1]/td[8]/div[1]/div[1]/ul[1]/li[1]/a[1]")
	WebElement TracedEditBtn;
	
	@FindBy(xpath="//input[@value='Delete']")
	WebElement customDelete;
	
	
	
	
	public CustomBlockViewPage() {
		PageFactory.initElements(driver, this);	
	}
	
	public void clickCustomNewBtnandSaveBtn(String title1, String C_RLM) throws InterruptedException {
		addNewCustom.click();
		title.sendKeys(title1);
		//TextFormat.selectByVisibleText(txtformat);
		//System.out.println(txtformat);
		Customlm.sendKeys(C_RLM);
		try {
			submitBtn.click();
			Log.info("clicked on SAVE button after providing details");
			Log.info("Successfully created a Custom Block with title name: " +title1);
		}
		catch(Exception e) {
			//Log.error("Unable to find SAVE button after providing details");
		}
		
		
	}
	
	public void searchCustomBlockTitle(String inputCustomTitle, String editedTitle, String E_CustomLog) {
		try {
			searchBox.sendKeys(inputCustomTitle);
			searchBtn.click();
			TracedEditBtn.click();
			title.clear();
			title.sendKeys(editedTitle);
			Customlm.clear();
			Customlm.sendKeys(E_CustomLog);
			try {
				submitBtn.click();
				System.out.println("clicked submit button");
				Log.info("clicked on SAVE button after Editing details");
				Log.info(inputCustomTitle+" is updated as: "+editedTitle);
			}
			catch(Exception e) {
				Log.info("Unable to get status message, but Custom Block is Edited");
			}
		}
		catch(Exception e1) {
			Log.info("No such Title "+inputCustomTitle+ " is present in the Custom Blocks Table List");
		}
	
	}
	
	public void deleteCustomBtn(String title1_D, String customLog_D, String deleteCustomTitle) throws InterruptedException {
		addNewCustom.click();
		title.sendKeys(title1_D);
		Customlm.sendKeys(customLog_D);
		try {
			submitBtn.click();
			Thread.sleep(1000);
			Log.info("Custom Block is created");
		}
		catch(Exception e) {
			Log.error("Unable to get status message, but custom Block has been created");
		}
		Thread.sleep(2000);
		searchBox.sendKeys(deleteCustomTitle);
		searchBtn.click();
		Thread.sleep(1000);
		TraceDeleteBtnArrow.click();
		List<WebElement>options=driver.findElements(By.xpath("//tbody/tr[1]/td[8]/div[1]/div[1]/ul[1]/li"));
		for(WebElement opt: options) {
			if (opt.getText().equals("Delete")) {
				opt.click();
			}
		}
		customDelete.click();
		System.out.println("Custom Block is deleted");
		
	}
}
