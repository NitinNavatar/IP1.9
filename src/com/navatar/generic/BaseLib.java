package com.navatar.generic;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import org.openqa.selenium.chrome.ChromeDriverService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.navatar.generic.CommonLib.excelLabel;

/**
 * 
 * @author Ankur Rana
 * @description Browser launch is controlled by this class.
 */

public class BaseLib extends AppListeners {

	public static WebDriver edriver;
	public static EventFiringWebDriver driver;
	public AppListeners testListner;
	public String investorRegLink=null;
	public String forgotPasswordLink=null;
	public static SoftAssert sa=new SoftAssert();
	public static List<String> ListofUploadedfiles = new ArrayList<String>();
	public static boolean PublicFlag = true;
	public CommonVariables cv = null;//common variable reference
	public SmokeCommonVariable scv=null;
	public static Set<String> uniquedocs = new LinkedHashSet<String>();
	public static int mostActiveContactActivityCount=0;
	public static int totalUploadedFiles = 0;
	public static String downloadedFilePath=System.getProperty("user.dir")+"\\DownloadedFiles";
	public static String filePath = System.getProperty("user.dir")+"/TestCases.xlsx";
	
	@Parameters(value = "browser")
	@BeforeMethod
	public void config(String browserName) {
		if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\exefiles\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
			options.addArguments("disable-infobars");
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("start-maximized");
			options.addArguments("--disable-notifications");
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("download.default_directory", downloadedFilePath);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			edriver = new ChromeDriver(options);
			
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\exefiles\\geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("log", "{level: trace}");
			capabilities.setCapability("marionette", true);
			capabilities.setCapability("moz:firefoxOptions", options);
			edriver = new FirefoxDriver(capabilities);
		} else if (browserName.equalsIgnoreCase("IE Edge")) {
			 System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") +"\\exefiles\\MicrosoftWebDriver.exe");
			 edriver = new EdgeDriver();
		}else {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\exefiles\\chromedriver.exe");
			edriver = new ChromeDriver();
		}
		driver = new EventFiringWebDriver(edriver);
		testListner = new AppListeners();
		driver.register(testListner);
		//driver.get(ExcelUtils.readDataFromPropertyFile("URL"));
		cv=new CommonVariables(this);
		scv  = new SmokeCommonVariable(this);
		//		sa=new SoftAssert();
//		 driver.manage().window().maximize();

	}

	@AfterMethod
	public void closeBrowser(ITestResult result) {
		uniquedocs = new LinkedHashSet<String>();
		mostActiveContactActivityCount=0;
		
		ExcelUtils.writeData(CommonVariables.FRW_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
		ExcelUtils.writeData(CommonVariables.FRW_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.FRW_Value);
		ExcelUtils.writeData(CommonVariables.FRW_DocumentDownload, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.FRW_Value);
		ExcelUtils.writeData(CommonVariables.FRW_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_Value);
		ExcelUtils.writeData(CommonVariables.FRW_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.FRW_Value);
		ExcelUtils.writeData(CommonVariables.FRW_ContactNotViewedAnyDocument, "IPAnalytics", excelLabel.Statistics, "No of Contacts who have not Viewed any Document", excelLabel.FRW_Value);
		
		ExcelUtils.writeData(CommonVariables.INV_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
		ExcelUtils.writeData(CommonVariables.INV_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.INV_Value);
		ExcelUtils.writeData(CommonVariables.INV_DocumentDownload, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.INV_Value);
		ExcelUtils.writeData(CommonVariables.INV_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_Value);
		ExcelUtils.writeData(CommonVariables.INV_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.INV_Value);
		ExcelUtils.writeData(CommonVariables.INV_ContactNotViewedAnyDocument, "IPAnalytics", excelLabel.Statistics, "No of Contacts who have not Viewed any Document", excelLabel.INV_Value);
		
		
		
		driver.close();
		sa=new SoftAssert();
		try {
			Process process = Runtime.getRuntime().exec(System.getProperty("user.dir")+"/killbrowser.bat");
			process.waitFor();
			CommonLib.ThreadSleep(1000);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result.getStatus()==2){
			if(ExcelUtils.readData("TestCases", excelLabel.TestCases, currentlyExecutingTC, excelLabel.Priority).equalsIgnoreCase("High")){
				appLog.fatal("Priority and dependency of this test case is high, So will not be able to continue with the execution.");
				String toValue = ExcelUtils.readDataFromPropertyFile("EmailIdForStatusMail");
				String[] attachment = {};
				String from = ExcelUtils.readDataFromPropertyFile("gmailUserName");
				String Password = ExcelUtils.readDataFromPropertyFile("gmailPassword");
				String userName = System.getProperty("user.name");
				String[] to = {from};
				if(!toValue.isEmpty()){
					to = new String[toValue.split(",").length];
					for(int i = 0; i < toValue.split(",").length; i++){
						to[i]=toValue.split(",")[i];
					}
					try {
						EmailLib.sendMail(from, Password, to, currentlyExecutingTC+" failed", "Dear "+userName+",\n\n"+currentlyExecutingTC+" is failed due to below reason: \n\n"+result.getThrowable().getMessage()+"\n\n\bNote: Priority and dependency of this test case is high, So will not be able to continue with the execution.", attachment);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					
				}
				CommonLib.exit("Priority and dependency of "+currentlyExecutingTC+" testcase was high so cannot continue with the execution.");
			}
		}
	}

}