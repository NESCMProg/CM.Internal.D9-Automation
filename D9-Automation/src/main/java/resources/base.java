package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.Date;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class base {

	public static WebDriver driver;
	public static Properties prop;
	public static String machineName;
	public static String currentDateTime;
	
	public WebDriver initialization() throws IOException {
		DOMConfigurator.configure("log4j.xml");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = new Date();
		currentDateTime = formatter.format(date1);
		 machineName = System.getProperty("user.name");
		 prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"src\\main\\java\\library\\config");
		prop.load(fis);
		
		//String browserName = System.getProperty("browser");
		//String browserName = prop.getProperty("browser");
                String browserName = System.getProperty("browser");
	
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"src\\main\\java\\library\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"src\\main\\java\\library\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("IE")) {
			System.setProperty("webdriver.edge.driver",
					"src\\main\\java\\library\\msedgedriver.exe");
			driver = new EdgeDriver();	
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(45,TimeUnit.SECONDS);
		driver.get(prop.getProperty("D9URL"));
		return driver;
		
	}
	
	
	
}
