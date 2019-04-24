package com.autothon.testing.appiumclient;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autothon.testing.configuration.ClientsConfig;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumClient {

	public static String URL;
	private static Logger log = LoggerFactory.getLogger(AppiumClient.class);
	public static DesiredCapabilities cap;
	public static AppiumServiceBuilder serviceBuilder;
	public AndroidDriver<MobileElement> androidDriver;
	private static ThreadLocal<AndroidDriver<MobileElement>> android = new ThreadLocal<>();
	// private static ThreadLocal<IOSDriver<MobileElement>> iosdriver = new
	// ThreadLocal<>();

	public WebDriver createAndroidDriver() {
		new AppiumService().startService();
		log.info(" : createConcrete Method Called");
		cap = new DesiredCapabilities();

		File appPathValueonLocalsystem = new File(ClientsConfig.getInstance().getApkPath());
		if (ClientsConfig.getInstance().isAndroidWebApp()) {
			cap.setCapability("browsername", ClientsConfig.getInstance().isAndroidWebApp());
		}
		cap.setCapability("app", appPathValueonLocalsystem.getAbsolutePath());
		cap.setCapability("deviceName", ClientsConfig.getInstance().getdeviceName());
		cap.setCapability("platformName", ClientsConfig.getInstance().getPlatformName());
		cap.setCapability("platformVersion", ClientsConfig.getInstance().getPlatformVersion());
		cap.setCapability("appActivity", ClientsConfig.getInstance().getAppActivity());
		cap.setCapability("appPackage", ClientsConfig.getInstance().getAppPackage());
		cap.setCapability("noReset ", ClientsConfig.getInstance().isNoResetApp());
		cap.setCapability("newCommandTimeout", 60 * 5);
		androidDriver = new AndroidDriver<MobileElement>(AppiumService.appiumService, cap);
		android.set(androidDriver);

		return androidDriver;
	}

	public static AndroidDriver<MobileElement> getDriver() {
		return android.get();
	}

	public void quitDriver() {
		if (androidDriver != null) {
			androidDriver.close();
			androidDriver.quit();
		}
	}
}
