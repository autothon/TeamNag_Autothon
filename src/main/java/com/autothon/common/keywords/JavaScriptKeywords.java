package com.autothon.common.keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.autothon.core.DriverFactory;
import com.autothon.core.TestBase;
import com.aventstack.extentreports.Status;

/**
 * This class contains all the keywords created using Java script.
 */
public class JavaScriptKeywords extends TestBase{

	private static DriverFactory instance = DriverFactory.getInstance();

	/**
	 * This method click on the last button present on the pop up using java script.
	 */
	public static void jsClickOnPopupLastButton(){
		((JavascriptExecutor) instance.getDriver()).executeScript(" $('.modal-dialog').filter(':visible').find('.btn').last().click()");
		extentReportLogger().log(Status.PASS, "Button Clicked");
	}

	/**
	 * This method click on the first button present on the pop up using java script.
	 */
	public static void jsClickOnPopupFirstButton(){
		((JavascriptExecutor) instance.getDriver()).executeScript(" $('.modal-dialog').filter(':visible').find('.btn').first().click()");
		extentReportLogger().log(Status.PASS, "Button Clicked");
	}

	/**
	 * This method click on element using java script.
	 *
	 * @param element	element on page.
	 */
	public static void click(WebElement element){			
		JavascriptExecutor executor = (JavascriptExecutor)instance.getDriver();
		MKeywords.sleep(1000);
		executor.executeScript("arguments[0].click();", element);
		extentReportLogger().log(Status.INFO, "\"" + "clicked " + "\"" + element.getText() + "\"");
		log.info("\"" + "clicked " + "\"" + element.getText() + "\"");
	}
	
	/**
	 * This method click on element using java script.
	 *
	 * @param element	element on page.
	 */
	public static void click(By element){	
		WebElement webElement = instance.getDriver().findElement(element);
		JavascriptExecutor executor = (JavascriptExecutor)instance.getDriver();
		MKeywords.sleep(1000);
		executor.executeScript("arguments[0].click();", webElement);
		extentReportLogger().log(Status.INFO, "\"" + "clicked " + "\"" + webElement.getText() + "\"");
		log.info("\"" + "clicked " + "\"" + webElement.getText() + "\"");
	}
	
	/**
	 * This method waits for 60 seconds max for ajax call to complete.
	 */
	public static void waitForAjax() {
		int seconds = 10;
		try {
		log.info("Number of ajax connections found: " + ((JavascriptExecutor) instance.getDriver()).executeScript("return jQuery.active"));
		if((Long) ((JavascriptExecutor) instance.getDriver()).executeScript("return jQuery.active") > 0) {
			log.info("Waiting for ajax call to complete...");
	        new WebDriverWait(instance.getDriver(), seconds).until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {	
	            	Boolean isAjaxCallComplete = (Boolean) ((JavascriptExecutor) instance.getDriver()).executeScript("return jQuery.active == 0");
	            	if(isAjaxCallComplete) {
	            		log.info("\"" + "Ajax call completed." + "\"");
	            	}
					return isAjaxCallComplete;
	            }
	        });
		}
        MKeywords.sleep(2000);
		}catch(TimeoutException e) {
			log.info("\"" + "Ajax call did not complete. Waited for " + seconds + " seconds. " + e + "\"");
		}catch(Exception e) {
			log.info("\"" + "Ajax call did not complete." + e + "\"");
		}
    }
	
	/**
	 * This method scroll the page to view upward.
	 *
	 * @param WebElement	 element user wants to see into view.
	 * @param boolean		 true if element user scrolling to is below the where user currently are or
	 * 						 false if element user scrolling to is above where user currently are.
	 */
	public static void scrollElementIntoView(WebElement element, boolean scrollDown){			
		JavascriptExecutor executor = (JavascriptExecutor)instance.getDriver();
		executor.executeScript("arguments[0].scrollIntoView("+scrollDown+");", element);
		extentReportLogger().log(Status.INFO, "\"" + "scrolled to element: " + "\"" + element.getText() + "\"");
		log.info("\"" + "scrolled to element: " + "\"" + element.getText() + "\"");
	}
	
	/**
	 * This method scroll the page to view upward.
	 *
	 * @param By	 		 element user wants to see into view.
	 * @param boolean		 true if element user scrolling to is below the where user currently are or
	 * 						 false if element user scrolling to is above where user currently are.
	 */
	public static void scrollElementIntoView(By element, boolean scrollDown){	
		WebElement webElement = instance.getDriver().findElement(element);
		JavascriptExecutor executor = (JavascriptExecutor)instance.getDriver();
		executor.executeScript("arguments[0].scrollIntoView("+scrollDown+");", webElement);
		extentReportLogger().log(Status.INFO, "\"" + "scrolled to element: " + "\"" + webElement.getText() + "\"");
		log.info("\"" + "scrolled to element: " + "\"" + webElement.getText() + "\"");
	}

	/**
	 * This method scroll the page to view upward.
	 *
	 * @param element	 the element.
	 */
	public static void scrollPageUp(){			
		JavascriptExecutor executor = (JavascriptExecutor)instance.getDriver();
		executor.executeScript("scroll(0, -250);");
	}

	/**
	 * This method scroll the page to view downward.
	 *
	 * @param element	 the element.
	 */
	public static void scrollPageDown(){			
		JavascriptExecutor executor = (JavascriptExecutor)instance.getDriver();
		executor.executeScript("scroll(0, 250);");
	}

	/**
	 * This method gets the text of element by attribute using JS.
	 *
	 * @param attrName 		the name of attribute
	 * @param attrValue 	the value of attribute
	 * @return String 		text of element found
	 */
	public static String getTextByAttributeUsingJS(String attrName, String attrValue){
		Object obj = ((JavascriptExecutor) instance.getDriver())
				.executeScript("return " + "document.querySelectorAll('["+ attrName +"=\""+attrValue+"\"]')[0].value");
		return obj.toString();
	}

	/**
	 * Get the text by element Id using JS.
	 *
	 * @param controlId the name of control
	 * @return String 	text of element found
	 */
	public static String getTextByElementIdUsingJS(String controlId){
		Object obj = ((JavascriptExecutor) instance.getDriver())
				.executeScript("return " + "document.getElementById(\"" + controlId + "\").value");
		extentReportLogger().log(Status.INFO, "\"" + "Value: " +obj.toString()+ "\"");
		log.info("\"" + "Value: " +obj.toString()+ "\"");
		return obj.toString();
	}

	/**
	 * Get the text for dropdown by attribute using JS.
	 *
	 * @param key 		the name of attribute
	 * @param value 	the value of attribute
	 * @return String 	text of element found
	 */
	public static String getTextForDropDownByAttributeUsingJS(String key, String value){

		String js = "function getSelectedText(elementId) {" + 
				"    var elt = document.querySelectorAll('["+ key +"=\""+value+"\"]')[0];" + 				
				"    if (elt.selectedIndex == -1)" + 
				"        return null;" + 
				"    return elt.options[elt.selectedIndex].text;" + 
				"}" + 
				"return getSelectedText('" + value + "');";

		Object obj = ((JavascriptExecutor) instance.getDriver())
				.executeScript(js);

		return obj.toString();
	}
}
