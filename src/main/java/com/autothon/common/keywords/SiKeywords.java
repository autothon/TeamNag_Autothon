package com.autothon.common.keywords;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.autothon.core.Config;
import com.autothon.core.TestBase;
import com.aventstack.extentreports.Status;

import org.sikuli.script.Button;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;
import org.sikuli.script.Pattern;
import org.sikuli.basics.Settings;

public class SiKeywords extends TestBase{

	private static WebDriverWait wait;

	private static String SikuliImagePath = Config.SikuliImagePath;
	
	/**
	 * Click on a particular image.
	 *
	 * @param Path of the image.
	 */
	public static void click(String imageName)
	{	  
		try
		{
			if(imageName != null && !SikuliImagePath.isEmpty())
			{
			String imagePath = SikuliImagePath + "//" + imageName;
			Pattern image = new Pattern(imagePath);
			Screen screen=new Screen();
			screen.find(image);
			screen.click(image);
			log.info(imageName + " image is clicked");
			extentReportLogger().log(Status.PASS, "\"" + "Clicked the image " + "\"" + imageName + "\"");
			}
			else
			log.error("ImageName is not mentioned in test method");
		}catch(FindFailed ex){
			log.error("Either file is not available on the path given or file path is wrong " + ex);
		}

	}


	/**
	 * Capture a screenshot and save the file on a particular path.
	 *
	 * @param pathToSave path to save the image.
	 * @param fileName filename for the image file.
	 */
	public static void captureScreen(String fileName)
	{	  
		String absPath = null;
		try
		{
			if(SikuliImagePath != null && !SikuliImagePath.isEmpty())
			{
				Screen screen=new Screen();
				ScreenImage img = screen.capture();
				if(fileName != null && !fileName.isEmpty())
				{
					absPath = MKeywords.getAbsolutePath(SikuliImagePath);	
					img.save(absPath,fileName);
					log.info(fileName + " is saved at a location " + absPath);
				}
				else
					log.info("default file name is given to image and saved at " + SikuliImagePath);
			}

		}
		catch(Exception ex){
			log.error("Failed to read data from sikuli file" + ex);
		}

	}

	/**
	 * Search a text on a screen and click on it.
	 *
	 * @param inputText  Text to be searched.
	 * @param event either singleclick or doubleclick
	 */

	public static void searchAndClickText(String inputText, String event )
	{

		Settings.OcrTextRead = true; // to switch on the Region.text() function
		Settings.OcrTextSearch = true; // to switch on finding text with find("some text")
		Screen screen=new Screen();
		try {


			if(inputText != null && !inputText.isEmpty())
			{
				Match str = screen.findText(inputText);
				if(event.equalsIgnoreCase("doubleclick"))
				{
					screen.doubleClick();
					log.info("Double click has been performed on text " + inputText);
					extentReportLogger().log(Status.PASS, "\"" + "DoubleClicked the text " + "\"" + inputText + "\"");
				}
				else if (event.equalsIgnoreCase("singleclick"))
				{
					screen.click(str);
					log.info("Single click has been performed on text " + inputText);
					extentReportLogger().log(Status.PASS, "\"" + "SingleClicked the text " + "\"" + inputText + "\"");
				}
				else
					log.error("Either action is not mentioned or input action is wrong. Action should be either single click or double click");
			}
			else
				log.error("Text is not mentioned which need to be searched and clicked");

		} catch (FindFailed ex) {
			// TODO Auto-generated catch block
			log.error("Some error occured in identfying text on UI" + ex);
		}
	}



	/**
	 * Double click on a particular image.
	 *
	 * @param Path of the image.
	 */
	public static void doubleClick(String imageName)
	{	  
		try
		{
			String imagePath = SikuliImagePath + "//" + imageName;
			if(imagePath != null && !imagePath.isEmpty())
			{
			Pattern image = new Pattern(imagePath);
			Screen screen=new Screen();
			screen.find(image);
			screen.doubleClick(image);
			extentReportLogger().log(Status.PASS, "\"" + "Double clicked the image " + "\"" + imageName + "\"");
		}
			else
				log.error("Either Imagepath is not given or image is not present on the given path" + imagePath);
		}
		catch(Exception ex){
			log.error("Some error occured in identfying image on UI" + ex);
		}

	}

