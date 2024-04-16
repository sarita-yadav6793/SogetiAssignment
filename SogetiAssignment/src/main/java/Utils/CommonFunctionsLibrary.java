package Utils;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import DataProvider.PropertiesFileReader;

public class CommonFunctionsLibrary extends Driver{
	private Logger logger;	
	static PropertiesFileReader pfr = new PropertiesFileReader();
	
		public CommonFunctionsLibrary() {
		
			}
	//retrieve value from default properties file
			public static String readPropertiesData(String key) throws IOException {
				
				String val= pfr.readDefaultProperties(key);
				return val;
				
			}

	// click button
			public void clickButton(WebElement element) {
				new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(element));
				element.click();
			}
			
	// enter text in textbox
			public void enterTextInTextBox(WebElement element, String txt) {
				new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(element));
				if(element.isEnabled()) {
					element.clear();
					element.click();
					element.sendKeys(txt);
				}
			}
			
	//generate random string
			public  String generateRandomString(String possiblechars, int length) {
			        StringBuilder strbuilder = new StringBuilder();
			        Random rand = new Random();
			        for(int i=0;i<length;i++) {
			        	strbuilder.append(possiblechars.charAt(rand.nextInt(possiblechars.length())));
			        }
			        return strbuilder.toString();
			}
			
	// select checkbox
			public void selectCheckBox(WebElement element) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				if(!element.isSelected()) {
					element.click();
				}
			}
			
	// scroll element
			public void scrollelement(WebElement element) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", element);
			}
			
	//select drop down by text
			public void selectDDByText(WebElement element, String txt) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				Select select= new Select(element);
				select.selectByVisibleText(txt);
			}
			
	//generate random number
			public int generateRandomNumber() {
				Random r = new Random();
				int random_no= r.nextInt(1000000000);
				return random_no;
			}
			
	
		
}
