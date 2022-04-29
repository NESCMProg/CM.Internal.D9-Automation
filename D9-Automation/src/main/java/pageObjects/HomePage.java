package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import resources.base;

public class HomePage extends base{
	@FindBy(xpath="//a[contains(text(),'Dashboard')]")
	WebElement dashboardLink;
	

	
	public HomePage() {
		PageFactory.initElements(driver, this);	
	}
	
	public DashboardPage clickOnDashboardLink() throws InterruptedException {
		dashboardLink.click();
		Thread.sleep(1000);
		return new DashboardPage();
	}
	
	
}	
