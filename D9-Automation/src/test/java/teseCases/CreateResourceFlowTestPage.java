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
import pageObjects.resourceViewPage;
import resources.base;
import utility.TestUtil;
import utility.readCredentials;

public class CreateResourceFlowTestPage extends base {
	LoginPatron loginpatron;
	HomePage hp;
	DashboardPage dashboardpage;
	resourceViewPage rfvp;
	readCredentials rc = new readCredentials();
	CreateResourceFlowTestPage createResourceFlows;

	public CreateResourceFlowTestPage() {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser is opened");
		Log.info("Test functionality for creating a Resource Flow");
		loginpatron = new LoginPatron();
		String username1 = rc.ReadCellData(1, 0);
		String password1 = rc.ReadCellData(1, 1);
		Thread.sleep(1000);
		hp = new HomePage();
		hp = loginpatron.login(username1, password1);
		Thread.sleep(1000);
		dashboardpage = new DashboardPage();
		dashboardpage = hp.clickOnDashboardLink();
		rfvp = new resourceViewPage();
		rfvp = dashboardpage.clickOnResourceFlowLink();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

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
	public Object[][] getResourceFlowsData() {

		Object data[][] = null;
		try {
			data = TestUtil.getTestData("resourceflows");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Test(dataProvider = "getResourceFlowsData")
	public void newResourceFlowTest(String rtitle1, String upc1, String rtitle11, String author1) throws InterruptedException {
		Log.info("Module Name: Resource Flows");
		Log.info("Test Case ID: TS_RF_01");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Test Executed By: "+machineName );
		Log.info("Test Executed Date: " + currentDateTime);
		Log.info(
				"Test Description: Navigate to Resource Flow Page in Resource Flow Tile. Click on [Add New] button to create a new "
						+ "Resource Flow. Page navigates to Create Resource Flow page. Fill the details and click on [Save] button. "
						+ "New Resource Flow is created with respective Title name and and be viewed in Resource Flow table ");
		rfvp.clickResourceFlowNewandSaveBtn(rtitle1, upc1, rtitle11, author1);
		Log.info("Test Scenario Id: TS_RF_01 is Executed Successfully");
		System.out.println(rtitle1);
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
			Log.info("Browser is closed");
		
	}

}
