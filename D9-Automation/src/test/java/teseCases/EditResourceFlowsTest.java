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
import pageObjects.resourceViewPage;
import resources.base;
import utility.Log;
import utility.TestUtil;
import utility.readCredentials;

public class EditResourceFlowsTest extends base {
	LoginPatron loginpatron	;
	HomePage hp;
	DashboardPage dashboardpage;
	resourceViewPage rfvp;
	EditResourceFlowsTest editResourceFlow;
	readCredentials rc = new readCredentials();	
	
	public EditResourceFlowsTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser opened");
		Log.info("Resource Flow Edit Functionality");
		 loginpatron = new LoginPatron();
		 String username1 = rc.ReadCellData(1,0);
		String password1 = rc.ReadCellData(1,1);
		hp =  loginpatron.login(username1,password1);
		Thread.sleep(1000);
		dashboardpage = new DashboardPage();
		dashboardpage = hp.clickOnDashboardLink();
		 driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		 rfvp = new resourceViewPage();
		 rfvp = dashboardpage.clickOnResourceFlowLink();
		
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
	public Object[][] UpdateResourceData()
	{
		
		Object data[][]=null;
		try {
			data = TestUtil.getTestData("updateresourceflows");
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
	public Object[][] InputResourceFlowData()
	{
		
		Object data[][]=null;
		try {
			data = TestUtil.getTestData("inputresourceflowtitle");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	
	
	/*@Test(dataProvider="UpdateResourceData", priority=2)
	public void updateTest(String inputEditedTitle, String Edited_ISBN_1, String Edit_UPC1, String Edit_title1, String Edit_author1, String Edit_rfWorkFlowcmt1 , String Edit_resourceflowrlm1   ) {
		
		String abc = driver.findElement(By.xpath("//tr[@class='odd']/td[1]")).getText();
		System.out.println(abc);
		if(abc.equals(inputEditedTitle))
		System.out.println(inputEditedTitle);
		System.out.println(inputEditedTitle);
		{
			driver.findElement(By.xpath("//tr[@class='odd']/td[1]//following-sibling::td[6]/a[1]")).click();
			Log.info("Module Name: Resource Flows");
			Log.info("Test Case ID: TS_RF_05");
			Log.info("Test Designed By: Charan");
			Log.info("Test Priority: Medium");
			Log.info("Test Executed By: "+machineName );
			Log.info("Test Executed Date: "+currentDateTime);
			Log.info("Test Description: Navigate to Resource Flows Page in Resource Flow Tile. Select the first Resource Flow in the table grid."
					+" Edit the Resource Flow and save successfully.");
			rfvp.clickEditandSaveBtn(inputEditedTitle,Edited_ISBN_1, Edit_UPC1, Edit_title1, Edit_author1, Edit_rfWorkFlowcmt1, Edit_resourceflowrlm1);
			System.out.println("clickEditandSaveBtn method is applied");
		}
		
	}*/
	
	@Test(dataProvider="InputResourceFlowData", priority=1)
	public void provideResourceTitle(String existingTitle, String EditedTitle, String E_UPC1, String E_title1, String E_author1) {
		Log.info("Module Name: Resource Flows");
		Log.info("Test Case ID: TS_RF_02");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Test Executed By: "+machineName );
		Log.info("Test Executed Date: "+currentDateTime);
		Log.info("Test Description: Navigate to resource Flows Page in Resource Flows Tile. Search one Resource Flow Title in search bar. If the following title is present in Resource"
				+ " Flows table grid, then edit the following Resource Flow and save. If Resource Flow is not present in table grid, user will be"+
				" informed no such Resource Flow Title is present in this Resource Flows table grid"
				+ " Note: Resource Flow Title value is taken from Excel sheet : inputresourceflowtitle");
		rfvp.searchResourceTitle(existingTitle, EditedTitle, E_UPC1, E_title1, E_author1);
		Log.info("Test Result: Pass");
		System.out.println(existingTitle);
		System.out.println(EditedTitle);
		System.out.println("searchResourceTitle method is implemented");
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
