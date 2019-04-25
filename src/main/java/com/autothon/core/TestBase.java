package com.autothon.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.autothon.common.keywords.MKeywords;
import com.autothon.logs.ExtentManager;
import com.autothon.mobile.MobileService;
import com.autothon.util.CommonFunctionUtil;
import com.autothon.util.ReadFileUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import net.rcarz.jiraclient.JiraException;

/**
 * This is the base class in which all the initialization required to run the test cases occurs.
 * This class basically includes:
 * 1) Initialization of webdriver, extent reporting and logging.
 * 2) Closing of browser and webdriver.
 */

public class TestBase
{
	protected static ExtentReports extent;
	protected static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	protected static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	protected static ThreadLocal<ExtentTest> logger;
	protected CommonFunctionUtil util;
	protected static boolean jiraStatus;
	protected static boolean jiraBug;
	protected static String elementPath;
	protected static String seperator = ",";
	protected static ReadFileUtil dataSourceOperations = new ReadFileUtil();
	protected static SoftAssert softAssert;
	public static Logger log = Logger.getLogger(TestBase.class);
	protected static ATUTestRecorder recorder;
	public static String systemPlatform = null;
	public static String browserValue = null;
	public static String platformValue = null;
	
	/**
	 * Instantiates a new test base.
	 */
	public TestBase() {
		Config.initConstants();
		String log4jPath = MKeywords.getAbsolutePath(Config.log4jPath);
		PropertyConfigurator.configure(log4jPath + "/log4j.properties");	
	}

	/**
	 * This method:
	 * 1) creates extent manager instance
	 * 2) creates screenshot folder path if not exists.
	 * 3) creates ZipPath folder if not exists.
	 * 4) start video recording if set as 'true'.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ATUTestRecorderException the ATU test recorder exception
	 * @throws FrameworkException 
	 * @throws InterruptedException 
	 */
	@BeforeSuite(alwaysRun = true)
	protected void BeforeSuite() throws IOException, ATUTestRecorderException, InterruptedException, FrameworkException {
		log.info("-----------------EXECUTION START----------------------");
		log.info(" : TestBase - BeforeSuite called");
		extent = ExtentManager.getInstance();
		CommonFunctionUtil.isFolderExistAtPath(Config.TestReportFolder);
		CommonFunctionUtil.isFolderExistAtPath(Config.ScreenShotsPath);
		CommonFunctionUtil.isFolderExistAtPath(Config.ZipPath);
		CommonFunctionUtil.isFolderExistAtPath(Config.ExportFilePath);
		FileUtils.cleanDirectory(new File(Config.ScreenShotsPath));

		if (Config.SetVideoRecording.equalsIgnoreCase("True")) {
			recorder = CommonFunctionUtil.StartVideoRecording();
		}
	}

	/**
	 * This method sets extent reporting.
	 */
	@BeforeClass(alwaysRun = true)
	protected void BeforeClass() {
		log.info(" : TesTBase - BeforeClass called");
		ExtentTest parent = extent.createTest(getClass().getName());
		parentTest.set(parent);
	}

	@BeforeTest(alwaysRun = true)
	protected void BeforeTest() {
		
		log.info(" : TesTBase - BeforeTest called");
	}

