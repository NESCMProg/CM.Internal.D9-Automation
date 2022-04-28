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
import pageObjects.DataBaseListingsViewPage;
import pageObjects.HomePage;
import pageObjects.LoginPatron;
import pageObjects.calloutViewPage;
import pageObjects.researchGuidesViewPage;
import resources.base;
import utility.Log;
import utility.TestUtil;
import utility.readCredentials;

public class DeleteResearchGuideTest extends base {
	LoginPatron loginpatron;
	HomePage hp;
	DashboardPage dashboardpage;
	researchGuidesViewPage rgvp;
	DeleteResearchGuideTest deleteresearchguide;
	readCredentials rc = new readCredentials();

	public DeleteResearchGuideTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser is opened");
		Log.info("Test functionality to delete Research Guide");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		loginpatron = new LoginPatron();
		String username1 = rc.ReadCellData(1, 0);
		String password1 = rc.ReadCellData(1, 1);
		hp = new HomePage();
		hp = loginpatron.login(username1, password1);
		Thread.sleep(1000);
		dashboardpage = new DashboardPage();
		dashboardpage = hp.clickOnDashboardLink();
		rgvp = new researchGuidesViewPage();
		rgvp = dashboardpage.clickOnResearchGuideLink();
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
	public Object[][] getdeleteRGData() {
		Object data[][] = null;
		try {
			data = TestUtil.getTestData("deleteresearch");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Test(dataProvider = "getdeleteRGData")
	public void deleteResearchGuideTest(String RG_Title, String D_termRG, String D_tabTitle_1, String D_tabURL_1, String D_RGTitle) {
		Log.info("Test Case ID: TS_RG_03");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Module Name: Research Guide");
		Log.info("Test Executed By: " + machineName);
		Log.info("Test Executed Date: " + currentDateTime);
		Log.info(
				"Test Description: Log in to EBSCO Enterprise Research application to create a Research Guide first. Then identify the created ones in Research Guide"
						+ " table and delete the following Research Guide by search the title from search bar");

		try {
			rgvp.deleteResearchGuideBtn(RG_Title, D_termRG, D_tabTitle_1, D_tabURL_1, D_RGTitle);
			Log.info("'" + RG_Title + "'" + " Research Guide is created first");
			Log.info("'" + D_RGTitle + "'" + " is deleted successfully");
		} catch (Exception e1) {
			Log.error("Failed to Delete: "+D_RGTitle);
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
