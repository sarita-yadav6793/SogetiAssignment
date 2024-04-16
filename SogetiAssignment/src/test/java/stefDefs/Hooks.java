package stefDefs;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import Utils.CommonFunctionsLibrary;
import Utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks {
	public static CommonFunctionsLibrary cfl;
	private Logger logger= Logger.getLogger(this.getClass());
	Driver d= new Driver();
	
	
	@Before("@uiautomation")
	public void initializeDriver() throws IOException {
		PropertyConfigurator.configure(CommonFunctionsLibrary.readPropertiesData("log4jpath"));
		d.initialiseDriver();	
		d.loadApplication(CommonFunctionsLibrary.readPropertiesData("URL"));
	}
	
	@After(order = 1,value="@uiautomation")
	 public void afterScenario(Scenario scenario) {
		if(scenario.isFailed())
	    {	
			TakesScreenshot ts = (TakesScreenshot) Driver.driver;

			byte[] src = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(src, "image/png", scenario.getName());
	    }
	 }
	
	@After(order = 0,value="@uiautomation")
	public void  close() {	
		 d.closeDriver();
	}
	

}
