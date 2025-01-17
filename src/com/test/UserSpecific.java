package com.test;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Properties;
import java.util.Set;












import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;










/*===================LIBRARY FOR WEB SERVICE TESTING ===========================*/
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
public class UserSpecific  {

	public String clsName;
	public File file;
	public static Keyword r;
	public String text;
	public String[] myStringArray;
	public static Properties CONFIG = null;
	public static Integer sumActualvalue=0;
	public static Integer sumPlannedvalue=0;
	
	/*public UserSpecific() 
	{  
		try{
		CONFIG = new Properties();
		FileInputStream webService = new FileInputStream(
				System.getProperty("user.dir")
						+ "/Config/WebService.Properties");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
	}*/

	/*##################################################################################
	Function Name : clickWithHandleAlertWeb
	Description : This function is used click on login button with alert handle
	Created by : 
	####################################################################################*/
	public static void clickWithHandleAlertWeb() {
		
			//Keyword.driver.findElement(By.id(Keyword.getTestObject(objectName))).click();
			if (isAlertPresent()== true){
				/*System.out.println("in alert handle for login");
				Alert alert = Keyword.driver.switchTo().alert();
				alert.accept();
				Keyword.driver.switchTo().defaultContent();*/
				try{
					Alert alrt = Keyword.driver.switchTo().alert();
					String alrtText = alrt.getText();
					System.out.println("Text on alert is:--------" +alrtText);
					alrt.sendKeys("AlertText");
					
					alrt.accept();
					alrt.dismiss();
					
//					Keyword.writeOutput("JS Alert has been handled successfully :- Pass", Keyword.testName);
					Keyword.screnshotsForMytest();
					}
					catch(Exception e)
					{
						System.out.println("In screenshotException");
					}
				}
			/*Keyword.writeOutput("popup handled successfully:- Pass", Keyword.testName);
			
			Keyword.screnshotsForMytest();*/
		
		/*catch(Exception e)
		{
			System.out.println("in exceptionClickonlinkbyID=="+e);
			Keyword.writeOutput("popup not handled:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}*/
	}
	
	public static boolean isAlertPresent() 
	  { 
	      try 
	      { 
	    	  Keyword.driver.switchTo().alert(); 
	          return true; 
	      } 
	      catch (NoAlertPresentException Ex) 
	      { 
	          return false; 
	      }
	  }
	
	/*##################################################################################
	Function Name : selectFromQueueList
	Description : This function is used to click on Queue from Queue list
	Created by : 
	Application : OmniFlow
	objectName : object of table
	listName : name on which you want to click
	Date : 2 December 2015
	####################################################################################*/
	public static void selectFromQueueListWeb(String objectName, String listName) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			List<WebElement> QueueRow = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			 for(WebElement QL : QueueRow ) {
				 String queueText = QL.findElement(By.xpath("./td/a/span")).getText();	
				 if(queueText.contains(listName)){
					 System.out.println("found=---");
					 matchedItem = true;
					 QL.findElement(By.xpath("./td/a")).click();		 
					 break;
				 }
			 }
			if(matchedItem==true)
				Keyword.writeOutput("Click on \""+objectName+"\" is successful:- Pass", Keyword.testName);
			else
				Keyword.writeOutput("Click on \""+objectName+"\" is not successful:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("in selectFromQueueList=="+e);
			Keyword.writeOutput("Click on \""+objectName+"\" is not successful and exception found:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
			
		}
	}
	
