package com.autothon.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

import com.autothon.common.keywords.MKeywords;

/**
 * This class reads the "app.properties" file.
 */
public class Config {
	
	public static Logger log = Logger.getLogger(Config.class); 
	
	public static String ExtentReportsPath;
	public static String TestReportFolder;
	public static String ReportTitle;
	public static String ReportName;
	public static String ScreenShotsPath;
	public static String ChromeDriverPath;
	public static String FirefoxDriverPath;
	public static String IEDriverPath;
	public static String ApplicationURL;
	public static String Browser;
	public static String Headless;
	public static String Environment;
	public static String DBConnectionString;	
	public static String AppConfig;
	public static String MaxRetryCountOnTestFailure;
	public static String dataInputFile;
	public static String locatorsFile;
	public static String TestVideoPath;
	public static String SetVideoRecording;
	public static String MaxSizeVideoFilesGB;
	public static FileInputStream ofileInputStream;
	public static String ZipPath;
	public static String mailFrom;
	public static String subject;
	public static String mailTo;
	public static String mailUserName;
	public static String mailPassword;
	public static String mailHost;
	public static String setReportEmail;
	public static String autoITPath;
	public static String testData;
	public static String screenshotOnFailure;
	public static String screenshotOnSkip;
	public static String screenshotOnPass;
	public static String ExportFilePath;
	public static String log4jPath;
	
	//Mobile related variables
	public static String APKPath;
	public static String APKfreshInstall;
	public static String Appium_Node_Path;
	public static String Appium_JS_Path,path;
	public static String AppiumServer_url;
	public static String androidDeviceName;
	public static String appPackage;
	public static String appActivity;
	public static String androidPlatformVersion;
	public static String TimeoutValueInSeconds;
	public static String ApplicationType;
	public static String MobileBrowserType;
	public static String deviceType;
	public static String iOSappType;
	public static String iOSPlatformVersion;
	public static String iOSDeviceName;
	public static String iOSBrowserName;
	public static String userType;
	public static String macIP;
	public static String macUserName;
	public static String macPwd;
	public static String gspecFilePath;
	public static String SikuliImagePath;
	public static String DriverPath;
	public static String SeleniumGrid;

	// for API testing
	public static String inputFileAPITestData;
	public static String ClientId;
	public static String ClientSecret;
	public static String RedirectUri;
	public static String Username;
	public static String Password;
	public static String UMUri; 

	// Desktop application path
	public static String DesktopApp;
	/**
	 * Sole constructor. (For invocation by subclass 
	 * constructors, typically implicit.)
	 */
	public Config() {
		
	}

