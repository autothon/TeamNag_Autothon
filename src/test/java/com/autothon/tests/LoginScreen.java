package com.autothon.tests;

import org.testng.annotations.Test;

import com.autothon.core.TestBase;
import com.autothon.dataobjects.DataProviders;
import com.autothon.listeners.Priority;
import com.autothon.pageobjects.LoginPage;


/**
 * This class contains test methods related to Login screen.
 */
public class LoginScreen extends TestBase 
{
	
	/**
	 * This test enter the username and password and clicks on login button
	 * @param username 			username
	 * @param password 			password
	 */
	@Priority(3)
	@Test(dataProvider = "TestData", dataProviderClass = DataProviders.class, groups = "Smoke")
	public void tc001_LoginToApplication(String username, String password) 
	{
		LoginPage.setUsername(username);
		LoginPage.setPassword(password);
		LoginPage.clickLogin();
	}
}