	/**
	 * This method calls openbrowser() method.
	 *
	 * @param method the method.
	 * @throws Exception 
	 */
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "webdriverHost", "webdriverPort", "browser", "platform"})
	protected void BeforeMethod(String webdriverHost, int webdriverPort, String browser, String platform, Method method) throws Exception {
		try {
			browserValue = browser;
			platformValue = platform;
			log.info(" : TestBase - BeforeMethod called");
			log.info(" Browser called -> " + browser);
			softAssert = new SoftAssert();
			String grid = Config.SeleniumGrid;
			systemPlatform = platform;
			
			if((platform.equalsIgnoreCase("WINDOWS") && grid.equalsIgnoreCase("Yes")))
			{
				DriverFactory.getInstance().setDriverWithGrid(webdriverHost, webdriverPort, browser, platform);
			}
			else if ((platform.equalsIgnoreCase("WINDOWS") && grid.equalsIgnoreCase("No")))
			{
				DriverFactory.getInstance().setDriverNoGrid(browser, platform);
			}
			else if (platform.equalsIgnoreCase("REST"))
			{
					DriverFactory.getInstance().setAPIHost();
			} 
			else if (platform.equalsIgnoreCase("mobile"))
			{
					DriverFactory.getInstance().setDriverForMobile(platform);
			}
			ExtentTest child = parentTest.get().createNode(method.getName());
			test.set(child);
			extentReportLogger().info("Browser called -> " + browser );
			log.info("Test Method started -> " + method.getName());
		} catch (FrameworkException e) {
			log.error(e);
		}
	}


	/**
	 * This method checks whether the test is pass, fail or skip and the action accordingly.
	 *
	 * @param result 	gives user the result of test case.
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * @throws URISyntaxException 
	 * @throws JiraException 
	 * @throws FrameworkException 
	 */
	@AfterMethod(alwaysRun = true)
	protected void AfterMethod(ITestResult result) throws FrameworkException, JiraException, URISyntaxException, InterruptedException, ExecutionException {
		log.info(" : TestBase - AfterMethod called");
		DriverFactory instance  = DriverFactory.getInstance(); 
		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				log.info("Test case PASSED");

				extentReportLogger().pass(MarkupHelper.createLabel(result.getName() + " Test case PASSED", ExtentColor.GREEN));
				if (Config.screenshotOnPass.equalsIgnoreCase("true")){
					String screenShotPath = CommonFunctionUtil.captureScreenshot(instance.getDriver(), "Pass", result);
					extentReportLogger().pass("Snapshot below: " + test.get().addScreenCaptureFromPath(screenShotPath));
				}
			}else if (result.getStatus() == ITestResult.FAILURE) {
				log.info("Test case FAILED");

				extentReportLogger().fail(result.getThrowable().getMessage());
				extentReportLogger().fail(MarkupHelper.createLabel(result.getName() + " Test case FAILED", ExtentColor.RED));
				if (Config.screenshotOnFailure.equalsIgnoreCase("true")){
					extentReportLogger().fail("Environment on which test executed is -->" + Config.ApplicationURL);
					String screenShotPath = CommonFunctionUtil.captureScreenshot(instance.getDriver(), "Failure", result);
					extentReportLogger().fail("Snapshot below: " + test.get().addScreenCaptureFromPath(screenShotPath));
					log.info("This test method " + result.getName()  + " failed on browser ->" + browserValue);
				}
			} else if (result.getStatus() == ITestResult.SKIP){
				log.info("Test case SKIPPED");

				extentReportLogger().skip(result.getThrowable());
				extentReportLogger().skip(MarkupHelper.createLabel(result.getName() + " Test case SKIPPED", ExtentColor.BLUE));
				if (Config.screenshotOnSkip.equalsIgnoreCase("true")){
					String screenShotPath = CommonFunctionUtil.captureScreenshot(instance.getDriver(), "Skipped", result);
					extentReportLogger().skip("Snapshot below: " + test.get().addScreenCaptureFromPath(screenShotPath));
				}
			}
			else{
				log.info(" : Some error ocurred in TesTBase - @AfterMethod");
			}
			log.info(" Test Method completed -> " + result.getName());
			quitBrowser();
			/*if(systemPlatform.equalsIgnoreCase("windows"))
			 instance.getDriver().quit();*/
		}
		catch (IOException e){
			log.error("No Element Found to enter text" + e);
		}
	}

	@SuppressWarnings("static-access")
	@AfterClass(alwaysRun = true)
	protected void AfterClass() {
		log.info(" : TestBase - AfterClass called");

		try 
		{
			quitBrowser();
			if(systemPlatform.equalsIgnoreCase("Mobile"))
			{
				new MobileService().stopAppiumServer();
			}
		}catch (Exception e) {
			log.error(e.getStackTrace());
		}
	} 

	@AfterTest(alwaysRun = true)
	protected void AfterTest() 
	{
		log.info(" : TestBase - AfterTest called");
	}

	/**
	 * This method:
	 * 1) closes the extent reporting.
	 * 2) creates the zip folder of screenshots.
	 * 3) Email the test report if set as true.
	 * 4) stops recording if set as true.
	 *
	 * @throws Exception 	on error.
	 */
	@AfterSuite(alwaysRun = true)
	protected void afterSuite() throws Exception {
		log.info(" : TestBase - AfterSuite called");
		extent.flush();
		CommonFunctionUtil.setScreenshotRelativePath();
		String zipFilePath = Config.ZipPath + CommonFunctionUtil.generateUniqueName() + ".zip";
		CommonFunctionUtil.zipFolder(Paths.get(Config.ScreenShotsPath), Paths.get(zipFilePath));
		if (Config.setReportEmail.equalsIgnoreCase("True")) {
			CommonFunctionUtil.SendMail(zipFilePath);
		}

		if (Config.SetVideoRecording.equalsIgnoreCase("True")) {
			recorder.stop();
		}
		log.info("---------------EXECUTION COMPLETED--------------------");
	}

	public void quitBrowser() {
		try {
			if(browserValue.equalsIgnoreCase(DriverFactory.FIREFOX) && systemPlatform.equalsIgnoreCase("windows")){
				Runtime.getRuntime().exec("Taskkill /IM firefox.exe /F");
			}else if (browserValue.equalsIgnoreCase(DriverFactory.FIREFOX) && systemPlatform.equalsIgnoreCase("linux")){
				Runtime.getRuntime().exec("killall -9 firefox.exe");
			}else {
				DriverFactory.getInstance().getDriver().quit();
				DriverFactory.getInstance().driver.remove();
			}
		}catch(IOException e) {
			log.error("Error ocurred while closing this browser." + e);
		}
	}

	public static ExtentTest extentReportLogger() {
		return test.get();		
	}
}