	/**
	 * Reads the app.properties file and store the values in above defined fields.
	 * @throws FrameworkException 
	 */
	public static void initConstants() {
		
		String path = MKeywords.getAbsolutePath("/Resources/ConfigFiles/app.properties");
		Properties prop = new Properties();

		try {
			ofileInputStream = new FileInputStream(path);
			if (ofileInputStream != null) {
				prop.load(ofileInputStream);
				ApplicationURL = prop.getProperty("ApplicationURL");
				dataInputFile = System.getProperty("user.dir") + prop.getProperty("CSVFile");
				locatorsFile = System.getProperty("user.dir") + prop.getProperty("ORXmlFile");
				Browser = prop.getProperty("browser");
				Headless = prop.getProperty("Headless");
				ExtentReportsPath = System.getProperty("user.dir") + prop.getProperty("ExtentReportsPath");
				ReportTitle = prop.getProperty("ReportTitle");
				ReportName = prop.getProperty("ReportName");
				MaxRetryCountOnTestFailure = prop.getProperty("MaxRetryCountOnTestFailure");
				TestReportFolder = System.getProperty("user.dir") + prop.getProperty("TestReports");
				ScreenShotsPath = System.getProperty("user.dir") + prop.getProperty("ScreenShotsPath");
				ChromeDriverPath = System.getProperty("user.dir") + prop.getProperty("ChromeDriverPath");
				FirefoxDriverPath = System.getProperty("user.dir") + prop.getProperty("FirefoxDriverPath");
				IEDriverPath = System.getProperty("user.dir") + prop.getProperty("IEDriverPath");
				Environment = prop.getProperty("Environment");
				AppConfig = System.getProperty("user.dir") + prop.getProperty("AppConfig");
				TestVideoPath = System.getProperty("user.dir") + prop.getProperty("TestVideoPath");
				gspecFilePath = System.getProperty("user.dir") + prop.getProperty("GspecFiles");
				SetVideoRecording = prop.getProperty("SetVideoRecording");
				MaxSizeVideoFilesGB = prop.getProperty("MaxSizeVideoFilesGB");
				ZipPath = System.getProperty("user.dir") + prop.getProperty("ZipPath");
				mailFrom = prop.getProperty("mailFrom");
				subject = prop.getProperty("Subject");
				mailTo = prop.getProperty("mailTo");
				mailUserName = prop.getProperty("mailUserName");
				mailPassword = prop.getProperty("mailPassword");
				mailHost = prop.getProperty("mailHost");
				setReportEmail = prop.getProperty("SetReportEmail");
				autoITPath = System.getProperty("user.dir") + prop.getProperty("AutoITScriptPath");
				testData = System.getProperty("user.dir") + prop.getProperty("TestDataPath");
				screenshotOnFailure = prop.getProperty("ScreenshotOnFailure");
				screenshotOnSkip = prop.getProperty("ScreenshotOnSkip");
				screenshotOnPass = prop.getProperty("ScreenshotOnPass");
				ExportFilePath = System.getProperty("user.dir") + prop.getProperty("ExportFilePath");
				log4jPath = prop.getProperty("log4jPath");
				
				//Path for sikuli images
				SikuliImagePath = prop.getProperty("SikuliImages");
				
				//For parallel execution using grid
				SeleniumGrid = prop.getProperty("SeleniumGrid");
				
				//for drivers path
				DriverPath = prop.getProperty("DriverPath");
				
				// Mobile related properties
				APKPath = prop.getProperty("APKPath");
				APKfreshInstall = prop.getProperty("APKFreshInstall");
				Appium_Node_Path = prop.getProperty("Appium_Node_Path");
				Appium_JS_Path = prop.getProperty("Appium_JS_Path");
				ApplicationType = prop.getProperty("ApplicationType");
				MobileBrowserType = prop.getProperty("MobileBrowserType");
				
				AppiumServer_url = prop.getProperty("AppiumServer_url");
				androidDeviceName = prop.getProperty("androidDeviceName");
				appPackage = prop.getProperty("appPackage");
				appActivity = prop.getProperty("appActivity");
				androidPlatformVersion = prop.getProperty("androidPlatformVersion");
				TimeoutValueInSeconds = prop.getProperty("TimeoutValueInSeconds");
				
				deviceType = prop.getProperty("deviceType");
				iOSappType = prop.getProperty("iOS_appType");
				iOSPlatformVersion = prop.getProperty("iOSPlatformVersion");
				iOSDeviceName = prop.getProperty("iOS_Device_Name");
				iOSBrowserName = prop.getProperty("iOS_BrowserName");
				macIP = prop.getProperty("MACIP");
				macUserName = prop.getProperty("MACUserName");
				macPwd = prop.getProperty("MACPwd");
				
				//for API Testing
				inputFileAPITestData = System.getProperty("user.dir") + prop.getProperty("APITestData");
				ClientId = prop.getProperty("ClientId");
				ClientSecret = prop.getProperty("ClientSecret");
				RedirectUri = prop.getProperty("RedirectUri");
				Username = prop.getProperty("Username");
				Password = prop.getProperty("Password");
				UMUri = prop.getProperty("UMUri"); 
			}
		} 
		catch (IOException e) {
			log.error("Cannot find the app.properties file at " + path);
		} finally {
			if (ofileInputStream != null) {
				try {
					ofileInputStream.close();
				} catch (IOException e) {
					log.error("Cannot close the app.properties file instance.");
				}
			}
		}

	}
}