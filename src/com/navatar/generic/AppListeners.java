package com.navatar.generic;

import java.beans.ExceptionListener;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.*;
import java.util.*;

import javax.mail.MessagingException;
import javax.naming.ConfigurationException;

import org.apache.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.testng.*;

import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsByXPath;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.sikuli.guide.ClickableWindow;

/**
 * 
 * @author Ankur Rana
 * @description Declared listeners and there behaviour according to the execution
 */

public class AppListeners extends By implements ITestListener, IInvokedMethodListener, WebDriverEventListener, ExceptionListener,
		ITestNGListener, IExecutionListener,SearchContext {

	public static int iPassCount;
	public static int iFailCount;
	public static int iskipCount;
	public static Logger appLog;
	public static Map<String, String> status = new LinkedHashMap<String, String>();
	public static int exe;
	public static ExtentReports extentReport;
	public static ExtentTest extentLog;
	public static String executingMethod;
	public Field f;
	public static String currentlyExecutingTC;
	public static int numberOfExecution=0;
	
	public AppListeners() {
		DateFormat dateFormat = new SimpleDateFormat("yy_MM_dd_hh_mm_ss");
		Date date = new Date();
		System.setProperty("LongTimeDate", dateFormat.format(date));
		System.setProperty("ProjectLocation", System.getProperty("user.dir"));
		appLog = Logger.getLogger(this.getClass());
		extentReport = new ExtentReports(
				System.getProperty("user.dir") + "/Reports/ExtentReports/ExtentLog" + dateFormat.format(date) + ".html",
				true);
		extentReport.addSystemInfo("Host Name", "Ankur");
		extentReport.loadConfig(new File(System.getProperty("user.dir") + "\\ConfigFiles\\extent-config.xml"));
	}

	public String getTestName(Method m) {
		return m.getName();
	}

	@Override
	public void onTestStart(ITestResult result) {
		currentlyExecutingTC=result.getMethod().getMethodName();
		appLog.info("\n\n*************************Starting: "+result.getMethod().getMethodName()+"**********************\n\n");
		extentLog = extentReport.startTest(result.getName());
		executingMethod = result.getMethod().getMethodName();
		appLog.info("Test Case: " + result.getMethod().getMethodName() + " Started At: " + new Timestamp(new Date().getTime()));
		exe = 0;
		try {
			if (result.getName().substring(6, 18).equalsIgnoreCase("watermarking")) {
				exe = 1;
			}
		} catch (Exception e) {

		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// String screenshot=CommonLib.screenshot(result.getName());
		// extentLog.log(LogStatus.PASS, result.getName()+" is
		// passed.",extentLog.addScreenCapture(screenshot));
		iPassCount++;
		status.put(result.getMethod().getMethodName(), "Pass");
		appLog.info("Pass: " + result.getMethod().getMethodName());
		extentReport.endTest(extentLog);
		appLog.info("\n************************Finidshed TestCase: "+result.getMethod().getMethodName()+"****************\n");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try{
			String screenshot=CommonLib.screenshot(result.getMethod().getMethodName());
			AppListeners.appLog.info("Location of screenshot is: "+screenshot);
		} catch (Exception e){
			AppListeners.appLog.info("Screenshot cannot be taken because window is closed.");
		}
		// extentLog.log(LogStatus.FAIL, result.getName()+" is
		// failed.",extentLog.addScreenCapture(screenshot));
		iFailCount++;
		String msg=result.getThrowable().getMessage();
		if(msg==null){
			status.put(result.getMethod().getMethodName(), "Fail: " + CommonLib.errorMessage);
//			CommonLib.failedMethod(result.getThrowable());
			appLog.fatal("Failed Test Case: " + result.getMethod().getMethodName() + " \nReason: " + CommonLib.errorMessage);
//			extentReport.endTest(extentLog);
			msg = CommonLib.errorMessage;
		} else {
			status.put(result.getMethod().getMethodName(), "Fail: " + result.getThrowable().getMessage());
//		CommonLib.failedMethod(result.getThrowable());
			appLog.fatal("Failed Test Case: " + result.getMethod().getMethodName() + " \nReason: " + result.getThrowable().getMessage());
//			result.getThrowable().printStackTrace();
			msg = result.getThrowable().getMessage();
		}
		extentReport.endTest(extentLog);
		appLog.info("\n************************Finidshed TestCase: "+result.getMethod().getMethodName()+"****************\n");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// String screenshot=CommonLib.screenshot(result.getName());
		// extentLog.log(LogStatus.SKIP, result.getName()+" is
		// skipped.",extentLog.addScreenCapture(screenshot));
		iskipCount++;
		status.put(result.getMethod().getMethodName(), "Skip: " + result.getThrowable().getMessage());
		appLog.fatal("Skipped Test Case: " + result.getMethod().getMethodName() + " Skip Reason: " + result.getThrowable().getMessage()
				+ "\n Stack Trace");
		result.getThrowable().printStackTrace();
		extentReport.endTest(extentLog);
		appLog.info("\n************************Finidshed TestCase: "+result.getMethod().getMethodName()+"****************\n");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// System.out.println(result.);
	}

	@Override
	public void onStart(ITestContext context) {
		PropertyConfigurator.configure("log4j.properties");
		appLog.info("Test Suite Execution Started: " + new Timestamp(new Date().getTime()));
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		extentReport.close();
		ExcelUtils.writeStatusInExcel("testcases");
	}

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
		

	}

	@Override
	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		CommonLib.waitForPageLoad(driver);

	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		CommonLib.waitForPageLoad(driver);
		CommonLib.checkForLoaderImage(driver, "//div[@id='Processingdiv']/img", 120);
		CommonLib.checkForLoaderImage(driver, "//img[@src='/resource/1499340792000/DR_CRMFinal/DR_CRMFinal/images/processing-image.gif']", 120);
//		CommonLib.checkForLoaderImage(driver, "//div[@id='blurred']", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='blurred_procss_imaz']", 120);
		CommonLib.checkForLoaderImage(driver, "//img[@id='Img1']", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@class='waitingSearchDiv']", 120);
		CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'images/processing-image.gif')]", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='blurredImg']/div/img", 120);
		CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'loading')]", 120);
		CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred2']//img)[1]", 120);
		CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred2']//img)[2]", 120);
		CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred1']//img)[1]", 120);
		CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred']//img)[1]", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosBWFR')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosBWINV')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMFfundraising')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMFinvestor')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosMINFR')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosCreatingFolderMINFR')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMIMINFR')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='loadDivPosMININV']//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosMININV')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosCreatingFolderMININV')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMIMININV')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'statusInner_alldoc')]//img", 120);
		if(!CommonLib.isAlertPresent(driver)) {
			List<WebElement> pleaseWaitImages = driver.findElements(By.xpath("//img[contains(@src,'loading')]"));
			if(!pleaseWaitImages.isEmpty()){
				CommonLib.checkForLoaderImage(driver, pleaseWaitImages, 120);
			}
		}
		
		
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		if(BaseLib.PublicFlag){
			CommonLib.waitForPageLoad(driver);
			CommonLib.checkForLoaderImage(driver, "//div[@id='Processingdiv']/img", 120);
			CommonLib.checkForLoaderImage(driver, "//img[@src='/resource/1499340792000/DR_CRMFinal/DR_CRMFinal/images/processing-image.gif']", 120);
//			CommonLib.checkForLoaderImage(driver, "//div[@id='blurred']", 120);
			CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'processing-image.gif')]", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@id='blurred_procss_imaz']", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@class='waitingSearchDiv']", 120);
			CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'images/processing-image.gif')]", 120);		
			CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'loading')]", 120);
			CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred2']//img)[1]", 120);
			CommonLib.checkForLoaderImage(driver, "//img[@id='Img1']", 120);
			CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred2']//img)[2]", 120);
			CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred1']//img)[1]", 120);
			CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred']//img)[1]", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosBWFR')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosBWINV')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMFfundraising')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMFinvestor')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosMINFR')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosCreatingFolderMINFR')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMIMINFR')]", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@id='loadDivPosMININV']//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosMININV')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosCreatingFolderMININV')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'statusInner_alldoc')]//img", 120);
			if(!CommonLib.isAlertPresent(driver)) {
				List<WebElement> pleaseWaitImages = driver.findElements(By.xpath("//img[contains(@src,'loading')]"));
				if(!pleaseWaitImages.isEmpty()){
					CommonLib.checkForLoaderImage(driver, pleaseWaitImages, 120);
				}
			}
		}
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		if(BaseLib.PublicFlag){
			CommonLib.checkForLoaderImage(driver, "//div[@id='Processingdiv']/img", 120);
			CommonLib.checkForLoaderImage(driver, "//img[@src='/resource/1499340792000/DR_CRMFinal/DR_CRMFinal/images/processing-image.gif']", 120);
//			CommonLib.checkForLoaderImage(driver, null, 120);
			CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'processing-image.gif')]", 120);
