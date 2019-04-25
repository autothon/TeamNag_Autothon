package com.autothon.mobile;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class DriverIos extends AbstractMobileDriver {

	IOSDriver<MobileElement> driver;

	public DriverIos(IOSDriver<MobileElement> iosDriver) {
		super(iosDriver);
		driver = iosDriver;
	}

	/**
	 * returns the driver name
	 */

	public String driverName() {

		return this.getClass().getSimpleName();
	}

	/**
	 * quits the driver
	 */

	public void cleanUp() {
		quit();

	}

}
