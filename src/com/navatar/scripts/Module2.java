/**
 * 
 */
package com.navatar.scripts;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonVariables;
import com.navatar.generic.EmailLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.SortOrder;
import com.navatar.generic.CommonLib.TabName;
import com.navatar.generic.CommonLib.accessType;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.customTabActionType;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.pageObjects.AllFirmsErrorMessage;
import com.navatar.pageObjects.AllFirmsPageBusinesslayer;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.BasePageErrorMessage;
import com.navatar.pageObjects.CommitmentPageBusinessLayer;
import com.navatar.pageObjects.CommitmentPageErrorMessage;
import com.navatar.pageObjects.ContactPageBusinessLayer;
import com.navatar.pageObjects.ContactPageErrorMessage;
import com.navatar.pageObjects.FundRaisingPageBusinessLayer;
import com.navatar.pageObjects.FundsPageBusinessLayer;
import com.navatar.pageObjects.FundsPageErrorMessage;
import com.navatar.pageObjects.HomePageBusineesLayer;
import com.navatar.pageObjects.HomePageErrorMessage;
import com.navatar.pageObjects.InstitutionPageBusinessLayer;
import com.navatar.pageObjects.InstitutionPageErrorMessage;
import com.navatar.pageObjects.InvestorFirmPage;
import com.navatar.pageObjects.InvestorFirmPageBusinesslayer;
import com.navatar.pageObjects.InvestorProfile;
import com.navatar.pageObjects.InvestorProfileBusinessLayer;
import com.navatar.pageObjects.InvestorProfileErrorsPage;
import com.navatar.pageObjects.LoginErrorPage;
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.NIMPageBusinessLayer;
import com.navatar.pageObjects.NIMPageErrorMessage;
import com.navatar.pageObjects.NavatarInvestorAddOnsErrorMessage;
import com.navatar.pageObjects.NavatarInvestorAddonsPageBusinessLayer;
import com.navatar.pageObjects.PartnershipPageBusinessLayer;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.CommonVariables.*;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * @author Akul Bhutani
 *
 */