//			CommonLib.checkForLoaderImage(driver, "//div[@id='blurred']", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@id='blurred_procss_imaz']", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@class='waitingSearchDiv']", 120);
			CommonLib.waitForPageLoad(driver);
			CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'images/processing-image.gif')]", 120);	
			CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'loading')]", 120);
			CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred2']//img)[1]", 120);
			CommonLib.checkForLoaderImage(driver, "//img[@id='Img1']", 120);
			CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred2']//img)[2]", 120);
			CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred1']//img)[1]", 120);
			CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred']//img)[1]", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosBWFR')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosBWINV')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMFfundraising')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMFinvestor')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosMINFR')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosCreatingFolderMINFR')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMIMINFR')]", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@id='loadDivPosMININV']//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosMININV')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosCreatingFolderMININV')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'statusInner_alldoc')]//img", 120);
			if(!CommonLib.isAlertPresent(driver)) {
				List<WebElement> pleaseWaitImages = driver.findElements(By.xpath("//img[contains(@src,'loading')]"));
				if(!pleaseWaitImages.isEmpty()){
					CommonLib.checkForLoaderImage(driver, pleaseWaitImages, 120);
				}
			}
			
		}
	} 

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		if(BaseLib.PublicFlag){
			CommonLib.checkForLoaderImage(driver, "//div[@id='Processingdiv']/img", 120);
			CommonLib.checkForLoaderImage(driver, "//img[@src='/resource/1499340792000/DR_CRMFinal/DR_CRMFinal/images/processing-image.gif']", 120);
//			CommonLib.checkForLoaderImage(driver, null, 120);
			CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'processing-image.gif')]", 120);
