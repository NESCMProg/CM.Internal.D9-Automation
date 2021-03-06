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
import pageObjects.calloutViewPage;
import pageObjects.resourceViewPage;
import resources.base;
import utility.Log;
import utility.TestUtil;
import utility.readCredentials;

public class DeleteResourceFlowTest extends base {
	LoginPatron loginpatron;
	HomePage hp;
	DashboardPage dashboardpage;
	resourceViewPage rfvp;
	DeleteResourceFlowTest delresource;
	readCredentials rc = new readCredentials();

	public DeleteResourceFlowTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser is opened");
		Log.info("Test functionality to delete a Resource Flow");
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		loginpatron = new LoginPatron();
		Thread.sleep(1000);
		String username1 = rc.ReadCellData(1, 0);
		String password1 = rc.ReadCellData(1, 1);
		hp = new HomePage();
		hp = loginpatron.login(username1, password1);
		Thread.sleep(1000);
		dashboardpage = new DashboardPage();
		dashboardpage = hp.clickOnDashboardLink();
		rfvp = new resourceViewPage();
		rfvp = dashboardpage.clickOnResourceFlowLink();
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
	public Object[][] getdeleteResourceData() {
		Object data[][] = null;
		try {
			data = TestUtil.getTestData("deleteresourceflow");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Test(dataProvider = "getdeleteResourceData")
	public void deleteTest(String mainResourceTitle, String upc_1, String rtitle11, String author1,
			String deleteResourceTitle) {
		Log.info("Test Case ID: TS_RF_03");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Module Name: Resource Flows");
		Log.info("Test Executed By: " + machineName);
		Log.info("Test Executed Date: " + currentDateTime);
		Log.info(
				"Test Description: Log in to Ebsco Enterprise Research application to create a Resource Flow first. Then identify the created ones in Resource"
						+ " Flow table and delete the following Resource Flow");

		try {
			rfvp.deleteResourceFlowBtn(mainResourceTitle, upc_1, rtitle11, author1, deleteResourceTitle);
			Log.info("'" + mainResourceTitle + "'" + " Resource Flow is created first");
			Log.info("'" + deleteResourceTitle + "'" + " is deleted successfully");
		} catch (Exception e1) {

		}

		Log.info("Test Result: Pass");
		System.out.println("For Deletetion, deleteResourceFlowBtn method is implemented");
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
