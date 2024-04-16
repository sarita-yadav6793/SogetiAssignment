package Pages;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utils.CommonFunctionsLibrary;
import Utils.Driver;

public class Homepage extends Driver{
	static boolean	flag= false;
	
	private Logger logger;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	
	@FindBy(xpath="//img[@alt='Sogeti Logo']")
	WebElement sogetiLogo;
	
	@FindBy(xpath="//*[@id='main-menu']//div[@class='wrapper']/span[text()='Services']")
	WebElement	servicesMainMenu;
	
	@FindBy(xpath="//a[@class='subMenuLink' and text()='Automation']")
	WebElement	servicessubmenuAutomation;
	
	@FindBy(xpath="//button[@class='acceptCookie']")
	WebElement	acceptCookie;
	
	@FindBy(xpath="//span[text()='Worldwide']/following-sibling::div")
	WebElement	linkWorldwide;
	
	@FindBys(@FindBy(xpath="//div[@id='country-list-id']//li/a"))
	List<WebElement>	listCountry;
	
	
	
	//constructor of the class
	public Homepage() {
		
		logger= Logger.getLogger(this.getClass());
		comfunctions= new CommonFunctionsLibrary();
		PageFactory.initElements(driver, this);
	}
	
	//verify sogeti logo
	public void verifySogetiLogo()
	{
		Assert.assertTrue(sogetiLogo.isDisplayed());
		logger.info("Sogeti logo is displayed, URL is navigating correctly.");
		acceptCookies();
	}
	
	//Click automation submenu
	public void clickAutomation() {
		Actions actions = new Actions(driver);
		actions.moveToElement(servicesMainMenu).perform();
		
		wait.until(ExpectedConditions.visibilityOf(servicessubmenuAutomation)).click();
		
		
	}
	
	//accept cookies
	public void acceptCookies() {
		wait.until(ExpectedConditions.elementToBeClickable(acceptCookie)).click();
	}
	
	
	// switching window and verifying links
	public boolean verifyCountryLinksOnDiffPage(String expectedURL) {
		String mainWindow= driver.getWindowHandle();
		Set<String> childWindows= driver.getWindowHandles();
		Iterator<String> i= childWindows.iterator();
		while(i.hasNext()) {
			String countryWindow= i.next();
			if(!mainWindow.equalsIgnoreCase(countryWindow)) {
				driver.switchTo().window(countryWindow);
				String currentURL= driver.getCurrentUrl();
				assertEquals("Country URL is not working fine",currentURL, expectedURL);
				flag=true;
				driver.close();
			}
		
		}
		driver.switchTo().window(mainWindow);
		
		return flag;
	}
					
	// Click Worldwide
	public void clickWorldWide() {
		comfunctions.clickButton(linkWorldwide);
	}
	
	//verify country links
	public void verifyCountryLinks() {
		int noOfCountries= listCountry.size();
		logger.info("No. of countries in list are:"+ noOfCountries);
		for(WebElement element : listCountry) {
			logger.info("-------Verifying link for Country  "+element.getText()+"-------");
			String countrylink= element.getAttribute("href");
			comfunctions.clickButton(element);
			flag= verifyCountryLinksOnDiffPage(countrylink);
			logger.info("-------Verification for Country  "+element.getText()+" link is successful-------");
			
		}
		
		
	}
	
	public void displayCountryLinkWorking() {
			if(flag=true) {
				logger.info("success");
			}
	}
	

}
