package com.autothon.pageobjects;

import java.util.List;

import com.autothon.common.keywords.MKeywords;
import com.autothon.twitter.keywords.TwitterKeywords;

/**
 * This class contains all the actions that are required to perform on LoginPage.
 */
public class WhoToFollow
{
	protected static final String PAGE_NAME = "WhoToFollow";

	/**
	 * This method clicks on ViewAll link in Who To Follow Section.
	 */
	public static List<String> getUsersList()
	{
		MKeywords.sleep(5000);
		return TwitterKeywords.getListOfAvailableOptions(MKeywords.findElement(PAGE_NAME,"ListGrid"), MKeywords.findElement(PAGE_NAME,"ListOptions"));
	}
}

