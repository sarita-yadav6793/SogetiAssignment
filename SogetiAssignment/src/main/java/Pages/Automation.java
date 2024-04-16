package Pages;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.CommonFunctionsLibrary;
import Utils.Driver;


public class Automation extends Homepage {
	private Logger logger;
	String  redColorhexval= "#ff304c";
	
	@FindBy(xpath="//span[text()='Automation']")
	WebElement	txtAutomation;
	
	@FindBy(xpath="//h2[text()='Contact us:']")
	WebElement	txtContactUs;
	
	@FindBy(xpath="//label[text()='Country']/following-sibling::div/select")
	WebElement	selectCountry;
	
	@FindBy(xpath="//label[text()='First Name']/following-sibling::input")
	WebElement	txtFirstName;
	
	@FindBy(xpath="//label[text()='Last Name']/following-sibling::input")
	WebElement	txtLastName;
	
	@FindBy(xpath="//label[text()='Email']/following-sibling::input")
	WebElement	txtEmail;
	
	@FindBy(xpath="//label[text()='Phone']/following-sibling::input")
	WebElement	txtPhone;
	
	@FindBy(xpath="//label[text()='Company']/following-sibling::input")
	WebElement	txtCompany;
	
	@FindBy(xpath="//label[text()='Message']/following-sibling::textarea")
	WebElement	txtareaMessage;
	
	@FindBy(xpath="//input[@value='I agree']")
	WebElement	chkboxagree;
	
	@FindBy(name="submit")
	WebElement	btnSubmit;
	
	@FindBy(xpath ="//img[@alt='linked in']")
	WebElement	imgLinkedin;
	
	@FindBy(xpath="//div[@class='Form__Status__Message Form__Success__Message']/p")
	WebElement	txtSuccessMsg;
	
	// constructor of the class
	public Automation() {
		logger= Logger.getLogger(this.getClass());
		comfunctions= new CommonFunctionsLibrary();
		PageFactory.initElements(driver, this);
		
	}
	
	//verify Automation is displayed
	public void verifyAutomationText() {
		Assert.assertTrue("Automation page is displayed, automation link is working fine", txtAutomation.isDisplayed());
		logger.info("Automation page is displayed");
	}
	
	// verify Services and Automation links are selected
	public void verifyServicesAutomationSelected() {
		String serviceslinkcolor = servicesMainMenu.getCssValue("color");
		String serviceslinkcolorhex=Color.fromString(serviceslinkcolor).asHex();
		String automationlinkcolor = servicessubmenuAutomation.getCssValue("color");
		String automationlinkcolorhex=Color.fromString(automationlinkcolor).asHex();
		Assert.assertEquals("Services link color is not red, means it is not selected",serviceslinkcolorhex,redColorhexval);
		Assert.assertEquals("Automation link color is not red, means it is not selected",automationlinkcolorhex,redColorhexval);
	}
	
	public WebElement generateContactfieldsxpath(String fieldname) {
		WebElement element	= driver.findElement(By.xpath("//label[text()='"+fieldname+"']/following-sibling::input"));
		return	element;
	
	}

	
	public void fillcontactform(String country) {
		String possiblechars="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		comfunctions.scrollelement(imgLinkedin);
		comfunctions.enterTextInTextBox(txtFirstName, comfunctions.generateRandomString(possiblechars,10));
		comfunctions.enterTextInTextBox(txtLastName, comfunctions.generateRandomString(possiblechars,10));
		comfunctions.enterTextInTextBox(txtEmail, comfunctions.generateRandomString(possiblechars,10)+"@gmail.com");
		comfunctions.enterTextInTextBox(txtPhone, String.valueOf(comfunctions.generateRandomNumber()));
		comfunctions.enterTextInTextBox(txtCompany, comfunctions.generateRandomString(possiblechars,10));
		comfunctions.selectDDByText(selectCountry, country);
		comfunctions.enterTextInTextBox(txtareaMessage, comfunctions.generateRandomString(possiblechars,20));
		comfunctions.scrollelement(selectCountry);
		wait.until(ExpectedConditions.visibilityOf(chkboxagree));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", chkboxagree);
		/* this below step can not be verified because of captcha which is by definition a verification to verify it is human therefore
		 *verifying it with automated program solely finishes it purpose.
		 * 
		 * Ways to overcome:
		 * 1. either add manual wait and then do manually cerify captcha --- but this method is not useful for more number of tests and 
		 * when we are running in pipeline.
		 * 2. BY disabling captcha in testing environment using google reCAPTCHA v2 by using site key and secret key where 
		 * it shows that captcha is bypassed for testing purpose.
		 * 3. By adding hooks which tells that it is automated program not the human and its like bypassing captcha
		 */
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));

		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-checkmark"))).click();
		comfunctions.clickButton(btnSubmit);
		
		
	}
	
	public void verifySuccessMsg(String message) {
		Assert.assertEquals(message, txtSuccessMsg.getText());
	}

}
