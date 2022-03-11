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
import pageObjects.DataBaseListingsViewPage;
import pageObjects.HomePage;
import pageObjects.LoginPatron;
import pageObjects.calloutViewPage;
import pageObjects.researchGuidesViewPage;
import resources.base;
import utility.TestUtil;

public class CreateResearchGuidesTestPage extends base {
	LoginPatron loginpatron;
	HomePage hp;
	DashboardPage dashboardpage;
	researchGuidesViewPage rgvp;
	CreateResearchGuidesTestPage createresearchguide;
	readCredentials rc = new readCredentials();

	public CreateResearchGuidesTestPage() {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser is opened");
		Log.info("Test Functionality for creating Research Guide");
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		loginpatron = new LoginPatron();
		String username1 = rc.ReadCellData(1, 0);
		String password1 = rc.ReadCellData(1, 1);
		hp = new HomePage();
		hp = loginpatron.login(username1, password1);
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
	public Object[][] getResearchGuideData() {

		Object data[][] = null;
		try {
			data = TestUtil.getTestData("research");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Test(dataProvider = "getResearchGuideData")
	public void newResearchGuideTest(String C_researchTitle, String C_termRG, String C_tabTitle_1, String C_tabURL_1) throws InterruptedException {
		Log.info("Module Name: Research Guides");
		Log.info("Test Case ID: TS_RG_01");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Test Executed By: "+machineName );
		Log.info("Test Executed Date: " + currentDateTime);
		Log.info("Test Description: Navigate to 'Research Guides Page' in Research Guides Tile. Click on [+Add Research guide] button to create a new Research Guide."
				+ " Page navigates to 'Create Research Guide' page. Fill the details and click on [Save] button. "
				+ "New Research Guide is created with respective Title name and can be viewed in Research Guides table grid.");
		rgvp.clickResearchNewandSaveBtn(C_researchTitle, C_termRG, C_tabTitle_1, C_tabURL_1);
		Log.info("Condition in Test Scenario Id: TS_RG_01 is Executed Successfully");
		Log.info("Test Result: Pass");

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
			Log.info("Browser is closed");
		

	}

}
