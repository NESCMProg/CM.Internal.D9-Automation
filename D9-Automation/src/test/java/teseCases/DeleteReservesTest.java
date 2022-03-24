package teseCases;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.HomePage;
import pageObjects.LoginPatron;
import pageObjects.NewsArticleViewPage;
import pageObjects.calloutViewPage;
import pageObjects.directoryListingsViewPage;
import pageObjects.reservesViewPage;
import resources.base;
import utility.Log;
import utility.TestUtil;
import utility.readCredentials;

public class DeleteReservesTest extends base {
	LoginPatron loginpatron;
	HomePage hp;
	DashboardPage dashboardpage;
	reservesViewPage rvp;
	DeleteReservesTest delreserveslist;
	readCredentials rc = new readCredentials();

	public DeleteReservesTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser is opened");
		Log.info("Test functionality to delete a Reserve List");
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		loginpatron = new LoginPatron();
		String username1 = rc.ReadCellData(1, 0);
		String password1 = rc.ReadCellData(1, 1);
		hp = new HomePage();
		hp = loginpatron.login(username1, password1);
		dashboardpage = new DashboardPage();
		dashboardpage = hp.clickOnDashboardLink();
		rvp = new reservesViewPage();
		rvp = dashboardpage.clickonReservesLink();
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
	public Object[][] getdeleteReserveData() {
		Object data[][] = null;
		try {
			data = TestUtil.getTestData("deletereserve");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Test(dataProvider = "getdeleteReserveData")
	public void deleteReserveTest(String deletereserveTitle, String D_language, String D_registrarID, String D_courseID, String D_startDate, String D_startTime, String D_endDate, String D_endTime, String D_Reserve) {
		Log.info("Test Case ID: TS_RL_03");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Module Name: Reserves");
		Log.info("Test Executed By: " + machineName);
		Log.info("Test Executed Date: " + currentDateTime);
		Log.info(
				"Test Description: Log in to EBSCO Enterprise Research application to create a Reserve List first. Then identify the created ones in Reserves"
						+ " Listings table grid and delete the following Reserve List");

		try {
			rvp.deleteReserveListBtn(deletereserveTitle, D_language, D_registrarID, D_courseID, D_startDate, D_startTime, D_endDate, D_endTime, D_Reserve);
			Log.info("'"+deletereserveTitle+"' is created first");
			Log.info( "'"+D_Reserve+"' is deleted successfully");
		} catch (Exception e1) {
			Log.error("Failed to Delete: "+D_Reserve);
		}

		Log.info("Test Result: Pass");
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
		try {
			Log.info("Browser is closed");
		} catch (Exception e) {
			Log.error(e.getMessage());
		}

	}

}
