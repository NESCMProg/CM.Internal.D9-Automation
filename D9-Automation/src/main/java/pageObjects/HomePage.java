package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.base;

public class HomePage extends base{
	WebDriverWait wait = new WebDriverWait(driver, 30);
	@FindBy(xpath="//a[contains(text(),'Dashboard')]")
	WebElement dashboardLink;
	

	
	public HomePage() {
		PageFactory.initElements(driver, this);	
	}
	
	public DashboardPage clickOnDashboardLink() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'Dashboard')]")));
		dashboardLink.click();
		return new DashboardPage();
	}
	
	
}	
