package com.autothon.twitter.keywords;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.autothon.common.keywords.JavaScriptKeywords;
import com.autothon.common.keywords.MKeywords;
import com.autothon.common.keywords.SeKeywords;
import com.autothon.core.DriverFactory;
import com.autothon.core.TestBase;

public class TwitterKeywords extends TestBase{
	
	private static WebDriverWait wait ;
	static	DriverFactory instance  = DriverFactory.getInstance(); 
		
	public static void selectTypeOfRoom(String typeOfRoom) throws InterruptedException
	{
		if(typeOfRoom.equalsIgnoreCase("kitchen"))
    	{
    		SeKeywords.click(MKeywords.findElement("RoomType", "Kitchen"));
    	}
    	else if(typeOfRoom.equalsIgnoreCase("gym"))
    	{
    		SeKeywords.click(MKeywords.findElement("RoomType", "Gym"));
    	}
    	else if(typeOfRoom.equalsIgnoreCase("exterior"))
    	{
    		SeKeywords.click(MKeywords.findElement("RoomType", "Exterior"));
    	}
	}
	
	public static void waitForSpinnerInvisibility() {
		try {
			wait = new WebDriverWait(instance.getDriver(), 420);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(MKeywords.findElement("General","Spinner")));
			extentReportLogger().log(Status.PASS, "\"" + "Element invisibility verified for " + "\"" + elementPath + "\"");
			log.info("\"" + "Element invisibility verified for " + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
		}
	}
	
	/**
	 * This method is used to handle the progress bar with text which is appeared on screen every time user starts RSSI
	 */
	public static void waitForProgressBarTextInvisibility()
	{
		try
		{
			wait = new WebDriverWait(instance.getDriver(), 300);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(MKeywords.findElement("ScheduleGeneral", "ProgressBarText")));
			log.info("\"" + "Progress bar invisibility verified for " + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
		}
	}
	
	/**
	 * This method is used to handle the progress bar with out text which is appeared on screen every time user starts RSSI
	 */
	public static void waitForProgressBarInvisibility()
	{
		try
		{
			wait = new WebDriverWait(instance.getDriver(), 300);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(MKeywords.findElement("ScheduleGeneral", "ProgressBar")));
			log.info("\"" + "Progress bar invisibility verified for " + "\"" + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
		}
	}
	

