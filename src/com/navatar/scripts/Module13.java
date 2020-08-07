/**
 * 
 */
package com.navatar.scripts;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.navatar.generic.BaseLib;
import com.navatar.generic.EmailLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.CommonLib.FolderType;
import static com.navatar.generic.SmokeCommonVariable.*;
import com.navatar.generic.CommonLib.OnlineImportFileAddTo;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.TabName;
import com.navatar.generic.CommonLib.UploadFileActions;
import com.navatar.generic.CommonLib.WorkSpaceAction;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.generic.CommonLib.sideMenu;
import com.navatar.pageObjects.AllFirmsPageBusinesslayer;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.CommitmentPageBusinessLayer;
import com.navatar.pageObjects.ContactPageBusinessLayer;
import com.navatar.pageObjects.FundRaisingPageBusinessLayer;
import com.navatar.pageObjects.FundsPageBusinessLayer;
import com.navatar.pageObjects.InstitutionPageBusinessLayer;
import com.navatar.pageObjects.InvestorFirmPageBusinesslayer;
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.NIMPageBusinessLayer;
import com.navatar.pageObjects.NIMPageErrorMessage;
import com.navatar.pageObjects.PartnershipPageBusinessLayer;

import sun.misc.FpUtils;

import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.CommonVariables.*;
import java.util.List;

/**
 * @Module Name: WaterMarking
 * @author Ankit Jaiswal
 * @Module : 13
 *
 */
public class Module13 extends BaseLib {

	
	@Test
	public void M13tc001_runPreConditionScenario() {
		boolean flag = false;
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName,adminPassword);
		if(nim.clickOnTab(TabName.NIMTab)) {
			if(nim.giveAccessToUserInNIMTabFromAdmin(CRMUser1FirstName+" "+CRMUser1LastName, accessType.InternalUserAccess)) {
				appLog.info("internal user access has been provided to the user : "+CRMUser1FirstName+" "+CRMUser1LastName);
			}else {
				appLog.error("Not able to provide internal user access to the user : "+CRMUser1FirstName+" "+CRMUser1LastName);
				sa.assertTrue(false, "Not able to provide internal user access to the user : "+CRMUser1FirstName+" "+CRMUser1LastName);
			}
			if(nim.giveAccessToUserInNIMTabFromAdmin(CRMUser2FirstName+" "+CRMUser2LastName, accessType.InternalUserAccess)) {
				appLog.info("internal user access has been provided to the user : "+CRMUser2FirstName+" "+CRMUser2LastName);
				flag=true;
			}else {
				appLog.error("Not able to provide internal user access to the user : "+CRMUser2FirstName+" "+CRMUser2LastName);
				sa.assertTrue(false, "Not able to provide internal user access to the user : "+CRMUser2FirstName+" "+CRMUser2LastName);
			}
			lp.CRMlogout();
			if(flag) {
				driver.close();
				config(browserToLaunch);
				lp = new LoginPageBusinessLayer(driver);
				nim = new NIMPageBusinessLayer(driver);
				lp.CRMLogin(CRMUser2EmailID, adminPassword);
				if(nim.clickOnTab(TabName.NIMTab)) {
					switchToFrame(driver, 10, nim.getFrame(PageName.NavatarInvestorManager, 10));
					if(click(driver, nim.getRegistrationSuccessfulCloseBtn(10),"user registration pop up close button", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on registration PopUp close button");
					}else {
						appLog.info("Registration PopUp close Button is not displayed");
					}
					switchToDefaultContent(driver);
					lp.CRMlogout();
					lp.CRMLogin(superAdminUserName, adminPassword);
					if(nim.clickOnTab(TabName.NIMTab)) {
						if(nim.giveAccessToUserInNIMTabFromAdmin(CRMUser2FirstName+" "+CRMUser2LastName, accessType.AdminUserAccess)) {
							appLog.info("Admin user access has been provided to the user : "+CRMUser2FirstName+" "+CRMUser2LastName);
						}else {
							appLog.error("Not able to provide admin user access to the user : "+CRMUser2FirstName+" "+CRMUser2LastName);
							sa.assertTrue(false, "Not able to provide admin user access to the user : "+CRMUser2FirstName+" "+CRMUser2LastName);
						}
						lp.CRMlogout();
					}else {
						appLog.error("Not able to click on NIM Tab so cannot give admin user access to user :"+CRMUser2FirstName+" "+CRMUser2LastName);
						sa.assertTrue(false, "Not able to click on NIM Tab so cannot give admin user access to user :"+CRMUser2FirstName+" "+CRMUser2LastName);
					}
				}else {
					appLog.error("Not able to click on user2 NIM Tab so cannot do user2 Registration");
					sa.assertTrue(false, "Not able to click on user2 NIM Tab so cannot do user2 Registration");
				}
			}else {
				appLog.error("Not able to provide internal user access to user : "+CRMUser2FirstName+" "+CRMUser2LastName+" so cannot give admin user access to the same user");
				sa.assertTrue(false, "Not able to provide internal user access to user : "+CRMUser2FirstName+" "+CRMUser2LastName+" so cannot give admin user access to the same user");
			}
			
		}else {
			appLog.error("Not able to click on NIM Tab so cannot give access to the user : "+CRMUser1FirstName+" "+CRMUser1LastName+","+CRMUser2FirstName+" "+CRMUser2LastName);
			sa.assertTrue(false, "Not able to click on NIM Tab so cannot give access to the user : "+CRMUser1FirstName+" "+CRMUser1LastName+","+CRMUser2FirstName+" "+CRMUser2LastName);
		}
		sa.assertAll();
	}

