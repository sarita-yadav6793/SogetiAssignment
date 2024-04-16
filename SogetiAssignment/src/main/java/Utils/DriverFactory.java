package Utils;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
private static Logger logger= Logger.getLogger(DriverFactory.class);
	
	public static WebDriver getDriver() throws IOException {
		String browser= CommonFunctionsLibrary.readPropertiesData("BROWSER");
		logger.info(Class.class.getClass() + "--------------Initialise Driver----------------");
		switch (browser){
			case "IE":
				String iepath="drivers/IEDriver/IEDriver.exe";
				System.setProperty("webdriver.ie.driver",iepath);
				return new InternetExplorerDriver();
			case "CHROME":
				//String chromepath="drivers/ChromeDriver/chromedriver";
				//System.setProperty("webdriver.chrome.driver", chromepath);
				return new ChromeDriver();
				
			default:
				return new FirefoxDriver();
		}
	}
	
	public static void closeDriver(WebDriver driver) {
		try {
			driver.quit();
			logger.info("---------------Driver Closed--------------");
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
		
		
	}

}