	/**
	 * Enter text in a text box identified by a image.
	 *
	 * @param imagePath Path of the image.
	 * @param Text text which need to be entered
	 */
	public static void enterText(String imageName,String text)
	{	  
		try
		{
			String imagePath = SikuliImagePath + "//" + imageName;
			if(imagePath != null && !imagePath.isEmpty())
			{
			Pattern image = new Pattern(imagePath);
			Screen screen=new Screen();
			screen.find(image);
			screen.type(image, text);
			log.info(text + " text is entered in the object " + imageName);
			extentReportLogger().log(Status.PASS, "\"" + "Entered Text " + text + " in an object identified by the image " + "\"" + imageName + "\"");
		
			}
			else
				log.error("Either Imagepath is not given or image is not present on the given path" + imagePath);
		}
		catch(Exception ex){
			log.error("Some error occured in identfying image on UI" + ex);
		}

	}

	/**
	 * To check whether an image is present on the UI or not.
	 *
	 * @param imagePath path of the image.
	 */

	public static boolean isImagePresent(String imageName)
	{	
		boolean presence = false;  
		try
		{	
			String imagePath = SikuliImagePath + "//" + imageName;
			if(imagePath != null && !imagePath.isEmpty())
			{
				Pattern image = new Pattern(imagePath);
				Screen screen=new Screen();
				Match img= screen.exists(image);
				if(img != null)
				{
					presence= true;
					log.info(imageName + " image is present on UI");
					extentReportLogger().log(Status.PASS, "\"" + imageName + " image is present on UI " + "\"");
				}
				else
				{
					presence= false;
					log.info(imageName + " image is not present on UI");
					extentReportLogger().log(Status.FAIL, "\"" + imageName + " image is not present on UI " + "\"");
				} 
			}
			else
				log.error("Either Imagepath is not given or image is not present on the given path" + imagePath);

		}
		catch(Exception ex){
			log.error("Some error occured in identfying image on UI" + ex);
		}
		return presence;

	}

	/**
	 * Move the screen either upward or downward as per mouse wheel movement.
	 *
	 * @param direction either up or down.
	 * @param stepCount No. of steps to move
	 */

	public static void screenScrollMouseWheel(String direction, int stepCount)
	{
		try{
			Screen screen=new Screen();
			if(direction.equalsIgnoreCase("up"))
			{screen.wheel(Button.WHEEL_UP,stepCount);
			log.info("Screen is moved up for a step count " + stepCount);}
			else if(direction.equalsIgnoreCase("down"))
			{screen.wheel(Button.WHEEL_DOWN,stepCount);
			log.info("Screen is moved down for a step count " + stepCount);}
			else
				log.error("Entered direction is wrong");
		}
		catch(Exception ex){
			log.error("Failed to read data from sikuli file" + ex);
		}
	}

	/**
	 * To Capture text from an image and verify it with input.
	 *
	 * @param filePath imageURL.
	 * @return true if text is found in the image
	 */
	public static boolean captureTextFromImage(String filePath, String text)
	{
		String parsedText = null;
		boolean flag = false;
		try
		{
			Settings.OcrTextRead = true; // to switch on the Region.text() function
			Settings.OcrTextSearch = true; // to switch on finding text with find("some text")
			Screen screen=new Screen();
			parsedText = screen.find(filePath).text();
			log.info("following text is extracted from the image" + parsedText);
			if(parsedText.contains(text)) 
			{
				flag=true;
			}
		}catch(FindFailed ex)
		{
			log.error("Either file is not available on the path given or file path is wrong " + ex);
		}

		return flag;

	}
}