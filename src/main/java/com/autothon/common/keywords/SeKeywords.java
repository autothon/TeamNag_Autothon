package com.autothon.common.keywords;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.autothon.core.DriverFactory;
import com.autothon.core.TestBase;
import com.autothon.util.CommonFunctionUtil;
import com.aventstack.extentreports.Status;
import com.jacob.com.LibraryLoader;

/**
 * This class contains all the keywords created using methods provided by Selenium Webdriver API.
 */
public class SeKeywords extends TestBase{

	/**  Web driver wait object. */
	private static WebDriverWait wait;
	
	/** The instance. */
	static	DriverFactory instance  = DriverFactory.getInstance(); 
	
	/**
	 *  This method checks the presence of element on web page.
	 *
	 * @param element	element on web page.
	 * @return true, if web element is present.
	 */
	public static boolean isElementPresent(By element){
		return isElementPresent(element, 15);
	}
	
	/**
	 * This method waits for visibility of the element present on web page for specified time.
	 *
	 * @param element   element on web page.
	 * @param secs 		time to wait in seconds (decided on the basis of application page load time)
	 * @return true, if is element present
	 */
	public static boolean isElementPresent(By element , int secs){
		boolean result = false;
		try
		{
			wait = new WebDriverWait(instance.getDriver(), secs);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			extentReportLogger().log(Status.PASS, "\"" + "Element found: " + "\"" + elementPath + "\"");
			log.info("Element found -> " + elementPath);
			result = true;
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found: " + elementPath);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.INFO, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.info("Timeout. Element not found: " + elementPath);
		} catch (Exception e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found: " + elementPath);
		}
		return result;
	}
	
