package com.autothon.mobile;

import com.autothon.common.keywords.MKeywords;

public class demo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DriverAndroid d = new AppiumClient().createAndroidDriver();
		d.click(MKeywords.GetElement("//com.android.packageinstaller[@id='permission_allow_button']", "xpath"));
		

	}

}
