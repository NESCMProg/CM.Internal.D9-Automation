package teseCases;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CustomBlockViewPage;
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

public class EditIndexPageTest extends base {
	LoginPatron loginpatron;
	HomePage hp;
	DashboardPage dashboardpage;
	indexDashboardPage ixdp;
	indexPagesViewPage ipvp;
	EditIndexPageTest editindexpage;
	readCredentials rc = new readCredentials();

	public EditIndexPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser opened");
		Log.info("Index Page Edit Functionality");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		loginpatron = new LoginPatron();
		String username1 = rc.ReadCellData(1, 0);
		String password1 = rc.ReadCellData(1, 1);
		hp = new HomePage();
		hp = loginpatron.login(username1, password1);
		Thread.sleep(1000);
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

	/*
	 * @DataProvider public Object[][] UpdateCustomBlockData() {
	 * 
	 * Object data[][]=null; try { data = TestUtil.getTestData("updatecustom"); }
	 * catch (InvalidFormatException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } return data; }
	 */

	@DataProvider
	public Object[][] InputIndexPageData() {

		Object data[][] = null;
		try {
			data = TestUtil.getTestData("inputindextitle");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	/*
	 * @Test(dataProvider="UpdateCustomBlockData", priority=2) public void
	 * updateTest(String U_title, String U_externalLink, String U_author, String
	 * U_naWorkFlowcmt) {
	 * 
	 * String abc =
	 * driver.findElement(By.xpath("//tr[@class='odd']/td[1]")).getText();
	 * System.out.println(abc); if(abc.equals(U_title)) System.out.println(U_title);
	 * System.out.println(U_title); { driver.findElement(By.xpath(
	 * "//tr[@class='odd']/td[1]//following-sibling::td[6]/a[1]")).click();
	 * Log.info("Module Name: News Articles"); Log.info("Test Case ID: TS_NA_05");
	 * Log.info("Test Designed By: Charan"); Log.info("Test Priority: Medium");
	 * Log.info("Test Executed By: "+machineName );
	 * Log.info("Test Executed Date: "+currentDateTime); Log.
	 * info("Test Description: Navigate to News Articles Page in News Articles Tile. Select the first Article in the table grid."
	 * +" Edit the News Article and save successfully.");
	 * navp.clickEditandSaveBtn(U_title, U_externalLink, U_author, U_naWorkFlowcmt);
	 * System.out.println("clickEditandSaveBtn method is applied"); }
	 * 
	 * }
	 */

	@Test(dataProvider = "InputIndexPageData", priority = 1)
	public void provideIndexPageTitle(String inputIndexTitle, String E_IndexTitle, String E_Languauge) {
		Log.info("Module Name: Index Pages");
		Log.info("Test Case ID: TS_IIP_02");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Test Executed By: " + machineName);
		Log.info("Test Executed Date: " + currentDateTime);
		Log.info(
				"Test Description: To navigate to Index Pages Tile, click on Index Page tile in dashboard. Two tiles present, namely - [Index Page], [Index Entry]. Again click on [Index Page] tile to view 'Index Pages' page. Search one Index Page Title in search bar. If the following index title is present in Index Pages"
						+ " table grid, then edit the following Index Page and save. If Index title is not present in table grid, user will be"
						+ " informed no such Index Page is present in this Index Pages table grid"
						+ " Note: Index Page title value is taken from Excel sheet : inputindextitle");
		ipvp.searchIndexPageTitle(inputIndexTitle, E_IndexTitle, E_Languauge);
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
