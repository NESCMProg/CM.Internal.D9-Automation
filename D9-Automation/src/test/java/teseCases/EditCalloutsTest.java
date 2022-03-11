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

import pageObjects.DashboardPage;
import pageObjects.HomePage;
import pageObjects.LoginPatron;
import pageObjects.calloutViewPage;
import resources.base;
import utility.Log;
import utility.TestUtil;
import utility.readCredentials;

public class EditCalloutsTest extends base {
	LoginPatron loginpatron	;
	HomePage hp;
	DashboardPage dashboardpage;
	calloutViewPage covp;
	EditCalloutsTest editcallout;
	readCredentials rc = new readCredentials();	
	
	public EditCalloutsTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser is opened");
		Log.info("Callout Edit Functionality");
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
	
	/*@DataProvider
	public Object[][] UpdateCalloutData()
	{
		
		Object data[][]=null;
		try {
			data = TestUtil.getTestData("updatecallout");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}*/
	
	@DataProvider
	public Object[][] InputCalloutData()
	{
		
		Object data[][]=null;
		try {
			data = TestUtil.getTestData("inputcalloutttitle");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	
	
	/*@Test(dataProvider="UpdateCalloutData", priority=2)
	public void updateTest(String EditTitle1, String EditSubtitle1, String EditDescription) {
		
		String abc = driver.findElement(By.xpath("//tr[@class='odd']/td[1]")).getText();
		System.out.println(abc);
		if(abc.equals(EditTitle1))
		System.out.println(EditTitle1);
		System.out.println(EditTitle1);
		{
			driver.findElement(By.xpath("//tr[@class='odd']/td[1]//following-sibling::td[6]/a[1]")).click();
			Log.info("Module Name: Callouts");
			Log.info("Test Case ID: TS_CO_05");
			Log.info("Test Designed By: Charan");
			Log.info("Test Priority: Medium");
			Log.info("Test Executed By: "+machineName );
			Log.info("Test Executed Date: "+currentDateTime);
			Log.info("Test Description: Navigate to Callouts Page in Callouts Tile. Select the first Callout in the table grid."
					+" Edit the callout and save successfully.");
			covp.clickEditandSaveBtn(EditTitle1, EditSubtitle1, EditDescription);
			System.out.println("clickEditandSaveBtn method is applied");
		}
		
	}*/
	
	@Test(dataProvider="InputCalloutData", priority=1)
	public void provideCalloutTitle(String inputExcelTitle, String editedTitle1) {
		Log.info("Module Name: Callouts");
		Log.info("Test Case ID: TS_CO_05");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Test Executed By: "+machineName );
		Log.info("Test Executed Date: "+currentDateTime);
		Log.info("Test Description: Navigate to Callouts Page in Callouts Tile. Search one callout in search bar. If the following callout is present in Callout"
				+ " table grid, then edit the following callout and save. If Callout is not present in table grid, user will be"+
				" informed no such callout is present in this Callout table grid"
				+ " Note: Callout value is taken from Excel sheet : inputcallouttitle");
		covp.searchCalloutTitle(inputExcelTitle, editedTitle1);
		Log.info("Test Result: Pass");
		System.out.println(inputExcelTitle);
		System.out.println("searchCalloutTitle ,method is implemented");
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