	/**
	 * This method drags the wheeler for hour
	 * @param wheeler				element value for the wheeler
	 */
	public static void dragHours(By wheeler)
	{
		try {
			WebElement dragElementFrom = instance.getDriver().findElement(wheeler);
			Actions actionBuilder = new Actions(instance.getDriver());
			actionBuilder.clickAndHold(dragElementFrom).moveByOffset(0, -80).build().perform();
			actionBuilder.release(dragElementFrom);
			log.info("Mouse is released");
			MKeywords.sleep(5000);
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
	}
	
	/**
	 * This method drags the wheeler for the 
	 * @param wheeler						element value for the wheeler
	 * @param value							value up to which wheeler needs to be draged
	 */
	public static void dragWheeler(By wheeler, int value) 
	{
		try {
		WebElement dragElementFrom = instance.getDriver().findElement(wheeler);
		Actions actionBuilder = new Actions(instance.getDriver());
		actionBuilder.dragAndDropBy(dragElementFrom, 0, value).build().perform();
		MKeywords.sleep(5000);
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
	}
	
	public static void setTimeClockFixedTime() 
	{
		try {
		JavascriptExecutor js = (JavascriptExecutor)instance.getDriver();
		js.executeScript("var b = $(\"#fn-inputFixedTimeTarget\").scroller('hide');");
		js.executeScript("b = $(\"#fn-inputFixedTimeTarget\").scroller('show');");
		js.executeScript("b.updateValue(\"9:00\");");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
	}
	
	//For Tuning Low End trim
	public static void setWheelerValuesUsingJSExecutor(String value) 
	{
		try {
		JavascriptExecutor js = (JavascriptExecutor)instance.getDriver();
		js.executeScript("var b = $(\"#fn-inputWheelTarget\").scroller('hide');");
		js.executeScript("b = $(\"#fn-inputWheelTarget\").scroller('show');");
		js.executeScript("b.updateValue(\"8%\");");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
	}
	
	/**
	 * This method sets the time on wheeler. 
	 *
	 * @param currentTime 		system time (in h:mm a format. e.g. 01:20 AM)
	 * @param timeToSelect 		time user wants to set (in h:mm a format. e.g. 01:20 AM)
	 * @param hourElement 		hour element.
	 * @param minutesElement 	minute element.
	 * @param amPMElement 		AM PM element.
	 */
	public static void setWheelerValues(String currentTime, String timeToSelect, By hourElement, By minutesElement, By amPMElement) {
		try {
			WebElement dragHourElement = instance.getDriver().findElement(hourElement);
			WebElement dragMinuteElement = instance.getDriver().findElement(minutesElement);
			WebElement dragAmpmElement = instance.getDriver().findElement(amPMElement);
			Actions actionBuilder = new Actions(instance.getDriver());
			
			int hour = 1;
			int minutes = 0;
			int ampmOffset = 0;
			int count = 1;
			
			String[] time = currentTime.split(":");
			int currentHour = Integer.parseInt(time[0]);
			int currentMinutes = Integer.parseInt(time[1].split(" ")[0]);
			String currentAmPm = time[1].split(" ")[1];
			
			String[] selectionTime = timeToSelect.split(":");
			int hourToSelect = Integer.parseInt(selectionTime[0]);
			int minuteToSelect = Integer.parseInt(selectionTime[1].split(" ")[0]);
			String amPMToSelect = selectionTime[1].split(" ")[1];
			
			if(currentMinutes > 30) {
				if(currentHour==12) {
					currentHour = 1;
				}else {
					currentHour = currentHour+1;
				}
			}		
			if(currentHour!=hourToSelect) {
				hour = currentHour-hourToSelect;
				if(hour > 0) {
					while(count <= hour) {
						actionBuilder.clickAndHold(dragHourElement).moveByOffset(0, 40).build().perform();
						count= count+1;
						actionBuilder.release().click();
					}
				}else {
					count = -1;
					while(count >= hour) {
						actionBuilder.clickAndHold(dragHourElement).moveByOffset(0, -40).build().perform();
						count = count-1;
						actionBuilder.release().click();
					}
				}
			}
			if(currentMinutes!=minuteToSelect) {
				if(minuteToSelect > 30) {
					count = 1;
					minutes = 60-minuteToSelect;
					while(count <= minutes) {
						actionBuilder.clickAndHold(dragMinuteElement).moveByOffset(0, 40).build().perform();
						count= count+1;
						actionBuilder.release().click();
					}
				}else if(minuteToSelect < 30) {
					count=-1;
					minutes = 0-minuteToSelect;
					while(count >= minutes) {
						actionBuilder.clickAndHold(dragMinuteElement).moveByOffset(0, -40).build().perform();
						count = count-1;
						actionBuilder.release().click();
					}
				}
			}
			if(!amPMToSelect.equals(currentAmPm) && amPMToSelect.equals("AM")) {
				ampmOffset = 40;
			}else if(!amPMToSelect.equals(currentAmPm) && amPMToSelect.equals("PM")) {
				ampmOffset = -40;
			}
			actionBuilder.clickAndHold(dragAmpmElement).moveByOffset(0, ampmOffset).build().perform();
			actionBuilder.release().click().perform();
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
	}
	
	/**
	 * This method sets the value in wheeler
	 * @param wheeler				element value for the wheeler						
	 */
	public static void setWheelerValues(By wheeler)
	{
		try {
			WebElement dragElementFrom = instance.getDriver().findElement(wheeler);
			Actions actionBuilder = new Actions(instance.getDriver());
			actionBuilder.clickAndHold(dragElementFrom).moveByOffset(0, 40).build().perform();
			actionBuilder.release(dragElementFrom);
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
	}

	/**
	 * This method changes the am or pm value for time wheeler
	 * @param amPmWheeler					element value for the wheeler
	 */
	public static void setTimeClockFixedTimeAmPm(By amPmWheeler)
	{
		try {
		wait = new WebDriverWait(instance.getDriver(), 40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(amPmWheeler));
		
		WebElement dragElementFrom = instance.getDriver().findElement(amPmWheeler);
		Actions actionBuilder = new Actions(instance.getDriver());
		actionBuilder.clickAndHold(dragElementFrom).moveByOffset(0, 40).perform();
		actionBuilder.release(dragElementFrom);
		MKeywords.sleep(5000);
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
	}
	
	
	public static boolean checkURL(String subText)
	{
		boolean flag = false;
		try {
		String url = instance.getDriver().getCurrentUrl();
		if(url.contains(subText))
		{
			flag = true;
		}
		System.out.println("Check URL"+flag);
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
		return flag;    
	}
	
	public static boolean checkElementIsEnabled(By element)
	{
		boolean flag = false;
		try {
		wait = new WebDriverWait(instance.getDriver(),60);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		if((instance.getDriver().findElement(element).isEnabled()))
        {
        	flag = true;
        }
		System.out.println("Check Element to display "+element+" "+flag);
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
		return flag;
	}
	
	//Added TabOut Function	
		public static void tabOut(By element)
		{
			try {
			instance.getDriver().findElement(element).sendKeys(Keys.TAB);
			} catch (NoSuchElementException e) {
				extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
				log.error("No Element Found" + e);
				throw(e);
			} catch (TimeoutException e) {
				extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
				log.error("Timeout. Element not found." + e);
				throw(e);
			} catch (Exception e) {
				extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
				log.error("Exception: " + e);
				throw(e);
			}
	    }

		@Deprecated
		/**
		 * Use method: SeKeywords.isElementDisplayed
		 * @param element
		 * @return
		 */
	public static boolean checkElementIsDisplayed(By element)
	{
		boolean flag = false;
		try {
		wait = new WebDriverWait(instance.getDriver(),100);
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
		if((instance.getDriver().findElement(element)).isDisplayed())
	    {
	       	flag = true;
	    }
		else
		{
			flag = false;
		}
		System.out.println("Check Element to display "+ element + " "+flag);
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
		return flag;
	}
	
	public static String getURL()
	{
		String url;
		try {
		url = instance.getDriver().getCurrentUrl();
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
		return url;
	}
	
	  public static String fetchValueOfElement(String pageName, String nameOfElement)
	   {
		  String text;
		  try {
		   wait = new WebDriverWait(instance.getDriver(),60);
		   By element = MKeywords.findElement(pageName, nameOfElement);
		   wait.until(ExpectedConditions.presenceOfElementLocated(element));
		   wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		   text = instance.getDriver().findElement(element).getText();
		  } catch (NoSuchElementException e) {
				extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
				log.error("No Element Found" + e);
				throw(e);
			} catch (TimeoutException e) {
				extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
				log.error("Timeout. Element not found." + e);
				throw(e);
			} catch (Exception e) {
				extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
				log.error("Exception: " + e);
				throw(e);
			}
		   return text;
	   }
	  
	  public static String fetchValueOfElementUsingJSExecutor(String pageName, String nameOfElement)
	  {
		  JavascriptExecutor js = (JavascriptExecutor)instance.getDriver();
		  return (String) js.executeScript("return document.getElementById(\"generic-textbox-1\").value");
	  }
	  
	  public static String fetchAttributeValue(By element, String attribute)
	   {
		  try {
		   wait = new WebDriverWait(instance.getDriver(),60);
		   wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		  } catch (NoSuchElementException e) {
				extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
				log.error("No Element Found" + e);
				throw(e);
			} catch (TimeoutException e) {
				extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
				log.error("Timeout. Element not found." + e);
				throw(e);
			} catch (Exception e) {
				extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
				log.error("Exception: " + e);
				throw(e);
			}
		   return instance.getDriver().findElement(element).getAttribute(attribute);
	   }
	  
		public static void printLog(String textToPrint)
		{
			extentReportLogger().log(Status.PASS, textToPrint);
		}
	
	public static boolean occupancyStatus(String occupancyValue)
	{
		boolean flag = false;
		try {
		if(occupancyValue.equalsIgnoreCase("occupied")||occupancyValue.equalsIgnoreCase("unoccupied")||occupancyValue.equalsIgnoreCase("occupancy disabled")||occupancyValue.equalsIgnoreCase("occupancy unknown"))
		{
			flag = true;
		}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
		return flag;
	}

	public static boolean isElementPresent(String pageName, String elementName) {
		boolean flag;
		try {
			if(instance.getDriver().findElements(MKeywords.findElement(pageName, elementName)).size()!=0)
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
		return flag;
	}
	
	public static void launchUrl(String url) {
		try {
		instance.getDriver().get(url);	
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
	}
	
	public static void fileUpload(By element, String filePath) 
	{
		try {
		WebElement uploadElement = instance.getDriver().findElement(element);
		uploadElement.sendKeys(filePath);
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
	}

	/**
	 * This method selects the specified date.
	 * 
	 * @param  dateAccordian		date grid element.
	 * @param  dateToBeSelected 	date user wants to select.
	 */
	public static void selectTimeClockDate(By dateAccordian, String dateToBeSelected) {
		String dateValue = null;
		try {
			wait = new WebDriverWait(instance.getDriver(), 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(dateAccordian));
			WebElement datesElement = instance.getDriver().findElement(dateAccordian);			
			WebElement activeDateElement = datesElement.findElement(By.className("itemslide-active"));
			List<WebElement> dates = activeDateElement.findElements(By.className("day"));
			for(WebElement date:dates){
				int attempts = 0;
				while (attempts < 1) {
					try {
						dateValue = date.getText();
					} catch (StaleElementReferenceException e) {
						extentReportLogger().log(Status.INFO, "\"" + "Stale Reference Exception is caught while getting the date value from  " + "\"" + elementPath + "\"" + " Retrying.....");
						log.info("Stale Reference Exception is caught while getting the date value from  " + "\"" + elementPath + "\"" + " Retrying.....");
					}
					attempts++;
				}
				if(dateValue.equals(dateToBeSelected)) {
					wait.until(ExpectedConditions.elementToBeClickable(date));
					date.click();	
					extentReportLogger().log(Status.PASS, "\"" + "Date " + dateToBeSelected + " is selected." + "\"");
					log.info("\"" + "Date " + dateToBeSelected + " is selected." + "\"");
					break;
				}
			}	
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
	}
	
	/**
	 * This method verifies that whether the specified date is disable or not.
	 * 
	 * @param  dateAccordian	date grid element.
	 * @param	dateToVerify, 	date user wants to verify.
	 * @return true, if date is disabled.
	 */
	public static boolean isDateDisable(By dateAccordian, String dateToVerify) {
		boolean isDateDisable = false;
		String dateValue = null;
		try {
			wait = new WebDriverWait(instance.getDriver(), 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(dateAccordian));
			WebElement datesElement = instance.getDriver().findElement(dateAccordian);			
			WebElement activeDateElement = datesElement.findElement(By.className("itemslide-active"));
			List<WebElement> dates = activeDateElement.findElements(By.xpath("//td[contains(@class,'disabled')]"));
			for(WebElement date:dates){
				int attempts = 0;
				while (attempts < 1) {
					try {
						dateValue = date.getText();
					} catch (StaleElementReferenceException e) {
						extentReportLogger().log(Status.INFO, "\"" + "Stale Reference Exception is caught while getting the date value from  " + "\"" + elementPath + "\"" + " Retrying.....");
						log.info("Stale Reference Exception is caught while getting the date value from  " + "\"" + elementPath + "\"" + " Retrying.....");
					}
					attempts++;
				}
				if(dateValue.equals(dateToVerify)) {
					isDateDisable = true;	
					extentReportLogger().log(Status.PASS, "\"" + "Date " + dateValue + " is disabled." + "\"");
					log.info("\"" + "Date " + dateValue + " is disabled." + "\"");
					break;
				}
			}	
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
		return isDateDisable;
	}
	
	/**
	 * This method get the value of selected date.
	 * 
	 * @param  dateAccordian	date grid element.
	 * @return	List<String>, 	list of selected dates.
	 */
	public static List<String> getSelectedDates(By dateAccordian) {
		List<String> selectedDatesList;
		try {
			selectedDatesList = new ArrayList<String>();
		wait = new WebDriverWait(instance.getDriver(), 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(dateAccordian));
		WebElement datesElement = instance.getDriver().findElement(dateAccordian);
		WebElement activeDateElement = datesElement.findElement(By.className("itemslide-active"));
		List<WebElement> selectedDates = activeDateElement.findElements(By.className("round-selected"));
		for(WebElement selectedDate: selectedDates) {
			selectedDatesList.add(selectedDate.getText());
			extentReportLogger().log(Status.PASS, "\"" + "Selected date is: " + "\"" + selectedDate.getText() + "\"");
			log.info("\"" + "Selected date is: " + "\"" + selectedDate.getText() + "\"");
		}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
		return selectedDatesList;
	}
	
	public static boolean verifyHeader(String PageName, String ElementToVerify, String HeaderToMatch) 
	{
		String HeaderText;
    	boolean flag=false;
    	if(TwitterKeywords.isElementPresent(PageName, ElementToVerify))
    	{
    		HeaderText=SeKeywords.getText(MKeywords.findElement(PageName, ElementToVerify));
    		if(HeaderToMatch.equals(HeaderText))
    		{
    			flag=true;
    		}
    		else 
    		{
    			flag=false;
    		}
    	}
    	return flag;
	}
	
	/**
	 * This method compare previous and next dates with current date.
	 * @param  currentDate	current date value.
	 * @param  previousDate	previous date value.
	 * @param  nextDate		next date value.
	 * @return	true, if previous and next dates are verified with current date successfully.
	 */
	public static boolean compareDates(String currentDate, String previousDate, String nextDate) {

		boolean result = false;
		Date currentMonth;
		Date currentYear;
		Date previousMonth = null;
		Date previousYear = null;
		Date nextMonth = null;
		Date nextYear = null;

		try {
			DateFormat monthFormat = new SimpleDateFormat("MMMM");
			DateFormat yearFormat = new SimpleDateFormat("YYYY");

			String[] currentDateSplit = currentDate.split(" ");
			currentMonth = monthFormat.parse(currentDateSplit[0]);		
			currentYear = yearFormat.parse(currentDateSplit[1]);	

			if(!previousDate.isEmpty()){
				String[] previousDateSplit = previousDate.split(" ");
				previousMonth = monthFormat.parse(previousDateSplit[0]);
				previousYear = yearFormat.parse(previousDateSplit[1]);
				if(currentYear.equals(previousYear) && currentMonth.after(previousMonth)) {
					result = true;
				}else if(currentYear.after(previousYear) && currentMonth.after(previousMonth)) {
					result = true;
				}else {
					extentReportLogger().log(Status.INFO, "\"" + "There is some issue in comapring the dates" + "\"");
					log.info("There is some issue in comapring the dates");
				}
			}

			if(!nextDate.isEmpty()) {
				String[] nextDateSplit = nextDate.split(" ");
				nextMonth = monthFormat.parse(nextDateSplit[0]);
				nextYear = yearFormat.parse(nextDateSplit[1]);
				if(currentYear.equals(nextYear) && currentMonth.before(nextMonth)) {
					result = true;		
				}else if(currentYear.before(nextYear) && currentMonth.before(nextMonth)) {
					result = true;
				}else {
					extentReportLogger().log(Status.INFO, "\"" + "There is some issue in comapring the dates" + "\"");
					log.info("There is some issue in comapring the dates");
				}
			}
		}catch (ParseException e) {
			extentReportLogger().log(Status.ERROR, "\"" + "Exception occurred while parsing the date." + "\"");
			log.error("\"" + "Exception occurred while parsing the date." + "\"" + e);
		}catch(Exception e) {
			extentReportLogger().log(Status.ERROR, "\"" + "Some error occurred while comparing the dates." + "\"");
			log.error("\"" + "Some error occurred while comparing the dates." + e);
			throw(e);
		}
		return result;
	}
	
	/**
	 * This method selects the specified option from available options.
	 * @param  optionsGrid				grid element contains all the options.
	 * @param  optionsToSelect			option name that user wants to select.
	 * @param  optionElement		    option element that user wants to select.
	 */
	public static void selectOptionFromAvailableItems(By optionsGrid, String optionToSelect, By optionElement) {
        try {
               wait = new WebDriverWait(instance.getDriver(), 30);
               wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
               WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
               List<WebElement> options = optionsGridElement.findElements(optionElement);
               for (WebElement option : options) {
            	   Actions actionBuilder=new Actions(instance.getDriver());
            	   actionBuilder.moveToElement(option).build().perform();
                      if (optionToSelect.equals(option.getText())) {
                    	  MKeywords.sleep(1000);
	                	  option.click();
	                      extentReportLogger().log(Status.PASS, "\"" + optionToSelect + " is selected." + "\"");
	                      log.info("\"" + optionToSelect + " is selected." + "\"");
	                      break;
                      }
               }      
        } catch (NoSuchElementException e) {
               extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
               log.error("No Element Found" + e);
               throw(e);
        } catch (TimeoutException e) {
               extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
               log.error("Timeout. Element not found." + e);
               throw(e);
        } catch (Exception e) {
               extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
               log.error("Exception: " + e);
               throw(e);
        }
  }
	
	/**
	 * This method selects the radio check box buttons of specified options.
	 * @param  optionsGrid				grid element contains all the options.
	 * @param  optionsToSelect			list of options user wants to select.
	 * @param  optionsRowElement		element which returns number of options.
	 * @param  optionsCheckboxElement	radio check-box element.
	 * @param  optionsNameElement		element which returns name of options.
	 */
	public static void selectOptionsCheckboxFromList(By optionsGrid, List<String> optionsToSelect, By optionsRowElement, By optionsCheckboxElement, By optionsNameElement) {
		try {
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> rows = optionsGridElement.findElements(optionsRowElement);
			for (WebElement row : rows) {
				Actions actionBuilder=new Actions(instance.getDriver());
         	    actionBuilder.moveToElement(row).build().perform();
				WebElement checkbox = row.findElement(optionsCheckboxElement);
				WebElement optionName = row.findElement(optionsNameElement);
				if (optionsToSelect.contains(optionName.getText()) && checkbox.getAttribute("class").contains("hide")) {
					optionName.click();
					extentReportLogger().log(Status.PASS, "\"" + optionName.getText() + " is selected." + "\"");
					log.info("\"" + optionName.getText() + " is selected." + "\"");
				}else if(!optionsToSelect.contains(optionName.getText()) && !checkbox.getAttribute("class").contains("hide")) {
					optionName.click();
					extentReportLogger().log(Status.PASS, "\"" + optionName.getText() + " is de-selected." + "\"");
					log.info("\"" + optionName.getText() + " is de-selected." + "\"");
				}
			}	
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
	}
	
	/**
	 * This method get the list of all the available options in a page.
	 * @param  optionsGrid		grid element contains all the options.
	 * @param  optionElement	element which returns name of options.
	 * @return List<String>, list of all the available options.
	 */
	public static List<String> getListOfAvailableOptions(By optionsGrid, By optionElement) {
		List<String> listOfOptions;
		int count;
		try {
			listOfOptions = new ArrayList<String>();
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> options = optionsGridElement.findElements(optionElement);
			count = options.size();
			MKeywords.sleep(1000);
			int i =0;
			
			for (WebElement option : options) {
				if(i<4) {
				Actions actionBuilder=new Actions(instance.getDriver());
				actionBuilder.moveToElement(option).build().perform();
				if(!option.getText().equals("")) {
					listOfOptions.add(option.getText());
					log.info("\"" + "Found: " + option.getText() + "\"");
					extentReportLogger().log(Status.INFO, "\"" + "Found: " + option.getText() + "\"");
				}
				i++;
			}	
			}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
		return listOfOptions;	
	}
	
	/**
	 * This method verifies the presence of all the expand icon of specified options.
	 * @param  optionNames				list of options of which user wants to verify the expand icon.
	 * @param  optionsGrid				grid element contains all the options.
	 * @param  optionsRowElement		element which returns number of options.
	 * @param  optionElement			option element of which user wants to verify the expand icon.
	 * @param  ExpandIconElement		expand icon element.
	 * @return true, if expand icon of provided options are verified .
	 */
	public static boolean verifyExpandIconOfOptions(List<String> optionNames, By optionsGrid, By optionsRowElement, By optionElement, By expandIconElement) {
		boolean result = false;
		try {
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> options = optionsGridElement.findElements(optionsRowElement);
			for (WebElement option : options) {
				if (optionNames.contains(option.findElement(optionElement).getText())) {
					WebElement ExpandIcon = option.findElement(expandIconElement);
					if(!ExpandIcon.getAttribute("class").contains("hide")) {
						extentReportLogger().log(Status.PASS, "\"" + "Expand Icon is verified for: " +option.findElement(optionElement).getText()+ "\"");
						log.info("\"" + "Expand Icon is verified for: " +option.findElement(optionElement).getText()+ "\"");
						result = true;
					}else {
						extentReportLogger().log(Status.PASS, "\"" + "Expand Icon is not verified." + "\"");
						log.info("\"" + "Expand Icon is not verified." + "\"");
					}
				}
			}	
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
		return result;
	}
	
	/**
	 * This method verifies the presence of collapse icon of specified option.
	 * @param  optionName				option name of which user wants to verify the collapse icon.
	 * @param  optionsGrid				grid element contains all the options.
	 * @param  optionsRowElement		element which returns number of options.
	 * @param  optionElement			option element of which user wants to verify the collapsed icon.
	 * @param  CollapseIconElement		collapsed icon element.
	 * @return true, if collapse icon of provided options are verified .
	 */
	public static boolean verifyCollapseIconOfOption(String optionName, By optionsGrid, By optionsRowElement, By optionElement, By collapseIconElement) {
		boolean result = false;
		try {
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> options = optionsGridElement.findElements(optionsRowElement);
			for (WebElement option : options) {
				if (option.findElement(optionElement).getText().equals(optionName)) {
					WebElement CollapseIcon = option.findElement(collapseIconElement);
					if(!CollapseIcon.getAttribute("class").contains("hide")) {
						extentReportLogger().log(Status.PASS, "\"" + "Collapse Icon is verified for: " +optionName+ "\"");
						log.info("\"" + "Collapse Icon is verified for: " +optionName+ "\"");
						result = true;
						break;
					}else {
						extentReportLogger().log(Status.PASS, "\"" + "Collapse Icon is not verified." + "\"");
						log.info("\"" + "Collapse Icon is not verified." + "\"");
					}
				}
			}	
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
		return result;
	}
	
	/**
	 * This method clicks on expand icon of specified option.
	 * @param  optionName				option name of which user wants to click on expand icon.
	 * @param  optionsGrid				grid element contains all the options.
	 * @param  optionsRowElement		element which returns number of options.
	 * @param  optionElement			option element of which user wants to click on expand icon.
	 * @param  expandIconElement		expand icon element.
	 */
	public static void clickExpandIconOfOption(String optionName, By optionsGrid, By optionsRowElement, By optionElement, By expandIconElement) {
		try {
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> options = optionsGridElement.findElements(optionsRowElement);
			for (WebElement option : options) {
				if (option.findElement(optionElement).getText().equals(optionName)) {
					WebElement ExpandIcon = option.findElement(expandIconElement);
					if(!ExpandIcon.getAttribute("class").contains("hide")) {
						ExpandIcon.click();
						extentReportLogger().log(Status.PASS, "\"" + "Clicked on expand icon of: " +optionName+ "\"");
						log.info("\"" + "Clicked on expand icon of: " +optionName+ "\"");
						break;
					}else {
						extentReportLogger().log(Status.PASS, "\"" + "Expand icon not found for: " +optionName+ "\"");
						log.info("\"" + "Expand icon not found for: " +optionName+ "\"");
					}
				}
			}	
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
	}
	
	/**
	 * This method clicks on collapse icon of specified option.
	 * @param  optionName				option name of which user wants to click on collapse icon.
	 * @param  optionsGrid				grid element contains all the options.
	 * @param  optionsRowElement		element which returns number of options.
	 * @param  optionElement			option element of which user wants to click on collapse icon.
	 * @param  collapseIconElement		collapse icon element.
	 */
	public static void clickCollapseIconOfOption(String optionName, By optionsGrid, By optionsRowElement, By optionElement, By collapseIconElement) {
		try {
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> options = optionsGridElement.findElements(optionsRowElement);
			for (WebElement option : options) {
				if (option.findElement(optionElement).getText().equals(optionName)) {
					WebElement CollapseIcon = option.findElement(collapseIconElement);
					if(!CollapseIcon.getAttribute("class").contains("hide")) {
						CollapseIcon.click();
						extentReportLogger().log(Status.PASS, "\"" + "Clicked on collapse icon of: " +optionName+ "\"");
						log.info("\"" + "Clicked on collapse icon of: " +optionName+ "\"");
						break;
					}else {
						extentReportLogger().log(Status.PASS, "\"" + "Collapse icon not found for: " +optionName+ "\"");
						log.info("\"" + "Collapse icon not found for: " +optionName+ "\"");
					}
				}
			}	
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
	}
	
	/**
	 * This method get the list of items present in specified option.
	 * @param  optionName				option name of which user wants get the list of items.
	 * @param  optionsGrid				grid element contains all the options.
	 * @param  optionsRowElement		element which returns number of options.
	 * @param  optionElement			option element of which user wants to get the list of items.
	 * @return List<String>, list of all the items present in specified option.
	 */
	public static List<String> getListOfItemsPresentInSpecifiedOption(String optionName, By optionsGrid, By optionsRowElement, By optionElement, By itemElement) {
		List<String> listOfOptions;
		try {
			listOfOptions = new ArrayList<String>();
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> rows = optionsGridElement.findElements(optionsRowElement);
			MKeywords.sleep(1000);
			for (WebElement row : rows) {
				WebElement option = row.findElement(optionElement);
				if(option.getText().equals(optionName)) {
					List<WebElement> items = row.findElements(itemElement);
					MKeywords.sleep(1000);
					for (WebElement item : items) {
						if(!item.getText().equals("")) {
							listOfOptions.add(item.getText());
							extentReportLogger().log(Status.INFO, "\"" + item.getText() + " is found in: " +optionName+ "\"");
							log.info("\"" + item.getText() + " is found in: " +optionName+ "\"");
						}
					}
				}
			}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
		return listOfOptions;	
	}

	/**
	 * This method get the text of specified option from list of options.
	 * @param  optionName				option name of which user wants get the text.
	 * @param  optionsGrid				grid element contains all the options.
	 * @param  optionsRowElement		element which returns number of options.
	 * @param  optionElement			option element of which user wants to get the text.
	 * @param  textElement				text element.
	 * @return String, 					text of specified option.
	 */
	public static String getTextUnderSpecifiedOptionFormOptionsList(String optionName, By optionsGrid, By optionsRowElement, By optionElement, By textElement) {
		String text = null;
		try {
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> rows = optionsGridElement.findElements(optionsRowElement);
			for (WebElement row : rows) {
				WebElement option = row.findElement(optionElement);
				if(option.getText().equals(optionName)) {
					text =  row.findElement(textElement).getText();
					extentReportLogger().log(Status.INFO, "\"" + text + " is found in: " +optionName+ "\"");
					log.info("\"" + text + " is found in: " +optionName+ "\"");
					break;
				}
			}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
		return text;
	}
	
	/**
	 * This method verify the presence of warning icon of specified option from list of options.
	 * @param  optionName				option name on which warning icon is present.
	 * @param  optionsGrid				grid element contains all the options.
	 * @param  optionsRowElement		element which returns number of options.
	 * @param  optionElement			option element on which warning icon is present.
	 * @param  elementToVerify			element that user want to verify.
	 * @return true, 					if element is present.
	 */
	public static boolean verifyElementPresenceInSpecifiedOptionFormOptionsList(String optionName, By optionsGrid, By optionsRowElement, By optionElement, By elementToVerify) {
		boolean isElementPresent = false;
		try {
			wait = new WebDriverWait(instance.getDriver(), 5);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> rows = optionsGridElement.findElements(optionsRowElement);
			
			for (WebElement row : rows) {
				Actions actionBuilder=new Actions(instance.getDriver());
				actionBuilder.moveToElement(row).build().perform();
				WebElement option = row.findElement(optionElement);
				if(option.getText().trim().equals(optionName.trim())) {
					WebElement element = row.findElement(elementToVerify);
					isElementPresent = element.isDisplayed();
					extentReportLogger().log(Status.INFO, "\"" +option.getText()+ "\"" + " is present.");
					log.info("\"" +option.getText()+ "\"" + " is present.");
					break;
				}
			}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
		}
		return isElementPresent;
	}
	
	/**
	 * This method get the attribute of specified option from list of options.
	 * @param  optionName				option name on corresponding to which attribute to find.
	 * @param  optionsGrid				grid element contains all the options.
	 * @param  optionsRowElement		element which returns number of options.
	 * @param  optionNameElement		option name element.
	 * @param  elementToVerify			element that user want to verify.
	 * @param  attribute				attribute name..
	 * @return 		attribute value
	 */
	public static String getAttributeOfSpecifiedOptionFormOptionsList(String optionName, By optionsGrid, By optionsRowElement, By optionNameElement, By elementToVerify, String attribute) {
		String attributeValue = null;
		try {
			wait = new WebDriverWait(instance.getDriver(), 5);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> rows = optionsGridElement.findElements(optionsRowElement);	
			for (WebElement row : rows) {
				Actions actionBuilder=new Actions(instance.getDriver());
				actionBuilder.moveToElement(row).build().perform();
				WebElement option = row.findElement(optionNameElement);
				if(option.getText().trim().equals(optionName.trim())) {
					WebElement element = row.findElement(elementToVerify);
					attributeValue = element.getAttribute(attribute);
					extentReportLogger().log(Status.INFO, "Attribute value: "+ "\"" +attributeValue+ "\"");
					log.info("Attribute value: "+ "\"" +attributeValue+ "\"");
					break;
				}
			}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
		}
		return attributeValue;
	}
	
	/**
	 * This method get the text of specified option from list of options.
	 * @param  optionName				option name on corresponding to which attribute to find.
	 * @param  optionsGrid				grid element contains all the options.
	 * @param  optionsRowElement		element which returns number of options.
	 * @param  optionNameElement		option name element.
	 * @param  elementToVerify			element that user want to verify.
	 * @param  attribute				attribute name..
	 * @return 		attribute value
	 */
	public static String getTextOfSpecifiedOptionFormOptionsList(String optionName, By optionsGrid, By optionsRowElement, By optionNameElement, By optionTextElement) {
		String value = null;
		try {
			wait = new WebDriverWait(instance.getDriver(), 5);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> rows = optionsGridElement.findElements(optionsRowElement);	
			for (WebElement row : rows) {
				Actions actionBuilder=new Actions(instance.getDriver());
				actionBuilder.moveToElement(row).build().perform();
				WebElement option = row.findElement(optionNameElement);
				if(option.getText().trim().equals(optionName.trim())) {
					WebElement element = row.findElement(optionTextElement);
					value = element.getText();
					extentReportLogger().log(Status.INFO, "Value: "+ "\"" +value+ "\"");
					log.info("Value: "+ "\"" +value+ "\"");
					break;
				}
			}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
		}
		return value;
	}
	
	
	/**
	 * This class contains the constant variables that defines the position of any element on page.
	 */
	public static final class ElementPosition {
	    public static final int ABOVE = 1;
	    public static final int BELOW = -1;
	    public static final int EQUAL = 0;
	}
	
	/**
	 * This method compares if position of one element is above the another element.
	 * @param  option1			text of option1.
	 * @param  option2			text of option2.
	 * @param  optionsGrid		grid element contains all the options.
	 * @param  optionElement	option element of which position is required to find.
	 * @return Integer, 	    1= if option1 is above option2, -1 = if option1 is below option2, 0 = if option1 and option2 is at the same line.
	 */
	public static int comparePositionOfTwoElementsOnPage(String option1, String option2, By optionsGrid, By optionElement) {
		int elementPosition;
		int option1Position = 0, option2Position = 0;
		try {
			wait = new WebDriverWait(instance.getDriver(), 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> options = optionsGridElement.findElements(optionElement);
			for (WebElement option : options) {
				if(option.getText().equals(option1)) {
					option1Position = option.getLocation().getY();
				}else if(option.getText().equals(option2)) {
					option2Position = option.getLocation().getY();
				}
			}
			if(option1Position < option2Position) {
				elementPosition = ElementPosition.ABOVE;
			}else if(option1Position > option2Position) {
				elementPosition = ElementPosition.BELOW;
			}else {
				elementPosition = ElementPosition.EQUAL;
			}			
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
		return elementPosition;
	}
	
	/**
	 * This method returns the first option's attribute value
	 * .
	 * @param  optionsGrid				grid element contains all the options.
	 * @param  optionElement		    option element that user wants to select.
	 * @param attribute					returns the attribute value after click
	 */
	public static String getAttributeOfFirstOptionFromAvailableItems(By optionsGrid, By optionElement, String attribute) {
		String attrValue = null;
        try {
               wait = new WebDriverWait(instance.getDriver(), 30);
               wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
               WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
               List<WebElement> options = optionsGridElement.findElements(optionElement);
               for (WebElement option : options) {
                  attrValue=option.getAttribute(attribute);
                  extentReportLogger().log(Status.PASS, "\"" + option + " is selected." + "\"");
                  log.info("\"" + option + " is selected." + "\"");
                  break;
               } 
               return attrValue;
        } catch (NoSuchElementException e) {
               extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
               log.error("No Element Found" + e);
               throw(e);
        } catch (TimeoutException e) {
               extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
               log.error("Timeout. Element not found." + e);
               throw(e);
        } catch (Exception e) {
               extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
               log.error("Exception: " + e);
               throw(e);
        }
  }
	
	/**
	 * This method selects the First option from the provided list.
	 * .
	 * @param  optionsGrid				grid element contains all the options.
	 * @param  optionElement		    option element that user wants to select.
	 */
	public static void selectFirstOptionFromAvailableItems(By optionsGrid, By optionElement) {
        try {
               wait = new WebDriverWait(instance.getDriver(), 30);
               wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
               WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
               List<WebElement> options = optionsGridElement.findElements(optionElement);
               for (WebElement option : options) {
            	  JavaScriptKeywords.scrollElementIntoView(option, false);
                  option.click();
                  extentReportLogger().log(Status.PASS, "\"" + elementPath + " is selected." + "\"");
                  log.info("\"" + elementPath + " is selected." + "\"");
                  break;
               } 
        } catch (NoSuchElementException e) {
               extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
               log.error("No Element Found" + e);
               throw(e);
        } catch (TimeoutException e) {
               extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
               log.error("Timeout. Element not found." + e);
               throw(e);
        } catch (Exception e) {
               extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
               log.error("Exception: " + e);
               throw(e);
        }
  }
	
	 /** This method selects the specified radio button.
	 * @param  radioButtonValue			radio button value that user wants to select.
	 * @param  optionsGrid				grid element contains all the options.
	 * @param  optionsElement			radio button options.
	 * @param  radioButtonValueElement	radio button value element.
	 * @param  radioButtonElement		radio button icon element.
	 * @return String, 	    			value of selected radio button.
	 */
	public static void selectSpecifiedRadioButton(String radioButtonValue, By optionsGrid, By optionsElement, By radioButtonValueElement, By radioButtonElement) {
		try {
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> options = optionsGridElement.findElements(optionsElement);
			for (WebElement option : options) {
				WebElement optionValue = option.findElement(radioButtonValueElement);
				if(optionValue.getText().equals(radioButtonValue)) {
					WebElement radioButton = option.findElement(radioButtonElement);
					if(!radioButton.getAttribute("class").contains("svg-common-universal-btn-selectedradio")) {
						radioButton.click();
						extentReportLogger().log(Status.INFO, "\""  + optionValue.getText() + "\"" + " is selected.");
						log.info("\""  + optionValue.getText() + "\"" + " is selected.");
						break;
					}	
				}
			}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
	}
	
	/**
	 * This method get the value of selected radio button.
	 * 
	 * @param  optionsGrid				grid element contains all the options.
	 * @param  optionsElement			radio button options.
	 * @param  radioButtonValueElement	radio button value element.
	 * @param  radioButtonElement		radio button icon element.
	 * @return String, 	    			value of selected radio button.
	 * @throws Exception 
	 */
	public static List<String> getSelectedRadioButtonValue(By optionsGrid, By optionsElement, By radioButtonValueElement, By radioButtonElement) {
		List<String> selectedValues = null;
		try {
			selectedValues = new ArrayList<String>();
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> options = optionsGridElement.findElements(optionsElement); 		
			for (WebElement option : options) {
				WebElement optionValue = option.findElement(radioButtonValueElement);
				WebElement radioButton = option.findElement(radioButtonElement);
				if(radioButton.getAttribute("class").contains("svg-common-universal-btn-selectedradio")) {
					selectedValues.add(optionValue.getText());
					extentReportLogger().log(Status.INFO, "\"" + " Selected radio button is: " + optionValue.getText() + "\"");
					log.info("\"" + " Selected radio button is: " + optionValue.getText() + "\"");
				}	
			}

			if(selectedValues.size() > 1) {
				throw new Exception("More than one values are selected.");
			}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
		}
		return selectedValues;	
	}
	
	/**
	 * This method clicks on the provided link for option row
	 * @param optionsGrid						Grid containing the elements
	 * @param optionToSelect					Name of the row needs to be clicked
	 * @param optionElement						Element container for the row 
	 * @param optionsNameElement				Element for the name in row
	 * @param optionsLinkElement				Element for link to click in the row
	 * @param attrValue							Attribute value which contains the text for the comparison
	 */
	public static void selectOptionsButtonFromAvailableItems(By optionsGrid, String optionToSelect, By optionElement, By optionsNameElement, By optionsLinkElement, String attrValue) {
        try {
               wait = new WebDriverWait(instance.getDriver(), 20);
               wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
               WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
               List<WebElement> options = optionsGridElement.findElements(optionElement);
               for (WebElement option : options) {
                  WebElement name = option.findElement(optionsNameElement);
                  WebElement Link = option.findElement(optionsLinkElement);
                      if (optionToSelect.equals(name.getAttribute(attrValue))) {
                               Link.click();
                          extentReportLogger().log(Status.PASS, "\"" + optionToSelect + " is selected." + "\"");
                          log.info("\"" + optionToSelect + " is selected." + "\"");
                          break;
                      }
               }      
        } catch (NoSuchElementException e) {
               extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
               log.error("No Element Found" + e);
               throw(e);
        } catch (TimeoutException e) {
               extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
               log.error("Timeout. Element not found." + e);
               throw(e);
        } catch (Exception e) {
               extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
               log.error("Exception: " + e);
               throw(e);
        }
  }
	
	/**
	 * This method clicks on each element present in grid.
	 * 
	 * @param  optionsGrid				grid element contains all the options.
	 * @param  optionsElement			option to click
	 */
	public static void clickEachOptionOfGrid(By optionsGrid, By optionsElement) {
		try {
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> options = optionsGridElement.findElements(optionsElement);
			for (WebElement option : options) {
				Actions actionBuilder=new Actions(instance.getDriver());
				actionBuilder.moveToElement(option).click().build().perform();
				int attempts = 0;
				while (attempts < 5) {
					try {
						option.click();
						break;
					} catch (StaleElementReferenceException e) {
						extentReportLogger().log(Status.INFO, "\"" + "Stale Exception retrying... " + "\"" + elementPath + "\"");
						log.info("Stale Exception retrying..." + e);
					}
					attempts++;
				}			
			}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}		
	}
	
	/**
	 * This method clicks on each element present in grid.
	 * 
	 * @param  textToMatch				text user want to match
	 * @param  optionsGrid				grid element contains all the options.
	 * @param  optionName				name of element corresponding to which user wants to click on element
	 * @param  optionsElement			option to click
	 */
	public static void clickSpecifiedOptionOfGrid(String textToMatch, By optionsGrid, By optionName, By optionsElement) {
		try {
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> options = optionsGridElement.findElements(optionName);
			for (WebElement option : options) {
				Actions actionBuilder=new Actions(instance.getDriver());
				actionBuilder.moveToElement(option).click().build().perform();
				if(option.getText().contains(textToMatch)) {
					WebElement ele = option.findElement(optionsElement);
					ele.click();
					break;
				}
			}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}		
	}
	
	/**
	 * This method selects multiple options from available options by clicking on corresponding element of specified option.
	 * .
	 * @param optionsToSelect 			list of options to select.
	 * @param  optionsGrid    			options grid.                                          
	 * @param  optionRowElement      	option row element.                               
	 * @param  optionNameElement      	option name element.                       
	 * @param  elementToSelect 			element to select.
	 * 
	 */
	public static void selectMultipleOptionsFromAvailableOptions(List<String> optionsToSelect, By optionsGrid, By optionRowElement, By optionNameElement, By elementToSelect) {
		try {
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> options = optionsGridElement.findElements(optionRowElement);
			int optionsListSize = optionsToSelect.size();
			for (WebElement option : options) {
				WebElement name = option.findElement(optionNameElement);
				Actions actionBuilder=new Actions(instance.getDriver());
				actionBuilder.moveToElement(name).build().perform();			
				if (optionsToSelect.contains(name.getText())) {
					WebElement link = option.findElement(elementToSelect);
					JavaScriptKeywords.scrollElementIntoView(option, true);
					JavaScriptKeywords.scrollElementIntoView(option, false);
					link.click();
					extentReportLogger().log(Status.PASS, "\"" + elementPath + " is clicked for: " + name.getText() + "\"");
					log.info("\"" + elementPath + " is clicked for: " + name.getText() + "\"");
					optionsListSize--;
					if(optionsListSize==0) {
						break;
					}
				}
			}      
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
	}
	
	/**
	 * This method get the list of all the available options in a page.
	 * @param  optionsGrid		grid element contains all the options.
	 * @param  optionElement	element which returns name of options.
	 * @return List<String>, list of all the available options.
	 */
	public static int getNumberOfExceptionDays(By optionsGrid, By optionElement) {
		List<String> listOfOptions;
		try {
			listOfOptions = new ArrayList<String>();
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> options = optionsGridElement.findElements(optionElement);
			MKeywords.sleep(1000);
			for (WebElement option : options) {
				Actions a=new Actions(instance.getDriver());
				a.moveToElement(option).build().perform();
				if(!option.getText().equals("")) {
					listOfOptions.add(option.getText());
					extentReportLogger().log(Status.INFO, "\"" + option.getText() + " is found." + "\"");
					log.info("\"" + option.getText() + " is found." + "\"");
				}
			}	
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
		return listOfOptions.size();	
}

	/**
	 * This method gets the hour value visible in Time clock time screen.
	 *
	 * @param time  currentTime
	 * @return,  time clock hour value
	 */
	public static String getTimeclockTime(String currentTime) {
		String hours = currentTime.split(":")[0];
		String minutes = currentTime.split(":")[1];
		int hour = 0;
		if(Integer.parseInt(minutes) > 30) {
			hour=Integer.parseInt(hours)+1;
		}else {
			hour = Integer.parseInt(hours);
		}
		extentReportLogger().log(Status.INFO, "\"" + "Hour value: " +hour+ "\"");
		log.info("\"" + "Hour value: " +hour+ "\"");
		return Integer.toString(hour);
	}
	
	/**
	 * This method clicks on the options from the provided list and then clicks on the provided option in the pop up
	 * @param optionsGrid						Grid containing the elements
	 * @param optionsToSelect					List of the row needs to be clicked
	 * @param optionElement						Element container for the row 
	 * @param optionsNameElement				Element for the name in row
	 * @param optionsLinkElement				Element for link to click in the row
	 * @param attrValue							Attribute value which contains the text for the comparison
	 * @param optionForPopUp					Element for the pop up to select
	 */
	public static void selectMultipleOptionsFromAvailableItemsWithPopUpAction(By optionsGrid, List<String> optionsToSelect, By optionElement, By optionsNameElement, By optionsLinkElement, 
			String attrValue, By optionForPopUp) {
		try {
			for (String optionToSelect : optionsToSelect) {
				selectOptionsButtonFromAvailableItems(optionsGrid, optionToSelect, optionElement, optionsNameElement, optionsLinkElement, attrValue);
				TwitterKeywords.waitForSpinnerInvisibility();
				if(SeKeywords.isElementDisplayed(optionForPopUp)) {
					SeKeywords.click(optionForPopUp);
				}
				TwitterKeywords.waitForSpinnerInvisibility();
			}
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
	}

	/**
	 * This method sets the low end trim value.
	 *
	 * @param currentLowEndTrimValue   current low end trim value
	 * @param lowEndTrimValueToSelect  low end trim value to select
	 * @param dimmableLightValues	   dimmable light text box element
	 */
	public static void setLowEndTrimValue(String currentLowEndTrimValue, String lowEndTrimValueToSelect, By dimmableLightValues) {

		WebElement dimmableLightsElement = instance.getDriver().findElement(dimmableLightValues);
		Actions actionBuilder = new Actions(instance.getDriver());

		int offset = 0;
		int currentLetValue = Integer.parseInt(currentLowEndTrimValue);
		int letValueToSelect = Integer.parseInt(lowEndTrimValueToSelect);
		
		//if low end trim value to select is greater than current value then for each roll up move 40 pixels
		if(letValueToSelect > currentLetValue) {
			offset = letValueToSelect-currentLetValue;
			while(offset > 0) {
				actionBuilder.clickAndHold(dimmableLightsElement).moveByOffset(0, 40).build().perform();
				offset= offset-1;
				actionBuilder.release().click();
			}
		//if low end trim value to select is greater than current value then for each roll down move -40 pixels
		}else if (letValueToSelect < currentLetValue){
			offset = letValueToSelect-currentLetValue;
			while(offset < 0) {
				actionBuilder.clickAndHold(dimmableLightsElement).moveByOffset(0, -40).build().perform();
				offset= offset+1;
				actionBuilder.release().click();
			}
		}else {
			extentReportLogger().log(Status.INFO, "value to select is same as already selected value.");
			log.info("value to select is same as already selected value.");
		}
		actionBuilder.release().click().perform();
	}
	
	/**
	 * This method gets the attribute of specified option.
	 * .
	 * @param optionName	 			option name to get attribute of.
	 * @param  optionsGrid    			options grid.                                          
	 * @param  optionRowElement      	option row element.                               
	 * @param  optionNameElement      	option name element.                       
	 * @param  elementToSelect 			element to select.
	 * @param  attribute	 			attribute name.
	 * 
	 */
	public static String getAttributeOfSpecifiedOption(String optionName, By optionsGrid, By optionRowElement, By optionNameElement, By elementToSelect, String attribute) {
		String attrValue = null;
		try {
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionsGrid));
			WebElement optionsGridElement = instance.getDriver().findElement(optionsGrid);
			List<WebElement> optionRows = optionsGridElement.findElements(optionRowElement);
			for (WebElement optionRow : optionRows) {
				Actions actionBuilder=new Actions(instance.getDriver());
				actionBuilder.moveToElement(optionRow).build().perform();
				WebElement name = optionRow.findElement(optionNameElement);
				if (optionName.equalsIgnoreCase(name.getText())) {
					attrValue = instance.getDriver().findElement(elementToSelect).getAttribute(attribute);
					extentReportLogger().log(Status.PASS, "\"" + "Attribute " + "\"" + elementPath + "\"" + " value is: " + "\"" + attrValue + "\"");
					log.info("Attribute " + "\"" + elementPath + "\"" + " value is: " + "\"" + attrValue + "\"");
					break;
				}
			}      
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Element Found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Exception: " + "\"" + elementPath + "\"");
			log.error("Exception: " + e);
			throw(e);
		}
		return attrValue;
	}

	/**
	 * This method gets the wheel values.
	 *
	 * @param optionGrid 		 option grid
	 * @param optionValues 	     option values
	 * @return List<String>		 wheel values
	 */
	public static List<String> getWheelValues(By optionGrid, By optionValues) {
		List<String> runTimeOptionList = null;
		try {
			runTimeOptionList = new ArrayList<String>();
			wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(optionGrid));
			WebElement grid = instance.getDriver().findElement(optionGrid);
			List<WebElement> rows = grid.findElements(optionValues);
			for(WebElement row : rows) {
				List<WebElement> options = row.findElements(By.tagName("li"));
				for (WebElement option : options) {
					if(!runTimeOptionList.contains(option.getAttribute("innerHTML"))) {
						runTimeOptionList.add(option.getAttribute("innerHTML"));
					}
				}
			}      
			extentReportLogger().log(Status.PASS, "\"" + " Values retrieved: " + elementPath + "\"");
			log.info("\"" + " Values retrieved: " + elementPath + "\"");
		} catch (NoSuchElementException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "getTextElement not found: " + "\"" + elementPath + "\"");
			log.error("getTextElement not found" + e);
			throw(e);
		} catch (TimeoutException e) {
			extentReportLogger().log(Status.FAIL, "\"" + "TimeOut. " + "\"" + elementPath + "\"" + " not found. " + "\"");
			log.error("Timeout. Element not found." + e);
			throw(e);
		} catch (Exception e) {
			extentReportLogger().log(Status.FAIL, "\"" + "Element not found: " + "\"" + elementPath + "\"");
			log.error("No Such Element Found" + e);
			throw(e);
		}
		return runTimeOptionList;
	}
}
