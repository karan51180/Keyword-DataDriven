package com.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.FileUtils;
//import org.apache.commons.*;
import org.openqa.selenium.Alert;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;// check desired capabilities
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;// check
import org.openqa.selenium.remote.DesiredCapabilities;// check
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

/* imports for dashboard functionality */



















import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.script.ScriptException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
/* imports for dashboard functionality */







import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/*##################################################################################
 Class Name : Keyword
 Description : This class will contain all the generic methods

 ####################################################################################*/

public class Keyword extends UserSpecific {
	public static WebDriver driver = null;
	public static AppiumDriver mobdriver = null;
	
	public static Connection conn = null;
	public static Statement stmt = null;
	public static String testName;
	public static String OSNameFromGeneric;
	public static String browserNameFromGeneric;
	public static String URLFromGeneric;
	public static String IEDriverServerPath;// String for the IEDriverServerPath
											// in the config file
	public static String ChromeDriverServerPath;
	public static String ScreenshotMasterCopyPath=null;
	public static String appPackage;
	public static String appActivity;
	public static int stepForwardExceptionFlag = 0;// Flag to set or reset on
													// the basis of exception
													// occurred
	public static String screenShotflag = "N";// initializing the screen flag as
												// N
	public static int nameOfScreenshotFlag = 1;
	public static int nameOfScreenshotFlag2 = 0;
	public static String RetryCountString = "0";
	public static int RetryCount = 0;
	public static int RetryCountTemp = 0;
	public static String configDataDrivenFlag = "N";
	public static String DataDrivenFlag = "N";
	public static String DataBaseURL=null;
	public static Properties CONFIG = null;
	public static JavascriptExecutor js=null;
	public static String ScreenshotBaselineCreationFlag = "N";
	public static String pageTitle;
	public static int totalTestCases=0;
	public static int testCaseNumber=0;
	


	/*
	 * ====================================== variables for Dashboard
	 * functionality =======================================
	 */
	String userName, str, str1;
	static String nameFromDb;
	static int valueFromDb, valueOnUI, valueFromDb2, valueFromDb3;

	static HashMap<String, Integer> usrlg = new HashMap<String, Integer>();
	
	/*declared these variable as global for screenshot compare functionality*/
	public static String folderPath;
	public static String nameOfScreenshot;
	public static String baselinefolderPath;
	
	 /* ==========================Extent reporting integration ==================*/
	
		public static ExtentReports extent;
		public static ExtentTest extentTestCase;
	
	

