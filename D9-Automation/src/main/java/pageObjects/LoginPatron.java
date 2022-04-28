package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.base;
import utility.Log;

public class LoginPatron extends base {						
	//Page Factory
	@FindBy(css="#edit-name")
	WebElement username;
	
	@FindBy(css="#edit-pass")
	WebElement password;
	
	@FindBy(css="#edit-submit")
	WebElement LoginBtn;
	
	@FindBy(xpath="//input[@id='edit-search-form-eds-search-bar-container-query']")
	WebElement pageSearchBar;
	
	@FindBy(xpath="//a[contains(text(),'Dashboard')]")
	WebElement viewDashboard;
	
	@FindBy(xpath="//h2[contains(text(),'Error message')]")
	WebElement errormessage;
	
	@FindBy(xpath="//body/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[1]")
	WebElement elementerror;
	
	@FindBy(css="a.close-reveal-modal")
	WebElement alertmessage;
	
	@FindBy(xpath="//strong[contains(text(),'You have entered an invalid username or password. ')]")
	WebElement alertException;
	
	//Initialization page factory
	public LoginPatron() {
		PageFactory.initElements(driver, this);	
	}
	
	//Actions in Login Page
	public String validatepatronLoginPageTitle() {
		return driver.getTitle();
	}
public boolean validateErrorMessage() {
	return errormessage.isDisplayed();
}
	

public boolean validatesingleError() {
	return elementerror.isDisplayed();
}

public HomePage login(String un, String pwd) throws InterruptedException {
	
	//username.clear();
	username.sendKeys(un);
	Log.info("User Name: " +un);
	//LoginBtn.click();
	//password.clear();
	password.sendKeys(pwd);
	Log.info("Password: "+pwd);
	Thread.sleep(1000);
	LoginBtn.click();
	System.out.println("clicked login btn");
	try {
		String viewDashboardLink = viewDashboard.getText();
		Log.info(viewDashboardLink);
		//pageSearchBar.click();
		Log.info("Application is accessed with correct credentials");
		Log.info("Navigated to Home page");
	}
	catch(Exception e1) {
		String alerexptmsg = alertException.getText();
		System.out.println(alerexptmsg);
		Log.info(alerexptmsg);
	}
	return new HomePage();
	
}


	
}
