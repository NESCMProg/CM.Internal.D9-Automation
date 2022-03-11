package pageObjects;

import org.openqa.selenium.support.PageFactory;

import resources.base;

public class deleteCallout extends base{

	public deleteCallout() {
		PageFactory.initElements(driver, this);
	}
}
