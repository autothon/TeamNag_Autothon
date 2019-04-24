package com.autothon.common.keywords;

import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.autothon.core.DriverFactory;
import com.autothon.core.TestBase;
import com.aventstack.extentreports.Status;

public class MobileKeywords extends TestBase
{
	private static List<String> listLocator;
	private static By locator;
	private static String objName;
	private static WebDriverWait wait;
	static	DriverFactory instance  = DriverFactory.getInstance();
	/** 
	 * @author mohitmaliwal
	 * @throws InterruptedException 
	 */

	public static void click(By button) throws InterruptedException {
		try
		{			
			WebElement abc = instance.getDriver().findElement(button);
			abc.click();	
			extentReportLogger().log(Status.PASS, "\"" +" Button Clicked " + "\"" + button + "\"");		
		}
		catch (NoSuchElementException ex){
			log.error("No Element Found to click" + ex);
		}
	}

	public static void waitForSpinnerInvisibility() throws InterruptedException
	{
		try 
		{
			boolean isProgressPresent = MobileKeywords.exists(MKeywords.findElement("NativeGeneral", "progress"));
			if(isProgressPresent)
			{
				wait = new WebDriverWait(instance.getDriver(), 60);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(MKeywords.findElement("NativeGeneral","progress")));
				extentReportLogger().log(Status.PASS, "\"" +" Spinner is Here" + "\"");
			}
		} 
		catch (NoSuchElementException ex)
		{
			System.err.format("No Element Found to enter text" + ex);
		}
	}

	public static void setText(By textBox, String value) {
		try{			
			instance.getDriver().findElement(textBox).sendKeys(value);
			extentReportLogger().log(Status.PASS, "\"" + value + "\"" + " is entered in text box " + "\"" + textBox + "\"");
		}
		catch (NoSuchElementException ex){
			log.error("No Element Found to enter text" + ex);
		}
	}

	public static String getText(By textBox) 
	{
		String myText = null;
		try
		{			
			myText = instance.getDriver().findElement(textBox).getText();
		}
		catch (NoSuchElementException ex)
		{
			log.error("No Element Found to enter text" + ex);
		}
		return myText;
	}

	public static boolean checkElementEnabled(By button)
	{
		boolean elementEnabled = false;
		try 
		{
			elementEnabled = instance.getDriver().findElement(button).isEnabled();
		}
		catch(NoSuchElementException ex)
		{
			log.error("No Element Found to enter text" + ex);
		}
		return elementEnabled;

	}



	public static void allowAppPermission()
	{	 

		while(instance.getDriver().findElements(By.xpath("//*[@class='android.widget.Button'][2]")).size() > 0)
		{
			WebElement abc = instance.getDriver().findElement(By.xpath("//*[@class='android.widget.Button'][2]"));
			abc.click();
			extentReportLogger().log(Status.PASS, "\"" + abc + "\"" + "is clicked to give the permission");
		}	 
	}

	public static void clickNext(String objectName,String pageName)
	{

	}
	public static void clickSelect(String objectName,String pageName)
	{

	}
	public static void waitForElementVisibility(By element , int secs){
		try
		{
			MKeywords.sleep(10000);
			wait = new WebDriverWait(instance.getDriver(), secs);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			//extentReportLogger().log(Status.PASS, "\"" + "Waiting for presence of  " + "\"" + objName + "\"");
		}
		catch (Exception ex)
		{
			log.error("No Element Found to enter text" + ex);
		}
	}
	public WebElement returnElement(By description,String value)
	{
		WebElement 	retElement=null;
		retElement=instance.getDriver().findElement(description.id(value));
		return retElement;
	}
	public static void type(WebElement buttonObj,String value)
	{
		buttonObj.sendKeys(value);
	}
	public static boolean exists(By userName) 
	{
		return instance.getDriver().findElement(userName).isDisplayed();
	}

	/*public static void closeApp()
	{
	    WebElement appValue=null; 
	    driver.pressKeyCode(187);
		try
		{
		 // appValue=FindControlOnAndroid(IdenType.ResourceId,"com.android.systemui:id/dismiss_task");
		  ClickButtonOnApp(appValue);
		} 
	    catch(Exception e)
		{

		}
	}
	public static void hideKeyborad()
	{
		try{
			driver.hideKeyboard();	
		}
		catch(Exception e){}
	}
	public static void SelectAndClickMenuItem(String appText)
	{

	}
	public static void ClickButtonOnApp(WebElement buttonObj)
	{
		buttonObj.click();	
	}



	public void swipingHorizontal() throws InterruptedException {
        //Get the size of screen.
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.70);              
        int endx = (int) (size.width * 0.30);
        //Find vertical point where you wants to swipe. It is in middle of screen height.
        int starty = size.height / 2;
        System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty);

        //Swipe from Right to Left.
        driver.swipe(startx, starty, endx, starty, 3000);
        extentReportLogger().log(Status.PASS, "Application is swiped horizontaly");
        MKeywords.sleep(3000);            
       }


	public void swipingVertical() throws InterruptedException {
		//Get the size of screen.
		Dimension size = driver.manage().window().getSize();
		System.out.println(size);
		//Find swipe start and end point from screen's with and height.
		//Find starty point which is at bottom side of screen.
		int starty = (int) (size.height * 0.80);
		//Find endy point which is at top side of screen.
		int endy = (int) (size.height * 0.20);
		//Find horizontal point where you wants to swipe. It is in middle of screen width.
		int startx = size.width / 2;
		//Swipe from Bottom to Top.
		driver.swipe(startx, starty, startx, endy, 3000);
		MKeywords.sleep(2000);
		//Swipe from Top to Bottom.
		driver.swipe(startx, endy, startx, starty, 3000);
		extentReportLogger().log(Status.PASS, "Application is swiped vertically");
		MKeywords.sleep(2000);
	}*/

	public static WebElement checkVisibility()
	{
		return wait.until(ExpectedConditions.visibilityOf(instance.getDriver()
				.findElement(
						By.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']"))));

	}

}
