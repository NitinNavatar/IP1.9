/**
 * 
 */
package com.navatar.scripts;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.navatar.generic.BaseLib;
import com.navatar.generic.EmailLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.CommonLib.SortOrder;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.generic.CommonLib.sideMenu;
import com.navatar.generic.CommonVariables;
import com.navatar.pageObjects.BasePageBusinessLayer;
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
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.NIMPageBusinessLayer;
import com.navatar.pageObjects.NIMPageErrorMessage;
import com.navatar.pageObjects.NavatarInvestorAddOnsErrorMessage;
import com.navatar.pageObjects.NavatarInvestorAddonsPageBusinessLayer;
import com.navatar.pageObjects.PartnershipPageBusinessLayer;
import static com.navatar.generic.CommonLib.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static com.navatar.generic.CommonVariables.*;

/**
 * @author Parul Singh
 *
 */
public class Module1 extends BaseLib {
	String passwordResetLink = null;

	
	@Test
	public void M1tc001_CreateCRMUser1InstallPackageAndThenCreatePassword() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		List<String> lst=new ArrayList<String>();
		lp.CRMLogin(superAdminUserName, adminPassword);
		String[] splitedUserName = removeNumbersFromString(CRMUser1LastName); 
		String UserLastName =splitedUserName[0]+bp.generateRandomNumber();
		ExcelUtils.writeData(UserLastName, "Users", excelLabel.Variable_Name, "User1", excelLabel.User_Last_Name);
		cv = new CommonVariables(this);
		if(bp.removeUnusedTabs()){
			appLog.info("Unused tabs remove successfully");
		}else{
			appLog.info("Unused tabs not removed successfully");
			saa.assertTrue(false, "Unused tabs not removed successfully");
		}
		WebElement ele=FindElement(driver, "//a[contains(@title,'Partnerships')]", "Partnerships tab",
				action.SCROLLANDBOOLEAN, 10);
		if(ele==null){
		lst=bp.addRemoveCustomTab("Partnerships", customTabActionType.Add);
			if(!lst.isEmpty()){
				for (int i = 0; i < lst.size(); i++) {
					appLog.error(lst.get(i));
					saa.assertTrue(false,lst.get(i));
				}
			}
		}
		ele=FindElement(driver, "//a[contains(@title,'Commitments')]", "Commitments tab",
				action.SCROLLANDBOOLEAN, 10);
		if(ele==null){
			lst=bp.addRemoveCustomTab("Commitments", customTabActionType.Add);
			if(!lst.isEmpty()){
				for (int i = 0; i < lst.size(); i++) {
					appLog.error(lst.get(i));
					saa.assertTrue(false,lst.get(i));
				}
			}
		}
		if (bp.createPEUser(CRMUser1FirstName, UserLastName, cp.generateRandomEmailId(), CRMUserLicense,
				CRMUserProfile)) {
			appLog.info("PE User 1 created Successfully");
			String emailID = isDisplayed(driver, bp.getUserEmailIDLabeltext(60), "visibility", 60,
					"User Email ID Text Label", action.THROWEXCEPTION).getText().trim();
			ExcelUtils.writeData(emailID, "Users", excelLabel.Variable_Name, "User1", excelLabel.User_Email);
		} else {
			appLog.info("PE User1 is not created successfully");
			saa.assertTrue(false, "PE User1 is not created successfully");
		}
		if (bp.installedPackages(CRMUser1FirstName, CRMUser1LastName)) {
			appLog.info("Install package is done for PE User 1 succesfully");
		} else {
			appLog.info("Install package is not done for PE User 1 succesfully");
			saa.assertTrue(false, "Install package is not done for PE User 1 succesfully");
		}
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		bp = new BasePageBusinessLayer(driver);
		try {
			passwordResetLink = new EmailLib().getResetPasswordLink("passwordreset",
					ExcelUtils.readDataFromPropertyFile("gmailUserName"),
					ExcelUtils.readDataFromPropertyFile("gmailPassword"));
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
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
		if (bp.getSalesForceLightingIcon(10) != null) {
			ThreadSleep(2000);
			click(driver, bp.getSalesForceLightingIcon(60), "sales force lighting icon", action.THROWEXCEPTION);
			ThreadSleep(1000);
			click(driver, bp.getSwitchToClassic(60), "sales force switch to classic link", action.THROWEXCEPTION);
			appLog.info("Sales Force is switched in classic mode successfully.");
		} else {
			appLog.info("Sales Force is open in classic mode.");
		}
		if(bp.removeUnusedTabs()){
			appLog.info("Unused tabs remove successfully");
		}else{
			appLog.info("Unused tabs not removed successfully");
			saa.assertTrue(false, "Unused tabs not removed successfully");
		}
		 ele=FindElement(driver, "//a[contains(@title,'Partnerships')]", "Partnerships tab",
				action.SCROLLANDBOOLEAN, 10);
		if(ele==null){
		lst=bp.addRemoveCustomTab("Partnerships", customTabActionType.Add);
			if(!lst.isEmpty()){
				for (int i = 0; i < lst.size(); i++) {
					appLog.error(lst.get(i));
					saa.assertTrue(false,lst.get(i));
				}
			}
		}
		ele=FindElement(driver, "//a[contains(@title,'Commitments')]", "Commitments tab",
				action.SCROLLANDBOOLEAN, 10);
		if(ele==null){
			lst=bp.addRemoveCustomTab("Commitments", customTabActionType.Add);
			if(!lst.isEmpty()){
				for (int i = 0; i < lst.size(); i++) {
					appLog.error(lst.get(i));
					saa.assertTrue(false,lst.get(i));
				}
			}
		}
		if (bp.getNavatarInvestorManagerTab(20) == null) {
			lst=bp.addRemoveCustomTab("Navatar Investor Manager", customTabActionType.Add);
			if(!lst.isEmpty()){
				for (int i = 0; i < lst.size(); i++) {
					appLog.error(lst.get(i));
					saa.assertTrue(false,lst.get(i));
				}
			}
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
		saa.assertTrue(userName.contains(CRMUser1LastName), "PE User name is  not verified.");
		ThreadSleep(1000);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc002_CreateCRMUser2InstallPackageAndThenCreatePassword() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		List<String> lst=new ArrayList<String>();
		lp.CRMLogin(superAdminUserName, adminPassword);
		String[] splitedUserName = removeNumbersFromString(CRMUser2LastName); 
		String UserLastName =splitedUserName[0]+bp.generateRandomNumber();
		ExcelUtils.writeData(UserLastName, "Users", excelLabel.Variable_Name, "User2", excelLabel.User_Last_Name);
		cv = new CommonVariables(this);
		if (bp.createPEUser(CRMUser2FirstName, UserLastName, cp.generateRandomEmailId(), CRMUserLicense,
				CRMUserProfile)) {
			appLog.info("PE User 2 created Successfully");
			String emailID = isDisplayed(driver, bp.getUserEmailIDLabeltext(60), "visibility", 60,
					"User Email ID Text Label", action.THROWEXCEPTION).getText().trim();
			ExcelUtils.writeData(emailID, "Users", excelLabel.Variable_Name, "User2", excelLabel.User_Email);
		} else {
			appLog.info("PE User2 is not created successfully");
			saa.assertTrue(false, "PE User2 is not created successfully");
		}
		if (bp.installedPackages(CRMUser2FirstName, CRMUser2LastName)) {
			appLog.info("Install package is done for PE User 2 succesfully");
		} else {
			appLog.info("Install package is not done for PE User 2 succesfully");
			saa.assertTrue(false, "Install package is not done for PE User 2 succesfully");
		}
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		bp = new BasePageBusinessLayer(driver);
		try {
			passwordResetLink = new EmailLib().getResetPasswordLink("passwordreset",
					ExcelUtils.readDataFromPropertyFile("gmailUserName"),
					ExcelUtils.readDataFromPropertyFile("gmailPassword"));
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		appLog.info("ResetLinkIs: " + passwordResetLink);
		driver.get(passwordResetLink);
		if (bp.setNewPassword()) {
			appLog.info("Password is set successfully for user2");
		} else {
			appLog.info("Password is not set for user2");
			saa.assertTrue(false, "Password is not set for user2");
		}
		if (bp.getSalesForceLightingIcon(10) != null) {
			ThreadSleep(2000);
			click(driver, bp.getSalesForceLightingIcon(60), "sales force lighting icon", action.THROWEXCEPTION);
			ThreadSleep(1000);
			click(driver, bp.getSwitchToClassic(60), "sales force switch to classic link", action.THROWEXCEPTION);
			appLog.info("Sales Force is switched in classic mode successfully.");
		} else {
			appLog.info("Sales Force is open in classic mode.");
		}
		if(bp.removeUnusedTabs()){
			appLog.info("Unused tabs remove successfully");
		}else{
			appLog.info("Unused tabs not removed successfully");
			saa.assertTrue(false, "Unused tabs not removed successfully");
		}
		WebElement ele=FindElement(driver, "//a[contains(@title,'Partnerships')]", "Partnerships tab",
				action.SCROLLANDBOOLEAN, 10);
		if(ele==null){
		lst=bp.addRemoveCustomTab("Partnerships", customTabActionType.Add);
			if(!lst.isEmpty()){
				for (int i = 0; i < lst.size(); i++) {
					appLog.error(lst.get(i));
					saa.assertTrue(false,lst.get(i));
				}
			}
		}
		ele=FindElement(driver, "//a[contains(@title,'Commitments')]", "Commitments tab",
				action.SCROLLANDBOOLEAN, 10);
		if(ele==null){
			lst=bp.addRemoveCustomTab("Commitments", customTabActionType.Add);
			if(!lst.isEmpty()){
				for (int i = 0; i < lst.size(); i++) {
					appLog.error(lst.get(i));
					saa.assertTrue(false,lst.get(i));
				}
			}
		}
		if (bp.getNavatarInvestorManagerTab(20) == null) {
			lst=bp.addRemoveCustomTab("Navatar Investor Manager", customTabActionType.Add);
			if(!lst.isEmpty()){
				for (int i = 0; i < lst.size(); i++) {
					appLog.error(lst.get(i));
					saa.assertTrue(false,lst.get(i));
				}
			}
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
		saa.assertTrue(userName.contains(CRMUser2LastName), "PE User name is  not verified.");
		ThreadSleep(1000);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc003_CreateCRMUser3InstallPackageAndThenCreatePassword() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		List<String> lst=new ArrayList<String>();
		lp.CRMLogin(superAdminUserName, adminPassword);
		String[] splitedUserName = removeNumbersFromString(CRMUser3LastName); 
		String UserLastName =splitedUserName[0]+bp.generateRandomNumber();
		ExcelUtils.writeData(UserLastName, "Users", excelLabel.Variable_Name, "User3", excelLabel.User_Last_Name);
		cv = new CommonVariables(this);
		if (bp.createPEUser(CRMUser3FirstName, UserLastName, cp.generateRandomEmailId(), CRMUserLicense,
				CRMUserProfile)) {
			appLog.info("PE User 3 created Successfully");
			String emailID = isDisplayed(driver, bp.getUserEmailIDLabeltext(60), "visibility", 60,
					"User Email ID Text Label", action.THROWEXCEPTION).getText().trim();
			ExcelUtils.writeData(emailID, "Users", excelLabel.Variable_Name, "User3", excelLabel.User_Email);
		} else {
			appLog.info("PE User3 is not created successfully");
			saa.assertTrue(false, "PE User3 is not created successfully");
		}
		if (bp.installedPackages(CRMUser3FirstName, CRMUser3LastName)) {
			appLog.info("Install package is done for PE User 3 succesfully");
		} else {
			appLog.info("Install package is not done for PE User 3 succesfully");
			saa.assertTrue(false, "Install package is not done for PE User 3 succesfully");
		}
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		bp = new BasePageBusinessLayer(driver);
		try {
			passwordResetLink = new EmailLib().getResetPasswordLink("passwordreset",
					ExcelUtils.readDataFromPropertyFile("gmailUserName"),
					ExcelUtils.readDataFromPropertyFile("gmailPassword"));
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		appLog.info("ResetLinkIs: " + passwordResetLink);
		driver.get(passwordResetLink);
		if (bp.setNewPassword()) {
			appLog.info("Password is set successfully for user3");
		} else {
			appLog.info("Password is not set for user3");
			saa.assertTrue(false, "Password is not set for user3");
		}
		if (bp.getSalesForceLightingIcon(10) != null) {
			ThreadSleep(2000);
			click(driver, bp.getSalesForceLightingIcon(60), "sales force lighting icon", action.THROWEXCEPTION);
			ThreadSleep(1000);
			click(driver, bp.getSwitchToClassic(60), "sales force switch to classic link", action.THROWEXCEPTION);
			appLog.info("Sales Force is switched in classic mode successfully.");
		} else {
			appLog.info("Sales Force is open in classic mode.");
		}
		if(bp.removeUnusedTabs()){
			appLog.info("Unused tabs remove successfully");
		}else{
			appLog.info("Unused tabs not removed successfully");
			saa.assertTrue(false, "Unused tabs not removed successfully");
		}
		WebElement ele=FindElement(driver, "//a[contains(@title,'Partnerships')]", "Partnerships tab",
				action.SCROLLANDBOOLEAN, 10);
		if(ele==null){
		lst=bp.addRemoveCustomTab("Partnerships", customTabActionType.Add);
			if(!lst.isEmpty()){
				for (int i = 0; i < lst.size(); i++) {
					appLog.error(lst.get(i));
					saa.assertTrue(false,lst.get(i));
				}
			}
		}
		ele=FindElement(driver, "//a[contains(@title,'Commitments')]", "Commitments tab",
				action.SCROLLANDBOOLEAN, 10);
		if(ele==null){
			lst=bp.addRemoveCustomTab("Commitments", customTabActionType.Add);
			if(!lst.isEmpty()){
				for (int i = 0; i < lst.size(); i++) {
					appLog.error(lst.get(i));
					saa.assertTrue(false,lst.get(i));
				}
			}
		}
		if (bp.getNavatarInvestorManagerTab(20) == null) {
			lst=bp.addRemoveCustomTab("Navatar Investor Manager", customTabActionType.Add);
			if(!lst.isEmpty()){
				for (int i = 0; i < lst.size(); i++) {
					appLog.error(lst.get(i));
					saa.assertTrue(false,lst.get(i));
				}
			}
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
		saa.assertTrue(userName.contains(CRMUser3LastName), "PE User name is  not verified.");
		ThreadSleep(1000);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc004_1_CreatePrecondition() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		List<String> lst=new ArrayList<String>();
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
	if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.createInstitution(M1Institution1)) {
				appLog.info("Institution is created successfully");
			} else {
				appLog.info("Institution is not created successfully");
				saa.assertTrue(false, "Institution is not created successfully");
			}
		} else {
			appLog.info("Not able to click on institution tab so we cannot craete institution");
			saa.assertTrue(false, "Not able to click on institution tab so we cannot craete institution");
		}
		scrollDownThroughWebelement(driver, bp.getUserNameAtUserMenuTab(60), "User Name");
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.createLimitedPartner(M1LimitedPartner1, M1Institution1)) {
				appLog.info("Limited Partner is created successfully");
			} else {
				appLog.info("Limited Partner is not created successfully");
				saa.assertTrue(false, "Limited Partner is not created successfully");
			}
		} else {
			appLog.info("Not able to click on institution tab so we cannot craete institution");
			saa.assertTrue(false, "Not able to click on institution tab so we cannot craete institution");
		}
		if (bp.clickOnTab(TabName.ContactTab)) {
			String emailId = cp.generateRandomEmailId();
			if (cp.createContact(M1Contact1FirstName, M1Contact1LastName, M1Institution1, emailId)) {
				appLog.info("Contact is created successfully");
				ExcelUtils.writeData(emailId, "Contacts", excelLabel.Variable_Name, "M1Contact1",
						excelLabel.Contact_EmailId);
			} else {
				appLog.info("Contact is not created successfully");
				saa.assertTrue(false, "Contact is not created successfully");
			}
		} else {
			appLog.info("Not able to click on Contact tab so we cannot create Contact");
			saa.assertTrue(false, "Not able to click on Contact tab so we cannot create Contact");
		}
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.createFund(M1FundName1, M1FundType1, M1FundInvestmentCategory1)) {
				appLog.info("Fund is created successfully");
			} else {
				appLog.info("Fund is not created successfully");
				saa.assertTrue(false, "Fund is not created successfully");
			}
		} else {
			appLog.info("Not able to click on Fund tab so we cannot create Fund");
			saa.assertTrue(false, "Not able to click on Fund tab so we cannot create Fund");
		}
		if (bp.clickOnTab(TabName.FundraisingsTab)) {
			if (frp.createFundRaising(M1FundRaisingName1, M1FundName1, M1Institution1)) {
				appLog.info("FundRaising is created successfully");
			} else {
				appLog.info("FundRaising is not created successfully");
				saa.assertTrue(false, "FundRaising is not created successfully");
			}
		} else {
			appLog.info("Not able to click on FundRaising tab so we cannot create FundRaising");
			saa.assertTrue(false, "Not able to click on FundRaising tab so we cannot create FundRaising");
		}
		WebElement ele=FindElement(driver, "//a[contains(@title,'Partnerships')]", "Partnerships tab",
				action.SCROLLANDBOOLEAN, 10);
		if(ele==null){
		lst=bp.addRemoveCustomTab("Partnerships", customTabActionType.Add);
			if(!lst.isEmpty()){
				for (int i = 0; i < lst.size(); i++) {
					appLog.error(lst.get(i));
					saa.assertTrue(false,lst.get(i));
				}
			}
			if (bp.clickOnTab(TabName.PartnershipsTab)) {
				if (pp.createPartnership(M1Partnership1, M1FundName1)) {
					appLog.info("Partnership is created successfully");
				} else {
					appLog.info("Partnership is not created successfully");
					saa.assertTrue(false, "Partnership is not created successfully");
				}
			} else {
				appLog.info("Not able to click on Partnership tab so we cannot create Partnership");
				saa.assertTrue(false, "Not able to click on Partnership tab so we cannot create Partnership");
			}
		}else{
		if (bp.clickOnTab(TabName.PartnershipsTab)) {
			if (pp.createPartnership(M1Partnership1, M1FundName1)) {
				appLog.info("Partnership is created successfully");
			} else {
				appLog.info("Partnership is not created successfully");
				saa.assertTrue(false, "Partnership is not created successfully");
			}
		} else {
			appLog.info("Not able to click on Partnership tab so we cannot create Partnership");
			saa.assertTrue(false, "Not able to click on Partnership tab so we cannot create Partnership");
		}
		}
		ele=FindElement(driver, "//a[contains(@title,'Commitments')]", "Commitments tab",
				action.SCROLLANDBOOLEAN, 10);
		if(ele==null){
			lst=bp.addRemoveCustomTab("Commitments", customTabActionType.Add);
			if(!lst.isEmpty()){
				for (int i = 0; i < lst.size(); i++) {
					appLog.error(lst.get(i));
					saa.assertTrue(false,lst.get(i));
				}
			}
			ThreadSleep(1000);
			 if (bp.clickOnTab(TabName.CommitmentsTab)) {
				if (cmp.createCommitment(M1LimitedPartner1, M1Partnership1, "M1Commitment1", null)) {
					appLog.info("Commitment is created successfully");
				} else {
					appLog.info("Commitment is not created successfully");
					saa.assertTrue(false, "Commitment is not created successfully");
				}
			} else {
				appLog.info("Not able to click on Commitment tab so we cannot create Commitment");
				saa.assertTrue(false, "Not able to click on Commitment tab so we cannot create Commitment");
			}
	
		}else{		
		if (bp.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.createCommitment(M1LimitedPartner1, M1Partnership1, "M1Commitment1", null)) {
				appLog.info("Commitment is created successfully");
			} else {
				appLog.info("Commitment is not created successfully");
				saa.assertTrue(false, "Commitment is not created successfully");
			}
		} else {
			appLog.info("Not able to click on Commitment tab so we cannot create Commitment");
			saa.assertTrue(false, "Not able to click on Commitment tab so we cannot create Commitment");
		}
		}
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc004_2_CheckErrorMessagesForAdminBeforeRegistration() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		HomePageBusineesLayer hp = new HomePageBusineesLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer naop = new NavatarInvestorAddonsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(superAdminUserName, adminPassword);
		scrollDownThroughWebelement(driver, hp.getNavatarInvestorActivitiesLabel(60),
				"Navatar Investor Manager Activitis label");
		ThreadSleep(2000);
		switchToFrame(driver, 60, hp.getHomePageAlertsFrame(60));
		if (bp.verifyErrorMessageOnPage(HomePageErrorMessage.errorMessageBeforeAdminRegistration,
				hp.getErrorMessageBeforeAdminRegistration(60), "Error Message before admin Registration")) {
			appLog.info("Error Message is verified at home page");
		} else {
			saa.assertTrue(false,
					"Error Message is not verified at home page.Expected:"
							+ HomePageErrorMessage.errorMessageBeforeAdminRegistration + " Actual"
							+ getText(driver, hp.getErrorMessageBeforeAdminRegistration(60),
									"Error Message before admin registration", action.BOOLEAN));
		}
		switchToDefaultContent(driver);
		scrollDownThroughWebelement(driver, ip.getInstitutionsTab(60), "Institution Tab");
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M1Institution1)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, ip.getWorkspaceFrame(60));
				if (bp.verifyErrorMessageOnPage(InstitutionPageErrorMessage.errorMessageBeforeAdminRegistration,
						bp.getErrorMessageOnAllPages(60).get(0),
						"Error Message before admin Registration on Instituition page")) {
					appLog.info("Error Message is verified  on Instituition page");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Instituition page.Expected:"
									+ InstitutionPageErrorMessage.errorMessageBeforeAdminRegistration + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
											"Error Message before admin registration on instituition page",
											action.BOOLEAN));
				}
				if (bp.verifyErrorMessageOnPage(InstitutionPageErrorMessage.errorMessageBeforeAdminRegistration,
						bp.getErrorMessageOnAllPages(60).get(1),
						"Error Message before admin Registration on Instituition page for investor workspace")) {
					appLog.info("Error Message is verified  on Instituition page for investor workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Instituition page for investor workspace.Expected:"
									+ InstitutionPageErrorMessage.errorMessageBeforeAdminRegistration + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(1),
											"Error Message before admin registration on instituition page for investor workspace",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				saa.assertTrue(false, "Not able to click on created instituition");
			}
		} else {
			appLog.info("Not able to click on institution tab");
			saa.assertTrue(false, "Not able to click on institution tab");
		}
		scrollDownThroughWebelement(driver, cp.getContactsTab(60), "Contacts Tab");
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M1Contact1FirstName, M1Contact1LastName, null)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, cp.getWorkspaceFrameIncontactPage(60));
				if (bp.verifyErrorMessageOnPage(ContactPageErrorMessage.errorMessageBeforeAdminRegistration,
						bp.getErrorMessageOnAllPages(60).get(0),
						"Error Message before admin Registration on Contact page")) {
					appLog.info("Error Message is verified  on Contact page for fundraising workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Contact page for fundraising workspace.Expected:"
									+ ContactPageErrorMessage.errorMessageBeforeAdminRegistration + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
											"Error Message before admin registration on contact page", action.BOOLEAN));
				}
				if (bp.verifyErrorMessageOnPage(ContactPageErrorMessage.errorMessageBeforeAdminRegistration,
						bp.getErrorMessageOnAllPages(60).get(1),
						"Error Message before admin Registration on Contact page for investor workspace")) {
					appLog.info("Error Message is verified  on Contact page for investor workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Contact page for investor workspace.Expected:"
									+ ContactPageErrorMessage.errorMessageBeforeAdminRegistration + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(1),
											"Error Message before admin registration on Contact page for investor workspace",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				saa.assertTrue(false, "Not able to click on created contact");
			}
		} else {
			appLog.info("Not able to click on contacts tab so we cannot check error message on contact page");
			saa.assertTrue(false, "Not able to click on contacts tab so we cannot check error message on contact page");
		}
		scrollDownThroughWebelement(driver, bp.getFundsTab(60), "Funds Tab");
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M1FundName1)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, fp.getWorkspaceFrameOnFundsPage(60));
				if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.errorMessageBeforeAdminRegistration,
						bp.getErrorMessageOnAllPages(60).get(0),
						"Error Message before admin Registration on Funds page")) {
					appLog.info("Error Message is verified  on Funds page for fundraising workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Funds page for fundraising workspace.Expected:"
									+ FundsPageErrorMessage.errorMessageBeforeAdminRegistration + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
											"Error Message before admin registration on Funds page", action.BOOLEAN));
				}
				if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.errorMessageBeforeAdminRegistration,
						bp.getErrorMessageOnAllPages(60).get(1),
						"Error Message before admin Registration on Funds page for investor workspace")) {
					appLog.info("Error Message is verified  on Funds page for investor workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Funds page for investor workspace.Expected:"
									+ FundsPageErrorMessage.errorMessageBeforeAdminRegistration + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
											"Error Message before admin registration on Funds page for investor workspace",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Nota ble to click on funds tab so we cannot check error message on funds page");
			saa.assertTrue(false, "Nota ble to click on funds tab so we cannot check error message on funds page");
		}
		scrollDownThroughWebelement(driver, ip.getInstitutionsTab(60), "Institution Tab");
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedLP(M1LimitedPartner1)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, ip.getWorkspaceFrame(60));
				if (bp.verifyErrorMessageOnPage(InstitutionPageErrorMessage.errorMessageBeforeAdminRegistration,
						bp.getErrorMessageOnAllPages(60).get(0),
						"Error Message before admin Registration on Limited Partner page")) {
					appLog.info("Error Message is verified  on Limited Partner page");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Limited Partner page.Expected:"
									+ InstitutionPageErrorMessage.errorMessageBeforeAdminRegistration + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
											"Error Message before admin registration on Limited Partner page",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				appLog.info("Not able to click on created limited partner");
				saa.assertTrue(false, "Not able to click on created limited partner");
			}
		} else {
			appLog.info("Not able to click on institution tab");
			saa.assertTrue(false, "Not able to click on institution tab");
		}
		scrollDownThroughWebelement(driver, bp.getUserNameAtUserMenuTab(60), "User Name At User Menu Tab");
		if (bp.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.clickOnCreatedCommitmentId(M1CommitmentId)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, cmp.getWorkspaceFrameOnCommitmentPage(60));
				if (bp.verifyErrorMessageOnPage(CommitmentPageErrorMessage.errorMessageBeforeAdminRegistration,
						bp.getErrorMessageOnAllPages(60).get(0),
						"Error Message before admin Registration on commitments page")) {
					appLog.info("Error Message is verified  on commitments page");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Limited Partner page.Expected:"
									+ CommitmentPageErrorMessage.errorMessageBeforeAdminRegistration + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
											"Error Message before admin registration on commitments page",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				appLog.info("Not able to click on created commitment ID");
				saa.assertTrue(false, "Not able to click on created commitment ID");
			}
		} else {
			appLog.info("Not able to click on commitment tab so cannot check error message on Commitments page");
			saa.assertTrue(false,
					"Not able to click on commitment tab so cannot check error message on Commitments page");
		}
		if (bp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			switchToFrame(driver, 60, naop.getNavatarInvestorAddOnFrame(60));
			switchToFrame(driver, 30, naop.getNavatarInvestorAddOnFrame1(60));
			if (bp.verifyErrorMessageOnPage(NavatarInvestorAddOnsErrorMessage.errorMessageBeforeAdminRegistration,
					naop.getErrorMessageBeforeAdminRegistration(60), "Error Message before admin Registration")) {
				appLog.info("Error Message is verified at navatar investor add on page");
			} else {
				saa.assertTrue(false,
						"Error Message is not verified at navatar investor add on page.Expected:"
								+ NavatarInvestorAddOnsErrorMessage.errorMessageBeforeAdminRegistration + " Actual"
								+ getText(driver, naop.getErrorMessageBeforeAdminRegistration(60),
										"Error Message before admin registration", action.BOOLEAN));
			}
		} else {
			appLog.info(
					"Not able to click on Navatar investor Add ons tab so cannot check error message on Navatar investor Add ons  page");
			saa.assertTrue(false,
					"Not able to click on Navatar investor Add ons tab tab so cannot check error message on Navatar investor Add ons  page");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc005_VerifyRegisterForNavatarInvestorPopUpAtNIMTab() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		HomePageBusineesLayer hp = new HomePageBusineesLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (ExcelUtils.readData("Users", 1, 7).equalsIgnoreCase("Not Registered")) {
			if (bp.clickOnTab(TabName.NIMTab)) {
				switchToFrame(driver, 60, nim.getNIMTabFrame(60));
				ThreadSleep(2000);
				if (nim.getnIMRegisterPopupHeader(60) != null) {
					String NIMHeader = trim(getText(driver, nim.getnIMRegisterPopupHeader(60),
							"NIM Register popup header", action.BOOLEAN));
					if (NIMHeader.equalsIgnoreCase("Register for Navatar Investor")) {
						appLog.info("Register popup header is verified");
					} else {
						appLog.info("Register popup header is not verified");
						saa.assertTrue(false, "Register popup header is not verified");
					}
					if (isDisplayed(driver, nim.getStartButton(60), "Visibility", 60, "Start Button") != null) {
						appLog.info("Start Button is displaying on header popup");
					} else {
						appLog.info("Start Button is not displaying on header popup");
						saa.assertTrue(false, "Start Button is not displaying on header popup");
					}
					if (isDisplayed(driver, nim.getNimRegisterPopupCancelButton(60), "Visibility", 60,
							"Cancel Button") != null) {
						appLog.info("Cancel Button is displaying on header popup");
					} else {
						appLog.info("Cancel Button is not displaying on header popup");
						saa.assertTrue(false, "Cancel Button is not displaying on header popup");
					}
					if (isDisplayed(driver, nim.getNimRegisterPopupCloseIcon(60), "Visibility", 60,
							"Close Icon") != null) {
						appLog.info("Close Icon is displaying on header popup");
					} else {
						appLog.info("Close Icon is not displaying on header popup");
						saa.assertTrue(false, "Close Icon is not displaying on header popup");
					}
					if (click(driver, nim.getNavatarSalesTeamLink(60), "Navatar Sales Team Link",
							action.SCROLLANDBOOLEAN)) {
						if (!nim.verifyNavatarSalesTeamLinkFunctionality("NavatarSalesTeamLink")) {
							saa.assertTrue(false, "Verification of navatar sales team link is unsuccessfull.");
						} else {
							appLog.info("Verification of the Navatar Sales Team Link is successfull.");
						}
					} else {
						appLog.info("Not able to click on navatar sales team link");
						saa.assertTrue(false, "Not able to click on navatar sales team link");

					}
					if (click(driver, nim.getNimRegisterPopupCloseIcon(60), "Close Icon", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						if (isDisplayed(driver, hp.getDashboardLabelOnHomePage(60), "Visibility", 60,
								"Dashboard label on HomePage") != null) {
							appLog.info("User is redirected to home page successfully");
						} else {
							appLog.info("User is not redirected to home page");
							saa.assertTrue(false, "User is not redirected to home page");
						}
					} else {
						appLog.info("Not able to click on close icon");
						saa.assertTrue(false, "Not able to click on close icon");
					}

					if (bp.clickOnTab(TabName.NIMTab)) {
						switchToFrame(driver, 60, nim.getNIMTabFrame(60));
						if (click(driver, nim.getNimRegisterPopupCancelButton(60), "Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							if (isDisplayed(driver, hp.getDashboardLabelOnHomePage(60), "Visibility", 60,
									"Dashboard label on HomePage") != null) {
								appLog.info("User is redirected to home page successfully");
							} else {
								appLog.info("User is not redirected to home page");
								saa.assertTrue(false, "User is not redirected to home page");
							}
						} else {
							appLog.info("Not able to click on register popup cancel button");
							saa.assertTrue(false, "Not able to click on register popup cancel button");
						}
					} else {
						saa.assertTrue(false, "Not able to click on NIM tab");
					}
				} else {
					appLog.info("Register popup is not displaying");
					saa.assertTrue(false, "Register popup is not displaying");
				}
			} else {
				appLog.info("Not able to click on NIM tab so cannot verify register popup");
				saa.assertTrue(false, "Not able to click on NIM tab so cannot verify register popup");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.info("Super admin is already registered so we cannot verify register popup.");
			saa.assertTrue(false, "Super admin is already registered so we cannot verify register popup.");
		}
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc006_VerifyRegisterForNavatarInvestorStep1Of2PopUpAndErrorMessages() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		HomePageBusineesLayer hp = new HomePageBusineesLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (ExcelUtils.readData("Users", 1, 7).equalsIgnoreCase("Not Registered")) {
			if (bp.clickOnTab(TabName.NIMTab)) {
				switchToFrame(driver, 60, nim.getNIMTabFrame(60));
				if (click(driver, nim.getStartButton(60), "Start Button", action.SCROLLANDBOOLEAN)) {
					if (nim.getRegisterPopupHeaderStep1Of2(60) != null) {
						String headertext = trim(getText(driver, nim.getRegisterPopupHeaderStep1Of2(60),
								"Register Popup Header", action.SCROLLANDBOOLEAN));
						if (headertext.equalsIgnoreCase("Register for Navatar Investor (Step 1 of 2)")) {
							appLog.info("Header tetx is verified");
						} else {
							appLog.info("Header text is not verified");
							saa.assertTrue(false, "Header text is not verified");
						}
					} else {
						appLog.info("Register popup header is not displaying");
						saa.assertTrue(false, "Register popup header is not displaying");
					}
					if (isDisplayed(driver, nim.getNextButton(60), "visibility", 60, "Next button") != null) {
						appLog.info("Next Button is displaying");
					} else {
						appLog.info("Next button is not displaying");
						saa.assertTrue(false, "Next button is not displaying");
					}
					if (isDisplayed(driver, nim.getRegisterPopupBackButton(60).get(0), "visibility", 60,
							"Back button") != null) {
						appLog.info("Back Button is displaying");
					} else {
						appLog.info("Back button is not displaying");
						saa.assertTrue(false, "Back button is not displaying");
					}
					String firstName = getText(driver, nim.getRegisterPopupFirstName(60), "First Name",
							action.SCROLLANDBOOLEAN);
					if (firstName != null) {
						appLog.info("First Name field is prefilled");
					} else {
						appLog.info("First Name field is not prefilled");
						saa.assertTrue(false, "First Name field is not prefilled");
					}
					String lastName = getText(driver, nim.getRegisterPopupLastName(60), "Last Name",
							action.SCROLLANDBOOLEAN);
					if (lastName != null) {
						appLog.info("Last Name field is prefilled");
					} else {
						appLog.info("Last Name field is not prefilled");
						saa.assertTrue(false, "Last Name field is not prefilled");
					}
					String email = getText(driver, nim.getRegisterPopupEmail(60), "email", action.SCROLLANDBOOLEAN);
					if (email != null) {
						appLog.info("email field is prefilled");
					} else {
						appLog.info("email field is not prefilled");
						saa.assertTrue(false, "email field is not prefilled");
					}
					if (!isEnabled(driver, nim.getRegisterPopupEmail(60), "Email")) {
						appLog.info("Email field is not enabled");
					} else {
						appLog.error("Email field is enabled");
						saa.assertTrue(false, "Email field is enabled");
					}
					if (click(driver, nim.getRegisterPopup1Of2CloseIcon(60), "Close Icon", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						if (isDisplayed(driver, hp.getDashboardLabelOnHomePage(60), "Visibility", 60,
								"Dashboard label on HomePage") != null) {
							appLog.info("User is redirected to home page successfully");
						} else {
							appLog.info("User is not redirected to home page");
							saa.assertTrue(false, "User is not redirected to home page");
						}
					} else {
						appLog.info("Not able to click on close icon");
						saa.assertTrue(false, "Not able to click on close icon");
					}
					if (bp.clickOnTab(TabName.NIMTab)) {
						switchToFrame(driver, 60, nim.getNIMTabFrame(60));
						if (click(driver, nim.getStartButton(60), "Start Button", action.SCROLLANDBOOLEAN)) {
							if (click(driver, nim.getRegisterPopupBackButton(60).get(0), "Back Button",
									action.SCROLLANDBOOLEAN)) {
								if (nim.getnIMRegisterPopupHeader(60) != null) {
									appLog.info("Register popup header is displaying");
									if (click(driver, nim.getStartButton(60), "Start Button",
											action.SCROLLANDBOOLEAN)) {
										nim.getRegisterPopupFirstName(60).clear();
										nim.getRegisterPopupLastName(60).clear();
										if (click(driver, nim.getNextButton(60), "Next Button",
												action.SCROLLANDBOOLEAN)) {
											if (bp.verifyErrorMessageOnPage(
													NIMPageErrorMessage.lastNameErrorMessageOnRegisterPopup,
													nim.getRegisterPopupLastNameRequiresErrorMessage(60),
													"Last Name Error Message on Register Popup")) {
												appLog.info("Last Name Error Message on Register Popup is verified");
											} else {
												saa.assertTrue(false,
														"Last Name Error Message on Register Popup is not verified.Expected:"
																+ NIMPageErrorMessage.lastNameErrorMessageOnRegisterPopup
																+ " Actual"
																+ getText(driver,
																		nim.getRegisterPopupLastNameRequiresErrorMessage(
																				60),
																		"Last Name Error Message on Register Popup",
																		action.BOOLEAN));
											}

											if (sendKeys(driver, nim.getRegisterPopupFirstName(60),
													ExcelUtils.readData("SpecialChar", 2, 0), "First Name",
													action.SCROLLANDBOOLEAN)) {
												if (sendKeys(driver, nim.getRegisterPopupLastName(60),
														ExcelUtils.readData("SpecialChar", 2, 1), "Last Name",
														action.SCROLLANDBOOLEAN)) {
															ThreadSleep(5000);
													if (click(driver, nim.getNextButton(60), "Next Button",
															action.BOOLEAN)) {
														ThreadSleep(10000);
														String alertMessage = switchToAlertAndGetMessage(driver, 60,
																action.GETTEXT);
														if (alertMessage.equalsIgnoreCase(
																NIMPageErrorMessage.invalidNameErrorMessage)) {
															appLog.info("error message of alert is verified");
														} else {
															appLog.info("Error Message of alert is not verified");
															saa.assertTrue(false,
																	"Error Message of alert is not verified");
														}
														switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
														if (!isAlertPresent(driver)) {
															appLog.info("Alert popup get closed successfully");
														} else {
															appLog.info("Alert popup is not closed successfully");
															saa.assertTrue(false,
																	"Alert popup is not closed successfully");
														}

														if (sendKeys(driver, nim.getRegisterPopupFirstName(60),
																M1SuperAdminFirstName, "First Name",
																action.SCROLLANDBOOLEAN)) {
															if (sendKeys(driver, nim.getRegisterPopupLastName(60),
																	M1SuperAdminLastName, "Last Name",
																	action.SCROLLANDBOOLEAN)) {
																ThreadSleep(2000);
																if (click(driver, nim.getNextButton(60), "Next Button",
																		action.SCROLLANDBOOLEAN)) {
																	ThreadSleep(2000);
																	if (click(driver, nim.getDenyButton(60),
																			"Deny Button", action.SCROLLANDBOOLEAN)) {
																		if (bp.verifyErrorMessageOnPage(
																				NIMPageErrorMessage.problemLoggingInErrorMessage,
																				nim.getProblemLoggingInErrorMessage(60),
																				"Problem Logging Error Message")) {
																			appLog.info(
																					"Problem Logging Error Message on Register Popup is verified");
																		} else {
																			saa.assertTrue(false,
																					"Problem Logging Error Message on Register Popup is not verified.Expected:"
																							+ NIMPageErrorMessage.problemLoggingInErrorMessage
																							+ " Actual"
																							+ getText(driver,
																									nim.getProblemLoggingInErrorMessage(
																											60),
																									"Problem Logging Error Message",
																									action.BOOLEAN));
																		}
																		String accessdeniedtext = trim(getText(driver,
																				isDisplayed(driver,
																						nim.getDeniedAccessLabeltext(
																								60),
																						"visibility", 60,
																						"Remote Access denied Text."),
																				"Error Message",
																				action.SCROLLANDBOOLEAN));
																		saa.assertTrue(
																				accessdeniedtext.contains(
																						"Remote_Error: access_denied"),
																				"Remote_Error: access_denied is not matched");
																		driver.navigate().back();
																		driver.navigate().back();
																		switchToFrame(driver, 60,
																				nim.getNIMTabFrame(60));
																		if (nim.getStartButton(60) != null) {
																			appLog.info(
																					"User is redirected to Register for Navatar Investor pop up");
																			if (click(driver, nim.getStartButton(60),
																					"Start button",
																					action.SCROLLANDBOOLEAN)) {
																				String userfirstName = getAttribute(
																						driver,
																						nim.getRegisterPopupFirstName(
																								60),
																						"First name", "value");
																				if (userfirstName.equalsIgnoreCase(
																						M1SuperAdminFirstName)) {
																					appLog.info(
																							"Super admin first name is displaying");
																				} else {
																					appLog.info(
																							"Super admin first name is not displaying");
																					saa.assertTrue(false,
																							"Super admin first name is not displaying");
																				}
																				String userlastName = getAttribute(
																						driver,
																						nim.getRegisterPopupLastName(
																								60),
																						"Last name", "value");
																				if (userlastName.equalsIgnoreCase(
																						M1SuperAdminLastName)) {
																					appLog.info(
																							"Super admin last name is displaying");
																				} else {
																					appLog.info(
																							"Super admin last name is not displaying");
																					saa.assertTrue(false,
																							"Super admin last name is not displaying");
																				}
																			} else {
																				appLog.info(
																						"Not able to click on start button");
																				saa.assertTrue(false,
																						"Not able to click on start button");
																			}
																		} else {
																			appLog.info(
																					"User is not redirected to Register for Navatar Investor pop up");
																			saa.assertTrue(false,
																					"User is not redirected to Register for Navatar Investor pop up");
																		}
																	} else {
																		appLog.info("Not able to click on deny button");
																		saa.assertTrue(false,
																				"Not able to click on deny button");
																	}
																} else {
																	appLog.info("Not able to click on next button");
																	saa.assertTrue(false,
																			"Not able to click on next button");
																}
															} else {
																appLog.info(
																		"Not able to enter value in Last name text box");
																saa.assertTrue(false,
																		"Not able to enter value in Last name text box");
															}
														} else {
															appLog.info(
																	"Not able to enter value in first name text box");
															saa.assertTrue(false,
																	"Not able to enter value in first name text box");
														}
													} else {
														appLog.info("Not able to click on next button");
														saa.assertTrue(false, "Not able to click on next button");
													}
												} else {
													appLog.info("Not able to enter value in Last name text box");
													saa.assertTrue(false,
															"Not able to enter value in Last name text box");
												}
											} else {
												appLog.info("Not able to enter value in first name text box");
												saa.assertTrue(false, "Not able to enter value in first name text box");
											}
										} else {
											appLog.info("Not able to click on next button");
											saa.assertTrue(false, "Not able to click on next button");
										}

									} else {
										appLog.error("Not able to click on start button");
										saa.assertTrue(false, "Not able to click on start button");
									}
								} else {
									appLog.info("Register popup header is not displaying");
									saa.assertTrue(false, "Register popup header is not displaying");
								}
							} else {
								appLog.info("Not able to click on back button");
								saa.assertTrue(false, "Not able to click on back button");
							}
						} else {
							appLog.error("Not able to click on start button");
							saa.assertTrue(false, "Not able to click on start button");
						}
					} else {
						appLog.info("Not able to click on navatar investor manager tab");
						saa.assertTrue(false,
								"Not able to click on navatar investor manager tab so cannot verify error messages");
					}

				} else {
					appLog.error("Not able to click on start button");
					saa.assertTrue(false, "Not able to click on start button");
				}
			} else {
				appLog.info("Not able to click on navatar investor manager tab");
				saa.assertTrue(false,
						"Not able to click on navatar investor manager tab so cannot verify error messages");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.info("Admin User is Already registered so we cannot check error messages");
			saa.assertTrue(false, "Admin User is Already registered so we cannot check error messages");
		}
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc007_VerifyCompleteAdminRegistration() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		List<String> headersText = new ArrayList<String>();
		List<String> sideMenusList = new ArrayList<String>();
		String registerPopup2of2Header = "Access<break>User<break>Profile";
		String sideMenusOnNIM = "Internal Users<break>Folder Templates<break>Manage Approvals<break>Watermarking<break>File Distributor Settings<break>Profiles";
		WebElement ele = null;
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (superAdminRegistered.equalsIgnoreCase("No")) {
			if (bp.clickOnTab(TabName.NIMTab)) {
				switchToFrame(driver, 60, nim.getNIMTabFrame(60));
				if (click(driver, nim.getStartButton(60), "Start Button", action.SCROLLANDBOOLEAN)) {
					if (click(driver, nim.getNextButton(60), "NextButton", action.SCROLLANDBOOLEAN)) {
						if (click(driver, nim.getAllowButton(60), "Allow Button", action.SCROLLANDBOOLEAN)) {
							switchToFrame(driver, 60, nim.getNIMTabFrame(60));
							ThreadSleep(5000);
							if (nim.getRegisterpopup2of2Header(60) != null) {
								String popupHeader = trim(getText(driver, nim.getRegisterpopup2of2Header(60),
										"Register popup 2 of 2 header", action.SCROLLANDBOOLEAN));
								if (popupHeader.contains("Register for Navatar Investor (Step 2 of 2)")) {
									appLog.info("Header text is verified");
								} else {
									appLog.info("Header text is not verified");
									saa.assertTrue(false, "Header text is not verified");
								}
								List<WebElement> headers = nim.getregisterPopupColoumnHeader(60);
								for (int i = 0; i < headers.size(); i++) {
									headersText.add(headers.get(i).getText().trim());
								}
								if (compareMultipleListWithoutAssertion(registerPopup2of2Header, headersText)) {
									appLog.info("Headers are matched");
								} else {
									appLog.info("Headers are not matched");
									saa.assertTrue(false, "Headers are not matched");
								}
								String userData[] = { CRMUser1FirstName + " " + CRMUser1LastName,
										CRMUser2FirstName + " " + CRMUser2LastName,
										CRMUser3FirstName + " " + CRMUser3LastName };
								for (int i = 0; i < userData.length; i++) {
									ele = FindElement(driver, "//td[text()='" + userData[i]
											+ "']/following-sibling::td[text()='" + CRMUserProfile + "']", "Grid Data",
											action.SCROLLANDBOOLEAN, 10);
									if (ele != null) {
										appLog.info("Grid Data Is Verify for User." + userData[i]);
									} else {
										appLog.info("Grid Data Is not Verify for User." + userData[i]);
										saa.assertTrue(false, "Grid Data Is not Verify for User." + userData[i]);
									}
								}
								for (int i = 0; i < userData.length; i++) {
									ele = FindElement(driver, "//td[text()='" + userData[i] + "']/..//input",
											"User CheckBox", action.SCROLLANDBOOLEAN, 60);
									if (!isSelected(driver, ele, "User checkbox")) {
										appLog.info("User checkbox is not selected for " + userData[i]);
									} else {
										appLog.info("User checkbox is selected for " + userData[i]);
										saa.assertTrue(false, "User checkbox is selected for " + userData[i]);
									}
								}
								ele = FindElement(driver,
										"//td[text()='" + CRMUser1FirstName + " " + CRMUser1LastName + "']/..//input",
										"User CheckBox", action.SCROLLANDBOOLEAN, 60);
								if (click(driver, ele, "CRM User1 Checkbox", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on User 1 Checkbox");
								} else {
									appLog.info("Not able to click on User1 checkbox");
									saa.assertTrue(false, "Not able to click on User1 checkbox");
								}
								if (click(driver, nim.getRegisterPopup2Of2CompleteButton(60), "Complete Button",
										action.SCROLLANDBOOLEAN)) {
									ExcelUtils.writeData("Registered", "Users", excelLabel.Variable_Name, "AdminUser", excelLabel.Registered);
									if (nim.getRegistrationSuccessfulPopupHeader(60) != null) {
										String regSuccessfulheader = trim(
												getText(driver, nim.getRegistrationSuccessfulPopupHeader(60),
														"Registration successful header", action.SCROLLANDBOOLEAN));
										if (regSuccessfulheader
												.contains("Registration Successful for Navatar Investor")) {
											appLog.info("Registration successful popup header text is verified");
										} else {
											appLog.info("Registration successful popup header text is not verified");
											saa.assertTrue(false,
													"Registration successful popup header text is not verified");
										}
										if (bp.verifyErrorMessageOnPage(
												NIMPageErrorMessage.registrationSuccessfulPopMessage,
												nim.getRegistrationSuccessfullPopupMessage(60),
												"Registarion successfull popup messgae")) {
											appLog.info("Registarion successfull popup messgae is verified");
										} else {
											saa.assertTrue(false,
													"Registarion successfull popup messgae is not verified.Expected:"
															+ NIMPageErrorMessage.registrationSuccessfulPopMessage
															+ " Actual"
															+ getText(driver,
																	nim.getRegistrationSuccessfullPopupMessage(60),
																	"Registarion successfull popup messgae",
																	action.BOOLEAN));
										}
										if (nim.getRegistrationSuccessfulCloseBtn(60) != null) {
											if (click(driver, nim.getRegistrationSuccessfulCloseBtn(60), "Close button",
													action.SCROLLANDBOOLEAN)) {
												switchToDefaultContent(driver);
												if (bp.clickOnTab(TabName.NIMTab)) {
													switchToFrame(driver, 60, nim.getNIMTabFrame(60));
													if (nim.verifyLandingPageText("Internal Users")) {
														appLog.info("Landing page text is verified");
													} else {
														appLog.info("Landing page text is not verified");
														saa.assertTrue(false, "Landing page text is not verified");
													}
													ThreadSleep(2000);
													ele = FindElement(driver,
															"//span[text()='" + CRMUser1FirstName + " "
																	+ CRMUser1LastName
																	+ "']/../..//input[@type='checkbox']",
															"Checkbox for User1", action.SCROLLANDBOOLEAN, 60);
													if (isSelected(driver, ele, "USer1 Checkbox")) {
														appLog.info("User1 checkbox is selected");
													} else {
														appLog.info("User1 check box is not selected");
														saa.assertTrue(false, "User1 check box is not selected");
													}
													List<WebElement> sideMenus = nim.getnimPageSideMenus();
													for (int i = 0; i < sideMenus.size(); i++) {
														sideMenusList.add(sideMenus.get(i).getText().trim());
													}
													if (compareMultipleListWithoutAssertion(sideMenusOnNIM,
															sideMenusList)) {
														appLog.info("All Menus are verified");
													} else {
														appLog.info("All side menus are not veriified");
														saa.assertTrue(false, "All side menus are not veriified");
													}
												} else {
													appLog.info(
															"Not able to click on nim Tab  so cannot check side menus");
													saa.assertTrue(false,
															"Not able to click on nim Tab  so cannot check side menus");
												}
											} else {
												appLog.info(
														"Not able to click on registration successful Popup Close Button");
												saa.assertTrue(false,
														"Not able to click on registration successful Popup Close Button");
											}
										} else {
											appLog.info("Regsitartion successful popup Close button is not displaying");
											saa.assertTrue(false,
													"Regsitartion successful popup Close button is not displaying");
										}
									} else {
										appLog.info("Registration successful popup is not displaying");
										saa.assertTrue(false, "Registration successful popup is not displaying");
									}
								} else {
									appLog.info("Not able to click on complete button");
									saa.assertTrue(false, "Not able to click on complete button");
								}
							} else {
								appLog.info("Registr popup 2of 2 header is not displaying");
								saa.assertTrue(false, "Registr popup 2of 2 header is not displaying");
							}
						} else {
							appLog.info("not able to click on allow button");
							saa.assertTrue(false, "Not able to click on allow button");
						}
					} else {
						appLog.info("Not able to click on next button");
						saa.assertTrue(false, "Not able to click on close button");
					}
				} else {
					appLog.error("Not able to click on start button");
					saa.assertTrue(false, "Not able to click on start button");
				}
			} else {
				appLog.info("Not able to click on navatar investor manager tab");
				saa.assertTrue(false,
						"Not able to click on navatar investor manager tab so cannot verify error messages");
			}
			switchToDefaultContent(driver);
			if(nim.getMyProfileFistNameAndLastNameAndFirmName("AdminUser")) {
				appLog.info("Admin User first,last name and firm name successfully write in excel");
			}else {
				appLog.error("Not able to write Admin User first,last name and firm profile in excel");
				saa.assertTrue(false, "Not able to write Admin User first,last name and firm profile in excel");
			}
		} else {
			appLog.info("Admin User is Already registered so we cannot check error messages");
			saa.assertTrue(false, "Admin User is Already registered so we cannot check error messages");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc008_CheckErrorMessagesForAdminAfterRegistration() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		HomePageBusineesLayer hp = new HomePageBusineesLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(superAdminUserName, adminPassword);
		scrollDownThroughWebelement(driver, hp.getNavatarInvestorActivitiesLabel(60),
				"Navatar Investor Manager Activitis label");
		ThreadSleep(2000);
		switchToFrame(driver, 60, hp.getHomePageAlertsFrame(60));
		if (bp.verifyErrorMessageOnPage(HomePageErrorMessage.errorMessageAfterAdminAndCRMUserRegistration,
				hp.getErrorMessageAfterAdminAndCRMUserRegistration(60), "Error Message After admin Registration")) {
			appLog.info("Error Message is verified at home page");
		} else {
			saa.assertTrue(false,
					"Error Message is not verified at home page.Expected:"
							+ HomePageErrorMessage.errorMessageAfterAdminAndCRMUserRegistration + " Actual"
							+ getText(driver, hp.getErrorMessageAfterAdminAndCRMUserRegistration(60),
									"Error Message after admin registration", action.BOOLEAN));
		}
		switchToDefaultContent(driver);
		scrollDownThroughWebelement(driver, ip.getInstitutionsTab(60), "Institution Tab");
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M1Institution1)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, ip.getWorkspaceFrame(60));
				if (bp.verifyErrorMessageOnPage(
						InstitutionPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace,
						ip.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60),
						"Error Message after admin Registration on Instituition page for fundraising workspace")) {
					appLog.info("Error Message is verified  on Instituition page for fundraising workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Instituition page for fundraising workspace.Expected:"
									+ InstitutionPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace
									+ " Actual"
									+ getText(driver,
											ip.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60),
											"Error Message before admin registration on instituition page for fundraising workspace",
											action.BOOLEAN));
				}
				if (bp.verifyErrorMessageOnPage(
						InstitutionPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace,
						ip.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),
						"Error Message after admin Registration on Instituition page for investor workspace")) {
					appLog.info("Error Message is verified  on Instituition page for investor workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Instituition page for investor workspace.Expected:"
									+ InstitutionPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace
									+ " Actual"
									+ getText(driver,
											ip.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),
											"Error Message after admin registration on instituition page for investor workspace",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				saa.assertTrue(false, "Not able to click on created instituition");
			}
		} else {
			appLog.info("Not able to click on institution tab");
			saa.assertTrue(false, "Not able to click on institution tab");
		}
		scrollDownThroughWebelement(driver, cp.getContactsTab(60), "Contacts Tab");
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M1Contact1FirstName, M1Contact1LastName, null)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, cp.getWorkspaceFrameIncontactPage(60));
				if (bp.verifyErrorMessageOnPage(
						ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace,
						cp.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60),
						"Error Message after admin Registration on Contact page for fundraising Workspace")) {
					appLog.info("Error Message is verified  on Contact page for fundraising workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Contact page for fundraising workspace.Expected:"
									+ ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace
									+ " Actual"
									+ getText(driver,
											cp.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60),
											"Error Message before admin registration on contact page", action.BOOLEAN));
				}
				if (bp.verifyErrorMessageOnPage(
						ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace,
						cp.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),
						"Error Message after admin Registration on Contact page for investor workspace")) {
					appLog.info("Error Message is verified  on Contact page for investor workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Contact page for investor workspace.Expected:"
									+ ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace
									+ " Actual"
									+ getText(driver,
											cp.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),
											"Error Message after admin registration on Contact page for investor workspace",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				saa.assertTrue(false, "Not able to click on created contact");
			}
		} else {
			appLog.info("Not able to click on contacts tab so we cannot check error message on contact page");
			saa.assertTrue(false, "Not able to click on contacts tab so we cannot check error message on contact page");
		}
		scrollDownThroughWebelement(driver, ip.getInstitutionsTab(60), "Institution Tab");
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedLP(M1LimitedPartner1)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, ip.getWorkspaceFrame(60));
				if (bp.verifyErrorMessageOnPage(
						InstitutionPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace,
						ip.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),
						"Error Message after admin Registration on Limited Partner page")) {
					appLog.info("Error Message is verified  on Limited Partner page");
				} else {
					saa.assertTrue(false, "Error Message is not verified on Limited Partner page.Expected:"
							+ InstitutionPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace
							+ " Actual"
							+ getText(driver, ip.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),
									"Error Message after admin registration on Limited Partner page", action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				appLog.info("Not able to click on created limited partner");
				saa.assertTrue(false, "Not able to click on created limited partner");
			}
		} else {
			appLog.info("Not able to click on institution tab");
			saa.assertTrue(false, "Not able to click on institution tab");
		}
		scrollDownThroughWebelement(driver, bp.getUserNameAtUserMenuTab(60), "User Name At User Menu Tab");
		if (bp.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.clickOnCreatedCommitmentId(M1CommitmentId)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, cmp.getWorkspaceFrameOnCommitmentPage(60));
				if (bp.verifyErrorMessageOnPage(CommitmentPageErrorMessage.errorMesageAfterAdminAndCRMUserRegistration,
						cmp.getErrorMessageAfterAdminAndCRMUserRegistration(60),
						"Error Message after admin Registration on commitments page")) {
					appLog.info("Error Message is verified  on Commitments page");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Limited Partner page.Expected:"
									+ CommitmentPageErrorMessage.errorMesageAfterAdminAndCRMUserRegistration + " Actual"
									+ getText(driver, cmp.getErrorMessageAfterAdminAndCRMUserRegistration(60),
											"Error Message after admin registration on commitments page",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				appLog.info("Not able to click on created commitment ID");
				saa.assertTrue(false, "Not able to click on created commitment ID");
			}
		} else {
			appLog.info("Not able to click on commitment tab so cannot check error message on Commitments page");
			saa.assertTrue(false,
					"Not able to click on commitment tab so cannot check error message on Commitments page");
		}
		scrollDownThroughWebelement(driver, bp.getFundsTab(60), "Funds Tab");
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M1FundName1)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, fp.getWorkspaceFrameOnFundsPage(60));
				if (fp.getBuildFundraisinWorkspaceButton(60) != null) {
					appLog.info("build Fundarising Workspace button is displaying");
				} else {
					appLog.info("build Fundarising Workspace button is not displaying ");
					saa.assertTrue(false, "build Fundarising Workspace button is not displaying ");
				}
				if (fp.getBuildInvestorWorkspace(60) != null) {
					appLog.info("build Investor Workspace button is displaying");
				} else {
					appLog.info("build Investor Workspace button is not displaying ");
					saa.assertTrue(false, "build Investor Workspace button is not displaying ");
				}
				switchToDefaultContent(driver);
			} else {
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on funds tab so we cannot check error message on funds page");
			saa.assertTrue(false, "Nota ble to click on funds tab so we cannot check error message on funds page");
		}
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc009_CheckErrorMessagesAtCRMUser2Side() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer nim=new NIMPageBusinessLayer(driver);
		HomePageBusineesLayer hp = new HomePageBusineesLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		List<String> lst=new ArrayList<String>();
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if(bp.removeUnusedTabs()){
			appLog.info("Unused tabs remove successfully");
		}else{
			appLog.info("Unused tabs not removed successfully");
			saa.assertTrue(false, "Unused tabs not removed successfully");
		}
		if(bp.clickOnTab(TabName.HomeTab)){
		scrollDownThroughWebelement(driver, hp.getNavatarInvestorActivitiesLabel(60),
				"Navatar Investor Manager Activitis label");
		ThreadSleep(2000);
		switchToFrame(driver, 60, hp.getHomePageAlertsFrame(60));
		if (bp.verifyErrorMessageOnPage(HomePageErrorMessage.errorMessageBeforeGivingInternalUserAccess,
				bp.getErrorMessageBeforeGivingInternalUserAccess(60),
				"Error Message before giving internal User Access")) {
			appLog.info("Error Message is verified at home page");
		} else {
			saa.assertTrue(false,
					"Error Message is not verified at home page.Expected:"
							+ HomePageErrorMessage.errorMessageBeforeGivingInternalUserAccess + " Actual"
							+ getText(driver, bp.getErrorMessageBeforeGivingInternalUserAccess(60),
									"Error Message before giving internal User Access", action.BOOLEAN));
		}
		}else{
			appLog.info("Not able to click on Home Tab");
			saa.assertTrue(false, "Not able to click on Home Tab");
		}
		switchToDefaultContent(driver);
		scrollDownThroughWebelement(driver, ip.getInstitutionsTab(60), "Institution Tab");
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M1Institution1)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, ip.getWorkspaceFrame(60));
				if (bp.verifyErrorMessageOnPage(InstitutionPageErrorMessage.errorMessageBeforGivingInternalUserAccess,
						bp.getErrorMessageOnAllPages(60).get(0),
						"Error Message before giving internal user access on Instituition page")) {
					appLog.info("Error Message is verified  on Instituition page");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Instituition page.Expected:"
									+ InstitutionPageErrorMessage.errorMessageBeforGivingInternalUserAccess + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
											"Error Message before giving internal user access on Instituition page",
											action.BOOLEAN));
				}
				if (bp.verifyErrorMessageOnPage(InstitutionPageErrorMessage.errorMessageBeforGivingInternalUserAccess,
						bp.getErrorMessageOnAllPages(60).get(1),
						"Error Message before giving internal user access on Instituition page for investor workspace")) {
					appLog.info("Error Message is verified  on Instituition page for investor workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Instituition page for investor workspace.Expected:"
									+ InstitutionPageErrorMessage.errorMessageBeforGivingInternalUserAccess + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(1),
											"Error Message before giving internal user access on Instituition page for investor workspace",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				saa.assertTrue(false, "Not able to click on created instituition");
			}
		} else {
			appLog.info("Not able to click on institutions tab");
			saa.assertTrue(false, "Not able to click on institutions tab");
		}
		scrollDownThroughWebelement(driver, cp.getContactsTab(60), "Contacts Tab");
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M1Contact1FirstName, M1Contact1LastName, null)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, cp.getWorkspaceFrameIncontactPage(60));
				if (bp.verifyErrorMessageOnPage(ContactPageErrorMessage.errorMessageBeforeGivingInternalUserAccess,
						bp.getErrorMessageOnAllPages(60).get(0),
						"Error Message before giving internal user access on Contact page")) {
					appLog.info("Error Message is verified  on Contact page for fundraising workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Contact page for fundraising workspace.Expected:"
									+ ContactPageErrorMessage.errorMessageBeforeGivingInternalUserAccess + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
											"Error Message before giving internal user access on Contact page",
											action.BOOLEAN));
				}
				if (bp.verifyErrorMessageOnPage(ContactPageErrorMessage.errorMessageBeforeGivingInternalUserAccess,
						bp.getErrorMessageOnAllPages(60).get(1),
						"Error Message before giving internal user access on Contact page for investor workspace")) {
					appLog.info("Error Message is verified  on Contact page for investor workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Contact page for investor workspace.Expected:"
									+ ContactPageErrorMessage.errorMessageBeforeGivingInternalUserAccess + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(1),
											"Error Message before giving internal user access on Contact page for investor workspace",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				saa.assertTrue(false, "Not able to click on created contact");
			}
		} else {
			appLog.info("Not able to click on contacts tab so we cannot check error message on contact page");
			saa.assertTrue(false, "Not able to click on contacts tab so we cannot check error message on contact page");
		}
		scrollDownThroughWebelement(driver, bp.getFundsTab(60), "Funds Tab");
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M1FundName1)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, fp.getWorkspaceFrameOnFundsPage(60));
				if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.errorMessageBeforeGivingInternalUseraccess,
						bp.getErrorMessageOnAllPages(60).get(0),
						"Error Message before giving internal user access on Funds page")) {
					appLog.info("Error Message is verified  on Funds page for fundraising workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Funds page for fundraising workspace.Expected:"
									+ FundsPageErrorMessage.errorMessageBeforeGivingInternalUseraccess + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
											"Error Message before giving internal user access on Funds page",
											action.BOOLEAN));
				}
				if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.errorMessageBeforeGivingInternalUseraccess,
						bp.getErrorMessageOnAllPages(60).get(1),
						"Error Message before giving internal user access on Funds page for investor workspace")) {
					appLog.info("Error Message is verified  on Funds page for investor workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Funds page for investor workspace.Expected:"
									+ FundsPageErrorMessage.errorMessageBeforeGivingInternalUseraccess + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
											"Error Message before giving internal user access on Funds page for investor workspace",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Nota ble to click on funds tab so we cannot check error message on funds page");
			saa.assertTrue(false, "Nota ble to click on funds tab so we cannot check error message on funds page");
		}
		scrollDownThroughWebelement(driver, ip.getInstitutionsTab(60), "Institution Tab");
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedLP(M1LimitedPartner1)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, ip.getWorkspaceFrame(60));
				if (bp.verifyErrorMessageOnPage(InstitutionPageErrorMessage.errorMessageBeforGivingInternalUserAccess,
						bp.getErrorMessageOnAllPages(60).get(0),
						"Error Message before giving internal user access on Limited Partner page")) {
					appLog.info("Error Message is verified  on Limited Partner page");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Limited Partner page.Expected:"
									+ InstitutionPageErrorMessage.errorMessageBeforGivingInternalUserAccess + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
											"Error Message before giving internal user access on Limited Partner page",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				appLog.info("Not able to click on created limited partner");
				saa.assertTrue(false, "Not able to click on created limited partner");
			}
		} else {
			appLog.info("Not able to click on created institution");
			saa.assertTrue(false, "Not able to click on created institution");
		}
		scrollDownThroughWebelement(driver, bp.getUserNameAtUserMenuTab(60), "User Name At User Menu Tab");
		WebElement ele=FindElement(driver, "//a[contains(@title,'Commitments')]", "Commitments tab",
				action.SCROLLANDBOOLEAN, 10);
		if(ele==null){
			lst=bp.addRemoveCustomTab("Commitments", customTabActionType.Add);
			if(!lst.isEmpty()){
				for (int i = 0; i < lst.size(); i++) {
					appLog.error(lst.get(i));
					saa.assertTrue(false,lst.get(i));
				}
			}		
			ThreadSleep(1000);
			if (bp.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.clickOnCreatedCommitmentId(M1CommitmentId)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, cmp.getWorkspaceFrameOnCommitmentPage(60));
				if (bp.verifyErrorMessageOnPage(CommitmentPageErrorMessage.errorMessageBeforeGivingInternalUseraccess,
						bp.getErrorMessageOnAllPages(60).get(0),
						"Error Message before giving internal user access on commitments page")) {
					appLog.info("Error Message is verified  on commitments page");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on commitments page.Expected:"
									+ CommitmentPageErrorMessage.errorMessageBeforeGivingInternalUseraccess + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
											"Error Message before giving internal user access on commitments page",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				appLog.info("Not able to click on created commitment ID");
				saa.assertTrue(false, "Not able to click on created commitment ID");
			}
		} else {
			appLog.info("Not able to click on commitment tab so cannot check error message on Commitments page");
			saa.assertTrue(false,
					"Not able to click on commitment tab so cannot check error message on Commitments page");
		}
		}else{
			if (bp.clickOnTab(TabName.CommitmentsTab)) {
				if (cmp.clickOnCreatedCommitmentId(M1CommitmentId)) {
					String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
					if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
						if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on workspace expand icon");
						} else {
							appLog.info("Not able to click on workspace expand icon");
							saa.assertTrue(false, "Not able to click on workspace expand icon");
						}
					} else {
						appLog.info("Workspace is in expanded form");
					}
					switchToFrame(driver, 60, cmp.getWorkspaceFrameOnCommitmentPage(60));
					if (bp.verifyErrorMessageOnPage(CommitmentPageErrorMessage.errorMessageBeforeGivingInternalUseraccess,
							bp.getErrorMessageOnAllPages(60).get(0),
							"Error Message before giving internal user access on commitments page")) {
						appLog.info("Error Message is verified  on commitments page");
					} else {
						saa.assertTrue(false,
								"Error Message is not verified on commitments page.Expected:"
										+ CommitmentPageErrorMessage.errorMessageBeforeGivingInternalUseraccess + " Actual"
										+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
												"Error Message before giving internal user access on commitments page",
												action.BOOLEAN));
					}
					switchToDefaultContent(driver);
				} else {
					appLog.info("Not able to click on created commitment ID");
					saa.assertTrue(false, "Not able to click on created commitment ID");
				}
			} else {
				appLog.info("Not able to click on commitment tab so cannot check error message on Commitments page");
				saa.assertTrue(false,
						"Not able to click on commitment tab so cannot check error message on Commitments page");
			}
		}
		scrollDownThroughWebelement(driver, bp.getUserNameAtUserMenuTab(60), "User Name At User Menu Tab");
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (bp.verifyErrorMessageOnPage(NIMPageErrorMessage.errorMessageBeforeGivingInternalUserAccess,
					bp.getErrorMessageBeforeGivingInternalUserAccess(60),
					"Error Message before giving internal user access on NIM page")) {
				appLog.info("Error Message is verified  on NIM page");
			} else {
				saa.assertTrue(false,
						"Error Message is not verified on NIM page.Expected:"
								+ NIMPageErrorMessage.errorMessageBeforeGivingInternalUserAccess + " Actual"
								+ getText(driver, bp.getErrorMessageBeforeGivingInternalUserAccess(60),
										"Error Message before giving internal user access on NIM page",
										action.BOOLEAN));
			}
			switchToDefaultContent(driver);
		} else {
			appLog.info("Not able to click on NIM Tab");
			saa.assertTrue(false, "Not able to click on NIM Tab");
		}
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc010_VerifyRegisterForNavatarInvestorPopUpAtNIMTab() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		HomePageBusineesLayer hp = new HomePageBusineesLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		List<String> lst=new ArrayList<String>();
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.removeUnusedTabs()){
			appLog.info("Unused tabs remove successfully");
		}else{
			appLog.info("Unused tabs not removed successfully");
			saa.assertTrue(false, "Unused tabs not removed successfully");
		}
		if(bp.clickOnTab(TabName.HomeTab)){
		scrollDownThroughWebelement(driver, hp.getNavatarInvestorActivitiesLabel(60),
				"Navatar Investor Manager Activitis label");
		ThreadSleep(2000);
		switchToFrame(driver, 60, hp.getHomePageAlertsFrame(60));
		if (bp.verifyErrorMessageOnPage(HomePageErrorMessage.errorMessageAfterGivingInternalUserAccess,
				bp.getErrorMessageBeforeGivingInternalUserAccess(60),
				"Error Message after giving internal User Access")) {
			appLog.info("Error Message is verified at home page");
		} else {
			saa.assertTrue(false,
					"Error Message is not verified at home page.Expected:"
							+ HomePageErrorMessage.errorMessageAfterGivingInternalUserAccess + " Actual"
							+ getText(driver, bp.getErrorMessageBeforeGivingInternalUserAccess(60),
									"Error Message after giving internal User Access", action.BOOLEAN));
		}
		}else{
			appLog.info("Not able to click on Home Tab");
			saa.assertTrue(false, "Not able to click on Home Tab");
		}
		switchToDefaultContent(driver);
		scrollDownThroughWebelement(driver, ip.getInstitutionsTab(60), "Institution Tab");
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M1Institution1)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, ip.getWorkspaceFrame(60));
				if (bp.verifyErrorMessageOnPage(InstitutionPageErrorMessage.errorMessageAfterGivingInternalUseraccess,
						bp.getErrorMessageOnAllPages(60).get(0),
						"Error Message after giving internal user access on Instituition page")) {
					appLog.info("Error Message is verified  on Instituition page");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Instituition page.Expected:"
									+ InstitutionPageErrorMessage.errorMessageAfterGivingInternalUseraccess + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
											"Error Message after giving internal user access on Instituition page",
											action.BOOLEAN));
				}
				if (bp.verifyErrorMessageOnPage(InstitutionPageErrorMessage.errorMessageAfterGivingInternalUseraccess,
						bp.getErrorMessageOnAllPages(60).get(1),
						"Error Message after giving internal user access on Instituition page for investor workspace")) {
					appLog.info("Error Message is verified  on Instituition page for investor workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Instituition page for investor workspace.Expected:"
									+ InstitutionPageErrorMessage.errorMessageAfterGivingInternalUseraccess + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(1),
											"Error Message after giving internal user access on Instituition page for investor workspace",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				saa.assertTrue(false, "Not able to click on created instituition");
			}
		} else {
			appLog.info("Not able to click on institutions tab");
			saa.assertTrue(false, "Not able to click on institutions tab");
		}
		scrollDownThroughWebelement(driver, cp.getContactsTab(60), "Contacts Tab");
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M1Contact1FirstName, M1Contact1LastName, null)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, cp.getWorkspaceFrameIncontactPage(60));
				if (bp.verifyErrorMessageOnPage(ContactPageErrorMessage.errorMessageAfterGivingInternalUserAccess,
						bp.getErrorMessageOnAllPages(60).get(0),
						"Error Message after giving internal user access on Contact page")) {
					appLog.info("Error Message is verified  on Contact page for fundraising workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Contact page for fundraising workspace.Expected:"
									+ ContactPageErrorMessage.errorMessageAfterGivingInternalUserAccess + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
											"Error Message after giving internal user access on Contact page",
											action.BOOLEAN));
				}
				if (bp.verifyErrorMessageOnPage(ContactPageErrorMessage.errorMessageAfterGivingInternalUserAccess,
						bp.getErrorMessageOnAllPages(60).get(1),
						"Error Message after giving internal user access on Contact page for investor workspace")) {
					appLog.info("Error Message is verified  on Contact page for investor workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Contact page for investor workspace.Expected:"
									+ ContactPageErrorMessage.errorMessageAfterGivingInternalUserAccess + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(1),
											"Error Message after giving internal user access on Contact page for investor workspace",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				saa.assertTrue(false, "Not able to click on created contact");
			}
		} else {
			appLog.info("Not able to click on contacts tab so we cannot check error message on contact page");
			saa.assertTrue(false, "Not able to click on contacts tab so we cannot check error message on contact page");
		}
		scrollDownThroughWebelement(driver, bp.getFundsTab(60), "Funds Tab");
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M1FundName1)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, fp.getWorkspaceFrameOnFundsPage(60));
				if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.errorMessageAfterGivingInternalUseraccess,
						bp.getErrorMessageOnAllPages(60).get(0),
						"Error Message after giving internal user access on Funds page")) {
					appLog.info("Error Message is verified  on Funds page for fundraising workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Funds page for fundraising workspace.Expected:"
									+ FundsPageErrorMessage.errorMessageAfterGivingInternalUseraccess + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
											"Error Message after giving internal user access on Funds page",
											action.BOOLEAN));
				}
				if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.errorMessageAfterGivingInternalUseraccess,
						bp.getErrorMessageOnAllPages(60).get(1),
						"Error Message after giving internal user access on Funds page for investor workspace")) {
					appLog.info("Error Message is verified  on Funds page for investor workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Funds page for investor workspace.Expected:"
									+ FundsPageErrorMessage.errorMessageAfterGivingInternalUseraccess + " Actual"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
											"Error Message after giving internal user access on Funds page for investor workspace",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Nota ble to click on funds tab so we cannot check error message on funds page");
			saa.assertTrue(false, "Nota ble to click on funds tab so we cannot check error message on funds page");
		}
		scrollDownThroughWebelement(driver, ip.getInstitutionsTab(60), "Institution Tab");
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedLP(M1LimitedPartner1)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						saa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 60, ip.getWorkspaceFrame(60));
				if (bp.verifyErrorMessageOnPage(InstitutionPageErrorMessage.errorMessageAfterGivingInternalUseraccess,
						bp.getErrorMessageOnAllPages(60).get(0),
						"Error Message after giving internal user access on Limited Partner page")) {
					appLog.info("Error Message is verified  on Limited Partner page");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Limited Partner page.Expected:"
									+ InstitutionPageErrorMessage.errorMessageAfterGivingInternalUseraccess + " Actual:"
									+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
											"Error Message after giving internal user access on Limited Partner page",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				appLog.info("Not able to click on created limited partner");
				saa.assertTrue(false, "Not able to click on created limited partner");
			}
		} else {
			appLog.info("Not able to click on created institution");
			saa.assertTrue(false, "Not able to click on created institution");
		}
		scrollDownThroughWebelement(driver, bp.getUserNameAtUserMenuTab(60), "User Name At User Menu Tab");
		WebElement ele=FindElement(driver, "//a[contains(@title,'Commitments')]", "Commitments tab",
				action.SCROLLANDBOOLEAN, 10);
		if(ele==null){
			lst=bp.addRemoveCustomTab("Commitments", customTabActionType.Add);
			if(!lst.isEmpty()){
				for (int i = 0; i < lst.size(); i++) {
					appLog.error(lst.get(i));
					saa.assertTrue(false,lst.get(i));
				}
			}
			ThreadSleep(1000);
				if (bp.clickOnTab(TabName.CommitmentsTab)) {
				if (cmp.clickOnCreatedCommitmentId(M1CommitmentId)) {
					String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
					if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
						if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on workspace expand icon");
						} else {
							appLog.info("Not able to click on workspace expand icon");
							saa.assertTrue(false, "Not able to click on workspace expand icon");
						}
					} else {
						appLog.info("Workspace is in expanded form");
					}
					switchToFrame(driver, 60, cmp.getWorkspaceFrameOnCommitmentPage(60));
					if (bp.verifyErrorMessageOnPage(CommitmentPageErrorMessage.errorMessageAfterGivingInternalUseraccess,
							bp.getErrorMessageOnAllPages(60).get(0),
							"Error Message after giving internal user access on commitments page")) {
						appLog.info("Error Message is verified  on commitments page");
					} else {
						saa.assertTrue(false,
								"Error Message is not verified on commitments page.Expected:"
										+ CommitmentPageErrorMessage.errorMessageAfterGivingInternalUseraccess + " Actual"
										+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
												"Error Message after giving internal user access on commitments page",
												action.BOOLEAN));
					}
					switchToDefaultContent(driver);
				} else {
					appLog.info("Not able to click on created commitment ID");
					saa.assertTrue(false, "Not able to click on created commitment ID");
				}
			} else {
				appLog.info("Not able to click on commitment tab so cannot check error message on Commitments page");
				saa.assertTrue(false,
						"Not able to click on commitment tab so cannot check error message on Commitments page");
			}
		}else{
			if (bp.clickOnTab(TabName.CommitmentsTab)) {
				if (cmp.clickOnCreatedCommitmentId(M1CommitmentId)) {
					String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
					if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
						if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on workspace expand icon");
						} else {
							appLog.info("Not able to click on workspace expand icon");
							saa.assertTrue(false, "Not able to click on workspace expand icon");
						}
					} else {
						appLog.info("Workspace is in expanded form");
					}
					switchToFrame(driver, 60, cmp.getWorkspaceFrameOnCommitmentPage(60));
					if (bp.verifyErrorMessageOnPage(CommitmentPageErrorMessage.errorMessageAfterGivingInternalUseraccess,
							bp.getErrorMessageOnAllPages(60).get(0),
							"Error Message after giving internal user access on commitments page")) {
						appLog.info("Error Message is verified  on commitments page");
					} else {
						saa.assertTrue(false,
								"Error Message is not verified on commitments page.Expected:"
										+ CommitmentPageErrorMessage.errorMessageAfterGivingInternalUseraccess + " Actual"
										+ getText(driver, bp.getErrorMessageOnAllPages(60).get(0),
												"Error Message after giving internal user access on commitments page",
												action.BOOLEAN));
					}
					switchToDefaultContent(driver);
				} else {
					appLog.info("Not able to click on created commitment ID");
					saa.assertTrue(false, "Not able to click on created commitment ID");
				}
			} else {
				appLog.info("Not able to click on commitment tab so cannot check error message on Commitments page");
				saa.assertTrue(false,
						"Not able to click on commitment tab so cannot check error message on Commitments page");
			}
		}

		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (nim.getRegisterPopupHeaderForCRMUser(60) != null) {
				String NIMHeader = trim(getText(driver, nim.getRegisterPopupHeaderForCRMUser(60),
						"NIM Register popup header", action.BOOLEAN));
				if (NIMHeader.equalsIgnoreCase("Register for Navatar Investor")) {
					appLog.info("Register popup header is verified");
				} else {
					appLog.info("Register popup header is not verified");
					saa.assertTrue(false, "Register popup header is not verified");
				}
				if (isDisplayed(driver, nim.getNextButton(60), "Visibility", 60, "Next Button") != null) {
					appLog.info("Next Button is displaying on header popup");
				} else {
					appLog.info("Next Button is not displaying on header popup");
					saa.assertTrue(false, "Next Button is not displaying on header popup");
				}
				if (isDisplayed(driver, nim.getRegisterPopupCRMUserCloseIcon(60), "Visibility", 60,
						"Close Icon") != null) {
					appLog.info("Close Icon is displaying on CRM user register header popup");
				} else {
					appLog.info("Close Icon is displaying on CRM user register header popup");
					saa.assertTrue(false, "Close Icon is displaying on CRM user register header popup");
				}
				if (isDisplayed(driver, nim.getRegisterPopupBackButton(60).get(1), "Visibility", 60,
						"Back Button") != null) {
					appLog.info("Back Button is displaying on header popup");
				} else {
					appLog.info("Back Button is not displaying on header popup");
					saa.assertTrue(false, "Back Button is not displaying on header popup");
				}
				String firstName = getText(driver, nim.getRegisterPopupFirstName(60), "First Name",
						action.SCROLLANDBOOLEAN);
				if (firstName != null) {
					appLog.info("First Name field is prefilled");
				} else {
					appLog.info("First Name field is not prefilled");
					saa.assertTrue(false, "First Name field is not prefilled");
				}
				String lastName = getText(driver, nim.getRegisterPopupLastName(60), "Last Name",
						action.SCROLLANDBOOLEAN);
				if (lastName != null) {
					appLog.info("Last Name field is prefilled");
				} else {
					appLog.info("Last Name field is not prefilled");
					saa.assertTrue(false, "Last Name field is not prefilled");
				}
				String email = getText(driver, nim.getRegisterPopupEmail(60), "email", action.SCROLLANDBOOLEAN);
				if (email != null) {
					appLog.info("email field is prefilled");
				} else {
					appLog.info("email field is not prefilled");
					saa.assertTrue(false, "email field is not prefilled");
				}
				if (!isEnabled(driver, nim.getRegisterPopupEmail(60), "Email")) {
					appLog.info("Email field is not enabled");
				} else {
					appLog.error("Email field is enabled");
					saa.assertTrue(false, "Email field is enabled");
				}

				if (click(driver, nim.getRegisterPopupCRMUserCloseIcon(60), "Close Icon on CRM Register popup",
						action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					if (isDisplayed(driver, hp.getDashboardLabelOnHomePage(60), "Visibility", 60,
							"Dashboard label on HomePage") != null) {
						appLog.info("User is redirected to home page successfully");
					} else {
						appLog.info("User is not redirected to home page");
						saa.assertTrue(false, "User is not redirected to home page");
					}
				} else {
					appLog.info("Not able to click on close icon");
					saa.assertTrue(false, "Not able to click on close icon");
				}
				if (bp.clickOnTab(TabName.NIMTab)) {
					switchToFrame(driver, 60, nim.getNIMTabFrame(60));
					if (click(driver, nim.getRegisterPopupBackButton(60).get(1), "Back Button",
							action.SCROLLANDBOOLEAN)) {
						if (isDisplayed(driver, hp.getDashboardLabelOnHomePage(60), "Visibility", 60,
								"Dashboard label on HomePage") != null) {
							appLog.info("User is redirected to home page successfully");
						} else {
							appLog.info("User is not redirected to home page");
							saa.assertTrue(false, "User is not redirected to home page");
						}
					} else {
						appLog.info("Not able to click on register popup Back button");
						saa.assertTrue(false, "Not able to click on register popup Back button");
					}
				} else {
					saa.assertTrue(false, "Not able to click on NIM tab");
				}
			} else {
				appLog.info(
						"Register popup is not displaying so we cannot check first name ,last name and email fields");
				saa.assertTrue(false,
						"Register popup is not displaying so we cannot check first name ,last name and email fields");
			}
		} else {
			appLog.info("Not able to click on NIM tab so cannot verify register popup");
			saa.assertTrue(false, "Not able to click on NIM tab so cannot verify register popup");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc011_VerifyRegisterForNavatarInvestorStep1Of2PopUpAndErrorMessages() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			nim.getRegisterPopupFirstName(60).clear();
			nim.getRegisterPopupLastName(60).clear();
			if (click(driver, nim.getNextButton(60), "Next Button", action.SCROLLANDBOOLEAN)) {
				if (bp.verifyErrorMessageOnPage(NIMPageErrorMessage.lastNameErrorMessageOnRegisterPopup,
						nim.getRegisterPopupLastNameRequiresErrorMessage(60),
						"Last Name Error Message on Register Popup")) {
					appLog.info("Last Name Error Message on Register Popup is verified");
				} else {
					saa.assertTrue(false,
							"Last Name Error Message on Register Popup is not verified.Expected:"
									+ NIMPageErrorMessage.lastNameErrorMessageOnRegisterPopup + " Actual"
									+ getText(driver, nim.getRegisterPopupLastNameRequiresErrorMessage(60),
											"Last Name Error Message on Register Popup", action.BOOLEAN));
				}

				if (sendKeys(driver, nim.getRegisterPopupFirstName(60), ExcelUtils.readData("SpecialChar", 3, 0),
						"First Name", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, nim.getRegisterPopupLastName(60), ExcelUtils.readData("SpecialChar", 3, 1),
							"Last Name", action.SCROLLANDBOOLEAN)) {
						if (click(driver, nim.getNextButton(60), "Next Button", action.SCROLLANDBOOLEAN)) {
							String alertMessage = switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
							if (alertMessage.equalsIgnoreCase(NIMPageErrorMessage.invalidNameErrorMessage)) {
								appLog.info("error message of alert is verified");
							} else {
								appLog.info("Error Message of alert is not verified");
								saa.assertTrue(false, "Error Message of alert is not verified");
							}
							switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
							if (!isAlertPresent(driver)) {
								appLog.info("Alert popup get closed successfully");
							} else {
								appLog.info("Alert popup is not closed successfully");
								saa.assertTrue(false, "Alert popup is not closed successfully");
							}
							if (sendKeys(driver, nim.getRegisterPopupFirstName(60), CRMUser1FirstName, "First Name",
									action.SCROLLANDBOOLEAN)) {
								if (sendKeys(driver, nim.getRegisterPopupLastName(60), CRMUser1LastName, "Last Name",
										action.SCROLLANDBOOLEAN)) {
									if (click(driver, nim.getNextButton(60), "Next Button", action.SCROLLANDBOOLEAN)) {
										if (click(driver, nim.getDenyButton(60), "Deny Button",
												action.SCROLLANDBOOLEAN)) {
											if (bp.verifyErrorMessageOnPage(
													NIMPageErrorMessage.problemLoggingInErrorMessage,
													nim.getProblemLoggingInErrorMessage(60),
													"Problem Logging Error Message")) {
												appLog.info(
														"Problem Logging Error Message on Register Popup is verified");
											} else {
												saa.assertTrue(false,
														"Problem Logging Error Message on Register Popup is not verified.Expected:"
																+ NIMPageErrorMessage.problemLoggingInErrorMessage
																+ " Actual"
																+ getText(driver,
																		nim.getProblemLoggingInErrorMessage(60),
																		"Problem Logging Error Message",
																		action.BOOLEAN));
											}
											String accessdeniedtext = trim(getText(driver,
													isDisplayed(driver, nim.getDeniedAccessLabeltext(60), "visibility",
															60, "Remote Access denied Text."),
													"Error Message", action.SCROLLANDBOOLEAN));
											saa.assertTrue(accessdeniedtext.contains("Remote_Error: access_denied"),
													"Remote_Error: access_denied is not matched");
											driver.navigate().back();
											driver.navigate().back();
											switchToFrame(driver, 60, nim.getNIMTabFrame(60));
											if (nim.getNextButton(60) != null) {
												String userfirstName = getAttribute(driver,
														nim.getRegisterPopupFirstName(60), "First name", "value");
												if (userfirstName.equalsIgnoreCase(CRMUser1FirstName)) {
													appLog.info("CRM User first name is displaying");
												} else {
													appLog.info("CRM User first name is not displaying");
													saa.assertTrue(false, "CRM User first name is not displaying");
												}
												String userlastName = getAttribute(driver,
														nim.getRegisterPopupLastName(60), "Last name", "value");
												if (userlastName.equalsIgnoreCase(CRMUser1LastName)) {
													appLog.info("CRM User last name is displaying");
												} else {
													appLog.info("CRM User last name is not displaying");
													saa.assertTrue(false, "CRM User last name is not displaying");
												}
											} else {
												appLog.info("Register popup is not available");
												saa.assertTrue(false, "Register popup is not available");
											}

										} else {
											appLog.info("Not able to click on deny button");
											saa.assertTrue(false, "Not able to click on deny button");
										}
									} else {
										appLog.info("Not able to click on next button");
										saa.assertTrue(false, "Not able to click on next button");
									}
								} else {
									appLog.info("Not able to enter value in Last name text box");
									saa.assertTrue(false, "Not able to enter value in Last name text box");
								}
							} else {
								appLog.info("Not able to enter value in first name text box");
								saa.assertTrue(false, "Not able to enter value in first name text box");
							}

						} else {
							appLog.info("Not able to click on next button");
							saa.assertTrue(false, "Not able to click on next button");
						}
					} else {
						appLog.info("Not able to enter value in Last name text box");
						saa.assertTrue(false, "Not able to enter value in Last name text box");
					}
				} else {
					appLog.info("Not able to enter value in first name text box");
					saa.assertTrue(false, "Not able to enter value in first name text box");
				}
			} else {
				appLog.info("Not able to click on next button");
				saa.assertTrue(false, "Not able to click on next button");
			}
		} else {
			appLog.info("Not able to click on navatar investor manager tab");
			saa.assertTrue(false, "Not able to click on navatar investor manager tab so cannot verify error messages");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();

	}

	@Test
	public void M1tc012_VerifyCompleteUserRegistrationStep2Of2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		List<String> sideMenusList = new ArrayList<String>();
		String sideMenusOnNIM = "Internal Users<break>Folder Templates<break>Manage Approvals<break>Watermarking<break>Profiles";
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (click(driver, nim.getNextButton(60), "NextButton", action.SCROLLANDBOOLEAN)) {
				if (click(driver, nim.getAllowButton(60), "Allow Button", action.SCROLLANDBOOLEAN)) {
					switchToFrame(driver, 60, nim.getNIMTabFrame(60));
					ThreadSleep(5000);
					if (nim.getRegistrationSuccessfulPopupHeader(60) != null) {
						String regSuccessfulheader = trim(getText(driver, nim.getRegistrationSuccessfulPopupHeader(60),
								"Registration successful header", action.SCROLLANDBOOLEAN));
						if (regSuccessfulheader.contains("Registration Successful for Navatar Investor")) {
							appLog.info("Registration successful popup header text is verified");
						} else {
							appLog.info("Registration successful popup header text is not verified");
							saa.assertTrue(false, "Registration successful popup header text is not verified");
						}
						if (bp.verifyErrorMessageOnPage(NIMPageErrorMessage.registrationSuccessfulPopMessage,
								nim.getRegistrationSuccessfullPopupMessage(60),
								"Registarion successfull popup messgae")) {
							appLog.info("Registarion successfull popup messgae is verified");
						} else {
							saa.assertTrue(false,
									"Registarion successfull popup messgae is not verified.Expected:"
											+ NIMPageErrorMessage.registrationSuccessfulPopMessage + " Actual"
											+ getText(driver, nim.getRegistrationSuccessfullPopupMessage(60),
													"Registarion successfull popup messgae", action.BOOLEAN));
						}
						if (nim.getRegistrationSuccessfulCloseBtn(60) != null) {
							if (click(driver, nim.getRegistrationSuccessfulCloseBtn(60), "Close button",
									action.SCROLLANDBOOLEAN)) {
								ExcelUtils.writeData("Registered", "Users", excelLabel.Variable_Name, "User1", excelLabel.Registered);
								switchToDefaultContent(driver);
								if (bp.clickOnTab(TabName.NIMTab)) {
									switchToFrame(driver, 60, nim.getNIMTabFrame(60));
									if (nim.verifyLandingPageText("Folder Templates")) {
										appLog.info("Landing page text is verified");
									} else {
										appLog.info("Landing page text is not verified");
										saa.assertTrue(false, "Landing page text is not verified");
									}
									List<WebElement> sideMenus = nim.getnimPageSideMenus();
									for (int i = 0; i < sideMenus.size(); i++) {
										sideMenusList.add(sideMenus.get(i).getText().trim());
									}
									if (compareMultipleListWithoutAssertion(sideMenusOnNIM, sideMenusList)) {
										appLog.info("All Menus are verified");
									} else {
										appLog.info("All side menus are not veriified");
										saa.assertTrue(false, "All side menus are not veriified");
									}
								} else {
									appLog.info("Not able to click on nim Tab  so cannot check side menus");
									saa.assertTrue(false, "Not able to click on nim Tab  so cannot check side menus");
								}
							} else {
								appLog.info("Not able to click on registration successful Popup Close Button");
								saa.assertTrue(false,
										"Not able to click on registration successful Popup Close Button");
							}
						} else {
							appLog.info("Regsitartion successful popup Close button is not displaying");
							saa.assertTrue(false, "Regsitartion successful popup Close button is not displaying");
						}
					} else {
						appLog.info("Registration successful popup is not displaying");
						saa.assertTrue(false, "Registration successful popup is not displaying");
					}
				} else {
					appLog.info("not able to click on allow button");
					saa.assertTrue(false, "Not able to click on allow button");
				}
			} else {
				appLog.info("Not able to click on next button");
				saa.assertTrue(false, "Not able to click on close button");
			}
		} else {
			appLog.info("Not able to click on navatar investor manager tab");
			saa.assertTrue(false, "Not able to click on navatar investor manager tab so cannot verify error messages");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc013_CheckErrorMessagesForCRMUserAfterRegistration() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		HomePageBusineesLayer hp = new HomePageBusineesLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		scrollDownThroughWebelement(driver, hp.getNavatarInvestorActivitiesLabel(60),
				"Navatar Investor Manager Activitis label");
		ThreadSleep(2000);
		switchToFrame(driver, 60, hp.getHomePageAlertsFrame(60));
		if (bp.verifyErrorMessageOnPage(HomePageErrorMessage.errorMessageAfterAdminAndCRMUserRegistration,
				hp.getErrorMessageAfterAdminAndCRMUserRegistration(60), "Error Message After CRM user Registration")) {
			appLog.info("Error Message is verified at home page");
		} else {
			saa.assertTrue(false,
					"Error Message is not verified at home page.Expected:"
							+ HomePageErrorMessage.errorMessageAfterAdminAndCRMUserRegistration + " Actual"
							+ getText(driver, hp.getErrorMessageAfterAdminAndCRMUserRegistration(60),
									"Error Message after CRM user registration", action.BOOLEAN));
		}
		switchToDefaultContent(driver);
		scrollDownThroughWebelement(driver, ip.getInstitutionsTab(60), "Institution Tab");
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M1Institution1)) {
				switchToFrame(driver, 60, ip.getWorkspaceFrame(60));
				if (bp.verifyErrorMessageOnPage(
						InstitutionPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace,
						ip.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60),
						"Error Message after CRM user Registration on Instituition page for fundraising workspace")) {
					appLog.info("Error Message is verified  on Instituition page for fundraising workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Instituition page for fundraising workspace.Expected:"
									+ InstitutionPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace
									+ " Actual"
									+ getText(driver,
											ip.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60),
											"Error Message before CRM user registration on instituition page for fundraising workspace",
											action.BOOLEAN));
				}
				if (bp.verifyErrorMessageOnPage(
						InstitutionPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace,
						ip.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),
						"Error Message after CRM user Registration on Instituition page for investor workspace")) {
					appLog.info("Error Message is verified  on Instituition page for investor workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Instituition page for investor workspace.Expected:"
									+ InstitutionPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace
									+ " Actual"
									+ getText(driver,
											ip.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),
											"Error Message after CRM user registration on instituition page for investor workspace",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				saa.assertTrue(false, "Not able to click on created instituition");
			}
		} else {
			appLog.info("Not able to click on institution tab");
			saa.assertTrue(false, "Not able to click on institution tab");
		}
		scrollDownThroughWebelement(driver, cp.getContactsTab(60), "Contacts Tab");
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M1Contact1FirstName, M1Contact1LastName, null)) {
				switchToFrame(driver, 60, cp.getWorkspaceFrameIncontactPage(60));
				if (bp.verifyErrorMessageOnPage(
						ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace,
						cp.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60),
						"Error Message after CRM user Registration on Contact page for fundraising Workspace")) {
					appLog.info("Error Message is verified  on Contact page for fundraising workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Contact page for fundraising workspace.Expected:"
									+ ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace
									+ " Actual"
									+ getText(driver,
											cp.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60),
											"Error Message before CRM user registration on contact page",
											action.BOOLEAN));
				}
				if (bp.verifyErrorMessageOnPage(
						ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace,
						cp.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),
						"Error Message after CRM user Registration on Contact page for investor workspace")) {
					appLog.info("Error Message is verified  on Contact page for investor workspace");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Contact page for investor workspace.Expected:"
									+ ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace
									+ " Actual"
									+ getText(driver,
											cp.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),
											"Error Message after CRM user registration on Contact page for investor workspace",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				saa.assertTrue(false, "Not able to click on created contact");
			}
		} else {
			appLog.info("Not able to click on contacts tab so we cannot check error message on contact page");
			saa.assertTrue(false, "Not able to click on contacts tab so we cannot check error message on contact page");
		}
		scrollDownThroughWebelement(driver, ip.getInstitutionsTab(60), "Institution Tab");
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedLP(M1LimitedPartner1)) {
				switchToFrame(driver, 60, ip.getWorkspaceFrame(60));
				if (bp.verifyErrorMessageOnPage(
						InstitutionPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace,
						ip.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),
						"Error Message after CRM user Registration on Limited Partner page")) {
					appLog.info("Error Message is verified  on Limited Partner page");
				} else {
					saa.assertTrue(false, "Error Message is not verified on Limited Partner page.Expected:"
							+ InstitutionPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace
							+ " Actual"
							+ getText(driver, ip.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),
									"Error Message after CRM user registration on Limited Partner page",
									action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				appLog.info("Not able to click on created limited partner");
				saa.assertTrue(false, "Not able to click on created limited partner");
			}
		} else {
			appLog.info("Not able to click on institution tab");
			saa.assertTrue(false, "Not able to click on institution tab");
		}
		scrollDownThroughWebelement(driver, bp.getUserNameAtUserMenuTab(60), "User Name At User Menu Tab");
		if (bp.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.clickOnCreatedCommitmentId(M1CommitmentId)) {
				switchToFrame(driver, 60, cmp.getWorkspaceFrameOnCommitmentPage(60));
				if (bp.verifyErrorMessageOnPage(CommitmentPageErrorMessage.errorMesageAfterAdminAndCRMUserRegistration,
						cmp.getErrorMessageAfterAdminAndCRMUserRegistration(60),
						"Error Message after CRM user Registration on commitments page")) {
					appLog.info("Error Message is verified  on Commitments page");
				} else {
					saa.assertTrue(false,
							"Error Message is not verified on Limited Partner page.Expected:"
									+ CommitmentPageErrorMessage.errorMesageAfterAdminAndCRMUserRegistration + " Actual"
									+ getText(driver, cmp.getErrorMessageAfterAdminAndCRMUserRegistration(60),
											"Error Message after CRM user registration on commitments page",
											action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			} else {
				appLog.info("Not able to click on created commitment ID");
				saa.assertTrue(false, "Not able to click on created commitment ID");
			}
		} else {
			appLog.info("Not able to click on commitment tab so cannot check error message on Commitments page");
			saa.assertTrue(false,
					"Not able to click on commitment tab so cannot check error message on Commitments page");
		}

		scrollDownThroughWebelement(driver, bp.getFundsTab(60), "Funds Tab");
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M1FundName1)) {
				switchToFrame(driver, 60, fp.getWorkspaceFrameOnFundsPage(60));
				if (fp.getBuildFundraisinWorkspaceButton(60) != null) {
					appLog.info("build Fundarising Workspace button is displaying");
				} else {
					appLog.info("build Fundarising Workspace button is not displaying ");
					saa.assertTrue(false, "build Fundarising Workspace button is not displaying ");
				}
				if (fp.getBuildInvestorWorkspace(60) != null) {
					appLog.info("build Investor Workspace button is displaying");
				} else {
					appLog.info("build Investor Workspace button is not displaying ");
					saa.assertTrue(false, "build Investor Workspace button is not displaying ");
				}
				switchToDefaultContent(driver);
			} else {
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on funds tab so we cannot check error message on funds page");
			saa.assertTrue(false, "Nota ble to click on funds tab so we cannot check error message on funds page");
		}
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc014_VerifyInternalUsersFromAdminSideAndEditIcon() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		WebElement ele = null;
		List<String> ColoumnList = new ArrayList<String>();
		String ColoumnHeaders = "Access<break>User<break>Admin";
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (nim.verifyLandingPageText("Internal Users")) {
				appLog.info("Internal User tab is selected");
			} else {
				appLog.info("Internal User Tab is not selected");
				saa.assertTrue(false, "Internal User Tab is not selected");
			}
			List<WebElement> CRMColoumns = nim.getCRMAdminColounHeaders();
			for (int i = 0; i < CRMColoumns.size(); i++) {
				ColoumnList.add(CRMColoumns.get(i).getText().trim());
			}
			if (compareMultipleListWithoutAssertion(ColoumnHeaders, ColoumnList)) {
				appLog.info("CRM Admin coloumn Headers are matched");
			} else {
				appLog.info("CRM Admin coloumn Headers are not matched.");
				saa.assertTrue(false, "CRM Admin coloumn Headers are not matched.");
			}
			ele = FindElement(driver, "//span[text()='" + superAdminFirstName + " " + superAdminLastName
					+ "']/../..//input[@type='checkbox']", "Super admin Checkbox", action.SCROLLANDBOOLEAN, 60);
			if (isSelected(driver, ele, "Admin User Checkbox")) {
				appLog.info("Admin User checkbox is selected");
			} else {
				appLog.info("Admin User checkbox is not selected");
				saa.assertTrue(false, "Admin User checkbox is not selected");
			}
			ele = FindElement(driver, "//span[text()='" + superAdminFirstName + " " + superAdminLastName
					+ "']/../..//input[@type='radio']", "Super admin radio butto", action.BOOLEAN, 60);
			if (isSelected(driver, ele, "Admin User radio button")) {
				appLog.info("Admin User radio button is selected");
			} else {
				appLog.info("Admin User radio button is not selected");
				saa.assertTrue(false, "Admin User radio button is not selected");
			}
			if (nim.getInternalUsersLiceneMessage(60) != null) {
				appLog.info("Internal user license message is displaying");
				String licenseMessage = nim.getInternalUsersLiceneMessage(60).getText().trim();
				if (licenseMessage.equalsIgnoreCase("Internal Users (1 out of 10 Licenses Used)")) {
					appLog.info("Internal user license mesage is verified");
				} else {
					appLog.info("Internal user license message is not verified");
					saa.assertTrue(false, "Internal user license message is not verified");
				}
			} else {
				appLog.info("Internal user license message is not displaying");
				saa.assertTrue(false, "Internal user license message is not displaying");
			}
			if (nim.getEditIcon(60) != null) {
				appLog.info("Edit icon is displaying");
			} else {
				appLog.info("Edit icon is not displaying");
				saa.assertTrue(false, "Edit icon is not displaying");
			}
			ele = FindElement(driver, "//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
					+ "']/../..//input[@type='checkbox']", "CRM User 1 Checkbox", action.SCROLLANDBOOLEAN, 60);
			if (isSelected(driver, ele, "CRM User 1 Checkbox")) {
				appLog.info("CRM User 1 checkbox is selected");
			} else {
				appLog.info("CRM User 1 checkbox is not selected");
				saa.assertTrue(false, "CRM User 1 checkbox is not selected");
			}
			ele = FindElement(driver, "//span[text()='" + CRMUser2FirstName + " " + CRMUser2LastName
					+ "']/../..//input[@type='checkbox']", "CRM User 2 Checkbox", action.SCROLLANDBOOLEAN, 60);
			if (isSelected(driver, ele, "CRM User 2 Checkbox")) {
				appLog.info("CRM User 2 checkbox is selected");
				saa.assertTrue(false, "CRM User 2 checkbox is  selected");
			} else {
				appLog.info("CRM User 2 checkbox is not selected");
			}
			ele = FindElement(driver, "//span[text()='" + CRMUser3FirstName + " " + CRMUser3LastName
					+ "']/../..//input[@type='checkbox']", "CRM User 3 Checkbox", action.SCROLLANDBOOLEAN, 60);
			if (isSelected(driver, ele, "CRM User 3 Checkbox")) {
				appLog.info("CRM User 3 checkbox is selected");
				saa.assertTrue(false, "CRM User 3 checkbox is  selected");
			} else {
				appLog.info("CRM User 3 checkbox is not selected");
			}
			ele = FindElement(driver,
					"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName + "']/../..//input[@type='radio']",
					"CRM User 1 radio Button", action.SCROLLANDBOOLEAN, 60);
			if (isSelected(driver, ele, "CRM User 1 radio Button")) {
				appLog.info("CRM User 1 radio Button is selected");
				saa.assertTrue(false, "CRM User 1 radio Button is selected");
			} else {
				appLog.info("CRM User 1 radio Button is not selected");

			}
			ele = FindElement(driver,
					"//span[text()='" + CRMUser2FirstName + " " + CRMUser2LastName + "']/../..//input[@type='radio']",
					"CRM User 2 radio Button", action.SCROLLANDBOOLEAN, 60);
			if (isSelected(driver, ele, "CRM User 2 radio Button")) {
				appLog.info("CRM User 2 radio Button is selected");
				saa.assertTrue(false, "CRM User 2 radio Button is selected");
			} else {
				appLog.info("CRM User 2 radio button is not selected");
			}
			ele = FindElement(driver,
					"//span[text()='" + CRMUser3FirstName + " " + CRMUser3LastName + "']/../..//input[@type='radio']",
					"CRM User 3 radio Button", action.SCROLLANDBOOLEAN, 60);
			if (isSelected(driver, ele, "CRM User 3 radio Button")) {
				appLog.info("CRM User 3 radio Button is selected");
				saa.assertTrue(false, "CRM User 3 radio Button is  selected");
			} else {
				appLog.info("CRM User 3 radio button is not selected");
			}
			if (nim.getInfoIconOnAdminLabel(60) != null) {
				appLog.info("Info icon is displaying");
				mouseOverOperation(driver, nim.getInfoIconOnAdminLabel(60));
				String tooltipMessage = nim.getInfoIcontoolTipMessage(60).getText().trim();
				if (tooltipMessage.equalsIgnoreCase(NIMPageErrorMessage.adminLabelToolTipMessage)) {
					appLog.info("ToolTip Message is verified");
				} else {
					appLog.info("Tool tip error Message is not verified");
					saa.assertTrue(false, "Tool tip error Message is not verified");
				}
			} else {
				appLog.info("Info icon is not displaying");
				saa.assertTrue(false, "Info Icon is not displaying");
			}
			if (nim.getSortingIconOnUserLabel(60) != null) {
				appLog.info("Sorting icon is displaying on user label ");
			} else {
				appLog.info("Sorting icon is not displaying on user label");
				saa.assertTrue(false, "Sorting icon is not displaying on user label");
			}
			bp.checkSorting(SortOrder.Assecending, nim.getUsersListInInternalUsers());
			if (nim.clickOnEditIcon()) {
				if (nim.getGoBackLink(60) != null) {
					appLog.info("go Back link is displaying");
				} else {
					appLog.info("Go back link is not displaying");
					saa.assertTrue(false, "Go back link is not displaying");
				}
				ele = FindElement(driver,
						"//span[text()='" + superAdminFirstName + " " + superAdminLastName
								+ "']/../..//input[@type='checkbox']",
						"Super admin Checkbox", action.SCROLLANDBOOLEAN, 60);
				if (isEnabled(driver, ele, "Admin User Checkbox")) {
					appLog.info("Admin User checkbox is enabled");
					saa.assertTrue(false, "Admin User checkbox is enabled");
				} else {
					appLog.info("Admin User checkbox is not enabled");
				}
				ele = FindElement(driver, "//span[text()='" + superAdminFirstName + " " + superAdminLastName
						+ "']/../..//input[@type='radio']", "Super admin radio butto", action.BOOLEAN, 60);
				if (isEnabled(driver, ele, "Admin User Radio Button")) {
					appLog.info("Admin User  Radio Button is enabled");
					saa.assertTrue(false, "Admin User  Radio Button is enabled");
				} else {
					appLog.info("Admin User  Radio Button is not enabled");
				}
				ele = FindElement(driver, "//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
						+ "']/../..//input[@type='radio']", "CRM User1 radio butto", action.BOOLEAN, 60);
				if (isEnabled(driver, ele, "CRM User Radio Button")) {
					appLog.info("CRM User 1  Radio Button is enabled");
				} else {
					appLog.info("CRM User  Radio Button is not enabled");
					saa.assertTrue(false, "CRM User  Radio Button is not enabled");
				}
				ele = FindElement(driver,
						"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
								+ "']/../..//input[@type='checkbox']",
						"Super admin Checkbox", action.SCROLLANDBOOLEAN, 60);
				if (isEnabled(driver, ele, "CRM User 1 Checkbox")) {
					appLog.info("CRM User 1 checkbox is enabled");
				} else {
					appLog.info("CRM User 1 checkbox is not enabled");
					saa.assertTrue(false, "CRM User 1 checkbox is not enabled");
				}
				ele = FindElement(driver,
						"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
								+ "']/../..//input[@type='checkbox']",
						"CRM User 1 Checkbox", action.SCROLLANDBOOLEAN, 60);
				if (isSelected(driver, ele, "CRM User 1 Checkbox")) {
					appLog.info("CRM User 1 checkbox is selected");
				} else {
					appLog.info("CRM User 1 checkbox is not selected");
					saa.assertTrue(false, "CRM User 1 checkbox is not selected");
				}
				ele = FindElement(driver,
						"//span[text()='" + CRMUser2FirstName + " " + CRMUser2LastName
								+ "']/../..//input[@type='checkbox']",
						"CRM User 2 Checkbox", action.SCROLLANDBOOLEAN, 60);
				if (isSelected(driver, ele, "CRM User 2 Checkbox")) {
					appLog.info("CRM User 2 checkbox is selected");
					saa.assertTrue(false, "CRM User 2 checkbox is  selected");
				} else {
					appLog.info("CRM User checkbox is not selected");
				}
				ele = FindElement(driver,
						"//span[text()='" + CRMUser3FirstName + " " + CRMUser3LastName
								+ "']/../..//input[@type='checkbox']",
						"CRM User 3 Checkbox", action.SCROLLANDBOOLEAN, 60);
				if (isSelected(driver, ele, "CRM User 3 Checkbox")) {
					appLog.info("CRM User 3 checkbox is selected");
					saa.assertTrue(false, "CRM User 3 checkbox is  selected");
				} else {
					appLog.info("CRM User 3 checkbox is not selected");
				}
				if (click(driver, nim.getGoBackLink(60), "Go Back Link", action.SCROLLANDBOOLEAN)) {
					if (nim.getEditIcon(60) != null) {
						appLog.info("Edit icon is available");
					} else {
						appLog.info("Edit icon is not available");
						saa.assertTrue(false, "Edit icon is not available");
					}
					if (nim.getGoBackLink(10) == null) {
						appLog.info("Go back link is not displaying");
					} else {
						appLog.info("Go back link is displaying");
						saa.assertTrue(false, "Go back link is displaying");
					}
				} else {
					appLog.info("Not able to click on go back link");
					saa.assertTrue(false, "Not able to click on go back link");
				}
			} else {
				appLog.info("Not able to click on edit icon");
				saa.assertTrue(false, "Not able to click on edit icon");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.info("Not able to click on NIM Tab");
			saa.assertTrue(false, "Not able to click on NIM Tab");
		}
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc015_VerifyConfirmUserPermissionRemovalConfirmUserPermissionAdditionConfirmAccessPopUps() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		WebElement ele = null;
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (nim.clickOnEditIcon()) {
				ele = FindElement(driver,
						"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
								+ "']/../..//input[@type='checkbox']",
						"CRM User 1 Checkbox", action.SCROLLANDBOOLEAN, 60);
				if (click(driver, ele, "CRM User1 Checkbox", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					if (nim.getConfirmUserPermissionRemovalPopupHeader(60) != null) {
						ThreadSleep(2000);
						if (isDisplayed(driver, nim.getUserPermissionRemovalPopupYesButton(60), "Visibility", 60,
								"User Permission Removal Popup Yes button") != null) {
							appLog.info("Yes Button is displaying on confirm user permission removal popup");
						} else {
							appLog.info("Yes Button is not displaying on confirm user permission removal popup");
							saa.assertTrue(false,
									"Yes Button is not displaying on confirm user permission removal popup");
						}
						if (isDisplayed(driver, nim.getUserPermissionRemovalPopupNoButton(60), "Visibility", 60,
								"User Permission Removal Popup No button") != null) {
							appLog.info("No Button is displaying on confirm user permission removal popup");
						} else {
							appLog.info("No Button is not displaying on confirm user permission removal popup");
							saa.assertTrue(false,
									"No Button is not displaying on confirm user permission removal popup");
						}
						if (isDisplayed(driver, nim.getUserPermissionRemovalPopupCloseButton(60), "Visibility", 60,
								"User Permission Removal Popup Close button") != null) {
							appLog.info("Close Button is displaying on confirm user permission removal popup");
						} else {
							appLog.info("Close Button is not displaying on confirm user permission removal popup");
							saa.assertTrue(false,
									"Close Button is not displaying on confirm user permission removal popup");
						}
						if (click(driver, nim.getUserPermissionRemovalPopupCloseButton(60),
								"User Permission Removal Popup Close button ", action.SCROLLANDBOOLEAN)) {
							ThreadSleep(4000);
							if (isSelected(driver, ele, "CRM User1 Checkbox")) {
								appLog.info("CRM User 1 check box is selected");
							} else {
								appLog.info("CRM User 1 checkbox is not selected");
								saa.assertTrue(false, "CRM User 1 checkbox is not selected");
							}
							ThreadSleep(2000);
							ele = FindElement(driver,
									"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
											+ "']/../..//input[@type='radio']",
									"CRM User 1 Checkbox", action.SCROLLANDBOOLEAN, 60);
							if (isSelected(driver, ele, "CRM User1 radio button")) {
								appLog.info("CRM User 1 radio button is selected");
								saa.assertTrue(false, "CRM User radio button is  selected");
							} else {
								appLog.info("CRM User radio button is not selected");
							}
							if (nim.getGoBackLink(60) != null) {
								appLog.info("Go back link is available");
							} else {
								appLog.info("Go back link is not available");
								saa.assertTrue(false, "Go back link is not available");
							}
							String licenseMessage = nim.getInternalUsersLiceneMessage(60).getText().trim();
							if (licenseMessage.equalsIgnoreCase("Internal Users (1 out of 10 Licenses Used)")) {
								appLog.info("Internal user license mesage is verified");
							} else {
								appLog.info("Internal user license message is not verified");
								saa.assertTrue(false, "Internal user license message is not verified");
							}
						} else {
							appLog.info("Not able to click on User Permission Removal Popup Close button");
							saa.assertTrue(false, "Not able to click on User Permission Removal Popup Close button");
						}
					} else {
						appLog.info("Confirm User permission Removal popup is not displaying");
						saa.assertTrue(false, "Confirm User permission Removal popup is not displaying");
					}
				} else {
					appLog.info("Not able to click on CRM User1 Checkbox");
					saa.assertTrue(false, "Not able to click on CRM User1 Checkbox");
				}
				ThreadSleep(2000);
				ele = FindElement(driver,
						"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
								+ "']/../..//input[@type='checkbox']",
						"CRM User 1 Checkbox", action.SCROLLANDBOOLEAN, 60);
				if (click(driver, ele, "CRM User1 Checkbox", action.SCROLLANDBOOLEAN)) {
					if (click(driver, nim.getUserPermissionRemovalPopupNoButton(60),
							"User Permission Removal Popup No button ", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(4000);
						if (isSelected(driver, ele, "CRM User1 Checkbox")) {
							appLog.info("CRM User 1 check box is selected");
						} else {
							appLog.info("CRM User 1 checkbox is not selected");
							saa.assertTrue(false, "CRM User 1 checkbox is not selected");
						}
						ThreadSleep(2000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
										+ "']/../..//input[@type='radio']",
								"CRM User 1 Checkbox", action.SCROLLANDBOOLEAN, 60);
						if (isSelected(driver, ele, "CRM User1 radio button")) {
							appLog.info("CRM User 1 radio button is selected");
							saa.assertTrue(false, "CRM User radio button is  selected");
						} else {
							appLog.info("CRM User radio button is not selected");
						}
						if (nim.getGoBackLink(60) != null) {
							appLog.info("Go back link is available");
						} else {
							appLog.info("Go back link is not available");
							saa.assertTrue(false, "Go back link is not available");
						}
						String licenseMessage = nim.getInternalUsersLiceneMessage(60).getText().trim();
						if (licenseMessage.equalsIgnoreCase("Internal Users (1 out of 10 Licenses Used)")) {
							appLog.info("Internal user license mesage is verified");
						} else {
							appLog.info("Internal user license message is not verified");
							saa.assertTrue(false, "Internal user license message is not verified");
						}
					} else {
						appLog.info("Not able to click on User Permission Removal Popup No button");
						saa.assertTrue(false, "Not able to click on User Permission Removal Popup No button");
					}
				} else {
					appLog.info("Not able to click on CRM User1 Checkbox");
					saa.assertTrue(false, "Not able to click on CRM User1 Checkbox");
				}
				ThreadSleep(2000);
				ele = FindElement(driver,
						"//span[text()='" + CRMUser2FirstName + " " + CRMUser2LastName
								+ "']/../..//input[@type='checkbox']",
						"CRM User 2 Checkbox", action.SCROLLANDBOOLEAN, 60);
				if (click(driver, ele, "CRM User 2 checkbox", action.SCROLLANDBOOLEAN)) {
					if (nim.getConfirmUserPermissionAdditionpopupHeader(60) != null) {
						appLog.info("Confirm User permission Addition popup is displaying");
						if (isDisplayed(driver, nim.getConfirmUserPermissionAdditionPopupYesButton(60), "Visibility",
								60, "User Permission addition Popup Yes button") != null) {
							appLog.info("Yes Button is displaying on confirm user permission addition popup");
						} else {
							appLog.info("Yes Button is not displaying on confirm user permission addition popup");
							saa.assertTrue(false,
									"Yes Button is not displaying on confirm user permission addition popup");
						}
						if (isDisplayed(driver, nim.getConfirmUserPermissionAdditionPopupNoButton(60), "Visibility", 60,
								"User Permission Addition Popup No button") != null) {
							appLog.info("No Button is displaying on confirm user permission Addition popup");
						} else {
							appLog.info("No Button is not displaying on confirm user permission Addition popup");
							saa.assertTrue(false,
									"No Button is not displaying on confirm user permission Addition popup");
						}
						if (isDisplayed(driver, nim.getConfirmUserPermissionAdditionPopupCloseButton(60), "Visibility",
								60, "User Permission Addition Popup Close button") != null) {
							appLog.info("Close Button is displaying on confirm user permission Addition popup");
						} else {
							appLog.info("Close Button is not displaying on confirm user permission Addition popup");
							saa.assertTrue(false,
									"Close Button is not displaying on confirm user permission Addition popup");
						}
						if (click(driver, nim.getConfirmUserPermissionAdditionPopupCloseButton(60),
								"User Permission Addition Popup Close button", action.SCROLLANDBOOLEAN)) {
							if (isSelected(driver, ele, "CRM User2 Checkbox")) {
								appLog.info("CRM User 2 check box is selected");
								saa.assertTrue(false, "CRM User  2 checkbox is selected");
							} else {
								appLog.info("CRM User 2 checkbox  is not selected");
							}
							ThreadSleep(2000);
							ele = FindElement(driver,
									"//span[text()='" + CRMUser2FirstName + " " + CRMUser2LastName
											+ "']/../..//input[@type='radio']",
									"CRM User 2 Radio button", action.SCROLLANDBOOLEAN, 60);
							if (isSelected(driver, ele, "CRM User2 radio button")) {
								appLog.info("CRM User 2 radio button is selected");
								saa.assertTrue(false, "CRM User  2 radio button is  selected");
							} else {
								appLog.info("CRM User  2 radio button is not selected");
							}
							if (nim.getGoBackLink(60) != null) {
								appLog.info("Go back link is available");
							} else {
								appLog.info("Go back link is not available");
								saa.assertTrue(false, "Go back link is not available");
							}
							String licenseMessage = nim.getInternalUsersLiceneMessage(60).getText().trim();
							if (licenseMessage.equalsIgnoreCase("Internal Users (1 out of 10 Licenses Used)")) {
								appLog.info("Internal user license mesage is verified");
							} else {
								appLog.info("Internal user license message is not verified");
								saa.assertTrue(false, "Internal user license message is not verified");
							}
						} else {
							appLog.info("Not able to click on close button");
							saa.assertTrue(false, "Not able to click on close button");
						}
					} else {
						appLog.info("Confirm User Permission popup is not displaying");
						saa.assertTrue(false, "Confirm User Permission popup is not displaying");
					}
				} else {
					appLog.info("Not able to click on CRM User2 checkbox");
					saa.assertTrue(false, "Not able to click on CRM User2 checkbox");
				}
				ThreadSleep(2000);
				ele = FindElement(driver,
						"//span[text()='" + CRMUser2FirstName + " " + CRMUser2LastName
								+ "']/../..//input[@type='checkbox']",
						"CRM User 2 Checkbox", action.SCROLLANDBOOLEAN, 60);
				if (click(driver, ele, "CRM User2 Checkbox", action.SCROLLANDBOOLEAN)) {
					if (click(driver, nim.getConfirmUserPermissionAdditionPopupNoButton(60),
							"User Permission Addition Popup No button ", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						if (isSelected(driver, ele, "CRM User2 Checkbox")) {
							appLog.info("CRM User 2 check box is selected");
							saa.assertTrue(false, "CRM User 2 checkbox is  selected");
						} else {
							appLog.info("CRM User 2 checkbox is not selected");
						}
						ThreadSleep(2000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser2FirstName + " " + CRMUser2LastName
										+ "']/../..//input[@type='radio']",
								"CRM User 2 radio button", action.SCROLLANDBOOLEAN, 60);
						if (isSelected(driver, ele, "CRM User2 radio button")) {
							appLog.info("CRM User 2 radio button is selected");
							saa.assertTrue(false, "CRM User radio button is  selected");
						} else {
							appLog.info("CRM User radio button is not selected");
						}
						if (nim.getGoBackLink(60) != null) {
							appLog.info("Go back link is available");
						} else {
							appLog.info("Go back link is not available");
							saa.assertTrue(false, "Go back link is not available");
						}
						String licenseMessage = nim.getInternalUsersLiceneMessage(60).getText().trim();
						if (licenseMessage.equalsIgnoreCase("Internal Users (1 out of 10 Licenses Used)")) {
							appLog.info("Internal user license mesage is verified");
						} else {
							appLog.info("Internal user license message is not verified");
							saa.assertTrue(false, "Internal user license message is not verified");
						}
					} else {
						appLog.info("Not able to click on User Permission Removal Popup No button");
						saa.assertTrue(false, "Not able to click on User Permission Removal Popup No button");
					}
				} else {
					appLog.info("Not able to click on CRM User2 Checkbox");
					saa.assertTrue(false, "Not able to click on CRM User2 Checkbox");
				}
				ThreadSleep(2000);
				ele = FindElement(driver,
						"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
								+ "']/../..//input[@type='radio']",
						"CRM User 1 radio button", action.SCROLLANDBOOLEAN, 60);
				if (click(driver, ele, "CRM User1 admin radio button", action.SCROLLANDBOOLEAN)) {
					if (nim.getConfirmAccessPopupHeader(60) != null) {
						appLog.info("Confirm Access Popup is displaying");
						if (isDisplayed(driver, nim.getConfirmAccessPopupYesButton(60), "Visibility", 60,
								"Confirm Access Popup Yes button") != null) {
							appLog.info("Yes Button is displaying on Confirm Access popup");
						} else {
							appLog.info("Yes Button is not displaying on Confirm Access popup");
							saa.assertTrue(false, "Yes Button is not displaying on Confirm Access popup");
						}
						if (isDisplayed(driver, nim.getConfirmAccessPopupNoButton(60), "Visibility", 60,
								"Confirm Access No button") != null) {
							appLog.info("No Button is displaying on Confirm Access popup");
						} else {
							appLog.info("No Button is not displaying on Confirm Access popup");
							saa.assertTrue(false, "No Button is not displaying on Confirm Access popup");
						}
						if (isDisplayed(driver, nim.getConfirmAccessPopupCloseButton(60), "Visibility", 60,
								"Confirm Access Popup Close button") != null) {
							appLog.info("Close Button is displaying on Confirm Access popup");
						} else {
							appLog.info("Close Button is not displaying on Confirm Access popup");
							saa.assertTrue(false, "Close Button is not displaying on Confirm Access popup");
						}
						if (bp.verifyErrorMessageOnPage(NIMPageErrorMessage.adminAccessConfirmationMessage,
								nim.getConfirmAccessPopupErrorMessage(60), "Message when giving admin permission")) {
							appLog.info("Message when giving admin permission is verified.");
						} else {
							saa.assertTrue(false,
									"Message when giving admin permission is not verified.Expected:"
											+ NIMPageErrorMessage.adminAccessConfirmationMessage + " Actual"
											+ getText(driver, nim.getConfirmAccessPopupErrorMessage(60),
													"Message when giving admin permission", action.BOOLEAN));
						}
						ThreadSleep(2000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
										+ "']/../..//input[@type='checkbox']",
								"CRM User 1 Checkbox", action.SCROLLANDBOOLEAN, 60);
						if (click(driver, nim.getConfirmAccessPopupCloseButton(60), "Close Button",
								action.SCROLLANDBOOLEAN)) {
							if (isSelected(driver, ele, "CRM User1 Checkbox")) {
								appLog.info("CRM User 1 check box is selected");
							} else {
								appLog.info("CRM User 1 checkbox is not selected");
								saa.assertTrue(false, "CRM User 1 checkbox is not selected");
							}
							ThreadSleep(2000);
							ele = FindElement(driver,
									"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
											+ "']/../..//input[@type='radio']",
									"CRM User 1 Checkbox", action.SCROLLANDBOOLEAN, 60);
							if (isSelected(driver, ele, "CRM User1 radio button")) {
								appLog.info("CRM User 1 radio button is selected");
								saa.assertTrue(false, "CRM User radio button is  selected");
							} else {
								appLog.info("CRM User radio button is not selected");
							}
							if (nim.getGoBackLink(60) != null) {
								appLog.info("Go back link is available");
							} else {
								appLog.info("Go back link is not available");
								saa.assertTrue(false, "Go back link is not available");
							}
							String licenseMessage = nim.getInternalUsersLiceneMessage(60).getText().trim();
							if (licenseMessage.equalsIgnoreCase("Internal Users (1 out of 10 Licenses Used)")) {
								appLog.info("Internal user license mesage is verified");
							} else {
								appLog.info("Internal user license message is not verified");
								saa.assertTrue(false, "Internal user license message is not verified");
							}
						} else {
							appLog.info("Not able to click on Close Button");
							saa.assertTrue(false, "Not able to click on Close Button");
						}
					} else {
						appLog.info("Confirm access popup header is not displaying");
						saa.assertTrue(false, "Confirm access popup header is not displaying");
					}
				} else {
					appLog.info("Not able to click on CRM User1 radio button");
					saa.assertTrue(false, "Not able to click on CRM User1 radio button");
				}
				ThreadSleep(2000);
				ele = FindElement(driver,
						"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
								+ "']/../..//input[@type='radio']",
						"CRM User 1 radio button", action.SCROLLANDBOOLEAN, 60);
				if (click(driver, ele, "CRM User1 admin radio button", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					if (click(driver, nim.getConfirmAccessPopupNoButton(60), "No Button", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						ThreadSleep(2000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
										+ "']/../..//input[@type='checkbox']",
								"CRM User 1 Checkbox", action.SCROLLANDBOOLEAN, 60);
						if (isSelected(driver, ele, "CRM User1 Checkbox")) {
							appLog.info("CRM User 1 check box is selected");
						} else {
							appLog.info("CRM User 1 checkbox is not selected");
							saa.assertTrue(false, "CRM User 1 checkbox is not selected");
						}
						ThreadSleep(2000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
										+ "']/../..//input[@type='radio']",
								"CRM User 1 radio button", action.SCROLLANDBOOLEAN, 60);
						if (isSelected(driver, ele, "CRM User1 radio button")) {
							appLog.info("CRM User 1 radio button is selected");
							saa.assertTrue(false, "CRM User radio button is  selected");
						} else {
							appLog.info("CRM User radio button is not selected");
						}
						if (nim.getGoBackLink(60) != null) {
							appLog.info("Go back link is available");
						} else {
							appLog.info("Go back link is not available");
							saa.assertTrue(false, "Go back link is not available");
						}
						String licenseMessage = nim.getInternalUsersLiceneMessage(60).getText().trim();
						if (licenseMessage.equalsIgnoreCase("Internal Users (1 out of 10 Licenses Used)")) {
							appLog.info("Internal user license mesage is verified");
						} else {
							appLog.info("Internal user license message is not verified");
							saa.assertTrue(false, "Internal user license message is not verified");
						}
					} else {
						appLog.info("Not able to clikc on No Button");
						saa.assertTrue(false, "Not able to click on No Button");
					}
				} else {
					appLog.info("Not able to click on CRM User1 radio button");
					saa.assertTrue(false, "Not able to click on CRM User1 radio button");
				}
			} else {
				appLog.info("Not able to click on edit icon");
				saa.assertTrue(false, "Not able to click on edit icon");
			}
		} else {
			appLog.info("Not able to click on NIM Tab so cannot verify different popups");
			saa.assertTrue(false, "Not able to click on NIM Tab so cannot verify different popups");
		}
		switchToDefaultContent(driver);
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc016_VerifyUncheckingAccessCheckbox() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		WebElement ele = null;
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (nim.clickOnEditIcon()) {
				ele = FindElement(driver,
						"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
								+ "']/../..//input[@type='checkbox']",
						"CRM User 1 Checkbox", action.SCROLLANDBOOLEAN, 60);
				if (click(driver, ele, "CRM User1 Checkbox", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					if (click(driver, nim.getUserPermissionRemovalPopupYesButton(60),
							"User Access Removal popup Yes button", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(5000);
						if (isSelected(driver, ele, "CRM User1 Checkbox")) {
							appLog.info("CRM User 1 check box is selected");
							saa.assertTrue(false, "CRM User 1 checkbox is selected");
						} else {
							appLog.info("CRM User 1 checkbox is not selected");

						}
						ThreadSleep(2000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
										+ "']/../..//input[@type='radio']",
								"CRM User 1 Checkbox", action.SCROLLANDBOOLEAN, 60);
						if (isSelected(driver, ele, "CRM User1 radio button")) {
							appLog.info("CRM User 1 radio button is selected");
							saa.assertTrue(false, "CRM User radio button is  selected");
						} else {
							appLog.info("CRM User radio button is not selected");
						}
						if (nim.getGoBackLink(60) != null) {
							appLog.info("Go back link is available");
						} else {
							appLog.info("Go back link is not available");
							saa.assertTrue(false, "Go back link is not available");
						}
						String licenseMessage = nim.getInternalUsersLiceneMessage(60).getText().trim();
						if (licenseMessage.equalsIgnoreCase("Internal Users (0 out of 10 Licenses Used)")) {
							appLog.info("Internal user license mesage is verified");
						} else {
							appLog.info("Internal user license message is not verified");
							saa.assertTrue(false, "Internal user license message is not verified");
						}
					} else {
						appLog.info("Not able to click on yes button");
						saa.assertTrue(false, "Not able to click on yes button");
					}
				} else {
					appLog.info("Not able to click on CRM User 1 checkbox");
					saa.assertTrue(false, "Not able to click on CRM User 1 checkbox");
				}
			} else {
				appLog.info("Not able to click on edit icon");
				saa.assertTrue(false, "Not able to click on edit icon");
			}
		} else {
			appLog.info("Not able to click on NIM Tab");
			saa.assertTrue(false, "Not able to click on NIM Tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);
		nim = new NIMPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (bp.verifyErrorMessageOnPage(NIMPageErrorMessage.errorMessageBeforeGivingInternalUserAccess,
					bp.getErrorMessageBeforeGivingInternalUserAccess(60),
					"Error Message After withdrawing internal user access on NIM page")) {
				appLog.info("Error Message is verified  on NIM page");
			} else {
				saa.assertTrue(false,
						"Error Message is not verified on NIM page.Expected:"
								+ NIMPageErrorMessage.errorMessageBeforeGivingInternalUserAccess + " Actual"
								+ getText(driver, bp.getErrorMessageBeforeGivingInternalUserAccess(60),
										"Error Message After withdrawing internal user access on NIM page",
										action.BOOLEAN));
			}
		} else {
			appLog.info("Not able to click on NIM Tab so cannot check error message");
			saa.assertTrue(false, "Not able to click on NIM Tab so cannot check error message");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc017_VerifyCheckingAccessCheckboxAndErrorMessageAtUserSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		WebElement ele = null;
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (nim.clickOnEditIcon()) {
				ele = FindElement(driver,
						"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
								+ "']/../..//input[@type='checkbox']",
						"CRM User 1 Checkbox", action.SCROLLANDBOOLEAN, 60);
				if (click(driver, ele, "CRM User1 Checkbox", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					if (click(driver, nim.getConfirmUserPermissionAdditionPopupYesButton(60),
							"User Access Addition popup Yes button", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(5000);
						if (isSelected(driver, ele, "CRM User1 Checkbox")) {
							appLog.info("CRM User 1 check box is selected");
						} else {
							appLog.info("CRM User 1 checkbox is not selected");
							saa.assertTrue(false, "CRM User 1 checkbox is not selected");

						}
						ThreadSleep(2000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
										+ "']/../..//input[@type='radio']",
								"CRM User 1 radio button", action.SCROLLANDBOOLEAN, 60);
						if (isSelected(driver, ele, "CRM User1 radio button")) {
							appLog.info("CRM User 1 radio button is selected");
							saa.assertTrue(false, "CRM User radio button is  selected");
						} else {
							appLog.info("CRM User radio button is not selected");
						}
						if (nim.getGoBackLink(60) != null) {
							appLog.info("Go back link is available");
						} else {
							appLog.info("Go back link is not available");
							saa.assertTrue(false, "Go back link is not available");
						}
						String licenseMessage = nim.getInternalUsersLiceneMessage(60).getText().trim();
						if (licenseMessage.equalsIgnoreCase("Internal Users (1 out of 10 Licenses Used)")) {
							appLog.info("Internal user license mesage is verified");
						} else {
							appLog.info("Internal user license message is not verified");
							saa.assertTrue(false, "Internal user license message is not verified");
						}
					} else {
						appLog.info("Not able to click on yes button");
						saa.assertTrue(false, "Not able to click on yes button");
					}
				} else {
					appLog.info("Not able to click on CRM User 1 checkbox");
					saa.assertTrue(false, "Not able to click on CRM User 1 checkbox");
				}
			} else {
				appLog.info("Not able to click on edit icon");
				saa.assertTrue(false, "Not able to click on edit icon");
			}
		} else {
			appLog.info("Not able to click on NIM Tab");
			saa.assertTrue(false, "Not able to click on NIM Tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);
		nim = new NIMPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (nim.getRegistrationSuccessfulCloseBtn(60) != null) {
				if (bp.verifyErrorMessageOnPage(NIMPageErrorMessage.registrationSuccessfulPopMessage,
						nim.getRegistrationSuccessfullPopupMessage(60), "Registarion successfull popup messgae")) {
					appLog.info("Registarion successfull popup messgae is verified");
				} else {
					saa.assertTrue(false,
							"Registarion successfull popup messgae is not verified.Expected:"
									+ NIMPageErrorMessage.registrationSuccessfulPopMessage + " Actual"
									+ getText(driver, nim.getRegistrationSuccessfullPopupMessage(60),
											"Registarion successfull popup messgae", action.BOOLEAN));
				}
				if (click(driver, nim.getRegistrationSuccessfulCloseBtn(60),
						"Registration successful popup close button", action.SCROLLANDBOOLEAN)) {
					switchToDefaultContent(driver);
					if (bp.clickOnTab(TabName.NIMTab)) {
						switchToFrame(driver, 60, nim.getNIMTabFrame(60));
						if (nim.verifyLandingPageText("Folder Templates")) {
							appLog.info("Landing page text is verified");
						} else {
							appLog.info("Landing page text is not verified");
							saa.assertTrue(false, "Landing page text is not verified");
						}
						if (click(driver, nim.getInternalUsersTab(60), "Internal User Tab", action.SCROLLANDBOOLEAN)) {
							if (nim.clickOnEditIcon()) {
								if (nim.getInsufficientPermissionHeader(60,sideMenu.InternalUsers) != null) {
									if (bp.verifyErrorMessageOnPage(
											NIMPageErrorMessage.insufficientPermissionErrorMessage,
											nim.getInsufficientPermissionErrorMesage(60, sideMenu.InternalUsers),
											"Insufficient Permission Error messgae")) {
										appLog.info("Insufficient Permission Error messgae is verified");
									} else {
										saa.assertTrue(false,
												"Insufficient Permission Error messgae is not verified.Expected:"
														+ NIMPageErrorMessage.insufficientPermissionErrorMessage
														+ " Actual"
														+ getText(driver, nim.getInsufficientPermissionErrorMesage(60, sideMenu.InternalUsers),
																"Insufficient Permission Error messgae",
																action.BOOLEAN));
									}
									if (click(driver, nim.getInsufficientPermissionClosButton(60,sideMenu.InternalUsers),
											"Insufficient permission Close button", action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on insufficient permission close button");
										if (nim.verifyLandingPageText("Internal Users")) {
											appLog.info("Internal Users page is avaiable");
										} else {
											appLog.info("Internal Users page is not avaiable");
											saa.assertTrue(false, "Internal Users page is not avaiable");
										}
										if (nim.getEditIcon(60) != null) {
											appLog.info("Edit icon is displaying");
										} else {
											appLog.info("Edit icon is displaying");
											saa.assertTrue(false, "Edit icon is not displaying");
										}
									} else {
										appLog.info("Not able to click on insufficient permission close button");
										saa.assertTrue(false,
												"Not able to click on insufficient permission close button");
									}
								} else {
									appLog.info("Insufficient Permission popup is not displaying");
									saa.assertTrue(false, "Insufficient Permission popup is not displaying");
								}
							} else {
								appLog.info("Not able to click on edit icon");
								saa.assertTrue(false, "Not able to click on edit icon");
							}
							if (nim.clickOnEditIcon()) {
								if (click(driver, nim.getInsufficientPermissionCrossIcon(60,sideMenu.InternalUsers),
										"Insufficient permission Cross icon", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on insufficient permission Cross icon");
							if(nim.getInsufficientPermissionCrossIcon(20,sideMenu.InternalUsers)==null){
								appLog.info("Insufficient popup get closed successfully");
							}else{
								appLog.error("Insufficient popup does not get closed successfully");
								saa.assertTrue(false, "Insufficient popup does not get closed successfully");
							}							
								} else {
									appLog.info("Not able to click on insufficient permission Cross icon");
									saa.assertTrue(false,"Not able to click on insufficient permission Cross icon");
								}
								if (nim.verifyLandingPageText("Internal Users")) {
									appLog.info("Internal Users page is avaiable");
								} else {
									appLog.info("Internal Users page is not avaiable");
									saa.assertTrue(false, "Internal Users page is not avaiable");
								}
								if (nim.getEditIcon(30) != null) {
									appLog.info("Edit icon is displaying");
								} else {
									appLog.info("Edit icon is displaying");
									saa.assertTrue(false, "Edit icon is not displaying");
								}
							} else {
								appLog.info("Not able to click on edit icon");
								saa.assertTrue(false, "Not able to click on edit icon");
							}
						} else {
							appLog.info("Not able to click on internal users tab");
							saa.assertTrue(false, "Not able to click on internal users tab");
						}
					} else {
						appLog.info("Not able to click on NIM Tab so cannot check error message");
						saa.assertTrue(false, "Not able to click on NIM Tab so cannot check error message");
					}
				} else {
					appLog.info("Not able to click on registration successful popup close button");
					saa.assertTrue(false, "Not able to click on registration successful popup close button");
				}
			} else {
				appLog.info("Registration successful popup close button is not displaying");
				saa.assertTrue(false, "Registration successful popup close button is not displaying");
			}
		} else {
			appLog.info("Not able to click on NIM Tab so cannot check error message");
			saa.assertTrue(false, "Not able to click on NIM Tab so cannot check error message");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc018_VerifyAdminRadioButton() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		List<String> sideMenusList = new ArrayList<String>();
		WebElement ele = null;
		String sideMenusOnNIM = "Internal Users<break>Folder Templates<break>Manage Approvals<break>Watermarking<break>File Distributor Settings<break>Profiles";
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (nim.clickOnEditIcon()) {
				ele = FindElement(driver,
						"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
								+ "']/../..//input[@type='radio']",
						"CRM User 1 radio button", action.SCROLLANDBOOLEAN, 60);
				if (click(driver, ele, "CRM User1 admin Raio Button", action.SCROLLANDBOOLEAN)) {
					if (click(driver, nim.getConfirmAccessPopupYesButton(60), "Yes Button", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(5000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
										+ "']/../..//input[@type='radio']",
								"CRM User 1 radio button", action.SCROLLANDBOOLEAN, 60);
						if (isSelected(driver, ele, "CRM User1 radio button")) {
							appLog.info("CRM User 1 radio button is selected");
						} else {
							appLog.info("CRM User radio button is not selected");
							saa.assertTrue(false, "CRM User radio button is not selected");
						}
						ThreadSleep(5000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
										+ "']/../..//input[@type='checkbox']",
								"CRM User 1 Checkbox", action.SCROLLANDBOOLEAN, 60);
						if (isSelected(driver, ele, "CRM User1 checkbox")) {
							appLog.info("CRM User 1 radio button is selected");
						} else {
							appLog.info("CRM User checkbox is not selected");
							saa.assertTrue(false, "CRM User checkbox is not  selected");
						}
						if (nim.getGoBackLink(60) == null) {
							appLog.info("Go back link is not available");
						} else {
							appLog.info("Go back link is available");
							saa.assertTrue(false, "Go back link is available");
						}
						String licenseMessage = nim.getInternalUsersLiceneMessage(60).getText().trim();
						if (licenseMessage.equalsIgnoreCase("Internal Users (1 out of 10 Licenses Used)")) {
							appLog.info("Internal user license mesage is verified");
						} else {
							appLog.info("Internal user license message is not verified");
							saa.assertTrue(false, "Internal user license message is not verified");
						}
					} else {
						appLog.info("Not able to click on yes button");
						saa.assertTrue(false, "Not able to click on yes button");
					}

				} else {
					appLog.info("Not able to click on radio button");
					saa.assertTrue(false, "Not able to click on radio button");
				}
			} else {
				appLog.info("Not able to click on edit icon");
				saa.assertTrue(false, "Not able to click on edit icon");
			}
		} else {
			appLog.info("Not able to click on NIM Tab so cannot check error message");
			saa.assertTrue(false, "Not able to click on NIM Tab so cannot check error message");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);
		nim = new NIMPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			List<WebElement> sideMenus = nim.getnimPageSideMenus();
			for (int i = 0; i < sideMenus.size(); i++) {
				sideMenusList.add(sideMenus.get(i).getText().trim());
			}
			if (compareMultipleListWithoutAssertion(sideMenusOnNIM, sideMenusList)) {
				appLog.info("All Menus are verified");
			} else {
				appLog.info("All side menus are not veriified");
				saa.assertTrue(false, "All side menus are not veriified");
			}
			if (nim.clickOnEditIcon()) {
				ele = FindElement(driver,
						"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
								+ "']/../..//input[@type='checkbox']",
						"CRM User 1 Checkbox", action.SCROLLANDBOOLEAN, 60);
				if (!isEnabled(driver, ele, "CRM User 1 Checkbox")) {
					appLog.info("CRM User 1 checkbox is not enabled");
				} else {
					appLog.info("CRm User1 checkbox is enabled");
					saa.assertTrue(false, "CRM User1 checkbox is enabled");
				}
				ThreadSleep(2000);
				ele = FindElement(driver,
						"//span[text()='" + CRMUser2FirstName + " " + CRMUser2LastName
								+ "']/../..//input[@type='checkbox']",
						"CRM User 2 Checkbox", action.SCROLLANDBOOLEAN, 60);
				if (!isEnabled(driver, ele, "CRM User 2 Checkbox")) {
					appLog.info("CRM User 2 checkbox is not enabled");
				} else {
					appLog.info("CRm User2 checkbox is enabled");
					saa.assertTrue(false, "CRM User2 checkbox is enabled");
				}
				ThreadSleep(2000);
				ele = FindElement(driver,
						"//span[text()='" + CRMUser3FirstName + " " + CRMUser3LastName
								+ "']/../..//input[@type='checkbox']",
						"CRM User 3 Checkbox", action.SCROLLANDBOOLEAN, 60);
				if (!isEnabled(driver, ele, "CRM User 3 Checkbox")) {
					appLog.info("CRM User 3 checkbox is not enabled");
				} else {
					appLog.info("CRm User3 checkbox is enabled");
					saa.assertTrue(false, "CRM User3 checkbox is enabled");
				}
				if (nim.getGoBackLink(60) != null) {
					appLog.info("Go back link is available");
				} else {
					appLog.info("Go back link is not available");
					saa.assertTrue(false, "Go back link is not available");
				}

			} else {
				appLog.info("Not able to click on edit icon");
				saa.assertTrue(false, "Not able to click on edit icon");
			}
		} else {
			appLog.info("Not able to click on NIM Tab so cannot check error message");
			saa.assertTrue(false, "Not able to click on NIM Tab so cannot check error message");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc019_VerifyChangingOfAdminAccessFromUserSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		List<String> sideMenusList = new ArrayList<String>();
		WebElement ele = null;
		String sideMenusOnNIM = "Internal Users<break>Folder Templates<break>Manage Approvals<break>Watermarking<break>Profiles";
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (nim.clickOnEditIcon()) {
				ele = FindElement(driver,
						"//span[text()='" + CRMUser2FirstName + " " + CRMUser2LastName
								+ "']/../..//input[@type='checkbox']",
						"CRM User 2 Checkbox", action.SCROLLANDBOOLEAN, 60);

				if (click(driver, ele, "CRM User 2 checkbox", action.SCROLLANDBOOLEAN)) {
					if (click(driver, nim.getConfirmUserPermissionAdditionPopupYesButton(60),
							"Yes Button in User Addition Popup", action.SCROLLANDBOOLEAN)) {
						if (isSelected(driver, ele, "CRM User 2 checkbox")) {
							appLog.info("CRM User 2 checkbox is selected");
						} else {
							appLog.info(" CRM User2 checkbox is not selected");
							saa.assertTrue(false, "CRM User2 checkbox is not selected");
						}
						ThreadSleep(2000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
										+ "']/../..//input[@type='checkbox']",
								"CRM User 1 Checkbox", action.SCROLLANDBOOLEAN, 60);
						if (isSelected(driver, ele, "CRM User 1 checkbox")) {
							appLog.info("CRM User 1 checkbox is selected");
						} else {
							appLog.info(" CRM User1 checkbox is not selected");
							saa.assertTrue(false, "CRM User1 checkbox is not selected");
						}
						ThreadSleep(2000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
										+ "']/../..//input[@type='radio']",
								"CRM User 1 Checkbox", action.SCROLLANDBOOLEAN, 60);
						if (isSelected(driver, ele, "CRM User 1 radio button")) {
							appLog.info("CRM User 1 radio button is selected");
						} else {
							appLog.info(" CRM User1 radio button is not selected");
							saa.assertTrue(false, "CRM User1 radio button is not selected");
						}
						ThreadSleep(2000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser2FirstName + " " + CRMUser2LastName
										+ "']/../..//input[@type='radio']",
								"CRM User 2 radio button", action.SCROLLANDBOOLEAN, 60);
						if (isSelected(driver, ele, "CRM User 2 radio button")) {
							appLog.info("CRM User 2 radio button is selected");
							saa.assertTrue(false, "CRM User2 radio button is selected");
						} else {
							appLog.info(" CRM User2 radio button is not selected");
						}
						if (isEnabled(driver, ele, "CRM User 2 radio button")) {
							appLog.info("CRM User2 radio button is enabled");
							saa.assertTrue(false, "CRM User2 radio button is enabled");
						} else {
							appLog.info("CRM User2 radio button is not enabled");
						}
						if (nim.getGoBackLink(60) != null) {
							appLog.info("Go back link is available");
						} else {
							appLog.info("Go back link is not available");
							saa.assertTrue(false, "Go back link is not available");
						}
						String licenseMessage = nim.getInternalUsersLiceneMessage(60).getText().trim();
						if (licenseMessage.equalsIgnoreCase("Internal Users (2 out of 10 Licenses Used)")) {
							appLog.info("Internal user license mesage is verified");
						} else {
							appLog.info("Internal user license message is not verified");
							saa.assertTrue(false, "Internal user license message is not verified");
						}
					} else {
						appLog.info("Not able to click on Yes Button");
						saa.assertTrue(false, "Not able to click on Yes Button");
					}
				} else {
					appLog.info("not able to click on CRM User 2 checkbox");
					saa.assertTrue(false, "not able to click on CRM User 2 checkbox");
				}

			} else {
				appLog.info("Not able to click on edit icon");
				saa.assertTrue(false, "Not able to click on edit icon");
			}
		} else {
			appLog.info("Not able to click on NIM Tab so cannot check error message");
			saa.assertTrue(false, "Not able to click on NIM Tab so cannot check error message");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);
		nim = new NIMPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		saa = new SoftAssert();
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			appLog.info("Clicked on NIM Tab");
			if (click(driver, nim.getNextButton(60), "Next Button", action.SCROLLANDBOOLEAN)) {
				if (click(driver, nim.getAllowButton(60), "Allow Button", action.SCROLLANDBOOLEAN)) {
					switchToFrame(driver, 60, nim.getNIMTabFrame(60));
					ThreadSleep(5000);
					if (nim.getRegistrationSuccessfulCloseBtn(60) != null) {
						if (click(driver, nim.getRegistrationSuccessfulCloseBtn(60), "Close button",
								action.SCROLLANDBOOLEAN)) {
							ExcelUtils.writeData("Registered", "Users", excelLabel.Variable_Name, "User2", excelLabel.Registered);
							switchToDefaultContent(driver);
							if (bp.clickOnTab(TabName.NIMTab)) {
								switchToFrame(driver, 60, nim.getNIMTabFrame(60));
								List<WebElement> sideMenus = nim.getnimPageSideMenus();
								for (int i = 0; i < sideMenus.size(); i++) {
									sideMenusList.add(sideMenus.get(i).getText().trim());
								}
								if (compareMultipleListWithoutAssertion(sideMenusOnNIM, sideMenusList)) {
									appLog.info("All Menus are verified");
								} else {
									appLog.info("All side menus are not veriified");
									saa.assertTrue(false, "All side menus are not veriified");
								}
							} else {
								appLog.info("Not able ot click on NIm Tab");
								saa.assertTrue(false, "Not able ot click on NIm Tab");
							}
						} else {
							appLog.info("Not able to click on registration successful popup close button");
							saa.assertTrue(false, "Not able to click on registration successful popup close button");
						}
					} else {
						appLog.info("Registration successful popup is not displaying");
						saa.assertTrue(false, "Registration successful popup is not displaying");
					}
				} else {
					appLog.info("Not able to click on allow button");
					saa.assertTrue(false, "Not able ot click on allow button");
				}
			} else {
				appLog.info("Not able to click on next button");
				saa.assertTrue(false, "Not able to click on next button");
			}
		} else {
			appLog.info("Not able to click on NIm Tab");
			saa.assertTrue(false, "Not able to click on NIm Tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);
		nim = new NIMPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			appLog.info("Clicked on NIM Tab");
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (nim.clickOnEditIcon()) {
				ele = FindElement(driver,
						"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
								+ "']/../..//input[@type='radio']",
						"CRM User 1 radio button", action.SCROLLANDBOOLEAN, 60);
				if (isSelected(driver, ele, "CRM User 1 radio button")) {
					appLog.info("CRM User1 radio button is selected");
				} else {
					appLog.info("CRM User1 radio button is not selected");
					saa.assertTrue(false, "CRM User1 radio button is not selected");
				}
				ThreadSleep(2000);
				ele = FindElement(driver,
						"//span[text()='" + CRMUser2FirstName + " " + CRMUser2LastName
								+ "']/../..//input[@type='radio']",
						"CRM User 2 radio button", action.SCROLLANDBOOLEAN, 60);
				if (isEnabled(driver, ele, "CRM User 2 radio button")) {
					appLog.info("CRM User2 radio button is enabled");
				} else {
					appLog.info("CRM User2 radio button is not enabled");
					saa.assertTrue(false, "CRM User2 radio button is not enabled");
				}
				if (click(driver, ele, "CRM User 2 radio button", action.SCROLLANDBOOLEAN)) {
					if (nim.getConfirmAccessPopupHeader(60) != null) {
						appLog.info("Confirm Access Popup is displaying");
						String message = "Are you sure you want to provide Admin permissions to " + CRMUser2FirstName
								+ " " + CRMUser2LastName;
						if (bp.verifyErrorMessageOnPage(message, nim.getConfirmAccessPopupErrorMessage(60),
								"Message when giving admin permission")) {
							appLog.info("Message when giving admin permission is verified.");
						} else {
							saa.assertTrue(false,
									"Message when giving admin permission is not verified.Expected:" + message
											+ " Actual" + getText(driver, nim.getConfirmAccessPopupErrorMessage(60),
													"Message when giving admin permission", action.BOOLEAN));
						}
						if (click(driver, nim.getConfirmAccessPopupNoButton(60), "No Button",
								action.SCROLLANDBOOLEAN)) {
							if (nim.getGoBackLink(60) != null) {
								appLog.info("Go back link is available");
							} else {
								appLog.info("Go back link is not available");
								saa.assertTrue(false, "Go back link is not available");
							}
							ThreadSleep(2000);
							ele = FindElement(driver,
									"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
											+ "']/../..//input[@type='radio']",
									"CRM User 1 radio button", action.SCROLLANDBOOLEAN, 60);
							if (isSelected(driver, ele, "CRM User 1 radio button")) {
								appLog.info("CRM User1 radio button is selected");
							} else {
								appLog.info("CRM User1 radio button is not selected");
								saa.assertTrue(false, "CRM User1 radio button is not selected");
							}
							ThreadSleep(2000);
							ele = FindElement(driver,
									"//span[text()='" + CRMUser2FirstName + " " + CRMUser2LastName
											+ "']/../..//input[@type='radio']",
									"CRM User 2 radio button", action.SCROLLANDBOOLEAN, 60);
							if (isSelected(driver, ele, "CRM User 2 radio button")) {
								appLog.info("CRM User2 radio button is selected");
								saa.assertTrue(false, "CRM User2 radio button is  enabled");
							} else {
								appLog.info("CRM User2 radio button is not selected");
							}
						} else {
							appLog.info("Not able to click on No button");
							saa.assertTrue(false, "Not able to click on No button");
						}
					} else {
						appLog.info("Confirm Access popup is not displaying");
						saa.assertTrue(false, "Confirm Access popup is not displaying");
					}
				} else {
					appLog.info("Not able to click on CRM User 2 radio button");
					saa.assertTrue(false, "Not able to click on CRM User 2 radio button");
				}

				if (click(driver, ele, "CRM User2 Radio Button", action.SCROLLANDBOOLEAN)) {
					if (click(driver, nim.getConfirmAccessPopupCloseButton(60), "Close Button",
							action.SCROLLANDBOOLEAN)) {
						if (nim.getGoBackLink(60) != null) {
							appLog.info("Go back link is available");
						} else {
							appLog.info("Go back link is not available");
							saa.assertTrue(false, "Go back link is not available");
						}
						ThreadSleep(2000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
										+ "']/../..//input[@type='radio']",
								"CRM User 1 radio button", action.SCROLLANDBOOLEAN, 60);
						if (isSelected(driver, ele, "CRM User 1 radio button")) {
							appLog.info("CRM User1 radio button is selected");
						} else {
							appLog.info("CRM User1 radio button is not selected");
							saa.assertTrue(false, "CRM User1 radio button is not selected");
						}
						ThreadSleep(2000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser2FirstName + " " + CRMUser2LastName
										+ "']/../..//input[@type='radio']",
								"CRM User 2 radio button", action.SCROLLANDBOOLEAN, 60);
						if (isSelected(driver, ele, "CRM User 2 radio button")) {
							appLog.info("CRM User2 radio button is selected");
							saa.assertTrue(false, "CRM User2 radio button is selected");
						} else {
							appLog.info("CRM User2 radio button is not selected");
						}
					} else {
						appLog.info("Not able to click on close button");
						saa.assertTrue(false, "Not able to click on close button");
					}
				} else {
					appLog.info("Not able to click on CRM User 2 radio button");
					saa.assertTrue(false, "Not able to click on CRM User 2 radio button");
				}
				if (click(driver, ele, "CRM User 2 radio button", action.SCROLLANDBOOLEAN)) {
					if (click(driver, nim.getConfirmAccessPopupYesButton(60), "Yes Button", action.SCROLLANDBOOLEAN)) {
						if (nim.getGoBackLink(60) != null) {
							appLog.info("Go back link is displaying");
							saa.assertTrue(false, "Go back link is displaying");
						} else {
							appLog.info("Go back link is not displaying");
						}
						ThreadSleep(2000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
										+ "']/../..//input[@type='radio']",
								"CRM User 1 radio button", action.SCROLLANDBOOLEAN, 60);
						if (isSelected(driver, ele, "CRM User 1 radio button")) {
							appLog.info("CRM User1 radio button is selected");
							saa.assertTrue(false, "CRM User1 radio button is selected");
						} else {
							appLog.info("CRM User1 radio button is not selected");

						}
						ThreadSleep(2000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser2FirstName + " " + CRMUser2LastName
										+ "']/../..//input[@type='radio']",
								"CRM User 2 radio button", action.SCROLLANDBOOLEAN, 60);
						if (isSelected(driver, ele, "CRM User 2 radio button")) {
							appLog.info("CRM User2 radio button is selected");
						} else {
							appLog.info("CRM User2 radio button is not selected");
							saa.assertTrue(false, "CRM User2 radio button is not selected");
						}
						List<WebElement> sideMenus = nim.getnimPageSideMenus();
						for (int i = 0; i < sideMenus.size(); i++) {
							sideMenusList.add(sideMenus.get(i).getText().trim());
						}
						if (compareMultipleListWithoutAssertion(sideMenusOnNIM, sideMenusList)) {
							appLog.info("All Menus are verified");
						} else {
							appLog.info("All side menus are not veriified");
							saa.assertTrue(false, "All side menus are not veriified");
						}
					} else {
						appLog.info("Not able to click on Yes Button");
						saa.assertTrue(false, "Not able to click on Yes Button");
					}
				} else {
					appLog.info("Not able to click on CRM User 2 radio button");
					saa.assertTrue(false, "Not able to click on CRM User 2 radio button");
				}
			} else {
				appLog.info("Not able to click on edit icon");
				saa.assertTrue(false, "Not able to click on edit icon");
			}
		} else {
			appLog.info("Not able to click on NIM Tab");
			saa.assertTrue(false, "Not bale to click on NIm Tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);
		nim = new NIMPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		saa = new SoftAssert();
		String sideMenusOnNIMHavingAdminAccess = "Internal Users<break>Folder Templates<break>Manage Approvals<break>Watermarking<break>File Distributor Settings<break>Profiles";
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			appLog.info("Clicked on NIM Tab");
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			List<WebElement> sideMenus = nim.getnimPageSideMenus();
			for (int i = 0; i < sideMenus.size(); i++) {
				sideMenusList.add(sideMenus.get(i).getText().trim());
			}
			if (compareMultipleListWithoutAssertion(sideMenusOnNIMHavingAdminAccess, sideMenusList)) {
				appLog.info("All Menus are verified");
			} else {
				appLog.info("All side menus are not veriified");
				saa.assertTrue(false, "All side menus are not veriified");
			}
		} else {
			appLog.info("Not able to click on NIM Tab");
			saa.assertTrue(false, "Not able to click on NIM Tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc020_VerifyChangingOfAdminAccessFromAdminSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		List<String> sideMenusList = new ArrayList<String>();
		WebElement ele = null;
		String sideMenusOnNIM = "Internal Users<break>Folder Templates<break>Manage Approvals<break>Watermarking<break>File Distributor Settings<break>Profiles";
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (nim.clickOnEditIcon()) {
				ele = FindElement(driver,
						"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
								+ "']/../..//input[@type='radio']",
						"CRM User 1 radio button", action.SCROLLANDBOOLEAN, 60);
				if (click(driver, ele, "CRm User 1 radio button", action.SCROLLANDBOOLEAN)) {
					if (click(driver, nim.getConfirmAccessPopupYesButton(60), "Yes Button", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
										+ "']/../..//input[@type='radio']",
								"CRM User 1 radio button", action.SCROLLANDBOOLEAN, 60);
						if (isSelected(driver, ele, "CRM User 1 radio button")) {
							appLog.info("CRM User1 radio button is selected");
						} else {
							appLog.info("CRM User1 radio button is not selected");
							saa.assertTrue(false, "CRM User1 radio button is not selected");
						}
						ThreadSleep(2000);
						ele = FindElement(driver,
								"//span[text()='" + CRMUser2FirstName + " " + CRMUser2LastName
										+ "']/../..//input[@type='radio']",
								"CRM User 2 radio button", action.SCROLLANDBOOLEAN, 60);
						if (isSelected(driver, ele, "CRM User 2 radio button")) {
							appLog.info("CRM User2 radio button is selected");
							saa.assertTrue(false, "CRM User2 radio button is selected");
						} else {
							appLog.info("CRM User2 radio button is not selected");
						}
					} else {
						appLog.info("Not able to click on close button");
						saa.assertTrue(false, "Not able to click on close button");
					}
				} else {
					appLog.info("Not able to click on CRM user 1 radio button");
					saa.assertTrue(false, "Not able to click on CRM user 1 radio button");
				}

			} else {
				appLog.info("Not able to click on edit icon");
				saa.assertTrue(false, "Not able to click on edit icon");
			}
		} else {
			appLog.info("Not able to click on NIM Tab");
			saa.assertTrue(false, "Not bale to click on NIm Tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);
		nim = new NIMPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			sideMenusList.clear();
			List<WebElement> sideMenus = nim.getnimPageSideMenus();
			for (int i = 0; i < sideMenus.size(); i++) {
				sideMenusList.add(sideMenus.get(i).getText().trim());
			}
			if (compareMultipleListWithoutAssertion(sideMenusOnNIM, sideMenusList)) {
				appLog.info("All Menus are verified");
			} else {
				appLog.info("All side menus are not veriified");
				saa.assertTrue(false, "All side menus are not veriified");
			}
		} else {
			appLog.info("Not able to click on NIM tab");
			saa.assertTrue(false, "Not able to click on NIM tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);
		nim = new NIMPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		String sideMenusOnNIMWithoutAdminAccess = "Internal Users<break>Folder Templates<break>Manage Approvals<break>Watermarking<break>Profiles";
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			List<WebElement> sideMenus = nim.getnimPageSideMenus();
			for (int i = 0; i < sideMenus.size(); i++) {
				sideMenusList.add(sideMenus.get(i).getText().trim());
			}
			if (compareMultipleListWithoutAssertion(sideMenusOnNIMWithoutAdminAccess, sideMenusList)) {
				appLog.info("All Menus are verified");
			} else {
				appLog.info("All side menus are not veriified");
				saa.assertTrue(false, "All side menus are not veriified");
			}

		} else {
			appLog.info("Not able to click on NIM tab");
			saa.assertTrue(false, "Not able to click on NIM tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc021_VerifyDeactivatingActivatingACRMUser() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		WebElement ele = null;
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.deactivateAndActivateCreatedUser("Active Users", CRMUser1FirstName, CRMUser1LastName, "Deactivate")) {
			appLog.info("deactivate the CRM User 1 ");
		} else {
			appLog.info("Not able to deactivate CRM User1");
			saa.assertTrue(false, "Not able to deactivate CRM User1");
		}
		if (bp.verifyUserGetDeactivatedAndActivated(CRMUser1FirstName, CRMUser1LastName, "Deactivate")) {
			appLog.info("User get deactivated successfully");
		} else {
			appLog.info("User is not get deactivated successfully");
			saa.assertTrue(false, "User is not get deactivated successfully");
		}
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			appLog.info("Clicked on NIm Tab");
			ele = FindElement(driver, "//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName + "(inactive)"
					+ "']/../..//input[@type='checkbox']", "CRM User 1 checkbox", action.SCROLLANDBOOLEAN, 60);
			if (ele != null) {
				appLog.info("CRM User1 with inactive keywod is displaying");
				if (nim.clickOnEditIcon()) {
					if (isEnabled(driver, ele, "CRM User 1 checkbox")) {
						appLog.info("CRM User1 checkbox is enabled");
					} else {
						appLog.info("CRM User1 checkbox is not enabled");
						saa.assertTrue(false, "CRM User1 checkbox is not enabled");
					}
					ThreadSleep(2000);
					ele = FindElement(driver,
							"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName + "(inactive)"
									+ "']/../..//input[@type='radio']",
							"CRM User 1 radio button", action.SCROLLANDBOOLEAN, 60);
					if (isSelected(driver, ele, "CRM User 1 radio button")) {
						appLog.info("CRM User1 radio button is selected");
					} else {
						appLog.info("CRM User1 radio button is not selected");
						saa.assertTrue(false, "CRM User1 radio button is not selected");
					}
					if (isEnabled(driver, ele, "CRM User 1 radio button")) {
						appLog.info("CRM User1 radio button is enabled");
						saa.assertTrue(false, "CRM User1 radio button is enabled");
					} else {
						appLog.info("CRM User1 radio button is not enabled");
					}
					String licenseMessage = nim.getInternalUsersLiceneMessage(60).getText().trim();
					if (licenseMessage.equalsIgnoreCase("Internal Users (2 out of 10 Licenses Used)")) {
						appLog.info("Internal user license mesage is verified");
					} else {
						appLog.info("Internal user license message is not verified");
						saa.assertTrue(false, "Internal user license message is not verified");
					}
				} else {
					appLog.info("Not able to click on edit icon");
					saa.assertTrue(false, "Not able to click on edit icon");
				}
			} else {
				appLog.info("CRM User1 with inactive keywod is not displaying");
				saa.assertTrue(false, "CRM User1 with inactive keywod is not displaying");
			}

		} else {
			appLog.info("Not able to click on NIm Tab");
			saa.assertTrue(false, "Not able to click on NIm Tab");
		}
		switchToDefaultContent(driver);
		if (bp.deactivateAndActivateCreatedUser("All Users", CRMUser1FirstName, CRMUser1LastName, "Activate")) {
			appLog.info("Activate the CRM User 1 ");
		} else {
			appLog.info("Not able to Activate CRM User1");
			saa.assertTrue(false, "Not able to Activate CRM User1");
		}
		if (bp.verifyUserGetDeactivatedAndActivated(CRMUser1FirstName, CRMUser1LastName, "Activate")) {
			appLog.info("User get Activate successfully");
		} else {
			appLog.info("User is not get Activate successfully");
			saa.assertTrue(false, "User is not get Activate successfully");
		}
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			ThreadSleep(2000);
			ele = FindElement(driver, "//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
					+ "']/../..//input[@type='checkbox']", "CRM User 1 checkbox", action.SCROLLANDBOOLEAN, 60);
			if (ele != null) {
				appLog.info("CRM User1  displaying");
				if (nim.clickOnEditIcon()) {
					if (isEnabled(driver, ele, "CRM User 1 radio button")) {
						appLog.info("CRM User1 radio button is enabled");
					} else {
						appLog.info("CRM User1 radio button is not enabled");
						saa.assertTrue(false, "CRM User1 radio button is not enabled");
					}
					ThreadSleep(2000);
					ele = FindElement(driver,
							"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName
									+ "']/../..//input[@type='radio']",
							"CRM User 1 radio button", action.SCROLLANDBOOLEAN, 60);

					if (isSelected(driver, ele, "CRM User 1 radio button")) {
						appLog.info("CRM User1 radio button is selected");
					} else {
						appLog.info("CRM User1 radio button is not selected");
						saa.assertTrue(false, "CRM User1 radio button is not selected");
					}
					if (isEnabled(driver, ele, "CRM User 1 radio button")) {
						appLog.info("CRM User1 radio button is enabled");
					} else {
						appLog.info("CRM User1 radio button is not enabled");
						saa.assertTrue(false, "CRM User1 radio button is not enabled");
					}
					String licenseMessage = nim.getInternalUsersLiceneMessage(60).getText().trim();
					if (licenseMessage.equalsIgnoreCase("Internal Users (2 out of 10 Licenses Used)")) {
						appLog.info("Internal user license mesage is verified");
					} else {
						appLog.info("Internal user license message is not verified");
						saa.assertTrue(false, "Internal user license message is not verified");
					}
				} else {
					appLog.info("Not able to click on edit icon");
					saa.assertTrue(false, "Not able to click on edit icon");
				}
			} else {
				appLog.info("CRM User1  is not displaying");
				saa.assertTrue(false, "CRM User1 is not displaying");
			}
		} else {
			appLog.info("Not able to click on NIM Tab");
			saa.assertTrue(false, "Not able to click on NIM Tab");
		}
		switchToDefaultContent(driver);
		if (bp.deactivateAndActivateCreatedUser("Active Users", CRMUser1FirstName, CRMUser1LastName, "Deactivate")) {
			appLog.info("deactivate the CRM User 1 ");
		} else {
			appLog.info("Not able to deactivate CRM User1");
			saa.assertTrue(false, "Not able to deactivate CRM User1");
		}
		if (bp.verifyUserGetDeactivatedAndActivated(CRMUser1FirstName, CRMUser1LastName, "Deactivate")) {
			appLog.info("User get deactivated successfully");
		} else {
			appLog.info("User is not get deactivated successfully");
			saa.assertTrue(false, "User is not get deactivated successfully");
		}
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			appLog.info("Clicked on NIm Tab");
			if (nim.clickOnEditIcon()) {
				ele = FindElement(driver,
						"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName + "(inactive)"
								+ "']/../..//input[@type='checkbox']",
						"CRM User 1 checkbox", action.SCROLLANDBOOLEAN, 60);
				if (click(driver, ele, "CRM User1 checkbox", action.SCROLLANDBOOLEAN)) {
					if (click(driver, nim.getUserPermissionRemovalPopupYesButton(60), "User Permissionreval Yes Button",
							action.SCROLLANDBOOLEAN)) {
						ThreadSleep(3000);
						if (click(driver, nim.getInternalUsersTab(60), "Internal Users Tab", action.SCROLLANDBOOLEAN)) {
							ThreadSleep(2000);
							ele = FindElement(driver,
									"//span[text()='" + CRMUser1FirstName + " " + CRMUser1LastName + "']",
									"CRM User 1 ", action.SCROLLANDBOOLEAN, 20);
							if (nim.getUsersListInInternalUsers().contains(ele)) {
								appLog.info("CRM User1 is displaying in the users list");
								saa.assertTrue(false, "CRM User1 is displaying in the users list");
							} else {
								appLog.info("CRM User1 is not displaying in the users list");
							}
							String licenseMessage = nim.getInternalUsersLiceneMessage(60).getText().trim();
							if (licenseMessage.equalsIgnoreCase("Internal Users (1 out of 10 Licenses Used)")) {
								appLog.info("Internal user license mesage is verified");
							} else {
								appLog.info("Internal user license message is not verified");
								saa.assertTrue(false, "Internal user license message is not verified");
							}
						} else {
							appLog.info("Not able to click on internal users tab");
							saa.assertTrue(false, "Not able to click on internal users tab");
						}
					} else {
						appLog.info("Not able to click on Yes Button");
						saa.assertTrue(false, "Not able to click on Yes Button");
					}
				} else {
					appLog.info("Not able to click on CRM User1 checkbox");
					saa.assertTrue(false, "Not able to click on CRM User1 checkbox");
				}
			} else {
				appLog.info("Not able to click on edit icon");
				saa.assertTrue(false, "Not able to click on edit icon");
			}
		} else {
			appLog.info("Not able to click on NIM Tab");
			saa.assertTrue(false, "Not able to click on NIM Tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc022_VerifyUpdationOfName() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		WebElement ele = null;
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (click(driver, bp.getUserMenuTab(120), "User Menu Button", action.SCROLLANDBOOLEAN)) {
			if (click(driver, bp.getUserMenuSetupLink(120), "Setup Link", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(1000);
				if (click(driver, bp.getExpandManageUserIcon(120), "Manage User Icon", action.SCROLLANDBOOLEAN)) {
					if (click(driver, bp.getUsersLink(120), "User Link", action.SCROLLANDBOOLEAN)) {
						if (selectVisibleTextFromDropDown(driver, bp.getViewAllDropdownList(120), "View dropdown list",
								"Active Users")) {
							ThreadSleep(2000);
							ele = FindElement(driver,
									"//a[text()='" + CRMUser2LastName + "," + " " + CRMUser2FirstName
											+ "']/..//preceding-sibling::td//a",
									"Edit icon", action.SCROLLANDBOOLEAN, 120);
							if (ele != null) {
								if (click(driver, ele, "Edit icon", action.SCROLLANDBOOLEAN)) {
									if (sendKeys(driver, bp.getUserFirstName(60), CRMUser2FirstName + "Updated",
											"First updtaed name", action.SCROLLANDBOOLEAN)) {
										if (sendKeys(driver, bp.getUserLastName(60), CRMUser2LastName + "Updated",
												"Last updtaed name", action.SCROLLANDBOOLEAN)) {
											if (click(driver, bp.getSaveButton(120), "Save Button",
													action.SCROLLANDBOOLEAN)) {
												appLog.info("Clicked on save button");
												ThreadSleep(2000);
												ele = FindElement(driver,
														"//a[text()='" + CRMUser2LastName + "Updated" + "," + " "
																+ CRMUser2FirstName + "Updated" + "']",
														"Updated CRM User 2 name", action.SCROLLANDBOOLEAN, 120);

												if (ele != null) {
													appLog.info("Updated CRM User2 name is displaying");
												} else {
													appLog.info("Updated CRm User2 name is not displaying");
													saa.assertTrue(false, "Updated CRm User2 name is not displaying");
												}
											} else {
												appLog.info("Not able to click on save button");
												saa.assertTrue(false, "Not able to click on save button");
											}
										} else {
											appLog.info("Not able to enter values in first name text box");
											saa.assertTrue(false, "Not able to enter values in first name text box");
										}
									} else {
										appLog.info("Not able to enter values in first name text box");
										saa.assertTrue(false, "Not able to enter values in first name text box");
									}
								} else {
									appLog.info("Not able to click on edit icon");
									saa.assertTrue(false, "Not able to click on edit icon");
								}
							} else {
								appLog.info("Element is not present");
								saa.assertTrue(false, "Element is not present");
							}
						} else {
							appLog.info("Not able to select visbible text from the dropdown");
							saa.assertTrue(false, "Not able to select visbible text from the dropdown");
						}
					} else {
						appLog.info("Not able to click on users Link");
						saa.assertTrue(false, "Not able to click on users Link");
					}
				} else {
					appLog.info("Not able to click on Manage User Icon");
					saa.assertTrue(false, "Not able to click on Manage User Icon");
				}
			} else {
				appLog.info("Not able to click on Setup link");
				saa.assertTrue(false, "Not able to click on Setup link");
			}
		} else {
			appLog.info("Not able to click on user menu tab");
			saa.assertTrue(false, "Not able to click on user menu tab");
		}
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (nim.clickOnSideMenusTab(sideMenu.Profiles)) {
				if (nim.clickOnEditIcon()) {
					if (sendKeys(driver, nim.getMyProfileFirstName(60), CRMUser2FirstName + "New", "New first Name",
							action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, nim.getMyProfileLastName(60), CRMUser2LastName + "New", "New Last Name",
								action.SCROLLANDBOOLEAN)) {
							if (click(driver, nim.getMyProfileSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)) {
								ThreadSleep(3000);
								String nameInViewMode = nim.getMyProfileNameInViewMode(60).getText().trim();
								if (nameInViewMode
										.equalsIgnoreCase(CRMUser2FirstName + "New" + " " + CRMUser2LastName + "New")) {
									appLog.info("Updtaed name is displaying");
								} else {
									appLog.info("Updated name is not displaying");
									saa.assertTrue(false, "Updated name is not displaying");
								}
							} else {
								appLog.info("Not able to click on save button");
								saa.assertTrue(false, "Not able to click on save button");
							}
						} else {
							appLog.info("Not able to enter value in last name text box");
							saa.assertTrue(false, "Not able to enter value in last name text box");
						}

					} else {
						appLog.info("Not able to enter value in first name text box");
						saa.assertTrue(false, "Not able to enter value in first name text box");
					}
				} else {
					appLog.info("Not able to click on edit icon");
					saa.assertTrue(false, "Not able to click on edit icon");
				}

			} else {
				appLog.info("Not able to click on profiles tab");
				saa.assertTrue(false, "Not able to click on profiles tab");
			}
		} else {
			appLog.info("Not able to click on NIM Tab");
			saa.assertTrue(false, "Not able to click on NIm Tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);
		nim = new NIMPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (nim.clickOnSideMenusTab(sideMenu.Profiles)) {
				if (nim.clickOnEditIcon()) {
					if (sendKeys(driver, nim.getMyProfileFirstName(60), superAdminFirstName + "New", "New first Name",
							action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, nim.getMyProfileLastName(60), superAdminLastName + "New", "New Last Name",
								action.SCROLLANDBOOLEAN)) {
							if (click(driver, nim.getMyProfileSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)) {
								ThreadSleep(3000);
								String nameInViewMode = nim.getMyProfileNameInViewMode(60).getText().trim();
								if (nameInViewMode.equalsIgnoreCase(
										superAdminFirstName + "New" + " " + superAdminLastName + "New")) {
									appLog.info("Updtaed name is displaying");
								} else {
									appLog.info("Updated name is not displaying");
									saa.assertTrue(false, "Updated name is not displaying");
								}
							} else {
								appLog.info("Not able to click on save button");
								saa.assertTrue(false, "Not able to click on save button");
							}
						} else {
							appLog.info("Not able to enter value in last name text box");
							saa.assertTrue(false, "Not able to enter value in last name text box");
						}

					} else {
						appLog.info("Not able to enter value in first name text box");
						saa.assertTrue(false, "Not able to enter value in first name text box");
					}
				} else {
					appLog.info("Not able to click on edit icon");
					saa.assertTrue(false, "Not able to click on edit icon");
				}
			} else {
				appLog.info("Not able to click on profiles tab");
				saa.assertTrue(false, "Not able to click on profiles tab");
			}
			ThreadSleep(2000);
			if (nim.clickOnSideMenusTab(sideMenu.InternalUsers)) {
				ThreadSleep(2000);
				ele = FindElement(driver,
						"//span[text()='" + CRMUser2FirstName + "Updated" + " " + CRMUser2LastName + "Updated" + "']",
						"CRM User 2", action.SCROLLANDBOOLEAN, 60);
				if (nim.getUsersListInInternalUsers().contains(ele)) {
					appLog.info("CRM User2 is displaying in the users list");
				} else {
					appLog.info("CRM User 2 is not displaying in the users list");
					saa.assertTrue(false, "CRM User 2 is displaying in the users list");
				}
				ThreadSleep(2000);
				ele = FindElement(driver,
						"//span[text()='" + superAdminFirstName + "New" + " " + superAdminLastName + "New" + "']",
						"Admin Name", action.SCROLLANDBOOLEAN, 60);
				if (ele != null) {
					appLog.info("Updated CRM Admin Name is displaying");
				} else {
					appLog.info("Updated CRM Admin Name is not displaying");
					saa.assertTrue(false, "Updated CRM Admin Name is not displaying");
				}
			} else {
				appLog.info("Not able to click on internal users tab");
				saa.assertTrue(false, "Not able to click on internal users tab");
			}
		} else {
			appLog.info("Not able to click on NIm Tab");
			saa.assertTrue(false, "Not able to click on NIm Tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc023_VerifySortingInInternalUsersSection() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (nim.getSortingIconOnUserLabel(60) != null) {
				appLog.info("Sorting icon is displaying on user label ");
			} else {
				appLog.info("Sorting icon is not displaying on user label");
				saa.assertTrue(false, "Sorting icon is not displaying on user label");
			}
			if (bp.checkSorting(SortOrder.Assecending, nim.getUsersListInInternalUsers())) {
				appLog.info("Users list is in ascending order");
			} else {
				appLog.info("User list is not in ascending order");
				saa.assertTrue(false, "User list is not in ascending order");
			}
			for (int i = 0; i < 2; i++) {
				if (click(driver, nim.getUserLabelInInternalUserHeaders(60), "User label in internal user tab",
						action.BOOLEAN)) {
					appLog.info("Clicked on User label in internal user header");
					ThreadSleep(2000);
				} else {
					appLog.info("Not able to click on user label in internal user header");
					saa.assertTrue(false, "Not able to click on user label in internal user header");
				}
			}
			if (bp.checkSorting(SortOrder.Decending, nim.getUsersListInInternalUsers())) {
				appLog.info("Users list is in decending order");
				ThreadSleep(2000);
				if (nim.clickOnEditIcon()) {
					if (bp.checkSorting(SortOrder.Decending, nim.getUsersListInInternalUsers())) {
						appLog.info("Users list is in decending order");
						for (int i = 0; i < 2; i++) {
							if (click(driver, nim.getUserLabelInInternalUserHeaders(60),
									"User label in internal user tab", action.BOOLEAN)) {
								appLog.info("Clicked on User label in internal user header");
							} else {
								appLog.info("Not able to click on user label in internal user header");
								saa.assertTrue(false, "Not able to click on user label in internal user header");
							}
						}
						if (bp.checkSorting(SortOrder.Assecending, nim.getUsersListInInternalUsers())) {
							appLog.info("Users list is in ascending order");
						} else {
							appLog.info("User list is not in ascending order");
							saa.assertTrue(false, "User list is not in ascending order");
						}
					} else {
						appLog.info("User list is not in decending order in edit mode");
						saa.assertTrue(false, "User list is not in decending order in edit mode");
					}
				} else {
					appLog.info("Not able to click on edit icon");
					saa.assertTrue(false, "Not able to click on edit icon");
				}

			} else {
				appLog.info("User list is not in decending order");
				saa.assertTrue(false, "User list is not in decending order");
			}

		} else {
			appLog.info("Not able to click on NIm Tab");
			saa.assertTrue(false, "Not able to click on NIm Tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M1tc024_MakeUserActiveAndUpdateInformation() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		WebElement ele = null;
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.deactivateAndActivateCreatedUser("All Users", CRMUser1FirstName, CRMUser1LastName, "Activate")) {
			appLog.info("Activate the CRM User 1 ");
		} else {
			appLog.info("Not able to Activate CRM User1");
			saa.assertTrue(false, "Not able to Activate CRM User1");
		}
		if (bp.verifyUserGetDeactivatedAndActivated(CRMUser1FirstName, CRMUser1LastName, "Activate")) {
			appLog.info("User get Activate successfully");
		} else {
			appLog.info("User is not get Activate successfully");
			saa.assertTrue(false, "User is not get Activate successfully");
		}
		if (click(driver, bp.getUserMenuTab(120), "User Menu Button", action.SCROLLANDBOOLEAN)) {
			if (click(driver, bp.getUserMenuSetupLink(120), "Setup Link", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(1000);
				if (click(driver, bp.getExpandManageUserIcon(120), "Manage User Icon", action.SCROLLANDBOOLEAN)) {
					if (click(driver, bp.getUsersLink(120), "User Link", action.SCROLLANDBOOLEAN)) {
						if (selectVisibleTextFromDropDown(driver, bp.getViewAllDropdownList(120), "View dropdown list",
								"Active Users")) {
							ThreadSleep(2000);
							ele = FindElement(driver,
									"//a[text()='" + CRMUser2LastName + "Updated" + "," + " " + CRMUser2FirstName
											+ "Updated" + "']/..//preceding-sibling::td//a",
									"Edit icon", action.SCROLLANDBOOLEAN, 120);
							if (ele != null) {
								if (click(driver, ele, "Edit icon", action.SCROLLANDBOOLEAN)) {
									if (sendKeys(driver, bp.getUserFirstName(60), CRMUser2FirstName, "First name",
											action.SCROLLANDBOOLEAN)) {
										if (sendKeys(driver, bp.getUserLastName(60), CRMUser2LastName, "Last name",
												action.SCROLLANDBOOLEAN)) {
											if (click(driver, bp.getSaveButton(120), "Save Button",
													action.SCROLLANDBOOLEAN)) {
												appLog.info("Clicked on save button");
												ele = FindElement(driver,
														"//a[text()='" + CRMUser2LastName + "," + " "
																+ CRMUser2FirstName + "']",
														"CRM User 2 name", action.SCROLLANDBOOLEAN, 120);
												ThreadSleep(2000);
												if (ele != null) {
													appLog.info("CRM User2 name is displaying");
												} else {
													appLog.info("CRm User2 name is not displaying");
													saa.assertTrue(false, "CRm User2 name is not displaying");
												}
											} else {
												appLog.info("Not able to click on save button");
												saa.assertTrue(false, "Not able to click on save button");
											}
										} else {
											appLog.info("Not able to enter values in first name text box");
											saa.assertTrue(false, "Not able to enter values in first name text box");
										}
									} else {
										appLog.info("Not able to enter values in first name text box");
										saa.assertTrue(false, "Not able to enter values in first name text box");
									}
								} else {
									appLog.info("Not able to click on edit icon");
									saa.assertTrue(false, "Not able to click on edit icon");
								}
							} else {
								appLog.info("Element is not present");
								saa.assertTrue(false, "Element is not present");
							}
						} else {
							appLog.info("Not able to select visbible text from the dropdown");
							saa.assertTrue(false, "Not able to select visbible text from the dropdown");
						}
					} else {
						appLog.info("Not able to click on users Link");
						saa.assertTrue(false, "Not able to click on users Link");
					}
				} else {
					appLog.info("Not able to click on Manage User Icon");
					saa.assertTrue(false, "Not able to click on Manage User Icon");
				}
			} else {
				appLog.info("Not able to click on Setup link");
				saa.assertTrue(false, "Not able to click on Setup link");
			}
		} else {
			appLog.info("Not able to click on user menu tab");
			saa.assertTrue(false, "Not able to click on user menu tab");
		}
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (nim.clickOnSideMenusTab(sideMenu.Profiles)) {
				if (nim.clickOnEditIcon()) {
					if (sendKeys(driver, nim.getMyProfileFirstName(60), superAdminFirstName, "first Name",
							action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, nim.getMyProfileLastName(60), superAdminLastName, "Last Name",
								action.SCROLLANDBOOLEAN)) {
							if (click(driver, nim.getMyProfileSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)) {
								ThreadSleep(3000);
								String nameInViewMode = nim.getMyProfileNameInViewMode(60).getText().trim();
								if (nameInViewMode.equalsIgnoreCase(superAdminFirstName + " " + superAdminLastName)) {
									appLog.info("Name is displaying");
								} else {
									appLog.info("Name is not displaying");
									saa.assertTrue(false, "Name is not displaying");
								}
							} else {
								appLog.info("Not able to click on save button");
								saa.assertTrue(false, "Not able to click on save button");
							}
						} else {
							appLog.info("Not able to enter value in last name text box");
							saa.assertTrue(false, "Not able to enter value in last name text box");
						}

					} else {
						appLog.info("Not able to enter value in first name text box");
						saa.assertTrue(false, "Not able to enter value in first name text box");
					}
				} else {
					appLog.info("Not able to click on edit icon");
					saa.assertTrue(false, "Not able to click on edit icon");
				}
			} else {
				appLog.info("Not able to click on profiles tab");
				saa.assertTrue(false, "Not able to click on profiles tab");
			}
		} else {
			appLog.info("Not able to click on NIM Tab so we cannot change admin first name and last name");
			saa.assertTrue(false, "Not able to click on NIM Tab so we cannot change admin first name and last name");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);
		nim = new NIMPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if (nim.clickOnSideMenusTab(sideMenu.Profiles)) {
				if (nim.clickOnEditIcon()) {
					if (sendKeys(driver, nim.getMyProfileFirstName(60), CRMUser2FirstName, "New first Name",
							action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, nim.getMyProfileLastName(60), CRMUser2LastName, "New Last Name",
								action.SCROLLANDBOOLEAN)) {
							if (click(driver, nim.getMyProfileSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)) {
								ThreadSleep(3000);
								String nameInViewMode = nim.getMyProfileNameInViewMode(60).getText().trim();
								if (nameInViewMode.equalsIgnoreCase(CRMUser2FirstName + " " + CRMUser2LastName)) {
									appLog.info("name is displaying");
								} else {
									appLog.info("name is not displaying");
									saa.assertTrue(false, "name is not displaying");
								}
							} else {
								appLog.info("Not able to click on save button");
								saa.assertTrue(false, "Not able to click on save button");
							}
						} else {
							appLog.info("Not able to enter value in last name text box");
							saa.assertTrue(false, "Not able to enter value in last name text box");
						}
				} else {
						appLog.info("Not able to enter value in first name text box");
						saa.assertTrue(false, "Not able to enter value in first name text box");
					}
				} else {
					appLog.info("Not able to click on edit icon");
					saa.assertTrue(false, "Not able to click on edit icon");
				}

			} else {
				appLog.info("Not able to click on profiles tab");
				saa.assertTrue(false, "Not able to click on profiles tab");
			}
		} else {
			appLog.info("Not able to click on NIM Tab");
			saa.assertTrue(false, "Not able to click on NIm Tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}
	
	@Test
	public void M1tc025_postCondition(){
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
