package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.base;

public class HomePage extends base{
	WebDriverWait wait = new WebDriverWait(driver, 30);
	@FindBy(xpath="//a[contains(text(),'Dashboard')]")
	WebElement dashboardLink;
	

	
	public HomePage() {
		PageFactory.initElements(driver, this);	
	}
	
	public DashboardPage clickOnDashboardLink() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'Dashboard')]")));
		dashboardLink.click();
		return new DashboardPage();
	}
	
	
}	
