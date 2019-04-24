package com.autothon.core;

import java.io.File;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.log4j.Logger;
import io.appium.java_client.service.local.AppiumDriverLocalService;



/** 
 * @author RajeshPopli
 */
public class Appium  {

    public static String URL;
    private static Logger log = Logger.getLogger(Appium.class);
    public static AppiumServiceBuilder serviceBuilder;
  
    public static  AppiumDriverLocalService appiumService;
    /** 
     * @author RajeshPopli
     */
    public Appium(){
    	//PropertyConfigurator.configure("log4j.properties");
		log.info(" : Browser Constructor Called");
    }
  
    /** 
     * @author Om Mishra
     * @throws FrameworkException 
     */
    public static void startAppium() throws FrameworkException {
    	log.info(" : Start Appium Method Called");
    	Config.initConstants();
		//cap=new DesiredCapabilities();
		serviceBuilder=new AppiumServiceBuilder();		 		 
		serviceBuilder.withIPAddress("127.0.0.1");	
		serviceBuilder.usingPort(4723);
		serviceBuilder.withAppiumJS(new File(Config.Appium_JS_Path)) ;
		serviceBuilder.usingDriverExecutable(new File(Config.Appium_Node_Path));
		appiumService = AppiumDriverLocalService.buildService(serviceBuilder);
		startAppiumServer();	
		} 

    
    public static void startAppiumServer()
    {
		System.out.println("Starting Appium Server......");
		if(appiumService.isRunning()==false)
		{
		  // appiumService.start();
		   System.out.println("Appium Server started......");
		}
		else
		{
		   System.out.println("Appium Server is already Started......");
		}
    }
    public static void stopAppiumServer()
    {
    	System.out.println("Stopping Appium Server......");
    	if(appiumService.isRunning()==true)
    	{
		     appiumService.stop();
		     System.out.println("Appium Server Stopped......");
    	}
    	else
		{
		    System.out.println("Appium Serveris already Stopped......");
		}
    }
    
    public static boolean AppiumServerStatus()
	 {
		 return appiumService.isRunning();
	 }
    	
}
