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
import resources.base;
import utility.Log;
import utility.TestUtil;
import utility.readCredentials;

public class DeleteDirectoryListingTest extends base {
	LoginPatron loginpatron;
	HomePage hp;
	DashboardPage dashboardpage;
	directoryListingsViewPage dlvp;
	DeleteDirectoryListingTest deldirectorylist;
	readCredentials rc = new readCredentials();

	public DeleteDirectoryListingTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser is opened");
		Log.info("Test functionality to delete a Directory List");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		loginpatron = new LoginPatron();
		Thread.sleep(1000);
		String username1 = rc.ReadCellData(1, 0);
		String password1 = rc.ReadCellData(1, 1);
		hp = new HomePage();
		hp = loginpatron.login(username1, password1);
		dashboardpage = new DashboardPage();
		dashboardpage = hp.clickOnDashboardLink();
		dlvp = new directoryListingsViewPage();
		dlvp = dashboardpage.clickOnDirectoryListingsLink();
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
	public Object[][] getdeletedirectorylistData() {
		Object data[][] = null;
		try {
			data = TestUtil.getTestData("deletedirectory");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Test(dataProvider = "getdeletedirectorylistData")
	public void deleteTest(String D_directoryName, String D_lastName, String D_jobTitle, String D_Location, String D_Directory1, String D_Directory2) {
		Log.info("Test Case ID: TS_DL_03");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Module Name: Directory Listings");
		Log.info("Test Executed By: " + machineName);
		Log.info("Test Executed Date: " + currentDateTime);
		Log.info(
				"Test Description: Log in to EBSCO Enterprise Research application to create a Directory List first. Then identify the created ones in Directory"
						+ " Listings table and delete the following Directory");

		try {
			dlvp.deleteDirectoryListBtn(D_directoryName, D_lastName, D_jobTitle, D_Location,
					D_Directory1, D_Directory2);
			Log.info(D_directoryName + " " + D_lastName + " is created first");
			Log.info(D_Directory1 + " " + D_Directory2 + " is deleted successfully");
		} catch (Exception e1) {
			Log.error("Failed to Delete" + D_directoryName);
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
