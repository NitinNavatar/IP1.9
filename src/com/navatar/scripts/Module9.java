/**
 * 
 */
package com.navatar.scripts;

import org.apache.poi.openxml4j.opc.PackageAccess;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib.EditViewMode;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.ManageApprovalTabs;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.SortOrder;
import com.navatar.generic.CommonLib.TabName;
import com.navatar.generic.CommonLib.UpdateIgnore;
import com.navatar.generic.CommonLib.UploadFileActions;
import com.navatar.generic.CommonLib.WorkSpaceAction;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.activityType;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.generic.CommonLib.investorSideWorkSpace;
import com.navatar.generic.CommonLib.sideMenu;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.EmailLib;
import com.navatar.pageObjects.AllFirmsPageBusinesslayer;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.CommitmentPageBusinessLayer;
import com.navatar.pageObjects.ContactPageBusinessLayer;
import com.navatar.pageObjects.FundRaisingPageBusinessLayer;
import com.navatar.pageObjects.FundsPageBusinessLayer;
import com.navatar.pageObjects.FundsPageErrorMessage;
import com.navatar.pageObjects.InstitutionPageBusinessLayer;
import com.navatar.pageObjects.InvestorFirmPage;
import com.navatar.pageObjects.InvestorFirmPageBusinesslayer;
import com.navatar.pageObjects.InvestorFirmPageErrorMessage;
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.NIMPageBusinessLayer;
import com.navatar.pageObjects.NIMPageErrorMessage;
import com.navatar.pageObjects.PartnershipPageBusinessLayer;

import static com.navatar.generic.CommonVariables.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.BaseLib.sa;
import static com.navatar.generic.CommonLib.*;
/**
 * @author Akul Bhutani
 *
 */
public class Module9 extends BaseLib{

	@Test
	public void M9tc001_precondition() {
		//Prerequisite: all users present with no NIM access
	LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
	BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
	FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
	FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
	InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
	ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
	PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
	CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
	NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
	lp.CRMLogin(CRMUser1EmailID, adminPassword);

	// Institution
	if (bp.clickOnTab(TabName.InstituitonsTab)) {
		if (ip.createInstitution(M9Institution1)) {
			appLog.info("Institution Created Successfully : " + M9Institution1);
		} else {
			appLog.error("Not Able to Create Institution : " + M9Institution1);
			sa.assertTrue(false, "Not Able to Create Institution : " + M9Institution1);
		}
	}
	if (bp.clickOnTab(TabName.InstituitonsTab)) {
		if (ip.createInstitution(M9Institution2)) {
			appLog.info("Institution Created Successfully : " + M9Institution2);
		} else {
			appLog.error("Not Able to Create Institution : " + M9Institution2);
			sa.assertTrue(false, "Not Able to Create Institution : " + M9Institution2);
		}
	} else {
		appLog.error("Not Able to Click Institution tab");
		sa.assertTrue(false, "Not Able to Click Institution tab");
	}

	String M9Contact1EmailID = cp.generateRandomEmailId();

	// Contact
	if (bp.clickOnTab(TabName.ContactTab)) {
		if (cp.createContact(M9Contact1FirstName, M9Contact1LastName, M9Institution1, M9Contact1EmailID)) {
			ExcelUtils.writeData(M9Contact1EmailID, "Contacts", excelLabel.Variable_Name, "M9C1",
					excelLabel.Contact_EmailId);
			appLog.info("Contact " + M9Contact1FirstName + " " + M9Contact1LastName + " was successfully created");
		} else {
			appLog.error("Contact " + M9Contact1FirstName + " " + M9Contact1LastName + " could not be created");
			sa.assertTrue(false,
					"Contact " + M9Contact1FirstName + " " + M9Contact1LastName + " could not be created");
		}
	} else {
		appLog.error("Not Able to Click contact Tab");
		sa.assertTrue(false, "Not Able to Click contact Tab");
	}

	

	// Fund

	if (bp.clickOnTab(TabName.FundsTab)) {
		if (fp.createFund(M9FundName1, M9Fund1Type, M9Fund1InvestmentCategory)) {
			appLog.info("New fund " + M9FundName1 + " was successfully created");
		} else {
			appLog.error("New fund " + M9FundName1 + " could not be created");
			sa.assertTrue(false, "New fund " + M9FundName1 + " could not be created");
		}
	} else {
		appLog.error("Not Able to Click Funds tab ");
		sa.assertTrue(false, "Not Able to Click Funds tab ");
	}
	if (bp.clickOnTab(TabName.FundsTab)) {
		if (fp.createFund(M9FundName2, M9Fund2Type, M9Fund2InvestmentCategory)) {
			appLog.info("New fund " + M9FundName2 + " was successfully created");
		} else {
			appLog.error("New fund " + M9FundName2 + " could not be created");
			sa.assertTrue(false, "New fund " + M9FundName2 + " could not be created");
		}
	} else {
		appLog.error("Not Able to Click Funds tab ");
		sa.assertTrue(false, "Not Able to Click Funds tab ");
	}
	if (bp.clickOnTab(TabName.FundsTab)) {
		if (fp.createFund(M9FundName3, M9Fund3Type, M9Fund3InvestmentCategory)) {
			appLog.info("New fund " + M9FundName3 + " was successfully created");
		} else {
			appLog.error("New fund " + M9FundName3 + " could not be created");
			sa.assertTrue(false, "New fund " + M9FundName3 + " could not be created");
		}
	} else {
		appLog.error("Not Able to Click Funds tab ");
		sa.assertTrue(false, "Not Able to Click Funds tab ");
	}

	// Fund Raising
	if (bp.clickOnTab(TabName.FundraisingsTab)) {
		if (frp.createFundRaising(M9FundRaisingName1, M9FundName1, M9Institution1)) {
			appLog.info("Fundraising " + M9FundRaisingName1 + " was successfully created");
		} else {
			appLog.error("Fundraising " + M9FundRaisingName1 + " could not be created");
			sa.assertTrue(false, "Fundraising " + M9FundRaisingName1 + " could not be created");
		}
	} else {
		appLog.error("Not Able to Click Fundraising tab");
		sa.assertTrue(false, "Not Able to Click Fundraising tab");
	}
	
	
	if (bp.clickOnTab(TabName.FundraisingsTab)) {
		if (frp.createFundRaising(M9FundRaisingName2, M9FundName1, M9Institution2)) {
			appLog.info("Fundraising " + M9FundRaisingName1 + " was successfully created");
		} else {
			appLog.error("Fundraising " + M9FundRaisingName1 + " could not be created");
			sa.assertTrue(false, "Fundraising " + M9FundRaisingName1 + " could not be created");
		}
	} else {
		appLog.error("Not Able to Click Fundraising tab");
		sa.assertTrue(false, "Not Able to Click Fundraising tab");
	}
	// Limited Partner
	if (bp.clickOnTab(TabName.InstituitonsTab)) {
		if (ip.createLimitedPartner(M9LimitedPartner1, M9Institution1)) {
			appLog.info(M9LimitedPartner1 + " limited partner was successfully created");
		} else {
			appLog.error(M9LimitedPartner1 + " LP could not be created");
			sa.assertTrue(false, M9LimitedPartner1 + " LP could not be created");
		}
	} else {
		appLog.error("Not Able to Click Institution tab");
		sa.assertTrue(false, "Not Able to Click Institution tab");
	}

	// Limited Partner
	if (bp.clickOnTab(TabName.InstituitonsTab)) {
		if (ip.createLimitedPartner(M9LimitedPartner2, M9Institution2)) {
			appLog.info(M9LimitedPartner1 + " limited partner was successfully created");
		} else {
			appLog.error(M9LimitedPartner1 + " LP could not be created");
			sa.assertTrue(false, M9LimitedPartner1 + " LP could not be created");
		}
	} else {
		appLog.error("Not Able to Click Institution tab");
		sa.assertTrue(false, "Not Able to Click Institution tab");
	}
	// PartnerShip
	if (bp.clickOnTab(TabName.PartnershipsTab)) {
		if (pp.createPartnership(M9Partnership1, M9FundName1)) {
			appLog.info(M9Partnership1 + " was successfully created");
		} else {
			appLog.error(M9Partnership1 + " could not be created");
			sa.assertTrue(false, M9Partnership1 + " could not be created");
		}
	} else {
		appLog.error("Not Able to Click Partnership tab");
		sa.assertTrue(false, "Not Able to Click Partnership tab");
	}
	// PartnerShip
		if (bp.clickOnTab(TabName.PartnershipsTab)) {
			if (pp.createPartnership(M9Partnership2, M9FundName2)) {
				appLog.info(M9Partnership2 + " was successfully created");
			} else {
				appLog.error(M9Partnership2 + " could not be created");
				sa.assertTrue(false, M9Partnership2 + " could not be created");
			}
		} else {
			appLog.error("Not Able to Click Partnership tab");
			sa.assertTrue(false, "Not Able to Click Partnership tab");
		}
	// Commitment
	if (bp.clickOnTab(TabName.CommitmentsTab)) {
		if (cmp.createCommitment(M9LimitedPartner1, M9Partnership1, M9Commitment1, null)) {
			appLog.info(M9Commitment1 + " was successfully created");
		} else {
			appLog.error(M9Commitment1 + " could not be created");
			sa.assertTrue(false, M9Commitment1 + " could not be created");
		}
	} else {
		appLog.error("Not Able to Click Commitments tab");
		sa.assertTrue(false, "Not Able to Click Commitments tab");
	}
	// Commitment
	if (bp.clickOnTab(TabName.CommitmentsTab)) {
		if (cmp.createCommitment(M9LimitedPartner2, M9Partnership1, M9Commitment2, null)) {
			appLog.info(M9Commitment1 + " was successfully created");
		} else {
			appLog.error(M9Commitment1 + " could not be created");
			sa.assertTrue(false, M9Commitment1 + " could not be created");
		}
	} else {
		appLog.error("Not Able to Click Commitments tab");
		sa.assertTrue(false, "Not Able to Click Commitments tab");
	}


	lp.CRMlogout();
	sa.assertAll();
}

	@Test
	public void M9tc002_ManageApprovalWithoutAccess() {
		//prerequisite: 1. Manage Approval Deactivated. 2. No user should be given nim user access
	LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
	BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
	NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
	String adminFirstName = ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.User_First_Name);
	String adminLastName = ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.User_Last_Name);
	lp.CRMLogin(superAdminUserName, adminPassword);

	if (bp.clickOnTab(TabName.NIMTab)) {
	switchToFrame(driver, 30, bp.getFrame( PageName.NavatarInvestorManager, 30));
	if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
		if (np.getManageApprovalsHead(60).getText().trim().equals("Manage Approvals")) {
			appLog.info("manage approval heading is successfully displayed on manage approval page");
		}
		else {
			appLog.error("manage approvals heading is not visible on manage approvals page");
			sa.assertTrue(false, "manage approvals heading is not visible on manage approvals page");
		}
		if (np.getInfoIconManageApproval(60)!=null) {
			appLog.info("info icon is successfully displayed on manage approval window");
		}
		else {
			appLog.error("info icon is not visible on manage approvals page");
			sa.assertTrue(false, "info icon is not visible on manage approvals page");
		}
		if (np.getEditIcon(60)!=null) {
			appLog.info("edit icon is successfully displayed on manage approval window");
		}
		else {
			appLog.error("edit icon is not present on manage approvals page");
			sa.assertTrue(false, "edit icon is not present on manage approvals page");
		}
		if (np.getManageApprovalsActivateCheckbox(EditViewMode.View)!=null) {
			if (!isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.View), "manage approval activate checkbox")) {
				appLog.info("manage approval activate checkbox is present on page and is unchecked");
			}
			else {
				appLog.error("manage approval activate checkbox is checked already by default");
				sa.assertTrue(false, "manage approval activate checkbox is checked already by default");
			}
		}
		if (np.getActivateManageAppText(60).getText().trim().contains("Activate Manage Approvals")) {
			appLog.info("activate manage approvals text is succesfully verified");
		}
		else {
			appLog.error("activate manage approvals text is not present on manage approvals page");
			sa.assertTrue(false, "activate manage approvals text is not present on manage approvals page");
		}
		if (np.getCRMAdminHeadsManageApproval(60).get(0).getText().trim().equals("User")) {
			appLog.info("user tab is succeessfully present in crm admin heading");
		}
		else {
			appLog.error("user tab is not present in crm admin heading");
			sa.assertTrue(false, "user tab is not present in crm admin heading");
		}
		if (np.getCRMAdminHeadsManageApproval(60).get(1).getText().trim().equals("Manage Approvals")) {
			appLog.info("manage approval tab is succeessfully present in crm admin heading");
		}
		else {
			appLog.error("manage approval is not present in crm admin heading");
			sa.assertTrue(false, "manage approval is not present in crm admin heading");
		}
		if (np.getRowsCRMAdminManageApproval(60).getText().trim().equals(adminFirstName+" "+adminLastName)) {
			appLog.info("super admin full name is successfully present in crm admin rows");
		}
		else {
			appLog.error("admin name is not correct on manage approvals page");
			sa.assertTrue(false, "admin name is not correct on manage approvals page");
		}
		WebElement ele = isDisplayed(driver, FindElement(driver, "//span[contains(@id,'grid_CRMAdmin-cell-1')]", "checkbox for admin manage approval", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "checkbox for admin manage approval");
		if (ele.getAttribute("class").contains("aw-value-true")) {
			appLog.info("check box for admin manage approval is checked by default");
		}
		else {
			appLog.error("not ticked admin manage approval checkbox"+ele.getAttribute("class"));
			sa.assertTrue(false, "not ticked admin manage approval checkbox");
		}
		
		if (np.getUserHeadsManageApproval(60,EditViewMode.View).get(0).getText().trim().equals("User")) {
		appLog.info("user tab is successfully present in crm user heading");
		}
		else {
			appLog.error("user tab is not present in crm user heading");
			sa.assertTrue(false, "user tab is not present in crm user heading");
		}
		if (np.getUserHeadsManageApproval(60,EditViewMode.View).get(1).getText().trim().equals("Manage Approvals")) {
			appLog.info("manage approval tab is successfully present in user heading");
		}
		else {
			appLog.error("manage approval tab is not present in user heading");
			sa.assertTrue(false, "manage approval tab is not present in user heading");
		}
		if (np.getNoDataToDisplayInUsersTable(60, EditViewMode.View)!=null) {
		if (np.getNoDataToDisplayInUsersTable(60, EditViewMode.View).getText().trim().equals(NIMPageErrorMessage.noDataToDisplay)) {
			appLog.info("no data to display error message is successfully present on manage approvals page");
		}
		
		}else {
			appLog.error("'no data to display' error is not found");
			sa.assertTrue(false, "'no data to display' error is not found");
		}
		if (mouseOverOperation(driver, np.getInfoIconManageApproval(60))) {
			ThreadSleep(3000);
			if (np.getToolTipInfoIconManageApproval(60).getText().trim().equals(NIMPageErrorMessage.toolTipMessage)) {
				appLog.info("tool tip message is successfully verified. It is "+np.getToolTipInfoIconManageApproval(60).getText().trim());
			}
			else {
				appLog.error("tool tip text is not correct");
				sa.assertTrue(false, "tool tip text is not correct");
			}
		}
		else {
			appLog.error("mouse over on info icon could not be possible");
			sa.assertTrue(false, "mouse over on info icon could not be possible");
		}
		if (click(driver, np.getEditIcon(60), "edit icon on nim page manage approvals", action.SCROLLANDBOOLEAN)) {
			appLog.info("manage approval edit icon is successfully clicked");
		}
		
		if (np.getGoBackLink(60)!=null) {
			appLog.info("go back link is succesfully present on manage approvals page");
		}
		else {
			appLog.error("go back link is not present on manage approvals page");
			sa.assertTrue(false, "go back link is not present on manage approvals page");
		}
		if (!isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "manage approval activate checkbox")) {
			appLog.info("manage approval activate checkbox is present on page and is unchecked");
		}
		else {
			appLog.error("manage approval activate checkbox is checked, but it should have been unchecked");
			sa.assertTrue(false, "manage approval activate checkbox is checked, but it should have been unchecked");
		}
		if (np.getNoDataToDisplayInUsersTable(60, EditViewMode.Edit).getText().trim().equals(NIMPageErrorMessage.noDataToDisplay)) {
			appLog.info("no data to display error message is successfully present on manage approvals page");
		}
		else {
			appLog.error("'no data to display' error is not found");
			sa.assertTrue(false, "'no data to display' error is not found");
		}
		if ((np.getManageApprovalSaveButton(60)!=null)&&(np.getCancelButton(60)!=null)) {
			appLog.info("save button and cancel button is successfully present in manage approvals tab");
		}
		else {
			appLog.error("save button and cancel button are not present in manage approvals tab");
			sa.assertTrue(false, "save button and cancel button are not present in manage approvals tab");
		}
		//edit mode checkbox
		if (click(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "manage approval checkbox", action.SCROLLANDBOOLEAN)) {
			if (click(driver, np.getCancelButton(60), "cancel button on manage approval page", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(3000);
				//view mode checkbox
				if (!isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.View), "manage approval checkbox")) {
					appLog.info("manage approval checkbox is not selected after clicking on cancel button");
				}
				else {
					appLog.error("manage approval checkbox is selected even after clicking cancel button");
					sa.assertTrue(false, "manage approval checkbox is selected even after clicking cancel button");
				}
			}
			else {
				appLog.error("cancel button is not clickable on manage approvals window");
				sa.assertTrue(false, "cancel button is not clickable on manage approvals window");
			}
		}
		else {
			appLog.error("manage approvals activate checkbox is not clickable, it is disabled");
			sa.assertTrue(false, "manage approvals activate checkbox is not clickable, it is disabled");
		}
		click(driver, np.getEditIcon(60), "edit icon on nim page manage approvals", action.SCROLLANDBOOLEAN);
		//edit mode checkbox
		if (click(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "manage approval checkbox", action.SCROLLANDBOOLEAN)) {
			if (click(driver, np.getGoBackLink(60), "go back link on manage approval edit mode", action.SCROLLANDBOOLEAN)) {	
				ThreadSleep(3000);
				//view mode checkbox
				if (!isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.View), "manage approval checkbox")) {
					appLog.info("manage approval checkbox is not selected after clicking on cancel button");
				}
				else {
					appLog.error("manage approval checkbox is selected even after clicking cancel button");
					sa.assertTrue(false, "manage approval checkbox is selected even after clicking cancel button");
				}
			}
			else {
				appLog.error("cancel button is not clickable on manage approvals window");
				sa.assertTrue(false, "cancel button is not clickable on manage approvals window");
			}
		}
	}
	else {
		appLog.error("side menu of manage approvals is not clickable");
		sa.assertTrue(false, "side menu of manage approvals is not clickable");
	}
	}
	else {
		appLog.error("nim tab is not clickable on base page");
		sa.assertTrue(false, "nim tab is not clickable on base page");
	}
	switchToDefaultContent(driver);
	lp.CRMlogout();
	sa.assertAll();
}

	@Test
	public void M9tc003_ManageApprovalWithAccessToInternalUser() {
	//prerequisite: user2 will be given internal user access 2nd time
	LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
	BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
	NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
	String userName1 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_Last_Name);
	String userName2 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.User_Last_Name);
	String userName3 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User3", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User3", excelLabel.User_Last_Name);
	System.out.println(userName3+" name of user3");
	lp.CRMLogin(superAdminUserName, adminPassword);
	if (bp.clickOnTab(TabName.NIMTab)) {
		switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
		if (np.clickOnSideMenusTab(sideMenu.InternalUsers)) {
				switchToDefaultContent(driver);
				if (np.giveAccessToUserInNIMTabFromAdmin(userName1, accessType.InternalUserAccess)) {
					appLog.info("access has been successfully given to user "+userName1);
					switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
					switchToDefaultContent(driver);
				}
				else {
					appLog.error("internal user access could not be given to "+userName1);
					sa.assertTrue(false, "internal user access could not be given to "+userName1);
				}
				if (np.giveAccessToUserInNIMTabFromAdmin(userName2, accessType.InternalUserAccess)) {
					appLog.info("access has been successfully given to user "+userName2);
					switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
					switchToDefaultContent(driver);
				}
				else {
					appLog.error("internal user access could not be given to "+userName2);
					sa.assertTrue(false, "internal user access could not be given to "+userName2);
				}
				
		}
		else {
			appLog.error("side menu manage approvals is not clickable");
			sa.assertTrue(false, "side menu manage approvals is not clickable");
		}
	}
	else {
		appLog.error("nim tab is not clickable");
		sa.assertTrue(false, "nim tab is not clickable");
	}
	lp.CRMlogout();
	driver.close();
	//logging as user 2
	config(ExcelUtils.readDataFromPropertyFile("Browser"));
	lp = new LoginPageBusinessLayer(driver);
	bp = new BasePageBusinessLayer(driver);
	np = new NIMPageBusinessLayer(driver);
	


	lp.CRMLogin(CRMUser2EmailID, adminPassword);
	if (bp.clickOnTab(TabName.NIMTab)) {
		if (np.findRegistrationSuccessfulPopup()){
			appLog.info("user "+CRMUser2FirstName+" is now successfully registered");
		}
		else {
			appLog.error("this user needs to start registration from 1st step");
			if (np.NIMRegistration(userType.CRMUser, CRMUser2FirstName, CRMUser2LastName)) {
				appLog.info("now "+CRMUser2FirstName+" "+CRMUser2LastName+" is not successfully registered");
			}
		}
	}
	lp.CRMlogout();
	driver.close();
	//logging as crmadmin
	config(ExcelUtils.readDataFromPropertyFile("Browser"));
	lp = new LoginPageBusinessLayer(driver);
	bp = new BasePageBusinessLayer(driver);
	np = new NIMPageBusinessLayer(driver);
	

	lp.CRMLogin(superAdminUserName, adminPassword);
	if (bp.clickOnTab(TabName.NIMTab)) {
		if (np.giveAccessToUserInNIMTabFromAdmin(userName2, accessType.AdminUserAccess)) {
			appLog.info("admin access has been successfully given to user "+userName2);
			
		}
		else {
			appLog.error("could not register "+userName2);
			sa.assertTrue(false, "could not register "+userName2);
		}
	
	}
	boolean user1flag=false, user2flag = false;
	switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
	if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
		int size = np.getUserNamesManageApproval(60,EditViewMode.View).size();
		for (int i=0;i<size;i++) {
			if (np.getUserNamesManageApproval(60,EditViewMode.View).get(i).getText().trim().equals(userName1)) {
				appLog.info("username 1 is successfully found");
				user1flag = true;
			}
			if (np.getUserNamesManageApproval(60,EditViewMode.View).get(i).getText().trim().equals(userName2)) {
				appLog.info("username 2 is successfully found");
				user2flag = true;
			}

		}
		if (user1flag==false) {
			appLog.error(userName1+" was not found in user names list");
		}
		if (user2flag == false) {
			appLog.error(userName2+" was not found in user names list");
		}
		
		//verifying unchecked checkbox for user1 and user2
		if (np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName1)) {
			appLog.error("checkbox for "+userName1+" should not be checked, but it is");
			sa.assertTrue(false, "checkbox for "+userName1+" should not be checked, but it is");
		}
		else {
			appLog.info("checkbox for "+userName1+" is unchecked");
		}
		if (np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName2)) {
			appLog.error("checkbox for "+userName2+" should not be checked, but it is");
			sa.assertTrue(false, "checkbox for "+userName2+" should not be checked, but it is");
		}
		else {
			appLog.info("checkbox for "+userName2+" is unchecked");
		}
		//verifying user 3 is not present
		WebElement ele = isDisplayed(driver, FindElement(driver, "//span[text()='"+userName3+"']", "user name 3 text", action.SCROLLANDBOOLEAN, 10), "visibility", 10, "user name 3 text");
		if (ele==null) {
			appLog.info("user 3 "+userName3+" is not found as required");
		}
		ThreadSleep(5000);
		//verifying unchecked checkbox for user1 and user2(edit mode)
		if (np.clickOnEditIcon()) {
			if (np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, userName1)) {
				appLog.error("checkbox for "+userName1+" should not be checked, but it is");
				sa.assertTrue(false, "checkbox for "+userName1+" should not be checked, but it is");
			}
			else {
				appLog.info("checkbox for "+userName1+" is unchecked");
			}
			if (np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, userName2)) {
				appLog.error("checkbox for "+userName2+" should not be checked, but it is");
				sa.assertTrue(false, "checkbox for "+userName2+" should not be checked, but it is");
			}
			else {
				appLog.info("checkbox for "+userName2+" is unchecked");
			}
		}
		else {
			appLog.error("edit icon is not clickable on manage approvals page");
			sa.assertTrue(false, "edit icon is not clickable on manage approvals page");
		}
		appLog.info("Step:--------clicking on checkboxes of user 1 and user2 and verifying that they still remain unchecked");
		System.err.println("script ticks the checkbox, but manually we cannot tick");
		//clicking on checkboxes of user 1 and user2 and verifying that they still remain unchecked
		if (click(driver, np.findCheckboxForUserInManageApproval(userName1), "checkbox in front of "+userName1, action.SCROLLANDBOOLEAN)) {
			appLog.info("clicked on checkbox of "+userName1);
		}
		ThreadSleep(5000);
		if (np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, userName1)) {
			appLog.error("checkbox for "+userName1+" should not be checked, but it is");
			sa.assertTrue(false, "checkbox for "+userName1+" should not be checked, but it is");
		}
		else {
			appLog.info("clicked on checkbox for "+userName1+" still it is unchecked as expected");
		}
		click(driver, np.getUserHeadsManageApproval(30, EditViewMode.Edit).get(0), "users head", action.BOOLEAN);
		ThreadSleep(3000);

		click(driver,  np.getUserHeadsManageApproval(30, EditViewMode.Edit).get(0), "users head", action.BOOLEAN);
		ThreadSleep(3000);
		if (click(driver, np.findCheckboxForUserInManageApproval(userName2), "checkbox in front of "+userName2, action.SCROLLANDBOOLEAN)) {
			appLog.info("clicked on checkbox of "+userName2);
		}
		if (np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, userName2)) {
			appLog.error("checkbox for "+userName2+" should not be checked, but it is");
			sa.assertTrue(false, "checkbox for "+userName2+" should not be checked, but it is");
		}
		else {
			appLog.info("clicked on checkbox for "+userName2+" still it is unchecked as expected");
		}
	}
	else {
		appLog.error("manage approvals side menu is not clickable");
		sa.assertTrue(false, "manage approvals side menu is not clickable");
	}
	switchToDefaultContent(driver);
	lp.CRMlogout();
	sa.assertAll();
	

}
	
	@Test
	public void M9tc004_VerifyActivationOfManageApprovalSetting() {
	LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
	BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
	NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
	String userName1 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_Last_Name);
	lp.CRMLogin(superAdminUserName, adminPassword);
	if (bp.clickOnTab(TabName.NIMTab)) {
		switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
		if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
			if (np.clickOnEditIcon()) {
				if (click(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "activate manage approval checkbox", action.SCROLLANDBOOLEAN)) {
					if (click(driver, np.getManageApprovalSaveButton(60), "save button on manage approvals page", action.SCROLLANDBOOLEAN)) {
						if (np.getManageApprovalActivateNoButton(60)!=null) {
							appLog.info("manage approval no button is successfully verified");
						}
						else {
							appLog.error("manage approval no button is not present on activation popup");
							sa.assertTrue(false, "manage approval no button is not present on activation popup");
						}
						if (np.getManageApprovalActivateYesButton(60)!=null) {
							appLog.info("manage approval yes button is successfully verified");
						}
						else {
							appLog.error("manage approval yes button is not presnet on activation popup");
							sa.assertTrue(false, "manage approval yes button is not presnet on activation popup");
						}
						if(np.getAllSelectedUsersWillBeGrantedText(60).getText().trim().equals(NIMPageErrorMessage.allSelectedUsersText)) {
							appLog.info("all selected users text for activation is correct");
						}
						else {
							appLog.error("all selected users text is not present in activation popup");
							sa.assertTrue(false, "all selected users text is not present in activation popup");
						}
						if (np.getDoYouWantToProceedText(60).getText().trim().equals(NIMPageErrorMessage.areYouSureYouWantToProceed)) {
							appLog.info("alert pop up for activation of manage approval is correct");
						}
						else {
							appLog.error("are you sure you want to proceed text is not present on activation popup");
							sa.assertTrue(false, "are you sure you want to proceed text is not present on activation popup");
						}
					
					if (click(driver, np.getManageApprovalActivateNoButton(60), "no button on manage approval activation popup", action.SCROLLANDBOOLEAN)) {
						if (np.getGoBackLink(60)!=null) {
							appLog.info("go back link is successfully displayed");
						}
						else {
							appLog.error("go back link is not displayed on manage approvals page");
							sa.assertTrue(false, "go back link is not displayed on manage approvals page");
						}
						if (!isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "checkbox for manage approval activation")) {
							appLog.info("manage approval activation checkbox is disabled as expected");
						}
						else {
							appLog.error("manage approval activate checkbox is not visible on manage approvals window");
							sa.assertTrue(false, "manage approval activate checkbox is not visible on manage approvals window");
						}
					}
					else {
						appLog.error("manage approval activation popup no button is not clickable");
						sa.assertTrue(false, "manage approval activation popup no button is not clickable");
					}
					
				}
					else {
						appLog.error("save button on manage approvals page is not clickable");
						sa.assertTrue(false, "save button on manage approvals page is not clickable");
					}
			}
				else {
					appLog.error("manage approval activate checkbox is not clickable");
					sa.assertTrue(false, "manage approval activate checkbox is not clickable");
				}
				if (click(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "activate manage approval checkbox", action.SCROLLANDBOOLEAN)) {
				if (click(driver, np.findCheckboxForUserInManageApproval(userName1),"checkbox in front of "+userName1, action.SCROLLANDBOOLEAN)) {
					if (click(driver, np.getManageApprovalSaveButton(60), "manage approval save button", action.SCROLLANDBOOLEAN)) {
						if (click(driver, np.getCrossIconOnManageApprovalActivationPopup(60), "cross icon on manage approval popup", action.SCROLLANDBOOLEAN)) {
							//verify activation checkbox and user manage app checkbox is unchecked
							if (np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, userName1)) {
								appLog.error("checkbox for manage approval right of "+userName1+" is checked");
								sa.assertTrue(false, "checkbox for manage approval right of "+userName1+" is checked");
							}
							if (!isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "activation checkbox manage approval")) {
								appLog.info("activation checkbox is not checked as expected");
							}
							else {
								appLog.error("activation checkbox is checked, even after clicking cross icon");
								sa.assertTrue(false, "activation checkbox is checked, even after clicking cross icon");
							}
						}
						else {
							appLog.error("cross icon on manage approval activation popup is not clickable");
							sa.assertTrue(false, "cross icon on manage approval activation popup is not clickable");
						}
					}
					else {
						appLog.error("save button on manage approval page is not clickable");
						sa.assertTrue(false, "save button on manage approval page is not clickable");
					}
				}
				else {
					appLog.error("checkbox for user "+userName1+" is not clickable");
					sa.assertTrue(false, "checkbox for user "+userName1+" is not clickable");
				}
				}
				else {
					appLog.error("manage approval activate checkbox is not clickable");
					sa.assertTrue(false, "manage approval activate checkbox is not clickable");
				}
				if (click(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "manage approvals activate checkbox in edit mode", action.SCROLLANDBOOLEAN)) {
					if (click(driver, np.findCheckboxForUserInManageApproval(userName1),"checkbox in front of "+userName1, action.SCROLLANDBOOLEAN)) {
						if (click(driver, np.getManageApprovalSaveButton(60), "manage approval save button", action.SCROLLANDBOOLEAN)) {
							if (click(driver, np.getManageApprovalActivateYesButton(60), "manage approval activate yes button", action.SCROLLANDBOOLEAN)) {
								if (np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName1)) {
									appLog.info("checkbox for manage approval right of "+userName1+" is checked successfully");
									
								}
								else {
									appLog.error("checkbox for manage approval right of "+userName1+" is not checked");
									sa.assertTrue(false, "checkbox for manage approval right of "+userName1+" is not checked");
								}
								if (isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.View), "activation checkbox manage approval")) {
									appLog.info("activation checkbox is checked successfully");
								}
								else {
									appLog.error("activation checkbox is not checked after clicking yes button");
									sa.assertTrue(false, "activation checkbox is not checked, after clicking yes button");
								}
							}
							else {
								appLog.error("manage approval yes button is not clickable");
								sa.assertTrue(false, "manage approval yes button is not clickable");
							}
						}
						else {
							appLog.error("manage approval save button is not clickable");
							sa.assertTrue(false, "manage approval save button is not clickable");
						}
					}
					else {
						appLog.error("checkbox in front of "+userName1+" is not clickable");
						sa.assertTrue(false, "checkbox in front of "+userName1+" is not clickable");
					}
				}
				else {
					appLog.error("manage approval main activate checkbox is not clickable");
					sa.assertTrue(false, "manage approval main activate checkbox is not clickable");
				}
				
				
		}
			else {
				appLog.error("edit icon is not clickable on manage approvals page");
				sa.assertTrue(false, "edit icon is not clickable on manage approvals page");
			}
	}
		else {
			appLog.error("side menu manage approvals is not clickable");
			sa.assertTrue(false, "side menu manage approvals is not clickable");
		}
		switchToDefaultContent(driver);
}
	else {
		appLog.error("nim tab is not clickable");
		sa.assertTrue(false, "nim tab is not clickable");
	}
	lp.CRMlogout();
	sa.assertAll();
}
	
	@Test
	public void M9tc005_VerifyManageApprovalsUserSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String userName1 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_Last_Name);
		String size = ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M9F1", excelLabel.Fund_Size);
		String year = ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M9F1", excelLabel.Fund_VintageYear);
		String contact_name = ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M9F1", excelLabel.Fund_Contact);
		String contact_phone = ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M9F1", excelLabel.Fund_Phone);
		String contact_email = ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M9F1", excelLabel.Fund_Email);
		String desc = ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M9F1", excelLabel.Fund_Description);
		
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			if (np.findRegistrationSuccessfulPopup()) {
				appLog.info("registration for user1 "+CRMUser1FirstName+" "+CRMUser1LastName+" is done successfully");
			}
			else {
				if (np.NIMRegistration(userType.CRMUser, CRMUser1FirstName, CRMUser1LastName)) {
					appLog.info("nim registration for user1 is successful");
				}
				else {
					appLog.error("nim registration was unsuccesful for user 1");
					sa.assertAll();
				}
			}
			
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
				if(isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.View),"manage approvals checkbox view mode")) {
					appLog.info("manage approvals activate checkbox is selected and is disabled");
				}
				else {
					appLog.error("manage approvals activate checkbox is not checked");
					sa.assertTrue(false, "manage approvals activate checkbox is not checked");
				}
				if (np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName1)) {
					appLog.info("checkbox for "+userName1+" is selected already as required");
				}
				else {
					appLog.error("checkbox for "+userName1+" not selected, but it should be");
					sa.assertTrue(false, "checkbox for "+userName1+" not selected, but it should be");
				}
				if (np.clickOnEditIcon()) {
					if (np.getInsufficientPermissionHeader(60,sideMenu.ManageApprovals) != null) {
						if (bp.verifyErrorMessageOnPage(
								NIMPageErrorMessage.insufficientPermissionErrorMessage,
								np.getInsufficientPermissionErrorMesage(60, sideMenu.ManageApprovals),
								"Insufficient Permission Error messgae")) {
							appLog.info("Insufficient Permission Error messgae is verified");
						} else {
							sa.assertTrue(false,
									"Insufficient Permission Error messgae is not verified.Expected:"
											+ NIMPageErrorMessage.insufficientPermissionErrorMessage
											+ " Actual"
											+ getText(driver, np.getInsufficientPermissionErrorMesage(60,sideMenu.ManageApprovals),
													"Insufficient Permission Error messgae",
													action.BOOLEAN));
						}
						if (click(driver, np.getInsufficientPermissionClosButton(60,sideMenu.ManageApprovals),
								"Insufficient permission Close button", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on insufficient permission close button");
							if (np.verifyLandingPageText("Manage Approvals")) {
								appLog.info("Manage Approvals page is avaiable");
							} else {
								appLog.info("Manage Approvals page is not avaiable");
								sa.assertTrue(false, "Manage Approvals page is not avaiable");
							}
							if (np.getEditIcon(60) != null) {
								appLog.info("Edit icon is displaying");
							} else {
								appLog.info("Edit icon is displaying");
								sa.assertTrue(false, "Edit icon is not displaying");
							}
						} else {
							appLog.info("Not able to click on insufficient permission close button");
							sa.assertTrue(false,
									"Not able to click on insufficient permission close button");
						}
					} else {
						appLog.info("Insufficient Permission popup is not displaying");
						sa.assertTrue(false, "Insufficient Permission popup is not displaying");
					}
				}
				
				if (np.clickOnEditIcon()) {
					if (np.getInsufficientPermissionHeader(60,sideMenu.ManageApprovals) != null) {
						if (bp.verifyErrorMessageOnPage(
								NIMPageErrorMessage.insufficientPermissionErrorMessage,
								np.getInsufficientPermissionErrorMesage(60, sideMenu.ManageApprovals),
								"Insufficient Permission Error messgae")) {
							appLog.info("Insufficient Permission Error messgae is verified");
						} else {
							sa.assertTrue(false,
									"Insufficient Permission Error messgae is not verified.Expected:"
											+ NIMPageErrorMessage.insufficientPermissionErrorMessage
											+ " Actual"
											+ getText(driver, np.getInsufficientPermissionErrorMesage(60,sideMenu.ManageApprovals),
													"Insufficient Permission Error messgae",
													action.BOOLEAN));
						}
						if (click(driver, np.getInsufficientPermissionCrossIcon(60,sideMenu.ManageApprovals),
								"Insufficient permission Close button", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on insufficient permission close button");
							if (np.verifyLandingPageText("Manage Approvals")) {
								appLog.info("Manage Approvals page is avaiable");
							} else {
								appLog.info("Manage Approvals page is not avaiable");
								sa.assertTrue(false, "Manage Approvals page is not avaiable");
							}
							if (np.getEditIcon(60) != null) {
								appLog.info("Edit icon is displaying");
							} else {
								appLog.info("Edit icon is displaying");
								sa.assertTrue(false, "Edit icon is not displaying");
							}
						} else {
							appLog.info("Not able to click on insufficient permission close button");
							sa.assertTrue(false,
									"Not able to click on insufficient permission close button");
						}
					} else {
						appLog.info("Insufficient Permission popup is not displaying");
						sa.assertTrue(false, "Insufficient Permission popup is not displaying");
					}
				}
				
				
				
				
				
			}
			switchToDefaultContent(driver);
			if (np.clickOnTab(TabName.FundsTab)) {
				if (fp.clickOnCreatedFund(M9FundName1)) {
					String[] step1 = {size,year,contact_name, contact_phone,contact_email,desc};
					if (fp.buildWorkspace(step1, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null, M9Institution1+"<break>"+M9Institution2, Workspace.FundraisingWorkspace, 30)){
						appLog.info("fundraising worskpace has been created succesfully");
					}
					if (fp.buildWorkspace(step1, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null,M9Institution1+"/"+M9LimitedPartner1+"<break>"+M9Institution2+"/"+M9LimitedPartner2, Workspace.InvestorWorkspace, 30)) {
						appLog.info("investor workspace has been successfully created");
					}
				}
				else {
					appLog.error("cannot click on fund "+M9FundName1);
					sa.assertTrue(false, "cannot click on fund "+M9FundName1);
				}
			}
			else {
				appLog.error("cannot click on funds tab");
				sa.assertTrue(false, "cannot click on funds tab");
			}
		}
	
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
}

	@Test
	public void M9tc006_VerifyingUncheckManageAppSettingForUser() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String userName1 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_Last_Name);
		String userName2 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.User_Last_Name);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
				if (np.clickOnEditIcon()) {
					//if username 2 is not checked, then check it
					if (!np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, userName2)) {
						if (click(driver, np.findCheckboxForUserInManageApproval(userName2), "manage approval checkbox for "+userName2, action.BOOLEAN)) {
							appLog.info("successully clicked on chkecbox of "+userName2);
						}
						else {
							appLog.error("checkbox for "+userName2+" is not clickable");
							sa.assertTrue(false, "checkbox for "+userName2+" is not clickable");
						}
					}
					else {
						appLog.info("checkbox of "+userName2+" was already checked");
					}
					//if username 1 is checked, then uncheck it
					if (np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, userName1)) {
						if (click(driver, np.findCheckboxForUserInManageApproval(userName1), "manage approval checkbox for "+userName1, action.BOOLEAN)) {
							appLog.info("successfully unchecked chkecbox of "+userName1);
						}
						else {
							appLog.error("checkbox for "+userName1+" is not clickable");
							sa.assertTrue(false, "checkbox for "+userName1+" is not clickable");
						}
					}
					else {
						appLog.info("checkbox of "+userName1+"already unchecked");
					}
					boolean userflag = true;
					if (click(driver, np.getManageApprovalSaveButton(60), "save button on manage approvals page", action.SCROLLANDBOOLEAN)) {
						if (click(driver, np.getManageApprovalActivateYesButton(60), "yes button on manage approvals popup", action.SCROLLANDBOOLEAN)) {
							System.err.println("bug");
							//verifying in view mode, username 2 will be checked and username 1 will be unchecked
							if (!np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName1)) {
								appLog.info(userName1+" is unchecked successfully");
							}
							else {
								userflag = false;
								appLog.error(userName1 + " is checked, but it should not be");
								sa.assertTrue(false, userName1 + " is checked, but it should not be");
							}
							if (np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName2)) {
								appLog.info(userName2 + " is checked as expected");
							}
							else {
								userflag = false;
								appLog.error(userName2 + " is not checked but should be");
								sa.assertTrue(false, userName2 + " is not checked but should be");
							}
							if(userflag == false) {
								appLog.info("bug found, now correcting users access to approvals");
								if (np.clickOnEditIcon()) {
									if (!np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, userName2)) {
										if (click(driver, np.findCheckboxForUserInManageApproval(userName2), "manage approval checkbox for "+userName2, action.BOOLEAN)) {
											appLog.info("successully clicked on chkecbox of "+userName2);
										}
										else {
											appLog.error("checkbox for "+userName2+" is not clickable");
											sa.assertTrue(false, "checkbox for "+userName2+" is not clickable");
										}
									}
									else {
										appLog.info("checkbox of "+userName2+" was already checked");
									}
									if (click(driver, np.getManageApprovalSaveButton(60), "save button on manage approvals page", action.SCROLLANDBOOLEAN)) {
										if (click(driver, np.getManageApprovalActivateYesButton(60), "yes button on manage approvals popup", action.SCROLLANDBOOLEAN)) {
										
										}
										else {
											appLog.error("activate yes button is not clickable");
											sa.assertTrue(false, "activate yes button is not clickable");
										}
									}
									else {
										appLog.error("save button is not clickable");
										sa.assertTrue(false, "save button is not clickable");
									}
								}
								if (np.clickOnEditIcon()) {
									if (np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, userName1)) {
										if (click(driver, np.findCheckboxForUserInManageApproval(userName1), "manage approval checkbox for "+userName1, action.BOOLEAN)) {
											appLog.info("successfully unchecked chkecbox of "+userName1);
										}
										else {
											appLog.error("checkbox for "+userName1+" is not clickable");
											sa.assertTrue(false, "checkbox for "+userName1+" is not clickable");
										}
									}
									else {
										appLog.info("checkbox of "+userName1+"already unchecked");
									}
								}
								if (click(driver, np.getManageApprovalSaveButton(60), "save button on manage approvals page", action.SCROLLANDBOOLEAN)) {
									if (click(driver, np.getManageApprovalActivateYesButton(60), "yes button on manage approvals popup", action.SCROLLANDBOOLEAN)) {
									}
									else {
										appLog.error("activate yes button is not clickable");
										sa.assertTrue(false, "activate yes button is not clickable");
									}
								}
								else {
									appLog.error("save button is not clickable");
									sa.assertTrue(false, "save button is not clickable");
								}
							}
							boolean user1flag = false, user2flag = false;
							int size = np.getUserNamesManageApproval(60,EditViewMode.View).size();
							for (int i=0;i<size;i++) {
								if (np.getUserNamesManageApproval(60,EditViewMode.View).get(i).getText().trim().equals(userName1)) {
									appLog.info("username 1 is successfully found");
									user1flag = true;
								}
								if (np.getUserNamesManageApproval(60,EditViewMode.View).get(i).getText().trim().equals(userName2)) {
									appLog.info("username 2 is successfully found");
									user2flag = true;
								}
							}
							if (user1flag!=true) {
								appLog.error(userName1+" is not found in usernames list on manage approval page");
								sa.assertTrue(false, userName1+" is not found in usernames list on manage approval page");
							}
							if (user2flag!=true) {
								appLog.error(userName2+" is not found in usernames list on manage approval page");
								sa.assertTrue(false, userName2+" is not found in usernames list on manage approval page");
							}
						}
						else {
							appLog.error("manage approval popup yes button is not clickable");
							sa.assertTrue(false, "manage approval popup yes button is not clickable");
						}
					}
					else {
						appLog.error("manage approval page save button is not clickable");
						sa.assertTrue(false, "manage approval page save button is not clickable");
					}
				}
				else {
					appLog.error("edit icon is not clickable on manage approvals page");
					sa.assertTrue(false, "edit icon is not clickable on manage approvals page");
				}
				//verify sorting
				if (click(driver, np.getUserHeadsManageApproval(60,EditViewMode.View).get(0), "users tab", action.SCROLLANDBOOLEAN)) {
					if (bp.checkSorting(SortOrder.Assecending, np.getUserNamesManageApproval(60, EditViewMode.View))) {
						appLog.info("sorting is successfully verified for users column ascending order");
					}
					else {
						appLog.error("sorting cannot be verified in ascending order");
						sa.assertTrue(false, "sorting cannot be verified in ascending order");
					}
					if (click(driver, np.getUserHeadsManageApproval(60,EditViewMode.View).get(0), "users tab", action.SCROLLANDBOOLEAN)) {
						if (bp.checkSorting(SortOrder.Decending, np.getUserNamesManageApproval(60, EditViewMode.View))) {
							appLog.info("sorting is successfully verified for users column descending order");
						}
						else {
							appLog.error("sorting could not be verified for users column descending order");
							sa.assertTrue(false, "sorting could not be verified for users column descending order");
						}
					
					}
					else {
						appLog.error("users head is not clickable on manage approvals page");
						sa.assertTrue(false, "users head is not clickable on manage approvals page");
					}
					//sorting on basis of checkbox
					if (click(driver, np.getUserHeadsManageApproval(30, EditViewMode.View).get(1), "checkboxes tab", action.BOOLEAN)) {
						if (np.checkSortingCheckBox(driver, SortOrder.Assecending, np.getCheckboxesManageApproval(EditViewMode.View,10))) {
							appLog.info("successfully verified sorting on checkboxes");
						}
						else {
							appLog.error("could not verify sorting on checkboxes");
							sa.assertTrue(false, "could not verify sorting on checkboxes");
						}
						if (click(driver, np.getUserHeadsManageApproval(30, EditViewMode.View).get(1), "checkboxes tab", action.BOOLEAN)) {
							if (np.checkSortingCheckBox(driver, SortOrder.Decending, np.getCheckboxesManageApproval(EditViewMode.View,10))) {
								appLog.info("successfully verified sorting on checkboxes");
							}
							else {
								appLog.error("could not verify sorting on checkboxes");
								sa.assertTrue(false, "could not verify sorting on checkboxes");
							}
						}
					}
					else {
						appLog.error("could not click on checkboxes header");
						sa.assertTrue(false, "could not click on checkboxes header");
					}
					if (click(driver, np.getUserHeadsManageApproval(30, EditViewMode.View).get(1), "checkboxes tab", action.BOOLEAN)) {
					}
					else {
						appLog.error("could not click on checkboxes header");
						sa.assertTrue(false, "could not click on checkboxes header");
					}
					np.clickOnSideMenusTab(sideMenu.ManageApprovals);
					ThreadSleep(3000);
				}
				else {
					appLog.error("users head is not clickable on manage approvals page");
					sa.assertTrue(false, "users head is not clickable on manage approvals page");
				}
				if (np.clickOnEditIcon()) {
					//verify sorting
					
					if (click(driver, np.getUserHeadsManageApproval(60,EditViewMode.Edit).get(0), "users tab", action.SCROLLANDBOOLEAN)) {
						if (bp.checkSorting(SortOrder.Assecending, np.getUserNamesManageApproval(60, EditViewMode.Edit))) {
							appLog.info("sorting is successfully verified for users column ascending order");
						}
						else {
							appLog.error("sorting cannot be verified in ascending order");
							sa.assertTrue(false, "sorting cannot be verified in ascending order");
						}
						if (click(driver, np.getUserHeadsManageApproval(60,EditViewMode.Edit).get(0), "users tab", action.SCROLLANDBOOLEAN)) {
							if (bp.checkSorting(SortOrder.Decending, np.getUserNamesManageApproval(60, EditViewMode.Edit))) {
								appLog.info("sorting is successfully verified for users column descending order");
							}
							else {
								appLog.error("sorting could not be verified for users column descending order");
								sa.assertTrue(false, "sorting could not be verified for users column descending order");
							}
						}
						else {
							appLog.error("users head is not clickable on manage approvals page");
							sa.assertTrue(false, "users head is not clickable on manage approvals page");
						}
						
						//sorting on basis of checkbox
						if (click(driver, np.getUserHeadsManageApproval(30, EditViewMode.Edit).get(1), "checkboxes tab", action.BOOLEAN)) {
							if (np.checkSortingCheckBox(driver, SortOrder.Assecending, np.getCheckboxesManageApproval(EditViewMode.Edit,10))) {
								appLog.info("successfully verified sorting on checkboxes");
							}
							else {
								appLog.error("could not verify sorting on checkboxes");
								sa.assertTrue(false, "could not verify sorting on checkboxes");
							}
							if (click(driver, np.getUserHeadsManageApproval(30, EditViewMode.Edit).get(1), "checkboxes tab", action.BOOLEAN)) {
								if (np.checkSortingCheckBox(driver, SortOrder.Decending, np.getCheckboxesManageApproval(EditViewMode.Edit,10))) {
									appLog.info("successfully verified sorting on checkboxes");
								}
								else {
									appLog.error("could not verify sorting on checkboxes");
									sa.assertTrue(false, "could not verify sorting on checkboxes");
								}
							}
						}
						else {
							appLog.error("could not click on checkboxes header");
							sa.assertTrue(false, "could not click on checkboxes header");
						}
						np.clickOnSideMenusTab(sideMenu.ManageApprovals);
						ThreadSleep(3000);
					}
					else {
						appLog.error("users head is not clickable on manage approvals page");
						sa.assertTrue(false, "users head is not clickable on manage approvals page");
					}
				}
				else {
					appLog.error("edit icon is not clickable on manage approval page");
					sa.assertTrue(false, "edit icon is not clickable on manage approval page");
				}
		}
			else {
				appLog.error("side menu manage approvals is not clickable");
				sa.assertTrue(false, "side menu manage approvals is not clickable");
			}
			switchToDefaultContent(driver);
	}
		else {
			appLog.error("nim tab is not clickable");
			sa.assertTrue(false, "nim tab is not clickable");
		}
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		np = new NIMPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		
		
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		//for user 1 manage approval permission error will be displayed
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
			scrollDownThroughWebelement(driver, fp.getFundRaisingWorkSpaceSection(60), "fundraising workspace section");
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30)!=null) {
				appLog.info("manage approval icon is successfully present for user 1 in fundraising workspace");
				WebElement ele= BaseLib.edriver.findElement(By.cssSelector("div#frworkspace a[title=\"Manage Approvals\"] > img"));
				try{
					scrollDownThroughWebelement(driver, ele, "Yes Button");
					ele.click();
					appLog.info("clicked on Yes Button");
				}catch(Exception e){
					appLog.error("Not able to click on Yes button so cannot clear fundraising workspace");
					BaseLib.sa.assertTrue(false, "Not able to click on Yes button so cannot clear fundraising workspace");
				}

					ThreadSleep(5000);
					if (isAlertPresent(driver)) {
						String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
						switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
						if (msg.contains(FundsPageErrorMessage.manageApprovalPermissionError)) {
							appLog.info("correct alert error is successfully present when manage approval access is not given to user 1");
						}
						else {
							appLog.error("correct alert error message is not present for manage approval clicking");
							sa.assertTrue(false, "correct alert error message is not present for manage approval clicking");
						}
					}
					else {
						appLog.error("no alert message is present when manage approval is clicked");
						sa.assertTrue(false, "no alert message is present when manage approval is clicked");
					}
				
			}
			else {
				appLog.error("manage approval icon is not present on fundraising workspace");
				sa.assertTrue(false, "manage approval icon is not present on fundraising workspace");
			}
			
			if (fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30)!=null) {
				appLog.info("manage approval icon is successfully present for user 1 in InvestorWorkspace");
				
				WebElement ele= BaseLib.edriver.findElement(By.cssSelector("div#invworkspace a[title=\"Manage Approvals\"] > img"));
				try{
					scrollDownThroughWebelement(driver, ele, "Yes Button");
					ele.click();
					appLog.info("clicked on Yes Button");
				}catch(Exception e){
					appLog.error("Not able to click on Yes button so cannot clear fundraising workspace");
					BaseLib.sa.assertTrue(false, "Not able to click on Yes button so cannot clear fundraising workspace");
				}
			
					ThreadSleep(5000);
					if (isAlertPresent(driver)) {
						String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
						switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
						if (msg.equals(FundsPageErrorMessage.manageApprovalPermissionError)) {
							appLog.info("correct alert error is successfully present when manage approval access is not given to user 1");
						}
						else {
							appLog.error("correct alert error message is not present for manage approval clicking");
							sa.assertTrue(false, "correct alert error message is not present for manage approval clicking");
						}
					}
					else {
						appLog.error("no alert message is present when manage approval is clicked");
						sa.assertTrue(false, "no alert message is present when manage approval is clicked");
					}
				
			}
			else {
				appLog.error("manage approval icon is not present on InvestorWorkspace");
				sa.assertTrue(false, "manage approval icon is not present on InvestorWorkspace");
			}
			
			switchToDefaultContent(driver);
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		np = new NIMPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		//not to add, already clicked on reg close button
		/*if (bp.clickOnTab(TabName.NIMTab)) {
			if (np.findRegistrationSuccessfulPopup()) {
				appLog.info("registration is done successfully for user 2 2nd time");
			}
			else {
				if (np.NIMRegistration(userType.CRMUser, CRMUser2FirstName, CRMUser2LastName)) {
					appLog.info("user 2 "+CRMUser2FirstName+" "+CRMUser2LastName+" is now registered for first time");
				}
				else {
					appLog.error("user 2 "+CRMUser2FirstName+" "+CRMUser2LastName+"could not be registered successfully");
				}
			}
		}
		else {
			appLog.error("nim tab is not clickable");
			sa.assertTrue(false, "nim tab is not clickable");
		}
		*/if (np.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				//for user 2, manage approval popup will be displayed
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30)!=null) {
				appLog.info("manage approval icon is successfully present for user 2 in fundraising workspace");
				if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "fundraising workspace manage approval", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(5000);
					if (fp.getManageAppHeadText(60).getText().trim().equals("Manage Approvals")) {
						appLog.info("manage approvals header for manage approvals popup is successfully verified");
					}
					else {
						appLog.error("manage approvals popup header is not correct");
						sa.assertTrue(false, "manage approvals popup header is not correct");
					}
					
					if (click(driver, fp.getManageAppCancelBtn(60), "manage approval popup cancel button", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on manage approvals cancel button");
					}
					else {
						appLog.error("cannot click on manage approvals canecl button");
						sa.assertTrue(false, "cannot click on manage approvals canecl button");
					}
				}
				else {
					appLog.error("manage approval icon is not clickable");
					sa.assertTrue(false, "manage approval icon is not clickable");
				}
			}
			else {
				appLog.error("manage approval icon is not present on fundraising workspace");
				sa.assertTrue(false, "manage approval icon is not present on fundraising workspace");
			}
			
			if (fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30)!=null) {
				appLog.info("manage approval icon is successfully present for user 2 in InvestorWorkspace");
				if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "InvestorWorkspace manage approval", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(5000);
					if (fp.getManageAppHeadText(60).getText().trim().equals("Manage Approvals")) {
						appLog.info("manage approvals header for manage approvals popup is successfully verified");
					}
					else {
						appLog.error("manage approvals popup header is not correct");
						sa.assertTrue(false, "manage approvals popup header is not correct");
					}
					if (click(driver, fp.getManageAppCancelBtn(60), "manage approval popup cancel button", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on manage approvals cancel button");
					}
					else {
						appLog.error("cannot click on manage approvals canecl button");
						sa.assertTrue(false, "cannot click on manage approvals canecl button");
					}
				}
				else {
					appLog.error("manage approval icon is not clickable");
					sa.assertTrue(false, "manage approval icon is not clickable");
				}
			}
			else {
				appLog.error("manage approval icon is not present on fundraising workspace");
				sa.assertTrue(false, "manage approval icon is not present on fundraising workspace");
			}
			
			switchToDefaultContent(driver);
		
		}
			else {
				appLog.error("fund "+M9FundName1+" cannot be found");
				sa.assertTrue(false, "fund "+M9FundName1+" cannot be found");
			}
			}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc007_VerifyEditManageApprovalSettingDelegateAdmin() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String userName1 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_Last_Name);
		String userName2 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.User_Last_Name);
		//user 2 is delegate admin
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
				if (np.clickOnEditIcon()) {
					if (np.getGoBackLink(60)!=null) {
						appLog.info("go back link is available on clicking edit icon in manage approvals page edit mode");
					}
					else {
						appLog.error("go back link is not found on manage approvals page edit mode");
						sa.assertTrue(false, "go back link is not found on manage approvals page edit mode");
					}
					if (isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "activate manage approvals checkbox")) {
						appLog.info("manage approvals checkbox is selected");
					}
					else {
						appLog.error("manage approvals checkbox is not selected, but it should be");
						sa.assertTrue(false, "manage approvals checkbox is not selected, but it should be");
					}
					
					boolean user1flag = false, user2flag = false;
					int size = np.getUserNamesManageApproval(60,EditViewMode.Edit).size();
					for (int i=0;i<size;i++) {
						if (np.getUserNamesManageApproval(60,EditViewMode.Edit).get(i).getText().trim().equals(userName1)) {
							appLog.info("username 1 is successfully found");
							user1flag = true;
						}
						if (np.getUserNamesManageApproval(60,EditViewMode.Edit).get(i).getText().trim().equals(userName2)) {
							appLog.info("username 2 is successfully found");
							user2flag = true;
						}
					}
					if (user1flag!=true) {
						appLog.error(userName1+" is not found in usernames list on manage approval page");
						sa.assertTrue(false, userName1+" is not found in usernames list on manage approval page");
					}
					if (user2flag!=true) {
						appLog.error(userName2+" is not found in usernames list on manage approval page");
						sa.assertTrue(false, userName2+" is not found in usernames list on manage approval page");
					}
					if (!np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, userName1)) {
						appLog.info("checkbox in front of "+userName1+" is unchecked as expected");
					}
					else {
						appLog.error("checkbox for "+userName1+" is checked but it should not be");
						sa.assertTrue(false, "checkbox for "+userName1+" is checked but it should not be");
					}
					if (np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, userName2)) {
						appLog.info("checkbox in front of "+userName2+" is checked as expected");
					}
					else {
						appLog.error("checkbox in front of "+userName2+" is unchecked, but it should be checked");
						sa.assertTrue(false, "checkbox in front of "+userName2+" is unchecked, but it should be checked");
					}
					appLog.info("task 2:");
					
				
						if (click(driver, np.findCheckboxForUserInManageApproval(userName1), "manage approval checkbox for "+userName1, action.BOOLEAN)) {
							appLog.info("checked checkbox for user1 "+userName1);
						}
						else {
							appLog.error("checkbox of user 1"+userName1+" manage approvals is not clickable");
							sa.assertTrue(false, "checkbox of user 1"+userName1+" manage approvals is not clickable");
						}
						if (click(driver, np.findCheckboxForUserInManageApproval(userName2), "checkbox for username 2"+userName2, action.BOOLEAN)) {
							appLog.info("unchecked checkbox for user2 "+userName2);
						}
						else {
							appLog.error("checkbox for "+userName1+" is not clickable");
							sa.assertTrue(false, "checkbox for "+userName1+" is not clickable");
						}
						if (click(driver, np.getManageApprovalSaveButton(60), "save button manage approvals page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, np.getManageApprovalActivateYesButton(60), "yes button on manage approvals activate popup", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on activate yes button");
								if (np.getEditIcon(60)!=null) {
									appLog.info("Edit icon is available");
								}
								else {
									appLog.error("Edit icon is not available on view mode manage approvals page");
									sa.assertTrue(false, "Edit icon is not available on view mode manage approvals page");
								}
								if (isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.View), "manage approvals main checkbox")) {
									appLog.info("manage approvals main checkbox is checked as expected");
								}
								else {
									appLog.error("manage approvals main checkbox is not checked, but it should be");
									sa.assertTrue(false, "manage approvals main checkbox is not checked, but it should be");
								}
								
								user1flag = false; user2flag = false;
								size = np.getUserNamesManageApproval(60,EditViewMode.View).size();
								for (int i=0;i<size;i++) {
									if (np.getUserNamesManageApproval(60,EditViewMode.View).get(i).getText().trim().equals(userName1)) {
										appLog.info("username 1 is successfully found");
										user1flag = true;
									}
									if (np.getUserNamesManageApproval(60,EditViewMode.View).get(i).getText().trim().equals(userName2)) {
										appLog.info("username 2 is successfully found");
										user2flag = true;
									}
								}
								if (user1flag!=true) {
									appLog.error(userName1+" is not found in usernames list on manage approval page");
									sa.assertTrue(false, userName1+" is not found in usernames list on manage approval page");
								}
								if (user2flag!=true) {
									appLog.error(userName2+" is not found in usernames list on manage approval page");
									sa.assertTrue(false, userName2+" is not found in usernames list on manage approval page");
								}
								System.err.println("bug");
								boolean userflag=true;
								if (!np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName2)) {
									appLog.info("checkbox in front of "+userName2+" is unchecked as expected");
								}
								else {
									userflag = false;
									appLog.error("checkbox for "+userName2+" is checked but it should not be");
									sa.assertTrue(false, "checkbox for "+userName2+" is checked but it should not be");
								}
								if (np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName1)) {
									appLog.info("checkbox in front of "+userName1+" is checked as expected");
								}
								else {
									userflag = false;
									appLog.error("checkbox in front of "+userName1+" is unchecked, but it should be checked");
									sa.assertTrue(false, "checkbox in front of "+userName1+" is unchecked, but it should be checked");
								}
								if(userflag == false) {
									appLog.info("bug found, now correcting users access to approvals");
									if (np.clickOnEditIcon()) {
										if (np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, userName2)) {
											if (click(driver, np.findCheckboxForUserInManageApproval(userName2), "manage approval checkbox for "+userName2, action.BOOLEAN)) {
												appLog.info("successully clicked on chkecbox of "+userName2);
											}
											else {
												appLog.error("checkbox for "+userName2+" is not clickable");
												sa.assertTrue(false, "checkbox for "+userName2+" is not clickable");
											}
										}
										else {
											appLog.info("checkbox of "+userName2+" was already checked");
										}
										if (click(driver, np.getManageApprovalSaveButton(60), "save button on manage approvals page", action.SCROLLANDBOOLEAN)) {
											if (click(driver, np.getManageApprovalActivateYesButton(60), "yes button on manage approvals popup", action.SCROLLANDBOOLEAN)) {
											
											}
											else {
												appLog.error("activate yes button is not clickable");
												sa.assertTrue(false, "activate yes button is not clickable");
											}
										}
										else {
											appLog.error("save button is not clickable");
											sa.assertTrue(false, "save button is not clickable");
										}
									}
									if (np.clickOnEditIcon()) {
										if (!np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, userName1)) {
											if (click(driver, np.findCheckboxForUserInManageApproval(userName1), "manage approval checkbox for "+userName1, action.BOOLEAN)) {
												appLog.info("successfully unchecked chkecbox of "+userName1);
											}
											else {
												appLog.error("checkbox for "+userName1+" is not clickable");
												sa.assertTrue(false, "checkbox for "+userName1+" is not clickable");
											}
										}
										else {
											appLog.info("checkbox of "+userName1+"already unchecked");
										}
									}
									if (click(driver, np.getManageApprovalSaveButton(60), "save button on manage approvals page", action.SCROLLANDBOOLEAN)) {
										if (click(driver, np.getManageApprovalActivateYesButton(60), "yes button on manage approvals popup", action.SCROLLANDBOOLEAN)) {
										}
										else {
											appLog.error("activate yes button is not clickable");
											sa.assertTrue(false, "activate yes button is not clickable");
										}
									}
									else {
										appLog.error("save button is not clickable");
										sa.assertTrue(false, "save button is not clickable");
									}
								}
								appLog.info("task 3:");
								/*
								if (np.clickOnEditIcon()) {
									appLog.info("clicked on edit icon");
									//now verifying UI, presence of edit icon, checked activate MA checkbox
									if (np.getEditIcon(60)!=null) {
										appLog.info("edit icon is displayed on manage approvals page");
									}
									else {
										appLog.error("edit icon is not available in view mode manage approvals page");
										sa.assertTrue(false, "edit icon is not available in view mode manage approvals page");
									}
									if (isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "manage approval main checkbox")) {
										appLog.info("manage approval main checkbox is checked as expected");
									}
									else {
										appLog.error("manage approval main checkbox should be checked, but it is unchecked");
										sa.assertTrue(false, "manage approval main checkbox should be checked, but it is unchecked");
									}



									user1flag = false; user2flag = false;
									size = np.getUserNamesManageApproval(60,EditViewMode.View).size();
									for (int i=0;i<size;i++) {
										if (np.getUserNamesManageApproval(60,EditViewMode.View).get(i).getText().trim().equals(userName1)) {
											appLog.info("username 1 is successfully found");
											user1flag = true;
										}
										if (np.getUserNamesManageApproval(60,EditViewMode.View).get(i).getText().trim().equals(userName2)) {
											appLog.info("username 2 is successfully found");
											user2flag = true;
										}
									}
									if (user1flag!=true) {
										appLog.error(userName1+" is not found in usernames list on manage approval page");
										sa.assertTrue(false, userName1+" is not found in usernames list on manage approval page");
									}
									if (user2flag!=true) {
										appLog.error(userName2+" is not found in usernames list on manage approval page");
										sa.assertTrue(false, userName2+" is not found in usernames list on manage approval page");
									}


									if (!np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName2)) {
										appLog.info("checkbox in front of "+userName2+" is unchecked as expected");
									}
									else {
										appLog.error("checkbox for "+userName2+" is checked but it should not be");
										sa.assertTrue(false, "checkbox for "+userName2+" is checked but it should not be");
									}
									if (np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName1)) {
										appLog.info("checkbox in front of "+userName1+" is checked as expected");
									}
									else {
										appLog.error("checkbox in front of "+userName1+" is unchecked, but it should be checked");
										sa.assertTrue(false, "checkbox in front of "+userName1+" is unchecked, but it should be checked");
									}


								}
								else {
									appLog.error("Edit icon is not clickable");
									sa.assertTrue(false, "Edit icon is not clickable");
								}
								*/
							}
							else {
								appLog.error("manage approval activate yes button is not clickable");
								sa.assertTrue(false, "manage approval activate yes button is not clickable");
							}
						}
						else {
							appLog.error("save button is not clickable on manage approval activation");
							sa.assertTrue(false, "save button is not clickable on manage approval activation");
						}
							
							
					
				}
				else {
					appLog.error("edit icon is not clickable");
					sa.assertTrue(false, "edit icon is not clickable");
				}
			}
			else {
				appLog.error("manage approvals side menu link is not clickable");
				sa.assertTrue(false, "manage approvals side menu link is not clickable");
			}
		}
		else {
			appLog.error("nim tab is not clickable");
			sa.assertTrue(false, "nim tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc008_VerifyUncheckingActivateManageApprovalCheckbox() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String userName1 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_Last_Name);
		String userName2 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.User_Last_Name);
		//user 2 is delegate admin
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
				appLog.info("task 1:");
				if (np.clickOnEditIcon()) {
					if (isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "activate manage approval checkbox")) {
						if (click(driver,np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "manage approval activate checkbox", action.SCROLLANDBOOLEAN)) {
							if (click(driver, np.getManageApprovalSaveButton(60), "manage approval save button", action.SCROLLANDBOOLEAN)) {
								if (click(driver, np.getManageApprovalDeactivatePopupNoButton(60), "manage approval deactivate no button", action.SCROLLANDBOOLEAN)) {
									//verify go back link, verify activate manage approval checked
									if (np.getGoBackLink(60)!=null) {
										appLog.info("go back link is available in manage approvals edit mode");
									}
									else {
										appLog.error("go back link is not available in manage approvals edit mode");
										sa.assertTrue(false, "go back link is not available in manage approvals edit mode");
									}if (isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "manage approvals activate checkbox edit mode")) {
										appLog.info("manage approvals checkbox is activated as expected");
									}
									else {
										appLog.error("manage approval checkbox is unchecked, but it shouls be activated");
										sa.assertTrue(false, "manage approval checkbox is unchecked, but it shouls be activated");
									}
									if (np.verifyPresenceOfUsersInManageApprovalsMenu(userName1, userName2, EditViewMode.Edit)) {
										appLog.info(userName1+" "+userName2+" is successfully found in users menu");
									}
									if (np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, userName1)) {
										appLog.info("user 1"+userName1+" is checked as expected");
									}
									else {
										appLog.error(userName1+" is unchecked, but it should be checked");
										sa.assertTrue(false, userName1+" is unchecked, but it should be checked");
									}
									if (!np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, userName2)) {
										appLog.info(userName2+" is unchecked as expected");
									}
									else {
										appLog.error(userName2+" is checked, but it shuld be unchecked");
										sa.assertTrue(false, userName2+" is checked, but it shuld be unchecked");
									}

									if (click(driver, np.getGoBackLink(60), "go back link on manage approvals page", action.SCROLLANDBOOLEAN)) {
										appLog.info("go back link clicked");
										if (np.getEditIcon(60)!=null) {
											appLog.info("edit icon is successfully present on view mode");
										}
										if (isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.View), "manage approvals activate checkbox")) {
											appLog.info("manage approvals checkbox is checked as expected");
										}
										else {
											appLog.error("manage approvals checkbox should be checked, but is unchecked");
											sa.assertTrue(false, "manage approvals checkbox should be checked, but is unchecked");
										}
										if (np.verifyPresenceOfUsersInManageApprovalsMenu(userName1, userName2, EditViewMode.View)) {
											appLog.info(userName1+" and "+userName2+" are successfully present");
										}

										if (np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName1)) {
											appLog.info("user 1"+userName1+" is checked as expected");
										}
										else {
											appLog.error(userName1+" is unchecked, but it should be checked");
											sa.assertTrue(false, userName1+" is unchecked, but it should be checked");
										}
										if (!np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName2)) {
											appLog.info(userName2+" is unchecked as expected");
										}
										else {
											appLog.error(userName2+" is checked, but it shuld be unchecked");
											sa.assertTrue(false, userName2+" is checked, but it shuld be unchecked");
										}
										appLog.info("task 2:");
										if (click(driver, np.getEditIcon(60), "edit icon ", action.SCROLLANDBOOLEAN)) {
											if (isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "manage approvals activate checkbox")) {
												if (click(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "manage approvals activation checkbox", action.SCROLLANDBOOLEAN)) {
													if (click(driver, np.getManageApprovalSaveButton(60), "save button", action.SCROLLANDBOOLEAN)) {
													if (click(driver,np.getCrossIconOnManageApprovalDeactivationPopup(60),"cross icon on Manage Approval Deactivation Popup", action.SCROLLANDBOOLEAN)) {
														if (click(driver, np.getGoBackLink(60), "go back link", action.SCROLLANDBOOLEAN)) {
															
														}
														//verify presence of edit icon, activate main MA checked
																if (np.getEditIcon(60)!=null) {
																appLog.info("edit icon is successfully present on manage approvals popup");
															}
															else {
																appLog.error("edit icon was not present in view mode manage approvals");
																sa.assertTrue(false, "edit icon was not present in view mode manage approvals");
															}
															if (isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.View), "manage approval activate checkbox")) {
																appLog.info("manage approvals activate checkbox is checked as expected");
															}
															else {
																appLog.error("manage approvals checkbox is unchecked, but it should be checked");
																sa.assertTrue(false, "manage approvals checkbox is unchecked, but it should be checked");
															}
															if (np.verifyPresenceOfUsersInManageApprovalsMenu(userName1, userName2, EditViewMode.View)) {
																appLog.info(userName1+" "+userName2+" is displayed in internal users table");
															}

															if (np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName1)) {
																appLog.info("user 1"+userName1+" is checked as expected");
															}
															else {
																appLog.error(userName1+" is unchecked, but it should be checked");
																sa.assertTrue(false, userName1+" is unchecked, but it should be checked");
															}
															if (!np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName2)) {
																appLog.info(userName2+" is unchecked as expected");
															}
															else {
																appLog.error(userName2+" is checked, but it shuld be unchecked");
																sa.assertTrue(false, userName2+" is checked, but it shuld be unchecked");
															}

														}
														else {
															appLog.error("cross icon on manage approvals deactivate popup is not clickable");
															sa.assertTrue(false, "cross icon on manage approvals deactivate popup is not clickable");
														}
													}
													else {
														appLog.error("manage approval save button is not clickable");
														sa.assertTrue(false, "manage approval save button is not clickable");
													}
												}
												else {
													appLog.error("manage approvals checkbox is not clickable on manage approvals page");
													sa.assertTrue(false, "manage approvals checkbox is not clickable on manage approvals page");
												}
											}
											else {
												appLog.error("activate manage approvals checkbox is not selected, so cannot continue further test");
												sa.assertTrue(false, "activate manage approvals checkbox is not selected, so cannot continue further test");
											}
										}
										else {
											appLog.error("edit icon is not clickable");
											sa.assertTrue(false, "edit icon is not clickable");
										}

										appLog.info("task 3:");
											if (click(driver, np.getEditIcon(60), "edit icon ", action.SCROLLANDBOOLEAN)) {
												if (isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "manage approvals activate checkbox")) {
													if (click(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "manage approvals activation checkbox", action.SCROLLANDBOOLEAN)) {
														if (click(driver, np.getManageApprovalSaveButton(60), "save button", action.SCROLLANDBOOLEAN)) {
															if (click(driver, np.getManageApprovalDeactivatePopupYesButton(60), "manage approvals deactivate yes button", action.SCROLLANDBOOLEAN)) {
																if (np.getEditIcon(60)!=null) {
																	appLog.info("edit icon is successfully present on manage approvals popup");
																}
																else {
																	appLog.error("edit icon was not present in view mode manage approvals");
																	sa.assertTrue(false, "edit icon was not present in view mode manage approvals");
																}
																if (!isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.View), "manage approval activate checkbox")) {
																	appLog.info("manage approvals activate checkbox is unchecked as expected");
																}
																else {
																	appLog.error("manage approvals checkbox is activated, but it should not be");
																	sa.assertTrue(false, "manage approvals checkbox is activated, but it should not be");
																}
																if (np.verifyPresenceOfUsersInManageApprovalsMenu(userName1, userName2, EditViewMode.View)) {
																	appLog.info(userName1+" "+userName2+" is displayed in internal users table");
																}

																if (np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName1)) {
																	appLog.info("user 1"+userName1+" is checked as expected");
																}
																else {
																	appLog.error(userName1+" is unchecked, but it should be checked");
																	sa.assertTrue(false, userName1+" is unchecked, but it should be checked");
																}
																if (!np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName2)) {
																	appLog.info(userName2+" is unchecked as expected");
																}
																else {
																	appLog.error(userName2+" is checked, but it shuld be unchecked");
																	sa.assertTrue(false, userName2+" is checked, but it shuld be unchecked");
																}
															}
															else {
																appLog.error("deactivate popup yes button is not clickable");
																sa.assertTrue(false, "deactivate popup yes button is not clickable");
															}
														}
														else {
															appLog.error("save button is not clickable");
															sa.assertTrue(false, "save button is not clickable");
														}
													}
													else {
														appLog.error("manage approval activate checkbox is not clickable");
														sa.assertTrue(false, "manage approval activate checkbox is not clickable");
													}
												}
												else {
													appLog.error("manage approval checkbox is not selected previously so cannot continue further test");
													sa.assertTrue(false, "manage approval checkbox is not selected previously so cannot continue further test");
												}
											}
											else {
												appLog.error("edit icon is not clickable");
												sa.assertTrue(false, "edit icon is not clickable");
											}
										
									}
									else {
										appLog.error("go back link is not clickable");
										sa.assertTrue(false, "go back link is not clickable");
									}
								}
								else {
									appLog.error("deactivate popup no button is not clickable");
									sa.assertTrue(false, "deactivate popup no button is not clickable");
								}
							}
							else {
								appLog.error("save button on manage approvals page is not clickable");
								sa.assertTrue(false,"save button on manage approvals page is not clickable");
							}
						}
						else {
							appLog.error("manage approvals activate checkbox is not clickable");
							sa.assertTrue(false, "manage approvals activate checkbox is not clickable");
						}
					}
					else {
						appLog.error("manage approvals activate checkbox is not selected, so cannot continue test");
						sa.assertTrue(false, "manage approvals activate checkbox is not selected, so cannot continue test");
					}
				}
				else {
					appLog.error("edit icon is not clickable");
					sa.assertTrue(false, "edit icon is not clickable");
				}
			}
			else {
				appLog.error("side menu manage approvals is not clickable");
				sa.assertTrue(false, "side menu manage approvals is not clickable");
			}
		}
		else {
			appLog.error("nim tab is not clickable");
			sa.assertTrue(false, "nim tab is not clickable");
		}
		switchToDefaultContent(driver);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30) == null) {
					appLog.info("manage approval icon is not present on fundraising workspace as expected");
				}
				else {
					appLog.error("mnage approval icon is still present on fundraising workspace after unchecking from nim page");
					sa.assertTrue(false, "mnage approval icon is still present on fundraising workspace after unchecking from nim page");
				}
				if (fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30) == null) {
					appLog.info("manage approval icon is not present on InvestorWorkspace as expected");
				}
				else {
					appLog.error("mnage approval icon is still present on InvestorWorkspace after unchecking from nim page");
					sa.assertTrue(false, "mnage approval icon is still present on InvestorWorkspace after unchecking from nim page");
				}
			}else {
				appLog.error("could not find "+M9FundName1);
				sa.assertTrue(false, "could not find "+M9FundName1);
			}
		}else {
			appLog.error("could not find funds tab");
			sa.assertTrue(false, "could not find funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		ThreadSleep(5000);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30) == null) {
					appLog.info("manage approval icon is not present on fundraising workspace as expected");
				}
				else {
					appLog.error("mnage approval icon is still present on fundraising workspace after unchecking from nim page");
					sa.assertTrue(false, "mnage approval icon is still present on fundraising workspace after unchecking from nim page");
				}
				if (fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30) == null) {
					appLog.info("manage approval icon is not present on InvestorWorkspace as expected");
				}
				else {
					appLog.error("mnage approval icon is still present on InvestorWorkspace after unchecking from nim page");
					sa.assertTrue(false, "mnage approval icon is still present on InvestorWorkspace after unchecking from nim page");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find "+M9FundName1);
				sa.assertTrue(false, "could not find "+M9FundName1);
			}
		}
		else {
			appLog.error("could not find funds tab");
			sa.assertTrue(false, "could not find funds tab");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc009_VerifyCheckingManageApprovalSettingAgain() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String userName1 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_Last_Name);
		String userName2 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.User_Last_Name);
		
		
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
				if (np.clickOnEditIcon()) {
					if (!isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "manage approval activate checkbox")) {
						if (click(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit),  "manage approval activate checkbox", action.SCROLLANDBOOLEAN)) {
							appLog.info("manually checked activate manage approvals checkbox");
						}
						else {
							appLog.error("manage approvals activate checkbox is not clickable");
							sa.assertTrue(false, "manage approvals activate checkbox is not clickable");
						}
					}
					else {
						appLog.error("activate manage approvals checkbox is already checked, so cannot continue test");
						sa.assertTrue(false, "activate manage approvals checkbox is already checked, so cannot continue test");
					}
					if (click(driver,np.getManageApprovalSaveButton(60), "save button on manage approvals ", action.SCROLLANDBOOLEAN)) {
						if (click(driver, np.getManageApprovalActivateYesButton(60), "manage approvals activate yes button", action.SCROLLANDBOOLEAN)) {
							//verifying result 1
							if (np.getEditIcon(60)!=null) {
								appLog.info("edit icon is successfully present in view mode");
							}
							else {
								appLog.error("edit icon is not present in view mode");
								sa.assertTrue(false, "edit icon is not present in view mode");
							}
							if (isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.View), "manage approvals activate checkbox")) {
								appLog.info("activate manage approvals checkbox is checked as expected");
							}
							else {
								appLog.error("manage approvals activate checkbox is unchecked, but it should be checked");
								sa.assertTrue(false, "manage approvals activate checkbox is unchecked, but it should be checked");
							}
							if (np.verifyPresenceOfUsersInManageApprovalsMenu(userName1, userName2, EditViewMode.View)) {
								appLog.info(userName1+" and "+userName2+" is successfully present in users table in manage aprpovals page");
							}
							if (np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName1)) {
								appLog.info(userName1+" checkbox is checked as expected");
							}
							else {
								appLog.error(userName1+" checkbox is unchecked, but it should be checked");
								sa.assertTrue(false, userName1+" checkbox is unchecked, but it should be checked");
							}
							if (!np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName2)) {
								appLog.info(userName2+" checkbox is unchecked as expected");
							}
							else {
								appLog.error(userName2+" checkbox is checked, but it should be unchecked");
								sa.assertTrue(false, userName2+" checkbox is checked, but it should be unchecked");
							}
						}
					}
					else {
						appLog.error("save button is not clickable on manage approvals page");
						sa.assertTrue(false, "save button is not clickable on manage approvals page");
					}
				}
				else {
					appLog.error("edit icon is not clickable");
					sa.assertTrue(false, "edit icon is not clickable");
				}
			}
			else {
				appLog.error("side menu manage approvals is not clickable");
				sa.assertTrue(false, "side menu manage approvals is not clickable");
			}
			switchToDefaultContent(driver);
		}
		if (np.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						sa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30)!=null) {
					appLog.info("manage approvals icon is succesfully present on fundraising workspace");
				}
				else {
					appLog.error("manage approvals icon is not present on fundraising workspace");
					sa.assertTrue(false, "manage approvals icon is not present on fundraising workspace");
				}
				if (fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30)!=null) {
					appLog.info("manage approvals icon is succesfully present on InvestorWorkspace");
				}
				else {
					appLog.error("manage approvals icon is not present on InvestorWorkspace");
					sa.assertTrue(false, "manage approvals icon is not present on InvestorWorkspace");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("fund "+M9FundName1+" is not found");
				sa.assertTrue(false, "fund "+M9FundName1+" is not found");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		np = new NIMPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						sa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30)!=null) {
					appLog.info("manage approvals icon is succesfully present on fundraising workspace");
				}
				else {
					appLog.error("manage approvals icon is not present on fundraising workspace");
					sa.assertTrue(false, "manage approvals icon is not present on fundraising workspace");
				}
				if (fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30)!=null) {
					appLog.info("manage approvals icon is succesfully present on fundraising workspace");
				}
				else {
					appLog.error("manage approvals icon is not present on fundraising workspace");
					sa.assertTrue(false, "manage approvals icon is not present on fundraising workspace");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("fund "+M9FundName1+" is not found");
				sa.assertTrue(false, "fund "+M9FundName1+" is not found");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc010_VerifyActiveInactiveAndRemovingAccessFromInternalUser() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String userName1 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_Last_Name);
		String userName2 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.User_Last_Name);
		
		
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.deactivateAndActivateCreatedUser("Active Users", CRMUser1FirstName, CRMUser1LastName, "Deactivate")) {
			appLog.info("successfully deactivated user "+CRMUser1FirstName+" "+CRMUser1LastName);
		}
		else {
			appLog.error("could not deactivate user");
			sa.assertTrue(false, "could not deactivate user");
		}
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
				if (np.verifyPresenceOfUsersInManageApprovalsMenu(userName1+" (inactive)", userName2, EditViewMode.View)) {
					appLog.info(userName1+" is present as inactive in manage approvals page");
				}
				else {
					appLog.error(userName1+" inactive is not present in manage approvals page");
					sa.assertTrue(false,userName1+" inactive is not present in manage approvals page");
				}
				if (np.clickOnEditIcon()) {
					if (np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, userName1+" (inactive)")) {
						if (click(driver, np.findCheckboxForUserInManageApproval(userName1+" (inactive)"), "checkbox of user 1 inactive", action.SCROLLANDBOOLEAN)) {
							appLog.info("manually unchecked user1  in manage approvals users table");
						}
						else {
							appLog.error("user 1 checkbox is not clickable");
							sa.assertTrue(false, "user 1 checkbox is not clickable");
						}
					}
						if (click(driver, np.getManageApprovalSaveButton(60), "save button manage aprpovals", action.SCROLLANDBOOLEAN)) {
							if (click(driver, np.getManageApprovalActivateYesButton(60), "yes button activation popup", action.SCROLLANDBOOLEAN)) {
								//verify both users unchecked
								if (!np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName1+" (inactive)")) {
									appLog.info(userName1+"inactive is not checked as expected");
								}
								else {
									appLog.error(userName1+"inactive is checked, but it should be unchecked");
									sa.assertTrue(false, userName1+"inactive is checked, but it should be unchecked");
								}

								if (!np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName2)) {
									appLog.info(userName2+" is not checked as expected");
								}
								else {
									appLog.error(userName2+" is checked, but it should be unchecked");
									sa.assertTrue(false, userName2+" is checked, but it should be unchecked");
								}

								if (np.verifyPresenceOfUsersInManageApprovalsMenu(userName1+" (inactive)", userName2, EditViewMode.View)) {
									appLog.info(userName1+" is present as inactive in manage approvals page");
								}
								else {
									appLog.error(userName1+" inactive is not present in manage approvals page");
									sa.assertTrue(false,userName1+" inactive is not present in manage approvals page");
								}
							}
							else {
								appLog.error("manage approvals activate yes button is not clickable");
								sa.assertTrue(false, "manage approvals activate yes button is not clickable");
							}
						}
						else {
							appLog.error("save button on activate is not clickable");
							sa.assertTrue(false, "save button on activate is not clickable");
						}
						if (np.clickOnSideMenusTab(sideMenu.InternalUsers)) {
							if (np.clickOnEditIcon()) {
								if (click(driver, np.findCheckboxForUserInternalUsers(userName1+"(inactive)"),"checkbox in front of "+userName1, action.BOOLEAN)) {
									if (click(driver, np.getUserPermissionRemovalPopupYesButton(60), "yes button on internal users", action.SCROLLANDBOOLEAN)) {
										if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
											int size =  np.getUserNamesManageApproval(60,EditViewMode.View).size();
											boolean flag = false;
											for (int i=0;i<size;i++) {
												if ( np.getUserNamesManageApproval(60,EditViewMode.View).get(i).getText().trim().equals(userName2)) {
													appLog.info(userName2+" is successfully found");
													flag = true;
												}
											}
											if (flag == false) {
												appLog.error(userName2+" is not found in users list of manage approvals page");
												sa.assertTrue(false, userName2+" is not found in users list of manage approvals page");
											}
											
											if (!np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName2)) {
												appLog.info("checkbox for "+userName2+" is unchecked as expected");
											}
											else {
												appLog.error("checkbox for "+userName2+" is checked, but it should be unchecked");
												sa.assertTrue(false, "checkbox for "+userName2+" is checked, but it should be unchecked");
											}
										}
										else {
											appLog.error("side menu manage approvals is not clickable");
											sa.assertTrue(false, "side menu manage approvals is not clickable");
										}
									}
									else {
										appLog.error("user permission removal popup yes button is not clickable");
										sa.assertTrue(false, "user permission removal popup yes button is not clickable");
									}
								}
								else {
									appLog.error("checkbox for user "+userName1+" is not clickable");
									sa.assertTrue(false, "checkbox for user "+userName1+" is not clickable");
								}
							}
							else {
								appLog.error("edit icon is not clickable");
								sa.assertTrue(false, "edit icon is not clickable");
							}
						}
						else {
							appLog.error("side menu internal users is not clickable");
							sa.assertTrue(false, "side menu internal users is not clickable");
						}
				}
				else {
					appLog.error("edit icon is not clickable");
					sa.assertTrue(false, "edit icon is not clickable");
				}
			}
			else {
				appLog.error("side menu manage approvals is not clickable");
				sa.assertTrue(false, "side menu manage approvals is not clickable");
			}
			switchToDefaultContent(driver);
			}
		else {
			appLog.error("nim tab is not clickable");
			sa.assertTrue(false, "nim tab is not clickable");
		}
		
		
		if (bp.deactivateAndActivateCreatedUser("All Users", CRMUser1FirstName, CRMUser1LastName, "Activate")) {
			appLog.info("successfully activated "+CRMUser1FirstName+" "+CRMUser1LastName);
		}
		else {
			appLog.error("could not reactivate first user");
			sa.assertTrue(false, "could not reactivate first user");
		}
		if (np.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.InternalUsers)) {
				if(np.clickOnEditIcon()) {
					if (click(driver, np.findCheckboxForUserInternalUsers(userName1), "checkbox for user 1"+userName1, action.SCROLLANDBOOLEAN)) {
						if (click(driver, np.getConfirmUserPermissionAdditionPopupYesButton(60), "checkbox for user 1"+userName1, action.SCROLLANDBOOLEAN)) {
							appLog.info("given ip access successfully to "+userName1);
						}
						else {
							appLog.error("confirm user permission popup yes button is not clickable");
							sa.assertTrue(false, "confirm user permission popup yes button is not clickable");
						}
					}
					else {
						appLog.error("checkbox for internal user "+userName1+" is not clickable");
						sa.assertTrue(false, "checkbox for internal user "+userName1+" is not clickable");
					}
				}
				else {
					appLog.error("edit icon is not clickable");
					sa.assertTrue(false, "edit icon is not clickable");
				}
			}
			if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
				if (np.verifyPresenceOfUsersInManageApprovalsMenu(userName1, userName2, EditViewMode.View)) {
					
				}
				if (!np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName1)) {
					appLog.info(userName1+" is not checked as expected");
				}
				else {
					appLog.error(userName1+" is checked, but it should be unchecked");
					sa.assertTrue(false, userName1+" is checked, but it should be unchecked");
				}
				if (!np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName2)) {
					appLog.info(userName2+" is not checked as expected");
				}
				else {
					appLog.error(userName2+" is checked, but it should be unchecked");
					sa.assertTrue(false, userName2+" is checked, but it should be unchecked");
				}
				if (np.clickOnEditIcon()) {
					if (click(driver, np.findCheckboxForUserInManageApproval(userName1), "checkbox for user "+userName1, action.SCROLLANDBOOLEAN)) {
						if (click(driver, np.getManageApprovalSaveButton(60),"manage approvals save button", action.SCROLLANDBOOLEAN)) {
							if (click(driver, np.getManageApprovalActivateYesButton(60), "Manage Approval Activation Yes Button", action.SCROLLANDBOOLEAN)) {
							if (np.verifyPresenceOfUsersInManageApprovalsMenu(userName1, userName2, EditViewMode.View)) {
								
							}
							if (np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName1)) {
								appLog.info(userName1+" is checked as expected");
							}
							else {
								appLog.error(userName2+" is unchecked but it should be checked");
								sa.assertTrue(false, userName2+" is unchecked but it should be checked");
							}
							if (!np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName2)) {
								appLog.info(userName2+" is not checked as expected");
							}
							else {
								appLog.error(userName2+" is checked, but it should be unchecked");
								sa.assertTrue(false, userName2+" is checked, but it should be unchecked");
							}
						}
							else {
								appLog.error("yes button on confirmation popup is not clickable");
								sa.assertTrue(false, "yes button on confirmation popup is not clickable");
							}
						}
						else {
							appLog.error("save button manage approvals page is not clickable");
							sa.assertTrue(false, "save button manage approvals page is not clickable");
						}
					}
					else {
						appLog.error("checkbox for "+userName1+" is not found(manage approvals)");
						sa.assertTrue(false, "checkbox for "+userName1+" is not found(manage approvals)");
					}
				}
				else {
					appLog.error("edit icon is not clickable");
					sa.assertTrue(false, "edit icon is not clickable");
				}
			}
			else {
				appLog.error("side menu manage approvals is not clickable");
				sa.assertTrue(false, "side menu manage approvals is not clickable");
			}
			if (np.clickOnSideMenusTab(sideMenu.InternalUsers)) {
				if (np.clickOnEditIcon()) {
					if (isSelected(driver, np.findCheckboxForUserInternalUsers(userName1), "checkbox in internal users for "+userName1)) {
						if (click(driver, np.findCheckboxForUserInternalUsers(userName1), "checkbox in internal users for "+userName1, action.SCROLLANDBOOLEAN)) {
							appLog.info("successfully unchecked "+userName1+" checkbox");
						}
						else {
							appLog.error("checkbox for "+userName1+" is not clickable");
							sa.assertTrue(false, "checkbox for "+userName1+" is not clickable");
						}
					}
					if (click(driver, np.getUserPermissionRemovalPopupYesButton(60),"save button manage approvals page", action.SCROLLANDBOOLEAN)) {
					}
					else {
						appLog.error("yes button on user permission removal popup is not clickable");
						sa.assertTrue(false, "yes button on user permission removal popup is not clickable");
					}
					np.clickOnSideMenusTab(sideMenu.InternalUsers);
				}
				else {
					appLog.error("edit icon is not clickable");
					sa.assertTrue(false, "edit icon is not clickable");
				}
				if (np.clickOnEditIcon()) {
					if (!isSelected(driver, np.findCheckboxForUserInternalUsers(userName1), "checkbox in internal users for "+userName1)) {
						if (click(driver, np.findCheckboxForUserInternalUsers(userName1), "checkbox in internal users for "+userName1, action.SCROLLANDBOOLEAN)) {
							appLog.info("successfully checked "+userName1+" checkbox");
						}
						else {
							appLog.error("checkbox for "+userName1+" is not clickable");
							sa.assertTrue(false, "checkbox for "+userName1+" is not clickable");
						}
					}
					else {
						appLog.info("checkbox for "+userName1+" is already selected");
					}
					if (click(driver, np.getConfirmUserPermissionAdditionPopupYesButton(60),"Confirm User Permission Addition Popup Yes manage approvals page", action.SCROLLANDBOOLEAN)) {
					}
					else {
						appLog.error("yes button is not clickable");
						sa.assertTrue(false, "yes button is not clickable");
					}
				}
				else {
					appLog.error("edit icon is not clickable");
					sa.assertTrue(false, "edit icon is not clickable");
				}
			}
			if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
				np.verifyPresenceOfUsersInManageApprovalsMenu(userName1, userName2, EditViewMode.View);
				
				if (!np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName1)) {
					appLog.info(userName1+" is not checked as expected");
				}
				else {
					appLog.error(userName1+" is checked, but it should be unchecked");
					sa.assertTrue(false, userName1+" is checked, but it should be unchecked");
				}
				if (!np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName2)) {
					appLog.info(userName2+" is not checked as expected");
				}
				else {
					appLog.error(userName2+" is checked, but it should be unchecked");
					sa.assertTrue(false, userName2+" is checked, but it should be unchecked");
				}
			}
			else {
				appLog.error("manage approvals side menu is not clickable");
				sa.assertTrue(false, "manage approvals side menu is not clickable");
			}
			switchToDefaultContent(driver);
		}
		if (np.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						sa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30)!=null) {
					appLog.info("successfully found manage approvals icon on FundraisingWorkspace");
				}
				else {
					appLog.error("manage approval icon is not present on fundraising workspace, but it should not be present");
					sa.assertTrue(false, "manage approval icon is not present on fundraising workspace, but it should not be present");
				}
				if (fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30)!=null) {
					appLog.info("successfully found manage approvals icon on InvestorWorkspace");
				}
				else {
					appLog.error("manage approval icon is not present on InvestorWorkspace workspace, but it should not be present");
					sa.assertTrue(false, "manage approval icon is not present on InvestorWorkspace workspace, but it should not be present");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" fund is not found");
				sa.assertTrue(false, M9FundName1+" fund is not found");
			}
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc011_VerifyUpdationOfName() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		
		String userName1 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_Last_Name);
		String user2FirstName = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.User_First_Name);
		String user2LastName = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.User_Last_Name);
		String superAdminFirstName = ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.User_First_Name);
		String superAdminLastName = ExcelUtils.readData("Users",excelLabel.Variable_Name, "AdminUser", excelLabel.User_Last_Name);
		
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (click(driver, bp.getUserMenuTab(120), "User Menu Button", action.SCROLLANDBOOLEAN)) {
			if (click(driver, bp.getUserMenuSetupLink(120), "Setup Link", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(1000);
				if (click(driver, bp.getExpandManageUserIcon(120), "Manage User Icon", action.SCROLLANDBOOLEAN)) {
					if (click(driver, bp.getUsersLink(120), "User Link", action.SCROLLANDBOOLEAN)) {
						if (selectVisibleTextFromDropDown(driver, bp.getViewAllDropdownList(120), "View dropdown list",
								"All Users")) {
							ThreadSleep(2000);
							if (click(driver, bp.getLastLogin(120), "Last Login", action.SCROLLANDBOOLEAN)) {
								if (click(driver, bp.getLastLogin(120), "Last Login", action.SCROLLANDBOOLEAN)) {
									WebElement ele = FindElement(driver,
											"//a[text()='" + CRMUser2LastName + "," + " " + CRMUser2FirstName
													+ "']/..//preceding-sibling::td//a",
											"Edit icon", action.SCROLLANDBOOLEAN, 120);
									if (ele != null) {
										if (click(driver, ele, "Edit icon", action.SCROLLANDBOOLEAN)) {
											if (sendKeys(driver, bp.getUserFirstName(60), user2FirstName+"MD", "User First Name",
													action.SCROLLANDBOOLEAN)) {
												if (sendKeys(driver, bp.getUserLastName(60), user2LastName+"MD", "User Last Name",
														action.SCROLLANDBOOLEAN)) {
													if (click(driver, bp.getSaveButton(60), "Save Button",
															action.SCROLLANDBOOLEAN)) {
														
														
														appLog.info("successfully save user 2's new name");
														ExcelUtils.writeData(user2FirstName+"MD", "Users", excelLabel.Variable_Name, "User2",
																excelLabel.User_First_Name);
														ExcelUtils.writeData(user2LastName+"MD", "Users", excelLabel.Variable_Name, "User2",
																excelLabel.User_Last_Name);
														appLog.info("written new user 2 full name to excel");
													}
													else {
														appLog.error("save button on user's edit page is not clickable");
														sa.assertTrue(false, "save button on user's edit page is not clickable");
													}
												}
												else {
													appLog.error("users last name textbox is not visible");
													sa.assertTrue(false, "users last name textbox is not visible");
												}
											}
											else {
												appLog.error("users first name textbox is not visible");
												sa.assertTrue(false, "users first name textbox is not visible");
											}
										}
										else {
											appLog.error("edit icon is not clickable");
											sa.assertTrue(false, "edit icon is not clickable");
										}
									}
									else {
										appLog.error("edit icon is not found");
										sa.assertTrue(false, "edit icon is not found");
									}
								}
								
							}
						}
						else {
							appLog.error("dropdown for user type is not visible");
							sa.assertTrue(false, "dropdown for user type is not visible");
						}
					}
					else {
						appLog.error("users link on base page is not clickable");
						sa.assertTrue(false, "users link on base page is not clickable");
					}
				}
				else {
					appLog.error("expanding manage users icon is not clickable on home page");
					sa.assertTrue(false, "expanding manage users icon is not clickable on home page");
				}
			}
			else {
				appLog.error("user menu setup link is not clickable");
				sa.assertTrue(false, "user menu setup link is not clickable");
			}
		}
		else {
			appLog.error("user menu tab is not clickable on home page");
			sa.assertTrue(false, "user menu tab is not clickable on home page");
		}
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.Profiles)) {
				if (np.clickOnEditIcon()) {
					if (sendKeys(driver, np.getMyProfileFirstName(60),user2FirstName +"UP", "super admin first name", action.BOOLEAN)) {
						if (sendKeys(driver, np.getMyProfileLastName(60),user2LastName +"UP", "super admin last name", action.BOOLEAN)) {
							if (click(driver, np.getMyProfileSaveButton(60), "my profile save button", action.SCROLLANDBOOLEAN)) {
								if (getText(driver, np.getMyProfileNameInViewMode(60), "my profile name", action.BOOLEAN).equals(user2FirstName+"UP "+user2LastName+"UP")) {
									ExcelUtils.writeData(user2FirstName+"UP", "Users", excelLabel.Variable_Name, "User2", excelLabel.MyProfile_FName);
									ExcelUtils.writeData(user2LastName+"UP", "Users", excelLabel.Variable_Name, "User2", excelLabel.MyProfile_LName);
									appLog.info("my profile name has been successfully updated from nim page");
								}
								else {
									appLog.error("profile name in view mode does not match, it is "+np.getMyProfileNameInViewMode(60).getText()+" and not "+user2FirstName+"UP "+user2LastName+"UP");
									sa.assertTrue(false, "profile name in view mode does not match, it is "+np.getMyProfileNameInViewMode(60).getText());
								}
							}
							else {
								appLog.error("save button is not clickable on my profile page");
								sa.assertTrue(false, "save button is not clickable on my profile page");
							}
						}
						else {
							appLog.error("my profile last name textbox is not clickable");
							sa.assertTrue(false, "my profile last name textbox is not clickable");
						}
					}
					else {
						appLog.error("my profile first name textbox is not visible");
						sa.assertTrue(false, "my profile first name textbox is not visible");
					}
				}
				else {
					appLog.error("edit icon is not clickable");
					sa.assertTrue(false, "edit icon is not clickable");
				}
			}
			else {
				appLog.error("my profiles link is not clickable");
				sa.assertTrue(false, "my profiles link is not clickable");
			}
			switchToDefaultContent(driver);
		}
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		np = new NIMPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if(np.clickOnSideMenusTab(sideMenu.Profiles)) {
				if (np.clickOnEditIcon()) {
					if (sendKeys(driver, np.getMyProfileFirstName(60),superAdminFirstName +"UP", "super admin first name", action.BOOLEAN)) {
						if (sendKeys(driver, np.getMyProfileLastName(60),superAdminLastName +"UP", "super admin last name", action.BOOLEAN)) {
							if (click(driver, np.getMyProfileSaveButton(60), "my profile save button", action.SCROLLANDBOOLEAN)) {
								if (getText(driver, np.getMyProfileNameInViewMode(60), "my profile name", action.BOOLEAN).equals(superAdminFirstName+"UP "+superAdminLastName+"UP")) {
									appLog.info("my profile name has been successfully for admin updated from nim page");
								}
									else {
										appLog.error("profile name in view mode does not match, it is "+np.getMyProfileNameInViewMode(60));
										sa.assertTrue(false, "profile name in view mode does not match, it is "+np.getMyProfileNameInViewMode(60));
									}
								}
								else {
									appLog.error("save button is not clickable on my profile page");
									sa.assertTrue(false, "save button is not clickable on my profile page");
								}
							}
							else {
								appLog.error("my profile last name textbox is not clickable");
								sa.assertTrue(false, "my profile last name textbox is not clickable");
							}
						}
						else {
							appLog.error("my profile first name textbox is not visible");
							sa.assertTrue(false, "my profile first name textbox is not visible");
						}
					}
					else {
						appLog.error("edit icon is not clickable");
						sa.assertTrue(false, "edit icon is not clickable");
					}
			}
			else {
				appLog.error("nim tab is not clickable");
				sa.assertTrue(false, "nim tab is not clickable");
			}
			if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
				if (np.verifyPresenceOfUsersInManageApprovalsMenu(userName1, user2FirstName+"MD "+ user2LastName+"MD", EditViewMode.View)) {
					appLog.info("user 2 new name has been successfuly verified");
				}
				if (getText(driver, np.getRowsCRMAdminManageApproval(60), "admin name on manage approvals page", action.SCROLLANDBOOLEAN).equals(superAdminFirstName+"UP "+superAdminLastName+"UP")) {
					appLog.info("admin name has been successfully updated");
				}
			}
			switchToDefaultContent(driver);
		}
		lp.CRMlogout();
		sa.assertAll();
											
	}
	
	@Test
	public void M9tc012_GiveManageApprovalAccessToUser1AndUser2() {
		//register for nim close button
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		
		String userName1 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_Last_Name);
		String user2UpdatedFullName = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.User_Last_Name);
		String user2LastName = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.User_Last_Name);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver,30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
				if (np.clickOnEditIcon()) {
					if (!np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, userName1)) {
						if (click(driver, np.findCheckboxForUserInManageApproval(userName1), "checkbox for "+userName1, action.SCROLLANDBOOLEAN)) {
							appLog.info("checkbox for "+userName1+" is now ticked");
						}
						else {
							appLog.error("checkbox of "+userName1+" is not clickable");
							sa.assertTrue(false, "checkbox of "+userName1+" is not clickable");
						}
					}
					click(driver, np.getUserHeadsManageApproval(30, EditViewMode.Edit).get(0), "user name", action.BOOLEAN);
					ThreadSleep(3000);
					click(driver, np.getUserHeadsManageApproval(30, EditViewMode.Edit).get(0), "user name", action.BOOLEAN);
					ThreadSleep(3000);
					if (!np.verifyCheckedOrNotManageApproval(EditViewMode.Edit, user2UpdatedFullName)) {
						if (click(driver, np.findCheckboxForUserInManageApproval(user2UpdatedFullName), "checkbox for manage approval in front of user 2 "+user2UpdatedFullName, action.SCROLLANDBOOLEAN)) {
							appLog.info("checkbox for "+user2LastName+" is now ticked");
						}
						else {
							appLog.error("checkbox of "+user2LastName+" is not clickable");
							sa.assertTrue(false, "checkbox of "+user2LastName+" is not clickable");
						}
					}
					if (click(driver, np.getManageApprovalSaveButton(60), "save button on manage approvals page", action.SCROLLANDBOOLEAN)) {
						appLog.info("save button is clicked after providing manage approvals permission to user1 and user2");
						if (click(driver, np.getManageApprovalActivateYesButton(60), "confirm popup yes button", action.SCROLLANDBOOLEAN)) {
							if (np.verifyCheckedOrNotManageApproval(EditViewMode.View, userName1)) {
								appLog.info(userName1+" is verified for manage approvals permission succesfully");
							}
							else {
								appLog.error("checkbox for "+userName1+" is not checked, but it should be checked");
								sa.assertTrue(false, "checkbox for "+userName1+" is not checked, but it should be checked");
							}
							if (np.verifyCheckedOrNotManageApproval(EditViewMode.View, user2UpdatedFullName)) {
								appLog.info(user2UpdatedFullName+" is verified for manage approvals permission succesfully");
							}
							else {
								appLog.error("checkbox for "+user2UpdatedFullName+" is not checked, but it should be checked");
								sa.assertTrue(false, "checkbox for "+user2UpdatedFullName+" is not checked, but it should be checked");
							}
						}
						else {
							appLog.error("yes button in confirm popup is not clickable");
							sa.assertTrue(false, "yes button in confirm popup is not clickable");
						}
					}
					else {
						appLog.error("manage approval save button is not clickable");
						sa.assertTrue(false, "manage approval save button is not clickable");
					}
				}
				else {
					appLog.error("edit icon is not clickable on manage approvals page");
					sa.assertTrue(false, "edit icon is not clickable on manage approvals page");
				}
			}
			else {
				appLog.error("side menu manage approvals is not clickable");
				sa.assertTrue(false, "side menu manage approvals is not clickable");
			}
			switchToDefaultContent(driver);
		}
		else {
			appLog.error("nim tab is not clickable");
			sa.assertTrue(false, "nim tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M9tc013_ManageApprovalPopUpFundsPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		String break_separated = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.KeyWord_For_Search);
		String[] columnNames = break_separated.split("<break>");
		if (bp.clickOnTab(TabName.NIMTab)) {
			if (np.findRegistrationSuccessfulPopup()) {
				appLog.info("registration complete for users 2nd time access");
			}
			else {
				appLog.error("could not registered user as registration successful popup is not visible");
			}
		}
		else {
			appLog.error("nim tab is not clickable");
			sa.assertTrue(false, "nim tab is not clickable");
		}
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						sa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon on fund/workspace", action.SCROLLANDBOOLEAN)) {
					//verify manage approvals ui
					if (fp.getManageAppHeadText(60).getText().trim().equals("Manage Approvals")) {
						appLog.info("manage approvals popup is successfully opened");
					}
					else {
						appLog.error("manage approvals popup cannot be verified as opened");
						sa.assertTrue(false, "manage approvals popup cannot be verified as opened");
					}
					if (fp.getPendingDocsTab(60)!=null) {
						appLog.info("pending docs tab is present on manage app popup");
					}
					else {
						appLog.error("pending docs tab is not present on manage approvals popup");
						sa.assertTrue(false, "pending docs tab is not present on manage approvals popup");
					}
					if (fp.getApprovedDocsTab(60)!=null) {
						appLog.info("approved docs tab is present on manage app popup");
					}else {
						appLog.error("approved docs tab is not present on manage approvals popup");
						sa.assertTrue(false, "approved docs tab is not present on manage approvals popup");
					
					}
					if (fp.getPendingDocsTab(60).getAttribute("class").contains("selected")) {
						appLog.info("pending docs tab is the default selected tab");
					}
					else {
						appLog.error("pending docs tab is not the selected tab");
						sa.assertTrue(false, "pending docs tab is not the selected tab");
					}
					List<WebElement> viewDropContents = allOptionsInDropDrop(driver, fp.getManageAppPendingDropdown(60), "manage approvals view dropdown");
					
					
					if (viewDropContents.size() == 1) {
						appLog.info("view dropdown has size of 1");
						if (viewDropContents.get(0).getText().trim().equals("All Pending Documents")) {
							appLog.info("all pending documents is successfully verified on view dropdown manage approvals");
						}
						else {
							appLog.error("the option in dropdown is not 'all pending docs'");
							sa.assertTrue(false, "the option in dropdown is not 'all pending docs'");
						}
					}
					else {
						appLog.error("the size of dropdown is not 1, it is "+viewDropContents.size());
						sa.assertTrue(false, "the size of dropdown is not 1, it is "+viewDropContents.size());
					}
					if (fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 60)!=null) {
						appLog.info("search textbox is succesfully present on manage approvals window");
					}
					
					else {
						appLog.error("search textbox is not present on manage approvals(pending documents)");
						sa.assertTrue(false, "search textbox is not present on manage approvals(pending documents)");
					}
					if (fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 60)!=null) {
						appLog.info("manage approvals popup search icon is successfully found");
					}
					else {
						appLog.error("search icon is not present on manage approvals(pending documents)");
						sa.assertTrue(false, "search icon is not present on manage approvals(pending documents)");
					}
					List<WebElement> temp_list = fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments,60);
					for (int i = 1;i<temp_list.size();i++) {
						if (!temp_list.get(i).getText().trim().equals(columnNames[i-1])) {
							appLog.error(temp_list.get(i).getText()+" does not match with "+columnNames[i-1]);
							sa.assertTrue(false,temp_list.get(i).getText()+" does not match with "+columnNames[i-1] );
						}
						else {
							appLog.info(temp_list.get(i).getText()+" matches with "+columnNames[i-1]);
						}
					}
					if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
						appLog.info("no data to display error message is successfully verified");
					}
					else {
						appLog.error("no data to display message is not present");
						sa.assertTrue(false, "no data to display message is not present");
					}
					if (fp.getApproveBtnManageApprovals(60)!=null) {
						appLog.info("approve button manage approvals is successfully verified");
					}
					else {
						appLog.error("approve button is not present on manage approvals window");
						sa.assertTrue(false, "approve button is not present on manage approvals window");
					}
					if (fp.getdeleteBtnManageApprovals(60)!=null) {
						appLog.info("delete button manage approvals is successfully verified");
					}
					else {
						appLog.error("delete button is not present on manage approvals window");
						sa.assertTrue(false, "delete button is not present on manage approvals window");
					}
					if (fp.getManageAppCancelBtn(60)!=null) {
						appLog.info("cancel button manage approvals is successfully verified");
					}
					else {
						appLog.error("cancel button not present on manage approvals window");
						sa.assertTrue(false, "cancel button not present on manage approvals window");
					}
					WebElement ele= BaseLib.edriver.findElement(By.cssSelector(".SearchBasedOnAccountAndContacts .icon_btn_search"));
					try{
						scrollDownThroughWebelement(driver, ele, "search icon");
						ele.click();
						appLog.info("clicked on search icon");
					}catch(Exception e){
						appLog.error("Not able to click on search icon");
						BaseLib.sa.assertTrue(false, "Not able to click on search icon");
					}
						ThreadSleep(3000);
						if (isAlertPresent(driver)) {
							String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
							switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
							if (msg.trim().equals(FundsPageErrorMessage.pleaseEnterAValueErrorMessageInManageEmails)) {
								appLog.info("correct error message is verified when search icon is clicked");
							}
							else {
								appLog.error("error alert message is wrong when search icon is clicked");
								sa.assertTrue(false, "error alert message is wrong when search icon is clicked");
							}
						}
						else {
							appLog.error("no alert is present when search icon is clicked");
							sa.assertTrue(false, "no alert is present when search icon is clicked");
						}
					if (click(driver, fp.getCheckAllDocsManageApprovals(60), "checkbox to select all documents in manage approvals", action.SCROLLANDBOOLEAN)) {
						//added after bug found
						ele= BaseLib.edriver.findElement(By.cssSelector("#ApproveId"));
						try{
							scrollDownThroughWebelement(driver, ele, "Approve icon");
							ele.click();
							appLog.info("clicked on Approve icon");
						}catch(Exception e){
							appLog.error("Not able to click on Approve icon");
							BaseLib.sa.assertTrue(false, "Not able to click on Approve icon");
						}
						
						//click(driver, fp.getManageApprovalsApproveBtn(30), "approve btn", action.SCROLLANDBOOLEAN);
						if (isAlertPresent(driver)) {
							String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
							switchToAlertAndAcceptOrDecline(driver, 30,action.ACCEPT);
							if (msg.trim().equals(FundsPageErrorMessage.manageApprovalNoDocError)) {
								appLog.info("correct error message is verified when checkbox is ticked with no documents manage approvals window");
							}
							else {
								appLog.error("error message for please select a doc to approve is not found");
								sa.assertTrue(false, "error message for please select a doc to approve is not found");
							}
						}
						if (isSelected(driver, fp.getCheckAllDocsManageApprovals(60), "checkbox for all documents manage approval")) {
							appLog.info("checkbox for selecting all documents is selected");
						}
						else {
							appLog.error("checkbox for selecting all documents is not selected");
							sa.assertTrue(false, "checkbox for selecting all documents is not selected");
						}
					}
					else {
						appLog.error("checkbox for selecting all documents is not clickable");
						sa.assertTrue(false, "checkbox for selecting all documents is not clickable");
					}
					boolean flag;
					flag=true;
					ele= BaseLib.edriver.findElement(By.cssSelector("#DeleteId"));
					try{
						scrollDownThroughWebelement(driver, ele, "Delete icon");
						ele.click();
						appLog.info("clicked on Delete icon");
					}catch(Exception e){
						flag=false;
						appLog.error("Not able to click on Delete icon");
						BaseLib.sa.assertTrue(false, "Not able to click on Delete icon");
					}
					if (flag) {
						if (isAlertPresent(driver)) {
							String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
							switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
							if (msg.trim().equals(FundsPageErrorMessage.manageApprovalDelError)) {
								appLog.info("correct error mesage is verified when delete button is clicked with no document");
							}
							else {
								appLog.error("error message for please select a doc to del is not found");
								sa.assertTrue(false, "error message for please select a doc to del is not found");
							}
						}
						if (isSelected(driver, fp.getCheckAllDocsManageApprovals(60), "checkbox for all documents manage approval")) {
							appLog.info("checkbox for selecting all documents is selected");
						}
						else {
							appLog.error("checkbox for selecting all documents is not selected");
							sa.assertTrue(false, "checkbox for selecting all documents is not selected");
						}
					}
					else {
						appLog.error("delete button is not clickable on manage approvals window");
						sa.assertTrue(false, "delete button is not clickable on manage approvals window");
					}
					
					if (click(driver, fp.getManageAppCancelBtn(60), "cancel button manage approvals", action.SCROLLANDBOOLEAN)) {
					}
					else {
						appLog.error("cancel button is not clickable on manage approvals window");
						sa.assertTrue(false, "cancel button is not clickable on manage approvals window");
					}
					if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30),"manage approval icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
						if (!isSelected(driver, fp.getCheckAllDocsManageApprovals(60), "checkbox for all documents manage approvals")) {
							appLog.info("checkbox for selecting all documents is not selected");
						}
						else {
							appLog.error("checkbox for selecting all documents is selected, but it should not be");
							sa.assertTrue(false, "checkbox for selecting all documents is selected, but it should not be");
						}
						if (click(driver, fp.getApprovedDocsTab(60), "approved docs tab manage approvals", action.SCROLLANDBOOLEAN)) {
							viewDropContents = allOptionsInDropDrop(driver, fp.getManageAppovalsApprovedDocsDropdown(60), "manage approvals view dropdown");
							
							
							if (viewDropContents.size() == 1) {
								appLog.info("view dropdown has size of 1");
								if (viewDropContents.get(0).getText().trim().equals("All Approved Documents")) {
									appLog.info("all pending documents is successfully verified on view dropdown manage approvals");
								}
								else {
									appLog.error("'all approved documents' is not present in manage approvals dropdown");
									sa.assertTrue(false, "'all approved documents' is not present in manage approvals dropdown");
								}
							}
							else {
								appLog.error("dropdown for all approved documents does not contain 1 element, it has "+viewDropContents.size());
								sa.assertTrue(false, "dropdown for all approved documents does not contain 1 element, it has "+viewDropContents.size());
							}
							if (fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 30)!=null) {
								appLog.info("approved documents search textbox is visible on manage approvals popup");
							}
							else {
								appLog.error("search box is not present on approved documents tab");
								sa.assertTrue(false, "search box is not present on approved documents tab");
							}
							if (fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 30)!=null) {
								appLog.info("approved documents search box is successfully visible on manage approvals popup");
							}
							else {
								appLog.error("search icon is not present on approved documents tab");
								sa.assertTrue(false, "search icon is not present on approved documents tab");
							}
							temp_list = fp.getColumnListManageApproval(ManageApprovalTabs.ApprovedDocuments,60);
							for (int i = 0;i<temp_list.size();i++) {
								if (!temp_list.get(i).getText().trim().equals(columnNames[i])) {
									appLog.error(temp_list.get(i).getText()+" does not match with "+columnNames[i]);
									sa.assertTrue(false,temp_list.get(i).getText()+" does not match with "+columnNames[i] );
								}
								else {
									appLog.info(temp_list.get(i).getText()+" matches with "+columnNames[i]);
								}
							}
							if (fp.noDataToDisplay(ManageApprovalTabs.ApprovedDocuments, 60)!=null) {
								appLog.info("no data to display error message is successfully verified");
							}
							else {
								appLog.error("no data to display message is not present");
								sa.assertTrue(false, "no data to display message is not present");
							}
							
							if (fp.getCloseBtnApprovedDocs(60)!=null) {
								appLog.info("close button is successfully verified on manage approval window");
							}
							else {
								appLog.error("close button is not present on approved documents window");
								sa.assertTrue(false, "close button is not present on approved documents window");
							}
							//now checking functionality of close button
							if (click(driver, fp.getCloseBtnApprovedDocs(60),"close button manage approval window" , action.SCROLLANDBOOLEAN)) {
								if (click(driver,fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30),"manage approval icon fund/workspace",action.SCROLLANDBOOLEAN)) {
									if (fp.getPendingDocsTab(30).getAttribute("class").contains("selected")) {
										appLog.info("pending docs is successfully selected");
									}
									else {
										appLog.error("pending docs is not the selected tab");
										sa.assertTrue(false, "close button is not present on approved documents window");
									}
									if (click(driver, fp.getApprovedDocsTab(60), "approved docs tab", action.BOOLEAN)) {
										if (fp.getApprovedDocsTab(60).getAttribute("class").contains("selected")) {
											appLog.info("approved docs is verified as the seelcted tab");
										}
										else {
											appLog.error("approved docs is not the selected tab");
											sa.assertTrue(false, "approved docs is not the selected tab");
										}
										if (click(driver, fp.getCrossIconManageApp(60), "cross icon manage approval", action.SCROLLANDBOOLEAN)) {
											appLog.info("close manage approvals window");
										}
										else {
											appLog.error("close button is not present on approved documents window");
											sa.assertTrue(false, "close button is not present on approved documents window");
										}
											
									}
									else {
										appLog.error("approved docs tab is not clicakble");
										sa.assertTrue(false, "approved docs tab is not clicakble");
									}
								}
								else {
									appLog.error("manage approval icon is not clickable on fundraisign workspace");
									sa.assertTrue(false, "manage approval icon is not clickable on fundraisign workspace");
								}
								}
							else {
								appLog.error("close button is not clickable");
								sa.assertTrue(false, "close button is not clickable");
							}
							}
						else {
							appLog.error("approved docs tab is not clickable");
							sa.assertTrue(false, "approved docs tab is not clickable");
						}
						}
					else {
						appLog.error("manage approval icon is not clickable on fundraising workspace");
						sa.assertTrue(false, "manage approval icon is not clickable on fundraising workspace");
					}
					}
				else {
					appLog.error("manage approval icon is not clickable on fundraising workspace");
					sa.assertTrue(false, "manage approval icon is not clickable on fundraising workspace");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("fund "+M9FundName1+" is not clickable");
				sa.assertTrue(false, "fund "+M9FundName1+" is not clickable");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M9tc014_InviteContactAndRegister() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String reg_link=null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				String expandIcon = getAttribute(driver, bp.getWorkspaceExpandIcon(60), "WorkspaceIcon", "title");
				if (expandIcon.equalsIgnoreCase("Show Section - Workspace")) {
					if (click(driver, bp.getWorkspaceExpandIcon(60), "Workspace Expand Icon",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on workspace expand icon");
					} else {
						appLog.info("Not able to click on workspace expand icon");
						sa.assertTrue(false, "Not able to click on workspace expand icon");
					}
				} else {
					appLog.info("Workspace is in expanded form");
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.inviteContact(environment, mode,M9Institution1, M9Contact1EmailId, null, FolderType.Standard, "Upload", "Yes", "Yes", M9Institution1, Workspace.FundraisingWorkspace, null)) {
					appLog.info("invited contact "+M9Contact1FirstName+" "+M9Contact1LastName+" for "+M9Institution1);
				}
				else {
					appLog.error("could not invite contact "+M9Contact1FirstName+" for std folder for "+M9Institution1);
					sa.assertTrue(false, "could not invite contact "+M9Contact1FirstName+" for std folder for "+M9Institution1);
				}
				if (fp.inviteContact(environment, mode,M9Institution2, M9Contact1EmailId, null, FolderType.Standard, "Upload", "Yes", "No", M9Institution2, Workspace.FundraisingWorkspace, M9Contact1EmailId)) {
					appLog.info("invited contact "+M9Contact1FirstName+" "+M9Contact1LastName+" for "+M9Institution2);
				}
				else {
					appLog.error("could not invite contact "+M9Contact1FirstName+" for std folder for "+M9Institution2);
					sa.assertTrue(false, "could not invite contact "+M9Contact1FirstName+" for std folder for "+M9Institution2);
				}
				if (fp.inviteContact(environment, mode,null, M9Contact1EmailId, shdPath, FolderType.Shared, "download", "Yes", "No", "Shared", Workspace.FundraisingWorkspace, M9Contact1EmailId)) {
					appLog.info("contact has been invited to shared folder");
				}
				else {
					appLog.error("could not invite contact "+M9Contact1FirstName+" for shared folder");
					sa.assertTrue(false, "could not invite contact "+M9Contact1FirstName+" for std folder");
				}
				try {
					reg_link = new EmailLib().getInvestorRegLink("InvitationMail", gmailUserName, adminPassword, CRMUser1EmailID, M9Contact1EmailId);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
					appLog.error("investor registration url was not found on mail");
					sa.assertTrue(false, "investor registration url was not found on mail");
				}
				
				switchToDefaultContent(driver);
			}
		}
		lp.CRMlogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		bp = new BasePageBusinessLayer(driver);
		if (reg_link!=null) {
		driver.get(reg_link);
		}
		else if (reg_link == null) {
			driver.get(InvestorRegistrationURL);
			appLog.error("as reg link is null, opening with link on credential properties file");
		}
		if (bp.targetRegistration(M9Contact1FirstName, M9Contact1LastName, M9Contact1EmailId,Org1FirmName, adminPassword)) {
			appLog.info("registration for "+M9Contact1FirstName+" is done successfully");
		}
		else {
			appLog.error("registration could not be done for "+M9Contact1EmailId);
			sa.assertTrue(false, "registration could not be done for "+M9Contact1EmailId);
		}
		sa.assertAll();
		}
	
	@Test
	public void M9tc015_UploadManageApprovalOn_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String intPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		
		String[] filesCommon = {};
		String[] filesInternal = {};
		String[] filesShared = {};
		String[] filesStand = {};
		String date = getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				String docpath = "UploadFiles\\Module9\\";
				//common
				if (fp.uploadFile(cmnPath, null, docpath+cmnPath, null, UploadFileActions.Upload,
						Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfully");
					filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon).split("<break>");
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesCommon[0],false )) {
						appLog.info("filename and status is verified successfully files present in common folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesCommon[0]);
						sa.assertTrue(false, "filename and status is not verified for "+filesCommon[0]);
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesCommon[1] ,false)) {
						appLog.info("filename and status is verified successfully files present in common folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesCommon[1]);
						sa.assertTrue(false, "filename and status is not verified for "+filesCommon[1]);
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesCommon[2] ,false)) {
						appLog.info("filename and status is verified successfully files present in common folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesCommon[2]);
						sa.assertTrue(false, "filename and status is not verified for "+filesCommon[2]);
					}
					if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
						
					}
					if (fp.verifyManageApprovalsDropdownContents(cmnPath, ManageApprovalTabs.PendingDocuments) ) {
						appLog.info("dropdown contents are successfully verified");
					}
					else {
						appLog.error("dropdown is not having correct elements for common folder");
						sa.assertTrue(false, "dropdown is not having correct elements for common folder");
					}
					if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,filesCommon, M9FundName1+" > "+cmnPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
						appLog.info("successfully verified file "+filesCommon+" on manage approvals pending docs");
					}
					else {
						appLog.error("could not verify "+filesCommon+" on manage approvals pending docs");
						sa.assertTrue(false, "could not verify "+filesCommon+" on manage approvals pending docs");
					}
					click(driver, fp.getManageAppCancelBtn(60), "cancel button manage approvals", action.SCROLLANDBOOLEAN);
					switchToDefaultContent(driver);
				}
				
				//internal
				if (fp.uploadFile(intPath, null, docpath+intPath, null, UploadFileActions.Upload,
						Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					filesInternal = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal).split("<break>");
					
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesInternal[0] ,true)) {
						appLog.info("filename and status is verified "+filesInternal[0]+"present in Internal folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesInternal[0]);
						sa.assertTrue(false, "filename and status is not verified for "+filesInternal[0]);
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesInternal[1] ,true)) {
						appLog.info("filename and status is verified  "+filesInternal[1]+"present in Internal folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesInternal[1]);
						sa.assertTrue(false, "filename and status is not verified for "+filesInternal[1]);
					}
					if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
						
					}
					
					if (fp.verifyManageApprovalsDropdownContents(intPath,ManageApprovalTabs.PendingDocuments) ) {
						appLog.info("dropdown contents are successfully verified");
					}
					else {
						appLog.error("dropdown is not having correct elements for Internal folder");
						sa.assertTrue(false, "dropdown is not having correct elements for Internal folder");
					}
					if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
						appLog.info("no data to display is successfully verified for internl folder");
					}
					else {
						appLog.error("no data to display is not present on manage approvals window");
						sa.assertTrue(false, "no data to display is not present on manage approvals window");
					}
					click(driver, fp.getManageAppCancelBtn(60), "cancel button manage approvals", action.SCROLLANDBOOLEAN);
					switchToDefaultContent(driver);
				}
				
				
				//shared
				if (fp.uploadFile(shdPath, null, docpath+shdPath, null, UploadFileActions.Upload,
						Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared).split("<break>");
					
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesShared[0],false )) {
						appLog.info("filename and status is verified "+filesShared[0]+" successfully files present in Shared folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesShared[0]);
						sa.assertTrue(false, "filename and status is not verified for "+filesShared[0]);
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesShared[1] ,false)) {
						appLog.info("filename and status is verified "+filesShared[1]+" successfully files present in Shared folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesShared[1]);
						sa.assertTrue(false, "filename and status is not verified for "+filesShared[1]);
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesShared[2],false )) {
						appLog.info("filename and status is verified "+filesShared[2]+" successfully files present in Shared folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesShared[2]);
						sa.assertTrue(false, "filename and status is not verified for "+filesShared[2]);
					}
					if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
						
					}
					if (fp.verifyManageApprovalsDropdownContents(shdPath,ManageApprovalTabs.PendingDocuments) ) {
						appLog.info("dropdown contents are successfully verified");
					}
					else {
						appLog.error("dropdown is not having correct elements for Shared folder");
						sa.assertTrue(false, "dropdown is not having correct elements for Shared folder");
					}
					if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,filesShared, M9FundName1+" > "+shdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
						appLog.info("successfully verified manage approvals for "+filesShared);
					}
					else {
						appLog.error("could not verify manage approvals pending docs for "+filesShared);
						sa.assertTrue(false, "could not verify manage approvals pending docs for "+filesShared);
					}
					click(driver, fp.getManageAppCancelBtn(60), "cancel button manage approvals", action.SCROLLANDBOOLEAN);
					switchToDefaultContent(driver);
				}
				//standard for inst 1
				if (fp.uploadFile(stdPath, M9Institution1+"<break>"+M9Institution2, docpath+stdPath, null, UploadFileActions.Upload,
						Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					filesStand = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard).split("<break>");
					
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesStand[0] ,false)) {
						appLog.info("filename and status is verified "+filesStand[0]+"successfully files present in Standard folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesStand[0]);
						sa.assertTrue(false, "filename and status is not verified for "+filesStand[0]);
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesStand[1] ,false)) {
						appLog.info("filename and status is verified "+filesStand[1]+"successfully files present in Standard folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesStand[1]);
						sa.assertTrue(false, "filename and status is not verified for "+filesStand[1]);
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesStand[2],false )) {
						appLog.info("filename and status is verified "+filesStand[2]+"successfully files present in Standard folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesStand[2]);
						sa.assertTrue(false, "filename and status is not verified for "+filesStand[2]);
					}
					if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
						
					}
					if (fp.verifyManageApprovalsDropdownContents(stdPath,ManageApprovalTabs.PendingDocuments) ) {
						appLog.info("dropdown contents are successfully verified");
					}
					else {
						appLog.error("dropdown is not having correct elements for Standard folder");
						sa.assertTrue(false, "dropdown is not having correct elements for Standard folder");
					}
					if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace,ManageApprovalTabs.PendingDocuments, filesStand, M9FundName1+" > "+M9Institution1+" > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
						appLog.info("successfully verified manage approvals pending docs "+filesStand);
					}
					else {
						appLog.error("could not verify manage approvals pending docs "+filesStand);
						sa.assertTrue(false, "could not verify manage approvals pending docs "+filesStand);
					}
					//selecting all pending docs
					if (selectVisibleTextFromDropDown(driver,  fp.getManageApprovalsDropdown(ManageApprovalTabs.PendingDocuments, 30), "manage approval dropdown","All Pending Documents")) {
						ThreadSleep(3000);
						for (int i = 0;i<3;i++) {
						if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, filesStand[i], M9FundName1+" > "+M9Institution1+" > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
							appLog.info("successfully verified file "+filesStand[i] + " for "+M9Institution1);
						}
						else {
							click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments,60), "document name manage approvals", action.BOOLEAN);
							if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, filesStand[i], M9FundName1+" > "+M9Institution1+" > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info("successfully verified file "+filesStand[i] + " for "+M9Institution1);
							}
							else {
							appLog.error(filesStand[i]+" could not be verified for "+M9Institution1);
							sa.assertTrue(false, filesStand[i]+" could not be verified for "+M9Institution1);
							}
						}
						if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, filesStand[i], M9FundName1+" > "+M9Institution2+" > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
							appLog.info("successfully verified file "+filesStand[i] + " for "+M9Institution2);
						}
						else {
							click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments,60), "document name manage approvals", action.BOOLEAN);
							if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, filesStand[i], M9FundName1+" > "+M9Institution2+" > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info("successfully verified file "+filesStand[i] + " for "+M9Institution2);
							}
							else {
							appLog.error(filesStand[i]+" could not be verified for "+M9Institution2);
							sa.assertTrue(false, filesStand[i]+" could not be verified for "+M9Institution2);
							}
						}
							
						}
						for (int i = 0;i<3;i++) {
							if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, filesCommon[i], M9FundName1+" > "+cmnPath,"pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info("successfully verified file "+filesCommon[i]);
							}
							else {
								click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments,60), "document name manage approvals", action.BOOLEAN);
								if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, filesCommon[i], M9FundName1+" > "+cmnPath,"pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
									appLog.info("successfully verified file "+filesCommon[i]);
								}
								else {
								appLog.error(filesCommon[i]+" could not be verified");
								sa.assertTrue(false, filesCommon[i]+" could not be verified");
								}
							}
						}
						for (int i = 0;i<3;i++) {
							if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, filesShared[i], M9FundName1+" > "+shdPath,"pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info("successfully verified file "+filesShared[i]);
							}
							else {
								click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments,60), "document name manage approvals", action.BOOLEAN);
								if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, filesShared[i], M9FundName1+" > "+shdPath,"pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
									appLog.info("successfully verified file "+filesShared[i]);
								}
								else {
								appLog.error(filesShared[i]+" could not be verified");
								sa.assertTrue(false, filesShared[i]+" could not be verified");
								}
							}
						}
					}
					else {
						appLog.error("all pending documents is not present in manage approvals window");
						sa.assertTrue(false, "all pending documents is not present in manage approvals window");
					}
					click(driver, fp.getManageAppCancelBtn(60), "cancel button manage approvals", action.SCROLLANDBOOLEAN);
					//for inst 2
					if (fp.verifyFolderPathdummy(stdPath, M9Institution2, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesStand[0] ,false)) {
							appLog.info("filename and status is verified "+filesStand[0]+"successfully files present in Standard folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[0]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[0]);
						}
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesStand[1] ,false)) {
							appLog.info("filename and status is verified "+filesStand[1]+"successfully files present in Standard folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[1]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[1]);
						}
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesStand[2],false )) {
							appLog.info("filename and status is verified "+filesStand[2]+"successfully files present in Standard folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[2]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[2]);
						}
					}
					else {
						appLog.error(stdPath+" is not found in funds page");
						sa.assertTrue(false, stdPath+" is not found in funds page");
					}
					switchToDefaultContent(driver);
				}
				else {
					appLog.error("file "+docpath+" could not be uploaded");
					sa.assertTrue(false, "file "+docpath+" could not be uploaded");
				}
				
				}
			else {
				appLog.error("fund "+M9FundName1+" could not be found");
				sa.assertTrue(false, "fund "+M9FundName1+" could not be found");
			}
		}	

		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M9tc015_UploadManageApprovalOn_ImpactCRM() {
	LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
	BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
	FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
	InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
	ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
	String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.StandardPath);
	String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.SharedPath);
	String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.CommonPath);
	String intPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.InternalPath);
	
	String[] filesCommon  = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.UploadedFileCommon).split("<break>");
	
	String[] filesInternal= ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.UploadedFileInternal).split("<break>");
	
	String[] filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.UploadedFileShared).split("<break>");
	
	String[] filesStand = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.UploadedFileStandard).split("<break>");
	
	lp.CRMLogin(CRMUser1EmailID, adminPassword);
	if (fp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M9Institution1)) {
				switchToFrame(driver, 30, ip.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesCommon[0], false)) {
						appLog.info("filename and status is correct for "+filesCommon[0]);
					}
					else {
						appLog.error("filename and status is not verified for "+filesCommon[0]);
						sa.assertTrue(false, "filename and status is not verified for "+filesCommon[0]);
					}
					if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace,filesCommon[1] ,false)) {
						appLog.info("filename and status is verified successfully files present in common folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesCommon[1]);
						sa.assertTrue(false, "filename and status is not verified for "+filesCommon[1]);
					}
					if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace,filesCommon[2] ,false)) {
						appLog.info("filename and status is verified successfully files present in common folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesCommon[2]);
						sa.assertTrue(false, "filename and status is not verified for "+filesCommon[2]);
					}
				}
				
				if (fp.verifyFolderPathdummy(intPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesInternal[0], true)) {
						appLog.info("filename and status is correct for "+filesInternal[0]);
					}
					else {
						appLog.error("filename and status is not verified for "+filesInternal[0]);
						sa.assertTrue(false, "filename and status is not verified for "+filesInternal[0]);
					}
					if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace,filesInternal[1] ,true)) {
						appLog.info("filename and status is verified successfully files present in common folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesInternal[1]);
						sa.assertTrue(false, "filename and status is not verified for "+filesInternal[1]);
					}
				}
				
				if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesShared[0], false)) {
						appLog.info("filename and status is correct for "+filesShared[0]);
					}
					else {
						appLog.error("filename and status is not verified for "+filesShared[0]);
						sa.assertTrue(false, "filename and status is not verified for "+filesShared[0]);
					}
					if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace,filesShared[1] ,false)) {
						appLog.info("filename and status is verified successfully files present in shared folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesShared[1]);
						sa.assertTrue(false, "filename and status is not verified for "+filesShared[1]);
					}
					if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace,filesShared[2] ,false)) {
						appLog.info("filename and status is verified successfully files present in shared folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesShared[2]);
						sa.assertTrue(false, "filename and status is not verified for "+filesShared[2]);
					}
				}
				
				
				if (fp.verifyFolderPathdummy(stdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesStand[0], false)) {
						appLog.info("filename and status is correct for "+filesStand[0]);
					}
					else {
						appLog.error("filename and status is not verified for "+filesStand[0]);
						sa.assertTrue(false, "filename and status is not verified for "+filesStand[0]);
					}
					if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace,filesStand[1] ,false)) {
						appLog.info("filename and status is verified successfully files present in std folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesStand[1]);
						sa.assertTrue(false, "filename and status is not verified for "+filesStand[1]);
					}
					if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace,filesStand[2] ,false)) {
						appLog.info("filename and status is verified successfully files present in std folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesStand[2]);
						sa.assertTrue(false, "filename and status is not verified for "+filesStand[2]);
					}
				}
				switchToDefaultContent(driver);
			}
			
			
			//Contact
			if (ip.clickOnTab(TabName.ContactTab)) {
				if (cp.clickOnCreatedContact(M9Contact1FirstName, M9Contact1LastName, null)) {
					
					switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
					scrollDownThroughWebelement(driver, ip.getWorkspaceSectionView(Workspace.FundraisingWorkspace,30),"fundraising workspace view");
					if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
						if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesCommon[0], false)) {
							appLog.info("filename and status is correct for "+filesCommon[0]);
						}
						else {
							appLog.error("filename and status is not verified for "+filesCommon[0]);
							sa.assertTrue(false, "filename and status is not verified for "+filesCommon[0]);
						}
						if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace,filesCommon[1] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesCommon[1]);
							sa.assertTrue(false, "filename and status is not verified for "+filesCommon[1]);
						}
						if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace,filesCommon[2] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesCommon[2]);
							sa.assertTrue(false, "filename and status is not verified for "+filesCommon[2]);
						}
					}
					
					if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
						if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesShared[0], false)) {
							appLog.info("filename and status is correct for "+filesShared[0]);
						}
						else {
							appLog.error("filename and status is not verified for "+filesShared[0]);
							sa.assertTrue(false, "filename and status is not verified for "+filesShared[0]);
						}
						if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace,filesShared[1] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesShared[1]);
							sa.assertTrue(false, "filename and status is not verified for "+filesShared[1]);
						}
						if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace,filesShared[2] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesShared[2]);
							sa.assertTrue(false, "filename and status is not verified for "+filesShared[2]);
						}
					}
					
					
					if (fp.verifyFolderPathdummy(stdPath, M9Institution1, null, M9FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
						if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesStand[0], false)) {
							appLog.info("filename and status is correct for "+filesStand[0]);
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[0]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[0]);
						}
						if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace,filesStand[1] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[1]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[1]);
						}
						if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace,filesStand[2] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[2]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[2]);
						}
					}
					switchToDefaultContent(driver);
			}
				else {
					appLog.error("could not find contact "+M9Contact1FirstName+" "+M9Contact1LastName);
					sa.assertTrue(false, "could not find contact "+M9Contact1FirstName+" "+M9Contact1LastName);
				}
			}
			else {
				appLog.error("contacts tab is not clickable");
				sa.assertTrue(false, "contacts tab is not clickable");
			}
		}
		else {
			appLog.error("inst tab is not clickable");
			sa.assertTrue(false, "inst tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc015_UploadManageApprovalOn_ImpactInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.CommonPath);
		String[] filesCommon  = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.UploadedFileCommon).split("<break>");
		
		lp.investorLogin(M9Contact1EmailId, adminPassword);
		List<String> fail_list = null;
		if (selectVisibleTextFromDropDown(driver, ifp.getFirmNameDropdownWRTPage(PageName.AllFirmsPage), "All Firms", "All Firms")) {
			ThreadSleep(5000);
			fail_list=afp.verifyAlertsOnAllFirmsPage(filesCommon[0], Org1FirmName, M9FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpload, PageName.AllFirmsPage); 				
			if (fail_list.isEmpty()) {
				appLog.error("pending docs are also being displayed on alerts on all firms page");
				sa.assertTrue(false, "pending docs are also being displayed on alerts on all firms page");
			}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.info("pending docs not found on all firms alert as expected");
				}
				
			}
		}
		else {
			appLog.error("could not find all firms option in dropdown");
			sa.assertTrue(false, "could not find all firms option in dropdown");
		}
		if (selectVisibleTextFromDropDown(driver, ifp.getFirmNameDropdownWRTPage(PageName.AllFirmsPage),Org1FirmName,Org1FirmName)) {
			click(driver, ifp.getRecentActivitiesTab(30), "recent activities tab", action.BOOLEAN);
			fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(filesCommon[0], M9FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities, activityType.DocumentUpload, PageName.AllFirmsPage);
			if (fail_list.isEmpty()) {
				appLog.error("pending docs are also being displayed on recent activities page");
				sa.assertTrue(false, "pending docs are also being displayed on recent activities page");
			}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.info("pending docs not found on recent activities tab as expected");
				}
				
			}
			
			click(driver, ifp.getAllDocumentsTab(30), "recent activities tab", action.BOOLEAN);
			fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(filesCommon[0], M9FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments, activityType.DocumentUpload, PageName.AllFirmsPage);
			if (fail_list.isEmpty()) {
				appLog.error("pending docs are also being displayed on recent activities page");
				sa.assertTrue(false, "pending docs are also being displayed on recent activities page");
			}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.info("pending docs not found on recent activities tab as expected");
				}
				
			}
		}
		else {
			appLog.error("could not find firm name in dropdown selection");
			sa.assertTrue(false, "could not find firm name in dropdown selection");
		}
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			//common
			if (fp.verifyFolderPathdummy(cmnPath, null,null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
			if (ifp.getNoDocumentToDisplay(60).getText().trim().equals(InvestorFirmPageErrorMessage.noDocumentPresentInFolder)) {
				appLog.info("no document to display error message is successfully displayed for common folder");
			}
			else {
				appLog.error("no document to display message is not displayed");
				sa.assertTrue(false, "no document to display message is not displayed");
			}
			}
			else {
				appLog.error(cmnPath + " is not displayed in folder structure");
				sa.assertTrue(false, cmnPath + " is not displayed in folder structure");
			}
			//shared
			if (fp.verifyFolderPathdummy(shdPath, null,null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				if (ifp.getNoDocumentToDisplay(60).getText().trim().equals(InvestorFirmPageErrorMessage.noDocumentPresentInFolder)) {
					appLog.info("no document to display error message is successfully displayed for shd folder");
				}
				else {
					appLog.error("no document to display message is not displayed");
					sa.assertTrue(false, "no document to display message is not displayed");
				}
					
			}
			else {
				appLog.error(shdPath + " is not displayed in folder structure");
				sa.assertTrue(false, shdPath + " is not displayed in folder structure");
			}
			//std
			if (fp.verifyFolderPathdummy(stdPath, M9Institution1,null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				if (ifp.getNoDocumentToDisplay(60).getText().trim().equals(InvestorFirmPageErrorMessage.noDocumentPresentInFolder)) {
					appLog.info("no document to display error message is successfully displayed for std folder");
				}
				else {
					appLog.error("no document to display message is not displayed");
					sa.assertTrue(false, "no document to display message is not displayed");
				}
					
			}
			else {
				appLog.error(stdPath + " is not displayed in folder structure");
				sa.assertTrue(false, stdPath + " is not displayed in folder structure");
			}
		}
		else {
			appLog.error("potential investments tab is not clickable");
			sa.assertTrue(false, "potential investments tab is not clickable");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Test
	public void M9tc016_VerifyDeleteFunctionality() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String cmnFolderName = cmnPath.split(" ")[0];
		String shdFolderName = shdPath.split(" ")[0];
		String stdFolderName = stdPath.split(" ")[0];
		String[] filesToDel = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.KeyWord_For_Search).split("<break>");
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		String docpath = "UploadFiles\\Module9\\";
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "fundraising manage approval icon", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon for "+Workspace.FundraisingWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getPendingDocsTab(30), "pending documents tab", action.BOOLEAN)) {
								if (selectVisibleTextFromDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.PendingDocuments,30), "manage approvals pending dropdown", "All Pending Documents")) {
									for (int i = 0;i<filesToDel.length;i++) {
										WebElement el=null;
										if (i!=2) {
											if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30),filesToDel[i] , M9FundName1+" > ", null, Org1FirmName, date)) {
												el = fp.checkboxForFileInManageApprovals(filesToDel[i]);
											}
										}
										else if (i==2) {
											if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30),filesToDel[i] , M9FundName1+" > "+M9Institution1+" > "+stdPath, null, Org1FirmName, date)) {
												el = fp.checkboxForFileInManageApprovals(filesToDel[i],M9FundName1+" > "+M9Institution1+" > "+stdPath);
											}
										}
										if (el!=null) {
											if (!isSelected(driver, el, "checkbox for "+filesToDel[i])) {
												if (click(driver, el,"checkbox for "+filesToDel[i] , action.SCROLLANDBOOLEAN)) {
													appLog.info("selected checkbox for "+filesToDel[i]);
												}
											}
											else {
												appLog.info("checkbox for "+filesToDel[i]+" is already selected");
											}

										}
										else {
											appLog.error("could not find "+filesToDel[i]);
											sa.assertTrue(false, "could not find "+filesToDel[i]);
										}

									}
								}
							if (click(driver, fp.getdeleteBtnManageApprovals(60), "delete Btn Manage Approvals", action.BOOLEAN)) {
								if (fp.getManageApprovalDeletePopupText(60).getText().trim().contains(FundsPageErrorMessage.manageApprovalDelSuccess1)
										&& fp.getManageApprovalDeletePopupText(60).getText().trim().contains(FundsPageErrorMessage.manageApprovalDelSuccess2)) {
									appLog.info("delete message is correct for manage approvals");
								}
								else {
									appLog.error("delete message is not correct for manage approvals");
									sa.assertTrue(false, "delete message is not correct for manage approvals");
								}
								if (fp.getManageApprovalDelYesorNo(YesNo.Yes,60)!=null) {
									appLog.info("yes button for manage approval is successfully displayed");
								}
								else {
									appLog.error("yes button is not displayed");
									sa.assertTrue(false, "yes button is not displayed");
								}
								if (fp.getManageApprovalDelYesorNo(YesNo.No,60)!=null) {
									appLog.info("no button for manage approval is successfully displayed");
								}
								else {
									appLog.error("no button is not displayed");
									sa.assertTrue(false, "no button is not displayed");
								}
								if (fp.getManageApprovalDelCrossIcon(60)!=null) {
									appLog.info("cross icon for manage approvals is successfully displayed");
								}
								else {
									appLog.error("cross icon for manage approvals is not present");
									sa.assertTrue(false, "cross icon for manage approvals is not present");
								}
								if (click(driver, fp.getManageApprovalDelYesorNo(YesNo.No,60), "delete message no button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on no button on delete popup");
								}
								else {
									appLog.error("no button on delete popup is not clickable");
									sa.assertTrue(false, "no button on delete popup is not clickable");
								}



									//clicking delete button and then cross icon on popup
									if (click(driver, fp.getdeleteBtnManageApprovals(60), "delete button manage approvals", action.SCROLLANDBOOLEAN)) {
										if (click(driver, fp.getManageApprovalDelCrossIcon(60), "manage approvals del cross icon", action.BOOLEAN)) {
										//checkboxes for all 3 files will still be selected
											fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30),filesToDel[0] , M9FundName1+" > ", null, Org1FirmName, date);
											if (isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[0]), "checkbox for "+filesToDel[0]))
											{
												appLog.info("checkboxes for "+filesToDel[0]+" to delete are selected");
											}
											else {
												appLog.error("checkboxes for the "+filesToDel[0]+" to delete are not selected");
												sa.assertTrue(false, "checkboxes for the "+filesToDel[0]+" to delete are not selected");
											}
												fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30),filesToDel[1] , M9FundName1+" > ", null, Org1FirmName, date);

												if (isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[1]), "checkbox for "+filesToDel[1]))
												{
												appLog.info("checkboxes for "+filesToDel[1]+"  are selected");
											}
											else {
												appLog.error("checkboxes for the "+filesToDel[1]+"  are not selected");
												sa.assertTrue(false, "checkboxes for the"+filesToDel[1]+"  are not selected");
											}
													fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30),filesToDel[2] , M9FundName1+" > "+M9Institution1, null, Org1FirmName, date);

												if (isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[2],M9Institution1), "checkbox for "+filesToDel[2]))
											{
												appLog.info("checkboxes for "+filesToDel[2]+" to delete are selected");
											}
											else {
												appLog.error("checkboxes for the "+filesToDel[2]+" are not selected");
												sa.assertTrue(false, "checkboxes for "+filesToDel[2]+" are not selected");
											}
										}
									}
									//clicking delete icon and then yes button
									if (click(driver, fp.getdeleteBtnManageApprovals(60), "delete button manage approvals", action.SCROLLANDBOOLEAN)) {
										if (click(driver, fp.getManageApprovalDelYesorNo(YesNo.Yes,60), "manage approval del yes button", action.SCROLLANDBOOLEAN)) {
											if (click(driver, fp.getManageAppCancelBtn(60), "get manage approval cancel button", action.SCROLLANDBOOLEAN)) {
												//verify files in common, shared and std folder
												if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
													if (!fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesToDel[0])) {
														appLog.info("file is successfully deleted from content grid");
													}
													else {
														appLog.error(filesToDel[0]+" should have been deleted but is still present in content grid");
														sa.assertTrue(false, filesToDel[0]+" should have been deleted but is still present in content grid");
													}
												}

												if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
													if (!fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesToDel[1])) {
														appLog.info("file is successfully deleted from content grid");
													}
													else {
														appLog.error(filesToDel[1]+" should have been deleted but is still present in content grid");
														sa.assertTrue(false, filesToDel[1]+" should have been deleted but is still present in content grid");
													}
												}

												if (fp.verifyFolderPathdummy(stdPath, M9Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
													if (!fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesToDel[2])) {
														appLog.info("file is successfully deleted from content grid");
													}
													else {
														appLog.error(filesToDel[2]+" should have been deleted but is still present in content grid");
														sa.assertTrue(false, filesToDel[2]+" should have been deleted but is still present in content grid");
													}
												}
												if (fp.verifyFolderPathdummy(stdPath, M9Institution2, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
													if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesToDel[2])) {
														appLog.info("file is successfully present content grid in inst2");
													}
													else {
														appLog.error(filesToDel[2]+" should be present but is not present in content grid");
														sa.assertTrue(false, filesToDel[2]+" should be present but is not present in content grid");
													}
												}
												else {
													appLog.error(stdPath+" is not found in folder structure");
													sa.assertTrue(false, stdPath+" is not found in folder structure");
												}
											}
											else {
												appLog.error("manage approval cancel button is not clickable");
												sa.assertTrue(false, "manage approval cancel button is not clickable");
											}
										}
										else {
											appLog.error("delete popup yes button is not clickable");
											sa.assertTrue(false, "delete popup yes button is not clickable");
										}
									}
									else {
										appLog.error("delete button is not clickable");
										sa.assertTrue(false, "delete button is not clickable");
									}
								}
								else {
									appLog.error("delete button is not clickable");
									sa.assertTrue(false, "delete button is not clickable");
								}
							}

							else {
								appLog.error("files could not be deleted");
								sa.assertTrue(false, "files could not be deleted");
							}

						}
						else {
							appLog.error("manage approvals icon is not clickable");
							sa.assertTrue(false, "manage approvals icon is not clickable");
						}
					}
					else {
						appLog.error("manage approvals icon is not clickable");
						sa.assertTrue(false, "manage approvals icon is not clickable");
					}
				}
				else {
					appLog.error(cmnPath + " is not present in folder structure");
					sa.assertTrue(false, cmnPath + " is not present in folder structure");
				}

				switchToDefaultContent(driver);
			}
			if (bp.clickOnTab(TabName.InstituitonsTab)) {
				if (ip.clickOnCreatedInstitution(M9Institution1)) {
					switchToFrame(driver, 30, ip.getFrame(environment,mode, PageName.InstitutionsPage, 30));
					//common folder
					if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
						if (!bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToDel[0])) {
							appLog.info(filesToDel[0]+"file is successfully deleted and is not present in "+PageName.InstitutionsPage);
						}
						else {
							appLog.error(filesToDel[0]+" should have been deleted but is still present in "+PageName.InstitutionsPage);
							sa.assertTrue(false, filesToDel[0]+" should have been deleted but is still present in "+PageName.InstitutionsPage);
						}
					}
					else {
						appLog.error(cmnPath+" is not present in folder structure");
						sa.assertTrue(false, cmnPath+" is not present in folder structure");
					}
					//shared
					if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
						if (!bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToDel[1])) {
							appLog.info(filesToDel[1]+"file is successfully deleted and is not present in "+PageName.InstitutionsPage);
						}
						else {
							appLog.error(filesToDel[1]+" should have been deleted but is still present in "+PageName.InstitutionsPage);
							sa.assertTrue(false, filesToDel[1]+" should have been deleted but is still present in "+PageName.InstitutionsPage);
						}
					}
					else {
						appLog.error(shdPath+" is not present in folder structure");
						sa.assertTrue(false, shdPath+" is not present in folder structure");
					}
					//standard inst 1
					if (fp.verifyFolderPathdummy(stdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
						if (!bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToDel[2])) {
							appLog.info(filesToDel[2]+"file is successfully deleted and is not present in "+PageName.InstitutionsPage);
						}
						else {
							appLog.error(filesToDel[2]+" should have been deleted but is still present in "+PageName.InstitutionsPage);
							sa.assertTrue(false, filesToDel[2]+" should have been deleted but is still present in "+PageName.InstitutionsPage);
						}
					}
					else {
						appLog.error(stdPath+" is not present in folder structure");
						sa.assertTrue(false, stdPath+" is not present in folder structure");
					}
					switchToDefaultContent(driver);
				}
				else {
					appLog.error(M9Institution1+" is not present in instittuions page");
					sa.assertTrue(false, M9Institution1+" is not present in instittuions page");
				}
			}
			else {
				appLog.error("institutions tab is not clickable");
				sa.assertTrue(false, "institutions tab is not clickable");
			}
			if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				//common
				if (fp.uploadFile(cmnPath, null, docpath+cmnFolderName+"_Single", null, UploadFileActions.Upload,
						Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfully");
					String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesCommon,false )) {
						appLog.info("filename and status is verified successfully files present in common folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesCommon);
						sa.assertTrue(false, "filename and status is not verified for "+filesCommon);
					}
				}
				else {
					appLog.error("could not upload file in "+cmnPath);
				}
				
				//shared
				if (fp.uploadFile(shdPath, null, docpath+shdFolderName+"_Single", null, UploadFileActions.Upload,
						Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfully");
					String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesShared,false )) {
						appLog.info("filename and status is verified successfully files present in shared folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesShared);
						sa.assertTrue(false, "filename and status is not verified for "+filesShared);
					}
				}
				else {
					appLog.error("could not upload file in "+shdPath);
				}
			
				
				//standard
				if (fp.uploadFile(stdPath, M9Institution1, docpath+stdFolderName+"_Single", null, UploadFileActions.Upload,
						Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfully");
					String filesStd = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesStd,false )) {
						appLog.info("filename and status is verified successfully files present in std folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesStd);
						sa.assertTrue(false, "filename and status is not verified for "+filesStd);
					}
				switchToDefaultContent(driver);
			}
				else {
					appLog.error("could not upload file in "+stdPath);
				}
			}
			else {
				appLog.error("fund "+M9FundName1+" cannot be found");
				sa.assertTrue(false, "fund "+M9FundName1+" cannot be found");
			}

			}
			else {
				appLog.error("funds tab is not clickable");
				sa.assertTrue(false, "funds tab is not clickable");
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

	@Test
	public void M9tc017_VerifyApprovingDocuments_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.UploadedFileStandard);
		String filesToDel[] = filesCommon.split("<break>");
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M9FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "workspace section view");
				if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getCheckAllDocsManageApprovals(60), "all checkbox", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getApproveBtnManageApprovals(60),"approve button on pending docs tab", action.SCROLLANDBOOLEAN)) {
								if (fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30)!=null) {
									appLog.info("yes button for manage approval approve popup is successfully displayed");
								}
								else {
									appLog.error("yes button is not present in manage approvals approve popup");
									sa.assertTrue(false, "yes button is not present in manage approvals approve popup");
								}
								if (fp.getManageApprovalApproveYesOrNo(YesNo.No, 30)!=null) {
									appLog.info("no button for manage approval approve popup is successfully displayed");
								}
								else {
									appLog.error("no button is not present in manage approvals approve popup");
									sa.assertTrue(false, "no button is not present in manage approvals approve popup");
								}
								if (fp.getManageApprovalApproveCrossIcon(60)!=null) {
									appLog.info("cross icon on approve poup is successfully displayed");
								}
								if (click(driver,fp.getManageApprovalApproveYesOrNo(YesNo.No, 30), "approve no button", action.SCROLLANDBOOLEAN)) {	
									appLog.info("no button is successfully clicked on approve popup");
									if ((isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[0]),"checkbox for "+filesToDel[0]))
										&& (isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[1]),"checkbox for "+filesToDel[1]))
										&& (isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[2]),"checkbox for "+filesToDel[2]))) {
											appLog.info("after clicking approve no button, still the 3 files checkboxes are checked");
										}
									else {
										appLog.error("checkbox for the 3 files to approve are not checked");
										sa.assertTrue(false, "checkbox for the 3 files to approve are not checked");
									}
								}
								else {
									appLog.error("no button on manage approvals approve popup is not clickable");
									sa.assertTrue(false, "no button on manage approvals approve popup is not clickable");
								}
							}
							if (click(driver,fp.getApproveBtnManageApprovals(60),"approve button on pending docs tab", action.SCROLLANDBOOLEAN)) {
								if (click(driver,fp.getManageApprovalApproveCrossIcon(60), "approve cross button", action.SCROLLANDBOOLEAN)) {	
									appLog.info("cross button is successfully clicked on approve popup");
								}
								else {
									appLog.error("cross button on manage approvals approve popup is not clickable");
									sa.assertTrue(false, "cross button on manage approvals approve popup is not clickable");
								}
								if ((isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[0]),"checkbox for "+filesToDel[0]))
										&& (isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[1]),"checkbox for "+filesToDel[1]))
										&& (isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[2]),"checkbox for "+filesToDel[2]))) {
											appLog.info("after clicking approve cross button, still the 3 files checkboxes are checked");
										}
									else {
										appLog.error("checkbox for the 3 files to approve are not checked");
										sa.assertTrue(false, "checkbox for the 3 files to approve are not checked");
									}
							}
							if (click(driver,fp.getApproveBtnManageApprovals(60),"approve button on pending docs tab", action.SCROLLANDBOOLEAN)) {
								if (click(driver,fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "approve yes button", action.SCROLLANDBOOLEAN)) {
									if (fp.getApproveConfirmPopupText(60).getText().trim().equals("3 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
										appLog.info("confirmation text for approval is verified successfully");
									}
									else {
										appLog.error("confirmation text for approval is incorrect");
										sa.assertTrue(false, "confirmation text for approval is incorrect");
									}
									if (fp.getManageApprovalsApproveConfirmationCrossIcon(60)!=null) {
										appLog.info("cross icon for successful approve is successfully displayed");
									}
									if (click(driver, fp.getManageApprovalsApproveConfirmationCrossIcon(60), "cross icon for successful approve icon", action.SCROLLANDBOOLEAN)) {
										if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
											appLog.info("no data to display is successfully displayed");
										}
									}
									else {
										appLog.error("cross icon on approve confirmation popup is not clickable");
										sa.assertTrue(false, "cross icon on approve confirmation popup is not clickable");
									}
								if (click(driver, fp.getApprovedDocsTab(60),"approved docs tab", action.SCROLLANDBOOLEAN)) {
										
										if (getSelectedOptionOfDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.ApprovedDocuments, 30), "dropdown of approved documents","text").equals(cmnPath)) {
											appLog.info("correct default selected option of dropdown is "+cmnPath);
										}
										else {
											appLog.error("default selected option of dropdown is incorrect. it is "+getSelectedOptionOfDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.ApprovedDocuments, 30), "dropdown of approved documents","text"));
											sa.assertTrue(false, "default selected option of dropdown is incorrect. it is "+getSelectedOptionOfDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.ApprovedDocuments, 30), "dropdown of approved documents","text"));
										}
										
										if (fp.verifyManageApprovalsDropdownContents(cmnPath, ManageApprovalTabs.ApprovedDocuments)) {
											appLog.info("dropdown content are successfully verified for common path");
										}
										else {
											appLog.error("dropdown contents for common folder could not be verified");
											sa.assertTrue(false, "dropdown contents for common folder could not be verified");
										}
										
									}
									//verifying documents in approved tab
									
						
								if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.ApprovedDocuments, filesToDel, M9FundName1+" > "+ cmnPath, "approved", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
										appLog.info("files are successfully present in manage approvals approved tab");
									}
									else {
										appLog.error("files are not present in approved docs tab manage approvals");
										sa.assertTrue(false, "files are not present in approved docs tab manage approvals");
									}
									click(driver, fp.getCrossIconManageApp(30), "close button manage approvals", action.SCROLLANDBOOLEAN);
									//verifying documents in funds page content grid
									if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
										if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesToDel[0],true)) {
											appLog.info("filesname and status is now succesfully verified on content grid "+PageName.FundsPage.toString()+" for "+cmnPath);
										}
										else {
											appLog.error("files name and status could not be verified");
											sa.assertTrue(false, "files name and status could not be verified");
										}
										if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesToDel[1],true)) {
											appLog.info("filesname and status is now succesfully verified on content grid "+PageName.FundsPage.toString()+" for "+cmnPath);
										}
										else {
											appLog.error("files name and status could not be verified");
											sa.assertTrue(false, "files name and status could not be verified");
										}
										if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesToDel[2],true)) {
											appLog.info("filesname and status is now succesfully verified on content grid "+PageName.FundsPage.toString()+" for "+cmnPath);
										}
										else {
											appLog.error("files name and status could not be verified");
											sa.assertTrue(false, "files name and status could not be verified");
										}
									}
									else {
										appLog.error(cmnPath+" is not found in folder structure");
										sa.assertTrue(false, cmnPath+" is not found in folder structure");
									}
								}
								else {
									appLog.error("manage approval approve yes button is not clicked");
									sa.assertTrue(false, "manage approval approve yes button is not clicked");
								}
							}
							else {
								appLog.error("manage approvals yes button is not clickable");
								sa.assertTrue(false, "manage approvals yes button is not clickable");
							}
						
						}
						else {
							appLog.error("all docs checkbox is not clickable on manage approval popup");
							sa.assertTrue(false, "all docs checkbox is not clickable on manage approval popup");
						}
					}
					else  {
						appLog.error("manage approval icon is not clickable");
						sa.assertTrue(false, "manage approval icon is not clickable");
					}
						
				}
				else {
					appLog.error(cmnPath+" is not found in folder path");
					sa.assertTrue(false, cmnPath+" is not found in folder path");
				}
				filesToDel = filesShared.split("<break>");
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getCheckAllDocsManageApprovals(60), "all checkbox", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getApproveBtnManageApprovals(60),"approve button on pending docs tab", action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "approve popup yes button", action.SCROLLANDBOOLEAN)) {

									if (fp.getApproveConfirmPopupText(60).getText().trim().equals("3 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
										appLog.info("confirmation text for approval is verified successfully");
									}
									else {
										appLog.error("confirmation text for approval is incorrect");
										sa.assertTrue(false, "confirmation text for approval is incorrect");
									}
									WebElement ele = isDisplayed(driver, FindElement(driver, "(//div[text()='Confirmation']/a/span)[1]", "manage approvals approve confirmation cross icon", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "manage approvals approve confirmation cross icon");
									if (ele!=null) {
										appLog.info("cross icon for successful approve is successfully displayed");
									}
									if (click(driver, ele, "cross icon for successful approve icon", action.BOOLEAN)) {
										if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
											appLog.info("no data to display is successfully displayed");
										}
									}
									if (click(driver, fp.getApprovedDocsTab(60),"approved docs tab", action.SCROLLANDBOOLEAN)) {
										if (fp.verifyManageApprovalsDropdownContents(shdPath, ManageApprovalTabs.ApprovedDocuments)) {
											appLog.info(shdPath+" dropdown contents are successfully verified");
										}
										else {
											appLog.error(shdPath+" contents could not be verified");
											sa.assertTrue(false, shdPath+" contents could not be verified");
										}
										if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.ApprovedDocuments, filesToDel, M9FundName1+" > "+ shdPath, "approved", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
											appLog.info("all files are present in manage approvals popup approved docs tab");
										}
										else {
											appLog.error("files are not present in approved docs tab");
											sa.assertTrue(false, "files are not present in approved docs tab");
										}
									}
									else {
										appLog.error("approved docs tab is not clickable on manage approvals popup");
										sa.assertTrue(false, "approved docs tab is not clickable on manage approvals popup");
									}

								}
								else {
									appLog.error("yes button on manage approvals approve popup is not clickable");
									sa.assertTrue(false, "yes button on manage approvals approve popup is not clickable");
								}
							}
							else {
								appLog.error("approve button is not clickable on manage approvals popup");
								sa.assertTrue(false, "approve button is not clickable on manage approvals popup");
							}
						}

						if (click(driver, fp.getCrossIconManageApp(30), "manage approvals close button", action.SCROLLANDBOOLEAN)) {
							if (fp.verifyFolderPathdummy(shdPath,null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
								if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesToDel[0],true)) {
									appLog.info(filesToDel[0] + " is successfully verified on content grid");
								}
								else {
									appLog.error(filesToDel[0]+" is not present on content grid fundraising workspace");
									sa.assertTrue(false, filesToDel[0]+" is not present on content grid fundraising workspace");
								}
								if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesToDel[1],true)) {
									appLog.info(filesToDel[1] + " is successfully verified on content grid");
								}
								else {
									appLog.error(filesToDel[1]+" is not present on content grid fundraising workspace");
									sa.assertTrue(false, filesToDel[1]+" is not present on content grid fundraising workspace");
								}
								if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesToDel[2],true)) {
									appLog.info(filesToDel[2] + " is successfully verified on content grid");
								}
								else {
									appLog.error(filesToDel[2]+" is not present on content grid fundraising workspace");
									sa.assertTrue(false, filesToDel[2]+" is not present on content grid fundraising workspace");
								}
							}
							else {
								appLog.error(shdPath+" is not found on folder structure");
								sa.assertTrue(false, shdPath+" is not found on folder structure");
							}
						}
						else {
							appLog.error("manage approvals close button is not clickable");
							sa.assertTrue(false, "manage approvals close button is not clickable");
						}
					}
					else  {
						appLog.error("manage approval icon is not clickable");
						sa.assertTrue(false, "manage approval icon is not clickable");
					}
				}
				else {
					appLog.error(shdPath+" is not found on folder structure");
					sa.assertTrue(false, shdPath+" is not found on folder structure");
				}
				filesToDel = filesStandard.split("<break>");
				if (fp.verifyFolderPathdummy(stdPath,M9Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) { 
					if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getCheckAllDocsManageApprovals(60), "all checkbox", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getApproveBtnManageApprovals(60),"approve button on pending docs tab", action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "yes button on approve popup", action.SCROLLANDBOOLEAN)) {
									appLog.info("yes button for manage approval approve popup is successfully displayed");
									if (fp.getApproveConfirmPopupText(60).getText().trim().equals("3 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
										appLog.info("confirmation text for approval is verified successfully");
									}
									else {
										appLog.error("confirmation text for approval is incorrect");
										sa.assertTrue(false, "confirmation text for approval is incorrect");
									}
									WebElement ele = isDisplayed(driver, FindElement(driver, "(//div[text()='Confirmation']/a/span)[1]", "manage approvals approve confirmation cross icon", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "manage approvals approve confirmation cross icon");
									if (ele!=null) {
										appLog.info("cross icon for successful approve is successfully displayed");
									}
									if (click(driver, ele, "cross icon for successful approve icon", action.BOOLEAN)) {
										if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
											appLog.info("no data to display is successfully displayed");
										}
									}
									if (click(driver, fp.getApprovedDocsTab(60),"approved docs tab", action.SCROLLANDBOOLEAN)) {
										if (fp.verifyManageApprovalsDropdownContents(stdPath, ManageApprovalTabs.ApprovedDocuments)) {
											appLog.info("successfully verified dropdown contents of std folder in approved docs");
										}
										else {
											appLog.error("could not verify dropdown contents std folder");
											sa.assertTrue(false, "could not verify dropdown contents std folder");
										}
										if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.ApprovedDocuments, filesToDel, M9FundName1+" > " + M9Institution1+" > "+ stdPath, "approved", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
											appLog.info("successfully verified file presence in approved docs of "+filesToDel);
										}
										else {
											appLog.error("file not found in approved docs "+filesToDel);
											sa.assertTrue(false, "file not found in approved docs "+filesToDel);
										}
									}
									else {
										appLog.error("approved docs tab is not clickable on manage approvals popup");
										sa.assertTrue(false, "approved docs tab is not clickable on manage approvals popup");
									}
								}
								else {
									appLog.error("yes button is not clickable on manage approvals aprove popup");
									sa.assertTrue(false, "yes button is not clickable on manage approvals aprove popup");
								}
							}
							else {
								appLog.error("approve button is not clickable on manage approvals popup");
								sa.assertTrue(false, "approve button is not clickable on manage approvals popup");
							}
						}
						else {
							appLog.error("checkbox for checking all docs in manage approvals popup is not clickable");
							sa.assertTrue(false, "checkbox for checking all docs in manage approvals popup is not clickable");
						}

					}
					else {
						appLog.error("manage approvals icon is not clickable");
						sa.assertTrue(false, "manage approvals icon is not clickable");
					}
				}
				if (click(driver, fp.getCrossIconManageApp(60), "manage approvals close button", action.SCROLLANDBOOLEAN)) {
					if (fp.verifyFolderPathdummy(stdPath,M9Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesToDel[0],true)) {
							appLog.info(filesToDel[0] + " is successfully verified on funds page content grid");
						}
						else {
							appLog.error(filesToDel[0] + " is not present on content grid funds page");
							sa.assertTrue(false, filesToDel[0] + " is not present on content grid funds page");
						}
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesToDel[1],true)) {
							appLog.info(filesToDel[1] + " is successfully verified on funds page content grid");
						}
						else {
							appLog.error(filesToDel[1] + " is not present on content grid funds page");
							sa.assertTrue(false, filesToDel[1] + " is not present on content grid funds page");
						}
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesToDel[2],true)) {
							appLog.info(filesToDel[2] + " is successfully verified on funds page content grid");
						}
						else {
							appLog.error(filesToDel[2] + " is not present on content grid funds page");
							sa.assertTrue(false, filesToDel[2] + " is not present on content grid funds page");
						}
					}
					else {
						appLog.error(stdPath+" for "+M9Institution1+" is not displayed");
						sa.assertTrue(false, stdPath+" for "+M9Institution1+" is not displayed");
					}
				}
				else {
					appLog.error("manage approvals close button is not clickable");
					sa.assertTrue(false, "manage approvals close button is not clickable");
				}
				filesToDel = filesStandard.split("<break>");
				if (fp.verifyFolderPathdummy(stdPath,M9Institution2, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) { 
					if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getCheckAllDocsManageApprovals(60), "all checkbox", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getApproveBtnManageApprovals(60),"approve button on pending docs tab", action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "approve popup yes button", action.SCROLLANDBOOLEAN)) {
									appLog.info("yes button for manage approval approve popup is successfully displayed");
									if (fp.getApproveConfirmPopupText(60).getText().trim().equals("3 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
										appLog.info("confirmation text for approval is verified successfully");
									}
									else {
										appLog.error("confirmation text for approval is incorrect");
										sa.assertTrue(false, "confirmation text for approval is incorrect");
									}
									WebElement ele = isDisplayed(driver, FindElement(driver, "(//div[text()='Confirmation']/a/span)[1]", "manage approvals approve confirmation cross icon", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "manage approvals approve confirmation cross icon");
									if (ele!=null) {
										appLog.info("cross icon for successful approve is successfully displayed");
									}
									if (click(driver, ele, "cross icon for successful approve icon", action.BOOLEAN)) {
										if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
											appLog.info("no data to display is successfully displayed");
										}
										else {
											appLog.error("no data to display is not displayed on pending documents");
											sa.assertTrue(false, "no data to display is not displayed on pending documents");
										}
									}
									if (click(driver, fp.getApprovedDocsTab(60),"approved docs tab", action.SCROLLANDBOOLEAN)) {
										if (fp.verifyManageApprovalsDropdownContents(stdPath, ManageApprovalTabs.ApprovedDocuments)) {
											appLog.info(stdPath+" dropdown contents are successfully verified");
										}
										else {
											appLog.error(stdPath+" dropdown contents could not be verified");
											sa.assertTrue(false, stdPath+" dropdown contents could not be verified");
										}
										if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.ApprovedDocuments, filesToDel, M9FundName1+" > " + M9Institution2+" > "+ stdPath, "approved", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
											appLog.info("standard files are verified successfully on manage approvals window");
										}
										else {
											appLog.error("standard files could not be verified on manage approvals window");
											sa.assertTrue(false, "standard files could not be verified on manage approvals window");
										
										}
									}
										else {
												appLog.error("approved docs tab is not clickable on manage approvals popup");
												sa.assertTrue(false, "approved docs tab is not clickable on manage approvals popup");
											}
										}
										else {
											appLog.error("yes button is not clickable on manage approvals aprove popup");
											sa.assertTrue(false, "yes button is not clickable on manage approvals aprove popup");
										}
									}
									else {
										appLog.error("approve button is not clickable on manage approvals popup");
										sa.assertTrue(false, "approve button is not clickable on manage approvals popup");
									}
								}
								else {
									appLog.error("checkbox for checking all docs in manage approvals popup is not clickable");
									sa.assertTrue(false, "checkbox for checking all docs in manage approvals popup is not clickable");
								}

							}
							else {
								appLog.error("manage approvals icon is not clickable");
								sa.assertTrue(false, "manage approvals icon is not clickable");
							}
				}
				else {
					appLog.error(stdPath+" for "+M9Institution2+" is not clickable");
					sa.assertTrue(false, stdPath+" for "+M9Institution2+" is not clickable");
				}
					
				if (click(driver, fp.getCrossIconManageApp(60), "manage approvals close button", action.SCROLLANDBOOLEAN)) {
					if (fp.verifyFolderPathdummy(stdPath,M9Institution2, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesToDel[0],true)) {
							appLog.info(filesToDel[0] + " is successfully verified on funds page content grid");
						}
						else {
							appLog.error(filesToDel[0] + " is not present on content grid funds page");
							sa.assertTrue(false, filesToDel[0] + " is not present on content grid funds page");
						}
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesToDel[1],true)) {
							appLog.info(filesToDel[1] + " is successfully verified on funds page content grid");
						}
						else {
							appLog.error(filesToDel[1] + " is not present on content grid funds page");
							sa.assertTrue(false, filesToDel[1] + " is not present on content grid funds page");
						}
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesToDel[2],true)) {
							appLog.info(filesToDel[2] + " is successfully verified on funds page content grid");
						}
						else {
							appLog.error(filesToDel[2] + " is not present on content grid funds page");
							sa.assertTrue(false, filesToDel[2] + " is not present on content grid funds page");
						}
					}
					else {
						appLog.error(stdPath+" for "+M9Institution2+"is not found on folder structure");
						sa.assertTrue(false, stdPath+" for "+M9Institution2+"is not found on folder structure");
					}
				}
				else {
					appLog.error("close button is not clickable on manage approvals window");
					sa.assertTrue(false, "close button is not clickable on manage approvals window");
				}

			}
			else {
				appLog.error(M9FundName1+" is not found on funds page");
				sa.assertTrue(false, M9FundName1+" is not found on funds page");
			}
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M9tc017_VerifyApprovingDocuments_Impact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.UploadedFileStandard);
		String filesToDel[] = filesCommon.split("<break>");
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		//Institution
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M9Institution1)) {
				switchToFrame(driver, 30, ip.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToDel[0], true)) {
						appLog.info(filesToDel[0] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToDel[0] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToDel[0] + " is not verified on content grid "+cmnPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToDel[0], true)) {
						appLog.info(filesToDel[1] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToDel[1] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToDel[1] + " is not verified on content grid "+cmnPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToDel[2], true)) {
						appLog.info(filesToDel[2] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToDel[2] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToDel[2] + " is not verified on content grid "+cmnPath);
					}
				}
				else {
					appLog.error(cmnPath+" is not present on folder structure");
					sa.assertTrue(false, cmnPath+" is not present on folder structure");
					
				}
				filesToDel = filesShared.split("<break>");
				if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToDel[0], true)) {
						appLog.info(filesToDel[0] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToDel[0] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToDel[0] + " is not verified on content grid "+shdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToDel[0], true)) {
						appLog.info(filesToDel[1] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToDel[1] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToDel[1] + " is not verified on content grid "+shdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToDel[2], true)) {
						appLog.info(filesToDel[2] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToDel[2] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToDel[2] + " is not verified on content grid "+shdPath);
					}
				}
				else {
					appLog.error(shdPath+" is not present on folder structure");
					sa.assertTrue(false, shdPath+" is not present on folder structure");
					
				}
				
				filesToDel = filesStandard.split("<break>");
				if (fp.verifyFolderPathdummy(stdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToDel[0], true)) {
						appLog.info(filesToDel[0] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToDel[0] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToDel[0] + " is not verified on content grid "+stdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToDel[1], true)) {
						appLog.info(filesToDel[1] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToDel[1] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToDel[1] + " is not verified on content grid "+stdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToDel[2], true)) {
						appLog.info(filesToDel[2] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToDel[2] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToDel[2] + " is not verified on content grid "+stdPath);
					}
				}
				else {
					appLog.error(stdPath+" is not present on folder structure");
					sa.assertTrue(false, stdPath+" is not present on folder structure");
					
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9Institution1+" is not present on institutions page");
				sa.assertTrue(false, M9Institution1+" is not present on institutions page");
			}
		}
		else {
			appLog.error("institutions tab is not clickable");
			sa.assertTrue(false, "institutions tab is not clickable");
		}
		filesToDel = filesCommon.split("<break>");
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M9Contact1FirstName, M9Contact1LastName, null)) {
				switchToFrame(driver, 30, fp.getFrame(environment,mode, PageName.ContactsPage, 30));
				if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToDel[0], true)) {
						appLog.info(filesToDel[0] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToDel[0] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToDel[0] + " is not verified on content grid "+cmnPath);
					}
				}
				else {
					appLog.error(cmnPath+" is not present on folder structure");
					sa.assertTrue(false, cmnPath+" is not present on folder structure");

				}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToDel[1], true)) {
						appLog.info(filesToDel[1] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToDel[1] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToDel[1] + " is not verified on content grid "+cmnPath);
					}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToDel[2], true)) {
						appLog.info(filesToDel[2] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToDel[2] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToDel[2] + " is not verified on content grid "+cmnPath);
					}
				}
				else {
					appLog.error(cmnPath+" is not present on folder structure");
					sa.assertTrue(false, cmnPath+" is not present on folder structure");
					
				}
				filesToDel = filesShared.split("<break>");
				if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToDel[0], true)) {
						appLog.info(filesToDel[0] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToDel[0] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToDel[0] + " is not verified on content grid "+shdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToDel[1], true)) {
						appLog.info(filesToDel[1] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToDel[1] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToDel[1] + " is not verified on content grid "+shdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToDel[2], true)) {
						appLog.info(filesToDel[2] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToDel[2] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToDel[2] + " is not verified on content grid "+shdPath);
					}
				}
				else {
					appLog.error(shdPath+" is not present on folder structure");
					sa.assertTrue(false, shdPath+" is not present on folder structure");
					
				}
				
				filesToDel = filesStandard.split("<break>");
				if (fp.verifyFolderPathdummy(stdPath, M9Institution1, null, M9FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToDel[0], true)) {
						appLog.info(filesToDel[0] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToDel[0] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToDel[0] + " is not verified on content grid "+stdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToDel[1], true)) {
						appLog.info(filesToDel[1] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToDel[1] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToDel[1] + " is not verified on content grid "+stdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToDel[2], true)) {
						appLog.info(filesToDel[2] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToDel[2] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToDel[2] + " is not verified on content grid "+stdPath);
					}
				}
				else {
					appLog.error(stdPath+" is not present on folder structure");
					sa.assertTrue(false, stdPath+" is not present on folder structure");
					
				}
					switchToDefaultContent(driver);
					
			}
		
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc017_VerifyApprovingDocuments_ImpactInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc017_VerifyApprovingDocuments_Impact", excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc017_VerifyApprovingDocuments_Impact", excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc017_VerifyApprovingDocuments_Impact", excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.UploadedFileStandard);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		SoftAssert saa = new SoftAssert();
		lp.investorLogin(M9Contact1EmailId, adminPassword);
		List<String> fail_list = null;
		click(driver, ifp.getAllDocumentsTab(30), "all documents tab", action.BOOLEAN);
		//fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(filesCommon+"<break>"+filesShared, M9FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments, activityType.DocumentUpload, PageName.AllFirmsPage);
		Set<String> allDocsFiles =scrollActiveWidgetforSetofFiles(driver, ifp.getScrollBoxAtFirmPage(30), By.xpath("//span[contains(@id,'myGrid_firmAllDoc-row')]//span[contains(@id,'cell-0')]/a"));
		for (int i = 0;i<filesCommon.split("<break>").length;i++) {
			if (!allDocsFiles.contains(filesCommon.split("<break>")[i])) {
				appLog.error("could not find "+filesCommon.split("<break>")[i]+" on all docs grid");
				sa.assertTrue(false, "could not find "+filesCommon.split("<break>")[i]+" on all docs grid");
			}
			else
				appLog.info("successfully verified "+filesCommon.split("<break>")[i]+" on all docs grid");
		}
		
		for (int i = 0;i<filesShared.split("<break>").length;i++) {
			if (!allDocsFiles.contains(filesShared.split("<break>")[i])) {
				appLog.error("could not find "+filesShared.split("<break>")[i]+" on all docs grid");
				sa.assertTrue(false, "could not find "+filesShared.split("<break>")[i]+" on all docs grid");
			}
			else
				appLog.info("successfully verified "+filesShared.split("<break>")[i]+" on all docs grid");
		}
		
		/*if (fail_list.isEmpty()) {
			appLog.info("successfully verified common and shared files on AllDocument page");
				}
		else {
			for (int i = 0;i<fail_list.size();i++) {
				appLog.error("could not verify common and shared files alerts on AllDocument tab");
				sa.assertTrue(false, "could not verify common and shared files alerts on AllDocument tab");
			}
			
		}*/
		
		if (selectVisibleTextFromDropDown(driver, ifp.getFirmNameDropdownWRTPage(PageName.AllFirmsPage), "All Firms", "All Firms")) {
			ThreadSleep(5000);
			fail_list=afp.verifyAlertsOnAllFirmsPage(filesCommon+"<break>"+filesShared, Org1FirmName, M9FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpload, PageName.AllFirmsPage); 				
			if (fail_list.isEmpty()) {
				appLog.info("successfully verified common and shared files on all firms alerts");
				}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.error("docs could not be verified on all firms page");
					sa.assertTrue(false, "docs could not be verified on all firms page");
				}
				
			}
		}
		else {
			appLog.error("could not find all firms option in dropdown");
			sa.assertTrue(false, "could not find all firms option in dropdown");
		}
		if (selectVisibleTextFromDropDown(driver, ifp.getFirmNameDropdownWRTPage(PageName.AllFirmsPage),Org1FirmName,Org1FirmName)) {
			click(driver, ifp.getRecentActivitiesTab(30), "recent activities tab", action.BOOLEAN);
			fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(filesCommon+"<break>"+filesShared, M9FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities, activityType.DocumentUpload, PageName.AllFirmsPage);
			if (fail_list.isEmpty()) {
				appLog.info("successfully verified common and shared files on recent activities page");
					}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.error("could not verify common and shared files alerts on recent activities tab");
					sa.assertTrue(false, "could not verify common and shared files alerts on recent activities tab");
				}
				
			}
			
		}
		else {
			appLog.error("could not find firm name in dropdown selection");
			sa.assertTrue(false, "could not find firm name in dropdown selection");
		}
		
		ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment);
		if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
			saa=ifp.verifyContentGridInvestorSide(driver,PageName.PotentialInvestmentPage, filesCommon,CRMUser1FirstName+" "+CRMUser1LastName,date );
			sa.combineAssertions(saa);
		}
		else {
			appLog.error(cmnPath+" is not present on folder structure");
			sa.assertTrue(false, cmnPath+" is not present on folder structure");

		}
		if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
			saa=ifp.verifyContentGridInvestorSide(driver,PageName.PotentialInvestmentPage, filesShared,CRMUser1FirstName+" "+CRMUser1LastName,date );
			sa.combineAssertions(saa);
		}
		else {
			appLog.error(shdPath+" is not present on folder structure");
			sa.assertTrue(false, shdPath+" is not present on folder structure");

		}

		if (fp.verifyFolderPathdummy(stdPath, M9Institution1, null, M9FundName1, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
			saa=ifp.verifyContentGridInvestorSide(driver,PageName.PotentialInvestmentPage, filesStandard,CRMUser1FirstName+" "+CRMUser1LastName,date );
			sa.combineAssertions(saa);
		}
		else {
			appLog.error(stdPath+" is not present on folder structure");
			sa.assertTrue(false, stdPath+" is not present on folder structure");

		}

		if (fp.verifyFolderPathdummy(stdPath, M9Institution2, null, M9FundName1, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
			saa=ifp.verifyContentGridInvestorSide(driver,PageName.PotentialInvestmentPage, filesStandard,CRMUser1FirstName+" "+CRMUser1LastName,date );
			sa.combineAssertions(saa);
		}
		else {
			appLog.error(stdPath+" is not present on folder structure");
			sa.assertTrue(false, stdPath+" is not present on folder structure");

		}
		lp.investorLogout();
		sa.assertAll();

	}
	
	@Test
	public void M9tc018_VerifyOnlineImportManageApprovalOn_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String intPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		String docPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.OnlineImportPath);
		String fileCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
		String fileInternal = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
		
		String fileShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared);
		String fileStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String filesToImport[] = fileCommon.split("<break>");
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				if (fp.onlineImport(environment, mode, null, null, null, cmnPath, docPath, fileCommon, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Common, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("online import is done successfully for "+cmnPath);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesToImport[0] , false)) {
						appLog.info(filesToImport[0] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[0] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[0] + " could not be found on content grid");
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesToImport[1] , false)) {
						appLog.info(filesToImport[1] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[1] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[1] + " could not be found on content grid");
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesToImport[2] , false)) {
						appLog.info(filesToImport[2] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[2] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[2] + " could not be found on content grid");
					}
					if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30),"manage approval icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
						if (fp.verifyManageApprovalsDropdownContents(cmnPath, ManageApprovalTabs.PendingDocuments)) {
							appLog.info("manage approval dropdown contents are successfully verified");
						}
						else {
							appLog.error("manage approval dropdown contents are wrong");
							sa.assertTrue(false, "manage approval dropdown contents are wrong");
						}
						if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,filesToImport , M9FundName1+" > "+cmnPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
							appLog.info("imported files are successfully verified on manage approvals popup");
						}
						else {
							appLog.error("imported files could not be verified on manage approvals popup");
							sa.assertTrue(false, "imported files could not be verified on manage approvals popup");
						}
						if (click(driver, fp.getManageApprovalsCancelBtn(60), "manage approval pendings tab cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on cancel button");
						}
						else {
							appLog.error("cannot click on cancel button");
							sa.assertTrue(false, "cannot click on cancel button");
						}
					}
				}
				else {
					appLog.error("online import was unsuccessfull for "+cmnPath);
					sa.assertTrue(false, "online import was unsuccessfull for "+cmnPath);
				}
				
				//internal
				filesToImport = fileInternal.split("<break>");
				switchToDefaultContent(driver);
				if (fp.onlineImport(environment, mode, null, null, null, intPath, docPath, fileInternal, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPDATE, FolderType.Internal, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("online import is done successfully for "+intPath);
					
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					
					if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30),"manage approval icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
						click(driver, fp.getApprovedDocsTab(60), "approved documents tab", action.BOOLEAN);
						if (fp.verifyManageApprovalsDropdownContents(intPath, ManageApprovalTabs.ApprovedDocuments)) {
							appLog.info("manage approval dropdown contents are successfully verified");
						}
						else {
							appLog.error("manage approval dropdown contents are wrong");
							sa.assertTrue(false, "manage approval dropdown contents are wrong");
						}
						if (fp.noDataToDisplay(ManageApprovalTabs.ApprovedDocuments, 60).getText().trim().equals(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
							appLog.info("no data to display error message is verified succeessfully");
						}
						else {
							appLog.error("no data to display is not verified on approved documents for internal folder");
							sa.assertTrue(false, "no data to display is not verified on approved documents for internal folder");
						}
						if (click(driver, fp.getCrossIconManageApp(30), "manage approval pendings tab cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on cancel button");
						}
						else {
							appLog.error("cannot click on cancel button");
						}
					}
				}
				else {
					appLog.error("online import was unsuccessfull for "+cmnPath);
					sa.assertTrue(false, "online import was unsuccessfull for "+cmnPath);
				}
				
				//shared
				filesToImport = fileShared.split("<break>");
				switchToDefaultContent(driver);
				if (fp.onlineImport(environment, mode, null, null, null, shdPath, docPath, fileShared, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Shared, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("online import is done successfully for "+shdPath);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesToImport[0] , false)) {
						appLog.info(filesToImport[0] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[0] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[0] + " could not be found on content grid");
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesToImport[1] , false)) {
						appLog.info(filesToImport[1] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[1] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[1] + " could not be found on content grid");
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesToImport[2] , false)) {
						appLog.info(filesToImport[2] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[2] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[2] + " could not be found on content grid");
					}
					if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30),"manage approval icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
						if (fp.verifyManageApprovalsDropdownContents(shdPath, ManageApprovalTabs.PendingDocuments)) {
							appLog.info("manage approval dropdown contents are successfully verified");
						}
						else {
							appLog.error("manage approval dropdown contents are wrong");
							sa.assertTrue(false, "manage approval dropdown contents are wrong");
						}
						if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,filesToImport , M9FundName1+" > "+shdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
							appLog.info("imported files are successfully verified on manage approvals popup");
						}
						else {
							appLog.error("imported files could not be verified on manage approvals popup");
							sa.assertTrue(false, "imported files could not be verified on manage approvals popup");
						}
						if (click(driver, fp.getManageApprovalsCancelBtn(60), "manage approval pendings tab cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on cancel button");
						}
						else {
							appLog.error("cannot click on cancel button");
						}
					}
				}
				else {
					appLog.error("online import was unsuccessfull for "+shdPath);
					sa.assertTrue(false, "online import was unsuccessfull for "+shdPath);
				}
				
				
				
				//standard
				filesToImport = fileStandard.split("<break>");
				switchToDefaultContent(driver);
				if (fp.onlineImport(environment, mode, M9Institution1, null, M9Institution2, stdPath, docPath, fileStandard, BoxUserName, BoxPassword, OnlineImportFileAddTo.MultipleInstitute, WorkSpaceAction.UPLOAD, FolderType.Standard, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("online import is done successfully for "+stdPath);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesToImport[0] , false)) {
						appLog.info(filesToImport[0] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[0] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[0] + " could not be found on content grid");
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesToImport[1] , false)) {
						appLog.info(filesToImport[1] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[1] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[1] + " could not be found on content grid");
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesToImport[2] , false)) {
						appLog.info(filesToImport[2] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[2] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[2] + " could not be found on content grid");
					}
					if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30),"manage approval icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
						if (fp.verifyManageApprovalsDropdownContents(stdPath, ManageApprovalTabs.PendingDocuments)) {
							appLog.info("manage approval dropdown contents are successfully verified");
						}
						else {
							appLog.error("manage approval dropdown contents are wrong");
							sa.assertTrue(false, "manage approval dropdown contents are wrong");
						}
						if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,filesToImport , M9FundName1+" > "+M9Institution1+" > " +stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
							appLog.info("imported files are successfully verified on manage approvals popup");
						}
						else {
							appLog.error("imported files could not be verified on manage approvals popup");
							sa.assertTrue(false, "imported files could not be verified on manage approvals popup");
						}
						if (click(driver, fp.getManageApprovalsCancelBtn(60), "manage approval pendings tab cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on cancel button");
						}
						else {
							appLog.error("cannot click on cancel button");
						}
					}
					//std inst2
					if (fp.verifyFolderPathdummy(stdPath, M9Institution2, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesToImport[0] , false)) {
							appLog.info(filesToImport[0] + " was successfully found on content grid");
						}
						else {
							appLog.error(filesToImport[0] + " could not be found on content grid");
							sa.assertTrue(false, filesToImport[0] + " could not be found on content grid");
						}
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesToImport[1] , false)) {
							appLog.info(filesToImport[1] + " was successfully found on content grid");
						}
						else {
							appLog.error(filesToImport[1] + " could not be found on content grid");
							sa.assertTrue(false, filesToImport[1] + " could not be found on content grid");
						}
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,filesToImport[2] , false)) {
							appLog.info(filesToImport[2] + " was successfully found on content grid");
						}
						else {
							appLog.error(filesToImport[2] + " could not be found on content grid");
							sa.assertTrue(false, filesToImport[2] + " could not be found on content grid");
						}
					}
				}
				else {
					appLog.error("online import was unsuccessfull for "+stdPath);
					sa.assertTrue(false, "online import was unsuccessfull for "+stdPath);
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("fund "+M9FundName1+" could not be found");
				sa.assertTrue(false, "fund "+M9FundName1+" could not be found");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc018_VerifyOnlineImportManageApprovalOn_Impact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String intPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileCommon);
		String filesInternal = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileInternal);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileStandard);
		String filesToImport[] = filesCommon.split("<break>");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M9Institution1)) {
				switchToFrame(driver, 30, ip.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToImport[0], false)) {
						appLog.info(filesToImport[0] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToImport[0] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToImport[0] + " is not verified on content grid "+cmnPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToImport[1], false)) {
						appLog.info(filesToImport[1] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToImport[1] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToImport[1] + " is not verified on content grid "+cmnPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToImport[2], false)) {
						appLog.info(filesToImport[2] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToImport[2] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToImport[2] + " is not verified on content grid "+cmnPath);
					}
				}
				else {
					appLog.error(cmnPath+" is not present on folder structure");
					sa.assertTrue(false, cmnPath+" is not present on folder structure");
					
				}
				//internal
				filesToImport = filesInternal.split("<break>");
				if (fp.verifyFolderPathdummy(intPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToImport[0], true)) {
						appLog.info(filesToImport[0] + " is successfully verified on content grid folder "+intPath);
					}
					else {
						appLog.error(filesToImport[0] + " is not verified on content grid "+intPath);
						sa.assertTrue(false, filesToImport[0] + " is not verified on content grid "+intPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToImport[1], true)) {
						appLog.info(filesToImport[1] + " is successfully verified on content grid folder "+intPath);
					}
					else {
						appLog.error(filesToImport[1] + " is not verified on content grid "+intPath);
						sa.assertTrue(false, filesToImport[1] + " is not verified on content grid "+intPath);
					}
				}
				else {
					appLog.error(intPath+" is not present on folder structure");
					sa.assertTrue(false, intPath+" is not present on folder structure");
					
				}
				//shared
				filesToImport = filesShared.split("<break>");
				if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToImport[0], false)) {
						appLog.info(filesToImport[0] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToImport[0] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToImport[0] + " is not verified on content grid "+shdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToImport[1], false)) {
						appLog.info(filesToImport[1] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToImport[1] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToImport[1] + " is not verified on content grid "+shdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToImport[2], false)) {
						appLog.info(filesToImport[2] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToImport[2] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToImport[2] + " is not verified on content grid "+shdPath);
					}
				}
				else {
					appLog.error(shdPath+" is not present on folder structure");
					sa.assertTrue(false, shdPath+" is not present on folder structure");
					
				}
				//standard
				filesToImport = filesStandard.split("<break>");
				if (fp.verifyFolderPathdummy(stdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToImport[0], false)) {
						appLog.info(filesToImport[0] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToImport[0] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToImport[0] + " is not verified on content grid "+stdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToImport[1], false)) {
						appLog.info(filesToImport[1] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToImport[1] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToImport[1] + " is not verified on content grid "+stdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesToImport[2], false)) {
						appLog.info(filesToImport[2] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToImport[2] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToImport[2] + " is not verified on content grid "+stdPath);
					}
				}
				else {
					appLog.error(stdPath+" is not present on folder structure");
					sa.assertTrue(false, stdPath+" is not present on folder structure");
					
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9Institution1+" is not present on institutions page");
				sa.assertTrue(false, M9Institution1+" is not present on institutions page");
			}
		}
		else {
			appLog.error("institutions tab is not clickable");
			sa.assertTrue(false, "institutions tab is not clickable");
		}
		filesToImport = filesCommon.split("<break>");
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M9Contact1FirstName, M9Contact1LastName, null)) {
				switchToFrame(driver, 30, fp.getFrame(environment,mode, PageName.ContactsPage, 30));
				if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToImport[0], false)) {
						appLog.info(filesToImport[0] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToImport[0] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToImport[0] + " is not verified on content grid "+cmnPath);
					}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToImport[1], false)) {
						appLog.info(filesToImport[1] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToImport[1] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToImport[1] + " is not verified on content grid "+cmnPath);
					}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToImport[2], false)) {
						appLog.info(filesToImport[2] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToImport[2] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToImport[2] + " is not verified on content grid "+cmnPath);
					}
				}
				else {
					appLog.error(cmnPath+" is not present on folder structure");
					sa.assertTrue(false, cmnPath+" is not present on folder structure");
					
				}
				filesToImport = filesShared.split("<break>");
				if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToImport[0], false)) {
						appLog.info(filesToImport[0] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToImport[0] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToImport[0] + " is not verified on content grid "+shdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToImport[1], false)) {
						appLog.info(filesToImport[1] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToImport[1] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToImport[1] + " is not verified on content grid "+shdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToImport[2], false)) {
						appLog.info(filesToImport[2] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToImport[2] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToImport[2] + " is not verified on content grid "+shdPath);
					}
				}
				else {
					appLog.error(shdPath+" is not present on folder structure");
					sa.assertTrue(false, shdPath+" is not present on folder structure");
					
				}
				
				filesToImport = filesStandard.split("<break>");
				if (fp.verifyFolderPathdummy(stdPath, M9Institution1, null, M9FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToImport[0], false)) {
						appLog.info(filesToImport[0] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToImport[0] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToImport[0] + " is not verified on content grid "+stdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToImport[1], false)) {
						appLog.info(filesToImport[1] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToImport[1] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToImport[1] + " is not verified on content grid "+stdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesToImport[2], false)) {
						appLog.info(filesToImport[2] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToImport[2] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToImport[2] + " is not verified on content grid "+stdPath);
					}
				}
				else {
					appLog.error(stdPath+" is not present on folder structure");
					sa.assertTrue(false, stdPath+" is not present on folder structure");
					
				}
					switchToDefaultContent(driver);
					
			}
		}
		
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc019_VerifySearchingManageApprovals() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileCommon);
		String filesInternal = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileInternal);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileStandard);
		String filesToSearch[] = filesCommon.split("<break>");
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		int size;
		String grid[][]=new String[12][12];
		//filling data in grid
		for (int j = 0;j<3;j++) {
			grid[j][0] = filesCommon.split("<break>")[j];
			grid[j][1] = "pending";
			grid[j][2] = M9FundName1+" > "+cmnPath;
			
		}
		for (int j=3;j<6;j++) {
			int i = j-3;
			grid[j][0] = filesShared.split("<break>")[i];
			grid[j][1] = "pending";
			grid[j][2] = M9FundName1+" > "+ shdPath;
			
		}
		for (int j=6;j<9;j++) {
			int i = j-6;
			grid[j][0] = filesStandard.split("<break>")[i];
			grid[j][1] = "pending";
			grid[j][2] = M9FundName1+" > "+ M9Institution1+" > " +stdPath;
			
		}
		for (int j=9;j<12;j++) {
			int i = j-9;
			grid[j][0] = filesStandard.split("<break>")[i];
			grid[j][1] = "pending";
			grid[j][2] = M9FundName1+" > "+ M9Institution2+" > " +stdPath;
			
		}
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 30), filesToSearch[0], "search box pending tabs", action.BOOLEAN)) {
						if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 30), "pending docs manage approvals", action.SCROLLANDBOOLEAN)) {
							
							if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,filesToSearch[0], M9FundName1+" > "+cmnPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info(filesCommon.split("<break>")[0] + " is successfully present in manage approvals window");
							}
							else {
							appLog.error(filesCommon.split("<break>")[0] + "could not be verified in manage approvals window");
							sa.assertTrue(false, filesCommon.split("<break>")[0] + "could not be verified in manage approvals window");
							}
							if (click(driver,fp.getSearchIconCrossButtonManageApprovals(ManageApprovalTabs.PendingDocuments,60) , "search icon cross button", action.BOOLEAN)) {
								
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
								ThreadSleep(5000);
								List<WebElement> ele = FindElements(driver, "//span[contains(@id,'pendingGrid-cell-1')]//a", "list of file names manage approvals");
								size = ele.size();
								/*
								 for (int i = 0;i<size;i++) {

									if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,grid[i][0], grid[i][2], grid[i][1],CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
										appLog.info("successfully verified "+grid[i][0] + " "+grid[i][1]+" "+grid[i][2]);
									}
									else {
										appLog.error(grid[i][0] + " file contents are wrong");
											sa.assertTrue(false, grid[i][0] + " file contents are wrong");
										}
									}
								 */
								boolean flag = false;
								//verifying data on grid
								for (int i = 0;i<size;i++) {

									if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,grid[i][0], grid[i][2], grid[i][1],CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
										appLog.info("successfully verified "+grid[i][0] + " "+grid[i][1]+" "+grid[i][2]);
									}
									else {
										int j = 0;
										flag = false;
										while(j<2) {
											click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments,60), "document name manage approvals", action.BOOLEAN);
											if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,grid[i][0], grid[i][2], grid[i][1],CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
												flag = true;
												appLog.info("successfully verified "+grid[i][0] + " "+grid[i][1]+" "+grid[i][2]);
												break;
											}
											j++;
										}
										if (flag == false) {
											appLog.error(grid[i][0] + " file contents are wrong");
											sa.assertTrue(false, grid[i][0] + " file contents are wrong");
										}
									}
								}	
							}
							else {
								appLog.error("cross icon on search textbox is not clickable");
								sa.assertTrue(false, "cross icon on search textbox is not clickable");
							}
						}
						else {
							appLog.error("search icon is not clickable");
							sa.assertTrue(false, "search icon is not clickable");
						}
					}
					else {
						appLog.error("search textbox is not visible on manage approvals window");
						sa.assertTrue(false, "search textbox is not visible on manage approvals window");
					}
					
					if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 30), ".txt", "search box pending tabs", action.BOOLEAN)) {
						if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 30), "pending docs manage approvals", action.SCROLLANDBOOLEAN)) {
							List<WebElement> a = FindElements(driver, "//span[contains(@id,'pendingGrid-cell-1')]//a", "list of file names manage approvals");
							size = a.size();
							//all files should have .txt at end
							for (int i = 0;i<size;i++) {
								String str = a.get(i).getText().trim();
								if (str.substring(str.length()-4).equals(".txt")) {
									appLog.info(".txt is successfully found at end of file when .txt is searched"+str);
								}
								else {
									appLog.error(str.substring(str.length()-4) +" is not equal to .txt for element at "+i);
									sa.assertTrue(false, str.substring(str.length()-4) +" is not equal to .txt for element at "+i);
								}
							}
							
							//verify 3 files
							if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,filesShared.split("<break>")[0], M9FundName1+" > "+shdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info(filesShared.split("<break>")[0] + " is successfully present in manage approvals window");
							}
							else {
								appLog.error(filesShared.split("<break>")[0]+" is not verified on manage approvals content grid");
								sa.assertTrue(false, filesShared.split("<break>")[0]+" is not verified on manage approvals content grid");
							}
							if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,filesShared.split("<break>")[2],  M9FundName1+" > "+shdPath,"pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info(filesShared.split("<break>")[2] + " is successfully present in manage approvals window");
							}
							else {
								appLog.error(filesShared.split("<break>")[2]+" is not verified on manage approvals content grid");
								sa.assertTrue(false, filesShared.split("<break>")[2]+" is not verified on manage approvals content grid");
							}
							if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,filesStandard.split("<break>")[0],  M9FundName1+" > "+M9Institution1+" > " +stdPath,"pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info(filesStandard.split("<break>")[0] + " is successfully present in manage approvals window");
							}
							else {
								appLog.error(filesStandard.split("<break>")[0]+" is not verified on manage approvals content grid");
								sa.assertTrue(false, filesStandard.split("<break>")[0]+" is not verified on manage approvals content grid");
							}
							if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,filesStandard.split("<break>")[0],  M9FundName1+" > "+M9Institution2+" > " +stdPath,"pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info(filesStandard.split("<break>")[0] + " is successfully present in manage approvals window");
							}
							else {
								appLog.error(filesStandard.split("<break>")[0]+" is not verified on manage approvals content grid");
								sa.assertTrue(false, filesStandard.split("<break>")[0]+" is not verified on manage approvals content grid");
							}
							if (click(driver, fp.getSearchIconCrossButtonManageApprovals(ManageApprovalTabs.PendingDocuments,60), "search icon cross button", action.BOOLEAN)) {

							}
							else {
								appLog.error("cross icon is not clickable on manage approvals search box");
								sa.assertTrue(false, "cross icon is not clickable on manage approvals search box");
							}
						}
						else {
							appLog.error("search icon popup is not clickable");
							sa.assertTrue(false, "search icon popup is not clickable");
						}
					}
					if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 30), filesInternal.split("<break>")[0], "search box pending tabs", action.BOOLEAN)) {
						if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 30), "search icon manage approvals", action.BOOLEAN)) {
							if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
								appLog.info("no data to display text is successfully displayed");
							}
							else {
								appLog.error("no data to display message is not displayed");
								sa.assertTrue(false, "no data to display message is not displayed");
							}
						}
						else {
							appLog.error("search icon is not clickable");
							sa.assertTrue(false, "search icon is not clickable");
						}
					}
					else {
						appLog.error("search textbox is not visible on manage approvals window");
						sa.assertTrue(false, "search textbox is not visible on manage approvals window");
					}
					if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 30), date, "search box pending tabs", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 30), "search icon manage approvals", action.BOOLEAN)) {
							if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
								appLog.info("no data to display text is successfully displayed");
							}
							else {
								appLog.error("no data to display message is not displayed");
								sa.assertTrue(false, "no data to display message is not displayed");
							}
						}
						else {
							appLog.error("search icon is not clickable");
							sa.assertTrue(false, "search icon is not clickable");
						}
					}
					else {
						appLog.error("search textbox is not visible on manage approvals window");
						sa.assertTrue(false, "search textbox is not visible on manage approvals window");
					}
					if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 30),CRMUser1FirstName+" "+CRMUser1LastName, "search box pending tabs", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 30), "search icon manage approvals", action.BOOLEAN)) {
							List<WebElement> ele = FindElements(driver, "//span[contains(@id,'pendingGrid-cell-1')]//a", "list of file names manage approvals");
							size = ele.size();

							boolean flag = false;
							//verifying data on grid
							for (int i = 0;i<size;i++) {
								if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,grid[i][0], grid[i][2], grid[i][1],CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
									appLog.info("successfully verified "+grid[i][0] + " "+grid[i][1]+" "+grid[i][2] + " when entered user name in search box");
								}
								else {
									int j = 0;
									flag = false;
									while(j<2) {
										click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments,60), "document name manage approvals", action.BOOLEAN);
										if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,grid[i][0], grid[i][2], grid[i][1],CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
											flag = true;
											appLog.info("successfully verified "+grid[i][0] + " "+grid[i][1]+" "+grid[i][2]);
											break;
										}
										j++;
									}
									if (flag == false) {
										appLog.error(grid[i][0] + " file contents are wrong");
										sa.assertTrue(false, grid[i][0] + " file contents are wrong");
									}
								}
							}
						}else {
							appLog.error("search icon is not clickable");
							sa.assertTrue(false, "search icon is not clickable");
						}
				}
					else {
						appLog.error("search textbox is not visible on manage approvals window when entered user name in search box");
						sa.assertTrue(false, "search textbox is not visible on manage approvals window when entered user name in search box");
					}
					
					//entering firm name on search textbox
					if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 30),Org1FirmName, "search box pending tabs", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 30), "search icon manage approvals", action.BOOLEAN)) {
							List<WebElement> ele = FindElements(driver, "//span[contains(@id,'pendingGrid-cell-1')]//a", "list of file names manage approvals");
							size = ele.size();

							boolean flag = false;
							//verifying data on grid
							for (int i = 0;i<size;i++) {
								if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,grid[i][0], grid[i][2], grid[i][1],CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
									appLog.info("successfully verified "+grid[i][0] + " "+grid[i][1]+" "+grid[i][2] + " when entered user name in search box");
								}
								else {
									int j = 0;
									flag = false;
									while(j<2) {
										click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments,60), "document name manage approvals", action.BOOLEAN);
										if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,grid[i][0], grid[i][2], grid[i][1],CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
											flag = true;
											appLog.info("successfully verified "+grid[i][0] + " "+grid[i][1]+" "+grid[i][2]);
											break;
										}
										j++;
									}
									if (flag == false) {
										appLog.error(grid[i][0] + " file contents are wrong");
										sa.assertTrue(false, grid[i][0] + " file contents are wrong");
									}
								}
							}
						}
						else {
							appLog.error("search icon is not clickable");
							sa.assertTrue(false, "search icon is not clickable");
						}
					}
					else {
						appLog.error("search textbox is not visible on manage approvals window when entered user name in search box");
						sa.assertTrue(false, "search textbox is not visible on manage approvals window when entered user name in search box");
					}
					if (sendKeys(driver,fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 30) , filesStandard.split("<break>")[0], "search box pending tabs", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 60),"search icon pending documents", action.SCROLLANDBOOLEAN)) {
							if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, filesStandard.split("<break>")[0], M9FundName1+" > "+M9Institution1+" > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info(filesStandard.split("<break>")[0]+" is successfully found in pending documents tab");
							}
							else {
								appLog.error(filesStandard.split("<break>")[0]+" could not be found on pending tabs");
								sa.assertTrue(false, filesStandard.split("<break>")[0]+" could not be found on pending tabs");
								
							}
							if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, filesStandard.split("<break>")[0], M9FundName1+" > "+M9Institution2+" > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info(filesStandard.split("<break>")[0]+" is successfully found in pending documents tab");
							}
							else {
								appLog.error(filesStandard.split("<break>")[0]+" could not be found on pending tabs");
								sa.assertTrue(false, filesStandard.split("<break>")[0]+" could not be found on pending tabs");
								
							}
						}
					else {
						appLog.error("search icon is not clickable");
						sa.assertTrue(false, "search icon is not clickable");
					}
				}
					//verifying all files in approved tab
					if (click(driver, fp.getApprovedDocsTab(60), "approved docs tab", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver,fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 30) , filesStandard.split("<break>")[0], "search box ApprovedDocuments", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 60),"search icon ApprovedDocuments", action.SCROLLANDBOOLEAN)) {
								if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.ApprovedDocuments, filesStandard.split("<break>")[0], M9FundName1+" > "+M9Institution1+" > "+stdPath, "approved", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
									appLog.info(filesStandard.split("<break>")[0]+" is successfully found in pending documents tab");
								}
								else {
									appLog.error(filesStandard.split("<break>")[0]+" could not be found on pending tabs");
									sa.assertTrue(false, filesStandard.split("<break>")[0]+" could not be found on pending tabs");
									
								}
								if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.ApprovedDocuments, filesStandard.split("<break>")[0], M9FundName1+" > "+M9Institution2+" > "+stdPath, "approved", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
									appLog.info(filesStandard.split("<break>")[0]+" is successfully found in pending documents tab");
								}
								else {
									appLog.error(filesStandard.split("<break>")[0]+" could not be found on pending tabs");
									sa.assertTrue(false, filesStandard.split("<break>")[0]+" could not be found on pending tabs");
									
								}
							}
							else {
								appLog.error("search icon on manage approvals tab is not clickable");
								sa.assertTrue(false, "search icon on manage approvals tab is not clickable");
							}
						}
						else {
							appLog.error("search textbox is not visible on approved documents tab");
							sa.assertTrue(false, "search textbox is not visible on approved documents tab");
						}
						
						//common file
						if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 60), filesCommon.split("<break>")[0], "search box approved tabs", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 60),"search icon approved documents", action.SCROLLANDBOOLEAN)) {
								if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.ApprovedDocuments, filesCommon.split("<break>")[0], M9FundName1+" > "+cmnPath, "approved", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
									appLog.info(filesCommon.split("<break>")[0]+" is successfully found in pending documents tab");
								}
								else {
								appLog.error(filesCommon.split("<break>")+" could not be found on manage approvals page");
								sa.assertTrue(false, filesCommon.split("<break>")+" could not be found on manage approvals page");
								}
							}
						}
						//internal file
						if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 60), filesInternal.split("<break>")[0], "search box approved tabs", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 30), "search icon manage approvals", action.BOOLEAN)) {
								if (fp.noDataToDisplay(ManageApprovalTabs.ApprovedDocuments, 30)!=null) {
									appLog.info("no data to display text is successfully displayed");
								}
								else {
									appLog.error("no data to display message is not displayed");
									sa.assertTrue(false, "no data to display message is not displayed");
								}
							}
						}
						//current date
						if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 30), date, "search box ApprovedDocuments", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 30), "search icon manage approvals", action.BOOLEAN)) {
								if (fp.noDataToDisplay(ManageApprovalTabs.ApprovedDocuments, 30)!=null) {
									appLog.info("no data to display text is successfully displayed");
								}
								else {
									appLog.error("no data to display message is not displayed");
									sa.assertTrue(false, "no data to display message is not displayed");
								}
							}
							else {
								appLog.error("search icon is not clickable");
								sa.assertTrue(false, "search icon is not clickable");
							}
						}
						else {
							appLog.error("search textbox is not visible on manage approvals window");
							sa.assertTrue(false, "search textbox is not visible on manage approvals window");
						}
						//shared file
						if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 30), filesShared.split("<break>")[1], "search box ApprovedDocuments", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 30), "search icon manage approvals", action.BOOLEAN)) {
								if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.ApprovedDocuments, filesShared.split("<break>")[1], M9FundName1+" > "+shdPath, "approved", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
									appLog.info(filesShared.split("<break>")[1]+" is successfully found in pending documents tab");
								}
								else {
									appLog.error(filesShared.split("<break>")[1]+" is not displayed");
									sa.assertTrue(false, filesShared.split("<break>")[1]+" is not displayed");
								}
							}
							else {
								appLog.error("search icon is not clickable");
								sa.assertTrue(false, "search icon is not clickable");
							}
						}
						else {
							appLog.error("search textbox is not visible on manage approvals window");
							sa.assertTrue(false, "search textbox is not visible on manage approvals window");
						}
					}
					else {
						appLog.error("approved docs tab is not clickable");
						sa.assertTrue(false, "approved docs tab is not clickable");
					}
					//by default pending documents will be opened
				if (click(driver, fp.getCrossIconManageApp(30), "manage approvals close button", action.SCROLLANDBOOLEAN)) {	
						if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approvals icon on fundraising workspace", action.SCROLLANDBOOLEAN)) {
							if (fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 60).getText().equals("")) {
								appLog.info("search textbox is now empty");
							}
							else {
								appLog.error("search textbox is not empty, it has text "+fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 60).getText());
								sa.assertTrue(false, "search textbox is not empty, it has text "+fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 60).getText());

							}
						}
						else {
							appLog.error("manage approvals icon is not clickable");
							sa.assertTrue(false, "manage approvals icon is not clickable");
						}
					}
					else {
						appLog.error("manage approvals close button is not clickable");
						sa.assertTrue(false, "manage approvals close button is not clickable");
					}
				}
				
				else {
					appLog.error("manage approvals icon is not clickable");
					sa.assertTrue(false, "manage approvals icon is not clickable");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("fund "+M9FundName1+" is not found on funds page");
				sa.assertTrue(false, "fund "+M9FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M9tc020_OpeningOfDocument() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileStandard);
		String parentID=null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
					/*if (!fp.checkVisibilityOfDocumentManageApprovals(ManageApprovalTabs.PendingDocuments, filesCommon.split("<break>")[0])) {
						click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments,60), "document name column", action.SCROLLANDBOOLEAN);
					}
					click(driver, fp.clickOnDocumentLinkOnManageApprovals(ManageApprovalTabs.PendingDocuments, filesCommon.split("<break>")[0], 30), "common 1 document", action.SCROLLANDBOOLEAN);
					*/fp.clickOnDocumentManageApprovals(ManageApprovalTabs.PendingDocuments, filesCommon.split("<break>")[0], 30, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30));
						
					
				parentID = switchOnWindow(driver);
				if (parentID != null) {

					if (fp.getDownloadLink(60) != null) {
						appLog.info("Download Button is displaying");
					} else {
						appLog.info("Document Download Button is not displaying");
						sa.assertTrue(false, "Document Download Button is not displaying");
					}

					if (fp.getDocumentCloseBtn(60) != null) {

						appLog.info("Close Button is displaying");
					} else {
						appLog.info("Close Button is not displaying");
						sa.assertTrue(false, "Close Button is not displaying");
					}
				}
				driver.close();
				driver.switchTo().window(parentID);
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				//clicking on 2nd document(shared)
				if (fp.clickOnDocumentManageApprovals(ManageApprovalTabs.PendingDocuments, filesShared.split("<break>")[0], 30, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30))){
				parentID = switchOnWindow(driver);
				if (parentID != null) {

					if (fp.getDownloadLink(60) != null) {
						appLog.info("Download Button is displaying");
					} else {
						appLog.info("Document Download Button is not displaying");
						sa.assertTrue(false, "Document Download Button is not displaying");
					}

					if (fp.getDocumentCloseBtn(60) != null) {

						appLog.info("Close Button is displaying");
					} else {
						appLog.info("Close Button is not displaying");
						sa.assertTrue(false, "Close Button is not displaying");
					}
					driver.close();
					driver.switchTo().window(parentID);
				}
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				//click on 3rd document(standard)
				if (fp.clickOnDocumentManageApprovals(ManageApprovalTabs.PendingDocuments, filesStandard.split("<break>")[0], 30, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30))){
				parentID = switchOnWindow(driver);
				if (parentID != null) {

					if (fp.getDownloadLink(60) != null) {
						appLog.info("Download Button is displaying");
					} else {
						appLog.info("Document Download Button is not displaying");
						sa.assertTrue(false, "Document Download Button is not displaying");
					}

					if (fp.getDocumentCloseBtn(60) != null) {

						appLog.info("Close Button is displaying");
					} else {
						appLog.info("Close Button is not displaying");
						sa.assertTrue(false, "Close Button is not displaying");
					}
					driver.close();
					driver.switchTo().window(parentID);
				}
				}
				
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				ThreadSleep(5000);
				//clicking on approved docs tab
				if (click(driver, fp.getApprovedDocsTab(60), "approved docs tab", action.SCROLLANDBOOLEAN)) {
					if (fp.clickOnDocumentManageApprovals(ManageApprovalTabs.ApprovedDocuments, filesCommon.split("<break>")[0], 30, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30))){
					parentID = switchOnWindow(driver);
					if (parentID != null) {

						if (fp.getDownloadLink(60) != null) {
							appLog.info("Download Button is displaying");
						} else {
							appLog.info("Document Download Button is not displaying");
							sa.assertTrue(false, "Document Download Button is not displaying");
						}

						if (fp.getDocumentCloseBtn(60) != null) {

							appLog.info("Close Button is displaying");
						} else {
							appLog.info("Close Button is not displaying");
							sa.assertTrue(false, "Close Button is not displaying");
						}
					}
					}
					driver.close();
					driver.switchTo().window(parentID);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					//clicking on 2nd document(shared)
					if(fp.clickOnDocumentManageApprovals(ManageApprovalTabs.ApprovedDocuments, filesShared.split("<break>")[0], 30, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30))){
					parentID = switchOnWindow(driver);
					if (parentID != null) {

						if (fp.getDownloadLink(60) != null) {
							appLog.info("Download Button is displaying");
						} else {
							appLog.info("Document Download Button is not displaying");
							sa.assertTrue(false, "Document Download Button is not displaying");
						}

						if (fp.getDocumentCloseBtn(60) != null) {

							appLog.info("Close Button is displaying");
						} else {
							appLog.info("Close Button is not displaying");
							sa.assertTrue(false, "Close Button is not displaying");
						}
						driver.close();
						driver.switchTo().window(parentID);
					}
					}
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					//click on 3rd document(standard)
					if (fp.clickOnDocumentManageApprovals(ManageApprovalTabs.ApprovedDocuments, filesStandard.split("<break>")[0], 30, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30))){
						parentID = switchOnWindow(driver);
						if (parentID != null) {

							if (fp.getDownloadLink(60) != null) {
								appLog.info("Download Button is displaying");
							} else {
								appLog.info("Document Download Button is not displaying");
								sa.assertTrue(false, "Document Download Button is not displaying");
							}

							if (fp.getDocumentCloseBtn(60) != null) {

								appLog.info("Close Button is displaying");
							} else {
								appLog.info("Close Button is not displaying");
								sa.assertTrue(false, "Close Button is not displaying");
							}
							driver.close();
							driver.switchTo().window(parentID);
						}
					}
					else {
						appLog.error("documents is not clickable from manage approvals window");
						sa.assertTrue(false, "documents is not clickable from manage approvals window");
					}
				}
				else {
					appLog.error("approved docs tab is not clickable");
					sa.assertTrue(false, "approved docs tab is not clickable");
				}
				}
				else {
					appLog.error("manage approval icon is not clickable");
					sa.assertTrue(false, "manage approval icon is not clickable");
				}

			}
			else {
				appLog.error(M9FundName1+" is not found on funds page");
				sa.assertTrue(false, M9FundName1+" is not found on funds page");
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

	@Test
	public void M9tc021_DuplicateDocumentError() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileStandard);
		List<String> temp_li = new ArrayList<String>();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon on fundraising workspace", action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getCheckAllDocsManageApprovals(60), "check all documents checkbox", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getApproveBtnManageApprovals(60), "approve button on manage approvals window", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "approve yes button", action.SCROLLANDBOOLEAN)) {
								if (fp.getApproveDuplicateDocsHeadText(60).getText().trim().equals(FundsPageErrorMessage.manageApprovalDuplicateError)) {
									appLog.info("duplicate docs text under manage approvals is successfully verified");
								}
								else {
									appLog.error("duplicate docs text does not match with expected text "+fp.getApproveDuplicateDocsHeadText(60).getText().trim()+" and "+FundsPageErrorMessage.duplicateDocumentsTextUnderHeading);
									sa.assertTrue(false, "duplicate docs text does not match with expected text");
								}
								if (fp.getManageApprovalsUpdateAllDocument(60)!=null) {
									appLog.info("update all button is successfully displayed");
								}
								else {
									appLog.error("update all button is not present");
									sa.assertTrue(false, "update all button is not present");
								}
								if (fp.getManageApprovalsIgnoreAll(60)!=null) {
									appLog.info("ignore all button is successfully displayed");
								}
								else {
									appLog.error("ignore all button is not present");
									sa.assertTrue(false, "ignore all button is not present");
								}
								if (fp.getDuplicateDocsCloseButton(60)!=null) {
									appLog.info("duplicate docs popup close button is successfully displayed");
								}
								else {
									appLog.error("duplicate docs close button is not displayed");
									sa.assertTrue(false, "duplicate docs close button is not displayed");
								}
								if (fp.getDuplicateDocsHeadColumn(60,false)!=null) {
									appLog.info("duplicate docs column is successfully displayed");
								}
								else {
									appLog.error("duplicate docs column is not displayed");
									sa.assertTrue(false, "duplicate docs column is not displayed");
								}
								if (fp.getDuplicateDocsHeadFolderPath(60,false)!=null) {
									appLog.info("folder path column is successfully displayed");
								}
								else {
									appLog.error("duplicate docs folder path column is not displayed");
									sa.assertTrue(false, "duplicate docs folder path column is not displayed");
								}
								//check default sorting
								//column of document names
								List<WebElement> el = fp.duplicateDocColumnList(60);
								if (checkSorting(driver, SortOrder.Assecending, el)) {
									appLog.info("correct sorting for "+SortOrder.Assecending.toString()+" is successfully found");
								}
								else {
									appLog.error("default sorting is not correct for ascending order");
									sa.assertTrue(false, "default sorting is not correct for ascending order");
								}
								click(driver, fp.getDuplicateDocsHeadColumn(60,false), "duplicate documents column head", action.BOOLEAN);
								ThreadSleep(3000);	
								if (fp.verifyDocumentInDuplicateDocManageApprovalPopup(filesCommon.split("<break>")[0]+"/"+ M9FundName1+" > "+cmnPath+
										"<break>"+filesCommon.split("<break>")[1]+"/"+ M9FundName1+" > "+cmnPath+
										"<break>"+filesCommon.split("<break>")[2]+"/"+ M9FundName1+" > "+cmnPath+
										"<break>"+filesShared.split("<break>")[0]+"/"+M9FundName1+" > "+shdPath+
										"<break>"+filesShared.split("<break>")[1]+"/"+M9FundName1+" > "+shdPath+
										"<break>"+filesShared.split("<break>")[2]+"/"+M9FundName1+" > "+shdPath+
										"<break>"+filesStandard.split("<break>")[0]+"/"+M9FundName1+" > "+M9Institution1+" > "+stdPath+
										"<break>"+filesStandard.split("<break>")[1]+"/"+M9FundName1+" > "+M9Institution1+" > "+stdPath+
										"<break>"+filesStandard.split("<break>")[2]+"/"+M9FundName1+" > "+M9Institution1+" > "+stdPath+
										"<break>"+filesStandard.split("<break>")[0]+"/"+M9FundName1+" > "+M9Institution2+" > "+stdPath+
										"<break>"+filesStandard.split("<break>")[1]+"/"+M9FundName1+" > "+M9Institution2+" > "+stdPath+
										"<break>"+filesStandard.split("<break>")[0]+"/"+M9FundName1+" > "+M9Institution2+" > "+stdPath,true).isEmpty()){

								}
								else {
									//click(driver, fp.getDuplicateDocsHeadColumn(60,false), "duplicate documents column head", action.BOOLEAN);
									appLog.error(fp.printAllElementsInList(temp_li)+" are not found");
									sa.assertTrue(false, "could not find a few elements in common");
								}
								
								//verify sorting
								el = fp.duplicateDocColumnList(60);	
								if (checkSorting(driver, SortOrder.Decending, el)) {
										appLog.info("sorting is successfully found in descending order");
									}
									else {
										appLog.error("correct sorting was not found in descending order");
										sa.assertTrue(false, "correct sorting was not found in descending order");
									}
									if (click(driver, fp.getDuplicateDocsHeadColumn(60,true), "duplicate document column", action.BOOLEAN)) {
										el = fp.duplicateDocColumnList(60);
										if (checkSorting(driver, SortOrder.Assecending, el)) {
											appLog.info("sorting is successfully found in ascending order");
										}
										else {
											appLog.error("correct sorting was not found in ascending order");
											sa.assertTrue(false, "correct sorting was not found in ascending order");
										}
									}
									else {
										appLog.error("duplicate docs head column is not clickable");
										sa.assertTrue(false, "duplicate docs head column is not clickable");
									}
								
								//verify sorting folder path column
								if (click(driver, fp.getDuplicateDocsHeadFolderPath(60,false), "folder path head column", action.SCROLLANDBOOLEAN)) {
									el=fp.duplicateDocFolderPathList(60);
									if (checkSorting(driver, SortOrder.Assecending, el)) {
										appLog.info("sorting is successfully found in ascending order folder path");
									}
									else {
										appLog.error("correct sorting was not found in ascending order folder path");
										sa.assertTrue(false, "correct sorting was not found in ascending order folder path");
									}
									if (click(driver, fp.getDuplicateDocsHeadFolderPath(60,true), "folder path head column", action.SCROLLANDBOOLEAN)) {
										el=fp.duplicateDocFolderPathList(60);
										if (checkSorting(driver, SortOrder.Decending, el)) {
											appLog.info("sorting is successfully found in Decending order folder path");
										}
										else {
											appLog.error("correct sorting was not found in Decending order folder path");
											sa.assertTrue(false, "correct sorting was not found in Decending order folder path");
										}
									}
									else {
										appLog.error("folder path column is not clickable");
										sa.assertTrue(false, "folder path column is not clickable");
									}
								}
								if (click(driver, fp.getDuplicateDocsCloseButton(60), "duplicate docs cross icon", action.SCROLLANDBOOLEAN)) {
									if (fp.getApproveConfirmPopupText(60).getText().trim().equals("0 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
										appLog.info("correct approval message is displayed");
									}
									else {
										appLog.error("no of documents approved message is not correct");
										sa.assertTrue(false, "no of documents approved message is not correct");
									}
									if (click(driver, fp.getManageApprovalsApproveConfirmationCrossIcon(60), "Manage Approvals Approve Confirmation Cross Icon", action.BOOLEAN)) {
										//in manage approvals popup, all checkboxes will be checked
										List<WebElement> li = fp.getManageApprovalsAllPendingFilesCheckBoxList();
										for (int i = 0;i<li.size();i++) {
											if (!isSelected(driver, li.get(i),"checkbox for each document manage approvals")) {
												appLog.error("not selected "+i+" checkbox");
												sa.assertTrue(false, "not selected "+i+" checkbox");
											}
										}
										
										//selecting all elements, approving and clicking ignore all
										if (click(driver, fp.getApproveBtnManageApprovals(60), "approve button manage approvals", action.SCROLLANDBOOLEAN)) {
											if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "yes button approve popup", action.BOOLEAN)) {
												if (fp.verifyDocumentInDuplicateDocManageApprovalPopup(filesCommon.split("<break>")[0]+"/"+ M9FundName1+" > "+cmnPath+
														"<break>"+filesCommon.split("<break>")[1]+"/"+ M9FundName1+" > "+cmnPath+
														"<break>"+filesCommon.split("<break>")[2]+"/"+ M9FundName1+" > "+cmnPath+
														"<break>"+filesShared.split("<break>")[0]+"/"+M9FundName1+" > "+shdPath+
														"<break>"+filesShared.split("<break>")[1]+"/"+M9FundName1+" > "+shdPath+
														"<break>"+filesShared.split("<break>")[2]+"/"+M9FundName1+" > "+shdPath+
														"<break>"+filesStandard.split("<break>")[0]+"/"+M9FundName1+" > "+M9Institution1+" > "+stdPath+
														"<break>"+filesStandard.split("<break>")[1]+"/"+M9FundName1+" > "+M9Institution1+" > "+stdPath+
														"<break>"+filesStandard.split("<break>")[2]+"/"+M9FundName1+" > "+M9Institution1+" > "+stdPath+
														"<break>"+filesStandard.split("<break>")[0]+"/"+M9FundName1+" > "+M9Institution2+" > "+stdPath+
														"<break>"+filesStandard.split("<break>")[1]+"/"+M9FundName1+" > "+M9Institution2+" > "+stdPath+
														"<break>"+filesStandard.split("<break>")[2]+"/"+M9FundName1+" > "+M9Institution2+" > "+stdPath,true).isEmpty()){

												}
												else {
													appLog.error(fp.printAllElementsInList(temp_li)+" are not found");
													sa.assertTrue(false, "could not find a few elements in common");
												}
												if (click(driver, fp.getManageApprovalsIgnoreAll(60), "approve ignore all button", action.BOOLEAN)) {
													if (fp.getApproveConfirmPopupText(60).getText().trim().equals("0 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
														appLog.info("correct approval message is displayed");
													}
													else {
														appLog.error("no of documents approved message is not correct");
														sa.assertTrue(false, "no of documents approved message is not correct");
													}
												}
												else {
													appLog.error("ignore all button is not clickable on approve window");
													sa.assertTrue(false, "ignore all button is not clickable on approve window");
												}
												if (click(driver, fp.getManageApprovalsApproveConfirmationCrossIcon(60), "Manage Approvals Approve Confirmation Cross Icon", action.BOOLEAN)) {
													//all 12 checkboxes will be ticked
													li = fp.getManageApprovalsAllPendingFilesCheckBoxList();
													for (int i = 0;i<li.size();i++) {
														if (!isSelected(driver, li.get(i),"checkbox for each document manage approvals")) {
															appLog.error("not selected "+i+" checkbox");
															sa.assertTrue(false, "not selected "+i+" checkbox");
														}
														else {
															appLog.info("checkbox is successfully selected for "+i+"th element");
														}
													}
												}
												else {
													appLog.error("Manage Approvals Approve Confirmation Cross Icon is not clickable");
													sa.assertTrue(false, "Manage Approvals Approve Confirmation Cross Icon is not clickable");
												}
											}
											else {
												appLog.error("manage approval approve yes button is not clickable");
												sa.assertTrue(false, "manage approval approve yes button is not clickable");
											}
										}
										else {
											appLog.error("approve button is not clickable");
											sa.assertTrue(false, "approve button is not clickable");
										}
										//find not more than 10 error
										if (click(driver, fp.getApproveBtnManageApprovals(60), "approve button manage approvals", action.SCROLLANDBOOLEAN)) {
											if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "yes button approve popup", action.BOOLEAN)) {
												boolean flag=true;
												WebElement ele= BaseLib.edriver.findElement(By.cssSelector("a[title=\"Update All\"]"));
												try{
													scrollDownThroughWebelement(driver, ele, "search icon");
													ele.click();
													appLog.info("clicked on search icon");
												}catch(Exception e){
													flag=false;
													appLog.error("Not able to click on search icon");
													BaseLib.sa.assertTrue(false, "Not able to click on search icon");
												}
												if (flag) {
													if (isAlertPresent(driver)) {
														String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
														switchToAlertAndAcceptOrDecline(driver, 10, action.ACCEPT);
														if (msg.trim().equals(FundsPageErrorMessage.manageApprovalApproveMoreThan10)) {
															appLog.info("correct alert that cannot approve more than 10 docs is successfully verified");
														}
														else {
															appLog.error("alert message is not as expected");
															sa.assertTrue(false, "alert message is not as expected");
														}
													}
													else {
														appLog.error("no alert is present when more than 10 docs is selected for approvals");
														sa.assertTrue(false, "no alert is present when more than 10 docs is selected for approvals");
													}
												}
												else {
													appLog.error("update all button is not clickable");
													sa.assertTrue(false, "update all button is not clickable");
												}
											}
											else {
												appLog.error("manage approval approve yes button is not clickable");
												sa.assertTrue(false, "manage approval approve yes button is not clickable");
											}
										}
										else {
											appLog.error("approve button is not clickable");
											sa.assertTrue(false, "approve button is not clickable");
										}
									}
									else {
										appLog.error("confirmation cross icon is not clickable");
										sa.assertTrue(false, "confirmation cross icon is not clickable");
									}
								}
								else {
									appLog.error("duplicate docs cross icon is not clickable");
									sa.assertTrue(false, "duplicate docs cross icon is not clickable");
								}
							}
							else {
								appLog.error("yes button on manage approvals approve popup is not clickable");
								sa.assertTrue(false, "yes button on manage approvals approve popup is not clickable");
							}
						}
						else {
							appLog.error("approve button on manage approvals popup is not clickable");
							sa.assertTrue(false, "approve button on manage approvals popup is not clickable");
						}
					}
					else {
						appLog.error("checkbox to select all documents is not clickable");
						sa.assertTrue(false, "checkbox to select all documents is not clickable");
					}
				}
				else {
					appLog.error("manage approvals icon is not clickable");
					sa.assertTrue(false, "manage approvals icon is not clickable");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not found on funds page");
				sa.assertTrue(false, M9FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc022_UpdateAllAndDuplicateDoc_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileStandard);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		
		
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon on fundraising workspace", action.SCROLLANDBOOLEAN)) {
					if (click(driver,fp.getCheckAllDocsManageApprovals(60), "checkbox to select all boxes", action.BOOLEAN)) {
						//uncheck checkbox in front of 2 common files
						fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[0], M9FundName1, CRMUser1FirstName+" "+CRMUser1LastName, M9FirmName, date);
						if (fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[0])!=null) {
							appLog.info("checkbox is not null");
							if (isSelected(driver,fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[0]) , "checkbox for "+filesCommon.split("<break>")[0])) {
								if (click(driver, fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[0]), "checkbox for "+filesCommon.split("<break>")[0], action.BOOLEAN)) {
									appLog.info("successfully unchecked checkbox of "+filesCommon.split("<break>")[0]);
								}
								else {
									appLog.error("cannot click checkbox for 1st file of common folder");
									sa.assertTrue(false, "cannot click checkbox for 1st file of common folder");
								}
							}
							else {
								appLog.error(filesCommon.split("<break>")[0]+" checkbox is already unselected");
							}
						}
						else {
							appLog.info("checkbox is null");
							click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments, 30), "document name column", action.BOOLEAN);
							if (isSelected(driver,fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[0]) , "checkbox for "+filesCommon.split("<break>")[0])) {
								click(driver, fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[0]), "checkbox for "+filesCommon.split("<break>")[0], action.BOOLEAN);
							}
							else {
								appLog.error(filesCommon.split("<break>")[0]+" checkbox is already selected");
							}
						}
						fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[1], M9FundName1, CRMUser1FirstName+" "+CRMUser1LastName, M9FirmName, date);
						if (fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[1])!=null) {
							if (isSelected(driver,fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[1]) , "checkbox for "+filesCommon.split("<break>")[1])) {
								if (click(driver, fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[1]), "checkbox for "+filesCommon.split("<break>")[1], action.BOOLEAN)) {
									appLog.info("successfully unchecked checkbox of "+filesCommon.split("<break>")[1]);
								}
								else {
									appLog.error("cannot click checkbox for 2nd file on common folder");
									sa.assertTrue(false, "cannot click checkbox for 2nd file on common folder");
								}
							}
							else {
								appLog.error(filesCommon.split("<break>")[1]+" checkbox is already unselected");
							}
						}
						else {
							click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments, 30), "document name column", action.BOOLEAN);
							click(driver, fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[1]), "checkbox for "+filesCommon.split("<break>")[1], action.BOOLEAN);

						}
						//after this the header checkbox will be unchecked
						if (!isSelected(driver, fp.getCheckAllDocsManageApprovals(60),"checkbox to select all docs in manage approvals")) {
							appLog.info("by unchecking 2 files, the head checkbox is unchecked successfully");
						}
						else {
							appLog.error("by unchecking 2 files, header checkbox should have been unchecked, but it is still checked");
							sa.assertTrue(false, "by unchecking 2 files, header checkbox should have been unchecked, but it is still checked");
						}
						if (click(driver, fp.getApproveBtnManageApprovals(60), "approve button on manage approval", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "yes btton on approve popup", action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageApprovalsUpdateAllDocument(60), "update all button approve popup", action.BOOLEAN)) {
									//popup that 10 docs are updated successfully
									if (fp.getApproveConfirmPopupText(60).getText().trim().equals("10 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
										appLog.info("correct approval message is displayed");
									}
									else {
										appLog.error("incorrect message is displayed on approve popup");
										sa.assertTrue(false, "incorrect message is displayed on approve popup");
									}
									if (click(driver, fp.getManageApprovalsApproveConfirmationCrossIcon(60), "Manage Approvals Approve Confirmation Cross Icon", action.BOOLEAN)) {
										if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,filesCommon.split("<break>")[0] +"<break>"+filesCommon.split("<break>")[1], M9FundName1+" > "+cmnPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
											appLog.info("documents are found successfully in manage approvals window");
										}
										else {
											appLog.error(filesCommon.split("<break>")[0]+" are not found in manage approvals window");
											sa.assertTrue(false, filesCommon.split("<break>")[0]+" are not found in manage approvals window");
										}
									}
									else {
										appLog.error("manage approvals cross icon is not clickable");
										sa.assertTrue(false, "manage approvals cross icon is not clickable");
									}
										
								}
								else {
									appLog.error("update all button is not found");
									sa.assertTrue(false, "update all button is not found");
								
								}
							}
							else {
								appLog.error("yes button is not clickable on manage approval window");
								sa.assertTrue(false, "yes button is not clickable on manage approval window");
							}
						}
						else {
							appLog.error("approve button is not clickable");
							sa.assertTrue(false, "approve button is not clickable");
						}
					}
					else {
						appLog.error("checkbox to select all boxes is not present on this page");
						sa.assertTrue(false, "checkbox to select all boxes is not present on this page");
					}
					if (click(driver, fp.getCrossIconManageApp(30), "manage approvals cancel button", action.SCROLLANDBOOLEAN)) {
						appLog.info("cancel button is successfully clicked");
					}
					else {
						appLog.error("cancel button on manage approvals is not clickable");
						sa.assertTrue(false, "cancel button on manage approvals is not clickable");
					}
					if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesCommon.split("<break>")[2], true)) {
							appLog.info(filesCommon.split("<break>")[2]+" file is successfully verified on content grid");
						}
						else {
							appLog.error(filesCommon.split("<break>")[2]+" is not found on content grid");
							sa.assertTrue(false, filesCommon.split("<break>")[2]+" is not found on content grid");
						}
					}
					for (int i = 0;i<3;i++) {
					if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesShared.split("<break>")[i], true)) {
							appLog.info(filesShared.split("<break>")[i]+" is successfully found in content grid");
						}
						else {
							appLog.error(filesShared.split("<break>")[i]+" could not be found in content grid");
							sa.assertTrue(false, filesShared.split("<break>")[i]+" could not be found in content grid");
						}
					}
					
					if (fp.verifyFolderPathdummy(stdPath, M9Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesStandard.split("<break>")[i], true)) {
							appLog.info(filesStandard.split("<break>")[i]+" is successfully found on content grid");
						}
						else {
							appLog.error(filesStandard.split("<break>")[i]+" could not be found in content grid");
							sa.assertTrue(false, filesStandard.split("<break>")[i]+" could not be found in content grid");
						}
					}
					else {
						appLog.error(stdPath+" is not found in folder structure");
						sa.assertTrue(false, stdPath+" is not found in folder structure");
					}
					if (fp.verifyFolderPathdummy(stdPath, M9Institution2, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesStandard.split("<break>")[i], true)) {
							appLog.info(filesStandard.split("<break>")[i]+" is successfully found on content grid");
						}
						else {
							appLog.error(filesStandard.split("<break>")[i]+" could not be found in content grid");
							sa.assertTrue(false, filesStandard.split("<break>")[i]+" could not be found in content grid");
						}
					}
					else {
						appLog.error(stdPath+" is not found in folder structure");
						sa.assertTrue(false, stdPath+" is not found in folder structure");
					}
					}
				}
				else {
					appLog.error("manage approval icon is not clickable");
					sa.assertTrue(false, "manage approval icon is not clickable");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not found on content grid");
				sa.assertTrue(false, M9FundName1+" is not found on content grid");
			}
			
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc022_UpdateAllAndDuplicateDoc_Impact1() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileStandard);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M9Institution1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesCommon.split("<break>")[2], true)) {
						appLog.info(filesCommon.split("<break>")[2]+" is successfully found in content grid");
					}
					else {
						appLog.error(filesCommon.split("<break>")[2]+" could not be found in content grid");
						sa.assertTrue(false, filesCommon.split("<break>")[2]+" could not be found in content grid");
					}
					if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {

						for (int i = 0;i<3;i++) {
							if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesShared.split("<break>")[i], true)) {

							}else {
								appLog.error(filesShared.split("<break>")[i]+" could not be found in content grid");
								sa.assertTrue(false, filesShared.split("<break>")[i]+" could not be found in content grid");
							}
						}
					}
					else {
						appLog.error(shdPath+" is not found in folder structure");
						sa.assertTrue(false, stdPath+" is not found in folder structure");
					}
					if (fp.verifyFolderPathdummy(stdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {

						for (int i = 0;i<3;i++) {
							if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, filesStandard.split("<break>")[i], true)) {
								appLog.info(filesStandard.split("<break>")[i]+" is successfully found in content grid");
							}
							else {
								appLog.error(filesStandard.split("<break>")[i]+" could not be found in content grid");
								sa.assertTrue(false, filesStandard.split("<break>")[i]+" could not be found in content grid");
							}
						}
					}
					else {
						appLog.error(stdPath+" is not found in folder structure");
						sa.assertTrue(false, stdPath+" is not found in folder structure");
					}
					
				}
				else {
					appLog.error(cmnPath+" is not found in folder structure");
					sa.assertTrue(false, cmnPath+" is not found in folder structure");
				}
				switchToDefaultContent(driver);
			}
			if (ip.clickOnTab(TabName.ContactTab)) {
				if (cp.clickOnCreatedContact(M9Contact1FirstName, M9Contact1LastName, null)) {
					switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
					if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesCommon.split("<break>")[2], true)) {
							appLog.info(filesCommon.split("<break>")[2]+" is successfully found in content grid");
						}
						else {
							appLog.error(filesCommon.split("<break>")[2]+" could not be found in content grid");
							sa.assertTrue(false, filesCommon.split("<break>")[2]+" could not be found in content grid");
						}
						
					}
					else {
						appLog.error(cmnPath+" is not found in folder structure");
						sa.assertTrue(false, cmnPath+" is not found in folder structure");
					}
					if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {

						for (int i = 0;i<3;i++) {
							if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesShared.split("<break>")[i], true)) {
								appLog.info(filesShared.split("<break>")[i]+" is successfully found in content grid");
							}
							else {
								appLog.error(filesShared.split("<break>")[i]+" could not be found in content grid");
								sa.assertTrue(false, filesShared.split("<break>")[i]+" could not be found in content grid");
							}
						}
					}
					else {
						appLog.error(shdPath+" is not found in folder structure");
						sa.assertTrue(false, shdPath+" is not found in folder structure");
					}
					if (fp.verifyFolderPathdummy(stdPath, M9Institution1, null, M9FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {

						for (int i = 0;i<3;i++) {
							if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace, filesStandard.split("<break>")[i], true)) {
								appLog.info(filesStandard.split("<break>")[i]+" is successfully found in content grid");
							}
							else {
								appLog.error(filesStandard.split("<break>")[i]+" could not be found in content grid");
								sa.assertTrue(false, filesStandard.split("<break>")[i]+" could not be found in content grid");
							}
						}
					}
					
					else {
						appLog.error(stdPath+" is not found in content grid");
						sa.assertTrue(false, stdPath+" is not found in content grid");
					}
					switchToDefaultContent(driver);
				}
				else {
					appLog.error("contact could not be found on contacts page");
					sa.assertTrue(false, "contact could not be found on contacts page");
				}
			}
			else {
				appLog.error("contacts tab is not clickable");
				sa.assertTrue(false, "contacts tab is not clickable");
			}
			lp.CRMlogout();
			sa.assertAll();
		}
		
	}
	
	@Test
	public void M9tc022_UpdateAllAndDuplicateDoc_Impact2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileStandard);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		SoftAssert saa = new SoftAssert();
		lp.investorLogin(M9Contact1EmailId, adminPassword);
		List<String> fail_list = null;
		if (selectVisibleTextFromDropDown(driver, ifp.getFirmNameDropdownWRTPage(PageName.AllFirmsPage), "All Firms", "All Firms")) {
			ThreadSleep(5000);
			fail_list=af.verifyAlertsOnAllFirmsPage(filesCommon.split("<break>")[2]+"<break>"+filesStandard+"<break>"+filesShared, Org1FirmName, M9FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpload, PageName.AllFirmsPage); 				
			if (fail_list.isEmpty()) {
				appLog.info("successfully verified std and shared files on all firms alerts");
				}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.error("docs could not be verified on all firms page");
					sa.assertTrue(false, "docs could not be verified on all firms page");
				}
				
			}
		}
		else {
			appLog.error("could not find all firms option in dropdown");
			sa.assertTrue(false, "could not find all firms option in dropdown");
		}
		if (selectVisibleTextFromDropDown(driver, ifp.getFirmNameDropdownWRTPage(PageName.AllFirmsPage),Org1FirmName,Org1FirmName)) {
			click(driver, ifp.getRecentActivitiesTab(30), "recent activities tab", action.BOOLEAN);
			fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(filesCommon.split("<break>")[2]+"<break>"+filesStandard+"<break>"+filesShared, M9FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities, activityType.DocumentUpload, PageName.AllFirmsPage);
			if (fail_list.isEmpty()) {
				appLog.info("successfully verified std and shared files on recent activities page");
					}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.error("could not verify std and shared files alerts on recent activities tab");
					sa.assertTrue(false, "could not verify std and shared files alerts on recent activities tab");
				}
				
			}
			
			click(driver, ifp.getAllDocumentsTab(30), "AllDocuments tab", action.BOOLEAN);
			fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(filesCommon.split("<break>")[2]+"<break>"+filesStandard+"<break>"+filesShared, M9FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments, activityType.DocumentUpload, PageName.AllFirmsPage);
			if (fail_list.isEmpty()) {
				appLog.info("successfully verified std and shared files on recent activities page");
					}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.error("could not verify std and shared files alerts on recent activities tab");
					sa.assertTrue(false, "could not verify std and shared files alerts on recent activities tab");
				}
				
			}
		}
		else {
			appLog.error("could not find firm name in dropdown selection");
			sa.assertTrue(false, "could not find firm name in dropdown selection");
		}
		
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.PotentialInvestmentPage, filesCommon, CRMUser1FirstName+" "+CRMUser1LastName, date);

				sa.combineAssertions(saa);
			}
			else {
				appLog.error(cmnPath+" is not clickable on folder structure");
				sa.assertTrue(false, cmnPath+" is not clickable on folder structure");
			}

			if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.PotentialInvestmentPage, filesShared, CRMUser1FirstName+" "+CRMUser1LastName, date);

				sa.combineAssertions(saa);
			}
			else {
				appLog.error(shdPath+" is not clickable on folder structure");
				sa.assertTrue(false, shdPath+" is not clickable on folder structure");
			}
			if (fp.verifyFolderPathdummy(stdPath, M9Institution1, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.PotentialInvestmentPage, filesStandard, CRMUser1FirstName+" "+CRMUser1LastName, date);

				sa.combineAssertions(saa);
			}
			else {
				appLog.error(stdPath+" is not clickable on folder structure");
				sa.assertTrue(false, stdPath+" is not clickable on folder structure");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Test
	public void M9tc023_VerifyManageApprovalsAfterDeleteFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileCommon);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");

		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30),"manage folder icon on fundraising workspace", action.SCROLLANDBOOLEAN)) {
					if(!cmnPath.isEmpty() && !cmnPath.equalsIgnoreCase("Commonpath")) {
						String id=null;
						id=fp.getCreatedFolderId(cmnPath, PageName.ManageFolderPopUp, 20);
						System.err.println("id>>>>>>"+id);
						if(id!=null) {
							if(fp.clickOnDeleteFolderButton(id)) {
								
								if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), "yes button on delete window", action.BOOLEAN)) {
									appLog.info("Yes button is displaying on folder delete pop up");
								}else {
									appLog.error("Yes button is not displaying on folder delete pop up");
									sa.assertTrue(false, "Yes button is not displaying on folder delete pop up");
								}
								if (click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 30), "close button on manage folder", action.BOOLEAN)) {
									appLog.info("manage folder close button is successfully clicked");
								}
								else {
									appLog.error("manage folder close button is not clickable");
									sa.assertTrue(false, "manage folder close button is not clickable");
								}
							}
							else {
								appLog.error("cannot click on delete button on folder of id "+id);
								sa.assertTrue(false, "cannot click on delete button on folder of id "+id);
							}
						}
						if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[0], M9FundName1+" > "+cmnPath,  CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info(filesCommon.split("<break>")[0]+" common folder documents are successfully found in manage approvals popup pending status");
							}
							else {
								appLog.error(filesCommon.split("<break>")[0]+" common folder documents were not found on manage approvals popup");
								sa.assertTrue(false, filesCommon.split("<break>")[0]+" common folder documents were not found on manage approvals popup");
							}
							
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[1], M9FundName1+" > "+cmnPath,  CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info(filesCommon.split("<break>")[1]+" common folder first 2 documents are successfully found in manage approvals popup pending status");
							}
							else {
								appLog.error(filesCommon.split("<break>")[1]+" common folder first 2 documents were not found on manage approvals popup");
								sa.assertTrue(false, filesCommon.split("<break>")[1]+" common folder first 2 documents were not found on manage approvals popup");
							}
							
						}
						if (click(driver, fp.getCheckAllDocsManageApprovals(60), "checkbox to select all docs manage approvals", action.BOOLEAN)) {
							if (click(driver, fp.getApproveBtnManageApprovals(60), "approve button on manage approvals window", action.BOOLEAN)) {
								if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "yes button on approve window", action.SCROLLANDBOOLEAN)) {
									//folders deleted popup
									if (fp.getDuplicateDocsHeadFolderPath(30, false)!=null) {
										appLog.info("folder path column is successfully displayed on folder delete popup");
									}
									else {
										appLog.error("folder path column is not visible on folder delete popup");
										sa.assertTrue(false, "folder path column is not visible on folder delete popup");
									}
									if (fp.getFolderDeletedText(60).getText().trim().equals(FundsPageErrorMessage.manageApprovalFolderDeleted)) {
										appLog.info("folder deleted error message is successfully found");
									}
									else {
										appLog.error("folder deleted error could not be found");
										sa.assertTrue(false, "folder deleted error could not be found");
									}
									if (fp.getDocumentsHeadOnFolderDeletePopup(60, false)!=null) {
										appLog.info("documents head is successfully found on folder delete popup");
									}
									else {
										appLog.error("documents head cannot be found on folder delete popup");
										sa.assertTrue(false, "documents head cannot be found on folder delete popup");
									}
									if (fp.getFoldersDeletedOkButton(60)!=null) {
										appLog.info("folders delete ok button is successfully present on popup");
									}
									else {
										appLog.error("ok button is not visible on folders delete popup");
										sa.assertTrue(false, "ok button is not visible on folders delete popup");
									}
									if (fp.getFoldersDeletedCrossButton(60)!=null) {
										appLog.info("cross icon on folders delete popup is successfully displayed");
									}
									else {
										appLog.error("cross icon is not visible on folders delete popup");
										sa.assertTrue(false, "cross icon is not visible on folders delete popup");
									}
									if (checkSorting(driver, SortOrder.Assecending, fp.deleteDocsColumnList(60))) {
										appLog.info("default sorting is successfully verified in ascending order");
									}
									else {
										appLog.error("default sorting cannot be verified");
										sa.assertTrue(false, "default sorting cannot be verified");
									}
									click(driver,fp.getDocumentsHeadOnFolderDeletePopup(60, false), "document name on folder delete popup", action.BOOLEAN);
									click(driver,fp.getDocumentsHeadOnFolderDeletePopup(60, false), "document name on folder delete popup", action.BOOLEAN);
									if (fp.verifyDocumentInFolderDeleteWindow(filesCommon.split("<break>")[0]+"/"+M9FundName1+" > "+cmnPath+"<break>"+filesCommon.split("<break>")[1]+"/"+M9FundName1+" > "+cmnPath)) {
										appLog.info("docs on delete window is successfully verified");
									}
									else {
										appLog.error("documents "+filesCommon.split("<break>")[0]+" and "+filesCommon.split("<break>")[1]+" cannot be found on delete window");
										sa.assertTrue(false, "documents "+filesCommon.split("<break>")[0]+" and "+filesCommon.split("<break>")[1]+" cannot be found on delete window");
									}
									//4
									if (click(driver, fp.getDocumentsHeadOnFolderDeletePopup(60, false), "Documents Head On Folder Delete Popup", action.BOOLEAN)) {
										if (checkSorting(driver, SortOrder.Decending, fp.deleteDocsColumnList(60))) {
											appLog.info("successfully verified delete docs column list in descending order");
										}
										else {
											appLog.error("sorting cannot be verified descending order delete docs column list");
											sa.assertTrue(false, "sorting cannot be verified descending order delete docs column list");
										}
										if (click(driver, fp.getDocumentsHeadOnFolderDeletePopup(60, false), "Documents Head On Folder Delete Popup", action.BOOLEAN)) {
											if (checkSorting(driver, SortOrder.Assecending, fp.deleteDocsColumnList(60))) {
												appLog.info("successfully verified delete docs column list in Assecending order");
											}
											else {
												appLog.error("sorting cannot be verified Assecending order delete docs column list");
												sa.assertTrue(false, "sorting cannot be verified Assecending order delete docs column list");
											}
										}
										else {
											appLog.error("documents header column is not clickable");
											sa.assertTrue(false, "documents header column is not clickable");
										}
									}
									else {
										appLog.error("documents header column is not clickable");
										sa.assertTrue(false, "documents header column is not clickable");
									}
									//5
									if (click(driver, fp.getDuplicateDocsHeadFolderPath(30, false), "Documents Head On Folder Delete Popup", action.BOOLEAN)) {
										if (checkSorting(driver, SortOrder.Assecending, fp.folderDeleteFolderPathList(60))) {
											appLog.info("successfully verified delete docs column list in Assecending order");
										}
										else {
											appLog.error("sorting cannot be verified Assecending order delete docs column list");
											sa.assertTrue(false, "sorting cannot be verified Assecending order delete docs column list");
										}
										//6
										if (click(driver, fp.getDuplicateDocsHeadFolderPath(30,true), "Documents Head On Folder Delete Popup", action.BOOLEAN)) {
											if (checkSorting(driver, SortOrder.Decending, fp.folderDeleteFolderPathList(60))) {
												appLog.info("successfully verified delete docs column list in Decending order");
											}
											else {
												appLog.error("sorting cannot be verified Decending order on folder delete docs column list");
												sa.assertTrue(false, "sorting cannot be verified Decending order on folder delete docs column list");
											}
										}
										else {
											appLog.error("documents head on folder delete popup is not clickable");
											sa.assertTrue(false, "documents head on folder delete popup is not clickable");

										}
									}
									else {
										appLog.error("folder path column is not clickable so cannot verify sorting");
										sa.assertTrue(false, "folder path column is not clickable so cannot verify sorting");
									}
									ThreadSleep(5000);
									//7
									if (click(driver, fp.getFoldersDeletedOkButton(60), "ok button folder delete popup", action.BOOLEAN)) {
										if (fp.getApproveConfirmPopupText(60).getText().trim().equals("0 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
											appLog.info("approve success message is found successfully");
										}
										else {
											appLog.error("approve success message cannot be verified");
											sa.assertTrue(false, "approve success message cannot be verified");
										}
										if (click(driver, fp.getManageApprovalsApproveConfirmationCrossIcon(60), "approve confirm cross icon", action.BOOLEAN)) {
											
										}
										
										fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[0], M9FundName1+" > "+cmnPath,  CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date);
										if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, filesCommon.split("<break>")[0], M9FundName1+" > "+cmnPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
											appLog.info(filesCommon.split("<break>")[0]+" common folder documents are successfully found in manage approvals popup pending status");
										}
										else {
											appLog.error(filesCommon.split("<break>")[0]+" common folder documents were not found on manage approvals popup");
											sa.assertTrue(false, filesCommon.split("<break>")[0]+" common folder documents were not found on manage approvals popup");
										}
										
										fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[1], M9FundName1+" > "+cmnPath,  CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date);
										if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, filesCommon.split("<break>")[1], M9FundName1+" > "+cmnPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
											appLog.info(filesCommon.split("<break>")[1]+" common folder first 2 documents are successfully found in manage approvals popup pending status");
										}
										else {
											appLog.error(filesCommon.split("<break>")[1]+" common folder first 2 documents were not found on manage approvals popup");
											sa.assertTrue(false, filesCommon.split("<break>")[1]+" common folder first 2 documents were not found on manage approvals popup");
										}
										
										if (click(driver, fp.getApproveBtnManageApprovals(60), "approve button manage approvals", action.SCROLLANDBOOLEAN)) {
											if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "yes button approve window", action.SCROLLANDBOOLEAN)) {

												if (fp.getDuplicateDocsHeadFolderPath(30, false)!=null) {
													appLog.info("folder path column is successfully displayed on folder delete popup");
												}
												else {
													appLog.error("folder path column is not visible on folder delete popup");
													sa.assertTrue(false, "folder path column is not visible on folder delete popup");
												}
												if (fp.getFolderDeletedText(60).getText().trim().equals(FundsPageErrorMessage.manageApprovalFolderDeleted)) {
													appLog.info("folder deleted error message is successfully found");
												}
												else {
													appLog.error("folder deleted error could not be found");
													sa.assertTrue(false, "folder deleted error could not be found");
												}
												if (fp.getDocumentsHeadOnFolderDeletePopup(60, false)!=null) {
													appLog.info("documents head is successfully found on folder delete popup");
												}
												else {
													appLog.error("documents head cannot be found on folder delete popup");
													sa.assertTrue(false, "documents head cannot be found on folder delete popup");
												}
												if (fp.getFoldersDeletedOkButton(60)!=null) {
													appLog.info("folders delete ok button is successfully present on popup");
												}
												else {
													appLog.error("ok button is not visible on folders delete popup");
													sa.assertTrue(false, "ok button is not visible on folders delete popup");
												}
												if (fp.getFoldersDeletedCrossButton(60)!=null) {
													appLog.info("cross icon on folders delete popup is successfully displayed");
												}
												else {
													appLog.error("cross icon is not visible on folders delete popup");
													sa.assertTrue(false, "cross icon is not visible on folders delete popup");
												}
												if (checkSorting(driver, SortOrder.Assecending, fp.deleteDocsColumnList(60))) {
													appLog.info("default sorting is successfully verified in ascending order");
												}
												else {
													appLog.error("default sorting cannot be verified");
													sa.assertTrue(false, "default sorting cannot be verified");
												}
												click(driver,fp.getDocumentsHeadOnFolderDeletePopup(60, false), "document name on folder delete popup", action.BOOLEAN);
												if (fp.verifyDocumentInFolderDeleteWindow(filesCommon.split("<break>")[0]+"/"+M9FundName1+" > "+cmnPath+"<break>"+filesCommon.split("<break>")[1]+"/"+M9FundName1+" > "+cmnPath)) {
													appLog.info("files are successfully found in folder delete window");
												}
												else {
													appLog.error("files could not be found in folder delete window");
													sa.assertTrue(false, "files could not be found in folder delete window");
												}
												if (click(driver, fp.getFoldersDeletedCrossButton(60), "folder delete cross icon", action.BOOLEAN)) {
													if (fp.getApproveConfirmPopupText(60).getText().trim().equals("0 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
														appLog.info("approve success message is found successfully");
													}
													else {
														appLog.error("approve success message cannot be verified");
														sa.assertTrue(false, "approve success message cannot be verified");
													}
													if (click(driver, fp.getManageApprovalsApproveConfirmationCrossIcon(60), "approve confirmation cross icon", action.SCROLLANDBOOLEAN)) {
														if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, filesCommon.split("<break>")[0]+"<break>"+filesCommon.split("<break>")[1], M9FundName1+" > "+cmnPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
															appLog.info("successfully found files in manage approvals pending document grid");
														}
														else {
															appLog.error("files are not found in manage approvals pendingc doc window");
															sa.assertTrue(false, "files are not found in manage approvals pendingc doc window");
														}
													}
													else {
														appLog.error("approve confirmation cross icon is not clickable");
														sa.assertTrue(false, "approve confirmation cross icon is not clickable");
													}
												}
												else {
													appLog.error("folders delete cross icon is not clickable");
													sa.assertTrue(false, "folders delete cross icon is not clickable");
												}
											}
											else {
												appLog.error("manage approvals yes button is not clickable");
												sa.assertTrue(false, "manage approvals yes button is not clickable");
											}
										}
										else {
											appLog.error("approve button manage approvals is not clickable");
											sa.assertTrue(false, "approve button manage approvals is not clickable");
										}
									}
									else {
										appLog.error("ok button on folders delete popup is not clickable");
										sa.assertTrue(false, "ok button on folders delete popup is not clickable");
									}
								}
								else {
									appLog.error("manage approvals yes button is not clickable");
									sa.assertTrue(false, "manage approvals yes button is not clickable");
								}
							}
							else {
								appLog.error("approve button manage approvals is not clickable");
								sa.assertTrue(false, "approve button manage approvals is not clickable");
							}
						}
						else {
							appLog.error("checkbox for approving all docs is not clickable");
							sa.assertTrue(false, "checkbox for approving all docs is not clickable");
						}

					}
				}
				else {
					appLog.error("manage folder icon is not clickable");
					sa.assertTrue(false, "manage folder icon is not clickable");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not found on funds tab");
				sa.assertTrue(false, M9FundName1+" is not found on funds tab");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc024_CreateSameNameFolderAndVerify() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc018_VerifyOnlineImportManageApprovalOn_Action", excelLabel.UploadedFileStandard);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M9FundName1)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
				if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "Manage folder icon", action.BOOLEAN)){

					if(click(driver, fp.getAllFolderAddIcon(Workspace.FundraisingWorkspace, 30), "Add folder button", action.BOOLEAN)){

						if(click(driver, fp.getFolderTypeRadioButton(FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30),"common radio button",action.BOOLEAN)) {
							appLog.info("Common folder radio button is verified.");
							if(sendKeys(driver, fp.getParentFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30), cmnPath.split(" ")[0], "Parent folder name text box", action.BOOLEAN)){

								if(click(driver, fp.getParentFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30), "Save button", action.BOOLEAN)){
									if (click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 30), "close button on manage folder window", action.SCROLLANDBOOLEAN)) {
										appLog.info("close button on manage folder window is clicked");
									}
									else {
										appLog.error("close button is not clickable");
										sa.assertTrue(false, "close button is not clickable");
									}
								}
								else {
									appLog.error("save button on manage approvals window is not clickable");
									sa.assertTrue(false, "save button on manage approvals window is not clickable");
								}
							}
						else {
							appLog.error("folder name text box is not visible on manage folder window");
							sa.assertTrue(false, "folder name text box is not visible on manage folder window");
						}
						}
						else {
							appLog.error("radio button for common folder is not clickable");
							sa.assertTrue(false, "radio button for common folder is not clickable");
						}
					}
					else {
						appLog.error("add icon for all folders is not clickable");
						sa.assertTrue(false, "add icon for all folders is not clickable");
					}
				}
				else {
					appLog.error("manage folder icon is not clickable");
					sa.assertTrue(false, "manage folder icon is not clickable");
				}
				if (click(driver,fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
					if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, filesCommon.split("<break>")[0]+"<break>"+filesCommon.split("<break>")[1], M9FundName1+" > "+cmnPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
						appLog.info("files are successfully verified on manage approvals ui");
					}
					else {
						appLog.error("files were not found correctly on manage approvals ui");
						sa.assertTrue(false, "files were not found correctly on manage approvals ui");
					}
					
					if (click(driver, fp.getCheckAllDocsManageApprovals(60), "header checkbox manage approvals", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getManageApprovalsApproveBtn(60), "approve button", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "yes button approve popup", action.SCROLLANDBOOLEAN)) {
								if (fp.getFolderDeletedText(60).getText().trim().equals(FundsPageErrorMessage.manageApprovalFolderDeleted)) {
									appLog.info("folder delete popup is successfully verified");
								}
								else {
									appLog.error("folder deleted popup could not be verified");
									sa.assertTrue(false, "folder deleted popup could not be verified");
								}
							}
							else {
								appLog.error("yes button manage approvals is not clickable");
								sa.assertTrue(false, "yes button manage approvals is not clickable");
							}
						}
						else {
							appLog.error("approve button is not clickable");
							sa.assertTrue(false, "approve button is not clickable");
						}
					}
					else {
						appLog.error("checkbox to select all docs is not clickable");
						sa.assertTrue(false, "checkbox to select all docs is not clickable");
					}
				}
				else {
					appLog.error("manage approval icon is not clickable");
					sa.assertTrue(false, "manage approval icon is not clickable");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not clickable");
				sa.assertTrue(false, M9FundName1+" is not clickable");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M9tc025_UploadFilesUser2AndVerify() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		String cmnUpload="UploadFiles\\Module9\\CommonUser2";
		String shdUpload="UploadFiles\\Module9\\SharedUser2";
		String stdUpload="UploadFiles\\Module9\\StandardUser2";
		String User2first = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.MyProfile_FName);
		String User2last= ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.MyProfile_LName);
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
					if (fp.uploadFile(cmnPath, null, cmnUpload, UploadFileActions.Upload, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
						appLog.info("documents are uploaded successfully");
					}
					else {
						appLog.error("documents could not be uploaded successfully");
						sa.assertTrue(false, "documents could not be uploaded successfully");
					}
					if (fp.uploadFile(shdPath, null, shdUpload, UploadFileActions.Upload, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
						appLog.info("documents are uploaded successfully");
					}
					else {
						appLog.error("documents could not be uploaded successfully");
						sa.assertTrue(false, "documents could not be uploaded successfully");
					}
					if (fp.uploadFile(stdPath, M9Institution1+"<break>"+M9Institution2, stdUpload, UploadFileActions.Upload, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
						appLog.info("documents are uploaded successfully");
					}
					else {
						appLog.error("documents could not be uploaded successfully");
						sa.assertTrue(false, "documents could not be uploaded successfully");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "fundraising workspace view");

				String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
				String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared);
				String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.UploadedFileStandard);

				if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
					if (selectVisibleTextFromDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.PendingDocuments, 30), "pending documents dropdown", "All Pending Documents")) {
						for (int i = 0;i<4;i++) {
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30),filesCommon.split("<break>")[i] , M9FundName1+" > "+cmnPath, User2first+" "+User2last, Org1FirmName, date)) {
							appLog.info("common file "+filesCommon.split("<break>")[i]+" is successfully verified on manage approval window");
						}
						else {
							appLog.error(filesCommon.split("<break>")[i]+" could not be verified on manage approvals window");
								sa.assertTrue(false, filesCommon.split("<break>")[i]+" could not be verified on manage approvals window");
						}
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesShared.split("<break>")[i], M9FundName1+" > "+shdPath, User2first+" "+User2last, Org1FirmName, date)) {
							appLog.info("shared file "+filesShared.split("<break>")[i]+" is successfully verified on manage approval window");
						}
						else {
								appLog.error(filesShared.split("<break>")[i]+" could not be verified on manage approvals window");
								sa.assertTrue(false, filesShared.split("<break>")[i]+" could not be verified on manage approvals window");
						}
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[i], M9FundName1+" > "+M9Institution1+" > "+stdPath, User2first+" "+User2last, Org1FirmName, date)) {
							
							appLog.info("std file(inst 1)  "+filesStandard.split("<break>")[i]+" is successfully verified on manage approval window");
						}
						else {
								appLog.error(filesStandard.split("<break>")[i]+" could not be verified on manage approvals window");
								sa.assertTrue(false, filesStandard.split("<break>")[i]+" could not be verified on manage approvals window");
						}
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[i], M9FundName1+" > "+M9Institution2+" > "+stdPath, User2first+" "+User2last, Org1FirmName, date)) {
				
							appLog.info("std files(inst2) "+filesStandard.split("<break>")[i]+" is successfully verified on manage approval window");
						}
						else {
						/*	click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments, 30), "document name column", action.BOOLEAN);
							if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesStandard.split("<break>")[i] , M9FundName1+" > "+M9Institution2+" > "+M9LimitedPartner2+" > "+stdPath, "pending", CRMUser2FirstName+" "+CRMUser2LastName, Org1FirmName, date)) {
								appLog.info("std files(inst2) "+filesStandard.split("<break>")[i]+" is successfully verified on manage approval window");
							}
							else {*/
								appLog.error(filesStandard.split("<break>")[i]+" could not be verified on manage approvals window");
								sa.assertTrue(false, filesStandard.split("<break>")[i]+" could not be verified on manage approvals window");
						}
					}
					}
					else {
						appLog.error("all pending docs cannot be found on dropdown on manage approvals");
						sa.assertTrue(false, "all pending docs cannot be found on dropdown on manage approvals");
					}
					//closing and opening manage approval popup
					click(driver, fp.getManageApprovalsCancelBtn(60),"cancel button on manage approvals", action.BOOLEAN);
					click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN);
					//verify default sorting
					if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalUploadedOnList(ManageApprovalTabs.PendingDocuments))) {
						appLog.info("sorting is successfully verified Decending order on pending docs for uploaded on column");
					}
					if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(6), "uploaded on column", action.SCROLLANDBOOLEAN)) {
						if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalUploadedOnList(ManageApprovalTabs.PendingDocuments))) {
							appLog.info("sorting is successfully verified Assecending order on pending docs for uploaded on column");
						}
						else {
							appLog.error("sorting is incorrect for Assecending uploaded on column");
							sa.assertTrue(false, "sorting is incorrect for Assecending uploaded on column");
						}

						if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(6), "uploaded on column", action.SCROLLANDBOOLEAN)) {
							if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalUploadedOnList(ManageApprovalTabs.PendingDocuments))) {
								appLog.info("sorting is successfully verified Decending order on pending docs for uploaded on column");
							}
							else {
								appLog.error("sorting is incorrect for Decending uploaded on column");
								sa.assertTrue(false, "sorting is incorrect for Decending uploaded on column");
							}
						}
						else {
							appLog.error("uplaoded on column is not clickable");
							sa.assertTrue(false, "uplaoded on column is not clickable");
						}
					}
					else {
						appLog.error("uplaoded on column is not clickable");
						sa.assertTrue(false, "uplaoded on column is not clickable");
					}
					//sorting on uploaded by column
					if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(4), "uploaded by column", action.SCROLLANDBOOLEAN)) {
						if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalUploadedByList(ManageApprovalTabs.PendingDocuments))) {
							appLog.info("sorting is successfully verified Assecending order on pending docs for uploaded by column");
						}
						else {
							appLog.error("sorting is incorrect for Assecending uploaded by column");
							sa.assertTrue(false, "sorting is incorrect for Assecending uploaded by column");
						}

						if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(4), "uploaded by column", action.SCROLLANDBOOLEAN)) {
							if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalUploadedByList(ManageApprovalTabs.PendingDocuments))) {
								appLog.info("sorting is successfully verified Decending order on pending docs for uploaded by column");
							}
							else {
								appLog.error("sorting is incorrect for Decending uploaded by column");
								sa.assertTrue(false, "sorting is incorrect for Decending uploaded by column");
							}
						}
						else {
							appLog.error("uplaoded by column is not clickable");
							sa.assertTrue(false, "uplaoded by column is not clickable");
						}
					}
					else {
						appLog.error("uplaoded by column is not clickable");
						sa.assertTrue(false, "uplaoded by column is not clickable");
					}
					//sorting on document name column
					if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(1), "document name column", action.SCROLLANDBOOLEAN)) {
						if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalDocumentNameList(ManageApprovalTabs.PendingDocuments))) {
							appLog.info("sorting is successfully verified Assecending order on pending docs for document name column");
						}
						else {
							appLog.error("sorting is incorrect for Assecending document name column");
							sa.assertTrue(false, "sorting is incorrect for Assecending document name column");
						}

						if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(1), "document name column", action.SCROLLANDBOOLEAN)) {
							if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalDocumentNameList(ManageApprovalTabs.PendingDocuments))) {
								appLog.info("sorting is successfully verified Decending order on pending docs for document name column");
							}
							else {
								appLog.error("sorting is incorrect for Decending document name column");
								sa.assertTrue(false, "sorting is incorrect for Decending document name column");
							}
						}
						else {
							appLog.error("document name column is not clickable");
							sa.assertTrue(false, "document name column is not clickable");
						}
					}
					else {
						appLog.error("document name column is not clickable");
						sa.assertTrue(false, "document name column is not clickable");
					}
					//sorting on firm name column
					if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(5), "firm name column", action.SCROLLANDBOOLEAN)) {
						if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalFirmNameList(ManageApprovalTabs.PendingDocuments))) {
							appLog.info("sorting is successfully verified Assecending order on pending docs for firm name column");
						}
						else {
							appLog.error("sorting is incorrect for Assecending firm name column");
							sa.assertTrue(false, "sorting is incorrect for Assecending firm name column");
						}

						if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(5), "firm name column", action.SCROLLANDBOOLEAN)) {
							if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalFirmNameList(ManageApprovalTabs.PendingDocuments))) {
								appLog.info("sorting is successfully verified Decending order on pending docs for firm name column");
							}
							else {
								appLog.error("sorting is incorrect for Decending firm name column");
								sa.assertTrue(false, "sorting is incorrect for Decending firm name column");
							}
						}
						else {
							appLog.error("firm name column is not clickable");
							sa.assertTrue(false, "firm name column is not clickable");
						}
					}
					else {
						appLog.error("firm name column is not clickable");
						sa.assertTrue(false, "firm name column is not clickable");
					}
				}
				else {
					appLog.error("manage approval icon is not clickable");
					sa.assertTrue(false, "manage approval icon is not clickable");
				}

				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not found on funds page");
				sa.assertTrue(false, M9FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();

	}
	
	@Test
	public void M9tc026_PendingFilesVerifyDuplicatePopup() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc025_UploadFilesUser2AndVerify", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc025_UploadFilesUser2AndVerify", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M9tc025_UploadFilesUser2AndVerify", excelLabel.UploadedFileStandard);
		String filesApprovedShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc015_UploadManageApprovalOn_Action", excelLabel.UploadedFileShared);
		String User2first = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.MyProfile_FName);
		String User2last= ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.MyProfile_LName);
		
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		String cmnUpload="UploadFiles\\Module9\\CommonUser2";
		String shdUpload="UploadFiles\\Module9\\SharedUser2";
		String stdUpload="UploadFiles\\Module9\\StandardUser2";
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (fp.verifyFolderPathdummy(cmnPath,null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
				if (click(driver, fp.getUploadIcon(Workspace.FundraisingWorkspace, 30), "upload icon", action.SCROLLANDBOOLEAN)) {
					if (fp.verifyDuplicateWindowWhileUploading(Workspace.FundraisingWorkspace,M9Institution1+"<break>"+M9Institution2,cmnUpload, filesCommon, cmnPath,FolderType.Common, UpdateIgnore.Update)) {
						appLog.info("duplicate docs are successfully verified for cmn folder");
					}
					else {
						appLog.error("duplicate docs cannot be verified for cmn folder");
						sa.assertTrue(false, "duplicate docs cannot be verified for cmn folder");
					}
				}
				else {
					appLog.error("upload icon is not clickable");
					sa.assertTrue(false, "upload icon is not clickable");
				}
			}
			else {
				appLog.error(cmnPath+" could not be found in folder structure");
				sa.assertTrue(false, cmnPath+" could not be found in folder structure");
			}
			switchToDefaultContent(driver);
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (fp.verifyFolderPathdummy(shdPath,null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
				if (click(driver, fp.getUploadIcon(Workspace.FundraisingWorkspace, 30), "upload icon", action.SCROLLANDBOOLEAN)) {
					if (fp.verifyDuplicateWindowWhileUploading(Workspace.FundraisingWorkspace,M9Institution1+"<break>"+M9Institution2,shdUpload, filesShared, shdPath,FolderType.Shared, UpdateIgnore.Ignore)) {
						appLog.info("duplicate docs are successfully verified for shd folder");
					}
					else {
						appLog.error("duplicate docs cannot be verified for shd folder");
						sa.assertTrue(false, "duplicate docs cannot be verified for shd folder");
					}
				}
				else {
					appLog.error("upload icon is not clickable");
					sa.assertTrue(false, "upload icon is not clickable");
				}
			}
			else {
				appLog.error(shdPath+" could not be found in folder structure");
				sa.assertTrue(false, shdPath+" could not be found in folder structure");
			}
			switchToDefaultContent(driver);
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			for (int i = 0;i<filesShared.split("<break>").length;i++) {
				if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesShared.split("<break>")[i], false)) {
					appLog.info("file "+filesShared.split("<break>")[i]+" is successfully found");
				}
				else {
					appLog.error("file "+filesShared.split("<break>")[i]+" is not found");
					sa.assertTrue(false, "file "+filesShared.split("<break>")[i]+" is not found");
				}
			}
			for (int i = 0;i<filesApprovedShared.split("<break>").length;i++) {
				if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesApprovedShared.split("<break>")[i], true)) {
					appLog.info("file "+filesApprovedShared.split("<break>")[i]+" is successfully found");
				}
				else {
					appLog.error("file "+filesApprovedShared.split("<break>")[i]+" is not found");
					sa.assertTrue(false, "file "+filesApprovedShared.split("<break>")[i]+" is not found");
				}
			}
			if (fp.verifyFolderPathdummy(stdPath,M9Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
				if (click(driver, fp.getUploadIcon(Workspace.FundraisingWorkspace, 30), "upload icon", action.SCROLLANDBOOLEAN)) {

					if (fp.verifyDuplicateWindowWhileUploading(Workspace.FundraisingWorkspace,M9Institution1+"<break>"+M9Institution2,stdUpload, filesStandard, stdPath,FolderType.Standard, UpdateIgnore.Update)) {
						appLog.info("duplicate docs are successfully verified for std folder");
					}
					else {
						appLog.error("duplicate docs cannot be verified for std folder");
						sa.assertTrue(false, "duplicate docs cannot be verified for std folder");
					}
				}
				else {
					appLog.error("upload icon is not clickable");
					sa.assertTrue(false, "upload icon is not clickable");
				}
			}
			else {
				appLog.error(stdPath+" could not be found in folder structure");
				sa.assertTrue(false, stdPath+" could not be found in folder structure");
			}
			switchToDefaultContent(driver);
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
				if (selectVisibleTextFromDropDown(driver,fp.getManageApprovalsDropdown(ManageApprovalTabs.PendingDocuments, 30), "pending docs dropdown", "All Pending Documents")) {
						for (int i = 0;i<4;i++) {
						
//						if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,filesCommon.split("<break>")[i] , M9FundName1+" > "+cmnPath, "pending", CRMUser2FirstName+" "+CRMUser2LastName, Org1FirmName, date)) {
//							appLog.info("common file "+filesCommon.split("<break>")[i]+" is successfully verified on manage approval window");
//						}
//						else {
//							click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments, 30), "document name column", action.BOOLEAN);
//							if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesCommon.split("<break>")[i] , M9FundName1+" > "+cmnPath, "pending", CRMUser2FirstName+" "+CRMUser2LastName, Org1FirmName, date)) {
//								appLog.info("common file "+filesCommon.split("<break>")[i]+" is successfully verified on manage approval window");
//							}
//						}
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[i], M9FundName1+" > "+cmnPath, User2first+" "+User2last, Org1FirmName, date)) {
							appLog.info("common file "+filesCommon.split("<break>")[i]+" is successfully verified on manage approval window");	
						}
						else {
							appLog.error(filesCommon.split("<break>")[i] + " could not be verified on manage approvals");
							sa.assertTrue(false, filesCommon.split("<break>")[i] + " could not be verified on manage approvals");	
						}

						//if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesShared.split("<break>")[i] , M9FundName1+" > "+shdPath, "pending", CRMUser2FirstName+" "+CRMUser2LastName, Org1FirmName, date)) {
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesShared.split("<break>")[i], M9FundName1+" > "+shdPath, User2first+" "+User2last, Org1FirmName, date)) {
							
						appLog.info("shared file "+filesShared.split("<break>")[i]+" is successfully verified on manage approval window");
						}
						else {
							
							appLog.error(filesShared.split("<break>")[i] + " could not be verified on manage approvals");
							sa.assertTrue(false, filesShared.split("<break>")[i] + " could not be verified on manage approvals");	
					
						}
						//if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,filesStandard.split("<break>")[i] , M9FundName1+" > "+M9Institution1+" > "+stdPath, "pending", CRMUser2FirstName+" "+CRMUser2LastName, Org1FirmName, date)) {
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[i], M9FundName1+" > "+M9Institution1+" > "+stdPath, User2first+" "+User2last, Org1FirmName,date)) {
					
							appLog.info("std file(inst 1)  "+filesStandard.split("<break>")[i]+" is successfully verified on manage approval window");
						}
						else {
							appLog.error(filesStandard.split("<break>")[i] + " could not be verified on manage approvals");
							sa.assertTrue(false, filesStandard.split("<break>")[i] + " could not be verified on manage approvals");	
						}
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[i], M9FundName1+" > "+M9Institution2+" > "+stdPath, User2first+" "+User2last, Org1FirmName,date)) {
							appLog.info("std file(inst 2)  "+filesStandard.split("<break>")[i]+" is successfully verified on manage approval window");
						}
						else {
							appLog.error(filesStandard.split("<break>")[i] + " could not be verified on manage approvals");
							sa.assertTrue(false, filesStandard.split("<break>")[i] + " could not be verified on manage approvals");	
						}
					}
				}
				else {
					appLog.error("all pending documents is not found in manage approvals popup");
					sa.assertTrue(false, "all pending documents is not found in manage approvals popup");
				}
			}
			else {
				appLog.error("manage approval icon is not clickable");
				sa.assertTrue(false, "manage approval icon is not clickable");
			}
			switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not found on funds tab");
				sa.assertTrue(false, M9FundName1+" is not found on funds tab");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc027_VerifyFolderDeletedAndCountOfDocs() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc025_UploadFilesUser2AndVerify", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc025_UploadFilesUser2AndVerify", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M9tc025_UploadFilesUser2AndVerify", excelLabel.UploadedFileStandard);
		String User2first = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.MyProfile_FName);
		String User2last= ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.MyProfile_LName);
		
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
					for (int i = 2;i<4;i++) {
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[i], M9FundName1+" > "+cmnPath, User2first+" "+User2last, Org1FirmName, date)) {
							if (click(driver, fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[i],User2first+" "+User2last), "checkbox for common 1 file", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on checkbox for "+filesCommon.split("<break>")[i]);
							}
							else {
								appLog.error("checkbox for "+filesCommon.split("<break>")[i]+" is not clickable");
								sa.assertTrue(false, "checkbox for "+filesCommon.split("<break>")[i]+" is not clickable");
							}
						}
						else {
							appLog.error("common file not found on pending docs manage approvals");
							sa.assertTrue(false, "common file not found on pending docs manage approvals");
						}
						//shared
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesShared.split("<break>")[i], M9FundName1+" > "+shdPath, User2first+" "+User2last, Org1FirmName, date)) {
							if (click(driver, fp.checkboxForFileInManageApprovals(filesShared.split("<break>")[i]), "checkbox for "+filesShared.split("<break>")[i]+" file", action.SCROLLANDBOOLEAN)) {
							}

						}
						else {
							appLog.error(filesShared.split("<break>")[i]+" Shared file not found");
							sa.assertTrue(false, filesShared.split("<break>")[i]+" Shared file not found");
						}
						//standard

						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[i], M9Institution1 + " > "+stdPath, User2first+" "+User2last, Org1FirmName, date)) {
							if (click(driver, fp.checkboxForFileInManageApprovals(filesStandard.split("<break>")[i], M9Institution1), "checkbox for "+filesStandard.split("<break>")[i]+" file", action.SCROLLANDBOOLEAN)) {

							}

						}
						else {
							appLog.error(filesStandard.split("<break>")[i]+ " file not found for inst 1");
							sa.assertTrue(false, filesStandard.split("<break>")[i]+" file not found for inst 1");
						}
					}

					for (int i = 2;i<4;i++) {
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[i], M9Institution2 + " > "+stdPath, User2first+" "+User2last, Org1FirmName,date)) {
							if (click(driver, fp.checkboxForFileInManageApprovals(filesStandard.split("<break>")[i],M9Institution2), "checkbox for "+filesStandard.split("<break>")[2]+" file", action.SCROLLANDBOOLEAN)) {

							}
						}
						else {
							appLog.error(filesStandard.split("<break>")[i]+ " file not found for inst 2");
							sa.assertTrue(false, filesStandard.split("<break>")[i]+" file not found for inst 2");
						}
					}
					if (click(driver, fp.getApproveBtnManageApprovals(30), "approve button manage approvals", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "yes button approve", action.SCROLLANDBOOLEAN)) {
							List<String> li = fp.verifyDocumentInDuplicateDocManageApprovalPopup(filesCommon.split("<break>")[2]+"/"+M9FundName1+" > "+cmnPath+"<break>"+filesShared.split("<break>")[2]+"/"+M9FundName1+" > "+shdPath+"<break>"+filesStandard.split("<break>")[2]+"/"+M9FundName1+" > "+M9Institution1+" > "+stdPath,false);
							if (li.isEmpty()) {
								appLog.info("all documents are successfully verified in duplicate docs window");
							}
							else {
								for (int i = 0;i<li.size();i++) {
									appLog.error(li.get(i).split("/")[0] + " is not found correctly, path found is "+li.get(i).split("/")[1]);
								}
							}
							if (click(driver, fp.getManageApprovalsUpdateAllDocument(60), "update all document", action.BOOLEAN)) {
								if (fp.getApproveConfirmPopupText(60).getText().trim().equals("8 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
									appLog.info("confirmation text for approval is verified successfully");
								}
								else {
									appLog.error("confirmation text for approval is incorrect");
									sa.assertTrue(false, "confirmation text for approval is incorrect");
								}
								if (click(driver, fp.getManageApprovalsApproveConfirmationCrossIcon(60), "cross icon for successful approve icon", action.SCROLLANDBOOLEAN)) {
									//verify file not present in pending docs tab
									if (isDisplayed(driver, FindElement(driver, "//a[text()='"+filesCommon.split("<break>")[2]+"']", "common file 3", action.BOOLEAN, 5), "visibility", 5, "common file 3")==null) {
										appLog.info("common 3 file is not displayed successfully in pending documents");
									}
									else {
										appLog.error("common 3 file is being displayed but it should not be displayed");
										sa.assertTrue(false, "common 3 file is being displayed but it should not be displayed");
									}
									if (isDisplayed(driver, FindElement(driver, "//a[text()='"+filesCommon.split("<break>")[3]+"']", "common file 4", action.BOOLEAN, 5), "visibility", 5, "common file 4")==null) {
										appLog.info("common 4 file is not displayed successfully in pending documents");
									}
									else {
										appLog.error("common 4 file is being displayed but it should not be displayed");
										sa.assertTrue(false, "common 4 file is being displayed but it should not be displayed");
									}
									if (isDisplayed(driver, FindElement(driver, "//a[text()='"+filesShared.split("<break>")[2]+"']", "Shared file 3", action.BOOLEAN, 5), "visibility", 5, "Shared file 3")==null) {
										appLog.info("Shared 3 file is not displayed successfully in pending documents");
									}
									else {
										appLog.error("Shared 3 file is being displayed but it should not be displayed");
										sa.assertTrue(false, "Shared 3 file is being displayed but it should not be displayed");
									}
									if (isDisplayed(driver, FindElement(driver, "//a[text()='"+filesShared.split("<break>")[3]+"']", "Shared file 4", action.BOOLEAN, 5), "visibility", 5, "Shared file 4")==null) {
										appLog.info("Shared 4 file is not displayed successfully in pending documents");
									}
									else {
										appLog.error("Shared 4 file is being displayed but it should not be displayed");
										sa.assertTrue(false, "Shared 4 file is being displayed but it should not be displayed");
									}
									if (isDisplayed(driver, FindElement(driver, "//a[text()='"+filesStandard.split("<break>")[2]+"']", "Standard file 3", action.BOOLEAN, 5), "visibility", 5, "Standard file 3")==null) {
										appLog.info("Standard 3 file is not displayed successfully in pending documents");
									}
									else {
										appLog.error("Standard 3 file is being displayed but it should not be displayed");
										sa.assertTrue(false, "Standard 3 file is being displayed but it should not be displayed");
									}
									if (isDisplayed(driver, FindElement(driver, "//a[text()='"+filesStandard.split("<break>")[3]+"']", "Standard file 4", action.BOOLEAN, 5), "visibility", 5, "Standard file 4")==null) {
										appLog.info("Standard 4 file is not displayed successfully in pending documents");
									}
									else {
										appLog.error("Standard 4 file is being displayed but it should not be displayed");
										sa.assertTrue(false, "Standard 4 file is being displayed but it should not be displayed");
									}

								}
								else {
									appLog.error("manage approval approve cross icon is not clickable");
									sa.assertTrue(false, "manage approval approve cross icon is not clickable");
								}
							}
							else {
								appLog.error("update all documents link is not clickable");
								sa.assertTrue(false, "update all documents link is not clickable");
							}
						}

						else {
							appLog.error("yes button is not clickable");
							sa.assertTrue(false, "yes button is not clickable");
						}
					}
					else {
						appLog.error("approve button is not clickable");
						sa.assertTrue(false, "approve button is not clickable");
					}
					
					ThreadSleep(5000);
					if (click(driver, fp.getApprovedDocsTab(60), "approved docs tab", action.BOOLEAN)) {
						ThreadSleep(15000);	
						if (selectVisibleTextFromDropDown(driver,fp.getManageApprovalsDropdown(ManageApprovalTabs.ApprovedDocuments, 30), "approved documents dropdown", "All Approved Documents")) {
							for (int i = 2;i<4;i++) {
								if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.ApprovedDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), filesCommon.split("<break>")[i], M9FundName1+" > "+ cmnPath, null, Org1FirmName, date)) {
									appLog.info( filesCommon.split("<break>")[i]+"found common file successfully in approved docs");
								}
								else {
									appLog.error( filesCommon.split("<break>")[i]+"common file could not be found in approved docs");
									sa.assertTrue(false,  filesCommon.split("<break>")[i]+"common file could not be found in approved docs");
								}
								if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.ApprovedDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), filesShared.split("<break>")[i], M9FundName1+" > "+ shdPath,null, Org1FirmName, date)) {
									appLog.info( filesShared.split("<break>")[i]+"found shared file successfully in approved docs");
								}
								else {
									appLog.error( filesShared.split("<break>")[i]+"shared file could not be found in approved docs");
									sa.assertTrue(false,  filesShared.split("<break>")[i]+"shared file could not be found in approved docs");
								}
								if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.ApprovedDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), filesStandard.split("<break>")[i], M9FundName1+" > "+M9Institution1+" > "+stdPath, null, Org1FirmName, date)) {
									appLog.info( filesStandard.split("<break>")[i]+"found Standard file successfully in approved docs");
								}
								else {
									appLog.error( filesStandard.split("<break>")[i]+"Standard file could not be found in approved docs");
									sa.assertTrue(false,  filesStandard.split("<break>")[i]+"Standard file could not be found in approved docs");
								}
							}
						}
						else {
							appLog.error("all approved documents is not found in dropdown");
							sa.assertTrue(false, "all approved documents is not found in dropdown");
						}
					}
					else {
						appLog.error("approved docs tab is not clickable");
						sa.assertTrue(false, "approved docs tab is not clickable");
					}

				}
				else {
					appLog.error("manage approval icon is not clickable");
					sa.assertTrue(false, "manage approval icon is not clickable");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not found on funds page");
				sa.assertTrue(false, M9FundName1+" is not found on funds page");
			}


		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}

		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M9tc028_1_UpdateFolderNameAndVerify() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc025_UploadFilesUser2AndVerify", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc025_UploadFilesUser2AndVerify", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M9tc025_UploadFilesUser2AndVerify", excelLabel.UploadedFileStandard);
		String updatedcmnPath = cmnPath.split("\\(")[0].trim()+ "UP ("+cmnPath.split("\\(")[1].trim();
		String updatedshdPath = shdPath.split("\\(")[0].trim()+ "UP ("+shdPath.split("\\(")[1].trim();
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
				String id=fp.getCreatedFolderId(shdPath, PageName.ManageFolderPopUp, 20);
				if (id!=null) {
					if(fp.clickOnRenameFolderButton(id)) {
						if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), shdPath.split(" ")[0] + "UP", "shd folder text box", action.BOOLEAN)) {
							if(click(driver, fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), "sub folder save button", action.BOOLEAN)) {
								ThreadSleep(2000);

							}else {
								appLog.error("save button for renaming folder is not clickable");
								sa.assertTrue(false, "save button for renaming folder is not clickable");
							}
						}
						else {
							appLog.error("folder textbox is not visible on web page");
							sa.assertTrue(false, "folder textbox is not visible on web page");
						}
					}
					else {
						appLog.error("rename folder icon is not found");
						sa.assertTrue(false, "rename folder icon is not found");
					}
				}
				/*//TODO rename common
				id=fp.getCreatedFolderId(cmnPath, PageName.ManageFolderPopUp, 20);
				System.err.println("id>>>>>>"+id);
				if(id!=null) {
					if(fp.clickOnRenameFolderButton(id)) {
						if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), cmnPath.split(" ")[0] + "UP", "cmn folder text box", action.BOOLEAN)) {
							if(click(driver, fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), "sub folder save button", action.BOOLEAN)) {
								ThreadSleep(2000);
							}
							else {
								appLog.error("save button for renaming folder is not clickable");
								sa.assertTrue(false, "save button for renaming folder is not clickable");
							}
						}
						else {
							appLog.error("folder textbox is not visible on web page");
							sa.assertTrue(false, "folder textbox is not visible on web page");
						}
					}
					else {
						appLog.error("rename folder icon is not found");
						sa.assertTrue(false, "rename folder icon is not found");
					}
				}
				*/
				id=fp.getCreatedFolderId(stdPath, PageName.ManageFolderPopUp, 20);
				if (id!=null) {
					if(fp.clickOnRenameFolderButton(id)) {
						if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), stdPath+"UP", "shd folder text box", action.BOOLEAN)) {
							if(click(driver, fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), "sub folder save button", action.BOOLEAN)) {
								ThreadSleep(2000);

							}else {
								appLog.error("save button for renaming folder is not clickable");
								sa.assertTrue(false, "save button for renaming folder is not clickable");
							}
						}
						else {
							appLog.error("folder textbox is not visible on web page");
							sa.assertTrue(false, "folder textbox is not visible on web page");
						}
					}
					else {
						appLog.error("rename folder icon is not found");
						sa.assertTrue(false, "rename folder icon is not found");
					}
				}
				if (click(driver, fp.getManageFolderCrossIcon(Workspace.FundraisingWorkspace, 30), "manage folder cross icon", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on manage folder cross icon");
				}
				else {
					appLog.error("cross icon on manage folder is not clickable");
					sa.assertTrue(false, "cross icon on manage folder is not clickable");
				}
				/*if (fp.verifyFolderPathdummy(updatedcmnPath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {

				}
				else {
					appLog.error("cmn folder name is not updated");
					sa.assertTrue(false, "cmn folder name is not updated");
				}*/
				if (fp.verifyFolderPathdummy(updatedshdPath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {

				}
				else {
					appLog.error("shd folder name is not updated");
					sa.assertTrue(false, "shd folder name is not updated");
				}
				if (fp.verifyFolderPathdummy(stdPath+"UP", M9Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {

				}
				else {
					appLog.error("std folder name is not updated");
					sa.assertTrue(false, "std folder name is not updated");
				}
				if (click(driver, fp.getInvestmentInfo(Workspace.FundraisingWorkspace), "investment info link", action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getInvestmentInfoEdit(30), "investment info edit", action.BOOLEAN)) {
						if (sendKeys(driver, fp.getInvestmentInfoFundNameTxtbox(30), M9FundName1+"UP", "fund name textbox", action.BOOLEAN)) {
							if (click(driver, fp.getInvestmentInfoSaveBtn(30), "save button", action.BOOLEAN)) {
								if (click(driver, fp.getInvestmentInfoCancelButton(30), "cancel button investment info", action.BOOLEAN)) {
									
								}
								else {
									appLog.error("cancel button on investment info is not clickable");
									sa.assertTrue(false, "cancel button on investment info is not clickable");
								}
							}
							else {
								appLog.error("save button on investment info is not clickable");
								sa.assertTrue(false, "save button on investment info is not clickable");
							}
						}
						else {
							appLog.error("fund name textbox is not visible on investment info popup");
							sa.assertTrue(false, "fund name textbox is not visible on investment info popup");
							
						}
					}
					else {
						appLog.error("edit icon is not clickable on investment info window");
						sa.assertTrue(false, "edit icon is not clickable on investment info window");
					}
				}
				else {
					appLog.error("investment info link is not clickable");
					sa.assertTrue(false, "investment info link is not clickable");
				}
				switchToDefaultContent(driver);
				scrollDownThroughWebelement(driver, fp.getEditButton(30), "base page edit button");
				if (click(driver, fp.getEditButton(30), "base page edit button", action.SCROLLANDBOOLEAN)) {
					if(sendKeys(driver, fp.getFundName(60), M9FundName1+"NUP", "Fund Name", action.BOOLEAN)){
						if (click(driver, fp.getSaveButton(60), "Save Button funds page", action.BOOLEAN)) {
							String fundNameViewMode =getText(driver, fp.getFundNameInViewMode(60), "Fund Name", action.BOOLEAN);
							if (fundNameViewMode.equalsIgnoreCase(M9FundName1+"NUP")) {
								appLog.info("Fund is edit successfully.:" + M9FundName1+"NUP");
							}
							else {
								appLog.error("fund name is not edited correctly");
								sa.assertTrue(false, "fund name is not edited correctly");
							}
						}
						else {
							appLog.error("save button is not clickable on funds page");
							sa.assertTrue(false, "save button is not clickable on funds page");
						}
							
					}
					else {
						appLog.error("funds name textbox is not visible");
						sa.assertTrue(false, "funds name textbox is not visible");
					}
						
				}
				else {
					appLog.error("edit button is not clickable");
					sa.assertTrue(false, "edit button is not clickable");
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.updateInvestorOrLPNameFromManageInvestor(Workspace.FundraisingWorkspace, M9Institution1, null, M9Institution1+"UP", "M9Institution1")) {
					appLog.info("successfully updated institution1 name");
				}
				else {
					appLog.error("could not update institution name to "+M9Institution1+"NUP");
					sa.assertTrue(false, "could not update institution name to "+M9Institution1+"NUP");
				}
			}
			switchToDefaultContent(driver);
			}
			if (bp.clickOnTab(TabName.InstituitonsTab)) {
				if (ip.clickOnCreatedInstitution(M9Institution1)) {
					if (click(driver, bp.getEditButton(30), "edit button base page", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getLegalNameTextBox(60), M9Institution1+"NUP", "leagl name text box",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("passed data in text box: " + M9Institution1+"NUP");
							if (click(driver, ip.getSaveButton(60), "save button", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on save button");
								String str = getText(driver, ip.getLegalNameLabelTextbox(60), "legal Name Label Text",
										action.SCROLLANDBOOLEAN);
								if (str != null) {
									if (str.contains(M9Institution1+"NUP")) {
										appLog.info(M9Institution1+"UP name is changed successfully.");
									}
								}
								else {
									appLog.error("inst name textbox is not visible");
									sa.assertTrue(false, "inst name textbox is not visible");
								}
							}
							else {
								appLog.error("save button is not clickable");
								sa.assertTrue(false, "save button is not clickable");
							}
						}
						else {
							appLog.error("inst name textbox is not visble on inst page");
							sa.assertTrue(false, "inst name textbox is not visble on inst page");
						}
					}
					else {
						appLog.error("edit button inst page is not clickable");
						sa.assertTrue(false, "edit button inst page is not clickable");
					}
				}
				else {
					appLog.error(M9Institution1+" is not found on inst page");
					sa.assertTrue(false, M9Institution1+" is not found on inst page");
				}
			}
			else {
				appLog.error("inst tab is not clickable");
				sa.assertTrue(false, "inst tab is not clickable");
			}
			if (bp.clickOnTab(TabName.FundsTab)) {
				if (fp.clickOnCreatedFund(M9FundName1)) {
					switchToFrame(driver,30, fp.getFrame( PageName.FundsPage, 30));
					if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getPendingDocsTab(30), "pending documents manage approvals", action.SCROLLANDBOOLEAN)) {
							//file is not present
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[1], M9FundName1+"UP > "+cmnPath, null, Org1FirmName, date)) {
								appLog.info(filesCommon.split("<break>")[1]+" file is successfully verified for updated fund name");
							}
							else {
								appLog.error(filesCommon.split("<break>")[1]+" file could not be verified for updated fund name");
								sa.assertTrue(false, filesCommon.split("<break>")[1]+" file could not be verified for updated fund name");
							}
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesShared.split("<break>")[1], M9FundName1+"UP > "+updatedshdPath, null, Org1FirmName, date)) {
								appLog.info(filesShared.split("<break>")[1]+" file is successfully verified for updated fund name, folder name");
							}
							else {
								appLog.error(filesShared.split("<break>")[1]+" file could not be verified for updated fund name, folder name");
								sa.assertTrue(false, filesShared.split("<break>")[1]+" file could not be verified for updated fund name, folder name");
							}
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[1], M9FundName1+"UP > "+M9Institution1+"UP > "+stdPath+"UP", null, Org1FirmName, date)) {
								appLog.info(filesStandard.split("<break>")[1]+" file is successfully verified for updated fund name, inst name and folder name");
							}
							else {
								appLog.error(filesStandard.split("<break>")[1]+" file could not be verified for updated fund name, inst name and folder name");
								sa.assertTrue(false, filesStandard.split("<break>")[1]+" file could not be verified for updated fund name, inst name and folder name");
							}
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[1], M9FundName1+"UP > "+M9Institution2+" > "+stdPath+"UP", null, Org1FirmName, date)) {
								appLog.info(filesStandard.split("<break>")[1]+" file is successfully verified for updated fund name, inst name and folder name");
							}
							else {
								appLog.error("could not find "+filesStandard.split("<break>")[1]+ " on manage approvals approved docs tab for inst 1");
								sa.assertTrue(false, "could not find "+filesStandard.split("<break>")[1]+ " on manage approvals approved docs tab for inst 1");
							}
							
						}
						if (click(driver, fp.getApprovedDocsTab(30), "approved documents tab", action.SCROLLANDBOOLEAN)) {
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.ApprovedDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), filesCommon.split("<break>")[3],  M9FundName1+"UP > "+cmnPath, null, Org1FirmName, date)) {
								appLog.info(filesCommon.split("<break>")[3]+" (approved docs) file is successfully verified for updated fund name");
							}
							else {
								appLog.error("could not find "+filesCommon.split("<break>")[3]+ " on manage approvals approved docs tab");
								sa.assertTrue(false, "could not find "+filesCommon.split("<break>")[3]+ " on manage approvals approved docs tab");
							}
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.ApprovedDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), filesShared.split("<break>")[3],  M9FundName1+"UP > "+updatedshdPath, null, Org1FirmName, date)) {
								appLog.info(filesShared.split("<break>")[3]+" (approved docs) file is successfully verified for updated fund name");
							}
							else {
								appLog.error("could not find "+filesShared.split("<break>")[3]+ " on manage approvals approved docs tab");
								sa.assertTrue(false, "could not find "+filesShared.split("<break>")[3]+ " on manage approvals approved docs tab");
							}
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.ApprovedDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), filesStandard.split("<break>")[3],  M9FundName1+"UP > "+M9Institution1+"UP > "+stdPath+"UP", null, Org1FirmName, date)) {
								appLog.info(filesStandard.split("<break>")[3]+" (approved docs) file is successfully verified for updated fund name, inst name and folder name");
							}
							else {
								appLog.error("could not find "+filesStandard.split("<break>")[3]+ " on manage approvals approved docs tab for inst 1");
								sa.assertTrue(false, "could not find "+filesStandard.split("<break>")[3]+ " on manage approvals approved docs tab for inst 1");
							}
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.ApprovedDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), filesStandard.split("<break>")[3], M9FundName1+"UP > "+M9Institution2+" > "+stdPath+"UP", null, Org1FirmName, date)) {
								appLog.info(filesStandard.split("<break>")[3]+" (approved docs) file is successfully verified for updated fund name and folder name");
							}
							else {
								appLog.error("could not find "+filesStandard.split("<break>")[3]+ " on manage approvals approved docs tab for inst 2");
								sa.assertTrue(false, "could not find "+filesStandard.split("<break>")[3]+ " on manage approvals approved docs tab for inst 2");
							}
						}
						else {
							appLog.error("approved docs tab is not clickable");
							sa.assertTrue(false, "approved docs tab is not clickable");
						}

					}
					else {
						appLog.error("manage approval icon is not clickable");
						sa.assertTrue(false, "manage approval icon is not clickable");
					}
					switchToDefaultContent(driver);
				}
				else {
					appLog.error(M9FundName1+ " is not found on funds tab");
					sa.assertTrue(false, M9FundName1+ " is not found on funds tab");
				}
			}
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M9tc028_2_RevertRenameOfFundnameAndInstName() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M9Institution1+"NUP")) {
				if (click(driver, bp.getEditButton(30), "edit button base page", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, ip.getLegalNameTextBox(60), M9Institution1, "leagl name text box",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("passed data in text box: " + M9Institution1);
						if (click(driver, ip.getSaveButton(60), "save button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on save button");
							String str = getText(driver, ip.getLegalNameLabelTextbox(60), "legal Name Label Text",
									action.SCROLLANDBOOLEAN);
							if (str != null) {
								if (str.contains(M9Institution1)) {
									appLog.info(M9Institution1+" name is changed successfully.");
								}
							}
							else {
								appLog.error("could not get text from institution name textbox on inst page");
								sa.assertTrue(false, "could not get text from institution name textbox on inst page");
							}
						}
						else {
							appLog.error("save button is not clickable");
							sa.assertTrue(false, "save button is not clickable");
						}
					}
					else {
						appLog.error("inst name textbox is not visble on inst page");
						sa.assertTrue(false, "inst name textbox is not visble on inst page");
					}
				}
				else {
					appLog.error("edit button inst page is not clickable");
					sa.assertTrue(false, "edit button inst page is not clickable");
				}
			}
			else {
				appLog.error(M9Institution1+" is not found on inst page");
				sa.assertTrue(false, M9Institution1+" is not found on inst page");
			}
		}
		else {
			appLog.error("inst tab is not clickable");
			sa.assertTrue(false, "inst tab is not clickable");
		}
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1+"NUP")) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.updateInvestorOrLPNameFromManageInvestor(Workspace.FundraisingWorkspace, M9Institution1+"UP", null, M9Institution1, "M9Institution1")) {
					appLog.info("successfully updated institution1 name");
				}
				else {
					appLog.error("could not update institution name to "+M9Institution1+"NUP");
					sa.assertTrue(false, "could not update institution name to "+M9Institution1+"NUP");
				}
				switchToDefaultContent(driver);
				
				scrollDownThroughWebelement(driver, fp.getEditButton(30), "base page edit button");
				if (click(driver, fp.getEditButton(30), "base page edit button", action.SCROLLANDBOOLEAN)) {
					if(sendKeys(driver, fp.getFundName(60), M9FundName1, "Fund Name", action.BOOLEAN)){
						if (click(driver, fp.getSaveButton(60), "Save Button funds page", action.BOOLEAN)) {
							String fundNameViewMode =getText(driver, fp.getFundNameInViewMode(60), "Fund Name", action.BOOLEAN);
							if (fundNameViewMode.equalsIgnoreCase(M9FundName1)) {
								appLog.info("Fund is edit successfully.:" + M9FundName1);
							}
							else {
								appLog.error("fund name is not edited correctly");
								sa.assertTrue(false, "fund name is not edited correctly");
							}
						}
						else {
							appLog.error("save button is not clickable on funds page");
							sa.assertTrue(false, "save button is not clickable on funds page");
						}
							
					}
					else {
						appLog.error("funds name textbox is not visible");
						sa.assertTrue(false, "funds name textbox is not visible");
					}
						
				}
				else {
					appLog.error("edit button is not clickable");
					sa.assertTrue(false, "edit button is not clickable");
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver, fp.getInvestmentInfo(Workspace.FundraisingWorkspace), "investment info link", action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getInvestmentInfoEdit(30), "investment info edit", action.BOOLEAN)) {
						if (sendKeys(driver, fp.getInvestmentInfoFundNameTxtbox(30), M9FundName1, "fund name textbox", action.BOOLEAN)) {
							if (click(driver, fp.getInvestmentInfoSaveBtn(30), "save button", action.BOOLEAN)) {
								if (click(driver, fp.getInvestmentInfoCancelButton(30), "cancel button investment info", action.BOOLEAN)) {
									
								}
								else {
									appLog.error("cancel button on investment info is not clickable");
									sa.assertTrue(false, "cancel button on investment info is not clickable");
								}
							}
							else {
								appLog.error("save button on investment info is not clickable");
								sa.assertTrue(false, "save button on investment info is not clickable");
							}
						}
						else {
							appLog.error("fund name textbox is not visible on investment info popup");
							sa.assertTrue(false, "fund name textbox is not visible on investment info popup");
							
						}
					}
					else {
						appLog.error("edit icon is not clickable on investment info window");
						sa.assertTrue(false, "edit icon is not clickable on investment info window");
					}
				}
				else {
					appLog.error("investment info link is not clickable");
					sa.assertTrue(false, "investment info link is not clickable");
				}
				switchToDefaultContent(driver);
			}
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M9tc029_VerifySortingManageApproval() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
				if (click(driver, fp.getApprovedDocsTab(30), "approved docs tab", action.BOOLEAN)) {
					//uploaded on ascending
					if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.ApprovedDocuments, 30).get(5), "uploaded on column name", action.SCROLLANDBOOLEAN)) {
						if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalUploadedOnList(ManageApprovalTabs.ApprovedDocuments))) {
							appLog.info("correct sorting is successfully verified for ascending order on uploaded on list");
						}
						else {
							appLog.error("sorting could not be verified for ascending order on uploaded on list");
							sa.assertTrue(false, "sorting could not be verified for ascending order on uploaded on list");
						}
					}
					//uploaded by ascending
					if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.ApprovedDocuments, 30).get(3), "uploaded by column name", action.SCROLLANDBOOLEAN)) {
						if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalUploadedByList(ManageApprovalTabs.ApprovedDocuments))) {
							appLog.info("correct sorting is successfully verified for ascending order on uploaded by list");
						}
						else {
							appLog.error("sorting could not be verified for ascending order on uploaded by list");
							sa.assertTrue(false, "sorting could not be verified for ascending order on uploaded by list");
						}
					}
					//uploaded by descending
					if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.ApprovedDocuments, 30).get(3), "uploaded by column name", action.SCROLLANDBOOLEAN)) {
						if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalUploadedByList(ManageApprovalTabs.ApprovedDocuments))) {
							appLog.info("correct sorting is successfully verified for Decending order on uploaded by list");
						}
						else {
							appLog.error("sorting could not be verified for Decending order on uploaded by list");
							sa.assertTrue(false, "sorting could not be verified for Decending order on uploaded by list");
						}
					}
					//document name ascending
					if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.ApprovedDocuments, 30).get(0), "document name column name", action.SCROLLANDBOOLEAN)) {
						if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalDocumentNameList(ManageApprovalTabs.ApprovedDocuments))) {
							appLog.info("correct sorting is successfully verified for ascending order on document name list");
						}
						else {
							appLog.error("sorting could not be verified for ascending order on document name list");
							sa.assertTrue(false, "sorting could not be verified for ascending order on document name list");
						}
					}
					if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.ApprovedDocuments, 30).get(0), "document name column name", action.SCROLLANDBOOLEAN)) {
						if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalDocumentNameList(ManageApprovalTabs.ApprovedDocuments))) {
							appLog.info("correct sorting is successfully verified for Decending order on document name list");
						}
						else {
							appLog.error("sorting could not be verified for Decending order on document name list");
							sa.assertTrue(false, "sorting could not be verified for Decending order on document name list");
						}
					}
					if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 30), "Manage", "search textbox approved docs", action.BOOLEAN)) {
						/*if (fp.noDataToDisplay(ManageApprovalTabs.ApprovedDocuments, 30).getText().trim().equals(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
							appLog.info("no data is present when searched manage in textbox");
						}
						else {
						 */
						click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 30), "search icon manage approvals", action.BOOLEAN);
							HashSet<String> se = scrollActiveWidgetforSetofFiles(driver, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), By.xpath("//span[contains(@id,'aw') and contains(@id,'cell-1-')]"));
							for (String s:se) {
							if (s.contains("Manage")) {
								appLog.info("manage is found for element "+s);
							}
							else {
								appLog.error("manage is not present in "+s);
								sa.assertTrue(false,"manage is not present in "+s);
							}
						}
						//}
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0,0)",fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30));
							ThreadSleep(3000);
							if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalDocumentNameList(ManageApprovalTabs.ApprovedDocuments))) {
								appLog.info("correct sorting after searching 'Manage' for descending order is successfully found on document names");
							}
							else {
								appLog.error("sorting is not correct after searching 'Manage' for descending order on document names");
								sa.assertTrue(false, "sorting is not correct after searching 'Manage' for descending order on document names");
							}
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0,0)",fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30));
							ThreadSleep(3000);
						if (click(driver, fp.getSearchIconCrossButtonManageApprovals(ManageApprovalTabs.ApprovedDocuments, 30), "cross icon on search textbox", action.BOOLEAN)) {
							if (getText(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 30), "approved docs search box", action.BOOLEAN).equals("")) {
								appLog.info("search box is successfully cleared after clicking cross icon on search box");
							}
							else {
								appLog.error("search box is not empty after clicking cross icon on search box");
								sa.assertTrue(false, "search box is not empty after clicking cross icon on search box");
							}
							if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalDocumentNameList(ManageApprovalTabs.ApprovedDocuments))) {
									appLog.info("correct sorting for descending order is successfully found on document names after clicking cross icon on search box");
								
							}
							else {
									appLog.error("sorting is not correct for descending order on document names after clicking cross icon on search box");
									sa.assertTrue(false, "sorting is not correct for descending order on document names after clicking cross icon on search box");
							}
							//firm name sorting
							if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.ApprovedDocuments, 30).get(4), "firm name column", action.SCROLLANDBOOLEAN)) {
								if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalFirmNameList(ManageApprovalTabs.ApprovedDocuments))) {
									appLog.info("sorting is successfully verified Assecending order on ApprovedDocuments for firm name column");
								}
								else {
									appLog.error("sorting is incorrect for Assecending firm name column");
									sa.assertTrue(false, "sorting is incorrect for Assecending firm name column");
								}

								if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.ApprovedDocuments, 30).get(4), "firm name column", action.SCROLLANDBOOLEAN)) {
									if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalFirmNameList(ManageApprovalTabs.ApprovedDocuments))) {
										appLog.info("sorting is successfully verified Decending order on ApprovedDocuments for firm name column");
									}
									else {
										appLog.error("sorting is incorrect for Decending firm name column");
										sa.assertTrue(false, "sorting is incorrect for Decending firm name column");
									}
								}
								else {
									appLog.error("firm name column is not clickable");
									sa.assertTrue(false, "firm name column is not clickable");
								}
							}
							else {
								appLog.error("firm name column is not clickable");
								sa.assertTrue(false, "firm name column is not clickable");
							}
						}
					}
					else {
						appLog.error("search icon is not visible on manage approval window");
						sa.assertTrue(false, "search icon is not visible on manage approval window");
					}
				}
				if (click(driver, fp.getPendingDocsTab(30), "pending docs tab", action.BOOLEAN)) {
					ThreadSleep(3000);
					if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(4), "uploaded by column", action.BOOLEAN)) {
						ThreadSleep(3000);
						if (click(driver, fp.getApprovedDocsTab(30), "approved docs tab", action.BOOLEAN)) {
							if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalUploadedOnList(ManageApprovalTabs.ApprovedDocuments))) {
								appLog.info("correct sorting is successfully verified for Decending order on uploaded on list");
							}
							else {
								appLog.error("sorting could not be verified for Decending order on uploaded on list");
								sa.assertTrue(false, "sorting could not be verified for Decending order on uploaded on list");
							}
						}
						if (click(driver, fp.getPendingDocsTab(30), "pending docs tab", action.BOOLEAN)) {
							if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalUploadedByList(ManageApprovalTabs.PendingDocuments))) {
								appLog.info("correct sorting is successfully verified for ascending order on uploaded by list");
							}
							else {
								appLog.error("sorting could not be verified for ascending order on uploaded by list");
								sa.assertTrue(false, "sorting could not be verified for ascending order on uploaded by list");
							}
						}
						else {
							appLog.error("pending docs tab is not clickable");
							sa.assertTrue(false, "pending docs tab is not clickable");
						}
					}
					else {
						appLog.error("uploaded by column is not clickable");
						sa.assertTrue(false, "uploaded by column is not clickable");
					}
				}
				else {
					appLog.error("pending docs tab is not clickable");
					sa.assertTrue(false, "pending docs tab is not clickable");
				}
			}
			else {
				appLog.error("manage approval icon is not clickable");
				sa.assertTrue(false, "manage approval icon is not clickable");
			}
			switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not found on funds page");
				sa.assertTrue(false, M9FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc030_UploadDocumentInvestorSide_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String docpath = "UploadFiles\\Module9\\investorSide";
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		SoftAssert saa = new SoftAssert();
		lp.investorLogin(M9Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (fp.verifyFolderPathdummy(stdPath+"UP", M9Institution1, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				if (ifp.uploadUpdateFileInvestorSide(stdPath+"UP",filesStandard, M9Institution1, null, FolderType.Standard, docpath, null, 30,
						PageName.PotentialInvestmentPage, null, null, WorkSpaceAction.UPLOAD)) {
					appLog.info("successfully uploaded file to investor side");
				}
				else {
					appLog.error("could not upload file on investor side"); 
					sa.assertTrue(false, "could not upload file on investor side");
					
				}
				click(driver,ifp.getRefreshIcon(30), "content grid refresh", action.BOOLEAN);
				saa =ifp.verifyContentGridInvestorSide(driver, PageName.PotentialInvestmentPage, filesStandard, M9Contact1FirstName+" "+M9Contact1LastName, date) ;
				sa.combineAssertions(saa);
				
			}
			else {
				appLog.error("could not find "+stdPath+"UP on folder structure");
				sa.assertTrue(false, "could not find "+stdPath+"UP on folder structure");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M9tc030_UploadDocumentInvestorSide_Impact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M9tc030_UploadDocumentInvestorSide_Action", excelLabel.UploadedFileStandard);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (fp.verifyFolderPathdummy(stdPath+"UP", M9Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
				if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace, filesStandard,true)) {
					appLog.info("investor side uploaded file is successfully found on content grid");
				}
				else {
					appLog.error("investor side uploaded file could not be found on content grid");
					sa.assertTrue(false, "investor side uploaded file could not be found on content grid");
				}
				if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getApprovedDocsTab(30), "approved docs tab", action.SCROLLANDBOOLEAN)) {
						/*if (!fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.ApprovedDocuments, filesStandard, M9Institution1+"UP > "+M9LimitedPartner1, "approved", M9Contact1FirstName+" "+M9Contact1LastName, Org1FirmName, date)) {
							appLog.info("investor uploaded file is not present as expected manage approvals window");
						}
						else {
							appLog.error("file uploaded from investor side is found on manage approvals window but it should not be");
							sa.assertTrue(false, "file uploaded from investor side is found on manage approvals window but it should not be");
						}
						*/HashSet<String> se = scrollActiveWidgetforSetofFiles(driver, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), By.xpath("//span[contains(@id,'aw') and contains(@id,'-cell-1')]//a"));
						boolean flag = false;
						for (String s:se) {
							if (s.equals(filesStandard)) {
								appLog.error("found "+filesStandard+" in approved docs tab but it should not be present");
								flag = true;
								break;
							}
						}
						if (flag == true) {
							sa.assertTrue(false, "found "+filesStandard+" in approved docs tab but it should not be present");
						}
						else if(flag == false) {
							appLog.info(filesStandard + " is not found as expected on approved docs tab");
						}
						if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 30), filesStandard, "search textbox", action.SCROLLANDBOOLEAN)) {
							click(driver,fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 30), "manage approvals search icon", action.SCROLLANDBOOLEAN);
							if (fp.noDataToDisplay(ManageApprovalTabs.ApprovedDocuments, 30).getText().trim().equals(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
								appLog.info("no data to display error message is successfully verified");
							}
							else  {
								appLog.error("no data to display is not found in manage approval window");
								sa.assertTrue(false, "no data to display is not found in manage approval window");
							}
							
						}
						else {
							appLog.error("search text box on approved docs is not visible");
							sa.assertTrue(false, "search text box on approved docs is not visible");
						}
					}
					else {
						appLog.error("approved docs tab is not clickable");
						sa.assertTrue(false, "approved docs tab is not clickable");
					}
				}
				else {
					appLog.error("manage approval icon is not clickable");
					sa.assertTrue(false, "manage approval icon is not clickable");
				}
			}
			else {
				appLog.error(stdPath+"UP is not found on folder structure");
				sa.assertTrue(false, stdPath+"UP is not found on folder structure");
			}
			switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not found on funds page");
				sa.assertTrue(false, M9FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
		
	}

	@Test
	public void M9tc031_VerifyRemovalAndAdditionOfInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M9tc025_UploadFilesUser2AndVerify", excelLabel.UploadedFileStandard);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
		if (fp.clickOnCreatedFund(M9FundName1)) {
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 30), "manage investor icon", action.SCROLLANDBOOLEAN)) {
				if (fp.selectInstitutionOrLP(M9Institution2, Workspace.FundraisingWorkspace, 30)) {
					appLog.info("deselected institution "+M9Institution2+" successfully");
				}
				else {
					appLog.error("chechbox for inst2 is not clickable");
					sa.assertTrue(false, "chechbox for inst2 is not clickable");
				}
				if (click(driver, fp.getManageInvestorDoneButton(Workspace.FundraisingWorkspace, 30), "manage investor done button", action.BOOLEAN)) {

				}
				else {
					appLog.error("done button on manage investor is not clickable");
					sa.assertTrue(false, "done button on manage investor is not clickable");
				}
			}
			if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon", action.BOOLEAN)) {
				if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[1],M9FundName1+" > "+M9Institution2+" > "+stdPath, null, Org1FirmName, date)) {
					appLog.info("successfully verified "+filesStandard.split("<break>")[1]+" file on manage approvals pending window");
				}
				else {
					appLog.error("could not be verified "+filesStandard.split("<break>")[1]+" file on manage approvals pending window");
					sa.assertTrue(false, "could not be verified "+filesStandard.split("<break>")[1]+" file on manage approvals pending window");
				}
				if (click(driver, fp.getApprovedDocsTab(30), "approved docs tab", action.BOOLEAN)) {
					if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.ApprovedDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), filesStandard.split("<break>")[3],M9FundName1+" > "+M9Institution2+" > "+stdPath, null, Org1FirmName, date)) {
						appLog.info("successfully verified "+filesStandard.split("<break>")[3]+" file on manage approvals approved docs window");
					}
					else {
						appLog.error("could not be verified "+filesStandard.split("<break>")[3]+" file on manage approvals approved docs window");
						sa.assertTrue(false, "could not be verified "+filesStandard.split("<break>")[3]+" file on manage approvals approved docs window");
					}
				}
				else {
					appLog.error("approved docs tab is not clickable");
					sa.assertTrue(false, "approved docs tab is not clickable");
				}
			}
			else {
				appLog.error("manage approval icon is not clickable");
				sa.assertTrue(false, "manage approval icon is not clickable");
			}
			switchToDefaultContent(driver);
			}
		
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc032_ManageApprovalsPopupInvestorWorkspace() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		String break_separated = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.KeyWord_For_Search);
		String[] columnNames = break_separated.split("<break>");
		
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
		
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon on fund/workspace", action.SCROLLANDBOOLEAN)) {
					//verify manage approvals ui
					if (fp.getManageAppHeadText(60).getText().trim().equals("Manage Approvals")) {
						appLog.info("manage approvals popup is successfully opened");
					}
					else {
						appLog.error("manage approvals popup cannot be verified as opened");
						sa.assertTrue(false, "manage approvals popup cannot be verified as opened");
					}
					if (fp.getPendingDocsTab(60)!=null) {
						appLog.info("pending docs tab is present on manage app popup");
					}
					else {
						appLog.error("pending docs tab is not present on manage approvals popup");
						sa.assertTrue(false, "pending docs tab is not present on manage approvals popup");
					}
					if (fp.getApprovedDocsTab(60)!=null) {
						appLog.info("approved docs tab is present on manage app popup");
					}else {
						appLog.error("approved docs tab is not present on manage approvals popup");
						sa.assertTrue(false, "approved docs tab is not present on manage approvals popup");
					
					}
					if (fp.getPendingDocsTab(60).getAttribute("class").contains("selected")) {
						appLog.info("pending docs tab is the default selected tab");
					}
					else {
						appLog.error("pending docs tab is not the selected tab");
						sa.assertTrue(false, "pending docs tab is not the selected tab");
					}
					List<WebElement> viewDropContents = allOptionsInDropDrop(driver, fp.getManageAppPendingDropdown(60), "manage approvals view dropdown");
					
					
					if (viewDropContents.size() == 1) {
						appLog.info("view dropdown has size of 1");
						if (viewDropContents.get(0).getText().trim().equals("All Pending Documents")) {
							appLog.info("all pending documents is successfully verified on view dropdown manage approvals");
						}
						else {
							appLog.error("the option in dropdown is not 'all pending docs'");
							sa.assertTrue(false, "the option in dropdown is not 'all pending docs'");
						}
					}
					else {
						appLog.error("the size of dropdown is not 1, it is "+viewDropContents.size());
						sa.assertTrue(false, "the size of dropdown is not 1, it is "+viewDropContents.size());
					}
					if (fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 60)!=null) {
						appLog.info("search textbox is succesfully present on manage approvals window");
					}
					
					else {
						appLog.error("search textbox is not present on manage approvals(pending documents)");
						sa.assertTrue(false, "search textbox is not present on manage approvals(pending documents)");
					}
					if (fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 60)!=null) {
						appLog.info("manage approvals popup search icon is successfully found");
					}
					else {
						appLog.error("search icon is not present on manage approvals(pending documents)");
						sa.assertTrue(false, "search icon is not present on manage approvals(pending documents)");
					}
					List<WebElement> temp_list = fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments,60);
					for (int i = 1;i<temp_list.size();i++) {
						if (!temp_list.get(i).getText().trim().equals(columnNames[i-1])) {
							appLog.error(temp_list.get(i).getText()+" does not match with "+columnNames[i-1]);
							sa.assertTrue(false,temp_list.get(i).getText()+" does not match with "+columnNames[i-1] );
						}
						else {
							appLog.info(temp_list.get(i).getText()+" matches with "+columnNames[i-1]);
						}
					}
					if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
						appLog.info("no data to display error message is successfully verified");
					}
					else {
						appLog.error("no data to display message is not present");
						sa.assertTrue(false, "no data to display message is not present");
					}
					if (fp.getApproveBtnManageApprovals(60)!=null) {
						appLog.info("approve button manage approvals is successfully verified");
					}
					else {
						appLog.error("approve button is not present on manage approvals window");
						sa.assertTrue(false, "approve button is not present on manage approvals window");
					}
					if (fp.getdeleteBtnManageApprovals(60)!=null) {
						appLog.info("delete button manage approvals is successfully verified");
					}
					else {
						appLog.error("delete button is not present on manage approvals window");
						sa.assertTrue(false, "delete button is not present on manage approvals window");
					}
					if (fp.getManageAppCancelBtn(60)!=null) {
						appLog.info("cancel button manage approvals is successfully verified");
					}
					else {
						appLog.error("cancel button not present on manage approvals window");
						sa.assertTrue(false, "cancel button not present on manage approvals window");
					}
					WebElement ele= BaseLib.edriver.findElement(By.cssSelector(".SearchBasedOnAccountAndContacts .icon_btn_search"));
					try{
						scrollDownThroughWebelement(driver, ele, "search icon");
						ele.click();
						appLog.info("clicked on search icon");
					}catch(Exception e){
						appLog.error("Not able to click on search icon");
						BaseLib.sa.assertTrue(false, "Not able to click on search icon");
					}

						ThreadSleep(3000);
						if (isAlertPresent(driver)) {
							String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
							switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
							if (msg.trim().equals(FundsPageErrorMessage.pleaseEnterAValueErrorMessageInManageEmails)) {
								appLog.info("correct error message is verified when search icon is clicked");
							}
							else {
								appLog.error("error alert message is wrong when search icon is clicked");
								sa.assertTrue(false, "error alert message is wrong when search icon is clicked");
							}
						}
						else {
							appLog.error("no alert is present when search icon is clicked");
							sa.assertTrue(false, "no alert is present when search icon is clicked");
						}
					if (click(driver, fp.getCheckAllDocsManageApprovals(60), "checkbox to select all documents in manage approvals", action.SCROLLANDBOOLEAN)) {
						ele= BaseLib.edriver.findElement(By.cssSelector("#ApproveId"));
						try{
							scrollDownThroughWebelement(driver, ele, "Approve icon");
							ele.click();
							appLog.info("clicked on Approve icon");
						}catch(Exception e){
							appLog.error("Not able to click on Approve icon");
							BaseLib.sa.assertTrue(false, "Not able to click on Approve icon");
						}
						
						//click(driver, fp.getApproveBtnManageApprovals(30), "approve btn manage approvals", action.BOOLEAN);
						if (isAlertPresent(driver)) {
							String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
							switchToAlertAndAcceptOrDecline(driver, 30,action.ACCEPT);
							if (msg.trim().equals(FundsPageErrorMessage.manageApprovalNoDocError)) {
								appLog.info("correct error message is verified when checkbox is ticked with no documents manage approvals window");
							}
							else {
								appLog.error("error message is not correct when checkbox is ticked and no documents are there in mange approvals");
								sa.assertTrue(false, "error message is not correct when checkbox is ticked and no documents are there in mange approvals");
							}
						}
						if (isSelected(driver, fp.getCheckAllDocsManageApprovals(60), "checkbox for all documents manage approval")) {
							appLog.info("checkbox for selecting all documents is selected");
						}
						else {
							appLog.error("checkbox for selecting all documents is not selected");
							sa.assertTrue(false, "checkbox for selecting all documents is not selected");
						}
					}
					else {
						appLog.error("checkbox for selecting all documents is not clickable");
						sa.assertTrue(false, "checkbox for selecting all documents is not clickable");
					}
					ele= BaseLib.edriver.findElement(By.cssSelector("#DeleteId"));
					try{
						scrollDownThroughWebelement(driver, ele, "Delete icon");
						ele.click();
						appLog.info("clicked on Delete icon");
					}catch(Exception e){
						appLog.error("Not able to click on Delete icon");
						BaseLib.sa.assertTrue(false, "Not able to click on Delete icon");
					}
					
					//if (click(driver, fp.getdeleteBtnManageApprovals(60), "delete button manage approvals", action.SCROLLANDBOOLEAN)) {
						if (isAlertPresent(driver)) {
							String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
							switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
							if (msg.trim().equals(FundsPageErrorMessage.manageApprovalDelError)) {
								appLog.info("correct error mesage is verified when delete button is clicked with no document");
							}
						}
						else {
							appLog.error("delete error message could not be verified");
							sa.assertTrue(false,"delete error message could not be verified");
						}
						if (isSelected(driver, fp.getCheckAllDocsManageApprovals(60), "checkbox for all documents manage approval")) {
							appLog.info("checkbox for selecting all documents is selected");
						}
						else {
							appLog.error("checkbox for selecting all documents is not selected");
							sa.assertTrue(false, "checkbox for selecting all documents is not selected");
						}
					//}
					//else {
					//	appLog.error("delete button is not clickable on manage approvals window");
					//	sa.assertTrue(false, "delete button is not clickable on manage approvals window");
					//}
					
					if (click(driver, fp.getManageAppCancelBtn(60), "cancel button manage approvals", action.SCROLLANDBOOLEAN)) {
					}
					else {
						appLog.error("cancel button is not clickable on manage approvals window");
						sa.assertTrue(false, "cancel button is not clickable on manage approvals window");
					}
					if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30),"manage approval icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
						if (!isSelected(driver, fp.getCheckAllDocsManageApprovals(60), "checkbox for all documents manage approvals")) {
							appLog.info("checkbox for selecting all documents is not selected");
						}
						else {
							appLog.error("checkbox for selecting all documents is selected, but it should not be");
							sa.assertTrue(false, "checkbox for selecting all documents is selected, but it should not be");
						}
						if (click(driver, fp.getCrossIconManageApp(30), "cross icon manage approvals", action.BOOLEAN)) {
							if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
								
							}
						}
						if (fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 30).getText().equals("")) {
							appLog.info("search textbox is empty as expected");
						}
						else {
							appLog.error("search textbox is not empty but it should be");
							sa.assertTrue(false, "search textbox is not empty but it should be");
						}
						if (click(driver, fp.getApprovedDocsTab(60), "approved docs tab manage approvals", action.SCROLLANDBOOLEAN)) {
							viewDropContents = allOptionsInDropDrop(driver, fp.getManageAppovalsApprovedDocsDropdown(60), "manage approvals view dropdown");
							
							
							if (viewDropContents.size() == 1) {
								appLog.info("view dropdown has size of 1");
								if (viewDropContents.get(0).getText().trim().equals("All Approved Documents")) {
									appLog.info("all pending documents is successfully verified on view dropdown manage approvals");
								}
								else {
									appLog.error("'all approved documents' is not present in manage approvals dropdown");
									sa.assertTrue(false, "'all approved documents' is not present in manage approvals dropdown");
								}
							}
							else {
								appLog.error("dropdown for all approved documents does not contain 1 element, it has "+viewDropContents.size());
								sa.assertTrue(false, "dropdown for all approved documents does not contain 1 element, it has "+viewDropContents.size());
							}
							if (fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 30)!=null) {
								appLog.info("approved documents search textbox is visible on manage approvals popup");
							}
							else {
								appLog.error("search box is not present on approved documents tab");
								sa.assertTrue(false, "search box is not present on approved documents tab");
							}
							if (fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 30)!=null) {
								appLog.info("approved documents search box is successfully visible on manage approvals popup");
							}
							else {
								appLog.error("search icon is not present on approved documents tab");
								sa.assertTrue(false, "search icon is not present on approved documents tab");
							}
							temp_list = fp.getColumnListManageApproval(ManageApprovalTabs.ApprovedDocuments,60);
							for (int i = 0;i<temp_list.size();i++) {
								if (!temp_list.get(i).getText().trim().equals(columnNames[i])) {
									appLog.error(temp_list.get(i).getText()+" does not match with "+columnNames[i]);
									sa.assertTrue(false,temp_list.get(i).getText()+" does not match with "+columnNames[i] );
								}
								else {
									appLog.info(temp_list.get(i).getText()+" matches with "+columnNames[i]);
								}
							}
							if (fp.noDataToDisplay(ManageApprovalTabs.ApprovedDocuments, 60)!=null) {
								appLog.info("no data to display error message is successfully verified");
							}
							else {
								appLog.error("no data to display message is not present");
								sa.assertTrue(false, "no data to display message is not present");
							}
							
							if (fp.getCloseBtnApprovedDocs(60)!=null) {
								appLog.info("close button is successfully verified on manage approval window");
							}
							else {
								appLog.error("close button is not present on approved documents window");
								sa.assertTrue(false, "close button is not present on approved documents window");
							}
							if (fp.getCrossIconManageApp(30)!=null) {
								appLog.info("cross icon of manage approval is successfully found");
							}
							else {
								appLog.error("cross icon is not present when approved docs is opened");
								sa.assertTrue(false, "cross icon is not present when approved docs is opened");
							}
							
							//now checking functionality of close button
							if (click(driver, fp.getCloseBtnApprovedDocs(60),"close button manage approval window" , action.SCROLLANDBOOLEAN)) {
								if (click(driver,fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30),"manage approval icon fund/workspace",action.SCROLLANDBOOLEAN)) {
									if (fp.getPendingDocsTab(30).getAttribute("class").contains("selected")) {
										appLog.info("pending docs is successfully selected");
									}
									else {
										appLog.error("pending docs is not the selected tab");
										sa.assertTrue(false, "close button is not present on approved documents window");
									}
									if (click(driver, fp.getApprovedDocsTab(60), "approved docs tab", action.BOOLEAN)) {
										if (fp.getApprovedDocsTab(60).getAttribute("class").contains("selected")) {
											appLog.info("approved docs is verified as the seelcted tab");
										}
										else {
											appLog.error("approved docs is not the selected tab");
											sa.assertTrue(false, "approved docs is not the selected tab");
										}
										if (click(driver, fp.getCrossIconManageApp(60), "cross icon manage approval", action.SCROLLANDBOOLEAN)) {
											appLog.info("close manage approvals window");
										}
										else {
											appLog.error("close button is not present on approved documents window");
											sa.assertTrue(false, "close button is not present on approved documents window");
										}
											
									}
									else {
										appLog.error("approved docs tab is not clicakble");
										sa.assertTrue(false, "approved docs tab is not clicakble");
									}
								}
								else {
									appLog.error("manage approval icon is not clickable on fundraisign workspace");
									sa.assertTrue(false, "manage approval icon is not clickable on fundraisign workspace");
								}
								}
							else {
								appLog.error("close button is not clickable");
								sa.assertTrue(false, "close button is not clickable");
							}
							}
						else {
							appLog.error("approved docs tab is not clickable");
							sa.assertTrue(false, "approved docs tab is not clickable");
						}
						}
					else {
						appLog.error("manage approval icon is not clickable on fundraising workspace");
						sa.assertTrue(false, "manage approval icon is not clickable on fundraising workspace");
					}
					}
				else {
					appLog.error("manage approval icon is not clickable on fundraising workspace");
					sa.assertTrue(false, "manage approval icon is not clickable on fundraising workspace");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("fund "+M9FundName1+" is not clickable");
				sa.assertTrue(false, "fund "+M9FundName1+" is not clickable");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc033_InviteContactInvestorWorkpsace() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				if(fp.inviteContact(environment,mode,M9Institution1,  M9Contact1EmailId,null, FolderType.Standard,"Upload","Yes", "No","All Folders", Workspace.InvestorWorkspace, null)) {
					appLog.info(M9Contact1EmailId+" is successfully invited to "+M9Institution1);
				}
				else {
					appLog.error(M9Contact1EmailId+" could not be given access to "+M9Institution1);
					sa.assertTrue(false, M9Contact1EmailId+" could not be given access to "+M9Institution1);
				}
				if(fp.inviteContact(environment,mode,M9Institution2, M9Contact1EmailId,null, FolderType.Standard,"Upload","Yes", "No","All Folders", Workspace.InvestorWorkspace, M9Contact1EmailId)) {
					appLog.info(M9Contact1EmailId+" is successfully invited to "+M9Institution2);
				}
				else {
					appLog.error(M9Contact1EmailId+" could not be given access to "+M9Institution1);
					sa.assertTrue(false, M9Contact1EmailId+" could not be given access to "+M9Institution1);
				}
				if(fp.inviteContact(environment,mode,null, M9Contact1EmailId,shdPath, FolderType.Shared,"Download","Yes", "No","All Folders", Workspace.InvestorWorkspace, null)) {
					appLog.info(M9Contact1EmailId+" is successfully invited for shared folder");
				}
				else {
					appLog.error(M9Contact1EmailId+" could not be provided access for shared folder");
					sa.assertTrue(false, M9Contact1EmailId+" could not be provided access for shared folder");
				}
			}
			else {
				appLog.error("could not find fund on funds page");
				sa.assertTrue(false, "could not find fund on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String intPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		
		String[] filesCommon = {};
		String[] filesInternal = {};
		String[] filesShared = {};
		String[] filesStand = {};
		
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				String docpath = "UploadFiles\\Module9\\";
				//common
				if (fp.uploadFile(cmnPath, null, docpath+cmnPath, null, UploadFileActions.Upload,
						Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfully");
					filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon).split("<break>");
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesCommon[0],false )) {
						appLog.info("filename and status is verified successfully files present in common folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesCommon[0]);
						sa.assertTrue(false, "filename and status is not verified for "+filesCommon[0]);
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesCommon[1] ,false)) {
						appLog.info("filename and status is verified successfully files present in common folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesCommon[1]);
						sa.assertTrue(false, "filename and status is not verified for "+filesCommon[1]);
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesCommon[2] ,false)) {
						appLog.info("filename and status is verified successfully files present in common folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesCommon[2]);
						sa.assertTrue(false, "filename and status is not verified for "+filesCommon[2]);
					}
					if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
						
					}
					if (fp.verifyManageApprovalsDropdownContents(cmnPath, ManageApprovalTabs.PendingDocuments) ) {
						appLog.info("dropdown contents are successfully verified");
					}
					else {
						appLog.error("dropdown is not having correct elements for common folder");
						sa.assertTrue(false, "dropdown is not having correct elements for common folder");
					}
					if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesCommon, M9FundName1+" > "+cmnPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date) ) {
						appLog.info("successfully verified "+filesCommon+" on investor workspace manage approvals");
					}
					else {
						appLog.error("could not verify "+filesCommon + " on investor workspace manage approvals");
						sa.assertTrue(false, "could not verify "+filesCommon + " on investor workspace manage approvals");
					}
					click(driver, fp.getManageAppCancelBtn(60), "cancel button manage approvals", action.SCROLLANDBOOLEAN);
					switchToDefaultContent(driver);
				}
				
				//internal
				if (fp.uploadFile(intPath, null, docpath+intPath, null, UploadFileActions.Upload,
						Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					filesInternal = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal).split("<break>");
					
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesInternal[0] ,true)) {
						appLog.info("filename and status is verified "+filesInternal[0]+"present in Internal folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesInternal[0]);
						sa.assertTrue(false, "filename and status is not verified for "+filesInternal[0]);
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesInternal[1] ,true)) {
						appLog.info("filename and status is verified  "+filesInternal[1]+"present in Internal folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesInternal[1]);
						sa.assertTrue(false, "filename and status is not verified for "+filesInternal[1]);
					}
					if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
						
					}
					
					if (fp.verifyManageApprovalsDropdownContents(intPath,ManageApprovalTabs.PendingDocuments) ) {
						appLog.info("dropdown contents are successfully verified");
					}
					else {
						appLog.error("dropdown is not having correct elements for Internal folder");
						sa.assertTrue(false, "dropdown is not having correct elements for Internal folder");
					}
					if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
						appLog.info("no data to display is successfully verified for internl folder");
					}
					else {
						appLog.error("no data to display is not present on manage approvals window");
						sa.assertTrue(false, "no data to display is not present on manage approvals window");
					}
					click(driver, fp.getManageAppCancelBtn(60), "cancel button manage approvals", action.SCROLLANDBOOLEAN);
					switchToDefaultContent(driver);
				}
				
				
				//shared
				if (fp.uploadFile(shdPath, null, docpath+shdPath, null, UploadFileActions.Upload,
						Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared).split("<break>");
					
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesShared[0],false )) {
						appLog.info("filename and status is verified "+filesShared[0]+" successfully files present in Shared folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesShared[0]);
						sa.assertTrue(false, "filename and status is not verified for "+filesShared[0]);
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesShared[1] ,false)) {
						appLog.info("filename and status is verified "+filesShared[1]+" successfully files present in Shared folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesShared[1]);
						sa.assertTrue(false, "filename and status is not verified for "+filesShared[1]);
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesShared[2],false )) {
						appLog.info("filename and status is verified "+filesShared[2]+" successfully files present in Shared folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesShared[2]);
						sa.assertTrue(false, "filename and status is not verified for "+filesShared[2]);
					}
					if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
						
					}
					if (fp.verifyManageApprovalsDropdownContents(shdPath,ManageApprovalTabs.PendingDocuments) ) {
						appLog.info("dropdown contents are successfully verified");
					}
					else {
						appLog.error("dropdown is not having correct elements for Shared folder");
						sa.assertTrue(false, "dropdown is not having correct elements for Shared folder");
					}
					if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesShared, M9FundName1+" > "+shdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
						appLog.info("succssfully verified files"+filesShared+" on investor workspace manage approvals");
					}
					else {
						appLog.error("could not verify files "+filesShared+" on investor workspace manage approvals");
						sa.assertTrue(false, "could not verify files "+filesShared+" on investor workspace manage approvals");
					}
					click(driver, fp.getManageAppCancelBtn(60), "cancel button manage approvals", action.SCROLLANDBOOLEAN);
					switchToDefaultContent(driver);
				}
				//standard for inst 1
				if (fp.uploadFile(stdPath, M9Institution1+"/"+M9LimitedPartner1+"<break>"+M9Institution2+"/"+M9LimitedPartner2, docpath+stdPath, null, UploadFileActions.Upload,
						Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly");
					filesStand = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard).split("<break>");
					
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesStand[0] ,false)) {
						appLog.info("filename and status is verified "+filesStand[0]+"successfully files present in Standard folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesStand[0]);
						sa.assertTrue(false, "filename and status is not verified for "+filesStand[0]);
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesStand[1] ,false)) {
						appLog.info("filename and status is verified "+filesStand[1]+"successfully files present in Standard folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesStand[1]);
						sa.assertTrue(false, "filename and status is not verified for "+filesStand[1]);
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesStand[2],false )) {
						appLog.info("filename and status is verified "+filesStand[2]+"successfully files present in Standard folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesStand[2]);
						sa.assertTrue(false, "filename and status is not verified for "+filesStand[2]);
					}
					if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
						
					}
					if (fp.verifyManageApprovalsDropdownContents(stdPath,ManageApprovalTabs.PendingDocuments) ) {
						appLog.info("dropdown contents are successfully verified");
					}
					else {
						appLog.error("dropdown is not having correct elements for Standard folder");
						sa.assertTrue(false, "dropdown is not having correct elements for Standard folder");
					}
					fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace,ManageApprovalTabs.PendingDocuments, filesStand, M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+" > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date);
					//selecting all pending docs
					if (selectVisibleTextFromDropDown(driver,  fp.getManageApprovalsDropdown(ManageApprovalTabs.PendingDocuments, 30), "manage approval dropdown","All Pending Documents")) {
						for (int i = 0;i<3;i++) {
						if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments, filesStand[i], M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+ " > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
							appLog.info("successfully verified file "+filesStand[i] + " for "+M9Institution1);
						}
						else {
							click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments,60), "document name manage approvals", action.BOOLEAN);
							if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments, filesStand[i], M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+ " > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info("successfully verified file "+filesStand[i] + " for "+M9Institution1);
							}
							else {
							appLog.error(filesStand[i]+" could not be verified for "+M9Institution1);
							sa.assertTrue(false, filesStand[i]+" could not be verified for "+M9Institution1);
							}
						}
						if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments, filesStand[i], M9FundName1+" > "+M9Institution2+" > "+M9LimitedPartner2+ " > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
							appLog.info("successfully verified file "+filesStand[i] + " for "+M9Institution2);
						}
						else {
							click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments,60), "document name manage approvals", action.BOOLEAN);
							if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments, filesStand[i], M9FundName1+" > "+M9Institution2+" > "+M9LimitedPartner2+ " > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info("successfully verified file "+filesStand[i] + " for "+M9Institution2);
							}
							else {
							appLog.error(filesStand[i]+" could not be verified for "+M9Institution2);
							sa.assertTrue(false, filesStand[i]+" could not be verified for "+M9Institution2);
							}
						}
							
						}
						for (int i = 0;i<3;i++) {
							if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments, filesCommon[i], M9FundName1+" > "+cmnPath,"pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info("successfully verified file "+filesCommon[i]);
							}
							else {
								click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments,60), "document name manage approvals", action.BOOLEAN);
								if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments, filesCommon[i], M9FundName1+" > "+cmnPath,"pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
									appLog.info("successfully verified file "+filesCommon[i]);
								}
								else {
								appLog.error(filesCommon[i]+" could not be verified");
								sa.assertTrue(false, filesCommon[i]+" could not be verified");
								}
							}
						}
						for (int i = 0;i<3;i++) {
							if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments, filesShared[i], M9FundName1+" > "+shdPath,"pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info("successfully verified file "+filesShared[i]);
							}
							else {
								click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments,60), "document name manage approvals", action.BOOLEAN);
								if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments, filesShared[i], M9FundName1+" > "+shdPath,"pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
									appLog.info("successfully verified file "+filesShared[i]);
								}
								else {
								appLog.error(filesShared[i]+" could not be verified");
								sa.assertTrue(false, filesShared[i]+" could not be verified");
								}
							}
						}
					}
					else {
						appLog.error("all pending documents is not present in manage approvals window");
						sa.assertTrue(false, "all pending documents is not present in manage approvals window");
					}
					click(driver, fp.getManageAppCancelBtn(60), "cancel button manage approvals", action.SCROLLANDBOOLEAN);
					//for inst 2
					if (fp.verifyFolderPathdummy(stdPath, M9Institution2, M9LimitedPartner2, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesStand[0] ,false)) {
							appLog.info("filename and status is verified "+filesStand[0]+"successfully files present in Standard folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[0]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[0]);
						}
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesStand[1] ,false)) {
							appLog.info("filename and status is verified "+filesStand[1]+"successfully files present in Standard folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[1]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[1]);
						}
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesStand[2],false )) {
							appLog.info("filename and status is verified "+filesStand[2]+"successfully files present in Standard folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[2]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[2]);
						}
					}
					else {
						appLog.error(stdPath+" is not found in funds page");
						sa.assertTrue(false, stdPath+" is not found in funds page");
					}
					switchToDefaultContent(driver);
				}
				else {
					appLog.error("file "+docpath+" could not be uploaded");
					sa.assertTrue(false, "file "+docpath+" could not be uploaded");
				}
				
				}
			else {
				appLog.error("fund "+M9FundName1+" could not be found");
				sa.assertTrue(false, "fund "+M9FundName1+" could not be found");
			}
				}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_ImpactCRM() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String intPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		String[] filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.UploadedFileCommon).split("<break>");
		String[] filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.UploadedFileShared).split("<break>");
		String[] filesStand = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.UploadedFileStandard).split("<break>");
		
		String[] filesInternal = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.UploadedFileInternal).split("<break>");
	String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		//Institution
		if (fp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M9Institution1)) {
				switchToFrame(driver, 30, ip.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesCommon[0], false)) {
						appLog.info("filename and status is correct for "+filesCommon[0]);
					}
					else {
						appLog.error("filename and status is not verified for "+filesCommon[0]);
						sa.assertTrue(false, "filename and status is not verified for "+filesCommon[0]);
					}
					if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace,filesCommon[1] ,false)) {
						appLog.info("filename and status is verified successfully files present in common folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesCommon[1]);
						sa.assertTrue(false, "filename and status is not verified for "+filesCommon[1]);
					}
					if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace,filesCommon[2] ,false)) {
						appLog.info("filename and status is verified successfully files present in common folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesCommon[2]);
						sa.assertTrue(false, "filename and status is not verified for "+filesCommon[2]);
					}
				}
				
				if (fp.verifyFolderPathdummy(intPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesInternal[0], true)) {
						appLog.info("filename and status is correct for "+filesInternal[0]);
					}
					else {
						appLog.error("filename and status is not verified for "+filesInternal[0]);
						sa.assertTrue(false, "filename and status is not verified for "+filesInternal[0]);
					}
					if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace,filesInternal[1] ,true)) {
						appLog.info("filename and status is verified successfully files present in common folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesInternal[1]);
						sa.assertTrue(false, "filename and status is not verified for "+filesInternal[1]);
					}
				}
				
				if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesShared[0], false)) {
						appLog.info("filename and status is correct for "+filesShared[0]);
					}
					else {
						appLog.error("filename and status is not verified for "+filesShared[0]);
						sa.assertTrue(false, "filename and status is not verified for "+filesShared[0]);
					}
					if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace,filesShared[1] ,false)) {
						appLog.info("filename and status is verified successfully files present in shared folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesShared[1]);
						sa.assertTrue(false, "filename and status is not verified for "+filesShared[1]);
					}
					if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace,filesShared[2] ,false)) {
						appLog.info("filename and status is verified successfully files present in shared folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesShared[2]);
						sa.assertTrue(false, "filename and status is not verified for "+filesShared[2]);
					}
				}
				
				
				if (fp.verifyFolderPathdummy(stdPath, null, M9LimitedPartner1, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesStand[0], false)) {
						appLog.info("filename and status is correct for "+filesStand[0]);
					}
					else {
						appLog.error("filename and status is not verified for "+filesStand[0]);
						sa.assertTrue(false, "filename and status is not verified for "+filesStand[0]);
					}
					if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace,filesStand[1] ,false)) {
						appLog.info("filename and status is verified successfully files present in std folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesStand[1]);
						sa.assertTrue(false, "filename and status is not verified for "+filesStand[1]);
					}
					if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace,filesStand[2] ,false)) {
						appLog.info("filename and status is verified successfully files present in std folder");
					}
					else {
						appLog.error("filename and status is not verified for "+filesStand[2]);
						sa.assertTrue(false, "filename and status is not verified for "+filesStand[2]);
					}
				}
				switchToDefaultContent(driver);
			}
			
			
			//Contact
			if (ip.clickOnTab(TabName.ContactTab)) {
				if (cp.clickOnCreatedContact(M9Contact1FirstName, M9Contact1LastName, null)) {
					
					switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
					scrollDownThroughWebelement(driver, ip.getWorkspaceSectionView(Workspace.InvestorWorkspace,30),"fundraising workspace view");
					if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
						if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesCommon[0], false)) {
							appLog.info("filename and status is correct for "+filesCommon[0]);
						}
						else {
							appLog.error("filename and status is not verified for "+filesCommon[0]);
							sa.assertTrue(false, "filename and status is not verified for "+filesCommon[0]);
						}
						if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace,filesCommon[1] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesCommon[1]);
							sa.assertTrue(false, "filename and status is not verified for "+filesCommon[1]);
						}
						if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace,filesCommon[2] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesCommon[2]);
							sa.assertTrue(false, "filename and status is not verified for "+filesCommon[2]);
						}
					}
					
					if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
						if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesShared[0], false)) {
							appLog.info("filename and status is correct for "+filesShared[0]);
						}
						else {
							appLog.error("filename and status is not verified for "+filesShared[0]);
							sa.assertTrue(false, "filename and status is not verified for "+filesShared[0]);
						}
						if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace,filesShared[1] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesShared[1]);
							sa.assertTrue(false, "filename and status is not verified for "+filesShared[1]);
						}
						if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace,filesShared[2] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesShared[2]);
							sa.assertTrue(false, "filename and status is not verified for "+filesShared[2]);
						}
					}
					
					
					if (fp.verifyFolderPathdummy(stdPath, M9Institution1, M9LimitedPartner1, M9FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
						if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesStand[0], false)) {
							appLog.info("filename and status is correct for "+filesStand[0]);
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[0]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[0]);
						}
						if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace,filesStand[1] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[1]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[1]);
						}
						if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace,filesStand[2] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[2]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[2]);
						}
					}
					switchToDefaultContent(driver);
			}
				else {
					appLog.error("could not find contact "+M9Contact1FirstName+" "+M9Contact1LastName);
					sa.assertTrue(false, "could not find contact "+M9Contact1FirstName+" "+M9Contact1LastName);
				}
			}
			else {
				appLog.error("contacts tab is not clickable");
				sa.assertTrue(false, "contacts tab is not clickable");
			}
			
			//Commitment
			if (ip.clickOnTab(TabName.CommitmentsTab)) {
				if (cmp.clickOnCreatedCommitmentId(M9Commitment1ID)) {
					
					switchToFrame(driver, 30, cp.getFrame( PageName.CommitmentsPage, 30));
					scrollDownThroughWebelement(driver, ip.getWorkspaceSectionView(Workspace.InvestorWorkspace,30),"fundraising workspace view");
					if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
						if (bp.verifyFileinContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace, filesCommon[0], false)) {
							appLog.info("filename and status is correct for "+filesCommon[0]);
						}
						else {
							appLog.error("filename and status is not verified for "+filesCommon[0]);
							sa.assertTrue(false, "filename and status is not verified for "+filesCommon[0]);
						}
						if (fp.verifyFileinContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace,filesCommon[1] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesCommon[1]);
							sa.assertTrue(false, "filename and status is not verified for "+filesCommon[1]);
						}
						if (fp.verifyFileinContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace,filesCommon[2] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesCommon[2]);
							sa.assertTrue(false, "filename and status is not verified for "+filesCommon[2]);
						}
					}
					
					if (fp.verifyFolderPathdummy(intPath, null, null, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
						if (bp.verifyFileinContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace, filesInternal[0], true)) {
							appLog.info("filename and status is correct for "+filesInternal[0]);
						}
						else {
							appLog.error("filename and status is not verified for "+filesInternal[0]);
							sa.assertTrue(false, "filename and status is not verified for "+filesInternal[0]);
						}
						if (fp.verifyFileinContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace,filesInternal[1] ,true)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesInternal[1]);
							sa.assertTrue(false, "filename and status is not verified for "+filesInternal[1]);
						}
					}
					
					if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
						if (bp.verifyFileinContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace, filesShared[0], false)) {
							appLog.info("filename and status is correct for "+filesShared[0]);
						}
						else {
							appLog.error("filename and status is not verified for "+filesShared[0]);
							sa.assertTrue(false, "filename and status is not verified for "+filesShared[0]);
						}
						if (fp.verifyFileinContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace,filesShared[1] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesShared[1]);
							sa.assertTrue(false, "filename and status is not verified for "+filesShared[1]);
						}
						if (fp.verifyFileinContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace,filesShared[2] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesShared[2]);
							sa.assertTrue(false, "filename and status is not verified for "+filesShared[2]);
						}
					}
					
					
					if (fp.verifyFolderPathdummy(stdPath, null, M9LimitedPartner1, M9FundName1, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
						if (bp.verifyFileinContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace, filesStand[0], false)) {
							appLog.info("filename and status is correct for "+filesStand[0]);
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[0]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[0]);
						}
						if (fp.verifyFileinContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace,filesStand[1] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[1]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[1]);
						}
						if (fp.verifyFileinContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace,filesStand[2] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[2]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[2]);
						}
					}
					switchToDefaultContent(driver);
			}
				else {
					appLog.error("could not find contact "+M9Contact1FirstName+" "+M9Contact1LastName);
					sa.assertTrue(false, "could not find contact "+M9Contact1FirstName+" "+M9Contact1LastName);
				}
			}
			
			
			//LP
			if (ip.clickOnTab(TabName.InstituitonsTab)) {
				if (ip.clickOnCreatedLP(M9LimitedPartner1)) {
					
					switchToFrame(driver, 30, cp.getFrame( PageName.InstitutionsPage, 30));
					scrollDownThroughWebelement(driver, ip.getWorkspaceSectionView(Workspace.InvestorWorkspace,30),"fundraising workspace view");
					if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
						if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesCommon[0], false)) {
							appLog.info("filename and status is correct for "+filesCommon[0]);
						}
						else {
							appLog.error("filename and status is not verified for "+filesCommon[0]);
							sa.assertTrue(false, "filename and status is not verified for "+filesCommon[0]);
						}
						if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace,filesCommon[1] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesCommon[1]);
							sa.assertTrue(false, "filename and status is not verified for "+filesCommon[1]);
						}
						if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace,filesCommon[2] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesCommon[2]);
							sa.assertTrue(false, "filename and status is not verified for "+filesCommon[2]);
						}
					}
					
					if (fp.verifyFolderPathdummy(intPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
						if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesInternal[0], true)) {
							appLog.info("filename and status is correct for "+filesInternal[0]);
						}
						else {
							appLog.error("filename and status is not verified for "+filesInternal[0]);
							sa.assertTrue(false, "filename and status is not verified for "+filesInternal[0]);
						}
						if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace,filesInternal[1] ,true)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesInternal[1]);
							sa.assertTrue(false, "filename and status is not verified for "+filesInternal[1]);
						}
					}
					
					if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
						if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesShared[0], false)) {
							appLog.info("filename and status is correct for "+filesShared[0]);
						}
						else {
							appLog.error("filename and status is not verified for "+filesShared[0]);
							sa.assertTrue(false, "filename and status is not verified for "+filesShared[0]);
						}
						if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace,filesShared[1] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesShared[1]);
							sa.assertTrue(false, "filename and status is not verified for "+filesShared[1]);
						}
						if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace,filesShared[2] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesShared[2]);
							sa.assertTrue(false, "filename and status is not verified for "+filesShared[2]);
						}
					}
					
					
					if (fp.verifyFolderPathdummy(stdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
						if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesStand[0], false)) {
							appLog.info("filename and status is correct for "+filesStand[0]);
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[0]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[0]);
						}
						if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace,filesStand[1] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[1]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[1]);
						}
						if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace,filesStand[2] ,false)) {
							appLog.info("filename and status is verified successfully files present in common folder");
						}
						else {
							appLog.error("filename and status is not verified for "+filesStand[2]);
							sa.assertTrue(false, "filename and status is not verified for "+filesStand[2]);
						}
					}
					switchToDefaultContent(driver);
			}else {
					appLog.error("could not find LP "+M9LimitedPartner1);
					sa.assertTrue(false, "could not find LP "+M9LimitedPartner1);
				}
			}
		}
		else {
			appLog.error("inst tab is not clickable");
			sa.assertTrue(false, "inst tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_ImpactInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.CommonPath);
		String[] filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.UploadedFileCommon).split("<break>");
		
		lp.investorLogin(M9Contact1EmailId, adminPassword);
		List<String> fail_list = null;
		if (selectVisibleTextFromDropDown(driver, ifp.getFirmNameDropdownWRTPage(PageName.AllFirmsPage), "All Firms", "All Firms")) {
			ThreadSleep(5000);
			fail_list=afp.verifyAlertsOnAllFirmsPage(filesCommon[0], Org1FirmName, M9FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpload, PageName.AllFirmsPage); 				
			if (fail_list.isEmpty()) {
				appLog.error("pending docs are also being displayed on alerts on all firms page");
				sa.assertTrue(false, "pending docs are also being displayed on alerts on all firms page");
			}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.info("pending docs not found on all firms alert as expected");
				}
				
			}
		}
		else {
			appLog.error("could not find all firms option in dropdown");
			sa.assertTrue(false, "could not find all firms option in dropdown");
		}
		if (selectVisibleTextFromDropDown(driver, ifp.getFirmNameDropdownWRTPage(PageName.AllFirmsPage),Org1FirmName,Org1FirmName)) {
			click(driver, ifp.getAllDocumentsTab(30), "all docs tab", action.BOOLEAN);
			fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(filesCommon[0], M9FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments, activityType.DocumentUpload, PageName.AllFirmsPage);
			if (fail_list.isEmpty()) {
				appLog.error("pending docs are also being displayed on recent activities page");
				sa.assertTrue(false, "pending docs are also being displayed on recent activities page");
			}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.info("pending docs not found on recent activities tab as expected");
				}
			}
			click(driver, ifp.getRecentActivitiesTab(30), "recent activities tab", action.BOOLEAN);
			fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(filesCommon[0], M9FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities, activityType.DocumentUpload, PageName.AllFirmsPage);
			if (fail_list.isEmpty()) {
				appLog.error("pending docs are also being displayed on recent activities page");
				sa.assertTrue(false, "pending docs are also being displayed on recent activities page");
			}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.info("pending docs not found on recent activities tab as expected");
				}
			}
		}
		else {
			appLog.error("could not find firm name in dropdown selection");
			sa.assertTrue(false, "could not find firm name in dropdown selection");
		}
		
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			//common
			if (fp.verifyFolderPathdummy(cmnPath, null,null, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
			if (ifp.getNoDocumentToDisplay(60).getText().trim().equals(InvestorFirmPageErrorMessage.noDocumentPresentInFolder)) {
				appLog.info("no document to display error message is successfully displayed for common folder");
			}
			else {
				appLog.error("no document to display message is not displayed");
				sa.assertTrue(false, "no document to display message is not displayed");
			}
			}
			else {
				appLog.error(cmnPath + " is not displayed in folder structure");
				sa.assertTrue(false, cmnPath + " is not displayed in folder structure");
			}
			//shared
			if (fp.verifyFolderPathdummy(shdPath, null,null, null,PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
				if (ifp.getNoDocumentToDisplay(60).getText().trim().equals(InvestorFirmPageErrorMessage.noDocumentPresentInFolder)) {
					appLog.info("no document to display error message is successfully displayed for shd folder");
				}
				else {
					appLog.error("no document to display message is not displayed");
					sa.assertTrue(false, "no document to display message is not displayed");
				}
					
			}
			else {
				appLog.error(shdPath + " is not displayed in folder structure");
				sa.assertTrue(false, shdPath + " is not displayed in folder structure");
			}
			//std
			if (fp.verifyFolderPathdummy(stdPath, M9Institution1,M9LimitedPartner1, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
				if (ifp.getNoDocumentToDisplay(60).getText().trim().equals(InvestorFirmPageErrorMessage.noDocumentPresentInFolder)) {
					appLog.info("no document to display error message is successfully displayed for std folder");
				}
				else {
					appLog.error("no document to display message is not displayed");
					sa.assertTrue(false, "no document to display message is not displayed");
				}
					
			}
			else {
				appLog.error(stdPath + " is not displayed in folder structure");
				sa.assertTrue(false, stdPath + " is not displayed in folder structure");
			}
		}
		else {
			appLog.error("potential investments tab is not clickable");
			sa.assertTrue(false, "potential investments tab is not clickable");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M9tc035_VerifyDeleteInvestorWorkspace_Action() {
	LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
	BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
	FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
	String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
	String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
	String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
	String[] filesToDel = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.KeyWord_For_Search).split("<break>");
	String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
	lp.CRMLogin(CRMUser1EmailID, adminPassword);
	if (bp.clickOnTab(TabName.FundsTab)) {
		if (fp.clickOnCreatedFund(M9FundName1)) {
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
				if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "fundraising manage approval icon", action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon for "+Workspace.InvestorWorkspace.toString(), action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getPendingDocsTab(30), "pending documents tab", action.BOOLEAN)) {
							if (selectVisibleTextFromDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.PendingDocuments,30), "manage approvals pending dropdown", "All Pending Documents")) {
								for (int i = 0;i<filesToDel.length;i++) {
									WebElement el=null;
									if (i!=2) {
											if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30),filesToDel[i] , M9FundName1+" > ", null, Org1FirmName, date)) {
									 el = fp.checkboxForFileInManageApprovals(filesToDel[i]);
									}
									}
									else if (i==2) {
											if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30),filesToDel[i] , M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+" > "+stdPath, null, Org1FirmName, date)) {
										el = fp.checkboxForFileInManageApprovals(filesToDel[i],M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+" > "+stdPath);
									}
									}
									 scrollDownThroughWebelement(driver,el, filesToDel[i]);
									 if (el!=null) {
										 if (!isSelected(driver, el, "checkbox for "+filesToDel[i])) {
											 if (click(driver, el,"checkbox for "+filesToDel[i] , action.SCROLLANDBOOLEAN)) {
												 appLog.info("selected checkbox for "+filesToDel[i]);
											 }
										 }
										 else {
											 appLog.info("checkbox for "+filesToDel[i]+" is already selected");
										 }

									 }
									 else {
										 
											 appLog.error("could not find file "+filesToDel[i]);
											 sa.assertTrue(false, "could not find file "+filesToDel[i]);
										 }
									
								}
							}
							if (click(driver, fp.getdeleteBtnManageApprovals(60), "delete Btn Manage Approvals", action.BOOLEAN)) {
								if (fp.getManageApprovalDeletePopupText(60).getText().trim().contains(FundsPageErrorMessage.manageApprovalDelSuccess1)
										&& fp.getManageApprovalDeletePopupText(60).getText().trim().contains(FundsPageErrorMessage.manageApprovalDelSuccess2)) {
									appLog.info("delete message is correct for manage approvals");
								}
								else {
									appLog.error("delete message is not correct for manage approvals");
									sa.assertTrue(false, "delete message is not correct for manage approvals");
								}
								if (fp.getManageApprovalDelYesorNo(YesNo.Yes,60)!=null) {
									appLog.info("yes button for manage approval is successfully displayed");
								}
								else {
									appLog.error("yes button is not displayed");
									sa.assertTrue(false, "yes button is not displayed");
								}
								if (fp.getManageApprovalDelYesorNo(YesNo.No,60)!=null) {
									appLog.info("no button for manage approval is successfully displayed");
								}
								else {
									appLog.error("no button is not displayed");
									sa.assertTrue(false, "no button is not displayed");
								}
								if (fp.getManageApprovalDelCrossIcon(60)!=null) {
									appLog.info("cross icon for manage approvals is successfully displayed");
								}
								else {
									appLog.error("cross icon for manage approvals is not present");
									sa.assertTrue(false, "cross icon for manage approvals is not present");
								}
								if (click(driver, fp.getManageApprovalDelYesorNo(YesNo.No,60), "delete message no button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on no button on delete popup");
								}
								else {
									appLog.error("no button on delete popup is not clickable");
									sa.assertTrue(false, "no button on delete popup is not clickable");
								}



								//clicking delete button and then cross icon on popup
								if (click(driver, fp.getdeleteBtnManageApprovals(60), "delete button manage approvals", action.SCROLLANDBOOLEAN)) {
									if (click(driver, fp.getManageApprovalDelCrossIcon(60), "manage approvals del cross icon", action.BOOLEAN)) {
											fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30),filesToDel[0] , M9FundName1+" > ", null, Org1FirmName, date);
										//checkboxes for all 3 files will still be selected
											if (isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[0]), "checkbox for "+filesToDel[0]))
											{
										
												appLog.info("checkboxes for "+filesToDel[0]+" to delete are selected");
											}
										else {
												appLog.error("checkboxes for the "+filesToDel[0]+" to delete are not selected");
												sa.assertTrue(false, "checkboxes for the "+filesToDel[0]+" to delete are not selected");
										}
												fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30),filesToDel[1] , M9FundName1+" > ", null, Org1FirmName, date);

												if (isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[1]), "checkbox for "+filesToDel[1]))
												{
												appLog.info("checkboxes for "+filesToDel[1]+"  are selected");
											}
											else {
												appLog.error("checkboxes for the "+filesToDel[1]+"  are not selected");
												sa.assertTrue(false, "checkboxes for the"+filesToDel[1]+"  are not selected");
											}
													fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30),filesToDel[2] , M9FundName1+" > "+M9Institution1, null, Org1FirmName, date);
												if (isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[2]), "checkbox for "+filesToDel[2]))
											{
												appLog.info("checkboxes for "+filesToDel[2]+" to delete are selected");
											}
											else {
												appLog.error("checkboxes for the "+filesToDel[2]+" are not selected");
												sa.assertTrue(false, "checkboxes for "+filesToDel[2]+" are not selected");
											}
									}
								}
								//clicking delete icon and then yes button
								if (click(driver, fp.getdeleteBtnManageApprovals(60), "delete button manage approvals", action.SCROLLANDBOOLEAN)) {
									if (click(driver, fp.getManageApprovalDelYesorNo(YesNo.Yes,60), "manage approval del yes button", action.SCROLLANDBOOLEAN)) {
										if (click(driver, fp.getManageAppCancelBtn(60), "get manage approval cancel button", action.SCROLLANDBOOLEAN)) {
											//verify files in common, shared and std folder
											if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
												if (!fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesToDel[0])) {
													appLog.info("file is successfully deleted from content grid");
												}
												else {
													appLog.error(filesToDel[0]+" should have been deleted but is still present in content grid");
													sa.assertTrue(false, filesToDel[0]+" should have been deleted but is still present in content grid");
												}
											}

											if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
												if (!fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesToDel[1])) {
													appLog.info("file is successfully deleted from content grid");
												}
												else {
													appLog.error(filesToDel[1]+" should have been deleted but is still present in content grid");
													sa.assertTrue(false, filesToDel[1]+" should have been deleted but is still present in content grid");
												}
											}

											if (fp.verifyFolderPathdummy(stdPath, M9Institution1, M9LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
												scrollDownThroughWebelement(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon");
												if (!fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesToDel[2])) {
													appLog.info("file is successfully deleted from content grid");
												}
												else {
													appLog.error(filesToDel[2]+" should have been deleted but is still present in content grid");
													sa.assertTrue(false, filesToDel[2]+" should have been deleted but is still present in content grid");
												}
											}
											if (fp.verifyFolderPathdummy(stdPath, M9Institution2, M9LimitedPartner2, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
												scrollDownThroughWebelement(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon");
												if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesToDel[2])) {
													appLog.info("file is successfully present content grid in inst2");
												}
												else {
													appLog.error(filesToDel[2]+" should be present but is not present in content grid");
													sa.assertTrue(false, filesToDel[2]+" should be present but is not present in content grid");
												}
											}
											else {
												appLog.error(stdPath+" is not found in folder structure");
												sa.assertTrue(false, stdPath+" is not found in folder structure");
											}
										}
										else {
											appLog.error("manage approval cancel button is not clickable");
											sa.assertTrue(false, "manage approval cancel button is not clickable");
										}
									}
									else {
										appLog.error("delete popup yes button is not clickable");
										sa.assertTrue(false, "delete popup yes button is not clickable");
									}
								}
								else {
									appLog.error("delete button is not clickable");
									sa.assertTrue(false, "delete button is not clickable");
								}
							}
							else {
								appLog.error("delete button is not clickable");
								sa.assertTrue(false, "delete button is not clickable");
							}
						}

						else {
							appLog.error("files could not be deleted");
							sa.assertTrue(false, "files could not be deleted");
						}

					}
					else {
						appLog.error("manage approvals icon is not clickable");
						sa.assertTrue(false, "manage approvals icon is not clickable");
					}
				}
				else {
					appLog.error("manage approvals icon is not clickable");
					sa.assertTrue(false, "manage approvals icon is not clickable");
				}
			}
			else {
				appLog.error(cmnPath + " is not present in folder structure");
				sa.assertTrue(false, cmnPath + " is not present in folder structure");
			}
		}
			switchToDefaultContent(driver);
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M9tc035_VerifyDeleteInvestorWorkspace_Impact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc035_VerifyDeleteInvestorWorkspace_Action", excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc035_VerifyDeleteInvestorWorkspace_Action", excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc035_VerifyDeleteInvestorWorkspace_Action", excelLabel.CommonPath);
		String cmnFolderName = cmnPath.split(" ")[0];
		String shdFolderName = shdPath.split(" ")[0];
		String stdFolderName = stdPath.split(" ")[0];
		String[] filesToDel = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc035_VerifyDeleteInvestorWorkspace_Action", excelLabel.KeyWord_For_Search).split("<break>");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		String docpath = "UploadFiles\\Module9\\";
		
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M9Institution1)) {
				switchToFrame(driver, 30, ip.getFrame(environment,mode, PageName.InstitutionsPage, 30));
				//common folder
				if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (!bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[0])) {
						appLog.info(filesToDel[0]+"file is successfully deleted and is not present in "+PageName.InstitutionsPage);
					}
					else {
						appLog.error(filesToDel[0]+" should have been deleted but is still present in "+PageName.InstitutionsPage);
						sa.assertTrue(false, filesToDel[0]+" should have been deleted but is still present in "+PageName.InstitutionsPage);
					}
				}
				else {
					appLog.error(cmnPath+" is not present in folder structure");
					sa.assertTrue(false, cmnPath+" is not present in folder structure");
				}
				//shared
				if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (!bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[1])) {
						appLog.info(filesToDel[1]+"file is successfully deleted and is not present in "+PageName.InstitutionsPage);
					}
					else {
						appLog.error(filesToDel[1]+" should have been deleted but is still present in "+PageName.InstitutionsPage);
						sa.assertTrue(false, filesToDel[1]+" should have been deleted but is still present in "+PageName.InstitutionsPage);
					}
				}
				else {
					appLog.error(shdPath+" is not present in folder structure");
					sa.assertTrue(false, shdPath+" is not present in folder structure");
				}
				//standard inst 1
				if (fp.verifyFolderPathdummy(stdPath, null, M9LimitedPartner1, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (!bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[2])) {
						appLog.info(filesToDel[2]+"file is successfully deleted and is not present in "+PageName.InstitutionsPage);
					}
					else {
						appLog.error(filesToDel[2]+" should have been deleted but is still present in "+PageName.InstitutionsPage);
						sa.assertTrue(false, filesToDel[2]+" should have been deleted but is still present in "+PageName.InstitutionsPage);
					}
				}
				else {
					appLog.error(stdPath+" is not present in folder structure");
					sa.assertTrue(false, stdPath+" is not present in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9Institution1+" is not present in instittuions page");
				sa.assertTrue(false, M9Institution1+" is not present in instittuions page");
			}
		}
		else {
			appLog.error("institutions tab is not clickable");
			sa.assertTrue(false, "institutions tab is not clickable");
		}
		
		
		//Commitment
		if (bp.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.clickOnCreatedCommitmentId(M9Commitment1ID)) {
				switchToFrame(driver, 30, ip.getFrame(environment,mode, PageName.CommitmentsPage, 30));
				//common folder
				if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
					if (!bp.verifyFileinContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace, filesToDel[0])) {
						appLog.info(filesToDel[0]+"file is successfully deleted and is not present in "+PageName.CommitmentsPage);
					}
					else {
						appLog.error(filesToDel[0]+" should have been deleted but is still present in "+PageName.CommitmentsPage);
						sa.assertTrue(false, filesToDel[0]+" should have been deleted but is still present in "+PageName.CommitmentsPage);
					}
				}
				else {
					appLog.error(cmnPath+" is not present in folder structure");
					sa.assertTrue(false, cmnPath+" is not present in folder structure");
				}
				//shared
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
					if (!bp.verifyFileinContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace, filesToDel[1])) {
						appLog.info(filesToDel[1]+"file is successfully deleted and is not present in "+PageName.CommitmentsPage);
					}
					else {
						appLog.error(filesToDel[1]+" should have been deleted but is still present in "+PageName.CommitmentsPage);
						sa.assertTrue(false, filesToDel[1]+" should have been deleted but is still present in "+PageName.CommitmentsPage);
					}
				}
				else {
					appLog.error(shdPath+" is not present in folder structure");
					sa.assertTrue(false, shdPath+" is not present in folder structure");
				}
				//standard inst 1
				if (fp.verifyFolderPathdummy(stdPath, null, M9LimitedPartner1, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
					if (!bp.verifyFileinContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace, filesToDel[2])) {
						appLog.info(filesToDel[2]+"file is successfully deleted and is not present in "+PageName.CommitmentsPage);
					}
					else {
						appLog.error(filesToDel[2]+" should have been deleted but is still present in "+PageName.CommitmentsPage);
						sa.assertTrue(false, filesToDel[2]+" should have been deleted but is still present in "+PageName.CommitmentsPage);
					}
				}
				else {
					appLog.error(stdPath+" is not present in folder structure");
					sa.assertTrue(false, stdPath+" is not present in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9Institution1+" is not present in instittuions page");
				sa.assertTrue(false, M9Institution1+" is not present in instittuions page");
			}
		}
		else {
			appLog.error("institutions tab is not clickable");
			sa.assertTrue(false, "institutions tab is not clickable");
		}
		//LP
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedLP(M9LimitedPartner1)) {
				switchToFrame(driver, 30, ip.getFrame(environment,mode, PageName.InstitutionsPage, 30));
				//common folder
				if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (!bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[0])) {
						appLog.info(filesToDel[0]+"file is successfully deleted and is not present in "+PageName.LimitedPartnerPage);
					}
					else {
						appLog.error(filesToDel[0]+" should have been deleted but is still present in "+PageName.LimitedPartnerPage);
						sa.assertTrue(false, filesToDel[0]+" should have been deleted but is still present in "+PageName.LimitedPartnerPage);
					}
				}
				else {
					appLog.error(cmnPath+" is not present in folder structure");
					sa.assertTrue(false, cmnPath+" is not present in folder structure");
				}
				//shared
				if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (!bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[1])) {
						appLog.info(filesToDel[1]+"file is successfully deleted and is not present in "+PageName.LimitedPartnerPage);
					}
					else {
						appLog.error(filesToDel[1]+" should have been deleted but is still present in "+PageName.LimitedPartnerPage);
						sa.assertTrue(false, filesToDel[1]+" should have been deleted but is still present in "+PageName.LimitedPartnerPage);
					}
				}
				else {
					appLog.error(shdPath+" is not present in folder structure");
					sa.assertTrue(false, shdPath+" is not present in folder structure");
				}
				//standard inst 1
				if (fp.verifyFolderPathdummy(stdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (!bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[2])) {
						appLog.info(filesToDel[2]+"file is successfully deleted and is not present in "+PageName.LimitedPartnerPage);
					}
					else {
						appLog.error(filesToDel[2]+" should have been deleted but is still present in "+PageName.LimitedPartnerPage);
						sa.assertTrue(false, filesToDel[2]+" should have been deleted but is still present in "+PageName.LimitedPartnerPage);
					}
				}
				else {
					appLog.error(stdPath+" is not present in folder structure");
					sa.assertTrue(false, stdPath+" is not present in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9Institution1+" is not present in instittuions page");
				sa.assertTrue(false, M9Institution1+" is not present in instittuions page");
			}
		}
		else {
			appLog.error("institutions tab is not clickable");
			sa.assertTrue(false, "institutions tab is not clickable");
		}
		
		
		
		
		
		if (bp.clickOnTab(TabName.FundsTab)) {
		if (fp.clickOnCreatedFund(M9FundName1)) {
			//common
			if (fp.uploadFile(cmnPath, null, docpath+cmnFolderName+"_Single", null, UploadFileActions.Upload,
					Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
				appLog.info("File is upload successfully");
				String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
				if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesCommon,false )) {
					appLog.info("filename and status is verified successfully files present in common folder");
				}
				else {
					appLog.error("filename and status is not verified for "+filesCommon);
					sa.assertTrue(false, "filename and status is not verified for "+filesCommon);
				}
			}
			else {
				appLog.error("could not upload file in "+cmnPath);
			}
			
			//shared
			if (fp.uploadFile(shdPath, null, docpath+shdFolderName+"_Single", null, UploadFileActions.Upload,
					Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
				appLog.info("File is upload successfully");
				String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared);
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
				if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesShared,false )) {
					appLog.info("filename and status is verified successfully files present in shared folder");
				}
				else {
					appLog.error("filename and status is not verified for "+filesShared);
					sa.assertTrue(false, "filename and status is not verified for "+filesShared);
				}
			}
			else {
				appLog.error("could not upload file in "+shdPath);
			}
		
			
			//standard
			if (fp.uploadFile(stdPath, M9Institution1+"/"+M9LimitedPartner1, docpath+stdFolderName+"_Single", null, UploadFileActions.Upload,
					Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
				appLog.info("File is upload successfully");
				String filesStd = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
				if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesStd,false )) {
					appLog.info("filename and status is verified successfully files present in std folder");
				}
				else {
					appLog.error("filename and status is not verified for "+filesStd);
					sa.assertTrue(false, "filename and status is not verified for "+filesStd);
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not upload file in "+stdPath);
			}
		}
		else {
			appLog.error("fund "+M9FundName1+" cannot be found");
			sa.assertTrue(false, "fund "+M9FundName1+" cannot be found");
		}

		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}

	lp.CRMlogout();
	sa.assertAll();
	}

	@Test
	public void M9tc036_VerifyApprovingDocumentsInvestors_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.UploadedFileStandard);
		String filesToDel[] = filesCommon.split("<break>");
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M9FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "workspace section view");
				if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getCheckAllDocsManageApprovals(60), "all checkbox", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getApproveBtnManageApprovals(60),"approve button on pending docs tab", action.SCROLLANDBOOLEAN)) {
								if (fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30)!=null) {
									appLog.info("yes button for manage approval approve popup is successfully displayed");
								}
								else {
									appLog.error("yes button is not present in manage approvals approve popup");
									sa.assertTrue(false, "yes button is not present in manage approvals approve popup");
								}
								if (fp.getManageApprovalApproveYesOrNo(YesNo.No, 30)!=null) {
									appLog.info("no button for manage approval approve popup is successfully displayed");
								}
								else {
									appLog.error("no button is not present in manage approvals approve popup");
									sa.assertTrue(false, "no button is not present in manage approvals approve popup");
								}
								if (fp.getManageApprovalApproveCrossIcon(60)!=null) {
									appLog.info("cross icon on approve poup is successfully displayed");
								}
								if (click(driver,fp.getManageApprovalApproveYesOrNo(YesNo.No, 30), "approve no button", action.SCROLLANDBOOLEAN)) {	
									appLog.info("no button is successfully clicked on approve popup");
									if ((isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[0]),"checkbox for "+filesToDel[0]))
										&& (isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[1]),"checkbox for "+filesToDel[1]))
										&& (isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[2]),"checkbox for "+filesToDel[2]))) {
											appLog.info("after clicking approve no button, still the 3 files checkboxes are checked");
										}
									else {
										appLog.error("checkbox for the 3 files to approve are not checked");
										sa.assertTrue(false, "checkbox for the 3 files to approve are not checked");
									}
								}
								else {
									appLog.error("no button on manage approvals approve popup is not clickable");
									sa.assertTrue(false, "no button on manage approvals approve popup is not clickable");
								}
							}
							if (click(driver,fp.getApproveBtnManageApprovals(60),"approve button on pending docs tab", action.SCROLLANDBOOLEAN)) {
								if (click(driver,fp.getManageApprovalApproveCrossIcon(60), "approve cross button", action.SCROLLANDBOOLEAN)) {	
									appLog.info("cross button is successfully clicked on approve popup");
								}
								else {
									appLog.error("cross button on manage approvals approve popup is not clickable");
									sa.assertTrue(false, "cross button on manage approvals approve popup is not clickable");
								}
								if ((isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[0]),"checkbox for "+filesToDel[0]))
										&& (isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[1]),"checkbox for "+filesToDel[1]))
										&& (isSelected(driver, fp.checkboxForFileInManageApprovals(filesToDel[2]),"checkbox for "+filesToDel[2]))) {
											appLog.info("after clicking approve no button, still the 3 files checkboxes are checked");
										}
									else {
										appLog.error("checkbox for the 3 files to approve are not checked");
										sa.assertTrue(false, "checkbox for the 3 files to approve are not checked");
									}
							}
							if (click(driver,fp.getApproveBtnManageApprovals(60),"approve button on pending docs tab", action.SCROLLANDBOOLEAN)) {
								if (click(driver,fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "approve yes button", action.SCROLLANDBOOLEAN)) {
									if (fp.getApproveConfirmPopupText(60).getText().trim().equals("3 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
										appLog.info("confirmation text for approval is verified successfully");
									}
									else {
										appLog.error("confirmation text for approval is incorrect");
										sa.assertTrue(false, "confirmation text for approval is incorrect");
									}
									if (fp.getManageApprovalsApproveConfirmationCrossIcon(60)!=null) {
										appLog.info("cross icon for successful approve is successfully displayed");
									}
									if (click(driver, fp.getManageApprovalsApproveConfirmationCrossIcon(60), "cross icon for successful approve icon", action.SCROLLANDBOOLEAN)) {
										if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
											appLog.info("no data to display is successfully displayed");
										}
									}
									else {
										appLog.error("cross icon on approve confirmation popup is not clickable");
										sa.assertTrue(false, "cross icon on approve confirmation popup is not clickable");
									}
									if (click(driver, fp.getApprovedDocsTab(60),"approved docs tab", action.SCROLLANDBOOLEAN)) {
										
										if (getSelectedOptionOfDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.ApprovedDocuments, 30), "dropdown of approved documents","text").equals(cmnPath)) {
											appLog.info("correct default selected option of dropdown is "+cmnPath);
										}
										else {
											appLog.error("default selected option of dropdown is incorrect. it is "+getSelectedOptionOfDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.ApprovedDocuments, 30), "dropdown of approved documents","text"));
											sa.assertTrue(false, "default selected option of dropdown is incorrect. it is "+getSelectedOptionOfDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.ApprovedDocuments, 30), "dropdown of approved documents","text"));
										}
										
										if (fp.verifyManageApprovalsDropdownContents(cmnPath, ManageApprovalTabs.ApprovedDocuments)) {
											appLog.info("dropdown content are successfully verified for common path");
										}
										else {
											appLog.error("dropdown contents for common folder could not be verified");
											sa.assertTrue(false, "dropdown contents for common folder could not be verified");
										}
										
									}
									//verifying documents in approved tab
									if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.ApprovedDocuments, filesToDel, M9FundName1+" > "+ cmnPath, "approved", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
										appLog.info("files are successfully present in manage approvals approved tab");
									}
									else {
										appLog.error("files are not present in approved docs tab manage approvals");
										sa.assertTrue(false, "files are not present in approved docs tab manage approvals");
									}
									click(driver, fp.getCrossIconManageApp(30), "close button manage approvals", action.SCROLLANDBOOLEAN);
									//verifying documents in funds page content grid
									if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
										if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesToDel[0],true)) {
											appLog.info("filesname and status is now succesfully verified on content grid "+PageName.FundsPage.toString()+" for "+cmnPath);
										}
										else {
											appLog.error("files name and status could not be verified");
											sa.assertTrue(false, "files name and status could not be verified");
										}
										if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesToDel[1],true)) {
											appLog.info("filesname and status is now succesfully verified on content grid "+PageName.FundsPage.toString()+" for "+cmnPath);
										}
										else {
											appLog.error("files name and status could not be verified");
											sa.assertTrue(false, "files name and status could not be verified");
										}
										if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesToDel[2],true)) {
											appLog.info("filesname and status is now succesfully verified on content grid "+PageName.FundsPage.toString()+" for "+cmnPath);
										}
										else {
											appLog.error("files name and status could not be verified");
											sa.assertTrue(false, "files name and status could not be verified");
										}
									}
									else {
										appLog.error(cmnPath+" is not found in folder structure");
										sa.assertTrue(false, cmnPath+" is not found in folder structure");
									}
								}
								else {
									appLog.error("manage approval approve yes button is not clicked");
									sa.assertTrue(false, "manage approval approve yes button is not clicked");
								}
							}
							else {
								appLog.error("manage approvals yes button is not clickable");
								sa.assertTrue(false, "manage approvals yes button is not clickable");
							}
						
						}
						else {
							appLog.error("all docs checkbox is not clickable on manage approval popup");
							sa.assertTrue(false, "all docs checkbox is not clickable on manage approval popup");
						}
					}
					else  {
						appLog.error("manage approval icon is not clickable");
						sa.assertTrue(false, "manage approval icon is not clickable");
					}
						
				}
				else {
					appLog.error(cmnPath+" is not found in folder path");
					sa.assertTrue(false, cmnPath+" is not found in folder path");
				}
				filesToDel = filesShared.split("<break>");
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getCheckAllDocsManageApprovals(60), "all checkbox", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getApproveBtnManageApprovals(60),"approve button on pending docs tab", action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "approve popup yes button", action.SCROLLANDBOOLEAN)) {

									if (fp.getApproveConfirmPopupText(60).getText().trim().equals("3 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
										appLog.info("confirmation text for approval is verified successfully");
									}
									else {
										appLog.error("confirmation text for approval is incorrect");
										sa.assertTrue(false, "confirmation text for approval is incorrect");
									}
									WebElement ele = isDisplayed(driver, FindElement(driver, "(//div[text()='Confirmation']/a/span)[1]", "manage approvals approve confirmation cross icon", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "manage approvals approve confirmation cross icon");
									if (ele!=null) {
										appLog.info("cross icon for successful approve is successfully displayed");
									}
									if (click(driver, ele, "cross icon for successful approve icon", action.BOOLEAN)) {
										if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
											appLog.info("no data to display is successfully displayed");
										}
									}
									if (click(driver, fp.getApprovedDocsTab(60),"approved docs tab", action.SCROLLANDBOOLEAN)) {
										if (fp.verifyManageApprovalsDropdownContents(shdPath, ManageApprovalTabs.ApprovedDocuments)) {
											appLog.info(shdPath+" dropdown contents are successfully verified");
										}
										else {
											appLog.error(shdPath+" contents could not be verified");
											sa.assertTrue(false, shdPath+" contents could not be verified");
										}
										if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.ApprovedDocuments, filesToDel, M9FundName1+" > "+ shdPath, "approved", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
											appLog.info("all files are present in manage approvals popup approved docs tab");
										}
										else {
											appLog.error("files are not present in approved docs tab");
											sa.assertTrue(false, "files are not present in approved docs tab");
										}
									}
									else {
										appLog.error("approved docs tab is not clickable on manage approvals popup");
										sa.assertTrue(false, "approved docs tab is not clickable on manage approvals popup");
									}

								}
								else {
									appLog.error("yes button on manage approvals approve popup is not clickable");
									sa.assertTrue(false, "yes button on manage approvals approve popup is not clickable");
								}
							}
							else {
								appLog.error("approve button is not clickable on manage approvals popup");
								sa.assertTrue(false, "approve button is not clickable on manage approvals popup");
							}
						}

						if (click(driver, fp.getCrossIconManageApp(30), "manage approvals close button", action.SCROLLANDBOOLEAN)) {
							if (fp.verifyFolderPathdummy(shdPath,null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
								if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesToDel[0],true)) {
									appLog.info(filesToDel[0] + " is successfully verified on content grid");
								}
								else {
									appLog.error(filesToDel[0]+" is not present on content grid fundraising workspace");
									sa.assertTrue(false, filesToDel[0]+" is not present on content grid fundraising workspace");
								}
								if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesToDel[1],true)) {
									appLog.info(filesToDel[1] + " is successfully verified on content grid");
								}
								else {
									appLog.error(filesToDel[1]+" is not present on content grid fundraising workspace");
									sa.assertTrue(false, filesToDel[1]+" is not present on content grid fundraising workspace");
								}
								if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesToDel[2],true)) {
									appLog.info(filesToDel[2] + " is successfully verified on content grid");
								}
								else {
									appLog.error(filesToDel[2]+" is not present on content grid fundraising workspace");
									sa.assertTrue(false, filesToDel[2]+" is not present on content grid fundraising workspace");
								}
							}
							else {
								appLog.error(shdPath+" is not found on folder structure");
								sa.assertTrue(false, shdPath+" is not found on folder structure");
							}
						}
						else {
							appLog.error("manage approvals close button is not clickable");
							sa.assertTrue(false, "manage approvals close button is not clickable");
						}
					}
					else  {
						appLog.error("manage approval icon is not clickable");
						sa.assertTrue(false, "manage approval icon is not clickable");
					}
				}
				else {
					appLog.error(shdPath+" is not found on folder structure");
					sa.assertTrue(false, shdPath+" is not found on folder structure");
				}
				filesToDel = filesStandard.split("<break>");
				if (fp.verifyFolderPathdummy(stdPath,M9Institution1, M9LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) { 
					if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getCheckAllDocsManageApprovals(60), "all checkbox", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getApproveBtnManageApprovals(60),"approve button on pending docs tab", action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "yes button on approve popup", action.SCROLLANDBOOLEAN)) {
									appLog.info("yes button for manage approval approve popup is successfully displayed");
									if (fp.getApproveConfirmPopupText(60).getText().trim().equals("3 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
										appLog.info("confirmation text for approval is verified successfully");
									}
									else {
										appLog.error("confirmation text for approval is incorrect");
										sa.assertTrue(false, "confirmation text for approval is incorrect");
									}
									WebElement ele = isDisplayed(driver, FindElement(driver, "(//div[text()='Confirmation']/a/span)[1]", "manage approvals approve confirmation cross icon", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "manage approvals approve confirmation cross icon");
									if (ele!=null) {
										appLog.info("cross icon for successful approve is successfully displayed");
									}
									if (click(driver, ele, "cross icon for successful approve icon", action.BOOLEAN)) {
										if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
											appLog.info("no data to display is successfully displayed");
										}
									}
									if (click(driver, fp.getApprovedDocsTab(60),"approved docs tab", action.SCROLLANDBOOLEAN)) {
										if (fp.verifyManageApprovalsDropdownContents(stdPath, ManageApprovalTabs.ApprovedDocuments)) {

										}
										if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.ApprovedDocuments, filesToDel, M9FundName1+" > " + M9Institution1+" > "+ M9LimitedPartner1+" > "+ stdPath, "approved", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {

										}
									}
									else {
										appLog.error("approved docs tab is not clickable on manage approvals popup");
										sa.assertTrue(false, "approved docs tab is not clickable on manage approvals popup");
									}
								}
								else {
									appLog.error("yes button is not clickable on manage approvals aprove popup");
									sa.assertTrue(false, "yes button is not clickable on manage approvals aprove popup");
								}
							}
							else {
								appLog.error("approve button is not clickable on manage approvals popup");
								sa.assertTrue(false, "approve button is not clickable on manage approvals popup");
							}
						}
						else {
							appLog.error("checkbox for checking all docs in manage approvals popup is not clickable");
							sa.assertTrue(false, "checkbox for checking all docs in manage approvals popup is not clickable");
						}

					}
					else {
						appLog.error("manage approvals icon is not clickable");
						sa.assertTrue(false, "manage approvals icon is not clickable");
					}
				}
				if (click(driver, fp.getCrossIconManageApp(60), "manage approvals close button", action.SCROLLANDBOOLEAN)) {
					if (fp.verifyFolderPathdummy(stdPath,M9Institution1, M9LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesToDel[0],true)) {
							appLog.info(filesToDel[0] + " is successfully verified on funds page content grid");
						}
						else {
							appLog.error(filesToDel[0] + " is not present on content grid funds page");
							sa.assertTrue(false, filesToDel[0] + " is not present on content grid funds page");
						}
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesToDel[1],true)) {
							appLog.info(filesToDel[1] + " is successfully verified on funds page content grid");
						}
						else {
							appLog.error(filesToDel[1] + " is not present on content grid funds page");
							sa.assertTrue(false, filesToDel[1] + " is not present on content grid funds page");
						}
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesToDel[2],true)) {
							appLog.info(filesToDel[2] + " is successfully verified on funds page content grid");
						}
						else {
							appLog.error(filesToDel[2] + " is not present on content grid funds page");
							sa.assertTrue(false, filesToDel[2] + " is not present on content grid funds page");
						}
					}
					else {
						appLog.error(stdPath+" for "+M9Institution1+" is not displayed");
						sa.assertTrue(false, stdPath+" for "+M9Institution1+" is not displayed");
					}
				}
				else {
					appLog.error("manage approvals close button is not clickable");
					sa.assertTrue(false, "manage approvals close button is not clickable");
				}
				
				filesToDel = filesStandard.split("<break>");
				if (fp.verifyFolderPathdummy(stdPath,M9Institution2, M9LimitedPartner2, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) { 
					if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getCheckAllDocsManageApprovals(60), "all checkbox", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getApproveBtnManageApprovals(60),"approve button on pending docs tab", action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "approve popup yes button", action.SCROLLANDBOOLEAN)) {
									appLog.info("yes button for manage approval approve popup is successfully displayed");
									if (fp.getApproveConfirmPopupText(60).getText().trim().equals("3 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
										appLog.info("confirmation text for approval is verified successfully");
									}
									else {
										appLog.error("confirmation text for approval is incorrect");
										sa.assertTrue(false, "confirmation text for approval is incorrect");
									}
									WebElement ele = isDisplayed(driver, FindElement(driver, "(//div[text()='Confirmation']/a/span)[1]", "manage approvals approve confirmation cross icon", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "manage approvals approve confirmation cross icon");
									if (ele!=null) {
										appLog.info("cross icon for successful approve is successfully displayed");
									}
									if (click(driver, ele, "cross icon for successful approve icon", action.BOOLEAN)) {
										if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
											appLog.info("no data to display is successfully displayed");
										}
										else {
											appLog.error("no data to display is not displayed on pending documents");
											sa.assertTrue(false, "no data to display is not displayed on pending documents");
										}
									}
									if (click(driver, fp.getApprovedDocsTab(60),"approved docs tab", action.SCROLLANDBOOLEAN)) {
										if (fp.verifyManageApprovalsDropdownContents(stdPath, ManageApprovalTabs.ApprovedDocuments)) {
											appLog.info(stdPath+" dropdown contents are successfully verified");
										}
										else {
											appLog.error(stdPath+" dropdown contents could not be verified");
											sa.assertTrue(false, stdPath+" dropdown contents could not be verified");
										}
										if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.ApprovedDocuments, filesToDel, M9FundName1+" > " + M9Institution2+" > "+ M9LimitedPartner2+" > "+stdPath, "approved", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
											appLog.info("standard files are verified successfully on manage approvals window");
										}
										else {
											appLog.error("standard files could not be verified on manage approvals window");
											sa.assertTrue(false, "standard files could not be verified on manage approvals window");
										}
									}
										else {
												appLog.error("approved docs tab is not clickable on manage approvals popup");
												sa.assertTrue(false, "approved docs tab is not clickable on manage approvals popup");
											}
										}
										else {
											appLog.error("yes button is not clickable on manage approvals aprove popup");
											sa.assertTrue(false, "yes button is not clickable on manage approvals aprove popup");
										}
									}
									else {
										appLog.error("approve button is not clickable on manage approvals popup");
										sa.assertTrue(false, "approve button is not clickable on manage approvals popup");
									}
								}
								else {
									appLog.error("checkbox for checking all docs in manage approvals popup is not clickable");
									sa.assertTrue(false, "checkbox for checking all docs in manage approvals popup is not clickable");
								}

							}
							else {
								appLog.error("manage approvals icon is not clickable");
								sa.assertTrue(false, "manage approvals icon is not clickable");
							}
				}
				else {
					appLog.error(stdPath+" for "+M9Institution2+" is not clickable");
					sa.assertTrue(false, stdPath+" for "+M9Institution2+" is not clickable");
				}
					
				if (click(driver, fp.getCrossIconManageApp(60), "manage approvals close button", action.SCROLLANDBOOLEAN)) {
					if (fp.verifyFolderPathdummy(stdPath,M9Institution2, M9LimitedPartner2, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesToDel[0],true)) {
							appLog.info(filesToDel[0] + " is successfully verified on funds page content grid");
						}
						else {
							appLog.error(filesToDel[0] + " is not present on content grid funds page");
							sa.assertTrue(false, filesToDel[0] + " is not present on content grid funds page");
						}
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesToDel[1],true)) {
							appLog.info(filesToDel[1] + " is successfully verified on funds page content grid");
						}
						else {
							appLog.error(filesToDel[1] + " is not present on content grid funds page");
							sa.assertTrue(false, filesToDel[1] + " is not present on content grid funds page");
						}
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesToDel[2],true)) {
							appLog.info(filesToDel[2] + " is successfully verified on funds page content grid");
						}
						else {
							appLog.error(filesToDel[2] + " is not present on content grid funds page");
							sa.assertTrue(false, filesToDel[2] + " is not present on content grid funds page");
						}
					}
					else {
						appLog.error(stdPath+" for "+M9Institution2+"is not found on folder structure");
						sa.assertTrue(false, stdPath+" for "+M9Institution2+"is not found on folder structure");
					}
				}
				else {
					appLog.error("close button is not clickable on manage approvals window");
					sa.assertTrue(false, "close button is not clickable on manage approvals window");
				}

			}
			else {
				appLog.error(M9FundName1+" is not found on funds page");
				sa.assertTrue(false, M9FundName1+" is not found on funds page");
			}
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc036_VerifyApprovingDocumentsInvestors_Impact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc036_VerifyApprovingDocumentsInvestors_Action", excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc036_VerifyApprovingDocumentsInvestors_Action", excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc036_VerifyApprovingDocumentsInvestors_Action", excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.UploadedFileStandard);
		String filesToDel[] = filesCommon.split("<break>");
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		//Institution
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M9Institution1)) {
				switchToFrame(driver, 30, ip.getFrame( PageName.InstitutionsPage, 30));
				
				if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[0], true)) {
						appLog.info(filesToDel[0] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToDel[0] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToDel[0] + " is not verified on content grid "+cmnPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[0], true)) {
						appLog.info(filesToDel[1] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToDel[1] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToDel[1] + " is not verified on content grid "+cmnPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[2], true)) {
						appLog.info(filesToDel[2] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToDel[2] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToDel[2] + " is not verified on content grid "+cmnPath);
					}
				}
				else {
					appLog.error(cmnPath+" is not present on folder structure");
					sa.assertTrue(false, cmnPath+" is not present on folder structure");
					
				}
				filesToDel = filesShared.split("<break>");
				if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[0], true)) {
						appLog.info(filesToDel[0] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToDel[0] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToDel[0] + " is not verified on content grid "+shdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[0], true)) {
						appLog.info(filesToDel[1] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToDel[1] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToDel[1] + " is not verified on content grid "+shdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[2], true)) {
						appLog.info(filesToDel[2] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToDel[2] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToDel[2] + " is not verified on content grid "+shdPath);
					}
				}
				else {
					appLog.error(shdPath+" is not present on folder structure");
					sa.assertTrue(false, shdPath+" is not present on folder structure");
					
				}
				
				filesToDel = filesStandard.split("<break>");
				if (fp.verifyFolderPathdummy(stdPath, null, M9LimitedPartner1, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[0], true)) {
						appLog.info(filesToDel[0] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToDel[0] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToDel[0] + " is not verified on content grid "+stdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[1], true)) {
						appLog.info(filesToDel[1] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToDel[1] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToDel[1] + " is not verified on content grid "+stdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[2], true)) {
						appLog.info(filesToDel[2] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToDel[2] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToDel[2] + " is not verified on content grid "+stdPath);
					}
				}
				else {
					appLog.error(stdPath+" is not present on folder structure");
					sa.assertTrue(false, stdPath+" is not present on folder structure");
					
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9Institution1+" is not present on institutions page");
				sa.assertTrue(false, M9Institution1+" is not present on institutions page");
			}
		}
		else {
			appLog.error("institutions tab is not clickable");
			sa.assertTrue(false, "institutions tab is not clickable");
		}
		//Contacts
		filesToDel = filesCommon.split("<break>");
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M9Contact1FirstName, M9Contact1LastName, null)) {
				switchToFrame(driver, 30, fp.getFrame(environment,mode, PageName.ContactsPage, 30));
				if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					for (int i = 0;i<filesToDel.length;i++) {
						if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesToDel[i], true)) {
							appLog.info(filesToDel[i] + " is successfully verified on content grid folder "+cmnPath);
						}
						else {
							appLog.error(filesToDel[i] + " is not verified on content grid "+cmnPath);
							sa.assertTrue(false, filesToDel[i] + " is not verified on content grid "+cmnPath);
						}
					}
				}
				else {
					appLog.error(cmnPath+" is not present on folder structure");
					sa.assertTrue(false, cmnPath+" is not present on folder structure");

				}
					
				filesToDel = filesShared.split("<break>");
				if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					for (int i = 0;i<filesToDel.length;i++) {
						if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesToDel[i], true)) {
							appLog.info(filesToDel[i] + " is successfully verified on content grid folder "+shdPath);
						}
						else {
							appLog.error(filesToDel[i] + " is not verified on content grid "+shdPath);
							sa.assertTrue(false, filesToDel[i] + " is not verified on content grid "+shdPath);
						}
					}
				}
				else {
					appLog.error(shdPath+" is not present on folder structure");
					sa.assertTrue(false, shdPath+" is not present on folder structure");
				}
				
				filesToDel = filesStandard.split("<break>");
				if (fp.verifyFolderPathdummy(stdPath, M9Institution1, M9LimitedPartner1, M9FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					for (int i = 0;i<filesToDel.length;i++) {
						if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesToDel[i], true)) {
							appLog.info(filesToDel[i] + " is successfully verified on content grid folder "+stdPath);
						}
						else {
							appLog.error(filesToDel[i] + " is not verified on content grid "+stdPath);
							sa.assertTrue(false, filesToDel[i] + " is not verified on content grid "+stdPath);
						}
					}
				}
				else {
					appLog.error(stdPath+" is not present on folder structure");
					sa.assertTrue(false, stdPath+" is not present on folder structure");

				}
				
				if (fp.verifyFolderPathdummy(stdPath, M9Institution2, M9LimitedPartner2, M9FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					for (int i = 0;i<filesToDel.length;i++) {
						if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesToDel[i], true)) {
							appLog.info(filesToDel[i] + " is successfully verified on content grid folder "+stdPath);
						}
						else {
							appLog.error(filesToDel[i] + " is not verified on content grid "+stdPath);
							sa.assertTrue(false, filesToDel[i] + " is not verified on content grid "+stdPath);
						}
					}
				}
				else {
					appLog.error(stdPath+" is not present on folder structure");
					sa.assertTrue(false, stdPath+" is not present on folder structure");

				}
				switchToDefaultContent(driver);
			}
		}
		filesToDel = filesCommon.split("<break>");
		if (bp.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.clickOnCreatedCommitmentId(M9Commitment1ID)) {
				
				switchToFrame(driver, 30, fp.getFrame(environment,mode, PageName.CommitmentsPage, 30));
				if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
					for (int i = 0;i<filesToDel.length;i++) {
						if (bp.verifyFileinContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace, filesToDel[i], true)) {
							appLog.info(filesToDel[i] + " is successfully verified on content grid folder "+cmnPath);
						}
						else {
							appLog.error(filesToDel[i] + " is not verified on content grid "+cmnPath);
							sa.assertTrue(false, filesToDel[i] + " is not verified on content grid "+cmnPath);
						}
					}
				}
				else {
					appLog.error(cmnPath+" is not present on folder structure");
					sa.assertTrue(false, cmnPath+" is not present on folder structure");

				}
					
				filesToDel = filesShared.split("<break>");
				if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
					for (int i = 0;i<filesToDel.length;i++) {
						if (bp.verifyFileinContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace, filesToDel[i], true)) {
							appLog.info(filesToDel[i] + " is successfully verified on content grid folder "+shdPath);
						}
						else {
							appLog.error(filesToDel[i] + " is not verified on content grid "+shdPath);
							sa.assertTrue(false, filesToDel[i] + " is not verified on content grid "+shdPath);
						}
					}
				}
				else {
					appLog.error(shdPath+" is not present on folder structure");
					sa.assertTrue(false, shdPath+" is not present on folder structure");
				}
				
				filesToDel = filesStandard.split("<break>");
				if (fp.verifyFolderPathdummy(stdPath, null, M9LimitedPartner1, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
					for (int i = 0;i<filesToDel.length;i++) {
						if (bp.verifyFileinContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace, filesToDel[i], true)) {
							appLog.info(filesToDel[i] + " is successfully verified on content grid folder "+stdPath);
						}
						else {
							appLog.error(filesToDel[i] + " is not verified on content grid "+stdPath);
							sa.assertTrue(false, filesToDel[i] + " is not verified on content grid "+stdPath);
						}
					}
				}
				else {
					appLog.error(stdPath+" is not present on folder structure");
					sa.assertTrue(false, stdPath+" is not present on folder structure");

				}
				switchToDefaultContent(driver);
			}
		}
		//LP
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedLP(M9LimitedPartner1)) {
				filesToDel = filesCommon.split("<break>");
				switchToFrame(driver, 30, fp.getFrame(environment,mode, PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					for (int i = 0;i<filesToDel.length;i++) {
						if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[i], true)) {
							appLog.info(filesToDel[i] + " is successfully verified on content grid folder "+cmnPath);
						}
						else {
							appLog.error(filesToDel[i] + " is not verified on content grid "+cmnPath);
							sa.assertTrue(false, filesToDel[i] + " is not verified on content grid "+cmnPath);
						}
					}
				}
				else {
					appLog.error(cmnPath+" is not present on folder structure");
					sa.assertTrue(false, cmnPath+" is not present on folder structure");

				}
					
				filesToDel = filesShared.split("<break>");
				if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					for (int i = 0;i<filesToDel.length;i++) {
						if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[i], true)) {
							appLog.info(filesToDel[i] + " is successfully verified on content grid folder "+shdPath);
						}
						else {
							appLog.error(filesToDel[i] + " is not verified on content grid "+shdPath);
							sa.assertTrue(false, filesToDel[i] + " is not verified on content grid "+shdPath);
						}
					}
				}
				else {
					appLog.error(shdPath+" is not present on folder structure");
					sa.assertTrue(false, shdPath+" is not present on folder structure");
				}
				
				filesToDel = filesStandard.split("<break>");
				if (fp.verifyFolderPathdummy(stdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					for (int i = 0;i<filesToDel.length;i++) {
						if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToDel[i], true)) {
							appLog.info(filesToDel[i] + " is successfully verified on content grid folder "+stdPath);
						}
						else {
							appLog.error(filesToDel[i] + " is not verified on content grid "+stdPath);
							sa.assertTrue(false, filesToDel[i] + " is not verified on content grid "+stdPath);
						}
					}
				}
				else {
					appLog.error(stdPath+" is not present on folder structure");
					sa.assertTrue(false, stdPath+" is not present on folder structure");

				}
				switchToDefaultContent(driver);
				
				
			}
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc036_VerifyApprovingDocumentsInvestors_ImpactInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc036_VerifyApprovingDocumentsInvestors_Impact", excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc036_VerifyApprovingDocumentsInvestors_Impact", excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc036_VerifyApprovingDocumentsInvestors_Impact", excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.UploadedFileStandard);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		SoftAssert saa = new SoftAssert();
		List<String> fail_list = null;
		lp.investorLogin(M9Contact1EmailId, adminPassword);
		if (selectVisibleTextFromDropDown(driver, ifp.getFirmNameDropdownWRTPage(PageName.AllFirmsPage), "All Firms", "All Firms")) {
			ThreadSleep(5000);
			fail_list=afp.verifyAlertsOnAllFirmsPage(filesCommon+"<break>"+filesShared, Org1FirmName, M9FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpload, PageName.AllFirmsPage); 				
			if (fail_list.isEmpty()) {
				appLog.info("successfully verified common and shared files on all firms alerts");
				}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.error("docs could not be verified on all firms page");
					sa.assertTrue(false, "docs could not be verified on all firms page");
				}
				
			}
		}
		else {
			appLog.error("could not find all firms option in dropdown");
			sa.assertTrue(false, "could not find all firms option in dropdown");
		}
		if (selectVisibleTextFromDropDown(driver, ifp.getFirmNameDropdownWRTPage(PageName.AllFirmsPage),Org1FirmName,Org1FirmName)) {
			click(driver, ifp.getAllDocumentsTab(30), "all documents tab", action.BOOLEAN);
			Set<String> allDocsFiles =scrollActiveWidgetforSetofFiles(driver, ifp.getScrollBoxAtFirmPage(30), By.xpath("//span[contains(@id,'myGrid_firmAllDoc-row')]//span[contains(@id,'cell-0')]/a"));
			for (int i = 0;i<filesCommon.split("<break>").length;i++) {
				if (!allDocsFiles.contains(filesCommon.split("<break>")[i])) {
					appLog.error("could not find "+filesCommon.split("<break>")[i]+" on all docs grid");
					sa.assertTrue(false, "could not find "+filesCommon.split("<break>")[i]+" on all docs grid");
				}
				else
					appLog.info("successfully verified "+filesCommon.split("<break>")[i]+" on all docs grid");
			}
			
			for (int i = 0;i<filesShared.split("<break>").length;i++) {
				if (!allDocsFiles.contains(filesShared.split("<break>")[i])) {
					appLog.error("could not find "+filesShared.split("<break>")[i]+" on all docs grid");
					sa.assertTrue(false, "could not find "+filesShared.split("<break>")[i]+" on all docs grid");
				}
				else
					appLog.info("successfully verified "+filesShared.split("<break>")[i]+" on all docs grid");
			}
			
			click(driver, ifp.getRecentActivitiesTab(30), "recent activities tab", action.BOOLEAN);
			fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(filesCommon+"<break>"+filesShared, M9FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities, activityType.DocumentUpload, PageName.AllFirmsPage);
			if (fail_list.isEmpty()) {
				appLog.info("successfully verified common and shared files on recent activities page");
					}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.error("could not verify common and shared files alerts on recent activities tab");
					sa.assertTrue(false, "could not verify common and shared files alerts on recent activities tab");
				}
				
			}
		}
		else {
			appLog.error("could not find firm name in dropdown selection");
			sa.assertTrue(false, "could not find firm name in dropdown selection");
		}
		
		
		ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment);
		if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver,PageName.CurrentInvestmentPgae, filesCommon,CRMUser1FirstName+" "+CRMUser1LastName,date );
				sa.combineAssertions(saa);
		}
		else {
			appLog.error(cmnPath+" is not present on folder structure");
			sa.assertTrue(false, cmnPath+" is not present on folder structure");
			
		}
		if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver,PageName.CurrentInvestmentPgae, filesShared,CRMUser1FirstName+" "+CRMUser1LastName,date );
				sa.combineAssertions(saa);
		}
		else {
			appLog.error(shdPath+" is not present on folder structure");
			sa.assertTrue(false, shdPath+" is not present on folder structure");
			
		}
		
		if (fp.verifyFolderPathdummy(stdPath, M9Institution1, M9LimitedPartner1, M9FundName1, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver,PageName.CurrentInvestmentPgae, filesStandard,CRMUser1FirstName+" "+CRMUser1LastName,date );
				sa.combineAssertions(saa);
		}
		else {
			appLog.error(stdPath+" is not present on folder structure");
			sa.assertTrue(false, stdPath+" is not present on folder structure");
			
		}

		if (fp.verifyFolderPathdummy(stdPath, M9Institution2, M9LimitedPartner2, M9FundName1, PageName.CurrentInvestmentPgae, Workspace.FundraisingWorkspace, 30)) {
			saa=ifp.verifyContentGridInvestorSide(driver,PageName.CurrentInvestmentPgae, filesStandard,CRMUser1FirstName+" "+CRMUser1LastName,date );
			sa.combineAssertions(saa);
		}
		else {
			appLog.error(stdPath+" is not present on folder structure");
			sa.assertTrue(false, stdPath+" is not present on folder structure");

		}
		lp.investorLogout();
		sa.assertAll();
		
	}

	@Test
	public void M9tc037_VerifyOnlineImportInvestors_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String intPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		String docPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.OnlineImportPath);
		String fileCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
		String fileInternal = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);

		String fileShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared);
		String fileStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String filesToImport[] = fileCommon.split("<break>");
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				if (fp.onlineImport(environment, mode, null, null, null, cmnPath, docPath, fileCommon, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Common, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("online import is done successfully for "+cmnPath);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesToImport[0] , false)) {
						appLog.info(filesToImport[0] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[0] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[0] + " could not be found on content grid");
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesToImport[1] , false)) {
						appLog.info(filesToImport[1] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[1] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[1] + " could not be found on content grid");
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesToImport[2] , false)) {
						appLog.info(filesToImport[2] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[2] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[2] + " could not be found on content grid");
					}
					if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30),"manage approval icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
						if (fp.verifyManageApprovalsDropdownContents(cmnPath, ManageApprovalTabs.PendingDocuments)) {
							appLog.info("manage approval dropdown contents are successfully verified");
						}
						else {
							appLog.error("manage approval dropdown contents are wrong");
							sa.assertTrue(false, "manage approval dropdown contents are wrong");
						}
						if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesToImport , M9FundName1+" > "+cmnPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
							appLog.info("imported files are successfully verified on manage approvals popup");
						}
						else {
							appLog.error("imported files could not be verified on manage approvals popup");
							sa.assertTrue(false, "imported files could not be verified on manage approvals popup");
						}
						if (click(driver, fp.getManageApprovalsCancelBtn(60), "manage approval pendings tab cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on cancel button");
						}
						else {
							appLog.error("cannot click on cancel button");
						}
					}
				}
				else {
					appLog.error("online import was unsuccessfull for "+cmnPath);
					sa.assertTrue(false, "online import was unsuccessfull for "+cmnPath);
				}

				//internal
				filesToImport = fileInternal.split("<break>");
				switchToDefaultContent(driver);
				if (fp.onlineImport(environment, mode, null, null, null, intPath, docPath, fileInternal, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPDATE, FolderType.Internal, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("online import is done successfully for "+intPath);

					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));

					if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30),"manage approval icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
						click(driver, fp.getApprovedDocsTab(60), "approved documents tab", action.BOOLEAN);
						if (fp.verifyManageApprovalsDropdownContents(intPath, ManageApprovalTabs.ApprovedDocuments)) {
							appLog.info("manage approval dropdown contents are successfully verified");
						}
						else {
							appLog.error("manage approval dropdown contents are wrong");
							sa.assertTrue(false, "manage approval dropdown contents are wrong");
						}
						if (fp.noDataToDisplay(ManageApprovalTabs.ApprovedDocuments, 60).getText().trim().equals(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
							appLog.info("no data to display error message is verified succeessfully");
						}
						else {
							appLog.error("no data to display is not verified on approved documents for internal folder");
							sa.assertTrue(false, "no data to display is not verified on approved documents for internal folder");
						}
						if (click(driver, fp.getCrossIconManageApp(30), "manage approval pendings tab cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on cancel button");
						}
						else {
							appLog.error("cannot click on cancel button");
						}
					}
				}
				else {
					appLog.error("online import was unsuccessfull for "+cmnPath);
					sa.assertTrue(false, "online import was unsuccessfull for "+cmnPath);
				}

				//shared
				filesToImport = fileShared.split("<break>");
				switchToDefaultContent(driver);
				if (fp.onlineImport(environment, mode, null, null, null, shdPath, docPath, fileShared, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Shared, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("online import is done successfully for "+shdPath);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesToImport[0] , false)) {
						appLog.info(filesToImport[0] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[0] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[0] + " could not be found on content grid");
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesToImport[1] , false)) {
						appLog.info(filesToImport[1] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[1] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[1] + " could not be found on content grid");
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesToImport[2] , false)) {
						appLog.info(filesToImport[2] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[2] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[2] + " could not be found on content grid");
					}
					if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30),"manage approval icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
						if (fp.verifyManageApprovalsDropdownContents(shdPath, ManageApprovalTabs.PendingDocuments)) {
							appLog.info("manage approval dropdown contents are successfully verified");
						}
						else {
							appLog.error("manage approval dropdown contents are wrong");
							sa.assertTrue(false, "manage approval dropdown contents are wrong");
						}
						if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesToImport , M9FundName1+" > "+shdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
							appLog.info("imported files are successfully verified on manage approvals popup");
						}
						else {
							appLog.error("imported files could not be verified on manage approvals popup");
							sa.assertTrue(false, "imported files could not be verified on manage approvals popup");
						}
						if (click(driver, fp.getManageApprovalsCancelBtn(60), "manage approval pendings tab cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on cancel button");
						}
						else {
							appLog.error("cannot click on cancel button");
						}
					}
				}
				else {
					appLog.error("online import was unsuccessfull for "+shdPath);
					sa.assertTrue(false, "online import was unsuccessfull for "+shdPath);
				}



				//standard
				filesToImport = fileStandard.split("<break>");
				switchToDefaultContent(driver);
				if (fp.onlineImport(environment, mode, M9Institution1, M9LimitedPartner1, M9LimitedPartner2, stdPath, docPath, fileStandard, BoxUserName, BoxPassword, OnlineImportFileAddTo.MultipleInstitute, WorkSpaceAction.UPLOAD, FolderType.Standard, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					appLog.info("online import is done successfully for "+stdPath);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "content grid refresh button", action.SCROLLANDBOOLEAN);
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesToImport[0] , false)) {
						appLog.info(filesToImport[0] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[0] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[0] + " could not be found on content grid");
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesToImport[1] , false)) {
						appLog.info(filesToImport[1] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[1] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[1] + " could not be found on content grid");
					}
					if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesToImport[2] , false)) {
						appLog.info(filesToImport[2] + " was successfully found on content grid");
					}
					else {
						appLog.error(filesToImport[2] + " could not be found on content grid");
						sa.assertTrue(false, filesToImport[2] + " could not be found on content grid");
					}
					if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30),"manage approval icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
						if (fp.verifyManageApprovalsDropdownContents(stdPath, ManageApprovalTabs.PendingDocuments)) {
							appLog.info("manage approval dropdown contents are successfully verified");
						}
						else {
							appLog.error("manage approval dropdown contents are wrong");
							sa.assertTrue(false, "manage approval dropdown contents are wrong");
						}
						if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesToImport , M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+" > "  +stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
							appLog.info("imported files are successfully verified on manage approvals popup");
						}
						else {
							appLog.error("imported files could not be verified on manage approvals popup");
							sa.assertTrue(false, "imported files could not be verified on manage approvals popup");
						}
						if (click(driver, fp.getManageApprovalsCancelBtn(60), "manage approval pendings tab cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on cancel button");
						}
						else {
							appLog.error("cannot click on cancel button");
						}
					}
					//std inst2
					if (fp.verifyFolderPathdummy(stdPath, M9Institution2, M9LimitedPartner2, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesToImport[0] , false)) {
							appLog.info(filesToImport[0] + " was successfully found on content grid");
						}
						else {
							appLog.error(filesToImport[0] + " could not be found on content grid");
							sa.assertTrue(false, filesToImport[0] + " could not be found on content grid");
						}
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesToImport[1] , false)) {
							appLog.info(filesToImport[1] + " was successfully found on content grid");
						}
						else {
							appLog.error(filesToImport[1] + " could not be found on content grid");
							sa.assertTrue(false, filesToImport[1] + " could not be found on content grid");
						}
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,filesToImport[2] , false)) {
							appLog.info(filesToImport[2] + " was successfully found on content grid");
						}
						else {
							appLog.error(filesToImport[2] + " could not be found on content grid");
							sa.assertTrue(false, filesToImport[2] + " could not be found on content grid");
						}
					}
				}
				else {
					appLog.error("online import was unsuccessfull for "+stdPath);
					sa.assertTrue(false, "online import was unsuccessfull for "+stdPath);
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("fund "+M9FundName1+" could not be found");
				sa.assertTrue(false, "fund "+M9FundName1+" could not be found");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc037_VerifyOnlineImportInvestors_Impact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String intPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileCommon);
		String filesInternal = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileInternal);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileStandard);
		String filesToImport[] = filesCommon.split("<break>");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M9Institution1)) {
				switchToFrame(driver, 30, ip.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToImport[0], false)) {
						appLog.info(filesToImport[0] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToImport[0] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToImport[0] + " is not verified on content grid "+cmnPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToImport[1], false)) {
						appLog.info(filesToImport[1] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToImport[1] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToImport[1] + " is not verified on content grid "+cmnPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToImport[2], false)) {
						appLog.info(filesToImport[2] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToImport[2] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToImport[2] + " is not verified on content grid "+cmnPath);
					}
				}
				else {
					appLog.error(cmnPath+" is not present on folder structure");
					sa.assertTrue(false, cmnPath+" is not present on folder structure");
					
				}
				//internal
				filesToImport = filesInternal.split("<break>");
				if (fp.verifyFolderPathdummy(intPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToImport[0], true)) {
						appLog.info(filesToImport[0] + " is successfully verified on content grid folder "+intPath);
					}
					else {
						appLog.error(filesToImport[0] + " is not verified on content grid "+intPath);
						sa.assertTrue(false, filesToImport[0] + " is not verified on content grid "+intPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToImport[1], true)) {
						appLog.info(filesToImport[1] + " is successfully verified on content grid folder "+intPath);
					}
					else {
						appLog.error(filesToImport[1] + " is not verified on content grid "+intPath);
						sa.assertTrue(false, filesToImport[1] + " is not verified on content grid "+intPath);
					}
				}
				else {
					appLog.error(intPath+" is not present on folder structure");
					sa.assertTrue(false, intPath+" is not present on folder structure");
					
				}
				//shared
				filesToImport = filesShared.split("<break>");
				if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToImport[0], false)) {
						appLog.info(filesToImport[0] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToImport[0] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToImport[0] + " is not verified on content grid "+shdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToImport[1], false)) {
						appLog.info(filesToImport[1] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToImport[1] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToImport[1] + " is not verified on content grid "+shdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToImport[2], false)) {
						appLog.info(filesToImport[2] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToImport[2] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToImport[2] + " is not verified on content grid "+shdPath);
					}
				}
				else {
					appLog.error(shdPath+" is not present on folder structure");
					sa.assertTrue(false, shdPath+" is not present on folder structure");
					
				}
				//standard
				filesToImport = filesStandard.split("<break>");
				if (fp.verifyFolderPathdummy(stdPath, null, M9LimitedPartner1, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToImport[0], false)) {
						appLog.info(filesToImport[0] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToImport[0] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToImport[0] + " is not verified on content grid "+stdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToImport[1], false)) {
						appLog.info(filesToImport[1] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToImport[1] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToImport[1] + " is not verified on content grid "+stdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesToImport[2], false)) {
						appLog.info(filesToImport[2] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToImport[2] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToImport[2] + " is not verified on content grid "+stdPath);
					}
				}
				else {
					appLog.error(stdPath+" is not present on folder structure");
					sa.assertTrue(false, stdPath+" is not present on folder structure");
					
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9Institution1+" is not present on institutions page");
				sa.assertTrue(false, M9Institution1+" is not present on institutions page");
			}
		}
		else {
			appLog.error("institutions tab is not clickable");
			sa.assertTrue(false, "institutions tab is not clickable");
		}
		filesToImport = filesCommon.split("<break>");
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M9Contact1FirstName, M9Contact1LastName, null)) {
				switchToFrame(driver, 30, fp.getFrame(environment,mode, PageName.ContactsPage, 30));
				if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesToImport[0], false)) {
						appLog.info(filesToImport[0] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToImport[0] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToImport[0] + " is not verified on content grid "+cmnPath);
					}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesToImport[1], false)) {
						appLog.info(filesToImport[1] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToImport[1] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToImport[1] + " is not verified on content grid "+cmnPath);
					}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesToImport[2], false)) {
						appLog.info(filesToImport[2] + " is successfully verified on content grid folder "+cmnPath);
					}
					else {
						appLog.error(filesToImport[2] + " is not verified on content grid "+cmnPath);
						sa.assertTrue(false, filesToImport[2] + " is not verified on content grid "+cmnPath);
					}
				}
				else {
					appLog.error(cmnPath+" is not present on folder structure");
					sa.assertTrue(false, cmnPath+" is not present on folder structure");
					
				}
				filesToImport = filesShared.split("<break>");
				if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesToImport[0], false)) {
						appLog.info(filesToImport[0] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToImport[0] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToImport[0] + " is not verified on content grid "+shdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesToImport[1], false)) {
						appLog.info(filesToImport[1] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToImport[1] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToImport[1] + " is not verified on content grid "+shdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesToImport[2], false)) {
						appLog.info(filesToImport[2] + " is successfully verified on content grid folder "+shdPath);
					}
					else {
						appLog.error(filesToImport[2] + " is not verified on content grid "+shdPath);
						sa.assertTrue(false, filesToImport[2] + " is not verified on content grid "+shdPath);
					}
				}
				else {
					appLog.error(shdPath+" is not present on folder structure");
					sa.assertTrue(false, shdPath+" is not present on folder structure");
					
				}
				
				filesToImport = filesStandard.split("<break>");
				if (fp.verifyFolderPathdummy(stdPath, M9Institution1, M9LimitedPartner1, M9FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesToImport[0], false)) {
						appLog.info(filesToImport[0] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToImport[0] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToImport[0] + " is not verified on content grid "+stdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesToImport[1], false)) {
						appLog.info(filesToImport[1] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToImport[1] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToImport[1] + " is not verified on content grid "+stdPath);
					}
					if (bp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesToImport[2], false)) {
						appLog.info(filesToImport[2] + " is successfully verified on content grid folder "+stdPath);
					}
					else {
						appLog.error(filesToImport[2] + " is not verified on content grid "+stdPath);
						sa.assertTrue(false, filesToImport[2] + " is not verified on content grid "+stdPath);
					}
				}
				else {
					appLog.error(stdPath+" is not present on folder structure");
					sa.assertTrue(false, stdPath+" is not present on folder structure");
					
				}
					switchToDefaultContent(driver);
					
			}
		}
		
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc038_VerifySearchingManageApprovalsInvestor() {
	LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
	BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
	FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
	String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
	String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
	String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
	String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileCommon);
	String filesInternal = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileInternal);
	String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileShared);
	String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileStandard);
	String filesToSearch[] = filesCommon.split("<break>");
	String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
	int size;
	String grid[][]=new String[12][12];
	//filling data in grid
	for (int j = 0;j<3;j++) {
		grid[j][0] = filesCommon.split("<break>")[j];
		grid[j][1] = "pending";
		grid[j][2] = M9FundName1+" > "+cmnPath;
		
	}
	for (int j=3;j<6;j++) {
		int i = j-3;
		grid[j][0] = filesShared.split("<break>")[i];
		grid[j][1] = "pending";
		grid[j][2] = M9FundName1+" > "+ shdPath;
		
	}
	for (int j=6;j<9;j++) {
		int i = j-6;
		grid[j][0] = filesStandard.split("<break>")[i];
		grid[j][1] = "pending";
		grid[j][2] = M9FundName1+" > "+ M9Institution1+" > " + M9LimitedPartner1+" > "+stdPath;
		
	}
	for (int j=9;j<12;j++) {
		int i = j-9;
		grid[j][0] = filesStandard.split("<break>")[i];
		grid[j][1] = "pending";
		grid[j][2] = M9FundName1+" > "+ M9Institution2+" > " + M9LimitedPartner2+" > "+stdPath;
		
	}
	lp.CRMLogin(CRMUser1EmailID, adminPassword);
	if (bp.clickOnTab(TabName.FundsTab)) {
		if (fp.clickOnCreatedFund(M9FundName1)) {
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
				if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 30), filesToSearch[0], "search box pending tabs", action.BOOLEAN)) {
					if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 30), "pending docs manage approvals", action.SCROLLANDBOOLEAN)) {
						
						if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesToSearch[0], M9FundName1+" > "+cmnPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
							appLog.info(filesCommon.split("<break>")[0] + " is successfully present in manage approvals window");
						}
						else {
							appLog.error(filesCommon.split("<break>")[0] + "could not be verified in manage approvals window");
							sa.assertTrue(false, filesCommon.split("<break>")[0] + "could not be verified in manage approvals window");
						}
						if (click(driver,fp.getSearchIconCrossButtonManageApprovals(ManageApprovalTabs.PendingDocuments,60) , "search icon cross button", action.BOOLEAN)) {
							
							switchToDefaultContent(driver);
							switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
							ThreadSleep(5000);
							List<WebElement> ele = FindElements(driver, "//span[contains(@id,'pendingGrid-cell-1')]//a", "list of file names manage approvals");
							size = ele.size();
							/*
							 for (int i = 0;i<size;i++) {

								if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,grid[i][0], grid[i][2], grid[i][1],CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
									appLog.info("successfully verified "+grid[i][0] + " "+grid[i][1]+" "+grid[i][2]);
								}
								else {
									appLog.error(grid[i][0] + " file contents are wrong");
										sa.assertTrue(false, grid[i][0] + " file contents are wrong");
									}
								}
							 */
							boolean flag = false;
							//verifying data on grid
							for (int i = 0;i<size;i++) {

								if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,grid[i][0], grid[i][2], grid[i][1],CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
									appLog.info("successfully verified "+grid[i][0] + " "+grid[i][1]+" "+grid[i][2]);
								}
								else {
									int j = 0;
									flag = false;
									while(j<2) {
										click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments,60), "document name manage approvals", action.BOOLEAN);
										if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,grid[i][0], grid[i][2], grid[i][1],CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
											flag = true;
											appLog.info("successfully verified "+grid[i][0] + " "+grid[i][1]+" "+grid[i][2]);
											break;
										}
										j++;
									}
									if (flag == false) {
										appLog.error(grid[i][0] + " file contents are wrong");
										sa.assertTrue(false, grid[i][0] + " file contents are wrong");
									}
								}
							}	
						}
						else {
							appLog.error("cross icon on search textbox is not clickable");
							sa.assertTrue(false, "cross icon on search textbox is not clickable");
						}
					}
					else {
						appLog.error("search icon is not clickable");
						sa.assertTrue(false, "search icon is not clickable");
					}
				}
				else {
					appLog.error("search textbox is not visible on manage approvals window");
					sa.assertTrue(false, "search textbox is not visible on manage approvals window");
				}
				
				if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 30), ".txt", "search box pending tabs", action.BOOLEAN)) {
					if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 30), "pending docs manage approvals", action.SCROLLANDBOOLEAN)) {
						List<WebElement> a = FindElements(driver, "//span[contains(@id,'pendingGrid-cell-1')]//a", "list of file names manage approvals");
						size = a.size();
						//all files should have .txt at end
						for (int i = 0;i<size;i++) {
							String str = a.get(i).getText().trim();
							if (str.substring(str.length()-4).equals(".txt")) {
								appLog.info(".txt is successfully found at end of file when .txt is searched"+str);
							}
							else {
								appLog.error(str.substring(str.length()-4) +" is not equal to .txt for element at "+i);
								sa.assertTrue(false, str.substring(str.length()-4) +" is not equal to .txt for element at "+i);
							}
						}
						
						//verify 3 files
						if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesShared.split("<break>")[0], M9FundName1+" > "+shdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
							appLog.info(filesShared.split("<break>")[0] + " is successfully present in manage approvals window");
						}
						else {
							appLog.error(filesShared.split("<break>")[0]+" is not verified on manage approvals content grid");
							sa.assertTrue(false, filesShared.split("<break>")[0]+" is not verified on manage approvals content grid");
						}
						if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesShared.split("<break>")[2],  M9FundName1+" > "+shdPath,"pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
							appLog.info(filesShared.split("<break>")[2] + " is successfully present in manage approvals window");
						}
						else {
							appLog.error(filesShared.split("<break>")[2]+" is not verified on manage approvals content grid");
							sa.assertTrue(false, filesShared.split("<break>")[2]+" is not verified on manage approvals content grid");
						}
						if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesStandard.split("<break>")[0],  M9FundName1+" > "+M9Institution1+" > " + M9LimitedPartner1+" > "+stdPath,"pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
							appLog.info(filesStandard.split("<break>")[0] + " is successfully present in manage approvals window");
						}
						else {
							appLog.error(filesStandard.split("<break>")[0]+" is not verified on manage approvals content grid");
							sa.assertTrue(false, filesStandard.split("<break>")[0]+" is not verified on manage approvals content grid");
						}
						if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesStandard.split("<break>")[0],  M9FundName1+" > "+M9Institution2+" > " + M9LimitedPartner2+" > "+stdPath,"pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
							appLog.info(filesStandard.split("<break>")[0] + " is successfully present in manage approvals window");
						}
						else {
							appLog.error(filesStandard.split("<break>")[0]+" is not verified on manage approvals content grid");
							sa.assertTrue(false, filesStandard.split("<break>")[0]+" is not verified on manage approvals content grid");
						}
						if (click(driver, fp.getSearchIconCrossButtonManageApprovals(ManageApprovalTabs.PendingDocuments,60), "search icon cross button", action.BOOLEAN)) {

						}
						else {
							appLog.error("cross icon is not clickable on manage approvals search box");
							sa.assertTrue(false, "cross icon is not clickable on manage approvals search box");
						}
					}
					else {
						appLog.error("search icon popup is not clickable");
						sa.assertTrue(false, "search icon popup is not clickable");
					}
				}
				if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 30), filesInternal.split("<break>")[0], "search box pending tabs", action.BOOLEAN)) {
					if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 30), "search icon manage approvals", action.BOOLEAN)) {
						if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
							appLog.info("no data to display text is successfully displayed");
						}
						else {
							appLog.error("no data to display message is not displayed");
							sa.assertTrue(false, "no data to display message is not displayed");
						}
					}
					else {
						appLog.error("search icon is not clickable");
						sa.assertTrue(false, "search icon is not clickable");
					}
				}
				else {
					appLog.error("search textbox is not visible on manage approvals window");
					sa.assertTrue(false, "search textbox is not visible on manage approvals window");
				}
				if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 30), date, "search box pending tabs", action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 30), "search icon manage approvals", action.BOOLEAN)) {
						if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
							appLog.info("no data to display text is successfully displayed");
						}
						else {
							appLog.error("no data to display message is not displayed");
							sa.assertTrue(false, "no data to display message is not displayed");
						}
					}
					else {
						appLog.error("search icon is not clickable");
						sa.assertTrue(false, "search icon is not clickable");
					}
				}
				else {
					appLog.error("search textbox is not visible on manage approvals window");
					sa.assertTrue(false, "search textbox is not visible on manage approvals window");
				}
				if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 30),CRMUser1FirstName+" "+CRMUser1LastName, "search box pending tabs", action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 30), "search icon manage approvals", action.BOOLEAN)) {
						List<WebElement> ele = FindElements(driver, "//span[contains(@id,'pendingGrid-cell-1')]//a", "list of file names manage approvals");
						size = ele.size();

						boolean flag = false;
						//verifying data on grid
						for (int i = 0;i<size;i++) {
							if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,grid[i][0], grid[i][2], grid[i][1],CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info("successfully verified "+grid[i][0] + " "+grid[i][1]+" "+grid[i][2] + " when entered user name in search box");
							}
							else {
								int j = 0;
								flag = false;
								while(j<2) {
									click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments,60), "document name manage approvals", action.BOOLEAN);
									if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,grid[i][0], grid[i][2], grid[i][1],CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
										flag = true;
										appLog.info("successfully verified "+grid[i][0] + " "+grid[i][1]+" "+grid[i][2]);
										break;
									}
									j++;
								}
								if (flag == false) {
									appLog.error(grid[i][0] + " file contents are wrong");
									sa.assertTrue(false, grid[i][0] + " file contents are wrong");
								}
							}
						}
					}else {
						appLog.error("search icon is not clickable");
						sa.assertTrue(false, "search icon is not clickable");
					}
				}
				else {
					appLog.error("search textbox is not visible on manage approvals window when entered user name in search box");
					sa.assertTrue(false, "search textbox is not visible on manage approvals window when entered user name in search box");
				}
				
				//entering firm name on search textbox
				if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 30),Org1FirmName, "search box pending tabs", action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 30), "search icon manage approvals", action.BOOLEAN)) {
						List<WebElement> ele = FindElements(driver, "//span[contains(@id,'pendingGrid-cell-1')]//a", "list of file names manage approvals");
						size = ele.size();

						boolean flag = false;
						//verifying data on grid
						for (int i = 0;i<size;i++) {
							if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,grid[i][0], grid[i][2], grid[i][1],CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info("successfully verified "+grid[i][0] + " "+grid[i][1]+" "+grid[i][2] + " when entered user name in search box");
							}
							else {
								int j = 0;
								flag = false;
								while(j<2) {
									click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments,60), "document name manage approvals", action.BOOLEAN);
									if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,grid[i][0], grid[i][2], grid[i][1],CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
										flag = true;
										appLog.info("successfully verified "+grid[i][0] + " "+grid[i][1]+" "+grid[i][2]);
										break;
									}
									j++;
								}
								if (flag == false) {
									appLog.error(grid[i][0] + " file contents are wrong");
									sa.assertTrue(false, grid[i][0] + " file contents are wrong");
								}
							}
						}
					}
					else {
						appLog.error("search icon is not clickable");
						sa.assertTrue(false, "search icon is not clickable");
					}
				}
				else {
					appLog.error("search textbox is not visible on manage approvals window when entered user name in search box");
					sa.assertTrue(false, "search textbox is not visible on manage approvals window when entered user name in search box");
				}
				if (sendKeys(driver,fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 30) , filesStandard.split("<break>")[0], "search box pending tabs", action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 60),"search icon pending documents", action.SCROLLANDBOOLEAN)) {
						if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments, filesStandard.split("<break>")[0], M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+" > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
							appLog.info(filesStandard.split("<break>")[0]+" is successfully found in pending documents tab");
						}
						else {
							appLog.error(filesStandard.split("<break>")[0]+" could not be found on pending tabs");
							sa.assertTrue(false, filesStandard.split("<break>")[0]+" could not be found on pending tabs");
							
						}
						if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments, filesStandard.split("<break>")[0], M9FundName1+" > "+M9Institution2+" > "+M9LimitedPartner2+" > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
							appLog.info(filesStandard.split("<break>")[0]+" is successfully found in pending documents tab");
						}
						else {
							appLog.error(filesStandard.split("<break>")[0]+" could not be found on pending tabs");
							sa.assertTrue(false, filesStandard.split("<break>")[0]+" could not be found on pending tabs");
							
						}
					}
					else {
						appLog.error("search icon is not clickable");
						sa.assertTrue(false, "search icon is not clickable");
					}
				}
				//verifying all files in approved tab
				if (click(driver, fp.getApprovedDocsTab(60), "approved docs tab", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver,fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 30) , filesStandard.split("<break>")[0], "search box ApprovedDocuments", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 60),"search icon ApprovedDocuments", action.SCROLLANDBOOLEAN)) {
							if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.ApprovedDocuments, filesStandard.split("<break>")[0], M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+" > "+stdPath, "approved", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info(filesStandard.split("<break>")[0]+" is successfully found in pending documents tab");
							}
							else {
								appLog.error(filesStandard.split("<break>")[0]+" could not be found on pending tabs");
								sa.assertTrue(false, filesStandard.split("<break>")[0]+" could not be found on pending tabs");
							}
							if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.ApprovedDocuments, filesStandard.split("<break>")[0], M9FundName1+" > "+M9Institution2+" > "+M9LimitedPartner2+" > "+stdPath, "approved", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info(filesStandard.split("<break>")[0]+" is successfully found in pending documents tab");
							}
							else {
								appLog.error(filesStandard.split("<break>")[0]+" could not be found on pending tabs");
								sa.assertTrue(false, filesStandard.split("<break>")[0]+" could not be found on pending tabs");
								
							}
						}
						else {
							appLog.error("search icon on manage approvals tab is not clickable");
							sa.assertTrue(false, "search icon on manage approvals tab is not clickable");
						}
					}
					else {
						appLog.error("search textbox is not visible on approved documents tab");
						sa.assertTrue(false, "search textbox is not visible on approved documents tab");
					}
					
					//common file
					if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 60), filesCommon.split("<break>")[0], "search box approved tabs", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 60),"search icon approved documents", action.SCROLLANDBOOLEAN)) {
							if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.ApprovedDocuments, filesCommon.split("<break>")[0], M9FundName1+" > "+cmnPath, "approved", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info(filesCommon.split("<break>")[0]+" is successfully found in pending documents tab");
							}
								else {
								appLog.error(filesCommon.split("<break>")+" could not be found on manage approvals page");
								sa.assertTrue(false, filesCommon.split("<break>")+" could not be found on manage approvals page");
								}
						}
					}
					//internal file
					if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 60), filesInternal.split("<break>")[0], "search box approved tabs", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 30), "search icon manage approvals", action.BOOLEAN)) {
							if (fp.noDataToDisplay(ManageApprovalTabs.ApprovedDocuments, 30)!=null) {
								appLog.info("no data to display text is successfully displayed");
							}
							else {
								appLog.error("no data to display message is not displayed");
								sa.assertTrue(false, "no data to display message is not displayed");
							}
						}
					}
					//current date
					if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 30), date, "search box ApprovedDocuments", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 30), "search icon manage approvals", action.BOOLEAN)) {
							if (fp.noDataToDisplay(ManageApprovalTabs.ApprovedDocuments, 30)!=null) {
								appLog.info("no data to display text is successfully displayed");
							}
							else {
								appLog.error("no data to display message is not displayed");
								sa.assertTrue(false, "no data to display message is not displayed");
							}
						}
						else {
							appLog.error("search icon is not clickable");
							sa.assertTrue(false, "search icon is not clickable");
						}
					}
					else {
						appLog.error("search textbox is not visible on manage approvals window");
						sa.assertTrue(false, "search textbox is not visible on manage approvals window");
					}
					//shared file
					if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 30), filesShared.split("<break>")[1], "search box ApprovedDocuments", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 30), "search icon manage approvals", action.BOOLEAN)) {
							if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.ApprovedDocuments, filesShared.split("<break>")[1], M9FundName1+" > "+shdPath, "approved", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info(filesShared.split("<break>")[1]+" is successfully found in pending documents tab");
							}
							else {
								appLog.error(filesShared.split("<break>")[1]+" is not displayed");
								sa.assertTrue(false, filesShared.split("<break>")[1]+" is not displayed");
							}
						}
						else {
							appLog.error("search icon is not clickable");
							sa.assertTrue(false, "search icon is not clickable");
						}
					}
					else {
						appLog.error("search textbox is not visible on manage approvals window");
						sa.assertTrue(false, "search textbox is not visible on manage approvals window");
					}
				}
				else {
					appLog.error("approved docs tab is not clickable");
					sa.assertTrue(false, "approved docs tab is not clickable");
				}
				//by default pending documents will be opened
				if (click(driver, fp.getCrossIconManageApp(30), "manage approvals close button", action.SCROLLANDBOOLEAN)) {	
					if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approvals icon on fundraising workspace", action.SCROLLANDBOOLEAN)) {
						if (fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 60).getText().equals("")) {
							appLog.info("search textbox is now empty");
						}
						else {
							appLog.error("search textbox is not empty, it has text "+fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 60).getText());
							sa.assertTrue(false, "search textbox is not empty, it has text "+fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 60).getText());

						}
					}
					else {
						appLog.error("manage approvals icon is not clickable");
						sa.assertTrue(false, "manage approvals icon is not clickable");
					}
				}
				else {
					appLog.error("manage approvals close button is not clickable");
					sa.assertTrue(false, "manage approvals close button is not clickable");
				}
			}
			
			else {
				appLog.error("manage approvals icon is not clickable");
				sa.assertTrue(false, "manage approvals icon is not clickable");
			}
			switchToDefaultContent(driver);
		}
		else {
			appLog.error("fund "+M9FundName1+" is not found on funds page");
			sa.assertTrue(false, "fund "+M9FundName1+" is not found on funds page");
		}
	}
	else {
		appLog.error("funds tab is not clickable");
		sa.assertTrue(false, "funds tab is not clickable");
	}
	lp.CRMlogout();
	sa.assertAll();
	}

	@Test
	public void M9tc039_OpeningOfDocsInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileStandard);
		String parentID=null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
					/*if (!fp.checkVisibilityOfDocumentManageApprovals(ManageApprovalTabs.PendingDocuments, filesCommon.split("<break>")[0])) {
						click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments,60), "document name column", action.SCROLLANDBOOLEAN);
					}
					click(driver, fp.clickOnDocumentLinkOnManageApprovals(ManageApprovalTabs.PendingDocuments, filesCommon.split("<break>")[0], 30), "common 1 document", action.SCROLLANDBOOLEAN);
					*/fp.clickOnDocumentManageApprovals(ManageApprovalTabs.PendingDocuments, filesCommon.split("<break>")[0], 30, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30));
						
					
				parentID = switchOnWindow(driver);
				if (parentID != null) {

					if (fp.getDownloadLink(60) != null) {
						appLog.info("Download Button is displaying");
					} else {
						appLog.info("Document Download Button is not displaying");
						sa.assertTrue(false, "Document Download Button is not displaying");
					}

					if (fp.getDocumentCloseBtn(60) != null) {

						appLog.info("Close Button is displaying");
					} else {
						appLog.info("Close Button is not displaying");
						sa.assertTrue(false, "Close Button is not displaying");
					}
				}
				driver.close();
				driver.switchTo().window(parentID);
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				//clicking on 2nd document(shared)
				if (fp.clickOnDocumentManageApprovals(ManageApprovalTabs.PendingDocuments, filesShared.split("<break>")[0], 30, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30))){
				parentID = switchOnWindow(driver);
				if (parentID != null) {

					if (fp.getDownloadLink(60) != null) {
						appLog.info("Download Button is displaying");
					} else {
						appLog.info("Document Download Button is not displaying");
						sa.assertTrue(false, "Document Download Button is not displaying");
					}

					if (fp.getDocumentCloseBtn(60) != null) {

						appLog.info("Close Button is displaying");
					} else {
						appLog.info("Close Button is not displaying");
						sa.assertTrue(false, "Close Button is not displaying");
					}
					driver.close();
					driver.switchTo().window(parentID);
				}
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				//click on 3rd document(standard)
				if (fp.clickOnDocumentManageApprovals(ManageApprovalTabs.PendingDocuments, filesStandard.split("<break>")[0], 30, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30))){
				parentID = switchOnWindow(driver);
				if (parentID != null) {

					if (fp.getDownloadLink(60) != null) {
						appLog.info("Download Button is displaying");
					} else {
						appLog.info("Document Download Button is not displaying");
						sa.assertTrue(false, "Document Download Button is not displaying");
					}

					if (fp.getDocumentCloseBtn(60) != null) {

						appLog.info("Close Button is displaying");
					} else {
						appLog.info("Close Button is not displaying");
						sa.assertTrue(false, "Close Button is not displaying");
					}
					driver.close();
					driver.switchTo().window(parentID);
				}
				}
				
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				ThreadSleep(5000);
				//clicking on approved docs tab
				if (click(driver, fp.getApprovedDocsTab(60), "approved docs tab", action.SCROLLANDBOOLEAN)) {
					if (fp.clickOnDocumentManageApprovals(ManageApprovalTabs.ApprovedDocuments, filesCommon.split("<break>")[0], 30, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30))){
					parentID = switchOnWindow(driver);
					if (parentID != null) {

						if (fp.getDownloadLink(60) != null) {
							appLog.info("Download Button is displaying");
						} else {
							appLog.info("Document Download Button is not displaying");
							sa.assertTrue(false, "Document Download Button is not displaying");
						}

						if (fp.getDocumentCloseBtn(60) != null) {

							appLog.info("Close Button is displaying");
						} else {
							appLog.info("Close Button is not displaying");
							sa.assertTrue(false, "Close Button is not displaying");
						}
					}
					}
					driver.close();
					driver.switchTo().window(parentID);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					//clicking on 2nd document(shared)
					if(fp.clickOnDocumentManageApprovals(ManageApprovalTabs.ApprovedDocuments, filesShared.split("<break>")[0], 30, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30))){
					parentID = switchOnWindow(driver);
					if (parentID != null) {

						if (fp.getDownloadLink(60) != null) {
							appLog.info("Download Button is displaying");
						} else {
							appLog.info("Document Download Button is not displaying");
							sa.assertTrue(false, "Document Download Button is not displaying");
						}

						if (fp.getDocumentCloseBtn(60) != null) {

							appLog.info("Close Button is displaying");
						} else {
							appLog.info("Close Button is not displaying");
							sa.assertTrue(false, "Close Button is not displaying");
						}
						driver.close();
						driver.switchTo().window(parentID);
					}
					}
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					//click on 3rd document(standard)
					if (fp.clickOnDocumentManageApprovals(ManageApprovalTabs.ApprovedDocuments, filesStandard.split("<break>")[0], 30, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30))){
						parentID = switchOnWindow(driver);
						if (parentID != null) {

							if (fp.getDownloadLink(60) != null) {
								appLog.info("Download Button is displaying");
							} else {
								appLog.info("Document Download Button is not displaying");
								sa.assertTrue(false, "Document Download Button is not displaying");
							}

							if (fp.getDocumentCloseBtn(60) != null) {

								appLog.info("Close Button is displaying");
							} else {
								appLog.info("Close Button is not displaying");
								sa.assertTrue(false, "Close Button is not displaying");
							}
							driver.close();
							driver.switchTo().window(parentID);
						}
					}
					else {
						appLog.error("documents is not clickable from manage approvals window");
						sa.assertTrue(false, "documents is not clickable from manage approvals window");
					}
				}
				else {
					appLog.error("approved docs tab is not clickable");
					sa.assertTrue(false, "approved docs tab is not clickable");
				}
				}
				else {
					appLog.error("manage approval icon is not clickable");
					sa.assertTrue(false, "manage approval icon is not clickable");
				}

			}
			else {
				appLog.error(M9FundName1+" is not found on funds page");
				sa.assertTrue(false, M9FundName1+" is not found on funds page");
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

	@Test
	public void M9tc040_VerifyDuplicateDocsInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileStandard);
		List<String> temp_li = new ArrayList<String>();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon on fundraising workspace", action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getCheckAllDocsManageApprovals(60), "check all documents checkbox", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getApproveBtnManageApprovals(60), "approve button on manage approvals window", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "approve yes button", action.SCROLLANDBOOLEAN)) {
								if (fp.getApproveDuplicateDocsHeadText(60).getText().trim().equals(FundsPageErrorMessage.manageApprovalDuplicateError)) {
									appLog.info("duplicate docs text under manage approvals is successfully verified");
								}
								else {
									appLog.error("duplicate docs text does not match with expected text "+fp.getApproveDuplicateDocsHeadText(60).getText().trim()+" and "+FundsPageErrorMessage.duplicateDocumentsTextUnderHeading);
									sa.assertTrue(false, "duplicate docs text does not match with expected text");
								}
								if (fp.getManageApprovalsUpdateAllDocument(60)!=null) {
									appLog.info("update all button is successfully displayed");
								}
								else {
									appLog.error("update all button is not present");
									sa.assertTrue(false, "update all button is not present");
								}
								if (fp.getManageApprovalsIgnoreAll(60)!=null) {
									appLog.info("ignore all button is successfully displayed");
								}
								else {
									appLog.error("ignore all button is not present");
									sa.assertTrue(false, "ignore all button is not present");
								}
								if (fp.getDuplicateDocsCloseButton(60)!=null) {
									appLog.info("duplicate docs popup close button is successfully displayed");
								}
								else {
									appLog.error("duplicate docs close button is not displayed");
									sa.assertTrue(false, "duplicate docs close button is not displayed");
								}
								if (fp.getDuplicateDocsHeadColumn(60,false)!=null) {
									appLog.info("duplicate docs column is successfully displayed");
								}
								else {
									appLog.error("duplicate docs column is not displayed");
									sa.assertTrue(false, "duplicate docs column is not displayed");
								}
								if (fp.getDuplicateDocsHeadFolderPath(60,false)!=null) {
									appLog.info("folder path column is successfully displayed");
								}
								else {
									appLog.error("duplicate docs folder path column is not displayed");
									sa.assertTrue(false, "duplicate docs folder path column is not displayed");
								}
								//check default sorting
								//column of document names
								List<WebElement> el = fp.duplicateDocColumnList(60);
								if (checkSorting(driver, SortOrder.Assecending, el)) {
									appLog.info("correct sorting for "+SortOrder.Assecending.toString()+" is successfully found");
								}
								else {
									appLog.error("default sorting is not correct for ascending order");
									sa.assertTrue(false, "default sorting is not correct for ascending order");
								}
								click(driver, fp.getDuplicateDocsHeadColumn(60,false), "duplicate documents column head", action.BOOLEAN);
								ThreadSleep(3000);	
								if (fp.verifyDocumentInDuplicateDocManageApprovalPopup(filesCommon.split("<break>")[0]+"/"+ M9FundName1+" > "+cmnPath+
										"<break>"+filesCommon.split("<break>")[1]+"/"+ M9FundName1+" > "+cmnPath+
										"<break>"+filesCommon.split("<break>")[2]+"/"+ M9FundName1+" > "+cmnPath+
										"<break>"+filesShared.split("<break>")[0]+"/"+M9FundName1+" > "+shdPath+
										"<break>"+filesShared.split("<break>")[1]+"/"+M9FundName1+" > "+shdPath+
										"<break>"+filesShared.split("<break>")[2]+"/"+M9FundName1+" > "+shdPath+
										"<break>"+filesStandard.split("<break>")[0]+"/"+M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+" > "+stdPath+
										"<break>"+filesStandard.split("<break>")[1]+"/"+M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+" > "+stdPath+
										"<break>"+filesStandard.split("<break>")[2]+"/"+M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+" > "+stdPath+
										"<break>"+filesStandard.split("<break>")[0]+"/"+M9FundName1+" > "+M9Institution2+" > "+M9LimitedPartner2+" > "+stdPath+
										"<break>"+filesStandard.split("<break>")[1]+"/"+M9FundName1+" > "+M9Institution2+" > "+M9LimitedPartner2+" > "+stdPath+
										"<break>"+filesStandard.split("<break>")[0]+"/"+M9FundName1+" > "+M9Institution2+" > "+M9LimitedPartner2+" > "+stdPath,true).isEmpty()){

								}
								else {
									//click(driver, fp.getDuplicateDocsHeadColumn(60,false), "duplicate documents column head", action.BOOLEAN);
									appLog.error(fp.printAllElementsInList(temp_li)+" are not found");
									sa.assertTrue(false, "could not find a few elements in common");
								}
								
								//verify sorting
								el = fp.duplicateDocColumnList(60);	
								if (checkSorting(driver, SortOrder.Decending, el)) {
										appLog.info("sorting is successfully found in descending order");
									}
									else {
										appLog.error("correct sorting was not found in descending order");
										sa.assertTrue(false, "correct sorting was not found in descending order");
									}
									if (click(driver, fp.getDuplicateDocsHeadColumn(60,true), "duplicate document column", action.BOOLEAN)) {
										el = fp.duplicateDocColumnList(60);
										if (checkSorting(driver, SortOrder.Assecending, el)) {
											appLog.info("sorting is successfully found in ascending order");
										}
										else {
											appLog.error("correct sorting was not found in ascending order");
											sa.assertTrue(false, "correct sorting was not found in ascending order");
										}
									}
									else {
										appLog.error("duplicate docs head column is not clickable");
										sa.assertTrue(false, "duplicate docs head column is not clickable");
									}
								
								//verify sorting folder path column
								if (click(driver, fp.getDuplicateDocsHeadFolderPath(60,false), "folder path head column", action.SCROLLANDBOOLEAN)) {
									el=fp.duplicateDocFolderPathList(60);
									if (checkSorting(driver, SortOrder.Assecending, el)) {
										appLog.info("sorting is successfully found in ascending order folder path");
									}
									else {
										appLog.error("correct sorting was not found in ascending order folder path");
										sa.assertTrue(false, "correct sorting was not found in ascending order folder path");
									}
									if (click(driver, fp.getDuplicateDocsHeadFolderPath(60,true), "folder path head column", action.SCROLLANDBOOLEAN)) {
										el=fp.duplicateDocFolderPathList(60);
										if (checkSorting(driver, SortOrder.Decending, el)) {
											appLog.info("sorting is successfully found in Decending order folder path");
										}
										else {
											appLog.error("correct sorting was not found in Decending order folder path");
											sa.assertTrue(false, "correct sorting was not found in Decending order folder path");
										}
									}
									else {
										appLog.error("folder path column is not clickable");
										sa.assertTrue(false, "folder path column is not clickable");
									}
								}
								if (click(driver, fp.getDuplicateDocsCloseButton(60), "duplicate docs cross icon", action.SCROLLANDBOOLEAN)) {
									if (fp.getApproveConfirmPopupText(60).getText().trim().equals("0 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
										appLog.info("correct approval message is displayed");
									}
									else {
										appLog.error("no of documents approved message is not correct");
										sa.assertTrue(false, "no of documents approved message is not correct");
									}
									if (click(driver, fp.getManageApprovalsApproveConfirmationCrossIcon(60), "Manage Approvals Approve Confirmation Cross Icon", action.BOOLEAN)) {
										//in manage approvals popup, all checkboxes will be checked
										List<WebElement> li = fp.getManageApprovalsAllPendingFilesCheckBoxList();
										for (int i = 0;i<li.size();i++) {
											if (!isSelected(driver, li.get(i),"checkbox for each document manage approvals")) {
												appLog.error("not selected "+i+" checkbox");
												sa.assertTrue(false, "not selected "+i+" checkbox");
											}
										}
										
										//selecting all elements, approving and clicking ignore all
										if (click(driver, fp.getApproveBtnManageApprovals(60), "approve button manage approvals", action.SCROLLANDBOOLEAN)) {
											if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "yes button approve popup", action.BOOLEAN)) {
												if (fp.verifyDocumentInDuplicateDocManageApprovalPopup(filesCommon.split("<break>")[0]+"/"+ M9FundName1+" > "+cmnPath+
														"<break>"+filesCommon.split("<break>")[1]+"/"+ M9FundName1+" > "+cmnPath+
														"<break>"+filesCommon.split("<break>")[2]+"/"+ M9FundName1+" > "+cmnPath+
														"<break>"+filesShared.split("<break>")[0]+"/"+M9FundName1+" > "+shdPath+
														"<break>"+filesShared.split("<break>")[1]+"/"+M9FundName1+" > "+shdPath+
														"<break>"+filesShared.split("<break>")[2]+"/"+M9FundName1+" > "+shdPath+
														"<break>"+filesStandard.split("<break>")[0]+"/"+M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+" > "+stdPath+
														"<break>"+filesStandard.split("<break>")[1]+"/"+M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+" > "+stdPath+
														"<break>"+filesStandard.split("<break>")[2]+"/"+M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+" > "+stdPath+
														"<break>"+filesStandard.split("<break>")[0]+"/"+M9FundName1+" > "+M9Institution2+" > "+M9LimitedPartner2+" > "+stdPath+
														"<break>"+filesStandard.split("<break>")[1]+"/"+M9FundName1+" > "+M9Institution2+" > "+M9LimitedPartner2+" > "+stdPath+
														"<break>"+filesStandard.split("<break>")[0]+"/"+M9FundName1+" > "+M9Institution2+" > "+M9LimitedPartner2+" > "+stdPath,true).isEmpty()){

												}
												else {
													appLog.error(fp.printAllElementsInList(temp_li)+" are not found");
													sa.assertTrue(false, "could not find a few elements in common");
												}
												if (click(driver, fp.getManageApprovalsIgnoreAll(60), "approve ignore all button", action.BOOLEAN)) {
													if (fp.getApproveConfirmPopupText(60).getText().trim().equals("0 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
														appLog.info("correct approval message is displayed");
													}
													else {
														appLog.error("no of documents approved message is not correct");
														sa.assertTrue(false, "no of documents approved message is not correct");
													}
												}
												else {
													appLog.error("ignore all button is not clickable on approve window");
													sa.assertTrue(false, "ignore all button is not clickable on approve window");
												}
												if (click(driver, fp.getManageApprovalsApproveConfirmationCrossIcon(60), "Manage Approvals Approve Confirmation Cross Icon", action.BOOLEAN)) {
													//all 12 checkboxes will be ticked
													li = fp.getManageApprovalsAllPendingFilesCheckBoxList();
													for (int i = 0;i<li.size();i++) {
														if (!isSelected(driver, li.get(i),"checkbox for each document manage approvals")) {
															appLog.error("not selected "+i+" checkbox");
															sa.assertTrue(false, "not selected "+i+" checkbox");
														}
														else {
															appLog.info("checkbox is successfully selected for "+i+"th element");
														}
													}
												}
												else {
													appLog.error("Manage Approvals Approve Confirmation Cross Icon is not clickable");
													sa.assertTrue(false, "Manage Approvals Approve Confirmation Cross Icon is not clickable");
												}
											}
											else {
												appLog.error("manage approval approve yes button is not clickable");
												sa.assertTrue(false, "manage approval approve yes button is not clickable");
											}
										}
										else {
											appLog.error("approve button is not clickable");
											sa.assertTrue(false, "approve button is not clickable");
										}
										//find not more than 10 error
										if (click(driver, fp.getApproveBtnManageApprovals(60), "approve button manage approvals", action.SCROLLANDBOOLEAN)) {
											if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "yes button approve popup", action.BOOLEAN)) {
												WebElement ele= BaseLib.edriver.findElement(By.cssSelector("a[title=\"Update All\"]"));
												boolean flag=true;
												try{
													scrollDownThroughWebelement(driver, ele, "Update All Button");
													ele.click();
													appLog.info("clicked on Update All Button");
												}catch(Exception e){
													flag=false;
													appLog.error("Not able to click on Update All buttone");
													BaseLib.sa.assertTrue(false, "Not able to click on Update All");
												}
												if (flag) {
													if (isAlertPresent(driver)) {
														String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
														switchToAlertAndAcceptOrDecline(driver, 10, action.ACCEPT);
														if (msg.trim().equals(FundsPageErrorMessage.manageApprovalApproveMoreThan10)) {
															appLog.info("correct alert that cannot approve more than 10 docs is successfully verified");
														}
														else {
															appLog.error("alert message is not as expected");
															sa.assertTrue(false, "alert message is not as expected");
														}
													}
													else {
														appLog.error("no alert is present when more than 10 docs is selected for approvals");
														sa.assertTrue(false, "no alert is present when more than 10 docs is selected for approvals");
													}
												}
												else {
													appLog.error("update all button is not clickable");
													sa.assertTrue(false, "update all button is not clickable");
												}
											}
											else {
												appLog.error("manage approval approve yes button is not clickable");
												sa.assertTrue(false, "manage approval approve yes button is not clickable");
											}
										}
										else {
											appLog.error("approve button is not clickable");
											sa.assertTrue(false, "approve button is not clickable");
										}
									}
									else {
										appLog.error("confirmation cross icon is not clickable");
										sa.assertTrue(false, "confirmation cross icon is not clickable");
									}
								}
								else {
									appLog.error("duplicate docs cross icon is not clickable");
									sa.assertTrue(false, "duplicate docs cross icon is not clickable");
								}
							}
							else {
								appLog.error("yes button on manage approvals approve popup is not clickable");
								sa.assertTrue(false, "yes button on manage approvals approve popup is not clickable");
							}
						}
						else {
							appLog.error("approve button on manage approvals popup is not clickable");
							sa.assertTrue(false, "approve button on manage approvals popup is not clickable");
						}
					}
					else {
						appLog.error("checkbox to select all documents is not clickable");
						sa.assertTrue(false, "checkbox to select all documents is not clickable");
					}
				}
				else {
					appLog.error("manage approvals icon is not clickable");
					sa.assertTrue(false, "manage approvals icon is not clickable");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not found on funds page");
				sa.assertTrue(false, M9FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc041_UpdateAllAndDuplicateDoc_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileStandard);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		
		
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon on fundraising workspace", action.SCROLLANDBOOLEAN)) {
					if (click(driver,fp.getCheckAllDocsManageApprovals(60), "checkbox to select all boxes", action.BOOLEAN)) {
						//uncheck checkbox in front of 2 common files
						fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[0], M9FundName1, CRMUser1FirstName+" "+CRMUser1LastName, M9FirmName, date);
						if (fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[0])!=null) {
							appLog.info("checkbox is not null");
							if (isSelected(driver,fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[0]) , "checkbox for "+filesCommon.split("<break>")[0])) {
								if (click(driver, fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[0]), "checkbox for "+filesCommon.split("<break>")[0], action.BOOLEAN)) {
									appLog.info("successfully unchecked checkbox of "+filesCommon.split("<break>")[0]);
								}
								else {
									appLog.error("cannot click checkbox for 1st file of common folder");
									sa.assertTrue(false, "cannot click checkbox for 1st file of common folder");
								}
							}
							else {
								appLog.error(filesCommon.split("<break>")[0]+" checkbox is already selected");
							}
						}
						else {
							appLog.info("checkbox is null");
							click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments, 30), "document name column", action.BOOLEAN);
							if (isSelected(driver,fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[0]) , "checkbox for "+filesCommon.split("<break>")[0])) {
								click(driver, fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[0]), "checkbox for "+filesCommon.split("<break>")[0], action.BOOLEAN);
							}
							else {
								appLog.error(filesCommon.split("<break>")[0]+" checkbox is already selected");
							}
						}
						fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[1], M9FundName1, CRMUser1FirstName+" "+CRMUser1LastName, M9FirmName, date);
						if (fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[1])!=null) {
							if (isSelected(driver,fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[1]) , "checkbox for "+filesCommon.split("<break>")[1])) {
								if (click(driver, fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[1]), "checkbox for "+filesCommon.split("<break>")[1], action.BOOLEAN)) {
									appLog.info("successfully unchecked checkbox of "+filesCommon.split("<break>")[1]);
								}
								else {
									appLog.error("cannot click checkbox for 2nd file on common folder");
									sa.assertTrue(false, "cannot click checkbox for 2nd file on common folder");
								}
							}
							else {
								appLog.error(filesCommon.split("<break>")[1]+" checkbox is already unselected");
							}
						}
						else {
							click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments, 30), "document name column", action.BOOLEAN);
							click(driver, fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[1]), "checkbox for "+filesCommon.split("<break>")[1], action.BOOLEAN);

						}
						//after this the header checkbox will be unchecked
						if (!isSelected(driver, fp.getCheckAllDocsManageApprovals(60),"checkbox to select all docs in manage approvals")) {
							appLog.info("by unchecking 2 files, the head checkbox is unchecked successfully");
						}
						else {
							appLog.error("by unchecking 2 files, header checkbox should have been unchecked, but it is still checked");
							sa.assertTrue(false, "by unchecking 2 files, header checkbox should have been unchecked, but it is still checked");
						}
						if (click(driver, fp.getApproveBtnManageApprovals(60), "approve button on manage approval", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "yes btton on approve popup", action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageApprovalsUpdateAllDocument(60), "update all button approve popup", action.BOOLEAN)) {
									//popup that 10 docs are updated successfully
									if (fp.getApproveConfirmPopupText(60).getText().trim().equals("10 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
										appLog.info("correct approval message is displayed");
									}
									else {
										appLog.error("incorrect message is displayed on approve popup");
										sa.assertTrue(false, "incorrect message is displayed on approve popup");
									}
									if (click(driver, fp.getManageApprovalsApproveConfirmationCrossIcon(60), "Manage Approvals Approve Confirmation Cross Icon", action.BOOLEAN)) {
										if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesCommon.split("<break>")[0] +"<break>"+filesCommon.split("<break>")[1], M9FundName1+" > "+cmnPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
											appLog.info("documents are found successfully in manage approvals window");
										}
										else {
											appLog.error(filesCommon.split("<break>")[0]+" are not found in manage approvals window");
											sa.assertTrue(false, filesCommon.split("<break>")[0]+" are not found in manage approvals window");
										}
									}
									else {
										appLog.error("manage approvals cross icon is not clickable");
										sa.assertTrue(false, "manage approvals cross icon is not clickable");
									}
										
								}
								else {
									appLog.error("update all button is not found");
									sa.assertTrue(false, "update all button is not found");
								
								}
							}
							else {
								appLog.error("yes button is not clickable on manage approval window");
								sa.assertTrue(false, "yes button is not clickable on manage approval window");
							}
						}
						else {
							appLog.error("approve button is not clickable");
							sa.assertTrue(false, "approve button is not clickable");
						}
					}
					else {
						appLog.error("checkbox to select all boxes is not present on this page");
						sa.assertTrue(false, "checkbox to select all boxes is not present on this page");
					}
					if (click(driver, fp.getCrossIconManageApp(30), "manage approvals cancel button", action.SCROLLANDBOOLEAN)) {
						appLog.info("cancel button is successfully clicked");
					}
					else {
						appLog.error("cancel button on manage approvals is not clickable");
						sa.assertTrue(false, "cancel button on manage approvals is not clickable");
					}
					if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesCommon.split("<break>")[2], true)) {
							appLog.info(filesCommon.split("<break>")[2]+" file is successfully verified on content grid");
						}
						else {
							appLog.error(filesCommon.split("<break>")[2]+" is not found on content grid");
							sa.assertTrue(false, filesCommon.split("<break>")[2]+" is not found on content grid");
						}
					}
					for (int i = 0;i<3;i++) {
					if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesShared.split("<break>")[i], true)) {
							appLog.info(filesShared.split("<break>")[i]+" is successfully found in content grid");
						}
						else {
							appLog.error(filesShared.split("<break>")[i]+" could not be found in content grid");
							sa.assertTrue(false, filesShared.split("<break>")[i]+" could not be found in content grid");
						}
					}
					
					if (fp.verifyFolderPathdummy(stdPath, M9Institution1, M9LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesStandard.split("<break>")[i], true)) {
							appLog.info(filesStandard.split("<break>")[i]+" is successfully found on content grid");
						}
						else {
							appLog.error(filesStandard.split("<break>")[i]+" could not be found in content grid");
							sa.assertTrue(false, filesStandard.split("<break>")[i]+" could not be found in content grid");
						}
					}
					else {
						appLog.error(stdPath+" is not found in folder structure");
						sa.assertTrue(false, stdPath+" is not found in folder structure");
					}
					if (fp.verifyFolderPathdummy(stdPath, M9Institution2, M9LimitedPartner2, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesStandard.split("<break>")[i], true)) {
							appLog.info(filesStandard.split("<break>")[i]+" is successfully found on content grid");
						}
						else {
							appLog.error(filesStandard.split("<break>")[i]+" could not be found in content grid");
							sa.assertTrue(false, filesStandard.split("<break>")[i]+" could not be found in content grid");
						}
					}
					else {
						appLog.error(stdPath+" is not found in folder structure");
						sa.assertTrue(false, stdPath+" is not found in folder structure");
					}
					}
				}
				else {
					appLog.error("manage approval icon is not clickable");
					sa.assertTrue(false, "manage approval icon is not clickable");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not found on content grid");
				sa.assertTrue(false, M9FundName1+" is not found on content grid");
			}
			
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc041_UpdateAllAndDuplicateDoc_ImpactCRM() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc041_UpdateAllAndDuplicateDoc_Action", excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc041_UpdateAllAndDuplicateDoc_Action", excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc041_UpdateAllAndDuplicateDoc_Action", excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileStandard);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");

		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M9Institution1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesCommon.split("<break>")[2], true)) {
						appLog.info(filesCommon.split("<break>")[2]+" is successfully found in content grid");
					}
					else {
						appLog.error(filesCommon.split("<break>")[2]+" could not be found in content grid");
						sa.assertTrue(false, filesCommon.split("<break>")[2]+" could not be found in content grid");
					}
					if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {

						for (int i = 0;i<3;i++) {
							if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesShared.split("<break>")[i], true)) {

							}else {
								appLog.error(filesShared.split("<break>")[i]+" could not be found in content grid");
								sa.assertTrue(false, filesShared.split("<break>")[i]+" could not be found in content grid");
							}
						}
					}
					else {
						appLog.error(shdPath+" is not found in folder structure");
						sa.assertTrue(false, stdPath+" is not found in folder structure");
					}
					if (fp.verifyFolderPathdummy(stdPath, null, M9LimitedPartner1, M9FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {

						for (int i = 0;i<3;i++) {
							if (fp.verifyFileinContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace, filesStandard.split("<break>")[i], true)) {
								appLog.info(filesStandard.split("<break>")[i]+" is successfully found in content grid");
							}
							else {
								appLog.error(filesStandard.split("<break>")[i]+" could not be found in content grid");
								sa.assertTrue(false, filesStandard.split("<break>")[i]+" could not be found in content grid");
							}
						}
					}
					else {
						appLog.error(stdPath+" is not found in folder structure");
						sa.assertTrue(false, stdPath+" is not found in folder structure");
					}

				}
				else {
					appLog.error(cmnPath+" is not found in folder structure");
					sa.assertTrue(false, cmnPath+" is not found in folder structure");
				}
				switchToDefaultContent(driver);
			}
			if (ip.clickOnTab(TabName.ContactTab)) {
				if (cp.clickOnCreatedContact(M9Contact1FirstName, M9Contact1LastName, null)) {
					switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
					if (fp.verifyFolderPathdummy(cmnPath, null, null, M9FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
						if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesCommon.split("<break>")[2], true)) {
							appLog.info(filesCommon.split("<break>")[2]+" is successfully found in content grid");
						}
						else {
							appLog.error(filesCommon.split("<break>")[2]+" could not be found in content grid");
							sa.assertTrue(false, filesCommon.split("<break>")[2]+" could not be found in content grid");
						}

					}
					else {
						appLog.error(cmnPath+" is not found in folder structure");
						sa.assertTrue(false, cmnPath+" is not found in folder structure");
					}
					if (fp.verifyFolderPathdummy(shdPath, null, null, M9FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {

						for (int i = 0;i<3;i++) {
							if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesShared.split("<break>")[i], true)) {
								appLog.info(filesShared.split("<break>")[i]+" is successfully found in content grid");
							}
							else {
								appLog.error(filesShared.split("<break>")[i]+" could not be found in content grid");
								sa.assertTrue(false, filesShared.split("<break>")[i]+" could not be found in content grid");
							}
						}
					}
					else {
						appLog.error(shdPath+" is not found in folder structure");
						sa.assertTrue(false, shdPath+" is not found in folder structure");
					}
					if (fp.verifyFolderPathdummy(stdPath, M9Institution1, M9LimitedPartner1, M9FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {

						for (int i = 0;i<3;i++) {
							if (fp.verifyFileinContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace, filesStandard.split("<break>")[i], true)) {
								appLog.info(filesStandard.split("<break>")[i]+" is successfully found in content grid");
							}
							else {
								appLog.error(filesStandard.split("<break>")[i]+" could not be found in content grid");
								sa.assertTrue(false, filesStandard.split("<break>")[i]+" could not be found in content grid");
							}
						}
					}

					else {
						appLog.error(stdPath+" is not found in content grid");
						sa.assertTrue(false, stdPath+" is not found in content grid");
					}
					switchToDefaultContent(driver);
				}
				else {
					appLog.error("contact could not be found on contacts page");
					sa.assertTrue(false, "contact could not be found on contacts page");
				}
			}
			else {
				appLog.error("contacts tab is not clickable");
				sa.assertTrue(false, "contacts tab is not clickable");
			}
			lp.CRMlogout();
			sa.assertAll();
		}
	}
	
	@Test
	public void M9tc041_UpdateAllAndDuplicateDoc_ImpactInvestor() {
	LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
	FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
	InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
	String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc041_UpdateAllAndDuplicateDoc_Action", excelLabel.StandardPath);
	String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc041_UpdateAllAndDuplicateDoc_Action", excelLabel.SharedPath);
	String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc041_UpdateAllAndDuplicateDoc_Action", excelLabel.CommonPath);
	String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileCommon);
	String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileShared);
	String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileStandard);
	AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
	String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
	SoftAssert saa = new SoftAssert();
	lp.investorLogin(M9Contact1EmailId, adminPassword);
	List<String> fail_list = null;
	if (selectVisibleTextFromDropDown(driver, ifp.getFirmNameDropdownWRTPage(PageName.AllFirmsPage), "All Firms", "All Firms")) {
		ThreadSleep(5000);
		fail_list=af.verifyAlertsOnAllFirmsPage(filesCommon.split("<break>")[2]+"<break>"+filesStandard+"<break>"+filesShared, Org1FirmName, M9FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpload, PageName.AllFirmsPage); 				
		if (fail_list.isEmpty()) {
			appLog.info("successfully verified std and shared files on all firms alerts");
			}
		else {
			for (int i = 0;i<fail_list.size();i++) {
				appLog.error("docs could not be verified on all firms page");
				sa.assertTrue(false, "docs could not be verified on all firms page");
			}
			
		}
	}
	else {
		appLog.error("could not find all firms option in dropdown");
		sa.assertTrue(false, "could not find all firms option in dropdown");
	}
	if (selectVisibleTextFromDropDown(driver, ifp.getFirmNameDropdownWRTPage(PageName.AllFirmsPage),Org1FirmName,Org1FirmName)) {
		click(driver, ifp.getAllDocumentsTab(30), "alldocs tab", action.BOOLEAN);
		fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(filesCommon.split("<break>")[2]+"<break>"+filesStandard+"<break>"+filesShared, M9FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments, activityType.DocumentUpload, PageName.AllFirmsPage);
		if (fail_list.isEmpty()) {
			appLog.info("successfully verified std and shared files on recent activities page");
				}
		else {
			for (int i = 0;i<fail_list.size();i++) {
				appLog.error("could not verify std and shared files alerts on recent activities tab");
				sa.assertTrue(false, "could not verify std and shared files alerts on recent activities tab");
			}
			
		}
		
		click(driver, ifp.getRecentActivitiesTab(30), "recent activities tab", action.BOOLEAN);
		fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(filesCommon.split("<break>")[2]+"<break>"+filesStandard+"<break>"+filesShared, M9FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities, activityType.DocumentUpload, PageName.AllFirmsPage);
		if (fail_list.isEmpty()) {
			appLog.info("successfully verified std and shared files on recent activities page");
				}
		else {
			for (int i = 0;i<fail_list.size();i++) {
				appLog.error("could not verify std and shared files alerts on recent activities tab");
				sa.assertTrue(false, "could not verify std and shared files alerts on recent activities tab");
			}
			
		}
	}
	else {
		appLog.error("could not find firm name in dropdown selection");
		sa.assertTrue(false, "could not find firm name in dropdown selection");
	}
	
	
	if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
		if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
			saa=ifp.verifyContentGridInvestorSide(driver, PageName.CurrentInvestmentPgae, filesCommon, CRMUser1FirstName+" "+CRMUser1LastName, date);

			sa.combineAssertions(saa);
		}
		else {
			appLog.error(cmnPath+" is not clickable on folder structure");
			sa.assertTrue(false, cmnPath+" is not clickable on folder structure");
		}

		if (fp.verifyFolderPathdummy(shdPath, null, null, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
			saa=ifp.verifyContentGridInvestorSide(driver, PageName.CurrentInvestmentPgae, filesShared, CRMUser1FirstName+" "+CRMUser1LastName, date);

			sa.combineAssertions(saa);
		}
		else {
			appLog.error(shdPath+" is not clickable on folder structure");
			sa.assertTrue(false, shdPath+" is not clickable on folder structure");
		}
		if (fp.verifyFolderPathdummy(stdPath, M9Institution1, M9LimitedPartner1, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
			saa=ifp.verifyContentGridInvestorSide(driver, PageName.CurrentInvestmentPgae, filesStandard, CRMUser1FirstName+" "+CRMUser1LastName, date);

			sa.combineAssertions(saa);
		}
		else {
			appLog.error(stdPath+" is not clickable on folder structure");
			sa.assertTrue(false, stdPath+" is not clickable on folder structure");
		}
	}
	lp.investorLogout();
	sa.assertAll();
}

	@Test
	public void M9tc042_VerifyManageApprovalAfterDelete() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileCommon);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");

		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30),"manage folder icon on InvestorWorkspace", action.SCROLLANDBOOLEAN)) {
					if(!cmnPath.isEmpty() && !cmnPath.equalsIgnoreCase("Commonpath")) {
						String id=null;
						id=fp.getCreatedFolderId(cmnPath, PageName.ManageFolderPopUp, 20);
						System.err.println("id>>>>>>"+id);
						if(id!=null) {
							if(fp.clickOnDeleteFolderButton(id)) {
								
								if(click(driver, fp.getFolderDeleteYesBtn(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), "yes button on delete window", action.BOOLEAN)) {
									appLog.info("Yes button is displaying on folder delete pop up");
								}else {
									appLog.error("Yes button is not displaying on folder delete pop up");
									sa.assertTrue(false, "Yes button is not displaying on folder delete pop up");
								}
								if (click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 30), "close button on manage folder", action.BOOLEAN)) {
									appLog.info("manage folder close button is successfully clicked");
								}
								else {
									appLog.error("manage folder close button is not clickable");
									sa.assertTrue(false, "manage folder close button is not clickable");
								}
							}
							else {
								appLog.error("cannot click on delete button on folder of id "+id);
								sa.assertTrue(false, "cannot click on delete button on folder of id "+id);
							}
						}
						if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[0], M9FundName1+" > "+cmnPath,  CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info(filesCommon.split("<break>")[0]+" common folder documents are successfully found in manage approvals popup pending status");
							}
							else {
								appLog.error(filesCommon.split("<break>")[0]+" common folder documents were not found on manage approvals popup");
								sa.assertTrue(false, filesCommon.split("<break>")[0]+" common folder documents were not found on manage approvals popup");
							}
							
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[1], M9FundName1+" > "+cmnPath,  CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info(filesCommon.split("<break>")[1]+" common folder first 2 documents are successfully found in manage approvals popup pending status");
							}
							else {
								appLog.error(filesCommon.split("<break>")[1]+" common folder first 2 documents were not found on manage approvals popup");
								sa.assertTrue(false, filesCommon.split("<break>")[1]+" common folder first 2 documents were not found on manage approvals popup");
							}
							
						}
						if (click(driver, fp.getCheckAllDocsManageApprovals(60), "checkbox to select all docs manage approvals", action.BOOLEAN)) {
							if (click(driver, fp.getApproveBtnManageApprovals(60), "approve button on manage approvals window", action.BOOLEAN)) {
								if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "yes button on approve window", action.SCROLLANDBOOLEAN)) {
									//folders deleted popup
									if (fp.getDuplicateDocsHeadFolderPath(30, false)!=null) {
										appLog.info("folder path column is successfully displayed on folder delete popup");
									}
									else {
										appLog.error("folder path column is not visible on folder delete popup");
										sa.assertTrue(false, "folder path column is not visible on folder delete popup");
									}
									if (fp.getFolderDeletedText(60).getText().trim().equals(FundsPageErrorMessage.manageApprovalFolderDeleted)) {
										appLog.info("folder deleted error message is successfully found");
									}
									else {
										appLog.error("folder deleted error could not be found");
										sa.assertTrue(false, "folder deleted error could not be found");
									}
									if (fp.getDocumentsHeadOnFolderDeletePopup(60, false)!=null) {
										appLog.info("documents head is successfully found on folder delete popup");
									}
									else {
										appLog.error("documents head cannot be found on folder delete popup");
										sa.assertTrue(false, "documents head cannot be found on folder delete popup");
									}
									if (fp.getFoldersDeletedOkButton(60)!=null) {
										appLog.info("folders delete ok button is successfully present on popup");
									}
									else {
										appLog.error("ok button is not visible on folders delete popup");
										sa.assertTrue(false, "ok button is not visible on folders delete popup");
									}
									if (fp.getFoldersDeletedCrossButton(60)!=null) {
										appLog.info("cross icon on folders delete popup is successfully displayed");
									}
									else {
										appLog.error("cross icon is not visible on folders delete popup");
										sa.assertTrue(false, "cross icon is not visible on folders delete popup");
									}
									if (checkSorting(driver, SortOrder.Assecending, fp.deleteDocsColumnList(60))) {
										appLog.info("default sorting is successfully verified in ascending order");
									}
									else {
										appLog.error("default sorting cannot be verified");
										sa.assertTrue(false, "default sorting cannot be verified");
									}
									click(driver,fp.getDocumentsHeadOnFolderDeletePopup(60, false), "document name on folder delete popup", action.BOOLEAN);
									click(driver,fp.getDocumentsHeadOnFolderDeletePopup(60, false), "document name on folder delete popup", action.BOOLEAN);
									if (fp.verifyDocumentInFolderDeleteWindow(filesCommon.split("<break>")[0]+"/"+M9FundName1+" > "+cmnPath+"<break>"+filesCommon.split("<break>")[1]+"/"+M9FundName1+" > "+cmnPath)) {
										appLog.info("docs on delete window is successfully verified");
									}
									else {
										appLog.error("documents "+filesCommon.split("<break>")[0]+" and "+filesCommon.split("<break>")[1]+" cannot be found on delete window");
										sa.assertTrue(false, "documents "+filesCommon.split("<break>")[0]+" and "+filesCommon.split("<break>")[1]+" cannot be found on delete window");
									}
									//4
									if (click(driver, fp.getDocumentsHeadOnFolderDeletePopup(60, false), "Documents Head On Folder Delete Popup", action.BOOLEAN)) {
										if (checkSorting(driver, SortOrder.Decending, fp.deleteDocsColumnList(60))) {
											appLog.info("successfully verified delete docs column list in descending order");
										}
										else {
											appLog.error("sorting cannot be verified descending order delete docs column list");
											sa.assertTrue(false, "sorting cannot be verified descending order delete docs column list");
										}
										if (click(driver, fp.getDocumentsHeadOnFolderDeletePopup(60, false), "Documents Head On Folder Delete Popup", action.BOOLEAN)) {
											if (checkSorting(driver, SortOrder.Assecending, fp.deleteDocsColumnList(60))) {
												appLog.info("successfully verified delete docs column list in Assecending order");
											}
											else {
												appLog.error("sorting cannot be verified Assecending order delete docs column list");
												sa.assertTrue(false, "sorting cannot be verified Assecending order delete docs column list");
											}
										}
										else {
											appLog.error("documents header column is not clickable");
											sa.assertTrue(false, "documents header column is not clickable");
										}
									}
									else {
										appLog.error("documents header column is not clickable");
										sa.assertTrue(false, "documents header column is not clickable");
									}
									//5
									if (click(driver, fp.getDuplicateDocsHeadFolderPath(30, false), "Documents Head On Folder Delete Popup", action.BOOLEAN)) {
										if (checkSorting(driver, SortOrder.Assecending, fp.folderDeleteFolderPathList(60))) {
											appLog.info("successfully verified delete docs column list in Assecending order");
										}
										else {
											appLog.error("sorting cannot be verified Assecending order delete docs column list");
											sa.assertTrue(false, "sorting cannot be verified Assecending order delete docs column list");
										}
										//6
										if (click(driver, fp.getDuplicateDocsHeadFolderPath(30,true), "Documents Head On Folder Delete Popup", action.BOOLEAN)) {
											if (checkSorting(driver, SortOrder.Decending, fp.folderDeleteFolderPathList(60))) {
												appLog.info("successfully verified delete docs column list in Decending order");
											}
											else {
												appLog.error("sorting cannot be verified Decending order on folder delete docs column list");
												sa.assertTrue(false, "sorting cannot be verified Decending order on folder delete docs column list");
											}
										}
										else {
											appLog.error("documents head on folder delete popup is not clickable");
											sa.assertTrue(false, "documents head on folder delete popup is not clickable");

										}
									}
									else {
										appLog.error("folder path column is not clickable so cannot verify sorting");
										sa.assertTrue(false, "folder path column is not clickable so cannot verify sorting");
									}
									ThreadSleep(5000);
									//7
									if (click(driver, fp.getFoldersDeletedOkButton(60), "ok button folder delete popup", action.BOOLEAN)) {
										if (fp.getApproveConfirmPopupText(60).getText().trim().equals("0 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
											appLog.info("approve success message is found successfully");
										}
										else {
											appLog.error("approve success message cannot be verified");
											sa.assertTrue(false, "approve success message cannot be verified");
										}
										if (click(driver, fp.getManageApprovalsApproveConfirmationCrossIcon(60), "approve confirm cross icon", action.BOOLEAN)) {

										}
										fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[0], M9FundName1+" > "+cmnPath,  CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date);
										if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments, filesCommon.split("<break>")[0]+"<break>"+filesCommon.split("<break>")[1], M9FundName1+" > "+cmnPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
										appLog.info(filesCommon.split("<break>")[0]+" common folder documents are successfully found in manage approvals popup pending status");
										}
										else {
											appLog.error(filesCommon.split("<break>")[0]+" common folder documents were not found on manage approvals popup");
											sa.assertTrue(false, filesCommon.split("<break>")[0]+" common folder documents were not found on manage approvals popup");
										}
										
										fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[1], M9FundName1+" > "+cmnPath,  CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date);
										if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, filesCommon.split("<break>")[1], M9FundName1+" > "+cmnPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
											appLog.info(filesCommon.split("<break>")[1]+" common folder first 2 documents are successfully found in manage approvals popup pending status");
										}
										else {
											appLog.error(filesCommon.split("<break>")[1]+" common folder first 2 documents were not found on manage approvals popup");
											sa.assertTrue(false, filesCommon.split("<break>")[1]+" common folder first 2 documents were not found on manage approvals popup");
										}
										
										if (click(driver, fp.getApproveBtnManageApprovals(60), "approve button manage approvals", action.SCROLLANDBOOLEAN)) {
											if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "yes button approve window", action.SCROLLANDBOOLEAN)) {

												if (fp.getDuplicateDocsHeadFolderPath(30, false)!=null) {
													appLog.info("folder path column is successfully displayed on folder delete popup");
												}
												else {
													appLog.error("folder path column is not visible on folder delete popup");
													sa.assertTrue(false, "folder path column is not visible on folder delete popup");
												}
												if (fp.getFolderDeletedText(60).getText().trim().equals(FundsPageErrorMessage.manageApprovalFolderDeleted)) {
													appLog.info("folder deleted error message is successfully found");
												}
												else {
													appLog.error("folder deleted error could not be found");
													sa.assertTrue(false, "folder deleted error could not be found");
												}
												if (fp.getDocumentsHeadOnFolderDeletePopup(60, false)!=null) {
													appLog.info("documents head is successfully found on folder delete popup");
												}
												else {
													appLog.error("documents head cannot be found on folder delete popup");
													sa.assertTrue(false, "documents head cannot be found on folder delete popup");
												}
												if (fp.getFoldersDeletedOkButton(60)!=null) {
													appLog.info("folders delete ok button is successfully present on popup");
												}
												else {
													appLog.error("ok button is not visible on folders delete popup");
													sa.assertTrue(false, "ok button is not visible on folders delete popup");
												}
												if (fp.getFoldersDeletedCrossButton(60)!=null) {
													appLog.info("cross icon on folders delete popup is successfully displayed");
												}
												else {
													appLog.error("cross icon is not visible on folders delete popup");
													sa.assertTrue(false, "cross icon is not visible on folders delete popup");
												}
												if (checkSorting(driver, SortOrder.Assecending, fp.deleteDocsColumnList(60))) {
													appLog.info("default sorting is successfully verified in ascending order");
												}
												else {
													appLog.error("default sorting cannot be verified");
													sa.assertTrue(false, "default sorting cannot be verified");
												}
												click(driver,fp.getDocumentsHeadOnFolderDeletePopup(60, false), "document name on folder delete popup", action.BOOLEAN);
												if (fp.verifyDocumentInFolderDeleteWindow(filesCommon.split("<break>")[0]+"/"+M9FundName1+" > "+cmnPath+"<break>"+filesCommon.split("<break>")[1]+"/"+M9FundName1+" > "+cmnPath)) {
													appLog.info("files are successfully found in folder delete window");
												}
												else {
													appLog.error("files could not be found in folder delete window");
													sa.assertTrue(false, "files could not be found in folder delete window");
												}
												if (click(driver, fp.getFoldersDeletedCrossButton(60), "folder delete cross icon", action.BOOLEAN)) {
													if (fp.getApproveConfirmPopupText(60).getText().trim().equals("0 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
														appLog.info("approve success message is found successfully");
													}
													else {
														appLog.error("approve success message cannot be verified");
														sa.assertTrue(false, "approve success message cannot be verified");
													}
													if (click(driver, fp.getManageApprovalsApproveConfirmationCrossIcon(60), "approve confirmation cross icon", action.SCROLLANDBOOLEAN)) {
														if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments, filesCommon.split("<break>")[0]+"<break>"+filesCommon.split("<break>")[1], M9FundName1+" > "+cmnPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
															appLog.info("successfully found files in manage approvals pending document grid");
														}
														else {
															appLog.error("files are not found in manage approvals pendingc doc window");
															sa.assertTrue(false, "files are not found in manage approvals pendingc doc window");
														}
													}
													else {
														appLog.error("approve confirmation cross icon is not clickable");
														sa.assertTrue(false, "approve confirmation cross icon is not clickable");
													}
												}
												else {
													appLog.error("folders delete cross icon is not clickable");
													sa.assertTrue(false, "folders delete cross icon is not clickable");
												}
											}
											else {
												appLog.error("manage approvals yes button is not clickable");
												sa.assertTrue(false, "manage approvals yes button is not clickable");
											}
										}
										else {
											appLog.error("approve button manage approvals is not clickable");
											sa.assertTrue(false, "approve button manage approvals is not clickable");
										}
									}
									else {
										appLog.error("ok button on folders delete popup is not clickable");
										sa.assertTrue(false, "ok button on folders delete popup is not clickable");
									}
								}
								else {
									appLog.error("manage approvals yes button is not clickable");
									sa.assertTrue(false, "manage approvals yes button is not clickable");
								}
							}
							else {
								appLog.error("approve button manage approvals is not clickable");
								sa.assertTrue(false, "approve button manage approvals is not clickable");
							}
						}
						else {
							appLog.error("checkbox for approving all docs is not clickable");
							sa.assertTrue(false, "checkbox for approving all docs is not clickable");
						}

					}
				}
				else {
					appLog.error("manage folder icon is not clickable");
					sa.assertTrue(false, "manage folder icon is not clickable");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not found on funds tab");
				sa.assertTrue(false, M9FundName1+" is not found on funds tab");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc043_CreateSameNameFolderAndVerifyInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc037_VerifyOnlineImportInvestors_Action", excelLabel.UploadedFileCommon);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M9FundName1)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Fundraising workspace view.");
				if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "Manage folder icon", action.BOOLEAN)){

					if(click(driver, fp.getAllFolderAddIcon(Workspace.InvestorWorkspace, 30), "Add folder button", action.BOOLEAN)){

						if(click(driver, fp.getFolderTypeRadioButton(FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30),"common radio button",action.BOOLEAN)) {
							appLog.info("Common folder radio button is verified.");
							if(sendKeys(driver, fp.getParentFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30), cmnPath.split(" ")[0], "Parent folder name text box", action.BOOLEAN)){

								if(click(driver, fp.getParentFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30), "Save button", action.BOOLEAN)){
									if (click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 30), "close button on manage folder window", action.SCROLLANDBOOLEAN)) {
										appLog.info("close button on manage folder window is clicked");
									}
									else {
										appLog.error("close button is not clickable");
										sa.assertTrue(false, "close button is not clickable");
									}
								}
								else {
									appLog.error("save button on manage approvals window is not clickable");
									sa.assertTrue(false, "save button on manage approvals window is not clickable");
								}
							}
						else {
							appLog.error("folder name text box is not visible on manage folder window");
							sa.assertTrue(false, "folder name text box is not visible on manage folder window");
						}
						}
						else {
							appLog.error("radio button for common folder is not clickable");
							sa.assertTrue(false, "radio button for common folder is not clickable");
						}
					}
					else {
						appLog.error("add icon for all folders is not clickable");
						sa.assertTrue(false, "add icon for all folders is not clickable");
					}
				}
				else {
					appLog.error("manage folder icon is not clickable");
					sa.assertTrue(false, "manage folder icon is not clickable");
				}
				if (click(driver,fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
					if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments, filesCommon.split("<break>")[0]+"<break>"+filesCommon.split("<break>")[1], M9FundName1+" > "+cmnPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
						appLog.info("files are successfully verified on manage approvals ui");
					}
					else {
						appLog.error("files were not found correctly on manage approvals ui");
						sa.assertTrue(false, "files were not found correctly on manage approvals ui");
					}
					
					if (click(driver, fp.getCheckAllDocsManageApprovals(60), "header checkbox manage approvals", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getManageApprovalsApproveBtn(60), "approve button", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "yes button approve popup", action.SCROLLANDBOOLEAN)) {
								if (fp.getFolderDeletedText(60).getText().trim().equals(FundsPageErrorMessage.manageApprovalFolderDeleted)) {
									appLog.info("folder delete popup is successfully verified");
								}
								else {
									appLog.error("folder deleted popup could not be verified");
									sa.assertTrue(false, "folder deleted popup could not be verified");
								}
							}
							else {
								appLog.error("yes button manage approvals is not clickable");
								sa.assertTrue(false, "yes button manage approvals is not clickable");
							}
						}
						else {
							appLog.error("approve button is not clickable");
							sa.assertTrue(false, "approve button is not clickable");
						}
					}
					else {
						appLog.error("checkbox to select all docs is not clickable");
						sa.assertTrue(false, "checkbox to select all docs is not clickable");
					}
				}
				else {
					appLog.error("manage approval icon is not clickable");
					sa.assertTrue(false, "manage approval icon is not clickable");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not clickable");
				sa.assertTrue(false, M9FundName1+" is not clickable");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc044_UploadFilesCRMUser2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		String cmnUpload="UploadFiles\\Module9\\CommonUser2";
		String shdUpload="UploadFiles\\Module9\\SharedUser2";
		String stdUpload="UploadFiles\\Module9\\StandardUser2";
		String User2first = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.MyProfile_FName);
		String User2last= ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.MyProfile_LName);
		
		
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
					if (fp.uploadFile(cmnPath, null, cmnUpload, UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
						appLog.info("documents are uploaded successfully");
					}
					else {
						appLog.error("documents could not be uploaded successfully");
						sa.assertTrue(false, "documents could not be uploaded successfully");
					}
					if (fp.uploadFile(shdPath, null, shdUpload, UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
						appLog.info("documents are uploaded successfully");
					}
					else {
						appLog.error("documents could not be uploaded successfully");
						sa.assertTrue(false, "documents could not be uploaded successfully");
					}
					if (fp.uploadFile(stdPath, M9Institution1+"/"+M9LimitedPartner1+"<break>"+M9Institution2+"/"+M9LimitedPartner2, stdUpload, UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
						appLog.info("documents are uploaded successfully");
					}
					else {
						appLog.error("documents could not be uploaded successfully");
						sa.assertTrue(false, "documents could not be uploaded successfully");
					}
					switchToFrame(driver, 30,fp.getFrame( PageName.FundsPage, 20));
					//scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "fundraising workspace view");

				String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
				String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared);
				String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.UploadedFileStandard);

				if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
					if (selectVisibleTextFromDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.PendingDocuments, 30), "pending documents dropdown", "All Pending Documents")) {
						for (int i = 0;i<4;i++) {
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[i], M9FundName1+" > "+cmnPath, User2first+" "+User2last, Org1FirmName, date)) {
								appLog.info("common file "+filesCommon.split("<break>")[i]+" is successfully verified on manage approval window");
							}
							else {
								appLog.error(filesCommon.split("<break>")[i]+" could not be verified on manage approvals window");
								sa.assertTrue(false, filesCommon.split("<break>")[i]+" could not be verified on manage approvals window");
							}
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesShared.split("<break>")[i], M9FundName1+" > "+shdPath, User2first+" "+User2last, Org1FirmName, date)) {
							appLog.info("shared file "+filesShared.split("<break>")[i]+" is successfully verified on manage approval window");
						}
						else {
								appLog.error(filesShared.split("<break>")[i]+" could not be verified on manage approvals window");
								sa.assertTrue(false, filesShared.split("<break>")[i]+" could not be verified on manage approvals window");
						}
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[i], M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+" > "+stdPath, User2first+" "+User2last, Org1FirmName, date)) {
							
							appLog.info("std file(inst 1)  "+filesStandard.split("<break>")[i]+" is successfully verified on manage approval window");
						}
						else {
								appLog.error(filesStandard.split("<break>")[i]+" could not be verified on manage approvals window");
								sa.assertTrue(false, filesStandard.split("<break>")[i]+" could not be verified on manage approvals window");
						//	}
						}
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[i], M9FundName1+" > "+M9Institution2+" > "+M9LimitedPartner2+" > "+stdPath, User2first+" "+User2last, Org1FirmName, date)) {
					//	if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesStandard.split("<break>")[i] , M9FundName1+" > "+M9Institution2+" > "+M9LimitedPartner2+" > "+stdPath, "pending", CRMUser2FirstName+" "+CRMUser2LastName, Org1FirmName, date)) {
							appLog.info("std files(inst2) "+filesStandard.split("<break>")[i]+" is successfully verified on manage approval window");
						}
						else {
								appLog.error(filesStandard.split("<break>")[i]+" could not be verified on manage approvals window");
								sa.assertTrue(false, filesStandard.split("<break>")[i]+" could not be verified on manage approvals window");
						//	}
						}
					}
					}
					else {
						appLog.error("all pending docs cannot be found on dropdown on manage approvals");
						sa.assertTrue(false, "all pending docs cannot be found on dropdown on manage approvals");
					}
					//closing and opening manage approval popup
					click(driver, fp.getManageApprovalsCancelBtn(60),"cancel button on manage approvals", action.BOOLEAN);
					click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN);
					
					//verify default sorting
					if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalUploadedOnList(ManageApprovalTabs.PendingDocuments))) {
						appLog.info("sorting is successfully verified Decending order on pending docs for uploaded on column");
					}
					else {
						appLog.error("uploaded on column sorting is not correct");
						sa.assertTrue(false, "uploaded on column sorting is not correct");
					}
					if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(6), "uploaded on column", action.SCROLLANDBOOLEAN)) {
						if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalUploadedOnList(ManageApprovalTabs.PendingDocuments))) {
							appLog.info("sorting is successfully verified Assecending order on pending docs for uploaded on column");
						}
						else {
							appLog.error("sorting is incorrect for Assecending uploaded on column");
							sa.assertTrue(false, "sorting is incorrect for Assecending uploaded on column");
						}

						if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(6), "uploaded on column", action.SCROLLANDBOOLEAN)) {
							if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalUploadedOnList(ManageApprovalTabs.PendingDocuments))) {
								appLog.info("sorting is successfully verified Decending order on pending docs for uploaded on column");
							}
							else {
								appLog.error("sorting is incorrect for Decending uploaded on column");
								sa.assertTrue(false, "sorting is incorrect for Decending uploaded on column");
							}
						}
						else {
							appLog.error("uplaoded on column is not clickable");
							sa.assertTrue(false, "uplaoded on column is not clickable");
						}
					}
					else {
						appLog.error("uplaoded on column is not clickable");
						sa.assertTrue(false, "uplaoded on column is not clickable");
					}
					//sorting on uploaded by column
					if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(4), "uploaded by column", action.SCROLLANDBOOLEAN)) {
						if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalUploadedByList(ManageApprovalTabs.PendingDocuments))) {
							appLog.info("sorting is successfully verified Assecending order on pending docs for uploaded by column");
						}
						else {
							appLog.error("sorting is incorrect for Assecending uploaded by column");
							sa.assertTrue(false, "sorting is incorrect for Assecending uploaded by column");
						}

						if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(4), "uploaded by column", action.SCROLLANDBOOLEAN)) {
							if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalUploadedByList(ManageApprovalTabs.PendingDocuments))) {
								appLog.info("sorting is successfully verified Decending order on pending docs for uploaded by column");
							}
							else {
								appLog.error("sorting is incorrect for Decending uploaded by column");
								sa.assertTrue(false, "sorting is incorrect for Decending uploaded by column");
							}
						}
						else {
							appLog.error("uplaoded by column is not clickable");
							sa.assertTrue(false, "uplaoded by column is not clickable");
						}
					}
					else {
						appLog.error("uplaoded by column is not clickable");
						sa.assertTrue(false, "uplaoded by column is not clickable");
					}
					//sorting on document name column
					if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(1), "document name column", action.SCROLLANDBOOLEAN)) {
						if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalDocumentNameList(ManageApprovalTabs.PendingDocuments))) {
							appLog.info("sorting is successfully verified Assecending order on pending docs for document name column");
						}
						else {
							appLog.error("sorting is incorrect for Assecending document name column");
							sa.assertTrue(false, "sorting is incorrect for Assecending document name column");
						}

						if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(1), "document name column", action.SCROLLANDBOOLEAN)) {
							if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalDocumentNameList(ManageApprovalTabs.PendingDocuments))) {
								appLog.info("sorting is successfully verified Decending order on pending docs for document name column");
							}
							else {
								appLog.error("sorting is incorrect for Decending document name column");
								sa.assertTrue(false, "sorting is incorrect for Decending document name column");
							}
						}
						else {
							appLog.error("document name column is not clickable");
							sa.assertTrue(false, "document name column is not clickable");
						}
					}
					else {
						appLog.error("document name column is not clickable");
						sa.assertTrue(false, "document name column is not clickable");
					}
					//sorting on firm name column
					if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(5), "firm name column", action.SCROLLANDBOOLEAN)) {
						if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalFirmNameList(ManageApprovalTabs.PendingDocuments))) {
							appLog.info("sorting is successfully verified Assecending order on pending docs for firm name column");
						}
						else {
							appLog.error("sorting is incorrect for Assecending firm name column");
							sa.assertTrue(false, "sorting is incorrect for Assecending firm name column");
						}

						if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(5), "firm name column", action.SCROLLANDBOOLEAN)) {
							if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalFirmNameList(ManageApprovalTabs.PendingDocuments))) {
								appLog.info("sorting is successfully verified Decending order on pending docs for firm name column");
							}
							else {
								appLog.error("sorting is incorrect for Decending firm name column");
								sa.assertTrue(false, "sorting is incorrect for Decending firm name column");
							}
						}
						else {
							appLog.error("firm name column is not clickable");
							sa.assertTrue(false, "firm name column is not clickable");
						}
					}
					else {
						appLog.error("firm name column is not clickable");
						sa.assertTrue(false, "firm name column is not clickable");
					}
				}
				else {
					appLog.error("manage approval icon is not clickable");
					sa.assertTrue(false, "manage approval icon is not clickable");
				}

				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not found on funds page");
				sa.assertTrue(false, M9FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc045_VerifyPendingFilesDuplicateDocPopup() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc044_UploadFilesCRMUser2", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc044_UploadFilesCRMUser2", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M9tc044_UploadFilesCRMUser2", excelLabel.UploadedFileStandard);
		String filesApprovedShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.UploadedFileShared);
		String filesApprovedStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc034_VerifyDocUploadManageInvestorForInvestorWorkspace_Action", excelLabel.UploadedFileStandard);
		String User2first = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.MyProfile_FName);
		String User2last= ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.MyProfile_LName);
		
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		String cmnUpload="UploadFiles\\Module9\\CommonUser2";
		String shdUpload="UploadFiles\\Module9\\SharedUser2";
		String stdUpload="UploadFiles\\Module9\\StandardUser2";
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (fp.verifyFolderPathdummy(cmnPath,null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
				if (click(driver, fp.getUploadIcon(Workspace.InvestorWorkspace, 30), "upload icon", action.SCROLLANDBOOLEAN)) {
					if (fp.verifyDuplicateWindowWhileUploading(Workspace.InvestorWorkspace,null,cmnUpload, filesCommon, cmnPath,FolderType.Common, UpdateIgnore.Update)) {
						appLog.info("duplicate docs are successfully verified for cmn folder");
					}
					else {
						appLog.error("duplicate docs cannot be verified for cmn folder");
						sa.assertTrue(false, "duplicate docs cannot be verified for cmn folder");
					}
				}
				else {
					appLog.error("upload icon is not clickable");
					sa.assertTrue(false, "upload icon is not clickable");
				}
			}
			else {
				appLog.error(cmnPath+" could not be found in folder structure");
				sa.assertTrue(false, cmnPath+" could not be found in folder structure");
			}
			switchToDefaultContent(driver);
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (fp.verifyFolderPathdummy(shdPath,null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
				if (click(driver, fp.getUploadIcon(Workspace.InvestorWorkspace, 30), "upload icon", action.SCROLLANDBOOLEAN)) {
					if (fp.verifyDuplicateWindowWhileUploading(Workspace.InvestorWorkspace,null,shdUpload, filesShared, shdPath,FolderType.Shared, UpdateIgnore.Ignore)) {
						appLog.info("duplicate docs are successfully verified for shd folder");
					}
					else {
						appLog.error("duplicate docs cannot be verified for shd folder");
						sa.assertTrue(false, "duplicate docs cannot be verified for shd folder");
					}
				}
				else {
					appLog.error("upload icon is not clickable");
					sa.assertTrue(false, "upload icon is not clickable");
				}
			}
			else {
				appLog.error(shdPath+" could not be found in folder structure");
				sa.assertTrue(false, shdPath+" could not be found in folder structure");
			}
			switchToDefaultContent(driver);
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			for (int i = 0;i<filesShared.split("<break>").length;i++) {
				if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesShared.split("<break>")[i], false)) {
					appLog.info("file "+filesShared.split("<break>")[i]+" is successfully found");
				}
				else {
					appLog.error("file "+filesShared.split("<break>")[i]+" is not found");
					sa.assertTrue(false, "file "+filesShared.split("<break>")[i]+" is not found");
				}
			}
			for (int i = 0;i<filesApprovedShared.split("<break>").length;i++) {
				if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesShared.split("<break>")[i], true)) {
					appLog.info("file "+filesApprovedShared.split("<break>")[i]+" is successfully found");
				}
				else {
					appLog.error("file "+filesApprovedShared.split("<break>")[i]+" is not found");
					sa.assertTrue(false, "file "+filesApprovedShared.split("<break>")[i]+" is not found");
				}
			}
			if (fp.verifyFolderPathdummy(stdPath,M9Institution1, M9LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
				if (click(driver, fp.getUploadIcon(Workspace.InvestorWorkspace, 30), "upload icon", action.SCROLLANDBOOLEAN)) {

					if (fp.verifyDuplicateWindowWhileUploading(Workspace.InvestorWorkspace,M9Institution1+"/"+M9LimitedPartner1+"<break>"+M9Institution2+"/"+M9LimitedPartner2,stdUpload, filesStandard, stdPath,FolderType.Standard, UpdateIgnore.Update)) {
						appLog.info("duplicate docs are successfully verified for std folder");
					}
					else {
						appLog.error("duplicate docs cannot be verified for std folder");
						sa.assertTrue(false, "duplicate docs cannot be verified for std folder");
					}
				}
				else {
					appLog.error("upload icon is not clickable");
					sa.assertTrue(false, "upload icon is not clickable");
				}
			}
			else {
				appLog.error(stdPath+" could not be found in folder structure");
				sa.assertTrue(false, stdPath+" could not be found in folder structure");
			}
			switchToDefaultContent(driver);
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
				if (selectVisibleTextFromDropDown(driver,fp.getManageApprovalsDropdown(ManageApprovalTabs.PendingDocuments, 30), "pending docs dropdown", "All Pending Documents")) {
						for (int i = 0;i<4;i++) {
						
//						if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesCommon.split("<break>")[i] , M9FundName1+" > "+cmnPath, "pending", CRMUser2FirstName+" "+CRMUser2LastName, Org1FirmName, date)) {
//							appLog.info("common file "+filesCommon.split("<break>")[i]+" is successfully verified on manage approval window");
//						}
//						else {
//							click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments, 30), "document name column", action.BOOLEAN);
//							if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesCommon.split("<break>")[i] , M9FundName1+" > "+cmnPath, "pending", CRMUser2FirstName+" "+CRMUser2LastName, Org1FirmName, date)) {
//								appLog.info("common file "+filesCommon.split("<break>")[i]+" is successfully verified on manage approval window");
//							}
//						}
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[i], M9FundName1+" > "+cmnPath, User2first+" "+User2last, Org1FirmName, date)) {
							appLog.info("common file "+filesCommon.split("<break>")[i]+" is successfully verified on manage approval window");	
						}
						else {
							appLog.error(filesCommon.split("<break>")[i] + " could not be verified on manage approvals");
							sa.assertTrue(false, filesCommon.split("<break>")[i] + " could not be verified on manage approvals");	
						}

						//if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesShared.split("<break>")[i] , M9FundName1+" > "+shdPath, "pending", CRMUser2FirstName+" "+CRMUser2LastName, Org1FirmName, date)) {
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesShared.split("<break>")[i], M9FundName1+" > "+shdPath, User2first+" "+User2last, Org1FirmName, date)) {
							
						appLog.info("shared file "+filesShared.split("<break>")[i]+" is successfully verified on manage approval window");
						}
						else {
					/*		click(driver, fp.getDocumentNameManageApproval(ManageApprovalTabs.PendingDocuments, 30), "document name column", action.BOOLEAN);
							if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesShared.split("<break>")[i] , M9FundName1+" > "+shdPath, "pending", CRMUser2FirstName+" "+CRMUser2LastName, Org1FirmName, date)) {
								appLog.info("shared file "+filesShared.split("<break>")[i]+" is successfully verified on manage approval window");
							}
					*/
							appLog.error(filesShared.split("<break>")[i] + " could not be verified on manage approvals");
							sa.assertTrue(false, filesShared.split("<break>")[i] + " could not be verified on manage approvals");	
						
						}
						//if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments,filesStandard.split("<break>")[i] , M9FundName1+" > "+M9Institution1+" > "+stdPath, "pending", CRMUser2FirstName+" "+CRMUser2LastName, Org1FirmName, date)) {
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[i], M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+" > "+stdPath, User2first+" "+User2last, Org1FirmName,date)) {
							appLog.info("std file(inst 1)  "+filesStandard.split("<break>")[i]+" is successfully verified on manage approval window");
						}
						else {
							appLog.error(filesStandard.split("<break>")[i] + " could not be verified on manage approvals");
							sa.assertTrue(false, filesStandard.split("<break>")[i] + " could not be verified on manage approvals");	
						}
						if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[i], M9FundName1+" > "+M9Institution2+" > "+M9LimitedPartner2+" > "+stdPath, User2first+" "+User2last, Org1FirmName,date)) {
							appLog.info("std file(inst 2)  "+filesStandard.split("<break>")[i]+" is successfully verified on manage approval window");
						}
						else {
							appLog.error(filesStandard.split("<break>")[i] + " could not be verified on manage approvals");
							sa.assertTrue(false, filesStandard.split("<break>")[i] + " could not be verified on manage approvals");	
						}
					}
				}
				else {
					appLog.error("all pending documents is not found in manage approvals popup");
					sa.assertTrue(false, "all pending documents is not found in manage approvals popup");
				}
			}
			else {
				appLog.error("manage approval icon is not clickable");
				sa.assertTrue(false, "manage approval icon is not clickable");
			}
			switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not found on funds tab");
				sa.assertTrue(false, M9FundName1+" is not found on funds tab");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc046_VerifyFolderDeletedAndCountOfDocs() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc044_UploadFilesCRMUser2", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc044_UploadFilesCRMUser2", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M9tc044_UploadFilesCRMUser2", excelLabel.UploadedFileStandard);
		String User2first = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.MyProfile_FName);
		String User2last= ExcelUtils.readData("Users",excelLabel.Variable_Name, "User2", excelLabel.MyProfile_LName);
		
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
				for (int i = 2;i<4;i++) {
					if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[i], M9FundName1+" > "+cmnPath, User2first+" "+User2last, Org1FirmName, date)) {
						if (click(driver, fp.checkboxForFileInManageApprovals(filesCommon.split("<break>")[i],User2first+" "+User2last), "checkbox for common 1 file", action.SCROLLANDBOOLEAN) ) {
							appLog.info("clicked on checkbox for "+filesCommon.split("<break>")[i]);
						}
						else {
							appLog.error("checkbox for "+filesCommon.split("<break>")[i]+" is not clickable");
							sa.assertTrue(false, "checkbox for "+filesCommon.split("<break>")[i]+" is not clickable");
						}
					}
					else {
						appLog.error("common file not found on pending docs manage approvals");
						sa.assertTrue(false, "common file not found on pending docs manage approvals");
					}
					//shared
					if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesShared.split("<break>")[i], M9FundName1+" > "+shdPath, User2first+" "+User2last, Org1FirmName, date)) {
						if (click(driver, fp.checkboxForFileInManageApprovals(filesShared.split("<break>")[i]), "checkbox for "+filesShared.split("<break>")[i]+" file", action.SCROLLANDBOOLEAN)) {
						}

					}
					else {
						appLog.error(filesShared.split("<break>")[i]+" Shared file not found");
						sa.assertTrue(false, filesShared.split("<break>")[i]+" Shared file not found");
					}
					//standard

					if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[i], M9Institution1 + " > "+M9LimitedPartner1 + " > "+stdPath, User2first+" "+User2last, Org1FirmName, date)) {
						if (click(driver, fp.checkboxForFileInManageApprovals(filesStandard.split("<break>")[i], M9Institution1), "checkbox for "+filesStandard.split("<break>")[i]+" file", action.SCROLLANDBOOLEAN)) {

						}

					}
					else {
						appLog.error(filesStandard.split("<break>")[i]+ " file not found for inst 1");
						sa.assertTrue(false, filesStandard.split("<break>")[i]+" file not found for inst 1");
					}
				}

				for (int i = 2;i<4;i++) {
					if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[i], M9Institution2 + " > "+M9LimitedPartner2 + " > "+stdPath, User2first+" "+User2last, Org1FirmName,date)) {
						if (click(driver, fp.checkboxForFileInManageApprovals(filesStandard.split("<break>")[i],M9Institution2), "checkbox for "+filesStandard.split("<break>")[i]+" file", action.SCROLLANDBOOLEAN)) {

						}
					}
					else {
						appLog.error(filesStandard.split("<break>")[i]+ " file not found for inst 2");
						sa.assertTrue(false, filesStandard.split("<break>")[i]+" file not found for inst 2");
					}
				}
				 if (click(driver, fp.getApproveBtnManageApprovals(30), "approve button manage approvals", action.SCROLLANDBOOLEAN)) {
					 if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "yes button approve", action.SCROLLANDBOOLEAN)) {
						 List<String> li = fp.verifyDocumentInDuplicateDocManageApprovalPopup(filesCommon.split("<break>")[2]+"/"+M9FundName1+" > "+cmnPath+"<break>"+filesShared.split("<break>")[2]+"/"+M9FundName1+" > "+shdPath+"<break>"+filesStandard.split("<break>")[2]+"/"+M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+" > "+stdPath,false);
						 if (li.isEmpty()) {
							 appLog.info("all documents are successfully verified in duplicate docs window");
						 }
						 else {
							 for (int i = 0;i<li.size();i++) {
								 appLog.error(li.get(i).split("/")[0] + " is not found correctly, path found is "+li.get(i).split("/")[1]);
							 }
						 }
						 if (click(driver, fp.getManageApprovalsUpdateAllDocument(60), "update all document", action.BOOLEAN)) {
							 if (fp.getApproveConfirmPopupText(60).getText().trim().equals("8 "+FundsPageErrorMessage.manageApprovalApproveSuccess)) {
									appLog.info("confirmation text for approval is verified successfully");
								}
								else {
									appLog.error("confirmation text for approval is incorrect");
									sa.assertTrue(false, "confirmation text for approval is incorrect");
								}
								if (click(driver, fp.getManageApprovalsApproveConfirmationCrossIcon(60), "cross icon for successful approve icon", action.SCROLLANDBOOLEAN)) {
									//verify file not present in pending docs tab
									if (isDisplayed(driver, FindElement(driver, "//a[text()='"+filesCommon.split("<break>")[2]+"']", "common file 3", action.BOOLEAN, 5), "visibility", 5, "common file 3")==null) {
										appLog.info("common 3 file is not displayed successfully in pending documents");
									}
									else {
										appLog.error("common 3 file is being displayed but it should not be displayed");
										sa.assertTrue(false, "common 3 file is being displayed but it should not be displayed");
									}
									if (isDisplayed(driver, FindElement(driver, "//a[text()='"+filesCommon.split("<break>")[3]+"']", "common file 4", action.BOOLEAN, 5), "visibility", 5, "common file 4")==null) {
										appLog.info("common 4 file is not displayed successfully in pending documents");
									}
									else {
										appLog.error("common 4 file is being displayed but it should not be displayed");
										sa.assertTrue(false, "common 4 file is being displayed but it should not be displayed");
									}
									if (isDisplayed(driver, FindElement(driver, "//a[text()='"+filesShared.split("<break>")[2]+"']", "Shared file 3", action.BOOLEAN, 5), "visibility", 5, "Shared file 3")==null) {
										appLog.info("Shared 3 file is not displayed successfully in pending documents");
									}
									else {
										appLog.error("Shared 3 file is being displayed but it should not be displayed");
										sa.assertTrue(false, "Shared 3 file is being displayed but it should not be displayed");
									}
									if (isDisplayed(driver, FindElement(driver, "//a[text()='"+filesShared.split("<break>")[3]+"']", "Shared file 4", action.BOOLEAN, 5), "visibility", 5, "Shared file 4")==null) {
										appLog.info("Shared 4 file is not displayed successfully in pending documents");
									}
									else {
										appLog.error("Shared 4 file is being displayed but it should not be displayed");
										sa.assertTrue(false, "Shared 4 file is being displayed but it should not be displayed");
									}
									if (isDisplayed(driver, FindElement(driver, "//a[text()='"+filesStandard.split("<break>")[2]+"']", "Standard file 3", action.BOOLEAN, 5), "visibility", 5, "Standard file 3")==null) {
										appLog.info("Standard 3 file is not displayed successfully in pending documents");
									}
									else {
										appLog.error("Standard 3 file is being displayed but it should not be displayed");
										sa.assertTrue(false, "Standard 3 file is being displayed but it should not be displayed");
									}
									if (isDisplayed(driver, FindElement(driver, "//a[text()='"+filesStandard.split("<break>")[3]+"']", "Standard file 4", action.BOOLEAN, 5), "visibility", 5, "Standard file 4")==null) {
										appLog.info("Standard 4 file is not displayed successfully in pending documents");
									}
									else {
										appLog.error("Standard 4 file is being displayed but it should not be displayed");
										sa.assertTrue(false, "Standard 4 file is being displayed but it should not be displayed");
									}

								}
								else {
									appLog.error("manage approval approve cross icon is not clickable");
									sa.assertTrue(false, "manage approval approve cross icon is not clickable");
								}
						 }
						 else {
							 appLog.error("update all documents link is not clickable");
							 sa.assertTrue(false, "update all documents link is not clickable");
						 }
					 }
					
				 else {
					 appLog.error("yes button is not clickable");
					 sa.assertTrue(false, "yes button is not clickable");
				 }
					}
				else {
					appLog.error("approve button is not clickable");
					sa.assertTrue(false, "approve button is not clickable");
				}
				ThreadSleep(5000);
				 if (click(driver, fp.getApprovedDocsTab(60), "approved docs tab", action.BOOLEAN)) {
					 if (selectVisibleTextFromDropDown(driver,fp.getManageApprovalsDropdown(ManageApprovalTabs.ApprovedDocuments, 30), "approved documents dropdown", "All Approved Documents")) {
						 for (int i = 2;i<4;i++) {
							 //if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.ApprovedDocuments, filesCommon.split("<break>")[i], M9FundName1+" > "+ cmnPath, "approved", null, Org1FirmName, date)) {
							 if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.ApprovedDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), filesCommon.split("<break>")[i], M9FundName1+" > "+ cmnPath, null, Org1FirmName, date)) {
								 appLog.info( filesCommon.split("<break>")[i]+"found common file successfully in approved docs");
							 }
							 else {
								 appLog.error( filesCommon.split("<break>")[i]+"common file could not be found in approved docs");
								 sa.assertTrue(false,  filesCommon.split("<break>")[i]+"common file could not be found in approved docs");
							 }
							 //if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.ApprovedDocuments, filesShared.split("<break>")[i], M9FundName1+" > "+ shdPath, "approved", null, Org1FirmName, date)) {
							 if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.ApprovedDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), filesShared.split("<break>")[i], M9FundName1+" > "+ shdPath,null, Org1FirmName, date)) {
								 appLog.info( filesShared.split("<break>")[i]+"found shared file successfully in approved docs");
							 }
							 else {
								 appLog.error( filesShared.split("<break>")[i]+"shared file could not be found in approved docs");
								 sa.assertTrue(false,  filesShared.split("<break>")[i]+"shared file could not be found in approved docs");
							 }
							 //if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace,  ManageApprovalTabs.ApprovedDocuments, filesStandard.split("<break>")[i], M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+" > "+stdPath, "approved", null, Org1FirmName, date)) {
							 if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.ApprovedDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), filesStandard.split("<break>")[i], M9FundName1+" > "+M9Institution1+" > "+M9LimitedPartner1+" > "+stdPath, null, Org1FirmName, date)) {
								 appLog.info( filesStandard.split("<break>")[i]+"found Standard file successfully in approved docs");
							 }
							 else {
								 appLog.error( filesStandard.split("<break>")[i]+"Standard file could not be found in approved docs");
								 sa.assertTrue(false,  filesStandard.split("<break>")[i]+"Standard file could not be found in approved docs");
							 }
						 }
					 }
					 else {
						 appLog.error("all approved documents is not found in dropdown");
						 sa.assertTrue(false, "all approved documents is not found in dropdown");
					 }
				 }
				 else {
					 appLog.error("approved docs tab is not clickable");
					 sa.assertTrue(false, "approved docs tab is not clickable");
				 }
				}
			else {
				appLog.error("manage approval icon is not clickable");
				sa.assertTrue(false, "manage approval icon is not clickable");
			}
			switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not found on funds page");
				sa.assertTrue(false, M9FundName1+" is not found on funds page");
			}
		
			
			}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
	
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M9tc047_1_UpdateFolderNameAndVerify_InvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc044_UploadFilesCRMUser2", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc044_UploadFilesCRMUser2", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M9tc044_UploadFilesCRMUser2", excelLabel.UploadedFileStandard);
		String updatedcmnPath = cmnPath.split("\\(")[0].trim()+ "UP ("+cmnPath.split("\\(")[1].trim();
		String updatedshdPath = shdPath.split("\\(")[0].trim()+ "UP ("+shdPath.split("\\(")[1].trim();
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
				String id=fp.getCreatedFolderId(shdPath, PageName.ManageFolderPopUp, 20);
				if (id!=null) {
					if(fp.clickOnRenameFolderButton(id)) {
						if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), shdPath.split(" ")[0] + "UP", "shd folder text box", action.BOOLEAN)) {
							if(click(driver, fp.getParentRenameFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), "sub folder save button", action.BOOLEAN)) {
								ThreadSleep(2000);

							}else {
								appLog.error("save button for renaming folder is not clickable");
								sa.assertTrue(false, "save button for renaming folder is not clickable");
							}
						}
						else {
							appLog.error("folder textbox is not visible on web page");
							sa.assertTrue(false, "folder textbox is not visible on web page");
						}
					}
					else {
						appLog.error("rename folder icon is not found");
						sa.assertTrue(false, "rename folder icon is not found");
					}
				}
				//TODO rename common
				/*id=fp.getCreatedFolderId(cmnPath, PageName.ManageFolderPopUp, 20);
				System.err.println("id>>>>>>"+id);
				if(id!=null) {
					if(fp.clickOnRenameFolderButton(id)) {
						if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), cmnPath.split(" ")[0] + "UP", "cmn folder text box", action.BOOLEAN)) {
							if(click(driver, fp.getParentRenameFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), "sub folder save button", action.BOOLEAN)) {
								ThreadSleep(2000);
							}
							else {
								appLog.error("save button for renaming folder is not clickable");
								sa.assertTrue(false, "save button for renaming folder is not clickable");
							}
						}
						else {
							appLog.error("folder textbox is not visible on web page");
							sa.assertTrue(false, "folder textbox is not visible on web page");
						}
					}
					else {
						appLog.error("rename folder icon is not found");
						sa.assertTrue(false, "rename folder icon is not found");
					}
				}
				*/
				id=fp.getCreatedFolderId(stdPath, PageName.ManageFolderPopUp, 20);
				if (id!=null) {
					if(fp.clickOnRenameFolderButton(id)) {
						if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), stdPath+"UP", "shd folder text box", action.BOOLEAN)) {
							if(click(driver, fp.getParentRenameFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), "sub folder save button", action.BOOLEAN)) {
								ThreadSleep(2000);

							}else {
								appLog.error("save button for renaming folder is not clickable");
								sa.assertTrue(false, "save button for renaming folder is not clickable");
							}
						}
						else {
							appLog.error("folder textbox is not visible on web page");
							sa.assertTrue(false, "folder textbox is not visible on web page");
						}
					}
					else {
						appLog.error("rename folder icon is not found");
						sa.assertTrue(false, "rename folder icon is not found");
					}
				}
				if (click(driver, fp.getManageFolderCrossIcon(Workspace.InvestorWorkspace, 30), "manage folder cross icon", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on manage folder cross icon");
				}
				else {
					appLog.error("cross icon on manage folder is not clickable");
					sa.assertTrue(false, "cross icon on manage folder is not clickable");
				}
				if (fp.verifyFolderPathdummy(cmnPath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {

				}
				else {
					appLog.error("cmn folder name is not updated");
					sa.assertTrue(false, "cmn folder name is not updated");
				}
				if (fp.verifyFolderPathdummy(updatedshdPath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {

				}
				else {
					appLog.error("shd folder name is not updated");
					sa.assertTrue(false, "shd folder name is not updated");
				}
				if (fp.verifyFolderPathdummy(stdPath+"UP", M9Institution1, M9LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {

				}
				else {
					appLog.error("std folder name is not updated");
					sa.assertTrue(false, "std folder name is not updated");
				}
				if (click(driver, fp.getInvestmentInfo(Workspace.InvestorWorkspace), "investment info link", action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getInvestmentInfoEdit(30), "investment info edit", action.BOOLEAN)) {
						if (sendKeys(driver, fp.getInvestmentInfoFundNameTxtbox(30), M9FundName1+"UP", "fund name textbox", action.BOOLEAN)) {
							if (click(driver, fp.getInvestmentInfoSaveBtn(30), "save button", action.BOOLEAN)) {
								if (click(driver, fp.getInvestmentInfoCancelButton(30), "cancel button investment info", action.BOOLEAN)) {
									
								}
								else {
									appLog.error("cancel button on investment info is not clickable");
									sa.assertTrue(false, "cancel button on investment info is not clickable");
								}
							}
							else {
								appLog.error("save button on investment info is not clickable");
								sa.assertTrue(false, "save button on investment info is not clickable");
							}
						}
						else {
							appLog.error("fund name textbox is not visible on investment info popup");
							sa.assertTrue(false, "fund name textbox is not visible on investment info popup");
							
						}
					}
					else {
						appLog.error("edit icon is not clickable on investment info window");
						sa.assertTrue(false, "edit icon is not clickable on investment info window");
					}
				}
				else {
					appLog.error("investment info link is not clickable");
					sa.assertTrue(false, "investment info link is not clickable");
				}
				switchToDefaultContent(driver);
				scrollDownThroughWebelement(driver, fp.getEditButton(30), "base page edit button");
				if (click(driver, fp.getEditButton(30), "base page edit button", action.SCROLLANDBOOLEAN)) {
					if(sendKeys(driver, fp.getFundName(60), M9FundName1+"NUP", "Fund Name", action.BOOLEAN)){
						if (click(driver, fp.getSaveButton(60), "Save Button funds page", action.BOOLEAN)) {
							String fundNameViewMode =getText(driver, fp.getFundNameInViewMode(60), "Fund Name", action.BOOLEAN);
							if (fundNameViewMode.equalsIgnoreCase(M9FundName1+"NUP")) {
								appLog.info("Fund is edit successfully.:" + M9FundName1+"NUP");
							}
							else {
								appLog.error("fund name is not edited correctly");
								sa.assertTrue(false, "fund name is not edited correctly");
							}
						}
						else {
							appLog.error("save button is not clickable on funds page");
							sa.assertTrue(false, "save button is not clickable on funds page");
						}
							
					}
					else {
						appLog.error("fund name textbox is not visible");
						sa.assertTrue(false, "fund name textbox is not visible");
					}
						
				}
				else {
					appLog.error("edit button is not clickable on funds page");
					sa.assertTrue(false, "edit button is not clickable on funds page");
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.updateInvestorOrLPNameFromManageInvestor(Workspace.InvestorWorkspace, M9Institution1, M9LimitedPartner1, M9LimitedPartner1+"UP", "M9Institution1")) {
					appLog.info("successfully updated institution1 name");
				}
				else {
					appLog.error("could not update institutions name from manage investors");
					sa.assertTrue(false, "could not update institutions name from manage investors");
				}
			}
			switchToDefaultContent(driver);
			}
			if (bp.clickOnTab(TabName.InstituitonsTab)) {
				if (ip.clickOnCreatedInstitution(M9Institution1)) {
					if (click(driver, bp.getEditButton(30), "edit button base page", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ip.getLegalNameTextBox(60), M9Institution1+"NUP", "leagl name text box",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("passed data in text box: " + M9Institution1+"NUP");
							if (click(driver, ip.getSaveButton(60), "save button", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on save button");
								String str = getText(driver, ip.getLegalNameLabelTextbox(60), "legal Name Label Text",
										action.SCROLLANDBOOLEAN);
								if (str != null) {
									if (str.contains(M9Institution1+"NUP")) {
										appLog.info(M9Institution1+"UP name is changed successfully.");
									}
								}
								else {
									appLog.error("inst name textbox is not visible");
									sa.assertTrue(false, "inst name textbox is not visible");
								}
							}
							else {
								appLog.error("save button is not clickable");
								sa.assertTrue(false, "save button is not clickable");
							}
						}
						else {
							appLog.error("inst name textbox is not visible");
							sa.assertTrue(false, "inst name textbox is not visible");
						}
					}
					else {
						appLog.error("edit button is not clickable on inst page");
						sa.assertTrue(false, "edit button is not clickable on inst page");
					}
				}
				else {
					appLog.error(M9Institution1+" is not found on inst tab");
					sa.assertTrue(false, M9Institution1+" is not found on inst tab");
				}
			}
			else {
				appLog.error("institutions tab is not clickable");
				sa.assertTrue(false, "institutions tab is not clickable");
			}
			if (bp.clickOnTab(TabName.FundsTab)) {
				if (fp.clickOnCreatedFund(M9FundName1)) {
					switchToFrame(driver,30, fp.getFrame( PageName.FundsPage, 30));
					if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getPendingDocsTab(30), "pending documents manage approvals", action.SCROLLANDBOOLEAN)) {
						
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesCommon.split("<break>")[1], M9FundName1+"UP > "+cmnPath, null, Org1FirmName, date)) {
								appLog.info(filesCommon.split("<break>")[1]+" file is successfully verified for updated fund name");
							}
							else {
								appLog.error(filesCommon.split("<break>")[1]+" file could not be verified for updated fund name");
								sa.assertTrue(false, filesCommon.split("<break>")[1]+" file could not be verified for updated fund name");
							}
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesShared.split("<break>")[1], M9FundName1+"UP > "+updatedshdPath, null, Org1FirmName, date)) {
								appLog.info(filesShared.split("<break>")[1]+" file is successfully verified for updated fund name, folder name");
							}
							else {
								appLog.error(filesShared.split("<break>")[1]+" file could not be verified for updated fund name, folder name");
								sa.assertTrue(false, filesShared.split("<break>")[1]+" file could not be verified for updated fund name, folder name");
							}
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[1], M9FundName1+"UP > "+M9Institution1+" > "+M9LimitedPartner1+"UP > "+stdPath+"UP", null, Org1FirmName, date)) {
								appLog.info(filesStandard.split("<break>")[1]+" file is successfully verified for updated fund name, inst name and folder name");
							}
							else {
								appLog.error(filesStandard.split("<break>")[1]+" file could not be verified for updated fund name, inst name and folder name");
								sa.assertTrue(false, filesStandard.split("<break>")[1]+" file could not be verified for updated fund name, inst name and folder name");
							}
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[1], M9FundName1+"UP > "+M9Institution2+" > "+M9LimitedPartner2+" > "+stdPath+"UP", null, Org1FirmName, date)) {
								appLog.info(filesStandard.split("<break>")[1]+" file is successfully verified for updated fund name, inst name and folder name");
							}
							else {
								appLog.error("could not find "+filesStandard.split("<break>")[1]+ " on manage approvals approved docs tab for inst 1");
								sa.assertTrue(false, "could not find "+filesStandard.split("<break>")[1]+ " on manage approvals approved docs tab for inst 1");
							}
							
						}
						if (click(driver, fp.getApprovedDocsTab(30), "approved documents tab", action.SCROLLANDBOOLEAN)) {
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.ApprovedDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), filesCommon.split("<break>")[3],  M9FundName1+"UP > "+cmnPath, null, Org1FirmName, date)) {
								appLog.info(filesCommon.split("<break>")[3]+" (approved docs) file is successfully verified for updated fund name");
							}
							else {
								appLog.error("could not find "+filesCommon.split("<break>")[3]+ " on manage approvals approved docs tab");
								sa.assertTrue(false, "could not find "+filesCommon.split("<break>")[3]+ " on manage approvals approved docs tab");
							}
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.ApprovedDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), filesShared.split("<break>")[3],  M9FundName1+"UP > "+updatedshdPath, null, Org1FirmName, date)) {
								appLog.info(filesShared.split("<break>")[3]+" (approved docs) file is successfully verified for updated fund name");
							}
							else {
								appLog.error("could not find "+filesShared.split("<break>")[3]+ " on manage approvals approved docs tab");
								sa.assertTrue(false, "could not find "+filesShared.split("<break>")[3]+ " on manage approvals approved docs tab");
							}
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.ApprovedDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), filesStandard.split("<break>")[3],  M9FundName1+"UP > "+M9Institution1+" > "+M9LimitedPartner1+"UP > "+stdPath+"UP", null, Org1FirmName, date)) {
								appLog.info(filesStandard.split("<break>")[3]+" (approved docs) file is successfully verified for updated fund name, inst name and folder name");
							}
							else {
								appLog.error("could not find "+filesStandard.split("<break>")[3]+ " on manage approvals approved docs tab for inst 1");
								sa.assertTrue(false, "could not find "+filesStandard.split("<break>")[3]+ " on manage approvals approved docs tab for inst 1");
							}
							if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.ApprovedDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), filesStandard.split("<break>")[3], M9FundName1+"UP > "+M9Institution2+" > "+M9LimitedPartner2+" > "+stdPath+"UP", null, Org1FirmName, date)) {
								appLog.info(filesStandard.split("<break>")[3]+" (approved docs) file is successfully verified for updated fund name and folder name");
							}
							else {
								appLog.error("could not find "+filesStandard.split("<break>")[3]+ " on manage approvals approved docs tab for inst 2");
								sa.assertTrue(false, "could not find "+filesStandard.split("<break>")[3]+ " on manage approvals approved docs tab for inst 2");
							}
						}
						else {
							appLog.error("approved docs tab is not clickable");
							sa.assertTrue(false, "approved docs tab is not clickable");
						}

					}
					else {
						appLog.error("manage approval icon is not clickable");
						sa.assertTrue(false, "manage approval icon is not clickable");
					}
					switchToDefaultContent(driver);
				}
				else {
					appLog.error(M9FundName1+ " is not found on funds tab");
					sa.assertTrue(false, M9FundName1+ " is not found on funds tab");
				}
			}
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc047_2_RevertRenameInvestorAndFundName() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesCommon = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc044_UploadFilesCRMUser2", excelLabel.UploadedFileCommon);
		String filesShared = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M9tc044_UploadFilesCRMUser2", excelLabel.UploadedFileShared);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M9tc044_UploadFilesCRMUser2", excelLabel.UploadedFileStandard);
		String updatedcmnPath = cmnPath.split("\\(")[0].trim()+ "UP ("+cmnPath.split("\\(")[1].trim();
		String updatedshdPath = shdPath.split("\\(")[0].trim()+ "UP ("+shdPath.split("\\(")[1].trim();
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M9Institution1+"NUP")) {
				if (click(driver, bp.getEditButton(30), "edit button base page", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, ip.getLegalNameTextBox(60), M9Institution1, "leagl name text box",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("passed data in text box: " + M9Institution1);
						if (click(driver, ip.getSaveButton(60), "save button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on save button");
							String str = getText(driver, ip.getLegalNameLabelTextbox(60), "legal Name Label Text",
									action.SCROLLANDBOOLEAN);
							if (str != null) {
								if (str.contains(M9Institution1)) {
									appLog.info(M9Institution1+" name is changed successfully.");
								}
							}
							else {
								appLog.error("could not get text from institution name textbox on inst page");
								sa.assertTrue(false, "could not get text from institution name textbox on inst page");
							}
						}
						else {
							appLog.error("save button is not clickable on inst page");
							sa.assertTrue(false, "save button is not clickable on inst page");
						}
					}
					else {
						appLog.error("inst name textbox is not visible");
						sa.assertTrue(false, "inst name textbox is not visible");
					}
				}
				else {
					appLog.error("edit button is not clickable on inst page");
					sa.assertTrue(false, "edit button is not clickable on inst page");
				}
			}
			else {
				appLog.error(M9Institution1+" is not found on inst tab");
				sa.assertTrue(false, M9Institution1+" is not found on inst tab");
			}
		}
		else {
			appLog.error("institutions tab is not clickable");
			sa.assertTrue(false, "institutions tab is not clickable");
		}
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1+"NUP")) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.updateInvestorOrLPNameFromManageInvestor(Workspace.InvestorWorkspace, M9Institution1, M9LimitedPartner1+"UP",M9LimitedPartner1 , "M9Institution1")) {
					appLog.info("successfully updated institution1 name");
				}
				else {
					appLog.error("could not update institution name to "+M9Institution1+"NUP");
					sa.assertTrue(false, "could not update institution name to "+M9Institution1+"NUP");
				}
				switchToDefaultContent(driver);
			}
			scrollDownThroughWebelement(driver, fp.getEditButton(30), "base page edit button");
			if (click(driver, fp.getEditButton(30), "base page edit button", action.SCROLLANDBOOLEAN)) {
				if(sendKeys(driver, fp.getFundName(60), M9FundName1, "Fund Name", action.BOOLEAN)){
					if (click(driver, fp.getSaveButton(60), "Save Button funds page", action.BOOLEAN)) {
						String fundNameViewMode =getText(driver, fp.getFundNameInViewMode(60), "Fund Name", action.BOOLEAN);
						if (fundNameViewMode.equalsIgnoreCase(M9FundName1)) {
							appLog.info("Fund is edit successfully.:" + M9FundName1);
						}
						else {
								appLog.error("fund name is not edited correctly");
								sa.assertTrue(false, "fund name is not edited correctly");
						
					}
					}
					else {
						appLog.error("save button is not clickable on funds page");
						sa.assertTrue(false, "save button is not clickable on funds page");
					}
						
				}
				else {
					appLog.error("fund name textbox is not visible");
					sa.assertTrue(false, "fund name textbox is not visible");
				}
					
			}
			else {
				appLog.error("edit button is not clickable on funds page");
				sa.assertTrue(false, "edit button is not clickable on funds page");
			}
			
			
			
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (click(driver, fp.getInvestmentInfo(Workspace.InvestorWorkspace), "investment info link", action.SCROLLANDBOOLEAN)) {
				if (click(driver, fp.getInvestmentInfoEdit(30), "investment info edit", action.BOOLEAN)) {
					if (sendKeys(driver, fp.getInvestmentInfoFundNameTxtbox(30), M9FundName1, "fund name textbox", action.BOOLEAN)) {
						if (click(driver, fp.getInvestmentInfoSaveBtn(30), "save button", action.BOOLEAN)) {
							if (click(driver, fp.getInvestmentInfoCancelButton(30), "cancel button investment info", action.BOOLEAN)) {
								
							}
							else {
								appLog.error("cancel button on investment info is not clickable");
								sa.assertTrue(false, "cancel button on investment info is not clickable");
							}
						}
						else {
							appLog.error("save button on investment info is not clickable");
							sa.assertTrue(false, "save button on investment info is not clickable");
						}
					}
					else {
						appLog.error("fund name textbox is not visible on investment info popup");
						sa.assertTrue(false, "fund name textbox is not visible on investment info popup");
					}
					}
					else {
						appLog.error("edit icon is not clickable on investment info window");
						sa.assertTrue(false, "edit icon is not clickable on investment info window");
					}
				}
				else {
					appLog.error("investment info link is not clickable");
					sa.assertTrue(false, "investment info link is not clickable");
				}
				switchToDefaultContent(driver);
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test	
	public void M9tc048_VerifySortingManageApprovalInvWorkspace() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);

		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getApprovedDocsTab(30), "approved docs tab", action.BOOLEAN)) {
						//uploaded on ascending
						if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.ApprovedDocuments, 30).get(5), "uploaded on column name", action.SCROLLANDBOOLEAN)) {
							if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalUploadedOnList(ManageApprovalTabs.ApprovedDocuments))) {
								appLog.info("correct sorting is successfully verified for ascending order on uploaded on list");
							}
							else {
								appLog.error("sorting could not be verified for ascending order on uploaded on list");
								sa.assertTrue(false, "sorting could not be verified for ascending order on uploaded on list");
							}
						}
						//uploaded by ascending
						if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.ApprovedDocuments, 30).get(3), "uploaded by column name", action.SCROLLANDBOOLEAN)) {
							if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalUploadedByList(ManageApprovalTabs.ApprovedDocuments))) {
								appLog.info("correct sorting is successfully verified for ascending order on uploaded by list");
							}
							else {
								appLog.error("sorting could not be verified for ascending order on uploaded by list");
								sa.assertTrue(false, "sorting could not be verified for ascending order on uploaded by list");
							}
						}
						//uploaded by descending
						if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.ApprovedDocuments, 30).get(3), "uploaded by column name", action.SCROLLANDBOOLEAN)) {
							if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalUploadedByList(ManageApprovalTabs.ApprovedDocuments))) {
								appLog.info("correct sorting is successfully verified for Decending order on uploaded by list");
							}
							else {
								appLog.error("sorting could not be verified for Decending order on uploaded by list");
								sa.assertTrue(false, "sorting could not be verified for Decending order on uploaded by list");
							}
						}
						//document name ascending
						if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.ApprovedDocuments, 30).get(0), "document name column name", action.SCROLLANDBOOLEAN)) {
							if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalDocumentNameList(ManageApprovalTabs.ApprovedDocuments))) {
								appLog.info("correct sorting is successfully verified for ascending order on document name list");
							}
							else {
								appLog.error("sorting could not be verified for ascending order on document name list");
								sa.assertTrue(false, "sorting could not be verified for ascending order on document name list");
							}
						}
						if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.ApprovedDocuments, 30).get(0), "document name column name", action.SCROLLANDBOOLEAN)) {
							if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalDocumentNameList(ManageApprovalTabs.ApprovedDocuments))) {
								appLog.info("correct sorting is successfully verified for Decending order on document name list");
							}
							else {
								appLog.error("sorting could not be verified for Decending order on document name list");
								sa.assertTrue(false, "sorting could not be verified for Decending order on document name list");
							}
						}
						
						if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 30), "Manage", "search textbox approved docs", action.BOOLEAN)) {
							/*if (fp.noDataToDisplay(ManageApprovalTabs.ApprovedDocuments, 30).getText().trim().equals(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
								appLog.info("no data is present when searched manage in textbox");
							}
							else {
							 */
							click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 30), "search icon manage approvals", action.BOOLEAN);
							HashSet<String> se = scrollActiveWidgetforSetofFiles(driver, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), By.xpath("//span[contains(@id,'aw') and contains(@id,'cell-1-')]"));
							for (String s:se) {
							if (s.contains("Manage")) {
									appLog.info("manage is found for element "+s);
								}
								else {
									appLog.error("manage is not present in "+s);
									sa.assertTrue(false,"manage is not present in "+s);
								}
							}
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0,0)",fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30));
							ThreadSleep(3000);
							if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalDocumentNameList(ManageApprovalTabs.ApprovedDocuments))) {
								appLog.info("correct sorting after searching 'Manage' for descending order is successfully found on document names");

							}
							else {
								appLog.error("sorting is not correct after searching 'Manage' for descending order on document names");
								sa.assertTrue(false, "sorting is not correct after searching 'Manage' for descending order on document names");
							}
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0,0)",fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30));
							
							ThreadSleep(3000);
							if (click(driver, fp.getSearchIconCrossButtonManageApprovals(ManageApprovalTabs.ApprovedDocuments, 30), "cross icon on search textbox", action.BOOLEAN)) {
								if (getText(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 30), "approved docs search box", action.BOOLEAN).equals("")) {
									appLog.info("search box is successfully cleared after clicking cross icon on search box");
								}
								else {
									appLog.error("search box is not empty after clicking cross icon on search box");
									sa.assertTrue(false, "search box is not empty after clicking cross icon on search box");
								}
								if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalDocumentNameList(ManageApprovalTabs.ApprovedDocuments))) {
									appLog.info("correct sorting for descending order is successfully found on document names after clicking cross icon on search box");

								}
								else {
									appLog.error("sorting is not correct for descending order on document names after clicking cross icon on search box");
									sa.assertTrue(false, "sorting is not correct for descending order on document names after clicking cross icon on search box");
								}
								//firm name sorting
								if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.ApprovedDocuments, 30).get(4), "firm name column", action.SCROLLANDBOOLEAN)) {
									if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalFirmNameList(ManageApprovalTabs.ApprovedDocuments))) {
										appLog.info("sorting is successfully verified Assecending order on ApprovedDocuments for firm name column");
									}
									else {
										appLog.error("sorting is incorrect for Assecending firm name column");
										sa.assertTrue(false, "sorting is incorrect for Assecending firm name column");
									}

									if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.ApprovedDocuments, 30).get(4), "firm name column", action.SCROLLANDBOOLEAN)) {
										if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalFirmNameList(ManageApprovalTabs.ApprovedDocuments))) {
											appLog.info("sorting is successfully verified Decending order on ApprovedDocuments for firm name column");
										}
										else {
											appLog.error("sorting is incorrect for Decending firm name column");
											sa.assertTrue(false, "sorting is incorrect for Decending firm name column");
										}
									}
									else {
										appLog.error("firm name column is not clickable");
										sa.assertTrue(false, "firm name column is not clickable");
									}
								}
								else {
									appLog.error("firm name column is not clickable");
									sa.assertTrue(false, "firm name column is not clickable");
								}
							}
						}
						else {
							appLog.error("search icon is not visible on manage approval window");
							sa.assertTrue(false, "search icon is not visible on manage approval window");
						}
					}
					if (click(driver, fp.getPendingDocsTab(30), "pending docs tab", action.BOOLEAN)) {
						ThreadSleep(3000);
						if (click(driver, fp.getColumnListManageApproval(ManageApprovalTabs.PendingDocuments, 30).get(4), "uploaded by column", action.BOOLEAN)) {
							ThreadSleep(3000);
							if (click(driver, fp.getApprovedDocsTab(30), "approved docs tab", action.BOOLEAN)) {
								if (checkSorting(driver, SortOrder.Decending, fp.getManageApprovalUploadedOnList(ManageApprovalTabs.ApprovedDocuments))) {
									appLog.info("correct sorting is successfully verified for Decending order on uploaded on list");
								}
								else {
									appLog.error("sorting could not be verified for Decending order on uploaded on list");
									sa.assertTrue(false, "sorting could not be verified for Decending order on uploaded on list");
								}
							}
							if (click(driver, fp.getPendingDocsTab(30), "pending docs tab", action.BOOLEAN)) {
								if (checkSorting(driver, SortOrder.Assecending, fp.getManageApprovalUploadedByList(ManageApprovalTabs.PendingDocuments))) {
									appLog.info("correct sorting is successfully verified for ascending order on uploaded by list");
								}
								else {
									appLog.error("sorting could not be verified for ascending order on uploaded by list");
									sa.assertTrue(false, "sorting could not be verified for ascending order on uploaded by list");
								}
							}
							else {
								appLog.error("pending docs tab is not clickable");
								sa.assertTrue(false, "pending docs tab is not clickable");
							}
						}
						else {
							appLog.error("uploaded by column is not clickable");
							sa.assertTrue(false, "uploaded by column is not clickable");
						}
					}
					else {
						appLog.error("pending docs tab is not clickable");
						sa.assertTrue(false, "pending docs tab is not clickable");
					}
				}
				else {
					appLog.error("manage approval icon is not clickable");
					sa.assertTrue(false, "manage approval icon is not clickable");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not found on funds page");
				sa.assertTrue(false, M9FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc049_UploadDocumentInvestorSide_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String docpath = "UploadFiles\\Module9\\investorSide";
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.investorLogin(M9Contact1EmailId, adminPassword);
		SoftAssert saa = new SoftAssert();
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if (fp.verifyFolderPathdummy(stdPath+"UP", M9Institution1, M9LimitedPartner1, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
				if (ifp.uploadUpdateFileInvestorSide(stdPath+"UP",filesStandard, M9Institution1, M9LimitedPartner1, FolderType.Standard, docpath, null, 30,
						PageName.CurrentInvestmentPgae, null, null, WorkSpaceAction.UPLOAD)) {
					appLog.info("successfully uploaded file to investor side");
				}
				else {
					appLog.error("could not upload file on investor side"); 
					sa.assertTrue(false, "could not upload file on investor side");
					
				}
				click(driver,ifp.getRefreshIcon(30), "content grid refresh", action.BOOLEAN);
				saa =ifp.verifyContentGridInvestorSide(driver, PageName.CurrentInvestmentPgae, filesStandard, M9Contact1FirstName+" "+M9Contact1LastName, date) ;
				sa.combineAssertions(saa);
				
			}
			else {
				appLog.error("could not find "+stdPath+"UP on folder structure");
				sa.assertTrue(false, "could not find "+stdPath+"UP on folder structure");
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Test
	public void M9tc049_UploadDocumentInvestorSide_Impact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M9tc049_UploadDocumentInvestorSide_Action", excelLabel.UploadedFileStandard);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M9FundName1)) {
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (fp.verifyFolderPathdummy(stdPath+"UP", M9Institution1, M9LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
				if (fp.verifyFileinContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace, filesStandard,true)) {
					appLog.info("investor side uploaded file is successfully found on content grid");
				}
				else {
					appLog.error("investor side uploaded file could not be found on content grid");
					sa.assertTrue(false, "investor side uploaded file could not be found on content grid");
				}
				if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getApprovedDocsTab(30), "approved docs tab", action.SCROLLANDBOOLEAN)) {
						/*if (!fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.ApprovedDocuments, filesStandard, M9Institution1+"UP > "+M9LimitedPartner1, "approved", M9Contact1FirstName+" "+M9Contact1LastName, Org1FirmName, date)) {
							appLog.info("investor uploaded file is not present as expected manage approvals window");
						}
						else {
							appLog.error("file uploaded from investor side is found on manage approvals window but it should not be");
							sa.assertTrue(false, "file uploaded from investor side is found on manage approvals window but it should not be");
						}
						*/HashSet<String> se = scrollActiveWidgetforSetofFiles(driver, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), By.xpath("//span[contains(@id,'aw') and contains(@id,'-cell-1')]//a"));
						boolean flag = false;
						for (String s:se) {
							if (s.equals(filesStandard)) {
								appLog.error("found "+filesStandard+" in approved docs tab but it should not be present");
								flag = true;
								break;
							}
						}
						if (flag == true) {
							sa.assertTrue(false, "found "+filesStandard+" in approved docs tab but it should not be present");
						}
						else if(flag == false) {
							appLog.info(filesStandard + " is not found as expected on approved docs tab");
						}
						if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 30), filesStandard, "search textbox", action.SCROLLANDBOOLEAN)) {
							click(driver,fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 30), "manage approvals search icon", action.SCROLLANDBOOLEAN);
							if (fp.noDataToDisplay(ManageApprovalTabs.ApprovedDocuments, 30).getText().trim().equals(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
								appLog.info("no data to display error message is successfully verified");
							}
							else  {
								appLog.error("no data to display is not found in manage approval window");
								sa.assertTrue(false, "no data to display is not found in manage approval window");
							}
							
						}
						else {
							appLog.error("search text box on approved docs is not visible");
							sa.assertTrue(false, "search text box on approved docs is not visible");
						}
					}
					else {
						appLog.error("approved docs tab is not clickable");
						sa.assertTrue(false, "approved docs tab is not clickable");
					}
				}
				else {
					appLog.error("manage approval icon is not clickable");
					sa.assertTrue(false, "manage approval icon is not clickable");
				}
			}
			else {
				appLog.error(stdPath+" is not found on folder structure");
				sa.assertTrue(false, stdPath+" is not found on folder structure");
			}
			switchToDefaultContent(driver);
			}
			else {
				appLog.error(M9FundName1+" is not found on funds page");
				sa.assertTrue(false, M9FundName1+" is not found on funds page");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
		
	}

	@Test
	public void M9tc050_VerifyRemovalAndAdditionOfInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String filesStandard = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M9tc044_UploadFilesCRMUser2", excelLabel.UploadedFileStandard);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
		if (fp.clickOnCreatedFund(M9FundName1)) {
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			if (click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 30), "manage investor icon", action.SCROLLANDBOOLEAN)) {
				if (fp.selectInstitutionOrLP(M9Institution2+"/"+M9LimitedPartner2, Workspace.InvestorWorkspace, 30)) {
					appLog.info("deselected institution,LP "+M9LimitedPartner2+" successfully");
				}
				else {
					appLog.error("chechbox for inst2 is not clickable");
					sa.assertTrue(false, "chechbox for inst2 is not clickable");
				}
				if (click(driver, fp.getManageInvestorDoneButton(Workspace.InvestorWorkspace, 30), "manage investor done button", action.BOOLEAN)) {

				}
				else {
					appLog.error("done button on manage investor is not clickable");
					sa.assertTrue(false, "done button on manage investor is not clickable");
				}
			}
			if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon", action.BOOLEAN)) {
				if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[1], M9FundName1+" > "+M9Institution2+" > "+M9LimitedPartner2, null, Org1FirmName, date)) {
					appLog.info("successfully verified "+filesStandard.split("<break>")[1]+" file on manage approvals pending window");
				}
				else {
					appLog.error("could not be verified "+filesStandard.split("<break>")[1]+" file on manage approvals pending window");
					sa.assertTrue(false, "could not be verified "+filesStandard.split("<break>")[1]+" file on manage approvals pending window");
				}
				if (click(driver, fp.getApprovedDocsTab(30), "approved docs tab", action.BOOLEAN)) {
					if (fp.findRowByScrollingManageApprovals(ManageApprovalTabs.ApprovedDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), filesStandard.split("<break>")[3], M9FundName1+" > "+M9Institution2+" > "+M9LimitedPartner2, null, Org1FirmName, date)) {
						appLog.info("successfully verified "+filesStandard.split("<break>")[3]+" file on manage approvals approved docs window");
					}
					else {
						appLog.error("could not be verified "+filesStandard.split("<break>")[3]+" file on manage approvals approved docs window");
						sa.assertTrue(false, "could not be verified "+filesStandard.split("<break>")[3]+" file on manage approvals approved docs window");
					}
				}
				else {
					appLog.error("approved docs tab is not clickable");
					sa.assertTrue(false, "approved docs tab is not clickable");
				}
			}
			else {
				appLog.error("manage approval icon is not clickable");
				sa.assertTrue(false, "manage approval icon is not clickable");
			}
			switchToDefaultContent(driver);
			}
		
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M9tc051_postCondition(){
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

