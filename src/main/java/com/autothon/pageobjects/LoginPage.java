package com.autothon.pageobjects;

import com.autothon.common.keywords.MKeywords;
import com.autothon.common.keywords.SeKeywords;

/**
 * This class contains all the actions that are required to perform on LoginPage.
 */
public class LoginPage
{
	protected static final String PAGE_NAME = "Login";

	/**
	 * This method sets the username.
	 *
	 * @param username	 username of the user
	 */
	public static void setUsername(String username)
	{
		SeKeywords.setText(MKeywords.findElement(PAGE_NAME,"UsernameTextBox"), username);
	}
	
	/**
	 * This method sets the password.
	 *
	 * @param password   password of the user
	 */
	public static void setPassword(String password)
	{
		SeKeywords.setText(MKeywords.findElement(PAGE_NAME,"PasswordTextBox"), password);
	}

	/**
	 * This method clicks on login.
	 */
	public static void clickLogin()
	{
		SeKeywords.click(MKeywords.findElement(PAGE_NAME, "LoginButton"));
	}
}

