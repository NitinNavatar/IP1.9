/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.navatar.generic.BaseLib;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.CommonLib.ContentGridArrowKeyFunctions;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.SortOrder;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.multiInstance;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.SoftAssert;

import static com.navatar.generic.CommonLib.*;
import java.util.ArrayList;
import java.util.List;
import static com.navatar.generic.AppListeners.*;
import static com.navatar.generic.BaseLib.sa;

/**
 * @author Ankit Jaiswal
 *
 */
public class InvestorFirmPageBusinesslayer extends InvestorFirmPage implements InvestorFirmPageErrorMessage {

	/**
	 * @param driver
	 */
	public InvestorFirmPageBusinesslayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author Ankit Jaiswal
	 * @return true/false
	 */
	public boolean clickOnHomeTab() {
		try {
			if(click(driver, getHomeTab(30), "Home Tab", action.BOOLEAN)) {
				appLog.info("clicked on Home Tab successfully.");
				if(getAllDocumentsTab(30)!=null) {
					appLog.info("Home page is loaded successfully");
					return true;
				}else {
					appLog.info("Home page is not loaded after click on Home tab");
					throw new Exception();
				}
			}else {
				appLog.error("Not able to click on Home tab");
				throw new Exception();
			}			
		} catch (Exception e) {
			appLog.info("Trying once again...");
			if(click(driver, getHomeTab(30), "Home Tab", action.BOOLEAN)) {
				appLog.info("clicked on Home Tab successfully.");
				if(getAllDocumentsTab(30)!=null) {
					appLog.info("Home page is loaded successfully");
					return true;
				}else {
					appLog.info("Home page is not loaded after click on Home tab");
				}
			}else {
				appLog.error("Not able to click on Home tab");
			}		
		}
		return false;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param investorSideWorkSpace
	 * @return true/false
	 */
	public boolean clickOnInvestmentsTab(investorSideWorkSpace investorSideWorkSpace) {
		try {
			if(click(driver, getInvestmentsTab(5), "invesment tab", action.BOOLEAN)) {
				appLog.info("clicked on investment tab");
				if(investorSideWorkSpace.toString().equalsIgnoreCase(investorSideWorkSpace.CurrentInvestment.toString())) {
					if(click(driver, getCurrentInvestmentTab(5),"current invesment tab", action.BOOLEAN)) {
						appLog.info("successfully clicked on current invesment tab");
						return true;
					}else {
						appLog.error("Not able to click on current investment tab");
						throw new Exception();
					}
				}else if (investorSideWorkSpace.toString().equalsIgnoreCase(investorSideWorkSpace.PotentialInvestment.toString())) {
					if(click(driver, getPotentialInvestmentTab(5),"potential invesment tab", action.BOOLEAN)) {
						appLog.info("successfully clicked on potential invesment tab");
						return true;
					}else {
						appLog.error("Not able to click on potential investment tab");
						throw new Exception();
					}
				}
			}else {
				appLog.error("not able to click on invesment tab so cannot click on "+investorSideWorkSpace.toString()+"");
				throw new Exception();
			}			
		}catch (Exception e) {
			// TODO: handle exception
			appLog.info("Trying once again....");
			if(click(driver, getInvestmentsTab(5), "invesment tab", action.BOOLEAN)) {
				appLog.info("clicked on investment tab");
				ThreadSleep(3000);
				if(investorSideWorkSpace.toString().equalsIgnoreCase(investorSideWorkSpace.CurrentInvestment.toString())) {
					if(click(driver, getCurrentInvestmentTab(5),"current invesment tab", action.BOOLEAN)) {
						appLog.info("successfully clicked on current invesment tab");
						return true;
					}else {
						appLog.error("Not able to click on current investment tab");
					}
				}else if (investorSideWorkSpace.toString().equalsIgnoreCase(investorSideWorkSpace.PotentialInvestment.toString())) {
					if(click(driver, getPotentialInvestmentTab(5),"potential invesment tab", action.BOOLEAN)) {
						appLog.info("successfully clicked on potential invesment tab");
						return true;
					}else {
						appLog.error("Not able to click on potential investment tab");
					}
				}
			}else {
				appLog.error("not able to click on invesment tab so cannot click on "+investorSideWorkSpace.toString()+"");
			}		
		}
		
		return false;
		
	}
	
	/**
	 * @author ANKIT JAISWAL
	 * @param fileName
	 * @param invesmentName
	 * @param investorSideWorkSpace
	 * @param tabName
	 * @param activityType
	 * @param pageName
	 * @return List of String
	 */
	public List<String> verifyAlertOnRecentAllDocumentGrid(String fileName,String invesmentName,investorSideWorkSpace investorSideWorkSpace, TabName TabName, activityType activityType,PageName PageName ) {
		String workSpacetYpe=null;
		String activityTYpe=null;
		String[] splitedfileName=null;
		List<WebElement> fileNamelist = new ArrayList<WebElement>();
		List<WebElement>investmentNameList= new ArrayList<WebElement>();
		List<WebElement> workSpaceList= new ArrayList<WebElement>();
		List<WebElement> activityTypeList= new ArrayList<WebElement>();
		List<String> result = new ArrayList<String>();
		if(fileName!=null) {
			splitedfileName=fileName.split("<break>");
			if(investorSideWorkSpace.toString().equalsIgnoreCase(investorSideWorkSpace.PotentialInvestment.toString())) {
				workSpacetYpe="Potential Investment";
			}else {
				workSpacetYpe="Current Investment";
			}				
		}
		if(TabName.toString().equalsIgnoreCase(TabName.AllDocuments.toString())) {
			ThreadSleep(5000);
			fileNamelist=getAllDocumentFileNameList();	
			if(!fileNamelist.isEmpty()) {
				investmentNameList=getAllDocumentInvestmentNameList();
				workSpaceList=getAllDocumentWrokSpaceList();
				for(int i=0; i<splitedfileName.length; i++) {
						for(int j=0; j<fileNamelist.size(); j++) {
							scrollDownThroughWebelement(driver, fileNamelist.get(j), "");
							if(fileNamelist.get(j).getAttribute("title").trim().equalsIgnoreCase(splitedfileName[i]) && investmentNameList.get(j).getAttribute("title").trim().equalsIgnoreCase(invesmentName) && workSpaceList.get(j).getText().trim().equalsIgnoreCase(workSpacetYpe)) {
								appLog.info("file Name is matched successfully: "+splitedfileName[i]);
								appLog.info(splitedfileName[i]+" invesment name is matched successfully: "+invesmentName);
								appLog.info(splitedfileName[i]+" activity type is matched successfully: "+workSpacetYpe);
								break;
							}
							if(j==fileNamelist.size()-1) {
								appLog.error(splitedfileName[i]+" is not available in all document grid so we cannot verify invesment name and activity type of file: "+splitedfileName[i]);
								result.add(splitedfileName[i]+" is not available in all document grid so we cannot verify invesment name and activity type of file: "+splitedfileName[i]);	
							}				
						}			
					}				
			}else {
				appLog.error("Files are not available in the all document grid so cannot verify file name, invesment type and activity type.");
				result.add("Files are not available in the all document grid so cannot verify file name, invesment type and activity type.");
			}
		}else if (TabName.toString().equalsIgnoreCase(TabName.RecentActivities.toString())) {
			if(!activityType.toString().equalsIgnoreCase(activityType.ContactProfileUpdate.toString()) && !activityType.toString().equalsIgnoreCase(activityType.FirmProfileUpdate.toString())) {
				if(activityType.toString().equalsIgnoreCase(activityType.DocumentUpload.toString())) {
					activityTYpe="Document Uploaded";
				}else if (activityType.toString().equalsIgnoreCase(activityType.DocumentUpdate.toString())) {
					activityTYpe="Document Updated";
				}else if (activityType.toString().equalsIgnoreCase(activityType.ContactProfileUpdate.toString())) {
					activityTYpe="Contact Profile Updated";
				}else if (activityType.toString().equalsIgnoreCase(activityType.FirmProfileUpdate.toString())) {
					activityTYpe="Firm Profile Updated";
				}
				ThreadSleep(10000);
				fileNamelist=getRecentActivitiesFileNameList();
				if(!fileNamelist.isEmpty()) {
					investmentNameList=getRecentActivitiestInvestmentNameList();
					workSpaceList=getRecentActivitiesWorkSpaceList();
					activityTypeList=getRecentActivitiesActivityTypeList();
					for(int i = 0; i < splitedfileName.length; i++) {
						for(int j = 0; j < fileNamelist.size(); j++) {
							scrollDownThroughWebelement(driver, fileNamelist.get(j), "");
							String FileName=fileNamelist.get(j).getText().trim();
							String investmentName=investmentNameList.get(j).getText().trim();
							String workSpace=workSpaceList.get(j).getText().trim();
							String activitytype=activityTypeList.get(j).getText().trim();
							if(splitedfileName[i].equalsIgnoreCase(FileName)&&invesmentName.equalsIgnoreCase(investmentName)&&workSpacetYpe.equalsIgnoreCase(workSpace)&&activityTYpe.equalsIgnoreCase(activitytype)) {
								appLog.info(splitedfileName[i]+" is matched successfully.");
								appLog.info(invesmentName+" is successfully matched successfully of file: "+splitedfileName[i]);
								appLog.info(workSpacetYpe+" is matched successfully of file: "+splitedfileName[i]);
								appLog.info(activityTYpe+" is matched successfully of file: "+splitedfileName[i]);
								break;
							} 
							if(j==fileNamelist.size()-1) {
								appLog.error(splitedfileName[i]+" is not available in recent document grid so cannot verify invesmant Name,work Space Name and Activity type.");
								result.add(splitedfileName[i]+" is not available in recent document grid so cannot verify invesmant Name,work Space Name and Activity type.");
							}
						}
					}
				}else {
					appLog.info("file are not available in the recent activities grid so cannot verify file name,investment, activity type and workspace name.");
					result.add("file are not available in the recent activities grid so cannot verify file name,investment, activity type and workspace name.");
				}				
			}else {
				result=verifyUpdateAlerts(activityType, PageName);
			}
		}
		return result;
	}
	
	/**
	 * @author ANKIT JAISWAL
	 * @param activityType
	 * @param pageName
	 * @return List of String
	 */
	public List<String> verifyUpdateAlerts(activityType activityType,PageName pageName) {
		String activityTYpe=null;
		String actualresult1=null;
		String actualresult2=null;
		AllFirmsPageBusinesslayer allfirm;
		List<String> rslt= new ArrayList<String>();
		List<WebElement> alterlist= new ArrayList<WebElement>();
		if(activityType.toString().equalsIgnoreCase(activityType.ContactProfileUpdate.toString())) {
			activityTYpe="Contact Profile Updated";
		}else if (activityType.toString().equalsIgnoreCase(activityType.FirmProfileUpdate.toString())) {
			activityTYpe="Firm Profile Updated";
		}
		ThreadSleep(5000);
		if(pageName.toString().equalsIgnoreCase(pageName.InvestorFirmPage.toString())) {
			alterlist= getRecentActivitiesContactFirmUpdateList();
		}else if (pageName.toString().equalsIgnoreCase(pageName.AllFirmsPage.toString())) {
			allfirm= new AllFirmsPageBusinesslayer(driver);
			alterlist=allfirm.getContactFirmUpdateList();
		}
		if(!alterlist.isEmpty()) {
			for(int i=0; i<alterlist.size(); i++) {
				if(alterlist.size()>3) {
					if(i<2) {
						scrollDownThroughWebelement(driver, alterlist.get(i), "");
						if(i==0) {
							actualresult1=alterlist.get(i).getAttribute("title").trim();					
						}
						if(i==1) {
							actualresult2=alterlist.get(i).getAttribute("title").trim();					
						}
					}else {
						break;
					}				
				}else {
					scrollDownThroughWebelement(driver, alterlist.get(i), "");
					if(i==0) {
						actualresult1=alterlist.get(i).getAttribute("title").trim();
						System.err.println(actualresult1);
					}
					if(i==1) {
						actualresult2=alterlist.get(i).getAttribute("title").trim();
						System.err.println(actualresult2);
					}	
				}
			}
			if(actualresult1!=null || actualresult2!=null) {
				if(actualresult1.equalsIgnoreCase(activityTYpe)) {
					appLog.info(activityType.toString()+" is matched successfully");		
				}else if (actualresult2.equalsIgnoreCase(activityTYpe)) {
					appLog.info(activityType.toString()+" is matched successfully");	
				}else {
					appLog.error(activityTYpe+" alert is not matched. Expected: "+activityTYpe+"\tActual: "+actualresult1+","+actualresult2);
					rslt.add(activityTYpe+" alert is not matched. Expected: "+activityTYpe+"\tActual: "+actualresult1+","+actualresult2);						
				}						
			}else {
				appLog.error(activityTYpe+" alert is not matched. Expected: "+activityTYpe+"\tActual: "+actualresult1+","+actualresult2);
				rslt.add(activityTYpe+" alert is not matched. Expected: "+activityTYpe+"\tActual: "+actualresult1+","+actualresult2);	
			}
			
		}else {
			appLog.error(activityType.toString()+" is not available in "+pageName.toString()+" alert grid");
			rslt.add(activityType.toString()+" is not available in "+pageName.toString()+" alert grid");
		}
		return rslt;
	}

	/**
	 * @author ANKIT JAISWAL
	 * @param fileName
	 * @param investorSideWorkSpace
	 * @return List of String
	 */
	public List<String> verifyDocumentInvestorSideContentGrid(String fileName,investorSideWorkSpace investorSideWorkSpace){
		List<String> result = new ArrayList<String>();
		CurrentInvestmentpageBusinessLayer cur = new CurrentInvestmentpageBusinessLayer(driver);
		List<WebElement> filesName=cur.getDocumentNameList();
		String[] splitedFileName=fileName.split(",");
		for(int i=0; i<splitedFileName.length; i++) {
			for(int j=0;j<filesName.size();j++) {
				String fName=filesName.get(j).getAttribute("title").trim();
				if(fName.equalsIgnoreCase(splitedFileName[i])) {
					appLog.info("file is matched: "+splitedFileName[i]+" in "+investorSideWorkSpace.toString());
					break;
				}else {
					if(j==filesName.size()-1) {
						appLog.error("file is not matched: "+splitedFileName[i]+" in "+investorSideWorkSpace.toString()+" Expected Result: "+splitedFileName[i]+"\t Actual Result: "+fName);
						result.add("file is not matched: "+splitedFileName[i]+" in "+investorSideWorkSpace.toString()+" Expected Result: "+splitedFileName[i]+"\t Actual Result: "+fName);
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * @author ANKIT JAISWAL
	 * @param Tabname
	 * @param PageName
	 * @param timeOut
	 * @return int
	 */
	public int getAlertCount(TabName Tabname,PageName PageName,int timeOut) {
		int count=0;
		WebElement ele=null;
		if(PageName.toString().equalsIgnoreCase(PageName.InvestorFirmPage.toString())) {
			if(Tabname.toString().equalsIgnoreCase(TabName.AllDocuments.toString())) {
				ele=getAllDocumentCount(timeOut);
				if(ele!=null) {
					String[] result=ele.getText().trim().split(": ");
					count= Integer.parseInt(result[1].trim());
				}else {
					appLog.error("all documents count is not visible so cannot read all document count");
				}
			}else {
				ele=getRecentActivitiesCount(timeOut);
				if(ele!=null) {
					String[] result=ele.getText().trim().split(": ");
					count= Integer.parseInt(result[1].trim());
				}else {
					appLog.error("recent activities count is not visible so cannot read recent activities count");
				}
			}
		}else {
			ele=getRecentActivitiesCount(timeOut);
			if(ele!=null) {
				String[] result=ele.getText().trim().split(": ");
				count= Integer.parseInt(result[1].trim());
			}else {
				appLog.error("All firms count is not visible so cannot read all firms count");
			}
		}
		return count;
	}
	/**
	 * @author Ankit Jaiswal
	 * @param alertCount
	 * @param TabName
	 * @param pageName
	 * @param timeOut
	 * @return True/False
	 */
	public boolean verifyAlertCount(String alertCount,TabName TabName,PageName pageName,int timeOut) {
		boolean flag=false;
		WebElement ele=null;
		int ActualCount;
		if(alertCount!=null) {
			int ExpCount=Integer.parseInt(alertCount);	
			if(pageName.toString().equalsIgnoreCase(pageName.InvestorFirmPage.toString())) {
				if(TabName.toString().equalsIgnoreCase(TabName.AllDocuments.toString())) {
					ele=getAllDocumentCount(timeOut);
					if(ele!=null) {
						String[] result=ele.getText().trim().split(": ");
						ActualCount= Integer.parseInt(result[1].trim());
						if(ExpCount==ActualCount) {
							appLog.info("All documents alert count "+ActualCount+" is matched suuccessfully.");
							flag=true;
						}else {
							appLog.error("All documents alert count is not matched. Expected: "+ExpCount+" \t Actual result: "+ActualCount);
						}
							
					}else {
						appLog.error("all documents count is not visible so cannot match all document alert count. Expected: "+alertCount);
					}				
				}else {
					ele=getRecentActivitiesCount(timeOut);
					if(ele!=null) {
						String[] result=ele.getText().trim().split(": ");
						ActualCount= Integer.parseInt(result[1].trim());
						if(ExpCount==ActualCount) {
							appLog.info("recent activities alert count "+ActualCount+" is matched suuccessfully.");
							flag=true;
						}else {
							appLog.error("recent activities alert count is not matched. Expected: "+ExpCount+" \t Actual result: "+ActualCount);
						}
					}else {
						appLog.error("recent activities count is not visible so cannot match recent activities count");
					}
				}
			}else if (pageName.toString().equalsIgnoreCase(PageName.AllFirmsPage.toString())) {
				ele=getRecentActivitiesCount(timeOut);
				if(ele!=null) {
					String[] result=ele.getText().trim().split(": ");
					ActualCount= Integer.parseInt(result[1].trim());
					if(ExpCount==ActualCount) {
						appLog.info("All firms alert count "+ActualCount+" is matched suuccessfully.");
						flag=true;
					}else {
						appLog.error("All firms alert count is not matched. Expected: "+ExpCount+" \t Actual result: "+ActualCount);
					}
				}else {
					appLog.error("All firms count is not visible so cannot match all firms count. Expected: "+alertCount);
				}
			}
		}else {
			appLog.error("Expected Alert Count should not : "+alertCount);
		}
		return flag;
	}
	
	
	/**
	 * @author Ankur Rana
	 * @return true/false
	 */
	public boolean clickOnBulkDownload(){
		if (getBulkDownloadIcon(5) != null) {
			appLog.info("Bulk Download Icon is visible.");
		} else {
			sa.assertTrue(false, " Bulk Download Icon is not visible.");
		}
		
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", FindElement(driver, "//img[@alt='Bulk Download']", "Bulk Download Icon", action.BOOLEAN, 30));
			appLog.info("Successfully clicked on bulk download icon");
			return true;
		} catch(Exception e){
			appLog.info("Not able to click on bulk download icon.");
			return false;
		}
	}
	/**
	 * @author Azhar Alam
	 * @param driver
	 * @param pName
	 * @param filesName
	 * @param uploadedBy
	 * @param uploadedOn
	 * @return List of String
	 */
	public SoftAssert verifyContentGridInvestorSide(WebDriver driver, PageName pName, String filesName,
			String uploadedBy, String uploadedOn){

		SoftAssert saa = new SoftAssert();
		String workSpaceXpath = "";

		String dcName = "";
		String dcUploadedBy = "";
		String dcUploadedOn = "";
		String docNameXpath = "";
		String docUploadedByXpath = "";
		String docUploadedOnXpath = "";
		workSpaceXpath = "//span[contains(@id,'grid_Investor')]";
		docNameXpath = workSpaceXpath + "//span[contains(@id,'grid_Investor-cell-0')]/a";
		docUploadedByXpath = workSpaceXpath + "//span[contains(@id,'grid_Investor-cell-1')]/span";
		docUploadedOnXpath = workSpaceXpath + "//span[contains(@id,'grid_Investor-cell-3')]";
		if (workSpaceXpath != null) {
			List<WebElement> docNames = FindElements(driver, docNameXpath, "Document Name List");
			if (!docNames.isEmpty()) {
				List<WebElement> docUploadedBy = FindElements(driver, docUploadedByXpath, "Document Uploaded By List");
				List<WebElement> docUploadedOn = FindElements(driver, docUploadedOnXpath, "Document Uploaded On List");
				if (docUploadedBy.isEmpty()) {
				if (docNames.size() == docUploadedBy.size() && docNames.size() == docUploadedOn.size()) {
					String[] files = filesName.split("<break>");
					for (int i = 0; i < files.length; i++) {
						for (int j = 0; j < docNames.size(); j++) {
							dcName = docNames.get(j).getText().trim();
							dcUploadedBy = docUploadedBy.get(j).getText().trim();
							dcUploadedOn = docUploadedOn.get(j).getText().trim();

							System.err.println(">>>>>>> from WebPage " + dcName + "  " + dcUploadedBy + " " + dcUploadedOn+ " <<<<<<<<");
							System.out.println("<<<<<<<<< pASSING " + files[i] + "  " + uploadedBy + " "+ uploadedOn + " <<<<<<<<<");
							appLog.info(">>>>>>> from WebPage " + dcName + "  " + dcUploadedBy + " " + dcUploadedOn+ " <<<<<<<<");
							appLog.info("<<<<<<<<< pASSING " + files[i] + "  " + uploadedBy + " "+ uploadedOn + " <<<<<<<<<");
							if (dcName.equalsIgnoreCase(files[i]) && dcUploadedBy.equalsIgnoreCase(uploadedBy)
									&& (uploadedOn.contains(dcUploadedOn) || previousOrForwardDate(-1, "MM/dd/yyyy").contains(dcUploadedOn) )) {

								appLog.info(files[i] + " File  Present  Uploaded By "+uploadedBy+" Uploaded on"+uploadedOn +" "+ pName.toString());
								break;
							}

							if (j == docNames.size() - 1) {
								appLog.error(files[i] + " File not Present Uploaded By "+uploadedBy+" Uploaded on"+uploadedOn +" "+ pName.toString());
								saa.assertTrue(false, files[i] + " File not Present Uploaded By "+uploadedBy+" Uploaded on"+uploadedOn +" "+ pName.toString());
							}
						}
					}
				}
				else {
					appLog.error(docNames.size()+" documents are present, but "+docUploadedBy.size()+" user names, "+docUploadedOn.size()+" dates");
					sa.assertTrue(false, docNames.size()+" documents are present, but "+docUploadedBy.size()+" user names, "+docUploadedOn.size()+" dates");
				}
				}
				else {
					appLog.error("user List is Empty " + pName.toString());
					saa.assertTrue(false, "user List is Empty " + pName.toString());
				}
			} else {
				appLog.info("Document List is Empty " + pName.toString());
				saa.assertTrue(false, "Document List is Empty " + pName.toString());
			}
		} 

		return saa;
	}
	
	/**
	 * @author Azhar Alam
	 * @param workspace
	 * @param timeOut
	 * @return String
	 */
	public String getRecordCountvalue(investorSideWorkSpace workspace, int timeOut) {
		String s = "";
		WebElement ele = getRecordCount(workspace, timeOut);
		if (ele != null) {
			s = ele.getText().trim();
		}
		return s;
	}
	/**
	 * @author Azhar Alam
	 * @param folderPath
	 * @param documentName
	 * @param institutionName
	 * @param limitedPartnername
	 * @param FolderType
	 * @param filePath
	 * @param verifyErrorMessage
	 * @param timeOut
	 * @param pageName
	 * @param fundName
	 * @param Workspace
	 * @param workSpaceaction
	 * @return List of String
	 */
	public boolean uploadUpdateFileInvestorSide(String folderPath, String documentName, String institutionName,
			String limitedPartnername, FolderType FolderType, String filePath,
			String verifyErrorMessage, int timeOut, PageName pageName, String fundName,
			Workspace Workspace, WorkSpaceAction workSpaceaction) {
		BaseLib.mostActiveContactActivityCount=0;
		FundsPageBusinessLayer fpb = new FundsPageBusinessLayer(driver);
		if (FolderType.toString().equalsIgnoreCase(FolderType.Standard.toString())) {
			if (!fpb.verifyFolderPathdummy(folderPath, institutionName, limitedPartnername, fundName, pageName,
					Workspace, timeOut)) {
				BaseLib.sa.assertTrue(false, folderPath + " :Folder Structure is not verified.");
				switchToDefaultContent(driver);
				return false;
			}
		} else {
			if (!fpb.verifyFolderPathdummy(folderPath, null, null, fundName, pageName, Workspace, timeOut)) {
				BaseLib.sa.assertTrue(false, folderPath + " :Folder Structure is not verified.");
				switchToDefaultContent(driver);
				return false;
			}
		}
		// Upload Button Click
		if (click(driver, getUploadIcon(10), "Upload Icon", action.SCROLLANDBOOLEAN)) {
			ThreadSleep(5000);
			String parentWinID = switchOnWindow(driver);
			if (parentWinID != null) {
				appLog.info("Update window is successfully opened.");
				String path =System.getProperty("user.dir")+"\\"+filePath+"\\";
				System.err.println(">><<<<   "+path+documentName);
				if (sendKeys(driver, fpb.getChooseFileButton(60), path+documentName, "Choose Button", action.BOOLEAN)) {
					if (click(driver, getAddButton(60), "Add button", action.BOOLEAN)) {
						String msg = null;
						WebElement ele = null;
						if (workSpaceaction.toString().equalsIgnoreCase(WorkSpaceAction.UPLOAD.toString())) {
							ele = getUploadMsg(30);
							if (ele != null) {
								msg = ele.getText().trim();
								if (msg.equalsIgnoreCase(DocumentUploadSuccessMsg)) {
									appLog.info(
											"Upload confirmation message is verified : " + DocumentUploadSuccessMsg);
									if(!click(driver, getCloseButton(10), "Close Button", action.BOOLEAN)){
										driver.switchTo().window(parentWinID);
										BaseLib.mostActiveContactActivityCount++;
										System.err.println("most activity count: "+BaseLib.mostActiveContactActivityCount);
										return true;
									}
								} else {
									appLog.error("Upload confirmation message is verified :  Expected: "
											+ DocumentUploadSuccessMsg + " /tActual: " + msg);
									BaseLib.sa.assertTrue(false, "Confirmation message is not verified. Expected: "
											+ DocumentUploadSuccessMsg + " /t Actual: " + msg);
								}
							} else {
								appLog.error("Upload Msg is null");
								BaseLib.sa.assertTrue(false, "Upload Msg is null");
							}
							
						} else {
							ele = getUpdateMsg(20);
							if (ele != null) {
								msg = ele.getText().trim();
								if (msg.equalsIgnoreCase(DocumentUploadUpdateSuccessMsg)) {
									appLog.info("Update confirmation message is verified.");
								} else {
									appLog.error("Update message is not verified. Expected: "
											+ DocumentUploadUpdateSuccessMsg + " /tActual: " + msg);
									BaseLib.sa.assertTrue(false, "Update message is not verified. Expected: "
											+ DocumentUploadUpdateSuccessMsg + " /t Actual: " + msg);
								}

							}
							try {
								getUpdateButton(20).click();
//							if (click(driver, getUpdateButton(20), "Update Button", action.BOOLEAN)) {
								if (isAlertPresent(driver)) {
									msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if (msg.equalsIgnoreCase(DocumentUploadSuccessMsg)) {
										appLog.info("Update confirmation message is verified.");
										driver.switchTo().window(parentWinID);
										BaseLib.mostActiveContactActivityCount++;
										return true;
									} else {
										appLog.error("Confirmation message is not verified. Expected: "
												+ DocumentUploadSuccessMsg + " /tActual: " + msg);
										BaseLib.sa.assertTrue(false, "Confirmation message is not verified. Expected: "
												+ DocumentUploadSuccessMsg + " /t Actual: " + msg);
										driver.switchTo().window(parentWinID);
										return false;
									}
									
								
								} else {
									appLog.error("Confirmation alert message is not present on this window. Expected: "
											+ DocumentUploadSuccessMsg + " /tActual: " + msg);
									BaseLib.sa.assertTrue(false,
											"Confirmation message is not present on this window. Expected: "
													+ DocumentUploadSuccessMsg + " /tActual: " + msg);
									
								}
							}catch (Exception e) {
								appLog.error("Not Able to Click Update Button");
								BaseLib.sa.assertTrue(false, "Not Able to Update Close Button");
							}
//							} else {
//							
//								appLog.error("Not Able to Click Update Button");
//								BaseLib.sa.assertTrue(false, "Not Able to Update Close Button");
//							}
						}
					

					}else{
						appLog.error("Not Able to Click Add Button");
						BaseLib.sa.assertTrue(false, "Not Able to Add Close Button");
					}
				
					
				} else {
					appLog.error("Not Able to Send Document Path.");
					BaseLib.sa.assertTrue(false, "Not Able to Send Document Path.");
				
					
				}
				driver.close();
				driver.switchTo().window(parentWinID);
			} else {
				appLog.error("Upload Window is not Open");
				BaseLib.sa.assertTrue(false, "Upload Window is not Open");
			}
			
			
		} else {
			appLog.error("Not Able to Click Upload Icon");
			BaseLib.sa.assertTrue(false, "Not Able to Click Upload Icon");
		}

		
		return false;
	}

	/**
	 * @author Akul Bhutani
	 * @param workspace
	 * @param pageName
	 * @return true/false
	 */
	public boolean performSortingCheckInvestorSideAllColumns(Workspace workspace, PageName pageName) {
		boolean flag = true;
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		
		if (checkSorting(SortOrder.Decending, getUploadedOnInvestmentTab())){
			appLog.info(" Default sorting Verified on Upload On column");
		}
		else {
			appLog.error(" Default sorting Not Verified on Upload On column");
			flag= false;
		}
		
		appLog.info("checking sorting for document name column");
		//checking sorting for descending order
		click(driver, getColumnHeadsInvestor().get(0), "column head document names", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		/*if (checkSorting(SortOrder.Assecending, getDocListonInvestmentTab())){
			appLog.info("correct sorting is present for document name list in Ascending order");
		}
		else {
			appLog.error("Incorrect sorting is present for document name in Ascending order");
			flag = false;
		}*/
		//checking sorting for ascending order
		click(driver, af.getSortingIconList().get(0), "column head document names", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		/*if (checkSorting(SortOrder.Decending, getDocListonInvestmentTab())){
			appLog.info("correct sorting is present for document name in decending order");
		}
		else {
			appLog.error("Incorrect sorting is present for document name in decending order");
			flag = false;
		}*/
		appLog.info("checking sorting for uploaded by column");
		click(driver, getColumnHeadsInvestor().get(1), "uploaded by column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		if (checkSorting(SortOrder.Assecending, getUploadedByListInvestmentTab())){
			appLog.info("correct sorting is present for Uploaded  By list in Ascending order");
		}
		else {
			appLog.error("Incorrect sorting is present for Uploaded By in Ascending order");
			flag = false;
		}
		
		click(driver, af.getSortingIconList().get(1), "uploaded by column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		if (checkSorting(SortOrder.Decending, getUploadedByListInvestmentTab())){
			appLog.info("correct sorting is present for uploaded by column in decending order");
		}
		else {
			appLog.error("Incorrect sorting is present for uploaded by column in decending order");
			flag = false;
		}
		
		appLog.info("checking sorting for firm name column");
		click(driver, getColumnHeadsInvestor().get(2), "firm column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		if (checkSorting(SortOrder.Assecending, getFirmListInvestmentTab())){
			appLog.info("correct sorting is present for firm column list in Ascending order");
		}
		else {
			appLog.error("Incorrect sorting is present for firm column in Ascending order");
			flag = false;
		}
		
		click(driver, af.getSortingIconList().get(2), "firm column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		if (checkSorting(SortOrder.Decending, getFirmListInvestmentTab())){
			appLog.info("correct sorting is present for firm column in decending order");
		}
		else {
			appLog.error("Incorrect sorting is present for firm column in decending order");
			flag = false;
		}
		
		appLog.info("checking sroting for uploaded on column");
		click(driver, getColumnHeadsInvestor().get(3), "uploaded on column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		if (checkSorting(SortOrder.Assecending, getUploadedOnInvestmentTab())){
			appLog.info("correct sorting is present for uploaded on column list in Ascending order");
		}
		else {
			appLog.error("Incorrect sorting is present for uploaded on column in Ascending order");
			flag = false;
		}
		
		click(driver, af.getSortingIconList().get(3), "uploaded on column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		if (checkSorting(SortOrder.Decending, getUploadedOnInvestmentTab())){
			appLog.info("correct sorting is present for uploaded on columnin decending order");
		}
		else {
			appLog.error("Incorrect sorting is present for uploaded on column in decending order");
			flag = false;
		}
		return flag;
		
	}
		
	/**
	 * @author Ankur Rana
	 * @param fileName
	 * @return true/false
	 */
	public boolean verifyDownloadFromAllDocuments(String fileName)
	{
		List<WebElement> listOfDoc=getAllDocumentFileNameList();
	
		for(int i=0;i<listOfDoc.size();i++)
		{
		System.out.println(listOfDoc.get(i).getAttribute("title"));
		}
		if(!listOfDoc.isEmpty()) {
		for(int j=0;j<listOfDoc.size(); j++) {
			
			if(listOfDoc.get(j).getAttribute("title").trim().equalsIgnoreCase(fileName) ) 
			{
				scrollDownThroughWebelement(driver, listOfDoc.get(j), "scroll till the Document");
				appLog.info("Document Name is matched successfully: "+fileName);
				break;
			}
			if(j==listOfDoc.size()-1) {
				appLog.error(fileName+" is not available in all document grid so not able to check download functionality. ");
				
				return false;
			}				
		}	
		return true;
	}
	else {
		appLog.error("Documents are not available in the all document grid so cannot verify Document name");
		
	}
		return false;
	

	
}	
	
	/**
	 * @author Akul Bhutani
	 * @param workspace
	 * @param pageName
	 * @return int
	 */
	public int getNoOfDocumentsGrid( Workspace workspace,PageName pageName) {
		String xpath = "//span[contains(@id,'grid_Investor-cell-0')]/a";
		return FindElements(driver, xpath, "list of documents investor side").size();
	}
	

	public boolean verifyBulkDownLoadFolderStructure(String path,int timeOut) {
		String[] paths = path.split("/");
		
		String parentXpath="//a[contains(text(),'All Folders')]/../following-sibling::ul/li/span/a[text()='"+paths[0]+"']/preceding-sibling::span[3]";
		String childPath="";
		String xpath="";
		boolean flag=true;
		WebElement ele = FindElement(driver, parentXpath, paths[0], action.BOOLEAN, timeOut);
		ele = isDisplayed(driver, ele, "Visibility", timeOut, paths[0]);
		if (ele!=null) {
			
			if (click(driver, ele, paths[0], action.SCROLLANDBOOLEAN)) {
				appLog.info("Folder Found : "+paths[0]);
			} else {
				appLog.error("Folder Not Found : "+paths[0]);
				return false;
			}
			
			
		} else {
			return false;
		}
	
		if (paths.length>1) {
		
			for (int i = 1; i < paths.length; i++) {
				childPath=childPath+"/../following-sibling::ul/li/span/a[text()='"+paths[i]+"']/preceding-sibling::span[3]";
				xpath=parentXpath+childPath;
				
				ele = FindElement(driver, xpath, paths[i], action.SCROLLANDBOOLEAN, timeOut);
				ele = isDisplayed(driver, ele, "Visibility", timeOut, paths[i]);
				if (click(driver, ele, paths[i], action.SCROLLANDBOOLEAN)) {
					appLog.info("Folder Found : "+paths[i]);	
				} else {
					appLog.error("Folder Not Found : "+paths[i]);
					flag=false;
					break;
				}
			}
		}
		
		
		return flag;
		
	}
	
	public boolean clickCheckBoxForFolderonBulkDownloadWindow(String folderName,int timeOut){
		String[] folders = folderName.split("/");
		
		String xpath = "//a[text()='"+folders[folders.length-1]+"']/preceding-sibling::span[2]";
		WebElement ele = FindElement(driver, xpath, "CheckBox for Folder : "+folderName, action.SCROLLANDBOOLEAN, 10);
		
		if(clickUsingJavaScript(driver, ele, "Check Box")) {
//		if (click(driver, ele, "Check Box", action.BOOLEAN)) {
			return true;
		}
		
		return false;
		
	}
	

}