/**
 * 
 */
package com.navatar.pageObjects;
import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.TabName;
import com.navatar.generic.CommonLib.activityType;
import com.navatar.generic.CommonLib.investorSideWorkSpace;

import static com.navatar.generic.CommonLib.*;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.navatar.generic.AppListeners.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AllFirmsPageBusinesslayer extends AllFirmsPage implements AllFirmsErrorMessage {

	/**
	 * @param driver
	 */
	public AllFirmsPageBusinesslayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param firmName
	 * @return true/false
	 */
	public boolean selectFirmName(String firmName) {
		if(selectVisibleTextFromDropDown(driver, getFirmNameDropdown(60), "firm name drop down list",firmName)) {
			appLog.info(firmName+" is selected from firm name drop down list");
			return true;
		}else {
			appLog.error("Not able to select "+firmName+" from drop down list.");
		}
		return false;
	}
	
	/**
	 * @author ANKIT JAISWAL
	 * @param fileName
	 * @param firmName
	 * @param invesmentName
	 * @param investorSideWorkSpace
	 * @param activityType
	 * @param PageName
	 * @return List of String
	 */
	public List<String> verifyAlertsOnAllFirmsPage(String fileName,String firmName,String investmentName,investorSideWorkSpace investorSideWorkSpace, activityType activityType,PageName PageName) {
		String workSpacetYpe=null;
		String activityTYpe=null;
		List<WebElement> fileNamelist = new ArrayList<WebElement>();
		List<WebElement>investmentNameList= new ArrayList<WebElement>();
		List<WebElement> workSpaceList= new ArrayList<WebElement>();
		List<WebElement> activityTypeList= new ArrayList<WebElement>();
		List<WebElement> firmNameList= new ArrayList<WebElement>();
		List<String> result = new ArrayList<String>();
		if(!activityType.toString().equalsIgnoreCase(activityType.ContactProfileUpdate.toString()) && !activityType.toString().equalsIgnoreCase(activityType.FirmProfileUpdate.toString())) {
			String[] splitedfileName=fileName.split("<break>");
			if(investorSideWorkSpace.toString().equalsIgnoreCase(investorSideWorkSpace.PotentialInvestment.toString())) {
				workSpacetYpe="Potential Investment";
			}else {
				workSpacetYpe="Current Investment";
			}
			if(activityType.toString().equalsIgnoreCase(activityType.DocumentUpload.toString())) {
				activityTYpe="Document Uploaded";
			}else if (activityType.toString().equalsIgnoreCase(activityType.DocumentUpdate.toString())) {
				activityTYpe="Document Updated";
			}else if (activityType.toString().equalsIgnoreCase(activityType.ContactProfileUpdate.toString())) {
				activityTYpe="Contact Profile Updated";
			}else if (activityType.toString().equalsIgnoreCase(activityType.FirmProfileUpdate.toString())) {
				activityTYpe="Firm Profile Updated";
			}
			fileNamelist=getDocumentNameList();
			if(!fileNamelist.isEmpty()) {
				investmentNameList=getInvesmentNameList();
				workSpaceList=getWorkSpaceNameList();
				activityTypeList=getActivityTypeList();
				firmNameList= getFirmNameList();
				for(int i = 0; i < splitedfileName.length; i++) {
					for(int j = 0; j < fileNamelist.size(); j++) {
						scrollDownThroughWebelement(driver, fileNamelist.get(j), "");
						String fname=fileNamelist.get(j).getAttribute("title").trim();
						String firmname=firmNameList.get(j).getAttribute("title").trim();
						String investmantName=investmentNameList.get(j).getAttribute("title").trim();
						String workspaceName=workSpaceList.get(j).getText().trim();
						String activity=activityTypeList.get(j).getText().trim();
						if(splitedfileName[i].equalsIgnoreCase(fname)&& firmName.equalsIgnoreCase(firmname) && investmentName.equalsIgnoreCase(investmantName)&& workSpacetYpe.equalsIgnoreCase(workspaceName)&& activityTYpe.equalsIgnoreCase(activity)) {
							appLog.info(splitedfileName[i]+" is matched successfully on all firm page");
							appLog.info(investmentName+" is successfully matched successfully on all firm page of file: "+splitedfileName[i]);
							appLog.info(workSpacetYpe+" is matched successfully on all firm page of file: "+splitedfileName[i]);
							appLog.info(activityTYpe+" is matched successfully on all firm page of file: "+splitedfileName[i]);
							break;
						} 
						if(j==fileNamelist.size()-1) {
							appLog.error(splitedfileName[i]+" is not available in all firms alert grid so cannot verify firm Name,invesmant Name,work Space Name and Activity type.");
							result.add(splitedfileName[i]+" is not available in all firms alert grid so cannot verify firm Name,invesmant Name,work Space Name and Activity type.");
						}
					}
				}
			}else {
				appLog.info("file are not available in the all firms alert grid so cannot verify file name,firm name,investment, activity type and workspace name.");
				result.add("file are not available in the all firms alert grid so cannot verify file name,firm name,investment, activity type and workspace name.");
			}				
		}else {
			InvestorFirmPageBusinesslayer ins = new InvestorFirmPageBusinesslayer(driver);
			result=ins.verifyUpdateAlerts(activityType, PageName);
		}
		return result;	
	}
}
