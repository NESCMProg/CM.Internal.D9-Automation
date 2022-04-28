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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CustomBlockViewPage;
import pageObjects.DashboardPage;
import pageObjects.HomePage;
import pageObjects.LoginPatron;
import pageObjects.NewsArticleViewPage;
import pageObjects.calloutViewPage;
import resources.base;
import utility.TestUtil;

public class CreateCustomBlockTestPage extends base {
	LoginPatron loginpatron;
	HomePage hp;
	DashboardPage dashboardpage;
	CreateCustomBlockTestPage createcustom;
	CustomBlockViewPage cbvp;
	readCredentials rc = new readCredentials();

	public CreateCustomBlockTestPage() {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser is opened");
		Log.info("Test functionality for creating a Custom Block");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		loginpatron = new LoginPatron();
		String username1 = rc.ReadCellData(1, 0);
		String password1 = rc.ReadCellData(1, 1);
		Thread.sleep(1000);
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
	public Object[][] getCustomBlockData() {

		Object data[][] = null;
		try {
			data = TestUtil.getTestData("custom");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Test(dataProvider = "getCustomBlockData")
	public void newCustomCreateTest(String title1, String C_RLM) throws InterruptedException {
		Log.info("Module Name: Custom Block");
		Log.info("Test Case ID: TS_CB_01");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Test Executed By: " + machineName);
		Log.info("Test Executed Date: " + currentDateTime);
		Log.info("Test Description: Navigate to Custom Block Page in Custom Block Tile. Click on [Add custom block] button to create a new Custom."
						+ " Page navigates to 'Create Custom Block' page. Fill the details and click on [Save] button. "
						+ "New 'Custom Block' is created with respective Title name and can be viewed in Custom Block table grid.");
		cbvp.clickCustomNewBtnandSaveBtn(title1, C_RLM);
		Log.info("Condition in Test Scenario Id: TS_CB_01 is Executed Successfully");

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
		Log.info("Browser is closed");

	}

}