public class Module2 extends BaseLib{
	@Test
	public void M2tc001_UserNIMregistration() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		
//		if(lp.preCondition(superAdminUserName, CRMUser1FirstName+" "+CRMUser1LastName, CRMUser1EmailID, EnableDisable.Disable, EnableDisable.Disable, accessType.InternalUserAccess)){
//			appLog.info("Provided internal user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
//		} else {
//			appLog.error("Not able to provide internal user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
//			saa.assertTrue(false,"Not able to provide internal user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
//		}
//		driver.close();
//		config(ExcelUtils.readDataFromPropertyFile("browser"));
//		lp = new LoginPageBusinessLayer(driver);
//		bp = new BasePageBusinessLayer(driver);
//		np = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName,adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
		 
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.Profiles)){
				if (np.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
					String firm_name = np.getFirmName(60).getText().trim();
					ExcelUtils.writeData(firm_name, "Users", excelLabel.Variable_Name, "AdminUser", excelLabel.Firm_Name);
					appLog.info("firm name "+firm_name+" is successfully written to excel");
				}
				else {
					sa.assertTrue(false, "my firm profile tab is not clickable");
				}
			}
			else {
				sa.assertTrue(false, "profile side menu is not cllickable on nim page");
			}

		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
	
		sa.assertAll();	
	}

	@Test
	public void M2tc002_1_Module2_PreconditionData() {
	
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		
		
		String M2Contact1EmailID,M2Contact2EmailID;
		
		
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			ip.createInstitution(M2Institution1);
		}
		else {
			appLog.error("Institutions Tab is not clickable");
			sa.assertTrue(false, "Institution tab is not clickable");
		}
		
		M2Contact1EmailID = cp.generateRandomEmailId();
		M2Contact2EmailID = cp.generateRandomEmailId();
		
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.createContact(M2Contact1First, M2Contact1Last, M2Contact1Inst, M2Contact1EmailID)) {
				ExcelUtils.writeData(M2Contact1EmailID, "Contacts", excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
				appLog.info("Contact "+M2Contact1Last+" was successfully created");
			}
			else {
				sa.assertTrue(false, "Contact "+M2Contact1Last+" could not be created");
			}
		}
		else {
			sa.assertTrue(false,"Contacts tab is not clickable");
		}
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.createContact(M2Contact2First, M2Contact2Last, M2Contact2Inst, M2Contact2EmailID)) {
				ExcelUtils.writeData(M2Contact2EmailID, "Contacts", excelLabel.Variable_Name, "M2Contact2", excelLabel.Contact_EmailId);
				appLog.info("Contact "+M2Contact2Last +" was successfully created");
			}
			else {
				sa.assertTrue(false, "Contact "+M2Contact2Last+ " could not be created");
			}
		}
		else {
			sa.assertTrue(false, "Contacts tab is not clickable");
		}
		
		if (bp.clickOnTab(TabName.FundsTab)){
		if (fp.createFund(M2Fund1Name,M2Fund1Type, M2Fund1InvCategory)) {
			appLog.info("New fund "+M2Fund1Name+ " was successfully created");
		}
		else {
			sa.assertTrue(false, "New fund "+M2Fund1Name+" could not be created");
		}
		}
		else {
			sa.assertTrue(false, "Funds tab is not clickable");
		}
		
		if (bp.clickOnTab(TabName.FundraisingsTab)) {
		if (frp.createFundRaising(M2Fundraising1, M2Fundraising1Fund, M2Fundraising1Inst)) {
			appLog.info("Fundraising "+ M2Fundraising1+ " was successfully created");
		}
		else {
			sa.assertTrue(false, "Fundraising "+M2Fundraising1+ " could not be created");
		}
		}
		else {
			sa.assertTrue(false, "Fundraising tab is not clickable");
		}
		
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
		if (ip.createLimitedPartner(M2LP1, M2LP1Inst)) {
			appLog.info(M2LP1+ " limited partner was successfully created");
		}
		else {
			sa.assertTrue(false, M2LP1+ " LP could not be created");
		}
		}
		else {
			sa.assertTrue(false, "Institution tab is not clickable");
		}
		
		
		
		WebElement ele=FindElement(driver, "//a[contains(@title,'Partnerships')]", "Partnerships tab",
				action.SCROLLANDBOOLEAN, 10);
		if(ele==null){
			bp.addRemoveCustomTab("Partnerships", customTabActionType.Add);
			
			if (bp.clickOnTab(TabName.PartnershipsTab)) {
				if (pp.createPartnership(M2Partnership1Name, M2Partnership1Fund)) {
					appLog.info("Partnership is created successfully");
				} else {
					appLog.info("Partnership is not created successfully");
					sa.assertTrue(false, "Partnership is not created successfully");
				}
			} else {
				appLog.info("Not able to click on Partnership tab so we cannot create Partnership");
				sa.assertTrue(false, "Not able to click on Partnership tab so we cannot create Partnership");
			}
		}else{
		if (bp.clickOnTab(TabName.PartnershipsTab)) {
			if (pp.createPartnership(M2Partnership1Name, M2Partnership1Fund)) {
				appLog.info("Partnership is created successfully");
			} else {
				appLog.info("Partnership is not created successfully");
				sa.assertTrue(false, "Partnership is not created successfully");
			}
		} else {
			appLog.info("Not able to click on Partnership tab so we cannot create Partnership");
			sa.assertTrue(false, "Not able to click on Partnership tab so we cannot create Partnership");
		}
		}
		
		ele=FindElement(driver, "//a[contains(@title,'Commitments')]", "Commitments tab",
				action.SCROLLANDBOOLEAN, 10);
		if(ele==null){
			bp.addRemoveCustomTab("Commitments", customTabActionType.Add);
			ThreadSleep(1000);
			 if (bp.clickOnTab(TabName.CommitmentsTab)) {
				if (cmp.createCommitment(M2Commitment1LP, M2Commitment1Partnership, "M1Commitment1", null)) {
					appLog.info("Commitment is created successfully");
				} else {
					appLog.error("Commitment is not created successfully");
					sa.assertTrue(false, "Commitment is not created successfully");
				}
			} else {
				appLog.error("Not able to click on Commitment tab so we cannot create Commitment");
				sa.assertTrue(false, "Not able to click on Commitment tab so we cannot create Commitment");
			}
	
		}else{		
		if (bp.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.createCommitment(M2Commitment1LP, M2Commitment1Partnership, "M1Commitment1", null)) {
				appLog.info("Commitment is created successfully");
			} else {
				appLog.error("Commitment is not created successfully");
				sa.assertTrue(false, "Commitment is not created successfully");
			}
		} 
		}
		
		lp.CRMlogout();
		sa.assertAll();
	}
	@Test
	public void M2tc002_2_createFolderTemplateOrg1() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.createFolderTemplate("FolderTemp", folderTemplateName, "", 60)) {
				appLog.info("folder template"+folderTemplateName+" is successfully created");
			}
			else {
				appLog.error("folder template could not be created");
				sa.assertTrue(false, "folder template could not be created");
			}
			switchToDefaultContent(driver);
		}
		else {
			appLog.error("nim tab is not clickable so cannot create folder template");
			sa.assertTrue(false, "nim tab is not clickable so cannot create folder template");
		}
		sa.assertAll();
	}
	@Test
	public void M2tc003_BuildFundraisingAndInvestorWorkspace() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M2Fund1", excelLabel.Fund_Size);
		String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M2Fund1", excelLabel.Fund_VintageYear);
		String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M2Fund1", excelLabel.Fund_Contact);
		String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M2Fund1", excelLabel.Fund_Phone);
		String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M2Fund1", excelLabel.Fund_Email);
		String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M2Fund1", excelLabel.Fund_Description);
		
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		String[] step1of3= {Size,vintageyear,contact,phone,email,description};
		
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M2Fund1Name)) {
				if (fp.buildWorkspace(step1of3, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null,M2Institution1, Workspace.FundraisingWorkspace, 60)) {
					appLog.info("fundraising workspace is successfully built");
				}
				else {
					appLog.error( "could not build fundraising workspace");
					sa.assertTrue(false, "could not build fundraising workspace");
				}
				if (fp.buildWorkspace(step1of3, WorkSpaceAction.IMPORTFOLDERTEMPLATE,folderTemplateName, null, M2Institution1+"/"+M2LP1, Workspace.InvestorWorkspace, 60)) {
					appLog.info("investor workspace successfully built");
				}
				else {
					appLog.error("could not build investor workspace");
					sa.assertTrue(false, "could not build investor workspace");
				}
			}
			else {
				appLog.error( "could not find fund "+M2Fund1Name+ " hence cannot build workspace");
				sa.assertTrue(false, "could not find fund "+M2Fund1Name+ " hence cannot build workspace");
			}

		}
		else {
			appLog.error( "funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	@Test
	public void M2tc004_GiveContactAccessAndInvite() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String M2Contact2EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact2", excelLabel.Contact_EmailId);
		
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M2Fund1Name)) {
				//giving access in fundraising workspace
				
				if (fp.inviteContact(environment, mode, M2Institution1, M2Contact1EmailID, stdPath, FolderType.Standard, "Upload", "Yes", "Yes", "All Folders", Workspace.FundraisingWorkspace, M2Contact1EmailID)) {
					appLog.info("contact has been given access successfully and invite has been sent to mail");
				}
				else {
					appLog.error( "Contact could not be given access");
					sa.assertTrue(false, "Contact could not be given access");
				}
			
				//giving access in investor workspace
				if (fp.inviteContact(environment, mode, M2Institution1+"/"+M2LP1, M2Contact2EmailID, stdPath, FolderType.Standard, "Upload", "Yes", "Yes", "All Folders", Workspace.InvestorWorkspace, M2Contact2EmailID)) {
					appLog.info("contact has been given access successfully and invite has been sent to mail");
				}
				else {
					appLog.error( "Contact could not be given access");
					sa.assertTrue(false, "Contact could not be given access");
				}
			}
			else {
				appLog.error( "fund name provided could not be found");
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
	
	@Test
	public void M2tc005_verifyRegistrationPage() {
		String M2Contact1EmailID;
		String regLink=null;
		EmailLib el = new EmailLib();
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		System.out.println("m2contact email "+M2Contact1EmailID);
		try {
			regLink = new EmailLib().getInvestorRegLink("InvitationMail",gmailUserName, gmailPassword, CRMUser1EmailID, M2Contact1EmailID);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			appLog.error("registration link is not found");
		}

		SoftAssert sa = new SoftAssert();
		if (regLink!=null) {
			driver.get(regLink);
			
		}
		else {
			driver.get(InvestorRegistrationURL);
			appLog.error( "registration link was not found, so failing case, and opening URL from properties file");
			sa.assertTrue(false, "registration link was not found, so failing case, and opening URL from properties file");
			}
		
			if (bp.getnavatarImageInvRegistration(30)!=null) {
				appLog.info("Navatar logo is correctly displayed on investor registration page");
			}
			else {
				appLog.error( "Navatar logo not visible on investor registration page");
				sa.assertTrue(false, "Navatar logo not visible on investor registration page");
			}
			if (bp.getFirstNameLabel(60).getText().trim().equals("First Name:")) {
				appLog.info("First name text is correctly displayed on investor registration page");
			}
			else {
				appLog.error( "First name label is not visible on investor registration page");
				sa.assertTrue(false, "First name label is not visible on investor registration page");
			}

			if (bp.getTargetFirstName(30)!=null) {
				appLog.info("first name textbox present on investor registration page");

			}
			else {
				appLog.error("First name textbox is not visible investor registration page");
				sa.assertTrue(false, "First name textbox is not visible investor registration page");
			}


			if (bp.getLastNameLabel(60).getText().trim().equals("Last Name:")) {
				appLog.info("Last name text is successfully displayed on investor registration page");
			}
			else {
				appLog.error( "Last name label is not visible on investor registration page");
				sa.assertTrue(false, "Last name label is not visible on investor registration page");
			}

			if (bp.getTargetlastName(30)!=null) {
				appLog.info("last name textbox present on investor registration page");

			}
			else {
				appLog.error( "Last name textbox is not visible on investor registration page");
				sa.assertTrue(false, "Last name textbox is not visible on investor registration page");
			}

			if (bp.getNickNameLabel(60).getText().trim().equals("Nick Name:")) {
				appLog.info("Nick name text is correctly displayed on investor registration page");
			}
			else {
				appLog.error( "Nick name text label is not visible on investor registration page");
				sa.assertTrue(false, "Nick name text label is not visible on investor registration page");
			}

			if (bp.getTargetNickName(30)!=null) {
				appLog.info("nick name textbox present on investor registration page");

			}
			else {
				appLog.error( "Nick name text box is not visible on investor registration page");
				sa.assertTrue(false, "Nick name text box is not visible on investor registration page");
			}

			if (bp.getEmailLabel(60).getText().trim().equals("E-mail (Username):")) {
				appLog.info("email id text is correctly dispalyed on investor registration page");
			}
			else {
				appLog.error(  "EmailID text is not visible on investor registration page");
				sa.assertTrue(false, "EmailID text is not visible on investor registration page");
			}
			if (bp.getTargetEmailId(30)!=null) {
				appLog.info("email id textbox present on investor registration page");

			}
			else {
				appLog.error( "Email id textbox on investor registration page is not visible");
				sa.assertTrue(false, "Email id textbox on investor registration page is not visible");
			}


			if (bp.getFirmNameLabel(60).getText().trim().equals("Firm Name:")) {
				appLog.info("Firm name text is correctly displayed on investor registration page");
			}
			else {
				appLog.error( "Firm name text label is not visible on investor registration page");
				sa.assertTrue(false, "Firm name text label is not visible on investor registration page");
			}

			if (bp.getTargetFirmName(30)!=null) {
				appLog.info("firm name textbox present on investor registration page");

			}
			else {
				appLog.error( "Firm name text box on investor registration page is not visible");
				sa.assertTrue(false, "Firm name text box on investor registration page is not visible");
			}


			if (bp.getPasswordLabel(60).getText().trim().equals("Password:")) {
				appLog.info("Password text label is correctly displayed on investor registration page");
			}
			else {
				appLog.error(  "Password text label is not visible on investor registration page");
				sa.assertTrue(false, "Password text label is not visible on investor registration page");
			}



			if (bp.getTargetpassword(30)!=null) {
				appLog.info("password textbox present on investor registration page");

			}
			else {
				appLog.error( "Nick name text label is not visible on investor registration page");
				sa.assertTrue(false, "Password text box in investor registration page is not visible");
			}


			if (bp.getConfirmPasswordLabel(60).getText().trim().equals("Confirm Password:")) {
				appLog.info("Confirm password text is correctly displayed on investor registration page");
			}
			else {
				appLog.error( "Nick name text label is not visible on investor registration page");
				sa.assertTrue(false, "Confirm Password text label is not visible on investor registration page");
			}



			if (bp.getTargetConfirmPassword(30)!=null) {
				appLog.info("confirm password textbox present on investor registration page");

			}
			else {
				appLog.error( "Nick name text label is not visible on investor registration page");
				sa.assertTrue(false, "Confirm password textbox on investor registration page is not visible");
			}

			if (bp.getEightCharactersInfoLabel(60).getText().trim().equals(BasePageErrorMessage.eightCharactersMessage)) {
				appLog.info(BasePageErrorMessage.eightCharactersMessage+" is correctly displayed on investor registration page");
			}
			else {
				appLog.error( "Nick name text label is not visible on investor registration page");
				sa.assertTrue(false, BasePageErrorMessage.eightCharactersMessage+ " could not be verified on registration page");
			}

			if (bp.getTargetSignUpBtn(30)!=null) {
				appLog.info("sign up button present on investor registration page");

			}
			else {
				appLog.error( "Nick name text label is not visible on investor registration page");
				sa.assertTrue(false, "Sign up button is not clickable on investor registration page");
			}
			/*if (bp.getTargetcancelBtn(30)!=null) {
				appLog.info("cancel button present on investor registration page");

			}
			else {
				sa.assertTrue(false, "Cancel button is not clickable on investor registration page");
			}*/
		
		sa.assertAll();
	}
	
	@Test
	public void M2tc006_CheckInvestorRegistrationLinks() {
		
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		driver.get(InvestorRegistrationURL);
		SoftAssert sa = new SoftAssert();
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		if (click(driver, bp.getnavatarImageInvRegistration(60), "Navatar logo on registration page", action.SCROLLANDBOOLEAN)) {
			if (getURL(driver, 60).contains(InvestorURL)) {
				appLog.info("Investor login page successfully opened on clicking on navatar logo");
				driver.get(InvestorRegistrationURL);
				
			}
			else {
				sa.assertTrue(false, "investor login page not opened after clicking on navatar logo on registration page");
			}
		}
		if (click(driver, bp.getTargetSignUpBtn(60), "Sign up button on investor registration page", action.SCROLLANDBOOLEAN)) {
			if (af.getErrorFirstName(30).getText().trim().equals(AllFirmsErrorMessage.pleaseEnterRequiredFields)) {
				appLog.info("Please enter required fields text successfully found below first name text box");
				
			}
			else {
				sa.assertTrue(false, "Please enter the required fields text was not present below first name text box");
			}
		}
		if (sendKeys(driver, bp.getTargetFirstName(60), M2Contact1First, "Investor side registration first name", action.BOOLEAN)) {
			if (click(driver, bp.getTargetSignUpBtn(60),"investor registration page sign up button", action.BOOLEAN)) {
				if (af.getErrorLastName(60).getText().trim().equals(AllFirmsErrorMessage.pleaseEnterRequiredFields)) {
					appLog.info("Found error message below last name textbox on investor registration page");
					sa.assertTrue(true);
				}
				else {
					sa.assertTrue(false,"Error message below last name textbox was not present");
				}
			}
			if (sendKeys(driver, bp.getTargetlastName(60), M2Contact1Last, "Investor side registration last name", action.BOOLEAN)) {
				if (click(driver, bp.getTargetSignUpBtn(60), "investor registration page sign up button", action.BOOLEAN)) {
					if (af.getErrorNickname(60).getText().trim().equals(AllFirmsErrorMessage.pleaseEnterRequiredFields)) {
						appLog.info("Found error message below nick name textbox on investor registration page");
						sa.assertTrue(true);
					}
					else {
						sa.assertTrue(false, "Error message below nick name textbox was not present");
					}
				}
				if (sendKeys(driver, bp.getTargetNickName(60), M2Contact1First+M2Contact1Last + bp.generateRandomNumber(), "Investor registration nick name", action.BOOLEAN)) {
					if (click(driver, bp.getTargetSignUpBtn(60), "investor registration sign up button", action.BOOLEAN)) {
						if (af.getErrorPleaseEnterRequiredFieldsEmailID(60).getText().trim().equals(AllFirmsErrorMessage.pleaseEnterRequiredFields)) {
							appLog.info("Found error message below email id textbox on investor registration page");
							sa.assertTrue(true);
						}
						else {
							sa.assertTrue(false, "Please enter the required fields error message below email id textbox was not present");
						}
					}
					//Entering invalid email id and checking error message
					if (sendKeys(driver, bp.getTargetEmailId(60), "abcgmail.com", "Investor registration email id textbox", action.BOOLEAN)) {
						if (click(driver, bp.getTargetSignUpBtn(60), "investor registration sign up button", action.BOOLEAN)) {
							if (af.getErrorInvalidEmailAddressbelowEmail(60).getText().trim().equals(AllFirmsErrorMessage.invalidEmail)) {
								appLog.info("Found invalid email address error below email id textbox on investor registration pag");
								sa.assertTrue(true);
							}
							else {
								sa.assertTrue(false, "Invalid email address below email id textbox was not present");
							}
						}
					}
					if (sendKeys(driver, bp.getTargetEmailId(60), M2Contact1EmailID, "Investor registration email id textbox", action.BOOLEAN)) {
						if (click(driver, bp.getTargetSignUpBtn(60), "investor registration sign up button", action.BOOLEAN)) {
							if (af.getErrorFirmName(60).getText().trim().equals(AllFirmsErrorMessage.pleaseEnterRequiredFields)) {
								appLog.info("Found error message below firm name textbox on investor registration page");
								sa.assertTrue(true);
							}
							else {
								sa.assertTrue(false, "Error message below firm name textbox was not present");
							}
						}
						
						if (sendKeys(driver, bp.getTargetFirmName(60), M2FirmName1, "Investor registration firm name text box", action.BOOLEAN)) {
							if (click(driver,bp.getTargetSignUpBtn(60) , "investor registration sign up button", action.BOOLEAN)) {
								if (af.getErrorPleaseEnterRequiredFieldsPassword(60).getText().trim().equals(AllFirmsErrorMessage.pleaseEnterRequiredFields)) {
									appLog.info("Found please enter the required fields error message below password textbox");
									sa.assertTrue(true);
								}
								else {
									sa.assertTrue(false,"Please enter required fields error message below password textbox was not found");
								}
							}
							if(sendKeys(driver, bp.getTargetpassword(60), "d", "Investor registration password text box", action.BOOLEAN)) {
								if (click(driver, bp.getTargetSignUpBtn(60), "investor registration sign up button", action.BOOLEAN)) {
									if (af.getErrorAtLeastEightCharsPassword(60).getText().trim().equals(AllFirmsErrorMessage.passwordMustHaveEightChars)) {
										appLog.info("Found password must have at least 8 characters error message below password textbox");
										sa.assertTrue(true);
									}
									else {
										sa.assertTrue(false, "Password must have at least 8 characters error message was not present below password textbox");
									}
								}
								else {
									sa.assertTrue(false,"Sign up button on registration page was not clickabled");
								}
							}
							if (sendKeys(driver, bp.getTargetpassword(60),adminPassword,"Investor registration password text box", action.BOOLEAN)) {
								if (click(driver, bp.getTargetSignUpBtn(60), "investor registration sign up button", action.BOOLEAN)) {
									if (af.getErrorRetypeConfirmPassword(60).getText().trim().equals(AllFirmsErrorMessage.pleaseRetypePassword)) {
										appLog.info("Please retype the password to confirm error message was present below confirm pssword text box");
										sa.assertTrue(true);
									}
									else {
										sa.assertTrue(false, "Please retype the password to confirm error message was present below confirm password text box");
									}
								}
							
							if (sendKeys(driver, bp.getTargetConfirmPassword(60), adminPassword+"1", "Investor registration confirm password text box", action.BOOLEAN)) {
								if (click(driver, bp.getTargetSignUpBtn(60),"investor registration sign up button", action.BOOLEAN)) {
									if (af.getErrorConfirmPasswordNotMatch(60).getText().trim().equals(AllFirmsErrorMessage.passwordsDoNotMatch)) {
										appLog.info("Passwords entered do not match error message found below confirm password text box");
										sa.assertTrue(true);
									}
									else {
										sa.assertTrue(false, "Password entered do not match error message was not present below confirm password text box");
									}
								}
							}
							
							if (sendKeys(driver, bp.getTargetEmailId(60), "abc@gmail.com", "Email id textbox on investor registration page", action.BOOLEAN)) {
								if (sendKeys(driver, bp.getTargetConfirmPassword(60), adminPassword, "Confirm password textbox on investor registration page", action.BOOLEAN)) {
									if(bp.clickUsingCssSelectorPath("a.GlobalButton[title='Sign up']", "sign up button")) {
//									if (click(driver, bp.getTargetSignUpBtn(60), "sign up button on investor registration page", action.BOOLEAN)) {
										ThreadSleep(5000);
										if (isAlertPresent(driver)) {
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											if (msg.contains("Access to Navatar Investor is by Invitation only. Please use the invited email address to register")) {
												appLog.info("alert(that investor does not have access) is successfully verified");
											}else {
												appLog.info("alert(that investor does not have access) is not verified");
												sa.assertTrue(false,"alert(that investor does not have access) is not verified");
											}
										}else {
											appLog.error("Alert for unsuccessful investor registration is not present");
											sa.assertTrue(false,"Alert for unsuccessful investor registration is not present");
											
										}
									}
									else {
										appLog.error("Sign up button on investor registration page is not clickable");
									}
								}
							}
								if (sendKeys(driver, bp.getTargetConfirmPassword(60), adminPassword, "Investor registration confirm password text box", action.BOOLEAN)) {
									if (click(driver, bp.getSignInLink(60), "investor registration sign in link", action.BOOLEAN)) {
										if (getURL(driver, 60).contains(InvestorURL)) {
											appLog.info("After clicking sign in link on registration page, navigated to investor login page");
											
										}
									
										else {
											sa.assertTrue(false,"investor login page is not opened after clicking on sign in link on registration page");
										}
									}
									else {
										sa.assertTrue(false,"Sign in link on registration page was not clickable");
									}
						
								}
								else {
									sa.assertTrue(false,"Confirm password text box is not visible on investor registration page");
								}
								
							}
							else {
								sa.assertTrue(false,"Password textbox on investor registration page was not visible");
							}
						}
						else {
							sa.assertTrue(false,"Firm name textbox on investor registration page was not visible");
						}
					}
					else {
						sa.assertTrue(false,"Email id textbox on investor registration page was not visible");
					}
				}
				else {
					sa.assertTrue(false,"Nick name textbox on investor registration page was not visible");
				}
			}
			else {
				sa.assertTrue(false,"Last name textbox on investor registration page was not visible");
			}
		}
		else {
			sa.assertTrue(false,"First name textbox on investor registration page was not visible");
		}
		sa.assertAll();
	}
	@Test
	public void M2tc007_InvestorRegistrationWithValidData() {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String M2Contact2EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact2", excelLabel.Contact_EmailId);
		String nickname_contact1 =  M2Contact1First + M2Contact1Last + bp.generateRandomNumber();
		driver.get(InvestorRegistrationURL);
		if (sendKeys(driver, bp.getTargetFirstName(60), M2Contact1First, "Target First Nametextbox", action.BOOLEAN)) {
			if (sendKeys(driver, bp.getTargetlastName(60), M2Contact1Last, "Target Last name textbox", action.BOOLEAN)) {
				if(sendKeys(driver, bp.getTargetNickName(60), nickname_contact1, "Nick name textbox", action.BOOLEAN)) {
					if (sendKeys(driver, bp.getTargetEmailId(60), M2Contact1EmailID, "Email id textbox", action.BOOLEAN)) {
						if (sendKeys(driver, bp.getTargetFirmName(60), M2FirmName1, "Target firm name textbox", action.BOOLEAN)) {
							if (sendKeys(driver, bp.getTargetpassword(60), adminPassword, "Password textbox", action.BOOLEAN)) {
								if (sendKeys(driver,bp.getTargetConfirmPassword(60), adminPassword, "Confirm password textbox", action.BOOLEAN)) {
									if (click(driver, bp.getTargetSignUpBtn(60), "Sign up button on investor registration page", action.BOOLEAN)) {
										if (matchTitle(driver, "Firm Alerts", 60)) {
											if (getSelectedOptionOfDropDown(driver, af.getFirmNameDropdown(60), "Firm name dropdown on investor firm page", "text").contains(M2FirmName1)) {

												appLog.info("Investor successfully registered and default landing page is firm page");
											}
										}
										else {
											sa.assertTrue(false, "Title is not Firm alerts so contact could be registered.");
										}
									}
									else {
										sa.assertTrue(false, "Investor registration page sign up button was not clickable");
									}
								}
								else {
									sa.assertTrue(false, "Confirm password textbox was not visible on investor registration page");
								}
							}
							else {
								sa.assertTrue(false, "Password textbox was not visible on investor registration page");
							}
						}else {
							sa.assertTrue(false, "Firm name textbox was not visible on investor registration page");
						}
					}
					else {
						sa.assertTrue(false, "Email id textbox was not visible on investor registration page");
					}
				}
				else {
					sa.assertTrue(false, "Nick name textbox was not visible on investor registration page");
				}
			}
			else {
				sa.assertTrue(false, "Last name textbox was not visible on investor registration page");
			}
		}
		else {
			sa.assertTrue(false, "First name textbox was not visible on investor registration page");
		}
		lp.investorLogout();
		//second investor registration

		driver.get(InvestorRegistrationURL);
		if (sendKeys(driver, bp.getTargetFirstName(60), M2Contact2First,"First name textbox", action.BOOLEAN)) {
			if (sendKeys(driver, bp.getTargetlastName(60),M2Contact2Last,  "Last name textbox",action.BOOLEAN)) {
				//enter nick name of 1st contact to display error
				if (sendKeys(driver,bp.getTargetNickName(60), nickname_contact1, "Nick name textbox", action.BOOLEAN)) {
					if (sendKeys(driver, bp.getTargetEmailId(60),M2Contact2EmailID,"Email id textbox", action.BOOLEAN)) {
						if (sendKeys(driver, bp.getTargetFirmName(60),M2FirmName1, "Firm name textbox", action.BOOLEAN)) {
							if (sendKeys(driver, bp.getTargetpassword(60), adminPassword, "target password textbox", action.BOOLEAN)) {
								if (sendKeys(driver, bp.getTargetConfirmPassword(60), adminPassword, "Confirm password textbox", action.BOOLEAN)) {
									if(bp.clickUsingCssSelectorPath("a.GlobalButton[title='Sign up']", "sign up button")) {
//									if (click(driver, bp.getTargetSignUpBtn(60), "investor registration sign up button", action.BOOLEAN)) {
										ThreadSleep(5000);
										if (isAlertPresent(driver)) {
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											if (msg.contains("This nickname already exists")) {
												appLog.info("alert(that nickname already exists) is successfully verified");
												sa.assertTrue(true);
											}
										}else {
											sa.assertTrue(false,"Alert for nickname already exists investor registration is not present");

										}
									}
									else {
										sa.assertTrue(false, "investor sign up link is not clickable on investor registration page");
									}
								}
								else {
									sa.assertTrue(false, "confirm password text box is not visible on investor registration page");
								}
							}
							else {
								sa.assertTrue(false, "password text box is not visible on investor registration page");
							}
						}
						else {
							sa.assertTrue(false, "firm name textbox is not visible on investor registration page");
						}
					}
					else {
						sa.assertTrue(false, "email id textbox is not visible on investor registration page");
					}
				}
				else {
					sa.assertTrue(false, "nickname textbox is not visible on investor registration page");
				}
			}
			else {
				sa.assertTrue(false, "contact last name textbox is not visible on investor registration page");
			}
		}
		else {
			sa.assertTrue(false, "contact first name textbox is not visible on investor registration page");
		}
		sa.assertAll();
	}
	
	@Test
	public void M2tc008_SecondInvestorRegistration() {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String M2Contact2EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact2", excelLabel.Contact_EmailId);
		String regLink=null;
		try {
			regLink = new EmailLib().getInvestorRegLink("InvitationMail",gmailUserName, gmailPassword, CRMUser1EmailID, M2Contact2EmailID);
		} catch (InterruptedException e) {
			e.printStackTrace();
			appLog.error("registration link is null");
		}
		if (regLink!=null)
		{
			driver.get(regLink);
			appLog.info("registration link was successfully opened");
		}
		else {
			driver.get(InvestorRegistrationURL);
			sa.assertTrue(false, "registration url was not found on mail, still opening URL from properties file");
		}
		//enter email id of 1st contact and nick name of 2nd contact to view error
		if (sendKeys(driver, bp.getTargetFirstName(60), M2Contact2First, "contact first name", action.BOOLEAN)) {
			if (sendKeys(driver, bp.getTargetlastName(60), M2Contact2Last, "contact last name", action.BOOLEAN)) {
				if (sendKeys(driver, bp.getTargetNickName(60), M2Contact2First + M2Contact2Last + bp.generateRandomNumber(), "nick name for contact2", action.BOOLEAN)) {
					
					if (sendKeys(driver, bp.getTargetEmailId(60), M2Contact1EmailID, "email id textbox", action.BOOLEAN)) {

						if (sendKeys(driver, bp.getTargetFirmName(60),M2FirmName1, "Firm name textbox", action.BOOLEAN)) {
							if (sendKeys(driver, bp.getTargetpassword(60), adminPassword, "target password textbox", action.BOOLEAN)) {
								if (sendKeys(driver, bp.getTargetConfirmPassword(60), adminPassword, "Confirm password textbox", action.BOOLEAN)) {
									if(bp.clickUsingCssSelectorPath("a.GlobalButton[title='Sign up']", "sign up button")) {
//									if(click(driver, bp.getTargetSignUpBtn(60), "sign up button", action.BOOLEAN)) {
										ThreadSleep(5000);
										if (isAlertPresent(driver)) {
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											if (msg.contains(BasePageErrorMessage.YouAreAlreadyRegistered)) {
												appLog.info("alert(you are already registered for navatar investor) is successfully verified");
												sa.assertTrue(true);
											}
										}else {
											sa.assertTrue(false,"Alert that you are already registered is not present");

										}

										if(sendKeys(driver, bp.getTargetEmailId(60), M2Contact2EmailID,"email id textbox", action.BOOLEAN)) {
											if (click(driver, bp.getTargetSignUpBtn(60), "investor sign up button", action.BOOLEAN)) {
												if (matchTitle(driver, "Firm Alerts", 60)) {
													appLog.info("2nd contact is successfully registered");
												}
												else {
													sa.assertTrue(false,"Title firm alerts is not matched, investor not registered succefully");
												}
											}
											else {
												sa.assertTrue(false, "Sign up button on investor registration page is not clickable");
											}
										}
										else {
											sa.assertTrue(false, "Email id textbox is not visible on investor registration page");
										}
									}
									else {
										sa.assertTrue(false, "sign up button is not clickable on investor registration page");
									}
								}
								else {
									sa.assertTrue(false, "Confirm password textbox is not visible on investor registration page");

								}
							}
							else {
								sa.assertTrue(false, "Password textbox is not visible on investor registration page");
							}
						}
						else {
							sa.assertTrue(false, "Firm name textbox is not visible on investor registration page");
						}
					}
					else {
						sa.assertTrue(false, "Email id textbox is not visible on investor registration page");

					}
				}
				else {
					sa.assertTrue(false, "Investor nickname textbox is not visible on investorr egistration page");
				}
			}
			else
			{
				sa.assertTrue(false, "Investor last name textbox is not visible");
			}
		}
		else {
			sa.assertTrue(false, "Investor first name textbox is not visible");
		}
		sa.assertAll();
	}

	@Test
	public void M2tc009_1_Org2PreconditionData() {
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		
		SoftAssert saa = new SoftAssert();
		String passwordResetLink=null;
		String Org2Admin = ExcelUtils.readData("Users",excelLabel.Variable_Name, "Org2Admin", excelLabel.User_Email);
		String Org2User1Last = ExcelUtils.readData("Users",excelLabel.Variable_Name, "Org2User1", excelLabel.User_Last_Name);
		String Org2User1First= ExcelUtils.readData("Users",excelLabel.Variable_Name, "Org2User1", excelLabel.User_First_Name);
		String Org2AdminLast = ExcelUtils.readData("Users",excelLabel.Variable_Name, "Org2Admin", excelLabel.User_Last_Name);
		String Org2AdminFirst= ExcelUtils.readData("Users",excelLabel.Variable_Name, "Org2Admin", excelLabel.User_First_Name);
		String org2adminPassword = ExcelUtils.readDataFromPropertyFile("passwordOrg2");
		
		lp.CRMLogin(Org2Admin, org2adminPassword);
		
		//Org2 creating user from admin
		String UserLastName = Org2User1Last + bp.generateRandomNumber();
		ExcelUtils.writeData(UserLastName, "Users", excelLabel.Variable_Name, "Org2User1", excelLabel.User_Last_Name);
		cv = new CommonVariables(this);
		if (bp.createPEUser(Org2User1First, UserLastName, cp.generateRandomEmailId(), CRMUserLicense,
				CRMUserProfile)) {
			appLog.info("Org2 User 1 created Successfully");
			String emailID = isDisplayed(driver, bp.getUserEmailIDLabeltext(60), "visibility", 60,
					"User Email ID Text Label", action.THROWEXCEPTION).getText().trim();
			ExcelUtils.writeData(emailID, "Users", excelLabel.Variable_Name, "Org2User1", excelLabel.User_Email);
		} else {
			appLog.info("PE User1 is not created successfully");
			saa.assertTrue(false, "PE User1 is not created successfully");
		}
		Org2User1Last = UserLastName;
		//installing pe package for user1
		if (bp.installedPackages(Org2User1First, Org2User1Last)) {
			appLog.info("Install package is done for PE User 1 succesfully");
		} else {
			appLog.info("Install package is not done for PE User 1 succesfully");
			saa.assertTrue(false, "Install package is not done for PE User 1 succesfully");
		}
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		try {
			passwordResetLink = new EmailLib().getResetPasswordLink("passwordreset",
					ExcelUtils.readDataFromPropertyFile("gmailUserName"),
					ExcelUtils.readDataFromPropertyFile("gmailPassword"));
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		appLog.info("ResetLinkIs: " + passwordResetLink);
		driver.get(passwordResetLink);
		if (bp.setNewPassword()) {
			appLog.info("Password is set successfully for user1");
		} else {
			appLog.info("Password is not set for user1");
			saa.assertTrue(false, "Password is not set for user1");
		}
		if (bp.getSalesForceLightingIcon(20) != null) {
			ThreadSleep(2000);
			click(driver, bp.getSalesForceLightingIcon(60), "sales force lighting icon", action.THROWEXCEPTION);
			ThreadSleep(1000);
			click(driver, bp.getSwitchToClassic(60), "sales force switch to classic link", action.THROWEXCEPTION);
			appLog.info("Sales Force is switched in classic mode successfully.");
		} else {
			appLog.info("Sales Force is open in classic mode.");
		}
		if (bp.getNavatarInvestorManagerTab(20) == null) {
			bp.addRemoveCustomTab("Navatar Investor Manager", customTabActionType.Add);
			if (bp.getNavatarInvestorManagerTab(20) != null) {
				appLog.info("Navatar Investor Manager Tab is displaying in Tab Row.");
			} else {
				appLog.info("Navatar Investor Manager Tab is not displaying in  Tab Row.");
				saa.assertTrue(false, "Navatar Investor Manager Tab is not displaying in Tab Row.");
			}
		}
		String userNameAtTab = getAttribute(driver, bp.getUserNameAtUserMenuTab(60), "User Name At User Menu Tab",
				"title");
		String userName = trim(userNameAtTab);
		saa.assertTrue(userName.contains(Org2User1Last), "PE User name is  not verified.");
		ThreadSleep(1000);
		
		lp.CRMlogout();
		driver.close();
		
		
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		frp = new FundRaisingPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		ip = new InstitutionPageBusinessLayer(driver);
		cp = new ContactPageBusinessLayer(driver);
		pp = new PartnershipPageBusinessLayer(driver);
		cmp = new CommitmentPageBusinessLayer(driver);
		np = new NIMPageBusinessLayer(driver);
		lp = new LoginPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		lp.CRMLogin(Org2Admin, org2adminPassword);
		//org2 admin nim access for itself
		String adminRegOrNot = ExcelUtils.readData("Users",excelLabel.Variable_Name, "Org2Admin", excelLabel.Registered);
		
		if (bp.clickOnTab(TabName.NIMTab)) {
			if (adminRegOrNot.contains("Not")) {
			if (np.NIMRegistration(userType.SuperAdmin, Org2AdminFirst, Org2AdminLast)) {
				appLog.info("ip access was given succesfully to admin ");
				if (ExcelUtils.writeData("Registered", "Users", excelLabel.Variable_Name, "Org2Admin", excelLabel.Registered)) {
					appLog.info("written registered for "+Org2Admin+" in excel");
				}
			}
			}
			else if(adminRegOrNot.contains("Registered"))
			{
				appLog.info("admin is already registered for ip access");
			}
			//giving access to user
			if (np.giveAccessToUserInNIMTabFromAdmin(Org2User1First+ " " + Org2User1Last, accessType.InternalUserAccess)) {
				appLog.info("internal user access was successfully given to "+Org2User1Last);
			}
			else {
				sa.assertTrue(false, "user "+Org2User1Last+" could not be given ip access from admin");
			}
			
			 //writing org2 firm name to excel
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
				if (np.clickOnSideMenusTab(sideMenu.Profiles)){
					if (np.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
						String firm_name = np.getFirmName(60).getText().trim();
						ExcelUtils.writeData(firm_name, "Users", excelLabel.Variable_Name, "Org2Admin", excelLabel.Firm_Name);
						appLog.info("firm name "+firm_name+" is successfully written to excel");
					}
					else {
						sa.assertTrue(false, "my firm profile tab is not clickable");
					}
				}
				else {
					sa.assertTrue(false, "profile side menu is not cllickable on nim page");
				}
				switchToDefaultContent(driver);
			
			
		}
		else {
			sa.assertTrue(false, "nim tab is not clickable");
		}
		lp.CRMlogout();
		driver.close();
		
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		frp = new FundRaisingPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		ip = new InstitutionPageBusinessLayer(driver);
		cp = new ContactPageBusinessLayer(driver);
		pp = new PartnershipPageBusinessLayer(driver);
		cmp = new CommitmentPageBusinessLayer(driver);
		np = new NIMPageBusinessLayer(driver);
		lp = new LoginPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		cv = new CommonVariables(this);
		
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String M2Contact2EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact2", excelLabel.Contact_EmailId);
		String Org2User1EmailID = ExcelUtils.readData("Users",excelLabel.Variable_Name, "Org2User1", excelLabel.User_Email);
		
		//Email id read from excel
		lp.CRMLogin(Org2User1EmailID, adminPassword);
		if(bp.removeUnusedTabs()){
			appLog.info("Unused tabs remove successfully");
		}else{
			appLog.info("Unused tabs not removed successfully");
			saa.assertTrue(false, "Unused tabs not removed successfully");
		}
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			ip.createInstitution(M2Institution1);
		}
		else {
			appLog.error("Institutions Tab is not clickable");
		}
		
		
		if (bp.clickOnTab(TabName.ContactTab)) {
			//M2C1 email will be same as M2C1 of org1
			if (cp.createContact(M2Contact1First, M2Contact1Last, M2Contact1Inst, M2Contact1EmailID)) {
				appLog.info("Contact "+M2Contact1Last+" was successfully created");
			}
			else {
				sa.assertTrue(false, "Contact "+M2Contact1Last+" could not be created");
			}
		}
		else {
			sa.assertTrue(false,"Contacts tab is not clickable");
		}
		if (bp.clickOnTab(TabName.ContactTab)) {
			//M2C2 email will be same as M2C2 of org1
			if (cp.createContact(M2Contact2First, M2Contact2Last, M2Contact2Inst, M2Contact2EmailID)) {
				appLog.info("Contact "+M2Contact2Last +" was successfully created");
			}
			else {
				sa.assertTrue(false, "Contact "+M2Contact2Last+ " could not be created");
			}
		}
		else {
			sa.assertTrue(false, "Contacts tab is not clickable");
		}
		
		if (bp.clickOnTab(TabName.FundsTab)){
		if (fp.createFund(M2Fund1Name,M2Fund1Type, M2Fund1InvCategory)) {
			appLog.info("New fund "+M2Fund1Name+ " was successfully created");
		}
		else {
			sa.assertTrue(false, "New fund "+M2Fund1Name+" could not be created");
		}
		}
		else {
			sa.assertTrue(false, "Funds tab is not clickable");
		}
		
		if (bp.clickOnTab(TabName.FundraisingsTab)) {
		if (frp.createFundRaising(M2Fundraising1, M2Fundraising1Fund, M2Fundraising1Inst)) {
			appLog.info("Fundraising "+ M2Fundraising1+ " was successfully created");
		}
		else {
			sa.assertTrue(false, "Fundraising "+M2Fundraising1+ " could not be created");
		}
		}
		else {
			sa.assertTrue(false, "Fundraising tab is not clickable");
		}
		
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
		if (ip.createLimitedPartner(M2LP1, M2LP1Inst)) {
			appLog.info(M2LP1+ " limited partner was successfully created");
		}
		else {
			sa.assertTrue(false, M2LP1+ " LP could not be created");
		}
		}
		else {
			sa.assertTrue(false, "Institution tab is not clickable");
		}
		
		WebElement ele=FindElement(driver, "//a[contains(@title,'Partnerships')]", "Partnerships tab",
				action.SCROLLANDBOOLEAN, 10);
		if(ele==null){
			bp.addRemoveCustomTab("Partnerships", customTabActionType.Add);
			
			if (bp.clickOnTab(TabName.PartnershipsTab)) {
				if (pp.createPartnership(M2Partnership1Name, M2Partnership1Fund)) {
					appLog.info("Partnership is created successfully");
				} else {
					appLog.info("Partnership is not created successfully");
					sa.assertTrue(false, "Partnership is not created successfully");
				}
			} else {
				appLog.info("Not able to click on Partnership tab so we cannot create Partnership");
				sa.assertTrue(false, "Not able to click on Partnership tab so we cannot create Partnership");
			}
		}else{
		if (bp.clickOnTab(TabName.PartnershipsTab)) {
			if (pp.createPartnership(M2Partnership1Name, M2Partnership1Fund)) {
				appLog.info("Partnership is created successfully");
			} else {
				appLog.info("Partnership is not created successfully");
				sa.assertTrue(false, "Partnership is not created successfully");
			}
		} else {
			appLog.info("Not able to click on Partnership tab so we cannot create Partnership");
			sa.assertTrue(false, "Not able to click on Partnership tab so we cannot create Partnership");
		}
		}
		
		ele=FindElement(driver, "//a[contains(@title,'Commitments')]", "Commitments tab",
				action.SCROLLANDBOOLEAN, 10);
		if(ele==null){
			bp.addRemoveCustomTab("Commitments", customTabActionType.Add);
			ThreadSleep(1000);
			 if (bp.clickOnTab(TabName.CommitmentsTab)) {
				if (cmp.createCommitment(M2Commitment1LP, M2Commitment1Partnership, "M1Commitment1", null)) {
					appLog.info("Commitment is created successfully");
				} else {
					appLog.info("Commitment is not created successfully");
					sa.assertTrue(false, "Commitment is not created successfully");
				}
			} else {
				appLog.info("Not able to click on Commitment tab so we cannot create Commitment");
				sa.assertTrue(false, "Not able to click on Commitment tab so we cannot create Commitment");
			}
	
		}else{		
		if (bp.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.createCommitment(M2Commitment1LP, M2Commitment1Partnership, "M1Commitment1", null)) {
				appLog.info("Commitment is created successfully");
			} else {
				appLog.info("Commitment is not created successfully");
				sa.assertTrue(false, "Commitment is not created successfully");
			}
		} 
		}
		
		//user registering for IP access
		if (bp.clickOnTab(TabName.NIMTab)) {
			if (np.NIMRegistration(userType.CRMUser, Org2User1First, Org2User1Last)) {
				appLog.info("user has successfully registered itself for ip access");
			}
			else {
				sa.assertTrue(false, "user could not register for IP access");
			}
		}
		else {
			appLog.error("Cannot click on Nim tab");
			sa.assertTrue(false, "cannot click on nim tab");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M2tc009_2_createFolderTemplateOrg2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String Org2User1Email = ExcelUtils.readData("Users",excelLabel.Variable_Name, "Org2User1", excelLabel.User_Email);
		lp.CRMLogin(Org2User1Email, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.createFolderTemplate("FolderTemp", folderTemplateName, "", 60)) {
				appLog.info("folder template"+folderTemplateName+" is successfully created");
			}
			else {
				sa.assertTrue(false, "folder template could not be created");
			}
			switchToDefaultContent(driver);
		}
		else {
			sa.assertTrue(false, "nim tab is not clickable so cannot create folder template");
		}
		sa.assertAll();
	}
	
	@Test
	public void M2tc010_Org2BuildFundraisingAndInvestorWorkspace() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String Org2User1EmailID = ExcelUtils.readData("Users",excelLabel.Variable_Name, "Org2User1", excelLabel.User_Email);
		String contact_phone = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_Phone);
		String contact_email = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		
		String[] step1of3 = {"" , "" , M2Contact1Last, contact_phone,contact_email, ""};
		lp.CRMLogin(Org2User1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M2Fund1Name)) {
				if (fp.buildWorkspace(step1of3, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null, M2Institution1, Workspace.FundraisingWorkspace, 60)) {
					appLog.info("fundraising workspace is successfully built");
				}
				else {
					sa.assertTrue(false, "could not build fundraising workspace");
				}
				if (fp.buildWorkspace(step1of3, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null, M2Institution1+"/"+M2LP1, Workspace.InvestorWorkspace, 60)) {
					appLog.info("investor workspace successfully built");
				}
				else {
					sa.assertTrue(false, "could not build investor workspace");
				}
			}
		else {
			sa.assertTrue(false, "could not find fund "+M2Fund1Name+ " hence cannot build workspace");
		}
		}
	
	else {
		sa.assertTrue(false, "funds tab is not clickable");
	}
		lp.CRMlogout();
	sa.assertAll();
			}
	@Test	
	public void M2tc011_ContactAccessAndInvitationMailOrg2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String M2Contact2EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact2", excelLabel.Contact_EmailId);
		String Org2User1EmailID = ExcelUtils.readData("Users",excelLabel.Variable_Name, "Org2User1", excelLabel.User_Email);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(Org2User1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M2Fund1Name)) {
				//giving access in fundraising workspace
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.inviteContact(environment, mode, M2Institution1, M2Contact1EmailID, stdPath, FolderType.Standard, "Upload", "Yes", "Yes", "All Folders", Workspace.FundraisingWorkspace, M2Contact1EmailID)) {
					appLog.info("contact has been given access successfully and invite has been sent to mail");
				}
				else {
					sa.assertTrue(false, "Contact could not be given access");
				}
			
			
				//giving access in investor workspace
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.inviteContact(environment, mode, M2Institution1+"/"+M2LP1, M2Contact1EmailID, stdPath, FolderType.Standard, "Upload", "Yes", "Yes", "All Folders", Workspace.InvestorWorkspace, M2Contact1EmailID)) {
					appLog.info("contact has been given access successfully and invite has been sent to mail");
				}
				else {
					sa.assertTrue(false, "Contact could not be given access");
				}
			}
			else {
				sa.assertTrue(false, "fund name provided could not be found");
			}
		}
		else {
			sa.assertTrue(false, "Funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	@Test
	public void M2tc012_InvestorLoginPageFunctionality() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPage ifp = new InvestorFirmPage(driver);
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		
		SoftAssert sa = new SoftAssert();
		//Logging from firm 1 user and sending email to contact1
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (lp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M2Fund1Name)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.sendInvitationMail(Workspace.FundraisingWorkspace, M2Contact1EmailID, "All Folders", M2Contact1Last)) {
					appLog.info("invitation mail to "+M2Contact1EmailID+" has been sent again");
				}
				else {
					sa.assertTrue(false, "invitation mail could not be sent");
				}
				switchToDefaultContent(driver);
			}
			else {
				sa.assertTrue(false, M2Fund1Name+" could not be found");
			}
		}
		else {
			sa.assertTrue(false, "funds tab is not clickable");
		}
		
		String login_link=null;
		try {
			login_link = new EmailLib().getInvestorLoginLink("InvitationMail",gmailUserName, gmailPassword,CRMUser1EmailID,M2Contact1EmailID );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		lp.CRMlogout();
		//closing browser and opening investor portal
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		
		lp = new LoginPageBusinessLayer(driver);
		af = new AllFirmsPageBusinesslayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		ifp = new InvestorFirmPageBusinesslayer(driver);
		//opening login link and check after login firm name 1 is found
		if (login_link!=null)
		{
			driver.get(login_link);
			System.out.println("login link is"+login_link);
		
		
			if (sendKeys(driver, lp.getInvestorUsernameTextbox(60), M2Contact1EmailID, "investor login username textbox", action.BOOLEAN)) {
				if (sendKeys(driver, lp.getInvestorPasswordTextbox(60), adminPassword,"investor login password text box", action.BOOLEAN)) {
					if (click(driver, lp.getInvestorLoginButton(60), "investor login button", action.BOOLEAN)) {
						if (matchTitle(driver, "Firm Alerts", 60)) {
							if (getSelectedOptionOfDropDown(driver, af.getFirmNameDropdown(60), "firm name dropdown", "text").contains(M2FirmName1)) {
								appLog.info("Investor logged logged in successfully and correct firm name 1 is displayed :"+M2FirmName1);
							}
							else {
								sa.assertTrue(false, "Correct firm information is not displayed");
							}
							ThreadSleep(5000);
							if (click(driver, ifp.getAllDocumentsTab(60), "al documents tab on investor firm page", action.SCROLLANDBOOLEAN)) {
							appLog.info("all documents tab is successfully clicked on investor firm page");
							}
							else {
								sa.assertTrue(false, "all documents tab is not clickable on investor firm page");
							}
							if (selectVisibleTextFromDropDown(driver, af.getFirmNameDropdown(60), "firm name dropdown on investor firm page", M2FirmName2)) {
								if (click(driver, ifp.getRecentActivitiesTab(60), "recent activities tab", action.SCROLLANDBOOLEAN)) {
									appLog.info("recent activities is selected manuallly in firm 2");
								}
								else {
									sa.assertTrue(false, "recent activities tab is not clickable");
								}
							}
							else {
								sa.assertTrue(false, "firm name dropdown does not contain "+M2FirmName2);
							}
							lp.investorLogout();
						}
						
						else {
							sa.assertTrue(false, "Investor not correctly logged in");
						}
					}
					else {
						sa.assertTrue(false, "Investor login button is not clickable on investor login page");
					}
				}
				else {
					sa.assertTrue(false, "password text box is not visible on investor login page");
				}
			}
			else {
			sa.assertTrue(false, "username text box is not visible on investor login page");
			}
			
			//checking facebook and twitter links on investor login page
			driver.get(InvestorURL);
			String parentID=null;
			if (click(driver, lp.getTwitterIcon(60), "twitter icon on investor login page", action.BOOLEAN)) {
				ThreadSleep(5000);
				parentID=switchOnWindow(driver);
				ThreadSleep(5000);
				String tempHandle = driver.getWindowHandle();
				if(!tempHandle.equals(parentID)) {
					appLog.info("Navatar twitter page successfully opened");
				}
				else {
					sa.assertTrue(false,"Navatar twitter page was not opened");
				}
				driver.close();
				ThreadSleep(5000);
			}
			else {
				sa.assertTrue(false,"Twitter icon link is not clickable on investor login page");
				
			}
			driver.switchTo().window(parentID);
				if (click(driver, lp.getFacebookIcon(60), "Facebook icon on investor login page", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(10000);
					parentID=switchOnWindow(driver);
					ThreadSleep(5000);
					String tempHandle = driver.getWindowHandle();
					if(!tempHandle.equals(parentID)) {
						appLog.info("Navatar facebook page successfully opened");
						
					}
					else {
						sa.assertTrue(false, "Navatar facebook page was not opened");
						
					}
					driver.close();
					driver.switchTo().window(parentID);
					ThreadSleep(5000);
				}
				else {
					sa.assertTrue(false, "facebook icon is not clickable");
				}
				
		}
		else {
			sa.assertTrue(false, "Login link was not found from mail");
		}
		
		sa.assertAll();
		}
	@Test
	public void M2tc013_OpenEmailFromFirm2AndLoginFromLinkOnMail() {
		EmailLib el = new EmailLib();
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer invFirm = new InvestorFirmPageBusinesslayer(driver);
		String loginURL = null;
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String Org2User1 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "Org2User1", excelLabel.User_Email);
		try {
			loginURL=el.getInvestorLoginLink("InvitationMail",gmailUserName, gmailPassword,Org2User1,M2Contact1EmailID );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (loginURL!=null) {
			driver.get(loginURL);
			if (sendKeys(driver,lp.getInvestorUsernameTextbox(60),M2Contact1EmailID , "investor user name text box", action.BOOLEAN)) {
				if (sendKeys(driver, lp.getInvestorPasswordTextbox(60), adminPassword, "investor password text box" ,action.BOOLEAN)) {
					if (click(driver, lp.getInvestorLoginButton(60), "investor login button", action.BOOLEAN)) {
						if (getSelectedOptionOfDropDown(driver, af.getFirmNameDropdown(60), "Firm name dropdown on investor all firms page", "text").contains(M2FirmName2)) {
				appLog.info("firm 2 name is successfully verified on investor page after logging from firm 2 email link");
					if (invFirm.getActivitiesCreatedOnLabel(60)!=null) {
					appLog.info("Recent Activities page is successfully displayed of firm 2");
				}
				else {
					sa.assertTrue(false, "Recent Activities page is not displayed of firm 2");
				}
			}
			else {
				sa.assertTrue(false, "firm name selected is not "+M2FirmName2);
			}
				//selecting firm 1 to check tab changed or not
			if( selectVisibleTextFromDropDown(driver,af.getFirmNameDropdown(60) , "firm name dropdown on investor login", M2FirmName1))
			{
				if (invFirm.getActivitiesCreatedOnLabel(60)==null) {
					appLog.info("All documents page is successfully opened of Firm 1 "+M2FirmName1);
				}
			}
			else {
				sa.assertTrue(false, "dropdown is not visible on investor login page");
			}
			}
			else {
				sa.assertTrue(false, "investor login button is not clickable");
			}
			
		}
				else {
					sa.assertTrue(false, "investor password textbox is not visible on investor login page");
				}
			}
			else {
				sa.assertTrue(false, "investor username textbox is not visible on ivestor login page");
			}
		}
	
		else {
			sa.assertTrue(false, "invitation url is not received");
		}
		sa.assertAll();
		
	}
	@Test
	public void M2tc014_LoginWithMultipleData() {
		
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer ap = new AllFirmsPageBusinesslayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String M2Contact2EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact2", excelLabel.Contact_EmailId);
		driver.get(InvestorURL);
		SoftAssert sa = new SoftAssert();
		if(bp.clickUsingCssSelectorPath("input[title='Login']", "login button")) {
//		if (click(driver, lp.getInvestorLoginButton(60), "Investor page login button", action.BOOLEAN)) {
			ThreadSleep(5000);
			if (isAlertPresent(driver)) {

				String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
				switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
				if (msg.contains("Username is mandatory")) {
					appLog.info("Username is mandatory correct alert was found");
				}
				else {
					sa.assertTrue(false, "Wrong alert found when username was empty in investor login");
				}
			}
			else {
				sa.assertTrue(false, "No alert was found");
			}
		}
		if (sendKeys(driver, lp.getInvestorUsernameTextbox(60), M2Contact1EmailID, "Investor page username textbox", action.BOOLEAN)) {
			if(bp.clickUsingCssSelectorPath("input[title='Login']", "login button")) {
//			if (click(driver, lp.getInvestorLoginButton(60), "Investor page login button", action.BOOLEAN)) {
				ThreadSleep(5000);
				if (isAlertPresent(driver)) {

					String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
					switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
					if (msg.contains("Password is mandatory")) {
						appLog.info("Password is mandatory correct alert was found");
					}
					else {
						sa.assertTrue(false, "Wrong alert found when password was empty in investor login");
					}
				}
				else {
					sa.assertTrue(false, "No alert was found");
				}
			}
			else {
				sa.assertTrue(false,"Investor login button was not clickable on investor login page");
			}
		}
		else {
			sa.assertTrue(false,"Username textbox was not visible on investor login page");
		}

		//Check alert when username is empty and password is filled
		lp.getInvestorUsernameTextbox(60).clear();
		if (sendKeys(driver, lp.getInvestorPasswordTextbox(60), "Testing", "Investor login page password textbox", action.BOOLEAN)) {
			if(bp.clickUsingCssSelectorPath("input[title='Login']", "login button")) {
//			if (click(driver, lp.getInvestorLoginButton(60), "Investor page login button", action.BOOLEAN)) {
				ThreadSleep(5000);
				if (isAlertPresent(driver)) {

					String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
					switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
					if (msg.contains("Username is mandatory")) {
						appLog.info("Username is mandatory, correct alert was found");
					}
					else {
						sa.assertTrue(false, "Wrong alert found when username was empty in investor login");
					}
				}
				else {
					sa.assertTrue(false,"Alert not found on investor login page when username textbox is empty");
				}
			}
			else {
				sa.assertTrue(false,"Investor login button was not clickable on investor login page");
			}
		}
		else {
			sa.assertTrue(false,"Investor password textbox was not visible on investor login page");
		}

		if (sendKeys(driver, lp.getInvestorUsernameTextbox(60), M2Contact1EmailID, "Investor username textbox on investor login page", action.BOOLEAN)) {
			if (sendKeys(driver, lp.getInvestorPasswordTextbox(60), "Testing", "Investor password textbox on investor login page", action.BOOLEAN)) {
				if(bp.clickUsingCssSelectorPath("input[title='Login']", "login button")) {
//				if (click(driver, lp.getInvestorLoginButton(60), "Investor login button", action.BOOLEAN)) {
					ThreadSleep(5000);
					if (isAlertPresent(driver)) {
						String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
						switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
						if (msg.contains("The Username or Password you provided do not match. Please check your credentials")) {
							appLog.info("Username and password are wrong, correct alert was found");

						}
						else {
							sa.assertTrue(false, "Wrong alert found when username and password were wrong in investor login");
						}
					}
					else {
						sa.assertTrue(false,"No alert was found when investor username and password are wrong on investor login page");
					}
				}
				else {
					sa.assertTrue(false,"Investor login button was not clickable on investor login page");
				}
			}
			else {
				sa.assertTrue(false,"Investor password textbox was not visible on investor login page");
			}
		}
		else {
			sa.assertTrue(false,"Username textbox was not visible on investor login page");
		}
		if (sendKeys(driver, lp.getInvestorUsernameTextbox(60), M2Contact1EmailID, "Investor username textbox on investor login page", action.BOOLEAN)) {
			if (sendKeys(driver, lp.getInvestorPasswordTextbox(60), adminPassword, "Password textbox on investor login page", action.BOOLEAN)) {
				if (click(driver, lp.getInvestorLoginButton(60), "Investor login button on investor login page", action.BOOLEAN)) {
					ThreadSleep(4000);
					List<WebElement> lst =allOptionsInDropDrop(driver, ap.getFirmNameDropdown(60), "firm name drop down list");
					if(!lst.isEmpty()) {
						if(fp.checkSorting(SortOrder.Assecending, lst)) {
							appLog.info("firms name are visible in the alphabetic order");
						}else {
							appLog.error("firms name are not visible in the alphabetic order");
							sa.assertTrue(false, "firms name are not visible in the alphabetic order");
						}
					}else {
						appLog.error("Firms Name drop down list is empty. so cannot check sorting of fims name ");
						sa.assertTrue(false, "Firms Name drop down list is empty. so cannot check sorting of firms name");
					}
				}
				else {
					sa.assertTrue(false,"Investor login button was not clickable on investor login page");
				}
			}
			else {
				sa.assertTrue(false,"Investor password textbox was not visible on investor login page");
			}
		}
		else {
			sa.assertTrue(false,"Investor username textbox was not visible on investor login page");
		}
		sa.assertAll();

	}
	@Test
	public void M2tc015_ClickOnMultipleLinksInvestorLoginPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		String parentID;
		String second_wind_ID;
		Set<String> windows;
		driver.get(InvestorURL);
		if (click(driver, lp.getForgotUsernameInvestor(60), "Forgot username link on investor login page", action.BOOLEAN)) {
			parentID=switchOnWindow(driver);
			ThreadSleep(5000);
			second_wind_ID = driver.getWindowHandle();
			if ((lp.getPleaseContactOurLabel(60)!=null)&&(lp.getCloseButtonForgotPassword(60)!=null)){
				appLog.info("Please contact our customer support text and close link was successfully found on forgot your username window");

				if (click(driver, lp.getCustomerSupportLink(60), "customer support link on forgot your username page", action.BOOLEAN)) {
					windows = driver.getWindowHandles();
					if (windows!=null) {
						for (String temp_window:windows) {
							if (!temp_window.equalsIgnoreCase(parentID)) {
								if (!temp_window.equalsIgnoreCase(second_wind_ID)) {
									driver.switchTo().window(temp_window);
									break;
								}
							}
						}
					}
					ThreadSleep(5000);
					if (getURL(driver, 60).contains(ExcelUtils.readDataFromPropertyFile("NavatarSupportLink"))) {
						appLog.info("customer support link was successfully clicked");
						sa.assertTrue(true, "customer support link was not opened");
						driver.close();
						driver.switchTo().window(second_wind_ID);
						driver.close();
					}
				}
				else {
					sa.assertTrue(false,"customer support url was not clickable");
				}
			}
			else {
				sa.assertTrue(false, "Please contact our customer support and close link was not found");

			}
			driver.switchTo().window(parentID);
		}
		else {

			sa.assertTrue(false,"Forgot username url on investor login page was not clickable");
		}
		//clicking on navatar logo
		if (click(driver, lp.getNavatarImg(60), "navatar logo on investor login", action.SCROLLANDBOOLEAN)) {
			ThreadSleep(5000);
			if (getURL(driver, 60).contains(InvestorURL)) {
				appLog.info("Investor login Url was opened after clicking Navatar logo");
			}
			else {
				sa.assertTrue(false, "Investor URL was not opened after clicking on navatar logo");
			}
		}
		else {
			sa.assertTrue(false, "Navatar logo image was not clickable");
		}
		//clicking on sign up link
		if (click(driver, lp.getInvestorAlreadyInvitedSignupLink(60), "Sign up link", action.BOOLEAN)) {
			ThreadSleep(5000);
			if (getURL(driver, 60).contains(InvestorRegistrationURL)) {
					appLog.info("Registration page opened successfully after clicking on sign up link on investor login page");
				}
			else {
				if (click(driver, lp.getInvestorAlreadyInvitedSignupLink(60), "Sign up link", action.BOOLEAN)) {
					ThreadSleep(5000);
					System.err.println(getURL(driver, 60));
					if (getURL(driver, 60).contains("SiteRegisteration1")) {
							appLog.info("Registration page opened successfully after clicking on sign up link on investor login page");
						}
					else {
				appLog.error("Investor registration page was not opened");
				sa.assertTrue(false, "Investor registration page was not opened");
					}
				}
			}
			
		}
		else {
			sa.assertTrue(false,"sign up link was not clickable on investor login page");
		}
		driver.get(InvestorURL);
		
		
		//clicking on sign up button for general partner
		if (click(driver, lp.getGeneralPartnerAlreadyInvitedSignupLink(60), "Sign up link", action.BOOLEAN)) {
			ThreadSleep(5000);
			if (getURL(driver, 60).contains(InvestorURL+"apex/NonUserRegisterStep1")) {
					appLog.info("Registration page opened successfully after clicking on sign up link on investor login page");
				}
			else {
				
				if (click(driver, lp.getGeneralPartnerAlreadyInvitedSignupLink(60), "Sign up link", action.BOOLEAN)) {
					ThreadSleep(5000);
					if (getURL(driver, 60).contains(InvestorURL+"apex/NonUserRegisterStep1")) {
							appLog.info("Registration page opened successfully after clicking on sign up link on investor login page");
						}
					else {
				sa.assertTrue(false, "Investor registration page was not opened");
					}
				}
			}
			
		}
		else {
			sa.assertTrue(false,"sign up link was not clickable on investor login page");
		}
		driver.get(InvestorURL);
		if (click(driver, lp.getResetPasswordLink(60), "Reset password url on investor login page", action.SCROLLANDBOOLEAN)) {
			
			
			if (lp.getEnterEmailIdTextForgotPasswordPage(60)!=null)
			{
				if (lp.getEnterEmailIdTextForgotPasswordPage(60).getText().trim().contains(LoginErrorPage.pleaseEnterYourEmailForgotPassword)) {
					appLog.info("Text to enter email id to change password was not present on forgot your password page");

				}
				else {
					sa.assertTrue(false, "text to enter email id to change password is not correct");
				}
			}
			else {
				sa.assertTrue(false, "Text to enter email id to change password was not present on forgot your password page");	
			}
			
			if (lp.getResetPasswordSubmitButton(60)!=null) {
				appLog.info("submit button was successfully found on forgot password page");
			}
			else {
			sa.assertTrue(false, "submit button was not present on forgot password page");
			}
			
		}
		else {
			appLog.error("forgot password link is not present on investor page");
			sa.assertTrue(false, "forgot password link is not present on investor page");
		}
		sa.assertAll();
		
	}
	
	@Test
	public void M2tc016_ForgotPasswordFunctionality() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer (driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		EmailLib el = new EmailLib();
		
		String passwordResetChars=null;
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String cont_password_updated = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_password_updated);
		driver.get(InvestorURL);
		if (click(driver, lp.getResetPasswordLink(60), "reset password link on investor login page", action.BOOLEAN)) {
			if (sendKeys(driver, lp.getEmailTextbox(60), M2Contact1EmailID, "email id textbox on forgot password page", action.BOOLEAN)) {
				if (click(driver, lp.getResetPasswordSubmitButton(60), "submit button on reset password page", action.BOOLEAN)) {
					ThreadSleep(15000);

					if (lp.getResetPasswordConfirmationTemproryPasswdMsg(60).getText().trim().contains(LoginErrorPage.resetPasswordConfirmationTemproryPasswdMsg)) {
						appLog.info("Text is successfully displayed that email to reset password has been sent");
					}
					else {
						appLog.error("could not verify 'email to reset password has been sent'");
						sa.assertTrue(false, "could not verify 'email to reset password has been sent'");
					}
					ThreadSleep(4000);
					SoftAssert saa=bp.verifyResetPasswordMailContent(M2Contact1First, M2Contact1EmailID, "InvestorPage", null, null,null);
					sa.combineAssertions(saa);

					ThreadSleep(4000);
					appLog.info(passwordResetChars);
					driver.get(ExcelUtils.readData("Contacts", excelLabel.Variable_Name	, "M2Contact1", excelLabel.Click_HereLink));


					/*if (lp.getChangeYourPasswordText(60).getText().trim().equals("Change Your Password")){
						appLog.info("change your password text is successfully present on forgot password page");
					}
					else {
						appLog.error("change your password text couldn otbe verified on forgot password page");
						sa.assertTrue(false, "change your password text couldn otbe verified on forgot password page");
					}
					if (lp.getChangePasswordBtnForgotPassword(60)!=null) {
						appLog.info("change password button is successfully present on forgot password page");
					}
					else {
						appLog.error("change password button could not be found on forgot password page");
						sa.assertTrue(false, "change password button could not be found on forgot password page");
					}
					if (lp.getNewPasswordText(60).getText().trim().equals("New Password")){
						appLog.info("new password text is successfully present on forgot password page");
					}
					else {
						appLog.error("new password text could not be found on forgot password page");
						sa.assertTrue(false, "new password text could not be found on forgot password page");
					}
					if (lp.getVerifyYourPasswordText(60).getText().trim().equals("Verify New Password")) {
						appLog.info("verify new password text is successfully present on forgot password page");
					}
					else {
						appLog.error("verify new password text could not be found on forgot password page");
						sa.assertTrue(false, "verify new password text could not be found on forgot password page");
					}*/
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
					/*if (sendKeys(driver, lp.getForgotPasswordNewPasswordTextbox(60), adminPassword, "new password textbox", action.BOOLEAN)) {
						if (sendKeys(driver, lp.getForgotPasswordVerifyNewPasswordTextbox(60), adminPassword, "new password textbox", action.BOOLEAN)) {
							if (click(driver, lp.getChangePasswordBtnForgotPassword(60), "change password button", action.BOOLEAN)) {
								if (lp.getCannotReuseOldPassword(60).getText().trim().contains(LoginErrorPage.cannotResuldOldPassword)) {
									appLog.error("Correct error message is displayed that you cannot reuse old password");
								}
								else {
									sa.assertTrue(false, "Error message cannot reuse old password is not visible");
								}

							}
							else {
								sa.assertTrue(false, "change password button is not clickable on forgot password page");
							}
						}
						else {
							sa.assertTrue(false, "verify new password textbox is not visible on forgot password page");
						}
					}
					else {
						sa.assertTrue(false, "new password textbox is not visible on forgot password page");
					}
					if (sendKeys(driver, lp.getForgotPasswordNewPasswordTextbox(60), cont_password_updated, "new password textbox", action.BOOLEAN)) {
						if (sendKeys(driver, lp.getForgotPasswordVerifyNewPasswordTextbox(60), cont_password_updated+"5", "verify new password textbox", action.BOOLEAN)) {
							if (click(driver, lp.getChangePasswordBtnForgotPassword(60), "change password button", action.BOOLEAN)) {
								if (lp.getPasswordsNotMatch(60).getText().trim().contains(LoginErrorPage.passwordNotMatch)) {
									appLog.info("correct error message is displayed that password do not match");
								}
								else {
									sa.assertTrue(false, "error message that passwords do not match was not visible on forgot password page");
								}
							}
							else {
								sa.assertTrue(false, "change password button is not clickable on forgot password page");
							}
						}
						else {
							sa.assertTrue(false, "verify new password textbox is not visible on forgot password page");
						}
					}
					else {
						sa.assertTrue(false, "new password textbox is not visible on forgot password page");
					}*/
					if (sendKeys(driver, lp.getResetPasswordNewPasswordTextBox(60), cont_password_updated, "new password textbox on forgot password page", action.BOOLEAN) ){
						if (sendKeys(driver, lp.getResetPasswordConfirmPasswordTextBox(60), cont_password_updated, "verify new password on forgot password page", action.BOOLEAN)) {
							if (click(driver, lp.getResetPasswordButton(60), "change password button on forgot password page", action.BOOLEAN)) {
								if(bp.getResetPasswordPasswordChangeMessage(60).getText().equalsIgnoreCase(LoginErrorPage.resetPasswordPasswordChangeMessage)){
									appLog.info("Password change message is verified");
								}else{
									appLog.error("Password change message is not verified");
									sa.assertTrue(false, "Password change message is not verified");
								}
							}
							else {
								sa.assertTrue(false, "change password button is not clickable on forgot password page");
							}
						}
						else {
							sa.assertTrue(false, "verify new password textbox is not visible on forgot password page");
						}
					}
					else {
						sa.assertTrue(false, "new password textbox is not visible on forgot password page");
					}
					ThreadSleep(3000);
					driver.get(InvestorURL);
					if (sendKeys(driver, lp.getInvestorUsernameTextbox(60), M2Contact1EmailID, "investor login username textbox", action.BOOLEAN)) {
						if (sendKeys(driver, lp.getInvestorPasswordTextbox(60), cont_password_updated, "investor login Password textbox", action.BOOLEAN)) {
							if (click(driver, lp.getInvestorLoginButton(60), "Investor login button", action.BOOLEAN)) {
								if (matchTitle(driver, "Firm Alerts", 60)) {
									appLog.info("Investor logged with in with new password, so password changed successfully");
									
									lp.investorLogout();
								}
								else {
									sa.assertTrue(false, "Title not matched, investor could not login with new password");
								}
							}
							else {
								sa.assertTrue(false, "Investor login button is not clickable on investor login page");
							}

						}
						else {
							sa.assertTrue(false, "Investor password textbox is not visible on investor login page");
						}
					}
					else {
						sa.assertTrue(false, "investor username textbox is not visible on investor login page");
					}

				}
				else {
					sa.assertTrue(false, "submit button on is not clickable forgot password page");
				}
			}
			else {
				sa.assertTrue(false, "email textbox is not visible on forgot password page");
			}
		}
		else {
			sa.assertTrue(false, "forgot password link is not clickable on forogt password page");
		}
		sa.assertAll();
		
	}


	@Test
	public void M2tc017_MyProfileUICheck() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String cont_password_updated = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_password_updated);
		String[] basic_table_string = {"Name:", "Firm:", "Phone:", "Title:","LinkedIn:","E-mail:","Facebook:","Address:"}; 
		String[] investment_pref_string = {"Username:", "Password:"};
		String[] heads_str = {"Basic Information", "Login Information","Notification Preferences"};
		String[] editmode_basic_table = {"First Name:", "Last Name:", "Phone:", "LinkedIn:", "Firm:", "Facebook:", "Title:", "E-mail:" , " ", "Mailing Street:","Mailing City:", "Mailing State/Province:", "Mailing Zip/Postal Code:", "Mailing Country:"}; 
		if (lp.investorLogin( M2Contact1EmailID, cont_password_updated)) {
			if (click(driver, af.getProfileLink(60), "profile link on investor firm page", action.SCROLLANDBOOLEAN)) {
				if (getSelectedOptionOfDropDown(driver, af.getFirmNameDropdown(60), "firm name dropdown investor firm page", "text").equals("")) {
					appLog.info("Firm name dropdown is succesfully verified as empty on my profile page");
						int size_basic_info_list=ip.getBasicInfoLabelTextList().size();
					int size_login_info_list=ip.getLoginInfoLabelTextList().size();
					int size_subheads=ip.getSubHeads().size();
					List<String> basic_table = new ArrayList<String>();
					basic_table.addAll( Arrays.asList(basic_table_string) );

					if (!ip.getBasicInfoLabelTextList().isEmpty()) {
						for (int i = 0;i<size_basic_info_list;i++) {
							WebElement temp_element = ip.getBasicInfoLabelTextList().get(i);
							if (temp_element.getText().trim().equals(basic_table.get(i))){
								appLog.info(basic_table.get(i)+" was successfully found on my profile page");
							}
							else {
								sa.assertTrue(false, basic_table.get(i)+" was not found on page");
							}
						}

					}
					else {
						sa.assertTrue(false, "Basic info label text list is empty");
					}
					List<String> invest_pref_list = new ArrayList<String>();
					invest_pref_list.addAll((Arrays.asList(investment_pref_string)) );
					if (!ip.getLoginInfoLabelTextList().isEmpty()) {

						for (int i = 0;i<size_login_info_list;i++) {
							WebElement temp_element = ip.getLoginInfoLabelTextList().get(i);
							if (temp_element.getText().trim().equals(invest_pref_list.get(i))) {
								appLog.info(invest_pref_list.get(i)+" was successfully found on my profile page");
							}
							else {
								sa.assertTrue(false, invest_pref_list.get(i)+" was not found");
							}
						}
					}
					else {
						sa.assertTrue(false, "Login info label text list is empty");
					}

					List<String> heads_string = new ArrayList<String>();
					heads_string.addAll(Arrays.asList(heads_str) );
					if (!ip.getSubHeads().isEmpty()) {


						for(int i = 0;i<size_subheads;i++) {
							WebElement temp_element = ip.getSubHeads().get(i);
							if (temp_element.getText().trim().equals(heads_string.get(i))) {
								appLog.info(heads_string.get(i)+ " was successfully found on my profile page");
							}
							else {
								sa.assertTrue(false, heads_string.get(i)+ "was not found");
							}
						}
					}
					else
					{
						sa.assertTrue(false, "Sub heading list is empty");
					}


					if ((ip.getDailyDigestEmailText(60)!=null) && (ip.getNoEmailText(60)!=null)) {
						if (ip.getDailyDigestEmailText(60).getText().trim().equals("Daily Digest Email")) {
							if (ip.getNoEmailText(60).getText().trim().equals("No Email")) {

								appLog.info("daily digest and no email option of notification preferences are successfully displayed");
							}
							else {
								sa.assertTrue(false, "no email text is not visible on my profile");
							}
						}
						else {
							sa.assertTrue(false, "Daily digest text is not visible on my profile");
						}
					}
					else {
						sa.assertTrue(false, "daily digest and no email are not visible on my profile page");
					}
					//keep first name and last name empty and click on cancel button
					ThreadSleep(5000);
					if (click(driver, ip.getEditIcon(60), "edit icon on my profile page", action.SCROLLANDBOOLEAN)) {
						int size_edit_mode_basic_info_list = ip.getEditModeBasicInfoList().size();
						List<String> edit_mode_string_list = new ArrayList<String>();
						edit_mode_string_list.addAll( Arrays.asList(editmode_basic_table) );

						if (!ip.getEditModeBasicInfoList().isEmpty()) {
							for (int i = 0;i<size_edit_mode_basic_info_list;i++) {
								WebElement temp_element = ip.getEditModeBasicInfoList().get(i);

								if (temp_element.getText().contains(edit_mode_string_list.get(i))){
									appLog.info(edit_mode_string_list.get(i)+" was successfully found on my profile page");
								}
								else {
									sa.assertTrue(false, edit_mode_string_list.get(i)+" was not found on page");
								}
							}
						}
						else {
							sa.assertTrue(false, "list of basic info labels is empty, no labels were found on page");
						}
						if (sendKeys(driver, ip.getFirstNameTextBox(60), "", "First name textbox edit mode my profile page", action.SCROLLANDBOOLEAN)) {
							if (sendKeys(driver, ip.getLastNameTextBox(60), "", "Last name textbox edit mode my profile page", action.SCROLLANDBOOLEAN)) {
								if (click(driver, ip.getCancelButtonMyProfilePage(60), "cancel button edit mode my profile page", action.SCROLLANDBOOLEAN)) {
									WebElement complete_name = isDisplayed(driver, FindElement(driver, "//div[contains(text(),'"+ M2Contact1First+" "+M2Contact1Last+"')]", "complete name of user", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "complete name of user");
									//WebElement complete_name = isDisplayed(driver, FindElement(driver, "//div[contains(text(),'avi avinash25jul')]", "complete name of user", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "complete name of user");
									if (complete_name!=null) {
										appLog.info("The correct name of user is being displayed on my profiles page");
									}
									else {
										sa.assertTrue(false, "complete name of user is not being displayed");
									}
								}
								else {
									sa.assertTrue(false, "cancel button is not clickable on my profile page");
								}
							}
							else {
								sa.assertTrue(false, "last name text box is not visible on my profile page");
							}
						}
						else {
							sa.assertTrue(false, "First name textbox is not visible on my profle page");
						}
					}
					//keep first name and last name empty and click on save button to check error
					if (click(driver, ip.getEditIcon(60), "edit icon on my profile page", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getFirstNameTextBox(60), "", "First name textbox edit mode my profile page", action.SCROLLANDBOOLEAN)) {
							if (sendKeys(driver, ip.getLastNameTextBox(60), "", "Last name textbox edit mode my profile page", action.SCROLLANDBOOLEAN)) {
								if (click(driver, ip.getSaveButtonMyProfilePage(60), "save button edit mode my profile page", action.SCROLLANDBOOLEAN)) {


									if ((ip.getFirstNameTextboxError(60)!=null) && (ip.getLastNameTextboxError(60)!=null)) {
										if (ip.getFirstNameTextboxError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterRequiredFields)) {
											if (ip.getLastNameTextboxError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterRequiredFields)) {
												appLog.info("Error message that first name and last name necessary fields should be entered is successfully displayed");
											}

											else {
												sa.assertTrue(false, "Last name error is not correctly displayed");
											}
										}else {
											sa.assertTrue(false, "First name error is not correctly displayed");
										}

									}
									else {
										sa.assertTrue(false, "first name error and last name error messages were not displayed on my profile page");
									}
								}
								else {
									sa.assertTrue(false, "save button on my profile page is not clickable");
								}
							}
							else {
								sa.assertTrue(false, "last name textbox is not visible on my profile page");
							}
						}
						else {
							sa.assertTrue(false, "first name textbox is not visiible on my profile page");
						}
						if (sendKeys(driver, ip.getFirstNameTextBox(60), M2Contact1First, "First name textbox edit mode my profile page", action.SCROLLANDBOOLEAN)) {
							if (sendKeys(driver, ip.getLastNameTextBox(60), M2Contact1Last, "Last name textbox edit mode my profile page", action.SCROLLANDBOOLEAN)) {
								if (click(driver, ip.getSaveButtonMyProfilePage(60), "save button edit mode my profile page", action.SCROLLANDBOOLEAN)) {
									WebElement complete_name = isDisplayed(driver, FindElement(driver, "//div[contains(text(),'"+ M2Contact1First+" "+M2Contact1Last+"')]", "complete name of user", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "complete name of user");
									//WebElement complete_name = isDisplayed(driver, FindElement(driver, "//div[contains(text(),'avi avinash25jul')]", "complete name of user", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "complete name of user");
									if (complete_name!=null) {
										appLog.info("The correct name of user is being displayed on my profiles page");
									}
									else {
										sa.assertTrue(false, "the correct name of user is not displayed on my profiles page after clicking save button");
									}
								}
								else {
									sa.assertTrue(false, "save button is not clickable on my profiles page");
								}
							}
							else {
								sa.assertTrue(false, "last name text box is not visible on my profile page");
							}
						}
						else {


							sa.assertTrue(false, "first name textbox is not visible on my profile page");
						}
					}
					if (click(driver, ip.getEmailUrl(60), "email url on my profile page", action.SCROLLANDBOOLEAN)) {
						if (!new NIMPageBusinessLayer(driver).verifyNavatarSalesTeamLinkFunctionality("ContactEmailLink")) {
							   appLog.error("Verification of Contact Email link is unsuccessfull.");
							   sa.assertTrue(false, "Verification of Contact Email link is unsuccessfull.");
							  } else {
							   appLog.info("Verification of Contact Email Link is successfully");
							  }
					}
				}
				else {
					sa.assertTrue(false, "Selected element in Firm name dropdown is not null on my profile page");
				}
			}
			else {
				sa.assertTrue(false, "profile link is not clickable on all firms page");
			}

			}
		
		else {
			sa.assertTrue(false, "Investor password is wrong so cannot login");
		}
		lp.investorLogout();
		
		sa.assertAll();
		
	}
	
	@Test
	public void M2tc018_ChangeProfilePicture() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String parentID;
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String cont_password_updated = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_password_updated);
		driver.get(InvestorURL);
		lp.investorLogin( M2Contact1EmailID,cont_password_updated);
		if (click(driver, af.getProfileLink(60), "profile link on all firms page", action.SCROLLANDBOOLEAN)) {
			if (click(driver, ip.getChangeProfilePictureLink(60), "change profile picture link", action.SCROLLANDBOOLEAN)) {
				parentID=switchOnWindow(driver);


				if (ip.getChangeProfilePictureText(60)!=null) {
					if (ip.getChangeProfilePictureText(60).getText().trim().equals("Change Profile Picture")) {
						appLog.info("textfound"+ip.getChangeProfilePictureText(60)+"Correct text change profile picture is displayed");
					}
					else {
						sa.assertTrue(false, "Change profile picture text is not correctly dispalyed");
					}
				}
				else {
					sa.assertTrue(false, "change profile picture text is not visible");
				}
				if (ip.getSubmitButtonChangePicture(60)!=null) {
					appLog.info("Submit button is successfully displayed");
					if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button", action.SCROLLANDBOOLEAN)) {
						if (ip.getSpecifyFileToUploadError(60)!=null) {
							if (ip.getSpecifyFileToUploadError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseSpecifyFileToUpload)) {
								appLog.info("Please specify file to upload error message is successfully displayed");
							}
						}
						else {
							sa.assertTrue(false, "Error message specify file to upload could not be displayed");
						}
						if (sendKeys(driver, ip.getBrowseButton(60), System.getProperty("user.dir")+"\\UploadFiles\\greaterthantwomb.png" , "browse button change profile picture", action.BOOLEAN)) {
							if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change profile button", action.BOOLEAN)) {
								if (ip.getSizeGreaterUploadError(120)!=null ){
									if (ip.getSizeGreaterUploadError(60).getText().trim().equals(InvestorProfileErrorsPage.sizeGreaterError)) {
										appLog.info("Unable to upload error message due to size greater than 2mb error messag successfully displayed");
									}
									else {
										sa.assertTrue(false, "Text unable to upload error is not displayed correctly");
									}
								}
								else {
									sa.assertTrue(false, "Text unable to uploaded error is not visible on change picture window");
								}
							}
							else {
								sa.assertTrue(false, "Submit button is not clickable on change profile window");
							}
						}
						else {
							sa.assertTrue(false, "file could not be sent to browse button on change picture window");
						}
						if (sendKeys(driver, ip.getBrowseButton(60), System.getProperty("user.dir")+"\\UploadFiles\\lessthantwomb.png", "browse button change profile picture",action.BOOLEAN)) {
							if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change picture window", action.BOOLEAN)) {
								if(bp.clickUsingCssSelectorPath("a[title='Save']", "change profile save button")) {
//								if (click(driver, ip.getChangeProfileSaveButton(100), "save button", action.BOOLEAN)) {
									ThreadSleep(5000);
									if (isAlertPresent(driver)) {
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if (msg.trim().equals(InvestorProfileErrorsPage.changeProfilePictureQuestion)) {
											appLog.info("alert to ask if want to change profile picture is successfully verified");

										}
									}else {
										sa.assertTrue(false,"alert to ask if want to change profile picture is not present");

									}

									driver.switchTo().window(parentID);
									driver.navigate().refresh();
								}
								else {
									sa.assertTrue(false, "save button on change profile window is not clickable");
								}
							}
							else {
								sa.assertTrue(false, "submit btuton on change profile window is not clickable");
							}
						}
						else {
							sa.assertTrue(false, "cannot send path to browse button");
						}

					}
					else {
						sa.assertTrue(false, "submit button is not clickable");
					}
				}
				else {
					sa.assertTrue(false, "submit button is not displayed");
				}
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M2tc019_VerifyVariousFormatChangePictureMyProfile() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String parentID;
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String cont_password_updated = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_password_updated);
		driver.get(InvestorURL);
		lp.investorLogin( M2Contact1EmailID,cont_password_updated);
		if (click(driver, af.getProfileLink(60), "profile link on all firms page", action.SCROLLANDBOOLEAN)) {
			if (click(driver, ip.getChangeProfilePictureLink(60), "change profile picture link", action.SCROLLANDBOOLEAN)) {
				parentID=switchOnWindow(driver);

				//uploading bmp image(successful)
				if (sendKeys(driver, ip.getBrowseButton(60), System.getProperty("user.dir")+"\\UploadFiles\\logoSupportFormatImage\\bmpimage.bmp", "browse button change profile picture",action.BOOLEAN)) {
					if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change picture window", action.BOOLEAN)) {
						if(bp.clickUsingCssSelectorPath("a[title='Save']", "change profile save button")) {
//						if (click(driver, ip.getChangeProfileSaveButton(100), "save button", action.BOOLEAN)) {
							ThreadSleep(5000);
							if (isAlertPresent(driver)) {
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								if (msg.trim().equals(InvestorProfileErrorsPage.changeProfilePictureQuestion)) {
									appLog.info("alert to ask if want to change profile picture is successfully verified");

								}
							}else {
								sa.assertTrue(false,"alert to ask if want to change profile picture is not present");

							}
						}
						else {
							sa.assertTrue(false, "change profile save button is not clickable");
						}
					}
					else {
						sa.assertTrue(false, "submit button on change picture window is not clickable");
					}
				}
				else {
					sa.assertTrue(false, "browse button on change profile picture window is not clickable");
				}
				driver.close();
				driver.switchTo().window(parentID);
				driver.navigate().refresh();



			}
			else {
				sa.assertTrue(false, "change profile picture link on my profile page is not clickable");
			}



			if (click(driver, ip.getChangeProfilePictureLink(60), "change profile picture link", action.SCROLLANDBOOLEAN)) {
				parentID=switchOnWindow(driver);

				//uploading jpg image(successful)
				if (sendKeys(driver, ip.getBrowseButton(60), System.getProperty("user.dir")+"\\UploadFiles\\logoSupportFormatImage\\jpgimage.jpg", "browse button change profile picture",action.BOOLEAN)) {
					if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change picture window", action.BOOLEAN)) {
						if(bp.clickUsingCssSelectorPath("a[title='Save']", "change profile save button")) {
//						if (click(driver, ip.getChangeProfileSaveButton(100), "save button", action.BOOLEAN)) {
							ThreadSleep(5000);
							if (isAlertPresent(driver)) {
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								if (msg.trim().equals(InvestorProfileErrorsPage.changeProfilePictureQuestion)) {
									appLog.info("alert to ask if want to change profile picture is successfully verified");

								}
							}else {
								sa.assertTrue(false,"alert to ask if want to change profile picture is not present");
							}
						}
						else {
							sa.assertTrue(false, "change profile save button is not clickable");
						}
					}
					else {
						sa.assertTrue(false, "submit button on change picture window is not clickable");
					}
				}
				else {
					sa.assertTrue(false, "browse button on change profile picture window is not clickable");
				}
				driver.close();
				driver.switchTo().window(parentID);
				driver.navigate().refresh();



			}




			if (click(driver, ip.getChangeProfilePictureLink(60), "change profile picture link", action.SCROLLANDBOOLEAN)) {
				parentID=switchOnWindow(driver);

				//uploading gif image(successful)
				if (sendKeys(driver, ip.getBrowseButton(60), System.getProperty("user.dir")+"\\UploadFiles\\logoSupportFormatImage\\gifimage.gif", "browse button change profile picture",action.BOOLEAN)) {
					if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change picture window", action.BOOLEAN)) {
						if(bp.clickUsingCssSelectorPath("a[title='Save']", "change profile save button")) {
//						if (click(driver, ip.getChangeProfileSaveButton(100), "save button", action.BOOLEAN)) {
							ThreadSleep(5000);
							if (isAlertPresent(driver)) {
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								if (msg.trim().equals(InvestorProfileErrorsPage.changeProfilePictureQuestion)) {
									appLog.info("alert to ask if want to change profile picture is successfully verified");

								}
							}else {
								sa.assertTrue(false,"alert to ask if want to change profile picture is not present");
							}
						}
						else {
							sa.assertTrue(false, "change profile save button is not clickable");
						}
					}
					else {
						sa.assertTrue(false, "submit button on change picture window is not clickable");
					}
				}
				else {
					sa.assertTrue(false, "browse button on change profile picture window is not clickable");
				}
				driver.close();
				driver.switchTo().window(parentID);
				driver.navigate().refresh();


			}
			if (click(driver, ip.getChangeProfilePictureLink(60), "change profile picture link", action.SCROLLANDBOOLEAN)) {
				parentID=switchOnWindow(driver);

				//uploading png image(successful)
				if (sendKeys(driver, ip.getBrowseButton(60), System.getProperty("user.dir")+"\\UploadFiles\\logoSupportFormatImage\\pngimage.png", "browse button change profile picture",action.BOOLEAN)) {
					if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change picture window", action.BOOLEAN)) {
						if(bp.clickUsingCssSelectorPath("a[title='Save']", "change profile save button")) {
//						if (click(driver, ip.getChangeProfileSaveButton(100), "save button", action.BOOLEAN)) {
							ThreadSleep(5000);
							if (isAlertPresent(driver)) {
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								if (msg.trim().equals(InvestorProfileErrorsPage.changeProfilePictureQuestion)) {
									appLog.info("alert to ask if want to change profile picture is successfully verified");

								}
							}else {
								sa.assertTrue(false,"alert to ask if want to change profile picture is not present");
							}
						}
						else {
							sa.assertTrue(false, "change profile save button is not clickable");
						}
					}
					else {
						sa.assertTrue(false, "submit button on change picture window is not clickable");
					}
				}
				else {
					sa.assertTrue(false, "browse button on change profile picture window is not clickable");
				}
				driver.close();
				driver.switchTo().window(parentID);
				driver.navigate().refresh();
			}
			if (click(driver, ip.getChangeProfilePictureLink(60), "change profile picture link", action.SCROLLANDBOOLEAN)) {
				parentID=switchOnWindow(driver);
				//uploading pdf file(unsuccessful)
				if (sendKeys(driver, ip.getBrowseButton(60), System.getProperty("user.dir")+"\\UploadFiles\\OtherFormatFile\\Target1.pdf", "browse button change profile picture",action.BOOLEAN)) {
					if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change picture window", action.BOOLEAN)) {

						if (ip.getSizeGreaterUploadError(60)!=null) {
							if (ip.getSizeGreaterUploadError(60).getText().trim().equals(InvestorProfileErrorsPage.fileTypeNotCorrect)) {
								appLog.info("file type not correct error messaage found successfully");
							}
							else {
								sa.assertTrue(false, "error message of wrong file type is not correct");
							}
						}
						else {
							sa.assertTrue(false, "error message text is not displayed");
						}
					}
					else {
						sa.assertTrue(false, "submit button is not clickable on change picture window");
					}
				}
				else {
					sa.assertTrue(false, "image path could not be sent to browse button in change picture window");
				}
				driver.close();
				driver.switchTo().window(parentID);
				driver.navigate().refresh();
			}
			if (click(driver, ip.getChangeProfilePictureLink(60), "change profile picture link", action.SCROLLANDBOOLEAN)) {
				parentID=switchOnWindow(driver);
				//uploading doc file(unsuccessful)
				if (sendKeys(driver, ip.getBrowseButton(60), System.getProperty("user.dir")+"\\UploadFiles\\OtherFormatFile\\docfile.docx", "browse button change profile picture",action.BOOLEAN)) {
					if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change picture window", action.BOOLEAN)) {

						if (ip.getSizeGreaterUploadError(60)!=null) {
							if (ip.getSizeGreaterUploadError(60).getText().trim().equals(InvestorProfileErrorsPage.fileTypeNotCorrect)) {
								appLog.info("file type not correct error messaage found successfully");
							}
							else {
								sa.assertTrue(false, "error message of wrong file type is not correct");
							}
						}
						else {
							sa.assertTrue(false, "error message text is not displayed");
						}
					}
					else {
						sa.assertTrue(false, "submit button is not clickable on change picture window");
					}
				}
				else {
					sa.assertTrue(false, "image path could not be sent to browse button in change picture window");
				}
				driver.close();
				driver.switchTo().window(parentID);
				driver.navigate().refresh();
			}

			if (click(driver, ip.getChangeProfilePictureLink(60), "change profile picture link", action.SCROLLANDBOOLEAN)) {
				parentID=switchOnWindow(driver);

				//uploading xls file(unsuccessful)
				if (sendKeys(driver, ip.getBrowseButton(60), System.getProperty("user.dir")+"\\UploadFiles\\OtherFormatFile\\excelfile.xlsx", "browse button change profile picture",action.BOOLEAN)) {
					if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change picture window", action.BOOLEAN)) {

						if (ip.getSizeGreaterUploadError(60)!=null) {
							if (ip.getSizeGreaterUploadError(60).getText().trim().equals(InvestorProfileErrorsPage.fileTypeNotCorrect)) {
								appLog.info("file type not correct error messaage found successfully");
							}
							else {
								sa.assertTrue(false, "error message of wrong file type is not correct");
							}
						}
						else {
							sa.assertTrue(false, "error message text is not displayed");
						}
					}
					else {
						sa.assertTrue(false, "submit button is not clickable on change picture window");
					}
				}
				else {
					sa.assertTrue(false, "image path could not be sent to browse button in change picture window");
				}
				
				driver.switchTo().window(parentID);
				driver.navigate().refresh();
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M2tc020_ChangePassword() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String cont_password_updated = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_password_updated);
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		driver.get(InvestorURL);
		lp.investorLogin( M2Contact1EmailID,cont_password_updated);
		SoftAssert sa = new SoftAssert();
		if (click(driver, af.getProfileLink(60), "profile link all firms page", action.SCROLLANDBOOLEAN)) {
			if (click(driver,ip.getChangePasswordLink(60) , "change password url on my profile page", action.SCROLLANDBOOLEAN)) {
			if (ip.getChangePasswordHeading(60).getText().trim().equals("Change Password")) {
				appLog.info("change password heading is succesfully found");
			}
			else {
				sa.assertTrue(false, "Change password heading is not correct");
			}
			if (ip.getOldPasswordText(60).getText().contains("Old Password:")) {
				if (ip.getOldPasswordTextbox(60).getText().isEmpty()) {
					appLog.info("old password text is successfully verified, and its textbox is empty");
				}
				else {
					sa.assertTrue(false, "old password textbox is not empty");
				}
			}
			else {
				sa.assertTrue(false, "text present in old password textbox is wrong");
			}
			if (ip.getNewPasswordText(60).getText().contains("New Password:")) {
				if (ip.getNewPasswordTextbox(60).getText().isEmpty()) {
					appLog.info("new password text is successfully verified, and its textbox is empty");
				}
				else {
					sa.assertTrue(false, "new password textbox is not empty");
				}
			}
			else {
				sa.assertTrue(false, "new password text label contains wrong data");
			}
			if (ip.getConfirmPasswordText(60).getText().contains("Confirm Password:")) {
				if (ip.getConfirmPasswordTextbox(60).getText().isEmpty()) {
					appLog.info("confirm password text is successfully verified, and its textbox is empty");
				}
				else {
					sa.assertTrue(false, "confirm password textbox is not empty");
				}
			}
			else {
				sa.assertTrue(false, "confirm password text label does not contain correct text");
			}
			
			
			
			if (click(driver, ip.getSaveButtonChangePassword(60), "save button on change password window", action.SCROLLANDBOOLEAN)) {
				if (ip.getOldPasswordTextboxError(60).getText().contains(InvestorProfileErrorsPage.pleaseEnterRequiredFieldsChangePassword)) {
					appLog.info("Plase enter the required fields is displayed successfully below old password textbox");
				}
				else {
					sa.assertTrue(false, "Error message below old password textbox is wrong");
				}
				if (ip.getNewPasswordTextboxError(60).getText().contains(InvestorProfileErrorsPage.pleaseEnterRequiredFieldsChangePassword)) {
					appLog.info("Please enter the required fields is displayed succeesfully below new password texbox");
				}
				else {
					sa.assertTrue(false, "Error message below new password textbox is wrong");
				}
				if (ip.getConfirmPasswordTextboxError(60).getText().contains(InvestorProfileErrorsPage.pleaseEnterRequiredFieldsChangePassword)) {
					appLog.info("Please enter the required fields is displayed succesffully below confirm password textbox");
				}
				else {
					sa.assertTrue(false, "Error message below confirm password textbox is wrong");
				}
				
				
				if (sendKeys(driver, ip.getOldPasswordTextbox(60), adminPassword, "Old pasword textbox", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, ip.getNewPasswordTextbox(60), adminPassword+"1", "new password textbox", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getConfirmPasswordTextbox(60), adminPassword+"1", "confirm password textbox", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonChangePassword(60), "save button on change password window", action.SCROLLANDBOOLEAN)) {
								if (ip.getCommonErrorChangePassword(60).getText().trim().equals(InvestorProfileErrorsPage.oldPasswordInvalid)) {
									appLog.info("Your old password is invalid message is displayed sucessfully");
								}
								else {
									sa.assertTrue(false, "'invalid old password error message' is wrong");
								}
							}
							else {
								sa.assertTrue(false, "Save button change password window is not clcikable");
							}
						}
						else {
							sa.assertTrue(false, "Confirm password textbox is not visible on change password window");
						}
					}
					else {
						sa.assertTrue(false, "New password textbox is not visible on change password window");
					}
					
					//only digits as password
					if (sendKeys(driver, ip.getOldPasswordTextbox(60), cont_password_updated,"old password textbox", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, ip.getNewPasswordTextbox(60), "12345678", "new password textbox", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, ip.getConfirmPasswordTextbox(60), "12345678", "confirm password textbox", action.SCROLLANDBOOLEAN)) {
						if (click(driver, ip.getSaveButtonChangePassword(60), "save button on confirm password window", action.SCROLLANDBOOLEAN)) {
							if (ip.getCommonErrorChangePassword(60).getText().trim().equals(InvestorProfileErrorsPage.passwordMustHaveLettersAndNumbers)) {
								appLog.info("error message for mix of letters and numbers is displayed successfully");
							}
							else {
								sa.assertTrue(false, "error message for all letters is not correct");
							}
						}
						else {
							sa.assertTrue(false, "save button is not clickable in change password window");
						}
					}
					else {
						sa.assertTrue(false, "confirm password textbox is not visible on change password window");
					}
					}
					else {
						sa.assertTrue(false, "new password textbox is not visible on change password window");
					}
					}
					else {
						sa.assertTrue(false, "old pasword textbox is not visible on change password windows");
					}
					
					
					
					if (sendKeys(driver, ip.getOldPasswordTextbox(60), cont_password_updated,"old password textbox", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getNewPasswordTextbox(60),adminPassword, "new password textbox", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getConfirmPasswordTextbox(60), adminPassword+"5", "confirm password textbox", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonChangePassword(60), "save button on confirm password window", action.SCROLLANDBOOLEAN)) {
								if (ip.getCommonErrorChangePassword(60).getText().trim().equals(InvestorProfileErrorsPage.passwordsDoNotMatch)) {
									appLog.info("error message for different values of password is displayed successfully");
								}
								else {
									sa.assertTrue(false, "error message for different values of password entered, is not correct");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable in change password window");
							}
						}
						else {
							sa.assertTrue(false, "confirm password textbox is not visible on change password window");
						}
						}
						else {
							sa.assertTrue(false, "new password textbox is not visible on change password window");
						}
						}
						else {
							sa.assertTrue(false, "old pasword textbox is not visible on change password windows");
						}
					
					if (sendKeys(driver, ip.getOldPasswordTextbox(60),cont_password_updated,"old password textbox", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getNewPasswordTextbox(60), adminPassword+"UP", "new password textbox", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getConfirmPasswordTextbox(60),adminPassword+"UP", "confirm password textbox", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getCancelButtonChangePassword(60), "cancel button on confirm password window", action.SCROLLANDBOOLEAN)) {
								ThreadSleep(5000);
									if (click(driver, ip.getChangePasswordLink(60), "change password link on my profile page", action.BOOLEAN)) {
										if (ip.getOldPasswordTextbox(60).getText().isEmpty() && ip.getNewPasswordTextbox(60).getText().isEmpty() && (ip.getConfirmPasswordTextbox(60).getText().isEmpty())) {
											appLog.info("old password, new password and confirm password textboxes are empty after clicking cancel button");
										}
									}
									else {
										sa.assertTrue(false, "change password link on my profile page is not clcikable");
									}
								
							}
							else {
								sa.assertTrue(false, "cancel button is not clickable in change password window");
							}
						}
						else {
							sa.assertTrue(false, "confirm password textbox is not visible on change password window");
						}
						}
						else {
							sa.assertTrue(false, "new password textbox is not visible on change password window");
						}
						}
						else {
							sa.assertTrue(false, "old pasword textbox is not visible on change password windows");
						}
					
					if (sendKeys(driver, ip.getOldPasswordTextbox(60), cont_password_updated,"old password textbox", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getNewPasswordTextbox(60),adminPassword+"UP", "new password textbox", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getConfirmPasswordTextbox(60),adminPassword+"UP", "confirm password textbox", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getCrossIcon(60), "cross icon on confirm password window", action.SCROLLANDBOOLEAN)) {
								ThreadSleep(5000);
									if (click(driver, ip.getChangePasswordLink(60), "change password link on my profile page", action.BOOLEAN)) {
										if (ip.getOldPasswordTextbox(60).getText().isEmpty() && ip.getNewPasswordTextbox(60).getText().isEmpty() && (ip.getConfirmPasswordTextbox(60).getText().isEmpty())) {
											appLog.info("old password, new password and confirm password textboxes are empty after clicking cross icon");
										}
									}
									else {
										sa.assertTrue(false, "change password link on my profile page is not clcikable");
									}
								
							}
							else {
								sa.assertTrue(false, "cross icon is not clickable in change password window");
							}
						}
						else {
							sa.assertTrue(false, "confirm password textbox is not visible on change password window");
						}
						}
						else {
							sa.assertTrue(false, "new password textbox is not visible on change password window");
						}
						}
						else {
							sa.assertTrue(false, "old pasword textbox is not visible on change password windows");
						}
					
					if (sendKeys(driver, ip.getOldPasswordTextbox(60), cont_password_updated,"old password textbox", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getNewPasswordTextbox(60), adminPassword+"UP", "new password textbox", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getConfirmPasswordTextbox(60), adminPassword+"UP", "confirm password textbox", action.SCROLLANDBOOLEAN)) {
							if(bp.clickUsingCssSelectorPath("div.ChangePassword_fancybox.FancyboxContainer a[title='Save']", "change password save button")) {
//							if (click(driver, ip.getSaveButtonChangePassword(60), "save button on confirm password window", action.SCROLLANDBOOLEAN)) {
									ThreadSleep(5000);
									ExcelUtils.writeData(adminPassword+"UP", "Contacts", excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_password_updated);
									if (isAlertPresent(driver)) {
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if (msg.trim().equals("Password changed successfully!")) {
											appLog.info("alert password changed is successfully verified");
											lp.investorLogout();
											driver.close();
											config(ExcelUtils.readDataFromPropertyFile("Browser"));
											lp = new LoginPageBusinessLayer(driver);
											lp.investorLogin( M2Contact1EmailID, adminPassword+"UP");
											if (matchTitle(driver, "Firm Alerts", 60)) {
												appLog.info("Contact successfully logged in with changed password");
											}
											else {
												sa.assertTrue(false, "contact could not log in");
											}
										}
										else {
											sa.assertTrue(false, "password changing alert does not have correct text");
										}
									}
									else {
										sa.assertTrue(false, "alert for changing password was not found");
									}
							}
							else {
								sa.assertTrue(false, "cancel button is not clickable in change password window");
							}
						}
						else {
							sa.assertTrue(false, "confirm password textbox is not visible on change password window");
						}
						}
						else {
							sa.assertTrue(false, "new password textbox is not visible on change password window");
						}
						}
						else {
							sa.assertTrue(false, "old pasword textbox is not visible on change password windows");
						}
				}
				else {
					sa.assertTrue(false, "Old passwod textbox is not visible on change password window");
				}
			}
			else {
				sa.assertTrue(false, "save button in change password window is not clcikable");
			}
			}
			else {
				sa.assertTrue(false, "change password link on my profile page is not clickable");
			}
		}
		else {
			sa.assertTrue(false, "profile link on all firms page is not clcikable");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Test
	public void M2tc021_ChangeNotificationPreferences() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String parentID;
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String cont_password_updated = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_password_updated);
		driver.get(InvestorURL);
		String new_first_name=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.ContactNew_fName);
		String new_last_name=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.ContactNew_lName);
		String title=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_Title);
		String phone=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_Phone);
		String facebook_link=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Facebook);
		String mailing_street=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Mailing_Street);
		String linkedin_link=ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Linkedin);
		
		lp.investorLogin( M2Contact1EmailID,cont_password_updated);
		if (click(driver, af.getProfileLink(60), "profile link on all firms page", action.SCROLLANDBOOLEAN)) {
			if (click(driver,ip.getEditIcon(60) , "edit icon on my profile page", action.SCROLLANDBOOLEAN)) {
				if (sendKeys(driver, ip.getFirstNameTextBox(60), new_first_name, "first name textbox on my profile page", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, ip.getLastNameTextBox(60),new_last_name , "Last name textbox on my profile page", action.SCROLLANDBOOLEAN)) {
						if (click(driver, ip.getNoEmailRadiobuttonEditMode(60), "no email radio button", action.SCROLLANDBOOLEAN)) {
							if (sendKeys(driver, ip.getTitleTextbox(60), title, "title textbox on my profile page", action.SCROLLANDBOOLEAN)) {
								if (sendKeys(driver, ip.getPhoneTextbox(60), phone, "phone textbox on my profile page", action.SCROLLANDBOOLEAN)) {
									if (sendKeys(driver, ip.getFacebookTextbox(60),facebook_link , "facebook textbox my profile page", action.SCROLLANDBOOLEAN)) {
										if (sendKeys(driver, ip.getMailingStreetTextbox(60),mailing_street, "mailing street textbox", action.SCROLLANDBOOLEAN)) {
											if (click(driver, ip.getCancelButtonMyProfilePage(60), "Cancel button my profile page", action.SCROLLANDBOOLEAN)) {
												if (ip.getTitleValue(60)==null) {
													appLog.info("value of title text was empty earlier and is still empty");
												}
												else {
													if (!ip.getTitleValue(60).getText().trim().equals(title)) {
														appLog.info("title is not updated after cancel button");
													}
													else {
														sa.assertTrue(false, "title is updated after cancel button");
													}
												}
												if (ip.getPhoneValue(60)==null) {
													appLog.info("value of phone text was empty earlier and is still empty");
												}
												else {
												if (!ip.getPhoneValue(60).getText().trim().equals(phone)) {
													appLog.info("phone is not updated after cancel button");
												}
												else {
													sa.assertTrue(false, "phone is updated after cancel button");
													
													
												}
												}
												if (ip.getAddressValue(60)==null) {
													appLog.info("value of address was empty earlier and is still empty after cancel button");
												}
												else {
												if (!ip.getAddressValue(60).getText().trim().equals(mailing_street)) {
													appLog.info("address is not updated after cancel button");
												}
												else {
													sa.assertTrue(false, "address is updated after cancel button");
												}
												}
												
												if (ip.getFacebookValue(60)==null)
												{
												appLog.info("facebook value was empty earlier and is still empty after cancel button");
												}
												else {
												if (!ip.getFacebookValue(60).getText().trim().equals("http://"+facebook_link)) {
													appLog.info("facebook link is not updated after cancel button");
												}
												else {
													sa.assertTrue(false, "facebook link is updated after cancel button");
												}
												}
												
												if (!ip.getNoEmailRadioBtnView(60).isSelected()) {
													appLog.info("no email radio button is not selected after cancel button");
												}
												else {
													sa.assertTrue(false, "no email radio button got selected after clicking cancel button");
												}
											}
											else {
												sa.assertTrue(false, "Cancel button is not clickable in my profile page");
											}
										}
										else {
											sa.assertTrue(false, "mailing street textbox is not visible on my profile page");
										}
									}
									else {
										sa.assertTrue(false, "facebook textbox is not visible on my profile page");
									}
								}
								else {
									sa.assertTrue(false, "phone textbox is not visible on my profile page");
								}
							}
							else {
								sa.assertTrue(false, "title textbox is not visible on my profile page");
							}
						}
						else {
							sa.assertTrue(false, "no email radio button is not clickable on my profile page");
						}
					}
					else {
						sa.assertTrue(false, "last name textbox is not visible on my profile page");
					}
				}
				else {
					sa.assertTrue(false, "first name textbox is not visible on my profile page");
				}
			}
			else {
				sa.assertTrue(false, "edit icon is not clickable on my profile page");
			}
			
			
			if (click(driver,ip.getEditIcon(60) , "edit icon on my profile page", action.SCROLLANDBOOLEAN)) {
				if (sendKeys(driver, ip.getFirstNameTextBox(60), new_first_name, "first name textbox on my profile page", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, ip.getLastNameTextBox(60),new_last_name , "Last name textbox on my profile page", action.SCROLLANDBOOLEAN)) {
						if (click(driver, ip.getNoEmailRadiobuttonEditMode(60), "no email radio button", action.SCROLLANDBOOLEAN)) {
							if (sendKeys(driver, ip.getTitleTextbox(60), title, "title textbox on my profile page", action.SCROLLANDBOOLEAN)) {
								if (sendKeys(driver, ip.getPhoneTextbox(60), phone, "phone textbox on my profile page", action.SCROLLANDBOOLEAN)) {
									if (sendKeys(driver, ip.getFacebookTextbox(60), facebook_link, "facebook textbox my profile page", action.SCROLLANDBOOLEAN)) {
										if (sendKeys(driver, ip.getLinkedinTextbox(60), linkedin_link, "linkedin textbox on my profile page", action.SCROLLANDBOOLEAN)) {
										if (sendKeys(driver, ip.getMailingStreetTextbox(60), mailing_street, "mailing street textbox", action.SCROLLANDBOOLEAN)) {
											if (click(driver, ip.getSaveButtonMyProfilePage(60), "Save button on my profile page", action.SCROLLANDBOOLEAN)) {
												if (ip.getTitleValue(60).getText().trim().equals("Founder")) {
													appLog.info("title is successfully updated after save button");
												}
												else {
													sa.assertTrue(false, "title is not updated after save button");
												}
												if (ip.getPhoneValue(60).getText().trim().equals(phone)) {
													appLog.info("phone is updated successfully after clicking save button");
												}
												else {
													sa.assertTrue(false, "phone is not updated after save button");
												}
												if (ip.getAddressValue(60).getText().trim().equals(mailing_street)) {
													appLog.info("address is updated successfully after clicking save button");
												}
												else {
													sa.assertTrue(false, "address is not updated after save button");
												}
												if (ip.getFacebookValue(60).getText().trim().equals("http://"+facebook_link)) {
													appLog.info("facebook link is updated successfully after clicking save button");
												}
												else {
													sa.assertTrue(false, "facebook link is not updated after save button");
												}
												if (ip.getLinkedinValue(60).getText().trim().equals("http://"+linkedin_link)) {
													appLog.info("linkedin link is updated successfully after clicking save button");
												}
												else {
													sa.assertTrue(false, "linkedin link is not updated after save button");
												}
												
												if (ip.getNoEmailRadioBtnView(60).isSelected()) {
													appLog.info("no email radio button is selected successfully after save button");
												}
												else {
													sa.assertTrue(false, "no email radio button is not selected after clicking save button");
												}
											}
											else {
												sa.assertTrue(false, "save button is not clickable on my profile page");
											}
										}
										else {
											sa.assertTrue(false, "mailing street textbox is not visibile on my profile page");
										}
									}
										else {
											sa.assertTrue(false, "linkedin textbox is not visibile on my profile page");
										}
									}
									else {
										sa.assertTrue(false, "facebook textbox is not visibile on my profile page");
									}
								}
								else {
									sa.assertTrue(false, "phone textbox is not visibile on my profile page");
								}
							}
							else {
								sa.assertTrue(false, "title textbox is not visibile on my profile page");
							}
						}
						else {
							sa.assertTrue(false, "no email radio button is not clickable on my profile page");
						}
					}
					else {
						sa.assertTrue(false, "last name textbox is not visibile on my profile page");
					}
				}
				else {
					sa.assertTrue(false, "first name textbox is not visibile on my profile page");
				}
			}
			else {
				sa.assertTrue(false, "edit icon is not clickable on my profile page");
			}
			if (click(driver, ip.getEmailUrl(60), "email url of contact", action.SCROLLANDBOOLEAN)) {
				if (!new NIMPageBusinessLayer(driver).verifyNavatarSalesTeamLinkFunctionality("ContactEmailLink")) {
					   appLog.error("Verification of Contact Email link is unsuccessfull.");
					   sa.assertTrue(false, "Verification of Contact Email link is unsuccessfull.");
					  } else {
					   appLog.info("Verification of Contact Email Link is successfully");
					  }
			}
			if (click(driver, ip.getLinkedinValue(60), "linkedin profile link", action.SCROLLANDBOOLEAN)) {
				parentID = switchOnWindow(driver);
				ThreadSleep(5000);
				if (getURL(driver, 60).equals("https://"+linkedin_link)) {
					appLog.info("correct linkedin url was opened");
					driver.close();
					driver.switchTo().window(parentID);
				}
				else {
					sa.assertTrue(false, "linkedin url was not opened correctly");
				}
			}
			else {
				sa.assertTrue(false, "linkedin url was not clickable on my profile page");
			}
			if (click(driver, ip.getFacebookValue(60), "facebook link value on my profile page", action.SCROLLANDBOOLEAN)) {
				parentID = switchOnWindow(driver);
				ThreadSleep(5000);
				String tempHandle = driver.getWindowHandle();
				if (!tempHandle.equals(parentID)) {
				appLog.info("correct facebook profile url was opened");
				driver.close();
				driver.switchTo().window(parentID);
				}
				else {
					sa.assertTrue(false, "facebook url was not opened correctly");
				}
			}
			else {
				sa.assertTrue(false, "facebook profile url was not clickable on my profile page");
			}
		}
		else {
			sa.assertTrue(false, "profile link is not clickable on all firms page");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	@Test
	public void M2tc022_CheckUIMyFirmProfile() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		
		String cont_password_updated = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_password_updated);
		String[] basic_table_string = {"Firm Name:", "Industry:", "Address:", "Institution Type:","Description:"}; 
		String[] investment_pref_string = {"Type:","Min. Investment Size (mn):", "Industries:", "Max. Investment Size (mn):","Geographic Focus:","AUM (mn):"};
		String[] heads_str = {"Basic Information", "Investment Preferences"};
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		if (lp.investorLogin( M2Contact1EmailID, cont_password_updated)) {
			if (click(driver, af.getProfileLink(60), "profile link on investor firm page", action.SCROLLANDBOOLEAN)) {
				if (getSelectedOptionOfDropDown(driver, af.getFirmNameDropdown(60), "firm name dropdown investor firm page", "text").equals("")) {
						appLog.info("Firm name dropdown is succesfully verified as empty on my profile page");
						if (click(driver, ip.getMyFirmProfileTab(60), "my firm profile tab", action.BOOLEAN)) {
							if(ip.getMyFirmsProfileHeading(60).getText().trim().equals("My Firm's Profile")) {
							appLog.info("My firm's profile heading is successfully displayed");
						}
					
					List<String> basic_table = new ArrayList<String>();
					basic_table.addAll( Arrays.asList(basic_table_string) );

					if (!ip.getBasicInfoLabelTextList().isEmpty()) {
						for (int i = 0;i<5;i++) {
							WebElement temp_element = ip.getBasicInfoLabelTextList().get(i);
							if (temp_element.getText().trim().equals(basic_table.get(i))){
								appLog.info(basic_table.get(i)+" was successfully found on my profile page");
							}
							else {
								sa.assertTrue(false, basic_table.get(i)+" was not found on page");
							}
							
						}

					}
					else {
						sa.assertTrue(false, "Basic info label text list is empty");
					}
					List<String> invest_pref_list = new ArrayList<String>();
					invest_pref_list.addAll((Arrays.asList(investment_pref_string)) );
					if (!ip.getInvestmentPrefLabelListFirmProfile().isEmpty()) {

						for (int i = 0;i<6;i++) {
							WebElement temp_element = ip.getInvestmentPrefLabelListFirmProfile().get(i);
							if (temp_element.getText().trim().equals(invest_pref_list.get(i))) {
								appLog.info(invest_pref_list.get(i)+" was successfully found on my profile page");
							}
							else {
								sa.assertTrue(false, invest_pref_list.get(i)+" was not found");
							}
							
						}
					}
					else {
						sa.assertTrue(false, "Login info label text list is empty");
					}

					List<String> heads_string = new ArrayList<String>();
					heads_string.addAll(Arrays.asList(heads_str) );
					if (!ip.getSubHeads().isEmpty()) {
						for(int i = 0;i<2;i++) {
							
							WebElement temp_element = ip.getSubHeadsFirmProfile().get(i);
							if (temp_element.getText().trim().equals(heads_string.get(i))) {
								appLog.info(heads_string.get(i)+ " was successfully found on my profile page");
							}
							else {
								sa.assertTrue(false, heads_string.get(i)+ "was not found");
							}
						}
					}
					else
					{
						sa.assertTrue(false, "Sub heading list is empty");
					}
					if (ip.getWeWishToBeContactedYesViewModeRadiobtn(60).isSelected()) {
						appLog.info("we wish to be contacted radio button is selected as Yes by default");
					}
					else {
						sa.assertTrue(false, "we wish to be contacted radio button is selected as No by default");
					}
				}
					else {
						sa.assertTrue(false, "My firm profile tab is not clickable");
					}
				}
				else {
					sa.assertTrue(false, "dropdown is not empty in my profile page");
				}
				
			}
			else {
				sa.assertTrue(false, "profile link on all firms page is not clickable");
			}
			}
		else {
			sa.assertTrue(false, "Investor password is wrong so cannot login");
		}
		lp.investorLogout();
		sa.assertAll();
		
	}
	@Test
	public void M2tc023_ChangeFirmLogo() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String cont_password_updated = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_password_updated);
		
		lp.investorLogin( M2Contact1EmailID, cont_password_updated);
		String parentID = driver.getWindowHandle();
		if (click(driver, af.getProfileLink(60), "Profile link on all firms page", action.BOOLEAN)) {
			if (click(driver, ip.getMyFirmProfileTab(60), "firm profile tab on investor portal", action.BOOLEAN)) {
				if (click(driver, ip.getChangeFirmLogoLink(60), "change firm logo link", action.SCROLLANDBOOLEAN)) {
					parentID = switchOnWindow(driver);
					if (ip.getChangeFirmLogoHeading(60).getText().trim().equals("Change Firm Logo")) {
						appLog.info("change firm logo heading text is successfully verified");
					}
					if (sendKeys(driver, ip.getBrowseButton(60), System.getProperty("user.dir")+"//UploadFiles/greaterthantwomb.png", "browse button on change firm logo window", action.BOOLEAN)) {
						if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change firm logo window", action.BOOLEAN)) {
							if (ip.getSizeGreaterUploadError(60).getText().trim().equals(InvestorProfileErrorsPage.sizeGreaterError)) {
								appLog.info("file size greater error messsage is successfully verified");
							}
							else {
								sa.assertTrue(false, "file size greater error message is not correct");
							}
							
						}
						else {
							sa.assertTrue(false, "submit button on change picture window is not clickable");
						}
					}
					else {
						sa.assertTrue(false, "browse button on change picture window is not clickable");
					}
					if (sendKeys(driver, ip.getBrowseButton(60),  System.getProperty("user.dir")+"//UploadFiles/lessthantwomb.png", "browse button on change firm logo window", action.BOOLEAN)) {
						if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change firm logo window", action.BOOLEAN)) {
							if(bp.clickUsingCssSelectorPath("a[title='Save']", "change profile save button")) {
//							if (click(driver,ip.getChangeProfileSaveButton(60), "save button on change firm logo window", action.BOOLEAN)) {
								ThreadSleep(5000);
								if (isAlertPresent(driver)) {
									String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if (msg.contains(InvestorProfileErrorsPage.changeLogoQuestion)) {
										appLog.info("alert(that investor wants to change logo) is successfully verified");
										sa.assertTrue(true);
									}
								}else {
									sa.assertTrue(false,"Alert for confirmation to change logo is not present");
								}
							}
							else {
								sa.assertTrue(false, "save button on change picture window is not clickable");
							}
						}
						else {
							sa.assertTrue(false, "submit button on change picture window is not clickable");
						}
					}
					else {
						sa.assertTrue(false, "browse button is not clickable on change picture window");
					}
					
				}
				else {
					sa.assertTrue(false, "change firm logo link is not clickable on firm profile page");
				}
				
				driver.close();
				driver.switchTo().window(parentID);
				driver.navigate().refresh();
				if (click(driver, ip.getChangeFirmLogoLink(60), "change firm logo link on my firms profile page", action.BOOLEAN)) {
					parentID=switchOnWindow(driver);
					if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change firm logo window", action.BOOLEAN)) {
					if (ip.getSpecifyFileToUploadError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseSpecifyFileToUpload)) {
						appLog.info("please specify file to upload error message is successfully verified");
					}
					else {
						sa.assertTrue(false, "please specify file to upload error message is not correct");
					}
					}
					else {
						sa.assertTrue(false, "submit button is not clickable on change picture window");
					}
					}
				else {
					sa.assertTrue(false, "change firm logo link is not clickable on firm profile page");
				}
				
				

				//uploading bmp image(successful)
				if (sendKeys(driver, ip.getBrowseButton(60), System.getProperty("user.dir")+"\\UploadFiles\\logoSupportFormatImage\\bmpimage.bmp", "browse button change profile picture",action.BOOLEAN)) {
					if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change picture window", action.BOOLEAN)) {
						if(bp.clickUsingCssSelectorPath("a[title='Save']", "change profile save button")) {
//						if (click(driver, ip.getChangeProfileSaveButton(100), "save button", action.BOOLEAN)) {
							ThreadSleep(5000);
							if (isAlertPresent(driver)) {
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 10, action.ACCEPT);
								if (msg.trim().equals(InvestorProfileErrorsPage.changeLogoQuestion)) {
									appLog.info("alert to ask if want to change logo is successfully verified");

								}
								else {
									sa.assertTrue(false, "alert to ask if want to change logo is not correct");
								}
							}else {
								sa.assertTrue(false,"alert to ask if want to change logo is not present");

							}
						}
						else {
							sa.assertTrue(false, "save button on change profile picture is not clickable");
						}
					}
					else {
						sa.assertTrue(false, "submit button on change profile picture is not clickable");
					}
				}
				else {
					sa.assertTrue(false, "browse button is not visible on change profile window");
				}
				driver.close();
				driver.switchTo().window(parentID);
				driver.navigate().refresh();
			}
			if (click(driver,  ip.getChangeFirmLogoLink(60), "change firm logo link", action.SCROLLANDBOOLEAN)) {
				parentID=switchOnWindow(driver);

				//uploading jpg image(successful)
				if (sendKeys(driver, ip.getBrowseButton(60), System.getProperty("user.dir")+"\\UploadFiles\\logoSupportFormatImage\\jpgimage.jpg", "browse button change profile picture",action.BOOLEAN)) {
					if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change picture window", action.BOOLEAN)) {
						if(bp.clickUsingCssSelectorPath("a[title='Save']", "change profile save button")) {
//						if (click(driver, ip.getChangeProfileSaveButton(100), "save button", action.BOOLEAN)) {
							ThreadSleep(5000);
							if (isAlertPresent(driver)) {
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								if (msg.trim().equals(InvestorProfileErrorsPage.changeLogoQuestion)) {
									appLog.info("alert to ask if want to change logo is successfully verified");
								}
								else {
									sa.assertTrue(false, "alert to ask if want to change logo is not correct");
								}
							}else {
								sa.assertTrue(false,"alert to ask if want to change logo is not present");

							}
						}
						else {
							sa.assertTrue(false, "save button on change profile picture is not clickable");
						}
					}
					else {
						sa.assertTrue(false, "submit button on change profile picture is not clickable");
					}
				}
				else {
					sa.assertTrue(false, "browse button is not visible on change profile window");
				}
				driver.close();
				driver.switchTo().window(parentID);
				driver.navigate().refresh();
			}
			else {
				sa.assertTrue(false, "change firm logo link is not clickable on firm profile page");
			}

			if (click(driver,  ip.getChangeFirmLogoLink(60), "change firm logo link", action.SCROLLANDBOOLEAN)) {
				parentID=switchOnWindow(driver);

				//uploading gif image(successful)
				if (sendKeys(driver, ip.getBrowseButton(60), System.getProperty("user.dir")+"\\UploadFiles\\logoSupportFormatImage\\gifimage.gif", "browse button change profile picture",action.BOOLEAN)) {
					if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change picture window", action.BOOLEAN)) {
						if(bp.clickUsingCssSelectorPath("a[title='Save']", "change profile save button")) {
//						if (click(driver, ip.getChangeProfileSaveButton(100), "save button", action.BOOLEAN)) {
							ThreadSleep(5000);
							if (isAlertPresent(driver)) {
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								if (msg.trim().equals(InvestorProfileErrorsPage.changeLogoQuestion)) {
									appLog.info("alert to ask if want to change logo is successfully verified");
								}
								else {
									sa.assertTrue(false, "alert to ask if want to change logo is not correct");
								}
							}else {
								sa.assertTrue(false,"alert to ask if want to change logo is not present");

							}
						}
						else {
							sa.assertTrue(false, "save button on change profile picture is not clickable");
						}
					}
					else {
						sa.assertTrue(false, "submit button on change profile picture is not clickable");
					}
				}
				else {
					sa.assertTrue(false, "browse button is not visible on change profile window");
				}
				driver.close();
				driver.switchTo().window(parentID);
				driver.navigate().refresh();


			}
			if (click(driver,  ip.getChangeFirmLogoLink(60), "change firm logo link", action.SCROLLANDBOOLEAN)) {
				parentID=switchOnWindow(driver);

				//uploading png image(successful)
				if (sendKeys(driver, ip.getBrowseButton(60), System.getProperty("user.dir")+"\\UploadFiles\\logoSupportFormatImage\\pngimage.png", "browse button change profile picture",action.BOOLEAN)) {
					if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change picture window", action.BOOLEAN)) {
						if(bp.clickUsingCssSelectorPath("a[title='Save']", "change profile save button")) {
//						if (click(driver, ip.getChangeProfileSaveButton(100), "save button", action.BOOLEAN)) {
							ThreadSleep(5000);
							if (isAlertPresent(driver)) {
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								if (msg.trim().equals(InvestorProfileErrorsPage.changeLogoQuestion)) {
									appLog.info("alert to ask if want to change logo is successfully verified");
								}
								else {
									sa.assertTrue(false, "alert to ask if want to change logo is not correct");
								}
							}else {
								sa.assertTrue(false,"alert to ask if want to change logo is not present");

							}
						}
						else {
							sa.assertTrue(false, "save button on change profile picture is not clickable");
						}
					}
					else {
						sa.assertTrue(false, "submit button on change profile picture is not clickable");
					}
				}
				else {
					sa.assertTrue(false, "browse button is not visible on change profile window");
				}
				driver.close();
				driver.switchTo().window(parentID);
				driver.navigate().refresh();
			}
			if (click(driver, ip.getChangeFirmLogoLink(60), "change firm logo link", action.SCROLLANDBOOLEAN)) {
				parentID=switchOnWindow(driver);
				//uploading pdf file(unsuccessful)
				if (sendKeys(driver, ip.getBrowseButton(60), System.getProperty("user.dir")+"\\UploadFiles\\OtherFormatFile\\Target1.pdf", "browse button change profile picture",action.BOOLEAN)) {
					if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change picture window", action.BOOLEAN)) {

						if (ip.getSizeGreaterUploadError(60)!=null) {
							if (ip.getSizeGreaterUploadError(60).getText().trim().equals(InvestorProfileErrorsPage.fileTypeNotCorrect)) {
								appLog.info("file type not correct error messaage found successfully for pdf file");
							}
							else {
								sa.assertTrue(false, "error message of wrong file type is not correct");
							}
						}
						else {
							sa.assertTrue(false, "error message text is not displayed");
						}
					}
					else {
						sa.assertTrue(false, "submit button is not clickable on change picture window");
					}
				}
				else {
					sa.assertTrue(false, "image path could not be sent to browse button in change picture window");
				}
				driver.close();
				driver.switchTo().window(parentID);
				driver.navigate().refresh();
			}
			if (click(driver, ip.getChangeFirmLogoLink(60), "change firm logo link", action.SCROLLANDBOOLEAN)) {
				parentID=switchOnWindow(driver);
				//uploading doc file(unsuccessful)
				if (sendKeys(driver, ip.getBrowseButton(60), System.getProperty("user.dir")+"\\UploadFiles\\OtherFormatFile\\docfile.docx", "browse button change profile picture",action.BOOLEAN)) {
					if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change picture window", action.BOOLEAN)) {

						if (ip.getSizeGreaterUploadError(60)!=null) {
							if (ip.getSizeGreaterUploadError(60).getText().trim().equals(InvestorProfileErrorsPage.fileTypeNotCorrect)) {
								appLog.info("file type not correct error messaage found successfully for doc file");
							}
							else {
								sa.assertTrue(false, "error message of wrong file type is not correct");
							}
						}
						else {
							sa.assertTrue(false, "error message text is not displayed");
						}
					}
					else {
						sa.assertTrue(false, "submit button is not clickable on change picture window");
					}
				}
				else {
					sa.assertTrue(false, "image path could not be sent to browse button in change picture window");
				}
				driver.close();
				driver.switchTo().window(parentID);
				driver.navigate().refresh();
			}

			if (click(driver, ip.getChangeFirmLogoLink(60), "change firm logo link", action.SCROLLANDBOOLEAN)) {
				parentID=switchOnWindow(driver);

				//uploading xls file(unsuccessful)
				if (sendKeys(driver, ip.getBrowseButton(60), System.getProperty("user.dir")+"\\UploadFiles\\OtherFormatFile\\excelfile.xlsx", "browse button change profile picture",action.BOOLEAN)) {
					if (click(driver, ip.getSubmitButtonChangePicture(60), "submit button on change picture window", action.BOOLEAN)) {

						if (ip.getSizeGreaterUploadError(60)!=null) {
							if (ip.getSizeGreaterUploadError(60).getText().trim().equals(InvestorProfileErrorsPage.fileTypeNotCorrect)) {
								appLog.info("file type not correct error messaage found successfully for xls file");
							}
							else {
								sa.assertTrue(false, "error message of wrong file type is not correct");
							}
						}
						else {
							sa.assertTrue(false, "error message text is not displayed");
						}
					}
					else {
						sa.assertTrue(false, "submit button is not clickable on change picture window");
					}
				}
				else {
					sa.assertTrue(false, "image path could not be sent to browse button in change picture window");
				}
				driver.close();
				driver.switchTo().window(parentID);
				driver.navigate().refresh();
			}
			else {
				sa.assertTrue(false, "change firm logo link is not clickable on all firms page");
			}

			}
		else {
			sa.assertTrue(false, "Profile link on all firms page is not clickable");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	@Test
	public void M2tc024_CheckUIMyFirmsProfileEditMode() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);

		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String cont_password_updated = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_password_updated);
		
		lp.investorLogin( M2Contact1EmailID, cont_password_updated);
		String[] editmode_basic_table = {"Firm Name:", "Industry:", "Billing Street:", "Billing City:", "Billing State/Province:", "Billing Zip/Postal Code:", "Billing Country:","Institution Type:", "Description:"};
		if (click(driver, af.getProfileLink(60), "profile link on all firms page", action.SCROLLANDBOOLEAN)) {
			if (click(driver,ip.getMyFirmProfileTab(60) , "my firms profile tab on all firms page", action.BOOLEAN)) {
				if (click(driver, ip.getEditIcon(60), "edit icon on my firms profile page", action.BOOLEAN)) {
					//finding values of labels in firm profile's edit mode
					int size_edit_mode_basic_info_list = ip.getEditModeBasicInfoListFirmProfile().size();

					List<String> edit_mode_string_list = new ArrayList<String>();
					edit_mode_string_list.addAll( Arrays.asList(editmode_basic_table) );

					if (!ip.getEditModeBasicInfoListFirmProfile().isEmpty()) {
						for (int i = 5, j=0;i<size_edit_mode_basic_info_list;i++,j++) {
							WebElement temp_element = ip.getEditModeBasicInfoListFirmProfile().get(i);

							if (temp_element.getText().contains(edit_mode_string_list.get(j))){
								appLog.info(edit_mode_string_list.get(j)+" was successfully found on my profile page");
							}
							else {
								sa.assertTrue(false, edit_mode_string_list.get(j)+" was not found on page");
							}
						}
					}
					else {
						sa.assertTrue(false, "The element list cannot be found");
					}

					//finding values of elements in firm profile's edit mode
					if (ip.getFirmNameTextbox(60)!=null) {
						appLog.info("Firm name textbox present on my firm profiles page");

					}
					else {
						sa.assertTrue(false, "firm name textbox is not visible on my firm profile page");
					}
					if (ip.getIndustryDropdown(60)!=null) {
						appLog.info("Industry dropdown present on my firm profile page");
					}
					else {
						sa.assertTrue(false, "industry dropdown is not visible on my firm profile page");
					}
					if (ip.getBillingStreetTextArea(60)!=null) {
						appLog.info("Billing street text area is present on my firm profile page");

					}
					else {
						sa.assertTrue(false, "billing street textbox is not visible on my firm profile page");
					}
					if (ip.getBillingCityTextbox(60)!=null) {
						appLog.info("billing city textbox is present on my firm profile page");
					}
					else {
						sa.assertTrue(false, "billing city textbox is not visible on my firm profile page");
					}
					if (ip.getBillingStateTextbox(60)!=null) {
						appLog.info("billing state textbox is present on my firm profile page");
					}
					else {
						sa.assertTrue(false, "billing state textbox is not visible on my firm profile page");
					}
					if (ip.getBillingZipCodeTextbox(60)!=null) {
						appLog.info("billing zip code texbox is presnent on my firm profile page");
					}
					else {
						sa.assertTrue(false, "billing zip code textbox is not visible on my firm profile page");
					}
					if(ip.getBillingCountryTextbox(60)!=null) {
						appLog.info("billing country textbox is present on my firm profile page");
					}
					else {
						sa.assertTrue(false, "billing country textbox is not visible on my firm profile page");

					}
					if(ip.getInstitutionTypeDropdown(60)!=null) {
						appLog.info("institution type dropdown is present on my firm profile page");
					}
					else {
						sa.assertTrue(false, "institution type dropdown is not visible on my firm profile page");
					}
					if (ip.getDescriptionTextArea(60)!=null) {
						appLog.info("description textbox is present on my firm profile page");
					}
					else {
						sa.assertTrue(false, "description textbox is not visible on my firm profile page");

					}
					//finding elements under investment preferences section
					if (ip.getFundTypeSelectbox(60)!=null) {
						appLog.info("fund type select box is present on my firm profile page");
					}
					else {
						sa.assertTrue(false, "fund type selectbox is not visible on my firm profile page");
					}
					if (ip.getIndustriesSelectbox(60)!=null) {
						appLog.info("industries type select box is presnt on my firm profile page");
					}
					else {
						sa.assertTrue(false, "insdustries type selectbox is not visible on my firm profile page");
					}
					if (ip.getGeoFocusSelectbox(60)!=null) {
						appLog.info("geo focus select box is present on my firm profile page");
					}
					else {
						sa.assertTrue(false, "geo focus selectbox is not visible on my firm profile page");
					}
					if (ip.getMinInvestmentTextbox(60)!=null) {
						appLog.info("min investment amount textbox is present on my firm profile page");
					}
					else {
						sa.assertTrue(false, "min investment amount textbox is not visible on my firm profile page");
					}
					if (ip.getMaxInvestmentTextbox(60)!=null) {
						appLog.info("max investment amount textbox is present on my firm profile page");
					}
					else {
						sa.assertTrue(false, "max investment amount textbox is not visible on my firm profile page");
					}
					if (ip.getAUMtextbox(60)!=null) {
						appLog.info("aum amount textbox is present on my firm profile page");
					}
					else {
						sa.assertTrue(false, "aum textbox is not visible on my firm profile page");
					}
				}
				else {
					sa.assertTrue(false, "Edit icon on firm profile page is not clickable");
				}
			}
			else {
				sa.assertTrue(false, "My firm profile tab is not clickable on all firms page");
			}
		}
		else {
			sa.assertTrue(false, "profile link on investor login is not clickable");
		}
		lp.investorLogout();
		sa.assertAll();


	}

	@Test
	public void M2tc025_CheckValuesInSelectboxesFirmProfilePage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String cont_password_updated = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_password_updated);
		
		lp.investorLogin( M2Contact1EmailID, cont_password_updated);
		String instType = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.Institution_Type_MyProfile);
		String industryDropdown = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.Industry_Dropdown_myprofile);
		String fundType = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.FundType_myprofile);
		String industrySelectBox = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.IndustrySelectBox_myprofile);
		String geoFocus = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.GeoFocus_myprofile);
		
		if (click(driver, af.getProfileLink(60), "profile link on investor login", action.SCROLLANDBOOLEAN)) {
			if (click(driver, ip.getMyFirmProfileTab(60), "my firm profile tab on investor login", action.BOOLEAN)) {
				if (click(driver, ip.getEditIcon(60), "edit icon on firm profile page", action.BOOLEAN)) {
					List<WebElement> industryDropdownList = allOptionsInDropDrop(driver, ip.getIndustryDropdown(60), "industry dropdown on my firm profile page");
					List<WebElement> instTypeDropdownList = allOptionsInDropDrop(driver, ip.getInstitutionTypeDropdown(60), "institution type dropdown");
					List<WebElement> fundTypeList = allOptionsInDropDrop(driver, ip.getFundTypeSelectbox(60), "fund type select box");
					List<WebElement> industrySelectboxList = allOptionsInDropDrop(driver, ip.getIndustriesSelectbox(60), "industries select box");
					List<WebElement> geofocusList = allOptionsInDropDrop(driver, ip.getGeoFocusSelectbox(60), "geo focus select box");
				
					List<String> lst=compareMultipleList(driver,industryDropdown, industryDropdownList);
					if(!lst.isEmpty()) {
						for(int i=0; i<lst.size(); i++) {
							appLog.error(lst.get(i) + " is not present in industry dropdown");
							sa.assertTrue(false, lst.get(i) + " is not present in industry dropdown");
						}
					}else {
						appLog.info("industry dropdown list is matched successfully.");
					}
					
					lst.clear();
					lst=compareMultipleList(driver,instType, instTypeDropdownList);
					if(!lst.isEmpty()) {
						for(int i=0; i<lst.size(); i++) {
							appLog.error(lst.get(i) + " is not present in inst type dropdown");
							sa.assertTrue(false, lst.get(i) + " is not present in inst type dropdown");
						}
					}else {
						appLog.info("inst type dropdown list is matched successfully.");
					}
					
					lst.clear();
					lst=compareMultipleList(driver,fundType, fundTypeList);
					if(!lst.isEmpty()) {
						for(int i=0; i<lst.size(); i++) {
							appLog.error(lst.get(i) + " is not present in fund type dropdown");
							sa.assertTrue(false, lst.get(i) + " is not present in fund type dropdown");
						}
					}else {
						appLog.info("fund type dropdown list is matched successfully.");
					}
					

					lst.clear();
					lst=compareMultipleList(driver,industrySelectBox, industrySelectboxList);
					if(!lst.isEmpty()) {
						for(int i=0; i<lst.size(); i++) {
							appLog.error(lst.get(i) + " is not present in industry select box");
							sa.assertTrue(false, lst.get(i) + " is not present in industry select box");
						}
					}else {
						appLog.info("industry select box list is matched successfully.");
					}
					
					lst.clear();
					lst=compareMultipleList(driver,geoFocus, geofocusList);
					if(!lst.isEmpty()) {
						for(int i=0; i<lst.size(); i++) {
							appLog.error(lst.get(i) + " is not present in geo focus select box");
							sa.assertTrue(false, lst.get(i) + " is not present in geo focus select box");
						}
					}else {
						appLog.info("geo focus select box list is matched successfully.");
					}
				}
				else {
					sa.assertTrue(false, "edit icon on my firm profile page is not clickable");
				}
			}
			else {
				sa.assertTrue(false, "my firm profile tab is not clickable");
			}
		}
		else {
			sa.assertTrue(false, "profile link is not clickable on all firms page");
		}
		
		lp.investorLogout();
		sa.assertAll();
	}
	@Test
	public void M2tc026_CheckSaveAndCancelFunctionalityMyFirmsProfile() {
		
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);

		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String cont_password_updated = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_password_updated);
		
		lp.investorLogin( M2Contact1EmailID, cont_password_updated);
		//lp.investorLogin( "navatarusertestcrm+avi25jul@gmail.com", adminPassword);
		String firmDescription = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Firm_Description_Firmprofile);
		String billing_street = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Billing_street_Firmprofile);
		String billing_city = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Billing_city_firmprofile);
		String billing_state = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Billing_state_firmprofile);
		String billing_country = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Billing_country_firmprofile);
		String industries_selectbox = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Industries_selectbox);
		String fundType_selectbox = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.FundType_selectbox);
		String geofocus_selectbox = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Geofocus_selectbox);
		String industryDropdown = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Industry_dropdown);
		String instTypeDropdown = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.InstType_dropdown);
		String min_investment = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Min_investment);
		String max_investment = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Max_investment);
		String aum = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.AUM);
		String firm_name = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_updated_firmname);
		
		String[] viewModeValueFirmProfileString= {firm_name, industryDropdown, billing_street, billing_city, billing_state, billing_country, instTypeDropdown, firmDescription, fundType_selectbox, min_investment, industries_selectbox, max_investment, geofocus_selectbox, aum};
		
		if (click(driver, af.getProfileLink(60), "profile link on investor login", action.SCROLLANDBOOLEAN)) {
			if (click(driver,ip.getMyFirmProfileTab(60) , "my firm profile tab", action.BOOLEAN)) {
				if (click(driver, ip.getEditIcon(60), "edit icon on myfirm profile page", action.BOOLEAN)) {
					if (selectVisibleTextFromDropDown(driver, ip.getInstitutionTypeDropdown(60), "institution type dropdown my firm profile page", instTypeDropdown)) {
						if (selectVisibleTextFromDropDown(driver, ip.getIndustryDropdown(60), "industry dropdown on myfirm profile page", industryDropdown)) {
							if (sendKeys(driver, ip.getDescriptionTextArea(60), firmDescription,"description text area on firm profile page", action.SCROLLANDBOOLEAN)) {
								if (sendKeys(driver, ip.getBillingStreetTextArea(60), billing_street, "billing street on my firm profile page", action.SCROLLANDBOOLEAN)) {
									if (sendKeys(driver, ip.getBillingCityTextbox(60), billing_city, "billing city textbox on my firm profile page", action.SCROLLANDBOOLEAN)) {
										if (sendKeys(driver,ip.getBillingStateTextbox(60), billing_state, "billing state on my firm profile page", action.SCROLLANDBOOLEAN)) {
											if (sendKeys(driver, ip.getBillingCountryTextbox(60), billing_country, "billing country on my firms profile page", action.SCROLLANDBOOLEAN)) {
												if (click(driver, ip.getWeWishToBeContactedNoEditModeRadiobtn(60), "we wish to be contacted no radio button", action.SCROLLANDBOOLEAN)) {
													if (selectVisibleTextFromDropDown(driver, ip.getFundTypeSelectbox(60), "fund type select box on firm profile page", fundType_selectbox)) {
														if(click(driver, ip.getAddButtonFundsSelectbox(60), "add button in funds type select box", action.BOOLEAN)) {
															if (selectVisibleTextFromDropDown(driver, ip.getIndustriesSelectbox(60), "industries select box in firm profile page", industries_selectbox)) {
																if (click(driver, ip.getAddButtonIndustriesSelectbox(60), "add button in industries select box", action.BOOLEAN)) {
																	if (selectVisibleTextFromDropDown(driver, ip.getGeoFocusSelectbox(60), "geo focus select box on firm profile page", geofocus_selectbox)) {
																		if (click(driver, ip.getAddButtonGeographicSelectbox(60), "add button geographic select box on firm profile page", action.BOOLEAN)) {
																			if (sendKeys(driver, ip.getMinInvestmentTextbox(60), min_investment, "minimum investment amount textbox firm profile page", action.SCROLLANDBOOLEAN)) {			
																				if (sendKeys(driver, ip.getMaxInvestmentTextbox(60), max_investment, "max investment amount textbox on firm profile page", action.SCROLLANDBOOLEAN)) {
																					if (sendKeys(driver, ip.getAUMtextbox(60), aum, "aum textbox on my firm profile page", action.SCROLLANDBOOLEAN)) {
																						if (click(driver, ip.getCancelButtonFirmProfile(60), "cancel button on my firm profile page", action.SCROLLANDBOOLEAN)) {
																							List<String> temp_list = new ArrayList<String>();
																							temp_list.addAll( Arrays.asList(viewModeValueFirmProfileString) );
																							for (int i = 0;i<=1;i++) {
																								WebElement el = ip.getViewModeValueFirmProfile().get(i);
																								if (el==null)
																								{
																									appLog.info(temp_list.get(i)+" was empty before and is still empty after clicking cancel button");
																								}
																								else {
																								if (!el.getText().trim().equals(temp_list.get(i))) {
																									appLog.info(temp_list.get(i)+" is not updated after clicking cancel in my profile firm page");
																								}
																								else {
																									sa.assertTrue(false, temp_list.get(i)+" is updated after clicking cancel button in my profile firm page");
																								}
																								}
																							}
																							
																							for (int i = 2;i<=5;i++) {
																								WebElement el = ip.getViewModeValueFirmProfile().get(2);
																								if (el==null)
																								{
																									appLog.info(temp_list.get(i)+" was empty before and is still empty after clicking cancel button");
																								}
																								else {
																								if (!el.getText().trim().contains(temp_list.get(i))) {
																									appLog.info(temp_list.get(i)+" is not updated after clicking cancel in my profile firm page");
																								}
																								else {
																									sa.assertTrue(false, temp_list.get(i)+" is updated after clicking cancel button in my profile firm page");
																								}
																								}
																							}
																							for (int i = 6,j=3;j<11;i++,j++) {
																							WebElement temp_el = ip.getViewModeValueFirmProfile().get(j);
																								if (temp_el==null)
																								{
																									appLog.info(temp_list.get(i)+" was empty before and is still empty after clicking cancel button");
																								}
																								else {
																								if (!temp_el.getText().trim().contains(temp_list.get(i))) {
																									appLog.info(temp_list.get(i)+" is not updated after clicking cancel in my profile firm page");
																								}
																								else {
																									sa.assertTrue(false, temp_list.get(i)+" is updated after clicking cancel button in my profile firm page");
																								}
																								}
																							}
																						}
																						else {
																							sa.assertTrue(false, "cancel button is not clickable on my firm profile page");
																						}
																					}else {
																						sa.assertTrue(false, "AUM textboxx is not visible on my firm profile page");
																					}
																				}
																				else {
																					sa.assertTrue(false, "Max investment textbox is not visiblt on my firm profile page");
																				}
																			}
																			else {
																				sa.assertTrue(false, "min investment textbox is not visible on my firm profile page");
																			}
																		}
																	}
																	else {
																		sa.assertTrue(false, "geo focus select box is not visible on my firm profile page");
																	}
																}
															}
															else {
																sa.assertTrue(false, "industries select box is not visible on my firm profile page");
															}
														}
													}
													else {
														sa.assertTrue(false, "funds type select box is not visible on my firm profile page");
													}
												}
												else {
													sa.assertTrue(false, "no radiobtn for we wish to be contacted for further opportunities is not clickable");
												}
											}
											else {
												sa.assertTrue(false, "billing country textbox is not visible on my firm profile page");
											}
										}
										else {
											sa.assertTrue(false, "billing state textbox is not visible on my firm profile page");
										}
									}
									else {
										sa.assertTrue(false, "billing city textbox is not visible on my firm profile page");
									}
								}
								else {
									sa.assertTrue(false, "billing street textbox is not visible on my firm profile page");
								}
							}
							else {
								sa.assertTrue(false, "description textbox is not visible on my firm profile page");
							}

						}
						else {
							sa.assertTrue(false, "industry dropdown is not visible on my firm profile page");
						}
					}
					else {
						sa.assertTrue(false, "institution type dropdown is not visible on my firm profile page");
					}
					if (click(driver, ip.getEditIcon(60), "edit icon on myfirm profile page", action.BOOLEAN)) {
						if (selectVisibleTextFromDropDown(driver, ip.getInstitutionTypeDropdown(60), "institution type dropdown my firm profile page", instTypeDropdown)) {
							if (selectVisibleTextFromDropDown(driver, ip.getIndustryDropdown(60), "industry dropdown on myfirm profile page",industryDropdown)) {
								if (sendKeys(driver, ip.getDescriptionTextArea(60),firmDescription, "description text area on firm profile page", action.SCROLLANDBOOLEAN)) {
									if (sendKeys(driver, ip.getBillingStreetTextArea(60), billing_street, "billing street on my firm profile page", action.SCROLLANDBOOLEAN)) {
										if (sendKeys(driver, ip.getBillingCityTextbox(60),billing_city, "billing city textbox on my firm profile page", action.SCROLLANDBOOLEAN)) {
											if (sendKeys(driver,ip.getBillingStateTextbox(60), billing_state, "billing state on my firm profile page", action.SCROLLANDBOOLEAN)) {
												if (sendKeys(driver, ip.getBillingCountryTextbox(60), billing_country, "billing country on my firms profile page", action.SCROLLANDBOOLEAN)) {
													if (click(driver, ip.getWeWishToBeContactedNoEditModeRadiobtn(60), "we wish to be contacted no radio button", action.SCROLLANDBOOLEAN)) {

														if (selectVisibleTextFromDropDown(driver, ip.getFundTypeSelectbox(60), "fund type select box on firm profile page", fundType_selectbox)) {
															if(click(driver, ip.getAddButtonFundsSelectbox(60), "add button in funds type select box", action.BOOLEAN)) {
																if (selectVisibleTextFromDropDown(driver, ip.getIndustriesSelectbox(60), "industries select box in firm profile page", industries_selectbox)) {
																	if (click(driver, ip.getAddButtonIndustriesSelectbox(60), "add button in industries select box", action.BOOLEAN)) {

																		if (selectVisibleTextFromDropDown(driver, ip.getGeoFocusSelectbox(60), "geo focus select box on firm profile page", geofocus_selectbox)) {
																			if (click(driver, ip.getAddButtonGeographicSelectbox(60), "add button geographic select box on firm profile page", action.BOOLEAN)) {
																				if (sendKeys(driver, ip.getMinInvestmentTextbox(60), min_investment, "minimum investment amount textbox firm profile page", action.SCROLLANDBOOLEAN)) {
																					if (sendKeys(driver, ip.getMaxInvestmentTextbox(60), max_investment, "max investment amount textbox on firm profile page", action.SCROLLANDBOOLEAN)) {
																						if (sendKeys(driver, ip.getAUMtextbox(60), aum, "aum textbox on my firm profile page", action.SCROLLANDBOOLEAN)) {

																							ip.getFirmNameTextbox(60).clear();
																							if (click(driver, ip.getSaveButtonFirmProfile(60), "save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
																								if (ip.getFirmNameErrorText(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterRequiredFields)) {
																									appLog.info("when firm name is empty, error message to please enter required fields is found successfully");
																								}
																								if (sendKeys(driver, ip.getFirmNameTextbox(60), firm_name, "firm name textbox on my firms profile page", action.SCROLLANDBOOLEAN)) {
																									if(click(driver, ip.getSaveButtonFirmProfile(60), "save button my firm profile page", action.SCROLLANDBOOLEAN)) {
																										List<String> temp_list = new ArrayList<String>();
																										temp_list.addAll( Arrays.asList(viewModeValueFirmProfileString) );

																										for (int i = 0;i<=1;i++) {
																											WebElement temp_el = ip.getViewModeValueFirmProfile().get(i);
																											if (temp_el.getText().trim().equals(temp_list.get(i))) {
																												appLog.info(temp_list.get(i)+" is updated after clicking save in my profile firm page");
																											}
																											else {
																												sa.assertTrue(false, temp_list.get(i)+" is not updated after clicking save button in my profile firm page");
																											}
																										}
																										
																										for (int i = 2;i<=5;i++) {
																											WebElement el = ip.getViewModeValueFirmProfile().get(2);	
																											if (el.getText().trim().contains(temp_list.get(i))) {
																												appLog.info(temp_list.get(i)+" is updated after clicking save in my profile firm page");
																											}
																											else {
																												sa.assertTrue(false, temp_list.get(i)+" is not updated after clicking save button in my profile firm page");
																											}
																										}
																										for (int i = 6,j=3;j<11;i++,j++) {
																											WebElement temp_el = ip.getViewModeValueFirmProfile().get(i-3);
																											if (temp_el.getText().trim().contains(temp_list.get(i))) {
																												appLog.info(temp_list.get(i)+" is updated after clicking save in my profile firm page");
																											}
																											else {
																												sa.assertTrue(false, temp_list.get(i)+" is not updated after clicking save button in my profile firm page");
																											}
																										}
																										if (click(driver, ip.getMyProfileTab(60), "my profiles tab on investor login", action.SCROLLANDBOOLEAN)) {
																											if (ip.getFirmNameValueMyProfilePage(60).getText().trim().equals(firm_name)) {
																												appLog.info("Firm name changed in my firm profile page is successfully updated in my profile page");
																											}
																											else {
																												sa.assertTrue(false, "firm name changed in firm profile page was not updated in my profile page");
																											}
																										}
																										else {
																											sa.assertTrue(false, "My profile tab is not clickable on all firms page");
																										}
																									}
																									else {
																										sa.assertTrue(false, "save button on my firm profile page is not clickable");
																									}

																								}
																								else {
																									sa.assertTrue(false, "firm name textbox is not visible on my firm profile page");
																								}

																							}
																							else {
																								sa.assertTrue(false, "save button on my firm profile page is not clickable");
																							}
																						}
																						else {
																							sa.assertTrue(false, "AUM textboxx is not visible on my firm profile page");
																						}
																					}
																					else {
																						sa.assertTrue(false, "Max investment textbox is not visiblt on my firm profile page");
																					}
																				}
																				else {
																					sa.assertTrue(false, "min investment textbox is not visible on my firm profile page");
																				}
																			}
																		}
																		else {
																			sa.assertTrue(false, "geo focus select box is not visible on my firm profile page");
																		}
																	}
																}
																else {
																	sa.assertTrue(false, "industries select box is not visible on my firm profile page");
																}
															}
														}
														else {
															sa.assertTrue(false, "funds type select box is not visible on my firm profile page");
														}
													}
													else {
														sa.assertTrue(false, "no radiobtn for we wish to be contacted for further opportunities is not clickable");
													}
												}
												else {
													sa.assertTrue(false, "billing country textbox is not visible on my firm profile page");
												}
											}
											else {
												sa.assertTrue(false, "billing state textbox is not visible on my firm profile page");
											}
										}
										else {
											sa.assertTrue(false, "billing city textbox is not visible on my firm profile page");
										}
									}
									else {
										sa.assertTrue(false, "billing street textbox is not visible on my firm profile page");
									}
								}
								else {
									sa.assertTrue(false, "description textbox is not visible on my firm profile page");
								}

							}
							else {
								sa.assertTrue(false, "industry dropdown is not visible on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "institution type dropdown is not visible on my firm profile page");
						}

					}
					else {
						sa.assertTrue(false, "edit icon is not clickable on my firm profile page");
					}
				}
				else {
					sa.assertTrue(false, "edit icon is not clickable on my firm profile page");
				}
			}
			else {
				sa.assertTrue(false, "my firm profile tab is not clickable on all firms page");
			}
		}
		else {
			sa.assertTrue(false, "profile link is not clickable on all firms page");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	@Test
	public void M2tc027_CheckMinInvestmentAndMaxInvestmentValues() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String cont_password_updated = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_password_updated);
		
		lp.investorLogin(M2Contact1EmailID, cont_password_updated);
		if (click(driver, af.getProfileLink(60), "profile link on investor login page", action.SCROLLANDBOOLEAN)) {
			if (click(driver, ip.getMyFirmProfileTab(60), "my firm profile tab on investor login page", action.BOOLEAN)) {
				if (click(driver, ip.getEditIcon(60), "edit icon on my firm profile page", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, ip.getMinInvestmentTextbox(60), "abc@123", "min investment amount textbox on firm profile page", action.SCROLLANDBOOLEAN)) {
						if (click(driver, ip.getSaveButtonFirmProfile(60), "save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (ip.getMinInvestmentSizeError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidMinInvestmentSize)) {
								appLog.info("min investment size error is successfully verified for abc@123");
							}
							else {
								sa.assertTrue(false, "min investment size error does not contains correct text for abc@123");
							}
						}
						else {
							sa.assertTrue(false, "save button is not clickable on my firm profile page");
						}
					}
					else {
						sa.assertTrue(false, "min investment textbox is not visble on my firm profile page");
					}
					if (sendKeys(driver, ip.getMinInvestmentTextbox(60), "abcde", "min investment size textbox on firm profile page", action.SCROLLANDBOOLEAN)) {
						if (click(driver, ip.getSaveButtonFirmProfile(60), "save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (ip.getMinInvestmentSizeError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidMinInvestmentSize)) {
								appLog.info("min investment size error is successfully verified for abcde");
							}
							else {
								sa.assertTrue(false, "min investment size error does not contain correct text for abcde");
							}
						}
						else {
							sa.assertTrue(false, "save button is not clickable on my firm profile page");
						}
					}
					else {
						sa.assertTrue(false, "min investment textbox is not visble on my firm profile page");
					}
					if (sendKeys(driver, ip.getMinInvestmentTextbox(60), "!@#$%^&*((()&&", "min investment size textbox firm profile page", action.SCROLLANDBOOLEAN)) {
						if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (ip.getMinInvestmentSizeError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidMinInvestmentSize)) {
								appLog.info("min investment size error is successfully verified for !@#$%^&*((()&&");
							}
							else {
								sa.assertTrue(false, "min investment size error does not contain correct text for !@#$%^&*((()&&");
							}
						}
						else {
							sa.assertTrue(false, "save button is not clickable on my firm profile page");
						}
					}
					else {
						sa.assertTrue(false, "min investment textbox is not visble on my firm profile page");
					}
					if (sendKeys(driver, ip.getMinInvestmentTextbox(60), "1234%&", "min investment size textbox firm profile page", action.SCROLLANDBOOLEAN)) {
						if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (ip.getMinInvestmentSizeError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidMinInvestmentSize)) {
								appLog.info("min investment size error is successfully verified for 1234%&");
							}
							else {
								sa.assertTrue(false, "min investment size error does not contain correct text for 1234%&");
							}
						}
						else {
							sa.assertTrue(false, "save button is not clickable on my firm profile page");
						}
					}
					else {
						sa.assertTrue(false, "min investment textbox is not visble on my firm profile page");
					}
					if (sendKeys(driver, ip.getMinInvestmentTextbox(60), "abced&&", "min investment size textbox firm profile page", action.SCROLLANDBOOLEAN)) {
						if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (ip.getMinInvestmentSizeError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidMinInvestmentSize)) {
								appLog.info("min investment size error is successfully verified for abced&&");
							}
							else {
								sa.assertTrue(false, "min investment size error does not contain correct text for abced&&");
							}
						}
						else {
							sa.assertTrue(false, "save button is not clickable on my firm profile page");
						}
					}
					else {
						sa.assertTrue(false, "min investment textbox is not visble on my firm profile page");
					}
					if (sendKeys(driver, ip.getMinInvestmentTextbox(60), "100", "min investment size textbox firm profile page", action.SCROLLANDBOOLEAN)) {
						if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (ip.getMinInvestmentSizeValue(60).getText().trim().equals("$100.00 Mn USD")) {
								appLog.info("min investment size value is successfully changed");
							}
							else {
								sa.assertTrue(false, "min investment size value could not be changed");
							}
						}
						else {
							sa.assertTrue(false, "save button is not clickable on my firm profile page");
						}
					}
					else {
						sa.assertTrue(false, "min investment textbox is not visble on my firm profile page");
					}
				}
				if (click(driver, ip.getEditIcon(60), "edit icon on my firm profile page", action.SCROLLANDBOOLEAN)) {

					if (sendKeys(driver, ip.getMinInvestmentTextbox(60), "155100.25", "min investment size textbox firm profile page", action.SCROLLANDBOOLEAN)) {
						if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (ip.getMinInvestmentSizeValue(60).getText().trim().equals("$155100.25 Mn USD")) {
								appLog.info("min investment size value is successfully changed");
							}
							else {
								sa.assertTrue(false, "min investment size value could not be changed");
							}
						}
						else {
							sa.assertTrue(false, "save button is not clickable on my firm profile page");
						}
					}
					else {
						sa.assertTrue(false, "min investment textbox is not visble on my firm profile page");
					}
				}
				if (click(driver, ip.getEditIcon(60), "edit icon on my firm profile page", action.SCROLLANDBOOLEAN)) {

					if (sendKeys(driver, ip.getMinInvestmentTextbox(60), "155100.258", "min investment size textbox firm profile page", action.SCROLLANDBOOLEAN)) {
						if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (ip.getMinInvestmentSizeError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidMinInvestmentSize)) {
								appLog.info("min investment size value is successfully changed");
							}
							else {
								sa.assertTrue(false, "min investment size value could not be changed");
							}
						}
						else {
							sa.assertTrue(false, "save button is not clickable on my firm profile page");
						}
					}
					else {
						sa.assertTrue(false, "min investment textbox is not visble on my firm profile page");
					}
				

					if (sendKeys(driver, ip.getMinInvestmentTextbox(60), "123456789012345678", "min investment size textbox firm profile page", action.SCROLLANDBOOLEAN)) {
						if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (ip.getMinInvestmentSizeError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidMinInvestmentSize)) {
								appLog.info("min investment error value is successfully verified");
							}
							else {
								sa.assertTrue(false, "min investment size value could not be changed");
							}
						}
						else {
							sa.assertTrue(false, "save button is not clickable on my firm profile page");
						}
					}
					else {
						sa.assertTrue(false, "min investment textbox is not visble on my firm profile page");
					}

					if (sendKeys(driver, ip.getMinInvestmentTextbox(60), "1234567890123456.12", "min investment size textbox firm profile page", action.SCROLLANDBOOLEAN)) {
						if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (ip.getMinInvestmentSizeValue(60).getText().trim().equals("$1234567890123456.12 Mn USD")) {
								appLog.info("min investment size value is successfully changed");
							}
							else {
								sa.assertTrue(false, "min investment size value could not be changed");
							}
						}
						else {
							sa.assertTrue(false, "save button is not clickable on my firm profile page");
						}
					}
					else {
						sa.assertTrue(false, "min investment textbox is not visble on my firm profile page");
					}
				}

					if (click(driver, ip.getEditIcon(60), "edit icon firm profile page", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getMaxInvestmentTextbox(60), "abc@123", "max investment textbox on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60), "save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getMaxInvestmentSizeError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidMaxInvestmentSize)) {
									appLog.info("max investment size error is successfully verified for abc@123");
								}
								else {
									sa.assertTrue(false, "max investment size error does not contain correct text for abc@123");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "max investment textbox is not visble on my firm profile page");
						}



						if (sendKeys(driver, ip.getMaxInvestmentTextbox(60), "abcde", "max investment textbox on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60), "save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getMaxInvestmentSizeError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidMaxInvestmentSize)) {
									appLog.info("max investment size error is successfully verified for abcde");
								}
								else {
									sa.assertTrue(false, "max investment size error does not contain correct text for abcde");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "max investment textbox is not visble on my firm profile page");
						}




						if (sendKeys(driver, ip.getMaxInvestmentTextbox(60), "!@#$%^&*((()&&", "max investment textbox on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60), "save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getMaxInvestmentSizeError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidMaxInvestmentSize)) {
									appLog.info("max investment size error is successfully verified for !@#$%^&*((()&&");
								}
								else {
									sa.assertTrue(false, "max investment size error does not contain correct text for !@#$%^&*((()&&");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "max investment textbox is not visble on my firm profile page");
						}




						if (sendKeys(driver, ip.getMaxInvestmentTextbox(60), "1234%&", "max investment textbox on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60), "save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getMaxInvestmentSizeError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidMaxInvestmentSize)) {
									appLog.info("max investment size error is successfully verified for 1234%&");
								}
								else {
									sa.assertTrue(false, "max investment size error does not contain correct text for 1234%&");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "max investment textbox is not visble on my firm profile page");
						}

						if (sendKeys(driver, ip.getMaxInvestmentTextbox(60), "abced&&", "max investment textbox on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60), "save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getMaxInvestmentSizeError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidMaxInvestmentSize)) {
									appLog.info("max investment size error is successfully verified for abced&&");
								}
								else {
									sa.assertTrue(false, "max investment size error does not contain correct text for abced&&");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "max investment textbox is not visble on my firm profile page");
						}


						if (sendKeys(driver, ip.getMaxInvestmentTextbox(60), "10000", "max investment size textbox firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getMaxInvestmentSizeValue(60).getText().trim().equals("$10000.00 Mn USD")) {
									appLog.info("max investment size value is successfully changed");
								}
								else {
									sa.assertTrue(false, "max investment size value could not be changed");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "max investment textbox is not visble on my firm profile page");
						}

					}
					if (click(driver, ip.getEditIcon(60), "edit icon on my firm profile page", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getMaxInvestmentTextbox(60), "155100.25", "max investment size textbox firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getMaxInvestmentSizeValue(60).getText().trim().equals("$155100.25 Mn USD")) {
									appLog.info("max investment size value is successfully changed");
								}
								else {
									sa.assertTrue(false, "max investment size value could not be changed");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "max investment textbox is not visble on my firm profile page");
						}

					}

					if (click(driver, ip.getEditIcon(60), "edit icon on my firm profile page", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getMaxInvestmentTextbox(60), "155100.258", "max investment size textbox firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getMaxInvestmentSizeError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidMaxInvestmentSize)) {
									appLog.info("max investment size value is successfully changed");
								}
								else {
									sa.assertTrue(false, "max investment size value could not be changed");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "max investment textbox is not visble on my firm profile page");
						}

						if (sendKeys(driver, ip.getMaxInvestmentTextbox(60), "123456789012345678", "max investment size textbox firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getMaxInvestmentSizeError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidMaxInvestmentSize)) {
									appLog.info("max investment size value is successfully changed");
								}
								else {
									sa.assertTrue(false, "max investment size value could not be changed");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "max investment textbox is not visble on my firm profile page");
						}

						if (sendKeys(driver, ip.getMaxInvestmentTextbox(60), "1234567890123456.12", "max investment size textbox firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getMaxInvestmentSizeValue(60).getText().trim().equals("$1234567890123456.12 Mn USD")) {
									appLog.info("max investment size value is successfully changed");
								}
								else {
									sa.assertTrue(false, "max investment size value could not be changed");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "max investment textbox is not visble on my firm profile page");
						}

					}
					//aum textbox

					if (click(driver, ip.getEditIcon(60), "edit icon firm profile page", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getAUMtextbox(60), "abc@123", "aum textbox on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60), "save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getPleaseEnterValidAUMError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidAUM)) {
									appLog.info("aum error is successfully verified for abc@123");
								}
								else {
									sa.assertTrue(false, "aum error does not contain correct text for abc@123");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "aum textbox is not visble on my firm profile page");
						}




						if (sendKeys(driver, ip.getAUMtextbox(60), "abcde", "aum textbox on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60), "save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getPleaseEnterValidAUMError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidAUM)) {
									appLog.info("aum error is successfully verified for abcde");
								}
								else {
									sa.assertTrue(false, "aum error does not contain correct text for abcde");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "aum textbox is not visble on my firm profile page");
						}



						if (sendKeys(driver, ip.getAUMtextbox(60), "!@#$%^&*((()&&", "aum textbox on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60), "save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getPleaseEnterValidAUMError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidAUM)) {
									appLog.info("aum error is successfully verified for !@#$%^&*((()&&");
								}
								else {
									sa.assertTrue(false, "aum error does not contain correct text for !@#$%^&*((()&&");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "aum textbox is not visble on my firm profile page");
						}



						if (sendKeys(driver, ip.getAUMtextbox(60), "1234%&", "aum textbox on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60), "save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getPleaseEnterValidAUMError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidAUM)) {
									appLog.info("aum error is successfully verified for 1234%&");
								}
								else {
									sa.assertTrue(false, "aum error does not contain correct text for 1234%&");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "aum textbox is not visble on my firm profile page");
						}
						if (sendKeys(driver, ip.getAUMtextbox(60), "abced&&", "aum textbox on my firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60), "save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getPleaseEnterValidAUMError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidAUM)) {
									appLog.info("aum error is successfully verified for abced&&");
								}
								else {
									sa.assertTrue(false, "aum error does not contain correct text for abced&&");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "aum textbox is not visble on my firm profile page");
						}

						if (sendKeys(driver, ip.getAUMtextbox(60), "10000", "aum size textbox firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getAumValue(60).getText().trim().equals("$10000.00 Mn USD")) {
									appLog.info("aum value is successfully changed");
								}
								else {
									sa.assertTrue(false, "aum value could not be changed");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "aum textbox is not visble on my firm profile page");
						}
					}
					if (click(driver, ip.getEditIcon(60), "edit icon on my firm profile page", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getAUMtextbox(60), "155100.25", "aum size textbox firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getAumValue(60).getText().trim().equals("$155100.25 Mn USD")) {
									appLog.info("aum value is successfully changed");
								}
								else {
									sa.assertTrue(false, "aum value could not be changed");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "aum textbox is not visible on my firm profile page");
						}
					}
					else {
						sa.assertTrue(false, "edit icon is not clickable on my firm profile page");
					}

					if (click(driver, ip.getEditIcon(60), "edit icon on my firm profile page", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getAUMtextbox(60), "155100.258", "aum textbox firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getPleaseEnterValidAUMError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidAUM)) {
									appLog.info("aum value is successfully changed");
								}
								else {
									sa.assertTrue(false, "aum value could not be changed");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "aum textbox is not visible on my firm profile page");
						}
						if (sendKeys(driver, ip.getAUMtextbox(60), "123456789012345678", "aum textbox firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getPleaseEnterValidAUMError(60).getText().trim().equals(InvestorProfileErrorsPage.pleaseEnterValidAUM)) {
									appLog.info("aum value is successfully changed");
								}
								else {
									sa.assertTrue(false, "aum value could not be changed");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "aum textbox is not visible on my firm profile page");
						}
						if (sendKeys(driver, ip.getAUMtextbox(60), "1234567890123456.12", "aum textbox firm profile page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ip.getSaveButtonFirmProfile(60),"save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getAumValue(60).getText().trim().equals("$1234567890123456.12 Mn USD")) {
									appLog.info("aum value is successfully changed");
								}
								else {
									sa.assertTrue(false, "aum value could not be changed");
								}
							}
							else {
								sa.assertTrue(false, "save button is not clickable on my firm profile page");
							}
						}
							else {
								sa.assertTrue(false, "aum textbox is not visible on my firm profile page");
							}
						}
					else {
						sa.assertTrue(false, "edit icon is not clickable on my firm profile page");
					}

				}
			else {
				sa.assertTrue(false, "my firm profile tab is not clickable on investor login page");
			}
			}
		else {
			sa.assertTrue(false, "my profile link is not clickable on investor login page");
		}
		lp.investorLogout();
		sa.assertAll();
		}
	@Test
	public void M2tc028_MaxCharacterLimit() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String M2Contact1EmailID = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_EmailId);
		String cont_password_updated = ExcelUtils.readData("Contacts",excelLabel.Variable_Name, "M2Contact1", excelLabel.Contact_password_updated);
		
		String manyChars = ExcelUtils.readData("SpecialChar",7,10);
		String normalChars = "TestUserDescription1";
		lp.investorLogin(M2Contact1EmailID, cont_password_updated);
		if (click(driver, af.getProfileLink(60), "profile link on investor login", action.SCROLLANDBOOLEAN)) {
			if (click(driver, ip.getMyFirmProfileTab(60), "my firm profile tab on investor login", action.SCROLLANDBOOLEAN)) {
				if (click(driver, ip.getEditIcon(60),"edit icon on firm profile page" ,action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, ip.getDescriptionTextArea(60), normalChars, "description text area", action.SCROLLANDBOOLEAN)) {
						if (click(driver, ip.getSaveButtonFirmProfile(60), "save button firm profile page", action.SCROLLANDBOOLEAN)) {
							if (ip.getDescriptionValue(60).getText().trim().equals(normalChars)) {
								appLog.info("correct description text has been successfully verified on firm profile page");
							}
							else {
								sa.assertTrue(false, "correct description text is not present on firm profile page");
							}
							if (click(driver, ip.getEditIcon(60),"edit icon on firm profile page" ,action.SCROLLANDBOOLEAN)) {
								if (sendKeys(driver, ip.getDescriptionTextArea(60),manyChars+manyChars,"description area textbox on firm profile page", action.SCROLLANDBOOLEAN)) {
									if (click(driver,ip.getSaveButtonFirmProfile(60), "save button on my firm profile page", action.SCROLLANDBOOLEAN)) {
								if (ip.getDescriptionError(60).getText().trim().equals(InvestorProfileErrorsPage.descriptionError)) {
									appLog.info("description limited exceeded error message is successfully verified");
								}
								else {

								}
								
								if (click(driver,ip.getCancelButtonFirmProfile(60), "cancel button on firm profile page", action.SCROLLANDBOOLEAN)) {
									if (click(driver, ip.getMyProfileTab(60), "my profile tab on my firm profile page", action.SCROLLANDBOOLEAN)) {
										if (click(driver, ip.getEditIcon(60), "edit icon on my profile page", action.SCROLLANDBOOLEAN)) {
											if (sendKeys(driver, ip.getFirstNameTextBox(60), "Rename", "First name textbox in my profile page", action.SCROLLANDBOOLEAN)) {
												if (sendKeys(driver,ip.getLastNameTextBox(60), "Investor", "Last name textbox in my profile page", action.SCROLLANDBOOLEAN)) {
													if (click(driver,ip.getSaveButtonMyProfilePage(60), "save button on my profile page", action.SCROLLANDBOOLEAN)) {
														if (click(driver, ip.getMyFirmProfileTab(60), "firm profile tab on my profile page", action.BOOLEAN)) {
																 SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
																    Date date = new Date();  
																    String lastUpdateDate[] = ip.getLastModifiedText(60).getText().split(" ");
																    if (bp.verifyDate(lastUpdateDate[3], "last updated date")) {
																    	appLog.info("correct date is successfully found in last updated message");
																    	if (ip.getLastModifiedText(60).getText().contains("Last Updated on ")){
																    		appLog.info("last updated on message is successfully found");
																    		if (ip.getLastModifiedText(60).getText().contains(" by Rename Investor")) {
																    			appLog.info("changed name of investor is successfully found in updated message");
																    		}
																    		else {
																    			sa.assertTrue(false, "new name of investor is not present in last updated message");
																    		}
																    	}
																    	else {
																    		sa.assertTrue(false, "last updated on mesage is not correct");
																    	}
																    	
																    }
																    else {
																    	sa.assertTrue(false, "last updated date is not correct");
																    }
														}

														else {
															sa.assertTrue(false, "my firm profile tab is not clickable from firm profile page");
														}
													}
													else {
														sa.assertTrue(false, "save button is not clickable on my firm profile page");
													}
												}
												else {
													sa.assertTrue(false, "last name textbox is not visible on my firm profile page");
												}
											}
											else {
												sa.assertTrue(false, "first name textbox is not visible on my firm profile page");
											}
										}
										else {
											sa.assertTrue(false, "edit icon is not clickable on my firm profile page");
										}
									}
									else {
										sa.assertTrue(false, "my profile link is not clickable on my firm profile page");
									}
								}
								else {
									sa.assertTrue(false, "cancel button is not clickable on my firm profile page");
								}
							}
									else {
										sa.assertTrue(false, "save button is not clickable on my firm profile page");
									}
								}
								else {
									sa.assertTrue(false, "description text area is not visible on my firm profile page");
								}
							}
							else {
								sa.assertTrue(false, "edit button is not clickable on my firm profile page");
							}
						}
						else {
							sa.assertTrue(false, "save button is not clickable on profile page");
						}
					}
					else {
						sa.assertTrue(false, "description text area is not visible on my firm profile page");
					}
				}
				else {
					sa.assertTrue(false, "edit icon is not clickable on my firm profile page");
				}
			}
			else {
				sa.assertTrue(false, "my firm profile tab is not clickable on investor login");
			}
		}
		else {
			sa.assertTrue(false, "profile link is not clickable on investor login");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	}		


