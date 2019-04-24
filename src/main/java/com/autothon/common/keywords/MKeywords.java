package com.autothon.common.keywords;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.autothon.core.Config;
import com.autothon.core.DriverFactory;
import com.autothon.core.TestBase;
import com.aventstack.extentreports.Status;

/**
 * This class contains the methods that read the element locators and their values
 * from OR.xml file.
 */
public class MKeywords extends TestBase {
	
	private static int invalidLinksCount;
	private static int invalidImageCount;
	static	DriverFactory instance  = DriverFactory.getInstance();
	/**
	 * Get page the element.
	 *
	 * @param locatorValue	value of locator.
	 * @param locatorType	type of locator.
	 * @return By			returns By data type.
	 * @throws Exception	on error.
	 * @see	   Exception
	 */
	public static By GetElement(String locatorValue, String locatorType) throws Exception {
		switch (locatorType.toLowerCase()) {
		case "id":
			return By.id(locatorValue);
		case "name":
			return By.name(locatorValue);
		case "classname":
		case "class":
			return By.className(locatorValue);
		case "tagname":
			return By.tagName(locatorValue);
		case "linktext":
			return By.linkText(locatorValue);
		case "partiallinktext":
			return By.partialLinkText(locatorValue);
		case "cssselector":
			return By.cssSelector(locatorValue);
		case "xpath":
			return By.xpath(locatorValue);
		default:
			throw new Exception("DOM FINDER : did not find the correct dom finder type in the file for locator value: "
					+ locatorValue);
		}
	}

	/**
	 * Finds the page element.
	 *
	 * @param PageName		page name tag in OR.xml file.
	 * @param ObjectName	name of element.
	 * @return By			returns By data type.
	 */
	public static By findElement(String pageName, String objectName) {
		By locator = null;
		try {
			elementPath = objectName;
			String xmlPath = Config.locatorsFile;
			List<String> listLocator = dataSourceOperations.GetXmlValue(objectName, xmlPath, pageName);
			if(!listLocator.isEmpty())
				locator = GetElement(listLocator.get(0), listLocator.get(1));
			else {
				log.error("NameOfElement or SectionName is given wrong in ObjectRepository.xml file OR PageName in Page Object Class is wrong");
			}
		} catch (FileNotFoundException ex) {
			log.error("Can not find ObjectRepository.XML");
		}catch (Exception e) {
			log.error("Did not find the correct dom finder type in the file for locator value. Please check locator type also");
		} 
		return locator;
	}

	/**
	 * Pause the execution of test for defined time.
	 *
	 * @param millsecs	time in milliseconds.
	 */
	public static void sleep(int millsecs) {
		try {
			Thread.sleep(millsecs);
		} catch (Exception ex) {
			extentReportLogger().log(Status.FAIL, "exception Occured while waiting " + ex);
		}
	}

	/**
	 * Runs auto IT script.
	 *
	 * @param fileName		name of the file.
	 * @throws IOException	on input error.
	 * @see	   IOException
	 */
	public static void runAutoITScript(String fileName) {
		try {
			Runtime.getRuntime().exec(Config.autoITPath + fileName);
		}catch (IOException ex) {
			extentReportLogger().log(Status.FAIL, "Exception Occured on running autoIT script: " + ex);
			log.error("Exception Occured on running autoIT script: " + ex);
		}catch (Exception ex) {
			extentReportLogger().log(Status.FAIL, "Exception Occured on running autoIT script: " + ex);
			log.error("Exception Occured on running autoIT script: " + ex);
		}
	}

	public static void validateInvalidLinks1() {

		try {
			invalidLinksCount = 0;
			List<WebElement> anchorTagsList = instance.getDriver().findElements(By.tagName("a"));
			/*System.out.println("Total no. of links are " + anchorTagsList.size());*/
			for (WebElement anchorTagElement : anchorTagsList) {
				if (anchorTagElement != null) {
					String url = anchorTagElement.getAttribute("href");
					if (url != null && !url.contains("javascript")) {
						verifyURLStatus(url);
					} else {
						invalidLinksCount++;
					}
				}
			}

			//System.out.println("Total no. of invalid links are " + invalidLinksCount);

		} catch (Exception e) {
			e.printStackTrace();
			/*System.out.println(e.getMessage());*/
		}
	}

	public static void verifyURLStatus(String url) {

		HttpClient client = HttpClientBuilder.create().build();
		/*System.out.println("URL is " + url);*/
		HttpGet request = new HttpGet(url);
		try {
			HttpResponse response = client.execute(request);
			// verifying response code and The HttpStatus should be 200 if not,
			// increment invalid link count
			//// We can also check for 404 status code like
			// response.getStatusLine().getStatusCode() == 404
			if (response.getStatusLine().getStatusCode() != 200)
			{
				invalidLinksCount++;
			}
			else
			{
				extentReportLogger().log(Status.PASS, "\"" + "URL acessed " + "\"" + url + "\"");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void validateInvalidImages() {
		// MKeywords.Sleep(10000);
		try {
			invalidImageCount = 0;
			List<WebElement> imagesList = instance.getDriver().findElements(By.tagName("img"));
			/*System.out.println("Total no. of images are " + imagesList.size());*/
			for (WebElement imgElement : imagesList) {
				if (imgElement != null) {
					verifyImageActive(imgElement);
					//System.out.println("Image verified->" + imgElement );
				}
			}
			// System.out.println("Total no. of invalid images are " + invalidImageCount);
		} catch (Exception e) {
			e.printStackTrace();
			/*System.out.println(e.getMessage());*/
		}
	}

	public static void verifyImageActive(WebElement imgElement) {
		//MKeywords.Sleep(10000);
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(imgElement.getAttribute("src"));
			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() != 200)
			{     
				invalidImageCount++;
				extentReportLogger().log(Status.FAIL, "\"" + " Image Accessed --->" + "\"" + request.getURI() + "\"");
			}
			else
				extentReportLogger().log(Status.PASS, "\"" + " Image Accessed --->" + "\"" + request.getURI() + "\"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This will read the PDF and parse it.
	 * @param reqTextInPDF String to be matched.
	 * @return whether the required text is matched with the PDF content
	 */

	public static boolean verifyPDFContent(String reqTextInPDF, int startPage, int endPage) {

		boolean flag = false;

		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		String parsedText = null;

		try {
			URL url = new URL(instance.getDriver().getCurrentUrl());
			BufferedInputStream file = new BufferedInputStream(url.openStream());
			PDFParser parser = new PDFParser(file);

			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdfStripper.setStartPage(startPage);
			pdfStripper.setEndPage(endPage);

			pdDoc = new PDDocument(cosDoc);
			parsedText = pdfStripper.getText(pdDoc);
		} catch (MalformedURLException e2) {
			log.error("URL string could not be parsed "+e2.getMessage());
		} catch (IOException e) {
			log.error("Unable to open PDF Parser. " + e.getMessage());
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}

		log.info(" ++++++++++++++PDF Content starts+++++++++++++++++");
		log.info(parsedText);
		log.info("+++++++++++++++++ PDF content ends++++++++++++++++");

		if(parsedText.contains(reqTextInPDF)) {
			flag=true;
		}

		return flag;
	}

	/**
	 * to get the absolute path from relative path .
	 * @param relative path.
	 * @return Absolute Path
	 */
	public static String getAbsolutePath(String relativePath)
	{
		String absPath = System.getProperty("user.dir") + relativePath;	
		return absPath;
	}
}
