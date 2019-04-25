package com.autothon.logs;

import com.autothon.core.Config;
import com.autothon.core.FrameworkException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * The Class ExtentManager.
 *
 */
public class ExtentManager {

	/** The extent. */
	private static ExtentReports extent;

	/**
	 * Gets the single instance of ExtentManager.
	 *
	 * @return ExtentReports 	instance of Extent Report.
	 * @throws FrameworkException 
	 */
	public static ExtentReports getInstance() throws FrameworkException {
		Config.initConstants();
		if (extent == null)
			createInstance(Config.ExtentReportsPath);
		return extent;
	}

	/**
	 * Creates the instance.
	 *
	 * @param fileName 			file name.
	 * @return ExtentReports 	instance of Extent Report.
	 */
	public static ExtentReports createInstance(String fileName) {
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(Config.ReportTitle);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(Config.ReportName);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		return extent;
	}
}