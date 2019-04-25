package com.autothon.tests;

import org.testng.annotations.Test;

import com.autothon.core.TestBase;
import com.autothon.dataobjects.DataProviders;
import com.autothon.listeners.Priority;
import com.autothon.pageobjects.LoginPage;
import com.autothon.pageobjects.StepInForum;
import com.autothon.pageobjects.WhoToFollow;


/**
 * This class contains test methods related to Login screen.
 */
public class LoginScreen extends TestBase 
{
	
	/**
	 * This test get the list of three users from who to follow section
	 * @param username 			username
	 * @param password 			password
	 */
	@Priority(1)
	@Test(dataProvider = "TestData", dataProviderClass = DataProviders.class/*, groups = "Smoke"*/)
	public void tc002_GetWhoToFollowList(String username, String password) 
	{
		LoginPage.setUsername(username);
		LoginPage.setPassword(password);
		LoginPage.clickLogin();
		StepInForum.clickViewAllInWhoToFollowSection();
		softAssert.assertNotNull(WhoToFollow.getUsersList(), "List should not be null.");
	}
}