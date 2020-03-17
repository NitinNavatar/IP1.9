/**
 * 
 */
package com.navatar.pageObjects;

import static com.navatar.generic.CommonLib.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static com.navatar.generic.AppListeners.*;

import com.navatar.generic.ExcelUtils;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;

/**
 * @author Parul Singh
 *
 */
public class HomePageBusineesLayer extends HomePage implements HomePageErrorMessage {
	/**
	 * @param driver
	 */
	public HomePageBusineesLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @author Parul Singh
	 * @return true/false
	 */
	public boolean clickOnRecycleBin(){
		if(getRecycleBinLink(20)==null){
			if(click(driver, getSideBarIcon(60), "Side Bar Icon", action.SCROLLANDBOOLEAN)){	
				appLog.info("Able to click on side bar icon");
				if(click(driver, getRecycleBinLink(60), "recycle bin link", action.SCROLLANDBOOLEAN)){
					return true;
				}else {
					appLog.error("Not Able to click Recycle Bin Link");
				}
			}else{
				appLog.info("Not able to click on side bar icon");
			}
		}else {
			if(click(driver, getRecycleBinLink(60), "recycle bin link", action.SCROLLANDBOOLEAN)){
				appLog.info("clicked on Recycle Bin Icon");
				return true;
			}else {
				appLog.error("Not Able to click Recycle Bin Link");
			}
		}
		return false;
	}

	/**
	 * @author Parul Singh
	 * @param RestoreValue
	 * @return true/false
	 */
	public boolean restoreValuesFromRecycleBin(String RestoreValue) {
		if (clickOnRecycleBin()) {
			if (sendKeys(driver, getRecycleBinSearchBox(60), RestoreValue,
					"Recycle Bin Search Text Box value : " + RestoreValue, action.BOOLEAN)) {

				if (click(driver, getRecycleBinSearchButton(60), "Recycle Bin Search Btn", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(3000);
					WebElement check = FindElement(driver, "//th[text()='" + RestoreValue + "']/..//input", "CheckBox",
							action.SCROLLANDBOOLEAN, 30);
					if (click(driver, check, "CheckBox", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(3000);
						if (click(driver, getUnDeleteButton(30), "CheckBox", action.BOOLEAN)) {
							ThreadSleep(3000);
							return true;
						} else {
							appLog.info("Not Able to Restore value : " + RestoreValue);
						}
					} else {
						appLog.info("Not Able to click on Check Box");
					}
				} else {
					appLog.info("Not Able to click Recycle Bin Search Btn");
				}
			} else {
				appLog.info("Not Able to Enter value on  Recycle Bin Search Text Box");
				}
		} else {
			appLog.info("Not Able to click Recycle Bin Link");
		}
		return false;
	}
		
	/**
	 * @author Parul Singh
	 * @param activityType
	 * @param fund
	 * @param Workspce
	 * @param documentName
	 * @param firm
	 * @param Contact
	 * @param Date
	 * @return true/false
	 */
	public boolean verifyAlertOnHomePage(String activityType,String fund,String Workspce,String documentName,String firm,String Contact,String Date){
	WebElement ele=null;
	if(activityType.equalsIgnoreCase("Firm Profile Updated") || activityType.equalsIgnoreCase("Contact Profile Updated")){
		ele=FindElement(driver, "//a[text()='"+activityType+"']/../..//span[6]//span[text()='"+firm+"']/../..//span[7]/a[text()='"+Contact+"']/../..//span[8]//div[text()='"+getSystemDate("M/dd/yyyy")+"' or text()='"+previousOrForwardDate(-1, "M/dd/yyyy")+"' or text()='"+getSystemDate("MM/dd/yyyy")+"' or text()='"+previousOrForwardDate(-1, "MM/dd/yyyy")+"']", "grid data", action.SCROLLANDBOOLEAN, 20);
	}		
	if(ele!=null){
		return true;
	} else {
		return false;
	}		
		
	}	
	
	/**
	 * @author Parul Singh
	 * @return true/false
	 */
	public boolean alertRecordCountAtHomePage(){
		int recWithValue=0;
		String [] recvalue=null;
		if(getRecordLabelWithValueOnHomeAlert(60)!=null){
				recvalue =getRecordLabelWithValueOnHomeAlert(10).getText().trim().split(":");
				recWithValue=Integer.parseInt(recvalue[1].trim());	
				if(ExcelUtils.writeData(recWithValue, "FilePath",excelLabel.TestCases_Name, "M2tc025_CheckValuesInSelectboxesFirmProfilePage", excelLabel.HomePageAlertCount)){
					return true;
				}else {
					appLog.error("Not able to write Home Alert Count in Filepath excel sheet");
				}
		}else {
			appLog.error("Home Alert Count is  not visible so cannot get home page alert count");
		}
		return false;
	}
	
}
