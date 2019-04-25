package com.autothon.mobile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class DriverAndroid extends AbstractMobileDriver {

	private static final Logger log = LoggerFactory.getLogger(DriverAndroid.class);
	static AndroidDriver<MobileElement> driver;

	public DriverAndroid(AndroidDriver<MobileElement> androidDriver) {
		super(androidDriver);
		driver = androidDriver;

	}

	/**
	 * press the back key of mobile
	 */

	public void pressAndroidBackKey() {
		driver.pressKeyCode(4);
	}

	/**
	 * Hides keyboard of device
	 */

	public void hideKeyboard() {
		driver.hideKeyboard();
	}

	/**
	 * Closes the current app
	 */

	public void closeApp() {
		driver.closeApp();
	}

	/**
	 * Check if the keyboard is displayed. Returns:true if keyboard is displayed.
	 * False otherwise
	 * 
	 * @return
	 */

	public boolean isKeyboardShown() {
		return driver.isKeyboardShown();
	}

	// A
	/**
	 * Open the notification shade, on Android devices.
	 */

	public void openNotifications() {
		driver.openNotifications();
	}

	public void unlockDevice() {
		driver.unlockDevice();
	}

	// A

	public void presskeycode(int keycode) {
		try {
			driver.pressKeyCode(keycode);
		} catch (Exception e) {
			log.error("Error in pressing the enter key {}", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Scroll the container whose id is given upto the element whose text is
	 * provided
	 * 
	 * @param idOfElementToScroll
	 * @param text
	 */

	public void scrollByIdUptoText(String idOfElementToScroll, String text) {
		driver.findElementByAndroidUIAutomator("UiScrollable(UiSelector().resourceId(\"" + idOfElementToScroll
				+ "\")).scrollIntoView(UiSelector().textContains(\"" + text + "\"))");
	}

	/**
	 * Scroll the container whose class is given upto the element whose text is
	 * provided
	 * 
	 * @param classOfElementToScroll
	 * @param text
	 */

	public void scrollByClassNameUptoText(String classOfElementToScroll, String text) {
		driver.findElementByAndroidUIAutomator("UiScrollable(UiSelector().className(\"" + classOfElementToScroll
				+ "\")).scrollIntoView(UiSelector().textContains(\"" + text + "\"))");
	}

	/**
	 * Scroll the container whose id is given upto the element whose description is
	 * provided
	 * 
	 * @param idOfElementToScroll
	 * @param description
	 */

	public void scrollByIdUptoDescription(String idOfElementToScroll, String description) {
		driver.findElementByAndroidUIAutomator("UiScrollable(UiSelector().resourceId(\"" + idOfElementToScroll
				+ "\")).scrollIntoView(UiSelector().descriptionContains(\"" + description + "\"))");
	}

	/**
	 * Scroll the container whose classname is given upto the element whose
	 * description is provided
	 * 
	 * @param classOfElementToScroll
	 * @param description
	 */

	public void scrollByClassNameUptoDescription(String classOfElementToScroll, String description) {
		driver.findElementByAndroidUIAutomator("UiScrollable(UiSelector().className(\"" + classOfElementToScroll
				+ "\")).scrollIntoView(UiSelector().descriptionContains(\"" + description + "\"))");
	}

	/**
	 * returns driver name
	 */

	public String driverName() {
		return this.getClass().getSimpleName();
	}

	/**
	 * auits the driver
	 */

	public void cleanUp() {
		quit();

	}

}