//			CommonLib.checkForLoaderImage(driver, "//div[@id='blurred']", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@id='blurred_procss_imaz']", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@class='waitingSearchDiv']", 120);
			CommonLib.waitForPageLoad(driver);
			CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'images/processing-image.gif')]", 120);	
			CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'loading')]", 120);
			CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred2']//img)[1]", 120);
			CommonLib.checkForLoaderImage(driver, "//img[@id='Img1']", 120);
			CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred2']//img)[2]", 120);
			CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred1']//img)[1]", 120);
			CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred']//img)[1]", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosBWFR')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosBWINV')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMFfundraising')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMFinvestor')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosMINFR')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosCreatingFolderMINFR')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMIMINFR')]", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@id='loadDivPosMININV']//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosMININV')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosCreatingFolderMININV')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'statusInner_alldoc')]//img", 120);
			if(!CommonLib.isAlertPresent(driver)) {
				List<WebElement> pleaseWaitImages = driver.findElements(By.xpath("//img[contains(@src,'loading')]"));
				if(!pleaseWaitImages.isEmpty()){
					CommonLib.checkForLoaderImage(driver, pleaseWaitImages, 120);
				}
			}
		}
	} 

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		if(BaseLib.PublicFlag){
			CommonLib.checkForLoaderImage(driver, "//div[@id='Processingdiv']/img", 120);
			CommonLib.checkForLoaderImage(driver, "//img[@src='/resource/1499340792000/DR_CRMFinal/DR_CRMFinal/images/processing-image.gif']", 120);
//			CommonLib.checkForLoaderImage(driver, null, 120);
			CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'processing-image.gif')]", 120);
