package com.autothon.testing.selenumclient;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumClient {
	//private static final Logger log = LoggerFactory.getLogger(SeleniumClient.class);
	public WebDriver driver;

	public WebDriver createChromeDriver() {
		ChromeOptions options = new ChromeOptions();

		// Prevent WebDriver server session timeout
		options.addArguments("--no-sandbox");
		// Start Chrome w/ disabled extensions
		options.addArguments("--disable-extensions");
		// Start Chrome in screen
		options.addArguments("--start-maximized");
		options.addArguments("--disable-notifications");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}

	@SuppressWarnings("deprecation")
	public WebDriver createFireFoxDriver() {
		FirefoxProfile profile = new FirefoxProfile();
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability(FirefoxDriver.MARIONETTE, false);

		// Start Mozilla with disable web notifications
		profile.setPreference("dom.webnotifications.enabled", false);

		// Allows the untrusted websites to run in the browser
		profile.setPreference("webdriver_assume_untrusted_issuer", true);

		// Start Mozilla assuming the Untrusted certificates accepting
		profile.setPreference("setAssumeUntrustedCertificateIssuer", true);
		profile.setPreference("security.enterprise_roots.enabled", true);

		capabilities.setCapability(FirefoxDriver.PROFILE, profile);
		driver = new FirefoxDriver(capabilities);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}

	@SuppressWarnings("deprecation")
	public WebDriver createIEDriver() {
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

		// Start IE ignoring security domains and popups on the website
		capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

		// Start IE, to run with default zoom size, and prevents it from throwing error
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver(capabilities);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}
	
	public void quitDriver() {
		if(driver != null) {
			driver.close();
			driver.quit();
		}
	}
}
