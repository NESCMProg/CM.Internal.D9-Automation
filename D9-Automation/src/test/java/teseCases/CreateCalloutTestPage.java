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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.HomePage;
import pageObjects.LoginPatron;
import pageObjects.calloutViewPage;
import resources.base;
import utility.TestUtil;

public class CreateCalloutTestPage extends base {
	LoginPatron loginpatron;
	HomePage hp;
	DashboardPage dashboardpage;
	calloutViewPage covp;
	CreateCalloutTestPage createcallouts;
	readCredentials rc = new readCredentials();

	public CreateCalloutTestPage() {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser is opened");
		Log.info("Test functionality for creating a Callout");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		loginpatron = new LoginPatron();
		Thread.sleep(1000);
		String username1 = rc.ReadCellData(1, 0);
		String password1 = rc.ReadCellData(1, 1);
		hp = new HomePage();
		hp = loginpatron.login(username1, password1);
		dashboardpage = new DashboardPage();
		dashboardpage = hp.clickOnDashboardLink();
		covp = new calloutViewPage();
		covp = dashboardpage.clickOnCalloutsLink();
		

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
	public Object[][] getCalloutData() {

		Object data[][] = null;
		try {
			data = TestUtil.getTestData("callouts");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Test(dataProvider = "getCalloutData")
	public void newcalloutcreatetest(String callouttitle1) throws InterruptedException {
		Log.info("Module Name: Callouts");
		Log.info("Test Case ID: TS_CO_01");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Test Executed By: "+machineName );
		Log.info("Test Executed Date: " + currentDateTime);
		Log.info("Test Description: Navigate to Callouts Page in Callouts Tile. Click on [Add callout] button to create a new callout."
				+ " Page navigates to 'Create Callouts' page. Fill the details and click on [Save] button. "
				+ "New callout is created with respective Title name and can be viewed in Callouts table grid.");
		covp.clickonNewBtnandSaveBtn(callouttitle1);
		Log.info("Condition in Test Scenario Id: TS_CO_01 is Executed Successfully");

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
			Log.info("Browser is closed");
	}

}
