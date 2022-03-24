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
import resources.base;
import utility.Log;
import utility.TestUtil;
import utility.readCredentials;

public class DeleteCalloutTest extends base {
	LoginPatron loginpatron	;
	HomePage hp;
	DashboardPage dashboardpage;
	calloutViewPage covp;
	DeleteCalloutTest delcallout;
	readCredentials rc = new readCredentials();
	
	public DeleteCalloutTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser is opened");
		Log.info("Test functionality to delete callout");
		 driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
		 loginpatron = new LoginPatron();
		 String username1 = rc.ReadCellData(1,0);
		String password1 = rc.ReadCellData(1,1);
		 hp = new HomePage();
		hp =  loginpatron.login(username1,password1);
		 dashboardpage = new DashboardPage();
		 dashboardpage = hp.clickOnDashboardLink();
		 covp = new calloutViewPage();
		 covp = dashboardpage.clickOnCalloutsLink();
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
	public Object[][] getdeleteData()
	{
		Object data[][]=null;
		try {
			data = TestUtil.getTestData("deletecallout");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	@Test(dataProvider="getdeleteData")
	public void deleteTest(String title1_D, String deleteCalloutTitle) {
		Log.info("Test Case ID: TS_CO_03");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Module Name: Callouts");
		Log.info("Test Executed By: "+machineName );
		Log.info("Test Executed Date: "+currentDateTime);
		Log.info("Test Description: Log in to EBSCO Enterprise Research application to create a callout first. Then identify the created ones in callout"
				+" table and delete the following callout");
		
		try {
			covp.deleteCalloutBtn(title1_D, deleteCalloutTitle);
			Log.info("'"+title1_D+"'"+"callout is created first");
			Log.info("'"+deleteCalloutTitle+"'"+" is deleted successfully");
		}
		catch(Exception e1) {
			Log.error("Failed to Delete: "+deleteCalloutTitle);
		}
		
		Log.info("Test Result: Pass");
		System.out.println("For Deletetion, deleteCalloutBtn method is implemented");
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	

