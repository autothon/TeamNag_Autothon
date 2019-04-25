package com.autothon.core;

/*package com.lutron.saltmarsh.core;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.support.ui.*;

import com.lutron.saltmarsh.listeners.WebDriverEventListners;

import java.net.URL;


public class Mobile extends TestBase {

	public static String URL;
	public static String macip;
	private static Logger log = Logger.getLogger(Browser.class);
	public static DesiredCapabilities cap ;
	public static AppiumServiceBuilder serviceBuilder;
	public static  AppiumDriverLocalService appiumService;

	public Mobile(){
		PropertyConfigurator.configure("log4j.properties");
		log.info(" : Browser Constructor Called");
	}

	public static void openMobileBrowserOrApp() {
		log.info(" : OpenBrowserApp Method Called");
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
				adriver=new AndroidDriver<WebElement>(Appium.appiumService, cap);
				
				eDriver = new EventFiringWebDriver(adriver);
				//WebDriverEventListners handler = new WebDriverEventListners();
				eDriver.register(handler);
				eDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				adriver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.TimeoutValueInSeconds), TimeUnit.SECONDS);
				
				break;
			}
			case "AndroidWebApp":
			{

				// Name of mobile web browser to automate. Safari for iOS and Chrome
				// or Browser for Android
				cap.setCapability("browsername", "chrome");
				//cap.setCapability("browsername", Config.MobileBrowserType.toLowerCase());

				// The kind of mobile device or emulator to use - iPad Simulator, iPhone
				// Retina 4-inch, Android Emulator, Galaxy S4 etc
				cap.setCapability("deviceName", Config.androidDeviceName);

				// Which mobile OS platform to use - iOS, Android, or FirefoxOS
				cap.setCapability("platformName", "Android");

				// Java package of the Android app you want to run- Ex:
				// com.example.android.myApp
				cap.setCapability("appPackage", "com.android.chrome");

				// Activity name for the Android activity you want to launch from your
				// package
				cap.setCapability("appActivity", "com.google.android.apps.chrome.Main");
				adriver=new AndroidDriver<WebElement>(Appium.appiumService, cap);

				eDriver = new EventFiringWebDriver(adriver);
				
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
}



*/