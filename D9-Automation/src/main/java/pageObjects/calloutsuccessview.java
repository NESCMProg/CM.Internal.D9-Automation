package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.base;

public class calloutsuccessview extends base {

	@FindBy(css="em.placeholder")
	WebElement calloutname;
	
	@FindBy(xpath="//a[contains(text(),'Dashboard')]")
	WebElement dash;
	
	@FindBy(xpath= "//span[contains(text(),'Callouts')]")
	WebElement dashTocalloutLink;
	
	public calloutsuccessview() {
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	
	
	
	
}
