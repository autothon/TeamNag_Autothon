package com.autothon.util;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.autothon.core.Config;
import com.autothon.core.TestBase;
import com.aventstack.extentreports.Status;

/**
 * This class contains methods that re-execute the entire test case by specified number of times.
 *
 */
public class RetryUtil extends TestBase implements IRetryAnalyzer {
	
	private int retryCount = 0;
	private int maxRetryCount;
	
	static Logger log = Logger.getLogger(RetryUtil.class);

	/**
	 * Instantiates a new retry.
	 */
	public RetryUtil(){
	}

	/**
	 * Below method returns 'true' if the test method has to be retried else
	 * 'false' and it takes the 'Result' as parameter of the test method that
	 * just ran.
	 *
	 * @param result 	ItestResult.
	 * @return true, 	if successful
	 */
	public boolean retry(ITestResult result) {
		boolean status = false;
		try
		{	
			maxRetryCount = Integer.parseInt(Config.MaxRetryCountOnTestFailure);
			if (retryCount < maxRetryCount) {
				log.info(" :Retrying test method -- " + result.getName() + " -- with status " + getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
				extentReportLogger().log(Status.INFO, "\"" + "Retry count: " +(retryCount + 1) + ", Test method: " +result.getName() + ", Status: " + getResultStatusName(result.getStatus()) + "\"");
				retryCount++;
				status = true;
			}
		}catch(NumberFormatException ex){
			log.error("Wrong input of MaxRetryCountOnTestFailure in app.properties");
		}
		return status;
		
	}

	/**
	 * Gets the result status name.
	 *
	 * @param status 	status of test result.
	 * @return String	result status name.
	 */
	public String getResultStatusName(int status) {
		String resultName = null;
		if (status == 1)
			resultName = "SUCCESS";
		if (status == 2)
			resultName = "FAILURE";
		if (status == 3)
			resultName = "SKIP";
		return resultName;
	}
}
