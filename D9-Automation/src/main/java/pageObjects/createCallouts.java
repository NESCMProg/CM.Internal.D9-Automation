package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.base;

public class createCallouts extends base {
@FindBy(id="edit-title")
WebElement title;

@FindBy(id="edit-field-callouts-und-0-field-callout-title-und-0-value")
WebElement subtitle;

@FindBy(xpath="//textarea[@id='edit-field-callouts-und-0-field-callout-description-und-0-value']")
WebElement desc;

@FindBy(id="edit-submit")
WebElement submitbtn;



public WebElement calloutTitle()
{
	return title;
}
public WebElement calloutsubTitle()
{
	return subtitle;
}

public WebElement description1()
{
	return desc;
}
public WebElement submit()
{
	return submitbtn;
}
}
