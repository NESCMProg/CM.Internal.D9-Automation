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
import pageObjects.DataBaseListingsViewPage;
import pageObjects.HomePage;
import pageObjects.LoginPatron;
import pageObjects.calloutViewPage;
import resources.base;
import utility.TestUtil;

public class CreateDataBaseListingsTestPage extends base {
	LoginPatron loginpatron;
	HomePage hp;
	DashboardPage dashboardpage;
	DataBaseListingsViewPage dblvp;
	CreateDataBaseListingsTestPage createdatabaselistings;
	readCredentials rc = new readCredentials();

	public CreateDataBaseListingsTestPage() {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser is opened");
		Log.info("Test Functionality for creating Database Listings");
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		loginpatron = new LoginPatron();
		String username1 = rc.ReadCellData(1, 0);
		String password1 = rc.ReadCellData(1, 1);
		hp = new HomePage();
		hp = loginpatron.login(username1, password1);
		dashboardpage = new DashboardPage();
		dashboardpage = hp.clickOnDashboardLink();
		dblvp = new DataBaseListingsViewPage();
		dblvp = dashboardpage.clickOnDatabaseListingLink();
		

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
	public Object[][] getDBListingsData() {

		Object data[][] = null;
		try {
			data = TestUtil.getTestData("dblisting");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Test(dataProvider = "getDBListingsData")
	public void newDBLCreateTest(String DBLTITLE, String Main_URL1, String C_subURL1, String C_subLinkText1) throws InterruptedException {
		Log.info("Module Name: Database Listings");
		Log.info("Test Case ID: TS_DBL_01");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Test Executed By: "+machineName );
		Log.info("Test Executed Date: " + currentDateTime);
		Log.info("Test Description: Navigate to Database Listings Page in Database Listings Tile. Click on [Add New] button to create a new Database List."
				+ " Page navigates to 'Create Database Listing' page. Fill the details and click on [Save] button. "
				+ "New Database List is created with respective Title name and can be viewed in Database Listings table grid.");
		dblvp.clickDBLNewBtnandSaveBtn(DBLTITLE, Main_URL1, C_subURL1, C_subLinkText1);
		Log.info("Condition in Test Scenario Id: TS_DBL_01 is Executed Successfully");
		Log.info("Test Result: Pass");

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
			Log.info("Browser is closed");
		

	}

}
