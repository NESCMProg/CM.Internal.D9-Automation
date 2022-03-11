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
import pageObjects.NewsArticleViewPage;
import pageObjects.calloutViewPage;
import resources.base;
import utility.Log;
import utility.TestUtil;
import utility.readCredentials;

public class EditNewsArticlesTest extends base {
	LoginPatron loginpatron;
	HomePage hp;
	DashboardPage dashboardpage;
	NewsArticleViewPage navp;
	EditNewsArticlesTest editnewsarticle;
	readCredentials rc = new readCredentials();	
	
	public EditNewsArticlesTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		Log.info("Browser opened");
		Log.info("News Article Edit Functionality");
		 driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
		 loginpatron = new LoginPatron();
		 String username1 = rc.ReadCellData(1,0);
		String password1 = rc.ReadCellData(1,1);
		hp = new HomePage();
		hp =  loginpatron.login(username1,password1);
		 dashboardpage = new DashboardPage();
		 dashboardpage = hp.clickOnDashboardLink();
		 navp = new NewsArticleViewPage();
		 navp = dashboardpage.clickOnNewsArticlesLink();
		
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
	public Object[][] UpdateNewsArticleData()
	{
		
		Object data[][]=null;
		try {
			data = TestUtil.getTestData("updatenews");
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
	public Object[][] InputNewsArticleData()
	{
		
		Object data[][]=null;
		try {
			data = TestUtil.getTestData("inputnews");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	
	
	/*@Test(dataProvider="UpdateNewsArticleData", priority=2)
	public void updateTest(String U_title, String U_externalLink, String U_author, String U_naWorkFlowcmt) {
		
		String abc = driver.findElement(By.xpath("//tr[@class='odd']/td[1]")).getText();
		System.out.println(abc);
		if(abc.equals(U_title))
		System.out.println(U_title);
		System.out.println(U_title);
		{
			driver.findElement(By.xpath("//tr[@class='odd']/td[1]//following-sibling::td[6]/a[1]")).click();
			Log.info("Module Name: News Articles");
			Log.info("Test Case ID: TS_NA_05");
			Log.info("Test Designed By: Charan");
			Log.info("Test Priority: Medium");
			Log.info("Test Executed By: "+machineName );
			Log.info("Test Executed Date: "+currentDateTime);
			Log.info("Test Description: Navigate to News Articles Page in News Articles Tile. Select the first Article in the table grid."
					+" Edit the News Article and save successfully.");
			navp.clickEditandSaveBtn(U_title, U_externalLink, U_author, U_naWorkFlowcmt);
			System.out.println("clickEditandSaveBtn method is applied");
		}
		
	}*/
	
	@Test(dataProvider="InputNewsArticleData", priority=1)
	public void provideNewsArticleTitle(String inputNewsTitle, String editedTitle, String E_author) {
		Log.info("Module Name: News Article");
		Log.info("Test Case ID: TS_NA_05");
		Log.info("Test Designed By: Charan");
		Log.info("Test Priority: High");
		Log.info("Test Executed By: "+machineName );
		Log.info("Test Executed Date: "+currentDateTime);
		Log.info("Test Description: Navigate to News Articles Page in News Articles Tile. Search one Article in search bar. If the following Article is present in News Articles"
				+ " table grid, then edit the following Article and save. If Article is not present in table grid, user will be"+
				" informed no such News Article is present in this News Article table grid"
				+ " Note: Article Title value is taken from Excel sheet : inputnews");
		navp.searchNewsArticleTitle(inputNewsTitle, editedTitle, E_author);
		Log.info("Test Result: Pass");
		System.out.println();
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
