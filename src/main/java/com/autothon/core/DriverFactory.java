package com.autothon.core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;

import com.autothon.common.keywords.MKeywords;
import com.autothon.listeners.WebDriverEventListners;
import com.autothon.util.CommonFunctionUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class DriverFactory {

	private static DriverFactory instance = new DriverFactory();
	public static String CHROME = "chrome";
	public static String FIREFOX = "firefox";
	public static String INTERNET_EXPLORER = "ie";
	public static String SAFARI = "safari";
	public static String EDGE = "edge";
	
	public static String URL;
	private static Logger log = Logger.getLogger(TestBase.class);
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(); // thread local driver object for webdriver
	public static EventFiringWebDriver eDriver;
	public static AndroidDriver<?> adriver;
	public  static WiniumDriver wdriver = null;
	public static WiniumDriverService service = null;
	public static DesktopOptions options=null;
	public static String macip;
	public static DesiredCapabilities cap ;
	public static AppiumServiceBuilder serviceBuilder;
	public static  AppiumDriverLocalService appiumService;
	public static String  platformType;
	public static String  isHeadless;
	
	
	private DriverFactory()
	{
		//Do-nothing..Do not allow to initialize this class from outside
	}

	public static DriverFactory getInstance()
	{
		return instance;
	}

	public void setDriverNoGrid(String browser, String platform) throws FrameworkException // call this method to set the driver using browser value in case of no grid configuration and open the browser
	{	
		platformType = platform;
		log.info(" : setDriverNoGrid Method Called");
		URL = Config.ApplicationURL;
		isHeadless = Config.Headless;
		DesiredCapabilities capability = null;
		if(platform.equalsIgnoreCase("windows"))
		{
			System.setProperty("webdriver.chrome.driver", MKeywords.getAbsolutePath(Config.DriverPath) + "/chromedriver.exe");
			System.setProperty("webdriver.ie.driver",  MKeywords.getAbsolutePath(Config.DriverPath) + "/IEDriverServer.exe");
			System.setProperty("webdriver.gecko.driver", MKeywords.getAbsolutePath(Config.DriverPath) + "/geckodriver.exe");
			System.setProperty("webdriver.edge.driver", MKeywords.getAbsolutePath(Config.DriverPath) + "/MicrosoftWebDriver.exe");
		}
		
		else if(platform.equalsIgnoreCase("linux")||platform.equalsIgnoreCase("unix"))
		{
			System.setProperty("webdriver.chrome.driver", MKeywords.getAbsolutePath(Config.DriverPath) + "/chromedriver");
			System.setProperty("webdriver.ie.driver",  MKeywords.getAbsolutePath(Config.DriverPath) + "/IEDriverServer.exe");
			System.setProperty("webdriver.gecko.driver", MKeywords.getAbsolutePath(Config.DriverPath) + "/geckodriver.exe");
			System.setProperty("webdriver.edge.driver", MKeywords.getAbsolutePath(Config.DriverPath) + "/MicrosoftWebDriver.exe");
		}
		
		if(browser.equalsIgnoreCase(CHROME) && isHeadless.equalsIgnoreCase("true"))
		{
			capability = DesiredCapabilities.chrome();
			ChromeOptions option = DriverFactory.setChromeToDownloadFileAtSpecifiedPath(capability);
			//option.addArguments("--headless"); Commenting for now, as with latest update, setHeadless() is available
			option.setHeadless(true);
			driver.set(new ChromeDriver(option));
		}
		else if(browser.equalsIgnoreCase(CHROME) && isHeadless.equalsIgnoreCase("false"))
		{
			capability = DesiredCapabilities.chrome();
			ChromeOptions option = new ChromeOptions();
			option = DriverFactory.setChromeToDownloadFileAtSpecifiedPath(capability);
			driver.set(new ChromeDriver(option));
		}
		else if(browser.equalsIgnoreCase(SAFARI))
		{
			driver.set(new SafariDriver());
		}
		
		else if(browser.equalsIgnoreCase(FIREFOX))
		{
			capability = DesiredCapabilities.firefox();
			FirefoxOptions option = new FirefoxOptions(capability);
			option.setCapability("marionette", false);
			option.setHeadless(true);
			driver.set(new FirefoxDriver(option));
		}
		else if(browser.equalsIgnoreCase(INTERNET_EXPLORER))
		{
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			ieOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
			ieOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			ieOptions.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
			ieOptions.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,UnexpectedAlertBehaviour.DISMISS);
			driver.set(new InternetExplorerDriver(ieOptions));
		}
		else if(browser.equalsIgnoreCase(EDGE))
		{
			driver.set(new EdgeDriver());
		}
		else
		{
			throw new FrameworkException("Either browser is set wrong in testng xml file or set 'RunOnGrid' option to 'yes' in properties file.!!!");
		}
		WebDriver driver = DriverFactory.getInstance().getDriver();
		driver.manage().timeouts().pageLoadTimeout(60 * 5, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(URL);
		log.info(" : Application Opened : " + URL);
	}

	// call this method to set the driver using values set via testng.xml in case of grid configuration and open the browser
	public void setDriverWithGrid(String webdriverHost, int webdriverPort, String browser, String platformType) throws FrameworkException, IOException {
		log.info(" : setDriverWithGrid Method Called");
		URL = Config.ApplicationURL;

		System.setProperty("webdriver.chrome.driver", MKeywords.getAbsolutePath(Config.DriverPath) + "/chromedriver.exe");
		System.setProperty("webdriver.ie.driver",  MKeywords.getAbsolutePath(Config.DriverPath) + "/IEDriverServer.exe");
		System.setProperty("webdriver.gecko.driver", MKeywords.getAbsolutePath(Config.DriverPath) + "/geckodriver.exe");
		System.setProperty("webdriver.gecko.driver", MKeywords.getAbsolutePath(Config.DriverPath) + "/geckodriver.exe");
		System.setProperty("webdriver.edge.driver", MKeywords.getAbsolutePath(Config.DriverPath) + "/MicrosoftWebDriver.exe");
		
		try {
			org.openqa.selenium.Platform platform;
			DesiredCapabilities capability = null;
			platform = DriverFactory.setPlatform(platformType);				
			capability = DriverFactory.setBrowser(browser);	
			
			if(browser.equalsIgnoreCase(CHROME)) {
				DriverFactory.setChromeToDownloadFileAtSpecifiedPath(capability);
			}else if(browser.equalsIgnoreCase(FIREFOX)){
                log.info("Setting up firefox browser");
			}else if(browser.equalsIgnoreCase(INTERNET_EXPLORER)){
                log.info("Setting up IE browser");
			}else if(browser.equalsIgnoreCase(SAFARI)){
                capability.setCapability("version", "10");
                log.info("Setting up Safari browser");
			}else if(browser.equalsIgnoreCase(EDGE)){
				 log.info("Setting up Edge browser");
			}else{
                throw new FrameworkException("Browser is set wrong from xml file !!!");
			}
			
			if(null != capability){
				capability.setPlatform(platform);
			}else{
				throw new FrameworkException("Browser is set wrong from xml file !!!");
			}
			driver.set(new RemoteWebDriver(new URL("http://"+ webdriverHost + ":" + webdriverPort + "/wd/hub"),capability));
			WebDriver driver = DriverFactory.getInstance().getDriver();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(URL);
			log.info(" : Application Opened");
		}catch (UnreachableBrowserException ex) {
			throw new FrameworkException("Browser not reachable, either HUB/NODE not started or webdriver port is wrong.");				
		}catch(MalformedURLException ex){
			throw new FrameworkException("Issue in setting newly created driver - Malformed URL");
		}catch(FrameworkException ex){
			throw ex;
		}catch(Exception ex){
			log.error(ex);
		}
	}
	
	/**
	 * Sets the desired capabilities of chrome to download any file at specified path.
	 */
	private static ChromeOptions setChromeToDownloadFileAtSpecifiedPath(DesiredCapabilities cap) {
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		HashMap<String, String> chromeDownloadPrefs = new HashMap<>();
		
		
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("cmd", "Page.setDownloadBehavior");
		chromeDownloadPrefs.put("behavior", "allow");
		chromePrefs.put("download.default_directory", Config.ExportFilePath);
		
		chromePrefs.put("params", chromeDownloadPrefs);
		
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		options.setExperimentalOption("prefs", chromePrefs);
		//options.setExperimentalOption("prefs", chromeDownloadPrefs);
		options.addArguments("--test-type");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-extensions"); //to disable browser extension pop-up
		options.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.setCapability(ChromeOptions.CAPABILITY, options);
		options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		options.addArguments("--proxy-server='direct://'");
		options.addArguments("--proxy-bypass-list=*");
		return options;
	}

	/**
	 * Sets the desired capabilities of specified browser.
	 */
	public static DesiredCapabilities setBrowser(String browser){

		if(browser.equalsIgnoreCase("firefox")) {
			return DesiredCapabilities.firefox();
		}else if(browser.equalsIgnoreCase("chrome")) {
			return DesiredCapabilities.chrome();
		}else if(browser.equalsIgnoreCase("internet explorer")) {
			return DesiredCapabilities.internetExplorer();					
		}else if(browser.equalsIgnoreCase("android")) {
			System.out.println("[Debug] Android.....");
			return DesiredCapabilities.android();
		}else if(browser.equalsIgnoreCase("ipad")) {
			return DesiredCapabilities.ipad();
		}else if(browser.equalsIgnoreCase("iphone")) {
			return DesiredCapabilities.iphone();
		}else if(browser.equalsIgnoreCase("safari")) {
			return DesiredCapabilities.safari();
		}else if(browser.equalsIgnoreCase("htmlunit")) {
			return DesiredCapabilities.htmlUnit();
		}
		return null;
	}

	/**
	 * Sets the platform on which user wants to run the test cases.
	 */
	public static Platform setPlatform(String platform){

		if(platform.equalsIgnoreCase("LINUX")){
			return Platform.LINUX;
		}else if(platform.equalsIgnoreCase("WINDOWS")) {
			return Platform.WINDOWS;
		}else if(platform.equalsIgnoreCase("MAC")){
			return Platform.MAC;
		}else if(platform.equalsIgnoreCase("ANDROID")){
			return Platform.ANDROID;
		}else if(platform.equalsIgnoreCase("WIN8")){
			return Platform.WIN8;
		}else if(platform.equalsIgnoreCase("XP")){
			return Platform.XP;
		}else if(platform.equalsIgnoreCase("VISTA")){
			return Platform.VISTA;
		}else if(platform.equalsIgnoreCase("UNIX")){
			return Platform.UNIX;
		}
		return Platform.ANY;
	}

	public WebDriver getDriver() // call this method to get the driver object and launch the browser
	{
		if(platformType.equalsIgnoreCase("desktop"))
			return wdriver;
		else if (platformType.equalsIgnoreCase("mobile"))
			return adriver;
		else
			return driver.get();
		
	}

	public void removeDriver() // Quits the driver and closes the browser
	{
		driver.get().quit();
		driver.remove();
	}

	public void setDriverForMobile(AppiumDriverLocalService abc, String platform) {
		platformType = platform;
		log.info(" setDriverForMobile:  Method Called");
		WebDriverEventListners handler = new WebDriverEventListners();
		if(driver==null || adriver == null)
		{	
			cap = new DesiredCapabilities();
			URL = Config.ApplicationURL;
			//URL = "http://www.seleniumeasy.com";
			switch(Config.ApplicationType)
			{
			case "AndroidNativeApp":
			{
				File appPathValueonLocalsystem=new File(Config.APKPath);
				cap.setCapability("deviceName", Config.androidDeviceName);
				if(Config.APKfreshInstall.equalsIgnoreCase("true"))
					cap.setCapability(MobileCapabilityType.FULL_RESET, true);
				else
					cap.setCapability(MobileCapabilityType.FULL_RESET, false);
				cap.setCapability("platform", "LINUX");
				cap.setCapability("platformVersion", Config.androidPlatformVersion);
				cap.setCapability("platformName", "Android"); 
				cap.setCapability("newCommandTimeout", 60 * 5);
				
				//Added by Raghav Bajoria for handling location pop-up after app launch for first time
				cap.setCapability("autoGrantPermissions", true);
				
				//cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
				if(Config.APKfreshInstall.equalsIgnoreCase("false"))
				{
					cap.setCapability("app", appPathValueonLocalsystem.getAbsolutePath());		
					//openAppFlag=true;
				}
				else
				{
					cap.setCapability("appPackage",Config.appPackage );
					if(Config.appActivity.trim().length() > 0)
					{
						cap.setCapability("appActivity", Config.appActivity);
					}

				}



				//cap.setCapability("appium-version", "1.4");
				adriver=new AndroidDriver<WebElement>(abc, cap);

				/*eDriver = new EventFiringWebDriver(adriver);
				driver.set(eDriver);
				//WebDriverEventListners handler = new WebDriverEventListners();
				eDriver.register(handler);
				eDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
				//adriver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.TimeoutValueInSeconds), TimeUnit.SECONDS);
				
				break;
			}
			case "AndroidWebApp":
			{

				// Name of mobile web browser to automate. Safari for iOS and Chrome
				// or Browser for Android
				cap.setCapability("browsername", "Browser");
				//cap.setCapability("browsername", Config.MobileBrowserType.toLowerCase());

				// The kind of mobile device or emulator to use - iPad Simulator, iPhone
				// Retina 4-inch, Android Emulator, Galaxy S4 etc
				cap.setCapability("deviceName", Config.androidDeviceName);

				// Which mobile OS platform to use - iOS, Android, or FirefoxOS
				cap.setCapability("platformName", "Android");

				// Java package of the Android app you want to run- Ex:
				// com.example.android.myApp
				//cap.setCapability("appPackage", "com.android.chrome");
				
				cap.setCapability("appPackage", "com.android.chrome");
				//cap.setCapability("skip_first_run_ui", "true");

				// Activity name for the Android activity you want to launch from your
				// package
				//cap.setCapability("appActivity", "com.google.android.apps.chrome.Main");
				cap.setCapability("appActivity", "com.google.android.apps.chrome.Main");
				
				adriver=new AndroidDriver<WebElement>(abc, cap);

				eDriver = new EventFiringWebDriver(adriver);
				driver.set(eDriver);
				eDriver.register(handler);
				eDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				//eDriver.manage().window().maximize();
				eDriver.get(Config.ApplicationURL);
				


				//adriver.get(Config.ApplicationURL);
				//adriver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.TimeoutValueInSeconds), TimeUnit.SECONDS);
				break;
			}
			case "iOSWebApp":
			{

				try
				{
					//Initialize the capabilities object 
					//DesiredCapabilities capabilities = new DesiredCapabilities();	
					//Set the appium version in capabilities
					cap.setCapability("platformName", "iOS");
					//Set the appium version in capabilities
					cap.setCapability("platformVersion", Config.iOSPlatformVersion);
					//Set the appium version in capabilities
					cap.setCapability("deviceName", Config.iOSDeviceName);	
					cap.setCapability("platform", "Mac");
					//Set the browser name
					//cap.setCapability("app","settings");	
					cap.setCapability(CapabilityType.BROWSER_NAME, Config.iOSBrowserName);	
					//cap.setCapability(MobileCapabilityType.UDID, "AF7B1F6AE74AD6B04965EA819F4B16FE8576C1C1");
					//cap.setCapability("udid", "AF7B1F6AE74AD6B04965EA819F4B16FE8576C1C1");
					//cap.setCapability("udid", "386E4E6C3D854F84844541770F5CD9EA");
					//capabilities.setCapability("app", "/Users/nagarro/Desktop/P51.ipa");		
					//Launch the appium driver with the above required capabilities---
					//driver = new AppiumDriver(new URL("http://10.175.1.20:4723/wd/hub"), cap);
					//IOSDriver driver = new IOSDriver(new URL("http://10.175.0.217:4723/wd/hub"), cap);
					macip = "http://" + Config.macIP + ":4723/wd/hub";
					//AppiumDriver<IOSElement> driver = new IOSDriver<IOSElement>(new URL("http://10.175.0.165:4723/wd/hub"), cap);
					AppiumDriver<IOSElement> driver = new IOSDriver<IOSElement>(new URL(macip), cap);
					//driver.manage().timeouts().implicitlyWait(380, TimeUnit.SECONDS);
					//driver.get("http://www.google.com");

					eDriver = new EventFiringWebDriver(driver);
					eDriver.register(handler);
					eDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					//eDriver.manage().window().maximize();
					eDriver.get(Config.ApplicationURL);

					//	driver.get(Config.ApplicationURL);
				}
				catch (Exception ex)
				{
					System.out.println("set up exception is ...." ); ex.printStackTrace();

				}
				catch (Error e)
				{
					System.out.println("set up error is ...." ); e.printStackTrace();

				}		
				break;
			}
			case "iOSNativeApp":
			{

				try
				{
					//Initialize the capabilities object 
					//DesiredCapabilities capabilities = new DesiredCapabilities();	
					//Set the appium version in capabilities
					cap.setCapability("platformName", "iOS");
					//Set the appium version in capabilities
					cap.setCapability("platformVersion", Config.iOSPlatformVersion);
					//Set the appium version in capabilities
					cap.setCapability("deviceName", Config.iOSDeviceName);	
					cap.setCapability("platform", "Mac");
					//Set the browser name
					//cap.setCapability("app","settings");	
					cap.setCapability(CapabilityType.BROWSER_NAME, Config.iOSBrowserName);	
					//cap.setCapability(MobileCapabilityType.UDID, "AF7B1F6AE74AD6B04965EA819F4B16FE8576C1C1");
					//cap.setCapability("udid", "AF7B1F6AE74AD6B04965EA819F4B16FE8576C1C1");
					//cap.setCapability("udid", "386E4E6C3D854F84844541770F5CD9EA");
					cap.setCapability("app", "/Users/nagarro/Desktop/P51.ipa");		
					//Launch the appium driver with the above required capabilities---
					//driver = new AppiumDriver(new URL("http://10.175.1.20:4723/wd/hub"), cap);
					//IOSDriver driver = new IOSDriver(new URL("http://10.175.0.217:4723/wd/hub"), cap);
					macip = "http://" + Config.macIP + ":4723/wd/hub";
					//AppiumDriver<IOSElement> driver = new IOSDriver<IOSElement>(new URL("http://10.175.0.165:4723/wd/hub"), cap);
					AppiumDriver<IOSElement> driver = new IOSDriver<IOSElement>(new URL(macip), cap);
					//driver.manage().timeouts().implicitlyWait(380, TimeUnit.SECONDS);
					//driver.get("http://www.google.com");

					eDriver = new EventFiringWebDriver(driver);

					eDriver.register(handler);
					eDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					//eDriver.manage().window().maximize();
					eDriver.get(Config.ApplicationURL);

					//	driver.get(Config.ApplicationURL);
				}
				catch (Exception ex)
				{
					System.out.println("set up exception is ...." ); ex.printStackTrace();

				}
				catch (Error e)
				{
					System.out.println("set up error is ...." ); e.printStackTrace();

				}		
				break;
			}

			}

		}

	}

	public void setDriverForDesktop(String webdriverHost, int webdriverPort, String browser, String platform) throws Exception {
		
		platformType = platform;
		options = new DesktopOptions();
		/*options.setApplicationPath(Config.DesktopApp);
		File driverPath = new File((Config.DriverPath) + "\\Winium.Desktop.Driver.exe");*/
		options.setApplicationPath(Config.DesktopApp);
		File driverPath = new File("D:\\SVN_Repositories\\LutronAutomation\\Master_JavaFramework\\HubNodeConfig\\drivers\\Winium.Desktop.Driver.exe");
		CommonFunctionUtil.killAProcess("22672");
		service = new WiniumDriverService.Builder().usingDriverExecutable(driverPath).usingPort(9999).withVerbose(true).withSilent(false).buildDesktopService();
		service.start();
		wdriver = new WiniumDriver(service, options);
		//WebDriverEventListners handler = new WebDriverEventListners();
	
	}
	
	public void quit()
	{
		service.stop();
		
	}
	
}
