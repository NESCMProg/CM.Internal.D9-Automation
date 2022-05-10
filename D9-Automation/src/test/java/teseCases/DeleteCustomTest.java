package teseCases;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CustomBlockViewPage;
import pageObjects.DashboardPage;
import pageObjects.HomePage;
import pageObjects.LoginPatron;
import pageObjects.calloutViewPage;
import resources.base;
import utility.Log;
import utility.TestUtil;
import utility.readCredentials;

public class DeleteCustomTest extends base {
	LoginPatron loginpatron;
	HomePage hp;
	DashboardPage dashboardpage;
	CustomBlockViewPage cbvp;
	DeleteCustomTest delcustom;
	readCredentials rc = new readCredentials();

	public DeleteCustomTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser is opened");
		Log.info("Test functionality to delete a Custom block");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginpatron = new LoginPatron();
		String username1 = rc.ReadCellData(1, 0);
		String password1 = rc.ReadCellData(1, 1);
		hp = new HomePage();
		hp = loginpatron.login(username1, password1);
		dashboardpage = new DashboardPage();
		dashboardpage = hp.clickOnDashboardLink();
		cbvp = new CustomBlockViewPage();
		cbvp = dashboardpage.clickOnCustomBlockLink();
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
	public Object[][] getdeleteData() {
		Object data[][] = null;
		try {
			data = TestUtil.getTestData("deletecustom");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Test(dataProvider = "getdeleteData")
	public void deleteTest(String title1_D, String customLog_D, String deleteCustomTitle) {
		Log.info("Test Case ID: TS_CB_03");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Module Name: Custom Block");
		Log.info("Test Executed By: " + machineName);
		Log.info("Test Executed Date: " + currentDateTime);
		Log.info(
				"Test Description: Log in to EBSCO Enterprise Research application to create a Custom Block first. Then identify the created ones in Custom"
						+ " table and delete the following Custom Block");

		try {
			cbvp.deleteCustomBtn(title1_D, customLog_D, deleteCustomTitle);
			Log.info("'" + title1_D + "'" + " Custom Block is created first");
			Log.info("'" + deleteCustomTitle + "'" + " is deleted successfully");
			Log.info("Test Result: Pass");
		} catch (Exception e1) {
			Log.error("Failed to Delete: " + deleteCustomTitle);
		}

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
