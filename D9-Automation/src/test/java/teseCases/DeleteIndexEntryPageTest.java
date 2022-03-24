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
import pageObjects.indexEntriesViewPage;
import pageObjects.indexPagesViewPage;
import resources.base;
import utility.Log;
import utility.TestUtil;
import utility.readCredentials;

public class DeleteIndexEntryPageTest extends base {
	LoginPatron loginpatron	;
	HomePage hp;
	DashboardPage dashboardpage;
	indexDashboardPage ixdp;
	indexEntriesViewPage ievp;
	DeleteIndexEntryPageTest delindexentrypage;
	readCredentials rc = new readCredentials();
	
	public DeleteIndexEntryPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser is opened");
		Log.info("Test functionality to delete an Index Entry");
		 driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
		 loginpatron = new LoginPatron();
		 String username1 = rc.ReadCellData(1,0);
		String password1 = rc.ReadCellData(1,1);
		 hp = new HomePage();
		hp =  loginpatron.login(username1,password1);
		 dashboardpage = new DashboardPage();
		 dashboardpage = hp.clickOnDashboardLink();
		 ixdp = new indexDashboardPage();
		ixdp = dashboardpage.clickOnIndexPagesLink();
		ievp = new indexEntriesViewPage();
		ievp = ixdp.clickOnDashToIndexEntryPage();
		
	}
	
	@DataProvider
	public Object[][] getLoginData()
	{
		
		Object data[][]=null;
		try {
			data = TestUtil.getTestData("login");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return data;
	}
	
	@DataProvider
	public Object[][] getdeleteIndexEntryData()
	{
		Object data[][]=null;
		try {
			data = TestUtil.getTestData("deleteindexentry");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	@Test(dataProvider="getdeleteIndexEntryData")
	public void deleteTest(String D_indexEntryTitle, String D_Languauge, String D_IndexURL, String deletedIndexEntry) {
		Log.info("Test Case ID: TS_IEP_03");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Module Name: Index Entries");
		Log.info("Test Executed By: "+machineName );
		Log.info("Test Executed Date: "+currentDateTime);
		Log.info("Test Description: To navigate to Index Entries Tile, click on Index Page tile in dashboard. Two tiles present, namely - [Index Page], [Index Entries]. Again click on [Index Entries] tile to view 'Index Entries' page."
				+" Create a new Index Entry and then again identify the Index Entry Title from search bar. Then delete the following Index Entry");
		
		try {
			ievp.deleteIndexEntryBtn(D_indexEntryTitle, D_Languauge, D_IndexURL, deletedIndexEntry);
			Log.info("'"+D_indexEntryTitle+"'"+ " is created first");
			Log.info("'"+deletedIndexEntry+"'"+" is deleted successfully");
		}
		catch(Exception e1) {
			Log.error("Failed to Delete: "+deletedIndexEntry);
		}
		
		Log.info("Test Result: Pass");
		System.out.println("For Deletetion, deleteIndexPageBtn method is implemented");
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
		try {
			Log.info("Browser is closed");
		}
		catch(Exception e) {
			Log.error(e.getMessage());
		}
		
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