	/*##################################################################################
	Function Name : navigateWindowWithTitleWeb
	Description : This function is used to navigate on another window by title
	Created by : 
	Application : OmniFlow
	titleName : title name on which you want to navigate
	Date : 2 December 2015
	####################################################################################*/
	public static void navigateWindowWithTitleWeb(String titleName) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			 Set<String> allWindowsHandle = Keyword.driver.getWindowHandles(); // get all windows
			    for (String window : allWindowsHandle) {
			    	 
			    	if (Keyword.driver.switchTo().window(window).getTitle().contains(titleName)) {            
			    		Keyword.driver.switchTo().window(window);
			            System.out.println("Break----");
			            matchedItem = true;
			            break;
			               }
			           }
			if(matchedItem==true)
				Keyword.writeOutput("Navigate to window with title \""+titleName+"\" is successful:- Pass", Keyword.testName);
			else
				Keyword.writeOutput("Navigate to window with title \""+titleName+"\" is not successful:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("in navigateWindowWithTitle=="+e);
			Keyword.writeOutput("Navigate to window with title \""+titleName+"\" is not successful and exception found:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	
	
	/*##################################################################################
	Function Name : navigateWindowWithTitleAndSelectFromPickListWeb
	Description : This function is used to navigate on another window and select item from pick list
	Created by : 
	Application : OmniFlow
	listName : list name which you want to select
	Date : 2 December 2015
	####################################################################################*/
	public static void navigateWindowWithTitleAndSelectFromPickListWeb(String listName) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String windowsBefore = Keyword.driver.getWindowHandle();
		    Set<String> windowsPicker = Keyword.driver.getWindowHandles();
		    for (String windowp : windowsPicker) {
		    	System.out.println("Title-----"+ Keyword.driver.switchTo().window(windowp).getTitle());
		    	if (Keyword.driver.switchTo().window(windowp).getTitle().equals("Pick List")) {  
		    		matchedItem = true;
		    		Keyword.driver.switchTo().window(windowp);
		               break;
		            }
		        }
		       WebElement mySelectElm = Keyword.driver.findElement(By.id("itemList"));
		       Select se=new Select(mySelectElm);
		       se.selectByVisibleText(listName);
		       Thread.sleep(2000);
		       Keyword.driver.findElement(By.id("btn_OK")).click(); 
		       Keyword.driver.switchTo().window(windowsBefore);
			if(matchedItem==true)
				Keyword.writeOutput("Item \""+listName+"\" is selected from Pick list successfully:- Pass", Keyword.testName);
			else
				Keyword.writeOutput("Item \""+listName+"\" is not selected from Pick List successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("in navigateWindowWithTitleAndSelectFromPickList=="+e);
			Keyword.writeOutput("Item \""+listName+"\" is not found in Pick List and exception found:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	
	/*##################################################################################
	Function Name : saveFlowWeb
	Description : This function is used to save the omniflow
	Created by : 
	Application : OmniFlow
	Date : 2 December 2015
	####################################################################################*/
	public static void saveFlowWeb() {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			 Robot rb = new Robot();
			    rb.keyPress(KeyEvent.VK_CONTROL);
			    rb.keyPress(KeyEvent.VK_F4);
			    rb.keyRelease(KeyEvent.VK_CONTROL);
			    Thread.sleep(3000);
			    rb.keyPress(KeyEvent.VK_ENTER);
			    rb.keyRelease(KeyEvent.VK_ENTER); 
			    Thread.sleep(2000);

			Keyword.writeOutput("Omniflow is saved successfully:- Pass", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("in saveFlow=="+e);
			Keyword.writeOutput("Omniflow is not saved successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}	
	
	/*##################################################################################
	Function Name : VerifyItemFromMenuListWeb
	Description : The function is used to verify the item from menu list
	Created by :       ///=========================simple
	####################################################################################*/
	public static void VerifyItemFromMenuListWeb(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";
			List<WebElement> listCandidate = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			for( WebElement Candidate: listCandidate){		
				if (Candidate.getText().contains(item)){
					actualText =Candidate.getText();
					matchedItem=true;	
					break;
				}
			}
			if(matchedItem==true)
					Keyword.writeOutput("Expected text \""+item+"\" is matched with actual text \""+actualText+"\" successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text \""+actualText+"\" successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	
	
	/*##################################################################################
	Function Name : selectPositionWeb
	Description : The function is used to select the position
	Created by :     ///=========================simple
	####################################################################################*/
	public static void selectPositionWeb(String objectName, String positionName) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			List<WebElement> listPosition = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			for( WebElement position: listPosition){		
				if (position.getText().contains(positionName)){
					position.findElement(By.tagName("a")).click();
					matchedItem=true;	
					break;
				}
			}
			if(matchedItem==true)
					Keyword.writeOutput("Position \""+positionName+"\" is selected successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Position \""+positionName+"\" is not selected successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("Exception in selectPosition=="+e);
			Keyword.writeOutput("Position \""+positionName+"\" is not selected successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	
	/*##################################################################################
	Function Name : holdPositionWeb
	Description : The function is used to verify the candidate in position and change the status into on-hold
	Created by :     ///=========================complex
	####################################################################################*/
	public static void holdPositionWeb(String objectName) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			List<WebElement> listCandidate = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			if(listCandidate.size()>1){
				matchedItem = true;
				//System.out.println("there is candidates exist");
			}				
			Keyword.driver.findElement(By.xpath("//div[@class='dropdown ng-scope']/a")).click();
			Thread.sleep(2000);
			Keyword.driver.findElement(By.xpath("//span[contains(text(),'On-hold')]")).click();		
			if (matchedItem==true){
			Thread.sleep(2000);
			Keyword.driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
			Keyword.writeOutput("Status of Position and its candidates get change to On-hold successfully:- Pass", Keyword.testName);
			}else{
			Keyword.writeOutput("Status of Position get change to On-hold successfully:- Pass", Keyword.testName);
			}
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("Exception in holdPosition=="+e);
			Keyword.writeOutput("Status of Position does not get change to On-hold successfully:- Pass", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}

	/*##################################################################################
	Function Name : duplicateCandidateWeb
	Description : The function is used to keep duplicate candidate
	Created by :      ///=========================simple
	####################################################################################*/
	public static void duplicateCandidateWeb(String objectName) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			List<WebElement> listCandidate = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			if(listCandidate.size()>=1){
				matchedItem = true;
				System.out.println("duplicate candidate exist");
			}				
		
			if (matchedItem==true){
			Keyword.driver.findElement(By.xpath(Keyword.getTestObject(objectName))).click();
			Keyword.writeOutput("Candidate with similar details is found, keep this candidate successfully:- Pass", Keyword.testName);
			}else{
				Keyword.writeOutput("Duplicate candidate is not found:- Pass", Keyword.testName);
			}
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("Exception in holdPosition=="+e);
			Keyword.writeOutput("Candidate with similar details is found but this candidate is not proceed successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	
	/*##################################################################################
	Function Name : verifyInterviewQuestionByjquery
	Description : The function is used to get interview question through jquery and verify it.
	Created by :    ///=========================complex
	####################################################################################*/
	public static void verifyInterviewQuestionByjquery(String objectName, String interviewQuestion) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String theTextIWant = "";
			int index = 0;
		//	Thread.sleep(3000);
		//	JavascriptExecutor jse = (JavascriptExecutor)Keyword.driver;
			List<WebElement> listquestions = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			System.out.println("listquestions---------------->>>>"+listquestions.size()); 
			for( WebElement question: listquestions){		
				theTextIWant = ((JavascriptExecutor) Keyword.driver).executeScript("return angular.element('div.questionMain:eq("+index+") textarea').val();").toString();
				System.out.println("telno1----------------------"+theTextIWant);
				System.out.println("question----------------------"+question);
				index++;	
				if(theTextIWant.contains(interviewQuestion)){
					matchedItem = true;
					break;
				}
				}
			
			if (matchedItem==true)
				Keyword.writeOutput("Expected Interview question \""+interviewQuestion+"\" is matched with actual interview question \""+theTextIWant+"\" successfully:- Pass", Keyword.testName);
			else
				Keyword.writeOutput("Expected Interview question \""+interviewQuestion+"\" is not matched with actual interview question \""+theTextIWant+"\" successfully:- Fail", Keyword.testName);
			Thread.sleep(1000);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("verifyInterviewQuestionByjquery=="+e);
			Keyword.writeOutput("Expected Interview question \""+interviewQuestion+"\" is not matched with actual interview question:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	/*##################################################################################
	Function Name : clickItemFromAutoListWeb
	Description : This function is used to click on Automatic field List
	Created by : Karan Arora
	Application : Dploy
	objectName : object of Automatic field List
	listName : name on which you want to click
	Date : 2 AUG 2016
	####################################################################################*/
	public static void clickItemFromAutoListWeb(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";
			List<WebElement> users = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			for( WebElement user: users){
				System.out.println(user.getText() +"------------"+ user.getText().contains(item));
				
				if (user.getText().contains(item))
				{
					actualText=user.getText();
					Keyword.driver.findElement(By.xpath("//ul/li[text()='"+ user.getText() +"']")).click();
					matchedItem=true;	
					break;
				}
			}
			if(matchedItem==true)
					Keyword.writeOutput("Expected text \""+item+"\" is matched with actual text \""+actualText+"\" successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text \""+actualText+"\" successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	/*##################################################################################
	Function Name : clickItemFromMetricListWeb
	Description : This function is used to click on Metric List
	Created by : Karan Arora
	Application : Dploy
	objectName : object of Metric List
	listName : name on which you want to click
	Date : 3 AUG 2016
	####################################################################################*/
	public static void clickItemFromMetricListWeb(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";
			List<WebElement> users = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			for( WebElement user: users){
				System.out.println(user.findElement(By.xpath("./td[3]/div[1]/span[2]")).getText() +"------------"+ user.getText().contains(item));
				
				if (user.findElement(By.xpath("./td[3]/div[1]/span[2]")).getText().contains(item))
				{
					actualText=user.findElement(By.xpath("./td[3]/div[1]/span[2]")).getText();
					user.findElement(By.xpath("./td[3]/div[1]/span[2]")).click();
					matchedItem=true;	
					break;
				}
			}
			if(matchedItem==true)
					Keyword.writeOutput("Expected text \""+item+"\" is matched with actual text \""+actualText+"\" successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text \""+actualText+"\" successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	/*##################################################################################
	Function Name : moveToListWeb
	Description : This function is used to click on moveTo List
	Created by : Karan Arora
	Application : Dploy
	objectName : object of MOVETO List
	listName : name on which you want to click
	Date : 26 AUG 2016
	####################################################################################*/
	public static void moveToListWeb(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			Integer count=0;
			Integer Increment=1;
			String actualText= "";
			List<WebElement> users = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			for( WebElement user: users){
				count++;
				
				if(count==(2*Increment+1))
			{  
					System.out.println(user.findElement(By.xpath("./a")).getText() +"------------"+ user.findElement(By.xpath("./a")).getText().trim().equals(item.trim()));
				if (user.findElement(By.xpath("./a")).getText().contains(item))
				{
					actualText=user.findElement(By.xpath("./a")).getText();
					user.findElement(By.xpath("./a")).click();
					matchedItem=true;	
					break;
				}
				Increment++;
			 }
			}	
			if(matchedItem==true)
					Keyword.writeOutput("Expected text \""+item+"\" is matched with actual text \""+actualText+"\" successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text \""+actualText+"\" successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	
	/*##################################################################################
	Function Name : clickItemFromWidgetListWeb
	Description : This function is used to click on Widget List
	Created by : Karan Arora
	Application : Dploy
	objectName : object of Widget List
	listName : name on which you want to click
	Date : 3 AUG 2016
	####################################################################################*/
	public static void clickItemFromWidgetListWeb(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";
			String excel=item;
			List<WebElement> widgets = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			for( WebElement widget: widgets){
				
				String onSite=widget.findElement(By.xpath("./td[4]/div/span")).getText().toString();
				actualText=onSite;
				System.out.println("onSite :" + onSite);
				System.out.println("onSite.trim().equals(item.trim())" + onSite.trim().equals(item.trim()));
	
				if(onSite.trim().equals(item.trim()))
				{
					widget.findElement(By.xpath("./td[2]")).click();
					matchedItem=true;	
					break;
				}
			}
			if(matchedItem==true)
					Keyword.writeOutput("Expected text \""+item+"\" is matched with actual text \""+actualText+"\" successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text \""+actualText+"\" successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	/*##################################################################################
	Function Name : dropdownListtWeb
	Description : This function is used to click on Widget List
	Created by : Karan Arora
	Application : Dploy
	objectName : object of dropdown List
	listName : name on which you want to click
	Date : 8 AUG 2016
	####################################################################################*/
	public static void dropdownListtWeb(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= " ";

			List<WebElement> roots = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			  int rootSize = 1;
			  for(WebElement root: roots)
			  {
			   String openRoot = root.getAttribute("aria-expanded");
			   System.out.println("openroot :"+ openRoot);
			   System.out.println("childRoot text" + root.findElement(By.xpath("./div/span[2]")).getText());
			     if(root.findElement(By.xpath("./div/span[2]")).getText().contains(item))
			     {
			    	 actualText=root.findElement(By.xpath("./div/span[2]")).getText();
			    	 root.findElement(By.xpath("./div/span[2]")).click();
			    	 Keyword.driver.findElement(By.id("btnAMSelect")).click();
			    	 matchedItem=true;
			    	 break;
			     }
			     else
			     {
			    	 root.findElement(By.xpath("./div/span[1]")).click();	 
			     } 
			     List<WebElement> childRoots =root.findElements(By.xpath("./ul/li"));
			    int subRootSize = 1;
			    for(WebElement childRoot:childRoots)
			    {
			     String openSubRoot = childRoot.getAttribute("aria-expanded");
			        System.out.println("childRoot text" + childRoot.findElement(By.xpath("./div/span/span")).getText());
			    	 if(childRoot.findElement(By.xpath("./div/span/span")).getText().contains(item))
			         {
			    		 System.out.println("TEXT :" + childRoot.findElement(By.xpath("./div/span/span")).getText());
			    		 actualText=root.findElement(By.xpath("./div/span/span")).getText();
			    		 Keyword.driver.findElement(By.id("btnAMSelect")).click();			    		 
			    		 matchedItem=true;
			    		 break;
			         }
			     subRootSize = subRootSize+1;
			   }
			    root.findElement(By.xpath("./div/span/span")).click();
			   rootSize = rootSize+1;
			  }
			if(matchedItem==true)
					Keyword.writeOutput("Expected text \""+item+"\" is matched with actual text \""+actualText+"\" successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text \""+actualText+"\" successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}/*public static void dropdownListtWeb(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";

			List<WebElement> roots = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			  int rootSize = 1;
			  for(WebElement root: roots)
			  {
			   String openRoot = root.getAttribute("aria-expanded");
			   System.out.println("openroot :"+ openRoot);

			     if(root.findElement(By.xpath("./div/span[2]")).getText().contains(item))
			     {
			    	 root.findElement(By.xpath("./div/span[2]")).click();
			    	 matchedItem=true;
			    	 break;
			     }
			     else
			     {
			    	 root.findElement(By.xpath("./div/span[1]")).click();
			    	 
			     } 
			     List<WebElement> childRoots =root.findElements(By.xpath("./ul/li"));
			    int subRootSize = 1;
			    for(WebElement childRoot:childRoots)
			    {
			     String openSubRoot = childRoot.getAttribute("aria-expanded");
			    
			    	 if(childRoot.findElement(By.xpath("./div/span[2]")).getText().contains(item))
			         {
			    		 childRoot.findElement(By.xpath("./div/span[2]")).click();
			    		 matchedItem=true;
			    		 break;
			         }
			   
			     subRootSize = subRootSize+1;
			   }
			   rootSize = rootSize+1;
			  }
			if(matchedItem==true)
					Keyword.writeOutput("Expected text \""+item+"\" is matched with actual text \""+actualText+"\" successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text \""+actualText+"\" successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}*/
	/**/
	/*##################################################################################
	Function Name : attachmentdelete
	Description : This function is used to delete attachment
	Created by : Karan Arora
	Application : Dploy
	objectName : object of filtered List 
	listName : name of which you want to assert
	Date : 16 AUG 2016
	pre-condition :  used after update
	####################################################################################*/
	public static void attachmentdelete(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";
			String excel=item;
			List<WebElement> attachments = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			for( WebElement attachment:attachments){
				System.out.println(" text inside div" +  attachment.findElement(By.xpath("./td[3]/div/a")).getText());
				System.out.println(attachment.findElement(By.xpath("/td[3]/div/a")).getText() +"------------"+ attachment.findElement(By.xpath("./td[2]/div")).getText().trim().startsWith(item));
				
				if (attachment.findElement(By.xpath("./td[3]/div/a")).getText().trim().equals(item))
				{
					System.out.println("got inside matched");
					attachment.findElement(By.xpath(".td[1]/input")).click();
					matchedItem=true;	
				}
			}
			if(matchedItem==false)
					Keyword.writeOutput("Expected text \""+item+"\" is deleted successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not deleted successfully:- Fail", Keyword.testName);
			
			    Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	
	/*##################################################################################
	Function Name : dashBoardAssertion
	Description : This function is used to assert dashboard
	Created by : Karan Arora
	Application : Dploy
	objectName : object of dashboard List
	listName : name on which you want to click
	Date : 10 AUG 2016
	####################################################################################*/
	public static void dashBoardAssertion(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";
			List<WebElement> dashboards = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			
			actualText=dashboards.get(dashboards.size()-1).getText();
			
			System.out.println("last dashboard value :" + actualText);
				if (actualText.equals(item))
				{
					matchedItem=true;	
				}
			if(matchedItem==true)
					Keyword.writeOutput("Expected text \""+item+"\" is matched with actual text \""+actualText+"\" in DashBoard successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text \""+actualText+"\"in DashBoard successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	/*##################################################################################
	Function Name : widgetAssertion
	Description : This function is used to Assert widgets on dashboard
	Created by : Karan Arora
	Application : Dploy
	objectName : object of widget List on dashboard
	listName : name of which you want to assert
	Date : 11 AUG 2016
	pre-condition : single widget of its type and no widget should be present before running this function 
	####################################################################################*/
	public static void widgetAssertion(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";
			String excel=item;
			List<WebElement> widgets = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			for( WebElement widget: widgets)
			
			{	
				String widgetAssertion=widget.getText();
				actualText=widgetAssertion;
				System.out.println("widgetAssertion :" + widgetAssertion.trim());
                System.out.println("widgetAssertion.trim().equals(item.trim())"  +  widgetAssertion.trim().equals(item.trim()));
                System.out.println("item.trim() :" + item.trim());		
                if(widgetAssertion.trim().equals(item.trim()))
				{
					matchedItem=true;	
					break;
				}
			}
			if(matchedItem==true)
					Keyword.writeOutput("Expected widget \""+item+"\" is matched with  \""+actualText+"\" widget successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected widget \""+item+"\" is not matched with \""+actualText+"\" widget successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	/*##################################################################################
	Function Name : deployAlertAssert
	Description : This function is used to Assert Through Alert
	Created by : Karan Arora
	Application : Dploy
	objectName : object of widget List on dashboard
	listName : name of which you want to assert
	Date : 11 AUG 2016
	pre-condition : single widget of its type and no widget should be present before running this function 
	####################################################################################*/
	public static void deployAlertAssert(String objectName) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			String actualText= "";
			WebElement alert = Keyword.driver.findElement(By.xpath(Keyword.getTestObject(objectName)));
			actualText= alert.getText();
			System.out.println("alert text is :" + actualText);
	
			if(actualText.equals("")){
				
				Keyword.writeOutput("Expected alert not catched successfully:- Pass", Keyword.testName);
			}
			else
			{
				Keyword.writeOutput("Expected alert gives \""+actualText+"\" error:- Fail", Keyword.testName);
			}
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected alert not catched successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	
	/*##################################################################################
	Function Name : severityAssertion
	Description : This function is used to Assert Severity table
	Created by : Karan Arora
	Application : Dploy
	objectName : object of Severity List on dashboard
	listName : name of which you want to assert
	Date : 16 AUG 2016
	pre-condition : no duplicate severity code present in Severity List tABLE 
	####################################################################################*/
	public static void severityAssertion(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";
			String excel=item;
			List<WebElement> severities = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			for( WebElement sever: severities){
				System.out.println(sever.findElement(By.xpath("./td[1]")).getText() +"------------"+ sever.getText().contains(item));
				
				if (sever.findElement(By.xpath("./td[1]")).getText().contains(item))
				{
					System.out.println("got inside");
					actualText=sever.findElement(By.xpath("./td[1]")).getText();
					matchedItem=true;	
					break;
				}
			}
			if(matchedItem==true)
					Keyword.writeOutput("Expected text \""+item+"\" is matched with actual text \""+actualText+"\" successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text \""+actualText+"\" successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	
	
	
	/*##################################################################################
	Function Name : FilterAssertion
	Description : This function is used to check filter table
	Created by : Karan Arora
	Application : Dploy
	objectName : object of filtered List 
	listName : name of which you want to assert
	Date : 16 AUG 2016
	pre-condition :  
	####################################################################################*/
	public static void filterAssertion(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";
			String excel=item;
			List<WebElement> metrics = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			for( WebElement metric:metrics){
				System.out.println(" text inside div" +  metric.findElement(By.xpath("./td[2]/a")).getText());
				
				System.out.println(metric.findElement(By.xpath("./td[2]/a")).getText().trim().startsWith(item));
				
				if (metric.findElement(By.xpath("./td[2]/a")).getText().trim().startsWith(item))
				{
					System.out.println("got inside matched");
					actualText=metric.findElement(By.xpath("./td[2]/a")).getText();
					matchedItem=true;	
				}
				else
				{
					System.out.println("got inside not matched");
					matchedItem=false;
					break;
				}
			}
			if(matchedItem==true)
					Keyword.writeOutput("Expected text \""+item+"\" is matched with actual text \""+actualText+"\" successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text \""+actualText+"\" successfully:- Fail", Keyword.testName);
			
			    Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	
	/*##################################################################################
	Function Name : attachmentupdateAssertion
	Description : This function is used to check attachment after update table
	Created by : Karan Arora
	Application : Dploy
	objectName : object of filtered List 
	listName : name of which you want to assert
	Date : 16 AUG 2016
	pre-condition :  used after update
	####################################################################################*/
	public static void attachmentupdateAssertion(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";
			String excel=item;
			List<WebElement> attachments = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			for( WebElement attachment:attachments){
				System.out.println(" text inside div" +  attachment.findElement(By.xpath("./td[3]/div/a")).getText());
				System.out.println(attachment.findElement(By.xpath("/td[3]/div/a")).getText() +"------------"+ attachment.findElement(By.xpath("./td[2]/div")).getText().trim().startsWith(item));
				
				if (attachment.findElement(By.xpath("./td[3]/div/a")).getText().trim().equals(item))
				{
					System.out.println("got inside matched");
					actualText=attachment.findElement(By.xpath("./td[3]/div/a")).getText();
					matchedItem=true;	
				}
			}
			if(matchedItem==true)
					Keyword.writeOutput("Expected text \""+item+"\" is matched with actual text \""+actualText+"\" successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text \""+actualText+"\" successfully:- Fail", Keyword.testName);
			
			    Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	/*##################################################################################
	Function Name : attachmentdeleteAssertion
	Description : This function is used to check attachment after update table
	Created by : Karan Arora
	Application : Dploy
	objectName : object of filtered List 
	listName : name of which you want to assert
	Date : 16 AUG 2016
	pre-condition :  used after update
	####################################################################################*/
	public static void attachmentdeleteAssertion(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";
			String excel=item;
			List<WebElement> attachments = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			for( WebElement attachment:attachments){
				System.out.println(" text inside div" +  attachment.findElement(By.xpath("./td[3]/div/a")).getText());
				System.out.println(attachment.findElement(By.xpath("/td[3]/div/a")).getText() +"------------"+ attachment.findElement(By.xpath("./td[2]/div")).getText().trim().startsWith(item));
				
				if (attachment.findElement(By.xpath("./td[3]/div/a")).getText().trim().equals(item))
				{
					System.out.println("got inside matched");
					actualText=attachment.findElement(By.xpath("./td[3]/div/a")).getText();
					matchedItem=true;	
				}
			}
			if(matchedItem==false)
					Keyword.writeOutput("Expected text \""+item+"\" is deleted successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not deleted successfully:- Fail", Keyword.testName);
			
			    Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	
	/*##################################################################################
	Function Name : settingAssertion
	Description : This function is used to assert settings
	Created by : Karan Arora
	Application : Dploy
	objectName : object of setting List
	listName : 
	Date : 18 AUG 2016
	####################################################################################*/
	public static void settingAssertion(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";
			WebElement setting = Keyword.driver.findElement(By.xpath(Keyword.getTestObject(objectName)));
			
			System.out.println("settin text :" + setting.getText());
			actualText= setting.getText().trim();
				if (actualText.equals(item.trim()))
				{
					matchedItem=true;	
				}
			if(matchedItem==true)
					Keyword.writeOutput("Expected text \""+item+"\" is matched with actual text \""+actualText+"\" in setting successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text \""+actualText+"\"in setting successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	
	/*##################################################################################
	Function Name : MetricAssertion
	Description : This function is used to Metric settings
	Created by : Karan Arora
	Application : Dploy
	objectName : object of Metric List
	listName : 
	Date : 22 AUG 2016
	####################################################################################*/
	public static void MetricAssertion(String objectName, String item) {
		try
		{
            ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";
			String excel=item;
			List<WebElement> metrics = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			for( WebElement metric:metrics){
				System.out.println(" text inside div" +  metric.findElement(By.xpath("./td[2]/div/a")).getText());
				System.out.println(metric.findElement(By.xpath("./td[2]/div/a")).getText() +"------------"+ metric.findElement(By.xpath("./td[2]/div")).getText().trim().startsWith(item));
				
				if (metric.findElement(By.xpath("./td[2]/div/a")).getText().trim().equals(item))
				{
					System.out.println("got inside matched");
					actualText=metric.findElement(By.xpath("./td[2]/div/a")).getText();
					matchedItem=true;
					break;
				}
			}
			if(matchedItem==true)
					Keyword.writeOutput("Expected text \""+item+"\" is verified successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not verified successfully:- Fail", Keyword.testName);
			
			    Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	 /*##################################################################################
		Function Name : assertHistoryGrid
		Description : This function is used to assert HistoryGrid
		Created by : Karan Arora
		Application : Dploy
		objectName : object of historyActualValGrid List
		listName : 
		Date : 25 AUG 2016
		####################################################################################*/
		public static void assertHistoryGrid(String objectName, String item) {
			try
			{
				ExecutionTime.stopOrPauseExecution();
				boolean matchedItem = false;
				String actualText= "";
				Integer plannedValue;
				Integer actualValue;
				Integer unitLost;
				Integer count=0;
				List<WebElement> historyActualValGrid = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
				
				for(WebElement historyActualValRow:historyActualValGrid)
				{
					plannedValue=Integer.parseInt(historyActualValRow.findElement(By.xpath("./td[2]/div")).getText());
					actualValue=Integer.parseInt(historyActualValRow.findElement(By.xpath("./td[3]/div")).getText());
					unitLost=Integer.parseInt(historyActualValRow.findElement(By.xpath("./td[4]/div")).getText());
					System.out.println("planned :" + plannedValue+" & actualValue :" + actualValue + " &  unitLost :" + unitLost +" & subtracting " + ((plannedValue-actualValue)==unitLost || (plannedValue-actualValue)<0) + " \n ");
					if((plannedValue-actualValue)==unitLost || (plannedValue-actualValue)<0 )
					{
						matchedItem = true;
					}
					else
					{
						matchedItem = false;
						break;
					}
					count++;
					sumActualvalue+=actualValue;
					sumPlannedvalue+=plannedValue;
				}
				sumActualvalue/=count;
				sumPlannedvalue/=count;
				System.out.println("sumActualvalue :" + sumActualvalue +" & sumPlannedvalue" + sumPlannedvalue);
				
				if(matchedItem==true)
					Keyword.writeOutput("Expected historyActualValGrid verified successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected historyActualValGrid does not verified successfully:- Fail", Keyword.testName);
			
			}
			catch(Exception e)
			{
				System.out.println("in exceptionClickonlinkbyxpath=="+e);
				Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
				
				Keyword.screnshotsForMytest();
			}
		}
		
	/*##################################################################################
	Function Name : performanceGridValueAssertion
	Description : This function is used to check  HistoryGrid table assertion(average of actual and planned)
	Created by : Karan Arora
	Application : Dploy
	objectName : object of filtered List 
	listName : name of which you want to assert
	Date : 25 AUG 2016
	pre-condition :  use with assertHistoryGrid function only (value of sumActualvalue and sumPlannedvalue are taken up  by same function)
	####################################################################################*/
	public static void performanceGridValueAssertion(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String[] actualText=null;
			Integer plannedSum;
			Integer actualSum;
			String excel=item;
			Keyword.driver.navigate().refresh();
			List<WebElement> metrics = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			for( WebElement metric:metrics){
				System.out.print("");
				System.out.println(" text inside div" +  metric.findElement(By.xpath("./td[2]/a")).getText());
				
				System.out.println(metric.findElement(By.xpath("./td[2]/a")).getText().trim().equals(item));
				
				if (metric.findElement(By.xpath("./td[2]/a")).getText().trim().equals(item))
				{
					System.out.println("got inside matched");
					actualText=metric.findElement(By.xpath("./td[8]/div/span[1]")).getText().split(Pattern.quote("/"));
					plannedSum=Integer.parseInt(actualText[0]);
					System.out.println();
					actualSum=(int)Float.parseFloat(actualText[1]);
					if (sumActualvalue==actualSum && sumPlannedvalue==plannedSum)
					{
						matchedItem=true;
						break;
					}
				}
			}
			if(matchedItem==true)
					Keyword.writeOutput("Actual Sum and Plannes Sum matched successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Actual Sum and Plannes Sum are not matched successfully:- Fail", Keyword.testName);
			
			    Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	
	
	/*##################################################################################
	Function Name : minimumPercMissAssertion
	Description : This function is used to Assert minimum percentage miss assertion for biggest bowler miss
	Created by : Karan Arora
	Application : Dploy
	objectName : object of Biggest Bowler Miss Detail table List
	listName : name on which you want to click
	Date : 31 AUG 2016
	####################################################################################*/
	public static void minimumPercMissAssertion(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";
			Float tablePerc;
			Float excelPerc;
			
			List<WebElement> bowlerMissDetails = Keyword.driver.findElements(By.xpath(Keyword.getTestObject(objectName)));
			for( WebElement bowlerMissDetail: bowlerMissDetails){
				
				String[] onSite=bowlerMissDetail.findElement(By.xpath("./td[7]")).getText().trim().split(Pattern.quote("%"));
				System.out.println("fgdsads");
				actualText=onSite[0].replaceAll(",","");

				tablePerc=Float.parseFloat(actualText);
				excelPerc=Float.parseFloat(item.trim());
				
				System.out.println("onSite :" + tablePerc + "table value :" + excelPerc);
				if(excelPerc<=tablePerc)
				{
					System.out.println("value matched"+bowlerMissDetail.findElement(By.xpath("./td[3]")).getText());
					matchedItem=true;		
				}
				else
				{
					matchedItem=false;
					break;
				}
			}
			if(matchedItem==true)
					Keyword.writeOutput("Expected text \""+item+"\" is matched with actual text \""+actualText+"\" successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text \""+actualText+"\" successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	/*##################################################################################
	Function Name : performanceMetricradio
	Description : This function is used to select 
	Created by : Karan Arora
	Application : Dploy
	objectName : object of Biggest Bowler Miss Detail table List
	listName : name on which you want to click
	Date :  2 sept 2016
	####################################################################################*/
	public static void performanceMetricRadio(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";
			String objName=Keyword.getTestObject(objectName);
			
            List<WebElement> metrics = Keyword.driver.findElements(By.xpath(objName));
			for(WebElement metric: metrics)
			{
			System.out.println("metric text :" + metric.findElement(By.xpath("./td[2]")).getText());
			actualText= metric.findElement(By.xpath("./td[3]/span")).getText().trim();
				if (actualText.equals(item.trim()))
				{
					matchedItem=true;
					metric.findElement(By.xpath("./td[1]/input")).click();
					break;
				}
			}	
			if(matchedItem==true)
					Keyword.writeOutput("Expected text \""+item+"\" is matched with actual text \""+actualText+"\" in setting successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text \""+actualText+"\"in setting successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	/*##################################################################################
	Function Name : DeleteDashBoard
	Description : This function is used to select 
	Created by : Karan Arora
	Application : Dploy
	objectName : object of Biggest Bowler Miss Detail table List
	listName : name on which you want to click
	Date :  6 sept 2016
	####################################################################################*/
	public static void DeleteDashBoard(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";
			String objName=Keyword.getTestObject(objectName);
			
            List<WebElement> dashboards = Keyword.driver.findElements(By.xpath(objName));
			for(WebElement dashboard: dashboards)
			{
			System.out.println("metric text :" + dashboard.findElement(By.xpath("./td[1]")).getText());
			actualText= dashboard.findElement(By.xpath("./td[1]")).getText().trim();
				if (actualText.equals(item.trim()))
				{
					matchedItem=true;
					dashboard.findElement(By.xpath("./td[5]/a[2]")).click();
					break;
				}
			}	
			if(matchedItem==true)
					Keyword.writeOutput("Expected text \""+item+"\" is matched with actual text \""+actualText+"\" in setting successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text \""+actualText+"\"in setting successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	/*##################################################################################
	Function Name : severityCode
	Description : This function is used to select severity code
	Created by : Karan Arora
	Application : Dploy
	objectName : object of Biggest Bowler Miss Detail table List
	listName : name on which you want to click
	Date :  6 sept 2016
	####################################################################################*/
	public static void severityCode(String objectName, String item) {
		try
		{
			ExecutionTime.stopOrPauseExecution();
			boolean matchedItem = false;
			String actualText= "";
			String objName=Keyword.getTestObject(objectName);
			
			if ( !Keyword.driver.findElement(By.id(objName)).isSelected() )
			{
			     Keyword.driver.findElement(By.id(objName)).click();
			}
			
           /* List<WebElement> codes = Keyword.driver.findElements(By.xpath(objName));
			for(WebElement code: codes)
			{
			System.out.println("metric text :" + code.findElement(By.xpath("./td[1]")).getText());
			actualText= code.findElement(By.xpath("./td[1]")).getText().trim();
				if (actualText.equals(item.trim()))
				{
					matchedItem=true;
					code.findElement(By.xpath("./td[5]/a[2]")).click();
					break;
				}
			}	
			if(matchedItem==true)
					Keyword.writeOutput("Expected text \""+item+"\" is matched with actual text \""+actualText+"\" in setting successfully:- Pass", Keyword.testName);
				else
					Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text \""+actualText+"\"in setting successfully:- Fail", Keyword.testName);
			*/
			Keyword.screnshotsForMytest();
		}
		catch(Exception e)
		{
			System.out.println("in exceptionClickonlinkbyxpath=="+e);
			Keyword.writeOutput("Expected text \""+item+"\" is not matched with actual text successfully:- Fail", Keyword.testName);
			
			Keyword.screnshotsForMytest();
		}
	}
	/*##################################################################################
	Function Name : webServiceCall
	Description : The function is used to get interview question through jquery and verify it.
	Created by :    ///=========================complex
	####################################################################################*/
	/*public static  void webServiceCall() throws Exception {


		String request=CONFIG.getProperty("SendRequest");
		 String name="\"dileep\"";
		 String domain="\"newgen\"";
		 String password="\"volt#123\"";
     //String request="<![CDATA[<Request JobID=\"\" Type=\"AUTHENTICATION\"><File Name=\"abc.doc\" Pages=\"0\" Tag=\"\" Watermark=\"\" Header=\"\" Footer=\"\" PrintFormat=\"PS\" Size=\"\" PaperSizeName=\"\" PaperSizeDimension=\"\" Encryption=\"unsecure\" CONTENTTYPE=\"%!PS-Adobe-3.0\" COLORPAGES=\"0\" /><Sender Name="+name+" Domain="+domain+" Password="+password+" CustomMode=\"False\" /><Items ID=\"\" /><LocalSite><Users Type=\"\" Count=\"0\" /></LocalSite><PrinterDeployment Clientversion=\"1.1.1.2\" /></Request>]]>";		
	
     
     String envelope="<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" + 
			       "  <soap:Body>" + 
			       "    <SendRequest xmlns=\"http://www.newgensoft.com/\">" + 
			       "      <sRequest>"+
			       request+
			       "</sRequest>" + 			       
			       "    </SendRequest>" + 
			       "  </soap:Body>" + 
			       "</soap:Envelope>";
	       
	       System.out.println("final envelope==================="+envelope);
		String wbserviceurl="http://192.168.60.144/ccserver/ccservice.asmx";
		
		String SoapAction="http://www.newgensoft.com/SendRequest";
		
		String outdata=callNPCAPI(wbserviceurl, envelope,SoapAction);
		
		System.out.println("output="+outdata);
//-------------------- parsing string to xml for output xml-------------------//		
		String xml = outdata;
		
		
		System.out.println("We are after outdata");
		System.out.println("length of String" + xml.length());
		xml = xml.substring(xml.indexOf("<SendRequestResult>"),xml.indexOf("</SendRequestResponse>"));
        xml=xml.replaceAll("&lt;","<").replaceAll("&gt;",">");
        System.out.println("xml=============================" + xml);
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));

			Document doc = db.parse(is);
			Element docEle = doc.getDocumentElement();
			System.out.println("elements :-----------" + docEle);
			NodeList n1 = docEle.getChildNodes();
			//NodeList n1 = docEle.getElementsByTagName("Response");
			System.out.println("first element==========" + n1.item(0));
			//System.out.println("first element==========" + n1.item(1).getAttributes());
			Element el = (Element) n1.item(0);
			NodeList mainNodes  = el.getChildNodes();
			Element mainel=(Element) mainNodes.item(0);
			System.out.println("fiththtt==========" + mainel.getAttribute("Value"));
			System.out.println("n1 subnodes : " + n1.getLength());
			if(mainel.getAttribute("Value").contains("SUCCESS"))
			{
				System.out.println("User is authenticated for this WebService");
			}
			
			
			
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
			e.printStackTrace();
		}

	}
	public static String callNPCAPI(String ASMXUrl,
    		String soapEnvelope,String SoapAction) throws ClientProtocolException,    Exception {  
    String data=""  ;
    HttpClient httpclient =new DefaultHttpClient();
    //2. Set HTTPPOST request to send SOAP-ENVELOPE
    String endpoint = ASMXUrl;
    HttpPost httppost = new HttpPost(endpoint);
    

    httppost.addHeader("SOAPAction",SoapAction);    

    //3. passing soap payload and executing request to sharepoint server
    StringEntity se = new StringEntity(soapEnvelope,HTTP.UTF_8 );
    se.setContentType("text/xml");
    httppost.setEntity(se);
        
    //4.Fething Response    
    HttpResponse httpresponse = httpclient.execute(httppost);
    HttpEntity resEntity = httpresponse.getEntity();
    
    if(httpresponse.getStatusLine().getStatusCode()==200)
    {
    //5. Convert response to an XML String     
    data=  EntityUtils.toString(resEntity);
   // System.out.println("Status OK: \n" +data);
    }
    
    
    else
    { data=  EntityUtils.toString(resEntity);
        System.out.println("Status Not OK: \n" +data); 
        System.out.println("HTTP Error:"+httpresponse.getStatusLine());
    throw new Exception(" HTTP Error:"+httpresponse.getStatusLine());
    }       
    return data;
    }*/
}
