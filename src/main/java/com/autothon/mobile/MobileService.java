package com.autothon.mobile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class MobileService {

	public static String URL;
	private static Logger log = LoggerFactory.getLogger(MobileService.class);
	public static AppiumServiceBuilder serviceBuilder;
	public static AppiumDriverLocalService appiumService;

	public void startService() {
		log.info(" : Start Appium Method Called");
		// Build the Appium service

		serviceBuilder = new AppiumServiceBuilder();
		serviceBuilder.withIPAddress(ClientsConfig.getInstance().getIp());
		serviceBuilder.usingPort(ClientsConfig.getInstance().getPort());

		appiumService = AppiumDriverLocalService.buildService(serviceBuilder);

	}

	@SuppressWarnings("unused")
	private static void startAppiumServer() {
		System.out.println("Starting Appium Server......");
		if (appiumService.isRunning() == false) {
			appiumService.start();
			System.out.println("Appium Server started......");
		} else {
			System.out.println("Appium Server is already Started......");
		}
	}

	public static void stopAppiumServer() {
		System.out.println("Stopping Appium Server......");
		if (appiumService.isRunning() == true) {
			appiumService.stop();
			System.out.println("Appium Server Stopped......");
		} else {
			System.out.println("Appium Serveris already Stopped......");
		}
	}

	@SuppressWarnings("unused")
	private static boolean AppiumServerStatus() {
		return appiumService.isRunning();
	}

}
