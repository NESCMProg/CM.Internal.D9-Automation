package teseCases;
import utility.Log;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import pageObjects.HomePage;
import pageObjects.LoginPatron;
import pageObjects.calloutViewPage;
import resources.base;
import utility.TestUtil;

public class LoginPageTest extends base {
//public static Logger log =LogManager.getLogger(LoginPageTest.class.getName());
	
LoginPatron loginpatron	;
HomePage hp;

	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws IOException {
		initialization();
		System.out.println("driver is initialized for Login Patron Page");
		Log.info("Browser Opened the Application");
		 loginpatron = new LoginPatron();
		 hp = new HomePage();
		 
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
	
	/*@Test(priority=2)
	public void validatepatronLoginPageTitleTest() {
		Log.info("Test Case ID: TS_LP_01");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Test Executed By: "+machineName );
		Log.info("Test Executed Date: " +currentDateTime);
		Log.info("Test Description: Open the application in browser and verify the Title of the application");
		String title = loginpatron.validatepatronLoginPageTitle();
		Assert.assertEquals(title, "EBSCOhost");
		Log.info("Title for Login Patron page is verified : "+title);
		Log.info("Test Result: Pass");
	}*/
	
	@Test(dataProvider="getLoginData", priority=1)
	public void loginTest(String username, String password) throws InterruptedException
	{
		Log.info("Test Case ID: TS_LP_01");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Test Executed By: "+machineName );
		Log.info("Test Executed Date: " +currentDateTime);
		Log.info("Test Description: Open application in browser. Then give right patron credentials and click on Login button. Patron "
				+ "should be able to access the application. If wrong credentials are submitted patron should not get "
				+ "access to open application.");
		hp = loginpatron.login(username, password);
		Log.info("Test Result: Pass");
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