	/**
	 *  This method checks the visibility of element on web page.
	 *
	 * @param element	element on web page.
	 * @return true,    if web element is visible.
	 */
	public static boolean isElementVisible(By element){
		boolean result = false;
		try{
			wait = new WebDriverWait(instance.getDriver(), 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			extentReportLogger().log(Status.PASS, "\"" + "Element is visible: " + "\"" + elementPath + "\"");
			log.info("Element is visible -> " + elementPath);
			result = true;
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found: " + elementPath);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.INFO, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.info("Timeout. Element not found: " + elementPath);
		} catch (Exception e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found: " + elementPath);
		}
		return result;
	}
	
	/**
	 *  This method checks the enable state of element on web page.
	 *
	 * @param element	element on page.
	 * @return true, 	if element is enabled.
	 */
	public static boolean isElementEnabled(By element){
		boolean result = false;
		try{
			wait = new WebDriverWait(instance.getDriver(), 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			if (instance.getDriver().findElement(element).isEnabled()) {
				extentReportLogger().log(Status.PASS, "\"" + "Element enabled: " + "\"" + elementPath + "\"");
				log.info("Element enabled -> " + elementPath);
				result = true;
			} else {
				extentReportLogger().log(Status.INFO, "\"" + "Element not enabled: " + "\"" + elementPath + "\"");
				log.info("Element not enabled -> " + elementPath);
				result = false;
			}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found: " + elementPath);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.INFO, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.info("Timeout. Element not found: " + elementPath);
		} catch (Exception e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found: " + elementPath);
		}
		return result;
	}
	
	/**
	 * Returns a value indicating whether a given attribute is present for a given element.
	 *
	 * @param element WebElement to search for a given attribute
	 * @param attribute Attribute name to test for existence on element
	 * @return true, if is attribute present
	 */
	public static boolean isAttributePresent(WebElement element, String attribute) {
		boolean result = false;
		try {
			String attrValue = element.getAttribute(attribute);
			if (attrValue != null) {
				result = true;
			}
			extentReportLogger().log(Status.PASS, "\"" + "Attribute found: " + "\"" + elementPath + "\"");
			log.info("Attribute found -> " + elementPath);
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.INFO, "\"" + "Attribute element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found: " + elementPath);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.INFO, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.info("Timeout. Element not found: " + elementPath);
		} catch (Exception e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found: "  + elementPath);
		}
		return result;
	}

	/**
	 *  Moves the mouse to the specified element.
	 *
	 * @param element	element on page.
	 */
	public static void moveToElement(By element){
		try{
			Actions a=new Actions(instance.getDriver());
			MKeywords.sleep(1000);
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			a.moveToElement(instance.getDriver().findElement(element)).build().perform();
			extentReportLogger().log(Status.PASS, "\"" + "Scrolled to " + "\"" + elementPath + "\"");
			log.info("Scrolled to -> " + elementPath);
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
	}

	/**
	 * Clear field.
	 *
	 * @param element the element
	 */
	public static void clearField(By element)
	{
		try
		{
			instance.getDriver().findElement(element).clear();
			extentReportLogger().log(Status.PASS, "\"" +" Cleared the field" + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
		}
	}

	/**
	 * This method get the access on window opened.
	 *
	 * @param element	element on web page.
	 */
	public static void switchToWindow(By element){
		try{
			Set<String> windows = instance.getDriver().getWindowHandles();
			for (String currentWindowHandle : windows) {
				instance.getDriver().switchTo().window(currentWindowHandle);
			}
			extentReportLogger().log(Status.PASS, "\"" + "Switched to window: " + "\"" + elementPath + "\"");
			log.info("Switched to window -> "+ elementPath);
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
	}

	/**
	 * Switch to the Frame style Popup.
	 *
	 * @param element	element on page.
	 */
	public static void switchToFrame(By element){
		try{
			instance.getDriver().switchTo().frame(instance.getDriver().findElement(element));
			extentReportLogger().log(Status.PASS, "\"" + "Switched to frame: " + "\"" + elementPath + "\"");
			log.info("Switched to frame -> "+ elementPath);
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
	}
	
	/**
	 *  This method get the text of web element.
	 *
	 * @param element	element on web page.
	 * @return string,	web text.
	 */
	public static String getText(By element) {
		String text = null;
		try {
			SeKeywords.moveToElement(element);
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			text = instance.getDriver().findElement(element).getText().trim();
			extentReportLogger().log(Status.PASS, "\"" + " Text of " + elementPath + " is: " + text + "\"");
			log.info("Text of " + elementPath + " is: " + text + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "getTextElement not found: " + "\"" + elementPath + "\"");
			log.error("getTextElement not found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
		return text;
	}
	
	/**
	 *  This method get the text of web element using inner HTML.
	 *
	 * @param element	element on web page.
	 * @return string,	text.
	 */
	public static String getInnerHTML(By element) {
		String text = null;
		try {
			SeKeywords.moveToElement(element);
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			text = instance.getDriver().findElement(element).getAttribute("innerHTML");
			extentReportLogger().log(Status.PASS, "\"" + " Text of " + elementPath + " is: " + text + "\"");
			log.info("Text of " + elementPath + " is: " + text + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "getTextElement not found: " + "\"" + elementPath + "\"");
			log.error("getTextElement not found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
		return text;
	}

	/**
	 *  This method get the specified attribute of web element.
	 *
	 * @param element			element on page.
	 * @param attribute			name of the attribute whose value user wants to get.
	 * @return String 			the attribute value.
	 */
	public static String getAttribute(By element, String attribute) {
		String attrValue = null;
		try {
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			attrValue = instance.getDriver().findElement(element).getAttribute(attribute);
			extentReportLogger().log(Status.PASS, "\"" + "Attribute " + "\"" + elementPath + "\"" + " value is: " + "\"" + attrValue + "\"");
			log.info("Attribute " + "\"" + elementPath + "\"" + " value is: " + "\"" + attrValue + "\"");		
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element with " + "\"" + attribute + "\"" + " attribute not found for " + "\"" + elementPath + "\"");
			log.error("Element with " + attribute + " attribute not found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
		return attrValue;
	}
	
	/**
	 * This method checks whether the string is empty or not.
	 *
	 * @param text		text to verify.
	 * @return true, 	if not empty.
	 */
	public static boolean notEmpty(String text) {
		boolean result = false;
		try {
			if (!text.equals("")) {
				extentReportLogger().log(Status.PASS, "\"" + text + " is present" + "\"" + " for " + "\"" + elementPath);
				log.info(text + " is present" + "\"" + " for " + "\"" + elementPath);
				result = true;
			}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found" + e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.INFO, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.info("Timeout. Element not found." + e);
		} catch (Exception e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found" + e);
		}
		return result;
	}

	/**
	 * This method clear the text entered in the text box.
	 *
	 * @param textBox 	the text box element.
	 */
	public static void clear(By textBox) {
		try {
			wait = new WebDriverWait(instance.getDriver(), 10);
			MKeywords.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(textBox));
			instance.getDriver().findElement(textBox).clear();
			extentReportLogger().log(Status.PASS, "\"" + " Text Box Cleared ---> " + "\"" + elementPath + "\"");
			log.info(" Text Box Cleared ---> " + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Text Box to be Cleared not found: " + "\"" + elementPath + "\"");
			log.error("Text Box to be Cleared not found" + e);
			throw(e);

		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);

		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
	}

	/**
	 *  Select the check box.
	 *
	 * @param checkBox the check box
	 */
	public static void tickCheckbox(By checkBox) {
		try{
			if(!instance.getDriver().findElement(checkBox).isSelected()) {
			instance.getDriver().findElement(checkBox).click();
			}
			extentReportLogger().log(Status.PASS, "\"" + "Ticked CheckBox " + "\"" + elementPath + "\"");
			log.info("Ticked CheckBox " + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Ticked CheckBox not found: " + "\"" + elementPath + "\"");
			log.error("Ticked CheckBox not found" + e);
			throw(e);

		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);

		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
	}

	/**
	 *  This method get the text written on the pop-up opened.
	 *
	 * @param modal		pop-up box element.
	 * @param message 	message element.
	 * @return string,	text on the pop up.
	 */
	public static String getModalPopMessage(By modal, By message) {
		String text = null;
		try {
			instance.getDriver().switchTo().activeElement();
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(modal));
			wait.until(ExpectedConditions.visibilityOfElementLocated(modal));
			text = instance.getDriver().findElement(message).getText().trim();
			extentReportLogger().log(Status.PASS, "\"" + " Pop up message is: " + "\"" + text + "\"");
			log.info(" Pop up message is: " + "\"" + text + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Modal Pop Message not found: " + "\"" + elementPath + "\"");
			log.error("Modal Pop Message not found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
		return text;
	}
	
	/**
	 *  This method click on the button present on pop up.
	 *
	 * @param modal 	 pop-up box element.
	 * @param buttonName button name on pop-up.
	 */
	public static void clickModalPopButton(By modal, String buttonName){
		try{
			wait = new WebDriverWait(instance.getDriver(), 10);
			instance.getDriver().switchTo().activeElement();
			WebElement modalbuttons = instance.getDriver().findElement(modal);
			List<WebElement> options = modalbuttons.findElements(By.className("btn"));
			for (WebElement option : options) {
				if (buttonName.equalsIgnoreCase(option.getText())){
					MKeywords.sleep(2000);
					wait.until(ExpectedConditions.elementToBeClickable(option));
					Actions a2=new Actions(instance.getDriver());
					a2.moveToElement(option).click().build().perform();
					extentReportLogger().log(Status.PASS, "Clicked on the modal pop up button"  + "\"" + elementPath + "\"");
					log.info("Clicked on the modal pop up button"  + "\"" + elementPath + "\"");
					break;
				}
			}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Modal Pop Button not found: " + "\"" + elementPath + "\"");
			log.error("Modal Pop Button not found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
	}

	/**
	 * This method waits for visibility of the element present on web page.
	 *
	 * @param element element on web page.
	 * @param secs 		time to wait in seconds.
	 * @return true, if successful
	 */
	public static boolean waitForElementVisibility(By element , int secs){
		boolean result = false;
		try
		{
			wait = new WebDriverWait(instance.getDriver(), secs);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			extentReportLogger().log(Status.PASS, "Presence of element visibilty verified for" + "\"" + elementPath + "\"");
			log.info("Presence of element visibilty verified for" + "\"" + elementPath + "\"");
			result = true;
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found" + e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.INFO, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.info("Timeout. Element not found." + e);
		} catch (Exception e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found" + e);
		}
		return result;
	}

	/**
	 * This method waits for the invisibility of the element present on web page. 
	 *
	 * @param element element on web page.
	 * @param secs 		number of second to wait.
	 * @return true, if successful
	 */
	public static boolean waitForElementInVisibility(By element , int secs){
		boolean result = false;
		try
		{
			wait = new WebDriverWait(instance.getDriver(), secs);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
			extentReportLogger().log(Status.PASS, "Element invisibility verified for " + "\"" + elementPath + "\"");
			log.info("Element invisibility verified for " + "\"" + elementPath + "\"");
			result = true;
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found" + e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.INFO, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.info("Timeout. Element not found." + e);
		} catch (Exception e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found" + e);
		}
		return result;
	}

	/**
	 * This method wait for element to be clickable on web page.
	 *
	 * @param element 	web element to wait for.
	 * @param secs 		number of seconds to wait.
	 * 
	 */
	public static void waitForElementToBeClickable(By element , int secs){
		try
		{
			wait = new WebDriverWait(instance.getDriver(), secs);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			extentReportLogger().log(Status.PASS, "Element to be clicked verified: " + "\"" + elementPath + "\"");
			log.info("Element to be clicked verified: " + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.INFO, "\"" + "Clickable Element not found: " + "\"" + elementPath + "\"");
			log.info("Clickable Element not found" + e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.INFO, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.info("Timeout. Element not found." + e);
		} catch (Exception e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found" + e);
		}
	}

	/**
	 * This method enter the value in text box.
	 *
	 * @param textBox	text box element.
	 * @param value 	value user wants to enter in the text box.
	 */
	public static void setText(By textBox, String value) {
		try{
			SeKeywords.scrollAndClick(textBox);
			wait = new WebDriverWait(instance.getDriver(), 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(textBox));
			instance.getDriver().findElement(textBox).clear();
			instance.getDriver().findElement(textBox).sendKeys(value);
			instance.getDriver().findElement(textBox).sendKeys(Keys.TAB);
			extentReportLogger().log(Status.PASS, "\"" + value + "\"" + " is entered in text box " + "\"" + elementPath + "\"");
			log.info(value + "\"" + " is entered in text box " + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Textbox to enter text not found: " + "\"" + elementPath + "\"");
			log.error("Textbox to enter text not found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
	}

	/**
	 * This method set the focus on text box and enter value in it.
	 *
	 * @param textBox	text box element.
	 * @param value 	value user wants to enter in the text box.
	 */
	public static void focusSetText(By textBox, String value) {
		try{
			wait = new WebDriverWait(instance.getDriver(), 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(textBox));
			Actions action = new Actions(instance.getDriver()); 
			action.sendKeys(Keys.TAB).build().perform();
			action.sendKeys(value).build().perform(); 
			extentReportLogger().log(Status.PASS, "\"" + value + "\"" + " is entered in text box " + "\"" + elementPath + "\"");
			log.info(value + "\"" + " is entered in text box " + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element to be focused not found: " + "\"" + elementPath + "\"");
			log.error("Element to be focused not found" + e);
			throw(e);

		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);

		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
	}

	/**
	 * This method enter value in text box and select the element appears from the drop-down list.
	 *
	 * @param textBox 	text box element.
	 * @param list 		list element.
	 * @param value 	value user wants to select.
	 */
	public static void searchAndSelect(By textBox , By list , String value){
		try {
			wait = new WebDriverWait(instance.getDriver(), 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(textBox));
			instance.getDriver().findElement(textBox).click();
			
			Actions action = new Actions(instance.getDriver());
			action.moveToElement(instance.getDriver().findElement(textBox)).sendKeys(value).build().perform();
			MKeywords.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(list));
			instance.getDriver().findElement(textBox).sendKeys(Keys.TAB);
			extentReportLogger().log(Status.PASS, "\"" + value + "\"" + " searched & selected from " + "\"" + elementPath + "\"");
			log.info(value + "\"" + " searched & selected from " + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Search Element not found: " + "\"" + elementPath + "\"");
			log.error("Search Element not found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
	}

	/**
	 *  This method enter the random generated string in the text box.
	 *
	 * @param textBox 	text box to enter generated string.
	 * @return String	unique text.
	 */
	public static String setUniqueText(By textBox) {
		String uniqueText = null;
		try{
			uniqueText = CommonFunctionUtil.generateUniqueName();
			wait = new WebDriverWait(instance.getDriver(), 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(textBox));
			instance.getDriver().findElement(textBox).clear();
			instance.getDriver().findElement(textBox).sendKeys(uniqueText);
			extentReportLogger().log(Status.PASS, "Unique text is entered in the text box " + "\"" + elementPath + "\"");
			log.info("Unique text is entered in the text box " + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Textbox to enter text not found: " + "\"" + elementPath + "\"");
			log.error("Textbox to enter text not found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
		return uniqueText;
	}

	/**
	 *  This method click on specified element on web page.
	 *
	 * @param element	element to click on web page.
	 */
	public static void click(By element) {
		try {
			wait = new WebDriverWait(instance.getDriver(), 15);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			instance.getDriver().findElement(element).click();
			extentReportLogger().log(Status.PASS, "\"" + " Clicked " + "\"" + elementPath + "\"");
			log.info("\"" + " Clicked " + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Clickable Element not found: " + "\"" + elementPath + "\"");
			log.error("Clickable Element not found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
	}
	
	/**
	 * Waits(for given time in seconds) for the visibility of the element present on the page and then Click on the element.
	 *
	 * @param element    element on page.
	 * @param secs       time in seconds.
	 */
	public static void click(By element , int secs){
		try
		{
			wait = new WebDriverWait(instance.getDriver(), secs);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			instance.getDriver().findElement(element).click();
			extentReportLogger().log(Status.PASS, "\"" +" Clicked " + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Clickable Element not found: " + "\"" + elementPath + "\"");
			log.error("Clickable Element not found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
	}

	/**
	 * This method click on the link present on web page.
	 *
	 * @param link 		link element on web page.
	 * @param linkText 	text of the link.
	 */
	public static void clickLinkText(By link, String linkText) {
		try{
			wait = new WebDriverWait(instance.getDriver(), 20);
			wait.until(ExpectedConditions.elementToBeClickable(link));
			Actions a=new Actions(instance.getDriver());
			a.moveToElement(instance.getDriver().findElement(link)).build().perform();
			instance.getDriver().findElement(By.linkText(linkText)).click();
			extentReportLogger().log(Status.PASS, "\"" + " Clicked " + "\"" + elementPath + "\"");
			log.info("\"" + " Clicked " + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Linktext not found: " + "\"" + elementPath + "\"");
			log.error("No LinkText Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
	}

	/**
	 * This method scroll the web page and click on web element.
	 *
	 * @param element	element on user wants to click.
	 */
	public static void scrollAndClick(By element) {
		try{
			Actions a=new Actions(instance.getDriver());
			a.moveToElement(instance.getDriver().findElement(element)).click().build().perform();
			extentReportLogger().log(Status.PASS, "\"" + "Scrolled & Clicked " + "\"" + elementPath + "\"");
			log.info("\"" + "Scrolled & Clicked " + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
	}

	/**
	 *  This method press the ENTER key of keyboard.
	 *
	 * @param textBox	text box element.
	 */
	public static void keyPressEnter(By textBox) {
		try {
			instance.getDriver().findElement(textBox).sendKeys(Keys.ENTER);
			extentReportLogger().log(Status.PASS, "Press Enter key action called for " + "\"" + elementPath + "\"");
			log.info("Press Enter key action called for " + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element for Enter key action not found: " + "\"" + elementPath + "\"");
			log.error("Element for Enter key action not found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
	}

	/**
	 *  Press keyboard Keys.
	 *
	 * @param textBox	text box element.
	 * @param keyType Key which need to press
	 */

	public static void keyPress(By textBox, String keyType) {
		keyType.toUpperCase();
		switch(keyType) {
		case "TAB" :
			instance.getDriver().findElement(textBox).sendKeys(Keys.TAB);
		case "ENTER" :
			instance.getDriver().findElement(textBox).sendKeys(Keys.ENTER);
		}
	}

	/**
	 * This method select the value from drop down list.
	 *
	 * @param dropdown 	drop down web list.
	 * @param value 	value user wants to select from the dropdown list.
	 */
	public static void selectDropdownValue(By dropdown, String value) {
		try{
			Select drpdown = new Select(instance.getDriver().findElement(dropdown));
			drpdown.selectByVisibleText(value);
			extentReportLogger().log(Status.PASS, "\"" + " Selected " + "\"" + value + "\"" + "from Dropdown");
			log.info("\"" + " Selected " + "\"" + value + "\"" + "from Dropdown");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Dropdown Element not found: " + "\"" + elementPath + "\"");
			log.error("No Dropdown Element Found" + e);
			throw(e);

		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
	}

	/**
	 *  This method get the title of web page.
	 *
	 * @return string,	title of web page.
	 */
	public static String getPageTitle() {
		String title = null;
		try {
			MKeywords.sleep(1000);
			 title = instance.getDriver().getTitle();
			extentReportLogger().log(Status.PASS, "Title for " + "\"" + elementPath + "\"" + " is " + title + "\"");
			log.info( "Title for " + "\"" + elementPath + "\"" + " is " + title + "\"");			
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Title not found: " + "\"" + elementPath + "\"");
			log.error("Title not found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
		return title;
	}
	
	/**
	 * This method verify the presence of specified text on the page.
	 *
	 * @param text		text value user want to verify on web page.
	 * @return true,	if text is present on the web page.
	 */
	public static boolean verifyAnyTextOnPage(String text) {
		boolean result = false;
		try {
			if (instance.getDriver().getPageSource().contains(text)) {
				extentReportLogger().log(Status.PASS, "\"" + text + "\"" + " is present. " + "\"");
				log.info("\"" + text + "\"" + " is present. " + "\"");
				result = true;
			} else {
				extentReportLogger().log(Status.INFO, "\"" + text + "\"" + " is not found. " + "\"");
				log.info("\"" + text + "\"" + " is not found. " + "\"");
				result = false;
			}
		} catch (Exception e) {
			extentReportLogger().log(Status.INFO, "\"" + text + "\"" + " is not found on Page " + "\"");
			log.info("Text is not found on Page " + e);
		}
		return result;
	}
	
	/**
	 * This method verify the visibility of element on web page.
	 *
	 * @param element the element
	 * @return true, if is element visible
	 */
	public static boolean isElementDisplayed(By element) {
		boolean result = false;
		try {
			if (instance.getDriver().findElement(element).isDisplayed()) {
				result = true;
				extentReportLogger().log(Status.PASS, "\"" + elementPath + "\"" + " is displayed on the page.");
				log.info( "\"" + elementPath + "\"" + " is displayed on the page.");
			}		
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not visible: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found" + elementPath);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.INFO, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.info("Timeout. Element not found." + elementPath);
		} catch (Exception e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found" + elementPath);
		}
		return result;	
	}
	
	/**
	 * This method delete all the files present in the folder.
	 *
	 * @param folderPath	path to the folder
	 */
	public static void deleteExistingFilesFromFolder(String folderPath) {
		try {
			File folder = new File(folderPath);
			File[] files = folder.listFiles();
			int noOfFiles = files.length;
			if (noOfFiles > 0) {
				for (int i = 0; i < noOfFiles; i++) {
					if (files[i].isFile()) {
						files[i].delete();
						log.info("Filed deleted from the folder at path -> " + folderPath);
						extentReportLogger().log(Status.INFO, "Filed deleted from the folder at path -> " + folderPath);
					} else {
						extentReportLogger().log(Status.INFO, "There are no files to delete");
						log.info("There are no files to delete");
					}
				}
			} else {
				extentReportLogger().log(Status.INFO, "There are no files to delete");
				log.info("There are no files to delete");
			}
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + folderPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
	}

	/**
	 * This method scrolls page up using page coordinates.
	 *
	 * @param scrollBarElement the scroll bar element
	 */
	public static void scrollUpUsingCoordinates(By scrollBarElement) {
		boolean result = false;
		try {
			result = SeKeywords.waitForElementVisibility(scrollBarElement, 5);
			if(result) {
			WebElement scrollbar = instance.getDriver().findElement(scrollBarElement);
			Actions dragger = new Actions(instance.getDriver());
			// drag upward
			int pixelsToDrag = -10;
			for (int i = 250; i > 10; i = i + pixelsToDrag) {
				dragger.moveToElement(scrollbar).clickAndHold().moveByOffset(0, pixelsToDrag).release().perform();
			}
			extentReportLogger().log(Status.PASS, "\"" + "Page scrolled up. " + "\"");
			log.info( "\"" + "Page scrolled up. " + "\"");
			}else {
				extentReportLogger().log(Status.INFO, "\"" + "Scrollbar not present. " + "\"");
				log.info("\"" + "Scrollbar not present. " + "\"");
				result = false;
			}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.INFO, "\"" + "ScrollBar Element not found: " + "\"" + elementPath + "\"");
			log.info("ScrollBar Element Found" + e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.INFO, "\"" + "TimeOut.  " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.info("Timeout. ScrollBar Element not found." + e);
		} catch (Exception e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found" + e);
		}
	}
	
	/**
	 * This method scrolls page down using page coordinates.
	 *
	 * @param scrollBarElement the scroll bar element
	 * @return true, if successful
	 */
	public static boolean scrollDownUsingCoordinates(By scrollBarElement) {
		boolean result = false;
		try {
			result = SeKeywords.waitForElementVisibility(scrollBarElement, 5);
			if(result) {
				WebElement scrollbar = instance.getDriver().findElement(scrollBarElement);
				Actions dragger = new Actions(instance.getDriver());
				// drag upward
				int pixelsToDrag = 10;
				for (int i = 10; i < 300; i = i + pixelsToDrag) {
					dragger.moveToElement(scrollbar).clickAndHold().moveByOffset(0, pixelsToDrag).release().perform();
				}
				extentReportLogger().log(Status.PASS, "\"" + "Page scrolled down. " + "\"");
				log.info("\"" + "Page scrolled down. " + "\"");
			}else {
				extentReportLogger().log(Status.INFO, "\"" + "Scrollbar not present. " + "\"");
				log.info("\"" + "Scrollbar not present. " + "\"");
				result = false;
			}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.INFO, "\"" + "ScrollBar Element not found: " + "\"" + elementPath + "\"");
			log.info("ScrollBar Element Found" + e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.INFO, "\"" + "TimeOut.  " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.info("Timeout. ScrollBar Element not found." + e);
		} catch (Exception e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found" + e);
		}
		return result;
	}

	/**
	 * Key press tab.
	 *
	 * @param textBox       the text box
	 */
	public static void keyPressTab(By textBox) {
		try {
			instance.getDriver().findElement(textBox).sendKeys(Keys.TAB);
			extentReportLogger().log(Status.PASS, "\"" + "Press Tab key action called for " + "\"" + elementPath + "\"");
			log.info("\"" + "Press Tab key action called for " + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
	}
	
	/**
	 * Verify if file is present or not.
	 * 
	 * @param fileName	filename.
	 * @return true,	if file is present.
	 */
	public static boolean isFilePresent(String fileName) {
		boolean result = false;
		try {
			File file = new File(fileName);
			if (file.exists()) {
				extentReportLogger().log(Status.PASS, "\"" + "File " + "\"" + fileName + "\"" + " found " + "\"");
				log.info("\"" + "File " + "\"" + fileName + "\"" + " found " + "\"");
				result = true;
			} else {
				extentReportLogger().log(Status.FAIL, "\"" + "File " + "\"" + fileName + "\"" + " not found " + "\"");
				log.error("\"" + "File " + "\"" + fileName + "\"" + " not found " + "\"");
				log.error("\"" + "File " + "\"" + fileName + "\"" + " not found " + "\"");
			}
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "File not found: " + "\"" + fileName + "\"");
			log.error("File not found:" + e);
			throw(e);
		}
		return result;
	}
	
	/**
	 * This method waits for the visibility of web page Title.
	 *
	 * @param pageTitle pageTitle on web page.
	 * @param secs 		number of second to wait.
	 */
	public static void waitForPageTitle(String pageTitle , int secs){
		try
		{
			wait = new WebDriverWait(instance.getDriver(), secs);
			wait.until(ExpectedConditions.titleContains(pageTitle));
			extentReportLogger().log(Status.PASS, "Page title " +pageTitle + " is verified." + "\"");
			log.info("Page title " +pageTitle + " is verified." + "\"");
		}catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "page title " +pageTitle + " is not verified." + "\"");
			log.error("Timeout. page title " +pageTitle + " is not verified." + e);
			throw(e);
		}catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "page title " +pageTitle + " is not verified." + "\"");
			log.error("Page title " +pageTitle + " is not verified." + e);
			throw(e);
		}
	}
	
	/**
	 * This method checks whether checkbox is selected or not.
	 *
	 * @param checkBox the check box
	 * @return true,		If selected
	 */
	public static boolean isCheckBoxSelected(By checkBox) {
		boolean isCheckBoxSelected = false;
		try {
			isCheckBoxSelected = instance.getDriver().findElement(checkBox).isSelected();
			extentReportLogger().log(Status.PASS, "\"" + "Checkbox found:  " + "\"" + elementPath + "\"");
			log.info("\"" + "Checkbox found:  " + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.INFO, "\"" + "Checkbox not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found" + e);	
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.INFO,
					"\"" + "TimeOut. Checkbox not found " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.info("TimeOut. Checkbox not found." + e);
		} catch (Exception e) {
			extentReportLogger().log(Status.INFO, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.info("No Such Element Found" + e);
		}
		return isCheckBoxSelected;
	}
	
	/**
	 * Fetch any date, past or future from specified date.
	 *
	 * @param inputDate 						date user want to input
	 * @param dateFormat    					format of date.
	 * @param dateType 						type of date : Calendar Day, Month or Year.
	 * @param numberOfDateTypeToSubtractAdd 	number of Date type (Day, Month or Year) user wants to subtract or add from specified date.
	 * @return String							Result date
	 * @throws Exception the exception
	 */
     public static String getAnyDateFromSpecifiedDate(String inputDate, String dateFormat, int dateType, int numberOfDateTypeToSubtractAdd) throws Exception {
           String newDate = null;           
           try {
                  SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                  Date myDate = sdf.parse(inputDate);
                  Calendar cal = Calendar.getInstance();
                  cal.setTime(myDate);
                  cal.add(dateType, numberOfDateTypeToSubtractAdd);
                  newDate = sdf.format(cal.getTime());
                  extentReportLogger().log(Status.PASS, "\"" + "New date is: " + newDate + "\"");
                  log.info("\"" + "New date is: " + newDate + "\"");
           } catch (Exception e) {
                  extentReportLogger().log(Status.FAIL, "\"" + "Error occurred while getting new date. " + "\"" + e);
                  log.error("\"" + "Error occurred while getting new date." + "\"" + e);
                  throw(e);
           }
           return newDate;
     }

     /**
      * This Method returns the current page URL.
      *
      * @return current URL
      */
 	public static String getCurrentURL() {
 		String url = null;
 		try {
 			url = instance.getDriver().getCurrentUrl();
 			extentReportLogger().log(Status.PASS, "\"" + "Current URL is:  " + "\"" + url + "\"");
 			log.info( "\"" + "Current URL is:  " + "\"" + url + "\"");
 		} catch (NoSuchElementException e) {
 			extentReportLogger().log(Status.FAIL, "\"" + "URL not found. " + "\"");
 			log.error("URL not found." + e);
 			throw(e);
 		} catch (TimeoutException e) {
 			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. URL not found. " + "\"");
 			log.error("Timeout. Element not found." + e);
 			throw(e);
 		} catch (Exception e) {
 			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
 			log.error("No Such Element Found" + e);
 			throw(e);
 		}
 		return url;
 	}

	/**
	 * to open a URL.
	 *
	 * @param string the string
	 */
	public static void goToURL(String string) {

		instance.getDriver().get(string);
	}
	
	/**
	 * Register auto IT.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void registerAutoIT() throws IOException {
		try {
			File autoitXPath = new File("lib");
			// '/s' is used for Silent: display no message boxes
			String command =  "regsvr32 /s "  +autoitXPath.getAbsolutePath()+"\\AutoItX3_x64.dll";
			Runtime.getRuntime().exec(command);
			extentReportLogger().log(Status.INFO, "\"" + "'AutoItX3_x64.dll' is registered successfully." + "\"");
			log.info("\"" + "'AutoItX3_x64.dll' is registered successfully." + "\"");

			String jacobDllVersionToUse;
			if (System.getProperty("sun.arch.data.model").contains("32")) {
				jacobDllVersionToUse = "jacob-1.18-x86.dll";
				extentReportLogger().log(Status.INFO, "\"" + "Supports only 64-bit windows" + "\"");
				log.info("\"" + "Supports only 64-bit windows." + "\"");
			} else {
				jacobDllVersionToUse = "jacob-1.18-x64.dll";
			}
			File file = new File("lib", jacobDllVersionToUse);
			System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());
		}catch(IOException e) {
			extentReportLogger().log(Status.ERROR, "\"" + "Check if 'AutoItX3_x64.dll' is registered or not." + "\"");
			log.error("Check if 'AutoItX3_x64.dll' is registered or not." + e);
			throw (e);
		}catch(Exception e) {
			extentReportLogger().log(Status.ERROR, "\"" + "Check if 'AutoItX3_x64.dll' is registered or not." + "\"");
			log.error("Check if 'AutoItX3_x64.dll' is registered or not." + e);
			throw (e);
		}
	}
	
	/**
	 * Fetch any date, past or future in specified date format.
	 *
	 * @param dateFormat    					format of date.
	 * @param dateType 							type of date : Calendar Day, Month or Year.
	 * @param numberOfDateTypeToSubtractAdd 	number of Date type (Day, Month or Year) user wants to subtract or add from specified date.
	 * @return String							Result date
	 */
	public static String getDateInSpecifiedFormat(String dateFormat, int dateType, int numberOfDateTypeToSubtractAdd) {
		String newDate=null;
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(dateType, numberOfDateTypeToSubtractAdd);
			newDate = new SimpleDateFormat(dateFormat).format(calendar.getTime());
			extentReportLogger().log(Status.INFO, "\"" + "New Date: " + newDate + "\"");
			log.info("\"" + "New Date: " + newDate + "\"");
		} catch (Exception e) {
            extentReportLogger().log(Status.FAIL, "\"" + "Error occurred while getting new date. " + "\"" + e);
            log.error("\"" + "Error occurred while getting new date." + "\"" + e);
            throw(e);
     }
		return newDate;
	}
	
	/**
	 * This method get the first word of specified string separated with spaces.
	 *
	 * @return String, 	value of string after split.
	 */
	public static String getFirstWordOfString(String savedEventText) {
		String text = null;
		text = savedEventText.split(" ")[0].replace("\"", "");	
		return text;	
	}

	/**
	 * This method splits the string based on delimiter and return the string on specified index of array.
	 *
	 * @param textToSplit 		 text to split
	 * @param delimiter			 delimiter by which user wants to split the string
	 * @param index				 index of array after string splits
	 * @return String			 string after split.
	 */
	public static String splitString(String textToSplit, String delimiter, int index) {
		String[] textArray = textToSplit.split(delimiter);
		String text = textArray[index];
		log.info("Text after split: "+text);
		return text;
	}

	/** This method converts input time into specified time format.
	 *
	 * @param time			time user want to format
	 * @param timeFormat	format on which user wants to convert the input time.
	 * @return string	 	formatted time.
	 */
	public static String getTimeInSpecifiedFormat(String time, String timeFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		Date dateObj = null;
		try {
			dateObj = sdf.parse(time);
		} catch (ParseException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Error occurred while parsing the date. " + "\"" + e);
			log.error("\"" + "Error occurred while parsing the date." + "\"" + e);
		}
		time = sdf.format(dateObj);
		return time;
	}
}
