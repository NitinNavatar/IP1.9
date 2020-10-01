/**
 * 
 */

package com.navatar.scripts;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.android.dx.io.instructions.SparseSwitchPayloadDecodedInstruction;
import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib;
import com.navatar.generic.CommonVariables;
import com.navatar.generic.EmailLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.CommonLib.EnableDisable;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.SortOrder;
import com.navatar.generic.CommonLib.TabName;
import com.navatar.generic.CommonLib.WorkSpaceAction;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.accessType;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.pageObjects.AllFirmsPageBusinesslayer;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.BasePageErrorMessage;
import com.navatar.pageObjects.CommitmentPageBusinessLayer;
import com.navatar.pageObjects.ContactPageBusinessLayer;
import com.navatar.pageObjects.ContactPageErrorMessage;
import com.navatar.pageObjects.FundRaisingPageBusinessLayer;
import com.navatar.pageObjects.FundsPageBusinessLayer;
import com.navatar.pageObjects.FundsPageErrorMessage;
import com.navatar.pageObjects.HomePageBusineesLayer;
import com.navatar.pageObjects.InstitutionPageBusinessLayer;
import com.navatar.pageObjects.InvestorFirmPageBusinesslayer;
import com.navatar.pageObjects.InvestorProfileBusinessLayer;
import com.navatar.pageObjects.LoginErrorPage;
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.NIMPageBusinessLayer;
import com.navatar.pageObjects.PartnershipPageBusinessLayer;
import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.CommonVariables.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Parul Singh
 *
 */
public class Module18 extends BaseLib {

