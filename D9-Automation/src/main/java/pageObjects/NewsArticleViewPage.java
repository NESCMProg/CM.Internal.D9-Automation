package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.base;
import utility.Log;

public class NewsArticleViewPage extends base {

	@FindBy(xpath = "//input[@value='Delete']")
	WebElement newsArticleDelete;

	@FindBy(xpath = "//a[contains(text(),'Add New')]")
	WebElement addNewArticle;

	@FindBy(xpath = "//input[@id='edit-title-0-value']")
	WebElement title;

	@FindBy(xpath = "//input[@id='edit-field-news-author-0-value']")
	WebElement author;

	@FindBy(css = "#edit-submit")
	WebElement submitBtn;

	@FindBy(id = "edit-q")
	WebElement searchBox;

	@FindBy(css = "#edit-search")
	WebElement searchBtn;

	@FindBy(xpath = "//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li[1]/a[1]")
	WebElement TracedEditBtn;

	@FindBy(xpath = "//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li[2]/button[1]")
	WebElement TraceDeleteBtnArrow;

	@FindBy(xpath = "//main[@id='main-content']/div[1]/div[@class='messages messages-status']")
	WebElement createMethodSuccess;

	@FindBy(css = "div.messages__content")
	WebElement titleValidationError;

	@FindBy(css = "div.site-slogan")
	WebElement viewPageSlogan;

	@FindBy(css = "div.form-item__error-message")
	WebElement fieldError;

	@FindBy(linkText = "Dashboard")
	WebElement viewToDash;

	@FindBy(linkText = "News articles")
	WebElement newsToDashLink;

	@FindBy(xpath = "//a[contains(text(),'Dashboard')]")
	WebElement viewDashboard;

	@FindBy(css = "div.messages__content")
	WebElement successStatusCheck;

	public NewsArticleViewPage() {
		PageFactory.initElements(driver, this);
	}

	public void clickonNewBtnandSaveBtn(String newsTitle, String newsAuthor) {
		addNewArticle.click();
		title.sendKeys(newsTitle);
		author.sendKeys(newsAuthor);
		try {
			submitBtn.click();
			String validateAuthor = successStatusCheck.getText();
			Log.info(validateAuthor);
			Log.info("Test Result: Pass");
		} catch (Exception e) {
			String titleWarning = titleValidationError.getText();
			Log.error("Error Message: " + titleWarning);
			String elementError = fieldError.getText();
			Log.error("Field Message: " + elementError);
			Log.error("Test Result: Fail to create a News Article, since mandatory field not provided");
		}

	}

	public void searchNewsArticleTitle(String inputNewsTitle, String editedTitle, String E_author) {
		try {
			searchBox.sendKeys(inputNewsTitle);
			searchBtn.click();
			TracedEditBtn.click();
			title.clear();
			title.sendKeys(editedTitle);
			Thread.sleep(1000);
			author.clear();
			author.sendKeys(E_author);
			try {
				submitBtn.click();
				System.out.println("clicked submit button");
				Log.info("clicked on SAVE button after Editing details");
				Log.info(inputNewsTitle + " is updated as: " + editedTitle);
			} catch (Exception e) {
				Log.info("Unable to get status message, but News Article is Edited");
			}
		} catch (Exception e1) {
			Log.info("No such Title '" + inputNewsTitle + "' is present in the News Article Table List");
		}

	}

	public void deleteNewsArticleBtn(String D_title, String D_author, String deletedTitle) throws InterruptedException {
		addNewArticle.click();
		title.sendKeys(D_title);
		author.sendKeys(D_author);
		Thread.sleep(1000);
		try {
			submitBtn.click();
			Log.info("News Article created");
		} catch (Exception e) {
			Log.error("Unable to get status message, but News Article has been created");
		}
		Thread.sleep(1000);
		searchBox.sendKeys(deletedTitle);
		searchBtn.click();
		TraceDeleteBtnArrow.click();
		List<WebElement> options = driver.findElements(By.xpath("//tbody/tr[1]/td[7]/div[1]/div[1]/ul[1]/li[5]/a[1]"));
		for (WebElement opt : options) {
			if (opt.getText().equals("Delete")) {
				opt.click();
			}
		}
		newsArticleDelete.click();
		System.out.println("News Article is deleted");
	}
}