	@Test
	public void M13tc002_Module13_preCondition() {
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
			String instutionName=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M13Instituition"+(i+1), excelLabel.Institutions_Name);
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
		String emailId = cp.generateRandomEmailId();
			if(bp.clickOnTab(TabName.ContactTab)){
				if(cp.createContact(M13Contact1FirstName,M13Contact1LastName,M13Institution1, emailId)) {
					appLog.info("contact is created: "+M13Contact1FirstName+" "+M13Contact1LastName);
					ExcelUtils.writeData(emailId,"Contacts", excelLabel.Variable_Name, "M13Contact1",excelLabel.Contact_EmailId);
				}else {
					appLog.error("Not able to create contact: "+M13Contact1FirstName+" "+M13Contact1LastName);
					sa.assertTrue(false, "Not able to create contact: "+M13Contact1FirstName+" "+M13Contact1LastName);
				}
			}
			if(bp.clickOnTab(TabName.FundsTab)) {
				if(fp.createFund(M13FundName1,M13FundType,M13FundInvestmentCategory)) {
					appLog.info("fund is created: "+M13FundName1);
				}else {
					appLog.error("Not able to create fund: "+M13FundName1);
					sa.assertTrue(false, "Not able to create fund: "+M13FundName1);
				}
			}else {
				appLog.error("Not able to click on fund tab so cannot create fund: "+M13FundName1);
				sa.assertTrue(false, "Not able to click on fund tab so cannot create fund: "+M13FundName1);
			}
		for(int i=0; i<2; i++) {
			String fundraisingName=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M13FundRaising"+(i+1), excelLabel.FundRaising_Name);
			if(bp.clickOnTab(TabName.FundraisingsTab)) {
				String fundName=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M13FundRaising"+(i+1), excelLabel.Fund_Name);
				String instutionName=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M13FundRaising"+(i+1), excelLabel.Institutions_Name);
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
			String lpName=ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M13LimitedPartner"+(i+1), excelLabel.LimitedPartner_Name);
			if(bp.clickOnTab(TabName.InstituitonsTab)) {
				String instutionName=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M13Instituition"+(i+1), excelLabel.Institutions_Name);
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
				if(pp.createPartnership(M13Partnership1,M13FundName1)) {
					appLog.info("partnership is created: "+M13Partnership1);
				}else {
					appLog.error("Not able to create partnership: "+M13Partnership1);
					sa.assertTrue(false, "Not able to create partnership: "+M13Partnership1);
				}
			}else {
				appLog.error("Not able to click on partnership tab so cannot create partnership: "+M13Partnership1);
				sa.assertTrue(false, "Not able to click on partnership tab so cannot create partnership: "+M13Partnership1);
			}
		for(int i=0; i<2; i++) {
			String lpName;
			String partnershipName = null;
			lpName=ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M13LimitedPartner"+(i+1)+"", excelLabel.LimitedPartner_Name);
			partnershipName=ExcelUtils.readData("Partnerships",excelLabel.Variable_Name, "M13Partnership1", excelLabel.PartnerShip_Name);
			if(bp.clickOnTab(TabName.CommitmentsTab)) {
				if(cmp.createCommitment(lpName,partnershipName,"M13Commitment"+(i+1), null)) {
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
		if(nim.getMyProfileFistNameAndLastNameAndFirmName("AdminUser")) {
			appLog.info("CRM User1 first,last name and firm name successfully write in excel");
		}else {
			appLog.error("Not able to write CRM User1 first,last name and firm profile in excel");
			sa.assertTrue(false, "Not able to write CRM User1 first,last name and firm profile in excel");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc003_verifyWaterMarkingInViewMode() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser2EmailID,adminPassword);
		if(nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if(nim.clickOnSideMenusTab(sideMenu.Watermarking)) {
				if(nim.getEditIcon(10)!=null) {
					appLog.info("Edit Icon is visible");
				}else {
					appLog.error("Edit Icon is not visible");
					sa.assertTrue(false, "Edit Icon is not visible");
				}
				if(nim.getWaterMarkingLabeltext(10)!=null) {
					if(nim.getWaterMarkingLabeltext(10).getText().trim().contains("Watermarking")) {
						appLog.info("WaterMarking label in visible on watermarking tab");
					}else {
						appLog.error("Watermakring label is not matched");
						sa.assertTrue(false, "Watermakring label is not matched");
					}
				}else {
					appLog.error("WaterMarking label is not visible");
					sa.assertTrue(false, "WaterMarking label is not visible");
				}
				if(!isSelected(driver, nim.getWatermarkingActivateCheckbox(10), "watermarking activate check box")) {
					appLog.info("WaterMarking checkbox is not selected");
					}else {
						appLog.error("WaterMarking checkbox is selected");
						sa.assertTrue(false, "WaterMarking checkbox is selected");
					}
					if(nim.getWatermarkingActivateCheckbox(10)!=null) {
						appLog.info("Apply Watermarking to PDF Documents check box is visible");
					}else {
						appLog.error("Apply Watermarking to PDF Documents check box is visible");
						sa.assertTrue(false, "Apply Watermarking to PDF Documents check box is visible");
					}
					if(compareMultipleList(driver, "Applied On Upload<break>Applied On Download",nim.getWaterMakringSectionslist()).isEmpty()) {
						appLog.info("WaterMakring Grid is divide in the two sections and label text is verified");
					}else {
						appLog.error("watermakring grid section label text is not verified");
						sa.assertTrue(false, "watermakring grid section label text is not verified");
					}
					if(compareMultipleList(driver, "Label<break>Location",nim.getWatermakringAppliedOnUploadHeaderTextList()).isEmpty()) {
						appLog.info("Applied on upload header label text is verified");
					}else {
						appLog.error("Applied on upload header label text is not verified");
						sa.assertTrue(false, "Applied on upload header label text is not verified");
					}
					if(nim.getWatermarkingFirmNameLabel(10)!=null) {
						appLog.info("Firm Name check box is visible in watermakring");
					}else {
						appLog.error("Firm Name check box is not visible in watermakring");
						sa.assertTrue(false, "Firm Name check box is visible in watermakring");
					}
					if(nim.getWaterMarkingMyFirmNameDropDown(10)!=null) {
						appLog.info("firm name drop down list is visible in watermarking");
					}else {
						appLog.error("firm name drop down list is not visible in watermarking");
						sa.assertTrue(false, "firm name drop down list is not visible in watermarking");
					}
					if(nim.getInvestorNameLabel(10)!=null) {
						appLog.info("Investor Name check box is visible in watermakring");
					}else {
						appLog.error("Investor Name check box is not visible in watermakring");
						sa.assertTrue(false, "Investor Name check box is visible in watermakring");
					}
					if(nim.getInvestorNameLabelDropDown(10)!=null) {
						appLog.info("Investor name drop down list is visible in watermarking");
					}else {
						appLog.error("Investor name drop down list is not visible in watermarking");
						sa.assertTrue(false, "Investor name drop down list is not visible in watermarking");
					}
					if(mouseOverOperation(driver, nim.getWaterMarkingInvestorNameToolTip(10))){
						String text = trim(getText(driver, nim.getWaterMarkingInvestorNametoolTipText(10), "", action.BOOLEAN));
						if(text.contains(NIMPageErrorMessage.InvestorNameToolTipErrorMsg)) {
							appLog.info("tootl tip text is matched");
						}else {
							appLog.error("investor name tooltip text is not matched Expected: "+NIMPageErrorMessage.InvestorNameToolTipErrorMsg+"/t Actual: "+text);
							sa.assertTrue(false, "investor name tooltip text is not matched Expected: "+NIMPageErrorMessage.InvestorNameToolTipErrorMsg+"/t Actual: "+text);
						}
					} else {
						appLog.error("Cannot hover the mouse on investor name info icon, So cannot verify investor name info icon message.");
						sa.assertTrue(false,"Cannot hover the mouse on info icon, So cannot verify investor name info icon message.");
					}
					if(nim.getFundNameLabel(10)!=null) {
						appLog.info("Fund Name check box is visible in watermakring");
					}else {
						appLog.error("Fund Name check box is not visible in watermakring");
						sa.assertTrue(false, "Fund Name check box is visible in watermakring");
					}
					if(nim.getFundNameLabelDropDown(10)!=null) {
						appLog.info("Fund name drop down list is visible in watermarking");
					}else {
						appLog.error("Fund name drop down list is not visible in watermarking");
						sa.assertTrue(false, "Fund name drop down list is not visible in watermarking");
					}
					if(compareMultipleList(driver, "Label<break>Location",nim.getWaterMarkingAppliedOnDownloadHeaderTextList()).isEmpty()) {
						appLog.info("Applied on download header label text is verified");
					}else {
						appLog.error("Applied on download header label text is not verified");
						sa.assertTrue(false, "Applied on download header label text is not verified");
					}
					if(nim.getDownloadDateLabel(10)!=null) {
						appLog.info("download date check box is visible in watermakring");
					}else {
						appLog.error("download date check box is not visible in watermakring");
						sa.assertTrue(false, "download date check box is visible in watermakring");
					}
					if(nim.getDownloadDateLabelDropDown(10)!=null) {
						appLog.info("download date drop down list is visible in watermarking");
					}else {
						appLog.error("download date drop down list is not visible in watermarking");
						sa.assertTrue(false, "download date drop down list is not visible in watermarking");
					}
					if(nim.getIPAddressLabel(10)!=null) {
						appLog.info("ip address check box is visible in watermakring");
					}else {
						appLog.error("ip address check box is not visible in watermakring");
						sa.assertTrue(false, "ip address check box is visible in watermakring");
					}
					if(nim.getIPAddressLabelDropDown(10)!=null) {
						appLog.info("ip address drop down list is visible in watermarking");
					}else {
						appLog.error("ip address drop down list is not visible in watermarking");
						sa.assertTrue(false, "ip address drop down list is not visible in watermarking");
					}
					if(nim.getEmailAddressLabel(10)!=null) {
						appLog.info("email address check box is visible in watermakring");
					}else {
						appLog.error("email address check box is not visible in watermakring");
						sa.assertTrue(false, "email address check box is visible in watermakring");
					}
					if(nim.getEmailAddressLabelDropDown(10)!=null) {
						appLog.info("email address drop down list is visible in watermarking");
					}else {
						appLog.error("email address drop down list is not visible in watermarking");
						sa.assertTrue(false, "email address drop down list is not visible in watermarking");
					}
					if(nim.getCustomLabelCheckBox(10)!=null) {
						appLog.info("custom label check box is visible in watermarking");
					}else {
						appLog.error("custom label check box is not visible in watermarking");
						sa.assertTrue(false, "custom label check box is not visible in watermarking");
					}
			}else {
				appLog.error("Not able to click on watermarking tab so cannot verify watermarking UI");
				sa.assertTrue(false, "Not able to click on watermarking tab so cannot verify watermarking UI");
			}
		}else {
			appLog.error("Not able to click on Nim tab so cannot verify watermarking tab");
			sa.assertTrue(false, "Not able to click on Nim tab so cannot verify watermarking tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc004_verifyErrorMsgAtUser() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if(nim.clickOnSideMenusTab(sideMenu.Watermarking)) {
				if(nim.clickOnEditIcon()) {
					if(nim.getWaterMarkingInsufficientPermissionHeadertext(10)!=null) {
						String aa=getText(driver, nim.getWaterMarkingInsufficientPermissionHeadertext(10),"header text", action.SCROLLANDBOOLEAN);
						if(aa.contains("Insufficient Permissions")) {
							appLog.info("insufficient permission header text is matched");
						}else {
							appLog.error("insufficient permission header text is not matched");
							sa.assertTrue(false, "insufficient permission header text is not matched");
						}
					}else {
						appLog.error("Insufficient permission header text is not visible");
						sa.assertTrue(false, "Insufficient permission header text is not visible");
					}
					if(nim.getWatermarkingInsufficientPermissionCrossIcon(10)!=null) {
						appLog.info("cross icon is visible on insufficient permission pop up");
					}else {
						appLog.error("cross icon is not visible on insufficient permission pop up");
						sa.assertTrue(false, "cross icon is not visible on insufficient permission pop up");
					}
					if(nim.getWaterMarkingInsufficientPermissionErrorMsg(10)!=null) {
						String aa=getText(driver, nim.getWaterMarkingInsufficientPermissionErrorMsg(10),"error message text", action.SCROLLANDBOOLEAN);
						if(aa.contains(NIMPageErrorMessage.watermarkingInsufficientPermissionErrorMsg)) {
							appLog.info("insufficient permission error text is matched");
						}else {
							appLog.error("insufficient permission error text is not matched");
							sa.assertTrue(false, "insufficient permission error text is not matched");
						}
					}else {
						appLog.error("Insufficient permission header text is not visible");
						sa.assertTrue(false, "Insufficient permission header text is not visible");
					}
					if(click(driver, nim.getWatermarkingInsufficientPermissionCrossIcon(10), "cross icon", action.BOOLEAN)) {
						appLog.info("clicked on cross icon");
						if(nim.getWaterMarkingInsufficientPermissioncloseBtn(10)==null) {
							appLog.info("watermarking insufficient permssion popup is closed after click on cross icon");
						}else {
							appLog.error("watermarking insufficient permssion popup is not closed after click on cross icon");
							sa.assertTrue(false, "watermarking insufficient permssion popup is not closed after click on cross icon");
						}
					}else {
						appLog.error("Not able to click on watermarking insufficient permission cross icon");
						sa.assertTrue(false, "Not able to click on watermarking insufficient permission cross icon");
					}
					if(nim.clickOnEditIcon()) {
						if(click(driver, nim.getWaterMarkingInsufficientPermissioncloseBtn(10), "close button", action.BOOLEAN)) {
							appLog.info("clicked on close icon");
							if(nim.getWaterMarkingInsufficientPermissioncloseBtn(10)==null) {
								appLog.info("watermarking insufficient permssion popup is closed after click on close button");
							}else {
								appLog.error("watermarking insufficient permssion popup is not closed after click on close button");
								sa.assertTrue(false, "watermarking insufficient permssion popup is not closed after click on close button");
							}
						}else {
							appLog.error("Not able to click on watermarking insufficient permission cross icon");
							sa.assertTrue(false, "Not able to click on watermarking insufficient permission cross icon");
						}
					}else {
						appLog.error("Not abel to click on edit icon so cannot click on insufficient permssion close button");
						sa.assertTrue(false, "Not abel to click on edit icon so cannot click on insufficient permssion close button");
					}
				}else {
					appLog.error("Not able to click on edit icon so cannot verify Error Message. Error Msg : "+NIMPageErrorMessage.watermarkingInsufficientPermissionErrorMsg);
					sa.assertTrue(false, "Not able to click on edit icon so cannot verify Error Message. Error Msg : "+NIMPageErrorMessage.watermarkingInsufficientPermissionErrorMsg);
				}
				
				
			}else {
				appLog.error("Not able to click on watermarking tab so cannot verify watermarking UI");
				sa.assertTrue(false, "Not able to click on watermarking tab so cannot verify watermarking UI");
			}
		}else {
			appLog.error("Not able to click on Nim tab so cannot verify watermarking tab");
			sa.assertTrue(false, "Not able to click on Nim tab so cannot verify watermarking tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc005_verifyWaterMarkingInEditMode() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser2EmailID,adminPassword);
		if(nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if(nim.clickOnSideMenusTab(sideMenu.Watermarking)) {
				if(nim.getEditIcon(10)!=null) {
					appLog.info("Edit Icon is visible");
				}else {
					appLog.error("Edit Icon is not visible");
					sa.assertTrue(false, "Edit Icon is not visible");
				}
				if(nim.getWaterMarkingLabeltext(10)!=null) {
					if(nim.getWaterMarkingLabeltext(10).getText().trim().contains("Watermarking")) {
						appLog.info("WaterMarking label in visible on watermarking tab");
					}else {
						appLog.error("Watermakring label is not matched");
						sa.assertTrue(false, "Watermakring label is not matched");
					}
				}else {
					appLog.error("WaterMarking label is not visible");
					sa.assertTrue(false, "WaterMarking label is not visible");
				}
				if(nim.clickOnEditIcon()) {
					if(!isSelected(driver, nim.getWatermarkingActivateCheckbox(10), "watermarking activate check box")) {
						if(click(driver, nim.getWatermarkingActivateCheckbox(10), "check box", action.SCROLLANDBOOLEAN)) {
							appLog.info("watermarking is activated");
						}else {
							appLog.error("Not able to activate watermarking");
							sa.assertTrue(false, "Not able to activate watermarking");
						}
						
					}
					if(nim.getWatermarkingActivateCheckbox(10)!=null) {
						appLog.info("Apply Watermarking to PDF Documents check box is visible");
					}else {
						appLog.error("Apply Watermarking to PDF Documents check box is visible");
						sa.assertTrue(false, "Apply Watermarking to PDF Documents check box is visible");
					}
					if(compareMultipleList(driver, "Applied On Upload<break>Applied On Download",nim.getWaterMakringSectionslist()).isEmpty()) {
						appLog.info("WaterMakring Grid is divide in the two sections and label text is verified");
					}else {
						appLog.error("watermakring grid section label text is not verified");
						sa.assertTrue(false, "watermakring grid section label text is not verified");
					}
					if(compareMultipleList(driver, "Label<break>Location",nim.getWatermakringAppliedOnUploadHeaderTextList()).isEmpty()) {
						appLog.info("Applied on upload header label text is verified");
					}else {
						appLog.error("Applied on upload header label text is not verified");
						sa.assertTrue(false, "Applied on upload header label text is not verified");
					}
					if(nim.getWatermarkingFirmNameLabel(10)!=null) {
						appLog.info("Firm Name check box is visible in watermakring");
					}else {
						appLog.error("Firm Name check box is not visible in watermakring");
						sa.assertTrue(false, "Firm Name check box is visible in watermakring");
					}
					if(nim.getWaterMarkingMyFirmNameDropDown(10)!=null) {
						appLog.info("firm name drop down list is visible in watermarking");
					}else {
						appLog.error("firm name drop down list is not visible in watermarking");
						sa.assertTrue(false, "firm name drop down list is not visible in watermarking");
					}
					if(nim.getInvestorNameLabel(10)!=null) {
						appLog.info("Investor Name check box is visible in watermakring");
					}else {
						appLog.error("Investor Name check box is not visible in watermakring");
						sa.assertTrue(false, "Investor Name check box is visible in watermakring");
					}
					if(nim.getInvestorNameLabelDropDown(10)!=null) {
						appLog.info("Investor name drop down list is visible in watermarking");
					}else {
						appLog.error("Investor name drop down list is not visible in watermarking");
						sa.assertTrue(false, "Investor name drop down list is not visible in watermarking");
					}
					if(mouseOverOperation(driver, nim.getWaterMarkingInvestorNameToolTip(10))){
						String text = trim(getText(driver, nim.getWaterMarkingInvestorNametoolTipText(10), "", action.BOOLEAN));
						if(text.contains(NIMPageErrorMessage.InvestorNameToolTipErrorMsg)) {
							appLog.info("tootl tip text is matched");
						}else {
							appLog.error("investor name tooltip text is not matched Expected: "+NIMPageErrorMessage.InvestorNameToolTipErrorMsg+"/t Actual: "+text);
							sa.assertTrue(false, "investor name tooltip text is not matched Expected: "+NIMPageErrorMessage.InvestorNameToolTipErrorMsg+"/t Actual: "+text);
						}
					} else {
						appLog.error("Cannot hover the mouse on investor name info icon, So cannot verify investor name info icon message.");
						sa.assertTrue(false,"Cannot hover the mouse on info icon, So cannot verify investor name info icon message.");
					}
					if(nim.getFundNameLabel(10)!=null) {
						appLog.info("Fund Name check box is visible in watermakring");
					}else {
						appLog.error("Fund Name check box is not visible in watermakring");
						sa.assertTrue(false, "Fund Name check box is visible in watermakring");
					}
					if(nim.getFundNameLabelDropDown(10)!=null) {
						appLog.info("Fund name drop down list is visible in watermarking");
					}else {
						appLog.error("Fund name drop down list is not visible in watermarking");
						sa.assertTrue(false, "Fund name drop down list is not visible in watermarking");
					}
					if(compareMultipleList(driver, "Label<break>Location",nim.getWaterMarkingAppliedOnDownloadHeaderTextList()).isEmpty()) {
						appLog.info("Applied on download header label text is verified");
					}else {
						appLog.error("Applied on download header label text is not verified");
						sa.assertTrue(false, "Applied on download header label text is not verified");
					}
					if(nim.getDownloadDateLabel(10)!=null) {
						appLog.info("download date check box is visible in watermakring");
					}else {
						appLog.error("download date check box is not visible in watermakring");
						sa.assertTrue(false, "download date check box is visible in watermakring");
					}
					if(nim.getDownloadDateLabelDropDown(10)!=null) {
						appLog.info("download date drop down list is visible in watermarking");
					}else {
						appLog.error("download date drop down list is not visible in watermarking");
						sa.assertTrue(false, "download date drop down list is not visible in watermarking");
					}
					if(nim.getIPAddressLabel(10)!=null) {
						appLog.info("ip address check box is visible in watermakring");
					}else {
						appLog.error("ip address check box is not visible in watermakring");
						sa.assertTrue(false, "ip address check box is visible in watermakring");
					}
					if(nim.getIPAddressLabelDropDown(10)!=null) {
						appLog.info("ip address drop down list is visible in watermarking");
					}else {
						appLog.error("ip address drop down list is not visible in watermarking");
						sa.assertTrue(false, "ip address drop down list is not visible in watermarking");
					}
					if(nim.getEmailAddressLabel(10)!=null) {
						appLog.info("email address check box is visible in watermakring");
					}else {
						appLog.error("email address check box is not visible in watermakring");
						sa.assertTrue(false, "email address check box is visible in watermakring");
					}
					if(nim.getEmailAddressLabelDropDown(10)!=null) {
						appLog.info("email address drop down list is visible in watermarking");
					}else {
						appLog.error("email address drop down list is not visible in watermarking");
						sa.assertTrue(false, "email address drop down list is not visible in watermarking");
					}
					if(nim.getCustomLabelCheckBox(10)!=null) {
						appLog.info("custom label check box is visible in watermarking");
					}else {
						appLog.error("custom label check box is not visible in watermarking");
						sa.assertTrue(false, "custom label check box is not visible in watermarking");
					}
					if(nim.getCustomLabelAddRowLink(10)!=null) {
						appLog.info("custom label add row link is visible in watermarking");
					}else {
						appLog.error("custom label add row link is not visible in watermarking");
						sa.assertTrue(false, "custom label add row link is not visible in watermarking");
					}
					if(nim.getWatermarkingSaveButton(10)!=null) {
						appLog.info("save button is visible in watermarking");
					}else {
						appLog.error("save button is not visible in watermarking");
						sa.assertTrue(false, "save button is not visible in watermarking");
					}
					if(nim.getWaterMarkingCancelBtn(10)!=null) {
						appLog.info("cancel button is visible in watermarking");
					}else {
						appLog.error("cancel button is not visible in watermarking");
						sa.assertTrue(false, "cancel button is not visible in watermarking");
					}
					if(click(driver, nim.getCustomLabelCheckBox(10), "custom check box", action.SCROLLANDBOOLEAN)) {
						for(int i=0 ;i<2; i++) {
							if(click(driver, nim.getCustomLabelAddRowLink(10), "add custom label link", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on Add Row Link");
								if(nim.getWatermarkingCustomTextBoxList().size()==(i+2)) {
									appLog.info("WaterMarking Custom Text box is added ");
								}else {
									appLog.error("WaterMarking Custom Text Box is not added");
									sa.assertTrue(false, "WaterMarking Custom Text Box is not added");
								}
								if(nim.getWaterMarkingCustomDropDownList().size()==(i+2)) {
									appLog.info("WaterMarking custom Dropdown list is added");
								}else {
									appLog.error("Watermarking Custom DropDown list is not added");
									sa.assertTrue(false, "Watermarking Custom DropDown list is not added");
								}
							}else {
								appLog.error("Not able to add Custom label text box");
								sa.assertTrue(false, "Not able to add Custom label text box");
							}
						}
						
					}else {
						appLog.error("Not able to click on custom  watermarking check box");
						sa.assertTrue(false, "Not able to click on custom  watermarking check box");
					}
					if(click(driver, nim.getWatermarkingFirmNameLabel(10), "firm name check box", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on firm name check box");
					}else {
						appLog.error("Not able to click on firm name check box");
						sa.assertTrue(false, "Not able to click on firm name check box");
					}
					if(click(driver, nim.getFundNameLabel(10), "fund name check box", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on fund name check box");
					}else {
						appLog.error("Not able to click on fund name check box");
						sa.assertTrue(false, "Not able to click on fund name check box");
					}
					if(click(driver, nim.getWaterMarkingCancelBtn(10), "cancel button", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on cancel button");
					}else {
						appLog.error("Not able to click watermarking cancel button");
						sa.assertTrue(false, "Not able to click watermarking cancel button");
					}
					if(!isSelected(driver, nim.getWatermarkingFirmNameLabel(10), "firm name check box")) {
						appLog.info("firm name check box is not selected after click on cancel button");
					}else {
						appLog.error("firm name check box is  selected after click on cancel button");
						sa.assertTrue(false, "firm name check box is  selected after click on cancel button");
					}
					if(!isSelected(driver, nim.getFundNameLabel(10), "fund name check box")) {
						appLog.info("fund name check box is not selected after click on cancel button");
					}else {
						appLog.error("fund name check box is  selected after click on cancel button");
						sa.assertTrue(false, "fund name check box is  selected after click on cancel button");
					}
				}else {
					appLog.error("Not able to click on Edit icon so cannot verify watermarking UI");
					sa.assertTrue(false, "Not able to click on Edit icon so cannot verify watermarking UI");
				}
			}else {
				appLog.error("Not able to click on watermarking tab so cannot verify watermarking UI");
				sa.assertTrue(false, "Not able to click on watermarking tab so cannot verify watermarking UI");
			}
		}else {
			appLog.error("Not able to click on Nim tab so cannot verify watermarking tab");
			sa.assertTrue(false, "Not able to click on Nim tab so cannot verify watermarking tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc006_verifyErrorMsg() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser2EmailID,adminPassword);
		if(nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if(nim.clickOnSideMenusTab(sideMenu.Watermarking)) {
				if(nim.clickOnEditIcon()) {
					if(!isSelected(driver, nim.getWatermarkingActivateCheckbox(10), "watermarking activate check box")) {
						if(click(driver, nim.getWatermarkingActivateCheckbox(10), "check box", action.SCROLLANDBOOLEAN)) {
							appLog.info("watermarking is activated");
						}else {
							appLog.error("Not able to activate watermarking");
							sa.assertTrue(false, "Not able to activate watermarking");
						}
						
					}else {
						appLog.info("WaterMarking settings is already activated");
					}
					if(click(driver, nim.getCustomLabelCheckBox(10), "custom check box", action.SCROLLANDBOOLEAN)) {
						int count =1;
						for(int i=0 ;i<8; i++) {
							if (nim.clickUsingCssSelectorPath("#btnAddMore", "add custom label")) {
							//if(click(driver, nim.getCustomLabelAddRowLink(10), "add custom label link", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on Add Row Link");
								if(i!=7) {
									if(nim.getWatermarkingCustomTextBoxList().size()==(i+2)) {
										appLog.info((i+2)+" : Custom Text box has been displayed");
										count++;
									}else {
										appLog.error((i+2)+" : Custom Text Box should be visible");
										sa.assertTrue(false, (i+2)+" : Custom Text Box should be visible");
									}
									if(nim.getWaterMarkingCustomDropDownList().size()==(i+2)) {
										appLog.info((i+2)+": custom Dropdown list has been displayed");
									}else {
										appLog.error((i+2)+": custom Dropdown list should be displayed");
										sa.assertTrue(false, (i+2)+": Custom DropDown should be displayed");
									}
								}
								if(i==7) {
									if(isAlertPresent(driver)) {
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if(msg.equalsIgnoreCase(NIMPageErrorMessage.AddRowErrorMsg)) {
											appLog.info("Error Message is verified: "+NIMPageErrorMessage.AddRowErrorMsg);
										}else {
											appLog.error("Error message is not matched. Expected: "+NIMPageErrorMessage.AddRowErrorMsg+" Actual Result: "+msg);
											sa.assertTrue(false, "Error message is not matched. Expected: "+NIMPageErrorMessage.AddRowErrorMsg+" Actual Result: "+msg);
										}
									}else {
										appLog.error("Alert is not present so cannot check error message : "+NIMPageErrorMessage.AddRowErrorMsg);
										sa.assertTrue(false, "Alert is not present so cannot check error message : "+NIMPageErrorMessage.AddRowErrorMsg);
									}
								}
							}else {
								appLog.error("Not able to add Custom label text box");
								sa.assertTrue(false, "Not able to add Custom label text box");
							}
						}
						List<WebElement> lst = nim.getWaterMarkingCustomFieldCrossIconList();
						System.err.println(count);
						for(int j=0; j<count-1; j++) {
							if(click(driver, lst.get(j), "custom field cross icon", action.SCROLLANDBOOLEAN)) {
								int textbox=nim.getWatermarkingCustomTextBoxList().size();
								int dropdown=nim.getWaterMarkingCustomDropDownList().size();
								if(textbox==(count-(j+1))) {
									appLog.info(count-(j+1)+" : Custom Text box has been displayed");
								}else {
									appLog.error(count-(j+1)+" : Custom Text Box should be visible. Actual: "+textbox);
									sa.assertTrue(false, count-(j+1)+" : Custom Text Box should be visible. Actual: "+textbox);
								}
								if(dropdown==count-(j+1)) {
									appLog.info(count-(j+1)+": custom Dropdown list has been displayed");
								}else {
									appLog.error(count-(j+1)+": custom Dropdown list should be displayed. Actual: "+dropdown);
									sa.assertTrue(false, count-(j+1)+": Custom DropDown should be displayed. Actual: "+dropdown);
								}
								
							}
						}
						if(isSelected(driver, nim.getCustomLabelCheckBox(10), "custom check box")) {
							if(click(driver, nim.getCustomLabelCheckBox(10), "custom check box", action.SCROLLANDBOOLEAN)) {
								appLog.info("custom check box is unchecked");
							}else {
								appLog.error("Not able to uncheck custom label check box");
								sa.assertTrue(false, "Not able to uncheck custom label check box");
							}
						}else {
							appLog.error("Custom check box is not selected");
							sa.assertTrue(false, "Custom check box is not selecteded");
						}
					}else {
						appLog.error("Not able to click on custom  watermarking check box");
						sa.assertTrue(false, "Not able to click on custom  watermarking check box");
					}
					List<WebElement> lst = nim.getWaterMarkingCheckBoxList();
					List<WebElement> dropdown=nim.getWatermarkingDropDownList();
					String[] errorMsg= {NIMPageErrorMessage.FirmNamePleaseSelectLocationErrorMsg,NIMPageErrorMessage.InvestorNamePleaseSelectLocationErrorMsg,NIMPageErrorMessage.FundNamePleaseSelectLocationErrorMsg,
							NIMPageErrorMessage.DatePleaseSelectLocationErrorMsg,NIMPageErrorMessage.IPAddressPleaseSelectLocationErrorMsg,NIMPageErrorMessage.EmailAddressPleaseSelectLocationErrorMsg};
					if(!lst.isEmpty()) {
						for(int i=0 ;i<lst.size()-1; i++) {
							if(!isSelected(driver, lst.get(i), "check box")) {
								if(click(driver,lst.get(i), "check box", action.SCROLLANDBOOLEAN)) {
									appLog.info("label clicked on check box");
								}else {
									appLog.error("Not able to click on check box so cannot check error message: "+errorMsg[i]);
									sa.assertTrue(false, "Not able to click on check box so cannot check error message: "+errorMsg[i]);
									break;
								}
							}else {
								appLog.info("label check Box is already checked");
							}
							if(getSelectedOptionOfDropDown(driver, dropdown.get(i), "drop down", "text").trim()!="None") {
								if(selectVisibleTextFromDropDown(driver,dropdown.get(i), "drop down","None")) {
									appLog.info("Select None value from drop down list");
								}else {
									appLog.error("Not able to select None value from drop down list so cannot check Error Message: "+errorMsg[i]);
									sa.assertTrue(false, "Not able to select None value from drop down list so cannot check Error Message: "+errorMsg[i]);
									break;
								}
							}else {
								appLog.info("None is alreadyed selected in drop down list");
							}
							if (nim.clickUsingCssSelectorPath("#savebtn", "save")) {
							//if(click(driver, nim.getWatermarkingSaveButton(10), "save button", action.SCROLLANDBOOLEAN)) {
								if(isAlertPresent(driver)) {
									String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if(msg.contains(errorMsg[i])) {
										appLog.info("Error Message is verified: "+errorMsg[i]);
									}else {
										appLog.error("Error message is not matched. Expected: "+errorMsg[i]+" Actual Result: "+msg);
										sa.assertTrue(false, "Error message is not matched. Expected: "+errorMsg[i]+" Actual Result: "+msg);
									}
								}else {
									appLog.error("Alert is not present so cannot check error message : "+errorMsg[i]);
									sa.assertTrue(false, "Alert is not present so cannot check error message : "+errorMsg[i]);
								}
								lst.clear();
								lst = nim.getWaterMarkingCheckBoxList();
								if(click(driver,lst.get(i), "check box", action.SCROLLANDBOOLEAN)) {
									appLog.info("Check box is Unchecked");
								}else {
									appLog.error("Not able to Uncheck check box");
									sa.assertTrue(false, "Not able to Uncheck check box");
								}
							}else {
								appLog.error("Not able to click on watermarking save button so cannot check error message: "+errorMsg[i]);
								sa.assertTrue(false, "Not able to click on watermarking save button so cannot check error message: "+errorMsg[i]);
							}
						}
					}else {
						appLog.error("WaterMarking label check boxes are not available so cannot check location error message");
						sa.assertTrue(false, "WaterMarking label check boxes are not available so cannot check location error message");
					}
					
					lst = nim.getWaterMarkingCheckBoxList();
					dropdown=nim.getWatermarkingDropDownList();
					if(!lst.isEmpty()) {
						for(int i=0 ;i<lst.size()-1; i++) {
							if(!isSelected(driver, lst.get(i), "check box")) {
								if(click(driver,lst.get(i), "check box", action.SCROLLANDBOOLEAN)) {
									appLog.info("label clicked on check box");
								}else {
									appLog.error("Not able to click on check box so cannot check error message: "+errorMsg[i]);
									sa.assertTrue(false, "Not able to click on check box so cannot check error message: "+errorMsg[i]);
								}
							}else {
								appLog.info("label check Box is already checked");
							}
							if(getSelectedOptionOfDropDown(driver, dropdown.get(i), "drop down", "text").trim()!="None") {
								if(selectVisibleTextFromDropDown(driver,dropdown.get(i), "drop down","None")) {
									appLog.info("Select None value from drop down list");
								}else {
									appLog.error("Not able to select None value from drop down list so cannot check Error Message: "+errorMsg[i]);
									sa.assertTrue(false, "Not able to select None value from drop down list so cannot check Error Message: "+errorMsg[i]);
								}
							}else {
								appLog.info("None is alreadyed selected in drop down list");
							}
						}	
						lst = nim.getWaterMarkingCheckBoxList();
						if (nim.clickUsingCssSelectorPath("#savebtn", "save button")) {
						//if(click(driver, nim.getWatermarkingSaveButton(10), "save button", action.SCROLLANDBOOLEAN)) {
							if(isAlertPresent(driver)) {
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								for(int i=0; i<errorMsg.length; i++) {
									if(msg.contains(errorMsg[i])) {
										appLog.info("Error Message is verified: "+errorMsg[i]);
									}else {
										appLog.error("Error message is not matched. Expected: "+errorMsg[i]+" Actual Result: "+msg);
										sa.assertTrue(false, "Error message is not matched. Expected: "+errorMsg[i]+" Actual Result: "+msg);
									}
									if(click(driver,lst.get(i), "check box", action.SCROLLANDBOOLEAN)) {
										appLog.info("check box is unchecked");
									}
								}
							}else {
								appLog.error("Alert is not present so cannot check error message.");
								sa.assertTrue(false, "Alert is not present so cannot check error message.");
							}
						}else {
							appLog.error("Not able to click on watermarking save button so cannot check error message");
							sa.assertTrue(false, "Not able to click on watermarking save button so cannot check error message");
						}

					}else {
						appLog.error("WaterMarking label check boxes are not available so cannot check location error message");
						sa.assertTrue(false, "WaterMarking label check boxes are not available so cannot check location error message");
					}
					lst = nim.getWaterMarkingCheckBoxList();
					dropdown=nim.getWatermarkingDropDownList();
					if(!lst.isEmpty()) {
						for(int i=0 ;i<lst.size()-4; i++) {
							if(!isSelected(driver, lst.get(i), "check box")) {
								if(click(driver,lst.get(i), "check box", action.SCROLLANDBOOLEAN)) {
									appLog.info("label clicked on check box");
								}else {
									appLog.error("Not able to click on check box so cannot check error message: "+NIMPageErrorMessage.SameLocationErrorMsg);
									sa.assertTrue(false, "Not able to click on check box so cannot check error message: "+NIMPageErrorMessage.SameLocationErrorMsg);
								}
							}else {
								appLog.info("label check Box is already checked");
							}
							if(getSelectedOptionOfDropDown(driver, dropdown.get(i), "drop down", "text").trim()!="Top: Left") {
								if(selectVisibleTextFromDropDown(driver,dropdown.get(i), "drop down","Top: Left")) {
									appLog.info("Select Top: Left value from drop down list");
								}else {
									appLog.error("Not able to select Top: Left value from drop down list so cannot check Error Message: "+NIMPageErrorMessage.SameLocationErrorMsg);
									sa.assertTrue(false, "Not able to select Top: Left value from drop down list so cannot check Error Message: "+NIMPageErrorMessage.SameLocationErrorMsg);
								}
							}else {
								appLog.info("Top: Left is alreadyed selected in drop down list");
							}
						}	
						if (nim.clickUsingCssSelectorPath("#savebtn", "save")) {
						//if(click(driver, nim.getWatermarkingSaveButton(10), "save button", action.SCROLLANDBOOLEAN)) {
							if(isAlertPresent(driver)) {
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if(msg.contains(NIMPageErrorMessage.SameLocationErrorMsg)) {
										appLog.info("Error Message is verified: "+NIMPageErrorMessage.SameLocationErrorMsg);
									}else {
										appLog.error("Error message is not matched. Expected: "+NIMPageErrorMessage.SameLocationErrorMsg+" Actual Result: "+msg);
										sa.assertTrue(false, "Error message is not matched. Expected: "+NIMPageErrorMessage.SameLocationErrorMsg+" Actual Result: "+msg);
									}
							}else {
								appLog.error("Alert is not present so cannot check error message.");
								sa.assertTrue(false, "Alert is not present so cannot check error message.");
							}
						}else {
							appLog.error("Not able to click on watermarking save button so cannot check error message");
							sa.assertTrue(false, "Not able to click on watermarking save button so cannot check error message");
						}

					}else {
						appLog.error("WaterMarking label check boxes are not available so cannot check location error message");
						sa.assertTrue(false, "WaterMarking label check boxes are not available so cannot check location error message");
					}
					if(nim.resetWaterMarkingSetting().isEmpty()) {
						if (nim.clickUsingCssSelectorPath("#savebtn", "save")) {
						//if(click(driver, nim.getWatermarkingSaveButton(10), "save button", action.SCROLLANDBOOLEAN)) {
							if(isAlertPresent(driver)) {
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if(msg.contains(NIMPageErrorMessage.pleaseSelectAleastOneLabelErrorMsg)) {
										appLog.info("Error Message is verified: "+NIMPageErrorMessage.pleaseSelectAleastOneLabelErrorMsg);
									}else {
										appLog.error("Error message is not matched. Expected: "+NIMPageErrorMessage.pleaseSelectAleastOneLabelErrorMsg+" Actual Result: "+msg);
										sa.assertTrue(false, "Error message is not matched. Expected: "+NIMPageErrorMessage.pleaseSelectAleastOneLabelErrorMsg+" Actual Result: "+msg);
									}
							}else {
								appLog.error("Alert is not present so cannot check error message.");
								sa.assertTrue(false, "Alert is not present so cannot check error message.");
							}
						}else {
							appLog.error("Not able to click on watermarking save button so cannot check error message"+NIMPageErrorMessage.pleaseSelectAleastOneLabelErrorMsg);
							sa.assertTrue(false, "Not able to click on watermarking save button so cannot check error message"+NIMPageErrorMessage.pleaseSelectAleastOneLabelErrorMsg);
						}
						lst = nim.getWaterMarkingCheckBoxList();
						dropdown=nim.getWatermarkingDropDownList();
						if(!lst.isEmpty()) {
							for(int i=3 ;i<lst.size()-1; i++) {
								if(!isSelected(driver, lst.get(i), "check box")) {
									if(click(driver,lst.get(i), "check box", action.SCROLLANDBOOLEAN)) {
										appLog.info("label clicked on check box");
									}else {
										appLog.error("Not able to click on check box so cannot check error message: "+NIMPageErrorMessage.SameLocationErrorMsg);
										sa.assertTrue(false, "Not able to click on check box so cannot check error message: "+NIMPageErrorMessage.SameLocationErrorMsg);
									}
								}else {
									appLog.info("label check Box is already checked");
								}
								if(getSelectedOptionOfDropDown(driver, dropdown.get(i), "drop down", "text").trim()!="Top: Left") {
									if(selectVisibleTextFromDropDown(driver,dropdown.get(i), "drop down","Top: Left")) {
										appLog.info("Select Top: Left value from drop down list");
									}else {
										appLog.error("Not able to select Top: Left value from drop down list so cannot check Error Message: "+NIMPageErrorMessage.SameLocationErrorMsg);
										sa.assertTrue(false, "Not able to select Top: Left value from drop down list so cannot check Error Message: "+NIMPageErrorMessage.SameLocationErrorMsg);
									}
								}else {
									appLog.info("Top: Left is alreadyed selected in drop down list");
								}
							}	
							if (nim.clickUsingCssSelectorPath("#savebtn", "save")) {
							//if(click(driver, nim.getWatermarkingSaveButton(10), "save button", action.SCROLLANDBOOLEAN)) {
								if(isAlertPresent(driver)) {
									String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if(msg.contains(NIMPageErrorMessage.SameLocationErrorMsg)) {
											appLog.info("Error Message is verified: "+NIMPageErrorMessage.SameLocationErrorMsg);
										}else {
											appLog.error("Error message is not matched. Expected: "+NIMPageErrorMessage.SameLocationErrorMsg+" Actual Result: "+msg);
											sa.assertTrue(false, "Error message is not matched. Expected: "+NIMPageErrorMessage.SameLocationErrorMsg+" Actual Result: "+msg);
										}
								}else {
									appLog.error("Alert is not present so cannot check error message.");
									sa.assertTrue(false, "Alert is not present so cannot check error message.");
								}
							}else {
								appLog.error("Not able to click on watermarking save button so cannot check error message");
								sa.assertTrue(false, "Not able to click on watermarking save button so cannot check error message");
							}

						}else {
							appLog.error("WaterMarking label check boxes are not available so cannot check location error message");
							sa.assertTrue(false, "WaterMarking label check boxes are not available so cannot check location error message");
						}
					}else {
						appLog.error("Watermarking Settings is not reset so cannot check Error Message"+NIMPageErrorMessage.SameLocationErrorMsg);
						sa.assertTrue(false, "Watermarking Settings is not reset so cannot check Error Message"+NIMPageErrorMessage.SameLocationErrorMsg);
					}
					if(nim.resetWaterMarkingSetting().isEmpty()) {
						dropdown=nim.getWatermarkingDropDownList();
						if(!lst.isEmpty()) {
							if(!isSelected(driver, nim.getCustomLabelCheckBox(10), "custom check box")) {
								if(click(driver,nim.getCustomLabelCheckBox(10), " custom check box", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on  custom label check box");
								}else {
									appLog.error("Not able to click on custom label check box so cannot check error message: "+NIMPageErrorMessage.CustomLabelValueErrorMsg);
									sa.assertTrue(false, "Not able to click on  custom label check box so cannot check error message: "+NIMPageErrorMessage.CustomLabelValueErrorMsg);
								}
							}else {
								appLog.info(" custom label check Box is already checked");
							}
							if (nim.clickUsingCssSelectorPath("#savebtn", "save")) {
								//if(click(driver, nim.getWatermarkingSaveButton(10), "save button", action.SCROLLANDBOOLEAN)) {
								if(isAlertPresent(driver)) {
									String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if(msg.contains(NIMPageErrorMessage.CustomLabelValueErrorMsg)) {
											appLog.info("Error Message is verified: "+NIMPageErrorMessage.CustomLabelValueErrorMsg);
										}else {
											appLog.error("Error message is not matched. Expected: "+NIMPageErrorMessage.CustomLabelValueErrorMsg+" Actual Result: "+msg);
											sa.assertTrue(false, "Error message is not matched. Expected: "+NIMPageErrorMessage.CustomLabelValueErrorMsg+" Actual Result: "+msg);
										}
								}else {
									appLog.error("Alert is not present so cannot check error message.");
									sa.assertTrue(false, "Alert is not present so cannot check error message.");
								}
							}else {
								appLog.error("Not able to click on watermarking save button so cannot check error message"+NIMPageErrorMessage.CustomLabelValueErrorMsg);
								sa.assertTrue(false, "Not able to click on watermarking save button so cannot check error message"+NIMPageErrorMessage.CustomLabelValueErrorMsg);
							}
							if(sendKeys(driver,nim.getWatermarkingCustomTextBoxList().get(0), "value1","custom label text box", action.SCROLLANDBOOLEAN)) {
								if (nim.clickUsingCssSelectorPath("#savebtn", "save")) {
								//if(click(driver, nim.getWatermarkingSaveButton(10), "save button", action.SCROLLANDBOOLEAN)) {
									if(isAlertPresent(driver)) {
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											if(msg.contains(NIMPageErrorMessage.CustomLabelDropDownListErrorMsg)) {
												appLog.info("Error Message is verified: "+NIMPageErrorMessage.CustomLabelDropDownListErrorMsg);
											}else {
												appLog.error("Error message is not matched. Expected: "+NIMPageErrorMessage.CustomLabelDropDownListErrorMsg+" Actual Result: "+msg);
												sa.assertTrue(false, "Error message is not matched. Expected: "+NIMPageErrorMessage.CustomLabelDropDownListErrorMsg+" Actual Result: "+msg);
											}
									}else {
										appLog.error("Alert is not present so cannot check error message."+NIMPageErrorMessage.CustomLabelDropDownListErrorMsg);
										sa.assertTrue(false, "Alert is not present so cannot check error message."+NIMPageErrorMessage.CustomLabelDropDownListErrorMsg);
									}
								}else {
									appLog.error("Not able to click on save button so cannot check error message :"+NIMPageErrorMessage.CustomLabelDropDownListErrorMsg);
									sa.assertTrue(false, "Not able to click on save button so cannot check error message :"+NIMPageErrorMessage.CustomLabelDropDownListErrorMsg);
								}
								
							}else {
								appLog.error("Not able to pass value in custom label text box so cannot check error message"+NIMPageErrorMessage.CustomLabelDropDownListErrorMsg);
								sa.assertTrue(false, "Not able to pass value in custom label text box so cannot check error message"+NIMPageErrorMessage.CustomLabelDropDownListErrorMsg);
							}
							if(sendKeys(driver,nim.getWatermarkingCustomTextBoxList().get(0), "value1;","custom label text box", action.SCROLLANDBOOLEAN)) {
								if (nim.clickUsingCssSelectorPath("#savebtn", "save")) {
									//if(click(driver, nim.getWatermarkingSaveButton(10), "save button", action.SCROLLANDBOOLEAN)) {
									if(isAlertPresent(driver)) {
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											if(msg.contains(NIMPageErrorMessage.WaterMarkingspiecalCharErrorMsg)) {
												appLog.info("Error Message is verified: "+NIMPageErrorMessage.WaterMarkingspiecalCharErrorMsg);
											}else {
												appLog.error("Error message is not matched. Expected: "+NIMPageErrorMessage.WaterMarkingspiecalCharErrorMsg+" Actual Result: "+msg);
												sa.assertTrue(false, "Error message is not matched. Expected: "+NIMPageErrorMessage.WaterMarkingspiecalCharErrorMsg+" Actual Result: "+msg);
											}
									}else {
										appLog.error("Alert is not present so cannot check error message."+NIMPageErrorMessage.WaterMarkingspiecalCharErrorMsg);
										sa.assertTrue(false, "Alert is not present so cannot check error message."+NIMPageErrorMessage.WaterMarkingspiecalCharErrorMsg);
									}
								}else {
									appLog.error("Not able to click on save button so cannot check error message :"+NIMPageErrorMessage.WaterMarkingspiecalCharErrorMsg);
									sa.assertTrue(false, "Not able to click on save button so cannot check error message :"+NIMPageErrorMessage.WaterMarkingspiecalCharErrorMsg);
								}
								
							}else {
								appLog.error("Not able to pass value in custom label text box so cannot check error message"+NIMPageErrorMessage.WaterMarkingspiecalCharErrorMsg);
								sa.assertTrue(false, "Not able to pass value in custom label text box so cannot check error message"+NIMPageErrorMessage.WaterMarkingspiecalCharErrorMsg);
							}

						}else {
							appLog.error("WaterMarking label check boxes are not available so cannot check location error message");
							sa.assertTrue(false, "WaterMarking label check boxes are not available so cannot check location error message");
						}
					}else {
						appLog.error("Not able to reset watermarking setting so cannot verify Custom text box Error Message");
						sa.assertTrue(false, "Not able to reset watermarking setting so cannot verify Custom text box Error Message");
					}
					
				}else {
					appLog.error("Not able to click on edit icon so cannot verify Error Message.");
					sa.assertTrue(false, "Not able to click on edit icon so cannot verify Error Message.");
				}
				
				
			}else {
				appLog.error("Not able to click on watermarking tab so cannot verify watermarking UI");
				sa.assertTrue(false, "Not able to click on watermarking tab so cannot verify watermarking UI");
			}
		}else {
			appLog.error("Not able to click on Nim tab so cannot verify watermarking tab");
			sa.assertTrue(false, "Not able to click on Nim tab so cannot verify watermarking tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc007_1_buildFWRWorkSpaceandInviteContact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String sharedfolderpath=ExcelUtils.readData("FilePath",0, 3, currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.Fund_Email);
				String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.Fund_Description);
				String templateName=ExcelUtils.readData("Foldertemp", 0, 1);
				String[] data= {Size,vintageyear,contact,phone,email,description};
				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,M13Institution1+"<break>"+M13Institution2, Workspace.FundraisingWorkspace,60)) {
					appLog.info("FundRaising work is build successfully.");
					if(fp.inviteContact(environment,mode,M13Institution1, M13Contact1EmailId,null, FolderType.Standard,"Upload","Yes", "Yes","All Folders", Workspace.FundraisingWorkspace, null)) {
						appLog.info("contact is invited from institution in fundraising workspace successfully: "+M13Contact1FirstName+" "+M13Contact1LastName);
					}else {
						appLog.error("Not able to invite contact from institution from fundraising workspace: "+M13Contact1FirstName+" "+M13Contact1LastName);
						sa.assertTrue(false, "Not able to invite contact from institution from fundraising workspace: "+M13Contact1FirstName+" "+M13Contact1LastName);
					}
					if(fp.inviteContact(environment,mode,null , M13Contact1EmailId,sharedfolderpath, FolderType.Shared,"Download","Yes", null,null, Workspace.FundraisingWorkspace, null)) {
						appLog.info("contact is invites successfully from shared folder in fundraising workspace: "+M13Contact1FirstName+" "+M13Contact1LastName);
					}else {
						appLog.error("Not able to invite contact from shared folder in fundraising workspace: "+M13Contact1FirstName+" "+M13Contact1LastName);
						sa.assertTrue(false, "Not able to invite contact from shared folder in fundraising workspace: "+M13Contact1FirstName+" "+M13Contact1LastName);
					}
				}else {
					appLog.error("Not able to bulid fundraising workspace on fund: "+M13FundName1);
					sa.assertTrue(false, "Not able to bulid fundraising workspace on fund: "+M13FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot build fundraising workspace: "+M13FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build fundraising workspace: "+M13FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace: "+M13FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace: "+M13FundName1);
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc007_2_uploadDocumentInFWR() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
				String FWR_docpath="UploadFiles\\Module13\\FileToUploadCRMSide\\UploadFilesCRMSide\\FWR\\Standard";
				if(fp.uploadFile(folderpath,M13Institution1+"<break>"+M13Institution2, FWR_docpath,null,UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
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
						appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				switchToDefaultContent(driver);
				String CommonPath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
				String docpath="UploadFiles\\Module13\\FileToUploadCRMSide\\UploadFilesCRMSide\\FWR\\Common";
				if(fp.uploadFile(CommonPath,null, docpath,null,UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
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
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+CommonPath);
					sa.assertTrue(false, "File is not uploaded in "+CommonPath);
				}
				switchToDefaultContent(driver);
				String SharedFolderpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
				docpath="UploadFiles\\Module13\\FileToUploadCRMSide\\UploadFilesCRMSide\\FWR\\Shared";
				if(fp.uploadFile(SharedFolderpath,null, docpath,null,UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
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
							}
							
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+SharedFolderpath);
					sa.assertTrue(false, "File is not uploaded in "+SharedFolderpath);
				}
				switchToDefaultContent(driver);
				String Internalfolderpath=ExcelUtils.readData("FilePath",0,2,currentlyExecutingTC);
				docpath="UploadFiles\\Module13\\FileToUploadCRMSide\\UploadFilesCRMSide\\FWR\\Internal";
				if(fp.uploadFile(Internalfolderpath,null, docpath,null,UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
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
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents");
					}
				}else {
					appLog.error("File is not uploaded in "+Internalfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+Internalfolderpath);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M13FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M13FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc008_activateWaterMarkingAndManageApprovals() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser2EmailID,adminPassword);
		if(nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if(nim.clickOnSideMenusTab(sideMenu.Watermarking)) {
				String WatermarkingLabels=ExcelUtils.readData("FilePath",0,30,currentlyExecutingTC);
				if(nim.activateWatermarking2(WatermarkingLabels).isEmpty()) {
					appLog.info("WaterMarking is activate Successfully");
				}else {
					appLog.error("WaterMarking setting is not activated");
					sa.assertTrue(false, "WaterMarking setting is not activated");
				}
			}else {
				appLog.error("Not able to click on watermarking side menu so cannot activate watermarking settings");
				sa.assertTrue(false, "Not able to click on watermarking side menu so cannot activate watermarking settings");
			}
			if(nim.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
				ThreadSleep(3000);
				if(nim.activateManageApprovalsSettings(CRMUser1FirstName+" "+CRMUser1LastName).isEmpty()) {
					appLog.info("Manage approvals settings is activated successfully and access granted to crm user: "+CRMUser1FirstName+" "+CRMUser1LastName);
				}else {
					appLog.error("Manage approvals settings is not activated and Not able to grant access to crm user: "+CRMUser1FirstName+" "+CRMUser1LastName);
					sa.assertTrue(false, "Manage approvals settings is not activated and Not able to grant access to crm user: "+CRMUser1FirstName+" "+CRMUser1LastName);
				}
			}else {
				appLog.error("Not able to click on watermarking side menu so cannot activate Manage Approvals settings");
				sa.assertTrue(false, "Not able to click on watermarking side menu so cannot activate Manage Approvals settings");
			}
		}else {
			appLog.error("Not able to click on nim tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on nim tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc009_onlineImportDocumentInFWR() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,8,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		String Commonfolderpath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
		String CommonfileName=ExcelUtils.readData("FilePath",0,5,currentlyExecutingTC);
		String sharedfolderpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
		String sharedfileName=ExcelUtils.readData("FilePath",0,7,currentlyExecutingTC);
		String Internalfolderpath=ExcelUtils.readData("FilePath",0,2,currentlyExecutingTC);
		String InternalfileName=ExcelUtils.readData("FilePath",0,6,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				if(fp.onlineImport(environment, mode, M13Institution1,null,M13Institution2,folderpath, docPath, fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.MultipleInstitute, WorkSpaceAction.UPLOAD, FolderType.Standard,PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
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
					switchToDefaultContent(driver);
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
				if(fp.onlineImport(environment, mode, null,null,null,Commonfolderpath, docPath, CommonfileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Common,PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
					appLog.info("file is imported successfully: "+CommonfileName+" in :"+Commonfolderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,CommonfileName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(CommonfileName+" is verified.");
							}
							
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+CommonfileName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+CommonfileName);
						}
						switchToDefaultContent(driver);
				}else {
					appLog.error("file is not imported: "+CommonfileName+" in :"+Commonfolderpath);
					sa.assertTrue(false, "file is not imported: "+CommonfileName+" in :"+Commonfolderpath);
				}
				if(fp.onlineImport(environment, mode, null,null,null,sharedfolderpath, docPath, sharedfileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Shared,PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
					appLog.info("file is imported successfully: "+sharedfileName+" in :"+sharedfolderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,sharedfileName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(sharedfileName+" is verified.");
							}
							
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+sharedfileName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+sharedfileName);
						}
						switchToDefaultContent(driver);
				}else {
					appLog.error("file is not imported: "+sharedfileName+" in :"+sharedfolderpath);
					sa.assertTrue(false, "file is not imported: "+sharedfileName+" in :"+sharedfolderpath);
				}
				
				if(fp.onlineImport(environment, mode, null,null,null,Internalfolderpath, docPath, InternalfileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Internal,PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
					appLog.info("file is imported successfully: "+InternalfileName+" in :"+Internalfolderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,InternalfileName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(InternalfileName+" is verified.");
								
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+InternalfileName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+InternalfileName);
						}
						switchToDefaultContent(driver);
				}else {
					appLog.error("file is not imported: "+InternalfileName+" in :"+Internalfolderpath);
					sa.assertTrue(false, "file is not imported: "+InternalfileName+" in :"+Internalfolderpath);
				}
			}else {
				appLog.error("Not able to click created fund "+M13FundName1+" so cannot import file");
				sa.assertTrue(false, "Not able to click created fund "+M13FundName1+" so cannot import file");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot import files");
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M13tc010_1_verifyDocumentOnFundPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.Watermarking);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String Internalfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared);
		String UploadedFileInInternal=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileShared);
		String importfileInInternal=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileInternal);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, M13Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					System.err.println(getSystemDate("hh:mm:ss"));
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+importfileInstd+" in folder "+stdfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+importfileInstd+" in folder "+stdfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						for(int i=0; i<aa.size(); i++) {
							appLog.error(aa.get(i));
							sa.assertTrue(false, aa.get(i));
						}
					}else {
						appLog.info("Watermarking labels are verified in  import file : "+importfileInstd+" in folder "+stdfolderpath);
					}
				}else {
					appLog.error("Not able to click on institution Name ::"+M13Institution1+" so cannot check watermarking on fundpage");
					sa.assertTrue(false, "Not able to click on institution Name ::"+M13Institution1+" so cannot check watermarking on fundpage");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check watermarking on fundpage");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check watermarking on fundpage");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check watermarking on fundpage");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check watermarking on fundpage");
				}
				
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(Internalfolderpath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInInternal,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Email Address<break>IP Address<break>Download Date<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name,Fund Name,Email Address,Download Date,IP Address,Label1,Label2,Label3 watermarking labels are not available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInInternal,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.FundraisingWorkspace);
						if(!aa.isEmpty()) {
							if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Email Address<break>IP Address<break>Download Date<break>Label 1<break>Label 2<break>Label 3", aa)) {
								appLog.info("My Firm Name,Investor Name,Fund Name,Email Address,Download Date,IP Address,Label1,Label2,Label3 watermarking labels are not available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
							}else {
								appLog.error("My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
								sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
							}
						}else {
							appLog.error("Watermarking is not verified on funds page in imported file: "+importfileInInternal+" in folder "+Internalfolderpath);
							sa.assertTrue(false, "Watermarking is not verified on funds page on fund in imported file: "+importfileInInternal+" in folder "+Internalfolderpath);
						}
				}else {
					appLog.error("Not able to click on shared Folder ::"+Internalfolderpath+" so cannot check watermarking on fundpage");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Internalfolderpath+" so cannot check watermarking on fundpage");
				}
				
				
			}else {
				appLog.error("Not able to click on created fund "+M13FundName1+" so cannot verify watermarking on fundpage");
				sa.assertTrue(false, "Not able to click on created fund "+M13FundName1+" so cannot verify watermarking on fundpage");
			}
		}else {
			appLog.error("Not able to click on Fund tab so cannot verify watermarking on fundpage");
			sa.assertTrue(false, "Not able to click on Fund tab so cannot verify watermarking on fundpage");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc010_2_verifyDocumentOnInstitutionPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.Watermarking);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.SharedPath);
		String Internalfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.InternalPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.CommonPath);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileShared);
		String UploadedFileInInternal=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileInternal);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileShared);
		String importfileInInternal=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileInternal);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedInstitution(M13Institution1)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, null, null, M13FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+importfileInstd+" in folder "+stdfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+importfileInstd+" in folder "+stdfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						for(int i=0; i<aa.size(); i++) {
							appLog.error(aa.get(i));
							sa.assertTrue(false, aa.get(i));
						}
					}else {
						appLog.info("Watermarking labels are verified in  import file : "+importfileInstd+" in folder "+stdfolderpath);
					}
				}else {
					appLog.error("Not able to click on folder ::"+stdfolderpath+" so cannot check watermarking on Institution page");
					sa.assertTrue(false, "Not able to click on folder ::"+stdfolderpath+" so cannot check watermarking on Institution page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, M13FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check watermarking on Institution Page");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check watermarking on Institution Page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, M13FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check watermarking on Institution Page");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check watermarking on Institution Page");
				}
				
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Internalfolderpath, null, null, M13FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInInternal,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Email Address<break>IP Address<break>Download Date<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name,Fund Name,Email Address,Download Date,IP Address,Label1,Label2,Label3 watermarking labels are not available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInInternal,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.FundraisingWorkspace);
						if(!aa.isEmpty()) {
							if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Email Address<break>IP Address<break>Download Date<break>Label 1<break>Label 2<break>Label 3", aa)) {
								appLog.info("My Firm Name,Investor Name,Fund Name,Email Address,Download Date,IP Address,Label1,Label2,Label3 watermarking labels are not available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
							}else {
								appLog.error("My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
								sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
							}
						}else {
							appLog.error("Watermarking is not verified on funds page in imported file: "+importfileInInternal+" in folder "+Internalfolderpath);
							sa.assertTrue(false, "Watermarking is not verified on funds page on fund in imported file: "+importfileInInternal+" in folder "+Internalfolderpath);
						}
				}else {
					appLog.error("Not able to click on internal Folder ::"+Internalfolderpath+" so cannot check watermarking on Institution Page");
					sa.assertTrue(false, "Not able to click on internal folder ::"+Internalfolderpath+" so cannot check watermarking on Institution Page");
				}
				
				
			}else {
				appLog.error("Not able to click on created Institution "+M13Institution1+" so cannot verify watermarking");
				sa.assertTrue(false, "Not able to click on created Institution "+M13Institution1+" so cannot verify watermarking");
			}
		}else {
			appLog.error("Not able to click on Institution tab so cannot verify watermarking on Institution page");
			sa.assertTrue(false, "Not able to click on Institution tab so cannot verify watermarking on Institution page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc010_3_verifyDocumentOnContactPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.Watermarking);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.SharedPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.CommonPath);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileShared);
		if(fp.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(M13Contact1FirstName, M13Contact1LastName, null)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, M13Institution1, null, M13FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+importfileInstd+" in folder "+stdfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+importfileInstd+" in folder "+stdfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						for(int i=0; i<aa.size(); i++) {
							appLog.error(aa.get(i));
							sa.assertTrue(false, aa.get(i));
						}
					}else {
						appLog.info("Watermarking labels are verified in  import file : "+importfileInstd+" in folder "+stdfolderpath);
					}
				}else {
					appLog.error("Not able to click on folder ::"+stdfolderpath+" so cannot check watermarking on Contact page");
					sa.assertTrue(false, "Not able to click on folder ::"+stdfolderpath+" so cannot check watermarking on Contact page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, M13FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check watermarking on Contact Page");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check watermarking on Contact Page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, M13FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check watermarking on Contact Page");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check watermarking on Contact Page");
				}
			}else {
				appLog.error("Not able to click on created Contact "+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot verify watermarking");
				sa.assertTrue(false, "Not able to click on created Contact "+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot verify watermarking");
			}
		}else {
			appLog.error("Not able to click on Contact tab so cannot verify watermarking on Contact page");
			sa.assertTrue(false, "Not able to click on Contact tab so cannot verify watermarking on Contact page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc010_4_verifyDocumentOnManageApprovalsPendingDocument() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.Watermarking);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.SharedPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.CommonPath);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileShared);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 10), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(3000);
						List<String> aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1+"<break>"+M13Institution2,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ManageApprovalsPopUp, Workspace.FundraisingWorkspace);
						if(!aa.isEmpty()) {
							for(int i=0; i<aa.size(); i++) {
								appLog.error(aa.get(i));
								sa.assertTrue(false, aa.get(i));
							}
						}else {
							appLog.info("Watermarking labels are verified in  import file : "+importfileInstd+" in folder "+stdfolderpath);
						}
						switchToFrame(driver, 20,fp.getFrame( PageName.FundsPage, 20));
						 aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ManageApprovalsPopUp, Workspace.FundraisingWorkspace);
						if(!aa.isEmpty()) {
							if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
								appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							}else {
								appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
								sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							}
							if(aa.size()==1) {
								appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							}else {
								appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
								sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							}
						}else {
							appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
						}
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ManageApprovalsPopUp, Workspace.FundraisingWorkspace);
						if(!aa.isEmpty()) {
							if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
								appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							}else {
								appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
								sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							}
							if(aa.size()==1) {
								appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							}else {
								appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
								sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							}
						}else {
							appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
						}
				}else {
					appLog.error("Not able to click on Manage approvals icon so cannot verify watermarking");
					sa.assertTrue(false, "Not able to click on Manage Approvals so cannot verify watermarking");
				}
			}else {
				appLog.error("Not able to click on created Fund "+M13FundName1+" so cannot verify watermarking");
				sa.assertTrue(false, "Not able to click on created Fund "+M13FundName1+" so cannot verify watermarking");
			}
		}else {
			appLog.error("Not able to click on Fund tab so cannot verify watermarking on Manage Approvals PopUp");
			sa.assertTrue(false, "Not able to click on Contact tab so cannot verify watermarking on manage Approvals PopUp");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc011_approveAllPendingFileAndverifyWaterMarking() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.Watermarking);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileShared);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 20), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
					if(fp.selectAllPendingFilesToApprove(WorkSpaceAction.UPLOAD).isEmpty()) {
						appLog.info("all pending document is approved successfully from manage approvals");
					}else {
						appLog.error("all pending document is not approved from manage approvals");
						sa.assertTrue(false, "all pending document is not approved from manage approvals");
					}
					if(click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 10), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
						if(click(driver, fp.getApprovedDocsTab(60), "approved document tab", action.SCROLLANDBOOLEAN)) {
							ThreadSleep(3000);
							List<String> aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1+"<break>"+M13Institution2,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ManageApprovalsPopUp, Workspace.FundraisingWorkspace);
							if(!aa.isEmpty()) {
								for(int i=0; i<aa.size(); i++) {
									appLog.error(aa.get(i));
									sa.assertTrue(false, aa.get(i));
								}
							}else {
								appLog.info("Watermarking labels are verified in  import file : "+importfileInstd+" in manage approvals approved document tab");
							}
							switchToFrame(driver, 20,fp.getFrame( PageName.FundsPage, 20));
							aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ManageApprovalsPopUp, Workspace.FundraisingWorkspace);
							if(!aa.isEmpty()) {
								if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
									appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in manage approvals approved document tab");
								}else {
									appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in manage approvals approved document tab");
									sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in manage approvals approved document tab");
								}
								if(aa.size()==1) {
									appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in manage approvals approved document tab");
								}else {
									appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in manage approvals approved document tab");
									sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in manage approvals approved document tab");
								}
							}else {
								appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in manage approvals approved document tab");
								sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in manage approvals approved document tab");
							}
							switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
							aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ManageApprovalsPopUp, Workspace.FundraisingWorkspace);
							if(!aa.isEmpty()) {
								if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
									appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in manage approvals approved document tab");
								}else {
									appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in manage approvals approved document tab");
									sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in manage approvals approved document tab");
								}
								if(aa.size()==1) {
									appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in manage approvals approved document tab");
								}else {
									appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in manage approvals approved document tab");
									sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in manage approvals approved document tab");
								}
							}else {
								appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in manage approvals approved document tab");
								sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in manage approvals approved document tab");
							}
							
						}else {
							appLog.error("Not able to click on approved document tab icon so cannot verify watermarking");
							sa.assertTrue(false, "Not able to click on approved document tab so cannot verify watermarking");
						}
				}else {
					appLog.error("Not able to click on Manage approvals icon so cannot verify watermarking");
					sa.assertTrue(false, "Not able to click on Manage Approvals so cannot verify watermarking");
				}
				}else {
					appLog.error("Not able to click on manage approvals icon so approve pending document and verify watermarking ");
					sa.assertTrue(false, "Not able to click on manage approvals icon so approve pending document and verify watermarking ");
				}
				
			}else {
				appLog.error("Not able to click on created fund "+M13FundName1+" so approve pending document and verify watermarking");
				sa.assertTrue(false, "Not able to click on created fund "+M13FundName1+" approve pending document and verify watermarking");
			}
		}else {
			appLog.error("Not able to click on Fund tab so cannot approve pending document and verify watermarking");
			sa.assertTrue(false, "Not able to click on Fund tab so cannot approve pending document and verify watermarking");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc012_1_registerContactAndVerifyWaterMarkingInAllDocumentTab() {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileShared);
		if (ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M13Contact1", excelLabel.Registered).equalsIgnoreCase("Not Registered")) {
			try {
				investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail",gmailUserName,gmailPassword,CRMUser1EmailID,M13Contact1EmailId);
				appLog.info("Investor Registration Link: " + investorRegLink);
			} catch (InterruptedException e) {
				appLog.info("Invitation mail not found.");
				e.printStackTrace();
			}
			if (investorRegLink != null) {
				driver.get(investorRegLink);
				if (bp.targetRegistration(M13Contact1FirstName, M13Contact1LastName, M13Contact1EmailId, M13Institution1,
						adminPassword)) {
					appLog.info(
							"Investor is registered successfully: " + M13Contact1FirstName + " " + M13Contact1LastName);
					ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Registered);
				} else {
					driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
					appLog.info("Register Target URL through Direct URL..");
					if (bp.targetRegistration(M13Contact1FirstName, M13Contact1LastName, M13Contact1EmailId,
							M13Institution1, adminPassword)) {
						appLog.info("Investor is registered successfully: " + M13Contact1FirstName + " "
								+ M13Contact1LastName);
						ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Registered);
						sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M13Contact1FirstName
								+ " " + M13Contact1LastName);
					} else {
						appLog.error("Not able to registered investor through direct URL:" + M13Contact1FirstName + " "
								+ M13Contact1LastName);
						sa.assertTrue(false, "Not able to registered investor through direct URL:" + M13Contact1FirstName
								+ " " + M13Contact1LastName);
					}
				}
			} else {
				driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
				appLog.info("Register Target URL through Direct URL..");
				if (bp.targetRegistration(M13Contact1FirstName, M13Contact1LastName, M13Contact1EmailId,
						M13Institution1, adminPassword)) {
					appLog.info("Investor is registered successfully: " + M13Contact1FirstName + " "
							+ M13Contact1LastName);
					ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Registered);
					sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M13Contact1FirstName
							+ " " + M13Contact1LastName);
				} else {
					appLog.error("Not able to registered investor through direct URL:" + M13Contact1FirstName + " "
							+ M13Contact1LastName);
					sa.assertTrue(false, "Not able to registered investor through direct URL:" + M13Contact1FirstName
							+ " " + M13Contact1LastName);
				}
			}
			if(click(driver, ivp.getAllDocumentsTab(20), "all document tab", action.BOOLEAN)) {
				List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab, null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
						appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available in file: "+UploadedFileInStd+" in all document tab");
					}else {
						appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available in file: "+UploadedFileInStd+" in all document tab");
						sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available in file: "+UploadedFileInStd+" in all document tab");
					}
					if(aa.size()==6) {
						appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in all document tab");
					}else {
						appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in all document tab");
						sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in all document tab");
					}
				}else {
					appLog.error("Watermarking is not verified on all document tab in file: "+UploadedFileInStd);
					sa.assertTrue(false, "Watermarking is not verified on all document tab in file: "+UploadedFileInStd);
				}
				aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab, null);
				if(!aa.isEmpty()) {
					for(int i=0; i<aa.size(); i++) {
						appLog.error(aa.get(i));
						sa.assertTrue(false, aa.get(i));
					}
				}else {
					appLog.info("Watermarking labels are verified in  import file : "+importfileInstd);
				}
				aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab,null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
						appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in all dcoument tab");
					}else {
						appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in all document tab");
						sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in all document tab");
					}
					if(aa.size()==6) {
						appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in all document tab");
					}else {
						appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in all document tab");
						sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in all document tab");
					}
				}else {
					appLog.error("Watermarking is not verified on all document tab in upload file: "+UploadedFileInCommon+" in all document tab");
					sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInCommon+" in all document tab");
				}
				aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab, null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
						appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in all document tab");
					}else {
						appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in all document tab");
						sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in all document tab");
					}
					if(aa.size()==1) {
						appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in all document tab");
					}else {
						appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in all document tab");
						sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in all document tab");
					}
				}else {
					appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in all document tab");
					sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in all document tab");
				}
				aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab, null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
						appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in all document tab");
					}else {
						appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in all document tab");
						sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in all document tab");
					}
					if(aa.size()==6) {
						appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in all document tab");
					}else {
						appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in all document tab");
						sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in all document tab");
					}
				}else {
					appLog.error("Watermarking is not verified on all document tab in upload file: "+UploadedFileInShared+" in all document tab");
					sa.assertTrue(false, "Watermarking is not verified on all document tab in upload file: "+UploadedFileInShared+" in all document tab");
				}
				aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab,null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
						appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in all document tab");
					}else {
						appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in all document tab");
						sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in all document tab");
					}
					if(aa.size()==1) {
						appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in all document tab");
					}else {
						appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in all document tab");
						sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in all document tab");
					}
				}else {
					appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in all document tab");
					sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in all document tab");
				}
			}else {
				appLog.error("Not able to click on all document tab so cannot check watermarking in all document tab documents");
				sa.assertTrue(false, "Not able to click on all document tab so cannot check watermarking in all document tab documents");
			}
			lp.investorLogout();
		} else {
			appLog.info("investor "+M13Contact1FirstName+" "+M13Contact1LastName+" is already Registered.");
			sa.assertTrue(false, "investor "+M13Contact1FirstName+" "+M13Contact1LastName+" is already Registered.");
		}
		sa.assertAll();
	}
	
	@Test
	public void M13tc012_2_VerifyWaterMarkingInRecentActivitiesTab() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		String dependOntc="M13tc012_1_registerContactAndVerifyWaterMarkingInAllDocumentTab";
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileShared);
		lp.investorLogin(M13Contact1EmailId,adminPassword);
		if(click(driver, ivp.getRecentActivitiesTab(60), "recent activities tab", action.BOOLEAN)) {
			List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.RecentActivitiesTab, null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
					appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available in file: "+UploadedFileInStd+" in recent activities tab");
				}else {
					appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available in file: "+UploadedFileInStd+" in recent activities tab");
					sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available in file: "+UploadedFileInStd+" in recent activities tab");
				}
				if(aa.size()==6) {
					appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in recent activities tab");
				}else {
					appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in recent activities tab");
					sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in recent activities tab");
				}
			}else {
				appLog.error("Watermarking is not verified on recent activities tab in file: "+UploadedFileInStd);
				sa.assertTrue(false, "Watermarking is not verified on recent activities tab in file: "+UploadedFileInStd);
			}
			aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.RecentActivitiesTab, null);
			if(!aa.isEmpty()) {
				for(int i=0; i<aa.size(); i++) {
					appLog.error(aa.get(i));
					sa.assertTrue(false, aa.get(i));
				}
			}else {
				appLog.info("Watermarking labels are verified in  import file : "+importfileInstd);
			}
			aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.RecentActivitiesTab,null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
					appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in all dcoument tab");
				}else {
					appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in recent activities tab");
					sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in recent activities tab");
				}
				if(aa.size()==6) {
					appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in recent activities tab");
				}else {
					appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in recent activities tab");
					sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in recent activities tab");
				}
			}else {
				appLog.error("Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInCommon+" in recent activities tab");
				sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInCommon+" in recent activities tab");
			}
			aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.RecentActivitiesTab, null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
					appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in recent activities tab");
				}else {
					appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in recent activities tab");
					sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in recent activities tab");
				}
				if(aa.size()==1) {
					appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in recent activities tab");
				}else {
					appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in recent activities tab");
					sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in recent activities tab");
				}
			}else {
				appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in recent activities tab");
				sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in recent activities tab");
			}
			aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.RecentActivitiesTab, null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
					appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in recent activities tab");
				}else {
					appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in recent activities tab");
					sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in recent activities tab");
				}
				if(aa.size()==6) {
					appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in recent activities tab");
				}else {
					appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in recent activities tab");
					sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in recent activities tab");
				}
			}else {
				appLog.error("Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInShared+" in recent activities tab");
				sa.assertTrue(false, "Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInShared+" in recent activities tab");
			}
			aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.RecentActivitiesTab,null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
					appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in recent activities tab");
				}else {
					appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in recent activities tab");
					sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in recent activities tab");
				}
				if(aa.size()==1) {
					appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in recent activities tab");
				}else {
					appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in recent activities tab");
					sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in recent activities tab");
				}
			}else {
				appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in recent activities tab");
				sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in recent activities tab");
			}
		}else {
			appLog.error("Not able to click on recent activities tab so cannot check watermarking in recent activities tab documents");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot check watermarking in recent activities tab documents");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc012_3_VerifyWaterMarkingInAllFirmPage() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		String dependOntc="M13tc012_1_registerContactAndVerifyWaterMarkingInAllDocumentTab";
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileShared);
		lp.investorLogin(M13Contact1EmailId,adminPassword);
		if(allfp.selectFirmName("All Firms")) {
			List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllFirmsPage, null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
					appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available in file: "+UploadedFileInStd+" in AllFirmsPage");
				}else {
					appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available in file: "+UploadedFileInStd+" in AllFirmsPage");
					sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available in file: "+UploadedFileInStd+" in AllFirmsPage");
				}
				if(aa.size()==6) {
					appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in AllFirmsPage");
				}else {
					appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in AllFirmsPage");
					sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in AllFirmsPage");
				}
			}else {
				appLog.error("Watermarking is not verified on recent activities tab in file: "+UploadedFileInStd);
				sa.assertTrue(false, "Watermarking is not verified on recent activities tab in file: "+UploadedFileInStd);
			}
			aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllFirmsPage, null);
			if(!aa.isEmpty()) {
				for(int i=0; i<aa.size(); i++) {
					appLog.error(aa.get(i));
					sa.assertTrue(false, aa.get(i));
				}
			}else {
				appLog.info("Watermarking labels are verified in  import file : "+importfileInstd);
			}
			aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllFirmsPage,null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
					appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in AllFirmsPage");
				}else {
					appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in AllFirmsPage");
					sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in AllFirmsPage");
				}
				if(aa.size()==6) {
					appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in AllFirmsPage");
				}else {
					appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in AllFirmsPage");
					sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in AllFirmsPage");
				}
			}else {
				appLog.error("Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInCommon+" in AllFirmsPage");
				sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInCommon+" in AllFirmsPage");
			}
			aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllFirmsPage, null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
					appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in AllFirmsPage");
				}else {
					appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in AllFirmsPage");
					sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in AllFirmsPage");
				}
				if(aa.size()==1) {
					appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in AllFirmsPage");
				}else {
					appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in AllFirmsPage");
					sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in AllFirmsPage");
				}
			}else {
				appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in AllFirmsPage");
				sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in AllFirmsPage");
			}
			aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllFirmsPage, null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
					appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in AllFirmsPage");
				}else {
					appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in AllFirmsPage");
					sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in AllFirmsPage");
				}
				if(aa.size()==6) {
					appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in AllFirmsPage");
				}else {
					appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in AllFirmsPage");
					sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in AllFirmsPage");
				}
			}else {
				appLog.error("Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInShared+" in AllFirmsPage");
				sa.assertTrue(false, "Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInShared+" in AllFirmsPage");
			}
			aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllFirmsPage,null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
					appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in All Firms Page");
				}else {
					appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in All Firms Page");
					sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in All Firms Page");
				}
				if(aa.size()==1) {
					appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in All Firms Page");
				}else {
					appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in All Firms Page");
					sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in All Firms Page");
				}
			}else {
				appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in All Firms Page");
				sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in All Firms Page");
			}
		}else {
			appLog.error("Not able to click on All Firms Page so cannot check watermarking in All Firms Page documents");
			sa.assertTrue(false, "Not able to click on All Firms Page so cannot check watermarking in All Firms Page documents");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc012_4_VerifyWaterMarkingInPotentialInvestmentTab() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		String dependOntc="M13tc012_1_registerContactAndVerifyWaterMarkingInAllDocumentTab";
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileShared);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.StandardPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.CommonPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.SharedPath);
		lp.investorLogin(M13Contact1EmailId,adminPassword);
		if(ivp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if(fp.verifyFolderPathdummy(stdfolderpath, M13Institution1, null, null, PageName.PotentialInvestmentPage, null, 30)) {
				List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,null,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.PotentialInvestmentPage, null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
						appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available in file: "+UploadedFileInStd+" in PotentialInvestmentPage");
					}else {
						appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available in file: "+UploadedFileInStd+" in PotentialInvestmentPage");
						sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available in file: "+UploadedFileInStd+" in PotentialInvestmentPage");
					}
					if(aa.size()==6) {
						appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in PotentialInvestmentPage");
					}else {
						appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in PotentialInvestmentPage");
						sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in PotentialInvestmentPage");
					}
				}else {
					appLog.error("Watermarking is not verified on recent activities tab in file: "+UploadedFileInStd);
					sa.assertTrue(false, "Watermarking is not verified on recent activities tab in file: "+UploadedFileInStd);
				}
				aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.PotentialInvestmentPage, null);
				if(!aa.isEmpty()) {
					for(int i=0; i<aa.size(); i++) {
						appLog.error(aa.get(i));
						sa.assertTrue(false, aa.get(i));
					}
				}else {
					appLog.info("Watermarking labels are verified in  import file : "+importfileInstd);
				}
				
			}else {
				appLog.error("Not able to click on folder path : "+stdfolderpath+" so cannot verify watermarking in standard folder files");
				sa.assertTrue(false, "Not able to click on folder path : "+stdfolderpath+" so cannot verify watermarking in standard folder files");
			}
			if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, null, PageName.PotentialInvestmentPage,null, 30)) {
				List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.PotentialInvestmentPage,null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
						appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in PotentialInvestmentPage");
					}else {
						appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in PotentialInvestmentPage");
						sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in PotentialInvestmentPage");
					}
					if(aa.size()==6) {
						appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in PotentialInvestmentPage");
					}else {
						appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in PotentialInvestmentPage");
						sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in PotentialInvestmentPage");
					}
				}else {
					appLog.error("Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInCommon+" in PotentialInvestmentPage");
					sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInCommon+" in PotentialInvestmentPage");
				}
				aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.PotentialInvestmentPage, null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
						appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in PotentialInvestmentPage");
					}else {
						appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in PotentialInvestmentPage");
						sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in PotentialInvestmentPage");
					}
					if(aa.size()==1) {
						appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in PotentialInvestmentPage");
					}else {
						appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in PotentialInvestmentPage");
						sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in PotentialInvestmentPage");
					}
				}else {
					appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in PotentialInvestmentPage");
					sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in PotentialInvestmentPage");
				}
				
			}else {
				appLog.error("Not able to click on folderpath: "+Commonfolderpath+" so cannot check watermarking in common folder files");
				sa.assertTrue(false, "Not able to click on folderpath: "+Commonfolderpath+" so cannot check watermarking in common folder files");
			}
			if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, null, PageName.PotentialInvestmentPage,null, 30)) {
				List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.PotentialInvestmentPage, null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
						appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in PotentialInvestmentPage");
					}else {
						appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in PotentialInvestmentPage");
						sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in PotentialInvestmentPage");
					}
					if(aa.size()==6) {
						appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in PotentialInvestmentPage");
					}else {
						appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in PotentialInvestmentPage");
						sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in PotentialInvestmentPage");
					}
				}else {
					appLog.error("Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInShared+" in PotentialInvestmentPage");
					sa.assertTrue(false, "Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInShared+" in PotentialInvestmentPage");
				}
				aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.PotentialInvestmentPage,null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
						appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in PotentialInvestmentPage");
					}else {
						appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in PotentialInvestmentPage");
						sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in PotentialInvestmentPage");
					}
					if(aa.size()==1) {
						appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in PotentialInvestmentPage");
					}else {
						appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in PotentialInvestmentPage");
						sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in PotentialInvestmentPage");
					}
				}else {
					appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in PotentialInvestmentPage");
					sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in PotentialInvestmentPage");
				}
			}else {
				appLog.error("Not able to click on folderpath: "+Sharedfolderpath+" so cannot check watermarking in shared folder files");
				sa.assertTrue(false, "Not able to click on folderpath: "+Sharedfolderpath+" so cannot check watermarking in shared folder files");
			}
		}else {
			appLog.error("Not able to click on PotentialInvestmentPage tab so cannot check watermarking");
			sa.assertTrue(false, "Not able to click on PotentialInvestmentPage tab so cannot check watermarking");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc013_1_uploadfileInvestorAndCheckWatermarking() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.StandardPath);
		String docpath = "UploadFiles\\Module13\\FileToUploadInvestorSide";
		lp.investorLogin(M13Contact1EmailId,adminPassword);
		if(ivp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (ivp.uploadUpdateFileInvestorSide(stdfolderpath,UploadedFileInStd, M13Institution1, null, FolderType.Standard, docpath, null, 30,
					PageName.PotentialInvestmentPage, null, null, WorkSpaceAction.UPLOAD)) {
				appLog.info("file is uploaded successfully in folderpath :"+stdfolderpath);
			}else {
				appLog.error("Not able to upload file in folderpath: "+stdfolderpath);
				sa.assertTrue(false, "Not able to upload file in folderpath: "+stdfolderpath);
			}
		}else {
			appLog.error("Not able to click on potential investment tab so cannot upload file in folderpath: "+stdfolderpath);
			sa.assertTrue(false,"Not able to click on potential investment tab so cannot upload file in folderpath: "+stdfolderpath);
		}
		lp.investorLogout();
		sa.assertAll();
	}
				
	@Test
	public void M13tc013_2_verifyWatermarkingOnHomePageAndFundPage() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.StandardPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		switchToFrame(driver, 20,fp.getFrame( PageName.HomePage, 10));
		List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, Workspace.FundraisingWorkspace);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
				appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in home alerts grid");
			}else {
				appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in home alerts grid");
				sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in home alerts grid");
			}
			if(aa.size()==6) {
				appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in home alerts grid");
			}else {
				appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in home alerts grid");
				sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in home alerts grid");
			}
			switchToDefaultContent(driver);
		}else {
			appLog.error("Watermarking is not verified in file: "+UploadedFileInStd+" on home alerts grid");
			sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInStd+" on home alerts grid");
		}
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, M13Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
					
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInStd+" in folder "+stdfolderpath);
					}
					switchToFrame(driver, 20,fp.getFrame( PageName.FundsPage, 20));
				}else {
					appLog.error("Not able to click on folderpath: "+stdfolderpath+"  so cannot check watermarking in investor side uploaded file: "+UploadedFileInStd);
					sa.assertTrue(false, "Not able to click on folderpath: "+stdfolderpath+"  so cannot check watermarking in investor side uploaded file: "+UploadedFileInStd);
				}
				if(click(driver, fp.getAlertHistoryLink(Workspace.FundraisingWorkspace,PageName.FundsPage, 10), "alert histroy link", action.SCROLLANDBOOLEAN)) {
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInStd+" in folder "+stdfolderpath);
					}
				}else {
					appLog.error("Not able to click on fund page alert history link so cannot check watermarking in investor side upload file: "+UploadedFileInStd);
					sa.assertTrue(false, "Not able to click on fund page alert history link so cannot check watermarking in investor side upload file: "+UploadedFileInStd);
				}
			}else {
				appLog.error("Not able to click on created fund "+M13FundName1+" so cannot check watermarking in investor side upload file: "+UploadedFileInStd);
				sa.assertTrue(false, "Not able to click on created fund "+M13FundName1+" so cannot check watermarking in investor side upload file: "+UploadedFileInStd);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot check watermarking in investor side uploaded file: "+UploadedFileInStd);
			sa.assertTrue(false, "Not able to click on fund tab so cannot check watermarking in investor side uploaded file: "+UploadedFileInStd);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc013_3_verifyWatermarkingOnContactPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.StandardPath);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(M13Contact1FirstName, M13Contact1LastName, null)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, M13Institution1, null, M13FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified in file: "+UploadedFileInStd+" on contact page in FWR");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInStd+" on contact page in FWR");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				}else {
					appLog.error("Not able to click on folderpath: "+stdfolderpath+" on contact page so cannot verify watermarking");
					sa.assertTrue(false, "Not able to click on folderpath: "+stdfolderpath+" on contact page so cannot verify watermarking");
				}
				if(click(driver, contact.getAlertHistoryLink(Workspace.FundraisingWorkspace, PageName.ContactsPage, 10), "alert history link", action.SCROLLANDBOOLEAN)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in alert history pop up");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in alert history pop up");
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in alert history pop up");
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in alert history pop up");
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in alert history pop up");
						}
					}else {
						appLog.error("Watermarking is not verified file: "+UploadedFileInStd+" in alert history pop up");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInStd+" in alert history pop up");
					}
				}else {
					appLog.error("Not able to click on alert history link on contact page so cannot check watermrking in file : "+UploadedFileInStd);
					sa.assertTrue(false, "Not able to click on alert history link on contact page so cannot check watermrking in file : "+UploadedFileInStd);
				}
				
			}else {
				appLog.error("Not able to click on created contact : "+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot check watermarking on contact page");
				sa.assertTrue(false, "Not able to click on created contact : "+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot check watermarking on contact page");
			}
			
		}else {
			appLog.error("Not able to click on contact tab so cannot check watermarking on contact page");
			sa.assertTrue(false, "Not able to click on contact tab so cannot check watermarking on contact page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc014_1_verifyWaterMarkingInUploadedFilesOnHomepage() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOntc="M13tc007_2_uploadDocumentInFWR";
		String dependOntc1="M13tc009_onlineImportDocumentInFWR";
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileShared);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		switchToFrame(driver, 20,fp.getFrame( PageName.HomePage, 10));
		List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
				appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in home alert grid");
			}else {
				appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in home alert grid");
				sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in home alert grid");
			}
			if(aa.size()==6) {
				appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in home alert grid");
			}else {
				appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in  home alert grid");
				sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in home alert grid");
			}
		}else {
			appLog.error("Watermarking is not verified in file: "+UploadedFileInStd+" in home alert grid");
			sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInStd+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, null);
		if(!aa.isEmpty()) {
			for(int i=0; i<aa.size(); i++) {
				appLog.error(aa.get(i));
				sa.assertTrue(false, aa.get(i));
			}
		}else {
			appLog.info("Watermarking labels are verified in  import file : "+importfileInstd+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
				appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in home alert grid");
			}else {
				appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in home alert grid");
				sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in home alert grid");
			}
			if(aa.size()==6) {
				appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in home alert grid");
			}else {
				appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in home alert grid");
				sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in home alert grid");
			}
		}else {
			appLog.error("Watermarking is not verified in file: "+UploadedFileInCommon+" in home alert grid");
			sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInCommon+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
				appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in home alert grid");
			}else {
				appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in home alert grid");
				sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in home alert grid");
			}
			if(aa.size()==1) {
				appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in home alert grid");
			}else {
				appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in home alert grid");
				sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in home alert grid");
			}
		}else {
			appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in home alert grid");
			sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage,null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
				appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in home alert grid");
			}else {
				appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in home alert grid");
				sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in home alert grid");
			}
			if(aa.size()==6) {
				appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in home alert grid");
			}else {
				appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in home alert grid");
				sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in home alert grid");
			}
		}else {
			appLog.error("Watermarking is not verified in file: "+UploadedFileInShared+" in home alert grid");
			sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInShared+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage,null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
				appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in home alert grid");
			}else {
				appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in home alert grid");
				sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in home alert grid");
			}
			if(aa.size()==1) {
				appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in home alert grid");
			}else {
				appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in home alert grid");
				sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in home alert grid");
			}
		}else {
			appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in home alert grid");
			sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in home alert grid");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc014_2_verifyWaterMarkingInUploadedFilesOnFundPageAlert() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOntc="M13tc007_2_uploadDocumentInFWR";
		String dependOntc1="M13tc009_onlineImportDocumentInFWR";
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc014_1_verifyWaterMarkingInUploadedFilesOnHomepage", excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileShared);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 20,fp.getFrame( PageName.FundsPage, 10));
				if(click(driver, fp.getAlertHistoryLink(Workspace.FundraisingWorkspace,PageName.FundsPage, 10), "alert histroy link", action.SCROLLANDBOOLEAN)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in home alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in home alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in home alert grid");
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in home alert grid");
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in  home alert grid");
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in home alert grid");
						}
					}else {
						appLog.error("Watermarking is not verified in file: "+UploadedFileInStd+" in home alert grid");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInStd+" in home alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						for(int i=0; i<aa.size(); i++) {
							appLog.error(aa.get(i));
							sa.assertTrue(false, aa.get(i));
						}
					}else {
						appLog.info("Watermarking labels are verified in  import file : "+importfileInstd+" in home alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in home alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in home alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in home alert grid");
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in fund page alert grid");
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in fund page alert grid");
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in fund page alert grid");
						}
					}else {
						appLog.error("Watermarking is not verified in file: "+UploadedFileInCommon+" in fund page alert grid");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInCommon+" in fund page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in fund page alert grid");
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in fund page alert grid");
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in fund page alert grid");
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in fund page alert grid");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in fund page alert grid");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in fund page alert grid");
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in fund page alert grid");
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in fund page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in fund page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in fund page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in fund page alert grid");
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in fund page alert grid");
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in fund page alert grid");
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in fund page alert grid");
						}
					}else {
						appLog.error("Watermarking is not verified in file: "+UploadedFileInShared+" in fund page alert grid");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInShared+" in fund page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in fund page alert grid");
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in fund page alert grid");
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in fund page alert grid");
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in fund page alert grid");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in fund page alert grid");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in fund page alert grid");
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in fund page alert grid");
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in fund page alert grid");
					}
					
				}else {
					appLog.error("Not able to click on fund page fundraising workspace alert history link so cannot check watermarking");
					sa.assertTrue(false, "Not able to click on fund page fundraising workspace alert history link so cannot check watermarking");
				}
				
			}else {
				appLog.error("Not able to click on created fund :"+M13FundName1+" so cannot watermrking on fund page history pop up");
				sa.assertTrue(false, "Not able to click on created fund :"+M13FundName1+" so cannot watermrking on fund page history pop up");
			}
		}else {
			appLog.error("Not able to click fund tab so cannot check watermarking in fund page alert history pop up");
			sa.assertTrue(false, "Not able to click fund tab so cannot check watermarking in fund page alert history pop up");
			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc014_3_verifyWaterMarkingInUploadedFilesOnContactPageAlert() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		String dependOntc="M13tc007_2_uploadDocumentInFWR";
		String dependOntc1="M13tc009_onlineImportDocumentInFWR";
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc014_1_verifyWaterMarkingInUploadedFilesOnHomepage", excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileShared);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(contact.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(M13Contact1FirstName, M13Contact1LastName, null)) {
				switchToFrame(driver, 20,fp.getFrame( PageName.ContactsPage, 10));
				if(click(driver, fp.getAlertHistoryLink(Workspace.FundraisingWorkspace,PageName.ContactsPage, 10), "alert histroy link", action.SCROLLANDBOOLEAN)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in contact page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in contact page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in contact page alert grid");
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in contact page alert grid");
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in  contact page alert grid");
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in contact page alert grid");
						}
					}else {
						appLog.error("Watermarking is not verified in file: "+UploadedFileInStd+" in contact page alert grid");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInStd+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						for(int i=0; i<aa.size(); i++) {
							appLog.error(aa.get(i));
							sa.assertTrue(false, aa.get(i));
						}
					}else {
						appLog.info("Watermarking labels are verified in  import file : "+importfileInstd+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in contact page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in contact page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in contact page alert grid");
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in contact page alert grid");
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in contact page alert grid");
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in contact page alert grid");
						}
					}else {
						appLog.error("Watermarking is not verified in file: "+UploadedFileInCommon+" in contact page alert grid");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInCommon+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in contact page alert grid");
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in contact page alert grid");
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in contact page alert grid");
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in contact page alert grid");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in contact page alert grid");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in contact page alert grid");
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in contact page alert grid");
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in contact page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in contact page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in contact page alert grid");
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in contact page alert grid");
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in contact page alert grid");
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in contact page alert grid");
						}
					}else {
						appLog.error("Watermarking is not verified in file: "+UploadedFileInShared+" in contact page alert grid");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInShared+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in contact page alert grid");
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in contact page alert grid");
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in contact page alert grid");
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in contact page alert grid");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in contact page alert grid");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in contact page alert grid");
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in contact page alert grid");
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in contact page alert grid");
					}
					
				}else {
					appLog.error("Not able to click on contact page fundraising workspace alert history link so cannot check watermarking");
					sa.assertTrue(false, "Not able to click on contact page fundraising workspace alert history link so cannot check watermarking");
				}
				
			}else {
				appLog.error("Not able to click on created contact :"+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot watermrking on contact page history pop up");
				sa.assertTrue(false, "Not able to click on created contact :"+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot watermrking on contact page history pop up");
			}
		}else {
			appLog.error("Not able to click Contact tab so cannot check watermarking in contact page alert history pop up");
			sa.assertTrue(false, "Not able to click contact tab so cannot check watermarking in contact page alert history pop up");
			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc015_1_updateMyFirmProfile() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if(bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30,bp.getFrame( PageName.NavatarInvestorManager, 30));
			if(nim.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
				if(nim.clickOnEditIcon()) {
					if(sendKeys(driver,nim.getMyFirmProfileNameTextBox(60), Org1FirmName+"Updated1", "my firm profile name", action.SCROLLANDBOOLEAN)) {
						ExcelUtils.writeData(Org1FirmName+"Updated1", "Users",excelLabel.Variable_Name,"AdminUser", excelLabel.Updated_FirmName);
						ExcelUtils.writeData(Org1FirmName+"Updated1", "Users",excelLabel.Variable_Name,"User1", excelLabel.Updated_FirmName);
						appLog.info("Passed value in firm name text box: "+Org1FirmName+"Updated1");
						if(click(driver, nim.getMyFirmProfilesaveBtn(30), "my firm profile save button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on save button");
						}else {
							appLog.error("Not able to click on my firm firm  profile save button so cannot update my  firm profile");
							sa.assertTrue(false, "Not able to click on my firm firm  profile save button so cannot update my  firm profile");
						}
					}else {
						appLog.error("Not able to pass value in my firm profile text box :"+Org1FirmName+"Update1 so cannot update my firm profile");
						sa.assertTrue(false, "Not able to pass value in my firm profile text box :"+Org1FirmName+"Update1 so cannot update my firm profile");
					}
				}else {
					appLog.error("Not able to click on my firm profile edit icon so  cannot update my firm profile");
					sa.assertTrue(false, "Not able to click on my firm profile edit icon so  cannot update my firm profile");
				}
			}else {
				appLog.error("Not able to click on my firm profile tab so cannot update my firm profile");
				sa.assertTrue(false, "Not able to click on my firm profile tab so cannot update my firm profile");
			}
		}else {
			appLog.error("Not able to click on nim tab so cannot update my firm profile");
			sa.assertTrue(false, "Not able to click on nim tab so cannot update my firm profile");
		}
		switchToDefaultContent(driver);
		
	}
	
	@Test
	public void M13tc015_2_updateInvestorNameAndFundName() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 20,fp.getFrame( PageName.FundsPage, 10));
				if(fp.updateInvestorOrLPNameFromManageInvestor(Workspace.FundraisingWorkspace, M13Institution1, null, M13Institution1+"UP", "M13Instituition1")) {
					appLog.info("investor name is updated successfully: "+M13Institution1+"UP");
				}else {
					appLog.error("Not able to update investor name form manage investor");
					sa.assertTrue(false, "Not able to update investor name form manage investor");
				}
			}else {
				appLog.error("Not able to click on created fund :"+M13FundName1+" so cannot update institution Name from manage investor");
				sa.assertTrue(false, "Not able to click on created fund :"+M13FundName1+" so cannot update institution Name from manage investor");
			}
			switchToDefaultContent(driver);
		}else {
			appLog.error("Not able to click fund tab so cannot update institution Name from manage investor");
			sa.assertTrue(false, "Not able to click fund tab so cannot update institution Name from manage investor");
		}
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 20,fp.getFrame( PageName.FundsPage, 10));
				if (click(driver, fp.getInvestmentInfo(Workspace.FundraisingWorkspace), "Investment info",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getInvestmentInfoEdit(60), "investment info edit button",
							action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, fp.getInvestmentInfoFundNameTxtbox(60), M13FundName1+"UP","Fund name text box",
								action.BOOLEAN)) {
							if (click(driver, fp.getInvestmentInfoSaveBtn(40), "Investment Info save button",
									action.SCROLLANDBOOLEAN)) {
								ExcelUtils.writeData(M13FundName1+"UP", "Funds",excelLabel.Variable_Name,"M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
								if (getText(driver, fp.getFirmName(30), "Fund Name", action.SCROLLANDBOOLEAN)
										.equals(M13FundName1+"UP")) {
									appLog.info("Fund Name is successfully updated and verfied.");
									
								} else {
									appLog.error("Fund Name is not Updated.");
									sa.assertTrue(false, "Fund Name is not Updated.");
								}
							} else {
								appLog.error("Save button is not clicked so ,Updated fund name is not able to save.");
								sa.assertTrue(false,
										"Save button is not clicked so ,Updated fund name is not able to save.");
							}
						} else {
							appLog.error(
									"fund name text box is not visible on Investment Info edit mode so, not able to enter value.");
							sa.assertTrue(false,
									"fund name text box is not visible on Investment Info edit mode so, not able to enter value.");
						}
					} else {
						appLog.error("Investment Info edit button is not clickable so, not able to enter value.");
						sa.assertTrue(false,
								"Investment Info edit button is not clickable so, not able to enter value.");
					}
				}else {
					appLog.error("Not able to click on investment update info link so cannot update fund Name from investment update info");
					sa.assertTrue(false, "Not able to click on investment update info link so cannot update fund Name from investment info");
				}
			}else {
				appLog.error("Not able to click on created fund :"+M13FundName1+" so cannot update fund Name from investment update info");
				sa.assertTrue(false, "Not able to click on created fund :"+M13FundName1+" so cannot update fund Name from investment info");
			}
			
		}else {
			appLog.error("Not able to click fund tab so cannot update Fund Name from investment update info");
			sa.assertTrue(false, "Not able to click fund tab so cannot update fund Name from investment update info");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				if(fp.updateCreatedFund(M13FundName1, M13FundName1+"NUP", "M13Fund1")) {
					appLog.info("Fund Name is updated successfully : "+M13FundName1+" to "+M13FundName1+"NUP");
				}else {
					appLog.error("Fund Name is not updated: "+M13FundName1);
					sa.assertTrue(false, "Fund Name is not updated "+M13FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund :"+M13FundName1+" so cannot update fund Name");
				sa.assertTrue(false, "Not able to click on created fund :"+M13FundName1+" so cannot update fund Name");
			}
		}else {
			appLog.error("Not able to click fund tab so cannot update Fund Name");
			sa.assertTrue(false, "Not able to click fund tab so cannot update fund Name");
		}
		if(ins.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedInstitution(M13Institution1)) {
				if(ins.updateCreatedInstitute(M13Institution1, M13Institution1+"NUP", "M13Instituition1")) {
					appLog.info("Institution Name is updated successfully : "+M13Institution1+" to "+M13Institution1+"NUP");
				}else {
					appLog.error("Institution Name is not updated: "+M13FundName1);
					sa.assertTrue(false, "Institution Name is not updated "+M13FundName1);
				}
			}else {
				appLog.error("Not able to click on created Institution :"+M13Institution1+" so cannot update Institution Name");
				sa.assertTrue(false, "Not able to click on created Institution :"+M13Institution1+" so cannot update Institution Name");
			}
		}else {
			appLog.error("Not able to click Institution tab so cannot update Institution Name");
			sa.assertTrue(false, "Not able to click Institution tab so cannot update Institution Name");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc016_1_updateWatermarkingAndVerifyDocumentInFWR() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.Watermarking);
		lp.CRMLogin(CRMUser2EmailID,adminPassword);
		if(nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if(nim.clickOnSideMenusTab(sideMenu.Watermarking)) {
				if(nim.clickOnEditIcon()) {
					List<WebElement> lst = nim.getWaterMarkingCheckBoxList();
					List<WebElement> dropdown=nim.getWatermarkingDropDownList();
					if(!lst.isEmpty()) {
						for(int i=1 ;i<lst.size()-3; i++) {
							if(isSelected(driver, lst.get(i), "check box")) {
								if(click(driver,lst.get(i), "check box", action.SCROLLANDBOOLEAN)) {
									appLog.info("label clicked on check box");
								}else {
									appLog.error("Not able to click on check box so cannot uncheck check box");
									sa.assertTrue(false, "Not able to click on check box so cannot uncheck check box");
								}
							}else {
								appLog.info("label check Box is not checked");
							}
						}
					}else {
						appLog.error("watermarking check box list is not visible so cannot uncheck it");
						sa.assertTrue(false, "watermarking check box list is not visible so cannot uncheck it");
					}
					if(!dropdown.isEmpty()) {
						for(int j1=1 ;j1<dropdown.size(); j1++) {
							if(getSelectedOptionOfDropDown(driver, dropdown.get(j1), "drop down", "text").trim()!="None") {
								if(selectVisibleTextFromDropDown(driver,dropdown.get(j1), "drop down","None")) {
									appLog.info("Select None value from drop down list");
								}else {
									appLog.error("Not able to select None value from drop down list");
									sa.assertTrue(false, "Not able to select None value from drop down list");
								}
							}else {
								appLog.info("None is alreadyed selected in drop down list");
							}
						}
					}else {
						appLog.error("watermarking drop dwon list is not visible so cannot reset drop down");
						sa.assertTrue(false, "watermarking drop dwon list is not visible so cannot reset drop down");
					}
					lst =nim.getWaterMarkingCustomFieldCrossIconList();
					if(!lst.isEmpty()) {
						for(int i=0 ;i<lst.size(); i++) {
							if(click(driver,lst.get(i), "check box", action.SCROLLANDBOOLEAN)) {
								appLog.info("label clicked on check box");
							}else {
								appLog.error("Not able to click on check box so cannot uncheck check box");
								sa.assertTrue(false, "Not able to click on check box so cannot uncheck check box");
							}
						}
					}else {
						appLog.error("custom watermarking check box list is not visible so cannot uncheck it");
						sa.assertTrue(false, "custom watermarking check box list is not visible so cannot uncheck it");
					}
					lst.clear();
					lst = nim.getWatermarkingCustomTextBoxList();
					if(!lst.isEmpty()) {
						for(int i=0 ;i<lst.size(); i++) {
							if(sendKeys(driver, lst.get(i), "", "custom text box", action.SCROLLANDBOOLEAN)) {
								appLog.info("custom label text is cleared");
							}else {
								appLog.error("Not able to clear custom text box");
								sa.assertTrue(false, "Not able to clear custom text box");
							}
						}
					}else {
						appLog.error("custom watermarking check box list is not visible so cannot uncheck it");
						sa.assertTrue(false, "custom watermarking check box list is not visible so cannot uncheck it");
					}
					if(click(driver,nim.getCustomLabelCheckBox(10), "custom check box", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on custom check box");
						if(click(driver, nim.getWatermarkingSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)){
							appLog.info("clicked on save button");
							if(nim.activateWatermarking2(WatermarkingLabels).isEmpty()) {
								appLog.info("watermarking setting is updated successfully");
							}else {
								appLog.error("Not able to update watermarking setting");
								sa.assertTrue(false, "Not able to update watermarking setting");
							}
						}else {
							appLog.error("Not able to click on watermarking save button so cannot save it");
							sa.assertTrue(false, "Not able to click on watermarking save button so cannot save it");
						}
					}else {
						appLog.error("Not able to click on watermarking custom check check box so cannot save it");
						sa.assertTrue(false, "Not able to click on watermarking custom check check box so cannot save it");
					}
				}else {
					appLog.error("Not able to click on watermarking side menu so cannot activate watermarking settings");
					sa.assertTrue(false, "Not able to click on watermarking side menu so cannot activate watermarking settings");
				}
			}else {
				appLog.error("Not able to click on watermarking tab so cannot update watermarking");
				sa.assertTrue(false, "Not able to click on watermarking tab so cannot update watermarking");
			}
		}else {
			appLog.error("Not able to click on NIM tab so cannot update watermarking");
			sa.assertTrue(false, "Not able to click on NIM tab so cannot update watermarking");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc016_2_verifyUpdatedWaterMarkingInUploadedFilesOnHomepage() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOntc="M13tc007_2_uploadDocumentInFWR";
		String dependOntc1="M13tc009_onlineImportDocumentInFWR";
		M13Institution1=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		M13FundName1=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M13FundRaising1", excelLabel.Fund_Name);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileShared);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		switchToFrame(driver, 20,fp.getFrame( PageName.HomePage, 10));
		List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, null);
		String NOtAvailableWaterMarkingLabels= "My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3<break>IP Address";
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
				appLog.info("My Firm Name,Investor Name, Fund Name,IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in home alert grid");
			}else {
				appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in home alert grid");
				sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in home alert grid");
			}
			if(aa.size()==7) {
				appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInStd+" in home alert grid");
			}else {
				appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in  home alert grid");
				sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in home alert grid");
			}
		}else {
			appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInStd+" in home alert grid");
			sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInStd+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion("IP Address", aa)) {
				appLog.info("IP Address watermarking labels are not available on file: "+UploadedFileInStd+" in home alert grid");
			}else {
				appLog.error("IP Address watermarking labels are available on file: "+UploadedFileInStd+" in home alert grid");
				sa.assertTrue(false, "IP Address watermarking labels are available on file: "+UploadedFileInStd+" in home alert grid");
			}
		}else {
			appLog.error("IP Address watermarking is available in file: "+UploadedFileInStd+" in home alert grid");
			sa.assertTrue(false, "IP Address watermarking is available in file: "+UploadedFileInStd+" in home alert grid");
		}
			if(aa.size()==1) {
				appLog.info("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is available on file: "+UploadedFileInStd+" in home alert grid");
			}else {
				appLog.error("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in  home alert grid");
				sa.assertTrue(false, "My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in home alert grid");
			}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
				appLog.info("My Firm Name,Investor Name, Fund Name, IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in home alert grid");
			}else {
				appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in home alert grid");
				sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in home alert grid");
			}
			if(aa.size()==7) {
				appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInCommon+" in home alert grid");
			}else {
				appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in home alert grid");
				sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in home alert grid");
			}
		}else {
			appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in home alert grid");
			sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, null);
		String NOtAvailableLablesInCommon="Investor Name<break>IP Address";
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
				appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInCommon+" in home alert grid");
			}else {
				appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in home alert grid");
				sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in home alert grid");
			}
			if(aa.size()==2) {
				appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInCommon+" in home alert grid");
			}else {
				appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in home alert grid");
				sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in home alert grid");
			}
		}else {
			appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in home alert grid");
			sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage,null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
				appLog.info("My Firm Name,Investor Name, Fund Name,IP Address,Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in home alert grid");
			}else {
				appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in home alert grid");
				sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in home alert grid");
			}
			if(aa.size()==7) {
				appLog.info("Download Date,UserEmailId is available on file: "+UploadedFileInShared+" in home alert grid");
			}else {
				appLog.error("Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in home alert grid");
				sa.assertTrue(false, "Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in home alert grid");
			}
		}else {
			appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInShared+" in home alert grid");
			sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInShared+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage,null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
				appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInShared+" in home alert grid");
			}else {
				appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in home alert grid");
				sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in home alert grid");
			}
			if(aa.size()==2) {
				appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInShared+" in home alert grid");
			}else {
				appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in home alert grid");
				sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in home alert grid");
			}
		}else {
			appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in home alert grid");
			sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in home alert grid");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc016_3_verifyUpdatedWaterMarkingInDocumentOnFundPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String dependOnTC="M13tc007_2_uploadDocumentInFWR";
		String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.Fund_Name);
		String UpdatedM13Institution1=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M13Instituition1", excelLabel.UpdateInstitution_NameFormManageInvestor);
		M13Institution1=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		M13FundName1=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M13FundRaising1", excelLabel.Fund_Name);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.Watermarking);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTC, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTC, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTC, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileShared);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(UpdatedM13FundName1)) {
				String NOtAvailableWaterMarkingLabels= "My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3<break>IP Address";
				String NOtAvailableLablesInCommon="Investor Name<break>IP Address";
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, UpdatedM13Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder : "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder:"+stdfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInStd+" in folder : "+stdfolderpath);
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address", aa)) {
							appLog.info("IP Address watermarking labels are not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("IP Address watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "IP Address watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
					}else {
						appLog.error("IP Address watermarking is available in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						sa.assertTrue(false, "IP Address watermarking is available in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
					}
						if(aa.size()==1) {
							appLog.info("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
				}else {
					appLog.error("Not able to click on institution Name ::"+UpdatedM13Institution1+" so cannot check watermarking on fundpage");
					sa.assertTrue(false, "Not able to click on institution Name ::"+UpdatedM13Institution1+" so cannot check watermarking on fundpage");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage,Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder: "+Commonfolderpath);
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder: "+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check watermarking on fundpage");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check watermarking on fundpage");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage,Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address,Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date,UserEmailId is available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in home alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage,Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInShared+" in folder:"+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder: "+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder: "+Sharedfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check updated watermarking on fundpage");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check updated watermarking on fundpage");
				}
			}else {
				appLog.error("Not able to click on created fund "+M13FundName1+" so cannot verify updated watermarking on fundpage");
				sa.assertTrue(false, "Not able to click on created fund "+M13FundName1+" so cannot verify updated watermarking on fundpage");
			}
		}else {
			appLog.error("Not able to click on Fund tab so cannot verify updated watermarking on fundpage");
			sa.assertTrue(false, "Not able to click on Fund tab so cannot verify updated watermarking on fundpage");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc016_4_verifyUpdatedWaterMarkingInUploadedFilesOnFundPageAlert() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOntc="M13tc007_2_uploadDocumentInFWR";
		String dependOntc1="M13tc009_onlineImportDocumentInFWR";
		M13Institution1=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		M13FundName1=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M13FundRaising1", excelLabel.Fund_Name);
		String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.Fund_Name);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc014_1_verifyWaterMarkingInUploadedFilesOnHomepage", excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileShared);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(UpdatedM13FundName1)) {
				switchToFrame(driver, 20,fp.getFrame( PageName.FundsPage, 10));
				if(click(driver, fp.getAlertHistoryLink(Workspace.FundraisingWorkspace,PageName.FundsPage, 10), "alert histroy link", action.SCROLLANDBOOLEAN)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, null);
					String NOtAvailableWaterMarkingLabels= "My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3<break>IP Address";
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in fund page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in fund page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in fund page alert grid");
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInStd+" in fund page alert grid");
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in fund page alert grid");
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in fund page alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInStd+" in fund page alert grid");
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInStd+" in fund page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address", aa)) {
							appLog.info("IP Address watermarking labels are not available on file: "+UploadedFileInStd+" in fund page alert grid");
						}else {
							appLog.error("IP Address watermarking labels are available on file: "+UploadedFileInStd+" in fund page alert grid");
							sa.assertTrue(false, "IP Address watermarking labels are available on file: "+UploadedFileInStd+" in fund page alert grid");
						}
					}else {
						appLog.error("IP Address watermarking is available in file: "+UploadedFileInStd+" in fund page alert grid");
						sa.assertTrue(false, "IP Address watermarking is available in file: "+UploadedFileInStd+" in fund page alert grid");
					}
						if(aa.size()==1) {
							appLog.info("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is available on file: "+UploadedFileInStd+" in fund page alert grid");
						}else {
							appLog.error("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in fund page alert grid");
							sa.assertTrue(false, "My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in fund page alert grid");
						}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in fund page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in fund page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in fund page alert grid");
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInCommon+" in fund page alert grid");
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in fund page alert grid");
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in fund page alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in fund page alert grid");
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in fund page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, null);
					String NOtAvailableLablesInCommon="Investor Name<break>IP Address";
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInCommon+" in fund page alert grid");
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in fund page alert grid");
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in fund page alert grid");
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInCommon+" in fund page alert grid");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in fund page alert grid");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in fund page alert grid");
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in fund page alert grid");
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in fund page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address,Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in fund page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in fund page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in fund page alert grid");
						}
						if(aa.size()==7) {
							appLog.info("Download Date,UserEmailId is available on file: "+UploadedFileInShared+" in fund page alert grid");
						}else {
							appLog.error("Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in fund page alert grid");
							sa.assertTrue(false, "Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in fund page alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInShared+" in fund page alert grid");
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInShared+" in fund page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInShared+" in fund page alert grid");
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in fund page alert grid");
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in fund page alert grid");
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInShared+" in fund page alert grid");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in fund page alert grid");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in fund page alert grid");
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in fund page alert grid");
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in fund page alert grid");
					}
					
				}else {
					appLog.error("Not able to click on fund page fundraising workspace alert history link so cannot check updated watermarking");
					sa.assertTrue(false, "Not able to click on fund page fundraising workspace alert history link so cannot check updated watermarking");
				}
				
			}else {
				appLog.error("Not able to click on created fund :"+UpdatedM13FundName1+" so cannot watermrking on fund page history pop up");
				sa.assertTrue(false, "Not able to click on created fund :"+UpdatedM13FundName1+" so cannot watermrking on fund page history pop up");
			}
		}else {
			appLog.error("Not able to click fund tab so cannot check watermarking in fund page alert history pop up");
			sa.assertTrue(false, "Not able to click fund tab so cannot check watermarking in fund page alert history pop up");
			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc016_5_verifyUpdatedWaterMarkingInUploadedFilesOnInstitutionPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String UpdatedM13Institution1=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M13Instituition1", excelLabel.UpdateInstitution_NameFormManageInvestor);
		String oldM13Institution1=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		M13FundName1=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M13FundRaising1", excelLabel.Fund_Name);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.Watermarking);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.SharedPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.CommonPath);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileShared);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedInstitution(M13Institution1)) {
				String NOtAvailableWaterMarkingLabels= "My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3<break>IP Address";
				String NOtAvailableLablesInCommon="Investor Name<break>IP Address";
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, null, null, UpdatedM13FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder : "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder:"+stdfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInStd+" in folder : "+stdfolderpath);
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address", aa)) {
							appLog.info("IP Address watermarking labels are not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("IP Address watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "IP Address watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
					}else {
						appLog.error("IP Address watermarking is available in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						sa.assertTrue(false, "IP Address watermarking is available in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
					}
						if(aa.size()==1) {
							appLog.info("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
				}else {
					appLog.error("Not able to click on folder ::"+stdfolderpath+" so cannot check updated watermarking on Institution page");
					sa.assertTrue(false, "Not able to click on folder ::"+stdfolderpath+" so cannot check updated watermarking on Institution page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, UpdatedM13FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage,Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder: "+Commonfolderpath);
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder: "+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check updated watermarking on Institution Page");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check updated watermarking on Institution Page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, UpdatedM13FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage,Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address,Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date,UserEmailId is available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in home alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage,Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInShared+" in folder:"+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder: "+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder: "+Sharedfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check watermarking on Institution Page");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check watermarking on Institution Page");
				}
			}else {
				appLog.error("Not able to click on created Institution "+UpdatedM13Institution1+" so cannot verify updated watermarking");
				sa.assertTrue(false, "Not able to click on created Institution "+UpdatedM13Institution1+" so cannot verify updated watermarking");
			}
		}else {
			appLog.error("Not able to click on Institution tab so cannot verify watermarking on Institution page");
			sa.assertTrue(false, "Not able to click on Institution tab so cannot verify watermarking on Institution page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M13tc016_6_verifyUpdatedWaterMarkingInUploadedFilesOnContactPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String UpdatedM13Institution1=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M13Instituition1", excelLabel.UpdateInstitution_NameFormManageInvestor);
		String oldM13Institution1=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		M13FundName1=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M13FundRaising1", excelLabel.Fund_Name);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.Watermarking);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.SharedPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.CommonPath);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc010_1_verifyDocumentOnFundPage", excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc009_onlineImportDocumentInFWR", excelLabel.UploadedFileShared);
		if(fp.clickOnTab(TabName.ContactTab)) {
			String NOtAvailableWaterMarkingLabels= "My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3<break>IP Address";
			String NOtAvailableLablesInCommon="Investor Name<break>IP Address";
			if(contact.clickOnCreatedContact(M13Contact1FirstName, M13Contact1LastName, null)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, UpdatedM13Institution1, null, UpdatedM13FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder : "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder:"+stdfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInStd+" in folder : "+stdfolderpath);
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address", aa)) {
							appLog.info("IP Address watermarking labels are not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("IP Address watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "IP Address watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
					}else {
						appLog.error("IP Address watermarking is available in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						sa.assertTrue(false, "IP Address watermarking is available in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
					}
						if(aa.size()==1) {
							appLog.info("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
				}else {
					appLog.error("Not able to click on folder ::"+stdfolderpath+" so cannot check updated watermarking on Contact page");
					sa.assertTrue(false, "Not able to click on folder ::"+stdfolderpath+" so cannot check updated watermarking on Contact page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, UpdatedM13FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage,Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder: "+Commonfolderpath);
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder: "+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check updated watermarking on Contact Page");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check updated watermarking on Contact Page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, UpdatedM13FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage,Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address,Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date,UserEmailId is available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in home alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage,Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInShared+" in folder:"+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder: "+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder: "+Sharedfolderpath);
					}

				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check watermarking on Contact Page");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check watermarking on Contact Page");
				}
			}else {
				appLog.error("Not able to click on created Contact "+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot verify updated watermarking");
				sa.assertTrue(false, "Not able to click on created Contact "+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot verify updated watermarking");
			}
		}else {
			appLog.error("Not able to click on Contact tab so cannot verify watermarking on Contact page");
			sa.assertTrue(false, "Not able to click on Contact tab so cannot verify watermarking on Contact page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc016_7_verifyUpdatedWaterMarkingInUploadedFilesOnContactAlertPopUp() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		String dependOntc="M13tc007_2_uploadDocumentInFWR";
		String dependOntc1="M13tc009_onlineImportDocumentInFWR";
		String oldM13Institution1=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		M13FundName1=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M13FundRaising1", excelLabel.Fund_Name);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M13tc014_1_verifyWaterMarkingInUploadedFilesOnHomepage", excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc1, excelLabel.UploadedFileShared);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(contact.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(M13Contact1FirstName, M13Contact1LastName, null)) {
				switchToFrame(driver, 20,fp.getFrame( PageName.ContactsPage, 10));
				if(click(driver, fp.getAlertHistoryLink(Workspace.FundraisingWorkspace,PageName.ContactsPage, 10), "alert histroy link", action.SCROLLANDBOOLEAN)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					String NOtAvailableWaterMarkingLabels= "My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3<break>IP Address";
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in contact page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in contact page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in contact page alert grid");
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInStd+" in contact page alert grid");
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in contact page alert grid");
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in contact page alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInStd+" in contact page alert grid");
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInStd+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address", aa)) {
							appLog.info("IP Address watermarking labels are not available on file: "+UploadedFileInStd+" in contact page alert grid");
						}else {
							appLog.error("IP Address watermarking labels are available on file: "+UploadedFileInStd+" in contact page alert grid");
							sa.assertTrue(false, "IP Address watermarking labels are available on file: "+UploadedFileInStd+" in contact page alert grid");
						}
					}else {
						appLog.error("IP Address watermarking is available in file: "+UploadedFileInStd+" in contact page alert grid");
						sa.assertTrue(false, "IP Address watermarking is available in file: "+UploadedFileInStd+" in contact page alert grid");
					}
						if(aa.size()==1) {
							appLog.info("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is available on file: "+UploadedFileInStd+" in contact page alert grid");
						}else {
							appLog.error("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in contact page alert grid");
							sa.assertTrue(false, "My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in contact page alert grid");
						}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in contact page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in contact page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in contact page alert grid");
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInCommon+" in contact page alert grid");
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in contact page alert grid");
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in contact page alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in contact page alert grid");
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					String NOtAvailableLablesInCommon="Investor Name<break>IP Address";
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInCommon+" in contact page alert grid");
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in contact page alert grid");
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in contact page alert grid");
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInCommon+" in contact page alert grid");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in contact page alert grid");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in contact page alert grid");
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in contact page alert grid");
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address,Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in contact page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in contact page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in contact page alert grid");
						}
						if(aa.size()==7) {
							appLog.info("Download Date,UserEmailId is available on file: "+UploadedFileInShared+" in contact page alert grid");
						}else {
							appLog.error("Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in contact page alert grid");
							sa.assertTrue(false, "Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in contact page alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInShared+" in contact page alert grid");
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInShared+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,M13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInShared+" in contact page alert grid");
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in contact page alert grid");
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in contact page alert grid");
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInShared+" in contact page alert grid");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in contact page alert grid");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in contact page alert grid");
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in contact page alert grid");
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in contact page alert grid");
					}
					
				}else {
					appLog.error("Not able to click on contact page fundraising workspace alert history link so cannot check watermarking");
					sa.assertTrue(false, "Not able to click on contact page fundraising workspace alert history link so cannot check watermarking");
				}
				
			}else {
				appLog.error("Not able to click on created contact :"+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot watermrking on contact page history pop up");
				sa.assertTrue(false, "Not able to click on created contact :"+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot watermrking on contact page history pop up");
			}
		}else {
			appLog.error("Not able to click Contact tab so cannot check watermarking in contact page alert history pop up");
			sa.assertTrue(false, "Not able to click contact tab so cannot check watermarking in contact page alert history pop up");
			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc017_1_updateOnlineImportDocumentInFWR() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String UpdatedM13Institution1=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M13Instituition1", excelLabel.UpdateInstitution_NameFormManageInvestor);
		String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
		String fileName=ExcelUtils.readData("FilePath",0,8,currentlyExecutingTC);
		String docPath=ExcelUtils.readData("FilePath",0,13,currentlyExecutingTC);
		String Commonfolderpath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
		String CommonfileName=ExcelUtils.readData("FilePath",0,5,currentlyExecutingTC);
		String sharedfolderpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
		String sharedfileName=ExcelUtils.readData("FilePath",0,7,currentlyExecutingTC);
		String Internalfolderpath=ExcelUtils.readData("FilePath",0,2,currentlyExecutingTC);
		String InternalfileName=ExcelUtils.readData("FilePath",0,6,currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				if(fp.onlineImport(environment, mode, UpdatedM13Institution1,null,M13Institution2,folderpath, docPath, fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.MultipleInstitute, WorkSpaceAction.UPDATE, FolderType.Standard,PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
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
						appLog.error("Not able to click on refresh icon so cannot verify updated document: "+fileName);
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated document: "+fileName);
					}
					switchToDefaultContent(driver);
				}else {
					appLog.error("file is not updated: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not updated: "+fileName+" in :"+folderpath);
				}
				
				if(fp.onlineImport(environment, mode, null,null,null,Commonfolderpath, docPath, CommonfileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPDATE, FolderType.Common,PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
					appLog.info("file is imported successfully: "+CommonfileName+" in :"+Commonfolderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,CommonfileName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(CommonfileName+" is verified.");
							}
							
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify updated document: "+CommonfileName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated document: "+CommonfileName);
						}
						switchToDefaultContent(driver);
				}else {
					appLog.error("file is not updated: "+CommonfileName+" in :"+Commonfolderpath);
					sa.assertTrue(false, "file is not updated: "+CommonfileName+" in :"+Commonfolderpath);
				}
				if(fp.onlineImport(environment, mode, null,null,null,sharedfolderpath, docPath, sharedfileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPDATE, FolderType.Shared,PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
					appLog.info("file is imported successfully: "+sharedfileName+" in :"+sharedfolderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,sharedfileName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(sharedfileName+" is verified.");
							}
							
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify updated document: "+sharedfileName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated document: "+sharedfileName);
						}
						switchToDefaultContent(driver);
				}else {
					appLog.error("file is not imported: "+sharedfileName+" in :"+sharedfolderpath);
					sa.assertTrue(false, "file is not imported: "+sharedfileName+" in :"+sharedfolderpath);
				}
				
				if(fp.onlineImport(environment, mode, null,null,null,Internalfolderpath, docPath, InternalfileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPDATE, FolderType.Internal,PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
					appLog.info("file is imported successfully: "+InternalfileName+" in :"+Internalfolderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,InternalfileName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(InternalfileName+" is verified.");
								
							}
							
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify updated document: "+InternalfileName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated document: "+InternalfileName);
						}
						switchToDefaultContent(driver);
				}else {
					appLog.error("file is not updated: "+InternalfileName+" in :"+Internalfolderpath);
					sa.assertTrue(false, "file is not updated: "+InternalfileName+" in :"+Internalfolderpath);
				}
			}else {
				appLog.error("Not able to click created fund "+M13FundName1+" so cannot import file");
				sa.assertTrue(false, "Not able to click created fund "+M13FundName1+" so cannot import file");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot import files");
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc017_2_approveUpdatedDocumentAndVerifyUpdatedDocOnFundPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOntc="M13tc017_1_updateOnlineImportDocumentInFWR";
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.Watermarking);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.SharedPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.CommonPath);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileShared);
		String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String UpdatedM13Institution1=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M13Instituition1", excelLabel.UpdateInstitution_NameFormManageInvestor);
		M13FundName1=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M13FundRaising1", excelLabel.Fund_Name);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				String NOtAvailableLablesInCommon="Investor Name<break>IP Address<break>Label 2";
				if(click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 20), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
					if(fp.selectAllPendingFilesToApprove(WorkSpaceAction.UPDATE).isEmpty()) {
						appLog.info("all pending document is approved successfully from manage approvals");
					}else {
						appLog.error("all pending document is not approved from manage approvals");
						sa.assertTrue(false, "all pending document is not approved from manage approvals");
					}
				}else {
					appLog.error("Not able to click on Manage apporval icon so canot approve updated document");
					sa.assertTrue(false, "Not able to click on Manage apporval icon so canot approve updated document");
				}
				if(fp.verifyFolderPathdummy(stdfolderpath, UpdatedM13Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedM13Institution1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address<break>label 2", aa)) {
							appLog.info("IP Address,Label2 watermarking labels are not available on file: "+importfileInstd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("IP Address,Label2 watermarking labels are available on file: "+importfileInstd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "IP Address,Label2 watermarking labels are available on file: "+importfileInstd+" in folder: "+stdfolderpath);
						}
					}else {
						appLog.error("IP Address,Label2 watermarking is available in file: "+importfileInstd+" in folder: "+stdfolderpath);
						sa.assertTrue(false, "IP Address,Label2 watermarking is available in file: "+importfileInstd+" in folder: "+stdfolderpath);
					}
						if(aa.size()==2) {
							appLog.info("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is available on file: "+importfileInstd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+importfileInstd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+importfileInstd+" in folder: "+stdfolderpath);
						}
				}else {
					appLog.error("Not able to click on institution Name ::"+UpdatedM13Institution1+" so cannot check updated watermarking on fundpage");
					sa.assertTrue(false, "Not able to click on institution Name ::"+UpdatedM13Institution1+" so cannot check updated watermarking on fundpage");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String >aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedM13Institution1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label 2 and IP Address watermarking labels are not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,Updated FundName,EmailAddress,Label1,label3 is available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder: "+Commonfolderpath);
						sa.assertTrue(false, "Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder: "+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check updated watermarking on fundpage");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check updated watermarking on fundpage");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedM13Institution1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage,Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label2 and IP Address watermarking labels are not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("Investor Name/IP Address/Label2 watermarking labels are available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address/Label2 watermarking labels are available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label3 is available on file: "+importfileInShared+" in folder:"+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder: "+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder: "+Sharedfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check updated watermarking on fundpage");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check updated watermarking on fundpage");
				}
			}else {
				appLog.error("Not able to click created fund "+M13FundName1+" so cannot import file");
				sa.assertTrue(false, "Not able to click created fund "+M13FundName1+" so cannot import file");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot import files");
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc017_3_verifyUpdatedWaterMarkingInUploadedFilesOnFundPageAlert() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOntc="M13tc017_1_updateOnlineImportDocumentInFWR";
		String oldM13Institution1=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.Watermarking);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileShared);
		String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String UpdatedM13Institution1=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M13Instituition1", excelLabel.UpdateInstitution_NameFormManageInvestor);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 20,fp.getFrame( PageName.FundsPage, 10));
				String NOtAvailableLablesInCommon="Investor Name<break>IP Address<break>Label 2";
				if(click(driver, fp.getAlertHistoryLink(Workspace.FundraisingWorkspace,PageName.FundsPage, 10), "alert histroy link", action.SCROLLANDBOOLEAN)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+UpdatedM13Institution1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address<break>label 2", aa)) {
							appLog.info("IP Address,Label2 watermarking labels are not available on file: "+importfileInstd+" in fund page alert history");
						}else {
							appLog.error("IP Address,Label2 watermarking labels are available on file: "+importfileInstd+" in fund page alert history");
							sa.assertTrue(false, "IP Address,Label2 watermarking labels are available on file: "+importfileInstd+" in fund page alert history");
						}
					}else {
						appLog.error("IP Address,Label2 watermarking is available in file: "+importfileInstd+" in fund page alert history");
						sa.assertTrue(false, "IP Address,Label2 watermarking is available in file: "+importfileInstd+" in fund page alert history");
					}
						if(aa.size()==2) {
							appLog.info("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is available on file: "+importfileInstd+" in fund page alert history");
						}else {
							appLog.error("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+importfileInstd+" in fund page alert history");
							sa.assertTrue(false, "My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+importfileInstd+" in fund page alert history");
						}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+UpdatedM13Institution1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label 2 and IP Address watermarking labels are not available on file: "+importfileInCommon+" in fund page alert history");
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in fund page alert history");
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in fund page alert history");
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,Updated FundName,EmailAddress,Label1,label3 is available on file: "+importfileInCommon+" in fund page alert history");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInCommon+" in fund page alert history");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInCommon+" in fund page alert history");
						}
					}else {
						appLog.info("Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in fund page alert history");
						sa.assertTrue(false, "Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in fund page alert history");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+UpdatedM13Institution1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp,Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label2 and IP Address watermarking labels are not available on file: "+importfileInShared+" in fund page alert history");
						}else {
							appLog.error("Investor Name/IP Address/Label2 watermarking labels are available on file: "+importfileInShared+" in fund page alert history");
							sa.assertTrue(false, "Investor Name/IP Address/Label2 watermarking labels are available on file: "+importfileInShared+" in fund page alert history");
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label3 is available on file: "+importfileInShared+" in fund page alert history");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInShared+" in fund page alert history");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInShared+" in fund page alert history");
						}
					}else {
						appLog.info("Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+importfileInShared+" in fund page alert history");
						sa.assertTrue(false, "Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+importfileInShared+" in fund page alert history");
					}
					
				}else {
					appLog.error("Not able to click on fund page fundraising workspace alert history link so cannot check updated watermarking");
					sa.assertTrue(false, "Not able to click on fund page fundraising workspace alert history link so cannot check updated watermarking");
				}
				
			}else {
				appLog.error("Not able to click on created fund :"+UpdatedM13FundName1+" so cannot watermrking on fund page history pop up");
				sa.assertTrue(false, "Not able to click on created fund :"+UpdatedM13FundName1+" so cannot watermrking on fund page history pop up");
			}
		}else {
			appLog.error("Not able to click fund tab so cannot check watermarking in fund page alert history pop up");
			sa.assertTrue(false, "Not able to click fund tab so cannot check watermarking in fund page alert history pop up");
			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc017_4_verifyUpdatedWaterMarkingInUploadedFilesOnContactPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String dependOntc="M13tc017_1_updateOnlineImportDocumentInFWR";
		String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String UpdatedM13Institution1=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M13Instituition1", excelLabel.UpdateInstitution_NameFormManageInvestor);
		M13FundName1=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M13FundRaising1", excelLabel.Fund_Name);
		String oldM13Institution1=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.SharedPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.CommonPath);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.Watermarking);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileShared);
		if(fp.clickOnTab(TabName.ContactTab)) {
			String NOtAvailableLablesInCommon="Investor Name<break>IP Address<break>Label 2";
			if(contact.clickOnCreatedContact(M13Contact1FirstName, M13Contact1LastName, null)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, UpdatedM13Institution1, null, UpdatedM13FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedM13Institution1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address<break>label 2", aa)) {
							appLog.info("IP Address, Label2 watermarking labels are not available on file: "+importfileInstd+" in folder :"+stdfolderpath);
						}else {
							appLog.error("IP Address,Label2 watermarking labels are available on file: "+importfileInstd+" in folder :"+stdfolderpath);
							sa.assertTrue(false, "IP Address,Label2 watermarking labels are available on file: "+importfileInstd+" in folder :"+stdfolderpath);
						}
					}else {
						appLog.error("IP Address,label2 watermarking is available in file: "+importfileInstd+" in folder :"+stdfolderpath);
						sa.assertTrue(false, "IP Address,label2 watermarking is available in file: "+importfileInstd+" in folder :"+stdfolderpath);
					}
						if(aa.size()==2) {
							appLog.info("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is available on file: "+importfileInstd+" in folder :"+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+importfileInstd+" in folder :"+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+importfileInstd+" in folder :"+stdfolderpath);
						}
				}else {
					appLog.error("Not able to click on folder ::"+stdfolderpath+" so cannot check updated watermarking on Contact page");
					sa.assertTrue(false, "Not able to click on folder ::"+stdfolderpath+" so cannot check updated watermarking on Contact page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, UpdatedM13FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedM13Institution1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label 2 and IP Address watermarking labels are not available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,Updated FundName,EmailAddress,Label1,label3 is available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder :"+Commonfolderpath);
						sa.assertTrue(false, "Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder :"+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check updated watermarking on Contact Page");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check updated watermarking on Contact Page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, UpdatedM13FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedM13Institution1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage,Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label2 and IP Address watermarking labels are not available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
						}else {
							appLog.error("Investor Name/IP Address/Label2 watermarking labels are available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address/Label2 watermarking labels are available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label3 is available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder :"+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder :"+Sharedfolderpath);
					}
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check watermarking on Contact Page");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check watermarking on Contact Page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(click(driver, fp.getAlertHistoryLink(Workspace.FundraisingWorkspace,PageName.ContactsPage, 10), "alert histroy link", action.SCROLLANDBOOLEAN)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+UpdatedM13Institution1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address<break>label 2", aa)) {
							appLog.info("IP Address,Label2 watermarking labels are not available on file: "+importfileInstd+" in contact alert history");
						}else {
							appLog.error("IP Address,label2 watermarking labels are available on file: "+importfileInstd+" in contact alert history");
							sa.assertTrue(false, "IP Address,label2 watermarking labels are available on file: "+importfileInstd+" in contact alert history");
						}
					}else {
						appLog.error("IP Address,label2 watermarking is available in file: "+importfileInstd+" in contact alert history");
						sa.assertTrue(false, "IP Address,label2 watermarking is available in file: "+importfileInstd+" in contact alert history");
					}
						if(aa.size()==2) {
							appLog.info("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is available on file: "+importfileInstd+" in contact alert history");
						}else {
							appLog.error("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+importfileInstd+" in contact alert history");
							sa.assertTrue(false, "My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+importfileInstd+" in contact alert history");
						}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					 aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+UpdatedM13Institution1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label 2 and IP Address watermarking labels are not available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,Updated FundName,EmailAddress,Label1,label3 is available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder :"+Commonfolderpath);
						sa.assertTrue(false, "Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder :"+Commonfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+UpdatedM13Institution1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label2 and IP Address watermarking labels are not available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
						}else {
							appLog.error("Investor Name/IP Address/Label2 watermarking labels are available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address/Label2 watermarking labels are available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label3 is available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder :"+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder :"+Sharedfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on contact page fundraising workspace alert history link so cannot check updated watermarking");
					sa.assertTrue(false, "Not able to click on contact page fundraising workspace alert history link so cannot check updated watermarking");
				}
			}else {
				appLog.error("Not able to click on created Contact "+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot verify updated watermarking");
				sa.assertTrue(false, "Not able to click on created Contact "+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot verify updated watermarking");
			}
		}else {
			appLog.error("Not able to click on Contact tab so cannot verify watermarking on Contact page");
			sa.assertTrue(false, "Not able to click on Contact tab so cannot verify watermarking on Contact page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc017_5_verifyUpdatedWaterMarkingInUploadedFilesOnInstitutionPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String dependOntc="M13tc017_1_updateOnlineImportDocumentInFWR";
		String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String UpdatedM13Institution1=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M13Instituition1", excelLabel.UpdateInstitution_NameFormManageInvestor);
		M13FundName1=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M13FundRaising1", excelLabel.Fund_Name);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.SharedPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.CommonPath);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.Watermarking);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileShared);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedInstitution(M13Institution1)) {
				String NOtAvailableLablesInCommon="Investor Name<break>IP Address<break>Label 2";
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, null, null, UpdatedM13FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedM13Institution1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address<break>label 2", aa)) {
							appLog.info("IP Address,Label2 watermarking labels are not available on file: "+importfileInstd+" in folder :"+stdfolderpath);
						}else {
							appLog.error("IP Address,Label2 watermarking labels are available on file: "+importfileInstd+" in folder :"+stdfolderpath);
							sa.assertTrue(false, "IP Address,Label2 watermarking labels are available on file: "+importfileInstd+" in folder :"+stdfolderpath);
						}
					}else {
						appLog.error("IP Address,label2 watermarking is available in file: "+importfileInstd+" in folder :"+stdfolderpath);
						sa.assertTrue(false, "IP Address,label2 watermarking is available in file: "+importfileInstd+" in folder :"+stdfolderpath);
					}
						if(aa.size()==2) {
							appLog.info("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is available on file: "+importfileInstd+" in folder :"+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+importfileInstd+" in folder :"+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+importfileInstd+" in folder :"+stdfolderpath);
						}
				}else {
					appLog.error("Not able to click on folder ::"+stdfolderpath+" so cannot check updated watermarking on Institution page");
					sa.assertTrue(false, "Not able to click on folder ::"+stdfolderpath+" so cannot check updated watermarking on Institution page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, UpdatedM13FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedM13Institution1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label 2 and IP Address watermarking labels are not available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,Updated FundName,EmailAddress,Label1,label3 is available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInCommon+" in folder :"+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder :"+Commonfolderpath);
						sa.assertTrue(false, "Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder :"+Commonfolderpath);
					}
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check updated watermarking on Institution Page");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check updated watermarking on Institution Page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, UpdatedM13FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedM13Institution1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage,Workspace.FundraisingWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label2 and IP Address watermarking labels are not available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
						}else {
							appLog.error("Investor Name/IP Address/Label2 watermarking labels are available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address/Label2 watermarking labels are available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label3 is available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInShared+" in folder :"+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder :"+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder :"+Sharedfolderpath);
					}
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check watermarking on Institution Page");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check watermarking on Institution Page");
				}
			}else {
				appLog.error("Not able to click on created Institution "+UpdatedM13Institution1+" so cannot verify updated watermarking");
				sa.assertTrue(false, "Not able to click on created Institution "+UpdatedM13Institution1+" so cannot verify updated watermarking");
			}
		}else {
			appLog.error("Not able to click on Institution tab so cannot verify watermarking on Institution page");
			sa.assertTrue(false, "Not able to click on Institution tab so cannot verify watermarking on Institution page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc017_6_VerifyUpdatedWaterMarkingTargetSide() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		String dependOntc="M13tc017_1_updateOnlineImportDocumentInFWR";
		String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String UpdatedM13Institution1=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M13Instituition1", excelLabel.UpdateInstitution_NameFormManageInvestor);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.Watermarking);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileShared);
		lp.investorLogin(M13Contact1EmailId,adminPassword);
		String NOtAvailableLablesInCommon="Investor Name<break>IP Address<break>Label 2";
		if(click(driver, ivp.getAllDocumentsTab(20), "all document tab", action.BOOLEAN)) {
			List<String> aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedM13Institution1,UpdatedM13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab, null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("IP Address<break>label 2", aa)) {
					appLog.info("IP Address,Label2 watermarking labels are not available on file: "+importfileInstd+" in all document page");
				}else {
					appLog.error("IP Address,Label2 watermarking labels are available on file: "+importfileInstd+" in all document page");
					sa.assertTrue(false, "IP Address,Label2 watermarking labels are available on file: "+importfileInstd+" in all document page");
				}
			}else {
				appLog.error("IP Address,Label2 watermarking is available in file: "+importfileInstd+" in all document page");
				sa.assertTrue(false, "IP Address,Label2 watermarking is available in file: "+importfileInstd+" in all document page");
			}
				if(aa.size()==2) {
					appLog.info("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is available on file: "+importfileInstd+" in all document page");
				}else {
					appLog.error("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+importfileInstd+" in all document page");
					sa.assertTrue(false, "My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+importfileInstd+" in all document page");
				}
			aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedM13Institution1,UpdatedM13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab,null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
					appLog.info("Investor Name,Label 2 and IP Address watermarking labels are not available on file: "+importfileInCommon+" in all document page");
				}else {
					appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in all document page");
					sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in all document page");
				}
				if(aa.size()==3) {
					appLog.info("MyFirmName,Updated FundName,EmailAddress,Label1,label3 is available on file: "+importfileInCommon+" in all document page");
				}else {
					appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInCommon+" in all document page");
					sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInCommon+" in all document page");
				}
			}else {
				appLog.info("Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in all document page");
				sa.assertTrue(false, "Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in all document page");
			}
			aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedM13Institution1,UpdatedM13FundName1,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab,null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
					appLog.info("Investor Name,Label2 and IP Address watermarking labels are not available on file: "+importfileInShared+" in all document page");
				}else {
					appLog.error("Investor Name/IP Address/Label2 watermarking labels are available on file: "+importfileInShared+" in fund page alert history");
					sa.assertTrue(false, "Investor Name/IP Address/Label2 watermarking labels are available on file: "+importfileInShared+" in all document page");
				}
				if(aa.size()==3) {
					appLog.info("MyFirmName,FundName,EmailAddress,Label1,label3 is available on file: "+importfileInShared+" in all document page");
				}else {
					appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInShared+" in all document page");
					sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+importfileInShared+" in all document page");
				}
			}else {
				appLog.info("Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+importfileInShared+" in all document page");
				sa.assertTrue(false, "Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+importfileInShared+" in all document page");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot check updated watermarking in all document");
			sa.assertTrue(false, "Not able to click on all document tab so cannot check updated watermarking in all document");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc018_deactivateWaterMarkingAndManageAppvoralsSettings() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser2EmailID,adminPassword);
		if(nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if(nim.clickOnSideMenusTab(sideMenu.Watermarking)) {
				if(nim.clickOnEditIcon()) {
					if(isSelected(driver, nim.getWatermarkingActivateCheckbox(10), "watermarking activate check box")) {
						List<WebElement> lst =nim.getWaterMarkingCustomFieldCrossIconList();
						if(!lst.isEmpty()) {
							for(int i=0 ;i<lst.size(); i++) {
									if(click(driver,lst.get(i), "check box", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on custom label cross icon");
									}else {
										appLog.error("Not able to click on custom label cross icon so cannot delete custom text box");
										sa.assertTrue(false,"Not able to click on custom label cross icon so cannot delete custom text box");
									}
							}
						}else {
							appLog.error("custom watermarking cross icon list is not visible so cannot delete custom text box");
							sa.assertTrue(false,"custom watermarking cross icon list is not visible so cannot delete custom text box");
						}
						lst.clear();
						lst = nim.getWaterMarkingCheckBoxList();
						List<WebElement> checkBox=nim.getWatermarkingDropDownList();
						if(!lst.isEmpty()) {
							for(int i=1 ;i<lst.size()-1; i++) {
								if(isSelected(driver, lst.get(i), "check box")) {
									if(click(driver,lst.get(i), "check box", action.SCROLLANDBOOLEAN)) {
										appLog.info("label clicked on check box");
									}else {
										appLog.error("Not able to uncheck check box");
										sa.assertTrue(false,"Not able to uncheck check box");
									}
								}else {
									appLog.info("label check Box is already unchecked");
								}
							}	
						}else {
							appLog.error("WaterMarking label check boxes are not available");
							sa.assertTrue(false,"WaterMarking label check boxes are not available");
						}
						if(!checkBox.isEmpty()) {
							for(int i=1 ;i<checkBox.size(); i++) {
								if(getSelectedOptionOfDropDown(driver, checkBox.get(i), "drop down", "text").trim()!="None") {
									if(selectVisibleTextFromDropDown(driver,checkBox.get(i), "drop down","None")) {
										appLog.info("Select None value from drop down list");
									}else {
										appLog.error("Not able to select None value from drop down list");
										sa.assertTrue(false,"Not able to select None value from drop down list");
									}
								}else {
									appLog.info("None is alreadyed selected in drop down list");
								}
							}
						}
						if(click(driver,nim.getCustomLabelCheckBox(10), "custom check box", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on custom check box");
								appLog.info("watermarking setting is deactivated successfully");
								if(click(driver, nim.getWatermarkingSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)){
									appLog.info("clicked on save button");
									
								}else {
									appLog.error("Not able to click on watermarking save button so cannot save it");
									sa.assertTrue(false, "Not able to click on watermarking save button so cannot save it");
								}
						}else {
							appLog.error("Not able to click on watermarking custom check check box so cannot save it");
							sa.assertTrue(false, "Not able to click on watermarking custom check check box so cannot save it");
						}
						if(nim.clickOnEditIcon()) {
							if(click(driver,  nim.getWatermarkingActivateCheckbox(10), "watermarking activate check box", action.SCROLLANDBOOLEAN)) {
								if(click(driver, nim.getWatermarkingSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)){
									appLog.info("clicked on save button");
									appLog.info("Watermarking setting is deactivated successfully and all labels is reset");
									
								}else {
									appLog.error("Not able to click on watermarking save button so cannot deactiave wateramring settings");
									sa.assertTrue(false, "Not able to click on watermarking save button so cannot deactivate watermarking settings");
								}
								
							}else {
								appLog.error("Not able to click on watermarking activate check box so cannot deactivate watermarking settings");
								sa.assertTrue(false, "Not able to click on watermarking activate check box so cannot deactivate watermarking settings");
							}
						}else {
							appLog.error("Not able to click on edit icon so cannot deactive watermarking settings");
							sa.assertTrue(false, "Not able to click on edit icon so cannot deactive watermarking settings");
						}
					}else {
						appLog.info("WaterMarking settings is not activated");
						sa.assertTrue(false, "WaterMarking settings is not activate");
					}
					
				}else {
					appLog.error("Not able to click on edit icon so cannot deactivate watermarking settings.");
					sa.assertTrue(false, "Not able to click on edit icon so cannot deactivate watermarking settings.");
				}
			}else {
				appLog.error("Not able to click on watrermarking tab so cannot deactivate watermarking settings");
				sa.assertTrue(false, "Not able to click on watrermarking tab so cannot deactivate watermarking settings");
			}
			ThreadSleep(3000);
			if(nim.deactivateManageApprovalsSetting()) {
				appLog.info("manage arprovals settings is deactivated successfully");
			}else {
				appLog.error("Not able to deactivate manage approvals settings");
				sa.assertTrue(false, "Not able to deactivate manage approvals settings");
			}
		}else {
			appLog.error("Not able to click on NIM tab so cannot deactivate watermarking and manage approvals settings");
			sa.assertTrue(false, "Not able to click on NIM tab so cannot deactivate watermarking and manage approvals settings");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc019_1_buildINVWorkSpaceandInviteContact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String sharedfolderpath=ExcelUtils.readData("FilePath",0, 3, currentlyExecutingTC);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.Fund_Email);
				String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.Fund_Description);
				String templateName=ExcelUtils.readData("Foldertemp", 0, 1);
				String[] data= {Size,vintageyear,contact,phone,email,description};
				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,M13Institution1+"/"+M13LimitedPartner1+"<break>"+M13Institution2+"/"+M13LimitedPartner2, Workspace.InvestorWorkspace,60)) {
					appLog.info("Investor workspace is build successfully.");
					if(fp.inviteContact(environment,mode,M13Institution1, M13Contact1EmailId,null, FolderType.Standard,"Upload","Yes", null,null, Workspace.InvestorWorkspace, null)) {
						appLog.info("contact is invited from institution in investor workspace successfully: "+M13Contact1FirstName+" "+M13Contact1LastName);
					}else {
						appLog.error("Not able to invite contact from institution from investor workspace: "+M13Contact1FirstName+" "+M13Contact1LastName);
						sa.assertTrue(false, "Not able to invite contact from institution from investor workspace: "+M13Contact1FirstName+" "+M13Contact1LastName);
					}
					if(fp.inviteContact(environment,mode,null , M13Contact1EmailId,sharedfolderpath, FolderType.Shared,"Download","Yes", null,null, Workspace.InvestorWorkspace, null)) {
						appLog.info("contact is invites successfully from shared folder in investor workspace: "+M13Contact1FirstName+" "+M13Contact1LastName);
					}else {
						appLog.error("Not able to invite contact from shared folder in investor workspace: "+M13Contact1FirstName+" "+M13Contact1LastName);
						sa.assertTrue(false, "Not able to invite contact from shared folder in investor workspace: "+M13Contact1FirstName+" "+M13Contact1LastName);
					}
				}else {
					appLog.error("Not able to bulid investor workspace on fund: "+M13FundName1);
					sa.assertTrue(false, "Not able to bulid investor workspace on fund: "+M13FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot build investor workspace: "+M13FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build investor workspace: "+M13FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build investor workspace: "+M13FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build investor workspace: "+M13FundName1);
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M13tc019_2_uploadDocumentInINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
				String INV_docpath="UploadFiles\\Module13\\FileToUploadCRMSide\\UploadFilesCRMSide\\INV\\Standard";
				if(fp.uploadFile(folderpath,M13Institution1+"/"+M13LimitedPartner1+"<break>"+M13Institution2+"/"+M13LimitedPartner2, INV_docpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
						if(filesName!=null) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,filesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
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
					appLog.error("File is not uploaded in "+folderpath);
					sa.assertTrue(false, "File is not uploaded in "+folderpath);
				}
				String CommonPath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
				String docpath="UploadFiles\\Module13\\FileToUploadCRMSide\\UploadFilesCRMSide\\INV\\Common";
				if(fp.uploadFile(CommonPath,null, docpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
						if(filesName!=null) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,filesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
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
					appLog.error("File is not uploaded in "+CommonPath);
					sa.assertTrue(false, "File is not uploaded in "+CommonPath);
				}
				String SharedFolderpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
				docpath="UploadFiles\\Module13\\FileToUploadCRMSide\\UploadFilesCRMSide\\INV\\Shared";
				if(fp.uploadFile(SharedFolderpath,null, docpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared);
						if(filesName!=null) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,filesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
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
					appLog.error("File is not uploaded in "+SharedFolderpath);
					sa.assertTrue(false, "File is not uploaded in "+SharedFolderpath);
				}
				
				String Internalfolderpath=ExcelUtils.readData("FilePath",0,2,currentlyExecutingTC);
				docpath="UploadFiles\\Module13\\FileToUploadCRMSide\\UploadFilesCRMSide\\INV\\Internal";
				if(fp.uploadFile(Internalfolderpath,null, docpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
						if(filesName!=null) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,filesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
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
					appLog.error("File is not uploaded in "+Internalfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+Internalfolderpath);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M13FundName1+" so cannot upload files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M13FundName1+" so cannot upload files in investor workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc020_activateWaterMarkingAndManageApprovals() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser2EmailID,adminPassword);
		if(nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if(nim.clickOnSideMenusTab(sideMenu.Watermarking)) {
				String WatermarkingLabels=ExcelUtils.readData("FilePath",0,30,currentlyExecutingTC);
				if(nim.activateWatermarking2(WatermarkingLabels).isEmpty()) {
					appLog.info("WaterMarking is activate Successfully");
				}else {
					appLog.error("WaterMarking setting is not activated");
					sa.assertTrue(false, "WaterMarking setting is not activated");
				}
			}else {
				appLog.error("Not able to click on watermarking side menu so cannot activate watermarking settings");
				sa.assertTrue(false, "Not able to click on watermarking side menu so cannot activate watermarking settings");
			}
			if(nim.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
				ThreadSleep(3000);
				if(nim.activateManageApprovalsSettings(CRMUser1FirstName+" "+CRMUser1LastName).isEmpty()) {
					appLog.info("Manage approvals settings is activated successfully and access granted to crm user: "+CRMUser1FirstName+" "+CRMUser1LastName);
				}else {
					appLog.error("Manage approvals settings is not activated and Not able to grant access to crm user: "+CRMUser1FirstName+" "+CRMUser1LastName);
					sa.assertTrue(false, "Manage approvals settings is not activated and Not able to grant access to crm user: "+CRMUser1FirstName+" "+CRMUser1LastName);
				}
			}else {
				appLog.error("Not able to click on watermarking side menu so cannot activate Manage Approvals settings");
				sa.assertTrue(false, "Not able to click on watermarking side menu so cannot activate Manage Approvals settings");
			}
		}else {
			appLog.error("Not able to click on nim tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on nim tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc021_onlineImportDocumentInINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String fileName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String docPath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.OnlineImportPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String CommonfileName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
		String sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String sharedfileName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared);
		String Internalfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		String InternalfileName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				if(fp.onlineImport(environment, mode, M13Institution1,M13LimitedPartner1,M13LimitedPartner2,folderpath, docPath, fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.MultipleInstitute, WorkSpaceAction.UPLOAD, FolderType.Standard,PageName.FundsPage, Workspace.InvestorWorkspace, 20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						List<String>result=compareMultipleListOnBasisOfTitle(driver,fileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
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
					switchToDefaultContent(driver);
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
				if(fp.onlineImport(environment, mode, null,null,null,Commonfolderpath, docPath, CommonfileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Common,PageName.FundsPage, Workspace.InvestorWorkspace, 20)) {
					appLog.info("file is imported successfully: "+CommonfileName+" in :"+Commonfolderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,CommonfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(CommonfileName+" is verified.");
							}
							
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+CommonfileName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+CommonfileName);
						}
						switchToDefaultContent(driver);
				}else {
					appLog.error("file is not imported: "+CommonfileName+" in :"+Commonfolderpath);
					sa.assertTrue(false, "file is not imported: "+CommonfileName+" in :"+Commonfolderpath);
				}
				if(fp.onlineImport(environment, mode, null,null,null,sharedfolderpath, docPath, sharedfileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Shared,PageName.FundsPage, Workspace.InvestorWorkspace, 20)) {
					appLog.info("file is imported successfully: "+sharedfileName+" in :"+sharedfolderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,sharedfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(sharedfileName+" is verified.");
							}
							
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+sharedfileName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+sharedfileName);
						}
						switchToDefaultContent(driver);
				}else {
					appLog.error("file is not imported: "+sharedfileName+" in :"+sharedfolderpath);
					sa.assertTrue(false, "file is not imported: "+sharedfileName+" in :"+sharedfolderpath);
				}
				
				if(fp.onlineImport(environment, mode, null,null,null,Internalfolderpath, docPath, InternalfileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Internal,PageName.FundsPage, Workspace.InvestorWorkspace, 20)) {
					appLog.info("file is imported successfully: "+InternalfileName+" in :"+Internalfolderpath);
						switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,InternalfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(InternalfileName+" is verified.");
								
							}
							
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+InternalfileName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+InternalfileName);
						}
						switchToDefaultContent(driver);
				}else {
					appLog.error("file is not imported: "+InternalfileName+" in :"+Internalfolderpath);
					sa.assertTrue(false, "file is not imported: "+InternalfileName+" in :"+Internalfolderpath);
				}
			}else {
				appLog.error("Not able to click created fund "+M13FundName1+" so cannot import file");
				sa.assertTrue(false, "Not able to click created fund "+M13FundName1+" so cannot import file");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot import files");
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc022_1_verifyDocumentOnFundPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.Watermarking);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String Internalfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInInternal=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileInternal);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		String importfileInInternal=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileInternal);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, M13Institution1, M13LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInStd+" in folder "+stdfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						for(int i=0; i<aa.size(); i++) {
							appLog.error(aa.get(i));
							sa.assertTrue(false, aa.get(i));
						}
					}else {
						appLog.info("Watermarking labels are verified in  import file : "+importfileInstd+" in folder "+stdfolderpath);
					}
				}else {
					appLog.error("Not able to click on institution Name ::"+M13Institution1+" so cannot check watermarking on investor workspace");
					sa.assertTrue(false, "Not able to click on institution Name ::"+M13Institution1+" so cannot check watermarking on investor workspace");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check watermarking on investor workspace");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check watermarking on investor workspace");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check watermarking on investor workspace");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check watermarking on investor workspace");
				}
				
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(Internalfolderpath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInInternal,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Email Address<break>IP Address<break>Download Date<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name,Fund Name,Email Address,Download Date,IP Address,Label1,Label2,Label3 watermarking labels are not available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInInternal,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.InvestorWorkspace);
						if(!aa.isEmpty()) {
							if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Email Address<break>IP Address<break>Download Date<break>Label 1<break>Label 2<break>Label 3", aa)) {
								appLog.info("My Firm Name,Investor Name,Fund Name,Email Address,Download Date,IP Address,Label1,Label2,Label3 watermarking labels are not available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
							}else {
								appLog.error("My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
								sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
							}
						}else {
							appLog.error("Watermarking is not verified on funds page in imported file: "+importfileInInternal+" in folder "+Internalfolderpath);
							sa.assertTrue(false, "Watermarking is not verified on funds page on fund in imported file: "+importfileInInternal+" in folder "+Internalfolderpath);
						}
				}else {
					appLog.error("Not able to click on shared Folder ::"+Internalfolderpath+" so cannot check watermarking on investor workspace");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Internalfolderpath+" so cannot check watermarking on investor workspace");
				}
				
				
			}else {
				appLog.error("Not able to click on created fund "+M13FundName1+" so cannot verify watermarking on investor workspace");
				sa.assertTrue(false, "Not able to click on created fund "+M13FundName1+" so cannot verify watermarking on investor workspace");
			}
		}else {
			appLog.error("Not able to click on Fund tab so cannot verify watermarking on investor workspace");
			sa.assertTrue(false, "Not able to click on Fund tab so cannot verify watermarking on investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc022_2_verifyDocumentOnInstitutionPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String UpdatedM13FundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc2, excelLabel.Watermarking);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.SharedPath);
		String Internalfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.InternalPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.CommonPath);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInInternal=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileInternal);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		String importfileInInternal=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileInternal);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedInstitution(M13Institution1)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, null, M13LimitedPartner1, UpdatedM13FundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+importfileInstd+" in folder "+stdfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+importfileInstd+" in folder "+stdfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						for(int i=0; i<aa.size(); i++) {
							appLog.error(aa.get(i));
							sa.assertTrue(false, aa.get(i));
						}
					}else {
						appLog.info("Watermarking labels are verified in  import file : "+importfileInstd+" in folder "+stdfolderpath);
					}
				}else {
					appLog.error("Not able to click on folder ::"+stdfolderpath+" so cannot check watermarking on Institution page");
					sa.assertTrue(false, "Not able to click on folder ::"+stdfolderpath+" so cannot check watermarking on Institution page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, UpdatedM13FundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check watermarking on Institution Page");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check watermarking on Institution Page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, UpdatedM13FundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check watermarking on Institution Page");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check watermarking on Institution Page");
				}
				
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Internalfolderpath, null, null, UpdatedM13FundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInInternal,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Email Address<break>IP Address<break>Download Date<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name,Fund Name,Email Address,Download Date,IP Address,Label1,Label2,Label3 watermarking labels are not available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInInternal,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.InvestorWorkspace);
						if(!aa.isEmpty()) {
							if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Email Address<break>IP Address<break>Download Date<break>Label 1<break>Label 2<break>Label 3", aa)) {
								appLog.info("My Firm Name,Investor Name,Fund Name,Email Address,Download Date,IP Address,Label1,Label2,Label3 watermarking labels are not available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
							}else {
								appLog.error("My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
								sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
							}
						}else {
							appLog.error("Watermarking is not verified on funds page in imported file: "+importfileInInternal+" in folder "+Internalfolderpath);
							sa.assertTrue(false, "Watermarking is not verified on funds page on fund in imported file: "+importfileInInternal+" in folder "+Internalfolderpath);
						}
				}else {
					appLog.error("Not able to click on internal Folder ::"+Internalfolderpath+" so cannot check watermarking on Institution Page");
					sa.assertTrue(false, "Not able to click on internal folder ::"+Internalfolderpath+" so cannot check watermarking on Institution Page");
				}
				
				
			}else {
				appLog.error("Not able to click on created Institution "+M13Institution1+" so cannot verify watermarking");
				sa.assertTrue(false, "Not able to click on created Institution "+M13Institution1+" so cannot verify watermarking");
			}
		}else {
			appLog.error("Not able to click on Institution tab so cannot verify watermarking on Institution page");
			sa.assertTrue(false, "Not able to click on Institution tab so cannot verify watermarking on Institution page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc022_3_verifyDocumentOnContactPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String UpdatedM13FundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc2, excelLabel.Watermarking);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.SharedPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.CommonPath);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		if(fp.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(M13Contact1FirstName, M13Contact1LastName, null)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, M13Institution1, M13LimitedPartner1, UpdatedM13FundName, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+importfileInstd+" in folder "+stdfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+importfileInstd+" in folder "+stdfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						for(int i=0; i<aa.size(); i++) {
							appLog.error(aa.get(i));
							sa.assertTrue(false, aa.get(i));
						}
					}else {
						appLog.info("Watermarking labels are verified in  import file : "+importfileInstd+" in folder "+stdfolderpath);
					}
				}else {
					appLog.error("Not able to click on folder ::"+stdfolderpath+" so cannot check watermarking on Contact page");
					sa.assertTrue(false, "Not able to click on folder ::"+stdfolderpath+" so cannot check watermarking on Contact page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, UpdatedM13FundName, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check watermarking on Contact Page");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check watermarking on Contact Page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, UpdatedM13FundName, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check watermarking on Contact Page");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check watermarking on Contact Page");
				}
			}else {
				appLog.error("Not able to click on created Contact "+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot verify watermarking");
				sa.assertTrue(false, "Not able to click on created Contact "+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot verify watermarking");
			}
		}else {
			appLog.error("Not able to click on Contact tab so cannot verify watermarking on Contact page");
			sa.assertTrue(false, "Not able to click on Contact tab so cannot verify watermarking on Contact page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc022_4_verifyDocumentOnLimitedPartnerPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String UpdatedM13FundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc2, excelLabel.Watermarking);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.SharedPath);
		String Internalfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.InternalPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.CommonPath);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInInternal=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileInternal);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		String importfileInInternal=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileInternal);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedLP(M13LimitedPartner1)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, null, null, UpdatedM13FundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.LimitedPartnerPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+importfileInstd+" in folder "+stdfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+importfileInstd+" in folder "+stdfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.LimitedPartnerPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						for(int i=0; i<aa.size(); i++) {
							appLog.error(aa.get(i));
							sa.assertTrue(false, aa.get(i));
						}
					}else {
						appLog.info("Watermarking labels are verified in  import file : "+importfileInstd+" in folder "+stdfolderpath);
					}
				}else {
					appLog.error("Not able to click on folder ::"+stdfolderpath+" so cannot check watermarking on LP page");
					sa.assertTrue(false, "Not able to click on folder ::"+stdfolderpath+" so cannot check watermarking on LP page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, UpdatedM13FundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.LimitedPartnerPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.LimitedPartnerPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check watermarking on LP Page");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check watermarking on LP Page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, UpdatedM13FundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.LimitedPartnerPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.LimitedPartnerPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check watermarking on LP Page");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check watermarking on LP Page");
				}
				
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Internalfolderpath, null, null, UpdatedM13FundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInInternal,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.LimitedPartnerPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Email Address<break>IP Address<break>Download Date<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name,Fund Name,Email Address,Download Date,IP Address,Label1,Label2,Label3 watermarking labels are not available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInInternal,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.LimitedPartnerPage, Workspace.InvestorWorkspace);
						if(!aa.isEmpty()) {
							if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Email Address<break>IP Address<break>Download Date<break>Label 1<break>Label 2<break>Label 3", aa)) {
								appLog.info("My Firm Name,Investor Name,Fund Name,Email Address,Download Date,IP Address,Label1,Label2,Label3 watermarking labels are not available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
							}else {
								appLog.error("My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
								sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
							}
						}else {
							appLog.error("Watermarking is not verified on funds page in imported file: "+importfileInInternal+" in folder "+Internalfolderpath);
							sa.assertTrue(false, "Watermarking is not verified on funds page on fund in imported file: "+importfileInInternal+" in folder "+Internalfolderpath);
						}
				}else {
					appLog.error("Not able to click on internal Folder ::"+Internalfolderpath+" so cannot check watermarking on LP Page");
					sa.assertTrue(false, "Not able to click on internal folder ::"+Internalfolderpath+" so cannot check watermarking on LP Page");
				}
			}else {
				appLog.error("Not able to click on created Institution "+M13LimitedPartner1+" so cannot verify watermarking");
				sa.assertTrue(false, "Not able to click on created Institution "+M13LimitedPartner1+" so cannot verify watermarking");
			}
		}else {
			appLog.error("Not able to click on Institution tab so cannot verify watermarking on LP page");
			sa.assertTrue(false, "Not able to click on Institution tab so cannot verify watermarking on LP page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc022_5_verifyDocumentOnCommitmentPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		CommitmentPageBusinessLayer com = new CommitmentPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String UpdatedM13FundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc2, excelLabel.Watermarking);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.SharedPath);
		String Internalfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.InternalPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.CommonPath);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInInternal=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileInternal);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		String importfileInInternal=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileInternal);
		if(com.clickOnTab(TabName.CommitmentsTab)) {
			if(com.clickOnCreatedCommitmentId(M13Commitment1ID)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.CommitmentsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, null, M13LimitedPartner1, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.CommitmentsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+importfileInstd+" in folder "+stdfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+importfileInstd+" in folder "+stdfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.CommitmentsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.CommitmentsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						for(int i=0; i<aa.size(); i++) {
							appLog.error(aa.get(i));
							sa.assertTrue(false, aa.get(i));
						}
					}else {
						appLog.info("Watermarking labels are verified in  import file : "+importfileInstd+" in folder "+stdfolderpath);
					}
				}else {
					appLog.error("Not able to click on folder ::"+stdfolderpath+" so cannot check watermarking on Commitments page");
					sa.assertTrue(false, "Not able to click on folder ::"+stdfolderpath+" so cannot check watermarking on Commitments page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.CommitmentsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, UpdatedM13FundName, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.CommitmentsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInCommon+" in folder "+Commonfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.CommitmentsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.CommitmentsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder "+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in folder "+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check watermarking on CommitmentsPage");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check watermarking on CommitmentsPage");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.CommitmentsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, UpdatedM13FundName, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.CommitmentsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInShared+" in folder "+Sharedfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.CommitmentsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.CommitmentsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder "+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in folder "+Sharedfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check watermarking on CommitmentsPage");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check watermarking on CommitmentsPage");
				}
				
				switchToFrame(driver, 30,fp.getFrame( PageName.CommitmentsPage, 20));
				if(fp.verifyFolderPathdummy(Internalfolderpath, null, null, UpdatedM13FundName, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInInternal,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.CommitmentsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Email Address<break>IP Address<break>Download Date<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name,Fund Name,Email Address,Download Date,IP Address,Label1,Label2,Label3 watermarking labels are not available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInInternal+" in folder "+Internalfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.CommitmentsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInInternal,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.CommitmentsPage, Workspace.InvestorWorkspace);
						if(!aa.isEmpty()) {
							if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Email Address<break>IP Address<break>Download Date<break>Label 1<break>Label 2<break>Label 3", aa)) {
								appLog.info("My Firm Name,Investor Name,Fund Name,Email Address,Download Date,IP Address,Label1,Label2,Label3 watermarking labels are not available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
							}else {
								appLog.error("My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
								sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Email Address/Download Date/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+importfileInInternal+" in folder "+Internalfolderpath);
							}
						}else {
							appLog.error("Watermarking is not verified on funds page in imported file: "+importfileInInternal+" in folder "+Internalfolderpath);
							sa.assertTrue(false, "Watermarking is not verified on funds page on fund in imported file: "+importfileInInternal+" in folder "+Internalfolderpath);
						}
				}else {
					appLog.error("Not able to click on internal Folder ::"+Internalfolderpath+" so cannot check watermarking on CommitmentsPage");
					sa.assertTrue(false, "Not able to click on internal folder ::"+Internalfolderpath+" so cannot check watermarking on CommitmentsPage");
				}
			}else {
				appLog.error("Not able to click on created Commitment "+M13Commitment1+" so cannot verify watermarking");
				sa.assertTrue(false, "Not able to click on created Commitments "+M13Commitment1+" so cannot verify watermarking");
			}
		}else {
			appLog.error("Not able to click on Commitments tab so cannot verify watermarking on Commitments page");
			sa.assertTrue(false, "Not able to click on Commitments tab so cannot verify watermarking on Commitments page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc023_approveAllPendingFileAndverifyWaterMarking() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String UpdatedM13FundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc2, excelLabel.Watermarking);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 20), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
					if(fp.selectAllPendingFilesToApprove(WorkSpaceAction.UPLOAD).isEmpty()) {
						appLog.info("all pending document is approved successfully from manage approvals");
					}else {
						appLog.error("all pending document is not approved from manage approvals");
						sa.assertTrue(false, "all pending document is not approved from manage approvals");
					}
					if(click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 10), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
						if(click(driver, fp.getApprovedDocsTab(60), "approved document tab", action.SCROLLANDBOOLEAN)) {
							ThreadSleep(3000);
							List<String> aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1+"<break>"+M13LimitedPartner2,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ManageApprovalsPopUp, Workspace.InvestorWorkspace);
							if(!aa.isEmpty()) {
								for(int i=0; i<aa.size(); i++) {
									appLog.error(aa.get(i));
									sa.assertTrue(false, aa.get(i));
								}
							}else {
								appLog.info("Watermarking labels are verified in  import file : "+importfileInstd+" in manage approvals approved document tab");
							}
							switchToFrame(driver, 20,fp.getFrame( PageName.FundsPage, 20));
							aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ManageApprovalsPopUp, Workspace.InvestorWorkspace);
							if(!aa.isEmpty()) {
								if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
									appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in manage approvals approved document tab");
								}else {
									appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in manage approvals approved document tab");
									sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in manage approvals approved document tab");
								}
								if(aa.size()==1) {
									appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in manage approvals approved document tab");
								}else {
									appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in manage approvals approved document tab");
									sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in manage approvals approved document tab");
								}
							}else {
								appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in manage approvals approved document tab");
								sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in manage approvals approved document tab");
							}
							switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
							aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ManageApprovalsPopUp, Workspace.InvestorWorkspace);
							if(!aa.isEmpty()) {
								if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
									appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in manage approvals approved document tab");
								}else {
									appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in manage approvals approved document tab");
									sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in manage approvals approved document tab");
								}
								if(aa.size()==1) {
									appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in manage approvals approved document tab");
								}else {
									appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in manage approvals approved document tab");
									sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in manage approvals approved document tab");
								}
							}else {
								appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in manage approvals approved document tab");
								sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in manage approvals approved document tab");
							}
							
						}else {
							appLog.error("Not able to click on approved document tab icon so cannot verify watermarking");
							sa.assertTrue(false, "Not able to click on approved document tab so cannot verify watermarking");
						}
				}else {
					appLog.error("Not able to click on Manage approvals icon so cannot verify watermarking");
					sa.assertTrue(false, "Not able to click on Manage Approvals so cannot verify watermarking");
				}
					
				}else {
					appLog.error("Not able to click on manage approvals icon so cannot approve pending document and verify watermarking ");
					sa.assertTrue(false, "Not able to click on manage approvals icon so cannot approve pending document and verify watermarking ");
				}
				
			}else {
				appLog.error("Not able to click on created fund "+M13FundName1+" so cannot approve pending document and verify watermarking");
				sa.assertTrue(false, "Not able to click on created fund "+M13FundName1+" so cannot approve pending document and verify watermarking");
			}
		}else {
			appLog.error("Not able to click on Fund tab so cannot approve pending document and verify watermarking");
			sa.assertTrue(false, "Not able to click on Fund tab so cannot approve pending document and verify watermarking");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc024_1_VerifyWaterMarkingInAllDocumentTab() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String UpdatedM13FundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc2, excelLabel.Watermarking);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.UploadedFileCommon);
		lp.investorLogin(M13Contact1EmailId,adminPassword);
		if(click(driver, ivp.getAllDocumentsTab(20), "all document tab", action.BOOLEAN)) {
			List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab, null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
					appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available in file: "+UploadedFileInStd+" in all document tab");
				}else {
					appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available in file: "+UploadedFileInStd+" in all document tab");
					sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available in file: "+UploadedFileInStd+" in all document tab");
				}
				if(aa.size()==6) {
					appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in all document tab");
				}else {
					appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in all document tab");
					sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in all document tab");
				}
			}else {
				appLog.error("Watermarking is not verified on all document tab in file: "+UploadedFileInStd);
				sa.assertTrue(false, "Watermarking is not verified on all document tab in file: "+UploadedFileInStd);
			}
			aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab, null);
			if(!aa.isEmpty()) {
				for(int i=0; i<aa.size(); i++) {
					appLog.error(aa.get(i));
					sa.assertTrue(false, aa.get(i));
				}
			}else {
				appLog.info("Watermarking labels are verified in  import file : "+importfileInstd);
			}
			aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab,null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
					appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in all dcoument tab");
				}else {
					appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in all document tab");
					sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in all document tab");
				}
				if(aa.size()==6) {
					appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in all document tab");
				}else {
					appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in all document tab");
					sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in all document tab");
				}
			}else {
				appLog.error("Watermarking is not verified on all document tab in upload file: "+UploadedFileInCommon+" in all document tab");
				sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInCommon+" in all document tab");
			}
			aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab, null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
					appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in all document tab");
				}else {
					appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in all document tab");
					sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in all document tab");
				}
				if(aa.size()==1) {
					appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in all document tab");
				}else {
					appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in all document tab");
					sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in all document tab");
				}
			}else {
				appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in all document tab");
				sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in all document tab");
			}
			aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab,null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
					appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in all document tab");
				}else {
					appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in all document tab");
					sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in all document tab");
				}
				if(aa.size()==6) {
					appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in all document tab");
				}else {
					appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in all document tab");
					sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in all document tab");
				}
			}else {
				appLog.error("Watermarking is not verified on all document tab in upload file: "+UploadedFileInShared+" in all document tab");
				sa.assertTrue(false, "Watermarking is not verified on all document tab in upload file: "+UploadedFileInShared+" in all document tab");
			}
			aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab,null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
					appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in all document tab");
				}else {
					appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in all document tab");
					sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in all document tab");
				}
				if(aa.size()==1) {
					appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in all document tab");
				}else {
					appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in all document tab");
					sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in all document tab");
				}
			}else {
				appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in all document tab");
				sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in all document tab");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot check watermarking in all document tab documents");
			sa.assertTrue(false, "Not able to click on all document tab so cannot check watermarking in all document tab documents");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc024_2_VerifyWaterMarkingInRecentActivitiesTab() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String UpdatedM13FundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc2, excelLabel.Watermarking);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.UploadedFileCommon);
		lp.investorLogin(M13Contact1EmailId,adminPassword);
		if(click(driver, ivp.getRecentActivitiesTab(20), "recent activities tab", action.BOOLEAN)) {
			List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.RecentActivitiesTab, null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
					appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available in file: "+UploadedFileInStd+" in recent activities tab");
				}else {
					appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available in file: "+UploadedFileInStd+" in recent activities tab");
					sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available in file: "+UploadedFileInStd+" in recent activities tab");
				}
				if(aa.size()==6) {
					appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in recent activities tab");
				}else {
					appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in recent activities tab");
					sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in recent activities tab");
				}
			}else {
				appLog.error("Watermarking is not verified on recent activities tab in file: "+UploadedFileInStd);
				sa.assertTrue(false, "Watermarking is not verified on recent activities tab in file: "+UploadedFileInStd);
			}
			aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.RecentActivitiesTab, null);
			if(!aa.isEmpty()) {
				for(int i=0; i<aa.size(); i++) {
					appLog.error(aa.get(i));
					sa.assertTrue(false, aa.get(i));
				}
			}else {
				appLog.info("Watermarking labels are verified in  import file : "+importfileInstd);
			}
			aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.RecentActivitiesTab,null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
					appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in all dcoument tab");
				}else {
					appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in recent activities tab");
					sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in recent activities tab");
				}
				if(aa.size()==6) {
					appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in recent activities tab");
				}else {
					appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in recent activities tab");
					sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in recent activities tab");
				}
			}else {
				appLog.error("Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInCommon+" in recent activities tab");
				sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInCommon+" in recent activities tab");
			}
			aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.RecentActivitiesTab, null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
					appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in recent activities tab");
				}else {
					appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in recent activities tab");
					sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in recent activities tab");
				}
				if(aa.size()==1) {
					appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in recent activities tab");
				}else {
					appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in recent activities tab");
					sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in recent activities tab");
				}
			}else {
				appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in recent activities tab");
				sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in recent activities tab");
			}
			aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.RecentActivitiesTab, null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
					appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in recent activities tab");
				}else {
					appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in recent activities tab");
					sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in recent activities tab");
				}
				if(aa.size()==6) {
					appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in recent activities tab");
				}else {
					appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in recent activities tab");
					sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in recent activities tab");
				}
			}else {
				appLog.error("Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInShared+" in recent activities tab");
				sa.assertTrue(false, "Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInShared+" in recent activities tab");
			}
			aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.RecentActivitiesTab,null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
					appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in recent activities tab");
				}else {
					appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in recent activities tab");
					sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in recent activities tab");
				}
				if(aa.size()==1) {
					appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in recent activities tab");
				}else {
					appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in recent activities tab");
					sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in recent activities tab");
				}
			}else {
				appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in recent activities tab");
				sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in recent activities tab");
			}
		}else {
			appLog.error("Not able to click on recent activities tab so cannot check watermarking in recent activities tab documents");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot check watermarking in recent activities tab documents");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc024_3_VerifyWaterMarkingInPotentialInvestmentTab() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String UpdatedM13FundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc2, excelLabel.Watermarking);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.UploadedFileCommon);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.StandardPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.CommonPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.SharedPath);
		lp.investorLogin(M13Contact1EmailId,adminPassword);
		if(ivp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if(fp.verifyFolderPathdummy(stdfolderpath, M13Institution1, M13LimitedPartner1, null, PageName.CurrentInvestmentPgae, null, 30)) {
				List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.CurrentInvestmentPgae, null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
						appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available in file: "+UploadedFileInStd+" in current investment tab");
					}else {
						appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available in file: "+UploadedFileInStd+" in current investment tab");
						sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available in file: "+UploadedFileInStd+" in current investment tab");
					}
					if(aa.size()==6) {
						appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in current investment tab");
					}else {
						appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in current investment tab");
						sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in current investment tab");
					}
				}else {
					appLog.error("Watermarking is not verified on current investment tab in file: "+UploadedFileInStd);
					sa.assertTrue(false, "Watermarking is not verified on current investment tab in file: "+UploadedFileInStd);
				}
				aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.CurrentInvestmentPgae, null);
				if(!aa.isEmpty()) {
					for(int i=0; i<aa.size(); i++) {
						appLog.error(aa.get(i));
						sa.assertTrue(false, aa.get(i));
					}
				}else {
					appLog.info("Watermarking labels are verified in  import file : "+importfileInstd);
				}
				
			}else {
				appLog.error("Not able to click on folder path : "+stdfolderpath+" so cannot verify watermarking in standard folder files");
				sa.assertTrue(false, "Not able to click on folder path : "+stdfolderpath+" so cannot verify watermarking in standard folder files");
			}
			if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, null, PageName.CurrentInvestmentPgae,null, 30)) {
				List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.CurrentInvestmentPgae,null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
						appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in current investment tab");
					}else {
						appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in current investment tab");
						sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in current investment tab");
					}
					if(aa.size()==6) {
						appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in current investment tab");
					}else {
						appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in current investment tab");
						sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in current investment tab");
					}
				}else {
					appLog.error("Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInCommon+" in current investment tab");
					sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInCommon+" in current investment tab");
				}
				aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.CurrentInvestmentPgae, null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
						appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in current investment tab");
					}else {
						appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in current investment tab");
						sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in current investment tab");
					}
					if(aa.size()==1) {
						appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in current investment tab");
					}else {
						appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in current investment tab");
						sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in current investment tab");
					}
				}else {
					appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in current investment tab");
					sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in current investment tab");
				}
				
			}else {
				appLog.error("Not able to click on folderpath: "+Commonfolderpath+" so cannot check watermarking in common folder files");
				sa.assertTrue(false, "Not able to click on folderpath: "+Commonfolderpath+" so cannot check watermarking in common folder files");
			}
			if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, null, PageName.CurrentInvestmentPgae,null, 30)) {
				List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.CurrentInvestmentPgae, null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
						appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in current investment tab");
					}else {
						appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in current investment tab");
						sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in current investment tab");
					}
					if(aa.size()==6) {
						appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in current investment tab");
					}else {
						appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in current investment tab");
						sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in current investment tab");
					}
				}else {
					appLog.error("Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInShared+" in current investment tab");
					sa.assertTrue(false, "Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInShared+" in current investment tab");
				}
				aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.CurrentInvestmentPgae,null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
						appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in current investment tab");
					}else {
						appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in current investment tab");
						sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in current investment tab");
					}
					if(aa.size()==1) {
						appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in current investment tab");
					}else {
						appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in current investment tab");
						sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in current investment tab");
					}
				}else {
					appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in current investment tab");
					sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in current investment tab");
				}
			}else {
				appLog.error("Not able to click on folderpath: "+Sharedfolderpath+" so cannot check watermarking in shared folder files");
				sa.assertTrue(false, "Not able to click on folderpath: "+Sharedfolderpath+" so cannot check watermarking in shared folder files");
			}
		}else {
			appLog.error("Not able to click on current investment tab so cannot check watermarking");
			sa.assertTrue(false, "Not able to click on current investment tab so cannot check watermarking");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc024_4_VerifyWaterMarkingInAllFirmPage() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String UpdatedM13FundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc2, excelLabel.Watermarking);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.UploadedFileCommon);
		lp.investorLogin(M13Contact1EmailId,adminPassword);
		if(allfp.selectFirmName("All Firms")) {
			List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllFirmsPage, null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
					appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available in file: "+UploadedFileInStd+" in all firms tab");
				}else {
					appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available in file: "+UploadedFileInStd+" in all firms tab");
					sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available in file: "+UploadedFileInStd+" in all firms tab");
				}
				if(aa.size()==6) {
					appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in all firms tab");
				}else {
					appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in all firms tab");
					sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in all firms tab");
				}
			}else {
				appLog.error("Watermarking is not verified on recent all firms in file: "+UploadedFileInStd);
				sa.assertTrue(false, "Watermarking is not verified on all firms in file: "+UploadedFileInStd);
			}
			aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllFirmsPage, null);
			if(!aa.isEmpty()) {
				for(int i=0; i<aa.size(); i++) {
					appLog.error(aa.get(i));
					sa.assertTrue(false, aa.get(i));
				}
			}else {
				appLog.info("Watermarking labels are verified in  import file : "+importfileInstd);
			}
			aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllFirmsPage,null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
					appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in AllFirmsPage");
				}else {
					appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in AllFirmsPage");
					sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in AllFirmsPage");
				}
				if(aa.size()==6) {
					appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in AllFirmsPage");
				}else {
					appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in AllFirmsPage");
					sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in AllFirmsPage");
				}
			}else {
				appLog.error("Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInCommon+" in AllFirmsPage");
				sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInCommon+" in AllFirmsPage");
			}
			aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllFirmsPage, null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
					appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in AllFirmsPage");
				}else {
					appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in AllFirmsPage");
					sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in AllFirmsPage");
				}
				if(aa.size()==1) {
					appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in AllFirmsPage");
				}else {
					appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in AllFirmsPage");
					sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in AllFirmsPage");
				}
			}else {
				appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in AllFirmsPage");
				sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in AllFirmsPage");
			}
			aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllFirmsPage, null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
					appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in AllFirmsPage");
				}else {
					appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in AllFirmsPage");
					sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in AllFirmsPage");
				}
				if(aa.size()==6) {
					appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in AllFirmsPage");
				}else {
					appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in AllFirmsPage");
					sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in AllFirmsPage");
				}
			}else {
				appLog.error("Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInShared+" in AllFirmsPage");
				sa.assertTrue(false, "Watermarking is not verified on recent activities tab in upload file: "+UploadedFileInShared+" in AllFirmsPage");
			}
			aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllFirmsPage,null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
					appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in All Firms Page");
				}else {
					appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in All Firms Page");
					sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in All Firms Page");
				}
				if(aa.size()==1) {
					appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in All Firms Page");
				}else {
					appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in All Firms Page");
					sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in All Firms Page");
				}
			}else {
				appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in All Firms Page");
				sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in All Firms Page");
			}
		}else {
			appLog.error("Not able to click on All Firms Page so cannot check watermarking in All Firms Page documents");
			sa.assertTrue(false, "Not able to click on All Firms Page so cannot check watermarking in All Firms Page documents");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc025_1_uploadfileInvestorInCurrentInvestmentAndCheckWatermarking() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.StandardPath);
		String docpath = "UploadFiles\\Module13\\FileToUploadInvestorSide";
		lp.investorLogin(M13Contact1EmailId,adminPassword);
		if(ivp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if (ivp.uploadUpdateFileInvestorSide(stdfolderpath,UploadedFileInStd, M13Institution1, M13LimitedPartner1, FolderType.Standard, docpath, null, 30,
					PageName.CurrentInvestmentPgae, null, null, WorkSpaceAction.UPLOAD)) {
				appLog.info("file is uploaded successfully in folderpath :"+stdfolderpath);
			}else {
				appLog.error("Not able to upload file in folderpath: "+stdfolderpath);
				sa.assertTrue(false, "Not able to upload file in folderpath: "+stdfolderpath);
			}
		}else {
			appLog.error("Not able to click on Current investment tab so cannot upload file in folderpath: "+stdfolderpath);
			sa.assertTrue(false,"Not able to click on Current investment tab so cannot upload file in folderpath: "+stdfolderpath);
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc025_2_verifyWatermarkingOnHomePageAndFundPage() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOnTc="M13tc025_1_uploadfileInvestorInCurrentInvestmentAndCheckWatermarking";
		String dependOnTc1="M13tc022_1_verifyDocumentOnFundPage";
		String oldM13Institution1=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.UploadedFileStandard);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.StandardPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		switchToFrame(driver, 20,fp.getFrame( PageName.HomePage, 10));
		List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
				appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in home alerts grid");
			}else {
				appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in home alerts grid");
				sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in home alerts grid");
			}
			if(aa.size()==6) {
				appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in home alerts grid");
			}else {
				appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in home alerts grid");
				sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in home alerts grid");
			}
			switchToDefaultContent(driver);
		}else {
			appLog.error("Watermarking is not verified in file: "+UploadedFileInStd+" on home alerts grid");
			sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInStd+" on home alerts grid");
		}
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, M13Institution1, M13LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
					
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInStd+" in folder "+stdfolderpath);
					}
					switchToFrame(driver, 20,fp.getFrame( PageName.FundsPage, 20));
				}else {
					appLog.error("Not able to click on folderpath: "+stdfolderpath+"  so cannot check watermarking in investor side uploaded file: "+UploadedFileInStd);
					sa.assertTrue(false, "Not able to click on folderpath: "+stdfolderpath+"  so cannot check watermarking in investor side uploaded file: "+UploadedFileInStd);
				}
				if(click(driver, fp.getAlertHistoryLink(Workspace.InvestorWorkspace,PageName.FundsPage, 10), "alert histroy link", action.SCROLLANDBOOLEAN)) {
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInStd+" in folder "+stdfolderpath);
					}
				}else {
					appLog.error("Not able to click on fund page alert history link so cannot check watermarking in investor side upload file: "+UploadedFileInStd);
					sa.assertTrue(false, "Not able to click on fund page alert history link so cannot check watermarking in investor side upload file: "+UploadedFileInStd);
				}
			}else {
				appLog.error("Not able to click on created fund "+M13FundName1+" so cannot check watermarking in investor side upload file: "+UploadedFileInStd);
				sa.assertTrue(false, "Not able to click on created fund "+M13FundName1+" so cannot check watermarking in investor side upload file: "+UploadedFileInStd);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot check watermarking in investor side uploaded file: "+UploadedFileInStd);
			sa.assertTrue(false, "Not able to click on fund tab so cannot check watermarking in investor side uploaded file: "+UploadedFileInStd);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc025_3_verifyWatermarkingOnContactPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		String dependOnTc="M13tc025_1_uploadfileInvestorInCurrentInvestmentAndCheckWatermarking";
		String dependOnTc1="M13tc022_1_verifyDocumentOnFundPage";
		String oldM13Institution1=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.UploadedFileStandard);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.StandardPath);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(M13Contact1FirstName, M13Contact1LastName, null)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, M13Institution1, M13LimitedPartner1, UpdatedM13FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified in file: "+UploadedFileInStd+" on contact page in INV");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInStd+" on contact page in INV");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				}else {
					appLog.error("Not able to click on folderpath: "+stdfolderpath+" on contact page so cannot verify watermarking");
					sa.assertTrue(false, "Not able to click on folderpath: "+stdfolderpath+" on contact page so cannot verify watermarking");
				}
				if(click(driver, contact.getAlertHistoryLink(Workspace.InvestorWorkspace, PageName.ContactsPage, 10), "alert history link", action.SCROLLANDBOOLEAN)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in alert history pop up");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in alert history pop up");
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in alert history pop up");
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in alert history pop up");
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in alert history pop up");
						}
					}else {
						appLog.error("Watermarking is not verified file: "+UploadedFileInStd+" in alert history pop up");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInStd+" in alert history pop up");
					}
				}else {
					appLog.error("Not able to click on alert history link on contact page so cannot check watermrking in file : "+UploadedFileInStd);
					sa.assertTrue(false, "Not able to click on alert history link on contact page so cannot check watermrking in file : "+UploadedFileInStd);
				}
				
			}else {
				appLog.error("Not able to click on created contact : "+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot check watermarking on contact page");
				sa.assertTrue(false, "Not able to click on created contact : "+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot check watermarking on contact page");
			}
			
		}else {
			appLog.error("Not able to click on contact tab so cannot check watermarking on contact page");
			sa.assertTrue(false, "Not able to click on contact tab so cannot check watermarking on contact page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc025_4_verifyWaterMarkingOnLimitedPartnerAndCommitmentPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		CommitmentPageBusinessLayer com = new CommitmentPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String dependOnTc="M13tc025_1_uploadfileInvestorInCurrentInvestmentAndCheckWatermarking";
		String dependOnTc1="M13tc022_1_verifyDocumentOnFundPage";
		String UpdatedM13FundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.UploadedFileStandard);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc, excelLabel.StandardPath);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedLP(M13LimitedPartner1)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, null, null, UpdatedM13FundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.LimitedPartnerPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified in file: "+UploadedFileInStd+" on LP page");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInStd+" on LP page");
					}
				}else {
					appLog.error("Not able to click on folder ::"+stdfolderpath+" so cannot check watermarking on LP page");
					sa.assertTrue(false, "Not able to click on folder ::"+stdfolderpath+" so cannot check watermarking on LP page");
				}
			}else {
				appLog.error("Not able to click on created Institution "+M13LimitedPartner1+" so cannot verify watermarking");
				sa.assertTrue(false, "Not able to click on created Institution "+M13LimitedPartner1+" so cannot verify watermarking");
			}
		}else {
			appLog.error("Not able to click on Institution tab so cannot verify watermarking on LP page");
			sa.assertTrue(false, "Not able to click on Institution tab so cannot verify watermarking on LP page");
		}
		switchToDefaultContent(driver);
		if(com.clickOnTab(TabName.CommitmentsTab)) {
			if(com.clickOnCreatedCommitmentId(M13Commitment1ID)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.CommitmentsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, null, M13LimitedPartner1, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,M13LimitedPartner1,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.CommitmentsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder "+stdfolderpath);
						}
					}else {
						appLog.error("Watermarking is not verified in file: "+UploadedFileInStd+" on contact page in INV");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInStd+" on contact page in INV");
					}
				}else {
					appLog.error("Watermarking is not verified on funds page in upload file: "+UploadedFileInStd+" in folder "+stdfolderpath);
					sa.assertTrue(false, "Watermarking is not verified on funds page on fund in upload file: "+UploadedFileInStd+" in folder "+stdfolderpath);
				}
			}else {
				appLog.error("Not able to click on created Commitment "+M13Commitment1+" so cannot verify watermarking");
				sa.assertTrue(false, "Not able to click on created Commitments "+M13Commitment1+" so cannot verify watermarking");
			}
		}else {
			appLog.error("Not able to click on Commitments tab so cannot verify watermarking on Commitments page");
			sa.assertTrue(false, "Not able to click on Commitments tab so cannot verify watermarking on Commitments page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc026_1_verifyWaterMarkingInUploadedFilesInINVOnHomepage() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String oldM13Institution1=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc2, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		switchToFrame(driver, 20,fp.getFrame( PageName.HomePage, 10));
		List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
				appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in home alert grid");
			}else {
				appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in home alert grid");
				sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in home alert grid");
			}
			if(aa.size()==6) {
				appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in home alert grid");
			}else {
				appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in  home alert grid");
				sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in home alert grid");
			}
		}else {
			appLog.error("Watermarking is not verified in file: "+UploadedFileInStd+" in home alert grid");
			sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInStd+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, null);
		if(!aa.isEmpty()) {
			for(int i=0; i<aa.size(); i++) {
				appLog.error(aa.get(i));
				sa.assertTrue(false, aa.get(i));
			}
		}else {
			appLog.info("Watermarking labels are verified in  import file : "+importfileInstd+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
				appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in home alert grid");
			}else {
				appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in home alert grid");
				sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in home alert grid");
			}
			if(aa.size()==6) {
				appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in home alert grid");
			}else {
				appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in home alert grid");
				sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in home alert grid");
			}
		}else {
			appLog.error("Watermarking is not verified in file: "+UploadedFileInCommon+" in home alert grid");
			sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInCommon+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
				appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in home alert grid");
			}else {
				appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in home alert grid");
				sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in home alert grid");
			}
			if(aa.size()==1) {
				appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in home alert grid");
			}else {
				appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in home alert grid");
				sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in home alert grid");
			}
		}else {
			appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in home alert grid");
			sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage,null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
				appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in home alert grid");
			}else {
				appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in home alert grid");
				sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in home alert grid");
			}
			if(aa.size()==6) {
				appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in home alert grid");
			}else {
				appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in home alert grid");
				sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in home alert grid");
			}
		}else {
			appLog.error("Watermarking is not verified in file: "+UploadedFileInShared+" in home alert grid");
			sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInShared+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage,null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
				appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in home alert grid");
			}else {
				appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in home alert grid");
				sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in home alert grid");
			}
			if(aa.size()==1) {
				appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in home alert grid");
			}else {
				appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in home alert grid");
				sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in home alert grid");
			}
		}else {
			appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in home alert grid");
			sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in home alert grid");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc026_2_verifyWaterMarkingInUploadedFilesInINVOnFundPageAlert() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String oldM13Institution1=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc2, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 20,fp.getFrame( PageName.FundsPage, 10));
				if(click(driver, fp.getAlertHistoryLink(Workspace.InvestorWorkspace,PageName.FundsPage, 10), "alert histroy link", action.SCROLLANDBOOLEAN)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in fund alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in fund alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in fund alert grid");
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in fund alert grid");
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in  fund alert grid");
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in fund alert grid");
						}
					}else {
						appLog.error("Watermarking is not verified in file: "+UploadedFileInStd+" in fund alert grid");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInStd+" in fund alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						for(int i=0; i<aa.size(); i++) {
							appLog.error(aa.get(i));
							sa.assertTrue(false, aa.get(i));
						}
					}else {
						appLog.info("Watermarking labels are verified in  import file : "+importfileInstd+" in fund alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in fund alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in fund alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in fund alert grid");
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in fund page alert grid");
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in fund page alert grid");
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in fund page alert grid");
						}
					}else {
						appLog.error("Watermarking is not verified in file: "+UploadedFileInCommon+" in fund page alert grid");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInCommon+" in fund page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in fund page alert grid");
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in fund page alert grid");
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in fund page alert grid");
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in fund page alert grid");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in fund page alert grid");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in fund page alert grid");
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in fund page alert grid");
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in fund page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in fund page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in fund page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in fund page alert grid");
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in fund page alert grid");
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in fund page alert grid");
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in fund page alert grid");
						}
					}else {
						appLog.error("Watermarking is not verified in file: "+UploadedFileInShared+" in fund page alert grid");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInShared+" in fund page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in fund page alert grid");
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in fund page alert grid");
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in fund page alert grid");
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in fund page alert grid");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in fund page alert grid");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in fund page alert grid");
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in fund page alert grid");
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in fund page alert grid");
					}
					
				}else {
					appLog.error("Not able to click on fund page fundraising workspace alert history link so cannot check watermarking");
					sa.assertTrue(false, "Not able to click on fund page fundraising workspace alert history link so cannot check watermarking");
				}
				
			}else {
				appLog.error("Not able to click on created fund :"+M13FundName1+" so cannot watermrking on fund page history pop up");
				sa.assertTrue(false, "Not able to click on created fund :"+M13FundName1+" so cannot watermrking on fund page history pop up");
			}
		}else {
			appLog.error("Not able to click fund tab so cannot check watermarking in fund page alert history pop up");
			sa.assertTrue(false, "Not able to click fund tab so cannot check watermarking in fund page alert history pop up");
			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc026_3_verifyWaterMarkingInUploadedFilesInINVOnContactPageAlert() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String oldM13Institution1=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc2, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(contact.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(M13Contact1FirstName, M13Contact1LastName, null)) {
				switchToFrame(driver, 20,fp.getFrame( PageName.ContactsPage, 10));
				if(click(driver, fp.getAlertHistoryLink(Workspace.InvestorWorkspace,PageName.ContactsPage, 10), "alert histroy link", action.SCROLLANDBOOLEAN)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in contact page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in contact page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in contact page alert grid");
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInStd+" in contact page alert grid");
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in  contact page alert grid");
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInStd+" in contact page alert grid");
						}
					}else {
						appLog.error("Watermarking is not verified in file: "+UploadedFileInStd+" in contact page alert grid");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInStd+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						for(int i=0; i<aa.size(); i++) {
							appLog.error(aa.get(i));
							sa.assertTrue(false, aa.get(i));
						}
					}else {
						appLog.info("Watermarking labels are verified in  import file : "+importfileInstd+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in contact page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in contact page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in contact page alert grid");
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInCommon+" in contact page alert grid");
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in contact page alert grid");
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInCommon+" in contact page alert grid");
						}
					}else {
						appLog.error("Watermarking is not verified in file: "+UploadedFileInCommon+" in contact page alert grid");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInCommon+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInCommon+" in contact page alert grid");
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInCommon+" in contact page alert grid");
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInCommon+" in contact page alert grid");
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInCommon+" in contact page alert grid");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in contact page alert grid");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in contact page alert grid");
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in contact page alert grid");
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInCommon+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3", aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in contact page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in contact page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in contact page alert grid");
						}
						if(aa.size()==6) {
							appLog.info("Download Date/IP Adderss/UserEmail Id is available on file: "+UploadedFileInShared+" in contact page alert grid");
						}else {
							appLog.error("Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in contact page alert grid");
							sa.assertTrue(false, "Download Date/IP Adderss/UserEmail Id is not available on file: "+UploadedFileInShared+" in contact page alert grid");
						}
					}else {
						appLog.error("Watermarking is not verified in file: "+UploadedFileInShared+" in contact page alert grid");
						sa.assertTrue(false, "Watermarking is not verified in file: "+UploadedFileInShared+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+M13LimitedPartner1,UpdatedM13FundName1,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("Investor Name", aa)) {
							appLog.info("Investor Name watermarking labels are not available on file: "+importfileInShared+" in contact page alert grid");
						}else {
							appLog.error("Investor Name watermarking labels are available on file: "+importfileInShared+" in contact page alert grid");
							sa.assertTrue(false, "Investor Name watermarking labels are available on file: "+importfileInShared+" in contact page alert grid");
						}
						if(aa.size()==1) {
							appLog.info("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is available on file: "+importfileInShared+" in contact page alert grid");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in contact page alert grid");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/IPAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in contact page alert grid");
						}
					}else {
						appLog.info("Investor Name Watermarking labels are present in import file : "+importfileInShared+" in contact page alert grid");
						sa.assertTrue(false, "Investor Name Watermarking labels are present in import file : "+importfileInShared+" in contact page alert grid");
					}
					
				}else {
					appLog.error("Not able to click on contact page fundraising workspace alert history link so cannot check watermarking");
					sa.assertTrue(false, "Not able to click on contact page fundraising workspace alert history link so cannot check watermarking");
				}
				
			}else {
				appLog.error("Not able to click on created contact :"+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot watermrking on contact page history pop up");
				sa.assertTrue(false, "Not able to click on created contact :"+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot watermrking on contact page history pop up");
			}
		}else {
			appLog.error("Not able to click Contact tab so cannot check watermarking in contact page alert history pop up");
			sa.assertTrue(false, "Not able to click contact tab so cannot check watermarking in contact page alert history pop up");
			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc027_1_updateMyFirmProfile() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if(bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30,bp.getFrame( PageName.NavatarInvestorManager, 30));
			if(nim.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
				if(nim.clickOnEditIcon()) {
					if(sendKeys(driver,nim.getMyFirmProfileNameTextBox(60), Org1FirmName+"UpdatedIWR", "my firm profile name", action.SCROLLANDBOOLEAN)) {
						ExcelUtils.writeData(Org1FirmName+"UpdatedIWR", "Users",excelLabel.Variable_Name,"AdminUser", excelLabel.Updated_FirmName);
						ExcelUtils.writeData(Org1FirmName+"UpdatedIWR", "Users",excelLabel.Variable_Name,"User1", excelLabel.Updated_FirmName);
						appLog.info("Passed value in firm name text box: "+Org1FirmName+"UpdatedIWR");
						if(click(driver, nim.getMyFirmProfilesaveBtn(30), "my firm profile save button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on save button");
						}else {
							appLog.error("Not able to click on my firm firm  profile save button so cannot update my  firm profile");
							sa.assertTrue(false, "Not able to click on my firm firm  profile save button so cannot update my  firm profile");
						}
					}else {
						appLog.error("Not able to pass value in my firm profile text box :"+Org1FirmName+"UpdatedIWR so cannot update my firm profile");
						sa.assertTrue(false, "Not able to pass value in my firm profile text box :"+Org1FirmName+"UpdatedIWR so cannot update my firm profile");
					}
				}else {
					appLog.error("Not able to click on my firm profile edit icon so  cannot update my firm profile");
					sa.assertTrue(false, "Not able to click on my firm profile edit icon so  cannot update my firm profile");
				}
			}else {
				appLog.error("Not able to click on my firm profile tab so cannot update my firm profile");
				sa.assertTrue(false, "Not able to click on my firm profile tab so cannot update my firm profile");
			}
		}else {
			appLog.error("Not able to click on nim tab so cannot update my firm profile");
			sa.assertTrue(false, "Not able to click on nim tab so cannot update my firm profile");
		}
		switchToDefaultContent(driver);
		
	}
	
	@Test
	public void M13tc027_2_updateInvestorNameLPNameAndFundName() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 20,fp.getFrame( PageName.FundsPage, 10));
				if(fp.updateInvestorOrLPNameFromManageInvestor(Workspace.InvestorWorkspace, M13Institution1, M13LimitedPartner1, M13LimitedPartner1+"UP", "M13LimitedPartner1")) {
					appLog.info("investor name is updated successfully: "+M13LimitedPartner1+"UP");
				}else {
					appLog.error("Not able to update investor name form manage investor");
					sa.assertTrue(false, "Not able to update investor name form manage investor");
				}
			}else {
				appLog.error("Not able to click on created fund :"+M13FundName1+" so cannot update LP Name from manage investor");
				sa.assertTrue(false, "Not able to click on created fund :"+M13FundName1+" so cannot update LP Name from manage investor");
			}
			switchToDefaultContent(driver);
		}else {
			appLog.error("Not able to click fund tab so cannot update LP Name from manage investor");
			sa.assertTrue(false, "Not able to click fund tab so cannot update LP Name from manage investor");
		}
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 20,fp.getFrame( PageName.FundsPage, 10));
				if (click(driver, fp.getInvestmentInfo(Workspace.InvestorWorkspace), "Investment info",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getInvestmentInfoEdit(60), "investment info edit button",
							action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, fp.getInvestmentInfoFundNameTxtbox(60), M13FundName1+"UPIWR","Fund name text box",
								action.BOOLEAN)) {
							if (click(driver, fp.getInvestmentInfoSaveBtn(40), "Investment Info save button",
									action.SCROLLANDBOOLEAN)) {
								
								ExcelUtils.writeData(M13FundName1+"UPIWR", "Funds",excelLabel.Variable_Name,"M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoIWR);
								if (getText(driver, fp.getFirmName(30), "Fund Name", action.SCROLLANDBOOLEAN)
										.equals(M13FundName1+"UPIWR")) {
									appLog.info("Fund Name is successfully updated and verfied.");
									
								} else {
									appLog.error("Fund Name is not Updated.");
									sa.assertTrue(false, "Fund Name is not Updated.");
								}
							} else {
								appLog.error("Save button is not clicked so Updated fund name is not able to save.");
								sa.assertTrue(false,
										"Save button is not clicked so Updated fund name is not able to save.");
							}
						} else {
							appLog.error(
									"fund name text box is not visible on Investment Info edit mode so, not able to enter value.");
							sa.assertTrue(false,
									"fund name text box is not visible on Investment Info edit mode so, not able to enter value.");
						}
					} else {
						appLog.error("Investment Info edit button is not clickable so, not able to enter value.");
						sa.assertTrue(false,
								"Investment Info edit button is not clickable so, not able to enter value.");
					}
				}else {
					appLog.error("Not able to click on investment update info link so cannot update fund Name from investment update info");
					sa.assertTrue(false, "Not able to click on investment update info link so cannot update fund Name from investment info");
				}
			}else {
				appLog.error("Not able to click on created fund :"+M13FundName1+" so cannot update fund Name from investment update info");
				sa.assertTrue(false, "Not able to click on created fund :"+M13FundName1+" so cannot update fund Name from investment info");
			}
			
		}else {
			appLog.error("Not able to click fund tab so cannot update Fund Name from investment update info");
			sa.assertTrue(false, "Not able to click fund tab so cannot update fund Name from investment update info");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				if(fp.updateCreatedFund(M13FundName1, M13FundName1+"NUPIWR", "M13Fund1")) {
					appLog.info("Fund Name is updated successfully : "+M13FundName1+" to "+M13FundName1+"NUPIWR");
				}else {
					appLog.error("Fund Name is not updated: "+M13FundName1);
					sa.assertTrue(false, "Fund Name is not updated "+M13FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund :"+M13FundName1+" so cannot update fund Name");
				sa.assertTrue(false, "Not able to click on created fund :"+M13FundName1+" so cannot update fund Name");
			}
		}else {
			appLog.error("Not able to click fund tab so cannot update Fund Name");
			sa.assertTrue(false, "Not able to click fund tab so cannot update fund Name");
		}
		if(ins.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedLP(M13LimitedPartner1)) {
				if(ins.updateCreateLimitedPartner(M13LimitedPartner1, M13LimitedPartner1+"NUPIWR", "M13LimitedPartner1")) {
					appLog.info("LP Name is updated successfully : "+M13LimitedPartner1+" to "+M13LimitedPartner1+"NUPIWR");
				}else {
					appLog.error("Institution Name is not updated: "+M13LimitedPartner1);
					sa.assertTrue(false, "Institution Name is not updated "+M13LimitedPartner1);
				}
			}else {
				appLog.error("Not able to click on created LP :"+M13LimitedPartner1+" so cannot update LP Name");
				sa.assertTrue(false, "Not able to click on created LP :"+M13LimitedPartner1+" so cannot update LP Name");
			}
		}else {
			appLog.error("Not able to click Institution tab so cannot update LP Name");
			sa.assertTrue(false, "Not able to click Institution tab so cannot update LP Name");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc028_1_updateWatermarkingAndVerifyDocumentInINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.Watermarking);
		lp.CRMLogin(CRMUser2EmailID,adminPassword);
		if(nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if(nim.clickOnSideMenusTab(sideMenu.Watermarking)) {
				if(nim.clickOnEditIcon()) {
					List<WebElement> lst = nim.getWaterMarkingCheckBoxList();
					List<WebElement> dropdown=nim.getWatermarkingDropDownList();
					if(!lst.isEmpty()) {
						for(int i=1 ;i<lst.size()-3; i++) {
							if(isSelected(driver, lst.get(i), "check box")) {
								if(click(driver,lst.get(i), "check box", action.SCROLLANDBOOLEAN)) {
									appLog.info("label clicked on check box");
								}else {
									appLog.error("Not able to click on check box so cannot uncheck check box");
									sa.assertTrue(false, "Not able to click on check box so cannot uncheck check box");
								}
							}else {
								appLog.info("label check Box is not checked");
							}
						}
					}else {
						appLog.error("watermarking check box list is not visible so cannot uncheck it");
						sa.assertTrue(false, "watermarking check box list is not visible so cannot uncheck it");
					}
					if(!dropdown.isEmpty()) {
						for(int j1=1 ;j1<dropdown.size(); j1++) {
							if(getSelectedOptionOfDropDown(driver, dropdown.get(j1), "drop down", "text").trim()!="None") {
								if(selectVisibleTextFromDropDown(driver,dropdown.get(j1), "drop down","None")) {
									appLog.info("Select None value from drop down list");
								}else {
									appLog.error("Not able to select None value from drop down list");
									sa.assertTrue(false, "Not able to select None value from drop down list");
								}
							}else {
								appLog.info("None is alreadyed selected in drop down list");
							}
						}
					}else {
						appLog.error("watermarking drop dwon list is not visible so cannot reset drop down");
						sa.assertTrue(false, "watermarking drop dwon list is not visible so cannot reset drop down");
					}
					lst =nim.getWaterMarkingCustomFieldCrossIconList();
					if(!lst.isEmpty()) {
						for(int i=0 ;i<lst.size(); i++) {
							if(click(driver,lst.get(i), "check box", action.SCROLLANDBOOLEAN)) {
								appLog.info("label clicked on check box");
							}else {
								appLog.error("Not able to click on check box so cannot uncheck check box");
								sa.assertTrue(false, "Not able to click on check box so cannot uncheck check box");
							}
						}
					}else {
						appLog.error("custom watermarking check box list is not visible so cannot uncheck it");
						sa.assertTrue(false, "custom watermarking check box list is not visible so cannot uncheck it");
					}
					lst.clear();
					lst = nim.getWatermarkingCustomTextBoxList();
					if(!lst.isEmpty()) {
						for(int i=0 ;i<lst.size(); i++) {
							if(sendKeys(driver, lst.get(i), "", "custom text box", action.SCROLLANDBOOLEAN)) {
								appLog.info("custom label text is cleared");
							}else {
								appLog.error("Not able to clear custom text box");
								sa.assertTrue(false, "Not able to clear custom text box");
							}
						}
					}else {
						appLog.error("custom watermarking check box list is not visible so cannot uncheck it");
						sa.assertTrue(false, "custom watermarking check box list is not visible so cannot uncheck it");
					}
					if(click(driver,nim.getCustomLabelCheckBox(10), "custom check box", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on custom check box");
						if(click(driver, nim.getWatermarkingSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)){
							appLog.info("clicked on save button");
							if(nim.activateWatermarking2(WatermarkingLabels).isEmpty()) {
								appLog.info("watermarking setting is updated successfully");
							}else {
								appLog.error("Not able to update watermarking setting");
								sa.assertTrue(false, "Not able to update watermarking setting");
							}
						}else {
							appLog.error("Not able to click on watermarking save button so cannot save it");
							sa.assertTrue(false, "Not able to click on watermarking save button so cannot save it");
						}
					}else {
						appLog.error("Not able to click on watermarking custom check check box so cannot save it");
						sa.assertTrue(false, "Not able to click on watermarking custom check check box so cannot save it");
					}
				}else {
					appLog.error("Not able to click on watermarking side menu so cannot activate watermarking settings");
					sa.assertTrue(false, "Not able to click on watermarking side menu so cannot activate watermarking settings");
				}
			}else {
				appLog.error("Not able to click on watermarking tab so cannot update watermarking");
				sa.assertTrue(false, "Not able to click on watermarking tab so cannot update watermarking");
			}
		}else {
			appLog.error("Not able to click on NIM tab so cannot update watermarking");
			sa.assertTrue(false, "Not able to click on NIM tab so cannot update watermarking");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc028_2_verifyOldWaterMarkingInUploadedFilesOnHomepageINV() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		M13Institution1=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		M13FundName1=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M13FundRaising1", excelLabel.Fund_Name);
		String oldFundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String oldLPName =ExcelUtils.readData("Commitments",excelLabel.Variable_Name, "M13Commitment1", excelLabel.LimitedPartner_Name);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc2, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		switchToFrame(driver, 20,fp.getFrame( PageName.HomePage, 10));
		List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, null);
		String NOtAvailableWaterMarkingLabels= "My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3<break>IP Address";
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
				appLog.info("My Firm Name,Investor Name, Fund Name,IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in home alert grid");
			}else {
				appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in home alert grid");
				sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in home alert grid");
			}
			if(aa.size()==7) {
				appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInStd+" in home alert grid");
			}else {
				appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in  home alert grid");
				sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in home alert grid");
			}
		}else {
			appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInStd+" in home alert grid");
			sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInStd+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion("IP Address", aa)) {
				appLog.info("IP Address watermarking labels are not available on file: "+UploadedFileInStd+" in home alert grid");
			}else {
				appLog.error("IP Address watermarking labels are available on file: "+UploadedFileInStd+" in home alert grid");
				sa.assertTrue(false, "IP Address watermarking labels are available on file: "+UploadedFileInStd+" in home alert grid");
			}
		}else {
			appLog.error("IP Address watermarking is available in file: "+UploadedFileInStd+" in home alert grid");
			sa.assertTrue(false, "IP Address watermarking is available in file: "+UploadedFileInStd+" in home alert grid");
		}
			if(aa.size()==1) {
				appLog.info("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is available on file: "+UploadedFileInStd+" in home alert grid");
			}else {
				appLog.error("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in  home alert grid");
				sa.assertTrue(false, "My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in home alert grid");
			}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
				appLog.info("My Firm Name,Investor Name, Fund Name, IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in home alert grid");
			}else {
				appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in home alert grid");
				sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in home alert grid");
			}
			if(aa.size()==7) {
				appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInCommon+" in home alert grid");
			}else {
				appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in home alert grid");
				sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in home alert grid");
			}
		}else {
			appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in home alert grid");
			sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage, null);
		String NOtAvailableLablesInCommon="Investor Name<break>IP Address";
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
				appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInCommon+" in home alert grid");
			}else {
				appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in home alert grid");
				sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in home alert grid");
			}
			if(aa.size()==2) {
				appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInCommon+" in home alert grid");
			}else {
				appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in home alert grid");
				sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in home alert grid");
			}
		}else {
			appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in home alert grid");
			sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage,null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
				appLog.info("My Firm Name,Investor Name, Fund Name,IP Address,Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in home alert grid");
			}else {
				appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in home alert grid");
				sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in home alert grid");
			}
			if(aa.size()==7) {
				appLog.info("Download Date,UserEmailId is available on file: "+UploadedFileInShared+" in home alert grid");
			}else {
				appLog.error("Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in home alert grid");
				sa.assertTrue(false, "Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in home alert grid");
			}
		}else {
			appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInShared+" in home alert grid");
			sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInShared+" in home alert grid");
		}
		switchToFrame(driver, 30,fp.getFrame( PageName.HomePage, 20));
		aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,M13Institution1+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.HomePage,null);
		if(!aa.isEmpty()) {
			if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
				appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInShared+" in home alert grid");
			}else {
				appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in home alert grid");
				sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in home alert grid");
			}
			if(aa.size()==2) {
				appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInShared+" in home alert grid");
			}else {
				appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in home alert grid");
				sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in home alert grid");
			}
		}else {
			appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in home alert grid");
			sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in home alert grid");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc028_3_verifyOldWaterMarkingInDocumentOnFundPageINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.Fund_Name);
		String UpdatedM13Institution1=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M13Instituition1", excelLabel.UpdateInstitution_NameFormManageInvestor);
		String UpdatedLPName=ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M13LimitedPartner1", excelLabel.UpdatedLimitedPartner_NameFormManageInvestor);
		String oldFundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		M13FundName1=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M13FundRaising1", excelLabel.Fund_Name);
		String oldLPName =ExcelUtils.readData("Commitments",excelLabel.Variable_Name, "M13Commitment1", excelLabel.LimitedPartner_Name);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc1, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.SharedPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.CommonPath);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc2, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(UpdatedM13FundName1)) {
				String NOtAvailableWaterMarkingLabels= "My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3<break>IP Address";
				String NOtAvailableLablesInCommon="Investor Name<break>IP Address";
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, M13Institution1, UpdatedLPName, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder : "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder:"+stdfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInStd+" in folder : "+stdfolderpath);
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address", aa)) {
							appLog.info("IP Address watermarking labels are not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("IP Address watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "IP Address watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
					}else {
						appLog.error("IP Address watermarking is available in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						sa.assertTrue(false, "IP Address watermarking is available in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
					}
						if(aa.size()==1) {
							appLog.info("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
				}else {
					appLog.error("Not able to click on institution Name ::"+UpdatedM13Institution1+" so cannot check  old watermarking on fundpage");
					sa.assertTrue(false, "Not able to click on institution Name ::"+UpdatedM13Institution1+" so cannot check  old watermarking on fundpage");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage,Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder: "+Commonfolderpath);
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder: "+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check old watermarking on fundpage");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check old watermarking on fundpage");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage,Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address,Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date,UserEmailId is available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in home alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage,Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInShared+" in folder:"+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder: "+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder: "+Sharedfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check old watermarking on fundpage");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check old watermarking on fundpage");
				}
			}else {
				appLog.error("Not able to click on created fund "+M13FundName1+" so cannot verify old watermarking on fundpage");
				sa.assertTrue(false, "Not able to click on created fund "+M13FundName1+" so cannot verify old watermarking on fundpage");
			}
		}else {
			appLog.error("Not able to click on Fund tab so cannot verify old watermarking on fundpage");
			sa.assertTrue(false, "Not able to click on Fund tab so cannot verify old watermarking on fundpage");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc028_4_verifyOlddWaterMarkingInUploadedFilesOnFundPageAlert() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		//String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.Fund_Name);
		String oldInstituteName=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		String oldFundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String oldLPName =ExcelUtils.readData("Commitments",excelLabel.Variable_Name, "M13Commitment1", excelLabel.LimitedPartner_Name);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc2, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 20,fp.getFrame( PageName.FundsPage, 10));
				if(click(driver, fp.getAlertHistoryLink(Workspace.InvestorWorkspace,PageName.FundsPage, 10), "alert histroy link", action.SCROLLANDBOOLEAN)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldInstituteName+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, null);
					String NOtAvailableWaterMarkingLabels= "My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3<break>IP Address";
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in fund page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in fund page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in fund page alert grid");
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInStd+" in fund page alert grid");
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in fund page alert grid");
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in fund page alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInStd+" in fund page alert grid");
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInStd+" in fund page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldInstituteName+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address", aa)) {
							appLog.info("IP Address watermarking labels are not available on file: "+UploadedFileInStd+" in fund page alert grid");
						}else {
							appLog.error("IP Address watermarking labels are available on file: "+UploadedFileInStd+" in fund page alert grid");
							sa.assertTrue(false, "IP Address watermarking labels are available on file: "+UploadedFileInStd+" in fund page alert grid");
						}
					}else {
						appLog.error("IP Address watermarking is available in file: "+UploadedFileInStd+" in fund page alert grid");
						sa.assertTrue(false, "IP Address watermarking is available in file: "+UploadedFileInStd+" in fund page alert grid");
					}
						if(aa.size()==1) {
							appLog.info("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is available on file: "+UploadedFileInStd+" in fund page alert grid");
						}else {
							appLog.error("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in fund page alert grid");
							sa.assertTrue(false, "My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in fund page alert grid");
						}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldInstituteName+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in fund page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in fund page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in fund page alert grid");
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInCommon+" in fund page alert grid");
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in fund page alert grid");
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in fund page alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in fund page alert grid");
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in fund page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldInstituteName+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, null);
					String NOtAvailableLablesInCommon="Investor Name<break>IP Address";
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInCommon+" in fund page alert grid");
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in fund page alert grid");
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in fund page alert grid");
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInCommon+" in fund page alert grid");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in fund page alert grid");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in fund page alert grid");
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in fund page alert grid");
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in fund page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldInstituteName+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address,Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in fund page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in fund page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in fund page alert grid");
						}
						if(aa.size()==7) {
							appLog.info("Download Date,UserEmailId is available on file: "+UploadedFileInShared+" in fund page alert grid");
						}else {
							appLog.error("Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in fund page alert grid");
							sa.assertTrue(false, "Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in fund page alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInShared+" in fund page alert grid");
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInShared+" in fund page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldInstituteName+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInShared+" in fund page alert grid");
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in fund page alert grid");
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in fund page alert grid");
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInShared+" in fund page alert grid");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in fund page alert grid");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in fund page alert grid");
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in fund page alert grid");
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in fund page alert grid");
					}
					
				}else {
					appLog.error("Not able to click on fund page fundraising workspace alert history link so cannot check old watermarking");
					sa.assertTrue(false, "Not able to click on fund page fundraising workspace alert history link so cannot check old watermarking");
				}
				
			}else {
				appLog.error("Not able to click on created fund :"+M13FundName1+" so cannot check old watermrking on fund page history pop up");
				sa.assertTrue(false, "Not able to click on created fund :"+M13FundName1+" so cannot check old watermrking on fund page history pop up");
			}
		}else {
			appLog.error("Not able to click fund tab so cannot check old watermarking in fund page alert history pop up");
			sa.assertTrue(false, "Not able to click fund tab so cannot check old watermarking in fund page alert history pop up");
			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc028_5_verifyOldWaterMarkingInUploadedFilesOnInstitutionPageInINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String oldM13Institution1=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String UpdatedM13FundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoIWR);
		String UpdatedLPName=ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M13LimitedPartner1", excelLabel.UpdatedLimitedPartner_NameFormManageInvestor);
		String oldFundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String oldLPName =ExcelUtils.readData("Commitments",excelLabel.Variable_Name, "M13Commitment1", excelLabel.LimitedPartner_Name);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc1, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.SharedPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.CommonPath);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc2, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedInstitution(M13Institution1)) {
				String NOtAvailableWaterMarkingLabels= "My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3<break>IP Address";
				String NOtAvailableLablesInCommon="Investor Name<break>IP Address";
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, null, UpdatedLPName, UpdatedM13FundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder : "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder:"+stdfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInStd+" in folder : "+stdfolderpath);
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address", aa)) {
							appLog.info("IP Address watermarking labels are not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("IP Address watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "IP Address watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
					}else {
						appLog.error("IP Address watermarking is available in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						sa.assertTrue(false, "IP Address watermarking is available in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
					}
						if(aa.size()==1) {
							appLog.info("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
				}else {
					appLog.error("Not able to click on folder ::"+stdfolderpath+" so cannot check old watermarking on Institution page");
					sa.assertTrue(false, "Not able to click on folder ::"+stdfolderpath+" so cannot check old watermarking on Institution page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, UpdatedM13FundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldM13Institution1,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage,Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder: "+Commonfolderpath);
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder: "+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check updated watermarking on Institution Page");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check updated watermarking on Institution Page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, UpdatedM13FundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage,Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address,Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date,UserEmailId is available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in home alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage,Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInShared+" in folder:"+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder: "+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder: "+Sharedfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check old nwatermarking on Institution Page");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check old watermarking on Institution Page");
				}
			}else {
				appLog.error("Not able to click on created Institution "+M13Institution1+" so cannot verify old watermarking");
				sa.assertTrue(false, "Not able to click on created Institution "+M13Institution1+" so cannot verify old watermarking");
			}
		}else {
			appLog.error("Not able to click on Institution tab so cannot verify old watermarking on Institution page");
			sa.assertTrue(false, "Not able to click on Institution tab so cannot verify old watermarking on Institution page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc028_6_verifyOldWaterMarkingInUploadedFilesOnContactPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String UpdatedM13FundName1=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoIWR);
		String UpdatedLPName=ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M13LimitedPartner1", excelLabel.UpdatedLimitedPartner_NameFormManageInvestor);
		String oldFundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String oldLPName =ExcelUtils.readData("Commitments",excelLabel.Variable_Name, "M13Commitment1", excelLabel.LimitedPartner_Name);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc1, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.SharedPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.CommonPath);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc2, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		if(fp.clickOnTab(TabName.ContactTab)) {
			String NOtAvailableWaterMarkingLabels= "My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3<break>IP Address";
			String NOtAvailableLablesInCommon="Investor Name<break>IP Address";
			if(contact.clickOnCreatedContact(M13Contact1FirstName, M13Contact1LastName, null)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, M13Institution1, UpdatedLPName, UpdatedM13FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder : "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder:"+stdfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInStd+" in folder : "+stdfolderpath);
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address", aa)) {
							appLog.info("IP Address watermarking labels are not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("IP Address watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "IP Address watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
					}else {
						appLog.error("IP Address watermarking is available in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						sa.assertTrue(false, "IP Address watermarking is available in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
					}
						if(aa.size()==1) {
							appLog.info("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
				}else {
					appLog.error("Not able to click on folder ::"+stdfolderpath+" so cannot check updated watermarking on Contact page");
					sa.assertTrue(false, "Not able to click on folder ::"+stdfolderpath+" so cannot check updated watermarking on Contact page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, UpdatedM13FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage,Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder: "+Commonfolderpath);
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder: "+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check old watermarking on Contact Page");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check old watermarking on Contact Page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, UpdatedM13FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage,Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address,Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date,UserEmailId is available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in home alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage,Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInShared+" in folder:"+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder: "+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder: "+Sharedfolderpath);
					}

				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check old watermarking on Contact Page");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check old watermarking on Contact Page");
				}
			}else {
				appLog.error("Not able to click on created Contact "+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot verify old watermarking");
				sa.assertTrue(false, "Not able to click on created Contact "+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot verify old watermarking");
			}
		}else {
			appLog.error("Not able to click on Contact tab so cannot verify old watermarking on Contact page");
			sa.assertTrue(false, "Not able to click on Contact tab so cannot verify old watermarking on Contact page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc028_7_verifyOldWaterMarkingInUploadedFilesOnContactAlertPopUpInINV() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String oldInstituteName=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		String oldFundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String oldLPName =ExcelUtils.readData("Commitments",excelLabel.Variable_Name, "M13Commitment1", excelLabel.LimitedPartner_Name);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc2, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(contact.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(M13Contact1FirstName, M13Contact1LastName, null)) {
				switchToFrame(driver, 20,fp.getFrame( PageName.ContactsPage, 10));
				if(click(driver, fp.getAlertHistoryLink(Workspace.InvestorWorkspace,PageName.ContactsPage, 10), "alert histroy link", action.SCROLLANDBOOLEAN)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldInstituteName+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					String NOtAvailableWaterMarkingLabels= "My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3<break>IP Address";
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in contact page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in contact page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in contact page alert grid");
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInStd+" in contact page alert grid");
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in contact page alert grid");
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in contact page alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInStd+" in contact page alert grid");
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInStd+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldInstituteName+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address", aa)) {
							appLog.info("IP Address watermarking labels are not available on file: "+UploadedFileInStd+" in contact page alert grid");
						}else {
							appLog.error("IP Address watermarking labels are available on file: "+UploadedFileInStd+" in contact page alert grid");
							sa.assertTrue(false, "IP Address watermarking labels are available on file: "+UploadedFileInStd+" in contact page alert grid");
						}
					}else {
						appLog.error("IP Address watermarking is available in file: "+UploadedFileInStd+" in contact page alert grid");
						sa.assertTrue(false, "IP Address watermarking is available in file: "+UploadedFileInStd+" in contact page alert grid");
					}
						if(aa.size()==1) {
							appLog.info("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is available on file: "+UploadedFileInStd+" in contact page alert grid");
						}else {
							appLog.error("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in contact page alert grid");
							sa.assertTrue(false, "My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in contact page alert grid");
						}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldInstituteName+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in contact page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in contact page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in contact page alert grid");
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInCommon+" in contact page alert grid");
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in contact page alert grid");
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in contact page alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in contact page alert grid");
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldInstituteName+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					String NOtAvailableLablesInCommon="Investor Name<break>IP Address";
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInCommon+" in contact page alert grid");
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in contact page alert grid");
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in contact page alert grid");
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInCommon+" in contact page alert grid");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in contact page alert grid");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in contact page alert grid");
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in contact page alert grid");
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldInstituteName+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address,Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in contact page alert grid");
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in contact page alert grid");
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in contact page alert grid");
						}
						if(aa.size()==7) {
							appLog.info("Download Date,UserEmailId is available on file: "+UploadedFileInShared+" in contact page alert grid");
						}else {
							appLog.error("Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in contact page alert grid");
							sa.assertTrue(false, "Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in contact page alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInShared+" in contact page alert grid");
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInShared+" in contact page alert grid");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldInstituteName+"<break>"+oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInShared+" in contact page alert grid");
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in contact page alert grid");
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in contact page alert grid");
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInShared+" in contact page alert grid");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in contact page alert grid");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in contact page alert grid");
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in contact page alert grid");
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in contact page alert grid");
					}
					
				}else {
					appLog.error("Not able to click on contact page investor workspace alert history link so cannot check old watermarking");
					sa.assertTrue(false, "Not able to click on contact page investor workspace alert history link so cannot check old watermarking");
				}
				
			}else {
				appLog.error("Not able to click on created contact :"+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot old watermrking on contact page history pop up");
				sa.assertTrue(false, "Not able to click on created contact :"+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot old watermrking on contact page history pop up");
			}
		}else {
			appLog.error("Not able to click Contact tab so cannot check old watermarking in contact page alert history pop up");
			sa.assertTrue(false, "Not able to click contact tab so cannot check old watermarking in contact page alert history pop up");
			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc028_8_verifyOldWaterMarkingInUploadedFilesOnLimitedPartnerPageInINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String oldFundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String UpdatedFundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoIWR);
		String oldLPName =ExcelUtils.readData("Commitments",excelLabel.Variable_Name, "M13Commitment1", excelLabel.LimitedPartner_Name);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc1, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.SharedPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.CommonPath);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc2, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedLP(M13LimitedPartner1)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				String NOtAvailableWaterMarkingLabels= "My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3<break>IP Address";
				String NOtAvailableLablesInCommon="Investor Name<break>IP Address";
				if(fp.verifyFolderPathdummy(stdfolderpath, null, null, UpdatedFundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in folder : "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in folder:"+stdfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInStd+" in folder : "+stdfolderpath);
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address", aa)) {
							appLog.info("IP Address watermarking labels are not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("IP Address watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "IP Address watermarking labels are available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
					}else {
						appLog.error("IP Address watermarking is available in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						sa.assertTrue(false, "IP Address watermarking is available in file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
					}
						if(aa.size()==1) {
							appLog.info("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in folder: "+stdfolderpath);
						}
				}else {
					appLog.error("Not able to click on folder ::"+stdfolderpath+" so cannot check old watermarking on LP page");
					sa.assertTrue(false, "Not able to click on folder ::"+stdfolderpath+" so cannot check old watermarking on LP page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, UpdatedFundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage,Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name, IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in folder: "+Commonfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in folder: "+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder: "+Commonfolderpath);
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in folder: "+Commonfolderpath);
					}
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check old watermarking on LP Page");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check old watermarking on LP Page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, UpdatedFundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage,Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
							appLog.info("My Firm Name,Investor Name, Fund Name,IP Address,Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}
						if(aa.size()==7) {
							appLog.info("Download Date,UserEmailId is available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in home alert grid");
						}
					}else {
						appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
						sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInShared+" in folder: "+Sharedfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage,Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}
						if(aa.size()==2) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInShared+" in folder:"+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in folder: "+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder: "+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in folder: "+Sharedfolderpath);
					}
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check old watermarking on LP Page");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check old watermarking on LP Page");
				}
			}else {
				appLog.error("Not able to click on created Institution "+M13LimitedPartner1+" so cannot verify old watermarking");
				sa.assertTrue(false, "Not able to click on created Institution "+M13LimitedPartner1+" so cannot verify old watermarking");
			}
		}else {
			appLog.error("Not able to click on Institution tab so cannot verify old watermarking on LP page");
			sa.assertTrue(false, "Not able to click on Institution tab so cannot verify old watermarking on LP page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc028_9_verifyOldWaterMarkingAtTargetSide() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		String dependOnTc="M13tc019_2_uploadDocumentInINV";
		String dependOnTc1="M13tc021_onlineImportDocumentInINV";
		String dependOnTc2="M13tc022_1_verifyDocumentOnFundPage";
		String oldFundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoFR);
		String oldLPName =ExcelUtils.readData("Commitments",excelLabel.Variable_Name, "M13Commitment1", excelLabel.LimitedPartner_Name);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOnTc2, excelLabel.Watermarking);
		String UploadedFileInStd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
		String UploadedFileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		String UploadedFileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileCommon);
		String importfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileStandard);
		String importfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileCommon);
		String importfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOnTc1, excelLabel.UploadedFileShared);
		lp.investorLogin(M13Contact1EmailId,adminPassword);
			if(click(driver, ivp.getAllDocumentsTab(20), "all document tab", action.BOOLEAN)) {
				String NOtAvailableWaterMarkingLabels= "My Firm's Name<break>Investor Name<break>Fund Name<break>Label 1<break>Label 2<break>Label 3<break>IP Address";
				String NOtAvailableLablesInCommon="Investor Name<break>IP Address";
				ThreadSleep(10000);
				List<String> aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInStd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab, null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
						appLog.info("My Firm Name,Investor Name, Fund Name,IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInStd+" in all document tab");
					}else {
						appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in all document tab");
						sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInStd+" in all document tab");
					}
					if(aa.size()==7) {
						appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInStd+" in all document tab");
					}else {
						appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in all document tab");
						sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInStd+" in all document tab");
					}
				}else {
					appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInStd+" in all document tab");
					sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInStd+" in all document tab");
				}
				aa=fp.verifyWatermarkingWithoutAssertion(importfileInstd,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab, null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion("IP Address", aa)) {
						appLog.info("IP Address watermarking labels are not available on file: "+UploadedFileInStd+" in all document tab");
					}else {
						appLog.error("IP Address watermarking labels are available on file: "+UploadedFileInStd+" in all document tab");
						sa.assertTrue(false, "IP Address watermarking labels are available on file: "+UploadedFileInStd+" in all document tab");
					}
				}else {
					appLog.error("IP Address watermarking is available in file: "+UploadedFileInStd+" in all document tab");
					sa.assertTrue(false, "IP Address watermarking is available in file: "+UploadedFileInStd+" in all document tab");
				}
				if(aa.size()==1) {
					appLog.info("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is available on file: "+UploadedFileInStd+" in all document tab");
				}else {
					appLog.error("My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in all document tab");
					sa.assertTrue(false, "My Firm Name/Fund Name/Investor Name/Download Date/Label1/Label2/Label3/UserEmailID is not available on file: "+UploadedFileInStd+" in all document tab");
				}
				aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab,null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
						appLog.info("My Firm Name,Investor Name, Fund Name, IP Address, Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInCommon+" in all document tab");
					}else {
						appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in all document tab");
						sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInCommon+" in all document tab");
					}
					if(aa.size()==7) {
						appLog.info("Download Date/UserEmail Id is available on file: "+UploadedFileInCommon+" in all document tab");
					}else {
						appLog.error("Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in all document tab");
						sa.assertTrue(false, "Download Date/UserEmail Id is not available on file: "+UploadedFileInCommon+" in all document tab");
					}
				}else {
					appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in all document tab");
					sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInCommon+" in all document tab");
				}
				aa=fp.verifyWatermarkingWithoutAssertion(importfileInCommon,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab, null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
						appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInCommon+" in all document tab");
					}else {
						appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in all document tab");
						sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInCommon+" in all document tab");
					}
					if(aa.size()==2) {
						appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInCommon+" in all document tab");
					}else {
						appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in all document tab");
						sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInCommon+" in all document tab");
					}
				}else {
					appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in all document tab");
					sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInCommon+" in all document tab");
				}
				aa=fp.verifyWatermarkingWithoutAssertion(UploadedFileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab,null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion(NOtAvailableWaterMarkingLabels, aa)) {
						appLog.info("My Firm Name,Investor Name, Fund Name,IP Address,Label1,Label2, Label3 watermarking labels are not available on file: "+UploadedFileInShared+" in all document tab");
					}else {
						appLog.error("My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in all document tab");
						sa.assertTrue(false, "My Firm Name/Investor Name/Fund Name/IP Address/Label1/Label2/Label3 watermarking labels are available on file: "+UploadedFileInShared+" in all document tab");
					}
					if(aa.size()==7) {
						appLog.info("Download Date,UserEmailId is available on file: "+UploadedFileInShared+" in all document tab");
					}else {
						appLog.error("Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in all document tab");
						sa.assertTrue(false, "Download Date,UserEmailId is not available on file: "+UploadedFileInShared+" in all document tab");
					}
				}else {
					appLog.error("Updated Watermarking is not verified in file: "+UploadedFileInShared+" in all document tab");
					sa.assertTrue(false, "Updated Watermarking is not verified in file: "+UploadedFileInShared+" in all document tab");
				}
				aa=fp.verifyWatermarkingWithoutAssertion(importfileInShared,FolderType.Standard,WatermarkingLabels,Org1FirmName,oldLPName,oldFundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab,null);
				if(!aa.isEmpty()) {
					if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
						appLog.info("Investor Name and IP Address watermarking labels are not available on file: "+importfileInShared+" in all document tab");
					}else {
						appLog.error("Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in all document tab");
						sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+importfileInShared+" in all document tab");
					}
					if(aa.size()==2) {
						appLog.info("MyFirmName,FundName,EmailAddress,Label1,label2,label3 is available on file: "+importfileInShared+" in all document tab");
					}else {
						appLog.error("MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in all document tab");
						sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label2/label3 is not available on file: "+importfileInShared+" in all document tab");
					}
				}else {
					appLog.info("Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in all document tab");
					sa.assertTrue(false, "Investor Name and IP Address Watermarking labels are present in import file : "+importfileInShared+" in all document tab");
				}
			}else {
				appLog.error("Not able to click on all document tab so cannot check old watermarking in all document tab documents");
				sa.assertTrue(false, "Not able to click on all document tab so cannot check old watermarking in all document tab documents");
			}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc030_1_updateDocumentInINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String UpdatedLPName =ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M13LimitedPartner1", excelLabel.UpdatedLimitedPartner_NameFormManageInvestor);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				String folderpath=ExcelUtils.readData("FilePath",0,4,currentlyExecutingTC);
				String INV_docpath="UploadFiles\\Module13\\FileToUploadCRMSide\\UploadFilesCRMSide\\INV\\Standard";
				if(fp.uploadFile(folderpath,M13Institution1+"/"+UpdatedLPName+"<break>"+M13Institution2+"/"+M13LimitedPartner2, INV_docpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
						if(filesName!=null) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,filesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
								
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify update documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify update documents");
					}
				}else {
					appLog.error("File is not updated in "+folderpath);
					sa.assertTrue(false, "File is not updated in "+folderpath);
				}
				String CommonPath=ExcelUtils.readData("FilePath",0,1,currentlyExecutingTC);
				String docpath="UploadFiles\\Module13\\FileToUploadCRMSide\\UploadFilesCRMSide\\INV\\Common";
				if(fp.uploadFile(CommonPath,null, docpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
						if(filesName!=null) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,filesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify update documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify update documents");
					}
				}else {
					appLog.error("File is not uploaded in "+CommonPath);
					sa.assertTrue(false, "File is not uploaded in "+CommonPath);
				}
				String SharedFolderpath=ExcelUtils.readData("FilePath",0,3,currentlyExecutingTC);
				docpath="UploadFiles\\Module13\\FileToUploadCRMSide\\UploadFilesCRMSide\\INV\\Shared";
				if(fp.uploadFile(SharedFolderpath,null, docpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared);
						if(filesName!=null) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,filesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info("All files are verified: "+filesName);
							}
							
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify update documents.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify update documents");
					}
				}else {
					appLog.error("File is not uploaded in "+SharedFolderpath);
					sa.assertTrue(false, "File is not uploaded in "+SharedFolderpath);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M13FundName1+" so cannot update files in investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M13FundName1+" so cannot update files in investor workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot update files in investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot update files in investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc030_2_approveUpdatedDocumentAndVerifyUpdatedDocOnFundPageINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOntc="M13tc030_1_updateDocumentInINV";
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.Watermarking);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.SharedPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.CommonPath);
		String uploadfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileStandard);
		String uploadfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileCommon);
		String uploadfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileShared);
		String UpdatedM13FundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoIWR);
		String UpdatedLPName=ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M13LimitedPartner1", excelLabel.UpdatedLimitedPartner_NameFormManageInvestor);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				String NOtAvailableLablesInCommon="Investor Name<break>IP Address<break>Label 2";
				if(click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 20), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
					if(fp.selectAllPendingFilesToApprove(WorkSpaceAction.UPDATE).isEmpty()) {
						appLog.info("all pending document is approved successfully from manage approvals");
					}else {
						appLog.error("all pending document is not approved from manage approvals");
						sa.assertTrue(false, "all pending document is not approved from manage approvals");
					}
				}else {
					appLog.error("Not able to click on Manage apporval icon so canot approve updated document");
					sa.assertTrue(false, "Not able to click on Manage apporval icon so canot approve updated document");
				}
				if(fp.verifyFolderPathdummy(stdfolderpath, M13Institution1, UpdatedLPName, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedLPName,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address<break>label 2", aa)) {
							appLog.info("IP Address,Label2 watermarking labels are not available on file: "+uploadfileInstd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("IP Address,Label2 watermarking labels are available on file: "+uploadfileInstd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "IP Address,Label2 watermarking labels are available on file: "+uploadfileInstd+" in folder: "+stdfolderpath);
						}
					}else {
						appLog.error("IP Address,Label2 watermarking is available in file: "+uploadfileInstd+" in folder: "+stdfolderpath);
						sa.assertTrue(false, "IP Address,Label2 watermarking is available in file: "+uploadfileInstd+" in folder: "+stdfolderpath);
					}
						if(aa.size()==2) {
							appLog.info("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is available on file: "+uploadfileInstd+" in folder: "+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+uploadfileInstd+" in folder: "+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+uploadfileInstd+" in folder: "+stdfolderpath);
						}
				}else {
					appLog.error("Not able to click on institution Name ::"+M13Institution1+" so cannot check updated watermarking on fundpage");
					sa.assertTrue(false, "Not able to click on institution Name ::"+M13Institution1+" so cannot check updated watermarking on fundpage");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					List<String >aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedLPName,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label 2 and IP Address watermarking labels are not available on file: "+uploadfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+uploadfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+uploadfileInCommon+" in folder: "+Commonfolderpath);
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,Updated FundName,EmailAddress,Label1,label3 is available on file: "+uploadfileInCommon+" in folder: "+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInCommon+" in folder: "+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInCommon+" in folder: "+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+uploadfileInCommon+" in folder: "+Commonfolderpath);
						sa.assertTrue(false, "Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+uploadfileInCommon+" in folder: "+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check updated watermarking on fundpage");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check updated watermarking on fundpage");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedLPName,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundsPage,Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label2 and IP Address watermarking labels are not available on file: "+uploadfileInShared+" in folder: "+Sharedfolderpath);
						}else {
							appLog.error("Investor Name/IP Address/Label2 watermarking labels are available on file: "+uploadfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address/Label2 watermarking labels are available on file: "+uploadfileInShared+" in folder: "+Sharedfolderpath);
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label3 is available on file: "+uploadfileInShared+" in folder:"+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInShared+" in folder: "+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInShared+" in folder: "+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+uploadfileInShared+" in folder: "+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+uploadfileInShared+" in folder: "+Sharedfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check updated watermarking on fundpage");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check updated watermarking on fundpage");
				}
			}else {
				appLog.error("Not able to click created fund "+M13FundName1+" so cannot verify updated watermarking");
				sa.assertTrue(false, "Not able to click created fund "+M13FundName1+" so cannot verify updated watermarking");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot verify updated watermarking");
			sa.assertTrue(false, "Not able to click on fund tab so cannot verify updated watermarking");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc030_3_verifyUpdatedWaterMarkingInUploadedFilesOnFundPageAlertINV() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOntc="M13tc030_1_updateDocumentInINV";
		String oldM13Institution1=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.Watermarking);
		String uploadfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileStandard);
		String uploadfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileCommon);
		String uploadfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileShared);
		String UpdatedM13FundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoIWR);
		String UpdatedLPName=ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M13LimitedPartner1", excelLabel.UpdatedLimitedPartner_NameFormManageInvestor);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M13FundName1)) {
				switchToFrame(driver, 20,fp.getFrame( PageName.FundsPage, 10));
				String NOtAvailableLablesInCommon="Investor Name<break>IP Address<break>Label 2";
				if(click(driver, fp.getAlertHistoryLink(Workspace.InvestorWorkspace,PageName.FundsPage, 10), "alert histroy link", action.SCROLLANDBOOLEAN)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+UpdatedLPName,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address<break>label 2", aa)) {
							appLog.info("IP Address,Label2 watermarking labels are not available on file: "+uploadfileInstd+" in fund page alert history");
						}else {
							appLog.error("IP Address,Label2 watermarking labels are available on file: "+uploadfileInstd+" in fund page alert history");
							sa.assertTrue(false, "IP Address,Label2 watermarking labels are available on file: "+uploadfileInstd+" in fund page alert history");
						}
					}else {
						appLog.error("IP Address,Label2 watermarking is available in file: "+uploadfileInstd+" in fund page alert history");
						sa.assertTrue(false, "IP Address,Label2 watermarking is available in file: "+uploadfileInstd+" in fund page alert history");
					}
						if(aa.size()==2) {
							appLog.info("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is available on file: "+uploadfileInstd+" in fund page alert history");
						}else {
							appLog.error("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+uploadfileInstd+" in fund page alert history");
							sa.assertTrue(false, "My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+uploadfileInstd+" in fund page alert history");
						}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+UpdatedLPName,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label 2 and IP Address watermarking labels are not available on file: "+uploadfileInCommon+" in fund page alert history");
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+uploadfileInCommon+" in fund page alert history");
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+uploadfileInCommon+" in fund page alert history");
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,Updated FundName,EmailAddress,Label1,label3 is available on file: "+uploadfileInCommon+" in fund page alert history");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInCommon+" in fund page alert history");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInCommon+" in fund page alert history");
						}
					}else {
						appLog.info("Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+uploadfileInCommon+" in fund page alert history");
						sa.assertTrue(false, "Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+uploadfileInCommon+" in fund page alert history");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+UpdatedLPName,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.FundPageAlertPopUp,Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label2 and IP Address watermarking labels are not available on file: "+uploadfileInShared+" in fund page alert history");
						}else {
							appLog.error("Investor Name/IP Address/Label2 watermarking labels are available on file: "+uploadfileInShared+" in fund page alert history");
							sa.assertTrue(false, "Investor Name/IP Address/Label2 watermarking labels are available on file: "+uploadfileInShared+" in fund page alert history");
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label3 is available on file: "+uploadfileInShared+" in fund page alert history");
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInShared+" in fund page alert history");
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInShared+" in fund page alert history");
						}
					}else {
						appLog.info("Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+uploadfileInShared+" in fund page alert history");
						sa.assertTrue(false, "Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+uploadfileInShared+" in fund page alert history");
					}
					
				}else {
					appLog.error("Not able to click on fund page fundraising workspace alert history link so cannot check updated watermarking");
					sa.assertTrue(false, "Not able to click on fund page fundraising workspace alert history link so cannot check updated watermarking");
				}
				
			}else {
				appLog.error("Not able to click on created fund :"+M13FundName1+" so cannot verify updated watermrking on fund page history pop up");
				sa.assertTrue(false, "Not able to click on created fund :"+M13FundName1+" so cannot verify updated watermrking on fund page history pop up");
			}
		}else {
			appLog.error("Not able to click fund tab so cannot check updated watermarking in fund page alert history pop up");
			sa.assertTrue(false, "Not able to click fund tab so cannot check updated watermarking in fund page alert history pop up");
			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc030_4_verifyUpdatedWaterMarkingInUploadedFilesOnContactPageINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String dependOntc="M13tc030_1_updateDocumentInINV";
		String oldM13Institution1=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M13Contact1", excelLabel.Institutions_Name);
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.Watermarking);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.SharedPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.CommonPath);
		String uploadfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileStandard);
		String uploadfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileCommon);
		String uploadfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileShared);
		String UpdatedM13FundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoIWR);
		String UpdatedLPName=ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M13LimitedPartner1", excelLabel.UpdatedLimitedPartner_NameFormManageInvestor);
		if(fp.clickOnTab(TabName.ContactTab)) {
			String NOtAvailableLablesInCommon="Investor Name<break>IP Address<break>Label 2";
			if(contact.clickOnCreatedContact(M13Contact1FirstName, M13Contact1LastName, null)) {
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, M13Institution1, UpdatedLPName, UpdatedM13FundName, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedLPName,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address<break>label 2", aa)) {
							appLog.info("IP Address, Label2 watermarking labels are not available on file: "+uploadfileInstd+" in folder :"+stdfolderpath);
						}else {
							appLog.error("IP Address,Label2 watermarking labels are available on file: "+uploadfileInstd+" in folder :"+stdfolderpath);
							sa.assertTrue(false, "IP Address,Label2 watermarking labels are available on file: "+uploadfileInstd+" in folder :"+stdfolderpath);
						}
					}else {
						appLog.error("IP Address,label2 watermarking is available in file: "+uploadfileInstd+" in folder :"+stdfolderpath);
						sa.assertTrue(false, "IP Address,label2 watermarking is available in file: "+uploadfileInstd+" in folder :"+stdfolderpath);
					}
						if(aa.size()==2) {
							appLog.info("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is available on file: "+uploadfileInstd+" in folder :"+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+uploadfileInstd+" in folder :"+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+uploadfileInstd+" in folder :"+stdfolderpath);
						}
				}else {
					appLog.error("Not able to click on folder ::"+stdfolderpath+" so cannot check updated watermarking on Contact page");
					sa.assertTrue(false, "Not able to click on folder ::"+stdfolderpath+" so cannot check updated watermarking on Contact page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, UpdatedM13FundName, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedLPName,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label 2 and IP Address watermarking labels are not available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,Updated FundName,EmailAddress,Label1,label3 is available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+uploadfileInCommon+" in folder :"+Commonfolderpath);
						sa.assertTrue(false, "Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+uploadfileInCommon+" in folder :"+Commonfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check updated watermarking on Contact Page");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check updated watermarking on Contact Page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, UpdatedM13FundName, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedLPName,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactsPage,Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label2 and IP Address watermarking labels are not available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
						}else {
							appLog.error("Investor Name/IP Address/Label2 watermarking labels are available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address/Label2 watermarking labels are available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label3 is available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+uploadfileInShared+" in folder :"+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+uploadfileInShared+" in folder :"+Sharedfolderpath);
					}
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check watermarking on Contact Page");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check watermarking on Contact Page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
				if(click(driver, fp.getAlertHistoryLink(Workspace.InvestorWorkspace,PageName.ContactsPage, 10), "alert histroy link", action.SCROLLANDBOOLEAN)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+UpdatedLPName,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp, null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address<break>label 2", aa)) {
							appLog.info("IP Address,Label2 watermarking labels are not available on file: "+uploadfileInstd+" in contact alert history");
						}else {
							appLog.error("IP Address,label2 watermarking labels are available on file: "+uploadfileInstd+" in contact alert history");
							sa.assertTrue(false, "IP Address,label2 watermarking labels are available on file: "+uploadfileInstd+" in contact alert history");
						}
					}else {
						appLog.error("IP Address,label2 watermarking is available in file: "+uploadfileInstd+" in contact alert history");
						sa.assertTrue(false, "IP Address,label2 watermarking is available in file: "+uploadfileInstd+" in contact alert history");
					}
						if(aa.size()==2) {
							appLog.info("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is available on file: "+uploadfileInstd+" in contact alert history");
						}else {
							appLog.error("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+uploadfileInstd+" in contact alert history");
							sa.assertTrue(false, "My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+uploadfileInstd+" in contact alert history");
						}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					 aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+UpdatedLPName,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label 2 and IP Address watermarking labels are not available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,Updated FundName,EmailAddress,Label1,label3 is available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+uploadfileInCommon+" in folder :"+Commonfolderpath);
						sa.assertTrue(false, "Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+uploadfileInCommon+" in folder :"+Commonfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.ContactsPage, 20));
					aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,oldM13Institution1+"<break>"+UpdatedLPName,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.ContactPageAlertPopUp,null);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label2 and IP Address watermarking labels are not available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
						}else {
							appLog.error("Investor Name/IP Address/Label2 watermarking labels are available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address/Label2 watermarking labels are available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label3 is available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+uploadfileInShared+" in folder :"+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+uploadfileInShared+" in folder :"+Sharedfolderpath);
					}
					
				}else {
					appLog.error("Not able to click on contact page fundraising workspace alert history link so cannot check updated watermarking");
					sa.assertTrue(false, "Not able to click on contact page fundraising workspace alert history link so cannot check updated watermarking");
				}
			}else {
				appLog.error("Not able to click on created Contact "+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot verify updated watermarking");
				sa.assertTrue(false, "Not able to click on created Contact "+M13Contact1FirstName+" "+M13Contact1LastName+" so cannot verify updated watermarking");
			}
		}else {
			appLog.error("Not able to click on Contact tab so cannot verify watermarking on Contact page");
			sa.assertTrue(false, "Not able to click on Contact tab so cannot verify watermarking on Contact page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc030_5_verifyUpdatedWaterMarkingInUploadedFilesOnInstitutionPageINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String dependOntc="M13tc030_1_updateDocumentInINV";
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.Watermarking);
		String stdfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.StandardPath);
		String Sharedfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.SharedPath);
		String Commonfolderpath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.CommonPath);
		String uploadfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileStandard);
		String uploadfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileCommon);
		String uploadfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileShared);
		String UpdatedM13FundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoIWR);
		String UpdatedLPName=ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M13LimitedPartner1", excelLabel.UpdatedLimitedPartner_NameFormManageInvestor);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedInstitution(M13Institution1)) {
				String NOtAvailableLablesInCommon="Investor Name<break>IP Address<break>Label 2";
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(stdfolderpath, null, UpdatedLPName, UpdatedM13FundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedLPName,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion("IP Address<break>label 2", aa)) {
							appLog.info("IP Address,Label2 watermarking labels are not available on file: "+uploadfileInstd+" in folder :"+stdfolderpath);
						}else {
							appLog.error("IP Address,Label2 watermarking labels are available on file: "+uploadfileInstd+" in folder :"+stdfolderpath);
							sa.assertTrue(false, "IP Address,Label2 watermarking labels are available on file: "+uploadfileInstd+" in folder :"+stdfolderpath);
						}
					}else {
						appLog.error("IP Address,label2 watermarking is available in file: "+uploadfileInstd+" in folder :"+stdfolderpath);
						sa.assertTrue(false, "IP Address,label2 watermarking is available in file: "+uploadfileInstd+" in folder :"+stdfolderpath);
					}
						if(aa.size()==2) {
							appLog.info("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is available on file: "+uploadfileInstd+" in folder :"+stdfolderpath);
						}else {
							appLog.error("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+uploadfileInstd+" in folder :"+stdfolderpath);
							sa.assertTrue(false, "My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+uploadfileInstd+" in folder :"+stdfolderpath);
						}
				}else {
					appLog.error("Not able to click on folder ::"+stdfolderpath+" so cannot check updated watermarking on Institution page");
					sa.assertTrue(false, "Not able to click on folder ::"+stdfolderpath+" so cannot check updated watermarking on Institution page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Commonfolderpath, null, null, UpdatedM13FundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedLPName,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage, Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label 2 and IP Address watermarking labels are not available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
						}else {
							appLog.error("Investor Name/IP Address watermarking labels are available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,Updated FundName,EmailAddress,Label1,label3 is available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInCommon+" in folder :"+Commonfolderpath);
						}
					}else {
						appLog.info("Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+uploadfileInCommon+" in folder :"+Commonfolderpath);
						sa.assertTrue(false, "Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+uploadfileInCommon+" in folder :"+Commonfolderpath);
					}
				}else {
					appLog.error("Not able to click on Common Folder ::"+Commonfolderpath+" so cannot check updated watermarking on Institution Page");
					sa.assertTrue(false, "Not able to click on Common folder ::"+Commonfolderpath+" so cannot check updated watermarking on Institution Page");
				}
				switchToFrame(driver, 30,fp.getFrame( PageName.InstitutionsPage, 20));
				if(fp.verifyFolderPathdummy(Sharedfolderpath, null, null, UpdatedM13FundName, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					List<String> aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedLPName,UpdatedM13FundName,CRMUser1EmailID,getSystemDate("MM-dd-YYYY"), PageName.InstitutionsPage,Workspace.InvestorWorkspace);
					if(!aa.isEmpty()) {
						if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
							appLog.info("Investor Name,Label2 and IP Address watermarking labels are not available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
						}else {
							appLog.error("Investor Name/IP Address/Label2 watermarking labels are available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
							sa.assertTrue(false, "Investor Name/IP Address/Label2 watermarking labels are available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
						}
						if(aa.size()==3) {
							appLog.info("MyFirmName,FundName,EmailAddress,Label1,label3 is available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
						}else {
							appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
							sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInShared+" in folder :"+Sharedfolderpath);
						}
					}else {
						appLog.info("Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+uploadfileInShared+" in folder :"+Sharedfolderpath);
						sa.assertTrue(false, "Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+uploadfileInShared+" in folder :"+Sharedfolderpath);
					}
				}else {
					appLog.error("Not able to click on shared Folder ::"+Sharedfolderpath+" so cannot check watermarking on Institution Page");
					sa.assertTrue(false, "Not able to click on shared folder ::"+Sharedfolderpath+" so cannot check watermarking on Institution Page");
				}
			}else {
				appLog.error("Not able to click on created Institution "+M13Institution1+" so cannot verify updated watermarking");
				sa.assertTrue(false, "Not able to click on created Institution "+M13Institution1+" so cannot verify updated watermarking");
			}
		}else {
			appLog.error("Not able to click on Institution tab so cannot verify updated watermarking on Institution page");
			sa.assertTrue(false, "Not able to click on Institution tab so cannot verify updated watermarking on Institution page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc030_6_VerifyUpdatedWaterMarkingTargetSide() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		String dependOntc="M13tc030_1_updateDocumentInINV";
		String WatermarkingLabels=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.Watermarking);
		String uploadfileInstd=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileStandard);
		String uploadfileInCommon=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileCommon);
		String uploadfileInShared=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, dependOntc, excelLabel.UploadedFileShared);
		String UpdatedM13FundName=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M13Fund1", excelLabel.UpdateFund_NameFromUpdateInfoIWR);
		String UpdatedLPName=ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M13LimitedPartner1", excelLabel.UpdatedLimitedPartner_NameFormManageInvestor);
		lp.investorLogin(M13Contact1EmailId,adminPassword);
		String NOtAvailableLablesInCommon="Investor Name<break>IP Address<break>Label 2";
		if(click(driver, ivp.getAllDocumentsTab(20), "all document tab", action.BOOLEAN)) {
			List<String> aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInstd,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedLPName,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab, null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion("IP Address<break>label 2", aa)) {
					appLog.info("IP Address,Label2 watermarking labels are not available on file: "+uploadfileInstd+" in all document page");
				}else {
					appLog.error("IP Address,Label2 watermarking labels are available on file: "+uploadfileInstd+" in all document page");
					sa.assertTrue(false, "IP Address,Label2 watermarking labels are available on file: "+uploadfileInstd+" in all document page");
				}
			}else {
				appLog.error("IP Address,Label2 watermarking is available in file: "+uploadfileInstd+" in all document page");
				sa.assertTrue(false, "IP Address,Label2 watermarking is available in file: "+uploadfileInstd+" in all document page");
			}
				if(aa.size()==2) {
					appLog.info("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is available on file: "+uploadfileInstd+" in all document page");
				}else {
					appLog.error("My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+uploadfileInstd+" in all document page");
					sa.assertTrue(false, "My Firm Name/Update Fund Name/Updated Investor Name/Download Date/Updated Label1/Label3/UserEmailID is not available on file: "+uploadfileInstd+" in all document page");
				}
			aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInCommon,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedLPName,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab,null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
					appLog.info("Investor Name,Label 2 and IP Address watermarking labels are not available on file: "+uploadfileInCommon+" in all document page");
				}else {
					appLog.error("Investor Name/IP Address watermarking labels are available on file: "+uploadfileInCommon+" in all document page");
					sa.assertTrue(false, "Investor Name/IP Address watermarking labels are available on file: "+uploadfileInCommon+" in all document page");
				}
				if(aa.size()==3) {
					appLog.info("MyFirmName,Updated FundName,EmailAddress,Label1,label3 is available on file: "+uploadfileInCommon+" in all document page");
				}else {
					appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInCommon+" in all document page");
					sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInCommon+" in all document page");
				}
			}else {
				appLog.info("Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+uploadfileInCommon+" in all document page");
				sa.assertTrue(false, "Investor Name,Label 2 and IP Address Watermarking labels are present in import file : "+uploadfileInCommon+" in all document page");
			}
			switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
			aa=fp.verifyWatermarkingWithoutAssertion(uploadfileInShared,FolderType.Standard,WatermarkingLabels,Org1UpdatedFirmName,UpdatedLPName,UpdatedM13FundName,M13Contact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab,null);
			if(!aa.isEmpty()) {
				if(compareMultipleListWithoutAssertion(NOtAvailableLablesInCommon, aa)) {
					appLog.info("Investor Name,Label2 and IP Address watermarking labels are not available on file: "+uploadfileInShared+" in all document page");
				}else {
					appLog.error("Investor Name/IP Address/Label2 watermarking labels are available on file: "+uploadfileInShared+" in fund page alert history");
					sa.assertTrue(false, "Investor Name/IP Address/Label2 watermarking labels are available on file: "+uploadfileInShared+" in all document page");
				}
				if(aa.size()==3) {
					appLog.info("MyFirmName,FundName,EmailAddress,Label1,label3 is available on file: "+uploadfileInShared+" in all document page");
				}else {
					appLog.error("MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInShared+" in all document page");
					sa.assertTrue(false, "MyFirmName/FundName/EmailAddress/Label1/label3 is not available on file: "+uploadfileInShared+" in all document page");
				}
			}else {
				appLog.info("Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+uploadfileInShared+" in all document page");
				sa.assertTrue(false, "Investor Name,Label2 and IP Address Watermarking labels are present in import file : "+uploadfileInShared+" in all document page");
			}
		}else {
			appLog.error("Not able to click on all document tab so cannot check updated watermarking in all document");
			sa.assertTrue(false, "Not able to click on all document tab so cannot check updated watermarking in all document");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M13tc031_PostConditionForAll() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		lp.postCondition().assertAll();
		lp.CRMlogout();

	}
}
