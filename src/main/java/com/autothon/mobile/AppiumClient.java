package com.autothon.mobile;

import java.io.File;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumClient {

	public static String URL;
	private static Logger log = LoggerFactory.getLogger(AppiumClient.class);
	public static DesiredCapabilities cap;
	public static AppiumServiceBuilder serviceBuilder;

	public DriverAndroid createAndroidDriver() {
		new MobileService().startService();
		log.info(" : createConcrete Method Called");
		cap = new DesiredCapabilities();

		if (ClientsConfig.getInstance().isAndroidWebApp()) {
			cap.setCapability("browsername", ClientsConfig.getInstance().isAndroidWebApp());
		}
		if (!ClientsConfig.getInstance().getApkPath().isEmpty()) {
			File appPathValueonLocalsystem = new File(ClientsConfig.getInstance().getApkPath());
			cap.setCapability("app", appPathValueonLocalsystem.getAbsolutePath());
		}
		cap.setCapability("deviceName", ClientsConfig.getInstance().getdeviceName());
		cap.setCapability("platformName", ClientsConfig.getInstance().getPlatformName());
		cap.setCapability("platformVersion", ClientsConfig.getInstance().getPlatformVersion());
		cap.setCapability("appActivity", ClientsConfig.getInstance().getAppActivity());
		cap.setCapability("appPackage", ClientsConfig.getInstance().getAppPackage());
		cap.setCapability("noReset ", ClientsConfig.getInstance().isNoResetApp());
		cap.setCapability("newCommandTimeout", 60 * 5);
		AndroidDriver<MobileElement> androidDriver = new AndroidDriver<MobileElement>(MobileService.appiumService, cap);

		return new DriverAndroid(androidDriver);
	}

	public DriverIos createIOSDriver() {
		IOSDriver<MobileElement> iosDriver;
		try {
			// Initialize the capabilities object
			// DesiredCapabilities capabilities = new DesiredCapabilities();
			// Set the appium version in capabilities
			cap.setCapability("platformName", "iOS");
			// Set the appium version in capabilities
			cap.setCapability("platformVersion", "11.0.1");
			// Set the appium version in capabilities
			cap.setCapability("deviceName", " iPad");
			cap.setCapability("platform", "Mac");
			// Set the browser name
			// cap.setCapability("app","settings");
			cap.setCapability(CapabilityType.BROWSER_NAME, "safari");

			cap.setCapability("udid", "AF7B1F6AE74AD6B04965EA819F4B16FE8576C1C1");

			iosDriver = new IOSDriver<MobileElement>(new java.net.URL("http://10.175.1.20:4723/wd/hub"), cap);

		} catch (Exception ex) {
			System.out.println("set up exception is ....");
			ex.printStackTrace();
			return null;

		}

		return new DriverIos(iosDriver);
	}

}
