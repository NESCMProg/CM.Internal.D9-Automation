package teseCases;

import utility.Log;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.HomePage;
import pageObjects.LoginPatron;
import pageObjects.calloutViewPage;
import pageObjects.pagesViewPage;
import pageObjects.resourceViewPage;
import resources.base;
import utility.TestUtil;
import utility.readCredentials;

public class CreatePagesTestPage extends base {
	LoginPatron loginpatron;
	HomePage hp;
	DashboardPage dashboardpage;
	pagesViewPage pvp;
	readCredentials rc = new readCredentials();

	public CreatePagesTestPage() {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser is opened");
		Log.info("Test functionality for creating a Page");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		loginpatron = new LoginPatron();
		String username1 = rc.ReadCellData(1, 0);
		String password1 = rc.ReadCellData(1, 1);
		Thread.sleep(1000);
		hp = new HomePage();
		hp = loginpatron.login(username1, password1);
		dashboardpage = new DashboardPage();
		Thread.sleep(1000);
		dashboardpage = hp.clickOnDashboardLink();
		pvp = new pagesViewPage();
		pvp = dashboardpage.clickOnPagesLink();
		

	}

	@DataProvider
	public Object[][] getLoginData() {

		// common utility - which is applicable for excel files
		Object data[][] = null;
		try {
			data = TestUtil.getTestData("login");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@DataProvider
	public Object[][] getpagesData() {

		Object data[][] = null;
		try {
			data = TestUtil.getTestData("pages");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Test(dataProvider = "getpagesData")
	public void newPageCreateTest(String pgTitle) throws InterruptedException {
		Log.info("Module Name: Pages");
		Log.info("Test Case ID: TS_PG_01");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Test Executed By: " + machineName);
		Log.info("Test Executed Date: " + currentDateTime);
		Log.info(
				"Test Description: To create Page, click on Pages Tile in dashboard. Click on [Add New] button to navigate to"
						+ " 'Create Page'. Fill details and click on [SAVE] button. New Page is created");
		pvp.clickPagesNewandSaveBtn(pgTitle);
		Log.info("Test Scenario Id: TS_PG_01 is Executed Successfully");
		
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
		Log.info("Browser is closed");

	}

}