	public Keyword() {
		try {
			CONFIG = new Properties();
			FileInputStream ip1 = new FileInputStream(
					System.getProperty("user.dir")
							+ "/Config/Config.Properties");
			CONFIG.load(ip1);
			File batchExcelForGeneric = new File(
					CONFIG.getProperty("BatchRunnerexcel"));
			
			Workbook batchWorkBookForGeneric = Workbook
					.getWorkbook(batchExcelForGeneric);
			Sheet batchSheetForGeneric = batchWorkBookForGeneric
					.getSheet("Generic");
			Cell OSCell = batchSheetForGeneric.getCell(1, 1);
			OSNameFromGeneric = OSCell.getContents();
			System.out.println("OSNameFromGeneric" + OSNameFromGeneric);
			Cell browserCell = batchSheetForGeneric.getCell(1, 2);
			browserNameFromGeneric = browserCell.getContents();
			Cell URLCell = batchSheetForGeneric.getCell(1, 3);
			URLFromGeneric = URLCell.getContents();
			Cell appPakageCell = batchSheetForGeneric.getCell(1, 4);
			appPackage = appPakageCell.getContents();
			Cell appActivityCell = batchSheetForGeneric.getCell(1, 5);
			appActivity = appActivityCell.getContents();
			IEDriverServerPath = CONFIG.getProperty("IEDriverServerPath")
					.toString();
			ChromeDriverServerPath = CONFIG.getProperty(
					"ChromeDriverServerPath").toString();
			ScreenshotMasterCopyPath =CONFIG.getProperty("ScreenshotMasterCopyPath").toString(); 
			RetryCountString = CONFIG.getProperty("RetryCount").toString();
			configDataDrivenFlag = CONFIG.getProperty("DataDrivenFlag");
			DataDrivenFlag=configDataDrivenFlag;
			DataBaseURL=CONFIG.getProperty("DataBaseURL");
			RetryCount = Integer.parseInt(RetryCountString);
			RetryCountTemp = RetryCount;
			
			
			
	
			

		} catch (Exception e) {
			e.printStackTrace();
			closewindowCommon();
		}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : writeOutput Description : The function is used
	 * to write result in textfile Created by : 
	 * ####################
	 * ################################################################
	 */
	public static void writeOutput(String text, String ReportName) {
		Writer output = null;
		String folderPath = ExecutionTime.subFolderpathForExecution + "/";
		Integer x = 0;
		File file;
		if (DataDrivenFlag.equals("Y") || DataDrivenFlag.equals("y")) {
			file = new File(folderPath + ReportName + "^"
					+ Framework.testCaseDescription + "^"
					+ Framework.subTestCaseDescription + ".txt");
		} else {
			file = new File(folderPath + ReportName + "^"
					+ Framework.testCaseDescription + ".txt");
			
			/* ======================= Extent Report Editing ===================== */
		
			String[] status=text.split(Pattern.quote(":-"));	
		    System.out.println("Status:" + status[1]);
		    
		    switch (status[1].trim())
		    {
		    	    case "Fail":
		    		extentTestCase.log(LogStatus.FAIL,status[0]);
					break;
		    	    case "Pass":
			    	extentTestCase.log(LogStatus.PASS,status[0]);
					break;
		    	    default :
		    	    extentTestCase.log(LogStatus.INFO,status[0]);
		    }	
		}
		// Object_ID
		x = x + 1;
		try {
			FileWriter fileWriter = new FileWriter(file, true);
			output = new BufferedWriter(fileWriter);
			output.write(text);
			((BufferedWriter) output).newLine();
			output.close();
		} catch (Exception e) {
			System.out.println(e);
			stepForwardExceptionFlag = 1;
			closewindowCommon();
		}

	}

	/*
	 * ##########################################################################
	 * ########## Function Name : getTestObject Description : The function is
	 * used to get test data for test case Created by :   created
	 * date: 18.7.2014
	 * ##########################################################
	 * ##########################
	 */
	public static String getTestObject(String ORName) {
 		String ORproperties = null;
		Sheet ORSheet = null;
		try {
			System.out.println("entered repositry------------");
			File ORExcel = new File(CONFIG.getProperty("ObjectRepositry"));
			Workbook ORWorkbook = Workbook.getWorkbook(ORExcel);
			if (OSNameFromGeneric.contains("Window 7")
					|| OSNameFromGeneric.contains("mac"))
				ORSheet = ORWorkbook.getSheet("OR");
			else if (OSNameFromGeneric.contains("ANDROID.browser")
					|| OSNameFromGeneric.contains("iPhone")
					|| OSNameFromGeneric.contains("iPad")) {
				ORSheet = ORWorkbook.getSheet("OR_device");
			} else if (OSNameFromGeneric.contains("ANDROID_app"))
//				ORSheet = ORWorkbook.getSheet("OR_device");
			ORSheet = ORWorkbook.getSheet("OR");
			for (int j = 1; j < ORSheet.getRows(); j++) {
				Cell ORcell = ORSheet.getCell(1, j);
				String ORvariableName = ORcell.getContents();
				if (ORvariableName.equals(ORName)) {
					{
						ORproperties = ORSheet.getCell(2, j).getContents();
						System.out.println("ORproperties--------------  ;  "
								+ ORproperties);
					}
					break;
				}
			}
			if(ORproperties==(null))
			{
				writeOutput("ORvariableName :"+ ORName +" does'nt exists in ORsheet :- Fail", testName);
			   
			    ExecutionTime.stopExecutionflag=true;
			    stepForwardExceptionFlag = 1;
			    closewindowCommon();
			}
		} catch (Exception e) {
			System.out.println("in exception-----------get Or properties");
			stepForwardExceptionFlag = 1;
			closewindowCommon();
		}
		return ORproperties;
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : waitForPageLoadWeb Description : The function is
	 * used for wait for page load Created by :   Created on :
	 * 9/22/2014
	 * ################################################################
	 * ####################
	 */
	public static void waitForPageLoadWeb() {
		try {
			driver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
			writeOutput("Wait for page load is successful:- Pass", testName);
			
			screnshotsForMytest();
		} catch (Exception e) {
			e.printStackTrace();
			writeOutput("Wait for page load is not successful:- Fail", testName);
			
			screnshotsForMytest();
			System.out.println("in exception------in waitForPageLoad");
		}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : waitForObjectByXpathWeb Description : The function
	 * is used for wait for object Created by :   Created on :
	 * 9/22/2014
	 * ################################################################
	 * ####################
	 */
	public static void waitForObjectByXpathWeb(String objectName) {
		try {
			ExecutionTime.stopOrPauseExecution();
			writeOutput("Wait for \"" + objectName + "\" is successful:- Pass",
					testName);
			
			screnshotsForMytest();
		} catch (Exception e) {
			e.printStackTrace();
			writeOutput("Wait for \"" + objectName
					+ "\" is not successful:- Fail", testName);
			
			screnshotsForMytest();
			System.out.println("in exception------in waitForObjectByXpath");
		}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : navigateurlCommon Description : The function is used
	 * to navigate to application Created by :   Created on :
	 * 7/22/2014
	 * ################################################################
	 * ####################
	 */
	public static void navigateurlCommon(String URL) {
		try {
			System.out.println("entered=======================");
			System.out
					.println("OSNameFromGeneric :"
							+ (OSNameFromGeneric.contains("mac") && browserNameFromGeneric
									.contains("Chrome")));
			if (OSNameFromGeneric.contains("Window 7")
					&& browserNameFromGeneric.contains("IE")
					|| browserNameFromGeneric.contains("ie")
					|| browserNameFromGeneric.contains("explorer")) {
				System.out.println("Using IE");
				System.setProperty("webdriver.ie.driver", IEDriverServerPath);
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				driver.get(URLFromGeneric + "/");
				
			} else if (OSNameFromGeneric.contains("Window 7")
					&& browserNameFromGeneric.contains("Firefox")
					|| browserNameFromGeneric.contains("firefox")
					|| browserNameFromGeneric.contains("mozila")) {
				System.out.println("Using Firefox");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				driver.get(URLFromGeneric + "/");

			} 
			else if (OSNameFromGeneric.contains("Window 7")
					&& browserNameFromGeneric.contains("Chrome")
					|| browserNameFromGeneric.contains("chrome")) {
				System.out.println("Using Chrome");
				System.setProperty("webdriver.chrome.driver",
						ChromeDriverServerPath);
				
//				Maximizing with new chromedriver version you will require chromeoptions
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				options.addArguments("start-maximized");
				options.addArguments("--enable-automation");
				options.addArguments("test-type=browser");
				options.addArguments("disable-infobars");
				
				
				driver = new ChromeDriver(options);
//				driver.manage().window().maximize();
//				driver.get(UsRLFromGeneric + "/");
				driver.get(URLFromGeneric);
			} else if (OSNameFromGeneric.contains("mac")
					&& browserNameFromGeneric.contains("Safari")
					|| browserNameFromGeneric.contains("safari")) {
				System.out.println("Using Safari");
				DesiredCapabilities dc = new DesiredCapabilities();
				dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
						UnexpectedAlertBehaviour.IGNORE);
				driver = new SafariDriver(dc);
				driver.manage().window().maximize();
				driver.get(URLFromGeneric + "/");
			} else if (OSNameFromGeneric.contains("mac")
					&& (browserNameFromGeneric.contains("Chrome") || browserNameFromGeneric
							.contains("chrome"))) {
				System.out.println("Using Chrome");
				System.setProperty("webdriver.chrome.driver",
						ChromeDriverServerPath);
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(URLFromGeneric + "/");
			}
			if (OSNameFromGeneric.contains("ANDROID.browser"))//
			{
				System.out
						.println("entered----------------------------------------------------");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("deviceName", "Android");
				// capabilities.setCapability("deviceName", "Android Emulator");
				capabilities.setCapability("browserName", "Chrome");
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("launch", true);
				capabilities.setCapability("rotatable", true);
				capabilities.setCapability("launchTimeout", 90000);
				capabilities.setCapability("newCommandTimeout", 6000);
				capabilities.setCapability(CapabilityType.VERSION, "6.0.1");
				driver = new RemoteWebDriver(new URL(
						"http://127.0.0.1:4723/wd/hub"), capabilities);
				// driver = new AppiumDriver(new
				// URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				driver.manage().timeouts()
						.pageLoadTimeout(200, TimeUnit.SECONDS);
				driver.get(URLFromGeneric);
				Robot sendkey = new Robot();
				sendkey.keyPress(KeyEvent.VK_ENTER);
			} else if (OSNameFromGeneric.contains("ANDROID_app")) {
				System.out
						.println("entered--------ANDROID_app--------------------------------------------");
				final DesiredCapabilities capabilities = new DesiredCapabilities();
				/*capabilities.setCapability("deviceName", "Android");
				capabilities.setCapability("platformName", "Android");
				// --for browser
				// capabilities.setCapability("browserName", "chrome");
				// ---for application------
				// capabilities.setCapability("browserName", "");
				capabilities.setCapability("launch", true);
				capabilities.setCapability("rotatable", true);
				capabilities.setCapability("launchTimeout", 90000);
				capabilities.setCapability("newCommandTimeout", 60000);
				capabilities.setCapability(CapabilityType.VERSION, "6.0.1");
				// File appDir = new
				// File("C:\\Users\\beant\\Downloads\\apktool");
				// File app = new File(appDir, "AndroidCalculator.apk");
				// capabilities.setCapability("app", app);
				// capabilities.setCapability("appPackage",
				// "com.newgen.nemf.client.omnidesk");
				// capabilities.setCapability("appActivity",
				// "com.newgen.nemf.client.omnidesk.MainActivity");
				capabilities.setCapability("appPackage", appPackage);
				capabilities.setCapability("appActivity", appActivity);*/
				
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("deviceName", "fd77186a");

				capabilities.setCapability("platformversion","10.3.2");
				capabilities.setCapability("appPackage", appPackage);
				capabilities.setCapability("appActivity", appActivity);
							
				if(driver==null)
				{
					 driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
//					driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
//					 ((AndroidDriver) driver).resetApp();
				}
				
				else
				{
					((AndroidDriver) driver).launchApp();
					((AndroidDriver) driver).resetApp();   
				}
				// driver.findElement(By.xpath("//android.view.View[@resource-id='iRemoveUser']")).click();
//				 driver.manage().timeouts().pageLoadTimeout(3000,TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);
				// Thread.sleep(20000);
				System.out.println("ANDROID_app----intialized");
			} else if (OSNameFromGeneric.contains("iPhone")) {
				System.out
						.println("entered--------iphone--------------------------------------------");
				final DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("deviceName", "iPhone 6 Plus");
				capabilities.setCapability("platformName", "ios");
				capabilities.setCapability("browserName", "Safari");
				capabilities.setCapability(CapabilityType.VERSION, "8.4");
				driver = new RemoteWebDriver(new URL(
						"http://127.0.0.1:4723/wd/hub"), capabilities);
				driver.get(URLFromGeneric);
			} else if (OSNameFromGeneric.contains("iPad")) {
				System.out
						.println("entered--------ipad--------------------------------------------");
				final DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("deviceName", "iPad 2");
				capabilities.setCapability("platformName", "ios");
				capabilities.setCapability("browserName", "Safari");
				capabilities.setCapability("launch", true);
				capabilities.setCapability("rotatable", true);
				capabilities.setCapability("launchTimeout", 90000);
				capabilities.setCapability("newCommandTimeout", 6000);
				capabilities.setCapability(CapabilityType.VERSION, "8.4");
				driver = new RemoteWebDriver(new URL(
						"http://127.0.0.1:4723/wd/hub"), capabilities);
				driver.get(URLFromGeneric);
			}

			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Thread.sleep(2000);
			writeOutput("sentinel :- ", testName);
			writeOutput("Browser \"" + URL
					+ "\" is launched successfully:- Pass", testName);
			
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			e.printStackTrace();
			writeOutput("Browser \"" + URL
					+ "\" is not launched successfully:- Fail", testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : typeTextByXpathWeb Description : The function is
	 * used to type text on object using xpath locator Created by : 
	 *  Created on : 7/22/2014
	 * #############################################
	 * #######################################
	 */
	public static void typeTextByXpathWeb(String objectName, String text) {
		try {
			ExecutionTime.stopOrPauseExecution();
			
			driver.findElement(By.xpath(getTestObject(objectName))).clear();
			driver.findElement(By.xpath(getTestObject(objectName))).sendKeys(
					text);
			driver.findElement(By.xpath(getTestObject(objectName))).sendKeys(
					Keys.TAB);
			waitCommon("1000");
//			driver.findElement(By.xpath(getTestObject(objectName))).getText();
			writeOutput("Text \"" + text + "\" is entered successfully in \"" + objectName + "\"  :- Pass", testName);
            System.out.println(driver.findElement(By.xpath(getTestObject(objectName))).getText());
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			e.printStackTrace();
			System.out.println(" in exception--------inputtextbyxpath");
			writeOutput("Text \"" + text + "\" is not entered successfully in \"" + objectName + "\"  :- Fail", testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}
	
	/*
	 * ##########################################################################
	 * ######## Function Name : typetextbyCssSelectorWeb Description : The function is
	 * used to type text on object using css selector locator Created by : 
	 *  Created on : 5/17/2016
	 * #############################################
	 * #######################################
	 */
	public static void typetextbyCssSelectorWeb(String objectName, String text) {
		try {
			ExecutionTime.stopOrPauseExecution();
			driver.findElement(By.cssSelector(getTestObject(objectName))).sendKeys(
					text);
			waitCommon("1000");
			writeOutput("Text \"" + text + "\" is entered successfully in \"" + objectName + "\"  :- Pass", testName);
			
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out.println(" in exception--------inputtextbyxpath");
			writeOutput("Text \"" + text + "\" is not entered successfully in \"" + objectName + "\"  :- Fail", testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : clearTextByXpathWeb Description : The function is
	 * used to clear text on object using xpath locator Created by : 
	 *  Created on : 7/22/2014
	 * #############################################
	 * #######################################
	 */
	public static void clearTextByXpathWeb(String objectName) {
		try {
			ExecutionTime.stopOrPauseExecution();
			 driver.findElement(By.xpath(getTestObject(objectName))).clear();
			writeOutput("Text from \"" + objectName
					+ "\" is cleared successfully:- Pass", testName);
			
			screnshotsForMytest();

		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out.println(" in exception--------clearTextByXpath");
			writeOutput("Text from \"" + objectName
					+ "\" is not cleared successfully:- Pass", testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}
	
	/*
	 * ##########################################################################
	 * ######## Function Name : clearTextByCssSelectorWeb Description : The function is
	 * used to clear text on object using CssSelector locator Created by : 
	 *  Created on : 5/17/2016
	 * #############################################
	 * #######################################
	 */
	public static void clearTextByCssSelectorWeb(String objectName) {
		try {
			ExecutionTime.stopOrPauseExecution();
			 driver.findElement(By.cssSelector(getTestObject(objectName))).clear();
			writeOutput("Text from \"" + objectName
					+ "\" is cleared successfully:- Pass", testName);
			
			screnshotsForMytest();

		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out.println(" in exception--------clearTextByXpath");
			writeOutput("Text from \"" + objectName
					+ "\" is not cleared successfully:- Pass", testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}
	

	/*
	 * ##########################################################################
	 * ######## Function Name : typeTextByNameWeb Description : The function is
	 * used to type text on object using name locator Created by :  
	 * Created on : 7/22/2014
	 * ###################################################
	 * #################################
	 */
	public static void typeTextByNameWeb(String objectName, String text) {
		try {
            String getTestObject= getTestObject(objectName);
			ExecutionTime.stopOrPauseExecution();
			if(!(getTestObject==null)){
			if (driver.findElement(By.name(getTestObject))
					.isDisplayed()) {
				driver.findElement(By.name(getTestObject)).clear();
				driver.findElement(By.name(getTestObject))
						.sendKeys(text);
				waitCommon("1000");
				writeOutput("Text \"" + text + "\" is entered successfully in \"" + objectName + "\"  :- Pass", testName);
			} else {
				stepForwardExceptionFlag = 1;
				//System.out.println("Text \"" + text + "\" at \"" + objectName+ "\" is not entered successfully:- Fail");
				writeOutput("Text \"" + text + "\" is not entered successfully in \"" + objectName + "\"  :- Fail", testName);
				closewindowCommon();
			}
			
			screnshotsForMytest();
			}
			
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out.println("in exception-----------inputtextbyname" + e);
			writeOutput("Text \"" + text + "\" is not entered successfully in \"" + objectName + "\"  :- Fail", testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : typeTextByIDWebAndroid Description : The function is used
	 * to type text on object using ID locator Created by :  
	 * Created on : 7/22/2014
	 * ###################################################
	 * #################################
	 */
	public static void typeTextByIDWebAndroid(String objectName, String text) {

		try {
			String getTestObject = getTestObject(objectName);
			ExecutionTime.stopOrPauseExecution();
			if(!(getTestObject==null))
			{	
			if (OSNameFromGeneric.equalsIgnoreCase("Window 7")) {

				if (driver.findElement(By.id(getTestObject)).isDisplayed()) {
					driver.findElement(By.id(getTestObject)).clear();
					driver.findElement(By.id(getTestObject)).sendKeys(text);
					waitCommon("1000");
					writeOutput("Text \"" + text + "\" is entered successfully in \"" + objectName + "\"  :- Pass", testName);
				} else {
					stepForwardExceptionFlag = 1;
					writeOutput("Text \"" + text + "\" is not entered successfully in \"" + objectName + "\"  :- Fail", testName);
					closewindowCommon();
				}
			} else if (OSNameFromGeneric.equalsIgnoreCase("ANDROID_app")) {
				if (((AndroidDriver) driver).findElementById(getTestObject)
						.isDisplayed()) {
					((AndroidDriver) driver).findElementById(getTestObject)
							.clear();
					((AndroidDriver) driver).findElementById(getTestObject).click();
					
					((AndroidDriver) driver).findElementById(getTestObject).sendKeys(text);
					
					((AndroidDriver) driver).hideKeyboard();
					writeOutput("Text \"" + text + "\" is entered successfully in \"" + objectName + "\"  :- Pass", testName);
				} else {
					stepForwardExceptionFlag = 1;
					writeOutput("Text \"" + text + "\" is not entered successfully in \"" + objectName + "\"  :- Fail", testName);
					closewindowCommon();
				}
			}
			
			screnshotsForMytest();
			}
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out.println("in exception-----------typeTextByID"
					+ e.getMessage());
			writeOutput("Text \"" + text + "\" is not entered successfully in \"" + objectName + "\"  :- Fail", testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : clickByXpathWeb Description : The function is used
	 * Click on object using xpath locator Created by :   Created on
	 * : 7/22/2014
	 * ##############################################################
	 * ######################
	 */
	public static void clickByXpathWeb(String objectName) {
		try {
			ExecutionTime.stopOrPauseExecution();
			js=(JavascriptExecutor)driver;
			String getTestObject = getTestObject(objectName);
			if(!(getTestObject==null))
			{	
			WebElement xPathElement = driver.findElement(By.xpath(getTestObject));
//			js.executeScript("arguments[0].setAttribute('style','border: solid 2px red;background-color: Yellow')", xPathElement);
			xPathElement.click();
			waitCommon("1000");
			/*if (isAlertPresent()== true){
				System.out.println("in alert handle for login");
				Alert alert = Keyword.driver.switchTo().alert();
				alert.accept();
				Keyword.driver.switchTo().defaultContent();
				}*/
//			clickWithHandleAlertWeb();
			
			writeOutput(
					"Click on \"" + objectName + "\"  is successful:- Pass",
					testName);
			
			screnshotsForMytest();
			}
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			e.printStackTrace();
			System.out.println("in exception-------------------ClickonButtonbyxpath");
			writeOutput("Click on \"" + objectName
					+ "\" is not successful:- Fail", testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}
	

	/*
	 * ##########################################################################
	 * ######## Function Name : clickByNameWebAndroid Description : The function is used
	 * Click on object using name locator Created by :   Created on
	 * : 7/22/2014
	 * ##############################################################
	 * ######################
	 */
	public static void clickByNameWebAndroid(String objectName) throws IOException {
		String getTestObject = getTestObject(objectName);
		try {
			ExecutionTime.stopOrPauseExecution();

			if (OSNameFromGeneric.equalsIgnoreCase("Window 7")) {
				if (driver.findElement(By.name(getTestObject)).isEnabled()) {
					driver.findElement(By.name(getTestObject)).click();
					waitCommon("1000");
					writeOutput("Click on \"" + objectName
							+ "\"  is successful:- Pass", testName);
				}
			} else if (OSNameFromGeneric.equalsIgnoreCase("ANDROID_app")) {
				if (((AndroidDriver) driver).findElementByName(getTestObject)
						.isEnabled()) {
					((AndroidDriver) driver).findElementByName(getTestObject)
							.click();
					writeOutput("Click on \"" + objectName
							+ "\"  is successful:- Pass", testName);
				}
			}
			
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out
					.println("in Exception------------------------ClickonButtonbyname");
			writeOutput("Click on \"" + objectName
					+ "\" is not successful:- Fail", testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}
	
	/*
	 * ##########################################################################
	 * ######## Function Name : switchToIFrameByName Description : used for
	 * handling IFrames Created by :   Created on :
	 * 28/04/2016
	 * #################################################################
	 * ###################
	 */	
	public static void switchToIFrameByNameWeb(String objectName) throws IOException
	{
		String getTestObject = getTestObject(objectName);
		try {
			ExecutionTime.stopOrPauseExecution();

			if (OSNameFromGeneric.equalsIgnoreCase("Window 7")) {
//				if (driver.findElement(By.name(getTestObject)).isEnabled()) {
				WebElement iFrame = driver.findElement(By.name(getTestObject));
				driver.switchTo().frame(iFrame);
//				js.executeScript("arguments[0].setAttribute('style,'border: solid 2px red'')", iFrame);
					/*if(getTestObject.equals("frame-middle"))
					{
						System.out.println(driver.findElement(By.id("content")).getText());
					}*/
					writeOutput("Switching to \"" + objectName
							+ "\"  is successful:- Pass", testName);
					
				}
//			} 
		/*else if (OSNameFromGeneric.equalsIgnoreCase("ANDROID_app")) {
				if (((AndroidDriver) driver).findElementByName(getTestObject)
						.isEnabled()) {
					((AndroidDriver) driver).findElementByName(getTestObject)
							.click();
					writeOutput("Click on \"" + objectName
							+ "\"  is successful:- Pass", testName);
				}
			}*/
			
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out
					.println("in Exception------------------------while Switching to Frame");
			writeOutput("Click on \"" + objectName
					+ "\" is not successful:- Fail", testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
		
	}
	
	
	/*
	 * ##########################################################################
	 * ######## Function Name : switchToIFrameByXpath Description : used for
	 * handling IFrames using xPath Created by :   Created on :
	 * 17/05/2016
	 * #################################################################
	 * ###################
	 */	
	public static void switchToIFrameByXpathWeb(String objectName) throws IOException
	{
		String getTestObject = getTestObject(objectName);
		try {
			ExecutionTime.stopOrPauseExecution();

			if (OSNameFromGeneric.equalsIgnoreCase("Window 7")) {
//				if (driver.findElement(By.name(getTestObject)).isEnabled()) {
				WebElement iFrame = driver.findElement(By.xpath(getTestObject));
				driver.switchTo().frame(iFrame);
//				js.executeScript("arguments[0].setAttribute('style,'border: solid 2px red'')", iFrame);
					/*if(getTestObject.equals("frame-middle"))
					{
						System.out.println(driver.findElement(By.id("content")).getText());
					}*/
					writeOutput("Switching to \"" + objectName
							+ "\"  is successful:- Pass", testName);
					
				}
//			} 
		/*else if (OSNameFromGeneric.equalsIgnoreCase("ANDROID_app")) {
				if (((AndroidDriver) driver).findElementByName(getTestObject)
						.isEnabled()) {
					((AndroidDriver) driver).findElementByName(getTestObject)
							.click();
					writeOutput("Click on \"" + objectName
							+ "\"  is successful:- Pass", testName);
				}
			}*/
			
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out
					.println("in Exception------------------------while Switching to Frame");
			writeOutput("Click on \"" + objectName
					+ "\" is not successful:- Fail", testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
		
	}
	
	/*
	 * ##########################################################################
	 * ######## Function Name : switchToIFrameByCSSSelector Description : used for
	 * handling IFrames using xPath Created by :   Created on :
	 * 17/05/2016
	 * #################################################################
	 * ###################
	 */	
	public static void switchToIFrameByCSSSelectorWeb(String objectName) throws IOException
	{
		String getTestObject = getTestObject(objectName);
		try {
			ExecutionTime.stopOrPauseExecution();

			if (OSNameFromGeneric.equalsIgnoreCase("Window 7")) {
//				if (driver.findElement(By.name(getTestObject)).isEnabled()) {
				WebElement iFrame = driver.findElement(By.cssSelector(getTestObject));
				driver.switchTo().frame(iFrame);
//				js.executeScript("arguments[0].setAttribute('style,'border: solid 2px red'')", iFrame);
					/*if(getTestObject.equals("frame-middle"))
					{
						System.out.println(driver.findElement(By.id("content")).getText());
					}*/
					writeOutput("Switching to \"" + objectName
							+ "\"  is successful:- Pass", testName);
					
				}
//			} 
		/*else if (OSNameFromGeneric.equalsIgnoreCase("ANDROID_app")) {
				if (((AndroidDriver) driver).findElementByName(getTestObject)
						.isEnabled()) {
					((AndroidDriver) driver).findElementByName(getTestObject)
							.click();
					writeOutput("Click on \"" + objectName
							+ "\"  is successful:- Pass", testName);
				}
			}*/
			
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out
					.println("in Exception------------------------while Switching to Frame");
			writeOutput("Click on \"" + objectName
					+ "\" is not successful:- Fail", testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
		
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : clickByLinktextWeb Description : The function is
	 * used Click on button Created by :   Created on :
	 * 7/22/2014(edited on 15/02/16)
	 * ############################################
	 * ########################################
	 */
	public static void clickByLinktextWeb(String objectName) throws IOException {

		System.out.println("clickByLinktext");
		try {
			ExecutionTime.stopOrPauseExecution();
			if (driver.findElement(By.linkText(getTestObject(objectName)))
					.isEnabled()) {
				driver.findElement(By.linkText(getTestObject(objectName)))
						.click();
				writeOutput("Button \"" + objectName
						+ "\" is clicked successully:- Pass", testName);
			}
			
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out
					.println("in Exception------------------------ClickonButtonbyname");
			writeOutput("Button \"" + objectName
					+ "\" is not clicked successully:- Fail", testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : clickByIDWebAndroid Description : The function is used
	 * Click by id Created by :  
	 * ###################################
	 * #################################################
	 */
	public static void clickByIDWebAndroid(String objectName) {
		try {
			if(!OSNameFromGeneric.equalsIgnoreCase("ANDROID_app"))
			pageTitle = driver.getTitle();
			ExecutionTime.stopOrPauseExecution();
			String getTestObject = getTestObject(objectName);
			if (OSNameFromGeneric.equalsIgnoreCase("Window 7"))
			{
				WebElement idWebElement = driver.findElement(By.id(getTestObject));
				/*js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].setAttribute('style','border: solid 2px red;background-color: Yellow')", idWebElement);*/
				System.out.println("asda");
				idWebElement.click();
				waitCommon("1000");
				if (isAlertPresent()== true){
					System.out.println("in alert handle for login");
					Alert alert = Keyword.driver.switchTo().alert();
					alert.accept();
					Keyword.driver.switchTo().defaultContent();
					}
			}	
			else if (OSNameFromGeneric.equalsIgnoreCase("ANDROID_app"))
				((AndroidDriver) driver).findElementById(getTestObject).click();
			
			writeOutput(
					"Click on \"" + objectName + "\"  is successful:- Pass",
					testName);
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			writeOutput("Click on \"" + objectName
					+ "\" is not successful:- Fail", testName);
			e.printStackTrace();
			screnshotsForMytest();
			    closewindowCommon();
		}
	}

	/*
	 * ##########################################################################
	 * ########## Function Name : closewindowCommon Description : This is used to
	 * close window Created by :   Created on : 7/22/2014
	 * ###########
	 * #########################################################################
	 */
	public static void closewindowCommon() {
		try {
			ExecutionTime.stopOrPauseExecution();
			writeOutput("Browser is closed successfully:- Pass", testName);
			
			screnshotsForMytest();
			Thread.sleep(1000);
			if (OSNameFromGeneric.equalsIgnoreCase("Window 7")) {
				driver.close();
				
				System.out.println("driver" + driver);
				//driver.quit();
			} else if (OSNameFromGeneric.equalsIgnoreCase("ANDROID_app")) {
				((AndroidDriver)driver).closeApp();
			}
			
			System.out.println(" before Status of test :" + extentTestCase.getRunStatus());
			extent.endTest(extentTestCase);
			System.out.println("after Status of test :" + extentTestCase.getRunStatus());
			
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			stepForwardExceptionFlag = 1;
			System.out.println("in exception----------closebrowserv" + e);
			writeOutput("Browser is not closed successfully:- Fail", testName);
			
			screnshotsForMytest();
		}

	}

	/*
	 * ##########################################################################
	 * ######## Function Name : waitCommon Description : wait for specific time and
	 * time in millisecond Created by :   Created on : 7/7/2014
	 * #####
	 * #####################################################################
	 * ##########
	 */
	public static void waitCommon(String timeToWait) {
		try {
			System.out.println("<--Wait-->");
			ExecutionTime.stopOrPauseExecution();
			int intTimeToWait = Integer.parseInt(timeToWait);
			Thread.sleep(intTimeToWait);
			
			/*writeOutput("Wait for \"" + timeToWait
					+ "\" milisecond is successful:- Pass", testName);
			
			screnshotsForMytest();*/
		} catch (Exception e) {
			
			/*writeOutput("Wait for \"" + timeToWait
					+ "\" milisecond is not successful:- UIFail", testName);
			
			screnshotsForMytest();*/
			 closewindowCommon();
		}
	}

	/*
	 * ##########################################################################
	 * ########## Function Name : verifyTextByXpathWeb Description : To get the
	 * text from object from xpath locator and verify with expected text Created
	 * by :   Created on : 7/22/2014
	 * ################################
	 * ####################################################
	 */
	public static void verifyTextByXpathWeb(String objectName, String text) {

		WebElement bodyTextWebElement = null;
		String bodyText=null;
		js=(JavascriptExecutor)driver;
//		JavascriptExecutor js=(JavascriptExecutor)driver;
		try {
			ExecutionTime.stopOrPauseExecution();
			Thread.sleep(2000);
			try {
				bodyTextWebElement= driver.findElement(By.xpath(getTestObject(objectName)));
				bodyText=bodyTextWebElement.getText().trim();
//				JavascriptExecutor js=(JavascriptExecutor)driver;
//				js.executeScript("arguments[0].setAttribute('style,'border: solid 2px red'')", bodyTextWebElement);
				js.executeScript("arguments[0].setAttribute('style','border: solid 2px red;background-color: Yellow')", bodyTextWebElement);
				Thread.sleep(2000);
				
			} catch (org.openqa.selenium.UnhandledAlertException e) {
				System.out
						.println("in exception as unhandled---------------------verifyTextByXpath"
								+ e);

			}
            System.out.println("equals : " + bodyText.equals(text.trim()));
            System.out.println("comparess : " + bodyText.compareTo(text.trim()));
            
			if (bodyText.contains(text.trim())) {
				writeOutput("Actual Text \"" + bodyText
						+ "\" matches the Expected Text \"" + text.trim()
						+ "\" :- Pass", testName);
			} else {
				writeOutput("Actual Text \"" + bodyText
						+ "\" does not match Expected Text \"" + text.trim()
						+ "\" :- UIFail", testName);
			}
			
			screnshotsForMytest();
			js.executeScript("arguments[0].setAttribute('style','border: solid 2px White;background-color: White')", bodyTextWebElement);
		} catch (Exception e) {
			e.printStackTrace();
			// stepForwardExceptionFlag = 1;
			System.out
					.println("in exception---------------------verifyTextByXpath"
							+ e);
			writeOutput("Actual Text does not match Expected Text \"" + text
					+ "\" :- UIFail", testName);
			
			screnshotsForMytest();
			closewindowCommon();
			if(bodyTextWebElement!=null)
			{
				js.executeScript("arguments[0].setAttribute('style','border: solid 2px White;background-color: White')", bodyTextWebElement);	
			}
			
			
		}
	}
	
	

/*
	 * ##########################################################################
	 * ########## Function Name : verifyTextByIDWebAndroid Description : To get the text
	 * from object from id locator and verify with expected text Created by :
	 *   Created on : 7/22/2014
	 * #####################################
	 * ###############################################
	 */
	public static void verifyTextByIDWebAndroid(String objectName, String text) {
		String bodyText = null;
//		js=(JavascriptExecutor)driver;
		WebElement element=driver.findElement(By.id(getTestObject(objectName))); 
		try {
			ExecutionTime.stopOrPauseExecution();
			bodyText = element.getText();
			/*if(!OSNameFromGeneric.equalsIgnoreCase("ANDROID_app"))
			    js.executeScript("arguments[0].setAttribute('style','border: solid 2px red;background-color: Yellow')", bodyText);*/
			Thread.sleep(2000);
			if (bodyText.equalsIgnoreCase(text))
				writeOutput("Actual Text \"" + bodyText	+ "\" matches the Expected Text \"" + text	+ "\" :- Pass", testName);
			else {
				System.out.println("Actual Text \"" + bodyText
						+ "\" does not match Expected Text \"" + text
						+ "\" :- UIFail");
				writeOutput("Actual Text \"" + bodyText
						+ "\" does not match Expected Text \"" + text
						+ "\" :- UIFail", testName);
			}
//			screnshotsForMytest();
			/*if(!OSNameFromGeneric.equalsIgnoreCase("ANDROID_app"))
			       js.executeScript("arguments[0].setAttribute('style','border: solid 2px White;background-color: White')", bodyText);*/
		} catch (Exception e) {
			System.out
					.println("in exception---------------------Textvalidationbyxpath"
							+ e);
			writeOutput("Actual Text \"" + bodyText
					+ "\" does not match Expected Text \"" + text
					+ "\" :- Fail", testName);
			e.printStackTrace();
//			screnshotsForMytest();
			/*if(!OSNameFromGeneric.equalsIgnoreCase("ANDROID_app"))
			       js.executeScript("arguments[0].setAttribute('style','border: solid 2px White;background-color: White')", bodyText);*/
		}
	}


/*
	 * ##########################################################################
	 * ########## Function Name : verifyTextByNameWeb Description : To get the text
	 * from object from id locator and verify with expected text Created by :
	 *   Created on : 7/22/2014
	 * #####################################
	 * ###############################################
	 */
	public static void verifyTextByNameWeb(String objectName, String text) {
		String bodyText = null;
		js=(JavascriptExecutor)driver;
		try {
			ExecutionTime.stopOrPauseExecution();
			bodyText = driver.findElement(By.name(objectName))
					.getText();
			js.executeScript("arguments[0].setAttribute('style','border: solid 2px red;background-color: Yellow')", bodyText);
			Thread.sleep(2000);
			if (bodyText.contains(text))
				writeOutput("Actual Text \"" + bodyText
						+ "\" matches the Expected Text \"" + text
						+ "\" :- Pass", testName);
			else {
				System.out.println("Actual Text \"" + bodyText
						+ "\" does not match Expected Text \"" + text
						+ "\" :- UIFail");
				writeOutput("Actual Text \"" + bodyText
						+ "\" does not match Expected Text \"" + text
						+ "\" :- UIFail", testName);
			}
			
			screnshotsForMytest();
			js.executeScript("arguments[0].setAttribute('style','border: solid 2px White;background-color: White')", bodyText);
		} catch (Exception e) {
			System.out
					.println("in exception---------------------Textvalidationbyxpath"
							+ e);
			writeOutput("Actual Text \"" + bodyText
					+ "\" does not match Expected Text \"" + text
					+ "\" :- Fail", testName);
			
			screnshotsForMytest();
			js.executeScript("arguments[0].setAttribute('style','border: solid 2px White;background-color: White')", bodyText);
		}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : verifyValuebyIDWeb Description : To get the
	 * Attribute value from object from id locator and verify with expected text
	 * Created by :   Created on : 7/23/2014
	 * ########################
	 * ############################################################
	 */
	public static String verifyValuebyIDWeb(String objectName, String text) {
		String attributeValue = null;
		try {
			ExecutionTime.stopOrPauseExecution();
			attributeValue = driver.findElement(
					By.id(getTestObject(objectName))).getAttribute("value");
			if (attributeValue.contains(text))
				writeOutput("Actual Text \"" + attributeValue
						+ "\" matches Expected Text \"" + text + "\" :- Fail",
						testName);
			else {
				System.out.println("Actual Text \"" + attributeValue
						+ "\" does not match Expected Text \"" + text
						+ "\" :- UIFail");
				writeOutput("Actual Text \"" + attributeValue
						+ "\" does not match Expected Text \"" + text
						+ "\" :- UIFail", testName);
			}
			
			screnshotsForMytest();
		} catch (Exception e) {
			System.out.println(e);
			writeOutput("Actual Text \"" + attributeValue
					+ "\" does not match Expected Text \"" + text
					+ "\" :- UIFail", testName);
			
			screnshotsForMytest();
		}
		return attributeValue;
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : verifyWebListSelectedItemByIDWeb Description : The
	 * function is used to verify the by-default selected item in dropdown
	 * Created by :   Created on : 7/28/2014
	 * ########################
	 * ############################################################
	 */
	public static boolean verifyWebListSelectedItemByIDWeb(String objectName,
			String valueToselect) {
		boolean verifyselectedItem = false;
		String selectedItem = "";
		try {
			ExecutionTime.stopOrPauseExecution();
			Select dropdown = new Select(driver.findElement(By.id(getTestObject(objectName))));
			selectedItem = dropdown.getFirstSelectedOption().getText();
			verifyselectedItem = dropdown.getFirstSelectedOption().getText().contains(valueToselect);
			writeOutput("Selected Item \"" + selectedItem + "\" from \""+ objectName + "\" is verified successfully:- Pass",testName);
			screnshotsForMytest();
		} catch (Exception e) {
			System.out
					.println("in exception-----------verifyWebListSelectedItemByID"
							+ e);
			writeOutput("Selected Item \"" + selectedItem + "\" from \""
					+ objectName + "\" is not verified successfully:- UIFail",
					testName);
			
			screnshotsForMytest();
		}
		return verifyselectedItem;
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : isEnabledByIDWeb Description : Used to check that
	 * object is enabled or not Created by :   Created on :
	 * 7/28/2014
	 * ################################################################
	 * ####################
	 */
	public static void isEnabledByIDWeb(String objectName) {
		boolean isEnabled = false;
		try {
			ExecutionTime.stopOrPauseExecution();
			isEnabled = driver.findElement(By.id(getTestObject(objectName)))
					.isEnabled();
			if (isEnabled == true)
				writeOutput("Object \"" + objectName + "\" is enabled :- Pass",
						testName);
			else {
				stepForwardExceptionFlag = 1;
				writeOutput("Object \"" + objectName
						+ "\" is not enabled :- Fail", testName);
				closewindowCommon();
			}
			
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out.println("in exception-----------isEnabledByID" + e);
			writeOutput("Object \"" + objectName + "\" is not enabled :- Fail",
					testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : isNotEnabledByIDWeb Description : Used to check
	 * that object is enabled or not Created by :   Created on :
	 * 7/28/2014
	 * ################################################################
	 * ####################
	 */
	public static void isNotEnabledByIDWeb(String objectName) {
		boolean isEnabled = false;
		try {
			ExecutionTime.stopOrPauseExecution();
			isEnabled = driver.findElement(By.id(getTestObject(objectName)))
					.isEnabled();
			if (isEnabled == true)
				writeOutput("Object \"" + objectName + "\" is enabled :- Fail",
						testName);
			else
				writeOutput("Object \"" + objectName
						+ "\" is not enabled :- Pass", testName);
			
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out.println("in exception-----------isNotEnabledByID" + e);
			writeOutput("Object \"" + objectName + "\" is not enabled :- Fail",
					testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}

	}

	/*
	 * ##########################################################################
	 * ######## Function Name : selectWebListByID Description : The function is
	 * used select item from dropdown using id locator Created by : Karan
	 * Arora
	 * ####################################################################
	 * ################
	 */
	public static void selectWebListByIDWeb(String objectName, String valueToselect) {
		try {
			ExecutionTime.stopOrPauseExecution();
			Select dropdown = new Select(driver.findElement(By
					.id(getTestObject(objectName))));
			dropdown.selectByVisibleText(valueToselect);
			writeOutput("Item \"" + valueToselect
					+ "\" is selected in dropdown \"" + objectName
					+ "\" successfully:- Pass", testName);
			
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out.println("in exception-----------selectWebListByID" + e);
			writeOutput("Item \"" + valueToselect
					+ "\" is not selected in dropdown \"" + objectName
					+ "\" successfully:- Fail", testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}
	
	/*
	 * ##########################################################################
	 * ######## Function Name : stopExecutionByXpath Description : The function is used
	 * stop the execution on validation failure Created by :   Created on
	 * : 04/19/2016
	 * ##############################################################
	 * ######################
	 */
	public static void stopExecutionByXpathWeb(String objectName) throws IOException {
		String getTestObject = getTestObject(objectName);
		try {
			ExecutionTime.stopOrPauseExecution();

			if(!(getTestObject==null)){

			if (OSNameFromGeneric.equalsIgnoreCase("Window 7")) 
			{
				String title = driver.getTitle();
				System.out.println(title);
				if(pageTitle.equals(driver.getTitle()))
				{
				    
				    	String loginMessage = driver.findElement(By.xpath(getTestObject)).getText();
				    	System.out.println("Login failure validation message is:-" +loginMessage);
//						writeOutput("Click on \"" + objectName+ "\"  is successful:- Pass", testName);
						if(loginMessage!=null)
							{
							ExecutionTime.stopExecutionflag=true;
							writeOutput("Login is Unsuccessful:- Fail", testName);
							stepForwardExceptionFlag=1;
							closewindowCommon();
						}
				    
					
					
				}
				else
				{
					writeOutput("Login is successful:- Pass", testName);
				}
			 }	
			} 
			
			screnshotsForMytest();
		} catch (Exception e) {
			e.printStackTrace();
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}

	/*
	 * ##########################################################################
	 * ########## Function Name : verifyTextByCssSelectorWeb Description : To get the text
	 * from object by cssSelector locator and verify with expected text Created by :
	 *   Created on : 05/12/2016
	 * #####################################
	 * ###############################################
	 */
	public static void verifyTextByCssSelectorWeb(String objectName, String text) {
		String bodyText = null;
		js=(JavascriptExecutor)driver;
		try {
			ExecutionTime.stopOrPauseExecution();
			js=(JavascriptExecutor)driver;
			WebElement textElement = driver.findElement(By.cssSelector(getTestObject(objectName)));
			js.executeScript("arguments[0].setAttribute('style','border: solid 2px red;background-color: Yellow')", textElement);
			bodyText = textElement.getText();
//			js.executeScript("arguments[0].setAttribute('style','border: solid 2px red;background-color: Yellow')", bodyText);
			Thread.sleep(2000);
			if (bodyText.contains(text))
				writeOutput("Actual Text \"" + bodyText
						+ "\" matches the Expected Text \"" + text
						+ "\" :- Pass", testName);
			else {
				System.out.println("Actual Text \"" + bodyText
						+ "\" does not match Expected Text \"" + text
						+ "\" :- UIFail");
				writeOutput("Actual Text \"" + bodyText
						+ "\" does not match Expected Text \"" + text
						+ "\" :- UIFail", testName);
			}
			
			screnshotsForMytest();
//			js.executeScript("arguments[0].setAttribute('style','border: solid 2px White;background-color: White')", bodyText);
		} catch (Exception e) {
			System.out
					.println("in exception---------------------TextvalidationbycssSelector"
							+ e);
			writeOutput("Actual Text \"" + bodyText
					+ "\" does not match Expected Text \"" + text
					+ "\" :- Fail", testName);
			
			screnshotsForMytest();
			js.executeScript("arguments[0].setAttribute('style','border: solid 2px White;background-color: White')", bodyText);
		}
	}
		
	/*
	 * ##########################################################################
	 * ######## Function Name : clickByCSSSelectorWeb Description : The function is used
	 * Click on object using CSSSelector locator Created by :   Created on
	 * : 5/12/2016
	 * ##############################################################
	 * ######################
	 */
	public static void clickByCSSSelectorWeb(String objectName) {
		try {
			ExecutionTime.stopOrPauseExecution();
			String getTestObject = getTestObject(objectName);
			if(!(getTestObject==null))
			{	
			driver.findElement(By.cssSelector(getTestObject)).click();
			/*if (isAlertPresent()== true){
				System.out.println("in alert handle for login");
				Alert alert = Keyword.driver.switchTo().alert();
				alert.accept();
				Keyword.driver.switchTo().defaultContent();
				}*/
			clickWithHandleAlertWeb();
			writeOutput(
					"Click on \"" + objectName + "\"  is successful:- Pass",
					testName);
			
			screnshotsForMytest();
			}
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			e.printStackTrace();
			System.out.println("in exception-------------------clickByCSSSelector");
			writeOutput("Click on \"" + objectName
					+ "\" is not successful:- Fail", testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}
	

	/*
	 * ##########################################################################
	 * ######## Function Name : sendkeysWeb Description : used to send
	 * keyboard'keys on focused object Created by :   Created on :
	 * 7/9/2014
	 * #################################################################
	 * ###################
	 */
	public static void sendkeysWeb(String keyString) {
		try {
			/*ExecutionTime.stopOrPauseExecution();
			Robot robot = new Robot();
			switch (keyString) {
			case "ENTER":
				robot.keyPress(KeyEvent.VK_ENTER);
				break;
			case "TAB":
				robot.keyPress(KeyEvent.VK_TAB);
				break;
			case "ARROW_DOWN":
				robot.keyPress(KeyEvent.VK_KP_DOWN);
				break;
			case "ARROW_UP":
				robot.keyPress(KeyEvent.VK_KP_UP);
				break;
			case "ARROW_LEFT":
				robot.keyPress(KeyEvent.VK_KP_LEFT);
				break;
			case "ARROW_RIGHT":
				robot.keyPress(KeyEvent.VK_KP_RIGHT);
				break;*/
			ExecutionTime.stopOrPauseExecution();
			Actions action = new Actions(driver); 
			switch (keyString) {
			case "ENTER":
				action.sendKeys(Keys.ENTER);
				break;
			case "TAB":
				action.sendKeys(Keys.ENTER);
				break;
			case "ARROW_DOWN":
//				action.sendKeys(Keys.ARROW_DOWN);
				action.moveToElement(driver.findElement(By.xpath("//*[@id='ui-id-1']/li[1]"))).click();
				break;
			case "ARROW_UP":
				action.sendKeys(Keys.ARROW_UP);
				break;
			case "ARROW_LEFT":
				action.sendKeys(Keys.ENTER);
				break;
			case "ARROW_RIGHT":
				action.sendKeys(Keys.ENTER);
				break;
			
			
				
			}
			writeOutput("send key \"" + keyString
					+ "\" from keyboard is successfully:- Pass", testName);
			
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out.println("in exception---------------------sendkeys" + e);
			writeOutput("send keys \"" + keyString
					+ "\" from keyboard is not successfully:- Fail", testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : connectDatabaseWeb Description : Connect to
	 * database using connecting string Created by :   Created on :
	 * 8/5/2014
	 * #################################################################
	 * ###################
	 */
	public static void connectDatabaseWeb(String connectDatabaseString) {
		try {
			ExecutionTime.stopOrPauseExecution();
			String connectionURL[] = connectDatabaseString.split(";");
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://" + connectionURL[0];
			Properties props = new Properties();
			props.setProperty("user", connectionURL[1]);
			props.setProperty("password", connectionURL[2]);
			conn = DriverManager.getConnection(url, props);
			writeOutput("Database connection is successfully:- Pass", testName);
			
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out
					.println("in exception---------------------connectDatabase"
							+ e);
			writeOutput("Database connection is not successfully:- Fail",
					testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : disconnectDatabaseWeb Description : used to
	 * disconnect with database Created by :   Created on : 8/5/2014
	 * #
	 * #########################################################################
	 * ##########
	 */
	public static void disconnectDatabaseWeb() {
		try {
			ExecutionTime.stopOrPauseExecution();
			conn.close();
			writeOutput("Database disconnect is successfully:- Pass", testName);
			
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out
					.println("in exception---------------------disconnectDatabase"
							+ e);
			writeOutput("Database disconnect is not successfully:- Fail",
					testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : getDataFromDatabase Description : Get data from
	 * database Created by :   Created on : 8/5/2014
	 * ################
	 * ####################################################################
	 */
	public static void getDataFromDatabase(String query) {
		try {
			String name = "";
			ExecutionTime.stopOrPauseExecution();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from devices;");
			while (rs.next()) {
				name = rs.getString("name");
			}
			writeOutput("Get data from database" + name
					+ " is successfully:- Pass", testName);
			
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out
					.println("in exception---------------------disconnectDatabase"
							+ e);
			writeOutput("Get data from database is not successfully:- Fail",
					testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : verifyObjectbyIDWeb Description : verify the object
	 * exists on webpage using id locator Created by :   Created on
	 * : 9/23/2014
	 * ##############################################################
	 * ######################
	 */
	public static void verifyObjectbyIDWeb(String objectName) {
		try {
			driver.findElement(By.id(getTestObject(objectName)));
			writeOutput(
					"Object \"" + objectName + "\" is exist on page:- Pass",
					testName);
			
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out.println("in exception---------------------verifyObject"
					+ e);
			writeOutput("Object \"" + objectName
					+ "\" is not exist on page:- Fail", testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : verifyDropDownOptionFromDropdownByIDWeb Description
	 * : verify the dropdown item from drop down using id locator Created by :
	 *   Created on : 9/24/2014
	 * #####################################
	 * ###############################################
	 */
	public static void verifyDropDownOptionFromDropdownByIDWeb(String objectName,
			String dropDownServer) {
		try {
			Select group = new Select(driver.findElement(By
					.id(getTestObject(objectName))));
			List<WebElement> allElement = group.getOptions();
			boolean verifyElementsFromDropDown = false;
			for (int i = 0; i < allElement.size(); i++) {
				if (allElement.get(i).getText().contains(dropDownServer)) {
					verifyElementsFromDropDown = true;
					break;
				}
			}
			if (verifyElementsFromDropDown == true)
				writeOutput(
						"Dropdown option value \""
								+ dropDownServer
								+ "\" is matched with available options in dropdown:- Pass",
						testName);
			else {
				stepForwardExceptionFlag = 1;
				writeOutput("Dropdown option value \""+ dropDownServer+ "\" is not matched with available options in dropdown:- Fail",
						testName);
				closewindowCommon();
			}
			
			screnshotsForMytest();
		} catch (Exception e) {
			e.printStackTrace();
			stepForwardExceptionFlag = 1;
			System.out
					.println("in exception---------------------verifyDropDownOptionFromDropdownByID"
							+ e);
			writeOutput(
					"Dropdown option value \""
							+ dropDownServer
							+ "\" is not matched with available options in dropdown:- Fail",
					testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : firstFrameSelectWeb Description : first frame
	 * select Created by :   Created on : 02/12/2015
	 * ################
	 * ####################################################################
	 */
	public static void firstFrameSelectWeb() {
		try {
			driver.switchTo().frame(0);
			writeOutput("Frame is selected successfully:- Pass", testName);
			
			screnshotsForMytest();
		} catch (Exception e) {
			System.out
					.println("in exception---------------------firstFrameSelect"
							+ e);
			writeOutput(
					"Frame is not selected successfully and exception found:- Fail",
					testName);
			
			screnshotsForMytest();
		}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : switchToDefaultWeb Description : first frame select
	 * Created by :   Created on : 02/12/2015
	 * #######################
	 * #############################################################
	 */
	public static void switchToDefaultWeb() {
		try {

			driver.switchTo().defaultContent();
			writeOutput("Switch to default Frame successful:- Pass", testName);
			
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out
					.println("in exception---------------------switchToDefault"
							+ e);
			writeOutput(
					"Switch to default Frame is not successful and exception found:- Fail",
					testName);
			
			screnshotsForMytest();
			closewindowCommon();
		}
	}

	/*
	 * ================================================== ===========Function
	 * for screnshot=================
	 * ==================================================
	 */
	public static void screnshotsForMytest() {
//		String nameOfScreenshot = "";
//		nameOfScreenshot = "";
		ScreenshotBaselineCreationFlag = CONFIG.getProperty("ScreenshotBaselineCreationFlag");
		try {
			
//			if(ScreenshotBaselineCreationFlag.equalsIgnoreCase("y")||ScreenshotBaselineCreationFlag.equalsIgnoreCase("n"))
			
			if (screenShotflag.equals("Y") || screenShotflag.equals("y"))// take
																			// the
																			// screen
																			// shot
																			// only
																			// if
																			// it
																			// is
																			// "Y"
																			// for
																			// the
																			// test
																			// case
																			// in
																			// the
																			// Test
																			// Case
																			// sheet
			{
				
//				String folderPath;
				if (DataDrivenFlag.equals("Y") || DataDrivenFlag.equals("y")) {
					// folderPath = ExecutionTime.subFolderpathForExecution +
					// "/screenshots/"+ testName +
					// "^"+Framework.testCaseDescription + "^"
					// +Framework.subTestCaseDescription +"/";
					folderPath = ExecutionTime.subFolderpathForExecution
							+ "/screenshots/" + Framework.testCaseName + "_"
							+ Framework.subTestCaseDescription + "/";
					
					/*Baseline*/
					if (ScreenshotBaselineCreationFlag.equalsIgnoreCase("y")) {
						baselinefolderPath = ScreenshotMasterCopyPath
								+ Framework.testCaseName + "\\"
								+ Framework.subTestCaseDescription+"\\";
						System.out
								.println("In case of Data Driven as YES baseline creation path will be"
										+ baselinefolderPath);
					}
					/*Baseline End*/
				} else {
					// folderPath = ExecutionTime.subFolderpathForExecution +
					// "/screenshots/"+ testName +
					// "_"+Framework.testCaseDescription +"/";
					folderPath = ExecutionTime.subFolderpathForExecution
							+ "/screenshots/" + testName + "/";
					/*Baseline*/
					if (ScreenshotBaselineCreationFlag.equalsIgnoreCase("y")) {
						baselinefolderPath = ScreenshotMasterCopyPath
								+ testName + "\\";
						System.out
								.println("In case of Data Driven as NO baseline creation path will be"
										+ baselinefolderPath);
						
					}
					/*Baseline End*/
				}
				
//				Code for baseline folder creation
				if (ScreenshotBaselineCreationFlag.equalsIgnoreCase("y")) {
					File baselinefolderPath1 = new File(baselinefolderPath);
					  if(!baselinefolderPath1.exists()){
						  baselinefolderPath1.mkdirs();
					 }
				}
				
//				Code end for baseline folder creation
				
				
				File folder_browser = new File(folderPath);
				File[] s = folder_browser.listFiles();
				if (s == null && nameOfScreenshotFlag2 == 0) {
					nameOfScreenshot = "1.png";
					nameOfScreenshotFlag = 1;
				} else {
					nameOfScreenshot = nameOfScreenshotFlag + ".png";
				}
				File scrFile = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(folderPath
						+ nameOfScreenshot));
				
				/*Baseline*/
				if (ScreenshotBaselineCreationFlag.equalsIgnoreCase("y")) {
					FileUtils.copyFile(scrFile, new File(baselinefolderPath
							+ nameOfScreenshot));
				}
				/*BaselineEnd*/
							
				
			}
			nameOfScreenshotFlag++;
			
		} catch (Exception e) {
			nameOfScreenshotFlag++;
			System.out.println("in screen shot exception---");
		}
	}
	/*
	 * ##########################################################################
	 * ######## Function Name : selectIndexWebListByIDWeb Description : The function is
	 * used select item from dropdown using id locator Created by : Karan
	 * Arora
	 * ####################################################################
	 * ################
	 */
	public static void selectIndexWebListByIDWeb(String objectName, String index) {
		String Text = " ";
		String objectNAme=getTestObject(objectName);
		try {
			ExecutionTime.stopOrPauseExecution();
			WebElement dropdownElement=driver.findElement(By.id(objectNAme));
			System.out.println(dropdownElement.getText());
			System.out.println("Attribute---" +dropdownElement.isEnabled());
			if(dropdownElement.isEnabled())
			{
				Select dropdown = new Select(driver.findElement(By.id(objectNAme)));
				List<WebElement> dropDownValue=dropdown.getOptions();
				Text = dropDownValue.get(Integer.parseInt(index)).getText();
				dropdown.selectByIndex(Integer.parseInt(index));
				
				
				
				writeOutput("Item \"" + Text + "\" is selected in dropdown \"" + objectName + "\" successfully:- Pass", testName);
			}
			else
			{
				writeOutput("DropDown Field  "+ objectName  +" is Disabled:- Pass", testName);
			}
			
			screnshotsForMytest();
		} catch (Exception e) {
			e.printStackTrace();
			stepForwardExceptionFlag = 1;
			System.out.println("in exception-----------selectWebListByID" + e);
			writeOutput("Item \"" + Text
					+ "\" is not selected in dropdown \"" + objectName
					+ "\" successfully:- Fail", testName);
			screnshotsForMytest();
			closewindowCommon();
		}
	}
	/*
	 * ##########################################################################
	 * ######## Function Name : selectIndexWebListByxpathWeb Description : The function is
	 * used select item from dropdown using id locator Created by : Karan
	 * Arora
	 * ####################################################################
	 * ################
	 */
	public static void selectIndexWebListByxpathWeb(String objectName, String index) {
		try {
			ExecutionTime.stopOrPauseExecution();
			Select dropdown = new Select(driver.findElement(By
					.xpath(getTestObject(objectName))));
			
			dropdown.selectByIndex(Integer.parseInt(index));
			
			writeOutput("Item \"" + index
					+ "\" is selected in dropdown \"" + objectName
					+ "\" successfully:- Pass", testName);
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out.println("in exception-----------selectWebListByID" + e);
			writeOutput("Item \"" + index
					+ "\" is not selected in dropdown \"" + objectName
					+ "\" successfully:- Fail", testName);
			screnshotsForMytest();
			closewindowCommon();
		}
	}


	/*


	/*
	 * ##########################################################################
	 * ######## Function Name : clickByIDMibile Description : The function is
	 * used Click by id on mobile Created by :   Created On :
	 * 17/2/2015
	 * ################################################################
	 * ####################
	 */
	public static void clickByIDMibile(String objectName) {
		System.out.println("clickByIDMibile=====");
		try {
			ExecutionTime.stopOrPauseExecution();

			driver.findElement(By.id(getTestObject(objectName))).sendKeys(
					Keys.ENTER);
			;
			writeOutput(
					"click on \"" + objectName + "\" is successully:- Pass",
					testName);
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out.println("in exception clickByID ==" + e);
			writeOutput("click on \"" + objectName
					+ "\" is not successully:- Fail", testName);
			screnshotsForMytest();
			closewindowCommon();
		}
	}

	/*
	 * ================================================== ===========Function
	 * for SWIPE=================
	 * ==================================================
	 */
	public static void scrollClickTextAndroid(String textName) {
		System.out.println("scrollClickText=====");
		try {
			ExecutionTime.stopOrPauseExecution();

			((AndroidDriver) driver).scrollTo(textName).click();
			writeOutput("click on text  \"" + textName
					+ "\" is successully:- Pass", testName);
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out.println("in exception Scrollclick ==" + e);
			writeOutput("click on text\"" + textName
					+ "\" is not successully:- Fail", testName);
			screnshotsForMytest();
			closewindowCommon();
		}
	}
	/*
	 * ================================================== ===========Function
	 * for infinite scroll---Android=================
	 * ==================================================
	 */
	
	public static void infiniteScrollTextClickAndroid(String textName)
	{
		System.out.println("infiniteScrollTextClick=====");
		try {
			ExecutionTime.stopOrPauseExecution();
			while(true)
			 {	 
				 try{ 
					 	if(((AndroidDriver) driver).findElementByName(textName)!=null)
					 			{
					 		       ((AndroidDriver) driver).findElementByName(textName).click();
					 		       writeOutput("click on text  \"" + textName+ "\" is successully:- Pass", testName);
					 		       break;
					 			} 
				 }
				 catch(Exception e)
						 {
					 		Thread.sleep(1000); 
					 		((AndroidDriver) driver).swipe(750,2000,750,1000,800);
						 }
			 }
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out.println("in exception Scrollclick ==" + e);
			writeOutput("click on text\"" + textName+ "\" is not successully:- Fail", testName);
			screnshotsForMytest();
			closewindowCommon();
		}
		
		
	}

	public static void swipeAndroid(String textname) {
		System.out.println("swipe=====");
		try {
			String parameter[] = textname.split(",");
			int startx = Integer.parseInt(parameter[0]);
			int endx = Integer.parseInt(parameter[1]);
			int starty = Integer.parseInt(parameter[2]);
			int endy = Integer.parseInt(parameter[3]);
			int duration = Integer.parseInt(parameter[4]);
			ExecutionTime.stopOrPauseExecution();
			Thread.sleep(2000);
			((AndroidDriver) driver)
					.swipe(startx, endx, starty, endy, duration);
			// ((AndroidDriver) driver).swipe(5, 500, 1000, 500, 250);
			// ((AndroidDriver) driver).swipe(800, 2000, 800, 1300, 900);
			writeOutput("swipe is successully:- Pass", testName);
			screnshotsForMytest();
		} catch (Exception e) {
			stepForwardExceptionFlag = 1;
			System.out.println("in exception swipe ==" + e);
			writeOutput("swipe is not successully:- Fail", testName);
			screnshotsForMytest();
			closewindowCommon();
		}
	}

	public static void clickByTextAndroid(String textname){
	  try {
	  		ExecutionTime.stopOrPauseExecution();
	  		
	  		if( ((AndroidDriver) driver).findElementByName(textname).isEnabled())
			{
				((AndroidDriver) driver).findElementByName(textname).click();
				writeOutput("Click on \""+textname+"\"  is successful:- Pass", testName);
				screnshotsForMytest();
			}
	  		
	  	}
	  	catch(Exception e)
	  	{   
	  		stepForwardExceptionFlag = 1;
	  		System.out.println("in exception clickByText =="+e);
	  		writeOutput("click on \""+textname+"\" is not successully:- Fail", testName);
	  		screnshotsForMytest();
	  		closewindowCommon();
	  	}
	}

	/*
	 * ##########################################################################
	 * ######## Function Name : dashBoardWeb Description : The function is used for
	 * xmlparsing and matching with database Created by :   Created On
	 * : 17/2/2015
	 * ##############################################################
	 * ######################
	 */

	public static void dashBoardWeb(String objectName) throws IOException, InterruptedException {
		//DashBoardTest dbTest = new DashBoardTest();
		Boolean failPassDFlag=true;
		try {
			postgreSqlDbConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			failPassDFlag=false;
			e.printStackTrace();
		}

		/*System.setProperty("webdriver.chrome.driver",
		"D:\\Karan\\Selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://192.168.60.64/CCServer/");
		driver.findElement(By.id("btnLogInNew")).click();
		driver.findElement(By.id("txtUserName")).sendKeys("admin");
		driver.findElement(By.id("txtPassword")).sendKeys("system123#");
		driver.findElement(By.id("btnLogin")).click();

		// WebElement ele =
		// driver.findElement(By.xpath("//div[@id='chartDiv1']/embed[@id='ChartId']"));*/
		
		Thread.sleep(5000);
		String outputValue="";
		try {
		WebElement ele = driver.findElement(By.xpath(getTestObject(objectName)));
		String xml = ele.getAttribute("flashvars");
		System.out.println(xml);
		
		System.out.println("We are in Dashboard");
		xml = xml.substring(xml.indexOf("dataXML") + 8, xml.length());
//		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));

			Document doc = db.parse(is);
			Element docEle = doc.getDocumentElement();
			NodeList nl = docEle.getChildNodes();
			System.out.println("n1 subnodes : " + nl.getLength());
			if (nl != null && nl.getLength() > 0) {
				for (int i = 0; i < nl.getLength(); i++) {

					if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Element el = (Element) nl.item(i);
						if (el.getNodeName().contains("set")) {
							System.out.println(el.getAttribute("hoverText")
									+ " " + el.getAttribute("value"));
							String str = el.getAttribute("hoverText");
//							Integer dbValue = usrlg.get(str.toLowerCase());
							Integer dbValue = usrlg.get(str);
							outputValue=outputValue.concat("UserName= "+str+"--> DBValue = "+dbValue+" & DashBoardValue = "+el.getAttribute("value")+";%");
							if (Integer.parseInt(el.getAttribute("value")) == (dbValue)) {
								System.out.println(str
										+ "values got matched-------"
										+ "value in database  :" + dbValue
										+ "and value on DashBoard :"
										+ el.getAttribute("value"));
							} else {
								System.out.println(str
										+ "values not matched-------"
										+ "value in database  :" + dbValue
										+ "and value on DashBoard :"
										+ el.getAttribute("value"));
								failPassDFlag=false;
							}

						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ghdsfhdfsgdfs");
			failPassDFlag=false;
			e.printStackTrace();
		}
		/*catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		screnshotsForMytest();
		if(failPassDFlag==true)
		{ 
			 writeOutput("Comparison is successful for Top Five Users%"+outputValue+":- Pass", testName);
			 System.out.println("Comparison is successful for Top Five Users%");
		}
		else
		{
			writeOutput("Comparison is unsuccessful for Top Five Users%"+outputValue+":- Fail", testName);
		}

	}
	

	public static void postgreSqlDbConnection() throws SQLException
	{ 
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		/*cal.set(Calendar.SECOND, +59);
		cal.set(Calendar.MINUTE, +59);
		cal.set(Calendar.HOUR, +11);*/
		String currentDate= dateFormat.format(cal.getTime());
		
		System.out.println(currentDate); 
		cal.add(Calendar.MONTH, -1);
		cal.add(Calendar.DATE, -1);
		/*cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR, -12);*/
		String backDate=dateFormat.format(cal.getTime());
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Include postgre sql JDBC driver in your library path!");
			e.printStackTrace();
		}
			conn = DriverManager.getConnection(DataBaseURL,"postgres", "system123#");
			if(conn!=null){
				System.out.println("connection successful ");
			}
			System.out.println("Code is still running");  		
		Statement st = conn.createStatement();
//		ResultSet rs = st.executeQuery("select distinct(sender),sum(Pages) from userlog where outcome='Success' and  action='PrintDocument' and  log_time > '" + backDate +" ' and log_time <'"+currentDate+"' Group By sender");
		ResultSet rs = st.executeQuery("SELECT upper(username), sum(prints)FROM devicelog  WHERE upper(outcome) = 'SUCCESS' AND log_time BETWEEN '"+backDate+"' AND '"+currentDate+"' GROUP BY upper(username) LIMIT(5)");
		
		
		
		
		while ( rs.next() )
	      {   
	    	  nameFromDb=rs.getString(1);
	    	  valueFromDb= rs.getInt(2);
	    	  usrlg.put(nameFromDb, valueFromDb);
	      }
	      for(Map.Entry m:usrlg.entrySet())
	      	  {  
	    	   System.out.println(m.getKey()+" "+m.getValue());  
	    	  } 
	      rs.close();
	      st.close(); 
		conn.close();
		
	}
	/*
	 * ##########################################################################
	 * ######## Function Name : screenshotCompareWeb Description : The function is used for
	 * comparing the screenshots pixelwise Created by :   Created On
	 * : 21/3/2015
	 * ##############################################################
	 * ######################
	 */
	
	public static void screenshotCompareWeb()
	{
		Boolean failPassSCFlag= true;
		Path path ;
		File file;
		
//		Path path = Paths.get("D:\\Screenshot1Dir");
		
		
        try
        {
        	if(!DataDrivenFlag.equalsIgnoreCase("Y"))
        	{
        		path = Paths.get(ScreenshotMasterCopyPath+testName);
        	}
        	else
        	{
        		 path = Paths.get(ScreenshotMasterCopyPath+Framework.testCaseName + "\\"
							+ Framework.subTestCaseDescription+"\\");
        		 System.out.println("Path of subTestCase is :-" +path.toString());
        	}
        	String pathString = path.toString();
        	File directoryPath = new File(pathString);
        	if(directoryPath.exists())
        	{
        	
    		Path path1 = Paths.get(folderPath);
            DirectoryStream<Path> originalStream;
            DirectoryStream<Path> newStream;
            newStream = Files.newDirectoryStream(path1);

            System.out.println(new File(folderPath).listFiles().length);
            System.out.println("No of files present in Baseline folder is :-"+ new File(ScreenshotMasterCopyPath+testName).listFiles().length );
//            if(new File(ScreenshotMasterCopyPath+testName).listFiles().length==0)
            if(new File(path.toString()).listFiles().length==0)
            {
            	failPassSCFlag=false;
            	
            	 screnshotsForMytest();
         			writeOutput("No file exist in the Baseline folder:- UIFail", testName);
         		
            }
            else
            {
            
            for (Path entry : newStream)
            {
//            	System.out.println(entry.);
            	 originalStream = Files.newDirectoryStream(path);
            	 
                System.out.println("All the filenames in upcoming folder(new folder) :-" +entry.getFileName().toString());
                for (Path entry1 : originalStream)
                {
                	String str = entry1.getFileName().toString();
                	
                	 System.out.println("All the filenames of master folder(str)" +str);
                if(str.equals(entry.getFileName().toString())){
                	System.out.println("File name Matched");
      /*Starting Comparing the images pixelwise*/
                
                long start = System.currentTimeMillis();
                int q=0;
                    File file1 = new File("filename.txt");

                /* if file doesnt exists, then create it
                if (!file.exists()) {
                            file.createNewFile();
                        }*/
                FileWriter fw = new FileWriter(file1.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                if(!DataDrivenFlag.equalsIgnoreCase("Y"))
                         file= new File(ScreenshotMasterCopyPath + testName +"\\"+str);
                else
                	     file= new File(ScreenshotMasterCopyPath + testName +"\\"+ Framework.subTestCaseDescription+ "\\" +str);
                    BufferedImage image = ImageIO.read(file);
                int width = image.getWidth(null);
                    int height = image.getHeight(null);
                int[][] clr=  new int[width][height]; 
//                File files= new File("D:\\Screenshot2Dir\\" +entry.getFileName().toString());
                File files= new File(folderPath +entry.getFileName().toString());
                    BufferedImage images = ImageIO.read(files);
                int widthe = images.getWidth(null);
                    int heighte = images.getHeight(null);
                int[][] clre=  new int[widthe][heighte]; 
                int smw=0;
                int smh=0;
                int p=0;
                    //CALUCLATING THE SMALLEST VALUE AMONG WIDTH AND HEIGHT
                    if(width>widthe)
                    { 
                        smw =widthe;
                    }
                    else 
                    {
                        smw=width;
                    }
                    if(height>heighte)
                    {
                        smh=heighte;
                    }
                    else 
                    {
                        smh=height;
                    }
                    //CHECKING NUMBER OF PIXELS SIMILARITY
                    for(int a=0;a<smw;a++)
                    {
                        for(int b=0;b<smh;b++)
                        {
                            clre[a][b]=images.getRGB(a,b);
                            clr[a][b]=image.getRGB(a,b);
                            if(clr[a][b]==clre[a][b]) 
                            {
                                p=p+1;
                                bw.write("\t");
                                 bw.write(Integer.toString(a));
                                bw.write("\t");
                                 bw.write(Integer.toString(b)); 
                                bw.write("\n");
                            }
                            else
                                q=q+1;
                        }
                    }

            float w,h=0;
            if(width>widthe) 
            {
                w=width;
            }
            else 
            {
                w=widthe;
            }
            if(height>heighte)
            { 
                h = height;
            }
            else
            {
                h = heighte;
            }
            float s = (smw*smh);
            //CALUCLATING PERCENTAGE
            float x =(100*p)/s;

            System.out.println("THE PERCENTAGE SIMILARITY IS APPROXIMATELY ="+x+"%");
            long stop = System.currentTimeMillis();
            System.out.println("TIME TAKEN IS ="+(stop-start));
            System.out.println("NO OF PIXEL GETS VARIED:="+q);
            System.out.println("NO OF PIXEL GETS MATCHED:="+p);
            
            if(x<99)
            {
            	System.out.println("x is equal to :" +x);
            	System.out.println("Images does not match completely");
            	failPassSCFlag = false;
            }
            else {
        		System.out.println("Images matched");
        	}
                	/*Ending of comparison*/
//                	break;
                }
                }
            }
//            originalStream.close();
//            newStream.close();
            
            screnshotsForMytest();
            if(failPassSCFlag==true)
    		{ 
    			 writeOutput("screenshot Comparison match successful:- Pass", testName);
    		}
    		else
    		{
    			writeOutput("screenshot Comparison did not match:- UIFail", testName);
    			
    			
    		}
            }
        	}
        	else
        	{
        		System.out.println("Baseline folder doesn't exists");
        		writeOutput("Baseline folder doesn't exist,Please create it first :- Fail", testName);
        	}
        }
        catch (IOException e)
        {
            e.printStackTrace();
            
            screnshotsForMytest();
        }
    		
        }
	static void defaultFunctionCommon(String stepAction)
	{
		try
		{ 
			 writeOutput("Terminating test case "+ stepAction +" stepAction does not exits in Framework:- Fail", testName);
			 ExecutionTime.stopExecutionflag=true;
			 closewindowCommon();	 
		}
		catch(Exception e)
		{
			closewindowCommon();
		}
	}
        
	
	/*
	 * ##########################################################################
	 * ######## Function Name : baselineCreation Description : The function is used for
	 * creating the baseline of screenshots pixelwise Created by :   Created On
	 * : 21/3/2015
	 * ##############################################################
	 * ######################
	 */
	/*public static void baselineCreation()
	{
		nameOfScreenshot = "";
		ScreenshotBaselineCreationFlag = CONFIG.getProperty("ScreenshotBaselineCreationFlag");
		try {
			if(ScreenshotBaselineCreationFlag.equalsIgnoreCase("y"))
			{
				if (screenShotflag.equals("Y") || screenShotflag.equals("Y"))
				{
//					String folderPath;
					if (DataDrivenFlag.equals("Y") || DataDrivenFlag.equals("Y")) {
						
						folderPath = ExecutionTime.subFolderpathForExecution
								+ "/screenshots/" + Framework.testCaseName + "_"
								+ Framework.subTestCaseDescription + "/";
						baselinefolderPath = ScreenshotMasterCopyPath+Framework.testCaseName + "\\"
								+ Framework.subTestCaseDescription;
						System.out.println("In case of Data Driven as YES baseline creation path will be"+baselinefolderPath);
					} else {
						folderPath = ExecutionTime.subFolderpathForExecution
								+ "/screenshots/" + testName + "/";
						baselinefolderPath = ScreenshotMasterCopyPath+testName+"\\";
						System.out.println("In case of Data Driven as NO baseline creation path will be"+baselinefolderPath);
					}
//					Code for baseline folder creation
					File baselinefolderPath1 = new File(baselinefolderPath);
					  if(!baselinefolderPath1.exists()){
						  baselinefolderPath1.mkdirs();
					 }
//					Code end for baseline folder creation
					
					
					File folder_browser = new File(baselinefolderPath);
					File[] s = folder_browser.listFiles();
					if (s == null && nameOfScreenshotFlag2 == 0) {
						nameOfScreenshot = "1.png";
						nameOfScreenshotFlag = 1;
					} else {
						nameOfScreenshot = nameOfScreenshotFlag + ".png";
					}
					File scrFile = ((TakesScreenshot) driver)
							.getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(baselinefolderPath
							+ nameOfScreenshot));
				}
				
			}
			nameOfScreenshotFlag++;
    	
    	}
		catch (Exception e)
        {
            e.printStackTrace();
            
        }
	
	}*/
	
	
	/*
	 * ##########################################################################
	 * ######## Function Name : handleWindowPopUpWeb Description : The function is
	 * used for handling the window pop up Created by :   Created On :
	 * 25/5/2016
	 * ################################################################
	 * ####################
	 */
	public static void handleWindowPopUpWeb() {
		System.out.println("handling window pop up");
		try {
			Runtime.getRuntime().exec("D:\\autoit\\FileUpload4.exe"+" "+"D:\\autoit\\pathexist.txt");
			
            writeOutput("Windows pop up has been handled successfully:- Pass",testName);
			
		} catch (Exception e) {
			/*stepForwardExceptionFlag = 1;
			System.out.println("in exception clickByID ==" + e);
			writeOutput("click on \"" + objectName
					+ "\" is not successully:- Fail", testName);
			screnshotsForMytest();
			closewindow();*/
			e.printStackTrace();
		}
	}
	 
	

/*Click By Xpath Android*/
public static void clickByXpathWebAndroid(String objectName) {
	try {
		if(!OSNameFromGeneric.equalsIgnoreCase("ANDROID_app"))
		pageTitle = driver.getTitle();
		ExecutionTime.stopOrPauseExecution();
		String getTestObject = getTestObject(objectName);
		if (OSNameFromGeneric.equalsIgnoreCase("Window 7"))
		{
			WebElement idWebElement = driver.findElement(By.id(getTestObject));
			/*js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].setAttribute('style','border: solid 2px red;background-color: Yellow')", idWebElement);*/
			System.out.println("asda");
			idWebElement.click();
			waitCommon("1000");
			if (isAlertPresent()== true){
				System.out.println("in alert handle for login");
				Alert alert = Keyword.driver.switchTo().alert();
				alert.accept();
				Keyword.driver.switchTo().defaultContent();
				}
		}	
		else if (OSNameFromGeneric.equalsIgnoreCase("ANDROID_app"))
			System.out.println("test");
			((AndroidDriver) driver).findElementByXPath(getTestObject).click();
		
		writeOutput(
				"Click on \"" + objectName + "\"  is successful:- Pass",
				testName);
		screnshotsForMytest();
	} catch (Exception e) {
		stepForwardExceptionFlag = 1;
		writeOutput("Click on \"" + objectName
				+ "\" is not successful:- Fail", testName);
		e.printStackTrace();
		screnshotsForMytest();
		    closewindowCommon();
	}
}
public static void verifyTextByXpathAndroid(String objectName, String text) {
	String bodyText = null;
	js=(JavascriptExecutor)driver;
	WebElement element=driver.findElement(By.xpath(getTestObject(objectName))); 
	try {
		ExecutionTime.stopOrPauseExecution();
		bodyText = element.getText();
		if(!OSNameFromGeneric.equalsIgnoreCase("ANDROID_app"))
		    js.executeScript("arguments[0].setAttribute('style','border: solid 2px red;background-color: Yellow')", bodyText);
		Thread.sleep(2000);
		if (bodyText.equalsIgnoreCase(text))
			writeOutput("Actual Text \"" + bodyText
					+ "\" matches the Expected Text \"" + text
					+ "\" :- Pass", testName);
		else {
			System.out.println("Actual Text \"" + bodyText
					+ "\" does not match Expected Text \"" + text
					+ "\" :- UIFail");
			writeOutput("Actual Text \"" + bodyText
					+ "\" does not match Expected Text \"" + text
					+ "\" :- UIFail", testName);
		}
		screnshotsForMytest();
		if(!OSNameFromGeneric.equalsIgnoreCase("ANDROID_app"))
		       js.executeScript("arguments[0].setAttribute('style','border: solid 2px White;background-color: White')", bodyText);
	} catch (Exception e) {
		System.out
				.println("in exception---------------------Textvalidationbyxpath"
						+ e);
		writeOutput("Actual Text \"" + bodyText
				+ "\" does not match Expected Text \"" + text
				+ "\" :- Fail", testName);
		e.printStackTrace();
		screnshotsForMytest();
		if(!OSNameFromGeneric.equalsIgnoreCase("ANDROID_app"))
		       js.executeScript("arguments[0].setAttribute('style','border: solid 2px White;background-color: White')", bodyText);
	}
}


}
