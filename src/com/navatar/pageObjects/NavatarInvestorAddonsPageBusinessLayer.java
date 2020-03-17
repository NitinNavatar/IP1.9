/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib.action;
import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.CommonVariables.M4DIS1;

import java.util.List;

/**
 * @author Parul Singh
 *
 */
public class NavatarInvestorAddonsPageBusinessLayer extends NavatarInvestorAddOnsPage implements NavatarInvestorAddOnsErrorMessage {
	/**
	 * @param driver
	 */
	public NavatarInvestorAddonsPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @author Ankur Rana
	 * @param disclaimerName
	 * @return true/false
	 */
	public boolean clickOnDisclaimerNameLink(String disclaimerName){
		WebElement ele = FindElement(driver, "//span[contains(@id,'grid_DefaultFoldersView1-cell-1')]/a[@title='"+disclaimerName+"']", "Disclaimer Name link", action.BOOLEAN, 30);
		if(ele != null){
			if(click(driver, ele, disclaimerName+" Disclaimer name link", action.BOOLEAN)){
				return true;
			} else {
				appLog.error(disclaimerName+" Disclaimer name link cannot be clicked, So cannot check the functionality.");
				BaseLib.sa.assertTrue(false,disclaimerName+" Disclaimer name link cannot be clicked, So cannot check the functionality.");
			}
		} else {
			appLog.error(disclaimerName+" Disclaimer Name Link is not present, So cannot check the link functionality.");
			BaseLib.sa.assertTrue(false,disclaimerName+" Disclaimer Name Link is not present, So cannot check the link functionality.");
		}
		return false;
	}
	
	/**
	 * @author Ankur Rana
	 * @param disclaimerName
	 * @return true/false
	 */
	public boolean clickOnViewLinkOfDisclaimer(String disclaimerName){
		WebElement ele = FindElement(driver, "//span[contains(@id,'grid_DefaultFoldersView1-cell-1')]/a[@title='"+disclaimerName+"']/../following-sibling::span[1]/a", disclaimerName+" Disclaimer View link", action.BOOLEAN, 30);
		if(ele != null){
			if(click(driver, ele, disclaimerName+" Disclaimer View link", action.BOOLEAN)){
				return true;
			} else {
				appLog.error(disclaimerName+" Disclaimer View link cannot be clicked, So cannot check the functionality.");
				BaseLib.sa.assertTrue(false,disclaimerName+" Disclaimer View link cannot be clicked, So cannot check the functionality.");
			}
		} else {
			appLog.error(disclaimerName+" Disclaimer View Link is not present, So cannot check the link functionality.");
			BaseLib.sa.assertTrue(false,disclaimerName+" Disclaimer View Link is not present, So cannot check the link functionality.");
		}
		return false;
	}
	
	/**
	 * @author Ankur Rana
	 * @param DisclaimerName
	 * @param timeOut
	 * @return true/false
	 */
	public boolean activateDisclaimer(String DisclaimerName, int timeOut){
		WebElement ele = FindElement(driver, "//a[@title='"+DisclaimerName+"']/../preceding-sibling::span/a[@title='Activate']", DisclaimerName+" Activate link", action.BOOLEAN, timeOut);
		if(ele !=null){
			if(click(driver, ele, DisclaimerName+" activate link", action.BOOLEAN)){
				if(click(driver, getDisclaimerActivationPopUpYesButton(timeOut), "Yes Button", action.BOOLEAN)){
					return true;
				} else {
					appLog.error("Disclaimer activation pop up yes button cannot be clicked, So cannot activate the disclaiemr '"+DisclaimerName+"'");
					BaseLib.sa.assertTrue(false,"Disclaimer activation pop up yes button cannot be clicked, So cannot activate the disclaiemr '"+DisclaimerName+"'");
				}
			} else {
				appLog.error(DisclaimerName+" activateion link cannot be clicked, So cannot activate disclaimer.");
				BaseLib.sa.assertTrue(false,DisclaimerName+" activateion link cannot be clicked, So cannot activate disclaimer."); 
			}
		} else {
			appLog.error(DisclaimerName+" is not present in the list with activate link.");
			BaseLib.sa.assertTrue(false,DisclaimerName+" is not present in the list with activate link.");
		}
		return false;
	}
	
	/**
	 * @author Ankur Rana
	 * @param DisclaimerName
	 * @param timeOut
	 * @return true/false
	 */
	public boolean clickOnActivationLinkOfDisclaimer(String DisclaimerName, int timeOut) {
		if(click(driver, FindElement(driver, "//a[@title='"+DisclaimerName+"']/../preceding-sibling::span/a[@title='Activate']", DisclaimerName+" activation link", action.BOOLEAN, timeOut), DisclaimerName+" disclaimer actiavtion link", action.BOOLEAN)){
			return true;
		} else {
			appLog.error("Cannot click on activation link of disclaimer "+DisclaimerName);
			BaseLib.sa.assertTrue(false,"Cannot click on activation link of disclaimer "+DisclaimerName);
		}
		return false;
	}
	