//			CommonLib.checkForLoaderImage(driver, "//div[@id='blurred']", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@id='blurred_procss_imaz']", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@class='waitingSearchDiv']", 120);
			CommonLib.waitForPageLoad(driver);
			CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'images/processing-image.gif')]", 120);	
			CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'loading')]", 120);
			CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred2']//img)[1]", 120);
			CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred2']//img)[2]", 120);
			CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred1']//img)[1]", 120);
			CommonLib.checkForLoaderImage(driver, "//img[@id='Img1']", 120);
			CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred']//img)[1]", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosBWFR')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosBWINV')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMFfundraising')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMFinvestor')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosMINFR')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosCreatingFolderMINFR')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMIMINFR')]", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@id='loadDivPosMININV']//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosMININV')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosCreatingFolderMININV')]//img", 120);
			CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'statusInner_alldoc')]//img", 120);
			System.err.println("Going to check loader image");
			if(!CommonLib.isAlertPresent(driver)) {
				List<WebElement> pleaseWaitImages = driver.findElements(By.xpath("//img[contains(@src,'loading')]"));
				if(!pleaseWaitImages.isEmpty()){
					CommonLib.checkForLoaderImage(driver, pleaseWaitImages, 120);
				}
			}
		} else {
			BaseLib.PublicFlag = true;
		}
		if(CommonLib.getXpath(element).equalsIgnoreCase("//a[@title='Alert History']")){
			System.out.println("Element matched successfully.");
//				driver.findElement(By.xpath("//*[contains(@id,'grid_AlertHistory')][text()='Date']")).click();
				if(CommonLib.click(driver, CommonLib.FindElement(driver, "//*[contains(@id,'grid_AlertHistory')][text()='Date']", null, action.BOOLEAN, 10), null, action.BOOLEAN))
					CommonLib.ThreadSleep(1000);
				if(CommonLib.click(driver, CommonLib.FindElement(driver, "//*[contains(@id,'grid_AlertHistory')][text()='Date']", null, action.BOOLEAN, 10), null, action.BOOLEAN))
					CommonLib.ThreadSleep(1000);
				CommonLib.click(driver, CommonLib.FindElement(driver, "//*[contains(@id,'grid_AlertHistory')][text()='Date']", null, action.BOOLEAN, 10), null, action.BOOLEAN);
		} else {
			System.out.println("Elements not matched.");
		}
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		CommonLib.checkForLoaderImage(driver, "//div[@id='Processingdiv']/img", 120);
		CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'processing-image.gif')]", 120);
		CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'images/processing-image.gif')]", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='blurredImg']/div/img", 120);
		CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'loading')]", 120);
		CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred2']//img)[1]", 120);
		CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred2']//img)[2]", 120);
		CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred1']//img)[1]", 120);
		CommonLib.checkForLoaderImage(driver, "//img[@id='Img1']", 120);
		CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred']//img)[1]", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosBWFR')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosBWINV')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMFfundraising')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMFinvestor')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosMINFR')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosCreatingFolderMINFR')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMIMINFR')]", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='loadDivPosMININV']//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosMININV')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosCreatingFolderMININV')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'statusInner_alldoc')]//img", 120);
		if(!CommonLib.isAlertPresent(driver)) {
			List<WebElement> pleaseWaitImages = driver.findElements(By.xpath("//img[contains(@src,'loading')]"));
			if(!pleaseWaitImages.isEmpty()){
				CommonLib.checkForLoaderImage(driver, pleaseWaitImages, 120);
			}
		}
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		CommonLib.checkForLoaderImage(driver, "//div[@id='Processingdiv']/img", 120);
		CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'processing-image.gif')]", 120);
		CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'images/processing-image.gif')]", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='blurredImg']/div/img", 120);
		CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'loading')]", 120);
		CommonLib.checkForLoaderImage(driver, "//img[@id='Img1']", 120);
		CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred2']//img)[1]", 120);
		CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred2']//img)[2]", 120);
		CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred1']//img)[1]", 120);
		CommonLib.checkForLoaderImage(driver, "(//div[@id='blurred']//img)[1]", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosBWFR')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosBWINV')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMFfundraising')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadMFinvestor')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosMINFR')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosCreatingFolderMINFR')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='loadMIMINFR']//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='loadDivPosMININV']//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'loadDivPosMININV')]//img", 120);
		CommonLib.checkForLoaderImage(driver, "//div[contains(@id,'statusInner_alldoc')]//img", 120);
		if(!CommonLib.isAlertPresent(driver)) {
			List<WebElement> pleaseWaitImages = driver.findElements(By.xpath("//img[contains(@src,'loading')]"));
			if(!pleaseWaitImages.isEmpty()){
				CommonLib.checkForLoaderImage(driver, pleaseWaitImages, 120);
			}
		}
		//img[contains(@src,'processing-image.gif')]
		
		
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		// TODO Auto-generated method stub
		extentReport.endTest(extentLog);
	}

	@Override
	public void exceptionThrown(Exception e) {
		// TODO Auto-generated method stub

		System.out.println("executing exception thrown....");
		try {
			System.out.println("Try in on exception....");
		} catch (Exception et) {
			System.out.println("On Exception Catch Executing.....");
		}

	}

	@Override
	public void onExecutionStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onExecutionFinish() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<WebElement> findElements(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement findElement(By by) {
		// TODO Auto-generated method stub
		return by.findElement(this);
	}

	@Override
	public List<WebElement> findElements(SearchContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	public WebElement findElementByXPath(String using) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WebElement> findElementsByXPath(String using) {
		// TODO Auto-generated method stub
		return null;
	}
	

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#afterGetScreenshotAs(org.openqa.selenium.OutputType, java.lang.Object)
	 */
	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#afterGetText(org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver, java.lang.String)
	 */
	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#afterSwitchToWindow(java.lang.String, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#beforeGetScreenshotAs(org.openqa.selenium.OutputType)
	 */
	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#beforeGetText(org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#beforeSwitchToWindow(java.lang.String, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	

}
