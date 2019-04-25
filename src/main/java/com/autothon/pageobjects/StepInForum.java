package com.autothon.pageobjects;

import com.autothon.common.keywords.MKeywords;
import com.autothon.common.keywords.SeKeywords;

/**
 * This class contains all the actions that are required to perform on LoginPage.
 */
public class StepInForum
{
	protected static final String PAGE_NAME = "StepInForum";

	/**
	 * This method clicks on ViewAll link in Who To Follow Section.
	 */
	public static void clickViewAllInWhoToFollowSection()
	{
		SeKeywords.waitForElementInVisibility(MKeywords.findElement(PAGE_NAME, "ViewAll"), 10);
		SeKeywords.click(MKeywords.findElement(PAGE_NAME, "ViewAll"));
	}
}

