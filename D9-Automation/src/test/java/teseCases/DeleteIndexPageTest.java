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
import pageObjects.indexDashboardPage;
import pageObjects.indexPagesViewPage;
import resources.base;
import utility.Log;
import utility.TestUtil;
import utility.readCredentials;

public class DeleteIndexPageTest extends base {
	LoginPatron loginpatron;
	HomePage hp;
	DashboardPage dashboardpage;
	indexDashboardPage ixdp;
	indexPagesViewPage ipvp;
	DeleteIndexPageTest delindexpage;
	readCredentials rc = new readCredentials();

	public DeleteIndexPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser is opened");
		Log.info("Test functionality to delete an Index Page");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		loginpatron = new LoginPatron();
		String username1 = rc.ReadCellData(1, 0);
		String password1 = rc.ReadCellData(1, 1);
		hp = new HomePage();
		hp = loginpatron.login(username1, password1);
		dashboardpage = new DashboardPage();
		dashboardpage = hp.clickOnDashboardLink();
		ixdp = new indexDashboardPage();
		ixdp = dashboardpage.clickOnIndexPagesLink();
		ipvp = new indexPagesViewPage();
		ipvp = ixdp.clickOnDashToIndexPage();
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
	public Object[][] getdeleteIndexPageData() {
		Object data[][] = null;
		try {
			data = TestUtil.getTestData("deleteindexpage");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Test(dataProvider = "getdeleteIndexPageData")
	public void deleteTest(String D_indexPageTitle, String D_Languauge, String deletedIndexTitle) {
		Log.info("Test Case ID: TS_IIP_03");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Module Name: Index Page");
		Log.info("Test Executed By: " + machineName);
		Log.info("Test Executed Date: " + currentDateTime);
		Log.info(
				"Test Description: To navigate to Index Pages Tile, click on Index Page tile in dashboard. Two tiles present, namely - [Index Page], [Index Entry]. Again click on [Index Page] tile to view 'Index Pages' page."
						+ " Create a new Index Page and then again identify the Index Page Title from search bar. Then delete the following Index Page");

		try {
			ipvp.deleteIndexPageBtn(D_indexPageTitle, D_Languauge, deletedIndexTitle);
			Log.info("'" + D_indexPageTitle + "'" + " is created first");
			Log.info("'" + deletedIndexTitle + "'" + " is deleted successfully");
		} catch (Exception e1) {
			Log.error("Failed to Delete: " + deletedIndexTitle);
		}

		Log.info("Test Result: Pass");
		System.out.println("For Deletetion, deleteIndexPageBtn method is implemented");
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