	@Test
	public void M18tc001_CreatePreconditionData() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		if(lp.preCondition(superAdminUserName, CRMUser1FirstName+" "+CRMUser1LastName, CRMUser1EmailID, EnableDisable.Disable, EnableDisable.Disable, accessType.InternalUserAccess)){
			appLog.info("Provided admin user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
		} else {
			appLog.error("Not able to provide admin user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
			saa.assertTrue(false,"Not able to provide admin user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
		}
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String instutionName = null;
		for (int i = 0; i < 2; i++) {
			instutionName = ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M18Institution" + (i + 1),
					excelLabel.Institutions_Name);
			if (bp.clickOnTab(TabName.InstituitonsTab)) {
				if (ip.createInstitution(instutionName)) {

				} else {
					appLog.error("Not able to create institution: " + instutionName);
					saa.assertTrue(false, "Not able to create institution: " + instutionName);
				}
			} else {
				appLog.error("Not able to click on institution tab so cannot create institution: " + instutionName);
				saa.assertTrue(false,
						"Not able to click on institution tab so cannot create institution: " + instutionName);
			}
		}
		for (int i = 0; i < 2; i++) {
			String fundName = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M18Fund" + (i + 1),
					excelLabel.Fund_Name);
			if (bp.clickOnTab(TabName.FundsTab)) {
				String fundType = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M18Fund" + (i + 1),
						excelLabel.Fund_Type);
				String investmentCategory = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M18Fund" + (i + 1),
						excelLabel.Fund_InvestmentCategory);
				if (fp.createFund(fundName, fundType, investmentCategory)) {
					appLog.info("fund is created: " + fundName);
				} else {
					appLog.error("Not able to create fund: " + fundName);
					saa.assertTrue(false, "Not able to create fund: " + fundName);
				}
			} else {
				appLog.error("Not able to click on fund tab so cannot create fund: " + fundName);
				saa.assertTrue(false, "Not able to click on fund tab so cannot create fund: " + fundName);
			}
		}
		for (int i = 0; i < 3; i++) {
			String emailId = cp.generateRandomEmailId();
			instutionName = ExcelUtils.readData("Contacts", excelLabel.Variable_Name, "M18Contact" + (i + 1),
					excelLabel.Institutions_Name);
			String ContactFirstName = ExcelUtils.readData("Contacts", excelLabel.Variable_Name, "M18Contact" + (i + 1),
					excelLabel.Contact_FirstName);
			String ContactLastName = ExcelUtils.readData("Contacts", excelLabel.Variable_Name, "M18Contact" + (i + 1),
					excelLabel.Contact_LastName);
			if (bp.clickOnTab(TabName.ContactTab)) {
				if (cp.createContact(ContactFirstName, ContactLastName, instutionName, emailId)) {
					appLog.info("contact is created: " + ContactFirstName + " " + ContactLastName);
					if (emailId != "") {
						ExcelUtils.writeData(emailId, "Contacts", excelLabel.Variable_Name, "M18Contact" + (i + 1),
								excelLabel.Contact_EmailId);
					}
				} else {
					appLog.error("Not able to create contact: " + ContactFirstName + " " + ContactLastName);
					saa.assertTrue(false, "Not able to create contact: " + ContactFirstName + " " + ContactLastName);
				}
			}
		}
		for (int i = 0; i < 2; i++) {
			String fundraisingName = ExcelUtils.readData("Fundraisings", excelLabel.Variable_Name,
					"M18FundRaising" + (i + 1), excelLabel.FundRaising_Name);
			if (bp.clickOnTab(TabName.FundraisingsTab)) {
				String fundName = ExcelUtils.readData("Fundraisings", excelLabel.Variable_Name,
						"M18FundRaising" + (i + 1), excelLabel.Fund_Name);
				instutionName = ExcelUtils.readData("Fundraisings", excelLabel.Variable_Name,
						"M18FundRaising" + (i + 1), excelLabel.Institutions_Name);
				if (frp.createFundRaising(fundraisingName, fundName, instutionName)) {
					appLog.info("fundraising is created : " + fundraisingName);
				} else {
					appLog.error("Not able to create fundraising: " + fundraisingName);
					saa.assertTrue(false, "Not able to create fundraising: " + fundraisingName);
				}
			} else {
				appLog.error("Not able to click on fundraising tab so cannot create fundraising: " + fundraisingName);
				saa.assertTrue(false,
						"Not able to click on fundraising tab so cannot create fundraising: " + fundraisingName);
			}
		}
		for (int i = 0; i < 2; i++) {
			String lpName = ExcelUtils.readData("Limited Partner", excelLabel.Variable_Name,
					"M18LimitedPartner" + (i + 1), excelLabel.LimitedPartner_Name);
			if (bp.clickOnTab(TabName.InstituitonsTab)) {
				instutionName = ExcelUtils.readData("Institutions", excelLabel.Variable_Name,
						"M18Institution" + (i + 1), excelLabel.Institutions_Name);
				if (ip.createLimitedPartner(lpName, instutionName)) {
					appLog.info("limited partner is created: " + lpName);
				} else {
					appLog.error("Not able to create limited partner: " + lpName);
					saa.assertTrue(false, "Not able to create limited partner: " + lpName);
				}
			} else {
				appLog.error("Not able to click on institution tab so cannot create limite partner: " + lpName);
				saa.assertTrue(false,
						"Not able to click on institution tab so cannot create limite partner: " + lpName);
			}
		}
		String partnershipName="";
		for (int i = 0; i < 2; i++) {
		 partnershipName = ExcelUtils.readData("Partnerships", excelLabel.Variable_Name, "M18Partnership"+(i+1),
				excelLabel.PartnerShip_Name);
		if (bp.clickOnTab(TabName.PartnershipsTab)) {
			String fundName = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M18Fund"+(i+1), excelLabel.Fund_Name);
			if (pp.createPartnership(partnershipName, fundName)) {
				appLog.info("partnership is created: " + partnershipName);
			} else {
				appLog.error("Not able to create partnership: " + partnershipName);
				saa.assertTrue(false, "Not able to create partnership: " + partnershipName);
			}
		} else {
			appLog.error("Not able to click on partnership tab so cannot create partnership: " + partnershipName);
			saa.assertTrue(false,
					"Not able to click on partnership tab so cannot create partnership: " + partnershipName);
		}
		}
		for (int i = 0; i < 2; i++) {
			String lpName;
			partnershipName = null;
			if (i == 0) {
				lpName = ExcelUtils.readData("Limited Partner", excelLabel.Variable_Name, "M18LimitedPartner1",
						excelLabel.LimitedPartner_Name);
			} else {
				lpName = ExcelUtils.readData("Limited Partner", excelLabel.Variable_Name, "M18LimitedPartner2",
						excelLabel.LimitedPartner_Name);
			}
			if (bp.clickOnTab(TabName.CommitmentsTab)) {
				partnershipName = ExcelUtils.readData("Partnerships", excelLabel.Variable_Name, "M18Partnership"+(i+1),
						excelLabel.PartnerShip_Name);

				if (cmp.createCommitment(lpName, partnershipName, "M18Commitment" + (i + 1), null)) {
					appLog.info("commitment is created successfully");
				} else {
					appLog.error("Not able to create commitment for limited partner: " + lpName
							+ " and partnership Name: " + partnershipName);
					saa.assertTrue(false, "Not able to create commitment for limited partner: " + lpName
							+ " and partnership Name: " + partnershipName);
				}
			} else {
				appLog.error("Not able to click on commitment tab so cannot create committment for limite partner: "
						+ lpName + " and partnership Name: " + partnershipName);
				saa.assertTrue(false,
						"Not able to click on commitment tab so cannot create committment for limite partner: " + lpName
								+ " and partnership Name: " + partnershipName);
			}
		}
		if (nim.getMyProfileFistNameAndLastNameAndFirmName("AdminUser")) {
			appLog.info("CRM User first,last name and firm name successfully write in excel");
		} else {
			appLog.error("Not able to write CRM User first,last name and firm profile in excel");
			saa.assertTrue(false, "Not able to write CRM User first,last name and firm profile in excel");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M18tc002_CreationOfFundraisingWorkSpace(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] step1of3 = {ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_Size), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_VintageYear),
				ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_Contact), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_Phone),
				ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_Email), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_Description) };
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M18FundName1)) {
				if (fp.buildWorkspace(step1of3, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null,
						M18Institution1 , Workspace.FundraisingWorkspace, 60)) {
					appLog.info("Fundraising Workspace is created successfully");
				} else {
					appLog.info("Fundraising workspace is not created successfully");
					saa.assertTrue(false, " Fundraising workspace is not craeted successfully");
				}				
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab so we cannot create workspace");
			saa.assertTrue(false, "Not able to click on Funds tab so we cannot create workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();		
	}
	
	@Test
	public void M18tc003_GiveContactAccessAndSendInvitationEmailToCntactsAndVerifyContactDetailPage(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M18FundName1)) {				
				if (fp.inviteContact(environment, mode, M18Institution1, M18Contact1EmailId, null, FolderType.Standard,
						"Upload", "Yes", "Yes", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M18Contact1FirstName + " "
							+ M18Contact1LastName + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M18Contact1FirstName + " "
							+ M18Contact1LastName + "'.");
					saa.assertTrue(false, "Not able to provide access to contact '" + M18Contact1FirstName + " "
							+ M18Contact1LastName + "'.");
				}
				if (fp.inviteContact(environment, mode, M18Institution1, M18Contact2EmailId, null, FolderType.Standard,
						"Upload", "Yes", "Yes", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M18Contact2FirstName + " "
							+ M18Contact2LastName + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M18Contact2FirstName + " "
							+ M18Contact2LastName + "'.");
					saa.assertTrue(false, "Not able to provide access to contact '" + M18Contact2FirstName + " "
							+ M18Contact2LastName + "'.");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab so we cannot create workspace");
			saa.assertTrue(false, "Not able to click on Funds tab so we cannot create workspace");
		}
		switchToDefaultContent(driver);
		if(bp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact1FirstName, M18Contact1LastName, null)){
				switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+" Section view");
				mouseOverOperation(driver, cp.getResetPasswordInactiveButton(Workspace.FundraisingWorkspace,60));
				String text=cp.getResetPasswordInactiveButton(Workspace.FundraisingWorkspace,60).getAttribute("title");
				if(text.equalsIgnoreCase(ContactPageErrorMessage.resetPasswordToolTipMessage)){
					appLog.info("Error messsage is verified");
					}else{
					appLog.error("Error mesage is not verified");
					sa.assertTrue(false, "Error mesage is not verified");
				}
			}else{
				appLog.equals("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}		
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();			
	}
		
	@Test
	public void M18tc004_RegisterContactAndVerifyInvestorRegistrationPage(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);	
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		boolean invitedthroughLink1 = false;
		if (ExcelUtils.readData("Contacts", excelLabel.Variable_Name, "M18Contact1", excelLabel.Registered)
				.equalsIgnoreCase("Not Registered")) {
			try {
				investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail", gmailUserName, gmailPassword,
						CRMUser1EmailID, M18Contact1EmailId);
				appLog.info("Investor Registration Link: " + investorRegLink);
			} catch (InterruptedException e) {
				appLog.info("Invitation mail not found.");
				e.printStackTrace();
			}
			if (investorRegLink != null) {
				try{
				driver.get(investorRegLink);
				SoftAssert	saa=bp.verifyInvestorUI(M18Contact1FirstName, M18Contact1LastName,M18Contact1FirstName+M18Contact1LastName,M18Contact1EmailId,M18Institution1,adminPassword);
				sa.combineAssertions(saa);			   
				ExcelUtils.writeData("Registered", "Contacts", excelLabel.Variable_Name, "M18Contact1",
							excelLabel.Registered);
				invitedthroughLink1=true;
				} catch(Throwable th)  {
					driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
					appLog.info("Register Target URL through Direct URL..");
					if (bp.targetRegistration(M18Contact1FirstName, M18Contact1LastName, M18Contact1EmailId,
							M18Institution1, adminPassword)) {
						appLog.info("Investor is registered successfully: " + M18Contact1FirstName + " "
								+ M18Contact1LastName);
						ExcelUtils.writeData("Registered", "Contacts", excelLabel.Variable_Name, "M18Contact1",
								excelLabel.Registered);
					} else {
						appLog.error("Investor is not Registered through Invited Link: " + M18Contact1FirstName + " "
								+ M18Contact1LastName);
						sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M18Contact1FirstName
								+ " " + M18Contact1LastName);
					}
				}
			} else {
				driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
				appLog.info("Register Target URL through Direct URL..");
				if (bp.targetRegistration(M18Contact1FirstName, M18Contact1FirstName, M18Contact1EmailId,
						M18Institution1, adminPassword)) {
					appLog.info(
							"Investor is registered successfully: " + M18Contact1FirstName + " " + M18Contact1LastName);
					ExcelUtils.writeData("Registered", "Contacts", excelLabel.Variable_Name, "M18Contact1",
							excelLabel.Registered);
				} else {
					appLog.error("Investor is not Registered through Invited Link: " + M18Contact1FirstName + " "
							+ M18Contact1LastName);
					sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M18Contact1FirstName
							+ " " + M18Contact1LastName);
				}
			}
			if(invitedthroughLink1){
				String firmName = getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Firm name dropdown",
						"text");
				if (firmName.equalsIgnoreCase(Org1FirmName)) {
					appLog.info("Firm Page Is opened");
				} else {
					appLog.info("Firm Page is not opened");
					sa.assertTrue(false, "Firm Page is not opened");
				}
			}			
		} else {
			appLog.info("investor " + M18Contact1FirstName + " " + M18Contact1LastName + " is already Registered.");
			sa.assertTrue(false,
					"investor " + M18Contact1FirstName + " " + M18Contact1LastName + " is already Registered.");
		}
		lp.investorLogout();
		sa.assertAll();		
	}
		
	@Test
	public void M18tc005_VerifyContactDetailPageAndResetPasswordPopUpUIAfterInvestorContactRegisteredSuccessfully(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact1FirstName, M18Contact1LastName, null)){
				switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+" Section view");
				mouseOverOperation(driver, cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace,60));
				String text=cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace,60).getAttribute("title");
				if(text.equalsIgnoreCase("Reset Password")){
					appLog.info("messsage is verified");
				}else{
					appLog.error("mesage is not verified");
					sa.assertTrue(false, "mesage is not verified");
				}
				if (click(driver, cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace,60), "Reset Password Active button",
						action.SCROLLANDBOOLEAN)) {
				SoftAssert saa = cp.verifyResetPasswordPopupUI(M18Contact1FirstName, M18Contact1LastName, M18Contact1EmailId);
				sa.combineAssertions(saa);
				} else {
					appLog.error("Not able to click on reset password active button");
					sa.assertTrue(false, "Not able to click on reset password active button");
				}
				if (click(driver, cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace,60), "Reset Password Active button",
						action.SCROLLANDBOOLEAN)) {
					if(click(driver, cp.getResetPasswordPopupCloseIcon(60), "Close icon", action.SCROLLANDBOOLEAN)){
						appLog.info("Clicked on close icon");
						ThreadSleep(1000);
						if(cp.getResetPasswordpopupHeader(10)==null){
						appLog.info("Reset Password poup get closed successfully");
						}else{
							appLog.error("Reset Password popup does not get closed successfully");
							sa.assertTrue(false, "Reset Password popup does not get closed successfully");
						}			
					}else{
						appLog.error("Not able to click on Close icon");
						sa.assertTrue(false, "Not bale ot click on Close icon");
					}
				}else{
					appLog.error("Not able to click on reset password link for contact "+M18Contact1FirstName+" "+M18Contact1LastName);
					sa.assertTrue(false, "Not able to click on reset password link for contact"+M18Contact1FirstName+" "+M18Contact1LastName);
				}				
			}else{
				appLog.error("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
			switchToDefaultContent(driver);
			if(bp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact2FirstName, M18Contact2LastName, null)){
				switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+" Section view");
				if(cp.getResetPasswordInactiveButton(Workspace.FundraisingWorkspace,60)!=null){
				appLog.info("Reset password inactive button is displaying");
				mouseOverOperation(driver, cp.getResetPasswordInactiveButton(Workspace.FundraisingWorkspace,60));
				String text=cp.getResetPasswordInactiveButton(Workspace.FundraisingWorkspace,60).getAttribute("title");
				if(text.equalsIgnoreCase(ContactPageErrorMessage.resetPasswordToolTipMessage)){
					appLog.info("Error messsage is verified");
					switchToDefaultContent(driver);
				}else{
					appLog.error("Error mesage is not verified");
					sa.assertTrue(false, "Error mesage is not verified");
				}	
				}else{
					appLog.error("Reset password inactive button is not displaying");
					sa.assertTrue(false, "Reset password inactive button is not displaying");
				}
			}else{
				appLog.error("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();		
	}
		
	@Test
	public void M18tc006_CheckTheUIOfForgotPasswordPageErrorMessageAndClickHereLinkInErrorMessagae(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		String windowID;
     	driver.get(InvestorURL);
		if(lp.getResetPasswordLink(60)!=null){
			appLog.info("Reset Password link is displaying");
			if(lp.getResetPasswordEmailText(60).getText().equalsIgnoreCase("Email (Username)")){
				appLog.info("Email text is displaying  just above the email textbox");
			}else{
				appLog.error("Email text is not displaying  just above the email textbox");
				sa.assertTrue(false, "Email text is not displaying  just above the email textbox");
			}
			 windowID= driver.getWindowHandle();
			if (click(driver, lp.getResetPasswordLink(60), "Reset Password Link",action.BOOLEAN)) {
				if (windowID.equals(driver.getWindowHandle())) {
				appLog.info("Reset password page is open in same window. ");
				} else {
					appLog.error("Some different url is opening. " + getURL(driver, 30));
					sa.assertTrue(false, "Some different url is opening. " + getURL(driver, 30));
				}
				if (isDisplayed(driver, lp.getTargetResetPassUserMsg(60), "Visibility", 60,
						"Target Reset Pass UserMsg") != null) {
					appLog.info("Please enter your email address below to reset your password is Displaying");
				} else {
					appLog.error("Please enter your email address below to reset your password is not displaying");
					sa.assertTrue(false, "Please enter your email address below to reset your password is not displaying");
				}
				if (isDisplayed(driver, lp.getResetPasswordUserNameTextBox(60), "Visibility", 60,
						"Reset Password Username Text Box") != null) {
					appLog.info("Reset Password Username Text Box is Displaying");
    		  String text=getValueFromElementUsingJavaScript(driver, lp.getResetPasswordUserNameTextBox(60), "Reset password username text box");
    		  if(text.isEmpty()){
    			appLog.info("username textbox is empty");
    		}else{
    			appLog.error("Username textbox is not empty");
    			sa.assertTrue(false, "Username textbox is not empty");
    		}
				} else {
					appLog.info("Reset Password Username Text Box is not displaying");
					sa.assertTrue(false, "Reset Password Username Text Box is not displaying");
				}
				if (isDisplayed(driver, lp.getResetPasswordSubmitButton(60), "Visibility", 60,
						"Reset Password Submit Button") != null) {
					appLog.info("Reset Passwrd Submit Button Is Displaying");
				} else {
					appLog.error("Reset Passwrd Submit Button Is not Displaying");
					sa.assertTrue(false, "Reset Passwrd Submit Button Is not Displaying");
				}
				if (click(driver, lp.getResetPasswordSubmitButton(60), "Reset Password Submit Button", action.BOOLEAN)) {
					WebElement ele = lp.getResetPasswordSubmitButtonErrorMessage(30);
					String msg;
					if (ele != null) {
						msg = ele.getText().trim();
						if (msg.contains(LoginErrorPage.resetPasswordEnterAValueErrorMsg)) {
							appLog.info("Error Message Matched without value : " + msg);
						} else {
							appLog.error("Error Message Not Matched without value" + "Expected"
									+ LoginErrorPage.resetPasswordEnterAValueErrorMsg + "/tActual"
									+ msg);
							sa.assertTrue(false,
									"Error Message Not Matched without value" + "Expected"
											+ LoginErrorPage.resetPasswordEnterAValueErrorMsg
											+ "/tActual" + msg);
						}
					} else {
						appLog.error("Submit Button error message element is null");
						sa.assertTrue(false, "Submit Button error message element is null");
					}
				} else {
					appLog.error("Not Able to Click Forgot Submit Button");
					sa.assertTrue(false, "Not Able to Click Forgot Submit Button");
				}	
				ThreadSleep(2000);
				if (sendKeys(driver, lp.getResetPasswordUserNameTextBox(60), "abcedefgh@#$123","Reset Username Text Box", action.BOOLEAN)) {
					if (click(driver, lp.getResetPasswordSubmitButton(60), "Forgot Password Submit Button",action.BOOLEAN)) {
						ThreadSleep(2000);
						WebElement ele = lp.getResetPasswordSubmitButtonErrorMessage(30);
						String msg;
						if (ele != null) {
							msg = ele.getText().trim();
							if (msg.contains(
									LoginErrorPage.forgotPasswordSubmitButtonErrorMessageWithUnInvitedContactEmailValue)) {
								appLog.info("Error Message  Matched  " + msg);
							} else {
								appLog.error("Error Message Not Matched  " + "Expected"
										+ LoginErrorPage.forgotPasswordSubmitButtonErrorMessageWithUnInvitedContactEmailValue
										+ "/tActual" + msg);
								sa.assertTrue(false,
										"Error Message Not Matched" + "Expected"
												+ LoginErrorPage.forgotPasswordSubmitButtonErrorMessageWithUnInvitedContactEmailValue
												+ "/tActual" + msg);
								}
						} else {
							appLog.error("Submit Button element is null");
							sa.assertTrue(false, "Submit Button element is null");
						}
					} else {
						appLog.error("Not Able to Click reset Submit Button");
						sa.assertTrue(false, "Not Able to Click reset Submit Button");
					}
				} else {
					appLog.error("Not Able to Enter Value to User Text");
					sa.assertTrue(false, "Not Able to Enter Value to User Text");
				}
				if (sendKeys(driver, lp.getResetPasswordUserNameTextBox(60), M18Contact3EmailId,"Forgot Username Text Box with Uninvited: " + M18Contact3EmailId, action.BOOLEAN)) {
					if (click(driver, lp.getResetPasswordSubmitButton(60), "Forgot Password Submit Button",
							action.BOOLEAN)) {
						ThreadSleep(2000);
						WebElement ele = lp.getResetPasswordSubmitButtonErrorMessage(30);
						String msg;
						if (ele != null) {
							msg = ele.getText().trim();
							if (msg.contains(LoginErrorPage.forgotPasswordSubmitButtonErrorMessageWithUnInvitedContactEmailValue)) {
								appLog.info("Error Message  Matched with Uninvited: " + msg);
							} else {
								appLog.error("Error Message Not Matched with Uninvited: " + "Expected"
										+ LoginErrorPage.forgotPasswordSubmitButtonErrorMessageWithUnInvitedContactEmailValue
										+ "/tActual" + msg);
								sa.assertTrue(false,
										"Error Message Not Matched with Uninvited: " + "Expected"
												+ LoginErrorPage.forgotPasswordSubmitButtonErrorMessageWithUnInvitedContactEmailValue
												+ "/tActual" + msg);
							}
						} else {
							appLog.error("Submit Button element is null");
							sa.assertTrue(false, "Submit Button element is null");
						}
					} else {
						appLog.error("Not Able to Click Forgot Submit Button");
						sa.assertTrue(false, "Not Able to Click Forgot Submit Button");
					}
				} else {
					appLog.error("Not Able to Enter Value to User Text");
					sa.assertTrue(false, "Not Able to Enter Value to User Text");
				}
				if (sendKeys(driver, lp.getResetPasswordUserNameTextBox(60), M18Contact2EmailId,"Forgot Username Text Box with Invited But Not Registered : " + M18Contact2EmailId, action.BOOLEAN)) {
					if (click(driver, lp.getResetPasswordSubmitButton(60), "Forgot Password Submit Button",
							action.BOOLEAN)) {
						ThreadSleep(2000);
						WebElement ele = lp.getResetPasswordSubmitButtonErrorMessage(30);
						String msg;
						if (ele != null) {
							msg = ele.getText().trim();
							if (msg.contains(LoginErrorPage.forgotPasswordSubmitButtonErrorMessageWithInvitedButNotRegisteredContactEmailValue)) {
								appLog.info("Error Message  Matched with Invited But Not Registered: " + msg);
							} else {
								appLog.error("Error Message Not Matched with Invited But Not Registered: " + "Expected"
										+ LoginErrorPage.forgotPasswordSubmitButtonErrorMessageWithInvitedButNotRegisteredContactEmailValue
										+ "\tActual" + msg);
								sa.assertTrue(false,
										"Error Message Not Matched with Invited But Not Registered: " + "Expected"
												+ LoginErrorPage.forgotPasswordSubmitButtonErrorMessageWithInvitedButNotRegisteredContactEmailValue
												+ "\tActual" + msg);
							}
							windowID= driver.getWindowHandle();
							if (click(driver, lp.getResetPasswordSubmitButtonClickLink(60),
									"Reset Password Submit Click Here Link", action.BOOLEAN)) {
								appLog.info(" Able to Click Here Link");
								CommonLib.waitForPageLoad(driver);
								if(windowID.equals(driver.getWindowHandle())){
									appLog.info("User Registration page is open in same window. ");
									if(lp.getResetPasswordregistrationpagePasswordErrorMsg(60).getText().contains(LoginErrorPage.resetPasswordRegistrationPagePasswordErrorMessage)){
										appLog.info("Password error message is verified"); 
									}else{
										appLog.error("Password error message is not verified");
										sa.assertTrue(false, "Password error message is not verified");
									}
									System.out.println(lp.getResetPasswordRegistrationPageClickHereMessage(60).getText());
									if(lp.getResetPasswordRegistrationPageClickHereMessage(60).getText().trim().contains(LoginErrorPage.resetPasswordRegistrationPageClickHereMessage)){
										appLog.info("Click here message is verified"); 
									}else{
										appLog.error("Click here message is not verified");
										sa.assertTrue(false, "Click here message is not verified");
									}		
								if(lp.getTargetEmailId(60)!=null){
									appLog.info("Email id textbox is displaying");
								}else{
									appLog.error("Email id textbox is not displaying");
									sa.assertTrue(false, "Email ID textbox is not displaying");
								}
							if(lp.getTargetFirstName(60).getAttribute("value").equalsIgnoreCase(M18Contact2FirstName)){
									appLog.info("First name is prefilled with "+M18Contact2FirstName);			
								}else{
									appLog.error("First name is not prefilled");
									sa.assertTrue(false, "first name is not prefilled");			
								}
							if(lp.getTargetlastName(60).getAttribute("value").equalsIgnoreCase(M18Contact2LastName)){
								appLog.info("Last name is prefilled with "+M18Contact2LastName);			
							}else{
								appLog.error("Last name is not prefilled");
								sa.assertTrue(false, "Last name is not prefilled");			
							}
							if(lp.getTargetNickName(60).getAttribute("value").equalsIgnoreCase(M18Contact2FirstName+M18Contact2LastName)){
								appLog.info("Nick name is prefilled with "+M18Contact2FirstName+M18Contact2LastName);			
							}else{
								appLog.error("Nick name is not prefilled");
								sa.assertTrue(false, "Nick name is not prefilled");			
							}
							if(lp.getTargetEmailId(60).getAttribute("value").equalsIgnoreCase(M18Contact2EmailId)){
								appLog.info("emailID is prefilled with "+M18Contact2EmailId);			
							}else{
								appLog.error("emailID is not prefilled");
								sa.assertTrue(false, "emailID is not prefilled");			
							}
							if(lp.getTargetFirmName(60).getAttribute("value").equalsIgnoreCase(M18Institution1)){
								appLog.info("Firm name is prefilled with "+M18Institution1);			
							}else{
								appLog.error("Firm name is not prefilled");
								sa.assertTrue(false, "Firm name is not prefilled");			
							}
							String text=getValueFromElementUsingJavaScript(driver, lp.getTargetpassword(60), "Reset password password text box");
				    		  if(text.isEmpty()){
				    			appLog.info("password textbox is empty");
				    		}else{
				    			appLog.error("password textbox is not empty");
				    			sa.assertTrue(false, "password textbox is not empty");
				    		}				
				    		  text=getValueFromElementUsingJavaScript(driver, lp.getTargetConfirmPassword(60), "Reset password confirm password text box");
				    		  if(text.isEmpty()){
				    			appLog.info("confirm password textbox is empty");
				    		}else{
				    			appLog.error("confirm password textbox is not empty");
				    			sa.assertTrue(false, "confirm password textbox is not empty");
				    		}				
				    		  if(click(driver, lp.getTargetSignUpBtn(60), "Target Sign Up Button", action.SCROLLANDBOOLEAN)){
				    				appLog.info("Clicked on signupbutton successfully");
				    	if(lp.getPasswordTargetRegPageErrorMessage(60).getText().contains(LoginErrorPage.targetRegisterPageErrorMessage)){
				    		appLog.info("Password error message is verified");
				    	}	else{
				    		appLog.error("Pasword error message is not verified");
				    		sa.assertTrue(false, "Pasword error message is not verified");
				    	}			    				
				    	}else{
				    		appLog.error("Not able to click on signup button");
				    		sa.assertTrue(false, "Not able to click on signup button");
				 			}
				    	  windowID=driver.getWindowHandle();
				    	if(click(driver, lp.getClickHereLinkOnTargetLoginPage(60), "Click here link", action.SCROLLANDBOOLEAN)){
				    		appLog.info("Clicked on click here link successfully");
				    		if(windowID.equals(driver.getWindowHandle())){
				    			appLog.info("Target Login page is open in same window. ");
				    		}else {
								appLog.error("Some different url is opening. " + getURL(driver, 30));
								sa.assertTrue(false, "Some different url is opening. " + getURL(driver, 30));
							}
				    	} else{
				    		appLog.error("Not able to click on click here link");
				    		sa.assertTrue(false, "Not able to click on click here link");
				    	}							
								} else {
									appLog.error("Some different url is opening. " + getURL(driver, 30));
									sa.assertTrue(false, "Some different url is opening. " + getURL(driver, 30));
								}
							} else {
								appLog.error("Not Able to Click Here Link");
								sa.assertTrue(false, "Not Able to Click Here Link");
							}		
						} else {
							appLog.error("Submit Button element is null");
							sa.assertTrue(false, "Submit Button element is null");
						}
					} else {
						appLog.error("Not Able to Click Forgot Submit Button");
						sa.assertTrue(false, "Not Able to Click Forgot Submit Button");
					}
				} else {
					appLog.error("Not Able to Enter Value to User Text");
					sa.assertTrue(false, "Not Able to Enter Value to User Text");
				}			
			}else{
				appLog.error("Not able to click on reset password link");
				sa.assertTrue(false, "Not able to click on reset password link");
			}
		}else{
			appLog.error("Reset PAssword link is not displaying");
			sa.assertTrue(false, "Reset Password link is not displaying");
		}	
		sa.assertAll();	
	}

	@Test
	public void M18tc007_SendForgotPasswordEmailToRegisterInvestorContactAndVerifyTheReceivedEmail(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
	   	driver.get(InvestorURL);
	   	String windowID= driver.getWindowHandle();
	   	if (click(driver, lp.getResetPasswordLink(60), "Reset Password link",action.BOOLEAN)) {
			CommonLib.waitForPageLoad(driver);
			if(windowID.equals(driver.getWindowHandle())){
				appLog.info("Forgot password page is open in same window. ");
				if (sendKeys(driver,lp.getResetPasswordUserNameTextBox(60), M18Contact1EmailId,
					"Reset Username Text Box with Register Target Mail Id: " + M18Contact1EmailId, action.BOOLEAN)) {
				if (click(driver, lp.getResetPasswordSubmitButton(60), "Reset Password Submit Button",
						action.BOOLEAN)) {
					WebElement ele = lp.getResetPasswordConfirmationTemproryPasswdMsg(30);
					String msg;
					if (ele != null) {
						msg = ele.getText().trim();
						if (msg.contains(LoginErrorPage.resetPasswordConfirmationTemproryPasswdMsg)) {
							appLog.info(" Reset Password Message  Matched for Invited Target: " + msg);
						} else {
							appLog.error(" Reset Password Message Not Matched for Invited Target: " + "Expected"
									+ LoginErrorPage.resetPasswordConfirmationTemproryPasswdMsg + "/tActual" + msg);
							sa.assertTrue(false,
									"Reset Password  Message Not Matched for Invited Target: " + "Expected"
											+ LoginErrorPage.resetPasswordConfirmationTemproryPasswdMsg + "/tActual"
											+ msg);
						}
					} else {
						appLog.error("Msg ele is null for Temprory Password");
						sa.assertTrue(false, "Msg ele is null for Temprory Password");
					}		
					ThreadSleep(4000);
					SoftAssert saa=bp.verifyResetPasswordMailContent(M18Contact1FirstName, M18Contact1EmailId, "InvestorPage", null, null,null);
					sa.combineAssertions(saa);
				} else {
					appLog.error("Not Able to Click reset Submit Button");
					sa.assertTrue(false, "Not Able to Click reset Submit Button");
				}
				} else {
				appLog.error("Not Able to Enter Value to User Text");
				sa.assertTrue(false, "Not Able to Enter Value to User Text");
			}
			} else {
				appLog.error("Some different url is opening. " + getURL(driver, 30));
				sa.assertTrue(false, "Some different url is opening. " + getURL(driver, 30));
			}	
	   	}else {
			appLog.error("Not Able to Click Forgot password Link");
			sa.assertTrue(false, "Not Able to Click Forgot password Link");
		}
		sa.assertAll();		
	}
    
	@Test
	public void M18tc008_1_VerifySendingResetPasswordmailtoInvestorFromContactDetailPage(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact1FirstName, M18Contact1LastName, null)){
				switchToFrame(driver, 30,bp.getFrame( PageName.ContactsPage, 20));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+"Section View");
				if(click(driver, cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace, 60), "Reset Password Active Button", action.SCROLLANDBOOLEAN)){
					if(click(driver, cp.getResetPasswordPopupSendEMailButton(60), "Send Email", action.SCROLLANDBOOLEAN)){
						if(cp.getResetPasswordPopupHeaderAfterSendEMail(60)!=null){
							if(cp.getResetPasswordPopupHeaderAfterSendEMail(60).getText().contains("Confirmation")){
								appLog.info("Confirmation Popup Header text is verified");
							}
							else{
								appLog.error("Confirmation Popup Header text is not verified");
								sa.assertTrue(false,"Confirmation Popup Header text is not verified");
							}
							if(cp.getResetPasswordPopupCrossIconAfterSendEMail(60)!=null){
								appLog.info("Cross icon is available on confirmation Popup");
							}
							else{
								appLog.error("Cross icon is not available on confirmation Popup");
								sa.assertTrue(false,"Cross icon is not available on confirmation Popup");
							}
							if(cp.getResetPasswordOKButton(60)!=null){
								appLog.info("OK button is available in Confirmation Popup");
							}
							else{
								appLog.error("OK button is not available in Confirmation Popup");
								sa.assertTrue(false,"OK button is not available in Confirmation Popup");
							}
							System.out.println(cp.getResetPasswordPopupMessage(60).getText());
							if(cp.getResetPasswordPopupMessage(60).getText().trim().contains("Reset password email sent to "+M18Contact1EmailId+" successfully.")){
								appLog.info("Reset password confirmation Popup message is verified");
							}
							else{
								appLog.error("Reset password confirmation Popup message is not verified");
								sa.assertTrue(false,"Reset password confirmation Popup message is not verified");
							}
							if(click(driver, cp.getResetPasswordOKButton(60), "OK Button", action.SCROLLANDBOOLEAN)){
                               if(!click(driver, cp.getResetPasswordOKButton(20), "OK", action.SCROLLANDBOOLEAN)){
                            	   appLog.info("Confirmation popup get closed successfully");
                               }
                               else{
									appLog.error("Confirmation popup not get closed successfully");
									sa.assertTrue(false, "Confirmation popup not get closed successfully");
								}	
                               
							}
							else{
                         	   appLog.error("Not able to click on OK Button");
									sa.assertTrue(false, "Not able to click on OK button");
                            }
							SoftAssert saa=cp.verifyResetPasswordMailContent(M18Contact1FirstName, M18Contact1EmailId, "Contact Page", Org1FirmName, CRMUser1EmailID,null);
							sa.combineAssertions(saa);
						}
						else {
							appLog.error("Confirmation Popup is not appearing after sending Reset Password email");
							sa.assertTrue(false,"Confirmation Popup is not appearing after sending Reset Password email");
						}
					}
					else{
						appLog.error("Not Able to click on Send Email button.");
						sa.assertTrue(false,"Not Able to click on Send Email button.");
					}

				}
				else{
					appLog.error("Not able to click on Reset Password Button");
					sa.assertTrue(false,"Not able to click on Reset Password Button");
				}

			}
			else{
				appLog.error("Not able to click on contact Name");
				sa.assertTrue(false, "Not able to click on contact Name");
			}
		}
		else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M18tc008_2_VerifySendingResetPasswordMailToTargetContactFromContactAccessTab(){
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer afp =new AllFirmsPageBusinesslayer(driver);
		String updatedPassword = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M18Contact1", excelLabel.Updated_Password);
		driver.get(ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M18Contact1", excelLabel.Click_HereLink));
		if(bp.getResetPasswordPageHeader(60)!=null){
			if(bp.getResetPasswordPageHeader(60).getText().equalsIgnoreCase("Reset Password")){
				appLog.info("Reset Pasword header text is verified");
			}else{
				appLog.error("Reset Password Header text is not verified");
				sa.assertTrue(false, "Reset Password Header text is not verified");
			}
			}else{
			appLog.error("Reset Password header is not displaying");
			sa.assertTrue(false, "Reset Password header is not displaying");
		}
		if(bp.getResetPasswordNavatarLogo(60)!=null){
			appLog.info("Navatar logo is displaying");
			}else{
				appLog.error("Navatar logo is not displaying");
				sa.assertTrue(false, "Navatar logo is not displaying");
			}
		if(bp.getResetPasswordNewPasswordText(60).getText().equalsIgnoreCase("New Password")){
			appLog.info("New Password text is displaying");
		}else{
			appLog.error("New passowrd text is not displaying");
			sa.assertTrue(false, "New passowrd text is not displaying");
		}
		if(bp.getResetPasswordNewPasswordTextBox(60)!=null){
			appLog.info("New Password textbox is displaying");
		}else{
			appLog.error("New passowrd textbox is not displaying");
			sa.assertTrue(false, "New passowrd textbox is not displaying");
		}
		if(bp.getResetPasswordConfirmPasswordText(60).getText().equalsIgnoreCase("Confirm Password")){
			appLog.info("Confirm Password text is displaying");
		}else{
			appLog.error("Confirm passowrd text is not displaying");
			sa.assertTrue(false, "Confirm passowrd text is not displaying");
		}
		if(bp.getResetPasswordConfirmPasswordTextBox(60)!=null){
			appLog.info("Confirm Password textbox is displaying");
		}else{
			appLog.error("Confirm passowrd textbox is not displaying");
			sa.assertTrue(false, "Confirm passowrd textbox is not displaying");
		}
		if(bp.getResetPasswordErrorMessageText(60).getText().equalsIgnoreCase(BasePageErrorMessage.resetPasswordRegistrationPagePasswordErrorMessage)){
			appLog.info("Error Message is verified");
		}else{
			appLog.error("Error Message is not verified");
			sa.assertTrue(false, "Error Message is not verified");
		}
		if(bp.getResetPasswordButton(60)!=null){
			appLog.info("Reset Pasword button is displaying");
		}else{
			appLog.error("Reset Password button is not displaying");
			sa.assertTrue(false, "Reset Password button is not displaying");
		}
	if(click(driver, bp.getResetPasswordButton(60), "Reset Password button", action.SCROLLANDBOOLEAN)){
		if(bp.getResetPasswordEnterValueErrorMsg(60).getText().equalsIgnoreCase(BasePageErrorMessage.resetPasswordEnterAValueErrorMsg)){
			appLog.info("Error message is verified");
		}else{
			appLog.error("Error Message is not verified");
			sa.assertTrue(false, "Error Message is not verified");
		}		
	}else{
		appLog.error("Not able to click on reset password button");
		sa.assertTrue(false, "Not able to click on reset password button");
	}
	
	if(sendKeys(driver, bp.getResetPasswordNewPasswordTextBox(60), "navatar", "New password text box", action.SCROLLANDBOOLEAN)){
		if(sendKeys(driver, bp.getResetPasswordConfirmPasswordTextBox(60), "navatar", "Confirm password text box", action.SCROLLANDBOOLEAN)){
		if(click(driver, bp.getResetPasswordButton(60), "Reset Password button", action.SCROLLANDBOOLEAN)){
			if(bp.getResetPasswordEnterValueErrorMsg(60).getText().equalsIgnoreCase(BasePageErrorMessage.resetPasswordContainingletterandNumberErrorMessage)){
				appLog.info("Error message is verified"+bp.getResetPasswordEnterValueErrorMsg(60).getText());
			}else{
				appLog.error("Error Message is not verified"+bp.getResetPasswordEnterValueErrorMsg(60).getText());
				sa.assertTrue(false, "Error Message is not verified"+bp.getResetPasswordEnterValueErrorMsg(60).getText());
			}			
		}else{
			appLog.error("Not able to click on reset password button");
			sa.assertTrue(false, "Not able to click on reset password button");
		}			
		}else{
			appLog.error("Not able to enter value in Confirm password text box");
			sa.assertTrue(false, "Not able to enter value in Confirm password text box");
		}
	}else{
		appLog.error("Not able to enter value in new password text box");
		sa.assertTrue(false, "Not able to enter value in new password text box");
	}
	if(sendKeys(driver, bp.getResetPasswordNewPasswordTextBox(60), "123456789", "New password text box", action.SCROLLANDBOOLEAN)){
		if(sendKeys(driver, bp.getResetPasswordConfirmPasswordTextBox(60), "123456789", "Confirm password text box", action.SCROLLANDBOOLEAN)){
		if(click(driver, bp.getResetPasswordButton(60), "Reset Password button", action.SCROLLANDBOOLEAN)){
			if(bp.getResetPasswordEnterValueErrorMsg(60).getText().equalsIgnoreCase(BasePageErrorMessage.resetPasswordContainingletterandNumberErrorMessage)){
				appLog.info("Error message is verified"+bp.getResetPasswordEnterValueErrorMsg(60).getText());
			}else{
				appLog.error("Error Message is not verified"+bp.getResetPasswordEnterValueErrorMsg(60).getText());
				sa.assertTrue(false, "Error Message is not verified"+bp.getResetPasswordEnterValueErrorMsg(60).getText());
			}			
		}else{
			appLog.error("Not able to click on reset password button");
			sa.assertTrue(false, "Not able to click on reset password button");
		}			
		}else{
			appLog.error("Not able to enter value in Confirm password text box");
			sa.assertTrue(false, "Not able to enter value in Confirm password text box");
		}
	}else{
		appLog.error("Not able to enter value in new password text box");
		sa.assertTrue(false, "Not able to enter value in new password text box");
	}	
	if(sendKeys(driver, bp.getResetPasswordNewPasswordTextBox(60),adminPassword, "New password text box", action.SCROLLANDBOOLEAN)){
		if(sendKeys(driver, bp.getResetPasswordConfirmPasswordTextBox(60),adminPassword, "Confirm password text box", action.SCROLLANDBOOLEAN)){
		if(clickUsingJavaScript(driver, bp.getResetPasswordButton(60), "Reset Password button")){
			ThreadSleep(3000);
			if(isAlertPresent(driver)){
			String errorText=switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
			if(errorText.equalsIgnoreCase(BasePageErrorMessage.resetPasswordLast3PasswordSameErrorMessage)){
				appLog.info("Alert message is verified");
			}else{
				appLog.error("Alert Message is not verified");
				sa.assertTrue(false, "Alert Message is not verified");
			}	
			switchToAlertAndAcceptOrDecline(driver, 20, action.ACCEPT);
			}else{
				appLog.error("Alert popup is not displaying");
				sa.assertTrue(false, "Alert popup is not displaying");
			}
		}else{
			appLog.error("Not able to click on reset password button");
			sa.assertTrue(false, "Not able to click on reset password button");
		}			
		}else{
			appLog.error("Not able to enter value in Confirm password text box");
			sa.assertTrue(false, "Not able to enter value in Confirm password text box");
		}
	}else{
		appLog.error("Not able to enter value in new password text box");
		sa.assertTrue(false, "Not able to enter value in new password text box");
	}
	if(sendKeys(driver, bp.getResetPasswordNewPasswordTextBox(60), "navatar1234", "New password text box", action.SCROLLANDBOOLEAN)){
		if(sendKeys(driver, bp.getResetPasswordConfirmPasswordTextBox(60), adminPassword, "Confirm password text box", action.SCROLLANDBOOLEAN)){
		if(click(driver, bp.getResetPasswordButton(60), "Reset Password button", action.SCROLLANDBOOLEAN)){
			if(bp.getResetPasswordPasswordAndConfirmPasswordNotSameErrorMessage(60).getText().equalsIgnoreCase(BasePageErrorMessage.resetPasswordConfirmPasswordNotSameErrorMessage)){
				appLog.info("Error message is verified"+bp.getResetPasswordPasswordAndConfirmPasswordNotSameErrorMessage(60).getText());
			}else{
				appLog.error("Error Message is not verified"+bp.getResetPasswordPasswordAndConfirmPasswordNotSameErrorMessage(60).getText());
				sa.assertTrue(false, "Error Message is not verified"+bp.getResetPasswordPasswordAndConfirmPasswordNotSameErrorMessage(60).getText());
			}			
		}else{
			appLog.error("Not able to click on reset password button");
			sa.assertTrue(false, "Not able to click on reset password button");
		}			
		}else{
			appLog.error("Not able to enter value in Confirm password text box");
			sa.assertTrue(false, "Not able to enter value in Confirm password text box");
		}
	}else{
		appLog.error("Not able to enter value in new password text box");
		sa.assertTrue(false, "Not able to enter value in new password text box");
	}
	
	if(sendKeys(driver, bp.getResetPasswordNewPasswordTextBox(60), updatedPassword, "New password text box", action.SCROLLANDBOOLEAN)){
		if(sendKeys(driver, bp.getResetPasswordConfirmPasswordTextBox(60), updatedPassword, "Confirm password text box", action.SCROLLANDBOOLEAN)){
		if(click(driver, bp.getResetPasswordButton(60), "Reset Password button", action.SCROLLANDBOOLEAN)){
			ThreadSleep(4000);
			if (getURL(driver, 60).contains("IP_login")) {
				appLog.info("Target Login page is open in same window. ");
			} else {
				appLog.error("Target Login page is not opening. " + getURL(driver, 30));
				sa.assertTrue(false, "Some different url is opening. " + getURL(driver, 30));
			}			
			if(bp.getResetPasswordPasswordChangeMessage(60)!=null){
			if(bp.getResetPasswordPasswordChangeMessage(60).getText().equalsIgnoreCase(BasePageErrorMessage.resetPasswordPasswordChangeMessage)){
				appLog.info("Password change message is verified");
			}else{
				appLog.error("Password change message is not verified");
				sa.assertTrue(false, "Password change message is not verified");
			}	
			String emailText=getValueFromElementUsingJavaScript(driver, lp.getInvestorUsernameTextbox(60), "Username text box");
				if(emailText.equalsIgnoreCase(M18Contact1EmailId)){
					appLog.info("Contact email id is prefilled in username textbox");
				}else{
					appLog.error("Contact email id is not prefilled in username text box");
					sa.assertTrue(false, "Contact email id is not prefilled in username text box");
				}				
			}else{
				appLog.error("Password change message is not displaying");
				sa.assertTrue(false, "Password change message is not displaying");
			}
		if(sendKeys(driver, lp.getInvestorPasswordTextbox(60), adminPassword, "Target passowrd text box", action.SCROLLANDBOOLEAN)){
			if(clickUsingJavaScript(driver, lp.getInvestorLoginButton(60), "Target login button")){
				ThreadSleep(3000);
				if(isAlertPresent(driver)){
					String AlertText=switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
					if(AlertText.equalsIgnoreCase(BasePageErrorMessage.resetPasswordPasswordDoNotMatchErrorMessage)){
						appLog.info("Password do not match error message is verified");
					}else{
						appLog.error("Password do not match error message is not verified");
						sa.assertTrue(false, "Password do not match error message is not verified");
					}
					switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
				}				
			}else{
				appLog.error("Not able to click on login button");
				sa.assertTrue(false, "Not able to click on login button");
			}			
		}else{
			appLog.error("Not able to send value in password tetx box");
			sa.assertTrue(false, "Not able to send value in password tetx box");
		}
		if(sendKeys(driver, lp.getInvestorPasswordTextbox(60),updatedPassword, "Target passowrd text box", action.SCROLLANDBOOLEAN)){
			if(click(driver, lp.getInvestorLoginButton(60), "Target login button", action.SCROLLANDBOOLEAN)){
				ThreadSleep(5000);
		if(afp.getFirmNameDropdown(60)!=null){
			if(getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Select deal dropdown", "text").equalsIgnoreCase(Org1FirmName)){
				appLog.info("Investor home page is displaying");
			}else{
				appLog.error("Investor home page is not displaying");
				sa.assertTrue(false, "Investor home page is not displaying");
			}				
		}else{
			appLog.error("Select firm dropdown is not displaying");
			sa.assertTrue(false, "Select firm dropdown is not displaying");
		}		
			}else{
				appLog.error("Not able to click on login button");
				sa.assertTrue(false, "Not able to click on login button");
			}			
		}else{
			appLog.error("Not able to send value in password tetx box");
			sa.assertTrue(false, "Not able to send value in password tetx box");
		}
		}else{
			appLog.error("Not able to click on reset password button");
			sa.assertTrue(false, "Not able to click on reset password button");
		}			
		}else{
			appLog.error("Not able to enter value in Confirm password text box");
			sa.assertTrue(false, "Not able to enter value in Confirm password text box");
		}
	}else{
		appLog.error("Not able to enter value in new password text box");
		sa.assertTrue(false, "Not able to enter value in new password text box");
	}
	lp.investorLogout();
	sa.assertAll();		
	}
		
	@Test
	public void M18tc009_VerifyClickHereIfInvestorContactHasAlreadyResetThePassword(){
		SoftAssert sa=new SoftAssert();
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);		
		driver.get(ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M18Contact1", excelLabel.Click_HereLink));	
		if(lp.getResetPasswordLinkExpiredErrorPopupHeader(60)!=null){
			if(lp.getResetPasswordLinkExpiredErrorPopupHeader(60).getText().equalsIgnoreCase("Error")){
				appLog.info("Error text is displaying on the header");
			}else{
				appLog.error("Error text is not displaying on the header");
				sa.assertTrue(false, "Error text is not displaying on the header");
			}
			if(lp.getResetPasswordLinkExpiredErrorMessage(60).getText().trim().contains(LoginErrorPage.resetPasswordLinkExpiredErrorMessage)){
					appLog.info("Error Popup Message is verified successfully");				
				}else{
					appLog.error("Error Popup Message is not verified successfully");
					sa.assertTrue(false, "Error Popup Message is not verified successfully");					
				}
				if(lp.getResetPasswordLinkExpiredErrorPopupOKButton(60)!=null){
					appLog.info("OK button is displaying in the popup");
				}else{
					appLog.error("OK Button is not displaying in the popup");
					sa.assertTrue(false, "OK Button is not displaying in the popup");
				}
				if(lp.getResetPasswordLinkExpiredErrorPopupCloseIcon(60)!=null){
					appLog.info("Cross Icon is displaying in the popup");
				}else{
					appLog.error("Cross Icon is not displaying in the popup");
					sa.assertTrue(false, "Cross Icon is not displaying in the popup");
				}
			if(click(driver, lp.getResetPasswordLinkExpiredErrorPopupOKButton(60), "OK Button", action.SCROLLANDBOOLEAN)){
				appLog.info("Clicked on OK Button");
			if(lp.getInvestorLoginButton(60)!=null){
				appLog.info("Investor login page is displaying");
			}else{
				appLog.error("Investor login page is not displaying");
				sa.assertTrue(false, "Investor login page is not displaying");
			}
			  String text=getValueFromElementUsingJavaScript(driver, lp.getInvestorUsernameTextbox(60), "Reset password username text box");
    		  if(text.isEmpty()){
    			appLog.info("username textbox is empty");
    		}else{
    			appLog.error("Username textbox is not empty");
    			sa.assertTrue(false, "Username textbox is not empty");
    		}				
			}else{
				appLog.error("Not able to click on OK Button");
				sa.assertTrue(false, "Not able to click on OK Button");
			}
			}else{
			appLog.error("Error Popup is not displaying");
			sa.assertTrue(false, "Error Popup is not displaying");
		}
		sa.assertAll();		
		}
	
	@Test
	public void M18tc010_1_UpdateTheEmailAddressOfTheContactCheckFunctionalityOfResetPasswordLink(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		String emailId = contact.generateRandomEmailId();
		if (contact.updateEmailAddressOfCreatedContact(M18Contact1FirstName, M18Contact1LastName, emailId)) {
			ExcelUtils.writeData(emailId, "Contacts", excelLabel.Variable_Name, "M18contact1", excelLabel.ContactUpdatedEmailID);
			appLog.info("Contact Email Id  Updated for : " + M18Contact1FirstName + " " + M18Contact1LastName);
		} else {
			appLog.error("Contact Email Id is not Updated : " + M18Contact1FirstName + " " + M18Contact1LastName);
			sa.assertTrue(false, "Contact Email Id is not Updated : " + M18Contact1FirstName + " " + M18Contact1LastName);
		}
		lp.CRMlogout();
		sa.assertAll();			
	}

	@Test
	public void M18tc010_2_checkfunctionalityOfResetPasswordLinkFromInvestorSide(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		String updtEmailIdbyCRM;
		String msg;
		WebElement ele;
		driver.get(ExcelUtils.readDataFromPropertyFile("InvestorURL"));
		CommonLib.waitForPageLoad(driver);
		appLog.info("Going to verify for Target Contact : " + M18Contact1FirstName + " " + M18Contact1LastName);
		String windowID= driver.getWindowHandle();
		if (click(driver, lp.getResetPasswordLink(60), "reset Password Link", action.BOOLEAN)) {
			CommonLib.waitForPageLoad(driver);
			if(windowID.equals(driver.getWindowHandle())){
				appLog.info("reset password page is open in same window. ");
			}else{
				appLog.error("reset password page is not open in same window. ");
				sa.assertTrue(false,"reset password page is not open in same window. ");
			}
			if (sendKeys(driver, lp.getResetPasswordUserNameTextBox(60), M18Contact1EmailId,
					"Reset Username Text Box : " + M18Contact1EmailId, action.BOOLEAN)) {
				if (click(driver, lp.getResetPasswordSubmitButton(60), "Reset Password Submit Button",
						action.BOOLEAN)) {
					 ele = lp.getResetPasswordConfirmationTemproryPasswdMsg(30);
						if (ele != null) {
						msg = ele.getText().trim();
						if (msg.contains(LoginErrorPage.resetPasswordConfirmationTemproryPasswdMsg)) {
							appLog.info(" Reset Password Message  Matched for Invited Target: " + msg);
						} else {
							appLog.error(" Reset Password Message Not Matched for Invited Target: " + "Expected"
									+ LoginErrorPage.resetPasswordConfirmationTemproryPasswdMsg + "/tActual" + msg);
							sa.assertTrue(false,
									"Reset Password  Message Not Matched for Invited Target: " + "Expected"
											+ LoginErrorPage.resetPasswordConfirmationTemproryPasswdMsg + "/tActual"
											+ msg);
						}
					} else {
						appLog.error("Msg ele is null for Temprory Password");
						sa.assertTrue(false, "Msg ele is null for Temprory Password");
					}					
					SoftAssert saa=bp.verifyResetPasswordMailContent(M18Contact1FirstName, M18Contact1EmailId, "InvestorPage", null, null,null);
					sa.combineAssertions(saa);
					updtEmailIdbyCRM = ExcelUtils.readData("Contacts",  excelLabel.Variable_Name, "M18Contact1", excelLabel.ContactUpdatedEmailID);
					if (sendKeys(driver, lp.getResetPasswordUserNameTextBox(60), updtEmailIdbyCRM,
							"reset Username Text Box : " + updtEmailIdbyCRM, action.BOOLEAN)) {
						if (click(driver, lp.getResetPasswordSubmitButton(60), "reset Password Submit Button",
								action.BOOLEAN)) {
							 ele = lp.getResetPasswordSubmitButtonErrorMessage(30);
							if (ele != null) {
								msg = ele.getText().trim();
								if (msg.contains(LoginErrorPage.forgotPasswordSubmitButtonErrorMessageWithUnInvitedContactEmailValue)) {
									appLog.info("Error Message Matched without value : " + msg);
								} else {
									appLog.error("Error Message Not Matched without value" + "Expected"
											+ LoginErrorPage.forgotPasswordSubmitButtonErrorMessageWithUnInvitedContactEmailValue + "/tActual"
											+ msg);
									sa.assertTrue(false,
											"Error Message Not Matched without value" + "Expected"
													+ LoginErrorPage.forgotPasswordSubmitButtonErrorMessageWithUnInvitedContactEmailValue
													+ "/tActual" + msg);
								}
							} else {
								appLog.error("Submit Button error message element is null");
								sa.assertTrue(false, "Submit Button error message element is null");
							}
						} else {
							appLog.error("Not Able to Click reset Submit Button");
							sa.assertTrue(false, "Not Able to Click reset Submit Button");
						}
						} else {
						appLog.error("Not Able to Enter Value to User Text");
						sa.assertTrue(false, "Not Able to Enter Value to User Text");
					}
				} else {
					appLog.error("Not Able to Click reset Submit Button");
					sa.assertTrue(false, "Not Able to Click reset Submit Button");
				}
			} else {
				appLog.error("Not Able to Enter value to reset pass username TextBox");
				sa.assertTrue(false, "Not Able to Enter value to reset pass username TextBox");
			}
		} else {
			appLog.error("Not Able to Click reset Pass Link");
			sa.assertTrue(false, "Not Able to Click reset Pass Link");
		}
		sa.assertAll();	
		}
	
	@Test
	public void M18tc010_3_UpdateMailIDtoPreviousEmailidofRegisterTargetContact1(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (contact.updateEmailAddressOfCreatedContact(M18Contact1FirstName, M18Contact1LastName, M18Contact1EmailId)) {
			appLog.info("Contact Email Id  Updated TO Previous for : " + M18Contact1FirstName + " " + M18Contact1LastName);
		} else {
			appLog.error("Contact Email Id is not Updated to Previous : " + M18Contact1FirstName + " " + M18Contact1LastName);
			sa.assertTrue(false, "Contact Email Id is not Updated to Previous : " + M18Contact1FirstName + " " + M18Contact1LastName);
		}
	
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();	
	}
		
	@Test
	public void M18tc011_1_DeleteTheEmailAddressOfTheContact(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (!contact.deleteCreatedContact(M18Contact1FirstName, M18Contact1LastName)) {
			appLog.error("Contact is not deleted : " + M18Contact1FirstName + " " + M18Contact1LastName);
			sa.assertTrue(false, "Contact  is not deleted : " + M18Contact1FirstName + " " + M18Contact1LastName);
		} else {
			appLog.info("Contact is  deleted : " + M18Contact1FirstName + " " + M18Contact1LastName);
		}		
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();		
	}
	
	@Test
	public void M18tc011_2_ImpactAfterDeletingRegisterTargetContact1() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		String msg;
		WebElement ele;
		driver.get(ExcelUtils.readDataFromPropertyFile("InvestorURL"));
		CommonLib.waitForPageLoad(driver);
		appLog.info("Going to verify for Target Contact : " + M18Contact1FirstName + " " + M18Contact1LastName);
		String windowID= driver.getWindowHandle();
		if (click(driver, lp.getResetPasswordLink(60), "reset Password Link", action.BOOLEAN)) {
			CommonLib.waitForPageLoad(driver);
			if(windowID.equals(driver.getWindowHandle())){
				appLog.info("reset password page is open in same window. ");
				if (sendKeys(driver, lp.getResetPasswordUserNameTextBox(60), M18Contact1EmailId,
						"Reset Username Text Box : " + M18Contact1EmailId, action.BOOLEAN)) {
					if (click(driver, lp.getResetPasswordSubmitButton(60), "Reset Password Submit Button",
							action.BOOLEAN)) {
						ele = lp.getResetPasswordConfirmationTemproryPasswdMsg(30);
						if (ele != null) {
							msg = ele.getText().trim();
							if (msg.contains(LoginErrorPage.resetPasswordConfirmationTemproryPasswdMsg)) {
								appLog.info(" Tempory Password Message  Matched for Invited Target: " + msg);
							} else {
								appLog.error(" Tempory Password Message Not Matched for Invited Target: " + "Expected"
										+ LoginErrorPage.resetPasswordConfirmationTemproryPasswdMsg + "/tActual" + msg);
								sa.assertTrue(false,
										"Tempory Password  Message Not Matched for Invited Target: " + "Expected"
												+ LoginErrorPage.resetPasswordConfirmationTemproryPasswdMsg + "/tActual"
												+ msg);
							}
						} else {
							appLog.error("Msg ele is null for Temprory Password");
							sa.assertTrue(false, "Msg ele is null for Temprory Password");
						}
						SoftAssert saa=bp.verifyResetPasswordMailContent(M18Contact1FirstName, M18Contact1EmailId, "InvestorPage", null, null,null);
						sa.combineAssertions(saa);
					} else {
						appLog.error("Not Able to Click reset Submit Button");
						sa.assertTrue(false, "Not Able to Click reset Submit Button");
					}
				} else {
					appLog.error("Not Able to Enter value to reset pass username TextBox");
					sa.assertTrue(false, "Not Able to Enter value to reset pass username TextBox");
				}			
			}else{
				appLog.error("reset password page is not open in same window. ");
				sa.assertTrue(false,"reset password page is not open in same window. ");
			}	
		} else {
			appLog.error("Not Able to Click reset Pass Link");
			sa.assertTrue(false, "Not Able to Click reset Pass Link");
		}
		sa.assertAll();
	}
	
	@Test
	public void M18tc011_3_RestoreRegisterTargetContact1AndExternalAdmin3() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		HomePageBusineesLayer hp = new HomePageBusineesLayer(driver);
		BasePageBusinessLayer bp= new BasePageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (hp.restoreValuesFromRecycleBin(M18Contact1FirstName + " " + M18Contact1LastName)) {
			if(bp.clickOnTab(TabName.ContactTab)){
			if (cp.clickOnCreatedContact(M18Contact1FirstName, M18Contact1LastName, null)) {
				appLog.info(M18Contact1FirstName + " " + M18Contact1LastName + "  has been Restored");
			} else {
				appLog.info(M18Contact1FirstName + " " + M18Contact1LastName + " has not  been Restored");
				sa.assertTrue(false, M18Contact1FirstName + " " + M18Contact1LastName + "  has not been Restored");
			}
			}else{
				appLog.error("Not able to click on contact tab");
				sa.assertTrue(false, "Not able to click on contact tab");
			}
		} else {
			appLog.error("Not Able to restore value");
			sa.assertTrue(false, "Not Able to restore value");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M18tc012_1_CloseFundRasingWorkspace(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M18FundName1)){
			switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
			System.err.println("Switched to frame.");
			scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace.toString()+" View.");
		if(fp.closeWorkSpace(Workspace.FundraisingWorkspace, 60)){
			appLog.info("Closed workspace successfully");
		}else{
			appLog.error("Not able to close the workspace");
			sa.assertTrue(false, "Not able to close the workspace");
		}			
			}else{
				appLog.error("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}			
		}else{
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");			
		}
		switchToDefaultContent(driver);
		if(bp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact1FirstName, M18Contact1LastName, null)){
				switchToFrame(driver, 30, bp.getFrame( PageName.ContactsPage, 60));
				System.err.println("Switched to frame.");
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace.toString()+" View.");
				if (bp.verifyErrorMessageOnPage(
						ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace,
						cp.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60),
						"Error Message after admin Registration on Contact page for fundraising Workspace")) {
					appLog.info("Error Message is verified  on Contact page for fundraising workspace");
				} else {
					sa.assertTrue(false,
							"Error Message is not verified on Contact page for fundraising workspace.Expected:"
									+ ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace
									+ " Actual"
									+ getText(driver,
											cp.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60),
											"Error Message before admin registration on contact page", action.BOOLEAN));
				}			
			}else{
				appLog.error("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();		
	} 

	@Test
	public void M18tc012_2_ImpactAfterClosingDealRoom(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		String msg;
		WebElement ele;
		driver.get(ExcelUtils.readDataFromPropertyFile("InvestorURL"));
		CommonLib.waitForPageLoad(driver);
		String windowID= driver.getWindowHandle();
		if (click(driver, lp.getResetPasswordLink(60), "reset Password Link", action.BOOLEAN)) {
			CommonLib.waitForPageLoad(driver);
			if(windowID.equals(driver.getWindowHandle())){
				appLog.info("reset password page is open in same window. ");
				if (sendKeys(driver, lp.getResetPasswordUserNameTextBox(60), M18Contact1EmailId,
						"Reset Username Text Box : " + M18Contact1EmailId, action.BOOLEAN)) {
					if (click(driver, lp.getResetPasswordSubmitButton(60), "Reset Password Submit Button",
							action.BOOLEAN)) {
						ele = lp.getResetPasswordConfirmationTemproryPasswdMsg(30);
						if (ele != null) {
							msg = ele.getText().trim();
							if (msg.contains(LoginErrorPage.resetPasswordConfirmationTemproryPasswdMsg)) {
								appLog.info(" Tempory Password Message  Matched for Invited Target: " + msg);
							} else {
								appLog.error(" Tempory Password Message Not Matched for Invited Target: " + "Expected"
										+ LoginErrorPage.resetPasswordConfirmationTemproryPasswdMsg + "/tActual" + msg);
								sa.assertTrue(false,
										"Tempory Password  Message Not Matched for Invited Target: " + "Expected"
												+ LoginErrorPage.resetPasswordConfirmationTemproryPasswdMsg + "/tActual"
												+ msg);
							}
						} else {
							appLog.error("Msg ele is null for Temprory Password");
							sa.assertTrue(false, "Msg ele is null for Temprory Password");
						}
						SoftAssert saa=bp.verifyResetPasswordMailContent(M18Contact1FirstName, M18Contact1EmailId, "InvestorPage", null, null,null);
						sa.combineAssertions(saa);
					} else {
						appLog.error("Not Able to Click reset Submit Button");
						sa.assertTrue(false, "Not Able to Click reset Submit Button");
					}
				} else {
					appLog.error("Not Able to Enter value to reset pass username TextBox");
					sa.assertTrue(false, "Not Able to Enter value to reset pass username TextBox");
				}
			}else{
				appLog.error("reset password page is not open in same window. ");
				sa.assertTrue(false,"reset password page is not open in same window. ");
			}	
		} else {
			appLog.error("Not Able to Click reset Pass Link");
			sa.assertTrue(false, "Not Able to Click reset Pass Link");
		}
		sa.assertAll();		
	}

	@Test
	public void M18tc013_AgainInviteContactsAndVerifyContactDetailPage(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M18FundName1)) {				
				if (fp.inviteContact(environment, mode, M18Institution1, M18Contact1EmailId, null, FolderType.Standard,
						"Upload", "Yes", "No", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M18Contact1FirstName + " "
							+ M18Contact1LastName + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M18Contact1FirstName + " "
							+ M18Contact1LastName + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M18Contact1FirstName + " "
							+ M18Contact1LastName + "'.");
				}
				if (fp.inviteContact(environment, mode, M18Institution1, M18Contact2EmailId, null, FolderType.Standard,
						"Upload", "Yes", "No", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M18Contact2FirstName + " "
							+ M18Contact2LastName + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M18Contact2FirstName + " "
							+ M18Contact2LastName + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M18Contact2FirstName + " "
							+ M18Contact2LastName + "'.");
				}
			} else {
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab so we cannot create workspace");
			sa.assertTrue(false, "Not able to click on Funds tab so we cannot create workspace");
		}
		switchToDefaultContent(driver);
		if(bp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact1FirstName, M18Contact1LastName, null)){
				switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+" Section view");
			if(cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace, 60)!=null){
				appLog.info("Reset password button is displaying in enabled mode");
			}else{
				appLog.error("Reset password button is not displaying in enabled mode");
				sa.assertTrue(false, "Reset password button is not displaying in enabled mode");
			}				
			}else{
				appLog.equals("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}		
		switchToDefaultContent(driver);
		if(bp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact2FirstName, M18Contact2LastName, null)){
				switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+" Section view");
			if(cp.getResetPasswordInactiveButton(Workspace.FundraisingWorkspace, 60)!=null){
				appLog.info("Reset password button is displaying in disabled mode");
			}else{
				appLog.error("Reset password button is not displaying in disabled mode");
				sa.assertTrue(false, "Reset password button is not displaying in disabled mode");
			}				
			}else{
				appLog.equals("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}	
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();			
	}

	@Test
	public void M18tc014_1_SendResetPasswordEmailFromContactPage(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID	, adminPassword);
		if(cp.clickOnTab(TabName.ContactTab)){
				if(cp.clickOnCreatedContact(M18Contact1FirstName, M18Contact1LastName, null)){
					switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
					scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+" Section view");
					if(cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace,60)!=null){
						if(click(driver, cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace,60), "Reset Pasword button", action.SCROLLANDBOOLEAN)){		
							if(click(driver, cp.getResetPasswordPopupSendEMailButton(60), "Send Email", action.SCROLLANDBOOLEAN)){
								if(cp.getResetPasswordPopupHeaderAfterSendEMail(60)!=null){
									if(cp.getResetPasswordPopupHeaderAfterSendEMail(60).getText().trim().contains("Confirmation")){
										appLog.info("Header text is verified");					
									}else{
										appLog.error("Header text is not verified");
										sa.assertTrue(false, "Header Text is not verified");
									}
									if(cp.getResetPasswordPopupCrossIconAfterSendEMail(60)!=null){
										appLog.info("Cross icon is available");
									}else{
										appLog.error("Cross icon is not available");
										sa.assertTrue(false, "Cross icon is not available");
									}
									if(cp.getResetPasswordOKButton(60)!=null){
										appLog.info("OK Button is available");
									}else{
										appLog.error("OK Button is not available");
										sa.assertTrue(false, "OK Button is not available");
									}
									if(cp.getResetPasswordMessageAfterSendEMail(60).getText().trim().contains("Reset password email sent to "+M18Contact1EmailId+" successfully.")){
										appLog.info("Reset password message is verified");				
									}else{
										appLog.error("Reset password message is not verified");
										sa.assertTrue(false, "Reset password message is not verified");
									}
									if(click(driver, cp.getResetPasswordOKButton(60), "OK Button", action.SCROLLANDBOOLEAN)){
										if(!click(driver, cp.getResetPasswordOKButton(5), "ok", action.SCROLLANDBOOLEAN)){
											appLog.info("Confirmation popup get closed successfully");
										}else{
											appLog.error("Confirmation popup not get closed successfully");
											sa.assertTrue(false, "Confirmation popup not get closed successfully");
										}					
									}else{
										appLog.error("Not able to click on OK Button");
										sa.assertTrue(false, "Not able to click on OK button");		
									}
									SoftAssert saa=bp.verifyResetPasswordMailContent(M18Contact1FirstName, M18Contact1EmailId, "Contact page",Org1FirmName , CRMUser1EmailID,null);
									sa.combineAssertions(saa);	
								}else{
									appLog.error("Confirmation popup is not displaying");
									sa.assertTrue(false, "Confirmation popup is not displaying");
								}
							}else{
								appLog.error("Not able to click on send email button");
								sa.assertTrue(false, "Not able to click on send email button");
							}
						}else{
							appLog.error("Not able to click on reset password button");
							sa.assertTrue(false, "Not able to click on reset password button");				
						}
					}else{
						appLog.error("Reset Password button is not displaying");
						sa.assertTrue(false, "Reset Password button is not displaying");						
					}
				}else{
					appLog.equals("Not able to click on created contact");
					sa.assertTrue(false, "Not able to click on created contact");
				}
			}else{
				appLog.error("Not able to click on contact tab");
				sa.assertTrue(false, "Not able to click on contact tab");
			}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();				
	}

	@Test
	public void M18tc014_2_UpdatePassword(){
		BasePageBusinessLayer  bp=new BasePageBusinessLayer(driver);
		driver.get(ExcelUtils.readData("Contacts", excelLabel.Variable_Name	, "M18Contact1", excelLabel.Click_HereLink));
		String newPassword=ExcelUtils.readData("Contacts", excelLabel.Variable_Name	, "M18Contact1", excelLabel.Updated_Password2);
		if(sendKeys(driver, bp.getResetPasswordNewPasswordTextBox(60), newPassword, "New password text box", action.SCROLLANDBOOLEAN)){
			if(sendKeys(driver, bp.getResetPasswordConfirmPasswordTextBox(60), newPassword, "Confirm password text box", action.SCROLLANDBOOLEAN)){
			if(click(driver, bp.getResetPasswordButton(60), "Reset Password button", action.SCROLLANDBOOLEAN)){
				if(bp.getResetPasswordPasswordChangeMessage(60)!=null){
					if(bp.getResetPasswordPasswordChangeMessage(60).getText().equalsIgnoreCase(LoginErrorPage.resetPasswordPasswordChangeMessage)){
						appLog.info("Password change message is verified");
					}else{
						appLog.error("Password change message is not verified");
						sa.assertTrue(false, "Password change message is not verified");
					}
				}else{
					appLog.error("Password change message is not displaying");
					sa.assertTrue(false, "Password change message is not displaying");
				}
			}else{
				appLog.error("Not able to click on reset password button");
				sa.assertTrue(false, "Not able to click on reset password button");
			}			
			}else{
				appLog.error("Not able to enter value in Confirm password text box");
				sa.assertTrue(false, "Not able to enter value in Confirm password text box");
			}
		}else{
			appLog.error("Not able to enter value in new password text box");
			sa.assertTrue(false, "Not able to enter value in new password text box");
		}
		sa.assertAll();				
	}
	
	@Test
	public void M18tc015_VerifyCopyLinkFunctionalityFromContactDetailPage(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		String newPassword=ExcelUtils.readData("Contacts", excelLabel.Variable_Name	, "M18Contact1", excelLabel.Updated_Password3);
		String url="";
		lp.CRMLogin(superAdminUserName, adminPassword);
		if(cp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact1FirstName, M18Contact1LastName, null)){
				switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+" Section view");
				if(cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace,60)!=null){
					if(click(driver, cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace,60), "Reset Pasword button", action.SCROLLANDBOOLEAN)){
						ThreadSleep(2000);
				if(click(driver, cp.getResetPasswordPopupcopyLinkRadioButton(60), "Copy email radio button", action.SCROLLANDBOOLEAN)){	
					if(click(driver, cp.getResetPasswordPopupCopyEmailButton(60), "Copy email button", action.SCROLLANDBOOLEAN)){	
						appLog.info("Clicked on copy email button");	
						String aa = cp.getTextOfPropertyResetpasswordCopyLink();
						if(aa.contains(ContactPageErrorMessage.resetPasswordCopyEmailText1)){
							appLog.info("Copy link text is verified");
						}else{
							appLog.error("Copy link text is not verified");
							sa.assertTrue(false, "Copy link text is not verified");
						}
						if(aa.contains(ContactPageErrorMessage.resetPasswordCopyEmailText2)){
							appLog.info("Copy link text is verified");
						}else{
							appLog.error("Copy link text is not verified");
							sa.assertTrue(false, "Copy link text is not verified");
						}
						 url=getClipBoardData();											
					}else{
						appLog.error("Not able to click on copy email button");
						sa.assertTrue(false, "Not able to click on copy email button");
					}
				}else{
					appLog.error("Not able to click on copy email radio button");
					sa.assertTrue(false, "Not able to click on copy email radio button");
				}
					}else{
						appLog.error("Not able to click on reset password button");
						sa.assertTrue(false, "Not able to click on reset password button");				
					}
				}else{
					appLog.error("Reset Password button is not displaying");
					sa.assertTrue(false, "Reset Password button is not displaying");						
				}	
			}else{
				appLog.error("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		switchToDefaultContent(driver);
	String oldPassword=ExcelUtils.readData("Contacts", excelLabel.Variable_Name, "M18Contact1", excelLabel.Updated_Password2);
		 newPassword=ExcelUtils.readData("Contacts", excelLabel.Variable_Name	, "M18Contact1", excelLabel.Updated_Password3);
		 String str = "window.open()";
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(str, "");
		String	parentId = switchOnWindow(driver);
	if(parentId!=null){
	if(!url.isEmpty()){
	driver.get(url);
	if(sendKeys(driver, bp.getResetPasswordNewPasswordTextBox(60),newPassword , "New password text box", action.SCROLLANDBOOLEAN)){
		if(sendKeys(driver, bp.getResetPasswordConfirmPasswordTextBox(60), newPassword, "Confirm password text box", action.SCROLLANDBOOLEAN)){
		if(click(driver, bp.getResetPasswordButton(60), "Reset Password button", action.SCROLLANDBOOLEAN)){
			ThreadSleep(4000);
			if(bp.getResetPasswordPasswordChangeMessage(60)!=null){
			if(bp.getResetPasswordPasswordChangeMessage(60).getText().equalsIgnoreCase(LoginErrorPage.resetPasswordPasswordChangeMessage)){
				appLog.info("Password change message is verified");
			}else{
				appLog.error("Password change message is not verified");
				sa.assertTrue(false, "Password change message is not verified");
			}	
		}else{
				appLog.error("Password change message is not displaying");
				sa.assertTrue(false, "Password change message is not displaying");
			}
			if(sendKeys(driver, lp.getInvestorPasswordTextbox(60), oldPassword, "Target passowrd text box", action.SCROLLANDBOOLEAN)){
				if(clickUsingJavaScript(driver, lp.getInvestorLoginButton(60), "Target login button")){
					ThreadSleep(3000);
					if(isAlertPresent(driver)){
						String AlertText=switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
						if(AlertText.equalsIgnoreCase(LoginErrorPage.resetPasswordPasswordDoNotMatchErrorMessage)){
							appLog.info("Password do not match error message is verified");
						}else{
							appLog.error("Password do not match error message is not verified");
							sa.assertTrue(false, "Password do not match error message is not verified");
						}
						switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
					}				
				}else{
					appLog.error("Not able to click on login button");
					sa.assertTrue(false, "Not able to click on login button");
				}			
			}else{
				appLog.error("Not able to send value in password tetx box");
				sa.assertTrue(false, "Not able to send value in password tetx box");
			}
			if(sendKeys(driver, lp.getInvestorPasswordTextbox(60),newPassword, "Target passowrd text box", action.SCROLLANDBOOLEAN)){
				if(clickUsingJavaScript(driver, lp.getInvestorLoginButton(60), "Target login button")){
					ThreadSleep(5000);
							if(isAlertPresent(driver)){
							String alertText=switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
							if(alertText.equalsIgnoreCase(LoginErrorPage.resetPasswordSessionErrorMessage)){
								appLog.info("Session conflict error message is verfied");
							}else{
								appLog.error("Session conflict error message is not verfied");
								sa.assertTrue(false, "Session conflict error message is not verfied");
							}	
							switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);							
						}else{
							appLog.error("No alert is present");
							sa.assertTrue(false, "No alert is present");
						}											
				}else{
					appLog.error("Not able to click on login button");
					sa.assertTrue(false, "Not able to click on login button");
				}			
			}else{
				appLog.error("Not able to send value in password tetx box");
				sa.assertTrue(false, "Not able to send value in password tetx box");
			}
		}else{
			appLog.error("Not able to click on reset password button");
			sa.assertTrue(false, "Not able to click on reset password button");
		}			
		}else{
			appLog.error("Not able to enter value in Confirm password text box");
			sa.assertTrue(false, "Not able to enter value in Confirm password text box");
		}
	}else{
		appLog.error("Not able to enter value in new password text box");
		sa.assertTrue(false, "Not able to enter value in new password text box");
	}										
	}else{
		appLog.error("URL is not copied");
		sa.assertTrue(false, "url is not copied");
	}
	driver.close();
	driver.switchTo().window(parentId);			
	}else{
		appLog.error("No new window is open");
		sa.assertTrue(false, "No new window is open");
	}
	lp.CRMlogout();
	sa.assertAll();
	}
	
	
	@Test
	public void M18tc016_1_UpdateFirstNameFromInvestorSide(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		InvestorProfileBusinessLayer ip=new InvestorProfileBusinessLayer(driver);
		String newPassword=ExcelUtils.readData("Contacts", excelLabel.Variable_Name	, "M18Contact1", excelLabel.Updated_Password3);
		lp.investorLogin(M18Contact1EmailId, newPassword);
		if(click(driver, ip.getProfileLink(60), "Profile link", action.SCROLLANDBOOLEAN)){
			if(click(driver, ip.getEditIcon(60), "Edit icon", action.SCROLLANDBOOLEAN)){	
				if(sendKeys(driver, ip.getFirstNameTextBox(60), M18Contact1FirstName+"UP", "Contact 1 first name	", action.SCROLLANDBOOLEAN)){
				if(click(driver, ip.getSaveButtonMyProfilePage(60), "Save button", action.SCROLLANDBOOLEAN)){
				if(ip.getGetFirstNameInViewMode(60).getText().contains(M18Contact1FirstName+"UP"+" "+M18Contact1LastName)){
					appLog.info("First name get changed successfully");
				}else{
					appLog.error("First name not get changed successfully");
					sa.assertTrue(false, "First name not get changed successfully");
				}			
				}else{
					appLog.error("Not able to click on save button");
					sa.assertTrue(false, "Not able to click on save button");
				}
				}else{
					appLog.error("Not able to enter value in first name");
					sa.assertTrue(false, "Not able to enter value in first name");
				}
			}else{
				appLog.error("Not able to  click on edit icon");
				sa.assertTrue(false, "Not able to  click on edit icon");
			}
			}else{
				appLog.error("Not able to click on profile link");
				sa.assertTrue(false, "Not able to click on profile link");
			}
		lp.investorLogout();
		sa.assertAll();		
	}
	
	@Test
	public void M18tc016_2_UpdateFirstNameAndFirmNameAndVerifyMail(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim =new NIMPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if(cp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact1FirstName, M18Contact1LastName, null)){
				if(click(driver, cp.getEditButtonContactsPage(60), "Edit button", action.SCROLLANDBOOLEAN)){
					if(sendKeys(driver, cp.getContactFirstName(60), M18Contact1FirstName+"NUP", "Contact 1 first name", action.SCROLLANDBOOLEAN)){
					if(sendKeys(driver, cp.getContactLastName(60), M18Contact1LastName, "COntact1  last name", action.SCROLLANDBOOLEAN)){
						if(click(driver, cp.getSaveButton(60), "Save button", action.SCROLLANDBOOLEAN)){
							if(cp.getContactFullNameInViewMode(60).getText().trim().contains(M18Contact1FirstName+"NUP"+" "+M18Contact1LastName)){
								appLog.info("Contact details updated successfully");
							}else{
								appLog.info("Contact details does not update successfully");
								sa.assertTrue(false, "Contact details does not update successfully");
							}					
						}else{
							appLog.info("Not able to click on save button");
							sa.assertTrue(false, "Not able to click on save button");
						}				
					}else{
						appLog.info("Not able to enter value in contact first name textbox");
						sa.assertTrue(false, "Not able to enter value in contact first name textbox");
					}				
					}else{
						appLog.info("Not able to enter value in contact first name textbox");
						sa.assertTrue(false, "Not able to enter value in contact first name textbox");
					}
				}else{
					appLog.info("Not able to click on edit button");
					sa.assertTrue(false, "Not able to click on edit button");
				}			
			}else{
				appLog.error("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		if(nim.clickOnTab(TabName.NIMTab)){
			switchToFrame(driver, 30, nim.getNIMTabFrame(30));
		if(nim.clickOnSideMenusTab(sideMenu.MyFirmProfile))	{
			if(click(driver, nim.getEditIcon(60), "Edit icon", action.SCROLLANDBOOLEAN)){
				if(sendKeys(driver, nim.getMyFirmProfileNameTextBox(60),Org1FirmName+"Updatedfirm", "Firm name", action.SCROLLANDBOOLEAN)){
					if(click(driver, nim.getMyFirmProfilesaveBtn(60), "Save button", action.SCROLLANDBOOLEAN)){
						appLog.info("click on save button");
						ExcelUtils.writeDataInExcel(Org1FirmName+"Updatedfirm", "Users", 1, 9);				
					}else{
						appLog.error("Not able to click on save button");
						sa.assertTrue(false, "Not able to click on save button");
					}
				}else{
					appLog.error("Not able to enter firm name");
					sa.assertTrue(false, "Not able to enter firm name");
				}			
			}else{
				appLog.error("Not able to click on edit icon");
				sa.assertTrue(false, "Not able to click on edit icon");
			}
		}else{
			appLog.error("Not able to click on my firm profile");
			sa.assertTrue(false, "Not able to click on my firm profile");
		}
		}else{
			appLog.error("Not able to click on NIM Tab");
			sa.assertTrue(false, "Not able to click on nim tab");
		}
		switchToDefaultContent(driver);
	if(cp.clickOnTab(TabName.ContactTab)){
		if(cp.clickOnCreatedContact(M18Contact1FirstName, M18Contact1LastName, null)){	
			switchToFrame(driver, 30,cp. getFrame( PageName.ContactsPage, 30));
			scrollDownThroughWebelement(driver,cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+" Section view");
			if(cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace,60)!=null){
				if(click(driver, cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace,60), "Reset Pasword button", action.SCROLLANDBOOLEAN)){
					ThreadSleep(2000);
					if(click(driver, cp.getResetPasswordPopupSendEMailButton(60), "Send email", action.SCROLLANDBOOLEAN)){
						if(click(driver, cp.getResetPasswordOKButton(60), "OK Button", action.SCROLLANDBOOLEAN)){
                            if(!click(driver, cp.getResetPasswordOKButton(20), "OK", action.SCROLLANDBOOLEAN)){
                         	   appLog.info("Confirmation popup get closed successfully");
                            }
                            else{
                            		appLog.error("Confirmation popup not get closed successfully");
									sa.assertTrue(false, "Confirmation popup not get closed successfully");
								}	                            
							}else{
								appLog.error("Not able to click on OK Button");
								sa.assertTrue(false, "Not able to click on OK button");
                         }
							SoftAssert saa=cp.verifyResetPasswordMailContent(M18Contact1FirstName+"UP", M18Contact1EmailId, "Contact Page", Org1UpdatedFirmName, CRMUser1EmailID,null);
							sa.combineAssertions(saa);					
					}else{
						appLog.error("Not able to click on send email button");
						sa.assertTrue(false, "Not able to click on send email button");
					}
				}else{
					appLog.error("Not able to click on reset password button");
					sa.assertTrue(false, "Not able to click on reset password button");				
				}
			}else{
				appLog.error("Reset Password button is not displaying");
				sa.assertTrue(false, "Reset Password button is not displaying");						
			}			
		}else{
			appLog.error("Not able to click on created contact");
			sa.assertTrue(false, "Not able to click on created contact");
		}
	}else{
		appLog.error("Not able to click on contact tab");
		sa.assertTrue(false, "Not able to click on contact tab");
	}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();		
	}
	
	@Test
	public void M18tc017_1_ClearFundraisingWorkspace(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M18FundName1)){
			switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
			System.err.println("Switched to frame.");
			scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace.toString()+" View.");
		if(fp.clearWorkSpace(Workspace.FundraisingWorkspace, 60)){
			appLog.info("cleared workspace successfully");
		}else{
			appLog.error("Not able to clear the workspace");
			sa.assertTrue(false, "Not able to clear the workspace");
		}			
			}else{
				appLog.error("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}			
		}else{
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");			
		}
		switchToDefaultContent(driver);
		if(bp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact1FirstName, M18Contact1LastName, null)){
				switchToFrame(driver, 30, bp.getFrame( PageName.ContactsPage, 60));
				System.err.println("Switched to frame.");
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace.toString()+" View.");
				if (bp.verifyErrorMessageOnPage(
						ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace,
						cp.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60),
						"Error Message after admin Registration on Contact page for fundraising Workspace")) {
					appLog.info("Error Message is verified  on Contact page for fundraising workspace");
				} else {
					sa.assertTrue(false,
							"Error Message is not verified on Contact page for fundraising workspace.Expected:"
									+ ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace
									+ " Actual"
									+ getText(driver,
											cp.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60),
											"Error Message before admin registration on contact page", action.BOOLEAN));
				}			
			}else{
				appLog.error("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();			
	}
	
	@Test
	public void M18tc017_2_CheckTheImpactWhenEnterRegisterInvestorContact(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		String msg;
		WebElement ele;
		driver.get(ExcelUtils.readDataFromPropertyFile("InvestorURL"));
		CommonLib.waitForPageLoad(driver);
		String windowID= driver.getWindowHandle();
		if (click(driver, lp.getResetPasswordLink(60), "reset Password Link", action.BOOLEAN)) {
			CommonLib.waitForPageLoad(driver);
			if(windowID.equals(driver.getWindowHandle())){
				appLog.info("reset password page is open in same window. ");
				if (sendKeys(driver, lp.getResetPasswordUserNameTextBox(60), M18Contact1EmailId,
						"Reset Username Text Box : " + M18Contact1EmailId, action.BOOLEAN)) {
					if (click(driver, lp.getResetPasswordSubmitButton(60), "Reset Password Submit Button",
							action.BOOLEAN)) {
						ele = lp.getResetPasswordConfirmationTemproryPasswdMsg(30);
						if (ele != null) {
							msg = ele.getText().trim();
							if (msg.contains(LoginErrorPage.resetPasswordConfirmationTemproryPasswdMsg)) {
								appLog.info(" Tempory Password Message  Matched for Invited Target: " + msg);
							} else {
								appLog.error(" Tempory Password Message Not Matched for Invited Target: " + "Expected"
										+ LoginErrorPage.resetPasswordConfirmationTemproryPasswdMsg + "/tActual" + msg);
								sa.assertTrue(false,
										"Tempory Password  Message Not Matched for Invited Target: " + "Expected"
												+ LoginErrorPage.resetPasswordConfirmationTemproryPasswdMsg + "/tActual"
												+ msg);
							}
						} else {
							appLog.error("Msg ele is null for Temprory Password");
							sa.assertTrue(false, "Msg ele is null for Temprory Password");
						}
						SoftAssert saa=bp.verifyResetPasswordMailContent(M18Contact1FirstName+"UP", M18Contact1EmailId, "InvestorPage", null, null,null);
						sa.combineAssertions(saa);
					} else {
						appLog.error("Not Able to Click reset Submit Button");
						sa.assertTrue(false, "Not Able to Click reset Submit Button");
					}
				} else {
					appLog.error("Not Able to Enter value to reset pass username TextBox");
					sa.assertTrue(false, "Not Able to Enter value to reset pass username TextBox");
				}
			}else{
				appLog.error("reset password page is not open in same window. ");
				sa.assertTrue(false,"reset password page is not open in same window. ");
			}	
		} else {
			appLog.error("Not Able to Click reset Pass Link");
			sa.assertTrue(false, "Not Able to Click reset Pass Link");
		}
		sa.assertAll();				
	}

	@Test
	public void M18tc018_BuildTheInvestorWorkspaceAndInviteTheContacts(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		String[] step1of3 = {ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_Size), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_VintageYear),
				ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_Contact), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_Phone),
				ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_Email), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_Description) };
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M18FundName1)) {
				if (fp.buildWorkspace(step1of3, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null,
						M18Institution1+"/"+M18LimitedPartner1 , Workspace.InvestorWorkspace, 60)) {
					appLog.info("Investor Workspace is created successfully");
				} else {
					appLog.info("Investor workspace is not created successfully");
					sa.assertTrue(false, " Investor workspace is not craeted successfully");
				}				
		switchToDefaultContent(driver);
		if (fp.inviteContact(environment, mode, M18Institution1, M18Contact1EmailId, null, FolderType.Standard,
				"Upload", "Yes", "No", null, Workspace.InvestorWorkspace, null)) {
			appLog.info("Successfully provided access to contact '" + M18Contact1FirstName + " "
					+ M18Contact1LastName + "'.");
		} else {
			appLog.error("Not able to provide access to contact '" + M18Contact1FirstName + " "
					+ M18Contact1LastName + "'.");
			sa.assertTrue(false, "Not able to provide access to contact '" + M18Contact1FirstName + " "
					+ M18Contact1LastName + "'.");
		}
		if (fp.inviteContact(environment, mode, M18Institution1, M18Contact2EmailId, null, FolderType.Standard,
				"Upload", "Yes", "No", null, Workspace.InvestorWorkspace, null)) {
			appLog.info("Successfully provided access to contact '" + M18Contact2FirstName + " "
					+ M18Contact2LastName + "'.");
		} else {
			appLog.error("Not able to provide access to contact '" + M18Contact2FirstName + " "
					+ M18Contact2LastName + "'.");
			sa.assertTrue(false, "Not able to provide access to contact '" + M18Contact2FirstName + " "
					+ M18Contact2LastName + "'.");
		}
			} else {
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab so we cannot create workspace");
			sa.assertTrue(false, "Not able to click on Funds tab so we cannot create workspace");
		}
		switchToDefaultContent(driver);
		if(bp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact1FirstName, M18Contact1LastName, null)){
				switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace.toString()+" Section view");
			if(cp.getResetPasswordActiveButton(Workspace.InvestorWorkspace, 60)!=null){
				appLog.info("Reset password button is displaying in enabled mode");
			}else{
				appLog.error("Reset password button is not displaying in enabled mode");
				sa.assertTrue(false, "Reset password button is not displaying in enabled mode");
			}				
			}else{
				appLog.equals("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}		
		switchToDefaultContent(driver);
		if(bp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact2FirstName, M18Contact2LastName, null)){
				switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace.toString()+" Section view");
			if(cp.getResetPasswordInactiveButton(Workspace.InvestorWorkspace, 60)!=null){
				appLog.info("Reset password button is displaying in disabled mode");
			}else{
				appLog.error("Reset password button is not displaying in disabled mode");
				sa.assertTrue(false, "Reset password button is not displaying in disabled mode");
			}				
			}else{
				appLog.equals("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}	
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();			
	}

	@Test
	public void M18tc019_1_SendResetPasswordEmailFromContactPage(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID	, adminPassword);
		if(cp.clickOnTab(TabName.ContactTab)){
				if(cp.clickOnCreatedContact(M18Contact1FirstName+"NUP", M18Contact1LastName, null)){
					switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
					scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace.toString()+" Section view");
					if(cp.getResetPasswordActiveButton(Workspace.InvestorWorkspace,60)!=null){
						if(click(driver, cp.getResetPasswordActiveButton(Workspace.InvestorWorkspace,60), "Reset Pasword button", action.SCROLLANDBOOLEAN)){		
							if(click(driver, cp.getResetPasswordPopupSendEMailButton(60), "Send Email", action.SCROLLANDBOOLEAN)){
								if(cp.getResetPasswordPopupHeaderAfterSendEMail(60)!=null){
									if(cp.getResetPasswordPopupHeaderAfterSendEMail(60).getText().trim().contains("Confirmation")){
										appLog.info("Header text is verified");					
									}else{
										appLog.error("Header text is not verified");
										sa.assertTrue(false, "Header Text is not verified");
									}
									if(cp.getResetPasswordPopupCrossIconAfterSendEMail(60)!=null){
										appLog.info("Cross icon is available");
									}else{
										appLog.error("Cross icon is not available");
										sa.assertTrue(false, "Cross icon is not available");
									}
									if(cp.getResetPasswordOKButton(60)!=null){
										appLog.info("OK Button is available");
									}else{
										appLog.error("OK Button is not available");
										sa.assertTrue(false, "OK Button is not available");
									}
									if(cp.getResetPasswordMessageAfterSendEMail(60).getText().trim().contains("Reset password email sent to "+M18Contact1EmailId+" successfully.")){
										appLog.info("Reset password message is verified");				
									}else{
										appLog.error("Reset password message is not verified");
										sa.assertTrue(false, "Reset password message is not verified");
									}
									if(click(driver, cp.getResetPasswordOKButton(60), "OK Button", action.SCROLLANDBOOLEAN)){
										if(!click(driver, cp.getResetPasswordOKButton(5), "ok", action.SCROLLANDBOOLEAN)){
											appLog.info("Confirmation popup get closed successfully");
										}else{
											appLog.error("Confirmation popup not get closed successfully");
											sa.assertTrue(false, "Confirmation popup not get closed successfully");
										}					
									}else{
										appLog.error("Not able to click on OK Button");
										sa.assertTrue(false, "Not able to click on OK button");		
									}
									SoftAssert saa=bp.verifyResetPasswordMailContent(M18Contact1FirstName+"UP", M18Contact1EmailId, "Contact page",Org1UpdatedFirmName , CRMUser1EmailID,null);
									sa.combineAssertions(saa);	
								}else{
									appLog.error("Confirmation popup is not displaying");
									sa.assertTrue(false, "Confirmation popup is not displaying");
								}
							}else{
								appLog.error("Not able to click on send email button");
								sa.assertTrue(false, "Not able to click on send email button");
							}
						}else{
							appLog.error("Not able to click on reset password button");
							sa.assertTrue(false, "Not able to click on reset password button");				
						}
					}else{
						appLog.error("Reset Password button is not displaying");
						sa.assertTrue(false, "Reset Password button is not displaying");						
					}
				}else{
					appLog.equals("Not able to click on created contact");
					sa.assertTrue(false, "Not able to click on created contact");
				}
			}else{
				appLog.error("Not able to click on contact tab");
				sa.assertTrue(false, "Not able to click on contact tab");
			}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();				
	}

	@Test
	public void M18tc019_2_UpdatePassword(){
		BasePageBusinessLayer  bp=new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer afp=new AllFirmsPageBusinesslayer(driver);
		driver.get(ExcelUtils.readData("Contacts", excelLabel.Variable_Name	, "M18Contact1", excelLabel.Click_HereLink));
		String newPassword=ExcelUtils.readData("Contacts", excelLabel.Variable_Name	, "M18Contact1", excelLabel.Updated_Password4);
		if(sendKeys(driver, bp.getResetPasswordNewPasswordTextBox(60), newPassword, "New password text box", action.SCROLLANDBOOLEAN)){
			if(sendKeys(driver, bp.getResetPasswordConfirmPasswordTextBox(60), newPassword, "Confirm password text box", action.SCROLLANDBOOLEAN)){
			if(click(driver, bp.getResetPasswordButton(60), "Reset Password button", action.SCROLLANDBOOLEAN)){
				if(bp.getResetPasswordPasswordChangeMessage(60)!=null){
					if(bp.getResetPasswordPasswordChangeMessage(60).getText().equalsIgnoreCase(LoginErrorPage.resetPasswordPasswordChangeMessage)){
						appLog.info("Password change message is verified");
						if(sendKeys(driver, lp.getInvestorPasswordTextbox(60),newPassword, "Target passowrd text box", action.SCROLLANDBOOLEAN)){
							if(click(driver, lp.getInvestorLoginButton(60), "Target login button", action.SCROLLANDBOOLEAN)){
								ThreadSleep(5000);
								if(afp.getFirmNameDropdown(60)!=null){
									if(getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Select deal dropdown", "text").equalsIgnoreCase(Org1UpdatedFirmName)){
										appLog.info("Investor home page is displaying");
									}else{
										appLog.error("Investor home page is not displaying");
										sa.assertTrue(false, "Investor home page is not displaying");
									}				
								}else{
									appLog.error("Select firm dropdown is not displaying");
									sa.assertTrue(false, "Select firm dropdown is not displaying");
								}											
							}else{
								appLog.error("Not able to click on login button");
								sa.assertTrue(false, "Not able to click on login button");
							}			
						}else{
							appLog.error("Not able to send value in password tetx box");
							sa.assertTrue(false, "Not able to send value in password tetx box");
						}						
					}else{
						appLog.error("Password change message is not verified");
						sa.assertTrue(false, "Password change message is not verified");
					}
				}else{
					appLog.error("Password change message is not displaying");
					sa.assertTrue(false, "Password change message is not displaying");
				}
			}else{
				appLog.error("Not able to click on reset password button");
				sa.assertTrue(false, "Not able to click on reset password button");
			}			
			}else{
				appLog.error("Not able to enter value in Confirm password text box");
				sa.assertTrue(false, "Not able to enter value in Confirm password text box");
			}
		}else{
			appLog.error("Not able to enter value in new password text box");
			sa.assertTrue(false, "Not able to enter value in new password text box");
		}
		lp.investorLogout();
		sa.assertAll();				
	}
	
	@Test
	public void M18tc020_VerifyCopyLinkFunctionalityFromContactDetailPage(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		String url="";
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(cp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact1FirstName+"NUP", M18Contact1LastName, null)){
				switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace.toString()+" Section view");
				if(cp.getResetPasswordActiveButton(Workspace.InvestorWorkspace,60)!=null){
					if(click(driver, cp.getResetPasswordActiveButton(Workspace.InvestorWorkspace,60), "Reset Pasword button", action.SCROLLANDBOOLEAN)){
						ThreadSleep(2000);
				if(click(driver, cp.getResetPasswordPopupcopyLinkRadioButton(60), "Copy email radio button", action.SCROLLANDBOOLEAN)){	
					if(click(driver, cp.getResetPasswordPopupCopyEmailButton(60), "Copy email button", action.SCROLLANDBOOLEAN)){	
						appLog.info("Clicked on copy email button");
						ThreadSleep(4000);					
						String aa = cp.getTextOfPropertyResetpasswordCopyLink();
						if(aa.contains(ContactPageErrorMessage.resetPasswordCopyEmailText1)){
							appLog.info("Copy link text is verified");
						}else{
							appLog.error("Copy link text is not verified");
							sa.assertTrue(false, "Copy link text is not verified");
						}
						if(aa.contains(ContactPageErrorMessage.resetPasswordCopyEmailText2)){
							appLog.info("Copy link text is verified");
						}else{
							appLog.error("Copy link text is not verified");
							sa.assertTrue(false, "Copy link text is not verified");
						}
						 url=getClipBoardData();
					}else{
						appLog.error("Not able to click on copy email button");
						sa.assertTrue(false, "Not able to click on copy email button");
					}
				}else{
					appLog.error("Not able to click on copy email radio button");
					sa.assertTrue(false, "Not able to click on copy email radio button");
				}
					}else{
						appLog.error("Not able to click on reset password button");
						sa.assertTrue(false, "Not able to click on reset password button");				
					}
				}else{
					appLog.error("Reset Password button is not displaying");
					sa.assertTrue(false, "Reset Password button is not displaying");						
				}
			
			}else{
				appLog.error("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		bp=new BasePageBusinessLayer(driver);
		lp=new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer afp=new AllFirmsPageBusinesslayer(driver);
		String oldPassword=ExcelUtils.readData("Contacts", excelLabel.Variable_Name	, "M18Contact1", excelLabel.Updated_Password4);
		String newPassword=ExcelUtils.readData("Contacts", excelLabel.Variable_Name	, "M18Contact1", excelLabel.Updated_Password5);
		if(!url.isEmpty()){
			driver.get(url);	
		if(bp.getResetPasswordButton(60)!=null){
			appLog.info("Reset password page is open");
			if(sendKeys(driver, bp.getResetPasswordNewPasswordTextBox(60),newPassword , "New password text box", action.SCROLLANDBOOLEAN)){
				if(sendKeys(driver, bp.getResetPasswordConfirmPasswordTextBox(60), newPassword, "Confirm password text box", action.SCROLLANDBOOLEAN)){
				if(click(driver, bp.getResetPasswordButton(60), "Reset Password button", action.SCROLLANDBOOLEAN)){
					ThreadSleep(4000);
					if (getURL(driver, 60).contains("IP_login")) {
						appLog.info("Investor Login page is open in same window. ");
					} else {
						appLog.error("Investor Login page is not opening. " + getURL(driver, 30));
						sa.assertTrue(false, "Some different url is opening. " + getURL(driver, 30));
					}			
					if(bp.getResetPasswordPasswordChangeMessage(60)!=null){
					if(bp.getResetPasswordPasswordChangeMessage(60).getText().equalsIgnoreCase(LoginErrorPage.resetPasswordPasswordChangeMessage)){
						appLog.info("Password change message is verified");
					}else{
						appLog.error("Password change message is not verified");
						sa.assertTrue(false, "Password change message is not verified");
					}	
				}else{
						appLog.error("Password change message is not displaying");
						sa.assertTrue(false, "Password change message is not displaying");
					}
				if(sendKeys(driver, lp.getInvestorPasswordTextbox(60), oldPassword, "investor passowrd text box", action.SCROLLANDBOOLEAN)){
					if(clickUsingJavaScript(driver, lp.getInvestorLoginButton(60), "investor login button")){
						ThreadSleep(3000);
						if(isAlertPresent(driver)){
							String AlertText=switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
							if(AlertText.equalsIgnoreCase(LoginErrorPage.resetPasswordPasswordDoNotMatchErrorMessage)){
								appLog.info("Password do not match error message is verified");
							}else{
								appLog.error("Password do not match error message is not verified");
								sa.assertTrue(false, "Password do not match error message is not verified");
							}
							switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
						}				
					}else{
						appLog.error("Not able to click on login button");
						sa.assertTrue(false, "Not able to click on login button");
					}			
				}else{
					appLog.error("Not able to send value in password tetx box");
					sa.assertTrue(false, "Not able to send value in password tetx box");
				}
				if(sendKeys(driver, lp.getInvestorPasswordTextbox(60),newPassword, "investor passowrd text box", action.SCROLLANDBOOLEAN)){
					if(click(driver, lp.getInvestorLoginButton(60), "investor login button", action.SCROLLANDBOOLEAN)){
						ThreadSleep(5000);
						if(afp.getFirmNameDropdown(60)!=null){
							if(getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Select deal dropdown", "text").equalsIgnoreCase(Org1UpdatedFirmName)){
								appLog.info("Investor home page is displaying");
							}else{
								appLog.error("Investor home page is not displaying");
								sa.assertTrue(false, "Investor home page is not displaying");
							}				
						}else{
							appLog.error("Select firm dropdown is not displaying");
							sa.assertTrue(false, "Select firm dropdown is not displaying");
						}
					}else{
						appLog.error("Not able to click on login button");
						sa.assertTrue(false, "Not able to click on login button");
					}			
				}else{
					appLog.error("Not able to send value in password tetx box");
					sa.assertTrue(false, "Not able to send value in password tetx box");
				}
				}else{
					appLog.error("Not able to click on reset password button");
					sa.assertTrue(false, "Not able to click on reset password button");
				}			
				}else{
					appLog.error("Not able to enter value in Confirm password text box");
					sa.assertTrue(false, "Not able to enter value in Confirm password text box");
				}
			}else{
				appLog.error("Not able to enter value in new password text box");
				sa.assertTrue(false, "Not able to enter value in new password text box");
			}		
		}else{
			appLog.error("Reset password page is not open");
			sa.assertTrue(false, "Reset password page is not open");
		}
		}else{
			appLog.error("URL is not copied");
			sa.assertTrue(false, "url is not copied");
		}
		lp.investorLogout();
		sa.assertAll();			
		}
		
	@Test
	public void M18tc021_BuildTheFundraisingWorkspaceAndInviteTheContactsFromContactAccess(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		String[] step1of3 = {ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_Size), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_VintageYear),
				ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_Contact), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_Phone),
				ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_Email), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_Description) };
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M18FundName1)) {
				if (fp.buildWorkspace(step1of3, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null,
						M18Institution1 , Workspace.FundraisingWorkspace, 60)) {
					appLog.info("Fundraising Workspace is created successfully");
				} else {
					appLog.info("Fundraising workspace is not created successfully");
					sa.assertTrue(false, " Fundraising workspace is not craeted successfully");
				}				
		switchToDefaultContent(driver);
		if (fp.inviteContact(environment, mode, M18Institution1, M18Contact1EmailId, null, FolderType.Standard,
				"Upload", "Yes", "No", null, Workspace.FundraisingWorkspace, null)) {
			appLog.info("Successfully provided access to contact '" + M18Contact1FirstName + " "
					+ M18Contact1LastName + "'.");
		} else {
			appLog.error("Not able to provide access to contact '" + M18Contact1FirstName + " "
					+ M18Contact1LastName + "'.");
			sa.assertTrue(false, "Not able to provide access to contact '" + M18Contact1FirstName + " "
					+ M18Contact1LastName + "'.");
		}
		if (fp.inviteContact(environment, mode, M18Institution1, M18Contact2EmailId, null, FolderType.Standard,
				"Upload", "Yes", "No", null, Workspace.FundraisingWorkspace, null)) {
			appLog.info("Successfully provided access to contact '" + M18Contact2FirstName + " "
					+ M18Contact2LastName + "'.");
		} else {
			appLog.error("Not able to provide access to contact '" + M18Contact2FirstName + " "
					+ M18Contact2LastName + "'.");
			sa.assertTrue(false, "Not able to provide access to contact '" + M18Contact2FirstName + " "
					+ M18Contact2LastName + "'.");
		}
			} else {
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab so we cannot create workspace");
			sa.assertTrue(false, "Not able to click on Funds tab so we cannot create workspace");
		}
		switchToDefaultContent(driver);
		if(bp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact1FirstName+"NUP", M18Contact1LastName, null)){
				switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+" Section view");
			if(cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace, 60)!=null){
				appLog.info("Reset password button is displaying in enabled mode");
			}else{
				appLog.error("Reset password button is not displaying in enabled mode");
				sa.assertTrue(false, "Reset password button is not displaying in enabled mode");
			}	
			if(cp.getResetPasswordInactiveButton(Workspace.InvestorWorkspace, 10)==null || cp.getResetPasswordActiveButton(Workspace.InvestorWorkspace, 10)==null){
				appLog.info("Reset password button is not displaying in Investor Workspace");
			}else{
				appLog.error("Reset password button is displaying in Investor Workspace");
				sa.assertTrue(false, "Reset password button is displaying in Investor Workspace");
			}		
			}else{
				appLog.equals("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}		
		switchToDefaultContent(driver);
		if(bp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact2FirstName, M18Contact2LastName, null)){
				switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+" Section view");
			if(cp.getResetPasswordInactiveButton(Workspace.FundraisingWorkspace, 60)!=null){
				appLog.info("Reset password button is displaying in disabled mode");
			}else{
				appLog.error("Reset password button is not displaying in disabled mode");
				sa.assertTrue(false, "Reset password button is not displaying in disabled mode");
			}		
			if(cp.getResetPasswordInactiveButton(Workspace.InvestorWorkspace, 10)==null && cp.getResetPasswordActiveButton(Workspace.InvestorWorkspace, 10)==null){
				appLog.info("Reset password button is not displaying in Investor Workspace");
			}else{
				appLog.error("Reset password button is displaying in Investor Workspace");
				sa.assertTrue(false, "Reset password button is displaying in Investor Workspace");
			}		
			}else{
				appLog.equals("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}	
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();		
	}
	
	@Test
	public void M18tc022_1_SendResetPasswordEmailFromContactPage(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID	, adminPassword);
		if(cp.clickOnTab(TabName.ContactTab)){
				if(cp.clickOnCreatedContact(M18Contact1FirstName+"NUP", M18Contact1LastName, null)){
					switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
					scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+" Section view");
					if(cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace,60)!=null){
						if(click(driver, cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace,60), "Reset Pasword button", action.SCROLLANDBOOLEAN)){		
							if(click(driver, cp.getResetPasswordPopupSendEMailButton(60), "Send Email", action.SCROLLANDBOOLEAN)){
								if(cp.getResetPasswordPopupHeaderAfterSendEMail(60)!=null){
									if(cp.getResetPasswordPopupHeaderAfterSendEMail(60).getText().trim().contains("Confirmation")){
										appLog.info("Header text is verified");					
									}else{
										appLog.error("Header text is not verified");
										sa.assertTrue(false, "Header Text is not verified");
									}
									if(cp.getResetPasswordPopupCrossIconAfterSendEMail(60)!=null){
										appLog.info("Cross icon is available");
									}else{
										appLog.error("Cross icon is not available");
										sa.assertTrue(false, "Cross icon is not available");
									}
									if(cp.getResetPasswordOKButton(60)!=null){
										appLog.info("OK Button is available");
									}else{
										appLog.error("OK Button is not available");
										sa.assertTrue(false, "OK Button is not available");
									}
									if(cp.getResetPasswordMessageAfterSendEMail(60).getText().trim().contains("Reset password email sent to "+M18Contact1EmailId+" successfully.")){
										appLog.info("Reset password message is verified");				
									}else{
										appLog.error("Reset password message is not verified");
										sa.assertTrue(false, "Reset password message is not verified");
									}
									if(click(driver, cp.getResetPasswordOKButton(60), "OK Button", action.SCROLLANDBOOLEAN)){
										if(!click(driver, cp.getResetPasswordOKButton(5), "ok", action.SCROLLANDBOOLEAN)){
											appLog.info("Confirmation popup get closed successfully");
										}else{
											appLog.error("Confirmation popup not get closed successfully");
											sa.assertTrue(false, "Confirmation popup not get closed successfully");
										}					
									}else{
										appLog.error("Not able to click on OK Button");
										sa.assertTrue(false, "Not able to click on OK button");		
									}
									SoftAssert saa=bp.verifyResetPasswordMailContent(M18Contact1FirstName+"UP", M18Contact1EmailId, "Contact page",Org1UpdatedFirmName , CRMUser1EmailID,null);
									sa.combineAssertions(saa);	
								}else{
									appLog.error("Confirmation popup is not displaying");
									sa.assertTrue(false, "Confirmation popup is not displaying");
								}
							}else{
								appLog.error("Not able to click on send email button");
								sa.assertTrue(false, "Not able to click on send email button");
							}
						}else{
							appLog.error("Not able to click on reset password button");
							sa.assertTrue(false, "Not able to click on reset password button");				
						}
					}else{
						appLog.error("Reset Password button is not displaying");
						sa.assertTrue(false, "Reset Password button is not displaying");						
					}
				}else{
					appLog.equals("Not able to click on created contact");
					sa.assertTrue(false, "Not able to click on created contact");
				}
			}else{
				appLog.error("Not able to click on contact tab");
				sa.assertTrue(false, "Not able to click on contact tab");
			}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();	
	}
		
	@Test
	public void M18tc022_2_UpdatePassword(){
		BasePageBusinessLayer  bp=new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer afp=new AllFirmsPageBusinesslayer(driver);
		driver.get(ExcelUtils.readData("Contacts", excelLabel.Variable_Name	, "M18Contact1", excelLabel.Click_HereLink));
		String newPassword=ExcelUtils.readData("Contacts", excelLabel.Variable_Name	, "M18Contact1", excelLabel.Updated_Password6);
		if(sendKeys(driver, bp.getResetPasswordNewPasswordTextBox(60), newPassword, "New password text box", action.SCROLLANDBOOLEAN)){
			if(sendKeys(driver, bp.getResetPasswordConfirmPasswordTextBox(60), newPassword, "Confirm password text box", action.SCROLLANDBOOLEAN)){
			if(click(driver, bp.getResetPasswordButton(60), "Reset Password button", action.SCROLLANDBOOLEAN)){
				if(bp.getResetPasswordPasswordChangeMessage(60)!=null){
					if(bp.getResetPasswordPasswordChangeMessage(60).getText().equalsIgnoreCase(LoginErrorPage.resetPasswordPasswordChangeMessage)){
						appLog.info("Password change message is verified");
						if(sendKeys(driver, lp.getInvestorPasswordTextbox(60),newPassword, "Target passowrd text box", action.SCROLLANDBOOLEAN)){
							if(click(driver, lp.getInvestorLoginButton(60), "Target login button", action.SCROLLANDBOOLEAN)){
								ThreadSleep(5000);
								if(afp.getFirmNameDropdown(60)!=null){
									if(getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Select deal dropdown", "text").equalsIgnoreCase(Org1UpdatedFirmName)){
										appLog.info("Investor home page is displaying");
									}else{
										appLog.error("Investor home page is not displaying");
										sa.assertTrue(false, "Investor home page is not displaying");
									}				
								}else{
									appLog.error("Select firm dropdown is not displaying");
									sa.assertTrue(false, "Select firm dropdown is not displaying");
								}											
							}else{
								appLog.error("Not able to click on login button");
								sa.assertTrue(false, "Not able to click on login button");
							}			
						}else{
							appLog.error("Not able to send value in password tetx box");
							sa.assertTrue(false, "Not able to send value in password tetx box");
						}						
					}else{
						appLog.error("Password change message is not verified");
						sa.assertTrue(false, "Password change message is not verified");
					}
				}else{
					appLog.error("Password change message is not displaying");
					sa.assertTrue(false, "Password change message is not displaying");
				}
			}else{
				appLog.error("Not able to click on reset password button");
				sa.assertTrue(false, "Not able to click on reset password button");
			}			
			}else{
				appLog.error("Not able to enter value in Confirm password text box");
				sa.assertTrue(false, "Not able to enter value in Confirm password text box");
			}
		}else{
			appLog.error("Not able to enter value in new password text box");
			sa.assertTrue(false, "Not able to enter value in new password text box");
		}
		lp.investorLogout();
		sa.assertAll();				
	}
	
	@Test
	public void M18tc023_VerifyCopyLinkFunctionalityFromContactDetailPage(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		String url="";
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(cp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact1FirstName+"NUP", M18Contact1LastName, null)){
				switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+" Section view");
				if(cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace,60)!=null){
					if(click(driver, cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace,60), "Reset Pasword button", action.SCROLLANDBOOLEAN)){
						ThreadSleep(2000);
				if(click(driver, cp.getResetPasswordPopupcopyLinkRadioButton(60), "Copy email radio button", action.SCROLLANDBOOLEAN)){	
					if(click(driver, cp.getResetPasswordPopupCopyEmailButton(60), "Copy email button", action.SCROLLANDBOOLEAN)){	
						appLog.info("Clicked on copy email button");
						if(cp.getResetPasswordPopupCopyEmailText(60).getText().contains(ContactPageErrorMessage.resetPasswordCopyEmailText1)){
							appLog.info("Copy link text is verified");
						}else{
							appLog.error("Copy link text is not verified");
							sa.assertTrue(false, "Copy link text is not verified");
						}
						if(cp.getResetPasswordPopupCopyEmailText(60).getText().contains(ContactPageErrorMessage.resetPasswordCopyEmailText2)){
							appLog.info("Copy link text is verified");
						}else{
							appLog.error("Copy link text is not verified");
							sa.assertTrue(false, "Copy link text is not verified");
						}
						 url=getClipBoardData();
					}else{
						appLog.error("Not able to click on copy email button");
						sa.assertTrue(false, "Not able to click on copy email button");
					}
				}else{
					appLog.error("Not able to click on copy email radio button");
					sa.assertTrue(false, "Not able to click on copy email radio button");
				}
					}else{
						appLog.error("Not able to click on reset password button");
						sa.assertTrue(false, "Not able to click on reset password button");				
					}
				}else{
					appLog.error("Reset Password button is not displaying");
					sa.assertTrue(false, "Reset Password button is not displaying");						
				}
			
			}else{
				appLog.error("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		bp=new BasePageBusinessLayer(driver);
		lp=new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer afp=new AllFirmsPageBusinesslayer(driver);
		String oldPassword=ExcelUtils.readData("Contacts", excelLabel.Variable_Name	, "M18Contact1", excelLabel.Updated_Password6);
		String newPassword=ExcelUtils.readData("Contacts", excelLabel.Variable_Name	, "M18Contact1", excelLabel.Updated_Password7);
		if(!url.isEmpty()){
			driver.get(url);	
		if(bp.getResetPasswordButton(60)!=null){
			appLog.info("Reset password page is open");
			if(sendKeys(driver, bp.getResetPasswordNewPasswordTextBox(60),newPassword , "New password text box", action.SCROLLANDBOOLEAN)){
				if(sendKeys(driver, bp.getResetPasswordConfirmPasswordTextBox(60), newPassword, "Confirm password text box", action.SCROLLANDBOOLEAN)){
				if(click(driver, bp.getResetPasswordButton(60), "Reset Password button", action.SCROLLANDBOOLEAN)){
					ThreadSleep(4000);
					if (getURL(driver, 60).contains("IP_login")) {
						appLog.info("Investor Login page is open in same window. ");
					} else {
						appLog.error("Investor Login page is not opening. " + getURL(driver, 30));
						sa.assertTrue(false, "Some different url is opening. " + getURL(driver, 30));
					}			
					if(bp.getResetPasswordPasswordChangeMessage(60)!=null){
					if(bp.getResetPasswordPasswordChangeMessage(60).getText().equalsIgnoreCase(LoginErrorPage.resetPasswordPasswordChangeMessage)){
						appLog.info("Password change message is verified");
					}else{
						appLog.error("Password change message is not verified");
						sa.assertTrue(false, "Password change message is not verified");
					}	
				}else{
						appLog.error("Password change message is not displaying");
						sa.assertTrue(false, "Password change message is not displaying");
					}
				if(sendKeys(driver, lp.getInvestorPasswordTextbox(60), oldPassword, "investor passowrd text box", action.SCROLLANDBOOLEAN)){
					if(clickUsingJavaScript(driver, lp.getInvestorLoginButton(60), "investor login button")){
						ThreadSleep(3000);
						if(isAlertPresent(driver)){
							String AlertText=switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
							if(AlertText.equalsIgnoreCase(LoginErrorPage.resetPasswordPasswordDoNotMatchErrorMessage)){
								appLog.info("Password do not match error message is verified");
							}else{
								appLog.error("Password do not match error message is not verified");
								sa.assertTrue(false, "Password do not match error message is not verified");
							}
							switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
						}				
					}else{
						appLog.error("Not able to click on login button");
						sa.assertTrue(false, "Not able to click on login button");
					}			
				}else{
					appLog.error("Not able to send value in password tetx box");
					sa.assertTrue(false, "Not able to send value in password tetx box");
				}
				if(sendKeys(driver, lp.getInvestorPasswordTextbox(60),newPassword, "investor passowrd text box", action.SCROLLANDBOOLEAN)){
					if(click(driver, lp.getInvestorLoginButton(60), "investor login button", action.SCROLLANDBOOLEAN)){
						ThreadSleep(5000);
						if(afp.getFirmNameDropdown(60)!=null){
							if(getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Select deal dropdown", "text").equalsIgnoreCase(Org1UpdatedFirmName)){
								appLog.info("Investor home page is displaying");
							}else{
								appLog.error("Investor home page is not displaying");
								sa.assertTrue(false, "Investor home page is not displaying");
							}				
						}else{
							appLog.error("Select firm dropdown is not displaying");
							sa.assertTrue(false, "Select firm dropdown is not displaying");
						}
					}else{
						appLog.error("Not able to click on login button");
						sa.assertTrue(false, "Not able to click on login button");
					}			
				}else{
					appLog.error("Not able to send value in password tetx box");
					sa.assertTrue(false, "Not able to send value in password tetx box");
				}
				}else{
					appLog.error("Not able to click on reset password button");
					sa.assertTrue(false, "Not able to click on reset password button");
				}			
				}else{
					appLog.error("Not able to enter value in Confirm password text box");
					sa.assertTrue(false, "Not able to enter value in Confirm password text box");
				}
			}else{
				appLog.error("Not able to enter value in new password text box");
				sa.assertTrue(false, "Not able to enter value in new password text box");
			}		
		}else{
			appLog.error("Reset password page is not open");
			sa.assertTrue(false, "Reset password page is not open");
		}
		}else{
			appLog.error("URL is not copied");
			sa.assertTrue(false, "url is not copied");
		}
		lp.investorLogout();
		sa.assertAll();			
	}
	
	@Test
	public void M18tc024_ClearFundraisingWorkspaceAndVerifyResetPasswordButtonOnContactDetailPage(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M18FundName1)){
			switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
			System.err.println("Switched to frame.");
			scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace.toString()+" View.");
		if(fp.clearWorkSpace(Workspace.FundraisingWorkspace, 60)){
			appLog.info("cleared workspace successfully");
		}else{
			appLog.error("Not able to clear the workspace");
			sa.assertTrue(false, "Not able to clear the workspace");
		}			
			}else{
				appLog.error("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}			
		}else{
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");			
		}
		switchToDefaultContent(driver);
		if(bp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact1FirstName+"NUP", M18Contact1LastName, null)){
				switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace.toString()+" Section view");
			if(cp.getResetPasswordActiveButton(Workspace.InvestorWorkspace, 60)!=null){
				appLog.info("Reset password button is displaying in enabled mode");
			}else{
				appLog.error("Reset password button is not displaying in enabled mode");
				sa.assertTrue(false, "Reset password button is not displaying in enabled mode");
			}				
			}else{
				appLog.equals("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}		
		switchToDefaultContent(driver);
		if(bp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact2FirstName, M18Contact2LastName, null)){
				switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace.toString()+" Section view");
			if(cp.getResetPasswordInactiveButton(Workspace.InvestorWorkspace, 60)!=null){
				appLog.info("Reset password button is displaying in disabled mode");
			}else{
				appLog.error("Reset password button is not displaying in disabled mode");
				sa.assertTrue(false, "Reset password button is not displaying in disabled mode");
			}				
			}else{
				appLog.equals("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}	
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();		
		}
	
	@Test
	public void M18tc025_BuildTheFundraisingWorkspaceForFund2AndInviteContactsFromContactAccess(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		String[] step1of3 = {ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund1", excelLabel.Fund_Size), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund2", excelLabel.Fund_VintageYear),
				ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund2", excelLabel.Fund_Contact), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund2", excelLabel.Fund_Phone),
				ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund2", excelLabel.Fund_Email), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M18Fund2", excelLabel.Fund_Description) };
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M18FundName2)) {
				if (fp.buildWorkspace(step1of3, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null,
						M18Institution2 , Workspace.FundraisingWorkspace, 60)) {
					appLog.info("Fundraising Workspace is created successfully");
				} else {
					appLog.info("Fundraising workspace is not created successfully");
					sa.assertTrue(false, " Fundraising workspace is not craeted successfully");
				}				
		switchToDefaultContent(driver);
		if (fp.inviteContact(environment, mode, M18Institution2, M18Contact1EmailId, null, FolderType.Standard,
				"Upload", "Yes", "No", null, Workspace.FundraisingWorkspace, M18Contact1EmailId)) {
			appLog.info("Successfully provided access to contact '" + M18Contact1FirstName + " "
					+ M18Contact1LastName + "'.");
		} else {
			appLog.error("Not able to provide access to contact '" + M18Contact1FirstName + " "
					+ M18Contact1LastName + "'.");
			sa.assertTrue(false, "Not able to provide access to contact '" + M18Contact1FirstName + " "
					+ M18Contact1LastName + "'.");
		}
		if (fp.inviteContact(environment, mode, M18Institution2, M18Contact2EmailId, null, FolderType.Standard,
				"Upload", "Yes", "No", null, Workspace.FundraisingWorkspace, M18Contact2EmailId)) {
			appLog.info("Successfully provided access to contact '" + M18Contact2FirstName + " "
					+ M18Contact2LastName + "'.");
		} else {
			appLog.error("Not able to provide access to contact '" + M18Contact2FirstName + " "
					+ M18Contact2LastName + "'.");
			sa.assertTrue(false, "Not able to provide access to contact '" + M18Contact2FirstName + " "
					+ M18Contact2LastName + "'.");
		}
			} else {
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab so we cannot create workspace");
			sa.assertTrue(false, "Not able to click on Funds tab so we cannot create workspace");
		}
		switchToDefaultContent(driver);
		if(bp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact1FirstName+"NUP", M18Contact1LastName, null)){
				switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+" Section view");
			if(cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace, 60)!=null){
				appLog.info("Reset password button is displaying in enabled mode");
			}else{
				appLog.error("Reset password button is not displaying in enabled mode");
				sa.assertTrue(false, "Reset password button is not displaying in enabled mode");
			}	
			if(cp.getResetPasswordInactiveButton(Workspace.InvestorWorkspace, 10)==null || cp.getResetPasswordActiveButton(Workspace.InvestorWorkspace, 10)==null){
				appLog.info("Reset password button is not displaying in Investor Workspace");
			}else{
				appLog.error("Reset password button is displaying in Investor Workspace");
				sa.assertTrue(false, "Reset password button is displaying in Investor Workspace");
			}		
			}else{
				appLog.equals("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}		
		switchToDefaultContent(driver);
		if(bp.clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(M18Contact2FirstName, M18Contact2LastName, null)){
				switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+" Section view");
			if(cp.getResetPasswordInactiveButton(Workspace.FundraisingWorkspace, 60)!=null){
				appLog.info("Reset password button is displaying in disabled mode");
			}else{
				appLog.error("Reset password button is not displaying in disabled mode");
				sa.assertTrue(false, "Reset password button is not displaying in disabled mode");
			}		
			if(cp.getResetPasswordInactiveButton(Workspace.InvestorWorkspace, 10)==null && cp.getResetPasswordActiveButton(Workspace.InvestorWorkspace, 10)==null){
				appLog.info("Reset password button is not displaying in Investor Workspace");
			}else{
				appLog.error("Reset password button is displaying in Investor Workspace");
				sa.assertTrue(false, "Reset password button is displaying in Investor Workspace");
			}		
			}else{
				appLog.equals("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}	
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();			
	}
	
	@Test
	public void M18tc026_CheckFunctionalityOfClickHereLinkWhenUnregisteredInvestorContactChangePassword(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer afp=new AllFirmsPageBusinesslayer(driver);
		String windowID;
		driver.get(InvestorURL);
		if(click(driver, lp.getResetPasswordLink(60), "Reset Password link", action.SCROLLANDBOOLEAN)){
			if (sendKeys(driver, lp.getResetPasswordUserNameTextBox(60), M18Contact2EmailId,"Reset Username Text Box", action.BOOLEAN)) {
				if (click(driver, lp.getResetPasswordSubmitButton(60), "Forgot Password Submit Button",action.BOOLEAN)) {
					ThreadSleep(2000);
					WebElement ele = lp.getResetPasswordSubmitButtonErrorMessage(30);
					String msg;
					if (ele != null) {
						msg = ele.getText().trim();
						if (msg.contains(LoginErrorPage.forgotPasswordSubmitButtonErrorMessageWithInvitedButNotRegisteredContactEmailValue)) {
							appLog.info("Error Message  Matched with Invited But Not Registered: " + msg);
						} else {
							appLog.error("Error Message Not Matched with Invited But Not Registered: " + "Expected"
									+ LoginErrorPage.forgotPasswordSubmitButtonErrorMessageWithInvitedButNotRegisteredContactEmailValue
									+ "\tActual" + msg);
							sa.assertTrue(false,
									"Error Message Not Matched with Invited But Not Registered: " + "Expected"
											+ LoginErrorPage.forgotPasswordSubmitButtonErrorMessageWithInvitedButNotRegisteredContactEmailValue
											+ "\tActual" + msg);
						}
						windowID= driver.getWindowHandle();
						if (click(driver, lp.getResetPasswordSubmitButtonClickLink(60),
								"Reset Password Submit Click Here Link", action.BOOLEAN)) {
							appLog.info(" Able to Click Here Link");
							CommonLib.waitForPageLoad(driver);
							if(windowID.equals(driver.getWindowHandle())){
								appLog.info("User Registration page is open in same window. ");
								if(lp.getResetPasswordregistrationpagePasswordErrorMsg(60).getText().contains(LoginErrorPage.resetPasswordRegistrationPagePasswordErrorMessage)){
									appLog.info("Password error message is verified"); 
								}else{
									appLog.error("Password error message is not verified");
									sa.assertTrue(false, "Password error message is not verified");
								}
								System.out.println(lp.getResetPasswordRegistrationPageClickHereMessage(60).getText());
								if(lp.getResetPasswordRegistrationPageClickHereMessage(60).getText().trim().contains(LoginErrorPage.resetPasswordRegistrationPageClickHereMessage)){
									appLog.info("Click here message is verified"); 
								}else{
									appLog.error("Click here message is not verified");
									sa.assertTrue(false, "Click here message is not verified");
								}		
							if(lp.getTargetEmailId(60)!=null){
								appLog.info("Email id textbox is displaying");
							}else{
								appLog.error("Email id textbox is not displaying");
								sa.assertTrue(false, "Email ID textbox is not displaying");
							}
						if(lp.getTargetFirstName(60).getAttribute("value").equalsIgnoreCase(M18Contact2FirstName)){
								appLog.info("First name is prefilled with "+M18Contact2FirstName);			
							}else{
								appLog.error("First name is not prefilled");
								sa.assertTrue(false, "first name is not prefilled");			
							}
						if(lp.getTargetlastName(60).getAttribute("value").equalsIgnoreCase(M18Contact2LastName)){
							appLog.info("Last name is prefilled with "+M18Contact2LastName);			
						}else{
							appLog.error("Last name is not prefilled");
							sa.assertTrue(false, "Last name is not prefilled");			
						}
						if(lp.getTargetNickName(60).getAttribute("value").equalsIgnoreCase(M18Contact2FirstName+M18Contact2LastName)){
							appLog.info("Nick name is prefilled with "+M18Contact2FirstName+M18Contact2LastName);			
						}else{
							appLog.error("Nick name is not prefilled");
							sa.assertTrue(false, "Nick name is not prefilled");			
						}
						if(lp.getTargetEmailId(60).getAttribute("value").equalsIgnoreCase(M18Contact2EmailId)){
							appLog.info("emailID is prefilled with "+M18Contact2EmailId);			
						}else{
							appLog.error("emailID is not prefilled");
							sa.assertTrue(false, "emailID is not prefilled");			
						}
						if(lp.getTargetFirmName(60).getAttribute("value").equalsIgnoreCase(M18Institution1)){
							appLog.info("Firm name is prefilled with "+M18Institution1);			
						}else{
							appLog.error("Firm name is not prefilled");
							sa.assertTrue(false, "Firm name is not prefilled");			
						}
						String text=getValueFromElementUsingJavaScript(driver, lp.getTargetpassword(60), "Reset password password text box");
			    		  if(text.isEmpty()){
			    			appLog.info("password textbox is empty");
			    		}else{
			    			appLog.error("password textbox is not empty");
			    			sa.assertTrue(false, "password textbox is not empty");
			    		}				
			    		  text=getValueFromElementUsingJavaScript(driver, lp.getTargetConfirmPassword(60), "Reset password confirm password text box");
			    		  if(text.isEmpty()){
			    			appLog.info("confirm password textbox is empty");
			    		}else{
			    			appLog.error("confirm password textbox is not empty");
			    			sa.assertTrue(false, "confirm password textbox is not empty");
			    		}
			    	if(sendKeys(driver,  lp.getTargetpassword(60), adminPassword, "Password text box", action.SCROLLANDBOOLEAN)){	  
			    		if(sendKeys(driver,  lp.getTargetConfirmPassword(60), adminPassword, "Confirm Password text box", action.SCROLLANDBOOLEAN)){	  
			    		  if(click(driver, lp.getTargetSignUpBtn(60), "Target Sign Up Button", action.SCROLLANDBOOLEAN)){
			    				appLog.info("Clicked on signupbutton successfully");
			    			if(afp.getFirmNameDropdown(60)!=null){
			    			if(getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Select deal dropdown", "text").equalsIgnoreCase(Org1UpdatedFirmName)){
			    				appLog.info("Investor home page is displaying");
			    			}else{
			    			appLog.error("Investor home page is not displaying");
			    			sa.assertTrue(false, "Investor home page is not displaying");
			    		}	
			    			}else{
			    				appLog.error("Firm Dropdown is not displaying on home page");
			    				sa.assertTrue(false, "Firm Dropdown is not displaying on home page");			    				
			    			}
			    	}else{
			    		appLog.error("Not able to click on signup button");
			    		sa.assertTrue(false, "Not able to click on signup button");
			 			}
			    		}else{
			    			appLog.error("Not able to enter confirm password in text box");
				    		sa.assertTrue(false, "Not able to enter confirm password in text box");
			    		}
			    	}else{
			    		appLog.error("Not able to enter password in text box");
			    		sa.assertTrue(false, "Not able to enter password in text box");
			    	}
					} else {
								appLog.error("Some different url is opening. " + getURL(driver, 30));
								sa.assertTrue(false, "Some different url is opening. " + getURL(driver, 30));
							}
						} else {
							appLog.error("Not Able to Click Here Link");
							sa.assertTrue(false, "Not Able to Click Here Link");
						}		
					} else {
						appLog.error("Submit Button element is null");
						sa.assertTrue(false, "Submit Button element is null");
					}			
				}else{
					appLog.error("Not able to click on submit button");
					sa.assertTrue(false, "Not able to click on submit button");
				}
			}else{
				appLog.error("Not able to enter value in username text box");
				sa.assertTrue(false, "Not able to enter value in username text box");
			}			
		}else{
			appLog.error("Not able to click on reset password link");
			sa.assertTrue(false, "Not able to click on reset password link");
		}
		lp.investorLogout();
		sa.assertAll();	
	}
	
	@Test
	public void M18tc027_1_SendResetPasswordEmailFromContactPage(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID	, adminPassword);
		if(cp.clickOnTab(TabName.ContactTab)){
				if(cp.clickOnCreatedContact(M18Contact2FirstName, M18Contact2LastName, null)){
					switchToFrame(driver, 30,bp. getFrame( PageName.ContactsPage, 30));
					scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+" Section view");
					if(cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace,60)!=null){
						if(click(driver, cp.getResetPasswordActiveButton(Workspace.FundraisingWorkspace,60), "Reset Pasword button", action.SCROLLANDBOOLEAN)){		
							if(click(driver, cp.getResetPasswordPopupSendEMailButton(60), "Send Email", action.SCROLLANDBOOLEAN)){
								if(cp.getResetPasswordPopupHeaderAfterSendEMail(60)!=null){
									if(cp.getResetPasswordPopupHeaderAfterSendEMail(60).getText().trim().contains("Confirmation")){
										appLog.info("Header text is verified");					
									}else{
										appLog.error("Header text is not verified");
										sa.assertTrue(false, "Header Text is not verified");
									}
									if(cp.getResetPasswordPopupCrossIconAfterSendEMail(60)!=null){
										appLog.info("Cross icon is available");
									}else{
										appLog.error("Cross icon is not available");
										sa.assertTrue(false, "Cross icon is not available");
									}
									if(cp.getResetPasswordOKButton(60)!=null){
										appLog.info("OK Button is available");
									}else{
										appLog.error("OK Button is not available");
										sa.assertTrue(false, "OK Button is not available");
									}
									if(cp.getResetPasswordMessageAfterSendEMail(60).getText().trim().contains("Reset password email sent to "+M18Contact2EmailId+" successfully.")){
										appLog.info("Reset password message is verified");				
									}else{
										appLog.error("Reset password message is not verified");
										sa.assertTrue(false, "Reset password message is not verified");
									}
									if(click(driver, cp.getResetPasswordOKButton(60), "OK Button", action.SCROLLANDBOOLEAN)){
										if(!click(driver, cp.getResetPasswordOKButton(5), "ok", action.SCROLLANDBOOLEAN)){
											appLog.info("Confirmation popup get closed successfully");
										}else{
											appLog.error("Confirmation popup not get closed successfully");
											sa.assertTrue(false, "Confirmation popup not get closed successfully");
										}					
									}else{
										appLog.error("Not able to click on OK Button");
										sa.assertTrue(false, "Not able to click on OK button");		
									}
									SoftAssert saa=bp.verifyResetPasswordMailContent(M18Contact2FirstName, M18Contact2EmailId, "Contact page",Org1UpdatedFirmName , CRMUser1EmailID,null);
									sa.combineAssertions(saa);	
								}else{
									appLog.error("Confirmation popup is not displaying");
									sa.assertTrue(false, "Confirmation popup is not displaying");
								}
							}else{
								appLog.error("Not able to click on send email button");
								sa.assertTrue(false, "Not able to click on send email button");
							}
						}else{
							appLog.error("Not able to click on reset password button");
							sa.assertTrue(false, "Not able to click on reset password button");				
						}
					}else{
						appLog.error("Reset Password button is not displaying");
						sa.assertTrue(false, "Reset Password button is not displaying");						
					}
				}else{
					appLog.equals("Not able to click on created contact");
					sa.assertTrue(false, "Not able to click on created contact");
				}
			}else{
				appLog.error("Not able to click on contact tab");
				sa.assertTrue(false, "Not able to click on contact tab");
			}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();				
	}
	
	@Test
	public void M18tc027_2_UpdatePassword(){
		BasePageBusinessLayer  bp=new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer afp=new AllFirmsPageBusinesslayer(driver);
		driver.get(ExcelUtils.readData("Contacts", excelLabel.Variable_Name	, "M18Contact2", excelLabel.Click_HereLink));
		String newPassword=ExcelUtils.readData("Contacts", excelLabel.Variable_Name	, "M18Contact2", excelLabel.Updated_Password);
		if(sendKeys(driver, bp.getResetPasswordNewPasswordTextBox(60), newPassword, "New password text box", action.SCROLLANDBOOLEAN)){
			if(sendKeys(driver, bp.getResetPasswordConfirmPasswordTextBox(60), newPassword, "Confirm password text box", action.SCROLLANDBOOLEAN)){
			if(click(driver, bp.getResetPasswordButton(60), "Reset Password button", action.SCROLLANDBOOLEAN)){
				if(bp.getResetPasswordPasswordChangeMessage(60)!=null){
					if(bp.getResetPasswordPasswordChangeMessage(60).getText().equalsIgnoreCase(LoginErrorPage.resetPasswordPasswordChangeMessage)){
						appLog.info("Password change message is verified");
						if(sendKeys(driver, lp.getInvestorPasswordTextbox(60),newPassword, "Target passowrd text box", action.SCROLLANDBOOLEAN)){
							if(click(driver, lp.getInvestorLoginButton(60), "Target login button", action.SCROLLANDBOOLEAN)){
								ThreadSleep(5000);
								if(afp.getFirmNameDropdown(60)!=null){
									if(getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Select deal dropdown", "text").equalsIgnoreCase(Org1UpdatedFirmName)){
										appLog.info("Investor home page is displaying");
									}else{
										appLog.error("Investor home page is not displaying");
										sa.assertTrue(false, "Investor home page is not displaying");
									}				
								}else{
									appLog.error("Select firm dropdown is not displaying");
									sa.assertTrue(false, "Select firm dropdown is not displaying");
								}											
							}else{
								appLog.error("Not able to click on login button");
								sa.assertTrue(false, "Not able to click on login button");
							}			
						}else{
							appLog.error("Not able to send value in password tetx box");
							sa.assertTrue(false, "Not able to send value in password tetx box");
						}						
					}else{
						appLog.error("Password change message is not verified");
						sa.assertTrue(false, "Password change message is not verified");
					}
				}else{
					appLog.error("Password change message is not displaying");
					sa.assertTrue(false, "Password change message is not displaying");
				}
			}else{
				appLog.error("Not able to click on reset password button");
				sa.assertTrue(false, "Not able to click on reset password button");
			}			
			}else{
				appLog.error("Not able to enter value in Confirm password text box");
				sa.assertTrue(false, "Not able to enter value in Confirm password text box");
			}
		}else{
			appLog.error("Not able to enter value in new password text box");
			sa.assertTrue(false, "Not able to enter value in new password text box");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Test
	public void M18tc028_postCondition(){
		LoginPageBusinessLayer	 lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert saa1 = new SoftAssert();
		lp.CRMLogin(superAdminUserName, adminPassword);
		saa1=bp.postCondition();
		sa.combineAssertions(saa1);
		lp.CRMlogout();
		sa.assertAll();		
		}
}

