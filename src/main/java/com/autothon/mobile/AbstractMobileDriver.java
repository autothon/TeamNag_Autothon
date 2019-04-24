package com.autothon.mobile;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autothon.core.TestBase;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

/**
 * AbstractMobileDriver is an abstract class which contains all the
 * events/functions related to appium.
 * 
 * @author nagarro
 */
public abstract class AbstractMobileDriver extends TestBase {

	private final AppiumDriver<MobileElement> driver;
	private static final Logger log = LoggerFactory.getLogger(AbstractMobileDriver.class);
	// private final TestReportLogger reportLog;

	public AbstractMobileDriver(AndroidDriver<MobileElement> driver2) {
		this.driver = driver2;
	}

	public AbstractMobileDriver(IOSDriver<MobileElement> iosDriver2) {
		this.driver = iosDriver2;

	}

	public void go(String url) {
		log.info("Navigating to URL: {}", url);
		driver.get(url);
		extentReportLogger().log(Status.PASS, "Navigating to URL: " + "\"" + url + "\"");
	}

	public void quit() {
		log.debug("Closing WebDriver");
		driver.quit();
		extentReportLogger().log(Status.PASS, "Closing web driver");
	}

	public String getDeviceTime() {
		return driver.getDeviceTime();
	}

	public void waitforAppload(int maximumTimeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, maximumTimeInSeconds);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*")));
	}

	public boolean waitForVisibility(By mobileElement, int timeout) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(mobileElement)));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isSelected(By mobileElement) {
		log.debug("Retrieving select status for element: {}", mobileElement);
		boolean selected = driver.findElement(mobileElement).isSelected();
		log.debug("Selected: {}", selected);
		extentReportLogger().log(Status.PASS,
				"Select status of element: " + "\"" + mobileElement + "\"" + " is " + "\"" + selected + "\"");
		return selected;
	}

	public String getAttribute(By mobileElement, String attributeName) {
		log.debug("Retrieving attribute {} from element: {}", attributeName, mobileElement);
		String attribute = driver.findElement(mobileElement).getAttribute(attributeName);
		log.debug("{}: {}", attributeName, attribute);
		extentReportLogger().log(Status.PASS,
				"Attribute of element: " + "\"" + mobileElement + "\"" + " is " + "\"" + attribute + "\"");
		return attribute;
	}

	public String getText(By mobileElement) {
		log.debug("Retrieving text from element: {}", mobileElement);
		String text = driver.findElement(mobileElement).getText();
		log.debug("Text: {}", text);
		extentReportLogger().log(Status.PASS,
				"Text of element: " + "\"" + mobileElement + "\"" + " is " + "\"" + text + "\"");
		return text;
	}

	public String getElementType(By mobileElement) {
		log.debug("Retrieving tag name from element: {}", mobileElement);
		String tagName = driver.findElement(mobileElement).getTagName();
		log.debug("Tag name: {}", tagName);
		extentReportLogger().log(Status.PASS,
				"Tag name of element: " + "\"" + mobileElement + "\"" + " is " + "\"" + tagName + "\"");
		return tagName;
	}

	public boolean isEnabled(By mobileElement) {
		log.debug("Retrieving enabled status from element: {}", mobileElement);
		boolean enabled = driver.findElement(mobileElement).isEnabled();
		log.debug("Enabled: {}", enabled);
		extentReportLogger().log(Status.PASS,
				"Enabled status of element: " + "\"" + mobileElement + "\"" + " is " + "\"" + enabled + "\"");
		return enabled;
	}

	public boolean isDisplayed(By mobileElement) {
		log.debug("Retrieving displayed status from element: {}", mobileElement);
		try {
			boolean displayed = driver.findElement(mobileElement).isDisplayed();
			log.debug("Displayed: {}", displayed);
			extentReportLogger().log(Status.PASS,
					"Display status of element: " + "\"" + mobileElement + "\"" + " is " + "\"" + displayed + "\"");
			return displayed;
		} catch (NoSuchElementException e) {
			log.error("Displayed: Couldn't find element");
			extentReportLogger().log(Status.ERROR, "Displayed: Couldn't find element");
			return false;
		}
	}

	public void click(By mobileElement) {
		log.debug("Clicking on element: {}", mobileElement);
		driver.findElement(mobileElement).click();
		extentReportLogger().log(Status.PASS, "clicking the element: " + "\"" + mobileElement + "\"");
	}

	public void click_hold(By mobileElement) {
		log.debug("Clicking and holding on element: {}", mobileElement);
		new TouchAction(driver).longPress(driver.findElement(mobileElement)).release().perform();
		extentReportLogger().log(Status.PASS, "clicking and holding on  the element: " + "\"" + mobileElement + "\"");
	}

	public void clear(By mobileElement) {
		log.debug("Clearing element: {}", mobileElement);
		driver.findElement(mobileElement).clear();
		extentReportLogger().log(Status.PASS, "Clearing element " + "\"" + mobileElement + "\"");
	}

	public void sendKeys(By mobileElement, String keys) {
		log.debug("Sending keys {} to element: {}", keys, mobileElement);
		driver.findElement(mobileElement).sendKeys(keys);
		extentReportLogger().log(Status.PASS, "Sending Keys " + keys + " to element: " + "\"" + mobileElement + "\"");
	}

	public File takeScreenshot(By mobileElement) {
		log.debug("Taking screenshot");
		extentReportLogger().log(Status.PASS, "Taking screenshot ");
		return driver.findElement(mobileElement).getScreenshotAs(OutputType.FILE);
	}

	public void selectDropdown(By mobileElement, String visibleText) {
		Select select = new Select(driver.findElement(mobileElement));
		select.selectByVisibleText(visibleText);
		extentReportLogger().log(Status.PASS, "Selecting dropdown for " + "\"" + visibleText + "\"");
	}

	public void hover(By mobileElement) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(mobileElement)).perform();
	}

	public void selectFromOptions(By mobileElement, String visibleText) {
		extentReportLogger().log(Status.PASS, "Selecting an Option from dropdown " + "\"" + visibleText + "\"");
		List<MobileElement> elements = driver.findElements(mobileElement);

		for (MobileElement webElement : elements) {
			if (webElement.getText().equals(visibleText)) {
				webElement.click();
				break;
			}
		}
	}

	public int size(By mobileElement) {
		List<MobileElement> elements = driver.findElements(mobileElement);
		extentReportLogger().log(Status.PASS, "Element size is " + "\"" + elements.size() + "\"");

		return elements.size();
	}
}
