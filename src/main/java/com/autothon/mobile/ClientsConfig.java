package com.autothon.mobile;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;

public class ClientsConfig {

	/* The instance of SeleniumConfigSpec */
	private static final ClientsConfigSpec INSTANCE = ConfigFactory.create(ClientsConfigSpec.class,
			System.getProperties(), System.getenv());

	public static ClientsConfigSpec getInstance() {
		return INSTANCE;
	}

	@LoadPolicy(LoadType.MERGE)
	@Sources({ "classpath:com/autothon/mobile/ClientsConfig.properties" })
	public interface ClientsConfigSpec extends Config {

		@Key("mobile.port")
		@DefaultValue("4723")
		int getPort();

		@Key("mobile.ip.address")
		@DefaultValue("127.0.0.1")
		String getIp();

		@Key("mobile.application.type")
		@DefaultValue("AndroidNativeApp")
		String getApplicationType();

		@Key("mobile.apk.path")
		@DefaultValue("")
		String getApkPath();

		@Key("mobile.device.name")
		@DefaultValue("deviceName")
		String getdeviceName();

		@Key("mobile.device.android")
		@DefaultValue("Android")
		String getPlatformName();

		@Key("mobile.platorm.version")
		@DefaultValue("9")
		String getPlatformVersion();
		
		@Key("mobile.app.activity")
		@DefaultValue("")
		String getAppActivity();
		
		@Key("mobile.app.package")
		@DefaultValue("")
		String getAppPackage();
		
		@Key("no.reset.app")
		@DefaultValue("true")
		String isNoResetApp();
			
		@Key("is.android.web.app")
		@DefaultValue("false")
		boolean isAndroidWebApp();
		
		@Key("mobile.app.browser")
		@DefaultValue("false")
		boolean AndroidAppBrowser();
	}
}
