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
import pageObjects.calloutViewPage;
import pageObjects.indexDashboardPage;
import pageObjects.indexPagesViewPage;
import pageObjects.reservesViewPage;
import resources.base;
import utility.TestUtil;

public class CreateIndexPageTestPage extends base {
	LoginPatron loginpatron;
	HomePage hp;
	DashboardPage dashboardpage;
	indexDashboardPage ixdp;
	CreateIndexPageTestPage createindexpage;
	indexPagesViewPage ipvp;
	readCredentials rc = new readCredentials();

	public CreateIndexPageTestPage() {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Test functionality for creating a Index Page");
		loginpatron = new LoginPatron();
		String username1 = rc.ReadCellData(1, 0);
		String password1 = rc.ReadCellData(1, 1);
		Thread.sleep(1000);
		hp = new HomePage();
		hp = loginpatron.login(username1, password1);
		dashboardpage = new DashboardPage();
		dashboardpage = hp.clickOnDashboardLink();
		ixdp = new indexDashboardPage();
		ixdp = dashboardpage.clickOnIndexPagesLink();
		ipvp = new indexPagesViewPage();
		ipvp = ixdp.clickOnDashToIndexPage();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

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
	public Object[][] getIndexPageData() {

		Object data[][] = null;
		try {
			data = TestUtil.getTestData("indexpage");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Test(dataProvider = "getIndexPageData")
	public void newIndexPagetest(String C_indexPageTitle, String C_Languauge) throws InterruptedException {
		Log.info("Module Name: Index Pages");
		Log.info("Test Case ID: TS_IIP_01");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Test Executed By: "+machineName );
		Log.info("Test Executed Date: " + currentDateTime);
		Log.info("Test Description: To navigate to Index Pages Tile, click on Index Page tile in dashboard. Two tiles present, namely - [Index Page], [Index Entries]. Again click on [Index Page] tile to view 'Index Pages' page. Click on [Add New] button to create a new 'Index Page'."
				+ " Page navigates to 'Create Index Page' page. Fill the details and click on [Save] button. "
				+ "New 'Index Page' is created with respective Title name and can be viewed in Index Pages table grid. Note: Index Page input data is taken from excel sheet: indexpage");
		ipvp.clickNewIndexPageBtn(C_indexPageTitle, C_Languauge);
		Log.info("Condition in Test Scenario Id: TS_IIP_01 is Executed Successfully");

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
			Log.info("Browser is closed");
	}

}
