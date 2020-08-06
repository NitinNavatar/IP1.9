/**
 * 
 */
package com.navatar.scripts;

import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib;
import com.navatar.generic.EmailLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.CommonLib.ContentGridArrowKeyFunctions;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.TabName;
import com.navatar.generic.CommonLib.UploadFileActions;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.generic.CommonLib.sideMenu;
import com.navatar.pageObjects.AllFirmsErrorMessage;
import com.navatar.pageObjects.AllFirmsPageBusinesslayer;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.CommitmentPageBusinessLayer;
import com.navatar.pageObjects.ContactPageBusinessLayer;
import com.navatar.pageObjects.FundRaisingPageBusinessLayer;
import com.navatar.pageObjects.FundsPageBusinessLayer;
import com.navatar.pageObjects.InstitutionPageBusinessLayer;
import com.navatar.pageObjects.InvestorFirmPageBusinesslayer;
import com.navatar.pageObjects.InvestorFirmPageErrorMessage;
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.NIMPageBusinessLayer;
import com.navatar.pageObjects.PartnershipPageBusinessLayer;
import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.CommonVariables.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Ankit Jaiswal
 *
 */
public class Module3 extends BaseLib {
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc001_Module3_preCondition() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		for(int i=0; i<2; i++) {
			String instutionName=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M3Instituition"+(i+1), excelLabel.Institutions_Name);
			if(bp.clickOnTab(TabName.InstituitonsTab)) {
			if(ip.createInstitution(instutionName)) {
				
			}else {
				appLog.error("Not able to create institution: "+instutionName);
				sa.assertTrue(false, "Not able to create institution: "+instutionName);
			}
		}else {
				appLog.error("Not able to click on institution tab so cannot create institution: "+instutionName);
				sa.assertTrue(false, "Not able to click on institution tab so cannot create institution: "+instutionName);
			}
		}
		for (int i = 0; i < 2; i++) {
			String emailId = cp.generateRandomEmailId();
			String instutionName=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M3Contact"+(i+1), excelLabel.Institutions_Name);
			String ContactFirstName=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M3Contact"+(i+1), excelLabel.Contact_FirstName);
			String ContactLastName=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M3Contact"+(i+1), excelLabel.Contact_LastName);	
			if(bp.clickOnTab(TabName.ContactTab)){
				if(cp.createContact(ContactFirstName, ContactLastName, instutionName, emailId)) {
					appLog.info("contact is created: "+ContactFirstName+" "+ContactLastName);
					if (emailId != "") {
						ExcelUtils.writeData(emailId,"Contacts", excelLabel.Variable_Name, "M3Contact"+(i+1),excelLabel.Contact_EmailId);
					}
				}else {
					appLog.error("Not able to create contact: "+ContactFirstName+" "+ContactLastName);
					sa.assertTrue(false, "Not able to create contact: "+ContactFirstName+" "+ContactLastName);
				}
			}
		}
		for(int i=0; i<2; i++) {
			String fundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund"+(i+1), excelLabel.Fund_Name);
			if(bp.clickOnTab(TabName.FundsTab)) {
				String fundType=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund"+(i+1), excelLabel.Fund_Type);
				String investmentCategory=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund"+(i+1), excelLabel.Fund_InvestmentCategory);
				if(fp.createFund(fundName, fundType, investmentCategory)) {
					appLog.info("fund is created: "+fundName);
				}else {
					appLog.error("Not able to create fund: "+fundName);
					sa.assertTrue(false, "Not able to create fund: "+fundName);
				}
			}else {
				appLog.error("Not able to click on fund tab so cannot create fund: "+fundName);
				sa.assertTrue(false, "Not able to click on fund tab so cannot create fund: "+fundName);
			}
		}
		for(int i=0; i<4; i++) {
			String fundraisingName=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M3FundRaising"+(i+1), excelLabel.FundRaising_Name);
			if(bp.clickOnTab(TabName.FundraisingsTab)) {
				String fundName=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M3FundRaising"+(i+1), excelLabel.Fund_Name);
				String instutionName=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M3FundRaising"+(i+1), excelLabel.Institutions_Name);
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
		for(int i=0; i<2; i++) {
			String lpName=ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M3LimitedPartner"+(i+1), excelLabel.LimitedPartner_Name);
			if(bp.clickOnTab(TabName.InstituitonsTab)) {
				String instutionName=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M3Instituition"+(i+1), excelLabel.Institutions_Name);
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
		
		for(int i=0; i<2; i++) {
			String partnershipName=ExcelUtils.readData("Partnerships",excelLabel.Variable_Name, "M3Partnership"+(i+1), excelLabel.PartnerShip_Name);
			if(bp.clickOnTab(TabName.PartnershipsTab)) {
				String fundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund"+(i+1), excelLabel.Fund_Name);
				if(pp.createPartnership(partnershipName,fundName)) {
					appLog.info("partnership is created: "+partnershipName);
				}else {
					appLog.error("Not able to create partnership: "+partnershipName);
					sa.assertTrue(false, "Not able to create partnership: "+partnershipName);
				}
			}else {
				appLog.error("Not able to click on partnership tab so cannot create partnership: "+partnershipName);
				sa.assertTrue(false, "Not able to click on partnership tab so cannot create partnership: "+partnershipName);
			}
		}
		for(int i=0; i<4; i++) {
			String lpName;
			String partnershipName = null;
			if(i==0 || i==1) {
				lpName=ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M3LimitedPartner1", excelLabel.LimitedPartner_Name);
			}else {
				lpName=ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M3LimitedPartner2", excelLabel.LimitedPartner_Name);
			}
			if(bp.clickOnTab(TabName.CommitmentsTab)) {
				if(i==0 || i==2) {
					partnershipName=ExcelUtils.readData("Partnerships",excelLabel.Variable_Name, "M3Partnership1", excelLabel.PartnerShip_Name);
				}else {
					partnershipName=ExcelUtils.readData("Partnerships",excelLabel.Variable_Name, "M3Partnership2", excelLabel.PartnerShip_Name);
				}
				if(cmp.createCommitment(lpName,partnershipName,"M3Commitment"+(i+1), null)) {
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
		if(nim.getMyProfileFistNameAndLastNameAndFirmName("User1")) {
			appLog.info("CRM User1 first,last name and firm name successfully write in excel");
		}else {
			appLog.error("Not able to write CRM User1 first,last name and firm profile in excel");
			sa.assertTrue(false, "Not able to write CRM User1 first,last name and firm profile in excel");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc002_buildFWRAndINVWorkSpace() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund1", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund1", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund1", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund1", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund1", excelLabel.Fund_Email);
				String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund1", excelLabel.Fund_Description);
				String templateName=ExcelUtils.readData("Foldertemp", 0, 1);
				String[] data= {Size,vintageyear,contact,phone,email,description};
				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,M3Institution1+"<break>"+M3Institution2, Workspace.FundraisingWorkspace,60)) {
					appLog.info("FundRaising work is build successfully.");
				}else {
					appLog.error("Not able to bulid fundraising workspace on fund: "+M3FundName1);
					sa.assertTrue(false, "Not able to bulid fundraising workspace on fund: "+M3FundName1);
				}
				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,M3Institution1+"/"+M3LimitedPartner1+"<break>"+M3Institution2+"/"+M3LimitedPartner2, Workspace.InvestorWorkspace,60)) {
					appLog.info("Investor work is build successfully.");
				}else {
					appLog.error("Not able to bulid Investor workspace on fund: "+M3FundName1);
					sa.assertTrue(false, "Not able to bulid Investor workspace on fund: "+M3FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot build fundraising workspace: "+M3FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build fundraising workspace: "+M3FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace: "+M3FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace: "+M3FundName1);
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc003_inviteContactFromWorkSpace(String environment, String mode) {
			LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
			BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
			FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
			lp.CRMLogin(CRMUser1EmailID,adminPassword);
			if(fp.clickOnTab(TabName.FundsTab)) {
				if(fp.clickOnCreatedFund(M3FundName1)) {
					switchToFrame(driver, 30,bp.getFrame( PageName.FundsPage, 30));
					if(fp.inviteContact(environment,mode, M3Institution1, M3Contact1EmailId,ExcelUtils.readData("FilePath",0, 4, currentlyExecutingTC), FolderType.Standard,"Upload","Yes", "Yes","All Folders", Workspace.FundraisingWorkspace, M3Contact1EmailId)) {
						appLog.info("contact is invites successfully from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					}else {
						appLog.error("Not able to invite contact from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
						sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					}
					if(fp.inviteContact(environment,mode, null, M3Contact1EmailId,ExcelUtils.readData("FilePath",0, 3, currentlyExecutingTC), FolderType.Shared,null,"Yes", null,"All Folders", Workspace.FundraisingWorkspace, null)) {
						appLog.info("contact is invites successfully from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					}else {
						appLog.error("Not able to invite contact from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
						sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					}
					
					if(fp.inviteContact(environment,mode, M3Institution1+"/"+M3LimitedPartner1, M3Contact1EmailId,ExcelUtils.readData("FilePath",0, 4, currentlyExecutingTC), FolderType.Standard,"Upload","Yes", null,"All Folders", Workspace.InvestorWorkspace, M3Contact1EmailId)) {
						appLog.info("contact is invites successfully from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					}else {
						appLog.error("Not able to invite contact from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
						sa.assertTrue(false, "Not able to invite contact from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					}
					if(fp.inviteContact(environment,mode, null, M3Contact1EmailId,ExcelUtils.readData("FilePath",0, 3, currentlyExecutingTC), FolderType.Shared,null,"Yes", null,"All Folders", Workspace.InvestorWorkspace, null)) {
						appLog.info("contact is invites successfully from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					}else {
						appLog.error("Not able to invite contact from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
						sa.assertTrue(false, "Not able to invite contact from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					}
				}else {
					appLog.error("Not able to click on created fund Name so cannot build fundraising and investor workspace: "+M3FundName1);
					sa.assertTrue(false, "Not able to click on created fund Name so cannot build fundraising and investor workspace: "+M3FundName1);
				}
			}else {
				appLog.error("Not able to click on fund tab so cannot build fundraising workspace and investor: "+M3FundName1);
				sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace and investor: "+M3FundName1);
			}
			switchToDefaultContent(driver);
			lp.CRMlogout();
			sa.assertAll();
		}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc004_registerM3Contact1(){
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		if (ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M3Contact1", excelLabel.Registered).equalsIgnoreCase("Not Registered")) {
			try {
				investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail",gmailUserName,gmailPassword,CRMUser1EmailID,M3Contact1EmailId);
				appLog.info("Investor Registration Link: " + investorRegLink);
			} catch (InterruptedException e) {
				appLog.info("Invitation mail not found.");
				e.printStackTrace();
			}
			if (investorRegLink != null) {
				driver.get(investorRegLink);
				if (bp.targetRegistration(M3Contact1FirstName, M3Contact1LastName, M3Contact1EmailId, M3Institution1,
						adminPassword)) {
					appLog.info(
							"Investor is registered successfully: " + M3Contact1FirstName + " " + M3Contact1LastName);
					ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M3Contact1", excelLabel.Registered);
				} else {
					driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
					appLog.info("Investor Target URL through Direct URL..");
					if (bp.targetRegistration(M3Contact1FirstName, M3Contact1LastName, M3Contact1EmailId,
							M3Institution1, adminPassword)) {
						appLog.info("Investor is registered successfully: " + M3Contact1FirstName + " "
								+ M3Contact1LastName);
						ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M3Contact1", excelLabel.Registered);
					} else {
						appLog.error("Investor is not Registered through Invited Link: " + M3Contact1FirstName + " "
								+ M3Contact1LastName);
						sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M3Contact1FirstName
								+ " " + M3Contact1LastName);
					}
				}
			} else {
				driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
				appLog.info("Register Investor URL through Direct URL..");
				if (bp.targetRegistration(M3Contact1FirstName, M3Contact1LastName, M3Contact1EmailId,
						M3Institution1, adminPassword)) {
					appLog.info("Investor is registered successfully: " + M3Contact1FirstName + " "
							+ M3Contact1LastName);
					ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M3Contact1", excelLabel.Registered);
				} else {
					appLog.error("Investor is not Registered through Invited Link: " + M3Contact1FirstName + " "
							+ M3Contact1LastName);
					sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M3Contact1FirstName
							+ " " + M3Contact1LastName);
				}
			}		
		lp.investorLogout();
		} else {
			appLog.info("investor "+M3Contact1FirstName+" "+M3Contact1LastName+" is already Registered.");
			sa.assertTrue(false, "investor "+M3Contact1FirstName+" "+M3Contact1LastName+" is already Registered.");
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc005_validate_DashboardUI() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		String[] alertGridHeaderText= {"Name","Firm","Investment Name","Workspace","Activity Created On","Activity Type"};
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			appLog.info("All firms is selected ");
			if(allfp.getInvestmentHeaderText(30).getText().trim().equalsIgnoreCase("Investments")) {
				appLog.info("Investments header is displaying on all firms home page");
			}else {
				appLog.error("Investments header is not displaying on all firms home page");
				sa.assertTrue(false, "Investments header is not displaying on all firms home page");
			}
			if(allfp.getActivitisHeadertext(30).getText().trim().contains("Activities (0)")) {
				appLog.info("Activities section is displaying on all firms home page");
			}else {
				appLog.error("Activities section is displaying on all firms home page");
				sa.assertTrue(false, "Activities section is displaying on all firms home page");
			}
			List<WebElement> headerlist=allfp.getAllfirmalertHeaderTextList();
			if(!headerlist.isEmpty()) {
				for(int i=0; i<alertGridHeaderText.length; i++) {
					for(int j=0; j<headerlist.size(); j++) {
						if(headerlist.get(j).getText().trim().equalsIgnoreCase(alertGridHeaderText[i])) {
							appLog.info(alertGridHeaderText[i]+" is matched");
							break;
						}else {
							if(j==headerlist.size()-1) {
								appLog.error(alertGridHeaderText[i]+" is not matched.");
								sa.assertTrue(false, alertGridHeaderText[i]+" is not matched.");
							}
						}
					}
				}
			}else {
				appLog.error("Activities header text is not visible on so cannot verify activities alert header text");
				sa.assertTrue(false, "Activities header text is not visible on so cannot verify activities alert header text");
			}
			if(allfp.getActivityAlertNoDatatoDisplayErrorMsg(30)!=null) {
				if(allfp.getActivityAlertNoDatatoDisplayErrorMsg(10).getText().trim().equalsIgnoreCase(AllFirmsErrorMessage.NodatatoDisplayErrorMsg)) {
					appLog.info(AllFirmsErrorMessage.NodatatoDisplayErrorMsg+" error message is verified");
				}else {
					appLog.error(AllFirmsErrorMessage.NodatatoDisplayErrorMsg+" error message is not verified");
					sa.assertTrue(false, AllFirmsErrorMessage.NodatatoDisplayErrorMsg+" error message is not verified");
				}
			}else {
				appLog.error("No data to display error message is not visible on alert grid");
				sa.assertTrue(false, "No data to display error message is not visible on alert grid");
			}
		}else {
			appLog.error("Not able to select All Firms from firm name drop down list so  cannot verify dashboard UI");
			sa.assertTrue(false, "Not able to select All Firms from firm name drop down list so  cannot verify dashboard UI");
		}
		lp.investorLogout();
		sa.assertAll();
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc006_validateFirmAlertUIAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		String[] alertGridHeaderText= {"Name","Investment Name","Workspace","Activity Created On","Activity Type"};
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String aa =getSelectedOptionOfDropDown(driver, allfp.getFirmNameDropdown(60), "firm name drop down list", "text");
		if(aa.contains(Org1FirmName)) {
			appLog.info("Firm Name is selected ");
		}else {
			appLog.error("Firm Name is not selected in firm name drop down  list. Expected:  "+Org1FirmName);
			sa.assertTrue(false, "Firm Name is not selected in firm name drop down  list. Expected:  "+Org1FirmName);
		}
		List<WebElement> hometab= ivp.gethomeTab(30);
		for(int i=0; i<hometab.size(); i++) {
			if(isDisplayed(driver, hometab.get(i), "visibility", 10, "hometab")!=null) {
				appLog.info("Home page is visible on investor firm page");
				break;
			}else {
				if(i==hometab.size()-1) {
					appLog.error("Home page is not visible on investor firm page");
					sa.assertTrue(false, "Home page is not visible on investor firm page");					
				}
			}
			
		}
		List<WebElement> investmentTab= ivp.getInvestmentsTab();
		for(int i=0; i<investmentTab.size(); i++) {
			if(isDisplayed(driver, investmentTab.get(i), "visibility", 10, "hometab")!=null) {
				appLog.info("investment tab is visible on investor firm page");
				break;
			}else {
				if(i==investmentTab.size()-1) {
					appLog.error("investment tab is not visible on investor firm page");
					sa.assertTrue(false, "investment tab is not visible on investor firm page");					
				}
			}
			
		}
		if(ivp.getRecentActivitiesTab(30)!=null) {
			appLog.info("recent activitie tab is visible on investor firm page");
		}else {
			appLog.error("recent activitie stab is not visible on investor firm page");
			sa.assertTrue(false, "recent activitie stab is not visible on investor firm page");
		}
		if(ivp.getAllDocumentsTab(30)!=null) {
			appLog.info("all document tab is visible on investor firm page");
		}else {
			appLog.error("all document tab is not visible on investor firm page");
			sa.assertTrue(false, "all document tab is not visible on investor firm page");
		}
		if(click(driver, ivp.getRecentActivitiesTab(30), "recent activities tab", action.BOOLEAN)) {
			appLog.info("clicked on recent activities tab");
			List<WebElement> recentHeaderList=ivp.getRecentActivitiesAlertHeaderTextList();
			if(!recentHeaderList.isEmpty()) {
				for(int i=0; i<alertGridHeaderText.length;i++) {
					for(int j=0;j<recentHeaderList.size();j++) {
						if(recentHeaderList.get(j).getText().trim().equalsIgnoreCase(alertGridHeaderText[i])) {
							appLog.info(alertGridHeaderText[i]+" is matched successfully");
							break;
						}else {
							if(j==recentHeaderList.size()-1) {
								appLog.error(alertGridHeaderText[i]+" is not macthed");
								sa.assertTrue(false, alertGridHeaderText[i]+" is not macthed");
							}
						}
					}
				}
			}
			if(ivp.getRecentActivitiesActivityCreatedOnSortinIcon(30)!=null) {
				appLog.info("deafult sorting icon is visible on activity created on header");
			}else {
				appLog.error("deafult sorting icon is not visible on activity created on header");
				sa.assertTrue(false, "deafult sorting icon is not visible on activity created on header");
			}
			if(ivp.getRecentActivitiesAlertNoDataToDisplayErrorMsg(20)!=null) {
				String aa1= ivp.getRecentActivitiesAlertNoDataToDisplayErrorMsg(10).getText().trim();
				if(aa1.equalsIgnoreCase(InvestorFirmPageErrorMessage.NodatatoDisplayErrorMsg)) {
					appLog.info(InvestorFirmPageErrorMessage.NodatatoDisplayErrorMsg+" is verified on recent activities alert grid");
				}else {
					appLog.error(InvestorFirmPageErrorMessage.NodatatoDisplayErrorMsg+" is not verified on recent activities alert grid.");
					sa.assertTrue(false, InvestorFirmPageErrorMessage.NodatatoDisplayErrorMsg+" is not verified on recent activities alert grid.");
				}
			}
			
		}else {
			appLog.error("Not able to click on recent activities tab so cannot verify recent activities header text");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot verify recent activities header text");
		}
		investmentTab.clear();
		investmentTab= ivp.getInvestmentsTab();
		for(int i=0; i<investmentTab.size(); i++) {
			if(isDisplayed(driver, investmentTab.get(i), "visibility", 20, "")!=null) {
				if(click(driver,investmentTab.get(i), "investment tab", action.BOOLEAN)) {
					appLog.info("clicked on investment tab");
					
					if(ivp.getCurrentInvestmentTab(30)!=null) {
						appLog.info("Current investment tab is visible on investor firm page.");
					}else {
						appLog.error("Current investment tab is not visible on investor firm page");
						sa.assertTrue(false, "Current investment tab is not visible on investor firm page");
					}
					if(ivp.getPotentialInvestmentTab(20)!=null) {
						appLog.info("potential investment tab is visible on investor firm page");
					}else {
						appLog.error("potential investment tab is not visible on investor firm page");
						sa.assertTrue(false, "potential investment tab is not visible on investor firm page");
					}
				}else {
					appLog.error("Not able to click on investment tab so cannot verify currrent investment and potential investment tab");
					sa.assertTrue(false, "Not able to click on investment tab so cannot verify currrent investment and potential investment tab");
				}
				break;
			}else {
				if(i==investmentTab.size()-1) {
					appLog.error("investment tab is not visible so cannot click on it");
					sa.assertTrue(false, "investment tab is not visible so cannot click on it");
				}
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc007_validateAllDocumentUI() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		String alertGridHeaderText= "Name<break>Investment Name<break>Workspace";
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String aa =getSelectedOptionOfDropDown(driver, allfp.getFirmNameDropdown(60), "firm name drop down list", "text");
		if(aa.contains(Org1FirmName)) {
			appLog.info("Firm Name is selected ");
		}else {
			appLog.error("Firm Name is not selected in firm name drop down  list. Expected:  "+Org1FirmName);
			sa.assertTrue(false, "Firm Name is not selected in firm name drop down  list. Expected:  "+Org1FirmName);
		}
		List<WebElement> hometab= ivp.gethomeTab(60);
		for(int i=0; i<hometab.size(); i++) {
			if(isDisplayed(driver, hometab.get(i), "visibility", 10, "hometab")!=null) {
				appLog.info("Home page is visible on investor firm page");
				break;
			}else {
				if(i==hometab.size()-1) {
					appLog.error("Home page is not visible on investor firm page");
					sa.assertTrue(false, "Home page is not visible on investor firm page");					
				}
			}
			
		}
		List<WebElement> investmentTab= ivp.getInvestmentsTab();
		for(int i=0; i<investmentTab.size(); i++) {
			if(isDisplayed(driver, investmentTab.get(i), "visibility", 10, "hometab")!=null) {
				appLog.info("investment tab is visible on investor firm page");
				break;
			}else {
				if(i==investmentTab.size()-1) {
					appLog.error("investment tab is not visible on investor firm page");
					sa.assertTrue(false, "investment tab is not visible on investor firm page");					
				}
			}
			
		}
		if(ivp.getRecentActivitiesTab(30)!=null) {
			appLog.info("recent activitie tab is visible on investor firm page");
		}else {
			appLog.error("recent activitie stab is not visible on investor firm page");
			sa.assertTrue(false, "recent activitie stab is not visible on investor firm page");
		}
		if(ivp.getAllDocumentsTab(30)!=null) {
			appLog.info("all document tab is visible on investor firm page");
		}else {
			appLog.error("all document tab is not visible on investor firm page");
			sa.assertTrue(false, "all document tab is not visible on investor firm page");
		}
		if(click(driver, ivp.getAllDocumentsTab(30), "all documents tab", action.BOOLEAN)) {
			appLog.info("clicked on all documents tab");
			List<String> lst=compareMultipleList(driver,alertGridHeaderText, ivp.getAllDocumentHeaderTextList());
			if(!lst.isEmpty()) {
				for(int i=0; i<lst.size(); i++) {
					appLog.error(lst.get(i));
					sa.assertTrue(false, lst.get(i));
				}
			}else {
				appLog.error("all files are matched successfully.");
			}
			if(ivp.getAlldocumentsNoDataToDisplayErrorMsg(30)!=null) {
				String aa1= ivp.getAlldocumentsNoDataToDisplayErrorMsg(10).getText().trim();
				if(aa1.equalsIgnoreCase(InvestorFirmPageErrorMessage.alldocumentNodatatodisplayErrorMsg)) {
					appLog.info(InvestorFirmPageErrorMessage.alldocumentNodatatodisplayErrorMsg+" is verified on all documents alert grid");
				}else {
					appLog.error(InvestorFirmPageErrorMessage.alldocumentNodatatodisplayErrorMsg+" is not verified on all documents alert grid.");
					sa.assertTrue(false, InvestorFirmPageErrorMessage.alldocumentNodatatodisplayErrorMsg+" is not verified on all documents alert grid.");
				}
			}
			if(ivp.getAlldocumentSearchBtn(20)!=null) {
				appLog.info("all documents search button is visible on all documents tab");
			}else {
				appLog.error("all documents search button is not visible on all documents tab");
				sa.assertTrue(false, "all documents search button is not visible on all documents tab");
			}
			if(ivp.getAllDocumentSearchTextBox(20)!=null) {
				appLog.info("all documents search text box is visible on all documents tab");
			}else {
				appLog.error("all documents search text box is not visible on all documents tab");
				sa.assertTrue(false, "all documents search text box is not visible on all documents tab");
			}
			if(ivp.getAllDocumentCount(20)!=null) {
				String aa1=ivp.getAllDocumentCount(20).getText().trim();
				if(aa1.contains("Count") && aa1.contains("0")) {
					appLog.info("Count is matched successfully: "+aa1);
				}else {
					appLog.error("Count is not matched: "+aa1);
					sa.assertTrue(false, "Count is not matched: "+aa1);
				}
			}
			
		}else {
			appLog.error("Not able to click on all documents tab so cannot verify all documents header text");
			sa.assertTrue(false, "Not able to click on all documents tab so cannot verify all documents header text");
		}
		investmentTab.clear();
		investmentTab= ivp.getInvestmentsTab();
		for(int i=0; i<investmentTab.size(); i++) {
			if(isDisplayed(driver, investmentTab.get(i), "visibility", 20, "")!=null) {
				if(click(driver,investmentTab.get(i), "investment tab", action.BOOLEAN)) {
					appLog.info("clicked on investment tab");
					
					if(ivp.getCurrentInvestmentTab(30)!=null) {
						appLog.info("Current investment tab is visible on investor firm page.");
					}else {
						appLog.error("Current investment tab is not visible on investor firm page");
						sa.assertTrue(false, "Current investment tab is not visible on investor firm page");
					}
					if(ivp.getPotentialInvestmentTab(20)!=null) {
						appLog.info("potential investment tab is visible on investor firm page");
					}else {
						appLog.error("potential investment tab is not visible on investor firm page");
						sa.assertTrue(false, "potential investment tab is not visible on investor firm page");
					}
				}else {
					appLog.error("Not able to click on investment tab so cannot verify currrent investment and potential investment tab");
					sa.assertTrue(false, "Not able to click on investment tab so cannot verify currrent investment and potential investment tab");
				}
				break;
			}else {
				if(i==investmentTab.size()-1) {
					appLog.error("investment tab is not visible so cannot click on it");
					sa.assertTrue(false, "investment tab is not visible so cannot click on it");
				}
			}
		}
		lp.investorLogout();
		sa.assertAll();

	}

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc008_1_uploadDocInFWRSTDFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
				String FWR_docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\Standard1";
				if(fp.uploadFile(folderpath,M3Institution1, FWR_docpath,null,UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								List<String> filecount=createListOutOfString(filesName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allDocCount=Integer.parseInt(AllDocument_count)+filecount.size();
								int allfirmsCount=Integer.parseInt(Allfirms_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);
								ExcelUtils.writeData(String.valueOf(allfirmsCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+allDocCount);
								appLog.info("All firms count should be at investor side: "+allfirmsCount);
								
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot upload files in fundraising workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc008_2_verifyDocAlertForSTDFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,8, "M3tc008_1_uploadDocInFWRSTDFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc009_1_uploadDocInFWRCommonFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);
		
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\Common1";
				if(fp.uploadFile(folderpath,null, docpath,null,UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								List<String> filecount=createListOutOfString(filesName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allDocCount=Integer.parseInt(AllDocument_count)+filecount.size();
								int allfirmsCount=Integer.parseInt(Allfirms_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);
								ExcelUtils.writeData(String.valueOf(allfirmsCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+allDocCount);
								appLog.info("All firms count should be at investor side: "+allfirmsCount);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot upload files in fundraising workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc009_2_verifyDocAlertForCommonFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,5, "M3tc009_1_uploadDocInFWRCommonFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc010_1_uploadDocInFWRSharedFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\Shared1";
				
				if(fp.uploadFile(folderpath,null, docpath,null,UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								List<String> filecount=createListOutOfString(filesName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allDocCount=Integer.parseInt(AllDocument_count)+filecount.size();
								int allfirmsCount=Integer.parseInt(Allfirms_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);
								ExcelUtils.writeData(String.valueOf(allfirmsCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+allDocCount);
								appLog.info("All firms count should be at investor side: "+allfirmsCount);
							}
							
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot upload files in fundraising workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc010_2_verifyDocAlertForShaedFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,7, "M3tc010_1_uploadDocInFWRSharedFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc011_1_uploadDocInFWRInternalFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,2,currentlyExecutingTC);
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\Internal1";
				
				if(fp.uploadFile(folderpath,null, docpath,null,UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								appLog.info("alert count should be at investor side: "+Alert_Count);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
								appLog.info("All firms count should be at investor side: "+Allfirms_Count);
							}
							
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot upload files in fundraising workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc011_2_verifyDocAlertForInternalFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,8,currentlyExecutingTC);
		String internalDocFileName=ExcelUtils.readData("FilePath",0,6,"M3tc011_1_uploadDocInFWRInternalFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(internalDocFileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
				}
			}else {
				appLog.error(internalDocFileName+" are visible investor side all documents tab");
				sa.assertTrue(false,internalDocFileName+" are visible investor side all documents tab");
				
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			result=ivp.verifyAlertOnRecentAllDocumentGrid(internalDocFileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
				}
			}else {
				appLog.error(internalDocFileName+" are visible investor side recent activities tab");
				sa.assertTrue(false,internalDocFileName+" are visible investor side recent activities tab");
				
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			result=allfp.verifyAlertsOnAllFirmsPage(internalDocFileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
				}
			}else {
				appLog.error(internalDocFileName+" are visible investor side all firms tab");
				sa.assertTrue(false,internalDocFileName+" are visible investor side all firms tab");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc012_1_updateDocInFWRSTDFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\UploadUpdateFiles\\Standard1";
				if(fp.uploadFile(folderpath,M3Institution1, docpath,UploadFileActions.Update,UploadFileActions.Update, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is updated successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileStandard);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								List<String> filecount=createListOutOfString(filesName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allFirmCount=Integer.parseInt(Allfirms_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allFirmCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
								appLog.info("All firms count should be at investor side: "+allFirmCount);
							}
							
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify updated documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot updated upload documents");
					}
				}else {
					appLog.error("File is not updated in "+folderpath);
					sa.assertTrue(false, "File is not updated in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in fundraising workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc012_2_verifyUpdateDocAlertForSTDFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,12, "M3tc012_1_updateDocInFWRSTDFolder");
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc013_1_updateDocInFWRCommonFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\UploadUpdateFiles\\Common1";
				if(fp.uploadFile(folderpath,null, docpath,UploadFileActions.Update,UploadFileActions.Update, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is updated successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileCommon);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								List<String> filecount=createListOutOfString(filesName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allFirmCount=Integer.parseInt(Allfirms_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allFirmCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
								appLog.info("All firms count should be at investor side: "+allFirmCount);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify updated documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot updated upload documents");
					}
				}else {
					appLog.error("File is not updated in "+folderpath);
					sa.assertTrue(false, "File is not updated in "+folderpath);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in fundraising workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc013_2_verifyUpdateDocAlertForCommonFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,9, "M3tc013_1_updateDocInFWRCommonFolder");
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc014_1_updateDocInFWRSharedFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\UploadUpdateFiles\\Shared1";
				if(fp.uploadFile(folderpath,null, docpath,UploadFileActions.Update,UploadFileActions.Update, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is updated successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileShared);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								List<String> filecount=createListOutOfString(filesName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allFirmCount=Integer.parseInt(Allfirms_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allFirmCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
								appLog.info("All firms count should be at investor side: "+allFirmCount);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify updated documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot updated upload documents");
					}
				}else {
					appLog.error("File is not updated in "+folderpath);
					sa.assertTrue(false, "File is not updated in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in fundraising workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc014_2_verifyUpdateDocAlertForSharedFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,11, "M3tc014_1_updateDocInFWRSharedFolder");
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc015_1_updateDocInFWRInternalFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,2,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\UploadUpdateFiles\\Internal1";
				if(fp.uploadFile(folderpath,null, docpath,UploadFileActions.Update,UploadFileActions.Update, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is updated successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileInternal);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								appLog.info("alert count should be at investor side: "+Alert_Count);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
								appLog.info("All firms count should be at investor side: "+Allfirms_Count);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify updated documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot updated upload documents");
					}
				}else {
					appLog.error("File is not updated in "+folderpath);
					sa.assertTrue(false, "File is not updated in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in fundraising workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc015_2_verifyUpdateDocAlertForInternalFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String internalDocFileName=ExcelUtils.readData("FilePath",0,10, "M3tc015_1_updateDocInFWRInternalFolder");
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(internalDocFileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
				}
			}else {
				appLog.error(internalDocFileName+" are visible investor side recent activities tab");
				sa.assertTrue(false,internalDocFileName+" are visible investor side recent activities tab");
				
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(internalDocFileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
				}
			}else {
				appLog.error(internalDocFileName+" are visible investor side all firms tab");
				sa.assertTrue(false,internalDocFileName+" are visible investor side all firms tab");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
		
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc016_1_onlineImportInFWRSTDFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,8,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.onlineImport(environment, mode, M3Institution1,null,null,folderpath, docPath, fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Standard,PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName+" is verified.");
								List<String> filecount=createListOutOfString(fileName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allDocCount=Integer.parseInt(AllDocument_count)+filecount.size();
								int allfirmsCount=Integer.parseInt(Allfirms_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);
								ExcelUtils.writeData(String.valueOf(allfirmsCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+allDocCount);
								appLog.info("All firms count should be at investor side: "+allfirmsCount);
							}
						}
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot import files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot import files in fundraising workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot import files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc016_2_verifyOnlineImportDocAlertForSTDFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,8, "M3tc016_1_onlineImportInFWRSTDFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc017_1_onlineImportInFWRCommonFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,5,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.onlineImport(environment, mode, null,null,null,folderpath, docPath, fileName, BoxUserName, BoxPassword, null, WorkSpaceAction.UPLOAD, FolderType.Common,PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName+" is verified.");
								List<String> filecount=createListOutOfString(fileName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allDocCount=Integer.parseInt(AllDocument_count)+filecount.size();
								int allfirmsCount=Integer.parseInt(Allfirms_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);
								ExcelUtils.writeData(String.valueOf(allfirmsCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+allDocCount);
								appLog.info("All firms count should be at investor side: "+allfirmsCount);
							}
						}
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot import files in fundraising workspace: "+fileName);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot import files in fundraising workspace: "+fileName);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot import files in fundraising workspace: "+fileName);
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files in fundraising workspace: "+fileName);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc017_2_verifyOnlineImportDocAlertForCommonFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,5, "M3tc017_1_onlineImportInFWRCommonFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc018_1_onlineImportInFWRSharedFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,7,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.onlineImport(environment, mode, null,null,null,folderpath, docPath, fileName, BoxUserName, BoxPassword, null, WorkSpaceAction.UPLOAD, FolderType.Shared,PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName+" is verified.");
								List<String> filecount=createListOutOfString(fileName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allDocCount=Integer.parseInt(AllDocument_count)+filecount.size();
								int allfirmsCount=Integer.parseInt(Allfirms_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);
								ExcelUtils.writeData(String.valueOf(allfirmsCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+allDocCount);
								appLog.info("All firms count should be at investor side: "+allfirmsCount);
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+fileName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+fileName);
						}
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot import files in fundraising workspace: "+fileName);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot import files in fundraising workspace: "+fileName);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot import files in fundraising workspace: "+fileName);
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files in fundraising workspace: "+fileName);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
		
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc018_2_verifyOnlineImportDocAlertForSharedFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,7, "M3tc018_1_onlineImportInFWRSharedFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
		
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc019_1_onlineImportInFWRInternalFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,2,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,6,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.onlineImport(environment, mode, null,null,null,folderpath, docPath, fileName, BoxUserName, BoxPassword, null, WorkSpaceAction.UPLOAD, FolderType.Internal,PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName+" is verified.");
								appLog.info("alert count should be at investor side: "+Alert_Count);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
								appLog.info("All firms count should be at investor side: "+Allfirms_Count);
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+fileName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+fileName);
						}
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot import files in fundraising workspace: "+fileName);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot import files in fundraising workspace: "+fileName);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot import files in fundraising workspace: "+fileName);
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files in fundraising workspace: "+fileName);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc019_2_verifyOnlineImportDocAlertForInternalFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String internalDocFileName=ExcelUtils.readData("FilePath",0,6,"M3tc019_1_onlineImportInFWRInternalFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(internalDocFileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
				}
			}else {
				appLog.error(internalDocFileName+" are visible investor side all documents tab");
				sa.assertTrue(false,internalDocFileName+" are visible investor side all documents tab");
				
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(internalDocFileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
				}
			}else {
				appLog.error(internalDocFileName+" are visible investor side recent activities tab");
				sa.assertTrue(false,internalDocFileName+" are visible investor side recent activities tab");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(internalDocFileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
				}
			}else {
				appLog.error(internalDocFileName+" are visible investor side all firms tab");
				sa.assertTrue(false,internalDocFileName+" are visible investor side all firms tab");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc020_1_updateImportInFWRSTDFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,8,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.onlineImport(environment, mode, M3Institution1,null,null,folderpath, docPath, fileName,BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPDATE, FolderType.Standard,PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName+" is verified.");
								List<String> filecount=createListOutOfString(fileName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allfirmsCount=Integer.parseInt(Allfirms_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allfirmsCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
								appLog.info("All firms count should be at investor side: "+allfirmsCount);
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+fileName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+fileName);
						}
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot import files in fundraising workspace: "+fileName);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot import files in fundraising workspace: "+fileName);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot import files in fundraising workspace: "+fileName);
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files in fundraising workspace: "+fileName);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc020_2_verifyUpdateImportInFWRSTDFolderAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,8, currentlyExecutingTC);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc021_1_updateOnlineImportInFWRCommonFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,5,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.onlineImport(environment, mode, null,null,null,folderpath, docPath, fileName, BoxUserName, BoxPassword, null, WorkSpaceAction.UPDATE, FolderType.Common,PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName+" is verified.");
								List<String> filecount=createListOutOfString(fileName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allfirmsCount=Integer.parseInt(Allfirms_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allfirmsCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
								appLog.info("All firms count should be at investor side: "+allfirmsCount);
							}
						}
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot import files in fundraising workspace: "+fileName);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot import files in fundraising workspace: "+fileName);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot import files in fundraising workspace: "+fileName);
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files in fundraising workspace: "+fileName);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
		
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc021_2_verifyOnlineImportUpdateAlertForCommonFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,5, currentlyExecutingTC);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc022_1_onlineImportUpdateInFWRSharedFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,7,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.onlineImport(environment, mode, null,null,null,folderpath, docPath, fileName, BoxUserName, BoxPassword, null, WorkSpaceAction.UPDATE, FolderType.Shared,PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName+" is verified.");
								List<String> filecount=createListOutOfString(fileName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allfirmsCount=Integer.parseInt(Allfirms_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allfirmsCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
								appLog.info("All firms count should be at investor side: "+allfirmsCount);
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+fileName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+fileName);
						}
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot upload files in fundraising workspace: "+fileName);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot upload files in fundraising workspace: "+fileName);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace: "+fileName);
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace: "+fileName);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc022_2_verifyOnlineImportUpdateAlertForSharedFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,7, currentlyExecutingTC);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc023_1_onlineImportUpdateInFWRInternalFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,2,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,6,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.onlineImport(environment, mode, null,null,null,folderpath, docPath, fileName, BoxUserName, BoxPassword, null, WorkSpaceAction.UPDATE, FolderType.Internal,PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName+" is verified.");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+fileName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+fileName);
						}
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot upload files in fundraising workspace: "+fileName);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot upload files in fundraising workspace: "+fileName);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace: "+fileName);
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace: "+fileName);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc023_2_verifyOnlineImportUpdateAlertForInternalFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String internalDocFileName=ExcelUtils.readData("FilePath",0,6,currentlyExecutingTC);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(internalDocFileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
				}
			}else {
				appLog.error(internalDocFileName+" are visible investor side recent activities tab");
				sa.assertTrue(false,internalDocFileName+" are visible investor side recent activities tab");
				
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(internalDocFileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
				}
			}else {
				appLog.error(internalDocFileName+" are visible investor side all firms tab");
				sa.assertTrue(false,internalDocFileName+" are visible investor side all firms tab");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc024_1_updateDiffNameDocInFWRSTDFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
		String[] fileName=ExcelUtils.readData("FilePath",0,8,currentlyExecutingTC).split(",");
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module3\\FileToUploadCRMSide\\FilesToUpdateCRMSide\\Standard1\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.updateFile(folderpath, fileName[0], M3Institution1, null, FolderType.Standard,docPath+fileName[1], null, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.FundraisingWorkspace)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName[1],fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[1]+" is verified.");
								int alertCount=Integer.parseInt(Alert_Count)+1;
								int allfirmsCount=Integer.parseInt(Allfirms_Count)+1;
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allfirmsCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
								appLog.info("All firms count should be at investor side: "+allfirmsCount);
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+fileName[0]+"to "+fileName[1]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+fileName[0]+"to "+fileName[1]);
						}
				}else {
					appLog.error("file is not updated: "+fileName[0]+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName[0]+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in fundraising workspace: "+fileName[0]);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in fundraising workspace: "+fileName[0]);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc024_2_verifyUpdateDocAlertForSTDFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,8, currentlyExecutingTC);
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
		
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc025_1_updateDiffNameDocInFWRCommonFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
		String[] fileName=ExcelUtils.readData("FilePath",0,5,currentlyExecutingTC).split(",");
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module3\\FileToUploadCRMSide\\FilesToUpdateCRMSide\\Common1\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.updateFile(folderpath, fileName[0], null, null, FolderType.Common,docPath+fileName[1], null, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.FundraisingWorkspace)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName[1],fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[1]+" is verified.");
								int alertCount=Integer.parseInt(Alert_Count)+1;
								int allfirmsCount=Integer.parseInt(Allfirms_Count)+1;
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allfirmsCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
								appLog.info("All firms count should be at investor side: "+allfirmsCount);
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+fileName[0]+"to "+fileName[1]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+fileName[0]+"to "+fileName[1]);
						}
				}else {
					appLog.error("file is not updated: "+fileName[0]+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName[0]+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in fundraising workspace: "+fileName[0]);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in fundraising workspace: "+fileName[0]);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc025_2_verifyUpdateDocAlertForCommonFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,5, currentlyExecutingTC);
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc026_1_updateDiffNameDocInFWRSharedFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
		String[] fileName=ExcelUtils.readData("FilePath",0,7,currentlyExecutingTC).split(",");
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module3\\FileToUploadCRMSide\\FilesToUpdateCRMSide\\Shared1\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.updateFile(folderpath, fileName[0], null, null, FolderType.Shared,docPath+fileName[1], null, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.FundraisingWorkspace)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName[1],fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[1]+" is verified.");
								int alertCount=Integer.parseInt(Alert_Count)+1;
								int allfirmsCount=Integer.parseInt(Allfirms_Count)+1;
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allfirmsCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
								appLog.info("All firms count should be at investor side: "+allfirmsCount);
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+fileName[0]+"to "+fileName[1]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+fileName[0]+"to "+fileName[1]);
						}
				}else {
					appLog.error("file is not updated: "+fileName[0]+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName[0]+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in fundraising workspace: "+fileName[0]);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in fundraising workspace: "+fileName[0]);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc026_2_verifyUpdateDocAlertForSharedFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,7, currentlyExecutingTC);
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
		
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc027_1_updateDiffNameDocInFWRInternalFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,2,currentlyExecutingTC);
		String[] fileName=ExcelUtils.readData("FilePath",0,6,currentlyExecutingTC).split(",");
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module3\\FileToUploadCRMSide\\FilesToUpdateCRMSide\\Internal1\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.updateFile(folderpath, fileName[0], null, null, FolderType.Internal,docPath+fileName[1], null, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.FundraisingWorkspace)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName[1],fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[1]+" is verified.");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+fileName[0]+"to "+fileName[1]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+fileName[0]+"to "+fileName[1]);
						}
				}else {
					appLog.error("file is not updated: "+fileName[0]+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName[0]+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in fundraising workspace: "+fileName[0]);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in fundraising workspace: "+fileName[0]);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
		
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc027_2_verifyUpdateDocAlertForInertnalFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,8, currentlyExecutingTC);
		String internalDocFileName=ExcelUtils.readData("FilePath",0,6,currentlyExecutingTC);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			result=ivp.verifyAlertOnRecentAllDocumentGrid(internalDocFileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
				}
			}else {
				appLog.error(internalDocFileName+" are visible investor side recent activities tab");
				sa.assertTrue(false,internalDocFileName+" are visible investor side recent activities tab");
				
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			result=allfp.verifyAlertsOnAllFirmsPage(internalDocFileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
				}
			}else {
				appLog.error(internalDocFileName+" are visible investor side all firms tab");
				sa.assertTrue(false,internalDocFileName+" are visible investor side all firms tab");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc028_1_updateDocViaManageVersionInFWRSTDFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
		String[] fileName=ExcelUtils.readData("FilePath",0,8,currentlyExecutingTC).split(",");
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module3\\FileToUploadCRMSide\\FilesToUpdateCRMSide\\Standard1\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.updateFile(folderpath, fileName[0], M3Institution1, null, FolderType.Standard,docPath+fileName[1], null, null, ContentGridArrowKeyFunctions.ManageVersions,20, PageName.FundsPage,"Yes",null, Workspace.FundraisingWorkspace)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName[1],fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[1]+" is verified.");
								int alertCount=Integer.parseInt(Alert_Count)+1;
								int allfirmsCount=Integer.parseInt(Allfirms_Count)+1;
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allfirmsCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
								appLog.info("All firms count should be at investor side: "+allfirmsCount);
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify updated document: "+fileName[0]+"to "+fileName[1]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated document: "+fileName[0]+"to "+fileName[1]);
						}
				}else {
					appLog.error("file is not updated: "+fileName[0]+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName[0]+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files via manage version in fundraising workspace: "+fileName[0]);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update file via manage version in fundraising workspace: "+fileName[0]);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload file via manage version in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload file via manage version in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc028_2_verifyUpdateDocViaManageVersionAlertForSTDFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,8, currentlyExecutingTC);
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc029_1_updateDocViaManageVersionInFWRCommonFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
		String[] fileName=ExcelUtils.readData("FilePath",0,5,currentlyExecutingTC).split(",");
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module3\\FileToUploadCRMSide\\FilesToUpdateCRMSide\\Common1\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.updateFile(folderpath, fileName[0], null, null, FolderType.Common,docPath+fileName[1], null, null, ContentGridArrowKeyFunctions.ManageVersions,20, PageName.FundsPage,"Yes",null, Workspace.FundraisingWorkspace)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName[1],fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[1]+" is verified.");
								int alertCount=Integer.parseInt(Alert_Count)+1;
								int allfirmsCount=Integer.parseInt(Allfirms_Count)+1;
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allfirmsCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
								appLog.info("All firms count should be at investor side: "+allfirmsCount);
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify updated document: "+fileName[0]+"to "+fileName[1]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated document: "+fileName[0]+"to "+fileName[1]);
						}
				}else {
					appLog.error("file is not updated: "+fileName[0]+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName[0]+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update file via manage version in fundraising workspace: "+fileName[0]);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update file via manage version in fundraising workspace: "+fileName[0]);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update file via manage version in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
			sa.assertTrue(false, "Not able to click on fund tab so cannot update file via manage version in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc029_2_verifyUpdateDocViaManageVersionAlertForCommonFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,5, currentlyExecutingTC);
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc030_1_updateDocViamanageversionInFWRSharedFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
		String[] fileName=ExcelUtils.readData("FilePath",0,7,currentlyExecutingTC).split(",");
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module3\\FileToUploadCRMSide\\FilesToUpdateCRMSide\\Shared1\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.updateFile(folderpath, fileName[0], null, null, FolderType.Shared,docPath+fileName[1], null, null, ContentGridArrowKeyFunctions.ManageVersions,20, PageName.FundsPage,"Yes",null, Workspace.FundraisingWorkspace)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName[1],fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[1]+" is verified.");
								int alertCount=Integer.parseInt(Alert_Count)+1;
								int allfirmsCount=Integer.parseInt(Allfirms_Count)+1;
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allfirmsCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
								appLog.info("All firms count should be at investor side: "+allfirmsCount);
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify updated document: "+fileName[0]+"to "+fileName[1]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated document: "+fileName[0]+"to "+fileName[1]);
						}
				}else {
					appLog.error("file is not updated: "+fileName[0]+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName[0]+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update file via manage version in fundraising workspace: "+fileName[0]);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update file via manage version in fundraising workspace: "+fileName[0]);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload file via manage version in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload file via manage version in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc030_2_verifyUpdateDocViamanageVersionAlertForSharedFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,7, currentlyExecutingTC);
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc031_1_updateDocViaManageversionInFWRInternalFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,2,currentlyExecutingTC);
		String[] fileName=ExcelUtils.readData("FilePath",0,6,currentlyExecutingTC).split(",");
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module3\\FileToUploadCRMSide\\FilesToUpdateCRMSide\\Internal1\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.updateFile(folderpath, fileName[0], null, null, FolderType.Internal,docPath+fileName[1], null, null, ContentGridArrowKeyFunctions.ManageVersions,20, PageName.FundsPage,"Yes",null, Workspace.FundraisingWorkspace)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName[1],fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[1]+" is verified.");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify updated document: "+fileName[0]+"to "+fileName[1]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated document: "+fileName[0]+"to "+fileName[1]);
						}
				}else {
					appLog.error("file is not updated: "+fileName[0]+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName[0]+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update file via manage version in fundraising workspace: "+fileName[0]);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update file via manage version in fundraising workspace: "+fileName[0]);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload file via manage version in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload file via manage version in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc031_2_verifyUpdateDocViaManageVersionAlertForInertnalFoldersAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,8, currentlyExecutingTC);
		String internalDocFileName=ExcelUtils.readData("FilePath",0,6,currentlyExecutingTC);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			result=ivp.verifyAlertOnRecentAllDocumentGrid(internalDocFileName, M3FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
				}
			}else {
				appLog.error(internalDocFileName+" are visible investor side recent activities tab");
				sa.assertTrue(false,internalDocFileName+" are visible investor side recent activities tab");
				
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			result=allfp.verifyAlertsOnAllFirmsPage(internalDocFileName,Org1FirmName, M3FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
				}
			}else {
				appLog.error(internalDocFileName+" are visible investor side all firms tab");
				sa.assertTrue(false,internalDocFileName+" are visible investor side all firms tab");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc032_1_changeDocViaManageVersoinInCommonFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
		String[] fileName=ExcelUtils.readData("FilePath",0,5,currentlyExecutingTC).split(",");
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.makeCurrentversionDocViaManageVersion(environment, mode, null, null, folderpath, fileName[0],fileName[1], Workspace.FundraisingWorkspace, 30)) {
					appLog.info("file is successfully make current: "+fileName[1]+" in :"+folderpath);
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName[1],fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[1]+" is verified.");
								appLog.info("alert count should be at investor side: "+Alert_Count);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
								appLog.info("All firms count should be at investor side: "+Allfirms_Count);
							}
							result.clear();
							result=compareMultipleList(driver,fileName[0],fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(result.isEmpty()) {
								for(int i=0; i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[0]+" is not visible after make current via manage version");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify current document: "+fileName[0]+"to "+fileName[1]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify current document: "+fileName[0]+"to "+fileName[1]);
						}
				}else {
					appLog.error("file is not updated: "+fileName[0]+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName[0]+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot make current document via manage version in fundraising workspace: "+fileName[0]);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot make current document via manage version in fundraising workspace: "+fileName[0]);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot make current document via manage version in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
			sa.assertTrue(false, "Not able to click on fund tab so cannot make current document via manage version  in fundraising workspace: "+fileName[0]+"to "+fileName[1]);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc032_2_verifyAlertsCountsAtInvestorSideAfterMakeCurrentDoc() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp = new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is not increased after make current document in CRM side.");
			}else {
				appLog.error("recent activities alert count is increased after make current document in CRM side.");
				sa.assertTrue(false, "recent activities alert count is increased after make current document in CRM side.");
			}
		}
		if(click(driver, ivp.getAllDocumentsTab(30),"all document", action.BOOLEAN)) {
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is not increased after make current document in CRM side.");
			}else {
				appLog.error("All documents alert count is increased after make current document in CRM side.");
				sa.assertTrue(false, "All documents alert count is increased after make current document in CRM side.");
			}
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is not increased after make current document in CRM side.");
			}else {
				appLog.error("All firms alert count is increased after make current document in CRM side.");
				sa.assertTrue(false, "All firms alert count is increased after make current document in CRM side.");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc033_1_uploadFilesInINVSTDFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\InvestorWorkSpace\\Standard1";
				
				if(fp.uploadFile(folderpath,M3Institution1+"/"+M3LimitedPartner1+"<break>"+M3Institution2+"/"+M3LimitedPartner2, docpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								List<String> filecount=createListOutOfString(filesName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allDocCount=Integer.parseInt(AllDocument_count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+allDocCount);
							}
							
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot upload files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot upload files in investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc033_2_verifyUploadDocSTDFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,8, "M3tc033_1_uploadFilesInINVSTDFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc034_1_uploadFilesInINVCommonFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);	
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\InvestorWorkSpace\\Common1";
				if(fp.uploadFile(folderpath,null, docpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								List<String> filecount=createListOutOfString(filesName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allDocCount=Integer.parseInt(AllDocument_count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+allDocCount);
							}
							
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot upload files in Investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot upload files in Investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in Investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in Investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc034_2_verifyUploadDoccommonFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,5, "M3tc034_1_uploadFilesInINVCommonFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc035_1_uploadFilesInINVSharedFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);	
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\InvestorWorkSpace\\Shared1";
				
				if(fp.uploadFile(folderpath,null, docpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								List<String> filecount=createListOutOfString(filesName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allDocCount=Integer.parseInt(AllDocument_count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+allDocCount);
							}
							
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot upload files in Investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot upload files in Investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in Investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in Investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc035_2_verifyUploadDocSharedFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,7, "M3tc035_1_uploadFilesInINVSharedFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc036_1_uploadFilesInINVInternalFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);	
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,2,currentlyExecutingTC);
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\InvestorWorkSpace\\Internal1";
				
				if(fp.uploadFile(folderpath,null, docpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								appLog.info("alert count should be at investor side: "+Alert_Count);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot upload files in Investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot upload files in Investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in Investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in Investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc036_2_verifyUploadDocInternalFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,6, "M3tc036_1_uploadFilesInINVInternalFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
				appLog.info(fileName+" are not visible in all documents alerts.");
			}else {
				appLog.error(fileName+" are visible in all documents alerts.");
				sa.assertTrue(false,fileName+" are visible in all documents alerts.");
			}
			
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
				appLog.info(fileName+" are not visible in recent document alert grid");
			}else {
				appLog.error(fileName+" are visible in recent document alert grid");
				sa.assertTrue(false, fileName+" are visible in recent document alert grid");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
					
				}
				appLog.info(fileName+" are not visible in all firms alerts grid");
			}else {
				appLog.error(fileName+" are visible in all firms alerts grid");
				sa.assertTrue(false, fileName+" are visible in all firms alerts grid");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc037_1_updateFilesInINVSTDFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\InvestorWorkSpace\\UploadUpdateFiles\\Standard1";
				if(fp.uploadFile(folderpath,M3Institution1+"/"+M3LimitedPartner1, docpath,UploadFileActions.Update,UploadFileActions.Update, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is updated successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileStandard);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								int alertCount=Integer.parseInt(Alert_Count)+1;
								int allfirmCount=Integer.parseInt(Allfirms_Count)+1;
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allfirmCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("alert count should be at investor side: "+allfirmCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
							
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify updated documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot updated upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc037_2_verifyUdateDocSTDFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,12, "M3tc037_1_updateFilesInINVSTDFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info(fileName+" are not visible in recent document alert grid");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate , null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info(fileName+" are not visible in all firms alerts grid");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc038_1_updateFilesInINVCommonFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\InvestorWorkSpace\\UploadUpdateFiles\\Common1";
				if(fp.uploadFile(folderpath,null, docpath,UploadFileActions.Update,UploadFileActions.Update, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is updated successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileCommon);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								int alertCount=Integer.parseInt(Alert_Count)+1;
								int allfirmCount=Integer.parseInt(Allfirms_Count)+1;
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allfirmCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
							
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify update documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify update documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc038_2_verifyUdateDocCommonFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,9, "M3tc038_1_updateFilesInINVCommonFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info(fileName+" are visible in recent document alert grid");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate , null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info(fileName+" are visible in all firms alerts grid");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc039_1_updateFilesInINVSharedFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\InvestorWorkSpace\\UploadUpdateFiles\\Shared1";
				
				if(fp.uploadFile(folderpath,null, docpath,UploadFileActions.Update,UploadFileActions.Update, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is updated successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileShared);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								int alertCount=Integer.parseInt(Alert_Count)+1;
								int allfirmCount=Integer.parseInt(Allfirms_Count)+1;
								ExcelUtils.writeData(String.valueOf(allfirmCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
							
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify update documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify update documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc039_2_verifyUdateDocSharedFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,11, "M3tc039_1_updateFilesInINVSharedFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info(fileName+" are visible in recent document alert grid");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate , null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info(fileName+" are visible in all firms alerts grid");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc040_1_updateFilesInINVSInternalFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,2,currentlyExecutingTC);
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\InvestorWorkSpace\\UploadUpdateFiles\\Internal1";
				if(fp.uploadFile(folderpath,null, docpath,UploadFileActions.Update,UploadFileActions.Update, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is updated successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileInternal);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								appLog.info("alert count should be at investor side: "+Alert_Count);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
								appLog.info("All firms count should be at investor side: "+Allfirms_Count);
								
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify updated documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated documents");
					}
				}else {
					appLog.error("File is not updated in "+folderpath);
					sa.assertTrue(false, "File is not updated in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot updated files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot updated files in investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot updated files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot updated files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc040_2_verifyUdateDocInternalFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,10, "M3tc040_1_updateFilesInINVSInternalFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}		
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
				appLog.info(fileName+" are not visible in recent document alert grid");
			}else {
				appLog.info(fileName+" are visible in recent document alert grid");
				sa.assertTrue(false, fileName+" are visible in recent document alert grid");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate , null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
				appLog.info(fileName+" are not visible in all firms alerts grid");
			}else {
				appLog.info(fileName+" are visible in all firms alerts grid");
				sa.assertTrue(false, fileName+" are visible in all firms alerts grid");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc041_1_onlineImportInINVSTDFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,8,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.onlineImport(environment, mode, M3Institution1,M3LimitedPartner1,null,folderpath, docPath, fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Standard,PageName.FundsPage, Workspace.InvestorWorkspace, 20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName+" is verified.");
								List<String> filecount=createListOutOfString(fileName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allDocCount=Integer.parseInt(AllDocument_count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+allDocCount);
							}
						}
					
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot online import files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot online import files in investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot online import files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot online import files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc041_2_verifyImportedDocSTDFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,8, "M3tc041_1_onlineImportInINVSTDFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc042_1_onlineImportInINVCommonFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,5,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.onlineImport(environment, mode, null,null,null,folderpath, docPath, fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Common,PageName.FundsPage, Workspace.InvestorWorkspace, 20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName+" is verified.");
								List<String> filecount=createListOutOfString(fileName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allDocCount=Integer.parseInt(AllDocument_count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+allDocCount);
							}
						}
					
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot online import files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot online import files in investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot online import files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot online import files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc042_2_verifyImportedDocCommonFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,5, "M3tc042_1_onlineImportInINVCommonFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc043_1_onlineImportInINVSharedFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,7,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.onlineImport(environment, mode, null,null,null,folderpath, docPath, fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Shared,PageName.FundsPage, Workspace.InvestorWorkspace, 20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName+" is verified.");
								List<String> filecount=createListOutOfString(fileName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allDocCount=Integer.parseInt(AllDocument_count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+allDocCount);
							}
						}
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot online import files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot online import files in investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot online import files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot online import files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc043_2_verifyImportedDocSharedFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,7,"M3tc043_1_onlineImportInINVSharedFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc044_1_onlineImportInINVInternalFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,2,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,6,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.onlineImport(environment, mode, null,null,null,folderpath, docPath, fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Internal,PageName.FundsPage, Workspace.InvestorWorkspace, 20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName+" is verified.");
								appLog.info("alert count should be at investor side: "+Alert_Count);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
						}
					
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot online import files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot online import files in investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot online import files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot online import files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc044_2_verifyImportedDocInternalFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,6,"M3tc044_1_onlineImportInINVInternalFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.info(fileName+" is available in all document alert grid");
				sa.assertTrue(false, fileName+" is available in all document alert grid");
			}
			
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.info(fileName+" is available in recent activities alert grid");
				sa.assertTrue(false, fileName+" is available in recent activities alert grid");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.info(fileName+" is available in all firms alert grid");
				sa.assertTrue(false, fileName+" is available in all firms alert grid");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc045_1_updateImporedDoctInINVSTDFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,8,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.onlineImport(environment, mode, M3Institution1,M3LimitedPartner1,null,folderpath, docPath, fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPDATE, FolderType.Standard,PageName.FundsPage, Workspace.InvestorWorkspace, 20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName+" is verified.");
								List<String> filecount=createListOutOfString(fileName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
						}
					
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update online import files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update online import files in investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update online import files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot update online import files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc045_2_verifyUpdateDocSTDFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,8,"M3tc045_1_updateImporedDoctInINVSTDFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc046_1_updateImportDocInINVCommonFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,5,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.onlineImport(environment, mode, null,null,null,folderpath, docPath, fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPDATE, FolderType.Common,PageName.FundsPage, Workspace.InvestorWorkspace, 20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName+" is verified.");
								List<String> filecount=createListOutOfString(fileName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
						}
					
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update online import files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot  update online import files in investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update online import files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot update online import files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc046_2_verifyUpdateImportedDocCommonFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,5, "M3tc046_1_updateImportDocInINVCommonFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc047_1_updateImportDocInINVSharedFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,7,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.onlineImport(environment, mode, null,null,null,folderpath, docPath, fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPDATE, FolderType.Shared,PageName.FundsPage, Workspace.InvestorWorkspace, 20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName+" is verified.");
								List<String> filecount=createListOutOfString(fileName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
						}
					
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update online import files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot  update online import files in investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update online import files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot update online import files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc047_2_verifyUpdateImportedDocSharedFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,7, "M3tc047_1_updateImportDocInINVSharedFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc048_1_updateImportDocInINVInternalFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,2,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,6,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.onlineImport(environment, mode, null,null,null,folderpath, docPath, fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPDATE, FolderType.Internal,PageName.FundsPage, Workspace.InvestorWorkspace, 20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName+" is verified.");
								appLog.info("alert count should be at investor side: "+Alert_Count);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
						}
					
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update online import files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot  update online import files in investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update online import files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot  update online import files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc048_2_verifyImportedDocInternalFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String fileName=ExcelUtils.readData("FilePath",0,6,"M3tc048_1_updateImportDocInINVInternalFolder");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.info(fileName+" is available in all document alert grid");
				sa.assertTrue(false, fileName+" is available in all document alert grid");
			}
			
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName, M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.info(fileName+" is available in recent activities alert grid");
				sa.assertTrue(false, fileName+" is available in recent activities alert grid");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName,Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.info(fileName+" is available in all firms alert grid");
				sa.assertTrue(false, fileName+" is available in all firms alert grid");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc049_1_updateDiffNameDocInINVSTDFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			ThreadSleep(5000);
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			ThreadSleep(5000);
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
		String[] fileName=ExcelUtils.readData("FilePath",0,12,currentlyExecutingTC).split(",");
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module3\\FileToUploadCRMSide\\FilesToUpdateCRMSide\\InvertorWorkspace\\Standard1\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.updateFile(folderpath, fileName[0], M3Institution1, M3LimitedPartner1, FolderType.Standard,docPath+fileName[1], multiInstance.ThisInvestorOnly, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.InvestorWorkspace)) {
					appLog.info("file is updated successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName[1],fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[1]+" is verified.");
								int alertCount=Integer.parseInt(Alert_Count)+1;
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
							result.clear();
							result=compareMultipleList(driver,fileName[0],fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(true, result.get(i));
								}
							}else {
								appLog.info(fileName[0]+" is available in the investor workspace");
								sa.assertTrue(false, fileName[0]+" is available in the investor workspace");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+fileName[0]+"to "+fileName[1]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+fileName[0]+"to "+fileName[1]);
						}
				}else {
					appLog.error("file is not updated: "+fileName[0]+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName[0]+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace: "+fileName[0]);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace: "+fileName[0]);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in investor workspace: "+fileName[0]+"to "+fileName[1]);
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in investor workspace: "+fileName[0]+"to "+fileName[1]);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc049_2_verifyDiffNameUpdateDocSTDFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String[] fileName=ExcelUtils.readData("FilePath",0,12,"M3tc049_1_updateDiffNameDocInINVSTDFolder").split(",");
		if(click(driver,ivp.getAllDocumentsTab(60),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName[1], M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(60),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName[1], M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName[1],Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc050_1_updateDiffNameDocInINVCommonFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			ThreadSleep(5000);
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			ThreadSleep(5000);
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
		String[] fileName=ExcelUtils.readData("FilePath",0,9,currentlyExecutingTC).split(",");
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module3\\FileToUploadCRMSide\\FilesToUpdateCRMSide\\InvertorWorkspace\\Common1\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.updateFile(folderpath, fileName[0], null, null, FolderType.Common,docPath+fileName[1], null, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.InvestorWorkspace)) {
					appLog.info("file is updated successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName[1],fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[1]+" is verified.");
								int alertCount=Integer.parseInt(Alert_Count)+1;
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
							result.clear();
							result=compareMultipleList(driver,fileName[0],fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(true, result.get(i));
								}
							}else {
								appLog.info(fileName[0]+" is available in the investor workspace");
								sa.assertTrue(false, fileName[0]+" is available in the investor workspace");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+fileName[0]+"to "+fileName[1]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+fileName[0]+"to "+fileName[1]);
						}
				}else {
					appLog.error("file is not updated: "+fileName[0]+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName[0]+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace: "+fileName[0]);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace: "+fileName[0]);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in investor workspace: "+fileName[0]+"to "+fileName[1]);
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in investor workspace: "+fileName[0]+"to "+fileName[1]);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc050_2_verifyDiffNameUpdateDocCommonFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String[] fileName=ExcelUtils.readData("FilePath",0,9,"M3tc050_1_updateDiffNameDocInINVCommonFolder").split(",");
		if(click(driver,ivp.getAllDocumentsTab(60),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName[1], M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(60),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName[1], M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName[1],Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc051_1_updateDiffNameDocInINVSharedFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			ThreadSleep(5000);
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			ThreadSleep(5000);
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
		String[] fileName=ExcelUtils.readData("FilePath",0,11,currentlyExecutingTC).split(",");
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module3\\FileToUploadCRMSide\\FilesToUpdateCRMSide\\InvertorWorkspace\\Shared1\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.updateFile(folderpath, fileName[0], null, null, FolderType.Shared,docPath+fileName[1], null, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.InvestorWorkspace)) {
					appLog.info("file is updated successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName[1],fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[1]+" is verified.");
								int alertCount=Integer.parseInt(Alert_Count)+1;
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
							result.clear();
							result=compareMultipleList(driver,fileName[0],fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(true, result.get(i));
								}
							}else {
								appLog.info(fileName[0]+" is available in the investor workspace");
								sa.assertTrue(false, fileName[0]+" is available in the investor workspace");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+fileName[0]+"to "+fileName[1]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+fileName[0]+"to "+fileName[1]);
						}
				}else {
					appLog.error("file is not updated: "+fileName[0]+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName[0]+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace: "+fileName[0]);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace: "+fileName[0]);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in investor workspace: "+fileName[0]+"to "+fileName[1]);
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in investor workspace: "+fileName[0]+"to "+fileName[1]);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc051_2_verifyDiffNameUpdateDocSharedFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String[] fileName=ExcelUtils.readData("FilePath",0,11,"M3tc051_1_updateDiffNameDocInINVSharedFolder").split(",");
		if(click(driver,ivp.getAllDocumentsTab(60),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName[1], M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(60),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName[1], M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName[1],Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc052_1_updateDiffNameDocInINVInternalFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			ThreadSleep(5000);
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			ThreadSleep(5000);
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,2,currentlyExecutingTC);
		String[] fileName=ExcelUtils.readData("FilePath",0,10,currentlyExecutingTC).split(",");
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module3\\FileToUploadCRMSide\\FilesToUpdateCRMSide\\InvertorWorkspace\\Internal1\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.updateFile(folderpath, fileName[0], null, null, FolderType.Internal,docPath+fileName[1], null, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.InvestorWorkspace)) {
					appLog.info("file is updated successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName[1],fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[1]+" is verified.");
								appLog.info("alert count should be at investor side: "+Alert_Count);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
							result.clear();
							result=compareMultipleList(driver,fileName[0],fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(true, result.get(i));
								}
							}else {
								appLog.info(fileName[0]+" is available in the investor workspace");
								sa.assertTrue(false, fileName[0]+" is available in the investor workspace");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+fileName[0]+"to "+fileName[1]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+fileName[0]+"to "+fileName[1]);
						}
				}else {
					appLog.error("file is not updated: "+fileName[0]+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName[0]+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace: "+fileName[0]);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace: "+fileName[0]);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in investor workspace: "+fileName[0]+"to "+fileName[1]);
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in investor workspace: "+fileName[0]+"to "+fileName[1]);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc052_2_verifyDiffNameUpdateDocInternalFolderAlertInINVAtInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String[] fileName=ExcelUtils.readData("FilePath",0,10,"M3tc052_1_updateDiffNameDocInINVInternalFolder").split(",");
		if(click(driver,ivp.getAllDocumentsTab(60),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName[1], M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.info(fileName[1]+"is available in the all documents alert grid");
				sa.assertTrue(false, fileName[1]+"is available in the all documents alert grid");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(60),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName[1], M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.info(fileName[1]+"is available in the recent activities alert grid");
				sa.assertTrue(false, fileName[1]+"is available in the recent activities alert grid");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName[1],Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.info(fileName[1]+"is available in all firms alerts grid");
				sa.assertTrue(false, fileName[1]+"is available in all firms alerts grid");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc053_1_updateDocViaManageVersionInINVSTDFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
		String[] fileName=ExcelUtils.readData("FilePath",0,12,currentlyExecutingTC).split(",");
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module3\\FileToUploadCRMSide\\FilesToUpdateCRMSide\\InvertorWorkspace\\Standard1\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.updateFile(folderpath, fileName[0], M3Institution1, M3LimitedPartner1, FolderType.Standard,docPath+fileName[1], null, null, ContentGridArrowKeyFunctions.ManageVersions,20, PageName.FundsPage,"Yes",null, Workspace.InvestorWorkspace)) {
					appLog.info("file is updated successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName[1],fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[1]+" is verified.");
								int alertCount=Integer.parseInt(Alert_Count)+1;
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
							result.clear();
							result=compareMultipleList(driver,fileName[0],fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(true, result.get(i));
								}
							}else {
								appLog.info(fileName[0]+" is available in the investor workspace");
								sa.assertTrue(false, fileName[0]+" is available in the investor workspace");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify updated document: "+fileName[0]+"to "+fileName[1]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated document: "+fileName[0]+"to "+fileName[1]);
						}
				}else {
					appLog.error("file is not updated: "+fileName[0]+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName[0]+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace: "+fileName[0]);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace: "+fileName[0]);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in investor workspace: "+fileName[0]+"to "+fileName[1]);
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in investor workspace: "+fileName[0]+"to "+fileName[1]);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc053_2_verifyUpdateDocViaManageVersionAlertForSTDInINVAtInvestorSide(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String[] fileName=ExcelUtils.readData("FilePath",0,12,"M3tc053_1_updateDocViaManageVersionInINVSTDFolder").split(",");
		if(click(driver,ivp.getAllDocumentsTab(60),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName[1], M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(60),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName[1], M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName[1],Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc054_1_updateDocViaManageVersionInINVCommonFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
		String[] fileName=ExcelUtils.readData("FilePath",0,9,currentlyExecutingTC).split(",");
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module3\\FileToUploadCRMSide\\FilesToUpdateCRMSide\\InvertorWorkspace\\Common1\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.updateFile(folderpath, fileName[0], null, null, FolderType.Common,docPath+fileName[1], null, null, ContentGridArrowKeyFunctions.ManageVersions,20, PageName.FundsPage,"Yes",null, Workspace.InvestorWorkspace)) {
					appLog.info("file is updated successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName[1],fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[1]+" is verified.");
								int alertCount=Integer.parseInt(Alert_Count)+1;
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
							result.clear();
							result=compareMultipleList(driver,fileName[0],fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(true, result.get(i));
								}
							}else {
								appLog.info(fileName[0]+" is available in the investor workspace");
								sa.assertTrue(false, fileName[0]+" is available in the investor workspace");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify updated document: "+fileName[0]+"to "+fileName[1]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated document: "+fileName[0]+"to "+fileName[1]);
						}
				}else {
					appLog.error("file is not updated: "+fileName[0]+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName[0]+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace: "+fileName[0]);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace: "+fileName[0]);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in investor workspace: "+fileName[0]+"to "+fileName[1]);
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in investor workspace: "+fileName[0]+"to "+fileName[1]);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc054_2_verifyUpdateDocViaManageVersionAlertForCommonInINVAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String[] fileName=ExcelUtils.readData("FilePath",0,9,"M3tc054_1_updateDocViaManageVersionInINVCommonFolder").split(",");
		if(click(driver,ivp.getAllDocumentsTab(60),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName[1], M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(60),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName[1], M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName[1],Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc055_1_updateDocViaManageVersionInINVSharedFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
		String[] fileName=ExcelUtils.readData("FilePath",0,11,currentlyExecutingTC).split(",");
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module3\\FileToUploadCRMSide\\FilesToUpdateCRMSide\\InvertorWorkspace\\Shared1\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.updateFile(folderpath, fileName[0], null, null, FolderType.Shared,docPath+fileName[1], null, null, ContentGridArrowKeyFunctions.ManageVersions,20, PageName.FundsPage,"Yes",null, Workspace.InvestorWorkspace)) {
					appLog.info("file is updated successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName[1],fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[1]+" is verified.");
								int alertCount=Integer.parseInt(Alert_Count)+1;
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
							result.clear();
							result=compareMultipleList(driver,fileName[0],fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(true, result.get(i));
								}
							}else {
								appLog.info(fileName[0]+" is available in the investor workspace");
								sa.assertTrue(false, fileName[0]+" is available in the investor workspace");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify updated document: "+fileName[0]+"to "+fileName[1]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated document: "+fileName[0]+"to "+fileName[1]);
						}
				}else {
					appLog.error("file is not updated: "+fileName[0]+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName[0]+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace: "+fileName[0]);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace: "+fileName[0]);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in investor workspace: "+fileName[0]+"to "+fileName[1]);
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in investor workspace: "+fileName[0]+"to "+fileName[1]);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc055_2_verifyUpdateDocViaManageVersionAlertForSharedInINVAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String[] fileName=ExcelUtils.readData("FilePath",0,11,"M3tc055_1_updateDocViaManageVersionInINVSharedFolder").split(",");
		if(click(driver,ivp.getAllDocumentsTab(60),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName[1], M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(60),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName[1], M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName[1],Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc056_1_updateDocViaManageVersionInINVInternalFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,2,currentlyExecutingTC);
		String[] fileName=ExcelUtils.readData("FilePath",0,10,currentlyExecutingTC).split(",");
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module3\\FileToUploadCRMSide\\FilesToUpdateCRMSide\\InvertorWorkspace\\Internal1\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.updateFile(folderpath, fileName[0], null, null, FolderType.Internal,docPath+fileName[1], null, null, ContentGridArrowKeyFunctions.ManageVersions,20, PageName.FundsPage,"Yes",null, Workspace.InvestorWorkspace)) {
					appLog.info("file is updated successfully: "+fileName+" in :"+folderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,fileName[1],fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(fileName[1]+" is verified.");
								appLog.info("alert count should be at investor side: "+Alert_Count);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
							result.clear();
							result=compareMultipleList(driver,fileName[0],fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(true, result.get(i));
								}
							}else {
								appLog.info(fileName[0]+" is available in the investor workspace");
								sa.assertTrue(false, fileName[0]+" is available in the investor workspace");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify updated document: "+fileName[0]+"to "+fileName[1]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated document: "+fileName[0]+"to "+fileName[1]);
						}
				}else {
					appLog.error("file is not updated: "+fileName[0]+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName[0]+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace: "+fileName[0]);
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace: "+fileName[0]);
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in investor workspace: "+fileName[0]+"to "+fileName[1]);
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in investor workspace: "+fileName[0]+"to "+fileName[1]);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc056_2_verifyUpdateDocViaManageVersionAlertForInternalInINVAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String[] fileName=ExcelUtils.readData("FilePath",0,10,"M3tc056_1_updateDocViaManageVersionInINVInternalFolder").split(",");
		if(click(driver,ivp.getAllDocumentsTab(60),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName[1], M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.info(fileName[1]+" is available in all doucments alert grid");
				sa.assertTrue(false, fileName[1]+" is available in all doucments alert grid");
			}
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(60),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(fileName[1], M3FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.info(fileName[1]+" is available in the recent document alert grid");
				sa.assertTrue(false, fileName[1]+" is available in the recent document alert grid");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(fileName[1],Org1FirmName, M3FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.info(fileName[1]+" is available in the all firms alert grid");
				sa.assertTrue(false, fileName[1]+" is available in the all firms alert grid");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc057_1_updateMyProfileAtCRMSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		String fristName=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Updated_FirstName);
		String LastName=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Updated_LastName);
		String title=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Title);
		String phone=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Phone);
		String MailingStreet=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Mailing_Street);
		String MailingCity=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Mailing_City);
		String MailingCounty=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Mailing_Country);
		String MailingState=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Mailing_State);
		String MailingZip=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Mailing_Zip);
		String facebook =ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Facebook);
		String linkedin =ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Linkedin);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30,bp.getFrame( PageName.NavatarInvestorManager, 30));
			if(nim.clickOnSideMenusTab(sideMenu.Profiles)) {
				if(nim.clickOnEditIcon()) {
					if(sendKeys(driver,nim.getMyProfileFirstName(60),fristName,"first name text box", action.BOOLEAN)) {
						appLog.info("Passed value in frist name text box: "+fristName);
						if(sendKeys(driver, nim.getMyProfileLastName(30), LastName, "last name text box", action.BOOLEAN)) {
							appLog.info("Passed value in last name text box: "+LastName);
							if(sendKeys(driver, nim.getMyProfileTitle(30), title, "title text box", action.BOOLEAN)) {
								appLog.info("Passed value in title text box: "+title);
								if(sendKeys(driver, nim.getMyProfilePhone(30), phone, "phone text box", action.BOOLEAN)) {
									appLog.info("passed value in phone text box: "+phone);
									if(sendKeys(driver, nim.getMyProfileMailingStreet(30), MailingStreet, "mailing street text box", action.SCROLLANDBOOLEAN)) {
										appLog.info("Passed Value in mailing street text box: "+MailingStreet);
										if(sendKeys(driver, nim.getMyprofileMailingcity(30), MailingCity, "mailing city text box", action.SCROLLANDBOOLEAN)) {
											appLog.info("Passed value in mailing city in text box : "+MailingCity);
											if(sendKeys(driver, nim.getMyProfilemailingState(30), MailingState, "mailing state", action.SCROLLANDBOOLEAN)) {
												appLog.info("Passed value in mailing state text box: "+MailingState);
												if(sendKeys(driver, nim.getMyProfilemailingZip(30), MailingZip, "mailing zip text box", action.SCROLLANDBOOLEAN)) {
													appLog.info("Passed vlaue in mailing zip text box: "+MailingZip);
													if(sendKeys(driver, nim.getMyProfileMailingCountry(30), MailingCounty, "mailing country text box", action.SCROLLANDBOOLEAN)) {
														appLog.info("Passed vlaue in mailing country text box: "+MailingCounty);
														if(sendKeys(driver, nim.getMyProfileFaceBookTextBox(30), facebook, "facebook text box", action.SCROLLANDBOOLEAN)) {
															appLog.info("Passed vlaue in facebook text box: "+facebook);
															if(sendKeys(driver, nim.getMyProfileLinkeDinTextBox(30), linkedin, "linkedin text box", action.SCROLLANDBOOLEAN)) {
																appLog.info("Passed vlaue in facebook text box: "+linkedin);
																if(click(driver, nim.getMyProfileSaveButton(30), "my profile save button", action.SCROLLANDBOOLEAN)) {
																	appLog.info("clicked on save button");
																	int alertCount=Integer.parseInt(Alert_Count)+1;
																	ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
																	appLog.info("alert count should be at investor side: "+alertCount);
																	appLog.info("All document count should be at investor side: "+AllDocument_count);
																	
																}else {
																	appLog.error("Not able to click on my profile save button so cannot update my profile");
																	sa.assertTrue(false, "Not able to click on my profile save button so cannot update my profile");
																}
															}else {
																appLog.error("Not able to pass value in linkedin text box: "+linkedin+" so cannot update my profile");
																sa.assertTrue(false, "Not able to pass value in linkedin text box: "+linkedin+" so cannot update my profile");
															}
														}else {
															appLog.error("Not able to pass value in facebook text box: "+facebook+" so cannot update my profile");
															sa.assertTrue(false, "Not able to pass value in facebook text box: "+facebook+" so cannot update my profile");
														}
														
													}else {
														appLog.error("Not able to pass value in mailing country text box: "+MailingCounty+" so cannot update my profile");
														sa.assertTrue(false, "Not able to pass value in mailing country text box: "+MailingCounty+" so cannot update my profile");
													}
												}else {
													appLog.error("Not able to pass value in mailing zip text box: "+MailingZip+" so cannot update my profile");
													sa.assertTrue(false, "Not able to pass value in mailing zip text box: "+MailingZip+" so cannot update my profile");
												}
											}else {
												appLog.error("Not able to pass value in mailing state text box: "+MailingState+" so cannot update my profile");
												sa.assertTrue(false, "Not able to pass value in mailing state text box: "+MailingState+" so cannot update my profile");
											}
										}else {
											appLog.error("Not able to pass value in mailing city text box: "+MailingCity+" so cannot update my profile");
											sa.assertTrue(false, "Not able to pass value in mailing city text box: "+MailingCity+" so cannot update my profile");
										}
									}else {
										appLog.error("Not able to pass value in mailing street text box: "+MailingStreet+" so cannot update my profile");
										sa.assertTrue(false, "Not able to pass value in mailing street text box: "+MailingStreet+" so cannot update my profile");
									}
								}else {
									appLog.error("Not able to pass value in phone text box: "+phone+" so cannot update my profile");
									sa.assertTrue(false, "Not able to pass value in phone text box: "+phone+" so cannot update my profile");
								}
							}else {
								appLog.error("Not able to pass value in title text box: "+title+" so cannot update my profile");
								sa.assertTrue(false, "Not able to pass value in title text box: "+title+" so cannot update my profile");
							}
						}else {
							appLog.error("Not able to pass value in last name text box: "+LastName+" so cannot update my profile");
							sa.assertTrue(false, "Not able to pass value in last name text box: "+LastName+" so cannot update my profile");
						}
					}else {
						appLog.error("Not able to pass value in frist name text box: "+fristName+" so cannot update my profile");
						sa.assertTrue(false, "Not able to pass value in frist name text box: "+fristName+" so cannot update my profile");
					}
					
				}else {
					appLog.error("Not able to click on Edit Icon so cannot update my profile");
					sa.assertTrue(false, "Not able to click on Edit Icon so cannot update my profile");
				}
			}else {
				appLog.error("Not able to click on profile tab so cannot update my profile");
				sa.assertTrue(false, "Not able to click on profile tab so cannot update my profile");
			}
		}else {
			appLog.error("Not able to click on NIM tab so cannot update my profile.");
			sa.assertTrue(false, "Not able to click on NIM tab so cannot update my profile.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc057_2_updateMyFirmProfile() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		String firmName=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Firm_Name);
		String firmContact=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Firm_Contact);
		String website=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.WebSite);
		String MailingStreet=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Mailing_Street);
		String MailingCity=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Mailing_City);
		String MailingCounty=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Mailing_Country);
		String MailingState=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Mailing_State);
		String MailingZip=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Mailing_Zip);
		String AUM=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.AUM);
		String fundType=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.FundType_selectbox);
		String Industries=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Industries_selectbox);
		String GeoFocus=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Geofocus_selectbox);
		String description=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Firm_Description_Firmprofile);
		String phone=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Phone);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if(bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30,bp.getFrame( PageName.NavatarInvestorManager, 30));
			if(nim.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
				if(nim.clickOnEditIcon()) {
					if(sendKeys(driver,nim.getMyFirmProfileNameTextBox(60), firmName+"Updated", "my firm profile name", action.SCROLLANDBOOLEAN)) {
						ExcelUtils.writeData(firmName+"Updated", "Users",excelLabel.Variable_Name,"AdminUser", excelLabel.Updated_FirmName);
						ExcelUtils.writeData(firmName+"Updated", "Users",excelLabel.Variable_Name,"AdminUser", excelLabel.Firm_Name);
						appLog.info("Passed value in firm name text box: "+firmName+" Updated");
						if(sendKeys(driver, nim.getMyFirmProfileFirmContact(60), firmContact,"firm contact text box", action.BOOLEAN)) {
							appLog.info("Passed value in firm contact text box: "+firmContact);
							if(sendKeys(driver,nim.getMyFirmProfileWebsite(30), website,"website text box", action.SCROLLANDBOOLEAN)) {
								appLog.info("Passed value in website text box: "+website);
								if(sendKeys(driver, nim.getMyFirmPrpofileMailingStreet(30), MailingStreet, "mailing street text box", action.SCROLLANDBOOLEAN)) {
									appLog.info("Passed Value in mailing street text box: "+MailingStreet);
									if(sendKeys(driver, nim.getMyFirmProfileMailingCity(30), MailingCity, "mailing city text box", action.SCROLLANDBOOLEAN)) {
										appLog.info("Passed value in mailing city in text box : "+MailingCity);
										if(sendKeys(driver, nim.getMyFirmProfileMailingState(30), MailingState, "mailing state", action.SCROLLANDBOOLEAN)) {
											appLog.info("Passed value in mailing state text box: "+MailingState);
											if(sendKeys(driver, nim.getMyFirmProfileMailingzip(30), MailingZip, "mailing zip text box", action.SCROLLANDBOOLEAN)) {
												appLog.info("Passed vlaue in mailing zip text box: "+MailingZip);
												if(sendKeys(driver, nim.getMyFirmprofileMailingCountry(30), MailingCounty, "mailing country text box", action.SCROLLANDBOOLEAN)) {
													if(sendKeys(driver, nim.getMyFirmProfilePhone(30), phone, "my firm profile phone text box", action.SCROLLANDBOOLEAN)) {
														appLog.info("Passed vlaue in phone text box: "+AUM);
													}else {
														appLog.error("Not able to pass value in  phone text box: "+AUM+" so cannot update phone text box");
														sa.assertTrue(false, "Not able to pass value in phone text box: "+AUM+" so cannot update phone text box");
													}
													if(sendKeys(driver, nim.getMyFirmProfileAUMTextBox(30), AUM, "my firm profile AUM text box", action.SCROLLANDBOOLEAN)) {
														appLog.info("Passed vlaue in AUM text box: "+AUM);
													}else {
														appLog.error("Not able to pass value in  AUM text box: "+AUM+" so cannot update AUM text box");
														sa.assertTrue(false, "Not able to pass value in AUM text box: "+AUM+" so cannot update AUM text box");
													}
													if(selectVisibleTextFromDropDown(driver, nim.getMyFirmProfileAvailableTypesDropDownList(30), "my firm profile types select box",fundType)) {
														if(click(driver, nim.getMyFirmProfileTypesAddBtn(30),"my firm profile types add button", action.SCROLLANDBOOLEAN)) {
															appLog.info("clicke on types select box add button");
														}else {
															appLog.error("Not able to click on types add button so cannot update types");
															sa.assertTrue(false, "Not able to click on types add button so cannot update types");
														}
													}else {
														appLog.error("Not able to select value from types select box: "+fundType+" so cannot update my firm prfile");
														sa.assertTrue(false, "Not able to select value from types select box: "+fundType+" so cannot update my firm prfile");
													}
													if(selectVisibleTextFromDropDown(driver, nim.getMyFirmProfileAvailableIndustriesDropDownList(30), "my firm profile industries select box",Industries)) {
														if(click(driver, nim.getMyFirmProfileIndustriesAddBtn(30),"my firm profile industries add button", action.SCROLLANDBOOLEAN)) {
															appLog.info("clicke on industries select box add button");
														}else {
															appLog.error("Not able to click on industries add button so cannot update industries");
															sa.assertTrue(false, "Not able to click on industries add button so cannot update industries");
														}
													}else {
														appLog.error("Not able to select value from industries select box: "+Industries+" so cannot update my firm prfile");
														sa.assertTrue(false, "Not able to select value from industries select box: "+Industries+" so cannot update my firm prfile");
													}
													if(selectVisibleTextFromDropDown(driver, nim.getMyFirmProfileAvailableGeoFocusDropDownList(30), "my firm profile geo focus select box",GeoFocus)) {
														if(click(driver, nim.getMyFirmProfileGeoFocusAddBtn(20),"my firm profile geo focus add button", action.SCROLLANDBOOLEAN)) {
															appLog.info("clicke on geo focus select box add button");
														}else {
															appLog.error("Not able to click on geo focus add button so cannot update geo focus");
															sa.assertTrue(false, "Not able to click on geo focus add button so cannot update geo focus");
														}
													}else {
														appLog.error("Not able to select value from industries select box: "+Industries+" so cannot update my firm prfile");
														sa.assertTrue(false, "Not able to select value from industries select box: "+Industries+" so cannot update my firm prfile");
													}
													if(sendKeys(driver, nim.getMyFirmProfileDescriptionTextArea(30), description, "my firm profile description text area", action.SCROLLANDBOOLEAN)) {
														appLog.info("Passed vlaue in description text area: "+description);
													}else {
														appLog.error("Not able to pass value in  description text area: "+MailingZip+" so cannot update description text area");
														sa.assertTrue(false, "Not able to pass value in description text area: "+MailingZip+" so cannot update description text area");
													}
													if(click(driver, nim.getMyFirmProfilesaveBtn(30), "my firm profile save button", action.SCROLLANDBOOLEAN)) {
														appLog.info("clicked on save button");
														int alertCount=Integer.parseInt(Alert_Count)+1;
														ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
														appLog.info("alert count should be at investor side: "+alertCount);
														appLog.info("All document count should be at investor side: "+AllDocument_count);
													}else {
														appLog.error("Not able to click on my firm  profile save button so cannot update my profile");
														sa.assertTrue(false, "Not able to click on my firm  profile save button so cannot update my profile");
													}
												}else {
													appLog.error("Not able to pass value in mailing country text box: "+MailingZip+" so cannot update my firm profile");
													sa.assertTrue(false, "Not able to pass value in mailing country text box: "+MailingZip+" so cannot update my firm profile");
												}
												
											}else {
												appLog.error("Not able to pass value in mailing zip text box: "+MailingZip+" so cannot update my firm profile");
												sa.assertTrue(false, "Not able to pass value in mailing zip text box: "+MailingZip+" so cannot update my firm profile");
											}
										}else {
											appLog.error("Not able to pass value in mailing state text box: "+MailingState+" so cannot update my firm profile");
											sa.assertTrue(false, "Not able to pass value in mailing state text box: "+MailingState+" so cannot update my firm profile");
										}
									}else {
										appLog.error("Not able to pass value in mailing city text box: "+MailingCity+" so cannot update my firm profile");
										sa.assertTrue(false, "Not able to pass value in mailing city text box: "+MailingCity+" so cannot update my firm profile");
									}
								}else {
									appLog.error("Not able to pass value in mailing street text box: "+MailingStreet+" so cannot update my firm profile");
									sa.assertTrue(false, "Not able to pass value in mailing street text box: "+MailingStreet+" so cannot update my firm profile");
								}
							}else {
								appLog.error("Not able to pass value in website text box: "+firmContact+" so cannot update my firm profile");
								sa.assertTrue(false, "Not able to pass value in website text box: "+firmContact+" so cannot update my firm profile");
							}
						}else {
							appLog.error("Not able to pass value in firm contact text box: "+firmContact+" so cannot update my firm profile");
							sa.assertTrue(false, "Not able to pass value in firm contact text box: "+firmContact+" so cannot update my firm profile");
						}
					}else {
						appLog.error("Not able to pass value in firm name text box: "+firmName+" Updated so cannot update my firm profile");
						sa.assertTrue(false, "Not able to pass value in firm name text box: "+firmName+" Updated so cannot update my firm profile");
					}
			}else {
				appLog.error("Not able to click on Edit Icon so cannot update my firm profile");
				sa.assertTrue(false, "Not able to click on Edit Icon so cannot update my firm profile");
			}
		}else {
			appLog.error("Not able to click on my firm profile tab so cannot update my firm profile");
			sa.assertTrue(false, "Not able to click on my firm profile tab so cannot update my  firm profile");
		}
	}else {
		appLog.error("Not able to click on NIM tab so cannot update my firm profile.");
		sa.assertTrue(false, "Not able to click on NIM tab so cannot update my  firm profile.");
	}
	switchToDefaultContent(driver);
	lp.CRMlogout();
	sa.assertAll();
}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc057_3_verifyProfileAlertsAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getAllDocumentsTab(60),"all document tab", action.BOOLEAN)) {
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(60),"recent activity tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(null, null,null, TabName.RecentActivities,activityType.FirmProfileUpdate,PageName.InvestorFirmPage);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("Firm Profile Update alert is verified Successfully on recent activities alert grid");
			}
			result=ivp.verifyAlertOnRecentAllDocumentGrid(null, null,null, TabName.RecentActivities,activityType.ContactProfileUpdate,PageName.InvestorFirmPage);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("Contact Profile Update alert is verified Successfully on recent activities alert grid");
			}
			
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(null,null, null,null, activityType.FirmProfileUpdate,PageName.AllFirmsPage);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("firm profile update alert is successfully verified on all firms alert grid");
			}
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(null,null, null,null, activityType.ContactProfileUpdate,PageName.AllFirmsPage);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("Contact profile update alert is successfully verified on all firms alert grid");
			}
			
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc058_inviteM3Contact2FromBothWrokSpace(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String sharedfolderpath=ExcelUtils.readData("FilePath",0, 3, currentlyExecutingTC);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.inviteContact(environment,mode,M3Institution1, M3Contact2EmailId,null, FolderType.Standard,"Upload","Yes", "Yes","All Folders", Workspace.FundraisingWorkspace, M3Contact2EmailId)) {
					appLog.info("contact is invites from fundraising workspace successfully: "+M3Contact2FirstName+" "+M3Contact2LastName);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
				}
				if(fp.inviteContact(environment,mode,null , M3Contact2EmailId,sharedfolderpath, FolderType.Shared,null,"Yes", null,null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("contact is invites successfully from fundraising workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
				}
				
				if(fp.inviteContact(environment,mode,M3Institution1, M3Contact2EmailId,null, FolderType.Standard,"Upload","Yes", null,null, Workspace.InvestorWorkspace, M3Contact2EmailId)) {
					appLog.info("contact is invites from investor workspace successfully: "+M3Contact2FirstName+" "+M3Contact2LastName);
				}else {
					appLog.error("Not able to invite contact from investor workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
					sa.assertTrue(false, "Not able to invite contact from investor workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
				}
				
				if(fp.inviteContact(environment,mode,null , M3Contact2EmailId,sharedfolderpath, FolderType.Shared,null,"Yes", null,null, Workspace.InvestorWorkspace, null)) {
					appLog.info("contact is invites successfully from investor workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
				}else {
					appLog.error("Not able to invite contact from investor workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
					sa.assertTrue(false, "Not able to invite contact from investor workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
				}
				
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact from  fund: "+M3FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact from fund: "+M3FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact from fund: "+M3FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact from: "+M3FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc059_registerM3Contact2(){
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		if (ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M3Contact2", excelLabel.Registered).equalsIgnoreCase("Not Registered")) {
			try {
				investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail",gmailUserName,gmailPassword,CRMUser1EmailID,M3Contact2EmailId);
				appLog.info("Investor Registration Link: " + investorRegLink);
			} catch (InterruptedException e) {
				appLog.info("Invitation mail not found.");
				e.printStackTrace();
			}
			if (investorRegLink != null) {
				driver.get(investorRegLink);
				if (bp.targetRegistration(M3Contact2FirstName, M3Contact2LastName, M3Contact2EmailId, M3Institution2,
						adminPassword)) {
					appLog.info(
							"Investor is registered successfully: " + M3Contact2FirstName + " " + M3Contact2LastName);
					ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M3Contact2", excelLabel.Registered);
				} else {
					driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
					appLog.info("Register Target URL through Direct URL..");
					if (bp.targetRegistration(M3Contact2FirstName, M3Contact2LastName, M3Contact2EmailId,
							M3Institution2, adminPassword)) {
						appLog.info("Investor is registered successfully: " + M3Contact2FirstName + " "
								+ M3Contact2LastName);
						ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M3Contact2", excelLabel.Registered);
						sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M3Contact2FirstName
								+ " " + M3Contact2LastName);
					} else {
						appLog.error("Not able to registered investor through direct URL:" + M3Contact2FirstName + " "
								+ M3Contact2LastName);
						sa.assertTrue(false, "Not able to registered investor through direct URL:" + M3Contact2FirstName
								+ " " + M3Contact2LastName);
					}
				}
			} else {
				driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
				appLog.info("Register Target URL through Direct URL..");
				if (bp.targetRegistration(M3Contact2FirstName, M3Contact2LastName, M3Contact2EmailId,
						M3Institution2, adminPassword)) {
					appLog.info("Investor is registered successfully: " + M3Contact2FirstName + " "
							+ M3Contact2LastName);
					ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M3Contact2", excelLabel.Registered);
					sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M3Contact2FirstName
							+ " " + M3Contact2LastName);
				} else {
					appLog.error("Not able to registered investor through direct URL:" + M3Contact2FirstName + " "
							+ M3Contact2LastName);
					sa.assertTrue(false, "Not able to registered investor through direct URL:" + M3Contact2FirstName
							+ " " + M3Contact2LastName);
				}
			}
			if(click(driver,ivp.getAllDocumentsTab(60),"all document tab", action.BOOLEAN)) {
				if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
					appLog.info("All documents alert count is matched.");
				}else {
					appLog.error("All documents alert count is not matched");
					sa.assertTrue(false, "All documents alert count is not matched");
				}
			}else {
				appLog.error("Not able to click on all document tab so cannot verify all document alerts");
				sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
			}
				if(click(driver,ivp.getRecentActivitiesTab(60),"recent activity tab", action.BOOLEAN)) {
					if(ivp.getRecentActivitiesAlertNoDataToDisplayErrorMsg(30)!=null) {
						String aa1=ivp.getRecentActivitiesAlertNoDataToDisplayErrorMsg(30).getText().trim();
						if(aa1.equalsIgnoreCase(InvestorFirmPageErrorMessage.NodatatoDisplayErrorMsg)) {
							appLog.info(InvestorFirmPageErrorMessage.NodatatoDisplayErrorMsg+" is verified on recent activities alert grid");
						}else {
							appLog.error(InvestorFirmPageErrorMessage.NodatatoDisplayErrorMsg+" is not verified on recent activities alert grid.");
							sa.assertTrue(false, InvestorFirmPageErrorMessage.NodatatoDisplayErrorMsg+" is not verified on recent activities alert grid.");
						}
					}
					
				}else {
					appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
					sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
				}
				if(allfp.selectFirmName("All Firms")) {
					ThreadSleep(5000);
					if(allfp.getActivityAlertNoDatatoDisplayErrorMsg(30)!=null) {
						String aa1=allfp.getActivityAlertNoDatatoDisplayErrorMsg(30).getText().trim();
						if(aa1.equalsIgnoreCase(AllFirmsErrorMessage.NodatatoDisplayErrorMsg)) {
							appLog.info(AllFirmsErrorMessage.NodatatoDisplayErrorMsg+" is verified on recent activities alert grid");
						}else {
							appLog.error(AllFirmsErrorMessage.NodatatoDisplayErrorMsg+" is not verified on recent activities alert grid.");
							sa.assertTrue(false, InvestorFirmPageErrorMessage.NodatatoDisplayErrorMsg+" is not verified on recent activities alert grid.");
						}
					}
					if(allfp.selectFirmName(Org1UpdatedFirmName)) {
						ThreadSleep(5000);
						if(click(driver,ivp.getRecentActivitiesTab(60),"recent activity tab", action.BOOLEAN)) {
							appLog.error("clicked on recent activity tab");
						}else {
							appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
							sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
						}
					}
			}else {
				appLog.error("Not able to select all firms from drop down list so cannot verify all firms alert grid");
				sa.assertTrue(false, "Not able to select all firms from drop down list so cannot verify all firms alert grid");
			}		
				lp.investorLogout();
				ThreadSleep(3000);
				lp.investorLogin(M3Contact1EmailId, adminPassword);
				if(ivp.getRecentActivitiesTab(60)!=null) {
					appLog.info("recent activitie tab is visible for investor: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}else {
					appLog.error("recent activitie tab is not visible for investor: "+M3Contact1FirstName+" "+M3Contact1LastName);
					sa.assertTrue(false, "recent activitie tab is not visible for investor: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}
		} else {
			appLog.info("investor "+M3Contact2FirstName+" "+M3Contact2LastName+" is already Registered.");
			sa.assertTrue(false, "investor "+M3Contact2FirstName+" "+M3Contact2LastName+" is already Registered.");
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc060_buildFund2WorkSpace() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName2)) {
				String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund2", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund2", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund2", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund2", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund2", excelLabel.Fund_Email);
				String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund2", excelLabel.Fund_Description);
				String templateName=ExcelUtils.readData("Foldertemp", 0, 1);
				String[] data= {Size,vintageyear,contact,phone,email,description};
				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,M3Institution1+"<break>"+M3Institution2, Workspace.FundraisingWorkspace,60)) {
					appLog.info("FundRaising work is build successfully.");
				}else {
					appLog.error("Not able to bulid fundraising workspace on fund: "+M3FundName2);
					sa.assertTrue(false, "Not able to bulid fundraising workspace on fund: "+M3FundName2);
				}
				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,M3Institution1+"/"+M3LimitedPartner1+"<break>"+M3Institution2+"/"+M3LimitedPartner2, Workspace.InvestorWorkspace,60)) {
					appLog.info("Investor work is build successfully.");
				}else {
					appLog.error("Not able to bulid Investor workspace on fund: "+M3FundName2);
					sa.assertTrue(false, "Not able to bulid Investor workspace on fund: "+M3FundName2);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot build workspace: "+M3FundName2);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build workspace: "+M3FundName2);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build workspace: "+M3FundName2);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build workspace: "+M3FundName2);
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc061_inviteM3Contact1FromFund2(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String sharedfolderpath=ExcelUtils.readData("FilePath",0, 3, currentlyExecutingTC);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName2)) {
				if(fp.inviteContact(environment,mode,M3Institution1, M3Contact2EmailId,null, FolderType.Standard,"Upload","Yes", null,"All Folders", Workspace.FundraisingWorkspace, M3Contact2EmailId)) {
					appLog.info("contact is invites from fundraising workspace successfully: "+M3Contact2FirstName+" "+M3Contact2LastName);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
				}
				if(fp.inviteContact(environment,mode,M3Institution1, M3Contact1EmailId,null, FolderType.Standard,"Upload","Yes", null,"All Folders", Workspace.FundraisingWorkspace, null)) {
					appLog.info("contact is invites from fundraising workspace successfully: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}
				if(fp.inviteContact(environment,mode,null , M3Contact2EmailId,sharedfolderpath, FolderType.Shared,null,"Yes", null,null, Workspace.FundraisingWorkspace, M3Contact2EmailId)) {
					appLog.info("contact is invites successfully from fundraising workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
				}
				if(fp.inviteContact(environment,mode,null , M3Contact1EmailId,sharedfolderpath, FolderType.Shared,null,"Yes", null,null, Workspace.FundraisingWorkspace, M3Contact1EmailId)) {
					appLog.info("contact is invites successfully from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}
				if(fp.inviteContact(environment,mode,M3Institution1, M3Contact2EmailId,null, FolderType.Standard,"Upload","Yes", null,null, Workspace.InvestorWorkspace, M3Contact2EmailId)) {
					appLog.info("contact is invites from investor workspace successfully: "+M3Contact2FirstName+" "+M3Contact2LastName);
				}else {
					appLog.error("Not able to invite contact from investor workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
					sa.assertTrue(false, "Not able to invite contact from investor workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
				}
				if(fp.inviteContact(environment,mode,M3Institution1, M3Contact1EmailId,null, FolderType.Standard,"Upload","Yes", null,null, Workspace.InvestorWorkspace, null)) {
					appLog.info("contact is invites from investor workspace successfully: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}else {
					appLog.error("Not able to invite contact from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}
				if(fp.inviteContact(environment,mode,null , M3Contact2EmailId,sharedfolderpath, FolderType.Shared,null,"Yes", null,null, Workspace.InvestorWorkspace, M3Contact2EmailId)) {
					appLog.info("contact is invites successfully from investor workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
				}else {
					appLog.error("Not able to invite contact from investor workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
					sa.assertTrue(false, "Not able to invite contact from investor workspace: "+M3Contact2FirstName+" "+M3Contact2LastName);
				}
				if(fp.inviteContact(environment,mode,null , M3Contact1EmailId,sharedfolderpath, FolderType.Shared,null,"Yes", null,null, Workspace.InvestorWorkspace, M3Contact1EmailId)) {
					appLog.info("contact is invites successfully from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}else {
					appLog.error("Not able to invite contact from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}
				
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact from  fund: "+M3FundName2);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact from fund: "+M3FundName2);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact from fund: "+M3FundName2);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact from: "+M3FundName2);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc062_1_uploadDocInSTDInFWRforFund2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName2)) {
				String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\M3Fund2\\FWR\\Standard1";
				if(fp.uploadFile(folderpath,M3Institution1, docpath,null,UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								List<String> filecount=createListOutOfString(filesName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allDocCount=Integer.parseInt(AllDocument_count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+allDocCount);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot upload files in fundraising and investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot upload files in fundraising and investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising and investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising and investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc062_2_uploadDocInSTDInINVforFund2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName2)) {
				String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\M3Fund2\\INV\\Standard1";				
				if(fp.uploadFile(folderpath,M3Institution1+"/"+M3LimitedPartner1, docpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								List<String> filecount=createListOutOfString(filesName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								int allDocCount=Integer.parseInt(AllDocument_count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+allDocCount);
							}
							
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot upload files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot upload files in investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc062_3_verifyUploadDocSTDFolderAlertAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String FWR_fileName=ExcelUtils.readData("FilePath",0,8, "M3tc062_1_uploadDocInSTDInFWRforFund2");
		String INV_fileName=ExcelUtils.readData("FilePath",0,8, "M3tc062_2_uploadDocInSTDInINVforFund2");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(FWR_fileName, M3FundName2, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched for potential investment successfully.");
			}
			
			result=ivp.verifyAlertOnRecentAllDocumentGrid(INV_fileName, M3FundName2, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All documents all alerts are matched for cuurent investment successfully.");
			}
			
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(FWR_fileName, M3FundName2, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched for potential investment successfully.");
			}
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(INV_fileName, M3FundName2, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("recent activities all alerts are matched for current investment successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(FWR_fileName,Org1FirmName, M3FundName2, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched for potential investment successfully.");
			}
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(INV_fileName,Org1FirmName, M3FundName2, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched for current investment successfully.");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc063_1_updateDocInSTDInFWRForFund2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from recent activities tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
		}else {
			appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
		}
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
			appLog.info("count successfully read from all documents tab: "+aa);
			ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
		}else {
			appLog.error("Not able to click on all documents tab so cannot read all documents count");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName2)) {
				String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\M3Fund2\\FWR\\UploadUpdateDoc\\Standard1";
				if(fp.uploadFile(folderpath,M3Institution1, docpath,UploadFileActions.Update,UploadFileActions.Update, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is updated successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileStandard);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								List<String> filecount=createListOutOfString(filesName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify updated documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot updated upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName2+" so cannot update files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName2+" so cannot update files in fundraising workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc063_2_updateDocInSTDInINVForFund2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName2)) {
				String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
				String docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\M3Fund2\\INV\\UploadUpdateDoc\\Standard1";
				
				if(fp.uploadFile(folderpath,M3Institution1+"/"+M3LimitedPartner1, docpath,UploadFileActions.Update,UploadFileActions.Update, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is updated successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileStandard);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								List<String> filecount=createListOutOfString(filesName);
								int alertCount=Integer.parseInt(Alert_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+AllDocument_count);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify updated documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot updated upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot update files in investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc063_3_verifyUpdateDocInSTDFolderAlertAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String FWR_fileName=ExcelUtils.readData("FilePath",0,12, "M3tc063_1_updateDocInSTDInFWRForFund2");
		String INV_fileName=ExcelUtils.readData("FilePath",0,12, "M3tc063_2_updateDocInSTDInINVForFund2");
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
				appLog.info("All documents alert count is matched.");
			}else {
				appLog.error("All documents alert count is not matched");
				sa.assertTrue(false, "All documents alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result=ivp.verifyAlertOnRecentAllDocumentGrid(FWR_fileName, M3FundName2, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info(FWR_fileName+" are is visible in recent document alert grid");
			}
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(INV_fileName, M3FundName2, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info(INV_fileName+" is visible in recent document alert grid");
			}
			if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
				appLog.info("recent activities alert count is matched");
			}else {
				appLog.error("recent activities alert count is not matched");
				sa.assertTrue(false, "recent activities alert count is not matched");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(FWR_fileName,Org1FirmName, M3FundName2, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate , null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info(FWR_fileName+" is visible in all firms alerts grid");
			}
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(INV_fileName,Org1FirmName, M3FundName2, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate , null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info(INV_fileName+"is visible in all firms alerts grid");
			}
			if(ivp.verifyAlertCount(Alert_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc064_1_preConditionForOrg2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		NIMPageBusinessLayer nim= new NIMPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg2UserName,adminPasswordOrg2);
			if(bp.clickOnTab(TabName.InstituitonsTab)) {
				if(ip.createInstitution(M3Org2Institution1)) {
				
			}else {
				appLog.error("Not able to create institution: "+M3Org2Institution1);
				sa.assertTrue(false, "Not able to create institution: "+M3Org2Institution1);
			}
		}else {
				appLog.error("Not able to click on institution tab so cannot create institution: "+M3Org2Institution1);
				sa.assertTrue(false, "Not able to click on institution tab so cannot create institution: "+M3Org2Institution1);
			}
		for (int i = 0; i < 2; i++) {
			String emailId=null;
			if(i==1) {
				 emailId = cp.generateRandomEmailId();				
			}
			String ContactFirstName=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M3Org2Contact"+(i+1), excelLabel.Contact_FirstName);
			String ContactLastName=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M3Org2Contact"+(i+1), excelLabel.Contact_LastName);	
			if(bp.clickOnTab(TabName.ContactTab)){
				if(i==0) {
					emailId=M3Contact1EmailId;
				}
				if(cp.createContact(ContactFirstName, ContactLastName, M3Org2Institution1, emailId)) {
					appLog.info("contact is created: "+ContactFirstName+" "+ContactLastName);
					if (emailId != "") {
						ExcelUtils.writeData(emailId,"Contacts", excelLabel.Variable_Name, "M3Org2Contact"+(i+1),excelLabel.Contact_EmailId);
						if(i==0) {
							String aa=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M3Contact1", excelLabel.Registered);
							ExcelUtils.writeData(aa,"Contacts", excelLabel.Variable_Name, "M3Org2Contact"+(i+1),excelLabel.Registered);
						}
					}
				}else {
					appLog.error("Not able to create contact: "+ContactFirstName+" "+ContactLastName);
					sa.assertTrue(false, "Not able to create contact: "+ContactFirstName+" "+ContactLastName);
				}
			}
		}
			if(bp.clickOnTab(TabName.FundsTab)) {
				if(fp.createFund(M3Org2FundName1, M3Org2FundType, M3Org2FundInvestmentCategory)) {
					appLog.info("fund is created: "+M3Org2FundName1);
				}else {
					appLog.error("Not able to create fund: "+M3Org2FundName1);
					sa.assertTrue(false, "Not able to create fund: "+M3Org2FundName1);
				}
			}else {
				appLog.error("Not able to click on fund tab so cannot create fund: "+M3Org2FundName1);
				sa.assertTrue(false, "Not able to click on fund tab so cannot create fund: "+M3Org2FundName1);
			}
			if(bp.clickOnTab(TabName.FundraisingsTab)) {
				if(frp.createFundRaising(M3Org2FundRaisingName1, M3Org2FundName1, M3Org2Institution1)) {
					appLog.info("fundraising is created : "+M3Org2FundRaisingName1);
				}else {
					appLog.error("Not able to create fundraising: "+M3Org2FundRaisingName1);
					sa.assertTrue(false, "Not able to create fundraising: "+M3Org2FundRaisingName1);
				}
			}else {
				appLog.error("Not able to click on fundraising tab so cannot create fundraising: "+M3Org2FundRaisingName1);
				sa.assertTrue(false,"Not able to click on fundraising tab so cannot create fundraising: "+M3Org2FundRaisingName1);
			}
			if(bp.clickOnTab(TabName.InstituitonsTab)) {
				if(ip.createLimitedPartner(M3Org2LimitedPartner1, M3Org2Institution1)) {
					appLog.info("limited partner is created: "+M3Org2LimitedPartner1);
				}else {
					appLog.error("Not able to create limited partner: "+M3Org2LimitedPartner1);
					sa.assertTrue(false, "Not able to create limited partner: "+M3Org2LimitedPartner1);
				}
			}else {
				appLog.error("Not able to click on institution tab so cannot create limite partner: "+M3Org2LimitedPartner1);
				sa.assertTrue(false, "Not able to click on institution tab so cannot create limite partner: "+M3Org2LimitedPartner1);
			}
			if(bp.clickOnTab(TabName.PartnershipsTab)) {
				if(pp.createPartnership(M3Org2Partnership1,M3Org2FundName1)) {
					appLog.info("partnership is created: "+M3Org2Partnership1);
				}else {
					appLog.error("Not able to create partnership: "+M3Org2Partnership1);
					sa.assertTrue(false, "Not able to create partnership: "+M3Org2Partnership1);
				}
			}else {
				appLog.error("Not able to click on partnership tab so cannot create partnership: "+M3Org2Partnership1);
				sa.assertTrue(false, "Not able to click on partnership tab so cannot create partnership: "+M3Org2Partnership1);
			}
			if(bp.clickOnTab(TabName.CommitmentsTab)) {
				if(cmp.createCommitment(M3Org2LimitedPartner1,M3Org2Partnership1,"M3Org2Commitment1", null)) {
					appLog.info("commitment is created successfully");
				}else {
					appLog.error("Not able to create commitment for limited partner: "+M3Org2LimitedPartner1+" and partnership Name: "+M3Org2Partnership1);
					sa.assertTrue(false, "Not able to create commitment for limited partner: "+M3Org2LimitedPartner1+" and partnership Name: "+M3Org2Partnership1);
				}
			}else {
				appLog.error("Not able to click on commitment tab so cannot create committment for limite partner: "+M3Org2LimitedPartner1+" and partnership Name: "+M3Org2Partnership1);
				sa.assertTrue(false, "Not able to click on commitment tab so cannot create committment for limite partner: "+M3Org2LimitedPartner1+" and partnership Name: "+M3Org2Partnership1);
			}
			if(nim.getMyProfileFistNameAndLastNameAndFirmName("User1")) {
				appLog.info("CRM User1 first,last name and firm name successfully write in excel");
			}else {
				appLog.error("Not able to write CRM User1 first,last name and firm profile in excel");
				sa.assertTrue(false, "Not able to write CRM User1 first,last name and firm profile in excel");
			}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc064_2_buildFWRAndINVWorkSpaceInOrg2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(Org2CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3Org2FundName1)) {
				String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Org2Fund1", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Org2Fund1", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Org2Fund1", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Org2Fund1", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Org2Fund1", excelLabel.Fund_Email);
				String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Org2Fund1", excelLabel.Fund_Description);
				String templateName=ExcelUtils.readData("Foldertemp", 0, 1);
				String[] data= {Size,vintageyear,contact,phone,email,description};
				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,M3Org2Institution1, Workspace.FundraisingWorkspace,60)) {
					appLog.info("FundRaising work is build successfully.");
				}else {
					appLog.error("Not able to bulid fundraising workspace on fund: "+M3Org2FundName1);
					sa.assertTrue(false, "Not able to bulid fundraising workspace on fund: "+M3Org2FundName1);
				}
				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,M3Org2Institution1+"/"+M3Org2LimitedPartner1, Workspace.InvestorWorkspace,60)) {
					appLog.info("Investor work is build successfully.");
				}else {
					appLog.error("Not able to bulid Investor workspace on fund: "+M3Org2FundName1);
					sa.assertTrue(false, "Not able to bulid Investor workspace on fund: "+M3Org2FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot build workspace: "+M3Org2FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build workspace: "+M3Org2FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build workspace: "+M3Org2FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build workspace: "+M3Org2FundName1);
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc065_inviteContactFromOrg2(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(Org2CRMUser1EmailID,adminPassword);
		String sharedfolderpath=ExcelUtils.readData("FilePath",0, 3, currentlyExecutingTC);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3Org2FundName1)) {
				if(fp.inviteContact(environment,mode,M3Org2Institution1, M3Org2Contact1EmailId,null, FolderType.Standard,"Upload","Yes", null,"All Folders", Workspace.FundraisingWorkspace, null)) {
					appLog.info("contact is invites from fundraising workspace successfully: "+M3Org2Contact1FirstName+" "+M3Org2Contact1LastName);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M3Org2Contact1FirstName+" "+M3Org2Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M3Org2Contact1FirstName+" "+M3Org2Contact1LastName);
				}
				if(fp.inviteContact(environment,mode,null , M3Org2Contact1EmailId,sharedfolderpath, FolderType.Shared,null,"Yes", null,null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("contact is invites successfully from fundraising workspace: "+M3Org2Contact1FirstName+" "+M3Org2Contact1LastName);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M3Org2Contact1FirstName+" "+M3Org2Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M3Org2Contact1FirstName+" "+M3Org2Contact1LastName);
				}
				if(fp.inviteContact(environment,mode,M3Org2Institution1, M3Org2Contact1EmailId,null, FolderType.Standard,"Upload","Yes", null,null, Workspace.InvestorWorkspace, null)) {
					appLog.info("contact is invites from investor workspace successfully: "+M3Org2Contact1FirstName+" "+M3Org2Contact1LastName);
				}else {
					appLog.error("Not able to invite contact from investor workspace: "+M3Org2Contact1FirstName+" "+M3Org2Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact from investor workspace: "+M3Org2Contact1FirstName+" "+M3Org2Contact1LastName);
				}
				if(fp.inviteContact(environment,mode,null , M3Org2Contact1EmailId,sharedfolderpath, FolderType.Shared,null,"Yes", null,null, Workspace.InvestorWorkspace, null)) {
					appLog.info("contact is invites successfully from investor workspace: "+M3Org2Contact1FirstName+" "+M3Org2Contact1LastName);
				}else {
					appLog.error("Not able to invite contact from investor workspace: "+M3Org2Contact1FirstName+" "+M3Org2Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact from investor workspace: "+M3Org2Contact1FirstName+" "+M3Org2Contact1LastName);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact from  fund: "+M3Org2FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact from fund: "+M3Org2FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact from fund: "+M3Org2FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact from: "+M3Org2FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc066_1_uploadDocInSTDFolderInFWRAtCRMSideInOrg2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		AllFirmsPageBusinesslayer allfp = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M3Org2Contact1EmailId,adminPassword);
		if(allfp.selectFirmName(Org2FirmName)) {
			if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
				int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
				appLog.info("count successfully read from recent activities tab: "+aa);
				ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc066_1_uploadDocInSTDFolderInFWRAtCRMSideInOrg2", excelLabel.Alert_Count);
			}else {
				appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
				sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
			}
			if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
				int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
				appLog.info("count successfully read from all documents tab: "+aa);
				ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc066_1_uploadDocInSTDFolderInFWRAtCRMSideInOrg2", excelLabel.AllDocument_Count);	
			}else {
				appLog.error("Not able to click on all documents tab so cannot read all documents count");
				sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
			}
			if(allfp.selectFirmName("All Firms")) {
				int aa=ivp.getAlertCount(null, PageName.AllFirmsPage,60);
				appLog.info("count successfully read from all firms page: "+aa);
				ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
			}else {
				appLog.error("Not able to select All firms from drop down list so cannot read all firms count");
				sa.assertTrue(false, "Not able to select All firms from drop down list so cannot read all firms count");
			}
		}else {
			appLog.error("Org2 Firm name is not available in firm name drop down list. so cannot read alert count");
			sa.assertTrue(false, "Org2 Firm name is not available in firm name drop down list. so cannot read alert count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(Org2CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3Org2FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
				String FWR_docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\Org2FileUpload\\FWR\\Standard1";
				if(fp.uploadFile(folderpath,M3Org2Institution1, FWR_docpath,null,UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								List<String> filecount=createListOutOfString(filesName);
								int alertCount=Integer.parseInt(Org2Alert_Count)+filecount.size();
								int allDocCount=Integer.parseInt(Org2AllDocument_count)+filecount.size();
								int allfirmsCount=Integer.parseInt(Allfirms_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc066_1_uploadDocInSTDFolderInFWRAtCRMSideInOrg2", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc066_1_uploadDocInSTDFolderInFWRAtCRMSideInOrg2", excelLabel.AllDocument_Count);
								ExcelUtils.writeData(String.valueOf(allfirmsCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+allDocCount);
								appLog.info("All firms count should be at investor side: "+allfirmsCount);
								
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3Org2FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3Org2FundName1+" so cannot upload files in fundraising workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc066_2_uploadDocInSTDFolderInINVAtCRMSideInOrg2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(Org2CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3Org2FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
				String INV_docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\Org2FileUpload\\INV\\Standard1";
				if(fp.uploadFile(folderpath,M3Org2Institution1+"/"+M3Org2LimitedPartner1, INV_docpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								List<String> filecount=createListOutOfString(filesName);
								int alertCount=Integer.parseInt(Org2Alert_Count)+filecount.size();
								int allDocCount=Integer.parseInt(Org2AllDocument_count)+filecount.size();
								int allfirmsCount=Integer.parseInt(Allfirms_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc066_1_uploadDocInSTDFolderInFWRAtCRMSideInOrg2", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc066_1_uploadDocInSTDFolderInFWRAtCRMSideInOrg2", excelLabel.AllDocument_Count);
								ExcelUtils.writeData(String.valueOf(allfirmsCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+allDocCount);
								appLog.info("All firms count should be at investor side: "+allfirmsCount);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3Org2FundName1+" so cannot upload files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3Org2FundName1+" so cannot upload files in investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc066_3_verifyUploadDocAlertForSTDAtInvestorSideForOrg2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String FWR_fileName=ExcelUtils.readData("FilePath",0,8, "M3tc066_1_uploadDocInSTDFolderInFWRAtCRMSideInOrg2");
		String INV_fileName=ExcelUtils.readData("FilePath",0,8, "M3tc066_2_uploadDocInSTDFolderInINVAtCRMSideInOrg2");
		if(allfp.selectFirmName(Org2FirmName)) {
			if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
				result=ivp.verifyAlertOnRecentAllDocumentGrid(FWR_fileName, M3Org2FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments,null,null);			
				if(!result.isEmpty()) {
					for(int i=0; i<result.size();i++) {
						appLog.error(result.get(i));
						sa.assertTrue(false, result.get(i));
					}
				}else {
					appLog.info("All documents all alerts are matched for potential investment successfully.");
				}
				
				result=ivp.verifyAlertOnRecentAllDocumentGrid(INV_fileName, M3Org2FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments,null,null);			
				if(!result.isEmpty()) {
					for(int i=0; i<result.size();i++) {
						appLog.error(result.get(i));
						sa.assertTrue(false, result.get(i));
					}
				}else {
					appLog.info("All documents all alerts are matched for cuurent investment successfully.");
				}
				if(ivp.verifyAlertCount(Org2AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
					appLog.info("All documents alert count is matched.");
				}else {
					appLog.error("All documents alert count is not matched");
					sa.assertTrue(false, "All documents alert count is not matched");
				}
				
			}else {
				appLog.error("Not able to click on all document tab so cannot verify all document alerts");
				sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
			}
			if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
				result.clear();
				result=ivp.verifyAlertOnRecentAllDocumentGrid(FWR_fileName, M3Org2FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
				if(!result.isEmpty()) {
					for(int i=0; i<result.size();i++) {
						appLog.error(result.get(i));
						sa.assertTrue(false, result.get(i));
					}
				}else {
					appLog.info("recent activities all alerts are matched for potential investment successfully.");
				}
				result.clear();
				result=ivp.verifyAlertOnRecentAllDocumentGrid(INV_fileName, M3Org2FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpload,null);			
				if(!result.isEmpty()) {
					for(int i=0; i<result.size();i++) {
						appLog.error(result.get(i));
						sa.assertTrue(false, result.get(i));
					}
				}else {
					appLog.info("recent activities all alerts are matched for current investment successfully.");
				}
				if(ivp.verifyAlertCount(Org2Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
					appLog.info("recent activities alert count is matched");
				}else {
					appLog.error("recent activities alert count is not matched");
					sa.assertTrue(false, "recent activities alert count is not matched");
				}
				
			}else {
				appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
				sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
			}
			
		}else {
			appLog.error("Org2 Firm name is not available in firm name drop down list. so cannot read alert count");
			sa.assertTrue(false, "Org2 Firm name is not available in firm name drop down list. so cannot read alert count");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(FWR_fileName,Org2FirmName, M3Org2FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched for potential investment successfully.");
			}
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(INV_fileName,Org2FirmName, M3Org2FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpload, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms alerts are matched for current investment successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc067_1_updateDocInSTDFolderInFWRAtCRMSideInOrg2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		AllFirmsPageBusinesslayer allfp = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M3Org2Contact1EmailId,adminPassword);
		if(allfp.selectFirmName(Org2FirmName)) {
			if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
				int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
				appLog.info("count successfully read from recent activities tab: "+aa);
				ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc066_1_uploadDocInSTDFolderInFWRAtCRMSideInOrg2", excelLabel.Alert_Count);
			}else {
				appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
				sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
			}
			if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
				int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
				appLog.info("count successfully read from all documents tab: "+aa);
				ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc066_1_uploadDocInSTDFolderInFWRAtCRMSideInOrg2", excelLabel.AllDocument_Count);	
			}else {
				appLog.error("Not able to click on all documents tab so cannot read all documents count");
				sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
			}
			if(allfp.selectFirmName("All Firms")) {
				int aa=ivp.getAlertCount(null, PageName.AllFirmsPage,60);
				appLog.info("count successfully read from all firms page: "+aa);
				ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
			}else {
				appLog.error("Not able to select All firms from drop down list so cannot read all firms count");
				sa.assertTrue(false, "Not able to select All firms from drop down list so cannot read all firms count");
			}
		}else {
			appLog.error("Org2 Firm name is not available in firm name drop down list. so cannot read alert count");
			sa.assertTrue(false, "Org2 Firm name is not available in firm name drop down list. so cannot read alert count");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(Org2CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3Org2FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
				String FWR_docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\Org2FileUpload\\FWR\\UpdateFiles\\Standard1";
				if(fp.uploadFile(folderpath,M3Org2Institution1, FWR_docpath,null,UploadFileActions.Update,Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileStandard);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace,PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								List<String> filecount=createListOutOfString(filesName);
								int alertCount=Integer.parseInt(Org2Alert_Count)+filecount.size();
								int allfirmCount=Integer.parseInt(Allfirms_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc066_1_uploadDocInSTDFolderInFWRAtCRMSideInOrg2", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allfirmCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+Org2AllDocument_count);
								appLog.info("All firms count should be at investor side: "+allfirmCount);
								
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify updated documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3Org2FundName1+" so cannot update files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3Org2FundName1+" so cannot update files in fundraising workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc067_2_updateDocInSTDFolderInINVAtCRMSideInOrg2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(Org2CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3Org2FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
				String INV_docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\Org2FileUpload\\INV\\UpdateFiles\\Standard1";
				if(fp.uploadFile(folderpath,M3Org2Institution1+"/"+M3Org2LimitedPartner1, INV_docpath,null,UploadFileActions.Update, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileStandard);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace,PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								List<String> filecount=createListOutOfString(filesName);
								int alertCount=Integer.parseInt(Org2Alert_Count)+filecount.size();
								int allfirmCount=Integer.parseInt(Allfirms_Count)+filecount.size();
								ExcelUtils.writeData(String.valueOf(alertCount), "FilePath",excelLabel.TestCases_Name, "M3tc066_1_uploadDocInSTDFolderInFWRAtCRMSideInOrg2", excelLabel.Alert_Count);
								ExcelUtils.writeData(String.valueOf(allfirmCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
								appLog.info("alert count should be at investor side: "+alertCount);
								appLog.info("All document count should be at investor side: "+Org2AllDocument_count);
								appLog.info("All firms count should be at investor side: "+allfirmCount);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify updated documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M3Org2FundName1+" so cannot update files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3Org2FundName1+" so cannot update files in investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc067_3_verifyUpdateDocAlertForSTDAtInvestorSideForOrg2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String FWR_fileName=ExcelUtils.readData("FilePath",0,12, "M3tc067_1_updateDocInSTDFolderInFWRAtCRMSideInOrg2");
		String INV_fileName=ExcelUtils.readData("FilePath",0,12, "M3tc067_2_updateDocInSTDFolderInINVAtCRMSideInOrg2");
		if(allfp.selectFirmName(Org2FirmName)) {
			if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
				if(ivp.verifyAlertCount(Org2AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
					appLog.info("All documents alert count is matched.");
				}else {
					appLog.error("All documents alert count is not matched");
					sa.assertTrue(false, "All documents alert count is not matched");
				}
				
			}else {
				appLog.error("Not able to click on all document tab so cannot verify all document alerts");
				sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
			}
			if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
				result.clear();
				result=ivp.verifyAlertOnRecentAllDocumentGrid(FWR_fileName, M3Org2FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
				if(!result.isEmpty()) {
					for(int i=0; i<result.size();i++) {
						appLog.error(result.get(i));
						sa.assertTrue(false, result.get(i));
					}
				}else {
					appLog.info("recent activities update alerts are matched for potential investment successfully.");
				}
				result.clear();
				result=ivp.verifyAlertOnRecentAllDocumentGrid(INV_fileName, M3Org2FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities,activityType.DocumentUpdate,null);			
				if(!result.isEmpty()) {
					for(int i=0; i<result.size();i++) {
						appLog.error(result.get(i));
						sa.assertTrue(false, result.get(i));
					}
				}else {
					appLog.info("recent activities update alerts are matched for current investment successfully.");
				}
				if(ivp.verifyAlertCount(Org2Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
					appLog.info("recent activities alert count is matched");
				}else {
					appLog.error("recent activities alert count is not matched");
					sa.assertTrue(false, "recent activities alert count is not matched");
				}
				
			}else {
				appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
				sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
			}
			
		}else {
			appLog.error("Org2 Firm name is not available in firm name drop down list. so cannot read alert count");
			sa.assertTrue(false, "Org2 Firm name is not available in firm name drop down list. so cannot read alert count");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(FWR_fileName,Org2FirmName, M3Org2FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms update alerts are matched for potential investment successfully.");
			}
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(INV_fileName,Org2FirmName, M3Org2FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpdate, null);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("All firms update alerts are matched for current investment successfully.");
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc068_validateFirmsNameAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		FundsPageBusinessLayer fpb = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String aa =getSelectedOptionOfDropDown(driver, allfp.getFirmNameDropdown(60), "firm name drop down list", "text");
		if(aa.contains(Org1UpdatedFirmName) || aa.contains(Org2FirmName) ) {
			appLog.info("Firm Name is selected by default in alphabetic order");
		}else {
			appLog.error("Firm Name is not selected in firm name drop down  list in alphabetic order.");
			sa.assertTrue(false, "Firm Name is not selected in firm name drop down  list in alphabetic order.");
		}
		List<WebElement> lst =allOptionsInDropDrop(driver, allfp.getFirmNameDropdown(60), "firm name drop down list");
		if(!lst.isEmpty()) {
			if(fpb.checkSorting(SortOrder.Assecending, lst)) {
				appLog.info("firms name are visible in the alphabetic order");
			}else {
				appLog.error("firms name are not visible in the alphabetic order");
				sa.assertTrue(false, "firms name are not visible in the alphabetic order");
			}
		}else {
			appLog.error("Firms Name drop down list is empty. so cannot check sorting of fims name ");
			sa.assertTrue(false, "Firms Name drop down list is empty. so cannot check sorting of firms name");
		}
		if(allfp.selectFirmName(Org2FirmName)) {
			appLog.info(Org2FirmName+" is selected from firm name drop down list");
			ThreadSleep(5000);
			if(ivp.getAllDocumentsTab(60)!=null) {
				appLog.info("all document tab is visible for "+Org2FirmName);
			}else {
				appLog.error("all document tab is not visible for "+Org2FirmName);
				sa.assertTrue(false, "all document tab is not selected for "+Org2FirmName);
			}
		}else {
			appLog.error("Not able to select "+Org2FirmName+" so cannot check default landing page");
			sa.assertTrue(false, "Not able to select "+Org2FirmName+" so cannot check default landing page");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc069_checkVeriousOperationOnAllDocAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fpb = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);	
		AllFirmsPageBusinesslayer allfp = new AllFirmsPageBusinesslayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		List<WebElement>fileName = new ArrayList<WebElement>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(allfp.selectFirmName(Org1FirmName)) {
		if(click(driver, ivp.getAllDocumentsTab(60), "all document tab", action.BOOLEAN)) {
				List<WebElement> lst =ivp.getAllDocumentHeaderTextList();
				String [] str = {"Name","Investment Name","Workspace"};
				if(!lst.isEmpty()) {
					for(int j=0; j<str.length; j++) {
						lst =ivp.getAllDocumentHeaderTextList();
						for(int i=0; i<lst.size(); i++) {
							String aa=lst.get(i).getText().trim();
							if(aa.contains(str[j])) {
								if(click(driver, lst.get(i), "all document "+str[j]+" text", action.BOOLEAN)) {
									List<WebElement> lst1=ivp.getAllDocumentHeaderTextSortingIcon();
									if(!lst1.isEmpty()) {
										if(click(driver,lst1.get(j), str[j]+" sorting icon", action.BOOLEAN)) {
											appLog.info("clicked on "+str[j]+" soritng icon");
											if(aa.equalsIgnoreCase(str[0])) {
												fileName=ivp.getAllDocumentFileNameList();											
											}else if (aa.equalsIgnoreCase(str[1])) {
												fileName=ivp.getAllDocumentInvestmentNameList();
											}else if (aa.equalsIgnoreCase(str[2])) {
												fileName=ivp.getAllDocumentWrokSpaceList();
											}
											if(!fileName.isEmpty()){
												if(fpb.checkSorting(SortOrder.Decending, fileName)) {
													appLog.info(str[j]+" cloumn data is  visible in Decending order");
												}else {
													appLog.error(str[j]+" cloumn is not visible in Decending order after click on sorting icon");
													sa.assertTrue(false, str[j]+" column is not visible in Decending order after click on sorting icon");
												}
												lst1=ivp.getAllDocumentHeaderTextSortingIcon();
												if(click(driver, lst1.get(j),str[j]+" sorting icon", action.BOOLEAN)) {
													fileName.clear();
													if(aa.equalsIgnoreCase(str[0])) {
														fileName=ivp.getAllDocumentFileNameList();											
													}else if (aa.equalsIgnoreCase(str[1])) {
														fileName=ivp.getAllDocumentInvestmentNameList();
													}else if (aa.equalsIgnoreCase(str[2])) {
														fileName=ivp.getAllDocumentWrokSpaceList();
													}
													if(fpb.checkSorting(SortOrder.Assecending, fileName)) {
														appLog.info(str[j]+" column data is visible in Assecending order");
													}else {
														appLog.error(str[j]+" data is not visible in Assecending order after click on sorting icon");
														sa.assertTrue(false, str[j]+" data is not visible in Assecending order after click on sorting icon");
													}
												}else {
													appLog.error("Not able to click on "+str[j]+" sorting icon so cannot check sorting in Asending Order");
													sa.assertTrue(false, "Not able to click on "+str[j]+" sorting icon so cannot check sorting in Asending Order");
												}
											}else {
												appLog.error(str[j]+" data is not available in all document alert grid so cannot check soritng on it");
												sa.assertTrue(false, str[j]+"data is not available in all document alert grid so cannot check soritng on it");
											}
										}else {
											appLog.error("Not able to click on all document "+str[j]+" soritng icon so cannot check sorting in Desending Order");
											sa.assertTrue(false, "Not able to click on all document "+str[j]+" soritng icon so cannot check sorting in Desending Order");
										}
									}else {
										appLog.error("all document "+str[j]+" column soritng icon is not visible so cannot click on it");
										sa.assertTrue(false,"all document "+str[j]+" column soritng icon is not visible so cannot click on it");
									}
								}
								break;
							}
							if(i==lst.size()-1) {
								appLog.error(str[j]+"column is not visible so cannot check sorting on it");
								sa.assertTrue(false, str[j]+"column is not visible so cannot check sorting on it");
							}
						}
						
					}
				}else {
					appLog.error("all document header is not displayed so cannot check soritng");
					sa.assertTrue(false, "all document header is not displayed so cannot check soritng");
				}
				driver.navigate().refresh();
				ThreadSleep(3000);
				
				if(bp.clickUsingCssSelectorPath("a[title='Search']", "investor side search button")) {
//				 if(click(driver, ivp.getAlldocumentSearchBtn(60), "all document search button", action.BOOLEAN)) {
					 ThreadSleep(3000);
					 if(isAlertPresent(driver)) {
						 String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
							switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
							if(msg.equalsIgnoreCase(InvestorFirmPageErrorMessage.pleaseEnterSomeTextMsg)) {
								appLog.info("Error Message is verified: "+InvestorFirmPageErrorMessage.pleaseEnterSomeTextMsg);
							}else {
								appLog.error("Error message is not matched. Expected: "+InvestorFirmPageErrorMessage.pleaseEnterSomeTextMsg+" Actual Result: "+msg);
								sa.assertTrue(false, "Error message is not matched. Expected: "+InvestorFirmPageErrorMessage.pleaseEnterSomeTextMsg+" Actual Result: "+msg);
							}
					 }else {
						appLog.error("Alert is not present so cannot check error message : "+InvestorFirmPageErrorMessage.pleaseEnterSomeTextMsg);
						sa.assertTrue(false, "Alert is not present so cannot check error message : "+InvestorFirmPageErrorMessage.pleaseEnterSomeTextMsg);
					}
					 
				 }else {
					appLog.error("Not able to click on search button so cannot check error message : "+InvestorFirmPageErrorMessage.pleaseEnterSomeTextMsg);
					sa.assertTrue(false, "Not able to click on search button so cannot check error message : "+InvestorFirmPageErrorMessage.pleaseEnterSomeTextMsg);
				}
				 for(int j=0; j<2; j++) {
					 fileName.clear();
					 fileName=ivp.getAllDocumentFileNameList();
					 String name=fileName.get(0).getText().trim();
					 if(j==1) {
						 name=".pdf";
					 }
					 if(sendKeys(driver,ivp.getAllDocumentSearchTextBox(60),name,"all document search text box", action.BOOLEAN)) {
						 if(click(driver, ivp.getAlldocumentSearchBtn(30), "search button", action.BOOLEAN)) {
							 List<WebElement> searchedFileName=ivp.getallDocumentSearchedFileNameList();
							 if(!searchedFileName.isEmpty()) {
								 for(int i=0; i<searchedFileName.size(); i++) {
									 if(j==0) {
										 if(searchedFileName.get(i).getText().trim().contains(name)) {
											 appLog.info(name+" file is visible in searched grid");
											 if(click(driver, fileName.get(i), name+" document name", action.BOOLEAN)) {
												 String parentId=switchOnWindow(driver);
												 if(parentId!=null) {
													 if(ivp.getDocumentCloseBtn(60)!=null) {
														 appLog.info("document is open successfully");
														 if(!click(driver, ivp.getDocumentCloseBtn(30), "close button", action.BOOLEAN)) {
															 appLog.info("clicked on close button");
														 }
														 driver.switchTo().window(parentId);
													 }else {
														 driver.close();
														 driver.switchTo().window(parentId);
														 appLog.error("close button is not visible");
														 sa.assertTrue(false, "close button is not visible");
													 }						 
												 }else {
													appLog.error("document is not open");
													sa.assertTrue(false, "document is not open");
												}
												 
											 }else {
												appLog.error("Not able to click on document name "+name+" so cannot check doucment in box view mode");
												sa.assertTrue(false, "Not able to click on document name "+name+" so cannot check doucment in box view mode");
											}
											 if(click(driver, ivp.getAlldocumentsearchedPopUpCloseBtn(30), "searched pop up close button", action.BOOLEAN)) {
												 appLog.info("clicked on searched pop up close button");
											 }else {
												 appLog.error("Not able to click on searched pop up close button so cannot close searched pop up");
												 sa.assertTrue(false, "Not able to click on searched pop up close button so cannot close searched pop up");
											 }
											 break;
										 }
										 if(i==searchedFileName.size()-1) {
											 appLog.error(name+" file is not visible in searched file grid");
											 sa.assertTrue(false, name+" file is not visible in searched file grid");
										 }										 
									 }else {
											String aa=searchedFileName.get(i).getText().trim();
											if(aa.contains(name)) {
												appLog.info(aa+" file is contains .pdf extension");
											}else {
												appLog.error(aa+" file is not containing .pdf extension. Expected: .pdf Actual Result: "+aa);
												sa.assertTrue(false, aa+" file is not containing .pdf extension. Expected: .pdf Actual Result: "+aa);
											}
									 }
								 }
								 if(click(driver, ivp.getAlldocumentsearchedPopUpCloseBtn(10), "searched pop up close button", action.BOOLEAN)) {
									 appLog.info("clicked on searched pop up close button");
								 }
							 }else {
								 appLog.error("files are not available in searched file grid");
								 sa.assertTrue(false, "files are not available in searched file grid");
							 }
							 
						 }else {
							 appLog.error("Not able to click on search button so cannot verify document search fuctionality");
							 sa.assertTrue(false, "Not able to click on search button so cannot verify document search fuctionality");
						 }
					 }else {
						 appLog.error("not able to pass value "+name+" in search text box so cannot verify document search fuctionality");
						 sa.assertTrue(false, "not able to pass value "+name+" in search text box so cannot verify document search fuctionality");
					 }
				 }
				 fileName.clear();
				 fileName=ivp.getAllDocumentFileNameList();
				 String name=fileName.get(0).getText().trim();
				 if(sendKeys(driver,ivp.getAllDocumentSearchTextBox(60),name,"all document search text box", action.BOOLEAN)) {
					 if(click(driver, ivp.getAlldocumentSearchBtn(30), "search button", action.BOOLEAN)) {
						 if(click(driver, ivp.getAllDocumentSearchedPopUpCrossBtn(60), "searched pop up cross icon", action.BOOLEAN)) {
							 appLog.info("clicked on searched pop up cross icon");
						 }else {
							appLog.error("Not able to click on searched pop up cross icon");
							sa.assertTrue(false, "Not able to click on searched pop up cross icon");
						}
					 }else {
						 appLog.error("Not able to click on search button so cannot click on cross button");
						 sa.assertTrue(false, "Not able to click on search button so cannot click on cross");
					 }
				 }else {
					 appLog.error("not able to pass value "+name+" in search text box so cannot click on cross button");
					 sa.assertTrue(false, "not able to pass value "+name+" in search text box so cannot click on cross button");
				 }
				 String[] fundName= {M3FundName1,M3FundName2};
				 String investmentName;
				 for(int j=0; j<fundName.length; j++) {
					 List<WebElement> investmentNamelist=ivp.getAllDocumentInvestmentNameList();
					 List<WebElement> workspace=ivp.getAllDocumentWrokSpaceList();
					 if(j==0) {
						 investmentName="Current Investment";
					 }else {
						 investmentName="Potential Investment";
					}
					 if(!investmentNamelist.isEmpty()) {
						 for(int i=0; i<investmentNamelist.size(); i++) {
							 scrollDownThroughWebelement(driver, investmentNamelist.get(i), "");
							 if(investmentNamelist.get(i).getText().trim().contains(fundName[j]) && workspace.get(i).getText().trim().contains(investmentName)) {
								 if(click(driver, investmentNamelist.get(i), "invesment name link", action.SCROLLANDBOOLEAN)) {
									 String parentId=switchOnWindow(driver);
									 String aa=driver.getTitle().trim();
									 if(parentId!=null) {
										 if(aa.contains(investmentName)) {
											 appLog.info(investmentName+" page is open");
										 }else {
											 appLog.error(investmentName+" page is not open after click on "+fundName[j]);
											 sa.assertTrue(false, investmentName+" page is not open after click on "+fundName[j]);
										 }
										 driver.close();
										 driver.switchTo().window(parentId);									
									 }else {
										 appLog.error("no new window is open");
										 sa.assertTrue(false, "no new window is open");
									 }
								 }else {
									 appLog.error("Not able to click on invesment name:  "+fundName[j]);
									 sa.assertTrue(false, "Not able to click on invesment name:  "+fundName[j]);
								 }
								 break;
							 }
							 if(i==investmentNamelist.size()-1) {
								 appLog.error(fundName[j]+" and "+investmentName+" workspace is not available in all document alert grid so cannot click on it");
								 sa.assertTrue(false, fundName[j]+" and "+investmentName+" workspace is not available in all document alert grid so cannot click on it");
							 }
						 }
					 }
				 }
		}else {
			appLog.error("Not able to click on all document tab so cannot check verious operation on all document tab");
			sa.assertTrue(false, "Not able to click on all document tab so cannot check verious operation on all document tab");
		}
		}else {
			appLog.error("Not able to select "+Org1FirmName+" so cannot check various operation");
			sa.assertTrue(false, "Not able to select "+Org1FirmName+" so cannot check various operation");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc070_veriousOperationOnFirmAlertGridAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fpb = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);	
		AllFirmsPageBusinesslayer allfp = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String AUM=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.AUM);
		String UpdatedfirmName=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Updated_FirmName);
		String firmContact=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Firm_Contact);
		String website=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.WebSite);
		String MailingStreet=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Mailing_Street);
		String MailingCity=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Mailing_City);
		String MailingCounty=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Mailing_Country);
		String MailingState=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Mailing_State);
		String MailingZip=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Mailing_Zip);
		String phone=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Phone);
		String firmProfileData=""+firmContact+"<break>"+website+"<break>"+MailingCity+"<break>"+MailingCounty+"<break>"+MailingState+"<break>"+MailingStreet+"<break>"+MailingZip+"<break>"+AUM+"<break>"+UpdatedfirmName+"<break>"+phone;
		if(allfp.selectFirmName(Org1FirmName)) {
		if(click(driver, ivp.getRecentActivitiesTab(60), "recent activities tab", action.BOOLEAN)) {
			List<WebElement> lst =ivp.getRecentActivitiesAlertHeaderTextList();
			String [] str = {"Name","Investment Name","Activity Created On","Activity Type"};
			if(!lst.isEmpty()) {
				for(int j=0; j<str.length; j++) {
					List<WebElement>fileName = new ArrayList<WebElement>();
					lst =ivp.getRecentActivitiesAlertHeaderTextList();
					for(int i=0; i<lst.size(); i++) {
						String aa=lst.get(i).getText().trim();
						if(!aa.equalsIgnoreCase("Workspace")) {
							if(aa.contains(str[j])) {
								if(click(driver, lst.get(i), "recent activities "+str[j]+" text", action.BOOLEAN)) {
									List<WebElement> lst1=ivp.getRecentDocumentSortingIconList();
									if(!lst1.isEmpty()) {
										ThreadSleep(2000);
										if(click(driver,lst1.get(i), str[j]+" sorting icon", action.BOOLEAN)) {
											appLog.info("clicked on "+str[j]+" soritng icon");
											if(aa.equalsIgnoreCase(str[0])) {
												fileName=ivp.getRecentActivitiesFileNameList();											
											}else if (aa.equalsIgnoreCase(str[1])) {
												fileName=ivp.getRecentActivitiestInvestmentNameList();
											}else if (aa.equalsIgnoreCase(str[2])) {
												fileName=ivp.getRecentActivitiesCreatedOnList();
											}else if (aa.equalsIgnoreCase(str[3])) {
												fileName=ivp.getRecentActivitiesActivityTypeList();
											}
											if(!fileName.isEmpty()){
												if(fpb.checkSorting(SortOrder.Decending, fileName)) {
													appLog.info(str[j]+" cloumn data is  visible in Decending order");
												}else {
													appLog.error(str[j]+" cloumn is not visible in Decending order after click on sorting icon");
													sa.assertTrue(false, str[j]+" column is not visible in Decending order after click on sorting icon");
												}
												lst1=ivp.getRecentDocumentSortingIconList();
												ThreadSleep(2000);
												if(click(driver, lst1.get(i),str[j]+" sorting icon", action.BOOLEAN)) {
													fileName.clear();
													if(aa.equalsIgnoreCase(str[0])) {
														fileName=ivp.getRecentActivitiesFileNameList();											
													}else if (aa.equalsIgnoreCase(str[1])) {
														fileName=ivp.getRecentActivitiestInvestmentNameList();
													}else if (aa.equalsIgnoreCase(str[2])) {
														fileName=ivp.getRecentActivitiesCreatedOnList();
													}else if (aa.equalsIgnoreCase(str[3])) {
														fileName=ivp.getRecentActivitiesActivityTypeList();
													}
													if(fpb.checkSorting(SortOrder.Assecending, fileName)) {
														appLog.info(str[j]+" column data is visible in Assecending order");
													}else {
														appLog.error(str[j]+" data is not visible in Assecending order after click on sorting icon");
														sa.assertTrue(false, str[j]+" data is not visible in Assecending order after click on sorting icon");
													}
												}else {
													appLog.error("Not able to click on "+str[j]+" sorting icon so cannot check sorting in Asending Order");
													sa.assertTrue(false, "Not able to click on "+str[j]+" sorting icon so cannot check sorting in Asending Order");
												}
											}else {
												appLog.error(str[j]+" data is not available in all document alert grid so cannot check soritng on it");
												sa.assertTrue(false, str[j]+"data is not available in all document alert grid so cannot check soritng on it");
											}
										}else {
											appLog.error("Not able to click on recent activities "+str[j]+" soritng icon so cannot check sorting in Desending Order");
											sa.assertTrue(false, "Not able to click on recent activities "+str[j]+" soritng icon so cannot check sorting in Desending Order");
										}
									}else {
										appLog.error("recent activities "+str[j]+" column soritng icon is not visible so cannot click on it");
										sa.assertTrue(false,"recent activities "+str[j]+" column soritng icon is not visible so cannot click on it");
									}
								}
								break;
							}
						}
						if(i==lst.size()-1) {
							appLog.error(str[j]+" column is not visible so cannot check sorting on it");
							sa.assertTrue(false, str[j]+" column is not visible so cannot check sorting on it");
						}
					}
					
				}
			}else {
				appLog.error("recent activities header is not displayed so cannot check soritng");
				sa.assertTrue(false, "recent activities header is not displayed so cannot check soritng");
			}
			driver.navigate().refresh();
			List<WebElement> activityType=ivp.getRecentActivitiesContactFirmUpdateList();
			if(!activityType.isEmpty()) {
				for(int i=0; i<activityType.size(); i++) {
					String aa=activityType.get(i).getText().trim();
					scrollDownThroughWebelement(driver, activityType.get(i), "");	
					if(aa.equalsIgnoreCase("Firm Profile Updated")) {
						if(click(driver,activityType.get(i), "firm profile update link", action.SCROLLANDBOOLEAN)) {
							HashSet<String> newValue = CommonLib.scrollActiveWidgetforSetofFiles(driver,
									ivp.getFirmProfileUpdatePopUpScroll(30),
									By.xpath("//span[contains(@id,'grid_AccountProfileUpdated-cell-1-')]"));
							List<String> fieldList = new ArrayList<String>(newValue);
							if (!compareMultipleListWithoutAssertion(firmProfileData, fieldList)) {
								appLog.info("New value data Not Matched for Firm Profile Updated Alerts on recent activities tab");
								sa.assertTrue(false, "New value data Not Matched for Firm Profile Updated Alerts on recent activities tab");
							} else {
								appLog.info("New value data is Matched for Firm Profile Updated Alerts on recent activities tab");
							}
							lst =ivp.getFirmProfileUpdateHeaderTextList();
							String [] str1 = {"Field","New Value"};
							if(!lst.isEmpty()) {
								for(int j=0; j<str1.length; j++) {
									List<WebElement>fileName = new ArrayList<WebElement>();
									lst =ivp.getFirmProfileUpdateHeaderTextList();
									for(int i1=0; i1<lst.size(); i1++) {
										String aa1=lst.get(i1).getText().trim();
										if(aa1.contains(str1[j])) {
											if(click(driver, lst.get(i1), "firm profile update "+str1[j]+" text", action.SCROLLANDBOOLEAN)) {
												List<WebElement> lst1=ivp.getFirmProfileUpdateSortingIconList();
												if(!lst1.isEmpty()) {
													ThreadSleep(2000);
													if(click(driver,lst1.get(j), str1[j]+" sorting icon", action.SCROLLANDBOOLEAN)) {
														appLog.info("clicked on "+str1[j]+" soritng icon");
														if(j==0) {
															click(driver, lst1.get(j), str1[j]+" soting icon", action.SCROLLANDBOOLEAN);
															appLog.info("again clicked on "+str1[j]+" soritng icon");															
														}
														if(aa1.equalsIgnoreCase(str1[0])) {
															fileName=ivp.getFirmProfileUpdateFieldList();											
														}else if (aa1.equalsIgnoreCase(str1[1])) {
															fileName=ivp.getFirmProfileUpdateNewvalueList();
														}
														if(!fileName.isEmpty()){
															if(fpb.checkSorting(SortOrder.Decending, fileName)) {
																appLog.info(str1[j]+" cloumn data is  visible in Decending order");
															}else {
																appLog.error(str1[j]+" cloumn is not visible in Decending order after click on sorting icon");
																sa.assertTrue(false, str1[j]+" column is not visible in Decending order after click on sorting icon");
															}
															lst1=ivp.getFirmProfileUpdateSortingIconList();
															ThreadSleep(2000);
															if(click(driver, lst1.get(j),str1[j]+" sorting icon", action.SCROLLANDBOOLEAN)) {
																fileName.clear();
																if(aa1.equalsIgnoreCase(str1[0])) {
																	fileName=ivp.getFirmProfileUpdateFieldList();											
																}else if (aa1.equalsIgnoreCase(str1[1])) {
																	fileName=ivp.getFirmProfileUpdateNewvalueList();
																}
																if(fpb.checkSorting(SortOrder.Assecending, fileName)) {
																	appLog.info(str1[j]+" column data is visible in Assecending order");
																}else {
																	appLog.error(str1[j]+" data is not visible in Assecending order after click on sorting icon");
																	sa.assertTrue(false, str1[j]+" data is not visible in Assecending order after click on sorting icon");
																}
															}else {
																appLog.error("Not able to click on "+str1[j]+" sorting icon so cannot check sorting in Asending Order");
																sa.assertTrue(false, "Not able to click on "+str1[j]+" sorting icon so cannot check sorting in Asending Order");
															}
														}else {
															appLog.error(str1[j]+" data is not available in all document alert grid so cannot check soritng on it");
															sa.assertTrue(false, str1[j]+"data is not available in all document alert grid so cannot check soritng on it");
														}
													}else {
														appLog.error("Not able to click on firm profile update "+str1[j]+" soritng icon so cannot check sorting in Desending Order");
														sa.assertTrue(false, "Not able to click on firm profile update "+str1[j]+" soritng icon so cannot check sorting in Desending Order");
													}
												}else {
													appLog.error("recent activities "+str1[j]+" column soritng icon is not visible so cannot click on it");
													sa.assertTrue(false,"recent activities "+str1[j]+" column soritng icon is not visible so cannot click on it");
												}
											}
											
											break;
										}
										if(i1==lst.size()-1) {
											appLog.error(str1[j]+" column is not visible so cannot check sorting on it");
											sa.assertTrue(false, str1[j]+" column is not visible so cannot check sorting on it");
										}
									}
									
								}
							}else {
								appLog.error("firm profile update header is not displayed so cannot check soritng");
								sa.assertTrue(false, "firm profile update header is not displayed so cannot check soritng");
							}
							
							if(click(driver, ivp.getFirmProfileUpdateCloseBtn(30), "firm profile update close button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Click on close button");
							}else {
								appLog.error("Not able to click on close button so cannot close firm profile pop up");
								sa.assertTrue(false, "Not able to click on close button so cannot close firm profile pop up");
							}
						}else {
							appLog.error("Not able to click on firm profile update link so cannot check pop up data and sorting ");
							sa.assertTrue(false, "Not able to click on firm profile update link so cannot check pop up data and sorting ");
						}
						break;
					}
					if(i==activityType.size()-1){
						appLog.error("Firm Profile Updated alert is not available so cannot check data and sorting in firm profile update pop up");
						sa.assertTrue(false, "Firm Profile Updated alert is not available so cannot check data and sorting in firm profile update pop up");
					}
				}
			}else {
				appLog.error("recent activities activity type list is not available in the grid so cannot click on firm update alert");
				sa.assertTrue(false, "recent activities activity type list is not available in the grid so cannot click on firm update alert");
			}
			activityType.clear();
			activityType=ivp.getRecentActivitiesContactFirmUpdateList();
			if(!activityType.isEmpty()) {
				for(int i=0; i<activityType.size(); i++) {
					String aa=activityType.get(i).getText().trim();
					scrollDownThroughWebelement(driver, activityType.get(i), "");	
					if(aa.equalsIgnoreCase("Firm Profile Updated")) {
						if(click(driver,activityType.get(i), "firm profile update link", action.SCROLLANDBOOLEAN)) {
							if(click(driver, ivp.getFirmprofileUpdateCrossIcon(30), "firm profile pop up cross icon", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on cross icon");
								if(ivp.getFirmProfileUpdateCloseBtn(10)==null) {
									appLog.info("firm profile update pop is closed");
								}else {
									appLog.error("firm profile update pop up is not closed after click on cross icon");
									sa.assertTrue(false, "firm profile update pop up is not closed after click on cross icon");
								}
							}else {
								appLog.error("Not able to click on firm profile update cross icon");
								sa.assertTrue(false, "Not able to click on firm profile update cross icon");
							}
						}else {
							appLog.error("Not able to click on firm profile update link so cannot check cross button functionality ");
							sa.assertTrue(false, "Not able to click on firm profile update link so cannot check cross button functionality ");
						}
						break;
					}if(i==activityType.size()-1){
						appLog.error("Firm Profile Updated alert is not available so cannot check cross button functionality");
						sa.assertTrue(false, "Firm Profile Updated alert is not available so cannot check cross button functionality");
					}				
				}
				
			}else {
				appLog.error("recent activities activity type list is not available in the grid so cannot click on firm update alert");
				sa.assertTrue(false, "recent activities activity type list is not available in the grid so cannot click on firm update alert");
			}
			activityType.clear();
			activityType=ivp.getRecentActivitiesContactFirmUpdateList();
			if(!activityType.isEmpty()) {
				String linkedin =ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Linkedin);
				String facebook =ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Facebook);
				String fristName=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Updated_FirstName);
				String LastName=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Updated_LastName);
				String title=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Title);
				phone=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Phone);
				MailingStreet=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Mailing_Street);
				MailingCity=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Mailing_City);
				MailingCounty=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Mailing_Country);
				MailingState=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Mailing_State);
				MailingZip=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Mailing_Zip);
				firmProfileData=null;
				firmProfileData=""+phone+"<break>"+title+"<break>"+LastName+"<break>"+fristName+"<break>"+MailingCity+"<break>"+MailingState+"<break>"+MailingStreet+"<break>"+MailingZip+"<break>"+facebook+"<break>"+linkedin+"<break>"+MailingCounty;
				for(int i=0; i<activityType.size(); i++) {
					String aa=activityType.get(i).getText().trim();
					scrollDownThroughWebelement(driver, activityType.get(i), "");	
					if(aa.equalsIgnoreCase("Contact Profile Updated")) {
						if(click(driver,activityType.get(i), "Contact profile update link", action.SCROLLANDBOOLEAN)) {
							HashSet<String> newValue = CommonLib.scrollActiveWidgetforSetofFiles(driver,
									ivp.getContactProfileUpdatedPopUpScroll(30),
									By.xpath("//span[contains(@id,'grid_ContactProfileUpdated')][contains(@id,'cell-1')]"));
							List<String> fieldList = new ArrayList<String>(newValue);
							if (!compareMultipleListWithoutAssertion(firmProfileData, fieldList)) {
								appLog.info("New value data Not Matched for Contact Profile Updated Alerts on recent activities tab");
								sa.assertTrue(false, "New value data Not Matched for Contact Profile Updated Alerts on recent activities tab");
							} else {
								appLog.info("New value data is Matched for Contact Profile Updated Alerts on recent activities tab");
							}
							lst =ivp.getContactProfileUpdateHeaderTextList();
							String [] str1 = {"Field","New Value"};
							if(!lst.isEmpty()) {
								for(int j=0; j<str1.length; j++) {
									List<WebElement>fileName = new ArrayList<WebElement>();
									lst =ivp.getContactProfileUpdateHeaderTextList();
									for(int i1=0; i1<lst.size(); i1++) {
										String aa1=lst.get(i1).getText().trim();
										if(aa1.contains(str1[j])) {
											if(click(driver, lst.get(i1), "Contact profile update "+str1[j]+" text", action.SCROLLANDBOOLEAN)) {
												List<WebElement> lst1=ivp.getContactProfileUpdateSortingIcon();
												if(!lst1.isEmpty()) {
													ThreadSleep(2000);
													if(click(driver,lst1.get(j), str1[j]+" sorting icon", action.SCROLLANDBOOLEAN)) {
														appLog.info("clicked on "+str1[j]+" soritng icon");
														if(j==0) {
															click(driver, lst1.get(j), str1[j]+" soting icon", action.SCROLLANDBOOLEAN);
															appLog.info("again clicked on "+str1[j]+" soritng icon");															
														}
														if(aa1.equalsIgnoreCase(str1[0])) {
															fileName=ivp.getContactProfileUpdateFieldList();										
														}else if (aa1.equalsIgnoreCase(str1[1])) {
															fileName=ivp.getContactProfileUpdateValueList();
														}
														if(!fileName.isEmpty()){
															if(fpb.checkSorting(SortOrder.Decending, fileName)) {
																appLog.info(str1[j]+" cloumn data is  visible in Decending order");
															}else {
																appLog.error(str1[j]+" cloumn is not visible in Decending order after click on sorting icon");
																sa.assertTrue(false, str1[j]+" column is not visible in Decending order after click on sorting icon");
															}
															lst1=ivp.getContactProfileUpdateSortingIcon();
															ThreadSleep(2000);
															if(click(driver, lst1.get(j),str1[j]+" sorting icon", action.SCROLLANDBOOLEAN)) {
																fileName.clear();
																if(aa1.equalsIgnoreCase(str1[0])) {
																	fileName=ivp.getContactProfileUpdateFieldList();										
																}else if (aa1.equalsIgnoreCase(str1[1])) {
																	fileName=ivp.getContactProfileUpdateValueList();
																}
																if(fpb.checkSorting(SortOrder.Assecending, fileName)) {
																	appLog.info(str1[j]+" column data is visible in Assecending order");
																}else {
																	appLog.error(str1[j]+" data is not visible in Assecending order after click on sorting icon");
																	sa.assertTrue(false, str1[j]+" data is not visible in Assecending order after click on sorting icon");
																}
															}else {
																appLog.error("Not able to click on "+str1[j]+" sorting icon so cannot check sorting in Asending Order");
																sa.assertTrue(false, "Not able to click on "+str1[j]+" sorting icon so cannot check sorting in Asending Order");
															}
														}else {
															appLog.error(str1[j]+" data is not available in all document alert grid so cannot check soritng on it");
															sa.assertTrue(false, str1[j]+"data is not available in all document alert grid so cannot check soritng on it");
														}
													}else {
														appLog.error("Not able to click on firm profile update "+str1[j]+" soritng icon so cannot check sorting in Desending Order");
														sa.assertTrue(false, "Not able to click on firm profile update "+str1[j]+" soritng icon so cannot check sorting in Desending Order");
													}
												}else {
													appLog.error("recent activities "+str1[j]+" column soritng icon is not visible so cannot click on it");
													sa.assertTrue(false,"recent activities "+str1[j]+" column soritng icon is not visible so cannot click on it");
												}
											}
											
											break;
										}
										if(i1==lst.size()-1) {
											appLog.error(str1[j]+" column is not visible so cannot check sorting on it");
											sa.assertTrue(false, str1[j]+" column is not visible so cannot check sorting on it");
										}
									}
									
								}
							}else {
								appLog.error("contact profile update header is not displayed so cannot check soritng");
								sa.assertTrue(false, "contact profile update header is not displayed so cannot check soritng");
							}
							
							if(click(driver, ivp.getContactProfileCloseBtn(30), "contact profile update close button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Click on close button");
							}else {
								appLog.error("Not able to click on close button so cannot close contact profile pop up");
								sa.assertTrue(false, "Not able to click on close button so cannot close contact profile pop up");
							}
						}else {
							appLog.error("Not able to click on contact profile update link so cannot check pop up data and sorting ");
							sa.assertTrue(false, "Not able to click on contact profile update link so cannot check pop up data and sorting ");
						}
						break;
					}
					if(i==activityType.size()-1){
						appLog.error("contact Profile Updated alert is not available so cannot check data and sorting in firm profile update pop up");
						sa.assertTrue(false, "contact Profile Updated alert is not available so cannot check data and sorting in firm profile update pop up");
					}
				}
			}else {
				appLog.error("recent activities activity type list is not available in the grid so cannot click on contact update alert");
				sa.assertTrue(false, "recent activities activity type list is not available in the grid so cannot click on contact update alert");
			}
			activityType.clear();
			activityType=ivp.getRecentActivitiesContactFirmUpdateList();
			if(!activityType.isEmpty()) {
				for(int i=0; i<activityType.size(); i++) {
					String aa=activityType.get(i).getText().trim();
					scrollDownThroughWebelement(driver, activityType.get(i), "");	
					if(aa.equalsIgnoreCase("Contact Profile Updated")) {
						if(click(driver,activityType.get(i), "Contact profile update link", action.SCROLLANDBOOLEAN)) {
							if(click(driver, ivp.getContactProfileUpdateCrossIcon(20), "firm profile pop up cross icon", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on cross icon");
								if(ivp.getContactProfileCloseBtn(10)==null) {
									appLog.info("contact profile update pop is closed");
								}else {
									appLog.error("contact profile update pop up is not closed after click on cross icon");
									sa.assertTrue(false, "contact profile update pop up is not closed after click on cross icon");
								}
							}else {
								appLog.error("Not able to click on contact profile update cross icon");
								sa.assertTrue(false, "Not able to click on contact profile update cross icon");
							}
						}else {
							appLog.error("Not able to click on contact profile update link so cannot check cross button functionality ");
							sa.assertTrue(false, "Not able to click on contact profile update link so cannot check cross button functionality ");
						}
						break;
					}if(i==activityType.size()-1){
						appLog.error("contact Profile Updated alert is not available so cannot check cross button functionality");
						sa.assertTrue(false, "contact Profile Updated alert is not available so cannot check cross button functionality");
					}				
				}
				
			}else {
				appLog.error("recent activities activity type list is not available in the grid so cannot click on contact update alert");
				sa.assertTrue(false, "recent activities activity type list is not available in the grid so cannot click on contact update alert");
			}
				 List<WebElement> fileName=ivp.getRecentActivitiesFileNameList();
						 if(!fileName.isEmpty()) {
							 for(int i=0; i<fileName.size(); i++) {
								 String name=fileName.get(i).getText().trim();
								 if(name.contains(".pdf")) {
									 scrollDownThroughWebelement(driver, fileName.get(0), "");
									 if(click(driver, fileName.get(0), name+" document name", action.SCROLLANDBOOLEAN)) {
										 String parentId=switchOnWindow(driver);
										 if(parentId!=null) {
											 if(ivp.getDocumentCloseBtn(60)!=null) {
												 appLog.info(name+" document is open successfully");
												 driver.close();
												 driver.switchTo().window(parentId);
											 }else {
												appLog.error("close button is not visible in open document");
												driver.close();
												driver.switchTo().window(parentId);
												sa.assertTrue(false, "close button is not visible in open document");
											}
										 }else {
											 appLog.error("document is not open");
											 sa.assertTrue(false, "document is not open");
										 }						 
									 }else {
										 appLog.error("Not able to click on document name "+name+" so cannot check doucment in box view mode");
										 sa.assertTrue(false, "Not able to click on document name "+name+" so cannot check doucment in box view mode");
									 }
									 break;
								 }if(i==fileName.size()-1) {
									 appLog.error("pdf file is not present in grid so cannot check doucment in box view mode");
									 sa.assertTrue(false, "pdf file is not present in grid so cannot check doucment in box view mode");
								 }
							}
						 }else {
							 appLog.error("files are not available in recent activities alert grid");
							 sa.assertTrue(false, "files are not available in recent activities alert grid");
						 }
						 String[] fundName= {M3FundName1,M3FundName2};
						 String investmentName;
						 for(int j=0; j<fundName.length; j++) {
							 List<WebElement> investmentNamelist=ivp.getRecentActivitiestInvestmentNameList();
							 List<WebElement> workspace=ivp.getRecentActivitiesWorkSpaceList();
							 if(j==0) {
								 investmentName="Current Investment";
							 }else {
								 investmentName="Potential Investment";
							}
							 if(!investmentNamelist.isEmpty()) {
								 for(int i=0; i<investmentNamelist.size(); i++) {
									 scrollDownThroughWebelement(driver, investmentNamelist.get(i), "");
									 if(investmentNamelist.get(i).getText().trim().contains(fundName[j]) && workspace.get(i).getText().trim().contains(investmentName)) {
										 if(click(driver, investmentNamelist.get(i), "invesment name link", action.SCROLLANDBOOLEAN)) {
											 String parentId=switchOnWindow(driver);
											 String aa=driver.getTitle().trim();
											 if(parentId!=null) {
												 if(aa.contains(investmentName)) {
													 appLog.info(investmentName+" page is open");
												 }else {
													 appLog.error(investmentName+" page is not open after click on "+fundName[j]);
													 sa.assertTrue(false, investmentName+" page is not open after click on "+fundName[j]);
												 }
												 driver.close();
												 driver.switchTo().window(parentId);									
											 }else {
												 appLog.error("no new window is open");
												 sa.assertTrue(false, "no new window is open");
											 }
										 }else {
											 appLog.error("Not able to click on invesment name:  "+fundName[j]);
											 sa.assertTrue(false, "Not able to click on invesment name:  "+fundName[j]);
										 }
										 break;
									 }
									 if(i==investmentNamelist.size()-1) {
										 appLog.error(fundName[j]+" and "+investmentName+" workspace is not available in recent activities alert grid so cannot click on it");
										 sa.assertTrue(false, fundName[j]+" and "+investmentName+" workspace is not available in recent activities alert grid so cannot click on it");
									 }
								 }
							 }else {
								appLog.error("Investment list is not available in content grid so cannot click on investment name link");
								sa.assertTrue(false, "Investment list is not available in content grid so cannot click on investment name link");
							}
						 }
		}else {
			appLog.error("Not able to click on recent document tab so cannot check verious operation on it");
			sa.assertTrue(false, "Not able to click on recent document tab so cannot check verious operation on it");
		}
		}else {
			appLog.error("Not able to select "+Org1FirmName+" so cannot check various operation");
			sa.assertTrue(false, "Not able to select "+Org1FirmName+" so cannot check various operation");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc071_veriousOperationOnAllFirmAlertGridAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		FundsPageBusinessLayer fpb = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);	
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String AUM=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.AUM);
		String UpdatedfirmName=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Updated_FirmName);
		String firmContact=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Firm_Contact);
		String website=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.WebSite);
		String MailingStreet=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Mailing_Street);
		String MailingCity=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Mailing_City);
		String MailingCounty=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Mailing_Country);
		String MailingState=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Mailing_State);
		String MailingZip=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Mailing_Zip);
		String phone=ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Phone);
		String firmProfileData=""+firmContact+"<break>"+website+"<break>"+MailingCity+"<break>"+MailingCounty+"<break>"+MailingState+"<break>"+MailingStreet+"<break>"+MailingZip+"<break>"+AUM+"<break>"+UpdatedfirmName+"<break>"+phone;
		if(allfp.selectFirmName("All Firms")) {
			List<WebElement> lst =allfp.getAllfirmalertHeaderTextList();
			String [] str = {"Name","Firm","Investment Name","Activity Created On","Activity Type"};
			if(!lst.isEmpty()) {
				for(int j=0; j<str.length; j++) {
					List<WebElement>fileName = new ArrayList<WebElement>();
					lst =allfp.getAllfirmalertHeaderTextList();
					for(int i=0; i<lst.size(); i++) {
						String aa=lst.get(i).getText().trim();
						if(!aa.equalsIgnoreCase("Workspace")) {
							if(aa.contains(str[j])) {
								if(click(driver, lst.get(i), "all firms "+str[j]+" text", action.BOOLEAN)) {
									List<WebElement> lst1=allfp.getSortingIconList();
									if(!lst1.isEmpty()) {
										ThreadSleep(2000);
										if(click(driver,lst1.get(i), str[j]+" sorting icon", action.BOOLEAN)) {
											appLog.info("clicked on "+str[j]+" soritng icon");
											if(aa.equalsIgnoreCase(str[0])) {
												fileName=allfp.getDocumentNameList();											
											}else if (aa.equalsIgnoreCase(str[1])) {
												fileName=allfp.getFirmNameList();
											}else if (aa.equalsIgnoreCase(str[2])) {
												fileName=allfp.getInvesmentNameList();
											}else if (aa.equalsIgnoreCase(str[3])) {
												fileName=allfp.getActivitiesCreatedOnList();
											}else if (aa.equalsIgnoreCase(str[4])) {
												fileName=allfp.getActivityTypeList();
											}
											if(!fileName.isEmpty()){
												if(fpb.checkSorting(SortOrder.Decending, fileName)) {
													appLog.info(str[j]+" cloumn data is  visible in Decending order");
												}else {
													appLog.error(str[j]+" cloumn is not visible in Decending order after click on sorting icon");
													sa.assertTrue(false, str[j]+" column is not visible in Decending order after click on sorting icon");
												}
												lst1=allfp.getSortingIconList();
												ThreadSleep(2000);
												if(click(driver, lst1.get(i),str[j]+" sorting icon", action.BOOLEAN)) {
													fileName.clear();
													if(aa.equalsIgnoreCase(str[0])) {
														fileName=allfp.getDocumentNameList();											
													}else if (aa.equalsIgnoreCase(str[1])) {
														fileName=allfp.getFirmNameList();
													}else if (aa.equalsIgnoreCase(str[2])) {
														fileName=allfp.getInvesmentNameList();
													}else if (aa.equalsIgnoreCase(str[3])) {
														fileName=allfp.getActivitiesCreatedOnList();
													}else if (aa.equalsIgnoreCase(str[4])) {
														fileName=allfp.getActivityTypeList();
													}
													if(fpb.checkSorting(SortOrder.Assecending, fileName)) {
														appLog.info(str[j]+" column data is visible in Assecending order");
													}else {
														appLog.error(str[j]+" data is not visible in Assecending order after click on sorting icon");
														sa.assertTrue(false, str[j]+" data is not visible in Assecending order after click on sorting icon");
													}
												}else {
													appLog.error("Not able to click on "+str[j]+" sorting icon so cannot check sorting in Asending Order");
													sa.assertTrue(false, "Not able to click on "+str[j]+" sorting icon so cannot check sorting in Asending Order");
												}
											}else {
												appLog.error(str[j]+" data is not available in all firms alert grid so cannot check soritng on it");
												sa.assertTrue(false, str[j]+"data is not available in all firms alert grid so cannot check soritng on it");
											}
										}else {
											appLog.error("Not able to click on all firms "+str[j]+" soritng icon so cannot check sorting in Desending Order");
											sa.assertTrue(false, "Not able to click on all firms "+str[j]+" soritng icon so cannot check sorting in Desending Order");
										}
									}else {
										appLog.error("all firms "+str[j]+" column soritng icon is not visible so cannot click on it");
										sa.assertTrue(false,"all firms "+str[j]+" column soritng icon is not visible so cannot click on it");
									}
								}
								break;
							}
						}
						if(i==lst.size()-1) {
							appLog.error(str[j]+" column is not visible so cannot check sorting on it");
							sa.assertTrue(false, str[j]+" column is not visible so cannot check sorting on it");
						}
					}
					
				}
			}else {
				appLog.error("all firms header is not displayed so cannot check soritng");
				sa.assertTrue(false, "all firms header is not displayed so cannot check soritng");
			}
			driver.navigate().refresh();
			List<WebElement> activityType=allfp.getContactFirmUpdateList();
			if(!activityType.isEmpty()) {
				for(int i=0; i<activityType.size(); i++) {
					scrollDownThroughWebelement(driver, activityType.get(i), "");	
					String aa=activityType.get(i).getText().trim();
					if(aa.equalsIgnoreCase("Firm Profile Updated")) {
						if(click(driver,activityType.get(i), "firm profile update link", action.SCROLLANDBOOLEAN)) {
							HashSet<String> newValue = CommonLib.scrollActiveWidgetforSetofFiles(driver,
									ivp.getFirmProfileUpdatePopUpScroll(30),
									By.xpath("//span[contains(@id,'grid_AccountProfileUpdated-cell-1-')]"));
							List<String> fieldList = new ArrayList<String>(newValue);
							if (!compareMultipleListWithoutAssertion(firmProfileData, fieldList)) {
								appLog.info("New value data Not Matched for Firm Profile Updated Alerts on all firms page page");
								sa.assertTrue(false, "New value data Not Matched for Firm Profile Updated Alerts on all firms page");
							} else {
								appLog.info("New value data is Matched for Firm Profile Updated Alerts");
							}
							lst =ivp.getFirmProfileUpdateHeaderTextList();
							String [] str1 = {"Field","New Value"};
							if(!lst.isEmpty()) {
								for(int j=0; j<str1.length; j++) {
									List<WebElement>fileName = new ArrayList<WebElement>();
									lst =ivp.getFirmProfileUpdateHeaderTextList();
									for(int i1=0; i1<lst.size(); i1++) {
										String aa1=lst.get(i1).getText().trim();
										if(aa1.contains(str1[j])) {
											if(click(driver, lst.get(i1), "firm profile update "+str1[j]+" text", action.SCROLLANDBOOLEAN)) {
												List<WebElement> lst1=ivp.getFirmProfileUpdateSortingIconList();
												if(!lst1.isEmpty()) {
													ThreadSleep(2000);
													if(click(driver,lst1.get(j), str1[j]+" sorting icon", action.SCROLLANDBOOLEAN)) {
														appLog.info("clicked on "+str1[j]+" soritng icon");
														if(j==0) {
															click(driver, lst1.get(j), str1[j]+" soting icon", action.SCROLLANDBOOLEAN);
															appLog.info("again clicked on "+str1[j]+" soritng icon");															
														}
														if(aa1.equalsIgnoreCase(str1[0])) {
															fileName=ivp.getFirmProfileUpdateFieldList();											
														}else if (aa1.equalsIgnoreCase(str1[1])) {
															fileName=ivp.getFirmProfileUpdateNewvalueList();
														}
														if(!fileName.isEmpty()){
															if(fpb.checkSorting(SortOrder.Decending, fileName)) {
																appLog.info(str1[j]+" cloumn data is  visible in Decending order");
															}else {
																appLog.error(str1[j]+" cloumn is not visible in Decending order after click on sorting icon");
																sa.assertTrue(false, str1[j]+" column is not visible in Decending order after click on sorting icon");
															}
															lst1=ivp.getFirmProfileUpdateSortingIconList();
															ThreadSleep(2000);
															if(click(driver, lst1.get(j),str1[j]+" sorting icon", action.SCROLLANDBOOLEAN)) {
																fileName.clear();
																if(aa1.equalsIgnoreCase(str1[0])) {
																	fileName=ivp.getFirmProfileUpdateFieldList();											
																}else if (aa1.equalsIgnoreCase(str1[1])) {
																	fileName=ivp.getFirmProfileUpdateNewvalueList();
																}
																if(fpb.checkSorting(SortOrder.Assecending, fileName)) {
																	appLog.info(str1[j]+" column data is visible in Assecending order");
																}else {
																	appLog.error(str1[j]+" data is not visible in Assecending order after click on sorting icon");
																	sa.assertTrue(false, str1[j]+" data is not visible in Assecending order after click on sorting icon");
																}
															}else {
																appLog.error("Not able to click on "+str1[j]+" sorting icon so cannot check sorting in Asending Order");
																sa.assertTrue(false, "Not able to click on "+str1[j]+" sorting icon so cannot check sorting in Asending Order");
															}
														}else {
															appLog.error(str1[j]+" data is not available in all document alert grid so cannot check soritng on it");
															sa.assertTrue(false, str1[j]+"data is not available in all document alert grid so cannot check soritng on it");
														}
													}else {
														appLog.error("Not able to click on firm profile update "+str1[j]+" soritng icon so cannot check sorting in Desending Order");
														sa.assertTrue(false, "Not able to click on firm profile update "+str1[j]+" soritng icon so cannot check sorting in Desending Order");
													}
												}else {
													appLog.error("recent activities "+str1[j]+" column soritng icon is not visible so cannot click on it");
													sa.assertTrue(false,"recent activities "+str1[j]+" column soritng icon is not visible so cannot click on it");
												}
											}
											
											break;
										}
										if(i1==lst.size()-1) {
											appLog.error(str1[j]+" column is not visible so cannot check sorting on it");
											sa.assertTrue(false, str1[j]+" column is not visible so cannot check sorting on it");
										}
									}
									
								}
							}else {
								appLog.error("firms update header is not displayed so cannot check soritng");
								sa.assertTrue(false, "firms profile update header is not displayed so cannot check soritng");
							}
							
							if(click(driver, ivp.getFirmProfileUpdateCloseBtn(30), "firm profile update close button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Click on close button");
							}else {
								appLog.error("Not able to click on close button so cannot close firm profile pop up");
								sa.assertTrue(false, "Not able to click on close button so cannot close firm profile pop up");
							}
						}else {
							appLog.error("Not able to click on firm profile update link so cannot check pop up data and sorting ");
							sa.assertTrue(false, "Not able to click on firm profile update link so cannot check pop up data and sorting ");
						}
						break;
					}
					if(i==activityType.size()-1){
						appLog.error("Firm Profile Updated alert is not available so cannot check data and sorting in firm profile update pop up");
						sa.assertTrue(false, "Firm Profile Updated alert is not available so cannot check data and sorting in firm profile update pop up");
					}
				}
			}else {
				appLog.error("all firms activity type list is not available in the grid so cannot click on firm update alert");
				sa.assertTrue(false, "all firms activity type list is not available in the grid so cannot click on firm update alert");
			}
			activityType.clear();
			activityType=allfp.getContactFirmUpdateList();
			if(!activityType.isEmpty()) {
				for(int i=0; i<activityType.size(); i++) {
					scrollDownThroughWebelement(driver, activityType.get(i), "");
					String aa=activityType.get(i).getText().trim();
					if(aa.equalsIgnoreCase("Firm Profile Updated")) {
						if(click(driver,activityType.get(i), "firm profile update link", action.SCROLLANDBOOLEAN)) {
							if(click(driver, ivp.getFirmprofileUpdateCrossIcon(30), "firm profile pop up cross icon", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on cross icon");
								if(ivp.getFirmProfileUpdateCloseBtn(10)==null) {
									appLog.info("firm profile update pop is closed");
								}else {
									appLog.error("firm profile update pop up is not closed after click on cross icon");
									sa.assertTrue(false, "firm profile update pop up is not closed after click on cross icon");
								}
							}else {
								appLog.error("Not able to click on firm profile update cross icon");
								sa.assertTrue(false, "Not able to click on firm profile update cross icon");
							}
						}else {
							appLog.error("Not able to click on firm profile update link so cannot check cross button functionality ");
							sa.assertTrue(false, "Not able to click on firm profile update link so cannot check cross button functionality ");
						}
						break;
					}if(i==activityType.size()-1){
						appLog.error("Firm Profile Updated alert is not available so cannot check cross button functionality");
						sa.assertTrue(false, "Firm Profile Updated alert is not available so cannot check cross button functionality");
					}				
				}
				
			}else {
				appLog.error("recent activities activity type list is not available in the grid so cannot click on firm update alert");
				sa.assertTrue(false, "recent activities activity type list is not available in the grid so cannot click on firm update alert");
			}
			activityType.clear();
			activityType=allfp.getContactFirmUpdateList();
			if(!activityType.isEmpty()) {
				String linkedin =ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Linkedin);
				String facebook =ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Facebook);
				String fristName=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Updated_FirstName);
				String LastName=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Updated_LastName);
				String title=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Title);
				phone=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Phone);
				MailingStreet=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Mailing_Street);
				MailingCity=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Mailing_City);
				MailingCounty=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Mailing_Country);
				MailingState=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Mailing_State);
				MailingZip=ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.Mailing_Zip);
				firmProfileData=null;
				firmProfileData=""+phone+"<break>"+title+"<break>"+LastName+"<break>"+fristName+"<break>"+MailingCity+"<break>"+MailingState+"<break>"+MailingStreet+"<break>"+MailingZip+"<break>"+facebook+"<break>"+linkedin+"<break>"+MailingCounty;
				for(int i=0; i<activityType.size(); i++) {
					scrollDownThroughWebelement(driver, activityType.get(i), "");
					String aa=activityType.get(i).getText().trim();
					if(aa.equalsIgnoreCase("Contact Profile Updated")) {
						if(click(driver,activityType.get(i), "Contact profile update link", action.SCROLLANDBOOLEAN)) {
							HashSet<String> newValue = CommonLib.scrollActiveWidgetforSetofFiles(driver,
									ivp.getContactProfileUpdatedPopUpScroll(30),
									By.xpath("//span[contains(@id,'grid_ContactProfileUpdated')][contains(@id,'cell-1')]"));
							List<String> fieldList = new ArrayList<String>(newValue);
							if (!compareMultipleListWithoutAssertion(firmProfileData, fieldList)) {
								appLog.info("New value data Not Matched for Contact Profile Updated Alerts on all firms page");
								sa.assertTrue(false, "New value data Not Matched for Contact Profile Updated Alerts on all firms page");
							} else {
								appLog.info("New value data is Matched for Contact Profile Updated Alerts on all firms page");
							}
							lst =ivp.getContactProfileUpdateHeaderTextList();
							String [] str1 = {"Field","New Value"};
							if(!lst.isEmpty()) {
								for(int j=0; j<str1.length; j++) {
									List<WebElement>fileName = new ArrayList<WebElement>();
									lst =ivp.getContactProfileUpdateHeaderTextList();
									for(int i1=0; i1<lst.size(); i1++) {
										String aa1=lst.get(i1).getText().trim();
										if(aa1.contains(str1[j])) {
											if(click(driver, lst.get(i1), "Contact profile update "+str1[j]+" text", action.SCROLLANDBOOLEAN)) {
												List<WebElement> lst1=ivp.getContactProfileUpdateSortingIcon();
												if(!lst1.isEmpty()) {
													ThreadSleep(2000);
													if(click(driver,lst1.get(j), str1[j]+" sorting icon", action.SCROLLANDBOOLEAN)) {
														appLog.info("clicked on "+str1[j]+" soritng icon");
//														if(j==0) {
//															click(driver, lst1.get(j), str1[j]+" soting icon", action.BOOLEAN);
//															appLog.info("again clicked on "+str1[j]+" soritng icon");															
//														}
														if(aa1.equalsIgnoreCase(str1[0])) {
															fileName=ivp.getContactProfileUpdateFieldList();										
														}else if (aa1.equalsIgnoreCase(str1[1])) {
															fileName=ivp.getContactProfileUpdateValueList();
														}
														if(!fileName.isEmpty()){
															if(fpb.checkSorting(SortOrder.Decending, fileName)) {
																appLog.info(str1[j]+" cloumn data is  visible in Decending order");
															}else {
																appLog.error(str1[j]+" cloumn is not visible in Decending order after click on sorting icon");
																sa.assertTrue(false, str1[j]+" column is not visible in Decending order after click on sorting icon");
															}
															lst1=ivp.getContactProfileUpdateSortingIcon();
															ThreadSleep(2000);
															if(click(driver, lst1.get(j),str1[j]+" sorting icon", action.SCROLLANDBOOLEAN)) {
																fileName.clear();
																if(aa1.equalsIgnoreCase(str1[0])) {
																	fileName=ivp.getContactProfileUpdateFieldList();										
																}else if (aa1.equalsIgnoreCase(str1[1])) {
																	fileName=ivp.getContactProfileUpdateValueList();
																}
																if(fpb.checkSorting(SortOrder.Assecending, fileName)) {
																	appLog.info(str1[j]+" column data is visible in Assecending order");
																}else {
																	appLog.error(str1[j]+" data is not visible in Assecending order after click on sorting icon");
																	sa.assertTrue(false, str1[j]+" data is not visible in Assecending order after click on sorting icon");
																}
															}else {
																appLog.error("Not able to click on "+str1[j]+" sorting icon so cannot check sorting in Asending Order");
																sa.assertTrue(false, "Not able to click on "+str1[j]+" sorting icon so cannot check sorting in Asending Order");
															}
														}else {
															appLog.error(str1[j]+" data is not available in all document alert grid so cannot check soritng on it");
															sa.assertTrue(false, str1[j]+"data is not available in all document alert grid so cannot check soritng on it");
														}
													}else {
														appLog.error("Not able to click on firm profile update "+str1[j]+" soritng icon so cannot check sorting in Desending Order");
														sa.assertTrue(false, "Not able to click on firm profile update "+str1[j]+" soritng icon so cannot check sorting in Desending Order");
													}
												}else {
													appLog.error("all firms "+str1[j]+" column soritng icon is not visible so cannot click on it");
													sa.assertTrue(false,"all firms "+str1[j]+" column soritng icon is not visible so cannot click on it");
												}
											}
											
											break;
										}
										if(i1==lst.size()-1) {
											appLog.error(str1[j]+" column is not visible so cannot check sorting on it");
											sa.assertTrue(false, str1[j]+" column is not visible so cannot check sorting on it");
										}
									}
									
								}
							}else {
								appLog.error("contact profile update header is not displayed so cannot check soritng");
								sa.assertTrue(false, "contact profile update header is not displayed so cannot check soritng");
							}
							
							if(click(driver, ivp.getContactProfileCloseBtn(30), "contact profile update close button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Click on close button");
							}else {
								appLog.error("Not able to click on close button so cannot close contact profile pop up");
								sa.assertTrue(false, "Not able to click on close button so cannot close contact profile pop up");
							}
						}else {
							appLog.error("Not able to click on contact profile update link so cannot check pop up data and sorting ");
							sa.assertTrue(false, "Not able to click on contact profile update link so cannot check pop up data and sorting ");
						}
						break;
					}
					if(i==activityType.size()-1){
						appLog.error("contact Profile Updated alert is not available so cannot check data and sorting in firm profile update pop up");
						sa.assertTrue(false, "contact Profile Updated alert is not available so cannot check data and sorting in firm profile update pop up");
					}
				}
			}else {
				appLog.error("all firms activity type list is not available in the grid so cannot click on contact update alert");
				sa.assertTrue(false, "all firms activity type list is not available in the grid so cannot click on contact update alert");
			}
			activityType.clear();
			activityType=allfp.getContactFirmUpdateList();
			if(!activityType.isEmpty()) {
				for(int i=0; i<activityType.size(); i++) {
					String aa=activityType.get(i).getText().trim();
					scrollDownThroughWebelement(driver, activityType.get(i), "");	
					if(aa.equalsIgnoreCase("Contact Profile Updated")) {
						if(click(driver,activityType.get(i), "Contact profile update link", action.SCROLLANDBOOLEAN)) {
							if(click(driver, ivp.getContactProfileUpdateCrossIcon(20), "contact profile pop up cross icon", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on cross icon");
								if(ivp.getContactProfileCloseBtn(10)==null) {
									appLog.info("contact profile update pop is closed");
								}else {
									appLog.error("contact profile update pop up is not closed after click on cross icon");
									sa.assertTrue(false, "contact profile update pop up is not closed after click on cross icon");
								}
							}else {
								appLog.error("Not able to click on contact profile update cross icon");
								sa.assertTrue(false, "Not able to click on contact profile update cross icon");
							}
						}else {
							appLog.error("Not able to click on contact profile update link so cannot check cross button functionality ");
							sa.assertTrue(false, "Not able to click on contact profile update link so cannot check cross button functionality ");
						}
						break;
					}if(i==activityType.size()-1){
						appLog.error("contact Profile Updated alert is not available so cannot check cross button functionality");
						sa.assertTrue(false, "contact Profile Updated alert is not available so cannot check cross button functionality");
					}				
				}
				
			}else {
				appLog.error("all firms activity type list is not available in the grid so cannot click on contact update alert");
				sa.assertTrue(false, "all firms activity type list is not available in the grid so cannot click on contact update alert");
			}
				 List<WebElement> fileName=allfp.getDocumentNameList();
						 if(!fileName.isEmpty()) {
							 for(int i=0; i<fileName.size(); i++) {
								 String name=fileName.get(i).getText().trim();
								 if(name.contains(".pdf")) {
									 scrollDownThroughWebelement(driver, fileName.get(0), "");
									 if(click(driver, fileName.get(0), name+" document name", action.SCROLLANDBOOLEAN)) {
										 String parentId=switchOnWindow(driver);
										 if(parentId!=null) {
											 if(ivp.getDocumentCloseBtn(60)!=null) {
												 appLog.info(name+" document is open successfully");
												 driver.close();
												 driver.switchTo().window(parentId);
											 }else {
												appLog.error("close button is not visible in open document");
												driver.close();
												driver.switchTo().window(parentId);
												sa.assertTrue(false, "close button is not visible in open document");
											}
										 }else {
											 appLog.error("document is not open");
											 sa.assertTrue(false, "document is not open");
										 }						 
									 }else {
										 appLog.error("Not able to click on document name "+name+" so cannot check doucment in box view mode");
										 sa.assertTrue(false, "Not able to click on document name "+name+" so cannot check doucment in box view mode");
									 }
									 break;
								 }if(i==fileName.size()-1) {
									 appLog.error("pdf file is not present in grid so cannot check doucment in box view mode");
									 sa.assertTrue(false, "pdf file is not present in grid so cannot check doucment in box view mode");
								 }
							}
						 }else {
							 appLog.error("files are not available in recent activities alert grid");
							 sa.assertTrue(false, "files are not available in recent activities alert grid");
						 }
						 String[] fundName= {M3FundName1,M3FundName2};
						 String investmentName;
						 for(int j=0; j<fundName.length; j++) {
							 List<WebElement> investmentNamelist=allfp.getInvesmentNameList();
							 List<WebElement> workspace=allfp.getWorkSpaceNameList();
							 if(j==0) {
								 investmentName="Current Investment";
							 }else {
								 investmentName="Potential Investment";
							}
							 if(!investmentNamelist.isEmpty()) {
								 for(int i=0; i<investmentNamelist.size(); i++) {
									 scrollDownThroughWebelement(driver, investmentNamelist.get(i), "");
									 if(investmentNamelist.get(i).getText().trim().contains(fundName[j]) && workspace.get(i).getText().trim().contains(investmentName)) {
										 if(click(driver, investmentNamelist.get(i), "invesment name link", action.SCROLLANDBOOLEAN)) {
											 String parentId=switchOnWindow(driver);
											 String aa=driver.getTitle().trim();
											 if(parentId!=null) {
												 if(aa.contains(investmentName)) {
													 appLog.info(investmentName+" page is open");
												 }else {
													 appLog.error(investmentName+" page is not open after click on "+fundName[j]);
													 sa.assertTrue(false, investmentName+" page is not open after click on "+fundName[j]);
												 }
												 driver.close();
												 driver.switchTo().window(parentId);									
											 }else {
												 appLog.error("no new window is open");
												 sa.assertTrue(false, "no new window is open");
											 }
										 }else {
											 appLog.error("Not able to click on invesment name:  "+fundName[j]);
											 sa.assertTrue(false, "Not able to click on invesment name:  "+fundName[j]);
										 }
										 break;
									 }
									 if(i==investmentNamelist.size()-1) {
										 appLog.error(fundName[j]+" and "+investmentName+" workspace is not available in recent activities alert grid so cannot click on it");
										 sa.assertTrue(false, fundName[j]+" and "+investmentName+" workspace is not available in recent activities alert grid so cannot click on it");
									 }
								 }
							 }else {
								appLog.error("Investment list is not available in content grid so cannot click on investment name link");
								sa.assertTrue(false, "Investment list is not available in content grid so cannot click on investment name link");
							}
						 }
		}else {
			appLog.error("Not able to select All firms from firm name drop down list so cannot check verious operation on alert grid");
			sa.assertTrue(false, "Not able to select All firms from firm name drop down list so cannot check verious operation on alert grid");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc072_1_uploadLogoForAdmin1AtCRMSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim= new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if(nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if(nim.clickOnSideMenusTab(sideMenu.Profiles)) {
				if(nim.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
						String logoName = ExcelUtils.readData("FilePath",0,19,currentlyExecutingTC);
						if (click(driver, nim.getChangeLogoLink(60), "Change Logo Link", action.SCROLLANDBOOLEAN)) {
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								String imagePath = System.getProperty("user.dir") + "//UploadFiles//Module3//logo//"+logoName;
								if (sendKeys(driver, nim.getBrowseButton(60), imagePath, "Browser Button", action.BOOLEAN)) {
									if (click(driver, nim.getSubmitButtonImageUpload(60), "Image Upload submit Button",
											action.BOOLEAN)) {
										if(bp.clickUsingCssSelectorPath("a[title='Save']", "upload image save button")) {
//										if (click(driver,nim.getSaveButtonImageUpload(60), "save Button", action.BOOLEAN)) {
											if (isAlertPresent(driver)) {
												if (!switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT)) {
													appLog.error("Not able to upload Logo.");
													sa.assertTrue(false, "Not able to upload Logo.");
												}
												driver.switchTo().window(parentID);
											} else {
												appLog.error("Upload Confirmation alert pop is not displayed for : "+logoName);
												driver.close();
												driver.switchTo().window(parentID);
												switchToFrame(driver,10,nim.getFrame( PageName.NavatarInvestorManager, 10));
												sa.assertTrue(false, "Upload Confirmation alert pop is not displayed for : "+logoName);
											}
											appLog.info("Successfully uploaded logo format : "+logoName);
											switchToFrame(driver,10,nim.getFrame( PageName.NavatarInvestorManager, 10));
											
										}else {
											appLog.error("save Button is not working for : "+logoName+" so cannot check the functionality.");
											driver.close();
											driver.switchTo().window(parentID);
											switchToFrame(driver,10,nim.getFrame( PageName.NavatarInvestorManager, 10));
											sa.assertTrue(false, "Crop Button is not working for: "+logoName+" so cannot check the functionality.");
										}
									}else {
										appLog.error("Save Button is not working so cannot check the functionality.");
										driver.close();
										driver.switchTo().window(parentID);
										switchToFrame(driver,10,nim.getFrame( PageName.NavatarInvestorManager, 10));
										sa.assertTrue(false, "Save Button is not working so cannot check the functionality.");
									}
								}else {
									appLog.error("Broswe Button is not working so cannot check functionality.");
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver,10,nim.getFrame( PageName.NavatarInvestorManager, 10));
									sa.assertTrue(false, "Broswe Button is not working so cannot check functionality.");
								}
							}else {
								appLog.info("Change logo Link is not working.");
								sa.assertTrue(false, "Change logo link is not working so cannot check the functionality.");
							}
						}else {
							appLog.error("Not able to click on change logo link so cannot upload logo");
							sa.assertTrue(false,"Not able to click on change logo link so cannot upload logo");
						}
				}else {
					appLog.error("Not able to click on my firm profile tab so cannot upload logo");
					sa.assertTrue(false, "Not able to click on my firm profile tab so cannot upload logo");
				}
			}else {
				appLog.error("Not able to click on profile tab so cannot upload logo");
				sa.assertTrue(false, "Not able to click on profile tab so cannot upload logo");
			}
		}else {
			appLog.error("Not able to click on NIM Tab so cannot upload logo");
			sa.assertTrue(false, "Not able to click on NIM Tab so cannot upload logo");
		}				
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc072_2_uploadLogoForAdmin2AtCRMSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim= new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg2UserName, adminPasswordOrg2);
		if(nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if(nim.clickOnSideMenusTab(sideMenu.Profiles)) {
				if(nim.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
						String logoName = ExcelUtils.readData("FilePath",0,19,currentlyExecutingTC);
						if (click(driver, nim.getChangeLogoLink(60), "Change Logo Link", action.SCROLLANDBOOLEAN)) {
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								String imagePath = System.getProperty("user.dir") + "//UploadFiles//Module3//logo//"+logoName;
								if (sendKeys(driver, nim.getBrowseButton(60), imagePath, "Browser Button", action.BOOLEAN)) {
									if (click(driver, nim.getSubmitButtonImageUpload(60), "Image Upload submit Button",
											action.BOOLEAN)) {
										if(bp.clickUsingCssSelectorPath("a[title='Save']", "upload image save button")) {
//										if (click(driver,nim.getSaveButtonImageUpload(60), "save Button", action.BOOLEAN)) {
											if (isAlertPresent(driver)) {
												if (!switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT)) {
													appLog.error("Not able to upload Logo.");
													sa.assertTrue(false, "Not able to upload Logo.");
												}
												driver.switchTo().window(parentID);
											} else {
												appLog.error("Upload Confirmation alert pop is not displayed for : "+logoName);
												driver.close();
												driver.switchTo().window(parentID);
												switchToFrame(driver,10,nim.getFrame( PageName.NavatarInvestorManager, 10));
												sa.assertTrue(false, "Upload Confirmation alert pop is not displayed for : "+logoName);
											}
											appLog.info("Successfully uploaded logo format : "+logoName);
											switchToFrame(driver,10,nim.getFrame( PageName.NavatarInvestorManager, 10));
											
										}else {
											appLog.error("save Button is not working for : "+logoName+" so cannot check the functionality.");
											driver.close();
											driver.switchTo().window(parentID);
											switchToFrame(driver,10,nim.getFrame( PageName.NavatarInvestorManager, 10));
											sa.assertTrue(false, "Crop Button is not working for: "+logoName+" so cannot check the functionality.");
										}
									}else {
										appLog.error("Save Button is not working so cannot check the functionality.");
										driver.close();
										driver.switchTo().window(parentID);
										switchToFrame(driver,10,nim.getFrame( PageName.NavatarInvestorManager, 10));
										sa.assertTrue(false, "Save Button is not working so cannot check the functionality.");
									}
								}else {
									appLog.error("Broswe Button is not working so cannot check functionality.");
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver,10,nim.getFrame( PageName.NavatarInvestorManager, 10));
									sa.assertTrue(false, "Broswe Button is not working so cannot check functionality.");
								}
							}else {
								appLog.info("Change logo Link is not working.");
								sa.assertTrue(false, "Change logo link is not working so cannot check the functionality.");
							}
						}else {
							appLog.error("Not able to click on change logo link so cannot upload logo");
							sa.assertTrue(false,"Not able to click on change logo link so cannot upload logo");
						}
				}else {
					appLog.error("Not able to click on my firm profile tab so cannot upload logo");
					sa.assertTrue(false, "Not able to click on my firm profile tab so cannot upload logo");
				}
			}else {
				appLog.error("Not able to click on profile tab so cannot upload logo");
				sa.assertTrue(false, "Not able to click on profile tab so cannot upload logo");
			}
		}else {
			appLog.error("Not able to click on NIM Tab so cannot upload logo");
			sa.assertTrue(false, "Not able to click on NIM Tab so cannot upload logo");
		}				
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc072_3_verifyUploadedLogoAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(allfp.selectFirmName("All Firms")) {
			List<WebElement> lst = allfp.getLogoListAtAllFirmsPage();
			if(!lst.isEmpty()) {
				if(lst.size()==2) {
					appLog.info("Uploaded logo is displaying on all firms page");
				}else {
					appLog.error("uploaded logo is not displaying on all firms page");
					sa.assertTrue(false, "uploaded logo is not displaying on all firms page");
				}
				if(click(driver, lst.get(0), Org1FirmName+" logo image", action.BOOLEAN)) {
					String aa =getSelectedOptionOfDropDown(driver, allfp.getFirmNameDropdown(60), "firm name drop down list", "text");
					if(aa.contains(Org1FirmName)) {
						appLog.info(Org1FirmName+" investor firm page is open");
						if(allfp.getLogoOnInvestorFirmsPage(60)!=null) {
							appLog.info(Org1FirmName+" logo is displaying at top of investor firm page");
						}else {
							appLog.error(Org1FirmName+" logo is not displaying at top of investor firm page");
							sa.assertTrue(false, Org1FirmName+" logo is not displaying at top of investor firm page");
						}
					}else {
						appLog.error(Org1FirmName+" investor firm page is open after click on logo");
						sa.assertTrue(false, Org1FirmName+" investor firm page is open after click on logo");
					}
					
				}else {
					appLog.error("Not able to click on 1'st logo so cannot verify investor firm page of :"+Org1FirmName);
					sa.assertTrue(false, "Not able to click on 1'st logo so cannot verify investor firm page of :"+Org1FirmName);
				}
				if(allfp.selectFirmName("All Firms")) {
					lst = allfp.getLogoListAtAllFirmsPage();
					if(click(driver, lst.get(1), Org2FirmName+" logo image", action.BOOLEAN)) {
						String aa =getSelectedOptionOfDropDown(driver, allfp.getFirmNameDropdown(60), "firm name drop down list", "text");
						if(aa.contains(Org2FirmName)) {
							appLog.info(Org2FirmName+" investor firm page is open");
							if(allfp.getLogoOnInvestorFirmsPage(60)!=null) {
								appLog.info(Org2FirmName+" logo is displaying at top of investor firm page");
							}else {
								appLog.error(Org2FirmName+" logo is not displaying at top of investor firm page");
								sa.assertTrue(false, Org2FirmName+" logo is not displaying at top of investor firm page");
							}
						}else {
							appLog.error(Org2FirmName+" investor firm page is open after click on logo");
							sa.assertTrue(false, Org2FirmName+" investor firm page is open after click on logo");
						}
						
					}else {
						appLog.error("Not able to click on 1'st logo so cannot verify investor firm page of :"+Org1FirmName);
						sa.assertTrue(false, "Not able to click on 1'st logo so cannot verify investor firm page of :"+Org1FirmName);
					}					
				}else {
					appLog.error("Not able to select all firms from firm name drop down list so cannot click on "+Org2FirmName+" logo");
					sa.assertTrue(false, "Not able to select all firms from firm name drop down list so cannot click on "+Org2FirmName+" logo");
				}
			}else {
				appLog.error("No logo is present at all firms page");
				sa.assertTrue(false, "No logo is present at all firms page");
			}
		}else {
			appLog.error("Not able to select all firms from firm name drop down list so cannot check uploaded logo");
			sa.assertTrue(false, "Not able to select all firms from firm name drop down list so cannot check uploaded logo");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	

	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc073_1_deleteUploadedDocInSTDFolderInFWRAtCRMSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		AllFirmsPageBusinesslayer allfp = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(allfp.selectFirmName(Org1FirmName)) {
			if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
				int aa=ivp.getAlertCount(TabName.RecentActivities, PageName.InvestorFirmPage,60);
				appLog.info("count successfully read from recent activities tab: "+aa);
				ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.Alert_Count);
			}else {
				appLog.error("Not able to click on recent activities tab so cannot read recent activities count");
				sa.assertTrue(false, "Not able to click on recent activities tab so cannot read recent activities count");
			}
			if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
				int aa=ivp.getAlertCount(TabName.AllDocuments, PageName.InvestorFirmPage,60);
				appLog.info("count successfully read from all documents tab: "+aa);
				ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);	
			}else {
				appLog.error("Not able to click on all documents tab so cannot read all documents count");
				sa.assertTrue(false, "Not able to click on recent activities tab so cannot read all documents count");
			}
		}else {
			appLog.error("Not able to select org "+Org1FirmName+" so cannot read count");
			sa.assertTrue(false, "Not able to select org "+Org1FirmName+" so cannot read count");
		}
			if(allfp.selectFirmName("All Firms")) {
				int aa=ivp.getAlertCount(null, PageName.AllFirmsPage,60);
				appLog.info("count successfully read from all firms page: "+aa);
				ExcelUtils.writeData(String.valueOf(aa), "FilePath",excelLabel.TestCases_Name,"M3tc003_inviteContactFromWorkSpace", excelLabel.AllFirms_Count);
			}else {
				appLog.error("Not able to select All firms from drop down list so cannot read all firms count");
				sa.assertTrue(false, "Not able to select All firms from drop down list so cannot read all firms count");
			}
			lp.investorLogout();
			driver.close();
			config(browserToLaunch);
			lp = new LoginPageBusinessLayer(driver);
			FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
			String path =ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
			lp.CRMLogin(CRMUser1EmailID,adminPassword);
			if(fp.clickOnTab(TabName.FundsTab)) {
				if(fp.clickOnCreatedFund(M3FundName1)) {
					switchToFrame(driver, 60, fp.getFrame( PageName.FundsPage, 60));
					if(fp.verifyFolderPathdummy(path, M3Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
						List<WebElement> lst=fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage);
						if(!lst.isEmpty()) {
							String docName1=lst.get(0).getText().trim();
							if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete, docName1,Workspace.FundraisingWorkspace, 60,
									"Yes")) {
								PublicFlag = false;
								if (click(driver, fp.getDocumentDeleteYesBtn(Workspace.FundraisingWorkspace,60), "Yes", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on File Delete Yes Button");
									if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
										if(docName1!=null) {
											List<String>result=compareMultipleList(driver,docName1,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
											if(!result.isEmpty()) {
												appLog.info("All files are verified: "+docName1+" document is deleted successfully");
												int allDocCount=Integer.parseInt(AllDocument_count)-1;
												ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);
												appLog.info("alert count should be at investor side: "+Alert_Count);
												appLog.info("All document count should be at investor side: "+allDocCount);
												appLog.info("All firms count should be at investor side: "+Allfirms_Count);
												ExcelUtils.writeData(docName1, "FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.UploadedFileStandard);
											}else {
												appLog.error(docName1+" is not deleted from content grid");
												sa.assertTrue(false, docName1+" is not deleted from content grid");
											}
										}
									}else {
										appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
										sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
									}
								} else {
									appLog.info("Not able to Click on Delete File Yes Button.");
									sa.assertTrue(false, "Not able to Click on Delete File Yes Button.");
								}
							} else {
								appLog.info("Not able to Click on Delete File Link.");
								sa.assertTrue(false, "Not able to Click on Delete File Link.");
							}
						}else {
							appLog.error("documents are not available in content grid so cannot delete document");
							sa.assertTrue(false, "documents are not available in content grid so cannot delete document");
						}
					}else {
						appLog.error("Folder path is not available : "+M3Institution1+"/"+path+" so cannot delete document");
						sa.assertTrue(false, "Folder path is not available : "+M3Institution1+"/"+path+" so cannot delete document");
					}
				}else {
					appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot delete files in fundraising workspace");
					sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot delete files in fundraising workspace");
				}
			}else {
				appLog.error("Not able to click on fund tab so cannot delete files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on fund tab so cannot delete files in fundraising workspace");
			}
			switchToDefaultContent(driver);
			lp.CRMlogout();
			sa.assertAll();
	}



	@Parameters({ "environment", "mode" }) @Test
	public void M3tc073_2_deleteUploadedDocInSTDFolderInINVAtCRMSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String path =ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				switchToFrame(driver, 60, fp.getFrame( PageName.FundsPage, 60));
				if(fp.verifyFolderPathdummy(path, M3Institution1, M3LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
					List<WebElement> lst=fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage);
					if(!lst.isEmpty()) {
						String docName2=lst.get(0).getText().trim();
						if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete, docName2,Workspace.InvestorWorkspace, 60,
								"Yes")) {
							PublicFlag = false;
							if (click(driver, fp.getDocumentDeleteYesBtn(Workspace.InvestorWorkspace,60), "Yes", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on File Delete Yes Button");
								if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
									if(docName2!=null) {
										List<String>result=compareMultipleList(driver,docName2,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
										if(!result.isEmpty()) {
											appLog.info("All files are verified: "+docName2+" document is deleted successfully");
											int allDocCount=Integer.parseInt(AllDocument_count)-1;
											ExcelUtils.writeData(String.valueOf(allDocCount), "FilePath",excelLabel.TestCases_Name, "M3tc003_inviteContactFromWorkSpace", excelLabel.AllDocument_Count);
											appLog.info("alert count should be at investor side: "+Alert_Count);
											appLog.info("All document count should be at investor side: "+allDocCount);
											appLog.info("All firms count should be at investor side: "+Allfirms_Count);
											ExcelUtils.writeData(docName2, "filePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
											
										}else {
											appLog.error(docName2+" is not deleted from content grid");
											sa.assertTrue(false, docName2+" is not deleted from content grid");
										}
									}
								}else {
									appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
									sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
								}
							} else {
								appLog.info("Not able to Click on Delete File Yes Button.");
								sa.assertTrue(false, "Not able to Click on Delete File Yes Button.");
							}
						} else {
							appLog.info("Not able to Click on Delete File Link.");
							sa.assertTrue(false, "Not able to Click on Delete File Link.");
						}
					}else {
						appLog.error("documents are not available in content grid so cannot delete document");
						sa.assertTrue(false, "documents are not available in content grid so cannot delete document");
					}
				}else {
					appLog.error("Folder path is not available : "+M3Institution1+"/"+M3LimitedPartner1+""+path+" so cannot delete document");
					sa.assertTrue(false, "Folder path is not available : "+M3Institution1+"/"+M3LimitedPartner1+"/"+path+" so cannot delete document");
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot upload files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot upload files in investor workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		}
	
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc073_3_verifyDeletedDocAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		String FWR_fileName=ExcelUtils.readData("FilePath",0,8, "M3tc073_1_deleteUploadedDocInSTDFolderInFWRAtCRMSide");
		String INV_fileName=ExcelUtils.readData("FilePath",0,8, "M3tc073_2_deleteUploadedDocInSTDFolderInINVAtCRMSide");
		String[] str= {FWR_fileName,INV_fileName};
			if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
				result=compareMultipleList(driver, FWR_fileName+"<break>"+INV_fileName,ivp.getAllDocumentFileNameList());
				if(!result.isEmpty()) {
					appLog.info("deleted document is not visible in all document alert grid: "+FWR_fileName+" , "+INV_fileName);
				}else {
					appLog.error("deleted document is visible in all document alert grid: "+FWR_fileName+" , "+INV_fileName);
				}
				if(ivp.verifyAlertCount(AllDocument_count, TabName.AllDocuments, PageName.InvestorFirmPage,30)) {
					appLog.info("All documents alert count is matched.");
				}else {
					appLog.error("All documents alert count is not matched");
					sa.assertTrue(false, "All documents alert count is not matched");
				}
			}else {
				appLog.error("Not able to click on all document tab so cannot verify all document alerts");
				sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
			}
			if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
				result.clear();
				result=compareMultipleList(driver, FWR_fileName+"<break>"+INV_fileName,ivp.getRecentActivitiesFileNameList());
				if(!result.isEmpty()) {
					for(int i=0; i<result.size(); i++) {
						appLog.error(result.get(i));
						sa.assertTrue(false, result.get(i));
					}
				}else {
					appLog.error("deleted document is visible in recent document alert grid: "+FWR_fileName+" , "+INV_fileName);
				}
				List<WebElement> lst = ivp.getRecentActivitiesFileNameList();
				for(int i=0; i<str.length; i++) {
					for(int j=0; j<lst.size(); j++) {
						scrollDownThroughWebelement(driver, lst.get(j), "");
						String aa=lst.get(j).getText().trim();
						if(aa.equalsIgnoreCase(str[i])) {
							if(click(driver, lst.get(j), aa+"document name link", action.BOOLEAN)) {
								String parentId=switchOnWindow(driver);
								if(parentId!=null) {
									if(ivp.getDocumentNotFound(60)!=null) {
										String a=ivp.getDocumentNotFound(30).getText().trim();
										if(a.contains(InvestorFirmPageErrorMessage.documentNotFoundMsg)) {
											appLog.info("error message is verified for document: "+str[i]);
										}else {
											appLog.error("error message is not verified. Expected: "+InvestorFirmPageErrorMessage.documentNotFoundMsg+" Actual: "+a);
											sa.assertTrue(false, "error message is not verified. Expected: "+InvestorFirmPageErrorMessage.documentNotFoundMsg+" Actual: "+a);
										}
									}else {
										appLog.error("document not found error message is not displayed in deleted document: "+aa);
										sa.assertTrue(false, "document not found error message is not displayed in deleted document: "+aa);
									}
									driver.close();
									driver.switchTo().window(parentId);
								}else {
									appLog.error("No new window is open");
									sa.assertTrue(false, "No new window is open");
								}
							}else {
								appLog.error("Not able to click on document name link: "+aa);
								sa.assertTrue(false, "Not able to click on document name link: "+aa);
							}
							break;
						}else {
							if(j==lst.size()-1) {
								appLog.error(str[i]+" is not present in recent activities alert grid");
								sa.assertTrue(false, str[i]+" is not present in recent activities alert grid");
							}
						}
						
					}
				}
				if(ivp.verifyAlertCount(Alert_Count, TabName.RecentActivities, PageName.InvestorFirmPage,30)) {
					appLog.info("recent activities alert count is matched");
				}else {
					appLog.error("recent activities alert count is not matched");
					sa.assertTrue(false, "recent activities alert count is not matched");
				}
				
			}else {
				appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
				sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
			}
			
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=compareMultipleList(driver, FWR_fileName+"<break>"+INV_fileName,allfp.getDocumentNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.error("deleted document is visible in all firms alert grid: "+FWR_fileName+" , "+INV_fileName);
			}
			List<WebElement> lst = allfp.getDocumentNameList();
			for(int i=0; i<str.length; i++) {
				for(int j=0; j<lst.size(); j++) {
					scrollDownThroughWebelement(driver, lst.get(j), "");
					String aa=lst.get(j).getText().trim();
					if(aa.equalsIgnoreCase(str[i])) {
						if(click(driver, lst.get(j), aa+"document name link", action.BOOLEAN)) {
							String parentId=switchOnWindow(driver);
							if(parentId!=null) {
								if(ivp.getDocumentNotFound(60)!=null) {
									String a=ivp.getDocumentNotFound(30).getText().trim();
									if(a.contains(InvestorFirmPageErrorMessage.documentNotFoundMsg)) {
										appLog.info("error message is verified for document: "+str[i]);
									}else {
										appLog.error("error message is not verified. Expected: "+InvestorFirmPageErrorMessage.documentNotFoundMsg+" Actual: "+a);
										sa.assertTrue(false, "error message is not verified. Expected: "+InvestorFirmPageErrorMessage.documentNotFoundMsg+" Actual: "+a);
									}
								}else {
									appLog.error("document not found error message is not displayed in deleted document: "+aa);
									sa.assertTrue(false, "document not found error message is not displayed in deleted document: "+aa);
								}
								driver.close();
								driver.switchTo().window(parentId);
							}else {
								appLog.error("No new window is open");
								sa.assertTrue(false, "No new window is open");
							}
						}else {
							appLog.error("Not able to click on document name link: "+aa);
							sa.assertTrue(false, "Not able to click on document name link: "+aa);
						}
						break;
					}else {
						if(j==lst.size()-1) {
							appLog.error(str[i]+" is not present in all firms alert grid");
							sa.assertTrue(false, str[i]+" is not present in all firms alert grid");
						}
					}
				}
			}
			if(ivp.verifyAlertCount(Allfirms_Count, null, PageName.AllFirmsPage,30)) {
				appLog.info("All firms alert count is matched");
			}else {
				appLog.error("All firms alert count is not matched");
				sa.assertTrue(false, "All firms alert count is not matched");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	

	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc074_1_deleteInvitedFolderInSTDFolderInFWRAtCRMSide() {
			LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
			FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
			String STDpath =ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
			String Sharedpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
			lp.CRMLogin(CRMUser1EmailID,adminPassword);
			if(fp.clickOnTab(TabName.FundsTab)) {
				if(fp.clickOnCreatedFund(M3FundName1)) {
					switchToFrame(driver, 60, fp.getFrame( PageName.FundsPage, 60));
					if(click(driver,fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 20), "manage folder icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on manage foler icon");
						ThreadSleep(3000);
						String id=fp.getCreatedFolderId(STDpath, PageName.FundsPage,60);
						if(id!=null) {
							if(fp.clickOnDeleteFolderButton(id)) {
								ThreadSleep(2000);
								if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 60), "folder delete yes button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on "+STDpath.split("/")[1]+" folder delete yes button");
									
								}else {
									appLog.error("Not able to click on folder delete Yes button so cannot delete foler: "+STDpath.split("/")[1]);
									sa.assertTrue(false, "Not able to click on folder delete Yes button so cannot delete foler: "+STDpath.split("/")[1]);
								}
							}else {
								appLog.error("Not able to click on folder : "+STDpath.split("/")[1]+" delete icon so cannot delete folder in fundraising workspace");
								sa.assertTrue(false, "Not able to click on folder : "+STDpath.split("/")[1]+" delete icon so cannot delete folder in fundraising workspace");
							}
						}else {
							appLog.error("Not able to get child folder: "+STDpath.split("/")[1]+" id cannot delete it");
							sa.assertTrue(false, "Not able to get child folder: "+STDpath.split("/")[1]+" id cannot delete it");
						}
						ThreadSleep(5000);
						id=fp.getCreatedFolderId(Sharedpath, PageName.FundsPage,60);
						if(id!=null) {
							if(fp.clickOnDeleteFolderButton(id)) {
								ThreadSleep(2000);
								if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 60), "folder delete yes button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on "+Sharedpath.split("/")[1]+" folder delete yes button");
									
								}else {
									appLog.error("Not able to click on folder delete Yes button so cannot delete foler: "+Sharedpath.split("/")[1]);
									sa.assertTrue(false, "Not able to click on folder delete Yes button so cannot delete foler: "+Sharedpath.split("/")[1]);
								}
							}else {
								appLog.error("Not able to click on folder : "+Sharedpath.split("/")[1]+" delete icon so cannot delete folder in fundraising workspace");
								sa.assertTrue(false, "Not able to click on folder : "+Sharedpath.split("/")[1]+" delete icon so cannot delete folder in fundraising workspace");
							}
						}else {
							appLog.error("Not able to get child folder: "+Sharedpath.split("/")[1]+" id cannot delete it");
							sa.assertTrue(false, "Not able to get child folder: "+Sharedpath.split("/")[1]+" id cannot delete it");
						}
						if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 30), "manage folder close icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on manage folder close icon");
						}else {
							appLog.error("Not able to click on manage folder close icon");
							sa.assertTrue(false, "Not able to click on manage folder close icon");
						}
					}else {
						appLog.error("Not able to click on fundraising manage folder icon so cannot delete folder : "+STDpath.split("/")[1]);
						sa.assertTrue(false, "Not able to click on fundraising manage folder icon so cannot delete folder : "+STDpath.split("/")[1]);
					}
					
				}else {
					appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot delete files in fundraising workspace");
					sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot delete files in fundraising workspace");
				}
			}else {
				appLog.error("Not able to click on fund tab so cannot delete files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on fund tab so cannot delete files in fundraising workspace");
			}
			switchToDefaultContent(driver);
			lp.CRMlogout();
			sa.assertAll();
	}
	


	@Parameters({ "environment", "mode" }) @Test
	public void M3tc074_2_deleteInvitedFolderInSTDFolderInINVAtCRMSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String STDpath =ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
		String Sharedpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
				if(fp.clickOnTab(TabName.FundsTab)) {
					if(fp.clickOnCreatedFund(M3FundName1)) {
						switchToFrame(driver, 60, fp.getFrame( PageName.FundsPage, 60));
						if(click(driver,fp.getManageFolderIcon(Workspace.InvestorWorkspace, 20), "manage folder icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on manage foler icon");
							ThreadSleep(3000);
							String id=fp.getCreatedFolderId(STDpath, PageName.FundsPage,60);
							if(id!=null) {
								if(fp.clickOnDeleteFolderButton(id)) {
									ThreadSleep(2000);
									if(click(driver, fp.getFolderDeleteYesBtn(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 60), "folder delete yes button", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on "+STDpath.split("/")[1]+" folder delete yes button");
									}else {
										appLog.error("Not able to click on folder delete Yes button so cannot delete foler: "+STDpath.split("/")[1]);
										sa.assertTrue(false, "Not able to click on folder delete Yes button so cannot delete foler: "+STDpath.split("/")[1]);
									}
								}else {
									appLog.error("Not able to click on folder : "+STDpath.split("/")[1]+" delete icon so cannot delete folder in fundraising workspace");
									sa.assertTrue(false, "Not able to click on folder : "+STDpath.split("/")[1]+" delete icon so cannot delete folder in fundraising workspace");
								}
							}else {
								appLog.error("Not able to get child folder: "+STDpath.split("/")[1]+" id cannot delete it");
								sa.assertTrue(false, "Not able to get child folder: "+STDpath.split("/")[1]+" id cannot delete it");
							}
							ThreadSleep(5000);
							id=fp.getCreatedFolderId(Sharedpath, PageName.FundsPage,60);
							if(id!=null) {
								if(fp.clickOnDeleteFolderButton(id)) {
									ThreadSleep(2000);
									if(click(driver, fp.getFolderDeleteYesBtn(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 60), "folder delete yes button", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on "+Sharedpath.split("/")[1]+" folder delete yes button");
									}else {
										appLog.error("Not able to click on folder delete Yes button so cannot delete foler: "+Sharedpath.split("/")[1]);
										sa.assertTrue(false, "Not able to click on folder delete Yes button so cannot delete foler: "+Sharedpath.split("/")[1]);
									}
								}else {
									appLog.error("Not able to click on folder : "+Sharedpath.split("/")[1]+" delete icon so cannot delete folder in fundraising workspace");
									sa.assertTrue(false, "Not able to click on folder : "+Sharedpath.split("/")[1]+" delete icon so cannot delete folder in fundraising workspace");
								}
							}else {
								appLog.error("Not able to get child folder: "+Sharedpath.split("/")[1]+" id cannot delete it");
								sa.assertTrue(false, "Not able to get child folder: "+Sharedpath.split("/")[1]+" id cannot delete it");
							}
							if(click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 30), "manage folder close icon", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on manage folder close icon");
							}else {
								appLog.error("Not able to click on manage folder close icon");
								sa.assertTrue(false, "Not able to click on manage folder close icon");
							}
						}else {
							appLog.error("Not able to click on investor manage folder icon so cannot delete folder : "+STDpath.split("/")[1]);
							sa.assertTrue(false, "Not able to click on investor manage folder icon so cannot delete folder : "+STDpath.split("/")[1]);
						}
					}else {
						appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot delete files in investor workspace");
						sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot delete files in investor workspace");
					}
				}else {
					appLog.error("Not able to click on fund tab so cannot delete files in investor workspace");
					sa.assertTrue(false, "Not able to click on fund tab so cannot delete files in investor workspace");
				}
				switchToDefaultContent(driver);
				lp.CRMlogout();
				sa.assertAll();
		}
	

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc074_3_verifyDeletedFolderFirmsNameAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(allfp.selectFirmName(Org1FirmName)) {
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=compareMultipleList(driver, M3FundName1, ivp.getAllDocumentInvestmentNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
				appLog.info(M3FundName1+"investment name is not present in the all document alert grid");
			}else {
				appLog.error(M3FundName1+"investment name is present in the all document alert grid");
				sa.assertTrue(false, M3FundName1+"investment name is present in the all document alert grid");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result=compareMultipleList(driver, M3FundName1, ivp.getRecentActivitiestInvestmentNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
				appLog.info(M3FundName1+"investment name is not present in the recent activities alert grid");
			}else {
				appLog.error(M3FundName1+"investment name is present in the recent activities alert grid");
				sa.assertTrue(false, M3FundName1+"investment name is present in the recent activities alert grid");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=compareMultipleList(driver, M3FundName1, allfp.getInvesmentNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
				appLog.info(M3FundName1+"investment name is not present in the all firms alert grid");
			}else {
				appLog.error(M3FundName1+"investment name is present in the all firms alert grid");
				sa.assertTrue(false, M3FundName1+"investment name is present in the all firms alert grid");
			}
		}
		}else {
			appLog.error("Not able to select org "+Org1FirmName+" so cannot read count");
			sa.assertTrue(false, "Not able to select org "+Org1FirmName+" so cannot read count");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc075_1_createDeletedFolderInSTDFolderInFWRAtCRMSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String STDpath =ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
		String[] splitedSTDFolder=STDpath.split("/");
		String Sharedpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
		String[] splitedSharedFolders=Sharedpath.split("/");
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				switchToFrame(driver, 60, fp.getFrame( PageName.FundsPage, 60));
				if(click(driver,fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 20), "manage folder icon", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on manage foler icon");
					ThreadSleep(3000);
					String id=fp.getCreatedFolderId(splitedSTDFolder[0], PageName.FundsPage,60);
					if(id!=null) {
						if(fp.clickOnAddFolderButton(id)) {
							appLog.info("clicked on add folder button: "+splitedSTDFolder[0]);
							ThreadSleep(3000);
							if(fp.createChildFolder(splitedSTDFolder[1], Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30)) {
								appLog.info("child folder is created successfully : "+splitedSTDFolder[1]);
							}else {
								appLog.error("Not able to create child folder : "+splitedSTDFolder[1]);
								sa.assertTrue(false, "Not able to create child folder : "+splitedSTDFolder[1]);
							}
						}else {
							appLog.error("Not able to click on folder :"+splitedSTDFolder[0]+" add button so cannot created child folder: "+splitedSTDFolder[1]);
							sa.assertTrue(false, "Not able to click on folder :"+splitedSTDFolder[0]+" add button so cannot created child folder: "+splitedSTDFolder[1]);
						}
					}else {
						appLog.error("Not able to get folder parent folder Id so cannot click on parent folder add button: "+splitedSTDFolder[0]);
						sa.assertTrue(false, "Not able to get folder parent folder Id so cannot click on parent folder add button: "+splitedSTDFolder[0]);
					}
					
					ThreadSleep(5000);
					id=fp.getCreatedFolderId(splitedSharedFolders[0], PageName.FundsPage,60);
					if(id!=null) {
						if(fp.clickOnAddFolderButton(id)) {
							appLog.info("clicked on add folder button: "+splitedSharedFolders[0]);
							ThreadSleep(3000);
							if(fp.createChildFolder(splitedSharedFolders[1], Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30)) {
								appLog.info("child folder is created successfully : "+splitedSharedFolders[1]);
							}else {
								appLog.error("Not able to create child folder : "+splitedSharedFolders[1]);
								sa.assertTrue(false, "Not able to create child folder : "+splitedSharedFolders[1]);
							}
						}else {
							appLog.error("Not able to click on folder :"+splitedSharedFolders[0]+" add button so cannot created child folder: "+splitedSharedFolders[1]);
							sa.assertTrue(false, "Not able to click on folder :"+splitedSharedFolders[0]+" add button so cannot created child folder: "+splitedSharedFolders[1]);
						}
					}else {
						appLog.error("Not able to get folder parent folder Id so cannot click on parent folder add button: "+splitedSharedFolders[0]);
						sa.assertTrue(false, "Not able to get folder parent folder Id so cannot click on parent folder add button: "+splitedSharedFolders[0]);
					}
					if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 30), "manage folder close icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on manage folder close icon");
					}else {
						appLog.error("Not able to click on manage folder close icon");
						sa.assertTrue(false, "Not able to click on manage folder close icon");
					}
				}else {
					appLog.error("Not able to click on fundraising workspace manage folder icon so cannot delete folder : "+splitedSTDFolder[1]+splitedSharedFolders[1]);
					sa.assertTrue(false, "Not able to click on fundraising workspace manage folder icon so cannot delete folder : "+splitedSTDFolder[1]+splitedSharedFolders[1]);
				}
				if(click(driver,fp.getManageFolderIcon(Workspace.InvestorWorkspace, 20), "manage folder icon", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on manage foler icon");
					ThreadSleep(3000);
					String id=fp.getCreatedFolderId(splitedSTDFolder[0], PageName.FundsPage,60);
					if(id!=null) {
						if(fp.clickOnAddFolderButton(id)) {
							appLog.info("clicked on add folder button: "+splitedSTDFolder[0]);
							ThreadSleep(3000);
							if(fp.createChildFolder(splitedSTDFolder[1], Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30)) {
								appLog.info("child folder is created successfully : "+splitedSTDFolder[1]);
							}else {
								appLog.error("Not able to create child folder : "+splitedSTDFolder[1]);
								sa.assertTrue(false, "Not able to create child folder : "+splitedSTDFolder[1]);
							}
						}else {
							appLog.error("Not able to click on folder :"+splitedSTDFolder[0]+" add button so cannot created child folder: "+splitedSTDFolder[1]);
							sa.assertTrue(false, "Not able to click on folder :"+splitedSTDFolder[0]+" add button so cannot created child folder: "+splitedSTDFolder[1]);
						}
					}else {
						appLog.error("Not able to get folder parent folder Id so cannot click on parent folder add button: "+splitedSTDFolder[0]);
						sa.assertTrue(false, "Not able to get folder parent folder Id so cannot click on parent folder add button: "+splitedSTDFolder[0]);
					}
					
					ThreadSleep(5000);
					id=fp.getCreatedFolderId(splitedSharedFolders[0], PageName.FundsPage,60);
					if(id!=null) {
						if(fp.clickOnAddFolderButton(id)) {
							appLog.info("clicked on add folder button: "+splitedSharedFolders[0]);
							ThreadSleep(3000);
							if(fp.createChildFolder(splitedSharedFolders[1], Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30)) {
								appLog.info("child folder is created successfully : "+splitedSharedFolders[1]);
							}else {
								appLog.error("Not able to create child folder : "+splitedSharedFolders[1]);
								sa.assertTrue(false, "Not able to create child folder : "+splitedSharedFolders[1]);
							}
						}else {
							appLog.error("Not able to click on folder :"+splitedSharedFolders[0]+" add button so cannot created child folder: "+splitedSharedFolders[1]);
							sa.assertTrue(false, "Not able to click on folder :"+splitedSharedFolders[0]+" add button so cannot created child folder: "+splitedSharedFolders[1]);
						}
					}else {
						appLog.error("Not able to get folder parent folder Id so cannot click on parent folder add button: "+splitedSharedFolders[0]);
						sa.assertTrue(false, "Not able to get folder parent folder Id so cannot click on parent folder add button: "+splitedSharedFolders[0]);
					}
						
					if(click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 30), "manage folder close icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on manage folder close icon");
					}else {
						appLog.error("Not able to click on manage folder close icon");
						sa.assertTrue(false, "Not able to click on manage folder close icon");
					}
				}else {
					appLog.error("Not able to click on investor workspace manage folder icon so cannot delete folder : "+splitedSTDFolder[1]+splitedSharedFolders[1]);
					sa.assertTrue(false, "Not able to click on investor workspace manage folder icon so cannot delete folder : "+splitedSTDFolder[1]+splitedSharedFolders[1]);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot delete files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot delete files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot delete files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot delete files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc075_2_inviteAgianM3Contact1AndUploadDocInFWR(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String STDFolderpath=ExcelUtils.readData("FilePath",0, 4, currentlyExecutingTC);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.inviteContact(environment,mode,M3Institution1, M3Contact1EmailId,STDFolderpath, FolderType.Standard,"Upload","Yes", null,"All Folders", Workspace.FundraisingWorkspace, M3Contact1EmailId)) {
					appLog.info("contact is invites from fundraising workspace successfully: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}
				String FWR_docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\Standard1";
				if(fp.uploadFile(STDFolderpath,M3Institution1, FWR_docpath,null,UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
							}
						}
					}else {
						appLog.error("Not able to click on fundraising refresh icon so cannot verify uploaded document");
						sa.assertTrue(false, "Not able to click on fundraising refresh icon so cannot verify uploaded document");
					}
				}else {
					appLog.error("file is not uploaded in fundraising workspace folder: "+STDFolderpath);
					sa.assertTrue(false, "file is not uploaded in fundraising workspace folder: "+STDFolderpath);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact from fund: "+M3FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact from fund: "+M3FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact from fund: "+M3FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact from: "+M3FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc075_3_inviteAgianM3Contact1AndUploadDocInINV(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String STDFolderpath=ExcelUtils.readData("FilePath",0, 4, currentlyExecutingTC);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.inviteContact(environment,mode,M3Institution1+"/"+M3LimitedPartner1, M3Contact1EmailId,STDFolderpath, FolderType.Standard,"Upload","Yes", null,"All Folders", Workspace.InvestorWorkspace, M3Contact1EmailId)) {
					appLog.info("contact is invites from investor workspace successfully: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}else {
					appLog.error("Not able to invite contact from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}
				String INV_docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\InvestorWorkSpace\\Standard1";
				if(fp.uploadFile(STDFolderpath,M3Institution1+"/"+M3LimitedPartner1, INV_docpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
						if(filesName!=null) {
							List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
					}
				}else {
					appLog.error("file is not uploaded in investor workspace folder: "+STDFolderpath);
					sa.assertTrue(false, "file is not uploaded in investor workspace folder: "+STDFolderpath);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact from fund: "+M3FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact from fund: "+M3FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact from fund: "+M3FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact from: "+M3FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc075_4_verifyUploadedDocAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		String FWR_STD=ExcelUtils.readData("FilePath",0, 8, "M3tc075_2_inviteAgianM3Contact1AndUploadDocInFWR");
		String INV_STD=ExcelUtils.readData("FilePath",0, 8, "M3tc075_3_inviteAgianM3Contact1AndUploadDocInINV");
		
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(allfp.selectFirmName(Org1FirmName)) {
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=compareMultipleList(driver, FWR_STD+"<break>"+INV_STD, ivp.getAllDocumentFileNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.error("document is present in the all document alert grid");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result=compareMultipleList(driver, FWR_STD+"<break>"+INV_STD, ivp.getRecentActivitiesFileNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.error("document is present in the recent activities alert grid");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=compareMultipleList(driver, FWR_STD+"<break>"+INV_STD, allfp.getDocumentNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.error("document is present in the all firms alert grid");
			}
		}
		}else {
			appLog.error("Not able to select org "+Org1FirmName+" so cannot read count");
			sa.assertTrue(false, "Not able to select org "+Org1FirmName+" so cannot read count");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc076_1_removeInstitutionAndLPFromManageTargetAtCRMSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				switchToFrame(driver, 60, fp.getFrame( PageName.FundsPage, 60));
				if(click(driver,fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "manage investor icon", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on manage investor icon");
					ThreadSleep(3000);
					if(fp.selectInstitutionOrLP(M3Institution1, Workspace.FundraisingWorkspace, 60)) {
						appLog.info(M3Institution1+" is removed from manage investor");
						if(click(driver, fp.getManageInvestorDeletedPopupCloseButton(Workspace.FundraisingWorkspace, 60), "manage investor remove pop up close button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on close button");
						}else {
							appLog.error("manage investor remove pop up close buttton is not present");
							sa.assertTrue(false, "manage investor remove pop up close buttton is not present");
						}
					}else {
						appLog.error("Not able to remove institution from manage investor: "+M3Institution1);
						sa.assertTrue(false, "Not able to remove institution from manage investor: "+M3Institution1);
					}
					if(click(driver, fp.getManageInvestorDoneButton(Workspace.FundraisingWorkspace, 30), "manage investor close button", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on manage investor close button");
					}else {
						appLog.error("Not able to close manage investor pop up");
						sa.assertTrue(false, "Not able to close manage investor pop up");
					}
					}else {
						appLog.error("Not able to click on manage investor icon so cannot remove institution : "+M3Institution1);
						sa.assertTrue(false, "Not able to click on manage investor icon so cannot remove institution : "+M3Institution1);
					}
				if(click(driver,fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "manage investor icon", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on manage investor icon");
					ThreadSleep(3000);
					if(fp.selectInstitutionOrLP(M3Institution1+"/"+M3LimitedPartner1, Workspace.InvestorWorkspace, 60)) {
						appLog.info(M3LimitedPartner1+" is removed from manage investor");
						if(click(driver, fp.getManageInvestorDeletedPopupCloseButton(Workspace.InvestorWorkspace, 60), "manage investor remove pop up close button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on close button");
						}else {
							appLog.error("manage investor remove pop up close buttton is not present");
							sa.assertTrue(false, "manage investor remove pop up close buttton is not present");
						}
						
					}else {
						appLog.error("Not able to remove institution from manage investor: "+M3LimitedPartner1);
						sa.assertTrue(false, "Not able to remove institution from manage investor: "+M3LimitedPartner1);
					}
					if(click(driver, fp.getManageInvestorDoneButton(Workspace.InvestorWorkspace, 30), "manage investor close button", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on manage investor close button");
					}else {
						appLog.error("Not able to close manage investor pop up");
						sa.assertTrue(false, "Not able to close manage investor pop up");
					}
				}else {
					appLog.error("Not able to click on manage investor icon so cannot remove institution : "+M3LimitedPartner1);
					sa.assertTrue(false, "Not able to click on manage investor icon so cannot remove institution : "+M3LimitedPartner1);
				}
					}else {
						appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot delete files in fundraising workspace");
						sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot delete files in fundraising workspace");
					}
		}else {
			appLog.error("Not able to click on fund tab so cannot delete files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot delete files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc076_2_verifyDeletedAccountFirmsNameAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(allfp.selectFirmName(Org1FirmName)) {
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=compareMultipleList(driver, M3FundName1, ivp.getAllDocumentInvestmentNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
				appLog.info(M3FundName1+"investment name is not present in the all document alert grid");
			}else {
				appLog.error(M3FundName1+"investment name is present in the all document alert grid");
				sa.assertTrue(false, M3FundName1+"investment name is present in the all document alert grid");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result=compareMultipleList(driver, M3FundName1, ivp.getRecentActivitiestInvestmentNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
				appLog.info(M3FundName1+"investment name is not present in the recent activities alert grid");
			}else {
				appLog.error(M3FundName1+"investment name is present in the recent activities alert grid");
				sa.assertTrue(false, M3FundName1+"investment name is present in the recent activities alert grid");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=compareMultipleList(driver, M3FundName1, allfp.getInvesmentNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
				appLog.info(M3FundName1+"investment name is not present in the all firms alert grid");
			}else {
				appLog.error(M3FundName1+"investment name is present in the all firms alert grid");
				sa.assertTrue(false, M3FundName1+"investment name is present in the all firms alert grid");
			}
		}
		}else {
			appLog.error("Not able to select org "+Org1FirmName+" so cannot read count");
			sa.assertTrue(false, "Not able to select org "+Org1FirmName+" so cannot read count");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc077_1_addInstitutionFromManageTargetAndUploadDocInFWRAtCRMSide(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String STDFolderpath=ExcelUtils.readData("FilePath",0, 4, currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				switchToFrame(driver, 60, fp.getFrame( PageName.FundsPage, 60));
				if(click(driver,fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "manage investor icon", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on manage investor icon");
					ThreadSleep(3000);
					if(fp.selectInstitutionOrLP(M3Institution1, Workspace.FundraisingWorkspace, 60)) {
						appLog.info(M3Institution1+" is add from manage investor");
						if(click(driver, fp.getManageInvestorAddedPopupCloseButton(Workspace.FundraisingWorkspace, 60), "manage investor add pop up close button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on close button");
						}else {
							appLog.error("manage investor add pop up close buttton is not present");
							sa.assertTrue(false, "manage investor add pop up close buttton is not present");
						}
						if(click(driver, fp.getManageInvestorDoneButton(Workspace.FundraisingWorkspace, 30), "manage investor close button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on manage investor close button");
						}else {
							appLog.error("Not able to close manage investor pop up");
							sa.assertTrue(false, "Not able to close manage investor pop up");
						}
						switchToDefaultContent(driver);
						if(fp.inviteContact(environment,mode,M3Institution1, M3Contact1EmailId,null, FolderType.Standard,"Upload","Yes", null,"All Folders", Workspace.FundraisingWorkspace, M3Contact1EmailId)) {
							appLog.info("contact is invites from fundraising workspace successfully: "+M3Contact1FirstName+" "+M3Contact1LastName);
							String FWR_docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\Standard1";
							if(fp.uploadFile(STDFolderpath,M3Institution1, FWR_docpath,null,UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
								appLog.info("File is upload successfullly");
								switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
								scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "fundraising workspace view");
								if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
									String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
									if(filesName!=null) {
										List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
										if(!result.isEmpty()) {
											for(int i=0;i<result.size();i++) {
												sa.assertTrue(false, result.get(i));
											}
										}else {
											appLog.info("All files are verified: "+filesName);
										}
									}
								}else {
									appLog.error("Not able to click on fundraising refresh icon so cannot verify uploaded document");
									sa.assertTrue(false, "Not able to click on fundraising refresh icon so cannot verify uploaded document");
								}
							}else {
								appLog.error("file is not uploaded in fundraising workspace folder: "+STDFolderpath);
								sa.assertTrue(false, "file is not uploaded in fundraising workspace folder: "+STDFolderpath);
							}
						}else {
							appLog.error("Not able to invite contact from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
							sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
						}
					}else {
						appLog.error("Not able to add institution from manage investor: "+M3Institution1);
						sa.assertTrue(false, "Not able to add institution from manage investor: "+M3Institution1);
					}
					
					}else {
						appLog.error("Not able to click on manage investor icon so cannot add institution : "+M3Institution1);
						sa.assertTrue(false, "Not able to click on manage investor icon so cannot add institution : "+M3Institution1);
					}
				}else {
					appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot add institution in fundraising workspace");
					sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot add institution in fundraising workspace");
				}
		}else {
			appLog.error("Not able to click on fund tab so cannot add institution in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot add institution in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc077_2_addLPFromManageTargetAndUploadDocInINVAtCRMSide(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String STDFolderpath=ExcelUtils.readData("FilePath",0, 4, currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				switchToFrame(driver, 60, fp.getFrame( PageName.FundsPage, 60));
				if(click(driver,fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "manage investor icon", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on manage investor icon");
					ThreadSleep(3000);
					if(fp.selectInstitutionOrLP(M3Institution1+"/"+M3LimitedPartner1, Workspace.InvestorWorkspace, 60)) {
						appLog.info(M3LimitedPartner1+" is add from manage investor");
						if(click(driver, fp.getManageInvestorAddedPopupCloseButton(Workspace.InvestorWorkspace, 60), "manage investor add pop up close button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on close button");
						}else {
							appLog.error("manage investor add pop up close buttton is not present");
							sa.assertTrue(false, "manage investor add pop up close buttton is not present");
						}
						if(click(driver, fp.getManageInvestorDoneButton(Workspace.InvestorWorkspace, 30), "manage investor close button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on manage investor close button");
						}else {
							appLog.error("Not able to close manage investor pop up");
							sa.assertTrue(false, "Not able to close manage investor pop up");
						}
						switchToDefaultContent(driver);
						if(fp.inviteContact(environment,mode,M3Institution1+"/"+M3LimitedPartner1, M3Contact1EmailId,STDFolderpath, FolderType.Standard,"Upload","Yes", null,"All Folders", Workspace.InvestorWorkspace, M3Contact1EmailId)) {
							appLog.info("contact is invites from investor workspace successfully: "+M3Contact1FirstName+" "+M3Contact1LastName);
							String INV_docpath="UploadFiles\\Module3\\FileToUploadCRMSide\\InvestorWorkSpace\\Standard1";
							if(fp.uploadFile(STDFolderpath,M3Institution1+"/"+M3LimitedPartner1, INV_docpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
								appLog.info("File is upload successfullly");
								switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
								scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "investor workspace view");
								if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
									String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
									if(filesName!=null) {
										List<String>result=compareMultipleList(driver,filesName,fp.getContentGridDocNameList(Workspace.InvestorWorkspace, PageName.FundsPage));
										if(!result.isEmpty()) {
											for(int i=0;i<result.size();i++) {
												sa.assertTrue(false, result.get(i));
											}
										}else {
											appLog.info("All files are verified: "+filesName);
										}
									}
								}else {
									appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
									sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
								}
							}else {
								appLog.error("file is not uploaded in investor workspace folder: "+STDFolderpath);
								sa.assertTrue(false, "file is not uploaded in investor workspace folder: "+STDFolderpath);
							}
						}else {
							appLog.error("Not able to invite contact from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
							sa.assertTrue(false, "Not able to invite contact from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
						}
					}else {
						appLog.error("Not able to add LP from manage investor: "+M3LimitedPartner1);
						sa.assertTrue(false, "Not able to add LP from manage investor: "+M3LimitedPartner1);
					}
					
					}else {
						appLog.error("Not able to click on manage investor icon so cannot add LP : "+M3LimitedPartner1);
						sa.assertTrue(false, "Not able to click on manage investor icon so cannot add LP : "+M3LimitedPartner1);
					}
				}else {
					appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot add institution in investor workspace");
					sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot add institution in investor workspace");
				}
		}else {
			appLog.error("Not able to click on fund tab so cannot add institution in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot add institution in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc077_3_verifyUploadedDocAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		String FWR_STD=ExcelUtils.readData("FilePath",0, 8, "M3tc077_1_addInstitutionFromManageTargetAndUploadDocInFWRAtCRMSide");
		String INV_STD=ExcelUtils.readData("FilePath",0, 8, "M3tc077_2_addLPFromManageTargetAndUploadDocInINVAtCRMSide");
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(allfp.selectFirmName(Org1FirmName)) {
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=compareMultipleList(driver, FWR_STD+"<break>"+INV_STD, ivp.getAllDocumentFileNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.error("document is present in the all document alert grid");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result=compareMultipleList(driver, FWR_STD+"<break>"+INV_STD, ivp.getRecentActivitiesFileNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.error("document is present in the recent activities alert grid");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=compareMultipleList(driver, FWR_STD+"<break>"+INV_STD, allfp.getDocumentNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
			}else {
				appLog.error("document is present in the all firms alert grid");
			}
		}

		}else {
			appLog.error("Not able to select org "+Org1FirmName+" so cannot verify");
			sa.assertTrue(false, "Not able to select org "+Org1FirmName+" so cannot verify");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc078_1_closeFRWAndcheckImpactAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				switchToFrame(driver, 60, fp.getFrame( PageName.FundsPage, 60));
				if(fp.closeWorkSpace(Workspace.FundraisingWorkspace,60)) {
					appLog.info("fundraising workspace is close succesfully.");
				}else {
					appLog.error("Not able to close fundraising workspace of fund: "+M3FundName1);
					sa.assertTrue(false, "Not able to close fundraising workspace of fund: "+M3FundName1);
				}
				}else {
					appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot add institution in investor workspace");
					sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot add institution in investor workspace");
				}
		}else {
			appLog.error("Not able to click on fund tab so cannot add institution in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot add institution in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc078_2_checkImpactAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(allfp.selectFirmName(Org1FirmName)) {
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			WebElement ele = isDisplayed(driver, FindElement(driver, "//a[text()='"+M3FundName1+"']/../following-sibling::span[text()='Potential Investment']", "Fund Name Link", action.SCROLLANDBOOLEAN, 5), "visibility",5,"");
			if(ele==null) {
				appLog.info(M3FundName1+" potential investment alerts is not visible on all document activity alert grid");
			}else {
				appLog.error(M3FundName1+" potential investment alerts is visible on all document activity alert grid");
				sa.assertTrue(false, M3FundName1+" potential investment alerts is visible on all document activity alert grid");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			WebElement ele = isDisplayed(driver, FindElement(driver, "//a[text()='"+M3FundName1+"']/../following-sibling::span[text()='Potential Investment']", "Fund Name Link", action.SCROLLANDBOOLEAN, 5), "visibility",5,"");
			if(ele==null) {
				appLog.info(M3FundName1+" potential investment alerts is not visible on recent document activity alert grid");
			}else {
				appLog.error(M3FundName1+" potential investment alerts is visible on recent document activity alert grid");
				sa.assertTrue(false, M3FundName1+" potential investment alerts is visible on recent document activity alert grid");
			}
			driver.navigate().refresh();
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(null, null,null, TabName.RecentActivities,activityType.FirmProfileUpdate,PageName.InvestorFirmPage);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("Firm Profile Update alert is visible on recent activities alert grid");
			}
			driver.navigate().refresh();
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(null, null,null, TabName.RecentActivities,activityType.ContactProfileUpdate,PageName.InvestorFirmPage);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("Contact Profile Update alert is visible on recent activities alert grid");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}

	}else {
			appLog.error("Not able to select org "+Org1FirmName+" so cannot verify");
			sa.assertTrue(false, "Not able to select org "+Org1FirmName+" so cannot verify");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			WebElement ele = isDisplayed(driver, FindElement(driver, "//a[text()='"+M3FundName1+"']/../following-sibling::span[text()='Potential Investment']", "Fund Name Link", action.SCROLLANDBOOLEAN, 5), "visibility",5,"");
			if(ele==null) {
				appLog.info(M3FundName1+" potential investment alerts is not visible on all firms activity alert grid");
			}else {
				appLog.error(M3FundName1+" potential investment alerts is visible on all firms activity alert grid");
				sa.assertTrue(false, M3FundName1+" potential investment alerts is visible on all firms activity alert grid");
			}
			driver.navigate().refresh();
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(null,null, null,null, activityType.FirmProfileUpdate,PageName.AllFirmsPage);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("firm profile update alert is successfully verified on all firms alert grid");
			}
			driver.navigate().refresh();
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(null,null, null,null, activityType.ContactProfileUpdate,PageName.AllFirmsPage);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("Contact profile update alert is successfully verified on all firms alert grid");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc078_3_closeINVAndcheckImpactAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				switchToFrame(driver, 60, fp.getFrame( PageName.FundsPage, 60));
				if(fp.closeWorkSpace(Workspace.InvestorWorkspace,60)) {
					appLog.info("investor workspace is close succesfully.");
				}else {
					appLog.error("Not able to close investor workspace of fund: "+M3FundName1);
					sa.assertTrue(false, "Not able to close investor workspace of fund: "+M3FundName1);
				}
				}else {
					appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot add institution in investor workspace");
					sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot add institution in investor workspace");
				}
		}else {
			appLog.error("Not able to click on fund tab so cannot add institution in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot add institution in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc078_4_checkImpactAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(allfp.selectFirmName(Org1FirmName)) {
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			WebElement ele = isDisplayed(driver, FindElement(driver, "//a[text()='"+M3FundName1+"']/../following-sibling::span[text()='Current Investment']", "Fund Name Link", action.SCROLLANDBOOLEAN, 5), "visibility",5,"");
			if(ele==null) {
				appLog.info(M3FundName1+" Current investment alerts is not visible on all document activity alert grid");
			}else {
				appLog.error(M3FundName1+" Current investment alerts is visible on all document activity alert grid");
				sa.assertTrue(false, M3FundName1+" Current investment alerts is visible on all document activity alert grid");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			WebElement ele = isDisplayed(driver, FindElement(driver, "//a[text()='"+M3FundName1+"']/../following-sibling::span[text()='Current Investment']", "Fund Name Link", action.SCROLLANDBOOLEAN, 5), "visibility",5,"");
			if(ele==null) {
				appLog.info(M3FundName1+" Current investment alerts is not visible on recent document activity alert grid");
			}else {
				appLog.error(M3FundName1+" Current investment alerts is visible on recent activity alert grid");
				sa.assertTrue(false, M3FundName1+" Current investment alerts is visible on recent activity alert grid");
			}
			driver.navigate().refresh();
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(null, null,null, TabName.RecentActivities,activityType.FirmProfileUpdate,PageName.InvestorFirmPage);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
				appLog.info("Firm Profile Update alert is not visible on recent activities alert grid");
			}else {
				appLog.info("Firm Profile Update alert is visible on recent activities alert grid");
				sa.assertTrue(false, "Firm Profile Update alert is visible on recent activities alert grid");
			}
			driver.navigate().refresh();
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(null, null,null, TabName.RecentActivities,activityType.ContactProfileUpdate,PageName.InvestorFirmPage);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
				appLog.info("Contact Profile Update alert is not visible on recent activities alert grid");
			}else {
				appLog.info("Contact Profile Update alert is visible on recent activities alert grid");
				sa.assertTrue(false, "Contact Profile Update alert is visible on recent activities alert grid");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}

	}else {
			appLog.error("Not able to select org "+Org1FirmName+" so cannot verify");
			sa.assertTrue(false, "Not able to select org "+Org1FirmName+" so cannot verify");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			WebElement ele = isDisplayed(driver, FindElement(driver, "//a[text()='"+M3FundName1+"']/../following-sibling::span[text()='Current Investment']", "Fund Name Link", action.SCROLLANDBOOLEAN, 5), "visibility",5,"");
			if(ele==null) {
				appLog.info(M3FundName1+" Current investment alerts is not visible on all firms activity alert grid");
			}else {
				appLog.error(M3FundName1+" Current investment alerts is visible on all firms activity alert grid");
				sa.assertTrue(false, M3FundName1+" Current investment alerts is visible on all firms activity alert grid");
			}
			driver.navigate().refresh();
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(null,null, null,null, activityType.FirmProfileUpdate,PageName.AllFirmsPage);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
				appLog.info("firm profile update alert is not visible on all firms alert grid");
			}else {
				appLog.info("firm profile update alert is visible on all firms alert grid");
				sa.assertTrue(false, "firm profile update alert is visible on all firms alert grid");
			}
			driver.navigate().refresh();
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(null,null, null,null, activityType.ContactProfileUpdate,PageName.AllFirmsPage);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
				appLog.info("Contact profile update alert is not visible on all firms alert grid");
			}else {
				appLog.info("Contact profile update alert is visible on all firms alert grid");
				sa.assertTrue(false, "Contact profile update alert is visible on all firms alert grid");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}

	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc079_1_againInviteContactAndCheckImpactInvestorSide(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				if(fp.inviteContact(environment,mode,M3Institution1, M3Contact1EmailId,null, FolderType.Standard,"Upload","Yes", null,"All Folders", Workspace.FundraisingWorkspace, M3Contact1EmailId)) {
					appLog.info("contact is invites from fundraising workspace successfully: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}
				if(fp.inviteContact(environment,mode,M3Institution1+"/"+M3LimitedPartner1, M3Contact1EmailId,null, FolderType.Standard,"Upload","Yes", null,"All Folders", Workspace.InvestorWorkspace, M3Contact1EmailId)) {
					appLog.info("contact is invites from investor workspace successfully: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}else {
					appLog.error("Not able to invite contact from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact from fund: "+M3FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact from fund: "+M3FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact from fund: "+M3FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact from: "+M3FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc079_2_againInviteContactAndCheckImpactInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(allfp.selectFirmName(Org1FirmName)) {
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=compareMultipleList(driver, M3FundName1, ivp.getAllDocumentInvestmentNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.error(M3FundName1+"investment name is present in the all document alert grid");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result=compareMultipleList(driver, M3FundName1, ivp.getRecentActivitiestInvestmentNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
				appLog.info(M3FundName1+"investment name is not present in the recent activities alert grid");
			}else {
				appLog.error(M3FundName1+"investment name is present in the recent activities alert grid");
				sa.assertTrue(false, M3FundName1+"investment name is present in the recent activities alert grid");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		}else {
			appLog.error("Not able to select org "+Org1FirmName+" so cannot verify");
			sa.assertTrue(false, "Not able to select org "+Org1FirmName+" so cannot verify");
		}
		
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=compareMultipleList(driver, M3FundName1, allfp.getInvesmentNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
				appLog.info(M3FundName1+"investment name is not present in the all firms alert grid");
			}else {
				appLog.error(M3FundName1+"investment name is present in the all firms alert grid");
				sa.assertTrue(false, M3FundName1+"investment name is present in the all firms alert grid");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc080_1_clearWorkspaceAndcheckImpactAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				switchToFrame(driver, 60, fp.getFrame( PageName.FundsPage, 60));
				if(fp.clearWorkSpace(Workspace.FundraisingWorkspace,60)) {
					appLog.info("fundraising workspace is close succesfully.");
				}else {
					appLog.error("Not able to close fundraising workspace of fund: "+M3FundName1);
					sa.assertTrue(false, "Not able to close fundraising workspace of fund: "+M3FundName1);
				}
				switchToDefaultContent(driver);
				driver.navigate().refresh();
				switchToFrame(driver, 60, fp.getFrame( PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60), "fundraising workspace view section");
				if(fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 20)!=null) {
					appLog.info("build fundraising workspace button is displayed");
				}else {
					appLog.error("build fundraising workspace button is not displayed");
					sa.assertTrue(false, "build fundraising workspace button is not displayed");
				}
				}else {
					appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot add institution in investor workspace");
					sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot add institution in investor workspace");
				}
		}else {
			appLog.error("Not able to click on fund tab so cannot add institution in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot add institution in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc080_2_checkImpactAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(allfp.selectFirmName(Org1FirmName)) {
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			WebElement ele = isDisplayed(driver, FindElement(driver, "//a[text()='"+M3FundName1+"']/../following-sibling::span[text()='Potential Investment']", "Fund Name Link", action.SCROLLANDBOOLEAN, 5), "visibility",5,"");
			if(ele==null) {
				appLog.info(M3FundName1+" potential investment alerts is not visible on all document activity alert grid");
			}else {
				appLog.error(M3FundName1+" potential investment alerts is visible on all document activity alert grid");
				sa.assertTrue(false, M3FundName1+" potential investment alerts is visible on all document activity alert grid");
			}	
			
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			WebElement ele = isDisplayed(driver, FindElement(driver, "//a[text()='"+M3FundName1+"']/../following-sibling::span[text()='Potential Investment']", "Fund Name Link", action.SCROLLANDBOOLEAN, 5), "visibility",5,"");
			if(ele==null) {
				appLog.info(M3FundName1+" potential investment alerts is not visible on recent document activity alert grid");
			}else {
				appLog.error(M3FundName1+" potential investment alerts is visible on recent document activity alert grid");
				sa.assertTrue(false, M3FundName1+" potential investment alerts is visible on recent document activity alert grid");
			}
			
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(null, null,null, TabName.RecentActivities,activityType.FirmProfileUpdate,PageName.InvestorFirmPage);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("Firm Profile Update alert is visible on recent activities alert grid");
				
			}
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(null, null,null, TabName.RecentActivities,activityType.ContactProfileUpdate,PageName.InvestorFirmPage);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.error("Contact Profile Update alert is visible on recent activities alert grid");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
	}else {
		appLog.error("Not able to select org "+Org1FirmName+" so cannot verify");
		sa.assertTrue(false, "Not able to select org "+Org1FirmName+" so cannot verify");
	}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			WebElement ele = isDisplayed(driver, FindElement(driver, "//a[text()='"+M3FundName1+"']/../following-sibling::span[text()='Potential Investment']", "Fund Name Link", action.SCROLLANDBOOLEAN, 5), "visibility",5,"");
			if(ele==null) {
				appLog.info(M3FundName1+" potential investment alerts is not visible on all firms activity alert grid");
			}else {
				appLog.error(M3FundName1+" potential investment alerts is visible on all firms activity alert grid");
				sa.assertTrue(false, M3FundName1+" potential investment alerts is visible on all firms activity alert grid");
			}
			
			ele = isDisplayed(driver, FindElement(driver, "//a[text()='"+M3FundName1+"']/../following-sibling::span[text()='Current Investment']", "Fund Name Link", action.SCROLLANDBOOLEAN, 5), "visibility",5,"");
			if(ele==null) {
				appLog.info(M3FundName1+" Current investment alerts is not visible on all firms activity alert grid");
			}else {
				appLog.error(M3FundName1+" Current investment alerts is visible on all firms activity alert grid");
				sa.assertTrue(false, M3FundName1+" potential investment alerts is visible on all firms activity alert grid");
			}
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(null,null, null,null, activityType.FirmProfileUpdate,PageName.AllFirmsPage);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("firm profile update alert is visible on all firms alert grid");
			}
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(null,null, null,null, activityType.ContactProfileUpdate,PageName.AllFirmsPage);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
			}else {
				appLog.info("Contact profile update alert is visible on all firms alert grid");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	

	@Parameters({ "environment", "mode" }) @Test
	public void M3tc080_3_clearINVAndcheckImpactAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				switchToFrame(driver, 60, fp.getFrame( PageName.FundsPage, 60));
				if(fp.clearWorkSpace(Workspace.InvestorWorkspace,60)) {
					appLog.info("investor workspace is close succesfully.");
				}else {
					appLog.error("Not able to close investor workspace of fund: "+M3FundName1);
					sa.assertTrue(false, "Not able to close investor workspace of fund: "+M3FundName1);
				}
				switchToDefaultContent(driver);
				driver.navigate().refresh();
				switchToFrame(driver, 60, fp.getFrame( PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60), "fundraising workspace view section");
				if(fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace,20)!=null) {
					appLog.info("build investor workspace button is displayed");
				}else {
					appLog.error("build investor workspace button is not displayed");
					sa.assertTrue(false, "build investor workspace button is not displayed");
				}
				}else {
					appLog.error("Not able to click on created Fund: "+M3FundName1+" so cannot add institution in investor workspace");
					sa.assertTrue(false, "Not able to click on created Fund: "+M3FundName1+" so cannot add institution in investor workspace");
				}
		}else {
			appLog.error("Not able to click on fund tab so cannot add institution in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot add institution in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc080_4_checkImpactAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(allfp.selectFirmName(Org1FirmName)) {
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			WebElement ele = isDisplayed(driver, FindElement(driver, "//a[text()='"+M3FundName1+"']/../following-sibling::span[text()='Current Investment']", "Fund Name Link", action.SCROLLANDBOOLEAN, 5), "visibility",5,"");
			if(ele==null) {
				appLog.info(M3FundName1+" Current investment alerts is not visible on all document activity alert grid");
			}else {
				appLog.error(M3FundName1+" Current investment alerts is visible on all document activity alert grid");
				sa.assertTrue(false, M3FundName1+" Current investment alerts is visible on all document activity alert grid");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			WebElement ele = isDisplayed(driver, FindElement(driver, "//a[text()='"+M3FundName1+"']/../following-sibling::span[text()='Current Investment']", "Fund Name Link", action.SCROLLANDBOOLEAN, 5), "visibility",5,"");
			if(ele==null) {
				appLog.info(M3FundName1+" Current investment alerts is not visible on all document activity alert grid");
			}else {
				appLog.error(M3FundName1+" Current investment alerts is visible on all document activity alert grid");
				sa.assertTrue(false, M3FundName1+" Current investment alerts is visible on all document activity alert grid");
			}
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(null, null,null, TabName.RecentActivities,activityType.FirmProfileUpdate,PageName.InvestorFirmPage);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
				appLog.info("Firm Profile Update alert is not visible on recent activities alert grid");
			}else {
				appLog.info("Firm Profile Update alert is  visible on recent activities alert grid");
				sa.assertTrue(false, "Firm Profile Update alert is  visible on recent activities alert grid");
			}
			driver.navigate().refresh();
			result.clear();
			result=ivp.verifyAlertOnRecentAllDocumentGrid(null, null,null, TabName.RecentActivities,activityType.ContactProfileUpdate,PageName.InvestorFirmPage);			
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
				appLog.info("Contact Profile Update alert is not visible on recent activities alert grid");
			}else {
				appLog.info("Contact Profile Update alert is not visible on recent activities alert grid");
				sa.assertTrue(false, "Contact Profile Update alert is not visible on recent activities alert grid");
			}
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
	}else {
		appLog.error("Not able to select org "+Org1FirmName+" so cannot verify");
		sa.assertTrue(false, "Not able to select org "+Org1FirmName+" so cannot verify");
	}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			WebElement ele = isDisplayed(driver, FindElement(driver, "//a[text()='"+M3FundName1+"']/../following-sibling::span[text()='Current Investment']", "Fund Name Link", action.SCROLLANDBOOLEAN, 5), "visibility",5,"");
			if(ele==null) {
				appLog.info(M3FundName1+" Current investment alerts is not visible on all document activity alert grid");
			}else {
				appLog.error(M3FundName1+" Current investment alerts is visible on all document activity alert grid");
				sa.assertTrue(false, M3FundName1+" Current investment alerts is visible on all document activity alert grid");
			}
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(null,null, null,null, activityType.FirmProfileUpdate,PageName.AllFirmsPage);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
				appLog.info("firm profile update alert is not visible on all firms alert grid");
			}else {
				appLog.info("firm profile update alert is visible on all firms alert grid");
				sa.assertTrue(false, "firm profile update alert is visible on all firms alert grid");
			}
			driver.navigate().refresh();
			result.clear();
			result=allfp.verifyAlertsOnAllFirmsPage(null,null, null,null, activityType.ContactProfileUpdate,PageName.AllFirmsPage);
			if(!result.isEmpty()) {
				for(int i=0; i<result.size();i++) {
					appLog.error(result.get(i));
					sa.assertTrue(false, result.get(i));
				}
				appLog.info("Contact profile update alert is not visible on all firms alert grid");
			}else {
				appLog.info("Contact profile update alert is visible on all firms alert grid");
				sa.assertTrue(false, "Contact profile update alert is visible on all firms alert grid");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc081_1_buildAgainFWRINVWorkSpaceAndCheckImpactInvestorSide(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M3FundName1)) {
				String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund1", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund1", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund1", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund1", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund1", excelLabel.Fund_Email);
				String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M3Fund1", excelLabel.Fund_Description);
				String templateName=ExcelUtils.readData("Foldertemp", 0, 1);
				String[] data= {Size,vintageyear,contact,phone,email,description};
				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,M3Institution1+"<break>"+M3Institution2, Workspace.FundraisingWorkspace,60)) {
					appLog.info("FundRaising work is build successfully.");
				}else {
					appLog.error("Not able to bulid fundraising workspace on fund: "+M3FundName1);
					sa.assertTrue(false, "Not able to bulid fundraising workspace on fund: "+M3FundName1);
				}
				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,M3Institution1+"/"+M3LimitedPartner1+"<break>"+M3Institution2+"/"+M3LimitedPartner2, Workspace.InvestorWorkspace,60)) {
					appLog.info("Investor work is build successfully.");
				}else {
					appLog.error("Not able to bulid Investor workspace on fund: "+M3FundName1);
					sa.assertTrue(false, "Not able to bulid Investor workspace on fund: "+M3FundName1);
				}
				if(fp.inviteContact(environment,mode,M3Institution1, M3Contact1EmailId,null, FolderType.Standard,"Upload","Yes", null,"All Folders", Workspace.FundraisingWorkspace, M3Contact1EmailId)) {
					appLog.info("contact is invites from fundraising workspace successfully: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}
				if(fp.inviteContact(environment,mode,M3Institution1+"/"+M3LimitedPartner1, M3Contact1EmailId,null, FolderType.Standard,"Upload","Yes", null,"All Folders", Workspace.InvestorWorkspace, M3Contact1EmailId)) {
					appLog.info("contact is invites from investor workspace successfully: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}else {
					appLog.error("Not able to invite contact from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact from investor workspace: "+M3Contact1FirstName+" "+M3Contact1LastName);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot build fundraising workspace: "+M3FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace: "+M3FundName1);
		}
		switchToDefaultContent(driver);
		// Azhar Addition
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, nim.getFrame( PageName.NavatarInvestorManager, 30));
			if (nim.clickOnSideMenusTab(sideMenu.Profiles)) {
				if (nim.clickOnEditIcon()) {
					if (sendKeys(driver, nim.getMyProfileFirstName(60), CRMUser1FirstName, "first name text box",
							action.BOOLEAN)) {
						appLog.info("Passed value in frist name text box: " + CRMUser1FirstName);
						if (sendKeys(driver, nim.getMyProfileLastName(30), CRMUser1LastName, "last name text box",
								action.BOOLEAN)) {
							appLog.info("Passed value in last name text box: " + CRMUser1LastName);
							if (click(driver, nim.getMyProfileSaveButton(30), "my profile save button",
									action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on save button");
							} else {
								appLog.error("Not Able to Click on Save Button");
								sa.assertTrue(false, "Not Able to Click on Save Button");
							}
						} else {
							appLog.error("Not Able to enter value to Profile Last Name Text Box");
							sa.assertTrue(false, "Not Able to enter value to Profile Last Name Text Box");
						}
					} else {
						appLog.error("Not Able to enter value to Profile First Name Text Box");
						sa.assertTrue(false, "Not Able to enter value to Profile First Name Text Box");
					}

				} else {
					appLog.error("Not Able to Click on Edit Icon");
					sa.assertTrue(false, "Not Able to Click on Edit Icon");
				}
			} else {
				appLog.error("profile side menu is not cllickable on nim page");
				sa.assertTrue(false, "profile side menu is not cllickable on nim page");
			}

		} else {
			appLog.error("Not Able to click on NIM Tab");
			sa.assertTrue(false, "Not Able to click on NIM Tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc081_2_buildAgainFWRINVWorkSpaceAndCheckImpactInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		List<String> result=new ArrayList<String>();
		lp.investorLogin(M3Contact1EmailId,adminPassword);
		if(allfp.selectFirmName(Org1FirmName)) {
		if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
			result=compareMultipleList(driver, M3FundName1, ivp.getAllDocumentInvestmentNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
				appLog.info(M3FundName1+"investment name is not present in the all document alert grid");
			}else {
				appLog.error(M3FundName1+"investment name is present in the all document alert grid");
				sa.assertTrue(false, M3FundName1+"investment name is present in the all document alert grid");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot verify all document alerts");
			sa.assertTrue(false, "Not able to click on all document tab so cannot verify all document alerts");
		}
		if(click(driver,ivp.getRecentActivitiesTab(30),"recent activity tab", action.BOOLEAN)) {
			result=compareMultipleList(driver, M3FundName1, ivp.getRecentActivitiestInvestmentNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
				appLog.info(M3FundName1+"investment name is not present in the recent activities alert grid");
			}else {
				appLog.error(M3FundName1+"investment name is present in the recent activities alert grid");
				sa.assertTrue(false, M3FundName1+"investment name is present in the recent activities alert grid");
			}
			
		}else {
			appLog.error("Not able to click on recent acitivites tab so cannot check recent activities alerts");
			sa.assertTrue(false, "Not able to click on recent acitivites tab so cannot check recent activities alerts");
		}
		}else {
			appLog.error("Not able to select org "+Org1FirmName+" so cannot verify");
			sa.assertTrue(false, "Not able to select org "+Org1FirmName+" so cannot verify");
		}
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			result.clear();
			result=compareMultipleList(driver, M3FundName1, allfp.getInvesmentNameList());
			if(!result.isEmpty()) {
				for(int i=0; i<result.size(); i++) {
					appLog.error(result.get(i));
					sa.assertTrue(true, result.get(i));
				}
				appLog.info(M3FundName1+"investment name is not present in the all firms alert grid");
			}else {
				appLog.error(M3FundName1+"investment name is present in the all firms alert grid");
				sa.assertTrue(false, M3FundName1+"investment name is present in the all firms alert grid");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M3tc082_PostConditionForAll() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		lp.postCondition().assertAll();
		lp.CRMlogout();

	}
}


