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
	public static String APIHost="https://api.twitter.com/1.1/statuses";

	public static String ConsumerKey="HXBDwvuE0IWoBJrWhEP3awKeG";
	public static String ConsumerSecret="M5wLtxJqoA29XGPgdMg8ZD0KusF29JxNNepEyMKydVbMiWFrLB";
	public static String Token="477845563-xB1ZS6KtME9ghTFLJAAC5vq5XKt24aC6vYKKUdQz";
	public static String TokenSecret="MrDvlv98On06r6oVdLmgHvJyzVwPUeqmwKBGGOsdsd4P1";
	
	
	
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
				APIHost = prop.getProperty("APIHost");
				ConsumerKey = prop.getProperty("ConsumerKey");
				ConsumerSecret=prop.getProperty("ConsumerSecret");
				Token=prop.getProperty("Token");
				TokenSecret=prop.getProperty("TokenSecret");
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