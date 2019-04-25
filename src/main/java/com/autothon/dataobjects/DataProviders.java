package com.autothon.dataobjects;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.autothon.core.Config;
import com.autothon.core.FrameworkException;
import com.autothon.util.ReadFileUtil;

/**
 * This DataProvider class is used in order to create data-driven tests.It will run the same test case, but with different data sets.
 */
public class DataProviders {

	/** The use of the comma character (,) typically separates each field of text.*/
	String seperator = ",";


	/**
	 * Get the data set required to test all the test cases.
	 * 
	 * @return Object[][], return data from the specified file.
	 * @throws FrameworkException 
	 */
	@DataProvider(name="TestData")
	public static Object[][] getTestData(Method method) throws FrameworkException
	{
		String [][] dataSet = ReadFileUtil.CSVDataProvider(method.getName(), "," ,Config.dataInputFile);
		return dataSet;
	} 

}