	/**
	 * @author Ankur Rana
	 * This method is used to switch into the discalimer frame
	 */
	public void recover(){
		refresh(driver);
		if(switchToFrame(driver, 30, getNavatarInvestorAddOnFrame(60))){
			System.err.println("Successfully switched to frame.");
			switchToFrame(driver, 30, getNavatarInvestorAddOnFrame1(60));
		}
	}
	
	/**
	 * @author Ankur Rana
	 * @return int 
	 */
	public int getWaitingDisclaimerCount(){
		if(getWaitingDisclaimerCount(30)!=null){
			return Integer.parseInt(getWaitingDisclaimerCount(30).getText().trim());
		} else {
			appLog.error("Waiting discalimer count is not present.");
			BaseLib.sa.assertTrue(false,"Waiting discalimer count is not present.");
		}
		return -1;
	}
	
	/**
	 * @author Ankur Rana
	 * @return int
	 */
	public int getAcceptedDiscalimerCount(){
		if(getAcceptedDisclaimerCount(30)!=null){
			return Integer.parseInt(getAcceptedDisclaimerCount(30).getText().trim());
		} else {
			appLog.error("Accepted discalimer count is not present.");
			BaseLib.sa.assertTrue(false,"Accepted discalimer count is not present.");
		}
		return -1;
	}
	
	/**
	 * @author Ankur Rana
	 * @return String
	 */
	public String getActiveDisclaimerStatisticsValue(){
		String text = getAttribute(driver, getActiveDisclaimerStatisticValue(30), "Active disclaimer statistics value", "title");
		if(text!=null && !text.isEmpty()){
			return text;
		} else {
			appLog.error("There is no value in the Active disclaimer Statistics");
			BaseLib.sa.assertTrue(false,"There is no value in the Active disclaimer Statistics");
		}
		return null;
	}
	
	/**
	 * @author Ankur Rana
	 * @return String array
	 */
	public String[] getCurrentStatusOfDisclaimer(){
		String [] statusAndColor = new String[2];
		if(getCurrentStatusOfDisclaimer(30)!=null){
			statusAndColor[0] = getText(driver, getCurrentStatusOfDisclaimer(30), "Current status of discalimer", action.BOOLEAN);
			if(getAttribute(driver, getCurrentStatusOfDisclaimer(30), "", "style").contains("rgb(0, 128, 0)")){
				statusAndColor[1] = "Green";
			} else /*if (getAttribute(driver, getCurrentStatusOfDisclaimer(30), "", "style").contains("background: green;"))*/{
				statusAndColor[1] = "Red";
			}
		} else {
			appLog.error("Disclaimer status is not present.");
			BaseLib.sa.assertTrue(false,"Disclaimer status is not present.");
		}
		return statusAndColor;
	}
	
