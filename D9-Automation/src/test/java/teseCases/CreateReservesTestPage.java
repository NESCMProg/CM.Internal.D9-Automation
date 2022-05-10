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
import pageObjects.reservesViewPage;
import resources.base;
import utility.TestUtil;

public class CreateReservesTestPage extends base {
	LoginPatron loginpatron;
	HomePage hp;
	DashboardPage dashboardpage;
	reservesViewPage rvp;
	CreateReservesTestPage createreserves;
	readCredentials rc = new readCredentials();

	public CreateReservesTestPage() {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser is opened");
		Log.info("Test functionality for creating a Reserve");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		loginpatron = new LoginPatron();
		String username1 = rc.ReadCellData(1, 0);
		String password1 = rc.ReadCellData(1, 1);
		Thread.sleep(1000);
		hp = new HomePage();
		hp = loginpatron.login(username1, password1);
		dashboardpage = new DashboardPage();
		Thread.sleep(1000);
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
	public Object[][] getReservesData() {

		Object data[][] = null;
		try {
			data = TestUtil.getTestData("reserves");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Test(dataProvider = "getReservesData")
	public void newReservesTest(String C_reserveTitle,String C_Languauge, String C_registrarID, String C_courseID, String C_startDate, String C_startTime, String C_endDate, String C_endTime, String C_ISBN) throws InterruptedException {
		Log.info("Module Name: Reserves");
		Log.info("Test Case ID: TS_RL_01");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Test Executed By: "+machineName );
		Log.info("Test Executed Date: " + currentDateTime);
		Log.info("Test Description: Navigate to Reserves Page in Reserves Tile. Click on [Add New] button to create a new Reserve."
				+ " Page navigates to 'Create Reserves' page. Fill the details and click on [Save] button. "
				+ "New Reserve is created with respective Title name and can be viewed in Reserves table grid.");
		rvp.clickReserveNewandSaveBtn(C_reserveTitle, C_Languauge, C_registrarID, C_courseID, C_startDate, C_startTime, C_endDate, C_endTime, C_ISBN);
		Log.info("Condition in Test Scenario Id: TS_RS_01 is Executed Successfully");
		

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
			Log.info("Browser is closed");
		

	}

}
