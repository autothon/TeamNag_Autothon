package com.autothon.testing.hooks;

import com.autothon.testing.appiumclient.AppiumClient;
import com.autothon.testing.selenumclient.SeleniumClient;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	SeleniumClient client;
	AppiumClient appium;

	@Before("@Chrome")
	public void InitializeChromeDriver() {
		client = new SeleniumClient();
		client.createChromeDriver();
	}

	@Before("@Firefox")
	public void InitializeFirefoxDriver() {
		client = new SeleniumClient();
		client.createFireFoxDriver();
	}

	@Before("@IE")
	public void InitializeIEDriver() {
		client = new SeleniumClient();
		client.createIEDriver();
	}

	@Before("@Android")
	public void InitializeAndroidDriver() {
		appium = new AppiumClient();
		appium.createAndroidDriver();
	}

	@Before("@Rest")
	public void InitializeRestDriver() {

	}

	@After("@Chrome,@Firefox,@IE")
	public void quitSeleniumDriver() {
		client.quitDriver();
	}

	@After("@Android")
	public void quitAppiumDriver() {
		appium.quitDriver();
	}

	@After("@Rest")
	public void quitRestDriver() {

	}
}