	/**
	 * @author Ankur Rana
	 * @param activateDeactivate
	 * @param DisclaimerName
	 * @param View
	 * @param lastActivatedOn
	 * @param createdDate
	 * @return true/false
	 */
	public boolean verifyDisclaimerGrid(DisclaimerGrid activateDeactivate, String DisclaimerName, DisclaimerGrid View, DisclaimerGrid lastActivatedOn, DisclaimerGrid createdDate){
		WebElement ele = null;
		if(lastActivatedOn==null && createdDate != null){
			ele = FindElement(driver, "//a[@title='"+DisclaimerName+"']/../preceding-sibling::span[1]/a[@title='"+activateDeactivate+"']/../following-sibling::span[2]/a[@title='"+View+"']/../following-sibling::span[1]/div[@title='']/../following-sibling::span[1]/div[@title='"+getSystemDate("MM/dd/yyyy")+"' or @title='"+previousOrForwardDate(-1, "MM/dd/yyyy")+"']", "Grid Data", action.BOOLEAN, 30);
		} else if (lastActivatedOn != null && createdDate !=null){
			ele = FindElement(driver, "//a[@title='"+DisclaimerName+"']/../preceding-sibling::span[1]/a[@title='"+activateDeactivate+"']/../following-sibling::span[2]/a[@title='"+View+"']/../following-sibling::span[1]/div[@title='"+getSystemDate("MM/dd/yyyy")+"' or @title='"+previousOrForwardDate(-1, "MM/dd/yyyy")+"']/../following-sibling::span[1]/div[@title='"+getSystemDate("M/dd/yyyy")+"' or @title='"+previousOrForwardDate(-1, "M/dd/yyyy")+"']", "Grid Data", action.BOOLEAN, 30);
		}
		if(ele!=null){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @author Ankur Rana
	 * @return int
	 */
	public int getDisclaimerViewPopUpRecordCount(){
		if(getDisclaimerViewPopUpRecordCount(30)!=null){
			return Integer.parseInt(getDisclaimerViewPopUpRecordCount(30).getText().trim());
		} else {
			appLog.error("Discalimer view record count is not present.");
			BaseLib.sa.assertTrue(false,"Discalimer view record count is not present.");
		}
		return -1;
	}
	
	/**
	 * @author Hervinder
	 * @return int 
	 */
	public int getDisclaimerRecordCount(){
	  String str=getDisclaimeRecordCount(60).getText();
	  str=str.replaceAll("[^0-9]", "");
	  int a=Integer.parseInt(str);
	  System.out.println(a);
	 return a;
	 }
	
	/**
	 * @author Hervinder
	 * @param contactName
	 * @param email
	 * @param firm
	 * @param status
	 * @param acceptedOn
	 * @return true/false
	 */
	public boolean verifyDisclaimerStatisticsGrid(String contactName, String email, String firm, String status,
			String acceptedOn) {
		List<WebElement> cntactName = FindElements(driver, "//span[contains(@id,'Disclaimer_Statistics-cell-0')]/a",
				"Contact Name");
		List<WebElement> contactEmail = FindElements(driver, "//span[contains(@id,'Disclaimer_Statistics-cell-1')]/a",
				"Email");
		List<WebElement> contactFirm = FindElements(driver, "//span[contains(@id,'Disclaimer_Statistics-cell-2')]/a",
				"Firm");
		List<WebElement> contactStatus = FindElements(driver,
				"//span[contains(@id,'Disclaimer_Statistics-cell-3')]/img", "Status");
		List<WebElement> contactAcceptedOn = FindElements(driver,
				"//span[contains(@id,'Disclaimer_Statistics-cell-4')]/span", "AcceptedOn");

		if (!cntactName.isEmpty()) {
			for (int i = 0; i < cntactName.size(); i++) {
				if (cntactName.get(i).getText().contains(contactName) && contactEmail.get(i).getText().contains(email)
						&& contactFirm.get(i).getText().contains(firm)
						&& contactStatus.get(i).getAttribute("title").contains(status)
						&& contactAcceptedOn.get(i).getText().contains(acceptedOn)) {
					appLog.info(contactName + " Contact Name is Verfied");
					appLog.info(email + " Email is Verfied");
					appLog.info(firm + " Firm is Verfied");
					appLog.info(status + " Status is Verfied");
					appLog.info(acceptedOn + " Accepted On  is Verfied");
					break;

				}
				if (i == cntactName.size() - 1) {
					appLog.info("Contact are not matched");
					return false;
				}

			}
			return true;

		} else {
			appLog.error("Contact is not available  in contact grid.");
			BaseLib.sa.assertTrue(false, "Contact is not available  in contact grid.");

		}
		return false;
	}
	
	/**
	 * @author Ankur Rana
	 * @param contactName
	 * @return
	 */
	public boolean clickOnContactNameLink(String contactName){
		WebElement ele = FindElement(driver, "//span[contains(@id,'Disclaimer_Statistics-cell-0')]/a[@title='"+contactName+"']", "Contact Name link", action.BOOLEAN, 30);
		if(ele != null){
			if(click(driver, ele, contactName+" Contact name link", action.BOOLEAN)){
				return true;
			} else {
				appLog.error(contactName+" Contact name link cannot be clicked, So cannot check the functionality.");
				BaseLib.sa.assertTrue(false,contactName+" Contact name link cannot be clicked, So cannot check the functionality.");
			}
		} else {
			appLog.error(contactName+" Contact Name Link is not present, So cannot check the link functionality.");
			BaseLib.sa.assertTrue(false,contactName+" Contact Name Link is not present, So cannot check the link functionality.");
		}
		return false;
	}
	
	/**
	 * @author Ankur Rana
	 * @param firmName
	 * @return true/false
	 */
	public boolean clickOnFirmNameLink(String firmName){
		WebElement ele = FindElement(driver, "//span[contains(@id,'Disclaimer_Statistics-cell-2')]/a[@title='"+firmName+"']", "Firm Name link", action.BOOLEAN, 30);
		if(ele != null){
			if(click(driver, ele, firmName+" Firm name link", action.BOOLEAN)){
				return true;
			} else {
				appLog.error(firmName+" Firm name link cannot be clicked, So cannot check the functionality.");
				BaseLib.sa.assertTrue(false,firmName+" Firm name link cannot be clicked, So cannot check the functionality.");
			}
		} else {
			appLog.error(firmName+" Firm Name Link is not present, So cannot check the link functionality.");
			BaseLib.sa.assertTrue(false,firmName+" Firm Name Link is not present, So cannot check the link functionality.");
		}
		return false;
	}
	
	/**
	 * @author Ankur Rana
	 * @param email
	 * @returntrue/false
	 */
	public boolean clickOnEmailLink(String email){
		WebElement ele = FindElement(driver, "//span[contains(@id,'Disclaimer_Statistics-cell-1')]/a[@title='"+email+"']", "Email link", action.BOOLEAN, 30);
		if(ele != null){
			if(click(driver, ele, email+" Email link", action.BOOLEAN)){
				return true;
			} else {
				appLog.error(email+" Email link cannot be clicked, So cannot check the functionality.");
				BaseLib.sa.assertTrue(false,email+" Email link cannot be clicked, So cannot check the functionality.");
			}
		} else {
			appLog.error(email+" Email Link is not present, So cannot check the link functionality.");
			BaseLib.sa.assertTrue(false,email+" Email Link is not present, So cannot check the link functionality.");
		}
		return false;
	}
	
	
	
	
	
	
	
}
