/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;
import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib;
import com.navatar.generic.EmailLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.generic.CommonVariables;
import com.navatar.generic.CommonLib.CheckBoxName;
import com.navatar.generic.CommonLib.ContentGridArrowKeyFunctions;
import com.navatar.generic.CommonLib.EnableDisable;
import com.navatar.generic.CommonLib.ErrorMessageType;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.ManageApprovalTabs;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.SortOrder;
import com.navatar.generic.CommonLib.UpdateIgnore;
import com.navatar.generic.CommonLib.Workspace;

import static com.navatar.generic.CommonLib.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import javax.print.attribute.standard.DocumentName;

import static com.navatar.generic.CommonVariables.*;
import static com.navatar.generic.AppListeners.*;
import static com.navatar.generic.BaseLib.sa;

/**
 * @author Parul Singh
 *
 */
/**
 * @author Akul Bhutani
 *
 */
public class FundsPageBusinessLayer extends FundsPage implements FundsPageErrorMessage{

	public List<String> notFoundFolder = new ArrayList<String>();
	/**
	 * @param driver
	 */
	public FundsPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author Parul Singh
	 * @param fundName
	 * @param fundType
	 * @param investmentCategory
	 * @return true/false
	 */
	public boolean createFund(String fundName, String fundType, String investmentCategory) {
		if (click(driver, getNewButton(60), "New Button", action.BOOLEAN)) {
			if(sendKeys(driver, getFundName(60), fundName, "Fund Name", action.BOOLEAN)){
				if (selectVisibleTextFromDropDown(driver, getFundType(60), "Fund Type", fundType)) {
					if (selectVisibleTextFromDropDown(driver, getInvestmentCategory(60), "Investment Category",
						investmentCategory)) {
						if (click(driver, getSaveButton(60), "Save Button", action.BOOLEAN)) {
							if (getFundNameInViewMode(60) != null) {
							String fundNameViewMode =getText(driver, getFundNameInViewMode(60), "Fund Name", action.BOOLEAN);
							if (fundNameViewMode.equalsIgnoreCase(fundName)) {
								appLog.info("Fund is created successfully.:" + fundName);
								return true;
							} else {
								appLog.error("Fund is not created successfully.:" + fundName);
							}
						} else {
							appLog.error("Not able to find Fund Name in View Mode");
						}
					} else {
						appLog.error("Not able to click on save button");
					}
				} else {
					appLog.error("Not able to select investment category");
				}
			} else {
				appLog.error("Not able to select fund type");
			}
			}else{
				appLog.error("Not able to enter fund name in text box");
			}
	} else {
			appLog.info("Not able to click on new button so we cannot create fund");
		}
		return false;
	}
	
	/*public boolean clickOnCreatedFund(String fundName) {
		if (getSelectedOptionOfDropDown(driver, getViewDropdown(60), "View dropdown", "text").equalsIgnoreCase("All")) {
			if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {

			}
			else {
				appLog.error("Go button not found");
				return false;
			}
		}
		else{
			if (selectVisibleTextFromDropDown(driver, getViewDropdown(60),"View dropdown","All") ){
			}
			else {
				appLog.error("All Funds not found in dropdown");
				return false;
			}

		}
	
			WebElement fund = FindElement(driver,
					"//div[@class='x-panel-bwrap']//a//span[contains(text(),'" + fundName + "')]", "Fund Name",
					action.BOOLEAN, 60);
			if (fund != null) {
				if (click(driver, fund, "Fund Name", action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on fund name.:" + fundName);
					} else {
					appLog.error("Not able to click on fund Name");
					return false;
				}
			} else {
				appLog.error("Fund Name is not Displaying.:" + fundName);
				return false;
			}
		
		return true;
	}*/
	
	/**
	 * @author Parul Singh
	 * @param fundName
	 * @param updatedFundName
	 * @param variable_value
	 * @return true/false
	 */
	public boolean updateCreatedFund(String fundName,String updatedFundName,String variable_value) {
		if(click(driver, getEditButton(10), "edit button", action.SCROLLANDBOOLEAN)) {
			if(sendKeys(driver, getFundName(60), updatedFundName, "Fund Name", action.BOOLEAN)){
				if (click(driver, getSaveButton(60), "Save Button", action.BOOLEAN)) {
					if (getFundNameInViewMode(60) != null) {
						String fundNameViewMode =getText(driver, getFundNameInViewMode(60), "Fund Name", action.BOOLEAN);
						if (fundNameViewMode.equalsIgnoreCase(updatedFundName)) {
							appLog.info("Fund is updated successfully. :" + updatedFundName);
							ExcelUtils.writeData(updatedFundName, "Funds",excelLabel.Variable_Name,variable_value, excelLabel.Fund_Name);
							return true;
						} else {
							appLog.error("Fund is not updated successfully. :" + updatedFundName);
						}
					} else {
						appLog.error("Not able to find Fund Name in View Mode");
					}
				}else {
					appLog.error("Not able to click on save button so cannot update fundName: "+fundName);
				}
			}else {
				appLog.error("Not able to pass value in fundName text box so cannot update fund Name: "+updatedFundName);
			}
		}else {
			appLog.error("Not able to click on edit icon so cannot update fundName: "+fundName);
		}
		return false;
	}

//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public boolean verifyFolderStructure(Object path, String institutionName, String checkStdFolder, String pageName) {
//		boolean flag = true;
//		String insti;
//		if (path == "") {
//			if (verifyFolderPath("", institutionName, pageName)) {
//				appLog.info("Successfully clicked on institution folder.");
//				flag = true;
//			} else {
//				flag = false;
//			}
//		} else if (path instanceof Map) {
//			Set<String> paths = ((Map) path).keySet();
//			Iterator<String> i = paths.iterator();
//			while (i.hasNext()) {
//				String string = (String) i.next();
//				if (institutionName != null) {
//					if (!((Map) path).get(string).toString().equalsIgnoreCase("Standard")) {
//						insti = null;
//					} else {
//						insti = institutionName;
//					}
//					if (((Map) path).get(string).toString().equalsIgnoreCase("Standard")
//							&& checkStdFolder.equalsIgnoreCase("No")) {
//						appLog.info("Standard folder will not be checked");
//						break;
//					}
//					if (verifyFolderPath(string, insti, pageName)) {
//						appLog.info("Folder path successfully found: " + string);
//					} else {
//						appLog.info("Folder path not found: " + string);
//						flag = false;
//					}
//				} else {
//					if (verifyFolderPath(string)) {
//						appLog.info("Folder path successfully found: " + string);
//					} else {
//						appLog.info("Folder path not found: " + string);
//						flag = false;
//					}
//				}
//			}
//		} else if (path instanceof String) {
//			String paths[] = ((String) path).split(",");
//			for (int i = 0; i < paths.length; i++) {
//				if (checkStdFolder.equalsIgnoreCase("Traverse")) {
//					if (verifyFolderPath(paths[i], institutionName, pageName)) {
//						appLog.info("Folder path successfully found: " + paths[i]);
//					} else {
//						appLog.info("Folder path not found: " + paths[i]);
//						flag = false;
//					}
//				} else if (verifyFolderPath(paths[i])) {
//					appLog.info("Folder path successfully found: " + paths[i]);
//				} else {
//					appLog.info("Folder path not found: " + paths[i]);
//					flag = false;
//				}
//			}
//		}
//		if (!flag) {
//			appLog.info("Folder Structure is not verified.");
//		}
//		return flag;
//	}
//
//	public boolean verifyFolderPath(String path) {
//		String path1[] = path.split("/");
//		List<String> notFoundFolders = new ArrayList<String>();
//		boolean found = true;
//		String xpath = "//a[contains(text(),'All Folders')]/../../../ul/li/div//a[contains(text(),'" + path1[0] + "')]";
//		String xpath1 = "/../../following-sibling::ul//a[contains(text(),'" + "folderName" + "')]";
//		for (int i = 1; i <= path1.length; i++) {
//			if (FindElement(driver, xpath, "Folder: " + path1[i - 1], action.BOOLEAN, 10) != null) {
//				// appLog.info("Folder Found: "+path1[i-1]);
//			} else {
//				// appLog.info("Folder Not Found: "+path1[i-1]);
//				found = false;
//				notFoundFolders.add(path1[i - 1]);
//			}
//			if (i != path1.length)
//				xpath = xpath + "/../../following-sibling::ul//a[contains(text(),'" + path1[i] + "')]";
//		}
//		if (!found) {
//			for (int k = 0; k < notFoundFolders.size(); k++) {
//				if (k == 0)
//					appLog.info("List of not found folder of path \"" + path + "\" are: ");
//				appLog.info(notFoundFolders.get(k));
//			}
//		} else {
//			appLog.info("Folder path is verified: " + path);
//		}
//		return found;
//	}
	
	/**
	 * @author Ankur Rana
	 * @param path
	 * @param institutionName
	 * @param limitedPartner
	 * @param fundName
	 * @param PageName
	 * @param Workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean verifyFolderPathdummy(String path, String institutionName, String limitedPartner, String fundName, PageName PageName, Workspace Workspace, int timeOut){
		String workspaceSelector="";
		boolean found = true;
		boolean flag = false;
		String middleXpath = "/../../following-sibling::ul/li/div//span[text()='";
		if(Workspace!=null){
			if(PageName.toString().equalsIgnoreCase("FundsPage") && Workspace.toString().equalsIgnoreCase("FundraisingWorkspace")){
				workspaceSelector = "//div[@id='frworkspace']";
				if(institutionName!=null) {
					middleXpath = "/../../../following-sibling::ul/li/div//span[text()='";
				}
			} else if (PageName.toString().equalsIgnoreCase("FundsPage") && Workspace.toString().equalsIgnoreCase("InvestorWorkspace")) {
				workspaceSelector = "//div[@id='invworkspace']";
				if(limitedPartner!=null) {
					middleXpath = "/../../../following-sibling::ul/li/div//span[text()='";
				}
			} else if((PageName.toString().equalsIgnoreCase("InstitutionsPage") || PageName.toString().equalsIgnoreCase("ContactsPage")) && Workspace.toString().equalsIgnoreCase("FundraisingWorkspace")){
				workspaceSelector = "//div[@id='divFrWorkspace']";
			} else if ((PageName.toString().equalsIgnoreCase("InstitutionsPage") || PageName.toString().equalsIgnoreCase("ContactsPage")) && Workspace.toString().equalsIgnoreCase("InvestorWorkspace")) {
				workspaceSelector = "//div[@id='Investorgrid_div']";
			}
		}
		String startingXpath=workspaceSelector+"//span[contains(text(),'All Folders')]/../../../ul/li/div//span[text()='";
		String endingXpath="']";
		String combinedXpath = "";
		if(institutionName!=null){
			if(limitedPartner!=null){
				path =institutionName+"/"+limitedPartner+"/"+path;
			} else {
				path =institutionName+"/"+path;
			}
		}else if(limitedPartner!=null && (PageName.toString().equalsIgnoreCase("CommitmentsPage")||PageName.toString().equalsIgnoreCase("InstitutionsPage")||PageName.toString().equalsIgnoreCase(PageName.CurrentInvestmentPgae.toString()))){
			path = limitedPartner+"/"+path;
		}
		if(PageName.toString().equalsIgnoreCase("ContactsPage")||PageName.toString().equalsIgnoreCase("InstitutionsPage")){
			path = fundName+"/"+path;
		}
		String[] folderToFind = path.split("/");
		
		for(int i = 0 ; i < folderToFind.length ; i++){
			if(i==0){
				combinedXpath = startingXpath+folderToFind[i]+endingXpath;
			} else {
				combinedXpath += middleXpath + folderToFind[i] +endingXpath;
				middleXpath = "/../../following-sibling::ul/li/div//span[text()='";
			}
			if(click(driver, FindElement(driver, combinedXpath, folderToFind[i]+" :Folder", action.BOOLEAN, timeOut), folderToFind[i]+" :Folder", action.SCROLLANDBOOLEAN)){
				appLog.info("Successfully clicked on the folder "+folderToFind[i]);
			} else {
				appLog.error(folderToFind[i]+" :Folder not found.");
				found=false;
				break;
			}
		}
		if(found==false){
			if(isAlertPresent(driver)){
				String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
				switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
				if(msg.trim().toLowerCase().contains("error") || msg.trim().toLowerCase().contains("status") || msg.trim().toLowerCase().contains("code")){
					driver.navigate().refresh();
					if(getFrame(PageName, timeOut)!=null){
//						scrollDownThroughWebelement(driver, getDealRoomSection(30), "Deal room view.");
						switchToFrame(driver, 30, getFrame(PageName, timeOut));
					}
					if(verifyFolderPathdummy(path, institutionName, limitedPartner, fundName, PageName, Workspace, timeOut)){
						found=true;
					} else {
						found = false;
					}
				} else {
					String loc = screenshot(currentlyExecutingTC);
					appLog.error("Folder verification failed due to some intermittent issue. Kindly check the screenshot: "+loc);
					BaseLib.sa.assertTrue(false, "Folder verification failed due to some intermittent issue. Kindly check the screenshot: "+loc);
				}
			} else if (FindElement(driver, "//img[@class='poweredByImage']", null, action.BOOLEAN, 0)!=null){
				flag=true;
			} else if (FindElement(driver, "//img[@src='/resource/1511337238000/SiteSamples/img/clock.png']", null, action.BOOLEAN, 0)!=null){
				flag=true;
			} else if (FindElement(driver, "//img[@src='/resource/1511337238000/SiteSamples/img/warning.gif']", null, action.BOOLEAN, 0)!=null){
				flag=true;
			} else if (FindElement(driver, "//span[@class='title'][text()='Error: Error occurred while loading a Visualforce page.']", null, action.BOOLEAN, 0)!=null){
				flag=true;
			}
			if(flag){
				driver.navigate().refresh();
				if(getFrame(PageName, timeOut)!=null){
//					scrollDownThroughWebelement(driver, getDealRoomSection(30), "Deal room view.");
					switchToFrame(driver, 30, getFrame(PageName, timeOut));
				}
				if(verifyFolderPathdummy(path, institutionName, limitedPartner, fundName, PageName, Workspace, timeOut)){
					found=true;
				} else {
					found = false;
				}
			}
			
		}
		return found;
	}
	
	/**
	 * @author Ankur Rana
	 * @param institutionName
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean clickOnInstituionFolder(String institutionName, Workspace workspace, int timeOut){
		String workspaceSelector="";
		if(workspace!=null && workspace.toString().equalsIgnoreCase("FundraisingWorkspace")){
			workspaceSelector = "//div[@id='frworkspace']";
		} else if (workspace!=null && workspace.toString().equalsIgnoreCase("InvestorWorkspace")) {
			workspaceSelector = "//div[@id='invworkspace']";
		}
		if(click(driver, FindElement(driver, workspaceSelector+"//span[contains(text(),'All Folders')]/../../../ul/li/div//span[text()='"+institutionName+"']", institutionName+" :Institution Folder", action.BOOLEAN, timeOut), institutionName+" :Institution Folder", action.BOOLEAN)){
			return true;
		}
		return false;
	}
	
	/**
	 * @author Ankur Rana
	 * @param institutionName
	 * @param limitedPartnerName
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean clickOnLimitedPartnerFolder(String institutionName, String limitedPartnerName, Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())){
			workspaceSelector = "//div[@id='invworkspace']";
		} else if (workspace.toString().equalsIgnoreCase(Workspace.CurrentInvestment.toString())){
			if(click(driver, FindElement(driver, workspaceSelector+"//span[contains(text(),'All Folders')]/../../../ul/li/div//span[text()='"+limitedPartnerName+"']", limitedPartnerName+" :Institution Folder", action.BOOLEAN, timeOut), limitedPartnerName+" :Institution Folder", action.BOOLEAN)){
				return true;
			}
		}
		if(click(driver, FindElement(driver, workspaceSelector+"//span[contains(text(),'All Folders')]/../../../ul/li/div//span[text()='"+institutionName+"']", institutionName+" :Institution Folder", action.BOOLEAN, timeOut), institutionName+" :Institution Folder", action.BOOLEAN)){
			if(click(driver, FindElement(driver, workspaceSelector+"//span[contains(text(),'All Folders')]/../../../ul/li/div//span[text()='"+institutionName+"']/../../../following-sibling::ul/li/div//span[text()='"+limitedPartnerName+"']", limitedPartnerName+" :Institution Folder", action.BOOLEAN, timeOut), limitedPartnerName+" :Institution Folder", action.BOOLEAN)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param firmName
	 * @param emailID
	 * @param folderType
	 * @param permission
	 * @param applyInvite
	 * @param sendInvitationMail
	 * @param contactAccessViewfolderName
	 * @param workspace
	 * @param contactSearchKeyword
	 * @return true/false
	 */
	public boolean inviteContact(String instutionOrLPName,String emailID,String folderpath,FolderType FolderType,String permission,String applyInvite,String sendInvitationMail,String contactAccessViewfolderName,Workspace workspace,String contactSearchKeyword ) {
	String[] splitedEmailId=emailID.split(",");
	String [] spiltedInstitutionOrLP=null;
	String ins=null;
	String lpName=null;
	switchToFrame(driver, 30, getFrame(PageName.FundsPage, 30));
	scrollDownThroughWebelement(driver, getWorkspaceSectionView(workspace, 30), workspace.toString()+" Section view");
	if(FolderType.toString().equalsIgnoreCase(FolderType.Standard.toString())) {
		if(instutionOrLPName!=null) {
			spiltedInstitutionOrLP=instutionOrLPName.split("/");
			if(spiltedInstitutionOrLP.length>1) {
				ins=spiltedInstitutionOrLP[0];
				lpName=spiltedInstitutionOrLP[1];
			}else {
				ins=spiltedInstitutionOrLP[0];
			}
			if(folderpath==null){
				if(lpName!=null){
					clickOnLimitedPartnerFolder(ins, lpName, workspace, 30);
				} else {
					clickOnInstituionFolder(ins, workspace, 30);
				}
			} else {
				if(verifyFolderPathdummy(folderpath,ins,lpName,null, PageName.FundsPage, workspace, 60)) {
					appLog.info("folder staructure is verified successfully: "+instutionOrLPName+"/"+folderpath);
				}else {
					appLog.error("folder structure is not verifed: "+instutionOrLPName+"/"+folderpath+" so cannot invite contact: "+emailID);
					switchToDefaultContent(driver);
					return false;
				}
			}
		}else {
			appLog.error("InstituionOrLP Name should not null when FolderType is Standard: "+instutionOrLPName);
			switchToDefaultContent(driver);
			return false;
		}
	}else {
		if(verifyFolderPathdummy(folderpath,null,null,null, PageName.FundsPage, workspace, 60)) {
			appLog.info("folder staructure is verified successfully: "+folderpath);
		}else {
			appLog.error("folder structure is not verifed: "+folderpath+" so cannot invite contact: "+emailID);
			switchToDefaultContent(driver);
			return false;
		}
	}		
	
		if(click(driver, getContactAccessIcon(workspace, 60), "Contact Access Icon of "+workspace, action.SCROLLANDBOOLEAN)) {
			ThreadSleep(2000);
			if(verifyContactAccessExpandCollapse(workspace)) {
				appLog.info(workspace+" contact access popUp is expanded successfully.");
				if(contactSearchKeyword!=null) {
					if(sendKeys(driver,getSearchTextBox(workspace, 60), contactSearchKeyword,workspace+" search text box", action.SCROLLANDBOOLEAN)) {
						appLog.info("enter the value in search text box : "+contactSearchKeyword);
						if(click(driver, getSearchBtn(workspace, 60), workspace+" search button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on  "+workspace+" search button");
						}else {
							appLog.error("Not able to click on "+workspace+" search button so cannot invite contact: "+emailID);
							if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
								appLog.error("Not able to click on contact access cancel button");
							}
							switchToDefaultContent(driver);
							return false;
						}
					}else {
						appLog.error("Not able to pass value in "+workspace+" search text box:  "+contactSearchKeyword+" so cannot invite contact: "+emailID);
						if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.error("Not able to click on contact access cancel button");
						}
						switchToDefaultContent(driver);
						return false;
					}
				}
					WebElement contactcheckBox=null;
					if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
							contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWFR_MA']//a[text()='"+emailID+"']/../..//input", emailID+" contact check box", action.SCROLLANDBOOLEAN, 30);
								scrollDownThroughWebelement(driver,contactcheckBox, "");
					}else if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
							contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWINV_MA']//a[text()='"+emailID+"']/../..//input", emailID+" contact check box", action.SCROLLANDBOOLEAN, 30);
								scrollDownThroughWebelement(driver,contactcheckBox, "");
					}
					if(contactcheckBox!=null) {
						ThreadSleep(3000);
						if(click(driver, contactcheckBox, emailID+"check box", action.BOOLEAN)) {
							appLog.info("clicked on contact check box : "+emailID);
							if(click(driver, getaddselectContactBtn(workspace, 30), workspace+" add select contact active button", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on add selected contact active button in "+workspace);
								ThreadSleep(5000);
								List<WebElement> listofContact= getselectContactEmailIDList(workspace);
								if(!listofContact.isEmpty()) {
									for(int i=0; i<splitedEmailId.length;i++) {
										for(int j=0; j<listofContact.size();j++) {
											String contactemail=listofContact.get(j).getText().trim();
											if(contactemail.equalsIgnoreCase(splitedEmailId[i])) {
												appLog.info(splitedEmailId[i]+" contact is displaying in selected contact access grid");
												break;
											}else {
												if(j==listofContact.size()-1) {
													appLog.error(contactemail+" is not matched with >>> "+splitedEmailId[i]);
													switchToDefaultContent(driver);
													return false;
												}
											}
										}
									}
									if(permission!=null) {
										WebElement downloadcheckBox=null;
										WebElement uploadCheckBox=null;
										for(int i=0; i<splitedEmailId.length; i++) {
											if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
													String downloadXpath="//span[contains(@id,'grid11_DealDetailBWFR-cell-3')]/a[text()='"+splitedEmailId[i]+"']/../../span[8]//input";
													String uploadXpath="//span[contains(@id,'grid11_DealDetailBWFR-cell-3')]/a[text()='"+splitedEmailId[i]+"']/../../span[9]//input";
												if(permission.equalsIgnoreCase("Upload")) {
													downloadcheckBox=FindElement(driver, downloadXpath, splitedEmailId[i]+" download check box", action.SCROLLANDBOOLEAN, 30);
												 	uploadCheckBox=FindElement(driver, uploadXpath, splitedEmailId[i]+" upload check box", action.SCROLLANDBOOLEAN, 30);
												}else if (permission.equalsIgnoreCase("download")) {
													downloadcheckBox=FindElement(driver, downloadXpath, splitedEmailId[i]+" download check box", action.SCROLLANDBOOLEAN, 30);
												}
												
											}else if (workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
													String downloadXpath="//span[contains(@id,'grid11_DealDetailBWINV-cell-3')]/a[text()='"+splitedEmailId[i]+"']/../../span[8]//input";
													String uploadXpath="//span[contains(@id,'grid11_DealDetailBWINV-cell-3')]/a[text()='"+splitedEmailId[i]+"']/../../span[9]//input";
												if(permission.equalsIgnoreCase("Upload")) {
													downloadcheckBox=FindElement(driver, downloadXpath, splitedEmailId[i]+" download check box", action.SCROLLANDBOOLEAN, 30);
												 	uploadCheckBox=FindElement(driver, uploadXpath, splitedEmailId[i]+" upload check box", action.SCROLLANDBOOLEAN, 30);
												}else if (permission.equalsIgnoreCase("download")) {
													downloadcheckBox=FindElement(driver, downloadXpath, splitedEmailId[i]+" download check box", action.SCROLLANDBOOLEAN, 30);
												}
											}
											if(permission.equalsIgnoreCase("Upload")) {
												if(click(driver, uploadCheckBox, splitedEmailId[i]+" upload check box", action.BOOLEAN)) {
													appLog.info("clicked on "+splitedEmailId[i]+" upload check box");
												}else {
													appLog.error("Not able to click on "+splitedEmailId[i]+" upload check box");
													if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
														appLog.error("Not able to click on contact access cancel button");
													}
													switchToDefaultContent(driver);
													return false;
												}
											}else if (permission.equalsIgnoreCase("download")) {
												if(click(driver, downloadcheckBox, splitedEmailId[i]+" download check box", action.BOOLEAN)) {
													appLog.info("clicked on "+splitedEmailId[i]+" download check box");
												}else {
													appLog.error("Not able to click on "+splitedEmailId[i]+" download check box");
													if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
														appLog.error("Not able to click on contact access cancel button");
													}
													switchToDefaultContent(driver);
													return false;
												}
											}
										}	
									}else {
										appLog.error("No need to provide any permission to the given contact: "+emailID);
									}
									if(applyInvite!=null || "Yes".equalsIgnoreCase(applyInvite)) {
										if(click(driver, getApplyBtn(workspace, 60), workspace+" apply button", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on apply button successfully");
											if(click(driver, getCloseBtn(workspace, 60), workspace+" close button", action.SCROLLANDBOOLEAN)) {
												appLog.info("Clicked on Close Buton");	
											}else {
												appLog.error("Not able to click on close button");
												switchToDefaultContent(driver);
												return false;
											}
											
										}else {
											appLog.error("not able to click on apply button so cannot invite contact: "+emailID);
											switchToDefaultContent(driver);
											return false;
										}
									}else {
										if("Cancel".equalsIgnoreCase(applyInvite)){
											if(click(driver, getCancelBtn(workspace, 60), workspace+" cancel button", action.SCROLLANDBOOLEAN)) {
												appLog.info("Clicked on cancel Buton");
												switchToDefaultContent(driver);
												return true;
											}else {
												appLog.error("Not able to click on close button");
												switchToDefaultContent(driver);
												return false;
											}
										}else {
											return true;
										}
									}
									if(sendInvitationMail!=null && "Yes".equalsIgnoreCase(sendInvitationMail)) {
										if(sendInvitationMail(workspace, emailID, contactAccessViewfolderName, contactSearchKeyword)) {
											appLog.info("email has been successfully send to contact: "+emailID);
										}else {
											appLog.error("Not able to send an email to contact: "+emailID);
											switchToDefaultContent(driver);
											return false;
										}
									}else {
										appLog.info("No need to send an email to contact: "+emailID);
									}
								}else {
									appLog.error(emailID+" contact is not visible in select contact access grid.");
									if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
										appLog.error("Not able to click on contact access cancel button");
									}
									switchToDefaultContent(driver);
									return false;
								}
								
							}else {
								appLog.error("Not able to click on add select contact button in "+workspace);
								if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
									appLog.error("Not able to click on contact access cancel button");
								}
								switchToDefaultContent(driver);
								return false;
							}
							
						}else {
							appLog.error("Not able to click on "+emailID+" contact check box so cannot invite contact: "+emailID);
							if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
								appLog.error("Not able to click on contact access cancel button");
							}
							switchToDefaultContent(driver);
							return false;
						}	
					}else {
						appLog.error(emailID+"contact is not available in the "+workspace+" contact access grid");
						if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.error("Not able to click on contact access cancel button");
						}
						switchToDefaultContent(driver);
						return false;
					}
			}else {
				appLog.error("Not able to expand "+workspace+" minus icon so cannot invite contact: "+emailID);
				if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
					appLog.error("Not able to click on contact access cancel button");
				}
				switchToDefaultContent(driver);
				return false;
			}	
		}else {
			appLog.error("Not able to click on contact access icon so cannot invite contact from "+workspace);
			if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
				appLog.error("Not able to click on contact access cancel button");
			}
			switchToDefaultContent(driver);
			return false;
		}
		switchToDefaultContent(driver);
		return true;
		
	}
	/**
	 * @author Akul Bhutani
	 * @param emailID
	 * @param workspace
	 * @return true/false
	 */
	public boolean revokeContactAccess(String emailID, Workspace workspace) {
		if (click(driver, getContactAccessIcon(workspace, 60), "contact access icon", action.SCROLLANDBOOLEAN)) {
			if (verifyContactAccessExpandCollapse(workspace)){
				WebElement remove = isDisplayed(driver, FindElement(driver, "//a[text()='"+emailID+"']/../../span/a[@title='Remove']", "remove link in front of contact email", action.BOOLEAN, 60), "Visibility", 60, "Remove link");
				if (remove!=null) {
					if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on remove link");
						click(driver, getRemoveAccessClose(workspace, 30), "remove access close button", action.BOOLEAN);
						if (click(driver, getCancelBtn(workspace, 30), "contact access cancel button", action.BOOLEAN)) {
							return true;
						}

					}else {
						appLog.error("Not able to click on remove link of contact: "+emailID);
					}
				}
				else {
					appLog.error("remove button adjacent to "+emailID+" is not found");
				}
			}
			else {
				appLog.error("could not expand contact access window");
			}
		}
		else {
			appLog.error("contact access icon is not clickable");
		}
		return false;

	}
/**
 	
* @author Ankit Jaiswal
 * @param workspace
 * @return true/false
 */
	public boolean verifyContactAccessExpandCollapse(Workspace workspace) {
	if(workspace.toString().equalsIgnoreCase("FundraisingWorkspace")) {
		if (getfWR_contactAccessMinusIcon(60) != null) {
			appLog.info(workspace+" Contact Access Pop is displaying collapsed.");
			if(click(driver, getfWR_contactAccessMinusIcon(60), "Contact Access Minus Icon", action.SCROLLANDBOOLEAN)) {
				if(getfWR_contactAccessPlusIcon(30)!=null) {
					appLog.info(workspace+" Contact Access popup is expanded successfully.");
					return true;
				}else {
					appLog.error(workspace+" Contact Access popup is not expanded");
				}
								
			}else {
				appLog.error(workspace+" contact access minus icon is clickable so cannot expand it");
			}
		} else {
			appLog.info("cotact access minus icon is not visible so cannot expand "+workspace+" popUp");	
		}	
	}else if (workspace.toString().equalsIgnoreCase("InvestorWorkspace")) {
		if (getINV_contactAccessMinusIcon(60) != null) {
			appLog.info(workspace+" Contact Access Pop is displaying collapsed.");
			if(click(driver, getINV_contactAccessMinusIcon(60), "Contact Access Minus Icon", action.SCROLLANDBOOLEAN)) {
				if(getINV_contactAccessPlusIcon(30)!=null) {
					appLog.info(workspace+" Contact Access popup is expanded successfully.");
					return true;
				}else {
					appLog.error(workspace+" Contact Access popup is not expanded");
				}
								
			}else {
				appLog.error(workspace+" contact access minus icon is clickable so cannot expand it");
			}
		} else {
			appLog.info("cotact access minus icon is not visible so cannot expand "+workspace+" popUp");	
		}	
	} 		
	return false;
}
	
	/**
	 * @author Ankit Jaiswal
	 * @param workspace
	 * @param contactEmailId
	 * @param folderName
	 * @param contactSearchKeyword
	 * @return true/false
	 */
	public boolean sendInvitationMail(Workspace workspace, String contactEmailId, String contactAccessViewfolderName,
			String contactSearchKeyword) {
		if (click(driver, getmanageEmails(workspace, 60), workspace + " manage emails icon", action.SCROLLANDBOOLEAN)) {
			appLog.info("clicked on " + workspace + " manage emails icon");
			if (contactAccessViewfolderName != null) {
				scrollDownThroughWebelement(driver, getManageEmailContactAccessViewDropDownList(30), "");
				if (selectVisibleTextFromDropDown(driver, getManageEmailContactAccessViewDropDownList(30),
						workspace + " contact access view drop down list", contactAccessViewfolderName)) {
					appLog.info("folder name is selected from contact access drop down list :" + contactAccessViewfolderName);

				} else {
					appLog.error("folder name is not available in " + workspace
							+ " contact access view drop down list so cannot send email to contact: " + contactEmailId);
					return false;
				}
			}
			if (contactSearchKeyword != null) {
				if (sendKeys(driver, getManageEmailSearchTextBox(30), contactSearchKeyword,
						workspace + " search text box", action.SCROLLANDBOOLEAN)) {
					appLog.info("enter value in the " + workspace + " search text box: " + contactSearchKeyword);
				} else {
					appLog.error("not able to enter value in " + workspace
							+ " search text box so cannot search and send email to contact: " + contactEmailId);
					return false;
				}
			}
			WebElement ele = FindElement(driver,
					"//div[@id='manageemailgrid_ME']//a[text()='" + contactEmailId + "']/../..//input",
					workspace + " input text box ", action.SCROLLANDBOOLEAN, 30);
			if (ele != null) {
				WebElement ele1 = FindElement(driver,
						"//div[@id='manageemailgrid_ME']//a[text()='" + contactEmailId + "']/../..//span[3]/a",
						"contact name", action.SCROLLANDBOOLEAN, 30);
				String str = getText(driver, ele1, contactEmailId + " contact name", action.BOOLEAN);
				if (click(driver, ele, str + " name check box", action.BOOLEAN)) {
					appLog.info("clicked on contact name successfully : " + str);

				} else {
					appLog.error("contact name is not visible on manage email grid: " + contactEmailId);
				}
			} else {
				appLog.error("contact is not available in the manage emails grid: " + contactEmailId
						+ " so cannot send email to contact: " + contactEmailId);
				return false;
			}
			if (click(driver, getmanageEmailsendBtn(30), "manage email send button", action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on manage emails send button successfully.");
				if (click(driver, getManageEmailYesBtn(30), "manage email yes button", action.SCROLLANDBOOLEAN)) {
					appLog.error("click on Yes button");
					
					if (click(driver, getManageEmailCancelBtn(30), "manage email cancel button", action.SCROLLANDBOOLEAN)) {
						appLog.error("click on cancel button");
					}
				}else {
					appLog.error("Not able to click on Yes Button so cannot send email to contact: "+contactEmailId);
					return false;
				}
			} else {
				if (click(driver, getManageEmailCancelBtn(30), "manage email cancel button", action.SCROLLANDBOOLEAN)) {
					appLog.error("click on cancel button");
				}
				appLog.error("Not able to click on send button so cannot send invitation email to contact: "
						+ contactEmailId);
				return false;
			}
		} else {
			appLog.error("Not able to click on " + workspace + " manage emails icon so cannot send email to contact: "
					+ contactEmailId);
			return false;
		}
		return true;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param accountNameToWhichFilesToBeUploaded
	 * @param OnlineImportFileAddTo
	 * @param parentWinID
	 * @param checkMsg
	 * @return true/false
	 */
	public boolean selectInstitutionOnUploadWindow(String InstituteOrLPNameToWhichFilesToBeUploaded, OnlineImportFileAddTo OnlineImportFileAddTo,String parentWinID,
			String checkMsg, Workspace Workspace) {
		if(OnlineImportFileAddTo.toString().equalsIgnoreCase(OnlineImportFileAddTo.MultipleInstitute.toString())) {
			if(getImportOnlineaddMultipleRadioBtn(30)!=null) {
				if(click(driver,getImportOnlineaddMultipleRadioBtn(20), "add multiple instutions radio button", action.BOOLEAN)) {
					appLog.info("clicked on add multiple instutution radio button");
					if (InstituteOrLPNameToWhichFilesToBeUploaded != null) {
						String names[] = InstituteOrLPNameToWhichFilesToBeUploaded.split(",");
						for (int i = 0; i < names.length; i++) {
							WebElement ele = FindElement(driver, "//*[text()='"+names[i]+"']/..//input",
									names[i] + " :Account Check box", action.SCROLLANDBOOLEAN, 30);
							if (ele != null) {
								if(!isSelected(driver, ele, names[i] + " :Account Check box")) {
									if (!click(driver, ele, names[i] + " :Account Check box", action.SCROLLANDBOOLEAN)) {
										appLog.info("Cannot select account: " + names[i]);
										driver.close();
										driver.switchTo().window(parentWinID);
										return false;
									}
									
								}else {
									appLog.info(names[i] + " :Account Check box is already selected ");
								}
							} else {
								appLog.info(names[i] + " :Account is not present in the list.");
								continue;
							}
						}
					} else {
						appLog.info("Will Continue upload with the default selection.");
					}
				}else {
					appLog.error("Not able to click on add multiple instution radio button so cannot select institutions: "+InstituteOrLPNameToWhichFilesToBeUploaded);
					return false;
				}
			}
		}
		if (!click(driver, getUploadNextButton(Workspace,30), "Upload Next Button", action.BOOLEAN)) {
			ThreadSleep(3000);
			if (CommonLib.isAlertPresent(driver)) {
				String msg = CommonLib.switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
				switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
				if (msg.trim().equalsIgnoreCase(UploadSelectTargetMessage)) {
					// driver.switchTo().window(parentWinID);
					return true;
				} else {
					BaseLib.sa.assertTrue(false, "Error message on upload window is not matched.\tExpected: "
							+ UploadSelectTargetMessage + "\tActual: " + msg);
					// driver.switchTo().window(parentWinID);
					return false;
				}
			} else if (checkMsg != null) {
				BaseLib.sa.assertTrue(false, "Select Atleast On Target Alert Is Not Showing On Upload Window.");
				appLog.info("There Is No Alert.");
				return false;
			} else {
				return true;
			}
		} else {
			ThreadSleep(3000);
			if (CommonLib.isAlertPresent(driver)) {
				String msg = CommonLib.switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
				switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
				if (msg.trim().equalsIgnoreCase(UploadSelectTargetMessage)) {
					// driver.switchTo().window(parentWinID);
					return true;
				} else {
					BaseLib.sa.assertTrue(false, "Error message on upload window is not matched.\tExpected: "
							+ UploadSelectTargetMessage + "\tActual: " + msg);
					// driver.switchTo().window(parentWinID);
					return false;
				}
			} else if (checkMsg != null) {
				BaseLib.sa.assertTrue(false, "Select Atleast On Target Alert Is Not Showing On Upload Window.");
				appLog.info("There Is No Alert.");
				return false;
			} else {
				return true;
			}
		}
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param institution
	 * @param limitedPartner
	 * @param InstitutionNameForStdFolder
	 * @param folderPath
	 * @param documentPath
	 * @param fileName
	 * @param boxUserName
	 * @param boxPassword
	 * @param onlineImportFileAddTo
	 * @param updateUpload
	 * @param FolderType
	 * @param pageName
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean onlineImport(String institution, String limitedPartner, String InstitutionOrLPNameForStdFolder, String folderPath,
			String documentPath, String fileName, String boxUserName, String boxPassword,OnlineImportFileAddTo onlineImportFileAddTo,
			WorkSpaceAction WorkSpaceAction, FolderType FolderType, PageName pageName, Workspace workspace,int timeOut) {
		if (pageName.toString().equalsIgnoreCase(pageName.FundsPage.toString())) {
			switchToFrame(driver, 60, getFrame(pageName.FundsPage, 60));
		}
		String parentID = null;
		if (FolderType.toString().equalsIgnoreCase(FolderType.Standard.toString())) {
			if(!verifyFolderPathdummy(folderPath, institution , limitedPartner, null, pageName, workspace, timeOut)) {
				BaseLib.sa.assertTrue(false, folderPath + " :Folder Structure is not verified.");
				switchToDefaultContent(driver);
				return false;
			}
		} else {
			if(!verifyFolderPathdummy(folderPath, null, null, null, pageName, workspace, timeOut)) {
				BaseLib.sa.assertTrue(false, folderPath + " :Folder Structure is not verified.");
				switchToDefaultContent(driver);
				return false;
			}
		}
		InvestorFirmPageBusinesslayer ifp= new InvestorFirmPageBusinesslayer(driver);
			WebElement ele;
			if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
				scrollDownThroughWebelement(driver, getFundRaisingWorkSpaceSection(30), "fundraising workspace View");
				
			}else if (workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
				scrollDownThroughWebelement(driver, getInvestorWorkSpaceSection(30), "investor workspace View");
			}
			ele=getOnlineImportLink(workspace, timeOut);
		if (click(driver, ele, "Online Import Icon", action.BOOLEAN)) {
			ThreadSleep(5000);
			parentID = switchOnWindow(driver);
			ThreadSleep(3000);
			sendKeys(driver, getBoxUserName(60), ExcelUtils.readDataFromPropertyFile("BoxUsername"),
					"Box username Text Box", action.THROWEXCEPTION);
			sendKeys(driver, getBoxPasswordTextBox(60), ExcelUtils.readDataFromPropertyFile("BoxPassword"),
					"Box Password Text Box", action.THROWEXCEPTION);
			click(driver, getBoxAuthorizeButton(60), "Authorize button", action.THROWEXCEPTION);
			click(driver, getGrantAccessToBoxButton(60), "Grant Access To Box Button", action.THROWEXCEPTION);
			if (FolderType.toString().equalsIgnoreCase(FolderType.Standard.toString())) {
				if (selectInstitutionOnUploadWindow(InstitutionOrLPNameForStdFolder,onlineImportFileAddTo, parentID, null,workspace)) {
					appLog.info("Successfully selected account: " + InstitutionOrLPNameForStdFolder);
				} else {
					appLog.info("Cannot select account: " + InstitutionOrLPNameForStdFolder);
					BaseLib.sa.assertTrue(false, InstitutionOrLPNameForStdFolder + " :Account not selected.");
				}
			}
			if (CommonLib.traverseImport(driver, documentPath, fileName)) {
				ThreadSleep(3000);
				 ele= BaseLib.edriver.findElement(By.cssSelector("#lbtOnlinImportSave"));
				try{
					scrollDownThroughWebelement(driver, ele, "import button");
					ele.click();
					appLog.info("Clicked on Import Button successfully");
//				if (click(driver, getImportButton(60), "Online Import Button", action.BOOLEAN)) {
					if (WorkSpaceAction.toString().equalsIgnoreCase(WorkSpaceAction.UPDATE.toString())) {
						ThreadSleep(5000);
						 ele= BaseLib.edriver.findElement(By.cssSelector("#lnkReplaceAll"));
						 try{
							 scrollDownThroughWebelement(driver, ele, "Update All Button");
							 ele.click();
							 appLog.info("clicked on Update All Button");
						 }catch(Exception e){
							 appLog.error("Not able to click on Update all button so cannot update files ");
							 BaseLib.sa.assertTrue(false, "Not able to click on Update all button so cannot update files ");
							 appLog.error(e.getMessage().toString());
							 driver.close();
							 driver.switchTo().window(parentID);
							 return false;
						 }
					}
					if (isAlertPresent(driver)) {
						String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
						switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
						if(msg.trim().toLowerCase().equalsIgnoreCase(OnlineImportSuccessMsg.toLowerCase())) {
							appLog.info("Online import success alert message is matched successfully.");
						
						}else {
							appLog.error("Online Import Success Message is not matched. Expected: "+OnlineImportSuccessMsg);
							BaseLib.sa.assertEquals(msg, OnlineImportSuccessMsg,
									"Online Import Success Message is not matched.");							
						}
					} else {
						driver.close();
						driver.switchTo().window(parentID);
						BaseLib.sa.assertFalse(false, "Online Import success alert message didn't displayed");
					}
					driver.switchTo().window(parentID);
					return true;

//				} else {
//					appLog.error("Import Button is not visible so cannot upload document: "+fileName);
//					BaseLib.sa.assertTrue(false, "Import Button is not visible.");
//					driver.close();
//					driver.switchTo().window(parentID);
//					return false;
//				}
				
				}catch(Exception e){
					appLog.error(e.getMessage().toString());
					appLog.error("Import Button is not visible so cannot upload document: "+fileName);
					BaseLib.sa.assertTrue(false, "Import Button is not visible.");
					driver.close();
					driver.switchTo().window(parentID);
					return false;
					
				}
			} else {
				BaseLib.sa.assertTrue(false, "Path passed or file name passed is not correct. "
						+ folderPath + " or " + fileName);
				driver.close();
				driver.switchTo().window(parentID);
				return false;
			}
		} else {
			exit("Online import link is not working so cannot continue with this test case.");
			return false;
		}
	}

	/**
	 * 
	 * @param step1Of3Data Pass data with respect to the text field boxes on the UI.
	 * @param WithOrWithOutFolderTemplate
	 * @param folderTemplateName if want to import folder structure otherwise null.
	 * @param folderStructureSheetName Sheet in excel which contains the folder structure in case you have to create structure at build time, otherwise null.
	 * @param institutionOrLPName  'InstitutionName' if passing single institution else if pasing multiple institutions 'InstitutionName1<break>InstitutionName2'
	 * 							   'InstituionName/LPName' if passing single LP else if passing multiple LP 'InstitutionName/LPName<break>InstitutionName1/LPName1' 
	 * @param workspace workspace which has to be created.
	 * @param timeOut
	 * @return boolean
	 */
	public boolean buildWorkspace(String[] step1Of3Data, WorkSpaceAction WithOrWithOutFolderTemplate, String folderTemplateName, String folderStructureSheetName, String institutionOrLPName, Workspace workspace, int timeOut){
		boolean flag = true;
		switchToFrame(driver, 30, getFrame(PageName.FundsPage, timeOut));
		System.err.println("Switched to frame.");
		scrollDownThroughWebelement(driver, getWorkspaceSectionView(workspace, timeOut), workspace.toString()+" View.");
		if(click(driver, getBuildWorkspaceButton(workspace, timeOut), workspace.toString()+" Workspace Button", action.BOOLEAN)){
			if(!sendKeys(driver, getSizeInMillionTextBox(workspace, timeOut), step1Of3Data[0], "Size in Million text box", action.BOOLEAN)){
				BaseLib.sa.assertTrue(false,"Not able to pass data to size in million text box.");
			}
			if(!sendKeys(driver, getVintageYear(workspace, timeOut), step1Of3Data[1], "vintage Year", action.BOOLEAN)){
				BaseLib.sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
			}
			String value = getAttribute(driver, getContactTextBox(workspace, timeOut), "Contact Text Box", "value");
			if(value!=null && !value.isEmpty()){
				appLog.info("value is already present in contact text box.");
			} else {
				if(!sendKeys(driver, getContactTextBox(workspace, timeOut), step1Of3Data[2], "Contact text Box", action.BOOLEAN)){
					flag=false;
				}
			}
			value = getAttribute(driver, getPhoneTextBox(workspace, timeOut), "Phone Text Box", "value");
			if(value!=null && !value.isEmpty()){
				appLog.info("value is already present in contact text box.");
			} else {
				if(!sendKeys(driver, getPhoneTextBox(workspace, timeOut), step1Of3Data[3], "Phone text Box", action.BOOLEAN)){
					flag=false;
				}
			}
			value = getAttribute(driver, getEmailTextBox(workspace, timeOut), "Email Text Box", "value");
			if(value!=null && !value.isEmpty()){
				appLog.info("value is already present in contact text box.");
			} else {
				if(!sendKeys(driver, getEmailTextBox(workspace, timeOut), step1Of3Data[4], "Email text Box", action.BOOLEAN)){
					flag=false;
				}
			}
			value = getAttribute(driver, getDescriptionTextBox(workspace, timeOut), "Description Text Box", "value");
			if(value!=null && !value.isEmpty()){
				appLog.info("value is already present in contact text box.");
			} else {
				if(!sendKeys(driver, getDescriptionTextBox(workspace, timeOut), step1Of3Data[5], "Description text Box", action.BOOLEAN)){
				}
			}
			if(flag){
				if(click(driver, getNext1Of3Button(workspace, timeOut), "Next Button", action.BOOLEAN)){
					if(!importFolderTemplate(folderStructureSheetName, folderTemplateName, WithOrWithOutFolderTemplate, workspace, timeOut)){
						flag=false;
						appLog.error("Folder sructure is not created properly");
						BaseLib.sa.assertTrue(false,"Folder sructure is not created properly");
					} else {
						appLog.info("Folder Structure is created successfully.");
					}
					if(click(driver, getNext2Of3Button(workspace, timeOut), "Next button 2Of3", action.BOOLEAN)){
						ThreadSleep(10000);
						if(selectInstitutionOrLP(institutionOrLPName, workspace, timeOut)){
							if(click(driver, getInvestorAddedConfirmationCloseButton(workspace, timeOut), "Investor Addition confirmation Close Button", action.BOOLEAN));
						}
						if(click(driver, getDone3Of3Button(workspace, timeOut), "Done button", action.BOOLEAN)){
							appLog.info("Workspace has been created.");
							if(click(driver, getCongratulationsCloseButton(workspace, timeOut), "Congratualtions pop close button", action.BOOLEAN)){
								appLog.info("Workspace Created Successfully.");
							} else {
								refresh(driver);
							}
						} else {
							appLog.error("Workspace is not created.");
							BaseLib.sa.assertTrue(false,"Workspace is not created.");
							flag=false;
						}
					} else {
						appLog.error("Next button 2Of3 cannot be clicked, So cannot build the workspace");
						BaseLib.sa.assertTrue(false,"Next button 2Of3 cannot be clicked, So cannot build the workspace");
						flag=false;
					}
				} else {
					appLog.error("Next button cannot be clicked, so cannot build the workspace.");
					BaseLib.sa.assertTrue(false,"Next button cannot be clicked, so cannot build the workspace.");
					flag=false;
				}
			} else {
				appLog.error("Some mantadory fields are not filled so cannot continue with the tc.");
				BaseLib.sa.assertTrue(false,"Some mantadory fields are not filled so cannot continue with the tc.");
			}
		} else {
			appLog.error("Build button is not present on, So cannot create workspace.");
			BaseLib.sa.assertTrue(false,"Build button is not present on, So cannot create workspace.");
			flag=false;
		}
		switchToDefaultContent(driver);
		return flag;
	}
	
	/**
	 * @param sheetName
	 * @param folderTemplateName
	 * @param WithOrWithOutFolderTemplate
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean importFolderTemplate(String sheetName, String folderTemplateName, WorkSpaceAction WithOrWithOutFolderTemplate, Workspace workspace, int timeOut){
		boolean flag = false;
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		if(WithOrWithOutFolderTemplate.toString().equalsIgnoreCase(WorkSpaceAction.WITHOUTEMPLATE.toString())){
			appLog.info("Building workspace without folder");
			flag=true;
		} else if (WithOrWithOutFolderTemplate.toString().equalsIgnoreCase(WorkSpaceAction.IMPORTFOLDERTEMPLATE.toString())){
			if(click(driver, getImportFolderTemplateButton(workspace, timeOut), "folder template Imprt Button", action.BOOLEAN)){
				if(selectVisibleTextFromDropDown(driver, getDisplayDropDown(workspace, timeOut), "Display Drop Down", "All Templates")){
					if(click(driver, getFolderTemplateRadioButton(folderTemplateName, workspace, timeOut), folderTemplateName+" Folder template radio Button", action.BOOLEAN)){
						if(click(driver, getFolderTemplateImportButton(workspace, timeOut), "Import Button", action.BOOLEAN)){
							appLog.info("Successfully imported folder template: "+folderTemplateName);
							flag=true;
						} else {
							appLog.error("Import button cannot be clicked, So cannot create "+workspace.toString()+" workspace.");
							BaseLib.sa.assertTrue(false,"Import button cannot be clicked, So cannot create "+workspace.toString()+" workspace.");
						}
					} else {
						appLog.error(folderTemplateName+" Folder template is not present in the list.");
						BaseLib.sa.assertTrue(false,folderTemplateName+" Folder template is not present in the list.");
					}
				} else {
					appLog.error("Not able to select Option from the drop down.");
					BaseLib.sa.assertTrue(false,"Not able to select Option from the drop down.");
				}
			} else {
				appLog.error("Import folder template button cannot be clicked, So cannot create workspace.");
				BaseLib.sa.assertTrue(false,"Import folder template button cannot be clicked, So cannot create workspace.");
			}
		} else if (WithOrWithOutFolderTemplate.toString().equalsIgnoreCase(WorkSpaceAction.CREATEFOLDERTEMPLATE.toString())){
			Map<String, String> s = folderStructureInExcel(sheetName);
			Set<String> paths = s.keySet();
			Iterator<String> i = paths.iterator();
			i = paths.iterator();
			FolderType folderType = null;
			while (i.hasNext()) {
				String string = i.next();
				if (string.isEmpty())
					continue;
				System.out.println("\n\n\nCreating folder template\n\n\n");
				if(s.get(string).equalsIgnoreCase("Shared")){
					folderType=FolderType.Shared;
				} else if (s.get(string).equalsIgnoreCase("Common")){
					folderType=FolderType.Common;
				} else if (s.get(string).equalsIgnoreCase("Internal")){
					folderType=FolderType.Internal;
				} else if (s.get(string).equalsIgnoreCase("Standard")){
					folderType=FolderType.Standard;
				}
				List<String> notCreatedFolders = nim.createFolderStructure(string, folderType, workspace, PageName.FundsPage, timeOut);
				if(notCreatedFolders.isEmpty()){
					flag=true;
				} else {
					String folderNames = createStringOutOfList(notCreatedFolders);
					BaseLib.sa.assertTrue(false,"Following folders are not created: "+folderNames);
				}
			}
//			if(verifyFolderStructure(folderStructureInExcel("FolderTemp"), 5)){
//				appLog.info("Folder strucuture is verified on build step 2 of 3.");
//			} else {
//				appLog.error("Folder structure is not verified on build step 2 of 3.");
//			}
		}
		return flag;
	}
	
	
	public boolean importFolderTemplate(String filePath,String sheetName, String folderTemplateName, WorkSpaceAction WithOrWithOutFolderTemplate, Workspace workspace, int timeOut){
		boolean flag = false;
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		if(WithOrWithOutFolderTemplate.toString().equalsIgnoreCase(WorkSpaceAction.WITHOUTEMPLATE.toString())){
			appLog.info("Building workspace without folder");
			flag=true;
		} else if (WithOrWithOutFolderTemplate.toString().equalsIgnoreCase(WorkSpaceAction.IMPORTFOLDERTEMPLATE.toString())){
			if(click(driver, getImportFolderTemplateButton(workspace, timeOut), "folder template Imprt Button", action.BOOLEAN)){
				if(selectVisibleTextFromDropDown(driver, getDisplayDropDown(workspace, timeOut), "Display Drop Down", "All Templates")){
					if(click(driver, getFolderTemplateRadioButton(folderTemplateName, workspace, timeOut), folderTemplateName+" Folder template radio Button", action.BOOLEAN)){
						if(click(driver, getFolderTemplateImportButton(workspace, timeOut), "Import Button", action.BOOLEAN)){
							appLog.info("Successfully imported folder template: "+folderTemplateName);
							flag=true;
						} else {
							appLog.error("Import button cannot be clicked, So cannot create "+workspace.toString()+" workspace.");
							BaseLib.sa.assertTrue(false,"Import button cannot be clicked, So cannot create "+workspace.toString()+" workspace.");
						}
					} else {
						appLog.error(folderTemplateName+" Folder template is not present in the list.");
						BaseLib.sa.assertTrue(false,folderTemplateName+" Folder template is not present in the list.");
					}
				} else {
					appLog.error("Not able to select Option from the drop down.");
					BaseLib.sa.assertTrue(false,"Not able to select Option from the drop down.");
				}
			} else {
				appLog.error("Import folder template button cannot be clicked, So cannot create workspace.");
				BaseLib.sa.assertTrue(false,"Import folder template button cannot be clicked, So cannot create workspace.");
			}
		} else if (WithOrWithOutFolderTemplate.toString().equalsIgnoreCase(WorkSpaceAction.CREATEFOLDERTEMPLATE.toString())){
			Map<String, String> s = folderStructureInExcel(filePath, sheetName);
			Set<String> paths = s.keySet();
			Iterator<String> i = paths.iterator();
			i = paths.iterator();
			FolderType folderType = null;
			while (i.hasNext()) {
				String string = i.next();
				if (string.isEmpty())
					continue;
				System.out.println("\n\n\nCreating folder template\n\n\n");
				if(s.get(string).equalsIgnoreCase("Shared")){
					folderType=FolderType.Shared;
				} else if (s.get(string).equalsIgnoreCase("Common")){
					folderType=FolderType.Common;
				} else if (s.get(string).equalsIgnoreCase("Internal")){
					folderType=FolderType.Internal;
				} else if (s.get(string).equalsIgnoreCase("Standard")){
					folderType=FolderType.Standard;
				}
				List<String> notCreatedFolders = nim.createFolderStructure(string, folderType, workspace, PageName.FundsPage, timeOut);
				if(notCreatedFolders.isEmpty()){
					flag=true;
				} else {
					String folderNames = createStringOutOfList(notCreatedFolders);
					BaseLib.sa.assertTrue(false,"Following folders are not created: "+folderNames);
				}
			}
//			if(verifyFolderStructure(folderStructureInExcel("FolderTemp"), 5)){
//				appLog.info("Folder strucuture is verified on build step 2 of 3.");
//			} else {
//				appLog.error("Folder structure is not verified on build step 2 of 3.");
//			}
		}
		return flag;
	}
	
	

	
	/**
	 * @author Ankur Rana
	 * @param institutionOrLPName
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean selectInstitutionOrLP(String institutionOrLPName, Workspace workspace, int timeOut){
		boolean flag = true;
		
		if(institutionOrLPName!=null){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				String[] multipleInstitutions = institutionOrLPName.split("<break>");
				for(int i = 0; i < multipleInstitutions.length; i++){
					if(click(driver, getInstituionCheckBoxOn3Of3(multipleInstitutions[i], workspace, timeOut), multipleInstitutions[i]+" Institution Check Box.", action.BOOLEAN)){
						appLog.info("Successfully Selected Institution '"+multipleInstitutions[i]+"'");
					} else {
						appLog.error("Not able to add '"+multipleInstitutions[i]+"' to the workspace.");
						BaseLib.sa.assertTrue(false,"Not able to add '"+multipleInstitutions[i]+"' to the workspace.");
						flag=false;
					}
				}
			} else {
				String[] multipleLP = institutionOrLPName.split("<break>");
				for(int i = 0; i < multipleLP.length; i++){
					if(click(driver, getLimitedPartnerCheckBox(multipleLP[i].split("/")[0], multipleLP[i].split("/")[1], workspace, timeOut), multipleLP[i]+" LP Check Box.", action.BOOLEAN)){
						appLog.info("Successfully Selected LP '"+multipleLP[i]+"'");
					} else {
						appLog.error("Not able to add '"+multipleLP[i]+"' to the workspace.");
						BaseLib.sa.assertTrue(false,"Not able to add '"+multipleLP[i]+"' to the workspace.");
						flag=false;
					}
				}
			}
		} else {
			appLog.info("Creating workspace without adding instituion or LP.");
		}
		return flag;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param contentGridArrowKeyFunctions
	 * @param documentName
	 * @param timeOut
	 * @param scroll
	 * @return true/false
	 */
	public boolean clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions contentGridArrowKeyFunctions,
			String documentName, Workspace workspace,int timeOut,String scroll) {
		WebElement ele=null;
		ele = FindElement(driver, "//a[@title='" + documentName + "']/following-sibling::img", "Down Arrow",
					action.BOOLEAN, timeOut);
		if (scroll != null && scroll.equalsIgnoreCase("Yes"))
			scrollDownThroughWebelement(driver, ele, "");
		if (ele != null) {
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].setAttribute('style', 'position: absolute; top: 5px; right: 1px; float: left; margin-top: 10px; cursor: pointer;');",
					ele);
			ThreadSleep(3000);
			if (click(driver, ele, "Down Arrow", action.BOOLEAN)) {
				if (contentGridArrowKeyFunctions.toString()
						.equalsIgnoreCase(ContentGridArrowKeyFunctions.Update.toString())) {
					if (click(driver, getUpdateLinkContentGrid(timeOut), "Update Link", action.BOOLEAN)) {
						return true;
					}
				} else if (contentGridArrowKeyFunctions.toString()
						.equalsIgnoreCase(ContentGridArrowKeyFunctions.ManageVersions.toString())) {
					if (click(driver, getManageVersionLinkContentGrid(workspace,timeOut), "Manage Version Link",
							action.BOOLEAN)) {
						return true;
					}
				} else if (contentGridArrowKeyFunctions.toString()
						.equalsIgnoreCase(ContentGridArrowKeyFunctions.Delete.toString())) {
						if(click(driver, getDeleteFileLinkContentGrid(workspace,timeOut), "Delete Link", action.BOOLEAN)) {
							return true;
						}
				} else {
					if (click(driver, getOpenLinkContentGrid(timeOut), "Open Link", action.BOOLEAN)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param folderPath
	 * @param documentName
	 * @param institutionName
	 * @param limitedPartnername
	 * @param FolderType
	 * @param filePath
	 * @param multiInstance
	 * @param verifyErrorMessage
	 * @param updateVia
	 * @param timeOut
	 * @param pageName
	 * @param scroll
	 * @param fundName
	 * @param Workspace
	 * @return true/false
	 */
	public boolean updateFile(String folderPath, String documentName, String institutionName,String limitedPartnername, FolderType FolderType,
			String filePath, multiInstance multiInstance, String verifyErrorMessage, ContentGridArrowKeyFunctions ContentGridArrowKeyFunctions,
			int timeOut, PageName pageName, String scroll,String fundName,Workspace Workspace) {
			switchToFrame(driver, 60, getFrame(pageName, timeOut));			
		if (FolderType.toString().equalsIgnoreCase(FolderType.Standard.toString())) {
			if (!verifyFolderPathdummy(folderPath, institutionName , limitedPartnername, fundName, pageName, Workspace, timeOut)) {
				BaseLib.sa.assertTrue(false, folderPath + " :Folder Structure is not verified.");
				switchToDefaultContent(driver);
				return false;
			}
		} else {
			if (!verifyFolderPathdummy(folderPath, null , null, fundName, pageName, Workspace, timeOut)) {
				BaseLib.sa.assertTrue(false, folderPath + " :Folder Structure is not verified.");
				switchToDefaultContent(driver);
				return false;
			}
		}
		if(Workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			scrollDownThroughWebelement(driver, getFundRaisingWorkSpaceSection(timeOut), "fundraising workspace View");
			
		}else if (Workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
			scrollDownThroughWebelement(driver, getInvestorWorkSpaceSection(timeOut), "investor workspace View");
		}		
		if (clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions, documentName,Workspace, timeOut, scroll)) {
			if (ContentGridArrowKeyFunctions.toString().equalsIgnoreCase(ContentGridArrowKeyFunctions.ManageVersions.toString())) {
				if (!click(driver, getUpdateButtonOnManageVersionPopUp(60), "Update Button", action.BOOLEAN)) {
					appLog.info("Update Button on manage version pop up is not clikable.");
					BaseLib.sa.assertTrue(false, "Update Button on manage version pop up is not available.");
					return false;
				}
			}
			String parentWinID = switchOnWindow(driver);
			if (parentWinID != null) {
				appLog.info("Update window is successfully opened.");
				if (multiInstance != null) {
					if (multiInstance.toString().equalsIgnoreCase(multiInstance.AllInvestor.toString())) {
						click(driver, getAllInvestorButton(60), "All investor Button", action.THROWEXCEPTION);
					} else {
						click(driver, getThisInvestorOnlyButton(60), "This investor Only Button", action.THROWEXCEPTION);
					}
				}
				if (verifyErrorMessage != null) {
					if (verifyErrorMessage.equalsIgnoreCase("Yes")) {
						if (click(driver, getUpdateLinkContentGrid(60), "Update button", action.BOOLEAN)) {
							if (getSelectDocumentErrorMsg(60) != null) {
								String msg = getSelectDocumentErrorMsg(60).getText().trim();
								if (msg.equalsIgnoreCase(updateDocumentErrorMsg)) {
									appLog.info("Please select a document to update message is verified.");
								} else {
									BaseLib.sa.assertTrue(false,
											"Please select a document to update message is not verified.");
								}
							} else {
								BaseLib.sa.assertTrue(false,
										"Please select a document to update message present on this page");
							}
						}
					}
				}
				if (sendKeys(driver, getChooseFileButton(60), filePath, "Choose Button", action.BOOLEAN)) {
					
					try{
						WebElement ele= BaseLib.edriver.findElement(By.cssSelector("#LinkButton1"));
						scrollDownThroughWebelement(driver, ele, "update button");
						ele.click();
						appLog.info("Clicked on update Button");
//						if (click(driver, getUpdateLinkContentGrid(60), "Update button", action.BOOLEAN)) {
							String msg=null;
							if (isAlertPresent(driver)) {
								 msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								if (msg.equalsIgnoreCase(updateDocumentSuccessAlertMsg)) {
									appLog.info("Update confirmation message is verified.");
								} else {
									appLog.error("Confirmation message is not verified. Expected: "+updateDocumentSuccessAlertMsg+" /tActual: "+msg);
									BaseLib.sa.assertTrue(false, "Confirmation message is not verified. Expected: "+updateDocumentSuccessAlertMsg+" /t Actual: "+msg);
								}
							} else {
								appLog.error("Confirmation alert message is not present on this window. Expected: "+updateDocumentSuccessAlertMsg+" /tActual: "+msg);
								BaseLib.sa.assertTrue(false, "Confirmation message is not present on this window. Expected: "+updateDocumentSuccessAlertMsg+" /tActual: "+msg);
								driver.close();
								driver.switchTo().window(parentWinID);
							}
							driver.switchTo().window(parentWinID);
							if (ContentGridArrowKeyFunctions.toString()
									.equalsIgnoreCase(ContentGridArrowKeyFunctions.ManageVersions.toString())) {
								switchToFrame(driver, 60, getFrame(pageName, timeOut));
								if (!click(driver, getManageVersionsPopUpCrossIcon(30), "Manage Versions Cross Icon",
										action.BOOLEAN)) {
									if (!click(driver, getManageVersionPopUpCloseButton(30),
											"Manage Versions Pop Up Cancel Button", action.BOOLEAN)) {
										driver.navigate().refresh();
									}
								}
									switchToDefaultContent(driver);
							}
							return true;
//						} else {
//							appLog.error("Update button is not present on pop up window.");
//							BaseLib.sa.assertTrue(false, "Update button is not present on pop up window.");
//							driver.close();
//						}
//					
					}catch(Exception e) {
						appLog.error("Update button is not present on pop up window.");
						BaseLib.sa.assertTrue(false, "Update button is not present on pop up window.");
						driver.close();
					}
					
				} else {
					appLog.error("Choose Button is not present or given file path: "+filePath+" is not correct so cannot update file");
					BaseLib.sa.assertTrue(false, "Choose Button is not present or given file path: "+filePath+" is not correct so cannot update file");
					driver.close();
				}
			} else {
				appLog.error("Update pop is not present.");
				BaseLib.sa.assertTrue(false, "Update pop is not present.");
				driver.close();
			}
		} else {
			appLog.info(documentName + " :Document is not present in the folder " + folderPath);
			BaseLib.sa.assertTrue(false, documentName + " :Document is not present in the folder " + folderPath);
			driver.close();
		}
		switchToDefaultContent(driver);
		return false;
	}
	
	

	/**
	 * @author Ankit Jaiswal
	 * @param loggedInUsername
	 * @param folderPath
	 * @param documentName
	 * @param institutionName
	 * @param limitedPartnername
	 * @param FolderType
	 * @param filePath
	 * @param multiInstance
	 * @param verifyErrorMessage
	 * @param ContentGridArrowKeyFunctions
	 * @param timeOut
	 * @param pageName
	 * @param scroll
	 * @param fundName
	 * @param Workspace
	 * @return
	 */
	public boolean updateBulkFile(String loggedInUsername,String folderPath, String documentName, String institutionName,String limitedPartnername, FolderType FolderType,
			String filePath, multiInstance multiInstance, String verifyErrorMessage, ContentGridArrowKeyFunctions ContentGridArrowKeyFunctions,
			int timeOut, PageName pageName, String scroll,String fundName,Workspace Workspace) {
			switchToFrame(driver, 60, getFrame(pageName, timeOut));			
		if (FolderType.toString().equalsIgnoreCase(FolderType.Standard.toString())) {
			if (!verifyFolderPathdummy(folderPath, institutionName , limitedPartnername, fundName, pageName, Workspace, timeOut)) {
				BaseLib.sa.assertTrue(false, folderPath + " :Folder Structure is not verified.");
				switchToDefaultContent(driver);
				return false;
			}
		} else {
			if (!verifyFolderPathdummy(folderPath, null , null, fundName, pageName, Workspace, timeOut)) {
				BaseLib.sa.assertTrue(false, folderPath + " :Folder Structure is not verified.");
				switchToDefaultContent(driver);
				return false;
			}
		}
		if(Workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			scrollDownThroughWebelement(driver, getFundRaisingWorkSpaceSection(timeOut), "fundraising workspace View");
			
		}else if (Workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
			scrollDownThroughWebelement(driver, getInvestorWorkSpaceSection(timeOut), "investor workspace View");
		}		
		if (clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions, documentName,Workspace, timeOut, scroll)) {
			if (ContentGridArrowKeyFunctions.toString().equalsIgnoreCase(ContentGridArrowKeyFunctions.ManageVersions.toString())) {
				if (!click(driver, getUpdateButtonOnManageVersionPopUp(60), "Update Button", action.BOOLEAN)) {
					appLog.info("Update Button on manage version pop up is not clikable.");
					BaseLib.sa.assertTrue(false, "Update Button on manage version pop up is not available.");
					return false;
				}
			}
			String parentWinID = switchOnWindow(driver);
			if (parentWinID != null) {
				appLog.info("Update window is successfully opened.");
				if (multiInstance != null) {
					if (multiInstance.toString().equalsIgnoreCase(multiInstance.AllInvestor.toString())) {
						click(driver, getAllInvestorButton(60), "All investor Button", action.THROWEXCEPTION);
					} else {
						click(driver, getThisInvestorOnlyButton(60), "This investor Only Button", action.THROWEXCEPTION);
					}
				}
				if (verifyErrorMessage != null) {
					if (verifyErrorMessage.equalsIgnoreCase("Yes")) {
						if (click(driver, getUpdateLinkContentGrid(60), "Update button", action.BOOLEAN)) {
							if (getSelectDocumentErrorMsg(60) != null) {
								String msg = getSelectDocumentErrorMsg(60).getText().trim();
								if (msg.equalsIgnoreCase(updateDocumentErrorMsg)) {
									appLog.info("Please select a document to update message is verified.");
								} else {
									BaseLib.sa.assertTrue(false,
											"Please select a document to update message is not verified.");
								}
							} else {
								BaseLib.sa.assertTrue(false,
										"Please select a document to update message present on this page");
							}
						}
					}
				}
				if (sendKeys(driver, getChooseFileButton(60), filePath, "Choose Button", action.BOOLEAN)) {
					if (click(driver, getUpdateLinkContentGrid(60), "Update button", action.BOOLEAN)) {
						String msg=null;
						if (isAlertPresent(driver)) {
							 msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
							switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
							if (msg.equalsIgnoreCase(updateDocumentSuccessAlertMsg)) {
								appLog.info("Update confirmation message is verified.");
							} else {
								appLog.error("Confirmation message is not verified. Expected: "+updateDocumentSuccessAlertMsg+" /tActual: "+msg);
								BaseLib.sa.assertTrue(false, "Confirmation message is not verified. Expected: "+updateDocumentSuccessAlertMsg+" /t Actual: "+msg);
							}
						}else if(getUploadBulkDelayedMessage(timeOut)!=null) {
							appLog.info("document is not uploaded.. we will check from email");
							 msg = getUploadBulkDelayedMessage(180).getText();
							if(msg.contains("email") && msg.contains("Document") && msg.contains("update")){
								for(int j = 0 ; j <= 100 ; j++)
									if(EmailLib.mailReceived(gmailUserName, gmailPassword, "noreply@navatarinvestor.com", loggedInUsername, "File update status","file update process has been completed" )){
										System.out.println("\n\n\nFound\n\n\n");
										break;
									} else {
										System.out.println("Mail is not found, Will Check for mail again.");
										if(j>=100){
											appLog.error("Mail is not received.");
											BaseLib.sa.assertTrue(false,"Mail is not received ");
											return false;
										}
										ThreadSleep(5000);
									}
							}else {
								appLog.error("msg warming on web page is not related to reading email regarding upload");
								sa.assertTrue(false, "msg warming on web page is not related to reading email regarding upload");
									return false;
							}
							if(!driver.getWindowHandle().equalsIgnoreCase(parentWinID)){
								driver.close();
							}
						}else {
							appLog.error("Confirmation alert message is not present on this window. Expected: "+updateDocumentSuccessAlertMsg+" /tActual: "+msg);
							BaseLib.sa.assertTrue(false, "Confirmation message is not present on this window. Expected: "+updateDocumentSuccessAlertMsg+" /tActual: "+msg);
							driver.close();
							driver.switchTo().window(parentWinID);
						}
						driver.switchTo().window(parentWinID);
						if (ContentGridArrowKeyFunctions.toString()
								.equalsIgnoreCase(ContentGridArrowKeyFunctions.ManageVersions.toString())) {
							switchToFrame(driver, 60, getFrame(pageName, timeOut));
							if (!click(driver, getManageVersionsPopUpCrossIcon(30), "Manage Versions Cross Icon",
									action.BOOLEAN)) {
								if (!click(driver, getManageVersionPopUpCloseButton(30),
										"Manage Versions Pop Up Cancel Button", action.BOOLEAN)) {
									driver.navigate().refresh();
								}
							}
								switchToDefaultContent(driver);
						}
						return true;
					} else {
						appLog.error("Update button is not present on pop up window.");
						BaseLib.sa.assertTrue(false, "Update button is not present on pop up window.");
						driver.close();
					}
				} else {
					appLog.error("Choose Button is not present or given file path: "+filePath+" is not correct so cannot update file");
					BaseLib.sa.assertTrue(false, "Choose Button is not present or given file path: "+filePath+" is not correct so cannot update file");
					driver.close();
				}
			} else {
				appLog.error("Update pop is not present.");
				BaseLib.sa.assertTrue(false, "Update pop is not present.");
				driver.close();
			}
		} else {
			appLog.info(documentName + " :Document is not present in the folder " + folderPath);
			BaseLib.sa.assertTrue(false, documentName + " :Document is not present in the folder " + folderPath);
			driver.close();
		}
		switchToDefaultContent(driver);
		return false;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param documentName
	 * @param timeOut
	 * @return True/False
	 */
	public boolean clickOnManageVersionOnContentGrid(String documentName, Workspace workspace, int timeOut) {
		WebElement ele = FindElement(driver, "//a[@title='" + documentName + "']/following-sibling::img", "Down Arrow",
				action.BOOLEAN, timeOut);
		if (ele != null) {
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].setAttribute('style', 'position: absolute; top: 5px; right: 1px; float: left; margin-top: 10px; cursor: pointer;');",
					ele);
			if (click(driver, ele, "Down Arrow", action.THROWEXCEPTION)) {
				if (click(driver, getManageVersionLinkContentGrid(workspace,timeOut), workspace.toString()+" Manage Version Link", action.BOOLEAN)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param uploadedDocumentName
	 * @param makeCurrentDocumentName
	 * @param Workspace
	 * @param timeOut
	 * @return True/False
	 */
	public boolean makeCurrentversionDocViaManageVersion(String instituteName,String LPName,String folderPath,String uploadedDocumentName,String makeCurrentDocumentName,Workspace Workspace,int timeOut) {
		switchToFrame(driver, timeOut, getFrame(PageName.FundsPage, 30));
		if(verifyFolderPathdummy(folderPath, instituteName, LPName, null, PageName.FundsPage, Workspace, timeOut)) {
			appLog.info("folder path is verified successfully: "+folderPath);
			if(clickOnManageVersionOnContentGrid(uploadedDocumentName, Workspace, timeOut)) {
				appLog.info("clicked on document: "+uploadedDocumentName+" manage version link succesfully");
				List<WebElement> ele=FindElements(driver, "//span[@title='"+makeCurrentDocumentName+"']/../../span[5]", makeCurrentDocumentName+" current status list");
				List<WebElement> ele1=FindElements(driver, "//span[@title='"+makeCurrentDocumentName+"']/../../span[2]/a[@title='Make Current']", makeCurrentDocumentName+" make current link list");
				if(!ele.isEmpty() || !ele1.isEmpty()) {
					for(int i=0; i<ele.size(); i++) {
						String status=ele.get(i).getText().trim();
						if(status.equalsIgnoreCase("No")) {
							if(click(driver, ele1.get(i), makeCurrentDocumentName+" make current link", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on document make current version: "+makeCurrentDocumentName);
								break;
							}else {
								appLog.error("Not able to click on document make current: "+makeCurrentDocumentName);
								return false;
							}
						}
						if(i==ele.size()-1) {
							appLog.error(makeCurrentDocumentName+" current status is already Yes so  document is already selected as current version");
							return false;
						}
					}
				}else {
					appLog.error(makeCurrentDocumentName+" status is not visible so cannot make it current version");
					return false;
				}
				if (!click(driver, getManageVersionPopUpCloseButton(30),"Manage Versions Pop Up close Button", action.BOOLEAN)) {
					appLog.error("Not able to click on Manage version Close button");
					return false;
				}
			}else {
				appLog.error("Not able to click on manage version of document: "+uploadedDocumentName);
				return false;
			}
			
		}else {
			appLog.error("folder path is not available in the "+Workspace.toString()+" so cannot make current "+makeCurrentDocumentName+" document");
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @param path Folder path in which file has to be uploaded
	 * @param institutionOrLPName if Fundraisng Workspace pass as InstitutionName if multiple Institution pass as Institution1<break>Instituion2
	 * 							   if Investor Workspace Passs as InstitutionName/LPName if multiple LP Pass as InstitutionName1/LPName1<break>InstitutionName2/LPName2
	 * @param dragFromFolder Name of the folder from which file is present, if inside folder of project location then pass as filesToUpload/FolderName
	 * @param uploadFileAddTo Only for bulk upload and file splitter
	 * @param uploadUpdate Want to upload file or update
	 * @param workspace workspace Name
	 * @param pageName Page From which file has to be uploaded
	 * @param timeOut
	 * @return boolean and write the name of the files uploaded and updated in there respective folder.
	 */
	public boolean uploadFile(String path, String institutionOrLPName, String dragFromFolder, UploadFileActions uploadFileAddTo, UploadFileActions uploadUpdate, Workspace workspace, PageName pageName, int timeOut){
		String institutionName=null;
		String limitedPartner=null;
		boolean flag = false;
		int counter = 0;
		String dropImage = "DropLoc.JPG";
		CommonLib compare = new CommonLib();
		switchToFrame(driver, timeOut, getFrame(pageName, timeOut));
		scrollDownThroughWebelement(driver, getWorkspaceSectionView(workspace, timeOut), workspace.toString()+" view.");
		if(institutionOrLPName!=null){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				institutionName = institutionOrLPName.split("<break>")[0];
			} else {
				institutionName = institutionOrLPName.split("<break>")[0].split("/")[0];
				limitedPartner = institutionOrLPName.split("<break>")[0].split("/")[1];
			}
		}
		if(verifyFolderPathdummy(path, institutionName, limitedPartner, null, pageName, workspace, timeOut)){
			for(int z = 0; z == 0; z++)
			if(click(driver, getUploadIcon(workspace, timeOut), "Upload Icon", action.BOOLEAN)){
				String parentWin = switchOnWindow(driver);
				if(parentWin!=null && !parentWin.isEmpty()){
					if(path.contains("(Common)") || path.contains("(Shared)") || path.contains("(Internal)")){
						appLog.info("Will directly upload the files.");
						flag = true;
					} else {
						if(institutionOrLPName.split("<break>").length>1){
							if(click(driver, getMultipleInstituionRadioButton(timeOut), "mulitple institution radio button", action.BOOLEAN)){
								if(selectMultipleInstitutionOrLP(institutionOrLPName, workspace, timeOut)){
									appLog.info("Successfully selected all the required Insitution or LP.");
								}
							} else {
								appLog.error("Not able to upload files in multiple instituions.");
								BaseLib.sa.assertTrue(false,"Not able to upload files in multiple instituions.");
							}
						} else if (uploadFileAddTo!=null && uploadFileAddTo.toString().equalsIgnoreCase(UploadFileActions.BulkUploaderOrFileSplitter.toString())){
							if(click(driver, getBulkUploaderOrFileSplitterRadioButton(timeOut), "Bulk Upload Radio Button", action.BOOLEAN)){
								dropImage="BulkUpload.jpg";
								flag = true;
							} else {
								appLog.error("Not able to click on bulk upload radio button.");
								BaseLib.sa.assertTrue(false,"Not able to click on bulk upload radio button.");
							}
						}
						System.err.println("Click on next button");
						if(click(driver, getUploadNextButton(workspace, timeOut), "Next Button", action.BOOLEAN)){
							flag = true;
						}
					}
					if(flag){
						if(dragDropFiles(dragFromFolder, dropImage)){
							List<WebElement> files = draggedFilesInFileUploadAtCRMSide();
							if (files.isEmpty()) {
								driver.close();
								driver.switchTo().window(parentWin);
								if (pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())) {
									System.out.println("Searching for the frame");
									switchToFrame(driver, 30, getFrame(PageName.FundsPage, 30));
								}
								if (counter > 1) {
									appLog.error(
											"Tried Upload for three times but still not able to upload file in folderpath: "
													+ path);
									BaseLib.sa.assertTrue(false,
											"Tried Upload for two times but still not able to upload file in folderpath: "
													+ path);
									return false;
								}
								counter++;
								z--;
								continue;
							}
							List<String> droppedFileNames = new ArrayList<String>();
							List<WebElement> droppedFiles = driver.findElements(By.xpath("//span[@class='File']/following-sibling::b"));
							if(!droppedFiles.isEmpty()){
								for(int i = 0; i < droppedFiles.size(); i++){
									droppedFileNames.add(getText(driver, droppedFiles.get(i), "Dropped Files", action.BOOLEAN).trim());
								}
								Collections.sort(droppedFileNames,compare);
								String previousuploadedFiles = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, identifyLabel(path, UploadFileActions.Upload));
								String newlyUploadedFiles = createStringOutOfList(droppedFileNames);
								if(previousuploadedFiles!=null && !previousuploadedFiles.isEmpty()){
									newlyUploadedFiles = previousuploadedFiles+"<break>"+newlyUploadedFiles;
								}
								if (ExcelUtils.writeData(newlyUploadedFiles, "filePath", excelLabel.TestCases_Name, currentlyExecutingTC, identifyLabel(path, UploadFileActions.Upload))) {
									appLog.info("written uploaded file data to excel");
								}
								else {
									appLog.error("could not write uploaded files information to excel");
								}
							} else {
								appLog.error("0 files are dropped.");
								BaseLib.sa.assertTrue(false,"0 files are dropped.");
							}
							appLog.info("Successfully uploaded files.");
							
							BaseLib.ListofUploadedfiles = new ArrayList<String>();
							for (int j = 0; j < files.size(); j++) {
								BaseLib.ListofUploadedfiles.add(files.get(j).getText().trim());
							}
							if (!uploadUpdate.toString().equalsIgnoreCase(UploadFileActions.Update.toString())) {
								if (!path.contains("(Internal)")) {
									for (int j = 0; j < files.size(); j++) {
										BaseLib.uniquedocs.add(files.get(j).getText().trim());
									}
								}
							}
							WebElement ele= BaseLib.edriver.findElement(By.cssSelector("#btnSave"));
							scrollDownThroughWebelement(driver, ele, "save button");
							try{
								ele.click();
								appLog.info("Clicked on Save Button");
//								if(click(driver, getUploadSaveButton(timeOut), "Upload Save Button", action.BOOLEAN)){
									if(uploadUpdate.toString().equalsIgnoreCase(UploadFileActions.Update.toString())){
										List<String> duplicateFileName = new ArrayList<String>();
										List<WebElement> duplicateFiles = driver.findElements(By.xpath("//table[@id='GridViewDuplicateFiles']//tr/td[1]//span"));
										if(!duplicateFiles.isEmpty()){
											for(int i = 0; i < duplicateFiles.size(); i++){
												duplicateFileName.add(getAttribute(driver, duplicateFiles.get(i), "Duplicate File Name", "title"));
											}
											String previousUpdatedFiles = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, identifyLabel(path, UploadFileActions.Update));
											String newlyupdatedFiles = createStringOutOfList(duplicateFileName);
											if(previousUpdatedFiles!=null && !previousUpdatedFiles.isEmpty()){
												newlyupdatedFiles = previousUpdatedFiles+"<break>"+newlyupdatedFiles;
											}
											ExcelUtils.writeData(newlyupdatedFiles, "filePath", excelLabel.TestCases_Name, currentlyExecutingTC, identifyLabel(path, UploadFileActions.Update));
										} else {
											appLog.error("There is no list of updated file");
											BaseLib.sa.assertTrue(false,"There is no list of updated file");
										}
										ele= BaseLib.edriver.findElement(By.cssSelector("#lnkReplaceAll"));
										scrollDownThroughWebelement(driver, ele, "update all button");
										try{
											ele.click();
											appLog.info("Clicked on update all button ");
											
										}catch(Exception e){
											appLog.error("Update Pop Up in not Displaying.");
											BaseLib.sa.assertTrue(false,"Update Pop Up in not Displaying.");
											flag = false;
										}
									}
									if(isAlertPresent(driver)){
										String alertText = switchToAlertAndGetMessage(driver, timeOut, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, timeOut, action.ACCEPT);
										if(alertText.contains(DocumentUploadSuccessMsg)){
											appLog.info("Upload success msg verified successfully.");
										} else {
											appLog.error("Upload successfull alert message is not verified.Expected: Document(s) uploaded successfully.\tActual: "+alertText);
											BaseLib.sa.assertTrue(false,"Upload successfull alert message is not verified.Expected: Document(s) uploaded successfully.\tActual: "+alertText);
											flag = false;
										}
									} else {
										appLog.info("upload Successfull alert message is not poping up.");
										BaseLib.sa.assertTrue(false,"upload Successfull alert message is not poping up.");
										flag = false;
									}
//								} else {
//									appLog.error("Cannot click on save button.");
//									BaseLib.sa.assertTrue(false,"Cannot click on save button.");
//									flag = false;
//								}
							}catch(Exception e){
								appLog.error("Cannot click on save button.");
								BaseLib.sa.assertTrue(false,"Cannot click on save button.");
								flag = false;
							}
						} else {
							appLog.error("Not able to upload files.");
							BaseLib.sa.assertTrue(false,"Not able to upload files.");
							flag = false;
						}
					}
				}
				driver.switchTo().window(parentWin);
			} else {
				appLog.error("Upload Icon cannot be clicked, So cannot continue with the upload process.");
				BaseLib.sa.assertTrue(false,"Upload Icon cannot be clicked, So cannot continue with the upload process.");
			}
		} else {
			appLog.error(path+" Folder structure is not verified.");
			BaseLib.sa.assertTrue(false,path+" Folder structure is not verified.");
		}
		return flag;
	}
	
	/**
	 * @author Ankit Jaiswa
	 * @param institutionOrLPName
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean selectMultipleInstitutionOrLP(String institutionOrLPName, Workspace workspace, int timeOut){
		WebElement ele = null;
		String[] tempVariable;
		boolean returnValue=true;
		tempVariable = institutionOrLPName.split("<break>");
		for(int i = 0; i < tempVariable.length; i++){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				ele = FindElement(driver, "//td[text()='"+tempVariable[i]+"']/preceding-sibling::td/input", tempVariable[i]+" instituion CheckBox", action.BOOLEAN, timeOut);
			} else {
				ele = FindElement(driver, "//a[text()='"+tempVariable[i].split("/")[1]+"']/preceding-sibling::input", " LP checkBox", action.BOOLEAN, timeOut);
			}
			if(!isSelected(driver, ele, tempVariable[i]+" CheckBox")){
				if(click(driver, ele, tempVariable[i]+" CheckBox", action.BOOLEAN)){
					appLog.info("Successfully selected: "+tempVariable[i]);
				} else {
					appLog.error("not able to select: "+tempVariable[i]);
					BaseLib.sa.assertTrue(false,"Not able to select: "+tempVariable[i]);
					returnValue=false;
				}
			} else {
				appLog.info(tempVariable[i]+" is already selected.");
			}
		}

		return returnValue;
	}
	
	/**
	 * @author Ankur Rana
	 * @param dragFromFolder
	 * @param dropLocImageName
	 * @return true/false
	 */
	public boolean dragDropFiles(String dragFromFolder, String dropLocImageName) {
		Screen screen = new Screen();
		try {
			System.err.println(System.getProperty("user.dir")+"\\"+dragFromFolder);
			Process process = Runtime.getRuntime()
					.exec(System.getProperty("user.dir") + "/OpenFolder.exe" + " " + dragFromFolder);
			process.waitFor();
			if (dragFromFolder.contains("\\")) {
				dragFromFolder = (dragFromFolder
						.split(Pattern.quote("\\")))[(dragFromFolder.split(Pattern.quote("\\")).length - 1)];
			}
			process = Runtime.getRuntime().exec(".\\AutoIT\\activateFilesToUpload.exe" + " " + dragFromFolder);
			process.waitFor();
			ThreadSleep(5000);
			screen.keyDown(Key.CTRL);
			screen.type("a");
			screen.keyUp(Key.CTRL);
			screen.drag(".\\AutoIT\\Drag.jpg");
			screen.mouseMove(-150, -100);
			Runtime.getRuntime().exec(".\\AutoIT\\UploadWindow.exe");
			try{
				screen.wait(".\\AutoIT\\"+dropLocImageName, 10);
				screen.dropAt(".\\AutoIT\\"+dropLocImageName);
				
			} catch (Exception e){
				System.err.println("After exception is running.");
				screen.wait(".\\"+dropLocImageName, 10);
				screen.dropAt(".\\"+dropLocImageName);
			}
			process = Runtime.getRuntime()
					.exec(System.getProperty("user.dir") + "\\AutoIT\\CloseFolder.exe" + " " + dragFromFolder);
			process.waitFor();
			System.err.println("Successfully cdroped files");
		} catch (FindFailed | IOException | InterruptedException e) {
			appLog.info("Issue with drag and drop");
			return false;
		}
		return true;
	}
	
	/**
	 * @param path
	 * @param uploadFileActions
	 * @return true/false
	 */
	public excelLabel identifyLabel(String path, UploadFileActions uploadFileActions){
		if(path.contains("(Common)")){
			if(uploadFileActions.toString().equalsIgnoreCase(UploadFileActions.Update.toString())){
				return excelLabel.UpdatedFileCommon;
			} else {
				return excelLabel.UploadedFileCommon;
			}
		} else if (path.contains("(Shared)")){
			if(uploadFileActions.toString().equalsIgnoreCase(UploadFileActions.Update.toString())){
				return excelLabel.UpdatedFileShared;
			} else {
				return excelLabel.UploadedFileShared;
			}
		} else if (path.contains("(Internal)")){
			if(uploadFileActions.toString().equalsIgnoreCase(UploadFileActions.Update.toString())){
				return excelLabel.UpdatedFileInternal;
			} else {
				return excelLabel.UploadedFileInternal;
			}
		} else {
			if(uploadFileActions.toString().equalsIgnoreCase(UploadFileActions.Update.toString())){
				return excelLabel.UpdatedFileStandard;
			} else {
				return excelLabel.UploadedFileStandard;
			}
		}
	}
	
	/**
	 * 
	 * @param InstitutionOrLPName
	 * @return
	 */
	public boolean clickOnRenameManageTargetInstitution(String Institution) {
		WebElement targetRename=FindElement(driver, "//div[text()='"+Institution+"']/span","Institution Account Id", action.BOOLEAN,50);
		try{
			for(int i=0;i<2;i++){
				((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style','display: inline-block;');",targetRename);
			}
		}catch(Exception e){
			return false;
		}
		return click(driver,targetRename, "Rename a folder", action.BOOLEAN);
	}
	
	/**
	 * @return the Record Count
	 * For Commitment Page use FundraisingWorkspace
	 * 
	 */
	public String getRecordCountvalue(Workspace workspace, int timeOut) {
		String s = "";
		WebElement ele = getRecordCount(workspace, timeOut);
		if (ele != null) {
			s = ele.getText().trim();
		}
		return s;
	}
	/**
	 * @author Akul Bhutani
	 * @param upload_file_name
	 * @param timeOut
	 * @return WebElement
	 */
	public WebElement getCrossIcon(String upload_file_name, int timeOut) {
		return isDisplayed(driver, FindElement(driver, "//b[text()='"+upload_file_name+"']//../a[@title='Remove']/img", "cross icon adjacent to uploaded file in upload window", action.SCROLLANDBOOLEAN, timeOut), "visibility", timeOut, "cross icon adjacent to uploaded file in upload window");

	}
	
	
	/**
	 * @author Akul Bhutani
	 * @param li
	 * @return true/flase
	 */
	public boolean removeAllFilesUploadWindow(List<WebElement> li) {
		boolean flag = true;
		if (!li.isEmpty()) {
		for (int i = 0;i<li.size();i++) {
			if (click(driver, li.get(i), "cross icon of file "+i, action.SCROLLANDBOOLEAN)) {
				ThreadSleep(3000);
			}
			else {
				flag = false;
				appLog.error("cross icon of "+i+"th file is not clickable");
				sa.assertTrue(false, "cross icon of "+i+"th file is not clickable");
			}
		}
		}
		else {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * @author Akul Bhutani
	 * @param fileName
	 * @param timeOut
	 * @return WebElement
	 */
	public WebElement findPresentOrAbsentFileOnUploadWindow(String fileName, int timeOut) {
		return isDisplayed(driver, FindElement(driver, "//li[@id='newfile']//b[text()='"+fileName+"']", fileName+" on upload window", action.BOOLEAN, timeOut), "visibility", timeOut, fileName+" on upload window");
	}
	
	/**
	 * @author Akul Bhutani
	 * @param inst
	 * @return WebElement
	 */
	public WebElement uploadWindowInstitutionTextElement(String inst) {
		return isDisplayed(driver, FindElement(driver,"//table[@id='GridViewInstitutions']//td[text()='"+inst+"']" , "institution present in standard folder upload window", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "institution present in standard folder uplaod window");
	}
//newcomment
	/**
	 * @author Ankur Rana
	 * @param institutionNAme
	 * @param LimitedPartnerName
	 * @return true/false
	 */
	public boolean verifyLPStructureInManageInvestor(String institutionNAme,String LimitedPartnerName){
		WebElement xpath=FindElement(driver, "//label[@title='"+institutionNAme+"']", "Institution Name", action.SCROLLANDBOOLEAN, 60);
		if(click(driver, xpath, "InstitutionName", action.SCROLLANDBOOLEAN)){
		WebElement ele=FindElement(driver, "//label[@title='"+institutionNAme+"']/../../../following-sibling::ul/li/div/a//label[@title='"+LimitedPartnerName+"']", "Limited Partner NAme", action.SCROLLANDBOOLEAN, 60);
		if(ele!=null){
			appLog.info("LP:"+LimitedPartnerName+"Is Displaying under institution:"+institutionNAme);
		}else{
			appLog.info("LP:"+LimitedPartnerName+"Is not Displaying under institution:"+institutionNAme);
			return false;
		}
		}else{
			appLog.info("Not able to click on institution name");
			return false;
		}
		return true;
	}
	
	/**
	 * @param workspace
	 * @param timeOut
	 */
	public void recover(Workspace workspace, int timeOut){
		refresh(driver);
		switchToFrame(driver, timeOut/2, getFrame(PageName.FundsPage, timeOut/2));
		scrollDownThroughWebelement(driver, getWorkspaceSectionView(workspace, timeOut), workspace.toString()+" View.");
	}

	/**
	 * @author Ankur Rana
	 * @param InstitutionName
	 * @param LimitedPartner
	 * @return true/false
	 */
	public boolean clickOnRenameManageTargetLimitedPartner(String InstitutionName,String LimitedPartner) {
		WebElement xpath=FindElement(driver, "//label[@title='"+InstitutionName+"']", "Institution Name", action.SCROLLANDBOOLEAN, 60);
		if(click(driver, xpath, "InstitutionName", action.SCROLLANDBOOLEAN)){
			WebElement targetRename=FindElement(driver, "//label[@title='"+LimitedPartner+"']//div", "Limited PArtner", action.SCROLLANDBOOLEAN, 60);
			try{
				for(int i=0;i<2;i++){
					((JavascriptExecutor) driver).executeScript(
					"arguments[0].setAttribute('style','display: inline-block;');",targetRename);
				}
			}catch(Exception e){
				return false;
			}
			return click(driver,targetRename, "Rename a folder", action.BOOLEAN);		
	}else{
		appLog.info("Not able to click on institution name");
		return false;
	}
		
	}

	/**
	 * @author Ankur Rana
	 * @param folderPathForStandard
	 * @param foldertype
	 * @param checkForChildFolder
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean verifyAddRenameDeleteButton(String folderPathForStandard, FolderType foldertype, YesNo checkForChildFolder, Workspace workspace, int timeOut){
		WebElement ele = null;
		String identifier = null;
		String workspaceSelector = null;
		boolean returnValue = true;
		if(foldertype.toString().equalsIgnoreCase(FolderType.Common.toString())){
			identifier = "(Common)";
		} else if(foldertype.toString().equalsIgnoreCase(FolderType.Shared.toString())){
			identifier = "(Shared)";
		} else if(foldertype.toString().equalsIgnoreCase(FolderType.Internal.toString())){
			identifier = "(Internal)";
		} else if(foldertype.toString().equalsIgnoreCase(FolderType.Standard.toString())){
			identifier = folderPathForStandard;
		}
		
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "//div[@id='frworkspace']";
		} else {
			workspaceSelector = "//div[@id='invworkspace']";
		}
		//../../../following-sibling::ul/li[1]/div/a/span/label
		ele = FindElement(driver, workspaceSelector+"//label[contains(text(),'"+identifier+"')]", foldertype+" parent folder", action.BOOLEAN, 30);
		if(ele!=null){
			if(mouseHoverJScript(driver, ele)){
				ele = FindElement(driver, workspaceSelector+"//label[contains(text(),'"+identifier+"')]//span[@title='Rename Folder']", "Rename Folder Icon", action.BOOLEAN, timeOut);
				if(ele != null){
					appLog.info("Rename icon is verified for "+foldertype.toString()+" parent folder.");
				} else {
					appLog.error("Rename icon is not verified for "+foldertype.toString()+" parent folder.");
					BaseLib.sa.assertTrue(false,"Rename icon is not verified for "+foldertype.toString()+" parent folder.");
					returnValue = false;
				}
				ele = FindElement(driver, workspaceSelector+"//label[contains(text(),'"+identifier+"')]//span[@title='Add a Folder']", "Add a Folder Icon", action.BOOLEAN, timeOut);
				if(ele != null){
					appLog.info("Add a Folder icon is verified for "+foldertype.toString()+" parent folder.");
				} else {
					appLog.error("Add a Folder icon is not verified for "+foldertype.toString()+" parent folder.");
					BaseLib.sa.assertTrue(false,"Add a Folder icon is not verified for "+foldertype.toString()+" parent folder.");
					returnValue = false;
				}
				ele = FindElement(driver, workspaceSelector+"//label[contains(text(),'"+identifier+"')]//span[@title='Delete Folder']", "Delete Folder Icon", action.BOOLEAN, timeOut);
				if(ele != null){
					appLog.info("Delete Folder icon is verified for "+foldertype.toString()+" parent folder.");
				} else {
					appLog.error("Delete Folder icon is not verified for "+foldertype.toString()+" parent folder.");
					BaseLib.sa.assertTrue(false,"Delete Folder icon is not verified for "+foldertype.toString()+" parent folder.");
					returnValue = false;
				}
			} else {
				appLog.error("Cannot hover mouse on "+foldertype.toString()+" parent folder, So cannot check the button.");
				BaseLib.sa.assertTrue(false,"Cannot hover mouse on "+foldertype.toString()+" parent folder, So cannot check the button.");
				returnValue = false;
			}
		} else {
			appLog.error(foldertype.toString()+" parent folder is not present, So cannot verify the icon.");
			BaseLib.sa.assertTrue(false,foldertype.toString()+" parent folder is not present, So cannot verify the icon.");
			returnValue = false;
		}
		if(checkForChildFolder.toString().equalsIgnoreCase(YesNo.Yes.toString())){
			ele = FindElement(driver, workspaceSelector+"//label[contains(text(),'"+identifier+"')]/../../../following-sibling::ul/li[1]/div/a/span/label", foldertype+" child folder", action.BOOLEAN, 30);
			if(ele!=null){
				if(mouseHoverJScript(driver, ele)){
					ele = FindElement(driver, workspaceSelector+"//label[contains(text(),'"+identifier+"')]/../../../following-sibling::ul/li[1]/div/a/span/label//span[@title='Rename Folder']", "Rename Folder Icon", action.BOOLEAN, timeOut);
					if(ele != null){
						appLog.info("Rename icon is verified for "+foldertype.toString()+" child folder.");
					} else {
						appLog.error("Rename icon is not verified for "+foldertype.toString()+" child folder.");
						BaseLib.sa.assertTrue(false,"Rename icon is not verified for "+foldertype.toString()+" child folder.");
						returnValue = false;
					}
					ele = FindElement(driver, workspaceSelector+"//label[contains(text(),'"+identifier+"')]/../../../following-sibling::ul/li[1]/div/a/span/label//span[@title='Add a Folder']", "Add a Folder Icon", action.BOOLEAN, timeOut);
					if(ele != null){
						appLog.info("Add a Folder icon is verified for "+foldertype.toString()+" child folder.");
					} else {
						appLog.error("Add a Folder icon is not verified for "+foldertype.toString()+" child folder.");
						BaseLib.sa.assertTrue(false,"Add a Folder icon is not verified for "+foldertype.toString()+" child folder.");
						returnValue = false;
					}
					ele = FindElement(driver, workspaceSelector+"//label[contains(text(),'"+identifier+"')]/../../../following-sibling::ul/li[1]/div/a/span/label//span[@title='Delete Folder']", "Delete Folder Icon", action.BOOLEAN, timeOut);
					if(ele != null){
						appLog.info("Delete Folder icon is verified for "+foldertype.toString()+" child folder.");
					} else {
						appLog.error("Delete Folder icon is not verified for "+foldertype.toString()+" child folder.");
						BaseLib.sa.assertTrue(false,"Delete Folder icon is not verified for "+foldertype.toString()+" child folder.");
						returnValue = false;
					}
				} else {
					appLog.error("Cannot hover mouse on "+foldertype.toString()+" child folder, So cannot check the button.");
					BaseLib.sa.assertTrue(false,"Cannot hover mouse on "+foldertype.toString()+" child folder, So cannot check the button.");
					returnValue = false;
				}
			} else {
				appLog.error(foldertype.toString()+" child folder is not present, So cannot verify the icon.");
				BaseLib.sa.assertTrue(false,foldertype.toString()+" child folder is not present, So cannot verify the icon.");
				returnValue = false;
			}
		}
		
		return returnValue;
	}

	/**
	 * @author Ankur Rana
	 * @param folderPath
	 * @param foldertype
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean clickOnAddAFolderButton(String folderPath, FolderType foldertype, Workspace workspace, int timeOut){
		WebElement ele = null;
		String identifier = null;
		String workspaceSelector = null;
		boolean returnValue = true;
		if(foldertype.toString().equalsIgnoreCase(FolderType.Common.toString()) && folderPath == null){
			identifier = "(Common)";
		} else if(foldertype.toString().equalsIgnoreCase(FolderType.Shared.toString()) && folderPath == null){
			identifier = "(Shared)";
		} else if(foldertype.toString().equalsIgnoreCase(FolderType.Internal.toString()) && folderPath == null){
			identifier = "(Internal)";
		} else if(foldertype.toString().equalsIgnoreCase(FolderType.Standard.toString())){
			identifier = folderPath;
		}
		
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "//div[@id='frworkspace']";
		} else {
			workspaceSelector = "//div[@id='invworkspace']";
		}
		//../../../following-sibling::ul/li[1]/div/a/span/label
		String xpath = "";
		if(folderPath!=null && folderPath.contains("/")){
			xpath = workspaceSelector+"//label[contains(text(),'"+folderPath.split("/")[0]+"')]/../../../following-sibling::ul/li[1]/div/a/span/label[contains(text(),'"+folderPath.split("/")[1]+"')]";
		} else {
			xpath = workspaceSelector+"//label[contains(text(),'"+identifier+"')]";
		}
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			scrollDownThroughWebelement(driver, getFundRaisingWorkSpaceSection(30), "fundraising workspace View");
			
		}else if (workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			scrollDownThroughWebelement(driver, getInvestorWorkSpaceSection(30), "investor workspace View");
		}
		ele = FindElement(driver, xpath, foldertype+" parent folder", action.BOOLEAN, 30);
		if(ele!=null){
			if(mouseHoverJScript(driver, ele)){
//				ele = FindElement(driver, workspaceSelector+"//label[contains(text(),'"+identifier+"')]//span[@title='Rename Folder']", "Rename Folder Icon", action.BOOLEAN, timeOut);
//				if(ele != null){
//					appLog.info("Rename icon is verified for "+foldertype.toString()+" parent folder.");
//				} else {
//					appLog.error("Rename icon is not verified for "+foldertype.toString()+" parent folder.");
//					BaseLib.sa.assertTrue(false,"Rename icon is not verified for "+foldertype.toString()+" parent folder.");
//					returnValue = false;
//				}
				ele = FindElement(driver, xpath+"//span[@title='Add a Folder']", "Add a Folder Icon", action.BOOLEAN, timeOut);
				if(clickUsingJavaScript(driver, ele, "Add a Folder BUtton")){
					appLog.info("Add a Folder icon is clicked for "+foldertype.toString()+" parent folder.");
				} else {
					appLog.error("Add a Folder icon is not clicked for "+foldertype.toString()+" parent folder.");
					BaseLib.sa.assertTrue(false,"Add a Folder icon is not clicked for "+foldertype.toString()+" parent folder.");
					returnValue = false;
				}
//				ele = FindElement(driver, workspaceSelector+"//label[contains(text(),'"+identifier+"')]//span[@title='Delete Folder']", "Delete Folder Icon", action.BOOLEAN, timeOut);
//				if(ele != null){
//					appLog.info("Delete Folder icon is verified for "+foldertype.toString()+" parent folder.");
//				} else {
//					appLog.error("Delete Folder icon is not verified for "+foldertype.toString()+" parent folder.");
//					BaseLib.sa.assertTrue(false,"Delete Folder icon is not verified for "+foldertype.toString()+" parent folder.");
//					returnValue = false;
//				}
			} else {
				appLog.error("Cannot hover mouse on "+foldertype.toString()+" parent folder, So cannot click add a folder button.");
				BaseLib.sa.assertTrue(false,"Cannot hover mouse on "+foldertype.toString()+" parent folder, So cannot click add a folder button.");
				returnValue = false;
			}
		} else {
			appLog.error(foldertype.toString()+" parent folder is not present, So cannot click add a folder button.");
			BaseLib.sa.assertTrue(false,foldertype.toString()+" parent folder is not present, So cannot click add a folder button.");
			returnValue = false;
		}
		return returnValue;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param folderPath
	 * @param foldertype
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean clickOnRenameButton(String folderPath, FolderType foldertype, Workspace workspace, int timeOut){
		WebElement ele = null;
		String identifier = null;
		String workspaceSelector = null;
		boolean returnValue = true;
		if(foldertype.toString().equalsIgnoreCase(FolderType.Common.toString()) && folderPath == null){
			identifier = "(Common)";
		} else if(foldertype.toString().equalsIgnoreCase(FolderType.Shared.toString()) && folderPath == null){
			identifier = "(Shared)";
		} else if(foldertype.toString().equalsIgnoreCase(FolderType.Internal.toString()) && folderPath == null){
			identifier = "(Internal)";
		} else if(foldertype.toString().equalsIgnoreCase(FolderType.Standard.toString())){
			identifier = folderPath;
		}
		
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "//div[@id='frworkspace']";
		} else {
			workspaceSelector = "//div[@id='invworkspace']";
		}
		//../../../following-sibling::ul/li[1]/div/a/span/label
		String xpath = "";
		if(folderPath!=null && folderPath.contains("/")){
			xpath = workspaceSelector+"//label[contains(text(),'"+folderPath.split("/")[0]+"')]/../../../following-sibling::ul/li[1]/div/a/span/label[contains(text(),'"+folderPath.split("/")[1]+"')]";
		} else {
			xpath = workspaceSelector+"//label[contains(text(),'"+identifier+"')]";
		}
		ele = FindElement(driver, xpath, foldertype+" parent folder", action.BOOLEAN, 30);
		if(ele!=null){
			if(mouseOverOperation(driver, ele)){
				ele = FindElement(driver, xpath+"//span[@title='Rename Folder']", "Rename Folder Icon", action.BOOLEAN, timeOut);
//				if(ele != null){
//					appLog.info("Rename icon is verified for "+foldertype.toString()+" parent folder.");
//				} else {
//					appLog.error("Rename icon is not verified for "+foldertype.toString()+" parent folder.");
//					BaseLib.sa.assertTrue(false,"Rename icon is not verified for "+foldertype.toString()+" parent folder.");
//					returnValue = false;
//				}
//				ele = FindElement(driver, workspaceSelector+"//label[contains(text(),'"+identifier+"')]//span[@title='Add a Folder']", "Add a Folder Icon", action.BOOLEAN, timeOut);
				if(click(driver, ele, "Rename BUtton", action.BOOLEAN)){
					appLog.info("Rename icon is clicked for "+foldertype.toString()+" parent folder.");
				} else {
					appLog.error("Rename icon is not clicked for "+foldertype.toString()+" parent folder.");
					BaseLib.sa.assertTrue(false,"Rename icon is not clicked for "+foldertype.toString()+" parent folder.");
					returnValue = false;
				}
//				ele = FindElement(driver, workspaceSelector+"//label[contains(text(),'"+identifier+"')]//span[@title='Delete Folder']", "Delete Folder Icon", action.BOOLEAN, timeOut);
//				if(ele != null){
//					appLog.info("Delete Folder icon is verified for "+foldertype.toString()+" parent folder.");
//				} else {
//					appLog.error("Delete Folder icon is not verified for "+foldertype.toString()+" parent folder.");
//					BaseLib.sa.assertTrue(false,"Delete Folder icon is not verified for "+foldertype.toString()+" parent folder.");
//					returnValue = false;
//				}
			} else {
				appLog.error("Cannot hover mouse on "+foldertype.toString()+" parent folder, So cannot click Rename button.");
				BaseLib.sa.assertTrue(false,"Cannot hover mouse on "+foldertype.toString()+" parent folder, So cannot click Rename button.");
				returnValue = false;
			}
		} else {
			appLog.error(foldertype.toString()+" parent folder is not present, So cannot click Rename button.");
			BaseLib.sa.assertTrue(false,foldertype.toString()+" parent folder is not present, So cannot click Rename button.");
			returnValue = false;
		}
		return returnValue;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param folderPath
	 * @param foldertype
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean clickOnDeleteButton(String folderPath, FolderType foldertype, Workspace workspace, int timeOut){
		WebElement ele = null;
		String identifier = null;
		String workspaceSelector = null;
		boolean returnValue = true;
		if(foldertype.toString().equalsIgnoreCase(FolderType.Common.toString()) && folderPath == null){
			identifier = "(Common)";
		} else if(foldertype.toString().equalsIgnoreCase(FolderType.Shared.toString()) && folderPath == null){
			identifier = "(Shared)";
		} else if(foldertype.toString().equalsIgnoreCase(FolderType.Internal.toString()) && folderPath == null){
			identifier = "(Internal)";
		} else if(foldertype.toString().equalsIgnoreCase(FolderType.Standard.toString())){
			identifier = folderPath;
		}
		
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "//div[@id='frworkspace']";
		} else {
			workspaceSelector = "//div[@id='invworkspace']";
		}
		//../../../following-sibling::ul/li[1]/div/a/span/label
		String xpath = "";
		if(folderPath!=null && folderPath.contains("/")){
			xpath = workspaceSelector+"//label[contains(text(),'"+folderPath.split("/")[0]+"')]/../../../following-sibling::ul/li[1]/div/a/span/label[contains(text(),'"+folderPath.split("/")[1]+"')]";
		} else {
			xpath = workspaceSelector+"//label[contains(text(),'"+identifier+"')]";
		}
		ele = FindElement(driver, xpath, foldertype+" parent folder", action.BOOLEAN, 30);
		if(ele!=null){
			if(mouseOverOperation(driver, ele)){
				ele = FindElement(driver, xpath+"//span[@title='Delete Folder']", "Delete Folder Icon", action.BOOLEAN, timeOut);
//				if(ele != null){
//					appLog.info("Delete icon is verified for "+foldertype.toString()+" parent folder.");
//				} else {
//					appLog.error("Delete icon is not verified for "+foldertype.toString()+" parent folder.");
//					BaseLib.sa.assertTrue(false,"Delete icon is not verified for "+foldertype.toString()+" parent folder.");
//					returnValue = false;
//				}
//				ele = FindElement(driver, workspaceSelector+"//label[contains(text(),'"+identifier+"')]//span[@title='Add a Folder']", "Add a Folder Icon", action.BOOLEAN, timeOut);
				if(click(driver, ele, "Delete BUtton", action.BOOLEAN)){
					appLog.info("Delete icon is clicked for "+foldertype.toString()+" parent folder.");
				} else {
					appLog.error("Delete icon is not clicked for "+foldertype.toString()+" parent folder.");
					BaseLib.sa.assertTrue(false,"Delete icon is not clicked for "+foldertype.toString()+" parent folder.");
					returnValue = false;
				}
//				ele = FindElement(driver, workspaceSelector+"//label[contains(text(),'"+identifier+"')]//span[@title='Delete Folder']", "Delete Folder Icon", action.BOOLEAN, timeOut);
//				if(ele != null){
//					appLog.info("Delete Folder icon is verified for "+foldertype.toString()+" parent folder.");
//				} else {
//					appLog.error("Delete Folder icon is not verified for "+foldertype.toString()+" parent folder.");
//					BaseLib.sa.assertTrue(false,"Delete Folder icon is not verified for "+foldertype.toString()+" parent folder.");
//					returnValue = false;
//				}
			} else {
				appLog.error("Cannot hover mouse on "+foldertype.toString()+" parent folder, So cannot click Delete button.");
				BaseLib.sa.assertTrue(false,"Cannot hover mouse on "+foldertype.toString()+" parent folder, So cannot click Delete button.");
				returnValue = false;
			}
		} else {
			appLog.error(foldertype.toString()+" parent folder is not present, So cannot click Delete button.");
			BaseLib.sa.assertTrue(false,foldertype.toString()+" parent folder is not present, So cannot click Delete button.");
			returnValue = false;
		}
		return returnValue;
	}
		
	/**
	 * @author Akul Bhutani
	 * @param inst
	 * @return WebElement
	 */
	public WebElement findSelectedInstitutionsInUploadWindow(String inst) {
			String xpath = "//span[contains(text(),'"+inst+"')]";
				return isDisplayed(driver, FindElement(driver, xpath, "institution name present in selected institutions(upload window)", action.SCROLLANDBOOLEAN, 30), "visibility", 30,"institutions name present in selected institutions(upload window)");
		}
	
	/**
	 * @author Akul Bhutani
	 * @param newFileName
	 * @param oldFileName
	 * @param oldestFileName
	 * @param version
	 * @return true/false
	 */
	public boolean verifyManageVersion2ndTime(String newFileName, String oldFileName, String oldestFileName,String version) {
	int oneLessVersion = Character.getNumericValue(version.charAt(1));
	int twoLessVersion,threeLessVersion;
	oneLessVersion -=1;
	twoLessVersion = oneLessVersion - 1;
	threeLessVersion = oneLessVersion - 2;
	int size = getDocumentNameColumnManageVersion().size();
	boolean flag = true, version1Flag=false, version2Flag=false;
	//first row will have new file name and first row current column will have yes
	
	if (getCurrentColumnManageVersion().get(0).getText().trim().equals("Yes")) {
		appLog.info("first row has current as Yes");
	}
	else if (getCurrentColumnManageVersion().get(0).getText().trim().equals("No")){
		appLog.error("first row has current as No");
		flag = false;
	}
	if (getVersionColumnManageVersion().get(0).getText().trim().equals(version)) {
		appLog.info("the correct version "+version+" is being displayed for "+oldFileName);
	}
	else {
		appLog.error("the version name is not correct. expected is "+version);
		flag = false;
	}
	if (getActionsColumnManageVersion().get(0).getAttribute("class").equalsIgnoreCase("disabled")) {
		appLog.info("make current text is disabled in front of "+oldFileName);
	}
	else {
		appLog.error("make current text is enabled in front of "+oldFileName);
		flag = false;
	}
	for (int i = 0;i<size;i++) {
		
		if (i == 0) {
			if (getDocumentNameColumnManageVersion().get(i).getText().trim().equals(newFileName)) {
				appLog.info("first row has new file name");
			}
			else {
				appLog.error("first row does not have the correct (new)file name "+newFileName);
				flag = false;
			}
		}
		else {
			//verify 3rd line v3's document name and 5th line v1's document name
			scrollDownThroughWebelement(driver, getDocumentNameColumnManageVersion().get(i), i+"th element of manage version window");
		if ((oneLessVersion!=0)&&(twoLessVersion!=0)) {
			if (getVersionColumnManageVersion().get(i).getText().trim().equals("V"+oneLessVersion)) {
			version1Flag = true;
			if (getDocumentNameColumnManageVersion().get(i).getText().trim().equals(oldFileName)) {
				appLog.info("old file name "+oldFileName+" has been found on manage version window on "+i+1+"th row along with V"+oneLessVersion);
			}
			else {
				appLog.error("version "+oneLessVersion+" has "+getDocumentNameColumnManageVersion().get(i).getText()+" adjacent and not expected");
				flag = false;
			}
		}
			if (getVersionColumnManageVersion().get(i).getText().trim().equals("V"+threeLessVersion)) {
				version2Flag = true;
				if (getDocumentNameColumnManageVersion().get(i).getText().trim().equals(oldestFileName)) {
					
					appLog.info("oldest file name "+oldestFileName+"has been successfuly verified with V"+threeLessVersion);
				}
				else {
					appLog.error("version number "+threeLessVersion+" has "+getDocumentNameColumnManageVersion().get(i).getText()+" adjacent and not expected");
					flag = false;
				}
			}
		}
		else {
			appLog.error("version number has gone to 0, file cannot be found");
			flag = false;
		}
		
	}
	}
	if (version1Flag == false) {
		appLog.error("version was not found V"+oneLessVersion);
		flag = version1Flag;
	}
	if (version2Flag == false) {
		appLog.error("version was not found V"+twoLessVersion);
		flag = version2Flag;
	}
	return flag;
}
	
	/**
	* @author Akul Bhutani
	 * @param newFileName
	 * @param oldFileName
	 * @param oldestFileName
	 * @param version
	 * @return true/false
	 */
	public boolean verifyMakeCurrentFunctionalityManageVersion(String newFileName, String oldFileName,String oldestFileName, String version) {
	WebElement el = null;
	boolean oldestDocFlag = false;
	boolean flag = true;
		//click on make current link left of old file name
		appLog.info("clicking on make current link");
		el = isDisplayed(driver, FindElement(driver, "//span[@title='"+oldFileName+"']/../..//a","make current link in front of old file name", action.BOOLEAN, 30), "visibility", 30, "make current link in front of old file name");
		if (el!=null) {
			if (click(driver, el, "make current link", action.BOOLEAN)) {
				ThreadSleep(5000);
				if (getDocumentNameColumnManageVersion().get(0).getText().trim().equals(oldFileName)) {
					appLog.info(oldFileName + " is succesfully found in first row of manage version");
				}
				else {
					flag = false;
					appLog.error("first row should be "+oldFileName+" but is "+getDocumentNameColumnManageVersion().get(0).getText());
				}
				if (getCurrentColumnManageVersion().get(0).getText().trim().equals("Yes")) {
					appLog.info("yes is present in first row adjacent to "+oldFileName);
				}
				else {
					flag = false;
					appLog.error("no is present in frist row adjacent to "+oldFileName);
				}
				if (getActionsColumnManageVersion().get(0).getAttribute("class").equalsIgnoreCase("disabled")) {
					appLog.info("make current text is disabled adjacent to "+oldFileName);
				}
				else {
					flag = false;
					appLog.error("make current text is enabled adjacent to "+oldFileName);
				}
				if (getVersionColumnManageVersion().get(0).getText().trim().equals(version)) {
					appLog.info("correct version "+version+ " is present in front of "+oldFileName);
				}
				else {
					appLog.error("incorrect version is written in front of "+oldFileName+". expected is "+version+" and present is "+getVersionColumnManageVersion().get(0).getText());
					flag = false;
				}
				for (int i = 0;i<getDocumentNameColumnManageVersion().size();i++) {
					if (getDocumentNameColumnManageVersion().get(i).getText().trim().equals(oldestFileName)) {
						oldestDocFlag = true;
					}
				}
				if (oldestDocFlag == false) {
					flag = oldestDocFlag;
					appLog.error(oldestFileName + " is not found in manage version window");
				}
				else if (oldestDocFlag == true) {
					appLog.info(oldestFileName + " is successfully found in manage version window");
				}
			}
			else {
				flag = false;
				appLog.error("make current link is not clickable on manage version window");
			}
		}
		else {
			appLog.error("element is not displayed on manage version window");
			flag = false;
		}
	
	return flag;
	
}
	
	/**
	* @author Akul Bhutani
	 * @param newFileName
	 * @param newFilePath
	 * @param oldFileName
	 * @param versionOfCurrent
	 * @param updated
	 * @param mult_inst
	 * @param sorting
	 * @param mi
	 * @return true/false
	 */
	public boolean verifyManageVersionUI(String newFileName, String newFilePath,String oldFileName, String versionOfCurrent,boolean updated, boolean mult_inst, boolean sorting, multiInstance mi) {
	boolean flag=true;
	WebElement ele;
	if (getManageVersionHead(60).getText().trim().equals("Manage Versions")) {
		appLog.info("manage version heading text is successfully verified");
	
	}
	else {
		flag = false;
	}
	List<WebElement> actionList = getActionsColumnManageVersion();
	if (!actionList.isEmpty()) {
		int size = actionList.size();
		List<WebElement> versionList=getVersionColumnManageVersion();
		List<WebElement> documentList=getDocumentNameColumnManageVersion();
		List<WebElement> currentList=getCurrentColumnManageVersion();
		
	for (int i = 0;i<size;i++) {
		if (i==0) {
			if (actionList.get(i).getAttribute("class").equalsIgnoreCase("disabled")) {
				appLog.info("first row make current text is disabled on manage version window");
			}
			else {
			appLog.error("make current text on first row is enabled on manage version window");
			BaseLib.sa.assertTrue(false, "make current text on first row is enabled on manage version window" );	
			flag = false;	
			}
			if (versionList.get(i).getText().trim().equals(versionOfCurrent)) {
				appLog.info("first row of version is correct "+versionOfCurrent);
			}
			else {
				appLog.error("version number is wrong of the last updated file. It is "+versionList.get(i).getText()+" and expected is "+versionOfCurrent);
				flag = false;
			}
			
			//checking previous first row document
			ele = isDisplayed(driver, FindElement(driver, "//div[contains(text(),'Manage Version')]/../span//a[text()='"+oldFileName+"']", oldFileName+" new file name link", action.BOOLEAN, 30), "visibility", 30,"new file name link on manage version window" );
			if (ele.getTagName().equals("a")) {
				appLog.info("first document name is url in manage version window");
			}
			//		if (documentList.get(i).getTagName().equals("a")) {
			//	appLog.info("first document name is a url");
			//}
			else {
				appLog.error("first document on manage version popup is not a url, tag name is "+getDocumentNameColumnManageVersion().get(i).getTagName());
				flag = false;
			}
			if (currentList.get(i).getText().trim().equals("Yes")) {
				appLog.info("first row has current value as Yes");
			}
			else {
				appLog.error("the first row does not have current value as Yes");
				flag = false;
			}
		}
		else {
			scrollDownThroughWebelement(driver, getActionsColumnManageVersion().get(i), "scrolling in manage version window");
			if (actionList.get(i).getTagName().equals("a")) {
			appLog.info("make current is a link on row "+i+1);
			}
			else {
				appLog.error("make current text is not a url on "+i+"th row");
				flag = false;
			}
			if (currentList.get(i).getText().trim().equals("No")) {
				appLog.info(i+" row has current value as No");
			}
			else {
				appLog.error("the row"+i+" does not have current value as No, it is "+currentList.get(i).getText());
				flag = false;
			}
			if (!documentList.get(i).getTagName().equals("a")) {
				appLog.info("document names other than first is not a url");
			}
			else {
				appLog.error("document on manage version popup is a url, it is "+documentList.get(i).getTagName());
				flag = false;
			}
			
		}
	}
	if (sorting) {
		if (checkSorting(SortOrder.Decending, versionList)) {
			appLog.info("default sorting has been done on basis of version list correctly");
		}
		else {
			appLog.error("default sorting is incorrect, not on the basis of version list");
			BaseLib.sa.assertTrue(false, "default sorting is incorrect, not on the basis of version list");
		}
		
		//sorting on basis of version
		if (click(driver, getVersionColumnName(60), "version column name", action.BOOLEAN)) {
			ThreadSleep(3000);
			List<WebElement> temp_list = getVersionColumnManageVersion();
			if (checkSorting(SortOrder.Decending, temp_list)) {
				appLog.info("correct descending sorting is applied for version column");
			}
			else {
				appLog.error("descending sorting is wrong for version column");
				BaseLib.sa.assertTrue(false, "descending sorting is wrong for version column");
			}
		}
		
		if (click(driver, getVersionColumnName(60), "version column name", action.BOOLEAN)) {
			ThreadSleep(3000);
			List<WebElement> temp_list = getVersionColumnManageVersion();
			if (checkSorting(SortOrder.Assecending, temp_list)) {
				appLog.info("correct descending sorting is applied for version column");
			}
			else {
				appLog.error("descending sorting is wrong for version column");
				BaseLib.sa.assertTrue(false, "descending sorting is wrong for version column");
			}
		}
		
		//sorting on basis of document name
		if (click(driver, getdocumentNameElementManageVersion(60), "document name column", action.BOOLEAN)) {
			ThreadSleep(3000);
			List<WebElement> temp_list = getDocumentNameColumnForSortingManageVersion();
			if (checkSorting(SortOrder.Assecending, temp_list)) {
				appLog.info("correct descending sorting is applied for document column");
			}
			else {
				appLog.error("descending sorting is wrong for document column");
				BaseLib.sa.assertTrue(false, "descending sorting is wrong for document column");
			}
		}
		
		if (click(driver, getdocumentNameElementManageVersion(60), "document column", action.BOOLEAN)) {
			ThreadSleep(3000);
			List<WebElement> temp_list = getDocumentNameColumnForSortingManageVersion();
			if (checkSorting(SortOrder.Decending, temp_list)) {
				appLog.info("correct descending sorting is applied for document column");
			}
			else {
				appLog.error("descending sorting is wrong for version column");
				BaseLib.sa.assertTrue(false, "descending sorting is wrong for document column");
			}
		}
		
		//sorting on basis of uploaded by
		if (click(driver, getUploadedByElementManageVersion(60), "uploaded by column", action.BOOLEAN)) {
			ThreadSleep(3000);
			List<WebElement> temp_list = getUploadedByColumnManageVersion();
			if (checkSorting(SortOrder.Decending, temp_list)) {
				appLog.info("correct descending sorting is applied for uploaded by column");
			}
			else {
				appLog.error("descending sorting is wrong for uploaded by column");
				BaseLib.sa.assertTrue(false, "descending sorting is wrong for uploaded by column");
			}
		}
		
		if (click(driver, getUploadedByElementManageVersion(60), "uploaded by column", action.BOOLEAN)) {
			List<WebElement> temp_list = getUploadedByColumnManageVersion();
			if (checkSorting(SortOrder.Assecending, temp_list)) {
				appLog.info("correct descending sorting is applied for uploaded by column");
			}
			else {
				appLog.error("descending sorting is wrong for uploaded by column");
				BaseLib.sa.assertTrue(false, "descending sorting is wrong for uploaded by column");
			}
		}
		//sorting on basis of uploaded on
		if (click(driver, getUploadedOnColumnName(60), "uploaded on column", action.BOOLEAN)) {
			ThreadSleep(3000);
			List<WebElement> temp_list = getUploadedOnColumnManageVersion();
			if (checkSorting(SortOrder.Decending, temp_list)) {
				appLog.info("correct descending sorting is applied for uploaded on column");
			}
			else {
				appLog.error("descending sorting is wrong for uploaded on column");
				BaseLib.sa.assertTrue(false, "descending sorting is wrong for uploaded on column");
			}
		}

		if (click(driver, getUploadedOnColumnName(60), "uploaded on column", action.BOOLEAN)) {
			List<WebElement> temp_list = getUploadedOnColumnManageVersion();
			if (checkSorting(SortOrder.Assecending, temp_list)) {
				appLog.info("correct descending sorting is applied for uploaded on column");
			}
			else {
				appLog.error("descending sorting is wrong for uploaded on column");
				BaseLib.sa.assertTrue(false, "descending sorting is wrong for uploaded on column");
			}
		}
		//click manage version refresh button
		click(driver, getManageVersionRefresh(60), "manage version refresh icon", action.SCROLLANDBOOLEAN);
		
	}
	String parentID= null;
	if(updated){
		if (click(driver, getUpdateButtonOnManageVersionPopUp(60), "update button on manage version popup", action.SCROLLANDBOOLEAN)) {
			
				parentID = switchOnWindow(driver);
				if(mult_inst) {
					if (getMultipleInstancesHead(60).getText().trim().equals("Multiple Instances Found")) {
						appLog.info("multiple instances heading is successfully displayed");
					}
					else {
						appLog.error("multiple instance heading is not present");
						BaseLib.sa.assertTrue(false, "multiple instance heading is not present");
					}
					if (getThisOrAllInvestorText(60).getText().trim().equals(FundsPageErrorMessage.thisOrAllInvestor)) {
						appLog.info("text for this or all investor is successfully displayed on update window");
					}
					else {
						appLog.error("text for this or all investor is not present on update window");
						BaseLib.sa.assertTrue(false, "text for this or all investor is not present on update window");
					}
					if (getThisInvestorOnlyButton(60)!=null) {
						appLog.info("button to select this investor only is successfully displayed on update window");
					}
					else {
						appLog.error("button to select this investor only is not present on update window");
						BaseLib.sa.assertTrue(false, "button to select this investor only is not present on update window");
					}
					if (getAllInvestorButton(60)!=null) {
						appLog.info("button to select all investors is successfully displayed on update window");
					}
					else {
						appLog.error("button to select all investors is not present on update window");
						BaseLib.sa.assertTrue(false, "button to select all investors is not present on update window");
					}
					//clicking on this investor only or all investors
					if (mi == multiInstance.ThisInvestorOnly) {
					if (click(driver, getThisInvestorOnlyButton(60), "this investor only button on manage version duplicate doc window", action.BOOLEAN)) {
						appLog.info("clicked on this investor only");
					}
					else {
						appLog.error("this investor only button is not clickable on manage version window");
					}
					}
					else if (mi == multiInstance.AllInvestor) {
						if (click(driver, getAllInvestorButton(60), "all investors button on mange version duplicate doc window", action.BOOLEAN)) {
							appLog.info("clicked on all investors");
						}
						else {
							appLog.error("all investor button is not clickable on manage version window");
						}
					}
			
				}
				ThreadSleep(3000);
			if (sendKeys(driver, getChooseFileButton(60), newFilePath, "Choose Button", action.BOOLEAN)) {
				if (click(driver, getUpdateLinkContentGrid(60), "Update button", action.BOOLEAN)) {
					String msg=null;
					if (isAlertPresent(driver)) {
						 msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
						switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
						if (msg.equalsIgnoreCase(updateDocumentSuccessAlertMsg)) {
							appLog.info("Update confirmation message is verified.");
							driver.switchTo().window(parentID);
							
						} else {
							driver.switchTo().window(parentID);
							appLog.error("Confirmation message is not verified. Expected: "+updateDocumentSuccessAlertMsg+" /tActual: "+msg);
							BaseLib.sa.assertTrue(false, "Confirmation message is not verified. Expected: "+updateDocumentSuccessAlertMsg+" /t Actual: "+msg);
						}
					} else {
						appLog.error("Confirmation alert message is not present on this window. Expected: "+updateDocumentSuccessAlertMsg+" /tActual: "+msg);
						BaseLib.sa.assertTrue(false, "Confirmation message is not present on this window. Expected: "+updateDocumentSuccessAlertMsg+" /tActual: "+msg);
						driver.close();
						driver.switchTo().window(parentID);
					}
				}
				else {
					appLog.error("update link on update window is not clickable");
					BaseLib.sa.assertTrue(false,"update link on update window is not clickable" );
				}
			}
			else {
				appLog.error("choose file button on update window is not visible");
				BaseLib.sa.assertTrue(false,"choose file button on update window is not visible" );
			}
			
		}
		else {
			appLog.error("update button on manage version window is not clickable");
			BaseLib.sa.assertTrue(false, "update button on manage version window is not clickable");
		}
		
		//file has been updated, now check updated document name present in manage version window
		switchToFrame(driver, 30, getFrame(PageName.FundsPage, 30));
		click(driver, getManageVersionRefresh(60), "manage version refresh button", action.SCROLLANDBOOLEAN);
		ThreadSleep(10000);
		scrollDownThroughWebelement(driver,getDocumentNameColumnManageVersion().get(0) , "first row in manage version popup");
		ThreadSleep(10000);
		if (getDocumentNameColumnManageVersion().get(0).getText().trim().equals(newFileName)) {
			appLog.info("updated file name "+newFileName+"is successfully present on manage version widnow");
		}
		else {
			appLog.error("updated file name "+newFileName+"is not present on manage version window");
			BaseLib.sa.assertTrue(false,"updated file name "+newFileName+"is not present on manage version window");
			flag = false;
		}
		//checking new first row document
		WebElement file = FindElement(driver, "//span[@id='grid12_DealDetail-box']//*[contains(text(),'Make Current')]/../..//*[text()='"+newFileName+"']", "updated file name on first row of manage version", action.BOOLEAN, 30);
		if (file.getTagName().equals("a")) {
			appLog.info("the first row of document name contains link of new file name");
		}
		else {
			appLog.error("the first row of document name does not contain link of new file name. tag name is "+getDocumentNameColumnManageVersion().get(0).getTagName());
			BaseLib.sa.assertTrue(false,"the first row of document name does not contain link of new file name");
			flag = false;
		}
		if (getCurrentColumnManageVersion().get(0).getText().trim().equals("Yes")) {
			appLog.info("first row for new file"+newFileName+" shows current as yes");
		}
		else {
			appLog.error("first row for new file"+newFileName+" shows current as no");
			BaseLib.sa.assertTrue(false,"first row for new file"+newFileName+" shows current as no");
			flag = false;
		}
	}
	
	}
	else {
		appLog.error("the list of manage version window is empty");
		flag = false;
	}
	return flag;
	
}

	/**
	 * @author Parul Singh
	 * @param fieldValue
	 * @param forFilterNumber
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean setFieldValueOnManageInvestor(String fieldValue, int forFilterNumber,Workspace workspace,int timeOut) {
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		String xpath = "//select[@id='a" + forFilterNumber + "aaMIN"+workspaceSelector+"']";
		WebElement ele = FindElement(driver, xpath, "Field value Drop Down", action.BOOLEAN, timeOut);
		scrollDownThroughWebelement(driver, ele, "Field value drop down");
		if (ele != null) {
		if (selectVisibleTextFromDropDown(driver, ele, "Field Value Drop Down", fieldValue)) {
			appLog.info("Field value " + fieldValue + " is present in the drop down");
			return true;
		} else {
			appLog.info("Field value " + fieldValue + " is not present in the drop down");
			return false;
		}
		} else {
		appLog.info("Field Drop Down number " + forFilterNumber + " is not present");
		return false;
		}
	}
	
	/**
	 * @author Parul Singh
	 * @param operator
	 * @param forFilterNumber
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean setOperatorValueOnManageInvestor(String operator, int forFilterNumber,Workspace workspace,int timeOut) {
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}		
		String xpath = "//select[@id='optMIN"+workspaceSelector+""+forFilterNumber+"']";

		WebElement ele = FindElement(driver, xpath, "Operator Drop Down", action.BOOLEAN, timeOut);
		scrollDownThroughWebelement(driver, ele, "Operator Drop Down");
		if (ele != null) {
			if (selectVisibleTextFromDropDown(driver, ele, "Field Value Drop Down", operator)) {
				appLog.info("Operator value " + operator + " is present in the drop down");
				return true;
			} else {
				appLog.info("Operator value " + operator + " is not present in the drop down");
				return false;
			}
		} else {
			appLog.info("Operator Drop Down number " + forFilterNumber + " is not present");
			return false;
		}
	}

	/**
	 * @author Parul Singh
	 * @param valueType
	 * @param forFilterNumber
	 * @param value
	 * @param fieldvalue
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean setCriterionValueOnManageTarget(String valueType, int forFilterNumber, String value, String fieldvalue,Workspace workspace,int timeOut) {
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}		
		String xpath = "//input[@id='criteriatextboxMIN"+workspaceSelector+""+forFilterNumber+"']";
		String xpathLookUp = "//img[@id='lookupdivMIN"+workspaceSelector+""+forFilterNumber+"']";
		String xpathcalendar = "//img[@id='dtedivMIN"+workspaceSelector+""+forFilterNumber+"']";
		String[] valueTypes = valueType.split("-");
		WebElement ele = FindElement(driver, xpath, "Criterion Box", action.BOOLEAN, timeOut);
		if (ele != null) {
			if (valueTypes[0].equalsIgnoreCase("TextBox") || value == null) {
				if (value == null) {
					value = "";
				}
				ele.clear();
				sendKeys(driver, ele, value, "Criterion Box", action.SCROLLANDTHROWEXCEPTION);
				return true;
			} else if (valueTypes[0].equalsIgnoreCase("lookup")) {
				ele.clear();
				WebElement eleLookUp = FindElement(driver, xpathLookUp, "Look Up icon", action.BOOLEAN, timeOut);
				scrollDownThroughWebelement(driver, eleLookUp, "LookUp Icon");
				if (eleLookUp != null) {
					click(driver, eleLookUp, "LookUp Icon", action.SCROLLANDTHROWEXCEPTION);
					
					String parentWinID = switchOnWindow(driver);
					if (valueType.equalsIgnoreCase("lookup-radio")) {
						if (!click(driver,
								FindElement(driver,
										"//label[text()='" + value+ "']/../preceding-sibling::td//input[contains(@onclick,'selectOne')]",
										"Radio Button of: " + value, action.BOOLEAN, timeOut),
								"Radio Button of: " + value, action.SCROLLANDBOOLEAN)) {
							appLog.info(value + " is not present in the lookup window.");
							driver.close();
							driver.switchTo().window(parentWinID);
							switchToFrame(driver, timeOut,new BasePageBusinessLayer(driver).getFrame(PageName.FundsPage, timeOut));
							return false;
						}
					} else {
						String val[] = value.split(",");
						for (int i = 0; i < val.length; i++) {
							if (!click(driver,
									FindElement(driver,
											"//label[text()='" + val[i]
													+ "']/../preceding-sibling::td//input[contains(@onclick,'checkone')]",
											"CheckBox of: " + value, action.BOOLEAN, 60),
									"CheckBox of: " + value, action.SCROLLANDBOOLEAN)) {
								appLog.info(value + " is not present in the lookup window.");
								driver.close();
								driver.switchTo().window(parentWinID);
								switchToFrame(driver, timeOut,new BasePageBusinessLayer(driver).getFrame(PageName.FundsPage, timeOut));
								return false;
							}
						}
					}
					if (!click(driver, getInsertSelectedButton(timeOut), "Insert Selected", action.SCROLLANDBOOLEAN)) {
						appLog.info("Successfully selected the value: " + value);
						driver.switchTo().window(parentWinID);
						switchToFrame(driver, timeOut,new BasePageBusinessLayer(driver).getFrame(PageName.FundsPage, timeOut));
						scrollDownThroughWebelement(driver, getWorkspaceSectionView(workspace, timeOut),
								workspace+"View.");
						return true;
					}
					System.out.println("going to close the lookup window.");
					driver.close();
					driver.switchTo().window(parentWinID);
					switchToFrame(driver, timeOut,new BasePageBusinessLayer(driver).getFrame(PageName.FundsPage, timeOut));
					appLog.info("Not able to select value " + value + " from the lookup window");
					return false;
				} else {
					appLog.info("Lookup is not displaying for field value: " + fieldvalue);
					return false;
				}
			} else if (valueType.equalsIgnoreCase("Date")) {
				ele.clear();
				if (!click(driver, FindElement(driver, xpathcalendar, "calendar Icon", action.BOOLEAN, timeOut),
						"Calendar icon", action.SCROLLANDBOOLEAN)) {
					appLog.info("Calendar icon is not present for the current field value.");
					return false;
				}
				String[] val = value.split("/");
				if (selectYear(val[2])) {
					if (selectMonth(val[1])) {
						if (selectDate(val[0])) {
							appLog.info("Date successfully selected.");
							return true;
						} else {
							appLog.info("Invalid date is provided. " + val[0]);
							return false;
						}
					} else {
						appLog.info("Invalid Month format: " + val[1]);
						return false;
					}
				} else {
					appLog.info("Invalid Year format: " + val[2]);
					return false;
				}
			}
		}else {
			appLog.info("Criterion box number " + forFilterNumber + " is not present");
			return false;
		}
		return false;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean closeWorkSpace(Workspace workspace,int timeOut) {
		WebElement ele=null;
		if(click(driver, getWorkSpaceCloseBtn(workspace, timeOut), "workspace close button", action.SCROLLANDBOOLEAN)) {
			String parentID=switchOnWindow(driver);
			if(parentID!=null) {
				ele= BaseLib.edriver.findElement(By.cssSelector("#LinkButton3"));
				try{
					scrollDownThroughWebelement(driver, ele, "Yes Button");
					ele.click();
					appLog.info("clicked on Update All Button");
				}catch(Exception e){
					appLog.error("Not able to click on Yes button so cannot update files ");
					BaseLib.sa.assertTrue(false, "Not able to click on Yes button so cannot update files ");
					appLog.error(e.getMessage().toString());
					driver.close();
					driver.switchTo().window(parentID);
					return false;
				}

				if (isAlertPresent(driver)) {
					appLog.info(workspace.toString()+" has been successfully close");
					String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
					switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
					if(msg.contains(closeWorkspaceMsg)) {
						appLog.info(closeWorkspaceMsg);
						driver.switchTo().window(parentID);
						return true;
					}else {
						appLog.error("Close Workspace Error Message is not matched. Expected: "+closeWorkspaceMsg);
					}
					driver.switchTo().window(parentID);
				} else {
					driver.close();
					appLog.error(workspace.toString()+" close Workspace alert message is not displayed");
					driver.switchTo().window(parentID);
				}

			}else {
				appLog.error("No new window is open so cannot close workspace");
			}
		}else {
			appLog.error("Not able to click on workspace close button so cannot close workspace");
		}
		return false;
	}
	
	/**
	 * @author Parul Singh
	 * @param year
	 * @return true/false
	 */
	public boolean selectYear(String year) {
		click(driver, getYear(60), "year", action.THROWEXCEPTION);
		WebElement ele = FindElement(driver, "//td[text()='" + year + "']", "Year", action.BOOLEAN, 20);
		if (ele == null) {
			for(int i=0;i<3;i++){
			click(driver, getYearBack(20), "Previous 15 Year", action.THROWEXCEPTION);
			}
			ele = FindElement(driver, "//td[text()='" + year + "']", "Year", action.BOOLEAN, 20);
			if (ele == null) {
				appLog.info("Wrong year format");
				return false;
			}
		}
		click(driver, ele, "Year", action.BOOLEAN);
		return true;
	}

	/**
	 * @author Parul Singh
	 * @param month
	 * @return true/false
	 */
	public boolean selectMonth(String month) {
		click(driver, getSelectMonth(50), "month", action.THROWEXCEPTION);
		WebElement ele = FindElement(driver, "//td[text()='" + month + "']", "Month", action.THROWEXCEPTION, 20);
		return click(driver, ele, "Month", action.BOOLEAN);
	}

	/**
	 * @author Parul Singh
	 * @param date
	 * @return true/false
	 */
	public boolean selectDate(String date) {
		WebElement ele = FindElement(driver, "//td[text()='" + date + "']", "Date", action.BOOLEAN, 20);
		return click(driver, ele, "date", action.BOOLEAN);
	}
	
	/**
	 * @author Parul Singh
	 * @param expectedResult
	 * @param workspace
	 * @param timeOut
	 * @return List<String>
	 */
	public List<String> checkResultOnManageInvestor(String expectedResult,Workspace workspace,int timeOut) {
		List<String> notFound = new ArrayList<String>();
		String[] result = expectedResult.split("<:break:>");
		if(workspace.toString().contains(Workspace.FundraisingWorkspace.toString())){
		for (int i = 0; i < result.length; i++) {
			WebElement ele=null;
			if(expectedResult.toLowerCase().contains("no data to display")){
				ele = FindElement(driver, "//span[text()='" + result[i] + "']", "Investor: " + result[i],
						action.BOOLEAN, timeOut);
			} else {
				ele = FindElement(driver, "//div[text()='" + result[i] + "']", "Investor: " + result[i],
						action.BOOLEAN, timeOut);
			}
			if (ele != null) {
				if(expectedResult.toLowerCase().contains("no data to display")){
					appLog.info(result[i] + " is matched.");
				} else if (click(driver, ele, "Investor: "+result[i], action.SCROLLANDBOOLEAN)) {
					appLog.info(result[i] + " is matched.");
				} else {
					notFound.add(result[i]);
				}
			} else {
				notFound.add(result[i]);
			}
		}
		}
		if(workspace.toString().contains(Workspace.InvestorWorkspace.toString())){
			WebElement ele1=null;
			System.out.println(result[0]);
			for (int i = 0; i < result.length; i++) {
				String[] institutionName=null;
				institutionName=result[i].split("/");
				WebElement ele=null;
				if(expectedResult.equalsIgnoreCase("All Investors")){
					ele = FindElement(driver, "//span[contains(text(),'"+institutionName[i]+"')]", "Investor: " + institutionName[i],
							action.BOOLEAN, timeOut);
					ele1=isDisplayed(driver, FindElement(driver, "//span[contains(text(),'All Investor')]/../..//img[@class='x-tree-ec-icon x-tree-elbow-end']", "all investor icon", action.BOOLEAN, timeOut), "visibility", timeOut, "");
				} else {
					ele = FindElement(driver, "//label[text()='" + institutionName[0] + "']", "Investor: " + institutionName[0],
							action.BOOLEAN, timeOut);
					ThreadSleep(2000);
				}
				if (ele != null) {
					if(expectedResult.equalsIgnoreCase("All Investors")){
						if(ele1!=null){
							appLog.info(result[i] + " is matched.");
						}else {
							notFound.add(result[i]+" is not matched. Institution and LP is visible");
						}
					} else if (click(driver, ele, "Investor: "+institutionName[0], action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						ele = FindElement(driver, "//label[text()='"+institutionName[1]+"']", "Investor: " + institutionName[1],
								action.SCROLLANDBOOLEAN, timeOut);
						if(ele!=null){
						click(driver, ele, "Investor: "+institutionName[1], action.SCROLLANDBOOLEAN);
						appLog.info(result[i] + " is matched.");
						}else{
							appLog.info(result[i] + " is not matched.");
							notFound.add(result[i]);
						}
					} else {
						notFound.add(result[i]);
					}
				} else {
					notFound.add(result[i]);
				}
			}	
			
			
		}
		return notFound;
	}
	
	/**
	 * @author Parul Singh
	 * @param path
	 * @param rowNum
	 * @param fieldValue
	 * @param Workspace
	 * @param sheetName
	 * @return List<String>
	 */
	public List<String> operatorCheck(String path,int rowNum, String fieldValue,Workspace Workspace,String sheetName ) {
		List<WebElement> operatorDropDownValues = allOptionsInDropDrop(driver, getManageInvestorFilterOperatorDropdown(Workspace).get(0),
				"Operator Drop Down");
		List<String> notFoundOperators = new ArrayList<String>();
		String operatorValues = ExcelUtils.readData(path,sheetName, rowNum, 5);
		String[] opVal = operatorValues.split(",");
		for (int j = 0; j < opVal.length; j++) {
			for (int k = 0; k < operatorDropDownValues.size(); k++) {
				if (opVal[j].equalsIgnoreCase(operatorDropDownValues.get(k).getText())) {
					appLog.info("Successfully Found The Operator: " + opVal[j]);
					break;
				} else if (k == operatorDropDownValues.size() - 1) {
					appLog.info(opVal[j] + " :Operator not found in the drop down for field value: " + fieldValue);
					notFoundOperators.add(opVal[j]);
					ExcelUtils.writeDataInExcel(
							"Not Found operator: " + opVal[j]
									+ " :Operator not found in the drop down for field value: " + fieldValue,
							"Filter Logic for single row", rowNum, 7);
				}
			}
		}
		return notFoundOperators;
	}
	
	/**
	 * @author Parul Singh
	 * @param path
	 * @param workspace
	 * @param sheetName
	 * @param timeOut
	 * @return softassert
	 */
	public SoftAssert checkFilterSingle(String path,Workspace workspace,String sheetName,int timeOut) {
		SoftAssert sa = new SoftAssert();
		String operation;
		String fieldValue;
		String operatorValue;
		String operator;
		String valueType;
		String expectedResult="";
		for (int i = 1; i > 0; i++) {
			operation = ExcelUtils.readData(path,sheetName, i, 6);
			if (operation == null) {
				appLog.info("Done with the filters");
				break;
			}
			operation.trim();
			fieldValue = ExcelUtils.readData(path,sheetName, i, 0);
			if (fieldValue == null) {
				appLog.info("No Field value is provided in row number " + (i + 1)
						+ ". So Skipping to the next field filter check.");
				ExcelUtils.writeDataInExcel("Not Checked: No Field value is provided in row number " + (i + 1)
						+ ". So Skipping to the next field filter check.", "Filter Logic for single row", i, 7);
				continue;
			}
			valueType = ExcelUtils.readData(path,sheetName, i, 2);
			if (setFieldValueOnManageInvestor(fieldValue, 1, workspace,timeOut)) {
				if (operation.equalsIgnoreCase("Operator")) {
					List<String> list = operatorCheck(path,i, fieldValue,workspace,sheetName);
					if (!list.isEmpty()) {
						for (int j = 0; j < list.size(); j++) {
							sa.assertTrue(false, list.get(j) + " :Operator not found in the drop down for field value: "
									+ fieldValue);
						}
					} else {
						appLog.info("Successfully verified operators.");
					}
				} else if (operation.equalsIgnoreCase("Result")) {
					operator = ExcelUtils.readData(path,sheetName, i, 1);
					operatorValue = ExcelUtils.readData(path,sheetName, i, 3);
					if (setOperatorValueOnManageInvestor(operator, 1,workspace,timeOut)) {
						if (setCriterionValueOnManageTarget(valueType, 1, operatorValue, fieldValue,workspace,timeOut)) {
							click(driver, getManageInvestorFilterApplyButton(workspace, timeOut), "Apply Button", action.THROWEXCEPTION);
							expectedResult = ExcelUtils.readData(path,sheetName, i, 4);
							List<String> list = new ArrayList<String>();
							list.addAll(checkResultOnManageInvestor(expectedResult,workspace,timeOut));
							System.out.println(
									"\n\n\nis list empty: " + list.isEmpty() + "\n\n\nList Size: " + list.size());
							if (!list.isEmpty()) {
								System.out.println("Inside the condition\n\n\n");
								for (int j = 0; j < list.size(); j++) {
									System.out.println("Inside the loop.");
									sa.assertTrue(false, list.get(j) + " :Target not found for field value "
											+ fieldValue + " operator " + operator + " and value " + operatorValue);
								}
							}
						}
					} else {
						appLog.info("Not able to set operator value. So Skipping to the next filter.");
						continue;
					}
				} else if (operation.equalsIgnoreCase("both")) {
					List<String> list = operatorCheck(path,i, fieldValue,workspace,sheetName);
					if (!list.isEmpty()) {
						for (int j = 0; j < list.size(); j++) {
							sa.assertTrue(false, list.get(j) + " :Operator not found in the drop down for field value: "
									+ fieldValue);
						}
					} else {
						appLog.info("Successfully verified operators.");
					}
					operator = ExcelUtils.readData(path,sheetName, i, 1);
					operatorValue = ExcelUtils.readData(path,sheetName, i, 3);
					if (setOperatorValueOnManageInvestor(operator, 1,workspace,timeOut)) {
						if (setCriterionValueOnManageTarget(valueType, 1, operatorValue, fieldValue,workspace,timeOut)) {
							click(driver, getManageInvestorFilterApplyButton(workspace, timeOut), "Apply Button", action.THROWEXCEPTION);
							expectedResult = ExcelUtils.readData(path,sheetName, i, 4);
							list.clear();
							list.addAll(checkResultOnManageInvestor(expectedResult,workspace,timeOut));
							System.out.println(
									"\n\n\nis list empty: " + list.isEmpty() + "\n\n\nList Size: " + list.size());
							if (!list.isEmpty()) {
								System.out.println("Inside the condition\n\n\n");
								for (int j = 0; j < list.size(); j++) {
									System.out.println("Inside the loop.");
									sa.assertTrue(false, list.get(j) + " :Target not found for field value "
											+ fieldValue + " operator " + operator + " and value " + operatorValue);
								}
							}
						}
					} else {
						appLog.info("Not able to set operator value. So Skipping to the next filter.");
						continue;
					}
				}
			} else {
				appLog.info("Field Value:" + fieldValue + " is not available in drop down. So skipping this filter");

				continue;
			}
		}
		return sa;
	}

	/**
	 * @author Parul Singh
	 * @param path
	 * @param sheetName
	 * @param workspace
	 * @param timeOut
	 * @return softassert
	 */
	public SoftAssert applyCriterionOnManageInvestor(String path,String sheetName,Workspace workspace,int timeOut) {
		// System.out.println("\n\n\napply criterion is starting\n\n\n\n");
		int z = 1;
		SoftAssert sa = new SoftAssert();
		String fieldValue = null;
		String operator = null;
		String valueType = null;
		String value = null;
		int l = 1;
		for (int k = 1; k > 0; k++) {
			// System.out.println("reading value from the excel");
			String val = ExcelUtils.readData(path,sheetName, k, 0);
			if (val == "") {
				continue;
			}
			if (val.equalsIgnoreCase("end")) {
				break;
			}
			int itr = Integer.parseInt(val);
			for (int i = 1; i <= itr; i++) {
				// System.out.println("\n\n\nz: "+z+"\t\ti: "+i+"\t\tk: "+k);
				fieldValue = ExcelUtils.readData(path,sheetName, l, 1);
				operator = ExcelUtils.readData(path,sheetName, l, 2);
				valueType = ExcelUtils.readData(path,sheetName, l, 3);
				value = ExcelUtils.readData(path,sheetName, l, 4);
				if (setFieldValueOnManageInvestor(fieldValue, i,workspace,timeOut)) {
					if (setOperatorValueOnManageInvestor(operator, i,workspace,timeOut)) {
						if (setCriterionValueOnManageTarget(valueType, i, value, fieldValue,workspace,timeOut)) {
							appLog.info("Successfully applied criteria.");
							l++;
							if (i == itr) {
								break;
							}
							click(driver,getManageInvestorFilterAddRowLink(workspace, timeOut), "Add Row Link", action.SCROLLANDTHROWEXCEPTION);
						} else {
							appLog.info(
									"Cannot continue with the set of criteria given kindly see previous line of the log.");
							break;
						}
					} else {
						appLog.info(
								"Cannot continue with the set of criteria given beacuase operator value is not present in the drop down.");
						break;
					}
				} else {
					appLog.info(
							"Cannot continue with the set of criteria given because field value is not present in the drop down.");
					break;
				}
			}
			click(driver, getManageInvestorFilterApplyButton(workspace, timeOut), "Apply Button", action.SCROLLANDTHROWEXCEPTION);
			ThreadSleep(5000);
			String expectedResult = ExcelUtils.readData(path,sheetName, l - itr, 5);
			List<String> list = checkResultOnManageInvestor(expectedResult,workspace,timeOut);
			// System.out.println("\n\n\nis list empty:
			// "+list.isEmpty()+"\n\n\nList Size: "+list.size());
			StringBuilder str = new StringBuilder();
			if (!list.isEmpty()) {
				// System.out.println("Inside the condition\n\n\n");
				for (int j = 0; j < list.size(); j++) {
					// System.out.println("Inside the loop.");
					sa.assertTrue(false, list.get(j) + " :Investor not found.");
					str.append(list.get(j) + ", ");
				}
				ExcelUtils.writeDataInExcel(path,"Not found Investors: " + str, sheetName, l - itr, 6);
			}
			click(driver, getManageInvestorFilterClearButton(workspace, timeOut), "clear Button", action.THROWEXCEPTION);
			List<WebElement> lst=getManageInvestorFilterRemoveRowIcon(workspace);
			System.err.println(lst.size());
			if(!lst.isEmpty()){
				for (int i=0;i<lst.size();i++){
					scrollDownThroughWebelement(driver, lst.get(i), "");
					if(click(driver, lst.get(i), "Remove row", action.SCROLLANDBOOLEAN)){
						appLog.info("clicked on Remove Icon");
					}else {
						appLog.error("Not able click on Remove Icon");
						sa.assertTrue(false, "Not able click on Remove Icon");
					}
				}			
			}else {
				appLog.error("No Remove Icon is visible");
			}
		}
		return sa;
	}
	
	public boolean clearWorkSpace(Workspace workspace,int timeOut) {
		if(click(driver, getWorkSpaceClearBtn(workspace, timeOut), "workspace clear button", action.SCROLLANDBOOLEAN)) {
			String parentID=switchOnWindow(driver);
			if(parentID!=null) {

				ThreadSleep(5000);
				if(clickUsingCssSelectorPath("div#subFolderDiv > div >a[title='Yes']", "workspace clear Yes button")) {
//				if(click(driver, getCloseAndClearWorkSpaceYesBtn(timeOut), "workspace clear pop up Yes button ", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(8000);
//					if (isAlertPresent(driver)) {
						appLog.info(workspace.toString()+" has been successfully clear");
						String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
						switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
						if(msg.contains(clearWorkSpaceMsg)) {
							appLog.info(clearWorkSpaceMsg);
							driver.switchTo().window(parentID);
							return true;
						}else {
							appLog.error("clear Workspace Error Message is not matched. Expected: "+closeWorkspaceMsg);
						}
						driver.switchTo().window(parentID);
//					} else {
//						driver.close();
//						appLog.error(workspace.toString()+" clear Workspace alert message is not displayed");
//						driver.switchTo().window(parentID);
//					}
				}else {
					driver.close();
					appLog.error("Not able to click on clear workspace Yes button so cannot clear workspace");
					driver.switchTo().window(parentID);
				}
			}else {
				appLog.error("No new window is open so cannot clear workspace");
			}
		}else {
			appLog.error("Not able to click on workspace clear button so cannot clear workspace");
		}
		return false;
	}
	/**
	 * @author Akul Bhutani
	 * @param fileName
	 * @param firmName
	 * @param userFullName
	 * @param firm
	 * @param uploadedBy
	 * @param userOrContact
	 * @return true/false
	 */
	public boolean verifyUploadedByNameAndFirmName(String fileName, String firmName, String userFullName,boolean firm, boolean uploadedBy,String userOrContact) {
		boolean flag=true;
		if (firm) {
			if (getFirmNameFromFileNameContentGrid(fileName,userOrContact).getAttribute("title").trim().equals(firmName)) {
				appLog.info("firm name for "+fileName+" file in content grid is successfully verified "+firmName);
			}
			else {
				flag = false;
				appLog.error("firm name is wrong for "+fileName+". It is "+getFirmNameFromFileNameContentGrid(fileName,userOrContact).getAttribute("title")+" and expected is "+firmName);
				BaseLib.sa.assertTrue(false, "firm name is wrong for "+fileName+". It is "+getFirmNameFromFileNameContentGrid(fileName,userOrContact).getAttribute("title"));
			}
		}
		if (uploadedBy) {
			if (getUploadedByFromFileNameContentGrid(fileName,userOrContact).getAttribute("title").trim().equals(userFullName)) {
				appLog.info("user name is successfully verified in front of file "+fileName);
			}
			else {
				flag = false;
				appLog.error("user name is wrong in front of "+fileName+". It is "+getUploadedByFromFileNameContentGrid(fileName,userOrContact).getAttribute("title")+" and expected is "+userFullName);
				BaseLib.sa.assertTrue(false, "user name is wrong in front of "+fileName+". It is "+getUploadedByFromFileNameContentGrid(fileName,userOrContact).getAttribute("title"));
			}
		}
		return flag;
		
	}

	/**
	 * @author Akul Bhutani
	 * @param fileName
	 * @param userOrContact
	 * @return WebElement uploaded by name
	 */
	public WebElement getUploadedByFromFileNameContentGrid(String fileName,String userOrContact) {
		String fileXpath = "//u[text()='"+fileName+"']/../../../..";
		WebElement ele=null;
		if (userOrContact.equalsIgnoreCase("User")) {
		ele= FindElement(driver, fileXpath+"//span[contains(@id,'cell-3-0')]/span", "user name w.r.t file name", action.SCROLLANDBOOLEAN, 30);
		}
		else if (userOrContact.equalsIgnoreCase("Contact")) {
			ele= FindElement(driver, fileXpath+"//span[contains(@id,'cell-3')]/a", "contact name w.r.t file name", action.SCROLLANDBOOLEAN, 30);
		}
		return ele;
	}
	
	/**
	 * @author Akul Bhutani
	 * @param fileName
	 * @param userOrContact
	 * @return WebElement firm name
	 */
	public WebElement getFirmNameFromFileNameContentGrid(String fileName,String userOrContact) {
		String fileXpath = "//u[text()='"+fileName+"']/../../../..";
		WebElement ele=null;
		if (userOrContact.equalsIgnoreCase("User")) {
		ele= FindElement(driver, fileXpath+"//span[contains(@id,'cell-4-0')]/span", "firm name w.r.t. file name", action.SCROLLANDBOOLEAN, 30);
		}
		else if (userOrContact.equalsIgnoreCase("Contact")) {
		ele = FindElement(driver, fileXpath+"//span[contains(@id,'cell-4')]/a", "firm name w.r.t. file name", action.SCROLLANDBOOLEAN, 30);
		}
		return ele;
		//return ele.getAttribute("title");
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param FolderType
	 * @param workspace
	 * @param PageName
	 * @param expectedErrorMsg
	 * @param value
	 * @param ErrorMessageType
	 * @param timeOut
	 * @return true/false
	 */
	public boolean verifyFolderErrorMessage(FolderType FolderType,Workspace workspace,PageName PageName,String expectedErrorMsg,String value,ErrorMessageType ErrorMessageType,int timeOut) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		WebElement ele=null;
		ele=bp.getFolderTypeRadioButton(FolderType,workspace,PageName, timeOut);
			if(ele!=null) {
				if(click(driver, ele, FolderType.toString()+" folder radio button icon", action.BOOLEAN)) {
					appLog.info("clicked on "+FolderType.toString()+" radio button");
					if(value!=null) {
						if(sendKeys(driver, bp.getParentFolderNameTextBox(workspace, PageName, timeOut), value, FolderType.toString()+"  folder text box", action.BOOLEAN)) {
							appLog.info("value pass in folder "+FolderType.toString()+" text box: "+value);
						}else {
							appLog.info("Not able to pass value in folder "+FolderType.toString()+" text box: "+value+" so cannot check error message");
							return false;
						}
					}
					if(click(driver, bp.getParentFolderSaveButton(workspace, PageName, 20), "folder save button", action.BOOLEAN)) {
						appLog.info("clicked on save button");
						WebElement ele1=bp.getParentFolderErrorMsg(workspace, PageName,ErrorMessageType,30);
						if(ele1!=null) {
							String aa=ele1.getText().trim().toLowerCase();
							if(aa.contains(expectedErrorMsg.toLowerCase())) {
								appLog.info("Folder error messgae is verified for folder type :"+FolderType.toString());
								return true;
							}else {
								appLog.error("Folder error message is not verified for folder type: "+FolderType.toString()+" Expected: "+expectedErrorMsg+"/t Actual: "+aa);
							}
						}else {
							appLog.error("Error message is not visible in folder type "+FolderType.toString()+" so cannot check Error Message. Expected: "+expectedErrorMsg);
						}
					}else {
						appLog.error("Not able to click on save button so cannot check error message for folder type: "+FolderType.toString());
					}
				}else {
					appLog.error("Not able to click on "+FolderType.toString()+" folder radio button so cannot check error message");
				}
			}else {
				appLog.error("folder radio button is not visible so cannot check error message");
			}
		return false;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param FolderType
	 * @param workspace
	 * @param PageName
	 * @param expectedErrorMsg
	 * @param value
	 * @param ErrorMessageType
	 * @param timeOut
	 * @return true/false
	 */
	public boolean verifyFolderErrorMessageSubLevel(FolderType FolderType,Workspace workspace,PageName PageName,String expectedErrorMsg,String value,ErrorMessageType ErrorMessageType,int timeOut) {
		if(value!=null) {
			if(sendKeys(driver, getChildFolderNameTextBox(workspace, PageName, timeOut), value, FolderType.toString()+"  sub folder text box", action.BOOLEAN)) {
				appLog.info("value pass in sub folder "+FolderType.toString()+" text box: "+value);
			}else {
				appLog.info("Not able to pass value in sub folder "+FolderType.toString()+" text box: "+value+" so cannot check error message");
				return false;
			}
		}
		if(click(driver,getChildFolderSaveButton(workspace, PageName, timeOut), "child folder save button", action.BOOLEAN)) {
			appLog.info("clicked on child folder save button");
			WebElement ele1=getSubFolderErrorMsg(workspace, PageName, ErrorMessageType, timeOut);
			if(ele1!=null) {
				String aa=ele1.getText().trim();
				if(aa.contains(expectedErrorMsg)) {
					appLog.info("Folder error messgae is verified for sub folder type :"+FolderType.toString());
					return true;
				}else {
					appLog.error("Folder error message is not verified for sub folder type: "+FolderType.toString()+" Expected: "+expectedErrorMsg+"/t Actual: "+aa);
					BaseLib.sa.assertTrue(false,"Folder error message is not verified for sub folder type: "+FolderType.toString()+" Expected: "+expectedErrorMsg+"/t Actual: "+aa);
				}
			}else {
				appLog.error("Error message is not visible in  sub folder type "+FolderType.toString()+" so cannot check Error Message. Expected: "+expectedErrorMsg);
			}
		}else {
			appLog.error("Not able to click on save button so cannot check error message for sub folder type: "+FolderType.toString());
		}
			return false;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param FolderType
	 * @param workspace
	 * @param PageName
	 * @param expectedErrorMsg
	 * @param value
	 * @param ErrorMessageType
	 * @param timeOut
	 * @return true/false
	 */
	public boolean verifyRenameFolderErrorMessageParentLevel(FolderType FolderType,Workspace workspace,PageName PageName,String expectedErrorMsg,String value,ErrorMessageType ErrorMessageType,int timeOut) {
		if(value!=null) {
			if(sendKeys(driver, getParentRenameFolderNameTextBox(workspace, PageName, timeOut), value, FolderType.toString()+"  Parent Rename folder text box", action.BOOLEAN)) {
				appLog.info("value pass in sub folder "+FolderType.toString()+" text box: "+value);
			}else {
				appLog.info("Not able to pass value in sub folder "+FolderType.toString()+" text box: "+value+" so cannot check error message");
				return false;
			}
		}
		if(click(driver,getParentRenameFolderSaveButton(workspace, PageName, timeOut), "Parent Rename folder save button", action.BOOLEAN)) {
			appLog.info("clicked on parent Rename folder save button");
			WebElement ele1=getParentRenameFolderErrorMsg(workspace, PageName, ErrorMessageType, timeOut);
			if(ele1!=null) {
				String aa=ele1.getText().trim();
				if(aa.contains(expectedErrorMsg)) {
					appLog.info("Rename Folder error messgae is verified for Parent folder type :"+FolderType.toString());
					return true;
				}else {
					appLog.error("Rename Folder error message is not verified for Parent folder type: "+FolderType.toString()+" Expected: "+expectedErrorMsg+"/t Actual: "+aa);
				}
			}else {
				appLog.error("Error message is not visible in  Rename parent folder type "+FolderType.toString()+" so cannot check Error Message. Expected: "+expectedErrorMsg);
			}
		}else {
			appLog.error("Not able to click on save button so cannot check error message for Rename Parent folder type: "+FolderType.toString());
		}
			return false;
	}
	
	/**
	 * @author Parul Singh
	 * @return softassert
	 */
	public SoftAssert verifyManageEmailUI() {
		SoftAssert saa = new SoftAssert();
		List<String> ListofText = new ArrayList<String>();
		String manageEmailsheader = getManageEmailsHeader(60).getText().trim();
		if (manageEmailsheader.equalsIgnoreCase("Manage Emails")) {
			appLog.info("Manage email popup is displaying");
			if (getManageEmailContactAccessViewDropDownList(60) != null) {
				appLog.info("Contact access view dropdown is displaying");
			} else {
				appLog.info("Contact access view dropodwn is not displaying");
				saa.assertTrue(false, "Contact access view dropdown is not displaying");
			}
			String contactaccessDropdownOption = getSelectedOptionOfDropDown(driver,
					getManageEmailContactAccessViewDropDownList(60), "Contact access view dropodown", "text");
			if (contactaccessDropdownOption.equalsIgnoreCase("All Folders")) {
				appLog.info("All folder option is displaying in the contact access view dropdown ");
			} else {
				appLog.info("All folder option is not displaying in contact access view dropdown");
				saa.assertTrue(false, "All folder option is not displaying in contact access view dropdown");
			}
			if (getManageEmailSearchTextBox(60) != null) {
				appLog.info("Search box is displaying");
			} else {
				appLog.info("Search box is not displaying");
				saa.assertTrue(false, "Search box is not displaying");
			}
			String contactSearchBoxText = getAttribute(driver, getManageEmailSearchTextBox(60), "Search textbox",
					"placeholder");
			if (contactSearchBoxText.equalsIgnoreCase("Search Contacts")) {
				appLog.info("Search contact text is displaying inside the text box");
			} else {
				appLog.info("Search contact text is not displaying inside the search box");
				saa.assertTrue(false, "Search contact text is not displaying inside the search box");
			}
			if (!isEnabled(driver, getManageEmailClearSearchIcon(60), "Clear search icon")) {
				appLog.info("Clear search icon is enabled");
				saa.assertTrue(false, "Clear search icon is enabled");
			} else {
				appLog.info("Clear search icon is not enabled");
			}
			if (isDisplayed(driver, getManageEmailSearchBtn(60), "Visibility", 60, "Search icon") != null) {
				appLog.info("Search icon is displaying");
			} else {
				appLog.info("Search icon is not displaying");
				saa.assertTrue(false, "Search icon is not displaying");
			}
			if (isDisplayed(driver, getGetManageEmailSelectAllCheckBox(60), "Visibility", 60,
					"Select All checkbox") != null) {
				appLog.info("Select All checkboxis displaying");
			} else {
				appLog.info("Select All checkbox is not displaying");
				saa.assertTrue(false, "Select All checkbox is not displaying");
			}
			List<WebElement> headerList = FindElements(driver,
					"//div[@id='manageemailgrid_ME']//span[contains(@class,'aw-item-text')]",
					"Manage Email Header");
			String expectedResultList = "Contact Name<break>Email<break>Firm<break>Last Invite Date";
			if (!headerList.isEmpty()) {
				for (int k = 1; k < headerList.size() - 5; k++) {
					ListofText.add(headerList.get(k).getText().trim());
				}
				if (!compareMultipleListWithoutAssertion(expectedResultList, ListofText)) {
					appLog.info("Manage Emails Contacts Header Text is not verified");
					saa.assertTrue(false, "Manage Emails Contacts Header Text is not verified");
				} else {
					appLog.info("Manage Emails Contacts Header Text is verified");
				}
			} else {
				appLog.info("Manage Emails Contacts Header Text is not available in Manage Emails");
				saa.assertTrue(false, "Manage Emails Contacts Header Text is not available in Manage Emails");
			}
			saa.assertTrue(
					getContactAccessManageEmailGridMsg(30).getText().trim()
							.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage),
					"Manage Emails Contacts Grid Error Message is not verified. Expected: "
							+ FundsPageErrorMessage.noDataToDisplayErrorMessage);
			saa.assertTrue(
					isSelected(driver, getManageEmailInvitationEmailTemplateRadioBtn(30),
							"Manage Emails Invitation Emails Radio Button"),
					"Manage Emails Invitation Emails Radio Button is not selected by default.");
			if (isSelected(driver, getManageEmailInvitationEmailTemplateRadioBtn(30),
					"Manage Emails Invitation Emails Radio Button")) {
				appLog.info("Manage Emails Invitation Emails radio button is selected by default");
			} else {
				appLog.info("Manage Emails Invitation Emails radio button is not selected by default");
				saa.assertTrue(false, "Manage Emails Invitation Emails radio button is not selected by default");
			}
			List<WebElement> headerText = getManageEmailInvitationEmailTemplateEditPreviewTextList();
			String result1 = "Edit<break>Preview";
			ListofText.clear();
			if (!headerText.isEmpty()) {
				for (int k = 0; k < headerText.size(); k++) {
					ListofText.add(headerText.get(k).getText().trim());
				}
				if (!compareMultipleListWithoutAssertion(result1, ListofText)) {
					appLog.info("Manage Emails Invitaion Email Template Link options are not verified");
					saa.assertTrue(false, "Manage Emails Invitaion Email Template Link options are not verified");
				} else {
					appLog.info("Manage Emails Invitaion Email Template Link options are  verified");
				}
			} else {
				appLog.info("Manage Emails Invitaion Email Template Link is not available");
				saa.assertTrue(false, "Manage Emails Invitaion Email Template Link is not available");
			}
			for (int i = 0; i < result1.split("<break>").length; i++) {
				if (isEnabled(driver, getManageEmailInvitationEmailTemplateEditPreviewTextList().get(i),
						"Invitation email option")) {
					appLog.info(result1.split("<break>")[i] + " Option is enabled");
				} else {
					appLog.info(result1.split("<break>")[i] + " Option is not enabled");
					saa.assertTrue(false,result1.split("<break>")[i] + " Option is not enabled");
				}
			}
			if (isSelected(driver, getManageEmailCustomRadioButton(30), "Manage Emails Custom Radio Button")) {
				appLog.info("Custom radio button is selected by default");
				saa.assertTrue(false, "Custom radio button is selected by default");
			} else {
				appLog.info("Custom radio button is not selected by default");
			}
			for (int i = 0; i < result1.split("<break>").length; i++) {
				if (!isEnabled(driver, getManageEmailCustomEmailTemplateEditPreviewTextList().get(i),
						"Custom email option")) {
					appLog.info(result1.split("<break>")[i] + " Option is enabled");
					saa.assertTrue(false, result1.split("<break>")[i] + " Option is enabled");
				} else {
					appLog.info(result1.split("<break>")[i]+ " Option is not enabled");
				}
			}
			if (getmanageEmailsendBtn(10) != null) {
				appLog.info("Send Button is available in Manage Emails Pop Up");
			} else {
				appLog.info("Send Button is not available in Manage Emails Pop Up");
				saa.assertTrue(false, "Send Button is not available in Manage Emails Pop Up");
			}
			if (getManageEmailCancelBtn(10) != null) {
				appLog.info("Cancel Button is available in Manage Emails Pop Up");
			} else {
				appLog.info("Cancel Button is not available in Manage Emails Pop Up");
				saa.assertTrue(false, "Cancel Button is not available in Manage Emails Pop Up");
			}
			if (getManageEmailsCloseIcon(10) != null) {
				appLog.info("Close Icon is available in Manage Emails Pop Up");
			} else {
				appLog.info("Close Icon is not available in Manage Emails Pop Up");
				saa.assertTrue(false, "Close Icon is not available in Manage Emails Pop Up");
			}
			WebElement sortingIcon = isDisplayed(driver,
					FindElement(driver, "//div[@id='manageemailgrid_ME']//span[text()='Last Invite Date']/span",
							"Manage Emails Sorting Icon", action.SCROLLANDBOOLEAN, 30),
					"visibility", 30, "Manage Emails Sorting Icon");
			if (sortingIcon != null) {
				appLog.info("Sorting Icon is displaying by default on last invite date column");
			} else {
				appLog.info("Sorting Icon is not displaying by default on last invite date column");
				saa.assertTrue(false, "Sorting Icon is not displaying by default on last invite date column");
			}
		}
		return saa;
	}

	/**
	 * @author Ankit Jaiswal
	 * @param folderType
	 * @param labelName
	 * @param investorName
	 * @param fundName
	 * @param emailAddress
	 * @param downloadDate
	 * @return
	 */
	public boolean verifyManageEmailGrid(String contactName,String contactEmailId,String firmName,String LastInviteDate){
		WebElement ele=null;
		if(LastInviteDate==null){
		ele=FindElement(driver, "//a[text()='"+contactName+"']/../..//a[text()='"+contactEmailId+"']/../..//a[text()='"+firmName+"']/..//following-sibling::span[@title=''][1]", "Grid Data", action.SCROLLANDBOOLEAN, 30);
		}else if(LastInviteDate!=null){
			ele=FindElement(driver, "//a[text()='"+contactName+"']/../following-sibling::span/a[text()='"+contactEmailId+"']/../following-sibling::span/a[text()='"+firmName+"']/../following-sibling::span[text()='"+getSystemDate("M/d/yyyy")+"' or text()='"+previousOrForwardDate(-1, "M/d/yyyy")+"' or text()='"+getSystemDate("MM/dd/yyyy")+"' or text()='"+previousOrForwardDate(-1, "MM/dd/yyyy")+"']", "Grid Data", action.SCROLLANDBOOLEAN, 30);
		}
		if(ele!=null){
			return true;
		} else {
			return false;
		}
	
		}

	/**
	 * @author Ankit Jaiswal
	 * @param folderType
	 * @param labelName
	 * @param investorName
	 * @param fundName
	 * @param emailAddress
	 * @param downloadDate
	 * @return
	 */
	public boolean checkWatermarking(FolderType folderType, String labelName,String firmName, String investorName, String fundName,
			String emailAddress, String downloadDate) {
		WebElement ele = null;
		switchToFrame(driver, 10, FindElement(driver, "//iframe", "iframe", action.BOOLEAN, 10));
		if ((labelName.equalsIgnoreCase("My Firm's Name") || labelName.equalsIgnoreCase("My Firm Name"))
				&& !folderType.toString().equalsIgnoreCase(FolderType.Internal.toString())) {
			ele = FindElement(driver, "//*[contains(text(),'"+firmName+"')]",
					"Watermarking Label", action.BOOLEAN, 0);
		} else if ((labelName.equalsIgnoreCase("Investor Name") && (folderType.toString().equalsIgnoreCase(FolderType.Standard.toString())))) {
			if(investorName.contains("<break>")) {
				String[] ss=investorName.split("<break>");
				ele = FindElement(driver, "//*[contains(text(),'" + ss[0] + "') or contains(text(),'" + ss[1] + "')]", "Watermarking Label",action.BOOLEAN, 0);
			}else {
				ele = FindElement(driver, "//*[contains(text(),'" + investorName + "')]", "Watermarking Label",action.BOOLEAN, 0);
			}
					
		} else if (labelName.equalsIgnoreCase("Fund Name") && !folderType.toString().equalsIgnoreCase(FolderType.Internal.toString())) {
			ele = FindElement(driver, "//*[contains(text(),'" + fundName + "')]", "Watermarking Label",
					action.BOOLEAN, 0);
		} else if (labelName.equalsIgnoreCase("Download Date") && !folderType.toString().equalsIgnoreCase(FolderType.Internal.toString())) {
			ele = FindElement(driver, "//*[contains(text(),'" + downloadDate + "') or contains(text(),'" +previousOrForwardDate(-1, "MM-dd-YYYY")+"') or contains(text(),'" +previousOrForwardDate(1, "MM-dd-YYYY")+"')]", "Watermarking Label",
					action.BOOLEAN, 0);
		} else if (labelName.equalsIgnoreCase("Email Address") && !folderType.toString().equalsIgnoreCase(FolderType.Internal.toString())) {
			ele = FindElement(driver, "//*[contains(text(),'" + emailAddress + "')]", "Watermarking Label",
					action.BOOLEAN, 0);
		} else if (labelName.equalsIgnoreCase("IP Address") && !folderType.toString().equalsIgnoreCase(FolderType.Internal.toString())) {
			ele = FindElement(driver, "//*[contains(text(),'" + getPublicIPAddress() + "')]", "Watermarking Label",
					action.BOOLEAN, 0);
		} else {
			ele = FindElement(driver, "//*[contains(text(),'" + labelName + "')]", "Watermarking Label",
					action.BOOLEAN, 0);
		}
		System.err.println("Inside method check watermarking: "+getSystemDate("hh:mm:ss"));
		scrollDownThroughWebelement(driver, ele, "watermaking label");
		switchToDefaultContent(driver);
		if (ele != null) {
			if (folderType.toString().equalsIgnoreCase(FolderType.Internal.toString()) || (labelName.equalsIgnoreCase("Investor Name")
					&& (!folderType.toString().equalsIgnoreCase(FolderType.Standard.toString())))) {
				return false;
			}
			return true;
		} else {
			if (folderType.toString().equalsIgnoreCase(FolderType.Internal.toString()) || (labelName.equalsIgnoreCase("Investor Name")
					&& (!folderType.toString().equalsIgnoreCase(FolderType.Standard.toString())))) {
				return true;
			}
			return false;
		}
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param fileName
	 * @param folderType
	 * @param Labels
	 * @param investorName
	 * @param fundName
	 * @param emailAddress
	 * @param downloadDate
	 * @param pageName
	 * @param target
	 * @param workspace
	 */
	public void verifyWatermarking2(String fileName, FolderType folderType, String Labels, String firmName, String investorName,
			String fundName, String emailAddress, String downloadDate, PageName pageName, boolean target,Workspace workspace) {
		WebElement ele = null;
		ele = FindElement(driver, "//a[@title='" + fileName + "']", fileName + " file Link", action.BOOLEAN, 10);
//		if (pageName.equalsIgnoreCase("contact page")) {
//			ele = FindElement(driver, "//div[@title='" + fileName + "']/a", fileName + " file Link", action.BOOLEAN, 10);
//		} else if (pageName.equalsIgnoreCase("Target Deal Page") || pageName.equalsIgnoreCase("Alert Page")) {
//			ele = FindElement(driver, "//span[@title='" + fileName + "']", fileName + " file Link", action.BOOLEAN, 10);
//		} else {
//			ele = FindElement(driver, "//a[@title='" + fileName + "']", fileName + " file Link", action.BOOLEAN, 10);
//		}
		if(ele!=null) {
			if (click(driver, ele, fileName + " file link", action.SCROLLANDBOOLEAN)) {
				String parentID = switchOnWindow(driver);
				if(parentID == null){
					click(driver, ele, fileName + " file link", action.SCROLLANDBOOLEAN);
					parentID = switchOnWindow(driver);
					if(parentID == null){
						appLog.error("Box view window is not opening after clicking on the file name link on '"+pageName+"' for the second time, File clicked: "+ fileName+", So will try to open file using open link in the drop down.");
						clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Open, fileName, workspace, 60, "Yes");
						parentID = switchOnWindow(driver);
					}
				}
				if (parentID != null) {
					for (int i = 0; i < Labels.split(",").length; i++) {
						if(target)
						{
							if (checkWatermarking(folderType, Labels.split(",")[i],firmName, investorName, fundName, emailAddress,
									downloadDate)) {
								appLog.info(Labels.split(",")[i] + " Watermarking label successfully verified for folder "
										+ folderType.toString());
							} else {
								appLog.error(
										Labels.split(",")[i] + " Watermarking label is not verified for folder " + folderType.toString());
								BaseLib.sa.assertTrue(false,
										Labels.split(",")[i] + " Watermarking label is not verified for folder " + folderType.toString());
							}	
						}else{
							if (!checkWatermarking(folderType, Labels.split(",")[i],firmName, investorName, fundName, emailAddress,
									downloadDate)) {
								appLog.info(Labels.split(",")[i] + " Watermarking label is not present for CRM/External Hence verified for folder "
										+ folderType.toString());
							} else {
								appLog.error(
										Labels.split(",")[i] + " Watermarking label is  present for CRM/External Hence Not verified for folder " + folderType.toString());
								BaseLib.sa.assertTrue(false,
										Labels.split(",")[i] + " Watermarking label is  present for CRM/External Hence Not verified for folder  " + folderType.toString());
							}	
						}
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.error(
							"Box view window is not opening after clicking on the file name link and by open link in the drop down on '"+pageName+"', So cannot check watermarking. File clicked: "
									+ fileName);
					BaseLib.sa.assertTrue(false,
							"Box view window is not opening after clicking on the file name link and by open link in the drop down on '"+pageName+"', So cannot check watermarking. File clicked: "
									+ fileName);
				}
			} else {
				appLog.error(fileName + " File cannot be clicked, so won't be able to check watermarking.");
				BaseLib.sa.assertTrue(false, fileName + " File cannot be clicked, so won't be able to check watermarking.");
			}
		}else {
			appLog.error("file is not available so cannot check watermarking");
			BaseLib.sa.assertTrue(false, "file is not available so cannot check watermarking");
		}
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param fileName
	 * @param folderType
	 * @param Labels
	 * @param investorName
	 * @param fundName
	 * @param emailAddress
	 * @param downloadDate
	 * @param pageName
	 * @param workspace
	 * @return
	 */
	public List<String> verifyWatermarkingWithoutAssertion(String fileName, FolderType folderType, String Labels,
			String firmName,String InstitutionNameOrLPName, String fundName, String emailAddress, String downloadDate, PageName pageName,Workspace workspace) {
		WebElement ele = null;
		String[] ss= null;
		List<String> notFoundWatermarking = new ArrayList<String>();
		if(pageName.toString().equalsIgnoreCase(PageName.ManageApprovalsPopUp.toString())) {
			ele = FindElement(driver, "//a[text()='"+fileName+"']", fileName + " file Link", action.BOOLEAN, 10);
		}else if (pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())) {
			if(InstitutionNameOrLPName.contains("<break>")) {
				ss =InstitutionNameOrLPName.split("<break>");
				ele = FindElement(driver, "//span[@title='"+ss[0]+"']/../../span[5]/a[@title='"+fileName+"']", fileName+" file name", action.SCROLLANDBOOLEAN, 10);
		}else {
			ele = FindElement(driver, "//span[@title='"+InstitutionNameOrLPName+"']/../../span[5]/a[@title='"+fileName+"']", fileName+" file name", action.SCROLLANDBOOLEAN, 10);
		}
		}else if (pageName.toString().equalsIgnoreCase(PageName.FundPageAlertPopUp.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactPageAlertPopUp.toString())) {
			if(InstitutionNameOrLPName.contains("<break>")) {
					ss =InstitutionNameOrLPName.split("<break>");
					ele = FindElement(driver, "//span[@title='"+ss[0]+"']/../../span[3]/a[@title='"+fileName+"']", fileName+" file name", action.SCROLLANDBOOLEAN, 10);
			}else {
				ele = FindElement(driver, "//span[@title='"+InstitutionNameOrLPName+"']/../../span[3]/a[@title='"+fileName+"']", fileName+" file name", action.SCROLLANDBOOLEAN, 10);
			}
		}else {
			ele = FindElement(driver, "//a[@title='" + fileName + "']", fileName + " file Link", action.BOOLEAN, 10);
		}
		if(ele!=null) {
			if (click(driver, ele, fileName + " file link", action.SCROLLANDBOOLEAN)) {
				String parentID = switchOnWindow(driver);
				if(parentID == null){
					click(driver, ele, fileName + " file link", action.SCROLLANDBOOLEAN);
					parentID = switchOnWindow(driver);
					if(workspace!=null) {
						if(parentID == null){
							appLog.error("Box view window is not opening after clicking on the file name link on '"+pageName.toString()+"' for the second time, File clicked: "+ fileName+", So will try to open file using open link in the drop down.");
							clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Open, fileName, workspace, 30, "Yes");
							parentID = switchOnWindow(driver);
						}
					}
				}
				ThreadSleep(20000);
				if (parentID != null) {
					System.err.println("Inside method: "+getSystemDate("hh:mm:ss"));
					for (int i = 0; i < Labels.split(",").length; i++) {
						System.err.println(i+" : Iteration: "+getSystemDate("hh:mm:ss"));
						if(ss!=null) {
							if(checkWatermarking(folderType,Labels.split(",")[i],firmName, ss[1], fundName, emailAddress, downloadDate)) {
								appLog.info(Labels.split(",")[i] + " is present Hence Successfully Watermarking label successfully verified for folder "
										+ folderType.toString() +" on '"+pageName.toString()+"'");
							} else {
								appLog.error(
										Labels.split(",")[i] + " is not present Hence Watermarking label is not verified for folder " + folderType.toString() +" on '"+pageName.toString()+"'");
								screenshot(currentlyExecutingTC);
								notFoundWatermarking.add(Labels.split(",")[i]);
							}
						}else {
							if(checkWatermarking(folderType,Labels.split(",")[i],firmName, InstitutionNameOrLPName, fundName, emailAddress, downloadDate )) {
								appLog.info(Labels.split(",")[i] + " is present Hence Successfully Watermarking label successfully verified for folder "
										+ folderType.toString() +" on '"+pageName.toString()+"'");
							} else {
								appLog.error(
										Labels.split(",")[i] + " is not present Hence Watermarking label is not verified for folder " + folderType.toString() +" on '"+pageName.toString()+"'");
								screenshot(currentlyExecutingTC);
								notFoundWatermarking.add(Labels.split(",")[i]);
							}	
						}
						
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.error(
							"Box view window is not opening after clicking on the file name link and by open link in the drop down on '"+pageName.toString()+"', So cannot check watermarking. File clicked: "
									+ fileName);
					screenshot(currentlyExecutingTC);
					return null;
				}
			} else {
				appLog.error(fileName + " File cannot be clicked, so won't be able to check watermarking.");
				screenshot(currentlyExecutingTC);
				return null;
			}
			
		}else {
			appLog.error("file is not avaialble in the content grid :: "+fileName+" so cannot check watermarking label");
			notFoundWatermarking.add("file is not avaialble in the content grid :: "+fileName+" so cannot check watermarking label");
			screenshot(currentlyExecutingTC);
		}
		
		return notFoundWatermarking;
	}
	
	/**
	 * @author Akul Bhutani
	 * @param driver
	 * @param filesToAprrove
	 * @throws InterruptedException
	 */
	public void selectPendingFilesToApprove(WebDriver driver, String filesToAprrove) throws InterruptedException {
		String[] files = filesToAprrove.split(",");
		int count=0;
		appLog.info("Number of files to be selected: "+files.length);
		CommonLib.scrollDownThroughWebelement(driver,getManageApprovalWindow(30), "manage aaproval window view.");
		for (int i = 0; i < files.length; i++) {
//			Thread.sleep(3000);
			List<WebElement> ele = driver.findElements(
					By.xpath("//a[contains(text(),'" + files[i] + "')]/../preceding-sibling::span//input"));
//			IPListeners.ipLog.info("Number of files to be selected: " + ele.size());
			for (int j = 1; j <= ele.size(); j++) {
				if(i>0){
					try{
						getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments,10);
//					continue;
					}catch(Exception e){
						appLog.info("Selecting other files.");
					}
				}
				try{
					appLog.info("Selecting the file: "
							+ driver.findElement(By.xpath("//a[contains(text(),'" + files[i] + "')]")).getText());
					driver.findElement(By.xpath("(//a[contains(text(),'" + files[i] + "')]/../preceding-sibling::span//input)["+j+"]")).click();
//				ele.get(j).click();
				appLog.info("Selected file is: "
						+ driver.findElement(By.xpath("//a[contains(text(),'" + files[i] + "')]")).getText());
				}catch(Exception e){
//					IPListeners.ipLog.info("Selecting rest of the files");
//					ele = driver.findElements(
//							By.xpath("//a[contains(text(),'" + files[i] + "')]/../preceding-sibling::span//input"));
				}
				count++;
			}
		}
		appLog.info("Number of files selected: "+count);

	}
	
	/**
	 * @author Akul Bhutani
	 * @param workspaceAction
	 * @return List<String>
	 */
	public List<String> selectAllPendingFilesToApprove(WorkSpaceAction workspaceAction) {
		List<String> result = new ArrayList<String>();
		CommonLib.scrollDownThroughWebelement(driver,getManageApprovalWindow(30), "manage aaproval window view.");
		WebElement alldocCheckBox=getManageApprovalsAllDocumentSelectCheckBox(10);
		List<WebElement> ele= getManageApprovalsAllPendingFilesCheckBoxList();
		if(!ele.isEmpty()) {
			if(alldocCheckBox!=null) {
				if(click(driver, alldocCheckBox,"all document check box", action.SCROLLANDBOOLEAN)) {
					for(int i=0 ;i<ele.size(); i++) {
						if(isSelected(driver, ele.get(i), "pending document check box")) {
							appLog.info("pending document check box is checked");
						}else {
							appLog.error("Pending Document check is not checked after click on all document check box");
							result.add("pending Document check is not checked after click on all document check box");
						}
					}
					if(click(driver, getManageApprovalsApproveBtn(10), "approve button", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on approve button");
						if(click(driver, getManageApprovalsConfirmYesBtn(20), "manage approvals confirm yes button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on manage approvals confirm yes button");
							if(workspaceAction.toString().equalsIgnoreCase(WorkSpaceAction.UPDATE.toString())) {
								if(click(driver, getManageApprovalsUpdateAllDocument(20), "update all document", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on update all button");
									if(click(driver, getManageApprovalsApprovedUpdatedDocumentCloseBtn(30), "close button", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on close button");
									}else {
										appLog.error("Not able to click on manage approvals close button");
										result.add("Not able to click on manage approvals close button");
									}
								}else {
									appLog.error("Not able to click on manage approvals update all button");
									result.add("Not able to click on manage approvals update all button");
								}
							}else {
								if(click(driver, getManageApprovalsCloseBtn(20), "manage approvals close button", action.SCROLLANDBOOLEAN)) {
									
								}else {
									appLog.error("Not able to click on manage approvals close button");
									result.add("Not able to click on manage approvals close button");
								}
							}
							if(click(driver, getManageApprovalsCancelBtn(10), "manage approvals cancel button", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on manage approvals cancel button");
							}else {
								appLog.error("Not able to click on manage approvals cancel button");
								result.add("Not able to click on manage approvals cancel button");
							}
						}else {
							appLog.error("Not able to click on manage approvals confirm Yes button");
							result.add("Not able to click on manage approvals confirm Yes button");
						}
					}else {
						appLog.error("Not able to click on manage apprpovals approve button");
						result.add("Not able to click on manage apprpovals approve button");
					}
				}else {
					appLog.error("Not able to click on all document check box so cannot approve pending document");
					result.add("Not able to click on all document check box so cannot approve pending document");
					click(driver, getManageApprovalsCancelBtn(10), "cancel button", action.SCROLLANDBOOLEAN);
				}
			}else {
				appLog.error("all document check box is not visible so cannot approve pending documents");
				result.add("all document check box is not visible so cannot approve pending documents");
				click(driver, getManageApprovalsCancelBtn(10), "cancel button", action.SCROLLANDBOOLEAN);
			}
		}else {
			appLog.error("No pending files are avilable for approve in manage approvals pop up");
			result.add("No pending files are avilable for approve in manage approvals pop up");
			click(driver, getManageApprovalsCancelBtn(10), "cancel button", action.SCROLLANDBOOLEAN);
		}
		return result;

	}
	
	/**
	 * @author Akul Bhutani
	 * @param folderName
	 * @param mat
	 * @return true/false
	 */
	public boolean verifyManageApprovalsDropdownContents(String folderName, ManageApprovalTabs mat) {
		boolean flag = true;
		if (getManageAppHeadText(60).getText().trim().equals("Manage Approvals")) {
			appLog.info("manage approvals popup is successfully opened");
		}
		else {
			appLog.error("manage approvals popup is not opened");
			flag = false;
		}
		if (getSelectedOptionOfDropDown(driver,getManageApprovalsDropdown(mat, 30) , "manage approval dropdown", "text").trim().equals(folderName)) {
			appLog.info("already selected option of dropdown is correct "+folderName);
		}
		else {
			appLog.error("already selected option of dropdown is wrong "+getSelectedOptionOfDropDown(driver,getManageApprovalsDropdown(mat, 30) , "manage approval dropdown", "text")+" and "+folderName);
			flag = false;
		}
		List<WebElement> temp_list = allOptionsInDropDrop(driver, getManageApprovalsDropdown(mat, 30), "manage approvals dropdown");
		if (temp_list.size()==2) {
			appLog.info("the dropdown contains 2 elements only as expected");
			if (mat == ManageApprovalTabs.PendingDocuments) {
				if (temp_list.get(1).getText().trim().equals("All Pending Documents")) {
					appLog.info("All pending documents is successfully found in manage approvals dropdown");
				}
				else {
					appLog.error("all pending documents is not found in manage approvals dropdown");
					BaseLib.sa.assertTrue(false, "all pending documents is not found in manage approvals dropdown");
				}
			}
			else {
				if (temp_list.get(1).getText().trim().equals("All Approved Documents")) {
					appLog.info("All Approved documents is successfully found in manage approvals dropdown");
				}
				else {
					appLog.error("all Approved documents is not found in manage approvals dropdown");
					BaseLib.sa.assertTrue(false, "all Approved documents is not found in manage approvals dropdown");
				}
			}
			
		}
		else {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * @author Akul Bhutani
	 * @param fileName
	 * @return Checkbox WebElement corresponding to particular file
	 */
	public WebElement checkboxForFileInManageApprovals(String fileName) {
		String xpath = "//span[contains(@id,'pendingGrid-cell-1')]//a[text()='"+fileName+"']/../..//span//input[@type='checkbox']";
		return isDisplayed(driver, FindElement(driver, xpath, "checkbox for "+fileName, action.SCROLLANDBOOLEAN, 30), "visibility", 30, "checkbox for "+fileName);
	}
	
	/**
	 * @author Akul Bhutani
	 * @param fileName
	 * @param folderPath
	 * @return Checkbox WebElement corresponding to particular file
	 */
	public WebElement checkboxForFileInManageApprovals(String fileName, String folderPath) {
		String xpath = "//span[contains(@id,'pendingGrid-cell-1')]//a[text()='"+fileName+"']/../..//span[contains(text(),'"+folderPath+"')]/..//input";
		return isDisplayed(driver, FindElement(driver, xpath, "checkbox for "+fileName, action.SCROLLANDBOOLEAN, 30), "visibility", 30, "checkbox for "+fileName);
	}
	
	/**
	 * @author Akul Bhutani
	 * @param fileName
	 * @param folderPath
	 * @param uploadedBy
	 * @return Checkbox WebElement corresponding to particular file
	 */
	public WebElement checkboxForFileInManageApprovals(String fileName, String folderPath, String uploadedBy) {
		String xpath = "//span[contains(@id,'pendingGrid-cell-1')]//a[text()='"+fileName+"']/../..//span[contains(text(),'"+folderPath+"')]/..//span[contains(text(),'"+uploadedBy+"')]/..//input";
		return isDisplayed(driver, FindElement(driver, xpath, "checkbox for "+fileName, action.SCROLLANDBOOLEAN, 30), "visibility", 30, "checkbox for "+fileName);
	}
	
	/**
	 * @author Akul Bhutani
	 * @param mat
	 * @param fileName
	 * @param timeOut
	 * @return true/false
	 */
	public WebElement clickOnDocumentLinkOnManageApprovals(ManageApprovalTabs mat, String fileName,int timeOut) {
		String tabXpath = "",docXpath = "";
		if (mat == ManageApprovalTabs.PendingDocuments) {
			tabXpath = "//span[contains(@id,'pending')]";
			docXpath = "//a[text()='"+fileName+"']";
		}
		else if(mat == ManageApprovalTabs.ApprovedDocuments) {
			docXpath = "//span[contains(@id,'aw')]";
			docXpath = "//a[title()='"+fileName+"']";
		}
		
		return isDisplayed(driver, FindElement(driver, tabXpath+docXpath, "document on manage approvals window", action.BOOLEAN, timeOut), "visibility", timeOut, "document on manage approvals window");
	}
	
	/**
	 * @author Akul Bhutani
	 * @param workspace
	 * @param filesName
	 * @return true/false
	 */
	public boolean deleteFromManageApprovals(Workspace workspace,String filesName) {
		String[] files = filesName.split("<break>");
		if (click(driver, getManageApprovalIcon(workspace, 30), "manage approval icon for "+workspace.toString(), action.SCROLLANDBOOLEAN)) {
			if (click(driver, getPendingDocsTab(30), "pending documents tab", action.BOOLEAN)) {
				if (selectVisibleTextFromDropDown(driver, getManageApprovalsDropdown(ManageApprovalTabs.PendingDocuments,30), "manage approvals pending dropdown", "All Pending Documents")) {
					for (int i = 0;i<files.length;i++) {
						if (checkboxForFileInManageApprovals(files[i])!=null) {
							if (!isSelected(driver, checkboxForFileInManageApprovals(files[i]), "checkbox for "+files[i])) {
								if (click(driver, checkboxForFileInManageApprovals(files[i]),"checkbox for "+files[i] , action.SCROLLANDBOOLEAN)) {
									appLog.info("selected checkbox for "+files[i]);
								}
							}
							else {
								appLog.info("checkbox for "+files[i]+" is already selected");
							}
						}
						else {
							appLog.error("could not find file "+files[i]);
							BaseLib.sa.assertTrue(false, "could not find file "+files[i]);
						}
					}
				}
				if (click(driver, getdeleteBtnManageApprovals(60), "delete Btn Manage Approvals", action.BOOLEAN)) {
					if (click(driver, getManageApprovalDelYesorNo(YesNo.Yes,60), "manage approvals delete yes", action.SCROLLANDBOOLEAN)) {
						return true;
					}
					else {
						appLog.error("manage approvals delete yes button is not clickable");
						BaseLib.sa.assertTrue(false, "manage approvals delete yes button is not clickable");
					}
				}
				else {
					appLog.error("manage approvals delete button is not clickable");
					BaseLib.sa.assertTrue(false, "manage approvals delete button is not clickable");
				}
			}
			else {
				appLog.error("pending docs tab is not clickable");
				BaseLib.sa.assertTrue(false, "manage approvals delete button is not clickable");
			}
		}
		return false;
		
	}
	
	/**
	 * @author Akul Bhutani
	 * @param workspace
	 * @param mat
	 * @param files
	 * @param folderPath
	 * @param status
	 * @param uploadedBy
	 * @param firmName
	 * @param uploadedOn
	 * @return
	 */
	public boolean verifyFilesPresentInManageApprovals(Workspace workspace,ManageApprovalTabs mat, String[] files,String folderPath,
				String status, String uploadedBy, String firmName, String uploadedOn) {

			String workSpaceXpath = "";
			
			boolean flag = true;
			String dcName=null;
			String dcStatus = null;
			String dcUploadedBy = null;
			String dcUploadedOn = null;
			String dcFolderPath = null;
			String dcFirmName = null;
			

				String docNameXpath="";
				String docStatusXpath="";
				String docUploadedByXpath="";
				String docUploadedOnXpath="";
				String folderpathXpath = "";
				String firmNameXpath="";
				//if (workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
				if (mat == ManageApprovalTabs.PendingDocuments) {
					docNameXpath = workSpaceXpath + "//span[contains(@id,'pendingGrid-cell-1')]//a";
					folderpathXpath = workSpaceXpath + "//span[contains(@id,'pendingGrid-cell-2')]";
					docStatusXpath = workSpaceXpath + "//span[contains(@id,'pendingGrid-cell-3')]/img";
					docUploadedByXpath = workSpaceXpath + "//span[contains(@id,'pendingGrid-cell-4-')]";
					firmNameXpath = workSpaceXpath + "//span[contains(@id,'pendingGrid-cell-5-')]";
					docUploadedOnXpath = workSpaceXpath + "//span[contains(@id,'pendingGrid-cell-6-')]";

				}
				else {
					docNameXpath = workSpaceXpath + "//span[contains(@id,'AprovedGrid-') and contains(@id,'-cell-1')]//a";
					folderpathXpath = workSpaceXpath + "//span[contains(@id,'AprovedGrid-') and contains(@id,'-cell-2')]";
					docStatusXpath = workSpaceXpath + "//span[contains(@id,'AprovedGrid-') and contains(@id,'-cell-3')]/img";
					docUploadedByXpath = workSpaceXpath + "//span[contains(@id,'AprovedGrid-') and contains(@id,'-cell-4')]";
					firmNameXpath = workSpaceXpath + "//span[contains(@id,'AprovedGrid-') and contains(@id,'-cell-5')]";
					docUploadedOnXpath = workSpaceXpath + "//span[contains(@id,'AprovedGrid-') and contains(@id,'-cell-6')]";
					}
				
				List<WebElement> docNames = FindElements(driver, docNameXpath, "Document Name List");

				if (!docNames.isEmpty()) {

					List<WebElement> docStatus = FindElements(driver, docStatusXpath, "Document Status List");
					List<WebElement> docUploadedBy = FindElements(driver, docUploadedByXpath, "Document Uploaded By List");
					List<WebElement> docUploadedOn = FindElements(driver, docUploadedOnXpath, "Document Uploaded On List");
					List<WebElement> docFirmName = FindElements(driver, firmNameXpath, "Document firm name List");
					List<WebElement> docFolderPath = FindElements(driver, folderpathXpath, "Document firm name List");
					System.err.println("/n/n/tSize  :      Doc : "+docNames.size()+" statsu : "+docStatus.size()+" docUploadedBy : "+docUploadedBy.size()+" uploaded on : "+docUploadedOn.size()+"/n/n");

					for (int i = 0; i < files.length; i++) {

						for (int j = 0; j < docNames.size(); j++) {
							dcName=docNames.get(j).getText().trim();
							dcStatus = docStatus.get(j).getAttribute("title").trim();
							dcUploadedBy = docUploadedBy.get(j).getText().trim();
							dcUploadedOn = docUploadedOn.get(j).getText().trim();
							dcFirmName = docFirmName.get(j).getText().trim();
							dcFolderPath = docFolderPath.get(j).getText().trim();
							System.out.println("/n/n");
							System.err.println(">>>>>>> from WebPage "+dcName+" "+dcFolderPath+" "+dcStatus+" "+dcUploadedBy+" "+dcFirmName+" "+dcUploadedOn+" <<<<<<<<");
							System.out.println("<<<<<<<<< pASSING "+files[i] +" "+folderPath+" "+status+" "+uploadedBy+" "+firmName+" "+uploadedOn+" <<<<<<<<<");
							System.out.println("/n/n");
							if (dcName.equalsIgnoreCase(files[i]) && dcFirmName.equalsIgnoreCase(firmName) && dcFolderPath.equalsIgnoreCase(folderPath)
									&& dcStatus.equalsIgnoreCase(status) && dcUploadedBy.equalsIgnoreCase(uploadedBy)
									&& (uploadedOn.contains(dcUploadedOn) || previousOrForwardDate(-1, "MM/dd/yyyy").contains(dcUploadedOn))) {

								appLog.info(files[i]+" is present having status "+status+" Uploaded By "+uploadedBy+" Uploaded on"+uploadedOn + " "+ " : "
										+ workspace.toString());
								break;
							}
							
							if (j == docNames.size() - 1) {
								appLog.error(files[i] + " File not Present having status "+status+" Uploaded By "+uploadedBy+" Uploaded on"+uploadedOn +" : "
										+ workspace.toString());
								BaseLib.sa.assertTrue(false,files[i] + " File not Present having status "+status+" Uploaded By "+uploadedBy+" Uploaded on"+uploadedOn +" : "
										+ workspace.toString());
								flag = false;
							}

						}

					}

				} else {
					appLog.info("Document List is Empty "+" : " + workspace.toString());
					BaseLib.sa.assertTrue(false, "Document List is Empty "+" : " + workspace.toString());
				}

			return flag;
		}

	/**
	 * @author Akul Bhutani
	 * @param workspace
	 * @param mat
	 * @param fileNames
	 * @param folderPath
	 * @param status
	 * @param uploadedBy
	 * @param firmName
	 * @param uploadedOn
	 * @return true/false
	 */
	public boolean verifyFilesPresentInManageApprovals(Workspace workspace,ManageApprovalTabs mat, String fileNames,String folderPath,
			String status, String uploadedBy, String firmName, String uploadedOn) {

		boolean flag = true;
		String dcName=null;
		String dcStatus = null;
		String dcUploadedBy = null;
		String dcUploadedOn = null;
		String dcFolderPath = null;
		String dcFirmName = null;
		
			String files[] = fileNames.split("<break>");
			String docNameXpath="";
			String docStatusXpath="";
			String docUploadedByXpath="";
			String docUploadedOnXpath="";
			String folderpathXpath = "";
			String firmNameXpath="";
			//if (workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			if (mat == ManageApprovalTabs.PendingDocuments) {
				docNameXpath = "//span[contains(@id,'pendingGrid-cell-1')]//a";
				folderpathXpath = "//span[contains(@id,'pendingGrid-cell-2')]";
				docStatusXpath = "//span[contains(@id,'pendingGrid-cell-3')]/img";
				docUploadedByXpath = "//span[contains(@id,'pendingGrid-cell-4-')]";
				firmNameXpath = "//span[contains(@id,'pendingGrid-cell-5-')]";
				docUploadedOnXpath = "//span[contains(@id,'pendingGrid-cell-6-')]";

			}
			else {
				docNameXpath = "//span[contains(@id,'AprovedGrid-') and contains(@id,'-cell-1')]//a";
				folderpathXpath = "//span[contains(@id,'AprovedGrid-') and contains(@id,'-cell-2')]";
				docStatusXpath = "//span[contains(@id,'AprovedGrid-') and contains(@id,'-cell-3')]/img";
				docUploadedByXpath = "//span[contains(@id,'AprovedGrid-') and contains(@id,'-cell-4')]";
				firmNameXpath = "//span[contains(@id,'AprovedGrid-') and contains(@id,'-cell-5')]";
				docUploadedOnXpath = "//span[contains(@id,'AprovedGrid-') and contains(@id,'-cell-6')]";
				}
			
			List<WebElement> docNames = FindElements(driver, docNameXpath, "Document Name List");
			if (!docNames.isEmpty()) {

				List<WebElement> docStatus = FindElements(driver, docStatusXpath, "Document Status List");
				List<WebElement> docUploadedBy = FindElements(driver, docUploadedByXpath, "Document Uploaded By List");
				List<WebElement> docUploadedOn = FindElements(driver, docUploadedOnXpath, "Document Uploaded On List");
				List<WebElement> docFirmName = FindElements(driver, firmNameXpath, "Document firm name List");
				List<WebElement> docFolderPath = FindElements(driver, folderpathXpath, "Document firm name List");
				System.err.println("/n/n/tSize  :      Doc : "+docNames.size()+" statsu : "+docStatus.size()+" docUploadedBy : "+docUploadedBy.size()+" uploaded on : "+docUploadedOn.size()+"/n/n");
				
				for (int i = 0; i < files.length; i++) {

					for (int j = 0; j < docNames.size(); j++) {
						dcName=docNames.get(j).getText().trim();
						dcStatus = docStatus.get(j).getAttribute("title").trim();
						if (uploadedBy == null) {
							uploadedBy = "";
							dcUploadedBy = "";
						}
						else dcUploadedBy = docUploadedBy.get(j).getText().trim();
						dcUploadedOn = docUploadedOn.get(j).getText().trim();
						dcFirmName = docFirmName.get(j).getText().trim();
						dcFolderPath = docFolderPath.get(j).getText().trim();
						System.out.println("/n/n");
						System.err.println(">>>>>>> from WebPage "+dcName+" "+dcFolderPath+" "+dcStatus+" "+dcUploadedBy+" "+dcFirmName+" "+dcUploadedOn+" <<<<<<<<");
						System.out.println("<<<<<<<<< pASSING "+files[i] +" "+folderPath+" "+status+" "+uploadedBy+" "+firmName+" "+uploadedOn+" <<<<<<<<<");
						System.out.println("/n/n");
						if (dcName.equalsIgnoreCase(files[i]) && dcFirmName.equalsIgnoreCase(firmName) && dcFolderPath.equalsIgnoreCase(folderPath)
								&& dcStatus.equalsIgnoreCase(status) && dcUploadedBy.equalsIgnoreCase(uploadedBy)
								&& (uploadedOn.contains(dcUploadedOn) || previousOrForwardDate(-1, "MM/dd/yyyy").contains(dcUploadedOn))) {

							appLog.info(files[i]+" is present having status "+status+" Uploaded By "+uploadedBy+" Uploaded on"+uploadedOn + " "+ " : "
									+ workspace.toString());
							break;
						}
						if (j == docNames.size() - 1) {
							
							appLog.error(files[i] + " File not Present having status "+status+" Uploaded By "+uploadedBy+" Uploaded on"+uploadedOn +" : "
									+ workspace.toString());
							flag = false;
						}

					}

				}

			} else {
				appLog.info("Document List is Empty "+" : " + workspace.toString());
				BaseLib.sa.assertTrue(false, "Document List is Empty "+" : " + workspace.toString());
			}

		return flag;
	}
	
	/**
	 * @author Akul Bhutani
	 * @param mat
	 * @param documentName
	 * @param timeout
	 * @param scrollBoxele
	 * @return true/false
	 */
	public boolean clickOnDocumentManageApprovals(ManageApprovalTabs mat, String documentName, int timeout,WebElement scrollBoxele) {
		int j = 0;
		WebElement ele = null;
		By docByXpath;
		String docStringXpath;
		//  WebElement scrollBoxele = FindElement(driver, "//span[@id='myGrid_firmAllDoc-scroll-box']", "Scroll Pop Up",action.SCROLLANDBOOLEAN, timeout);
		if (mat == ManageApprovalTabs.PendingDocuments) {
			docByXpath = By.xpath("//span[contains(@id,'pending')]//a[text()='"+documentName+"']");
			docStringXpath = "//span[contains(@id,'pending')]//a[text()='"+documentName+"']";
		}
		else {
			docByXpath = By.xpath("//span[contains(@id,'AprovedGrid-')]//a[text()='"+documentName+"']");
			docStringXpath = "//span[contains(@id,'AprovedGrid-')]//a[text()='"+documentName+"']";
		}
		ThreadSleep(5000);
		int widgetTotalScrollingHeight = Integer.parseInt(String.valueOf(((JavascriptExecutor) driver)
					.executeScript("return arguments[0].scrollHeight", scrollBoxele)));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0,0)",scrollBoxele);
			for (int i = 0; i <= widgetTotalScrollingHeight / 25; i++) {
				if (!driver.findElements(docByXpath).isEmpty() && driver.findElement(docByXpath).isDisplayed()) {
					appLog.info("Element Successfully Found and displayed");
					ThreadSleep(500);
					ele=FindElement(driver, docStringXpath, "", action.BOOLEAN, 20);
					if(ele!=null) {
						if(click(driver, ele, "", action.BOOLEAN)){
							System.err.println("Clicked ON Element");
						}else {
							appLog.error("Not able to clicke on Document Name: "+documentName);
							return false;
						}
					}
					break;
				} else {
					System.out.println("Not FOund: "+docByXpath.toString());
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(" + j + "," + (j = j + 45) + ")",
							scrollBoxele);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (i == widgetTotalScrollingHeight / 50) {
						return false;
					}
				}
			}
			return true;
		}
	
	/**
	 * @author Akul Bhutani
	 * @param mat
	 * @param widgetScrollingElement
	 * @param fileName
	 * @param folderPath
	 * @param uploadedBy
	 * @param firmName
	 * @param date
	 * @return true/false
	 */
	public boolean findRowByScrollingManageApprovals(ManageApprovalTabs mat, WebElement widgetScrollingElement, String fileName, String folderPath, String uploadedBy, String firmName ,String date) {
		boolean flag = false;
		String statusSelect = "";
		String gridxpath = "";
		String xpath="";
		if (mat == ManageApprovalTabs.PendingDocuments) {
			gridxpath = "//span[contains(@id,'pendingGrid-cell-1')]";
			statusSelect = "Pending";
		}
		else {
			gridxpath = "//span[contains(@id,'AprovedGrid-') and contains(@id,'-cell-1')]";
			statusSelect = "Approved";
		}
		if (uploadedBy == null) {
			xpath = "/..//span/img[@title='"+statusSelect+"']/../..//span[contains(text(),'"+folderPath+"')]/..//span[contains(@id,'cell-5') and contains(text(),'"+firmName+"')]/..//span[contains(@id,'cell-6') and contains(text(),'"+date+"') or contains(text(),'"+previousOrForwardDate(-1, "MM/dd/yyyy")+"')]/..//a[text()='"+fileName+"']";
		}
		
		else {
			xpath = "/..//span/img[@title='"+statusSelect+"']/../..//span[contains(text(),'"+folderPath+"')]/..//span[contains(@id,'cell-4') and contains(text(),'"+uploadedBy+"')]/..//span[contains(@id,'cell-5') and contains(text(),'"+firmName+"')]/..//span[contains(@id,'cell-6') and contains(text(),'"+date+"') or contains(text(),'"+previousOrForwardDate(-1, "MM/dd/yyyy")+"')]/..//a[text()='"+fileName+"']";
		}
		xpath = gridxpath + xpath;
		int widgetTotalScrollingHeight = 100;
		try{
			widgetTotalScrollingHeight = Integer.parseInt(String.valueOf(((JavascriptExecutor) driver)
					.executeScript("return arguments[0].scrollHeight", widgetScrollingElement)));
//			System.err.println("Successfully got the scrolling height.");
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0,0)",widgetScrollingElement);
//			System.err.println("Successfully got the scrollTo 0,0.");
			
		} catch(Exception e){
//			e.printStackTrace();
//			System.err.println(widgetTotalScrollingHeight+" scrolling height.");
		}
		int j = 0;
		for (int i = 0; i <= widgetTotalScrollingHeight / 25; i++) {
			if (!FindElements(driver, xpath, "row of document "+fileName).isEmpty()) {
//				ThreadSleep(1000);
				CommonLib.scrollDownThroughWebelement(driver, FindElement(driver, xpath, "row of document "+fileName, action.BOOLEAN, 1), fileName);
				ThreadSleep(500);
				
				flag = true;
				break;
			} else {
				System.out.println("Not FOund: "+fileName+" in iteration "+i);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(" + j + "," + (j = j + 25) + ")",
						widgetScrollingElement);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (i == widgetTotalScrollingHeight / 50) {
					flag = false;
					break;
				}
			}
		}
		return flag;
		
	}
 	
	/**
	 * @author Parul Singh
	 * @param workspace
	 * @param InstituteName
	 * @param LPName
	 * @param updatedInsOrLPName
	 * @param variable_value
	 * @return true/false
	 */
	public boolean updateInvestorOrLPNameFromManageInvestor(Workspace workspace,String InstituteName,String LPName,String updatedInsOrLPName,String variable_value) {
		boolean clickOnRenameIcon=false;
		if(click(driver,getManageInvestorIcon(workspace, 20), "manage investor icon", action.SCROLLANDBOOLEAN)) {
			if(LPName!=null) {
				clickOnRenameIcon=clickOnRenameManageTargetLimitedPartner(InstituteName, LPName);
			}else {
				clickOnRenameIcon=clickOnRenameManageTargetInstitution(InstituteName);
			}
			if(clickOnRenameIcon) {
				ThreadSleep(3000);
				if(sendKeys(driver,getManageInvestorRenamePopupTextBox(workspace, 10), updatedInsOrLPName,"rename text box", action.SCROLLANDBOOLEAN)) {
					if(LPName!=null) {
						ExcelUtils.writeData(updatedInsOrLPName, "Limited Partner",excelLabel.Variable_Name,variable_value, excelLabel.UpdatedLimitedPartner_NameFormManageInvestor);
					}else {
						ExcelUtils.writeData(updatedInsOrLPName, "Institutions",excelLabel.Variable_Name,variable_value, excelLabel.UpdateInstitution_NameFormManageInvestor);
					}
					appLog.info("pass value in manage investor rename text box: "+updatedInsOrLPName);
					if(click(driver,getManageInvestorRenamePopupApplyButton(workspace, 10),"apply button", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on manage investor rename apply button");
						if(click(driver,getManageInvestorDoneButton(workspace, 10), "done button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on manage investor done button");
							return true;
						}else {
							appLog.error("Not able to click on done button so cannot close manage investor");
						}
					}else {
						appLog.error("Not able to click on rename apply save button so cannot update institution Name from manage investor");
					}
					
				}else {
					appLog.error("Not able to pass value in institution rename text box : "+M13Institution1+"UP so cannot update institution Name from manage investor");
				}
			}else {
				appLog.error("Not able to click on institution rename icon : "+M13Institution1+" so cannot update institution Name from manage investor");
			}
		}else {
			appLog.error("Not able to click on manage investor icon so cannot update institution Name from manage investor");
		}
		return false;
	}

	/**
	 * @author Parul Singh
	 * @param workspace
	 * @return softassert
	 */
	public SoftAssert verifyALertHistoryUI(Workspace workspace){
		SoftAssert sa=new SoftAssert();
		HomePageBusineesLayer hp=new HomePageBusineesLayer(driver);
		String errMsg = hp.getErrorMessage(60).getText().trim();
		if (errMsg.equalsIgnoreCase(HomePageErrorMessage.nodataToDisplayErroMessage)) {
			appLog.info("Message Matched : " + HomePageErrorMessage.nodataToDisplayErroMessage);
		} else {
			appLog.info("Message not Matched ");
			sa.assertTrue(false, "Message not matched " + "Expected: "
					+ HomePageErrorMessage.nodataToDisplayErroMessage + "\tActual: " + errMsg);
		}
		if (getAlertHistoryPopupHeader(workspace,60) != null
				&& getAlertHistoryPopupHeader(workspace,60).getText().trim().equalsIgnoreCase("Alert History")) {
			appLog.info("Alert history popup is displaying");
			} else {
			appLog.info("Alert history popup is not displaying");
			sa.assertTrue(false, "Alert history popup is not displaying");
		}			
		if (getAlertHistoryPopupShowLabel(workspace,60).getText().trim().equalsIgnoreCase("Show:")) {
			appLog.info("Show Drop Down Label is Displayed");
		} else {
			appLog.info("Show Drop Down Label is not Displayed");
			sa.assertTrue(false, "Show Drop Down Label is not Displayed");
		}
		if (getAlertHistoryPopupRangeLabel(workspace,60).getText().trim().equalsIgnoreCase("Range:")) {
			appLog.info("Range Drop Down Label is Displayed");
		} else {
			appLog.info("Range Drop Down Label is not Displayed");
			sa.assertTrue(false, "Range Drop Down Label is not Displayed");
		}
		String dropdownvalue = getSelectedOptionOfDropDown(driver, getAlertHistoryPopupShowDropdown(workspace,60), "Show dropdown ",
				"value");
		if (dropdownvalue.equalsIgnoreCase("All Alerts")) {
			appLog.info("Default All Alerts Drop Down value is selected in Show.");
		} else {
			appLog.info("Default All Alerts Drop Down value is not selected in Show");
			sa.assertTrue(false, "Default All Alerts Drop Down value is not selected in Show");
		}
		
		dropdownvalue = getSelectedOptionOfDropDown(driver, getAlertHistoryPopupRangeDropdown(workspace,60), "Range dropdown", "text");
		if (dropdownvalue.equalsIgnoreCase("Last 7 Days")) {
			appLog.info("Default Last 7 Days value is selected in Range.");
		} else {
			appLog.info("Default Last 7 Days value is not selected in Range.");
			sa.assertTrue(false, "Default Last 7 Days  value is not selected in Range.");
		}
		
		List<WebElement> columnValue = getAlertHitoryColoumnList(workspace);
		String[] ExpectedColumnText = { "Activity Type","Document ","Firm", "Contact","Date"};
		String[] actColumnText = new String[columnValue.size()];
		int i = 0;
		for (WebElement webElement : columnValue) {
			actColumnText[i] = webElement.getText().trim();
			i++;
		}
		for (int j = 0; j < actColumnText.length; j++) {
			if (ExpectedColumnText[j].contains(actColumnText[j])) {
				appLog.info("Column Verified : " + ExpectedColumnText[j]);
			} else {
				appLog.info("Column not Verified : " + "\tActual: " + actColumnText[j] + "\tExpected: "
						+ ExpectedColumnText[j]);
				sa.assertTrue(false, "Column not Verified : " + "\tActual: " + actColumnText[j] + "\tExpected: "
						+ ExpectedColumnText[j]);
			}
		}	
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
		String[] recWithValue = getAlertHistoryPopupRecordLabelWithValue(workspace, 60).getText().trim().split(":");

		if (recWithValue[0].equalsIgnoreCase("Records") && recWithValue[1].equalsIgnoreCase(" 0")) {
			appLog.info(recWithValue[0] + " Label with value " + recWithValue[1] + " is showing at bottom of section.");
		} else {
			appLog.info(recWithValue[0] + " Label with value " + recWithValue[1]
					+ " is showing at bottom of section. it should be 0");
			sa.assertTrue(false, recWithValue[0] + " Label with value " + recWithValue[1]
					+ " is showing at bottom of section. it should be 0");
		}		
		}
		List<WebElement> showOptions = allOptionsInDropDrop(driver, getAlertHistoryPopupShowDropdown(workspace, 60),
				"Show Drop Down");

		String[] expectedResult = { "All Alerts", "All Except Profile Updates", "Document Viewed",
				"Document Downloaded", "Document Uploaded", "Document Updated", "Contact Profile Updated",
				"Firm Profile Updated" };
		List<String> showOptions1 = new ArrayList<String>();
		for (WebElement showlist : showOptions) {
			showOptions1.add(showlist.getText().trim());
		}
		for (int k = 0; k < expectedResult.length; k++) {
			if (showOptions1.get(k).equalsIgnoreCase(expectedResult[k])) {
				appLog.info("Show Drop Down Value matched : " + expectedResult[k]);
			} else {
				appLog.info("Show Drop Down Value not matched : " + expectedResult[k]);
				sa.assertTrue(false, "Show Drop Down Value not matched : " + expectedResult[k]);
			}
		}
		List<WebElement> rangeOptions = allOptionsInDropDrop(driver, getAlertHistoryPopupRangeDropdown(workspace, 60),
				"Show Drop Down");
		String[] expectedResult1 = { "All Time", "Today", "Last 7 Days", "Last 30 Days", "Last 60 Days", "Last 90 Days",
				"Year to Date" };
		List<String> rangeOptions1 = new ArrayList<String>();
		for (WebElement rangelist : rangeOptions) {
			rangeOptions1.add(rangelist.getText().trim());
		}

		for (int p = 0; p < expectedResult1.length; p++) {
			if (rangeOptions1.get(p).equalsIgnoreCase(expectedResult1[p])) {
				appLog.info("Range Drop Down Value matched : " + expectedResult1[p]);
			} else {
				appLog.info("Range Drop Down Value not matched : " + expectedResult1[p]);
				sa.assertTrue(false, "Range Drop Down Value not matched : " + expectedResult1[p]);
			}
		}			
		return sa;		
	}
		
	/**
	 * @author Parul Singh
	 * @param contactName
	 * @param activityType
	 * @param PageName
	 * @return true/false
	 */
	public boolean verifyAlerts(String contactName, String activityType, String PageName) {
		  List<String> lst = new ArrayList<String>();
		  String xpath = "//a[text()='" + contactName + "']/../../following-sibling::span";
		  // SoftAssert sa=new SoftAssert();
		  ThreadSleep(5000);
		  // List<WebElement> activityTypeAlert=FindElements(driver,
		  // "//a[text()='"+contactName+"']/../../following-sibling::span[3]",
		  // "Activity type");
		  if (PageName.equalsIgnoreCase("Home Page")) {
		   if(activityType.equalsIgnoreCase("Contact Profile Updated") || activityType.equalsIgnoreCase("Firm Profile Updated")){
		    xpath = xpath+"[3]/a";
		   }
		   List<WebElement> activityTypeAlert = driver.findElements(By.xpath(xpath));
		   if(!activityTypeAlert.isEmpty()){
		    for (int i = 0; i < activityTypeAlert.size(); i++) {
		     String alertType = "";
		     if(activityType.equalsIgnoreCase("Contact Profile Updated") || activityType.equalsIgnoreCase("Firm Profile Updated")){
		      alertType = activityTypeAlert.get(i).getAttribute("title").trim();
		     } else {
		      alertType = activityTypeAlert.get(i).getText().trim();
		     }
		     System.out.println(alertType);
		     if (alertType.contains(activityType)) {
		      appLog.info(activityType + " Alerts are displaying in Home Page");
		      return true;
		     } else {
		      appLog.info(activityType + " Alerts are not displaying in Home Page");
		     }
		    }
		   } else {
		    appLog.info(contactName+" related alert is not present in the Home Page Alert Section.");
		   }
		  } else if (PageName.equalsIgnoreCase("Deal Page")) {
		   ThreadSleep(5000);
		   if(activityType.equalsIgnoreCase("Contact Profile Updated") || activityType.equalsIgnoreCase("Firm Profile Updated")){
		    xpath = xpath+"[2]/a";
		   }
		   List<WebElement> activityTypeAlert = driver
		     .findElements(By.xpath(xpath));
		   if(!activityTypeAlert.isEmpty()){
		    for (int i = 0; i < activityTypeAlert.size(); i++) {
		     String alertType = "";
		     if(activityType.equalsIgnoreCase("Contact Profile Updated") || activityType.equalsIgnoreCase("Firm Profile Updated")){
		      alertType = activityTypeAlert.get(i).getAttribute("title").trim();
		     } else {
		      alertType = activityTypeAlert.get(i).getText().trim();
		     }
		     System.out.println(alertType);
		     if (alertType.contains(activityType)) {
		      appLog.info(activityType + "Alerts are displaying in Deal Page");
		      return true;
		     } else {
		      appLog.info(activityType + " Alerts are not displaying in Deal Page");
		     }
		    }
		   }else{
		    appLog.info(contactName+" related alert is not present in Deal Page.");

		   }
		  }
		  else if (PageName.equalsIgnoreCase("External Admin")) {
		   ThreadSleep(5000);
		   if(activityType.equalsIgnoreCase("Contact Profile Updated") || activityType.equalsIgnoreCase("Firm Profile Updated")){
		    xpath = xpath+"[2]/a";
		   }
		   List<WebElement> activityTypeAlert = driver
		     .findElements(By.xpath(xpath));
		   if(!activityTypeAlert.isEmpty()){
		    for (int i = 0; i < activityTypeAlert.size(); i++) {
		     String alertType = "";
		     if(activityType.equalsIgnoreCase("Contact Profile Updated") || activityType.equalsIgnoreCase("Firm Profile Updated")){
		      alertType = activityTypeAlert.get(i).getAttribute("title").trim();
		     } else {
		      alertType = activityTypeAlert.get(i).getText().trim();
		     }
		     if (alertType.contains(activityType)) {
		      appLog.info(activityType + "Alerts are displaying in external admin page");
		      return true;
		     } else {
		      appLog.info(activityType + " Alerts are not displaying in external admin page");
		     }
		    }
		   }else{
		    appLog.info(contactName+" related alert is not present in the external admin page");

		   }
		  }else if(PageName.equalsIgnoreCase("Contact Page")){
		   ThreadSleep(5000);
		   if(activityType.equalsIgnoreCase("Contact Profile Updated") || activityType.equalsIgnoreCase("Firm Profile Updated")){
		    xpath = xpath+"[3]/a";
		   }
		   List<WebElement> activityTypeAlert = driver
		     .findElements(By.xpath(xpath));
		   if(!activityTypeAlert.isEmpty()){
		    for (int i = 0; i < activityTypeAlert.size(); i++) {
		     String alertType = "";
		     if(activityType.equalsIgnoreCase("Contact Profile Updated") || activityType.equalsIgnoreCase("Firm Profile Updated")){
		      alertType = activityTypeAlert.get(i).getAttribute("title").trim();
		     } else {
		      alertType = activityTypeAlert.get(i).getText().trim();
		     }
		     if (alertType.contains(activityType)) {
		      appLog.info(activityType + "Alerts are displaying in Contact page");

		      return true;
		     } else {
		      appLog.info(activityType + " Alerts are not displaying in Contact page");
		     }
		    }
		   }else{
		    appLog.info(contactName+" related alert is not present in the Contact Page");

		   }
		  }
		  return false;
		 }
	
	/**
	 * @author Parul Singh
	 * @param activityType
	 * @param Document
	 * @param firm
	 * @param Contact
	 * @param Date
	 * @return true/false
	 */
	public boolean verifyAlertHistoryGrid(String activityType,String Document,String firm,String Contact,String Date){
		WebElement ele=null;
		if(activityType.equalsIgnoreCase("Firm Profile Updated") || activityType.equalsIgnoreCase("Contact Profile Updated")){
			ele=FindElement(driver, "//a[text()='"+activityType+"']/../..//span[4]/span[text()='"+firm+"']/../..//span[5]//a[text()='"+Contact+"']/../..//span[6]/div[text()='"+getSystemDate("M/dd/yyyy")+"' or text()='"+previousOrForwardDate(-1, "M/dd/yyyy")+"' or text()='"+getSystemDate("MM/dd/yyyy")+"' or text()='"+previousOrForwardDate(-1, "MM/dd/yyyy")+"']", "grid data", action.SCROLLANDBOOLEAN, 20);
		}		
		if(ele!=null){
			return true;
		} else {
			return false;
		}			
	}
	
	/**
	 * @author Parul Singh
	 * @param workspace
	 * @param pageNAme
	 * @param fundVariableName
	 * @return true/false
	 */
	public boolean alertRecordCountAtFundPage(Workspace workspace,PageName pageNAme,String fundVariableName){
		int recWithValue=0;
		String [] recvalue=null;
		if(click(driver,getAlertHistoryLink(workspace, pageNAme, 60), "Alert History Link", action.SCROLLANDBOOLEAN)){
		if(getAlertHistoryPopupRecordLabelWithValue(workspace, 60)!=null){
				recvalue =getAlertHistoryPopupRecordLabelWithValue(workspace,10).getText().trim().split(":");
				recWithValue=Integer.parseInt(recvalue[1].trim());	
				if(ExcelUtils.writeData(recWithValue, "FilePath",excelLabel.FundName, fundVariableName, excelLabel.FundsPageALertCount)){
					return true;
				}else {
					appLog.error("Not able to write Fund Alert Count in Filepath excel sheet");
				}
		}else {
			appLog.error("Fund Alert Count is  not visible so cannot get fund page alert count");
		}
		}else{
			appLog.info("Not able to click on alert history link"); 			
		}
		return false;
	}
	
	/**
	 * @author Akul Bhutani
	 * @param fileNameAndPath
	 * @param scroll
	 * @return true/false
	 */
	public List<String> verifyDocumentInDuplicateDocManageApprovalPopup(String fileNameAndPath, boolean scroll) {
		List<String> notFound = new ArrayList<String>();
		String fileNameAndPathArr[] = fileNameAndPath.split("<break>");
		String fileName="",path = "";
		String docnamecol = "//span[@id='grid_DuplicateDocumentsFound']//span[contains(@class,'aw-hpanel-middle')]//span[contains(@id,'cell-0')]";
		String folderpathString = "//span[@id='grid_DuplicateDocumentsFound']//span[contains(@class,'aw-hpanel-middle')]//span[contains(@id,'cell-1')]";
		List<String> docsList = new ArrayList<String>();
		List<String> pathList= new ArrayList<String>();
		List<WebElement> temp_list;
		if (scroll) {
			docsList =scrollActiveWidgetforListofFiles(driver, getDuplicateDocScrollBox(60), By.xpath(docnamecol));
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)",getDuplicateDocScrollBox(60));
		appLog.info("scrolled to top of duplicate doc window");
		ThreadSleep(3000);
		pathList =scrollActiveWidgetforListofFiles(driver, getDuplicateDocScrollBox(60), By.xpath(folderpathString));
		System.err.println(docsList.size()+" "+pathList.size());
		}
		else {
			temp_list = FindElements(driver, docnamecol, "documents list on duplicate doc window");
			for (int i = 0;i<temp_list.size();i++) {
				docsList.add(temp_list.get(i).getText());
			}
			temp_list.clear();
			temp_list = FindElements(driver, folderpathString, "folderpathString list on duplicate doc window");
			for (int i = 0;i<temp_list.size();i++) {
				pathList.add(temp_list.get(i).getText());
			}
			
		}
		if((fileNameAndPathArr.length!=0)&&(!docsList.isEmpty() && (!pathList.isEmpty()))) {
			for (int j = 0;j<fileNameAndPathArr.length;j++) {
				fileName = fileNameAndPathArr[j].split("/")[0];
				path = fileNameAndPathArr[j].split("/")[1];

				for (int i = 0;i<docsList.size();i++) {
					if ((docsList.get(i).equals(fileName)) && (pathList.get(i).equals(path))) {
						appLog.info(fileName+" is successfully found with path "+path);
						break;
					}else {
						if(i==docsList.size()-1){
							appLog.error("Document Name"+fileName+" and its path "+path+" is not matched");
							notFound.add(fileName+"/"+path);
						}	
					}

				}

			}
		}else {
			appLog.error("Duplicate document name is not available in duplicate document grid so cannot verify document name and its path");
			notFound.add("Duplicate document name is not available in duplicate document grid so cannot verify document name and its path");
		}

		return notFound;
	}

	/**
	 * @author Ankit Jaiswal
	 * @param docsAndPath
	 * @return true/false
	 */
	public boolean verifyDocumentInFolderDeleteWindow(String docsAndPath) {
		String docsAndPathArr[] = docsAndPath.split("<break>");
		String fileName = "",path = "";
		boolean flag = false;
		List<WebElement> deldocslist = deleteDocsColumnList(30);
		List<WebElement> deletefolderpath = folderDeleteFolderPathList(30);
		if (docsAndPathArr.length!=0) {
		for (int i = 0;i<docsAndPathArr.length;i++) {
			flag = false;
			fileName = docsAndPathArr[i].split("/")[0];
			path = docsAndPathArr[i].split("/")[1];
			for (int j = 0;j<deldocslist.size();j++) {
				System.out.println(">>>>>----------------passing "+fileName+" "+path);
				System.err.println(">>>>------------------from webpage"+deldocslist.get(j).getText()+" "+deletefolderpath.get(j).getText());
				if ((deldocslist.get(j).getText().trim().equals(fileName))
				&& (deletefolderpath.get(j).getText().trim().equals(path))){
					appLog.info("found "+fileName+" and path "+path+"on folder delete window");
					flag = true;
				}
			}
			if (flag == false) {
				appLog.error(fileName+" and "+path+" could not be found in same row");
				sa.assertTrue(false, fileName+" and "+path+" could not be found in same row");
			}
		}
		return true;
		}
		else {
			appLog.error("docs and path array is empty so cannot check data on folder delete window");
			return false;
		}
	}
	
	/**
	 * @author Akul Bhutani
	 * @param li
	 * @return true/false
	 */
	public boolean printAllElementsInList(List<String> li) {
		if (!li.isEmpty()) {
			for (int i = 0;i<li.size();i++) {
				System.err.println(li.get(i));
			}
			return true;
		}
		return false;
	}

	/**
	 * @author Akul Bhutani
	 * @param workspace
	 * @param institutionsOrLP
	 * @param pathOfFolder
	 * @param files
	 * @param folderName
	 * @param ft
	 * @param updateIgnore
	 * @return true/false
	 */
	public boolean verifyDuplicateWindowWhileUploading(Workspace workspace,String institutionsOrLP, String pathOfFolder, String files, String folderName,FolderType ft, UpdateIgnore updateIgnore) {
		String arr[] = files.split("<break>");
		String parentID = switchOnWindow(driver);
		boolean flag = true;
		if (parentID!=null) {
			if (ft == FolderType.Standard) {
				click(driver, getMultipleInstituionRadioButton(30), "mulitple institution radio button", action.BOOLEAN);
				if (selectMultipleInstitutionOrLP(institutionsOrLP, workspace, 30)) {
					if (click(driver,getUploadNextButton(workspace, 30), "next button on upload window", action.SCROLLANDBOOLEAN) ) {
					}

					else {
						appLog.error("upload next button is not clickable");
						sa.assertTrue(false, "upload next button is not clickable");
					}
				}
				else {
					appLog.error("could not select multitle institutions 1 and 2");
					sa.assertTrue(false, "could not select multitle institutions 1 and 2");
				}
			}
			if ( dragDropFiles(pathOfFolder, "DropLoc.jpg")) {
				if (click(driver,  getUploadSaveButton(60), "upload save button", action.BOOLEAN)) {
					if ( getDuplicateDocumentsHeadText(60).getText().trim().equals("Duplicate Documents")) {
						appLog.info("duplicate documents heading is successfully verified");
					}
					else {
						appLog.error("duplicate documents heading is not present");
						sa.assertTrue(false, "duplicate documents heading is not present");
						flag = false;
					}
					if ( getDuplicateDocumentsSubHeadText(60).getText().trim().equals("Duplicate Documents")) {
						appLog.info("duplicate documents sub heading is successfully verified");
					}
					else {
						appLog.error("duplicate documents sub heading is not present");
						sa.assertTrue(false, "duplicate documents sub heading is not present");
						flag = false;
					}
					if ( getFolderLocationSubHead(60).getText().trim().equals("Folder Location")) {
						appLog.info("folder location sub heading is successfully verified");
					}
					else {
						appLog.error("folder location sub heading is not visible");
						sa.assertTrue(false, "folder location sub heading is not visible");
						flag = false;
					}
					if ( getDuplicateDocumentsTextUnderHeading(60).getText().trim().equals(FundsPageErrorMessage.duplicateDocumentsTextUnderHeading)) {
						appLog.info("text present to ask how to handle duplicate documents is displayed successfully");
					}
					else {
						appLog.error("text present to ask how to handle duplicate documents is displayed successfully");
					}
					if ( getUpdateAllButton(60)!=null) {
						appLog.info("update all button is present");
					}
					else {
						appLog.error("update all button is not present on duplicate document window");
						sa.assertTrue(false, "update all button is not present on duplicate document window");
						flag = false;
					}
					if ( getIgnoreAllButton(60)!=null) {
						appLog.info("ignore all button is successfully present");
					}
					else {
						appLog.error("ignore all button is not present on duplicate docuemnts heading");
						sa.assertTrue(false, "ignore all button is not present on duplicate docuemnts heading");
						flag = false;
					}
					int size = getDuplicateDocumentValueList("duplicate documents",30).size();
					boolean file_flag=false;

					
					for (int j = 0;j<arr.length;j++) {
						System.err.println("files passed "+j);
						file_flag = false;
						for (int i = 0;i<size;i++) {
							System.err.println("files on page "+i);
							if ((ft==FolderType.Common)||(ft==FolderType.Shared)) {
								if ( getDuplicateDocumentValueList("duplicate documents",30).get(i).getText().trim().equals(arr[j])
										&&(getDuplicateDocumentValueList("folder location", 30).get(i).getText().trim().contains(M9FundName1 + " > "+folderName)))  {
									appLog.info(arr[j] + " is found duplicate successfully according to alert window");
									file_flag = true;
									break;
								}
							}
							else if(ft == FolderType.Standard) {
								if (workspace == Workspace.FundraisingWorkspace) {
									if ( getDuplicateDocumentValueList("duplicate documents",30).get(i).getText().trim().equals(arr[j])
											&&(getDuplicateDocumentValueList("folder location", 30).get(i).getText().trim().contains(M9FundName1 + " > "+M9Institution1+" > "+folderName)))  {
										appLog.info(arr[j] + " for " + M9Institution1+" is found duplicate successfully according to alert window");
										file_flag = true;
									}
									if ( getDuplicateDocumentValueList("duplicate documents",30).get(i).getText().trim().equals(arr[j])
											&&(getDuplicateDocumentValueList("folder location", 30).get(i).getText().trim().contains(M9FundName1 + " > "+M9Institution2+" > "+folderName)))  {
										appLog.info(arr[j] + " for " + M9Institution2+" is found duplicate successfully according to alert window");
										file_flag = true;
									}
								}
								else if(workspace == Workspace.InvestorWorkspace) {
									if ( getDuplicateDocumentValueList("duplicate documents",30).get(i).getText().trim().equals(arr[j])
											&&(getDuplicateDocumentValueList("folder location", 30).get(i).getText().trim().contains(M9FundName1 + " > "+M9Institution1+" > "+M9LimitedPartner1+" > "+folderName)))  {
										appLog.info(arr[j] + " for " + M9Institution1+" is found duplicate successfully according to alert window");
										file_flag = true;
									}
									if ( getDuplicateDocumentValueList("duplicate documents",30).get(i).getText().trim().equals(arr[j])
											&&(getDuplicateDocumentValueList("folder location", 30).get(i).getText().trim().contains(M9FundName1 + " > "+M9Institution2+" > "+M9LimitedPartner2+" > "+folderName)))  {
										appLog.info(arr[j] + " for " + M9Institution2+" is found duplicate successfully according to alert window");
										file_flag = true;
									}
								}
							}


						}
						if (file_flag == false) {
							appLog.error("could not find file "+arr[j]);
							sa.assertTrue(false, "could not find file "+arr[j]);
						}
					}
					if (updateIgnore == UpdateIgnore.Update) {
						WebElement ele= BaseLib.edriver.findElement(By.cssSelector("#lnkReplaceAll"));
						try{
							scrollDownThroughWebelement(driver, ele, "update all");
							ele.click();
							appLog.info("clicked on update all");
						}catch(Exception e){
							appLog.error("Not able to click on update all");
							BaseLib.sa.assertTrue(false, "Not able to click on update all");
						}

						//click(driver,getUpdateAllButton(30), "Update All Button", action.BOOLEAN);
						if(isAlertPresent(driver)){
							String alertText = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
							switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
							if(alertText.contains(FundsPageErrorMessage.DocumentUploadSuccessMsg)){
								appLog.info("Upload success msg verified successfully.");
							} else {
								appLog.error("Upload successfull alert message is not verified.Expected: Document(s) uploaded successfully.\tActual: "+alertText);
								BaseLib.sa.assertTrue(false,"Upload successfull alert message is not verified.Expected: Document(s) uploaded successfully.\tActual: "+alertText);
								flag = false;
							}
						}
					}
					else {
						click(driver, getIgnoreAllButton(30), "Ignore All Button", action.BOOLEAN);

					}

				}
			}
			driver.switchTo().window(parentID);
		}

		else {
			flag = false;
		}
		return flag;
	}

	
	/**
	 * @author Ankur Rana
	 * @param path
	 * @param timeOut
	 * @return true/false
	 */
	public boolean verifyFolderPath(String path, int timeOut) {
		String path1[] = path.split("/");
		List<String> notFoundFolders = new ArrayList<String>();
		boolean found = true;
		String xpath = "//span[contains(text(),'All Folders')]/../../../ul/li/div//label[contains(text(),'" + path1[0] + "')]";
		for (int i = 1; i <= path1.length; i++) {
			if (FindElement(driver, xpath, "Folder: " + path1[i - 1], action.BOOLEAN, timeOut) != null) {
				// appLog.info("Folder Found: "+path1[i-1]);
			} else {
				// appLog.info("Folder Not Found: "+path1[i-1]);
				found = false;
				notFoundFolders.add(path1[i - 1]);
			}
			if (i != path1.length)
				xpath = xpath + "/../../../following-sibling::ul//label[contains(text(),'" + path1[i] + "')]";
		}
		if (!found) {
			for (int k = 0; k < notFoundFolders.size(); k++) {
				if (k == 0)
					appLog.info("List of not found folder of path \"" + path + "\" are: ");
				appLog.info(notFoundFolders.get(k));
			}
		} else {
			appLog.info("Folder path is verified: " + path);
		}
		return found;
	}
	
	/**
	 * @author Ankur Rana
	 * @param path
	 * @param timeOut
	 * @return true/false
	 */
	public boolean verifyFolderStructure(Object path, int timeOut) {
		boolean flag = true;
		if(path instanceof Map){
			Set<String> paths = ((Map) path).keySet();
			Iterator<String> i = paths.iterator();
			while (i.hasNext()) {
				String string = (String) i.next();
				if (verifyFolderPath(string, timeOut)) {
					appLog.info("Folder path successfully found: " + string);
				} else {
					appLog.info("Folder path not found: " + string);
					flag = false;
				}
			}
			
		} else {
			if (verifyFolderPath(path.toString(), timeOut)) {
				appLog.info("Folder path successfully found: " + path.toString());
			} else {
				appLog.info("Folder path not found: " + path.toString());
				flag = false;
			}
		}
		if (!flag) {
			appLog.info("Folder Structure is not verified.");
		}
		return flag;
	}
	
	/**
	 * @author Ankur Rana
	 * @param path
	 * @param institutionName
	 * @param limitedPartner
	 * @param fundName
	 * @param PageName
	 * @param Workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean verifyFolderPathBulk (String path, String institutionName, String limitedPartner, String fundName, PageName PageName, Workspace Workspace, int timeOut) {
		String workspaceSelector="";
		boolean found = true;
		boolean flag = false;
		String middleXpath = "/../../following-sibling::ul/li/div//span[text()='";
		if(Workspace!=null){
			if(PageName.toString().equalsIgnoreCase("FundsPage") && Workspace.toString().equalsIgnoreCase("FundraisingWorkspace")){
				workspaceSelector = "//div[@id='frworkspace']";
				if(institutionName!=null) {
					middleXpath = "/../../../following-sibling::ul/li/div//span[text()='";
				}
			} else if (PageName.toString().equalsIgnoreCase("FundsPage") && Workspace.toString().equalsIgnoreCase("InvestorWorkspace")) {
				workspaceSelector = "//div[@id='invworkspace']";
				if(limitedPartner!=null) {
					middleXpath = "/../../../following-sibling::ul/li/div//span[text()='";
				}
			} else if((PageName.toString().equalsIgnoreCase("InstitutionsPage") || PageName.toString().equalsIgnoreCase("ContactsPage")) && Workspace.toString().equalsIgnoreCase("FundraisingWorkspace")){
				workspaceSelector = "//div[@id='divFrWorkspace']";
			} else if ((PageName.toString().equalsIgnoreCase("InstitutionsPage") || PageName.toString().equalsIgnoreCase("ContactsPage")) && Workspace.toString().equalsIgnoreCase("InvestorWorkspace")) {
				workspaceSelector = "//div[@id='Investorgrid_div']";
			}
		}
		String startingXpath=workspaceSelector+"//span[contains(text(),'All Folders')]/../../../ul/li/div//span[text()='";
		String endingXpath="']";
		String combinedXpath = "";
		if(institutionName!=null){
			if(limitedPartner!=null){
				path =institutionName+"/"+limitedPartner+"/"+path;
			} else {
				path =institutionName+"/"+path;
			}
		}else if(limitedPartner!=null && (PageName.toString().equalsIgnoreCase("CommitmentsPage")||PageName.toString().equalsIgnoreCase("InstitutionsPage")||PageName.toString().equalsIgnoreCase(PageName.CurrentInvestmentPgae.toString()))){
			path = limitedPartner+"/"+path;
		}
		if(PageName.toString().equalsIgnoreCase("ContactsPage")||PageName.toString().equalsIgnoreCase("InstitutionsPage")){
			path = fundName+"/"+path;
		}
		String[] folderToFind = path.split("/");
		String xpathArray[] = new String[15];
		for(int i = 0 ; i < folderToFind.length ; i++){
			if(i==0){
				combinedXpath = startingXpath+folderToFind[i]+endingXpath;
			} else {
				combinedXpath += middleXpath + folderToFind[i] +endingXpath;
				middleXpath = "/../../following-sibling::ul/li/div//span[text()='";
			}
			xpathArray[i]=combinedXpath;
			if(FindElement(driver, combinedXpath, folderToFind[i]+" :Folder", action.BOOLEAN, 0)==null){
				try{
					if(click(driver, FindElement(driver, xpathArray[i-1], folderToFind[i-1]+" :Folder", action.BOOLEAN, timeOut), folderToFind[i-1]+" :Folder", action.SCROLLANDBOOLEAN)){
						if(FindElement(driver, combinedXpath, folderToFind[i]+" :Folder", action.BOOLEAN, 0)!=null){
							appLog.info("Folder found: "+folderToFind[i]);
						} else {
							appLog.error(folderToFind[i]+" :Folder not found.");
							found=false;
							notFoundFolder.add(path);
							break;
						}
					} else {
						appLog.error(folderToFind[i]+" :Folder not found.");
						notFoundFolder.add(path);
						found=false;
						break;
					}
				} catch (ArrayIndexOutOfBoundsException e){
					appLog.error(path+ " :Folder path is not verified.");
					notFoundFolder.add(path);
					found=false;
				}
			} else {
				appLog.info("Folder found: "+folderToFind[i]);
			}
		}
		if(found==false){
			if(isAlertPresent(driver)){
				String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
				switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
				if(msg.trim().toLowerCase().contains("error") || msg.trim().toLowerCase().contains("status") || msg.trim().toLowerCase().contains("code")){
					driver.navigate().refresh();
					if(getFrame(PageName, timeOut)!=null){
//						scrollDownThroughWebelement(driver, getDealRoomSection(30), "Deal room view.");
						switchToFrame(driver, 30, getFrame(PageName, timeOut));
					}
					if(verifyFolderPathdummy(path, institutionName, limitedPartner, fundName, PageName, Workspace, timeOut)){
						found=true;
					} else {
						found = false;
					}
				} else {
					String loc = screenshot(currentlyExecutingTC);
					appLog.error("Folder verification failed due to some intermittent issue. Kindly check the screenshot: "+loc);
					BaseLib.sa.assertTrue(false, "Folder verification failed due to some intermittent issue. Kindly check the screenshot: "+loc);
				}
			} else if (FindElement(driver, "//img[@class='poweredByImage']", null, action.BOOLEAN, 0)!=null){
				flag=true;
			} else if (FindElement(driver, "//img[@src='/resource/1511337238000/SiteSamples/img/clock.png']", null, action.BOOLEAN, 0)!=null){
				flag=true;
			} else if (FindElement(driver, "//img[@src='/resource/1511337238000/SiteSamples/img/warning.gif']", null, action.BOOLEAN, 0)!=null){
				flag=true;
			} else if (FindElement(driver, "//span[@class='title'][text()='Error: Error occurred while loading a Visualforce page.']", null, action.BOOLEAN, 0)!=null){
				flag=true;
			}
			if(flag){
				driver.navigate().refresh();
				if(getFrame(PageName, timeOut)!=null){
					switchToFrame(driver, 30, getFrame(PageName, timeOut));
				}
				if(verifyFolderPathdummy(path, institutionName, limitedPartner, fundName, PageName, Workspace, timeOut)){
					found=true;
				} else {
					found = false;
				}
			}
			
		}
		return found;
	}
	
	/**
	 * @author Ankur Rana
	 * @param path
	 * @param institutionName
	 * @param limitedPartner
	 * @param fundName
	 * @param PageName
	 * @param Workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean verifyFolderPathBulk2 (String path, String institutionName, String limitedPartner, String fundName, PageName PageName, Workspace Workspace, int timeOut) {
		String workspaceSelector="";
		boolean found = true;
		boolean flag = false;
		String middleXpath = "/../../following-sibling::ul/li/div//span[text()='";
		if(Workspace!=null){
			if(PageName.toString().equalsIgnoreCase("FundsPage") && Workspace.toString().equalsIgnoreCase("FundraisingWorkspace")){
				workspaceSelector = "//div[@id='frworkspace']";
				if(institutionName!=null) {
					middleXpath = "/../../../following-sibling::ul/li/div//span[text()='";
				}
			} else if (PageName.toString().equalsIgnoreCase("FundsPage") && Workspace.toString().equalsIgnoreCase("InvestorWorkspace")) {
				workspaceSelector = "//div[@id='invworkspace']";
				if(limitedPartner!=null) {
					middleXpath = "/../../../following-sibling::ul/li/div//span[text()='";
				}
			} else if((PageName.toString().equalsIgnoreCase("InstitutionsPage") || PageName.toString().equalsIgnoreCase("ContactsPage")) && Workspace.toString().equalsIgnoreCase("FundraisingWorkspace")){
				workspaceSelector = "//div[@id='divFrWorkspace']";
			} else if ((PageName.toString().equalsIgnoreCase("InstitutionsPage") || PageName.toString().equalsIgnoreCase("ContactsPage")) && Workspace.toString().equalsIgnoreCase("InvestorWorkspace")) {
				workspaceSelector = "//div[@id='Investorgrid_div']";
			}
		}
		String startingXpath=workspaceSelector+"//span[contains(text(),'All Folders')]/../../../ul/li/div//span[text()='";
		String endingXpath="']";
		String combinedXpath = "";
		if(institutionName!=null){
			if(limitedPartner!=null){
				path =institutionName+"/"+limitedPartner+"/"+path;
			} else {
				path =institutionName+"/"+path;
			}
		}else if(limitedPartner!=null && (PageName.toString().equalsIgnoreCase("CommitmentsPage")||PageName.toString().equalsIgnoreCase("InstitutionsPage")||PageName.toString().equalsIgnoreCase(PageName.CurrentInvestmentPgae.toString()))){
			path = limitedPartner+"/"+path;
		}
		if(PageName.toString().equalsIgnoreCase("ContactsPage")||PageName.toString().equalsIgnoreCase("InstitutionsPage")){
			path = fundName+"/"+path;
		}
		String[] folderToFind = path.split("/");
		List<String> xpathArray = new ArrayList<String>();
		for(int i = 0 ; i < folderToFind.length ; i++){
			if(i==0){
				combinedXpath = startingXpath+folderToFind[i]+endingXpath;
			} else {
				combinedXpath += middleXpath + folderToFind[i] +endingXpath;
				middleXpath = "/../../following-sibling::ul/li/div//span[text()='";
			}
			xpathArray.add(combinedXpath);
		}
		if(FindElement(driver, xpathArray.get(xpathArray.size()-1), folderToFind[folderToFind.length-1]+" :Folder", action.BOOLEAN, 0)==null){
			try{
				System.err.println("inside first if");
				if(click(driver, FindElement(driver, xpathArray.get(xpathArray.size()-2), folderToFind[folderToFind.length-2]+" :Folder", action.BOOLEAN, timeOut), folderToFind[folderToFind.length-2]+" :Folder", action.SCROLLANDBOOLEAN)){
					if(FindElement(driver, xpathArray.get(xpathArray.size()-1), folderToFind[folderToFind.length-1]+" :Folder", action.BOOLEAN, 0)!=null){
						appLog.info("Folder found: "+folderToFind[folderToFind.length-1]);
					} else {
						appLog.error(folderToFind[folderToFind.length-1]+" :Folder not found.");
						found=false;
						notFoundFolder.add(path);
					}
				} else {
					appLog.error(folderToFind[folderToFind.length-2]+" :Folder not found.");
					notFoundFolder.add(path);
					found=false;
				}
			} catch (ArrayIndexOutOfBoundsException e){
				appLog.error(path+ " :Folder path is not verified.");
				notFoundFolder.add(path);
				found=false;
			}
		} else {
			System.err.println("inside first else");
			appLog.info("Folder found: "+folderToFind[folderToFind.length-1]);
		}
		if(found==false){
			if(isAlertPresent(driver)){
				String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
				switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
				if(msg.trim().toLowerCase().contains("error") || msg.trim().toLowerCase().contains("status") || msg.trim().toLowerCase().contains("code")){
					driver.navigate().refresh();
					if(getFrame(PageName, timeOut)!=null){
//						scrollDownThroughWebelement(driver, getDealRoomSection(30), "Deal room view.");
						switchToFrame(driver, 30, getFrame(PageName, timeOut));
					}
					if(verifyFolderPathdummy(path, institutionName, limitedPartner, fundName, PageName, Workspace, timeOut)){
						found=true;
					} else {
						found = false;
					}
				} else {
					String loc = screenshot(currentlyExecutingTC);
					appLog.error("Folder verification failed due to some intermittent issue. Kindly check the screenshot: "+loc);
					BaseLib.sa.assertTrue(false, "Folder verification failed due to some intermittent issue. Kindly check the screenshot: "+loc);
				}
			} else if (FindElement(driver, "//img[@class='poweredByImage']", null, action.BOOLEAN, 0)!=null){
				flag=true;
			} else if (FindElement(driver, "//img[@src='/resource/1511337238000/SiteSamples/img/clock.png']", null, action.BOOLEAN, 0)!=null){
				flag=true;
			} else if (FindElement(driver, "//img[@src='/resource/1511337238000/SiteSamples/img/warning.gif']", null, action.BOOLEAN, 0)!=null){
				flag=true;
			} else if (FindElement(driver, "//span[@class='title'][text()='Error: Error occurred while loading a Visualforce page.']", null, action.BOOLEAN, 0)!=null){
				flag=true;
			}
			if(flag){
				driver.navigate().refresh();
				if(getFrame(PageName, timeOut)!=null){
					switchToFrame(driver, 30, getFrame(PageName, timeOut));
				}
				if(verifyFolderPathdummy(path, institutionName, limitedPartner, fundName, PageName, Workspace, timeOut)){
					found=true;
				} else {
					found = false;
				}
			}
			
		}
		return found;
	}
	
	public boolean selectAccountFromBuildStep3of3(String institutionOrLPName, String checkProgressMsg, String gmailEmailID, String gmailPassword, String sendersEmailID, String receiversEmailID, String mailSubject, Workspace workspace, int timeOut){
		System.err.println("Going to break");
		String institutionOrLPNamesToBeSelected[] = institutionOrLPName.split("<break>");
		System.err.println("break successfull.");
		boolean flag = true;
		System.err.println(institutionOrLPNamesToBeSelected.length);
		for(int i = 0 ; i < institutionOrLPNamesToBeSelected.length ; i++){
//			if(selectInstitutionOrLP(institutionOrLPName, workspace, timeOut)){
//				if(click(driver, getInvestorAddedConfirmationCloseButton(workspace, timeOut), "Investor Addition confirmation Close Button", action.BOOLEAN));
//			}
			try{
				if (setFieldValueOnManageInvestor("Account:Legal Name", 1, workspace,timeOut)) {
					if (setOperatorValueOnManageInvestor("equals", 1,workspace,timeOut)) {
						if (setCriterionValueOnManageTarget("textbox", 1, institutionOrLPNamesToBeSelected[i].split("/")[0], "Account:Legal Name",workspace,timeOut)) {
							click(driver, getManageInvestorFilterApplyButton(workspace, 0), "Apply Button", action.BOOLEAN);
							System.err.println("inside for");
							System.err.println(institutionOrLPNamesToBeSelected[i]);
							WebElement ele = null;
							if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
								ele = getInstituionCheckBoxOn3Of3(institutionOrLPNamesToBeSelected[i], workspace, timeOut);
							} else {
								ele = getLimitedPartnerCheckBox(institutionOrLPNamesToBeSelected[i].split("/")[0], institutionOrLPNamesToBeSelected[i].split("/")[1], workspace, timeOut);
							}
							scrollDownThroughWebelement(driver, ele, "Investor Name '"+institutionOrLPNamesToBeSelected[i]+"'");
							if(!isSelected(driver, ele, institutionOrLPNamesToBeSelected[i]+" check box")){
								ThreadSleep(1000);
								if(click(driver, ele, institutionOrLPNamesToBeSelected[i]+" Institution Check Box.", action.BOOLEAN)){
									appLog.info("Target Account '"+institutionOrLPNamesToBeSelected[i]+"' Selected Successfully");
									if(isAlertPresent(driver)){
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if(checkProgressMsg!=null&&checkProgressMsg.equalsIgnoreCase("Yes"))
											if(msg.equalsIgnoreCase(FundsPageErrorMessage.YourRequestInProgressErrorMsg)){
												appLog.info(msg+" :Message is verfiied successfully.");
											} else {
												appLog.error("Progress Message is not verfiied.Expexcted: "+FundsPageErrorMessage.YourRequestInProgressErrorMsg+"\tActual: "+msg);
												BaseLib.sa.assertTrue(false,"Progress Message is not verfiied.Expexcted: "+FundsPageErrorMessage.YourRequestInProgressErrorMsg+"\tActual: "+msg);
											}
										if(msg.contains("email") && msg.contains("progress") && msg.contains("request")){
											for(int j = 0 ; j <= 100 ; j++)
												if(EmailLib.mailReceived(gmailEmailID, gmailPassword, sendersEmailID, receiversEmailID, mailSubject, institutionOrLPNamesToBeSelected[i])){
													System.out.println("\n\n\nFound\n\n\n");
													break;
												} else {
													System.out.println("Mail is not found, Will Check for mail again.");
													if(j>=100){
														appLog.error("Mail is not received.");
														BaseLib.sa.assertTrue(false,"Mail is not received for account: "+institutionOrLPNamesToBeSelected[i]);
														return false;
													}
												}
											click(driver, getInvestorAddedConfirmationCloseButton(workspace, timeOut), "close button", action.BOOLEAN);
											return true;
										} else {
											appLog.info("Unexpected alert found with message: "+msg);
											if(msg.contains("180") && msg.contains("investor") && msg.contains("select")){
												appLog.error("Cannot add more investor.");
												break;
											} // continue from here
											return false;
										}
									} else if(FindElement(driver, "//span[@class='title'][text()='Error: Time limit exceeded']", "Visiual force timeout error", action.BOOLEAN, 0)!=null){
										String screenshotLoc = screenshot(currentlyExecutingTC);
										appLog.info("Visual Force failure screenshot: "+screenshotLoc);
										driver.navigate().refresh();
										return false;
									} else if(getInvestorAddedConfirmationCloseButton(workspace, timeOut)!=null){
										click(driver, getInvestorAddedConfirmationCloseButton(workspace, timeOut), "close button", action.BOOLEAN);
										return true;
									} else {
										return false;
									}
								} else {//span[@class='title'][text()='Error: Time limit exceeded']
									if(FindElement(driver, "//span[@class='title'][text()='Error: Time limit exceeded']", "Visiual force timeout error", action.BOOLEAN, 10)!=null){
										appLog.error("Error: Time limit exceeded");
									} else {
										appLog.error("Target Account '"+institutionOrLPNamesToBeSelected[i]+"' is not selected.");
										BaseLib.sa.assertTrue(false,"Target Account '"+institutionOrLPNamesToBeSelected[i]+"' is not selected.");
									}
								}
								
							} else {
								return true;
							}
							
						} else {
							appLog.error("No abe to set criterion value for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
							sa.assertTrue(false,"Not able to set criterion value  for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
						}
					} else {
						appLog.error("Not able to set operator value for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
						sa.assertTrue(false,"Not able to set operator value  for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
					}
				} else {
					appLog.error("Not able to set field value for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
					sa.assertTrue(false,"Not able to set field value  for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
				}
				
			} catch (Exception e){
				e.printStackTrace();
				appLog.error(e.getStackTrace());
			}
		}
		return false;
	}
	/**
	 * @author Ankit Jaiswal
	 * @param noOfUniqueDoc
	 * @param noOfDocViews
	 * @param noOfDocDownload
	 * @param noOfDocNotViewNotDownload
	 * @param contactGrantedAccess
	 * @param contactNotViewedAnyDoc
	 * @return List<String>
	 */
	public List<String> verifyIPAnalyticsTargetValues(int noOfUniqueDoc, int noOfDocViews, int noOfDocDownload,
			int noOfDocNotViewNotDownload, int contactGrantedAccess,int contactNotViewedAnyDoc) {
		List<String> lst = new ArrayList<String>();
		if (noOfUniqueDoc != -1) {
			String result = getIPAnalyticsUniqueDocumentvalue(60).getText().trim();
			if (result != null) {
				int a = Integer.parseInt(result);
				if (noOfUniqueDoc == a) {
					appLog.info("# of Unique Document Value is matched : " + result);
				} else {
					appLog.info("# of Unique Document Value is not matched. Expected: " + noOfUniqueDoc + "\t Actual : "
							+ result);
					lst.add("No of Unique Document Value is not matched. Expected: " + noOfUniqueDoc + "\t Actual : "
							+ result);
				}
			}
		}
		if (noOfDocViews != -1) {
			String result = getIPAnalyticsDocumentViewsvalue(30).getText().trim();
			if (result != null) {
				int a = Integer.parseInt(result);
				if (noOfDocViews == a) {
					appLog.info("# of Document Views Value is matched : " + result);
				} else {
					appLog.info("# of Document Views Value is not matched. Expected: " + noOfDocViews + "\t Actual : "
							+ result);
					lst.add("No of Document Views Value is not matched. Expected: " + noOfDocViews + "\t Actual : "
							+ result);
				}
			}
		}
		if (noOfDocDownload != -1) {
			String result = getIPAnalyticsDocumentDownload(30).getText().trim();
			if (result != null) {
				int a = Integer.parseInt(result);
				if (noOfDocDownload == a) {
					appLog.info("# of Document Download Value is matched : " + result);
				} else {
					appLog.info("# of Document Download Value is not matched. Expected: " + noOfDocDownload
							+ "\t Actual : " + result);
					lst.add("No of Document Download Value is not matched. Expected: " + noOfDocDownload
							+ "\t Actual : " + result);
				}
			}
		}
		if (noOfDocNotViewNotDownload != -1) {
			String result = getIPAnalyticsDocumentNotViwedorDownloadLink(30).getText().trim();
			if (result != null) {
				int a = Integer.parseInt(result);
				if (noOfDocNotViewNotDownload == a) {
					appLog.info("# of Document not Viewed or Downloaded Value is matched : " + result);
				} else {
					appLog.info("# of Document not Viewed or Downloaded Value is not matched. Expected: "
							+ noOfDocNotViewNotDownload + "\t Actual : " + result);
					lst.add("No of Document not Viewed or Downloaded Value is not matched. Expected: "
							+ noOfDocNotViewNotDownload + "\t Actual : " + result);
				}
			}
		}
		if (contactGrantedAccess != -1) {
			String result = getIPAnalyticsContactGrantedAccessvalue(30).getText().trim();
			if (result != null) {
				int a = Integer.parseInt(result);
				if (contactGrantedAccess == a) {
					appLog.info("# of Contact Granted Access Value is matched : " + result);
				} else {
					appLog.info("# of Contact Granted Access Value is not matched. Expected: " + contactGrantedAccess
							+ "\t Actual : " + result);
					lst.add("No of Contact Granted Access Value is not matched. Expected: " + contactGrantedAccess
							+ "\t Actual : " + result);
				}
			}
		}
		if (contactNotViewedAnyDoc != -1) {
			String result = getIPAnalyticsnotViewedAnyDocumentLink(30).getText().trim();
			if (result != null) {
				int a = Integer.parseInt(result);
				if (contactNotViewedAnyDoc == a) {
					appLog.info("# of Contact Who have not Viewed Any Document Value is matched : " + result);
				} else {
					appLog.info("# of Contact Who have not Viewed Any Document Value is not matched. Expected: "
							+ contactNotViewedAnyDoc + "\t Actual : " + result);
					lst.add("No of Contact Who have not Viewed Any Document Value is not matched. Expected: "
							+ contactNotViewedAnyDoc + "\t Actual : " + result);
				}
			}
		}
		return lst;
	}
	/**
	 * @author Ankit Jaiswal
	 * @param workspace
	 * @return
	 */
	public List<String> readIPAnalyticsCount(Workspace workspace){
		List<String> result= new ArrayList<String>();
		if(click(driver, getIPAnalyticsIcon(workspace), "ip analytics icon", action.SCROLLANDBOOLEAN)) {
			String parentid = switchOnWindow(driver);
			if(parentid!=null) {
				if (getIPAnalyticsUniqueDocumentvalue(60)!=null) {
					String aa = getText(driver, getIPAnalyticsUniqueDocumentvalue(10), "unique document count", action.BOOLEAN);
					if (aa != "") {
						if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
							ExcelUtils.writeData(aa, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
							FRW_UniqueDocument=Integer.parseInt(aa);
						}else {
							ExcelUtils.writeData(aa, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
							INV_UniqueDocument=Integer.parseInt(aa);
						}
					}else {
						appLog.error("Not able to get no of unique document count so cannot read count in excel sheet");
						result.add("Not able to get no of unique document count so cannot read count in excel sheet");
					}
				}else {
					appLog.error("unique no of document count is not visible so cannot read and write count in excel sheet");
					result.add("unique no of document count is not visible so cannot read and write count in excel sheet");
				}
				if (getIPAnalyticsDocumentViewsvalue(10) != null) {
					String aa = getText(driver, getIPAnalyticsDocumentViewsvalue(10), "no of document view count", action.BOOLEAN);
						if (aa != "") {
							if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
								ExcelUtils.writeData(aa, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.FRW_Value);
								FRW_DocumentViews=Integer.parseInt(aa);
							}else {
								ExcelUtils.writeData(aa, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.INV_Value);
								INV_DocumentViews=Integer.parseInt(aa);
							}
					}else {
						appLog.error("Not able to get document view count so cannot read count in excel sheet");
						result.add("Not able to get document view count so cannot read count in excel sheet");
					}
				}else {
					appLog.error("document view count is not visible so cannot read and write count in excel sheet");
					result.add("document view count is not visible so cannot read and write count in excel sheet");
				}
				if (getIPAnalyticsDocumentDownload(10) !=null) {
					String aa = getText(driver, getIPAnalyticsDocumentDownload(10), "document download count", action.BOOLEAN);
					if (aa != "") {
						if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
							ExcelUtils.writeData(aa, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.FRW_Value);
							FRW_DocumentDownload=Integer.parseInt(aa);
						}else {
							ExcelUtils.writeData(aa, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.INV_Value);
							INV_DocumentDownload=Integer.parseInt(aa);
						}
					}else {
						appLog.error("Not able to get document download count so cannot read count in excel sheet");
						result.add("Not able to get document download count so cannot read count in excel sheet");
					}
				}else {
					appLog.error("document download count is not visible so cannot read and write count in excel sheet");
					result.add("document download count is not visible so cannot read and write count in excel sheet");
				}
				if (getIPAnalyticsDocumentNotViwedorDownloadLink(10) != null) {
					String aa = getText(driver, getIPAnalyticsDocumentNotViwedorDownloadLink(10), "document not viewed or download", action.BOOLEAN);
					if (aa != "") {
						if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
							ExcelUtils.writeData(aa, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_Value);
							FRW_DocumentNotViewedOrDownload=Integer.parseInt(aa);
						}else {
							ExcelUtils.writeData(aa, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_Value);
							INV_DocumentNotViewedOrDownload=Integer.parseInt(aa);
						}
					}else {
						appLog.error("Not able to get document not viewed or download count so cannot read count in excel sheet");
						result.add("Not able to get document not viewed or download count so cannot read count in excel sheet");
					}
				}else {
					appLog.error("document not viewed or download count is not visible so cannot read and write count in excel sheet");
					result.add("document not viewed or download count is not visible so cannot read and write count in excel sheet");
				}
				if (getIPAnalyticsContactGrantedAccessvalue(10) !=null) {
					String aa = getText(driver, getIPAnalyticsContactGrantedAccessvalue(10), "contact grant access", action.BOOLEAN);
					if (aa != "") {
						if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
							ExcelUtils.writeData(aa, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.FRW_Value);
							FRW_ContactGrantedAccess=Integer.parseInt(aa);
						}else {
							ExcelUtils.writeData(aa, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.INV_Value);
							INV_ContactGrantedAccess=Integer.parseInt(aa);
						}
					}else {
						appLog.error("Not able to get contact grant access count so cannot read count in excel sheet");
						result.add("Not able to get contact grant access count so cannot read count in excel sheet");
					}
				}else {
					appLog.error("contact grant access count is not visible so cannot read and write count in excel sheet");
					result.add("contact grant access count is not visible so cannot read and write count in excel sheet");
				}
				if (getIPAnalyticsnotViewedAnyDocumentLink(10) != null) {
					String aa = getText(driver, getIPAnalyticsnotViewedAnyDocumentLink(10), "contact who have not viewed any document", action.BOOLEAN);
					if (aa != "") {
						if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
							ExcelUtils.writeData(aa, "IPAnalytics", excelLabel.Statistics, "No of Contacts who have not Viewed any Document", excelLabel.FRW_Value);
							FRW_ContactNotViewedAnyDocument=Integer.parseInt(aa);
						}else {
							ExcelUtils.writeData(aa, "IPAnalytics", excelLabel.Statistics, "No of Contacts who have not Viewed any Document", excelLabel.INV_Value);
							INV_ContactNotViewedAnyDocument=Integer.parseInt(aa);
						}
					}else {
						appLog.error("Not able to get contact who have not viewed any document count so cannot read count in excel sheet");
						result.add("Not able to get ccontact who have not viewed any document count so cannot read count in excel sheet");
					}
				}else {
					appLog.error("contact who have not viewed any document count is not visible so cannot read and write count in excel sheet");
					result.add("contact who have not viewed any document count is not visible so cannot read and write count in excel sheet");
				}
				driver.close();
				driver.switchTo().window(parentid);
			}else {
				appLog.error("No new window is open so cannot read ip analytics count");
				result.add("No new window is open so cannot read ip analytics count");
			}
		}else {
			appLog.error("Not able to click on ip analytics icon so cannot read ip analytics count");
			result.add("Not able to click on ip analytics icon so cannot read ip analytics count");
		}
		return result;
		
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param contactName
	 * @param firmName
	 * @param activityCount
	 * @return SoftAssert
	 */
	public SoftAssert verifyMostActiveContactInIPAnalytics(String contactName, String firmName,
			String activityCount) {
		SoftAssert saaa = new SoftAssert();
		ThreadSleep(5000);
		if (click(driver, getIPAnalyticsMostActiveContactsLink(30), "Most Active Contact Link",
				action.SCROLLANDBOOLEAN)) {
			if (contactName != null && firmName != null && activityCount != null) {
				List<WebElement> contactNameList = getMostActiveContactNameList();
				List<WebElement> firmNameList = getMostActiveContactFirmNameList();
				String[] splitedContactnames = contactName.split("<break>");
				String[] splitedFirnName = firmName.split("<break>");
				String[] splitedActivityCount = activityCount.split("<break>");
				for (int i = 0; i < splitedContactnames.length; i++) {
					for (int j = 0; j < contactNameList.size(); j++) {
						String aa=contactNameList.get(j).getAttribute("text").trim();
						if (aa.contains(splitedContactnames[i])) {
							appLog.info("Contact Name is matched: " + splitedContactnames[i]);
							break;
						} else {
							if (j == contactNameList.size() - 1) {
								appLog.info("Contact Name is not matched: " + splitedContactnames[i]+"/t Actual Result: "+aa);
								saaa.assertTrue(false,"Contact Name is not matched: " + splitedContactnames[i]+"/t Actual Result: "+aa);

							}
						}
					}
				}
				for (int i = 0; i < splitedFirnName.length; i++) {
					for (int j = 0; j < firmNameList.size(); j++) {
						if (firmNameList.get(j).getText().trim().contains(splitedFirnName[i])) {
							appLog.info("Firm Name is matched: " + splitedFirnName[i]);
							break;
						} else {
							if (j == contactNameList.size() - 1) {
								appLog.info("Firm Name is not matched: " + splitedFirnName[i]);
								saaa.assertTrue(false,"Firm Name is not matched: " + splitedFirnName[i]);

							}
						}
					}
				}
				for (int i = 0; i < splitedContactnames.length; i++) {
					for (int j = 0; j < contactNameList.size(); j++) {
						if (contactNameList.get(j).getText().trim().contains(splitedContactnames[i])) {
							appLog.info("Contact Name is matched: " + splitedContactnames[i]);
							WebElement ele = isDisplayed(driver,
									FindElement(driver, "//a[text()='" + splitedContactnames[i] + "']/../../span[4]",
											"Most Active Contact Activity Count", action.BOOLEAN, 30),
									"visibility", 20, "Most Active Contact Activity Count");
							if (ele != null) {
								int count = Integer.parseInt(ele.getText().trim());
								int expcCount = Integer.parseInt(splitedActivityCount[i]);
								if (count == expcCount) {
									appLog.info("Most Active Contact " + splitedContactnames[i]
											+ " Activity Count is matched.");
									break;
								} else {
									appLog.info("Most Active Contact " + splitedContactnames[i]
											+ " Activity Count is not matched. Expected: " + expcCount + "\t Actual: "
											+ count);
									saaa.assertTrue(false,"Most Active Contact " + splitedContactnames[i]
											+ " Activity Count is not matched.Expected: " + expcCount + "\t Actual: "
											+ count);
								}
							} else {
								appLog.info("Most Active Contact " + splitedContactnames[i]
										+ " Activity Count is not available in grid.");
								saaa.assertTrue(false, "Most Active Contact " + splitedContactnames[i]
										+ " Activity Count is not available in grid.");
							}
						} else {
							if (j == contactNameList.size() - 1) {
								appLog.info("Contact Name is not matched: " + splitedContactnames[i]
										+ "So we can't match Activity Count.");
								saaa.assertTrue(false, "Contact Name is not matched: " + splitedContactnames[i]
										+ "So we can't match Activity Count.");
							}
						}
					}
				}
			} else {
				if (getMostActiveContactErrorMsg(10) != null) {
					String msg = getMostActiveContactErrorMsg(10).getText().trim();
					if (msg.contains(FundsPageErrorMessage.MostActiveContactErrorMsg)) {
						appLog.info("Error Message: " + msg);
						appLog.info("No data to display Error Message is verified.");
					} else {
						appLog.info("No data to display Error Message is not verified. Expected: "
								+ FundsPageErrorMessage.MostActiveContactErrorMsg + "\t Actual Result: " + msg);
						saaa.assertTrue(false, "No data to display Error Message is not verified. Expected: "
								+ FundsPageErrorMessage.MostActiveContactErrorMsg + "\t Actual Result: " + msg);
					}
				} else {
					appLog.info("No data to display error message is not visible on Most Active Contact pop up");
					saaa.assertTrue(false,"No data to display error message is not visible on Most Active Contact pop up");

				}
			}
			if (click(driver, getMostActiveContactCloseBtn(30), "Most Active Contact close button",
					action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on Most Active Contact pop up Close button");
			} else {
				appLog.info("Not able to click on Active Contact pop up Close button");
				saaa.assertTrue(false,"Not able to click on  Active Contact pop up Close button");
			}
		} else {
			appLog.info("Not able to Click on Most Active Contact Link.");
			saaa.assertTrue(false,"Not able to Click on Most Active Contact Link.");
		}
		return saaa;

	}

	/**
	 * @author Akul Bhutani
	 * @param inst
	 * @param fileName
	 * @param view
	 * @param remove
	 * @param firstWindow
	 * @param secondWindow
	 * @param acceptOrRejectRemovePopup
	 * @return SoftAssert true/false
	 */
	public SoftAssert verifyFileDist2Of2SuccessfullyAppliedUI(String inst, String fileName, boolean view, boolean remove,String firstWindow, String secondWindow, action acceptOrRejectRemovePopup) {
		SoftAssert softa = new SoftAssert();
		String allFiles = "//ul[@class='decimal']//li";
		String viewXpath = allFiles + "//a[1]";
		String removeXpath = allFiles + "//a[2]";
		Set<String> windows = new HashSet<String>();
		String thirdWindow = null;
		boolean flag = false;
		int iter=5;
		List<WebElement> allDocs = FindElements(driver, allFiles, "all files");
		List<WebElement> allViewLinks = new ArrayList<WebElement>();
		List<WebElement> allRemoveLinks = new ArrayList<WebElement>();
		for (int i = 0;i<allDocs.size();i++) {
			allViewLinks = FindElements(driver, viewXpath, "all files");
			allRemoveLinks = FindElements(driver, removeXpath, "all files");
		
			System.err.println("from page>>>>>>>>"+allDocs.get(i).getText().trim());
			System.out.println("passing>>>>>>>>>>"+inst+" ( "+inst+"_"+fileName+" )");
			if (allDocs.get(i).getText().trim().contains(inst+" ( "+inst+"_"+fileName+" )")) {
					softa.assertTrue(true, "successfully found "+fileName+" and inst "+inst);
				if (allViewLinks.get(i)!=null) {
					appLog.info("successfully found view link of "+fileName+" and inst "+inst);
				}
				else {
					appLog.error("could not find view link of "+fileName+" and inst "+inst);
					softa.assertTrue(false, "could not find view link of "+fileName+" and inst "+inst);
				}
				
				if (allRemoveLinks.get(i)!=null) {
					appLog.info("successfully found remove link of "+fileName+" and inst "+inst);
				}
				else {
					appLog.error("could not find remove link of "+fileName+" and inst "+inst);
					softa.assertTrue(false, "could not find remove link of "+fileName+" and inst "+inst);
				}
				
				flag = true;
				if (view) {
					iter = i;
					
				}
				if (remove) {
					iter = i;
				}
				break;
			}
		}
		if (view) {
			allViewLinks = FindElements(driver, viewXpath, "all files");
			if (click(driver, allViewLinks.get(iter), "view link for "+fileName, action.BOOLEAN)) {
				appLog.info("clicked on view link of "+fileName+" and inst "+inst);
				ThreadSleep(5000);
				
				windows = driver.getWindowHandles();
				if (windows!=null) {
					for (String temp_window:windows) {
						if (!temp_window.equalsIgnoreCase(firstWindow)) {
							if (!temp_window.equalsIgnoreCase(secondWindow)) {
								driver.switchTo().window(temp_window);
								thirdWindow = temp_window;
								break;
							}
						}
					}
				}
				if (thirdWindow!=null) {
					if (verifyDownloadFunctionalityFileDistributorWithoutClick()) {
						appLog.info("download and close button are successfully verified");
					}
					else {
						appLog.error("could not verify download and close button");
					}
					driver.close();
					driver.switchTo().window(secondWindow);
					}
				}
				else {
					appLog.error("could not click on view link of "+fileName+" and inst "+inst);
				}

			}
			if (remove) {
				List<WebElement> ele = BaseLib.edriver.findElements(By.cssSelector("ul.decimal a[title=Remove]"));
				ele.get(iter).click();
				//if (click(driver, ele.get(iter), "remove link", action.BOOLEAN)) {
					ThreadSleep(4000);
					if (isAlertPresent(driver)) {
						String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
						switchToAlertAndAcceptOrDecline(driver, 30, acceptOrRejectRemovePopup);
						if (msg.trim().equals(FundsPageErrorMessage.removeSuccessfullyAppliedText)) {
							appLog.info("alert text for remove link is successully verified");
						}
						else {
							appLog.error("alert text is incorrect after clicking remove link");
							sa.assertTrue(false, "alert text is incorrect after clicking remove link");
						}
					}
					else {
						appLog.error("no alert is present after clicking remove link");
						sa.assertTrue(false, "no alert is present after clicking remove link");
					}
				/*}
				else {
					appLog.error("could not clicking on remove link for "+iter);
					sa.assertTrue(false, "could not clicking on remove link for "+iter);
				}*/
			}
			if (flag == false) {
				appLog.error("could not find "+fileName+" for "+inst+" in applied UI window");
				softa.assertTrue(false, "could not find "+fileName);
			}
			return softa;
		}
	
	/**
	 * @author Ankit Jaiswal
	 * @param documentName
	 * @param Views
	 * @param workspace
	 * @return List<String>
	 */
	public List<String> verifyMostViewedDocumentInIPAnalytics(String documentName, String Views, Workspace workspace) {
		List<String> result = new ArrayList<String>();
		ThreadSleep(5000);
		String filesName=null;
		if (click(driver, getIPAnalyticsMostViewedDocumentLink(30), "Most View Document Link",
				action.SCROLLANDBOOLEAN)) {
			if (documentName != null && Views != null) {
				if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
					 filesName=ExcelUtils.readData("IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_DocumentsName);
				}else {
					filesName=ExcelUtils.readData("IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_DocumentsName);
				}
				if(filesName!=null) {
					String[] splitedFileName = filesName.split("<break>");
					int counter;
					Map<String, Integer> fileCount = new LinkedHashMap<String, Integer>();
					Set<String> uniqueFileName = new LinkedHashSet<String>();
					for (int i = 0; i < splitedFileName.length; i++) {
						uniqueFileName.add(splitedFileName[i]);
					}
					for (String a : uniqueFileName) {
						counter = 0;
						for (int j = 0; j < splitedFileName.length; j++) {
							if (a.equalsIgnoreCase(splitedFileName[j])) {
								counter++;
								fileCount.put(a, counter);
								continue;
							}
						}
					}
					int j = -1;
					List<WebElement> files = getMostViewedDocumentName();
					List<WebElement> count = getMostViewDocumentsViews(30);
					for (int i = 0; i < files.size(); i++) {
						String text = files.get(i).getAttribute("text").trim();
						System.out.println(text);
						int counts = Integer.parseInt(count.get(i).getText().trim());
						System.out.println(counts);
						for (String string : fileCount.keySet()) {
							String fileName=string;
							System.out.println("File in excel: "+fileName);
							j++;
							if(text.equalsIgnoreCase(fileName)) {
								if (fileCount.get(text) == counts) {
									appLog.info("Count of file '" + text + "' is matched successfully.");
								} else {
									result.add("Count of file '" + text + "' is not matched. Expected: " + fileCount.get(text)
									+ "\tActual: " + count);
								}
								break;
							} else {
								if(i==files.size()-1 && j==files.size()-1) {
									appLog.error(text+" File is not present in excel.");
									result.add(text+" File is not present in excel.");
								}
							}
						}
					}
					
				}else {
					appLog.error("file names is not available in IPAnalytics excelsheet so cannot verify file name");
					result.add("file names is not available in IPAnalytics excelsheet so cannot verify file name");
				}
				
			} else {
				if (getMostViewedDocumentErrorMsg(10) != null) {
					String msg = getMostViewedDocumentErrorMsg(10).getText().trim();
					if (msg.contains(FundsPageErrorMessage.MostViewedDocErrorMsg)) {
						appLog.info("No data to display Error Message is verified.");
					} else {
						appLog.info("No data to display Error Message is not verified. Expected:"
								+ FundsPageErrorMessage.MostViewedDocErrorMsg + "\t Actual Result: " + msg);
						result.add("No data to display Error Message is not verified. Expected:"
								+ FundsPageErrorMessage.MostViewedDocErrorMsg + "\t Actual Result: " + msg);
					}
				} else {
					appLog.info("No data to display error message is not visible on Most Viewed Document pop up");
					result.add("No data to display error message is not visible on Most Viewed Document pop up");
				}
			}
			if (click(driver, getMostviewedDocumentCloseBtn(10), "Most Viewed Document pop up Close button",
					action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on Most Viewed Document pop up Close button");
			} else {
				appLog.info("Not able to Click on Most Viewed Document pop up Close button");
				result.add("Not able to Click on Most Viewed Document pop up Close button");
			}
		} else {
			appLog.info("Not able to click on Most View Document Link.");
			result.add("Not able to click on Most View Document Link.");
		}
		return result;
	}
	
	public boolean inviteContact(String instutionOrLPName,String emailID,String folderpath,FolderType FolderType,String permission,String applyInvite,String sendInvitationMail,String contactAccessViewfolderName,Workspace workspace,String contactSearchKeyword,
	String accessGrantedContactCount,boolean freshContactAccess ) {
		String[] splitedEmailId=emailID.split(",");
		String [] spiltedInstitutionOrLP=null;
		int noofCount = 0;
		String ins=null;
		String lpName=null;
		switchToFrame(driver, 30, getFrame(PageName.FundsPage, 30));
		scrollDownThroughWebelement(driver, getWorkspaceSectionView(workspace, 30), workspace.toString()+" Section view");
		if(FolderType.toString().equalsIgnoreCase(FolderType.Standard.toString())) {
			if(instutionOrLPName!=null) {
				spiltedInstitutionOrLP=instutionOrLPName.split("/");
				if(spiltedInstitutionOrLP.length>1) {
					ins=spiltedInstitutionOrLP[0];
					lpName=spiltedInstitutionOrLP[1];
				}else {
					ins=spiltedInstitutionOrLP[0];
				}
				if(folderpath==null){
					if(lpName!=null){
						clickOnLimitedPartnerFolder(ins, lpName, workspace, 30);
					} else {
						clickOnInstituionFolder(ins, workspace, 30);
					}
				} else {
					if(verifyFolderPathdummy(folderpath,ins,lpName,null, PageName.FundsPage, workspace, 60)) {
						appLog.info("folder staructure is verified successfully: "+instutionOrLPName+"/"+folderpath);
					}else {
						appLog.error("folder structure is not verifed: "+instutionOrLPName+"/"+folderpath+" so cannot invite contact: "+emailID);
						switchToDefaultContent(driver);
						return false;
					}
				}
			}else {
				appLog.error("InstituionOrLP Name should not null when FolderType is Standard: "+instutionOrLPName);
				switchToDefaultContent(driver);
				return false;
			}
		}else {
			if(verifyFolderPathdummy(folderpath,null,null,null, PageName.FundsPage, workspace, 60)) {
				appLog.info("folder staructure is verified successfully: "+folderpath);
			}else {
				appLog.error("folder structure is not verifed: "+folderpath+" so cannot invite contact: "+emailID);
				switchToDefaultContent(driver);
				return false;
			}
		}		
		
			if(click(driver, getContactAccessIcon(workspace, 60), "Contact Access Icon of "+workspace, action.SCROLLANDBOOLEAN)) {
				ThreadSleep(2000);
				if(verifyContactAccessExpandCollapse(workspace)) {
					appLog.info(workspace+" contact access popUp is expanded successfully.");
					if(contactSearchKeyword!=null) {
						if(sendKeys(driver,getSearchTextBox(workspace, 60), contactSearchKeyword,workspace+" search text box", action.SCROLLANDBOOLEAN)) {
							appLog.info("enter the value in search text box : "+contactSearchKeyword);
							if(click(driver, getSearchBtn(workspace, 60), workspace+" search button", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on  "+workspace+" search button");
							}else {
								appLog.error("Not able to click on "+workspace+" search button so cannot invite contact: "+emailID);
								if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
									appLog.error("Not able to click on contact access cancel button");
								}
								switchToDefaultContent(driver);
								return false;
							}
						}else {
							appLog.error("Not able to pass value in "+workspace+" search text box:  "+contactSearchKeyword+" so cannot invite contact: "+emailID);
							if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
								appLog.error("Not able to click on contact access cancel button");
							}
							switchToDefaultContent(driver);
							return false;
						}
					}
						WebElement contactcheckBox=null;
						if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
								contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWFR_MA']//a[text()='"+emailID+"']/../..//input", emailID+" contact check box", action.SCROLLANDBOOLEAN, 30);
									scrollDownThroughWebelement(driver,contactcheckBox, "");
						}else if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
								contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWINV_MA']//a[text()='"+emailID+"']/../..//input", emailID+" contact check box", action.SCROLLANDBOOLEAN, 30);
									scrollDownThroughWebelement(driver,contactcheckBox, "");
						}
						if(contactcheckBox!=null) {
							ThreadSleep(3000);
							if(click(driver, contactcheckBox, emailID+"check box", action.BOOLEAN)) {
								appLog.info("clicked on contact check box : "+emailID);
								if (accessGrantedContactCount.equalsIgnoreCase("Yes")) {
									boolean flag= false;
									int rowNUm=0;
									int lastRow=ExcelUtils.getLastRow("Contacts");
									for(int j=0; j<=lastRow;j++){
										String s =ExcelUtils.readData("Contacts", j, 4);
										System.err.println(s+" "+emailID);
										if((s!=null && !s.isEmpty()) && s.equalsIgnoreCase(emailID)){
											System.err.println("inside if");
											flag=true;
											break;
										}
										rowNUm++;
									}
									if (flag==true && ExcelUtils.readData("Contacts", rowNUm, 35).equalsIgnoreCase("No Access")) {
										noofCount++;
										appLog.info("Contact Selected: " + noofCount);
									}
								}
								if(click(driver, getaddselectContactBtn(workspace, 30), workspace+" add select contact active button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on add selected contact active button in "+workspace);
									ThreadSleep(5000);
									List<WebElement> listofContact= getselectContactEmailIDList(workspace);
									if(!listofContact.isEmpty()) {
										for(int i=0; i<splitedEmailId.length;i++) {
											for(int j=0; j<listofContact.size();j++) {
												String contactemail=listofContact.get(j).getText().trim();
												if(contactemail.equalsIgnoreCase(splitedEmailId[i])) {
													appLog.info(splitedEmailId[i]+" contact is displaying in selected contact access grid");
													break;
												}else {
													if(j==listofContact.size()-1) {
														appLog.error(contactemail+" is not matched with >>> "+splitedEmailId[i]);
														switchToDefaultContent(driver);
														return false;
													}
												}
											}
										}
										if(permission!=null) {
											WebElement downloadcheckBox=null;
											WebElement uploadCheckBox=null;
											for(int i=0; i<splitedEmailId.length; i++) {
												if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
														String downloadXpath="//span[contains(@id,'grid11_DealDetailBWFR-cell-3')]/a[text()='"+splitedEmailId[i]+"']/../../span[8]//input";
														String uploadXpath="//span[contains(@id,'grid11_DealDetailBWFR-cell-3')]/a[text()='"+splitedEmailId[i]+"']/../../span[9]//input";
													if(permission.equalsIgnoreCase("Upload")) {
														downloadcheckBox=FindElement(driver, downloadXpath, splitedEmailId[i]+" download check box", action.SCROLLANDBOOLEAN, 30);
													 	uploadCheckBox=FindElement(driver, uploadXpath, splitedEmailId[i]+" upload check box", action.SCROLLANDBOOLEAN, 30);
													}else if (permission.equalsIgnoreCase("download")) {
														downloadcheckBox=FindElement(driver, downloadXpath, splitedEmailId[i]+" download check box", action.SCROLLANDBOOLEAN, 30);
													}
													
												}else if (workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
														String downloadXpath="//span[contains(@id,'grid11_DealDetailBWINV-cell-3')]/a[text()='"+splitedEmailId[i]+"']/../../span[8]//input";
														String uploadXpath="//span[contains(@id,'grid11_DealDetailBWINV-cell-3')]/a[text()='"+splitedEmailId[i]+"']/../../span[9]//input";
													if(permission.equalsIgnoreCase("Upload")) {
														downloadcheckBox=FindElement(driver, downloadXpath, splitedEmailId[i]+" download check box", action.SCROLLANDBOOLEAN, 30);
													 	uploadCheckBox=FindElement(driver, uploadXpath, splitedEmailId[i]+" upload check box", action.SCROLLANDBOOLEAN, 30);
													}else if (permission.equalsIgnoreCase("download")) {
														downloadcheckBox=FindElement(driver, downloadXpath, splitedEmailId[i]+" download check box", action.SCROLLANDBOOLEAN, 30);
													}
												}
												if(permission.equalsIgnoreCase("Upload")) {
													if(click(driver, uploadCheckBox, splitedEmailId[i]+" upload check box", action.BOOLEAN)) {
														appLog.info("clicked on "+splitedEmailId[i]+" upload check box");
													}else {
														appLog.error("Not able to click on "+splitedEmailId[i]+" upload check box");
														if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
															appLog.error("Not able to click on contact access cancel button");
														}
														switchToDefaultContent(driver);
														return false;
													}
												}else if (permission.equalsIgnoreCase("download")) {
													if(click(driver, downloadcheckBox, splitedEmailId[i]+" download check box", action.BOOLEAN)) {
														appLog.info("clicked on "+splitedEmailId[i]+" download check box");
													}else {
														appLog.error("Not able to click on "+splitedEmailId[i]+" download check box");
														if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
															appLog.error("Not able to click on contact access cancel button");
														}
														switchToDefaultContent(driver);
														return false;
													}
												}
											}	
										}else {
											appLog.error("No need to provide any permission to the given contact: "+emailID);
										}
										if(applyInvite!=null || applyInvite.equalsIgnoreCase("Yes")) {
											if(click(driver, getApplyBtn(workspace, 60), workspace+" apply button", action.SCROLLANDBOOLEAN)) {
												appLog.info("Clicked on apply button successfully");
											
												if (accessGrantedContactCount.equalsIgnoreCase("Yes")) {
													if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
														CommonVariables.FRW_ContactGrantedAccess = CommonVariables.FRW_ContactGrantedAccess + noofCount;
														if(freshContactAccess) {
															CommonVariables.FRW_ContactNotViewedAnyDocument = CommonVariables.FRW_ContactNotViewedAnyDocument + noofCount;									
														}else {
//															CommonVariables.FRW_UniqueDocument=CommonVariables.FRW_UniqueDocument-noofCount;
//															CommonVariables.FRW_DocumentNotViewedOrDownload=CommonVariables.FRW_DocumentNotViewedOrDownload-noofCount;
//															ExcelUtils.writeDataInExcel(CommonVariables.FRW_UniqueDocument, "IPAnalytics",1, 1);
														}
														appLog.info("Access Granted Contact: " + CommonVariables.FRW_ContactGrantedAccess);
													}else {
														CommonVariables.INV_ContactGrantedAccess = CommonVariables.INV_ContactGrantedAccess + noofCount;
														if(freshContactAccess) {
															CommonVariables.INV_ContactNotViewedAnyDocument = CommonVariables.INV_ContactNotViewedAnyDocument + noofCount;									
														}else {
//															CommonVariables.INV_UniqueDocument=CommonVariables.INV_UniqueDocument-noofCount;
//															CommonVariables.INV_DocumentNotViewedOrDownload=CommonVariables.INV_DocumentNotViewedOrDownload-noofCount;
//															ExcelUtils.writeDataInExcel(CommonVariables.INV_UniqueDocument, "IPAnalytics",1, 2);
														}
														appLog.info("Access Granted Contact: " + CommonVariables.INV_ContactGrantedAccess);
													}
													int lastRow=ExcelUtils.getLastRow("Contacts");
														for(int j=0; j<=lastRow;j++){
															String s =ExcelUtils.readData("Contacts", j, 4);
															if((s!=null && !s.isEmpty()) && s.equalsIgnoreCase(emailID)){
																ExcelUtils.writeDataInExcel("Access Granted", "Contacts", j, 35);
															}
														}
												}
												if(click(driver, getCloseBtn(workspace, 60), workspace+" close button", action.SCROLLANDBOOLEAN)) {
													applyInvite.indexOf("Clicked on Close Buton");	
												}else {
													appLog.error("Not able to click on close button");
													switchToDefaultContent(driver);
													return false;
												}
												
											}else {
												appLog.error("not able to click on apply button so cannot invite contact: "+emailID);
												switchToDefaultContent(driver);
												return false;
											}
										}else {
											if(click(driver, getCancelBtn(workspace, 60), workspace+" cancel button", action.SCROLLANDBOOLEAN)) {
												applyInvite.indexOf("Clicked on cancel Buton");
												switchToDefaultContent(driver);
												return true;
											}else {
												appLog.error("Not able to click on close button");
												switchToDefaultContent(driver);
												return false;
											}
										}
										if(sendInvitationMail!=null && sendInvitationMail.equalsIgnoreCase("Yes")) {
											if(sendInvitationMail(workspace, emailID, contactAccessViewfolderName, contactSearchKeyword)) {
												appLog.info("email has been successfully send to contact: "+emailID);
											}else {
												appLog.error("Not able to send an email to contact: "+emailID);
												switchToDefaultContent(driver);
												return false;
											}
										}else {
											appLog.info("No need to send an email to contact: "+emailID);
										}
									}else {
										appLog.error(emailID+" contact is not visible in select contact access grid.");
										if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
											appLog.error("Not able to click on contact access cancel button");
										}
										switchToDefaultContent(driver);
										return false;
									}
									
								}else {
									appLog.error("Not able to click on add select contact button in "+workspace);
									if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
										appLog.error("Not able to click on contact access cancel button");
									}
									switchToDefaultContent(driver);
									return false;
								}
								
							}else {
								appLog.error("Not able to click on "+emailID+" contact check box so cannot invite contact: "+emailID);
								if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
									appLog.error("Not able to click on contact access cancel button");
								}
								switchToDefaultContent(driver);
								return false;
							}	
						}else {
							appLog.error(emailID+"contact is not available in the "+workspace+" contact access grid");
							if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
								appLog.error("Not able to click on contact access cancel button");
							}
							switchToDefaultContent(driver);
							return false;
						}
				}else {
					appLog.error("Not able to expand "+workspace+" minus icon so cannot invite contact: "+emailID);
					if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
						appLog.error("Not able to click on contact access cancel button");
					}
					switchToDefaultContent(driver);
					return false;
				}	
			}else {
				appLog.error("Not able to click on contact access icon so cannot invite contact from "+workspace);
				if(!click(driver, getContactAccessCancelBtn(workspace, 30), "cancel button", action.SCROLLANDBOOLEAN)) {
					appLog.error("Not able to click on contact access cancel button");
				}
				switchToDefaultContent(driver);
				return false;
			}
			switchToDefaultContent(driver);
			return true;
			
		}

	/**
	 * @author Akul Bhutani
	 * @param inst
	 * @param fileName
	 * @param view
	 * @param remove
	 * @param firstWindow
	 * @param secondWindow
	 * @return SoftAssert true/false
	 */
	public SoftAssert verifyFileDist2Of2SuccessfullyAppliedUIReverse(String inst, String fileName, boolean view, boolean remove,String firstWindow, String secondWindow) {
		SoftAssert softa = new SoftAssert();
		String allFiles = "//ul[@class='decimal']//li";
		String viewXpath = allFiles + "//a[1]";
		String removeXpath = allFiles + "//a[2]";
		Set<String> windows = new HashSet<String>();
		String f[] = fileName.split("\\.");
		boolean flag = false;
		int iter=5;
		List<WebElement> allDocs = FindElements(driver, allFiles, "all files");
		List<WebElement> allViewLinks = FindElements(driver, viewXpath, "all files");
		List<WebElement> allRemoveLinks = FindElements(driver, removeXpath, "all files");
		for (int i = 0;i<allDocs.size();i++) {
			System.err.println(allDocs.get(i).getText().trim());
			if (allDocs.get(i).getText().trim().contains(inst+" ( "+f[0]+"_"+inst+"."+f[1]+" )")) {
					softa.assertTrue(true, "successfully found "+fileName+" and inst "+inst);
				if (allViewLinks.get(i)!=null) {
					appLog.info("successfully found view link of "+fileName+" and inst "+inst);
				}
				else {
					appLog.error("could not find view link of "+fileName+" and inst "+inst);
					softa.assertTrue(false, "could not find view link of "+fileName+" and inst "+inst);
				}
				
				if (allRemoveLinks.get(i)!=null) {
					appLog.info("successfully found remove link of "+fileName+" and inst "+inst);
				}
				else {
					appLog.error("could not find remove link of "+fileName+" and inst "+inst);
					softa.assertTrue(false, "could not find remove link of "+fileName+" and inst "+inst);
				}
				
				flag = true;
				if (view) {
					iter = i;
					
				}
				if (remove) {
					iter = i;
				}
				break;
			}
		}
	
		if (flag == false) {
			appLog.error("could not find "+fileName+" in applied UI window");
			softa.assertTrue(false, "could not find "+fileName);
		}
		return softa;
	}
	
	/**
	 * @author Akul Bhutani
	 * @param fileName
	 * @param inst
	 * @param reverse
	 * @return file WebElement on Issue UI
	 */
	public WebElement findFileOnIssueUIFileDistributor(String fileName, String inst, boolean reverse) {
		
		String f[] =fileName.split("\\.");
		System.err.println(f[0]+" and "+f[1]);
		if (reverse) {
		return isDisplayed(driver, FindElement(driver, "//a[@title='"+reverseFileNameForFileDistributor(fileName, inst)+"']", "file name reverse on issue ui", action.BOOLEAN, 30),"visibility", 30,"file name on issue ui" );
		}
		else
			return isDisplayed(driver, FindElement(driver, "//a[@title='"+inst+"_"+fileName+"']", "file name suggested naming on issue ui", action.BOOLEAN, 30),"visibility", 30,"file name on issue ui" );
		
	}
	
	/**
	 * @author Akul Bhutani
	 * @param fileName
	 * @param inst
	 * @return reverse name according to file distribution
	 */
	public String reverseFileNameForFileDistributor(String fileName, String inst) {
		String f[] =fileName.split("\\.");
		System.err.println(f[0]+" and "+f[1]);
		return f[0]+"_"+inst+"."+f[1];
	}
	
	/**@author Akul Bhutani
	 * @return true/false
	 */
	public boolean verifyDownloadFunctionalityFileDistributorWithoutClick() {
		boolean flag = true;
		if (getViewFileOnfileDistDownload(60) != null) {
			appLog.info("Download Button is displaying");
		} else {
			appLog.error("Document Download Button is not displaying");
			flag = false;
			sa.assertTrue(false, "Document Download Button is not displaying");
		}
		if (getViewFileOnFileDistClose(60) != null) {
			appLog.info("Close Button is displaying");
		} else {
			appLog.error("Close Button is not displaying");
			flag = false;
			sa.assertTrue(false, "Close Button is not displaying");
		}
		return flag;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param contactName
	 * @param firmName
	 * @return soft assert
	 */
	public SoftAssert verifyContactNameAndFirmNameInContactNotViewedAnyDocument(String contactName, String firmName) {
		WebElement ele=null;
		SoftAssert saaa = new SoftAssert();
		if(click(driver, getIPAnalyticsnotViewedAnyDocumentLink(10),"Document Not Viewed any document link", action.BOOLEAN)) {
			ThreadSleep(2000);
			if(contactName!=null && firmName!=null) {
				String[] splitedConName= contactName.split("<break>");
				String[] splitedfirmName=firmName.split("<break>");
				for(int i=0; i<splitedConName.length; i++) {
					ele=null;
					ele=isDisplayed(driver, FindElement(driver, "//a[@text='"+splitedConName[i]+"']/../following-sibling::span[text()='"+splitedfirmName[i]+"']", "contact and firm name", action.BOOLEAN,10),"visibility",10, "contact and firm name");
					if(ele!=null) {
						appLog.info(splitedConName[i]+" and "+splitedfirmName[i]+" is displayed in document not viewed any document pop up");
						
					}else {
						appLog.error(splitedConName[i]+" and "+splitedfirmName[i]+" is not available in document not viewed any document pop up");
						saaa.assertTrue(false,splitedConName[i]+" and "+splitedfirmName[i]+" is not available in document not viewed any document pop up");
					}
				}
			}else {
				if(getContactNotViewedAnyDocumentErrorMsg(10)!=null) {
					String aa=getText(driver, getContactNotViewedAnyDocumentErrorMsg(10), "document not viewed any document error message", action.BOOLEAN);
					if(aa!=null) {
						if(aa.equalsIgnoreCase(FundsPageErrorMessage.DocumentNotViewedAnyDoc)) {
							appLog.info("Error Message is verified. "+FundsPageErrorMessage.DocumentNotViewedAnyDoc);
						}else {
							appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.DocumentNotViewedAnyDoc+" /t Actual: "+aa);
							saaa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.DocumentNotViewedAnyDoc+" /t Actual: "+aa);
						}
					}else {
						appLog.error("Error is not available in document not viewed any document pop up");
						saaa.assertTrue(false, "Error is not available in document not viewed any document pop up");
					}
				}else {
					appLog.error("Error message is not displayed so cannot verify error message: "+FundsPageErrorMessage.DocumentNotViewedAnyDoc);
					saaa.assertTrue(false, "Error message is not displayed so cannot verify error message: "+FundsPageErrorMessage.DocumentNotViewedAnyDoc);
				}
			}
		if(click(driver, getContactNotViewedAnyDocumentCloseBtn(10), "document not viewed any document close button", action.BOOLEAN)) {
			appLog.info("clicked on close button");
		}else {
			appLog.error("Not able to click on document not viewed any document close button");
			saaa.assertTrue(false, "Not able to click on document not viewed any document close button");
		}
		}else {
			appLog.error("Not able to click on document not viewed or download pop up so cannot verify contact name and firm name");
			saaa.assertTrue(false, "Not able to click on document not viewed or download pop up so cannot verify contact name and firm name");
		}
		return saaa;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param docName
	 * @param LargeNumberOfDocument TODO
	 * @return Soft Assert
	 */
	@Test
	public SoftAssert verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(String docName, boolean LargeNumberOfDocument) {
		SoftAssert saaa = new SoftAssert();
		if(click(driver, getIPAnalyticsDocumentNotViwedorDownloadLink(10), "document not viewed or download link", action.BOOLEAN)) {
			if(docName!=null) {
				String[] splitedDoc=docName.split("<break>");
				if(LargeNumberOfDocument) {
					HashSet<String> newValue = CommonLib.scrollActiveWidgetforSetofFiles(driver,
							getDocumentNotViewedOrDownloadScrollBox(30),
							By.xpath("//span[@id='NotViewedDocument_Grid-view-box-middle']//span[contains(@id,'NotViewedDocument_Grid-cell-0-')]/a"));
					List<String> fileNames = new ArrayList<String>(newValue);
					if(!fileNames.isEmpty()) {
						for(int i=0; i<splitedDoc.length; i++) {
							for(int j=0; j<fileNames.size(); j++) {
								//scrollDownThroughWebelement(driver, fileNames.get(j), "");
								//String aa=fileNames.get(j).getAttribute("text").trim();
								if(fileNames.get(j).equalsIgnoreCase(splitedDoc[i])) {
									appLog.info("file name is matched: "+splitedDoc[i]);
									break;
								}else {
									if(j==fileNames.size()-1) {
										appLog.error("file name is not matched. Expected: "+splitedDoc[i]+"\t Actual : "+fileNames.get(j));
										saaa.assertTrue(false, "file name is not matched. Expected: "+splitedDoc[i]+"\t Actual : "+fileNames.get(j));
									}
								}
							}
						}
					}else {
						appLog.error("Document list is not available in the document not viewed or download pop up");
						saaa.assertTrue(false, "Document list is not available in the document not viewed or download pop up");
					}
				}else {
					List<WebElement> fileNames = getDocumentNotViewedOrDownloadDocumentList();
					if(!fileNames.isEmpty()) {
						for(int i=0; i<splitedDoc.length; i++) {
							for(int j=0; j<fileNames.size(); j++) {
								scrollDownThroughWebelement(driver, fileNames.get(j), "");
								String aa=fileNames.get(j).getAttribute("text").trim();
								if(aa.equalsIgnoreCase(splitedDoc[i])) {
									appLog.info("file name is matched: "+splitedDoc[i]);
									break;
								}else {
									if(j==fileNames.size()-1) {
										appLog.error("file name is not matched. Expected: "+splitedDoc[i]+"\t Actual : "+aa);
										saaa.assertTrue(false, "file name is not matched. Expected: "+splitedDoc[i]+"\t Actual : "+aa);
									}
								}
							}
						}
					}else {
						appLog.error("Document list is not available in the document not viewed or download pop up");
						saaa.assertTrue(false, "Document list is not available in the document not viewed or download pop up");
					}
				}
			}else {
				if(getDocumentNotViewedOrDownloadedErrorMsg(10)!=null) {
					String aa=getText(driver, getDocumentNotViewedOrDownloadedErrorMsg(10), "document not viewed or downloadt error message", action.BOOLEAN);
					if(aa!=null) {
						if(aa.equalsIgnoreCase(FundsPageErrorMessage.DocumentNotViewedOrDownload)) {
							appLog.info("Error Message is verified. "+FundsPageErrorMessage.DocumentNotViewedOrDownload);
						}else {
							appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.DocumentNotViewedOrDownload+" /t Actual: "+aa);
							saaa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.DocumentNotViewedOrDownload+" /t Actual: "+aa);
						}
					}else {
						appLog.error("Error is not available in document not viewed or download pop up");
						saaa.assertTrue(false, "Error is not available in document not viewed or download pop up");
					}
				}else {
					appLog.error("Error message is not displayed so cannot verify error message: "+FundsPageErrorMessage.DocumentNotViewedOrDownload);
					saaa.assertTrue(false, "Error message is not displayed so cannot verify error message: "+FundsPageErrorMessage.DocumentNotViewedOrDownload);
				}
			}
			if(click(driver, getDocumentNotViewedOrDownloadedCloseBtn(10), "document not viewed or download close button", action.BOOLEAN)) {
				appLog.info("clicked on close button");
			}else {
				appLog.error("Not able to click on document not viewed or download close button");
				saaa.assertTrue(false, "Not able to click on document not viewed or download close button");
			}
		}else {
			appLog.error("Not able to click on document not viewed or download pop up");
			saaa.assertTrue(false, "Not able to click on document not viewed or download pop up");
		}
		return saaa;
	}

	/**
	 * @author Akul Bhutani
	 * @param workspace
	 * @param files
	 * @param paths
	 * @return
	 */
	public List<String> verifyDuplicateDocWindowWithoutUpload(Workspace workspace, String files, String paths) {
		String filesArr[] = files.split("<break>");
		String pathArr[] = paths.split("<break>");
		List<String> absent= new ArrayList<String>();
		boolean flag = true;
		
		
		if ( getDuplicateDocumentsHeadText(60).getText().trim().equals("Duplicate Documents")) {
			appLog.info("duplicate documents heading is successfully verified");
		}
		else {
			appLog.error("duplicate documents heading is not present");
			sa.assertTrue(false, "duplicate documents heading is not present");
			flag = false;
		}
		if ( getDuplicateDocumentsSubHeadText(60).getText().trim().equals("Duplicate Documents")) {
			appLog.info("duplicate documents sub heading is successfully verified");
		}
		else {
			appLog.error("duplicate documents sub heading is not present");
			sa.assertTrue(false, "duplicate documents sub heading is not present");
			flag = false;
		}
		if ( getFolderLocationSubHead(60).getText().trim().equals("Folder Location")) {
			appLog.info("folder location sub heading is successfully verified");
		}
		else {
			appLog.error("folder location sub heading is not visible");
			sa.assertTrue(false, "folder location sub heading is not visible");
			flag = false;
		}
		if ( getDuplicateDocumentsTextUnderHeading(60).getText().trim().equals(FundsPageErrorMessage.duplicateDocumentsTextUnderHeading)) {
			appLog.info("text present to ask how to handle duplicate documents is displayed successfully");
		}
		else {
			appLog.error("text present to ask how to handle duplicate documents is displayed successfully");
		}
		if ( getUpdateAllButton(60)!=null) {
			appLog.info("update all button is present");
		}
		else {
			appLog.error("update all button is not present on duplicate document window");
			sa.assertTrue(false, "update all button is not present on duplicate document window");
			flag = false;
		}
		if ( getIgnoreAllButton(60)!=null) {
			appLog.info("ignore all button is successfully present");
		}
		else {
			appLog.error("ignore all button is not present on duplicate docuemnts heading");
			sa.assertTrue(false, "ignore all button is not present on duplicate docuemnts heading");
			flag = false;
		}
		int size = getDuplicateDocumentValueList("duplicate documents",30).size();
		boolean file_flag=false;

		
		for (int j = 0;j<filesArr.length;j++) {
			System.err.println("files passed "+j);
			file_flag = false;
			for (int i = 0;i<size;i++) {
				System.err.println("files on page "+i);
				
				
					if (workspace == Workspace.FundraisingWorkspace) {
						if ( getDuplicateDocumentValueList("duplicate documents",30).get(i).getText().trim().equals(filesArr[j])
								&&(getDuplicateDocumentValueList("folder location", 30).get(i).getText().trim().contains(pathArr[j])))  {
							appLog.info(filesArr[j] + " for " + M9Institution1+" is found duplicate successfully according to alert window");
							file_flag = true;
						}
						
					}
					else if(workspace == Workspace.InvestorWorkspace) {
						if ( getDuplicateDocumentValueList("duplicate documents",30).get(i).getText().trim().equals(filesArr[j])
								&&(getDuplicateDocumentValueList("folder location", 30).get(i).getText().trim().contains(pathArr[j])))  {
							appLog.info(filesArr[j] + " for " + M9Institution1+" is found duplicate successfully according to alert window");
							file_flag = true;
						}
						
					}
				


			}
			if (file_flag == false) {
				absent.add(filesArr[j]);
				appLog.error("could not find file "+filesArr[j] + " with "+pathArr[j]);
				sa.assertTrue(false, "could not find file "+filesArr[j] + " with "+pathArr[j]);
			}
		}
		return absent;
	}

	/**
	 * @author Akul Bhutani
	 * @param inst
	 * @param folderName
	 * @return true/false
	 */
	public boolean clickonFolderStructureFileDist(String inst, String folderName) {
		WebElement ele=isDisplayed(driver, FindElement(driver, "//li[text()='"+inst+"']/span[contains(@onclick,'"+inst+"')]", "institution name plus icon", action.BOOLEAN, 10), "visibility", 30,"institution name plus icon");
		WebElement ele1=null;
		boolean flag = true;
		if (ele!=null) {
			if (click(driver, ele, "inst folder plus icon", action.BOOLEAN)) {
				ele1=isDisplayed(driver, FindElement(driver, "//span[contains(@onclick,'"+inst+"')]/../ul/li[text()='"+folderName+"']/input", "folder name radio icon", action.BOOLEAN, 10), "visibility", 30,"folder name radio icon");
				if (ele1!=null) {	
					click(driver, ele1, "folder name radio button", action.BOOLEAN);
				}
				else {
					flag = false;
					appLog.error("radio button for folder name not found");
				}
			}
			else {
				flag = false;
				appLog.error("inst folder plus icon not clickable");
			}

		}
		else {
			flag = false;
			appLog.error("could not find institution folder plus icon");
		}
		return flag;
	}
	
	/**
	 * @author Akul Bhutani
	 * @param instAndLp
	 * @param folderName
	 * @return true/false
	 */
	public boolean clickonFolderStructureFileDistInvestor(String instAndLp, String folderName) {
		WebElement ele=isDisplayed(driver, FindElement(driver, "//li[text()='"+instAndLp.split("/")[0]+"']/span[contains(@onclick,'"+instAndLp.split("/")[0]+"')]", "institution name plus icon", action.BOOLEAN, 10), "visibility", 30,"institution name plus icon");
		WebElement ele1=null;
		WebElement lp=null;
		boolean flag = true;
		if (ele!=null) {
			
			if (click(driver, ele, "inst folder plus icon", action.BOOLEAN)) {
				lp = isDisplayed(driver, FindElement(driver, "//li[text()='"+instAndLp.split("/")[0]+"']/span[contains(@onclick,'"+instAndLp.split("/")[0]+"')]/..//ul//li[text()='"+instAndLp.split("/")[1]+"']/span[1]", "lp folder cross icon", action.BOOLEAN, 10), "visibility", 30, "lp folder cross icon");
				if (lp!=null) {
					click(driver, lp,"lp folder cross icon", action.BOOLEAN);
					
					ele1=isDisplayed(driver, FindElement(driver, "//li[text()='"+instAndLp.split("/")[0]+"']/span[contains(@onclick,'"+instAndLp.split("/")[0]+"')]/..//ul//li[text()='"+instAndLp.split("/")[1]+"']//li[text()='"+folderName+"']//input", "folder name radio icon", action.BOOLEAN, 10), "visibility", 30,"folder name radio icon");
					if (ele1!=null) {	
						click(driver, ele1, "folder name radio button", action.BOOLEAN);
					}
					else {
						flag = false;
						appLog.error("radio button for folder name not found");
					}
				}
				else {
					flag = false;
					appLog.error("lp folder plus icon is not clickable");
				}
				
			}
			else {
				flag = false;
				appLog.error("inst folder plus icon not clickable");
			}

		}
		else {
			flag = false;
			appLog.error("could not find institution folder plus icon");
		}
		return flag;
	}
	
	/**
	 * @author Akul Bhutani
	 * @param inst1
	 * @param inst2
	 * @param fileName
	 * @param view
	 * @param remove
	 * @param firstWindow
	 * @param secondWindow
	 * @param acceptOrRejectRemovePopup
	 * @return SoftAssert true/false
	 */
	public SoftAssert verifyFileDist2Of2SuccessfullyAppliedUIDiffInst(String inst1, String inst2, String fileName, boolean view, boolean remove,String firstWindow, String secondWindow, action acceptOrRejectRemovePopup) {
		SoftAssert softa = new SoftAssert();
		String allFiles = "//ul[@class='decimal']//li";
		String viewXpath = allFiles + "//a[1]";
		String removeXpath = allFiles + "//a[2]";
		Set<String> windows = new HashSet<String>();
		String thirdWindow = null;
		boolean flag = false;
		int iter=5;
		List<WebElement> allDocs = FindElements(driver, allFiles, "all files");
		List<WebElement> allViewLinks = FindElements(driver, viewXpath, "all files");
		List<WebElement> allRemoveLinks = FindElements(driver, removeXpath, "all files");
		for (int i = 0;i<allDocs.size();i++) {
			System.err.println(allDocs.get(i).getText().trim());
			if (allDocs.get(i).getText().trim().contains(inst1+" ( "+inst1+"_"+inst2+"_"+fileName+" )")) {
					softa.assertTrue(true, "successfully found "+fileName+" and inst "+inst1);
				if (allViewLinks.get(i)!=null) {
					appLog.info("successfully found view link of "+fileName+" and inst "+inst1);
				}
				else {
					appLog.error("could not find view link of "+fileName+" and inst "+inst1);
					softa.assertTrue(false, "could not find view link of "+fileName+" and inst "+inst1);
				}
				
				if (allRemoveLinks.get(i)!=null) {
					appLog.info("successfully found remove link of "+fileName+" and inst "+inst1);
				}
				else {
					appLog.error("could not find remove link of "+fileName+" and inst "+inst1);
					softa.assertTrue(false, "could not find remove link of "+fileName+" and inst "+inst1);
				}
				
				flag = true;
				if (view) {
					iter = i;
					
				}
				if (remove) {
					iter = i;
				}
				break;
			}
		}
		if (view) {
			allViewLinks = FindElements(driver, viewXpath, "all files");
			if (click(driver, allViewLinks.get(iter), "view link for "+fileName, action.BOOLEAN)) {
				appLog.info("clicked on view link of "+fileName+" and inst "+inst1);
				ThreadSleep(5000);
				
				windows = driver.getWindowHandles();
				if (windows!=null) {
					for (String temp_window:windows) {
						if (!temp_window.equalsIgnoreCase(firstWindow)) {
							if (!temp_window.equalsIgnoreCase(secondWindow)) {
								driver.switchTo().window(temp_window);
								thirdWindow = temp_window;
								break;
							}
						}
					}
				}
				if (thirdWindow!=null) {
					if (verifyDownloadFunctionalityFileDistributorWithoutClick()) {
						appLog.info("download and close button are successfully verified");
					}
					else {
						appLog.error("could not verify download and close button");
					}
					driver.close();
					driver.switchTo().window(secondWindow);
					}
				}
				else {
					appLog.error("could not click on view link of "+fileName+" and inst "+inst1);
				}

			}
			if (remove) {
				List<WebElement> ele = BaseLib.edriver.findElements(By.cssSelector("ul.decimal a[title=Remove]"));
				ele.get(iter).click();
				//if (click(driver, allRemoveLinks.get(iter), "remove link", action.BOOLEAN)) {
					ThreadSleep(4000);
					if (isAlertPresent(driver)) {
						String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
						switchToAlertAndAcceptOrDecline(driver, 30, acceptOrRejectRemovePopup);
						if (msg.trim().equals(FundsPageErrorMessage.removeSuccessfullyAppliedText)) {
							appLog.info("alert text for remove link is successully verified");
						}
						else {
							appLog.error("alert text is incorrect after clicking remove link");
							sa.assertTrue(false, "alert text is incorrect after clicking remove link");
						}
					}
					else {
						appLog.error("no alert is present after clicking remove link");
						sa.assertTrue(false, "no alert is present after clicking remove link");
					}
				/*}
				else {
					appLog.error("could not clicking on remove link for "+iter);
					sa.assertTrue(false, "could not clicking on remove link for "+iter);
				}*/
			}
			if (flag == false) {
				appLog.error("could not find "+fileName+" in applied UI window for "+inst1+" and "+inst2);
				softa.assertTrue(false, "could not find "+fileName+" in applied UI window for "+inst1+" and "+inst2);
			}
			return softa;
		}

	/**
	 * @author Akul Bhutani
	 * @param inst
	 * @param fileName
	 * @return true/false
	 */
	public boolean verifyDataOnFileDistDocument(String inst, String fileName) {
		boolean flag = true;
		String instarr[] = inst.split(" ");
		switchToFrame(driver, 10, FindElement(driver, "//iframe", "iframe", action.BOOLEAN, 10));
		if (isDisplayed(driver, FindElement(driver, "//*[contains(text(),'<s|')]", "first part of inst delimiter", action.BOOLEAN, 10), "visibility", 30, "first part of inst delimiter")!=null) {
			appLog.info("successfully found first part of inst delimiter");
		}
		else {
			flag = false;
			appLog.error("could not find first part of inst delimiter");
		}
		if (isDisplayed(driver, FindElement(driver, "//*[contains(text(),'"+inst+"')]", "inst name delimiter", action.BOOLEAN, 10), "visibility", 30, "inst name delimiter")!=null) {
			appLog.info("successfully found inst name delimiter");
		}
		else {
			appLog.info("inst full name is not displayed, now finding parts of inst on document");
			if (isDisplayed(driver, FindElement(driver, "//*[contains(text(),'"+instarr[0]+"')]", "inst name delimiter", action.BOOLEAN, 10), "visibility", 30, "inst name delimiter")!=null) {
				appLog.info("successfully found "+instarr[0]+" on document");
			}
			else {
			flag = false;
			appLog.error("could not find inst/lp name delimiter");
			}
			if (isDisplayed(driver, FindElement(driver, "//*[contains(text(),'"+instarr[1]+"')]", "inst name delimiter", action.BOOLEAN, 10), "visibility", 30, "inst name delimiter")!=null) {
				appLog.info("successfully found "+instarr[1]+" on document");
			}
			else {
			flag = false;
			appLog.error("could not find inst/lp name delimiter");
			}
			if (isDisplayed(driver, FindElement(driver, "//*[contains(text(),'"+instarr[2]+"')]", "inst name delimiter", action.BOOLEAN, 10), "visibility", 30, "inst name delimiter")!=null) {
				appLog.info("successfully found "+instarr[2]+" on document");
			}
			else {
			flag = false;
			appLog.error("could not find inst/lp name delimiter");
			}
		}
		if (isDisplayed(driver, FindElement(driver, "//*[text()='"+fileName+"']", "file name delimiter", action.BOOLEAN, 10), "visibility", 30, "file name delimiter")!=null) {
			appLog.info("successfully found file name delimiter");
		}
		else {
			appLog.info("file full name is not displayed, now finding parts of full name on document");
			if (isDisplayed(driver, FindElement(driver, "//*[contains(text(),'"+fileName.substring(0,fileName.length()-2)+"')]", "inst name delimiter", action.BOOLEAN, 10), "visibility", 30, "inst name delimiter")!=null) {
				appLog.info("successfully found "+fileName.substring(0,fileName.length()-2)+" on document");
			}
			else {
			flag = false;
			appLog.error("could not find inst/lp name delimiter");
			}
			if (isDisplayed(driver, FindElement(driver, "//*[contains(text(),'"+fileName.charAt(fileName.length()-1)+"')]", "inst name delimiter", action.BOOLEAN, 10), "visibility", 30, "inst name delimiter")!=null) {
				appLog.info("successfully found "+fileName.charAt(fileName.length()-1)+" on document");
			}
			else {
			flag = false;
			appLog.error("could not find inst/lp name delimiter");
			}
		}
		if (isDisplayed(driver, FindElement(driver, "//*[contains(text(),'<e|nv>')]", "file end delimiter", action.BOOLEAN, 10), "visibility", 30, "file end delimiter")!=null) {
			appLog.info("successfully found file end delimiter");
		}
		else {
			flag = false;
			appLog.error("could not find file end delimiter");
		}
		switchToDefaultContent(driver);
		return flag;
	}
	
	/**
	 * @author ANKIT JAISWAL
	 * @param driver
	 * @param filesToAprrove
	 * @return
	 */
	public List<String> selectPendingFilesToApproveAndCountApprovedFiles(WebDriver driver, String filesToAprrove) {
		String[] files = filesToAprrove.split("<break>");
		int count = 0;
		List<String> notFoundFiles = new ArrayList<String>();
		appLog.info("Number of files to be selected: " + files.length);
		for (int i = 0; i < files.length; i++) {
			WebElement ele = FindElement(driver,
					"//a[contains(text(),'" + files[i] + "')]/../preceding-sibling::span//input", "Pending files",
					action.BOOLEAN, 30);
			if (ele != null) {
				if (click(driver, ele, files[i] + " checkbox", action.SCROLLANDBOOLEAN)) {
					count++;
					appLog.info(files[i] + " file is selected successfully.");
				} else {
					appLog.error(files[i] + " file is not selected.");
					notFoundFiles.add(files[i]);
				}
			} else {
				appLog.error(files[i] + " is not present in the pending list, So cannot select the file to approve.");
				notFoundFiles.add(files[i]);
			}
		}
		appLog.info("Number of files selected: " + count);
		BaseLib.totalUploadedFiles = BaseLib.totalUploadedFiles + count;
		return notFoundFiles;

	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param institutionOrLPName
	 * @param checkProgressMsg
	 * @param gmailEmailID
	 * @param gmailPassword
	 * @param sendersEmailID
	 * @param receiversEmailID
	 * @param mailSubject
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean selectAccountFromManageTargetPopUp(String institutionOrLPName, String checkProgressMsg, String gmailEmailID, String gmailPassword, String sendersEmailID, String receiversEmailID, String mailSubject, Workspace workspace, int timeOut){
		System.err.println("Going to break");
		String institutionOrLPNamesToBeSelected[] = institutionOrLPName.split("<break>");
		System.err.println("break successfull.");
		boolean flag = true;
		System.err.println(institutionOrLPNamesToBeSelected.length);
		for(int i = 0 ; i < institutionOrLPNamesToBeSelected.length ; i++){
//			if(selectInstitutionOrLP(institutionOrLPName, workspace, timeOut)){
//				if(click(driver, getInvestorAddedConfirmationCloseButton(workspace, timeOut), "Investor Addition confirmation Close Button", action.BOOLEAN));
//			}
			try{
				if (setFieldValueOnManageInvestor("Account:Legal Name", 1, workspace,timeOut)) {
					if (setOperatorValueOnManageInvestor("equals", 1,workspace,timeOut)) {
						if (setCriterionValueOnManageTarget("textbox", 1, institutionOrLPNamesToBeSelected[i].split("/")[0], "Account:Legal Name",workspace,timeOut)) {
							click(driver, getManageInvestorFilterApplyButton(workspace, 0), "Apply Button", action.BOOLEAN);
							System.err.println("inside for");
							System.err.println(institutionOrLPNamesToBeSelected[i]);
							WebElement ele = null;
							if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
								ele = getInstituionCheckBoxOn3Of3(institutionOrLPNamesToBeSelected[i], workspace, timeOut);
							} else {
								ele = getLimitedPartnerCheckBox(institutionOrLPNamesToBeSelected[i].split("/")[0], institutionOrLPNamesToBeSelected[i].split("/")[1], workspace, timeOut);
							}
							scrollDownThroughWebelement(driver, ele, "Investor Name '"+institutionOrLPNamesToBeSelected[i]+"'");
							if(!isSelected(driver, ele, institutionOrLPNamesToBeSelected[i]+" check box")){
								ThreadSleep(1000);
								if(click(driver, ele, institutionOrLPNamesToBeSelected[i]+" Institution Check Box.", action.BOOLEAN)){
									appLog.info("Target Account '"+institutionOrLPNamesToBeSelected[i]+"' Selected Successfully");
									if(isAlertPresent(driver)){
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if(checkProgressMsg!=null&&checkProgressMsg.equalsIgnoreCase("Yes"))
											if(msg.equalsIgnoreCase(FundsPageErrorMessage.YourRequestInProgressErrorMsg)){
												appLog.info(msg+" :Message is verfiied successfully.");
											} else {
												appLog.error("Progress Message is not verfiied.Expexcted: "+FundsPageErrorMessage.YourRequestInProgressErrorMsg+"\tActual: "+msg);
												BaseLib.sa.assertTrue(false,"Progress Message is not verfiied.Expexcted: "+FundsPageErrorMessage.YourRequestInProgressErrorMsg+"\tActual: "+msg);
											}
										if(msg.contains("email") && msg.contains("progress") && msg.contains("request")){
											for(int j = 0 ; j <= 100 ; j++)
												if(EmailLib.mailReceived(gmailEmailID, gmailPassword, sendersEmailID, receiversEmailID, mailSubject, institutionOrLPNamesToBeSelected[i])){
													System.out.println("\n\n\nFound\n\n\n");
													break;
												} else {
													System.out.println("Mail is not found, Will Check for mail again.");
													if(j>=100){
														appLog.error("Mail is not received.");
														BaseLib.sa.assertTrue(false,"Mail is not received for account: "+institutionOrLPNamesToBeSelected[i]);
														return false;
													}
												}
											click(driver, getManageInvestorAddedPopupCloseButton(workspace, timeOut), "close button", action.BOOLEAN);
											return true;
										} else {
											appLog.info("Unexpected alert found with message: "+msg);
											if(msg.contains("180") && msg.contains("investor") && msg.contains("select")){
												appLog.error("Cannot add more investor.");
												break;
											} // continue from here
											driver.navigate().refresh();
											switchToFrame(driver, 30, getFrame(PageName.FundsPage, timeOut));
											System.err.println("Switched to frame.");
											scrollDownThroughWebelement(driver, getWorkspaceSectionView(workspace, timeOut), workspace.toString()+" View.");
											click(driver, getManageInvestorIcon(workspace, timeOut), "Manage investor Icon", action.BOOLEAN);
											if(selectAccountFromManageTargetPopUp(institutionOrLPName, checkProgressMsg, gmailEmailID, gmailPassword, sendersEmailID, receiversEmailID, mailSubject, workspace, timeOut)){
												return true;
											} else {
												return false;
											}
										}
									} else if(FindElement(driver, "//span[@class='title'][text()='Error: Time limit exceeded']", "Visiual force timeout error", action.BOOLEAN, 0)!=null){
										String screenshotLoc = screenshot(currentlyExecutingTC);
										appLog.info("Visual Force failure screenshot: "+screenshotLoc);
										driver.navigate().refresh();
										switchToFrame(driver, 30, getFrame(PageName.FundsPage, timeOut));
										System.err.println("Switched to frame.");
										scrollDownThroughWebelement(driver, getWorkspaceSectionView(workspace, timeOut), workspace.toString()+" View.");
										click(driver, getManageInvestorIcon(workspace, timeOut), "Manage investor Icon", action.BOOLEAN);
										if(selectAccountFromManageTargetPopUp(institutionOrLPName, checkProgressMsg, gmailEmailID, gmailPassword, sendersEmailID, receiversEmailID, mailSubject, workspace, timeOut)){
											return true;
										} else {
											return false;
										}
									} else {
										if(click(driver, getManageInvestorAddedPopupCloseButton(workspace, timeOut), "Close Button", action.BOOLEAN)){
											return true;
										}
									}
								} else {//span[@class='title'][text()='Error: Time limit exceeded']
									if(FindElement(driver, "//span[@class='title'][text()='Error: Time limit exceeded']", "Visiual force timeout error", action.BOOLEAN, 10)!=null){
										driver.navigate().refresh();
										switchToFrame(driver, 30, getFrame(PageName.FundsPage, timeOut));
										System.err.println("Switched to frame.");
										scrollDownThroughWebelement(driver, getWorkspaceSectionView(workspace, timeOut), workspace.toString()+" View.");
										click(driver, getManageInvestorIcon(workspace, timeOut), "Manage investor Icon", action.BOOLEAN);
										if(selectAccountFromManageTargetPopUp(institutionOrLPName, checkProgressMsg, gmailEmailID, gmailPassword, sendersEmailID, receiversEmailID, mailSubject, workspace, timeOut)){
											return true;
										} else {
											return false;
										}
									} else {
										appLog.error("Target Account '"+institutionOrLPNamesToBeSelected[i]+"' is not selected.");
										BaseLib.sa.assertTrue(false,"Target Account '"+institutionOrLPNamesToBeSelected[i]+"' is not selected.");
									}
								}
								
							} else {
								return true;
							}
							
						} else {
							appLog.error("No abe to set criterion value for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
							sa.assertTrue(false,"Not able to set criterion value  for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
						}
					} else {
						appLog.error("Not able to set operator value for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
						sa.assertTrue(false,"Not able to set operator value  for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
					}
				} else {
					appLog.error("Not able to set field value for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
					sa.assertTrue(false,"Not able to set field value  for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
				}
				
			} catch (Exception e){
				e.printStackTrace();
				appLog.error(e.getStackTrace());
			}
		}
		return false;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param institutionOrLPName
	 * @param checkProgressMsg
	 * @param gmailEmailID
	 * @param gmailPassword
	 * @param sendersEmailID
	 * @param receiversEmailID
	 * @param mailSubject
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean verifyAccountInManageTargetPopUp(String institutionOrLPName, String checkProgressMsg, String gmailEmailID, String gmailPassword, String sendersEmailID, String receiversEmailID, String mailSubject, Workspace workspace, int timeOut){
		System.err.println("Going to break");
		String institutionOrLPNamesToBeSelected[] = institutionOrLPName.split("<break>");
		System.err.println("break successfull.");
		boolean flag = true;
		System.err.println(institutionOrLPNamesToBeSelected.length);
		for(int i = 0 ; i < institutionOrLPNamesToBeSelected.length ; i++){
//			if(selectInstitutionOrLP(institutionOrLPName, workspace, timeOut)){
//				if(click(driver, getInvestorAddedConfirmationCloseButton(workspace, timeOut), "Investor Addition confirmation Close Button", action.BOOLEAN));
//			}
			try{
				if (setFieldValueOnManageInvestor("Account:Legal Name", 1, workspace,timeOut)) {
					if (setOperatorValueOnManageInvestor("equals", 1,workspace,timeOut)) {
						if (setCriterionValueOnManageTarget("textbox", 1, institutionOrLPNamesToBeSelected[i].split("/")[0], "Account:Legal Name",workspace,timeOut)) {
							click(driver, getManageInvestorFilterApplyButton(workspace, 0), "Apply Button", action.BOOLEAN);
							System.err.println("inside for");
							System.err.println(institutionOrLPNamesToBeSelected[i]);
							WebElement ele = null;
							if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
								ele = getInstituionCheckBoxOn3Of3(institutionOrLPNamesToBeSelected[i], workspace, timeOut);
							} else {
								ele = getLimitedPartnerCheckBox(institutionOrLPNamesToBeSelected[i].split("/")[0], institutionOrLPNamesToBeSelected[i].split("/")[1], workspace, timeOut);
							}
							scrollDownThroughWebelement(driver, ele, "Investor Name '"+institutionOrLPNamesToBeSelected[i]+"'");
							if(isSelected(driver, ele, institutionOrLPNamesToBeSelected[i]+" check box")){
								return true;
								
							} else {
								return false;
							}
							
						} else {
							appLog.error("No abe to set criterion value for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
							sa.assertTrue(false,"Not able to set criterion value  for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
						}
					} else {
						appLog.error("Not able to set operator value for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
						sa.assertTrue(false,"Not able to set operator value  for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
					}
				} else {
					appLog.error("Not able to set field value for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
					sa.assertTrue(false,"Not able to set field value  for investor '"+institutionOrLPNamesToBeSelected[i]+"'");
				}
				
			} catch (Exception e){
				e.printStackTrace();
				appLog.error(e.getStackTrace());
			}
		}
		return false;
	}
	
	
	/**
	 * @author Ankit Jaiswal
	 * @param contactEmailId
	 * @param selectDeselect
	 * @param allOr1By1
	 * @param search
	 * @param workspace
	 * @param timeOut
	 * @return List<String>
	 */
	public List<String> selectDeselectContactFromContactAccess(List<String> contactEmailId, SelectDeselect selectDeselect, AllOr1By1 allOr1By1, String search, Workspace workspace, int timeOut){
//		if(click(driver, getContactAccessIcon(Workspace.FundraisingWorkspace, timeOut), "Contact Access Icon", action.BOOLEAN)){
//			if(verifyContactAccessExpandCollapse(workspace)){
		List<String> accessNotGivenContacts = new ArrayList<String>();
		boolean flag = false;
		if(search!=null && !search.isEmpty()){
			if(sendKeys(driver, getSearchTextBox(workspace, timeOut), search, "Contact access Search box", action.BOOLEAN)){
				if(click(driver, getSearchBtn(workspace, 60), workspace+" search button", action.SCROLLANDBOOLEAN)) {
					appLog.info("Search successfully done.");
				} else {
					appLog.error("Not able to click on search icon, So cannot provide contact access.");
					accessNotGivenContacts.add("Search Icon Not Working");
				}
			} else {
				appLog.error("Not able to pass value to search box, So cannot provide contact access.");
				accessNotGivenContacts.add("Search Box Not Working");
			}
		}
		if(allOr1By1.toString().equalsIgnoreCase(AllOr1By1.All.toString())){
			if(selectDeselect.toString().equalsIgnoreCase(SelectDeselect.Select.toString())){
				if(isSelected(driver, getSelectAllCheckBoxContactAccess(workspace, timeOut), "Select All check box")){
					appLog.info("Select All Check Box is already selected.");
				} else {
					appLog.info("Going to click on select All Check box.");
					flag = true;
				}
			} else {
				if(isSelected(driver,  getSelectAllCheckBoxContactAccess(workspace, timeOut), "Select All check box")){
					appLog.info("Going to click on select All Check box.");
					flag = true;
				} else {
					appLog.info("Select All check box is already deselected.");
				}
			}
			if(flag){
				if(click(driver, getSelectAllCheckBoxContactAccess(workspace, timeOut), "Select All Check box", action.BOOLEAN)){
					appLog.info("Successfully selected all contacts from the contact access.");
				} else {
					appLog.error("Not able to select contact for access.");
					accessNotGivenContacts.add("Not selected");
				}
			}
		} else {
			for(int i = 0; i < contactEmailId.size(); i++){
				WebElement ele = FindElement(driver, "//a[@href='mailto:"+contactEmailId.get(i)+"']/../preceding-sibling::span//input", contactEmailId.get(i)+" contact check box", action.BOOLEAN, timeOut);
				if(ele!=null){
					if(selectDeselect.toString().equalsIgnoreCase(SelectDeselect.Select.toString())){
						if(isSelected(driver, ele, contactEmailId.get(i)+" contact check box")){
							appLog.info(contactEmailId.get(i)+" contact is already selected.");
							continue;
						} else {
							appLog.info("Going to select '"+contactEmailId.get(i)+"' contact");
						}
					} else {
						if(isSelected(driver, ele, contactEmailId.get(i)+" contact check box")){
							appLog.info("Going to deselect '"+contactEmailId.get(i)+"' contact.");
						} else {
							appLog.info("'"+contactEmailId.get(i)+"' contact is not selected.");
							continue;
						}
					}
					if(click(driver, ele, contactEmailId.get(i)+" contact check box", action.SCROLLANDBOOLEAN)){
						appLog.info("Successfully selected '"+contactEmailId.get(i)+"'");
					} else {
						appLog.error("Contact check box cannot be clicked, So cannot provide acess to '"+contactEmailId.get(i)+"'.");
						accessNotGivenContacts.add(contactEmailId.get(i));
					}
				} else {
					appLog.error("Contact check box is not available '"+contactEmailId.get(i)+"'.");
					accessNotGivenContacts.add(contactEmailId.get(i));
				}
				
			}
		}
		return accessNotGivenContacts;
				
//			} else {
//				appLog.error("Not able to click on expand icon, So cannot provide access to '"+contactEmailId+"'.");
//			}
//		} else {
//			appLog.error("Cannot click on contact access icon, So cannot provide contact access to '"+contactEmailId+"'.");
//		}
	}

	/**
	 * @author Ankit Jaiswal
	 * @param filePath
	 * @param sheetName
	 * @param folderTemplateName
	 * @param WithOrWithOutFolderTemplate
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean importFolderTemplateBulk(String filePath,String sheetName, String folderTemplateName, WorkSpaceAction WithOrWithOutFolderTemplate, Workspace workspace, int timeOut){
		boolean flag = false;
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		if(WithOrWithOutFolderTemplate.toString().equalsIgnoreCase(WorkSpaceAction.WITHOUTEMPLATE.toString())){
			appLog.info("Building workspace without folder");
			flag=true;
		} else if (WithOrWithOutFolderTemplate.toString().equalsIgnoreCase(WorkSpaceAction.IMPORTFOLDERTEMPLATE.toString())){
			if(click(driver, getImportFolderTemplateButton(workspace, timeOut), "folder template Imprt Button", action.BOOLEAN)){
				if(selectVisibleTextFromDropDown(driver, getDisplayDropDown(workspace, timeOut), "Display Drop Down", "All Templates")){
					if(click(driver, getFolderTemplateRadioButton(folderTemplateName, workspace, timeOut), folderTemplateName+" Folder template radio Button", action.BOOLEAN)){
						if(click(driver, getFolderTemplateImportButton(workspace, timeOut), "Import Button", action.BOOLEAN)){
							appLog.info("Successfully imported folder template: "+folderTemplateName);
							flag=true;
						} else {
							appLog.error("Import button cannot be clicked, So cannot create "+workspace.toString()+" workspace.");
							BaseLib.sa.assertTrue(false,"Import button cannot be clicked, So cannot create "+workspace.toString()+" workspace.");
						}
					} else {
						appLog.error(folderTemplateName+" Folder template is not present in the list.");
						BaseLib.sa.assertTrue(false,folderTemplateName+" Folder template is not present in the list.");
					}
				} else {
					appLog.error("Not able to select Option from the drop down.");
					BaseLib.sa.assertTrue(false,"Not able to select Option from the drop down.");
				}
			} else {
				appLog.error("Import folder template button cannot be clicked, So cannot create workspace.");
				BaseLib.sa.assertTrue(false,"Import folder template button cannot be clicked, So cannot create workspace.");
			}
		} else if (WithOrWithOutFolderTemplate.toString().equalsIgnoreCase(WorkSpaceAction.CREATEFOLDERTEMPLATE.toString())){
			Map<String, String> s = folderStructureInExcel( filePath,sheetName);
			Set<String> paths = s.keySet();
			Iterator<String> i = paths.iterator();
			i = paths.iterator();
			FolderType folderType = null;
			while (i.hasNext()) {
				String string = i.next();
				if (string.isEmpty())
					continue;
				System.out.println("\n\n\nCreating folder template\n\n\n");
				if(s.get(string).equalsIgnoreCase("Shared")){
					folderType=FolderType.Shared;
				} else if (s.get(string).equalsIgnoreCase("Common")){
					folderType=FolderType.Common;
				} else if (s.get(string).equalsIgnoreCase("Internal")){
					folderType=FolderType.Internal;
				} else if (s.get(string).equalsIgnoreCase("Standard")){
					folderType=FolderType.Standard;
				}
				List<String> notCreatedFolders = nim.createFolderStructure(string, folderType, workspace, PageName.FundsPage, timeOut);
				if(notCreatedFolders.isEmpty()){
					flag=true;
				} else {
					String folderNames = createStringOutOfList(notCreatedFolders);
					BaseLib.sa.assertTrue(false,"Following folders are not created: "+folderNames);
				}
			}
//			if(verifyFolderStructure(folderStructureInExcel("FolderTemp"), 5)){
//				appLog.info("Folder strucuture is verified on build step 2 of 3.");
//			} else {
//				appLog.error("Folder structure is not verified on build step 2 of 3.");
//			}
		}
		return flag;
	}

	/**
	 * @author Ankit Jaiswal
	 * @param institutionOrLPName
	 * @param crmUsername
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean clickOnContactAccessApplyButton(String institutionOrLPName, String crmUsername, Workspace workspace, int timeOut){
		boolean flag = false;
		if(click(driver, getApplyBtn(workspace, timeOut), "Apply button", action.BOOLEAN)){
			if(isAlertPresent(driver)){
				String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
				switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
				if(msg.equalsIgnoreCase(FundsPageErrorMessage.WorkspaceLockedErrorMsg)){
					for(int i = 0; i < 1000; i++){
//						if(EmailLib.mailReceived(gmailUserName, gmailPassword, "noreply@navatargroup.com", crmUsername, "request update", "Processing request for Contact Access")){
						if(EmailLib.mailReceived(gmailUserName, gmailPassword, "noreply@navatargroup.com", crmUsername, "request update", "Processing request for Contact Access")){
							appLog.info("Mail received successfully.");
//							driver.navigate().refresh();
//							switchToFrame(driver, 30, getFrame(PageName.FundsPage, 30));
//							if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
//								if(clickOnInstituionFolder(institutionOrLPName, workspace, 30)){
//								}
//							} else {
//								if(clickOnLimitedPartnerFolder(institutionOrLPName.split("/")[0], institutionOrLPName.split("/")[1], workspace, timeOut)){
//									
//								}
//							}
//							if(click(driver, getContactAccessIcon(workspace, 30), "Contact access icon", action.BOOLEAN)){
//								if(verifyContactAccessExpandCollapse(workspace)){
//									
//								}
//							}
							flag = true;
							break;
						} else {
							appLog.info("Mail is not received, So will check for mail again.");
							if(i==999){
								appLog.error("Mail is not received in given time, So cannot continue the access process.");
								sa.assertTrue(false,"Mail is not received in given time, So cannot continue the access process.");
								return false;
							}
							ThreadSleep(5000);
						}
					}
				} else {
					appLog.info(msg + " message is displaying in alert.");
					sa.assertTrue(false,msg + " message is displaying in alert.");
					return false;
				}
			} else {
				appLog.info("No Alert present.");
			}
			driver.navigate().refresh();
			switchToFrame(driver, 30, getFrame(PageName.FundsPage, 30));
			if(institutionOrLPName!=null && workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				if(clickOnInstituionFolder(institutionOrLPName, workspace, 30)){
					if(click(driver, getContactAccessIcon(workspace, 30), "Contact access icon", action.BOOLEAN)){
						if(verifyContactAccessExpandCollapse(workspace)){
							
						}
					}
				}
			} else if (institutionOrLPName!=null) {
				if(clickOnLimitedPartnerFolder(institutionOrLPName.split("/")[0], institutionOrLPName.split("/")[1], workspace, timeOut)){
					if(click(driver, getContactAccessIcon(workspace, 30), "Contact access icon", action.BOOLEAN)){
						if(verifyContactAccessExpandCollapse(workspace)){
							
						}
					}
				}
			}
//			if(click(driver, getContactAccessIcon(workspace, 30), "Contact access icon", action.BOOLEAN)){
//				if(verifyContactAccessExpandCollapse(workspace)){
//					
//				}
//			}
			flag = true;
		} else {
			appLog.error("Apply button cannot be clicked, So cannot provide access.");
			sa.assertTrue(false,"Apply button cannot be clicked, So cannot provide access.");
		}
		return flag;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param loggedInUsername
	 * @param excelPath
	 * @param path
	 * @param institutionOrLPName
	 * @param dragFromFolder
	 * @param uploadFileAddTo
	 * @param uploadUpdate
	 * @param workspace
	 * @param pageName
	 * @param timeOut
	 * @return true/false
	 */
	public boolean uploadFileBulk(String loggedInUsername, String excelPath, String path, String institutionOrLPName, String dragFromFolder, UploadFileActions uploadFileAddTo, UploadFileActions uploadUpdate, Workspace workspace, PageName pageName, int timeOut){
		String institutionName=null;
		String limitedPartner=null;
		boolean flag = false;
		int counter = 0;
		int count =0;
		String dropImage = "DropLoc.JPG";
		CommonLib compare = new CommonLib();
		switchToFrame(driver, timeOut, getFrame(pageName, timeOut));
		scrollDownThroughWebelement(driver, getWorkspaceSectionView(workspace, timeOut), workspace.toString()+" view.");
		if(institutionOrLPName!=null){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				institutionName = institutionOrLPName.split("<break>")[0];
			} else {
				institutionName = institutionOrLPName.split("<break>")[0].split("/")[0];
				limitedPartner = institutionOrLPName.split("<break>")[0].split("/")[1];
			}
		}
		if(verifyFolderPathdummy(path, institutionName, limitedPartner, null, pageName, workspace, timeOut)){
			for(int z = 0; z == 0; z++)
			if(click(driver, getUploadIcon(workspace, timeOut), "Upload Icon", action.BOOLEAN)){
				String parentWin = switchOnWindow(driver);
				if(parentWin!=null && !parentWin.isEmpty()){
					if(path.contains("(Common)") || path.contains("(Shared)") || path.contains("(Internal)")){
						appLog.info("Will directly upload the files.");
						flag = true;
					} else {
						if(institutionOrLPName.split("<break>").length>1){
							ThreadSleep(20000);
							if(matchTitle(driver, "Navatar Investor Manager", 60)) {
								if(click(driver, getMultipleInstituionRadioButton(timeOut), "mulitple institution radio button", action.BOOLEAN)){
									if(uploadFileAddTo!=null && uploadFileAddTo.toString().equalsIgnoreCase(UploadFileActions.SelectAll.toString())) {
										if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
											if(!click(driver, getCheckAllInstitutions(30), "Select All Institutions", action.BOOLEAN)) {
												appLog.error("Not able to select all Institution.");
												BaseLib.sa.assertTrue(false,"Not able to select all Institution.");
											}
										} else {
											if(!click(driver, getCheckAllInstitutionsAndLimitedPartner(30), "Select All", action.BOOLEAN)) {
												appLog.error("Not able to select all LP.");
												BaseLib.sa.assertTrue(false,"Not able to select all LP.");
											}
										}
									} else {
										if(selectMultipleInstitutionOrLP(institutionOrLPName, workspace, timeOut)){
											appLog.info("Successfully selected all the required Insitution or LP.");
										}
									}
								} else {
									appLog.error("Not able to upload files in multiple instituions.");
									BaseLib.sa.assertTrue(false,"Not able to upload files in multiple instituions.");
								}
								
							}else {
//								appLog.error("Upload window is not open so cannot select Add to Multiple Institutions radio button so cannot continue upload file");
//								BaseLib.sa.assertTrue(false, "Upload window is not open so cannot select Add to Multiple Institutions radio button so cannot continue upload file");
								driver.close();
								driver.switchTo().window(parentWin);
								if (pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())) {
									System.out.println("Searching for the frame");
									switchToFrame(driver, 30, getFrame(PageName.FundsPage, 30));
								}
								if (count > 1) {
									appLog.error("Upload window is not open so cannot select Add to Multiple Institutions radio button so cannot continue upload file");
									BaseLib.sa.assertTrue(false, "Upload window is not open so cannot select Add to Multiple Institutions radio button so cannot continue upload file");
									return false;
								}
								count++;
								z--;
								continue;
							}
						} else if (uploadFileAddTo!=null && uploadFileAddTo.toString().equalsIgnoreCase(UploadFileActions.BulkUploaderOrFileSplitter.toString())){
							if(click(driver, getBulkUploaderOrFileSplitterRadioButton(timeOut), "Bulk Upload Radio Button", action.BOOLEAN)){
								dropImage="BulkUpload.jpg";
								flag = true;
							} else {
								appLog.error("Not able to click on bulk upload radio button.");
								BaseLib.sa.assertTrue(false,"Not able to click on bulk upload radio button.");
							}
						}
						System.err.println("Click on next button");
						if(click(driver, getUploadNextButton(workspace, timeOut), "Next Button", action.BOOLEAN)){
							flag = true;
						}
					}
					if(flag){
						if(dragDropFiles(dragFromFolder, dropImage)){
							List<WebElement> files = draggedFilesInFileUploadAtCRMSide();
							if (files.isEmpty()) {
								driver.close();
								driver.switchTo().window(parentWin);
								if (pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())) {
									System.out.println("Searching for the frame");
									switchToFrame(driver, 30, getFrame(PageName.FundsPage, 30));
								}
								if (counter > 1) {
									appLog.error(
											"Tried Upload for three times but still not able to upload file in folderpath: "
													+ path);
									BaseLib.sa.assertTrue(false,
											"Tried Upload for two times but still not able to upload file in folderpath: "
													+ path);
									return false;
								}
								counter++;
								z--;
								continue;
							}
							List<String> droppedFileNames = new ArrayList<String>();
							List<WebElement> droppedFiles = driver.findElements(By.xpath("//span[@class='File']/following-sibling::b"));
							if(!droppedFiles.isEmpty()){
								for(int i = 0; i < droppedFiles.size(); i++){
									droppedFileNames.add(getText(driver, droppedFiles.get(i), "Dropped Files", action.BOOLEAN).trim());
								}
								Collections.sort(droppedFileNames,compare);
								String previousuploadedFiles = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, identifyLabel(path, UploadFileActions.Upload));
								String newlyUploadedFiles = createStringOutOfList(droppedFileNames);
								if(previousuploadedFiles!=null && !previousuploadedFiles.isEmpty()){
									newlyUploadedFiles = previousuploadedFiles+"<break>"+newlyUploadedFiles;
								}
								if (ExcelUtils.writeData(excelPath, newlyUploadedFiles, "filePath", excelLabel.TestCases_Name, currentlyExecutingTC, identifyLabel(path, UploadFileActions.Upload))) {
									appLog.info("written uploaded file data to excel");
								}
								else {
									appLog.error("could not write uploaded files information to excel");
								}
							} else {
								appLog.error("0 files are dropped.");
								BaseLib.sa.assertTrue(false,"0 files are dropped.");
							}
							appLog.info("Successfully uploaded files.");
							
							BaseLib.ListofUploadedfiles = new ArrayList<String>();
							for (int j = 0; j < files.size(); j++) {
								BaseLib.ListofUploadedfiles.add(files.get(j).getText().trim());
							}
							if (!uploadUpdate.toString().equalsIgnoreCase(UploadFileActions.Update.toString())) {
								if (!path.contains("(Internal)")) {
									for (int j = 0; j < files.size(); j++) {
										BaseLib.uniquedocs.add(files.get(j).getText().trim());
									}
								}
							}
							WebElement ele= BaseLib.edriver.findElement(By.cssSelector("#btnSave"));
							scrollDownThroughWebelement(driver, ele, "save button");
							try{
//								if(ele.click()){
									ele.click();
									appLog.info("Clicked on Save Button");
									if(uploadUpdate.toString().equalsIgnoreCase(UploadFileActions.Update.toString())){
										List<String> duplicateFileName = new ArrayList<String>();
										List<WebElement> duplicateFiles = driver.findElements(By.xpath("//table[@id='GridViewDuplicateFiles']//tr/td[1]//span"));
										if(!duplicateFiles.isEmpty()){
											for(int i = 0; i < duplicateFiles.size(); i++){
												duplicateFileName.add(getAttribute(driver, duplicateFiles.get(i), "Duplicate File Name", "title"));
											}
											String previousUpdatedFiles = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, identifyLabel(path, UploadFileActions.Update));
											String newlyupdatedFiles = createStringOutOfList(duplicateFileName);
											if(previousUpdatedFiles!=null && !previousUpdatedFiles.isEmpty()){
												newlyupdatedFiles = previousUpdatedFiles+"<break>"+newlyupdatedFiles;
											}
											ExcelUtils.writeData(excelPath, newlyupdatedFiles, "filePath", excelLabel.TestCases_Name, currentlyExecutingTC, identifyLabel(path, UploadFileActions.Update));
										} else {
											appLog.error("There is no list of updated file");
											BaseLib.sa.assertTrue(false,"There is no list of updated file");
										}
										
										ele= BaseLib.edriver.findElement(By.cssSelector("#lnkReplaceAll"));
										scrollDownThroughWebelement(driver, ele, "update all button");
										try{
											ele.click();
											appLog.info("Clicked on update all button ");
											
										}catch(Exception e){
											appLog.error("Update Pop Up in not Displaying.");
											BaseLib.sa.assertTrue(false,"Update Pop Up in not Displaying.");
											flag = false;
										}
										
									}
									if(isAlertPresent(driver)){
										String alertText = switchToAlertAndGetMessage(driver, timeOut, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, timeOut, action.ACCEPT);
										if(alertText.contains(DocumentUploadSuccessMsg)){
											appLog.info("Upload success msg verified successfully.");
										} else {
											appLog.error("Upload successfull alert message is not verified.Expected: Document(s) uploaded successfully.\tActual: "+alertText);
											BaseLib.sa.assertTrue(false,"Upload successfull alert message is not verified.Expected: Document(s) uploaded successfully.\tActual: "+alertText);
											flag = false;
										}
									} else if(getUploadBulkDelayedMessage(timeOut)!=null) {
										appLog.info("document is not uploaded.. we will check from email");
										String msg = getUploadBulkDelayedMessage(180).getText();
										if(msg.contains("email") && msg.contains("Document") && msg.contains("upload")){
											for(int j = 0 ; j <= 100 ; j++)
												if(EmailLib.mailReceived(gmailUserName, gmailPassword, "noreply@navatarinvestor.com", loggedInUsername, "File upload status","file upload process has been completed" )){
													System.out.println("\n\n\nFound\n\n\n");
													break;
												} else {
													System.out.println("Mail is not found, Will Check for mail again.");
													if(j>=100){
														appLog.error("Mail is not received.");
														BaseLib.sa.assertTrue(false,"Mail is not received ");
														flag = false;
													}
													ThreadSleep(5000);
												}
										}else {
											appLog.error("msg warming on web page is not related to reading email regarding upload");
											sa.assertTrue(false, "msg warming on web page is not related to reading email regarding upload");
											flag = false;
										}
										if(!driver.getWindowHandle().equalsIgnoreCase(parentWin)){
											driver.close();
										}
									} else {
										appLog.info("upload Successfull alert message is not poping up.");
										BaseLib.sa.assertTrue(false,"upload Successfull alert message is not poping up.");
										flag = false;
									}
//								} else {
//									appLog.error("Cannot click on save button.");
//									BaseLib.sa.assertTrue(false,"Cannot click on save button.");
//									flag = false;
//								}
							}catch(Exception e){
								appLog.error(e.getMessage().toString());
								appLog.error("Cannot click on save button.");
								BaseLib.sa.assertTrue(false,"Cannot click on save button.");
								flag = false;
							}
						} else {
							appLog.error("Not able to upload files.");
							BaseLib.sa.assertTrue(false,"Not able to upload files.");
							flag = false;
						}
					}
				}
				driver.switchTo().window(parentWin);
			} else {
				appLog.error("Upload Icon cannot be clicked, So cannot continue with the upload process.");
				BaseLib.sa.assertTrue(false,"Upload Icon cannot be clicked, So cannot continue with the upload process.");
			}
		} else {
			appLog.error(path+" Folder structure is not verified.");
			BaseLib.sa.assertTrue(false,path+" Folder structure is not verified.");
		}
		return flag;
	}
	
	/**
	 * @author Ankur Rana
	 * @param loggedInUsername
	 * @param institution
	 * @param limitedPartner
	 * @param InstitutionOrLPNameForStdFolder
	 * @param folderPath
	 * @param documentPath
	 * @param fileName
	 * @param boxUserName
	 * @param boxPassword
	 * @param onlineImportFileAddTo
	 * @param WorkSpaceAction
	 * @param FolderType
	 * @param pageName
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean onlineImportBulk(String loggedInUsername, String institution, String limitedPartner, String InstitutionOrLPNameForStdFolder, String folderPath,
			String documentPath, String fileName, String boxUserName, String boxPassword,OnlineImportFileAddTo onlineImportFileAddTo,
			WorkSpaceAction WorkSpaceAction, FolderType FolderType, PageName pageName, Workspace workspace,int timeOut) {
		if (pageName.toString().equalsIgnoreCase(pageName.FundsPage.toString())) {
			switchToFrame(driver, 60, getFrame(pageName.FundsPage, 60));
		}
		String parentID = null;
		if (FolderType.toString().equalsIgnoreCase(FolderType.Standard.toString())) {
			if(!verifyFolderPathdummy(folderPath, institution , limitedPartner, null, pageName, workspace, timeOut)) {
				BaseLib.sa.assertTrue(false, folderPath + " :Folder Structure is not verified.");
				switchToDefaultContent(driver);
				return false;
			}
		} else {
			if(!verifyFolderPathdummy(folderPath, null, null, null, pageName, workspace, timeOut)) {
				BaseLib.sa.assertTrue(false, folderPath + " :Folder Structure is not verified.");
				switchToDefaultContent(driver);
				return false;
			}
		}
		InvestorFirmPageBusinesslayer ifp= new InvestorFirmPageBusinesslayer(driver);
			WebElement ele;
			if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
				scrollDownThroughWebelement(driver, getFundRaisingWorkSpaceSection(30), "fundraising workspace View");
				
			}else if (workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
				scrollDownThroughWebelement(driver, getInvestorWorkSpaceSection(30), "investor workspace View");
			}
			ele=getOnlineImportLink(workspace, timeOut);
		if (click(driver, ele, "Online Import Icon", action.BOOLEAN)) {
			parentID = switchOnWindow(driver);
			sendKeys(driver, getBoxUserName(60), ExcelUtils.readDataFromPropertyFile("BoxUsername"),
					"Box username Text Box", action.THROWEXCEPTION);
			sendKeys(driver, getBoxPasswordTextBox(60), ExcelUtils.readDataFromPropertyFile("BoxPassword"),
					"Box Password Text Box", action.THROWEXCEPTION);
			click(driver, getBoxAuthorizeButton(60), "Authorize button", action.THROWEXCEPTION);
			click(driver, getGrantAccessToBoxButton(60), "Grant Access To Box Button", action.THROWEXCEPTION);
			if (FolderType.toString().equalsIgnoreCase(FolderType.Standard.toString())) {
				if (selectInstitutionOnUploadWindow(InstitutionOrLPNameForStdFolder,onlineImportFileAddTo, parentID, null,workspace)) {
					appLog.info("Successfully selected account: " + InstitutionOrLPNameForStdFolder);
				} else {
					appLog.info("Cannot select account: " + InstitutionOrLPNameForStdFolder);
					BaseLib.sa.assertTrue(false, InstitutionOrLPNameForStdFolder + " :Account not selected.");
				}
			}
			if (CommonLib.traverseImport(driver, documentPath, fileName)) {
				if (click(driver, getImportButton(60), "Online Import Button", action.BOOLEAN)) {
					System.err.println("Clikced");
					if (WorkSpaceAction.toString().equalsIgnoreCase(WorkSpaceAction.UPDATE.toString())) {
						click(driver, getUpdateAllButton(60), "Update All Button", action.BOOLEAN);
					}
					if (isAlertPresent(driver)) {
						String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
						switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
						if(msg.trim().toLowerCase().equalsIgnoreCase(OnlineImportSuccessMsg.toLowerCase())) {
							appLog.info("Online import success alert message is matched successfully.");
						
						}else {
							appLog.error("Online Import Success Message is not matched. Expected: "+OnlineImportSuccessMsg);
							BaseLib.sa.assertEquals(msg, OnlineImportSuccessMsg,
									"Online Import Success Message is not matched.");							
						}
					} else if(getUploadBulkDelayedMessage(timeOut)!=null) {
						appLog.info("document is not uploaded.. we will check from email");
						String msg = getUploadBulkDelayedMessage(180).getText();
						if(msg.contains("email") && msg.contains("Document") && msg.contains("upload")){
							for(int j = 0 ; j <= 100 ; j++)
								if(EmailLib.mailReceived(gmailUserName, gmailPassword, "noreply@navatarinvestor.com", loggedInUsername, "File upload status","file upload process has been completed" )){
									System.out.println("\n\n\nFound\n\n\n");
									break;
								} else {
									System.out.println("Mail is not found, Will Check for mail again.");
									if(j>=100){
										appLog.error("Mail is not received.");
										BaseLib.sa.assertTrue(false,"Mail is not received ");
										driver.close();
										driver.switchTo().window(parentID);
										return false;
									}
									ThreadSleep(5000);
								}
						}else {
							appLog.error("msg warming on web page is not related to reading email regarding upload");
							sa.assertTrue(false, "msg warming on web page is not related to reading email regarding upload");
							driver.close();
							driver.switchTo().window(parentID);
							return false;
						}
							driver.close();
					} else {
						driver.close();
						driver.switchTo().window(parentID);
						BaseLib.sa.assertFalse(false, "Online Import success alert message didn't displayed");
					}
					driver.switchTo().window(parentID);
					return true;

				} else {
					appLog.error("Import Button is not visible so cannot upload document: "+fileName);
					BaseLib.sa.assertTrue(false, "Import Button is not visible.");
					driver.close();
					driver.switchTo().window(parentID);
					return false;
				}
			} else {
				BaseLib.sa.assertTrue(false, "Path passed or file name passed is not correct. "
						+ folderPath + " or " + fileName);
				driver.close();
				driver.switchTo().window(parentID);
				return false;
			}
		} else {
			exit("Online import link is not working so cannot continue with this test case.");
			return false;
		}
	}

	/**
	 * @author Ankit Jaiswal
	 * @param fundName
	 * @return true/false
	 */
	public boolean clickOnCreatedFund(String fundName) {
		int i=1;
		if (getSelectedOptionOfDropDown(driver, getViewDropdown(60), "View dropdown", "text").equalsIgnoreCase("All")) {
			if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {

			}
			else {
				appLog.error("Go button not found");
				return false;
			}
		}
		else{
			if (selectVisibleTextFromDropDown(driver, getViewDropdown(60),"View dropdown","All") ){
			}
			else {
				appLog.error("All Funds not found in dropdown");
				return false;
			}

		}
			WebElement fund = getFundNameAtFundPage(fundName, 20);
			if (fund != null) {
				if (click(driver, fund, "Fund Name", action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on fund name : " + fundName);
					return true;
					} 
			} else {
		
				//
				
				while (true) {
					appLog.error("Fund Name is not Displaying on "+i+ " Page:" + fundName);
					
					if (click(driver, getNextImageonPage(10), "Fund Page Next Button",
							action.SCROLLANDBOOLEAN)) {

						appLog.info("Clicked on Next Button");
						 fund = getFundNameAtFundPage(fundName, 20);
						if (fund != null) {
							if (click(driver, fund, "Fund Name", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on fund name : " + fundName);
								return true;
								
							}
						}

						

					} else {
						appLog.error("Fund Not Available : " + fundName);
						return false;
					}
					i++;
				}
				
				//
				
			}
			return false;
		
		
	}
	

	public SoftAssert verifySelectedContactsGridDataInContactAccess(Workspace workspace,String contactDetails) {
		SoftAssert saa = new SoftAssert();
		String[] contactDetailList=contactDetails.split("<break>");
		List<WebElement> lst = getSelectedContactDataList(workspace, 30);
		for(int i=0; i<contactDetailList.length; i++) {
			for(int j=0; j<lst.size(); j++) {
				lst = getSelectedContactDataList(workspace, 30);
				String aa = lst.get(j).getText().trim();
				System.err.println("Selected Contacts data : "+aa);
				if(aa.contains(contactDetailList[i])) {
					appLog.info(contactDetailList[i]+" is displaying in selected contacts grid");
					break;
				}else {
					if(j==lst.size()-1) {
						appLog.error(contactDetailList[i]+" is not displaying in selected contacts grid");
						saa.assertTrue(false, contactDetailList[i]+" is not displaying in selected contacts grid");
					}
				}
			}
		}
		return saa;
	}
	

	public boolean verifyUIOfContactAccess(Workspace workspace, String cols, String folder) {
		boolean flag = true;
		if (getContactAccessHeadText(workspace, 30).getText().trim().equalsIgnoreCase("Contact Access: "+folder)) {
			appLog.info("successfully verified head text and folder name of contact access");
			
		}
		else {
			flag = false;
			appLog.error("could not verify head text and folder name of contact access");
			sa.assertTrue(false, "could not verify head text and folder name of contact access");
		}
		if (getContactAccessFolderName(workspace, 30).getText().trim().equalsIgnoreCase(folder)) {
			appLog.info("successfully verified FolderName of contact access");
			
		}
		else {
			flag = false;
			appLog.error("could not verify FolderName of contact access");
			sa.assertTrue(false, "could not verify FolderName of contact access");
		}
		if (getContactAccessCrossIcon(workspace, 30)!=null) {
			appLog.info("successfully verified cross icon of contact access");
		}
		else {
			flag = false;
			appLog.error("could not verify cross icon of contact access");
			sa.assertTrue(false, "could not verify cross icon of contact access");
		}
		if (verifyContactAccessExpandCollapse(workspace)) {
			if (verifyContactAccessCols(workspace, cols)){
				appLog.info("successfully matched all columns in contact access popup");
			}
			else {
				flag = false;
				appLog.error("could not verify all columns in contact access popup");
				sa.assertTrue(false, "could not verify all columns in contact access popup");
			}
			if (getViewDocsIcon(30)!=null && getUploadDocsIcon(30)!=null) {
				appLog.info("successfully verified upload docs icon and view docs icon");
			}
			else {
				appLog.error("could not verify upload docs and view docs icon");
				sa.assertTrue(false, "could not verify upload docs and view docs icon");
			}
			if (noDataToDisplayContactAccess(workspace, 30).getText().trim().equals(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
				appLog.info("successfully found contact access no data to display(selected contacts)");
			}
			else {
				flag = false;
				appLog.error("no data to display is not found on contact access(selected contacts)");
				sa.assertTrue(false, "no data to display is not found on contact access(selected contacts)");
			}
			if (applyButtonContactAccessDeactive(workspace)!=null) {
				appLog.info("successfully found contact access apply button");
			}
			else {
				flag = false;
				appLog.error("apply button is not found on contact access");
				sa.assertTrue(false, "apply button is not found on contact access");
			}
			if (cancelButtonContactAccessActive(workspace)!=null) {
				appLog.info("successfully found contact access cancel button");
			}
			else {
				flag = false;
				appLog.error("cancel button is not found on contact access");
				sa.assertTrue(false, "cancel button is not found on contact access");
			}
		}
		return flag;
	}

	public boolean verifyContactAccessCols(Workspace workspace, String cols) {
		String[] arr=cols.split("<break>");
		List<WebElement> li=listOfColumnsContactAccess(workspace);
		for (int i = 1;i<arr.length;i++) {
			if (arr[i].equalsIgnoreCase(li.get(i).getText().trim())) {
				appLog.info("matched "+arr[i]+ " with "+li.get(i).getText().trim());
			}
			else {
				appLog.error("could not compare "+arr[i]+" "+li.get(i).getText().trim());
				return false;
			}
		}
		return true;
	}
	
	public boolean compareFirmColumnContactAccess(Workspace workspace, List<String> institutions) {
		String w = "";
		boolean flag = true;
		if (workspace == Workspace.InvestorWorkspace)
			w="INV";
		else
			w="FR";
		List<WebElement> li = FindElements(driver, "//span[contains(@id,'grid10_DealDetailBW"+w+"-cell-3')]","firm column contact access");
		for (int i = 0;i<li.size();i++) {
			scrollDownThroughWebelement(driver, li.get(i), i+1+" th element");
			if (institutions.contains(li.get(i).getText().trim())) {
				appLog.info(i+1+" th element is correct institution");
			}
			else
			{
				flag = false;
				appLog.error("could not find "+li.get(i).getText().trim());
			}
		}
		return flag;
	}
	
	public WebElement checkboxOnContactAccessUI(Workspace workspace, String emailID) {
		WebElement contactcheckBox=null;
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWFR_MA']//a[text()='"+emailID+"']/../..//input", emailID+" contact check box", action.SCROLLANDBOOLEAN, 30);
			scrollDownThroughWebelement(driver,contactcheckBox, "");
		}else if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
			contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWINV_MA']//a[text()='"+emailID+"']/../..//input", emailID+" contact check box", action.SCROLLANDBOOLEAN, 30);
			scrollDownThroughWebelement(driver,contactcheckBox, "");
			
		}
		return contactcheckBox;
	}

	public WebElement uploadDownloadCheckboxContactAccess(Workspace workspace, CheckBoxName cbn, String email) {
		String xpath = "";
		if (workspace == Workspace.InvestorWorkspace) {
			if (cbn == CheckBoxName.Download) {
				xpath="//span[contains(@id,'grid11_DealDetailBWINV-cell-3')]/a[text()='"+email+"']/../../span[8]//input";
			}
			else
				xpath="//span[contains(@id,'grid11_DealDetailBWINV-cell-3')]/a[text()='"+email+"']/../../span[9]//input";
		}
		else {
			if (cbn == CheckBoxName.Download) {
				xpath="//span[contains(@id,'grid11_DealDetailBWFR-cell-3')]/a[text()='"+email+"']/../../span[8]//input";
				
			}
			else
			xpath="//span[contains(@id,'grid11_DealDetailBWFR-cell-3')]/a[text()='"+email+"']/../../span[9]//input";
			
		}
	return isDisplayed(driver, FindElement(driver, xpath, "checkbox", action.BOOLEAN, 30), "visibility", 30, "checkbox");
		
	}
	
	public WebElement checkBoxonSelectContactsAtContactAccessIcon(Workspace workspace, String contactEmailId,int timeOut) {
		String xpath = "";
		if (workspace == Workspace.InvestorWorkspace) {
			xpath = "//div[@id='shwTopGridBWINV_MA']//a[text()='"+contactEmailId+"']/../..//input";
		}
		else {
			xpath = "//div[@id='shwTopGridBWFR_MA']//a[text()='"+contactEmailId+"']/../..//input";	
			
		}
	return isDisplayed(driver, FindElement(driver, xpath, "checkbox", action.BOOLEAN, timeOut), "visibility", timeOut, "checkbox");
		
	}
	
	public boolean verifySelectContactsGridDataInContactAccess(Workspace workspace, EnableDisable enableDisable,
			String contactName, String grantedAccessOn, String email, String firmName, int timeOut) {

		String xpath = "";
		WebElement ele;
		String prefix = "";

		if (workspace == Workspace.InvestorWorkspace) {
			xpath = "INV";
		} else {
			xpath = "FR";
		}

		if (EnableDisable.Disable.toString().equalsIgnoreCase(enableDisable.toString())) {
			prefix = "//span[@id='grid11_DealDetailBW" + xpath + "-rows']//span[contains(text(),'Remove')]";
		} else {
			prefix = "//span[@id='grid11_DealDetailBW" + xpath + "-rows']//span/a[contains(text(),'Remove')]/..";
		}

		String xpath1 = prefix + "/following-sibling::span[text()='" + contactName + "']";
		String xpath2 = "/following-sibling::span[text()='" + grantedAccessOn + "']";
		String xpath3 = "/following-sibling::span/a[text()='" + email + "']/../following-sibling::span[text()='" + firmName+ "']";
		xpath = xpath1 + xpath2 + xpath3;
		ele = FindElement(driver, xpath, "checkbox", action.BOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "visibility", timeOut, "checkbox");

		if (ele != null) {
			return true;
		}
		return false;
	}
	
	public WebElement getContactViewOrUploadOrDownloadCheckBox(Workspace workspace, EnableDisable enableDisable,
			String contactName, int timeOut, CheckBoxName checkBoxName) {
		WebElement ele = null;
		int i = 0;
		String xpath = "";
		String prefix;
		if (workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			xpath = "FR";
		} else {
			xpath = "INV";
		}

		if (EnableDisable.Disable.toString().equalsIgnoreCase(enableDisable.toString())) {
			prefix = "//span[@id='grid11_DealDetailBW" + xpath + "-rows']//span[contains(text(),'Remove')]";
		} else {
			prefix = "//span[@id='grid11_DealDetailBW" + xpath + "-rows']//span/a[contains(text(),'Remove')]/..";
		}

		if (checkBoxName.toString().equalsIgnoreCase(CheckBoxName.View.toString())) {
			i = 5;
		} else if (checkBoxName.toString().equalsIgnoreCase(CheckBoxName.Download.toString())) {
			i = 6;
		} else {
			i = 7;
		}
		String xpath1 = prefix + "/following-sibling::span[text()='" + contactName + "']";
		String xpath2 = "/following-sibling::span[contains(@id,'grid11_DealDetailBW" + xpath +"-cell-" + i + "')]//input";
		xpath = xpath1 + xpath2;
		ele = FindElement(driver, xpath, "Contact " + checkBoxName.toString() + " check box in selected contact grid",action.SCROLLANDBOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "visibility", timeOut,
				"Contact " + checkBoxName.toString() + " check box in selected contact grid");
		return ele;
	}
	
	public WebElement verifySelectContactsGridDataInContactAccess(Workspace workspace,String contactName,String email,String firmName,int timeOut) {
		
		WebElement ele = null;
		String xpath="";
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			xpath="FR";
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			xpath="INV";
		}
		FindElement(driver, "//span[@id='grid10_DealDetailBW"+xpath+"-rows']//span[text()='"+contactName+"']", "Contact NamE", action.SCROLLANDBOOLEAN, timeOut);
		xpath = "//span[@id='grid10_DealDetailBW"+xpath+"-rows']//span[text()='"+contactName+"']/..//following-sibling::span/a[text()='"+email+"']/..//following-sibling::span[text()='"+firmName+"']";
		ele= FindElement(driver, xpath, "Select Contact Grid for : "+contactName, action.SCROLLANDBOOLEAN, timeOut);
		ele=isDisplayed(driver, ele, "Visibility", timeOut, "Select Contact Grid for : "+contactName);
		return ele;
	}
	
	public boolean performSortingCheckOnContactAccessPopUpSelectContactGrid(Workspace workspace,int timeOut) {
		boolean flag = true;
		WebElement ele = null;
		String w;
		if (workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			w="FR";
		} else {
			w="INV";
		}
		String xpath = "//div[@id='shwTopGridBW"+w+"_MA']//span[contains(@id,'grid10_DealDetailBW"+w+"-cell-1')]";
		List<WebElement> values = FindElements(driver, xpath, "Contact Name Column Value List");
		
		String sortIcon = "//div[@id='shwTopGridBW"+w+"_MA']//span[@id='grid10_DealDetailBW"+w+"-header-1-box-text-sort']";
		ele = FindElement(driver, sortIcon, "Sort Icon on Contact Name Column", action.BOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "Visibilty", timeOut, "Sort Icon on Contact Name Column");
		if (ele!=null) {
			appLog.info("Sort Icon Visible for Contact Name Column");
		}
		else {
			appLog.error("Sort Icon Not Visible for Contact Name Column");
			flag= false;
		}
		
		appLog.info("checking Default sorting on Contact Name column");
		if (checkSorting(SortOrder.Assecending, values)){
			appLog.info(" Default Assecending sorting Verified on Contact Name column");
		}
		else {
			appLog.error(" Default Assecending sorting Not Verified on Contact Name column");
			flag= false;
		}
		
		appLog.info("checking sorting for Contact Name column");
		click(driver, getContactAccessPopUpSelectHeader(workspace).get(1), "Contact Name column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		xpath = "//div[@id='shwTopGridBW"+w+"_MA']//span[contains(@id,'grid10_DealDetailBW"+w+"-cell-1')]";
		values = FindElements(driver, xpath, "Contact Name Column Value List");
		
		sortIcon = "//div[@id='shwTopGridBW"+w+"_MA']//span[@id='grid10_DealDetailBW"+w+"-header-1-box-text-sort']";
		ele = FindElement(driver, sortIcon, "Sort Icon on Contact Name Column", action.BOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "Visibilty", timeOut, "Sort Icon on Contact Name Column");
		if (ele!=null) {
			appLog.info("Sort Icon Visible for Contact Name Column");
		}
		else {
			appLog.error("Sort Icon Not Visible for Contact Name Column");
			flag= false;
		}
		
		if (checkSorting(SortOrder.Decending, values)){
			appLog.info("correct sorting is present for Contact Name column in Decending order");
		}
		else {
			appLog.error("Incorrect sorting is present for Contact Name column in Decending order");
			flag = false;
		}
		
		appLog.info("checking sorting for Email column");
		click(driver, getContactAccessPopUpSelectHeader(workspace).get(2), "Email column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		sortIcon = "//div[@id='shwTopGridBW"+w+"_MA']//span[@id='grid10_DealDetailBW"+w+"-header-2-box-text-sort']";
		ele = FindElement(driver, sortIcon, "Sort Icon on Email Column", action.BOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "Visibilty", timeOut, "Sort Icon on Email Column");
		if (ele!=null) {
			appLog.info("Sort Icon Visible for Email Column");
		}
		else {
			appLog.error("Sort Icon Not Visible for Email Column");
			flag= false;
		}
		xpath = "//div[@id='shwTopGridBW"+w+"_MA']//span[contains(@id,'grid10_DealDetailBW"+w+"-cell-2')]";
		values = FindElements(driver, xpath, "EmailColumn Value List");
		if (checkSorting(SortOrder.Assecending, values)){
			appLog.info("correct sorting is present for Email column in Assecending order");
		}
		else {
			appLog.error("Incorrect sorting is present for Email column in Assecending order");
			flag = false;
		}
		
		click(driver, getContactAccessPopUpSelectHeader(workspace).get(2), "Email column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		sortIcon = "//div[@id='shwTopGridBW"+w+"_MA']//span[@id='grid10_DealDetailBW"+w+"-header-2-box-text-sort']";
		ele = FindElement(driver, sortIcon, "Sort Icon on Email Column", action.BOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "Visibilty", timeOut, "Sort Icon on Email Column");
		if (ele!=null) {
			appLog.info("Sort Icon Visible for Email Column");
		}
		else {
			appLog.error("Sort Icon Not Visible for Email Column");
			flag= false;
		}
		xpath = "//div[@id='shwTopGridBW"+w+"_MA']//span[contains(@id,'grid10_DealDetailBW"+w+"-cell-2')]";
		values = FindElements(driver, xpath, "Email Column Value List");
		if (checkSorting(SortOrder.Decending,values)){
			appLog.info("correct sorting is present for Email in Decending order");
		}
		else {
			appLog.error("Incorrect sorting is present for Email in Decending order");
			flag = false;
		}
		
		sendKeys(driver, getSearchTextBox(workspace, 30), "a", "search textbox", action.BOOLEAN);
		click(driver, getSearchBtn(workspace, 30), "search button", action.BOOLEAN);
		xpath = "//div[@id='shwTopGridBW"+w+"_MA']//span[contains(@id,'grid10_DealDetailBW"+w+"-cell-1')]";
		values = FindElements(driver, xpath, "Contact Name Column Value List");
		
		sortIcon = "//div[@id='shwTopGridBW"+w+"_MA']//span[@id='grid10_DealDetailBW"+w+"-header-1-box-text-sort']";
		ele = FindElement(driver, sortIcon, "Sort Icon on Contact Name Column", action.BOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "Visibilty", timeOut, "Sort Icon on Contact Name Column");
		if (ele!=null) {
			appLog.info("Sort Icon Visible for Contact Name Column");
		}
		else {
			appLog.error("Sort Icon Not Visible for Contact Name Column");
			flag= false;
		}
		
		appLog.info("checking Default sorting on Contact Name column");
		if (checkSorting(SortOrder.Assecending, values)){
			appLog.info(" Default Assecending sorting Verified on Contact Name column");
		}
		else {
			appLog.error(" Default Assecending sorting Not Verified on Contact Name column");
			flag= false;
		}
		
		
		appLog.info("checking sorting for Firm column");
		click(driver, getContactAccessPopUpSelectHeader(workspace).get(3), "Firm column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		sortIcon = "//div[@id='shwTopGridBW"+w+"_MA']//span[@id='grid10_DealDetailBW"+w+"-header-3-box-text-sort']";
		ele = FindElement(driver, sortIcon, "Sort Icon on Firm Column", action.BOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "Visibilty", timeOut, "Sort Icon on Firm Column");
		if (ele!=null) {
			appLog.info("Sort Icon Visible for Firm Column");
		}
		else {
			appLog.error("Sort Icon Not Visible for Firm Column");
			flag= false;
		}
		xpath = "//div[@id='shwTopGridBW"+w+"_MA']//span[contains(@id,'grid10_DealDetailBW"+w+"-cell-3')]";
		values = FindElements(driver, xpath, "Firm Column Value List");
		if (checkSorting(SortOrder.Assecending, values)){
			appLog.info("correct sorting is present for Firm column in Assecending order");
		}
		else {
			appLog.error("Incorrect sorting is present for Firm column in Assecending order");
			flag = false;
		}
		
		click(driver, getContactAccessPopUpSelectHeader(workspace).get(3), "Firm column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		sortIcon = "//div[@id='shwTopGridBW"+w+"_MA']//span[@id='grid10_DealDetailBW"+w+"-header-3-box-text-sort']";
		ele = FindElement(driver, sortIcon, "Sort Icon on Firm Column", action.BOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "Visibilty", timeOut, "Sort Icon on Firm Column");
		if (ele!=null) {
			appLog.info("Sort Icon Visible for Firm Column");
		}
		else {
			appLog.error("Sort Icon Not Visible for Firm Column");
			flag= false;
		}
		xpath = "//div[@id='shwTopGridBW"+w+"_MA']//span[contains(@id,'grid10_DealDetailBW"+w+"-cell-3')]";
		values = FindElements(driver, xpath, "Firm Column Value List");
		if (checkSorting(SortOrder.Decending, values)){
			appLog.info("correct sorting is present for Firm in Decending order");
		}
		else {
			appLog.error("Incorrect sorting is present for Firm in Decending order");
			flag = false;
		}
		
		return flag;
		
	}
	
	public boolean performSortingCheckOnContactAccessPopUpSelectedContactGrid(Workspace workspace,int timeOut) {
		boolean flag = true;
		WebElement ele = null;
		String w;
		if (workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			w="FR";
		} else {
			w="INV";
		}
		
		// Granted Access On
		String xpath = "//div[@id='shwBottomGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-cell-2')]";
		List<WebElement> values = FindElements(driver, xpath, "Granted Access on Column Value List");
		
		String sortIcon = "//div[@id='shwBottomGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-header-2-box-text-sort')]";
		ele = FindElement(driver, sortIcon, "Sort Icon on Granted Access on Column", action.BOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "Visibilty", timeOut, "Sort Icon on Granted Access on Column");
		if (ele!=null) {
			appLog.info("Sort Icon Visible for Granted Access on Column");
		}
		else {
			appLog.error("Sort Icon Not Visible for Granted Access on Column");
			flag= false;
		}
		
		appLog.info("checking Default sorting on Granted Access on column");
		if (checkSorting(SortOrder.Decending, values)){
			appLog.info(" Default Decending sorting Verified on Granted Access on column");
		}
		else {
			appLog.error(" Default Decending sorting Not Verified on Granted Access on column");
			flag= false;
		}
		
		appLog.info("checking sorting for Granted Access on column");
		click(driver, getContactAccessPopUpSelectedHeader(workspace).get(2), "Granted Access on column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		xpath = "//div[@id='shwBottomGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-cell-2')]";
		values = FindElements(driver, xpath, "Granted Access on Column Value List");
		
		sortIcon = "//div[@id='shwBottomGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-header-2-box-text-sort')]";
		ele = FindElement(driver, sortIcon, "Sort Icon on Granted Access on Column", action.BOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "Visibilty", timeOut, "Sort Icon on Granted Access on Column");
		if (ele!=null) {
			appLog.info("Sort Icon Visible for Granted Access on Column");
		}
		else {
			appLog.error("Sort Icon Not Visible for Granted Access on Column");
			flag= false;
		}
		
		if (checkSorting(SortOrder.Assecending, values)){
			appLog.info("correct sorting is present for Granted Access on column in Assecending order");
		}
		else {
			appLog.error("Incorrect sorting is present for Granted Access on column in Assecending order");
			flag = false;
		}
		
		// Contact Name On
		appLog.info("checking sorting for Contact Name column");
		click(driver, getContactAccessPopUpSelectedHeader(workspace).get(1), "Contact Name column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		xpath = "//div[@id='shwBottomGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-cell-1')]";
		values = FindElements(driver, xpath, "Contact Name Column Value List");
		sortIcon = "//div[@id='shwBottomGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-header-1-box-text-sort')]";
		ele = FindElement(driver, sortIcon, "Sort Icon on Contact Name Column", action.BOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "Visibilty", timeOut, "Sort Icon on Contact Name Column");
		if (ele!=null) {
			appLog.info("Sort Icon Visible for Contact Name Column");
		}
		else {
			appLog.error("Sort Icon Not Visible for Contact Name Column");
			flag= false;
		}
		
		appLog.info("checking first sorting on Contact Name column");
		if (checkSorting(SortOrder.Decending, values)){
			appLog.info(" Decending sorting Verified on Contact Name column");
		}
		else {
			appLog.error(" Decending sorting Not Verified on Contact Name column");
			flag= false;
		}
		
		appLog.info("checking sorting for Contact Name column");
		click(driver, getContactAccessPopUpSelectedHeader(workspace).get(1), "Contact Name column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		xpath = "//div[@id='shwBottomGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-cell-1')]";
		values = FindElements(driver, xpath, "Contact Name Column Value List");
		sortIcon = "//div[@id='shwBottomGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-header-1-box-text-sort')]";
		ele = FindElement(driver, sortIcon, "Sort Icon on Contact Name Column", action.BOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "Visibilty", timeOut, "Sort Icon on Contact Name Column");
		if (ele!=null) {
			appLog.info("Sort Icon Visible for Contact Name Column");
		}
		else {
			appLog.error("Sort Icon Not Visible for Contact Name Column");
			flag= false;
		}
		
		if (checkSorting(SortOrder.Assecending, values)){
			appLog.info("correct sorting is present for Contact Name column in Assecending order");
		}
		else {
			appLog.error("Incorrect sorting is present for Contact Name column in Assecending order");
			flag = false;
		}
		
		// Email
		appLog.info("checking sorting for Email column");
		click(driver, getContactAccessPopUpSelectedHeader(workspace).get(3), "Email column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		sortIcon = "//div[@id='shwBottomGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-header-3-box-text-sort')]";
		ele = FindElement(driver, sortIcon, "Sort Icon on Email Column", action.BOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "Visibilty", timeOut, "Sort Icon on Email Column");
		if (ele!=null) {
			appLog.info("Sort Icon Visible for Email Column");
		}
		else {
			appLog.error("Sort Icon Not Visible for Email Column");
			flag= false;
		}
		xpath = "//div[@id='shwBottomGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-cell-3')]";
		values = FindElements(driver, xpath, "EmailColumn Value List");
		if (checkSorting(SortOrder.Decending, values)){
			appLog.info("correct sorting is present for Email column in Decending order");
		}
		else {
			appLog.error("Incorrect sorting is present for Email column in Decending order");
			flag = false;
		}
		
		click(driver, getContactAccessPopUpSelectedHeader(workspace).get(3), "Email column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		sortIcon = "//div[@id='shwBottomGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-header-3-box-text-sort')]";
		ele = FindElement(driver, sortIcon, "Sort Icon on Email Column", action.BOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "Visibilty", timeOut, "Sort Icon on Email Column");
		if (ele!=null) {
			appLog.info("Sort Icon Visible for Email Column");
		}
		else {
			appLog.error("Sort Icon Not Visible for Email Column");
			flag= false;
		}
		xpath = "//div[@id='shwBottomGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-cell-3')]";
		values = FindElements(driver, xpath, "Email Column Value List");
		if (checkSorting(SortOrder.Assecending,values)){
			appLog.info("correct sorting is present for Email in Assecending order");
		}
		else {
			appLog.error("Incorrect sorting is present for Email in Assecending order");
			flag = false;
		}
		
		//Firm name
		appLog.info("checking sorting for Firm column");
		click(driver, getContactAccessPopUpSelectedHeader(workspace).get(4), "Firm column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		sortIcon = "//div[@id='shwBottomGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-header-4-box-text-sort')]";
		ele = FindElement(driver, sortIcon, "Sort Icon on Firm Column", action.BOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "Visibilty", timeOut, "Sort Icon on Firm Column");
		if (ele!=null) {
			appLog.info("Sort Icon Visible for Firm Column");
		}
		else {
			appLog.error("Sort Icon Not Visible for Firm Column");
			flag= false;
		}
		xpath = "//div[@id='shwBottomGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-cell-4')]";
		values = FindElements(driver, xpath, "Firm Column Value List");
		if (checkSorting(SortOrder.Decending, values)){
			appLog.info("correct sorting is present for Firm column in Decending order");
		}
		else {
			appLog.error("Incorrect sorting is present for Firm column in Decending order");
			flag = false;
		}
		
		click(driver, getContactAccessPopUpSelectedHeader(workspace).get(4), "Firm column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		sortIcon = "//div[@id='shwBottomGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-header-4-box-text-sort')]";
		ele = FindElement(driver, sortIcon, "Sort Icon on Firm Column", action.BOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "Visibilty", timeOut, "Sort Icon on Firm Column");
		if (ele!=null) {
			appLog.info("Sort Icon Visible for Firm Column");
		}
		else {
			appLog.error("Sort Icon Not Visible for Firm Column");
			flag= false;
		}
		xpath = "//div[@id='shwBottomGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-cell-4')]";
		values = FindElements(driver, xpath, "Firm Column Value List");
		if (checkSorting(SortOrder.Assecending, values)){
			appLog.info("correct sorting is present for Firm in Assecending order");
		}
		else {
			appLog.error("Incorrect sorting is present for Firm in Assecending order");
			flag = false;
		}
		
		return flag;
		
	}
}


