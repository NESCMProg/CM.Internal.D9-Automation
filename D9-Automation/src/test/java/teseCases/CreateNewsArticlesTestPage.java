package teseCases;

import utility.Log;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import utility.readCredentials;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.HomePage;
import pageObjects.LoginPatron;
import pageObjects.NewsArticleViewPage;
import pageObjects.calloutViewPage;
import resources.base;
import utility.TestUtil;

public class CreateNewsArticlesTestPage extends base {
	LoginPatron loginpatron;
	HomePage hp;
	DashboardPage dashboardpage;
	NewsArticleViewPage navp;
	CreateNewsArticlesTestPage createnewsarticles;
	readCredentials rc = new readCredentials();

	public CreateNewsArticlesTestPage() {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser is opened");
		Log.info("Test functionality for creating News Article Page");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginpatron = new LoginPatron();
		Thread.sleep(1000);
		String username1 = rc.ReadCellData(1, 0);
		String password1 = rc.ReadCellData(1, 1);
		hp = new HomePage();
		hp = loginpatron.login(username1, password1);
		dashboardpage = new DashboardPage();
		dashboardpage = hp.clickOnDashboardLink();
		navp = new NewsArticleViewPage();
		navp = dashboardpage.clickOnNewsArticlesLink();

	}

	@DataProvider
	public Object[][] getLoginData() {

		Object data[][] = null;
		try {
			data = TestUtil.getTestData("login");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

	@DataProvider
	public Object[][] getNewsArticlesData() {

		Object data[][] = null;
		try {
			data = TestUtil.getTestData("newsarticles");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Test(dataProvider = "getNewsArticlesData")
	public void newArticleCreateTest(String newsTitle, String newsAuthor)  {
		Log.info("Module Name: News Articles");
		Log.info("Test Case ID: TS_NA_01");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Test Executed By: "+machineName );
		Log.info("Test Executed Date: " + currentDateTime);
		Log.info("Test Description: Navigate to News Articles Page in News Articles Tile. Click on [Add New] button to create a new News Article."
				+ " Page navigates to Create News Article page. Fill the details and click on [Save] button. "
				+ "New 'News Article' is created with respective Title name and can be viewed in News Articles table grid.");
		navp.clickonNewBtnandSaveBtn(newsTitle, newsAuthor);
		Log.info("Condition in Test Scenario Id: TS_NA_01 is Executed Successfully");
		

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
			Log.info("Browser is closed");
	
	}

}
