package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.concurrent.TimeUnit;
import resources.base;

public class HomePage extends base{
	@FindBy(css="a.toolbar-item.is-active")
	WebElement dashboardLink;
	

	
	public HomePage() {
		PageFactory.initElements(driver, this);	
	}
	
	public DashboardPage clickOnDashboardLink() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		Thread.sleep(1000);
		dashboardLink.click();
		return new DashboardPage();
	}
	
	
}	
