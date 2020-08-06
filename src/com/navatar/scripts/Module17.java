/**
 * 
 */
package com.navatar.scripts;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.navatar.generic.BaseLib;
import com.navatar.generic.EmailLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.CommonLib.AllOr1By1;
import com.navatar.generic.CommonLib.CheckBoxName;
import com.navatar.generic.CommonLib.EnableDisable;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.TabName;
import com.navatar.generic.CommonLib.UploadFileActions;
import com.navatar.generic.CommonLib.WorkSpaceAction;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.accessType;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.generic.CommonLib.investorSideWorkSpace;
import com.navatar.pageObjects.AllFirmsPageBusinesslayer;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.CommitmentPageBusinessLayer;
import com.navatar.pageObjects.ContactPageBusinessLayer;
import com.navatar.pageObjects.ContactPageErrorMessage;
import com.navatar.pageObjects.FundRaisingPageBusinessLayer;
import com.navatar.pageObjects.FundsPageBusinessLayer;
import com.navatar.pageObjects.FundsPageErrorMessage;
import com.navatar.pageObjects.HomePageBusineesLayer;
import com.navatar.pageObjects.InstitutionPageBusinessLayer;
import com.navatar.pageObjects.InvestorFirmPageBusinesslayer;
import com.navatar.pageObjects.InvestorFirmPageErrorMessage;
import com.navatar.pageObjects.InvestorProfileBusinessLayer;
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.NIMPageBusinessLayer;
import com.navatar.pageObjects.PartnershipPageBusinessLayer;
import static com.navatar.generic.CommonVariables.*;
import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.BaseLib.sa;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static com.navatar.generic.CommonLib.*;
/**
 * @author Ankit Jaiswal
 *
 */
public class Module17 extends BaseLib {

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc001_preCondition() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		if(lp.preCondition(superAdminUserName, CRMUser1FirstName+" "+CRMUser1LastName, CRMUser1EmailID, EnableDisable.Disable, EnableDisable.Disable, accessType.InternalUserAccess)){
			appLog.info("Provided internal user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
		} else {
			appLog.error("Not able to provide internal user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
			sa.assertTrue(false,"Not able to provide internal user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
		}
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		cp = new ContactPageBusinessLayer(driver);
		ip = new InstitutionPageBusinessLayer(driver);
		cmp = new CommitmentPageBusinessLayer(driver);
		pp = new PartnershipPageBusinessLayer(driver);
		frp = new FundRaisingPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		for(int i=0; i<5; i++) {
			String instutionName=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M17Institution"+(i+1), excelLabel.Institutions_Name);
			if(bp.clickOnTab(TabName.InstituitonsTab)) {
			if(ip.createInstitution(instutionName)) {
				appLog.info("Institution is created successfully: "+instutionName);
			}else {
				appLog.error("Not able to create institution: "+instutionName);
				sa.assertTrue(false, "Not able to create institution: "+instutionName);
			}
		}else {
				appLog.error("Not able to click on institution tab so cannot create institution: "+instutionName);
				sa.assertTrue(false, "Not able to click on institution tab so cannot create institution: "+instutionName);
			}
		}
		for(int i=1; i<=7; i++) {
			String emailId = cp.generateRandomEmailId();
			if(bp.clickOnTab(TabName.ContactTab)){
				if(i==1) {
					if(cp.createContact(M17Contact1FirstName,M17Contact1LastName,M17Institution1, emailId)) {
						appLog.info("contact is created: "+M17Contact1FirstName+" "+M17Contact1LastName);
						ExcelUtils.writeData(emailId,"Contacts", excelLabel.Variable_Name, "M17Contact1",excelLabel.Contact_EmailId);
					}else {
						appLog.error("Not able to create contact: "+M17Contact1FirstName+" "+M17Contact1LastName);
						sa.assertTrue(false, "Not able to create contact: "+M17Contact1FirstName+" "+M17Contact1LastName);
					}
				}
				if(i==2) {
					if(cp.createContact(M17Contact2FirstName,M17Contact2LastName,M17Institution1, emailId)) {
						appLog.info("contact is created: "+M17Contact2FirstName+" "+M17Contact2LastName);
						ExcelUtils.writeData(emailId,"Contacts", excelLabel.Variable_Name, "M17Contact2",excelLabel.Contact_EmailId);
					}else {
						appLog.error("Not able to create contact: "+M17Contact2FirstName+" "+M17Contact2LastName);
						sa.assertTrue(false, "Not able to create contact: "+M17Contact2FirstName+" "+M17Contact2LastName);
					}
				}
				if(i==3) {
					if(cp.createContact(M17Contact3FirstName,M17Contact3LastName,M17Institution2, emailId)) {
						appLog.info("contact is created: "+M17Contact3FirstName+" "+M17Contact3LastName);
						ExcelUtils.writeData(emailId,"Contacts", excelLabel.Variable_Name, "M17Contact3",excelLabel.Contact_EmailId);
					}else {
						appLog.error("Not able to create contact: "+M17Contact3FirstName+" "+M17Contact3LastName);
						sa.assertTrue(false, "Not able to create contact: "+M17Contact3FirstName+" "+M17Contact3LastName);
					}
				}

				if(i==4) {
					if(cp.createContact(M17Contact4FirstName,M17Contact4LastName,M17Institution2, emailId)) {
						appLog.info("contact is created: "+M17Contact4FirstName+" "+M17Contact4LastName);
						ExcelUtils.writeData(emailId,"Contacts", excelLabel.Variable_Name, "M17Contact4",excelLabel.Contact_EmailId);
					}else {
						appLog.error("Not able to create contact: "+M17Contact4FirstName+" "+M17Contact4LastName);
						sa.assertTrue(false, "Not able to create contact: "+M17Contact4FirstName+" "+M17Contact4LastName);
					}
				}
				if(i==5) {
					if(cp.createContact(M17Contact5FirstName,M17Contact5LastName,M17Institution3, emailId)) {
						appLog.info("contact is created: "+M17Contact5FirstName+" "+M17Contact5LastName);
						ExcelUtils.writeData(emailId,"Contacts", excelLabel.Variable_Name, "M17Contact5",excelLabel.Contact_EmailId);
					}else {
						appLog.error("Not able to create contact: "+M17Contact5FirstName+" "+M17Contact5LastName);
						sa.assertTrue(false, "Not able to create contact: "+M17Contact5FirstName+" "+M17Contact5LastName);
					}
				}
				if(i==6) {
					if(cp.createContact(M17Contact6FirstName,M17Contact6LastName,M17Institution4, emailId)) {
						appLog.info("contact is created: "+M17Contact6FirstName+" "+M17Contact6LastName);
						ExcelUtils.writeData(emailId,"Contacts", excelLabel.Variable_Name, "M17Contact6",excelLabel.Contact_EmailId);
					}else {
						appLog.error("Not able to create contact: "+M17Contact6FirstName+" "+M17Contact6LastName);
						sa.assertTrue(false, "Not able to create contact: "+M17Contact6FirstName+" "+M17Contact6LastName);
					}
				}
				if(i==7) {
					if(cp.createContact(M17Contact7FirstName,M17Contact7LastName,M17Institution5, "")) {
						appLog.info("contact is created: "+M17Contact7FirstName+" "+M17Contact7LastName);
					}else {
						appLog.error("Not able to create contact: "+M17Contact7FirstName+" "+M17Contact7LastName);
						sa.assertTrue(false, "Not able to create contact: "+M17Contact7FirstName+" "+M17Contact7LastName);
					}
				}
			}
		}
		for(int i=1; i<=2; i++) {
			if(bp.clickOnTab(TabName.FundsTab)) {
				if(i==1) {
					if(fp.createFund(M17FundName1,M17Fund1Type,M17Fund1InvestmentCategory)) {
						appLog.info("fund is created: "+M17FundName1);
					}else {
						appLog.error("Not able to create fund: "+M17FundName1);
						sa.assertTrue(false, "Not able to create fund: "+M17FundName1);
					}
				}
				if(i==2) {
					if(fp.createFund(M17FundName2,M17Fund1Type,M17Fund1InvestmentCategory)) {
						appLog.info("fund is created: "+M17FundName2);
					}else {
						appLog.error("Not able to create fund: "+M17FundName2);
						sa.assertTrue(false, "Not able to create fund: "+M17FundName2);
					}
				}
			}else {
				appLog.error("Not able to click on fund tab so cannot create fund: "+M17FundName1+" and "+M17FundName2);
				sa.assertTrue(false, "Not able to click on fund tab so cannot create fund: "+M17FundName1+" and "+M17FundName2);
			}
		}
		for(int i=1; i<=3; i++) {
			String fundraisingName=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M17FundRaising"+(i), excelLabel.FundRaising_Name);
			if(bp.clickOnTab(TabName.FundraisingsTab)) {
				String fundName=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M17FundRaising"+(i), excelLabel.Fund_Name);
				String instutionName=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M17Institution"+(i), excelLabel.Institutions_Name);
				if(frp.createFundRaising(fundraisingName, fundName, instutionName)) {
					appLog.info("fundraising is created : "+fundraisingName);
				}else {
					appLog.error("Not able to create fundraising: "+fundraisingName);
					sa.assertTrue(false, "Not able to create fundraising: "+fundraisingName);
				}
			}else {
				appLog.error("Not able to click on fundraising tab so cannot create fundraising: "+fundraisingName);
				sa.assertTrue(false,"Not able to click on fundraising tab so cannot create fundraising: "+fundraisingName);
			}
		}
		for(int i=1; i<=5; i++) {
			String lpName=ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M17LimitedPartner"+(i), excelLabel.LimitedPartner_Name);
			if(bp.clickOnTab(TabName.InstituitonsTab)) {
				String instutionName=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M17Institution"+(i), excelLabel.Institutions_Name);
				if(ip.createLimitedPartner(lpName, instutionName)) {
					appLog.info("limited partner is created: "+lpName);
				}else {
					appLog.error("Not able to create limited partner: "+lpName);
					sa.assertTrue(false, "Not able to create limited partner: "+lpName);
				}
			}else {
				appLog.error("Not able to click on institution tab so cannot create limite partner: "+lpName);
				sa.assertTrue(false, "Not able to click on institution tab so cannot create limite partner: "+lpName);
			}
		}
		if(bp.clickOnTab(TabName.PartnershipsTab)) {
			if(pp.createPartnership(M17Partnership1,M17FundName1)) {
				appLog.info("partnership is created: "+M17Partnership1);
			}else {
				appLog.error("Not able to create partnership: "+M17Partnership1);
				sa.assertTrue(false, "Not able to create partnership: "+M17Partnership1);
			}
		}else {
			appLog.error("Not able to click on partnership tab so cannot create partnership: "+M17Partnership1);
			sa.assertTrue(false, "Not able to click on partnership tab so cannot create partnership: "+M17Partnership1);
		}
		for(int i=1; i<=3; i++) {
			String lpName;
			String partnershipName = null;
			lpName=ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M17LimitedPartner"+(i)+"", excelLabel.LimitedPartner_Name);
			partnershipName=ExcelUtils.readData("Partnerships",excelLabel.Variable_Name, "M17Partnership1", excelLabel.PartnerShip_Name);
			if(bp.clickOnTab(TabName.CommitmentsTab)) {
				if(cmp.createCommitment(lpName,partnershipName,"M17CMT"+(i), null)) {
					appLog.info("commitment is created successfully");
				}else {
					appLog.error("Not able to create commitment for limited partner: "+lpName+" and partnership Name: "+partnershipName);
					sa.assertTrue(false, "Not able to create commitment for limited partner: "+lpName+" and partnership Name: "+partnershipName);
				}
			}else {
				appLog.error("Not able to click on commitment tab so cannot create committment for limite partner: "+lpName+" and partnership Name: "+partnershipName);
				sa.assertTrue(false, "Not able to click on commitment tab so cannot create committment for limite partner: "+lpName+" and partnership Name: "+partnershipName);
			}
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc002_buildFRW(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund1", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund1", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund1", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund1", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund1", excelLabel.Fund_Email);
				String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund1", excelLabel.Fund_Description);
				String templateName=ExcelUtils.readData("Foldertemp", 0, 1);
				String[] data= {Size,vintageyear,contact,phone,email,description};
				String standardfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
				String sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
				String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
				String internalfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
				
				
				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,M17Institution1+"<break>"+M17Institution2, Workspace.FundraisingWorkspace,60)) {
					appLog.info("FundRaising work is build successfully on fund : "+M17FundName1);
					switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
					if(fp.verifyFolderPathdummy("", M17Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
						if(fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 20)!=null) {
							appLog.info("Contact access icon is displaying in "+M17Institution1);
						}else {
							appLog.error("Contact access pop up is not displaying in "+M17Institution1);
							sa.assertTrue(false, "Contact access pop up is not displaying in "+M17Institution1);
						}
					}else {
						appLog.error("Not able to click on folder: "+M17Institution1+" so cannot verify contact access icon");
						sa.assertTrue(false, "Not able to click on folder: "+M17Institution1+" so cannot verify contact access icon");
					}
					if(fp.verifyFolderPathdummy(standardfolderpath, M17Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
						if(fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 20)!=null) {
							appLog.info("Contact access icon is displaying in "+M17Institution1+"/"+standardfolderpath);
						}else {
							appLog.error("Contact access pop up is not displaying in "+M17Institution1+"/"+standardfolderpath);
							sa.assertTrue(false, "Contact access pop up is not displaying in "+M17Institution1+"/"+standardfolderpath);
						}
					}else {
						appLog.error("Not able to click on folder: "+M17Institution1+"+/"+standardfolderpath+" so cannot verify contact access icon");
						sa.assertTrue(false, "Not able to click on folder: "+M17Institution1+"/"+standardfolderpath+" so cannot verify contact access icon");
					}
					
					if(fp.verifyFolderPathdummy(sharedfolderpath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
						if(fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 20)!=null) {
							appLog.info("Contact access icon is displaying in "+sharedfolderpath);
						}else {
							appLog.error("Contact access pop up is not displaying in "+sharedfolderpath);
							sa.assertTrue(false, "Contact access pop up is not displaying in "+sharedfolderpath);
						}
					}else {
						appLog.error("Not able to click on folder: "+sharedfolderpath+" so cannot verify contact access icon");
						sa.assertTrue(false, "Not able to click on folder: "+sharedfolderpath+" so cannot verify contact access icon");
					}
					
					if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
						if(fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 10)==null) {
							appLog.info("Contact access icon is not displaying in "+Commonfolderpath);
						}else {
							appLog.error("Contact access pop up is displaying in "+Commonfolderpath);
							sa.assertTrue(false, "Contact access pop up is displaying in "+Commonfolderpath);
						}
					}else {
						appLog.error("Not able to click on folder: "+Commonfolderpath+" so cannot verify contact access icon");
						sa.assertTrue(false, "Not able to click on folder: "+Commonfolderpath+" so cannot verify contact access icon");
					}
					
					if(fp.verifyFolderPathdummy(internalfolderpath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
						if(fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 10)==null) {
							appLog.info("Contact access icon is not displaying in "+internalfolderpath);
						}else {
							appLog.error("Contact access pop up is displaying in "+internalfolderpath);
							sa.assertTrue(false, "Contact access pop up is displaying in "+internalfolderpath);
						}
					}else {
						appLog.error("Not able to click on folder: "+internalfolderpath+" so cannot verify contact access icon");
						sa.assertTrue(false, "Not able to click on folder: "+internalfolderpath+" so cannot verify contact access icon");
					}
					
				}else {
					appLog.error("Not able to bulid fundraising workspace on fund: "+M17FundName1);
					sa.assertTrue(false, "Not able to bulid fundraising workspace on fund: "+M17FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot build fundraising workspace: "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build fundraising workspace: "+M17FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc003_VerifySharedFolderContactAccessInFR(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String cols="<break>Contact Name<break>E-mail<break>Firm<break>Action<break>Contact Name<break>Granted Access On<break>E-mail<break>Firm<break><break><break>";
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		if (lp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
		switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver,fp.getFundRaisingWorkSpaceSection(30) , "Fundraising workspace section");
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("clicked on shared folder");
				}
				else {
					appLog.error("could not click on shared folder");
					sa.assertTrue(false, "could not click on shared folder");
				}
				if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
					//verify UI
					if (fp.verifyUIOfContactAccess(Workspace.FundraisingWorkspace, cols, shdPath)) {
						appLog.info("successfully verified UI of contact access");
					}
					else {
						appLog.error("could not verify UI of contact access");
						sa.assertTrue(false, "could not verify UI of contact access");
					}
				}
				if (click(driver, fp.getContactAccessCrossIcon(Workspace.FundraisingWorkspace, 30), "cross icon", action.SCROLLANDBOOLEAN)) {
					//verify UI is closed
					if (fp.getContactAccessHeadText(Workspace.FundraisingWorkspace, 30)==null) {
						appLog.info("successfully closed contact access UI");
					}
					else {
						appLog.error("contact access UI is not closed");
						sa.assertTrue(false, "contact access UI is not closed");
					}
				}
				
				if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
					//verify UI
					if (fp.verifyUIOfContactAccess(Workspace.FundraisingWorkspace, cols, shdPath)) {
						appLog.info("successfully verified UI of contact access");
					}
					else {
						appLog.error("could not verify UI of contact access");
						sa.assertTrue(false, "could not verify UI of contact access");
					}
				}
				if (click(driver, fp.cancelButtonContactAccessActive(Workspace.FundraisingWorkspace), "cancel button", action.SCROLLANDBOOLEAN)) {
					//verify UI is closed
					if (fp.getContactAccessHeadText(Workspace.FundraisingWorkspace, 30)==null) {
						appLog.info("successfully closed contact access UI");
					}
					else {
						appLog.error("contact access UI is not closed");
						sa.assertTrue(false, "contact access UI is not closed");
					}
				}
				if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
					if (mouseOverOperation(driver, fp.getMouseOverIconContactAccessimg(30))) {
						ThreadSleep(3000);
						if (getAttribute(driver, fp.getMouseOverIconContactAccess(30), "contact access i icon", "title").equalsIgnoreCase(fp.contactAccessHoverText)) {
							appLog.info("successfully verified contact access i icon text");
						}
						else {
							appLog.error("contact access i icon text is not correct");
							sa.assertTrue(false, "contact access i icon text is not correct");
						}
					}
				}
				//verify firm column
				List<String> firms = new ArrayList<String>();
				firms.add(M17Institution1);
				firms.add(M17Institution2);
				firms.add(M17Institution3);
				//open section in contact access
				if (fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
					if (fp.compareFirmColumnContactAccess(Workspace.FundraisingWorkspace, firms)) {
						appLog.info("successfully found firm names are correct");
					}
					else {
						appLog.error("could not find firm names as correct");
						sa.assertTrue(false, "could not find firm names as correct");
					}
					//click on contact 1 checkbox
					click(driver, fp.checkboxOnContactAccessUI(Workspace.FundraisingWorkspace, M17Contact1EmailId), "checkbox of "+M17Contact1FirstName, action.SCROLLANDBOOLEAN);
					if (click(driver, fp.cancelButtonContactAccessActive(Workspace.FundraisingWorkspace), "cancel button", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
							if (fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {

								if (!isSelected(driver,  fp.checkboxOnContactAccessUI(Workspace.FundraisingWorkspace, M17Contact1EmailId), "checkbox of "+M17Contact1FirstName)) {
									appLog.info("after clicking cancel button checkbox for contact 1 is unchecked as expected");
								}
								else {
									appLog.error("after clicking cancel button checkbox for contact 1 is checked but it should not be");
									sa.assertTrue(false, "after clicking cancel button checkbox for contact 1 is checked but it should not be");
								}
							}
							else {
								appLog.error("could not expand contact access");
								sa.assertTrue(false, "could not expand contact access");
							}
						}
						else {
							appLog.error("contact access icon is not clickable");
							sa.assertTrue(false, "contact access icon is not clickable");
						}
					}
					else {
						appLog.error("cancel button is not clickable on contact access");
						sa.assertTrue(false, "cancel button is not clickable on contact access");
					}
					
					click(driver, fp.getContactAccessCrossIcon(Workspace.FundraisingWorkspace, 30), "cross icon", action.SCROLLANDBOOLEAN);
					if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						if (fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
							if (click(driver, fp.checkboxOnContactAccessUI(Workspace.FundraisingWorkspace, M17Contact1EmailId), "checkbox of "+M17Contact1FirstName, action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "add select contact btn", action.SCROLLANDBOOLEAN)) {
									if (click(driver, fp.getcontactaccessremoveLink(Workspace.FundraisingWorkspace, EnableDisable.Enable, M17Contact1EmailId, 30), "Remove link", action.BOOLEAN)) {
										if (click(driver, fp.getApplyBtn(Workspace.FundraisingWorkspace, 30), "apply button", action.SCROLLANDBOOLEAN)) {
											click(driver, fp.getCloseBtn(Workspace.FundraisingWorkspace, 30), "close button", action.BOOLEAN);
										}
										else {
											appLog.error("could not click on apply button in contact access");
											sa.assertTrue(false, "could not click on apply button in contact access");
										}
										
									}
									else {
										appLog.error("remove link is not clickable");
										sa.assertTrue(false, "remove link is not clickable");
									}
								}
								else {
									appLog.error("add selected contacts btn is not clickable");
									sa.assertTrue(false, "add selected contacts btn is not clickable");
								}
							}
							else {
								appLog.error("contact checkbox is not clickable");
								sa.assertTrue(false, "contact checkbox is not clickable");
							}
						}
						else {
							appLog.error("contact access icon is not clickable");
							sa.assertTrue(false, "contact access icon is not clickable");
						}

					}
					else {
						appLog.error("could not open minus icon on contact access");
						sa.assertTrue(false, "could not open minus icon on contact access");
					}
					if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						if (fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {

							//click on email link
							click(driver, FindElement(driver, "//a[text()='"+M17Contact1EmailId+"']", "contact email id link", action.SCROLLANDBOOLEAN, 30), "contact email id link", action.BOOLEAN);
							if (nim.verifyNavatarSalesTeamLinkFunctionality("other")) {
								appLog.info("successfully verified email link");
							}
							else {
								appLog.error("could not verify email link");
								sa.assertTrue(false, "could not verify email link");
							}
						}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}

				}
				else {
					appLog.error("could not open minus icon on contact access");
					sa.assertTrue(false, "could not open minus icon on contact access");
				}
			}
			else {
				appLog.error("could not find "+M17FundName1);
				sa.assertTrue(false, "could not find fund "+M17FundName1);
			}
				switchToDefaultContent(driver);
		}
		lp.CRMlogout();
		sa.assertAll();
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc004_verifyFunctionalityOfAddSelectBtn(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
//		String standardfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
//		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
//		String internalfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if(fp.verifyFolderPathdummy(sharedfolderpath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
					if(fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 20)!=null) {
						appLog.info("Contact access icon is displaying in "+sharedfolderpath);
						if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
							ThreadSleep(5000);
							if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
								for(int i=0; i<6; i++) {
									WebElement contactcheckBox=null;
									if(i!=3) {
										contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWFR_MA']//a[text()='"+M17Contact1EmailId+"']/../..//input", M17Contact1FirstName+" "+M17Contact1LastName+" contact check box", action.SCROLLANDBOOLEAN, 10);
										scrollDownThroughWebelement(driver,contactcheckBox, "");
									}

									ThreadSleep(3000);
									if(i==0) {
										if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
											if(isEnabled(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "select add button")) {
												appLog.info("Add select Contact(s) button is enabled");
											}else {
												appLog.error("Add Select Contact(s) button is not enabled");
												sa.assertTrue(false, "Add Select Contact(s) button is not enabled");
											}
										}else {
											appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
											sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
										}
									}
									if(i==1) {
										if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
											if(!isEnabled(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "select add button")) {
												appLog.info("Add select Contact(s) button is disabled");
											}else {
												appLog.error("Add Select Contact(s) button is not disabled");
												sa.assertTrue(false, "Add Select Contact(s) button is not disabled");
											}
										}else {
											appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
											sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
										}
									}

									if(i==2) {

										contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWFR_MA']//a[text()='"+M17Contact1EmailId+"']/../..//input", M17Contact1FirstName+" "+M17Contact1LastName+" contact check box", action.SCROLLANDBOOLEAN, 30);
										scrollDownThroughWebelement(driver,contactcheckBox, "");
										if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
											if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "add select button", action.SCROLLANDBOOLEAN)) {
												appLog.info("click on Add select Contact(s) button");
												ThreadSleep(3000);
												String contactDetails= "Remove<break>"+M17Contact1FirstName+" "+M17Contact1LastName+"<break>"+getDateAccToTimeZone("America/New_York", "MM/dd/YYYY")+"<break>"+M17Contact1EmailId+"<break>"+M17Institution1;
												SoftAssert result = fp.verifySelectedContactsGridDataInContactAccess(Workspace.FundraisingWorkspace,contactDetails);
												sa.combineAssertions(result);
												if(isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 20, CheckBoxName.View), "check box")) {
													appLog.info("View Check box is checked by default checked");
												}else {
													appLog.error("View check box is not checked by default");
													sa.assertTrue(false, "View check box is not checked by default");
												}
												if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Download), "check box")) {
													appLog.info("Download Check box is not checked by default checked");
												}else {
													appLog.error("Download Check box is checked by default checked");
													sa.assertTrue(false, "Download Check box is checked by default checked");
												}
												if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Upload), "check box")) {
													appLog.info("Upload Check box is not checked by default checked");
												}else {
													appLog.error("Upload Check box is checked by default checked");
													sa.assertTrue(false, "Upload Check box is checked by default checked");
												}
												if(click(driver, fp.getContactAccessCancelBtn(Workspace.FundraisingWorkspace, 20), "cancel buton", action.SCROLLANDBOOLEAN)) {
													appLog.info("Clicked on contact access cancel button");
													if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
														appLog.info("clicked on contact access icon");
														ThreadSleep(3000);
														if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.FundraisingWorkspace, 20), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
															appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
														}else {
															appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
															sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
														}


													}else {
														appLog.error("Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
														sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
													}

												}else {
													appLog.error("Not able to click on contact access cancel button so cannot check cancel button functionality");
													sa.assertTrue(false, "Not able to click on contact access cancel button so cannot check cancel button functionality");
												}

											}else {
												appLog.error("Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
												sa.assertTrue(false, "Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
											}
										}else {
											appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
											sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
										}

									}

									WebElement remove;
									if(i==3) {

										if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
											appLog.error("Able to expad Contact Access");
											ThreadSleep(2000);
											contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWFR_MA']//a[text()='"+M17Contact1EmailId+"']/../..//input", M17Contact1FirstName+" "+M17Contact1LastName+" contact check box", action.SCROLLANDBOOLEAN, 30);
											
											if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
												if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "add select button", action.BOOLEAN)) {
													appLog.info("click on Add select Contact(s) button");
													ThreadSleep(3000);

													remove = fp.getcontactaccessremoveLink(Workspace.FundraisingWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
													if (remove!=null) {
														if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
															appLog.info("clicked on remove link for : "+M17Contact1EmailId);
															ThreadSleep(2000);
															if (click(driver, fp.getContactAccessCrossIcon(Workspace.FundraisingWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
																appLog.info("Clicked on Contact access Cross Icon");
															} else {
																appLog.error("Contact access Cross Icon is not clickable");
																sa.assertTrue(false, "Contact access close button is not clickable");
															}

														}else {
															appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
															sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
														}
													}
													else {
														appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
														sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
													}


												}else {
													appLog.error("Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
													sa.assertTrue(false, "Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
												}
											}else {
												appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
												sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
											}
										}else{
											appLog.error("Not able to expad Contact Access");
											sa.assertTrue(false, "Not able to expad Contact Access");

										}


									}
									
									if(i==4) {

										if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on contact access icon");
											ThreadSleep(3000);
											if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.FundraisingWorkspace, 20), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
												appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
											}else {
												appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
												sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
											}

											if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
												appLog.error("Able to expad Contact Access");
												ThreadSleep(2000);

												if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
													if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "add select button", action.BOOLEAN)) {
														appLog.info("click on Add select Contact(s) button");
														ThreadSleep(3000);

														remove = fp.getcontactaccessremoveLink(Workspace.FundraisingWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
														if (remove!=null) {
															if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
																appLog.info("clicked on remove link for : "+M17Contact1EmailId);
																ThreadSleep(2000);

																remove = fp.getcontactaccessremoveLink(Workspace.FundraisingWorkspace, EnableDisable.Enable, M17Contact1EmailId, 5);
																if (remove==null) {
																	appLog.info("Contact is not in Grid after Clicking on Remove Link");
																} else {
																	appLog.error("Contact Should not be in Grid after Clicking on Remove Link");
																	sa.assertTrue(false, "Contact Should not be in Grid after Clicking on Remove Link");
																}	

																if (click(driver, fp.getCancelBtn(Workspace.FundraisingWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
																	appLog.error("Clicked on Contact access Cancel Btn");	
																} else {
																	appLog.error("Contact access Cancel Btn is not clickable");
																	sa.assertTrue(false, "Contact Cancel Cancel Btn is not clickable");
																}

															}else {
																appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
																sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
															}
														}
														else {
															appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
															sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
														}


													}else {
														appLog.error("Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
														sa.assertTrue(false, "Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
													}
												}else {
													appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
													sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
												}
											}else{
												appLog.error("Not able to expad Contact Access");
												sa.assertTrue(false, "Not able to expad Contact Access");

											}


										}else {
											appLog.error("Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
											sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
										}

									}

									if(i==5) {

										if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on contact access icon");
											ThreadSleep(3000);
											if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.FundraisingWorkspace, 20), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
												appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
											}else {
												appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
												sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
											}

											if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
												appLog.error("Able to expad Contact Access");
												ThreadSleep(2000);

												if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
													if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "add select button", action.BOOLEAN)) {
														appLog.info("click on Add select Contact(s) button");
														ThreadSleep(3000);

														remove = fp.getcontactaccessremoveLink(Workspace.FundraisingWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
														if (remove!=null) {
															if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
																appLog.info("clicked on remove link for : "+M17Contact1EmailId);
																ThreadSleep(2000);

																remove = fp.getcontactaccessremoveLink(Workspace.FundraisingWorkspace, EnableDisable.Enable, M17Contact1EmailId, 5);
																if (remove==null) {
																	appLog.info("Contact is not in Grid after Clicking on Remove Link");
																} else {
																	appLog.error("Contact Should not be in Grid after Clicking on Remove Link");
																	sa.assertTrue(false, "Contact Should not be in Grid after Clicking on Remove Link");
																}	

																if (click(driver, fp.getApplyBtn(Workspace.FundraisingWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
																	appLog.info("Clicked on Contact access Apply Btn");
																	ThreadSleep(2000);

																	if (click(driver, fp.getCloseBtn(Workspace.FundraisingWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
																		appLog.info("Clicked on Confirmation Close Btn");
																		ThreadSleep(2000);
																		if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
																			appLog.info("clicked on contact access icon");
																			ThreadSleep(3000);
																			if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.FundraisingWorkspace, 20), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
																				appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
																			}else {
																				appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
																				sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
																			}


																		}else {
																			appLog.error("Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
																			sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
																		}
																	} else {
																		appLog.error("Confirmation Close Btn is not clickable");
																		sa.assertTrue(false, "Confirmation Close Btn is not clickable");
																	}
																} else {
																	appLog.error("Contact access Apply Btn is not clickable");
																	sa.assertTrue(false, "Contact Apply Cancel Btn is not clickable");
																}

															}else {
																appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
																sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
															}
														}
														else {
															appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
															sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
														}


													}else {
														appLog.error("Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
														sa.assertTrue(false, "Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
													}
												}else {
													appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
													sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
												}
											}else{
												appLog.error("Not able to expad Contact Access");
												sa.assertTrue(false, "Not able to expad Contact Access");

											}


										}else {
											appLog.error("Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
											sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
										}




									}

								}
							}else {
								appLog.error("Not able to expand contact access pop so cannot verify select add button functionality");
								sa.assertTrue(false, "Not able to expand contact access pop so cannot verify select add button functionality");
							}
						}else {
							appLog.error("Not able to click on contact access icon so cannot verify select add button functionality");
							sa.assertTrue(false, "Not able to click on contact access icon so cannot verify select add button functionality");
						}
					}else {
						appLog.error("Contact access pop up is not displaying in "+sharedfolderpath);
						sa.assertTrue(false, "Contact access pop up is not displaying in "+sharedfolderpath);
					}
				}else {
					appLog.error("Not able to click on folder: "+sharedfolderpath+" so cannot verify contact access icon");
					sa.assertTrue(false, "Not able to click on folder: "+sharedfolderpath+" so cannot verify contact access icon");
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot build fundraising workspace: "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build fundraising workspace: "+M17FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc005_VerifyStandardFolderContactAccessInFR() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String cols="<break>Contact Name<break>E-mail<break>Firm<break>Action<break>Contact Name<break>Granted Access On<break>E-mail<break>Firm<break><break><break>";
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		if (lp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
		
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver,fp.getInvestorWorkSpaceSection(30) , "investor workspace section");
				if (fp.verifyFolderPathdummy("", M17Institution1, "", null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("clicked on std folder");
				}
				else {
					appLog.error("could not click on std folder");
					sa.assertTrue(false, "could not click on std folder");
				}
				if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
					//verify UI
					if (fp.verifyUIOfContactAccess(Workspace.FundraisingWorkspace, cols, M17Institution1)) {
						appLog.info("successfully verified UI of contact access");
					}
					else {
						appLog.error("could not verify UI of contact access");
						sa.assertTrue(false, "could not verify UI of contact access");
					}
					if (mouseOverOperation(driver, fp.uploadDocTooltipContactAccess(Workspace.FundraisingWorkspace, 30))) {
						ThreadSleep(3000);
						if (getAttribute(driver, fp.uploadDocTooltipContactAccess(Workspace.FundraisingWorkspace, 30), "contact access upload access i icon", "title").equalsIgnoreCase(fp.contactAccessUploadToolTipText)) {
							appLog.info("successfully verified contact access upload access i icon text");
						}
						else {
							appLog.error("contact access upload access i icon text is not correct");
							sa.assertTrue(false, "contact access upload access i icon text is not correct");
						}
					}
				}
				if (click(driver, fp.getContactAccessCrossIcon(Workspace.FundraisingWorkspace, 30), "cross icon", action.SCROLLANDBOOLEAN)) {
					//verify UI is closed
					if (fp.getContactAccessHeadText(Workspace.FundraisingWorkspace, 30)==null) {
						appLog.info("successfully closed contact access UI");
					}
					else {
						appLog.error("contact access UI is not closed");
						sa.assertTrue(false, "contact access UI is not closed");
					}
				}
				
				if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
					//verify UI
					if (fp.verifyUIOfContactAccess(Workspace.FundraisingWorkspace, cols, M17Institution1)) {
						appLog.info("successfully verified UI of contact access");
					}
					else {
						appLog.error("could not verify UI of contact access");
						sa.assertTrue(false, "could not verify UI of contact access");
					}
				}
				if (click(driver, fp.cancelButtonContactAccessActive(Workspace.FundraisingWorkspace), "cancel button", action.SCROLLANDBOOLEAN)) {
					//verify UI is closed
					if (fp.getContactAccessHeadText(Workspace.FundraisingWorkspace, 30)==null) {
						appLog.info("successfully closed contact access UI");
					}
					else {
						appLog.error("contact access UI is not closed");
						sa.assertTrue(false, "contact access UI is not closed");
					}
				}
				if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
					if (mouseOverOperation(driver, fp.getMouseOverIconContactAccessimg(30))) {
						ThreadSleep(3000);
						if (getAttribute(driver, fp.getMouseOverIconContactAccess(30), "contact access i icon", "title").equalsIgnoreCase(fp.contactAccessHoverText)) {
							appLog.info("successfully verified contact access i icon text");
						}
						else {
							appLog.error("contact access i icon text is not correct");
							sa.assertTrue(false, "contact access i icon text is not correct");
						}
					}
				}
				//verify firm column
				List<String> firms = new ArrayList<String>();
				firms.add(M17Institution1);
				//open section in contact access
				if (fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
					if (fp.compareFirmColumnContactAccess(Workspace.FundraisingWorkspace, firms)) {
						appLog.info("successfully found firm names are correct");
					}
					else {
						appLog.error("could not find firm names as correct");
						sa.assertTrue(false, "could not find firm names as correct");
					}
					//click on contact 1 checkbox
					click(driver, fp.checkboxOnContactAccessUI(Workspace.FundraisingWorkspace, M17Contact1EmailId), "checkbox of "+M17Contact1FirstName, action.SCROLLANDBOOLEAN);
					if (click(driver, fp.cancelButtonContactAccessActive(Workspace.FundraisingWorkspace), "cancel button", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
							if (fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {

								if (!isSelected(driver,  fp.checkboxOnContactAccessUI(Workspace.FundraisingWorkspace, M17Contact1EmailId), "checkbox of "+M17Contact1FirstName)) {
									appLog.info("after clicking cancel button checkbox for contact 1 is unchecked as expected");
								}
								else {
									appLog.error("after clicking cancel button checkbox for contact 1 is checked but it should not be");
									sa.assertTrue(false, "after clicking cancel button checkbox for contact 1 is checked but it should not be");
								}
							}
							else {
								appLog.error("could not expand contact access");
								sa.assertTrue(false, "could not expand contact access");
							}
						}
						else {
							appLog.error("contact access icon is not clickable");
							sa.assertTrue(false, "contact access icon is not clickable");
						}
					}
					else {
						appLog.error("cancel button is not clickable on contact access");
						sa.assertTrue(false, "cancel button is not clickable on contact access");
					}
					
				click(driver, fp.getContactAccessCrossIcon(Workspace.FundraisingWorkspace, 30), "cross icon", action.SCROLLANDBOOLEAN);
				// AZHAR ADDED missing step8 id- 00027629
				if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
					if (fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
						if (click(driver, fp.checkboxOnContactAccessUI(Workspace.FundraisingWorkspace, M17Contact1EmailId), "checkbox of "+M17Contact1FirstName, action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "add select contact btn", action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getcontactaccessremoveLink(Workspace.FundraisingWorkspace, EnableDisable.Enable, M17Contact1EmailId, 30), "Remove link", action.BOOLEAN)) {
									if (click(driver, fp.getApplyBtn(Workspace.FundraisingWorkspace, 30), "apply button", action.SCROLLANDBOOLEAN)) {
										click(driver, fp.getCloseBtn(Workspace.FundraisingWorkspace, 30), "close button", action.BOOLEAN);
										ThreadSleep(2000);
									}
									else {
										appLog.error("could not click on apply button in contact access");
										sa.assertTrue(false, "could not click on apply button in contact access");
									}
									
								}
								else {
									appLog.error("remove link is not clickable");
									sa.assertTrue(false, "remove link is not clickable");
								}
							}
							else {
								appLog.error("add selected contacts btn is not clickable");
								sa.assertTrue(false, "add selected contacts btn is not clickable");
							}
						}
						else {
							appLog.error("contact checkbox is not clickable");
							sa.assertTrue(false, "contact checkbox is not clickable");
						}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}

				}
				else {
					appLog.error("could not open minus icon on contact access");
					sa.assertTrue(false, "could not open minus icon on contact access");
				}
			//	click(driver, fp.getContactAccessCrossIcon(Workspace.FundraisingWorkspace, 30), "cross icon", action.SCROLLANDBOOLEAN);
				if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
					if (fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
						//click on email link
						click(driver, FindElement(driver, "//a[text()='"+M17Contact1EmailId+"']", "contact email id link", action.SCROLLANDBOOLEAN, 30), "contact email id link", action.BOOLEAN);
						if (nim.verifyNavatarSalesTeamLinkFunctionality("other")) {
							appLog.info("successfully verified email link");
						}
						else {
							appLog.error("could not verify email link");
							sa.assertTrue(false, "could not verify email link");
						}
					}
				}
				else {
					appLog.error("contact access icon is not clickable");
					sa.assertTrue(false, "contact access icon is not clickable");
				}
					switchToDefaultContent(driver);
				}
				else {
					appLog.error("could not open minus icon on contact access");
					sa.assertTrue(false, "could not open minus icon on contact access");
				}
			}
			else {
				appLog.error("could not find "+M17FundName1);
				sa.assertTrue(false, "could not find fund "+M17FundName1);
			}
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc006_verifyContactAccessPopUpOnSTDFolderInFR(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		//		String standardfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
	//	String sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		//		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		//		String internalfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		WebElement remove=null;
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if(fp.verifyFolderPathdummy("", M17Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
					if(fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 20)!=null) {
						appLog.info("Contact access icon is displaying in "+M17Institution1);
						if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
							ThreadSleep(5000);
							if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
								for(int i=0; i<6; i++) {
									WebElement contactcheckBox=null;
									if(i!=3) {
										contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWFR_MA']//a[text()='"+M17Contact1EmailId+"']/../..//input", M17Contact1FirstName+" "+M17Contact1LastName+" contact check box", action.SCROLLANDBOOLEAN, 10);
										scrollDownThroughWebelement(driver,contactcheckBox, "");
									}

									ThreadSleep(3000);
									if(i==0) {
										if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
											if(isEnabled(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "select add button")) {
												appLog.info("Add select Contact(s) button is enabled");
											}else {
												appLog.error("Add Select Contact(s) button is not enabled");
												sa.assertTrue(false, "Add Select Contact(s) button is not enabled");
											}
										}else {
											appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
											sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
										}
									}
									if(i==1) {
										if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
											if(!isEnabled(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "select add button")) {
												appLog.info("Add select Contact(s) button is disabled");
											}else {
												appLog.error("Add Select Contact(s) button is not disabled");
												sa.assertTrue(false, "Add Select Contact(s) button is not disabled");
											}
										}else {
											appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
											sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
										}
									}

									if(i==2) {

										contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWFR_MA']//a[text()='"+M17Contact1EmailId+"']/../..//input", M17Contact1FirstName+" "+M17Contact1LastName+" contact check box", action.SCROLLANDBOOLEAN, 30);
										scrollDownThroughWebelement(driver,contactcheckBox, "");
										if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
											if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "add select button", action.SCROLLANDBOOLEAN)) {
												appLog.info("click on Add select Contact(s) button");
												ThreadSleep(3000);
												String contactDetails= "Remove<break>"+M17Contact1FirstName+" "+M17Contact1LastName+"<break>"+getDateAccToTimeZone("America/New_York", "MM/dd/YYYY")+"<break>"+M17Contact1EmailId+"<break>"+M17Institution1;
												SoftAssert result = fp.verifySelectedContactsGridDataInContactAccess(Workspace.FundraisingWorkspace,contactDetails);
												sa.combineAssertions(result);
												if(isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 20, CheckBoxName.View), "check box")) {
													appLog.info("View Check box is checked by default checked");
												}else {
													appLog.error("View check box is not checked by default");
													sa.assertTrue(false, "View check box is not checked by default");
												}
												if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Download), "check box")) {
													appLog.info("Download Check box is not checked by default checked");
												}else {
													appLog.error("Download Check box is checked by default checked");
													sa.assertTrue(false, "Download Check box is checked by default checked");
												}
												if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Upload), "check box")) {
													appLog.info("Upload Check box is not checked by default checked");
												}else {
													appLog.error("Upload Check box is checked by default checked");
													sa.assertTrue(false, "Upload Check box is checked by default checked");
												}
												if(click(driver, fp.getContactAccessCancelBtn(Workspace.FundraisingWorkspace, 20), "cancel buton", action.SCROLLANDBOOLEAN)) {
													appLog.info("Clicked on contact access cancel button");
													if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
														appLog.info("clicked on contact access icon");
														ThreadSleep(3000);
														if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.FundraisingWorkspace, 20), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
															appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
														}else {
															appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
															sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
														}


													}else {
														appLog.error("Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
														sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
													}

												}else {
													appLog.error("Not able to click on contact access cancel button so cannot check cancel button functionality");
													sa.assertTrue(false, "Not able to click on contact access cancel button so cannot check cancel button functionality");
												}

											}else {
												appLog.error("Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
												sa.assertTrue(false, "Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
											}
										}else {
											appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
											sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
										}

									}

									if(i==3) {

										if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
											appLog.error("Able to expad Contact Access");
											ThreadSleep(2000);
											contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWFR_MA']//a[text()='"+M17Contact1EmailId+"']/../..//input", M17Contact1FirstName+" "+M17Contact1LastName+" contact check box", action.SCROLLANDBOOLEAN, 30);
											
											if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
												if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "add select button", action.BOOLEAN)) {
													appLog.info("click on Add select Contact(s) button");
													ThreadSleep(3000);

													remove = fp.getcontactaccessremoveLink(Workspace.FundraisingWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
													if (remove!=null) {
														if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
															appLog.info("clicked on remove link for : "+M17Contact1EmailId);
															ThreadSleep(2000);
															if (click(driver, fp.getContactAccessCrossIcon(Workspace.FundraisingWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
																appLog.info("Clicked on Contact access Cross Icon");
															} else {
																appLog.error("Contact access Cross Icon is not clickable");
																sa.assertTrue(false, "Contact access close button is not clickable");
															}

														}else {
															appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
															sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
														}
													}
													else {
														appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
														sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
													}


												}else {
													appLog.error("Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
													sa.assertTrue(false, "Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
												}
											}else {
												appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
												sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
											}
										}else{
											appLog.error("Not able to expad Contact Access");
											sa.assertTrue(false, "Not able to expad Contact Access");

										}


									}
									
									if(i==4) {

										if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on contact access icon");
											ThreadSleep(3000);
											if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.FundraisingWorkspace, 20), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
												appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
											}else {
												appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
												sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
											}

											if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
												appLog.error("Able to expad Contact Access");
												ThreadSleep(2000);

												if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
													if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "add select button", action.BOOLEAN)) {
														appLog.info("click on Add select Contact(s) button");
														ThreadSleep(3000);

														remove = fp.getcontactaccessremoveLink(Workspace.FundraisingWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
														if (remove!=null) {
															if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
																appLog.info("clicked on remove link for : "+M17Contact1EmailId);
																ThreadSleep(2000);

																remove = fp.getcontactaccessremoveLink(Workspace.FundraisingWorkspace, EnableDisable.Enable, M17Contact1EmailId, 5);
																if (remove==null) {
																	appLog.info("Contact is not in Grid after Clicking on Remove Link");
																} else {
																	appLog.error("Contact Should not be in Grid after Clicking on Remove Link");
																	sa.assertTrue(false, "Contact Should not be in Grid after Clicking on Remove Link");
																}	

																if (click(driver, fp.getCancelBtn(Workspace.FundraisingWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
																	appLog.error("Clicked on Contact access Cancel Btn");	
																} else {
																	appLog.error("Contact access Cancel Btn is not clickable");
																	sa.assertTrue(false, "Contact Cancel Cancel Btn is not clickable");
																}

															}else {
																appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
																sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
															}
														}
														else {
															appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
															sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
														}


													}else {
														appLog.error("Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
														sa.assertTrue(false, "Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
													}
												}else {
													appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
													sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
												}
											}else{
												appLog.error("Not able to expad Contact Access");
												sa.assertTrue(false, "Not able to expad Contact Access");

											}


										}else {
											appLog.error("Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
											sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
										}

									}

									if(i==5) {

										if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on contact access icon");
											ThreadSleep(3000);
											if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.FundraisingWorkspace, 20), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
												appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
											}else {
												appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
												sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
											}

											if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
												appLog.error("Able to expad Contact Access");
												ThreadSleep(2000);

												if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
													if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "add select button", action.BOOLEAN)) {
														appLog.info("click on Add select Contact(s) button");
														ThreadSleep(3000);

														remove = fp.getcontactaccessremoveLink(Workspace.FundraisingWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
														if (remove!=null) {
															if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
																appLog.info("clicked on remove link for : "+M17Contact1EmailId);
																ThreadSleep(2000);

																remove = fp.getcontactaccessremoveLink(Workspace.FundraisingWorkspace, EnableDisable.Enable, M17Contact1EmailId, 5);
																if (remove==null) {
																	appLog.info("Contact is not in Grid after Clicking on Remove Link");
																} else {
																	appLog.error("Contact Should not be in Grid after Clicking on Remove Link");
																	sa.assertTrue(false, "Contact Should not be in Grid after Clicking on Remove Link");
																}	

																if (click(driver, fp.getApplyBtn(Workspace.FundraisingWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
																	appLog.info("Clicked on Contact access Apply Btn");
																	ThreadSleep(2000);

																	if (click(driver, fp.getCloseBtn(Workspace.FundraisingWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
																		appLog.info("Clicked on Confirmation Close Btn");
																		ThreadSleep(2000);
																		if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
																			appLog.info("clicked on contact access icon");
																			ThreadSleep(3000);
																			if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.FundraisingWorkspace, 20), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
																				appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
																			}else {
																				appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
																				sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
																			}


																		}else {
																			appLog.error("Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
																			sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
																		}
																	} else {
																		appLog.error("Confirmation Close Btn is not clickable");
																		sa.assertTrue(false, "Confirmation Close Btn is not clickable");
																	}
																} else {
																	appLog.error("Contact access Apply Btn is not clickable");
																	sa.assertTrue(false, "Contact Apply Cancel Btn is not clickable");
																}

															}else {
																appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
																sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
															}
														}
														else {
															appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
															sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
														}


													}else {
														appLog.error("Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
														sa.assertTrue(false, "Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
													}
												}else {
													appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
													sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
												}
											}else{
												appLog.error("Not able to expad Contact Access");
												sa.assertTrue(false, "Not able to expad Contact Access");

											}


										}else {
											appLog.error("Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
											sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
										}




									}

								}
							}else {
								appLog.error("Not able to expand contact access pop so cannot verify select add button functionality");
								sa.assertTrue(false, "Not able to expand contact access pop so cannot verify select add button functionality");
							}
						}else {
							appLog.error("Not able to click on contact access icon so cannot verify select add button functionality");
							sa.assertTrue(false, "Not able to click on contact access icon so cannot verify select add button functionality");
						}
					}else {
						appLog.error("Contact access pop up is not displaying in "+M17Institution1);
						sa.assertTrue(false, "Contact access pop up is not displaying in "+M17Institution1);
					}
				}else {
					appLog.error("Not able to click on folder: "+M17Institution1+" so cannot verify contact access icon");
					sa.assertTrue(false, "Not able to click on folder: "+M17Institution1+" so cannot verify contact access icon");
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot build fundraising workspace: "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build fundraising workspace: "+M17FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc007_inviteContactAndSendEMailToContact(String environment, String mode) {
	LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
	FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
	lp.CRMLogin(CRMUser1EmailID,adminPassword);
	if(fp.clickOnTab(TabName.FundsTab)) {
		if(fp.clickOnCreatedFund(M17FundName1)) {
			if(fp.inviteContact(environment,mode,M17Institution1, M17Contact1EmailId,null, FolderType.Standard, null, null, null,null, Workspace.FundraisingWorkspace, null)) {
				appLog.info(M17Contact1EmailId+" Contact is selected successfully ");
				ThreadSleep(2000);
				if(click(driver, fp.getApplyBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" apply button", action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on apply button successfully");
					if(fp.getInviteContactSuccessfulErrorMsg(Workspace.FundraisingWorkspace, 30)!=null) {
						String aa =fp.getInviteContactSuccessfulErrorMsg(Workspace.FundraisingWorkspace, 30).getText().trim();
						if(aa.contains(FundsPageErrorMessage.InviteContactSuccessfulErrorMsg)) {
							appLog.info(FundsPageErrorMessage.InviteContactSuccessfulErrorMsg+" error message is matched ");
						}else {
							appLog.error(FundsPageErrorMessage.InviteContactSuccessfulErrorMsg+" error message is not matched ");
							sa.assertTrue(false, FundsPageErrorMessage.InviteContactSuccessfulErrorMsg+" error message is not matched ");
						}
					}else {
						appLog.error(FundsPageErrorMessage.InviteContactSuccessfulErrorMsg+" Error Message is not visible so cannot verify it");
						sa.assertTrue(false, FundsPageErrorMessage.InviteContactSuccessfulErrorMsg+" Error Message is not visible so cannot verify it");
					}
					if(fp.getInviteContactSuccessfulPopUpCrossIcon(Workspace.FundraisingWorkspace, 20)!=null) {
						appLog.info("Cross icon is visible in confirmation pop up");
					}else {
						appLog.error("Cross icon is not visible in confirmation pop up");
						sa.assertTrue(false, "Cross icon is not visible in confirmation pop up");
					}
					if(click(driver, fp.getCloseBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" close button", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Close Buton");	
						
					}else {
						appLog.error("Not able to click on close button so cannot close confirmation popup");
						sa.assertTrue(false, "Not able to click on close button so cannot close confirmation popup");
						
					}
					if(fp.sendInvitationMail(Workspace.FundraisingWorkspace, M17Contact1EmailId,M17Institution1,M17Contact1EmailId)) {
						appLog.info("Email send successfully to contact "+M17Contact1EmailId);
						
					}else {
						appLog.error("Not able to send email to contact "+M17Contact1EmailId+" from manage emails");
						sa.assertTrue(false, "Not able to send email to contact "+M17Contact1EmailId+" from manage emails");
					}
				}else {
					appLog.error("Not able to click on apply button so cannot invite contact "+M17Contact1EmailId+" and verify error message");
					sa.assertTrue(false, "Not able to click on apply button so cannot invite contact "+M17Contact1EmailId+" and verify error message");
				}
			}else {
				appLog.error("Not able to select Contact from contact access grid "+M17Contact1EmailId);
				sa.assertTrue(false, "Not able to select Contact from contact access grid "+M17Contact1EmailId);
			}
		}else {
			appLog.error("Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
		}
	}else {
		appLog.error("Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
		sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
	}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc008_1_registerM3Contact1(){
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String stdPath = ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		if (ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M17Contact1", excelLabel.Registered).equalsIgnoreCase("Not Registered")) {
			try {
				investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail",gmailUserName,gmailPassword,CRMUser1EmailID,M17Contact1EmailId);
				appLog.info("Investor Registration Link: " + investorRegLink);
			} catch (InterruptedException e) {
				appLog.info("Invitation mail not found.");
				e.printStackTrace();
			}
			if (investorRegLink != null) {
				driver.get(investorRegLink);
				if (bp.targetRegistration(M17Contact1FirstName, M17Contact1LastName, M17Contact1EmailId, M17Institution1,
						adminPassword)) {
					appLog.info(
							"Investor is registered successfully: " + M17Contact1FirstName + " " + M17Contact1LastName);
					ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M17Contact1", excelLabel.Registered);
				} else {
					driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
					appLog.info("Investor Target URL through Direct URL..");
					if (bp.targetRegistration(M17Contact1FirstName, M17Contact1LastName, M17Contact1EmailId,
							M17Institution1, adminPassword)) {
						appLog.info("Investor is registered successfully: " + M17Contact1FirstName + " "
								+ M17Contact1LastName);
						ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M17Contact1", excelLabel.Registered);
					} else {
						appLog.error("Investor is not Registered through Invited Link: " + M17Contact1FirstName + " "
								+ M17Contact1LastName);
						sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M17Contact1FirstName
								+ " " + M17Contact1LastName);
					}
				}
			} else {
				driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
				appLog.info("Register Investor URL through Direct URL..");
				if (bp.targetRegistration(M17Contact1FirstName, M17Contact1LastName, M17Contact1EmailId,
						M17Institution1, adminPassword)) {
					appLog.info("Investor is registered successfully: " + M17Contact1FirstName + " "
							+ M17Contact1LastName);
					ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M17Contact1", excelLabel.Registered);
				} else {
					appLog.error("Investor is not Registered through Invited Link: " + M17Contact1FirstName + " "
							+ M17Contact1LastName);
					sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M17Contact1FirstName
							+ " " + M17Contact1LastName);
				}
			}
			if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {

				for (int i = 0;i<4;i++) {
					if (fp.verifyFolderPathdummy(cmnPath.split(",")[i], null, null, null, PageName.PotentialInvestmentPage,
							null, 60)) {
						appLog.info(" Folder Structure Verified: " + cmnPath.split(",")[i]);

					}
					else {
						appLog.error("could not find folder structure "+cmnPath.split(",")[i]);
						sa.assertTrue(false, "could not find folder structure "+cmnPath.split(",")[i]);
					}

				}
				for (int i = 0;i<2;i++) {
					if (fp.verifyFolderPathdummy(stdPath.split(",")[i], M17Institution1, null, null, PageName.PotentialInvestmentPage,
							null, 60)) {
						appLog.info(" Folder Structure Verified: " + stdPath.split(",")[i]);

					}
					else {
						appLog.error("could not find folder structure "+stdPath.split(",")[i]);
						sa.assertTrue(false, "could not find folder structure "+stdPath.split(",")[i]);
					}
				}
				//verify bulk download button
				
				if (ifp.getBulkDownloadIcon(5) == null) {
					appLog.info("Bulk Download Icon is not visible.");
				} else {
					appLog.error("Bulk Download Icon Should not be visible.");
					sa.assertTrue(false, "Bulk Download Icon Should not be visible.");
				}
			}
			lp.investorLogout();
		} else {
			appLog.info("investor "+M17Contact1FirstName+" "+M17Contact1LastName+" is already Registered.");
			sa.assertTrue(false, "investor "+M17Contact1FirstName+" "+M17Contact1LastName+" is already Registered.");
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc008_2_VerifyContentGridFRW() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		String dependentTc = "M17tc008_1_registerM3Contact1";
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependentTc,excelLabel.CommonPath);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		if (lp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");
				if (fp.verifyFolderPathdummy(cmnPath.split(",")[0], null, null, M17FundName1, PageName.ContactsPage,
						Workspace.FundraisingWorkspace, 60)) {
					if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 10)!=null) {
						appLog.info("successfully found enabled download access icon");
					}
					else {
						appLog.error("download access icon is not enabled");
						sa.assertTrue(false,"download access icon is not enabled");
					}
					if ( cp.getViewAccessIcon(Workspace.FundraisingWorkspace, 10)!=null) {
						appLog.info("successfully found enabled View access icon");
					}
					else {
						appLog.error("View access icon is not enabled");
						sa.assertTrue(false,"View access icon is not enabled");
					}
				}
				else {
					appLog.error("common path is not found on contacts page folder structure");
					sa.assertTrue(false, "common path is not found on contacts page folder structure");
				}
				if (fp.verifyFolderPathdummy("",M17Institution1 , "", M17FundName1, PageName.ContactsPage,
						Workspace.FundraisingWorkspace, 60)) {
					if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 10)==null) {
						appLog.info("successfully absent enabled download access icon");
					}
					else {
						appLog.error("download access icon is enabled, but it should not be");
						sa.assertTrue(false,"download access icon is enabled, but it should not be");
					}
					if ( cp.getViewAccessIcon(Workspace.FundraisingWorkspace, 10)!=null) {
						appLog.info("successfully found enabled View access icon");
					}
					else {
						appLog.error("View access icon is not enabled");
						sa.assertTrue(false,"View access icon is not enabled");
					}
				}
				else {
					appLog.error("folder path on contact page is not found");
					sa.assertTrue(false, "folder path on contact page is not found");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("contact not found on contacts page");
				sa.assertTrue(false, "contact not found on contacts page");
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		if (cp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "workspace section");
				if (fp.verifyFolderPathdummy("", M17Institution1, null, null, PageName.FundsPage,Workspace.FundraisingWorkspace,60)) {
					if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "contact access icon", action.SCROLLANDBOOLEAN)) {
						if (fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
							//verify select contacts grid
							String contactDetails= "Remove<break>"+M17Contact1FirstName+" "+M17Contact1LastName+"<break>"+getDateAccToTimeZone("America/New_York", "MM/dd/YYYY")+"<break>"+M17Contact1EmailId+"<break>"+M17Institution1;
							SoftAssert result = fp.verifySelectedContactsGridDataInContactAccess(Workspace.FundraisingWorkspace,contactDetails);
							sa.combineAssertions(result);
							if(isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.View), "check box")) {
								appLog.info("View Check box is checked by default checked");
							}else {
								appLog.error("View check box is not checked by default");
								sa.assertTrue(false, "View check box is not checked by default");
							}
							if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Download), "check box")) {
								appLog.info("Download Check box is not checked by default checked");
							}else {
								appLog.error("Download Check box is checked by default checked");
								sa.assertTrue(false, "Download Check box is checked by default checked");
							}
							//check and uncheck download checkbox
							if (click(driver, fp.uploadDownloadCheckboxContactAccess(Workspace.FundraisingWorkspace, CheckBoxName.Download, M17Contact1EmailId), "checkbox of contact 1", action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.uploadDownloadCheckboxContactAccess(Workspace.FundraisingWorkspace, CheckBoxName.Download, M17Contact1EmailId), "checkbox of contact 1", action.SCROLLANDBOOLEAN)) {
									if (click(driver, fp.getApplyBtn(Workspace.FundraisingWorkspace, 30), "apply button", action.BOOLEAN)) {
										if (click(driver, fp.getCloseBtn(Workspace.FundraisingWorkspace, 30), "remove access close button", action.BOOLEAN)) {
											
										}
										else {
											appLog.error("remove access button is not clickable");
											sa.assertTrue(false, "remove access button is not clickable");
										}
									}
									else {
										appLog.error("apply button is not clickable");
										sa.assertTrue(false, "apply button is not clickable");
									}
								}
								else {
									appLog.error("download checkbox is not clickable");
									sa.assertTrue(false, "download checkbox is not clickable");
								}
							}
							else {
								appLog.error("download checkbox is not clickable");
								sa.assertTrue(false, "download checkbox is not clickable");
							}
						}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}
					if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "contact access icon", action.SCROLLANDBOOLEAN)) {

						if (!isSelected(driver, fp.uploadDownloadCheckboxContactAccess(Workspace.FundraisingWorkspace, CheckBoxName.Download, M17Contact1EmailId), "download checkbox")) {
								appLog.info("download checkbox is not selected as expected");
							}
							else {
								appLog.error("download checkbox is selected but it should not be");
								sa.assertTrue(false, "download checkbox is selected but it should not be");
							}
						//click on email link
						if(click(driver, FindElement(driver, "//a[text()='"+M17Contact1EmailId+"']", "contact email id link", action.SCROLLANDBOOLEAN, 30), "contact email id link", action.BOOLEAN)){
							if (nim.verifyNavatarSalesTeamLinkFunctionality("other")) {
								appLog.info("successfully verified email link");
							}
							else {
								appLog.error("could not verify email link");
								sa.assertTrue(false, "could not verify email link");
							}	
						}else{
							appLog.error("Not Able to Click on Email Link");
							sa.assertTrue(false, "Not Able to Click on Email Link");	
						}
						
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}
				}
				else {
					appLog.error("folder path is not found");
					sa.assertTrue(false, "folder path is not found");
				}
			}
			else {
				appLog.error(M17FundName1+" is not found on funds page");
				sa.assertTrue(false, M17FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc009_1_LoginWithHubToEnableBulkDownLoad() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(HubUserName,HubPassword);
		String[] funds = {M17FundName1};
		for (String fund : funds) {
			
			if(fp.clickOnTab(TabName.FundsTab)) {
				if(fp.clickOnCreatedFund(fund)) {
					if (click(driver, fp.getEditButton(10), "Edit Button", action.BOOLEAN)) {
						appLog.error("Click on Edit Button : "+fund);
						ThreadSleep(1000);
						if (isSelected(driver, fp.getBulkDownLoadCheckBox(Workspace.FundraisingWorkspace, 10), "Bulk DownLoad CheckBox")) {
							appLog.info("Bulk DownLoad is Already Checked");	
						}else{
							if (click(driver, fp.getBulkDownLoadCheckBox(Workspace.FundraisingWorkspace, 10), "Bulk DownLoad CheckBox", action.SCROLLANDBOOLEAN)) {
								appLog.info(fund+" clicked on Bulk DownLoad CheckBox : "+Workspace.FundraisingWorkspace);	
							} else {
								appLog.error(fund+" Not able to click on Bulk DownLoad CheckBox : "+Workspace.FundraisingWorkspace);
								sa.assertTrue(false, fund+" Not able to click on Bulk DownLoad CheckBox : "+Workspace.FundraisingWorkspace);
							}	
						}
						ThreadSleep(2000);
						if (click(driver, fp.getSaveButton(10), "Save Button", action.SCROLLANDBOOLEAN)) {
							appLog.error("click on Save Button : "+fund);
						} else {
							appLog.error("Not able to click on Save Button : "+fund);
							sa.assertTrue(false, "Not able to click on Save Button : "+fund);
						}

					} else {
						appLog.error("Not able to click on Edit Button : "+fund);
						sa.assertTrue(false, "Not able to click on Edit Button : "+fund);
					}
				}else {
					appLog.error("Not able to click on created fund Name : "+fund);
					sa.assertTrue(false, "Not able to click on created fund Name : "+fund);

				}
			}else {
				appLog.error("Not able to click on fund tab");
				sa.assertTrue(false, "Not able to click on fund tab");
			}
			switchToDefaultContent(driver);
			
		}
		
		sa.assertAll();

	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc009_2_LoginWithHunToEnableBulkDownLoad() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		String updatedFirmName= M17Institution1+"_FR";
		
		//Investor Side
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			
			//verify bulk download button
			
			if (ifp.getBulkDownloadIcon(10) != null) {
				appLog.info("Bulk Download Icon is visible.");
			} else {
				appLog.error("Bulk Download Icon Should be visible.");
				sa.assertTrue(false, "Bulk Download Icon Should be visible.");
			}
		}
		
		if (click(driver, af.getProfileLink(60), "profile link on investor login", action.SCROLLANDBOOLEAN)) {
			appLog.info("Click on Profile Link");
			if (click(driver, ip.getEditIcon(60), "edit icon on my profile page", action.SCROLLANDBOOLEAN)) {
				appLog.info("Click on Edit Icon on My Profile Tab");
				if (sendKeys(driver, ip.getFirstNameTextBox(60), M17Contact1UpdatedFirstName, "First name textbox in my profile page", action.SCROLLANDBOOLEAN)) {
					appLog.info("Entered value on First Name Text Box : "+M17Contact1UpdatedFirstName);
					if (sendKeys(driver,ip.getLastNameTextBox(60), M17Contact1UpdatedLastName, "Last name textbox in my profile page", action.SCROLLANDBOOLEAN)) {
						appLog.info("Entered value on Last Name Text Box : "+M17Contact1UpdatedLastName);
						if (click(driver,ip.getSaveButtonMyProfilePage(60), "save button on my profile page", action.SCROLLANDBOOLEAN)) {
							ThreadSleep(3000);
							appLog.info("Click onSave Btn on My Profile Tab");
						}else {
							appLog.error("save button is not clickable on my profile page");
							sa.assertTrue(false, "save button is not clickable on my profile page");
						}
					}
					else {
						appLog.error("last name textbox is not visible on my profile page");
						sa.assertTrue(false, "last name textbox is not visible on my profile page");
					}
				}
				else {
					appLog.error("first name textbox is not visible on my profile page");
					sa.assertTrue(false, "first name textbox is not visible on my profile page");
				}
			}
			else {
				appLog.error("edit icon is not clickable on my profile page.");
				sa.assertTrue(false, "edit icon is not clickable on my profile page.");
			}
		
		
		//
		
		if (click(driver, ip.getMyFirmProfileTab(60), "my firm profile tab on investor login", action.SCROLLANDBOOLEAN)) {
			appLog.info("Clicked on My Firm Profile Tab");
			if (click(driver, ip.getEditIcon(60),"edit icon on firm profile page" ,action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on Edit Icon on  My Firm Profile Tab");
				if (sendKeys(driver, ip.getFirmNameTextbox(60), updatedFirmName, "Firm Name Text Box", action.SCROLLANDBOOLEAN)) {
					appLog.info("Entered Firm Name on My Firm Profile Tab : "+"");
					if (click(driver, ip.getSaveButtonFirmProfile(60), "save button firm profile page", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(3000);
						ExcelUtils.writeData(updatedFirmName, "Contacts", excelLabel.Variable_Name, "M17Contact1", excelLabel.Contact_updated_firmname);
						appLog.info("Click onSave Btn on My Firm Profile Tab");
					}
					else {
						appLog.error("save button is not clickable on My Firm profile page");
						sa.assertTrue(false, "save button is not clickable on My Firm profile page");
					}
				}
				else {
					appLog.error("description text area is not visible on my firm profile page");
					sa.assertTrue(false, "description text area is not visible on my firm profile page");
				}
			}
			else {
				appLog.error("edit icon is not clickable on my firm profile page");
				sa.assertTrue(false, "edit icon is not clickable on my firm profile page");
			}
		}
		else {
			appLog.error("description text area is not visible on my firm profile page");
			sa.assertTrue(false, "my firm profile tab is not clickable on investor login");
		}
		
		}else {
			appLog.error("profile link is not clickable on investor login");
			sa.assertTrue(false, "profile link is not clickable on investor login");
		}
		
		
		lp.investorLogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
		
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc009_3_VerifyUpdatedInfoAndRemoveFunctionalityCRMSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		String msg;
	
		if (cp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "workspace section");
				if (fp.verifyFolderPathdummy("", M17Institution1, null, null, PageName.FundsPage,Workspace.FundraisingWorkspace,60)) {
					if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "contact access icon", action.SCROLLANDBOOLEAN)) {
							//verify select contacts grid
							String contactDetails= "Remove<break>"+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+"<break>"+getDateAccToTimeZone("America/New_York", "MM/dd/YYYY")+"<break>"+M17Contact1EmailId+"<break>"+M17Contact1UpdatedFirmName;
							SoftAssert result = fp.verifySelectedContactsGridDataInContactAccess(Workspace.FundraisingWorkspace,contactDetails);
							sa.combineAssertions(result);
							WebElement remove = isDisplayed(driver, FindElement(driver, "//a[text()='"+M17Contact1EmailId+"']/../../span/a[@title='Remove']", "remove link in front "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName, action.BOOLEAN, 60), "Visibility", 60, "Remove link");
							if (remove!=null) {
								if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on remove link");
									ThreadSleep(5000);
									
									WebElement popUpEle = fp.getContactAccessRemovedPopUpMsg(Workspace.FundraisingWorkspace, 30);
									if (popUpEle!=null) {
										appLog.info("Access Remove PopUp Element Found");
										
										 popUpEle = fp.getContactAccessRemovedPopUpMsg(Workspace.FundraisingWorkspace, 30);
											if (popUpEle!=null) {
												appLog.info("Access Remove PopUp Msg Element Found");
												msg= popUpEle.getText().trim();
												if (msg.equals(FundsPageErrorMessage.contactAccessRemovedPopUpMsg)) {
													appLog.info("Contact Access Removed Message Verified : "+msg);	
												} else {
													appLog.error("Contact Access Removed Message not Verified Actual : "+msg+" \t Expected : "+FundsPageErrorMessage.contactAccessRemovedPopUpMsg);
													sa.assertTrue(false, "Contact Access Removed Message not Verified Actual : "+msg+" \t Expected : "+FundsPageErrorMessage.contactAccessRemovedPopUpMsg);
												}
											} else {
												appLog.error("Access Remove PopUp Msg Element is null so cannot Check Msg");
												sa.assertTrue(false, "Access Remove PopUp Msg Element is null so cannot Check Msg");
											}
											
									
									} else {
										appLog.error("Access Remove PopUp Element is null so cannot Check Msg");
										sa.assertTrue(false, "Access Remove PopUp Element is null so cannot Check Msg");
									}
									
									if (click(driver, fp.getRemoveAccessClose(Workspace.FundraisingWorkspace, 30), "remove access close button", action.BOOLEAN)) {
										appLog.error("Clicked on remove access close button");	
										
										popUpEle = fp.getContactAccessRemovedPopUpMsg(Workspace.FundraisingWorkspace, 5);
										if (popUpEle==null) {
											appLog.info("Access Remove PopUp is closed after click on Closed Button");
										}else{
											appLog.error("Access Remove PopUp is not closed after click on Closed Button");
											sa.assertTrue(false, "Access Remove PopUp is not closed after click on Closed Button");	
										}
										
										if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.FundraisingWorkspace, 60), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
											appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
										}else {
											appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
											sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
										}
										
										if (click(driver, fp.getContactAccessCrossIcon(Workspace.FundraisingWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on Contact access Cross Icon");
										} else {
											appLog.error("Contact access Cross Icon is not clickable");
											sa.assertTrue(false, "Contact access close button is not clickable");
										}
										
										popUpEle = fp.getContactAccessCrossIcon(Workspace.FundraisingWorkspace, 5);
										if (popUpEle==null) {
											appLog.info("Select Contact Access PopUp is closed after click on Cross Icon");
										}else{
											appLog.error("Select Contact Access PopUp is not closed after click on Cross Icon");
											sa.assertTrue(false, "Select Contact Access PopUp is not closed after click on Cross Icon");	
										}
									}
									else {
										appLog.error("remove access close button is not clickable");
										sa.assertTrue(false, "remove access close button is not clickable");
									}
								
								}else {
									appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
									sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
								}
							}
							else {
								appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
								sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
							}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}
				}
				else {
					appLog.error("folder path is not found : "+M17Institution1);
					sa.assertTrue(false, "folder path is not found : "+M17Institution1);
				}
			}
			else {
				appLog.error(M17FundName1+" is not found on funds page");
				sa.assertTrue(false, M17FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		switchToDefaultContent(driver);
		if (lp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				
				msg=ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace;
				if (cp.verifyErrorMessageOnPage(msg,
						cp.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60),
						"Error Message after Removing Contact Access on Contact page for FundRaising workspace")) {
					appLog.info("Error Message is verified on Contact page for FundRaising workspace : "+msg);
				} else {
					sa.assertTrue(false,"Error Message is not verified on Contact page for FundRaising workspace.Expected : "+msg+ "\t Actual : "
									+ getText(driver,
											cp.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60),
											"Error Message after Removing Contact Access on Contact page for FundRaising workspace",
											action.BOOLEAN));
				}
				
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("contact not found on contacts page");
				sa.assertTrue(false, "contact not found on contacts page");
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc009_4_CheckEffectAfterRemovingContactAccessInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		
		List<WebElement> dropdownList=allOptionsInDropDrop(driver, allfp.getFirmNameDropdown(60), "Drop Down List");
		List<String> result=compareMultipleList(driver, Org1FirmName, dropdownList);
		
		if (!result.isEmpty()) {
			appLog.info("Firm Name is not available in DropDown after removing Acces");	
		} else {
			appLog.error("Firm Name is available in DropDown after removing Acces");
			sa.assertTrue(false, "Firm Name is available in DropDown after removing Acces");
		}
		lp.investorLogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc010_deleteContactAndCheckImpactInContactAccessInFR(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(contact.deleteCreatedContact(M17Contact1FirstName, M17Contact1LastName)) {
			appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact is deleted");
		}else {
			appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact is not deleted");
			sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact is not deleted");
		}
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if(fp.verifyFolderPathdummy("", M17Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
						ThreadSleep(5000);
						if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
							List<String> contactname = new ArrayList<String>();
							contactname.add(M17Contact1EmailId);
							if(!fp.selectDeselectContactFromContactAccess(contactname, SelectDeselect.Select, AllOr1By1.OneByOne,null, Workspace.FundraisingWorkspace,10).isEmpty()) {
								appLog.info(M17Contact1EmailId+"Contact is not visible in Contact Access ");
							}else {
								appLog.error(M17Contact1EmailId+"Contact is visible in Contact Access ");
								sa.assertTrue(false, M17Contact1EmailId+"Contact is visible in Contact Access ");
							}
						}else {
							appLog.error("Contact Access popup is not expanded so cannot verify  deleted contact "+M17Contact1EmailId);
							sa.assertTrue(false, "Contact Access popup is not expanded so cannot verify  deleted contact "+M17Contact1EmailId);
						}
						
					}else {
						appLog.error("Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
						sa.assertTrue(false, "Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
					}
					
				}else {
					appLog.error("Not able to click on folder: "+M17Institution1+" so cannot verify contact "+M17Contact1EmailId);
					sa.assertTrue(false, "Not able to click on folder: "+M17Institution1+" so cannot verify contact "+M17Contact1EmailId);
				}
				
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
		}
		switchToDefaultContent(driver);
		if(home.restoreValuesFromRecycleBin(M17Contact1FirstName+" "+M17Contact1LastName)) {
			appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact is restored form Recycle Bin");
		}else {
			appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact is not restored form Recycle Bin");
			sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact is not restored form Recycle Bin");
		}
		if(contact.updateEmailAddressOfCreatedContact(M17Contact1FirstName, M17Contact1LastName,"")) {
			appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact email is updated as blank");
		}else {
			appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact email is not updated as blank");
			sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact email is not updated as blank");
		}
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if(fp.verifyFolderPathdummy("", M17Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
						ThreadSleep(5000);
						if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
							List<String> contactname = new ArrayList<String>();
							contactname.add(M17Contact1EmailId);
							String [] ss= {FundsPageErrorMessage.ContactEmailblankErrorMsg,FundsPageErrorMessage.AllContactSelectErrorMsg};
							for (int i=0; i<ss.length; i++) {
								WebElement ele=null;
								scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+" Section view");
								if(i==0) {
									ele=fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20);
									
								}else {
									ele=fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, null, 20);
								}
								if(ele!=null) {
									ThreadSleep(5000);
									if(!clickUsingJavaScript(driver, ele, "check box ")) {
//									if(click(driver, ele, "check box", action.BOOLEAN)) {
										appLog.info("clicked on contact check box : "+M17Contact1FirstName+" "+M17Contact1LastName);
										ThreadSleep(2000);
										if (isAlertPresent(driver)) {
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											if (msg.trim().contains(ss[i])) {
												appLog.info(
														"Error Message is verified in contact access : "+ss[i]);
											} else {
												appLog.error(
														"Error Message is verified in contact access : "+ss[i]);
												sa.assertTrue(false,
														"Error Message is verified in contact access :"+ss[i]);
											}
										} else {
											appLog.error("Alert is not present so cannot verify alert message "+ss[i]);
											sa.assertTrue(false, "Alert is not present so cannot verify alert message "+ss[i]);
										}
										if(i==0) {
											if(!isEnabled(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 5), "select add button")) {
												appLog.info("Add select Contact(s) button is disabled");
											}else {
												appLog.error("Add Select Contact(s) button is not disabled");
												sa.assertTrue(false, "Add Select Contact(s) button is not disabled");
											}
										}
										if(i==1) {
											if(isEnabled(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "select add button")) {
												appLog.info("Add select Contact(s) button is enabled");
											}else {
												appLog.error("Add Select Contact(s) button is not enabled");
												sa.assertTrue(false, "Add Select Contact(s) button is not enabled");
											}
										}
									}else {
										appLog.error("Not able to click on Contact "+M17Contact1FirstName+" "+M17Contact1LastName);
										sa.assertTrue(false, "Not able to click on Contact "+M17Contact1FirstName+" "+M17Contact1LastName);
									}
								}else {
									appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" Contact is not visible so cannot verify error message "+ss[i]);
									sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" Contact is not visible so cannot verify error message "+ss[i]);
								}
							}
						}else {
							appLog.error("Contact Access popup is not expanded so cannot verify  deleted contact "+M17Contact1EmailId);
							sa.assertTrue(false, "Contact Access popup is not expanded so cannot verify  deleted contact "+M17Contact1EmailId);
						}
					}else {
						appLog.error("Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
						sa.assertTrue(false, "Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
					}
				}else {
					appLog.error("Not able to click on folder: "+M17Institution1+" so cannot verify contact "+M17Contact1EmailId);
					sa.assertTrue(false, "Not able to click on folder: "+M17Institution1+" so cannot verify contact "+M17Contact1EmailId);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
		}
		switchToDefaultContent(driver);
		if(contact.updateEmailAddressOfCreatedContact(M17Contact1FirstName, M17Contact1LastName,M17Contact1EmailId)) {
			appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact email is updated with "+M17Contact1EmailId);
		}else {
			appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact email is not updated with "+M17Contact1EmailId);
			sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact email is not updated with "+M17Contact1EmailId);
		}
		lp.CRMlogout();
		sa.assertAll();
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc011_1_deleteFolderAndInviteSameContactFormContactAccess(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String cols="<break>Contact Name<break>E-mail<break>Firm<break>Action<break>Contact Name<break>Granted Access On<break>E-mail<break>Firm<break><break><break>";
		String shrdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String commonPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		boolean flag = false;
		String[] folders = {commonPath,shrdPath};
		if (lp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver,fp.getInvestorWorkSpaceSection(30) , "investor workspace section");
				if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					for (int i=0; i<folders.length; i++) {
						String id=null;
						id=fp.getCreatedFolderId(folders[i], PageName.ManageFolderPopUp, 20);
						System.err.println("id>>>>>>"+id);
						if(id!=null) {
							if(fp.clickOnDeleteFolderButton(id)) {
								if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), folders[i]+"yes button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on Yes button");
									flag =true;
								}else {
									appLog.error("Not able to click on Yes button so cannot delete folder "+folders[i]);
									sa.assertTrue(false, "Not able to click on Yes button so cannot delete folder "+folders[i]);
								}
							}else {
								appLog.error("Not able to click on folder: "+folders[i]+" delete icon so cannot check delete folder "+folders[i]);
								sa.assertTrue(false, "Not able to click on folder: "+folders[i]+" delete icon so cannot check delete folder "+folders[i]);
							}
						}else {
							appLog.error(folders[i]+" is not available in the manage folder structure so cannot click on folder "+folders[i]+" delete icon");
							sa.assertTrue(false, folders[i]+" is not available in the manage folder structure so cannot click on folder "+folders[i]+" delete icon");
						}
					}
					
					if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 20), "close button", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on manage Folder close button");
					}else {
						appLog.error("Not able to click on manage folder close button so cannot close manage folder pop up");
						sa.assertTrue(false, "Not able to click on manage folder close button so cannot close manage folder pop up");
					}
				}else {
					appLog.error("Not able to click on Manage folder icon so cannot delete Common and shared folders ");
					sa.assertTrue(false, "Not able to click on Manage folder icon so cannot delete Common and shared folders ");
				}
				switchToDefaultContent(driver);
				if(fp.inviteContact(environment,mode,M17Institution1, M17Contact1EmailId,null, FolderType.Standard, null, "Yes", null,null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Contact invited "+M17Contact1FirstName+" "+M17Contact1LastName);
					switchToDefaultContent(driver);
					if(contact.deleteCreatedContact(M17Contact1FirstName, M17Contact1LastName)) {
						appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact is deleted");
					}else {
						appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact is not deleted");
						sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact is not deleted");
					}
					if (lp.clickOnTab(TabName.FundsTab)) {
						if (fp.clickOnCreatedFund(M17FundName1)) {
							switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
							scrollDownThroughWebelement(driver,fp.getInvestorWorkSpaceSection(30) , "investor workspace section");
							if (fp.verifyFolderPathdummy("", M17Institution1, "", null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
								appLog.info("clicked on folder "+M17Institution1);
								if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
									String contactDetails= "Remove<break>"+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+"<break>"+getDateAccToTimeZone("America/New_York", "MM/dd/YYYY")+"<break>"+M17Contact1EmailId+"<break>"+M17Contact1UpdatedFirmName;
									SoftAssert res = fp.verifySelectedContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, contactDetails);
									sa.combineAssertions(res);
									switchToDefaultContent(driver);
									if(home.restoreValuesFromRecycleBin(M17Contact1FirstName+" "+M17Contact1LastName)) {
										appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact is restored form Recycle Bin");
									}else {
										appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact is not restored form Recycle Bin");
										sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact is not restored form Recycle Bin");
									}
								}else {
									appLog.error("Not able to click on contact access icon so cannot verify contact details in selected grid "+M17Contact1FirstName+" "+M17Contact1LastName);
									sa.assertTrue(false, "Not able to click on contact access icon so cannot verify contact details in selected grid "+M17Contact1FirstName+" "+M17Contact1LastName);
								}
							}
							else {
								appLog.error("could not click on folder "+M17Institution1);
								sa.assertTrue(false, "could not click on folder "+M17Institution1);
							}
						}else {
							appLog.error("Not able to click on fund name "+M17FundName1);
							sa.assertTrue(false, "Not able to click on fund name "+M17FundName1);
						}
					}else {
						appLog.error("Not able to click on fund tab");
						sa.assertTrue(false, "Not able to click on fund tab");
					}
					if(flag) {
						if (lp.clickOnTab(TabName.FundsTab)) {
							if (fp.clickOnCreatedFund(M17FundName1)) {
								switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
								scrollDownThroughWebelement(driver,fp.getInvestorWorkSpaceSection(30) , "investor workspace section");
								if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
									if(fp.createFolderStructure(folders[0], FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
										appLog.info(folders[0]+ " folder structure is created.");
										ThreadSleep(2000);
										String subCommonPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.KeyWord_For_Search);	
										if(fp.createFolderStructure(subCommonPath, FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
											appLog.info(subCommonPath+ " folder structure is created.");
										} else {
											appLog.error(subCommonPath+ " folder structure is not created.");
											sa.assertTrue(false,subCommonPath+ " folder structure is not created.");
										}
									} else {
										appLog.error(folders[0]+ " folder structure is not created.");
										sa.assertTrue(false,folders[0]+ " folder structure is not created.");
									}
									if(fp.createFolderStructure(folders[1], FolderType.Shared, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
										appLog.info(folders[1]+ " folder structure is created.");
									} else {
										appLog.error(folders[1]+ " folder structure is not created.");
										sa.assertTrue(false,folders[1]+ " folder structure is not created.");
									}
									if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 20), "close button", action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on manage Folder close button");
									}else {
										appLog.error("Not able to click on manage folder close button so cannot close manage folder pop up");
										sa.assertTrue(false, "Not able to click on manage folder close button so cannot close manage folder pop up");
									}
								}else {
									appLog.error("Not able to click on Manage folder icon so cannot delete Common and shared folders ");
									sa.assertTrue(false, "Not able to click on Manage folder icon so cannot delete Common and shared folders ");
								}
							}else {
								appLog.error("could not find "+M17FundName1);
								sa.assertTrue(false, "could not find fund "+M17FundName1);
							}
						}else {
							appLog.error("Not able to click on fund tab");
							sa.assertTrue(false, "Not able to click on fund tab");
						}
						switchToDefaultContent(driver);
						if(contact.clickOnTab(TabName.ContactTab)) {
							if(contact.clickOnCreatedContact(M17Contact1FirstName,M17Contact1LastName, null)) {
								switchToFrame(driver, 30, contact.getFrame( PageName.ContactsPage, 30));
								if(fp.verifyFolderPathdummy(folders[0], null, null, M17FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)){
									appLog.info("Folder structure is verified on Contact page of '"+M17Institution1+"'.");
								} else {
									appLog.error("Folder structure is not verified on Contact page of '"+M17Institution1+"'.");
									sa.assertTrue(false,"Folder structure is not verified on Contact page of '"+M17Institution1+"'.");
								}
							}else {
								appLog.error("Not able to click on contact name "+M17Contact1FirstName+" "+M17Contact1LastName);
								sa.assertTrue(false, "Not able to click on contact name "+M17Contact1FirstName+" "+M17Contact1LastName);
							}
						}else {
							appLog.error("Not able to click on contact tab so cannot verify folder structure");
							sa.assertTrue(false, "Not able to click on contact tab so cannot verify folder structure");
						}
				}
					
				}else {
					appLog.error("Not able to invite Contact "+M17Contact1FirstName+" "+M17Contact1LastName);
					sa.assertTrue(false, "Not able to invite Contact "+M17Contact1FirstName+" "+M17Contact1LastName);
				}
			}else {
				appLog.error("could not find "+M17FundName1);
				sa.assertTrue(false, "could not find fund "+M17FundName1);
			}
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc011_2_verifyFolderStructureAtAtargetSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.PotentialInvestmentPage,
					null, 60)) {
				appLog.info(" Folder Structure Verified: " + cmnPath);

			}
			else {
				appLog.error("could not find folder structure "+cmnPath);
				sa.assertTrue(false, "could not find folder structure "+cmnPath);
			}
			if (fp.verifyFolderPathdummy(stdPath, M17Institution1, null, null, PageName.PotentialInvestmentPage,
					null, 60)) {
				appLog.info(" Folder Structure Verified: " + stdPath);

			}
			else {
				appLog.error("could not find folder structure "+stdPath);
				sa.assertTrue(false, "could not find folder structure "+stdPath);
			}

		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc012_SearchAlreadyInvitedContactandTrytosendinvitationInFR(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		WebElement ele=null;
		String emptyString;
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy("", M17Institution1, "", null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					String[] ss ={FundsPageErrorMessage.alreadyInvitedContactAlertMsg,FundsPageErrorMessage.AllContactSelectErrorMsg};
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info(M17Contact1EmailId+" Contact is selected successfully ");
						if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
							for(int i=0; i<ss.length; i++){
								if(i==0){
									ThreadSleep(2000);
									if(sendKeys(driver,fp.getSearchTextBox(Workspace.FundraisingWorkspace, 60), M17Contact1FirstName+" "+M17Contact1LastName,Workspace.FundraisingWorkspace+" search text box", action.SCROLLANDBOOLEAN)) {
										appLog.info("enter the value in search text box : "+M17Contact1FirstName+" "+M17Contact1LastName);
										if(click(driver, fp.getSearchBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" search button", action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on  "+Workspace.FundraisingWorkspace+" search button");
											ele=fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20);
										}else {
											appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" Contact is not visible so cannot verify error message "+FundsPageErrorMessage.alreadyInvitedContactAlertMsg);
											sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" Contact is not visible so cannot verify error message "+FundsPageErrorMessage.alreadyInvitedContactAlertMsg);
										}
									}else {
										appLog.error("Not able to click on "+Workspace.FundraisingWorkspace+" search button so cannot invite contact: "+M17Contact1FirstName+" "+M17Contact1LastName);
										sa.assertTrue(false, "Not able to click on "+Workspace.FundraisingWorkspace+" search button so cannot invite contact: "+M17Contact1FirstName+" "+M17Contact1LastName);
									}
								}
								if(i==1){
									if (click(driver, fp.getClearIconOnContactAccessSearchBox(Workspace.FundraisingWorkspace, 10), "Clear Icon", action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on Cross Icon");
										emptyString = getValueFromElementUsingJavaScript(driver, fp.getSearchTextBox(Workspace.FundraisingWorkspace, 60), "Search Box");
										
										if(emptyString.isEmpty()) {
											appLog.info("Search Box is Empty");
											
										} else {
											appLog.info("Search Box is not Empty with Value : "+emptyString);
											sa.assertTrue(false, "Search Box is not Empty with Value : "+emptyString);
										}
										
										String[] contacts ={M17Contact1FirstName+" "+M17Contact1LastName,M17Contact2FirstName+" "+M17Contact2LastName};
										
										for (String contact : contacts) {
											ele=fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, contact, 20);
											
											if (ele!=null) {
												appLog.error(contact+" is dispalying after clearing CheckBox");	
											} else {
												appLog.error(contact+" is not dispalying after clearing CheckBox");
												sa.assertTrue(false, contact+" is not dispalying after clearing CheckBox");
											}	
										}
										
										ele=fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, null, 20);
										
										
										
									} else {
										appLog.error("Not Able to Click on Cross Icon");
										sa.assertTrue(false, "Not Able to Click on Cross Icon");
									}
								}
								
								if(ele!=null) {
									ThreadSleep(3000);
									if(!clickUsingJavaScript(driver, ele, "check box")) {
//									if(click(driver, ele, "check box", action.BOOLEAN)) {
										appLog.info("clicked on check box ");
										ThreadSleep(2000);
										if (isAlertPresent(driver)) {
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											if (msg.trim().equals(ss[i])) {
												appLog.info(
														"Error Message is verified in contact access : "+msg);
											} else {
												appLog.error(
														"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+ss[i]);
												sa.assertTrue(false,
														"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+ss[i]);
											}
											if(i==0){
												ele=fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 10);
												if (isSelected(driver, ele, "Check Box")) {
													appLog.error("CheckBox Should be Unchecked after Accepting Alert");
													sa.assertTrue(false, "CheckBox Should be Unchecked after Accepting Alert");
												} else {
													appLog.info("CheckBox is Unchecked after Accepting Alert");
													
												}
											}
											
										} else {
											appLog.error("Alert is not present so cannot verify alert message "+ss[i]);
											sa.assertTrue(false, "Alert is not present so cannot verify alert message "+ss[i]);
										}
									}else {
										appLog.error("Not able to click on Checkbox");
										sa.assertTrue(false, "Not able to click on Checkbox of on Contact ");
									}
									
								}else{
									appLog.error("Checkbox Element is null");
									sa.assertTrue(false, "Checkbox Element is null");
									
								}
							}
						}else {
							appLog.error("Not able to expand contact access grid so cannot check error message : "+ss[0]+" and "+ss[1]);
							sa.assertTrue(false, "Not able to expand contact access grid so cannot check error message : "+ss[0]+" and "+ss[1]);
						}
						
					}else {
						appLog.error("Not able to click on Contact Access Icon so cannot check error message : "+ss[0]+" and "+ss[1]);
						sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check error message : "+ss[0]+" and "+ss[1]);	
					}
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
			}
		}else {
			appLog.error("Not able to click on "+M17Institution1+" folder");
			sa.assertTrue(false, "Not able to click on "+M17Institution1+" folder");
		}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc013_VerifyCheckboxFunctionalityforDownloadPrintDocumentsUnderContactAccesspopup(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if(fp.verifyFolderPathdummy("", M17Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
						ThreadSleep(5000);
						
						// DownLoad / Print Documents
						appLog.info("Going to Checked/Unchecked DownLoad / Print Documents");
						if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Download), "Download / Print Documents CheckBox", action.BOOLEAN)) {
							appLog.info("DownLoad CheckBox Checked");
							if(isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Download), "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is checked after Click on Download / Print Documents CheckBox");
							}else {
								appLog.error("Download Check box Should be checked after Click on Download / Print Documents CheckBox");
								sa.assertTrue(false, "Download Check box Should be checked after Click on Download / Print Documents CheckBox");
							}
							
							if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Download), "Download / Print Documents CheckBox", action.BOOLEAN)) {
								appLog.info("DownLoad CheckBox UnChecked");
								if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Download), "Download / Print Documents CheckBox")) {
									appLog.info("Download Check box is Unchecked after Click on Download / Print Documents CheckBox");
								}else {
									appLog.error("Download Check box Should be Unchecked after Click on Download / Print Documents CheckBox");
									sa.assertTrue(false, "Download Check box Should be Unchecked after Click on Download / Print Documents CheckBox");
								}
								
							} else {
								appLog.error("Not able to click on Download / Print Documents CheckBox");
								sa.assertTrue(false, "Not able to click on Download / Print Documents CheckBox");
							}
							
							
						} else {
							appLog.error("Not able to click on Download / Print Documents CheckBox for Contact : "+M17Contact1EmailId);
							sa.assertTrue(false, "Not able to click on Download / Print Documents CheckBox  for Contact : "+M17Contact1EmailId);
						}
						
						// Upload Documents
						appLog.info("Going to Checked/Unchecked Upload CheckBox");
						if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Upload), "Upload CheckBox", action.BOOLEAN)) {
							appLog.info("Upload CheckBox Checked");
							if(isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Upload), "Upload CheckBox")) {
								appLog.info("Upload Check box is checked after Click on Upload CheckBox");
							}else {
								appLog.error("Upload Check box Should be checked after Click on Upload CheckBox");
								sa.assertTrue(false, "Upload Check box Should be checked after Click on Upload CheckBox");
							}
							
							if(isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Download), "Download CheckBox")) {
								appLog.info("Download Check box is checked after Click on Upload CheckBox");
							}else {
								appLog.error("Download Check box Should be checked after Click on Upload CheckBox");
								sa.assertTrue(false, "Download Check box Should be checked after Click on Upload CheckBox");
							}
							
							
							if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Upload), "Upload CheckBox", action.BOOLEAN)) {
								appLog.info("Upload CheckBox UnChecked");
								if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Upload), "Upload CheckBox")) {
									appLog.info("Upload Check box is Unchecked after Click on Upload CheckBox");
								}else {
									appLog.error("Upload Check box Should be Unchecked after Click on Upload CheckBox");
									sa.assertTrue(false, "Download Check box Should be Unchecked after Click on Upload CheckBox");
								}
								
								if(isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Download), "Upload CheckBox")) {
									appLog.info("Download Check box is checked after Click on Upload CheckBox");
								}else {
									appLog.error("Download Check box Should be checked after Click on Upload CheckBox");
									sa.assertTrue(false, "Download Check box Should be checked after Click on Upload CheckBox");
								}
								
								//
								appLog.info("Going to Checked/Unchecked Upload/Download CheckBox");
								if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Upload), "Upload CheckBox", action.BOOLEAN)) {
									appLog.info("Click on Upload CheckBox for Checked");	
									
									if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Download), "Download / Print Documents CheckBox", action.BOOLEAN)) {
										appLog.info("Click on Download CheckBox for Uncheck");		
										
										if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Upload), "Upload CheckBox")) {
											appLog.info("Upload Check box is Unchecked by clicking on Upload and then Download CheckBox");
										}else {
											appLog.error("Upload Check box Should be Unchecked by clicking on Upload and then Download CheckBox");
											sa.assertTrue(false, "Download Check box Should be Unchecked by clicking on Upload and then Download CheckBox");
										}
										
										if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Download), "Download / Print Documents CheckBox")) {
											appLog.info("Download Check box is Unchecked by clicking on Upload and then Download CheckBox");
										}else {
											appLog.error("Download Check box Should be Unchecked by clicking on Upload and then Download CheckBox");
											sa.assertTrue(false, "Download Check box Should be Unchecked by clicking on Upload and then Download CheckBox");
										}
										
										appLog.info("Going to Provide Download / Print Documents Permission to Contact : "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
										
										if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Download), "Download / Print Documents CheckBox Permission", action.BOOLEAN)) {
											appLog.info("Click on Download CheckBox for permission");	
											
											if(click(driver, fp.getApplyBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" apply button", action.SCROLLANDBOOLEAN)) {
												appLog.info("Clicked on apply button successfully");
												
												if(click(driver, fp.getCloseBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" close button", action.SCROLLANDBOOLEAN)) {
													appLog.info("Clicked on Close Buton");	
												}else {
													appLog.error("Not able to click on close button");
													sa.assertTrue(false, "Not able to click on close button");
												}
												
											
											}else{
												appLog.error("Not able to Click on apply button");
												sa.assertTrue(false, "Not able to Click on apply button")	;
											}
										} else {
											appLog.error("Not able to Click on Download CheckBox for permission");
											sa.assertTrue(false, "Not able to Click on Download CheckBox for permission");
										}
										
									} else {
										appLog.error("Not able to click on Download / Print Documents CheckBox");
										sa.assertTrue(false, "Not able to click on Download / Print Documents CheckBox");
									}
								
								} else {
									appLog.error("Not able to click on Upload CheckBox");
									sa.assertTrue(false, "Not able to click on Upload CheckBox");
								}
								
							} else {
								appLog.error("Not able to click on Upload CheckBox");
								sa.assertTrue(false, "Not able to click on Upload CheckBox ");
							}
							
							
						} else {
							appLog.error("Not able to click on Upload CheckBox for Contact : "+M17Contact1EmailId);
							sa.assertTrue(false, "Not able to click on Upload CheckBox  for Contact : "+M17Contact1EmailId);
						}
						
					}else {
						appLog.error("Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
						sa.assertTrue(false, "Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
					}
					
				}else {
					appLog.error("Not able to click on folder: "+M17Institution1+" so cannot verify contact "+M17Contact1EmailId);
					sa.assertTrue(false, "Not able to click on folder: "+M17Institution1+" so cannot verify contact "+M17Contact1EmailId);
				}
				
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
		}
		switchToDefaultContent(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		if (cp.clickOnTab(TabName.ContactTab)) {
		if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
			switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
			
			scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");
			if (fp.verifyFolderPathdummy(cmnPath, null, null, M17FundName1, PageName.ContactsPage,
					Workspace.FundraisingWorkspace, 60)) {
				if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 10)!=null) {
					appLog.info("successfully found enabled download access icon");
				}
				else {
					appLog.error("download access icon is not enabled");
					sa.assertTrue(false,"download access icon is not enabled");
				}
				if ( cp.getViewAccessIcon(Workspace.FundraisingWorkspace, 10)!=null) {
					appLog.info("successfully found enabled View access icon");
				}
				else {
					appLog.error("View access icon is not enabled");
					sa.assertTrue(false,"View access icon is not enabled");
				}
			}
			else {
				appLog.error("common path is not found on contacts page folder structure");
				sa.assertTrue(false, "common path is not found on contacts page folder structure");
			}
			if (fp.verifyFolderPathdummy("",M17Institution1 , "", M17FundName1, PageName.ContactsPage,
					Workspace.FundraisingWorkspace, 60)) {
				if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 10)!=null) {
					appLog.info("successfully absent enabled download access icon");
				}
				else {
					appLog.error("download access icon is enabled, but it should not be");
					sa.assertTrue(false,"download access icon is enabled, but it should not be");
				}
				if ( cp.getViewAccessIcon(Workspace.FundraisingWorkspace, 10)!=null) {
					appLog.info("successfully found enabled View access icon");
				}
				else {
					appLog.error("View access icon is not enabled");
					sa.assertTrue(false,"View access icon is not enabled");
				}
			}
			else {
				appLog.error(M17Institution1+" folder path on contact page is not found");
				sa.assertTrue(false, M17Institution1+" folder path on contact page is not found");
			}
			switchToDefaultContent(driver);
		}
		else {
			appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
			sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
		}
	}
	else {
		appLog.error("contact tab is not clickable");
		sa.assertTrue(false, "contact tab is not clickable");
	}
	
		lp.CRMlogout();
		sa.assertAll();
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc015_1_updateUploadPermissionForInvitedContactInFR(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if(fp.verifyFolderPathdummy("", M17Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
						ThreadSleep(5000);
						appLog.info("clicked on contact access icon");
						if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Upload), "Upload Documents CheckBox", action.BOOLEAN)) {
							appLog.info("upload CheckBox Checked");
							if(click(driver, fp.getApplyBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" apply button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on apply button successfully");
								if(click(driver, fp.getCloseBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" close button", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on Close Buton");	
									if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
										if(isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Upload), "Upload Documents CheckBox")) {
											appLog.info("Download Check box is checked after Click and apply upload permission");
										}else {
											appLog.error("Download Check box is not checked after Click and apply upload permission");
											sa.assertTrue(false, "Download Check box is not checked after Click and apply upload permission");
										}
									}else {
										appLog.error("Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
										sa.assertTrue(false, "Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
									}

								}else {
									appLog.error("Not able to click on close button");
									sa.assertTrue(false, "Not able to click on close button");
								}
							}else{
								appLog.error("Not able to Click on apply button");
								sa.assertTrue(false, "Not able to Click on apply button")	;
							}
						} else {
							appLog.error("Not able to click on Upload Documents CheckBox for Contact : "+M17Contact1EmailId);
							sa.assertTrue(false, "Not able to click on Upload Documents CheckBox  for Contact : "+M17Contact1EmailId);
						}
					}else {
						appLog.error("Not able to click on contact access icon cannot check upload permission contact "+M17Contact1EmailId);
						sa.assertTrue(false, "Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
					}
				}else {
					appLog.error("Not able to click on folder: "+M17Institution1+" so cannot check upload permission  "+M17Contact1EmailId);
					sa.assertTrue(false, "Not able to click on folder: "+M17Institution1+" so cannot check upload permission "+M17Contact1EmailId);
				}
				
			}else {
				appLog.error("Not able to click on created fund Name so cannot updated upload permission for invited contact "+M17Contact1EmailId);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot updated upload permission for invited contact "+M17Contact1EmailId);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot updated upload permission for invited contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on fund tab so cannot updated upload permission for invited contact "+M17Contact1EmailId);
		}
		switchToDefaultContent(driver);
		if (lp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");
				if (fp.verifyFolderPathdummy(cmnPath, null, null, M17FundName1, PageName.ContactsPage,Workspace.FundraisingWorkspace, 60)) {
					if ( cp.getUploadAccessIcon(Workspace.FundraisingWorkspace, 5)==null) {
						appLog.info("successfully found disabled upload access icon");
					}
					else {
						appLog.error("successfully found disabled upload access icon");
						sa.assertTrue(false,"successfully found disabled upload access icon");
					}
				}else {
					appLog.error("common path is not found on contacts page folder structure");
					sa.assertTrue(false, "common path is not found on contacts page folder structure");
				}
				if (fp.verifyFolderPathdummy("",M17Institution1 , "", M17FundName1, PageName.ContactsPage,Workspace.FundraisingWorkspace, 60)) {
					if ( cp.getUploadAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
						appLog.info("successfully found enabled upload access icon");
					}
					else {
						appLog.error("successfully found enabled upload access icon");
						sa.assertTrue(false,"successfully found enabled upload access icon");
					}
				}
				else {
					appLog.error("folder path on contact page is not found");
					sa.assertTrue(false, "folder path on contact page is not found");
				}
			}else {
				appLog.error("contact not found on contacts page");
				sa.assertTrue(false, "contact not found on contacts page");
			}
		}else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc015_2_verifyUploadpermissionAtTargetSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.PotentialInvestmentPage,
					null, 60)) {
				appLog.info(" Folder Structure Verified: " + cmnPath);
				if(ifp.getUploadIcon(5)==null) {
					appLog.info("Upload button is not visible ");
				}else {
					appLog.error("Upload button is visible");
					sa.assertTrue(false, "Upload button is visible");
				}
			}else {
				appLog.error("could not find folder structure "+cmnPath);
				sa.assertTrue(false, "could not find folder structure "+cmnPath);
			}
			if (fp.verifyFolderPathdummy(stdPath, M17Institution1, null, null, PageName.PotentialInvestmentPage,
					null, 60)) {
				appLog.info(" Folder Structure Verified: " + stdPath);
				if(ifp.getUploadIcon(5)!=null) {
					appLog.info("Upload button is visible ");
				}else {
					appLog.error("Upload button is not visible");
					sa.assertTrue(false, "Upload button is not visible");
				}

			}else {
				appLog.error("could not find folder structure "+stdPath);
				sa.assertTrue(false, "could not find folder structure "+stdPath);
			}

		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc016_TryToProvideAccessFromParentfolderWhenAccessAlreadyProvidedfromFirmAccountFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		WebElement ele=null;
		String alertMsg =FundsPageErrorMessage.alreadyInvitedContactAlertMsg;
		String msg;
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M17Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+M17Institution1+"/"+stdPath);
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
						
						
						ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Download);
						if(isSelected(driver, ele, "Download / Print Documents CheckBox")) {
							appLog.info("Download Check box is checked as Contact is Invited From Parent Folder");
						}else {
							appLog.error("Download Check box Should be checked after Click on Download / Print Documents CheckBox as Contact is Invited From Parent Folder");
							sa.assertTrue(false, "Download Check box Should be checked after Click on Download / Print Documents CheckBox as Contact is Invited From Parent Folder");
						}
						
						if(!isEnabled(driver, ele, "Download Icon")) {
							appLog.info("Download Check box is Disabled as Contact is Invited From Parent Folder");
						}else {
							appLog.error("Download Check box Should be Disabled as Contact is Invited From Parent Folder");
							sa.assertTrue(false, "Download Check box Should be Disabled as Contact is Invited From Parent Folder");
						}
						ele = isDisplayed(driver, FindElement(driver, "//span[@id='grid11_DealDetailBWFR-cell-3-0']/a[text()='"+M17Contact1EmailId+"']/../preceding-sibling::span[contains(text(),'Remove')]", "remove link in front of contact email", action.BOOLEAN, 10), "Visibility", 10, "Remove link");
						if(ele!=null) {
							appLog.info("Remove Link Should be Disabled as Contact is Invited From Parent Folder");
						}else {
							appLog.error("Remove Link is Disabled as Contact is Invited From Parent Folder");
							sa.assertTrue(false, "Remove Link is Disabled as Contact is Invited From Parent Folder");
						}
						
						if (fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
							appLog.info("Click on Expand Icon of Select Contacts");
							
							ele=fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20);
							if(ele!=null) {
								ThreadSleep(3000);
								if(!clickUsingJavaScript(driver, ele, "check box against : "+M17Contact1FirstName+" "+M17Contact1LastName)) {
//								if(click(driver, ele, "check box against : "+M17Contact1FirstName+" "+M17Contact1LastName, action.BOOLEAN)) {
									appLog.info("clicked on check box ");
									ThreadSleep(2000);
									if (isAlertPresent(driver)) {
										 msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if (msg.trim().equals(alertMsg)) {
											appLog.info(
													"Alert Message is verified in contact access : "+msg);
										} else {
											appLog.error(
													"Alert Message is not verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
											sa.assertTrue(false,
													"Alert Message is not verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
										}
									
									} else {
										appLog.error("Alert is not present so cannot verify alert message "+alertMsg);
										sa.assertTrue(false, "Alert is not present so cannot verify alert message "+alertMsg);
									}
								}else {
									appLog.error("Not able to click on Checkbox");
									sa.assertTrue(false, "Not able to click on Checkbox of on Contact ");
								}
								
							}else{
								appLog.error("Checkbox Element is null for "+M17Contact1FirstName+" "+M17Contact1LastName);
								sa.assertTrue(false, "Checkbox Element is null for "+M17Contact1FirstName+" "+M17Contact1LastName);
								
							}
							
							
						}else{
							appLog.error("Not able to click on Expand Icon of Select Contacts");
							sa.assertTrue(false, "Not able to click on Expand Icon of Select Contacts");
						}
						
						ele = FindElement(driver, "//span[@id='grid11_DealDetailBWFR-cell-3-0']/a[text()='"+M17Contact1EmailId+"']/../preceding-sibling::span[contains(text(),'Remove')]/a", "Info Link", action.BOOLEAN, 10);
						if (mouseOverOperation(driver, ele)) {
							appLog.info("Mouse Hover on info Icon");
							msg = ele.getAttribute("title").trim();
							if (msg.equals(FundsPageErrorMessage.toolTipinheritedPermissiononRemoveLink)) {
								appLog.info("Info Link Message is verified  : "+msg);
							} else {
								appLog.error(
										"Info Link Message is not verified in contact access Actual : "+msg+" \t Expected : "+FundsPageErrorMessage.toolTipinheritedPermissiononRemoveLink);
								sa.assertTrue(false,
										"Info Link Message is not verified in contact access Actual : "+msg+" \t Expected : "+FundsPageErrorMessage.toolTipinheritedPermissiononRemoveLink);
							}
							
						} else {
							appLog.error("Not able to perform Mouse Hover on info Icon");
							sa.assertTrue(false, "Not able to perform Mouse Hover on info Icon");
						}
						
						if (click(driver, fp.getCrossIconOnContactAccess(Workspace.FundraisingWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on Cross Icon");	
						} else {
							appLog.error("Not able to click on Cross Icon");
							sa.assertTrue(false, "Not able to click on Cross Icon");	
						}
						
					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
			}else {
				appLog.error("Folder Structure Not Verified  "+M17Institution1+"/"+stdPath);
				sa.assertTrue(false, "Folder Structure Not Verified  "+M17Institution1+"/"+stdPath);
			}
				
				if (fp.verifyFolderPathdummy("", M17Institution1, "", null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+M17Institution1);
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
					WebElement remove = isDisplayed(driver, FindElement(driver, "//a[text()='"+M17Contact1EmailId+"']/../../span/a[@title='Remove']", "remove link in front of contact email", action.BOOLEAN, 60), "Visibility", 60, "Remove link");
					if (remove!=null) {
						if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on remove link");
							if (click(driver, fp.getRemoveAccessClose(Workspace.FundraisingWorkspace, 30), "remove access close button", action.BOOLEAN)) {
								appLog.info("Clicked on remove access close button");
								
								if (click(driver, fp.getCrossIconOnContactAccess(Workspace.FundraisingWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on Cross Icon on Contact Access");	
								} else {
									appLog.error("clicked on Cross Icon on Contact Access");
									sa.assertTrue(false, "clicked on Cross Icon on Contact Access");	
								}
								
							}
							else {
								appLog.error("remove access close button is not clickable");
								sa.assertTrue(false, "remove access close button is not clickable");
							}
						}else {
							appLog.error("Not able to click on remove link of contact: "+M17Contact1EmailId);
							sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1EmailId);
						}
					}
					else {
						appLog.error("remove button adjacent to "+M17Contact1EmailId+" is not found");
						sa.assertTrue(false, "remove button adjacent to "+M17Contact1EmailId+" is not found");
					}
				}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
				
				}else {
					appLog.error("Folder Structure Not Verified  "+M17Institution1);
					sa.assertTrue(false, "Folder Structure Not Verified  "+M17Institution1);
				}	
				
		}else {
			appLog.error("Not able to click on created fund Name : "+M17FundName1);
			sa.assertTrue(false, "Not able to click on created fund Name : "+M17FundName1);
		
		}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc017_inviteContactAndCreateSubFolderCheckPermission(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath).split(",");
		String errormsg=FundsPageErrorMessage.alreadyInvitedContactAlertMsg;
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				if(fp.inviteContact(environment,mode,M17Institution1, M17Contact1EmailId,stdPath[0], FolderType.Standard, "Upload", "Yes", null,null, Workspace.FundraisingWorkspace, null)) {
					appLog.info(M17Contact1EmailId+" Contact is selected successfully ");
					ThreadSleep(2000);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
						String contactDetails= "Remove<break>"+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+"<break>"+getDateAccToTimeZone("America/New_York", "MM/dd/YYYY")+"<break>"+M17Contact1EmailId+"<break>"+M17Contact1UpdatedFirmName;
						SoftAssert result = fp.verifySelectedContactsGridDataInContactAccess(Workspace.FundraisingWorkspace,contactDetails);
						sa.combineAssertions(result);
						if(click(driver, fp.getContactAccessCancelBtn(Workspace.FundraisingWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("click on contact access pop up cancel button");
						}else {
							appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
							sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
						}
					}else {
						appLog.error("Not able to click on contact access icon so cannot verify select contact "+M17Contact1FirstName+" "+M17Contact1LastName);
						sa.assertTrue(false, "Not able to click on contact access icon so cannot verify select contact "+M17Contact1FirstName+" "+M17Contact1LastName);
					}
					if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "Manage folder icon", action.SCROLLANDBOOLEAN)){
						if(fp.createFolderStructure(stdPath[1], FolderType.Standard, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(stdPath[0]+ " folder structure is created.");
							if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 20), "close button", action.SCROLLANDBOOLEAN)) {
								if(fp.verifyFolderPathdummy(stdPath[1], M17Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
									if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
										if(fp.getcontactaccessremoveLink(Workspace.FundraisingWorkspace, EnableDisable.Disable,M17Contact1EmailId, 10)!=null) {
											appLog.info("Invited Contact "+M17Contact1FirstName+" "+M17Contact1LastName+" is displaying in disabled mode ");
										}else {
											appLog.error("Invited Contact "+M17Contact1FirstName+" "+M17Contact1LastName+" is not displaying in disabled mode ");
											sa.assertTrue(false, "Invited Contact "+M17Contact1FirstName+" "+M17Contact1LastName+" is not displaying in disabled mode ");
										}
										if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
											ThreadSleep(3000);
											if(!clickUsingJavaScript(driver, fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20), "Check Box")) {
//											if(click(driver, fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20), "Check Box", action.SCROLLANDBOOLEAN)) {
												ThreadSleep(2000);
												if (isAlertPresent(driver)) {
													String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
													switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
													if (msg.trim().equals(errormsg)) {
														appLog.info(
																"Error Message is verified in contact access : "+errormsg);
													} else {
														appLog.error(
																"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+errormsg);
														sa.assertTrue(false,
																"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+errormsg);
													}
													
												} else {
													appLog.error("Alert is not present so cannot verify alert message "+errormsg);
													sa.assertTrue(false, "Alert is not present so cannot verify alert message "+errormsg);
												}
												if(click(driver, fp.getContactAccessCancelBtn(Workspace.FundraisingWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
													appLog.info("click on contact access pop up cancel button");
												}else {
													appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
													sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
												}
											}else {
												appLog.error("Not able to click on Contact Name "+M17Contact1FirstName+" "+M17Contact1LastName+" so cannot check error message "+errormsg);
												sa.assertTrue(false, "Not able to click on Contact Name "+M17Contact1FirstName+" "+M17Contact1LastName+" so cannot check error message "+errormsg);
											}
											
										}else {
											appLog.error("Not able to expand contact access pop up so cannot verify error message "+errormsg);
											sa.assertTrue(false, "Not able to expand contact access pop up so cannot verify error message "+errormsg);
										}
										
										
										
									}else {
										appLog.error("Not able to click on contact access icon so cannot verify inherit contact access permission");
										sa.assertTrue(false, "Not able to click on contact access icon so cannot verify inherit contact access permission");
									}
									
								}else {
									appLog.error("Not able to click on folder structure "+stdPath[1]+" so cannot check inherit contact access permission on sub folder");
									sa.assertTrue(false, "Not able to click on folder structure "+stdPath[1]+" so cannot check inherit contact access permission on sub folder");
								}
								
							}else {
								appLog.error("Not able to click on manage folder close button");
								sa.assertTrue(false, "Not able to click on manage folder close button");
							}
							
						} else {
							appLog.error(stdPath[1]+ " folder structure is not created.");
							sa.assertTrue(false,stdPath[1]+ " folder structure is not created.");
						}
					}else {
						appLog.error("Not able to click on manage folder icon so cannot create sub folder");
						sa.assertTrue(false, "Not able to click on manage folder icon so cannot create sub folder");
					}
					if(fp.verifyFolderPathdummy(stdPath[2], M17Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
						if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
							if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.FundraisingWorkspace, 60), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
								appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
								if(click(driver, fp.getContactAccessCancelBtn(Workspace.FundraisingWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
									appLog.info("click on contact access pop up cancel button");
								}else {
									appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
									sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
								}
							}else {
								appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
								sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
							}
						}else {
							appLog.error("Not able to click on contact access icon so cannot check error message ");
							sa.assertTrue(false, "Not able to click on contact access icon so cannot check error message ");
						}
					}else {
						appLog.error("Not able to click on folder structure "+stdPath[2]+" so cannot check error message ");
						sa.assertTrue(false, "Not able to click on folder structure "+stdPath[2]+" so cannot check error message ");
					}
					switchToDefaultContent(driver);
					if(fp.inviteContact(environment,mode,M17Institution1, M17Contact1EmailId,stdPath[2], FolderType.Standard, null, "Yes", null,null, Workspace.FundraisingWorkspace, null)) {
						appLog.info("Contact is invited "+M17Contact1FirstName+" "+M17Contact1LastName);
					}else {
						appLog.error("Not able to invite contact from folder "+stdPath[2]);
						sa.assertTrue(false, "Not able to invite contact from folder "+stdPath[2]);
					}
					
				}else {
					appLog.error("Not able to select Contact from contact access grid "+M17Contact1EmailId);
					sa.assertTrue(false, "Not able to select Contact from contact access grid "+M17Contact1EmailId);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc018_1_VerifyAccessFromAddedChildFolderCRMSide(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		WebElement ele=null;
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String stdPaths = cmnPath+","+ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String[] allFolders=stdPaths.split(",");
		
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(allFolders[2], M17Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+M17Institution1+"/"+allFolders[2]);
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
						
						String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
						
						appLog.info("Going to Verify "+contactFullName+" with Remove Disabled Link");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, EnableDisable.Disable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is Verified");
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace,EnableDisable.Disable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked as Contact is Invited From Parent Folder");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, EnableDisable.Disable, contactFullName, 5, CheckBoxName.Download);
							if(!isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is Unchecked");
							}else {
								appLog.error("Download Check box Should be Unchecked");
								sa.assertTrue(false, "Download Check box Should be Unchecked");
							}
							
							if(!isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Disabled");
							}else {
								appLog.error("Download Check box Should be Disabled");
								sa.assertTrue(false, "Download Check box Should be Disabled");
							}
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace,EnableDisable.Disable, contactFullName, 5, CheckBoxName.Upload);
							if(!isSelected(driver, ele, "Upload CheckBox")) {
								appLog.info("Upload Check box is Unchecked");
							}else {
								appLog.error("Upload Check box Should be Unchecked");
								sa.assertTrue(false, "Upload Check box Should be Unchecked");
							}
							
							if(!isEnabled(driver, ele, "Upload Icon")) {
								appLog.info("Upload Check box is Disabled");
							}else {
								appLog.error("Upload Check box Should be Disabled");
								sa.assertTrue(false, "Upload Check box Should be Disabled");
							}
							
							
						} else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is not Verified");
					
						}
						appLog.info("Going to Verify "+contactFullName+" with Remove Enabled Link");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Enable is Verified");
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked as Contact is Invited From Parent Folder");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
							if(isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is checked");
							}else {
								appLog.error("Download Check box Should be checked");
								sa.assertTrue(false, "Download Check box Should be checked");
							}
							
							if(isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Enabled");
							}else {
								appLog.error("Download Check box Should be Enabled");
								sa.assertTrue(false, "Download Check box Should be Enabled");
							}
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.Upload);
							if(isSelected(driver, ele, "Upload CheckBox")) {
								appLog.info("Upload Check box is checked");
							}else {
								appLog.error("Upload Check box Should be checked");
								sa.assertTrue(false, "Upload Check box Should be checked");
							}
							
							if(isEnabled(driver, ele, "Upload Icon")) {
								appLog.info("Upload Check box is Enabled");
							}else {
								appLog.error("Upload Check box Should be Enabled");
								sa.assertTrue(false, "Upload Check box Should be Enabled");
							}
							
							
						} else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
					
						}
						
						if (click(driver, fp.getCrossIconOnContactAccess(Workspace.FundraisingWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on Cross Icon");	
						} else {
							appLog.error("Not able to click on Cross Icon");
							sa.assertTrue(false, "Not able to click on Cross Icon");	
						}
						
					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
			}else {
				appLog.error("Folder Structure Not Verified  "+M17Institution1+"/"+allFolders[2]);
				sa.assertTrue(false, "Folder Structure Not Verified  "+M17Institution1+"/"+allFolders[2]);
			}
				
				
		}else {
			appLog.error("Not able to click on created fund Name : "+M17FundName1);
			sa.assertTrue(false, "Not able to click on created fund Name : "+M17FundName1);
		
		}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		switchToDefaultContent(driver);
		
	
		if (cp.clickOnTab(TabName.ContactTab)) {
		if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
			switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
			
			scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");
			
			for (int i=0;i<allFolders.length;i++) {
				
				if (fp.verifyFolderPathdummy(allFolders[i], null, null, M17FundName1, PageName.ContactsPage,
						Workspace.FundraisingWorkspace, 60)) {
					
					if ( cp.getViewAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
						appLog.info("View access icon is Enabled for : "+allFolders[i]);
					}
					else {
						appLog.error("View access icon is Should be Enabled for : "+allFolders[i]);
						sa.assertTrue(false,"View access icon is Should be Enabled for : "+allFolders[i]);
					}
					
					
					if (i==0 || i==2 || i==3) {
						
						if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
							appLog.info("Download access icon is Enabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Download access icon is Should be Enabled for : "+allFolders[i]);
							sa.assertTrue(false,"Download access icon is Should be Enabled for : "+allFolders[i]);
						}
						
					} else {
						
						if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 5)==null) {
							appLog.info("Download access icon is Disabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Download access icon is Should be Disabled for : "+allFolders[i]);
							sa.assertTrue(false,"Download access icon is Should be Disabled for : "+allFolders[i]);
						}
					}
					
					
					if (i==2 || i==3) {
						
						if ( cp.getUploadAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
							appLog.info("Upload access icon is Enabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Upload access icon is Should be Enabled for : "+allFolders[i]);
							sa.assertTrue(false,"Upload access icon is Should be Enabled for : "+allFolders[i]);
						}
						
					} else {
						
						if ( cp.getUploadAccessIcon(Workspace.FundraisingWorkspace, 5)==null) {
							appLog.info("Upload access icon is Disabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Upload access icon is Should be Disabled for : "+allFolders[i]);
							sa.assertTrue(false,"Upload access icon is Should be Disabled for : "+allFolders[i]);
						}
						
					}
					
					
				}
				else {
					appLog.error("common path is not found on contacts page folder structure : "+allFolders[i]);
					sa.assertTrue(false, "common path is not found on contacts page folder structure : "+allFolders[i]);
				}
				
			}
			
		}
		else {
			appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
			sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
		}
	}
	else {
		appLog.error("contact tab is not clickable");
		sa.assertTrue(false, "contact tab is not clickable");
	}

	switchToDefaultContent(driver);

	lp.CRMlogout();
	sa.assertAll();
	
}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc018_2_VerifyAccessFromAddedChildFolderInvestoride() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String stdPaths = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String[] stdPath=stdPaths.split(",");
		String parentID;
		String alertMsg=InvestorFirmPageErrorMessage.LimitedAccessErrorMessageBulkDownload;
		/*String[] confirmationMsg={InvestorFirmPageErrorMessage.BulkDownloadConfirmationMessage1,InvestorFirmPageErrorMessage.BulkDownloadConfirmationMessage2,
				InvestorFirmPageErrorMessage.BulkDownloadConfirmationMessage3,InvestorFirmPageErrorMessage.BulkDownloadConfirmationMessage4,
				InvestorFirmPageErrorMessage.BulkDownloadConfirmationMessage5};
	*/	
		String msg ;
		WebElement ele;
		
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			
			if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.PotentialInvestmentPage,null, 20)) {
				appLog.info(" Folder Structure Verified: " + cmnPath);
			}else {
				appLog.error("could not find folder structure "+cmnPath);
				sa.assertTrue(false, "could not find folder structure "+cmnPath);
			}
			
			for (int i = 0; i < stdPath.length; i++) {
				
				if (fp.verifyFolderPathdummy(stdPath[i], null, null, null, PageName.PotentialInvestmentPage,
						null, 20)) {
					appLog.info(" Folder Structure Verified: " + stdPath[i]);
					

				}else {
					appLog.error("could not find folder structure "+stdPath[i]);
					sa.assertTrue(false, "could not find folder structure "+stdPath[i]);
				}
				
			}
			
			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");
				
				parentID=switchOnWindow(driver);
				
				if (parentID!=null) {
					if (ifp.verifyBulkDownLoadFolderStructure(cmnPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + cmnPath);
					}else {
						appLog.error("could not find folder structure on Bulk DownLoad: "+cmnPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+cmnPath);
					}

					if (ifp.verifyBulkDownLoadFolderStructure(stdPath[2], 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + stdPath[2]);
					}else {
						appLog.error("could not find folder structure on Bulk DownLoad"+stdPath[2]);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+stdPath[2]);
					}

					if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(stdPath[0], 10)) {
						appLog.info("Clicked on CheckBox for Folder: "+stdPath[0]);	
						ThreadSleep(2000);
						if (isAlertPresent(driver)) {
							 msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
							switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
							if (msg.trim().contains(alertMsg)) {
								appLog.info("Error Message is verified in contact access : "+alertMsg);
							} else {
								appLog.error("Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
								sa.assertTrue(false,"Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
							}
						} else {
							appLog.error("Alert is not present so cannot verify alert message "+alertMsg);
							sa.assertTrue(false, "Alert is not present so cannot verify alert message "+alertMsg);
						}
						} else {
						sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+stdPath[0]);
						appLog.error("Not Able to Click on CheckBox for Folder: "+stdPath[0]);
					}	

					if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(stdPath[1], 10)) {
						appLog.info("Clicked on CheckBox for Folder: "+stdPath[1]);	
						if (click(driver, ifp.getBulkDownloadDownloadButton(10), "DownLoad Button", action.BOOLEAN)) {
							
						/*	WebElement[] elements = {ifp.getbulkDownloadConfirmationMessage1(10),ifp.getbulkDownloadConfirmationMessage2(10),
									ifp.getbulkDownloadConfirmationMessage3(10),ifp.getbulkDownloadConfirmationMessage4(10),ifp.getbulkDownloadConfirmationMessage5(10)};
							*/
							
							if (click(driver, ifp.getBulkDownloadConfirmationPopUpYesBtn(30),
									"Bulk Download Yes Button", action.BOOLEAN)) {
								appLog.error("Clicked on Yes Button.");
								
								
								Robot rob;
								try {
									rob = new Robot();
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
								} catch (AWTException e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
								ThreadSleep(20000);
								driver.close();
								driver.switchTo().window(parentID);
								// ele.sendKeys(Keys.CONTROL+"j");
								parentID = switchOnWindow(driver);
								if (parentID == null) {
									try {
										rob = new Robot();
										rob.keyPress(KeyEvent.VK_TAB);
										rob.keyRelease(KeyEvent.VK_TAB);
										rob.keyPress(KeyEvent.VK_ENTER);
										rob.keyRelease(KeyEvent.VK_ENTER);
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_CONTROL);
										rob.keyPress(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_CONTROL);
										parentID = switchOnWindow(driver);
									} catch (AWTException e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}
								}
							
								ThreadSleep(5000);
								String fileName = ifp.getDownloadedFileName();
								if (fileName.contains(M17FundName1 + " " + getSystemDate("MM-dd-YYYY"))) {
									appLog.info("file name is successfully matched. " + fileName);
								} else {
									appLog.info("File name is not the matched. Expected: " + M17FundName1 + " "
											+ getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
									sa.assertTrue(false, "File name is not the matched. Expected: " + M17FundName1
											+ " " + getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
								}
								driver.close();
								driver.switchTo().window(parentID);
								
							} else {
								appLog.error("Not Able to Click on Yes Button.");
								sa.assertTrue(false, "Not Able to Click on Yes Button.");
							}
							
							
						} else {
							appLog.error("Download Button is not working.");
							sa.assertTrue(false, "Download Button is not working.");
						}
					
					
					} else {
						appLog.error("Not Able to Click on CheckBox for Folder: "+stdPath[1]);
						sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+stdPath[1]);
					
					}	
					
					
				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}
				
				
			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}
			
		
		}else{
			appLog.error("Not Able to Click on "+investorSideWorkSpace.PotentialInvestment);
			sa.assertTrue(false, "Not Able to Click on "+investorSideWorkSpace.PotentialInvestment);	
		}
		
		
		
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc019_1_RemoveTheAccessFromInvitedParentFolderActionCRMSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String stdPaths = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String[] stdFolder=stdPaths.split(",");
		String allFolders = cmnPath+","+stdFolder[3]+","+stdFolder[4];
		String[] allFolder = allFolders.split(",");
		WebElement popUpEle;
		WebElement ele;
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
		if (cp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "workspace section");
				if (fp.verifyFolderPathdummy(stdFolder[0], M17Institution1, null, null, PageName.FundsPage,Workspace.FundraisingWorkspace,60)) {
					appLog.error("folder path verified : "+M17Institution1+"/"+stdFolder[0]);
					if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
							WebElement remove = isDisplayed(driver, FindElement(driver, "//a[text()='"+M17Contact1EmailId+"']/../../span/a[@title='Remove']", "remove link in front "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName, action.BOOLEAN, 60), "Visibility", 60, "Remove link");
							if (remove!=null) {
								if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on remove link for : "+contactFullName);
									ThreadSleep(5000);
									if (click(driver, fp.getRemoveAccessClose(Workspace.FundraisingWorkspace, 30), "remove access close button", action.BOOLEAN)) {
										appLog.error("Clicked on remove access close button");	
										
										popUpEle = fp.getContactAccessRemovedPopUpMsg(Workspace.FundraisingWorkspace, 5);
										if (popUpEle==null) {
											appLog.info("Access Remove PopUp is closed after click on Closed Button");
										}else{
											appLog.error("Access Remove PopUp is not closed after click on Closed Button");
											sa.assertTrue(false, "Access Remove PopUp is not closed after click on Closed Button");	
										}
										
										if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.FundraisingWorkspace, 60), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
											appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
										}else {
											appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
											sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
										}
										
										if (click(driver, fp.getContactAccessCrossIcon(Workspace.FundraisingWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on Contact access Cross Icon");
										} else {
											appLog.error("Contact access Cross Icon is not clickable");
											sa.assertTrue(false, "Contact access close button is not clickable");
										}
										
										popUpEle = fp.getContactAccessCrossIcon(Workspace.FundraisingWorkspace, 5);
										if (popUpEle==null) {
											appLog.info("Select Contact Access PopUp is closed after click on Cross Icon");
										}else{
											appLog.error("Select Contact Access PopUp is not closed after click on Cross Icon");
											sa.assertTrue(false, "Select Contact Access PopUp is not closed after click on Cross Icon");	
										}
									}
									else {
										appLog.error("remove access close button is not clickable");
										sa.assertTrue(false, "remove access close button is not clickable");
									}
								
								}else {
									appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
									sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
								}
							}
							else {
								appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
								sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
							}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}
				}
				else {
					appLog.error("folder path is not found : "+M17Institution1+"/"+stdFolder[0]);
					sa.assertTrue(false, "folder path is not found : "+M17Institution1+"/"+stdFolder[0]);
				}
				
				
				// Verify SubFolder after Removing Access From Parent Folder
				
				if (fp.verifyFolderPathdummy(stdFolder[1], M17Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+M17Institution1+"/"+stdFolder[1]);
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
						
						
						appLog.info("Going to Verify "+contactFullName+" with Remove Disabled Link");
						if (!fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, EnableDisable.Disable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.info("Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is not displaying and Hence Verified");
						
						} else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is displaying and Hence Not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is displaying and Hence Not Verified");
					
						}
						appLog.info("Going to Verify "+contactFullName+" with Remove Enabled Link");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Enable is Verified");
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked as Contact is Invited From Parent Folder");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
							if(isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is checked");
							}else {
								appLog.error("Download Check box Should be checked");
								sa.assertTrue(false, "Download Check box Should be checked");
							}
							
							if(isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Enabled");
							}else {
								appLog.error("Download Check box Should be Enabled");
								sa.assertTrue(false, "Download Check box Should be Enabled");
							}
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.Upload);
							if(isSelected(driver, ele, "Upload CheckBox")) {
								appLog.info("Upload Check box is checked");
							}else {
								appLog.error("Upload Check box Should be checked");
								sa.assertTrue(false, "Upload Check box Should be checked");
							}
							
							if(isEnabled(driver, ele, "Upload Icon")) {
								appLog.info("Upload Check box is Enabled");
							}else {
								appLog.error("Upload Check box Should be Enabled");
								sa.assertTrue(false, "Upload Check box Should be Enabled");
							}
							
							
						} else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
					
						}
						
						if (click(driver, fp.getCrossIconOnContactAccess(Workspace.FundraisingWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on Cross Icon");	
						} else {
							appLog.error("Not able to click on Cross Icon");
							sa.assertTrue(false, "Not able to click on Cross Icon");	
						}
						
					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
			}else {
				appLog.error("Folder Structure Not Verified  "+M17Institution1+"/"+stdFolder[1]);
				sa.assertTrue(false, "Folder Structure Not Verified  "+M17Institution1+"/"+stdFolder[1]);
			}
			}
			else {
				appLog.error(M17FundName1+" is not found on funds page");
				sa.assertTrue(false, M17FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");
				
				for (int i=0;i<allFolder.length;i++) {
					
					if (fp.verifyFolderPathdummy(allFolder[i], null, null, M17FundName1, PageName.ContactsPage,
							Workspace.FundraisingWorkspace, 60)) {
						
						if ( cp.getViewAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
							appLog.info("View access icon is Enabled for : "+allFolder[i]);
						}
						else {
							appLog.error("View access icon is Should be Enabled for : "+allFolder[i]);
							sa.assertTrue(false,"View access icon is Should be Enabled for : "+allFolder[i]);
						}
						
						if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
							appLog.info("Download access icon is Enabled for : "+allFolder[i]);
						}
						else {
							appLog.error("Download access icon is Should be Enabled for : "+allFolder[i]);
							sa.assertTrue(false,"Download access icon is Should be Enabled for : "+allFolder[i]);
						}

						if (i==1 || i==2) {
							
							if ( cp.getUploadAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
								appLog.info("Upload access icon is Enabled for : "+allFolder[i]);
							}
							else {
								appLog.error("Upload access icon is Should be Enabled for : "+allFolder[i]);
								sa.assertTrue(false,"Upload access icon is Should be Enabled for : "+allFolder[i]);
							}
							
						} else {
							
							if ( cp.getUploadAccessIcon(Workspace.FundraisingWorkspace, 5)==null) {
								appLog.info("Upload access icon is Disabled for : "+allFolder[i]);
							}
							else {
								appLog.error("Upload access icon is Should be Disabled for : "+allFolder[i]);
								sa.assertTrue(false,"Upload access icon is Should be Disabled for : "+allFolder[i]);
							}
							
						}
					}
					else {
						appLog.error("common path is not found on contacts page folder structure : "+allFolder[i]);
						sa.assertTrue(false, "common path is not found on contacts page folder structure : "+allFolder[i]);
					}
				}
			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc019_2_RemoveTheAccessFromInvitedParentFolderImpcatInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String dependentTC="M17tc019_1_RemoveTheAccessFromInvitedParentFolderActionCRMSide";
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependentTC,excelLabel.CommonPath);
		String stdPaths = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependentTC, excelLabel.StandardPath);
		String[] stdPath=stdPaths.split(",");
		String AllFolders=cmnPath+","+stdPath[3]+","+stdPath[4];
		String[] allFolder=AllFolders.split(",");
		String parentID;
		
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			
			for (int i = 0; i < allFolder.length; i++) {
				
				if (fp.verifyFolderPathdummy(allFolder[i], null, null, null, PageName.PotentialInvestmentPage,
						null, 20)) {
					appLog.info(" Folder Structure Verified: " + allFolder[i]);
					}else {
					appLog.error("could not find folder structure "+allFolder[i]);
					sa.assertTrue(false, "could not find folder structure "+allFolder[i]);
				}
				
			}
			
			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");
				
				parentID=switchOnWindow(driver);
				
				if (parentID!=null) {
					if (ifp.verifyBulkDownLoadFolderStructure(cmnPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + cmnPath);
					}else {
						appLog.error("could not find folder structure on Bulk DownLoad: "+cmnPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+cmnPath);
					}

					if (ifp.verifyBulkDownLoadFolderStructure(stdPath[4], 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + stdPath[2]);
					}else {
						appLog.error("could not find folder structure on Bulk DownLoad"+stdPath[2]);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+stdPath[2]);
					}
	

					if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(stdPath[3], 10)) {
						appLog.info("Clicked on CheckBox for Folder: "+stdPath[3]);	
						if (click(driver, ifp.getBulkDownloadDownloadButton(10), "DownLoad Button", action.BOOLEAN)) {
						
							
							if (click(driver, ifp.getBulkDownloadConfirmationPopUpYesBtn(30),
									"Bulk Download Yes Button", action.BOOLEAN)) {
								appLog.error("Clicked on Yes Button.");
								
								
								Robot rob;
								try {
									rob = new Robot();
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
								} catch (AWTException e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
								ThreadSleep(20000);
								driver.close();
								driver.switchTo().window(parentID);
								// ele.sendKeys(Keys.CONTROL+"j");
								parentID = switchOnWindow(driver);
								if (parentID == null) {
									try {
										rob = new Robot();
										rob.keyPress(KeyEvent.VK_TAB);
										rob.keyRelease(KeyEvent.VK_TAB);
										rob.keyPress(KeyEvent.VK_ENTER);
										rob.keyRelease(KeyEvent.VK_ENTER);
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_CONTROL);
										rob.keyPress(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_CONTROL);
										parentID = switchOnWindow(driver);
									} catch (AWTException e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}
								}
							
								ThreadSleep(5000);
								String fileName = ifp.getDownloadedFileName();
								if (fileName.contains(M17FundName1 + " " + getSystemDate("MM-dd-YYYY"))) {
									appLog.info("file name is successfully matched. " + fileName);
								} else {
									appLog.info("File name is not the matched. Expected: " + M17FundName1 + " "
											+ getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
									sa.assertTrue(false, "File name is not the matched. Expected: " + M17FundName1
											+ " " + getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
								}
								driver.close();
								driver.switchTo().window(parentID);
								
							} else {
								appLog.error("Not Able to Click on Yes Button.");
								sa.assertTrue(false, "Not Able to Click on Yes Button.");
							}
							
							
						} else {
							appLog.error("Download Button is not working.");
							sa.assertTrue(false, "Download Button is not working.");
						}
					
					
					} else {
						appLog.error("Not Able to Click on CheckBox for Folder: "+stdPath[3]);
						sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+stdPath[3]);
					
					}	
					
					
				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}
				
				
			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}
			
		
		}else{
			appLog.error("Not Able to Click on "+investorSideWorkSpace.PotentialInvestment);
			sa.assertTrue(false, "Not Able to Click on "+investorSideWorkSpace.PotentialInvestment);	
		}
		
		lp.investorLogout();
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc020_1_InviteContactFromParentAndRemoveAccessFromChild(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String[] stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath).split(",");
		String commonPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String folder=commonPath+","+ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		String[] allFolders=folder.split(",");
		String errormsg=FundsPageErrorMessage.alreadyInvitedContactAlertMsg;
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				if(fp.inviteContact(environment,mode,M17Institution1, M17Contact1EmailId,stdPath[0], FolderType.Standard, null, "Yes", null,null, Workspace.FundraisingWorkspace, null)) {
					appLog.info(M17Contact1EmailId+" Contact is selected successfully ");
					ThreadSleep(2000);
					
				}else {
					appLog.error("Not able to invite contact from parent folder "+stdPath[0]);
					sa.assertTrue(false, "Not able to invite contact from parent folder "+stdPath[0]);
				}
				
			}else {
				appLog.error("Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
				sa.assertTrue(false, "Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
		}
		switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
		scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "workspace section");
		if (fp.verifyFolderPathdummy(stdPath[1], M17Institution1, null, null, PageName.FundsPage,Workspace.FundraisingWorkspace,60)) {
			if(fp.revokeContactAccess(M17Contact1EmailId, Workspace.FundraisingWorkspace)) {
				
			}else {
				appLog.error("Not able to remove contact access "+M17Contact1EmailId+" form "+stdPath[1]);
				sa.assertTrue(false, "Not able to remove contact access "+M17Contact1EmailId+" form "+stdPath[1]);
			}
			if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
				appLog.info(M17Contact1EmailId+" Contact is selected successfully ");
				if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
					WebElement ele=fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20);
					if(ele!=null) {
						ThreadSleep(3000);
						if(!clickUsingJavaScript(driver, ele, "check box")) {
//						if(click(driver, ele, "check box", action.BOOLEAN)) {
							appLog.info("clicked on check box ");
							ThreadSleep(2000);
							if (isAlertPresent(driver)) {
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								if (msg.trim().equals(errormsg)) {
									appLog.info(
											"Error Message is verified in contact access : "+msg);
								} else {
									appLog.error(
											"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+errormsg);
									sa.assertTrue(false,
											"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+errormsg);
								}
							} else {
								appLog.error("Alert is not present so cannot verify alert message "+errormsg);
								sa.assertTrue(false, "Alert is not present so cannot verify alert message "+errormsg);
							}
						}else {
							appLog.error("Not able to click on Checkbox");
							sa.assertTrue(false, "Not able to click on Checkbox of on Contact ");
						}
						
					}else{
						appLog.error("Checkbox Element is null");
						sa.assertTrue(false, "Checkbox Element is null");
						
					}
					if(click(driver, fp.getCancelBtn(Workspace.FundraisingWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
						appLog.error("Clicked on close button");
					}else {
						appLog.error("Not able to click on cancel button so cannot close contact access popup");
						sa.assertTrue(false, "Not able to click on cancel button so cannot close contact access popup");
					}
					
				}else {
					appLog.error("Not able to expand contact acces pop up so cannot check error message "+errormsg);
					sa.assertTrue(false, "Not able to expand contact acces pop up so cannot check error message "+errormsg);
				}
				
			}else {
				appLog.error("Not able to click on contact access icon so cannot check error message");
				sa.assertTrue(false, "Not able to click on contact access icon so cannot check error message");
			}
			
		}else {
			appLog.error("Not able to click on folder path "+stdPath[1]);
			sa.assertTrue(false, "Not able to click on folder path "+stdPath[1]);
		}
		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
		if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
			switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
			scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");
			for (int i=0;i<allFolders.length;i++) {
				
				if (fp.verifyFolderPathdummy(allFolders[i], null, null, M17FundName1, PageName.ContactsPage,
						Workspace.FundraisingWorkspace, 60)) {
					
					if ( cp.getViewAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
						appLog.info("View access icon is Enabled for : "+allFolders[i]);
					}
					else {
						appLog.error("View access icon is Should be Enabled for : "+allFolders[i]);
						sa.assertTrue(false,"View access icon is Should be Enabled for : "+allFolders[i]);
					}
					if(i==0) {
						if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
							appLog.info("Download access icon is Enabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Download access icon is Should be Enabled for : "+allFolders[i]);
							sa.assertTrue(false,"Download access icon is Should be Enabled for : "+allFolders[i]);
						}
					}else {
						if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 5)==null) {
							appLog.info("Download access icon is Disabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Download access icon is Should be Disabled for : "+allFolders[i]);
							sa.assertTrue(false,"Download access icon is Should be Disabled for : "+allFolders[i]);
						}
					}
					if ( cp.getUploadAccessIcon(Workspace.FundraisingWorkspace, 5)==null) {
						appLog.info("Upload access icon is Disabled for : "+allFolders[i]);
					}
					else {
						appLog.error("Upload access icon is Should be Disabled for : "+allFolders[i]);
						sa.assertTrue(false,"Upload access icon is Should be Disabled for : "+allFolders[i]);
					}
				}
				else {
					appLog.error("common path is not found on contacts page folder structure : "+allFolders[i]);
					sa.assertTrue(false, "common path is not found on contacts page folder structure : "+allFolders[i]);
				}
			}
		}
		else {
			appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
			sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
		}
	}
	else {
		appLog.error("contact tab is not clickable");
		sa.assertTrue(false, "contact tab is not clickable");
	}
	switchToDefaultContent(driver);
	lp.CRMlogout();
	sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc020_2_verifyUploadpermissionAtTargetSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOnTC="M17tc020_1_InviteContactFromParentAndRemoveAccessFromChild";
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependOnTC,excelLabel.CommonPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependOnTC,excelLabel.StandardPath);
		String standPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependOnTC,excelLabel.KeyWord_For_Search);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.PotentialInvestmentPage,
					null, 60)) {
				appLog.info(" Folder Structure Verified: " + cmnPath);
				if(ifp.getUploadIcon(2)==null) {
					appLog.info("Upload button is not visible ");
				}else {
					appLog.error("Upload button is visible");
					sa.assertTrue(false, "Upload button is visible");
				}
			}else {
				appLog.error("could not find folder structure "+cmnPath);
				sa.assertTrue(false, "could not find folder structure "+cmnPath);
			}

			for(int i=0; i<stdPath.split(",").length; i++) {
				if (fp.verifyFolderPathdummy(stdPath.split(",")[i], null, null, null, PageName.PotentialInvestmentPage,null, 60)) {
					appLog.info(" Folder Structure Verified: " + stdPath);
					if(ifp.getUploadIcon(2)==null) {
						appLog.info("Upload button is not visible ");
					}else {
						appLog.error("Upload button is visible");
						sa.assertTrue(false, "Upload button is visible");
					}
				}else {
					appLog.error("could not find folder structure "+stdPath);
					sa.assertTrue(false, "could not find folder structure "+stdPath);
				}
			}

			if (click(driver, ifp.getBulkDownloadIcon(39), "bulk download icon", action.SCROLLANDBOOLEAN)) {
				String parentID = switchOnWindow(driver);

				if (parentID!=null) {
					if (ifp.verifyBulkDownLoadFolderStructure(cmnPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + cmnPath);


						if (ifp.verifyBulkDownLoadFolderStructure(standPath, 20)) {
							appLog.info(" Folder Structure Verified on Bulk DownLoad: " + standPath);

							String[] folders = standPath.split("/");


							if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(folders[0], 10)) {
								appLog.info("Clicked on CheckBox for Folder: "+folders[0]);		

								ThreadSleep(2000);
								String alertMsg=InvestorFirmPageErrorMessage.LimitedAccessErrorMessageBulkDownload;
								String msg ;
								if (isAlertPresent(driver)) {
									msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if (msg.trim().contains(alertMsg)) {
										appLog.info("Error Message is verified in contact access : "+alertMsg);
									} else {
										appLog.error("Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
										sa.assertTrue(false,"Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
									}
								} else {
									appLog.error("Alert is not present so cannot verify alert message "+alertMsg);
									sa.assertTrue(false, "Alert is not present so cannot verify alert message "+alertMsg);
								}

							}else{
								appLog.error("Not Able to Click on CheckBox for Folder: "+folders[0]);
								sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+folders[0]);	
							}	

						}else {
							appLog.error("could not find folder structure on Bulk DownLoad"+standPath);
							sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+standPath);
						}


						if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(cmnPath, 10)) {
							appLog.info("Clicked on CheckBox for Folder: "+cmnPath);		
						}else{
							appLog.error("Not Able to Click on CheckBox for Folder: "+cmnPath);
							sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+cmnPath);	
						}

					}else {
						appLog.error("could not find folder structure on Bulk DownLoad: "+cmnPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+cmnPath);
					}



					if (click(driver, ifp.getBulkDownloadDownloadButton(30), "download window", action.BOOLEAN)) {


						if (click(driver, ifp.getBulkDownloadConfirmationPopUpYesBtn(30),
								"Bulk Download Yes Button", action.BOOLEAN)) {
							appLog.error("Clicked on Yes Button.");


							Robot rob;
							try {
								rob = new Robot();
								ThreadSleep(10000);
								rob.keyPress(KeyEvent.VK_ENTER);
								rob.keyRelease(KeyEvent.VK_ENTER);
								ThreadSleep(10000);
								rob.keyPress(KeyEvent.VK_CONTROL);
								rob.keyPress(KeyEvent.VK_J);
								rob.keyRelease(KeyEvent.VK_J);
								rob.keyRelease(KeyEvent.VK_CONTROL);
							} catch (AWTException e) {
								// TODO Auto-generated catch
								// block
								e.printStackTrace();
							}
							ThreadSleep(20000);
							driver.close();
							driver.switchTo().window(parentID);
							// ele.sendKeys(Keys.CONTROL+"j");
							parentID = switchOnWindow(driver);
							if (parentID == null) {
								try {
									rob = new Robot();
									rob.keyPress(KeyEvent.VK_TAB);
									rob.keyRelease(KeyEvent.VK_TAB);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
									parentID = switchOnWindow(driver);
								} catch (AWTException e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
							}

							ThreadSleep(5000);
							String fileName = ifp.getDownloadedFileName();
							if (fileName.contains(M17FundName1 + " " + getSystemDate("MM-dd-YYYY"))) {
								appLog.info("file name is successfully matched. " + fileName);
							} else {
								appLog.info("File name is not the matched. Expected: " + M17FundName1 + " "
										+ getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
								sa.assertTrue(false, "File name is not the matched. Expected: " + M17FundName1
										+ " " + getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
							}
							driver.close();
							driver.switchTo().window(parentID);
						}
					}else{
						appLog.error("Not able to click on DownLoad Button");
						sa.assertTrue(false, "Not able to click on DownLoad Button");
					}


				}else{
					appLog.error("Not New Windw is open");
					sa.assertTrue(false, "Not New Windw is open");		
				}
			}else{
				appLog.error("Not able to click on Bulk DownLoad Icon");
				sa.assertTrue(false, "Not able to click on Bulk DownLoad Icon");	
			}
		}else {
			appLog.error("Not able to click on potential investment tab so cannot check upload permission");
			sa.assertTrue(false, "Not able to click on potential investment tab so cannot check upload permission");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc021_1_RemoveTheAccessFromInvitedParentFolderActionCRMSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		String stdPaths = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String[] stdFolder=stdPaths.split(",");
		WebElement ele;
		String msg;
		String errorMsg=ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace;
		String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
		if (cp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "workspace section");
				if (fp.verifyFolderPathdummy(stdFolder[0], M17Institution1, null, null, PageName.FundsPage,Workspace.FundraisingWorkspace,60)) {
					appLog.error("folder path verified : "+M17Institution1+"/"+stdFolder[0]);
					if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
							WebElement remove = isDisplayed(driver, FindElement(driver, "//a[text()='"+M17Contact1EmailId+"']/../../span/a[@title='Remove']", "remove link in front "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName, action.BOOLEAN, 60), "Visibility", 60, "Remove link");
							if (remove!=null) {
								if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on remove link for : "+contactFullName);
									ThreadSleep(5000);
									if (click(driver, fp.getRemoveAccessClose(Workspace.FundraisingWorkspace, 30), "remove access close button", action.BOOLEAN)) {
										appLog.error("Clicked on remove access close button");	
										
										if (click(driver, fp.getContactAccessCrossIcon(Workspace.FundraisingWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on Contact access Cross Icon");
										} else {
											appLog.error("Contact access Cross Icon is not clickable");
											sa.assertTrue(false, "Contact access close button is not clickable");
										}
									
									}
									else {
										appLog.error("remove access close button is not clickable");
										sa.assertTrue(false, "remove access close button is not clickable");
									}
								
								}else {
									appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
									sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
								}
							}
							else {
								appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
								sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
							}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}
				}
				else {
					appLog.error("folder path is not found : "+M17Institution1+"/"+stdFolder[0]);
					sa.assertTrue(false, "folder path is not found : "+M17Institution1+"/"+stdFolder[0]);
				}
				// Verify Folder For No Data Access after Removing Access From Parent Folder
				if (fp.verifyFolderPathdummy(stdFolder[0], M17Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+M17Institution1+"/"+stdFolder[0]);
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
						ele=fp.noDataToDisplayContactAccess(Workspace.FundraisingWorkspace, 60);
						
						if (ele!=null) {
							msg =ele.getText().trim();
							if(msg.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
								appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message Verified");
							}else {
								appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
								sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
							}
							
						} else {
							appLog.error("No Data msg element is null");
							sa.assertTrue(false, "No Data msg element is null");	
						}
						if (click(driver, fp.getCrossIconOnContactAccess(Workspace.FundraisingWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on Cross Icon");	
						} else {
							appLog.error("Not able to click on Cross Icon");
							sa.assertTrue(false, "Not able to click on Cross Icon");	
						}
						
					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
			}else {
				appLog.error("Folder Structure Not Verified  "+M17Institution1+"/"+stdFolder[0]);
				sa.assertTrue(false, "Folder Structure Not Verified  "+M17Institution1+"/"+stdFolder[0]);
			}
			}
			else {
				appLog.error(M17FundName1+" is not found on funds page");
				sa.assertTrue(false, M17FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");
				
				ele=cp.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60);
				msg=ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace;
				
				if (ele!=null) {
					msg = ele.getText().trim();
					
					if (errorMsg.equals(msg)) {
					appLog.info(msg+" on contatct Page Verified ");	
					} else {
						appLog.error("Error Msg on contatct Page Not Verified Actual : "+msg+"\t Expected : "+errorMsg);	
						sa.assertTrue(false, "Error Msg on contatct Page Not Verified Actual : "+msg+"\t Expected : "+errorMsg);
					}
					
				} else {
					appLog.error("Contact Page Error Msg Ele is null");
					sa.assertTrue(false, "Contact Page Error Msg Ele is null");
				}
			
			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc021_2_RemoveTheAccessFromInvitedParentFolderImpactInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		//Investor Side
		List<WebElement> dropdownList=allOptionsInDropDrop(driver, allfp.getFirmNameDropdown(60), "Drop Down List");
		List<String> result=compareMultipleList(driver, Org1FirmName, dropdownList);
		
		if (!result.isEmpty()) {
			appLog.info("Firm Name is not available in DropDown after removing Acces");	
		} else {
			appLog.error("Firm Name is available in DropDown after removing Acces");
			sa.assertTrue(false, "Firm Name is available in DropDown after removing Acces");
		}
		lp.investorLogout();
		sa.assertAll();
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc022_1_inviteContactFromMultiFolders(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String[] stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath).split(",");
		String[] allFolders=ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath).split(",");
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				if(fp.inviteContact(environment,mode,M17Institution1, M17Contact1EmailId,stdPath[0], FolderType.Standard, "Upload", "Yes", null,null, Workspace.FundraisingWorkspace, null)) {
					appLog.info(M17Contact1EmailId+" Contact is invited successfully from folder "+stdPath[0]);
				}else {
					appLog.error("Not able to invite contact from parent folder "+stdPath[0]);
					sa.assertTrue(false, "Not able to invite contact from parent folder "+stdPath[0]);
				}
				
				if(fp.inviteContact(environment,mode,M17Institution1, M17Contact1EmailId,stdPath[1], FolderType.Standard, null, "Yes", null,null, Workspace.FundraisingWorkspace, null)) {
					appLog.info(M17Contact1EmailId+" Contact is invited successfully from folder "+stdPath[1]);
				}else {
					appLog.error("Not able to invite contact from parent folder "+stdPath[1]);
					sa.assertTrue(false, "Not able to invite contact from parent folder "+stdPath[1]);
				}
				
				if(fp.inviteContact(environment,mode,M17Institution1, M17Contact1EmailId,stdPath[2], FolderType.Standard, "download", "Yes", null,null, Workspace.FundraisingWorkspace, null)) {
					appLog.info(M17Contact1EmailId+" Contact is invited successfully from folder "+stdPath[2]);
				}else {
					appLog.error("Not able to invite contact from parent folder "+stdPath[2]);
					sa.assertTrue(false, "Not able to invite contact from parent folder "+stdPath[2]);
				}
				
				switchToFrame(driver, 30, cp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");
				for (int i = 0; i < allFolders.length; i++) {
					if (fp.verifyFolderPathdummy(allFolders[i], M17Institution1, null, null, PageName.FundsPage,Workspace.FundraisingWorkspace,60)) {
						if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
							if(i==0) {
								if(fp.getSelectedContactNameListInSelectContactGrid(Workspace.FundraisingWorkspace).size()==3) {
									appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact name is displaying three time in contact access grid on folder "+allFolders[i]);
								}else {
									appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact name is not displaying three time in contact access grid on folder "+allFolders[i]);
									sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact name is not displaying three time in contact access grid on folder "+allFolders[i]);
								}
							}
							if(i==1) {
								if(fp.getSelectedContactNameListInSelectContactGrid(Workspace.FundraisingWorkspace).size()==2) {
									appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact name is displaying two time in contact access grid on folder "+allFolders[i]);
								}else {
									appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact name is not displaying two time in contact access grid on folder "+allFolders[i]);
									sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact name is not displaying two time in contact access grid on folder "+allFolders[i]);
								}
							}
						}else {
							appLog.error("Not able to click on contact access icon on folder "+stdPath[i]);
							sa.assertTrue(false, "Not able to click on contact access icon on folder "+stdPath[i]);
						}
						if(click(driver, fp.getCancelBtn(Workspace.FundraisingWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.error("Clicked on close button");
						}else {
							appLog.error("Not able to click on cancel button so cannot close contact access popup");
							sa.assertTrue(false, "Not able to click on cancel button so cannot close contact access popup");
						}
					}else {
						appLog.error("Not able to click on folder structure "+allFolders[i]);
						sa.assertTrue(false, "Not able to click on folder structure "+allFolders[i]);
					}
				}
			}else {
				appLog.error("Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
				sa.assertTrue(false, "Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
		}
		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
		if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
			switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
			scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");
			for (int i=0;i<allFolders.length;i++) {
				
				if (fp.verifyFolderPathdummy(allFolders[i], null, null, M17FundName1, PageName.ContactsPage,
						Workspace.FundraisingWorkspace, 60)) {
					
					if ( cp.getViewAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
						appLog.info("View access icon is Enabled for : "+allFolders[i]);
					}
					else {
						appLog.error("View access icon is Should be Enabled for : "+allFolders[i]);
						sa.assertTrue(false,"View access icon is Should be Enabled for : "+allFolders[i]);
					}
					
					if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
						appLog.info("Download access icon is Enabled for : "+allFolders[i]);
					}
					else {
						appLog.error("Download access icon is Should be Enabled for : "+allFolders[i]);
						sa.assertTrue(false,"Download access icon is Should be Enabled for : "+allFolders[i]);
					}
					if(i==0) {
						if ( cp.getUploadAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
							appLog.info("Upload access icon is Disabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Upload access icon is Should be Disabled for : "+allFolders[i]);
							sa.assertTrue(false,"Upload access icon is Should be Disabled for : "+allFolders[i]);
						}
					}else {
						if ( cp.getUploadAccessIcon(Workspace.FundraisingWorkspace, 5)==null) {
							appLog.info("Upload access icon is Enabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Upload access icon is Should be Enabled for : "+allFolders[i]);
							sa.assertTrue(false,"Upload access icon is Should be Enabled for : "+allFolders[i]);
						}
					}
					
				}
				else {
					appLog.error("folder path is not found on contacts page folder structure : "+allFolders[i]);
					sa.assertTrue(false, "folder path is not found on contacts page folder structure : "+allFolders[i]);
				}
			}
		}
		else {
			appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
			sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
		}
	}
	else {
		appLog.error("contact tab is not clickable");
		sa.assertTrue(false, "contact tab is not clickable");
	}
	switchToDefaultContent(driver);
	lp.CRMlogout();
	sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc022_2_inviteContactFromMultiFoldersImpactInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String[] stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath).split(",");
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.PotentialInvestmentPage,
					null, 60)) {
				appLog.info(" Folder Structure Verified: " + cmnPath);
			
			}else {
				appLog.error("could not find folder structure "+cmnPath);
				sa.assertTrue(false, "could not find folder structure "+cmnPath);
			}
			
			for(int i=0; i<stdPath.length; i++) {
				if (fp.verifyFolderPathdummy(stdPath[i], null, null, null, PageName.PotentialInvestmentPage,null, 60)) {
					appLog.info(" Folder Structure Verified: " + stdPath[i]);
					
				}else {
					appLog.error("could not find folder structure "+stdPath[i]);
					sa.assertTrue(false, "could not find folder structure "+stdPath[i]);
				}
			}
			
			
			//
			
			
			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");
				
				String parentID = switchOnWindow(driver);
				
				if (parentID!=null) {
					if (ifp.verifyBulkDownLoadFolderStructure(cmnPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + cmnPath);
						
						if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(cmnPath, 10)) {
							appLog.info("Clicked on CheckBox for Folder: "+cmnPath);		
						}else{
							appLog.error("Not Able to Click on CheckBox for Folder: "+cmnPath);
							sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+cmnPath);	
						}
						
					}else {
						appLog.error("could not find folder structure on Bulk DownLoad: "+cmnPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+cmnPath);
					}

					if (ifp.verifyBulkDownLoadFolderStructure(stdPath[0], 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + stdPath[0]);
						
						String[] folders = stdPath[0].split("/");
						
						for (int i=folders.length-1;i>=0;i--) {
							if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(folders[i], 10)) {
								appLog.info("Clicked on CheckBox for Folder: "+folders[i]);		
							}else{
								appLog.error("Not Able to Click on CheckBox for Folder: "+folders[i]);
								sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+folders[i]);	
							}	
						}
						
						
					}else {
						appLog.error("could not find folder structure on Bulk DownLoad"+stdPath[0]);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+stdPath[0]);
					}
	
						if (click(driver, ifp.getBulkDownloadDownloadButton(10), "DownLoad Button", action.BOOLEAN)) {
						
							
							if (click(driver, ifp.getBulkDownloadConfirmationPopUpYesBtn(30),
									"Bulk Download Yes Button", action.BOOLEAN)) {
								appLog.error("Clicked on Yes Button.");
								
								
								Robot rob;
								try {
									rob = new Robot();
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
								} catch (AWTException e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
								ThreadSleep(20000);
								driver.close();
								driver.switchTo().window(parentID);
								// ele.sendKeys(Keys.CONTROL+"j");
								parentID = switchOnWindow(driver);
								if (parentID == null) {
									try {
										rob = new Robot();
										rob.keyPress(KeyEvent.VK_TAB);
										rob.keyRelease(KeyEvent.VK_TAB);
										rob.keyPress(KeyEvent.VK_ENTER);
										rob.keyRelease(KeyEvent.VK_ENTER);
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_CONTROL);
										rob.keyPress(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_CONTROL);
										parentID = switchOnWindow(driver);
									} catch (AWTException e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}
								}
							
								ThreadSleep(5000);
								String fileName = ifp.getDownloadedFileName();
								if (fileName.contains(M17FundName1 + " " + getSystemDate("MM-dd-YYYY"))) {
									appLog.info("file name is successfully matched. " + fileName);
								} else {
									appLog.info("File name is not the matched. Expected: " + M17FundName1 + " "
											+ getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
									sa.assertTrue(false, "File name is not the matched. Expected: " + M17FundName1
											+ " " + getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
								}
								driver.close();
								driver.switchTo().window(parentID);
								
							} else {
								appLog.error("Not Able to Click on Yes Button.");
								sa.assertTrue(false, "Not Able to Click on Yes Button.");
							}
							
							
						} else {
							appLog.error("Download Button is not working.");
							sa.assertTrue(false, "Download Button is not working.");
						}
				
						
				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}
				
				
			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}
			
			
		}else {
			appLog.error("Not able to click on potential investment tab so cannot check upload permission");
			sa.assertTrue(false, "Not able to click on potential investment tab so cannot check upload permission");
		}
		switchToDefaultContent(driver);
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc023_1_AddSharedFolderFromManageFolderAndInviteContact(){
	
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath).split(",");
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),"Investor Workspace.");
				if(fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30)!=null){
					if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "Manage Folder icon", action.SCROLLANDBOOLEAN)){

						//Creating shared folder
						if(fp.createFolderStructure(shdPath[0], FolderType.Shared, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(shdPath[0]+ " folder structure is created.");
							//Creating Shared sub folder
							if(fp.createFolderStructure(shdPath[1], FolderType.Shared, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
								appLog.info(shdPath[1]+ " folder structure is created.");
								
								if (click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 60), "Manage Folder Close Button", action.SCROLLANDBOOLEAN)) {
									sa.assertTrue(true, "Successfully created shared folder in Investor Workspace.");
									appLog.info("Successfully created shared folder in Investor Workspace.");
									
								} else{
									appLog.info("Manage folder close button is nto avalaible to click.");
									sa.assertTrue(false, "Manage folder close button is nto avalaible to click.");
								}
							}else{
								sa.assertTrue(false,"Not able to create Folder" +shdPath[1]+"from Manage Folder in Investor Workspace.");
								appLog.error("Not able to create Folder" +shdPath[1]+"from Manage Folder in Investor Workspace.");
							}
						} else{
							sa.assertTrue(false,"Not able to create Folder" +shdPath[0]+"from Manage Folder in Investor Workspace.");
							appLog.error("Not able to create Folder" +shdPath[0]+"from Manage Folder in Investor Workspace.");
						}
					}else{
						sa.assertTrue(false,"Not able to click on Manage Folder icon.");
						appLog.info("Not able to click on Manage Folder icon.");
					}

				} else{
					sa.assertTrue(false,"Manage Folder icon is not available to click.");
					appLog.info("Manage Folder icon is not available to click.");
				}

			}else{
				appLog.error("Not able to click on Created Fund: "+M17FundName1);
				sa.assertTrue(false, "Not able to click on Created Fund: "+M17FundName1);	
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc023_2_InviteContactFromNewlyCreatedSharedFolderAndVerifyItsImpact(String environment, String mode){
		
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath).split(",");
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (fp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				//Inviting contact from newly created Shared folder.
				if (fp.inviteContact(environment, mode, M17Institution1, M17Contact1EmailId, shdPath[0], FolderType.Shared, null, "Yes", "No", "Shared", Workspace.FundraisingWorkspace, null)) {
					appLog.info("contact has been given access successfully and invite has been sent to mail");
					
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					//Verify data in Selected Contacts grid.
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");
					String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
					
					appLog.info("Verifying "+contactFullName+" in Selected Contacts grid.");
					if (fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
						appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Enabled is Verified");
						
						WebElement ele = fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
						if(isSelected(driver, ele, "View CheckBox")) {
							appLog.info("View Check box is checked by default.");
						}else {
							appLog.error("View Check box Should be checked");
							sa.assertTrue(false, "View Check box Should be checked");
						}
						
						if(!isEnabled(driver, ele, "View Checkbox")) {
							appLog.info("View Checkbox box is Disabled");
						}else {
							appLog.error("View Checkbox Should be Disabled");
							sa.assertTrue(false, "View Checkbox Should be Disabled");
						}
						
						ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
						if(!isSelected(driver, ele, "Download / Print Documents CheckBox")) {
							appLog.info("Download Check box is Unchecked");
						}else {
							appLog.error("Download Check box Should be Unchecked");
							sa.assertTrue(false, "Download Check box Should be Unchecked");
						}
						
						if(isEnabled(driver, ele, "Download Icon")) {
							appLog.info("Download Check box is Enabled");
						}else {
							appLog.error("Download Check box is not enabled.");
							sa.assertTrue(false, "Download Check box is not enabled.");
							}
						ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.Upload);
						
						//verifying non availability of upload checkbox.
						if(ele==null){
							appLog.info("Upload check box is not available in case of Shared folder.");
						} else{
							appLog.info("Upload check box is available in case of Shared folder.");
							sa.assertTrue(false, "Upload check box is not available in case of Shared folder.");
						}
						
						if(fp.getUploadColumn(5)==null){
							appLog.info("Upload column is not available in case of Shared folder.");
						}else{
							appLog.info("Upload column is available in case of Shared folder.");
							sa.assertTrue(false, "Upload column is available in case of Shared folder.");
						}
						
						
						if(click(driver, fp.getContactAccessCancelBtn(Workspace.FundraisingWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("click on contact access pop up cancel button");
						}else {
							appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
							sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
						}
						

					}else {
						appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is not Verified");
						sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is not Verified");
						}
					}else{
						appLog.error("Cann't click on Contact access icon.");
						sa.assertTrue(false, "Cann't click on Contact access icon.");
					} 
				}
				else {
					appLog.error("Contact could not be given access");
					sa.assertTrue(false, "Contact could not be given access");
				}

				
				//Sending Mail to contact
				boolean mail_Status = false;
				switchToDefaultContent(driver);
				switchToFrame(driver, 30,fp. getFrame( PageName.FundsPage, 30));
				ThreadSleep(10000);
				if (fp.sendInvitationMail(Workspace.FundraisingWorkspace, M17Contact1EmailId, shdPath[0] , M17Contact1LastName)) {
					appLog.info("contact "+M17Contact1LastName+ " has been sent email for invitation");
					mail_Status=true;
					//Verifying mail received
					try {
						investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail", gmailUserName, gmailPassword,
								CRMUser1EmailID, M12Contact1EmailId);
						appLog.info("Investor Registration Link: " + investorRegLink);
						if(investorRegLink!=null){
							appLog.info("invitation Mail Sent and Received");	
						}else{
							appLog.error("invitation Mail Sent but not Received for "+M17Contact1LastName);
							sa.assertTrue(false, "invitation Mail Sent but not Received for "+M17Contact1LastName);
						}

					} catch (InterruptedException e) {
						appLog.info("Invitation mail not found.");
						e.printStackTrace();
					}
					
				}
				else {
					appLog.error("invitation email could not be sent to "+M17Contact1LastName);
					sa.assertTrue(false, "invitation email could not be sent to "+M17Contact1LastName);
				}
				
				
				
				if(mail_Status){
					appLog.info("Mail Sent");	
				}else{
					appLog.error("invitation email could not be sent to "+M17Contact1LastName);
					sa.assertTrue(false, "MAil is not recived b contact - "+M17Contact1LastName);
				}

				switchToDefaultContent(driver);
			}
			else {
				appLog.error("fund name provided could not be found");
				sa.assertTrue(false, "fund name provided could not be found");
			}
		}
		else {
			appLog.error("Funds tab is not clickable");
			sa.assertTrue(false, "Funds tab is not clickable");
		}

		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc024_1_InvestorUserLoginAndVerifyFolderStructure() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String[] shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath).split(",");
		String[] stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath).split(",");
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.PotentialInvestmentPage,
					null, 60)) {
				appLog.info(" Folder Structure Verified: " + cmnPath);
			
			}else {
				appLog.error("could not find folder structure "+cmnPath);
				sa.assertTrue(false, "could not find folder structure "+cmnPath);
			}
			
			for(int i=0; i<stdPath.length; i++) {
				if (fp.verifyFolderPathdummy(stdPath[i], null, null, null, PageName.PotentialInvestmentPage,null, 60)) {
					appLog.info(" Folder Structure Verified: " + stdPath[i]);
					
				}else {
					appLog.error("could not find folder structure "+stdPath[i]);
					sa.assertTrue(false, "could not find folder structure "+stdPath[i]);
				}
			}
			
			for(int i=0; i<shdPath.length; i++) {
				if (fp.verifyFolderPathdummy(shdPath[i], null, null, null, PageName.PotentialInvestmentPage,null, 60)) {
					appLog.info(" Folder Structure Verified: " + shdPath[i]);
					
				}else {
					appLog.error("could not find folder structure "+shdPath[i]);
					sa.assertTrue(false, "could not find folder structure "+shdPath[i]);
				}
			}
			
			if (ifp.getBulkDownloadIcon(10)!=null) {
				appLog.info("Bulk Download Icon is visibile");
			}else{
				appLog.error("Bulk Download Icon is not visibile");
				sa.assertTrue(false, "Bulk Download Icon is visibile");	
			}
			
			
		}else {
			appLog.error("Not able to click on potential investment tab so cannot verify Folder");
			sa.assertTrue(false, "Not able to click on potential investment tab so cannot verify Folder");
		}
		switchToDefaultContent(driver);
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc024_2_VerifyPermissionAtContactDetailPage(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		WebElement ele=null;
		String depentdenTc="M17tc024_1_InvestorUserLoginAndVerifyFolderStructure";
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, depentdenTc,excelLabel.CommonPath);
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, depentdenTc,excelLabel.SharedPath);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, depentdenTc, excelLabel.StandardPath);
		String folders = cmnPath+","+shdPath+","+stdPath;
		System.err.println("Common : >> "+cmnPath);
		System.err.println("shdPath : >> "+shdPath);
		System.err.println("stdPath : >> "+stdPath);
		System.err.println("Folders NAMES : >>>>>>>   "+folders);
		String[] allFolders = folders.split(",");
		boolean flag=false;
		
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		
		if (cp.clickOnTab(TabName.ContactTab)) {
		if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
			switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
			
			scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");
			
			for (int i=0;i<allFolders.length;i++) {
				
				if (fp.verifyFolderPathdummy(allFolders[i], null, null, M17FundName1, PageName.ContactsPage,
						Workspace.FundraisingWorkspace, 60)) {
					
					if ( cp.getViewAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
						appLog.info("View access icon is Enabled for : "+allFolders[i]);
					}
					else {
						appLog.error("View access icon is Should be Enabled for : "+allFolders[i]);
						sa.assertTrue(false,"View access icon is Should be Enabled for : "+allFolders[i]);
					}
					
					
					if (i==0 || i==3 || i==4 || i==5) {
						
						if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
							appLog.info("Download access icon is Enabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Download access icon is Should be Enabled for : "+allFolders[i]);
							sa.assertTrue(false,"Download access icon is Should be Enabled for : "+allFolders[i]);
						}
						
					} else {
						
						if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 5)==null) {
							appLog.info("Download access icon is Disabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Download access icon is Should be Disabled for : "+allFolders[i]);
							sa.assertTrue(false,"Download access icon is Should be Disabled for : "+allFolders[i]);
						}
					}
					
					
					if (i==5 ) {
						
						if ( cp.getUploadAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
							appLog.info("Upload access icon is Enabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Upload access icon is Should be Enabled for : "+allFolders[i]);
							sa.assertTrue(false,"Upload access icon is Should be Enabled for : "+allFolders[i]);
						}
						
					} else {
						
						if ( cp.getUploadAccessIcon(Workspace.FundraisingWorkspace, 5)==null) {
							appLog.info("Upload access icon is Disabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Upload access icon is Should be Disabled for : "+allFolders[i]);
							sa.assertTrue(false,"Upload access icon is Should be Disabled for : "+allFolders[i]);
						}
						
					}
					
					
				}
				else {
					appLog.error("common path is not found on contacts page folder structure : "+allFolders[i]);
					sa.assertTrue(false, "common path is not found on contacts page folder structure : "+allFolders[i]);
				}
				
			}
			
		}
		else {
			appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
			sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
		}
	}
	else {
		appLog.error("contact tab is not clickable");
		sa.assertTrue(false, "contact tab is not clickable");
	}

		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(shdPath.split(",")[0], null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+shdPath.split(",")[0]);
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
						
						String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
					
						appLog.info("Going to Verify "+contactFullName+" with Remove Enabled Link");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Enable is Verified");
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
							if(!isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is Unchecked");
								
								if (click(driver, ele, "Download CheckBox 1", action.BOOLEAN)) {
									appLog.info("Clicked on  Download Check 1 box So it is checked");	
										ThreadSleep(2000);
									if (click(driver, ele, "Download CheckBox 2", action.BOOLEAN)) {
										appLog.info("Clicked on  Download Check 2 box So it is Unchecked");	
										
										ThreadSleep(2000);
										if(click(driver, fp.getApplyBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" apply button", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on apply button successfully");
											if(click(driver, fp.getCloseBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" close button", action.SCROLLANDBOOLEAN)) {
												appLog.info("Clicked on Close Buton");	
												flag=true;
											}else {
												appLog.error("Not able to click on close button");
												sa.assertTrue(false, "Not able to click on close button");
											}
										}else{
											appLog.error("Not able to Click on apply button");
											sa.assertTrue(false, "Not able to Click on apply button")	;
										}
										
									} else {
										appLog.error("Not Able to Click on  Download Check 2 box");
										sa.assertTrue(false, "Not Able to Click on  Download Check 2 box");
									}
									
								} else {
									appLog.error("Not Able to Click on  Download Check 1 box");
									sa.assertTrue(false, "Not Able to Click on  Download Check1  box");
								}
							}else {
								appLog.error("Download Check box Should be Unchecked");
								sa.assertTrue(false, "Download Check box Should be Unchecked");
							}
								
						} else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
					
						}
					
						
					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
			}else {
				appLog.error("Folder Structure Not Verified  "+shdPath.split(",")[0]);
				sa.assertTrue(false, "Folder Structure Not Verified  "+shdPath.split(",")[0]);
			}
				
				
		}else {
			appLog.error("Not able to click on created fund Name : "+M17FundName1);
			sa.assertTrue(false, "Not able to click on created fund Name : "+M17FundName1);
		
		}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		
		if (flag) {
		appLog.info("Going to Verify After Check/Uncheck Operation");	
			if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on Contact Access Icon");	
				
				String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
			
				appLog.info("Going to Verify "+contactFullName+" with Remove Enabled Link");
				if (fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
					appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Enable is Verified");
					
					ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
					if(isSelected(driver, ele, "View CheckBox")) {
						appLog.info("View Check box is checked");
					}else {
						appLog.error("View Check box Should be checked");
						sa.assertTrue(false, "View Check box Should be checked");
					}
					
					ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
					if(!isSelected(driver, ele, "Download / Print Documents CheckBox")) {
						appLog.info("Download Check box is Unchecked");
						
					}else {
						appLog.error("Download Check box Should be Unchecked");
						sa.assertTrue(false, "Download Check box Should be Unchecked");
					}
						
				} else {
					appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
					sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
			
				}
				
				if (click(driver, fp.getCrossIconOnContactAccess(Workspace.FundraisingWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on Cross Icon");	
				} else {
					appLog.error("Not able to click on Cross Icon");
					sa.assertTrue(false, "Not able to click on Cross Icon");	
				}
				
			}else {
				appLog.error("Not able to click on Contact Access Icon");
				sa.assertTrue(false, "Not able to click on Contact Access Icon");	
			}
			
			
		} else {
			appLog.error("Flag is false so cannot check selected contact Grid in Shared Folder");
			sa.assertTrue(false, "Flag is false so cannot check selected contact Grid in Shared Folder");
		}
	
	switchToDefaultContent(driver);

	lp.CRMlogout();
	sa.assertAll();
	
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc025_1_CheckFunctionalityOfRemoveLinkUnderActionColumn() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		String commonPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		WebElement popUpEle;
		String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
		if (cp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "workspace section");
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage,Workspace.FundraisingWorkspace,60)) {
					appLog.error("folder path verified : "+shdPath);
					if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
							WebElement remove = isDisplayed(driver, FindElement(driver, "//a[text()='"+M17Contact1EmailId+"']/../../span/a[@title='Remove']", "remove link in front "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName, action.BOOLEAN, 60), "Visibility", 60, "Remove link");
							if (remove!=null) {
								if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on remove link for : "+contactFullName);
									ThreadSleep(5000);
									if (click(driver, fp.getRemoveAccessClose(Workspace.FundraisingWorkspace, 30), "remove access close button", action.BOOLEAN)) {
										appLog.error("Clicked on remove access close button");	
										
										popUpEle = fp.getContactAccessRemovedPopUpMsg(Workspace.FundraisingWorkspace, 5);
										if (popUpEle==null) {
											appLog.info("Access Remove PopUp is closed after click on Closed Button");
										}else{
											appLog.error("Access Remove PopUp is not closed after click on Closed Button");
											sa.assertTrue(false, "Access Remove PopUp is not closed after click on Closed Button");	
										}
										
										if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.FundraisingWorkspace, 60), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
											appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
										}else {
											appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
											sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
										}
										
										if (click(driver, fp.getContactAccessCrossIcon(Workspace.FundraisingWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on Contact access Cross Icon");
										} else {
											appLog.error("Contact access Cross Icon is not clickable");
											sa.assertTrue(false, "Contact access close button is not clickable");
										}
										
										popUpEle = fp.getContactAccessCrossIcon(Workspace.FundraisingWorkspace, 5);
										if (popUpEle==null) {
											appLog.info("Select Contact Access PopUp is closed after click on Cross Icon");
										}else{
											appLog.error("Select Contact Access PopUp is not closed after click on Cross Icon");
											sa.assertTrue(false, "Select Contact Access PopUp is not closed after click on Cross Icon");	
										}
									}
									else {
										appLog.error("remove access close button is not clickable");
										sa.assertTrue(false, "remove access close button is not clickable");
									}
								
								}else {
									appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
									sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
								}
							}
							else {
								appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
								sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
							}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}
				}
				else {
					appLog.error("folder path is not found : "+shdPath);
					sa.assertTrue(false, "folder path is not found : "+shdPath);
				}
				
			}
			else {
				appLog.error(M17FundName1+" is not found on funds page");
				sa.assertTrue(false, M17FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");
				if (fp.verifyFolderPathdummy(commonPath, null, null, M17FundName1, PageName.ContactsPage,Workspace.FundraisingWorkspace, 60)) {
					appLog.info("Folder structure Verified : " + commonPath);
				} else {
					appLog.error("Folder structure Not Verified : " + commonPath);
					sa.assertTrue(false, "Folder structure Not Verified : " + commonPath);
				}
				
				if (fp.verifyFolderPathdummy(stdPath, null, null, M17FundName1, PageName.ContactsPage,Workspace.FundraisingWorkspace, 60)) {
					appLog.info("Folder structure Verified : " + stdPath);
				} else {
					appLog.error("Folder structure Not Verified : " + stdPath);
					sa.assertTrue(false, "Folder structure Not Verified : " + stdPath);
				}
			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc025_2_CheckFunctionalityOfRemoveLinkImpactInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String dependentTc = "M17tc025_1_CheckFunctionalityOfRemoveLinkUnderActionColumn";
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependentTc,excelLabel.CommonPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependentTc,excelLabel.StandardPath);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.PotentialInvestmentPage,
					null, 60)) {
				appLog.info(" Folder Structure Verified: " + cmnPath);
			
			}else {
				appLog.error("could not find folder structure "+cmnPath);
				sa.assertTrue(false, "could not find folder structure "+cmnPath);
			}
			
				if (fp.verifyFolderPathdummy(stdPath, null, null, null, PageName.PotentialInvestmentPage,null, 60)) {
					appLog.info(" Folder Structure Verified: " + stdPath);
					
				}else {
					appLog.error("could not find folder structure "+stdPath);
					sa.assertTrue(false, "could not find folder structure "+stdPath);
				}
			
			
			//
			
			
			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");
				
				String parentID = switchOnWindow(driver);
				
				if (parentID!=null) {
					if (ifp.verifyBulkDownLoadFolderStructure(cmnPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + cmnPath);
						
						
					}else {
						appLog.error("could not find folder structure on Bulk DownLoad: "+cmnPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+cmnPath);
					}

					if (ifp.verifyBulkDownLoadFolderStructure(stdPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + stdPath);
						
						
						
					}else {
						appLog.error("could not find folder structure on Bulk DownLoad"+stdPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+stdPath);
					}
	
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}
				
				
			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}
			
				
		}else {
			appLog.error("Not able to click on Potential investment tab.");
			sa.assertTrue(false, "Not able to click on Potential investment tab.");
		}
		switchToDefaultContent(driver);
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc026_CheckValidationWhenUserHasNoEmailAddressINFR(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		WebElement ele=null;
		String emptyString;
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		String[] ss ={FundsPageErrorMessage.ContactEmailblankErrorMsg,FundsPageErrorMessage.AllContactSelectErrorMsg};
		String[] ss1 ={FundsPageErrorMessage.alreadyInvitedContactAlertMsg,FundsPageErrorMessage.AllContactSelectErrorMsg};

		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Click on Contact Access Icon");
						ThreadSleep(2000);
						if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
							appLog.info("Click on Contact Access Expand Icon");
							if(sendKeys(driver,fp.getSearchTextBox(Workspace.FundraisingWorkspace, 60), M17Contact7FirstName+" "+M17Contact7LastName,Workspace.FundraisingWorkspace+" search text box", action.SCROLLANDBOOLEAN)) {
								appLog.info("enter the value in search text box : "+M17Contact7FirstName+" "+M17Contact7LastName);
								if(click(driver, fp.getSearchBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" search button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on  "+Workspace.FundraisingWorkspace+" search button");

									for (int i=0; i<ss.length; i++) {
										if(i==0) {
											ele=fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, M17Contact7FirstName+" "+M17Contact7LastName, 20);

										}else {
											ele=fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, null, 20);
										}
										if(ele!=null) {
											ThreadSleep(3000);
											if(!clickUsingJavaScript(driver, ele, "check  box")) {
//											if(click(driver, ele, "check box", action.BOOLEAN)) {
												appLog.info("clicked on contact check box : ");
												ThreadSleep(2000);
												if (isAlertPresent(driver)) {
													String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
													switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
													if (msg.trim().contains(ss[i])) {
														appLog.info(
																"Error Message is verified in contact access : "+ss[i]);
													} else {
														appLog.error(
																"Error Message not verified in contact access : "+ss[i]);
														sa.assertTrue(false,
																"Error Message not verified in contact access :"+ss[i]);
													}
												} else {
													appLog.error("Alert is not present so cannot verify alert message "+ss[i]);
													sa.assertTrue(false, "Alert is not present so cannot verify alert message "+ss[i]);
												}

												if(!isEnabled(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 5), "select add button")) {
													appLog.info("Add select Contact(s) button is disabled for iteration : "+i);
												}else {
													appLog.error("Add Select Contact(s) button is not disabled for iteration : "+i);
													sa.assertTrue(false, "Add Select Contact(s) button is not disabled for iteration : "+i);
												}


												if (!isSelected(driver, ele, "Check Bpx against ")) {
													appLog.info("CheckBox is Unchecked for iteration : "+i);
												} else {
													appLog.error("CheckBox Should be Unchecked for iteration : "+i);
													sa.assertTrue(false, "CheckBox Should be Unchecked for iteration : "+i);
												}

											}else {
												appLog.error("Not able to click on Check Box for iteration : "+i);
												sa.assertTrue(false, "Not able to click on Check Box for iteration : "+i);
											}
										}else {
											appLog.error("Check Box Element is not visible so cannot verify error message "+ss[i]);
											sa.assertTrue(false, "Check Box Element is not visible so cannot verify error message "+ss[i]);
										}
									}
								
								}else {
									appLog.error("Not Able to Click on Search Icon");
									sa.assertTrue(false, "Not Able to Click on Search Icon");
								}
							}else {
								appLog.error("Not able to enter value on "+Workspace.FundraisingWorkspace+" search button so cannot invite contact: "+M17Contact7FirstName+" "+M17Contact7LastName);
								sa.assertTrue(false, "Not able to enter value on "+Workspace.FundraisingWorkspace+" search button so cannot invite contact: "+M17Contact7FirstName+" "+M17Contact7LastName);
							}

							if(click(driver, fp.getContactAccessCancelBtn(Workspace.FundraisingWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
								appLog.info("click on contact access pop up cancel button");
							}else {
								appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
								sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
							}

						}else {
							appLog.error("Not able to expand contact access grid so cannot check error message : "+ss[0]+" and "+ss[1]);
							sa.assertTrue(false, "Not able to expand contact access grid so cannot check error message : "+ss[0]+" and "+ss[1]);
						}

					}else {
						appLog.error("Not able to click on Contact Access Icon so cannot check error message : "+ss[0]+" and "+ss[1]);
						sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check error message : "+ss[0]+" and "+ss[1]);	
					}


				}else {
					appLog.error("Folder Path Not verified : "+shdPath);
					sa.assertTrue(false, "Folder Path Not verified : "+shdPath);
				}


				// Invite
				System.err.println("Going for 2nd Round");
				switchToDefaultContent(driver);
				if (fp.inviteContact(environment, mode, null, M17Contact1EmailId, shdPath, FolderType.Shared, null, "Yes", "No", "Shared", Workspace.FundraisingWorkspace, null)) {
					appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact has been given access successfully and invite has been sent to mail");
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					//Verify data in Selected Contacts grid.
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");
						String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;

						appLog.info("Verifying "+contactFullName+" in Selected Contacts grid.");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Enabled is Verified");

							ele = fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked by default.");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}

							if(!isEnabled(driver, ele, "View Checkbox")) {
								appLog.info("View Checkbox box is Disabled");
							}else {
								appLog.error("View Checkbox Should be Disabled");
								sa.assertTrue(false, "View Checkbox Should be Disabled");
							}

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
							if(!isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is Unchecked");
							}else {
								appLog.error("Download Check box Should be Unchecked");
								sa.assertTrue(false, "Download Check box Should be Unchecked");
							}

							if(isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Enabled");
							}else {
								appLog.error("Download Check box is not enabled.");
								sa.assertTrue(false, "Download Check box is not enabled.");
							}

						}else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is not Verified");
						}

						if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
							appLog.info("Click on Contact Access Expand Icon");
							

							for (int i = 0; i < ss1.length; i++) {
								String searchValue ;
								if(i==0){
									searchValue = M17Contact1FirstName+" "+M17Contact1LastName;
								}else{
									searchValue=M17Contact1FirstName;
								}

								if(sendKeys(driver,fp.getSearchTextBox(Workspace.FundraisingWorkspace, 60), searchValue,Workspace.FundraisingWorkspace+" search text box", action.SCROLLANDBOOLEAN)) {
									appLog.info("enter the value in search text box : "+searchValue);
									if(click(driver, fp.getSearchBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" search button", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on  "+Workspace.FundraisingWorkspace+" search button");

										if(i==0) {
											ele=fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, M17Contact7FirstName+" "+M17Contact1LastName, 20);
										}else {
											ele=fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, null, 20);
										}
										if(ele!=null) {
											ThreadSleep(3000);
											if(!clickUsingJavaScript(driver, ele, "check box")) {
//											if(click(driver, ele, "check box", action.BOOLEAN)) {
												appLog.info("clicked on contact check box for iteratin : "+i);
												ThreadSleep(2000);
												if (isAlertPresent(driver)) {
													String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
													switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
													if (msg.trim().contains(ss1[i])) {
														appLog.info(
																"Error Message is verified in contact access : "+ss1[i]);
													} else {
														appLog.error(
																"Error Message not verified in contact access : "+ss1[i]);
														sa.assertTrue(false,
																"Error Message not verified in contact access :"+ss1[i]);
													}
												} else {
													appLog.error("Alert is not present so cannot verify alert message "+ss1[i]);
													sa.assertTrue(false, "Alert is not present so cannot verify alert message "+ss1[i]);
												}
												
												if (!isSelected(driver, ele, "Check Bpx against ")) {
													appLog.info("CheckBox is Unchecked for iteration : "+i);
												} else {
													appLog.error("CheckBox Should be Unchecked for iteration : "+i);
													sa.assertTrue(false, "CheckBox Should be Unchecked for iteration : "+i);
												}

												if (click(driver, fp.getClearIconOnContactAccessSearchBox(Workspace.FundraisingWorkspace, 10), "Clear Icon", action.SCROLLANDBOOLEAN)) {
													appLog.info("Clicked on Cross Icon");
													emptyString = getValueFromElementUsingJavaScript(driver, fp.getSearchTextBox(Workspace.FundraisingWorkspace, 60), "Search Box");

													if(emptyString.isEmpty()) {
														appLog.info("Search Box is Empty for iteration : "+i);
													} else {
														appLog.info("Search Box is not Empty with Value : "+emptyString);
														sa.assertTrue(false, "Search Box is not Empty with Value : "+emptyString);
													}
												}else{
													appLog.info("Not Able to Click on Clear Icon on Search Box for iteration : "+i);
													sa.assertTrue(false, "Not Able to Click on Clear Icon on Search Box for iteration : "+i);	
												}

											}else {
												appLog.error("Not able to click on Check Box for iteration : "+i);
												sa.assertTrue(false, "Not able to click on Check Box for iteration : "+i);
											}
										}else {
											appLog.error("Check Box Element is not visible so cannot verify error message "+ss1[i]);
											sa.assertTrue(false, "Check Box Element is not visible so cannot verify error message "+ss1[i]);
										}

									}else {
										appLog.error("Not Able to Click on Search Icon for iteration : "+i);
										sa.assertTrue(false, "Not Able to Click on Search Icon for iteration : "+i);
									}
								}else {
									appLog.error("Not able to enter value on "+Workspace.FundraisingWorkspace+"so cannot verify error message "+ss1[i]);
									sa.assertTrue(false, "Not able to enter value on "+Workspace.FundraisingWorkspace+"so cannot verify error message "+ss1[i]);
								}

							}
							
						}else{
							appLog.error("Not able to expand contact access grid so cannot check error message : "+ss1[0]+" and "+ss1[1]);
							sa.assertTrue(false, "Not able to expand contact access grid so cannot check error message : "+ss1[0]+" and "+ss1[1]);
							
						}


						if(click(driver, fp.getContactAccessCancelBtn(Workspace.FundraisingWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("click on contact access pop up cancel button");
						}else {
							appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
							sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
						}

					}else{
						appLog.error("Cann't click on Contact access icon.");
						sa.assertTrue(false, "Cann't click on Contact access icon.");
					} 
				}
				else {
					appLog.error("Not Able to give Access to "+M17Contact1FirstName+" "+M17Contact1LastName);
					sa.assertTrue(false, "Not Able to give Access to "+M17Contact1FirstName+" "+M17Contact1LastName);
				}


			}else {
				appLog.error("Not able to click on created fund Name "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name "+M17FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc027_1_UpdateAndRevertEmailAddressFromContactPageandCheckImpactAtContactAccessPopUP(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		String commonPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String[] allFolders = (shdPath+","+stdPath+","+commonPath).split(",");
		WebElement ele;
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		String updatedEmail=cp.generateRandomEmailId();
		String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;

		if(cp.updateEmailAddressOfCreatedContact(M17Contact1FirstName, M17Contact1LastName,updatedEmail)) {
			appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact email id is updated");
			ExcelUtils.writeData(updatedEmail,"Contacts", excelLabel.Variable_Name, "M14Contact1",excelLabel.ContactUpdatedEmailID);
		}else {
			appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact email id is not updated");
			sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact email id is not updated");
		}

		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+shdPath);
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	

						appLog.info("Going to Verify "+contactFullName+" with Remove Enabled Link");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Enable is Verified");

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked ");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}

							if(!isEnabled(driver, ele, "View Icon")) {
								appLog.info("View Icon is disabled");
							}else {
								appLog.error("View Icon is should be disabled");
								sa.assertTrue(false, "View Icon is should be disabled");
							}

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
							if(!isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is Unchecked");
							}else {
								appLog.error("Download Check box Should be Unchecked");
								sa.assertTrue(false, "Download Check box Should be Unchecked");
							}

							if(isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Enabled");
							}else {
								appLog.error("Download Check box Should be Enabled");
								sa.assertTrue(false, "Download Check box Should be Enabled");
							}


						} else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");

						}

						if (click(driver, fp.getCrossIconOnContactAccess(Workspace.FundraisingWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on Cross Icon");	
						} else {
							appLog.error("Not able to click on Cross Icon");
							sa.assertTrue(false, "Not able to click on Cross Icon");	
						}

					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
				}else {
					appLog.error("Folder Structure Not Verified  "+shdPath);
					sa.assertTrue(false, "Folder Structure Not Verified  "+shdPath);
				}


			}else {
				appLog.error("Not able to click on created fund Name : "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name : "+M17FundName1);

			}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		switchToDefaultContent(driver);
		if(cp.updateEmailAddressOfCreatedContact(M17Contact1FirstName, M17Contact1LastName,M17Contact1EmailId)) {
			appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact email id is Restored");
		}else {
			appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact email id is not Restored");
			sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact email id is not Restored");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				for (int i = 0; i < allFolders.length; i++) {
					String ins = null;
				if (i==1) {
					ins=M17Institution1;
				}
					
				if (fp.verifyFolderPathdummy(allFolders[i], ins, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+allFolders[i]);
				}else {
					appLog.error("Folder Structure Not Verified  "+allFolders[i]);
					sa.assertTrue(false, "Folder Structure Not Verified  "+allFolders[i]);
				}
				
				}
			}else {
				appLog.error("Not able to click on created fund Name : "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name : "+M17FundName1);

			}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc027_2_VerifyFolderStructureAndBulkDownloadInInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String dependentTC = "M17tc027_1_UpdateAndRevertEmailAddressFromContactPageandCheckImpactAtContactAccessPopUP";
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependentTC,excelLabel.SharedPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependentTC,excelLabel.StandardPath);
		String commonPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependentTC,excelLabel.CommonPath);
		String[] allFolders = (shdPath+","+stdPath+","+commonPath).split(",");
		String parentID;
		String alertMsg=InvestorFirmPageErrorMessage.LimitedAccessErrorMessageBulkDownload;
		String msg ;

		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			
			for (int i = 0; i < allFolders.length; i++) {
				
				if (fp.verifyFolderPathdummy(allFolders[i], null, null, null, PageName.PotentialInvestmentPage,null, 20)) {
					appLog.info(" Folder Structure Verified: " + allFolders[i]);
				}else {
					appLog.error("could not find folder structure "+allFolders[i]);
					sa.assertTrue(false, "could not find folder structure "+allFolders[i]);
				}

			}

			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");

				parentID=switchOnWindow(driver);

				if (parentID!=null) {

					for (int i = 0; i < allFolders.length; i++) {

						if (ifp.verifyBulkDownLoadFolderStructure(allFolders[i], 20)) {
							appLog.info(" Folder Structure Verified on Bulk DownLoad: " + allFolders[i]);
						}else {
							appLog.error("could not find folder structure on Bulk DownLoad: "+allFolders[i]);
							sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+allFolders[i]);
						}

					}

					if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(shdPath, 10)) {
						appLog.info("Clicked on CheckBox for Folder: "+shdPath);	
						ThreadSleep(2000);
						if (isAlertPresent(driver)) {
							msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
							switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
							if (msg.trim().contains(alertMsg)) {
								appLog.info("Error Message is verified in contact access : "+alertMsg);
							} else {
								appLog.error("Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
								sa.assertTrue(false,"Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
							}
						} else {
							appLog.error("Alert is not present so cannot verify alert message "+alertMsg);
							sa.assertTrue(false, "Alert is not present so cannot verify alert message "+alertMsg);
						}
					} else {
						sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+shdPath);
						appLog.error("Not Able to Click on CheckBox for Folder: "+shdPath);
					}	
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}


			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}


		}else{
			appLog.error("Not Able to Click on "+investorSideWorkSpace.PotentialInvestment);
			sa.assertTrue(false, "Not Able to Click on "+investorSideWorkSpace.PotentialInvestment);	
		}

		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc028_1_ProvideDownloadPermissionToTheInvitedContactINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage,Workspace.FundraisingWorkspace,60)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, 10, CheckBoxName.Download), "DownLoad Documents CheckBox", action.BOOLEAN)) {
							appLog.info("DownLoad CheckBox Checked");
							if(click(driver, fp.getApplyBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" apply button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on apply button successfully");
								if(click(driver, fp.getCloseBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" close button", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on Close Buton");	
								}else {
									appLog.error("Not able to click on close button");
									sa.assertTrue(false, "Not able to click on close button");
								}
							}else{
								appLog.error("Not able to Click on apply button");
								sa.assertTrue(false, "Not able to Click on apply button")	;
							}
						} else {
							appLog.error("Not able to click on DownLoad Documents CheckBox for Contact : "+M17Contact1EmailId);
							sa.assertTrue(false, "Not able to click on DownLoad Documents CheckBox  for Contact : "+M17Contact1EmailId);
						}
					}else {
						appLog.error("Not able to click on contact access icon on folder "+shdPath);
						sa.assertTrue(false, "Not able to click on contact access icon on folder "+shdPath);
					}

				}else {
					appLog.error("Not able to click on folder structure "+shdPath);
					sa.assertTrue(false, "Not able to click on folder structure "+shdPath);
				}

			}else {
				appLog.error("Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
				sa.assertTrue(false, "Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
			}

		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
		}
		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");


				if (fp.verifyFolderPathdummy(shdPath, null, null, M17FundName1, PageName.ContactsPage,
						Workspace.FundraisingWorkspace, 60)) {

					if ( cp.getViewAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
						appLog.info("View access icon is Enabled for : "+shdPath);
					}
					else {
						appLog.error("View access icon is Should be Enabled for : "+shdPath);
						sa.assertTrue(false,"View access icon is Should be Enabled for : "+shdPath);
					}

					if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
						appLog.info("Download access icon is Enabled for : "+shdPath);
					}
					else {
						appLog.error("Download access icon is Should be Enabled for : "+shdPath);
						sa.assertTrue(false,"Download access icon is Should be Enabled for : "+shdPath);
					}

					if ( cp.getUploadAccessIcon(Workspace.FundraisingWorkspace, 5)==null) {
						appLog.info("Upload access icon is Disabled for : "+shdPath);
					}
					else {
						appLog.error("Upload access icon is Should be Disabled for : "+shdPath);
						sa.assertTrue(false,"Upload access icon is Should be Disabled for : "+shdPath);
					}
				}
				else {
					appLog.error("folder path is not found on contacts page folder structure : "+shdPath);
					sa.assertTrue(false, "folder path is not found on contacts page folder structure : "+shdPath);
				}

			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc028_2_VerifyFolderStructureAndBulkDownloadInInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		String commonPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String[] allFolders = (shdPath+","+stdPath+","+commonPath).split(",");
		String parentID;

		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {

			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");

				parentID=switchOnWindow(driver);

				if (parentID!=null) {

					for (int i = 0; i < allFolders.length; i++) {
						if (ifp.verifyBulkDownLoadFolderStructure(allFolders[i], 20)) {
							appLog.info(" Folder Structure Verified on Bulk DownLoad: " + allFolders[i]);
						}else {
							appLog.error("could not find folder structure on Bulk DownLoad: "+allFolders[i]);
							sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+allFolders[i]);
						}	
					}

					if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(shdPath.split("/")[0], 10)) {
						appLog.info("Clicked on CheckBox for Folder: "+shdPath.split("/")[0]);	
						if (click(driver, ifp.getBulkDownloadDownloadButton(10), "DownLoad Button", action.BOOLEAN)) {


							if (click(driver, ifp.getBulkDownloadConfirmationPopUpYesBtn(30),
									"Bulk Download Yes Button", action.BOOLEAN)) {
								appLog.error("Clicked on Yes Button.");


								Robot rob;
								try {
									rob = new Robot();
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
								} catch (AWTException e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
								ThreadSleep(20000);
								driver.close();
								driver.switchTo().window(parentID);
								// ele.sendKeys(Keys.CONTROL+"j");
								parentID = switchOnWindow(driver);
								if (parentID == null) {
									try {
										rob = new Robot();
										rob.keyPress(KeyEvent.VK_TAB);
										rob.keyRelease(KeyEvent.VK_TAB);
										rob.keyPress(KeyEvent.VK_ENTER);
										rob.keyRelease(KeyEvent.VK_ENTER);
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_CONTROL);
										rob.keyPress(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_CONTROL);
										parentID = switchOnWindow(driver);
									} catch (AWTException e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}
								}

								ThreadSleep(5000);
								String fileName = ifp.getDownloadedFileName();
								if (fileName.contains(M17FundName1 + " " + getSystemDate("MM-dd-YYYY"))) {
									appLog.info("file name is successfully matched. " + fileName);
								} else {
									appLog.info("File name is not the matched. Expected: " + M17FundName1 + " "
											+ getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
									sa.assertTrue(false, "File name is not the matched. Expected: " + M17FundName1
											+ " " + getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
								}
								driver.close();
								driver.switchTo().window(parentID);

							} else {
								appLog.error("Not Able to Click on Yes Button.");
								sa.assertTrue(false, "Not Able to Click on Yes Button.");
							}


						} else {
							appLog.error("Download Button is not working.");
							sa.assertTrue(false, "Download Button is not working.");
						}


					} else {
						appLog.error("Not Able to Click on CheckBox for Folder: "+shdPath.split("/")[0]);
						sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+shdPath.split("/")[0]);

					}	


				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}


			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}


		}else{
			appLog.error("Not Able to Click on "+investorSideWorkSpace.PotentialInvestment);
			sa.assertTrue(false, "Not Able to Click on "+investorSideWorkSpace.PotentialInvestment);	
		}

		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc029_TryToProvideAccessFromChildFolderWhenAccessAlreadyProvidedFromParentLevelSharedFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		WebElement ele=null;
		String alertMsg =FundsPageErrorMessage.alreadyInvitedContactAlertMsg;
		String msg;
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+shdPath);
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
						appLog.info("Verifying "+contactFullName+" in Selected Contacts grid.");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, EnableDisable.Disable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Icon Disable is Verified");

							ele = fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace,EnableDisable.Disable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}

							if(!isEnabled(driver, ele, "View Checkbox")) {
								appLog.info("View Checkbox box is Disabled");
							}else {
								appLog.error("View Checkbox Should be Disabled");
								sa.assertTrue(false, "View Checkbox Should be Disabled");
							}

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, EnableDisable.Disable, contactFullName, 5, CheckBoxName.Download);
							if(isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is checked");
							}else {
								appLog.error("Download Check box Should be checked");
								sa.assertTrue(false, "Download Check box Should be checked");
							}

							if(!isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Disable");
							}else {
								appLog.error("Download Check box is not Disable.");
								sa.assertTrue(false, "Download Check box is not Disable.");
							}

						}else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is not Verified");
						}

						if (fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
							appLog.info("Click on Expand Icon of Select Contacts");

							ele=fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20);
							if(ele!=null) {
								ThreadSleep(3000);
								if(!clickUsingJavaScript(driver, ele, "check box : "+M17Contact1FirstName+" "+M17Contact1LastName)) {
//								if(click(driver, ele, "check box against : "+M17Contact1FirstName+" "+M17Contact1LastName, action.BOOLEAN)) {
									appLog.info("clicked on check box ");
									ThreadSleep(2000);
									if (isAlertPresent(driver)) {
										msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if (msg.trim().equals(alertMsg)) {
											appLog.info(
													"Alert Message is verified in contact access : "+msg);
										} else {
											appLog.error(
													"Alert Message is not verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
											sa.assertTrue(false,
													"Alert Message is not verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
										}

									} else {
										appLog.error("Alert is not present so cannot verify alert message "+alertMsg);
										sa.assertTrue(false, "Alert is not present so cannot verify alert message "+alertMsg);
									}
								}else {
									appLog.error("Not able to click on Checkbox");
									sa.assertTrue(false, "Not able to click on Checkbox of on Contact ");
								}

							}else{
								appLog.error("Checkbox Element is null for "+M17Contact1FirstName+" "+M17Contact1LastName);
								sa.assertTrue(false, "Checkbox Element is null for "+M17Contact1FirstName+" "+M17Contact1LastName);

							}


						}else{
							appLog.error("Not able to click on Expand Icon of Select Contacts");
							sa.assertTrue(false, "Not able to click on Expand Icon of Select Contacts");
						}

						ele = fp.getremoveToolTipInfo(Workspace.FundraisingWorkspace, M17Contact1EmailId, 10);
						if (mouseOverOperation(driver, ele)) {
							ThreadSleep(1000);
							appLog.info("Mouse Hover on info Icon");
							msg = ele.getAttribute("title").trim();
							if (msg.equals(FundsPageErrorMessage.toolTipinheritedPermissiononRemoveLink)) {
								appLog.info("Info Link Message is verified  : "+msg);
							} else {
								appLog.error(
										"Info Link Message is not verified in contact access Actual : "+msg+" \t Expected : "+FundsPageErrorMessage.toolTipinheritedPermissiononRemoveLink);
								sa.assertTrue(false,
										"Info Link Message is not verified in contact access Actual : "+msg+" \t Expected : "+FundsPageErrorMessage.toolTipinheritedPermissiononRemoveLink);
							}

						} else {
							appLog.error("Not able to perform Mouse Hover on info Icon");
							sa.assertTrue(false, "Not able to perform Mouse Hover on info Icon");
						}

						if (click(driver, fp.getCrossIconOnContactAccess(Workspace.FundraisingWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on Cross Icon");	
						} else {
							appLog.error("Not able to click on Cross Icon");
							sa.assertTrue(false, "Not able to click on Cross Icon");	
						}

					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
				}else {
					appLog.error("Folder Structure Not Verified  "+shdPath);
					sa.assertTrue(false, "Folder Structure Not Verified  "+shdPath);
				}

				if (fp.verifyFolderPathdummy(shdPath.split("/")[0], null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+shdPath.split("/")[0]);
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
						WebElement remove = fp.getcontactaccessremoveLink(Workspace.FundraisingWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
						if (remove!=null) {
							if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on remove link");
								if (click(driver, fp.getRemoveAccessClose(Workspace.FundraisingWorkspace, 30), "remove access close button", action.BOOLEAN)) {
									appLog.info("Clicked on remove access close button");

									if (click(driver, fp.getCrossIconOnContactAccess(Workspace.FundraisingWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on Cross Icon on Contact Access");	
									} else {
										appLog.error("clicked on Cross Icon on Contact Access");
										sa.assertTrue(false, "clicked on Cross Icon on Contact Access");	
									}

								}
								else {
									appLog.error("remove access close button is not clickable");
									sa.assertTrue(false, "remove access close button is not clickable");
								}
							}else {
								appLog.error("Not able to click on remove link of contact: "+M17Contact1EmailId);
								sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1EmailId);
							}
						}
						else {
							appLog.error("remove button adjacent to "+M17Contact1EmailId+" is not found");
							sa.assertTrue(false, "remove button adjacent to "+M17Contact1EmailId+" is not found");
						}
					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}

				}else {
					appLog.error("Folder Structure Not Verified  "+shdPath.split("/")[0]);
					sa.assertTrue(false, "Folder Structure Not Verified  "+shdPath.split("/")[0]);
				}	

			}else {
				appLog.error("Not able to click on created fund Name : "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name : "+M17FundName1);

			}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc030_inviteContactFromSubSharedFolderAndCreateSubFolderCheckPermission(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath).split(",");
		String errormsg=FundsPageErrorMessage.alreadyInvitedContactAlertMsg;
		String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		WebElement ele;
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				// invite contcat Shared sub Folder
				if(fp.inviteContact(environment,mode,null, M17Contact1EmailId,shdPath[0], FolderType.Shared, "download", "Yes", null,null, Workspace.FundraisingWorkspace, null)) {
					appLog.info(M17Contact1EmailId+" Contact is selected successfully ");
					ThreadSleep(2000);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
						ThreadSleep(5000);
						appLog.info("Verifying "+contactFullName+" in Selected Contacts grid.");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.info("Contact Grid Information related to "+contactFullName+ " with Remove Icon Enabled is Verified");

							ele = fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked by default.");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}

							if(!isEnabled(driver, ele, "View Checkbox")) {
								appLog.info("View Checkbox box is Disabled");
							}else {
								appLog.error("View Checkbox Should be Disabled");
								sa.assertTrue(false, "View Checkbox Should be Disabled");
							}

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
							if(isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is checked");
							}else {
								appLog.error("Download Check box Should be checked");
								sa.assertTrue(false, "Download Check box Should be checked");
							}

							if(isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Enabled");
							}else {
								appLog.error("Download Check box is not enabled.");
								sa.assertTrue(false, "Download Check box is not enabled.");
							}


						}else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Icon Enable is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Icon Enable is not Verified");
						}

						if(click(driver, fp.getContactAccessCancelBtn(Workspace.FundraisingWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("click on contact access pop up cancel button");
						}else {
							appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
							sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
						}
					}else {
						appLog.error("Not able to click on contact access icon so cannot verify select contact "+M17Contact1FirstName+" "+M17Contact1LastName);
						sa.assertTrue(false, "Not able to click on contact access icon so cannot verify select contact "+M17Contact1FirstName+" "+M17Contact1LastName);
					}
					
					// Create Sub Sub Shared Folder
					
					if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "Manage folder icon", action.SCROLLANDBOOLEAN)){
						if(fp.createFolderStructure(shdPath[1], FolderType.Shared, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(shdPath[1]+ " folder structure is created.");
							if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 20), "close button", action.SCROLLANDBOOLEAN)) {
								if(fp.verifyFolderPathdummy(shdPath[1], null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
									if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
										appLog.info("Going to Verify "+contactFullName+" with Remove Disable Link");
										if (fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, EnableDisable.Disable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
											appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Disable is Verified");

											ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace,EnableDisable.Disable, contactFullName, 5, CheckBoxName.View);
											if(isSelected(driver, ele, "View CheckBox")) {
												appLog.info("View Check box is checked ");
											}else {
												appLog.error("View Check box Should be checked");
												sa.assertTrue(false, "View Check box Should be checked");
											}

											if(!isEnabled(driver, ele, "View Icon")) {
												appLog.info("View Icon is disabled");
											}else {
												appLog.error("View Icon is should be disabled");
												sa.assertTrue(false, "View Icon is should be disabled");
											}

											ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, EnableDisable.Disable, contactFullName, 5, CheckBoxName.Download);
											if(isSelected(driver, ele, "Download / Print Documents CheckBox")) {
												appLog.info("Download Check box is checked");
											}else {
												appLog.error("Download Check box Should be checked");
												sa.assertTrue(false, "Download Check box Should be checked");
											}

											if(!isEnabled(driver, ele, "Download Icon")) {
												appLog.info("Download Check box is Disable");
											}else {
												appLog.error("Download Check box Should be Disable");
												sa.assertTrue(false, "Download Check box Should be Disable");
											}


										} else {
											appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Link Disable is not Verified");
											sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Link Disable is not Verified");

										}
										if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
											ThreadSleep(3000);
											if(!clickUsingJavaScript(driver, fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20), "check box : "+M17Contact1FirstName+" "+M17Contact1LastName)) {
//											if(click(driver, fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20), "Check Box", action.SCROLLANDBOOLEAN)) {
												ThreadSleep(2000);
												if (isAlertPresent(driver)) {
													String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
													switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
													if (msg.trim().equals(errormsg)) {
														appLog.info(
																"Error Message is verified in contact access : "+errormsg);
													} else {
														appLog.error(
																"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+errormsg);
														sa.assertTrue(false,
																"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+errormsg);
													}

												} else {
													appLog.error("Alert is not present so cannot verify alert message "+errormsg);
													sa.assertTrue(false, "Alert is not present so cannot verify alert message "+errormsg);
												}
												if(click(driver, fp.getContactAccessCancelBtn(Workspace.FundraisingWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
													appLog.info("click on contact access pop up cancel button");
												}else {
													appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
													sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
												}
											}else {
												appLog.error("Not able to click on Contact Name "+M17Contact1FirstName+" "+M17Contact1LastName+" so cannot check error message "+errormsg);
												sa.assertTrue(false, "Not able to click on Contact Name "+M17Contact1FirstName+" "+M17Contact1LastName+" so cannot check error message "+errormsg);
											}

										}else {
											appLog.error("Not able to expand contact access pop up so cannot verify error message "+errormsg);
											sa.assertTrue(false, "Not able to expand contact access pop up so cannot verify error message "+errormsg);
										}

									}else {
										appLog.error("Not able to click on contact access icon so cannot verify inherit contact access permission");
										sa.assertTrue(false, "Not able to click on contact access icon so cannot verify inherit contact access permission");
									}

								}else {
									appLog.error("Not able to click on folder structure "+shdPath[1]+" so cannot check inherit contact access permission on sub folder");
									sa.assertTrue(false, "Not able to click on folder structure "+shdPath[1]+" so cannot check inherit contact access permission on sub folder");
								}

							}else {
								appLog.error("Not able to click on manage folder close button");
								sa.assertTrue(false, "Not able to click on manage folder close button");
							}

						} else {
							appLog.error(shdPath[1]+ " folder structure is not created.");
							sa.assertTrue(false,shdPath[1]+ " folder structure is not created.");
						}
					}else {
						appLog.error("Not able to click on manage folder icon so cannot create sub folder");
						sa.assertTrue(false, "Not able to click on manage folder icon so cannot create sub folder");
					}
					
					if(fp.verifyFolderPathdummy(shdPath[2], null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
						if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
							ele=fp.noDataToDisplayContactAccess(Workspace.FundraisingWorkspace, 60);
							if (ele!=null) {
								String	msg =ele.getText().trim();
								if(msg.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
									appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message Verified");
								}else {
									appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
									sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
								}
							} else {
								appLog.error("No Data msg element is null");
								sa.assertTrue(false, "No Data msg element is null");	
							}

							if(click(driver, fp.getContactAccessCancelBtn(Workspace.FundraisingWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
								appLog.info("click on contact access pop up cancel button");
							}else {
								appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
								sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
							}

						}else {
							appLog.error("Not able to click on contact access icon so cannot check error message ");
							sa.assertTrue(false, "Not able to click on contact access icon so cannot check error message ");
						}
					}else {
						appLog.error("Not able to click on folder structure "+shdPath[2]+" so cannot check error message ");
						sa.assertTrue(false, "Not able to click on folder structure "+shdPath[2]+" so cannot check error message ");
					}
					
					switchToDefaultContent(driver);
					
					if(fp.inviteContact(environment,mode,null, M17Contact1EmailId,shdPath[2], FolderType.Shared, null, "Yes", null,null, Workspace.FundraisingWorkspace, null)) {
						appLog.info("Contact is invited "+M17Contact1FirstName+" "+M17Contact1LastName);
					}else {
						appLog.error("Not able to invite contact from folder "+shdPath[2]);
						sa.assertTrue(false, "Not able to invite contact from folder "+shdPath[2]);
					}

				}else {
					appLog.error("Not able to select Contact from contact access grid "+M17Contact1EmailId);
					sa.assertTrue(false, "Not able to select Contact from contact access grid "+M17Contact1EmailId);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc031_1_VerifyAccessFromAddedSharedChildFolderCRMSide(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		WebElement ele=null;
		String[] shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath).split(",");
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "FundraisingWorkspace view.");
				if (fp.verifyFolderPathdummy(shdPath[1], null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+shdPath[1]);
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	

						String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;

						appLog.info("Going to Verify "+contactFullName+" with Remove Disabled Link");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, EnableDisable.Disable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is Verified");

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace,EnableDisable.Disable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked as Contact is Invited From Parent Folder");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, EnableDisable.Disable, contactFullName, 5, CheckBoxName.Download);
							if(!isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is Unchecked");
							}else {
								appLog.error("Download Check box Should be Unchecked");
								sa.assertTrue(false, "Download Check box Should be Unchecked");
							}

							if(!isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Disabled");
							}else {
								appLog.error("Download Check box Should be Disabled");
								sa.assertTrue(false, "Download Check box Should be Disabled");
							}



						} else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is not Verified");

						}
						appLog.info("Going to Verify "+contactFullName+" with Remove Enabled Link");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Icon Enable is Verified");

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked as Contact is Invited From Parent Folder");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
							if(isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is checked");
							}else {
								appLog.error("Download Check box Should be checked");
								sa.assertTrue(false, "Download Check box Should be checked");
							}

							if(isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Enabled");
							}else {
								appLog.error("Download Check box Should be Enabled");
								sa.assertTrue(false, "Download Check box Should be Enabled");
							}


						} else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");

						}

						if (click(driver, fp.getCrossIconOnContactAccess(Workspace.FundraisingWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on Cross Icon");	
						} else {
							appLog.error("Not able to click on Cross Icon");
							sa.assertTrue(false, "Not able to click on Cross Icon");	
						}

					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
				}else {
					appLog.error("Folder Structure Not Verified  "+shdPath[1]);
					sa.assertTrue(false, "Folder Structure Not Verified  "+shdPath[1]);
				}


			}else {
				appLog.error("Not able to click on created fund Name : "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name : "+M17FundName1);

			}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		switchToDefaultContent(driver);


		if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));

				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");

				for (int i=0;i<shdPath.length;i++) {

					if (fp.verifyFolderPathdummy(shdPath[i], null, null, M17FundName1, PageName.ContactsPage,
							Workspace.FundraisingWorkspace, 60)) {
						appLog.info("Folder Structure verified on Contact Page : "+shdPath[i]);
						if ( cp.getViewAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
							appLog.info("View access icon is Enabled for : "+shdPath[i]);
						}
						else {
							appLog.error("View access icon is Should be Enabled for : "+shdPath[i]);
							sa.assertTrue(false,"View access icon is Should be Enabled for : "+shdPath[i]);
						}


						if (i!=0) {

							if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
								appLog.info("Download access icon is Enabled for : "+shdPath[i]);
							}
							else {
								appLog.error("Download access icon is Should be Enabled for : "+shdPath[i]);
								sa.assertTrue(false,"Download access icon is Should be Enabled for : "+shdPath[i]);
							}

						} else {

							if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 5)==null) {
								appLog.info("Download access icon is Disabled for : "+shdPath[i]);
							}
							else {
								appLog.error("Download access icon is Should be Disabled for : "+shdPath[i]);
								sa.assertTrue(false,"Download access icon is Should be Disabled for : "+shdPath[i]);
							}
						}

						if ( cp.getUploadAccessIcon(Workspace.FundraisingWorkspace, 2)==null) {
							appLog.info("Upload access icon is Disabled for : "+shdPath[i]);
						}
						else {
							appLog.error("Upload access icon is Should be Disabled for : "+shdPath[i]);
							sa.assertTrue(false,"Upload access icon is Should be Disabled for : "+shdPath[i]);
						}


					}
					else {
						appLog.error("shared path is not found on contacts page folder structure : "+shdPath[i]);
						sa.assertTrue(false, "shared path is not found on contacts page folder structure : "+shdPath[i]);
					}

				}

			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}

		switchToDefaultContent(driver);

		lp.CRMlogout();
		sa.assertAll();

}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc031_2_VerifyAccessFromAddedSharedChildFolderInvestoride() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String[] allFolders=(cmnPath+","+stdPath+","+shdPath).split(",");
		String parentID;
		String alertMsg=InvestorFirmPageErrorMessage.LimitedAccessErrorMessageBulkDownload;
		String msg ;
		boolean flag=true;

		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {

			for (int i = 0; i < allFolders.length; i++) {

				if (fp.verifyFolderPathdummy(allFolders[i], null, null, null, PageName.PotentialInvestmentPage,
						null, 20)) {
					appLog.info(" Folder Structure Verified: " + allFolders[i]);

				}else {
					appLog.error("could not find folder structure "+allFolders[i]);
					sa.assertTrue(false, "could not find folder structure "+allFolders[i]);
				}

			}

			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");

				parentID=switchOnWindow(driver);

				if (parentID!=null) {

					for (int i = 0; i < allFolders.length; i++) {

						if (ifp.verifyBulkDownLoadFolderStructure(allFolders[i], 20)) {
							appLog.info(" Folder Structure Verified on Bulk DownLoad: " + allFolders[i]);
						}else {
							appLog.error("could not find folder structure on Bulk DownLoad"+allFolders[i]);
							sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+allFolders[i]);
						}

					}

					String[] sharedFolder = shdPath.split("/");

					for (int i = 0; i < sharedFolder.length; i++) {

						if(i==0){
							if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(sharedFolder[i], 10)) {
								appLog.info("Clicked on CheckBox for Folder: "+sharedFolder[i]);	
								ThreadSleep(2000);
								if (isAlertPresent(driver)) {
									msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if (msg.trim().contains(alertMsg)) {
										appLog.info("Error Message is verified in contact access : "+alertMsg);
									} else {
										appLog.error("Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
										sa.assertTrue(false,"Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
									}
								} else {
									appLog.error("Alert is not present so cannot verify alert message "+alertMsg);
									sa.assertTrue(false, "Alert is not present so cannot verify alert message "+alertMsg);
								}
							} else {
								sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+sharedFolder[i]);
								appLog.error("Not Able to Click on CheckBox for Folder: "+sharedFolder[i]);
							}	
						}else{
							if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(sharedFolder[sharedFolder.length-i], 10)) {
								appLog.info("Clicked on CheckBox for Folder: "+sharedFolder[sharedFolder.length-i]);	
							} else {
								flag=false;
								appLog.error("Not Able to Click on CheckBox for Folder: "+sharedFolder[sharedFolder.length-i]);
								sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+sharedFolder[sharedFolder.length-i]);

							}
						}
					}


					if (flag) {
						appLog.info("Able to Clicked on CheckBox for Sub and Sub-Sub Shared Folder");	
						if (click(driver, ifp.getBulkDownloadDownloadButton(10), "DownLoad Button", action.BOOLEAN)) {

							if (click(driver, ifp.getBulkDownloadConfirmationPopUpYesBtn(30),
									"Bulk Download Yes Button", action.BOOLEAN)) {
								appLog.error("Clicked on Yes Button.");

								Robot rob;
								try {
									rob = new Robot();
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
								} catch (AWTException e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
								ThreadSleep(20000);
								driver.close();
								driver.switchTo().window(parentID);
								// ele.sendKeys(Keys.CONTROL+"j");
								parentID = switchOnWindow(driver);
								if (parentID == null) {
									try {
										rob = new Robot();
										rob.keyPress(KeyEvent.VK_TAB);
										rob.keyRelease(KeyEvent.VK_TAB);
										rob.keyPress(KeyEvent.VK_ENTER);
										rob.keyRelease(KeyEvent.VK_ENTER);
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_CONTROL);
										rob.keyPress(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_CONTROL);
										parentID = switchOnWindow(driver);
									} catch (AWTException e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}
								}

								ThreadSleep(5000);
								String fileName = ifp.getDownloadedFileName();
								if (fileName.contains(M17FundName1 + " " + getSystemDate("MM-dd-YYYY"))) {
									appLog.info("file name is successfully matched. " + fileName);
								} else {
									appLog.info("File name is not the matched. Expected: " + M17FundName1 + " "
											+ getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
									sa.assertTrue(false, "File name is not the matched. Expected: " + M17FundName1
											+ " " + getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
								}
								driver.close();
								driver.switchTo().window(parentID);

							} else {
								appLog.error("Not Able to Click on Yes Button.");
								sa.assertTrue(false, "Not Able to Click on Yes Button.");
							}


						} else {
							appLog.error("Download Button is not working.");
							sa.assertTrue(false, "Download Button is not working.");
						}


					} else {
						appLog.error("Not Able to Clicked on CheckBox for Sub and Sub-Sub Shared Folder So Skipping Download Functionality");
						sa.assertTrue(false, "Not Able to Clicked on CheckBox for Sub and Sub-Sub Shared Folder So Skipping Download Functionality");

					}	


				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}


			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}


		}else{
			appLog.error("Not Able to Click on "+investorSideWorkSpace.PotentialInvestment);
			sa.assertTrue(false, "Not Able to Click on "+investorSideWorkSpace.PotentialInvestment);	
		}



		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc032_1_ChangePermissionToSharedFolderTheInvitedContactINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String[] shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath).split(",");
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");
				for (int i = shdPath.length-2; i >= 0; i--) {

					if (fp.verifyFolderPathdummy(shdPath[i], null, null, null, PageName.FundsPage,Workspace.FundraisingWorkspace,60)) {
						appLog.info("Folder structure verified : "+shdPath);
						if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
							ThreadSleep(2000);
							if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download),"DownLoad Check Box",action.BOOLEAN)) {
								appLog.info("Click on DownLoad CheckBox");
								if(click(driver, fp.getApplyBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" apply button", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on apply button successfully");
									if(click(driver, fp.getCloseBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" close button", action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on Close Buton");	
									}else {
										appLog.error("Not able to click on close button");
										sa.assertTrue(false, "Not able to click on close button");
									}
								}else{
									appLog.error("Not able to Click on apply button");
									sa.assertTrue(false, "Not able to Click on apply button")	;
								}
							} else {
								appLog.error("Not able to click on DownLoad Documents CheckBox for Contact : "+M17Contact1EmailId);
								sa.assertTrue(false, "Not able to click on DownLoad Documents CheckBox  for Contact : "+M17Contact1EmailId);
							}
						}else {
							appLog.error("Not able to click on contact access icon on folder "+shdPath[i]);
							sa.assertTrue(false, "Not able to click on contact access icon on folder "+shdPath[i]);
						}

					}else {
						appLog.error("Not able to click on folder structure "+shdPath[i]);
						sa.assertTrue(false, "Not able to click on folder structure "+shdPath[i]);
					}

				}


			}else {
				appLog.error("Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
				sa.assertTrue(false, "Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
			}

		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
		}
		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");

				for (int i = 0; i < shdPath.length; i++) {

					if (fp.verifyFolderPathdummy(shdPath[i], null, null, M17FundName1, PageName.ContactsPage,
							Workspace.FundraisingWorkspace, 60)) {

						if ( cp.getViewAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
							appLog.info("View access icon is Enabled for : "+shdPath[i]);
						}
						else {
							appLog.error("View access icon is Should be Enabled for : "+shdPath[i]);
							sa.assertTrue(false,"View access icon is Should be Enabled for : "+shdPath[i]);
						}

						if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
							appLog.info("Download access icon is Enabled for : "+shdPath[i]);
						}
						else {
							appLog.error("Download access icon is Should be Enabled for : "+shdPath[i]);
							sa.assertTrue(false,"Download access icon is Should be Enabled for : "+shdPath[i]);
						}

					}
					else {
						appLog.error("folder path is not found on contacts page folder structure : "+shdPath);
						sa.assertTrue(false, "folder path is not found on contacts page folder structure : "+shdPath);
					}


				}


			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc032_2_VerifyFolderStructureInInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {

			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");

				String parentID = switchOnWindow(driver);

				if (parentID!=null) {


					if (ifp.verifyBulkDownLoadFolderStructure(cmnPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + cmnPath);

						if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(cmnPath, 10)) {
							appLog.info("Clicked on CheckBox for Folder: "+cmnPath);		
						}else{
							appLog.error("Not Able to Click on CheckBox for Folder: "+cmnPath);
							sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+cmnPath);	
						}

					}else {
						appLog.error("could not find folder structure on Bulk DownLoad: "+cmnPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+cmnPath);
					}

					if (ifp.verifyBulkDownLoadFolderStructure(stdPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + stdPath);

						if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(stdPath, 10)) {
							appLog.info("Clicked on CheckBox for Folder: "+stdPath);		
						}else{
							appLog.error("Not Able to Click on CheckBox for Folder: "+stdPath);
							sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+stdPath);	
						}

					}else {
						appLog.error("could not find folder structure on Bulk DownLoad: "+cmnPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+cmnPath);
					}


					if (ifp.verifyBulkDownLoadFolderStructure(shdPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + shdPath);

						String[] folders = shdPath.split("/");

						for (int i=folders.length-1;i>=0;i--) {
							if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(folders[i], 10)) {
								appLog.info("Clicked on CheckBox for Folder: "+folders[i]);		
							}else{
								appLog.error("Not Able to Click on CheckBox for Folder: "+folders[i]);
								sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+folders[i]);	
							}	
						}


					}else {
						appLog.error("could not find folder structure on Bulk DownLoad"+shdPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+shdPath);
					}

					if (click(driver, ifp.getBulkDownloadDownloadButton(10), "DownLoad Button", action.BOOLEAN)) {


						if (click(driver, ifp.getBulkDownloadConfirmationPopUpYesBtn(30),
								"Bulk Download Yes Button", action.BOOLEAN)) {
							appLog.error("Clicked on Yes Button.");


							Robot rob;
							try {
								rob = new Robot();
								ThreadSleep(10000);
								rob.keyPress(KeyEvent.VK_ENTER);
								rob.keyRelease(KeyEvent.VK_ENTER);
								ThreadSleep(10000);
								rob.keyPress(KeyEvent.VK_CONTROL);
								rob.keyPress(KeyEvent.VK_J);
								rob.keyRelease(KeyEvent.VK_J);
								rob.keyRelease(KeyEvent.VK_CONTROL);
							} catch (AWTException e) {
								// TODO Auto-generated catch
								// block
								e.printStackTrace();
							}
							ThreadSleep(20000);
							driver.close();
							driver.switchTo().window(parentID);
							// ele.sendKeys(Keys.CONTROL+"j");
							parentID = switchOnWindow(driver);
							if (parentID == null) {
								try {
									rob = new Robot();
									rob.keyPress(KeyEvent.VK_TAB);
									rob.keyRelease(KeyEvent.VK_TAB);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
									parentID = switchOnWindow(driver);
								} catch (AWTException e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
							}

							ThreadSleep(5000);
							String fileName = ifp.getDownloadedFileName();
							if (fileName.contains(M17FundName1 + " " + getSystemDate("MM-dd-YYYY"))) {
								appLog.info("file name is successfully matched. " + fileName);
							} else {
								appLog.info("File name is not the matched. Expected: " + M17FundName1 + " "
										+ getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
								sa.assertTrue(false, "File name is not the matched. Expected: " + M17FundName1
										+ " " + getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
							}
							driver.close();
							driver.switchTo().window(parentID);

						} else {
							appLog.error("Not Able to Click on Yes Button.");
							sa.assertTrue(false, "Not Able to Click on Yes Button.");
						}


					} else {
						appLog.error("Download Button is not working.");
						sa.assertTrue(false, "Download Button is not working.");
					}


				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}


			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}


		}else {
			appLog.error("Not able to click on "+investorSideWorkSpace.PotentialInvestment);
			sa.assertTrue(false, "Not able to click on "+investorSideWorkSpace.PotentialInvestment);
		}
		switchToDefaultContent(driver);
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc033_1_RemoveTheAccessFromInvitedParentFolderActionCRMSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String shdFolderafterRemovel = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.KeyWord_For_Search);
		String shdFolders = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String[] shdPath = shdFolders.split(",");
		String[] allFolder = (cmnPath+","+shdFolderafterRemovel).split(",");

		WebElement ele;
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
		if (cp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "workspace section");
				if (fp.verifyFolderPathdummy(shdPath[0], null, null, null, PageName.FundsPage,Workspace.FundraisingWorkspace,60)) {
					appLog.error("folder path verified : "+shdPath[0]);
					if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
						WebElement remove = fp.getcontactaccessremoveLink(Workspace.FundraisingWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
						if (remove!=null) {
							if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on remove link for : "+contactFullName);
								ThreadSleep(5000);
								if (click(driver, fp.getRemoveAccessClose(Workspace.FundraisingWorkspace, 30), "remove access close button", action.BOOLEAN)) {
									appLog.error("Clicked on remove access close button");	

									ele=fp.noDataToDisplayContactAccess(Workspace.FundraisingWorkspace, 60);
									if (ele!=null) {
										String	msg =ele.getText().trim();
										if(msg.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
											appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message Verified");
										}else {
											appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
											sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
										}
									} else {
										appLog.error("No Data msg element is null");
										sa.assertTrue(false, "No Data msg element is null");	
									}

									if (click(driver, fp.getContactAccessCrossIcon(Workspace.FundraisingWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on Contact access Cross Icon");
									} else {
										appLog.error("Contact access Cross Icon is not clickable");
										sa.assertTrue(false, "Contact access close button is not clickable");
									}


									// Verify SubFolder after Removing Access From Parent Folder

									if (fp.verifyFolderPathdummy(shdPath[1], null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
										appLog.info("Folder Structure Verified  "+shdPath[1]);
										if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on Contact Access Icon");	


											appLog.info("Going to Verify "+contactFullName+" with Remove Disabled Link");
											if (!fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, EnableDisable.Disable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
												appLog.info("Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is not displaying and Hence Verified");

											} else {
												appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is displaying and Hence Not Verified");
												sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is displaying and Hence Not Verified");

											}
											appLog.info("Going to Verify "+contactFullName+" with Remove Enabled Link");
											if (fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
												appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Enable is Verified");

											} else {
												appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
												sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");

											}

											if (click(driver, fp.getCrossIconOnContactAccess(Workspace.FundraisingWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
												appLog.info("clicked on Cross Icon");	
											} else {
												appLog.error("Not able to click on Cross Icon");
												sa.assertTrue(false, "Not able to click on Cross Icon");	
											}

										}else {
											appLog.error("Not able to click on Contact Access Icon");
											sa.assertTrue(false, "Not able to click on Contact Access Icon");	
										}
									}else {
										appLog.error("Folder Structure Not Verified  "+shdPath[1]);
										sa.assertTrue(false, "Folder Structure Not Verified  "+shdPath[1]);
									}

								}
								else {
									appLog.error("remove access close button is not clickable");
									sa.assertTrue(false, "remove access close button is not clickable");
								}

							}else {
								appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
								sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
							}
						}
						else {
							appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
							sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
						}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}
				}
				else {
					appLog.error("folder path is not found : "+shdPath[0]);
					sa.assertTrue(false, "folder path is not found : "+shdPath[0]);
				}
			}
			else {
				appLog.error(M17FundName1+" is not found on funds page");
				sa.assertTrue(false, M17FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		switchToDefaultContent(driver);
			if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");

				for (int i=0;i<allFolder.length;i++) {

					if (fp.verifyFolderPathdummy(allFolder[i], null, null, M17FundName1, PageName.ContactsPage,
							Workspace.FundraisingWorkspace, 60)) {

						if ( cp.getViewAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
							appLog.info("View access icon is Enabled for : "+allFolder[i]);
						}
						else {
							appLog.error("View access icon is Should be Enabled for : "+allFolder[i]);
							sa.assertTrue(false,"View access icon is Should be Enabled for : "+allFolder[i]);
						}

						if (i==0) {
						
							if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
								appLog.info("Download access icon is Enabled for : "+allFolder[i]);
							}
							else {
								appLog.error("Download access icon is Should be Enabled for : "+allFolder[i]);
								sa.assertTrue(false,"Download access icon is Should be Enabled for : "+allFolder[i]);
							}
							
						} else {
							if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 5)==null) {
								appLog.info("Download access icon is Disable for : "+allFolder[i]);
							}
							else {
								appLog.error("Download access icon is Should be Disable for : "+allFolder[i]);
								sa.assertTrue(false,"Download access icon is Should be Disable for : "+allFolder[i]);
							}
						}
						
					}
					else {
						appLog.error("common path is not found on contacts page folder structure : "+allFolder[i]);
						sa.assertTrue(false, "common path is not found on contacts page folder structure : "+allFolder[i]);
					}

				}

			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc033_2_VerifyFolderStructureInInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String[] allFolders=(cmnPath+","+shdPath).split(",");
		String parentID;
		String alertMsg=InvestorFirmPageErrorMessage.LimitedAccessErrorMessageBulkDownload;
		String msg ;

		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {

			for (int i = 0; i < allFolders.length; i++) {

				if (fp.verifyFolderPathdummy(allFolders[i], null, null, null, PageName.PotentialInvestmentPage,
						null, 20)) {
					appLog.info(" Folder Structure Verified: " + allFolders[i]);

				}else {
					appLog.error("could not find folder structure "+allFolders[i]);
					sa.assertTrue(false, "could not find folder structure "+allFolders[i]);
				}

			}

			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");

				parentID=switchOnWindow(driver);

				if (parentID!=null) {

					for (int i = 0; i < allFolders.length; i++) {

						if (ifp.verifyBulkDownLoadFolderStructure(allFolders[i], 20)) {
							appLog.info(" Folder Structure Verified on Bulk DownLoad: " + allFolders[i]);
						}else {
							appLog.error("could not find folder structure on Bulk DownLoad"+allFolders[i]);
							sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+allFolders[i]);
						}

					}

					String[] sharedFolder = shdPath.split("/");

					for (int i = 0; i < sharedFolder.length; i++) {

						if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(sharedFolder[i], 10)) {
							appLog.info("Clicked on CheckBox for Folder: "+sharedFolder[i]);	
							ThreadSleep(2000);
							if (isAlertPresent(driver)) {
								msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								if (msg.trim().contains(alertMsg)) {
									appLog.info("Error Message is verified in contact access : "+alertMsg);
								} else {
									appLog.error("Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
									sa.assertTrue(false,"Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
								}
							} else {
								appLog.error("Alert is not present so cannot verify alert message "+alertMsg);
								sa.assertTrue(false, "Alert is not present so cannot verify alert message "+alertMsg);
							}
						} else {
							sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+sharedFolder[i]);
							appLog.error("Not Able to Click on CheckBox for Folder: "+sharedFolder[i]);
						}	

					}

					driver.close();
					driver.switchTo().window(parentID);

				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}


			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}


		}else{
			appLog.error("Not Able to Click on "+investorSideWorkSpace.PotentialInvestment);
			sa.assertTrue(false, "Not Able to Click on "+investorSideWorkSpace.PotentialInvestment);	
		}

		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc034_1_InviteContactFromSharedParentFolderAndRemoveAccessFromSubSharedFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String[] shdPath=ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath).split(",");
		String errormsg=FundsPageErrorMessage.alreadyInvitedContactAlertMsg;
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {

				if(fp.inviteContact(environment,mode,null, M17Contact1EmailId,shdPath[0], FolderType.Shared, "Download", "Yes", null,null, Workspace.FundraisingWorkspace, null)) {
					appLog.info(M17Contact1EmailId+" Contact is invited successfully from folder "+shdPath[0]);
				}else {
					appLog.error("Not able to invite contact from parent folder "+shdPath[0]);
					sa.assertTrue(false, "Not able to invite contact from parent folder "+shdPath[0]);
				}

				switchToFrame(driver, 30, cp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fund page investor workspace section");

				if (fp.verifyFolderPathdummy(shdPath[1], null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+shdPath[1]);
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
						WebElement remove = fp.getcontactaccessremoveLink(Workspace.FundraisingWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
						if (remove!=null) {
							if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on remove link");
								if (click(driver, fp.getRemoveAccessClose(Workspace.FundraisingWorkspace, 30), "remove access close button", action.BOOLEAN)) {
									appLog.info("Clicked on remove access close button");
									ThreadSleep(3000);

									if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
										ThreadSleep(3000);
										if(!clickUsingJavaScript(driver, fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20), "check box : "+M17Contact1FirstName+" "+M17Contact1LastName)) {
//										if(click(driver, fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20), "Check Box", action.SCROLLANDBOOLEAN)) {
											ThreadSleep(2000);
											if (isAlertPresent(driver)) {
												String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
												switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
												if (msg.trim().equals(errormsg)) {
													appLog.info("Error Message is verified in contact access : "+errormsg);
												} else {
													appLog.error("Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+errormsg);
													sa.assertTrue(false,"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+errormsg);
												}

											} else {
												appLog.error("Alert is not present so cannot verify alert message "+errormsg);
												sa.assertTrue(false, "Alert is not present so cannot verify alert message "+errormsg);
											}
											if(click(driver, fp.getContactAccessCancelBtn(Workspace.FundraisingWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
												appLog.info("click on contact access pop up cancel button");
											}else {
												appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
												sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
											}
										}else {
											appLog.error("Not able to click on Contact Name "+M17Contact1FirstName+" "+M17Contact1LastName+" so cannot check error message "+errormsg);
											sa.assertTrue(false, "Not able to click on Contact Name "+M17Contact1FirstName+" "+M17Contact1LastName+" so cannot check error message "+errormsg);
										}

									}else {
										appLog.error("Not able to expand contact access pop up so cannot verify error message "+errormsg);
										sa.assertTrue(false, "Not able to expand contact access pop up so cannot verify error message "+errormsg);
									}

								}
								else {
									appLog.error("remove access close button is not clickable");
									sa.assertTrue(false, "remove access close button is not clickable");
								}
							}else {
								appLog.error("Not able to click on remove link of contact: "+M17Contact1EmailId);
								sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1EmailId);
							}
						}
						else {
							appLog.error("remove button adjacent to "+M17Contact1EmailId+" is not found");
							sa.assertTrue(false, "remove button adjacent to "+M17Contact1EmailId+" is not found");
						}
					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}

				}else {
					appLog.error("Folder Structure Not Verified  "+shdPath[1]);
					sa.assertTrue(false, "Folder Structure Not Verified  "+shdPath[1]);
				}

			}else {
				appLog.error("Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
				sa.assertTrue(false, "Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
			}

		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
		}

		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");
				for (int i=0;i<shdPath.length;i++) {

					if (fp.verifyFolderPathdummy(shdPath[i], null, null, M17FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 60)) {

						if ( cp.getViewAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
							appLog.info("View access icon is Enabled for : "+shdPath[i]);
						}
						else {
							appLog.error("View access icon is Should be Enabled for : "+shdPath[i]);
							sa.assertTrue(false,"View access icon is Should be Enabled for : "+shdPath[i]);
						}

						if ( cp.getDownloadAccessIcon(Workspace.FundraisingWorkspace, 5)!=null) {
							appLog.info("Download access icon is Enabled for : "+shdPath[i]);
						}
						else {
							appLog.error("Download access icon is Should be Enabled for : "+shdPath[i]);
							sa.assertTrue(false,"Download access icon is Should be Enabled for : "+shdPath[i]);
						}

						if ( cp.getUploadAccessIcon(Workspace.FundraisingWorkspace, 1)==null) {
							appLog.info("Upload access icon is Disabled for : "+shdPath[i]);
						}
						else {
							appLog.error("Upload access icon is Should be Disabled for : "+shdPath[i]);
							sa.assertTrue(false,"Upload access icon is Should be Disabled for : "+shdPath[i]);
						}

					}

					else {
						appLog.error("folder path is not found on contacts page folder structure : "+shdPath[i]);
						sa.assertTrue(false, "folder path is not found on contacts page folder structure : "+shdPath[i]);
					}
				}
			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc034_2_VerifyFolderStructureInInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {

			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");

				String parentID = switchOnWindow(driver);

				if (parentID!=null) {

					if (ifp.verifyBulkDownLoadFolderStructure(shdPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + shdPath);

						if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(shdPath, 10)) {
							appLog.info("Clicked on CheckBox for Folder: "+shdPath);	


							if (click(driver, ifp.getBulkDownloadDownloadButton(10), "DownLoad Button", action.BOOLEAN)) {


								if (click(driver, ifp.getBulkDownloadConfirmationPopUpYesBtn(30),
										"Bulk Download Yes Button", action.BOOLEAN)) {
									appLog.error("Clicked on Yes Button.");


									Robot rob;
									try {
										rob = new Robot();
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_ENTER);
										rob.keyRelease(KeyEvent.VK_ENTER);
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_CONTROL);
										rob.keyPress(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_CONTROL);
									} catch (AWTException e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}
									ThreadSleep(20000);
									driver.close();
									driver.switchTo().window(parentID);
									// ele.sendKeys(Keys.CONTROL+"j");
									parentID = switchOnWindow(driver);
									if (parentID == null) {
										try {
											rob = new Robot();
											rob.keyPress(KeyEvent.VK_TAB);
											rob.keyRelease(KeyEvent.VK_TAB);
											rob.keyPress(KeyEvent.VK_ENTER);
											rob.keyRelease(KeyEvent.VK_ENTER);
											ThreadSleep(10000);
											rob.keyPress(KeyEvent.VK_CONTROL);
											rob.keyPress(KeyEvent.VK_J);
											rob.keyRelease(KeyEvent.VK_J);
											rob.keyRelease(KeyEvent.VK_CONTROL);
											parentID = switchOnWindow(driver);
										} catch (AWTException e) {
											// TODO Auto-generated catch
											// block
											e.printStackTrace();
										}
									}

									ThreadSleep(5000);
									String fileName = ifp.getDownloadedFileName();
									if (fileName.contains(M17FundName1 + " " + getSystemDate("MM-dd-YYYY"))) {
										appLog.info("file name is successfully matched. " + fileName);
									} else {
										appLog.info("File name is not the matched. Expected: " + M17FundName1 + " "
												+ getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
										sa.assertTrue(false, "File name is not the matched. Expected: " + M17FundName1
												+ " " + getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
									}
									driver.close();
									driver.switchTo().window(parentID);

								} else {
									appLog.error("Not Able to Click on Yes Button.");
									sa.assertTrue(false, "Not Able to Click on Yes Button.");
								}


							} else {
								appLog.error("Download Button is not working.");
								sa.assertTrue(false, "Download Button is not working.");
							}


						}else{
							appLog.error("Not Able to Click on CheckBox for Folder: "+shdPath);
							sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+shdPath);	
						}	



					}else {
						appLog.error("could not find folder structure on Bulk DownLoad"+shdPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+shdPath);
					}


				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}


			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}


		}else {
			appLog.error("Not able to click on "+investorSideWorkSpace.PotentialInvestment);
			sa.assertTrue(false, "Not able to click on "+investorSideWorkSpace.PotentialInvestment);
		}
		switchToDefaultContent(driver);
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc035_1_RemoveContactAccessActionFromContactDetailPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String shdPath=ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		String	msg;
		String errorMsg=ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace;
		WebElement ele = null;
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
						"Fundraising Workspace Section view");
				if (click(driver, cp.getRemoveContactAccessButton(Workspace.FundraisingWorkspace, 60),
						"Remove contact access close button", action.SCROLLANDBOOLEAN)) {
					ele = FindElement(driver, "//label[text()='" + M17FundName1 + "']/../..//a[@title='Remove']",M17FundName1+" Remove link", action.SCROLLANDBOOLEAN, 60);
					if (click(driver, ele, "Remove Link", action.SCROLLANDBOOLEAN)) {
						String ParentID = switchOnWindow(driver);
						if (ParentID != null) {
							ThreadSleep(5000);
							switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
							driver.switchTo().window(ParentID);
							ThreadSleep(2000);
							refresh(driver);
							ThreadSleep(2000);
							switchToFrame(driver, 30, bp.getFrame( PageName.ContactsPage, 30));
							scrollDownThroughWebelement(driver,bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),Workspace.FundraisingWorkspace+" Workspace Section view");
							ele=cp.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60);
							
							if (ele!=null) {
								msg = ele.getText().trim();
								
								if (errorMsg.equals(msg)) {
								appLog.info(msg+" on contatct Page Verified ");	
								} else {
									appLog.error("Error Msg on contatct Page Not Verified Actual : "+msg+"\t Expected : "+errorMsg);	
									sa.assertTrue(false, "Error Msg on contatct Page Not Verified Actual : "+msg+"\t Expected : "+errorMsg);
								}
								
							} else {
								appLog.error("Contact Page Error Msg Ele is null");
								sa.assertTrue(false, "Contact Page Error Msg Ele is null");
							}
							
						} else {
							appLog.info("No new window to switch");
							sa.assertTrue(false, "No new window to switch");
						}
					} else {
						appLog.info("Not bale ot click on remove link");
						sa.assertTrue(false, "Not able to click on remove link");
					}
				} else {
					appLog.info("Not able to clcik on remove contact access close button");
					sa.assertTrue(false, "Not able to click on remove contact access close button");
				}
			} else {
				appLog.info("Not able to click on created contact : "+M17Contact1FirstName+" "+M17Contact1LastName);
				sa.assertTrue(false, "Not able to click on created contact : "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		} else {
			appLog.info("Not able to click on contacts tab");
			sa.assertTrue(false, "Not able to click on contacts tab");
		}

		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "workspace section");
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage,Workspace.FundraisingWorkspace,60)) {
					appLog.error("folder path verified : "+shdPath);
					if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
						ThreadSleep(3000);

						ele=fp.noDataToDisplayContactAccess(Workspace.FundraisingWorkspace, 60);
						if (ele!=null) {
								msg =ele.getText().trim();
							if(msg.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
								appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message Verified");
							}else {
								appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
								sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
							}
						} else {
							appLog.error("No Data msg element is null");
							sa.assertTrue(false, "No Data msg element is null");	
						}

						if (click(driver, fp.getContactAccessCrossIcon(Workspace.FundraisingWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Contact access Cross Icon");
						} else {
							appLog.error("Contact access Cross Icon is not clickable");
							sa.assertTrue(false, "Contact access close button is not clickable");
						}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}
				}
				else {
					appLog.error("folder path is not found : "+shdPath);
					sa.assertTrue(false, "folder path is not found : "+shdPath);
				}
			}
			else {
				appLog.error(M17FundName1+" is not found on funds page");
				sa.assertTrue(false, M17FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}


		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();

	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc035_2_RemoveContactAccessImpactonInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		
		//Investor Side
		
		List<WebElement> dropdownList=allOptionsInDropDrop(driver, allfp.getFirmNameDropdown(60), "Drop Down List");
		List<String> result=compareMultipleList(driver, Org1FirmName, dropdownList);
		
		if (!result.isEmpty()) {
			appLog.info("Firm Name is not available in DropDown after removing Acces");	
		} else {
			appLog.error("Firm Name is available in DropDown after removing Acces");
			sa.assertTrue(false, "Firm Name is available in DropDown after removing Acces");
		}
		lp.investorLogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc036_1_buildFundraisingWorkspaceAndInviteContactForFund2(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);

		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {

				appLog.info("FundraisingWorkspace is build successfully on fund : "+M17FundName2);
				if(fp.inviteContact(environment,mode, M17Institution1, M17Contact4EmailId,null, FolderType.Standard,null,"Yes", "Yes","All Folders", Workspace.FundraisingWorkspace, M17Contact4FirstName+" "+M17Contact4LastName)) {
					appLog.info("contact is invites successfully from FundraisingWorkspace: "+M17Contact4FirstName+" "+M17Contact4LastName);
				}else {
					appLog.error("Not able to invite contact from FundraisingWorkspacee: "+M17Contact4FirstName+" "+M17Contact4LastName);
					sa.assertTrue(false, "Not able to invite contact from FundraisingWorkspace: "+M17Contact4FirstName+" "+M17Contact4LastName);
				}

			}else{
				appLog.error("Not able to click on Created Fund: "+M17FundName1);
				sa.assertTrue(false, "Not able to click on Created Fund: "+M17FundName1);	
			}

		}else {
			appLog.error("Not able to click on fund tab ");
			sa.assertTrue(false, "Not able to click on fund tab ");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName2)) {
				String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund2", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund2", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund2", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund2", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund2", excelLabel.Fund_Email);
				String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund2", excelLabel.Fund_Description);
				String templateName=ExcelUtils.readData("Foldertemp", 0, 1);
				String[] data= {Size,vintageyear,contact,phone,email,description};
				String shdPath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);


				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,null, Workspace.FundraisingWorkspace,60)) {

					appLog.info("FundraisingWorkspace is build successfully on fund : "+M17FundName2);
					if(fp.inviteContact(environment,mode, null, M17Contact4EmailId,shdPath, FolderType.Shared,null,"Yes", "Yes","All Folders", Workspace.FundraisingWorkspace, M17Contact4LastName)) {
						appLog.info("contact is invites successfully from FundraisingWorkspace: "+M17Contact4FirstName+" "+M17Contact4LastName);
					}else {
						appLog.error("Not able to invite contact from FundraisingWorkspacee: "+M17Contact4FirstName+" "+M17Contact4LastName);
						sa.assertTrue(false, "Not able to invite contact from FundraisingWorkspace: "+M17Contact4FirstName+" "+M17Contact4LastName);
					}


				}else {
					appLog.error("Not able to bulid FundraisingWorkspace on fund: "+M17FundName2);
					sa.assertTrue(false, "Not able to bulid FundraisingWorkspace on fund: "+M17FundName2);
				}
			}else{
				appLog.error("Not able to click on Created Fund: "+M17FundName2);
				sa.assertTrue(false, "Not able to click on Created Fund: "+M17FundName2);	
			}

		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName2);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName2);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc036_2_LoginWithHubToEnableBulkDownLoadForFund2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(HubUserName,HubPassword);
		String[] funds = {M17FundName2};
		for (String fund : funds) {
			
			if(fp.clickOnTab(TabName.FundsTab)) {
				if(fp.clickOnCreatedFund(fund)) {
					if (click(driver, fp.getEditButton(10), "Edit Button", action.BOOLEAN)) {
						appLog.error("Click on Edit Button : "+fund);
						ThreadSleep(1000);
						if (isSelected(driver, fp.getBulkDownLoadCheckBox(Workspace.FundraisingWorkspace, 10), "Bulk DownLoad CheckBox")) {
							appLog.info("Bulk DownLoad is Already Checked");	
						}else{
							if (click(driver, fp.getBulkDownLoadCheckBox(Workspace.FundraisingWorkspace, 10), "Bulk DownLoad CheckBox", action.SCROLLANDBOOLEAN)) {
								appLog.info(fund+" clicked on Bulk DownLoad CheckBox : "+Workspace.FundraisingWorkspace);	
							} else {
								appLog.error(fund+" Not able to click on Bulk DownLoad CheckBox : "+Workspace.FundraisingWorkspace);
								sa.assertTrue(false, fund+" Not able to click on Bulk DownLoad CheckBox : "+Workspace.FundraisingWorkspace);
							}	
						}
						ThreadSleep(2000);
						if (click(driver, fp.getSaveButton(10), "Save Button", action.SCROLLANDBOOLEAN)) {
							appLog.error("click on Save Button : "+fund);
						} else {
							appLog.error("Not able to click on Save Button : "+fund);
							sa.assertTrue(false, "Not able to click on Save Button : "+fund);
						}

					} else {
						appLog.error("Not able to click on Edit Button : "+fund);
						sa.assertTrue(false, "Not able to click on Edit Button : "+fund);
					}
				}else {
					appLog.error("Not able to click on created fund Name : "+fund);
					sa.assertTrue(false, "Not able to click on created fund Name : "+fund);

				}
			}else {
				appLog.error("Not able to click on fund tab");
				sa.assertTrue(false, "Not able to click on fund tab");
			}
			switchToDefaultContent(driver);
			
		}
		
		sa.assertAll();

	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc037_1_RemoveTheAccessOfContact4FromFund1() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		String contactFullName=M17Contact4FirstName+" "+M17Contact4LastName;
		if (cp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "workspace section");
				if (fp.verifyFolderPathdummy(M17Institution1, null, null, null, PageName.FundsPage,Workspace.FundraisingWorkspace,60)) {
					appLog.error("folder path verified : "+M17Institution1);
					if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
						WebElement remove = isDisplayed(driver, FindElement(driver, "//a[text()='"+M17Contact4EmailId+"']/../../span/a[@title='Remove']", "remove link in front "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName, action.BOOLEAN, 60), "Visibility", 60, "Remove link");
						if (remove!=null) {
							if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on remove link for : "+contactFullName);
								ThreadSleep(5000);
								if (click(driver, fp.getRemoveAccessClose(Workspace.FundraisingWorkspace, 30), "remove access close button", action.BOOLEAN)) {
									appLog.error("Clicked on remove access close button");	

									if (click(driver, fp.getContactAccessCrossIcon(Workspace.FundraisingWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on Contact access Cross Icon");
									} else {
										appLog.error("Contact access Cross Icon is not clickable");
										sa.assertTrue(false, "Contact access close button is not clickable");
									}

								}
								else {
									appLog.error("remove access close button is not clickable");
									sa.assertTrue(false, "remove access close button is not clickable");
								}

							}else {
								appLog.error("Not able to click on remove link of contact: "+M17Contact4FirstName+" "+M17Contact4LastName);
								sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact4FirstName+" "+M17Contact4LastName);
							}
						}
						else {
							appLog.error("remove button adjacent to "+M17Contact4FirstName+" "+M17Contact4LastName+" is not found");
							sa.assertTrue(false, "remove button adjacent to "+M17Contact4FirstName+" "+M17Contact4LastName+" is not found");
						}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}
				}
				else {
					appLog.error("folder path is not found : "+M17Institution1);
					sa.assertTrue(false, "folder path is not found : "+M17Institution1);
				}

			}
			else {
				appLog.error(M17FundName1+" is not found on funds page");
				sa.assertTrue(false, M17FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}

		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc037_2_registerM17Contact4(){
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		AllFirmsPageBusinesslayer allfp = new AllFirmsPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String shdPath = ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		boolean flag = false;
		String[] allFolders = (cmnPath+","+shdPath).split(",");
		if (ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M17Contact4", excelLabel.Registered).equalsIgnoreCase("Not Registered")) {
			try {
				investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail",gmailUserName,gmailPassword,CRMUser1EmailID,M17Contact4EmailId);
				appLog.info("Investor Registration Link: " + investorRegLink);
			} catch (InterruptedException e) {
				appLog.info("Invitation mail not found.");
				e.printStackTrace();
			}
			if (investorRegLink != null) {
				driver.get(investorRegLink);
				if (bp.targetRegistration(M17Contact4FirstName, M17Contact4LastName, M17Contact4EmailId, M17Institution2,adminPassword)) {
					flag = true;
					appLog.info("Investor is registered successfully: " + M17Contact4FirstName + " " + M17Contact4LastName);
					ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M17Contact4", excelLabel.Registered);
				} else {
					driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
					appLog.info("Investor Target URL through Direct URL..");
					if (bp.targetRegistration(M17Contact4FirstName, M17Contact4LastName, M17Contact4EmailId,M17Institution2, adminPassword)) {
						appLog.info("Investor is registered successfully: " + M17Contact4FirstName + " "+ M17Contact4LastName);
						flag = true;
						ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M17Contact4", excelLabel.Registered);
					} else {
						appLog.error("Investor is not Registered through Invited Link: " + M17Contact4FirstName + " "+ M17Contact4LastName);
						sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M17Contact4FirstName+ " " + M17Contact4LastName);
					}
				}
			} else {
				driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
				appLog.info("Register Investor URL through Direct URL..");
				if (bp.targetRegistration(M17Contact4FirstName, M17Contact4LastName, M17Contact4EmailId,M17Institution2, adminPassword)) {
					flag = true;
					appLog.info("Investor is registered successfully: " + M17Contact4FirstName + " "+ M17Contact4LastName);
					ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M17Contact4", excelLabel.Registered);
				} else {
					appLog.error("Investor is not Registered through Invited Link: " + M17Contact4FirstName + " "+ M17Contact4LastName);
					sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M17Contact4FirstName+ " " + M17Contact4LastName);
				}
			}

			lp.investorLogin(M17Contact4EmailId, adminPassword);

			if (flag) {

				if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {

					List<WebElement> dropdownList=allOptionsInDropDrop(driver, ifp.getPotentialAndCurrentInvestmentInvestmentDropdown(30), "Drop Down List");
					List<String> result=compareMultipleList(driver, M17FundName1, dropdownList);

					if (!result.isEmpty()) {
						appLog.info(M17FundName1+" : is not available in DropDown after removing Acces");	
					} else {
						appLog.error(M17FundName1+" : is available in DropDown after removing Acces");
						sa.assertTrue(false, M17FundName1+" : is available in DropDown after removing Acces");
					}

					result=compareMultipleList(driver, M17FundName2, dropdownList);

					if (result.isEmpty()) {
						appLog.info(M17FundName2+" : is available in DropDown");	
					} else {
						appLog.error(M17FundName2+" : is not available in DropDown");
						sa.assertTrue(false, M17FundName2+" : is not available in DropDown");
					}

					if(selectVisibleTextFromDropDown(driver, ifp.getPotentialAndCurrentInvestmentInvestmentDropdown(30), "select fund : "+M17FundName2, M17FundName2)){

						for (int i = 0;i<allFolders.length;i++) {
							if (fp.verifyFolderPathdummy(allFolders[i], null, null, null, PageName.PotentialInvestmentPage,null, 60)) {
								appLog.info(" Folder Structure Verified: " + allFolders[i]);
							}
							else {
								appLog.error("could not find folder structure "+allFolders[i]);
								sa.assertTrue(false, "could not find folder structure "+allFolders[i]);
							}

						}

					} else {
						appLog.error(M17FundName2+" fund is not present in the list, So cannot verify folder.");
						sa.assertTrue(false,M17FundName2+" fund is not present in the list, So cannot verify folder.");
					}

				}else{
					appLog.error("Not Able to Click on CurrentInvestment");
					sa.assertTrue(false, "Not Able to Click on CurrentInvestment");	
				}

				lp.investorLogout(); 
			} else {

				appLog.error("Flag is false so can not verify Folder Structure");
				sa.assertTrue(false, "Flag is false so can not verify Folder Structure");	
			}


		} else {
			appLog.info("investor "+M17Contact4FirstName+" "+M17Contact4LastName+" is already Registered.");
			sa.assertTrue(false, "investor "+M17Contact4FirstName+" "+M17Contact4LastName+" is already Registered.");
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc038_1_UploadFileAtSharedCommonFolderAndRenameSharedCommonFolderName() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);

		String 	date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		SoftAssert saa = new SoftAssert();

		String shdFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		String commonFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String[] updatedFolderNames =  ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.KeyWord_For_Search).split(",");

		String[] folders = {shdFolder,commonFolder};
		excelLabel[] excelLabels = {excelLabel.UploadedFileShared,excelLabel.UploadedFileCommon};

		String filesName =null;

		lp.CRMLogin(CRMUser1EmailID, adminPassword);

		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName2)) {

				String docpath = "UploadFiles\\Module17\\FileFolder";

				for (int i = 0; i < folders.length; i++) {


					if (fp.uploadFile(folders[i], null, docpath, null, UploadFileActions.Upload,Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {

						appLog.info("File is upload successfullly in  "+folders[i]);
						switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 20));
						scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),"Investor workspace view");

						if (click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"FFundraisingWorkspace refresh button", action.SCROLLANDBOOLEAN)) {
							filesName = ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC, excelLabels[i]);

							if (filesName != null) {

								saa = verifyContentGridCRMSide(driver, PageName.FundsPage, Workspace.FundraisingWorkspace, filesName,
										"Approved", CRMUser1FirstName + " " + CRMUser1LastName,date);
								sa.combineAssertions(saa);

							} else {
								appLog.error("No Files in Excel for Verifying Content Grid in "+folders[i]);
								sa.assertTrue(false, "No Files in Excel for Verifying Content Grid in "+folders[i]);
							}

						} else {

							appLog.error("Not able to click on refresh icon so cannot verify upload documents in "+folders[i]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents in "+folders[i]);
						}

					} else {
						appLog.error("File is not uploaded in " + folders[i]);
						sa.assertTrue(false, "File is not uploaded in " + folders[i]);
					}

					switchToDefaultContent(driver);	

				}

				// Rename Folder 
				switchToDefaultContent(driver);
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),"Investor workspace view");
				if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "Manage folder icon", action.BOOLEAN)){

					for(int i=0; i<folders.length; i++) {
						String id = null;
						id=fp.getCreatedFolderId(folders[i], PageName.ManageFolderPopUp, 20);
						System.err.println("id>>>>>>"+id);
						if(id!=null) {
							if(fp.clickOnRenameFolderButton(id)) {
								if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,20),updatedFolderNames[i].split(" ")[0],folders[i]+" parent rename folder text box", action.BOOLEAN)) {
									if(click(driver,fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20) ,"rename parent folder save button", action.BOOLEAN)) {
										appLog.info("clicked on parent rename folder save button");
										ThreadSleep(2000);
										if(fp.getCreatedFolderId(updatedFolderNames[i].split(" ")[0], PageName.ManageFolderPopUp, 10)!=null) {
											appLog.info(updatedFolderNames[i].split(" ")[0]+" is available in manage folder");

										}else {
											appLog.error(updatedFolderNames[i].split(" ")[0]+" is not available in manage folder");
											sa.assertTrue(false, updatedFolderNames[i].split(" ")[0]+" is not available in manage folder");
										}
									}else {
										appLog.error("Not able to click on folder "+folders[i]+" save button so cannot Update Folder Name");
										sa.assertTrue(false, "Not able to click on folder "+folders[i]+" save button so cannot Update Folder Name");
									}
								}else {
									appLog.error("Not able to pass value in "+folders[i]+" rename folder text box so cannot Update folder Name");
									sa.assertTrue(false, "Not able to pass value in "+folders[i]+" rename folder text box so cannot Update folder Name");
								}

							}else {
								appLog.error("Not able to click on "+folders[i]+" rename icon so cannot update folder Name");
								sa.assertTrue(false, "Not able to click on "+folders[i]+" rename icon so cannot update folder Name");
							}
						}else {
							appLog.error(folders[i]+" folder is not available in manage folder so cannot Update folder Name");
							sa.assertTrue(false, folders[i]+" folder is not available in manage folder so cannot Update folder Name");
						}

					}


					if (click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 60),
							"Manage Folder Close Button", action.SCROLLANDBOOLEAN)) {
						appLog.info("click on manage folder close button");
					} else {
						appLog.error("Not able to click on manage folder close button");
						sa.assertTrue(false, "Not able to click on manage folder folder close button ");
					}


					for (int i = 0; i < updatedFolderNames.length; i++) {
						if (fp.verifyFolderPathdummy(updatedFolderNames[i], null, null, null,PageName.FundsPage, Workspace.FundraisingWorkspace, 60)) {
							appLog.info("Folder Structure verified after Update : "+updatedFolderNames[i]);
						} else {
							appLog.error("Folder Structure not verified after Update : "+updatedFolderNames[i]);
							sa.assertTrue(false, "Folder Structure not verified after Update : "+updatedFolderNames[i]);
						}

					}


				}else {
					appLog.error("Not able to click on Manage folder Icon so cannot check error message in fundraising workspace");
					sa.assertTrue(false, "Not able to click on Manage folder Icon so cannot check error message in fundraising workspace");
				}


			} else {
				appLog.info("Not Able to Click Fund or Fud Not Available : " + M17FundName2);
				sa.assertTrue(false, "Not Able to Click Fund or Fud Not Available : " + M17FundName2);
			}
		} else {
			appLog.info("Not Able to Click Funds Tab");
			sa.assertTrue(false, "Not Able to Click Funds Tab");
		}


		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact4FirstName, M17Contact4LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "contact page investor workspace section");

				for (int i=0;i<updatedFolderNames.length;i++) {

					if (fp.verifyFolderPathdummy(updatedFolderNames[i], null, null, M17FundName2, PageName.ContactsPage,
							Workspace.FundraisingWorkspace, 60)) {
						appLog.info(" path verified on contacts page folder structure : "+updatedFolderNames[i]);
					}
					else {
						appLog.error(" path is not found on contacts page folder structure : "+updatedFolderNames[i]);
						sa.assertTrue(false, " path is not found on contacts page folder structure : "+updatedFolderNames[i]);
					}

				}

			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact3FirstName+" "+ M17Contact3LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact3FirstName+" "+M17Contact3LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}

		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc038_2_VerifyFolderStructureInInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String[] allFolders=(cmnPath+","+shdPath).split(",");
		String parentID;

		lp.investorLogin(M17Contact4EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {

			for (int i = 0; i < allFolders.length; i++) {

				if (fp.verifyFolderPathdummy(allFolders[i], null, null, null, PageName.PotentialInvestmentPage,
						null, 20)) {
					appLog.info(" Folder Structure Verified: " + allFolders[i]);

				}else {
					appLog.error("could not find folder structure "+allFolders[i]);
					sa.assertTrue(false, "could not find folder structure "+allFolders[i]);
				}

			}

			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");

				parentID=switchOnWindow(driver);

				if (parentID!=null) {

					for (int i = 0; i < allFolders.length; i++) {

						if (ifp.verifyBulkDownLoadFolderStructure(allFolders[i], 20)) {
							appLog.info(" Folder Structure Verified on Bulk DownLoad: " + allFolders[i]);
						}else {
							appLog.error("could not find folder structure on Bulk DownLoad"+allFolders[i]);
							sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+allFolders[i]);
						}

					}
					
					driver.close();
					driver.switchTo().window(parentID);

				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}


			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}


		}else{
			appLog.error("Not Able to Click on "+investorSideWorkSpace.PotentialInvestment);
			sa.assertTrue(false, "Not Able to Click on "+investorSideWorkSpace.PotentialInvestment);	
		}

		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc039_CheckFunctionalityofsearchtextfieldonContactAccessFundraisingWorkspace(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		WebElement ele=null;
		String msg;
		String alertMsg = FundsPageErrorMessage.PleaseEnterValueErrorMessage;
		String emptyString;
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy("", M17Institution2, "", null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
						if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
							// 1st Assertion
							if(!clickUsingJavaScript(driver, fp.getSearchBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" search button")) {
//							if(click(driver, fp.getSearchBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" search button", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on  "+Workspace.FundraisingWorkspace+" search button");
								ThreadSleep(2000);
								if (isAlertPresent(driver)) {
									msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if (msg.trim().contains(alertMsg)) {
										appLog.info("Error Message is verified in contact access : "+alertMsg);
									} else {
										appLog.error("Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
										sa.assertTrue(false,"Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
									}
								} else {
									appLog.error("Alert is not present so cannot verify alert message "+alertMsg);
									sa.assertTrue(false, "Alert is not present so cannot verify alert message "+alertMsg);
								}
							}else {
								appLog.error("Not Able to click on Search Icon");
								sa.assertTrue(false, "Not Able to click on Search Icon");
							}
							// 2nd Assertion
							String value ="vvvvvvvvvv";
							for (int i = 0; i < 3; i++) {
								if(sendKeys(driver,fp.getSearchTextBox(Workspace.FundraisingWorkspace, 60), value,Workspace.FundraisingWorkspace+" search text box", action.SCROLLANDBOOLEAN)) {
									appLog.info("enter the value in search text box : "+value);
									ele = fp.getClearSearchIcon(Workspace.FundraisingWorkspace, EnableDisable.Enable, 10);
									if (ele!=null) {
										appLog.info("Clear Icon Active");	
									} else {
										appLog.error("Clear Icon not Active");
										sa.assertTrue(false, "Clear Icon not Active");
									}
									if (i==0) {
										for (int j = 0; j < value.length(); j++) {

											Robot rob;
											try {
												rob = new Robot();
												ThreadSleep(200);
												rob.keyPress(KeyEvent.VK_BACK_SPACE);
												System.err.println("Back Space  pRESS");
												ThreadSleep(200);
												rob.keyRelease(KeyEvent.VK_BACK_SPACE);
												System.err.println("Back Space rELAESE");

											} catch (AWTException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										}
									} else if(i==1) {
										if (click(driver, fp.getClearIconOnContactAccessSearchBox(Workspace.FundraisingWorkspace, 10), "Clear Icon", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on Cross Icon");
										} else {
											appLog.error("Not Able to Click on Cross Icon");
											sa.assertTrue(false, "Not Able to Click on Cross Icon");
										}
									}else{
										// 4TH aSSERATION
										if(click(driver, fp.getSearchBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" search button", action.SCROLLANDBOOLEAN)) {
											ThreadSleep(3000);
											ele=fp.noDataToDisplayContactAccessTopGrid(Workspace.FundraisingWorkspace, 60);
											if (ele!=null) {
												msg =ele.getText().trim();
												if(msg.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
													appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message Verified");
												}else {
													appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
													sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
												}
											} else {
												appLog.error("No Data msg element is null");
												sa.assertTrue(false, "No Data msg element is null");	
											}
										}else {
											appLog.error("Not Able to click on Search Icon");
											sa.assertTrue(false, "Not Able to click on Search Icon");
										}
									}
									if (i==0 || i==1) {
										emptyString = getValueFromElementUsingJavaScript(driver, fp.getSearchTextBox(Workspace.FundraisingWorkspace, 60), "Search Box");
										if(emptyString.isEmpty()) {
											appLog.info("Search Box is Empty for iteration : "+i);
										} else {
											appLog.info(i+" Search Box is not Empty with Value : "+emptyString);
											sa.assertTrue(false, i+" Search Box is not Empty with Value : "+emptyString);
										}
									}
								}else {
									appLog.error("Not Able to enter the value in search text box : "+value);
									sa.assertTrue(false, "Not Able to enter the value in search text box : "+value);
								}
							}
							// 5th 
							String[][] contactInfo = {{M17Contact2FirstName,M17Contact2FirstName+" "+M17Contact2LastName,M17Contact2EmailId,M17Institution1},
									{M17Contact3LastName,M17Contact3FirstName+" "+M17Contact3LastName,M17Contact3EmailId,M17Institution2},
									{M17Institution3,M17Contact5FirstName+" "+M17Contact5LastName,M17Contact5EmailId,M17Institution3},
									{M17Contact1FirstName,M17Contact1FirstName+" "+M17Contact1LastName,M17Contact1EmailId,M17Institution1},
									{"~~~!#!~@#$%^&!@#$%^&*()$%^&*()_+&*()_+<><>:\"\":\"\"??,.,.//;;'';[][\\{}|}{+-=[]"}};

							for (int i = 0; i < contactInfo.length; i++) {
								if(sendKeys(driver,fp.getSearchTextBox(Workspace.FundraisingWorkspace, 60), contactInfo[i][0],Workspace.FundraisingWorkspace+" search text box", action.SCROLLANDBOOLEAN)) {
									appLog.info("enter the value in search text box : "+contactInfo[i][0]);
									ThreadSleep(1000);
									if(click(driver, fp.getSearchBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" search button", action.SCROLLANDBOOLEAN)) {
										ThreadSleep(2000);

										if (i==contactInfo.length-2) {
											ele = fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, M17Contact3FirstName+" "+M17Contact3LastName, M17Contact3EmailId, M17Institution2, 10);
											if (ele!=null) {
												appLog.info("For Search Value : "+contactInfo[i][0]+" >>> Select contact grid verified with value : "+M17Contact3FirstName+" "+M17Contact3LastName+"\t"+M17Contact3EmailId+"\t"+M17Institution2);
											} else {
												appLog.error("For Search Value : "+contactInfo[i][0]+" >>> Select contact grid Not verified with value : "+M17Contact3FirstName+" "+M17Contact3LastName+"\t"+M17Contact3EmailId+"\t"+M17Institution2);
												sa.assertTrue(false, "For Search Value : "+contactInfo[i][0]+" >>> Select contact Not grid verified with value : "+M17Contact3FirstName+" "+M17Contact3LastName+"\t"+M17Contact3EmailId+"\t"+M17Institution2);
											}
										} else if (i==contactInfo.length-1){

											ele=fp.noDataToDisplayContactAccessTopGrid(Workspace.FundraisingWorkspace, 60);
											if (ele!=null) {
												msg =ele.getText().trim();
												if(msg.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
													appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message Verified");
												}else {
													appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
													sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
												}
											} else {
												appLog.error("No Data msg element is null");
												sa.assertTrue(false, "No Data msg element is null");	
											}

										}else{
											ele = fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, contactInfo[i][1], contactInfo[i][2], contactInfo[i][3], 10);
											if (ele!=null) {
												appLog.info("For Search Value : "+contactInfo[i][0]+" >>> Select contact grid verified with value : "+contactInfo[i][1]+"\t"+contactInfo[i][2]+"\t"+contactInfo[i][3]);
											} else {
												appLog.error("For Search Value : "+contactInfo[i][0]+" >>> Select contact grid Not verified with value : "+contactInfo[i][1]+"\t"+contactInfo[i][2]+"\t"+contactInfo[i][3]);
												sa.assertTrue(false, "For Search Value : "+contactInfo[i][0]+" >>> Select contact Not grid verified with value : "+contactInfo[i][1]+"\t"+contactInfo[i][2]+"\t"+contactInfo[i][3]);
											}	
										}

										String[][] defaultContactInfo = {{M17Contact3FirstName+" "+M17Contact3LastName,M17Contact3EmailId,M17Institution2},
												{M17Contact4FirstName+" "+M17Contact4LastName,M17Contact4EmailId,M17Institution2}};
										if (click(driver, fp.getClearIconOnContactAccessSearchBox(Workspace.FundraisingWorkspace, 10), "Clear Icon", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on Cross Icon");
											ThreadSleep(2000);

											for (int j = 0; j < defaultContactInfo.length; j++) {
												ele = fp.verifySelectContactsGridDataInContactAccess(Workspace.FundraisingWorkspace, defaultContactInfo[j][0], defaultContactInfo[j][1], defaultContactInfo[j][2], 10);
												if (ele!=null) {
													appLog.info("Default list >>> Select contact grid verified with value : "+defaultContactInfo[j][0]+"\t"+defaultContactInfo[j][1]+"\t"+defaultContactInfo[j][2]);
												} else {
													appLog.error("Default list  >>> Select contact grid Not verified with value : "+defaultContactInfo[j][0]+"\t"+defaultContactInfo[j][1]+"\t"+defaultContactInfo[j][2]);
													sa.assertTrue(false, "Default list  >>> Select contact Not grid verified with value : "+defaultContactInfo[j][0]+"\t"+defaultContactInfo[j][1]+"\t"+defaultContactInfo[j][2]);
												}
											}																																
										} else {
											appLog.error("Not Able to Click on Cross Icon");
											sa.assertTrue(false, "Not Able to Click on Cross Icon");
										}
									}else {
										appLog.error("Not Able to click on Search Icon for iteration : "+i);
										sa.assertTrue(false, "Not Able to click on Search Icon for iteration : "+i);
									}
								}else {
									appLog.error("Not Able to enter the value in search text box : "+contactInfo[i][0]);
									sa.assertTrue(false, "Not Able to enter the value in search text box : "+contactInfo[i][0]);
								}
							}
						}else {
							appLog.error("Not able to expand contact access grid ");
							sa.assertTrue(false, "Not able to expand contact access grid ");
						}

					}else {
						appLog.error("Not able to click on Contact Access Icon ");
						sa.assertTrue(false, "Not able to click on Contact Access Icon ");	
					}
				}else {
					appLog.error("Not able to click on "+M17Institution2+" folder");
					sa.assertTrue(false, "Not able to click on "+M17Institution2+" folder");
				}
			}else {
				appLog.error("Not able to click on created fund Name "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name "+M17FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab ");
			sa.assertTrue(false, "Not able to click on fund tab ");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc040_CheckSortingOfColumnsForSelectContactsGrid(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy("", M17Institution1, "", null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "FundraisingWorkspace contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
						if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
							appLog.info("Able to expand Conatct Access Grid");
							ThreadSleep(2000);
							if (fp.performSortingCheckOnContactAccessPopUpSelectContactGrid(Workspace.FundraisingWorkspace, 10)) {
								appLog.info("Sorting Verified on Contact Access PopUp Header List");
							} else {
								appLog.error("Sorting Not Verified on Contact Access PopUp Header List");
								sa.assertTrue(false, "Sorting Not Verified on Contact Access PopUp Header List");
							}
							
						}else {
							appLog.error("Not able to expand contact access grid ");
							sa.assertTrue(false, "Not able to expand contact access grid ");
						}

					}else {
						appLog.error("Not able to click on Contact Access Icon ");
						sa.assertTrue(false, "Not able to click on Contact Access Icon ");	
					}
				}else {
					appLog.error("Not able to click on "+M17Institution1+" folder");
					sa.assertTrue(false, "Not able to click on "+M17Institution1+" folder");
				}
			}else {
				appLog.error("Not able to click on created fund Name "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name "+M17FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab ");
			sa.assertTrue(false, "Not able to click on fund tab ");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc041_CheckSortingOfColumnsForSelectedContactsGrid(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
		
			switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy("", M17Institution1, "", null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "FundraisingWorkspace contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
						if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
							appLog.info("Able to expand Conatct Access Grid");
							ThreadSleep(2000);

							WebElement ele = fp.getContactNameOrAllContactCheckBox(Workspace.FundraisingWorkspace, null, 20);

							if(ele!=null) {
								ThreadSleep(1000);
								if(click(driver, ele, "check box", action.BOOLEAN)) {
									appLog.info("clicked on Header check box");
									ThreadSleep(1000);
									if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace+" add select contact active button", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on add selected contact active button in "+Workspace.FundraisingWorkspace);

										if(click(driver, fp.getApplyBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" apply button", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on apply button successfully");
											if(click(driver, fp.getCloseBtn(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace+" close button", action.SCROLLANDBOOLEAN)) {
												appLog.info("Clicked on Close Buton");	
												ThreadSleep(2000);
												if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "FundraisingWorkspace contact access icon", action.SCROLLANDBOOLEAN)) {
													appLog.info("Clicked on Contact Access Icon");
													ThreadSleep(2000);
													if (fp.performSortingCheckOnContactAccessPopUpSelectedContactGrid(Workspace.FundraisingWorkspace, 10)) {
														appLog.info("Sorting Verified on Contact Access PopUp Selected Grid Header List");
													} else {
														appLog.error("Sorting Not Verified on Contact Access PopUp Selected Grid  Header List");
														sa.assertTrue(false, "Sorting Not Verified on Contact Access PopUp Selected Grid Header List");
													}
												}else{
													appLog.error("Not able to click on Contact Access Icon ");
													sa.assertTrue(false, "Not able to click on Contact Access Icon ");	
												}
											}else {
												appLog.error("Not able to click on close button");
												sa.assertTrue(false, "Not able to click on close button");
											}

										}else {
											appLog.error("not able to click on apply button so cannot invite contact: ");
											sa.assertTrue(false, "not able to click on apply button so cannot invite contact: ");
										}


									}else{
										appLog.error("Not able to click on add select contact button in "+Workspace.FundraisingWorkspace);
										sa.assertTrue(false, "Not able to click on add select contact button in "+Workspace.FundraisingWorkspace);
									}

								}else {
									appLog.error("Not able to click on Header CheckBox");
									sa.assertTrue(false, "Not able to click on Header CheckBox");
								}
							}else {
								appLog.error("Header CheckBox is not visible");
								sa.assertTrue(false, "Header CheckBox is not visible");
							}

							/*ThreadSleep(2000);
							if (fp.performSortingCheckOnContactAccessPopUpSelectedContactGrid(Workspace.FundraisingWorkspace, 10)) {
								appLog.info("Sorting Verified on Contact Access PopUp Header List");
							} else {
								appLog.error("Sorting Not Verified on Contact Access PopUp Header List");
								sa.assertTrue(false, "Sorting Not Verified on Contact Access PopUp Header List");
							}*/
						}else {
							appLog.error("Not able to expand contact access grid ");
							sa.assertTrue(false, "Not able to expand contact access grid ");
						}

					}else {
						appLog.error("Not able to click on Contact Access Icon ");
						sa.assertTrue(false, "Not able to click on Contact Access Icon ");	
					}
				}else {
					appLog.error("Not able to click on "+M17Institution1+" folder");
					sa.assertTrue(false, "Not able to click on "+M17Institution1+" folder");
				}
			}else {
				appLog.error("Not able to click on created fund Name "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name "+M17FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab ");
			sa.assertTrue(false, "Not able to click on fund tab ");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc042_CheckFunctionalityHeaderCheckBoxInSelectContactsGridUnderContactAccessPopup(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy("", M17Institution1, "", null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "FundraisingWorkspace contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
						if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
							appLog.info("Able to expand Conatct Access Grid");
							ThreadSleep(2000);

							// Check Header
							appLog.info("Going to Click Header CheckBox for Check");
							List<WebElement> eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.FundraisingWorkspace);

							if(eles!=null) {
								ThreadSleep(1000);
								if(click(driver, eles.get(0), "Header check box Check", action.BOOLEAN)) {
									appLog.info("clicked on Header check box for check");
									eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.FundraisingWorkspace);
									ThreadSleep(1000);

									for (int i = 0; i < eles.size(); i++) {

										if (isSelected(driver, eles.get(i), "CheckBoxes Checked")) {
											appLog.info("checkbox is checked after clicking on Header CheckBox for iteration : "+i);	
										} else {
											appLog.error("checkbox is not checked after clicking on Header CheckBox for iteration : "+i);
											sa.assertTrue(false, "checkbox is not checked after clicking on Header CheckBox for iteration : "+i);
										}

									}


								}else {
									appLog.error("Not able to click on Header CheckBox for check");
									sa.assertTrue(false, "Not able to click on Header CheckBox for check");
								}
							}else {
								appLog.error("CheckBox is not visible for check");
								sa.assertTrue(false, "CheckBox is not visible for check");
							}

							ThreadSleep(2000);
							// Uncheck Header
							appLog.info("Going to Click Header CheckBox for UnCheck");
							eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.FundraisingWorkspace);

							if(eles!=null) {
								ThreadSleep(1000);
								if(click(driver, eles.get(0), "Header check box Uncheck", action.BOOLEAN)) {
									appLog.info("clicked on Header check box for Uncheck");
									ThreadSleep(1000);
									eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.FundraisingWorkspace);
									for (int i = 0; i < eles.size(); i++) {

										if (!isSelected(driver, eles.get(i), "CheckBoxes UnChecked")) {
											appLog.info("checkbox is Unchecked after clicking on Header CheckBox for iteration : "+i);	
										} else {
											appLog.error("checkbox is not Unchecked after clicking on Header CheckBox for iteration : "+i);
											sa.assertTrue(false, "checkbox is not Unchecked after clicking on Header CheckBox for iteration : "+i);
										}

									}

								}else {
									appLog.error("Not able to click on Header CheckBox for Uncheck");
									sa.assertTrue(false, "Not able to click on Header CheckBox for Uncheck");
								}
							}else {
								appLog.error("CheckBox is not visible for Uncheck");
								sa.assertTrue(false, "CheckBox is not visible for Uncheck");
							}

							ThreadSleep(2000);


							// Check Header Again
							appLog.info("Going to Click Header CheckBox for Check Again");
							eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.FundraisingWorkspace);

							if(eles!=null) {
								ThreadSleep(1000);
								if(click(driver, eles.get(0), "Header check box Again Check", action.BOOLEAN)) {
									appLog.info("clicked on Header check box for check Again");
									eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.FundraisingWorkspace);
									ThreadSleep(1000);

									for (int i = 0; i < eles.size(); i++) {

										if (isSelected(driver, eles.get(i), "CheckBoxes Checked Again")) {
											appLog.info("checkbox is checked Again after clicking on Header CheckBox for iteration : "+i);	
										} else {
											appLog.error("checkbox is not checked Again after clicking on Header CheckBox for iteration : "+i);
											sa.assertTrue(false, "checkbox is not checked Again after clicking on Header CheckBox for iteration : "+i);
										}



									}

									// Uncheck Contact CheckBox
									appLog.info("Going to Uncheck Contact CheckBox");
									eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.FundraisingWorkspace);
									ThreadSleep(1000);
									if (click(driver, eles.get(eles.size()-1), "Contact check box for UnCheck", action.BOOLEAN)) {
										appLog.info(" able to click on Contact CheckBox for Uncheck");	

										ThreadSleep(1000);
										eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.FundraisingWorkspace);
										ThreadSleep(1000);

										for (int i = 0; i < eles.size(); i++) {

											if (i==0 || i==eles.size()-1) {

												if (!isSelected(driver, eles.get(i), "CheckBoxes UnChecked after Unchecking Contact CheckBox")) {
													appLog.info("checkbox is Unchecked after Unchecking Contact CheckBox for iteration : "+i);	
												} else {
													appLog.error("checkbox is not Unchecked after Unchecking Contact CheckBox for iteration : "+i);
													sa.assertTrue(false, "checkbox is not Unchecked after Unchecking Contact CheckBox for iteration : "+i);
												}	

											} else {

												if (isSelected(driver, eles.get(i), "CheckBoxes Checked after Unchecking Contact CheckBox")) {
													appLog.info("checkbox is checked after Unchecking Contact CheckBox for iteration : "+i);	
												} else {
													appLog.error("checkbox is not checked after Unchecking Contact CheckBox for iteration : "+i);
													sa.assertTrue(false, "checkbox is not checked after Unchecking Contact CheckBox for iteration : "+i);
												}
											}



										}

										// check  UnCheckBox
										eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.FundraisingWorkspace);
										ThreadSleep(2000);
										appLog.info("Going to check UnCheckBox");	
										for (int i = 0; i < eles.size(); i++) {

											if (i==0 || i==eles.size()-1) {

												if (click(driver, eles.get(i), "UnCheck box for Check", action.BOOLEAN)) {
													appLog.info(" able to click on UnCheckBox for check");	
												}else{
													appLog.error("Not able to click on UnCheckBox for check");
													sa.assertTrue(false, "Not able to click on UnCheckBox for check");
												}
												
												ThreadSleep(1000);

											}

										}

										// Going to verify CheckBox after checking UncheckBoxes
										appLog.info("Going to verify CheckBox after checking UncheckBoxes");
										eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.FundraisingWorkspace);
										ThreadSleep(1000);

										for (int i = 0; i < eles.size(); i++) {

											if (isSelected(driver, eles.get(i), "CheckBoxes Checked")) {
												appLog.info("checkbox is checked after checking Unchecked Box for iteration : "+i);	
											} else {
												appLog.error("checkbox is not checked after checking Unchecked Box for iteration : "+i);
												sa.assertTrue(false, "checkbox is not checked after checking Unchecked Box for iteration : "+i);
											}

										}

										//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

										// Uncheck Contact CheckBox
										appLog.info("Going to Uncheck Contact CheckBox Again");
										eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.FundraisingWorkspace);
										ThreadSleep(1000);
										if (click(driver, eles.get(eles.size()-1), "Contact check box for UnCheck Again", action.BOOLEAN)) {
											appLog.info(" able to click on Contact CheckBox for Uncheck Again");	

											ThreadSleep(1000);
											eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.FundraisingWorkspace);
											ThreadSleep(1000);

											for (int i = 0; i < eles.size(); i++) {

												if (i==0 || i==eles.size()-1) {

													if (!isSelected(driver, eles.get(i), "CheckBoxes UnChecked after Unchecking Contact CheckBox Again")) {
														appLog.info("checkbox is Unchecked after Unchecking Contact CheckBox Again for iteration : "+i);	
													} else {
														appLog.error("checkbox is not Unchecked after Unchecking Contact CheckBox Again for iteration : "+i);
														sa.assertTrue(false, "checkbox is not Unchecked after Unchecking Contact CheckBox Again for iteration : "+i);
													}	

												} else {

													if (isSelected(driver, eles.get(i), "CheckBoxes Checked after Unchecking Contact CheckBox Again")) {
														appLog.info("checkbox is checked after Unchecking Contact CheckBox Again for iteration : "+i);	
													} else {
														appLog.error("checkbox is not checked after Unchecking Contact CheckBox Again for iteration : "+i);
														sa.assertTrue(false, "checkbox is not checked after Unchecking Contact CheckBox Again for iteration : "+i);
													}
												}



											}

											// check  Header CheckBox
											appLog.info("Going to check Header CheckBox");	
											eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.FundraisingWorkspace);
											ThreadSleep(1000);

											if (click(driver, eles.get(0), "check Header CheckBox", action.BOOLEAN)) {
												appLog.error(" able to click on Header CheckBox");	
											}else{
												appLog.error("Not able to click on Header CheckBox");
												sa.assertTrue(false, "Not able to click on Header CheckBox");
											}



											// Going to verify CheckBox after checking UncheckBoxes
											appLog.info("Going to verify CheckBox after checking Header CheckBox");
											eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.FundraisingWorkspace);
											ThreadSleep(1000);

											for (int i = 0; i < eles.size(); i++) {

												if (isSelected(driver, eles.get(i), "checking Header CheckBox")) {
													appLog.info("checkbox is checked after checking Header CheckBox for iteration : "+i);	
												} else {
													appLog.error("checkbox is not checked after checking Header CheckBox for iteration : "+i);
													sa.assertTrue(false, "checkbox is not checked after checking Header CheckBox for iteration : "+i);
												}

											}




										} else {
											appLog.error("Not able to click on Contact CheckBox for Uncheck");
											sa.assertTrue(false, "Not able to click on Contact CheckBox for Uncheck");
										}				

										//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


									} else {
										appLog.error("Not able to click on Contact CheckBox for Uncheck");
										sa.assertTrue(false, "Not able to click on Contact CheckBox for Uncheck");
									}


								}else {
									appLog.error("Not able to click on Header CheckBox for check Again");
									sa.assertTrue(false, "Not able to click on Header CheckBox for check Again");
								}
							}else {
								appLog.error("CheckBox is not visible for check Again");
								sa.assertTrue(false, "CheckBox is not visible for check Again");
							}

							ThreadSleep(2000);

						}else {
							appLog.error("Not able to expand contact access grid ");
							sa.assertTrue(false, "Not able to expand contact access grid ");
						}

					}else {
						appLog.error("Not able to click on Contact Access Icon ");
						sa.assertTrue(false, "Not able to click on Contact Access Icon ");	
					}
				}else {
					appLog.error("Not able to click on "+M17Institution1+" folder");
					sa.assertTrue(false, "Not able to click on "+M17Institution1+" folder");
				}
			}else {
				appLog.error("Not able to click on created fund Name "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name "+M17FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab ");
			sa.assertTrue(false, "Not able to click on fund tab ");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc043_buildInvestorWorkSpace(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund1", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund1", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund1", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund1", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund1", excelLabel.Fund_Email);
				String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund1", excelLabel.Fund_Description);
				String templateName=ExcelUtils.readData("Foldertemp", 0, 1);
				String[] data= {Size,vintageyear,contact,phone,email,description};
				String standardfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
				String sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
				String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
				String internalfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
				
				
				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,M17Institution1 + "/" + M17LimitedPartner1 + "<break>" + M17Institution2 + "/" + M17LimitedPartner2, Workspace.InvestorWorkspace,60)) {

					appLog.info("FundRaising work is build successfully on fund : "+M17FundName1);
					switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
					if(fp.verifyFolderPathdummy("", M17Institution1, M17LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
						if(fp.getContactAccessIcon(Workspace.InvestorWorkspace, 20)!=null) {
							appLog.info("Contact access icon is displaying in "+M17Institution1+"/" + M17LimitedPartner1);
						}else {
							appLog.error("Contact access pop up is not displaying in "+M17Institution1+"/" + M17LimitedPartner1);
							sa.assertTrue(false, "Contact access pop up is not displaying in "+M17Institution1+"/" + M17LimitedPartner1);
						}
					}else {
						appLog.error("Not able to click on folder: "+M17Institution1+"/" + M17LimitedPartner1+" so cannot verify contact access icon");
						sa.assertTrue(false, "Not able to click on folder: "+M17Institution1+"/" + M17LimitedPartner1+" so cannot verify contact access icon");
					}
					if(fp.verifyFolderPathdummy(standardfolderpath, M17Institution1, M17LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
						if(fp.getContactAccessIcon(Workspace.InvestorWorkspace, 20)!=null) {
							appLog.info("Contact access icon is displaying in "+M17Institution1+"/" + M17LimitedPartner1+"/"+standardfolderpath);
						}else {
							appLog.error("Contact access pop up is not displaying in "+M17Institution1+"/" + M17LimitedPartner1+"/"+standardfolderpath);
							sa.assertTrue(false, "Contact access pop up is not displaying in "+M17Institution1+"/" + M17LimitedPartner1+"/"+standardfolderpath);
						}
					}else {
						appLog.error("Not able to click on folder: "+M17Institution1+"/" + M17LimitedPartner1+"/"+standardfolderpath+" so cannot verify contact access icon");
						sa.assertTrue(false, "Not able to click on folder: "+M17Institution1+"/" + M17LimitedPartner1+"/"+standardfolderpath+" so cannot verify contact access icon");
					}

					if(fp.verifyFolderPathdummy(sharedfolderpath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
						if(fp.getContactAccessIcon(Workspace.InvestorWorkspace, 20)!=null) {
							appLog.info("Contact access icon is displaying in "+sharedfolderpath);
						}else {
							appLog.error("Contact access pop up is not displaying in "+sharedfolderpath);
							sa.assertTrue(false, "Contact access pop up is not displaying in "+sharedfolderpath);
						}
					}else {
						appLog.error("Not able to click on folder: "+sharedfolderpath+" so cannot verify contact access icon");
						sa.assertTrue(false, "Not able to click on folder: "+sharedfolderpath+" so cannot verify contact access icon");
					}

					if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
						if(fp.getContactAccessIcon(Workspace.InvestorWorkspace, 10)==null) {
							appLog.info("Contact access icon is not displaying in "+Commonfolderpath);
						}else {
							appLog.error("Contact access pop up is displaying in "+Commonfolderpath);
							sa.assertTrue(false, "Contact access pop up is displaying in "+Commonfolderpath);
						}
					}else {
						appLog.error("Not able to click on folder: "+Commonfolderpath+" so cannot verify contact access icon");
						sa.assertTrue(false, "Not able to click on folder: "+Commonfolderpath+" so cannot verify contact access icon");
					}

					if(fp.verifyFolderPathdummy(internalfolderpath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
						if(fp.getContactAccessIcon(Workspace.InvestorWorkspace, 10)==null) {
							appLog.info("Contact access icon is not displaying in "+internalfolderpath);
						}else {
							appLog.error("Contact access pop up is displaying in "+internalfolderpath);
							sa.assertTrue(false, "Contact access pop up is displaying in "+internalfolderpath);
						}
					}else {
						appLog.error("Not able to click on folder: "+internalfolderpath+" so cannot verify contact access icon");
						sa.assertTrue(false, "Not able to click on folder: "+internalfolderpath+" so cannot verify contact access icon");
					}

				}else {
					appLog.error("Not able to bulid fundraising workspace on fund: "+M17FundName1);
					sa.assertTrue(false, "Not able to bulid fundraising workspace on fund: "+M17FundName1);
				}
			}else{
				appLog.error("Not able to click on Created Fund: "+M17FundName1);
				sa.assertTrue(false, "Not able to click on Created Fund: "+M17FundName1);	
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc044_VerifySharedFolderContactAccess_Investor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String cols="<break>Contact Name<break>E-mail<break>Firm<break>Action<break>Contact Name<break>Granted Access On<break>E-mail<break>Firm<break><break><break>";
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		if (lp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
		switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver,fp.getInvestorWorkSpaceSection(30) , "investor workspace section");
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("clicked on shared folder");
				}
				else {
					appLog.error("could not click on shared folder");
					sa.assertTrue(false, "could not click on shared folder");
				}
				if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
					//verify UI
					if (fp.verifyUIOfContactAccess(Workspace.InvestorWorkspace, cols, shdPath)) {
						appLog.info("successfully verified UI of contact access");
					}
					else {
						appLog.error("could not verify UI of contact access");
						sa.assertTrue(false, "could not verify UI of contact access");
					}
				}
				if (click(driver, fp.getContactAccessCrossIcon(Workspace.InvestorWorkspace, 30), "cross icon", action.SCROLLANDBOOLEAN)) {
					//verify UI is closed
					if (fp.getContactAccessHeadText(Workspace.InvestorWorkspace, 30)==null) {
						appLog.info("successfully closed contact access UI");
					}
					else {
						appLog.error("contact access UI is not closed");
						sa.assertTrue(false, "contact access UI is not closed");
					}
				}
				
				if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
					//verify UI
					if (fp.verifyUIOfContactAccess(Workspace.InvestorWorkspace, cols, shdPath)) {
						appLog.info("successfully verified UI of contact access");
					}
					else {
						appLog.error("could not verify UI of contact access");
						sa.assertTrue(false, "could not verify UI of contact access");
					}
				}
				if (click(driver, fp.cancelButtonContactAccessActive(Workspace.InvestorWorkspace), "cancel button", action.SCROLLANDBOOLEAN)) {
					//verify UI is closed
					if (fp.getContactAccessHeadText(Workspace.InvestorWorkspace, 30)==null) {
						appLog.info("successfully closed contact access UI");
					}
					else {
						appLog.error("contact access UI is not closed");
						sa.assertTrue(false, "contact access UI is not closed");
					}
				}
				if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
					if (mouseOverOperation(driver, fp.getMouseOverIconContactAccessimg(30))) {
						ThreadSleep(3000);
						if (getAttribute(driver, fp.getMouseOverIconContactAccess(30), "contact access i icon", "title").equalsIgnoreCase(fp.contactAccessHoverText)) {
							appLog.info("successfully verified contact access i icon text");
						}
						else {
							appLog.error("contact access i icon text is not correct");
							sa.assertTrue(false, "contact access i icon text is not correct");
						}
					}
				}
				//verify firm column
				List<String> firms = new ArrayList<String>();
				firms.add(M17Institution1);
				firms.add(M17Institution2);
				firms.add(M17Institution3);
				//open section in contact access
				if (fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
					if (fp.compareFirmColumnContactAccess(Workspace.InvestorWorkspace, firms)) {
						appLog.info("successfully found firm names are correct");
					}
					else {
						appLog.error("could not find firm names as correct");
						sa.assertTrue(false, "could not find firm names as correct");
					}
					//click on contact 1 checkbox
					click(driver, fp.checkboxOnContactAccessUI(Workspace.InvestorWorkspace, M17Contact1EmailId), "checkbox of "+M17Contact1FirstName, action.SCROLLANDBOOLEAN);
					if (click(driver, fp.cancelButtonContactAccessActive(Workspace.InvestorWorkspace), "cancel button", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
							if (fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {

								if (!isSelected(driver,  fp.checkboxOnContactAccessUI(Workspace.InvestorWorkspace, M17Contact1EmailId), "checkbox of "+M17Contact1FirstName)) {
									appLog.info("after clicking cancel button checkbox for contact 1 is unchecked as expected");
								}
								else {
									appLog.error("after clicking cancel button checkbox for contact 1 is checked but it should not be");
									sa.assertTrue(false, "after clicking cancel button checkbox for contact 1 is checked but it should not be");
								}
							}
							else {
								appLog.error("could not expand contact access");
								sa.assertTrue(false, "could not expand contact access");
							}
						}
						else {
							appLog.error("contact access icon is not clickable");
							sa.assertTrue(false, "contact access icon is not clickable");
						}
					}
					else {
						appLog.error("cancel button is not clickable on contact access");
						sa.assertTrue(false, "cancel button is not clickable on contact access");
					}
					
				click(driver, fp.getContactAccessCrossIcon(Workspace.InvestorWorkspace, 30), "cross icon", action.SCROLLANDBOOLEAN);
				if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
					if (fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
						//click on email link
						click(driver, FindElement(driver, "//a[text()='"+M17Contact1EmailId+"']", "contact email id link", action.SCROLLANDBOOLEAN, 30), "contact email id link", action.BOOLEAN);
						if (nim.verifyNavatarSalesTeamLinkFunctionality("other")) {
							appLog.info("successfully verified email link");
						}
						else {
							appLog.error("could not verify email link");
							sa.assertTrue(false, "could not verify email link");
						}
					}
				}
				else {
					appLog.error("contact access icon is not clickable");
					sa.assertTrue(false, "contact access icon is not clickable");
				}
					switchToDefaultContent(driver);
				}
				else {
					appLog.error("could not open minus icon on contact access");
					sa.assertTrue(false, "could not open minus icon on contact access");
				}
			}
			else {
				appLog.error("could not find "+M17FundName1);
				sa.assertTrue(false, "could not find fund "+M17FundName1);
			}
		}
		lp.CRMlogout();
		sa.assertAll();
		
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc045_verifyFunctionalityOfAddSelectBtnInvestorWorkSpace(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
//		String standardfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
//		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
//		String internalfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if(fp.verifyFolderPathdummy(sharedfolderpath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
					if(fp.getContactAccessIcon(Workspace.InvestorWorkspace, 20)!=null) {
						appLog.info("Contact access icon is displaying in "+sharedfolderpath);
						if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
							ThreadSleep(5000);
							if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
								for(int i=0; i<6; i++) {
									WebElement contactcheckBox=null;
									if(i!=3) {
										contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWINV_MA']//a[text()='"+M17Contact1EmailId+"']/../..//input", M17Contact1FirstName+" "+M17Contact1LastName+" contact check box", action.SCROLLANDBOOLEAN, 10);
										scrollDownThroughWebelement(driver,contactcheckBox, "");
									}

									ThreadSleep(3000);
									if(i==0) {
										if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
											if(isEnabled(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "select add button")) {
												appLog.info("Add select Contact(s) button is enabled");
											}else {
												appLog.error("Add Select Contact(s) button is not enabled");
												sa.assertTrue(false, "Add Select Contact(s) button is not enabled");
											}
										}else {
											appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
											sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
										}
									}
									if(i==1) {
										if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
											if(!isEnabled(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "select add button")) {
												appLog.info("Add select Contact(s) button is disabled");
											}else {
												appLog.error("Add Select Contact(s) button is not disabled");
												sa.assertTrue(false, "Add Select Contact(s) button is not disabled");
											}
										}else {
											appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
											sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
										}
									}

									if(i==2) {

										contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWINV_MA']//a[text()='"+M17Contact1EmailId+"']/../..//input", M17Contact1FirstName+" "+M17Contact1LastName+" contact check box", action.SCROLLANDBOOLEAN, 30);
										scrollDownThroughWebelement(driver,contactcheckBox, "");
										if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
											if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "add select button", action.SCROLLANDBOOLEAN)) {
												appLog.info("click on Add select Contact(s) button");
												ThreadSleep(3000);
												String contactDetails= "Remove<break>"+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+"<break>"+getDateAccToTimeZone("America/New_York", "MM/dd/YYYY")+"<break>"+M17Contact1EmailId+"<break>"+M17Institution1;
												SoftAssert result = fp.verifySelectedContactsGridDataInContactAccess(Workspace.InvestorWorkspace,contactDetails);
												sa.combineAssertions(result);
												if(isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 20, CheckBoxName.View), "check box")) {
													appLog.info("View Check box is checked by default checked");
												}else {
													appLog.error("View check box is not checked by default");
													sa.assertTrue(false, "View check box is not checked by default");
												}
												if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Download), "check box")) {
													appLog.info("Download Check box is not checked by default checked");
												}else {
													appLog.error("Download Check box is checked by default checked");
													sa.assertTrue(false, "Download Check box is checked by default checked");
												}
												if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Upload), "check box")) {
													appLog.info("Upload Check box is not checked by default checked");
												}else {
													appLog.error("Upload Check box is checked by default checked");
													sa.assertTrue(false, "Upload Check box is checked by default checked");
												}
												if(click(driver, fp.getContactAccessCancelBtn(Workspace.InvestorWorkspace, 20), "cancel buton", action.SCROLLANDBOOLEAN)) {
													appLog.info("Clicked on contact access cancel button");
													if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
														appLog.info("clicked on contact access icon");
														ThreadSleep(3000);
														if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.InvestorWorkspace, 20), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
															appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
														}else {
															appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
															sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
														}


													}else {
														appLog.error("Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
														sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
													}

												}else {
													appLog.error("Not able to click on contact access cancel button so cannot check cancel button functionality");
													sa.assertTrue(false, "Not able to click on contact access cancel button so cannot check cancel button functionality");
												}

											}else {
												appLog.error("Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
												sa.assertTrue(false, "Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
											}
										}else {
											appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
											sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
										}

									}

									WebElement remove;
									if(i==3) {

										if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
											appLog.error("Able to expad Contact Access");
											ThreadSleep(2000);
											contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWINV_MA']//a[text()='"+M17Contact1EmailId+"']/../..//input", M17Contact1FirstName+" "+M17Contact1LastName+" contact check box", action.SCROLLANDBOOLEAN, 30);
											
											if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
												if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "add select button", action.BOOLEAN)) {
													appLog.info("click on Add select Contact(s) button");
													ThreadSleep(3000);

													remove = fp.getcontactaccessremoveLink(Workspace.InvestorWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
													if (remove!=null) {
														if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
															appLog.info("clicked on remove link for : "+M17Contact1EmailId);
															ThreadSleep(2000);
															if (click(driver, fp.getContactAccessCrossIcon(Workspace.InvestorWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
																appLog.info("Clicked on Contact access Cross Icon");
															} else {
																appLog.error("Contact access Cross Icon is not clickable");
																sa.assertTrue(false, "Contact access close button is not clickable");
															}

														}else {
															appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
															sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
														}
													}
													else {
														appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
														sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
													}


												}else {
													appLog.error("Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
													sa.assertTrue(false, "Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
												}
											}else {
												appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
												sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
											}
										}else{
											appLog.error("Not able to expad Contact Access");
											sa.assertTrue(false, "Not able to expad Contact Access");

										}


									}
									
									if(i==4) {

										if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on contact access icon");
											ThreadSleep(3000);
											if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.InvestorWorkspace, 20), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
												appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
											}else {
												appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
												sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
											}

											if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
												appLog.error("Able to expad Contact Access");
												ThreadSleep(2000);

												if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
													if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "add select button", action.BOOLEAN)) {
														appLog.info("click on Add select Contact(s) button");
														ThreadSleep(3000);

														remove = fp.getcontactaccessremoveLink(Workspace.InvestorWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
														if (remove!=null) {
															if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
																appLog.info("clicked on remove link for : "+M17Contact1EmailId);
																ThreadSleep(2000);

																remove = fp.getcontactaccessremoveLink(Workspace.InvestorWorkspace, EnableDisable.Enable, M17Contact1EmailId, 5);
																if (remove==null) {
																	appLog.info("Contact is not in Grid after Clicking on Remove Link");
																} else {
																	appLog.error("Contact Should not be in Grid after Clicking on Remove Link");
																	sa.assertTrue(false, "Contact Should not be in Grid after Clicking on Remove Link");
																}	

																if (click(driver, fp.getCancelBtn(Workspace.InvestorWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
																	appLog.error("Clicked on Contact access Cancel Btn");	
																} else {
																	appLog.error("Contact access Cancel Btn is not clickable");
																	sa.assertTrue(false, "Contact Cancel Cancel Btn is not clickable");
																}

															}else {
																appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
																sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
															}
														}
														else {
															appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
															sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
														}


													}else {
														appLog.error("Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
														sa.assertTrue(false, "Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
													}
												}else {
													appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
													sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
												}
											}else{
												appLog.error("Not able to expad Contact Access");
												sa.assertTrue(false, "Not able to expad Contact Access");

											}


										}else {
											appLog.error("Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
											sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
										}

									}

									if(i==5) {

										if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on contact access icon");
											ThreadSleep(3000);
											if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.InvestorWorkspace, 20), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
												appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
											}else {
												appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
												sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
											}

											if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
												appLog.error("Able to expad Contact Access");
												ThreadSleep(2000);

												if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
													if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "add select button", action.BOOLEAN)) {
														appLog.info("click on Add select Contact(s) button");
														ThreadSleep(3000);

														remove = fp.getcontactaccessremoveLink(Workspace.InvestorWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
														if (remove!=null) {
															if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
																appLog.info("clicked on remove link for : "+M17Contact1EmailId);
																ThreadSleep(2000);

																remove = fp.getcontactaccessremoveLink(Workspace.InvestorWorkspace, EnableDisable.Enable, M17Contact1EmailId, 5);
																if (remove==null) {
																	appLog.info("Contact is not in Grid after Clicking on Remove Link");
																} else {
																	appLog.error("Contact Should not be in Grid after Clicking on Remove Link");
																	sa.assertTrue(false, "Contact Should not be in Grid after Clicking on Remove Link");
																}	

																if (click(driver, fp.getApplyBtn(Workspace.InvestorWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
																	appLog.info("Clicked on Contact access Apply Btn");
																	ThreadSleep(2000);

																	if (click(driver, fp.getCloseBtn(Workspace.InvestorWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
																		appLog.info("Clicked on Confirmation Close Btn");
																		ThreadSleep(2000);
																		if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
																			appLog.info("clicked on contact access icon");
																			ThreadSleep(3000);
																			if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.InvestorWorkspace, 20), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
																				appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
																			}else {
																				appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
																				sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
																			}


																		}else {
																			appLog.error("Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
																			sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
																		}
																	} else {
																		appLog.error("Confirmation Close Btn is not clickable");
																		sa.assertTrue(false, "Confirmation Close Btn is not clickable");
																	}
																} else {
																	appLog.error("Contact access Apply Btn is not clickable");
																	sa.assertTrue(false, "Contact Apply Cancel Btn is not clickable");
																}

															}else {
																appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
																sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
															}
														}
														else {
															appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
															sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
														}


													}else {
														appLog.error("Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
														sa.assertTrue(false, "Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
													}
												}else {
													appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
													sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
												}
											}else{
												appLog.error("Not able to expad Contact Access");
												sa.assertTrue(false, "Not able to expad Contact Access");

											}


										}else {
											appLog.error("Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
											sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
										}




									}

								}
							}else {
								appLog.error("Not able to expand contact access pop so cannot verify select add button functionality");
								sa.assertTrue(false, "Not able to expand contact access pop so cannot verify select add button functionality");
							}
						}else {
							appLog.error("Not able to click on contact access icon so cannot verify select add button functionality");
							sa.assertTrue(false, "Not able to click on contact access icon so cannot verify select add button functionality");
						}
					}else {
						appLog.error("Contact access pop up is not displaying in "+sharedfolderpath);
						sa.assertTrue(false, "Contact access pop up is not displaying in "+sharedfolderpath);
					}
				}else {
					appLog.error("Not able to click on folder: "+sharedfolderpath+" so cannot verify contact access icon");
					sa.assertTrue(false, "Not able to click on folder: "+sharedfolderpath+" so cannot verify contact access icon");
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot build fundraising workspace: "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build fundraising workspace: "+M17FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc046_VerifyStandardFolderContactAccess_Investor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String cols="<break>Contact Name<break>E-mail<break>Firm<break>Action<break>Contact Name<break>Granted Access On<break>E-mail<break>Firm<break><break><break>";
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		if (lp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
		
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver,fp.getInvestorWorkSpaceSection(30) , "investor workspace section");
				if (fp.verifyFolderPathdummy("", M17Institution1, "", null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("clicked on std folder");
				}
				else {
					appLog.error("could not click on std folder");
					sa.assertTrue(false, "could not click on std folder");
				}
				if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
					//verify UI
					if (fp.verifyUIOfContactAccess(Workspace.InvestorWorkspace, cols, M17Institution1)) {
						appLog.info("successfully verified UI of contact access");
					}
					else {
						appLog.error("could not verify UI of contact access");
						sa.assertTrue(false, "could not verify UI of contact access");
					}
					if (mouseOverOperation(driver, fp.uploadDocTooltipContactAccess(Workspace.InvestorWorkspace, 30))) {
						ThreadSleep(3000);
						if (getAttribute(driver, fp.uploadDocTooltipContactAccess(Workspace.InvestorWorkspace, 30), "contact access upload access i icon", "title").equalsIgnoreCase(fp.contactAccessUploadToolTipText)) {
							appLog.info("successfully verified contact access upload access i icon text");
						}
						else {
							appLog.error("contact access upload access i icon text is not correct");
							sa.assertTrue(false, "contact access upload access i icon text is not correct");
						}
					}
				}
				if (click(driver, fp.getContactAccessCrossIcon(Workspace.InvestorWorkspace, 30), "cross icon", action.SCROLLANDBOOLEAN)) {
					//verify UI is closed
					if (fp.getContactAccessHeadText(Workspace.InvestorWorkspace, 30)==null) {
						appLog.info("successfully closed contact access UI");
					}
					else {
						appLog.error("contact access UI is not closed");
						sa.assertTrue(false, "contact access UI is not closed");
					}
				}
				
				if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
					//verify UI
					if (fp.verifyUIOfContactAccess(Workspace.InvestorWorkspace, cols, M17Institution1)) {
						appLog.info("successfully verified UI of contact access");
					}
					else {
						appLog.error("could not verify UI of contact access");
						sa.assertTrue(false, "could not verify UI of contact access");
					}
				}
				if (click(driver, fp.cancelButtonContactAccessActive(Workspace.InvestorWorkspace), "cancel button", action.SCROLLANDBOOLEAN)) {
					//verify UI is closed
					if (fp.getContactAccessHeadText(Workspace.InvestorWorkspace, 30)==null) {
						appLog.info("successfully closed contact access UI");
					}
					else {
						appLog.error("contact access UI is not closed");
						sa.assertTrue(false, "contact access UI is not closed");
					}
				}
				if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
					if (mouseOverOperation(driver, fp.getMouseOverIconContactAccessimg(30))) {
						ThreadSleep(3000);
						if (getAttribute(driver, fp.getMouseOverIconContactAccess(30), "contact access i icon", "title").equalsIgnoreCase(fp.contactAccessHoverText)) {
							appLog.info("successfully verified contact access i icon text");
						}
						else {
							appLog.error("contact access i icon text is not correct");
							sa.assertTrue(false, "contact access i icon text is not correct");
						}
					}
				}
				//verify firm column
				List<String> firms = new ArrayList<String>();
				firms.add(M17Institution1);
				//open section in contact access
				if (fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
					if (fp.compareFirmColumnContactAccess(Workspace.InvestorWorkspace, firms)) {
						appLog.info("successfully found firm names are correct");
					}
					else {
						appLog.error("could not find firm names as correct");
						sa.assertTrue(false, "could not find firm names as correct");
					}
					//click on contact 1 checkbox
					click(driver, fp.checkboxOnContactAccessUI(Workspace.InvestorWorkspace, M17Contact1EmailId), "checkbox of "+M17Contact1FirstName, action.SCROLLANDBOOLEAN);
					if (click(driver, fp.cancelButtonContactAccessActive(Workspace.InvestorWorkspace), "cancel button", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
							if (fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {

								if (!isSelected(driver,  fp.checkboxOnContactAccessUI(Workspace.InvestorWorkspace, M17Contact1EmailId), "checkbox of "+M17Contact1FirstName)) {
									appLog.info("after clicking cancel button checkbox for contact 1 is unchecked as expected");
								}
								else {
									appLog.error("after clicking cancel button checkbox for contact 1 is checked but it should not be");
									sa.assertTrue(false, "after clicking cancel button checkbox for contact 1 is checked but it should not be");
								}
							}
							else {
								appLog.error("could not expand contact access");
								sa.assertTrue(false, "could not expand contact access");
							}
						}
						else {
							appLog.error("contact access icon is not clickable");
							sa.assertTrue(false, "contact access icon is not clickable");
						}
					}
					else {
						appLog.error("cancel button is not clickable on contact access");
						sa.assertTrue(false, "cancel button is not clickable on contact access");
					}
					
				click(driver, fp.getContactAccessCrossIcon(Workspace.InvestorWorkspace, 30), "cross icon", action.SCROLLANDBOOLEAN);
				// AZHAR ADDED missing step8 id- 00027629
				if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
					if (fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
						if (click(driver, fp.checkboxOnContactAccessUI(Workspace.InvestorWorkspace, M17Contact1EmailId), "checkbox of "+M17Contact1FirstName, action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "add select contact btn", action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getcontactaccessremoveLink(Workspace.InvestorWorkspace, EnableDisable.Enable, M17Contact1EmailId, 30), "Remove link", action.BOOLEAN)) {
									if (click(driver, fp.getApplyBtn(Workspace.InvestorWorkspace, 30), "apply button", action.SCROLLANDBOOLEAN)) {
										click(driver, fp.getCloseBtn(Workspace.InvestorWorkspace, 30), "close button", action.BOOLEAN);
										ThreadSleep(2000);
									}
									else {
										appLog.error("could not click on apply button in contact access");
										sa.assertTrue(false, "could not click on apply button in contact access");
									}
									
								}
								else {
									appLog.error("remove link is not clickable");
									sa.assertTrue(false, "remove link is not clickable");
								}
							}
							else {
								appLog.error("add selected contacts btn is not clickable");
								sa.assertTrue(false, "add selected contacts btn is not clickable");
							}
						}
						else {
							appLog.error("contact checkbox is not clickable");
							sa.assertTrue(false, "contact checkbox is not clickable");
						}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}

				}
				else {
					appLog.error("could not open minus icon on contact access");
					sa.assertTrue(false, "could not open minus icon on contact access");
				}
			//	click(driver, fp.getContactAccessCrossIcon(Workspace.InvestorWorkspace, 30), "cross icon", action.SCROLLANDBOOLEAN);
				if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
					if (fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
						//click on email link
						click(driver, FindElement(driver, "//a[text()='"+M17Contact1EmailId+"']", "contact email id link", action.SCROLLANDBOOLEAN, 30), "contact email id link", action.BOOLEAN);
						if (nim.verifyNavatarSalesTeamLinkFunctionality("other")) {
							appLog.info("successfully verified email link");
						}
						else {
							appLog.error("could not verify email link");
							sa.assertTrue(false, "could not verify email link");
						}
					}
				}
				else {
					appLog.error("contact access icon is not clickable");
					sa.assertTrue(false, "contact access icon is not clickable");
				}
					switchToDefaultContent(driver);
				}
				else {
					appLog.error("could not open minus icon on contact access");
					sa.assertTrue(false, "could not open minus icon on contact access");
				}
			}
			else {
				appLog.error("could not find "+M17FundName1);
				sa.assertTrue(false, "could not find fund "+M17FundName1);
			}
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc047_verifyContactAccessPopUpOnSTDFolderInInvestorWorkSpace(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		//		String standardfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
	//	String sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		//		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		//		String internalfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		WebElement remove=null;
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if(fp.verifyFolderPathdummy("", M17Institution1, null, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
					if(fp.getContactAccessIcon(Workspace.InvestorWorkspace, 20)!=null) {
						appLog.info("Contact access icon is displaying in "+M17Institution1);
						if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
							ThreadSleep(5000);
							if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
								for(int i=0; i<6; i++) {
									WebElement contactcheckBox=null;
									if(i!=3) {
										contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWINV_MA']//a[text()='"+M17Contact1EmailId+"']/../..//input", M17Contact1FirstName+" "+M17Contact1LastName+" contact check box", action.SCROLLANDBOOLEAN, 10);
										scrollDownThroughWebelement(driver,contactcheckBox, "");
									}

									ThreadSleep(3000);
									if(i==0) {
										if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
											if(isEnabled(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "select add button")) {
												appLog.info("Add select Contact(s) button is enabled");
											}else {
												appLog.error("Add Select Contact(s) button is not enabled");
												sa.assertTrue(false, "Add Select Contact(s) button is not enabled");
											}
										}else {
											appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
											sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
										}
									}
									if(i==1) {
										if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
											if(!isEnabled(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "select add button")) {
												appLog.info("Add select Contact(s) button is disabled");
											}else {
												appLog.error("Add Select Contact(s) button is not disabled");
												sa.assertTrue(false, "Add Select Contact(s) button is not disabled");
											}
										}else {
											appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
											sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
										}
									}

									if(i==2) {

										contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWINV_MA']//a[text()='"+M17Contact1EmailId+"']/../..//input", M17Contact1FirstName+" "+M17Contact1LastName+" contact check box", action.SCROLLANDBOOLEAN, 30);
										scrollDownThroughWebelement(driver,contactcheckBox, "");
										if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
											if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "add select button", action.SCROLLANDBOOLEAN)) {
												appLog.info("click on Add select Contact(s) button");
												ThreadSleep(3000);
												String contactDetails= "Remove<break>"+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+"<break>"+getDateAccToTimeZone("America/New_York", "MM/dd/YYYY")+"<break>"+M17Contact1EmailId+"<break>"+M17Institution1;
												SoftAssert result = fp.verifySelectedContactsGridDataInContactAccess(Workspace.InvestorWorkspace,contactDetails);
												sa.combineAssertions(result);
												if(isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 20, CheckBoxName.View), "check box")) {
													appLog.info("View Check box is checked by default checked");
												}else {
													appLog.error("View check box is not checked by default");
													sa.assertTrue(false, "View check box is not checked by default");
												}
												if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Download), "check box")) {
													appLog.info("Download Check box is not checked by default checked");
												}else {
													appLog.error("Download Check box is checked by default checked");
													sa.assertTrue(false, "Download Check box is checked by default checked");
												}
												if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Upload), "check box")) {
													appLog.info("Upload Check box is not checked by default checked");
												}else {
													appLog.error("Upload Check box is checked by default checked");
													sa.assertTrue(false, "Upload Check box is checked by default checked");
												}
												if(click(driver, fp.getContactAccessCancelBtn(Workspace.InvestorWorkspace, 20), "cancel buton", action.SCROLLANDBOOLEAN)) {
													appLog.info("Clicked on contact access cancel button");
													if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
														appLog.info("clicked on contact access icon");
														ThreadSleep(3000);
														if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.InvestorWorkspace, 20), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
															appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
														}else {
															appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
															sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
														}


													}else {
														appLog.error("Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
														sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
													}

												}else {
													appLog.error("Not able to click on contact access cancel button so cannot check cancel button functionality");
													sa.assertTrue(false, "Not able to click on contact access cancel button so cannot check cancel button functionality");
												}

											}else {
												appLog.error("Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
												sa.assertTrue(false, "Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
											}
										}else {
											appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
											sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
										}

									}

									if(i==3) {

										if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
											appLog.error("Able to expad Contact Access");
											ThreadSleep(2000);
											contactcheckBox=FindElement(driver, "//div[@id='shwTopGridBWINV_MA']//a[text()='"+M17Contact1EmailId+"']/../..//input", M17Contact1FirstName+" "+M17Contact1LastName+" contact check box", action.SCROLLANDBOOLEAN, 30);
											
											if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
												if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "add select button", action.BOOLEAN)) {
													appLog.info("click on Add select Contact(s) button");
													ThreadSleep(3000);

													remove = fp.getcontactaccessremoveLink(Workspace.InvestorWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
													if (remove!=null) {
														if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
															appLog.info("clicked on remove link for : "+M17Contact1EmailId);
															ThreadSleep(2000);
															if (click(driver, fp.getContactAccessCrossIcon(Workspace.InvestorWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
																appLog.info("Clicked on Contact access Cross Icon");
															} else {
																appLog.error("Contact access Cross Icon is not clickable");
																sa.assertTrue(false, "Contact access close button is not clickable");
															}

														}else {
															appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
															sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
														}
													}
													else {
														appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
														sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
													}


												}else {
													appLog.error("Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
													sa.assertTrue(false, "Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
												}
											}else {
												appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
												sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
											}
										}else{
											appLog.error("Not able to expad Contact Access");
											sa.assertTrue(false, "Not able to expad Contact Access");

										}


									}
									
									if(i==4) {

										if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on contact access icon");
											ThreadSleep(3000);
											if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.InvestorWorkspace, 20), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
												appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
											}else {
												appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
												sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
											}

											if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
												appLog.error("Able to expad Contact Access");
												ThreadSleep(2000);

												if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
													if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "add select button", action.BOOLEAN)) {
														appLog.info("click on Add select Contact(s) button");
														ThreadSleep(3000);

														remove = fp.getcontactaccessremoveLink(Workspace.InvestorWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
														if (remove!=null) {
															if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
																appLog.info("clicked on remove link for : "+M17Contact1EmailId);
																ThreadSleep(2000);

																remove = fp.getcontactaccessremoveLink(Workspace.InvestorWorkspace, EnableDisable.Enable, M17Contact1EmailId, 5);
																if (remove==null) {
																	appLog.info("Contact is not in Grid after Clicking on Remove Link");
																} else {
																	appLog.error("Contact Should not be in Grid after Clicking on Remove Link");
																	sa.assertTrue(false, "Contact Should not be in Grid after Clicking on Remove Link");
																}	

																if (click(driver, fp.getCancelBtn(Workspace.InvestorWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
																	appLog.error("Clicked on Contact access Cancel Btn");	
																} else {
																	appLog.error("Contact access Cancel Btn is not clickable");
																	sa.assertTrue(false, "Contact Cancel Cancel Btn is not clickable");
																}

															}else {
																appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
																sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
															}
														}
														else {
															appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
															sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
														}


													}else {
														appLog.error("Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
														sa.assertTrue(false, "Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
													}
												}else {
													appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
													sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
												}
											}else{
												appLog.error("Not able to expad Contact Access");
												sa.assertTrue(false, "Not able to expad Contact Access");

											}


										}else {
											appLog.error("Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
											sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
										}

									}

									if(i==5) {

										if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on contact access icon");
											ThreadSleep(3000);
											if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.InvestorWorkspace, 20), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
												appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
											}else {
												appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
												sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
											}

											if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
												appLog.error("Able to expad Contact Access");
												ThreadSleep(2000);

												if(click(driver, contactcheckBox, M17Contact1FirstName+" "+M17Contact1LastName+" check box", action.SCROLLANDBOOLEAN)) {
													if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "add select button", action.BOOLEAN)) {
														appLog.info("click on Add select Contact(s) button");
														ThreadSleep(3000);

														remove = fp.getcontactaccessremoveLink(Workspace.InvestorWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
														if (remove!=null) {
															if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
																appLog.info("clicked on remove link for : "+M17Contact1EmailId);
																ThreadSleep(2000);

																remove = fp.getcontactaccessremoveLink(Workspace.InvestorWorkspace, EnableDisable.Enable, M17Contact1EmailId, 5);
																if (remove==null) {
																	appLog.info("Contact is not in Grid after Clicking on Remove Link");
																} else {
																	appLog.error("Contact Should not be in Grid after Clicking on Remove Link");
																	sa.assertTrue(false, "Contact Should not be in Grid after Clicking on Remove Link");
																}	

																if (click(driver, fp.getApplyBtn(Workspace.InvestorWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
																	appLog.info("Clicked on Contact access Apply Btn");
																	ThreadSleep(2000);

																	if (click(driver, fp.getCloseBtn(Workspace.InvestorWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
																		appLog.info("Clicked on Confirmation Close Btn");
																		ThreadSleep(2000);
																		if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
																			appLog.info("clicked on contact access icon");
																			ThreadSleep(3000);
																			if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.InvestorWorkspace, 20), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
																				appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
																			}else {
																				appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
																				sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
																			}


																		}else {
																			appLog.error("Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
																			sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
																		}
																	} else {
																		appLog.error("Confirmation Close Btn is not clickable");
																		sa.assertTrue(false, "Confirmation Close Btn is not clickable");
																	}
																} else {
																	appLog.error("Contact access Apply Btn is not clickable");
																	sa.assertTrue(false, "Contact Apply Cancel Btn is not clickable");
																}

															}else {
																appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
																sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
															}
														}
														else {
															appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
															sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
														}


													}else {
														appLog.error("Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
														sa.assertTrue(false, "Not able to click Add Select Contact(s) button  so cannot add contact "+M17Contact1EmailId+" in select Contact grid");
													}
												}else {
													appLog.error("Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
													sa.assertTrue(false, "Not able to click on "+M17Contact1FirstName+" "+M17Contact1LastName+" check box so cannot check select add button functionality");
												}
											}else{
												appLog.error("Not able to expad Contact Access");
												sa.assertTrue(false, "Not able to expad Contact Access");

											}


										}else {
											appLog.error("Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
											sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check Contact Details in Select Contact(s) grid");
										}




									}

								}
							}else {
								appLog.error("Not able to expand contact access pop so cannot verify select add button functionality");
								sa.assertTrue(false, "Not able to expand contact access pop so cannot verify select add button functionality");
							}
						}else {
							appLog.error("Not able to click on contact access icon so cannot verify select add button functionality");
							sa.assertTrue(false, "Not able to click on contact access icon so cannot verify select add button functionality");
						}
					}else {
						appLog.error("Contact access pop up is not displaying in "+M17Institution1);
						sa.assertTrue(false, "Contact access pop up is not displaying in "+M17Institution1);
					}
				}else {
					appLog.error("Not able to click on folder: "+M17Institution1+" so cannot verify contact access icon");
					sa.assertTrue(false, "Not able to click on folder: "+M17Institution1+" so cannot verify contact access icon");
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot build fundraising workspace: "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build fundraising workspace: "+M17FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc048_inviteContactAndSendEMailToContact(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				if(fp.inviteContact(environment,mode,M17Institution1, M17Contact1EmailId,null, FolderType.Standard, null, null, null,null, Workspace.InvestorWorkspace, null)) {
					appLog.info(M17Contact1EmailId+" Contact is selected successfully ");
					ThreadSleep(2000);
					if(click(driver, fp.getApplyBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" apply button", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on apply button successfully");
						if(fp.getInviteContactSuccessfulErrorMsg(Workspace.InvestorWorkspace, 30)!=null) {
							String aa =fp.getInviteContactSuccessfulErrorMsg(Workspace.InvestorWorkspace, 30).getText().trim();
							if(aa.contains(FundsPageErrorMessage.InviteContactSuccessfulErrorMsg)) {
								appLog.info(FundsPageErrorMessage.InviteContactSuccessfulErrorMsg+" error message is matched ");
							}else {
								appLog.error(FundsPageErrorMessage.InviteContactSuccessfulErrorMsg+" error message is not matched ");
								sa.assertTrue(false, FundsPageErrorMessage.InviteContactSuccessfulErrorMsg+" error message is not matched ");
							}
						}else {
							appLog.error(FundsPageErrorMessage.InviteContactSuccessfulErrorMsg+" Error Message is not visible so cannot verify it");
							sa.assertTrue(false, FundsPageErrorMessage.InviteContactSuccessfulErrorMsg+" Error Message is not visible so cannot verify it");
						}
						if(fp.getInviteContactSuccessfulPopUpCrossIcon(Workspace.InvestorWorkspace, 20)!=null) {
							appLog.info("Cross icon is visible in confirmation pop up");
						}else {
							appLog.error("Cross icon is not visible in confirmation pop up");
							sa.assertTrue(false, "Cross icon is not visible in confirmation pop up");
						}
						if(click(driver, fp.getCloseBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" close button", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Close Buton");	
							
							
						}else {
							appLog.error("Not able to click on close button so cannot close confirmation popup");
							sa.assertTrue(false, "Not able to click on close button so cannot close confirmation popup");
							
						}
						if(fp.sendInvitationMail(Workspace.InvestorWorkspace, M17Contact1EmailId,M17Institution1,M17Contact1EmailId)) {
							appLog.info("Email send successfully to contact "+M17Contact1EmailId);
						}else {
							appLog.error("Not able to send email to contact "+M17Contact1EmailId+" from manage emails");
							sa.assertTrue(false, "Not able to send email to contact "+M17Contact1EmailId+" from manage emails");
						}
					}else {
						appLog.error("Not able to click on apply button so cannot invite contact "+M17Contact1EmailId+" and verify error message");
						sa.assertTrue(false, "Not able to click on apply button so cannot invite contact "+M17Contact1EmailId+" and verify error message");
					}
				}else {
					appLog.error("Not able to select Contact from contact access grid "+M17Contact1EmailId);
					sa.assertTrue(false, "Not able to select Contact from contact access grid "+M17Contact1EmailId);
				}
						
					
				
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
		}
			switchToDefaultContent(driver);
			lp.CRMlogout();
			sa.assertAll();
		}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc049_1_VerifyContentGridInvestorSide_Investor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.CommonPath);
		
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.StandardPath);
		
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		
		//Investor Side
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {

			for (int i = 0;i<4;i++) {
				if (fp.verifyFolderPathdummy(cmnPath.split(",")[i], null, null, null, PageName.CurrentInvestmentPgae,
						null, 60)) {
					appLog.info(" Folder Structure Verified: " + cmnPath.split(",")[i]);

				}
				else {
					appLog.error("could not find folder structure "+cmnPath.split(",")[i]);
					sa.assertTrue(false, "could not find folder structure "+cmnPath.split(",")[i]);
				}

			}
			for (int i = 0;i<2;i++) {
				if (fp.verifyFolderPathdummy(stdPath.split(",")[i], M17Institution1, M17LimitedPartner1, null, PageName.CurrentInvestmentPgae,
						null, 60)) {
					appLog.info(" Folder Structure Verified: " + stdPath.split(",")[i]);

				}
				else {
					appLog.error("could not find folder structure "+stdPath.split(",")[i]);
					sa.assertTrue(false, "could not find folder structure "+stdPath.split(",")[i]);
				}
			}
			//verify bulk download button
			
			if (ifp.getBulkDownloadIcon(5) == null) {
				appLog.info("Bulk Download Icon is not visible.");
			} else {
				appLog.error("Bulk Download Icon Should not be visible.");
				sa.assertTrue(false, "Bulk Download Icon Should not be visible.");
			}
		}
		lp.investorLogout();
		sa.assertAll();
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc049_2_VerifyContentGridInvestorSide_Investor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependentTc = "M17tc049_1_VerifyContentGridInvestorSide_Investor";
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependentTc,
				excelLabel.CommonPath);
		
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		if (lp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				appLog.info(cmnPath.split(",")[0]);
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");
				if (fp.verifyFolderPathdummy(cmnPath.split(",")[0], null, null, M17FundName1, PageName.ContactsPage,
						Workspace.InvestorWorkspace, 60)) {
					if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 30)!=null) {
						appLog.info("successfully found enabled download access icon");
					}
					else {
						appLog.error("download access icon is not enabled");
						sa.assertTrue(false,"download access icon is not enabled");
					}
					if ( cp.getViewAccessIcon(Workspace.InvestorWorkspace, 30)!=null) {
						appLog.info("successfully found enabled View access icon");
					}
					else {
						appLog.error("View access icon is not enabled");
						sa.assertTrue(false,"View access icon is not enabled");
					}
				}
				else {
					appLog.error("common path is not found on contacts page folder structure");
					sa.assertTrue(false, "common path is not found on contacts page folder structure");
				}
				if (fp.verifyFolderPathdummy("",M17Institution1 , "", M17FundName1, PageName.ContactsPage,
						Workspace.InvestorWorkspace, 60)) {
					if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 30)==null) {
						appLog.info("successfully absent enabled download access icon");
					}
					else {
						appLog.error("download access icon is enabled, but it should not be");
						sa.assertTrue(false,"download access icon is enabled, but it should not be");
					}
					if ( cp.getViewAccessIcon(Workspace.InvestorWorkspace, 30)!=null) {
						appLog.info("successfully found enabled View access icon");
					}
					else {
						appLog.error("View access icon is not enabled");
						sa.assertTrue(false,"View access icon is not enabled");
					}
				}
				else {
					appLog.error("folder path on contact page is not found");
					sa.assertTrue(false, "folder path on contact page is not found");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("contact not found on contacts page");
				sa.assertTrue(false, "contact not found on contacts page");
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		if (cp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "workspace section");
				if (fp.verifyFolderPathdummy("", M17Institution1, null, null, PageName.FundsPage,Workspace.InvestorWorkspace,60)) {
					if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "contact access icon", action.SCROLLANDBOOLEAN)) {
						if (fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
							//verify select contacts grid
							String contactDetails= "Remove<break>"+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+"<break>"+getDateAccToTimeZone("America/New_York", "MM/dd/YYYY")+"<break>"+M17Contact1EmailId+"<break>"+M17Institution1;
							SoftAssert result = fp.verifySelectedContactsGridDataInContactAccess(Workspace.InvestorWorkspace,contactDetails);
							sa.combineAssertions(result);
							if(isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 20, CheckBoxName.View), "check box")) {
								appLog.info("View Check box is checked by default checked");
							}else {
								appLog.error("View check box is not checked by default");
								sa.assertTrue(false, "View check box is not checked by default");
							}
							if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Download), "check box")) {
								appLog.info("Download Check box is not checked by default checked");
							}else {
								appLog.error("Download Check box is checked by default checked");
								sa.assertTrue(false, "Download Check box is checked by default checked");
							}
							//check and uncheck download checkbox
							if (click(driver, fp.uploadDownloadCheckboxContactAccess(Workspace.InvestorWorkspace, CheckBoxName.Download, M17Contact1EmailId), "checkbox of contact 1", action.SCROLLANDBOOLEAN)) {
								appLog.info("checked upload/download CheckBox");
								if (click(driver, fp.uploadDownloadCheckboxContactAccess(Workspace.InvestorWorkspace, CheckBoxName.Download, M17Contact1EmailId), "checkbox of contact 1", action.SCROLLANDBOOLEAN)) {
									appLog.info("Unchecked upload/download CheckBox");
									if (click(driver, fp.getApplyBtn(Workspace.InvestorWorkspace, 30), "apply button", action.BOOLEAN)) {
										appLog.info("Clicked on Apply Button");
										if (click(driver, fp.getCloseBtn(Workspace.InvestorWorkspace, 30), "Confirmation Close button", action.BOOLEAN)) {
											appLog.info("Clicked on Confirmation Close button");	
										}
										else {
											appLog.error("Confirmation Close button is not clickable");
											sa.assertTrue(false, "Confirmation Close button is not clickable");
										}
									}
									else {
										appLog.error("apply button is not clickable");
										sa.assertTrue(false, "apply button is not clickable");
									}
								}
								else {
									appLog.error("download checkbox is not clickable");
									sa.assertTrue(false, "download checkbox is not clickable");
								}
							}
							else {
								appLog.error("download checkbox is not clickable");
								sa.assertTrue(false, "download checkbox is not clickable");
							}
						}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}
					if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "contact access icon", action.SCROLLANDBOOLEAN)) {
			
							if (!isSelected(driver, fp.uploadDownloadCheckboxContactAccess(Workspace.InvestorWorkspace, CheckBoxName.Download, M17Contact1EmailId), "download checkbox")) {
								appLog.info("download checkbox is not selected as expected");
							}
							else {
								appLog.error("download checkbox is selected but it should not be");
								sa.assertTrue(false, "download checkbox is selected but it should not be");
							}
							
							//click on email link
							if(click(driver, FindElement(driver, "//a[text()='"+M17Contact1EmailId+"']", "contact email id link", action.SCROLLANDBOOLEAN, 30), "contact email id link", action.BOOLEAN)){
								if (nim.verifyNavatarSalesTeamLinkFunctionality("other")) {
									appLog.info("successfully verified email link");
								}
								else {
									appLog.error("could not verify email link");
									sa.assertTrue(false, "could not verify email link");
								}	
							}else{
								appLog.error("Not Able to Click on Email Link");
								sa.assertTrue(false, "Not Able to Click on Email Link");	
							}
							
						
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}
				}
				else {
					appLog.error("folder path is not found");
					sa.assertTrue(false, "folder path is not found");
				}
			}
			else {
				appLog.error(M17FundName1+" is not found on funds page");
				sa.assertTrue(false, M17FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc050_1_LoginWithHubToEnableBulkDownLoad() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.CommonPath);
		
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.StandardPath);
		
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		
		//Investor Side
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {

			for (int i = 0;i<4;i++) {
				if (fp.verifyFolderPathdummy(cmnPath.split(",")[i], null, null, null, PageName.CurrentInvestmentPgae,
						null, 60)) {
					appLog.info(" Folder Structure Verified: " + cmnPath.split(",")[i]);

				}
				else {
					appLog.error("could not find folder structure "+cmnPath.split(",")[i]);
					sa.assertTrue(false, "could not find folder structure "+cmnPath.split(",")[i]);
				}

			}
			for (int i = 0;i<2;i++) {
				if (fp.verifyFolderPathdummy(stdPath.split(",")[i], M17Institution1, M17LimitedPartner1, null, PageName.CurrentInvestmentPgae,
						null, 60)) {
					appLog.info(" Folder Structure Verified: " + stdPath.split(",")[i]);

				}
				else {
					appLog.error("could not find folder structure "+stdPath.split(",")[i]);
					sa.assertTrue(false, "could not find folder structure "+stdPath.split(",")[i]);
				}
			}
			//verify bulk download button
			
			if (ifp.getBulkDownloadIcon(5) == null) {
				appLog.info("Bulk Download Icon is not visible.");
			} else {
				appLog.error("Bulk Download Icon Should not be visible.");
				sa.assertTrue(false, "Bulk Download Icon Should not be visible.");
			}
		}
		lp.investorLogout();
		sa.assertAll();
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc050_2_LoginWithHubToEnableBulkDownLoad() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(HubUserName,HubPassword);
		String[] funds = {M17FundName1};
		
		for (String fund : funds) {
			if(fp.clickOnTab(TabName.FundsTab)) {
				if(fp.clickOnCreatedFund(fund)) {
					if (click(driver, fp.getEditButton(10), "Edit Button", action.BOOLEAN)) {
						appLog.error("Click on Edit Button : "+fund);
						ThreadSleep(1000);
						if (isSelected(driver, fp.getBulkDownLoadCheckBox(Workspace.InvestorWorkspace, 10), "Bulk DownLoad CheckBox")) {
							appLog.info("Bulk DownLoad is Already Checked for "+fund);	
						}else{
							if (click(driver, fp.getBulkDownLoadCheckBox(Workspace.InvestorWorkspace, 10), "Bulk DownLoad CheckBox", action.SCROLLANDBOOLEAN)) {
								appLog.info(fund+" clicked on Bulk DownLoad CheckBox : "+Workspace.InvestorWorkspace);	
							} else {
								appLog.error(fund+" Not able to click on Bulk DownLoad CheckBox : "+Workspace.InvestorWorkspace);
								sa.assertTrue(false, fund+" Not able to click on Bulk DownLoad CheckBox : "+Workspace.InvestorWorkspace);
							}	
						}
						ThreadSleep(2000);
						if (click(driver, fp.getSaveButton(10), "Save Button", action.SCROLLANDBOOLEAN)) {
							appLog.error("click on Save Button : "+fund);
						} else {
							appLog.error("Not able to click on Save Button : "+fund);
							sa.assertTrue(false, "Not able to click on Save Button : "+fund);
						}
						
					} else {
						appLog.error("Not able to click on Edit Button : "+fund);
						sa.assertTrue(false, "Not able to click on Edit Button : "+fund);
					}
				}else {
					appLog.error("Not able to click on created fund Name : "+fund);
					sa.assertTrue(false, "Not able to click on created fund Name : "+fund);
					
				}
			}else {
				appLog.error("Not able to click on fund tab");
				sa.assertTrue(false, "Not able to click on fund tab");
			}
			switchToDefaultContent(driver);
		
		}
		
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc050_3_LoginWithHunToEnableBulkDownLoad() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		String updatedFirmName= M17Institution1+"_INV";
		
		//Investor Side
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			
			//verify bulk download button
			
			if (ifp.getBulkDownloadIcon(10) != null) {
				appLog.info("Bulk Download Icon is visible.");
			} else {
				appLog.error("Bulk Download Icon Should be visible.");
				sa.assertTrue(false, "Bulk Download Icon Should be visible.");
			}
		}
		
		if (click(driver, af.getProfileLink(60), "profile link on investor login", action.SCROLLANDBOOLEAN)) {
			appLog.info("Click on Profile Link");
			if (click(driver, ip.getEditIcon(60), "edit icon on my profile page", action.SCROLLANDBOOLEAN)) {
				appLog.info("Click on Edit Icon on My Profile Tab");
				if (sendKeys(driver, ip.getFirstNameTextBox(60), M17Contact1UpdatedFirstName, "First name textbox in my profile page", action.SCROLLANDBOOLEAN)) {
					appLog.info("Entered value on First Name Text Box : "+M17Contact1UpdatedFirstName);
					if (sendKeys(driver,ip.getLastNameTextBox(60), M17Contact1UpdatedLastName, "Last name textbox in my profile page", action.SCROLLANDBOOLEAN)) {
						appLog.info("Entered value on Last Name Text Box : "+M17Contact1UpdatedLastName);
						if (click(driver,ip.getSaveButtonMyProfilePage(60), "save button on my profile page", action.SCROLLANDBOOLEAN)) {
							ThreadSleep(3000);
							appLog.info("Click onSave Btn on My Profile Tab");
						}else {
							appLog.error("save button is not clickable on my profile page");
							sa.assertTrue(false, "save button is not clickable on my profile page");
						}
					}
					else {
						appLog.error("last name textbox is not visible on my profile page");
						sa.assertTrue(false, "last name textbox is not visible on my profile page");
					}
				}
				else {
					appLog.error("first name textbox is not visible on my profile page");
					sa.assertTrue(false, "first name textbox is not visible on my profile page");
				}
			}
			else {
				appLog.error("edit icon is not clickable on my profile page.");
				sa.assertTrue(false, "edit icon is not clickable on my profile page.");
			}
		
		
		//
		
		if (click(driver, ip.getMyFirmProfileTab(60), "my firm profile tab on investor login", action.SCROLLANDBOOLEAN)) {
			appLog.info("Clicked on My Firm Profile Tab");
			if (click(driver, ip.getEditIcon(60),"edit icon on firm profile page" ,action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on Edit Icon on  My Firm Profile Tab");
				if (sendKeys(driver, ip.getFirmNameTextbox(60), updatedFirmName, "Firm Name Text Box", action.SCROLLANDBOOLEAN)) {
					appLog.info("Entered Firm Name on My Firm Profile Tab : "+"");
					if (click(driver, ip.getSaveButtonFirmProfile(60), "save button firm profile page", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(3000);
						ExcelUtils.writeData(updatedFirmName, "Contacts", excelLabel.Variable_Name, "M17Contact1", excelLabel.Contact_updated_firmname);
						appLog.info("Click onSave Btn on My Firm Profile Tab");
					}
					else {
						appLog.error("save button is not clickable on My Firm profile page");
						sa.assertTrue(false, "save button is not clickable on My Firm profile page");
					}
				}
				else {
					appLog.error("description text area is not visible on my firm profile page");
					sa.assertTrue(false, "description text area is not visible on my firm profile page");
				}
			}
			else {
				appLog.error("edit icon is not clickable on my firm profile page");
				sa.assertTrue(false, "edit icon is not clickable on my firm profile page");
			}
		}
		else {
			appLog.error("description text area is not visible on my firm profile page");
			sa.assertTrue(false, "my firm profile tab is not clickable on investor login");
		}
		
		}else {
			appLog.error("profile link is not clickable on investor login");
			sa.assertTrue(false, "profile link is not clickable on investor login");
		}
		
		
		lp.investorLogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
		
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc050_4_VerifyUpdatedInfoAndRemoveFunctionalityCRMSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		String msg;
	
		if (cp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "workspace section");
				if (fp.verifyFolderPathdummy("", M17Institution1, null, null, PageName.FundsPage,Workspace.InvestorWorkspace,60)) {
					if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "contact access icon", action.SCROLLANDBOOLEAN)) {
							//verify select contacts grid
							String contactDetails= "Remove<break>"+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+"<break>"+getDateAccToTimeZone("America/New_York", "MM/dd/YYYY")+"<break>"+M17Contact1EmailId+"<break>"+M17Contact1UpdatedFirmName;
							SoftAssert result = fp.verifySelectedContactsGridDataInContactAccess(Workspace.InvestorWorkspace,contactDetails);
							sa.combineAssertions(result);
							WebElement remove = isDisplayed(driver, FindElement(driver, "//a[text()='"+M17Contact1EmailId+"']/../../span/a[@title='Remove']", "remove link in front "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName, action.BOOLEAN, 60), "Visibility", 60, "Remove link");
							if (remove!=null) {
								if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on remove link");
									ThreadSleep(5000);
									
									WebElement popUpEle = fp.getContactAccessRemovedPopUpMsg(Workspace.InvestorWorkspace, 30);
									if (popUpEle!=null) {
										appLog.info("Access Remove PopUp Element Found");
										
										 popUpEle = fp.getContactAccessRemovedPopUpMsg(Workspace.InvestorWorkspace, 30);
											if (popUpEle!=null) {
												appLog.info("Access Remove PopUp Msg Element Found");
												msg= popUpEle.getText().trim();
												if (msg.equals(FundsPageErrorMessage.contactAccessRemovedPopUpMsg)) {
													appLog.info("Contact Access Removed Message Verified : "+msg);	
												} else {
													appLog.error("Contact Access Removed Message not Verified Actual : "+msg+" \t Expected : "+FundsPageErrorMessage.contactAccessRemovedPopUpMsg);
													sa.assertTrue(false, "Contact Access Removed Message not Verified Actual : "+msg+" \t Expected : "+FundsPageErrorMessage.contactAccessRemovedPopUpMsg);
												}
											} else {
												appLog.error("Access Remove PopUp Msg Element is null so cannot Check Msg");
												sa.assertTrue(false, "Access Remove PopUp Msg Element is null so cannot Check Msg");
											}
											
									
									} else {
										appLog.error("Access Remove PopUp Element is null so cannot Check Msg");
										sa.assertTrue(false, "Access Remove PopUp Element is null so cannot Check Msg");
									}
									
									if (click(driver, fp.getRemoveAccessClose(Workspace.InvestorWorkspace, 30), "remove access close button", action.BOOLEAN)) {
										appLog.error("Clicked on remove access close button");	
										
										popUpEle = fp.getContactAccessRemovedPopUpMsg(Workspace.InvestorWorkspace, 5);
										if (popUpEle==null) {
											appLog.info("Access Remove PopUp is closed after click on Closed Button");
										}else{
											appLog.error("Access Remove PopUp is not closed after click on Closed Button");
											sa.assertTrue(false, "Access Remove PopUp is not closed after click on Closed Button");	
										}
										
										if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.InvestorWorkspace, 60), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
											appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
										}else {
											appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
											sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
										}
										
										if (click(driver, fp.getContactAccessCrossIcon(Workspace.InvestorWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on Contact access Cross Icon");
										} else {
											appLog.error("Contact access Cross Icon is not clickable");
											sa.assertTrue(false, "Contact access close button is not clickable");
										}
										
										popUpEle = fp.getContactAccessCrossIcon(Workspace.InvestorWorkspace, 5);
										if (popUpEle==null) {
											appLog.info("Select Contact Access PopUp is closed after click on Cross Icon");
										}else{
											appLog.error("Select Contact Access PopUp is not closed after click on Cross Icon");
											sa.assertTrue(false, "Select Contact Access PopUp is not closed after click on Cross Icon");	
										}
									}
									else {
										appLog.error("remove access close button is not clickable");
										sa.assertTrue(false, "remove access close button is not clickable");
									}
								
								}else {
									appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
									sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
								}
							}
							else {
								appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
								sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
							}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}
				}
				else {
					appLog.error("folder path is not found : "+M17Institution1);
					sa.assertTrue(false, "folder path is not found : "+M17Institution1);
				}
			}
			else {
				appLog.error(M17FundName1+" is not found on funds page");
				sa.assertTrue(false, M17FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		switchToDefaultContent(driver);
		if (lp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				
				msg=ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace;
				if (cp.verifyErrorMessageOnPage(msg,
						cp.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),
						"Error Message after Removing Contact Access on Contact page for investor workspace")) {
					appLog.info("Error Message is verified on Contact page for investor workspace : "+msg);
				} else {
					sa.assertTrue(false,"Error Message is not verified on Contact page for investor workspace.Expected : "+msg+ "\t Actual : "
									+ getText(driver,
											cp.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),
											"Error Message after Removing Contact Access on Contact page for investor workspace",
											action.BOOLEAN));
				}
				
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("contact not found on contacts page");
				sa.assertTrue(false, "contact not found on contacts page");
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc050_5_CheckEffectAfterRemovingContactAccessInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		
		//Investor Side
		
		List<WebElement> dropdownList=allOptionsInDropDrop(driver, allfp.getFirmNameDropdown(60), "Drop Down List");
		List<String> result=compareMultipleList(driver, Org1FirmName, dropdownList);
		
		if (!result.isEmpty()) {
			appLog.info("Firm Name is not available in DropDown after removing Acces");	
		} else {
			appLog.error("Firm Name is available in DropDown after removing Acces");
			sa.assertTrue(false, "Firm Name is available in DropDown after removing Acces");
		}
		lp.investorLogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
		
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc051_deleteContactAndCheckImpactInContactAccessInINV(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(contact.deleteCreatedContact(M17Contact1FirstName, M17Contact1LastName)) {
			appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact is deleted");
		}else {
			appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact is not deleted");
			sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact is not deleted");
		}
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if(fp.verifyFolderPathdummy("", M17Institution1, null, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
						ThreadSleep(5000);
						if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
							List<String> contactname = new ArrayList<String>();
							contactname.add(M17Contact1EmailId);
							if(!fp.selectDeselectContactFromContactAccess(contactname, SelectDeselect.Select, AllOr1By1.OneByOne,null, Workspace.InvestorWorkspace,10).isEmpty()) {
								appLog.info(M17Contact1EmailId+"Contact is not visible in Contact Access ");
							}else {
								appLog.error(M17Contact1EmailId+"Contact is visible in Contact Access ");
								sa.assertTrue(false, M17Contact1EmailId+"Contact is visible in Contact Access ");
							}
						}else {
							appLog.error("Contact Access popup is not expanded so cannot verify  deleted contact "+M17Contact1EmailId);
							sa.assertTrue(false, "Contact Access popup is not expanded so cannot verify  deleted contact "+M17Contact1EmailId);
						}
						
					}else {
						appLog.error("Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
						sa.assertTrue(false, "Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
					}
					
				}else {
					appLog.error("Not able to click on folder: "+M17Institution1+" so cannot verify contact "+M17Contact1EmailId);
					sa.assertTrue(false, "Not able to click on folder: "+M17Institution1+" so cannot verify contact "+M17Contact1EmailId);
				}
				
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
		}
		switchToDefaultContent(driver);
		if(home.restoreValuesFromRecycleBin(M17Contact1FirstName+" "+M17Contact1LastName)) {
			appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact is restored form Recycle Bin");
		}else {
			appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact is not restored form Recycle Bin");
			sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact is not restored form Recycle Bin");
		}
		if(contact.updateEmailAddressOfCreatedContact(M17Contact1FirstName, M17Contact1LastName,"")) {
			appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact email is updated as blank");
		}else {
			appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact email is not updated as blank");
			sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact email is not updated as blank");
		}
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if(fp.verifyFolderPathdummy("", M17Institution1, null, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
						ThreadSleep(5000);
						if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
							List<String> contactname = new ArrayList<String>();
							contactname.add(M17Contact1EmailId);
							String [] ss= {FundsPageErrorMessage.ContactEmailblankErrorMsg,FundsPageErrorMessage.AllContactSelectErrorMsg};
							for (int i=0; i<ss.length; i++) {
								WebElement ele=null;
								if(i==0) {
									ele=fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20);
									
								}else {
									ele=fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, null, 20);
								}
								if(ele!=null) {
									ThreadSleep(3000);
									if(!clickUsingJavaScript(driver, ele, "check box ")) {
//									if(click(driver, ele, "check box", action.BOOLEAN)) {
										appLog.info("clicked on contact check box : "+M17Contact1FirstName+" "+M17Contact1LastName);
										ThreadSleep(2000);
										if (isAlertPresent(driver)) {
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											if (msg.trim().contains(ss[i])) {
												appLog.info(
														"Error Message is verified in contact access : "+ss[i]);
											} else {
												appLog.error(
														"Error Message is verified in contact access : "+ss[i]);
												sa.assertTrue(false,
														"Error Message is verified in contact access :"+ss[i]);
											}
										} else {
											appLog.error("Alert is not present so cannot verify alert message "+ss[i]);
											sa.assertTrue(false, "Alert is not present so cannot verify alert message "+ss[i]);
										}
										if(i==0) {
											if(!isEnabled(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 5), "select add button")) {
												appLog.info("Add select Contact(s) button is disabled");
											}else {
												appLog.error("Add Select Contact(s) button is not disabled");
												sa.assertTrue(false, "Add Select Contact(s) button is not disabled");
											}
										}
										if(i==1) {
											if(isEnabled(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "select add button")) {
												appLog.info("Add select Contact(s) button is enabled");
											}else {
												appLog.error("Add Select Contact(s) button is not enabled");
												sa.assertTrue(false, "Add Select Contact(s) button is not enabled");
											}
										}
									}else {
										appLog.error("Not able to click on Contact "+M17Contact1FirstName+" "+M17Contact1LastName);
										sa.assertTrue(false, "Not able to click on Contact "+M17Contact1FirstName+" "+M17Contact1LastName);
									}
								}else {
									appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" Contact is not visible so cannot verify error message "+ss[i]);
									sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" Contact is not visible so cannot verify error message "+ss[i]);
								}
							}
						}else {
							appLog.error("Contact Access popup is not expanded so cannot verify  deleted contact "+M17Contact1EmailId);
							sa.assertTrue(false, "Contact Access popup is not expanded so cannot verify  deleted contact "+M17Contact1EmailId);
						}
					}else {
						appLog.error("Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
						sa.assertTrue(false, "Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
					}
				}else {
					appLog.error("Not able to click on folder: "+M17Institution1+" so cannot verify contact "+M17Contact1EmailId);
					sa.assertTrue(false, "Not able to click on folder: "+M17Institution1+" so cannot verify contact "+M17Contact1EmailId);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
		}
		switchToDefaultContent(driver);
		if(contact.updateEmailAddressOfCreatedContact(M17Contact1FirstName, M17Contact1LastName,M17Contact1EmailId)) {
			appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact email is updated with "+M17Contact1EmailId);
		}else {
			appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact email is not updated with "+M17Contact1EmailId);
			sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact email is not updated with "+M17Contact1EmailId);
		}
		lp.CRMlogout();
		sa.assertAll();
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc052_1_deleteFolderAndInviteSameContactFormContactAccess(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
			String shrdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String commonPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		boolean flag = false;
		String[] folders = {commonPath,shrdPath};
		if (lp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver,fp.getInvestorWorkSpaceSection(30) , "investor workspace section");
				if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					for (int i=0; i<folders.length; i++) {
						String id=null;
						id=fp.getCreatedFolderId(folders[i], PageName.ManageFolderPopUp, 20);
						System.err.println("id>>>>>>"+id);
						if(id!=null) {
							if(fp.clickOnDeleteFolderButton(id)) {
								if(click(driver, fp.getFolderDeleteYesBtn(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), folders[i]+"yes button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on Yes button");
									flag =true;
								}else {
									appLog.error("Not able to click on Yes button so cannot delete folder "+folders[i]);
									sa.assertTrue(false, "Not able to click on Yes button so cannot delete folder "+folders[i]);
								}
							}else {
								appLog.error("Not able to click on folder: "+folders[i]+" delete icon so cannot check delete folder "+folders[i]);
								sa.assertTrue(false, "Not able to click on folder: "+folders[i]+" delete icon so cannot check delete folder "+folders[i]);
							}
						}else {
							appLog.error(folders[i]+" is not available in the manage folder structure so cannot click on folder "+folders[i]+" delete icon");
							sa.assertTrue(false, folders[i]+" is not available in the manage folder structure so cannot click on folder "+folders[i]+" delete icon");
						}
					}
					
					if(click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 20), "close button", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on manage Folder close button");
					}else {
						appLog.error("Not able to click on manage folder close button so cannot close manage folder pop up");
						sa.assertTrue(false, "Not able to click on manage folder close button so cannot close manage folder pop up");
					}
				}else {
					appLog.error("Not able to click on Manage folder icon so cannot delete Common and shared folders ");
					sa.assertTrue(false, "Not able to click on Manage folder icon so cannot delete Common and shared folders ");
				}
				switchToDefaultContent(driver);
				if(fp.inviteContact(environment,mode,M17Institution1, M17Contact1EmailId,null, FolderType.Standard, null, "Yes", null,null, Workspace.InvestorWorkspace, null)) {
					appLog.info("Contact invited "+M17Contact1FirstName+" "+M17Contact1LastName);
					switchToDefaultContent(driver);
					if(contact.deleteCreatedContact(M17Contact1FirstName, M17Contact1LastName)) {
						appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact is deleted");
					}else {
						appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact is not deleted");
						sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact is not deleted");
					}
					if (lp.clickOnTab(TabName.FundsTab)) {
						if (fp.clickOnCreatedFund(M17FundName1)) {
							switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
							scrollDownThroughWebelement(driver,fp.getInvestorWorkSpaceSection(30) , "investor workspace section");
							if (fp.verifyFolderPathdummy("", M17Institution1, "", null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
								appLog.info("clicked on folder "+M17Institution1);
								if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
									String contactDetails= "Remove<break>"+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+"<break>"+getDateAccToTimeZone("America/New_York", "MM/dd/YYYY")+"<break>"+M17Contact1EmailId+"<break>"+M17Contact1UpdatedFirmName;
									SoftAssert res = fp.verifySelectedContactsGridDataInContactAccess(Workspace.InvestorWorkspace, contactDetails);
									sa.combineAssertions(res);
									switchToDefaultContent(driver);
									if(home.restoreValuesFromRecycleBin(M17Contact1FirstName+" "+M17Contact1LastName)) {
										appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact is restored form Recycle Bin");
									}else {
										appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact is not restored form Recycle Bin");
										sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact is not restored form Recycle Bin");
									}
								}else {
									appLog.error("Not able to click on contact access icon so cannot verify contact details in selected grid "+M17Contact1FirstName+" "+M17Contact1LastName);
									sa.assertTrue(false, "Not able to click on contact access icon so cannot verify contact details in selected grid "+M17Contact1FirstName+" "+M17Contact1LastName);
								}
							}
							else {
								appLog.error("could not click on folder "+M17Institution1);
								sa.assertTrue(false, "could not click on folder "+M17Institution1);
							}
						}else {
							appLog.error("Not able to click on fund name "+M17FundName1);
							sa.assertTrue(false, "Not able to click on fund name "+M17FundName1);
						}
					}else {
						appLog.error("Not able to click on fund tab");
						sa.assertTrue(false, "Not able to click on fund tab");
					}
					if(flag) {
						if (lp.clickOnTab(TabName.FundsTab)) {
							if (fp.clickOnCreatedFund(M17FundName1)) {
								switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
								scrollDownThroughWebelement(driver,fp.getInvestorWorkSpaceSection(30) , "investor workspace section");
								if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
									if(fp.createFolderStructure(folders[0], FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
										appLog.info(folders[0]+ " folder structure is created.");
										ThreadSleep(2000);
										String subCommonPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.KeyWord_For_Search);	
										if(fp.createFolderStructure(subCommonPath, FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
											appLog.info(subCommonPath+ " folder structure is created.");
										} else {
											appLog.error(subCommonPath+ " folder structure is not created.");
											sa.assertTrue(false,subCommonPath+ " folder structure is not created.");
										}
									} else {
										appLog.error(folders[0]+ " folder structure is not created.");
										sa.assertTrue(false,folders[0]+ " folder structure is not created.");
									}
									if(fp.createFolderStructure(folders[1], FolderType.Shared, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
										appLog.info(folders[1]+ " folder structure is created.");
									} else {
										appLog.error(folders[1]+ " folder structure is not created.");
										sa.assertTrue(false,folders[1]+ " folder structure is not created.");
									}
									if(click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 20), "close button", action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on manage Folder close button");
									}else {
										appLog.error("Not able to click on manage folder close button so cannot close manage folder pop up");
										sa.assertTrue(false, "Not able to click on manage folder close button so cannot close manage folder pop up");
									}
								}else {
									appLog.error("Not able to click on Manage folder icon so cannot delete Common and shared folders ");
									sa.assertTrue(false, "Not able to click on Manage folder icon so cannot delete Common and shared folders ");
								}
							}else {
								appLog.error("could not find "+M17FundName1);
								sa.assertTrue(false, "could not find fund "+M17FundName1);
							}
						}else {
							appLog.error("Not able to click on fund tab");
							sa.assertTrue(false, "Not able to click on fund tab");
						}
						switchToDefaultContent(driver);
						if(contact.clickOnTab(TabName.ContactTab)) {
							if(contact.clickOnCreatedContact(M17Contact1FirstName,M17Contact1LastName, null)) {
								switchToFrame(driver, 30, contact.getFrame( PageName.ContactsPage, 30));
								if(fp.verifyFolderPathdummy(folders[0], null, null, M17FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)){
									appLog.info("Folder structure is verified on Contact page of '"+M17Institution1+"'.");
								} else {
									appLog.error("Folder structure is not verified on Contact page of '"+M17Institution1+"'.");
									sa.assertTrue(false,"Folder structure is not verified on Contact page of '"+M17Institution1+"'.");
								}
							}else {
								appLog.error("Not able to click on contact name "+M17Contact1FirstName+" "+M17Contact1LastName);
								sa.assertTrue(false, "Not able to click on contact name "+M17Contact1FirstName+" "+M17Contact1LastName);
							}
						}else {
							appLog.error("Not able to click on contact tab so cannot verify folder structure");
							sa.assertTrue(false, "Not able to click on contact tab so cannot verify folder structure");
						}
				}
					
				}else {
					appLog.error("Not able to invite Contact "+M17Contact1FirstName+" "+M17Contact1LastName);
					sa.assertTrue(false, "Not able to invite Contact "+M17Contact1FirstName+" "+M17Contact1LastName);
				}
			}else {
				appLog.error("could not find "+M17FundName1);
				sa.assertTrue(false, "could not find fund "+M17FundName1);
			}
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc052_2_verifyFolderStructureAtAtargetSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.CurrentInvestmentPgae,
					null, 60)) {
				appLog.info(" Folder Structure Verified: " + cmnPath);

			}
			else {
				appLog.error("could not find folder structure "+cmnPath);
				sa.assertTrue(false, "could not find folder structure "+cmnPath);
			}
			if (fp.verifyFolderPathdummy(stdPath, M17Institution1, M17LimitedPartner1, null, PageName.CurrentInvestmentPgae,
					null, 60)) {
				appLog.info(" Folder Structure Verified: " + stdPath);

			}
			else {
				appLog.error("could not find folder structure "+stdPath);
				sa.assertTrue(false, "could not find folder structure "+stdPath);
			}

		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc053_SearchAlreadyInvitedContactandTrytosendinvitationInINV(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		WebElement ele=null;
		String emptyString;
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy("", M17Institution1, "", null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					String[] ss ={FundsPageErrorMessage.alreadyInvitedContactAlertMsg,FundsPageErrorMessage.AllContactSelectErrorMsg};
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info(M17Contact1EmailId+" Contact is selected successfully ");
						if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
							for(int i=0; i<ss.length; i++){
								if(i==0){
									ThreadSleep(2000);
									if(sendKeys(driver,fp.getSearchTextBox(Workspace.InvestorWorkspace, 60), M17Contact1FirstName+" "+M17Contact1LastName,Workspace.InvestorWorkspace+" search text box", action.SCROLLANDBOOLEAN)) {
										appLog.info("enter the value in search text box : "+M17Contact1FirstName+" "+M17Contact1LastName);
										if(click(driver, fp.getSearchBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" search button", action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on  "+Workspace.InvestorWorkspace+" search button");
											ele=fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20);
										}else {
											appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" Contact is not visible so cannot verify error message "+FundsPageErrorMessage.alreadyInvitedContactAlertMsg);
											sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" Contact is not visible so cannot verify error message "+FundsPageErrorMessage.alreadyInvitedContactAlertMsg);
										}
									}else {
										appLog.error("Not able to click on "+Workspace.InvestorWorkspace+" search button so cannot invite contact: "+M17Contact1FirstName+" "+M17Contact1LastName);
										sa.assertTrue(false, "Not able to click on "+Workspace.InvestorWorkspace+" search button so cannot invite contact: "+M17Contact1FirstName+" "+M17Contact1LastName);
									}
								}
								if(i==1){
									if (click(driver, fp.getClearIconOnContactAccessSearchBox(Workspace.InvestorWorkspace, 10), "Clear Icon", action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on Cross Icon");
										emptyString = getValueFromElementUsingJavaScript(driver, fp.getSearchTextBox(Workspace.InvestorWorkspace, 60), "Search Box");
										
										if(emptyString.isEmpty()) {
											appLog.info("Search Box is Empty");
											
										} else {
											appLog.info("Search Box is not Empty with Value : "+emptyString);
											sa.assertTrue(false, "Search Box is not Empty with Value : "+emptyString);
										}
										
										String[] contacts ={M17Contact1FirstName+" "+M17Contact1LastName,M17Contact2FirstName+" "+M17Contact2LastName};
										
										for (String contact : contacts) {
											ele=fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, contact, 20);
											
											if (ele!=null) {
												appLog.error(contact+" is dispalying after clearing CheckBox");	
											} else {
												appLog.error(contact+" is not dispalying after clearing CheckBox");
												sa.assertTrue(false, contact+" is not dispalying after clearing CheckBox");
											}	
										}
										
										ele=fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, null, 20);
										
										
										
									} else {
										appLog.error("Not Able to Click on Cross Icon");
										sa.assertTrue(false, "Not Able to Click on Cross Icon");
									}
								}
								
								if(ele!=null) {
									ThreadSleep(3000);
									if(!clickUsingJavaScript(driver, ele, "check box")) {
//									if(click(driver, ele, "check box", action.BOOLEAN)) {
										appLog.info("clicked on check box ");
										ThreadSleep(2000);
										if (isAlertPresent(driver)) {
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											if (msg.trim().equals(ss[i])) {
												appLog.info(
														"Error Message is verified in contact access : "+msg);
											} else {
												appLog.error(
														"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+ss[i]);
												sa.assertTrue(false,
														"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+ss[i]);
											}
											if(i==0){
												ele=fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 10);
												if (isSelected(driver, ele, "Check Box")) {
													appLog.error("CheckBox Should be Unchecked after Accepting Alert");
													sa.assertTrue(false, "CheckBox Should be Unchecked after Accepting Alert");
												} else {
													appLog.info("CheckBox is Unchecked after Accepting Alert");
													
												}
											}
											
										} else {
											appLog.error("Alert is not present so cannot verify alert message "+ss[i]);
											sa.assertTrue(false, "Alert is not present so cannot verify alert message "+ss[i]);
										}
									}else {
										appLog.error("Not able to click on Checkbox");
										sa.assertTrue(false, "Not able to click on Checkbox of on Contact ");
									}
									
								}else{
									appLog.error("Checkbox Element is null");
									sa.assertTrue(false, "Checkbox Element is null");
									
								}
							}
						}else {
							appLog.error("Not able to expand contact access grid so cannot check error message : "+ss[0]+" and "+ss[1]);
							sa.assertTrue(false, "Not able to expand contact access grid so cannot check error message : "+ss[0]+" and "+ss[1]);
						}
						
					}else {
						appLog.error("Not able to click on Contact Access Icon so cannot check error message : "+ss[0]+" and "+ss[1]);
						sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check error message : "+ss[0]+" and "+ss[1]);	
					}
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
			}
		}else {
			appLog.error("Not able to click on "+M17Institution1+" folder");
			sa.assertTrue(false, "Not able to click on "+M17Institution1+" folder");
		}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc054_VerifyCheckboxFunctionalityforDownloadPrintDocumentsUnderContactAccesspopup(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if(fp.verifyFolderPathdummy("", M17Institution1, null, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
						ThreadSleep(5000);
						
						// DownLoad / Print Documents
						appLog.info("Going to Checked/Unchecked DownLoad / Print Documents");
						if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Download), "Download / Print Documents CheckBox", action.BOOLEAN)) {
							appLog.info("DownLoad CheckBox Checked");
							if(isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Download), "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is checked after Click on Download / Print Documents CheckBox");
							}else {
								appLog.error("Download Check box Should be checked after Click on Download / Print Documents CheckBox");
								sa.assertTrue(false, "Download Check box Should be checked after Click on Download / Print Documents CheckBox");
							}
							
							if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Download), "Download / Print Documents CheckBox", action.BOOLEAN)) {
								appLog.info("DownLoad CheckBox UnChecked");
								if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Download), "Download / Print Documents CheckBox")) {
									appLog.info("Download Check box is Unchecked after Click on Download / Print Documents CheckBox");
								}else {
									appLog.error("Download Check box Should be Unchecked after Click on Download / Print Documents CheckBox");
									sa.assertTrue(false, "Download Check box Should be Unchecked after Click on Download / Print Documents CheckBox");
								}
								
							} else {
								appLog.error("Not able to click on Download / Print Documents CheckBox");
								sa.assertTrue(false, "Not able to click on Download / Print Documents CheckBox");
							}
							
							
						} else {
							appLog.error("Not able to click on Download / Print Documents CheckBox for Contact : "+M17Contact1EmailId);
							sa.assertTrue(false, "Not able to click on Download / Print Documents CheckBox  for Contact : "+M17Contact1EmailId);
						}
						
						// Upload Documents
						appLog.info("Going to Checked/Unchecked Upload CheckBox");
						if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Upload), "Upload CheckBox", action.BOOLEAN)) {
							appLog.info("Upload CheckBox Checked");
							if(isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Upload), "Upload CheckBox")) {
								appLog.info("Upload Check box is checked after Click on Upload CheckBox");
							}else {
								appLog.error("Upload Check box Should be checked after Click on Upload CheckBox");
								sa.assertTrue(false, "Upload Check box Should be checked after Click on Upload CheckBox");
							}
							
							if(isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Download), "Download CheckBox")) {
								appLog.info("Download Check box is checked after Click on Upload CheckBox");
							}else {
								appLog.error("Download Check box Should be checked after Click on Upload CheckBox");
								sa.assertTrue(false, "Download Check box Should be checked after Click on Upload CheckBox");
							}
							
							
							if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Upload), "Upload CheckBox", action.BOOLEAN)) {
								appLog.info("Upload CheckBox UnChecked");
								if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Upload), "Upload CheckBox")) {
									appLog.info("Upload Check box is Unchecked after Click on Upload CheckBox");
								}else {
									appLog.error("Upload Check box Should be Unchecked after Click on Upload CheckBox");
									sa.assertTrue(false, "Download Check box Should be Unchecked after Click on Upload CheckBox");
								}
								
								if(isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Download), "Upload CheckBox")) {
									appLog.info("Download Check box is checked after Click on Upload CheckBox");
								}else {
									appLog.error("Download Check box Should be checked after Click on Upload CheckBox");
									sa.assertTrue(false, "Download Check box Should be checked after Click on Upload CheckBox");
								}
								
								//
								appLog.info("Going to Checked/Unchecked Upload/Download CheckBox");
								if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Upload), "Upload CheckBox", action.BOOLEAN)) {
									appLog.info("Click on Upload CheckBox for Checked");	
									
									if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Download), "Download / Print Documents CheckBox", action.BOOLEAN)) {
										appLog.info("Click on Download CheckBox for Uncheck");		
										
										if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Upload), "Upload CheckBox")) {
											appLog.info("Upload Check box is Unchecked by clicking on Upload and then Download CheckBox");
										}else {
											appLog.error("Upload Check box Should be Unchecked by clicking on Upload and then Download CheckBox");
											sa.assertTrue(false, "Download Check box Should be Unchecked by clicking on Upload and then Download CheckBox");
										}
										
										if(!isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Download), "Download / Print Documents CheckBox")) {
											appLog.info("Download Check box is Unchecked by clicking on Upload and then Download CheckBox");
										}else {
											appLog.error("Download Check box Should be Unchecked by clicking on Upload and then Download CheckBox");
											sa.assertTrue(false, "Download Check box Should be Unchecked by clicking on Upload and then Download CheckBox");
										}
										
										appLog.info("Going to Provide Download / Print Documents Permission to Contact : "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
										
										if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Download), "Download / Print Documents CheckBox Permission", action.BOOLEAN)) {
											appLog.info("Click on Download CheckBox for permission");	
											
											if(click(driver, fp.getApplyBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" apply button", action.SCROLLANDBOOLEAN)) {
												appLog.info("Clicked on apply button successfully");
												
												if(click(driver, fp.getCloseBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" close button", action.SCROLLANDBOOLEAN)) {
													appLog.info("Clicked on Close Buton");	
												}else {
													appLog.error("Not able to click on close button");
													sa.assertTrue(false, "Not able to click on close button");
												}
												
											
											}else{
												appLog.error("Not able to Click on apply button");
												sa.assertTrue(false, "Not able to Click on apply button")	;
											}
										} else {
											appLog.error("Not able to Click on Download CheckBox for permission");
											sa.assertTrue(false, "Not able to Click on Download CheckBox for permission");
										}
										
									} else {
										appLog.error("Not able to click on Download / Print Documents CheckBox");
										sa.assertTrue(false, "Not able to click on Download / Print Documents CheckBox");
									}
								
								} else {
									appLog.error("Not able to click on Upload CheckBox");
									sa.assertTrue(false, "Not able to click on Upload CheckBox");
								}
								
							} else {
								appLog.error("Not able to click on Upload CheckBox");
								sa.assertTrue(false, "Not able to click on Upload CheckBox ");
							}
							
							
						} else {
							appLog.error("Not able to click on Upload CheckBox for Contact : "+M17Contact1EmailId);
							sa.assertTrue(false, "Not able to click on Upload CheckBox  for Contact : "+M17Contact1EmailId);
						}
						
					}else {
						appLog.error("Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
						sa.assertTrue(false, "Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
					}
					
				}else {
					appLog.error("Not able to click on folder: "+M17Institution1+" so cannot verify contact "+M17Contact1EmailId);
					sa.assertTrue(false, "Not able to click on folder: "+M17Institution1+" so cannot verify contact "+M17Contact1EmailId);
				}
				
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
		}
		switchToDefaultContent(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		if (cp.clickOnTab(TabName.ContactTab)) {
		if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
			switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
			
			scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");
			if (fp.verifyFolderPathdummy(cmnPath, null, null, M17FundName1, PageName.ContactsPage,
					Workspace.InvestorWorkspace, 60)) {
				if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 10)!=null) {
					appLog.info("successfully found enabled download access icon");
				}
				else {
					appLog.error("download access icon is not enabled");
					sa.assertTrue(false,"download access icon is not enabled");
				}
				if ( cp.getViewAccessIcon(Workspace.InvestorWorkspace, 10)!=null) {
					appLog.info("successfully found enabled View access icon");
				}
				else {
					appLog.error("View access icon is not enabled");
					sa.assertTrue(false,"View access icon is not enabled");
				}
			}
			else {
				appLog.error("common path is not found on contacts page folder structure");
				sa.assertTrue(false, "common path is not found on contacts page folder structure");
			}
			if (fp.verifyFolderPathdummy("",M17Institution1 , M17LimitedPartner1, M17FundName1, PageName.ContactsPage,
					Workspace.InvestorWorkspace, 60)) {
				if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 10)!=null) {
					appLog.info("successfully absent enabled download access icon");
				}
				else {
					appLog.error("download access icon is enabled, but it should not be");
					sa.assertTrue(false,"download access icon is enabled, but it should not be");
				}
				if ( cp.getViewAccessIcon(Workspace.InvestorWorkspace, 10)!=null) {
					appLog.info("successfully found enabled View access icon");
				}
				else {
					appLog.error("View access icon is not enabled");
					sa.assertTrue(false,"View access icon is not enabled");
				}
			}
			else {
				appLog.error(M17Institution1+" folder path on contact page is not found");
				sa.assertTrue(false, M17Institution1+" folder path on contact page is not found");
			}
			switchToDefaultContent(driver);
		}
		else {
			appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
			sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
		}
	}
	else {
		appLog.error("contact tab is not clickable");
		sa.assertTrue(false, "contact tab is not clickable");
	}
	
		lp.CRMlogout();
		sa.assertAll();
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc055_VerifyFolderStructureAndBulkDownloadInvestorside() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		String updatedFirmName= M17Institution1+"_INV";
		
		//Investor Side
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			
			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");
			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}
			
		}
	
		
		
		lp.investorLogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc056_1_updateUploadPermissionForInvitedContactInFR(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if(fp.verifyFolderPathdummy("", M17Institution1, null, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
						ThreadSleep(5000);
						appLog.info("clicked on contact access icon");
						if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Upload), "Upload Documents CheckBox", action.BOOLEAN)) {
							appLog.info("upload CheckBox Checked");
							if(click(driver, fp.getApplyBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" apply button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on apply button successfully");
								if(click(driver, fp.getCloseBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" close button", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on Close Buton");	
									if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
										if(isSelected(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Upload), "Upload Documents CheckBox")) {
											appLog.info("Download Check box is checked after Click and apply upload permission");
										}else {
											appLog.error("Download Check box is not checked after Click and apply upload permission");
											sa.assertTrue(false, "Download Check box is not checked after Click and apply upload permission");
										}
									}else {
										appLog.error("Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
										sa.assertTrue(false, "Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
									}

								}else {
									appLog.error("Not able to click on close button");
									sa.assertTrue(false, "Not able to click on close button");
								}
							}else{
								appLog.error("Not able to Click on apply button");
								sa.assertTrue(false, "Not able to Click on apply button")	;
							}
						} else {
							appLog.error("Not able to click on Upload Documents CheckBox for Contact : "+M17Contact1EmailId);
							sa.assertTrue(false, "Not able to click on Upload Documents CheckBox  for Contact : "+M17Contact1EmailId);
						}
					}else {
						appLog.error("Not able to click on contact access icon cannot check upload permission contact "+M17Contact1EmailId);
						sa.assertTrue(false, "Not able to click on contact access icon cannot verify  deleted contact "+M17Contact1EmailId);
					}
				}else {
					appLog.error("Not able to click on folder: "+M17Institution1+" so cannot check upload permission  "+M17Contact1EmailId);
					sa.assertTrue(false, "Not able to click on folder: "+M17Institution1+" so cannot check upload permission "+M17Contact1EmailId);
				}
				
			}else {
				appLog.error("Not able to click on created fund Name so cannot updated upload permission for invited contact "+M17Contact1EmailId);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot updated upload permission for invited contact "+M17Contact1EmailId);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot updated upload permission for invited contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on fund tab so cannot updated upload permission for invited contact "+M17Contact1EmailId);
		}
		switchToDefaultContent(driver);
		if (lp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");
				if (fp.verifyFolderPathdummy(cmnPath, null, null, M17FundName1, PageName.ContactsPage,Workspace.InvestorWorkspace, 60)) {
					if ( cp.getUploadAccessIcon(Workspace.InvestorWorkspace, 5)==null) {
						appLog.info("successfully found disabled upload access icon");
					}
					else {
						appLog.error("successfully found disabled upload access icon");
						sa.assertTrue(false,"successfully found disabled upload access icon");
					}
				}else {
					appLog.error("common path is not found on contacts page folder structure");
					sa.assertTrue(false, "common path is not found on contacts page folder structure");
				}
				if (fp.verifyFolderPathdummy("",M17Institution1 ,M17LimitedPartner1, M17FundName1, PageName.ContactsPage,Workspace.InvestorWorkspace, 60)) {
					if ( cp.getUploadAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
						appLog.info("successfully found enabled upload access icon");
					}
					else {
						appLog.error("successfully found enabled upload access icon");
						sa.assertTrue(false,"successfully found enabled upload access icon");
					}
				}
				else {
					appLog.error("folder path on contact page is not found");
					sa.assertTrue(false, "folder path on contact page is not found");
				}
			}else {
				appLog.error("contact not found on contacts page");
				sa.assertTrue(false, "contact not found on contacts page");
			}
		}else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc056_2_verifyUploadpermissionAtTargetSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.CurrentInvestmentPgae,
					null, 60)) {
				appLog.info(" Folder Structure Verified: " + cmnPath);
				if(ifp.getUploadIcon(5)==null) {
					appLog.info("Upload button is not visible ");
				}else {
					appLog.error("Upload button is visible");
					sa.assertTrue(false, "Upload button is visible");
				}
			}else {
				appLog.error("could not find folder structure "+cmnPath);
				sa.assertTrue(false, "could not find folder structure "+cmnPath);
			}
			if (fp.verifyFolderPathdummy(stdPath, M17Institution1, M17LimitedPartner1, null, PageName.CurrentInvestmentPgae,
					null, 60)) {
				appLog.info(" Folder Structure Verified: " + stdPath);
				if(ifp.getUploadIcon(5)!=null) {
					appLog.info("Upload button is visible ");
				}else {
					appLog.error("Upload button is not visible");
					sa.assertTrue(false, "Upload button is not visible");
				}

			}else {
				appLog.error("could not find folder structure "+stdPath);
				sa.assertTrue(false, "could not find folder structure "+stdPath);
			}

		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc057_TryToProvideAccessFromParentfolderWhenAccessAlreadyProvidedfromFirmAccountFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		WebElement ele=null;
		String alertMsg =FundsPageErrorMessage.alreadyInvitedContactAlertMsg;
		String msg;
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M17Institution1, M17LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+M17Institution1+"/"+M17LimitedPartner1+"/"+stdPath);
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
						
						
						ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Download);
						if(isSelected(driver, ele, "Download / Print Documents CheckBox")) {
							appLog.info("Download Check box is checked as Contact is Invited From Parent Folder");
						}else {
							appLog.error("Download Check box Should be checked after Click on Download / Print Documents CheckBox as Contact is Invited From Parent Folder");
							sa.assertTrue(false, "Download Check box Should be checked after Click on Download / Print Documents CheckBox as Contact is Invited From Parent Folder");
						}
						
						if(!isEnabled(driver, ele, "Download Icon")) {
							appLog.info("Download Check box is Disabled as Contact is Invited From Parent Folder");
						}else {
							appLog.error("Download Check box Should be Disabled as Contact is Invited From Parent Folder");
							sa.assertTrue(false, "Download Check box Should be Disabled as Contact is Invited From Parent Folder");
						}
						ele = isDisplayed(driver, FindElement(driver, "//span[@id='grid11_DealDetailBWINV-cell-3-0']/a[text()='"+M17Contact1EmailId+"']/../preceding-sibling::span[contains(text(),'Remove')]", "remove link in front of contact email", action.BOOLEAN, 10), "Visibility", 10, "Remove link");
						if(ele!=null) {
							appLog.info("Remove Link Should be Disabled as Contact is Invited From Parent Folder");
						}else {
							appLog.error("Remove Link is Disabled as Contact is Invited From Parent Folder");
							sa.assertTrue(false, "Remove Link is Disabled as Contact is Invited From Parent Folder");
						}
						
						if (fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
							appLog.info("Click on Expand Icon of Select Contacts");
							
							ele=fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20);
							if(ele!=null) {
								ThreadSleep(3000);
								if(!clickUsingJavaScript(driver, ele, "check box")) {
//								if(click(driver, ele, "check box against : "+M17Contact1FirstName+" "+M17Contact1LastName, action.BOOLEAN)) {
									appLog.info("clicked on check box ");
									ThreadSleep(2000);
									if (isAlertPresent(driver)) {
										 msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if (msg.trim().equals(alertMsg)) {
											appLog.info(
													"Alert Message is verified in contact access : "+msg);
										} else {
											appLog.error(
													"Alert Message is not verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
											sa.assertTrue(false,
													"Alert Message is not verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
										}
									
									} else {
										appLog.error("Alert is not present so cannot verify alert message "+alertMsg);
										sa.assertTrue(false, "Alert is not present so cannot verify alert message "+alertMsg);
									}
								}else {
									appLog.error("Not able to click on Checkbox");
									sa.assertTrue(false, "Not able to click on Checkbox of on Contact ");
								}
								
							}else{
								appLog.error("Checkbox Element is null for "+M17Contact1FirstName+" "+M17Contact1LastName);
								sa.assertTrue(false, "Checkbox Element is null for "+M17Contact1FirstName+" "+M17Contact1LastName);
								
							}
							
							
						}else{
							appLog.error("Not able to click on Expand Icon of Select Contacts");
							sa.assertTrue(false, "Not able to click on Expand Icon of Select Contacts");
						}
						
						ele = FindElement(driver, "//span[@id='grid11_DealDetailBWINV-cell-3-0']/a[text()='"+M17Contact1EmailId+"']/../preceding-sibling::span[contains(text(),'Remove')]/a", "Info Link", action.BOOLEAN, 10);
						if (mouseOverOperation(driver, ele)) {
							appLog.info("Mouse Hover on info Icon");
							msg = ele.getAttribute("title").trim();
							if (msg.equals(FundsPageErrorMessage.toolTipinheritedPermissiononRemoveLink)) {
								appLog.info("Info Link Message is verified  : "+msg);
							} else {
								appLog.error(
										"Info Link Message is not verified in contact access Actual : "+msg+" \t Expected : "+FundsPageErrorMessage.toolTipinheritedPermissiononRemoveLink);
								sa.assertTrue(false,
										"Info Link Message is not verified in contact access Actual : "+msg+" \t Expected : "+FundsPageErrorMessage.toolTipinheritedPermissiononRemoveLink);
							}
							
						} else {
							appLog.error("Not able to perform Mouse Hover on info Icon");
							sa.assertTrue(false, "Not able to perform Mouse Hover on info Icon");
						}
						
						if (click(driver, fp.getCrossIconOnContactAccess(Workspace.InvestorWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on Cross Icon");	
						} else {
							appLog.error("Not able to click on Cross Icon");
							sa.assertTrue(false, "Not able to click on Cross Icon");	
						}
						
					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
			}else {
				appLog.error("Folder Structure Not Verified  "+M17Institution1+"/"+M17LimitedPartner1+"/"+stdPath);
				sa.assertTrue(false, "Folder Structure Not Verified  "+M17Institution1+"/"+M17LimitedPartner1+"/"+stdPath);
			}
				
				if (fp.verifyFolderPathdummy("", M17Institution1, "", null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+M17Institution1);
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
					WebElement remove = isDisplayed(driver, FindElement(driver, "//a[text()='"+M17Contact1EmailId+"']/../../span/a[@title='Remove']", "remove link in front of contact email", action.BOOLEAN, 60), "Visibility", 60, "Remove link");
					if (remove!=null) {
						if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on remove link");
							if (click(driver, fp.getRemoveAccessClose(Workspace.InvestorWorkspace, 30), "remove access close button", action.BOOLEAN)) {
								appLog.info("Clicked on remove access close button");
								
								if (click(driver, fp.getCrossIconOnContactAccess(Workspace.InvestorWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on Cross Icon on Contact Access");	
								} else {
									appLog.error("clicked on Cross Icon on Contact Access");
									sa.assertTrue(false, "clicked on Cross Icon on Contact Access");	
								}
								
							}
							else {
								appLog.error("remove access close button is not clickable");
								sa.assertTrue(false, "remove access close button is not clickable");
							}
						}else {
							appLog.error("Not able to click on remove link of contact: "+M17Contact1EmailId);
							sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1EmailId);
						}
					}
					else {
						appLog.error("remove button adjacent to "+M17Contact1EmailId+" is not found");
						sa.assertTrue(false, "remove button adjacent to "+M17Contact1EmailId+" is not found");
					}
				}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
				
				}else {
					appLog.error("Folder Structure Not Verified  "+M17Institution1);
					sa.assertTrue(false, "Folder Structure Not Verified  "+M17Institution1);
				}	
				
		}else {
			appLog.error("Not able to click on created fund Name : "+M17FundName1);
			sa.assertTrue(false, "Not able to click on created fund Name : "+M17FundName1);
		
		}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc058_inviteContactAndCreateSubFolderCheckPermission(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath).split(",");
		String errormsg=FundsPageErrorMessage.alreadyInvitedContactAlertMsg;
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				if(fp.inviteContact(environment,mode,M17Institution1+"/"+M17LimitedPartner1, M17Contact1EmailId,stdPath[0], FolderType.Standard, "Upload", "Yes", null,null, Workspace.InvestorWorkspace, null)) {
					appLog.info(M17Contact1EmailId+" Contact is selected successfully ");
					ThreadSleep(2000);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Fundraising workspace view.");
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
						String contactDetails= "Remove<break>"+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+"<break>"+getDateAccToTimeZone("America/New_York", "MM/dd/YYYY")+"<break>"+M17Contact1EmailId+"<break>"+M17Contact1UpdatedFirmName;
						SoftAssert result = fp.verifySelectedContactsGridDataInContactAccess(Workspace.InvestorWorkspace,contactDetails);
						sa.combineAssertions(result);
						ThreadSleep(4000);
						if(click(driver, fp.getContactAccessCancelBtn(Workspace.InvestorWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("click on contact access pop up cancel button");
						}else {
							appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
							sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
						}
					}else {
						appLog.error("Not able to click on contact access icon so cannot verify select contact "+M17Contact1FirstName+" "+M17Contact1LastName);
						sa.assertTrue(false, "Not able to click on contact access icon so cannot verify select contact "+M17Contact1FirstName+" "+M17Contact1LastName);
					}
					if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "Manage folder icon", action.SCROLLANDBOOLEAN)){
						if(fp.createFolderStructure(stdPath[1], FolderType.Standard, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(stdPath[0]+ " folder structure is created.");
							if(click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 20), "close button", action.SCROLLANDBOOLEAN)) {
								if(fp.verifyFolderPathdummy(stdPath[1], M17Institution1, M17LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
									if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
										if(fp.getcontactaccessremoveLink(Workspace.InvestorWorkspace, EnableDisable.Disable,M17Contact1EmailId, 10)!=null) {
											appLog.info("Invited Contact "+M17Contact1FirstName+" "+M17Contact1LastName+" is displaying in disabled mode ");
										}else {
											appLog.error("Invited Contact "+M17Contact1FirstName+" "+M17Contact1LastName+" is not displaying in disabled mode ");
											sa.assertTrue(false, "Invited Contact "+M17Contact1FirstName+" "+M17Contact1LastName+" is not displaying in disabled mode ");
										}
										if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
											ThreadSleep(3000);
											if(!clickUsingJavaScript(driver, fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20), "check box")) {
//											if(click(driver, fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20), "Check Box", action.SCROLLANDBOOLEAN)) {
												ThreadSleep(2000);
												if (isAlertPresent(driver)) {
													String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
													switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
													if (msg.trim().equals(errormsg)) {
														appLog.info(
																"Error Message is verified in contact access : "+errormsg);
													} else {
														appLog.error(
																"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+errormsg);
														sa.assertTrue(false,
																"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+errormsg);
													}
													
												} else {
													appLog.error("Alert is not present so cannot verify alert message "+errormsg);
													sa.assertTrue(false, "Alert is not present so cannot verify alert message "+errormsg);
												}
												if(click(driver, fp.getContactAccessCancelBtn(Workspace.InvestorWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
													appLog.info("click on contact access pop up cancel button");
												}else {
													appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
													sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
												}
											}else {
												appLog.error("Not able to click on Contact Name "+M17Contact1FirstName+" "+M17Contact1LastName+" so cannot check error message "+errormsg);
												sa.assertTrue(false, "Not able to click on Contact Name "+M17Contact1FirstName+" "+M17Contact1LastName+" so cannot check error message "+errormsg);
											}
											
										}else {
											appLog.error("Not able to expand contact access pop up so cannot verify error message "+errormsg);
											sa.assertTrue(false, "Not able to expand contact access pop up so cannot verify error message "+errormsg);
										}
										
										
										
									}else {
										appLog.error("Not able to click on contact access icon so cannot verify inherit contact access permission");
										sa.assertTrue(false, "Not able to click on contact access icon so cannot verify inherit contact access permission");
									}
									
								}else {
									appLog.error("Not able to click on folder structure "+stdPath[1]+" so cannot check inherit contact access permission on sub folder");
									sa.assertTrue(false, "Not able to click on folder structure "+stdPath[1]+" so cannot check inherit contact access permission on sub folder");
								}
								
							}else {
								appLog.error("Not able to click on manage folder close button");
								sa.assertTrue(false, "Not able to click on manage folder close button");
							}
							
						} else {
							appLog.error(stdPath[1]+ " folder structure is not created.");
							sa.assertTrue(false,stdPath[1]+ " folder structure is not created.");
						}
					}else {
						appLog.error("Not able to click on manage folder icon so cannot create sub folder");
						sa.assertTrue(false, "Not able to click on manage folder icon so cannot create sub folder");
					}
					if(fp.verifyFolderPathdummy(stdPath[2], M17Institution1, M17LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
						if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
							if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.InvestorWorkspace, 60), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
								appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
								if(click(driver, fp.getContactAccessCancelBtn(Workspace.InvestorWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
									appLog.info("click on contact access pop up cancel button");
								}else {
									appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
									sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
								}
							}else {
								appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
								sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
							}
						}else {
							appLog.error("Not able to click on contact access icon so cannot check error message ");
							sa.assertTrue(false, "Not able to click on contact access icon so cannot check error message ");
						}
					}else {
						appLog.error("Not able to click on folder structure "+stdPath[2]+" so cannot check error message ");
						sa.assertTrue(false, "Not able to click on folder structure "+stdPath[2]+" so cannot check error message ");
					}
					switchToDefaultContent(driver);
					if(fp.inviteContact(environment,mode,M17Institution1+"/"+M17LimitedPartner1, M17Contact1EmailId,stdPath[2], FolderType.Standard, null, "Yes", null,null, Workspace.InvestorWorkspace, null)) {
						appLog.info("Contact is invited "+M17Contact1FirstName+" "+M17Contact1LastName);
					}else {
						appLog.error("Not able to invite contact from folder "+stdPath[2]);
						sa.assertTrue(false, "Not able to invite contact from folder "+stdPath[2]);
					}
					
				}else {
					appLog.error("Not able to select Contact from contact access grid "+M17Contact1EmailId);
					sa.assertTrue(false, "Not able to select Contact from contact access grid "+M17Contact1EmailId);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc059_1_VerifyAccessFromAddedChildFolderCRMSide(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		WebElement ele=null;
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String stdPaths = cmnPath+","+ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String[] allFolders=stdPaths.split(",");
		
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(allFolders[2], M17Institution1, M17LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+M17Institution1+"/"+M17LimitedPartner1+"/"+allFolders[2]);
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
						
						String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
						
						appLog.info("Going to Verify "+contactFullName+" with Remove Disabled Link");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, EnableDisable.Disable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is Verified");
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace,EnableDisable.Disable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked as Contact is Invited From Parent Folder");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, EnableDisable.Disable, contactFullName, 5, CheckBoxName.Download);
							if(!isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is Unchecked");
							}else {
								appLog.error("Download Check box Should be Unchecked");
								sa.assertTrue(false, "Download Check box Should be Unchecked");
							}
							
							if(!isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Disabled");
							}else {
								appLog.error("Download Check box Should be Disabled");
								sa.assertTrue(false, "Download Check box Should be Disabled");
							}
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace,EnableDisable.Disable, contactFullName, 5, CheckBoxName.Upload);
							if(!isSelected(driver, ele, "Upload CheckBox")) {
								appLog.info("Upload Check box is Unchecked");
							}else {
								appLog.error("Upload Check box Should be Unchecked");
								sa.assertTrue(false, "Upload Check box Should be Unchecked");
							}
							
							if(!isEnabled(driver, ele, "Upload Icon")) {
								appLog.info("Upload Check box is Disabled");
							}else {
								appLog.error("Upload Check box Should be Disabled");
								sa.assertTrue(false, "Upload Check box Should be Disabled");
							}
							
							
						} else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is not Verified");
					
						}
						appLog.info("Going to Verify "+contactFullName+" with Remove Enabled Link");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Enable is Verified");
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked as Contact is Invited From Parent Folder");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
							if(isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is checked");
							}else {
								appLog.error("Download Check box Should be checked");
								sa.assertTrue(false, "Download Check box Should be checked");
							}
							
							if(isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Enabled");
							}else {
								appLog.error("Download Check box Should be Enabled");
								sa.assertTrue(false, "Download Check box Should be Enabled");
							}
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.Upload);
							if(isSelected(driver, ele, "Upload CheckBox")) {
								appLog.info("Upload Check box is checked");
							}else {
								appLog.error("Upload Check box Should be checked");
								sa.assertTrue(false, "Upload Check box Should be checked");
							}
							
							if(isEnabled(driver, ele, "Upload Icon")) {
								appLog.info("Upload Check box is Enabled");
							}else {
								appLog.error("Upload Check box Should be Enabled");
								sa.assertTrue(false, "Upload Check box Should be Enabled");
							}
							
							
						} else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
					
						}
						
						if (click(driver, fp.getCrossIconOnContactAccess(Workspace.InvestorWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on Cross Icon");	
						} else {
							appLog.error("Not able to click on Cross Icon");
							sa.assertTrue(false, "Not able to click on Cross Icon");	
						}
						
					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
			}else {
				appLog.error("Folder Structure Not Verified  "+M17Institution1+"/"+M17LimitedPartner1+"/"+allFolders[2]);
				sa.assertTrue(false, "Folder Structure Not Verified  "+M17Institution1+"/"+M17LimitedPartner1+"/"+allFolders[2]);
			}
				
				
		}else {
			appLog.error("Not able to click on created fund Name : "+M17FundName1);
			sa.assertTrue(false, "Not able to click on created fund Name : "+M17FundName1);
		
		}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		switchToDefaultContent(driver);
		
	
		if (cp.clickOnTab(TabName.ContactTab)) {
		if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
			switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
			
			scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");
			
			for (int i=0;i<allFolders.length;i++) {
				
				if (fp.verifyFolderPathdummy(allFolders[i], null, null, M17FundName1, PageName.ContactsPage,
						Workspace.InvestorWorkspace, 60)) {
					
					if ( cp.getViewAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
						appLog.info("View access icon is Enabled for : "+allFolders[i]);
					}
					else {
						appLog.error("View access icon is Should be Enabled for : "+allFolders[i]);
						sa.assertTrue(false,"View access icon is Should be Enabled for : "+allFolders[i]);
					}
					
					
					if (i==0 || i==2 || i==3) {
						
						if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
							appLog.info("Download access icon is Enabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Download access icon is Should be Enabled for : "+allFolders[i]);
							sa.assertTrue(false,"Download access icon is Should be Enabled for : "+allFolders[i]);
						}
						
					} else {
						
						if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 5)==null) {
							appLog.info("Download access icon is Disabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Download access icon is Should be Disabled for : "+allFolders[i]);
							sa.assertTrue(false,"Download access icon is Should be Disabled for : "+allFolders[i]);
						}
					}
					
					
					if (i==2 || i==3) {
						
						if ( cp.getUploadAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
							appLog.info("Upload access icon is Enabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Upload access icon is Should be Enabled for : "+allFolders[i]);
							sa.assertTrue(false,"Upload access icon is Should be Enabled for : "+allFolders[i]);
						}
						
					} else {
						
						if ( cp.getUploadAccessIcon(Workspace.InvestorWorkspace, 5)==null) {
							appLog.info("Upload access icon is Disabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Upload access icon is Should be Disabled for : "+allFolders[i]);
							sa.assertTrue(false,"Upload access icon is Should be Disabled for : "+allFolders[i]);
						}
						
					}
					
					
				}
				else {
					appLog.error("common path is not found on contacts page folder structure : "+allFolders[i]);
					sa.assertTrue(false, "common path is not found on contacts page folder structure : "+allFolders[i]);
				}
				
			}
			
		}
		else {
			appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
			sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
		}
	}
	else {
		appLog.error("contact tab is not clickable");
		sa.assertTrue(false, "contact tab is not clickable");
	}

	switchToDefaultContent(driver);

	lp.CRMlogout();
	sa.assertAll();
	
}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc059_2_VerifyAccessFromAddedChildFolderInvestoride() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String stdPaths = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String[] stdPath=stdPaths.split(",");
		String parentID;
		String alertMsg=InvestorFirmPageErrorMessage.LimitedAccessErrorMessageBulkDownload;
		/*String[] confirmationMsg={InvestorFirmPageErrorMessage.BulkDownloadConfirmationMessage1,InvestorFirmPageErrorMessage.BulkDownloadConfirmationMessage2,
				InvestorFirmPageErrorMessage.BulkDownloadConfirmationMessage3,InvestorFirmPageErrorMessage.BulkDownloadConfirmationMessage4,
				InvestorFirmPageErrorMessage.BulkDownloadConfirmationMessage5};
	*/	
		String msg ;
		WebElement ele;
		
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			
			if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.CurrentInvestmentPgae,null, 20)) {
				appLog.info(" Folder Structure Verified: " + cmnPath);
			}else {
				appLog.error("could not find folder structure "+cmnPath);
				sa.assertTrue(false, "could not find folder structure "+cmnPath);
			}
			
			for (int i = 0; i < stdPath.length; i++) {
				
				if (fp.verifyFolderPathdummy(stdPath[i], null, null, null, PageName.CurrentInvestmentPgae,
						null, 20)) {
					appLog.info(" Folder Structure Verified: " + stdPath[i]);
					

				}else {
					appLog.error("could not find folder structure "+stdPath[i]);
					sa.assertTrue(false, "could not find folder structure "+stdPath[i]);
				}
				
			}
			
			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");
				
				parentID=switchOnWindow(driver);
				
				if (parentID!=null) {
					if (ifp.verifyBulkDownLoadFolderStructure(cmnPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + cmnPath);
					}else {
						appLog.error("could not find folder structure on Bulk DownLoad: "+cmnPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+cmnPath);
					}

					if (ifp.verifyBulkDownLoadFolderStructure(stdPath[2], 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + stdPath[2]);
					}else {
						appLog.error("could not find folder structure on Bulk DownLoad"+stdPath[2]);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+stdPath[2]);
					}


					if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(stdPath[0], 10)) {
						appLog.info("Clicked on CheckBox for Folder: "+stdPath[0]);	
						ThreadSleep(2000);
						if (isAlertPresent(driver)) {
							 msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
							switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
							if (msg.trim().contains(alertMsg)) {
								appLog.info("Error Message is verified in contact access : "+alertMsg);
							} else {
								appLog.error("Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
								sa.assertTrue(false,"Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
							}
						} else {
							appLog.error("Alert is not present so cannot verify alert message "+alertMsg);
							sa.assertTrue(false, "Alert is not present so cannot verify alert message "+alertMsg);
						}
						} else {
						sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+stdPath[0]);
						appLog.error("Not Able to Click on CheckBox for Folder: "+stdPath[0]);
					}	

					if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(stdPath[1], 10)) {
						appLog.info("Clicked on CheckBox for Folder: "+stdPath[1]);	
						if (click(driver, ifp.getBulkDownloadDownloadButton(10), "DownLoad Button", action.BOOLEAN)) {
							
						/*	WebElement[] elements = {ifp.getbulkDownloadConfirmationMessage1(10),ifp.getbulkDownloadConfirmationMessage2(10),
									ifp.getbulkDownloadConfirmationMessage3(10),ifp.getbulkDownloadConfirmationMessage4(10),ifp.getbulkDownloadConfirmationMessage5(10)};
							*/
							
							if (click(driver, ifp.getBulkDownloadConfirmationPopUpYesBtn(30),
									"Bulk Download Yes Button", action.BOOLEAN)) {
								appLog.error("Clicked on Yes Button.");
								
								
								Robot rob;
								try {
									rob = new Robot();
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
								} catch (AWTException e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
								ThreadSleep(20000);
								driver.close();
								driver.switchTo().window(parentID);
								// ele.sendKeys(Keys.CONTROL+"j");
								parentID = switchOnWindow(driver);
								if (parentID == null) {
									try {
										rob = new Robot();
										rob.keyPress(KeyEvent.VK_TAB);
										rob.keyRelease(KeyEvent.VK_TAB);
										rob.keyPress(KeyEvent.VK_ENTER);
										rob.keyRelease(KeyEvent.VK_ENTER);
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_CONTROL);
										rob.keyPress(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_CONTROL);
										parentID = switchOnWindow(driver);
									} catch (AWTException e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}
								}
							
								ThreadSleep(5000);
								String fileName = ifp.getDownloadedFileName();
								if (fileName.contains(M17FundName1 + " " + getSystemDate("MM-dd-YYYY"))) {
									appLog.info("file name is successfully matched. " + fileName);
								} else {
									appLog.info("File name is not the matched. Expected: " + M17FundName1 + " "
											+ getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
									sa.assertTrue(false, "File name is not the matched. Expected: " + M17FundName1
											+ " " + getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
								}
								driver.close();
								driver.switchTo().window(parentID);
								
							} else {
								appLog.error("Not Able to Click on Yes Button.");
								sa.assertTrue(false, "Not Able to Click on Yes Button.");
							}
							
							
						} else {
							appLog.error("Download Button is not working.");
							sa.assertTrue(false, "Download Button is not working.");
						}
					
					
					} else {
						appLog.error("Not Able to Click on CheckBox for Folder: "+stdPath[1]);
						sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+stdPath[1]);
					
					}	
					
					
				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}
				
				
			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}
			
		
		}else{
			appLog.error("Not Able to Click on "+investorSideWorkSpace.CurrentInvestment);
			sa.assertTrue(false, "Not Able to Click on "+investorSideWorkSpace.CurrentInvestment);	
		}
		
		
		
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc060_1_RemoveTheAccessFromInvitedParentFolderActionCRMSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String stdPaths = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String[] stdFolder=stdPaths.split(",");
		String allFolders = cmnPath+","+stdFolder[3]+","+stdFolder[4];
		String[] allFolder = allFolders.split(",");
		WebElement popUpEle;
		WebElement ele;
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
		if (cp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "workspace section");
				if (fp.verifyFolderPathdummy(stdFolder[0], M17Institution1, M17LimitedPartner1, null, PageName.FundsPage,Workspace.InvestorWorkspace,60)) {
					appLog.error("folder path verified : "+M17Institution1+"/"+M17LimitedPartner1+"/"+stdFolder[0]);
					if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
							WebElement remove = isDisplayed(driver, FindElement(driver, "//a[text()='"+M17Contact1EmailId+"']/../../span/a[@title='Remove']", "remove link in front "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName, action.BOOLEAN, 60), "Visibility", 60, "Remove link");
							if (remove!=null) {
								if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on remove link for : "+contactFullName);
									ThreadSleep(5000);
									if (click(driver, fp.getRemoveAccessClose(Workspace.InvestorWorkspace, 30), "remove access close button", action.BOOLEAN)) {
										appLog.error("Clicked on remove access close button");	
										
										popUpEle = fp.getContactAccessRemovedPopUpMsg(Workspace.InvestorWorkspace, 5);
										if (popUpEle==null) {
											appLog.info("Access Remove PopUp is closed after click on Closed Button");
										}else{
											appLog.error("Access Remove PopUp is not closed after click on Closed Button");
											sa.assertTrue(false, "Access Remove PopUp is not closed after click on Closed Button");	
										}
										
										if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.InvestorWorkspace, 60), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
											appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
										}else {
											appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
											sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
										}
										
										if (click(driver, fp.getContactAccessCrossIcon(Workspace.InvestorWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on Contact access Cross Icon");
										} else {
											appLog.error("Contact access Cross Icon is not clickable");
											sa.assertTrue(false, "Contact access close button is not clickable");
										}
										
										popUpEle = fp.getContactAccessCrossIcon(Workspace.InvestorWorkspace, 5);
										if (popUpEle==null) {
											appLog.info("Select Contact Access PopUp is closed after click on Cross Icon");
										}else{
											appLog.error("Select Contact Access PopUp is not closed after click on Cross Icon");
											sa.assertTrue(false, "Select Contact Access PopUp is not closed after click on Cross Icon");	
										}
									}
									else {
										appLog.error("remove access close button is not clickable");
										sa.assertTrue(false, "remove access close button is not clickable");
									}
								
								}else {
									appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
									sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
								}
							}
							else {
								appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
								sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
							}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}
				}
				else {
					appLog.error("folder path is not found : "+M17Institution1+"/"+M17LimitedPartner1+"/"+stdFolder[0]);
					sa.assertTrue(false, "folder path is not found : "+M17Institution1+"/"+M17LimitedPartner1+"/"+stdFolder[0]);
				}
				
				
				// Verify SubFolder after Removing Access From Parent Folder
				
				if (fp.verifyFolderPathdummy(stdFolder[1], M17Institution1, M17LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+M17Institution1+"/"+M17LimitedPartner1+"/"+stdFolder[1]);
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
						
						
						appLog.info("Going to Verify "+contactFullName+" with Remove Disabled Link");
						if (!fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, EnableDisable.Disable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.info("Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is not displaying and Hence Verified");
						
						} else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is displaying and Hence Not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is displaying and Hence Not Verified");
					
						}
						appLog.info("Going to Verify "+contactFullName+" with Remove Enabled Link");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Enable is Verified");
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked as Contact is Invited From Parent Folder");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
							if(isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is checked");
							}else {
								appLog.error("Download Check box Should be checked");
								sa.assertTrue(false, "Download Check box Should be checked");
							}
							
							if(isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Enabled");
							}else {
								appLog.error("Download Check box Should be Enabled");
								sa.assertTrue(false, "Download Check box Should be Enabled");
							}
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.Upload);
							if(isSelected(driver, ele, "Upload CheckBox")) {
								appLog.info("Upload Check box is checked");
							}else {
								appLog.error("Upload Check box Should be checked");
								sa.assertTrue(false, "Upload Check box Should be checked");
							}
							
							if(isEnabled(driver, ele, "Upload Icon")) {
								appLog.info("Upload Check box is Enabled");
							}else {
								appLog.error("Upload Check box Should be Enabled");
								sa.assertTrue(false, "Upload Check box Should be Enabled");
							}
							
							
						} else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
					
						}
						
						if (click(driver, fp.getCrossIconOnContactAccess(Workspace.InvestorWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on Cross Icon");	
						} else {
							appLog.error("Not able to click on Cross Icon");
							sa.assertTrue(false, "Not able to click on Cross Icon");	
						}
						
					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
			}else {
				appLog.error("Folder Structure Not Verified  "+M17Institution1+"/"+M17LimitedPartner1+"/"+stdFolder[1]);
				sa.assertTrue(false, "Folder Structure Not Verified  "+M17Institution1+"/"+M17LimitedPartner1+"/"+stdFolder[1]);
			}
			}
			else {
				appLog.error(M17FundName1+" is not found on funds page");
				sa.assertTrue(false, M17FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");
				
				for (int i=0;i<allFolder.length;i++) {
					
					if (fp.verifyFolderPathdummy(allFolder[i], null, null, M17FundName1, PageName.ContactsPage,
							Workspace.InvestorWorkspace, 60)) {
						
						if ( cp.getViewAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
							appLog.info("View access icon is Enabled for : "+allFolder[i]);
						}
						else {
							appLog.error("View access icon is Should be Enabled for : "+allFolder[i]);
							sa.assertTrue(false,"View access icon is Should be Enabled for : "+allFolder[i]);
						}
						
						if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
							appLog.info("Download access icon is Enabled for : "+allFolder[i]);
						}
						else {
							appLog.error("Download access icon is Should be Enabled for : "+allFolder[i]);
							sa.assertTrue(false,"Download access icon is Should be Enabled for : "+allFolder[i]);
						}

						if (i==1 || i==2) {
							
							if ( cp.getUploadAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
								appLog.info("Upload access icon is Enabled for : "+allFolder[i]);
							}
							else {
								appLog.error("Upload access icon is Should be Enabled for : "+allFolder[i]);
								sa.assertTrue(false,"Upload access icon is Should be Enabled for : "+allFolder[i]);
							}
							
						} else {
							
							if ( cp.getUploadAccessIcon(Workspace.InvestorWorkspace, 5)==null) {
								appLog.info("Upload access icon is Disabled for : "+allFolder[i]);
							}
							else {
								appLog.error("Upload access icon is Should be Disabled for : "+allFolder[i]);
								sa.assertTrue(false,"Upload access icon is Should be Disabled for : "+allFolder[i]);
							}
							
						}
						
						
					}
					else {
						appLog.error("common path is not found on contacts page folder structure : "+allFolder[i]);
						sa.assertTrue(false, "common path is not found on contacts page folder structure : "+allFolder[i]);
					}
					
				}
				
			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc060_2_RemoveTheAccessFromInvitedParentFolderImpcatInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String dependentTC="M17tc060_1_RemoveTheAccessFromInvitedParentFolderActionCRMSide";
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependentTC,excelLabel.CommonPath);
		String stdPaths = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependentTC, excelLabel.StandardPath);
		String[] stdPath=stdPaths.split(",");
		String AllFolders=cmnPath+","+stdPath[3]+","+stdPath[4];
		String[] allFolder=AllFolders.split(",");
		String parentID;
		
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			
			for (int i = 0; i < allFolder.length; i++) {
				
				if (fp.verifyFolderPathdummy(allFolder[i], null, null, null, PageName.CurrentInvestmentPgae,
						null, 20)) {
					appLog.info(" Folder Structure Verified: " + allFolder[i]);
					}else {
					appLog.error("could not find folder structure "+allFolder[i]);
					sa.assertTrue(false, "could not find folder structure "+allFolder[i]);
				}
				
			}
			
			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");
				
				parentID=switchOnWindow(driver);
				
				if (parentID!=null) {
					if (ifp.verifyBulkDownLoadFolderStructure(cmnPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + cmnPath);
					}else {
						appLog.error("could not find folder structure on Bulk DownLoad: "+cmnPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+cmnPath);
					}

					if (ifp.verifyBulkDownLoadFolderStructure(stdPath[4], 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + stdPath[2]);
					}else {
						appLog.error("could not find folder structure on Bulk DownLoad"+stdPath[2]);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+stdPath[2]);
					}
	

					if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(stdPath[3], 10)) {
						appLog.info("Clicked on CheckBox for Folder: "+stdPath[3]);	
						if (click(driver, ifp.getBulkDownloadDownloadButton(10), "DownLoad Button", action.BOOLEAN)) {
						
							
							if (click(driver, ifp.getBulkDownloadConfirmationPopUpYesBtn(30),
									"Bulk Download Yes Button", action.BOOLEAN)) {
								appLog.error("Clicked on Yes Button.");
								
								
								Robot rob;
								try {
									rob = new Robot();
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
								} catch (AWTException e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
								ThreadSleep(20000);
								driver.close();
								driver.switchTo().window(parentID);
								// ele.sendKeys(Keys.CONTROL+"j");
								parentID = switchOnWindow(driver);
								if (parentID == null) {
									try {
										rob = new Robot();
										rob.keyPress(KeyEvent.VK_TAB);
										rob.keyRelease(KeyEvent.VK_TAB);
										rob.keyPress(KeyEvent.VK_ENTER);
										rob.keyRelease(KeyEvent.VK_ENTER);
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_CONTROL);
										rob.keyPress(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_CONTROL);
										parentID = switchOnWindow(driver);
									} catch (AWTException e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}
								}
							
								ThreadSleep(5000);
								String fileName = ifp.getDownloadedFileName();
								if (fileName.contains(M17FundName1 + " " + getSystemDate("MM-dd-YYYY"))) {
									appLog.info("file name is successfully matched. " + fileName);
								} else {
									appLog.info("File name is not the matched. Expected: " + M17FundName1 + " "
											+ getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
									sa.assertTrue(false, "File name is not the matched. Expected: " + M17FundName1
											+ " " + getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
								}
								driver.close();
								driver.switchTo().window(parentID);
								
							} else {
								appLog.error("Not Able to Click on Yes Button.");
								sa.assertTrue(false, "Not Able to Click on Yes Button.");
							}
							
							
						} else {
							appLog.error("Download Button is not working.");
							sa.assertTrue(false, "Download Button is not working.");
						}
					
					
					} else {
						appLog.error("Not Able to Click on CheckBox for Folder: "+stdPath[3]);
						sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+stdPath[3]);
					
					}	
					
					
				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}
				
				
			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}
			
		
		}else{
			appLog.error("Not Able to Click on "+investorSideWorkSpace.CurrentInvestment);
			sa.assertTrue(false, "Not Able to Click on "+investorSideWorkSpace.CurrentInvestment);	
		}
		
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc061_1_InviteContactFromParentAndRemoveAccessFromChild(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String[] stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath).split(",");
		String commonPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String folder=commonPath+","+ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		String[] allFolders=folder.split(",");
		String errormsg=FundsPageErrorMessage.alreadyInvitedContactAlertMsg;
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				if(fp.inviteContact(environment,mode,M17Institution1+"/"+M17LimitedPartner1, M17Contact1EmailId,stdPath[0], FolderType.Standard, null, "Yes", null,null, Workspace.InvestorWorkspace, null)) {
					appLog.info(M17Contact1EmailId+" Contact is selected successfully ");
					ThreadSleep(2000);
					
				}else {
					appLog.error("Not able to invite contact from parent folder "+stdPath[0]);
					sa.assertTrue(false, "Not able to invite contact from parent folder "+stdPath[0]);
				}
				
			}else {
				appLog.error("Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
				sa.assertTrue(false, "Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
		}
		switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
		scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "workspace section");
		if (fp.verifyFolderPathdummy(stdPath[1], M17Institution1, M17LimitedPartner1, null, PageName.FundsPage,Workspace.InvestorWorkspace,60)) {
			if(fp.revokeContactAccess(M17Contact1EmailId, Workspace.InvestorWorkspace)) {
				
			}else {
				appLog.error("Not able to remove contact access "+M17Contact1EmailId+" form "+stdPath[1]);
				sa.assertTrue(false, "Not able to remove contact access "+M17Contact1EmailId+" form "+stdPath[1]);
			}
			if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
				appLog.info(M17Contact1EmailId+" Contact is selected successfully ");
				if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
					WebElement ele=fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20);
					if(ele!=null) {
						ThreadSleep(3000);
						if(!clickUsingJavaScript(driver, ele, "check box")) {
//						if(click(driver, ele, "check box", action.BOOLEAN)) {
							appLog.info("clicked on check box ");
							ThreadSleep(2000);
							if (isAlertPresent(driver)) {
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								if (msg.trim().equals(errormsg)) {
									appLog.info(
											"Error Message is verified in contact access : "+msg);
								} else {
									appLog.error(
											"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+errormsg);
									sa.assertTrue(false,
											"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+errormsg);
								}
							} else {
								appLog.error("Alert is not present so cannot verify alert message "+errormsg);
								sa.assertTrue(false, "Alert is not present so cannot verify alert message "+errormsg);
							}
						}else {
							appLog.error("Not able to click on Checkbox");
							sa.assertTrue(false, "Not able to click on Checkbox of on Contact ");
						}
						
					}else{
						appLog.error("Checkbox Element is null");
						sa.assertTrue(false, "Checkbox Element is null");
						
					}
					if(click(driver, fp.getCancelBtn(Workspace.InvestorWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
						appLog.error("Clicked on close button");
					}else {
						appLog.error("Not able to click on cancel button so cannot close contact access popup");
						sa.assertTrue(false, "Not able to click on cancel button so cannot close contact access popup");
					}
					
				}else {
					appLog.error("Not able to expand contact acces pop up so cannot check error message "+errormsg);
					sa.assertTrue(false, "Not able to expand contact acces pop up so cannot check error message "+errormsg);
				}
				
			}else {
				appLog.error("Not able to click on contact access icon so cannot check error message");
				sa.assertTrue(false, "Not able to click on contact access icon so cannot check error message");
			}
			
		}else {
			appLog.error("Not able to click on folder path "+stdPath[1]);
			sa.assertTrue(false, "Not able to click on folder path "+stdPath[1]);
		}
		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
		if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
			switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
			scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");
			for (int i=0;i<allFolders.length;i++) {
				
				if (fp.verifyFolderPathdummy(allFolders[i], null, null, M17FundName1, PageName.ContactsPage,
						Workspace.InvestorWorkspace, 60)) {
					
					if ( cp.getViewAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
						appLog.info("View access icon is Enabled for : "+allFolders[i]);
					}
					else {
						appLog.error("View access icon is Should be Enabled for : "+allFolders[i]);
						sa.assertTrue(false,"View access icon is Should be Enabled for : "+allFolders[i]);
					}
					if(i==0) {
						if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
							appLog.info("Download access icon is Enabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Download access icon is Should be Enabled for : "+allFolders[i]);
							sa.assertTrue(false,"Download access icon is Should be Enabled for : "+allFolders[i]);
						}
					}else {
						if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 5)==null) {
							appLog.info("Download access icon is Disabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Download access icon is Should be Disabled for : "+allFolders[i]);
							sa.assertTrue(false,"Download access icon is Should be Disabled for : "+allFolders[i]);
						}
					}
					if ( cp.getUploadAccessIcon(Workspace.InvestorWorkspace, 5)==null) {
						appLog.info("Upload access icon is Disabled for : "+allFolders[i]);
					}
					else {
						appLog.error("Upload access icon is Should be Disabled for : "+allFolders[i]);
						sa.assertTrue(false,"Upload access icon is Should be Disabled for : "+allFolders[i]);
					}
				}
				else {
					appLog.error("common path is not found on contacts page folder structure : "+allFolders[i]);
					sa.assertTrue(false, "common path is not found on contacts page folder structure : "+allFolders[i]);
				}
			}
		}
		else {
			appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
			sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
		}
	}
	else {
		appLog.error("contact tab is not clickable");
		sa.assertTrue(false, "contact tab is not clickable");
	}
	switchToDefaultContent(driver);
	lp.CRMlogout();
	sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc061_2_verifyUploadpermissionAtTargetSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOnTC="M17tc061_1_InviteContactFromParentAndRemoveAccessFromChild";
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependOnTC,excelLabel.CommonPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependOnTC,excelLabel.StandardPath);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.CurrentInvestmentPgae,
					null, 60)) {
				appLog.info(" Folder Structure Verified: " + cmnPath);
				if(ifp.getUploadIcon(2)==null) {
					appLog.info("Upload button is not visible ");
				}else {
					appLog.error("Upload button is visible");
					sa.assertTrue(false, "Upload button is visible");
				}
			}else {
				appLog.error("could not find folder structure "+cmnPath);
				sa.assertTrue(false, "could not find folder structure "+cmnPath);
			}
			
			for(int i=0; i<stdPath.split(",").length; i++) {
				if (fp.verifyFolderPathdummy(stdPath.split(",")[i], null, null, null, PageName.CurrentInvestmentPgae,null, 60)) {
					appLog.info(" Folder Structure Verified: " + stdPath);
					if(ifp.getUploadIcon(2)==null) {
						appLog.info("Upload button is not visible ");
					}else {
						appLog.error("Upload button is visible");
						sa.assertTrue(false, "Upload button is visible");
					}
				}else {
					appLog.error("could not find folder structure "+stdPath);
					sa.assertTrue(false, "could not find folder structure "+stdPath);
				}
			}
			
			if (click(driver, ifp.getBulkDownloadIcon(39), "bulk download icon", action.SCROLLANDBOOLEAN)) {
				String parentID = switchOnWindow(driver);

				if (parentID!=null) {
					if (ifp.verifyBulkDownLoadFolderStructure(cmnPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + cmnPath);


						if (ifp.verifyBulkDownLoadFolderStructure(stdPath.split(",")[0], 20)) {
							appLog.info(" Folder Structure Verified on Bulk DownLoad: " + stdPath.split(",")[0]);


							if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(stdPath.split(",")[0], 10)) {
								appLog.info("Clicked on CheckBox for Folder: "+stdPath.split(",")[0]);		

								ThreadSleep(2000);
								String alertMsg=InvestorFirmPageErrorMessage.LimitedAccessErrorMessageBulkDownload;
								String msg ;
								if (isAlertPresent(driver)) {
									msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if (msg.trim().contains(alertMsg)) {
										appLog.info("Error Message is verified in contact access : "+alertMsg);
									} else {
										appLog.error("Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
										sa.assertTrue(false,"Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
									}
								} else {
									appLog.error("Alert is not present so cannot verify alert message "+alertMsg);
									sa.assertTrue(false, "Alert is not present so cannot verify alert message "+alertMsg);
								}

							}else{
								appLog.error("Not Able to Click on CheckBox for Folder: "+stdPath.split(",")[0]);
								sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+stdPath.split(",")[0]);	
							}	

						}else {
							appLog.error("could not find folder structure on Bulk DownLoad"+stdPath.split(",")[0]);
							sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+stdPath.split(",")[0]);
						}


						if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(cmnPath, 10)) {
							appLog.info("Clicked on CheckBox for Folder: "+cmnPath);		
						}else{
							appLog.error("Not Able to Click on CheckBox for Folder: "+cmnPath);
							sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+cmnPath);	
						}

					}else {
						appLog.error("could not find folder structure on Bulk DownLoad: "+cmnPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+cmnPath);
					}



					if (click(driver, ifp.getBulkDownloadDownloadButton(30), "download window", action.BOOLEAN)) {


						if (click(driver, ifp.getBulkDownloadConfirmationPopUpYesBtn(30),
								"Bulk Download Yes Button", action.BOOLEAN)) {
							appLog.error("Clicked on Yes Button.");


							Robot rob;
							try {
								rob = new Robot();
								ThreadSleep(10000);
								rob.keyPress(KeyEvent.VK_ENTER);
								rob.keyRelease(KeyEvent.VK_ENTER);
								ThreadSleep(10000);
								rob.keyPress(KeyEvent.VK_CONTROL);
								rob.keyPress(KeyEvent.VK_J);
								rob.keyRelease(KeyEvent.VK_J);
								rob.keyRelease(KeyEvent.VK_CONTROL);
							} catch (AWTException e) {
								// TODO Auto-generated catch
								// block
								e.printStackTrace();
							}
							ThreadSleep(20000);
							driver.close();
							driver.switchTo().window(parentID);
							// ele.sendKeys(Keys.CONTROL+"j");
							parentID = switchOnWindow(driver);
							if (parentID == null) {
								try {
									rob = new Robot();
									rob.keyPress(KeyEvent.VK_TAB);
									rob.keyRelease(KeyEvent.VK_TAB);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
									parentID = switchOnWindow(driver);
								} catch (AWTException e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
							}

							ThreadSleep(5000);
							String fileName = ifp.getDownloadedFileName();
							if (fileName.contains(M17FundName1 + " " + getSystemDate("MM-dd-YYYY"))) {
								appLog.info("file name is successfully matched. " + fileName);
							} else {
								appLog.info("File name is not the matched. Expected: " + M17FundName1 + " "
										+ getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
								sa.assertTrue(false, "File name is not the matched. Expected: " + M17FundName1
										+ " " + getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
							}
							driver.close();
							driver.switchTo().window(parentID);
						}
					}else{
						appLog.error("Not able to click on DownLoad Button");
						sa.assertTrue(false, "Not able to click on DownLoad Button");
					}


				}else{
					appLog.error("Not New Windw is open");
					sa.assertTrue(false, "Not New Windw is open");		
				}
			}else{
				appLog.error("Not able to click on Bulk DownLoad Icon");
				sa.assertTrue(false, "Not able to click on Bulk DownLoad Icon");	
			}
		}else {
			appLog.error("Not able to click on potential investment tab so cannot check upload permission");
			sa.assertTrue(false, "Not able to click on potential investment tab so cannot check upload permission");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc062_1_RemoveTheAccessFromInvitedParentFolderActionCRMSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		String stdPaths = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String[] stdFolder=stdPaths.split(",");
		WebElement ele;
		String msg;
		String errorMsg=ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace;
		String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
		if (cp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "workspace section");
				if (fp.verifyFolderPathdummy(stdFolder[0], M17Institution1, M17LimitedPartner1, null, PageName.FundsPage,Workspace.InvestorWorkspace,60)) {
					appLog.error("folder path verified : "+M17Institution1+"/"+M17LimitedPartner1+"/"+stdFolder[0]);
					if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
							WebElement remove = isDisplayed(driver, FindElement(driver, "//a[text()='"+M17Contact1EmailId+"']/../../span/a[@title='Remove']", "remove link in front "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName, action.BOOLEAN, 60), "Visibility", 60, "Remove link");
							if (remove!=null) {
								if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on remove link for : "+contactFullName);
									ThreadSleep(5000);
									if (click(driver, fp.getRemoveAccessClose(Workspace.InvestorWorkspace, 30), "remove access close button", action.BOOLEAN)) {
										appLog.error("Clicked on remove access close button");	
										
										if (click(driver, fp.getContactAccessCrossIcon(Workspace.InvestorWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on Contact access Cross Icon");
										} else {
											appLog.error("Contact access Cross Icon is not clickable");
											sa.assertTrue(false, "Contact access close button is not clickable");
										}
									
									}
									else {
										appLog.error("remove access close button is not clickable");
										sa.assertTrue(false, "remove access close button is not clickable");
									}
								
								}else {
									appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
									sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
								}
							}
							else {
								appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
								sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
							}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}
				}
				else {
					appLog.error("folder path is not found : "+M17Institution1+"/"+M17LimitedPartner1+"/"+stdFolder[0]);
					sa.assertTrue(false, "folder path is not found : "+M17Institution1+"/"+M17LimitedPartner1+"/"+stdFolder[0]);
				}
				
				
				// Verify Folder For No Data Access after Removing Access From Parent Folder
				
				if (fp.verifyFolderPathdummy(stdFolder[0], M17Institution1, M17LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+M17Institution1+"/"+M17LimitedPartner1+"/"+stdFolder[0]);
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
						ele=fp.noDataToDisplayContactAccess(Workspace.InvestorWorkspace, 60);
						
						if (ele!=null) {
							msg =ele.getText().trim();
							if(msg.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
								appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message Verified");
							}else {
								appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
								sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
							}
							
							
						} else {
							appLog.error("No Data msg element is null");
							sa.assertTrue(false, "No Data msg element is null");	
						}
						
						
						if (click(driver, fp.getCrossIconOnContactAccess(Workspace.InvestorWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on Cross Icon");	
						} else {
							appLog.error("Not able to click on Cross Icon");
							sa.assertTrue(false, "Not able to click on Cross Icon");	
						}
						
					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
			}else {
				appLog.error("Folder Structure Not Verified  "+M17Institution1+"/"+M17LimitedPartner1+"/"+stdFolder[0]);
				sa.assertTrue(false, "Folder Structure Not Verified  "+M17Institution1+"/"+M17LimitedPartner1+"/"+stdFolder[0]);
			}
			}
			else {
				appLog.error(M17FundName1+" is not found on funds page");
				sa.assertTrue(false, M17FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");
				
				ele=cp.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60);
				msg=ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace;
				
				if (ele!=null) {
					msg = ele.getText().trim();
					
					if (errorMsg.equals(msg)) {
					appLog.info(msg+" on contatct Page Verified ");	
					} else {
						appLog.error("Error Msg on contatct Page Not Verified Actual : "+msg+"\t Expected : "+errorMsg);	
						sa.assertTrue(false, "Error Msg on contatct Page Not Verified Actual : "+msg+"\t Expected : "+errorMsg);
					}
					
				} else {
					appLog.error("Contact Page Error Msg Ele is null");
					sa.assertTrue(false, "Contact Page Error Msg Ele is null");
				}
				
					
			
			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc062_2_RemoveTheAccessFromInvitedParentFolderImpactInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		
		//Investor Side
		
		List<WebElement> dropdownList=allOptionsInDropDrop(driver, allfp.getFirmNameDropdown(60), "Drop Down List");
		List<String> result=compareMultipleList(driver, Org1FirmName, dropdownList);
		
		if (!result.isEmpty()) {
			appLog.info("Firm Name is not available in DropDown after removing Acces");	
		} else {
			appLog.error("Firm Name is available in DropDown after removing Acces");
			sa.assertTrue(false, "Firm Name is available in DropDown after removing Acces");
		}
		lp.investorLogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc063_1_inviteContactFromMultiFolders(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String[] stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath).split(",");
		String[] allFolders=ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath).split(",");
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				if(fp.inviteContact(environment,mode,M17Institution1+"/"+M17LimitedPartner1, M17Contact1EmailId,stdPath[0], FolderType.Standard, "Upload", "Yes", null,null, Workspace.InvestorWorkspace, null)) {
					appLog.info(M17Contact1EmailId+" Contact is invited successfully from folder "+stdPath[0]);
				}else {
					appLog.error("Not able to invite contact from parent folder "+stdPath[0]);
					sa.assertTrue(false, "Not able to invite contact from parent folder "+stdPath[0]);
				}
				
				if(fp.inviteContact(environment,mode,M17Institution1+"/"+M17LimitedPartner1, M17Contact1EmailId,stdPath[1], FolderType.Standard, null, "Yes", null,null, Workspace.InvestorWorkspace, null)) {
					appLog.info(M17Contact1EmailId+" Contact is invited successfully from folder "+stdPath[1]);
				}else {
					appLog.error("Not able to invite contact from parent folder "+stdPath[1]);
					sa.assertTrue(false, "Not able to invite contact from parent folder "+stdPath[1]);
				}
				
				if(fp.inviteContact(environment,mode,M17Institution1+"/"+M17LimitedPartner1, M17Contact1EmailId,stdPath[2], FolderType.Standard, "download", "Yes", null,null, Workspace.InvestorWorkspace, null)) {
					appLog.info(M17Contact1EmailId+" Contact is invited successfully from folder "+stdPath[2]);
				}else {
					appLog.error("Not able to invite contact from parent folder "+stdPath[2]);
					sa.assertTrue(false, "Not able to invite contact from parent folder "+stdPath[2]);
				}
				
				switchToFrame(driver, 30, cp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");
				for (int i = 0; i < allFolders.length; i++) {
					if (fp.verifyFolderPathdummy(allFolders[i], M17Institution1, M17LimitedPartner1, null, PageName.FundsPage,Workspace.InvestorWorkspace,60)) {
						if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
							if(i==0) {
								if(fp.getSelectedContactNameListInSelectContactGrid(Workspace.InvestorWorkspace).size()==3) {
									appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact name is displaying three time in contact access grid on folder "+allFolders[i]);
								}else {
									appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact name is not displaying three time in contact access grid on folder "+allFolders[i]);
									sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact name is not displaying three time in contact access grid on folder "+allFolders[i]);
								}
							}
							if(i==1) {
								if(fp.getSelectedContactNameListInSelectContactGrid(Workspace.InvestorWorkspace).size()==2) {
									appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact name is displaying two time in contact access grid on folder "+allFolders[i]);
								}else {
									appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact name is not displaying two time in contact access grid on folder "+allFolders[i]);
									sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact name is not displaying two time in contact access grid on folder "+allFolders[i]);
								}
							}
						}else {
							appLog.error("Not able to click on contact access icon on folder "+stdPath[i]);
							sa.assertTrue(false, "Not able to click on contact access icon on folder "+stdPath[i]);
						}
						if(click(driver, fp.getCancelBtn(Workspace.InvestorWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.error("Clicked on close button");
						}else {
							appLog.error("Not able to click on cancel button so cannot close contact access popup");
							sa.assertTrue(false, "Not able to click on cancel button so cannot close contact access popup");
						}
					}else {
						appLog.error("Not able to click on folder structure "+allFolders[i]);
						sa.assertTrue(false, "Not able to click on folder structure "+allFolders[i]);
					}
				}
			}else {
				appLog.error("Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
				sa.assertTrue(false, "Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
		}
		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
		if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
			switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
			scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");
			for (int i=0;i<allFolders.length;i++) {
				
				if (fp.verifyFolderPathdummy(allFolders[i], null, null, M17FundName1, PageName.ContactsPage,
						Workspace.InvestorWorkspace, 60)) {
					
					if ( cp.getViewAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
						appLog.info("View access icon is Enabled for : "+allFolders[i]);
					}
					else {
						appLog.error("View access icon is Should be Enabled for : "+allFolders[i]);
						sa.assertTrue(false,"View access icon is Should be Enabled for : "+allFolders[i]);
					}
					
					if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
						appLog.info("Download access icon is Enabled for : "+allFolders[i]);
					}
					else {
						appLog.error("Download access icon is Should be Enabled for : "+allFolders[i]);
						sa.assertTrue(false,"Download access icon is Should be Enabled for : "+allFolders[i]);
					}
					if(i==0) {
						if ( cp.getUploadAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
							appLog.info("Upload access icon is Disabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Upload access icon is Should be Disabled for : "+allFolders[i]);
							sa.assertTrue(false,"Upload access icon is Should be Disabled for : "+allFolders[i]);
						}
					}else {
						if ( cp.getUploadAccessIcon(Workspace.InvestorWorkspace, 5)==null) {
							appLog.info("Upload access icon is Enabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Upload access icon is Should be Enabled for : "+allFolders[i]);
							sa.assertTrue(false,"Upload access icon is Should be Enabled for : "+allFolders[i]);
						}
					}
					
				}
				else {
					appLog.error("folder path is not found on contacts page folder structure : "+allFolders[i]);
					sa.assertTrue(false, "folder path is not found on contacts page folder structure : "+allFolders[i]);
				}
			}
		}
		else {
			appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
			sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
		}
	}
	else {
		appLog.error("contact tab is not clickable");
		sa.assertTrue(false, "contact tab is not clickable");
	}
	switchToDefaultContent(driver);
	lp.CRMlogout();
	sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc063_2_inviteContactFromMultiFoldersImpactInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String[] stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath).split(",");
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.CurrentInvestmentPgae,
					null, 60)) {
				appLog.info(" Folder Structure Verified: " + cmnPath);
			
			}else {
				appLog.error("could not find folder structure "+cmnPath);
				sa.assertTrue(false, "could not find folder structure "+cmnPath);
			}
			
			for(int i=0; i<stdPath.length; i++) {
				if (fp.verifyFolderPathdummy(stdPath[i], null, null, null, PageName.CurrentInvestmentPgae,null, 60)) {
					appLog.info(" Folder Structure Verified: " + stdPath[i]);
					
				}else {
					appLog.error("could not find folder structure "+stdPath[i]);
					sa.assertTrue(false, "could not find folder structure "+stdPath[i]);
				}
			}
			
			
			//
			
			
			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");
				
				String parentID = switchOnWindow(driver);
				
				if (parentID!=null) {
					if (ifp.verifyBulkDownLoadFolderStructure(cmnPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + cmnPath);
						
						if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(cmnPath, 10)) {
							appLog.info("Clicked on CheckBox for Folder: "+cmnPath);		
						}else{
							appLog.error("Not Able to Click on CheckBox for Folder: "+cmnPath);
							sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+cmnPath);	
						}
						
					}else {
						appLog.error("could not find folder structure on Bulk DownLoad: "+cmnPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+cmnPath);
					}

					if (ifp.verifyBulkDownLoadFolderStructure(stdPath[0], 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + stdPath[0]);
						
						String[] folders = stdPath[0].split("/");
						
						for (int i=folders.length-1;i>=0;i--) {
							if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(folders[i], 10)) {
								appLog.info("Clicked on CheckBox for Folder: "+folders[i]);		
							}else{
								appLog.error("Not Able to Click on CheckBox for Folder: "+folders[i]);
								sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+folders[i]);	
							}	
						}
						
						
					}else {
						appLog.error("could not find folder structure on Bulk DownLoad"+stdPath[0]);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+stdPath[0]);
					}
	
						if (click(driver, ifp.getBulkDownloadDownloadButton(10), "DownLoad Button", action.BOOLEAN)) {
						
							
							if (click(driver, ifp.getBulkDownloadConfirmationPopUpYesBtn(30),
									"Bulk Download Yes Button", action.BOOLEAN)) {
								appLog.error("Clicked on Yes Button.");
								
								
								Robot rob;
								try {
									rob = new Robot();
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
								} catch (AWTException e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
								ThreadSleep(20000);
								driver.close();
								driver.switchTo().window(parentID);
								// ele.sendKeys(Keys.CONTROL+"j");
								parentID = switchOnWindow(driver);
								if (parentID == null) {
									try {
										rob = new Robot();
										rob.keyPress(KeyEvent.VK_TAB);
										rob.keyRelease(KeyEvent.VK_TAB);
										rob.keyPress(KeyEvent.VK_ENTER);
										rob.keyRelease(KeyEvent.VK_ENTER);
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_CONTROL);
										rob.keyPress(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_CONTROL);
										parentID = switchOnWindow(driver);
									} catch (AWTException e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}
								}
							
								ThreadSleep(5000);
								String fileName = ifp.getDownloadedFileName();
								if (fileName.contains(M17FundName1 + " " + getSystemDate("MM-dd-YYYY"))) {
									appLog.info("file name is successfully matched. " + fileName);
								} else {
									appLog.info("File name is not the matched. Expected: " + M17FundName1 + " "
											+ getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
									sa.assertTrue(false, "File name is not the matched. Expected: " + M17FundName1
											+ " " + getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
								}
								driver.close();
								driver.switchTo().window(parentID);
								
							} else {
								appLog.error("Not Able to Click on Yes Button.");
								sa.assertTrue(false, "Not Able to Click on Yes Button.");
							}
							
							
						} else {
							appLog.error("Download Button is not working.");
							sa.assertTrue(false, "Download Button is not working.");
						}
				
						
				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}
				
				
			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}
			
			
		}else {
			appLog.error("Not able to click on potential investment tab so cannot check upload permission");
			sa.assertTrue(false, "Not able to click on potential investment tab so cannot check upload permission");
		}
		switchToDefaultContent(driver);
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc064_1_AddSharedFolderFromManageFolderAndInviteContact(){
	
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath).split(",");
		System.out.println(shdPath[0]);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),"Investor Workspace.");
				if(fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30)!=null){
					if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "Manage Folder icon", action.SCROLLANDBOOLEAN)){

						//Creating shared folder
						if(fp.createFolderStructure(shdPath[0], FolderType.Shared, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(shdPath[0]+ " folder structure is created.");
							//Creating Shared sub folder
							if(fp.createFolderStructure(shdPath[1], FolderType.Shared, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
								appLog.info(shdPath[1]+ " folder structure is created.");
								
								if (click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 60), "Manage Folder Close Button", action.SCROLLANDBOOLEAN)) {
									sa.assertTrue(true, "Successfully created shared folder in Investor Workspace.");
									appLog.info("Successfully created shared folder in Investor Workspace.");
									
								} else{
									appLog.info("Manage folder close button is nto avalaible to click.");
									sa.assertTrue(false, "Manage folder close button is nto avalaible to click.");
								}
							}else{
								sa.assertTrue(false,"Not able to create Folder" +shdPath[1]+"from Manage Folder in Investor Workspace.");
								appLog.error("Not able to create Folder" +shdPath[1]+"from Manage Folder in Investor Workspace.");
							}
						} else{
							sa.assertTrue(false,"Not able to create Folder" +shdPath[0]+"from Manage Folder in Investor Workspace.");
							appLog.error("Not able to create Folder" +shdPath[0]+"from Manage Folder in Investor Workspace.");
						}
					}else{
						sa.assertTrue(false,"Not able to click on Manage Folder icon.");
						appLog.info("Not able to click on Manage Folder icon.");
					}

				} else{
					sa.assertTrue(false,"Manage Folder icon is not available to click.");
					appLog.info("Manage Folder icon is not available to click.");
				}

			}else{
				appLog.error("Not able to click on Created Fund: "+M17FundName1);
				sa.assertTrue(false, "Not able to click on Created Fund: "+M17FundName1);	
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc064_2_InviteContactFromNewlyCreatedSharedFolderAndVerifyItsImpact(String environment, String mode){
		
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath).split(",");
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (fp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				//Inviting contact from newly created Shared folder.
				if (fp.inviteContact(environment, mode, M17Institution1, M17Contact1EmailId, shdPath[0], FolderType.Shared, null, "Yes", "No", "Shared", Workspace.InvestorWorkspace, null)) {
					appLog.info("contact has been given access successfully and invite has been sent to mail");
					
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					//Verify data in Selected Contacts grid.
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");
					String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
					
					appLog.info("Verifying "+contactFullName+" in Selected Contacts grid.");
					if (fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
						appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Enabled is Verified");
						
						WebElement ele = fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
						if(isSelected(driver, ele, "View CheckBox")) {
							appLog.info("View Check box is checked by default.");
						}else {
							appLog.error("View Check box Should be checked");
							sa.assertTrue(false, "View Check box Should be checked");
						}
						
						if(!isEnabled(driver, ele, "View Checkbox")) {
							appLog.info("View Checkbox box is Disabled");
						}else {
							appLog.error("View Checkbox Should be Disabled");
							sa.assertTrue(false, "View Checkbox Should be Disabled");
						}
						
						ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
						if(!isSelected(driver, ele, "Download / Print Documents CheckBox")) {
							appLog.info("Download Check box is Unchecked");
						}else {
							appLog.error("Download Check box Should be Unchecked");
							sa.assertTrue(false, "Download Check box Should be Unchecked");
						}
						
						if(isEnabled(driver, ele, "Download Icon")) {
							appLog.info("Download Check box is Enabled");
						}else {
							appLog.error("Download Check box is not enabled.");
							sa.assertTrue(false, "Download Check box is not enabled.");
							}
						ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.Upload);
						
						//verifying non availability of upload checkbox.
						if(ele==null){
							appLog.info("Upload check box is not available in case of Shared folder.");
						} else{
							appLog.info("Upload check box is available in case of Shared folder.");
							sa.assertTrue(false, "Upload check box is not available in case of Shared folder.");
						}
						
						if(fp.getUploadColumn(10)==null){
							appLog.info("Upload column is not available in case of Shared folder.");
						}else{
							appLog.info("Upload column is available in case of Shared folder.");
							sa.assertTrue(false, "Upload column is available in case of Shared folder.");
						}
						
						
						if(click(driver, fp.getContactAccessCancelBtn(Workspace.InvestorWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("click on contact access pop up cancel button");
						}else {
							appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
							sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
						}
						

					}else {
						appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is not Verified");
						sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is not Verified");
						}
					}else{
						appLog.error("Cann't click on Contact access icon.");
						sa.assertTrue(false, "Cann't click on Contact access icon.");
					} 
				}
				else {
					appLog.error("Contact could not be given access");
					sa.assertTrue(false, "Contact could not be given access");
				}

				
				//Sending Mail to contact
				boolean mail_Status = false;
				switchToDefaultContent(driver);
				switchToFrame(driver, 30,fp. getFrame( PageName.FundsPage, 30));
				ThreadSleep(10000);
				if (fp.sendInvitationMail(Workspace.InvestorWorkspace, M17Contact1EmailId, shdPath[0] , M17Contact1LastName)) {
					appLog.info("contact "+M17Contact1LastName+ " has been sent email for invitation");
					mail_Status=true;
					//Verifying mail received
					try {
						investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail", gmailUserName, gmailPassword,
								CRMUser1EmailID, M17Contact1EmailId);
						appLog.info("Investor Registration Link: " + investorRegLink);
						if(investorRegLink!=null){
							appLog.info("invitation Mail Sent and Received");	
						}else{
							appLog.error("invitation Mail Sent but not Received for "+M17Contact1LastName);
							sa.assertTrue(false, "invitation Mail Sent but not Received for "+M17Contact1LastName);
						}

					} catch (InterruptedException e) {
						appLog.info("Invitation mail not found.");
						e.printStackTrace();
					}
					
				}
				else {
					appLog.error("invitation email could not be sent to "+M17Contact1LastName);
					sa.assertTrue(false, "invitation email could not be sent to "+M17Contact1LastName);
				}
				
				
				
				if(mail_Status){
					appLog.info("Mail Sent");	
				}else{
					appLog.error("invitation email could not be sent to "+M17Contact1LastName);
					sa.assertTrue(false, "MAil is not recived b contact - "+M17Contact1LastName);
				}

				switchToDefaultContent(driver);
			}
			else {
				appLog.error("fund name provided could not be found");
				sa.assertTrue(false, "fund name provided could not be found");
			}
		}
		else {
			appLog.error("Funds tab is not clickable");
			sa.assertTrue(false, "Funds tab is not clickable");
		}

		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc065_1_InvestorUserLoginAndVerifyFolderStructure() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String[] shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath).split(",");
		String[] stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath).split(",");
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.CurrentInvestmentPgae,
					null, 60)) {
				appLog.info(" Folder Structure Verified: " + cmnPath);
			
			}else {
				appLog.error("could not find folder structure "+cmnPath);
				sa.assertTrue(false, "could not find folder structure "+cmnPath);
			}
			
			for(int i=0; i<stdPath.length; i++) {
				if (fp.verifyFolderPathdummy(stdPath[i], null, null, null, PageName.CurrentInvestmentPgae,null, 60)) {
					appLog.info(" Folder Structure Verified: " + stdPath[i]);
					
				}else {
					appLog.error("could not find folder structure "+stdPath[i]);
					sa.assertTrue(false, "could not find folder structure "+stdPath[i]);
				}
			}
			
			for(int i=0; i<shdPath.length; i++) {
				if (fp.verifyFolderPathdummy(shdPath[i], null, null, null, PageName.CurrentInvestmentPgae,null, 60)) {
					appLog.info(" Folder Structure Verified: " + shdPath[i]);
					
				}else {
					appLog.error("could not find folder structure "+shdPath[i]);
					sa.assertTrue(false, "could not find folder structure "+shdPath[i]);
				}
			}
			
			if (ifp.getBulkDownloadIcon(10)!=null) {
				appLog.info("Bulk Download Icon is visibile");
			}else{
				appLog.error("Bulk Download Icon is not visibile");
				sa.assertTrue(false, "Bulk Download Icon is visibile");	
			}
			
			
		}else {
			appLog.error("Not able to click on potential investment tab so cannot verify Folder");
			sa.assertTrue(false, "Not able to click on potential investment tab so cannot verify Folder");
		}
		switchToDefaultContent(driver);
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc065_2_VerifyPermissionAtContactDetailPage(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		WebElement ele=null;
		String depentdenTc="M17tc065_1_InvestorUserLoginAndVerifyFolderStructure";
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, depentdenTc,excelLabel.CommonPath);
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, depentdenTc,excelLabel.SharedPath);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, depentdenTc, excelLabel.StandardPath);
		String folders = cmnPath+","+shdPath+","+stdPath;
		System.err.println("Common : >> "+cmnPath);
		System.err.println("shdPath : >> "+shdPath);
		System.err.println("stdPath : >> "+stdPath);
		System.err.println("Folders NAMES : >>>>>>>   "+folders);
		String[] allFolders = folders.split(",");
		boolean flag=false;
		
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		
		if (cp.clickOnTab(TabName.ContactTab)) {
		if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
			switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
			
			scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");
			
			for (int i=0;i<allFolders.length;i++) {
				
				if (fp.verifyFolderPathdummy(allFolders[i], null, null, M17FundName1, PageName.ContactsPage,
						Workspace.InvestorWorkspace, 60)) {
					
					if ( cp.getViewAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
						appLog.info("View access icon is Enabled for : "+allFolders[i]);
					}
					else {
						appLog.error("View access icon is Should be Enabled for : "+allFolders[i]);
						sa.assertTrue(false,"View access icon is Should be Enabled for : "+allFolders[i]);
					}
					
					
					if (i==0 || i==3 || i==4 || i==5) {
						
						if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
							appLog.info("Download access icon is Enabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Download access icon is Should be Enabled for : "+allFolders[i]);
							sa.assertTrue(false,"Download access icon is Should be Enabled for : "+allFolders[i]);
						}
						
					} else {
						
						if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 5)==null) {
							appLog.info("Download access icon is Disabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Download access icon is Should be Disabled for : "+allFolders[i]);
							sa.assertTrue(false,"Download access icon is Should be Disabled for : "+allFolders[i]);
						}
					}
					
					
					if (i==5 ) {
						
						if ( cp.getUploadAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
							appLog.info("Upload access icon is Enabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Upload access icon is Should be Enabled for : "+allFolders[i]);
							sa.assertTrue(false,"Upload access icon is Should be Enabled for : "+allFolders[i]);
						}
						
					} else {
						
						if ( cp.getUploadAccessIcon(Workspace.InvestorWorkspace, 5)==null) {
							appLog.info("Upload access icon is Disabled for : "+allFolders[i]);
						}
						else {
							appLog.error("Upload access icon is Should be Disabled for : "+allFolders[i]);
							sa.assertTrue(false,"Upload access icon is Should be Disabled for : "+allFolders[i]);
						}
						
					}
					
					
				}
				else {
					appLog.error("common path is not found on contacts page folder structure : "+allFolders[i]);
					sa.assertTrue(false, "common path is not found on contacts page folder structure : "+allFolders[i]);
				}
				
			}
			
		}
		else {
			appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
			sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
		}
	}
	else {
		appLog.error("contact tab is not clickable");
		sa.assertTrue(false, "contact tab is not clickable");
	}

		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(shdPath.split(",")[0], null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+shdPath.split(",")[0]);
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
						
						String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
					
						appLog.info("Going to Verify "+contactFullName+" with Remove Enabled Link");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Enable is Verified");
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}
							
							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
							if(!isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is Unchecked");
								
								if (click(driver, ele, "Download CheckBox 1", action.BOOLEAN)) {
									appLog.info("Clicked on  Download Check 1 box So it is checked");	
										ThreadSleep(2000);
									if (click(driver, ele, "Download CheckBox 2", action.BOOLEAN)) {
										appLog.info("Clicked on  Download Check 2 box So it is Unchecked");	
										
										ThreadSleep(2000);
										if(click(driver, fp.getApplyBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" apply button", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on apply button successfully");
											if(click(driver, fp.getCloseBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" close button", action.SCROLLANDBOOLEAN)) {
												appLog.info("Clicked on Close Buton");	
												flag=true;
											}else {
												appLog.error("Not able to click on close button");
												sa.assertTrue(false, "Not able to click on close button");
											}
										}else{
											appLog.error("Not able to Click on apply button");
											sa.assertTrue(false, "Not able to Click on apply button")	;
										}
										
									} else {
										appLog.error("Not Able to Click on  Download Check 2 box");
										sa.assertTrue(false, "Not Able to Click on  Download Check 2 box");
									}
									
								} else {
									appLog.error("Not Able to Click on  Download Check 1 box");
									sa.assertTrue(false, "Not Able to Click on  Download Check1  box");
								}
							}else {
								appLog.error("Download Check box Should be Unchecked");
								sa.assertTrue(false, "Download Check box Should be Unchecked");
							}
								
						} else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
					
						}
					
						
					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
			}else {
				appLog.error("Folder Structure Not Verified  "+shdPath.split(",")[0]);
				sa.assertTrue(false, "Folder Structure Not Verified  "+shdPath.split(",")[0]);
			}
				
				
		}else {
			appLog.error("Not able to click on created fund Name : "+M17FundName1);
			sa.assertTrue(false, "Not able to click on created fund Name : "+M17FundName1);
		
		}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		
		if (flag) {
		appLog.info("Going to Verify After Check/Uncheck Operation");	
			if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on Contact Access Icon");	
				
				String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
			
				appLog.info("Going to Verify "+contactFullName+" with Remove Enabled Link");
				if (fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
					appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Enable is Verified");
					
					ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
					if(isSelected(driver, ele, "View CheckBox")) {
						appLog.info("View Check box is checked");
					}else {
						appLog.error("View Check box Should be checked");
						sa.assertTrue(false, "View Check box Should be checked");
					}
					
					ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
					if(!isSelected(driver, ele, "Download / Print Documents CheckBox")) {
						appLog.info("Download Check box is Unchecked");
						
					}else {
						appLog.error("Download Check box Should be Unchecked");
						sa.assertTrue(false, "Download Check box Should be Unchecked");
					}
						
				} else {
					appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
					sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
			
				}
				
				if (click(driver, fp.getCrossIconOnContactAccess(Workspace.InvestorWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on Cross Icon");	
				} else {
					appLog.error("Not able to click on Cross Icon");
					sa.assertTrue(false, "Not able to click on Cross Icon");	
				}
				
			}else {
				appLog.error("Not able to click on Contact Access Icon");
				sa.assertTrue(false, "Not able to click on Contact Access Icon");	
			}
			
			
		} else {
			appLog.error("Flag is false so cannot check selected contact Grid in Shared Folder");
			sa.assertTrue(false, "Flag is false so cannot check selected contact Grid in Shared Folder");
		}
	
	switchToDefaultContent(driver);

	lp.CRMlogout();
	sa.assertAll();
	
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc066_1_CheckFunctionalityOfRemoveLinkUnderActionColumn() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		String commonPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		WebElement popUpEle;
		String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
		if (cp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "workspace section");
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage,Workspace.InvestorWorkspace,60)) {
					appLog.error("folder path verified : "+shdPath);
					if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
							WebElement remove = isDisplayed(driver, FindElement(driver, "//a[text()='"+M17Contact1EmailId+"']/../../span/a[@title='Remove']", "remove link in front "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName, action.BOOLEAN, 60), "Visibility", 60, "Remove link");
							if (remove!=null) {
								if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on remove link for : "+contactFullName);
									ThreadSleep(5000);
									if (click(driver, fp.getRemoveAccessClose(Workspace.InvestorWorkspace, 30), "remove access close button", action.BOOLEAN)) {
										appLog.error("Clicked on remove access close button");	
										
										popUpEle = fp.getContactAccessRemovedPopUpMsg(Workspace.InvestorWorkspace, 5);
										if (popUpEle==null) {
											appLog.info("Access Remove PopUp is closed after click on Closed Button");
										}else{
											appLog.error("Access Remove PopUp is not closed after click on Closed Button");
											sa.assertTrue(false, "Access Remove PopUp is not closed after click on Closed Button");	
										}
										
										if(getText(driver, fp.noDataToDisplayContactAccess(Workspace.InvestorWorkspace, 60), "No data to display", action.SCROLLANDBOOLEAN).contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
											appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is displaying");
										}else {
											appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
											sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not displaying");
										}
										
										if (click(driver, fp.getContactAccessCrossIcon(Workspace.InvestorWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on Contact access Cross Icon");
										} else {
											appLog.error("Contact access Cross Icon is not clickable");
											sa.assertTrue(false, "Contact access close button is not clickable");
										}
										
										popUpEle = fp.getContactAccessCrossIcon(Workspace.InvestorWorkspace, 5);
										if (popUpEle==null) {
											appLog.info("Select Contact Access PopUp is closed after click on Cross Icon");
										}else{
											appLog.error("Select Contact Access PopUp is not closed after click on Cross Icon");
											sa.assertTrue(false, "Select Contact Access PopUp is not closed after click on Cross Icon");	
										}
									}
									else {
										appLog.error("remove access close button is not clickable");
										sa.assertTrue(false, "remove access close button is not clickable");
									}
								
								}else {
									appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
									sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
								}
							}
							else {
								appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
								sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
							}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}
				}
				else {
					appLog.error("folder path is not found : "+shdPath);
					sa.assertTrue(false, "folder path is not found : "+shdPath);
				}
				
			}
			else {
				appLog.error(M17FundName1+" is not found on funds page");
				sa.assertTrue(false, M17FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");
				if (fp.verifyFolderPathdummy(commonPath, null, null, M17FundName1, PageName.ContactsPage,Workspace.InvestorWorkspace, 60)) {
					appLog.info("Folder structure Verified : " + commonPath);
				} else {
					appLog.error("Folder structure Not Verified : " + commonPath);
					sa.assertTrue(false, "Folder structure Not Verified : " + commonPath);
				}
				
				if (fp.verifyFolderPathdummy(stdPath, null, null, M17FundName1, PageName.ContactsPage,Workspace.InvestorWorkspace, 60)) {
					appLog.info("Folder structure Verified : " + stdPath);
				} else {
					appLog.error("Folder structure Not Verified : " + stdPath);
					sa.assertTrue(false, "Folder structure Not Verified : " + stdPath);
				}
			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc066_2_CheckFunctionalityOfRemoveLinkImpactInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String dependentTc = "M17tc066_1_CheckFunctionalityOfRemoveLinkUnderActionColumn";
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependentTc,excelLabel.CommonPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependentTc,excelLabel.StandardPath);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.CurrentInvestmentPgae,
					null, 60)) {
				appLog.info(" Folder Structure Verified: " + cmnPath);
			
			}else {
				appLog.error("could not find folder structure "+cmnPath);
				sa.assertTrue(false, "could not find folder structure "+cmnPath);
			}
			
				if (fp.verifyFolderPathdummy(stdPath, null, null, null, PageName.CurrentInvestmentPgae,null, 60)) {
					appLog.info(" Folder Structure Verified: " + stdPath);
					
				}else {
					appLog.error("could not find folder structure "+stdPath);
					sa.assertTrue(false, "could not find folder structure "+stdPath);
				}
			
			
			//
			
			
			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");
				
				String parentID = switchOnWindow(driver);
				
				if (parentID!=null) {
					if (ifp.verifyBulkDownLoadFolderStructure(cmnPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + cmnPath);
						
						
					}else {
						appLog.error("could not find folder structure on Bulk DownLoad: "+cmnPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+cmnPath);
					}

					if (ifp.verifyBulkDownLoadFolderStructure(stdPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + stdPath);
						
						
						
					}else {
						appLog.error("could not find folder structure on Bulk DownLoad"+stdPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+stdPath);
					}
	
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}
				
				
			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}
			
				
		}else {
			appLog.error("Not able to click on Current investment tab.");
			sa.assertTrue(false, "Not able to click on Current investment tab.");
		}
		switchToDefaultContent(driver);
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc067_CheckValidationWhenUserHasNoEmailAddressININV(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		WebElement ele=null;
		String emptyString;
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		String[] ss ={FundsPageErrorMessage.ContactEmailblankErrorMsg,FundsPageErrorMessage.AllContactSelectErrorMsg};
		String[] ss1 ={FundsPageErrorMessage.alreadyInvitedContactAlertMsg,FundsPageErrorMessage.AllContactSelectErrorMsg};

		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Click on Contact Access Icon");
						ThreadSleep(2000);
						if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
							appLog.info("Click on Contact Access Expand Icon");
							if(sendKeys(driver,fp.getSearchTextBox(Workspace.InvestorWorkspace, 60), M17Contact7FirstName+" "+M17Contact7LastName,Workspace.InvestorWorkspace+" search text box", action.SCROLLANDBOOLEAN)) {
								appLog.info("enter the value in search text box : "+M17Contact7FirstName+" "+M17Contact7LastName);
								if(click(driver, fp.getSearchBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" search button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on  "+Workspace.InvestorWorkspace+" search button");

									for (int i=0; i<ss.length; i++) {
										if(i==0) {
											ele=fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, M17Contact7FirstName+" "+M17Contact7LastName, 20);

										}else {
											ele=fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, null, 20);
										}
										if(ele!=null) {
											ThreadSleep(3000);
											if(!clickUsingJavaScript(driver, ele, "check box")) {
//											if(click(driver, ele, "check box", action.BOOLEAN)) {
												appLog.info("clicked on contact check box : ");
												ThreadSleep(2000);
												if (isAlertPresent(driver)) {
													String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
													switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
													if (msg.trim().contains(ss[i])) {
														appLog.info(
																"Error Message is verified in contact access : "+ss[i]);
													} else {
														appLog.error(
																"Error Message not verified in contact access : "+ss[i]);
														sa.assertTrue(false,
																"Error Message not verified in contact access :"+ss[i]);
													}
												} else {
													appLog.error("Alert is not present so cannot verify alert message "+ss[i]);
													sa.assertTrue(false, "Alert is not present so cannot verify alert message "+ss[i]);
												}

												if(!isEnabled(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 5), "select add button")) {
													appLog.info("Add select Contact(s) button is disabled for iteration : "+i);
												}else {
													appLog.error("Add Select Contact(s) button is not disabled for iteration : "+i);
													sa.assertTrue(false, "Add Select Contact(s) button is not disabled for iteration : "+i);
												}


												if (!isSelected(driver, ele, "Check Bpx against ")) {
													appLog.info("CheckBox is Unchecked for iteration : "+i);
												} else {
													appLog.error("CheckBox Should be Unchecked for iteration : "+i);
													sa.assertTrue(false, "CheckBox Should be Unchecked for iteration : "+i);
												}

											}else {
												appLog.error("Not able to click on Check Box for iteration : "+i);
												sa.assertTrue(false, "Not able to click on Check Box for iteration : "+i);
											}
										}else {
											appLog.error("Check Box Element is not visible so cannot verify error message "+ss[i]);
											sa.assertTrue(false, "Check Box Element is not visible so cannot verify error message "+ss[i]);
										}
									}
								
								}else {
									appLog.error("Not Able to Click on Search Icon");
									sa.assertTrue(false, "Not Able to Click on Search Icon");
								}
							}else {
								appLog.error("Not able to enter value on "+Workspace.InvestorWorkspace+" search button so cannot invite contact: "+M17Contact7FirstName+" "+M17Contact7LastName);
								sa.assertTrue(false, "Not able to enter value on "+Workspace.InvestorWorkspace+" search button so cannot invite contact: "+M17Contact7FirstName+" "+M17Contact7LastName);
							}

							if(click(driver, fp.getContactAccessCancelBtn(Workspace.InvestorWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
								appLog.info("click on contact access pop up cancel button");
							}else {
								appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
								sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
							}

						}else {
							appLog.error("Not able to expand contact access grid so cannot check error message : "+ss[0]+" and "+ss[1]);
							sa.assertTrue(false, "Not able to expand contact access grid so cannot check error message : "+ss[0]+" and "+ss[1]);
						}

					}else {
						appLog.error("Not able to click on Contact Access Icon so cannot check error message : "+ss[0]+" and "+ss[1]);
						sa.assertTrue(false, "Not able to click on Contact Access Icon so cannot check error message : "+ss[0]+" and "+ss[1]);	
					}


				}else {
					appLog.error("Folder Path Not verified : "+shdPath);
					sa.assertTrue(false, "Folder Path Not verified : "+shdPath);
				}


				// Invite
				System.err.println("Going for 2nd Round");
				switchToDefaultContent(driver);
				if (fp.inviteContact(environment, mode, null, M17Contact1EmailId, shdPath, FolderType.Shared, null, "Yes", "No", "Shared", Workspace.InvestorWorkspace, null)) {
					appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact has been given access successfully and invite has been sent to mail");
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					//Verify data in Selected Contacts grid.
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");
						String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;

						appLog.info("Verifying "+contactFullName+" in Selected Contacts grid.");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Enabled is Verified");

							ele = fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked by default.");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}

							if(!isEnabled(driver, ele, "View Checkbox")) {
								appLog.info("View Checkbox box is Disabled");
							}else {
								appLog.error("View Checkbox Should be Disabled");
								sa.assertTrue(false, "View Checkbox Should be Disabled");
							}

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
							if(!isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is Unchecked");
							}else {
								appLog.error("Download Check box Should be Unchecked");
								sa.assertTrue(false, "Download Check box Should be Unchecked");
							}

							if(isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Enabled");
							}else {
								appLog.error("Download Check box is not enabled.");
								sa.assertTrue(false, "Download Check box is not enabled.");
							}

						}else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is not Verified");
						}

						if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
							appLog.info("Click on Contact Access Expand Icon");
							

							for (int i = 0; i < ss1.length; i++) {
								String searchValue ;
								if(i==0){
									searchValue = M17Contact1FirstName+" "+M17Contact1LastName;
								}else{
									searchValue=M17Contact1FirstName;
								}

								if(sendKeys(driver,fp.getSearchTextBox(Workspace.InvestorWorkspace, 60), searchValue,Workspace.InvestorWorkspace+" search text box", action.SCROLLANDBOOLEAN)) {
									appLog.info("enter the value in search text box : "+searchValue);
									if(click(driver, fp.getSearchBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" search button", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on  "+Workspace.InvestorWorkspace+" search button");

										if(i==0) {
											ele=fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, M17Contact7FirstName+" "+M17Contact1LastName, 20);
										}else {
											ele=fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, null, 20);
										}
										if(ele!=null) {
											ThreadSleep(3000);
											if(!clickUsingJavaScript(driver, ele, "check box")) {
//											if(click(driver, ele, "check box", action.BOOLEAN)) {
												appLog.info("clicked on contact check box for iteratin : "+i);
												ThreadSleep(2000);
												if (isAlertPresent(driver)) {
													String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
													switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
													if (msg.trim().contains(ss1[i])) {
														appLog.info(
																"Error Message is verified in contact access : "+ss1[i]);
													} else {
														appLog.error(
																"Error Message not verified in contact access : "+ss1[i]);
														sa.assertTrue(false,
																"Error Message not verified in contact access :"+ss1[i]);
													}
												} else {
													appLog.error("Alert is not present so cannot verify alert message "+ss1[i]);
													sa.assertTrue(false, "Alert is not present so cannot verify alert message "+ss1[i]);
												}
												
												if (!isSelected(driver, ele, "Check Bpx against ")) {
													appLog.info("CheckBox is Unchecked for iteration : "+i);
												} else {
													appLog.error("CheckBox Should be Unchecked for iteration : "+i);
													sa.assertTrue(false, "CheckBox Should be Unchecked for iteration : "+i);
												}

												if (click(driver, fp.getClearIconOnContactAccessSearchBox(Workspace.InvestorWorkspace, 10), "Clear Icon", action.SCROLLANDBOOLEAN)) {
													appLog.info("Clicked on Cross Icon");
													emptyString = getValueFromElementUsingJavaScript(driver, fp.getSearchTextBox(Workspace.InvestorWorkspace, 60), "Search Box");

													if(emptyString.isEmpty()) {
														appLog.info("Search Box is Empty for iteration : "+i);
													} else {
														appLog.info("Search Box is not Empty with Value : "+emptyString);
														sa.assertTrue(false, "Search Box is not Empty with Value : "+emptyString);
													}
												}else{
													appLog.info("Not Able to Click on Clear Icon on Search Box for iteration : "+i);
													sa.assertTrue(false, "Not Able to Click on Clear Icon on Search Box for iteration : "+i);	
												}

											}else {
												appLog.error("Not able to click on Check Box for iteration : "+i);
												sa.assertTrue(false, "Not able to click on Check Box for iteration : "+i);
											}
										}else {
											appLog.error("Check Box Element is not visible so cannot verify error message "+ss1[i]);
											sa.assertTrue(false, "Check Box Element is not visible so cannot verify error message "+ss1[i]);
										}

									}else {
										appLog.error("Not Able to Click on Search Icon for iteration : "+i);
										sa.assertTrue(false, "Not Able to Click on Search Icon for iteration : "+i);
									}
								}else {
									appLog.error("Not able to enter value on "+Workspace.InvestorWorkspace+"so cannot verify error message "+ss1[i]);
									sa.assertTrue(false, "Not able to enter value on "+Workspace.InvestorWorkspace+"so cannot verify error message "+ss1[i]);
								}

							}
							
						}else{
							appLog.error("Not able to expand contact access grid so cannot check error message : "+ss1[0]+" and "+ss1[1]);
							sa.assertTrue(false, "Not able to expand contact access grid so cannot check error message : "+ss1[0]+" and "+ss1[1]);
							
						}


						if(click(driver, fp.getContactAccessCancelBtn(Workspace.InvestorWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("click on contact access pop up cancel button");
						}else {
							appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
							sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
						}

					}else{
						appLog.error("Cann't click on Contact access icon.");
						sa.assertTrue(false, "Cann't click on Contact access icon.");
					} 
				}
				else {
					appLog.error("Not Able to give Access to "+M17Contact1FirstName+" "+M17Contact1LastName);
					sa.assertTrue(false, "Not Able to give Access to "+M17Contact1FirstName+" "+M17Contact1LastName);
				}


			}else {
				appLog.error("Not able to click on created fund Name "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name "+M17FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc068_1_UpdateAndRevertEmailAddressFromContactPageandCheckImpactAtContactAccessPopUP(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		String commonPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String[] allFolders = (shdPath+","+stdPath+","+commonPath).split(",");
		WebElement ele;
		String updatedEmail=cp.generateRandomEmailId();
		String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");

		if(cp.updateEmailAddressOfCreatedContact(M17Contact1FirstName, M17Contact1LastName,updatedEmail)) {
			appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact email id is updated");
			ExcelUtils.writeData(updatedEmail,"Contacts", excelLabel.Variable_Name, "M14Contact1",excelLabel.ContactUpdatedEmailID);
		}else {
			appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact email id is not updated");
			sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact email id is not updated");
		}

		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+shdPath);
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	

						appLog.info("Going to Verify "+contactFullName+" with Remove Enabled Link");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Enable is Verified");

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked ");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}

							if(!isEnabled(driver, ele, "View Icon")) {
								appLog.info("View Icon is disabled");
							}else {
								appLog.error("View Icon is should be disabled");
								sa.assertTrue(false, "View Icon is should be disabled");
							}

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
							if(!isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is Unchecked");
							}else {
								appLog.error("Download Check box Should be Unchecked");
								sa.assertTrue(false, "Download Check box Should be Unchecked");
							}

							if(isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Enabled");
							}else {
								appLog.error("Download Check box Should be Enabled");
								sa.assertTrue(false, "Download Check box Should be Enabled");
							}


						} else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");

						}

						if (click(driver, fp.getCrossIconOnContactAccess(Workspace.InvestorWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on Cross Icon");	
						} else {
							appLog.error("Not able to click on Cross Icon");
							sa.assertTrue(false, "Not able to click on Cross Icon");	
						}

					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
				}else {
					appLog.error("Folder Structure Not Verified  "+shdPath);
					sa.assertTrue(false, "Folder Structure Not Verified  "+shdPath);
				}


			}else {
				appLog.error("Not able to click on created fund Name : "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name : "+M17FundName1);

			}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		switchToDefaultContent(driver);
		if(cp.updateEmailAddressOfCreatedContact(M17Contact1FirstName, M17Contact1LastName,M17Contact1EmailId)) {
			appLog.info(M17Contact1FirstName+" "+M17Contact1LastName+" contact email id is Restored");
		}else {
			appLog.error(M17Contact1FirstName+" "+M17Contact1LastName+" contact email id is not Restored");
			sa.assertTrue(false, M17Contact1FirstName+" "+M17Contact1LastName+" contact email id is not Restored");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				for (int i = 0; i < allFolders.length; i++) {
					String ins = null;
					String lmp = null;
				if (i==1) {
					ins=M17Institution1;
					lmp=M17LimitedPartner1;
				}
				if (fp.verifyFolderPathdummy(allFolders[i], ins, lmp, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+allFolders[i]);
				}else {
					appLog.error("Folder Structure Not Verified  "+allFolders[i]);
					sa.assertTrue(false, "Folder Structure Not Verified  "+allFolders[i]);
				}
				}
			}else {
				appLog.error("Not able to click on created fund Name : "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name : "+M17FundName1);

			}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc068_2_VerifyFolderStructureAndBulkDownloadInInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String dependentTC = "M17tc068_1_UpdateAndRevertEmailAddressFromContactPageandCheckImpactAtContactAccessPopUP";
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependentTC,excelLabel.SharedPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependentTC,excelLabel.StandardPath);
		String commonPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependentTC,excelLabel.CommonPath);
		String[] allFolders = (shdPath+","+stdPath+","+commonPath).split(",");
		String parentID;
		String alertMsg=InvestorFirmPageErrorMessage.LimitedAccessErrorMessageBulkDownload;
		String msg ;

		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			
			for (int i = 0; i < allFolders.length; i++) {
				
				if (fp.verifyFolderPathdummy(allFolders[i], null, null, null, PageName.CurrentInvestmentPgae,null, 20)) {
					appLog.info(" Folder Structure Verified: " + allFolders[i]);
				}else {
					appLog.error("could not find folder structure "+allFolders[i]);
					sa.assertTrue(false, "could not find folder structure "+allFolders[i]);
				}

			}

			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");

				parentID=switchOnWindow(driver);

				if (parentID!=null) {

					for (int i = 0; i < allFolders.length; i++) {

						if (ifp.verifyBulkDownLoadFolderStructure(allFolders[i], 20)) {
							appLog.info(" Folder Structure Verified on Bulk DownLoad: " + allFolders[i]);
						}else {
							appLog.error("could not find folder structure on Bulk DownLoad: "+allFolders[i]);
							sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+allFolders[i]);
						}

					}

					if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(shdPath, 10)) {
						appLog.info("Clicked on CheckBox for Folder: "+shdPath);	
						ThreadSleep(2000);
						if (isAlertPresent(driver)) {
							msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
							switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
							if (msg.trim().contains(alertMsg)) {
								appLog.info("Error Message is verified in contact access : "+alertMsg);
							} else {
								appLog.error("Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
								sa.assertTrue(false,"Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
							}
						} else {
							appLog.error("Alert is not present so cannot verify alert message "+alertMsg);
							sa.assertTrue(false, "Alert is not present so cannot verify alert message "+alertMsg);
						}
					} else {
						sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+shdPath);
						appLog.error("Not Able to Click on CheckBox for Folder: "+shdPath);
					}	
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}


			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}


		}else{
			appLog.error("Not Able to Click on "+investorSideWorkSpace.CurrentInvestment);
			sa.assertTrue(false, "Not Able to Click on "+investorSideWorkSpace.CurrentInvestment);	
		}

		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc069_1_ProvideDownloadPermissionToTheInvitedContactINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage,Workspace.InvestorWorkspace,60)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, 10, CheckBoxName.Download), "DownLoad Documents CheckBox", action.BOOLEAN)) {
							appLog.info("DownLoad CheckBox Checked");
							if(click(driver, fp.getApplyBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" apply button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on apply button successfully");
								if(click(driver, fp.getCloseBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" close button", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on Close Buton");	
								}else {
									appLog.error("Not able to click on close button");
									sa.assertTrue(false, "Not able to click on close button");
								}
							}else{
								appLog.error("Not able to Click on apply button");
								sa.assertTrue(false, "Not able to Click on apply button")	;
							}
						} else {
							appLog.error("Not able to click on DownLoad Documents CheckBox for Contact : "+M17Contact1EmailId);
							sa.assertTrue(false, "Not able to click on DownLoad Documents CheckBox  for Contact : "+M17Contact1EmailId);
						}
					}else {
						appLog.error("Not able to click on contact access icon on folder "+shdPath);
						sa.assertTrue(false, "Not able to click on contact access icon on folder "+shdPath);
					}

				}else {
					appLog.error("Not able to click on folder structure "+shdPath);
					sa.assertTrue(false, "Not able to click on folder structure "+shdPath);
				}

			}else {
				appLog.error("Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
				sa.assertTrue(false, "Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
			}

		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
		}
		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");


				if (fp.verifyFolderPathdummy(shdPath, null, null, M17FundName1, PageName.ContactsPage,
						Workspace.InvestorWorkspace, 60)) {

					if ( cp.getViewAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
						appLog.info("View access icon is Enabled for : "+shdPath);
					}
					else {
						appLog.error("View access icon is Should be Enabled for : "+shdPath);
						sa.assertTrue(false,"View access icon is Should be Enabled for : "+shdPath);
					}

					if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
						appLog.info("Download access icon is Enabled for : "+shdPath);
					}
					else {
						appLog.error("Download access icon is Should be Enabled for : "+shdPath);
						sa.assertTrue(false,"Download access icon is Should be Enabled for : "+shdPath);
					}

					if ( cp.getUploadAccessIcon(Workspace.InvestorWorkspace, 5)==null) {
						appLog.info("Upload access icon is Disabled for : "+shdPath);
					}
					else {
						appLog.error("Upload access icon is Should be Disabled for : "+shdPath);
						sa.assertTrue(false,"Upload access icon is Should be Disabled for : "+shdPath);
					}
				}
				else {
					appLog.error("folder path is not found on contacts page folder structure : "+shdPath);
					sa.assertTrue(false, "folder path is not found on contacts page folder structure : "+shdPath);
				}

			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc069_2_VerifyFolderStructureAndBulkDownloadInInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		String commonPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String[] allFolders = (shdPath+","+stdPath+","+commonPath).split(",");
		String parentID;

		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {


			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");

				parentID=switchOnWindow(driver);

				if (parentID!=null) {

					for (int i = 0; i < allFolders.length; i++) {
						if (ifp.verifyBulkDownLoadFolderStructure(allFolders[i], 20)) {
							appLog.info(" Folder Structure Verified on Bulk DownLoad: " + allFolders[i]);
						}else {
							appLog.error("could not find folder structure on Bulk DownLoad: "+allFolders[i]);
							sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+allFolders[i]);
						}	
					}

					if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(shdPath.split("/")[0], 10)) {
						appLog.info("Clicked on CheckBox for Folder: "+shdPath.split("/")[0]);	
						if (click(driver, ifp.getBulkDownloadDownloadButton(10), "DownLoad Button", action.BOOLEAN)) {


							if (click(driver, ifp.getBulkDownloadConfirmationPopUpYesBtn(30),
									"Bulk Download Yes Button", action.BOOLEAN)) {
								appLog.error("Clicked on Yes Button.");


								Robot rob;
								try {
									rob = new Robot();
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
								} catch (AWTException e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
								ThreadSleep(20000);
								driver.close();
								driver.switchTo().window(parentID);
								// ele.sendKeys(Keys.CONTROL+"j");
								parentID = switchOnWindow(driver);
								if (parentID == null) {
									try {
										rob = new Robot();
										rob.keyPress(KeyEvent.VK_TAB);
										rob.keyRelease(KeyEvent.VK_TAB);
										rob.keyPress(KeyEvent.VK_ENTER);
										rob.keyRelease(KeyEvent.VK_ENTER);
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_CONTROL);
										rob.keyPress(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_CONTROL);
										parentID = switchOnWindow(driver);
									} catch (AWTException e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}
								}

								ThreadSleep(5000);
								String fileName = ifp.getDownloadedFileName();
								if (fileName.contains(M17FundName1 + " " + getSystemDate("MM-dd-YYYY"))) {
									appLog.info("file name is successfully matched. " + fileName);
								} else {
									appLog.info("File name is not the matched. Expected: " + M17FundName1 + " "
											+ getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
									sa.assertTrue(false, "File name is not the matched. Expected: " + M17FundName1
											+ " " + getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
								}
								driver.close();
								driver.switchTo().window(parentID);

							} else {
								appLog.error("Not Able to Click on Yes Button.");
								sa.assertTrue(false, "Not Able to Click on Yes Button.");
							}


						} else {
							appLog.error("Download Button is not working.");
							sa.assertTrue(false, "Download Button is not working.");
						}


					} else {
						appLog.error("Not Able to Click on CheckBox for Folder: "+shdPath.split("/")[0]);
						sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+shdPath.split("/")[0]);

					}	


				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}


			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}


		}else{
			appLog.error("Not Able to Click on "+investorSideWorkSpace.CurrentInvestment);
			sa.assertTrue(false, "Not Able to Click on "+investorSideWorkSpace.CurrentInvestment);	
		}

		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc070_TryToProvideAccessFromChildFolderWhenAccessAlreadyProvidedFromParentLevelSharedFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		WebElement ele=null;
		String alertMsg =FundsPageErrorMessage.alreadyInvitedContactAlertMsg;
		String msg;
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+shdPath);
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
						appLog.info("Verifying "+contactFullName+" in Selected Contacts grid.");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, EnableDisable.Disable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Icon Disable is Verified");

							ele = fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace,EnableDisable.Disable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}

							if(!isEnabled(driver, ele, "View Checkbox")) {
								appLog.info("View Checkbox box is Disabled");
							}else {
								appLog.error("View Checkbox Should be Disabled");
								sa.assertTrue(false, "View Checkbox Should be Disabled");
							}

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, EnableDisable.Disable, contactFullName, 5, CheckBoxName.Download);
							if(isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is checked");
							}else {
								appLog.error("Download Check box Should be checked");
								sa.assertTrue(false, "Download Check box Should be checked");
							}

							if(!isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Disable");
							}else {
								appLog.error("Download Check box is not Disable.");
								sa.assertTrue(false, "Download Check box is not Disable.");
							}

						}else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is not Verified");
						}

						if (fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
							appLog.info("Click on Expand Icon of Select Contacts");

							ele=fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20);
							if(ele!=null) {
								ThreadSleep(3000);
								if(!clickUsingJavaScript(driver, ele, "check box")) {
//								if(click(driver, ele, "check box against : "+M17Contact1FirstName+" "+M17Contact1LastName, action.BOOLEAN)) {
									appLog.info("clicked on check box ");
									ThreadSleep(2000);
									if (isAlertPresent(driver)) {
										msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if (msg.trim().equals(alertMsg)) {
											appLog.info(
													"Alert Message is verified in contact access : "+msg);
										} else {
											appLog.error(
													"Alert Message is not verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
											sa.assertTrue(false,
													"Alert Message is not verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
										}

									} else {
										appLog.error("Alert is not present so cannot verify alert message "+alertMsg);
										sa.assertTrue(false, "Alert is not present so cannot verify alert message "+alertMsg);
									}
								}else {
									appLog.error("Not able to click on Checkbox");
									sa.assertTrue(false, "Not able to click on Checkbox of on Contact ");
								}

							}else{
								appLog.error("Checkbox Element is null for "+M17Contact1FirstName+" "+M17Contact1LastName);
								sa.assertTrue(false, "Checkbox Element is null for "+M17Contact1FirstName+" "+M17Contact1LastName);

							}


						}else{
							appLog.error("Not able to click on Expand Icon of Select Contacts");
							sa.assertTrue(false, "Not able to click on Expand Icon of Select Contacts");
						}

						ele = fp.getremoveToolTipInfo(Workspace.InvestorWorkspace, M17Contact1EmailId, 10);
						if (mouseOverOperation(driver, ele)) {
							ThreadSleep(1000);
							appLog.info("Mouse Hover on info Icon");
							msg = ele.getAttribute("title").trim();
							if (msg.equals(FundsPageErrorMessage.toolTipinheritedPermissiononRemoveLink)) {
								appLog.info("Info Link Message is verified  : "+msg);
							} else {
								appLog.error(
										"Info Link Message is not verified in contact access Actual : "+msg+" \t Expected : "+FundsPageErrorMessage.toolTipinheritedPermissiononRemoveLink);
								sa.assertTrue(false,
										"Info Link Message is not verified in contact access Actual : "+msg+" \t Expected : "+FundsPageErrorMessage.toolTipinheritedPermissiononRemoveLink);
							}

						} else {
							appLog.error("Not able to perform Mouse Hover on info Icon");
							sa.assertTrue(false, "Not able to perform Mouse Hover on info Icon");
						}

						if (click(driver, fp.getCrossIconOnContactAccess(Workspace.InvestorWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on Cross Icon");	
						} else {
							appLog.error("Not able to click on Cross Icon");
							sa.assertTrue(false, "Not able to click on Cross Icon");	
						}

					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
				}else {
					appLog.error("Folder Structure Not Verified  "+shdPath);
					sa.assertTrue(false, "Folder Structure Not Verified  "+shdPath);
				}

				if (fp.verifyFolderPathdummy(shdPath.split("/")[0], null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+shdPath.split("/")[0]);
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
						WebElement remove = fp.getcontactaccessremoveLink(Workspace.InvestorWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
						if (remove!=null) {
							if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on remove link");
								if (click(driver, fp.getRemoveAccessClose(Workspace.InvestorWorkspace, 30), "remove access close button", action.BOOLEAN)) {
									appLog.info("Clicked on remove access close button");

									if (click(driver, fp.getCrossIconOnContactAccess(Workspace.InvestorWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on Cross Icon on Contact Access");	
									} else {
										appLog.error("clicked on Cross Icon on Contact Access");
										sa.assertTrue(false, "clicked on Cross Icon on Contact Access");	
									}

								}
								else {
									appLog.error("remove access close button is not clickable");
									sa.assertTrue(false, "remove access close button is not clickable");
								}
							}else {
								appLog.error("Not able to click on remove link of contact: "+M17Contact1EmailId);
								sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1EmailId);
							}
						}
						else {
							appLog.error("remove button adjacent to "+M17Contact1EmailId+" is not found");
							sa.assertTrue(false, "remove button adjacent to "+M17Contact1EmailId+" is not found");
						}
					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}

				}else {
					appLog.error("Folder Structure Not Verified  "+shdPath.split("/")[0]);
					sa.assertTrue(false, "Folder Structure Not Verified  "+shdPath.split("/")[0]);
				}	

			}else {
				appLog.error("Not able to click on created fund Name : "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name : "+M17FundName1);

			}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc071_inviteContactFromSubSharedFolderAndCreateSubFolderCheckPermission(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath).split(",");
		String errormsg=FundsPageErrorMessage.alreadyInvitedContactAlertMsg;
		String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		WebElement ele;
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				// invite contcat Shared sub Folder
				if(fp.inviteContact(environment,mode,null, M17Contact1EmailId,shdPath[0], FolderType.Shared, "download", "Yes", null,null, Workspace.InvestorWorkspace, null)) {
					appLog.info(M17Contact1EmailId+" Contact is selected successfully ");
					ThreadSleep(2000);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Fundraising workspace view.");
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
						ThreadSleep(5000);
						appLog.info("Verifying "+contactFullName+" in Selected Contacts grid.");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.info("Contact Grid Information related to "+contactFullName+ " with Remove Icon Enabled is Verified");

							ele = fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked by default.");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}

							if(!isEnabled(driver, ele, "View Checkbox")) {
								appLog.info("View Checkbox box is Disabled");
							}else {
								appLog.error("View Checkbox Should be Disabled");
								sa.assertTrue(false, "View Checkbox Should be Disabled");
							}

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
							if(isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is checked");
							}else {
								appLog.error("Download Check box Should be checked");
								sa.assertTrue(false, "Download Check box Should be checked");
							}

							if(isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Enabled");
							}else {
								appLog.error("Download Check box is not enabled.");
								sa.assertTrue(false, "Download Check box is not enabled.");
							}


						}else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Icon Enable is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Icon Enable is not Verified");
						}

						if(click(driver, fp.getContactAccessCancelBtn(Workspace.InvestorWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("click on contact access pop up cancel button");
						}else {
							appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
							sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
						}
					}else {
						appLog.error("Not able to click on contact access icon so cannot verify select contact "+M17Contact1FirstName+" "+M17Contact1LastName);
						sa.assertTrue(false, "Not able to click on contact access icon so cannot verify select contact "+M17Contact1FirstName+" "+M17Contact1LastName);
					}
					
					// Create Sub Sub Shared Folder
					
					if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "Manage folder icon", action.SCROLLANDBOOLEAN)){
						if(fp.createFolderStructure(shdPath[1], FolderType.Shared, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(shdPath[1]+ " folder structure is created.");
							if(click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 20), "close button", action.SCROLLANDBOOLEAN)) {
								if(fp.verifyFolderPathdummy(shdPath[1], null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
									if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
										appLog.info("Going to Verify "+contactFullName+" with Remove Disable Link");
										if (fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, EnableDisable.Disable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
											appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Disable is Verified");

											ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace,EnableDisable.Disable, contactFullName, 5, CheckBoxName.View);
											if(isSelected(driver, ele, "View CheckBox")) {
												appLog.info("View Check box is checked ");
											}else {
												appLog.error("View Check box Should be checked");
												sa.assertTrue(false, "View Check box Should be checked");
											}

											if(!isEnabled(driver, ele, "View Icon")) {
												appLog.info("View Icon is disabled");
											}else {
												appLog.error("View Icon is should be disabled");
												sa.assertTrue(false, "View Icon is should be disabled");
											}

											ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, EnableDisable.Disable, contactFullName, 5, CheckBoxName.Download);
											if(isSelected(driver, ele, "Download / Print Documents CheckBox")) {
												appLog.info("Download Check box is checked");
											}else {
												appLog.error("Download Check box Should be checked");
												sa.assertTrue(false, "Download Check box Should be checked");
											}

											if(!isEnabled(driver, ele, "Download Icon")) {
												appLog.info("Download Check box is Disable");
											}else {
												appLog.error("Download Check box Should be Disable");
												sa.assertTrue(false, "Download Check box Should be Disable");
											}


										} else {
											appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Link Disable is not Verified");
											sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Link Disable is not Verified");

										}
										if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
											ThreadSleep(3000);
											if(!clickUsingJavaScript(driver, fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20), "check box")) {
//											if(click(driver, fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20), "Check Box", action.SCROLLANDBOOLEAN)) {
												ThreadSleep(2000);
												if (isAlertPresent(driver)) {
													String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
													switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
													if (msg.trim().equals(errormsg)) {
														appLog.info(
																"Error Message is verified in contact access : "+errormsg);
													} else {
														appLog.error(
																"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+errormsg);
														sa.assertTrue(false,
																"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+errormsg);
													}

												} else {
													appLog.error("Alert is not present so cannot verify alert message "+errormsg);
													sa.assertTrue(false, "Alert is not present so cannot verify alert message "+errormsg);
												}
												if(click(driver, fp.getContactAccessCancelBtn(Workspace.InvestorWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
													appLog.info("click on contact access pop up cancel button");
												}else {
													appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
													sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
												}
											}else {
												appLog.error("Not able to click on Contact Name "+M17Contact1FirstName+" "+M17Contact1LastName+" so cannot check error message "+errormsg);
												sa.assertTrue(false, "Not able to click on Contact Name "+M17Contact1FirstName+" "+M17Contact1LastName+" so cannot check error message "+errormsg);
											}

										}else {
											appLog.error("Not able to expand contact access pop up so cannot verify error message "+errormsg);
											sa.assertTrue(false, "Not able to expand contact access pop up so cannot verify error message "+errormsg);
										}

									}else {
										appLog.error("Not able to click on contact access icon so cannot verify inherit contact access permission");
										sa.assertTrue(false, "Not able to click on contact access icon so cannot verify inherit contact access permission");
									}

								}else {
									appLog.error("Not able to click on folder structure "+shdPath[1]+" so cannot check inherit contact access permission on sub folder");
									sa.assertTrue(false, "Not able to click on folder structure "+shdPath[1]+" so cannot check inherit contact access permission on sub folder");
								}

							}else {
								appLog.error("Not able to click on manage folder close button");
								sa.assertTrue(false, "Not able to click on manage folder close button");
							}

						} else {
							appLog.error(shdPath[1]+ " folder structure is not created.");
							sa.assertTrue(false,shdPath[1]+ " folder structure is not created.");
						}
					}else {
						appLog.error("Not able to click on manage folder icon so cannot create sub folder");
						sa.assertTrue(false, "Not able to click on manage folder icon so cannot create sub folder");
					}
					
					if(fp.verifyFolderPathdummy(shdPath[2], null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
						if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
							ele=fp.noDataToDisplayContactAccess(Workspace.InvestorWorkspace, 60);
							if (ele!=null) {
								String	msg =ele.getText().trim();
								if(msg.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
									appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message Verified");
								}else {
									appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
									sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
								}
							} else {
								appLog.error("No Data msg element is null");
								sa.assertTrue(false, "No Data msg element is null");	
							}

							if(click(driver, fp.getContactAccessCancelBtn(Workspace.InvestorWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
								appLog.info("click on contact access pop up cancel button");
							}else {
								appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
								sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
							}

						}else {
							appLog.error("Not able to click on contact access icon so cannot check error message ");
							sa.assertTrue(false, "Not able to click on contact access icon so cannot check error message ");
						}
					}else {
						appLog.error("Not able to click on folder structure "+shdPath[2]+" so cannot check error message ");
						sa.assertTrue(false, "Not able to click on folder structure "+shdPath[2]+" so cannot check error message ");
					}
					
					switchToDefaultContent(driver);
					
					if(fp.inviteContact(environment,mode,null, M17Contact1EmailId,shdPath[2], FolderType.Shared, null, "Yes", null,null, Workspace.InvestorWorkspace, null)) {
						appLog.info("Contact is invited "+M17Contact1FirstName+" "+M17Contact1LastName);
					}else {
						appLog.error("Not able to invite contact from folder "+shdPath[2]);
						sa.assertTrue(false, "Not able to invite contact from folder "+shdPath[2]);
					}

				}else {
					appLog.error("Not able to select Contact from contact access grid "+M17Contact1EmailId);
					sa.assertTrue(false, "Not able to select Contact from contact access grid "+M17Contact1EmailId);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact "+M17Contact1EmailId);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact "+M17Contact1EmailId);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc072_1_VerifyAccessFromAddedSharedChildFolderCRMSide(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		WebElement ele=null;
		String[] shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath).split(",");
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "InvestorWorkspace view.");
				if (fp.verifyFolderPathdummy(shdPath[1], null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+shdPath[1]);
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	

						String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;

						appLog.info("Going to Verify "+contactFullName+" with Remove Disabled Link");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, EnableDisable.Disable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is Verified");

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace,EnableDisable.Disable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked as Contact is Invited From Parent Folder");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, EnableDisable.Disable, contactFullName, 5, CheckBoxName.Download);
							if(!isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is Unchecked");
							}else {
								appLog.error("Download Check box Should be Unchecked");
								sa.assertTrue(false, "Download Check box Should be Unchecked");
							}

							if(!isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Disabled");
							}else {
								appLog.error("Download Check box Should be Disabled");
								sa.assertTrue(false, "Download Check box Should be Disabled");
							}



						} else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Reove Icon Disabled is not Verified");

						}
						appLog.info("Going to Verify "+contactFullName+" with Remove Enabled Link");
						if (fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Icon Enable is Verified");

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace,EnableDisable.Enable, contactFullName, 5, CheckBoxName.View);
							if(isSelected(driver, ele, "View CheckBox")) {
								appLog.info("View Check box is checked as Contact is Invited From Parent Folder");
							}else {
								appLog.error("View Check box Should be checked");
								sa.assertTrue(false, "View Check box Should be checked");
							}

							ele=fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download);
							if(isSelected(driver, ele, "Download / Print Documents CheckBox")) {
								appLog.info("Download Check box is checked");
							}else {
								appLog.error("Download Check box Should be checked");
								sa.assertTrue(false, "Download Check box Should be checked");
							}

							if(isEnabled(driver, ele, "Download Icon")) {
								appLog.info("Download Check box is Enabled");
							}else {
								appLog.error("Download Check box Should be Enabled");
								sa.assertTrue(false, "Download Check box Should be Enabled");
							}


						} else {
							appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
							sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");

						}

						if (click(driver, fp.getCrossIconOnContactAccess(Workspace.InvestorWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on Cross Icon");	
						} else {
							appLog.error("Not able to click on Cross Icon");
							sa.assertTrue(false, "Not able to click on Cross Icon");	
						}

					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}
				}else {
					appLog.error("Folder Structure Not Verified  "+shdPath[1]);
					sa.assertTrue(false, "Folder Structure Not Verified  "+shdPath[1]);
				}


			}else {
				appLog.error("Not able to click on created fund Name : "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name : "+M17FundName1);

			}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		switchToDefaultContent(driver);


		if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));

				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");

				for (int i=0;i<shdPath.length;i++) {

					if (fp.verifyFolderPathdummy(shdPath[i], null, null, M17FundName1, PageName.ContactsPage,
							Workspace.InvestorWorkspace, 60)) {
						appLog.info("Folder Structure verified on Contact Page : "+shdPath[i]);
						if ( cp.getViewAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
							appLog.info("View access icon is Enabled for : "+shdPath[i]);
						}
						else {
							appLog.error("View access icon is Should be Enabled for : "+shdPath[i]);
							sa.assertTrue(false,"View access icon is Should be Enabled for : "+shdPath[i]);
						}


						if (i!=0) {

							if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
								appLog.info("Download access icon is Enabled for : "+shdPath[i]);
							}
							else {
								appLog.error("Download access icon is Should be Enabled for : "+shdPath[i]);
								sa.assertTrue(false,"Download access icon is Should be Enabled for : "+shdPath[i]);
							}

						} else {

							if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 5)==null) {
								appLog.info("Download access icon is Disabled for : "+shdPath[i]);
							}
							else {
								appLog.error("Download access icon is Should be Disabled for : "+shdPath[i]);
								sa.assertTrue(false,"Download access icon is Should be Disabled for : "+shdPath[i]);
							}
						}

						if ( cp.getUploadAccessIcon(Workspace.InvestorWorkspace, 2)==null) {
							appLog.info("Upload access icon is Disabled for : "+shdPath[i]);
						}
						else {
							appLog.error("Upload access icon is Should be Disabled for : "+shdPath[i]);
							sa.assertTrue(false,"Upload access icon is Should be Disabled for : "+shdPath[i]);
						}


					}
					else {
						appLog.error("shared path is not found on contacts page folder structure : "+shdPath[i]);
						sa.assertTrue(false, "shared path is not found on contacts page folder structure : "+shdPath[i]);
					}

				}

			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}

		switchToDefaultContent(driver);

		lp.CRMlogout();
		sa.assertAll();

}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc072_2_VerifyAccessFromAddedSharedChildFolderInvestoride() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String[] allFolders=(cmnPath+","+stdPath+","+shdPath).split(",");
		String parentID;
		String alertMsg=InvestorFirmPageErrorMessage.LimitedAccessErrorMessageBulkDownload;
		String msg ;
		boolean flag=true;

		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {

			for (int i = 0; i < allFolders.length; i++) {

				if (fp.verifyFolderPathdummy(allFolders[i], null, null, null, PageName.CurrentInvestmentPgae,
						null, 20)) {
					appLog.info(" Folder Structure Verified: " + allFolders[i]);

				}else {
					appLog.error("could not find folder structure "+allFolders[i]);
					sa.assertTrue(false, "could not find folder structure "+allFolders[i]);
				}

			}

			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");

				parentID=switchOnWindow(driver);

				if (parentID!=null) {

					for (int i = 0; i < allFolders.length; i++) {

						if (ifp.verifyBulkDownLoadFolderStructure(allFolders[i], 20)) {
							appLog.info(" Folder Structure Verified on Bulk DownLoad: " + allFolders[i]);
						}else {
							appLog.error("could not find folder structure on Bulk DownLoad"+allFolders[i]);
							sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+allFolders[i]);
						}

					}

					String[] sharedFolder = shdPath.split("/");

					for (int i = 0; i < sharedFolder.length; i++) {

						if(i==0){
							if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(sharedFolder[i], 10)) {
								appLog.info("Clicked on CheckBox for Folder: "+sharedFolder[i]);	
								ThreadSleep(2000);
								if (isAlertPresent(driver)) {
									msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if (msg.trim().contains(alertMsg)) {
										appLog.info("Error Message is verified in contact access : "+alertMsg);
									} else {
										appLog.error("Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
										sa.assertTrue(false,"Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
									}
								} else {
									appLog.error("Alert is not present so cannot verify alert message "+alertMsg);
									sa.assertTrue(false, "Alert is not present so cannot verify alert message "+alertMsg);
								}
							} else {
								sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+sharedFolder[i]);
								appLog.error("Not Able to Click on CheckBox for Folder: "+sharedFolder[i]);
							}	
						}else{
							if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(sharedFolder[sharedFolder.length-i], 10)) {
								appLog.info("Clicked on CheckBox for Folder: "+sharedFolder[sharedFolder.length-i]);	
							} else {
								flag=false;
								appLog.error("Not Able to Click on CheckBox for Folder: "+sharedFolder[sharedFolder.length-i]);
								sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+sharedFolder[sharedFolder.length-i]);

							}
						}
					}


					if (flag) {
						appLog.info("Able to Clicked on CheckBox for Sub and Sub-Sub Shared Folder");	
						if (click(driver, ifp.getBulkDownloadDownloadButton(10), "DownLoad Button", action.BOOLEAN)) {

							if (click(driver, ifp.getBulkDownloadConfirmationPopUpYesBtn(30),
									"Bulk Download Yes Button", action.BOOLEAN)) {
								appLog.error("Clicked on Yes Button.");

								Robot rob;
								try {
									rob = new Robot();
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
								} catch (AWTException e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
								ThreadSleep(20000);
								driver.close();
								driver.switchTo().window(parentID);
								// ele.sendKeys(Keys.CONTROL+"j");
								parentID = switchOnWindow(driver);
								if (parentID == null) {
									try {
										rob = new Robot();
										rob.keyPress(KeyEvent.VK_TAB);
										rob.keyRelease(KeyEvent.VK_TAB);
										rob.keyPress(KeyEvent.VK_ENTER);
										rob.keyRelease(KeyEvent.VK_ENTER);
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_CONTROL);
										rob.keyPress(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_CONTROL);
										parentID = switchOnWindow(driver);
									} catch (AWTException e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}
								}

								ThreadSleep(5000);
								String fileName = ifp.getDownloadedFileName();
								if (fileName.contains(M17FundName1 + " " + getSystemDate("MM-dd-YYYY"))) {
									appLog.info("file name is successfully matched. " + fileName);
								} else {
									appLog.info("File name is not the matched. Expected: " + M17FundName1 + " "
											+ getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
									sa.assertTrue(false, "File name is not the matched. Expected: " + M17FundName1
											+ " " + getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
								}
								driver.close();
								driver.switchTo().window(parentID);

							} else {
								appLog.error("Not Able to Click on Yes Button.");
								sa.assertTrue(false, "Not Able to Click on Yes Button.");
							}


						} else {
							appLog.error("Download Button is not working.");
							sa.assertTrue(false, "Download Button is not working.");
						}


					} else {
						appLog.error("Not Able to Clicked on CheckBox for Sub and Sub-Sub Shared Folder So Skipping Download Functionality");
						sa.assertTrue(false, "Not Able to Clicked on CheckBox for Sub and Sub-Sub Shared Folder So Skipping Download Functionality");

					}	


				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}


			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}


		}else{
			appLog.error("Not Able to Click on "+investorSideWorkSpace.CurrentInvestment);
			sa.assertTrue(false, "Not Able to Click on "+investorSideWorkSpace.CurrentInvestment);	
		}



		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc073_1_ChangePermissionToSharedFolderTheInvitedContactINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String[] shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath).split(",");
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");
				for (int i = shdPath.length-2; i >= 0; i--) {

					if (fp.verifyFolderPathdummy(shdPath[i], null, null, null, PageName.FundsPage,Workspace.InvestorWorkspace,60)) {
						appLog.info("Folder structure verified : "+shdPath);
						if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
							ThreadSleep(2000);
							if (click(driver, fp.getContactViewOrUploadOrDownloadCheckBox(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, 5, CheckBoxName.Download),"DownLoad Check Box",action.BOOLEAN)) {
								appLog.info("Click on DownLoad CheckBox");
								if(click(driver, fp.getApplyBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" apply button", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on apply button successfully");
									if(click(driver, fp.getCloseBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" close button", action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on Close Buton");	
									}else {
										appLog.error("Not able to click on close button");
										sa.assertTrue(false, "Not able to click on close button");
									}
								}else{
									appLog.error("Not able to Click on apply button");
									sa.assertTrue(false, "Not able to Click on apply button")	;
								}
							} else {
								appLog.error("Not able to click on DownLoad Documents CheckBox for Contact : "+M17Contact1EmailId);
								sa.assertTrue(false, "Not able to click on DownLoad Documents CheckBox  for Contact : "+M17Contact1EmailId);
							}
						}else {
							appLog.error("Not able to click on contact access icon on folder "+shdPath[i]);
							sa.assertTrue(false, "Not able to click on contact access icon on folder "+shdPath[i]);
						}

					}else {
						appLog.error("Not able to click on folder structure "+shdPath[i]);
						sa.assertTrue(false, "Not able to click on folder structure "+shdPath[i]);
					}

				}


			}else {
				appLog.error("Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
				sa.assertTrue(false, "Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
			}

		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
		}
		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");

				for (int i = 0; i < shdPath.length; i++) {

					if (fp.verifyFolderPathdummy(shdPath[i], null, null, M17FundName1, PageName.ContactsPage,
							Workspace.InvestorWorkspace, 60)) {

						if ( cp.getViewAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
							appLog.info("View access icon is Enabled for : "+shdPath[i]);
						}
						else {
							appLog.error("View access icon is Should be Enabled for : "+shdPath[i]);
							sa.assertTrue(false,"View access icon is Should be Enabled for : "+shdPath[i]);
						}

						if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
							appLog.info("Download access icon is Enabled for : "+shdPath[i]);
						}
						else {
							appLog.error("Download access icon is Should be Enabled for : "+shdPath[i]);
							sa.assertTrue(false,"Download access icon is Should be Enabled for : "+shdPath[i]);
						}

					}
					else {
						appLog.error("folder path is not found on contacts page folder structure : "+shdPath);
						sa.assertTrue(false, "folder path is not found on contacts page folder structure : "+shdPath);
					}


				}


			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc073_2_VerifyFolderStructureInInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {

			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");

				String parentID = switchOnWindow(driver);

				if (parentID!=null) {


					if (ifp.verifyBulkDownLoadFolderStructure(cmnPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + cmnPath);

						if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(cmnPath, 10)) {
							appLog.info("Clicked on CheckBox for Folder: "+cmnPath);		
						}else{
							appLog.error("Not Able to Click on CheckBox for Folder: "+cmnPath);
							sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+cmnPath);	
						}

					}else {
						appLog.error("could not find folder structure on Bulk DownLoad: "+cmnPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+cmnPath);
					}

					if (ifp.verifyBulkDownLoadFolderStructure(stdPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + stdPath);

						if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(stdPath, 10)) {
							appLog.info("Clicked on CheckBox for Folder: "+stdPath);		
						}else{
							appLog.error("Not Able to Click on CheckBox for Folder: "+stdPath);
							sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+stdPath);	
						}

					}else {
						appLog.error("could not find folder structure on Bulk DownLoad: "+cmnPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad: "+cmnPath);
					}


					if (ifp.verifyBulkDownLoadFolderStructure(shdPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + shdPath);

						String[] folders = shdPath.split("/");

						for (int i=folders.length-1;i>=0;i--) {
							if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(folders[i], 10)) {
								appLog.info("Clicked on CheckBox for Folder: "+folders[i]);		
							}else{
								appLog.error("Not Able to Click on CheckBox for Folder: "+folders[i]);
								sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+folders[i]);	
							}	
						}


					}else {
						appLog.error("could not find folder structure on Bulk DownLoad"+shdPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+shdPath);
					}

					if (click(driver, ifp.getBulkDownloadDownloadButton(10), "DownLoad Button", action.BOOLEAN)) {


						if (click(driver, ifp.getBulkDownloadConfirmationPopUpYesBtn(30),
								"Bulk Download Yes Button", action.BOOLEAN)) {
							appLog.error("Clicked on Yes Button.");


							Robot rob;
							try {
								rob = new Robot();
								ThreadSleep(10000);
								rob.keyPress(KeyEvent.VK_ENTER);
								rob.keyRelease(KeyEvent.VK_ENTER);
								ThreadSleep(10000);
								rob.keyPress(KeyEvent.VK_CONTROL);
								rob.keyPress(KeyEvent.VK_J);
								rob.keyRelease(KeyEvent.VK_J);
								rob.keyRelease(KeyEvent.VK_CONTROL);
							} catch (AWTException e) {
								// TODO Auto-generated catch
								// block
								e.printStackTrace();
							}
							ThreadSleep(20000);
							driver.close();
							driver.switchTo().window(parentID);
							// ele.sendKeys(Keys.CONTROL+"j");
							parentID = switchOnWindow(driver);
							if (parentID == null) {
								try {
									rob = new Robot();
									rob.keyPress(KeyEvent.VK_TAB);
									rob.keyRelease(KeyEvent.VK_TAB);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
									parentID = switchOnWindow(driver);
								} catch (AWTException e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
							}

							ThreadSleep(5000);
							String fileName = ifp.getDownloadedFileName();
							if (fileName.contains(M17FundName1 + " " + getSystemDate("MM-dd-YYYY"))) {
								appLog.info("file name is successfully matched. " + fileName);
							} else {
								appLog.info("File name is not the matched. Expected: " + M17FundName1 + " "
										+ getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
								sa.assertTrue(false, "File name is not the matched. Expected: " + M17FundName1
										+ " " + getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
							}
							driver.close();
							driver.switchTo().window(parentID);

						} else {
							appLog.error("Not Able to Click on Yes Button.");
							sa.assertTrue(false, "Not Able to Click on Yes Button.");
						}


					} else {
						appLog.error("Download Button is not working.");
						sa.assertTrue(false, "Download Button is not working.");
					}


				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}


			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}


		}else {
			appLog.error("Not able to click on "+investorSideWorkSpace.CurrentInvestment);
			sa.assertTrue(false, "Not able to click on "+investorSideWorkSpace.CurrentInvestment);
		}
		switchToDefaultContent(driver);
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc074_1_RemoveTheAccessFromInvitedParentFolderActionCRMSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,  adminPassword);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String shdFolderafterRemovel = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.KeyWord_For_Search);
		String shdFolders = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String[] shdPath = shdFolders.split(",");
		String[] allFolder = (cmnPath+","+shdFolderafterRemovel).split(",");

		WebElement ele;
		String grantedAccessOn=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		String contactFullName=M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName;
		if (cp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "workspace section");
				if (fp.verifyFolderPathdummy(shdPath[0], null, null, null, PageName.FundsPage,Workspace.InvestorWorkspace,60)) {
					appLog.error("folder path verified : "+shdPath[0]);
					if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
						WebElement remove = fp.getcontactaccessremoveLink(Workspace.InvestorWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
						if (remove!=null) {
							if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on remove link for : "+contactFullName);
								ThreadSleep(5000);
								if (click(driver, fp.getRemoveAccessClose(Workspace.InvestorWorkspace, 30), "remove access close button", action.BOOLEAN)) {
									appLog.error("Clicked on remove access close button");	

									ele=fp.noDataToDisplayContactAccess(Workspace.InvestorWorkspace, 60);
									if (ele!=null) {
										String	msg =ele.getText().trim();
										if(msg.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
											appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message Verified");
										}else {
											appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
											sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
										}
									} else {
										appLog.error("No Data msg element is null");
										sa.assertTrue(false, "No Data msg element is null");	
									}

									if (click(driver, fp.getContactAccessCrossIcon(Workspace.InvestorWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on Contact access Cross Icon");
									} else {
										appLog.error("Contact access Cross Icon is not clickable");
										sa.assertTrue(false, "Contact access close button is not clickable");
									}


									// Verify SubFolder after Removing Access From Parent Folder

									if (fp.verifyFolderPathdummy(shdPath[1], null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
										appLog.info("Folder Structure Verified  "+shdPath[1]);
										if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on Contact Access Icon");	


											appLog.info("Going to Verify "+contactFullName+" with Remove Disabled Link");
											if (!fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, EnableDisable.Disable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
												appLog.info("Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is not displaying and Hence Verified");

											} else {
												appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is displaying and Hence Not Verified");
												sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Icon Disabled is displaying and Hence Not Verified");

											}
											appLog.info("Going to Verify "+contactFullName+" with Remove Enabled Link");
											if (fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, EnableDisable.Enable, contactFullName, grantedAccessOn, M17Contact1EmailId, M17Contact1UpdatedFirmName, 10)) {
												appLog.error("Contact Grid Information related to "+contactFullName+ " with Reove Icon Enable is Verified");

											} else {
												appLog.error("Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");
												sa.assertTrue(false, "Contact Grid Information related to "+contactFullName+ " with Remove Link Enable is not Verified");

											}

											if (click(driver, fp.getCrossIconOnContactAccess(Workspace.InvestorWorkspace, 10), "Cross Icon", action.SCROLLANDBOOLEAN)) {
												appLog.info("clicked on Cross Icon");	
											} else {
												appLog.error("Not able to click on Cross Icon");
												sa.assertTrue(false, "Not able to click on Cross Icon");	
											}

										}else {
											appLog.error("Not able to click on Contact Access Icon");
											sa.assertTrue(false, "Not able to click on Contact Access Icon");	
										}
									}else {
										appLog.error("Folder Structure Not Verified  "+shdPath[1]);
										sa.assertTrue(false, "Folder Structure Not Verified  "+shdPath[1]);
									}

								}
								else {
									appLog.error("remove access close button is not clickable");
									sa.assertTrue(false, "remove access close button is not clickable");
								}

							}else {
								appLog.error("Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
								sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName);
							}
						}
						else {
							appLog.error("remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
							sa.assertTrue(false, "remove button adjacent to "+M17Contact1UpdatedFirstName+" "+M17Contact1UpdatedLastName+" is not found");
						}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}
				}
				else {
					appLog.error("folder path is not found : "+shdPath[0]);
					sa.assertTrue(false, "folder path is not found : "+shdPath[0]);
				}
			}
			else {
				appLog.error(M17FundName1+" is not found on funds page");
				sa.assertTrue(false, M17FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		switchToDefaultContent(driver);
			if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");

				for (int i=0;i<allFolder.length;i++) {

					if (fp.verifyFolderPathdummy(allFolder[i], null, null, M17FundName1, PageName.ContactsPage,
							Workspace.InvestorWorkspace, 60)) {

						if ( cp.getViewAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
							appLog.info("View access icon is Enabled for : "+allFolder[i]);
						}
						else {
							appLog.error("View access icon is Should be Enabled for : "+allFolder[i]);
							sa.assertTrue(false,"View access icon is Should be Enabled for : "+allFolder[i]);
						}

						if (i==0) {
						
							if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
								appLog.info("Download access icon is Enabled for : "+allFolder[i]);
							}
							else {
								appLog.error("Download access icon is Should be Enabled for : "+allFolder[i]);
								sa.assertTrue(false,"Download access icon is Should be Enabled for : "+allFolder[i]);
							}
							
						} else {
							if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 5)==null) {
								appLog.info("Download access icon is Disable for : "+allFolder[i]);
							}
							else {
								appLog.error("Download access icon is Should be Disable for : "+allFolder[i]);
								sa.assertTrue(false,"Download access icon is Should be Disable for : "+allFolder[i]);
							}
						}
						
					}
					else {
						appLog.error("common path is not found on contacts page folder structure : "+allFolder[i]);
						sa.assertTrue(false, "common path is not found on contacts page folder structure : "+allFolder[i]);
					}

				}

			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc074_2_VerifyFolderStructureInInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String[] allFolders=(cmnPath+","+shdPath).split(",");
		String parentID;
		String alertMsg=InvestorFirmPageErrorMessage.LimitedAccessErrorMessageBulkDownload;
		String msg ;

		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {

			for (int i = 0; i < allFolders.length; i++) {

				if (fp.verifyFolderPathdummy(allFolders[i], null, null, null, PageName.CurrentInvestmentPgae,
						null, 20)) {
					appLog.info(" Folder Structure Verified: " + allFolders[i]);

				}else {
					appLog.error("could not find folder structure "+allFolders[i]);
					sa.assertTrue(false, "could not find folder structure "+allFolders[i]);
				}

			}

			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");

				parentID=switchOnWindow(driver);

				if (parentID!=null) {

					for (int i = 0; i < allFolders.length; i++) {

						if (ifp.verifyBulkDownLoadFolderStructure(allFolders[i], 20)) {
							appLog.info(" Folder Structure Verified on Bulk DownLoad: " + allFolders[i]);
						}else {
							appLog.error("could not find folder structure on Bulk DownLoad"+allFolders[i]);
							sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+allFolders[i]);
						}

					}

					String[] sharedFolder = shdPath.split("/");

					for (int i = 0; i < sharedFolder.length; i++) {

						if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(sharedFolder[i], 10)) {
							appLog.info("Clicked on CheckBox for Folder: "+sharedFolder[i]);	
							ThreadSleep(2000);
							if (isAlertPresent(driver)) {
								msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								if (msg.trim().contains(alertMsg)) {
									appLog.info("Error Message is verified in contact access : "+alertMsg);
								} else {
									appLog.error("Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
									sa.assertTrue(false,"Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
								}
							} else {
								appLog.error("Alert is not present so cannot verify alert message "+alertMsg);
								sa.assertTrue(false, "Alert is not present so cannot verify alert message "+alertMsg);
							}
						} else {
							sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+sharedFolder[i]);
							appLog.error("Not Able to Click on CheckBox for Folder: "+sharedFolder[i]);
						}	

					}

					driver.close();
					driver.switchTo().window(parentID);

				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}


			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}


		}else{
			appLog.error("Not Able to Click on "+investorSideWorkSpace.CurrentInvestment);
			sa.assertTrue(false, "Not Able to Click on "+investorSideWorkSpace.CurrentInvestment);	
		}

		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc075_1_InviteContactFromSharedParentFolderAndRemoveAccessFromSubSharedFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String[] shdPath=ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath).split(",");
		String errormsg=FundsPageErrorMessage.alreadyInvitedContactAlertMsg;
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {

				if(fp.inviteContact(environment,mode,null, M17Contact1EmailId,shdPath[0], FolderType.Shared, "Download", "Yes", null,null, Workspace.InvestorWorkspace, null)) {
					appLog.info(M17Contact1EmailId+" Contact is invited successfully from folder "+shdPath[0]);
				}else {
					appLog.error("Not able to invite contact from parent folder "+shdPath[0]);
					sa.assertTrue(false, "Not able to invite contact from parent folder "+shdPath[0]);
				}

				switchToFrame(driver, 30, cp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Fund page investor workspace section");

				if (fp.verifyFolderPathdummy(shdPath[1], null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("Folder Structure Verified  "+shdPath[1]);
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on Contact Access Icon");	
						WebElement remove = fp.getcontactaccessremoveLink(Workspace.InvestorWorkspace, EnableDisable.Enable, M17Contact1EmailId, 10);
						if (remove!=null) {
							if(click(driver, remove, "remove link", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on remove link");
								if (click(driver, fp.getRemoveAccessClose(Workspace.InvestorWorkspace, 30), "remove access close button", action.BOOLEAN)) {
									appLog.info("Clicked on remove access close button");
									ThreadSleep(3000);

									if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
										ThreadSleep(3000);
										if(!clickUsingJavaScript(driver, fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20), "check box")) {
//										if(click(driver, fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, M17Contact1FirstName+" "+M17Contact1LastName, 20), "Check Box", action.SCROLLANDBOOLEAN)) {
											ThreadSleep(2000);
											if (isAlertPresent(driver)) {
												String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
												switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
												if (msg.trim().equals(errormsg)) {
													appLog.info("Error Message is verified in contact access : "+errormsg);
												} else {
													appLog.error("Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+errormsg);
													sa.assertTrue(false,"Error Message is not verified in contact access Actual : "+msg+" \t Expected : "+errormsg);
												}

											} else {
												appLog.error("Alert is not present so cannot verify alert message "+errormsg);
												sa.assertTrue(false, "Alert is not present so cannot verify alert message "+errormsg);
											}
											if(click(driver, fp.getContactAccessCancelBtn(Workspace.InvestorWorkspace, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
												appLog.info("click on contact access pop up cancel button");
											}else {
												appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
												sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
											}
										}else {
											appLog.error("Not able to click on Contact Name "+M17Contact1FirstName+" "+M17Contact1LastName+" so cannot check error message "+errormsg);
											sa.assertTrue(false, "Not able to click on Contact Name "+M17Contact1FirstName+" "+M17Contact1LastName+" so cannot check error message "+errormsg);
										}

									}else {
										appLog.error("Not able to expand contact access pop up so cannot verify error message "+errormsg);
										sa.assertTrue(false, "Not able to expand contact access pop up so cannot verify error message "+errormsg);
									}

								}
								else {
									appLog.error("remove access close button is not clickable");
									sa.assertTrue(false, "remove access close button is not clickable");
								}
							}else {
								appLog.error("Not able to click on remove link of contact: "+M17Contact1EmailId);
								sa.assertTrue(false, "Not able to click on remove link of contact: "+M17Contact1EmailId);
							}
						}
						else {
							appLog.error("remove button adjacent to "+M17Contact1EmailId+" is not found");
							sa.assertTrue(false, "remove button adjacent to "+M17Contact1EmailId+" is not found");
						}
					}else {
						appLog.error("Not able to click on Contact Access Icon");
						sa.assertTrue(false, "Not able to click on Contact Access Icon");	
					}

				}else {
					appLog.error("Folder Structure Not Verified  "+shdPath[1]);
					sa.assertTrue(false, "Folder Structure Not Verified  "+shdPath[1]);
				}

			}else {
				appLog.error("Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
				sa.assertTrue(false, "Not able to click on created fund "+M17FundName1+" so cannot invite contact from parent and remove access from child folder");
			}

		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact from parent and remove access from child folder");
		}

		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");
				for (int i=0;i<shdPath.length;i++) {

					if (fp.verifyFolderPathdummy(shdPath[i], null, null, M17FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 60)) {

						if ( cp.getViewAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
							appLog.info("View access icon is Enabled for : "+shdPath[i]);
						}
						else {
							appLog.error("View access icon is Should be Enabled for : "+shdPath[i]);
							sa.assertTrue(false,"View access icon is Should be Enabled for : "+shdPath[i]);
						}

						if ( cp.getDownloadAccessIcon(Workspace.InvestorWorkspace, 5)!=null) {
							appLog.info("Download access icon is Enabled for : "+shdPath[i]);
						}
						else {
							appLog.error("Download access icon is Should be Enabled for : "+shdPath[i]);
							sa.assertTrue(false,"Download access icon is Should be Enabled for : "+shdPath[i]);
						}

						if ( cp.getUploadAccessIcon(Workspace.InvestorWorkspace, 1)==null) {
							appLog.info("Upload access icon is Disabled for : "+shdPath[i]);
						}
						else {
							appLog.error("Upload access icon is Should be Disabled for : "+shdPath[i]);
							sa.assertTrue(false,"Upload access icon is Should be Disabled for : "+shdPath[i]);
						}

					}

					else {
						appLog.error("folder path is not found on contacts page folder structure : "+shdPath[i]);
						sa.assertTrue(false, "folder path is not found on contacts page folder structure : "+shdPath[i]);
					}
				}
			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact1FirstName+" "+ M17Contact1LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc075_2_VerifyFolderStructureInInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {

			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");

				String parentID = switchOnWindow(driver);

				if (parentID!=null) {

					if (ifp.verifyBulkDownLoadFolderStructure(shdPath, 20)) {
						appLog.info(" Folder Structure Verified on Bulk DownLoad: " + shdPath);

						if (ifp.clickCheckBoxForFolderonBulkDownloadWindow(shdPath, 10)) {
							appLog.info("Clicked on CheckBox for Folder: "+shdPath);	


							if (click(driver, ifp.getBulkDownloadDownloadButton(10), "DownLoad Button", action.BOOLEAN)) {


								if (click(driver, ifp.getBulkDownloadConfirmationPopUpYesBtn(30),
										"Bulk Download Yes Button", action.BOOLEAN)) {
									appLog.error("Clicked on Yes Button.");


									Robot rob;
									try {
										rob = new Robot();
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_ENTER);
										rob.keyRelease(KeyEvent.VK_ENTER);
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_CONTROL);
										rob.keyPress(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_CONTROL);
									} catch (AWTException e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}
									ThreadSleep(20000);
									driver.close();
									driver.switchTo().window(parentID);
									// ele.sendKeys(Keys.CONTROL+"j");
									parentID = switchOnWindow(driver);
									if (parentID == null) {
										try {
											rob = new Robot();
											rob.keyPress(KeyEvent.VK_TAB);
											rob.keyRelease(KeyEvent.VK_TAB);
											rob.keyPress(KeyEvent.VK_ENTER);
											rob.keyRelease(KeyEvent.VK_ENTER);
											ThreadSleep(10000);
											rob.keyPress(KeyEvent.VK_CONTROL);
											rob.keyPress(KeyEvent.VK_J);
											rob.keyRelease(KeyEvent.VK_J);
											rob.keyRelease(KeyEvent.VK_CONTROL);
											parentID = switchOnWindow(driver);
										} catch (AWTException e) {
											// TODO Auto-generated catch
											// block
											e.printStackTrace();
										}
									}

									ThreadSleep(5000);
									String fileName = ifp.getDownloadedFileName();
									if (fileName.contains(M17FundName1 + " " + getSystemDate("MM-dd-YYYY"))) {
										appLog.info("file name is successfully matched. " + fileName);
									} else {
										appLog.info("File name is not the matched. Expected: " + M17FundName1 + " "
												+ getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
										sa.assertTrue(false, "File name is not the matched. Expected: " + M17FundName1
												+ " " + getSystemDate("MM-dd-YYYY") + " Actual: " + fileName);
									}
									driver.close();
									driver.switchTo().window(parentID);

								} else {
									appLog.error("Not Able to Click on Yes Button.");
									sa.assertTrue(false, "Not Able to Click on Yes Button.");
								}


							} else {
								appLog.error("Download Button is not working.");
								sa.assertTrue(false, "Download Button is not working.");
							}


						}else{
							appLog.error("Not Able to Click on CheckBox for Folder: "+shdPath);
							sa.assertTrue(false, "Not Able to Click on CheckBox for Folder: "+shdPath);	
						}	



					}else {
						appLog.error("could not find folder structure on Bulk DownLoad"+shdPath);
						sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+shdPath);
					}


				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}


			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}


		}else {
			appLog.error("Not able to click on "+investorSideWorkSpace.CurrentInvestment);
			sa.assertTrue(false, "Not able to click on "+investorSideWorkSpace.CurrentInvestment);
		}
		switchToDefaultContent(driver);
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc076_1_RemoveContactAccessActionFromContactDetailPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String shdPath=ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		String	msg;
		String errorMsg=ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace;
		WebElement ele = null;
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact1FirstName, M17Contact1LastName, null)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
						"Fundraising Workspace Section view");
				if (click(driver, cp.getRemoveContactAccessButton(Workspace.InvestorWorkspace, 60),
						"Remove contact access close button", action.SCROLLANDBOOLEAN)) {
					ele = FindElement(driver, "//label[text()='" + M17FundName1 + "']/../..//a[@title='Remove']",M17FundName1+" Remove link", action.SCROLLANDBOOLEAN, 60);
					if (click(driver, ele, "Remove Link", action.SCROLLANDBOOLEAN)) {
						String ParentID = switchOnWindow(driver);
						if (ParentID != null) {
							ThreadSleep(5000);
							switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
							driver.switchTo().window(ParentID);
							ThreadSleep(2000);
							refresh(driver);
							ThreadSleep(2000);
							switchToFrame(driver, 30, bp.getFrame( PageName.ContactsPage, 30));
							scrollDownThroughWebelement(driver,bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),Workspace.InvestorWorkspace+" Workspace Section view");
							ele=cp.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60);
							msg=ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace;
							
							if (ele!=null) {
								msg = ele.getText().trim();
								
								if (errorMsg.equals(msg)) {
								appLog.info(msg+" on contatct Page Verified ");	
								} else {
									appLog.error("Error Msg on contatct Page Not Verified Actual : "+msg+"\t Expected : "+errorMsg);	
									sa.assertTrue(false, "Error Msg on contatct Page Not Verified Actual : "+msg+"\t Expected : "+errorMsg);
								}
								
							} else {
								appLog.error("Contact Page Error Msg Ele is null");
								sa.assertTrue(false, "Contact Page Error Msg Ele is null");
							}
							
						} else {
							appLog.info("No new window to switch");
							sa.assertTrue(false, "No new window to switch");
						}
					} else {
						appLog.info("Not bale ot click on remove link");
						sa.assertTrue(false, "Not able to click on remove link");
					}
				} else {
					appLog.info("Not able to clcik on remove contact access close button");
					sa.assertTrue(false, "Not able to click on remove contact access close button");
				}
			} else {
				appLog.info("Not able to click on created contact : "+M17Contact1FirstName+" "+M17Contact1LastName);
				sa.assertTrue(false, "Not able to click on created contact : "+M17Contact1FirstName+" "+M17Contact1LastName);
			}
		} else {
			appLog.info("Not able to click on contacts tab");
			sa.assertTrue(false, "Not able to click on contacts tab");
		}

		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "workspace section");
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage,Workspace.InvestorWorkspace,60)) {
					appLog.error("folder path verified : "+shdPath);
					if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
						ThreadSleep(3000);

						ele=fp.noDataToDisplayContactAccess(Workspace.InvestorWorkspace, 60);
						if (ele!=null) {
								msg =ele.getText().trim();
							if(msg.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
								appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message Verified");
							}else {
								appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
								sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
							}
						} else {
							appLog.error("No Data msg element is null");
							sa.assertTrue(false, "No Data msg element is null");	
						}

						if (click(driver, fp.getContactAccessCrossIcon(Workspace.InvestorWorkspace, 10), "Contact Access Cross Icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Contact access Cross Icon");
						} else {
							appLog.error("Contact access Cross Icon is not clickable");
							sa.assertTrue(false, "Contact access close button is not clickable");
						}
					}
					else {
						appLog.error("contact access icon is not clickable");
						sa.assertTrue(false, "contact access icon is not clickable");
					}
				}
				else {
					appLog.error("folder path is not found : "+shdPath);
					sa.assertTrue(false, "folder path is not found : "+shdPath);
				}
			}
			else {
				appLog.error(M17FundName1+" is not found on funds page");
				sa.assertTrue(false, M17FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}


		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();

	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc076_2_RemoveContactAccessImpactonInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M17Contact1EmailId, adminPassword);
		
		//Investor Side
		
		List<WebElement> dropdownList=allOptionsInDropDrop(driver, allfp.getFirmNameDropdown(60), "Drop Down List");
		List<String> result=compareMultipleList(driver, Org1FirmName, dropdownList);
		
		if (!result.isEmpty()) {
			appLog.info("Firm Name is not available in DropDown after removing Acces");	
		} else {
			appLog.error("Firm Name is available in DropDown after removing Acces");
			sa.assertTrue(false, "Firm Name is available in DropDown after removing Acces");
		}
		lp.investorLogout();
		sa.assertAll();
		System.err.println("Pass");
		appLog.info("Pass");
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc077_1_buildInvestorWorkSpaceAndInviteContactForFund2(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName2)) {
				String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund2", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund2", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund2", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund2", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund2", excelLabel.Fund_Email);
				String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M17Fund2", excelLabel.Fund_Description);
				String templateName=ExcelUtils.readData("Foldertemp", 0, 1);
				String[] data= {Size,vintageyear,contact,phone,email,description};
				String shdPath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
				
				
				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,null, Workspace.InvestorWorkspace,60)) {

					appLog.info("InvestorWorkspace is build successfully on fund : "+M17FundName2);
					if(fp.inviteContact(environment,mode, null, M17Contact3EmailId,shdPath, FolderType.Shared,null,"Yes", "Yes","All Folders", Workspace.InvestorWorkspace, M17Contact3EmailId)) {
						appLog.info("contact is invites successfully from InvestorWorkspace: "+M17Contact3FirstName+" "+M17Contact3LastName);
					}else {
						appLog.error("Not able to invite contact from InvestorWorkspacee: "+M17Contact3FirstName+" "+M17Contact3LastName);
						sa.assertTrue(false, "Not able to invite contact from InvestorWorkspace: "+M17Contact3FirstName+" "+M17Contact3LastName);
					}
					

				}else {
					appLog.error("Not able to bulid InvestorWorkspace on fund: "+M17FundName2);
					sa.assertTrue(false, "Not able to bulid InvestorWorkspace on fund: "+M17FundName2);
				}
			}else{
				appLog.error("Not able to click on Created Fund: "+M17FundName2);
				sa.assertTrue(false, "Not able to click on Created Fund: "+M17FundName2);	
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName2);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace: "+M17FundName2);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc077_2_registerM17Contact3(){
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String shdPath = ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		String[] allFolders = (cmnPath+","+shdPath).split(",");
		if (ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M17Contact3", excelLabel.Registered).equalsIgnoreCase("Not Registered")) {
			try {
				investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail",gmailUserName,gmailPassword,CRMUser1EmailID,M17Contact3EmailId);
				appLog.info("Investor Registration Link: " + investorRegLink);
			} catch (InterruptedException e) {
				appLog.info("Invitation mail not found.");
				e.printStackTrace();
			}
			if (investorRegLink != null) {
				driver.get(investorRegLink);
				if (bp.targetRegistration(M17Contact3FirstName, M17Contact3LastName, M17Contact3EmailId, M17Institution2,adminPassword)) {
					appLog.info("Investor is registered successfully: " + M17Contact3FirstName + " " + M17Contact3LastName);
					ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M17Contact3", excelLabel.Registered);
				} else {
					driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
					appLog.info("Investor Target URL through Direct URL..");
					if (bp.targetRegistration(M17Contact3FirstName, M17Contact3LastName, M17Contact3EmailId,M17Institution2, adminPassword)) {
						appLog.info("Investor is registered successfully: " + M17Contact3FirstName + " "+ M17Contact3LastName);
						ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M17Contact3", excelLabel.Registered);
					} else {
						appLog.error("Investor is not Registered through Invited Link: " + M17Contact3FirstName + " "+ M17Contact3LastName);
						sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M17Contact3FirstName+ " " + M17Contact3LastName);
					}
				}
			} else {
				driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
				appLog.info("Register Investor URL through Direct URL..");
				if (bp.targetRegistration(M17Contact3FirstName, M17Contact3LastName, M17Contact3EmailId, M17Institution2, adminPassword)) {
					appLog.info("Investor is registered successfully: " + M17Contact3FirstName + " "+ M17Contact3LastName);
					ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M17Contact3", excelLabel.Registered);
				} else {
					appLog.error("Investor is not Registered through Invited Link: " + M17Contact3FirstName + " "+ M17Contact3LastName);
					sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M17Contact3FirstName+ " " + M17Contact3LastName);
				}
			}
			if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {

				for (int i = 0;i<allFolders.length;i++) {
					if (fp.verifyFolderPathdummy(allFolders[i], null, null, null, PageName.CurrentInvestmentPgae,null, 60)) {
						appLog.info(" Folder Structure Verified: " + allFolders[i]);
					}
					else {
						appLog.error("could not find folder structure "+allFolders[i]);
						sa.assertTrue(false, "could not find folder structure "+allFolders[i]);
					}

				}


			}else{
				appLog.error("Not Able to Click on CurrentInvestment");
				sa.assertTrue(false, "Not Able to Click on CurrentInvestment");	
			}
			lp.investorLogout();
		} else {
			appLog.info("investor "+M17Contact3FirstName+" "+M17Contact3LastName+" is already Registered.");
			sa.assertTrue(false, "investor "+M17Contact3FirstName+" "+M17Contact3LastName+" is already Registered.");
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc077_3_LoginWithHubToEnableBulkDownLoadForFund2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(HubUserName,HubPassword);
		String[] funds = {M17FundName2};
		
		for (String fund : funds) {
			if(fp.clickOnTab(TabName.FundsTab)) {
				if(fp.clickOnCreatedFund(fund)) {
					if (click(driver, fp.getEditButton(10), "Edit Button", action.BOOLEAN)) {
						appLog.error("Click on Edit Button : "+fund);
						ThreadSleep(1000);
						if (isSelected(driver, fp.getBulkDownLoadCheckBox(Workspace.InvestorWorkspace, 10), "Bulk DownLoad CheckBox")) {
							appLog.info("Bulk DownLoad is Already Checked for "+fund);	
						}else{
							if (click(driver, fp.getBulkDownLoadCheckBox(Workspace.InvestorWorkspace, 10), "Bulk DownLoad CheckBox", action.SCROLLANDBOOLEAN)) {
								appLog.info(fund+" clicked on Bulk DownLoad CheckBox : "+Workspace.InvestorWorkspace);	
							} else {
								appLog.error(fund+" Not able to click on Bulk DownLoad CheckBox : "+Workspace.InvestorWorkspace);
								sa.assertTrue(false, fund+" Not able to click on Bulk DownLoad CheckBox : "+Workspace.InvestorWorkspace);
							}	
						}
						ThreadSleep(2000);
						if (click(driver, fp.getSaveButton(10), "Save Button", action.SCROLLANDBOOLEAN)) {
							appLog.error("click on Save Button : "+fund);
						} else {
							appLog.error("Not able to click on Save Button : "+fund);
							sa.assertTrue(false, "Not able to click on Save Button : "+fund);
						}
						
					} else {
						appLog.error("Not able to click on Edit Button : "+fund);
						sa.assertTrue(false, "Not able to click on Edit Button : "+fund);
					}
				}else {
					appLog.error("Not able to click on created fund Name : "+fund);
					sa.assertTrue(false, "Not able to click on created fund Name : "+fund);
					
				}
			}else {
				appLog.error("Not able to click on fund tab");
				sa.assertTrue(false, "Not able to click on fund tab");
			}
			switchToDefaultContent(driver);
		
		}
		
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc078_1_UploadFileAtSharedCommonFolderAndRenameSharedCommonFolderName() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);

		String 	date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		SoftAssert saa = new SoftAssert();

		String shdFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.SharedPath);
		String commonFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String[] updatedFolderNames =  ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.KeyWord_For_Search).split(",");

		String[] folders = {shdFolder,commonFolder};
		excelLabel[] excelLabels = {excelLabel.UploadedFileShared,excelLabel.UploadedFileCommon};

		String filesName =null;

		lp.CRMLogin(CRMUser1EmailID, adminPassword);

		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M17FundName2)) {

				String docpath = "UploadFiles\\Module17\\FileFolder";

				for (int i = 0; i < folders.length; i++) {


					if (fp.uploadFile(folders[i], null, docpath, null, UploadFileActions.Upload,Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {

						appLog.info("File is upload successfullly in  "+folders[i]);
						switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 20));
						scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),"Investor workspace view");

						if (click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"FInvestorWorkspace refresh button", action.SCROLLANDBOOLEAN)) {
							filesName = ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC, excelLabels[i]);

							if (filesName != null) {

								saa = verifyContentGridCRMSide(driver, PageName.FundsPage, Workspace.InvestorWorkspace, filesName,
										"Approved", CRMUser1FirstName + " " + CRMUser1LastName,date);
								sa.combineAssertions(saa);

							} else {
								appLog.error("No Files in Excel for Verifying Content Grid in "+folders[i]);
								sa.assertTrue(false, "No Files in Excel for Verifying Content Grid in "+folders[i]);
							}

						} else {

							appLog.error("Not able to click on refresh icon so cannot verify upload documents in "+folders[i]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents in "+folders[i]);
						}

					} else {
						appLog.error("File is not uploaded in " + folders[i]);
						sa.assertTrue(false, "File is not uploaded in " + folders[i]);
					}

					switchToDefaultContent(driver);	

				}

				// Rename Folder 
				switchToDefaultContent(driver);
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),"Investor workspace view");
				if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "Manage folder icon", action.BOOLEAN)){

					for(int i=0; i<folders.length; i++) {
						String id = null;
						id=fp.getCreatedFolderId(folders[i], PageName.ManageFolderPopUp, 20);
						System.err.println("id>>>>>>"+id);
						if(id!=null) {
							if(fp.clickOnRenameFolderButton(id)) {
								if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,20),updatedFolderNames[i].split(" ")[0],folders[i]+" parent rename folder text box", action.BOOLEAN)) {
									if(click(driver,fp.getParentRenameFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20) ,"rename parent folder save button", action.BOOLEAN)) {
										appLog.info("clicked on parent rename folder save button");
										ThreadSleep(2000);
										if(fp.getCreatedFolderId(updatedFolderNames[i].split(" ")[0], PageName.ManageFolderPopUp, 10)!=null) {
											appLog.info(updatedFolderNames[i].split(" ")[0]+" is available in manage folder");

										}else {
											appLog.error(updatedFolderNames[i].split(" ")[0]+" is not available in manage folder");
											sa.assertTrue(false, updatedFolderNames[i].split(" ")[0]+" is not available in manage folder");
										}
									}else {
										appLog.error("Not able to click on folder "+folders[i]+" save button so cannot Update Folder Name");
										sa.assertTrue(false, "Not able to click on folder "+folders[i]+" save button so cannot Update Folder Name");
									}
								}else {
									appLog.error("Not able to pass value in "+folders[i]+" rename folder text box so cannot Update folder Name");
									sa.assertTrue(false, "Not able to pass value in "+folders[i]+" rename folder text box so cannot Update folder Name");
								}

							}else {
								appLog.error("Not able to click on "+folders[i]+" rename icon so cannot update folder Name");
								sa.assertTrue(false, "Not able to click on "+folders[i]+" rename icon so cannot update folder Name");
							}
						}else {
							appLog.error(folders[i]+" folder is not available in manage folder so cannot Update folder Name");
							sa.assertTrue(false, folders[i]+" folder is not available in manage folder so cannot Update folder Name");
						}

					}


					if (click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 60),
							"Manage Folder Close Button", action.SCROLLANDBOOLEAN)) {
						appLog.info("click on manage folder close button");
					} else {
						appLog.error("Not able to click on manage folder close button");
						sa.assertTrue(false, "Not able to click on manage folder folder close button ");
					}


					for (int i = 0; i < updatedFolderNames.length; i++) {
						if (fp.verifyFolderPathdummy(updatedFolderNames[i], null, null, null,PageName.FundsPage, Workspace.InvestorWorkspace, 60)) {
							appLog.info("Folder Structure verified after Update : "+updatedFolderNames[i]);
						} else {
							appLog.error("Folder Structure not verified after Update : "+updatedFolderNames[i]);
							sa.assertTrue(false, "Folder Structure not verified after Update : "+updatedFolderNames[i]);
						}

					}


				}else {
					appLog.error("Not able to click on Manage folder Icon so cannot check error message in fundraising workspace");
					sa.assertTrue(false, "Not able to click on Manage folder Icon so cannot check error message in fundraising workspace");
				}


			} else {
				appLog.info("Not Able to Click Fund or Fud Not Available : " + M17FundName2);
				sa.assertTrue(false, "Not Able to Click Fund or Fud Not Available : " + M17FundName2);
			}
		} else {
			appLog.info("Not Able to Click Funds Tab");
			sa.assertTrue(false, "Not Able to Click Funds Tab");
		}


		switchToDefaultContent(driver);
		if (cp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M17Contact3FirstName, M17Contact3LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "contact page investor workspace section");

				for (int i=0;i<updatedFolderNames.length;i++) {

					if (fp.verifyFolderPathdummy(updatedFolderNames[i], null, null, M17FundName2, PageName.ContactsPage,
							Workspace.InvestorWorkspace, 60)) {
						appLog.info(" path verified on contacts page folder structure : "+updatedFolderNames[i]);
					}
					else {
						appLog.error(" path is not found on contacts page folder structure : "+updatedFolderNames[i]);
						sa.assertTrue(false, " path is not found on contacts page folder structure : "+updatedFolderNames[i]);
					}

				}

			}
			else {
				appLog.error("contact not found on contacts page "+M17Contact3FirstName+" "+ M17Contact3LastName);
				sa.assertTrue(false, "contact not found on contacts page "+M17Contact3FirstName+" "+M17Contact3LastName);
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}

		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc078_2_VerifyFolderStructureInInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.CommonPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String[] allFolders=(cmnPath+","+shdPath).split(",");
		String parentID;

		lp.investorLogin(M17Contact3EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {

			for (int i = 0; i < allFolders.length; i++) {

				if (fp.verifyFolderPathdummy(allFolders[i], null, null, null, PageName.CurrentInvestmentPgae,
						null, 20)) {
					appLog.info(" Folder Structure Verified: " + allFolders[i]);

				}else {
					appLog.error("could not find folder structure "+allFolders[i]);
					sa.assertTrue(false, "could not find folder structure "+allFolders[i]);
				}

			}

			if (click(driver, ifp.getBulkDownloadIcon(10), "Bulk DownLoad Icon", action.BOOLEAN)) {
				appLog.info("Clicked on Bulk Download");

				parentID=switchOnWindow(driver);

				if (parentID!=null) {

					for (int i = 0; i < allFolders.length; i++) {

						if (ifp.verifyBulkDownLoadFolderStructure(allFolders[i], 20)) {
							appLog.info(" Folder Structure Verified on Bulk DownLoad: " + allFolders[i]);
						}else {
							appLog.error("could not find folder structure on Bulk DownLoad"+allFolders[i]);
							sa.assertTrue(false, "could not find folder structure on Bulk DownLoad"+allFolders[i]);
						}

					}
					
					driver.close();
					driver.switchTo().window(parentID);

				} else {
					appLog.error("No New Window is open after clicking on Bulk DownLoad Icon");
					sa.assertTrue(false, "No New Window is open after clicking on Bulk DownLoad Icon");
				}


			} else {
				appLog.error("Not Able to Click Bulk Download.");
				sa.assertTrue(false, "Not Able to Click Bulk Download.");
			}


		}else{
			appLog.error("Not Able to Click on "+investorSideWorkSpace.CurrentInvestment);
			sa.assertTrue(false, "Not Able to Click on "+investorSideWorkSpace.CurrentInvestment);	
		}

		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc079_CheckFunctionalityofsearchtextfieldonContactAccessInvestorWorkSpace(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		WebElement ele=null;
		String msg;
		String alertMsg = FundsPageErrorMessage.PleaseEnterValueErrorMessage;
		String emptyString;
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy("", M17Institution2, "", null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "investor contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
						if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
							// 1st Assertion
							
							if(!clickUsingJavaScript(driver, fp.getSearchBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" search button")) {
//							if(click(driver, fp.getSearchBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" search button", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on  "+Workspace.InvestorWorkspace+" search button");
								ThreadSleep(2000);
								if (isAlertPresent(driver)) {
									msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if (msg.trim().contains(alertMsg)) {
										appLog.info("Error Message is verified in contact access : "+alertMsg);
									} else {
										appLog.error("Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
										sa.assertTrue(false,"Error Message is verified in contact access Actual : "+msg+" \t Expected : "+alertMsg);
									}
								} else {
									appLog.error("Alert is not present so cannot verify alert message "+alertMsg);
									sa.assertTrue(false, "Alert is not present so cannot verify alert message "+alertMsg);
								}
							}else {
								appLog.error("Not Able to click on Search Icon");
								sa.assertTrue(false, "Not Able to click on Search Icon");
							}
							// 2nd Assertion
							String value ="vvvvvvvvvv";
							for (int i = 0; i < 3; i++) {
								if(sendKeys(driver,fp.getSearchTextBox(Workspace.InvestorWorkspace, 60), value,Workspace.InvestorWorkspace+" search text box", action.SCROLLANDBOOLEAN)) {
									appLog.info("enter the value in search text box : "+value);
									ele = fp.getClearSearchIcon(Workspace.InvestorWorkspace, EnableDisable.Enable, 10);
									if (ele!=null) {
										appLog.info("Clear Icon Active");	
									} else {
										appLog.error("Clear Icon not Active");
										sa.assertTrue(false, "Clear Icon not Active");
									}
									if (i==0) {
										for (int j = 0; j < value.length(); j++) {

											Robot rob;
											try {
												rob = new Robot();
												ThreadSleep(200);
												rob.keyPress(KeyEvent.VK_BACK_SPACE);
												System.err.println("Back Space  pRESS");
												ThreadSleep(200);
												rob.keyRelease(KeyEvent.VK_BACK_SPACE);
												System.err.println("Back Space rELAESE");

											} catch (AWTException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										}
									} else if(i==1) {
										if (click(driver, fp.getClearIconOnContactAccessSearchBox(Workspace.InvestorWorkspace, 10), "Clear Icon", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on Cross Icon");
										} else {
											appLog.error("Not Able to Click on Cross Icon");
											sa.assertTrue(false, "Not Able to Click on Cross Icon");
										}
									}else{
										// 4TH aSSERATION
										if(click(driver, fp.getSearchBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" search button", action.SCROLLANDBOOLEAN)) {
											ThreadSleep(3000);
											ele=fp.noDataToDisplayContactAccessTopGrid(Workspace.InvestorWorkspace, 60);
											if (ele!=null) {
												msg =ele.getText().trim();
												if(msg.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
													appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message Verified");
												}else {
													appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
													sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
												}
											} else {
												appLog.error("No Data msg element is null");
												sa.assertTrue(false, "No Data msg element is null");	
											}
										}else {
											appLog.error("Not Able to click on Search Icon");
											sa.assertTrue(false, "Not Able to click on Search Icon");
										}
									}
									if (i==0 || i==1) {
										emptyString = getValueFromElementUsingJavaScript(driver, fp.getSearchTextBox(Workspace.InvestorWorkspace, 60), "Search Box");
										if(emptyString.isEmpty()) {
											appLog.info("Search Box is Empty for iteration : "+i);
										} else {
											appLog.info(i+" Search Box is not Empty with Value : "+emptyString);
											sa.assertTrue(false, i+" Search Box is not Empty with Value : "+emptyString);
										}
									}
								}else {
									appLog.error("Not Able to enter the value in search text box : "+value);
									sa.assertTrue(false, "Not Able to enter the value in search text box : "+value);
								}
							}
							// 5th 
							String[][] contactInfo = {{M17Contact2FirstName,M17Contact2FirstName+" "+M17Contact2LastName,M17Contact2EmailId,M17Institution1},
									{M17Contact3LastName,M17Contact3FirstName+" "+M17Contact3LastName,M17Contact3EmailId,M17Institution2},
									{M17Institution3,M17Contact5FirstName+" "+M17Contact5LastName,M17Contact5EmailId,M17Institution3},
									{M17Contact1FirstName,M17Contact1FirstName+" "+M17Contact1LastName,M17Contact1EmailId,M17Institution1},
									{"~~~!#!~@#$%^&!@#$%^&*()$%^&*()_+&*()_+<><>:\"\":\"\"??,.,.//;;'';[][\\{}|}{+-=[]"}};

							for (int i = 0; i < contactInfo.length; i++) {
								if(sendKeys(driver,fp.getSearchTextBox(Workspace.InvestorWorkspace, 60), contactInfo[i][0],Workspace.InvestorWorkspace+" search text box", action.SCROLLANDBOOLEAN)) {
									appLog.info("enter the value in search text box : "+contactInfo[i][0]);
									ThreadSleep(1000);
									if(click(driver, fp.getSearchBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" search button", action.SCROLLANDBOOLEAN)) {
										ThreadSleep(2000);

										if (i==contactInfo.length-2) {
											ele = fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, M17Contact3FirstName+" "+M17Contact3LastName, M17Contact3EmailId, M17Institution2, 10);
											if (ele!=null) {
												appLog.info("For Search Value : "+contactInfo[i][0]+" >>> Select contact grid verified with value : "+M17Contact3FirstName+" "+M17Contact3LastName+"\t"+M17Contact3EmailId+"\t"+M17Institution2);
											} else {
												appLog.error("For Search Value : "+contactInfo[i][0]+" >>> Select contact grid Not verified with value : "+M17Contact3FirstName+" "+M17Contact3LastName+"\t"+M17Contact3EmailId+"\t"+M17Institution2);
												sa.assertTrue(false, "For Search Value : "+contactInfo[i][0]+" >>> Select contact Not grid verified with value : "+M17Contact3FirstName+" "+M17Contact3LastName+"\t"+M17Contact3EmailId+"\t"+M17Institution2);
											}
										} else if (i==contactInfo.length-1){

											ele=fp.noDataToDisplayContactAccessTopGrid(Workspace.InvestorWorkspace, 60);
											if (ele!=null) {
												msg =ele.getText().trim();
												if(msg.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
													appLog.info(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message Verified");
												}else {
													appLog.error(FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
													sa.assertTrue(false, FundsPageErrorMessage.noDataToDisplayErrorMessage+" error message is not Verified Actual : "+msg);
												}
											} else {
												appLog.error("No Data msg element is null");
												sa.assertTrue(false, "No Data msg element is null");	
											}

										}else{
											ele = fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, contactInfo[i][1], contactInfo[i][2], contactInfo[i][3], 10);
											if (ele!=null) {
												appLog.info("For Search Value : "+contactInfo[i][0]+" >>> Select contact grid verified with value : "+contactInfo[i][1]+"\t"+contactInfo[i][2]+"\t"+contactInfo[i][3]);
											} else {
												appLog.error("For Search Value : "+contactInfo[i][0]+" >>> Select contact grid Not verified with value : "+contactInfo[i][1]+"\t"+contactInfo[i][2]+"\t"+contactInfo[i][3]);
												sa.assertTrue(false, "For Search Value : "+contactInfo[i][0]+" >>> Select contact Not grid verified with value : "+contactInfo[i][1]+"\t"+contactInfo[i][2]+"\t"+contactInfo[i][3]);
											}	
										}

										String[][] defaultContactInfo = {{M17Contact3FirstName+" "+M17Contact3LastName,M17Contact3EmailId,M17Institution2},
												{M17Contact4FirstName+" "+M17Contact4LastName,M17Contact4EmailId,M17Institution2}};
										if (click(driver, fp.getClearIconOnContactAccessSearchBox(Workspace.InvestorWorkspace, 10), "Clear Icon", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on Cross Icon");
											ThreadSleep(2000);

											for (int j = 0; j < defaultContactInfo.length; j++) {
												ele = fp.verifySelectContactsGridDataInContactAccess(Workspace.InvestorWorkspace, defaultContactInfo[j][0], defaultContactInfo[j][1], defaultContactInfo[j][2], 10);
												if (ele!=null) {
													appLog.info("Default list >>> Select contact grid verified with value : "+defaultContactInfo[j][0]+"\t"+defaultContactInfo[j][1]+"\t"+defaultContactInfo[j][2]);
												} else {
													appLog.error("Default list  >>> Select contact grid Not verified with value : "+defaultContactInfo[j][0]+"\t"+defaultContactInfo[j][1]+"\t"+defaultContactInfo[j][2]);
													sa.assertTrue(false, "Default list  >>> Select contact Not grid verified with value : "+defaultContactInfo[j][0]+"\t"+defaultContactInfo[j][1]+"\t"+defaultContactInfo[j][2]);
												}
											}																																
										} else {
											appLog.error("Not Able to Click on Cross Icon");
											sa.assertTrue(false, "Not Able to Click on Cross Icon");
										}
									}else {
										appLog.error("Not Able to click on Search Icon for iteration : "+i);
										sa.assertTrue(false, "Not Able to click on Search Icon for iteration : "+i);
									}
								}else {
									appLog.error("Not Able to enter the value in search text box : "+contactInfo[i][0]);
									sa.assertTrue(false, "Not Able to enter the value in search text box : "+contactInfo[i][0]);
								}
							}
						}else {
							appLog.error("Not able to expand contact access grid ");
							sa.assertTrue(false, "Not able to expand contact access grid ");
						}

					}else {
						appLog.error("Not able to click on Contact Access Icon ");
						sa.assertTrue(false, "Not able to click on Contact Access Icon ");	
					}
				}else {
					appLog.error("Not able to click on "+M17Institution2+" folder");
					sa.assertTrue(false, "Not able to click on "+M17Institution2+" folder");
				}
			}else {
				appLog.error("Not able to click on created fund Name "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name "+M17FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab ");
			sa.assertTrue(false, "Not able to click on fund tab ");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc080_CheckSortingOfColumnsForSelectContactsGrid(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy("", M17Institution1, "", null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "InvestorWorkspace contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
						if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
							appLog.info("Able to expand Conatct Access Grid");
							ThreadSleep(2000);
							if (fp.performSortingCheckOnContactAccessPopUpSelectContactGrid(Workspace.InvestorWorkspace, 10)) {
								appLog.info("Sorting Verified on Contact Access PopUp Header List");
							} else {
								appLog.error("Sorting Not Verified on Contact Access PopUp Header List");
								sa.assertTrue(false, "Sorting Not Verified on Contact Access PopUp Header List");
							}
						}else {
							appLog.error("Not able to expand contact access grid ");
							sa.assertTrue(false, "Not able to expand contact access grid ");
						}

					}else {
						appLog.error("Not able to click on Contact Access Icon ");
						sa.assertTrue(false, "Not able to click on Contact Access Icon ");	
					}
				}else {
					appLog.error("Not able to click on "+M17Institution1+" folder");
					sa.assertTrue(false, "Not able to click on "+M17Institution1+" folder");
				}
			}else {
				appLog.error("Not able to click on created fund Name "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name "+M17FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab ");
			sa.assertTrue(false, "Not able to click on fund tab ");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M17tc081_CheckSortingOfColumnsForSelectedContactsGrid(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M17Institution1, M17LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "InvestorWorkspace contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
						if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
							appLog.info("Able to expand Conatct Access Grid");
							ThreadSleep(2000);

							WebElement ele = fp.getContactNameOrAllContactCheckBox(Workspace.InvestorWorkspace, null, 20);

							if(ele!=null) {
								ThreadSleep(1000);
								if(click(driver, ele, "check box", action.BOOLEAN)) {
									appLog.info("clicked on Header check box");
									ThreadSleep(1000);
									if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace+" add select contact active button", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on add selected contact active button in "+Workspace.InvestorWorkspace);

										if(click(driver, fp.getApplyBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" apply button", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on apply button successfully");
											if(click(driver, fp.getCloseBtn(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace+" close button", action.SCROLLANDBOOLEAN)) {
												appLog.info("Clicked on Close Buton");	
												ThreadSleep(2000);
												if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "InvestorWorkspace contact access icon", action.SCROLLANDBOOLEAN)) {
													appLog.info("Clicked on Contact Access Icon");
													ThreadSleep(2000);
													if (fp.performSortingCheckOnContactAccessPopUpSelectedContactGrid(Workspace.InvestorWorkspace, 10)) {
														appLog.info("Sorting Verified on Contact Access PopUp Selected Grid Header List");
													} else {
														appLog.error("Sorting Not Verified on Contact Access PopUp Selected Grid  Header List");
														sa.assertTrue(false, "Sorting Not Verified on Contact Access PopUp Selected Grid Header List");
													}
												}else{
													appLog.error("Not able to click on Contact Access Icon ");
													sa.assertTrue(false, "Not able to click on Contact Access Icon ");	
												}
											}else {
												appLog.error("Not able to click on close button");
												sa.assertTrue(false, "Not able to click on close button");
											}

										}else {
											appLog.error("not able to click on apply button so cannot invite contact: ");
											sa.assertTrue(false, "not able to click on apply button so cannot invite contact: ");
										}


									}else{
										appLog.error("Not able to click on add select contact button in "+Workspace.InvestorWorkspace);
										sa.assertTrue(false, "Not able to click on add select contact button in "+Workspace.InvestorWorkspace);
									}

								}else {
									appLog.error("Not able to click on Header CheckBox");
									sa.assertTrue(false, "Not able to click on Header CheckBox");
								}
							}else {
								appLog.error("Header CheckBox is not visible");
								sa.assertTrue(false, "Header CheckBox is not visible");
							}

							
						}else {
							appLog.error("Not able to expand contact access grid ");
							sa.assertTrue(false, "Not able to expand contact access grid ");
						}

					}else {
						appLog.error("Not able to click on Contact Access Icon ");
						sa.assertTrue(false, "Not able to click on Contact Access Icon ");	
					}
				}else {
					appLog.error("Not able to click on "+M17Institution1+" folder");
					sa.assertTrue(false, "Not able to click on "+M17Institution1+" folder");
				}
			}else {
				appLog.error("Not able to click on created fund Name "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name "+M17FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab ");
			sa.assertTrue(false, "Not able to click on fund tab ");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
}

	@Parameters({ "environment", "mode" }) @Test
	public void M17tc082_CheckFunctionalityHeaderCheckBoxInSelectContactsGridUnderContactAccessPopup(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M17FundName1)) {
				switchToDefaultContent(driver);
				switchToFrame(driver, 60,fp.getFrame(environment,mode, PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy("", M17Institution1, "", null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "InvestorWorkspace contact access icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Access Icon");
						if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
							appLog.info("Able to expand Conatct Access Grid");
							ThreadSleep(2000);

							// Check Header
							appLog.info("Going to Click Header CheckBox for Check");
							List<WebElement> eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.InvestorWorkspace);

							if(eles!=null) {
								ThreadSleep(1000);
								if(click(driver, eles.get(0), "Header check box Check", action.BOOLEAN)) {
									appLog.info("clicked on Header check box for check");
									eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.InvestorWorkspace);
									ThreadSleep(1000);

									for (int i = 0; i < eles.size(); i++) {

										if (isSelected(driver, eles.get(i), "CheckBoxes Checked")) {
											appLog.info("checkbox is checked after clicking on Header CheckBox for iteration : "+i);	
										} else {
											appLog.error("checkbox is not checked after clicking on Header CheckBox for iteration : "+i);
											sa.assertTrue(false, "checkbox is not checked after clicking on Header CheckBox for iteration : "+i);
										}

									}


								}else {
									appLog.error("Not able to click on Header CheckBox for check");
									sa.assertTrue(false, "Not able to click on Header CheckBox for check");
								}
							}else {
								appLog.error("CheckBox is not visible for check");
								sa.assertTrue(false, "CheckBox is not visible for check");
							}

							ThreadSleep(2000);
							// Uncheck Header
							appLog.info("Going to Click Header CheckBox for UnCheck");
							eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.InvestorWorkspace);

							if(eles!=null) {
								ThreadSleep(1000);
								if(click(driver, eles.get(0), "Header check box Uncheck", action.BOOLEAN)) {
									appLog.info("clicked on Header check box for Uncheck");
									ThreadSleep(1000);
									eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.InvestorWorkspace);
									for (int i = 0; i < eles.size(); i++) {

										if (!isSelected(driver, eles.get(i), "CheckBoxes UnChecked")) {
											appLog.info("checkbox is Unchecked after clicking on Header CheckBox for iteration : "+i);	
										} else {
											appLog.error("checkbox is not Unchecked after clicking on Header CheckBox for iteration : "+i);
											sa.assertTrue(false, "checkbox is not Unchecked after clicking on Header CheckBox for iteration : "+i);
										}

									}

								}else {
									appLog.error("Not able to click on Header CheckBox for Uncheck");
									sa.assertTrue(false, "Not able to click on Header CheckBox for Uncheck");
								}
							}else {
								appLog.error("CheckBox is not visible for Uncheck");
								sa.assertTrue(false, "CheckBox is not visible for Uncheck");
							}

							ThreadSleep(2000);


							// Check Header Again
							appLog.info("Going to Click Header CheckBox for Check Again");
							eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.InvestorWorkspace);

							if(eles!=null) {
								ThreadSleep(1000);
								if(click(driver, eles.get(0), "Header check box Again Check", action.BOOLEAN)) {
									appLog.info("clicked on Header check box for check Again");
									eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.InvestorWorkspace);
									ThreadSleep(1000);

									for (int i = 0; i < eles.size(); i++) {

										if (isSelected(driver, eles.get(i), "CheckBoxes Checked Again")) {
											appLog.info("checkbox is checked Again after clicking on Header CheckBox for iteration : "+i);	
										} else {
											appLog.error("checkbox is not checked Again after clicking on Header CheckBox for iteration : "+i);
											sa.assertTrue(false, "checkbox is not checked Again after clicking on Header CheckBox for iteration : "+i);
										}



									}

									// Uncheck Contact CheckBox
									appLog.info("Going to Uncheck Contact CheckBox");
									eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.InvestorWorkspace);
									ThreadSleep(1000);
									if (click(driver, eles.get(eles.size()-1), "Contact check box for UnCheck", action.BOOLEAN)) {
										appLog.info(" able to click on Contact CheckBox for Uncheck");	

										ThreadSleep(1000);
										eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.InvestorWorkspace);
										ThreadSleep(1000);

										for (int i = 0; i < eles.size(); i++) {

											if (i==0 || i==eles.size()-1) {

												if (!isSelected(driver, eles.get(i), "CheckBoxes UnChecked after Unchecking Contact CheckBox")) {
													appLog.info("checkbox is Unchecked after Unchecking Contact CheckBox for iteration : "+i);	
												} else {
													appLog.error("checkbox is not Unchecked after Unchecking Contact CheckBox for iteration : "+i);
													sa.assertTrue(false, "checkbox is not Unchecked after Unchecking Contact CheckBox for iteration : "+i);
												}	

											} else {

												if (isSelected(driver, eles.get(i), "CheckBoxes Checked after Unchecking Contact CheckBox")) {
													appLog.info("checkbox is checked after Unchecking Contact CheckBox for iteration : "+i);	
												} else {
													appLog.error("checkbox is not checked after Unchecking Contact CheckBox for iteration : "+i);
													sa.assertTrue(false, "checkbox is not checked after Unchecking Contact CheckBox for iteration : "+i);
												}
											}



										}

										// check  UnCheckBox
										eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.InvestorWorkspace);
										ThreadSleep(2000);
										appLog.info("Going to check UnCheckBox");	
										for (int i = 0; i < eles.size(); i++) {

											if (i==0 || i==eles.size()-1) {

												if (click(driver, eles.get(i), "UnCheck box for Check", action.BOOLEAN)) {
													appLog.info(" able to click on UnCheckBox for check");	
												}else{
													appLog.error("Not able to click on UnCheckBox for check");
													sa.assertTrue(false, "Not able to click on UnCheckBox for check");
												}
												
												ThreadSleep(1000);

											}

										}

										// Going to verify CheckBox after checking UncheckBoxes
										appLog.info("Going to verify CheckBox after checking UncheckBoxes");
										eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.InvestorWorkspace);
										ThreadSleep(1000);

										for (int i = 0; i < eles.size(); i++) {

											if (isSelected(driver, eles.get(i), "CheckBoxes Checked")) {
												appLog.info("checkbox is checked after checking Unchecked Box for iteration : "+i);	
											} else {
												appLog.error("checkbox is not checked after checking Unchecked Box for iteration : "+i);
												sa.assertTrue(false, "checkbox is not checked after checking Unchecked Box for iteration : "+i);
											}

										}

										//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

										// Uncheck Contact CheckBox
										appLog.info("Going to Uncheck Contact CheckBox Again");
										eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.InvestorWorkspace);
										ThreadSleep(1000);
										if (click(driver, eles.get(eles.size()-1), "Contact check box for UnCheck Again", action.BOOLEAN)) {
											appLog.info(" able to click on Contact CheckBox for Uncheck Again");	

											ThreadSleep(1000);
											eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.InvestorWorkspace);
											ThreadSleep(1000);

											for (int i = 0; i < eles.size(); i++) {

												if (i==0 || i==eles.size()-1) {

													if (!isSelected(driver, eles.get(i), "CheckBoxes UnChecked after Unchecking Contact CheckBox Again")) {
														appLog.info("checkbox is Unchecked after Unchecking Contact CheckBox Again for iteration : "+i);	
													} else {
														appLog.error("checkbox is not Unchecked after Unchecking Contact CheckBox Again for iteration : "+i);
														sa.assertTrue(false, "checkbox is not Unchecked after Unchecking Contact CheckBox Again for iteration : "+i);
													}	

												} else {

													if (isSelected(driver, eles.get(i), "CheckBoxes Checked after Unchecking Contact CheckBox Again")) {
														appLog.info("checkbox is checked after Unchecking Contact CheckBox Again for iteration : "+i);	
													} else {
														appLog.error("checkbox is not checked after Unchecking Contact CheckBox Again for iteration : "+i);
														sa.assertTrue(false, "checkbox is not checked after Unchecking Contact CheckBox Again for iteration : "+i);
													}
												}



											}

											// check  Header CheckBox
											appLog.info("Going to check Header CheckBox");	
											eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.InvestorWorkspace);
											ThreadSleep(1000);

											if (click(driver, eles.get(0), "check Header CheckBox", action.BOOLEAN)) {
												appLog.error(" able to click on Header CheckBox");	
											}else{
												appLog.error("Not able to click on Header CheckBox");
												sa.assertTrue(false, "Not able to click on Header CheckBox");
											}



											// Going to verify CheckBox after checking UncheckBoxes
											appLog.info("Going to verify CheckBox after checking Header CheckBox");
											eles = fp.getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace.InvestorWorkspace);
											ThreadSleep(1000);

											for (int i = 0; i < eles.size(); i++) {

												if (isSelected(driver, eles.get(i), "checking Header CheckBox")) {
													appLog.info("checkbox is checked after checking Header CheckBox for iteration : "+i);	
												} else {
													appLog.error("checkbox is not checked after checking Header CheckBox for iteration : "+i);
													sa.assertTrue(false, "checkbox is not checked after checking Header CheckBox for iteration : "+i);
												}

											}




										} else {
											appLog.error("Not able to click on Contact CheckBox for Uncheck");
											sa.assertTrue(false, "Not able to click on Contact CheckBox for Uncheck");
										}				

										//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


									} else {
										appLog.error("Not able to click on Contact CheckBox for Uncheck");
										sa.assertTrue(false, "Not able to click on Contact CheckBox for Uncheck");
									}


								}else {
									appLog.error("Not able to click on Header CheckBox for check Again");
									sa.assertTrue(false, "Not able to click on Header CheckBox for check Again");
								}
							}else {
								appLog.error("CheckBox is not visible for check Again");
								sa.assertTrue(false, "CheckBox is not visible for check Again");
							}

							ThreadSleep(2000);

						}else {
							appLog.error("Not able to expand contact access grid ");
							sa.assertTrue(false, "Not able to expand contact access grid ");
						}

					}else {
						appLog.error("Not able to click on Contact Access Icon ");
						sa.assertTrue(false, "Not able to click on Contact Access Icon ");	
					}
				}else {
					appLog.error("Not able to click on "+M17Institution1+" folder");
					sa.assertTrue(false, "Not able to click on "+M17Institution1+" folder");
				}
			}else {
				appLog.error("Not able to click on created fund Name "+M17FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name "+M17FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab ");
			sa.assertTrue(false, "Not able to click on fund tab ");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
}

	
}
